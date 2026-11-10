package f0;

import android.view.View;
import io.github.libxposed.service.IXposedService;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i2 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2195d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f2197f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f2198g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f2199h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f2200i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f2201j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i2(l2 l2Var, k2 k2Var, g gVar, t3.c cVar) {
        super(2, cVar);
        this.f2195d = 0;
        this.f2199h = l2Var;
        this.f2200i = k2Var;
        this.f2201j = gVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public i2(j1.d0 d0Var, a4.f fVar, a4.c cVar, p.o0 o0Var, t3.c cVar2) {
        super(2, cVar2);
        this.f2195d = 2;
        this.f2198g = d0Var;
        this.f2199h = (v3.i) fVar;
        this.f2200i = (b4.m) cVar;
        this.f2201j = o0Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i2(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, t3.c cVar, int i5) {
        super(2, cVar);
        this.f2195d = i5;
        this.f2198g = obj;
        this.f2197f = obj2;
        this.f2199h = obj3;
        this.f2200i = obj4;
        this.f2201j = obj5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i2(Object obj, Object obj2, Object obj3, Object obj4, t3.c cVar, int i5) {
        super(2, cVar);
        this.f2195d = i5;
        this.f2198g = obj;
        this.f2199h = obj2;
        this.f2200i = obj3;
        this.f2201j = obj4;
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [a4.f, v3.i] */
    /* JADX WARN: Type inference failed for: r5v3, types: [a4.c, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f2195d) {
            case 0:
                i2 i2Var = new i2((l2) this.f2199h, (k2) this.f2200i, (g) this.f2201j, cVar);
                i2Var.f2197f = obj;
                return i2Var;
            case 1:
                i2 i2Var2 = new i2((p.v1) this.f2198g, (p.i) this.f2199h, (p.d) this.f2200i, (i4.s0) this.f2201j, cVar, 1);
                i2Var2.f2197f = obj;
                return i2Var2;
            case 2:
                i2 i2Var3 = new i2((j1.d0) this.f2198g, (v3.i) this.f2199h, (b4.m) this.f2200i, (p.o0) this.f2201j, cVar);
                i2Var3.f2197f = obj;
                return i2Var3;
            case 3:
                return new i2((v.o0) this.f2198g, (i1) this.f2197f, (e2.y) this.f2199h, (z.e0) this.f2200i, (e2.n) this.f2201j, cVar, 3);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return new i2((s.c) this.f2198g, (e2.x) this.f2197f, (v.o0) this.f2199h, (v.e1) this.f2200i, (e2.r) this.f2201j, cVar, 4);
            case 5:
                i2 i2Var4 = new i2((i4.v) this.f2198g, (i1) this.f2199h, (q.k) this.f2200i, (i1) this.f2201j, cVar, 5);
                i2Var4.f2197f = obj;
                return i2Var4;
            default:
                i2 i2Var5 = new i2((q1.r0) this.f2198g, (a4.c) this.f2199h, (x.c) this.f2200i, (x.q) this.f2201j, cVar, 6);
                i2Var5.f2197f = obj;
                return i2Var5;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        switch (this.f2195d) {
            case 0:
                return ((i2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((i2) create((p.g1) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 2:
                return ((i2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 3:
                return ((i2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return ((i2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 5:
                return ((i2) create((j1.d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                ((i2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:139:0x0317 A[Catch: all -> 0x0323, LOOP:1: B:138:0x0315->B:139:0x0317, LOOP_END, TryCatch #1 {all -> 0x0323, blocks: (B:133:0x0304, B:134:0x030a, B:136:0x030f, B:137:0x0310, B:139:0x0317, B:142:0x0328, B:160:0x036d, B:161:0x036e, B:135:0x030b), top: B:190:0x0304, inners: #7 }] */
    /* JADX WARN: Code duplicated, block: B:145:0x0342  */
    /* JADX WARN: Code duplicated, block: B:150:0x0352 A[Catch: all -> 0x0355, TryCatch #3 {all -> 0x0355, blocks: (B:148:0x034e, B:150:0x0352, B:153:0x0358), top: B:193:0x034e }] */
    /* JADX WARN: Code duplicated, block: B:166:0x037d A[Catch: all -> 0x0380, TryCatch #0 {all -> 0x0380, blocks: (B:164:0x0379, B:166:0x037d, B:169:0x0383), top: B:188:0x0379 }] */
    /* JADX WARN: Code duplicated, block: B:188:0x0379 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:193:0x034e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:199:0x030b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:212:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v18, types: [a4.f, v3.i] */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r7v13, types: [a4.c, b4.m] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        l4.d0 d0Var;
        Object obj2;
        l0.b bVar;
        i4.s0 s0Var;
        Throwable th;
        y0.x xVar;
        l2 l2Var;
        List listB;
        int size;
        int i5;
        b0.d dVar;
        l2 l2Var2;
        l2 l2Var3;
        int i6 = 2;
        t3.c cVar = null;
        int i7 = 1;
        switch (this.f2195d) {
            case 0:
                u3.a aVar = u3.a.f5838d;
                int i8 = this.f2196e;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    xVar = (y0.x) this.f2198g;
                    s0Var = (i4.s0) this.f2197f;
                    try {
                        p3.j.O(obj);
                        xVar.a();
                        l2Var3 = (l2) this.f2199h;
                        synchronized (l2Var3.f2245c) {
                            try {
                                if (l2Var3.f2246d == s0Var) {
                                    l2Var3.f2246d = null;
                                }
                                l2Var3.w();
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                        l4.d0 d0Var2 = l2.f2241y;
                        h.b(((l2) this.f2199h).f2266x);
                        return p3.n.f5026a;
                    } catch (Throwable th3) {
                        th = th3;
                        xVar.a();
                        l2Var2 = (l2) this.f2199h;
                        synchronized (l2Var2.f2245c) {
                            try {
                                if (l2Var2.f2246d == s0Var) {
                                    l2Var2.f2246d = null;
                                }
                                l2Var2.w();
                                l4.d0 d0Var3 = l2.f2241y;
                                h.b(((l2) this.f2199h).f2266x);
                                throw th;
                            } catch (Throwable th4) {
                                throw th4;
                            }
                        }
                    }
                }
                p3.j.O(obj);
                i4.s0 s0VarH = i4.y.h(((i4.v) this.f2197f).h());
                l2 l2Var4 = (l2) this.f2199h;
                synchronized (l2Var4.f2245c) {
                    Throwable th5 = l2Var4.f2247e;
                    if (th5 != null) {
                        throw th5;
                    }
                    if (((g2) l2Var4.f2263u.getValue()).compareTo(g2.f2178e) <= 0) {
                        throw new IllegalStateException("Recomposer shut down");
                    }
                    if (l2Var4.f2246d != null) {
                        throw new IllegalStateException("Recomposer already running");
                    }
                    l2Var4.f2246d = s0VarH;
                    l2Var4.w();
                }
                y yVar = new y(1, (l2) this.f2199h);
                p0.m.e(p0.m.f4717a);
                synchronized (p0.m.f4719c) {
                    p0.m.f4724h = q3.l.d0(p0.m.f4724h, yVar);
                }
                y0.x xVar2 = new y0.x(2, yVar);
                l4.d0 d0Var4 = l2.f2241y;
                h hVar = ((l2) this.f2199h).f2266x;
                try {
                    do {
                        d0Var = l2.f2241y;
                        obj2 = (i0.b) d0Var.getValue();
                        bVar = (l0.b) obj2;
                        m0.b bVar2 = m0.b.f3811a;
                        k0.c cVar2 = bVar.f3498f;
                        if (!cVar2.containsKey(hVar)) {
                            if (bVar.isEmpty()) {
                                bVar = new l0.b(hVar, hVar, cVar2.a(hVar, new l0.a(bVar2, bVar2)));
                            } else {
                                Object obj3 = bVar.f3497e;
                                Object obj4 = cVar2.get(obj3);
                                b4.l.b(obj4);
                                bVar = new l0.b(bVar.f3496d, hVar, cVar2.a(obj3, new l0.a(((l0.a) obj4).f3493a, hVar)).a(hVar, new l0.a(obj3, bVar2)));
                            }
                        }
                        if (obj2 != bVar) {
                            b3.r rVar = m4.c.f3818b;
                            if (obj2 == null) {
                                obj2 = rVar;
                            }
                        }
                        l2Var = (l2) this.f2199h;
                        synchronized (l2Var.f2245c) {
                            listB = l2Var.B();
                        }
                        size = listB.size();
                        for (i5 = 0; i5 < size; i5++) {
                            ((e0) listB.get(i5)).s();
                        }
                        dVar = new b0.d((k2) this.f2200i, (g) this.f2201j, cVar, i6);
                        this.f2197f = s0VarH;
                        this.f2198g = xVar2;
                        this.f2196e = 1;
                        if (i4.y.c(dVar, this) == aVar) {
                            return aVar;
                        }
                        s0Var = s0VarH;
                        xVar = xVar2;
                        xVar.a();
                        l2Var3 = (l2) this.f2199h;
                        synchronized (l2Var3.f2245c) {
                            if (l2Var3.f2246d == s0Var) {
                                l2Var3.f2246d = null;
                            }
                            l2Var3.w();
                            l4.d0 d0Var5 = l2.f2241y;
                            h.b(((l2) this.f2199h).f2266x);
                            return p3.n.f5026a;
                        }
                    } while (!d0Var.i(obj2, bVar));
                    l2Var = (l2) this.f2199h;
                    synchronized (l2Var.f2245c) {
                        listB = l2Var.B();
                        size = listB.size();
                        while (i5 < size) {
                            ((e0) listB.get(i5)).s();
                        }
                        dVar = new b0.d((k2) this.f2200i, (g) this.f2201j, cVar, i6);
                        this.f2197f = s0VarH;
                        this.f2198g = xVar2;
                        this.f2196e = 1;
                        if (i4.y.c(dVar, this) == aVar) {
                            return aVar;
                        }
                        s0Var = s0VarH;
                        xVar = xVar2;
                        xVar.a();
                        l2Var3 = (l2) this.f2199h;
                        synchronized (l2Var3.f2245c) {
                            if (l2Var3.f2246d == s0Var) {
                                l2Var3.f2246d = null;
                            }
                            l2Var3.w();
                            l4.d0 d0Var6 = l2.f2241y;
                            h.b(((l2) this.f2199h).f2266x);
                            return p3.n.f5026a;
                        }
                    }
                } catch (Throwable th6) {
                    s0Var = s0VarH;
                    th = th6;
                    xVar = xVar2;
                    xVar.a();
                    l2Var2 = (l2) this.f2199h;
                    synchronized (l2Var2.f2245c) {
                        if (l2Var2.f2246d == s0Var) {
                            l2Var2.f2246d = null;
                        }
                        l2Var2.w();
                    }
                    l4.d0 d0Var7 = l2.f2241y;
                    h.b(((l2) this.f2199h).f2266x);
                    throw th;
                }
            case 1:
                p.d dVar2 = (p.d) this.f2200i;
                p.i iVar = (p.i) this.f2199h;
                p.v1 v1Var = (p.v1) this.f2198g;
                u3.a aVar2 = u3.a.f5838d;
                int i9 = this.f2196e;
                if (i9 == 0) {
                    p3.j.O(obj);
                    p.g1 g1Var = (p.g1) this.f2197f;
                    v1Var.f4623e = p.i.y0(iVar, dVar2);
                    c.b bVar3 = new c.b(iVar, (i4.s0) this.f2201j, g1Var, i6);
                    n.i iVar2 = new n.i(iVar, v1Var, dVar2, i7);
                    this.f2196e = 1;
                    if (v1Var.a(bVar3, iVar2, this) == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i9 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            case 2:
                u3.a aVar3 = u3.a.f5838d;
                int i10 = this.f2196e;
                if (i10 == 0) {
                    p3.j.O(obj);
                    i4.v vVar = (i4.v) this.f2197f;
                    j1.d0 d0Var8 = (j1.d0) this.f2198g;
                    p.n1 n1Var = new p.n1(vVar, (a4.f) this.f2199h, (a4.c) this.f2200i, (p.o0) this.f2201j, (t3.c) null);
                    this.f2196e = 1;
                    if (a.a.j(d0Var8, n1Var, this) == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            case 3:
                v.o0 o0Var = (v.o0) this.f2198g;
                u3.a aVar4 = u3.a.f5838d;
                int i11 = this.f2196e;
                try {
                    if (i11 == 0) {
                        p3.j.O(obj);
                        a1.d dVar3 = new a1.d(new d3(new b0.r((i1) this.f2197f, 1), null));
                        m4.j jVar = new m4.j(o0Var, (e2.y) this.f2199h, (z.e0) this.f2200i, (e2.n) this.f2201j, 2);
                        this.f2196e = 1;
                        if (dVar3.g(jVar, this) == aVar4) {
                            return aVar4;
                        }
                    } else {
                        if (i11 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        p3.j.O(obj);
                    }
                    v.l0.g(o0Var);
                    return p3.n.f5026a;
                } catch (Throwable th7) {
                    v.l0.g(o0Var);
                    throw th7;
                }
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                p3.n nVar = p3.n.f5026a;
                u3.a aVar5 = u3.a.f5838d;
                int i12 = this.f2196e;
                if (i12 != 0) {
                    if (i12 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar;
                }
                p3.j.O(obj);
                s.c cVar3 = (s.c) this.f2198g;
                e2.x xVar3 = (e2.x) this.f2197f;
                v.t0 t0Var = ((v.o0) this.f2199h).f6006a;
                y1.e0 e0Var = ((v.e1) this.f2200i).f5899a;
                e2.r rVar2 = (e2.r) this.f2201j;
                this.f2196e = 1;
                int iB = rVar2.b(y1.g0.d(xVar3.f2063b));
                Object objA = cVar3.a(iB < e0Var.f6753a.f6743a.f6759d.length() ? e0Var.b(iB) : iB != 0 ? e0Var.b(iB - 1) : new x0.d(0.0f, 0.0f, 1.0f, (int) (v.w0.a(t0Var.f6099b, t0Var.f6104g, t0Var.f6105h, v.w0.f6126a, 1) & 4294967295L)), this);
                if (objA != aVar5) {
                    objA = nVar;
                }
                return objA == aVar5 ? aVar5 : nVar;
            case 5:
                p3.n nVar2 = p3.n.f5026a;
                u3.a aVar6 = u3.a.f5838d;
                int i13 = this.f2196e;
                if (i13 != 0) {
                    if (i13 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar2;
                }
                p3.j.O(obj);
                j1.d0 d0Var9 = (j1.d0) this.f2197f;
                v.z0 z0Var = new v.z0((i4.v) this.f2198g, (i1) this.f2199h, (q.k) this.f2200i, null);
                p.c1 c1Var = new p.c1((i1) this.f2201j, 2);
                this.f2196e = 1;
                p.h0 h0Var = p.s1.f4586a;
                Object objC = i4.y.c(new i2(d0Var9, z0Var, c1Var, new p.o0(d0Var9), null), this);
                if (objC != aVar6) {
                    objC = nVar2;
                }
                return objC == aVar6 ? aVar6 : nVar2;
            default:
                x.c cVar4 = (x.c) this.f2200i;
                q1.r0 r0Var = (q1.r0) this.f2198g;
                u3.a aVar7 = u3.a.f5838d;
                int i14 = this.f2196e;
                try {
                    if (i14 != 0) {
                        if (i14 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        p3.j.O(obj);
                        throw new i4.p();
                    }
                    p3.j.O(obj);
                    i4.v vVar2 = (i4.v) this.f2197f;
                    x.s sVar = x.t.f6500a;
                    View view = r0Var.f5332d;
                    sVar.getClass();
                    x.p pVar = new x.p(view);
                    x.u uVar = new x.u(r0Var.f5332d, new x.b((x.q) this.f2201j), pVar);
                    if (w.c.f6222a) {
                        i4.y.n(vVar2, null, new b0.t(cVar4, pVar, cVar, 20), 3);
                    }
                    a4.c cVar5 = (a4.c) this.f2199h;
                    if (cVar5 != null) {
                        cVar5.c(uVar);
                    }
                    cVar4.f6465c = uVar;
                    this.f2196e = 1;
                    r0Var.a(uVar, this);
                    return aVar7;
                } catch (Throwable th8) {
                    cVar4.f6465c = null;
                    throw th8;
                }
        }
    }
}
