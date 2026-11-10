package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a2.g f2027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2030d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2031e;

    public j(y1.f fVar, long j5) {
        String str = fVar.f6759d;
        a2.g gVar = new a2.g();
        gVar.f46d = str;
        gVar.f44b = -1;
        gVar.f45c = -1;
        this.f2027a = gVar;
        this.f2028b = y1.g0.e(j5);
        this.f2029c = y1.g0.d(j5);
        this.f2030d = -1;
        this.f2031e = -1;
        int iE = y1.g0.e(j5);
        int iD = y1.g0.d(j5);
        if (iE < 0 || iE > str.length()) {
            StringBuilder sbG = b.b.g("start (", iE, ") offset is outside of text region ");
            sbG.append(str.length());
            throw new IndexOutOfBoundsException(sbG.toString());
        }
        if (iD < 0 || iD > str.length()) {
            StringBuilder sbG2 = b.b.g("end (", iD, ") offset is outside of text region ");
            sbG2.append(str.length());
            throw new IndexOutOfBoundsException(sbG2.toString());
        }
        if (iE > iD) {
            throw new IllegalArgumentException(b.b.d("Do not set reversed range: ", iE, " > ", iD));
        }
    }

    public final void a(int i5, int i6) {
        long jG = p3.j.g(i5, i6);
        this.f2027a.g(i5, i6, "");
        long jR = h1.c.R(p3.j.g(this.f2028b, this.f2029c), jG);
        h(y1.g0.e(jR));
        g(y1.g0.d(jR));
        int i7 = this.f2030d;
        if (i7 != -1) {
            long jR2 = h1.c.R(p3.j.g(i7, this.f2031e), jG);
            if (y1.g0.b(jR2)) {
                this.f2030d = -1;
                this.f2031e = -1;
            } else {
                this.f2030d = y1.g0.e(jR2);
                this.f2031e = y1.g0.d(jR2);
            }
        }
    }

    public final char b(int i5) {
        a2.g gVar = this.f2027a;
        l lVar = (l) gVar.f47e;
        if (lVar != null && i5 >= gVar.f44b) {
            int iB = lVar.f2033b - lVar.b();
            int i6 = gVar.f44b;
            if (i5 >= iB + i6) {
                return ((String) gVar.f46d).charAt(i5 - ((iB - gVar.f45c) + i6));
            }
            int i7 = i5 - i6;
            int i8 = lVar.f2034c;
            return i7 < i8 ? ((char[]) lVar.f2036e)[i7] : ((char[]) lVar.f2036e)[(i7 - i8) + lVar.f2035d];
        }
        return ((String) gVar.f46d).charAt(i5);
    }

    public final y1.g0 c() {
        int i5 = this.f2030d;
        if (i5 != -1) {
            return new y1.g0(p3.j.g(i5, this.f2031e));
        }
        return null;
    }

    public final void d(int i5, int i6, String str) {
        a2.g gVar = this.f2027a;
        if (i5 < 0 || i5 > gVar.b()) {
            StringBuilder sbG = b.b.g("start (", i5, ") offset is outside of text region ");
            sbG.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG.toString());
        }
        if (i6 < 0 || i6 > gVar.b()) {
            StringBuilder sbG2 = b.b.g("end (", i6, ") offset is outside of text region ");
            sbG2.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG2.toString());
        }
        if (i5 > i6) {
            throw new IllegalArgumentException(b.b.d("Do not set reversed range: ", i5, " > ", i6));
        }
        gVar.g(i5, i6, str);
        h(str.length() + i5);
        g(str.length() + i5);
        this.f2030d = -1;
        this.f2031e = -1;
    }

    public final void e(int i5, int i6) {
        a2.g gVar = this.f2027a;
        if (i5 < 0 || i5 > gVar.b()) {
            StringBuilder sbG = b.b.g("start (", i5, ") offset is outside of text region ");
            sbG.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG.toString());
        }
        if (i6 < 0 || i6 > gVar.b()) {
            StringBuilder sbG2 = b.b.g("end (", i6, ") offset is outside of text region ");
            sbG2.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG2.toString());
        }
        if (i5 >= i6) {
            throw new IllegalArgumentException(b.b.d("Do not set reversed or empty range: ", i5, " > ", i6));
        }
        this.f2030d = i5;
        this.f2031e = i6;
    }

    public final void f(int i5, int i6) {
        a2.g gVar = this.f2027a;
        if (i5 < 0 || i5 > gVar.b()) {
            StringBuilder sbG = b.b.g("start (", i5, ") offset is outside of text region ");
            sbG.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG.toString());
        }
        if (i6 < 0 || i6 > gVar.b()) {
            StringBuilder sbG2 = b.b.g("end (", i6, ") offset is outside of text region ");
            sbG2.append(gVar.b());
            throw new IndexOutOfBoundsException(sbG2.toString());
        }
        if (i5 > i6) {
            throw new IllegalArgumentException(b.b.d("Do not set reversed range: ", i5, " > ", i6));
        }
        h(i5);
        g(i6);
    }

    public final void g(int i5) {
        if (i5 >= 0) {
            this.f2029c = i5;
        } else {
            throw new IllegalArgumentException(("Cannot set selectionEnd to a negative value: " + i5).toString());
        }
    }

    public final void h(int i5) {
        if (i5 >= 0) {
            this.f2028b = i5;
        } else {
            throw new IllegalArgumentException(("Cannot set selectionStart to a negative value: " + i5).toString());
        }
    }

    public final String toString() {
        return this.f2027a.toString();
    }
}
