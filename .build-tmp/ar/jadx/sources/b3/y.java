package b3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ReplacementSpan;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends ReplacementSpan {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x f665e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextPaint f668h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint.FontMetricsInt f664d = new Paint.FontMetricsInt();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public short f666f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f667g = 1.0f;

    public y(x xVar) {
        p3.p.h("rasterizer cannot be null", xVar);
        this.f665e = xVar;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i5, int i6, float f5, int i7, int i8, int i9, Paint paint) {
        TextPaint textPaint = null;
        if (charSequence instanceof Spanned) {
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) ((Spanned) charSequence).getSpans(i5, i6, CharacterStyle.class);
            if (characterStyleArr.length != 0) {
                if (characterStyleArr.length != 1 || characterStyleArr[0] != this) {
                    TextPaint textPaint2 = this.f668h;
                    if (textPaint2 == null) {
                        textPaint2 = new TextPaint();
                        this.f668h = textPaint2;
                    }
                    textPaint = textPaint2;
                    textPaint.set(paint);
                    for (CharacterStyle characterStyle : characterStyleArr) {
                        characterStyle.updateDrawState(textPaint);
                    }
                } else if (paint instanceof TextPaint) {
                    textPaint = (TextPaint) paint;
                }
            } else if (paint instanceof TextPaint) {
                textPaint = (TextPaint) paint;
            }
        } else if (paint instanceof TextPaint) {
            textPaint = (TextPaint) paint;
        }
        TextPaint textPaint3 = textPaint;
        if (textPaint3 != null && textPaint3.bgColor != 0) {
            int color = textPaint3.getColor();
            Paint.Style style = textPaint3.getStyle();
            textPaint3.setColor(textPaint3.bgColor);
            textPaint3.setStyle(Paint.Style.FILL);
            canvas.drawRect(f5, i7, f5 + this.f666f, i9, textPaint3);
            textPaint3.setStyle(style);
            textPaint3.setColor(color);
        }
        j.a().getClass();
        float f6 = i8;
        Paint paint2 = textPaint3;
        if (textPaint3 == null) {
            paint2 = paint;
        }
        x xVar = this.f665e;
        w wVar = xVar.f662b;
        Typeface typeface = (Typeface) wVar.f659g;
        Typeface typeface2 = paint2.getTypeface();
        paint2.setTypeface(typeface);
        canvas.drawText((char[]) wVar.f657e, xVar.f661a * 2, 2, f5, f6, paint2);
        paint2.setTypeface(typeface2);
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i5, int i6, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.f664d;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        x xVar = this.f665e;
        c3.a aVarB = xVar.b();
        int iA = aVarB.a(14);
        this.f667g = fAbs / (iA != 0 ? ((ByteBuffer) aVarB.f1504g).getShort(iA + aVarB.f1501d) : (short) 0);
        c3.a aVarB2 = xVar.b();
        int iA2 = aVarB2.a(14);
        if (iA2 != 0) {
            ((ByteBuffer) aVarB2.f1504g).getShort(iA2 + aVarB2.f1501d);
        }
        c3.a aVarB3 = xVar.b();
        int iA3 = aVarB3.a(12);
        short s4 = (short) ((iA3 != 0 ? ((ByteBuffer) aVarB3.f1504g).getShort(iA3 + aVarB3.f1501d) : (short) 0) * this.f667g);
        this.f666f = s4;
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s4;
    }
}
