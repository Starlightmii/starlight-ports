package q1;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g1 f5209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g1 f5210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g1 f5211h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5212e;

    static {
        int i5 = 2;
        f5209f = new g1(i5, 0);
        f5210g = new g1(i5, 1);
        f5211h = new g1(i5, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g1(int i5, int i6) {
        super(i5);
        this.f5212e = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5212e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    }
                }
                break;
            case 1:
                ((l1) obj).K((Matrix) obj2);
                break;
            default:
                ((Matrix) obj2).set(((View) obj).getMatrix());
                break;
        }
        return p3.n.f5026a;
    }
}
