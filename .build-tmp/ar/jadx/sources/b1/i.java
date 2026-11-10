package b1;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import y0.l0;
import y0.r;
import y0.s;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i implements d {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final h f536v = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c1.a f537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s f538c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o f539d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Resources f540e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f541f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f542g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f544i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f545j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f546k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f547l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f548m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f549n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f550o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f551p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f552q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f553r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f554s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f555t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f556u;

    public i(c1.a aVar) {
        s sVar = new s();
        a1.c cVar = new a1.c();
        this.f537b = aVar;
        this.f538c = sVar;
        o oVar = new o(aVar, sVar, cVar);
        this.f539d = oVar;
        this.f540e = aVar.getResources();
        this.f541f = new Rect();
        aVar.addView(oVar);
        oVar.setClipBounds(null);
        this.f544i = 0L;
        View.generateViewId();
        this.f548m = 3;
        this.f549n = 0;
        this.f550o = 1.0f;
        this.f552q = 1.0f;
        this.f553r = 1.0f;
        long j5 = u.f6689b;
        this.f555t = j5;
        this.f556u = j5;
    }

    @Override // b1.d
    public final void A(int i5) {
        this.f549n = i5;
        o oVar = this.f539d;
        boolean z2 = true;
        if (i5 == 1 || this.f548m != 3) {
            oVar.setLayerType(2, null);
            oVar.setCanUseCompositingLayer$ui_graphics_release(true);
            return;
        }
        if (i5 == 1) {
            oVar.setLayerType(2, null);
        } else if (i5 == 2) {
            oVar.setLayerType(0, null);
            z2 = false;
        } else {
            oVar.setLayerType(0, null);
        }
        oVar.setCanUseCompositingLayer$ui_graphics_release(z2);
    }

    @Override // b1.d
    public final void B(long j5) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f556u = j5;
            p.f572a.c(this.f539d, l0.w(j5));
        }
    }

    @Override // b1.d
    public final Matrix C() {
        return this.f539d.getMatrix();
    }

    @Override // b1.d
    public final void D(r rVar) {
        Rect rect;
        boolean z2 = this.f545j;
        o oVar = this.f539d;
        if (z2) {
            if (!p() || this.f546k) {
                rect = null;
            } else {
                rect = this.f541f;
                rect.left = 0;
                rect.top = 0;
                rect.right = oVar.getWidth();
                rect.bottom = oVar.getHeight();
            }
            oVar.setClipBounds(rect);
        }
        if (y0.e.a(rVar).isHardwareAccelerated()) {
            this.f537b.a(rVar, oVar, oVar.getDrawingTime());
        }
    }

    @Override // b1.d
    public final void E(int i5, int i6, long j5) {
        boolean zA = k2.j.a(this.f544i, j5);
        o oVar = this.f539d;
        if (zA) {
            int i7 = this.f542g;
            if (i7 != i5) {
                oVar.offsetLeftAndRight(i5 - i7);
            }
            int i8 = this.f543h;
            if (i8 != i6) {
                oVar.offsetTopAndBottom(i6 - i8);
            }
        } else {
            if (p()) {
                this.f545j = true;
            }
            int i9 = (int) (j5 >> 32);
            int i10 = (int) (4294967295L & j5);
            oVar.layout(i5, i6, i5 + i9, i6 + i10);
            this.f544i = j5;
            if (this.f551p) {
                oVar.setPivotX(i9 / 2.0f);
                oVar.setPivotY(i10 / 2.0f);
            }
        }
        this.f542g = i5;
        this.f543h = i6;
    }

    @Override // b1.d
    public final float F() {
        return 0.0f;
    }

    @Override // b1.d
    public final float G() {
        return this.f554s;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // b1.d
    public final void H(k2.b bVar, k2.k kVar, b bVar2, a4.c cVar) {
        o oVar = this.f539d;
        ViewParent parent = oVar.getParent();
        c1.a aVar = this.f537b;
        if (parent == null) {
            aVar.addView(oVar);
        }
        oVar.f568j = bVar;
        oVar.f569k = kVar;
        oVar.f570l = cVar;
        oVar.f571m = bVar2;
        if (oVar.isAttachedToWindow()) {
            oVar.setVisibility(4);
            oVar.setVisibility(0);
            try {
                s sVar = this.f538c;
                h hVar = f536v;
                y0.d dVar = sVar.f6683a;
                Canvas canvas = dVar.f6617a;
                dVar.f6617a = hVar;
                aVar.a(dVar, oVar, oVar.getDrawingTime());
                sVar.f6683a.f6617a = canvas;
            } catch (Throwable unused) {
            }
        }
    }

    @Override // b1.d
    public final float I() {
        return this.f553r;
    }

    @Override // b1.d
    public final float J() {
        return 0.0f;
    }

    @Override // b1.d
    public final int K() {
        return this.f548m;
    }

    @Override // b1.d
    public final void L(long j5) {
        boolean zF = p3.j.F(j5);
        o oVar = this.f539d;
        if (!zF) {
            this.f551p = false;
            oVar.setPivotX(x0.c.d(j5));
            oVar.setPivotY(x0.c.e(j5));
        } else {
            if (Build.VERSION.SDK_INT >= 28) {
                p.f572a.a(oVar);
                return;
            }
            this.f551p = true;
            oVar.setPivotX(((int) (this.f544i >> 32)) / 2.0f);
            oVar.setPivotY(((int) (this.f544i & 4294967295L)) / 2.0f);
        }
    }

    @Override // b1.d
    public final long M() {
        return this.f555t;
    }

    @Override // b1.d
    public final float a() {
        return this.f550o;
    }

    @Override // b1.d
    public final void b() {
        this.f539d.setRotationX(0.0f);
    }

    @Override // b1.d
    public final void c(float f5) {
        this.f550o = f5;
        this.f539d.setAlpha(f5);
    }

    @Override // b1.d
    public final void d() {
        this.f539d.setTranslationY(0.0f);
    }

    @Override // b1.d
    public final void f() {
        this.f539d.setRotationY(0.0f);
    }

    @Override // b1.d
    public final void g(float f5) {
        this.f552q = f5;
        this.f539d.setScaleX(f5);
    }

    @Override // b1.d
    public final void h() {
        this.f537b.removeViewInLayout(this.f539d);
    }

    @Override // b1.d
    public final void i() {
        this.f539d.setTranslationX(0.0f);
    }

    @Override // b1.d
    public final void j() {
        this.f539d.setRotation(0.0f);
    }

    @Override // b1.d
    public final void k(float f5) {
        this.f553r = f5;
        this.f539d.setScaleY(f5);
    }

    @Override // b1.d
    public final void m(float f5) {
        this.f539d.setCameraDistance(f5 * this.f540e.getDisplayMetrics().densityDpi);
    }

    @Override // b1.d
    public final void o(Outline outline) {
        o oVar = this.f539d;
        oVar.f566h = outline;
        oVar.invalidateOutline();
        if (p() && outline != null) {
            oVar.setClipToOutline(true);
            if (this.f547l) {
                this.f547l = false;
                this.f545j = true;
            }
        }
        this.f546k = outline != null;
    }

    @Override // b1.d
    public final boolean p() {
        return this.f547l || this.f539d.getClipToOutline();
    }

    @Override // b1.d
    public final float q() {
        return this.f552q;
    }

    @Override // b1.d
    public final void r(float f5) {
        this.f554s = f5;
        this.f539d.setElevation(f5);
    }

    @Override // b1.d
    public final float s() {
        return 0.0f;
    }

    @Override // b1.d
    public final long t() {
        return this.f556u;
    }

    @Override // b1.d
    public final void u(long j5) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f555t = j5;
            p.f572a.b(this.f539d, l0.w(j5));
        }
    }

    @Override // b1.d
    public final float v() {
        return this.f539d.getCameraDistance() / this.f540e.getDisplayMetrics().densityDpi;
    }

    @Override // b1.d
    public final float w() {
        return 0.0f;
    }

    @Override // b1.d
    public final void x(boolean z2) {
        boolean z3 = false;
        this.f547l = z2 && !this.f546k;
        this.f545j = true;
        if (z2 && this.f546k) {
            z3 = true;
        }
        this.f539d.setClipToOutline(z3);
    }

    @Override // b1.d
    public final int y() {
        return this.f549n;
    }

    @Override // b1.d
    public final float z() {
        return 0.0f;
    }
}
