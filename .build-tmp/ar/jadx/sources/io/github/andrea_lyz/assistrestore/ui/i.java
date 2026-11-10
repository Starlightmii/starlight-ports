package io.github.andrea_lyz.assistrestore.ui;

import f0.i1;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2909d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i1 f2910e;

    public /* synthetic */ i(i1 i1Var, int i5) {
        this.f2909d = i5;
        this.f2910e = i1Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f2909d) {
            case 0:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$45$0(this.f2910e);
            case 1:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$48$0(this.f2910e);
            case 2:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$4$0$0$0(this.f2910e);
            case 3:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$4$0$2$0(this.f2910e);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$49$0(this.f2910e);
            default:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$50$0(this.f2910e);
        }
    }
}
