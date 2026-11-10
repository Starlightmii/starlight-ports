package o1;

import java.util.HashSet;
import p1.d0;
import p1.m;
import q1.u;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f4370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0.e f4371b = new h0.e(new p1.c[16]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h0.e f4372c = new h0.e(new g[16]);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h0.e f4373d = new h0.e(new d0[16]);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h0.e f4374e = new h0.e(new g[16]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4375f;

    public d(u uVar) {
        this.f4370a = uVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static void b(o oVar, g gVar, HashSet hashSet) {
        o oVar2 = oVar.f5681d;
        if (!oVar2.f5693p) {
            d4.a.L("visitSubtreeIf called on an unattached node");
            throw null;
        }
        h0.e eVar = new h0.e(new o[16]);
        o oVar3 = oVar2.f5686i;
        if (oVar3 == null) {
            p1.f.b(eVar, oVar2);
        } else {
            eVar.b(oVar3);
        }
        while (eVar.k()) {
            o oVar4 = (o) eVar.m(eVar.f2578f - 1);
            if ((oVar4.f5684g & 32) != 0) {
                o oVar5 = oVar4;
                while (true) {
                    if (oVar5 != null) {
                        if ((oVar5.f5683f & 32) != 0) {
                            ?? eVar2 = 0;
                            ?? E = oVar5;
                            while (E != 0) {
                                if (E instanceof e) {
                                    e eVar3 = (e) E;
                                    if (eVar3 instanceof p1.c) {
                                        p1.c cVar = (p1.c) eVar3;
                                        if ((cVar.f4773q instanceof c) && cVar.f4775s.contains(gVar)) {
                                            hashSet.add(eVar3);
                                        }
                                    }
                                    if (eVar3.g().p(gVar)) {
                                        break;
                                    }
                                } else if ((E.f5683f & 32) != 0 && (E instanceof m)) {
                                    o oVar6 = ((m) E).f4921r;
                                    int i5 = 0;
                                    while (oVar6 != null) {
                                        if ((oVar6.f5683f & 32) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                E = E;
                                                eVar2 = eVar2;
                                                eVar2 = eVar2;
                                                E = oVar6;
                                            } else {
                                                if (eVar2 == 0) {
                                                    eVar2 = new h0.e(new o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar2.b(E);
                                                    E = 0;
                                                }
                                                eVar2.b(oVar6);
                                            }
                                        } else {
                                            E = E;
                                            eVar2 = eVar2;
                                        }
                                        oVar6 = oVar6.f5686i;
                                        E = E;
                                        eVar2 = eVar2;
                                    }
                                    if (i5 == 1) {
                                        E = E;
                                        eVar2 = eVar2;
                                    } else {
                                        E = E;
                                        eVar2 = eVar2;
                                    }
                                }
                                E = p1.f.e(eVar2);
                            }
                        }
                        oVar5 = oVar5.f5686i;
                    }
                }
            }
            p1.f.b(eVar, oVar4);
        }
    }

    public final void a() {
        if (this.f4375f) {
            return;
        }
        this.f4375f = true;
        b0.a aVar = new b0.a(15, this);
        h0.e eVar = this.f4370a.f5406s0;
        if (eVar.h(aVar)) {
            return;
        }
        eVar.b(aVar);
    }
}
