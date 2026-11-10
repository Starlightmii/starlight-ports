package androidx.compose.foundation.layout;

import p1.q0;
import r.g0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class LayoutWeightElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f93a;

    public LayoutWeightElement(float f5) {
        this.f93a = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        LayoutWeightElement layoutWeightElement = obj instanceof LayoutWeightElement ? (LayoutWeightElement) obj : null;
        return layoutWeightElement != null && this.f93a == layoutWeightElement.f93a;
    }

    @Override // p1.q0
    public final o h() {
        g0 g0Var = new g0();
        g0Var.f5563q = this.f93a;
        g0Var.f5564r = true;
        return g0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (Float.hashCode(this.f93a) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        g0 g0Var = (g0) oVar;
        g0Var.f5563q = this.f93a;
        g0Var.f5564r = true;
    }
}
