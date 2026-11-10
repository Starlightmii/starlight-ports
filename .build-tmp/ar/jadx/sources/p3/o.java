package p3;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o implements d, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b4.m f5027d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f5028e;

    /* JADX WARN: Type inference failed for: r0v2, types: [a4.a, b4.m, java.lang.Object] */
    @Override // p3.d
    public final Object getValue() {
        if (this.f5028e == m.f5025a) {
            ?? r1 = this.f5027d;
            b4.l.b(r1);
            this.f5028e = r1.a();
            this.f5027d = null;
        }
        return this.f5028e;
    }

    public final String toString() {
        return this.f5028e != m.f5025a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
