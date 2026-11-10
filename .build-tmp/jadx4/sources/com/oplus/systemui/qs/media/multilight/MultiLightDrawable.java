package com.oplus.systemui.qs.media.multilight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RuntimeShader;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.util.animation.ScreenOffAnimationGuardKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MultiLightDrawable extends Drawable implements BaseShaderInterface {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MultiLightDrawable";
    private CircleAnimConfig circleAnimConfig;
    private CircleAnimState circleAnimState;
    public final ValueAnimator circleAnimator;
    private PositionEnum circlePos;
    public float circlePosX;
    public float circlePosY;
    private final Context context;
    private Pair<Float, Float> curSize;
    private Pair<? extends PositionEnum, ShadowParams> innerShadowPos;
    private String name;
    private Pair<? extends PositionEnum, OutlineParams> outlinePos;
    private final Paint paint;
    private final MultiLightShaderParams params;
    public int repeatCount;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CircleAnimConfig {
        public static final Companion Companion = new Companion();
        public final int color;
        private final PositionEnum pos;
        public final float posX;
        public final float posY;
        public final int repeat;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final CircleAnimConfig from(MultiLightModel multiLightModel) {
                return new CircleAnimConfig(multiLightModel.getRepeat(), multiLightModel.getColor(), multiLightModel.getPos(), multiLightModel.getPosX(), multiLightModel.getPosY());
            }
        }

        public CircleAnimConfig(int i, int i2, PositionEnum positionEnum, float f, float f2) {
            this.repeat = i;
            this.color = i2;
            this.pos = positionEnum;
            this.posX = f;
            this.posY = f2;
        }

        public final PositionEnum component3() {
            return this.pos;
        }

        public final CircleAnimConfig copy(int i, int i2, PositionEnum positionEnum, float f, float f2) {
            return new CircleAnimConfig(i, i2, positionEnum, f, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CircleAnimConfig)) {
                return false;
            }
            CircleAnimConfig circleAnimConfig = (CircleAnimConfig) obj;
            return this.repeat == circleAnimConfig.repeat && this.color == circleAnimConfig.color && this.pos == circleAnimConfig.pos && Float.compare(this.posX, circleAnimConfig.posX) == 0 && Float.compare(this.posY, circleAnimConfig.posY) == 0;
        }

        public final int getColor() {
            return this.color;
        }

        public final PositionEnum getPos() {
            return this.pos;
        }

        public final float getPosX() {
            return this.posX;
        }

        public final float getPosY() {
            return this.posY;
        }

        public final int getRepeat() {
            return this.repeat;
        }

        public final int hashCode() {
            return Float.hashCode(this.posY) + ChangeSize$.ExternalSyntheticOutline0.m(this.posX, (this.pos.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.color, Integer.hashCode(this.repeat) * 31, 31)) * 31, 31);
        }

        public String toString() {
            PositionEnum positionEnum = this.pos;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("CircleAnimConfig(repeat=", this.repeat, this.color, ", color=", ", pos=");
            sbM.append(positionEnum);
            sbM.append(", posX=");
            sbM.append(this.posX);
            sbM.append(", posY=");
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.posY, ")", sbM);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class CircleAnimState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ CircleAnimState[] $VALUES;
        public static final CircleAnimState CANCELLING;
        public static final CircleAnimState FINISHED;
        public static final CircleAnimState IDLE;
        public static final CircleAnimState PAUSED;
        public static final CircleAnimState RUNNING;

        static {
            CircleAnimState circleAnimState = new CircleAnimState("IDLE", 0);
            IDLE = circleAnimState;
            CircleAnimState circleAnimState2 = new CircleAnimState("RUNNING", 1);
            RUNNING = circleAnimState2;
            CircleAnimState circleAnimState3 = new CircleAnimState("PAUSED", 2);
            PAUSED = circleAnimState3;
            CircleAnimState circleAnimState4 = new CircleAnimState("FINISHED", 3);
            FINISHED = circleAnimState4;
            CircleAnimState circleAnimState5 = new CircleAnimState("CANCELLING", 4);
            CANCELLING = circleAnimState5;
            CircleAnimState[] circleAnimStateArr = {circleAnimState, circleAnimState2, circleAnimState3, circleAnimState4, circleAnimState5};
            $VALUES = circleAnimStateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(circleAnimStateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CircleAnimState valueOf(String str) {
            return (CircleAnimState) Enum.valueOf(CircleAnimState.class, str);
        }

        public static CircleAnimState[] values() {
            return (CircleAnimState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PositionEnum.values().length];
            try {
                PositionEnum.Companion companion = PositionEnum.Companion;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                PositionEnum.Companion companion2 = PositionEnum.Companion;
                iArr[3] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                PositionEnum.Companion companion3 = PositionEnum.Companion;
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                PositionEnum.Companion companion4 = PositionEnum.Companion;
                iArr[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                PositionEnum.Companion companion5 = PositionEnum.Companion;
                iArr[5] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$setInnerShadowLightParam$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3 {
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((MultiLightShaderParams) ((CallableReference) this).receiver).setFadeOutColor(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$setInnerShadowLightParam$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function3 {
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((MultiLightShaderParams) ((CallableReference) this).receiver).setFadeInColor(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$setOutlineLightParam$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class C02571 extends FunctionReferenceImpl implements Function3 {
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((MultiLightShaderParams) ((CallableReference) this).receiver).setRectLineColorStart(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$setOutlineLightParam$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class C02582 extends FunctionReferenceImpl implements Function3 {
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((MultiLightShaderParams) ((CallableReference) this).receiver).setRectLineColorEnd(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$setSpreadLightParam$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class C02591 extends FunctionReferenceImpl implements Function3 {
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((MultiLightShaderParams) ((CallableReference) this).receiver).setCircleColor(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue());
            return Unit.INSTANCE;
        }
    }

    public MultiLightDrawable(Context context, String str) {
        this.context = context;
        this.name = str;
        MultiLightShaderParams multiLightShaderParams = new MultiLightShaderParams();
        this.params = multiLightShaderParams;
        Float fValueOf = Float.valueOf(0.0f);
        this.curSize = new Pair<>(fValueOf, fValueOf);
        this.repeatCount = -1;
        this.circleAnimState = CircleAnimState.IDLE;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat.setDuration(1000L);
        ScreenOffAnimationGuardKt.enableScreenOffAnimationGuard(valueAnimatorOfFloat, context);
        MultiLightDrawable$circleAnimator$1$1 multiLightDrawable$circleAnimator$1$1 = new MultiLightDrawable$circleAnimator$1$1();
        multiLightDrawable$circleAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(multiLightDrawable$circleAnimator$1$1);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.media.multilight.MultiLightDrawable$circleAnimator$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                    Log.d("SystemUi--Media", "MultiLightDrawable-->circleAnimator#cancel");
                }
                this.this$0.circleAnimState = MultiLightDrawable.CircleAnimState.CANCELLING;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                    Log.d("SystemUi--Media", "MultiLightDrawable-->circleAnimator#end");
                }
                MultiLightDrawable.CircleAnimState circleAnimState = this.this$0.circleAnimState;
                MultiLightDrawable.CircleAnimState circleAnimState2 = MultiLightDrawable.CircleAnimState.CANCELLING;
                MultiLightDrawable multiLightDrawable = this.this$0;
                if (circleAnimState == circleAnimState2) {
                    multiLightDrawable.circleAnimState = MultiLightDrawable.CircleAnimState.IDLE;
                } else {
                    MultiLightDrawable.access$transitionCircleAnimationToFinished(multiLightDrawable);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
            public final void onAnimationResume(Animator animator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                    Log.d("SystemUi--Media", "MultiLightDrawable-->circleAnimator#resume");
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                    Log.d("SystemUi--Media", "MultiLightDrawable-->circleAnimator#start");
                }
            }
        });
        this.circleAnimator = valueAnimatorOfFloat;
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setShader(multiLightShaderParams.getRuntimeShader());
    }

    public static final void access$transitionCircleAnimationToFinished(MultiLightDrawable multiLightDrawable) {
        multiLightDrawable.circleAnimState = CircleAnimState.FINISHED;
        multiLightDrawable.invalidateSelf();
    }

    public static void applyAndroidColor(int i, Function3 function3) {
        function3.invoke(Float.valueOf(Color.red(i) / 255.0f), Float.valueOf(Color.green(i) / 255.0f), Float.valueOf(Color.blue(i) / 255.0f));
    }

    public final void applyLight(List<MultiLightModel> list) {
        boolean z;
        LogUtil.i$default(LogUtil.TAG_MEDIA, TAG, "[" + this.name + "] applyLight with " + list, null, 8);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if ((((MultiLightModel) obj).getColor() & 16777215) != 0) {
                arrayList.add(obj);
            }
        }
        int i = 0;
        if (arrayList.isEmpty()) {
            z = false;
            break;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z = false;
                break;
            }
            Object obj2 = arrayList.get(i2);
            i2++;
            if (((MultiLightModel) obj2).getType() == 3) {
                z = true;
                break;
            }
        }
        setOutlineLightParam(null);
        setInnerShadowLightParam(null);
        if (!z) {
            setSpreadLightParam(null);
        }
        int size2 = arrayList.size();
        while (i < size2) {
            Object obj3 = arrayList.get(i);
            i++;
            MultiLightModel multiLightModel = (MultiLightModel) obj3;
            int type = multiLightModel.getType();
            if (type == 1) {
                setOutlineLightParam(multiLightModel);
            } else if (type == 2) {
                setInnerShadowLightParam(multiLightModel);
            } else if (type != 3) {
                LogUtil.e$default(8, LogUtil.TAG_MEDIA, TAG, "applyParams error: unsupported type " + multiLightModel.getType());
            } else {
                setSpreadLightParam(multiLightModel);
            }
        }
    }

    public final void attachToView(View view) {
        view.setBackground(this);
        view.setLayerType(2, null);
        float width = view.getWidth();
        float height = view.getHeight();
        Float fValueOf = Float.valueOf(0.0f);
        setCommonSizeParams(width, height, fValueOf, fValueOf);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        CircleAnimState circleAnimState;
        MultiLightShaderParams multiLightShaderParams = this.params;
        boolean z = true;
        if ((multiLightShaderParams.uCircle == 0.0f) || ((circleAnimState = this.circleAnimState) != CircleAnimState.RUNNING && circleAnimState != CircleAnimState.PAUSED)) {
            z = false;
        }
        if (multiLightShaderParams.uShadow == 0.0f && multiLightShaderParams.uEdge == 0.0f && !z) {
            return;
        }
        if (!canvas.isHardwareAccelerated()) {
            LogUtil.e$default(8, LogUtil.TAG_MEDIA, TAG, "called on non-hardware accelerated canvas.");
        } else {
            this.params.flushIfDirty(false, z);
            canvas.drawRect(getBounds(), this.paint);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0033  */
    public final void dump(String str) {
        boolean z;
        Configuration configuration;
        this.params.dumpParams(str);
        String str2 = this.name;
        Pair<Float, Float> pair = this.curSize;
        Pair<? extends PositionEnum, OutlineParams> pair2 = this.outlinePos;
        Pair<? extends PositionEnum, ShadowParams> pair3 = this.innerShadowPos;
        PositionEnum positionEnum = this.circlePos;
        float f = this.circlePosX;
        float f2 = this.circlePosY;
        int i = this.repeatCount;
        CircleAnimState circleAnimState = this.circleAnimState;
        boolean zIsRunning = this.circleAnimator.isRunning();
        Resources resources = this.context.getResources();
        if (resources != null && (configuration = resources.getConfiguration()) != null) {
            z = configuration.getLayoutDirection() == 1;
        }
        float f3 = this.context.getResources().getDisplayMetrics().density;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] [", str2, "]] curSize: ");
        sbM.append(pair);
        sbM.append(", outlinePos: ");
        sbM.append(pair2);
        sbM.append(", innerShadowPos: ");
        sbM.append(pair3);
        sbM.append(", circlePos: ");
        sbM.append(positionEnum);
        sbM.append(" (");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, "/", f2, "), repeatCount: ");
        sbM.append(i);
        sbM.append(", circleAnimState: ");
        sbM.append(circleAnimState);
        sbM.append(", isRunning: ");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isRtl: ", ", density: ", sbM, zIsRunning, z);
        sbM.append(f3);
        LogUtil.i$default(LogUtil.TAG_MEDIA, TAG, sbM.toString(), null, 8);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) ((Number) this.curSize.getSecond()).floatValue();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) ((Number) this.curSize.getFirst()).floatValue();
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public int getOpacity() {
        return -3;
    }

    @Override // com.oplus.systemui.qs.media.multilight.BaseShaderInterface
    public RuntimeShader getRuntimeShader() {
        return this.params.getRuntimeShader();
    }

    public final void mapCirclePos() {
        Pair pair;
        Float fValueOf = Float.valueOf(0.0f);
        PositionEnum positionEnum = this.circlePos;
        if (positionEnum == null) {
            return;
        }
        if (positionEnum == PositionEnum.DEFAULT) {
            positionEnum = PositionEnum.LEFT;
        }
        PositionEnum positionEnumMirrorLeftRightForLayout = mirrorLeftRightForLayout(positionEnum);
        Pair<Float, Float> pair2 = this.curSize;
        float fFloatValue = ((Number) pair2.component1()).floatValue();
        float fFloatValue2 = ((Number) pair2.component2()).floatValue();
        int i = positionEnumMirrorLeftRightForLayout == null ? -1 : WhenMappings.$EnumSwitchMapping$0[positionEnumMirrorLeftRightForLayout.ordinal()];
        if (i == 1) {
            pair = new Pair(Float.valueOf((-fFloatValue) / 2.0f), fValueOf);
        } else if (i == 2) {
            pair = new Pair(Float.valueOf(fFloatValue / 2.0f), fValueOf);
        } else if (i == 3) {
            pair = new Pair(fValueOf, Float.valueOf((-fFloatValue2) / 2.0f));
        } else if (i != 4) {
            pair = i != 5 ? new Pair(fValueOf, fValueOf) : new Pair(Float.valueOf(this.circlePosX), Float.valueOf(this.circlePosY));
        } else {
            pair = new Pair(fValueOf, Float.valueOf(fFloatValue2 / 2.0f));
        }
        this.params.setCirclePos(Float.valueOf(((Number) pair.component1()).floatValue()), Float.valueOf(((Number) pair.component2()).floatValue()));
    }

    public final void mapInnerShadowPos() {
        Pair<? extends PositionEnum, ShadowParams> pair = this.innerShadowPos;
        PositionEnum positionEnumMirrorLeftRightForLayout = mirrorLeftRightForLayout(pair != null ? (PositionEnum) pair.getFirst() : null);
        Pair<? extends PositionEnum, ShadowParams> pair2 = this.innerShadowPos;
        if (pair2 != null) {
            ShadowParams shadowParamsFromPosition = pair2.getSecond() != null ? (ShadowParams) pair2.getSecond() : ShadowParams.Companion.fromPosition(positionEnumMirrorLeftRightForLayout);
            if (shadowParamsFromPosition == null) {
                return;
            }
            shadowParamsFromPosition.applyTo(this.params);
            Pair<Float, Float> pair3 = this.curSize;
            float fFloatValue = ((Number) pair3.component1()).floatValue();
            float fFloatValue2 = ((Number) pair3.component2()).floatValue();
            this.params.setFadeIn((shadowParamsFromPosition.getFadeIn() * ((fFloatValue + fFloatValue2) * 2.0f)) / ((shadowParamsFromPosition.getStandardHeight() + shadowParamsFromPosition.getStandardWidth()) * 2.0f));
            int i = positionEnumMirrorLeftRightForLayout == null ? -1 : WhenMappings.$EnumSwitchMapping$0[positionEnumMirrorLeftRightForLayout.ordinal()];
            if (i == 1 || i == 2) {
                this.params.setFadeOffset(Float.valueOf((shadowParamsFromPosition.getFadeOffsetX() * fFloatValue) / shadowParamsFromPosition.getStandardWidth()), Float.valueOf(-shadowParamsFromPosition.getFadeOffsetY()));
            } else {
                this.params.setFadeOffset(Float.valueOf(shadowParamsFromPosition.getFadeOffsetX()), Float.valueOf(-((shadowParamsFromPosition.getFadeOffsetY() * fFloatValue2) / shadowParamsFromPosition.getStandardHeight())));
            }
        }
    }

    public final void mapOutlinePos() {
        Pair<? extends PositionEnum, OutlineParams> pair = this.outlinePos;
        PositionEnum positionEnumMirrorLeftRightForLayout = mirrorLeftRightForLayout(pair != null ? (PositionEnum) pair.getFirst() : null);
        Pair<? extends PositionEnum, OutlineParams> pair2 = this.outlinePos;
        if (pair2 != null) {
            OutlineParams outlineParamsFromPosition = pair2.getSecond() != null ? (OutlineParams) pair2.getSecond() : OutlineParams.Companion.fromPosition(positionEnumMirrorLeftRightForLayout);
            if (outlineParamsFromPosition == null) {
                return;
            }
            outlineParamsFromPosition.applyTo(this.params);
            Pair<Float, Float> pair3 = this.curSize;
            float fFloatValue = ((Number) pair3.component1()).floatValue();
            float fFloatValue2 = ((Number) pair3.component2()).floatValue();
            if (fFloatValue <= 0.0f || fFloatValue2 <= 0.0f || positionEnumMirrorLeftRightForLayout == PositionEnum.LEFT || positionEnumMirrorLeftRightForLayout == PositionEnum.RIGHT) {
                return;
            }
            float f = (fFloatValue2 + fFloatValue) * 2.0f;
            float fDp2px = ((DeviceUtil.dp2px(this.context, 24.0f) * 2.0f) + fFloatValue) / f;
            float standardHeight = (outlineParamsFromPosition.getStandardHeight() + outlineParamsFromPosition.getStandardWidth()) * 2.0f;
            float rectLineFadeX = (((outlineParamsFromPosition.getRectLineFadeX() * standardHeight) / outlineParamsFromPosition.getStandardWidth()) * fFloatValue) / f;
            float rectLineFadeY = (((outlineParamsFromPosition.getRectLineFadeY() * standardHeight) / outlineParamsFromPosition.getStandardWidth()) * fFloatValue) / f;
            float rectLineFadeZ = (((outlineParamsFromPosition.getRectLineFadeZ() * standardHeight) / outlineParamsFromPosition.getStandardWidth()) * fFloatValue) / f;
            float rectLineFadeW = (((outlineParamsFromPosition.getRectLineFadeW() * standardHeight) / outlineParamsFromPosition.getStandardWidth()) * fFloatValue) / f;
            if (positionEnumMirrorLeftRightForLayout == null || positionEnumMirrorLeftRightForLayout == PositionEnum.SPECIFIED || positionEnumMirrorLeftRightForLayout == PositionEnum.DEFAULT) {
                this.params.setRectLineFade(Float.valueOf(rectLineFadeX), Float.valueOf(rectLineFadeY), Float.valueOf(rectLineFadeZ), Float.valueOf(rectLineFadeW));
            } else {
                this.params.setRectLineFade(Float.valueOf(fDp2px), null, null, null);
            }
        }
    }

    public final PositionEnum mirrorLeftRightForLayout(PositionEnum positionEnum) {
        Configuration configuration;
        if (positionEnum == null) {
            return null;
        }
        Resources resources = this.context.getResources();
        if (resources != null && (configuration = resources.getConfiguration()) != null && configuration.getLayoutDirection() == 1) {
            int iOrdinal = positionEnum.ordinal();
            if (iOrdinal == 1) {
                return PositionEnum.RIGHT;
            }
            if (iOrdinal == 3) {
                return PositionEnum.LEFT;
            }
        }
        return positionEnum;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        setCommonSizeParams(rect.width(), rect.height(), null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        Pair<? extends PositionEnum, OutlineParams> pair = this.outlinePos;
        boolean z = (pair == null && this.innerShadowPos == null && this.circlePos == null) ? false : true;
        if (z) {
            if (pair != null) {
                mapOutlinePos();
            }
            if (this.innerShadowPos != null) {
                mapInnerShadowPos();
            }
            if (this.circlePos != null) {
                mapCirclePos();
            }
            invalidateSelf();
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.paint.setAlpha(i);
        invalidateSelf();
    }

    public final void setCircleParams(CircleParams circleParams) {
        circleParams.applyTo(this.params);
        this.circlePos = null;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setCommonSizeParams(float f, float f2, Float f3, Float f4) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("setCommonSizeParams: ", f, ", ", f2, ", ");
            sbM.append(f3);
            sbM.append(", ");
            sbM.append(f4);
            Log.d("SystemUi--Media", "MultiLightDrawable-->".concat(sbM.toString()));
        }
        this.curSize = new Pair<>(Float.valueOf(f), Float.valueOf(f2));
        this.params.setResolution(f, f2);
        this.params.setPosition(f3, f4);
        this.params.setSize(Float.valueOf(f), Float.valueOf(f2));
        mapInnerShadowPos();
        mapCirclePos();
        mapOutlinePos();
        invalidateSelf();
    }

    public final void setCornerParams(Integer num, Float f, Float f2) {
        if (num != null) {
            this.params.setCornerType(num.intValue());
        }
        if (f != null) {
            this.params.setCornerRadii(f, f, f, f);
        }
        if (f2 != null) {
            this.params.setWeight(f2.floatValue());
        }
        invalidateSelf();
    }

    public final void setInnerShadowLightParam(MultiLightModel multiLightModel) {
        this.params.setShadowEnabled(multiLightModel != null);
        this.innerShadowPos = new Pair<>(multiLightModel != null ? multiLightModel.getPos() : null, (Object) null);
        if (multiLightModel != null) {
            applyAndroidColor(multiLightModel.getColor(), new AnonymousClass1(3, this.params, MultiLightShaderParams.class, "setFadeOutColor", "setFadeOutColor(FFF)V", 0));
            Integer subColor = multiLightModel.getSubColor();
            applyAndroidColor(subColor != null ? subColor.intValue() : -1, new AnonymousClass2(3, this.params, MultiLightShaderParams.class, "setFadeInColor", "setFadeInColor(FFF)V", 0));
            mapInnerShadowPos();
        }
        invalidateSelf();
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setOutlineLightParam(MultiLightModel multiLightModel) {
        this.outlinePos = new Pair<>(multiLightModel != null ? multiLightModel.getPos() : null, (Object) null);
        this.params.setEdgeEnabled(multiLightModel != null);
        if (multiLightModel != null) {
            applyAndroidColor(multiLightModel.getColor(), new C02571(3, this.params, MultiLightShaderParams.class, "setRectLineColorStart", "setRectLineColorStart(FFF)V", 0));
            Integer subColor = multiLightModel.getSubColor();
            applyAndroidColor(subColor != null ? subColor.intValue() : -1, new C02582(3, this.params, MultiLightShaderParams.class, "setRectLineColorEnd", "setRectLineColorEnd(FFF)V", 0));
            mapOutlinePos();
        }
        invalidateSelf();
    }

    public final void setOutlineParams(OutlineParams outlineParams) {
        outlineParams.applyTo(this.params);
        this.outlinePos = new Pair<>((Object) null, outlineParams);
        mapOutlinePos();
        invalidateSelf();
    }

    public final void setShadowParams(ShadowParams shadowParams) {
        shadowParams.applyTo(this.params);
        this.innerShadowPos = new Pair<>((Object) null, shadowParams);
        mapInnerShadowPos();
        invalidateSelf();
    }

    public final void setSpreadLightParam(MultiLightModel multiLightModel) {
        boolean z = (multiLightModel == null || multiLightModel.getRepeat() == 0 || !(multiLightModel != null && multiLightModel.getStarted())) ? false : true;
        this.circlePos = z ? multiLightModel.getPos() : null;
        if (!z) {
            this.circleAnimConfig = null;
            this.circlePosX = 0.0f;
            this.circlePosY = 0.0f;
            int repeat = multiLightModel != null ? multiLightModel.getRepeat() : 0;
            this.repeatCount = repeat;
            this.params.setCircleRepeatCount(repeat);
            this.params.setCircleEnabled(false);
            stopAnimation();
            invalidateSelf();
            return;
        }
        if (multiLightModel != null) {
            this.circlePosX = multiLightModel.getPosX();
            this.circlePosY = multiLightModel.getPosY();
            CircleAnimConfig circleAnimConfigFrom = CircleAnimConfig.Companion.from(multiLightModel);
            boolean zAreEqual = Intrinsics.areEqual(this.circleAnimConfig, circleAnimConfigFrom);
            int repeat2 = multiLightModel.getRepeat();
            this.repeatCount = repeat2;
            this.params.setCircleRepeatCount(repeat2);
            if (zAreEqual && this.circleAnimState == CircleAnimState.FINISHED) {
                invalidateSelf();
                return;
            }
            this.params.setCircleEnabled(true);
            mapCirclePos();
            (this.repeatCount < 0 ? CircleParams.Companion.getINFINITE() : CircleParams.Companion.getLIMITED()).applyTo(this.params);
            applyAndroidColor(multiLightModel.getColor(), new C02591(3, this.params, MultiLightShaderParams.class, "setCircleColor", "setCircleColor(FFF)V", 0));
            if (!zAreEqual) {
                stopAnimation();
                this.circleAnimConfig = circleAnimConfigFrom;
                startAnimation(true);
            } else if (this.circleAnimState == CircleAnimState.IDLE) {
                startAnimation(false);
            }
        }
        invalidateSelf();
    }

    public final void startAnimation(boolean z) {
        CircleAnimState circleAnimState;
        CircleAnimState circleAnimState2;
        int i;
        CircleAnimConfig circleAnimConfig = this.circleAnimConfig;
        if (circleAnimConfig == null || (circleAnimState = this.circleAnimState) == (circleAnimState2 = CircleAnimState.FINISHED)) {
            return;
        }
        MultiLightShaderParams multiLightShaderParams = this.params;
        float f = multiLightShaderParams.uCircle;
        if ((f == 0.0f) || (i = this.repeatCount) == 0) {
            return;
        }
        CircleAnimState circleAnimState3 = CircleAnimState.PAUSED;
        if (circleAnimState != circleAnimState3) {
            CircleAnimState circleAnimState4 = CircleAnimState.RUNNING;
            if (circleAnimState == circleAnimState4 || this.circleAnimator.isRunning()) {
                return;
            }
            if (z) {
                this.params.resetTime();
            }
            this.circleAnimator.setRepeatCount(this.repeatCount < 0 ? -1 : 0);
            this.circleAnimator.setDuration(this.repeatCount < 0 ? 1000L : this.params.getCircleAnimationDurationMillis());
            this.circleAnimState = circleAnimState4;
            this.circleAnimator.start();
            invalidateSelf();
            return;
        }
        if (circleAnimConfig == null || circleAnimState == circleAnimState2) {
            return;
        }
        if ((f == 0.0f) || i == 0) {
            return;
        }
        if (circleAnimState != circleAnimState3) {
            startAnimation(false);
            return;
        }
        multiLightShaderParams.getClass();
        this.circleAnimState = CircleAnimState.RUNNING;
        this.circleAnimator.resume();
        invalidateSelf();
    }

    public final void stopAnimation() {
        if (!this.circleAnimator.isStarted()) {
            this.circleAnimState = CircleAnimState.IDLE;
            return;
        }
        CircleAnimState circleAnimState = CircleAnimState.CANCELLING;
        this.circleAnimState = circleAnimState;
        this.circleAnimator.cancel();
        if (this.circleAnimState == circleAnimState) {
            this.circleAnimState = CircleAnimState.IDLE;
        }
    }
}
