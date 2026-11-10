package w2;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Interpolator;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r.c0 f6449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z0 f6450b;

    public y(View view, r.c0 c0Var) {
        z0 z0VarB;
        this.f6449a = c0Var;
        int i5 = u.f6438a;
        z0 z0VarA = q.a(view);
        if (z0VarA != null) {
            int i6 = Build.VERSION.SDK_INT;
            z0VarB = (i6 >= 36 ? new k0(z0VarA) : i6 >= 35 ? new j0(z0VarA) : i6 >= 34 ? new i0(z0VarA) : i6 >= 31 ? new h0(z0VarA) : i6 >= 30 ? new g0(z0VarA) : i6 >= 29 ? new f0(z0VarA) : new e0(z0VarA)).b();
        } else {
            z0VarB = null;
        }
        this.f6450b = z0VarB;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        Interpolator interpolator;
        if (!view.isLaidOut()) {
            this.f6450b = z0.b(view, windowInsets);
            return z.h(view, windowInsets);
        }
        z0 z0VarB = z0.b(view, windowInsets);
        v0 v0Var = z0VarB.f6456a;
        if (this.f6450b == null) {
            int i5 = u.f6438a;
            this.f6450b = q.a(view);
        }
        if (this.f6450b == null) {
            this.f6450b = z0VarB;
            return z.h(view, windowInsets);
        }
        r.c0 c0VarI = z.i(view);
        if (c0VarI != null && Objects.equals(c0VarI.f5536d, z0VarB)) {
            return z.h(view, windowInsets);
        }
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        z0 z0Var = this.f6450b;
        int i6 = 1;
        while (i6 <= 512) {
            r2.c cVarH = v0Var.h(i6);
            r2.c cVarH2 = z0Var.f6456a.h(i6);
            int i7 = cVarH.f5697a;
            int i8 = cVarH.f5700d;
            int i9 = cVarH.f5699c;
            int i10 = cVarH.f5698b;
            int i11 = cVarH2.f5697a;
            int i12 = cVarH2.f5700d;
            int i13 = cVarH2.f5699c;
            int i14 = cVarH2.f5698b;
            boolean z2 = i7 > i11 || i10 > i14 || i9 > i13 || i8 > i12;
            if (z2 != (i7 < i11 || i10 < i14 || i9 < i13 || i8 < i12)) {
                if (z2) {
                    iArr[0] = iArr[0] | i6;
                } else {
                    iArr2[0] = iArr2[0] | i6;
                }
            }
            i6 <<= 1;
            iArr = iArr;
        }
        int i15 = iArr[0];
        int i16 = iArr2[0];
        int i17 = i15 | i16;
        if (i17 == 0) {
            this.f6450b = z0VarB;
            return z.h(view, windowInsets);
        }
        z0 z0Var2 = this.f6450b;
        if ((i15 & 8) != 0) {
            interpolator = z.f6451d;
        } else if ((i16 & 8) != 0) {
            interpolator = z.f6452e;
        } else if ((i15 & 519) != 0) {
            interpolator = z.f6453f;
        } else {
            interpolator = (i16 & 519) != 0 ? z.f6454g : null;
        }
        d0 d0Var = new d0(i17, interpolator, (i17 & 8) != 0 ? 160L : 250L);
        d0Var.f6385a.c(0.0f);
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(d0Var.f6385a.a());
        r2.c cVarH3 = v0Var.h(i17);
        r2.c cVarH4 = z0Var2.f6456a.h(i17);
        int iMin = Math.min(cVarH3.f5697a, cVarH4.f5697a);
        int i18 = cVarH3.f5698b;
        int i19 = cVarH4.f5698b;
        int iMin2 = Math.min(i18, i19);
        int i20 = cVarH3.f5699c;
        int i21 = cVarH4.f5699c;
        int iMin3 = Math.min(i20, i21);
        int i22 = cVarH3.f5700d;
        int i23 = cVarH4.f5700d;
        a2.h hVar = new a2.h(r2.c.b(iMin, iMin2, iMin3, Math.min(i22, i23)), 26, r2.c.b(Math.max(cVarH3.f5697a, cVarH4.f5697a), Math.max(i18, i19), Math.max(i20, i21), Math.max(i22, i23)));
        z.e(view, z0VarB, false);
        duration.addUpdateListener(new w(d0Var, z0VarB, z0Var2, i17, view));
        duration.addListener(new x(view, d0Var));
        k kVar = new k(view, new j4.c(view, d0Var, hVar, duration));
        view.getViewTreeObserver().addOnPreDrawListener(kVar);
        view.addOnAttachStateChangeListener(kVar);
        this.f6450b = z0VarB;
        return z.h(view, windowInsets);
    }
}
