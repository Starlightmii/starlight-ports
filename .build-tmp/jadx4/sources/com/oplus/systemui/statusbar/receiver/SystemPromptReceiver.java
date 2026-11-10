package com.oplus.systemui.statusbar.receiver;

import android.content.Context;
import android.content.IntentFilter;
import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SystemPromptReceiver extends AbstractReceiver {
    public SystemPromptReceiver(Context context) {
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public void beforeRegister(IntentFilter intentFilter) {
        intentFilter.setPriority(1000);
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.intent.action.BOOT_COMPLETED", "android.intent.action.USER_SWITCHED"};
    }
}
