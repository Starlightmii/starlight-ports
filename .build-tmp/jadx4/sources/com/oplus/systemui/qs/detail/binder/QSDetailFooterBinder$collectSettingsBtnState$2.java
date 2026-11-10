package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailFooterViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailFooterBinder$collectSettingsBtnState$2 implements View.OnClickListener {
    public /* synthetic */ View $detailContainer;
    public /* synthetic */ QSDetailFooterViewModel $viewModel;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.$viewModel.onSettingsBtnClick(this.$detailContainer);
    }
}
