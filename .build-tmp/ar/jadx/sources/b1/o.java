package b1;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.view.View;
import y0.r;
import y0.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends View {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final n f561n = new n(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c1.a f562d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s f563e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a1.c f564f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f565g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Outline f566h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f567i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k2.b f568j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public k2.k f569k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a4.c f570l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f571m;

    public o(c1.a aVar, s sVar, a1.c cVar) {
        super(aVar.getContext());
        this.f562d = aVar;
        this.f563e = sVar;
        this.f564f = cVar;
        setOutlineProvider(f561n);
        this.f567i = true;
        this.f568j = a1.e.f32a;
        this.f569k = k2.k.f3416d;
        d.f497a.getClass();
        this.f570l = a.f474g;
        setWillNotDraw(false);
        setClipBounds(null);
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
        s sVar = this.f563e;
        y0.d dVar = sVar.f6683a;
        Canvas canvas2 = dVar.f6617a;
        dVar.f6617a = canvas;
        k2.b bVar = this.f568j;
        k2.k kVar = this.f569k;
        long jF = p3.j.f(getWidth(), getHeight());
        b bVar2 = this.f571m;
        a4.c cVar = this.f570l;
        a1.c cVar2 = this.f564f;
        a1.b bVar3 = cVar2.f27e;
        a1.a aVar = ((a1.c) bVar3.f25f).f26d;
        k2.b bVar4 = aVar.f18a;
        k2.k kVar2 = aVar.f19b;
        r rVarR = bVar3.r();
        a1.b bVar5 = cVar2.f27e;
        long jT = bVar5.t();
        b bVar6 = (b) bVar5.f24e;
        bVar5.B(bVar);
        bVar5.C(kVar);
        bVar5.A(dVar);
        bVar5.D(jF);
        bVar5.f24e = bVar2;
        dVar.p();
        try {
            cVar.c(cVar2);
            dVar.m();
            bVar5.B(bVar4);
            bVar5.C(kVar2);
            bVar5.A(rVarR);
            bVar5.D(jT);
            bVar5.f24e = bVar6;
            sVar.f6683a.f6617a = canvas2;
            this.f565g = false;
        } catch (Throwable th) {
            dVar.m();
            bVar5.B(bVar4);
            bVar5.C(kVar2);
            bVar5.A(rVarR);
            bVar5.D(jT);
            bVar5.f24e = bVar6;
            throw th;
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    public final boolean getCanUseCompositingLayer$ui_graphics_release() {
        return this.f567i;
    }

    public final s getCanvasHolder() {
        return this.f563e;
    }

    public final View getOwnerView() {
        return this.f562d;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.f567i;
    }

    @Override // android.view.View
    public final void invalidate() {
        if (this.f565g) {
            return;
        }
        this.f565g = true;
        super.invalidate();
    }

    @Override // android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
    }

    public final void setCanUseCompositingLayer$ui_graphics_release(boolean z2) {
        if (this.f567i != z2) {
            this.f567i = z2;
            invalidate();
        }
    }

    public final void setInvalidated(boolean z2) {
        this.f565g = z2;
    }
}
