package androidx.lifecycle;

import io.github.libxposed.service.IXposedService;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f253d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f254e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f255f;

    public h(f fVar, t tVar) {
        b4.l.e("defaultLifecycleObserver", fVar);
        this.f254e = fVar;
        this.f255f = tVar;
    }

    public h(q qVar, j3.e eVar) {
        this.f254e = qVar;
        this.f255f = eVar;
    }

    public h(u uVar) {
        this.f254e = uVar;
        d dVar = d.f236c;
        Class<?> cls = uVar.getClass();
        b bVar = (b) dVar.f237a.get(cls);
        this.f255f = bVar == null ? dVar.a(cls, null) : bVar;
    }

    @Override // androidx.lifecycle.t
    public final void b(v vVar, o oVar) {
        switch (this.f253d) {
            case 0:
                f fVar = (f) this.f254e;
                switch (g.f250a[oVar.ordinal()]) {
                    case 1:
                        fVar.getClass();
                        break;
                    case 2:
                        fVar.c(vVar);
                        break;
                    case 3:
                        fVar.a(vVar);
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        fVar.getClass();
                        break;
                    case 5:
                        fVar.d(vVar);
                        break;
                    case 6:
                        fVar.getClass();
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                    default:
                        throw new i4.p();
                }
                t tVar = (t) this.f255f;
                if (tVar != null) {
                    tVar.b(vVar, oVar);
                    return;
                }
                return;
            case 1:
                if (oVar == o.ON_START) {
                    ((q) this.f254e).b(this);
                    ((j3.e) this.f255f).c();
                    return;
                }
                return;
            default:
                HashMap map = ((b) this.f255f).f232a;
                List list = (List) map.get(oVar);
                Object obj = this.f254e;
                b.a(list, vVar, oVar, obj);
                b.a((List) map.get(o.ON_ANY), vVar, oVar, obj);
                return;
        }
    }
}
