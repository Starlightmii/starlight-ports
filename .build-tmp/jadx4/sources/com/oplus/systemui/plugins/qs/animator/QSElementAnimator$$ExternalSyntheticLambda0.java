package com.oplus.systemui.plugins.qs.animator;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSElementAnimator$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSElementAnimator f$0;

    public /* synthetic */ QSElementAnimator$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        QSElementAnimator qSElementAnimator = this.f$0;
        switch (i) {
            case 0:
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f));
                cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
                int i2 = 0;
                QSElementAnimator$alphaAnimation$2$1$1 qSElementAnimator$alphaAnimation$2$1$1 = new QSElementAnimator$alphaAnimation$2$1$1(i2);
                qSElementAnimator$alphaAnimation$2$1$1.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(qSElementAnimator$alphaAnimation$2$1$1);
                QSElementAnimator$alphaAnimation$2$1$2 qSElementAnimator$alphaAnimation$2$1$2 = new QSElementAnimator$alphaAnimation$2$1$2(i2);
                qSElementAnimator$alphaAnimation$2$1$2.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(qSElementAnimator$alphaAnimation$2$1$2);
                return cOUISpringAnimation;
            case 1:
                COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation2.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.3f));
                cOUISpringAnimation2.setMinimumVisibleChange(3.0E-4f);
                int i3 = 2;
                QSElementAnimator$alphaAnimation$2$1$1 qSElementAnimator$alphaAnimation$2$1$3 = new QSElementAnimator$alphaAnimation$2$1$1(i3);
                qSElementAnimator$alphaAnimation$2$1$3.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addUpdateListener(qSElementAnimator$alphaAnimation$2$1$3);
                QSElementAnimator$alphaAnimation$2$1$2 qSElementAnimator$alphaAnimation$2$1$4 = new QSElementAnimator$alphaAnimation$2$1$2(i3);
                qSElementAnimator$alphaAnimation$2$1$4.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addEndListener(qSElementAnimator$alphaAnimation$2$1$4);
                return cOUISpringAnimation2;
            case 2:
                COUISpringAnimation cOUISpringAnimation3 = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation3.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.3f));
                cOUISpringAnimation3.setMinimumVisibleChange(3.0E-4f);
                int i4 = 1;
                QSElementAnimator$alphaAnimation$2$1$1 qSElementAnimator$alphaAnimation$2$1$5 = new QSElementAnimator$alphaAnimation$2$1$1(i4);
                qSElementAnimator$alphaAnimation$2$1$5.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation3.addUpdateListener(qSElementAnimator$alphaAnimation$2$1$5);
                QSElementAnimator$alphaAnimation$2$1$2 qSElementAnimator$alphaAnimation$2$1$6 = new QSElementAnimator$alphaAnimation$2$1$2(i4);
                qSElementAnimator$alphaAnimation$2$1$6.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation3.addEndListener(qSElementAnimator$alphaAnimation$2$1$6);
                return cOUISpringAnimation3;
            default:
                COUISpringAnimation cOUISpringAnimation4 = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation4.setSpring(new COUISpringForce().setBounce(0.0f));
                cOUISpringAnimation4.setMinimumVisibleChange(0.00390625f);
                int i5 = 3;
                QSElementAnimator$alphaAnimation$2$1$1 qSElementAnimator$alphaAnimation$2$1$7 = new QSElementAnimator$alphaAnimation$2$1$1(i5);
                qSElementAnimator$alphaAnimation$2$1$7.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation4.addUpdateListener(qSElementAnimator$alphaAnimation$2$1$7);
                QSElementAnimator$alphaAnimation$2$1$2 qSElementAnimator$alphaAnimation$2$1$8 = new QSElementAnimator$alphaAnimation$2$1$2(i5);
                qSElementAnimator$alphaAnimation$2$1$8.this$0 = qSElementAnimator;
                VarHandle.storeStoreFence();
                cOUISpringAnimation4.addEndListener(qSElementAnimator$alphaAnimation$2$1$8);
                return cOUISpringAnimation4;
        }
    }
}
