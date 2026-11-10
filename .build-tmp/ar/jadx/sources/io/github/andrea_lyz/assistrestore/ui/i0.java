package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i0 implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2911d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.a f2912e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f2913f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f2914g;

    public /* synthetic */ i0(a4.a aVar, String str, a4.e eVar) {
        this.f2912e = aVar;
        this.f2913f = str;
        this.f2914g = eVar;
    }

    public /* synthetic */ i0(String str, a4.a aVar, a4.e eVar) {
        this.f2913f = str;
        this.f2912e = aVar;
        this.f2914g = eVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f2911d;
        f0.p pVar = (f0.p) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i5) {
            case 0:
                return AssistRestoreAppKt.AppScreen$lambda$0(this.f2913f, this.f2912e, this.f2914g, pVar, iIntValue);
            default:
                return AssistRestoreAppKt.AppTopBar$lambda$0(this.f2912e, this.f2913f, this.f2914g, pVar, iIntValue);
        }
    }
}
