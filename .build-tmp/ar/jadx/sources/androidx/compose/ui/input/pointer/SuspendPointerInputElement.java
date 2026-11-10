package androidx.compose.ui.input.pointer;

import a4.e;
import b4.l;
import j1.d0;
import p1.q0;
import r0.o;
import v.u0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class SuspendPointerInputElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f198c;

    public SuspendPointerInputElement(Object obj, u0 u0Var, e eVar, int i5) {
        u0Var = (i5 & 2) != 0 ? null : u0Var;
        this.f196a = obj;
        this.f197b = u0Var;
        this.f198c = eVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuspendPointerInputElement)) {
            return false;
        }
        SuspendPointerInputElement suspendPointerInputElement = (SuspendPointerInputElement) obj;
        return l.a(this.f196a, suspendPointerInputElement.f196a) && l.a(this.f197b, suspendPointerInputElement.f197b) && this.f198c == suspendPointerInputElement.f198c;
    }

    @Override // p1.q0
    public final o h() {
        return new d0(this.f196a, this.f197b, this.f198c);
    }

    public final int hashCode() {
        Object obj = this.f196a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.f197b;
        return this.f198c.hashCode() + ((iHashCode + (obj2 != null ? obj2.hashCode() : 0)) * 961);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        d0 d0Var = (d0) oVar;
        Object obj = d0Var.f3232q;
        Object obj2 = this.f196a;
        boolean z2 = !l.a(obj, obj2);
        d0Var.f3232q = obj2;
        Object obj3 = d0Var.f3233r;
        Object obj4 = this.f197b;
        boolean z3 = l.a(obj3, obj4) ? z2 : true;
        d0Var.f3233r = obj4;
        if (z3) {
            d0Var.A0();
        }
        d0Var.f3234s = this.f198c;
    }
}
