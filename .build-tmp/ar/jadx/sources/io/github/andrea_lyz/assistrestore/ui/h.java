package io.github.andrea_lyz.assistrestore.ui;

import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2905d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i1 f2906e;

    public /* synthetic */ h(i1 i1Var, int i5) {
        this.f2905d = i5;
        this.f2906e = i1Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2905d) {
            case 0:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$44$0(this.f2906e, ((Integer) obj).intValue());
            case 1:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$0$0(this.f2906e, (String) obj);
            case 2:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$2$0(this.f2906e, (String) obj);
            case 3:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$3$0(this.f2906e, (String) obj);
            default:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$5$0(this.f2906e, (String) obj);
        }
    }
}
