package e2;

import android.view.inputmethod.CursorAnchorInfo;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static final CursorAnchorInfo.Builder a(CursorAnchorInfo.Builder builder, y1.e0 e0Var, x0.d dVar) {
        if (!dVar.e()) {
            float f5 = dVar.f6535b;
            y1.m mVar = e0Var.f6754b;
            int iC = mVar.c(f5);
            int iC2 = mVar.c(dVar.f6537d);
            if (iC <= iC2) {
                while (true) {
                    builder.addVisibleLineBounds(e0Var.f(iC), mVar.d(iC), e0Var.g(iC), mVar.b(iC));
                    if (iC == iC2) {
                        break;
                    }
                    iC++;
                }
            }
        }
        return builder;
    }
}
