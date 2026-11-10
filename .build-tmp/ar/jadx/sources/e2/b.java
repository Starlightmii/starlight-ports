package e2;

import android.view.inputmethod.CursorAnchorInfo;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final CursorAnchorInfo.Builder a(CursorAnchorInfo.Builder builder, x0.d dVar) {
        return builder.setEditorBoundsInfo(x.f.h().setEditorBounds(l0.v(dVar)).setHandwritingBounds(l0.v(dVar)).build());
    }
}
