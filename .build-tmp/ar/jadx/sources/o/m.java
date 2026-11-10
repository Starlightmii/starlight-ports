package o;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.compose.foundation.layout.LayoutWeightElement;
import androidx.compose.ui.draw.ShadowGraphicsLayerElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.c2;
import f0.b2;
import f0.o;
import f0.p;
import f0.v;
import f0.w1;
import n1.u0;
import n2.s;
import n2.t;
import p1.n;
import r.q0;
import r.s0;
import r.x;
import y0.b0;
import y0.l0;
import y0.u;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4349a = new t(14);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f4350b;

    static {
        long j5 = u.f6690c;
        long j6 = u.f6689b;
        f4350b = new a(j5, j6, j6, u.b(0.38f, j6), u.b(0.38f, j6));
    }

    public static final void a(a aVar, n0.d dVar, p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(-921259293);
        int i6 = (vVar.f(aVar) ? 4 : 2) | i5;
        r0.p shadowGraphicsLayerElement = r0.m.f5680a;
        if (((i6 | (vVar.f(shadowGraphicsLayerElement) ? 32 : 16)) & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            float f5 = f.f4328d;
            u.d dVarA = u.e.a(f.f4329e);
            float f6 = 0;
            boolean z2 = Float.compare(f5, f6) > 0;
            long j5 = b0.f6615a;
            if (Float.compare(f5, f6) > 0 || z2) {
                shadowGraphicsLayerElement = new ShadowGraphicsLayerElement(dVarA, z2, j5, j5);
            }
            r0.p pVarS = h1.c.S(androidx.compose.foundation.layout.a.h(androidx.compose.foundation.layout.a.j(androidx.compose.foundation.a.b(shadowGraphicsLayerElement, aVar.f4314a, l0.f6642a)), 0.0f, f.f4333i, 1), h1.c.L(vVar));
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarS);
            p1.j.f4892c.getClass();
            n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, vVarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            dVar.b(x.f5639a, vVar, 54);
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.l(i5, 9, aVar, dVar);
        }
    }

    public static final void b(String str, boolean z2, a aVar, a4.a aVar2, p pVar, int i5) {
        int i6;
        v vVar = (v) pVar;
        vVar.X(791018367);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(str) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.g(z2) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(aVar) ? 256 : 128;
        }
        int i7 = i5 & 3072;
        r0.m mVar = r0.m.f5680a;
        if (i7 == 0) {
            i6 |= vVar.f(mVar) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.h(null) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.h(aVar2) ? 131072 : 65536;
        }
        if ((74899 & i6) == 74898 && vVar.z()) {
            vVar.Q();
        } else {
            r0.g gVar = f.f4330f;
            r.b bVar = r.j.f5571a;
            float f5 = f.f4332h;
            r.f fVar = new r.f(f5);
            boolean z3 = ((i6 & 112) == 32) | ((458752 & i6) == 131072);
            Object objK = vVar.K();
            if (z3 || objK == o.f2281a) {
                objK = new k(aVar2, z2);
                vVar.f0(objK);
            }
            r0.p pVarE = androidx.compose.foundation.a.e(mVar, z2, str, (a4.a) objK, 4).e(androidx.compose.foundation.layout.c.f113a);
            float f6 = f.f4325a;
            float f7 = f.f4326b;
            float f8 = f.f4327c;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(androidx.compose.foundation.layout.c.j(pVarE, f6, f8, f7, f8), f5, 0.0f, 2);
            s0 s0VarA = q0.a(fVar, gVar, vVar, 54);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, s0VarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            vVar.V(554568909);
            vVar.p(false);
            h0 h0Var = new h0(z2 ? aVar.f4315b : aVar.f4317d, f.f4334j, f.f4335k, f.f4337m, f.f4331g, f.f4336l, 16613240);
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero".toString());
            }
            v.l0.a(str, new LayoutWeightElement(1.0f), h0Var, 0, false, 1, 0, vVar, (i6 & 14) | 1572864, 440);
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new l(str, z2, aVar, aVar2, i5);
        }
    }

    public static final void c(s sVar, a4.a aVar, c2 c2Var, p pVar, int i5) {
        int i6;
        v vVar = (v) pVar;
        vVar.X(712057293);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(sVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(r0.m.f5680a) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.h(c2Var) ? 2048 : 1024;
        }
        if ((i6 & 1171) == 1170 && vVar.z()) {
            vVar.Q();
        } else {
            Context context = (Context) vVar.j(AndroidCompositionLocals_androidKt.f204b);
            boolean zF = vVar.f((Configuration) vVar.j(AndroidCompositionLocals_androidKt.f203a)) | vVar.f(context);
            Object objK = vVar.K();
            if (zF || objK == o.f2281a) {
                a aVar2 = f4350b;
                long jC = aVar2.f4314a;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Widget.PopupMenu, new int[]{R.attr.colorBackground});
                int iW = l0.w(jC);
                int color = typedArrayObtainStyledAttributes.getColor(0, iW);
                typedArrayObtainStyledAttributes.recycle();
                if (color != iW) {
                    jC = l0.c(color);
                }
                long j5 = jC;
                TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(R.style.TextAppearance.Widget.PopupMenu.Large, new int[]{R.attr.textColorPrimary});
                ColorStateList colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(0);
                typedArrayObtainStyledAttributes2.recycle();
                long jC2 = aVar2.f4315b;
                int iW2 = l0.w(jC2);
                Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{R.attr.state_enabled}, iW2)) : null;
                if (numValueOf != null && numValueOf.intValue() != iW2) {
                    jC2 = l0.c(numValueOf.intValue());
                }
                long j6 = jC2;
                long jC3 = aVar2.f4317d;
                int iW3 = l0.w(jC3);
                Integer numValueOf2 = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{-16842910}, iW3)) : null;
                if (numValueOf2 != null && numValueOf2.intValue() != iW3) {
                    jC3 = l0.c(numValueOf2.intValue());
                }
                long j7 = jC3;
                a aVar3 = new a(j5, j6, j6, j7, j7);
                vVar.f0(aVar3);
                objK = aVar3;
            }
            d(sVar, aVar, (a) objK, c2Var, vVar, (i6 & 1022) | ((i6 << 3) & 57344));
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new u0(sVar, aVar, c2Var, i5);
        }
    }

    public static final void d(s sVar, a4.a aVar, a aVar2, c2 c2Var, p pVar, int i5) {
        int i6;
        a4.a aVar3;
        s sVar2;
        v vVar = (v) pVar;
        vVar.X(1447189339);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(sVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(r0.m.f5680a) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.f(aVar2) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.h(c2Var) ? 16384 : 8192;
        }
        if ((i6 & 9363) == 9362 && vVar.z()) {
            vVar.Q();
            aVar3 = aVar;
            sVar2 = sVar;
        } else {
            aVar3 = aVar;
            n2.j.a(sVar, aVar3, f4349a, n0.i.c(795909757, new c0.l(aVar2, c2Var), vVar), vVar, (i6 & 14) | 3456 | (i6 & 112), 0);
            sVar2 = sVar;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.s0(sVar2, aVar3, aVar2, c2Var, i5);
        }
    }
}
