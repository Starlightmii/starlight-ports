package y0;

import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface k0 {
    static void a(k0 k0Var, x0.d dVar) {
        Path.Direction direction;
        j jVar = (j) k0Var;
        float f5 = dVar.f6534a;
        float f6 = dVar.f6537d;
        float f7 = dVar.f6536c;
        float f8 = dVar.f6535b;
        if (Float.isNaN(f5) || Float.isNaN(f8) || Float.isNaN(f7) || Float.isNaN(f6)) {
            throw new IllegalStateException("Invalid rectangle, make sure no value is NaN");
        }
        if (jVar.f6639b == null) {
            jVar.f6639b = new RectF();
        }
        RectF rectF = jVar.f6639b;
        b4.l.b(rectF);
        rectF.set(f5, f8, f7, f6);
        Path path = jVar.f6638a;
        RectF rectF2 = jVar.f6639b;
        b4.l.b(rectF2);
        int iC = m.h.c(1);
        if (iC == 0) {
            direction = Path.Direction.CCW;
        } else {
            if (iC != 1) {
                throw new i4.p();
            }
            direction = Path.Direction.CW;
        }
        path.addRect(rectF2, direction);
    }

    static void b(k0 k0Var, x0.e eVar) {
        Path.Direction direction;
        j jVar = (j) k0Var;
        if (jVar.f6639b == null) {
            jVar.f6639b = new RectF();
        }
        RectF rectF = jVar.f6639b;
        b4.l.b(rectF);
        float f5 = eVar.f6538a;
        long j5 = eVar.f6545h;
        long j6 = eVar.f6544g;
        long j7 = eVar.f6543f;
        long j8 = eVar.f6542e;
        rectF.set(f5, eVar.f6539b, eVar.f6540c, eVar.f6541d);
        if (jVar.f6640c == null) {
            jVar.f6640c = new float[8];
        }
        float[] fArr = jVar.f6640c;
        b4.l.b(fArr);
        fArr[0] = x0.a.b(j8);
        fArr[1] = x0.a.c(j8);
        fArr[2] = x0.a.b(j7);
        fArr[3] = x0.a.c(j7);
        fArr[4] = x0.a.b(j6);
        fArr[5] = x0.a.c(j6);
        fArr[6] = x0.a.b(j5);
        fArr[7] = x0.a.c(j5);
        Path path = jVar.f6638a;
        RectF rectF2 = jVar.f6639b;
        b4.l.b(rectF2);
        float[] fArr2 = jVar.f6640c;
        b4.l.b(fArr2);
        int iC = m.h.c(1);
        if (iC == 0) {
            direction = Path.Direction.CCW;
        } else {
            if (iC != 1) {
                throw new i4.p();
            }
            direction = Path.Direction.CW;
        }
        path.addRoundRect(rectF2, fArr2, direction);
    }
}
