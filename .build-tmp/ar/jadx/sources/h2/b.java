package h2;

import a4.f;
import android.graphics.Typeface;
import android.text.Spannable;
import b4.l;
import b4.m;
import c0.c2;
import c0.h1;
import d2.e;
import d2.i;
import d2.j;
import d2.k;
import d2.p;
import d2.r;
import f0.g3;
import f0.h;
import f0.i1;
import f0.i2;
import f0.o;
import f0.v;
import g2.c;
import g2.d;
import j1.z;
import n.i0;
import n.j0;
import n.l0;
import p3.n;
import y1.a0;
import z.x;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends m implements f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2618g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(Object obj, int i5, Object obj2) {
        super(3);
        this.f2616e = i5;
        this.f2617f = obj;
        this.f2618g = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x014b  */
    /* JADX WARN: Code duplicated, block: B:44:0x0155  */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        Typeface typeface;
        v vVar;
        Object objK;
        switch (this.f2616e) {
            case 0:
                a0 a0Var = (a0) obj;
                int iIntValue = ((Number) obj2).intValue();
                int iIntValue2 = ((Number) obj3).intValue();
                Spannable spannable = (Spannable) this.f2617f;
                c cVar = (c) this.f2618g;
                p pVar = a0Var.f6714f;
                k kVar = a0Var.f6711c;
                if (kVar == null) {
                    kVar = k.f1613f;
                }
                i iVar = a0Var.f6712d;
                int i5 = iVar != null ? iVar.f1610a : 0;
                j jVar = a0Var.f6713e;
                int i6 = jVar != null ? jVar.f1611a : 1;
                d dVar = cVar.f2515e;
                r rVarB = ((e) dVar.f2520e).b(pVar, kVar, i5, i6);
                if (rVarB instanceof r) {
                    Object obj4 = rVarB.f1625d;
                    l.c("null cannot be cast to non-null type android.graphics.Typeface", obj4);
                    typeface = (Typeface) obj4;
                } else {
                    a1.b bVar = new a1.b(rVarB, dVar.f2525j);
                    dVar.f2525j = bVar;
                    Object obj5 = bVar.f25f;
                    l.c("null cannot be cast to non-null type android.graphics.Typeface", obj5);
                    typeface = (Typeface) obj5;
                }
                spannable.setSpan(new b2.b(1, typeface), iIntValue, iIntValue2, 33);
                return n.f5026a;
            case 1:
                ((Number) obj3).intValue();
                v vVar2 = (v) ((f0.p) obj2);
                vVar2.V(-353972293);
                j0 j0VarA = ((i0) this.f2617f).a((q.j) this.f2618g, vVar2);
                boolean zF = vVar2.f(j0VarA);
                Object objK2 = vVar2.K();
                if (zF || objK2 == o.f2281a) {
                    objK2 = new l0(j0VarA);
                    vVar2.f0(objK2);
                }
                l0 l0Var = (l0) objK2;
                vVar2.p(false);
                return l0Var;
            case 2:
                f0.p pVar2 = (f0.p) obj2;
                if ((((Number) obj3).intValue() & 17) == 16) {
                    v vVar3 = (v) pVar2;
                    if (vVar3.z()) {
                        vVar3.Q();
                    } else {
                        vVar = (v) pVar2;
                        objK = vVar.K();
                        if (objK == o.f2281a) {
                            objK = new o.e();
                            vVar.f0(objK);
                        }
                        o.e eVar = (o.e) objK;
                        c2 c2Var = (c2) this.f2617f;
                        o.a aVar = (o.a) this.f2618g;
                        eVar.f4324a.clear();
                        c2Var.c(eVar);
                        eVar.a(aVar, vVar, 0);
                    }
                } else {
                    vVar = (v) pVar2;
                    objK = vVar.K();
                    if (objK == o.f2281a) {
                        objK = new o.e();
                        vVar.f0(objK);
                    }
                    o.e eVar2 = (o.e) objK;
                    c2 c2Var2 = (c2) this.f2617f;
                    o.a aVar2 = (o.a) this.f2618g;
                    eVar2.f4324a.clear();
                    c2Var2.c(eVar2);
                    eVar2.a(aVar2, vVar, 0);
                }
                return n.f5026a;
            case 3:
                ((Number) obj3).intValue();
                q.k kVar2 = (q.k) this.f2618g;
                v vVar4 = (v) ((f0.p) obj2);
                vVar4.V(-102778667);
                Object objK3 = vVar4.K();
                h hVar = o.f2281a;
                if (objK3 == hVar) {
                    f0.i0 i0Var = new f0.i0(f0.l.l(vVar4));
                    vVar4.f0(i0Var);
                    objK3 = i0Var;
                }
                i4.v vVar5 = ((f0.i0) objK3).f2194d;
                Object objK4 = vVar4.K();
                if (objK4 == hVar) {
                    objK4 = f0.l.s(null);
                    vVar4.f0(objK4);
                }
                i1 i1Var = (i1) objK4;
                i1 i1VarV = f0.l.v((a4.c) this.f2617f, vVar4);
                boolean zF2 = vVar4.f(kVar2);
                Object objK5 = vVar4.K();
                if (zF2 || objK5 == hVar) {
                    objK5 = new c2(i1Var, 24, kVar2);
                    vVar4.f0(objK5);
                }
                f0.l.c(kVar2, (a4.c) objK5, vVar4);
                boolean zH = vVar4.h(vVar5) | vVar4.f(kVar2) | vVar4.f(i1VarV);
                q.k kVar3 = (q.k) this.f2618g;
                Object objK6 = vVar4.K();
                if (zH || objK6 == hVar) {
                    objK6 = new i2(vVar5, i1Var, kVar3, i1VarV, null, 5);
                    vVar4.f0(objK6);
                }
                r0.p pVarA = z.a(r0.m.f5680a, kVar2, (a4.e) objK6);
                vVar4.p(false);
                return pVarA;
            default:
                ((Number) obj3).intValue();
                v vVar6 = (v) ((f0.p) obj2);
                vVar6.V(759876635);
                a4.a aVar3 = (a4.a) this.f2617f;
                Object objK7 = vVar6.K();
                h hVar2 = o.f2281a;
                if (objK7 == hVar2) {
                    objK7 = f0.l.n(aVar3);
                    vVar6.f0(objK7);
                }
                g3 g3Var = (g3) objK7;
                Object objK8 = vVar6.K();
                if (objK8 == hVar2) {
                    objK8 = new m.c(new x0.c(((x0.c) g3Var.getValue()).f6532a), x.f7023b, new x0.c(x.f7024c), 8);
                    vVar6.f0(objK8);
                }
                m.c cVar2 = (m.c) objK8;
                boolean zH2 = vVar6.h(cVar2);
                Object objK9 = vVar6.K();
                if (zH2 || objK9 == hVar2) {
                    objK9 = new b0.d(g3Var, cVar2, (t3.c) null, 13);
                    vVar6.f0(objK9);
                }
                f0.l.e((a4.e) objK9, vVar6, n.f5026a);
                m.l lVar = cVar2.f3631c;
                a4.c cVar3 = (a4.c) this.f2618g;
                boolean zF3 = vVar6.f(lVar);
                Object objK10 = vVar6.K();
                if (zF3 || objK10 == hVar2) {
                    objK10 = new h1(lVar, 3);
                    vVar6.f0(objK10);
                }
                r0.p pVar3 = (r0.p) cVar3.c((a4.a) objK10);
                vVar6.p(false);
                return pVar3;
        }
    }
}
