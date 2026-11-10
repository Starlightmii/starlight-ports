package com.oplus.tingle.ipc.serviceproxy.pm;

import android.content.Context;
import android.content.pm.IPackageManager;
import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PackageManagerProxy$$ExternalSyntheticLambda0 implements InvocationHandler {
    public /* synthetic */ PackageManagerProxy f$0;
    public /* synthetic */ Context f$1;

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        PackageManagerProxy packageManagerProxy = this.f$0;
        Context context = this.f$1;
        boolean zEquals = TextUtils.equals((CharSequence) packageManagerProxy.mLocalKey.get(), Thread.currentThread().getName());
        Object obj2 = packageManagerProxy.mOriginalService;
        if (!zEquals) {
            return method.invoke(obj2, objArr);
        }
        packageManagerProxy.resetServiceCache(context, obj2);
        packageManagerProxy.mLocalKey.remove();
        return method.invoke(IPackageManager.Stub.asInterface(packageManagerProxy.mSlaveBinder), objArr);
    }
}
