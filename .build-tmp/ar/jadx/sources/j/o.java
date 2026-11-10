package j;

import androidx.lifecycle.k0;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.d f3125b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k0 f3126c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3127d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3128e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3129f;

    public o(int i5) {
        this.f3124a = i5;
        if (i5 <= 0) {
            k.a.c("maxSize <= 0");
            throw null;
        }
        this.f3125b = new a1.d(10);
        this.f3126c = new k0(23);
    }

    public final Object a(Object obj) {
        b4.l.e("key", obj);
        synchronized (this.f3126c) {
            a1.d dVar = this.f3125b;
            dVar.getClass();
            Object obj2 = ((LinkedHashMap) dVar.f31e).get(obj);
            if (obj2 != null) {
                this.f3128e++;
                return obj2;
            }
            this.f3129f++;
            return null;
        }
    }

    public final void b(Object obj, Object obj2) {
        b4.l.e("key", obj);
        synchronized (this.f3126c) {
            this.f3127d++;
            a1.d dVar = this.f3125b;
            dVar.getClass();
            if (((LinkedHashMap) dVar.f31e).put(obj, obj2) != null) {
                this.f3127d--;
            }
        }
        int i5 = this.f3124a;
        while (true) {
            synchronized (this.f3126c) {
                try {
                    if (this.f3127d < 0 || (((LinkedHashMap) this.f3125b.f31e).isEmpty() && this.f3127d != 0)) {
                        break;
                    }
                    if (this.f3127d > i5 && !((LinkedHashMap) this.f3125b.f31e).isEmpty()) {
                        Set setEntrySet = ((LinkedHashMap) this.f3125b.f31e).entrySet();
                        b4.l.d("<get-entries>(...)", setEntrySet);
                        Set set = setEntrySet;
                        Object next = null;
                        if (set instanceof List) {
                            List list = (List) set;
                            if (!list.isEmpty()) {
                                next = list.get(0);
                            }
                        } else {
                            Iterator it = set.iterator();
                            if (it.hasNext()) {
                                next = it.next();
                            }
                        }
                        Map.Entry entry = (Map.Entry) next;
                        if (entry == null) {
                            return;
                        }
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        a1.d dVar2 = this.f3125b;
                        dVar2.getClass();
                        b4.l.e("key", key);
                        ((LinkedHashMap) dVar2.f31e).remove(key);
                        int i6 = this.f3127d;
                        b4.l.e("value", value);
                        this.f3127d = i6 - 1;
                    }
                    return;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        throw new IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
    }

    public final String toString() {
        String str;
        synchronized (this.f3126c) {
            try {
                int i5 = this.f3128e;
                int i6 = this.f3129f + i5;
                str = "LruCache[maxSize=" + this.f3124a + ",hits=" + this.f3128e + ",misses=" + this.f3129f + ",hitRate=" + (i6 != 0 ? (i5 * 100) / i6 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
