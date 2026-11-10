package n;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends q1.m0 implements v0.e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f4042c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f4043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final z f4044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f4045f;

    public y(g gVar, z zVar) {
        this.f4043d = gVar;
        this.f4044e = zVar;
    }

    public y(g gVar, z zVar, v0 v0Var) {
        this.f4043d = gVar;
        this.f4044e = zVar;
        this.f4045f = v0Var;
    }

    public static boolean A(float f5, long j5, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(f5);
        canvas.translate(x0.c.d(j5), x0.c.e(j5));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    public static boolean z(float f5, EdgeEffect edgeEffect, Canvas canvas) {
        if (f5 == 0.0f) {
            return edgeEffect.draw(canvas);
        }
        int iSave = canvas.save();
        canvas.rotate(f5);
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    public RenderNode B() {
        RenderNode renderNode = (RenderNode) this.f4045f;
        if (renderNode != null) {
            return renderNode;
        }
        RenderNode renderNodeF = b1.f.f();
        this.f4045f = renderNodeF;
        return renderNodeF;
    }

    @Override // v0.e
    public final void d(p1.f0 f0Var) {
        boolean zA;
        float f5;
        boolean z2;
        float f6;
        switch (this.f4042c) {
            case 0:
                r.m0 m0Var = ((v0) this.f4045f).f4035b;
                a1.c cVar = f0Var.f4828d;
                long jB = cVar.b();
                g gVar = this.f4043d;
                gVar.l(jB);
                if (x0.f.e(cVar.b())) {
                    f0Var.c();
                    return;
                }
                f0Var.c();
                gVar.f3907f.getValue();
                Canvas canvasA = y0.e.a(cVar.f27e.r());
                z zVar = this.f4044e;
                if (z.f(zVar.f4051f)) {
                    zA = A(270.0f, p3.j.b(-x0.f.b(cVar.b()), f0Var.v(m0Var.c(f0Var.getLayoutDirection()))), zVar.c(), canvasA);
                } else {
                    zA = false;
                }
                if (z.f(zVar.f4049d)) {
                    zA = A(0.0f, p3.j.b(0.0f, f0Var.v(m0Var.f5589b)), zVar.e(), canvasA) || zA;
                }
                if (z.f(zVar.f4052g)) {
                    zA = A(90.0f, p3.j.b(0.0f, f0Var.v(m0Var.d(f0Var.getLayoutDirection())) + (-((float) d4.a.H(x0.f.d(cVar.b()))))), zVar.d(), canvasA) || zA;
                }
                if (z.f(zVar.f4050e)) {
                    zA = A(180.0f, p3.j.b(-x0.f.d(cVar.b()), (-x0.f.b(cVar.b())) + f0Var.v(m0Var.f5591d)), zVar.b(), canvasA) || zA;
                }
                if (zA) {
                    gVar.g();
                    return;
                }
                return;
            default:
                a1.c cVar2 = f0Var.f4828d;
                long jB2 = cVar2.b();
                g gVar2 = this.f4043d;
                gVar2.l(jB2);
                if (x0.f.e(cVar2.b())) {
                    f0Var.c();
                    return;
                }
                gVar2.f3907f.getValue();
                float fV = f0Var.v(v.f4032a);
                Canvas canvasA2 = y0.e.a(cVar2.f27e.r());
                z zVar2 = this.f4044e;
                boolean z3 = z.f(zVar2.f4049d) || z.g(zVar2.f4053h) || z.f(zVar2.f4050e) || z.g(zVar2.f4054i);
                boolean z4 = z.f(zVar2.f4051f) || z.g(zVar2.f4055j) || z.f(zVar2.f4052g) || z.g(zVar2.f4056k);
                if (z3 && z4) {
                    B().setPosition(0, 0, canvasA2.getWidth(), canvasA2.getHeight());
                } else if (z3) {
                    B().setPosition(0, 0, (d4.a.H(fV) * 2) + canvasA2.getWidth(), canvasA2.getHeight());
                } else {
                    if (!z4) {
                        f0Var.c();
                        return;
                    }
                    B().setPosition(0, 0, canvasA2.getWidth(), (d4.a.H(fV) * 2) + canvasA2.getHeight());
                }
                RecordingCanvas recordingCanvasBeginRecording = B().beginRecording();
                if (z.g(zVar2.f4055j)) {
                    EdgeEffect edgeEffectA = zVar2.f4055j;
                    if (edgeEffectA == null) {
                        edgeEffectA = zVar2.a();
                        zVar2.f4055j = edgeEffectA;
                    }
                    z(90.0f, edgeEffectA, recordingCanvasBeginRecording);
                    edgeEffectA.finish();
                }
                boolean zF = z.f(zVar2.f4051f);
                h hVar = h.f3917a;
                if (zF) {
                    EdgeEffect edgeEffectC = zVar2.c();
                    z2 = z(270.0f, edgeEffectC, recordingCanvasBeginRecording);
                    if (z.g(zVar2.f4051f)) {
                        float fE = x0.c.e(gVar2.c());
                        EdgeEffect edgeEffectA2 = zVar2.f4055j;
                        if (edgeEffectA2 == null) {
                            edgeEffectA2 = zVar2.a();
                            zVar2.f4055j = edgeEffectA2;
                        }
                        int i5 = Build.VERSION.SDK_INT;
                        float fB = i5 >= 31 ? hVar.b(edgeEffectC) : 0.0f;
                        f5 = fV;
                        float f7 = 1 - fE;
                        if (i5 >= 31) {
                            hVar.c(edgeEffectA2, fB, f7);
                        } else {
                            edgeEffectA2.onPull(fB, f7);
                        }
                    } else {
                        f5 = fV;
                    }
                } else {
                    f5 = fV;
                    z2 = false;
                }
                if (z.g(zVar2.f4053h)) {
                    EdgeEffect edgeEffectA3 = zVar2.f4053h;
                    if (edgeEffectA3 == null) {
                        edgeEffectA3 = zVar2.a();
                        zVar2.f4053h = edgeEffectA3;
                    }
                    z(180.0f, edgeEffectA3, recordingCanvasBeginRecording);
                    edgeEffectA3.finish();
                }
                if (z.f(zVar2.f4049d)) {
                    EdgeEffect edgeEffectE = zVar2.e();
                    boolean z5 = z(0.0f, edgeEffectE, recordingCanvasBeginRecording) || z2;
                    if (z.g(zVar2.f4049d)) {
                        float fD = x0.c.d(gVar2.c());
                        EdgeEffect edgeEffectA4 = zVar2.f4053h;
                        if (edgeEffectA4 == null) {
                            edgeEffectA4 = zVar2.a();
                            zVar2.f4053h = edgeEffectA4;
                        }
                        int i6 = Build.VERSION.SDK_INT;
                        float fB2 = i6 >= 31 ? hVar.b(edgeEffectE) : 0.0f;
                        if (i6 >= 31) {
                            hVar.c(edgeEffectA4, fB2, fD);
                        } else {
                            edgeEffectA4.onPull(fB2, fD);
                        }
                    }
                    z2 = z5;
                }
                if (z.g(zVar2.f4056k)) {
                    EdgeEffect edgeEffectA5 = zVar2.f4056k;
                    if (edgeEffectA5 == null) {
                        edgeEffectA5 = zVar2.a();
                        zVar2.f4056k = edgeEffectA5;
                    }
                    z(270.0f, edgeEffectA5, recordingCanvasBeginRecording);
                    edgeEffectA5.finish();
                }
                if (z.f(zVar2.f4052g)) {
                    EdgeEffect edgeEffectD = zVar2.d();
                    boolean z6 = z(90.0f, edgeEffectD, recordingCanvasBeginRecording) || z2;
                    if (z.g(zVar2.f4052g)) {
                        float fE2 = x0.c.e(gVar2.c());
                        EdgeEffect edgeEffectA6 = zVar2.f4056k;
                        if (edgeEffectA6 == null) {
                            edgeEffectA6 = zVar2.a();
                            zVar2.f4056k = edgeEffectA6;
                        }
                        int i7 = Build.VERSION.SDK_INT;
                        float fB3 = i7 >= 31 ? hVar.b(edgeEffectD) : 0.0f;
                        if (i7 >= 31) {
                            hVar.c(edgeEffectA6, fB3, fE2);
                        } else {
                            edgeEffectA6.onPull(fB3, fE2);
                        }
                    }
                    z2 = z6;
                }
                if (z.g(zVar2.f4054i)) {
                    EdgeEffect edgeEffectA7 = zVar2.f4054i;
                    if (edgeEffectA7 == null) {
                        edgeEffectA7 = zVar2.a();
                        zVar2.f4054i = edgeEffectA7;
                    }
                    f6 = 0.0f;
                    z(0.0f, edgeEffectA7, recordingCanvasBeginRecording);
                    edgeEffectA7.finish();
                } else {
                    f6 = 0.0f;
                }
                if (z.f(zVar2.f4050e)) {
                    EdgeEffect edgeEffectB = zVar2.b();
                    boolean z7 = z(180.0f, edgeEffectB, recordingCanvasBeginRecording) || z2;
                    if (z.g(zVar2.f4050e)) {
                        float fD2 = x0.c.d(gVar2.c());
                        EdgeEffect edgeEffectA8 = zVar2.f4054i;
                        if (edgeEffectA8 == null) {
                            edgeEffectA8 = zVar2.a();
                            zVar2.f4054i = edgeEffectA8;
                        }
                        int i8 = Build.VERSION.SDK_INT;
                        float fB4 = i8 >= 31 ? hVar.b(edgeEffectB) : f6;
                        float f8 = 1 - fD2;
                        if (i8 >= 31) {
                            hVar.c(edgeEffectA8, fB4, f8);
                        } else {
                            edgeEffectA8.onPull(fB4, f8);
                        }
                    }
                    z2 = z7;
                }
                if (z2) {
                    gVar2.g();
                }
                float f9 = z4 ? f6 : f5;
                if (!z3) {
                    f6 = f5;
                }
                k2.k layoutDirection = f0Var.getLayoutDirection();
                y0.d dVar = new y0.d();
                dVar.f6617a = recordingCanvasBeginRecording;
                long jB3 = cVar2.b();
                a1.b bVar = cVar2.f27e;
                a1.a aVar = ((a1.c) bVar.f25f).f26d;
                k2.b bVar2 = aVar.f18a;
                k2.k kVar = aVar.f19b;
                y0.r rVarR = bVar.r();
                long jT = cVar2.f27e.t();
                a1.b bVar3 = cVar2.f27e;
                b1.b bVar4 = (b1.b) bVar3.f24e;
                bVar3.B(f0Var);
                bVar3.C(layoutDirection);
                bVar3.A(dVar);
                bVar3.D(jB3);
                bVar3.f24e = null;
                dVar.p();
                try {
                    ((a1.d) cVar2.f27e.f23d).x(f9, f6);
                    try {
                        f0Var.c();
                        float f10 = -f9;
                        float f11 = -f6;
                        ((a1.d) cVar2.f27e.f23d).x(f10, f11);
                        dVar.m();
                        a1.b bVar5 = cVar2.f27e;
                        bVar5.B(bVar2);
                        bVar5.C(kVar);
                        bVar5.A(rVarR);
                        bVar5.D(jT);
                        bVar5.f24e = bVar4;
                        B().endRecording();
                        int iSave = canvasA2.save();
                        canvasA2.translate(f10, f11);
                        canvasA2.drawRenderNode(B());
                        canvasA2.restoreToCount(iSave);
                        return;
                    } catch (Throwable th) {
                        ((a1.d) cVar2.f27e.f23d).x(-f9, -f6);
                        throw th;
                    }
                } catch (Throwable th2) {
                    dVar.m();
                    a1.b bVar6 = cVar2.f27e;
                    bVar6.B(bVar2);
                    bVar6.C(kVar);
                    bVar6.A(rVarR);
                    bVar6.D(jT);
                    bVar6.f24e = bVar4;
                    throw th2;
                }
        }
    }
}
