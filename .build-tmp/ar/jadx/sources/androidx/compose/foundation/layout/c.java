package androidx.compose.foundation.layout;

import b4.l;
import c0.j1;
import r0.g;
import r0.h;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final FillElement f113a = new FillElement(1.0f, 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final FillElement f114b = new FillElement(1.0f, 3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final WrapContentElement f115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final WrapContentElement f116d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final WrapContentElement f117e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final WrapContentElement f118f;

    static {
        g gVar = r0.b.f5663n;
        f115c = new WrapContentElement(1, new j1(8, gVar), gVar);
        g gVar2 = r0.b.f5662m;
        f116d = new WrapContentElement(1, new j1(8, gVar2), gVar2);
        h hVar = r0.b.f5657h;
        int i5 = 9;
        f117e = new WrapContentElement(3, new j1(i5, hVar), hVar);
        h hVar2 = r0.b.f5653d;
        f118f = new WrapContentElement(3, new j1(i5, hVar2), hVar2);
    }

    public static final p a(p pVar, float f5, float f6) {
        return pVar.e(new UnspecifiedConstraintsElement(f5, f6));
    }

    public static /* synthetic */ p b(p pVar, float f5, float f6, int i5) {
        if ((i5 & 1) != 0) {
            f5 = Float.NaN;
        }
        if ((i5 & 2) != 0) {
            f6 = Float.NaN;
        }
        return a(pVar, f5, f6);
    }

    public static final p c(p pVar, float f5) {
        return pVar.e(f5 == 1.0f ? f114b : new FillElement(f5, 3));
    }

    public static final p d(p pVar, float f5) {
        return pVar.e(new SizeElement(0.0f, f5, 0.0f, f5, 5));
    }

    public static final p e(p pVar, float f5, float f6) {
        return pVar.e(new SizeElement(0.0f, f5, 0.0f, f6, 5));
    }

    public static final p f(p pVar, float f5, float f6) {
        return pVar.e(new SizeElement(f5, f6, f5, f6, false));
    }

    public static p g(p pVar, float f5, float f6, float f7, float f8, int i5) {
        return pVar.e(new SizeElement(f5, (i5 & 2) != 0 ? Float.NaN : f6, (i5 & 4) != 0 ? Float.NaN : f7, (i5 & 8) != 0 ? Float.NaN : f8, false));
    }

    public static final p h(p pVar, float f5) {
        return pVar.e(new SizeElement(f5, f5, f5, f5, true));
    }

    public static final p i(p pVar, float f5, float f6) {
        return pVar.e(new SizeElement(f5, f6, f5, f6, true));
    }

    public static final p j(p pVar, float f5, float f6, float f7, float f8) {
        return pVar.e(new SizeElement(f5, f6, f7, f8, true));
    }

    public static final p k(p pVar, float f5) {
        return pVar.e(new SizeElement(f5, 0.0f, f5, 0.0f, 10));
    }

    public static p l(p pVar) {
        WrapContentElement wrapContentElement;
        g gVar = r0.b.f5663n;
        if (l.a(gVar, gVar)) {
            wrapContentElement = f115c;
        } else {
            wrapContentElement = l.a(gVar, r0.b.f5662m) ? f116d : new WrapContentElement(1, new j1(8, gVar), gVar);
        }
        return pVar.e(wrapContentElement);
    }

    public static p m(p pVar, h hVar) {
        WrapContentElement wrapContentElement;
        if (hVar.equals(r0.b.f5657h)) {
            wrapContentElement = f117e;
        } else {
            wrapContentElement = hVar.equals(r0.b.f5653d) ? f118f : new WrapContentElement(3, new j1(9, hVar), hVar);
        }
        return pVar.e(wrapContentElement);
    }
}
