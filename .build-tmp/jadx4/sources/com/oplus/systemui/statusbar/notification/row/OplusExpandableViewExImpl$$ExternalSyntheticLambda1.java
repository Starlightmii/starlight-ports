package com.oplus.systemui.statusbar.notification.row;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusExpandableViewExImpl$$ExternalSyntheticLambda1 implements Function0 {
    public /* synthetic */ OplusExpandableViewExImpl f$0;

    public final Object invoke() {
        OplusExpandableViewExImpl oplusExpandableViewExImpl = this.f$0;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setStiffness(750.0f).setDampingRatio(1.0f).setBounce(0.0f).setResponse(0.25f));
        cOUISpringAnimation.setMinimumVisibleChange(0.01f);
        cOUISpringAnimation.setStartValue(1.0f);
        OplusExpandableViewExImpl$touchScaleAnimator$2$1$1 oplusExpandableViewExImpl$touchScaleAnimator$2$1$1 = new OplusExpandableViewExImpl$touchScaleAnimator$2$1$1();
        oplusExpandableViewExImpl$touchScaleAnimator$2$1$1.this$0 = oplusExpandableViewExImpl;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(oplusExpandableViewExImpl$touchScaleAnimator$2$1$1);
        OplusExpandableViewExImpl$touchScaleAnimator$2$1$2 oplusExpandableViewExImpl$touchScaleAnimator$2$1$2 = new OplusExpandableViewExImpl$touchScaleAnimator$2$1$2();
        oplusExpandableViewExImpl$touchScaleAnimator$2$1$2.$this_apply = cOUISpringAnimation;
        oplusExpandableViewExImpl$touchScaleAnimator$2$1$2.this$0 = oplusExpandableViewExImpl;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(oplusExpandableViewExImpl$touchScaleAnimator$2$1$2);
        return cOUISpringAnimation;
    }
}
