package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5556d;

    public e0(int i5, int i6, int i7, int i8) {
        this.f5553a = i5;
        this.f5554b = i6;
        this.f5555c = i7;
        this.f5556d = i8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return this.f5553a == e0Var.f5553a && this.f5554b == e0Var.f5554b && this.f5555c == e0Var.f5555c && this.f5556d == e0Var.f5556d;
    }

    public final int hashCode() {
        return (((((this.f5553a * 31) + this.f5554b) * 31) + this.f5555c) * 31) + this.f5556d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("InsetsValues(left=");
        sb.append(this.f5553a);
        sb.append(", top=");
        sb.append(this.f5554b);
        sb.append(", right=");
        sb.append(this.f5555c);
        sb.append(", bottom=");
        return b.b.f(sb, this.f5556d, ')');
    }
}
