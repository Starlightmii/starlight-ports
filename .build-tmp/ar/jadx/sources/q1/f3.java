package q1;

import io.github.andrea_lyz.assistrestore.R;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f3 implements f0.a0, androidx.lifecycle.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f5202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0.e0 f5203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public androidx.lifecycle.q f5205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a4.e f5206h = h1.f5217a;

    public f3(u uVar, f0.e0 e0Var) {
        this.f5202d = uVar;
        this.f5203e = e0Var;
    }

    @Override // androidx.lifecycle.t
    public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        if (oVar == androidx.lifecycle.o.ON_DESTROY) {
            e();
        } else {
            if (oVar != androidx.lifecycle.o.ON_CREATE || this.f5204f) {
                return;
            }
            f(this.f5206h);
        }
    }

    public final void e() {
        if (!this.f5204f) {
            this.f5204f = true;
            this.f5202d.getView().setTag(R.id.wrapped_composition_tag, null);
            androidx.lifecycle.q qVar = this.f5205g;
            if (qVar != null) {
                qVar.b(this);
            }
        }
        this.f5203e.l();
    }

    public final void f(a4.e eVar) {
        this.f5202d.setOnViewTreeOwnersAvailable(new c0.c2(this, 20, eVar));
    }
}
