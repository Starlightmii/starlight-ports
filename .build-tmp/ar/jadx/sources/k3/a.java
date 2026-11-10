package k3;

import androidx.lifecycle.o;
import androidx.lifecycle.t;
import androidx.lifecycle.v;
import w2.h;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3425d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f3426e;

    public /* synthetic */ a(int i5, Object obj) {
        this.f3425d = i5;
        this.f3426e = obj;
    }

    @Override // androidx.lifecycle.t
    public final void b(v vVar, o oVar) {
        switch (this.f3425d) {
            case 0:
                b bVar = (b) this.f3426e;
                if (oVar == o.ON_START) {
                    bVar.f3429c = true;
                } else if (oVar == o.ON_STOP) {
                    bVar.f3429c = false;
                }
                break;
            default:
                h hVar = (h) this.f3426e;
                hVar.getClass();
                if (oVar == o.ON_DESTROY) {
                    hVar.a();
                }
                break;
        }
    }
}
