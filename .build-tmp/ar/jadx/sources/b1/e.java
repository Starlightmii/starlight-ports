package b1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.os.Build;
import android.view.DisplayListCanvas;
import android.view.RenderNode;
import java.util.concurrent.atomic.AtomicBoolean;
import q1.u;
import y0.l0;
import y0.r;
import y0.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements d {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final AtomicBoolean f498u = new AtomicBoolean(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a1.c f500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RenderNode f501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Matrix f503f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f504g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f505h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f506i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f507j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f508k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f509l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f510m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f511n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f512o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f513p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f514q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f515r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f516s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f517t;

    public e(u uVar, s sVar, a1.c cVar) {
        this.f499b = sVar;
        this.f500c = cVar;
        RenderNode renderNodeCreate = RenderNode.create("Compose", uVar);
        this.f501d = renderNodeCreate;
        this.f502e = 0L;
        if (f498u.getAndSet(false)) {
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
                m mVar = m.f559a;
                mVar.c(renderNodeCreate, mVar.a(renderNodeCreate));
                mVar.d(renderNodeCreate, mVar.b(renderNodeCreate));
            }
            l.f558a.a(renderNodeCreate);
            renderNodeCreate.setLayerType(0);
            renderNodeCreate.setHasOverlappingRendering(renderNodeCreate.hasOverlappingRendering());
        }
        renderNodeCreate.setClipToBounds(false);
        l(0);
        this.f505h = 0;
        this.f506i = 3;
        this.f507j = 1.0f;
        this.f509l = 1.0f;
        this.f510m = 1.0f;
        long j5 = y0.u.f6689b;
        this.f512o = j5;
        this.f513p = j5;
        this.f514q = 8.0f;
    }

    @Override // b1.d
    public final void A(int i5) {
        this.f505h = i5;
        if (i5 != 1 && this.f506i == 3) {
            l(i5);
        } else {
            l(1);
        }
    }

    @Override // b1.d
    public final void B(long j5) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f513p = j5;
            m.f559a.d(this.f501d, l0.w(j5));
        }
    }

    @Override // b1.d
    public final Matrix C() {
        Matrix matrix = this.f503f;
        if (matrix == null) {
            matrix = new Matrix();
            this.f503f = matrix;
        }
        this.f501d.getMatrix(matrix);
        return matrix;
    }

    @Override // b1.d
    public final void D(r rVar) {
        DisplayListCanvas displayListCanvasA = y0.e.a(rVar);
        b4.l.c("null cannot be cast to non-null type android.view.DisplayListCanvas", displayListCanvasA);
        displayListCanvasA.drawRenderNode(this.f501d);
    }

    @Override // b1.d
    public final void E(int i5, int i6, long j5) {
        int i7 = (int) (j5 >> 32);
        int i8 = (int) (4294967295L & j5);
        this.f501d.setLeftTopRightBottom(i5, i6, i5 + i7, i6 + i8);
        if (k2.j.a(this.f502e, j5)) {
            return;
        }
        if (this.f508k) {
            this.f501d.setPivotX(i7 / 2.0f);
            this.f501d.setPivotY(i8 / 2.0f);
        }
        this.f502e = j5;
    }

    @Override // b1.d
    public final float F() {
        return 0.0f;
    }

    @Override // b1.d
    public final float G() {
        return this.f511n;
    }

    @Override // b1.d
    public final void H(k2.b bVar, k2.k kVar, b bVar2, a4.c cVar) {
        RenderNode renderNode = this.f501d;
        long j5 = this.f502e;
        Canvas canvasStart = renderNode.start((int) (j5 >> 32), (int) (j5 & 4294967295L));
        try {
            y0.d dVar = this.f499b.f6683a;
            Canvas canvas = dVar.f6617a;
            dVar.f6617a = canvasStart;
            a1.c cVar2 = this.f500c;
            a1.b bVar3 = cVar2.f27e;
            long jO = a.a.O(this.f502e);
            a1.a aVar = ((a1.c) bVar3.f25f).f26d;
            k2.b bVar4 = aVar.f18a;
            k2.k kVar2 = aVar.f19b;
            r rVarR = bVar3.r();
            long jT = bVar3.t();
            b bVar5 = (b) bVar3.f24e;
            bVar3.B(bVar);
            bVar3.C(kVar);
            bVar3.A(dVar);
            bVar3.D(jO);
            bVar3.f24e = bVar2;
            dVar.p();
            try {
                cVar.c(cVar2);
                dVar.m();
                bVar3.B(bVar4);
                bVar3.C(kVar2);
                bVar3.A(rVarR);
                bVar3.D(jT);
                bVar3.f24e = bVar5;
                dVar.f6617a = canvas;
                this.f501d.end(canvasStart);
            } catch (Throwable th) {
                dVar.m();
                a1.b bVar6 = cVar2.f27e;
                bVar6.B(bVar4);
                bVar6.C(kVar2);
                bVar6.A(rVarR);
                bVar6.D(jT);
                bVar6.f24e = bVar5;
                throw th;
            }
        } catch (Throwable th2) {
            this.f501d.end(canvasStart);
            throw th2;
        }
    }

    @Override // b1.d
    public final float I() {
        return this.f510m;
    }

    @Override // b1.d
    public final float J() {
        return 0.0f;
    }

    @Override // b1.d
    public final int K() {
        return this.f506i;
    }

    @Override // b1.d
    public final void L(long j5) {
        if (p3.j.F(j5)) {
            this.f508k = true;
            this.f501d.setPivotX(((int) (this.f502e >> 32)) / 2.0f);
            this.f501d.setPivotY(((int) (this.f502e & 4294967295L)) / 2.0f);
        } else {
            this.f508k = false;
            this.f501d.setPivotX(x0.c.d(j5));
            this.f501d.setPivotY(x0.c.e(j5));
        }
    }

    @Override // b1.d
    public final long M() {
        return this.f512o;
    }

    @Override // b1.d
    public final float a() {
        return this.f507j;
    }

    @Override // b1.d
    public final void b() {
        this.f501d.setRotationX(0.0f);
    }

    @Override // b1.d
    public final void c(float f5) {
        this.f507j = f5;
        this.f501d.setAlpha(f5);
    }

    @Override // b1.d
    public final void d() {
        this.f501d.setTranslationY(0.0f);
    }

    public final void e() {
        boolean z2 = this.f515r;
        boolean z3 = false;
        boolean z4 = z2 && !this.f504g;
        if (z2 && this.f504g) {
            z3 = true;
        }
        if (z4 != this.f516s) {
            this.f516s = z4;
            this.f501d.setClipToBounds(z4);
        }
        if (z3 != this.f517t) {
            this.f517t = z3;
            this.f501d.setClipToOutline(z3);
        }
    }

    @Override // b1.d
    public final void f() {
        this.f501d.setRotationY(0.0f);
    }

    @Override // b1.d
    public final void g(float f5) {
        this.f509l = f5;
        this.f501d.setScaleX(f5);
    }

    @Override // b1.d
    public final void h() {
        l.f558a.a(this.f501d);
    }

    @Override // b1.d
    public final void i() {
        this.f501d.setTranslationX(0.0f);
    }

    @Override // b1.d
    public final void j() {
        this.f501d.setRotation(0.0f);
    }

    @Override // b1.d
    public final void k(float f5) {
        this.f510m = f5;
        this.f501d.setScaleY(f5);
    }

    public final void l(int i5) {
        RenderNode renderNode = this.f501d;
        if (i5 == 1) {
            renderNode.setLayerType(2);
            renderNode.setLayerPaint((Paint) null);
            renderNode.setHasOverlappingRendering(true);
        } else if (i5 == 2) {
            renderNode.setLayerType(0);
            renderNode.setLayerPaint((Paint) null);
            renderNode.setHasOverlappingRendering(false);
        } else {
            renderNode.setLayerType(0);
            renderNode.setLayerPaint((Paint) null);
            renderNode.setHasOverlappingRendering(true);
        }
    }

    @Override // b1.d
    public final void m(float f5) {
        this.f514q = f5;
        this.f501d.setCameraDistance(-f5);
    }

    @Override // b1.d
    public final boolean n() {
        return this.f501d.isValid();
    }

    @Override // b1.d
    public final void o(Outline outline) {
        this.f501d.setOutline(outline);
        this.f504g = outline != null;
        e();
    }

    @Override // b1.d
    public final boolean p() {
        return this.f515r;
    }

    @Override // b1.d
    public final float q() {
        return this.f509l;
    }

    @Override // b1.d
    public final void r(float f5) {
        this.f511n = f5;
        this.f501d.setElevation(f5);
    }

    @Override // b1.d
    public final float s() {
        return 0.0f;
    }

    @Override // b1.d
    public final long t() {
        return this.f513p;
    }

    @Override // b1.d
    public final void u(long j5) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f512o = j5;
            m.f559a.c(this.f501d, l0.w(j5));
        }
    }

    @Override // b1.d
    public final float v() {
        return this.f514q;
    }

    @Override // b1.d
    public final float w() {
        return 0.0f;
    }

    @Override // b1.d
    public final void x(boolean z2) {
        this.f515r = z2;
        e();
    }

    @Override // b1.d
    public final int y() {
        return this.f505h;
    }

    @Override // b1.d
    public final float z() {
        return 0.0f;
    }
}
