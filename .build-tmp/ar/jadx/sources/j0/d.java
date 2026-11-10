package j0;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f3186f = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f3187g;

    public d(int i5, Object obj) {
        super(i5, 1);
        this.f3187g = obj;
    }

    public d(Object[] objArr, int i5, int i6) {
        super(i5, i6);
        this.f3187g = objArr;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f3186f) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object[] objArr = (Object[]) this.f3187g;
                int i5 = this.f3182d;
                this.f3182d = i5 + 1;
                return objArr[i5];
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.f3182d++;
                return this.f3187g;
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f3186f) {
            case 0:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                Object[] objArr = (Object[]) this.f3187g;
                int i5 = this.f3182d - 1;
                this.f3182d = i5;
                return objArr[i5];
            default:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                this.f3182d--;
                return this.f3187g;
        }
    }
}
