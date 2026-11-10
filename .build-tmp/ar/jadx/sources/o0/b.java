package o0;

import f0.d2;
import f0.h;
import f0.n2;
import p0.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements n2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public f f4357d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f4358e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4359f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f4360g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object[] f4361h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a1.b f4362i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d2 f4363j = new d2(2, this);

    public b(f fVar, c cVar, String str, Object obj, Object[] objArr) {
        this.f4357d = fVar;
        this.f4358e = cVar;
        this.f4359f = str;
        this.f4360g = obj;
        this.f4361h = objArr;
    }

    public final void a() {
        String strA;
        c cVar = this.f4358e;
        if (this.f4362i != null) {
            throw new IllegalArgumentException(("entry(" + this.f4362i + ") is not null").toString());
        }
        if (cVar != null) {
            d2 d2Var = this.f4363j;
            Object objA = d2Var.a();
            if (objA == null || cVar.a(objA)) {
                this.f4362i = cVar.c(this.f4359f, d2Var);
                return;
            }
            if (objA instanceof n) {
                n nVar = (n) objA;
                if (nVar.d() == h.f2186g || nVar.d() == h.f2189j || nVar.d() == h.f2187h) {
                    strA = "MutableState containing " + nVar.getValue() + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it as a stateSaver parameter to rememberSaveable().";
                } else {
                    strA = "If you use a custom SnapshotMutationPolicy for your MutableState you have to write a custom Saver";
                }
            } else {
                strA = g.a(objA);
            }
            throw new IllegalArgumentException(strA);
        }
    }

    @Override // f0.n2
    public final void d() {
        a();
    }

    @Override // f0.n2
    public final void e() {
        a1.b bVar = this.f4362i;
        if (bVar != null) {
            bVar.E();
        }
    }

    @Override // f0.n2
    public final void l() {
        a1.b bVar = this.f4362i;
        if (bVar != null) {
            bVar.E();
        }
    }
}
