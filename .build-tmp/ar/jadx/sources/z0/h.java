package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j.r f7068a;

    static {
        q qVar = d.f7042c;
        int i5 = qVar.f7039c;
        e eVar = new e(qVar, qVar, 1);
        int i6 = qVar.f7039c;
        l lVar = d.f7059t;
        int i7 = (lVar.f7039c << 6) | i6;
        g gVar = new g(qVar, lVar, 0);
        int i8 = (i6 << 6) | lVar.f7039c;
        g gVar2 = new g(lVar, qVar, 0);
        j.r rVar = j.j.f3115a;
        j.r rVar2 = new j.r();
        rVar2.g(i5 | (i5 << 6), eVar);
        rVar2.g(i7, gVar);
        rVar2.g(i8, gVar2);
        f7068a = rVar2;
    }
}
