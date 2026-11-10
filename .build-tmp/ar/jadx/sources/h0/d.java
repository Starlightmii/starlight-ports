package h0;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements ListIterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2575e;

    public d(int i5, List list) {
        this.f2574d = list;
        this.f2575e = i5;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final void add(Object obj) {
        this.f2574d.add(this.f2575e, obj);
        this.f2575e++;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f2575e < this.f2574d.size();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f2575e > 0;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i5 = this.f2575e;
        this.f2575e = i5 + 1;
        return this.f2574d.get(i5);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f2575e;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final Object previous() {
        int i5 = this.f2575e - 1;
        this.f2575e = i5;
        return this.f2574d.get(i5);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f2575e - 1;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i5 = this.f2575e - 1;
        this.f2575e = i5;
        this.f2574d.remove(i5);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final void set(Object obj) {
        this.f2574d.set(this.f2575e, obj);
    }
}
