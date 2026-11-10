package androidx.compose.foundation.layout;

import c0.j3;
import r.l0;
import r.m0;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final m0 a(float f5, float f6, float f7, float f8) {
        return new m0(f5, f6, f7, f8);
    }

    public static p b(p pVar) {
        return pVar.e(new AspectRatioElement());
    }

    public static final p c(p pVar, a4.c cVar) {
        return pVar.e(new OffsetPxElement(cVar));
    }

    public static final p d(p pVar, float f5, float f6) {
        return pVar.e(new OffsetElement(f5, f6));
    }

    public static final p e(p pVar, l0 l0Var) {
        return pVar.e(new PaddingValuesElement(l0Var));
    }

    public static final p f(p pVar, float f5) {
        return pVar.e(new PaddingElement(f5, f5, f5, f5));
    }

    public static final p g(p pVar, float f5, float f6) {
        return pVar.e(new PaddingElement(f5, f6, f5, f6));
    }

    public static p h(p pVar, float f5, float f6, int i5) {
        if ((i5 & 1) != 0) {
            f5 = 0;
        }
        if ((i5 & 2) != 0) {
            f6 = 0;
        }
        return g(pVar, f5, f6);
    }

    public static p i(p pVar, float f5, float f6, float f7, int i5) {
        float f8 = j3.f951e;
        if ((i5 & 1) != 0) {
            f5 = 0;
        }
        if ((i5 & 2) != 0) {
            f6 = 0;
        }
        if ((i5 & 4) != 0) {
            f7 = 0;
        }
        if ((i5 & 8) != 0) {
            f8 = 0;
        }
        return pVar.e(new PaddingElement(f5, f6, f7, f8));
    }

    public static final p j(p pVar) {
        return pVar.e(new IntrinsicWidthElement());
    }
}
