package com.oplus.systemui.qs;

import android.content.Context;
import android.view.View;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.widget.MoreButtonPopupWindow;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterViewController$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public /* synthetic */ OplusQSFooterViewController f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ View f$2;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusQSFooterViewController oplusQSFooterViewController = this.f$0;
        Context context = this.f$1;
        View view2 = this.f$2;
        MoreButtonPopupWindow moreButtonPopupWindow = oplusQSFooterViewController.mCouiPopup;
        if (moreButtonPopupWindow != null && moreButtonPopupWindow.isShowing()) {
            MoreButtonPopupWindow moreButtonPopupWindow2 = oplusQSFooterViewController.mCouiPopup;
            if (moreButtonPopupWindow2 == null || !moreButtonPopupWindow2.isShowing()) {
                return;
            }
            oplusQSFooterViewController.mCouiPopup.dismiss();
            return;
        }
        MoreButtonPopupWindow moreButtonPopupWindow3 = new MoreButtonPopupWindow(context, oplusQSFooterViewController.mOplusFgsManagerController, (OplusQSFooterImpl) ((ViewController) oplusQSFooterViewController).mView, oplusQSFooterViewController.mShadeViewController, oplusQSFooterViewController.mSeparateNotificationAndQSState, oplusQSFooterViewController.mNotificationDependOnCommon, oplusQSFooterViewController.mOplusQSActivityStarter, oplusQSFooterViewController.mQsPanelController);
        oplusQSFooterViewController.mCouiPopup = moreButtonPopupWindow3;
        moreButtonPopupWindow3.updateFsg(oplusQSFooterViewController.mFgsNumPkg, oplusQSFooterViewController.mShowFgs);
        oplusQSFooterViewController.mCouiPopup.updateRedDotView(oplusQSFooterViewController.mIsQsSettingsHasOpenState);
        oplusQSFooterViewController.mCouiPopup.getClass();
        oplusQSFooterViewController.mCouiPopup.show(view2, false);
        QsStatisticUtil.collectShortcutsCenterEvent(context, "more_click");
    }
}
