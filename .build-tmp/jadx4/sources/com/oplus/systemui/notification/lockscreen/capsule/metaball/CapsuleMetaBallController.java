package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.graphics.Rect;
import android.util.Log;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleBackgroundView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.LockScreenAmbientState;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMetaBallController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleMetaBallController";
    public boolean isLastScrollLeft;
    private MetaBallViewContext metaBallViewContext;
    private MetaBallEarManager earManager = new MetaBallEarManager();
    private MetaBallBlendManager blendManager = new MetaBallBlendManager();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MetaBallViewContext {
        private final MetaBallBlurDrawable baseCardBgDrawable;
        private final CapsuleBackgroundView baseCardBgView;
        private final MetaBallBlurDrawable nextCardBgDrawable;
        private final CapsuleBackgroundView nextCardBgView;

        public MetaBallViewContext(CapsuleBackgroundView capsuleBackgroundView, CapsuleBackgroundView capsuleBackgroundView2, MetaBallBlurDrawable metaBallBlurDrawable, MetaBallBlurDrawable metaBallBlurDrawable2) {
            this.baseCardBgView = capsuleBackgroundView;
            this.nextCardBgView = capsuleBackgroundView2;
            this.baseCardBgDrawable = metaBallBlurDrawable;
            this.nextCardBgDrawable = metaBallBlurDrawable2;
        }

        public final CapsuleBackgroundView component1() {
            return this.baseCardBgView;
        }

        public final CapsuleBackgroundView component2() {
            return this.nextCardBgView;
        }

        public final MetaBallBlurDrawable component3() {
            return this.baseCardBgDrawable;
        }

        public final MetaBallBlurDrawable component4() {
            return this.nextCardBgDrawable;
        }

        public final MetaBallViewContext copy(CapsuleBackgroundView capsuleBackgroundView, CapsuleBackgroundView capsuleBackgroundView2, MetaBallBlurDrawable metaBallBlurDrawable, MetaBallBlurDrawable metaBallBlurDrawable2) {
            return new MetaBallViewContext(capsuleBackgroundView, capsuleBackgroundView2, metaBallBlurDrawable, metaBallBlurDrawable2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MetaBallViewContext)) {
                return false;
            }
            MetaBallViewContext metaBallViewContext = (MetaBallViewContext) obj;
            return Intrinsics.areEqual(this.baseCardBgView, metaBallViewContext.baseCardBgView) && Intrinsics.areEqual(this.nextCardBgView, metaBallViewContext.nextCardBgView) && Intrinsics.areEqual(this.baseCardBgDrawable, metaBallViewContext.baseCardBgDrawable) && Intrinsics.areEqual(this.nextCardBgDrawable, metaBallViewContext.nextCardBgDrawable);
        }

        public final MetaBallBlurDrawable getBaseCardBgDrawable() {
            return this.baseCardBgDrawable;
        }

        public final CapsuleBackgroundView getBaseCardBgView() {
            return this.baseCardBgView;
        }

        public final MetaBallBlurDrawable getNextCardBgDrawable() {
            return this.nextCardBgDrawable;
        }

        public final CapsuleBackgroundView getNextCardBgView() {
            return this.nextCardBgView;
        }

        public final int hashCode() {
            CapsuleBackgroundView capsuleBackgroundView = this.baseCardBgView;
            int iHashCode = (capsuleBackgroundView == null ? 0 : capsuleBackgroundView.hashCode()) * 31;
            CapsuleBackgroundView capsuleBackgroundView2 = this.nextCardBgView;
            int iHashCode2 = (iHashCode + (capsuleBackgroundView2 == null ? 0 : capsuleBackgroundView2.hashCode())) * 31;
            MetaBallBlurDrawable metaBallBlurDrawable = this.baseCardBgDrawable;
            int iHashCode3 = (iHashCode2 + (metaBallBlurDrawable == null ? 0 : metaBallBlurDrawable.hashCode())) * 31;
            MetaBallBlurDrawable metaBallBlurDrawable2 = this.nextCardBgDrawable;
            return iHashCode3 + (metaBallBlurDrawable2 != null ? metaBallBlurDrawable2.hashCode() : 0);
        }

        public final boolean isInvalidDrawable(LockScreenAmbientState lockScreenAmbientState) {
            CapsuleBackgroundView bgView;
            CapsuleBackgroundView bgView2;
            MetaBallBlurDrawable metaBallBlurDrawable = this.baseCardBgDrawable;
            CapsuleItemView baseCard = lockScreenAmbientState.getBaseCard();
            MetaBallBlurDrawable blurDrawable = null;
            if (!Intrinsics.areEqual(metaBallBlurDrawable, (baseCard == null || (bgView2 = baseCard.getBgView()) == null) ? null : bgView2.getBlurDrawable())) {
                return true;
            }
            MetaBallBlurDrawable metaBallBlurDrawable2 = this.nextCardBgDrawable;
            CapsuleItemView nextCard = lockScreenAmbientState.getNextCard();
            if (nextCard != null && (bgView = nextCard.getBgView()) != null) {
                blurDrawable = bgView.getBlurDrawable();
            }
            return !Intrinsics.areEqual(metaBallBlurDrawable2, blurDrawable);
        }

        public String toString() {
            return "MetaBallViewContext(baseCardBgView=" + this.baseCardBgView + ", nextCardBgView=" + this.nextCardBgView + ", baseCardBgDrawable=" + this.baseCardBgDrawable + ", nextCardBgDrawable=" + this.nextCardBgDrawable + ")";
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
    public final void updateMetaBallAndEarState(LockScreenAmbientState lockScreenAmbientState) {
        ?? nextCard;
        if (lockScreenAmbientState.getBaseCard() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleMetaBallController-->Base card is null");
                return;
            }
            return;
        }
        if (lockScreenAmbientState.getNextCard() == null) {
            this.metaBallViewContext = null;
            this.earManager.clearDrawableResource("Next card is null");
            this.blendManager.clearMetaBallResource("Next card is null");
            return;
        }
        boolean z = lockScreenAmbientState.getScrollX() < 0;
        MetaBallViewContext metaBallViewContext = this.metaBallViewContext;
        if ((metaBallViewContext != null && metaBallViewContext.isInvalidDrawable(lockScreenAmbientState)) || this.isLastScrollLeft != z) {
            this.metaBallViewContext = null;
            this.earManager.clearDrawableResource("invalid drawable");
            this.blendManager.clearMetaBallResource("invalid drawable");
        }
        this.isLastScrollLeft = z;
        ?? baseCard = lockScreenAmbientState.getBaseCard();
        if (baseCard == 0 || (nextCard = lockScreenAmbientState.getNextCard()) == 0) {
            return;
        }
        if (this.metaBallViewContext == null) {
            CapsuleBackgroundView bgView = baseCard.getBgView();
            CapsuleBackgroundView bgView2 = nextCard.getBgView();
            MetaBallBlurDrawable blurDrawable = bgView.getBlurDrawable();
            if (blurDrawable != null) {
                MetaBallBlurDrawable blurDrawable2 = bgView2.getBlurDrawable();
                if (blurDrawable2 == null) {
                    bgView2.getCapsuleHelper().ensureBlurDrawable();
                    blurDrawable2 = bgView2.getBlurDrawable();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CapsuleMetaBallController-->".concat("doubleCheckNextCardBlurDrawable:" + blurDrawable2));
                    }
                }
                if (blurDrawable2 != null) {
                    MetaBallViewContext metaBallViewContext2 = new MetaBallViewContext(bgView, bgView2, blurDrawable, blurDrawable2);
                    this.metaBallViewContext = metaBallViewContext2;
                    this.blendManager.initMetaBallViewContext(metaBallViewContext2);
                    this.earManager.initMetaBallViewContext(this.metaBallViewContext);
                }
            }
        }
        Rect viewStateClipRect = nextCard.getViewStateClipRect();
        Rect viewStateClipRect2 = baseCard.getViewStateClipRect();
        float translationX = baseCard.getTranslationX();
        float translationX2 = nextCard.getTranslationX();
        int scrollX = lockScreenAmbientState.getScrollX();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleMetaBallController-->".concat("scrollX=" + scrollX + ", nextCardRect=" + viewStateClipRect + ", baseCardRect=" + viewStateClipRect2));
        }
        if (scrollX != 0) {
            int iWidth = viewStateClipRect.width();
            CapsuleEarShapeTool capsuleEarShapeTool = CapsuleEarShapeTool.INSTANCE;
            if (iWidth >= capsuleEarShapeTool.getEarEndHeightForCapsule() && viewStateClipRect2.width() >= capsuleEarShapeTool.getEarEndHeightForCapsule()) {
                if (!this.blendManager.isBindState()) {
                    this.earManager.updateEarClipEnable();
                }
                this.blendManager.handleMetaBallBlending(viewStateClipRect, viewStateClipRect2, translationX2, translationX);
                return;
            }
        }
        if (this.blendManager.isBindState()) {
            this.blendManager.exitMetaBallBlendState();
        }
        this.earManager.updateEarState(scrollX, viewStateClipRect, viewStateClipRect2);
    }
}
