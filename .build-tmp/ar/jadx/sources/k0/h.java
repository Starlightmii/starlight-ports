package k0;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends AbstractCollection implements Collection, c4.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3372d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f3373e;

    public /* synthetic */ h(int i5, Object obj) {
        this.f3372d = i5;
        this.f3373e = obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.f3372d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection collection) {
        switch (this.f3372d) {
            case 1:
                b4.l.e("elements", collection);
                throw new UnsupportedOperationException();
            default:
                return super.addAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        switch (this.f3372d) {
            case 0:
                ((n0.g) this.f3373e).clear();
                break;
            default:
                ((r3.f) this.f3373e).clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.f3372d) {
            case 0:
                return ((n0.g) this.f3373e).containsValue(obj);
            default:
                return ((r3.f) this.f3373e).containsValue(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        switch (this.f3372d) {
            case 1:
                return ((r3.f) this.f3373e).isEmpty();
            default:
                return super.isEmpty();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.f3372d) {
            case 0:
                n0.g gVar = (n0.g) this.f3373e;
                n[] nVarArr = new n[8];
                for (int i5 = 0; i5 < 8; i5++) {
                    nVarArr[i5] = new o(2);
                }
                return new g(gVar, nVarArr);
            default:
                r3.f fVar = (r3.f) this.f3373e;
                fVar.getClass();
                return new r3.d(fVar, 2);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        switch (this.f3372d) {
            case 1:
                r3.f fVar = (r3.f) this.f3373e;
                fVar.b();
                int iH = fVar.h(obj);
                if (iH < 0) {
                    return false;
                }
                fVar.k(iH);
                return true;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        switch (this.f3372d) {
            case 1:
                b4.l.e("elements", collection);
                ((r3.f) this.f3373e).b();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        switch (this.f3372d) {
            case 1:
                b4.l.e("elements", collection);
                ((r3.f) this.f3373e).b();
                break;
        }
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        switch (this.f3372d) {
            case 0:
                n0.g gVar = (n0.g) this.f3373e;
                gVar.getClass();
                return gVar.f4067h;
            default:
                return ((r3.f) this.f3373e).f5733l;
        }
    }
}
