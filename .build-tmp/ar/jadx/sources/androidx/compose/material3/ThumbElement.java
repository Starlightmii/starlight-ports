package androidx.compose.material3;

import b4.l;
import c0.r4;
import m.d;
import p1.f;
import p1.q0;
import q.j;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class ThumbElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f151b;

    public ThumbElement(j jVar, boolean z2) {
        this.f150a = jVar;
        this.f151b = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ThumbElement)) {
            return false;
        }
        ThumbElement thumbElement = (ThumbElement) obj;
        return l.a(this.f150a, thumbElement.f150a) && this.f151b == thumbElement.f151b;
    }

    @Override // p1.q0
    public final o h() {
        r4 r4Var = new r4();
        r4Var.f1237q = this.f150a;
        r4Var.f1238r = this.f151b;
        r4Var.f1242v = Float.NaN;
        r4Var.f1243w = Float.NaN;
        return r4Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f151b) + (this.f150a.hashCode() * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        r4 r4Var = (r4) oVar;
        r4Var.f1237q = this.f150a;
        boolean z2 = r4Var.f1238r;
        boolean z3 = this.f151b;
        if (z2 != z3) {
            f.m(r4Var);
        }
        r4Var.f1238r = z3;
        if (r4Var.f1241u == null && !Float.isNaN(r4Var.f1243w)) {
            r4Var.f1241u = d.a(r4Var.f1243w);
        }
        if (r4Var.f1240t != null || Float.isNaN(r4Var.f1242v)) {
            return;
        }
        r4Var.f1240t = d.a(r4Var.f1242v);
    }

    public final String toString() {
        return "ThumbElement(interactionSource=" + this.f150a + ", checked=" + this.f151b + ')';
    }
}
