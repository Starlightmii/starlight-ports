package c0;

import android.content.ClipDescription;
import android.content.Context;
import android.view.Choreographer;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import io.github.libxposed.service.IXposedService;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c2 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f781f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f782g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c2(Object obj, int i5, Object obj2) {
        super(1);
        this.f780e = i5;
        this.f781f = obj;
        this.f782g = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x007a  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // a4.c
    public final Object c(Object obj) {
        float fD;
        x.w wVar;
        v.o0 o0Var;
        boolean z2;
        q1.f1 f1Var;
        int i5 = 21;
        int i6 = 4;
        int i7 = 3;
        int i8 = 2;
        int i9 = 0;
        objArr = 0;
        objArr = 0;
        objArr = 0;
        Object[] objArr = 0;
        zD = false;
        zD = false;
        zD = false;
        zD = false;
        zD = false;
        boolean zD = false;
        z = false;
        z = false;
        boolean z3 = false;
        int i10 = 1;
        switch (this.f780e) {
            case 0:
                p1.f0 f0Var = (p1.f0) obj;
                long j5 = ((x0.f) ((d0.n) this.f781f).get()).f6546a;
                float fD2 = x0.f.d(j5);
                if (fD2 > 0.0f) {
                    float fV = f0Var.v(d2.f798a);
                    a1.c cVar = f0Var.f4828d;
                    float fV2 = f0Var.v(((r.l0) this.f782g).c(f0Var.getLayoutDirection())) - fV;
                    float f5 = 2;
                    float fD3 = (fV * f5) + fD2 + fV2;
                    k2.k layoutDirection = f0Var.getLayoutDirection();
                    int[] iArr = b2.f767a;
                    if (iArr[layoutDirection.ordinal()] == 1) {
                        fD = x0.f.d(cVar.b()) - fD3;
                    } else {
                        fD = fV2 < 0.0f ? 0.0f : fV2;
                    }
                    float f6 = fD;
                    if (iArr[f0Var.getLayoutDirection().ordinal()] == 1) {
                        fD3 = x0.f.d(cVar.b()) - (fV2 >= 0.0f ? fV2 : 0.0f);
                    }
                    float f7 = fD3;
                    float fB = x0.f.b(j5);
                    float f8 = (-fB) / f5;
                    float f9 = fB / f5;
                    a1.b bVar = cVar.f27e;
                    long jT = bVar.t();
                    bVar.r().p();
                    try {
                        ((a1.b) ((a1.d) bVar.f23d).f31e).r().i(f6, f8, f7, f9, 0);
                        f0Var.c();
                    } finally {
                        bVar.r().m();
                        bVar.D(jT);
                    }
                } else {
                    f0Var.c();
                }
                return p3.n.f5026a;
            case 1:
                ((d0.k) this.f781f).f1540a.setValue(new r.z((r.z0) this.f782g, (r.z0) obj));
                return p3.n.f5026a;
            case 2:
                y0.l0.m((a1.f) obj, (y0.j0) this.f781f, ((y0.u) ((l4) this.f782g).f1036a.get()).f6696a);
                return p3.n.f5026a;
            case 3:
                v0.c cVar2 = (v0.c) obj;
                return cVar2.c(new g1(27, new c2(((y0.q0) this.f781f).c(cVar2.f6165d.b(), cVar2.f6165d.getLayoutDirection(), cVar2), i8, (l4) this.f782g)));
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                i4.y.n((i4.v) this.f781f, null, new b0.z((m.s0) this.f782g, null), 1);
                return new m.r0();
            case 5:
                m.s0 s0Var = (m.s0) this.f781f;
                m.q0 q0Var = (m.q0) this.f782g;
                s0Var.f3757g.add(q0Var);
                return new m.v0(s0Var, i9, q0Var);
            case 6:
                p1.f0 f0Var2 = (p1.f0) obj;
                f0Var2.c();
                a1.f.f(f0Var2, ((y0.g0) this.f781f).f6628a, (y0.p) this.f782g, 0.0f, null, 60);
                return p3.n.f5026a;
            case 7:
                p1.f0 f0Var3 = (p1.f0) obj;
                f0Var3.c();
                a1.f.f(f0Var3, (y0.j) this.f781f, (y0.p) this.f782g, 0.0f, null, 60);
                return p3.n.f5026a;
            case 8:
                ((q.k) this.f781f).c((q.i) this.f782g);
                return p3.n.f5026a;
            case 9:
                n2.p pVar = (n2.p) this.f781f;
                pVar.setPositionProvider((n2.s) this.f782g);
                pVar.l();
                return new n2.d();
            case 10:
                ((j1.h) this.f781f).f3256a.l((p.h) this.f782g);
                return p3.n.f5026a;
            case 11:
                k1.c cVar3 = (k1.c) this.f781f;
                k1.b bVar2 = cVar3.f3398b;
                k1.b bVar3 = cVar3.f3397a;
                a.a.g(cVar3, (j1.u) obj);
                p.a1 a1Var = (p.a1) this.f782g;
                float fA = ((q1.o2) p1.f.h(a1Var, q1.j1.f5248q)).a();
                long jF = h1.c.f(fA, fA);
                if (k2.o.b(jF) <= 0.0f || k2.o.c(jF) <= 0.0f) {
                    d4.a.L("maximumVelocity should be a positive value. You specified=" + ((Object) k2.o.f(jF)));
                    throw null;
                }
                long jF2 = h1.c.f(bVar3.b(k2.o.b(jF)), bVar2.b(k2.o.c(jF)));
                k1.a[] aVarArr = bVar3.f3392b;
                q3.k.E(aVarArr, 0, aVarArr.length);
                bVar3.f3393c = 0;
                k1.a[] aVarArr2 = bVar2.f3392b;
                q3.k.E(aVarArr2, 0, aVarArr2.length);
                bVar2.f3393c = 0;
                cVar3.f3399c = 0L;
                k4.c cVar4 = a1Var.f4411w;
                if (cVar4 != null) {
                    int i11 = p.i0.f4483a;
                    cVar4.o(new p.r(h1.c.f(Float.isNaN(k2.o.b(jF2)) ? 0.0f : k2.o.b(jF2), Float.isNaN(k2.o.c(jF2)) ? 0.0f : k2.o.c(jF2))));
                }
                return p3.n.f5026a;
            case 12:
                p.g1 g1Var = (p.g1) this.f781f;
                p.i1 i1Var = (p.i1) this.f782g;
                long j6 = ((p.p) obj).f4565a;
                long jA = i1Var.f4487d == p.l0.f4526e ? x0.c.a(j6, 0.0f, 1) : x0.c.a(j6, 0.0f, 2);
                p.i1 i1Var2 = g1Var.f4466a;
                i1Var2.f4490g = 1;
                n.x0 x0Var = i1Var2.f4485b;
                if (x0Var == null || !(i1Var2.f4484a.c() || i1Var2.f4484a.a())) {
                    p.i1.a(i1Var2, i1Var2.f4491h, jA, 1);
                } else {
                    x0Var.d(jA, i1Var2.f4490g, i1Var2.f4493j);
                }
                return p3.n.f5026a;
            case 13:
                ((Number) obj).longValue();
                p.v1 v1Var = (p.v1) this.f781f;
                float f10 = v1Var.f4623e;
                v1Var.f4623e = 0.0f;
                ((a4.c) this.f782g).c(Float.valueOf(f10));
                return p3.n.f5026a;
            case 14:
                Context context = (Context) this.f781f;
                Context applicationContext = context.getApplicationContext();
                q1.o0 o0Var2 = (q1.o0) this.f782g;
                applicationContext.registerComponentCallbacks(o0Var2);
                return new m.v0(context, i10, o0Var2);
            case 15:
                Context context2 = (Context) this.f781f;
                Context applicationContext2 = context2.getApplicationContext();
                q1.p0 p0Var = (q1.p0) this.f782g;
                applicationContext2.registerComponentCallbacks(p0Var);
                return new m.v0(context2, i8, p0Var);
            case 16:
                return new q1.t1((x.u) this.f781f, new b0.a(i5, (q1.r0) this.f782g));
            case 17:
                q1.t1 t1Var = (q1.t1) this.f781f;
                synchronized (t1Var.f5369c) {
                    try {
                        t1Var.f5371e = true;
                        h0.e eVar = t1Var.f5370d;
                        int i12 = eVar.f2578f;
                        if (i12 > 0) {
                            Object[] objArr2 = eVar.f2576d;
                            do {
                                e2.o oVar = (e2.o) ((WeakReference) objArr2[i9]).get();
                                if (oVar != null && (wVar = oVar.f2046b) != null) {
                                    oVar.a(wVar);
                                    oVar.f2046b = null;
                                }
                                i9++;
                            } while (i9 < i12);
                        }
                        t1Var.f5370d.g();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                ((q1.r0) this.f782g).f5333e.f2065a.g();
                return p3.n.f5026a;
            case 18:
                q1.v0 v0Var = (q1.v0) this.f781f;
                q1.w0 w0Var = (q1.w0) this.f782g;
                synchronized (v0Var.f5428h) {
                    v0Var.f5430j.remove(w0Var);
                }
                return p3.n.f5026a;
            case 19:
                ((Choreographer) ((f0.g) this.f781f).f2173e).removeFrameCallback((q1.w0) this.f782g);
                return p3.n.f5026a;
            case 20:
                q1.m mVar = (q1.m) obj;
                a4.e eVar2 = (a4.e) this.f782g;
                q1.f3 f3Var = (q1.f3) this.f781f;
                if (!f3Var.f5204f) {
                    androidx.lifecycle.q lifecycle = mVar.f5259a.getLifecycle();
                    f3Var.f5206h = eVar2;
                    if (f3Var.f5205g == null) {
                        f3Var.f5205g = lifecycle;
                        lifecycle.a(f3Var);
                    } else if (((androidx.lifecycle.x) lifecycle).f298c.compareTo(androidx.lifecycle.p.f276f) >= 0) {
                        f3Var.f5203e.A(new n0.d(-2000640158, true, new q1.e3(f3Var, eVar2, i10)));
                    }
                }
                return p3.n.f5026a;
            case 21:
                r.a1 a1Var2 = (r.a1) this.f781f;
                View view = (View) this.f782g;
                r.c0 c0Var = a1Var2.f5526t;
                if (a1Var2.f5525s == 0) {
                    int i13 = w2.u.f6438a;
                    w2.p.b(view, c0Var);
                    if (view.isAttachedToWindow()) {
                        view.requestApplyInsets();
                    }
                    view.addOnAttachStateChangeListener(c0Var);
                    w2.u.a(view, c0Var);
                }
                a1Var2.f5525s++;
                return new m.v0(a1Var2, i7, view);
            case 22:
                KeyEvent keyEvent = ((h1.b) obj).f2604a;
                if (((v.o0) this.f781f).a() == v.f0.f5918e && keyEvent.getKeyCode() == 4 && h1.c.y(keyEvent) == 1) {
                    ((z.e0) this.f782g).e(null);
                    z3 = true;
                }
                return Boolean.valueOf(z3);
            case 23:
                KeyEvent keyEvent2 = ((h1.b) obj).f2604a;
                w0.g gVar = (w0.g) this.f781f;
                InputDevice device = keyEvent2.getDevice();
                if (device != null && device.supportsSource(513) && !device.isVirtual() && h1.c.y(keyEvent2) == 2 && keyEvent2.getSource() != 257) {
                    if (v.l0.i(keyEvent2, 19)) {
                        zD = ((androidx.compose.ui.focus.b) gVar).d(5);
                    } else if (v.l0.i(keyEvent2, 20)) {
                        zD = ((androidx.compose.ui.focus.b) gVar).d(6);
                    } else if (v.l0.i(keyEvent2, 21)) {
                        zD = ((androidx.compose.ui.focus.b) gVar).d(3);
                    } else if (v.l0.i(keyEvent2, 22)) {
                        zD = ((androidx.compose.ui.focus.b) gVar).d(4);
                    } else if (v.l0.i(keyEvent2, 23)) {
                        q1.k2 k2Var = ((v.o0) this.f782g).f6008c;
                        if (k2Var != null) {
                            ((q1.k1) k2Var).b();
                        }
                        zD = true;
                    }
                }
                return Boolean.valueOf(zD);
            case 24:
                return new m.v0((f0.i1) this.f781f, i6, (q.k) this.f782g);
            case 25:
                n1.l0.h((n1.l0) obj, (n1.m0) this.f781f, 0, 0, ((y0.o) this.f782g).f6665q, 4);
                return p3.n.f5026a;
            case 26:
                n1.l0.h((n1.l0) obj, (n1.m0) this.f781f, 0, 0, ((y0.r0) this.f782g).A, 4);
                return p3.n.f5026a;
            case 27:
                j1.u uVar = (j1.u) obj;
                a1.d dVar = (a1.d) this.f781f;
                long j7 = uVar.f3276c;
                e2.f0 f0Var4 = (e2.f0) this.f782g;
                z.e0 e0Var = (z.e0) dVar.f31e;
                if (e0Var.h() && e0Var.j().f2062a.f6759d.length() != 0 && (o0Var = e0Var.f6930d) != null && o0Var.d() != null) {
                    dVar.y(e0Var.j(), j7, false, f0Var4);
                    objArr = 1;
                }
                if (objArr != 0) {
                    uVar.a();
                }
                return p3.n.f5026a;
            default:
                o.e eVar3 = (o.e) obj;
                z.e0 e0Var2 = (z.e0) this.f781f;
                f0.s1 s1Var = e0Var2.f6936j;
                boolean zB = y1.g0.b(e0Var2.j().f2063b);
                o.j jVar = (o.j) this.f782g;
                o.e.b(eVar3, new v.i(1), !zB && ((Boolean) s1Var.getValue()).booleanValue(), new z.h0(jVar, e0Var2, i9));
                o.e.b(eVar3, new v.i(2), !zB, new z.h0(jVar, e0Var2, i10));
                if (!((Boolean) s1Var.getValue()).booleanValue() || (f1Var = e0Var2.f6932f) == null) {
                    z2 = false;
                } else {
                    ClipDescription primaryClipDescription = ((q1.h) f1Var).f5215a.getPrimaryClipDescription();
                    if (primaryClipDescription != null ? primaryClipDescription.hasMimeType("text/*") : false) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                o.e.b(eVar3, new v.i(3), z2, new z.h0(jVar, e0Var2, i8));
                o.e.b(eVar3, new v.i(4), y1.g0.c(e0Var2.j().f2063b) != e0Var2.j().f2062a.f6759d.length(), new z.h0(jVar, e0Var2, i7));
                return p3.n.f5026a;
        }
    }
}
