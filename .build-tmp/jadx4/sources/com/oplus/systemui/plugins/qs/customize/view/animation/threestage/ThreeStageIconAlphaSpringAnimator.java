package com.oplus.systemui.plugins.qs.customize.view.animation.threestage;

import android.content.res.ColorStateList;
import android.util.Log;
import android.widget.ImageView;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.core.animation.ArgbEvaluator;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.airbnb.lottie.LottieAnimationView;
import com.android.keyguard.OplusLockIconView$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageIconAlphaSpringAnimator {
    private static final String TAG = "ThreeStageIconAlphaSpringAnimator";
    public int alphaColor;
    private final COUISpringAnimation alphaSpringAnim;
    private final COUISpringAnimation colorSpringAnim;
    public float currentColorProgress;
    private final ImageView iconView;
    public boolean isSelected;
    private final LottieAnimationView lottieView;
    public int normalColor;
    private final Function2 onAnimationEnd;
    public int selectedColor;
    public static final Companion Companion = new Companion();
    private static final Lazy springConfig$delegate = LazyKt__LazyJVMKt.lazy(new ThreeStageIconAlphaSpringAnimator$$ExternalSyntheticLambda0());
    public float currentAlphaProgress = 0.54f;
    public float curActiveAlpha = 0.9f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringConfig() {
            return (Spring) ThreeStageIconAlphaSpringAnimator.springConfig$delegate.getValue();
        }
    }

    public ThreeStageIconAlphaSpringAnimator(ImageView imageView, LottieAnimationView lottieAnimationView, int i, int i2, Function2 function2) {
        this.iconView = imageView;
        this.lottieView = lottieAnimationView;
        this.normalColor = i;
        this.selectedColor = i2;
        this.onAnimationEnd = function2;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        Companion companion = Companion;
        cOUISpringForce.setBounce(companion.getSpringConfig().bounce());
        cOUISpringForce.setResponse(companion.getSpringConfig().response());
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
        ThreeStageIconAlphaSpringAnimator$1$2 threeStageIconAlphaSpringAnimator$1$2 = new ThreeStageIconAlphaSpringAnimator$1$2(0);
        threeStageIconAlphaSpringAnimator$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(threeStageIconAlphaSpringAnimator$1$2);
        ThreeStageIconAlphaSpringAnimator$1$3 threeStageIconAlphaSpringAnimator$1$3 = new ThreeStageIconAlphaSpringAnimator$1$3();
        threeStageIconAlphaSpringAnimator$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(threeStageIconAlphaSpringAnimator$1$3);
        this.colorSpringAnim = cOUISpringAnimation;
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce2 = new COUISpringForce();
        cOUISpringForce2.setBounce(companion.getSpringConfig().bounce());
        cOUISpringForce2.setResponse(companion.getSpringConfig().response());
        cOUISpringAnimation2.setSpring(cOUISpringForce2);
        cOUISpringAnimation2.setMinimumVisibleChange(0.00390625f);
        ThreeStageIconAlphaSpringAnimator$1$2 threeStageIconAlphaSpringAnimator$1$4 = new ThreeStageIconAlphaSpringAnimator$1$2(1);
        threeStageIconAlphaSpringAnimator$1$4.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(threeStageIconAlphaSpringAnimator$1$4);
        this.alphaSpringAnim = cOUISpringAnimation2;
        lottieAnimationView.setAlpha(this.currentAlphaProgress);
    }

    public final void setIconSelected(int i, int i2, boolean z) {
        this.normalColor = i2;
        this.selectedColor = i;
        this.isSelected = z;
        float f = z ? 1.0f : 0.0f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z2 = this.isSelected;
            int i3 = this.alphaColor;
            String hexString = Integer.toHexString(this.normalColor);
            String hexString2 = Integer.toHexString(this.selectedColor);
            ImageView imageView = this.iconView;
            StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("setIconSelected: isSelected: ", i3, " alphaColor: ", " normalColor: ", z2);
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, hexString, " selectedColor: ", hexString2, " view: ");
            sbM.append(imageView);
            Log.d("SystemUi--QuickSettings", "ThreeStageIconAlphaSpringAnimator-->".concat(sbM.toString()));
        }
        this.colorSpringAnim.animateToFinalPosition(f);
    }

    public final void setLottiViewSelected(boolean z) {
        float f = ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isTileColorfulEnable().getValue()).booleanValue() ? 1.0f : 0.9f;
        this.curActiveAlpha = f;
        if (!z) {
            f = 0.54f;
        }
        this.alphaSpringAnim.animateToFinalPosition(f);
    }

    public final void updateColor() {
        int iIntValue = ArgbEvaluator.getInstance().evaluate(this.currentColorProgress, Integer.valueOf(this.normalColor), Integer.valueOf(this.selectedColor)).intValue();
        this.alphaColor = iIntValue;
        this.iconView.setImageTintList(ColorStateList.valueOf(iIntValue));
    }
}
