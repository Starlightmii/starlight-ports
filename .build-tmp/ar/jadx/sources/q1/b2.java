package q1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.os.Build;
import android.view.DisplayListCanvas;
import android.view.RenderNode;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b2 implements l1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f5126g = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RenderNode f5127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5128b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5132f;

    public b2(u uVar) {
        RenderNode renderNodeCreate = RenderNode.create("Compose", uVar);
        this.f5127a = renderNodeCreate;
        if (f5126g) {
            renderNodeCreate.setScaleX(renderNodeCreate.getScaleX());
            renderNodeCreate.setScaleY(renderNodeCreate.getScaleY());
            renderNodeCreate.setTranslationX(renderNodeCreate.getTranslationX());
            renderNodeCreate.setTranslationY(renderNodeCreate.getTranslationY());
            renderNodeCreate.setElevation(renderNodeCreate.getElevation());
            renderNodeCreate.setRotation(renderNodeCreate.getRotation());
            renderNodeCreate.setRotationX(renderNodeCreate.getRotationX());
            renderNodeCreate.setRotationY(renderNodeCreate.getRotationY());
            renderNodeCreate.setCameraDistance(renderNodeCreate.getCameraDistance());
            renderNodeCreate.setPivotX(renderNodeCreate.getPivotX());
            renderNodeCreate.setPivotY(renderNodeCreate.getPivotY());
            renderNodeCreate.setClipToOutline(renderNodeCreate.getClipToOutline());
            renderNodeCreate.setClipToBounds(false);
            renderNodeCreate.setAlpha(renderNodeCreate.getAlpha());
            renderNodeCreate.isValid();
            renderNodeCreate.setLeftTopRightBottom(0, 0, 0, 0);
            renderNodeCreate.offsetLeftAndRight(0);
            renderNodeCreate.offsetTopAndBottom(0);
            if (Build.VERSION.SDK_INT >= 28) {
                g2 g2Var = g2.f5213a;
                g2Var.c(renderNodeCreate, g2Var.a(renderNodeCreate));
                g2Var.d(renderNodeCreate, g2Var.b(renderNodeCreate));
            }
            f2.f5201a.a(renderNodeCreate);
            renderNodeCreate.setLayerType(0);
            renderNodeCreate.setHasOverlappingRendering(renderNodeCreate.hasOverlappingRendering());
            f5126g = false;
        }
    }

    @Override // q1.l1
    public final void A() {
        this.f5127a.setLayerType(0);
        this.f5127a.setHasOverlappingRendering(true);
    }

    @Override // q1.l1
    public final void B(int i5) {
        if (Build.VERSION.SDK_INT >= 28) {
            g2.f5213a.c(this.f5127a, i5);
        }
    }

    @Override // q1.l1
    public final void C(float f5) {
        this.f5127a.setPivotY(f5);
    }

    @Override // q1.l1
    public final void D(float f5) {
        this.f5127a.setElevation(f5);
    }

    @Override // q1.l1
    public final int E() {
        return this.f5130d;
    }

    @Override // q1.l1
    public final boolean F() {
        return this.f5127a.getClipToOutline();
    }

    @Override // q1.l1
    public final void G(int i5) {
        this.f5129c += i5;
        this.f5131e += i5;
        this.f5127a.offsetTopAndBottom(i5);
    }

    @Override // q1.l1
    public final void H(boolean z2) {
        this.f5127a.setClipToOutline(z2);
    }

    @Override // q1.l1
    public final void I(int i5) {
        if (Build.VERSION.SDK_INT >= 28) {
            g2.f5213a.d(this.f5127a, i5);
        }
    }

    @Override // q1.l1
    public final boolean J() {
        return this.f5127a.setHasOverlappingRendering(true);
    }

    @Override // q1.l1
    public final void K(Matrix matrix) {
        this.f5127a.getMatrix(matrix);
    }

    @Override // q1.l1
    public final float L() {
        return this.f5127a.getElevation();
    }

    @Override // q1.l1
    public final float a() {
        return this.f5127a.getAlpha();
    }

    @Override // q1.l1
    public final void b() {
        this.f5127a.setRotationX(0.0f);
    }

    @Override // q1.l1
    public final void c(float f5) {
        this.f5127a.setAlpha(f5);
    }

    @Override // q1.l1
    public final void d() {
        this.f5127a.setTranslationY(0.0f);
    }

    @Override // q1.l1
    public final int e() {
        return this.f5131e - this.f5129c;
    }

    @Override // q1.l1
    public final void f() {
        this.f5127a.setRotationY(0.0f);
    }

    @Override // q1.l1
    public final void g(float f5) {
        this.f5127a.setScaleX(f5);
    }

    @Override // q1.l1
    public final void h() {
        f2.f5201a.a(this.f5127a);
    }

    @Override // q1.l1
    public final void i() {
        this.f5127a.setTranslationX(0.0f);
    }

    @Override // q1.l1
    public final void j() {
        this.f5127a.setRotation(0.0f);
    }

    @Override // q1.l1
    public final void k(float f5) {
        this.f5127a.setScaleY(f5);
    }

    @Override // q1.l1
    public final int l() {
        return this.f5130d - this.f5128b;
    }

    @Override // q1.l1
    public final void m(float f5) {
        this.f5127a.setCameraDistance(-f5);
    }

    @Override // q1.l1
    public final boolean n() {
        return this.f5127a.isValid();
    }

    @Override // q1.l1
    public final void o(Outline outline) {
        this.f5127a.setOutline(outline);
    }

    @Override // q1.l1
    public final void p(int i5) {
        this.f5128b += i5;
        this.f5130d += i5;
        this.f5127a.offsetLeftAndRight(i5);
    }

    @Override // q1.l1
    public final int q() {
        return this.f5131e;
    }

    @Override // q1.l1
    public final boolean r() {
        return this.f5132f;
    }

    @Override // q1.l1
    public final void s() {
    }

    @Override // q1.l1
    public final void t(Canvas canvas) {
        ((DisplayListCanvas) canvas).drawRenderNode(this.f5127a);
    }

    @Override // q1.l1
    public final int u() {
        return this.f5129c;
    }

    @Override // q1.l1
    public final int v() {
        return this.f5128b;
    }

    @Override // q1.l1
    public final void w(y0.s sVar, y0.k0 k0Var, c0.g1 g1Var) {
        Canvas canvasStart = this.f5127a.start(l(), e());
        y0.d dVar = sVar.f6683a;
        Canvas canvas = dVar.f6617a;
        dVar.f6617a = canvasStart;
        if (k0Var != null) {
            dVar.p();
            dVar.a(k0Var);
        }
        g1Var.c(dVar);
        if (k0Var != null) {
            dVar.m();
        }
        sVar.f6683a.f6617a = canvas;
        this.f5127a.end(canvasStart);
    }

    @Override // q1.l1
    public final void x(float f5) {
        this.f5127a.setPivotX(f5);
    }

    @Override // q1.l1
    public final void y(boolean z2) {
        this.f5132f = z2;
        this.f5127a.setClipToBounds(z2);
    }

    @Override // q1.l1
    public final boolean z(int i5, int i6, int i7, int i8) {
        this.f5128b = i5;
        this.f5129c = i6;
        this.f5130d = i7;
        this.f5131e = i8;
        return this.f5127a.setLeftTopRightBottom(i5, i6, i7, i8);
    }
}
