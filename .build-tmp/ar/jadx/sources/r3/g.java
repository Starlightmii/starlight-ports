package r3;

import b4.l;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends q3.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5738d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f5739e;

    public /* synthetic */ g(f fVar, int i5) {
        this.f5738d = i5;
        this.f5739e = fVar;
    }

    @Override // q3.g
    public final int a() {
        switch (this.f5738d) {
            case 0:
                break;
        }
        return this.f5739e.f5733l;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.f5738d) {
            case 0:
                l.e("element", (Map.Entry) obj);
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.f5738d) {
            case 0:
                l.e("elements", collection);
                throw new UnsupportedOperationException();
            default:
                l.e("elements", collection);
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f5738d) {
            case 0:
                this.f5739e.clear();
                break;
            default:
                this.f5739e.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.f5738d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                return this.f5739e.e((Map.Entry) obj);
            default:
                return this.f5739e.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.f5738d) {
            case 0:
                l.e("elements", collection);
                return this.f5739e.d(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.f5738d) {
            case 0:
                break;
        }
        return this.f5739e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f5738d) {
            case 0:
                f fVar = this.f5739e;
                fVar.getClass();
                return new d(fVar, 0);
            default:
                f fVar2 = this.f5739e;
                fVar2.getClass();
                return new d(fVar2, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.f5738d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                f fVar = this.f5739e;
                fVar.getClass();
                fVar.b();
                int iG = fVar.g(entry.getKey());
                if (iG < 0) {
                    return false;
                }
                Object[] objArr = fVar.f5726e;
                l.b(objArr);
                if (!l.a(objArr[iG], entry.getValue())) {
                    return false;
                }
                fVar.k(iG);
                return true;
            default:
                f fVar2 = this.f5739e;
                fVar2.b();
                int iG2 = fVar2.g(obj);
                if (iG2 < 0) {
                    return false;
                }
                fVar2.k(iG2);
                return true;
        }
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.f5738d) {
            case 0:
                l.e("elements", collection);
                this.f5739e.b();
                break;
            default:
                l.e("elements", collection);
                this.f5739e.b();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.f5738d) {
            case 0:
                l.e("elements", collection);
                this.f5739e.b();
                break;
            default:
                l.e("elements", collection);
                this.f5739e.b();
                break;
        }
        return super.retainAll(collection);
    }
}
