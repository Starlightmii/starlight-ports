package com.oplus.systemui.separate;

import com.android.systemui.LauncherProxyService;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1 implements LauncherProxyService.LauncherProxyListener {
    public /* synthetic */ QSSpecialModeUpdateMonitorImpl this$0;

    public final void onTaskbarStatusUpdated$1(boolean z, boolean z2) {
        this.this$0.taskbarVisible = z;
    }

    public /* bridge */ /* synthetic */ void setAssistantOverridesRequested(int[] iArr) {
        super.setAssistantOverridesRequested(iArr);
    }
}
