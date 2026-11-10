package androidx.compose.foundation;

import b4.l;
import n.j;
import p1.q0;
import r0.o;
import y0.e0;
import y0.p;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class BackgroundElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f52a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f53b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f54c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y0.q0 f55d;

    public BackgroundElement(long j5, e0 e0Var, y0.q0 q0Var, int i5) {
        j5 = (i5 & 1) != 0 ? u.f6694g : j5;
        e0Var = (i5 & 2) != 0 ? null : e0Var;
        this.f52a = j5;
        this.f53b = e0Var;
        this.f54c = 1.0f;
        this.f55d = q0Var;
    }

    public final boolean equals(Object obj) {
        BackgroundElement backgroundElement = obj instanceof BackgroundElement ? (BackgroundElement) obj : null;
        return backgroundElement != null && u.c(this.f52a, backgroundElement.f52a) && l.a(this.f53b, backgroundElement.f53b) && this.f54c == backgroundElement.f54c && l.a(this.f55d, backgroundElement.f55d);
    }

    @Override // p1.q0
    public final o h() {
        j jVar = new j();
        jVar.f3936q = this.f52a;
        jVar.f3937r = this.f53b;
        jVar.f3938s = this.f54c;
        jVar.f3939t = this.f55d;
        jVar.f3940u = 9205357640488583168L;
        return jVar;
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        int iHashCode = Long.hashCode(this.f52a) * 31;
        p pVar = this.f53b;
        return this.f55d.hashCode() + b.b.a(this.f54c, (iHashCode + (pVar != null ? pVar.hashCode() : 0)) * 31, 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        j jVar = (j) oVar;
        jVar.f3936q = this.f52a;
        jVar.f3937r = this.f53b;
        jVar.f3938s = this.f54c;
        jVar.f3939t = this.f55d;
    }
}
