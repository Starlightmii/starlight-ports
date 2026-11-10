package androidx.compose.foundation.layout;

import k2.e;
import p1.q0;
import r.v0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class SizeElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f105d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f106e;

    public /* synthetic */ SizeElement(float f5, float f6, float f7, float f8, int i5) {
        this((i5 & 1) != 0 ? Float.NaN : f5, (i5 & 2) != 0 ? Float.NaN : f6, (i5 & 4) != 0 ? Float.NaN : f7, (i5 & 8) != 0 ? Float.NaN : f8, true);
    }

    public SizeElement(float f5, float f6, float f7, float f8, boolean z2) {
        this.f102a = f5;
        this.f103b = f6;
        this.f104c = f7;
        this.f105d = f8;
        this.f106e = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeElement)) {
            return false;
        }
        SizeElement sizeElement = (SizeElement) obj;
        return e.a(this.f102a, sizeElement.f102a) && e.a(this.f103b, sizeElement.f103b) && e.a(this.f104c, sizeElement.f104c) && e.a(this.f105d, sizeElement.f105d) && this.f106e == sizeElement.f106e;
    }

    @Override // p1.q0
    public final o h() {
        v0 v0Var = new v0();
        v0Var.f5632q = this.f102a;
        v0Var.f5633r = this.f103b;
        v0Var.f5634s = this.f104c;
        v0Var.f5635t = this.f105d;
        v0Var.f5636u = this.f106e;
        return v0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f106e) + b.b.a(this.f105d, b.b.a(this.f104c, b.b.a(this.f103b, Float.hashCode(this.f102a) * 31, 31), 31), 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        v0 v0Var = (v0) oVar;
        v0Var.f5632q = this.f102a;
        v0Var.f5633r = this.f103b;
        v0Var.f5634s = this.f104c;
        v0Var.f5635t = this.f105d;
        v0Var.f5636u = this.f106e;
    }
}
