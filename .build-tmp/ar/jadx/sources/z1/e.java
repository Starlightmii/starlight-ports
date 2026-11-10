package z1;

import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f7113a = new e();

    public final void a(Canvas canvas, CharSequence charSequence, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        canvas.drawTextRun(charSequence, i5, i6, i7, i8, f5, f6, z2, paint);
    }

    public final void b(Canvas canvas, char[] cArr, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        canvas.drawTextRun(cArr, i5, i6, i7, i8, f5, f6, z2, paint);
    }
}
