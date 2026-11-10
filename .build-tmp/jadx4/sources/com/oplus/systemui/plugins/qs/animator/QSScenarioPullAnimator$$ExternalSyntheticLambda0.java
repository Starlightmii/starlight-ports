package com.oplus.systemui.plugins.qs.animator;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSScenarioPullAnimator$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ QSScenarioPullAnimator f$0;

    public final Object invoke() {
        QSScenarioPullAnimator qSScenarioPullAnimator = this.f$0;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f));
        cOUISpringAnimation.setMinimumVisibleChange(1.0E-4f);
        cOUISpringAnimation.getSpring().setResponse(0.35f);
        cOUISpringAnimation.setStartValue(0.0f);
        QSScenarioPullAnimator$pullToScenarioAnimation$2$1$1 qSScenarioPullAnimator$pullToScenarioAnimation$2$1$1 = new QSScenarioPullAnimator$pullToScenarioAnimation$2$1$1();
        qSScenarioPullAnimator$pullToScenarioAnimation$2$1$1.this$0 = qSScenarioPullAnimator;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(qSScenarioPullAnimator$pullToScenarioAnimation$2$1$1);
        QSScenarioPullAnimator$pullToScenarioAnimation$2$1$2 qSScenarioPullAnimator$pullToScenarioAnimation$2$1$2 = new QSScenarioPullAnimator$pullToScenarioAnimation$2$1$2();
        qSScenarioPullAnimator$pullToScenarioAnimation$2$1$2.this$0 = qSScenarioPullAnimator;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(qSScenarioPullAnimator$pullToScenarioAnimation$2$1$2);
        return cOUISpringAnimation;
    }
}
