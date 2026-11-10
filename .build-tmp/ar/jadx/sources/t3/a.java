package t3;

import p3.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f5803d;

    public a(g gVar) {
        this.f5803d = gVar;
    }

    @Override // t3.h
    public /* bridge */ h f(g gVar) {
        return p.y(this, gVar);
    }

    @Override // t3.f
    public final g getKey() {
        return this.f5803d;
    }

    @Override // t3.h
    public /* bridge */ f j(g gVar) {
        return p.p(this, gVar);
    }

    @Override // t3.h
    public final /* bridge */ h k(h hVar) {
        return p.A(this, hVar);
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return eVar.invoke(obj, this);
    }
}
