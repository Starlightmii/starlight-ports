package j0;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object[] f3201f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k f3202g;

    public h(Object[] objArr, Object[] objArr2, int i5, int i6, int i7) {
        super(i5, i6);
        this.f3201f = objArr2;
        int i8 = (i6 - 1) & (-32);
        this.f3202g = new k(objArr, i5 > i8 ? i8 : i5, i8, i7);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        k kVar = this.f3202g;
        if (kVar.hasNext()) {
            this.f3182d++;
            return kVar.next();
        }
        int i5 = this.f3182d;
        this.f3182d = i5 + 1;
        return this.f3201f[i5 - kVar.f3183e];
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i5 = this.f3182d;
        k kVar = this.f3202g;
        int i6 = kVar.f3183e;
        if (i5 <= i6) {
            this.f3182d = i5 - 1;
            return kVar.previous();
        }
        int i7 = i5 - 1;
        this.f3182d = i7;
        return this.f3201f[i7 - i6];
    }
}
