package z1;

import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f7125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextPaint f7126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f7128d = Float.NaN;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f7129e = Float.NaN;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BoringLayout.Metrics f7130f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f7131g;

    public n(CharSequence charSequence, TextPaint textPaint, int i5) {
        this.f7125a = charSequence;
        this.f7126b = textPaint;
        this.f7127c = i5;
    }

    public final BoringLayout.Metrics a() {
        if (!this.f7131g) {
            TextDirectionHeuristic textDirectionHeuristicA = z.a(this.f7127c);
            int i5 = Build.VERSION.SDK_INT;
            CharSequence charSequence = this.f7125a;
            TextPaint textPaint = this.f7126b;
            this.f7130f = i5 >= 33 ? c.b(charSequence, textPaint, textDirectionHeuristicA) : d.b(charSequence, textPaint, textDirectionHeuristicA);
            this.f7131g = true;
        }
        return this.f7130f;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x005c  */
    /* JADX WARN: Code duplicated, block: B:27:0x0065  */
    public final float b() {
        if (!Float.isNaN(this.f7128d)) {
            return this.f7128d;
        }
        BoringLayout.Metrics metricsA = a();
        float fCeil = metricsA != null ? metricsA.width : -1;
        TextPaint textPaint = this.f7126b;
        CharSequence charSequence = this.f7125a;
        if (fCeil < 0.0f) {
            fCeil = (float) Math.ceil(Layout.getDesiredWidth(charSequence, 0, charSequence.length(), textPaint));
        }
        if (fCeil != 0.0f) {
            if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                if (spanned.nextSpanTransition(-1, spanned.length(), b2.f.class) != spanned.length() || spanned.nextSpanTransition(-1, spanned.length(), b2.e.class) != spanned.length()) {
                    fCeil += 0.5f;
                } else if (textPaint.getLetterSpacing() != 0.0f) {
                    fCeil += 0.5f;
                }
            } else if (textPaint.getLetterSpacing() != 0.0f) {
                fCeil += 0.5f;
            }
        }
        this.f7128d = fCeil;
        return fCeil;
    }
}
