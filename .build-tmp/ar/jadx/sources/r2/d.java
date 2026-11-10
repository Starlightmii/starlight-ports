package r2;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.text.PositionedGlyphs;
import android.graphics.text.TextRunShaper;
import android.os.Build;
import android.os.Trace;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f5701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Paint f5702b;

    static {
        d4.a.g("TypefaceCompat static init");
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 31) {
            f5701a = new h();
        } else if (i5 >= 29) {
            f5701a = new g();
        } else if (i5 >= 28) {
            f5701a = new f();
        } else {
            f5701a = new e();
        }
        new a1.d(10);
        f5702b = null;
        Trace.endSection();
    }

    public static Font a(Typeface typeface) {
        if (f5702b == null) {
            f5702b = new Paint();
        }
        f5702b.setTextSize(10.0f);
        f5702b.setTypeface(typeface);
        PositionedGlyphs positionedGlyphsShapeTextRun = TextRunShaper.shapeTextRun((CharSequence) " ", 0, 1, 0, 1, 0.0f, 0.0f, false, f5702b);
        if (positionedGlyphsShapeTextRun.glyphCount() == 0) {
            return null;
        }
        return positionedGlyphsShapeTextRun.getFont(0);
    }
}
