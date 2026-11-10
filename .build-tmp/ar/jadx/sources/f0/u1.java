package f0;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u1 implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ v1 f2350d;

    public u1(v1 v1Var) {
        this.f2350d = v1Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [j.y] */
    /* JADX WARN: Type inference failed for: r5v1, types: [f0.a1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r9v3, types: [j.y] */
    @Override // a4.a
    public final Object a() {
        ArrayList arrayList = this.f2350d.f2381a;
        j.z zVar = new j.z(arrayList.size());
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            ?? r5 = (a1) arrayList.get(i5);
            Object obj = r5.f2084b;
            int i6 = r5.f2083a;
            Object z0Var = obj != null ? new z0(Integer.valueOf(i6), r5.f2084b) : Integer.valueOf(i6);
            int iD = zVar.d(z0Var);
            boolean z2 = iD < 0;
            Object obj2 = z2 ? null : zVar.f3178c[iD];
            if (obj2 != null) {
                if (obj2 instanceof j.y) {
                    ?? r9 = (j.y) obj2;
                    r9.a(r5);
                    r5 = r9;
                } else {
                    Object[] objArr = j.e0.f3097a;
                    ?? yVar = new j.y(2);
                    yVar.a(obj2);
                    yVar.a(r5);
                    r5 = yVar;
                }
            }
            if (z2) {
                int i7 = ~iD;
                zVar.f3177b[i7] = z0Var;
                zVar.f3178c[i7] = r5;
            } else {
                zVar.f3178c[iD] = r5;
            }
        }
        return new h0.a(zVar);
    }
}
