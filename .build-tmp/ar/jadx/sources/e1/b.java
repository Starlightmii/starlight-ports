package e1;

import c0.g1;
import java.util.ArrayList;
import java.util.List;
import y0.f0;
import y0.l0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float[] f1865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1866c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1867d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1868e = y0.u.f6694g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f1869f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1870g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.j f1871h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a4.c f1872i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final g1 f1873j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f1874k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1875l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f1876m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1877n;

    public b() {
        int i5 = a0.f1864a;
        this.f1869f = q3.s.f5477d;
        this.f1870g = true;
        this.f1873j = new g1(3, this);
        this.f1874k = "";
        this.f1875l = 1.0f;
        this.f1876m = 1.0f;
        this.f1877n = true;
    }

    @Override // e1.v
    public final void a(a1.f fVar) {
        if (this.f1877n) {
            float[] fArrA = this.f1865b;
            if (fArrA == null) {
                fArrA = f0.a();
                this.f1865b = fArrA;
            } else {
                f0.d(fArrA);
            }
            f0.h(fArrA, 0.0f, 0.0f);
            f0.e(fArrA, 0.0f);
            f0.f(fArrA, this.f1875l, this.f1876m);
            f0.h(fArrA, -0.0f, -0.0f);
            this.f1877n = false;
        }
        if (this.f1870g) {
            if (!this.f1869f.isEmpty()) {
                y0.j jVarH = this.f1871h;
                if (jVarH == null) {
                    jVarH = l0.h();
                    this.f1871h = jVarH;
                }
                h1.c.P(this.f1869f, jVarH);
            }
            this.f1870g = false;
        }
        a1.b bVarA = fVar.A();
        long jT = bVarA.t();
        bVarA.r().p();
        try {
            a1.b bVar = (a1.b) ((a1.d) bVarA.f23d).f31e;
            float[] fArr = this.f1865b;
            if (fArr != null) {
                bVar.r().r(fArr);
            }
            y0.j jVar = this.f1871h;
            if (!this.f1869f.isEmpty() && jVar != null) {
                bVar.r().a(jVar);
            }
            ArrayList arrayList = this.f1866c;
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                ((v) arrayList.get(i5)).a(fVar);
            }
        } finally {
            bVarA.r().m();
            bVarA.D(jT);
        }
    }

    @Override // e1.v
    public final a4.c b() {
        return this.f1872i;
    }

    @Override // e1.v
    public final void d(g1 g1Var) {
        this.f1872i = g1Var;
    }

    public final void e(int i5, v vVar) {
        ArrayList arrayList = this.f1866c;
        if (i5 < arrayList.size()) {
            arrayList.set(i5, vVar);
        } else {
            arrayList.add(vVar);
        }
        g(vVar);
        vVar.d(this.f1873j);
        c();
    }

    public final void f(long j5) {
        if (this.f1867d && j5 != 16) {
            long j6 = this.f1868e;
            if (j6 == 16) {
                this.f1868e = j5;
                return;
            }
            int i5 = a0.f1864a;
            if (y0.u.h(j6) == y0.u.h(j5) && y0.u.g(j6) == y0.u.g(j5) && y0.u.e(j6) == y0.u.e(j5)) {
                return;
            }
            this.f1867d = false;
            this.f1868e = y0.u.f6694g;
        }
    }

    public final void g(v vVar) {
        if (vVar instanceof h) {
            s0 s0Var = ((h) vVar).f1914b;
            if (this.f1867d && s0Var != null) {
                f(s0Var.f6684a);
                return;
            }
            return;
        }
        if (vVar instanceof b) {
            b bVar = (b) vVar;
            if (bVar.f1867d && this.f1867d) {
                f(bVar.f1868e);
            } else {
                this.f1867d = false;
                this.f1868e = y0.u.f6694g;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("VGroup: ");
        sb.append(this.f1874k);
        ArrayList arrayList = this.f1866c;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            v vVar = (v) arrayList.get(i5);
            sb.append("\t");
            sb.append(vVar.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
