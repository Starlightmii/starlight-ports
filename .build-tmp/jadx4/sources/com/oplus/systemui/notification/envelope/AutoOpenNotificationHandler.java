package com.oplus.systemui.notification.envelope;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AutoOpenNotificationHandler {
    public final Object mLock;

    public AutoOpenNotificationHandler(Context context) {
        new BroadcastReceiver() { // from class: com.oplus.systemui.notification.envelope.AutoOpenNotificationHandler.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                LogUtil.d(LogUtil.TAG_COMMON, "AutoOpenNotificationHandler", "receive action: " + action + " start");
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                synchronized (AutoOpenNotificationHandler.this.mLock) {
                    action.equals("android.intent.action.SCREEN_ON");
                }
                LogUtil.d(LogUtil.TAG_COMMON, "AutoOpenNotificationHandler", "receive action: " + action + " end");
            }
        };
        this.mLock = new Object();
    }
}
