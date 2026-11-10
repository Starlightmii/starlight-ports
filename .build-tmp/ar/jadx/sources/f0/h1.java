package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface h1 extends i1, g3 {
    @Override // f0.g3
    default Object getValue() {
        return Integer.valueOf(((p1) this).g());
    }

    @Override // f0.i1
    default void setValue(Object obj) {
        ((p1) this).h(((Number) obj).intValue());
    }
}
