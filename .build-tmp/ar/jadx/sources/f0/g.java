package f0;

import android.view.Choreographer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements t3.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2172d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f2173e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f2174f;

    public g(Choreographer choreographer, q1.v0 v0Var) {
        this.f2172d = 2;
        this.f2173e = choreographer;
        this.f2174f = v0Var;
    }

    public g(d2 d2Var) {
        this.f2172d = 0;
        this.f2173e = d2Var;
        this.f2174f = new f3.a();
    }

    public g(g gVar) {
        this.f2172d = 1;
        this.f2173e = gVar;
        this.f2174f = new c1();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    private final Object e(a4.c cVar, t3.c cVar2) {
        t1 t1Var;
        boolean z2;
        Object objO;
        if (cVar2 instanceof t1) {
            t1Var = (t1) cVar2;
            int i5 = t1Var.f2332g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                t1Var.f2332g = i5 - Integer.MIN_VALUE;
            } else {
                t1Var = new t1(this, cVar2);
            }
        } else {
            t1Var = new t1(this, cVar2);
        }
        Object obj = t1Var.f2330e;
        u3.a aVar = u3.a.f5838d;
        int i6 = t1Var.f2332g;
        if (i6 == 0) {
            p3.j.O(obj);
            c1 c1Var = (c1) this.f2174f;
            t1Var.f2329d = cVar;
            t1Var.f2332g = 1;
            synchronized (c1Var.f2103a) {
                z2 = c1Var.f2106d;
            }
            if (z2) {
                objO = p3.n.f5026a;
            } else {
                i4.h hVar = new i4.h(1, p3.j.B(t1Var));
                hVar.p();
                synchronized (c1Var.f2103a) {
                    c1Var.f2104b.add(hVar);
                }
                hVar.s(new b1(c1Var, 0, hVar));
                objO = hVar.o();
                if (objO != aVar) {
                    objO = p3.n.f5026a;
                }
            }
            if (objO != aVar) {
            }
        }
        if (i6 != 1) {
            if (i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
            return obj;
        }
        cVar = t1Var.f2329d;
        p3.j.O(obj);
        g gVar = (g) this.f2173e;
        t1Var.f2329d = null;
        t1Var.f2332g = 2;
        Object objD = gVar.d(cVar, t1Var);
        return objD == aVar ? aVar : objD;
    }

    public final Object d(a4.c cVar, t3.c cVar2) {
        int i5;
        int i6;
        int i7;
        Object hVar;
        switch (this.f2172d) {
            case 0:
                i4.h hVar2 = new i4.h(1, p3.j.B(cVar2));
                hVar2.p();
                f3.a aVar = (f3.a) this.f2174f;
                e eVar = new e();
                eVar.f2128a = hVar2;
                eVar.f2129b = cVar;
                d2 d2Var = (d2) this.f2173e;
                aVar.getClass();
                b4.u uVar = new b4.u();
                uVar.f695d = -1;
                synchronized (aVar.f2449a) {
                    Throwable th = (Throwable) aVar.f2450b;
                    if (th != null) {
                        i4.h hVar3 = eVar.f2128a;
                        if (hVar3 != null) {
                            hVar3.resumeWith(p3.j.r(th));
                        }
                        hVar = h.f2184e;
                    } else {
                        n0.a aVar2 = (n0.a) aVar.f2451c;
                        do {
                            i5 = aVar2.get();
                            i6 = i5 + 1;
                        } while (!aVar2.compareAndSet(i5, i6));
                        boolean z2 = (134217727 & i6) == 1;
                        uVar.f695d = (i6 >>> 27) & 15;
                        ((j.y) aVar.f2452d).a(eVar);
                        if (z2) {
                            try {
                                d2Var.a();
                            } catch (Throwable th2) {
                                synchronized (aVar.f2449a) {
                                    try {
                                        if (((Throwable) aVar.f2450b) == null) {
                                            aVar.f2450b = th2;
                                            j.y yVar = (j.y) aVar.f2452d;
                                            Object[] objArr = yVar.f3174a;
                                            int i8 = yVar.f3175b;
                                            for (int i9 = 0; i9 < i8; i9++) {
                                                i4.h hVar4 = ((e) objArr[i9]).f2128a;
                                                if (hVar4 != null) {
                                                    hVar4.resumeWith(p3.j.r(th2));
                                                }
                                            }
                                            ((j.y) aVar.f2452d).b();
                                            n0.a aVar3 = (n0.a) aVar.f2451c;
                                            do {
                                                i7 = aVar3.get();
                                            } while (!aVar3.compareAndSet(i7, ((((i7 >>> 27) & 15) + 1) & 15) << 27));
                                        }
                                    } catch (Throwable th3) {
                                        throw th3;
                                    }
                                }
                            }
                        }
                        hVar = new a2.h(new q(eVar, aVar, uVar, 3));
                    }
                    break;
                }
                hVar2.s(new f(0, hVar));
                return hVar2.o();
            case 1:
                return e(cVar, cVar2);
            default:
                q1.v0 v0Var = (q1.v0) this.f2174f;
                i4.h hVar5 = new i4.h(1, p3.j.B(cVar2));
                hVar5.p();
                q1.w0 w0Var = new q1.w0(hVar5, this, cVar);
                if (b4.l.a(v0Var.f5426f, (Choreographer) this.f2173e)) {
                    synchronized (v0Var.f5428h) {
                        v0Var.f5430j.add(w0Var);
                        if (!v0Var.f5433m) {
                            v0Var.f5433m = true;
                            v0Var.f5426f.postFrameCallback(v0Var.f5434n);
                        }
                        break;
                    }
                    hVar5.s(new c0.c2(v0Var, 18, w0Var));
                } else {
                    ((Choreographer) this.f2173e).postFrameCallback(w0Var);
                    hVar5.s(new c0.c2(this, 19, w0Var));
                }
                return hVar5.o();
        }
    }

    @Override // t3.h
    public final t3.h f(t3.g gVar) {
        switch (this.f2172d) {
            case 0:
                break;
            case 1:
                break;
        }
        return p3.p.y(this, gVar);
    }

    @Override // t3.f
    public t3.g getKey() {
        return h.f2185f;
    }

    @Override // t3.h
    public final t3.f j(t3.g gVar) {
        switch (this.f2172d) {
            case 0:
                break;
            case 1:
                break;
        }
        return p3.p.p(this, gVar);
    }

    @Override // t3.h
    public final t3.h k(t3.h hVar) {
        switch (this.f2172d) {
            case 0:
                break;
            case 1:
                break;
        }
        return p3.p.A(this, hVar);
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        switch (this.f2172d) {
            case 0:
                break;
            case 1:
                break;
        }
        return eVar.invoke(obj, this);
    }
}
