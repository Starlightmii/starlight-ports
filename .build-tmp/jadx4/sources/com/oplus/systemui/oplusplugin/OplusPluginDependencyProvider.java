package com.oplus.systemui.oplusplugin;

import android.util.ArrayMap;
import com.android.systemui.plugins.OplusPluginDependency;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginManager;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPluginDependencyProvider extends OplusPluginDependency.DependencyProvider {
    public final ArrayMap mDependencies = new ArrayMap();
    public final Lazy mManagerLazy;

    public OplusPluginDependencyProvider(Lazy lazy) {
        this.mManagerLazy = lazy;
        OplusPluginDependency.setProvider(this);
    }

    public final void allowPluginDependency(Class cls, Object obj) {
        synchronized (this.mDependencies) {
            this.mDependencies.put(cls, obj);
        }
    }

    public final Object get(Plugin plugin, Class cls) {
        Object obj;
        if (!((PluginManager) this.mManagerLazy.get()).dependsOn(plugin, cls)) {
            throw new IllegalArgumentException(plugin.getClass() + " does not depend on " + cls);
        }
        synchronized (this.mDependencies) {
            try {
                if (!this.mDependencies.containsKey(cls)) {
                    throw new IllegalArgumentException("Unknown dependency " + cls);
                }
                obj = this.mDependencies.get(cls);
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public final Object get(Class cls) {
        Object obj;
        synchronized (this.mDependencies) {
            try {
                if (!this.mDependencies.containsKey(cls)) {
                    throw new IllegalArgumentException("Unknown dependency " + cls);
                }
                obj = this.mDependencies.get(cls);
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }
}
