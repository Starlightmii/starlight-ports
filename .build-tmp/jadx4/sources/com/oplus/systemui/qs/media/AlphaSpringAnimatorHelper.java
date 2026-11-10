package com.oplus.systemui.qs.media;

import android.widget.ImageButton;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AlphaSpringAnimatorHelper {
    public static final Companion Companion = new Companion();
    private final COUISpringAnimation alphaSpringAnim;
    public float currentAlphaProgress = 1.0f;
    private final ArrayList<ImageButton> lottieView;
    private final Function0 onAnimationEnd;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AlphaSpringAnimatorHelper(ArrayList<ImageButton> arrayList, float f, float f2, Function0 function0) {
        this.lottieView = arrayList;
        this.onAnimationEnd = function0;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(f);
        cOUISpringForce.setResponse(f2);
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
        AlphaSpringAnimatorHelper$1$2 alphaSpringAnimatorHelper$1$2 = new AlphaSpringAnimatorHelper$1$2();
        alphaSpringAnimatorHelper$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(alphaSpringAnimatorHelper$1$2);
        AlphaSpringAnimatorHelper$1$3 alphaSpringAnimatorHelper$1$3 = new AlphaSpringAnimatorHelper$1$3();
        alphaSpringAnimatorHelper$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(alphaSpringAnimatorHelper$1$3);
        this.alphaSpringAnim = cOUISpringAnimation;
    }

    public static final void access$updateLottieViewAlpha(AlphaSpringAnimatorHelper alphaSpringAnimatorHelper) {
        ArrayList<ImageButton> arrayList = alphaSpringAnimatorHelper.lottieView;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            ImageButton imageButton = arrayList.get(i);
            i++;
            imageButton.setAlpha(alphaSpringAnimatorHelper.currentAlphaProgress);
        }
    }

    public final void animateTo(float f) {
        this.alphaSpringAnim.animateToFinalPosition(f);
    }

    public final void cancelAnimation() {
        this.alphaSpringAnim.cancel();
    }

    public final void setStartValue(float f) {
        this.alphaSpringAnim.setStartValue(f);
    }
}
