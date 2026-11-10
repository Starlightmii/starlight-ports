package e2;

import g0.k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2032a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2033b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2034c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2035d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2036e;

    public /* synthetic */ l() {
    }

    public l(int i5, int i6, int i7, y1.e0 e0Var) {
        this.f2033b = i5;
        this.f2034c = i6;
        this.f2035d = i7;
        this.f2036e = e0Var;
    }

    public l(k0 k0Var) {
        this.f2036e = k0Var;
    }

    public z.m a(int i5) {
        return new z.m(p3.p.t((y1.e0) this.f2036e, i5), i5, 1L);
    }

    public int b() {
        return this.f2035d - this.f2034c;
    }

    public int c(int i5) {
        return ((k0) this.f2036e).f2489m[this.f2034c + i5];
    }

    public Object d(int i5) {
        return ((k0) this.f2036e).f2491o[this.f2035d + i5];
    }

    public String toString() {
        switch (this.f2032a) {
            case 0:
                return "";
            case 1:
            default:
                return super.toString();
            case 2:
                StringBuilder sb = new StringBuilder("SelectionInfo(id=1, range=(");
                int i5 = this.f2033b;
                sb.append(i5);
                sb.append('-');
                y1.e0 e0Var = (y1.e0) this.f2036e;
                sb.append(p3.p.t(e0Var, i5));
                sb.append(',');
                int i6 = this.f2034c;
                sb.append(i6);
                sb.append('-');
                sb.append(p3.p.t(e0Var, i6));
                sb.append("), prevOffset=");
                return b.b.f(sb, this.f2035d, ')');
        }
    }
}
