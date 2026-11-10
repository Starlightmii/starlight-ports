package u;

import b4.l;
import k2.k;
import p3.p;
import x0.f;
import y0.h0;
import y0.i0;
import y0.j0;
import y0.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements q0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f5812d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f5813e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f5814f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5815g;

    public d(a aVar, a aVar2, a aVar3, a aVar4) {
        this.f5812d = aVar;
        this.f5813e = aVar2;
        this.f5814f = aVar3;
        this.f5815g = aVar4;
    }

    public static d a(d dVar, b bVar, b bVar2, b bVar3, int i5) {
        a aVar = bVar;
        if ((i5 & 1) != 0) {
            aVar = dVar.f5812d;
        }
        a aVar2 = dVar.f5813e;
        a aVar3 = bVar2;
        if ((i5 & 4) != 0) {
            aVar3 = dVar.f5814f;
        }
        dVar.getClass();
        return new d(aVar, aVar2, aVar3, bVar3);
    }

    @Override // y0.q0
    public final j0 c(long j5, k kVar, k2.b bVar) {
        float fA = this.f5812d.a(j5, bVar);
        float fA2 = this.f5813e.a(j5, bVar);
        float fA3 = this.f5814f.a(j5, bVar);
        float fA4 = this.f5815g.a(j5, bVar);
        float fC = f.c(j5);
        float f5 = fA + fA4;
        if (f5 > fC) {
            float f6 = fC / f5;
            fA *= f6;
            fA4 *= f6;
        }
        float f7 = fA2 + fA3;
        if (f7 > fC) {
            float f8 = fC / f7;
            fA2 *= f8;
            fA3 *= f8;
        }
        if (fA < 0.0f || fA2 < 0.0f || fA3 < 0.0f || fA4 < 0.0f) {
            throw new IllegalArgumentException(("Corner size in Px can't be negative(topStart = " + fA + ", topEnd = " + fA2 + ", bottomEnd = " + fA3 + ", bottomStart = " + fA4 + ")!").toString());
        }
        if (fA + fA2 + fA3 + fA4 == 0.0f) {
            return new h0(p.b(0L, j5));
        }
        x0.d dVarB = p.b(0L, j5);
        k kVar2 = k.f3416d;
        float f9 = kVar == kVar2 ? fA : fA2;
        long jA = p.a(f9, f9);
        if (kVar == kVar2) {
            fA = fA2;
        }
        long jA2 = p.a(fA, fA);
        float f10 = kVar == kVar2 ? fA3 : fA4;
        long jA3 = p.a(f10, f10);
        if (kVar != kVar2) {
            fA4 = fA3;
        }
        return new i0(new x0.e(dVarB.f6534a, dVarB.f6535b, dVarB.f6536c, dVarB.f6537d, jA, jA2, jA3, p.a(fA4, fA4)));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return l.a(this.f5812d, dVar.f5812d) && l.a(this.f5813e, dVar.f5813e) && l.a(this.f5814f, dVar.f5814f) && l.a(this.f5815g, dVar.f5815g);
    }

    public final int hashCode() {
        return this.f5815g.hashCode() + ((this.f5814f.hashCode() + ((this.f5813e.hashCode() + (this.f5812d.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "RoundedCornerShape(topStart = " + this.f5812d + ", topEnd = " + this.f5813e + ", bottomEnd = " + this.f5814f + ", bottomStart = " + this.f5815g + ')';
    }
}
