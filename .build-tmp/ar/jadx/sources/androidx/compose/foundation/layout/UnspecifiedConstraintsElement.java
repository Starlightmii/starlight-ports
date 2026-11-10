package androidx.compose.foundation.layout;

import k2.e;
import p1.q0;
import r.x0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class UnspecifiedConstraintsElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f108b;

    public UnspecifiedConstraintsElement(float f5, float f6) {
        this.f107a = f5;
        this.f108b = f6;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UnspecifiedConstraintsElement)) {
            return false;
        }
        UnspecifiedConstraintsElement unspecifiedConstraintsElement = (UnspecifiedConstraintsElement) obj;
        return e.a(this.f107a, unspecifiedConstraintsElement.f107a) && e.a(this.f108b, unspecifiedConstraintsElement.f108b);
    }

    @Override // p1.q0
    public final o h() {
        x0 x0Var = new x0();
        x0Var.f5640q = this.f107a;
        x0Var.f5641r = this.f108b;
        return x0Var;
    }

    public final int hashCode() {
        return Float.hashCode(this.f108b) + (Float.hashCode(this.f107a) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        x0 x0Var = (x0) oVar;
        x0Var.f5640q = this.f107a;
        x0Var.f5641r = this.f108b;
    }
}
