package a2;

import android.graphics.Matrix;
import android.os.Trace;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import androidx.lifecycle.k0;
import b3.a0;
import b3.p;
import b3.s;
import b3.x;
import b3.y;
import b4.l;
import c0.g1;
import f0.h2;
import f0.i;
import f0.q;
import f0.s1;
import f0.w2;
import g0.j0;
import io.github.libxposed.service.IXposedService;
import j.z;
import j1.t;
import j1.w;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.text.BreakIterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import m4.k;
import n1.f0;
import p1.c1;
import p1.d0;
import p1.m;
import p1.n;
import p1.p1;
import p3.j;
import q1.d1;
import q1.m0;
import r0.o;
import y0.l0;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements f, p, l4.d, i, j0, o0.f, d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f48d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f49e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f50f;

    public h(int i5) {
        this.f48d = i5;
        switch (i5) {
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                this.f49e = new k0(17);
                this.f50f = new s();
                break;
            case 5:
                break;
            case 6:
                this.f49e = new z();
                this.f50f = new z();
                break;
            case 7:
                this.f49e = new n0.a(0);
                this.f50f = new f3.a();
                break;
            case 17:
                this.f49e = h1.c.D(n.f4929f);
                this.f50f = new p1(new c1(1));
                break;
            case 18:
                this.f49e = new h(17);
                this.f50f = new h(17);
                break;
            case 21:
                this.f49e = new h0.e(new d0[16]);
                break;
            case 22:
                this.f49e = new x2.i(this);
                break;
            case 24:
                this.f49e = new h0.e(new Reference[16]);
                this.f50f = new ReferenceQueue();
                break;
            default:
                this.f49e = new LinkedHashMap();
                this.f50f = new LinkedHashMap();
                break;
        }
    }

    public h(a1.d dVar) {
        this.f48d = 16;
        this.f49e = dVar;
        this.f50f = androidx.compose.foundation.gestures.a.f87b;
    }

    public h(q qVar) {
        this.f48d = 8;
        this.f49e = qVar;
        this.f50f = new n0.a(0);
    }

    public /* synthetic */ h(Object obj, int i5, Object obj2) {
        this.f48d = i5;
        this.f49e = obj;
        this.f50f = obj2;
    }

    public h(Matcher matcher, CharSequence charSequence) {
        this.f48d = 10;
        l.e("input", charSequence);
        this.f50f = matcher;
        this.f49e = charSequence;
    }

    public h(d0 d0Var, f0 f0Var) {
        this.f48d = 19;
        this.f49e = d0Var;
        this.f50f = f0.l.s(f0Var);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public h(q1.f0 f0Var) {
        this(22);
        this.f48d = 22;
        this.f50f = f0Var;
    }

    public h(float[] fArr) {
        this.f48d = 23;
        this.f49e = fArr;
        this.f50f = new int[2];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v5 */
    public static void r(d0 d0Var) {
        p1.j0 j0Var = d0Var.f4802z;
        int i5 = 0;
        if (j0Var.f4895c == 5 && !j0Var.f4897e && !j0Var.f4896d && !d0Var.G && d0Var.E()) {
            o oVar = d0Var.f4801y.f4972e;
            if ((oVar.f5684g & 256) != 0) {
                while (oVar != null) {
                    if ((oVar.f5683f & 256) != 0) {
                        ?? E = oVar;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof p1.p) {
                                p1.p pVar = (p1.p) E;
                                pVar.D(p1.f.q(pVar, 256));
                            } else if ((E.f5683f & 256) != 0 && (E instanceof m)) {
                                o oVar2 = ((m) E).f4921r;
                                int i6 = 0;
                                while (oVar2 != null) {
                                    if ((oVar2.f5683f & 256) != 0) {
                                        i6++;
                                        if (i6 == 1) {
                                            E = E;
                                            eVar = eVar;
                                            eVar = eVar;
                                            E = oVar2;
                                        } else {
                                            if (eVar == 0) {
                                                eVar = new h0.e(new o[16]);
                                            }
                                            if (E != 0) {
                                                eVar.b(E);
                                                E = 0;
                                            }
                                            eVar.b(oVar2);
                                        }
                                    } else {
                                        E = E;
                                        eVar = eVar;
                                    }
                                    oVar2 = oVar2.f5686i;
                                    E = E;
                                    eVar = eVar;
                                }
                                if (i6 == 1) {
                                    E = E;
                                    eVar = eVar;
                                } else {
                                    E = E;
                                    eVar = eVar;
                                }
                            }
                            E = p1.f.e(eVar);
                        }
                    }
                    if ((oVar.f5684g & 256) == 0) {
                        break;
                    } else {
                        oVar = oVar.f5686i;
                    }
                }
            }
        }
        d0Var.F = false;
        h0.e eVarU = d0Var.u();
        int i7 = eVarU.f2578f;
        if (i7 > 0) {
            Object[] objArr = eVarU.f2576d;
            do {
                r((d0) objArr[i5]);
                i5++;
            } while (i5 < i7);
        }
    }

    @Override // b3.p
    public Object a() {
        return (a0) this.f49e;
    }

    @Override // q1.d1
    public void b(View view, float[] fArr) {
        y0.f0.d(fArr);
        w(view, fArr);
    }

    @Override // f0.i
    public void cancel() {
        if (((n0.a) this.f50f).compareAndSet(1, 1)) {
            return;
        }
        ((q) this.f49e).a();
    }

    @Override // g0.j0
    public List d(Integer num) {
        List listD = ((j0) this.f49e).d(null);
        w2 w2Var = (w2) this.f50f;
        int i5 = w2Var.f2411v;
        return i5 < 0 ? listD : q3.l.e0(j.n(w2Var, num, i5, Integer.valueOf(w2Var.E(w2Var.f2391b, i5))), listD);
    }

    @Override // a2.f
    public int e(int i5) {
        do {
            g gVar = (g) this.f50f;
            gVar.a(i5);
            i5 = ((BreakIterator) gVar.f47e).preceding(i5);
            if (i5 == -1) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f49e).charAt(i5)));
        return i5;
    }

    @Override // o0.f
    public Object f(o0.b bVar, Object obj) {
        return ((a4.e) this.f49e).invoke(bVar, obj);
    }

    @Override // l4.d
    public Object g(l4.e eVar, t3.c cVar) {
        switch (this.f48d) {
            case 3:
                Object objG = ((l4.d) this.f49e).g(new b0.h(eVar, 3, (d0.h) this.f50f), cVar);
                return objG == u3.a.f5838d ? objG : p3.n.f5026a;
            default:
                Object objG2 = ((k) this.f49e).g(new l4.h(new b4.s(), eVar, (h2) this.f50f), cVar);
                return objG2 == u3.a.f5838d ? objG2 : p3.n.f5026a;
        }
    }

    @Override // a2.f
    public int h(int i5) {
        do {
            g gVar = (g) this.f50f;
            gVar.a(i5);
            i5 = ((BreakIterator) gVar.f47e).following(i5);
            if (i5 == -1) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f49e).charAt(i5 - 1)));
        return i5;
    }

    @Override // o0.f
    public Object i(Object obj) {
        return ((a4.c) this.f50f).c(obj);
    }

    @Override // b3.p
    public boolean j(CharSequence charSequence, int i5, int i6, x xVar) {
        if ((xVar.f663c & 4) > 0) {
            return true;
        }
        if (((a0) this.f49e) == null) {
            this.f49e = new a0(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
        }
        ((k0) this.f50f).getClass();
        ((a0) this.f49e).setSpan(new y(xVar), i5, i6, 33);
        return true;
    }

    @Override // a2.f
    public int k(int i5) {
        CharSequence charSequence = (CharSequence) this.f49e;
        do {
            g gVar = (g) this.f50f;
            gVar.a(i5);
            i5 = ((BreakIterator) gVar.f47e).following(i5);
            if (i5 == -1 || i5 == charSequence.length()) {
                return -1;
            }
        } while (Character.isWhitespace(charSequence.charAt(i5)));
        return i5;
    }

    @Override // a2.f
    public int l(int i5) {
        do {
            g gVar = (g) this.f50f;
            gVar.a(i5);
            i5 = ((BreakIterator) gVar.f47e).preceding(i5);
            if (i5 == -1 || i5 == 0) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f49e).charAt(i5 - 1)));
        return i5;
    }

    public boolean m(long j5) {
        Object obj;
        List list = (List) ((h) this.f50f).f49e;
        int size = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i5);
            if (t.a(((w) obj).f3290a, j5)) {
                break;
            }
            i5++;
        }
        w wVar = (w) obj;
        if (wVar != null) {
            return wVar.f3297h;
        }
        return false;
    }

    public void n(d0 d0Var) {
        if (d0Var.D()) {
            ((p1) this.f50f).add(d0Var);
        } else {
            d4.a.L("DepthSortedSet.add called on an unattached node");
            throw null;
        }
    }

    public void o(d0 d0Var, boolean z2) {
        h hVar = (h) this.f50f;
        h hVar2 = (h) this.f49e;
        if (z2) {
            hVar2.n(d0Var);
            hVar.n(d0Var);
        } else {
            if (((p1) hVar2.f50f).contains(d0Var)) {
                return;
            }
            hVar.n(d0Var);
        }
    }

    public e2.x p(List list) {
        e2.i iVar;
        Exception e5;
        try {
            int size = list.size();
            int i5 = 0;
            iVar = null;
            while (i5 < size) {
                try {
                    e2.i iVar2 = (e2.i) list.get(i5);
                    try {
                        iVar2.a((e2.j) this.f50f);
                        i5++;
                        iVar = iVar2;
                    } catch (Exception e6) {
                        e5 = e6;
                        iVar = iVar2;
                        StringBuilder sb = new StringBuilder();
                        StringBuilder sb2 = new StringBuilder("Error while applying EditCommand batch to buffer (length=");
                        sb2.append(((e2.j) this.f50f).f2027a.b());
                        sb2.append(", composition=");
                        sb2.append(((e2.j) this.f50f).c());
                        sb2.append(", selection=");
                        e2.j jVar = (e2.j) this.f50f;
                        sb2.append((Object) g0.g(j.g(jVar.f2028b, jVar.f2029c)));
                        sb2.append("):");
                        sb.append(sb2.toString());
                        sb.append('\n');
                        q3.l.Z(list, sb, new g1(iVar, this), 60);
                        String string = sb.toString();
                        l.d("StringBuilder().apply(builderAction).toString()", string);
                        throw new RuntimeException(string, e5);
                    }
                } catch (Exception e7) {
                    e5 = e7;
                }
            }
            e2.j jVar2 = (e2.j) this.f50f;
            jVar2.getClass();
            y1.f fVar = new y1.f(jVar2.f2027a.toString(), null, 6);
            e2.j jVar3 = (e2.j) this.f50f;
            long jG = j.g(jVar3.f2028b, jVar3.f2029c);
            g0 g0Var = g0.f(((e2.x) this.f49e).f2063b) ? null : new g0(jG);
            e2.x xVar = new e2.x(fVar, g0Var != null ? g0Var.f6770a : j.g(g0.d(jG), g0.e(jG)), ((e2.j) this.f50f).c());
            this.f49e = xVar;
            return xVar;
        } catch (Exception e8) {
            iVar = null;
            e5 = e8;
        }
    }

    public x2.h q(int i5) {
        q1.f0 f0Var = (q1.f0) this.f50f;
        Trace.beginSection("createAccessibilityNodeInfo");
        try {
            x2.h hVarB = q1.f0.b(f0Var, i5);
            if (f0Var.f5190p && i5 == f0Var.f5188n) {
                f0Var.f5189o = hVarB;
            }
            return hVarB;
        } finally {
            Trace.endSection();
        }
    }

    public f0 s() {
        return (f0) ((s1) this.f50f).getValue();
    }

    public e4.d t() {
        Matcher matcher = (Matcher) this.f50f;
        return d4.a.N(matcher.start(), matcher.end());
    }

    public String toString() {
        switch (this.f48d) {
            case 17:
                return ((p1) this.f50f).toString();
            case 26:
                return "Bounds{lower=" + ((r2.c) this.f49e) + " upper=" + ((r2.c) this.f50f) + "}";
            default:
                return super.toString();
        }
    }

    public boolean u() {
        return !(((p1) ((h) this.f50f).f50f).isEmpty() && ((p1) ((h) this.f49e).f50f).isEmpty());
    }

    public boolean v(d0 d0Var) {
        if (d0Var.D()) {
            return ((p1) this.f50f).remove(d0Var);
        }
        d4.a.L("DepthSortedSet.remove called on an unattached node");
        throw null;
    }

    public void w(View view, float[] fArr) {
        float[] fArr2 = (float[]) this.f49e;
        Object parent = view.getParent();
        if (parent instanceof View) {
            w((View) parent, fArr);
            float f5 = -view.getScrollX();
            float f6 = -view.getScrollY();
            y0.f0.d(fArr2);
            y0.f0.h(fArr2, f5, f6);
            m0.u(fArr, fArr2);
            float left = view.getLeft();
            float top = view.getTop();
            y0.f0.d(fArr2);
            y0.f0.h(fArr2, left, top);
            m0.u(fArr, fArr2);
        } else {
            int[] iArr = (int[]) this.f50f;
            view.getLocationInWindow(iArr);
            float f7 = -view.getScrollX();
            float f8 = -view.getScrollY();
            y0.f0.d(fArr2);
            y0.f0.h(fArr2, f7, f8);
            m0.u(fArr, fArr2);
            float f9 = iArr[0];
            float f10 = iArr[1];
            y0.f0.d(fArr2);
            y0.f0.h(fArr2, f9, f10);
            m0.u(fArr, fArr2);
        }
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            return;
        }
        l0.r(matrix, fArr2);
        m0.u(fArr, fArr2);
    }

    public void x(f0.g1 g1Var) {
        Object objE = ((z) this.f50f).e(g1Var);
        if (objE != null) {
            if (!(objE instanceof j.y)) {
                throw new ClassCastException();
            }
            j.y yVar = (j.y) objE;
            Object[] objArr = yVar.f3174a;
            if (yVar.f3175b <= 0) {
                return;
            }
            l.c("null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap", objArr[0]);
            throw new ClassCastException();
        }
    }
}
