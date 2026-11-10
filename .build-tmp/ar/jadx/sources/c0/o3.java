package c0;

import androidx.compose.foundation.BorderModifierNodeElement;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class o3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.j0 f1134a = new f0.j0(t.f1304o);

    public static final void a(r0.p pVar, y0.q0 q0Var, long j5, long j6, float f5, float f6, n0.d dVar, f0.p pVar2, int i5, int i6) {
        if ((i6 & 1) != 0) {
            pVar = r0.m.f5680a;
        }
        if ((i6 & 2) != 0) {
            q0Var = y0.l0.f6642a;
        }
        if ((i6 & 8) != 0) {
            j6 = u.b(j5, pVar2);
        }
        if ((i6 & 16) != 0) {
            f5 = 0;
        }
        if ((i6 & 32) != 0) {
            f6 = 0;
        }
        f0.v vVar = (f0.v) pVar2;
        f0.j0 j0Var = f1134a;
        float f7 = f5 + ((k2.e) vVar.j(j0Var)).f3406d;
        f0.l.b(new f0.z1[]{b0.f762a.a(new y0.u(j6)), j0Var.a(new k2.e(f7))}, n0.i.c(-70914509, new m3(pVar, q0Var, j5, f7, null, f6, dVar), vVar), vVar, 56);
    }

    public static final r0.p b(r0.p pVar, y0.q0 q0Var, long j5, n.q qVar, float f5) {
        y0.q0 q0Var2;
        r0.p pVarB;
        r0.p borderModifierNodeElement = r0.m.f5680a;
        if (f5 > 0.0f) {
            q0Var2 = q0Var;
            pVarB = androidx.compose.ui.graphics.a.b(borderModifierNodeElement, 0.0f, 0.0f, 0.0f, f5, q0Var2, false, 124895);
        } else {
            q0Var2 = q0Var;
            pVarB = borderModifierNodeElement;
        }
        r0.p pVarE = pVar.e(pVarB);
        if (qVar != null) {
            borderModifierNodeElement = new BorderModifierNodeElement(qVar.f3994a, qVar.f3995b, q0Var2);
        }
        return q.f.j(androidx.compose.foundation.a.b(pVarE.e(borderModifierNodeElement), j5, q0Var2), q0Var2);
    }

    public static final long c(long j5, float f5, f0.p pVar) {
        f0.v vVar = (f0.v) pVar;
        s sVar = (s) vVar.j(u.f1329a);
        boolean zBooleanValue = ((Boolean) vVar.j(u.f1330b)).booleanValue();
        long j6 = sVar.f1259p;
        if (y0.u.c(j5, j6) && zBooleanValue) {
            return k2.e.a(f5, (float) 0) ? j6 : y0.l0.l(y0.u.b(((((float) Math.log(f5 + 1)) * 4.5f) + 2.0f) / 100.0f, sVar.f1263t), j6);
        }
        return j5;
    }
}
