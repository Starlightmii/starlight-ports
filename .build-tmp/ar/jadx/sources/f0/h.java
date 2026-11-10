package f0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements t3.g, b3 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e2.f0 f2184e = new e2.f0(1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ h f2185f = new h(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f2186g = new h(2);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f2187h = new h(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f2188i = new h(4);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final h f2189j = new h(5);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2190d;

    public /* synthetic */ h(int i5) {
        this.f2190d = i5;
    }

    public static final void b(h hVar) {
        l4.d0 d0Var;
        Object obj;
        l0.b bVar;
        l4.d0 d0Var2 = l2.f2241y;
        do {
            d0Var = l2.f2241y;
            obj = (i0.b) d0Var.getValue();
            bVar = (l0.b) obj;
            k0.c cVarA = bVar.f3498f;
            l0.a aVar = (l0.a) cVarA.get(hVar);
            if (aVar != null) {
                Object obj2 = aVar.f3493a;
                Object obj3 = aVar.f3494b;
                k0.m mVar = cVarA.f3361d;
                k0.m mVarV = mVar.v(hVar != null ? hVar.hashCode() : 0, 0, hVar);
                if (mVar != mVarV) {
                    cVarA = mVarV == null ? k0.c.f3360f : new k0.c(mVarV, cVarA.f3362e - 1);
                }
                m0.b bVar2 = m0.b.f3811a;
                if (obj2 != bVar2) {
                    Object obj4 = cVarA.get(obj2);
                    b4.l.b(obj4);
                    cVarA = cVarA.a(obj2, new l0.a(((l0.a) obj4).f3493a, obj3));
                }
                if (obj3 != bVar2) {
                    Object obj5 = cVarA.get(obj3);
                    b4.l.b(obj5);
                    cVarA = cVarA.a(obj3, new l0.a(obj2, ((l0.a) obj5).f3494b));
                }
                Object obj6 = obj2 != bVar2 ? bVar.f3496d : obj3;
                if (obj3 != bVar2) {
                    obj2 = bVar.f3497e;
                }
                bVar = new l0.b(obj6, obj2, cVarA);
            }
            if (obj == bVar) {
                return;
            }
            b3.r rVar = m4.c.f3818b;
            if (obj == null) {
                obj = rVar;
            }
        } while (!d0Var.i(obj, bVar));
    }

    @Override // f0.b3
    public boolean a(Object obj, Object obj2) {
        switch (this.f2190d) {
            case 2:
                return false;
            case 3:
                return obj == obj2;
            default:
                return b4.l.a(obj, obj2);
        }
    }

    public String toString() {
        switch (this.f2190d) {
            case 2:
                return "NeverEqualPolicy";
            case 3:
                return "ReferentialEqualityPolicy";
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
            case 6:
            default:
                return super.toString();
            case 5:
                return "StructuralEqualityPolicy";
            case 7:
                return "Empty";
        }
    }
}
