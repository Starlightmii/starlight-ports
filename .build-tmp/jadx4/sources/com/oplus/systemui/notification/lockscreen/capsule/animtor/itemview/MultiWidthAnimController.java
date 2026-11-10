package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.effect.CapsuleAnimUtilsKt;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiWidthAnimController extends WidthAnimController {
    public final boolean enableBlurAnimation;
    private final CapsuleItemBaseAnimator.ItemAnimatorUpdater onAnimUpdate;
    private final Function0 onAppearAnimEnd;
    private final Function0 onAppearAnimStart;
    private final Function0 onDisappearAnimEnd;
    private final Function0 onDisappearAnimStart;
    private final Set<COUISpringAnimation> p1AnimSet;
    private final Set<COUISpringAnimation> p1p2AnimSet;
    private final Set<COUISpringAnimation> p2AnimSet;
    private final CapsuleSpringAnimParams.CapsuleItemSpringData springData;
    public final int baseWidth = -1;
    public float minDiff = Float.MAX_VALUE;
    private final AtomicBoolean p2Started = new AtomicBoolean(false);

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.MultiWidthAnimController$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass7 implements COUIDynamicAnimation.OnAnimationUpdateListener {
        public /* synthetic */ MultiWidthAnimController this$0;

        public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
            MultiWidthAnimController multiWidthAnimController = this.this$0;
            float fAbs = Math.abs(f - multiWidthAnimController.baseWidth);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("MainUpdaterXX: width:", f, ", curDiff:", fAbs, ", minDiff:"), multiWidthAnimController.minDiff, "WidthAnimController-->", "SystemUi--Notification");
            }
            multiWidthAnimController.getCurUpdateData().setWidth(f);
            if (fAbs <= multiWidthAnimController.minDiff) {
                multiWidthAnimController.getCurUpdateData().setPhase1(true);
                multiWidthAnimController.getOnAnimUpdate().update(multiWidthAnimController.getCurUpdateData());
            } else {
                if (multiWidthAnimController.p2Started.compareAndSet(false, true)) {
                    MultiWidthAnimController.access$startP2Anim(multiWidthAnimController);
                }
                multiWidthAnimController.getCurUpdateData().setPhase1(false);
                multiWidthAnimController.getOnAnimUpdate().update(multiWidthAnimController.getCurUpdateData());
            }
            if (multiWidthAnimController.minDiff > fAbs) {
                multiWidthAnimController.minDiff = fAbs;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.MultiWidthAnimController$startAnim$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements COUIDynamicAnimation.OnAnimationEndListener {
        public /* synthetic */ boolean $addItem;
        public /* synthetic */ ItemAnimatorFinished $onItemAnimatorFinished;
        public /* synthetic */ MultiWidthAnimController this$0;

        public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
            MultiWidthAnimController multiWidthAnimController = this.this$0;
            Iterator<T> it = multiWidthAnimController.getP1AnimSet().iterator();
            while (it.hasNext()) {
                multiWidthAnimController.stopAnim((COUISpringAnimation) it.next());
            }
            if (multiWidthAnimController.getEnableBlurAnimation()) {
                Iterator<T> it2 = multiWidthAnimController.getP2AnimSet().iterator();
                while (it2.hasNext()) {
                    multiWidthAnimController.stopAnim((COUISpringAnimation) it2.next());
                }
            }
            Iterator<T> it3 = multiWidthAnimController.getP1p2AnimSet().iterator();
            while (it3.hasNext()) {
                multiWidthAnimController.stopAnim((COUISpringAnimation) it3.next());
            }
            multiWidthAnimController.getOnAppearAnimEnd().invoke();
            ((CapsuleItemBaseAnimator) this.$onItemAnimatorFinished).onItemAnimatorFinished(this.$addItem);
        }
    }

    public MultiWidthAnimController(CapsuleSpringAnimParams.CapsuleItemSpringData capsuleItemSpringData, Function0 function0, Function0 function1, Function0 function2, Function0 function3, CapsuleItemBaseAnimator.ItemAnimatorUpdater itemAnimatorUpdater, boolean z) {
        this.springData = capsuleItemSpringData;
        this.onAppearAnimStart = function0;
        this.onAppearAnimEnd = function1;
        this.onDisappearAnimStart = function2;
        this.onDisappearAnimEnd = function3;
        this.onAnimUpdate = itemAnimatorUpdater;
        this.enableBlurAnimation = z;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.p1p2AnimSet = linkedHashSet;
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        this.p1AnimSet = linkedHashSet2;
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        this.p2AnimSet = linkedHashSet3;
        linkedHashSet.add(capsuleItemSpringData.getHeightSpringAnim());
        if (z) {
            linkedHashSet2.add(capsuleItemSpringData.getP1BlurSpringAnim());
            COUISpringAnimation p2BlurSpringAnim = capsuleItemSpringData.getP2BlurSpringAnim();
            if (p2BlurSpringAnim != null) {
                linkedHashSet3.add(p2BlurSpringAnim);
            }
        }
        COUISpringAnimation heightSpringAnim = capsuleItemSpringData.getHeightSpringAnim();
        MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda0 = new MultiWidthAnimController$$ExternalSyntheticLambda0(0);
        multiWidthAnimController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        CapsuleAnimUtilsKt.addUpdater(heightSpringAnim, multiWidthAnimController$$ExternalSyntheticLambda0);
        if (z) {
            COUISpringAnimation p1BlurSpringAnim = capsuleItemSpringData.getP1BlurSpringAnim();
            MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda1 = new MultiWidthAnimController$$ExternalSyntheticLambda0(1);
            multiWidthAnimController$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            CapsuleAnimUtilsKt.addUpdater(p1BlurSpringAnim, multiWidthAnimController$$ExternalSyntheticLambda1);
            COUISpringAnimation p2BlurSpringAnim2 = capsuleItemSpringData.getP2BlurSpringAnim();
            if (p2BlurSpringAnim2 != null) {
                MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda2 = new MultiWidthAnimController$$ExternalSyntheticLambda0(2);
                multiWidthAnimController$$ExternalSyntheticLambda2.f$0 = this;
                VarHandle.storeStoreFence();
                CapsuleAnimUtilsKt.addUpdater(p2BlurSpringAnim2, multiWidthAnimController$$ExternalSyntheticLambda2);
            }
        }
        CapsuleSpringAnimParams.EarTransXSpringData earTransXSpringData = capsuleItemSpringData.getEarTransXSpringData();
        if (earTransXSpringData != null) {
            COUISpringAnimation appearSpring = earTransXSpringData.getAppearSpring();
            MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda3 = new MultiWidthAnimController$$ExternalSyntheticLambda0(3);
            multiWidthAnimController$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            CapsuleAnimUtilsKt.addUpdater(appearSpring, multiWidthAnimController$$ExternalSyntheticLambda3);
            COUISpringAnimation disappearSpring = earTransXSpringData.getDisappearSpring();
            MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda4 = new MultiWidthAnimController$$ExternalSyntheticLambda0(4);
            multiWidthAnimController$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            CapsuleAnimUtilsKt.addUpdater(disappearSpring, multiWidthAnimController$$ExternalSyntheticLambda4);
            COUISpringAnimation switchSpring = earTransXSpringData.getSwitchSpring();
            MultiWidthAnimController$$ExternalSyntheticLambda0 multiWidthAnimController$$ExternalSyntheticLambda5 = new MultiWidthAnimController$$ExternalSyntheticLambda0(5);
            multiWidthAnimController$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            CapsuleAnimUtilsKt.addUpdater(switchSpring, multiWidthAnimController$$ExternalSyntheticLambda5);
        }
        COUISpringAnimation widthSpringAnim = capsuleItemSpringData.getWidthSpringAnim();
        AnonymousClass7 anonymousClass7 = new AnonymousClass7();
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        widthSpringAnim.addUpdateListener(anonymousClass7);
    }

    public static final void access$startP2Anim(MultiWidthAnimController multiWidthAnimController) {
        Iterator<T> it = multiWidthAnimController.p1AnimSet.iterator();
        while (it.hasNext()) {
            multiWidthAnimController.cancelAnim((COUISpringAnimation) it.next());
        }
        multiWidthAnimController.onDisappearAnimEnd.invoke();
        multiWidthAnimController.onAppearAnimStart.invoke();
        Iterator<T> it2 = multiWidthAnimController.p2AnimSet.iterator();
        while (it2.hasNext()) {
            ((COUISpringAnimation) it2.next()).start();
        }
    }

    public final boolean getEnableBlurAnimation() {
        return this.enableBlurAnimation;
    }

    public final CapsuleItemBaseAnimator.ItemAnimatorUpdater getOnAnimUpdate() {
        return this.onAnimUpdate;
    }

    public final Function0 getOnAppearAnimEnd() {
        return this.onAppearAnimEnd;
    }

    public final Function0 getOnAppearAnimStart() {
        return this.onAppearAnimStart;
    }

    public final Function0 getOnDisappearAnimEnd() {
        return this.onDisappearAnimEnd;
    }

    public final Function0 getOnDisappearAnimStart() {
        return this.onDisappearAnimStart;
    }

    public final Set<COUISpringAnimation> getP1AnimSet() {
        return this.p1AnimSet;
    }

    public final Set<COUISpringAnimation> getP1p2AnimSet() {
        return this.p1p2AnimSet;
    }

    public final Set<COUISpringAnimation> getP2AnimSet() {
        return this.p2AnimSet;
    }

    public final CapsuleSpringAnimParams.CapsuleItemSpringData getSpringData() {
        return this.springData;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.WidthAnimController
    public void startAnim(ItemAnimatorFinished itemAnimatorFinished, boolean z) {
        this.onDisappearAnimStart.invoke();
        COUISpringAnimation widthSpringAnim = this.springData.getWidthSpringAnim();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$onItemAnimatorFinished = itemAnimatorFinished;
        anonymousClass1.$addItem = z;
        VarHandle.storeStoreFence();
        widthSpringAnim.addEndListener(anonymousClass1);
        this.springData.getWidthSpringAnim().start();
        Iterator<T> it = this.p1p2AnimSet.iterator();
        while (it.hasNext()) {
            ((COUISpringAnimation) it.next()).start();
        }
        Iterator<T> it2 = this.p1AnimSet.iterator();
        while (it2.hasNext()) {
            ((COUISpringAnimation) it2.next()).start();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "WidthAnimController-->CapsuleItemAnimatorStarted");
        }
    }
}
