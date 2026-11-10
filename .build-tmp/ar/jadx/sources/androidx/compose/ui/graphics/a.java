package androidx.compose.ui.graphics;

import a4.c;
import r0.p;
import y0.b0;
import y0.l0;
import y0.q0;
import y0.t0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final p a(p pVar, c cVar) {
        return pVar.e(new BlockGraphicsLayerElement(cVar));
    }

    public static p b(p pVar, float f5, float f6, float f7, float f8, q0 q0Var, boolean z2, int i5) {
        float f9 = (i5 & 1) != 0 ? 1.0f : f5;
        float f10 = (i5 & 2) != 0 ? 1.0f : f6;
        float f11 = (i5 & 4) != 0 ? 1.0f : f7;
        float f12 = (i5 & 32) != 0 ? 0.0f : f8;
        long j5 = t0.f6686b;
        q0 q0Var2 = (i5 & 2048) != 0 ? l0.f6642a : q0Var;
        boolean z3 = (i5 & 4096) != 0 ? false : z2;
        long j6 = b0.f6615a;
        return pVar.e(new GraphicsLayerElement(f9, f10, f11, f12, j5, q0Var2, z3, j6, j6));
    }
}
