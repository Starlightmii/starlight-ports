package m;

import android.content.Context;
import android.view.View;
import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 implements f0.p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3795b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f3796c;

    public /* synthetic */ v0(Object obj, int i5, Object obj2) {
        this.f3794a = i5;
        this.f3795b = obj;
        this.f3796c = obj2;
    }

    @Override // f0.p0
    public final void dispose() {
        int i5 = this.f3794a;
        Object obj = this.f3796c;
        Object obj2 = this.f3795b;
        switch (i5) {
            case 0:
                ((s0) obj2).f3757g.remove((q0) obj);
                break;
            case 1:
                ((Context) obj2).getApplicationContext().unregisterComponentCallbacks((q1.o0) obj);
                break;
            case 2:
                ((Context) obj2).getApplicationContext().unregisterComponentCallbacks((q1.p0) obj);
                break;
            case 3:
                r.a1 a1Var = (r.a1) obj2;
                View view = (View) obj;
                int i6 = a1Var.f5525s - 1;
                a1Var.f5525s = i6;
                if (i6 == 0) {
                    int i7 = w2.u.f6438a;
                    w2.p.b(view, null);
                    w2.u.a(view, null);
                    view.removeOnAttachStateChangeListener(a1Var.f5526t);
                }
                break;
            default:
                i1 i1Var = (i1) obj2;
                q.m mVar = (q.m) i1Var.getValue();
                if (mVar != null) {
                    q.l lVar = new q.l(mVar);
                    q.k kVar = (q.k) obj;
                    if (kVar != null) {
                        kVar.c(lVar);
                    }
                    i1Var.setValue(null);
                }
                break;
        }
    }
}
