package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.customize.view.animation.threestage.ThreeStageIconAlphaSpringAnimator;
import com.oplus.systemui.qs.base.tile.QSLottieAnimationView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.OnePlusQsColorUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLottieView extends QSLottieAnimationView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSThreeStageIconView";
    public boolean animating;
    private String curLottieFileName;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSThreeStageLottieView(Context context) {
        super(context, null, 6, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean playLottieAnimation(ImageView imageView, String str, ThreeStageIconAlphaSpringAnimator threeStageIconAlphaSpringAnimator, int i, int i2) {
        QSLottieAnimationView.ColorfulConfig colorfulConfig;
        removeAllAnimatorListeners();
        OplusQSThreeStageLottieView$initLottieAnimListener$1 oplusQSThreeStageLottieView$initLottieAnimListener$1 = new OplusQSThreeStageLottieView$initLottieAnimListener$1();
        oplusQSThreeStageLottieView$initLottieAnimListener$1.this$0 = this;
        oplusQSThreeStageLottieView$initLottieAnimListener$1.$iconView = imageView;
        oplusQSThreeStageLottieView$initLottieAnimListener$1.$alphaAnimator = threeStageIconAlphaSpringAnimator;
        oplusQSThreeStageLottieView$initLottieAnimListener$1.$activeColor = i;
        oplusQSThreeStageLottieView$initLottieAnimListener$1.$defaultColor = i2;
        VarHandle.storeStoreFence();
        addAnimatorListener(oplusQSThreeStageLottieView$initLottieAnimListener$1);
        if (isAnimating()) {
            boolean zAreEqual = Intrinsics.areEqual(this.curLottieFileName, str);
            if ((!zAreEqual && getSpeed() > 0.0f) || (zAreEqual && getSpeed() < 0.0f)) {
                if (ScrimUtil.isLowGaussianLevel(((ImageView) this).mContext)) {
                    cancelAnimation();
                    return true;
                }
                reverseAnimationSpeed();
            }
            return true;
        }
        if (((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isTileColorfulEnable().getValue()).booleanValue()) {
            int i3 = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_RED_COLOR;
            colorfulConfig = new QSLottieAnimationView.ColorfulConfig(i3, i3);
        } else {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp() && OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme() && !ScrimUtil.isWallpaperBlurDisable()) {
                int i4 = QsColorUtil.QS_ICON_ACTIVE_DARK_LOW_COLOR;
                colorfulConfig = new QSLottieAnimationView.ColorfulConfig(i4, i4);
            } else {
                int i5 = QsColorUtil.QS_ICON_VIEW_ACTIVE_DARK_COLOR;
                colorfulConfig = new QSLottieAnimationView.ColorfulConfig(i5, i5);
            }
        }
        setTintColorfulConfig(str, colorfulConfig);
        this.animating = true;
        if (!Intrinsics.areEqual(this.curLottieFileName, str)) {
            OplusQSThreeStageLottieView$updateLottieAnimation$1 oplusQSThreeStageLottieView$updateLottieAnimation$1 = new OplusQSThreeStageLottieView$updateLottieAnimation$1();
            oplusQSThreeStageLottieView$updateLottieAnimation$1.this$0 = this;
            oplusQSThreeStageLottieView$updateLottieAnimation$1.$assetName = str;
            oplusQSThreeStageLottieView$updateLottieAnimation$1.$this_updateLottieAnimation = this;
            VarHandle.storeStoreFence();
            setCallbackListener(oplusQSThreeStageLottieView$updateLottieAnimation$1);
            setAnimation(str);
        } else if (this.animating) {
            setVisibility(0);
        }
        setSpeed(1.0f);
        setTag(str);
        playAnimation();
        return true;
    }

    public OplusQSThreeStageLottieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 4, 0);
    }

    public OplusQSThreeStageLottieView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
