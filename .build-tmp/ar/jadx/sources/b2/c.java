package b2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;
import b4.l;
import z1.x;
import z1.z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements LeadingMarginSpan {
    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i5, int i6, int i7, int i8, int i9, CharSequence charSequence, int i10, int i11, boolean z2, Layout layout) {
        int lineForOffset;
        if (layout == null || paint == null || (lineForOffset = layout.getLineForOffset(i10)) != layout.getLineCount() - 1) {
            return;
        }
        x xVar = z.f7167a;
        if (layout.getEllipsisCount(lineForOffset) > 0) {
            float fW = a.a.w(layout, lineForOffset, paint) + a.a.v(layout, lineForOffset, paint);
            if (fW == 0.0f) {
                return;
            }
            l.b(canvas);
            canvas.translate(fW, 0.0f);
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z2) {
        return 0;
    }
}
