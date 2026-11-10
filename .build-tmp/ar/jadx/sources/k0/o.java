package k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends n {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f3387g;

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f3387g) {
            case 0:
                int i5 = this.f3386f;
                this.f3386f = i5 + 2;
                Object[] objArr = this.f3384d;
                return new a(objArr[i5], objArr[i5 + 1]);
            case 1:
                int i6 = this.f3386f;
                this.f3386f = i6 + 2;
                return this.f3384d[i6];
            default:
                int i7 = this.f3386f;
                this.f3386f = i7 + 2;
                return this.f3384d[i7 + 1];
        }
    }
}
