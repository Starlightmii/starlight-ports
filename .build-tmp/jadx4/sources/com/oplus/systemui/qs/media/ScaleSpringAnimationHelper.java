package com.oplus.systemui.qs.media;

import android.widget.ImageButton;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScaleSpringAnimationHelper {
    public final float bounce;
    private final ArrayList<ImageButton> iconView;
    public final float response;
    private final Lazy springAnim$delegate;

    public static COUISpringAnimation $r8$lambda$cYevNTXBkrIzJ1Lsw9kw9UMELsI(ScaleSpringAnimationHelper scaleSpringAnimationHelper) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        if (scaleSpringAnimationHelper.iconView.size() > 0) {
            cOUISpringAnimation = new COUISpringAnimation(COUIDynamicAnimation.SCALE_X, scaleSpringAnimationHelper.iconView.get(0));
        }
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(scaleSpringAnimationHelper.bounce);
        cOUISpringForce.setResponse(scaleSpringAnimationHelper.response);
        cOUISpringAnimation.setSpring(cOUISpringForce);
        ScaleSpringAnimationHelper$springAnim$2$1$1 scaleSpringAnimationHelper$springAnim$2$1$1 = new ScaleSpringAnimationHelper$springAnim$2$1$1();
        scaleSpringAnimationHelper$springAnim$2$1$1.this$0 = scaleSpringAnimationHelper;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(scaleSpringAnimationHelper$springAnim$2$1$1);
        return cOUISpringAnimation;
    }

    public ScaleSpringAnimationHelper(ArrayList<ImageButton> arrayList, float f, float f2) {
        this.iconView = arrayList;
        this.bounce = f;
        this.response = f2;
        ScaleSpringAnimationHelper$$ExternalSyntheticLambda0 scaleSpringAnimationHelper$$ExternalSyntheticLambda0 = new ScaleSpringAnimationHelper$$ExternalSyntheticLambda0();
        scaleSpringAnimationHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnim$delegate = LazyKt__LazyJVMKt.lazy(scaleSpringAnimationHelper$$ExternalSyntheticLambda0);
    }

    private final COUISpringAnimation getSpringAnim() {
        return (COUISpringAnimation) this.springAnim$delegate.getValue();
    }

    public final void animateTo(float f) {
        getSpringAnim().animateToFinalPosition(f);
    }

    public final void cancelAnimation() {
        getSpringAnim().cancel();
    }

    public final void setStartValue(float f) {
        getSpringAnim().setStartValue(f);
    }
}
