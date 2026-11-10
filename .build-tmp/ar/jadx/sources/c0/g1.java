package c0;

import android.graphics.Bitmap;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.compose.ui.draw.ShadowGraphicsLayerElement;
import io.github.libxposed.service.IXposedService;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f871f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g1(int i5, Object obj) {
        super(1);
        this.f870e = i5;
        this.f871f = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public g1(a4.c cVar) {
        super(1);
        this.f870e = 8;
        this.f871f = (b4.m) cVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g1(x0 x0Var) {
        super(1);
        this.f870e = 7;
        m.a1 a1Var = m.b1.f3620a;
        this.f871f = x0Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g1(e2.i iVar, a2.h hVar) {
        super(1);
        this.f870e = 4;
        this.f871f = iVar;
    }

    /* JADX WARN: Code duplicated, block: B:160:0x0408  */
    /* JADX WARN: Code duplicated, block: B:168:0x043b  */
    /* JADX WARN: Type inference failed for: r10v10, types: [a4.c, b4.m] */
    /* JADX WARN: Type inference failed for: r2v51, types: [n1.m0, p1.a] */
    @Override // a4.c
    public final Object c(Object obj) {
        String strConcat;
        char c5;
        int i5;
        y0.m mVar;
        boolean z2;
        int i6 = this.f870e;
        int i7 = 2;
        boolean z3 = true;
        int i8 = 0;
        p3.n nVar = p3.n.f5026a;
        Object obj2 = this.f871f;
        switch (i6) {
            case 0:
                ((f0.p1) ((f0.h1) obj2)).h((int) (((k2.j) obj).f3415a >> 32));
                return nVar;
            case 1:
                w1.i iVar = (w1.i) obj;
                f4.d[] dVarArr = w1.s.f6363a;
                w1.t tVar = w1.q.f6345j;
                f4.d dVar = w1.s.f6363a[3];
                tVar.a(iVar, new w1.d());
                iVar.c(w1.h.f6300s, new w1.a(null, new b0.a(3, (z2) obj2)));
                return nVar;
            case 2:
                d2.q qVar = (d2.q) obj;
                return ((d2.e) obj2).a(new d2.q(null, qVar.f1621b, qVar.f1622c, qVar.f1623d, qVar.f1624e)).f1625d;
            case 3:
                e1.v vVar = (e1.v) obj;
                e1.b bVar = (e1.b) obj2;
                bVar.g(vVar);
                a4.c cVar = bVar.f1872i;
                if (cVar != null) {
                    cVar.c(vVar);
                }
                return nVar;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                e2.i iVar2 = (e2.i) obj;
                String str = ((e2.i) obj2) == iVar2 ? " > " : "   ";
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                if (iVar2 instanceof e2.a) {
                    StringBuilder sb2 = new StringBuilder("CommitTextCommand(text.length=");
                    e2.a aVar = (e2.a) iVar2;
                    sb2.append(aVar.f1973a.f6759d.length());
                    sb2.append(", newCursorPosition=");
                    strConcat = b.b.f(sb2, aVar.f1974b, ')');
                } else if (iVar2 instanceof e2.v) {
                    StringBuilder sb3 = new StringBuilder("SetComposingTextCommand(text.length=");
                    e2.v vVar2 = (e2.v) iVar2;
                    sb3.append(vVar2.f2058a.f6759d.length());
                    sb3.append(", newCursorPosition=");
                    strConcat = b.b.f(sb3, vVar2.f2059b, ')');
                } else if ((iVar2 instanceof e2.u) || (iVar2 instanceof e2.g) || (iVar2 instanceof e2.h) || (iVar2 instanceof e2.w)) {
                    strConcat = iVar2.toString();
                } else if (iVar2 instanceof e2.k) {
                    strConcat = "FinishComposingTextCommand()";
                } else if (iVar2 instanceof e2.f) {
                    strConcat = "DeleteAllCommand()";
                } else {
                    String strB = b4.x.a(iVar2.getClass()).b();
                    if (strB == null) {
                        strB = "{anonymous EditCommand}";
                    }
                    strConcat = "Unknown EditCommand: ".concat(strB);
                }
                sb.append(strConcat);
                return sb.toString();
            case 5:
                Throwable th = (Throwable) obj;
                j1.c0 c0Var = (j1.c0) obj2;
                i4.h hVar = c0Var.f3226f;
                if (hVar != null) {
                    hVar.e(th);
                }
                c0Var.f3226f = null;
                return nVar;
            case 6:
                m.p pVar = (m.p) obj;
                float f5 = pVar.f3731b;
                if (f5 < 0.0f) {
                    f5 = 0.0f;
                }
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                float f6 = pVar.f3732c;
                if (f6 < -0.5f) {
                    f6 = -0.5f;
                }
                if (f6 > 0.5f) {
                    f6 = 0.5f;
                }
                float f7 = pVar.f3733d;
                float f8 = f7 >= -0.5f ? f7 : -0.5f;
                float f9 = f8 <= 0.5f ? f8 : 0.5f;
                float f10 = pVar.f3730a;
                float f11 = f10 < 0.0f ? 0.0f : f10;
                return new y0.u(y0.u.a(y0.l0.b(f5, f6, f9, f11 <= 1.0f ? f11 : 1.0f, z0.d.f7059t), (z0.c) obj2));
            case 7:
                m.j jVar = (m.j) obj;
                Object value = jVar.f3682e.getValue();
                m.a1 a1Var = m.b1.f3620a;
                ((x0) obj2).invoke(value, Float.valueOf(((m.m) jVar.f3683f).f3703a));
                return nVar;
            case 8:
                return ((b4.m) obj2).c(Long.valueOf(((Number) obj).longValue()));
            case 9:
                v0.c cVar2 = (v0.c) obj;
                n.p pVar2 = (n.p) obj2;
                if (cVar2.a() * pVar2.f3980t < 0.0f || x0.f.c(cVar2.f6165d.b()) <= 0.0f) {
                    return cVar2.c(n.l.f3953f);
                }
                float f12 = 2;
                float fMin = Math.min(k2.e.a(pVar2.f3980t, 0.0f) ? 1.0f : (float) Math.ceil(cVar2.a() * pVar2.f3980t), (float) Math.ceil(x0.f.c(cVar2.f6165d.b()) / f12));
                float f13 = fMin / f12;
                long jB = p3.j.b(f13, f13);
                long jF = p3.j.f(x0.f.d(cVar2.f6165d.b()) - fMin, x0.f.b(cVar2.f6165d.b()) - fMin);
                float f14 = f12 * fMin;
                boolean z4 = f14 > x0.f.c(cVar2.f6165d.b());
                y0.j0 j0VarC = pVar2.f3982v.c(cVar2.f6165d.b(), cVar2.f6165d.getLayoutDirection(), cVar2);
                if (!(j0VarC instanceof y0.g0)) {
                    if (!(j0VarC instanceof y0.i0)) {
                        boolean z5 = z4;
                        if (!(j0VarC instanceof y0.h0)) {
                            throw new i4.p();
                        }
                        y0.s0 s0Var = pVar2.f3981u;
                        if (z5) {
                            jB = 0;
                        }
                        long j5 = jB;
                        if (z5) {
                            jF = cVar2.f6165d.b();
                        }
                        return cVar2.c(new n.m(s0Var, j5, jF, z5 ? a1.i.f33a : new a1.j(fMin, 0.0f, 0, 30)));
                    }
                    y0.s0 s0Var2 = pVar2.f3981u;
                    x0.e eVar = ((y0.i0) j0VarC).f6636a;
                    if (q.f.q(eVar)) {
                        return cVar2.c(new n.o(z4, s0Var2, eVar.f6542e, f13, fMin, jB, jF, new a1.j(fMin, 0.0f, 0, 30)));
                    }
                    boolean z6 = z4;
                    if (pVar2.f3979s == null) {
                        pVar2.f3979s = new n.k();
                    }
                    n.k kVar = pVar2.f3979s;
                    b4.l.b(kVar);
                    y0.j jVar2 = kVar.f3949d;
                    y0.j jVar3 = jVar2;
                    if (jVar2 == null) {
                        y0.j jVarH = y0.l0.h();
                        kVar.f3949d = jVarH;
                        jVar3 = jVarH;
                    }
                    jVar3.d();
                    y0.k0.b(jVar3, eVar);
                    if (!z6) {
                        y0.k0 k0VarH = y0.l0.h();
                        y0.k0.b(k0VarH, new x0.e(fMin, fMin, eVar.b() - fMin, eVar.a() - fMin, h1.c.N(fMin, eVar.f6542e), h1.c.N(fMin, eVar.f6543f), h1.c.N(fMin, eVar.f6544g), h1.c.N(fMin, eVar.f6545h)));
                        jVar3.c(jVar3, k0VarH, 0);
                    }
                    return cVar2.c(new c2(jVar3, 7, s0Var2));
                }
                y0.s0 s0Var3 = pVar2.f3981u;
                y0.g0 g0Var = (y0.g0) j0VarC;
                y0.k0 k0Var = g0Var.f6628a;
                if (z4) {
                    return cVar2.c(new c2(g0Var, 6, s0Var3));
                }
                if (s0Var3 != null) {
                    long j6 = s0Var3.f6684a;
                    c5 = ' ';
                    i5 = 1;
                    mVar = new y0.m(j6, 5, Build.VERSION.SDK_INT >= 29 ? y0.n.f6649a.a(j6, 5) : new PorterDuffColorFilter(y0.l0.w(j6), y0.l0.z(5)));
                } else {
                    c5 = ' ';
                    i5 = 0;
                    mVar = null;
                }
                y0.j jVar4 = (y0.j) k0Var;
                if (jVar4.f6639b == null) {
                    jVar4.f6639b = new RectF();
                }
                RectF rectF = jVar4.f6639b;
                b4.l.b(rectF);
                jVar4.f6638a.computeBounds(rectF, true);
                float f15 = rectF.left;
                float f16 = rectF.top;
                x0.d dVar2 = new x0.d(f15, f16, rectF.right, rectF.bottom);
                if (pVar2.f3979s == null) {
                    pVar2.f3979s = new n.k();
                }
                n.k kVar2 = pVar2.f3979s;
                b4.l.b(kVar2);
                y0.j jVar5 = kVar2.f3949d;
                y0.j jVar6 = jVar5;
                if (jVar5 == null) {
                    y0.j jVarH2 = y0.l0.h();
                    kVar2.f3949d = jVarH2;
                    jVar6 = jVarH2;
                }
                jVar6.d();
                y0.k0.a(jVar6, dVar2);
                jVar6.c(jVar6, k0Var, 0);
                b4.w wVar = new b4.w();
                y0.j jVar7 = jVar6;
                long jF2 = a.a.f((int) Math.ceil(dVar2.c()), (int) Math.ceil(dVar2.b()));
                n.k kVar3 = pVar2.f3979s;
                b4.l.b(kVar3);
                y0.g gVarF = kVar3.f3946a;
                y0.d dVarA = kVar3.f3947b;
                y0.d0 d0Var = gVarF != null ? new y0.d0(gVarF.a()) : null;
                if (d0Var != null && d0Var.f6620a == 0) {
                    z2 = true;
                } else {
                    y0.d0 d0Var2 = gVarF != null ? new y0.d0(gVarF.a()) : null;
                    if (d0Var2 != null && i5 == d0Var2.f6620a) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (gVarF == null || dVarA == null) {
                    gVarF = y0.l0.f((int) (jF2 >> c5), (int) (4294967295L & jF2), i5);
                    kVar3.f3946a = gVarF;
                    dVarA = y0.l0.a(gVarF);
                    kVar3.f3947b = dVarA;
                } else {
                    float fD = x0.f.d(cVar2.f6165d.b());
                    boolean z7 = z2;
                    Bitmap bitmap = gVarF.f6627a;
                    if (fD > bitmap.getWidth() || x0.f.b(cVar2.f6165d.b()) > bitmap.getHeight() || !z7) {
                        gVarF = y0.l0.f((int) (jF2 >> c5), (int) (4294967295L & jF2), i5);
                        kVar3.f3946a = gVarF;
                        dVarA = y0.l0.a(gVarF);
                        kVar3.f3947b = dVarA;
                    }
                }
                a1.c cVar3 = kVar3.f3948c;
                if (cVar3 == null) {
                    cVar3 = new a1.c();
                    kVar3.f3948c = cVar3;
                }
                a1.b bVar2 = cVar3.f27e;
                a1.a aVar2 = cVar3.f26d;
                long jO = a.a.O(jF2);
                k2.k layoutDirection = cVar2.f6165d.getLayoutDirection();
                k2.b bVar3 = aVar2.f18a;
                a1.c cVar4 = cVar3;
                k2.k kVar4 = aVar2.f19b;
                y0.r rVar = aVar2.f20c;
                long j7 = aVar2.f21d;
                aVar2.f18a = cVar2;
                aVar2.f19b = layoutDirection;
                aVar2.f20c = dVarA;
                aVar2.f21d = jO;
                dVarA.p();
                a1.f.B(cVar4, y0.u.f6689b, jO, 58);
                float f17 = -f15;
                float f18 = -f16;
                ((a1.d) bVar2.f23d).x(f17, f18);
                try {
                    y0.d dVar3 = dVarA;
                    a1.f.f(cVar4, g0Var.f6628a, s0Var3, 0.0f, new a1.j(f14, 0.0f, 0, 30), 52);
                    float f19 = 1;
                    float fD2 = (x0.f.d(cVar4.b()) + f19) / x0.f.d(cVar4.b());
                    float fB = (x0.f.b(cVar4.b()) + f19) / x0.f.b(cVar4.b());
                    long jO2 = cVar4.O();
                    y0.g gVar = gVarF;
                    long jT = bVar2.t();
                    bVar2.r().p();
                    try {
                        ((a1.d) bVar2.f23d).v(fD2, fB, jO2);
                        a1.f.f(cVar4, jVar7, s0Var3, 0.0f, null, 28);
                        bVar2.r().m();
                        bVar2.D(jT);
                        ((a1.d) bVar2.f23d).x(-f17, -f18);
                        dVar3.m();
                        aVar2.f18a = bVar3;
                        aVar2.f19b = kVar4;
                        aVar2.f20c = rVar;
                        aVar2.f21d = j7;
                        gVar.f6627a.prepareToDraw();
                        wVar.f697d = gVar;
                        return cVar2.c(new n.n(dVar2, wVar, jF2, mVar));
                    } catch (Throwable th2) {
                        bVar2.r().m();
                        bVar2.D(jT);
                        throw th2;
                    }
                } catch (Throwable th3) {
                    ((a1.d) bVar2.f23d).x(-f17, -f18);
                    throw th3;
                }
            case 10:
                long j8 = ((x0.c) obj).f6532a;
                n.t tVar2 = (n.t) obj2;
                if (tVar2.f4021w) {
                    tVar2.f4022x.a();
                }
                return nVar;
            case 11:
                float fFloatValue = ((Number) obj).floatValue();
                n.h1 h1Var = (n.h1) obj2;
                f0.p1 p1Var = h1Var.f3920a;
                float fG = p1Var.g() + fFloatValue + h1Var.f3924e;
                float fL = d4.a.l(fG, 0.0f, h1Var.f3923d.g());
                z3 = fG == fL;
                float fG2 = fL - p1Var.g();
                int iRound = Math.round(fG2);
                p1Var.h(p1Var.g() + iRound);
                h1Var.f3924e = fG2 - iRound;
                if (!z3) {
                    fFloatValue = fG2;
                }
                return Float.valueOf(fFloatValue);
            case 12:
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                n1.w0 w0Var = (n1.w0) entry.getValue();
                n1.b0 b0Var = (n1.b0) obj2;
                int i9 = b0Var.f4102o.i(key);
                if (i9 < 0 || i9 >= b0Var.f4094g) {
                    w0Var.dispose();
                } else {
                    z3 = false;
                }
                return Boolean.valueOf(z3);
            case 13:
                ((o.j) obj2).f4340a.setValue(new o.h(((x0.c) obj).f6532a));
                return nVar;
            case 14:
                ((v.r0) obj2).a();
                return nVar;
            case 15:
                ((p.a1) obj2).G.f4478u = (n1.p) obj;
                return nVar;
            case 16:
                p.i1 i1Var = (p.i1) obj2;
                return new x0.c(p.i1.a(i1Var, i1Var.f4491h, ((x0.c) obj).f6532a, i1Var.f4490g));
            case 17:
                p1.a aVar3 = (p1.a) obj;
                p1.e0 e0Var = (p1.e0) obj2;
                if (aVar3.J()) {
                    if (aVar3.m().f4817b) {
                        aVar3.D();
                    }
                    for (Map.Entry entry2 : aVar3.m().f4824i.entrySet()) {
                        p1.e0.a(e0Var, (n1.l) entry2.getKey(), ((Number) entry2.getValue()).intValue(), aVar3.x());
                    }
                    p1.y0 y0Var = aVar3.x().f5000q;
                    b4.l.b(y0Var);
                    while (!y0Var.equals(e0Var.f4816a.x())) {
                        for (n1.l lVar : e0Var.b(y0Var).keySet()) {
                            p1.e0.a(e0Var, lVar, e0Var.c(y0Var, lVar), y0Var);
                        }
                        y0Var = y0Var.f5000q;
                        b4.l.b(y0Var);
                    }
                }
                return nVar;
            case 18:
                ((h0.e) obj2).b((r0.n) obj);
                return Boolean.TRUE;
            case 19:
                return new c.a(i7, (q1.m1) obj2);
            case 20:
                if (q1.q1.f5309b.compareAndSet(false, true)) {
                    ((k4.c) obj2).o(nVar);
                }
                return nVar;
            case 21:
                a1.f fVar = (a1.f) obj;
                y0.r rVarR = fVar.A().r();
                a4.e eVar2 = ((q1.r1) obj2).f5339g;
                if (eVar2 != null) {
                    eVar2.invoke(rVarR, (b1.b) fVar.A().f24e);
                }
                return nVar;
            case 22:
                e2.o oVar = (e2.o) obj;
                x.w wVar2 = oVar.f2046b;
                if (wVar2 != null) {
                    oVar.a(wVar2);
                    oVar.f2046b = null;
                }
                q1.t1 t1Var = (q1.t1) obj2;
                h0.e eVar3 = t1Var.f5370d;
                int i10 = eVar3.f2578f;
                if (i10 > 0) {
                    Object[] objArr = eVar3.f2576d;
                    while (!b4.l.a((WeakReference) objArr[i8], oVar)) {
                        i8++;
                        if (i8 >= i10) {
                            i8 = -1;
                        }
                    }
                } else {
                    i8 = -1;
                }
                if (i8 >= 0) {
                    eVar3.m(i8);
                }
                if (eVar3.j()) {
                    t1Var.f5368b.a();
                }
                return nVar;
            case 23:
                ((a4.e) obj2).invoke((y0.r) obj, null);
                return nVar;
            case 24:
                ((w1.i) obj).c(z.v.f7017c, new z.u(v.e0.f5895d, ((z.l) obj2).a(), 2, true));
                return nVar;
            case 25:
                float[] fArr = ((y0.f0) obj).f6626a;
                n1.p pVar3 = (n1.p) obj2;
                if (pVar3.N()) {
                    n1.v0.f(pVar3).T(pVar3, fArr);
                }
                return nVar;
            case 26:
                float fFloatValue2 = ((Number) obj).floatValue();
                v.c1 c1Var = (v.c1) obj2;
                f0.o1 o1Var = c1Var.f5869a;
                f0.o1 o1Var2 = c1Var.f5869a;
                float fG3 = o1Var.g() + fFloatValue2;
                f0.o1 o1Var3 = c1Var.f5870b;
                if (fG3 > o1Var3.g()) {
                    fFloatValue2 = o1Var3.g() - o1Var2.g();
                } else if (fG3 < 0.0f) {
                    fFloatValue2 = -o1Var2.g();
                }
                o1Var2.h(o1Var2.g() + fFloatValue2);
                return Float.valueOf(fFloatValue2);
            case 27:
                p1.f0 f0Var = (p1.f0) obj;
                ((c2) obj2).c(f0Var);
                f0Var.c();
                return nVar;
            case 28:
                y0.n0 n0Var = (y0.n0) obj;
                ShadowGraphicsLayerElement shadowGraphicsLayerElement = (ShadowGraphicsLayerElement) obj2;
                n0Var.h(n0Var.f6662p.a() * o.f.f4328d);
                n0Var.i(shadowGraphicsLayerElement.f166a);
                boolean z8 = shadowGraphicsLayerElement.f167b;
                if (n0Var.f6660n != z8) {
                    n0Var.f6650d |= 16384;
                    n0Var.f6660n = z8;
                }
                n0Var.d(shadowGraphicsLayerElement.f168c);
                n0Var.k(shadowGraphicsLayerElement.f169d);
                return nVar;
            default:
                if (((Throwable) obj) != null) {
                    ((CancellationSignal) obj2).cancel();
                }
                return nVar;
        }
    }
}
