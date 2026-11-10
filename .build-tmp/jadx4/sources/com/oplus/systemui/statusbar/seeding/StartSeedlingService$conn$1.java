package com.oplus.systemui.statusbar.seeding;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSeedlingService$conn$1 implements ServiceConnection {
    public /* synthetic */ StartSeedlingService this$0;

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StartSeedlingService-->onServiceConnected");
        }
        this.this$0.serverMessenger = new Messenger(iBinder);
        List list = this.this$0.mData;
        StartSeedlingService startSeedlingService = this.this$0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            startSeedlingService.sendMessage((Bundle) it.next(), false);
        }
        this.this$0.mData.clear();
        this.this$0.keyguardUpdateMonitor.removeCallback(this.this$0.keyguardUpdateMonitorCallback);
        this.this$0.keyguardUpdateMonitor.registerCallback(this.this$0.keyguardUpdateMonitorCallback);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StartSeedlingService-->onServiceDisconnected");
        }
        this.this$0.serverMessenger = null;
        this.this$0.keyguardUpdateMonitor.removeCallback(this.this$0.keyguardUpdateMonitorCallback);
    }
}
