package androidx.compose.foundation.layout;

import k2.e;
import p1.q0;
import r.k0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class PaddingElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f97a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f98b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f99c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f100d;

    public PaddingElement(float f5, float f6, float f7, float f8) {
        this.f97a = f5;
        this.f98b = f6;
        this.f99c = f7;
        this.f100d = f8;
        if ((f5 < 0.0f && !e.a(f5, Float.NaN)) || ((f6 < 0.0f && !e.a(f6, Float.NaN)) || ((f7 < 0.0f && !e.a(f7, Float.NaN)) || (f8 < 0.0f && !e.a(f8, Float.NaN))))) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
    }

    public final boolean equals(Object obj) {
        PaddingElement paddingElement = obj instanceof PaddingElement ? (PaddingElement) obj : null;
        return paddingElement != null && e.a(this.f97a, paddingElement.f97a) && e.a(this.f98b, paddingElement.f98b) && e.a(this.f99c, paddingElement.f99c) && e.a(this.f100d, paddingElement.f100d);
    }

    @Override // p1.q0
    public final o h() {
        k0 k0Var = new k0();
        k0Var.f5577q = this.f97a;
        k0Var.f5578r = this.f98b;
        k0Var.f5579s = this.f99c;
        k0Var.f5580t = this.f100d;
        k0Var.f5581u = true;
        return k0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + b.b.a(this.f100d, b.b.a(this.f99c, b.b.a(this.f98b, Float.hashCode(this.f97a) * 31, 31), 31), 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        k0 k0Var = (k0) oVar;
        k0Var.f5577q = this.f97a;
        k0Var.f5578r = this.f98b;
        k0Var.f5579s = this.f99c;
        k0Var.f5580t = this.f100d;
        k0Var.f5581u = true;
    }
}
