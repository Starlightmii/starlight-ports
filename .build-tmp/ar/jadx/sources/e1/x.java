package e1;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import f0.s1;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1959d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b4.m f1961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s1 f1962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.m f1963h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s1 f1964i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f1965j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f1966k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1967l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w f1968m;

    public x(b bVar) {
        this.f1957b = bVar;
        bVar.f1872i = new w(this, 0);
        this.f1958c = "";
        this.f1959d = true;
        this.f1960e = new a();
        this.f1961f = g.f1912g;
        this.f1962g = f0.l.s(null);
        this.f1964i = f0.l.s(new x0.f(0L));
        this.f1965j = 9205357640488583168L;
        this.f1966k = 1.0f;
        this.f1967l = 1.0f;
        this.f1968m = new w(this, 1);
    }

    @Override // e1.v
    public final void a(a1.f fVar) {
        e(fVar, 1.0f, null);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0039  */
    /* JADX WARN: Code duplicated, block: B:33:0x005d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x005f  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:37:0x0070  */
    /* JADX WARN: Code duplicated, block: B:39:0x0081  */
    /* JADX WARN: Code duplicated, block: B:50:0x0103  */
    public final void e(a1.f fVar, float f5, y0.m mVar) {
        int i5;
        y0.m mVar2;
        y0.g gVarF;
        Throwable th;
        char c5;
        long j5;
        a1.f fVar2;
        long j6;
        ColorFilter porterDuffColorFilter;
        y0.m mVar3;
        int i6;
        int i7;
        b bVar = this.f1957b;
        boolean z2 = bVar.f1867d;
        s1 s1Var = this.f1962g;
        if (!z2 || bVar.f1868e == 16) {
            i5 = 0;
        } else {
            y0.m mVar4 = (y0.m) s1Var.getValue();
            int i8 = a0.f1864a;
            if (mVar4 == null ? mVar4 != null : !((i7 = mVar4.f6648c) == 5 || i7 == 3)) {
                i5 = 0;
            } else if (mVar == null ? mVar != null : !((i6 = mVar.f6648c) == 5 || i6 == 3)) {
                i5 = 0;
            } else {
                i5 = 1;
            }
        }
        boolean z3 = this.f1959d;
        a aVar = this.f1960e;
        if (z3 || !x0.f.a(this.f1965j, fVar.b())) {
            if (i5 == 1) {
                j6 = bVar.f1868e;
                if (Build.VERSION.SDK_INT >= 29) {
                    porterDuffColorFilter = y0.n.f6649a.a(j6, 5);
                } else {
                    porterDuffColorFilter = new PorterDuffColorFilter(l0.w(j6), l0.z(5));
                }
                mVar2 = new y0.m(j6, 5, porterDuffColorFilter);
            } else {
                mVar2 = null;
            }
            this.f1963h = mVar2;
            float fD = x0.f.d(fVar.b());
            s1 s1Var2 = this.f1964i;
            this.f1966k = fD / x0.f.d(((x0.f) s1Var2.getValue()).f6546a);
            this.f1967l = x0.f.b(fVar.b()) / x0.f.b(((x0.f) s1Var2.getValue()).f6546a);
            long jF = a.a.f((int) Math.ceil(x0.f.d(fVar.b())), (int) Math.ceil(x0.f.b(fVar.b())));
            k2.k layoutDirection = fVar.getLayoutDirection();
            gVarF = aVar.f1859a;
            y0.d dVarA = aVar.f1860b;
            if (gVarF != null || dVarA == null) {
                th = null;
                c5 = ' ';
                j5 = 4294967295L;
            } else {
                th = null;
                c5 = ' ';
                int i9 = (int) (jF >> 32);
                Bitmap bitmap = gVarF.f6627a;
                j5 = 4294967295L;
                if (i9 > bitmap.getWidth() || ((int) (jF & 4294967295L)) > bitmap.getHeight() || aVar.f1862d != i5) {
                }
                aVar.f1861c = jF;
                a1.c cVar = aVar.f1863e;
                long jO = a.a.O(jF);
                a1.a aVar2 = cVar.f26d;
                k2.b bVar2 = aVar2.f18a;
                k2.k kVar = aVar2.f19b;
                y0.r rVar = aVar2.f20c;
                long j7 = aVar2.f21d;
                fVar2 = fVar;
                aVar2.f18a = fVar2;
                aVar2.f19b = layoutDirection;
                aVar2.f20c = dVarA;
                aVar2.f21d = jO;
                dVarA.p();
                a1.f.B(cVar, y0.u.f6689b, 0L, 62);
                this.f1968m.c(cVar);
                dVarA.m();
                a1.a aVar3 = cVar.f26d;
                aVar3.f18a = bVar2;
                aVar3.f19b = kVar;
                aVar3.f20c = rVar;
                aVar3.f21d = j7;
                gVarF.f6627a.prepareToDraw();
                this.f1959d = false;
                this.f1965j = fVar2.b();
            }
            gVarF = l0.f((int) (jF >> c5), (int) (jF & j5), i5);
            dVarA = l0.a(gVarF);
            aVar.f1859a = gVarF;
            aVar.f1860b = dVarA;
            aVar.f1862d = i5;
            aVar.f1861c = jF;
            a1.c cVar2 = aVar.f1863e;
            long jO2 = a.a.O(jF);
            a1.a aVar4 = cVar2.f26d;
            k2.b bVar3 = aVar4.f18a;
            k2.k kVar2 = aVar4.f19b;
            y0.r rVar2 = aVar4.f20c;
            long j8 = aVar4.f21d;
            fVar2 = fVar;
            aVar4.f18a = fVar2;
            aVar4.f19b = layoutDirection;
            aVar4.f20c = dVarA;
            aVar4.f21d = jO2;
            dVarA.p();
            a1.f.B(cVar2, y0.u.f6689b, 0L, 62);
            this.f1968m.c(cVar2);
            dVarA.m();
            a1.a aVar5 = cVar2.f26d;
            aVar5.f18a = bVar3;
            aVar5.f19b = kVar2;
            aVar5.f20c = rVar2;
            aVar5.f21d = j8;
            gVarF.f6627a.prepareToDraw();
            this.f1959d = false;
            this.f1965j = fVar2.b();
        } else {
            y0.g gVar = aVar.f1859a;
            if (i5 == (gVar != null ? gVar.a() : 0)) {
                fVar2 = fVar;
                th = null;
            } else {
                if (i5 == 1) {
                    j6 = bVar.f1868e;
                    if (Build.VERSION.SDK_INT >= 29) {
                        porterDuffColorFilter = y0.n.f6649a.a(j6, 5);
                    } else {
                        porterDuffColorFilter = new PorterDuffColorFilter(l0.w(j6), l0.z(5));
                    }
                    mVar2 = new y0.m(j6, 5, porterDuffColorFilter);
                } else {
                    mVar2 = null;
                }
                this.f1963h = mVar2;
                float fD2 = x0.f.d(fVar.b());
                s1 s1Var3 = this.f1964i;
                this.f1966k = fD2 / x0.f.d(((x0.f) s1Var3.getValue()).f6546a);
                this.f1967l = x0.f.b(fVar.b()) / x0.f.b(((x0.f) s1Var3.getValue()).f6546a);
                long jF2 = a.a.f((int) Math.ceil(x0.f.d(fVar.b())), (int) Math.ceil(x0.f.b(fVar.b())));
                k2.k layoutDirection2 = fVar.getLayoutDirection();
                gVarF = aVar.f1859a;
                y0.d dVarA2 = aVar.f1860b;
                if (gVarF != null) {
                    th = null;
                    c5 = ' ';
                    j5 = 4294967295L;
                    gVarF = l0.f((int) (jF2 >> c5), (int) (jF2 & j5), i5);
                    dVarA2 = l0.a(gVarF);
                    aVar.f1859a = gVarF;
                    aVar.f1860b = dVarA2;
                    aVar.f1862d = i5;
                } else {
                    th = null;
                    c5 = ' ';
                    j5 = 4294967295L;
                    gVarF = l0.f((int) (jF2 >> c5), (int) (jF2 & j5), i5);
                    dVarA2 = l0.a(gVarF);
                    aVar.f1859a = gVarF;
                    aVar.f1860b = dVarA2;
                    aVar.f1862d = i5;
                }
                aVar.f1861c = jF2;
                a1.c cVar3 = aVar.f1863e;
                long jO3 = a.a.O(jF2);
                a1.a aVar6 = cVar3.f26d;
                k2.b bVar4 = aVar6.f18a;
                k2.k kVar3 = aVar6.f19b;
                y0.r rVar3 = aVar6.f20c;
                long j9 = aVar6.f21d;
                fVar2 = fVar;
                aVar6.f18a = fVar2;
                aVar6.f19b = layoutDirection2;
                aVar6.f20c = dVarA2;
                aVar6.f21d = jO3;
                dVarA2.p();
                a1.f.B(cVar3, y0.u.f6689b, 0L, 62);
                this.f1968m.c(cVar3);
                dVarA2.m();
                a1.a aVar7 = cVar3.f26d;
                aVar7.f18a = bVar4;
                aVar7.f19b = kVar3;
                aVar7.f20c = rVar3;
                aVar7.f21d = j9;
                gVarF.f6627a.prepareToDraw();
                this.f1959d = false;
                this.f1965j = fVar2.b();
            }
        }
        if (mVar != null) {
            mVar3 = mVar;
        } else {
            mVar3 = ((y0.m) s1Var.getValue()) != null ? (y0.m) s1Var.getValue() : this.f1963h;
        }
        y0.g gVar2 = aVar.f1859a;
        if (gVar2 != null) {
            a1.f.Q(fVar2, gVar2, aVar.f1861c, 0L, f5, mVar3, 858);
        } else {
            d4.a.L("drawCachedImage must be invoked first before attempting to draw the result into another destination");
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Params: \tname: ");
        sb.append(this.f1958c);
        sb.append("\n\tviewportWidth: ");
        s1 s1Var = this.f1964i;
        sb.append(x0.f.d(((x0.f) s1Var.getValue()).f6546a));
        sb.append("\n\tviewportHeight: ");
        sb.append(x0.f.b(((x0.f) s1Var.getValue()).f6546a));
        sb.append("\n");
        String string = sb.toString();
        b4.l.d("StringBuilder().apply(builderAction).toString()", string);
        return string;
    }
}
