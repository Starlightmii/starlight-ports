package p0;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements ListIterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4659d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f4660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f4661f;

    public a0(b4.u uVar, b0 b0Var) {
        this.f4660e = uVar;
        this.f4661f = b0Var;
    }

    public a0(q3.w wVar, int i5) {
        this.f4661f = wVar;
        List list = wVar.f5480d;
        if (i5 >= 0 && i5 <= wVar.a()) {
            this.f4660e = list.listIterator(wVar.a() - i5);
            return;
        }
        StringBuilder sbG = b.b.g("Position index ", i5, " must be in range [");
        sbG.append(new e4.d(0, wVar.a(), 1));
        sbG.append("].");
        throw new IndexOutOfBoundsException(sbG.toString());
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.f4659d) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.f4659d) {
            case 0:
                return ((b4.u) this.f4660e).f695d < ((b0) this.f4661f).f4675g - 1;
            default:
                return ((ListIterator) this.f4660e).hasPrevious();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.f4659d) {
            case 0:
                return ((b4.u) this.f4660e).f695d >= 0;
            default:
                return ((ListIterator) this.f4660e).hasNext();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f4659d) {
            case 0:
                b4.u uVar = (b4.u) this.f4660e;
                int i5 = uVar.f695d + 1;
                b0 b0Var = (b0) this.f4661f;
                r.a(i5, b0Var.f4675g);
                uVar.f695d = i5;
                return b0Var.get(i5);
            default:
                return ((ListIterator) this.f4660e).previous();
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.f4659d) {
            case 0:
                return ((b4.u) this.f4660e).f695d + 1;
            default:
                q3.w wVar = (q3.w) this.f4661f;
                return p3.j.z(wVar) - ((ListIterator) this.f4660e).previousIndex();
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f4659d) {
            case 0:
                b4.u uVar = (b4.u) this.f4660e;
                int i5 = uVar.f695d;
                b0 b0Var = (b0) this.f4661f;
                r.a(i5, b0Var.f4675g);
                uVar.f695d = i5 - 1;
                return b0Var.get(i5);
            default:
                return ((ListIterator) this.f4660e).next();
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        switch (this.f4659d) {
            case 0:
                return ((b4.u) this.f4660e).f695d;
            default:
                q3.w wVar = (q3.w) this.f4661f;
                return p3.j.z(wVar) - ((ListIterator) this.f4660e).nextIndex();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.f4659d) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.f4659d) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }
}
