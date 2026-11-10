package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.lockscreen.effect.CapsuleAnimUtilsKt;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleWidthAnimController extends WidthAnimController {
    private final Set<COUISpringAnimation> childAnims;
    private final Function0 onAnimEnd;
    private final Function0 onAnimStart;
    private final CapsuleItemBaseAnimator.ItemAnimatorUpdater onAnimUpdate;
    private final CapsuleSpringAnimParams.CapsuleItemSpringData springData;

    public SingleWidthAnimController(CapsuleSpringAnimParams.CapsuleItemSpringData capsuleItemSpringData, Function0 function0, Function0 function1, CapsuleItemBaseAnimator.ItemAnimatorUpdater itemAnimatorUpdater) {
        this.springData = capsuleItemSpringData;
        this.onAnimStart = function0;
        this.onAnimEnd = function1;
        this.onAnimUpdate = itemAnimatorUpdater;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.childAnims = linkedHashSet;
        linkedHashSet.add(capsuleItemSpringData.getHeightSpringAnim());
        linkedHashSet.add(capsuleItemSpringData.getP1BlurSpringAnim());
        COUISpringAnimation heightSpringAnim = capsuleItemSpringData.getHeightSpringAnim();
        SingleWidthAnimController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new SingleWidthAnimController$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        CapsuleAnimUtilsKt.addUpdater(heightSpringAnim, externalSyntheticLambda0);
        COUISpringAnimation p1BlurSpringAnim = capsuleItemSpringData.getP1BlurSpringAnim();
        SingleWidthAnimController$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new SingleWidthAnimController$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        CapsuleAnimUtilsKt.addUpdater(p1BlurSpringAnim, externalSyntheticLambda1);
        COUISpringAnimation widthSpringAnim = capsuleItemSpringData.getWidthSpringAnim();
        4 r2 = new 4();
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        widthSpringAnim.addUpdateListener(r2);
    }

    public final Set<COUISpringAnimation> getChildAnims() {
        return this.childAnims;
    }

    public final Function0 getOnAnimEnd() {
        return this.onAnimEnd;
    }

    public final Function0 getOnAnimStart() {
        return this.onAnimStart;
    }

    public final CapsuleItemBaseAnimator.ItemAnimatorUpdater getOnAnimUpdate() {
        return this.onAnimUpdate;
    }

    public final CapsuleSpringAnimParams.CapsuleItemSpringData getSpringData() {
        return this.springData;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.WidthAnimController
    public void startAnim(ItemAnimatorFinished itemAnimatorFinished, boolean z) {
        this.onAnimStart.invoke();
        COUISpringAnimation widthSpringAnim = this.springData.getWidthSpringAnim();
        startAnim.1 r1 = new startAnim.1();
        r1.this$0 = this;
        r1.$onItemAnimatorFinished = itemAnimatorFinished;
        r1.$addItem = z;
        VarHandle.storeStoreFence();
        widthSpringAnim.addEndListener(r1);
        this.springData.getWidthSpringAnim().start();
        Iterator<T> it = this.childAnims.iterator();
        while (it.hasNext()) {
            ((COUISpringAnimation) it.next()).start();
        }
    }
}
