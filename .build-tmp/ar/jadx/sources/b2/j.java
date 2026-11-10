package b2;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends CharacterStyle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f599d;

    public j(float f5, float f6, float f7, int i5) {
        this.f596a = i5;
        this.f597b = f5;
        this.f598c = f6;
        this.f599d = f7;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.f599d, this.f597b, this.f598c, this.f596a);
    }
}
