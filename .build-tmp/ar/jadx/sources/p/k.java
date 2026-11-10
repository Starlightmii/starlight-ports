package p;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b4.t f4507d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m.l f4508e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4509f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f4510g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a2.h f4511h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e1 f4512i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(float f5, a2.h hVar, e1 e1Var, t3.c cVar) {
        super(2, cVar);
        this.f4510g = f5;
        this.f4511h = hVar;
        this.f4512i = e1Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new k(this.f4510g, this.f4511h, this.f4512i, cVar);
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((k) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        float f5;
        b4.t tVar;
        m.l lVar;
        int i5 = this.f4509f;
        if (i5 == 0) {
            p3.j.O(obj);
            f5 = this.f4510g;
            if (Math.abs(f5) > 1.0f) {
                b4.t tVar2 = new b4.t();
                tVar2.f694d = f5;
                b4.t tVar3 = new b4.t();
                m.a1 a1Var = m.b1.f3620a;
                m.l lVar2 = new m.l(a1Var, Float.valueOf(0.0f), new m.m(f5), Long.MIN_VALUE, Long.MIN_VALUE, false);
                try {
                    a2.h hVar = this.f4511h;
                    a1.d dVar = (a1.d) hVar.f49e;
                    j jVar = new j(tVar3, this.f4512i, tVar2, hVar);
                    this.f4507d = tVar2;
                    this.f4508e = lVar2;
                    this.f4509f = 1;
                    Object objB = m.d.b(lVar2, new m.u(dVar, a1Var, lVar2.f3696e.getValue(), lVar2.f3697f), Long.MIN_VALUE, jVar, this);
                    Object obj2 = u3.a.f5838d;
                    if (objB != obj2) {
                        objB = p3.n.f5026a;
                    }
                    if (objB == obj2) {
                        return obj2;
                    }
                    tVar = tVar2;
                    f5 = tVar.f694d;
                } catch (CancellationException unused) {
                    tVar = tVar2;
                    lVar = lVar2;
                    tVar.f694d = ((Number) lVar.f3695d.f3615b.c(lVar.f3697f)).floatValue();
                }
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            lVar = this.f4508e;
            tVar = this.f4507d;
            try {
                p3.j.O(obj);
            } catch (CancellationException unused2) {
                tVar.f694d = ((Number) lVar.f3695d.f3615b.c(lVar.f3697f)).floatValue();
            }
            f5 = tVar.f694d;
        }
        return new Float(f5);
    }
}
