package com.oplus.tingle.ipc.serviceproxy.inner;

import android.content.Context;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WindowManagerInnerProxy extends SystemServiceProxy {
    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void init(Context context) {
        SlaveBinder slaveBinder = new SlaveBinder(WindowManagerGlobal.getWindowManagerService().asBinder());
        int i = mirror.android.view.WindowManagerGlobal.$r8$clinit;
        IWindowManager.Stub.asInterface(slaveBinder);
        throw null;
    }

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void resetCacheInternal(Context context, Object obj) {
        int i = mirror.android.view.WindowManagerGlobal.$r8$clinit;
        throw null;
    }
}
