package y1;

import android.graphics.Matrix;
import android.graphics.Shader;
import java.util.ArrayList;
import java.util.List;
import y0.o0;
import y0.p0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f3.a f6789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f6791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f6792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f6793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6794f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f6795g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f6796h;

    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object, java.util.List] */
    public m(f3.a aVar, long j5, int i5, boolean z2) {
        boolean z3;
        int iH;
        this.f6789a = aVar;
        this.f6790b = i5;
        if (k2.a.k(j5) != 0 || k2.a.j(j5) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) aVar.f2453e;
        int size = arrayList2.size();
        int i6 = 0;
        int i7 = 0;
        float f5 = 0.0f;
        while (true) {
            if (i6 >= size) {
                z3 = false;
                break;
            }
            p pVar = (p) arrayList2.get(i6);
            g2.d dVar = pVar.f6806a;
            int i8 = k2.a.i(j5);
            if (k2.a.d(j5)) {
                iH = k2.a.h(j5) - ((int) Math.ceil(f5));
                if (iH < 0) {
                    iH = 0;
                }
            } else {
                iH = k2.a.h(j5);
            }
            a aVar2 = new a(dVar, this.f6790b - i7, z2, h1.c.c(i8, iH, 5));
            float fB = aVar2.b() + f5;
            z1.y yVar = aVar2.f6706d;
            int i9 = i7 + yVar.f7156f;
            arrayList.add(new o(aVar2, pVar.f6807b, pVar.f6808c, i7, i9, f5, fB));
            if (yVar.f7153c || (i9 == this.f6790b && i6 != p3.j.z((ArrayList) this.f6789a.f2453e))) {
                z3 = true;
                i7 = i9;
                f5 = fB;
                break;
            } else {
                i6++;
                i7 = i9;
                f5 = fB;
            }
        }
        this.f6793e = f5;
        this.f6794f = i7;
        this.f6791c = z3;
        this.f6796h = arrayList;
        this.f6792d = k2.a.i(j5);
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i10 = 0; i10 < size2; i10++) {
            o oVar = (o) arrayList.get(i10);
            ?? r7 = oVar.f6799a.f6708f;
            ArrayList arrayList4 = new ArrayList(r7.size());
            int size3 = r7.size();
            for (int i11 = 0; i11 < size3; i11++) {
                x0.d dVar2 = (x0.d) r7.get(i11);
                arrayList4.add(dVar2 != null ? dVar2.h(p3.j.b(0.0f, oVar.f6804f)) : null);
            }
            q3.q.U(arrayList3, arrayList4);
        }
        if (arrayList3.size() < ((List) this.f6789a.f2450b).size()) {
            int size4 = ((List) this.f6789a.f2450b).size() - arrayList3.size();
            ArrayList arrayList5 = new ArrayList(size4);
            for (int i12 = 0; i12 < size4; i12++) {
                arrayList5.add(null);
            }
            arrayList3 = q3.l.e0(arrayList3, arrayList5);
        }
        this.f6795g = arrayList3;
    }

    public static void g(m mVar, y0.r rVar, y0.p pVar, float f5, p0 p0Var, j2.j jVar, a1.g gVar) {
        rVar.p();
        ArrayList arrayList = mVar.f6796h;
        if (arrayList.size() <= 1 || (pVar instanceof s0)) {
            g2.k.a(mVar, rVar, pVar, f5, p0Var, jVar, gVar);
        } else if (pVar instanceof o0) {
            int size = arrayList.size();
            float fMax = 0.0f;
            float fB = 0.0f;
            for (int i5 = 0; i5 < size; i5++) {
                o oVar = (o) arrayList.get(i5);
                fB += oVar.f6799a.b();
                fMax = Math.max(fMax, oVar.f6799a.d());
            }
            Shader shaderB = ((o0) pVar).b(p3.j.f(fMax, fB));
            Matrix matrix = new Matrix();
            shaderB.getLocalMatrix(matrix);
            int size2 = arrayList.size();
            for (int i6 = 0; i6 < size2; i6++) {
                a aVar = ((o) arrayList.get(i6)).f6799a;
                aVar.g(rVar, new y0.q(shaderB), f5, p0Var, jVar, gVar);
                rVar.k(0.0f, aVar.b());
                matrix.setTranslate(0.0f, -aVar.b());
                shaderB.setLocalMatrix(matrix);
            }
        }
        rVar.m();
    }

    public final void a(long j5, float[] fArr) {
        h(g0.e(j5));
        i(g0.d(j5));
        b4.u uVar = new b4.u();
        uVar.f695d = 0;
        p3.p.o(this.f6796h, j5, new n.n(j5, fArr, uVar, new b4.t()));
    }

    public final float b(int i5) {
        j(i5);
        ArrayList arrayList = this.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        return aVar.f6706d.e(i5 - oVar.f6802d) + oVar.f6804f;
    }

    public final int c(float f5) {
        ArrayList arrayList = this.f6796h;
        o oVar = (o) arrayList.get(p3.p.n(arrayList, f5));
        int i5 = oVar.f6801c - oVar.f6800b;
        int i6 = oVar.f6802d;
        if (i5 == 0) {
            return i6;
        }
        a aVar = oVar.f6799a;
        float f6 = f5 - oVar.f6804f;
        z1.y yVar = aVar.f6706d;
        return yVar.f7155e.getLineForVertical(((int) f6) - yVar.f7157g) + i6;
    }

    public final float d(int i5) {
        j(i5);
        ArrayList arrayList = this.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        return aVar.f6706d.g(i5 - oVar.f6802d) + oVar.f6804f;
    }

    public final int e(long j5) {
        float fE = x0.c.e(j5);
        ArrayList arrayList = this.f6796h;
        o oVar = (o) arrayList.get(p3.p.n(arrayList, fE));
        int i5 = oVar.f6801c;
        int i6 = oVar.f6800b;
        if (i5 - i6 == 0) {
            return i6;
        }
        a aVar = oVar.f6799a;
        long jB = p3.j.b(x0.c.d(j5), x0.c.e(j5) - oVar.f6804f);
        z1.y yVar = aVar.f6706d;
        int lineForVertical = yVar.f7155e.getLineForVertical(((int) x0.c.e(jB)) - yVar.f7157g);
        return yVar.f7155e.getOffsetForHorizontal(lineForVertical, (yVar.b(lineForVertical) * (-1)) + x0.c.d(jB)) + i6;
    }

    public final long f(x0.d dVar, int i5, e2.f0 f0Var) {
        long jA;
        long j5;
        float f5 = dVar.f6535b;
        ArrayList arrayList = this.f6796h;
        int iN = p3.p.n(arrayList, f5);
        float f6 = ((o) arrayList.get(iN)).f6805g;
        float f7 = dVar.f6537d;
        if (f6 >= f7 || iN == p3.j.z(arrayList)) {
            o oVar = (o) arrayList.get(iN);
            return oVar.a(oVar.f6799a.c(dVar.h(p3.j.b(0.0f, -oVar.f6804f)), i5, f0Var), true);
        }
        int iN2 = p3.p.n(arrayList, f7);
        long jA2 = g0.f6768b;
        while (true) {
            jA = g0.f6768b;
            if (!g0.a(jA2, jA) || iN > iN2) {
                break;
            }
            o oVar2 = (o) arrayList.get(iN);
            jA2 = oVar2.a(oVar2.f6799a.c(dVar.h(p3.j.b(0.0f, -oVar2.f6804f)), i5, f0Var), true);
            iN++;
        }
        if (g0.a(jA2, jA)) {
            return jA;
        }
        while (true) {
            j5 = g0.f6768b;
            if (!g0.a(jA, j5) || iN > iN2) {
                break;
            }
            o oVar3 = (o) arrayList.get(iN2);
            jA = oVar3.a(oVar3.f6799a.c(dVar.h(p3.j.b(0.0f, -oVar3.f6804f)), i5, f0Var), true);
            iN2--;
        }
        return g0.a(jA, j5) ? jA2 : p3.j.g((int) (jA2 >> 32), (int) (4294967295L & jA));
    }

    public final void h(int i5) {
        f3.a aVar = this.f6789a;
        if (i5 < 0 || i5 >= ((f) aVar.f2449a).f6759d.length()) {
            StringBuilder sbG = b.b.g("offset(", i5, ") is out of bounds [0, ");
            sbG.append(((f) aVar.f2449a).f6759d.length());
            sbG.append(')');
            throw new IllegalArgumentException(sbG.toString().toString());
        }
    }

    public final void i(int i5) {
        f3.a aVar = this.f6789a;
        if (i5 < 0 || i5 > ((f) aVar.f2449a).f6759d.length()) {
            StringBuilder sbG = b.b.g("offset(", i5, ") is out of bounds [0, ");
            sbG.append(((f) aVar.f2449a).f6759d.length());
            sbG.append(']');
            throw new IllegalArgumentException(sbG.toString().toString());
        }
    }

    public final void j(int i5) {
        int i6 = this.f6794f;
        if (i5 < 0 || i5 >= i6) {
            throw new IllegalArgumentException(("lineIndex(" + i5 + ") is out of bounds [0, " + i6 + ')').toString());
        }
    }
}
