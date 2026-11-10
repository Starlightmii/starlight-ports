package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3004d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.c f3005e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f3006f;

    public /* synthetic */ s(int i5, int i6, a4.c cVar) {
        this.f3004d = i6;
        this.f3005e = cVar;
        this.f3006f = i5;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f3004d) {
            case 0:
                return AssistRestoreAppKt.AppBottomBar$lambda$0$0$0$0(this.f3005e, this.f3006f);
            case 1:
                return AssistRestoreAppKt.EntriesScreen$lambda$1$0$1$0$0$0(this.f3005e, this.f3006f);
            default:
                return AssistRestoreAppKt.TargetScreen$lambda$0$0$0$0$0$0(this.f3005e, this.f3006f);
        }
    }
}
