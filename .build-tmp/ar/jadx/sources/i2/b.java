package i2;

import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import f0.l;
import f0.n0;
import f0.s1;
import g2.k;
import x0.f;
import y0.o0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends CharacterStyle implements UpdateAppearance {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o0 f2667d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f2668e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f2669f = l.s(new f(9205357640488583168L));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final n0 f2670g = l.n(new b0.a(9, this));

    public b(o0 o0Var, float f5) {
        this.f2667d = o0Var;
        this.f2668e = f5;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        k.b(textPaint, this.f2668e);
        textPaint.setShader((Shader) this.f2670g.getValue());
    }
}
