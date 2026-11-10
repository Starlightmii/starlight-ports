package q0;

import androidx.lifecycle.k0;
import f0.v;
import g0.j0;
import java.util.List;
import p3.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements j0, t3.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k0 f5089e = new k0(29);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v f5090d;

    public d(v vVar) {
        this.f5090d = vVar;
    }

    @Override // g0.j0
    public final List d(Integer num) {
        return this.f5090d.D();
    }

    @Override // t3.h
    public final /* bridge */ t3.h f(t3.g gVar) {
        return p.y(this, gVar);
    }

    @Override // t3.f
    public final t3.g getKey() {
        return f5089e;
    }

    @Override // t3.h
    public final /* bridge */ t3.f j(t3.g gVar) {
        return p.p(this, gVar);
    }

    @Override // t3.h
    public final /* bridge */ t3.h k(t3.h hVar) {
        return p.A(this, hVar);
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return eVar.invoke(obj, this);
    }
}
