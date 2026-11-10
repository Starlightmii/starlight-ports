package r3;

import b4.l;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Map.Entry, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f5721d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5722e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5723f;

    public e(f fVar, int i5) {
        l.e("map", fVar);
        this.f5721d = fVar;
        this.f5722e = i5;
        this.f5723f = fVar.f5732k;
    }

    public final void a() {
        if (this.f5721d.f5732k != this.f5723f) {
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return l.a(entry.getKey(), getKey()) && l.a(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        a();
        return this.f5721d.f5725d[this.f5722e];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        a();
        Object[] objArr = this.f5721d.f5726e;
        l.b(objArr);
        return objArr[this.f5722e];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        a();
        f fVar = this.f5721d;
        fVar.b();
        Object[] objArr = fVar.f5726e;
        if (objArr == null) {
            int length = fVar.f5725d.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            fVar.f5726e = objArr;
        }
        int i5 = this.f5722e;
        Object obj2 = objArr[i5];
        objArr[i5] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
