package b0;

import android.view.ViewGroup;
import f0.i1;
import f0.n2;
import f0.s1;
import java.util.LinkedHashMap;
import n.j0;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements n2, p, j0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0 f379e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f380f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f381g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final i1 f382h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f383i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ViewGroup f384j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public o f385k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final s1 f386l = f0.l.s(null);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final s1 f387m = f0.l.s(Boolean.TRUE);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f388n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f389o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a f390p = new a(0, this);

    public b(boolean z2, float f5, i1 i1Var, i1 i1Var2, ViewGroup viewGroup) {
        this.f378d = z2;
        this.f379e = new a0(new r(i1Var2, 0), z2);
        this.f380f = z2;
        this.f381g = f5;
        this.f382h = i1Var;
        this.f383i = i1Var2;
        this.f384j = viewGroup;
    }

    @Override // n.j0
    public final void a(f0 f0Var) {
        a1.c cVar = f0Var.f4828d;
        this.f388n = cVar.b();
        float f5 = this.f381g;
        this.f389o = Float.isNaN(f5) ? d4.a.H(n.a(f0Var, this.f380f, cVar.b())) : cVar.M(f5);
        long j5 = ((y0.u) this.f382h.getValue()).f6696a;
        float f6 = ((i) this.f383i.getValue()).f411d;
        f0Var.c();
        this.f379e.a(f0Var, Float.isNaN(f5) ? n.a(f0Var, this.f378d, cVar.b()) : f0Var.v(f5), j5);
        y0.r rVarR = cVar.f27e.r();
        ((Boolean) this.f387m.getValue()).booleanValue();
        q qVar = (q) this.f386l.getValue();
        if (qVar != null) {
            qVar.e(f6, cVar.b(), j5);
            qVar.draw(y0.e.a(rVarR));
        }
    }

    @Override // b0.p
    public final void b0() {
        this.f386l.setValue(null);
    }

    @Override // f0.n2
    public final void d() {
    }

    @Override // f0.n2
    public final void e() {
        o oVar = this.f385k;
        if (oVar != null) {
            b0();
            a2.h hVar = oVar.f437g;
            q qVar = (q) ((LinkedHashMap) hVar.f49e).get(this);
            if (qVar != null) {
                qVar.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) hVar.f49e;
                q qVar2 = (q) linkedHashMap.get(this);
                if (qVar2 != null) {
                }
                linkedHashMap.remove(this);
                oVar.f436f.add(qVar);
            }
        }
    }

    @Override // f0.n2
    public final void l() {
        o oVar = this.f385k;
        if (oVar != null) {
            b0();
            a2.h hVar = oVar.f437g;
            q qVar = (q) ((LinkedHashMap) hVar.f49e).get(this);
            if (qVar != null) {
                qVar.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) hVar.f49e;
                q qVar2 = (q) linkedHashMap.get(this);
                if (qVar2 != null) {
                }
                linkedHashMap.remove(this);
                oVar.f436f.add(qVar);
            }
        }
    }
}
