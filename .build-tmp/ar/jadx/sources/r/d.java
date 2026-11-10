package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements e, h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5544b;

    public d(int i5) {
        this.f5543a = i5;
        switch (i5) {
            case 1:
                this.f5544b = 0;
                break;
            case 2:
                this.f5544b = 0;
                break;
            case 3:
                this.f5544b = 0;
                break;
            default:
                this.f5544b = 0;
                break;
        }
    }

    @Override // r.e, r.h
    public final float a() {
        switch (this.f5543a) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return this.f5544b;
    }

    @Override // r.e
    public final void b(n1.h0 h0Var, int i5, int[] iArr, k2.k kVar, int[] iArr2) {
        switch (this.f5543a) {
            case 0:
                if (kVar != k2.k.f3416d) {
                    j.a(i5, iArr, iArr2, true);
                } else {
                    j.a(i5, iArr, iArr2, false);
                }
                break;
            case 1:
                if (kVar != k2.k.f3416d) {
                    j.d(i5, iArr, iArr2, true);
                } else {
                    j.d(i5, iArr, iArr2, false);
                }
                break;
            case 2:
                if (kVar != k2.k.f3416d) {
                    j.e(i5, iArr, iArr2, true);
                } else {
                    j.e(i5, iArr, iArr2, false);
                }
                break;
            default:
                if (kVar != k2.k.f3416d) {
                    j.f(i5, iArr, iArr2, true);
                } else {
                    j.f(i5, iArr, iArr2, false);
                }
                break;
        }
    }

    @Override // r.h
    public final void c(int i5, n1.h0 h0Var, int[] iArr, int[] iArr2) {
        switch (this.f5543a) {
            case 0:
                j.a(i5, iArr, iArr2, false);
                break;
            case 1:
                j.d(i5, iArr, iArr2, false);
                break;
            case 2:
                j.e(i5, iArr, iArr2, false);
                break;
            default:
                j.f(i5, iArr, iArr2, false);
                break;
        }
    }

    public final String toString() {
        switch (this.f5543a) {
            case 0:
                return "Arrangement#Center";
            case 1:
                return "Arrangement#SpaceAround";
            case 2:
                return "Arrangement#SpaceBetween";
            default:
                return "Arrangement#SpaceEvenly";
        }
    }
}
