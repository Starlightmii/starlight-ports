package j1;

import i4.h1;
import q1.o2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 implements k2.b, t3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i4.h f3224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d0 f3225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i4.h f3226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j f3227g = j.f3261e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ d0 f3228h;

    public c0(d0 d0Var, i4.h hVar) {
        this.f3228h = d0Var;
        this.f3224d = hVar;
        this.f3225e = d0Var;
    }

    @Override // k2.b
    public final int E(long j5) {
        return this.f3225e.E(j5);
    }

    @Override // k2.b
    public final float I(long j5) {
        return this.f3225e.I(j5);
    }

    @Override // k2.b
    public final int M(float f5) {
        return this.f3225e.M(f5);
    }

    @Override // k2.b
    public final long R(long j5) {
        return this.f3225e.R(j5);
    }

    @Override // k2.b
    public final float U(long j5) {
        return this.f3225e.U(j5);
    }

    @Override // k2.b
    public final float a() {
        return this.f3225e.a();
    }

    public final Object c(j jVar, v3.a aVar) {
        i4.h hVar = new i4.h(1, p3.j.B(aVar));
        hVar.p();
        this.f3227g = jVar;
        this.f3226f = hVar;
        return hVar.o();
    }

    public final long d() {
        d0 d0Var = this.f3228h;
        long jR = d0Var.R(p1.f.s(d0Var).f4798v.g());
        long j5 = d0Var.f3240y;
        return p3.j.f(Math.max(0.0f, x0.f.d(jR) - ((int) (j5 >> 32))) / 2.0f, Math.max(0.0f, x0.f.b(jR) - ((int) (j5 & 4294967295L))) / 2.0f);
    }

    @Override // k2.b
    public final long d0(float f5) {
        return this.f3225e.d0(f5);
    }

    public final o2 g() {
        d0 d0Var = this.f3228h;
        d0Var.getClass();
        return p1.f.s(d0Var).f4798v;
    }

    @Override // t3.c
    public final t3.h getContext() {
        return t3.i.f5807d;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    /* JADX WARN: Type inference failed for: r6v1, types: [i4.s0] */
    /* JADX WARN: Type inference failed for: r6v4, types: [i4.s0] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r8v0, types: [a4.e] */
    public final Object h(long j5, a4.e eVar, v3.a aVar) {
        a0 a0Var;
        i4.h hVar;
        if (aVar instanceof a0) {
            a0Var = (a0) aVar;
            int i5 = a0Var.f3216g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                a0Var.f3216g = i5 - Integer.MIN_VALUE;
            } else {
                a0Var = new a0(this, aVar);
            }
        } else {
            a0Var = new a0(this, aVar);
        }
        Object objInvoke = a0Var.f3214e;
        int i6 = a0Var.f3216g;
        try {
            if (i6 == 0) {
                p3.j.O(objInvoke);
                if (j5 <= 0 && (hVar = this.f3226f) != null) {
                    hVar.resumeWith(p3.j.r(new k(j5)));
                }
                h1 h1VarN = i4.y.n(this.f3228h.n0(), null, new b0((long) j5, this, (t3.c) null), 3);
                a0Var.f3213d = h1VarN;
                a0Var.f3216g = 1;
                objInvoke = eVar.invoke(this, a0Var);
                u3.a aVar2 = u3.a.f5838d;
                j5 = h1VarN;
                if (objInvoke == aVar2) {
                    return aVar2;
                }
            } else {
                if (i6 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                h1 h1Var = a0Var.f3213d;
                p3.j.O(objInvoke);
                j5 = h1Var;
            }
            j5.b(b.f3217d);
            return objInvoke;
        } catch (Throwable th) {
            j5.b(b.f3217d);
            throw th;
        }
    }

    @Override // k2.b
    public final float i0(int i5) {
        return this.f3225e.i0(i5);
    }

    @Override // k2.b
    public final float j() {
        return this.f3225e.j();
    }

    @Override // k2.b
    public final float j0(float f5) {
        return f5 / this.f3225e.a();
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        d0 d0Var = this.f3228h;
        synchronized (d0Var.f3237v) {
            d0Var.f3237v.l(this);
        }
        this.f3224d.resumeWith(obj);
    }

    @Override // k2.b
    public final long s(float f5) {
        return this.f3225e.s(f5);
    }

    @Override // k2.b
    public final long u(long j5) {
        return this.f3225e.u(j5);
    }

    @Override // k2.b
    public final float v(float f5) {
        return this.f3225e.a() * f5;
    }
}
