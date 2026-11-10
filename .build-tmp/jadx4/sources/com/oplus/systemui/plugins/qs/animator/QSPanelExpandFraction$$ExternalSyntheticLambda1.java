package com.oplus.systemui.plugins.qs.animator;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSPanelExpandFraction$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSPanelExpandFraction f$0;

    public /* synthetic */ QSPanelExpandFraction$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        QSPanelExpandFraction qSPanelExpandFraction = this.f$0;
        switch (i) {
            case 0:
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f));
                cOUISpringAnimation.setStartValue(0.0f);
                cOUISpringAnimation.setMinimumVisibleChange(0.001f);
                Ref.FloatRef floatRef = new Ref.FloatRef();
                floatRef.element = qSPanelExpandFraction.maxFastResponse;
                int i2 = 0;
                QSPanelExpandFraction$fractionAnimation$2$1$1 qSPanelExpandFraction$fractionAnimation$2$1$1 = new QSPanelExpandFraction$fractionAnimation$2$1$1(i2);
                qSPanelExpandFraction$fractionAnimation$2$1$1.this$0 = qSPanelExpandFraction;
                qSPanelExpandFraction$fractionAnimation$2$1$1.$lastResponse = floatRef;
                qSPanelExpandFraction$fractionAnimation$2$1$1.$this_apply = cOUISpringAnimation;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(qSPanelExpandFraction$fractionAnimation$2$1$1);
                QSPanelExpandFraction$fractionAnimation$2$1$2 qSPanelExpandFraction$fractionAnimation$2$1$2 = new QSPanelExpandFraction$fractionAnimation$2$1$2(i2);
                qSPanelExpandFraction$fractionAnimation$2$1$2.this$0 = qSPanelExpandFraction;
                qSPanelExpandFraction$fractionAnimation$2$1$2.$lastResponse = floatRef;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(qSPanelExpandFraction$fractionAnimation$2$1$2);
                return cOUISpringAnimation;
            default:
                COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation2.setSpring(new COUISpringForce().setDampingRatio(1.0f));
                cOUISpringAnimation2.getSpring().setResponse(0.35f);
                cOUISpringAnimation2.setStartValue(0.0f);
                cOUISpringAnimation2.setMinimumVisibleChange(0.001f);
                Ref.FloatRef floatRef2 = new Ref.FloatRef();
                floatRef2.element = qSPanelExpandFraction.maxFastBlurResponse;
                int i3 = 1;
                QSPanelExpandFraction$fractionAnimation$2$1$1 qSPanelExpandFraction$fractionAnimation$2$1$3 = new QSPanelExpandFraction$fractionAnimation$2$1$1(i3);
                qSPanelExpandFraction$fractionAnimation$2$1$3.this$0 = qSPanelExpandFraction;
                qSPanelExpandFraction$fractionAnimation$2$1$3.$lastResponse = floatRef2;
                qSPanelExpandFraction$fractionAnimation$2$1$3.$this_apply = cOUISpringAnimation2;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addUpdateListener(qSPanelExpandFraction$fractionAnimation$2$1$3);
                QSPanelExpandFraction$fractionAnimation$2$1$2 qSPanelExpandFraction$fractionAnimation$2$1$4 = new QSPanelExpandFraction$fractionAnimation$2$1$2(i3);
                qSPanelExpandFraction$fractionAnimation$2$1$4.this$0 = qSPanelExpandFraction;
                qSPanelExpandFraction$fractionAnimation$2$1$4.$lastResponse = floatRef2;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addEndListener(qSPanelExpandFraction$fractionAnimation$2$1$4);
                return cOUISpringAnimation2;
        }
    }
}
