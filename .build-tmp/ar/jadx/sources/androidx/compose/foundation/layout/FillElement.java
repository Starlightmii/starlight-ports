package androidx.compose.foundation.layout;

import m.h;
import p1.q0;
import r.a0;
import r0.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class FillElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f91a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f92b;

    public FillElement(float f5, int i5) {
        this.f91a = i5;
        this.f92b = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FillElement)) {
            return false;
        }
        FillElement fillElement = (FillElement) obj;
        return this.f91a == fillElement.f91a && this.f92b == fillElement.f92b;
    }

    @Override // p1.q0
    public final o h() {
        a0 a0Var = new a0();
        a0Var.f5504q = this.f91a;
        a0Var.f5505r = this.f92b;
        return a0Var;
    }

    public final int hashCode() {
        return Float.hashCode(this.f92b) + (h.c(this.f91a) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        a0 a0Var = (a0) oVar;
        a0Var.f5504q = this.f91a;
        a0Var.f5505r = this.f92b;
    }
}
