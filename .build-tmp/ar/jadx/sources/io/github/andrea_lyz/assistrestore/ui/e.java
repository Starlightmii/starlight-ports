package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2880e;

    public /* synthetic */ e(int i5, int i6) {
        this.f2879d = i6;
        this.f2880e = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f2879d;
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i5) {
            case 0:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$60(this.f2880e, pVar, iIntValue);
            case 1:
                return AssistRestoreAppKt.RowDivider$lambda$0(this.f2880e, pVar, iIntValue);
            default:
                return AssistRestoreAppKt.Chevron$lambda$0(this.f2880e, pVar, iIntValue);
        }
    }
}
