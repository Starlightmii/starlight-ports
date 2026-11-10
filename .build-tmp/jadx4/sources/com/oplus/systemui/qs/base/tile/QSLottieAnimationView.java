package com.oplus.systemui.qs.base.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class QSLottieAnimationView extends LottieAnimationView {
    public static final Companion Companion = new Companion();
    private static final String KEYPATH_ENDFILL = "endFill";
    private static final String KEYPATH_ENDSTROKE = "endStroke";
    private static final String KEYPATH_STARTFILL = "startFill";
    private static final String KEYPATH_STARTSTROKE = "startStroke";
    private static final String TAG = "QSLottieAnimationView";
    private String assetFileName;
    private LottieOnCompositionLoadedListener callbackListener;
    private ColorfulConfig colorfulConfig;
    private final QSLottieHalfInterpolator interpolator;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.tile.QSLottieAnimationView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements LottieOnCompositionLoadedListener {
        public /* synthetic */ QSLottieAnimationView this$0;

        public final void onCompositionLoaded(LottieComposition lottieComposition) {
            QSLottieAnimationView qSLottieAnimationView = this.this$0;
            LogUtil.i$default(LogUtil.TAG_QS, QSLottieAnimationView.TAG, "colorfulConfig ：" + qSLottieAnimationView.colorfulConfig, null, 8);
            ColorfulConfig colorfulConfig = qSLottieAnimationView.colorfulConfig;
            Integer numValueOf = colorfulConfig != null ? Integer.valueOf(colorfulConfig.getStartColor()) : null;
            ColorfulConfig colorfulConfig2 = qSLottieAnimationView.colorfulConfig;
            qSLottieAnimationView.tintLottieJsonColor(numValueOf, colorfulConfig2 != null ? Integer.valueOf(colorfulConfig2.getEndColor()) : null);
            LottieOnCompositionLoadedListener callbackListener = qSLottieAnimationView.getCallbackListener();
            if (callbackListener != null) {
                callbackListener.onCompositionLoaded(lottieComposition);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSLottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.interpolator = new QSLottieHalfInterpolator();
        this.assetFileName = "";
        removeAllLottieOnCompositionLoadedListener();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        addLottieOnCompositionLoadedListener(anonymousClass1);
    }

    public final LottieOnCompositionLoadedListener getCallbackListener() {
        return this.callbackListener;
    }

    public final QSLottieHalfInterpolator getInterpolator() {
        return this.interpolator;
    }

    public final void setCallbackListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        this.callbackListener = lottieOnCompositionLoadedListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setTintColorfulConfig(String str, ColorfulConfig colorfulConfig) {
        if (colorfulConfig == null) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "newConfig ：" + str + " " + colorfulConfig, null, 8);
        this.colorfulConfig = colorfulConfig;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSLottieAnimationView-->".concat("applyColorfulConfig-->colorfulConfig:" + this.colorfulConfig));
        }
        ColorfulConfig colorfulConfig2 = this.colorfulConfig;
        if (colorfulConfig2 != null) {
            tintLottieJsonColor(Integer.valueOf(colorfulConfig2.getStartColor()), Integer.valueOf(colorfulConfig2.getEndColor()));
            this.assetFileName = str;
            removeAllUpdateListeners();
            if (colorfulConfig2.getStartAlpha() == colorfulConfig2.getEndAlpha()) {
                setAlpha(colorfulConfig2.getStartAlpha());
                return;
            }
            QSLottieAnimationView$applyColorfulConfig$2$1 qSLottieAnimationView$applyColorfulConfig$2$1 = new QSLottieAnimationView$applyColorfulConfig$2$1();
            qSLottieAnimationView$applyColorfulConfig$2$1.this$0 = this;
            qSLottieAnimationView$applyColorfulConfig$2$1.$it = colorfulConfig2;
            VarHandle.storeStoreFence();
            addAnimatorUpdateListener(qSLottieAnimationView$applyColorfulConfig$2$1);
        }
    }

    public final void tintLottieJsonColor(Integer num, Integer num2) {
        if (num == null || num.intValue() == 0 || num2 == null || num2.intValue() == 0) {
            return;
        }
        for (KeyPath keyPath : resolveKeyPath(new KeyPath(new String[]{"**"}))) {
            if (StringsKt__StringsKt.contains(keyPath.keys.toString(), KEYPATH_STARTFILL, false)) {
                PointF pointF = LottieProperty.TRANSFORM_ANCHOR_POINT;
                addValueCallback(keyPath, 1, new LottieValueCallback(num));
            } else if (StringsKt__StringsKt.contains(keyPath.keys.toString(), KEYPATH_ENDFILL, false)) {
                PointF pointF2 = LottieProperty.TRANSFORM_ANCHOR_POINT;
                addValueCallback(keyPath, 1, new LottieValueCallback(num2));
            } else if (StringsKt__StringsKt.contains(keyPath.keys.toString(), KEYPATH_STARTSTROKE, false)) {
                PointF pointF3 = LottieProperty.TRANSFORM_ANCHOR_POINT;
                addValueCallback(keyPath, 2, new LottieValueCallback(num));
            } else if (StringsKt__StringsKt.contains(keyPath.keys.toString(), KEYPATH_ENDSTROKE, false)) {
                PointF pointF4 = LottieProperty.TRANSFORM_ANCHOR_POINT;
                addValueCallback(keyPath, 2, new LottieValueCallback(num2));
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ColorfulConfig {
        public static final Companion Companion = new Companion();
        public final float endAlpha;
        public final int endColor;
        public final float startAlpha;
        public final int startColor;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final ColorfulConfig makeColorfulConfigForQSTile(String str, ColorStateList colorStateList) {
                if (colorStateList == null) {
                    return null;
                }
                int colorForState = colorStateList.getColorForState(TileViewFlag.Active.getFlag(), colorStateList.getDefaultColor());
                int colorForState2 = colorStateList.getColorForState(TileViewFlag.Inactive.getFlag(), colorStateList.getDefaultColor());
                if (StringsKt__StringsKt.contains(str, QSConstant.ACTIVE_SUFFIX, false)) {
                    return new ColorfulConfig(Color.alpha(colorForState2) / 255.0f, Color.alpha(colorForState) / 255.0f, colorForState2, colorForState);
                }
                if (StringsKt__StringsKt.contains(str, QSConstant.INACTIVE_SUFFIX, false)) {
                    return new ColorfulConfig(Color.alpha(colorForState) / 255.0f, Color.alpha(colorForState2) / 255.0f, colorForState, colorForState2);
                }
                return null;
            }

            public final ColorfulConfig makeColorfulConfigForQSVolumeBlueToothIcon(Context context, int i, int i2, boolean z, boolean z2, boolean z3) {
                boolean z4 = (QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || !ScrimUtil.isLowGaussianLevel(context) || OplusDarkModeUtil.isNightMode(context)) ? false : true;
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("makeColorfulConfigForQSVolumeBlueToothIcon aboveThreshold:", " lastAboveThreshold:", " isMute:", z2, z3);
                sbM.append(z);
                LogUtil.e$default(8, LogUtil.TAG_QS, QSLottieAnimationView.TAG, sbM.toString());
                if (!QsColorfulConfigUtil.INSTANCE.getEnableColorfulConfig()) {
                    if (z4) {
                        return new ColorfulConfig(0.7f, 0.7f, i, i2);
                    }
                    if (z) {
                        if (z2) {
                            return new ColorfulConfig(0.7f, 0.3f, i2, i);
                        }
                        return z3 ? new ColorfulConfig(0.7f, 0.3f, i, i) : new ColorfulConfig(0.3f, 0.3f, i, i);
                    }
                    if (z2) {
                        return new ColorfulConfig(0.3f, 0.7f, i, i2);
                    }
                    return z3 ? new ColorfulConfig(0.7f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i);
                }
                if (z4) {
                    if (!z) {
                        if (z2) {
                            return new ColorfulConfig(0.7f, 1.0f, i, i2);
                        }
                        return z3 ? new ColorfulConfig(1.0f, 0.7f, i, i2) : new ColorfulConfig(0.7f, 0.7f, i, i);
                    }
                    if (!z2 && !z3) {
                        return new ColorfulConfig(0.7f, 0.7f, i, i);
                    }
                    return new ColorfulConfig(1.0f, 0.7f, i2, i);
                }
                if (!z) {
                    if (z2) {
                        return new ColorfulConfig(0.3f, 1.0f, i, i2);
                    }
                    return z3 ? new ColorfulConfig(1.0f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i);
                }
                if (!z2 && !z3) {
                    return new ColorfulConfig(0.3f, 0.3f, i, i);
                }
                return new ColorfulConfig(1.0f, 0.3f, i2, i);
            }

            public final ColorfulConfig makeColorfulConfigForQSVolumeIcon(Context context, int i, int i2, int i3, int i4, boolean z, boolean z2) {
                boolean z3 = (QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || !ScrimUtil.isLowGaussianLevel(context) || OplusDarkModeUtil.isNightMode(context)) ? false : true;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" lastAboveThreshold:", "QSLottieAnimationView-->", FloatList$.ExternalSyntheticOutline0.m("makeColorfulConfigForQSVolumeIcon lastLevel:", i3, i4, " targetLevel:", " aboveThreshold:"), z, z2));
                }
                if (!QsColorfulConfigUtil.INSTANCE.getEnableColorfulConfig()) {
                    if (z3) {
                        return new ColorfulConfig(0.7f, 0.7f, i, i2);
                    }
                    if (i3 == 0) {
                        return z ? new ColorfulConfig(0.3f, 0.7f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
                    }
                    if (i4 == 0) {
                        return z ? new ColorfulConfig(0.7f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
                    }
                    if (z) {
                        return z2 ? new ColorfulConfig(0.7f, 0.7f, i, i2) : new ColorfulConfig(0.3f, 0.7f, i, i2);
                    }
                    return z2 ? new ColorfulConfig(0.7f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
                }
                if (z3) {
                    if (i3 == 0) {
                        return z ? new ColorfulConfig(0.7f, 1.0f, i, i2) : new ColorfulConfig(0.7f, 0.7f, i, i2);
                    }
                    if (i4 == 0) {
                        return z ? new ColorfulConfig(1.0f, 0.7f, i, i2) : new ColorfulConfig(0.7f, 0.7f, i, i2);
                    }
                    if (z) {
                        return z2 ? new ColorfulConfig(1.0f, 1.0f, i, i2) : new ColorfulConfig(0.7f, 1.0f, i, i2);
                    }
                    return z2 ? new ColorfulConfig(1.0f, 0.7f, i, i2) : new ColorfulConfig(0.7f, 0.7f, i, i2);
                }
                if (i3 == 0) {
                    return z ? new ColorfulConfig(0.3f, 1.0f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
                }
                if (i4 == 0) {
                    return z ? new ColorfulConfig(1.0f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
                }
                if (z) {
                    return z2 ? new ColorfulConfig(1.0f, 1.0f, i, i2) : new ColorfulConfig(0.3f, 1.0f, i, i2);
                }
                return z2 ? new ColorfulConfig(1.0f, 0.3f, i, i2) : new ColorfulConfig(0.3f, 0.3f, i, i2);
            }
        }

        public ColorfulConfig(float f, float f2, int i, int i2) {
            this.startColor = i;
            this.endColor = i2;
            this.startAlpha = f;
            this.endAlpha = f2;
        }

        public final ColorfulConfig copy(int i, int i2, float f, float f2) {
            return new ColorfulConfig(f, f2, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ColorfulConfig)) {
                return false;
            }
            ColorfulConfig colorfulConfig = (ColorfulConfig) obj;
            return this.startColor == colorfulConfig.startColor && this.endColor == colorfulConfig.endColor && Float.compare(this.startAlpha, colorfulConfig.startAlpha) == 0 && Float.compare(this.endAlpha, colorfulConfig.endAlpha) == 0;
        }

        public final float getEndAlpha() {
            return this.endAlpha;
        }

        public final int getEndColor() {
            return this.endColor;
        }

        public final float getStartAlpha() {
            return this.startAlpha;
        }

        public final int getStartColor() {
            return this.startColor;
        }

        public final int hashCode() {
            return Float.hashCode(this.endAlpha) + ChangeSize$.ExternalSyntheticOutline0.m(this.startAlpha, MonoSpline$.ExternalSyntheticOutline0.m(this.endColor, Integer.hashCode(this.startColor) * 31, 31), 31);
        }

        public String toString() {
            return SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("ColorfulConfig(startColor=", this.startColor, this.endColor, ", endColor=", ", startAlpha="), this.startAlpha, ", endAlpha=", this.endAlpha, ")");
        }

        public /* synthetic */ ColorfulConfig(int i, int i2) {
            this(1.0f, 1.0f, i, i2);
        }
    }

    public QSLottieAnimationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 4, 0);
    }

    public /* synthetic */ QSLottieAnimationView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, (i & 2) != 0 ? null : attributeSet, 0);
    }

    public QSLottieAnimationView(Context context) {
        this(context, null, 6, 0);
    }
}
