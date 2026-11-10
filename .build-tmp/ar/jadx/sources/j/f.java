package j;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends h0 implements Map {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f3099g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f3100h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e f3101i;

    @Override // java.util.Map
    public final Set entrySet() {
        a aVar = this.f3099g;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this);
        this.f3099g = aVar2;
        return aVar2;
    }

    public final boolean i(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!super.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final boolean j(Collection collection) {
        int i5 = this.f3110f;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            super.remove(it.next());
        }
        return i5 != this.f3110f;
    }

    @Override // java.util.Map
    public final Set keySet() {
        c cVar = this.f3100h;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this);
        this.f3100h = cVar2;
        return cVar2;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size() + this.f3110f;
        int i5 = this.f3110f;
        int[] iArr = this.f3108d;
        if (iArr.length < size) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, size);
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f3108d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f3109e, size * 2);
            b4.l.d("copyOf(...)", objArrCopyOf);
            this.f3109e = objArrCopyOf;
        }
        if (this.f3110f != i5) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        e eVar = this.f3101i;
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e(this);
        this.f3101i = eVar2;
        return eVar2;
    }
}
