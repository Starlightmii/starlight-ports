package k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class c extends q3.e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f3360f = new c(m.f3379e, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f3361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3362e;

    public c(m mVar, int i5) {
        this.f3361d = mVar;
        this.f3362e = i5;
    }

    public final c a(Object obj, l0.a aVar) {
        l lVarU = this.f3361d.u(obj != null ? obj.hashCode() : 0, 0, obj, aVar);
        return lVarU == null ? this : new c((m) lVarU.f3378b, this.f3362e + lVarU.f3377a);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.f3361d.d(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.f3361d.g(obj != null ? obj.hashCode() : 0, 0, obj);
    }
}
