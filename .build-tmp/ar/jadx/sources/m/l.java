package m;

import f0.g3;
import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l implements g3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a1 f3695d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f3696e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q f3697f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f3698g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3699h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3700i;

    public /* synthetic */ l(a1 a1Var, Object obj, q qVar, int i5) {
        this(a1Var, obj, (i5 & 4) != 0 ? null : qVar, Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    public l(a1 a1Var, Object obj, q qVar, long j5, long j6, boolean z2) {
        q qVarD;
        this.f3695d = a1Var;
        this.f3696e = f0.l.s(obj);
        if (qVar != null) {
            qVarD = d.d(qVar);
        } else {
            qVarD = (q) a1Var.f3614a.c(obj);
            qVarD.d();
        }
        this.f3697f = qVarD;
        this.f3698g = j5;
        this.f3699h = j6;
        this.f3700i = z2;
    }

    @Override // f0.g3
    public final Object getValue() {
        return this.f3696e.getValue();
    }

    public final String toString() {
        return "AnimationState(value=" + this.f3696e.getValue() + ", velocity=" + this.f3695d.f3615b.c(this.f3697f) + ", isRunning=" + this.f3700i + ", lastFrameTimeNanos=" + this.f3698g + ", finishedTimeNanos=" + this.f3699h + ')';
    }
}
