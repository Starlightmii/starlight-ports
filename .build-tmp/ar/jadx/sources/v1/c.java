package v1;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import b0.t;
import c0.g1;
import c0.o4;
import f0.l;
import i4.d1;
import i4.h1;
import i4.y;
import java.util.function.Consumer;
import p1.y0;
import w1.n;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements ScrollCaptureCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f6189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k2.i f6190b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f6191c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n4.c f6192d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f6193e;

    public c(n nVar, k2.i iVar, n4.c cVar, i iVar2) {
        this.f6189a = nVar;
        this.f6190b = iVar;
        this.f6191c = iVar2;
        this.f6192d = new n4.c(cVar.f4276d.k(e.f6196d));
        this.f6193e = new g(iVar.a(), new o4(this, null));
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:47:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:54:0x0130  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object a(c cVar, ScrollCaptureSession scrollCaptureSession, k2.i iVar, v3.c cVar2) {
        a aVar;
        int i5;
        int i6;
        c cVar3;
        int i7;
        ScrollCaptureSession scrollCaptureSessionH;
        int i8;
        int iM;
        int iM2;
        int i9;
        int i10;
        y0 y0VarC;
        Canvas canvasLockHardwareCanvas;
        if (cVar2 instanceof a) {
            aVar = (a) cVar2;
            int i11 = aVar.f6184k;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                aVar.f6184k = i11 - Integer.MIN_VALUE;
            } else {
                aVar = new a(cVar, cVar2);
            }
        } else {
            aVar = new a(cVar, cVar2);
        }
        Object obj = aVar.f6182i;
        int i12 = aVar.f6184k;
        u3.a aVar2 = u3.a.f5838d;
        if (i12 == 0) {
            p3.j.O(obj);
            i5 = iVar.f3412b;
            i6 = iVar.f3414d;
            g gVar = cVar.f6193e;
            aVar.f6177d = cVar;
            aVar.f6178e = scrollCaptureSession;
            aVar.f6179f = iVar;
            aVar.f6180g = i5;
            aVar.f6181h = i6;
            aVar.f6184k = 1;
            int i13 = gVar.f6201a;
            if (i5 > i6) {
                throw new IllegalArgumentException(b.b.d("Expected min=", i5, " ≤ max=", i6).toString());
            }
            int i14 = i6 - i5;
            if (i14 > i13) {
                throw new IllegalArgumentException(b.b.d("Expected range (", i14, ") to be ≤ viewportSize=", i13).toString());
            }
            float f5 = i5;
            float f6 = gVar.f6202b;
            Object obj2 = p3.n.f5026a;
            if (f5 < f6 || i6 > i13 + f6) {
                Object objB = gVar.b((f5 < f6 ? i5 : i6 - i13) - f6, aVar);
                if (objB != aVar2) {
                    objB = obj2;
                }
                if (objB == aVar2) {
                    obj2 = objB;
                }
            }
            if (obj2 != aVar2) {
            }
            return aVar2;
        }
        if (i12 == 1) {
            int i15 = aVar.f6181h;
            int i16 = aVar.f6180g;
            iVar = aVar.f6179f;
            ScrollCaptureSession scrollCaptureSessionH2 = q1.i.h(aVar.f6178e);
            c cVar4 = aVar.f6177d;
            p3.j.O(obj);
            i5 = i16;
            scrollCaptureSession = scrollCaptureSessionH2;
            i6 = i15;
            cVar = cVar4;
        } else {
            if (i12 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i7 = aVar.f6181h;
            i8 = aVar.f6180g;
            iVar = aVar.f6179f;
            scrollCaptureSessionH = q1.i.h(aVar.f6178e);
            cVar3 = aVar.f6177d;
            p3.j.O(obj);
        }
        g gVar2 = cVar3.f6193e;
        iM = d4.a.m(i8 - d4.a.H(gVar2.f6202b), 0, gVar2.f6201a);
        g gVar3 = cVar3.f6193e;
        iM2 = d4.a.m(i7 - d4.a.H(gVar3.f6202b), 0, gVar3.f6201a);
        i9 = iVar.f3411a;
        i10 = iVar.f3413c;
        if (iM == iM2) {
            return k2.i.f3410e;
        }
        y0VarC = cVar3.f6189a.c();
        if (y0VarC != null) {
            throw new IllegalStateException("Could not find coordinator for semantics node.");
        }
        canvasLockHardwareCanvas = scrollCaptureSessionH.getSurface().lockHardwareCanvas();
        try {
            BlendMode unused = BlendMode.CLEAR;
            canvasLockHardwareCanvas.drawColor(0, BlendMode.CLEAR);
            Canvas canvas = y0.e.f6621a;
            y0.d dVar = new y0.d();
            dVar.f6617a = canvasLockHardwareCanvas;
            dVar.k(-i9, -iM);
            y0VarC.D0(dVar, null);
            int iH = d4.a.H(cVar3.f6193e.f6202b);
            return new k2.i(i9, iM + iH, i10, iM2 + iH);
        } finally {
            scrollCaptureSessionH.getSurface().unlockCanvasAndPost(canvasLockHardwareCanvas);
        }
        b bVar = b.f6185f;
        aVar.f6177d = cVar;
        aVar.f6178e = scrollCaptureSession;
        aVar.f6179f = iVar;
        aVar.f6180g = i5;
        aVar.f6181h = i6;
        aVar.f6184k = 2;
        if (l.q(aVar.getContext()).d(bVar, aVar) != aVar2) {
            cVar3 = cVar;
            i7 = i6;
            scrollCaptureSessionH = scrollCaptureSession;
            i8 = i5;
            g gVar4 = cVar3.f6193e;
            iM = d4.a.m(i8 - d4.a.H(gVar4.f6202b), 0, gVar4.f6201a);
            g gVar5 = cVar3.f6193e;
            iM2 = d4.a.m(i7 - d4.a.H(gVar5.f6202b), 0, gVar5.f6201a);
            i9 = iVar.f3411a;
            i10 = iVar.f3413c;
            if (iM == iM2) {
                return k2.i.f3410e;
            }
            y0VarC = cVar3.f6189a.c();
            if (y0VarC != null) {
                throw new IllegalStateException("Could not find coordinator for semantics node.");
            }
            canvasLockHardwareCanvas = scrollCaptureSessionH.getSurface().lockHardwareCanvas();
            BlendMode unused2 = BlendMode.CLEAR;
            canvasLockHardwareCanvas.drawColor(0, BlendMode.CLEAR);
            Canvas canvas2 = y0.e.f6621a;
            y0.d dVar2 = new y0.d();
            dVar2.f6617a = canvasLockHardwareCanvas;
            dVar2.k(-i9, -iM);
            y0VarC.D0(dVar2, null);
            int iH2 = d4.a.H(cVar3.f6193e.f6202b);
            return new k2.i(i9, iM + iH2, i10, iM2 + iH2);
        }
        return aVar2;
    }

    public final void onScrollCaptureEnd(Runnable runnable) {
        y.n(this.f6192d, d1.f2724e, new t(this, runnable, null, 18), 2);
    }

    public final void onScrollCaptureImageRequest(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Rect rect, Consumer consumer) {
        h1 h1VarN = y.n(this.f6192d, null, new l4.n(this, scrollCaptureSession, rect, consumer, null, 6), 3);
        h1VarN.g(new g1(29, cancellationSignal));
        cancellationSignal.setOnCancelListener(new d(0, h1VarN));
    }

    public final void onScrollCaptureSearch(CancellationSignal cancellationSignal, Consumer consumer) {
        consumer.accept(l0.t(this.f6190b));
    }

    public final void onScrollCaptureStart(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Runnable runnable) {
        this.f6193e.f6202b = 0.0f;
        this.f6191c.f6204a.setValue(Boolean.TRUE);
        runnable.run();
    }
}
