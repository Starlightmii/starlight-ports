package b4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class b implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f676d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f678f;

    public b(q3.d dVar) {
        this.f678f = dVar;
    }

    public b(Object[] objArr) {
        l.e("array", objArr);
        this.f678f = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f676d) {
            case 0:
                return this.f677e < ((Object[]) this.f678f).length;
            default:
                return this.f677e < ((q3.d) this.f678f).a();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f676d) {
            case 0:
                try {
                    Object[] objArr = (Object[]) this.f678f;
                    int i5 = this.f677e;
                    this.f677e = i5 + 1;
                    return objArr[i5];
                } catch (ArrayIndexOutOfBoundsException e5) {
                    this.f677e--;
                    throw new NoSuchElementException(e5.getMessage());
                }
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                q3.d dVar = (q3.d) this.f678f;
                int i6 = this.f677e;
                this.f677e = i6 + 1;
                return dVar.get(i6);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f676d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }
}
