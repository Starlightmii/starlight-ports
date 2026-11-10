package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c0 implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2871e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2872f;

    public /* synthetic */ c0(Object obj, int i5, Object obj2) {
        this.f2870d = i5;
        this.f2871e = obj;
        this.f2872f = obj2;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2870d) {
            case 0:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$2$0((TargetChoice) this.f2871e, (a4.c) this.f2872f, (f0.p) obj, ((Integer) obj2).intValue());
            case 1:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$0((TargetChoice) this.f2871e, (a4.c) this.f2872f, (f0.p) obj, ((Integer) obj2).intValue());
            case 2:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$1((TargetChoice) this.f2871e, (a4.c) this.f2872f, (f0.p) obj, ((Integer) obj2).intValue());
            case 3:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$1$2((TargetChoice) this.f2871e, (a4.c) this.f2872f, (f0.p) obj, ((Integer) obj2).intValue());
            default:
                return AssistRestoreAppKt.AppBottomBar$lambda$0$0$1((e1.e) this.f2871e, (String) this.f2872f, (f0.p) obj, ((Integer) obj2).intValue());
        }
    }
}
