package com.oplus.vfxsdk.common;

import android.support.v4.content.FileProvider$;
import android.view.Choreographer;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorHandler {
    private final AnimatorHandler$frameCallback$1 frameCallback;
    public long lastTime;
    public boolean playing;
    private final String TAG = "AnimatorHandler";
    private ArrayList<FrameListener> frameListeners = new ArrayList<>();

    public AnimatorHandler() {
        AnimatorHandler$frameCallback$1 animatorHandler$frameCallback$1 = new AnimatorHandler$frameCallback$1();
        animatorHandler$frameCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.frameCallback = animatorHandler$frameCallback$1;
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0133  */
    /* JADX WARN: Code duplicated, block: B:32:0x0135  */
    public final void onDrawFrame(long j) {
        int i;
        ArrayList<FrameListener> arrayList;
        float f;
        boolean z;
        Object[] array;
        boolean z2;
        ArrayList<FrameListener> arrayList2 = this.frameListeners;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            AbsAnimator.onTriger.3 r4 = arrayList2.get(i2);
            int i3 = i2 + 1;
            AbsAnimator.onTriger.3 r5 = (FrameListener) r4;
            ArrayList arrayList3 = r5.$animParameters;
            AbsAnimator absAnimator = r5.this$0;
            int size2 = arrayList3.size();
            boolean z3 = false;
            int i4 = 0;
            while (i4 < size2) {
                Object obj = arrayList3.get(i4);
                i4++;
                AbsAnimator.StateAnimParam stateAnimParam = (AbsAnimator.StateAnimParam) obj;
                long duration = stateAnimParam.getUniformValue().getDuration();
                float delay = stateAnimParam.getUniformValue().getDelay();
                if (stateAnimParam.isDone) {
                    arrayList = arrayList2;
                    i = i3;
                    z = false;
                } else {
                    i = i3;
                    float f2 = j;
                    if (f2 < delay) {
                        arrayList = arrayList2;
                    } else {
                        float f3 = f2 - delay;
                        arrayList = arrayList2;
                        float interpolation = Intrinsics.areEqual(stateAnimParam.getUniformValue().getIpol(), "bezier") ? stateAnimParam.getDefaultParameter().getParam().getInterpolator().getInterpolation(Math.min(1.0f, f3 / duration)) : stateAnimParam.getDefaultParameter().getParam().getInterpolator().getInterpolation(f3);
                        Object srcValue = stateAnimParam.getSrcValue();
                        if (srcValue instanceof Float) {
                            array = new Object[]{Float.valueOf((((Float) stateAnimParam.getDeltaValue()).floatValue() * interpolation) + ((Number) stateAnimParam.getSrcValue()).floatValue())};
                        } else {
                            if (srcValue instanceof Integer) {
                                array = new Object[]{Float.valueOf((((Integer) stateAnimParam.getDeltaValue()).intValue() * interpolation) + ((Number) stateAnimParam.getSrcValue()).floatValue())};
                            } else {
                                if (!(srcValue instanceof float[])) {
                                    Object srcValue2 = stateAnimParam.getSrcValue();
                                    throw new IllegalArgumentException(FileProvider$.ExternalSyntheticOutline0.m("Unsupported type: ", srcValue2 != null ? srcValue2.getClass().getSimpleName() : null));
                                }
                                float[] fArr = (float[]) stateAnimParam.getSrcValue();
                                ArrayList arrayList4 = new ArrayList(fArr.length);
                                int length = fArr.length;
                                int i5 = 0;
                                int i6 = 0;
                                while (i6 < length) {
                                    arrayList4.add(Float.valueOf((((float[]) stateAnimParam.getDeltaValue())[i5] * interpolation) + fArr[i6]));
                                    i6++;
                                    interpolation = interpolation;
                                    i5++;
                                }
                                f = interpolation;
                                z = false;
                                array = arrayList4.toArray(new Object[0]);
                            }
                            AbsAnimator.setParamValue(stateAnimParam.getDefaultParameter(), array);
                            if (f >= r13) {
                                z2 = true;
                            } else {
                                z2 = z;
                            }
                            stateAnimParam.setDone(z2);
                            if (f < 1065353216) {
                            }
                            i3 = i;
                            arrayList2 = arrayList;
                        }
                        f = interpolation;
                        z = false;
                        AbsAnimator.setParamValue(stateAnimParam.getDefaultParameter(), array);
                        if (f >= r13) {
                            z2 = true;
                        } else {
                            z2 = z;
                        }
                        stateAnimParam.setDone(z2);
                        if (f < 1065353216) {
                        }
                        i3 = i;
                        arrayList2 = arrayList;
                    }
                    i3 = i;
                    arrayList2 = arrayList;
                }
                z3 = z3 ? true : z;
                i3 = i;
                arrayList2 = arrayList;
            }
            ArrayList<FrameListener> arrayList5 = arrayList2;
            int i7 = i3;
            if (!z3) {
                absAnimator.getAnimatorHandler().stop();
                Function0 function0 = r5.$endCb;
                if (function0 != null) {
                }
                Function0 function1 = absAnimator.animatorEndCb;
                if (function1 != null) {
                }
            }
            i2 = i7;
            arrayList2 = arrayList5;
        }
    }

    public final void play() {
        this.playing = true;
        this.lastTime = System.currentTimeMillis();
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
    }

    public final void registerFrameListener(FrameListener frameListener) {
        this.frameListeners.add(frameListener);
    }

    public final void stop() {
        this.playing = false;
        this.frameListeners.clear();
        Choreographer.getInstance().removeFrameCallback(this.frameCallback);
    }

    public final void unregisterFrameListener(FrameListener frameListener) {
        this.frameListeners.remove(frameListener);
    }
}
