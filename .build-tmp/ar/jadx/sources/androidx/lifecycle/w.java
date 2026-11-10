package androidx.lifecycle;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t f295b;

    public final void a(v vVar, o oVar) {
        p pVarA = oVar.a();
        p pVar = this.f294a;
        b4.l.e("state1", pVar);
        if (pVarA.compareTo(pVar) < 0) {
            pVar = pVarA;
        }
        this.f294a = pVar;
        this.f295b.b(vVar, oVar);
        this.f294a = pVarA;
    }
}
