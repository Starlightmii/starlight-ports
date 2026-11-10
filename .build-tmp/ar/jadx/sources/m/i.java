package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f3674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3675b;

    public i(l lVar, int i5) {
        this.f3674a = lVar;
        this.f3675b = i5;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("AnimationResult(endReason=");
        int i5 = this.f3675b;
        if (i5 != 1) {
            str = i5 != 2 ? "null" : "Finished";
        } else {
            str = "BoundReached";
        }
        sb.append(str);
        sb.append(", endState=");
        sb.append(this.f3674a);
        sb.append(')');
        return sb.toString();
    }
}
