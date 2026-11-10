package b0;

import c0.r4;
import f0.i1;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements l4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f405d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f406e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f407f;

    public /* synthetic */ h(Object obj, int i5, Object obj2) {
        this.f405d = i5;
        this.f407f = obj;
        this.f406e = obj2;
    }

    public h(l4.c cVar, b4.w wVar, l4.e eVar) {
        this.f405d = 4;
        this.f407f = wVar;
        this.f406e = eVar;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0096  */
    /* JADX WARN: Code duplicated, block: B:51:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:71:0x014f  */
    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        d0.g gVar;
        q.n nVar;
        q.l lVar;
        Object nVar2;
        l4.b bVar;
        l4.k kVar;
        h hVar;
        switch (this.f405d) {
            case 0:
                q.i iVar = (q.i) obj;
                b bVar2 = (b) this.f407f;
                if (iVar instanceof q.m) {
                    q.m mVar = (q.m) iVar;
                    o oVarA = bVar2.f385k;
                    if (oVarA == null) {
                        oVarA = y.a(bVar2.f384j);
                        bVar2.f385k = oVarA;
                    }
                    q qVarA = oVarA.a(bVar2);
                    qVarA.b(mVar, bVar2.f380f, bVar2.f388n, bVar2.f389o, ((y0.u) bVar2.f382h.getValue()).f6696a, ((i) bVar2.f383i.getValue()).f411d, bVar2.f390p);
                    bVar2.f386l.setValue(qVarA);
                } else if (iVar instanceof q.n) {
                    q qVar = (q) bVar2.f386l.getValue();
                    if (qVar != null) {
                        qVar.d();
                    }
                } else if (iVar instanceof q.l) {
                    q qVar2 = (q) bVar2.f386l.getValue();
                    if (qVar2 != null) {
                        qVar2.d();
                    }
                } else {
                    bVar2.f379e.b(iVar, (i4.v) this.f406e);
                }
                return p3.n.f5026a;
            case 1:
                q.i iVar2 = (q.i) obj;
                u uVar = (u) this.f407f;
                if (!(iVar2 instanceof q.o)) {
                    i4.v vVar = (i4.v) this.f406e;
                    a0 a0Var = uVar.f458v;
                    if (a0Var == null) {
                        a0Var = new a0(uVar.f457u, uVar.f454r);
                        p1.f.l(uVar);
                        uVar.f458v = a0Var;
                    }
                    a0Var.b(iVar2, vVar);
                } else if (uVar.f461y) {
                    uVar.A0((q.o) iVar2);
                } else {
                    uVar.f462z.a(iVar2);
                }
                return p3.n.f5026a;
            case 2:
                q.i iVar3 = (q.i) obj;
                b4.u uVar2 = (b4.u) this.f407f;
                if (iVar3 instanceof q.m) {
                    uVar2.f695d++;
                } else if ((iVar3 instanceof q.n) || (iVar3 instanceof q.l)) {
                    uVar2.f695d--;
                }
                boolean z2 = uVar2.f695d > 0;
                r4 r4Var = (r4) this.f406e;
                if (r4Var.f1239s != z2) {
                    r4Var.f1239s = z2;
                    p1.f.m(r4Var);
                }
                return p3.n.f5026a;
            case 3:
                d0.h hVar2 = (d0.h) this.f406e;
                LinkedHashMap linkedHashMap = hVar2.f1538b;
                if (cVar instanceof d0.g) {
                    gVar = (d0.g) cVar;
                    int i5 = gVar.f1535e;
                    if ((i5 & Integer.MIN_VALUE) != 0) {
                        gVar.f1535e = i5 - Integer.MIN_VALUE;
                    } else {
                        gVar = new d0.g(this, cVar);
                    }
                } else {
                    gVar = new d0.g(this, cVar);
                }
                Object obj2 = gVar.f1534d;
                int i6 = gVar.f1535e;
                if (i6 == 0) {
                    p3.j.O(obj2);
                    l4.e eVar = (l4.e) this.f407f;
                    q.i iVar4 = (q.i) obj;
                    if (iVar4 instanceof q.m) {
                        q.m mVar2 = new q.m(x0.c.g(((q.m) iVar4).f5084a, hVar2.f1537a));
                        linkedHashMap.put(iVar4, mVar2);
                        nVar2 = mVar2;
                    } else if (iVar4 instanceof q.l) {
                        lVar = (q.l) iVar4;
                        q.m mVar3 = (q.m) linkedHashMap.remove(lVar.f5083a);
                        if (mVar3 != null) {
                            nVar2 = lVar;
                            nVar2 = new q.l(mVar3);
                        }
                    } else if (iVar4 instanceof q.n) {
                        nVar = (q.n) iVar4;
                        q.m mVar4 = (q.m) linkedHashMap.remove(nVar.f5085a);
                        if (mVar4 != null) {
                            nVar2 = iVar4;
                            nVar2 = nVar;
                            nVar2 = new q.n(mVar4);
                        }
                    }
                    nVar2 = iVar4;
                    nVar2 = nVar;
                    nVar2 = iVar4;
                    nVar2 = lVar;
                    gVar.f1535e = 1;
                    Object objD = eVar.d(nVar2, gVar);
                    u3.a aVar = u3.a.f5838d;
                    if (objD == aVar) {
                        return aVar;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj2);
                }
                return p3.n.f5026a;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                b4.w wVar = (b4.w) this.f407f;
                if (cVar instanceof l4.b) {
                    bVar = (l4.b) cVar;
                    int i7 = bVar.f3516f;
                    if ((i7 & Integer.MIN_VALUE) != 0) {
                        bVar.f3516f = i7 - Integer.MIN_VALUE;
                    } else {
                        bVar = new l4.b(this, cVar);
                    }
                } else {
                    bVar = new l4.b(this, cVar);
                }
                Object obj3 = bVar.f3514d;
                int i8 = bVar.f3516f;
                p3.n nVar3 = p3.n.f5026a;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj3);
                    return nVar3;
                }
                p3.j.O(obj3);
                Object obj4 = wVar.f697d;
                if (obj4 != m4.c.f3818b && b4.l.a(obj4, obj)) {
                    return nVar3;
                }
                wVar.f697d = obj;
                l4.e eVar2 = (l4.e) this.f406e;
                bVar.f3516f = 1;
                Object objD2 = eVar2.d(obj, bVar);
                u3.a aVar2 = u3.a.f5838d;
                return objD2 == aVar2 ? aVar2 : nVar3;
            case 5:
                if (cVar instanceof l4.k) {
                    kVar = (l4.k) cVar;
                    int i9 = kVar.f3551f;
                    if ((i9 & Integer.MIN_VALUE) != 0) {
                        kVar.f3551f = i9 - Integer.MIN_VALUE;
                    } else {
                        kVar = new l4.k(this, cVar);
                    }
                } else {
                    kVar = new l4.k(this, cVar);
                }
                Object objInvoke = kVar.f3550e;
                int i10 = kVar.f3551f;
                if (i10 == 0) {
                    p3.j.O(objInvoke);
                    a4.e eVar3 = (a4.e) this.f407f;
                    kVar.f3549d = this;
                    kVar.f3553h = obj;
                    kVar.f3551f = 1;
                    objInvoke = eVar3.invoke(obj, kVar);
                    u3.a aVar3 = u3.a.f5838d;
                    if (objInvoke == aVar3) {
                        return aVar3;
                    }
                    hVar = this;
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj = kVar.f3553h;
                    hVar = kVar.f3549d;
                    p3.j.O(objInvoke);
                }
                if (!((Boolean) objInvoke).booleanValue()) {
                    return p3.n.f5026a;
                }
                ((b4.w) hVar.f406e).f697d = obj;
                throw new m4.a(hVar);
            case 6:
                q.i iVar5 = (q.i) obj;
                ArrayList arrayList = (ArrayList) this.f407f;
                if (iVar5 instanceof q.d) {
                    arrayList.add(iVar5);
                } else if (iVar5 instanceof q.e) {
                    arrayList.remove(((q.e) iVar5).f5080a);
                }
                ((i1) this.f406e).setValue(Boolean.valueOf(!arrayList.isEmpty()));
                return p3.n.f5026a;
            default:
                long j5 = ((x0.c) obj).f6532a;
                m.c cVar2 = (m.c) this.f407f;
                boolean zE = p3.j.E(((x0.c) cVar2.c()).f6532a);
                p3.n nVar4 = p3.n.f5026a;
                if (zE && p3.j.E(j5) && x0.c.e(((x0.c) cVar2.c()).f6532a) != x0.c.e(j5)) {
                    i4.y.n((i4.v) this.f406e, null, new j1.b0(cVar2, j5, (t3.c) null), 3);
                    return nVar4;
                }
                Object objD3 = cVar2.d(new x0.c(j5), cVar);
                return objD3 == u3.a.f5838d ? objD3 : nVar4;
        }
    }
}
