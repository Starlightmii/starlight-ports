package x;

import android.graphics.PointF;
import android.os.CancellationSignal;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import q1.o2;
import q1.z0;
import v.e1;
import v.f0;
import v.o0;
import y0.l0;
import y1.c0;
import y1.d0;
import y1.g0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f6474a = new n();

    private final void C(o0 o0Var, SelectGesture selectGesture, e0 e0Var) {
        if (e0Var != null) {
            long jS = p3.p.s(o0Var, l0.y(selectGesture.getSelectionArea()), G(selectGesture.getGranularity()));
            o0 o0Var2 = e0Var.f6930d;
            if (o0Var2 != null) {
                o0Var2.f(jS);
            }
            o0 o0Var3 = e0Var.f6930d;
            if (o0Var3 != null) {
                o0Var3.e(g0.f6768b);
            }
            if (g0.b(jS)) {
                return;
            }
            e0Var.p(false);
            e0Var.n(f0.f5917d);
        }
    }

    private final void D(y yVar, SelectGesture selectGesture, x xVar) {
        l0.y(selectGesture.getSelectionArea());
        G(selectGesture.getGranularity());
        throw null;
    }

    private final void E(o0 o0Var, SelectRangeGesture selectRangeGesture, e0 e0Var) {
        if (e0Var != null) {
            long jE = p3.p.e(o0Var, l0.y(selectRangeGesture.getSelectionStartArea()), l0.y(selectRangeGesture.getSelectionEndArea()), G(selectRangeGesture.getGranularity()));
            o0 o0Var2 = e0Var.f6930d;
            if (o0Var2 != null) {
                o0Var2.f(jE);
            }
            o0 o0Var3 = e0Var.f6930d;
            if (o0Var3 != null) {
                o0Var3.e(g0.f6768b);
            }
            if (g0.b(jE)) {
                return;
            }
            e0Var.p(false);
            e0Var.n(f0.f5917d);
        }
    }

    private final void F(y yVar, SelectRangeGesture selectRangeGesture, x xVar) {
        l0.y(selectRangeGesture.getSelectionStartArea());
        l0.y(selectRangeGesture.getSelectionEndArea());
        G(selectRangeGesture.getGranularity());
        throw null;
    }

    private final int G(int i5) {
        return i5 != 1 ? 0 : 1;
    }

    private final int a(y yVar, HandwritingGesture handwritingGesture) {
        throw null;
    }

    private final int b(HandwritingGesture handwritingGesture, a4.c cVar) {
        String fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        cVar.c(new e2.a(fallbackText, 1));
        return 5;
    }

    private final int c(o0 o0Var, DeleteGesture deleteGesture, y1.f fVar, a4.c cVar) {
        int iG = G(deleteGesture.getGranularity());
        long jS = p3.p.s(o0Var, l0.y(deleteGesture.getDeletionArea()), iG);
        if (g0.b(jS)) {
            return f6474a.b(l.j(deleteGesture), cVar);
        }
        h(jS, fVar, iG == 1, cVar);
        return 1;
    }

    private final int d(y yVar, DeleteGesture deleteGesture, x xVar) {
        G(deleteGesture.getGranularity());
        l0.y(deleteGesture.getDeletionArea());
        throw null;
    }

    private final int e(o0 o0Var, DeleteRangeGesture deleteRangeGesture, y1.f fVar, a4.c cVar) {
        int iG = G(deleteRangeGesture.getGranularity());
        long jE = p3.p.e(o0Var, l0.y(deleteRangeGesture.getDeletionStartArea()), l0.y(deleteRangeGesture.getDeletionEndArea()), iG);
        if (g0.b(jE)) {
            return f6474a.b(l.j(deleteRangeGesture), cVar);
        }
        h(jE, fVar, iG == 1, cVar);
        return 1;
    }

    private final int f(y yVar, DeleteRangeGesture deleteRangeGesture, x xVar) {
        G(deleteRangeGesture.getGranularity());
        l0.y(deleteRangeGesture.getDeletionStartArea());
        l0.y(deleteRangeGesture.getDeletionEndArea());
        throw null;
    }

    private final void g(y yVar, long j5, boolean z2) {
        if (!z2) {
            throw null;
        }
        throw null;
    }

    private final void h(long j5, y1.f fVar, boolean z2, a4.c cVar) {
        if (z2) {
            int i5 = g0.f6769c;
            int iCharCount = (int) (j5 >> 32);
            int iCharCount2 = (int) (j5 & 4294967295L);
            int iCodePointBefore = iCharCount > 0 ? Character.codePointBefore(fVar, iCharCount) : 10;
            int iCodePointAt = iCharCount2 < fVar.f6759d.length() ? Character.codePointAt(fVar, iCharCount2) : 10;
            if (p3.p.x(iCodePointBefore) && (p3.p.w(iCodePointAt) || p3.p.u(iCodePointAt))) {
                do {
                    iCharCount -= Character.charCount(iCodePointBefore);
                    if (iCharCount == 0) {
                        break;
                    } else {
                        iCodePointBefore = Character.codePointBefore(fVar, iCharCount);
                    }
                } while (p3.p.x(iCodePointBefore));
                j5 = p3.j.g(iCharCount, iCharCount2);
            } else if (p3.p.x(iCodePointAt) && (p3.p.w(iCodePointBefore) || p3.p.u(iCodePointBefore))) {
                do {
                    iCharCount2 += Character.charCount(iCodePointAt);
                    if (iCharCount2 == fVar.f6759d.length()) {
                        break;
                    } else {
                        iCodePointAt = Character.codePointAt(fVar, iCharCount2);
                    }
                } while (p3.p.x(iCodePointAt));
                j5 = p3.j.g(iCharCount, iCharCount2);
            }
        }
        int i6 = (int) (4294967295L & j5);
        cVar.c(new o(new e2.i[]{new e2.w(i6, i6), new e2.g(g0.c(j5), 0)}));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0049  */
    private final int k(o0 o0Var, InsertGesture insertGesture, o2 o2Var, a4.c cVar) {
        int iE;
        e1 e1VarD;
        long jK;
        int iQ;
        if (o2Var == null) {
            return b(l.j(insertGesture), cVar);
        }
        PointF insertionPoint = insertGesture.getInsertionPoint();
        long jB = p3.j.b(insertionPoint.x, insertionPoint.y);
        e1 e1VarD2 = o0Var.d();
        if (e1VarD2 != null) {
            y1.m mVar = e1VarD2.f5899a.f6754b;
            n1.p pVarC = o0Var.c();
            if (pVarC == null || (iQ = p3.p.q(mVar, (jK = pVarC.K(jB)), o2Var)) == -1) {
                iE = -1;
            } else {
                iE = mVar.e(x0.c.a(jK, (mVar.b(iQ) + mVar.d(iQ)) / 2.0f, 1));
            }
        } else {
            iE = -1;
        }
        if (iE == -1 || ((e1VarD = o0Var.d()) != null && p3.p.f(e1VarD.f5899a, iE))) {
            return b(l.j(insertGesture), cVar);
        }
        m(iE, insertGesture.getTextToInsert(), cVar);
        return 1;
    }

    private final int l(y yVar, InsertGesture insertGesture, x xVar, o2 o2Var) {
        PointF insertionPoint = insertGesture.getInsertionPoint();
        p3.j.b(insertionPoint.x, insertionPoint.y);
        throw null;
    }

    private final void m(int i5, String str, a4.c cVar) {
        cVar.c(new o(new e2.i[]{new e2.w(i5, i5), new e2.a(str, 1)}));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0049  */
    private final int n(o0 o0Var, JoinOrSplitGesture joinOrSplitGesture, y1.f fVar, o2 o2Var, a4.c cVar) {
        int iCharCount;
        e1 e1VarD;
        long jK;
        int iQ;
        if (o2Var == null) {
            return b(l.j(joinOrSplitGesture), cVar);
        }
        PointF joinOrSplitPoint = joinOrSplitGesture.getJoinOrSplitPoint();
        long jB = p3.j.b(joinOrSplitPoint.x, joinOrSplitPoint.y);
        e1 e1VarD2 = o0Var.d();
        if (e1VarD2 != null) {
            y1.m mVar = e1VarD2.f5899a.f6754b;
            n1.p pVarC = o0Var.c();
            if (pVarC == null || (iQ = p3.p.q(mVar, (jK = pVarC.K(jB)), o2Var)) == -1) {
                iCharCount = -1;
            } else {
                iCharCount = mVar.e(x0.c.a(jK, (mVar.b(iQ) + mVar.d(iQ)) / 2.0f, 1));
            }
        } else {
            iCharCount = -1;
        }
        if (iCharCount == -1 || ((e1VarD = o0Var.d()) != null && p3.p.f(e1VarD.f5899a, iCharCount))) {
            return b(l.j(joinOrSplitGesture), cVar);
        }
        int iCharCount2 = iCharCount;
        while (iCharCount2 > 0) {
            int iCodePointBefore = Character.codePointBefore(fVar, iCharCount2);
            if (!p3.p.w(iCodePointBefore)) {
                break;
            }
            iCharCount2 -= Character.charCount(iCodePointBefore);
        }
        while (iCharCount < fVar.f6759d.length()) {
            int iCodePointAt = Character.codePointAt(fVar, iCharCount);
            if (!p3.p.w(iCodePointAt)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        long jG = p3.j.g(iCharCount2, iCharCount);
        if (g0.b(jG)) {
            m((int) (jG >> 32), " ", cVar);
            return 1;
        }
        h(jG, fVar, false, cVar);
        return 1;
    }

    private final int o(y yVar, JoinOrSplitGesture joinOrSplitGesture, x xVar, o2 o2Var) {
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0088  */
    private final int p(o0 o0Var, RemoveSpaceGesture removeSpaceGesture, y1.f fVar, o2 o2Var, a4.c cVar) {
        long jF;
        int i5;
        int i6;
        int i7;
        int i8;
        String string;
        e1 e1VarD = o0Var.d();
        y1.e0 e0Var = e1VarD != null ? e1VarD.f5899a : null;
        PointF startPoint = removeSpaceGesture.getStartPoint();
        long jB = p3.j.b(startPoint.x, startPoint.y);
        PointF endPoint = removeSpaceGesture.getEndPoint();
        long jB2 = p3.j.b(endPoint.x, endPoint.y);
        n1.p pVarC = o0Var.c();
        int i9 = 0;
        if (e0Var != null) {
            y1.m mVar = e0Var.f6754b;
            if (pVarC == null) {
                jF = g0.f6768b;
            } else {
                long jK = pVarC.K(jB);
                long jK2 = pVarC.K(jB2);
                int iQ = p3.p.q(mVar, jK, o2Var);
                int iQ2 = p3.p.q(mVar, jK2, o2Var);
                if (iQ != -1) {
                    if (iQ2 != -1) {
                        iQ = Math.min(iQ, iQ2);
                    }
                    iQ2 = iQ;
                } else if (iQ2 == -1) {
                    jF = g0.f6768b;
                }
                float fB = (mVar.b(iQ2) + mVar.d(iQ2)) / 2;
                jF = mVar.f(new x0.d(Math.min(x0.c.d(jK), x0.c.d(jK2)), fB - 0.1f, Math.max(x0.c.d(jK), x0.c.d(jK2)), fB + 0.1f), 0, c0.f6737a);
            }
        } else {
            jF = g0.f6768b;
        }
        if (g0.b(jF)) {
            return f6474a.b(l.j(removeSpaceGesture), cVar);
        }
        String str = fVar.subSequence(g0.e(jF), g0.d(jF)).f6759d;
        Pattern patternCompile = Pattern.compile("\\s+");
        b4.l.d("compile(...)", patternCompile);
        b4.l.e("input", str);
        Matcher matcher = patternCompile.matcher(str);
        b4.l.d("matcher(...)", matcher);
        a2.h hVar = !matcher.find(0) ? null : new a2.h(matcher, str);
        if (hVar == null) {
            string = str.toString();
            i8 = 0;
            i6 = -1;
            i5 = -1;
        } else {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            int i10 = 0;
            i5 = -1;
            while (true) {
                sb.append((CharSequence) str, i10, hVar.t().f2074d);
                if (i5 == -1) {
                    i5 = hVar.t().f2074d;
                }
                i6 = hVar.t().f2075e + 1;
                sb.append((CharSequence) "");
                i7 = hVar.t().f2075e + 1;
                CharSequence charSequence = (CharSequence) hVar.f49e;
                Matcher matcher2 = (Matcher) hVar.f50f;
                i8 = i9;
                int iEnd = matcher2.end() + (matcher2.end() == matcher2.start() ? 1 : i8);
                if (iEnd <= charSequence.length()) {
                    Matcher matcher3 = matcher2.pattern().matcher(charSequence);
                    b4.l.d("matcher(...)", matcher3);
                    hVar = !matcher3.find(iEnd) ? null : new a2.h(matcher3, charSequence);
                } else {
                    hVar = null;
                }
                if (i7 >= length || hVar == null) {
                    break;
                }
                i9 = i8;
                i10 = i7;
            }
            if (i7 < length) {
                sb.append((CharSequence) str, i7, length);
            }
            string = sb.toString();
            b4.l.d("toString(...)", string);
        }
        if (i5 == -1 || i6 == -1) {
            return b(l.j(removeSpaceGesture), cVar);
        }
        int i11 = (int) (jF >> 32);
        String strSubstring = string.substring(i5, string.length() - (g0.c(jF) - i6));
        b4.l.d("this as java.lang.String…ing(startIndex, endIndex)", strSubstring);
        e2.w wVar = new e2.w(i11 + i5, i11 + i6);
        e2.a aVar = new e2.a(strSubstring, 1);
        e2.i[] iVarArr = new e2.i[2];
        iVarArr[i8] = wVar;
        iVarArr[1] = aVar;
        cVar.c(new o(iVarArr));
        return 1;
    }

    private final int q(y yVar, RemoveSpaceGesture removeSpaceGesture, x xVar, o2 o2Var) {
        throw null;
    }

    private final int r(o0 o0Var, SelectGesture selectGesture, e0 e0Var, a4.c cVar) {
        long jS = p3.p.s(o0Var, l0.y(selectGesture.getSelectionArea()), G(selectGesture.getGranularity()));
        if (g0.b(jS)) {
            return f6474a.b(l.j(selectGesture), cVar);
        }
        v(jS, e0Var, cVar);
        return 1;
    }

    private final int s(y yVar, SelectGesture selectGesture, x xVar) {
        l0.y(selectGesture.getSelectionArea());
        G(selectGesture.getGranularity());
        throw null;
    }

    private final int t(o0 o0Var, SelectRangeGesture selectRangeGesture, e0 e0Var, a4.c cVar) {
        long jE = p3.p.e(o0Var, l0.y(selectRangeGesture.getSelectionStartArea()), l0.y(selectRangeGesture.getSelectionEndArea()), G(selectRangeGesture.getGranularity()));
        if (g0.b(jE)) {
            return f6474a.b(l.j(selectRangeGesture), cVar);
        }
        v(jE, e0Var, cVar);
        return 1;
    }

    private final int u(y yVar, SelectRangeGesture selectRangeGesture, x xVar) {
        l0.y(selectRangeGesture.getSelectionStartArea());
        l0.y(selectRangeGesture.getSelectionEndArea());
        G(selectRangeGesture.getGranularity());
        throw null;
    }

    private final void v(long j5, e0 e0Var, a4.c cVar) {
        int i5 = g0.f6769c;
        cVar.c(new e2.w((int) (j5 >> 32), (int) (j5 & 4294967295L)));
        if (e0Var != null) {
            e0Var.f(true);
        }
    }

    private final void w(o0 o0Var, DeleteGesture deleteGesture, e0 e0Var) {
        if (e0Var != null) {
            long jS = p3.p.s(o0Var, l0.y(deleteGesture.getDeletionArea()), G(deleteGesture.getGranularity()));
            o0 o0Var2 = e0Var.f6930d;
            if (o0Var2 != null) {
                o0Var2.e(jS);
            }
            o0 o0Var3 = e0Var.f6930d;
            if (o0Var3 != null) {
                o0Var3.f(g0.f6768b);
            }
            if (g0.b(jS)) {
                return;
            }
            e0Var.p(false);
            e0Var.n(f0.f5917d);
        }
    }

    private final void x(y yVar, DeleteGesture deleteGesture, x xVar) {
        l0.y(deleteGesture.getDeletionArea());
        G(deleteGesture.getGranularity());
        throw null;
    }

    private final void y(o0 o0Var, DeleteRangeGesture deleteRangeGesture, e0 e0Var) {
        if (e0Var != null) {
            long jE = p3.p.e(o0Var, l0.y(deleteRangeGesture.getDeletionStartArea()), l0.y(deleteRangeGesture.getDeletionEndArea()), G(deleteRangeGesture.getGranularity()));
            o0 o0Var2 = e0Var.f6930d;
            if (o0Var2 != null) {
                o0Var2.e(jE);
            }
            o0 o0Var3 = e0Var.f6930d;
            if (o0Var3 != null) {
                o0Var3.f(g0.f6768b);
            }
            if (g0.b(jE)) {
                return;
            }
            e0Var.p(false);
            e0Var.n(f0.f5917d);
        }
    }

    private final void z(y yVar, DeleteRangeGesture deleteRangeGesture, x xVar) {
        l0.y(deleteRangeGesture.getDeletionStartArea());
        l0.y(deleteRangeGesture.getDeletionEndArea());
        G(deleteRangeGesture.getGranularity());
        throw null;
    }

    public final boolean A(o0 o0Var, PreviewableHandwritingGesture previewableHandwritingGesture, e0 e0Var, CancellationSignal cancellationSignal) {
        d0 d0Var;
        y1.f fVar = o0Var.f6015j;
        if (fVar == null) {
            return false;
        }
        e1 e1VarD = o0Var.d();
        if (!fVar.equals((e1VarD == null || (d0Var = e1VarD.f5899a.f6753a) == null) ? null : d0Var.f6743a)) {
            return false;
        }
        if (l.o(previewableHandwritingGesture)) {
            C(o0Var, l.k(previewableHandwritingGesture), e0Var);
        } else if (z0.q(previewableHandwritingGesture)) {
            w(o0Var, z0.g(previewableHandwritingGesture), e0Var);
        } else if (z0.u(previewableHandwritingGesture)) {
            E(o0Var, z0.l(previewableHandwritingGesture), e0Var);
        } else {
            if (!z0.w(previewableHandwritingGesture)) {
                return false;
            }
            y(o0Var, z0.h(previewableHandwritingGesture), e0Var);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new v1.d(1, e0Var));
        return true;
    }

    public final boolean B(y yVar, PreviewableHandwritingGesture previewableHandwritingGesture, x xVar, CancellationSignal cancellationSignal) {
        if (l.o(previewableHandwritingGesture)) {
            D(yVar, l.k(previewableHandwritingGesture), xVar);
        } else if (z0.q(previewableHandwritingGesture)) {
            x(yVar, z0.g(previewableHandwritingGesture), xVar);
        } else if (z0.u(previewableHandwritingGesture)) {
            F(yVar, z0.l(previewableHandwritingGesture), xVar);
        } else {
            if (!z0.w(previewableHandwritingGesture)) {
                return false;
            }
            z(yVar, z0.h(previewableHandwritingGesture), xVar);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new m());
        return true;
    }

    public final int i(o0 o0Var, HandwritingGesture handwritingGesture, e0 e0Var, o2 o2Var, a4.c cVar) {
        d0 d0Var;
        y1.f fVar = o0Var.f6015j;
        if (fVar == null) {
            return 3;
        }
        e1 e1VarD = o0Var.d();
        if (!fVar.equals((e1VarD == null || (d0Var = e1VarD.f5899a.f6753a) == null) ? null : d0Var.f6743a)) {
            return 3;
        }
        if (l.o(handwritingGesture)) {
            return r(o0Var, l.k(handwritingGesture), e0Var, cVar);
        }
        if (z0.q(handwritingGesture)) {
            return c(o0Var, z0.g(handwritingGesture), fVar, cVar);
        }
        if (z0.u(handwritingGesture)) {
            return t(o0Var, z0.l(handwritingGesture), e0Var, cVar);
        }
        if (z0.w(handwritingGesture)) {
            return e(o0Var, z0.h(handwritingGesture), fVar, cVar);
        }
        if (z0.C(handwritingGesture)) {
            return n(o0Var, z0.j(handwritingGesture), fVar, o2Var, cVar);
        }
        if (z0.y(handwritingGesture)) {
            return k(o0Var, z0.i(handwritingGesture), o2Var, cVar);
        }
        if (z0.A(handwritingGesture)) {
            return p(o0Var, z0.k(handwritingGesture), fVar, o2Var, cVar);
        }
        return 2;
    }

    public final int j(y yVar, HandwritingGesture handwritingGesture, x xVar, o2 o2Var) {
        if (l.o(handwritingGesture)) {
            return s(yVar, l.k(handwritingGesture), xVar);
        }
        if (z0.q(handwritingGesture)) {
            return d(yVar, z0.g(handwritingGesture), xVar);
        }
        if (z0.u(handwritingGesture)) {
            return u(yVar, z0.l(handwritingGesture), xVar);
        }
        if (z0.w(handwritingGesture)) {
            return f(yVar, z0.h(handwritingGesture), xVar);
        }
        if (z0.C(handwritingGesture)) {
            return o(yVar, z0.j(handwritingGesture), xVar, o2Var);
        }
        if (z0.y(handwritingGesture)) {
            return l(yVar, z0.i(handwritingGesture), xVar, o2Var);
        }
        if (z0.A(handwritingGesture)) {
            return q(yVar, z0.k(handwritingGesture), xVar, o2Var);
        }
        return 2;
    }
}
