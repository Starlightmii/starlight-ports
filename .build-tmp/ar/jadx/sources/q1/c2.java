package q1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c2 implements l1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RenderNode f5146a = b1.f.u();

    @Override // q1.l1
    public final void A() {
        RenderNode renderNode = this.f5146a;
        renderNode.setUseCompositingLayer(false, null);
        renderNode.setHasOverlappingRendering(true);
    }

    @Override // q1.l1
    public final void B(int i5) {
        this.f5146a.setAmbientShadowColor(i5);
    }

    @Override // q1.l1
    public final void C(float f5) {
        this.f5146a.setPivotY(f5);
    }

    @Override // q1.l1
    public final void D(float f5) {
        this.f5146a.setElevation(f5);
    }

    @Override // q1.l1
    public final int E() {
        return this.f5146a.getRight();
    }

    @Override // q1.l1
    public final boolean F() {
        return this.f5146a.getClipToOutline();
    }

    @Override // q1.l1
    public final void G(int i5) {
        this.f5146a.offsetTopAndBottom(i5);
    }

    @Override // q1.l1
    public final void H(boolean z2) {
        this.f5146a.setClipToOutline(z2);
    }

    @Override // q1.l1
    public final void I(int i5) {
        this.f5146a.setSpotShadowColor(i5);
    }

    @Override // q1.l1
    public final boolean J() {
        return this.f5146a.setHasOverlappingRendering(true);
    }

    @Override // q1.l1
    public final void K(Matrix matrix) {
        this.f5146a.getMatrix(matrix);
    }

    @Override // q1.l1
    public final float L() {
        return this.f5146a.getElevation();
    }

    @Override // q1.l1
    public final float a() {
        return this.f5146a.getAlpha();
    }

    @Override // q1.l1
    public final void b() {
        this.f5146a.setRotationX(0.0f);
    }

    @Override // q1.l1
    public final void c(float f5) {
        this.f5146a.setAlpha(f5);
    }

    @Override // q1.l1
    public final void d() {
        this.f5146a.setTranslationY(0.0f);
    }

    @Override // q1.l1
    public final int e() {
        return this.f5146a.getHeight();
    }

    @Override // q1.l1
    public final void f() {
        this.f5146a.setRotationY(0.0f);
    }

    @Override // q1.l1
    public final void g(float f5) {
        this.f5146a.setScaleX(f5);
    }

    @Override // q1.l1
    public final void h() {
        this.f5146a.discardDisplayList();
    }

    @Override // q1.l1
    public final void i() {
        this.f5146a.setTranslationX(0.0f);
    }

    @Override // q1.l1
    public final void j() {
        this.f5146a.setRotationZ(0.0f);
    }

    @Override // q1.l1
    public final void k(float f5) {
        this.f5146a.setScaleY(f5);
    }

    @Override // q1.l1
    public final int l() {
        return this.f5146a.getWidth();
    }

    @Override // q1.l1
    public final void m(float f5) {
        this.f5146a.setCameraDistance(f5);
    }

    @Override // q1.l1
    public final boolean n() {
        return this.f5146a.hasDisplayList();
    }

    @Override // q1.l1
    public final void o(Outline outline) {
        this.f5146a.setOutline(outline);
    }

    @Override // q1.l1
    public final void p(int i5) {
        this.f5146a.offsetLeftAndRight(i5);
    }

    @Override // q1.l1
    public final int q() {
        return this.f5146a.getBottom();
    }

    @Override // q1.l1
    public final boolean r() {
        return this.f5146a.getClipToBounds();
    }

    @Override // q1.l1
    public final void s() {
        if (Build.VERSION.SDK_INT >= 31) {
            d2.f5153a.a(this.f5146a, null);
        }
    }

    @Override // q1.l1
    public final void t(Canvas canvas) {
        canvas.drawRenderNode(this.f5146a);
    }

    @Override // q1.l1
    public final int u() {
        return this.f5146a.getTop();
    }

    @Override // q1.l1
    public final int v() {
        return this.f5146a.getLeft();
    }

    @Override // q1.l1
    public final void w(y0.s sVar, y0.k0 k0Var, c0.g1 g1Var) {
        RecordingCanvas recordingCanvasBeginRecording = this.f5146a.beginRecording();
        y0.d dVar = sVar.f6683a;
        Canvas canvas = dVar.f6617a;
        dVar.f6617a = recordingCanvasBeginRecording;
        if (k0Var != null) {
            dVar.p();
            dVar.a(k0Var);
        }
        g1Var.c(dVar);
        if (k0Var != null) {
            dVar.m();
        }
        sVar.f6683a.f6617a = canvas;
        this.f5146a.endRecording();
    }

    @Override // q1.l1
    public final void x(float f5) {
        this.f5146a.setPivotX(f5);
    }

    @Override // q1.l1
    public final void y(boolean z2) {
        this.f5146a.setClipToBounds(z2);
    }

    @Override // q1.l1
    public final boolean z(int i5, int i6, int i7, int i8) {
        return this.f5146a.setPosition(i5, i6, i7, i8);
    }
}
