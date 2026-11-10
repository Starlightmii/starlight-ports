package b2;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends MetricAffectingSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f574e;

    public /* synthetic */ a(float f5, int i5) {
        this.f573d = i5;
        this.f574e = f5;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        switch (this.f573d) {
            case 0:
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * this.f574e);
                break;
            default:
                textPaint.setTextSkewX(textPaint.getTextSkewX() + this.f574e);
                break;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        switch (this.f573d) {
            case 0:
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * this.f574e);
                break;
            default:
                textPaint.setTextSkewX(textPaint.getTextSkewX() + this.f574e);
                break;
        }
    }
}
