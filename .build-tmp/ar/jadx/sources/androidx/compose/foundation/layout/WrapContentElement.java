package androidx.compose.foundation.layout;

import a4.e;
import b4.m;
import m.h;
import p1.q0;
import r.e1;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class WrapContentElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f111c;

    /* JADX WARN: Multi-variable type inference failed */
    public WrapContentElement(int i5, e eVar, Object obj) {
        this.f109a = i5;
        this.f110b = (m) eVar;
        this.f111c = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WrapContentElement.class != obj.getClass()) {
            return false;
        }
        WrapContentElement wrapContentElement = (WrapContentElement) obj;
        return this.f109a == wrapContentElement.f109a && this.f111c.equals(wrapContentElement.f111c);
    }

    @Override // p1.q0
    public final o h() {
        e1 e1Var = new e1();
        e1Var.f5557q = this.f109a;
        e1Var.f5558r = this.f110b;
        return e1Var;
    }

    public final int hashCode() {
        return this.f111c.hashCode() + b.b.c(h.c(this.f109a) * 31, 31, false);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        e1 e1Var = (e1) oVar;
        e1Var.f5557q = this.f109a;
        e1Var.f5558r = this.f110b;
    }
}
