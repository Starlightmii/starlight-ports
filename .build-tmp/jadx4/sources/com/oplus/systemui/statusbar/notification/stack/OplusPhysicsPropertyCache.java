package com.oplus.systemui.statusbar.notification.stack;

import android.util.Property;
import android.view.View;
import androidx.collection.LruCache;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhysicsPropertyCache {
    public static final OplusPhysicsPropertyCache INSTANCE = new OplusPhysicsPropertyCache();
    private static final Lazy lruCache$delegate = LazyKt__LazyJVMKt.lazy(new OplusPhysicsPropertyCache$$ExternalSyntheticLambda0());

    private final LruCache getLruCache() {
        return (LruCache) lruCache$delegate.getValue();
    }

    public final void clear() {
        getLruCache().trimToSize(-1);
    }

    public final OplusPhysicsProperty getOrCreate(int i, Property<View, Float> property, float f) {
        int iHashCode = (Float.hashCode(f) * 31) + Integer.hashCode(i);
        OplusPhysicsProperty oplusPhysicsProperty = (OplusPhysicsProperty) getLruCache().get(Integer.valueOf(iHashCode));
        if (oplusPhysicsProperty != null) {
            return oplusPhysicsProperty;
        }
        OplusPhysicsProperty oplusPhysicsProperty2 = new OplusPhysicsProperty(i, property, f);
        INSTANCE.getLruCache().put(Integer.valueOf(iHashCode), oplusPhysicsProperty2);
        return oplusPhysicsProperty2;
    }
}
