package com.oplus.vfxsdk.common;

import android.animation.TimeInterpolator;
import android.view.Choreographer;
import android.view.animation.PathInterpolator;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.vfxsdk.common.api.ILinkProxy;
import com.oplus.vfxsdk.common.api.IPlayer;
import com.oplus.vfxsdk.common.parameter.IPop;
import com.oplus.vfxsdk.common.parameter.IUpdate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Animator implements IPlayer {
    public static final Companion Companion = new Companion();
    public static final String TAG = "VFX:Animator";
    private final COEExpressions coeExpressions;
    private final AnimatorValue data;
    private final FrameCallbackWrapper frameCb;
    private final ILinkProxy iLinkProxy;
    private boolean isAnimEnd;
    private boolean isPlayMainLooper;
    private Function0 mAnimEndListener;
    private CopyOnWriteArrayList<AnimEndListener> mAnimEndListenerList;
    private ArrayList<AnimatorLine> mAnimLines;
    private AnimMode mAnimMode;
    private float mAnimSpeed;
    private Function0 mAnimStartListener;
    private AnimaStatus mAnimStatus;
    private boolean mAnimStatusChanged;
    private Function1 mAnimUpdateListener;
    private double mCurrTime;
    private int mDirection;
    private double mEndTime;
    private boolean mPlayToDirty;
    private double mPlayToTime;
    private double mStartTime;
    private double mSyncPreTime;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimMode {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimMode[] $VALUES;
        public static final AnimMode LOOP;
        public static final AnimMode ONCE;
        public static final AnimMode REVERSE_LOOP;

        static {
            AnimMode animMode = new AnimMode("LOOP", 0);
            LOOP = animMode;
            AnimMode animMode2 = new AnimMode("REVERSE_LOOP", 1);
            REVERSE_LOOP = animMode2;
            AnimMode animMode3 = new AnimMode("ONCE", 2);
            ONCE = animMode3;
            AnimMode[] animModeArr = {animMode, animMode2, animMode3};
            $VALUES = animModeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animModeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimMode valueOf(String str) {
            return (AnimMode) Enum.valueOf(AnimMode.class, str);
        }

        public static AnimMode[] values() {
            return (AnimMode[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimaStatus {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimaStatus[] $VALUES;
        public static final AnimaStatus Pause;
        public static final AnimaStatus Play;
        public static final AnimaStatus Stop;

        static {
            AnimaStatus animaStatus = new AnimaStatus("Play", 0);
            Play = animaStatus;
            AnimaStatus animaStatus2 = new AnimaStatus("Pause", 1);
            Pause = animaStatus2;
            AnimaStatus animaStatus3 = new AnimaStatus("Stop", 2);
            Stop = animaStatus3;
            AnimaStatus[] animaStatusArr = {animaStatus, animaStatus2, animaStatus3};
            $VALUES = animaStatusArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animaStatusArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimaStatus valueOf(String str) {
            return (AnimaStatus) Enum.valueOf(AnimaStatus.class, str);
        }

        public static AnimaStatus[] values() {
            return (AnimaStatus[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimatorLine {
        public float currentTime = -1.0f;
        public float lastUpdateTime = -1.0f;
        private IKey[] mAnimKeys;
        private final AnimLine mAnimLine;
        public float mCurrentValue;
        public float mEndTime;
        private String mName;
        private Object mOrigin;
        private IPop mPop;
        public float mStartTime;
        private IUpdate mUpdate;

        public AnimatorLine(AnimLine animLine) {
            this.mAnimLine = animLine;
            this.mName = animLine.getName();
            this.mUpdate = animLine.getUpdate();
            this.mPop = animLine.getPop();
            IKey[] animKeys = animLine.getAnimKeys();
            this.mAnimKeys = animKeys;
            if (animKeys.length > 1) {
                ArraysKt___ArraysJvmKt.sortWith(animKeys, new Animator$AnimatorLine$special$$inlined$sortBy$1());
            }
            this.mStartTime = ((IKey) ArraysKt___ArraysKt.first(this.mAnimKeys)).getTime();
            this.mEndTime = ((IKey) ArraysKt___ArraysKt.last(this.mAnimKeys)).getTime();
            this.mCurrentValue = ((IKey) ArraysKt___ArraysKt.first(this.mAnimKeys)).getValue();
        }

        public final void forceCacheExprValue() {
            IPop iPop = this.mPop;
            Object objPopValue = iPop != null ? iPop.popValue(this.mAnimLine.getChannelData(), this.mName, this.mAnimLine.getIndex()) : null;
            if (objPopValue instanceof Float) {
                this.mOrigin = Float.valueOf(((Number) objPopValue).floatValue());
                for (IKey iKey : this.mAnimKeys) {
                    iKey.setCacheValue(getAnimKeyValue(iKey));
                }
            }
        }

        public final float getAnimKeyValue(IKey iKey) {
            AnimKey animKey;
            String expr;
            if (!(iKey instanceof AnimKey) || (expr = (animKey = (AnimKey) iKey).getExpr()) == null || expr.length() == 0) {
                return iKey.getValue();
            }
            Object obj = this.mOrigin;
            float fFloatValue = (obj == null || !(obj instanceof Float)) ? 0.0f : ((Float) obj).floatValue();
            COEExpressions coeExpressions = Animator.this.getCoeExpressions();
            String expr2 = animKey.getExpr();
            expr2.getClass();
            return coeExpressions.getValue(expr2, iKey.getValue(), fFloatValue).floatValue();
        }

        public final AnimLine getMAnimLine() {
            return this.mAnimLine;
        }

        public final float getMEndTime() {
            return this.mEndTime;
        }

        public final float getMStartTime() {
            return this.mStartTime;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final void seekOnce(boolean z) {
            ILinkProxy iLinkProxy;
            String custom;
            String custom2;
            IKey[] iKeyArr = this.mAnimKeys;
            int iBinarySearchByKey = AnimatorKt.binarySearchByKey(iKeyArr, Float.valueOf(this.currentTime), new Function1() { // from class: com.oplus.vfxsdk.common.Animator$AnimatorLine$seekOnce$index$1
                public final Float invoke(IKey iKey) {
                    return Float.valueOf(iKey.getTime());
                }
            });
            float interpolation = 0.0f;
            if (this.currentTime == 0.0f) {
                this.lastUpdateTime = 0.0f;
            }
            IPop iPop = this.mPop;
            Object objPopValue = iPop != null ? iPop.popValue(this.mAnimLine.getChannelData(), this.mName, this.mAnimLine.getIndex()) : null;
            int i = 0;
            if ((z || iBinarySearchByKey <= 0) && (objPopValue instanceof Float)) {
                this.mOrigin = Float.valueOf(((Number) objPopValue).floatValue());
                for (IKey iKey : this.mAnimKeys) {
                    iKey.setCacheValue(getAnimKeyValue(iKey));
                }
            }
            if (this.mAnimLine.getType() != UniformType.Event) {
                if (iBinarySearchByKey >= iKeyArr.length || this.currentTime >= this.mEndTime - 0.001f) {
                    IKey iKey2 = (IKey) ArraysKt___ArraysKt.last(iKeyArr);
                    if (iKey2 instanceof AnimKey) {
                        this.mCurrentValue = iKey2.getCacheValue();
                        IUpdate iUpdate = this.mUpdate;
                        if (iUpdate != null) {
                            iUpdate.updateValueIndex(this.mAnimLine.getChannelData(), this.mName, Float.valueOf(this.mCurrentValue), this.mAnimLine.getIndex());
                            return;
                        }
                        return;
                    }
                    return;
                }
                IKey iKey3 = iKeyArr[iBinarySearchByKey];
                if (iBinarySearchByKey == 0) {
                    this.mCurrentValue = iKeyArr[0].getCacheValue();
                    IUpdate iUpdate2 = this.mUpdate;
                    if (iUpdate2 != null) {
                        iUpdate2.updateValueIndex(this.mAnimLine.getChannelData(), this.mName, Float.valueOf(this.mCurrentValue), this.mAnimLine.getIndex());
                        return;
                    }
                    return;
                }
                if ((iKey3 instanceof AnimKey) && Intrinsics.areEqual(((AnimKey) iKey3).getExpr(), "origin") && this.mOrigin != null && !Float.valueOf(iKey3.getValue()).equals(this.mOrigin)) {
                    iKey3.setValue(((Float) this.mOrigin).floatValue());
                }
                float cacheValue = iKey3.getCacheValue();
                AnimKey animKey = (AnimKey) iKeyArr[iBinarySearchByKey - 1];
                float time = animKey.getTime();
                float cacheValue2 = animKey.getCacheValue();
                float time2 = iKey3.getTime() - time;
                String ipol = animKey.getIpol();
                if (Intrinsics.areEqual(ipol, "bezier")) {
                    if (animKey.getInterpolator() == null) {
                        animKey.setInterpolator(new PathInterpolator(animKey.getBezier()[0], animKey.getBezier()[1], animKey.getBezier()[2], animKey.getBezier()[3]));
                    }
                    TimeInterpolator interpolator = animKey.getInterpolator();
                    interpolator.getClass();
                    interpolation = interpolator.getInterpolation((this.currentTime - time) / time2);
                } else if (Intrinsics.areEqual(ipol, "spring")) {
                    if (animKey.getInterpolator() == null) {
                        animKey.setInterpolator(AbsAnimatorKt.createSpringAnimation(animKey.getSpring()[0], animKey.getSpring()[1], animKey.getSpring()[2]));
                    }
                    COUISpringAnimation interpolator2 = animKey.getInterpolator();
                    if (interpolator2.getValue() == 100.0f) {
                        interpolator2.setStartValue(0.0f);
                        interpolator2.setStartVelocity(interpolator2.defaultStartVelocity);
                        interpolator2.start();
                    }
                    float time3 = this.currentTime - animKey.getTime();
                    TimeInterpolator interpolator3 = animKey.getInterpolator();
                    interpolator3.getClass();
                    interpolation = interpolator3.getInterpolation(time3 * 1000.0f);
                }
                this.mCurrentValue = DrawerArrowDrawable$.ExternalSyntheticOutline0.m(cacheValue, cacheValue2, interpolation, cacheValue2);
                IUpdate iUpdate3 = this.mUpdate;
                if (iUpdate3 != null) {
                    iUpdate3.updateValueIndex(this.mAnimLine.getChannelData(), this.mName, Float.valueOf(this.mCurrentValue), this.mAnimLine.getIndex());
                    return;
                }
                return;
            }
            if (iBinarySearchByKey > 0) {
                IKey iKey4 = iKeyArr[iBinarySearchByKey - 1];
                if ((iKey4 instanceof EventKey) && iKey4.getTime() >= this.lastUpdateTime && iKey4.getTime() <= this.currentTime) {
                    EventKey eventKey = (EventKey) iKey4;
                    boolean zAreEqual = Intrinsics.areEqual(eventKey.getEventType(), "anim");
                    Animator animator = Animator.this;
                    if (zAreEqual) {
                        ArrayList arrayList = new ArrayList();
                        if (animator.getILinkProxy() != null) {
                            Integer[] linkIds = eventKey.getLinkIds();
                            if (linkIds != null) {
                                for (Integer num : linkIds) {
                                    IPlayer byId = animator.getILinkProxy().getById(num.intValue());
                                    if (byId != null) {
                                        arrayList.add(byId);
                                    }
                                }
                            }
                            int size = arrayList.size();
                            while (i < size) {
                                Object obj = arrayList.get(i);
                                i++;
                                IPlayer iPlayer = (IPlayer) obj;
                                String action = eventKey.getAction();
                                if (action != null) {
                                    switch (action.hashCode()) {
                                        case -906224877:
                                            if (action.equals("seekTo") && (custom = eventKey.getCustom()) != null && custom.length() != 0) {
                                                iPlayer.seekTo(Double.parseDouble(eventKey.getCustom()));
                                            }
                                            break;
                                        case 3443508:
                                            if (action.equals(AodMediaStatisticUtil.EVENT_PLAY)) {
                                                iPlayer.play();
                                            }
                                            break;
                                        case 3540994:
                                            if (action.equals("stop")) {
                                                iPlayer.stop();
                                            }
                                            break;
                                        case 106440182:
                                            if (action.equals(AodMediaStatisticUtil.EVENT_PAUSE)) {
                                                iPlayer.pause();
                                            }
                                            break;
                                        case 109641799:
                                            if (action.equals("speed") && (custom2 = eventKey.getCustom()) != null && custom2.length() != 0) {
                                                iPlayer.setSpeed(Float.parseFloat(eventKey.getCustom()));
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    } else if (Intrinsics.areEqual(eventKey.getEventType(), SeparateQSStatisicUtil.VALUE_PANEL_NOTIFY) && eventKey.getAction() != null && (iLinkProxy = animator.getILinkProxy()) != null) {
                        iLinkProxy.notify(eventKey.getAction(), eventKey.getCustom());
                    }
                }
            }
            this.lastUpdateTime = this.currentTime;
        }

        public final void seekTo(double d, boolean z) {
            float f = this.currentTime;
            float f2 = (float) d;
            if (f != f2 || z || f == 0.0f) {
                this.currentTime = f2;
                if (f2 == 0.0f) {
                    seekOnce(true);
                } else {
                    seekOnce(z);
                }
            }
        }

        public final void setAnimKeyValue(float f, int i) {
            this.mAnimKeys[i].setValue(f);
        }

        public final void setAnimLinePop(IPop iPop) {
            this.mPop = iPop;
        }

        public final void setAnimLineUpdate(IUpdate iUpdate) {
            this.mUpdate = iUpdate;
        }

        public final void setMEndTime(float f) {
            this.mEndTime = f;
        }

        public final void setMStartTime(float f) {
            this.mStartTime = f;
        }

        public final void updateExpr() {
            for (IKey iKey : this.mAnimKeys) {
                iKey.setCacheValue(getAnimKeyValue(iKey));
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FrameCallbackWrapper implements Choreographer.FrameCallback {
        private final WeakReference<Animator> weakAnimator;

        public FrameCallbackWrapper(Animator animator) {
            this.weakAnimator = new WeakReference<>(animator);
        }

        @Override // android.view.Choreographer.FrameCallback
        public final void doFrame(long j) {
            Animator animator = this.weakAnimator.get();
            if (animator == null) {
                return;
            }
            animator.frameUpdate(j / 1.0E9d);
            if (animator.isPlay()) {
                Choreographer.getInstance().postFrameCallback(this);
            }
        }
    }

    public Animator(AnimatorValue animatorValue, COEExpressions cOEExpressions, ILinkProxy iLinkProxy) {
        this.data = animatorValue;
        this.coeExpressions = cOEExpressions;
        this.iLinkProxy = iLinkProxy;
        this.mStartTime = Double.MAX_VALUE;
        this.mEndTime = Double.MIN_VALUE;
        this.mAnimLines = new ArrayList<>();
        this.mAnimSpeed = 1.0f;
        this.mSyncPreTime = -1.0d;
        this.mDirection = 1;
        this.mAnimMode = AnimMode.ONCE;
        this.mAnimStatus = AnimaStatus.Pause;
        this.isPlayMainLooper = true;
        this.frameCb = new FrameCallbackWrapper(this);
        this.mAnimEndListenerList = new CopyOnWriteArrayList<>();
    }

    private final void addAnimLine(AnimLine animLine) {
        if (animLine.getAnimKeys().length == 0) {
            return;
        }
        AnimatorLine animatorLine = new AnimatorLine(animLine);
        if (animatorLine.getMStartTime() < this.mStartTime) {
            this.mStartTime = animatorLine.getMStartTime();
        }
        if (animatorLine.getMEndTime() > this.mEndTime) {
            this.mEndTime = animatorLine.getMEndTime();
        }
        this.mAnimLines.add(animatorLine);
    }

    private final double clampValue(double d, double d2, double d3) {
        if (d > d3) {
            return d3;
        }
        return d < d2 ? d2 : d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void frameUpdate(double d) {
        AnimaStatus animaStatus;
        Function1 function1;
        if (this.mSyncPreTime < 0.0d) {
            this.mSyncPreTime = d;
        }
        if (d - this.mSyncPreTime > 0.1d) {
            this.mSyncPreTime = d;
        }
        if (this.mAnimStatusChanged && this.mAnimStatus == AnimaStatus.Play) {
            this.mAnimStatusChanged = false;
            Function0 function0 = this.mAnimStartListener;
            if (function0 != null) {
            }
        }
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            AnimatorLine animatorLine2 = animatorLine;
            animatorLine2.getClass();
            animatorLine2.seekTo(this.mCurrTime, false);
        }
        AnimaStatus animaStatus2 = this.mAnimStatus;
        AnimaStatus animaStatus3 = AnimaStatus.Play;
        if (animaStatus2 == animaStatus3 && (function1 = this.mAnimUpdateListener) != null) {
        }
        double d2 = this.mAnimStatus == animaStatus3 ? d - this.mSyncPreTime : 0.0d;
        double d3 = this.mCurrTime;
        double d4 = ((double) this.mAnimSpeed) * d2;
        int i2 = this.mDirection;
        double d5 = (d4 * ((double) i2)) + d3;
        this.mCurrTime = d5;
        double d6 = this.mEndTime;
        if (d5 > d6 || d5 < 0.0d) {
            AnimMode animMode = this.mAnimMode;
            if (animMode == AnimMode.LOOP) {
                this.mCurrTime = 0.0d;
            } else if (animMode == AnimMode.REVERSE_LOOP) {
                this.mDirection = -i2;
            } else if (animMode == AnimMode.ONCE) {
                this.mCurrTime = d5 > d6 ? d6 : 0.0d;
                boolean z = this.mPlayToDirty;
                if (z) {
                    animaStatus = AnimaStatus.Pause;
                } else {
                    if (z) {
                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                        return;
                    }
                    animaStatus = AnimaStatus.Stop;
                }
                this.mAnimStatus = animaStatus;
                this.isAnimEnd = true;
            }
        }
        if (this.mPlayToDirty && Math.abs(this.mCurrTime - this.mPlayToTime) < d2 * ((double) this.mAnimSpeed) * 2.0d) {
            this.mPlayToDirty = false;
            this.mDirection = 1;
            this.mAnimStatus = AnimaStatus.Pause;
            this.mCurrTime = this.mPlayToTime;
            this.isAnimEnd = true;
        }
        if (this.isAnimEnd) {
            ArrayList<AnimatorLine> arrayList2 = this.mAnimLines;
            int size2 = arrayList2.size();
            int i3 = 0;
            while (i3 < size2) {
                AnimatorLine animatorLine3 = arrayList2.get(i3);
                i3++;
                AnimatorLine animatorLine4 = animatorLine3;
                animatorLine4.getClass();
                animatorLine4.seekTo(this.mCurrTime, false);
            }
            Function0 function2 = this.mAnimEndListener;
            if (function2 != null) {
            }
            Iterator<AnimEndListener> it = this.mAnimEndListenerList.iterator();
            while (it.hasNext()) {
                it.next().onEnd();
            }
            this.isAnimEnd = false;
            if (this.mAnimMode == AnimMode.ONCE && this.mAnimStatus == AnimaStatus.Stop) {
                reset();
            }
        }
        this.mSyncPreTime = d;
    }

    private final void reset() {
        this.mCurrTime = 0.0d;
        this.mDirection = 1;
        this.mPlayToDirty = false;
    }

    public static /* synthetic */ void stop$default(Animator animator, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        animator.stop(z);
    }

    public final void addAnimEndListener(AnimEndListener animEndListener) {
        this.mAnimEndListenerList.add(animEndListener);
    }

    public final void forceFlushExprCache() {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            animatorLine.forceCacheExprValue();
        }
    }

    public final COEExpressions getCoeExpressions() {
        return this.coeExpressions;
    }

    public final AnimatorValue getData() {
        return this.data;
    }

    public final float getEndTime() {
        return (float) Math.max(0.0d, this.mEndTime);
    }

    public final ILinkProxy getILinkProxy() {
        return this.iLinkProxy;
    }

    public final AnimMode getMAnimMode() {
        return this.mAnimMode;
    }

    public final AnimaStatus getMAnimStatus() {
        return this.mAnimStatus;
    }

    public final void init() {
        for (AnimLine animLine : this.data.getAnimLines()) {
            addAnimLine(animLine);
        }
        AnimLine eventLine = this.data.getEventLine();
        if (eventLine != null) {
            addAnimLine(eventLine);
        }
        this.mCurrTime = 0.0d;
    }

    public final boolean isPause() {
        return this.mAnimStatus == AnimaStatus.Pause;
    }

    public final boolean isPlay() {
        return this.mAnimStatus == AnimaStatus.Play;
    }

    public final boolean isPlayMainLooper() {
        return this.isPlayMainLooper;
    }

    public void onUpdate(double d) {
        frameUpdate(d);
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void pause() {
        this.mAnimStatus = AnimaStatus.Pause;
    }

    public final void play(boolean z) {
        AnimaStatus animaStatus = this.mAnimStatus;
        AnimaStatus animaStatus2 = AnimaStatus.Play;
        if (animaStatus != animaStatus2) {
            if (z) {
                Choreographer.getInstance().postFrameCallback(this.frameCb);
            }
            this.mAnimStatusChanged = true;
        }
        this.mAnimStatus = animaStatus2;
    }

    public final void playTo(double d) {
        double dClampValue = clampValue(d, 0.0d, this.mEndTime);
        play(this.isPlayMainLooper);
        this.mDirection = this.mCurrTime < dClampValue ? 1 : -1;
        this.mPlayToDirty = true;
        this.mPlayToTime = dClampValue;
    }

    public final void removeAnimEndListener(AnimEndListener animEndListener) {
        this.mAnimEndListenerList.remove(animEndListener);
    }

    public final void restart(boolean z) {
        reset();
        play(z);
    }

    public final void seekNext() {
        pause();
        double d = this.mCurrTime + 0.016666666666666666d;
        this.mCurrTime = d;
        if (d > this.mEndTime) {
            this.mCurrTime = 0.0d;
        }
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            animatorLine.seekTo(this.mCurrTime, true);
        }
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void seekTo(double d) {
        this.mAnimStatus = AnimaStatus.Play;
        this.mCurrTime = d;
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            animatorLine.seekTo(d, true);
        }
        pause();
    }

    public final void seekToEnd() {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        float fMax = 0.0f;
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            fMax = Math.max(fMax, animatorLine.getMEndTime());
        }
        seekTo(fMax);
    }

    public final void setAnimEndListener(Function0 function0) {
        this.mAnimEndListener = function0;
    }

    public final void setAnimKeyValue(String str, int i, float f) {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            AnimatorLine animatorLine = arrayList.get(i2);
            i2++;
            AnimatorLine animatorLine2 = animatorLine;
            if (Intrinsics.areEqual(animatorLine2.getMAnimLine().getName(), str)) {
                animatorLine2.setAnimKeyValue(f, i);
            }
        }
    }

    public final void setAnimLinePop(int i, IPop iPop) {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            AnimatorLine animatorLine = arrayList.get(i2);
            i2++;
            AnimatorLine animatorLine2 = animatorLine;
            if (animatorLine2.getMAnimLine().hashCode() == i) {
                animatorLine2.setAnimLinePop(iPop);
            }
        }
    }

    public final void setAnimLineUpdate(int i, IUpdate iUpdate) {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            AnimatorLine animatorLine = arrayList.get(i2);
            i2++;
            AnimatorLine animatorLine2 = animatorLine;
            if (animatorLine2.getMAnimLine().hashCode() == i) {
                animatorLine2.setAnimLineUpdate(iUpdate);
            }
        }
    }

    public final void setAnimStartListener(Function0 function0) {
        this.mAnimStartListener = function0;
    }

    public final void setAnimUpdateListener(Function1 function1) {
        this.mAnimUpdateListener = function1;
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void setLoop(boolean z) {
        this.mAnimMode = z ? AnimMode.LOOP : AnimMode.ONCE;
    }

    public final void setMAnimMode(AnimMode animMode) {
        this.mAnimMode = animMode;
    }

    public final void setMAnimStatus(AnimaStatus animaStatus) {
        this.mAnimStatus = animaStatus;
    }

    public final void setPlayMainLooper(boolean z) {
        this.isPlayMainLooper = z;
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void setSpeed(float f) {
        this.mAnimSpeed = f;
    }

    public final void stop(boolean z) {
        this.mAnimStatus = AnimaStatus.Stop;
        if (z) {
            reset();
        }
    }

    public final void switchAnimIndex(int i) {
        this.mAnimStatus = AnimaStatus.Play;
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            AnimatorLine animatorLine = arrayList.get(i2);
            i2++;
            Animator.this.getCoeExpressions().switchAnimIndex(i);
        }
        pause();
    }

    public void updateExpr() {
        ArrayList<AnimatorLine> arrayList = this.mAnimLines;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            AnimatorLine animatorLine = arrayList.get(i);
            i++;
            animatorLine.updateExpr();
        }
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void stop() {
        this.mAnimStatus = AnimaStatus.Stop;
        reset();
    }

    @Override // com.oplus.vfxsdk.common.api.IPlayer
    public void play() {
        AnimaStatus animaStatus = this.mAnimStatus;
        AnimaStatus animaStatus2 = AnimaStatus.Play;
        if (animaStatus != animaStatus2) {
            Choreographer.getInstance().postFrameCallback(this.frameCb);
            this.mAnimStatusChanged = true;
        }
        this.mAnimStatus = animaStatus2;
    }

    public /* synthetic */ Animator(AnimatorValue animatorValue, COEExpressions cOEExpressions, ILinkProxy iLinkProxy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(animatorValue, cOEExpressions, (i & 4) != 0 ? null : iLinkProxy);
    }
}
