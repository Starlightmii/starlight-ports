package androidx.compose.foundation.selection;

import a4.f;
import androidx.compose.foundation.d;
import b4.m;
import f0.o;
import f0.p;
import f0.v;
import n.i0;
import q.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends m implements f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i0 f131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f132f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f133g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ w1.f f134h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.a f135i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(i0 i0Var, boolean z2, boolean z3, w1.f fVar, a4.a aVar) {
        super(3);
        this.f131e = i0Var;
        this.f132f = z2;
        this.f133g = z3;
        this.f134h = fVar;
        this.f135i = aVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        ((Number) obj3).intValue();
        v vVar = (v) ((p) obj2);
        vVar.V(-1525724089);
        Object objK = vVar.K();
        if (objK == o.f2281a) {
            objK = new k();
            vVar.f0(objK);
        }
        k kVar = (k) objK;
        r0.p pVarE = d.a(r0.m.f5680a, kVar, this.f131e).e(new SelectableElement(this.f132f, kVar, null, this.f133g, this.f134h, this.f135i));
        vVar.p(false);
        return pVarE;
    }
}
