package z1;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import c0.j1;
import java.text.Bidi;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f7132a = new p();

    public static final Rect a(TextPaint textPaint, CharSequence charSequence, int i5, int i6) {
        int i7 = i5;
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (spanned.nextSpanTransition(i7 - 1, i6, MetricAffectingSpan.class) != i6) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                TextPaint textPaint2 = new TextPaint();
                while (i7 < i6) {
                    int iNextSpanTransition = spanned.nextSpanTransition(i7, i6, MetricAffectingSpan.class);
                    MetricAffectingSpan[] metricAffectingSpanArr = (MetricAffectingSpan[]) spanned.getSpans(i7, iNextSpanTransition, MetricAffectingSpan.class);
                    textPaint2.set(textPaint);
                    for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
                        if (spanned.getSpanStart(metricAffectingSpan) != spanned.getSpanEnd(metricAffectingSpan)) {
                            metricAffectingSpan.updateMeasureState(textPaint2);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        o.a(textPaint2, charSequence, i7, iNextSpanTransition, rect2);
                    } else {
                        textPaint2.getTextBounds(charSequence.toString(), i7, iNextSpanTransition, rect2);
                    }
                    rect.right = rect2.width() + rect.right;
                    rect.top = Math.min(rect.top, rect2.top);
                    rect.bottom = Math.max(rect.bottom, rect2.bottom);
                    i7 = iNextSpanTransition;
                }
                return rect;
            }
        }
        Rect rect3 = new Rect();
        if (Build.VERSION.SDK_INT >= 29) {
            o.a(textPaint, charSequence, i7, i6, rect3);
            return rect3;
        }
        textPaint.getTextBounds(charSequence.toString(), i7, i6, rect3);
        return rect3;
    }

    public static final float b(int i5, int i6, float[] fArr) {
        return fArr[((i5 - i6) * 2) + 1];
    }

    public static final int c(Layout layout, int i5, boolean z2) {
        if (i5 <= 0) {
            return 0;
        }
        if (i5 >= layout.getText().length()) {
            return layout.getLineCount() - 1;
        }
        int lineForOffset = layout.getLineForOffset(i5);
        int lineStart = layout.getLineStart(lineForOffset);
        int lineEnd = layout.getLineEnd(lineForOffset);
        if (lineStart == i5 || lineEnd == i5) {
            if (lineStart == i5) {
                if (z2) {
                    return lineForOffset - 1;
                }
            } else if (!z2) {
                return lineForOffset + 1;
            }
        }
        return lineForOffset;
    }

    /* JADX WARN: Code duplicated, block: B:142:0x025f A[EDGE_INSN: B:142:0x025f->B:169:0x02bb BREAK  A[LOOP:5: B:152:0x027b->B:206:0x027b]] */
    public static final int d(y yVar, Layout layout, f3.a aVar, int i5, RectF rectF, a2.f fVar, j1 j1Var, boolean z2) {
        m[] mVarArr;
        int i6;
        m[] mVarArr2;
        int i7;
        int iH;
        int i8;
        int i9;
        int iE;
        Bidi bidiCreateLineBidi;
        float fA;
        float fA2;
        float fA3;
        int lineTop = layout.getLineTop(i5);
        int lineBottom = layout.getLineBottom(i5);
        int lineStart = layout.getLineStart(i5);
        int lineEnd = layout.getLineEnd(i5);
        if (lineStart == lineEnd) {
            return -1;
        }
        int i10 = (lineEnd - lineStart) * 2;
        float[] fArr = new float[i10];
        Layout layout2 = yVar.f7155e;
        int lineStart2 = layout2.getLineStart(i5);
        int iF = yVar.f(i5);
        if (i10 < (iF - lineStart2) * 2) {
            throw new IllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 2");
        }
        v1.g gVar = new v1.g(yVar);
        boolean z3 = false;
        boolean z4 = layout2.getParagraphDirection(i5) == 1;
        int i11 = 0;
        while (lineStart2 < iF) {
            boolean zIsRtlCharAt = layout2.isRtlCharAt(lineStart2);
            if (z4 && !zIsRtlCharAt) {
                fA = gVar.a(lineStart2, z3, z3, true);
                fA3 = gVar.a(lineStart2 + 1, true, true, true);
            } else if (z4 && zIsRtlCharAt) {
                fA3 = gVar.a(lineStart2, false, false, false);
                fA = gVar.a(lineStart2 + 1, true, true, false);
            } else {
                if (zIsRtlCharAt) {
                    fA2 = gVar.a(lineStart2, false, false, true);
                    fA = gVar.a(lineStart2 + 1, true, true, true);
                } else {
                    fA = gVar.a(lineStart2, false, false, false);
                    fA2 = gVar.a(lineStart2 + 1, true, true, false);
                }
                fA3 = fA2;
            }
            fArr[i11] = fA;
            fArr[i11 + 1] = fA3;
            i11 += 2;
            lineStart2++;
            z4 = z4;
            z3 = false;
        }
        Layout layout3 = (Layout) aVar.f2449a;
        int lineStart3 = layout3.getLineStart(i5);
        int lineEnd2 = layout3.getLineEnd(i5);
        int iH2 = aVar.h(lineStart3, false);
        int i12 = aVar.i(iH2);
        int i13 = lineStart3 - i12;
        int i14 = lineEnd2 - i12;
        Bidi bidiD = aVar.d(iH2);
        if (bidiD == null || (bidiCreateLineBidi = bidiD.createLineBidi(i13, i14)) == null) {
            mVarArr = new m[]{new m(lineStart3, lineEnd2, layout3.isRtlCharAt(lineStart3))};
        } else {
            int runCount = bidiCreateLineBidi.getRunCount();
            mVarArr = new m[runCount];
            int i15 = 0;
            while (i15 < runCount) {
                int i16 = runCount;
                mVarArr[i15] = new m(bidiCreateLineBidi.getRunStart(i15) + lineStart3, bidiCreateLineBidi.getRunLimit(i15) + lineStart3, bidiCreateLineBidi.getRunLevel(i15) % 2 == 1);
                i15++;
                runCount = i16;
            }
        }
        e4.b dVar = z2 ? new e4.d(0, mVarArr.length - 1, 1) : new e4.b(mVarArr.length - 1, 0, -1);
        int i17 = dVar.f2074d;
        int i18 = dVar.f2075e;
        int i19 = dVar.f2076f;
        if ((i19 <= 0 || i17 > i18) && (i19 >= 0 || i18 > i17)) {
            return -1;
        }
        while (true) {
            m mVar = mVarArr[i17];
            boolean z5 = mVar.f7124c;
            int iK = mVar.f7122a;
            int iL = mVar.f7123b;
            float f5 = z5 ? fArr[((iL - 1) - lineStart) * 2] : fArr[(iK - lineStart) * 2];
            float fB = z5 ? b(iK, lineStart, fArr) : b(iL - 1, lineStart, fArr);
            if (z2) {
                float f6 = rectF.left;
                if (fB >= f6) {
                    i6 = i19;
                    float f7 = rectF.right;
                    if (f5 <= f7) {
                        if ((z5 || f6 > f5) && (!z5 || f7 < fB)) {
                            int i20 = iL;
                            int i21 = iK;
                            while (true) {
                                i8 = i20;
                                if (i20 - i21 <= 1) {
                                    break;
                                }
                                int i22 = (i8 + i21) / 2;
                                float f8 = fArr[(i22 - lineStart) * 2];
                                if ((z5 || f8 <= rectF.left) && (!z5 || f8 >= rectF.right)) {
                                    i20 = i8;
                                    i21 = i22;
                                } else {
                                    i20 = i22;
                                }
                            }
                            i9 = z5 ? i8 : i21;
                        } else {
                            i9 = iK;
                        }
                        int iH3 = fVar.h(i9);
                        if (iH3 != -1 && (iE = fVar.e(iH3)) < iL) {
                            if (iE >= iK) {
                                iK = iE;
                            }
                            if (iH3 > iL) {
                                iH3 = iL;
                            }
                            mVarArr2 = mVarArr;
                            RectF rectF2 = new RectF(0.0f, lineTop, 0.0f, lineBottom);
                            int iH4 = iH3;
                            while (true) {
                                rectF2.left = z5 ? fArr[((iH4 - 1) - lineStart) * 2] : fArr[(iK - lineStart) * 2];
                                rectF2.right = z5 ? b(iK, lineStart, fArr) : b(iH4 - 1, lineStart, fArr);
                                if (((Boolean) j1Var.invoke(rectF2, rectF)).booleanValue()) {
                                    break;
                                }
                                iK = fVar.k(iK);
                                if (iK != -1 && iK < iL) {
                                    iH4 = fVar.h(iK);
                                    if (iH4 > iL) {
                                        iH4 = iL;
                                    }
                                }
                            }
                        }
                        iK = -1;
                        break;
                    }
                } else {
                    i6 = i19;
                }
                mVarArr2 = mVarArr;
                iK = -1;
                break;
            } else {
                i6 = i19;
                mVarArr2 = mVarArr;
                float f9 = rectF.left;
                if (fB < f9) {
                    iL = -1;
                    break;
                }
                float f10 = rectF.right;
                if (f5 <= f10) {
                    if ((z5 || f10 < fB) && (!z5 || f9 > f5)) {
                        int i23 = iL;
                        int i24 = iK;
                        while (i23 - i24 > 1) {
                            int i25 = (i23 + i24) / 2;
                            float f11 = fArr[(i25 - lineStart) * 2];
                            int i26 = i23;
                            if ((z5 || f11 <= rectF.right) && (!z5 || f11 >= rectF.left)) {
                                i23 = i26;
                                i24 = i25;
                            } else {
                                i23 = i25;
                            }
                        }
                        i7 = z5 ? i23 : i24;
                    } else {
                        i7 = iL - 1;
                    }
                    int iE2 = fVar.e(i7 + 1);
                    if (iE2 == -1 || (iH = fVar.h(iE2)) <= iK) {
                        iL = -1;
                        break;
                    }
                    if (iE2 < iK) {
                        iE2 = iK;
                    }
                    if (iH <= iL) {
                        iL = iH;
                    }
                    RectF rectF3 = new RectF(0.0f, lineTop, 0.0f, lineBottom);
                    int iE3 = iE2;
                    while (true) {
                        rectF3.left = z5 ? fArr[((iL - 1) - lineStart) * 2] : fArr[(iE3 - lineStart) * 2];
                        rectF3.right = z5 ? b(iE3, lineStart, fArr) : b(iL - 1, lineStart, fArr);
                        if (((Boolean) j1Var.invoke(rectF3, rectF)).booleanValue()) {
                            break;
                        }
                        iL = fVar.l(iL);
                        if (iL == -1 || iL <= iK) {
                            iL = -1;
                            break;
                        }
                        iE3 = fVar.e(iL);
                        if (iE3 < iK) {
                            iE3 = iK;
                        }
                    }
                } else {
                    iL = -1;
                    break;
                }
                iK = iL;
            }
            if (iK >= 0) {
                return iK;
            }
            if (i17 == i18) {
                return -1;
            }
            i17 += i6;
            i19 = i6;
            mVarArr = mVarArr2;
        }
    }
}
