package k0;

import e1.y;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends q3.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3370d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n0.g f3371e;

    public /* synthetic */ f(int i5, n0.g gVar) {
        this.f3370d = i5;
        this.f3371e = gVar;
    }

    @Override // q3.g
    public final int a() {
        switch (this.f3370d) {
            case 0:
                n0.g gVar = this.f3371e;
                gVar.getClass();
                return gVar.f4067h;
            default:
                n0.g gVar2 = this.f3371e;
                gVar2.getClass();
                return gVar2.f4067h;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.f3370d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f3370d) {
            case 0:
                this.f3371e.clear();
                break;
            default:
                this.f3371e.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.f3370d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                n0.g gVar = this.f3371e;
                Object obj2 = gVar.get(key);
                if (obj2 != null) {
                    return obj2.equals(entry.getValue());
                }
                return entry.getValue() == null && gVar.containsKey(entry.getKey());
            default:
                return this.f3371e.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f3370d) {
            case 0:
                return new y(this.f3371e);
            default:
                n[] nVarArr = new n[8];
                for (int i5 = 0; i5 < 8; i5++) {
                    nVarArr[i5] = new o(1);
                }
                return new g(this.f3371e, nVarArr);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.f3370d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return this.f3371e.remove(entry.getKey(), entry.getValue());
            default:
                n0.g gVar = this.f3371e;
                if (!gVar.containsKey(obj)) {
                    return false;
                }
                gVar.remove(obj);
                return true;
        }
    }
}
