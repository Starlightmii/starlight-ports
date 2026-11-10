package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2969d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f2970e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ TargetChoice f2971f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.c f2972g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f2973h;

    public /* synthetic */ p(AssistantSnapshot assistantSnapshot, boolean z2, TargetChoice targetChoice, a4.c cVar) {
        this.f2973h = assistantSnapshot;
        this.f2970e = z2;
        this.f2971f = targetChoice;
        this.f2972g = cVar;
    }

    public /* synthetic */ p(boolean z2, a4.a aVar, TargetChoice targetChoice, a4.c cVar) {
        this.f2970e = z2;
        this.f2973h = aVar;
        this.f2971f = targetChoice;
        this.f2972g = cVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        switch (this.f2969d) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1((AssistantSnapshot) this.f2973h, this.f2970e, this.f2971f, this.f2972g, (r.w) obj, (f0.p) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$2(this.f2970e, (a4.a) this.f2973h, this.f2971f, this.f2972g, (r.w) obj, (f0.p) obj2, iIntValue2);
        }
    }
}
