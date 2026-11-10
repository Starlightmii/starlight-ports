package n0;

import f0.j3;
import f0.y1;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractMap implements Map, c4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m0.b f4063d = new m0.b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k0.m f4064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f4065f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4066g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4067h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h f4068i;

    public g(h hVar) {
        this.f4064e = hVar.f3361d;
        this.f4067h = hVar.f3362e;
        this.f4068i = hVar;
    }

    public final h a() {
        k0.m mVar = this.f4064e;
        h hVar = this.f4068i;
        if (mVar != hVar.f3361d) {
            this.f4063d = new m0.b();
            hVar = new h(this.f4064e, this.f4067h);
        }
        this.f4068i = hVar;
        return hVar;
    }

    public final boolean b(Object obj) {
        return this.f4064e.d(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    public final Object c(Object obj) {
        return this.f4064e.g(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f4064e = k0.m.f3379e;
        e(0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof y1) {
            return b((y1) obj);
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof j3) {
            return super.containsValue((j3) obj);
        }
        return false;
    }

    public final Object d(Object obj) {
        this.f4065f = null;
        k0.m mVarN = this.f4064e.n(obj != null ? obj.hashCode() : 0, obj, 0, this);
        if (mVarN == null) {
            mVarN = k0.m.f3379e;
        }
        this.f4064e = mVarN;
        return this.f4065f;
    }

    public final void e(int i5) {
        this.f4067h = i5;
        this.f4066g++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new k0.f(0, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof y1) {
            return (j3) c((y1) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof y1) ? obj2 : (j3) super.getOrDefault((y1) obj, (j3) obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new k0.f(1, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.f4065f = null;
        this.f4064e = this.f4064e.l(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        return this.f4065f;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        k0.c cVarA = null;
        k0.c cVar = map instanceof k0.c ? (k0.c) map : null;
        if (cVar == null) {
            g gVar = map instanceof g ? (g) map : null;
            if (gVar != null) {
                cVarA = gVar.a();
            }
        } else {
            cVarA = cVar;
        }
        if (cVarA == null) {
            super.putAll(map);
            return;
        }
        m0.a aVar = new m0.a();
        aVar.f3810a = 0;
        int i5 = this.f4067h;
        k0.m mVar = this.f4064e;
        k0.m mVar2 = cVarA.f3361d;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>", mVar2);
        this.f4064e = mVar.m(mVar2, 0, aVar, this);
        int i6 = (cVarA.f3362e + i5) - aVar.f3810a;
        if (i5 != i6) {
            e(i6);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj instanceof y1) {
            return (j3) d((y1) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int i5 = this.f4067h;
        k0.m mVarO = this.f4064e.o(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        if (mVarO == null) {
            mVarO = k0.m.f3379e;
        }
        this.f4064e = mVarO;
        return i5 != this.f4067h;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f4067h;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return new k0.h(0, this);
    }
}
