package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface p {
    Object a(a4.e eVar, Object obj);

    boolean b(a4.c cVar);

    default p e(p pVar) {
        return pVar == m.f5680a ? this : new j(this, pVar);
    }
}
