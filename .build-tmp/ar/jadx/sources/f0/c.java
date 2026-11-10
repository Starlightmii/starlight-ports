package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface c {
    void c(int i5, Object obj);

    void e(Object obj);

    default void g() {
        Object objJ = j();
        m mVar = objJ instanceof m ? (m) objJ : null;
        if (mVar != null) {
            mVar.a();
        }
    }

    void h(int i5, Object obj);

    void i(int i5, int i6, int i7);

    Object j();

    void k(int i5, int i6);

    default void m(a4.e eVar, Object obj) {
        eVar.invoke(j(), obj);
    }

    void p();
}
