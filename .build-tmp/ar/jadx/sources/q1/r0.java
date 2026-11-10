package q1;

import android.view.View;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 implements i4.v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f5332d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e2.y f5333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i4.v f5334f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicReference f5335g = new AtomicReference(null);

    public r0(View view, e2.y yVar, i4.v vVar) {
        this.f5332d = view;
        this.f5333e = yVar;
        this.f5334f = vVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final void a(x.u uVar, v3.c cVar) {
        q0 q0Var;
        if (cVar instanceof q0) {
            q0Var = (q0) cVar;
            int i5 = q0Var.f5307f;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                q0Var.f5307f = i5 - Integer.MIN_VALUE;
            } else {
                q0Var = new q0(this, cVar);
            }
        } else {
            q0Var = new q0(this, cVar);
        }
        Object obj = q0Var.f5305d;
        int i6 = q0Var.f5307f;
        if (i6 == 0) {
            p3.j.O(obj);
            c0.c2 c2Var = new c0.c2(uVar, 16, this);
            b0.t tVar = new b0.t(this, null, 13);
            q0Var.f5307f = 1;
            if (i4.y.c(new l4.n(c2Var, this.f5335g, tVar, null), q0Var) == u3.a.f5838d) {
                return;
            }
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        throw new i4.p();
    }

    @Override // i4.v
    public final t3.h h() {
        return this.f5334f.h();
    }
}
