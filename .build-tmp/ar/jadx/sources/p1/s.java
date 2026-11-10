package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s extends n0 {
    @Override // p1.n0
    public final void A0() {
        h0 h0Var = this.f4932o.f4998o.f4802z.f4911s;
        b4.l.b(h0Var);
        h0Var.s0();
    }

    @Override // n1.e0
    public final int a0(int i5) {
        a2.h hVarQ = this.f4932o.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.b(d0Var.f4801y.f4970c, d0Var.k(), i5);
    }

    @Override // n1.e0
    public final n1.m0 c(long j5) {
        n0(j5);
        y0 y0Var = this.f4932o;
        h0.e eVarU = y0Var.f4998o.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                h0 h0Var = ((d0) objArr[i6]).f4802z.f4911s;
                b4.l.b(h0Var);
                h0Var.f4851l = 3;
                i6++;
            } while (i6 < i5);
        }
        d0 d0Var = y0Var.f4998o;
        n0.z0(this, d0Var.f4794r.h(this, d0Var.k(), j5));
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        a2.h hVarQ = this.f4932o.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.d(d0Var.f4801y.f4970c, d0Var.k(), i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        a2.h hVarQ = this.f4932o.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.j(d0Var.f4801y.f4970c, d0Var.k(), i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        a2.h hVarQ = this.f4932o.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.i(d0Var.f4801y.f4970c, d0Var.k(), i5);
    }

    @Override // p1.m0
    public final int o0(n1.l lVar) {
        h0 h0Var = this.f4932o.f4998o.f4802z.f4911s;
        b4.l.b(h0Var);
        e0 e0Var = h0Var.f4859t;
        if (!h0Var.f4852m) {
            j0 j0Var = h0Var.A;
            if (j0Var.f4895c == 2) {
                e0Var.f4821f = true;
                if (e0Var.f4817b) {
                    j0Var.f4900h = true;
                    j0Var.f4901i = true;
                }
            } else {
                e0Var.f4822g = true;
            }
        }
        s sVar = h0Var.x().N;
        if (sVar != null) {
            sVar.f4924k = true;
        }
        h0Var.D();
        s sVar2 = h0Var.x().N;
        if (sVar2 != null) {
            sVar2.f4924k = false;
        }
        Integer num = (Integer) e0Var.f4824i.get(lVar);
        int iIntValue = num != null ? num.intValue() : Integer.MIN_VALUE;
        this.f4937t.put(lVar, Integer.valueOf(iIntValue));
        return iIntValue;
    }
}
