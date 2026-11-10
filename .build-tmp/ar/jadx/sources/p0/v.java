package p0;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v implements ListIterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4758d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4759e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4760f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4761g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f4762h;

    public v(q qVar, int i5) {
        this.f4758d = 0;
        this.f4762h = qVar;
        this.f4759e = i5 - 1;
        this.f4760f = -1;
        this.f4761g = r.f(qVar);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public v(p1.r rVar, int i5, int i6) {
        this(rVar, (i6 & 1) != 0 ? 0 : i5, 0, rVar.f4960g);
        this.f4758d = 1;
    }

    public v(p1.r rVar, int i5, int i6, int i7) {
        this.f4758d = 1;
        this.f4762h = rVar;
        this.f4759e = i5;
        this.f4760f = i6;
        this.f4761g = i7;
    }

    public v(r3.a aVar, int i5) {
        this.f4758d = 2;
        this.f4762h = aVar;
        this.f4759e = i5;
        this.f4760f = -1;
        this.f4761g = ((AbstractList) aVar).modCount;
    }

    public v(r3.b bVar, int i5) {
        this.f4758d = 3;
        this.f4762h = bVar;
        this.f4759e = i5;
        this.f4760f = -1;
        this.f4761g = ((AbstractList) bVar).modCount;
    }

    public void a() {
        if (((AbstractList) ((r3.a) this.f4762h).f5714h).modCount != this.f4761g) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.f4758d) {
            case 0:
                c();
                q qVar = (q) this.f4762h;
                qVar.add(this.f4759e + 1, obj);
                this.f4760f = -1;
                this.f4759e++;
                this.f4761g = r.f(qVar);
                return;
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 2:
                a();
                r3.a aVar = (r3.a) this.f4762h;
                int i5 = this.f4759e;
                this.f4759e = i5 + 1;
                aVar.add(i5, obj);
                this.f4760f = -1;
                this.f4761g = ((AbstractList) aVar).modCount;
                return;
            default:
                b();
                r3.b bVar = (r3.b) this.f4762h;
                int i6 = this.f4759e;
                this.f4759e = i6 + 1;
                bVar.add(i6, obj);
                this.f4760f = -1;
                this.f4761g = ((AbstractList) bVar).modCount;
                return;
        }
    }

    public void b() {
        if (((AbstractList) ((r3.b) this.f4762h)).modCount != this.f4761g) {
            throw new ConcurrentModificationException();
        }
    }

    public void c() {
        if (r.f((q) this.f4762h) != this.f4761g) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.f4758d) {
            case 0:
                return this.f4759e < ((q) this.f4762h).size() - 1;
            case 1:
                return this.f4759e < this.f4761g;
            case 2:
                return this.f4759e < ((r3.a) this.f4762h).f5712f;
            default:
                return this.f4759e < ((r3.b) this.f4762h).f5717e;
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.f4758d) {
            case 0:
                return this.f4759e >= 0;
            case 1:
                return this.f4759e > this.f4760f;
            case 2:
                return this.f4759e > 0;
            default:
                return this.f4759e > 0;
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f4758d) {
            case 0:
                c();
                int i5 = this.f4759e + 1;
                this.f4760f = i5;
                q qVar = (q) this.f4762h;
                r.a(i5, qVar.size());
                Object obj = qVar.get(i5);
                this.f4759e = i5;
                return obj;
            case 1:
                Object[] objArr = ((p1.r) this.f4762h).f4957d;
                int i6 = this.f4759e;
                this.f4759e = i6 + 1;
                Object obj2 = objArr[i6];
                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.Modifier.Node", obj2);
                return (r0.o) obj2;
            case 2:
                a();
                int i7 = this.f4759e;
                r3.a aVar = (r3.a) this.f4762h;
                if (i7 >= aVar.f5712f) {
                    throw new NoSuchElementException();
                }
                this.f4759e = i7 + 1;
                this.f4760f = i7;
                return aVar.f5710d[aVar.f5711e + i7];
            default:
                b();
                int i8 = this.f4759e;
                r3.b bVar = (r3.b) this.f4762h;
                if (i8 >= bVar.f5717e) {
                    throw new NoSuchElementException();
                }
                this.f4759e = i8 + 1;
                this.f4760f = i8;
                return bVar.f5716d[i8];
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.f4758d) {
            case 0:
                return this.f4759e + 1;
            case 1:
                return this.f4759e - this.f4760f;
            case 2:
                return this.f4759e;
            default:
                return this.f4759e;
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f4758d) {
            case 0:
                c();
                int i5 = this.f4759e;
                q qVar = (q) this.f4762h;
                r.a(i5, qVar.size());
                int i6 = this.f4759e;
                this.f4760f = i6;
                Object obj = qVar.get(i6);
                this.f4759e--;
                return obj;
            case 1:
                Object[] objArr = ((p1.r) this.f4762h).f4957d;
                int i7 = this.f4759e - 1;
                this.f4759e = i7;
                Object obj2 = objArr[i7];
                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.Modifier.Node", obj2);
                return (r0.o) obj2;
            case 2:
                a();
                int i8 = this.f4759e;
                if (i8 <= 0) {
                    throw new NoSuchElementException();
                }
                int i9 = i8 - 1;
                this.f4759e = i9;
                this.f4760f = i9;
                r3.a aVar = (r3.a) this.f4762h;
                return aVar.f5710d[aVar.f5711e + i9];
            default:
                b();
                int i10 = this.f4759e;
                if (i10 <= 0) {
                    throw new NoSuchElementException();
                }
                int i11 = i10 - 1;
                this.f4759e = i11;
                this.f4760f = i11;
                return ((r3.b) this.f4762h).f5716d[i11];
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        int i5;
        switch (this.f4758d) {
            case 0:
                return this.f4759e;
            case 1:
                i5 = this.f4759e - this.f4760f;
                break;
            case 2:
                i5 = this.f4759e;
                break;
            default:
                i5 = this.f4759e;
                break;
        }
        return i5 - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.f4758d) {
            case 0:
                c();
                q qVar = (q) this.f4762h;
                qVar.remove(this.f4760f);
                this.f4759e--;
                this.f4760f = -1;
                this.f4761g = r.f(qVar);
                return;
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 2:
                r3.a aVar = (r3.a) this.f4762h;
                a();
                int i5 = this.f4760f;
                if (i5 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                aVar.b(i5);
                this.f4759e = this.f4760f;
                this.f4760f = -1;
                this.f4761g = ((AbstractList) aVar).modCount;
                return;
            default:
                r3.b bVar = (r3.b) this.f4762h;
                b();
                int i6 = this.f4760f;
                if (i6 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                bVar.b(i6);
                this.f4759e = this.f4760f;
                this.f4760f = -1;
                this.f4761g = ((AbstractList) bVar).modCount;
                return;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.f4758d) {
            case 0:
                q qVar = (q) this.f4762h;
                c();
                int i5 = this.f4760f;
                if (i5 < 0) {
                    throw new IllegalStateException("Cannot call set before the first call to next() or previous() or immediately after a call to add() or remove()");
                }
                qVar.set(i5, obj);
                this.f4761g = r.f(qVar);
                return;
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 2:
                a();
                int i6 = this.f4760f;
                if (i6 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((r3.a) this.f4762h).set(i6, obj);
                return;
            default:
                b();
                int i7 = this.f4760f;
                if (i7 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((r3.b) this.f4762h).set(i7, obj);
                return;
        }
    }
}
