package b0;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AnimationUtils;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends View {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f439i = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f440j = new int[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f441d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Boolean f442e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Long f443f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public androidx.lifecycle.a0 f444g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a4.a f445h;

    private final void setRippleState(boolean z2) {
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        Runnable runnable = this.f444g;
        if (runnable != null) {
            removeCallbacks(runnable);
            runnable.run();
        }
        Long l5 = this.f443f;
        long jLongValue = jCurrentAnimationTimeMillis - (l5 != null ? l5.longValue() : 0L);
        if (z2 || jLongValue >= 5) {
            int[] iArr = z2 ? f439i : f440j;
            c0 c0Var = this.f441d;
            if (c0Var != null) {
                c0Var.setState(iArr);
            }
        } else {
            androidx.lifecycle.a0 a0Var = new androidx.lifecycle.a0(2, this);
            this.f444g = a0Var;
            postDelayed(a0Var, 50L);
        }
        this.f443f = Long.valueOf(jCurrentAnimationTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRippleState$lambda$2(q qVar) {
        c0 c0Var = qVar.f441d;
        if (c0Var != null) {
            c0Var.setState(f440j);
        }
        qVar.f444g = null;
    }

    public final void b(q.m mVar, boolean z2, long j5, int i5, long j6, float f5, a4.a aVar) {
        if (this.f441d == null || !Boolean.valueOf(z2).equals(this.f442e)) {
            c0 c0Var = new c0(z2);
            setBackground(c0Var);
            this.f441d = c0Var;
            this.f442e = Boolean.valueOf(z2);
        }
        c0 c0Var2 = this.f441d;
        b4.l.b(c0Var2);
        this.f445h = aVar;
        Integer num = c0Var2.f394f;
        if (num == null || num.intValue() != i5) {
            c0Var2.f394f = Integer.valueOf(i5);
            b0.f391a.a(c0Var2, i5);
        }
        e(f5, j5, j6);
        if (z2) {
            c0Var2.setHotspot(x0.c.d(mVar.f5084a), x0.c.e(mVar.f5084a));
        } else {
            c0Var2.setHotspot(c0Var2.getBounds().centerX(), c0Var2.getBounds().centerY());
        }
        setRippleState(true);
    }

    public final void c() {
        this.f445h = null;
        androidx.lifecycle.a0 a0Var = this.f444g;
        if (a0Var != null) {
            removeCallbacks(a0Var);
            androidx.lifecycle.a0 a0Var2 = this.f444g;
            b4.l.b(a0Var2);
            a0Var2.run();
        } else {
            c0 c0Var = this.f441d;
            if (c0Var != null) {
                c0Var.setState(f440j);
            }
        }
        c0 c0Var2 = this.f441d;
        if (c0Var2 == null) {
            return;
        }
        c0Var2.setVisible(false, false);
        unscheduleDrawable(c0Var2);
    }

    public final void d() {
        setRippleState(false);
    }

    public final void e(float f5, long j5, long j6) {
        c0 c0Var = this.f441d;
        if (c0Var == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            f5 *= 2;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        long jB = y0.u.b(f5, j6);
        y0.u uVar = c0Var.f393e;
        if (!(uVar == null ? false : y0.u.c(uVar.f6696a, jB))) {
            c0Var.f393e = new y0.u(jB);
            c0Var.setColor(ColorStateList.valueOf(l0.w(jB)));
        }
        Rect rect = new Rect(0, 0, d4.a.H(x0.f.d(j5)), d4.a.H(x0.f.b(j5)));
        setLeft(rect.left);
        setTop(rect.top);
        setRight(rect.right);
        setBottom(rect.bottom);
        c0Var.setBounds(rect);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        a4.a aVar = this.f445h;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
    }
}
