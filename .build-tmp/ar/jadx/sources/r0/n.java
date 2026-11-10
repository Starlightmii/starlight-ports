package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface n extends p {
    @Override // r0.p
    default Object a(a4.e eVar, Object obj) {
        return eVar.invoke(obj, this);
    }

    @Override // r0.p
    default boolean b(a4.c cVar) {
        return ((Boolean) cVar.c(this)).booleanValue();
    }
}
