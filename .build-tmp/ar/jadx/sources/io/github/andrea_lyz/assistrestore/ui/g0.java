package io.github.andrea_lyz.assistrestore.ui;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g0 implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f2903e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.c f2904f;

    public /* synthetic */ g0(boolean z2, a4.c cVar, int i5) {
        this.f2902d = i5;
        this.f2903e = z2;
        this.f2904f = cVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f2902d;
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i5) {
            case 0:
                return AssistRestoreAppKt.EntriesScreen$lambda$1$0$2$0(this.f2903e, this.f2904f, pVar, iIntValue);
            case 1:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$0(this.f2903e, this.f2904f, pVar, iIntValue);
            case 2:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$1(this.f2903e, this.f2904f, pVar, iIntValue);
            case 3:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$2(this.f2903e, this.f2904f, pVar, iIntValue);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$3(this.f2903e, this.f2904f, pVar, iIntValue);
            case 5:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$4(this.f2903e, this.f2904f, pVar, iIntValue);
            default:
                return AssistRestoreAppKt.AdvancedScreen$lambda$1$0$0$5(this.f2903e, this.f2904f, pVar, iIntValue);
        }
    }
}
