package androidx.compose.foundation.layout;

import k2.e;
import p1.q0;
import r.i0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class OffsetElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f94a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f95b;

    public OffsetElement(float f5, float f6) {
        this.f94a = f5;
        this.f95b = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        OffsetElement offsetElement = obj instanceof OffsetElement ? (OffsetElement) obj : null;
        return offsetElement != null && e.a(this.f94a, offsetElement.f94a) && e.a(this.f95b, offsetElement.f95b);
    }

    @Override // p1.q0
    public final o h() {
        i0 i0Var = new i0();
        i0Var.f5568q = this.f94a;
        i0Var.f5569r = this.f95b;
        i0Var.f5570s = true;
        return i0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + b.b.a(this.f95b, Float.hashCode(this.f94a) * 31, 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        i0 i0Var = (i0) oVar;
        i0Var.f5568q = this.f94a;
        i0Var.f5569r = this.f95b;
        i0Var.f5570s = true;
    }

    public final String toString() {
        return "OffsetModifierElement(x=" + ((Object) e.b(this.f94a)) + ", y=" + ((Object) e.b(this.f95b)) + ", rtlAware=true)";
    }
}
