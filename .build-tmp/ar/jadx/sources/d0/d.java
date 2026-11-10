package d0;

import c0.a1;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements n2.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k2.b f1515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1516c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c0.c f1517d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1518e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f1519f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final w f1520g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final w f1521h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f1522i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f1523j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final b f1524k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final x f1525l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x f1526m;

    public d(long j5, k2.b bVar, c0.c cVar) {
        int iM = bVar.M(a1.f724a);
        this.f1514a = j5;
        this.f1515b = bVar;
        this.f1516c = iM;
        this.f1517d = cVar;
        int iM2 = bVar.M(Float.intBitsToFloat((int) (j5 >> 32)));
        r0.f fVar = r0.b.f5665p;
        this.f1518e = new a(fVar, fVar, iM2);
        r0.f fVar2 = r0.b.f5667r;
        this.f1519f = new a(fVar2, fVar2, iM2);
        this.f1520g = new w(r0.a.f5650c);
        this.f1521h = new w(r0.a.f5651d);
        int iM3 = bVar.M(Float.intBitsToFloat((int) (j5 & 4294967295L)));
        r0.g gVar = r0.b.f5662m;
        r0.g gVar2 = r0.b.f5664o;
        this.f1522i = new b(gVar, gVar2, iM3);
        this.f1523j = new b(gVar2, gVar, iM3);
        this.f1524k = new b(r0.b.f5663n, gVar, iM3);
        this.f1525l = new x(gVar, iM);
        this.f1526m = new x(gVar2, iM);
    }

    @Override // n2.s
    public final long a(k2.i iVar, long j5, k2.k kVar, long j6) {
        long j7;
        char c5;
        int iA;
        int i5;
        int i6 = iVar.f3411a;
        int i7 = iVar.f3413c;
        char c6 = 2;
        int i8 = iVar.f3412b;
        int i9 = (int) (j5 >> 32);
        int i10 = 0;
        List listH = p3.j.H(this.f1518e, this.f1519f, ((int) (h1.c.d(((i7 - i6) / 2) + i6, (iVar.a() / 2) + i8) >> 32)) < i9 / 2 ? this.f1520g : this.f1521h);
        int size = listH.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                j7 = j5;
                c5 = c6;
                iA = 0;
                break;
            }
            i iVar2 = (i) listH.get(i11);
            int i12 = (int) (j6 >> 32);
            int i13 = size;
            c5 = c6;
            int i14 = i11;
            j7 = j5;
            iA = iVar2.a(iVar, j7, i12, kVar);
            if (i14 == p3.j.z(listH) || (iA >= 0 && i12 + iA <= i9)) {
                break;
            }
            i11 = i14 + 1;
            size = i13;
            c6 = c5;
        }
        int i15 = iVar.f3411a;
        int i16 = (int) (j7 & 4294967295L);
        x xVar = ((int) (h1.c.d(((i7 - i15) / 2) + i15, (iVar.a() / 2) + i8) & 4294967295L)) < i16 / 2 ? this.f1525l : this.f1526m;
        j[] jVarArr = new j[4];
        jVarArr[0] = this.f1522i;
        jVarArr[1] = this.f1523j;
        jVarArr[c5] = this.f1524k;
        jVarArr[3] = xVar;
        List listH2 = p3.j.H(jVarArr);
        int size2 = listH2.size();
        for (int i17 = 0; i17 < size2; i17++) {
            int i18 = (int) (j6 & 4294967295L);
            int iA2 = ((j) listH2.get(i17)).a(iVar, j7, i18);
            if (i17 == p3.j.z(listH2) || (iA2 >= (i5 = this.f1516c) && i18 + iA2 <= i16 - i5)) {
                i10 = iA2;
                break;
            }
        }
        long jD = h1.c.d(iA, i10);
        int i19 = (int) (jD >> 32);
        int i20 = (int) (jD & 4294967295L);
        this.f1517d.invoke(iVar, new k2.i(i19, i20, ((int) (j6 >> 32)) + i19, ((int) (j6 & 4294967295L)) + i20));
        return jD;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f1514a == dVar.f1514a && b4.l.a(this.f1515b, dVar.f1515b) && this.f1516c == dVar.f1516c && b4.l.a(this.f1517d, dVar.f1517d);
    }

    public final int hashCode() {
        return this.f1517d.hashCode() + m.h.b(this.f1516c, (this.f1515b.hashCode() + (Long.hashCode(this.f1514a) * 31)) * 31, 31);
    }

    public final String toString() {
        return "DropdownMenuPositionProvider(contentOffset=" + ((Object) k2.f.a(this.f1514a)) + ", density=" + this.f1515b + ", verticalMargin=" + this.f1516c + ", onPositionCalculated=" + this.f1517d + ')';
    }
}
