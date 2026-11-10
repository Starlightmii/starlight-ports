package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2344a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2345b;

    public /* synthetic */ u(int i5, Object obj) {
        this.f2344a = i5;
        this.f2345b = obj;
    }

    public final void a() {
        switch (this.f2344a) {
            case 0:
                ((v) this.f2345b).A--;
                break;
            default:
                ((p0.s) this.f2345b).f4742k--;
                break;
        }
    }

    public final void b() {
        switch (this.f2344a) {
            case 0:
                ((v) this.f2345b).A++;
                break;
            default:
                ((p0.s) this.f2345b).f4742k++;
                break;
        }
    }
}
