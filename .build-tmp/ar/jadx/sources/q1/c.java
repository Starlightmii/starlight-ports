package q1;

import java.text.BreakIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static c f5134e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static c f5135f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static c f5136g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5137c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f5138d;

    @Override // q1.b
    public final int[] a(int i5) {
        int iE;
        switch (this.f5137c) {
            case 0:
                int length = c().length();
                if (length <= 0 || i5 >= length) {
                    return null;
                }
                if (i5 < 0) {
                    i5 = 0;
                }
                do {
                    BreakIterator breakIterator = (BreakIterator) this.f5138d;
                    if (breakIterator == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    if (breakIterator.isBoundary(i5)) {
                        BreakIterator breakIterator2 = (BreakIterator) this.f5138d;
                        if (breakIterator2 == null) {
                            b4.l.j("impl");
                            throw null;
                        }
                        int iFollowing = breakIterator2.following(i5);
                        if (iFollowing == -1) {
                            return null;
                        }
                        return b(i5, iFollowing);
                    }
                    BreakIterator breakIterator3 = (BreakIterator) this.f5138d;
                    if (breakIterator3 == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    i5 = breakIterator3.following(i5);
                } while (i5 != -1);
                return null;
            case 1:
                if (c().length() <= 0 || i5 >= c().length()) {
                    return null;
                }
                if (i5 < 0) {
                    i5 = 0;
                }
                while (!h(i5) && (!h(i5) || (i5 != 0 && h(i5 - 1)))) {
                    BreakIterator breakIterator4 = (BreakIterator) this.f5138d;
                    if (breakIterator4 == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    i5 = breakIterator4.following(i5);
                    if (i5 == -1) {
                        return null;
                    }
                }
                BreakIterator breakIterator5 = (BreakIterator) this.f5138d;
                if (breakIterator5 == null) {
                    b4.l.j("impl");
                    throw null;
                }
                int iFollowing2 = breakIterator5.following(i5);
                if (iFollowing2 == -1 || !g(iFollowing2)) {
                    return null;
                }
                return b(i5, iFollowing2);
            default:
                if (c().length() <= 0 || i5 >= c().length()) {
                    return null;
                }
                j2.h hVar = j2.h.f3319e;
                if (i5 < 0) {
                    y1.e0 e0Var = (y1.e0) this.f5138d;
                    if (e0Var == null) {
                        b4.l.j("layoutResult");
                        throw null;
                    }
                    iE = e0Var.e(0);
                } else {
                    y1.e0 e0Var2 = (y1.e0) this.f5138d;
                    if (e0Var2 == null) {
                        b4.l.j("layoutResult");
                        throw null;
                    }
                    int iE2 = e0Var2.e(i5);
                    iE = e(iE2, hVar) == i5 ? iE2 : iE2 + 1;
                }
                y1.e0 e0Var3 = (y1.e0) this.f5138d;
                if (e0Var3 == null) {
                    b4.l.j("layoutResult");
                    throw null;
                }
                if (iE >= e0Var3.f6754b.f6794f) {
                    return null;
                }
                return b(e(iE, hVar), e(iE, j2.h.f3318d) + 1);
        }
    }

    @Override // q1.b
    public final int[] d(int i5) {
        int iE;
        switch (this.f5137c) {
            case 0:
                int length = c().length();
                if (length <= 0 || i5 <= 0) {
                    return null;
                }
                if (i5 > length) {
                    i5 = length;
                }
                do {
                    BreakIterator breakIterator = (BreakIterator) this.f5138d;
                    if (breakIterator == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    if (breakIterator.isBoundary(i5)) {
                        BreakIterator breakIterator2 = (BreakIterator) this.f5138d;
                        if (breakIterator2 == null) {
                            b4.l.j("impl");
                            throw null;
                        }
                        int iPreceding = breakIterator2.preceding(i5);
                        if (iPreceding == -1) {
                            return null;
                        }
                        return b(iPreceding, i5);
                    }
                    BreakIterator breakIterator3 = (BreakIterator) this.f5138d;
                    if (breakIterator3 == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    i5 = breakIterator3.preceding(i5);
                } while (i5 != -1);
                return null;
            case 1:
                int length2 = c().length();
                if (length2 <= 0 || i5 <= 0) {
                    return null;
                }
                if (i5 > length2) {
                    i5 = length2;
                }
                while (i5 > 0 && !h(i5 - 1) && !g(i5)) {
                    BreakIterator breakIterator4 = (BreakIterator) this.f5138d;
                    if (breakIterator4 == null) {
                        b4.l.j("impl");
                        throw null;
                    }
                    i5 = breakIterator4.preceding(i5);
                    if (i5 == -1) {
                        return null;
                    }
                }
                BreakIterator breakIterator5 = (BreakIterator) this.f5138d;
                if (breakIterator5 == null) {
                    b4.l.j("impl");
                    throw null;
                }
                int iPreceding2 = breakIterator5.preceding(i5);
                if (iPreceding2 == -1 || !h(iPreceding2)) {
                    return null;
                }
                if (iPreceding2 == 0 || !h(iPreceding2 - 1)) {
                    return b(iPreceding2, i5);
                }
                return null;
            default:
                if (c().length() <= 0 || i5 <= 0) {
                    return null;
                }
                int length3 = c().length();
                j2.h hVar = j2.h.f3318d;
                if (i5 > length3) {
                    y1.e0 e0Var = (y1.e0) this.f5138d;
                    if (e0Var == null) {
                        b4.l.j("layoutResult");
                        throw null;
                    }
                    iE = e0Var.e(c().length());
                } else {
                    y1.e0 e0Var2 = (y1.e0) this.f5138d;
                    if (e0Var2 == null) {
                        b4.l.j("layoutResult");
                        throw null;
                    }
                    int iE2 = e0Var2.e(i5);
                    iE = e(iE2, hVar) + 1 == i5 ? iE2 : iE2 - 1;
                }
                if (iE < 0) {
                    return null;
                }
                return b(e(iE, j2.h.f3319e), e(iE, hVar) + 1);
        }
    }

    public int e(int i5, j2.h hVar) {
        y1.e0 e0Var = (y1.e0) this.f5138d;
        if (e0Var == null) {
            b4.l.j("layoutResult");
            throw null;
        }
        int iH = e0Var.h(i5);
        y1.e0 e0Var2 = (y1.e0) this.f5138d;
        if (e0Var2 == null) {
            b4.l.j("layoutResult");
            throw null;
        }
        if (hVar != e0Var2.i(iH)) {
            y1.e0 e0Var3 = (y1.e0) this.f5138d;
            if (e0Var3 != null) {
                return e0Var3.h(i5);
            }
            b4.l.j("layoutResult");
            throw null;
        }
        y1.e0 e0Var4 = (y1.e0) this.f5138d;
        if (e0Var4 != null) {
            return e0Var4.d(i5, false) - 1;
        }
        b4.l.j("layoutResult");
        throw null;
    }

    public void f(String str) {
        switch (this.f5137c) {
            case 0:
                this.f5116a = str;
                BreakIterator breakIterator = (BreakIterator) this.f5138d;
                if (breakIterator != null) {
                    breakIterator.setText(str);
                    return;
                } else {
                    b4.l.j("impl");
                    throw null;
                }
            default:
                this.f5116a = str;
                BreakIterator breakIterator2 = (BreakIterator) this.f5138d;
                if (breakIterator2 != null) {
                    breakIterator2.setText(str);
                    return;
                } else {
                    b4.l.j("impl");
                    throw null;
                }
        }
    }

    public boolean g(int i5) {
        if (i5 <= 0 || !h(i5 - 1)) {
            return false;
        }
        return i5 == c().length() || !h(i5);
    }

    public boolean h(int i5) {
        if (i5 < 0 || i5 >= c().length()) {
            return false;
        }
        return Character.isLetterOrDigit(c().codePointAt(i5));
    }
}
