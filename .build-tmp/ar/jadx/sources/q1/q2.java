package q1;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q2 extends View implements p1.d1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final b1.n f5310s = new b1.n(2);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static Method f5311t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static Field f5312u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f5313v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static boolean f5314w;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f5315d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p1 f5316e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a4.e f5317f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a4.a f5318g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x1 f5319h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5320i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Rect f5321j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5322k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5323l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y0.s f5324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k3.b f5325n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f5326o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5327p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f5328q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f5329r;

    public q2(u uVar, p1 p1Var, a4.e eVar, a4.a aVar) {
        super(uVar.getContext());
        this.f5315d = uVar;
        this.f5316e = p1Var;
        this.f5317f = eVar;
        this.f5318g = aVar;
        this.f5319h = new x1();
        this.f5324m = new y0.s();
        this.f5325n = new k3.b(g1.f5211h);
        this.f5326o = y0.t0.f6686b;
        this.f5327p = true;
        setWillNotDraw(false);
        p1Var.addView(this);
        this.f5328q = View.generateViewId();
    }

    private final y0.k0 getManualClipPath() {
        if (!getClipToOutline()) {
            return null;
        }
        x1 x1Var = this.f5319h;
        if (!x1Var.f5451g) {
            return null;
        }
        x1Var.d();
        return x1Var.f5449e;
    }

    private final void setInvalidated(boolean z2) {
        if (z2 != this.f5322k) {
            this.f5322k = z2;
            this.f5315d.s(this, z2);
        }
    }

    @Override // p1.d1
    public final long a(long j5, boolean z2) {
        k3.b bVar = this.f5325n;
        if (!z2) {
            return y0.f0.b(j5, bVar.b(this));
        }
        float[] fArrA = bVar.a(this);
        if (fArrA != null) {
            return y0.f0.b(j5, fArrA);
        }
        return 9187343241974906880L;
    }

    @Override // p1.d1
    public final void b(y0.r rVar, b1.b bVar) {
        boolean z2 = getElevation() > 0.0f;
        this.f5323l = z2;
        if (z2) {
            rVar.s();
        }
        this.f5316e.a(rVar, this, getDrawingTime());
        if (this.f5323l) {
            rVar.q();
        }
    }

    @Override // p1.d1
    public final void c(a4.e eVar, a4.a aVar) {
        this.f5316e.addView(this);
        this.f5320i = false;
        this.f5323l = false;
        int i5 = y0.t0.f6687c;
        this.f5326o = y0.t0.f6686b;
        this.f5317f = eVar;
        this.f5318g = aVar;
    }

    @Override // p1.d1
    public final void d(long j5) {
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        if (i5 == getWidth() && i6 == getHeight()) {
            return;
        }
        setPivotX(y0.t0.a(this.f5326o) * i5);
        setPivotY(y0.t0.b(this.f5326o) * i6);
        setOutlineProvider(this.f5319h.b() != null ? f5310s : null);
        layout(getLeft(), getTop(), getLeft() + i5, getTop() + i6);
        m();
        this.f5325n.c();
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
        boolean z2;
        y0.s sVar = this.f5324m;
        y0.d dVar = sVar.f6683a;
        Canvas canvas2 = dVar.f6617a;
        dVar.f6617a = canvas;
        if (getManualClipPath() == null && canvas.isHardwareAccelerated()) {
            z2 = false;
        } else {
            dVar.p();
            this.f5319h.a(dVar);
            z2 = true;
        }
        a4.e eVar = this.f5317f;
        if (eVar != null) {
            eVar.invoke(dVar, null);
        }
        if (z2) {
            dVar.m();
        }
        sVar.f6683a.f6617a = canvas2;
        setInvalidated(false);
    }

    @Override // p1.d1
    public final void e(float[] fArr) {
        y0.f0.g(fArr, this.f5325n.b(this));
    }

    @Override // p1.d1
    public final void f(float[] fArr) {
        float[] fArrA = this.f5325n.a(this);
        if (fArrA != null) {
            y0.f0.g(fArr, fArrA);
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    @Override // p1.d1
    public final void g() {
        setInvalidated(false);
        u uVar = this.f5315d;
        uVar.B = true;
        this.f5317f = null;
        this.f5318g = null;
        uVar.A(this);
        this.f5316e.removeViewInLayout(this);
    }

    public final float getCameraDistancePx() {
        return getCameraDistance() / getResources().getDisplayMetrics().densityDpi;
    }

    public final p1 getContainer() {
        return this.f5316e;
    }

    public long getLayerId() {
        return this.f5328q;
    }

    public final u getOwnerView() {
        return this.f5315d;
    }

    public long getOwnerViewId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return p2.a(this.f5315d);
        }
        return -1L;
    }

    @Override // p1.d1
    public final void h(long j5) {
        int i5 = (int) (j5 >> 32);
        int left = getLeft();
        k3.b bVar = this.f5325n;
        if (i5 != left) {
            offsetLeftAndRight(i5 - getLeft());
            bVar.c();
        }
        int i6 = (int) (j5 & 4294967295L);
        if (i6 != getTop()) {
            offsetTopAndBottom(i6 - getTop());
            bVar.c();
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.f5327p;
    }

    @Override // p1.d1
    public final void i() {
        if (!this.f5322k || f5314w) {
            return;
        }
        m0.y(this);
        setInvalidated(false);
    }

    @Override // android.view.View, p1.d1
    public final void invalidate() {
        if (this.f5322k) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.f5315d.invalidate();
    }

    @Override // p1.d1
    public final void j(x0.b bVar, boolean z2) {
        k3.b bVar2 = this.f5325n;
        if (!z2) {
            y0.f0.c(bVar2.b(this), bVar);
            return;
        }
        float[] fArrA = bVar2.a(this);
        if (fArrA != null) {
            y0.f0.c(fArrA, bVar);
            return;
        }
        bVar.f6528a = 0.0f;
        bVar.f6529b = 0.0f;
        bVar.f6530c = 0.0f;
        bVar.f6531d = 0.0f;
    }

    @Override // p1.d1
    public final boolean k(long j5) {
        y0.j0 j0Var;
        float fD = x0.c.d(j5);
        float fE = x0.c.e(j5);
        if (this.f5320i) {
            if (0.0f > fD || fD >= getWidth() || 0.0f > fE || fE >= getHeight()) {
                return false;
            }
        } else if (getClipToOutline()) {
            x1 x1Var = this.f5319h;
            if (x1Var.f5457m && (j0Var = x1Var.f5447c) != null) {
                return m0.r(j0Var, x0.c.d(j5), x0.c.e(j5));
            }
            return true;
        }
        return true;
    }

    @Override // p1.d1
    public final void l(y0.n0 n0Var) {
        a4.a aVar;
        int i5 = n0Var.f6650d | this.f5329r;
        if ((i5 & 4096) != 0) {
            long j5 = n0Var.f6658l;
            this.f5326o = j5;
            setPivotX(y0.t0.a(j5) * getWidth());
            setPivotY(y0.t0.b(this.f5326o) * getHeight());
        }
        if ((i5 & 1) != 0) {
            setScaleX(n0Var.f6651e);
        }
        if ((i5 & 2) != 0) {
            setScaleY(n0Var.f6652f);
        }
        if ((i5 & 4) != 0) {
            setAlpha(n0Var.f6653g);
        }
        if ((i5 & 8) != 0) {
            setTranslationX(0.0f);
        }
        if ((i5 & 16) != 0) {
            setTranslationY(0.0f);
        }
        if ((i5 & 32) != 0) {
            setElevation(n0Var.f6654h);
        }
        if ((i5 & 1024) != 0) {
            setRotation(0.0f);
        }
        if ((i5 & 256) != 0) {
            setRotationX(0.0f);
        }
        if ((i5 & 512) != 0) {
            setRotationY(0.0f);
        }
        if ((i5 & 2048) != 0) {
            setCameraDistancePx(n0Var.f6657k);
        }
        boolean z2 = getManualClipPath() != null;
        boolean z3 = n0Var.f6660n;
        r3.c cVar = y0.l0.f6642a;
        boolean z4 = z3 && n0Var.f6659m != cVar;
        if ((i5 & 24576) != 0) {
            this.f5320i = z3 && n0Var.f6659m == cVar;
            m();
            setClipToOutline(z4);
        }
        boolean zC = this.f5319h.c(n0Var.f6664r, n0Var.f6653g, z4, n0Var.f6654h, n0Var.f6661o);
        x1 x1Var = this.f5319h;
        if (x1Var.f5450f) {
            setOutlineProvider(x1Var.b() != null ? f5310s : null);
        }
        boolean z5 = getManualClipPath() != null;
        if (z2 != z5 || (z5 && zC)) {
            invalidate();
        }
        if (!this.f5323l && getElevation() > 0.0f && (aVar = this.f5318g) != null) {
            aVar.a();
        }
        if ((i5 & 7963) != 0) {
            this.f5325n.c();
        }
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 28) {
            int i7 = i5 & 64;
            s2 s2Var = s2.f5363a;
            if (i7 != 0) {
                s2Var.a(this, y0.l0.w(n0Var.f6655i));
            }
            if ((i5 & 128) != 0) {
                s2Var.b(this, y0.l0.w(n0Var.f6656j));
            }
        }
        if (i6 >= 31 && (131072 & i5) != 0) {
            t2.f5372a.a(this, null);
        }
        if ((i5 & 32768) != 0) {
            setLayerType(0, null);
            this.f5327p = true;
        }
        this.f5329r = n0Var.f6650d;
    }

    public final void m() {
        Rect rect;
        if (this.f5320i) {
            Rect rect2 = this.f5321j;
            if (rect2 == null) {
                this.f5321j = new Rect(0, 0, getWidth(), getHeight());
            } else {
                b4.l.b(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.f5321j;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
    }

    public final void setCameraDistancePx(float f5) {
        setCameraDistance(f5 * getResources().getDisplayMetrics().densityDpi);
    }
}
