package m;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends v3.i implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l f3602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b4.s f3603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3604f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ c f3605g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3606h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o0 f3607i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f3608j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(c cVar, Object obj, o0 o0Var, long j5, t3.c cVar2) {
        super(1, cVar2);
        this.f3605g = cVar;
        this.f3606h = obj;
        this.f3607i = o0Var;
        this.f3608j = j5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        return ((a) create((t3.c) obj)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final t3.c create(t3.c cVar) {
        return new a(this.f3605g, this.f3606h, this.f3607i, this.f3608j, cVar);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        l lVar;
        b4.s sVar;
        o0 o0Var = this.f3607i;
        int i5 = this.f3604f;
        int i6 = 1;
        c cVar = this.f3605g;
        try {
            if (i5 == 0) {
                p3.j.O(obj);
                cVar.f3631c.f3697f = (q) cVar.f3629a.f3614a.c(this.f3606h);
                cVar.f3633e.setValue(o0Var.f3723c);
                cVar.f3632d.setValue(Boolean.TRUE);
                l lVar2 = cVar.f3631c;
                l lVar3 = new l(lVar2.f3695d, lVar2.f3696e.getValue(), d.d(lVar2.f3697f), lVar2.f3698g, Long.MIN_VALUE, lVar2.f3700i);
                b4.s sVar2 = new b4.s();
                long j5 = this.f3608j;
                c.b bVar = new c.b(cVar, lVar3, sVar2, 1);
                this.f3602d = lVar3;
                this.f3603e = sVar2;
                this.f3604f = 1;
                Object objB = d.b(lVar3, o0Var, j5, bVar, this);
                u3.a aVar = u3.a.f5838d;
                if (objB == aVar) {
                    return aVar;
                }
                lVar = lVar3;
                sVar = sVar2;
            } else {
                if (i5 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                sVar = this.f3603e;
                lVar = this.f3602d;
                p3.j.O(obj);
            }
            if (!sVar.f693d) {
                i6 = 2;
            }
            l lVar4 = cVar.f3631c;
            lVar4.f3697f.d();
            lVar4.f3698g = Long.MIN_VALUE;
            cVar.f3632d.setValue(Boolean.FALSE);
            return new i(lVar, i6);
        } catch (CancellationException e5) {
            l lVar5 = cVar.f3631c;
            lVar5.f3697f.d();
            lVar5.f3698g = Long.MIN_VALUE;
            cVar.f3632d.setValue(Boolean.FALSE);
            throw e5;
        }
    }
}
