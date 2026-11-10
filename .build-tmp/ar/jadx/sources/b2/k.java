package b2;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends CharacterStyle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f601b;

    public k(boolean z2, boolean z3) {
        this.f600a = z2;
        this.f601b = z3;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.f600a);
        textPaint.setStrikeThruText(this.f601b);
    }
}
