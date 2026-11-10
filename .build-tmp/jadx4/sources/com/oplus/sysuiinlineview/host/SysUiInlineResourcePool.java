package com.oplus.sysuiinlineview.host;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory;
import com.oplus.sysuiinlineview.utils.SysUiInlineConstants;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public enum SysUiInlineResourcePool {
    Instance;

    private final String TAG = "SysUiInlineResourcePool";
    private final AtomicReference<Looper> mDefaultLooper = new AtomicReference<>(Looper.getMainLooper());
    private final ArrayMap<String, AtomicReference<Supplier<ComponentName>>> mComponentProvider = new ArrayMap<>();
    private final ArrayMap<String, AtomicReference<SysUiInlineViewFactory>> mViewFactoryProvider = new ArrayMap<>();
    private final ArrayMap<Pair<ComponentName, UserHandle>, SysUiInlineServiceManager> mServiceManagers = new ArrayMap<>();
    private final ArrayMap<Pair<String, UserHandle>, SysUiInlineViewHost> mInlineViewHosts = new ArrayMap<>();

    public final SysUiInlineViewHost getInlineViewHost(Context context, String str, UserHandle userHandle) {
        synchronized (this.mInlineViewHosts) {
            try {
                Pair<String, UserHandle> pair = new Pair<>(str, userHandle);
                SysUiInlineViewHost sysUiInlineViewHost = this.mInlineViewHosts.get(pair);
                if (sysUiInlineViewHost != null) {
                    return sysUiInlineViewHost;
                }
                SysUiInlineServiceManager serviceManager = getServiceManager(context, str, userHandle);
                SysUiInlineViewFactory sysUiInlineViewFactory = null;
                if (serviceManager == null) {
                    return null;
                }
                AtomicReference<SysUiInlineViewFactory> atomicReference = this.mViewFactoryProvider.get(str);
                if (atomicReference != null) {
                    sysUiInlineViewFactory = atomicReference.get();
                }
                if (sysUiInlineViewFactory == null) {
                    sysUiInlineViewFactory = SysUiInlineViewFactory.Default.INSTANCE;
                }
                Log.d(getTAG(), Intrinsics.stringPlus(str, "getPluginViewHost: create viewHost for category-"));
                SysUiInlineViewHost sysUiInlineViewHost2 = new SysUiInlineViewHost(str, sysUiInlineViewFactory, serviceManager);
                this.mInlineViewHosts.put(pair, sysUiInlineViewHost2);
                return sysUiInlineViewHost2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Intent getRequiredIntent() {
        return new Intent(SysUiInlineConstants.ACTION_SYS_UI_INLINE_SERVICE);
    }

    public final SysUiInlineServiceManager getServiceManager(Context context, String str, UserHandle userHandle) {
        Supplier<ComponentName> supplier;
        synchronized (this.mServiceManagers) {
            try {
                AtomicReference<Supplier<ComponentName>> atomicReference = this.mComponentProvider.get(str);
                ComponentName componentName = (atomicReference == null || (supplier = atomicReference.get()) == null) ? null : supplier.get();
                if (componentName == null) {
                    return null;
                }
                Pair<ComponentName, UserHandle> pair = new Pair<>(componentName, userHandle);
                SysUiInlineServiceManager sysUiInlineServiceManager = this.mServiceManagers.get(pair);
                if (sysUiInlineServiceManager != null) {
                    return sysUiInlineServiceManager;
                }
                Log.d(getTAG(), Intrinsics.stringPlus(str, "getClientManager: create client for category-"));
                Intent component = getRequiredIntent().setComponent(componentName);
                Looper mainLooper = this.mDefaultLooper.get();
                if (mainLooper == null) {
                    mainLooper = Looper.getMainLooper();
                }
                SysUiInlineServiceManager sysUiInlineServiceManager2 = new SysUiInlineServiceManager(context, component, userHandle, new Handler(mainLooper));
                this.mServiceManagers.put(pair, sysUiInlineServiceManager2);
                return sysUiInlineServiceManager2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final boolean setComponentProvider(String str, Supplier<ComponentName> supplier) {
        boolean zCompareAndSet;
        synchronized (this.mComponentProvider) {
            try {
                AtomicReference<Supplier<ComponentName>> atomicReference = this.mComponentProvider.get(str);
                if (atomicReference == null) {
                    atomicReference = new AtomicReference<>();
                    this.mComponentProvider.put(str, atomicReference);
                }
                zCompareAndSet = atomicReference.compareAndSet(null, supplier);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zCompareAndSet;
    }

    public final void setDefaultLooper(Looper looper) {
        Looper andSet = this.mDefaultLooper.getAndSet(looper);
        Log.i(this.TAG, "setDefaultLooper: update " + andSet + " to " + looper);
    }

    public final boolean setViewFactory(String str, SysUiInlineViewFactory sysUiInlineViewFactory) {
        boolean zCompareAndSet;
        synchronized (this.mViewFactoryProvider) {
            try {
                AtomicReference<SysUiInlineViewFactory> atomicReference = this.mViewFactoryProvider.get(str);
                if (atomicReference == null) {
                    atomicReference = new AtomicReference<>();
                    this.mViewFactoryProvider.put(str, atomicReference);
                }
                zCompareAndSet = atomicReference.compareAndSet(null, sysUiInlineViewFactory);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zCompareAndSet;
    }
}
