package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m extends r0.o {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f4920q = z0.f(this);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public r0.o f4921r;

    public final void A0(int i5, boolean z2) {
        r0.o oVar;
        int i6 = this.f5683f;
        this.f5683f = i5;
        if (i6 != i5) {
            r0.o oVar2 = this.f5681d;
            if (oVar2 == this) {
                this.f5684g = i5;
            }
            if (this.f5693p) {
                r0.o oVar3 = this;
                while (oVar3 != null) {
                    i5 |= oVar3.f5683f;
                    oVar3.f5683f = i5;
                    if (oVar3 == oVar2) {
                        break;
                    } else {
                        oVar3 = oVar3.f5685h;
                    }
                }
                if (z2 && oVar3 == oVar2) {
                    i5 = z0.g(oVar2);
                    oVar2.f5683f = i5;
                }
                int i7 = i5 | ((oVar3 == null || (oVar = oVar3.f5686i) == null) ? 0 : oVar.f5684g);
                while (oVar3 != null) {
                    i7 |= oVar3.f5683f;
                    oVar3.f5684g = i7;
                    oVar3 = oVar3.f5685h;
                }
            }
        }
    }

    @Override // r0.o
    public final void p0() {
        super.p0();
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.x0(this.f5688k);
            if (!oVar.f5693p) {
                oVar.p0();
            }
        }
    }

    @Override // r0.o
    public final void q0() {
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.q0();
        }
        super.q0();
    }

    @Override // r0.o
    public final void t0() {
        super.t0();
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.t0();
        }
    }

    @Override // r0.o
    public final void u0() {
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.u0();
        }
        super.u0();
    }

    @Override // r0.o
    public final void v0() {
        super.v0();
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.v0();
        }
    }

    @Override // r0.o
    public final void w0(r0.o oVar) {
        this.f5681d = oVar;
        for (r0.o oVar2 = this.f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
            oVar2.w0(oVar);
        }
    }

    @Override // r0.o
    public final void x0(y0 y0Var) {
        this.f5688k = y0Var;
        for (r0.o oVar = this.f4921r; oVar != null; oVar = oVar.f5686i) {
            oVar.x0(y0Var);
        }
    }

    public final void y0(l lVar) {
        r0.o oVar = ((r0.o) lVar).f5681d;
        if (oVar != lVar) {
            r0.o oVar2 = lVar instanceof r0.o ? (r0.o) lVar : null;
            r0.o oVar3 = oVar2 != null ? oVar2.f5685h : null;
            if (oVar != this.f5681d || !b4.l.a(oVar3, this)) {
                throw new IllegalStateException("Cannot delegate to an already delegated node");
            }
            return;
        }
        if (oVar.f5693p) {
            d4.a.L("Cannot delegate to an already attached node");
            throw null;
        }
        oVar.w0(this.f5681d);
        int i5 = this.f5683f;
        int iG = z0.g(oVar);
        oVar.f5683f = iG;
        int i6 = this.f5683f;
        int i7 = iG & 2;
        if (i7 != 0 && (i6 & 2) != 0 && !(this instanceof w)) {
            d4.a.L("Delegating to multiple LayoutModifierNodes without the delegating node implementing LayoutModifierNode itself is not allowed.\nDelegating Node: " + this + "\nDelegate Node: " + oVar);
            throw null;
        }
        oVar.f5686i = this.f4921r;
        this.f4921r = oVar;
        oVar.f5685h = this;
        A0(iG | i6, false);
        if (this.f5693p) {
            if (i7 == 0 || (i5 & 2) != 0) {
                x0(this.f5688k);
            } else {
                t0 t0Var = f.s(this).f4801y;
                this.f5681d.x0(null);
                t0Var.g();
            }
            oVar.p0();
            oVar.u0();
            z0.a(oVar);
        }
    }

    public final void z0(l lVar) {
        r0.o oVar = null;
        for (r0.o oVar2 = this.f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
            if (oVar2 == lVar) {
                boolean z2 = oVar2.f5693p;
                if (z2) {
                    j.x xVar = z0.f5010a;
                    if (!z2) {
                        d4.a.L("autoInvalidateRemovedNode called on unattached node");
                        throw null;
                    }
                    z0.b(oVar2, -1, 2);
                    oVar2.v0();
                    oVar2.q0();
                }
                oVar2.w0(oVar2);
                oVar2.f5684g = 0;
                if (oVar == null) {
                    this.f4921r = oVar2.f5686i;
                } else {
                    oVar.f5686i = oVar2.f5686i;
                }
                oVar2.f5686i = null;
                oVar2.f5685h = null;
                int i5 = this.f5683f;
                int iG = z0.g(this);
                A0(iG, true);
                if (this.f5693p && (i5 & 2) != 0 && (iG & 2) == 0) {
                    t0 t0Var = f.s(this).f4801y;
                    this.f5681d.x0(null);
                    t0Var.g();
                    return;
                }
                return;
            }
            oVar = oVar2;
        }
        throw new IllegalStateException(("Could not find delegate: " + lVar).toString());
    }
}
