package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 implements k2.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ k2.b f4556d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4557e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4558f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q4.c f4559g = new q4.c();

    public o0(k2.b bVar) {
        this.f4556d = bVar;
    }

    @Override // k2.b
    public final int E(long j5) {
        return this.f4556d.E(j5);
    }

    @Override // k2.b
    public final float I(long j5) {
        return this.f4556d.I(j5);
    }

    @Override // k2.b
    public final int M(float f5) {
        return this.f4556d.M(f5);
    }

    @Override // k2.b
    public final long R(long j5) {
        return this.f4556d.R(j5);
    }

    @Override // k2.b
    public final float U(long j5) {
        return this.f4556d.U(j5);
    }

    @Override // k2.b
    public final float a() {
        return this.f4556d.a();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object c(v3.c cVar) {
        m0 m0Var;
        o0 o0Var;
        if (cVar instanceof m0) {
            m0Var = (m0) cVar;
            int i5 = m0Var.f4535g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                m0Var.f4535g = i5 - Integer.MIN_VALUE;
            } else {
                m0Var = new m0(this, cVar);
            }
        } else {
            m0Var = new m0(this, cVar);
        }
        Object obj = m0Var.f4533e;
        int i6 = m0Var.f4535g;
        if (i6 == 0) {
            p3.j.O(obj);
            m0Var.f4532d = this;
            m0Var.f4535g = 1;
            Object objC = this.f4559g.c(m0Var);
            u3.a aVar = u3.a.f5838d;
            if (objC == aVar) {
                return aVar;
            }
            o0Var = this;
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            o0Var = m0Var.f4532d;
            p3.j.O(obj);
        }
        o0Var.f4557e = false;
        o0Var.f4558f = false;
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object d(v3.c cVar) {
        n0 n0Var;
        o0 o0Var;
        if (cVar instanceof n0) {
            n0Var = (n0) cVar;
            int i5 = n0Var.f4547g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                n0Var.f4547g = i5 - Integer.MIN_VALUE;
            } else {
                n0Var = new n0(this, cVar);
            }
        } else {
            n0Var = new n0(this, cVar);
        }
        Object obj = n0Var.f4545e;
        int i6 = n0Var.f4547g;
        if (i6 == 0) {
            p3.j.O(obj);
            if (this.f4557e || this.f4558f) {
                o0Var = this;
            } else {
                n0Var.f4544d = this;
                n0Var.f4547g = 1;
                Object objC = this.f4559g.c(n0Var);
                u3.a aVar = u3.a.f5838d;
                if (objC == aVar) {
                    return aVar;
                }
                o0Var = this;
            }
            return Boolean.valueOf(o0Var.f4557e);
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        o0Var = n0Var.f4544d;
        p3.j.O(obj);
        o0Var.f4559g.d(null);
        return Boolean.valueOf(o0Var.f4557e);
    }

    @Override // k2.b
    public final long d0(float f5) {
        return this.f4556d.d0(f5);
    }

    @Override // k2.b
    public final float i0(int i5) {
        return this.f4556d.i0(i5);
    }

    @Override // k2.b
    public final float j() {
        return this.f4556d.j();
    }

    @Override // k2.b
    public final float j0(float f5) {
        return this.f4556d.j0(f5);
    }

    @Override // k2.b
    public final long s(float f5) {
        return this.f4556d.s(f5);
    }

    @Override // k2.b
    public final long u(long j5) {
        return this.f4556d.u(j5);
    }

    @Override // k2.b
    public final float v(float f5) {
        return this.f4556d.v(f5);
    }
}
