package com.oplus.systemui.plugins.qs.customize.component;

import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.ParallelCOUIRecyclerView;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.PagerCallback;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.PerformanceUtils;
import com.oplusos.systemui.common.view.ViewExtKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$addPagerCallback$1 extends PagerCallback {
    public /* synthetic */ OplusQSTilesComponent this$0;

    public final void onPageScrollStateChanged(int i) {
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        oplusQSTilesComponent.indicatorView.getClass();
        if (i == 0) {
            JankUtils.gfxSceneEnd(oplusQSTilesComponent.getContext(), new int[]{21});
            if (!ParallelCOUIRecyclerView.Companion.shouldIgnoreParentBoundsForScroll(oplusQSTilesComponent.recyclerView)) {
                oplusQSTilesComponent.oplusGcSupressionExt.callGcDesupression(3);
            }
            oplusQSTilesComponent.stub.requestQsScrollBoost(false);
            oplusQSTilesComponent.qsRootView.requestDisallowInterceptTouchEvent(false);
            return;
        }
        if (i != 1) {
            return;
        }
        JankUtils.gfxSceneBegin$default(oplusQSTilesComponent.getContext(), 21, "SEPERATE_QS_SCROLL", 4L);
        oplusQSTilesComponent.oplusGcSupressionExt.callGcSupression(3, 3000);
        oplusQSTilesComponent.getOplusItemShowHideAnimator().enable(false);
        PerformanceUtils.notifySFAnimating(400);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "PerformanceUtils-->SeparateQSPageViewController notifySFAnimating done");
        }
        oplusQSTilesComponent.stub.requestQsScrollBoost(true);
    }

    public final void onPageScrolled(int i, float f, int i2) {
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        oplusQSTilesComponent.curPositionOffset = f;
        oplusQSTilesComponent.indicatorView.setCurrentPosition(f, i);
    }

    public final void onPageSelected(int i) {
        IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener;
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSTilesComponent", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "top indicator onPageSelect:"), null, 8);
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        oplusQSTilesComponent.indicatorView.setCurrentPosition(i);
        if (OplusQSTilesComponent.access$isEditMode(oplusQSTilesComponent) || (qSNodePageSelectedListener = oplusQSTilesComponent.nodePageSelectedListener) == null) {
            return;
        }
        QSPanelAnimatorManager qSPanelAnimatorManager = ((QSPanelAnimatorManager.AnonymousClass1) qSNodePageSelectedListener).this$0;
        LogUtil.i$default(LogUtil.TAG_QS, "QSPanelAnimatorManager", KeyguardLogger$.ExternalSyntheticOutline0.m("[onPageSelected] fRunning=", qSPanelAnimatorManager.qsPanelExpandFraction.isFractionAnimationRunning()), null, 8);
        QSPanelAnimatorManager.access$updateTileStatus(qSPanelAnimatorManager);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.PagerCallback
    public final void onPageSizeChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSTilesComponent", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "top indicator onPageSizeChanged:"), null, 8);
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        Object parent = oplusQSTilesComponent.indicatorView.getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view != null) {
            view.setVisibility(i > 1 ? 0 : 8);
        }
        oplusQSTilesComponent.indicatorView.setDotsCount(i);
        SeparateQSStatisicUtil.getSeparateTileInfoData().setQsTilesDotsCount(i);
        oplusQSTilesComponent.indicatorView.requestLayout();
        if (oplusQSTilesComponent.qsRootView.isLayoutSuppressed()) {
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQSTilesComponent", "qsRootView isLayoutSuppressed", null, 8);
            ViewExtKt.layoutViewToCenter(oplusQSTilesComponent.indicatorView);
        }
    }
}
