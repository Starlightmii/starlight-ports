package b2;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends MetricAffectingSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f575d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f576e;

    public /* synthetic */ b(int i5, Object obj) {
        this.f575d = i5;
        this.f576e = obj;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        switch (this.f575d) {
            case 0:
                textPaint.setFontFeatureSettings((String) this.f576e);
                break;
            default:
                textPaint.setTypeface((Typeface) this.f576e);
                break;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        switch (this.f575d) {
            case 0:
                textPaint.setFontFeatureSettings((String) this.f576e);
                break;
            default:
                textPaint.setTypeface((Typeface) this.f576e);
                break;
        }
    }
}
