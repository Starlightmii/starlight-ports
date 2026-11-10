package com.oplus.tingle.ipc.serviceproxy.app;

import android.app.INotificationManager;
import android.app.NotificationManager;
import android.content.Context;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationManagerProxy extends SystemServiceProxy {
    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void init(Context context) {
        INotificationManager service = NotificationManager.getService();
        this.mOriginalService = service;
        this.mSlaveBinder = new SlaveBinder(service.asBinder());
        NotificationManagerProxy$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationManagerProxy$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = context;
        VarHandle.storeStoreFence();
        this.mProxy = (INotificationManager) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{INotificationManager.class}, externalSyntheticLambda0);
    }

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void resetCacheInternal(Context context, Object obj) {
        int i = mirror.android.app.NotificationManager.$r8$clinit;
        throw null;
    }
}
