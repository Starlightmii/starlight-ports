package com.oplus.systemui.viewdialog.ui.viewmodel;

import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContentViewModel$qsClosingCallBack$1 implements QSFragmentHelper.BeginClosingCallback {
    public /* synthetic */ ViewDialogContentViewModel this$0;

    @Override // com.oplus.systemui.qs.helper.QSFragmentHelper.BeginClosingCallback
    public final void onClosingBegin() {
        ViewDialogContentViewModel viewDialogContentViewModel = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, viewDialogContentViewModel.logTag, "onClosingBegin: ", null, 8);
        viewDialogContentViewModel.dialogInteractor.dismissDialog("middle");
    }

    @Override // com.oplus.systemui.qs.helper.QSFragmentHelper.BeginClosingCallback
    public final void onClosingEnd() {
        ViewDialogContentViewModel viewDialogContentViewModel = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, viewDialogContentViewModel.logTag, "onClosingEnd: ", null, 8);
        viewDialogContentViewModel.dialogInteractor.dismissDialog("instant");
    }
}
