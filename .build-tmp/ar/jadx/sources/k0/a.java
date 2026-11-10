package k0;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class a implements Map.Entry, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f3356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f3357e;

    public a(Object obj, Object obj2) {
        this.f3356d = obj;
        this.f3357e = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Map.Entry entry = obj instanceof Map.Entry ? (Map.Entry) obj : null;
        return entry != null && b4.l.a(entry.getKey(), this.f3356d) && b4.l.a(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f3356d;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.f3357e;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f3356d;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        Object value = getValue();
        return (value != null ? value.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3356d);
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
