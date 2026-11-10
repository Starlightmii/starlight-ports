package c;

import f0.p0;
import io.github.andrea_lyz.assistrestore.R;
import java.util.Iterator;
import n2.p;
import q1.f3;
import q1.m1;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f700b;

    public /* synthetic */ a(int i5, Object obj) {
        this.f699a = i5;
        this.f700b = obj;
    }

    @Override // f0.p0
    public final void dispose() {
        switch (this.f699a) {
            case 0:
                Iterator it = ((d) this.f700b).f707a.iterator();
                while (it.hasNext()) {
                    ((b.d) it.next()).cancel();
                }
                break;
            case 1:
                p pVar = (p) this.f700b;
                f3 f3Var = pVar.f5099f;
                if (f3Var != null) {
                    f3Var.e();
                }
                pVar.f5099f = null;
                pVar.requestLayout();
                pVar.setTag(R.id.view_tree_lifecycle_owner, null);
                pVar.f4241q.removeViewImmediate(pVar);
                break;
            case 2:
                ((m1) this.f700b).f5263a.a();
                break;
            default:
                ((e0) this.f700b).k();
                break;
        }
    }
}
