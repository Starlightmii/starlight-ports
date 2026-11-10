package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f6818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f6819b = 0;

    static {
        k2.n[] nVarArr = k2.m.f3420b;
        f6818a = k2.m.f3421c;
    }

    public static final r a(r rVar, int i5, int i6, long j5, j2.o oVar, t tVar, j2.g gVar, int i7, int i8, j2.p pVar) {
        long j6;
        j2.o oVar2 = oVar;
        t tVar2 = tVar;
        j2.g gVar2 = gVar;
        int i9 = i7;
        int i10 = i8;
        if (i5 == Integer.MIN_VALUE || i5 == rVar.f6809a) {
            if (d4.a.A(j5)) {
                j6 = j5;
            } else {
                j6 = j5;
                if (k2.m.a(j6, rVar.f6811c)) {
                }
            }
            if ((oVar2 == null || oVar2.equals(rVar.f6812d)) && ((i6 == Integer.MIN_VALUE || i6 == rVar.f6810b) && ((tVar2 == null || tVar2.equals(rVar.f6813e)) && ((gVar2 == null || gVar2.equals(rVar.f6814f)) && ((i9 == 0 || i9 == rVar.f6815g) && ((i10 == Integer.MIN_VALUE || i10 == rVar.f6816h) && (pVar == null || pVar.equals(rVar.f6817i)))))))) {
                return rVar;
            }
        } else {
            j6 = j5;
        }
        long j7 = d4.a.A(j6) ? rVar.f6811c : j6;
        if (oVar2 == null) {
            oVar2 = rVar.f6812d;
        }
        if (i5 == Integer.MIN_VALUE) {
            i5 = rVar.f6809a;
        }
        int i11 = i6 == Integer.MIN_VALUE ? rVar.f6810b : i6;
        t tVar3 = rVar.f6813e;
        if (tVar3 != null && tVar2 == null) {
            tVar2 = tVar3;
        }
        if (gVar2 == null) {
            gVar2 = rVar.f6814f;
        }
        if (i9 == 0) {
            i9 = rVar.f6815g;
        }
        if (i10 == Integer.MIN_VALUE) {
            i10 = rVar.f6816h;
        }
        return new r(i5, i11, j7, oVar2, tVar2, gVar2, i9, i10, pVar == null ? rVar.f6817i : pVar);
    }
}
