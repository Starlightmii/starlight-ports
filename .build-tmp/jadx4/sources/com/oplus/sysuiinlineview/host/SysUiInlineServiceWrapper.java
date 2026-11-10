package com.oplus.sysuiinlineview.host;

import android.os.Bundle;
import android.os.IBinder;
import com.oplus.sysuiinlineview.ISysUiInlineCallback;
import com.oplus.sysuiinlineview.ISysUiInlineService;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineServiceWrapper {
    private final ISysUiInlineService service;

    public SysUiInlineServiceWrapper(ISysUiInlineService iSysUiInlineService) {
        this.service = iSysUiInlineService;
    }

    public final IBinder asBinder() {
        return this.service.asBinder();
    }

    public final ISysUiInlineService getService() {
        return this.service;
    }

    public final void onHostViewCommand(String str, Bundle bundle) {
        this.service.onHostViewCommand(str, bundle);
    }

    public final void onHostViewConfigurationChanged(String str, Bundle bundle) {
        this.service.onHostViewConfigurationChanged(str, bundle);
    }

    public final void onHostViewCreate(String str, ISysUiInlineCallback iSysUiInlineCallback, Bundle bundle) {
        this.service.onHostViewCreate(str, iSysUiInlineCallback, bundle);
    }

    public final void onHostViewDestroy(String str, Bundle bundle) {
        this.service.onHostViewDestroy(str, bundle);
    }

    public final void onHostViewLayout(String str, Bundle bundle) {
        this.service.onHostViewLayout(str, bundle);
    }

    public final void onHostViewPause(String str, Bundle bundle) {
        this.service.onHostViewPause(str, bundle);
    }

    public final void onHostViewResume(String str, Bundle bundle) {
        this.service.onHostViewResume(str, bundle);
    }
}
