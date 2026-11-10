package c;

import android.view.DragEvent;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import b.f0;
import b.h0;
import b.i0;
import b4.l;
import b4.m;
import b4.s;
import b4.w;
import f0.s1;
import i4.s0;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import k2.h;
import m.j;
import n1.l0;
import n1.m0;
import p.g1;
import p.i;
import p.i1;
import p1.n1;
import p1.o1;
import p3.n;
import q1.u;
import r.j0;
import r.k0;
import r.n0;
import v.e1;
import v.o0;
import w0.r;
import y0.p0;
import y1.a0;
import y1.d0;
import y1.e0;
import y1.g0;
import y1.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f702f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f703g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f704h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i5) {
        super(1);
        this.f701e = i5;
        this.f702f = obj;
        this.f703g = obj2;
        this.f704h = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public b(r rVar, androidx.compose.ui.focus.b bVar, a4.c cVar) {
        super(1);
        this.f701e = 11;
        this.f702f = rVar;
        this.f703g = bVar;
        this.f704h = (m) cVar;
    }

    /* JADX WARN: Type inference failed for: r0v105, types: [a4.c, b4.m] */
    /* JADX WARN: Type inference failed for: r15v10, types: [a4.a, b4.m] */
    @Override // a4.c
    public final Object c(Object obj) {
        boolean zBooleanValue;
        switch (this.f701e) {
            case 0:
                i0 i0Var = (i0) this.f702f;
                v vVar = (v) this.f703g;
                d dVar = (d) this.f704h;
                i0Var.getClass();
                l.e("owner", vVar);
                l.e("onBackPressedCallback", dVar);
                q lifecycle = vVar.getLifecycle();
                if (((x) lifecycle).f298c != p.f274d) {
                    dVar.f707a.add(new f0(i0Var, lifecycle, dVar));
                    i0Var.d();
                    dVar.f708b = new h0(0, i0Var, i0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 0, 0);
                }
                return new a(0, dVar);
            case 1:
                j jVar = (j) obj;
                m.c cVar = (m.c) this.f702f;
                m.d.i(jVar, cVar.f3631c);
                s1 s1Var = jVar.f3682e;
                Object objA = m.c.a(cVar, s1Var.getValue());
                if (!l.a(objA, s1Var.getValue())) {
                    cVar.f3631c.f3696e.setValue(objA);
                    ((m.l) this.f703g).f3696e.setValue(objA);
                    jVar.f3686i.setValue(Boolean.FALSE);
                    jVar.f3681d.a();
                    ((s) this.f704h).f693d = true;
                }
                return n.f5026a;
            case 2:
                float fFloatValue = ((Number) obj).floatValue();
                i iVar = (i) this.f702f;
                float f5 = iVar.f4476s ? 1.0f : -1.0f;
                i1 i1Var = iVar.f4475r;
                g1 g1Var = (g1) this.f704h;
                long jD = i1Var.d(i1Var.g(f5 * fFloatValue));
                i1 i1Var2 = g1Var.f4466a;
                float f6 = i1Var.f(i1Var.d(i1.a(i1Var2, i1Var2.f4491h, jD, 1))) * f5;
                if (Math.abs(f6) < Math.abs(fFloatValue)) {
                    s0 s0Var = (s0) this.f703g;
                    CancellationException cancellationException = new CancellationException("Scroll animation cancelled because scroll was not consumed (" + f6 + " < " + fFloatValue + ')');
                    cancellationException.initCause(null);
                    s0Var.b(cancellationException);
                }
                return n.f5026a;
            case 3:
                m0 m0Var = (m0) this.f703g;
                l0 l0Var = (l0) obj;
                n1.h0 h0Var = (n1.h0) this.f704h;
                r.i0 i0Var2 = (r.i0) this.f702f;
                if (i0Var2.f5570s) {
                    l0.f(l0Var, m0Var, h0Var.M(i0Var2.f5568q), h0Var.M(i0Var2.f5569r));
                } else {
                    l0.d(l0Var, m0Var, h0Var.M(i0Var2.f5568q), h0Var.M(i0Var2.f5569r));
                }
                return n.f5026a;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                m0 m0Var2 = (m0) this.f704h;
                l0 l0Var2 = (l0) obj;
                j0 j0Var = (j0) this.f702f;
                long j5 = ((h) j0Var.f5574q.c((n1.h0) this.f703g)).f3409a;
                if (j0Var.f5575r) {
                    l0.g(l0Var2, m0Var2, (int) (j5 >> 32), (int) (j5 & 4294967295L));
                } else {
                    l0.h(l0Var2, m0Var2, (int) (j5 >> 32), (int) (j5 & 4294967295L), null, 12);
                }
                return n.f5026a;
            case 5:
                m0 m0Var3 = (m0) this.f703g;
                l0 l0Var3 = (l0) obj;
                n1.h0 h0Var2 = (n1.h0) this.f704h;
                k0 k0Var = (k0) this.f702f;
                if (k0Var.f5581u) {
                    l0.f(l0Var3, m0Var3, h0Var2.M(k0Var.f5577q), h0Var2.M(k0Var.f5578r));
                } else {
                    l0.d(l0Var3, m0Var3, h0Var2.M(k0Var.f5577q), h0Var2.M(k0Var.f5578r));
                }
                return n.f5026a;
            case 6:
                m0 m0Var4 = (m0) this.f702f;
                n1.h0 h0Var3 = (n1.h0) this.f703g;
                n0 n0Var = (n0) this.f704h;
                l0.d((l0) obj, m0Var4, h0Var3.M(n0Var.f5595q.c(h0Var3.getLayoutDirection())), h0Var3.M(n0Var.f5595q.b()));
                return n.f5026a;
            case 7:
                o1 o1Var = (o1) obj;
                u0.c cVar2 = (u0.c) o1Var;
                if (((q1.o1) ((u) p1.f.t((u0.c) this.f703g)).getDragAndDropManager()).f5298b.contains(cVar2)) {
                    DragEvent dragEvent = (DragEvent) ((a1.d) this.f704h).f31e;
                    if (q.f.c(cVar2, p3.j.b(dragEvent.getX(), dragEvent.getY()))) {
                        ((w) this.f702f).f697d = o1Var;
                        return n1.f4940f;
                    }
                }
                return n1.f4938d;
            case 8:
                e2.x xVar = (e2.x) obj;
                ((f0.i1) this.f703g).setValue(xVar);
                f0.i1 i1Var3 = (f0.i1) this.f704h;
                boolean zA = l.a((String) i1Var3.getValue(), xVar.f2062a.f6759d);
                y1.f fVar = xVar.f2062a;
                i1Var3.setValue(fVar.f6759d);
                if (!zA) {
                    ((a4.c) this.f702f).c(fVar.f6759d);
                }
                return n.f5026a;
            case 9:
                a1.f fVar2 = (a1.f) obj;
                o0 o0Var = (o0) this.f702f;
                e1 e1VarD = o0Var.d();
                if (e1VarD != null) {
                    long j6 = ((e2.x) this.f703g).f2063b;
                    e2.r rVar = (e2.r) this.f704h;
                    y0.r rVarR = fVar2.A().r();
                    long j7 = ((g0) o0Var.f6029x.getValue()).f6770a;
                    long j8 = ((g0) o0Var.f6030y.getValue()).f6770a;
                    e0 e0Var = e1VarD.f5899a;
                    d0 d0Var = e0Var.f6753a;
                    y1.m mVar = e0Var.f6754b;
                    y0.h hVar = o0Var.f6027v;
                    long j9 = o0Var.f6028w;
                    if (!g0.b(j7)) {
                        hVar.e(j9);
                        int iB = rVar.b(g0.e(j7));
                        int iB2 = rVar.b(g0.d(j7));
                        if (iB != iB2) {
                            rVarR.b(e0Var.j(iB, iB2), hVar);
                        }
                    } else if (!g0.b(j8)) {
                        long jB = d0Var.f6744b.b();
                        y0.u uVar = new y0.u(jB);
                        if (jB == 16) {
                            uVar = null;
                        }
                        long j10 = uVar != null ? uVar.f6696a : y0.u.f6689b;
                        hVar.e(y0.u.b(y0.u.d(j10) * 0.2f, j10));
                        int iB3 = rVar.b(g0.e(j8));
                        int iB4 = rVar.b(g0.d(j8));
                        if (iB3 != iB4) {
                            rVarR.b(e0Var.j(iB3, iB4), hVar);
                        }
                    } else if (!g0.b(j6)) {
                        hVar.e(j9);
                        int iB5 = rVar.b(g0.e(j6));
                        int iB6 = rVar.b(g0.d(j6));
                        if (iB5 != iB6) {
                            rVarR.b(e0Var.j(iB5, iB6), hVar);
                        }
                    }
                    long j11 = e0Var.f6755c;
                    boolean z2 = ((((float) ((int) (j11 >> 32))) > mVar.f6792d ? 1 : (((float) ((int) (j11 >> 32))) == mVar.f6792d ? 0 : -1)) < 0 || mVar.f6791c || (((float) ((int) (j11 & 4294967295L))) > mVar.f6793e ? 1 : (((float) ((int) (j11 & 4294967295L))) == mVar.f6793e ? 0 : -1)) < 0) && d0Var.f6748f != 3;
                    if (z2) {
                        x0.d dVarB = p3.p.b(0L, p3.j.f((int) (j11 >> 32), (int) (j11 & 4294967295L)));
                        rVarR.p();
                        y0.r.h(rVarR, dVarB);
                    }
                    a0 a0Var = d0Var.f6744b.f6780a;
                    j2.j jVar2 = a0Var.f6721m;
                    j2.m mVar2 = a0Var.f6709a;
                    if (jVar2 == null) {
                        jVar2 = j2.j.f3322b;
                    }
                    j2.j jVar3 = jVar2;
                    p0 p0Var = a0Var.f6722n;
                    if (p0Var == null) {
                        p0Var = p0.f6668d;
                    }
                    p0 p0Var2 = p0Var;
                    a1.g gVar = a0Var.f6724p;
                    if (gVar == null) {
                        gVar = a1.i.f33a;
                    }
                    a1.g gVar2 = gVar;
                    try {
                        y0.p pVarC = mVar2.c();
                        j2.l lVar = j2.l.f3327a;
                        if (pVarC != null) {
                            y1.m.g(mVar, rVarR, pVarC, mVar2 != lVar ? mVar2.a() : 1.0f, p0Var2, jVar3, gVar2);
                        } else {
                            long jB2 = mVar2 != lVar ? mVar2.b() : y0.u.f6689b;
                            rVarR.p();
                            ArrayList arrayList = mVar.f6796h;
                            int size = arrayList.size();
                            for (int i5 = 0; i5 < size; i5++) {
                                o oVar = (o) arrayList.get(i5);
                                oVar.f6799a.f(rVarR, jB2, p0Var2, jVar3, gVar2);
                                rVarR.k(0.0f, oVar.f6799a.b());
                            }
                            rVarR.m();
                        }
                    } finally {
                        if (z2) {
                            rVarR.m();
                        }
                    }
                }
                return n.f5026a;
            case 10:
                a2.h hVar2 = (a2.h) this.f702f;
                a4.c cVar3 = (a4.c) this.f703g;
                e2.d0 d0Var2 = (e2.d0) ((w) this.f704h).f697d;
                e2.x xVarP = hVar2.p((List) obj);
                if (d0Var2 != null) {
                    d0Var2.a(null, xVarP);
                }
                cVar3.c(xVarP);
                return n.f5026a;
            default:
                r rVar2 = (r) obj;
                if (l.a(rVar2, (r) this.f702f)) {
                    zBooleanValue = false;
                } else {
                    if (l.a(rVar2, ((androidx.compose.ui.focus.b) this.f703g).f179f)) {
                        throw new IllegalStateException("Focus search landed at the root.");
                    }
                    zBooleanValue = ((Boolean) ((m) this.f704h).c(rVar2)).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
        }
    }
}
