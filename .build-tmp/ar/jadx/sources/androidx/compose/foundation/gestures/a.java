package androidx.compose.foundation.gestures;

import b4.t;
import n.x0;
import p.b1;
import p.i1;
import p.l0;
import p.r0;
import p.s0;
import p.t0;
import p.u0;
import p3.j;
import q.k;
import r0.p;
import v3.c;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s0 f86a = new s0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r0 f87b = new r0();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t0 f88c = new t0();

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object a(i1 i1Var, long j5, c cVar) {
        u0 u0Var;
        t tVar;
        i1 i1Var2;
        if (cVar instanceof u0) {
            u0Var = (u0) cVar;
            int i5 = u0Var.f4600g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                u0Var.f4600g = i5 - Integer.MIN_VALUE;
            } else {
                u0Var = new u0(cVar);
            }
        } else {
            u0Var = new u0(cVar);
        }
        Object obj = u0Var.f4599f;
        int i6 = u0Var.f4600g;
        if (i6 == 0) {
            j.O(obj);
            tVar = new t();
            n.a aVar = new n.a(i1Var, j5, tVar, null, 1);
            u0Var.f4597d = i1Var;
            u0Var.f4598e = tVar;
            u0Var.f4600g = 1;
            Object objE = i1Var.e(1, aVar, u0Var);
            u3.a aVar2 = u3.a.f5838d;
            if (objE == aVar2) {
                return aVar2;
            }
            i1Var2 = i1Var;
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            t tVar2 = u0Var.f4598e;
            i1 i1Var3 = u0Var.f4597d;
            j.O(obj);
            tVar = tVar2;
            i1Var2 = i1Var3;
        }
        return new x0.c(i1Var2.g(tVar.f694d));
    }

    public static final p b(p pVar, b1 b1Var, l0 l0Var, x0 x0Var, boolean z2, boolean z3, k kVar) {
        return pVar.e(new ScrollableElement(x0Var, l0Var, b1Var, kVar, z2, z3));
    }
}
