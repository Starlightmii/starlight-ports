package com.oplus.systemui.separate;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPanelViewPagerController$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ OplusPanelViewPagerController$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                OplusPanelViewPagerController oplusPanelViewPagerController = (OplusPanelViewPagerController) obj;
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce(0.14f).setResponse(0.25f));
                cOUISpringAnimation.setStartValue(0.0f);
                cOUISpringAnimation.setMinimumVisibleChange(0.001f);
                OplusPanelViewPagerController$fractionAnimation$2$1$1 oplusPanelViewPagerController$fractionAnimation$2$1$1 = new OplusPanelViewPagerController$fractionAnimation$2$1$1();
                oplusPanelViewPagerController$fractionAnimation$2$1$1.this$0 = oplusPanelViewPagerController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(oplusPanelViewPagerController$fractionAnimation$2$1$1);
                OplusPanelViewPagerController$fractionAnimation$2$1$2 oplusPanelViewPagerController$fractionAnimation$2$1$2 = new OplusPanelViewPagerController$fractionAnimation$2$1$2();
                oplusPanelViewPagerController$fractionAnimation$2$1$2.this$0 = oplusPanelViewPagerController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(oplusPanelViewPagerController$fractionAnimation$2$1$2);
                return cOUISpringAnimation;
            case 1:
                ((OplusPanelViewPagerController) obj).resetHorizontalTranslationView();
                return Unit.INSTANCE;
            case 2:
                ((OplusPanelViewPagerController) obj).resetHorizontalTranslationView();
                return Unit.INSTANCE;
            default:
                ((OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1) obj).resetView();
                return Unit.INSTANCE;
        }
    }
}
