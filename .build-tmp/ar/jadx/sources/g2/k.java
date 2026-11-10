package g2;

import android.text.TextPaint;
import java.util.ArrayList;
import y0.p;
import y0.p0;
import y0.r;
import y1.m;
import y1.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f2544a = new l(false);

    public static final void a(m mVar, r rVar, p pVar, float f5, p0 p0Var, j2.j jVar, a1.g gVar) {
        ArrayList arrayList = mVar.f6796h;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            o oVar = (o) arrayList.get(i5);
            oVar.f6799a.g(rVar, pVar, f5, p0Var, jVar, gVar);
            rVar.k(0.0f, oVar.f6799a.b());
        }
    }

    public static final void b(TextPaint textPaint, float f5) {
        if (Float.isNaN(f5)) {
            return;
        }
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        textPaint.setAlpha(Math.round(f5 * 255));
    }
}
