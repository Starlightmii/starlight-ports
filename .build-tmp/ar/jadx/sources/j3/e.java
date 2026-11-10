package j3;

import android.os.Bundle;
import androidx.lifecycle.k0;
import b4.l;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k3.b f3341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f3342b;

    public e(k3.b bVar) {
        this.f3341a = bVar;
    }

    public final Bundle a(String str) {
        l.e("key", str);
        k3.b bVar = this.f3341a;
        if (!bVar.f3428b) {
            throw new IllegalStateException("You can 'consumeRestoredStateForKey' only after the corresponding component has moved to the 'CREATED' state");
        }
        Bundle bundle = (Bundle) bVar.f3434h;
        if (bundle == null) {
            return null;
        }
        Bundle bundleY = bundle.containsKey(str) ? a.a.y(str, bundle) : null;
        bundle.remove(str);
        if (bundle.isEmpty()) {
            bVar.f3434h = null;
        }
        return bundleY;
    }

    public final void b(String str, d dVar) {
        l.e("provider", dVar);
        k3.b bVar = this.f3341a;
        synchronized (((k0) bVar.f3432f)) {
            if (((LinkedHashMap) bVar.f3433g).containsKey(str)) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            ((LinkedHashMap) bVar.f3433g).put(str, dVar);
        }
    }

    public final void c() {
        if (!this.f3341a.f3429c) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        a aVar = this.f3342b;
        if (aVar == null) {
            aVar = new a(this);
        }
        this.f3342b = aVar;
        try {
            androidx.lifecycle.l.class.getDeclaredConstructor(null);
            a aVar2 = this.f3342b;
            if (aVar2 != null) {
                aVar2.f3338a.add(androidx.lifecycle.l.class.getName());
            }
        } catch (NoSuchMethodException e5) {
            throw new IllegalArgumentException("Class " + androidx.lifecycle.l.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e5);
        }
    }
}
