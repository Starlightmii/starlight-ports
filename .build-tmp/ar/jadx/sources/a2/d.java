package a2;

import android.text.TextPaint;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends a.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final CharSequence f40l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final TextPaint f41m;

    public d(CharSequence charSequence, TextPaint textPaint) {
        this.f40l = charSequence;
        this.f41m = textPaint;
    }

    @Override // a.a
    public final int F(int i5) {
        CharSequence charSequence = this.f40l;
        return this.f41m.getTextRunCursor(charSequence, 0, charSequence.length(), false, i5, 0);
    }

    @Override // a.a
    public final int H(int i5) {
        CharSequence charSequence = this.f40l;
        return this.f41m.getTextRunCursor(charSequence, 0, charSequence.length(), false, i5, 2);
    }
}
