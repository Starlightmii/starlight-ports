package q1;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a3 implements androidx.lifecycle.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ n4.c f5111d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ f0.g f5112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0.l2 f5113f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ b4.w f5114g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ View f5115h;

    public a3(n4.c cVar, f0.g gVar, f0.l2 l2Var, b4.w wVar, View view) {
        this.f5111d = cVar;
        this.f5112e = gVar;
        this.f5113f = l2Var;
        this.f5114g = wVar;
        this.f5115h = view;
    }

    @Override // androidx.lifecycle.t
    public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        boolean z2;
        int i5 = z2.f5466a[oVar.ordinal()];
        i4.f fVarW = null;
        if (i5 == 1) {
            i4.y.n(this.f5111d, null, new m.e(this.f5114g, this.f5113f, vVar, this, this.f5115h, null), 1);
            return;
        }
        if (i5 != 2) {
            if (i5 != 3) {
                if (i5 != 4) {
                    return;
                }
                this.f5113f.v();
                return;
            } else {
                f0.l2 l2Var = this.f5113f;
                synchronized (l2Var.f2245c) {
                    l2Var.f2262t = true;
                }
                return;
            }
        }
        f0.g gVar = this.f5112e;
        if (gVar != null) {
            f0.c1 c1Var = (f0.c1) gVar.f2174f;
            synchronized (c1Var.f2103a) {
                try {
                    synchronized (c1Var.f2103a) {
                        z2 = c1Var.f2106d;
                    }
                    if (!z2) {
                        ArrayList arrayList = c1Var.f2104b;
                        c1Var.f2104b = c1Var.f2105c;
                        c1Var.f2105c = arrayList;
                        c1Var.f2106d = true;
                        int size = arrayList.size();
                        for (int i6 = 0; i6 < size; i6++) {
                            ((t3.c) arrayList.get(i6)).resumeWith(p3.n.f5026a);
                        }
                        arrayList.clear();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        f0.l2 l2Var2 = this.f5113f;
        synchronized (l2Var2.f2245c) {
            if (l2Var2.f2262t) {
                l2Var2.f2262t = false;
                fVarW = l2Var2.w();
            }
        }
        if (fVarW != null) {
            ((i4.h) fVarW).resumeWith(p3.n.f5026a);
        }
    }
}
