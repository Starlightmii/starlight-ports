package com.oplus.tingle.ipc.serviceproxy.view;

import android.view.IWindowSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WindowManagerProxy$$ExternalSyntheticLambda0 implements InvocationHandler {
    public /* synthetic */ WindowManagerProxy f$0;

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return method.invoke(IWindowSession.Stub.asInterface(this.f$0.mSlaveBinder), objArr);
    }
}
