package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3011d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f3012e;

    public /* synthetic */ t(String str, int i5) {
        this.f3011d = i5;
        this.f3012e = str;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f3011d;
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i5) {
            case 0:
                return AssistRestoreAppKt.AppBottomBar$lambda$0$0$2(this.f3012e, pVar, iIntValue);
            case 1:
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$4$0$3$0$0(this.f3012e, pVar, iIntValue);
            default:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$0$0$1(this.f3012e, pVar, iIntValue);
        }
    }
}
