package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.support.v4.content.FileProvider$;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiRepositoryImpl$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        switch (this.$r8$classId) {
            case 0:
                return ListPopupWindow$.ExternalSyntheticOutline0.m(logMessage.getInt1(), "NetworkCallback. OnCapabilitiesChanged network=");
            case 1:
                return ListPopupWindow$.ExternalSyntheticOutline0.m(logMessage.getInt1(), "NetworkCallback. onLost=");
            default:
                return FileProvider$.ExternalSyntheticOutline0.m("onBroadcastReceived action=", logMessage.getStr1());
        }
    }
}
