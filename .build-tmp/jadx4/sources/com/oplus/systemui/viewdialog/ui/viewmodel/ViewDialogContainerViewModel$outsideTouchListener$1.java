package com.oplus.systemui.viewdialog.ui.viewmodel;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerViewModel$outsideTouchListener$1 implements View.OnClickListener {
    public /* synthetic */ ViewDialogContainerViewModel this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerViewModel", "outsideTouchListener: ", null, 8);
        this.this$0.dialogInteractor.onBackAction();
    }
}
