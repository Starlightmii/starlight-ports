package p;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m1 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f4537e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m1(o0 o0Var, t3.c cVar, int i5) {
        super(2, cVar);
        this.f4536d = i5;
        this.f4537e = o0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4536d) {
            case 0:
                return new m1(this.f4537e, cVar, 0);
            case 1:
                return new m1(this.f4537e, cVar, 1);
            case 2:
                return new m1(this.f4537e, cVar, 2);
            case 3:
                return new m1(this.f4537e, cVar, 3);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return new m1(this.f4537e, cVar, 4);
            case 5:
                return new m1(this.f4537e, cVar, 5);
            case 6:
                return new m1(this.f4537e, cVar, 6);
            default:
                return new m1(this.f4537e, cVar, 7);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4536d) {
            case 0:
                m1 m1Var = (m1) create(vVar, cVar);
                p3.n nVar = p3.n.f5026a;
                m1Var.invokeSuspend(nVar);
                return nVar;
            case 1:
                m1 m1Var2 = (m1) create(vVar, cVar);
                p3.n nVar2 = p3.n.f5026a;
                m1Var2.invokeSuspend(nVar2);
                return nVar2;
            case 2:
                m1 m1Var3 = (m1) create(vVar, cVar);
                p3.n nVar3 = p3.n.f5026a;
                m1Var3.invokeSuspend(nVar3);
                return nVar3;
            case 3:
                m1 m1Var4 = (m1) create(vVar, cVar);
                p3.n nVar4 = p3.n.f5026a;
                m1Var4.invokeSuspend(nVar4);
                return nVar4;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                m1 m1Var5 = (m1) create(vVar, cVar);
                p3.n nVar5 = p3.n.f5026a;
                m1Var5.invokeSuspend(nVar5);
                return nVar5;
            case 5:
                m1 m1Var6 = (m1) create(vVar, cVar);
                p3.n nVar6 = p3.n.f5026a;
                m1Var6.invokeSuspend(nVar6);
                return nVar6;
            case 6:
                m1 m1Var7 = (m1) create(vVar, cVar);
                p3.n nVar7 = p3.n.f5026a;
                m1Var7.invokeSuspend(nVar7);
                return nVar7;
            default:
                m1 m1Var8 = (m1) create(vVar, cVar);
                p3.n nVar8 = p3.n.f5026a;
                m1Var8.invokeSuspend(nVar8);
                return nVar8;
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f4536d) {
            case 0:
                p3.j.O(obj);
                o0 o0Var = this.f4537e;
                o0Var.f4558f = true;
                o0Var.f4559g.d(null);
                break;
            case 1:
                p3.j.O(obj);
                o0 o0Var2 = this.f4537e;
                o0Var2.f4557e = true;
                o0Var2.f4559g.d(null);
                break;
            case 2:
                p3.j.O(obj);
                o0 o0Var3 = this.f4537e;
                o0Var3.f4557e = true;
                o0Var3.f4559g.d(null);
                break;
            case 3:
                p3.j.O(obj);
                o0 o0Var4 = this.f4537e;
                o0Var4.f4558f = true;
                o0Var4.f4559g.d(null);
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                p3.j.O(obj);
                o0 o0Var5 = this.f4537e;
                o0Var5.f4557e = true;
                o0Var5.f4559g.d(null);
                break;
            case 5:
                p3.j.O(obj);
                o0 o0Var6 = this.f4537e;
                o0Var6.f4557e = true;
                o0Var6.f4559g.d(null);
                break;
            case 6:
                p3.j.O(obj);
                o0 o0Var7 = this.f4537e;
                o0Var7.f4557e = true;
                o0Var7.f4559g.d(null);
                break;
            default:
                p3.j.O(obj);
                o0 o0Var8 = this.f4537e;
                o0Var8.f4558f = true;
                o0Var8.f4559g.d(null);
                break;
        }
        return p3.n.f5026a;
    }
}
