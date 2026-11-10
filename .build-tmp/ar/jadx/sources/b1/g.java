package b1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import y0.l0;
import y0.r;
import y0.s;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a1.c f519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RenderNode f520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Matrix f522f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f523g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f524h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f525i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f526j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f527k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f528l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f529m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f530n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f531o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f532p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f533q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f534r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f535s;

    public g() {
        s sVar = new s();
        a1.c cVar = new a1.c();
        this.f518b = sVar;
        this.f519c = cVar;
        RenderNode renderNodeB = a2.c.b();
        this.f520d = renderNodeB;
        this.f521e = 0L;
        renderNodeB.setClipToBounds(false);
        l(renderNodeB, 0);
        this.f524h = 1.0f;
        this.f525i = 3;
        this.f526j = 1.0f;
        this.f527k = 1.0f;
        long j5 = u.f6689b;
        this.f529m = j5;
        this.f530n = j5;
        this.f531o = 8.0f;
        this.f535s = 0;
    }

    public static void l(RenderNode renderNode, int i5) {
        if (i5 == 1) {
            renderNode.setUseCompositingLayer(true, null);
            renderNode.setHasOverlappingRendering(true);
        } else if (i5 == 2) {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(false);
        } else {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(true);
        }
    }

    @Override // b1.d
    public final void A(int i5) {
        this.f535s = i5;
        if (i5 != 1 && this.f525i == 3) {
            l(this.f520d, i5);
        } else {
            l(this.f520d, 1);
        }
    }

    @Override // b1.d
    public final void B(long j5) {
        this.f530n = j5;
        this.f520d.setSpotShadowColor(l0.w(j5));
    }

    @Override // b1.d
    public final Matrix C() {
        Matrix matrix = this.f522f;
        if (matrix == null) {
            matrix = new Matrix();
            this.f522f = matrix;
        }
        this.f520d.getMatrix(matrix);
        return matrix;
    }

    @Override // b1.d
    public final void D(r rVar) {
        y0.e.a(rVar).drawRenderNode(this.f520d);
    }

    @Override // b1.d
    public final void E(int i5, int i6, long j5) {
        this.f520d.setPosition(i5, i6, ((int) (j5 >> 32)) + i5, ((int) (4294967295L & j5)) + i6);
        this.f521e = a.a.O(j5);
    }

    @Override // b1.d
    public final float F() {
        return 0.0f;
    }

    @Override // b1.d
    public final float G() {
        return this.f528l;
    }

    @Override // b1.d
    public final void H(k2.b bVar, k2.k kVar, b bVar2, a4.c cVar) {
        a1.c cVar2 = this.f519c;
        RecordingCanvas recordingCanvasBeginRecording = this.f520d.beginRecording();
        try {
            s sVar = this.f518b;
            y0.d dVar = sVar.f6683a;
            Canvas canvas = dVar.f6617a;
            dVar.f6617a = recordingCanvasBeginRecording;
            a1.b bVar3 = cVar2.f27e;
            bVar3.B(bVar);
            bVar3.C(kVar);
            bVar3.f24e = bVar2;
            bVar3.D(this.f521e);
            bVar3.A(dVar);
            cVar.c(cVar2);
            sVar.f6683a.f6617a = canvas;
        } finally {
            this.f520d.endRecording();
        }
    }

    @Override // b1.d
    public final float I() {
        return this.f527k;
    }

    @Override // b1.d
    public final float J() {
        return 0.0f;
    }

    @Override // b1.d
    public final int K() {
        return this.f525i;
    }

    @Override // b1.d
    public final void L(long j5) {
        if (p3.j.F(j5)) {
            this.f520d.resetPivot();
        } else {
            this.f520d.setPivotX(x0.c.d(j5));
            this.f520d.setPivotY(x0.c.e(j5));
        }
    }

    @Override // b1.d
    public final long M() {
        return this.f529m;
    }

    @Override // b1.d
    public final float a() {
        return this.f524h;
    }

    @Override // b1.d
    public final void b() {
        this.f520d.setRotationX(0.0f);
    }

    @Override // b1.d
    public final void c(float f5) {
        this.f524h = f5;
        this.f520d.setAlpha(f5);
    }

    @Override // b1.d
    public final void d() {
        this.f520d.setTranslationY(0.0f);
    }

    public final void e() {
        boolean z2 = this.f532p;
        boolean z3 = false;
        boolean z4 = z2 && !this.f523g;
        if (z2 && this.f523g) {
            z3 = true;
        }
        if (z4 != this.f533q) {
            this.f533q = z4;
            this.f520d.setClipToBounds(z4);
        }
        if (z3 != this.f534r) {
            this.f534r = z3;
            this.f520d.setClipToOutline(z3);
        }
    }

    @Override // b1.d
    public final void f() {
        this.f520d.setRotationY(0.0f);
    }

    @Override // b1.d
    public final void g(float f5) {
        this.f526j = f5;
        this.f520d.setScaleX(f5);
    }

    @Override // b1.d
    public final void h() {
        this.f520d.discardDisplayList();
    }

    @Override // b1.d
    public final void i() {
        this.f520d.setTranslationX(0.0f);
    }

    @Override // b1.d
    public final void j() {
        this.f520d.setRotationZ(0.0f);
    }

    @Override // b1.d
    public final void k(float f5) {
        this.f527k = f5;
        this.f520d.setScaleY(f5);
    }

    @Override // b1.d
    public final void m(float f5) {
        this.f531o = f5;
        this.f520d.setCameraDistance(f5);
    }

    @Override // b1.d
    public final boolean n() {
        return this.f520d.hasDisplayList();
    }

    @Override // b1.d
    public final void o(Outline outline) {
        this.f520d.setOutline(outline);
        this.f523g = outline != null;
        e();
    }

    @Override // b1.d
    public final boolean p() {
        return this.f532p;
    }

    @Override // b1.d
    public final float q() {
        return this.f526j;
    }

    @Override // b1.d
    public final void r(float f5) {
        this.f528l = f5;
        this.f520d.setElevation(f5);
    }

    @Override // b1.d
    public final float s() {
        return 0.0f;
    }

    @Override // b1.d
    public final long t() {
        return this.f530n;
    }

    @Override // b1.d
    public final void u(long j5) {
        this.f529m = j5;
        this.f520d.setAmbientShadowColor(l0.w(j5));
    }

    @Override // b1.d
    public final float v() {
        return this.f531o;
    }

    @Override // b1.d
    public final float w() {
        return 0.0f;
    }

    @Override // b1.d
    public final void x(boolean z2) {
        this.f532p = z2;
        e();
    }

    @Override // b1.d
    public final int y() {
        return this.f535s;
    }

    @Override // b1.d
    public final float z() {
        return 0.0f;
    }
}
