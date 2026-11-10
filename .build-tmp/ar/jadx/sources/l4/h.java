package l4;

import f0.h2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3540d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f3541e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f3542f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final v3.i f3543g;

    public h(b4.s sVar, e eVar, h2 h2Var) {
        this.f3541e = sVar;
        this.f3542f = eVar;
        this.f3543g = h2Var;
    }

    public h(e eVar, t3.h hVar) {
        this.f3541e = hVar;
        this.f3542f = n4.a.k(hVar);
        this.f3543g = new b0.t(eVar, null, 4);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002e  */
    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        g gVar;
        h hVar;
        switch (this.f3540d) {
            case 0:
                if (cVar instanceof g) {
                    gVar = (g) cVar;
                    int i5 = gVar.f3539h;
                    if ((i5 & Integer.MIN_VALUE) != 0) {
                        gVar.f3539h = i5 - Integer.MIN_VALUE;
                    } else {
                        gVar = new g(this, cVar);
                    }
                } else {
                    gVar = new g(this, cVar);
                }
                Object objInvoke = gVar.f3537f;
                int i6 = gVar.f3539h;
                p3.n nVar = p3.n.f5026a;
                u3.a aVar = u3.a.f5838d;
                if (i6 != 0) {
                    if (i6 != 1) {
                        if (i6 == 2) {
                            obj = gVar.f3536e;
                            hVar = gVar.f3535d;
                            p3.j.O(objInvoke);
                        } else if (i6 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                    p3.j.O(objInvoke);
                    return nVar;
                }
                p3.j.O(objInvoke);
                if (((b4.s) this.f3541e).f693d) {
                    e eVar = (e) this.f3542f;
                    gVar.f3539h = 1;
                    if (eVar.d(obj, gVar) != aVar) {
                        return nVar;
                    }
                } else {
                    h2 h2Var = (h2) this.f3543g;
                    gVar.f3535d = this;
                    gVar.f3536e = obj;
                    gVar.f3539h = 2;
                    objInvoke = h2Var.invoke(obj, gVar);
                    if (objInvoke != aVar) {
                        hVar = this;
                    }
                }
                return aVar;
                if (((Boolean) objInvoke).booleanValue()) {
                    return nVar;
                }
                ((b4.s) hVar.f3541e).f693d = true;
                e eVar2 = (e) hVar.f3542f;
                gVar.f3535d = null;
                gVar.f3536e = null;
                gVar.f3539h = 3;
                if (eVar2.d(obj, gVar) != aVar) {
                    return nVar;
                }
                return aVar;
            default:
                Object objA = m4.c.a((t3.h) this.f3541e, obj, this.f3542f, (b0.t) this.f3543g, cVar);
                return objA == u3.a.f5838d ? objA : p3.n.f5026a;
        }
    }
}
