package com.oplus.systemui.notification.headsup.windowframe;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHeadsUpAnimator$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusHeadsUpAnimator f$0;

    public final Object invoke() {
        OplusHeadsUpAnimator oplusHeadsUpAnimator = this.f$0;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.3f).setResponse(0.5f));
        cOUISpringAnimation.setMinimumVisibleChange(1.0f);
        OplusHeadsUpAnimator$springAnimation$2$1$1 oplusHeadsUpAnimator$springAnimation$2$1$1 = new OplusHeadsUpAnimator$springAnimation$2$1$1();
        oplusHeadsUpAnimator$springAnimation$2$1$1.this$0 = oplusHeadsUpAnimator;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(oplusHeadsUpAnimator$springAnimation$2$1$1);
        return cOUISpringAnimation;
    }
}
