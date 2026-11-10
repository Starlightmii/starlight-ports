package com.oplus.systemui.plugins.qs.animator;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.coui.appcompat.springchain.COUISpringChain;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QsElementsChain$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QsElementsChain f$0;

    public /* synthetic */ QsElementsChain$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        QsElementsChain qsElementsChain = this.f$0;
        switch (i) {
            case 0:
                return COUISpringChain.create(qsElementsChain.springSystem, 150, 30, 450, 20);
            default:
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f));
                cOUISpringAnimation.getSpring().setBounce(0.14f);
                cOUISpringAnimation.setStartValue(0.0f);
                cOUISpringAnimation.getSpring().setStiffness(AnimatorUtils.responseToStiffness(0.3f));
                cOUISpringAnimation.setMinimumVisibleChange(1.0f);
                QsElementsChain$translationYOverAnimation$2$1$1 qsElementsChain$translationYOverAnimation$2$1$1 = new QsElementsChain$translationYOverAnimation$2$1$1();
                qsElementsChain$translationYOverAnimation$2$1$1.this$0 = qsElementsChain;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(qsElementsChain$translationYOverAnimation$2$1$1);
                QsElementsChain$translationYOverAnimation$2$1$2 qsElementsChain$translationYOverAnimation$2$1$2 = new QsElementsChain$translationYOverAnimation$2$1$2();
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(qsElementsChain$translationYOverAnimation$2$1$2);
                return cOUISpringAnimation;
        }
    }
}
