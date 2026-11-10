package com.oplus.tingle.ipc.serviceproxy.app;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy;
import mirror.android.util.Singleton;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ActivityManagerProxy extends SystemServiceProxy {
    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void init(Context context) {
        IActivityManager service = ActivityManager.getService();
        this.mOriginalService = service;
        this.mSlaveBinder = new SlaveBinder(service.asBinder());
        int i = mirror.android.app.ActivityManager.$r8$clinit;
        throw null;
    }

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void resetCacheInternal(Context context, Object obj) {
        int i = Singleton.$r8$clinit;
        throw null;
    }
}
