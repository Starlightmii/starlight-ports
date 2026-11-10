package com.oplus.systemui.statusbar.pipeline.wifi.data;

import com.oplus.systemui.utils.OplusThemeController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiRepositoryImpl$wifiThemeAdapterState$1$themeListener$1 implements OplusThemeController.ThemeListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ OplusWifiRepositoryImpl this$0;

    @Override // com.oplus.systemui.utils.OplusThemeController.ThemeListener
    public final void onThemeChanged() {
        OplusWifiRepositoryImpl oplusWifiRepositoryImpl = this.this$0;
        WifiThemeAdapterState wifiThemeAdapterStateCalculateAdapterState = oplusWifiRepositoryImpl.calculateAdapterState(oplusWifiRepositoryImpl.oplusThemeController.isThirdTheme);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusWifiRepositoryImpl", "onThemeChanged state:" + wifiThemeAdapterStateCalculateAdapterState, null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(wifiThemeAdapterStateCalculateAdapterState);
    }
}
