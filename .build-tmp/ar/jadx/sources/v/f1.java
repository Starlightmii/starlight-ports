package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a2.h f5921a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a2.h f5922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5923c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Long f5924d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5925e;

    /* JADX WARN: Code duplicated, block: B:32:0x006d  */
    public final void a(e2.x xVar) {
        a2.h hVar;
        y1.f fVar = xVar.f2062a;
        this.f5925e = false;
        a2.h hVar2 = this.f5921a;
        if (xVar.equals(hVar2 != null ? (e2.x) hVar2.f50f : null)) {
            return;
        }
        String str = fVar.f6759d;
        a2.h hVar3 = this.f5921a;
        if (b4.l.a(str, hVar3 != null ? ((e2.x) hVar3.f50f).f2062a.f6759d : null)) {
            a2.h hVar4 = this.f5921a;
            if (hVar4 == null) {
                return;
            }
            hVar4.f50f = xVar;
            return;
        }
        this.f5921a = new a2.h(this.f5921a, 25, xVar);
        this.f5922b = null;
        int length = fVar.f6759d.length() + this.f5923c;
        this.f5923c = length;
        if (length > 100000) {
            a2.h hVar5 = this.f5921a;
            if ((hVar5 != null ? (a2.h) hVar5.f49e : null) == null) {
                return;
            }
            while (true) {
                if (hVar5 == null) {
                    hVar = null;
                } else {
                    a2.h hVar6 = (a2.h) hVar5.f49e;
                    if (hVar6 != null) {
                        hVar = (a2.h) hVar6.f49e;
                    } else {
                        hVar = null;
                    }
                }
                if (hVar == null) {
                    break;
                } else {
                    hVar5 = (a2.h) hVar5.f49e;
                }
            }
            if (hVar5 == null) {
                return;
            }
            hVar5.f49e = null;
        }
    }
}
