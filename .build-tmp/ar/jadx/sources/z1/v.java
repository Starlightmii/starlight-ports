package z1;

import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f7133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextPaint f7135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7136d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextDirectionHeuristic f7137e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Layout.Alignment f7138f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7139g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TextUtils.TruncateAt f7140h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7141i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f7142j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f7143k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f7144l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f7145m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7146n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f7147o;

    public v(CharSequence charSequence, int i5, TextPaint textPaint, int i6, TextDirectionHeuristic textDirectionHeuristic, Layout.Alignment alignment, int i7, TextUtils.TruncateAt truncateAt, int i8, int i9, boolean z2, int i10, int i11, int i12, int i13) {
        this.f7133a = charSequence;
        this.f7134b = i5;
        this.f7135c = textPaint;
        this.f7136d = i6;
        this.f7137e = textDirectionHeuristic;
        this.f7138f = alignment;
        this.f7139g = i7;
        this.f7140h = truncateAt;
        this.f7141i = i8;
        this.f7142j = i9;
        this.f7143k = z2;
        this.f7144l = i10;
        this.f7145m = i11;
        this.f7146n = i12;
        this.f7147o = i13;
        if (i5 < 0) {
            throw new IllegalArgumentException("invalid start value");
        }
        int length = charSequence.length();
        if (i5 < 0 || i5 > length) {
            throw new IllegalArgumentException("invalid end value");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("invalid maxLines value");
        }
        if (i6 < 0) {
            throw new IllegalArgumentException("invalid width value");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("invalid ellipsizedWidth value");
        }
    }
}
