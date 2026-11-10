package j;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator, Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3092e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3093f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ f f3094g;

    public d(f fVar) {
        this.f3094g = fVar;
        this.f3091d = fVar.f3110f - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f3093f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i5 = this.f3092e;
        f fVar = this.f3094g;
        return b4.l.a(key, fVar.e(i5)) && b4.l.a(entry.getValue(), fVar.h(this.f3092e));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (this.f3093f) {
            return this.f3094g.e(this.f3092e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.f3093f) {
            return this.f3094g.h(this.f3092e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f3092e < this.f3091d;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f3093f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i5 = this.f3092e;
        f fVar = this.f3094g;
        Object objE = fVar.e(i5);
        Object objH = fVar.h(this.f3092e);
        return (objE == null ? 0 : objE.hashCode()) ^ (objH != null ? objH.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f3092e++;
        this.f3093f = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f3093f) {
            throw new IllegalStateException();
        }
        this.f3094g.f(this.f3092e);
        this.f3092e--;
        this.f3091d--;
        this.f3093f = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f3093f) {
            return this.f3094g.g(this.f3092e, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
