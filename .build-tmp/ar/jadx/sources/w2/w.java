package w2;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.PathInterpolator;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f6442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ z0 f6443b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ z0 f6444c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f6445d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f6446e;

    public w(d0 d0Var, z0 z0Var, z0 z0Var2, int i5, View view) {
        this.f6442a = d0Var;
        this.f6443b = z0Var;
        this.f6444c = z0Var2;
        this.f6445d = i5;
        this.f6446e = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        l0 f0Var;
        float animatedFraction = valueAnimator.getAnimatedFraction();
        d0 d0Var = this.f6442a;
        c0 c0Var = d0Var.f6385a;
        c0Var.c(animatedFraction);
        z0 z0Var = this.f6443b;
        v0 v0Var = z0Var.f6456a;
        float fB = c0Var.b();
        PathInterpolator pathInterpolator = z.f6451d;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 36) {
            f0Var = new k0(z0Var);
        } else if (i5 >= 35) {
            f0Var = new j0(z0Var);
        } else if (i5 >= 34) {
            f0Var = new i0(z0Var);
        } else if (i5 >= 31) {
            f0Var = new h0(z0Var);
        } else if (i5 >= 30) {
            f0Var = new g0(z0Var);
        } else {
            f0Var = i5 >= 29 ? new f0(z0Var) : new e0(z0Var);
        }
        int i6 = 1;
        while (i6 <= 512) {
            if ((this.f6445d & i6) == 0) {
                f0Var.d(i6, v0Var.h(i6));
            } else {
                r2.c cVarH = v0Var.h(i6);
                r2.c cVarH2 = this.f6444c.f6456a.h(i6);
                int i7 = cVarH.f5697a;
                int i8 = cVarH.f5700d;
                int i9 = cVarH.f5699c;
                int i10 = cVarH.f5698b;
                float f5 = 1.0f - fB;
                int i11 = (int) (((double) ((i7 - cVarH2.f5697a) * f5)) + 0.5d);
                int i12 = (int) (((double) ((i10 - cVarH2.f5698b) * f5)) + 0.5d);
                int i13 = (int) (((double) ((i9 - cVarH2.f5699c) * f5)) + 0.5d);
                int i14 = (int) (((double) ((i8 - cVarH2.f5700d) * f5)) + 0.5d);
                int iMax = Math.max(0, i7 - i11);
                int iMax2 = Math.max(0, i10 - i12);
                int iMax3 = Math.max(0, i9 - i13);
                int iMax4 = Math.max(0, i8 - i14);
                if (iMax != i11 || iMax2 != i12 || iMax3 != i13 || iMax4 != i14) {
                    cVarH = r2.c.b(iMax, iMax2, iMax3, iMax4);
                }
                f0Var.d(i6, cVarH);
            }
            i6 <<= 1;
            d0Var = d0Var;
            v0Var = v0Var;
            fB = fB;
        }
        z0 z0VarB = f0Var.b();
        Collections.singletonList(d0Var);
        z.f(this.f6446e, z0VarB);
    }
}
