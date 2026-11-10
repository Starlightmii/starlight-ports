package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements w0.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f4830a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f4831b;

    @Override // w0.i
    public final boolean a() {
        Boolean bool = f4831b;
        if (bool != null) {
            return bool.booleanValue();
        }
        d4.a.M("canFocus is read before it is written");
        throw null;
    }

    @Override // w0.i
    public final void b(boolean z2) {
        f4831b = Boolean.valueOf(z2);
    }
}
