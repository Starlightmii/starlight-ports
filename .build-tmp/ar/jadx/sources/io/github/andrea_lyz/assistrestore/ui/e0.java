package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e0 implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.f f2882e;

    public /* synthetic */ e0(a4.f fVar, int i5) {
        this.f2881d = i5;
        this.f2882e = fVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        switch (this.f2881d) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.SectionCard$lambda$0(this.f2882e, (r.w) obj, (f0.p) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.AppScreen$lambda$3(this.f2882e, (r.l0) obj, (f0.p) obj2, iIntValue2);
        }
    }
}
