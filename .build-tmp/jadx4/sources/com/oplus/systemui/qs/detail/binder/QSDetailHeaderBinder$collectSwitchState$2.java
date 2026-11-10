package com.oplus.systemui.qs.detail.binder;

import android.util.Log;
import com.coui.appcompat.couiswitch.COUISwitch;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailHeaderViewModel;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderBinder$collectSwitchState$2 implements COUISwitch.OnLoadingStateChangedListener {
    public /* synthetic */ COUISwitch $switch;
    public /* synthetic */ QSDetailHeaderViewModel $viewModel;

    public final void onStartLoading() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSDetailHeaderBinder-->COUISwitch - onStartLoading");
        }
        this.$viewModel.setToggleState(!this.$switch.isChecked());
    }

    public final void onStopLoading() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSDetailHeaderBinder-->COUISwitch - onStopLoading");
        }
    }
}
