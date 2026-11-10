package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2839d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.c f2840e;

    public /* synthetic */ a(a4.c cVar, int i5) {
        this.f2839d = i5;
        this.f2840e = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2839d) {
            case 0:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$2$0$0(this.f2840e, ((Boolean) obj).booleanValue());
            case 1:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$0$0$0(this.f2840e, ((Boolean) obj).booleanValue());
            case 2:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$2$0$0$0(this.f2840e, ((Boolean) obj).booleanValue());
            case 3:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$1$0$0(this.f2840e, ((Boolean) obj).booleanValue());
            default:
                p0.f fVar = (p0.f) this.f2840e.c((p0.k) obj);
                synchronized (p0.m.f4719c) {
                    p0.m.f4720d = p0.m.f4720d.e(fVar.g());
                }
                return fVar;
        }
    }
}
