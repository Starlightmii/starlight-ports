package com.oplus.tingle.ipc.serviceproxy.pm;

import android.app.ActivityThread;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.IPackageManager;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy;
import com.oplus.tingle.ipc.utils.Logger;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Proxy;
import mirror.android.app.ContextImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PackageManagerProxy extends SystemServiceProxy {
    public static Class mClass;

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void init(Context context) {
        if (context.getPackageManager() != null) {
            IPackageManager packageManager = ActivityThread.getPackageManager();
            this.mOriginalService = packageManager;
            this.mSlaveBinder = new SlaveBinder(packageManager.asBinder());
            PackageManagerProxy$$ExternalSyntheticLambda0 packageManagerProxy$$ExternalSyntheticLambda0 = new PackageManagerProxy$$ExternalSyntheticLambda0();
            packageManagerProxy$$ExternalSyntheticLambda0.f$0 = this;
            packageManagerProxy$$ExternalSyntheticLambda0.f$1 = context;
            VarHandle.storeStoreFence();
            this.mProxy = (IPackageManager) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IPackageManager.class}, packageManagerProxy$$ExternalSyntheticLambda0);
        }
    }

    @Override // com.oplus.tingle.ipc.serviceproxy.SystemServiceProxy
    public final void resetCacheInternal(Context context, Object obj) {
        if (mClass == null) {
            try {
                mClass = Class.forName("android.app.ContextImpl");
            } catch (Exception e) {
                Logger.e("PackageManagerProxy", e.toString(), new Object[0]);
            }
        }
        Class cls = mClass;
        if (cls == null || context == null) {
            return;
        }
        if (cls.isAssignableFrom(context.getClass())) {
            int i = ContextImpl.$r8$clinit;
            throw null;
        }
        int i2 = ContextImpl.$r8$clinit;
        ((ContextWrapper) context).getBaseContext();
        throw null;
    }
}
