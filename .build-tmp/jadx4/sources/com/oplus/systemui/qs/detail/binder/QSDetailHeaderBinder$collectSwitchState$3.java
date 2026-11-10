package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.coui.appcompat.couiswitch.COUISwitch;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailHeaderViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderBinder$collectSwitchState$3 implements View.OnClickListener {
    public /* synthetic */ COUISwitch $switch;
    public /* synthetic */ QSDetailHeaderViewModel $viewModel;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.$viewModel.setToggleState(this.$switch.isChecked());
    }
}
