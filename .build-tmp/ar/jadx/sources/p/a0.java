package p;

import c0.c2;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4398d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1 f4400f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ j1.d0 f4401g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ c0 f4402h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c2 f4403i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b0 f4404j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ b0 f4405k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c0.l f4406l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(a1 a1Var, j1.d0 d0Var, c0 c0Var, c2 c2Var, b0 b0Var, b0 b0Var2, c0.l lVar, t3.c cVar) {
        super(2, cVar);
        this.f4400f = a1Var;
        this.f4401g = d0Var;
        this.f4402h = c0Var;
        this.f4403i = c2Var;
        this.f4404j = b0Var;
        this.f4405k = b0Var2;
        this.f4406l = lVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        a0 a0Var = new a0(this.f4400f, this.f4401g, this.f4402h, this.f4403i, this.f4404j, this.f4405k, this.f4406l, cVar);
        a0Var.f4399e = obj;
        return a0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((a0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0055  */
    /* JADX WARN: Code duplicated, block: B:29:0x0061  */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        i4.v vVar;
        CancellationException cancellationException;
        k4.c cVar;
        int i5 = this.f4398d;
        p3.n nVar = p3.n.f5026a;
        a1 a1Var = this.f4400f;
        if (i5 == 0) {
            p3.j.O(obj);
            i4.v vVar2 = (i4.v) this.f4399e;
            try {
                l0 l0Var = a1Var.f4407s;
                j1.d0 d0Var = this.f4401g;
                c0 c0Var = this.f4402h;
                c2 c2Var = this.f4403i;
                b0 b0Var = this.f4404j;
                b0 b0Var2 = this.f4405k;
                c0.l lVar = this.f4406l;
                this.f4399e = vVar2;
                this.f4398d = 1;
                float f5 = z.f4654a;
                Object objJ = a.a.j(d0Var, new x(b0Var2, l0Var, c0Var, lVar, b0Var, c2Var, null), this);
                u3.a aVar = u3.a.f5838d;
                if (objJ != aVar) {
                    objJ = nVar;
                }
                if (objJ == aVar) {
                    return aVar;
                }
            } catch (CancellationException e5) {
                e = e5;
                vVar = vVar2;
                cancellationException = e;
                cVar = a1Var.f4411w;
                if (cVar != null) {
                    cVar.o(o.f4555a);
                }
                if (!i4.y.l(vVar)) {
                    throw cancellationException;
                }
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            vVar = (i4.v) this.f4399e;
            try {
                p3.j.O(obj);
            } catch (CancellationException e6) {
                e = e6;
                cancellationException = e;
                cVar = a1Var.f4411w;
                if (cVar != null) {
                    cVar.o(o.f4555a);
                }
                if (!i4.y.l(vVar)) {
                    throw cancellationException;
                }
            }
        }
        return nVar;
    }
}
