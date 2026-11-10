package com.oplus.tingle.ipc.serviceproxy.view;

import android.content.Context;
import android.view.IWindowSession;
import android.view.WindowManagerGlobal;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WindowManagerProxy extends SystemServiceProxy {
    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void init(Context context) {
        IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
        this.mOriginalService = windowSession;
        this.mSlaveBinder = new SlaveBinder(windowSession.asBinder());
        WindowManagerProxy$$ExternalSyntheticLambda0 windowManagerProxy$$ExternalSyntheticLambda0 = new WindowManagerProxy$$ExternalSyntheticLambda0();
        windowManagerProxy$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mProxy = (IWindowSession) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IWindowSession.class}, windowManagerProxy$$ExternalSyntheticLambda0);
    }

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void resetCacheInternal(Context context, Object obj) {
        int i = mirror.android.view.WindowManagerGlobal.$r8$clinit;
        throw null;
    }
}
