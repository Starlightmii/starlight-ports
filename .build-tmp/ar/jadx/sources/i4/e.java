package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2726b;

    public /* synthetic */ e(int i5, Object obj) {
        this.f2725a = i5;
        this.f2726b = obj;
    }

    public final String toString() {
        switch (this.f2725a) {
            case 0:
                return "CancelHandler.UserSupplied[" + ((a4.c) this.f2726b).getClass().getSimpleName() + '@' + y.g(this) + ']';
            default:
                return "DisposeOnCancel[" + ((f0) this.f2726b) + ']';
        }
    }
}
