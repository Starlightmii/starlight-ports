package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f5675e = new i(2);

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        String str = (String) obj;
        n nVar = (n) obj2;
        if (str.length() == 0) {
            return nVar.toString();
        }
        return str + ", " + nVar;
    }
}
