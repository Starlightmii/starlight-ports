package p;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4548e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4549f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f4550g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ i4.v f4551h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o0 f4552i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f4553j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Serializable f4554k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public n1(i4.v vVar, a4.f fVar, a4.c cVar, o0 o0Var, t3.c cVar2) {
        super(cVar2);
        this.f4551h = vVar;
        this.f4553j = (v3.i) fVar;
        this.f4554k = (b4.m) cVar;
        this.f4552i = o0Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n1(i4.v vVar, v.j jVar, b4.w wVar, o0 o0Var, t3.c cVar) {
        super(cVar);
        this.f4551h = vVar;
        this.f4553j = jVar;
        this.f4554k = wVar;
        this.f4552i = o0Var;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [a4.f, v3.i] */
    /* JADX WARN: Type inference failed for: r5v1, types: [a4.c, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4548e) {
            case 0:
                n1 n1Var = new n1(this.f4551h, (a4.f) this.f4553j, (a4.c) this.f4554k, this.f4552i, cVar);
                n1Var.f4550g = obj;
                return n1Var;
            default:
                n1 n1Var2 = new n1(this.f4551h, (v.j) this.f4553j, (b4.w) this.f4554k, this.f4552i, cVar);
                n1Var2.f4550g = obj;
                return n1Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        j1.c0 c0Var = (j1.c0) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4548e) {
            case 0:
                break;
        }
        return ((n1) create(c0Var, cVar)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d4  */
    /* JADX WARN: Type inference failed for: r0v7, types: [a4.c, b4.m] */
    /* JADX WARN: Type inference failed for: r4v2, types: [a4.f, v3.i] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        j1.c0 c0Var;
        j1.u uVar;
        switch (this.f4548e) {
            case 0:
                int i5 = this.f4549f;
                i4.v vVar = this.f4551h;
                o0 o0Var = this.f4552i;
                u3.a aVar = u3.a.f5838d;
                if (i5 != 0) {
                    if (i5 == 1) {
                        c0Var = (j1.c0) this.f4550g;
                        p3.j.O(obj);
                    } else {
                        if (i5 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        p3.j.O(obj);
                    }
                    uVar = (j1.u) obj;
                    if (uVar == null) {
                        i4.y.n(vVar, null, new m1(o0Var, null, 0), 3);
                    } else {
                        uVar.a();
                        i4.y.n(vVar, null, new m1(o0Var, null, 1), 3);
                        ((b4.m) this.f4554k).c(new x0.c(uVar.f3276c));
                    }
                    return p3.n.f5026a;
                }
                p3.j.O(obj);
                c0Var = (j1.c0) this.f4550g;
                i4.y.n(vVar, null, new l1(o0Var, null, 0), 3);
                this.f4550g = c0Var;
                this.f4549f = 1;
                obj = s1.b(c0Var, (2 & 1) != 0, j1.j.f3261e, this);
                if (obj == aVar) {
                    return aVar;
                }
                j1.u uVar2 = (j1.u) obj;
                uVar2.a();
                ?? r4 = (v3.i) this.f4553j;
                if (r4 != s1.f4586a) {
                    i4.y.n(vVar, null, new b0.d((a4.f) r4, o0Var, uVar2, (t3.c) null), 3);
                }
                this.f4550g = null;
                this.f4549f = 2;
                obj = s1.d(c0Var, j1.j.f3261e, this);
                if (obj == aVar) {
                    return aVar;
                }
                uVar = (j1.u) obj;
                if (uVar == null) {
                    i4.y.n(vVar, null, new m1(o0Var, null, 0), 3);
                } else {
                    uVar.a();
                    i4.y.n(vVar, null, new m1(o0Var, null, 1), 3);
                    ((b4.m) this.f4554k).c(new x0.c(uVar.f3276c));
                }
                return p3.n.f5026a;
            default:
                int i6 = this.f4549f;
                if (i6 == 0) {
                    p3.j.O(obj);
                    j1.c0 c0Var2 = (j1.c0) this.f4550g;
                    this.f4549f = 1;
                    obj = s1.d(c0Var2, j1.j.f3261e, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (obj == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                j1.u uVar3 = (j1.u) obj;
                i4.v vVar2 = this.f4551h;
                o0 o0Var2 = this.f4552i;
                if (uVar3 != null) {
                    uVar3.a();
                    i4.y.n(vVar2, null, new m1(o0Var2, null, 6), 3);
                    throw null;
                }
                i4.y.n(vVar2, null, new m1(o0Var2, null, 7), 3);
                v.j jVar = (v.j) this.f4553j;
                long j5 = ((j1.u) ((b4.w) this.f4554k).f697d).f3276c;
                jVar.f5949f.o();
                return p3.n.f5026a;
        }
    }
}
