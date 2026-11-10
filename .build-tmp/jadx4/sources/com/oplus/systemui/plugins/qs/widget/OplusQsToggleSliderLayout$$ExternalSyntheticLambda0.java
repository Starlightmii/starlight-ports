package com.oplus.systemui.plugins.qs.widget;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplusos.systemui.common.olivelink.Spring;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsToggleSliderLayout$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusQsToggleSliderLayout f$0;

    public final Object invoke() {
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = this.f$0;
        OplusQsToggleSliderLayout.Companion companion = OplusQsToggleSliderLayout.Companion;
        Spring springConfig = IEditModeSwitcher.Companion.getSpringConfig("enter_exit_edit_mode_main_transition");
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        if (springConfig != null) {
            COUISpringForce cOUISpringForce = new COUISpringForce();
            cOUISpringForce.setBounce(springConfig.bounce());
            cOUISpringForce.setResponse(springConfig.response());
            cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
            cOUISpringAnimation.setSpring(cOUISpringForce);
            cOUISpringAnimation.setStartVelocity(springConfig.velocity());
        }
        OplusQsToggleSliderLayout.springAnimation.2.1.2 r0 = new OplusQsToggleSliderLayout.springAnimation.2.1.2();
        r0.this$0 = oplusQsToggleSliderLayout;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(r0);
        OplusQsToggleSliderLayout.springAnimation.2.1.3 r1 = new OplusQsToggleSliderLayout.springAnimation.2.1.3();
        r1.this$0 = oplusQsToggleSliderLayout;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(r1);
        return cOUISpringAnimation;
    }
}
