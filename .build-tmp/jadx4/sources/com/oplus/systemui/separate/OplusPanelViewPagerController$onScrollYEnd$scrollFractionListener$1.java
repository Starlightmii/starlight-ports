package com.oplus.systemui.separate;

import android.util.Log;
import android.view.View;
import com.android.systemui.shade.NotificationPanelView;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanelViewPagerController this$0;

    public /* synthetic */ OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1(int i) {
        this.$r8$classId = i;
    }

    public final void onFractionChangeFinished(boolean z) {
        switch (this.$r8$classId) {
            case 0:
                OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onScrollYEnd onFractionChangeFinished shouldScrollToNext=" + z + " downExpanded=" + oplusPanelViewPagerController.downExpanded + "}", null, 8);
                oplusPanelViewPagerController.fractionListeners.clear();
                oplusPanelViewPagerController.getFractionAnimation().setStartValue(0.0f);
                oplusPanelViewPagerController.getFractionAnimation().cancelComplete();
                if (!z) {
                    OplusSeparateQSManager oplusSeparateQSManager = oplusPanelViewPagerController.separateQSManager;
                    OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1$.ExternalSyntheticLambda0();
                    externalSyntheticLambda0.f$0 = oplusPanelViewPagerController;
                    externalSyntheticLambda0.f$1 = this;
                    VarHandle.storeStoreFence();
                    OplusSeparateQSManager.collapseQSPanel$default(oplusSeparateQSManager, false, true, false, externalSyntheticLambda0, 4);
                    oplusPanelViewPagerController.targetPage = OplusPanelViewPagerController.PageType.NOTIFICATION;
                    break;
                } else {
                    oplusPanelViewPagerController.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                    resetView();
                    oplusPanelViewPagerController.targetPage = OplusPanelViewPagerController.PageType.QS;
                    SeparateQSStatisicUtil.EXPANDED expanded = SeparateQSStatisicUtil.EXPANDED.QS;
                    oplusPanelViewPagerController.downExpanded = expanded;
                    TemporarilyInfoManager.Companion.getInstance().setStarted(true);
                    if (!oplusPanelViewPagerController.staticInfo.getDisuse() && oplusPanelViewPagerController.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
                        oplusPanelViewPagerController.staticInfo.setOpType(SeparateQSStatisicUtil.OPTYPE_SCROLL_TOPTOBOTTOM);
                        oplusPanelViewPagerController.onTrackComm(expanded);
                        break;
                    }
                }
                break;
            default:
                OplusPanelViewPagerController oplusPanelViewPagerController2 = this.this$0;
                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onScrollYEnd onFractionChangeFinished shouldScrollToNext=" + z + " downExpanded=" + oplusPanelViewPagerController2.downExpanded + "}", null, 8);
                oplusPanelViewPagerController2.fractionListeners.clear();
                oplusPanelViewPagerController2.getFractionAnimation().setStartValue(0.0f);
                oplusPanelViewPagerController2.getFractionAnimation().cancelComplete();
                if (!z) {
                    oplusPanelViewPagerController2.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                    View view = oplusPanelViewPagerController2.qsPanelView;
                    if (view != null) {
                        view.bringToFront();
                    }
                    resetView();
                    oplusPanelViewPagerController2.targetPage = OplusPanelViewPagerController.PageType.QS;
                    TemporarilyInfoManager.Companion.getInstance().setStarted(true);
                    break;
                } else {
                    OplusSeparateQSManager oplusSeparateQSManager2 = oplusPanelViewPagerController2.separateQSManager;
                    OplusPanelViewPagerController$$ExternalSyntheticLambda1 oplusPanelViewPagerController$$ExternalSyntheticLambda1 = new OplusPanelViewPagerController$$ExternalSyntheticLambda1(3);
                    oplusPanelViewPagerController$$ExternalSyntheticLambda1.f$0 = this;
                    VarHandle.storeStoreFence();
                    OplusSeparateQSManager.collapseQSPanel$default(oplusSeparateQSManager2, false, true, false, oplusPanelViewPagerController$$ExternalSyntheticLambda1, 4);
                    oplusPanelViewPagerController2.targetPage = OplusPanelViewPagerController.PageType.NOTIFICATION;
                    SeparateQSStatisicUtil.EXPANDED expanded2 = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
                    oplusPanelViewPagerController2.downExpanded = expanded2;
                    if (!oplusPanelViewPagerController2.staticInfo.getDisuse() && oplusPanelViewPagerController2.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.QS) {
                        oplusPanelViewPagerController2.staticInfo.setOpType(SeparateQSStatisicUtil.OPTYPE_SCROLL_TOPTOBOTTOM);
                        oplusPanelViewPagerController2.onTrackComm(expanded2);
                        break;
                    }
                }
                break;
        }
    }

    public final void onMoveDisplacementChanged(float f) {
        switch (this.$r8$classId) {
            case 0:
                OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
                NotificationPanelView notificationPanelView = oplusPanelViewPagerController.notificationPanelView;
                if (notificationPanelView != null) {
                    oplusPanelViewPagerController.setAlphaForScrollY(f, oplusPanelViewPagerController.currentQsFraction, notificationPanelView);
                }
                break;
            default:
                OplusPanelViewPagerController oplusPanelViewPagerController2 = this.this$0;
                View view = oplusPanelViewPagerController2.qsPanelView;
                if (view != null) {
                    oplusPanelViewPagerController2.setAlphaForScrollY(f, oplusPanelViewPagerController2.currentNotifyFraction, view);
                }
                break;
        }
    }

    public final void resetView() {
        switch (this.$r8$classId) {
            case 0:
                OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->resetView notificationPanelView");
                }
                NotificationPanelView notificationPanelView = oplusPanelViewPagerController.notificationPanelView;
                if (notificationPanelView != null) {
                    notificationPanelView.setAlpha(1.0f);
                }
                NotificationPanelView notificationPanelView2 = oplusPanelViewPagerController.notificationPanelView;
                if (notificationPanelView2 != null) {
                    notificationPanelView2.setScaleX(1.0f);
                }
                NotificationPanelView notificationPanelView3 = oplusPanelViewPagerController.notificationPanelView;
                if (notificationPanelView3 != null) {
                    notificationPanelView3.setScaleY(1.0f);
                }
                break;
            default:
                OplusPanelViewPagerController oplusPanelViewPagerController2 = this.this$0;
                View view = oplusPanelViewPagerController2.qsPanelView;
                if (view != null) {
                    view.setAlpha(1.0f);
                }
                View view2 = oplusPanelViewPagerController2.qsPanelView;
                if (view2 != null) {
                    view2.setScaleX(1.0f);
                }
                View view3 = oplusPanelViewPagerController2.qsPanelView;
                if (view3 != null) {
                    view3.setScaleY(1.0f);
                }
                break;
        }
    }
}
