package com.oplus.systemui.statusbar.pipeline.wifi.data;

import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ Object f$1;

    public /* synthetic */ OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                ((BroadcastDispatcher) this.f$0).unregisterReceiver((OplusWifiRepositoryImpl$isWifiToData$1$receiver$1) this.f$1);
                break;
            case 1:
                OplusWifiRepositoryImpl oplusWifiRepositoryImpl = (OplusWifiRepositoryImpl) this.f$0;
                MultiUserContentHelper.Companion.unregister(oplusWifiRepositoryImpl.context, (OplusWifiRepositoryImpl$wifiTechnicalEnable$1$wifiTechnicalStandardObserver$1) this.f$1);
                break;
            case 2:
                OplusWifiRepositoryImpl oplusWifiRepositoryImpl2 = (OplusWifiRepositoryImpl) this.f$0;
                oplusWifiRepositoryImpl2.oplusThemeController.removeCallback((OplusWifiRepositoryImpl$wifiThemeAdapterState$1$themeListener$1) this.f$1);
                break;
            default:
                OplusWifiRepositoryImpl oplusWifiRepositoryImpl3 = (OplusWifiRepositoryImpl) this.f$0;
                MultiUserContentHelper.Companion.unregister(oplusWifiRepositoryImpl3.context, (OplusWifiRepositoryImpl$wlanBandLabelOff$1$wlanBandLabelObserver$1) this.f$1);
                break;
        }
        return Unit.INSTANCE;
    }
}
