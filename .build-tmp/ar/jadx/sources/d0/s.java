package d0;

import m.p0;
import m.z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final s f1577e = new s(3);

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        Object z0Var;
        p0 p0Var = (p0) obj;
        ((Number) obj3).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj2);
        vVar.V(-1154662212);
        f fVar = f.f1530d;
        f fVar2 = f.f1531e;
        if (p0Var.a(fVar, fVar2)) {
            z0Var = m.d.h(67, 2, m.w.f3799c);
        } else {
            z0Var = (p0Var.a(fVar2, fVar) || p0Var.a(f.f1532f, fVar2)) ? new z0(83, 67, m.w.f3799c) : m.d.g(7, null);
        }
        vVar.p(false);
        return z0Var;
    }
}
