package r0;

import c0.j1;
import f0.v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f5648a = new e(-1.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f5649b = new e(1.0f);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f5650c = new d(-1.0f);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d f5651d = new d(1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final StackTraceElement[] f5652e = new StackTraceElement[0];

    public static final p a(p pVar, a4.f fVar) {
        return pVar.e(new k(fVar));
    }

    public static final p b(f0.p pVar, p pVar2) {
        if (pVar2.b(l.f5679e)) {
            return pVar2;
        }
        v vVar = (v) pVar;
        vVar.W(1219399079);
        p pVar3 = (p) pVar2.a(new j1(10, vVar), m.f5680a);
        vVar.p(false);
        return pVar3;
    }

    public static final p c(f0.p pVar, p pVar2) {
        v vVar = (v) pVar;
        vVar.V(439770924);
        p pVarB = b(vVar, pVar2);
        vVar.p(false);
        return pVarB;
    }
}
