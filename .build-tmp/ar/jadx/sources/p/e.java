package p;

import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import f0.h3;
import f0.w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f4433f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f4434g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4435e;

    static {
        int i5 = 1;
        f4433f = new e(i5, 0);
        f4434g = new e(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i5, int i6) {
        super(i5);
        this.f4435e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4435e) {
            case 0:
                w1 w1Var = (w1) obj;
                h3 h3Var = AndroidCompositionLocals_androidKt.f204b;
                w1Var.getClass();
                if (((Context) f0.l.t(w1Var, h3Var)).getPackageManager().hasSystemFeature("android.software.leanback")) {
                    return g.f4459b;
                }
                d.f4424a.getClass();
                return c.f4419c;
            default:
                return Boolean.valueOf(!(((j1.u) obj).f3282i == 2));
        }
    }
}
