package q1;

import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5151e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0 f5152f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(f0 f0Var, int i5) {
        super(1);
        this.f5151e = i5;
        this.f5152f = f0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f5151e) {
            case 0:
                f0 f0Var = this.f5152f;
                return Boolean.valueOf(f0Var.f5178d.getParent().requestSendAccessibilityEvent(f0Var.f5178d, (AccessibilityEvent) obj));
            default:
                h2 h2Var = (h2) obj;
                if (h2Var.f5219e.contains(h2Var)) {
                    f0 f0Var2 = this.f5152f;
                    f0Var2.f5178d.getSnapshotObserver().a(h2Var, f0Var2.M, new c0.t2(h2Var, 7, f0Var2));
                }
                return p3.n.f5026a;
        }
    }
}
