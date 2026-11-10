package i2;

import a1.g;
import a1.i;
import a1.j;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends CharacterStyle implements UpdateAppearance {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f2666d;

    public a(g gVar) {
        this.f2666d = gVar;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        Paint.Join join;
        Paint.Cap cap;
        if (textPaint != null) {
            i iVar = i.f33a;
            g gVar = this.f2666d;
            if (l.a(gVar, iVar)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (gVar instanceof j) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((j) gVar).f34a);
                textPaint.setStrokeMiter(((j) gVar).f35b);
                int i5 = ((j) gVar).f37d;
                if (i5 == 0) {
                    join = Paint.Join.MITER;
                } else if (i5 == 1) {
                    join = Paint.Join.ROUND;
                } else {
                    join = i5 == 2 ? Paint.Join.BEVEL : Paint.Join.MITER;
                }
                textPaint.setStrokeJoin(join);
                int i6 = ((j) gVar).f36c;
                if (i6 == 0) {
                    cap = Paint.Cap.BUTT;
                } else if (i6 == 1) {
                    cap = Paint.Cap.ROUND;
                } else {
                    cap = i6 == 2 ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
                }
                textPaint.setStrokeCap(cap);
                ((j) gVar).getClass();
                textPaint.setPathEffect(null);
            }
        }
    }
}
