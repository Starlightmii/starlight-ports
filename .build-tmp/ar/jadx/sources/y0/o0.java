package y0;

import android.graphics.Paint;
import android.graphics.Shader;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class o0 extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Shader f6666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f6667b = 9205357640488583168L;

    @Override // y0.p
    public final void a(float f5, long j5, h hVar) {
        Paint paint = hVar.f6629a;
        Shader shaderB = this.f6666a;
        if (shaderB == null || !x0.f.a(this.f6667b, j5)) {
            if (x0.f.e(j5)) {
                shaderB = null;
                this.f6666a = null;
                this.f6667b = 9205357640488583168L;
            } else {
                shaderB = b(j5);
                this.f6666a = shaderB;
                this.f6667b = j5;
            }
        }
        long jC = l0.c(paint.getColor());
        long j6 = u.f6689b;
        if (!u.c(jC, j6)) {
            hVar.e(j6);
        }
        if (!b4.l.a(hVar.f6631c, shaderB)) {
            hVar.h(shaderB);
        }
        if (paint.getAlpha() / 255.0f == f5) {
            return;
        }
        hVar.c(f5);
    }

    public abstract Shader b(long j5);
}
