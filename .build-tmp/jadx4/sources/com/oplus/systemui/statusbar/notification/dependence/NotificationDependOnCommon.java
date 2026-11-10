package com.oplus.systemui.statusbar.notification.dependence;

import android.content.Context;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationDependOnCommon {
    void addListener(StatusBarWindowStateListener statusBarWindowStateListener);

    Context getApplicationContext();

    int getCurrentUser();

    int getWindowState();

    void removeListener(StatusBarWindowStateListener statusBarWindowStateListener);
}
