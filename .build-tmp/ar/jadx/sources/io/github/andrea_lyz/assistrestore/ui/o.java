package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2963d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2964e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.c f2965f;

    public /* synthetic */ o(int i5, int i6, a4.c cVar) {
        this.f2963d = i6;
        this.f2964e = i5;
        this.f2965f = cVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f2963d;
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i5) {
            case 0:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$0(this.f2964e, this.f2965f, pVar, iIntValue);
            case 1:
                return AssistRestoreAppKt.AdvancedScreen$lambda$0(this.f2964e, this.f2965f, pVar, iIntValue);
            default:
                return AssistRestoreAppKt.EntriesScreen$lambda$0(this.f2964e, this.f2965f, pVar, iIntValue);
        }
    }
}
