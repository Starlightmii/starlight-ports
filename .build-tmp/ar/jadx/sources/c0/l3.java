package c0;

import android.view.Choreographer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l3 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1035d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l3(int i5, t3.c cVar, int i6) {
        super(i5, cVar);
        this.f1035d = i6;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f1035d) {
            case 0:
                return new l3(2, cVar, 0);
            default:
                return new l3(2, cVar, 1);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1035d) {
            case 0:
                l3 l3Var = (l3) create((j1.d0) obj, (t3.c) obj2);
                p3.n nVar = p3.n.f5026a;
                l3Var.invokeSuspend(nVar);
                return nVar;
            default:
                return ((l3) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f1035d) {
            case 0:
                p3.j.O(obj);
                return p3.n.f5026a;
            default:
                p3.j.O(obj);
                return Choreographer.getInstance();
        }
    }
}
