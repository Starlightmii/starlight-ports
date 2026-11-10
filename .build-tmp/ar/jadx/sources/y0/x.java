package y0;

import android.graphics.ColorSpace;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x implements z0.i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f6700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f6701e;

    public /* synthetic */ x(int i5, Object obj) {
        this.f6700d = i5;
        this.f6701e = obj;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Iterable, java.lang.Object] */
    public void a() {
        a4.e eVar = (a4.e) this.f6701e;
        synchronized (p0.m.f4719c) {
            ?? r4 = p0.m.f4724h;
            b4.l.e("<this>", r4);
            ArrayList arrayList = new ArrayList(q3.m.S(r4));
            boolean z2 = false;
            for (Object obj : r4) {
                boolean z3 = true;
                if (!z2 && b4.l.a(obj, eVar)) {
                    z2 = true;
                    z3 = false;
                }
                if (z3) {
                    arrayList.add(obj);
                }
            }
            p0.m.f4724h = arrayList;
        }
    }

    @Override // z0.i
    public double b(double d5) {
        switch (this.f6700d) {
            case 0:
                return ((ColorSpace.Rgb) ((ColorSpace) this.f6701e)).getOetf().applyAsDouble(d5);
            default:
                return ((ColorSpace.Rgb) ((ColorSpace) this.f6701e)).getEotf().applyAsDouble(d5);
        }
    }
}
