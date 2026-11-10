package q1;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Build;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import android.view.View;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 implements n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class[] f5261a = {Serializable.class, Parcelable.class, String.class, SparseArray.class, Binder.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final x0.d f5262b = new x0.d(0.0f, 0.0f, 10.0f, 10.0f);

    public static final boolean h(w1.n nVar) {
        return !nVar.i().f6308d.containsKey(w1.q.f6344i);
    }

    public static final boolean i(w1.n nVar) {
        return nVar.f6317c.f4797u == k2.k.f3417e;
    }

    public static final boolean j(Object obj) {
        if (obj instanceof p0.n) {
            p0.n nVar = (p0.n) obj;
            if (nVar.d() == f0.h.f2186g || nVar.d() == f0.h.f2189j || nVar.d() == f0.h.f2187h) {
                Object value = nVar.getValue();
                if (value == null) {
                    return true;
                }
                return j(value);
            }
        } else {
            if ((obj instanceof p3.c) && (obj instanceof Serializable)) {
                return false;
            }
            for (int i5 = 0; i5 < 7; i5++) {
                if (f5261a[i5].isInstance(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final float k(float[] fArr, int i5, float[] fArr2, int i6) {
        int i7 = i5 * 4;
        return (fArr[i7 + 3] * fArr2[12 + i6]) + (fArr[i7 + 2] * fArr2[8 + i6]) + (fArr[i7 + 1] * fArr2[4 + i6]) + (fArr[i7] * fArr2[i6]);
    }

    public static final j.r l(w1.o oVar) {
        w1.n nVarA = oVar.a();
        j.r rVar = j.j.f3115a;
        j.r rVar2 = new j.r();
        p1.d0 d0Var = nVarA.f6317c;
        if (d0Var.E() && d0Var.D()) {
            x0.d dVarE = nVarA.e();
            m(new Region(Math.round(dVarE.f6534a), Math.round(dVarE.f6535b), Math.round(dVarE.f6536c), Math.round(dVarE.f6537d)), nVarA, rVar2, nVarA, new Region());
        }
        return rVar2;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00d3 A[EDGE_INSN: B:43:0x00d3->B:46:0x00e9 BREAK  A[LOOP:1: B:40:0x00c8->B:44:0x00d6]] */
    public static final void m(Region region, w1.n nVar, j.r rVar, w1.n nVar2, Region region2) {
        x0.d dVar;
        p1.d0 d0Var;
        Object objR;
        int i5 = nVar.f6321g;
        p1.d0 d0Var2 = nVar2.f6317c;
        int i6 = nVar2.f6321g;
        boolean z2 = (d0Var2.E() && d0Var2.D()) ? false : true;
        if (!region.isEmpty() || i6 == i5) {
            if (!z2 || nVar2.f6319e) {
                Object obj = nVar2.f6315a;
                w1.i iVar = nVar2.f6318d;
                if (iVar.f6309e && (objR = p3.p.r(d0Var2)) != null) {
                    obj = objR;
                }
                r0.o oVar = ((r0.o) obj).f5681d;
                Object obj2 = iVar.f6308d.get(w1.h.f6283b);
                if (obj2 == null) {
                    obj2 = null;
                }
                boolean z3 = obj2 != null;
                if (!oVar.f5681d.f5693p) {
                    dVar = x0.d.f6533e;
                    break;
                }
                if (z3) {
                    p1.y0 y0VarQ = p1.f.q(oVar, 8);
                    if (!y0VarQ.L0().f5693p) {
                        dVar = x0.d.f6533e;
                        break;
                    }
                    n1.p pVarF = n1.v0.f(y0VarQ);
                    x0.b bVar = y0VarQ.B;
                    if (bVar == null) {
                        bVar = new x0.b();
                        bVar.f6528a = 0.0f;
                        bVar.f6529b = 0.0f;
                        bVar.f6530c = 0.0f;
                        bVar.f6531d = 0.0f;
                        y0VarQ.B = bVar;
                    }
                    long jB0 = y0VarQ.B0(y0VarQ.K0());
                    bVar.f6528a = -x0.f.d(jB0);
                    bVar.f6529b = -x0.f.b(jB0);
                    bVar.f6530c = x0.f.d(jB0) + y0VarQ.g0();
                    bVar.f6531d = x0.f.b(jB0) + ((int) (y0VarQ.f4132f & 4294967295L));
                    while (true) {
                        if (y0VarQ == pVarF) {
                            dVar = new x0.d(bVar.f6528a, bVar.f6529b, bVar.f6530c, bVar.f6531d);
                            break;
                        }
                        y0VarQ.Z0(bVar, false, true);
                        if (bVar.b()) {
                            dVar = x0.d.f6533e;
                            break;
                        } else {
                            y0VarQ = y0VarQ.f5000q;
                            b4.l.b(y0VarQ);
                        }
                    }
                } else {
                    p1.y0 y0VarQ2 = p1.f.q(oVar, 8);
                    dVar = n1.v0.f(y0VarQ2).k(y0VarQ2, true);
                }
                int iRound = Math.round(dVar.f6534a);
                int iRound2 = Math.round(dVar.f6535b);
                int iRound3 = Math.round(dVar.f6536c);
                int iRound4 = Math.round(dVar.f6537d);
                region2.set(iRound, iRound2, iRound3, iRound4);
                if (i6 == i5) {
                    i6 = -1;
                }
                if (!region2.op(region, Region.Op.INTERSECT)) {
                    if (nVar2.f6319e) {
                        w1.n nVarJ = nVar2.j();
                        x0.d dVarE = (nVarJ == null || (d0Var = nVarJ.f6317c) == null || !d0Var.E()) ? f5262b : nVarJ.e();
                        rVar.g(i6, new j2(nVar2, new Rect(Math.round(dVarE.f6534a), Math.round(dVarE.f6535b), Math.round(dVarE.f6536c), Math.round(dVarE.f6537d))));
                        return;
                    } else {
                        if (i6 == -1) {
                            rVar.g(i6, new j2(nVar2, region2.getBounds()));
                            return;
                        }
                        return;
                    }
                }
                rVar.g(i6, new j2(nVar2, region2.getBounds()));
                List listH = w1.n.h(nVar2, 4);
                for (int size = listH.size() - 1; -1 < size; size--) {
                    m(region, nVar, rVar, (w1.n) listH.get(size), region2);
                }
                if (q(nVar2)) {
                    region.op(iRound, iRound2, iRound3, iRound4, Region.Op.DIFFERENCE);
                }
            }
        }
    }

    public static final y1.e0 n(w1.i iVar) {
        a4.c cVar;
        ArrayList arrayList = new ArrayList();
        Object obj = iVar.f6308d.get(w1.h.f6282a);
        if (obj == null) {
            obj = null;
        }
        w1.a aVar = (w1.a) obj;
        if (aVar == null || (cVar = (a4.c) aVar.f6273b) == null || !((Boolean) cVar.c(arrayList)).booleanValue()) {
            return null;
        }
        return (y1.e0) arrayList.get(0);
    }

    public static final boolean o(float[] fArr, float[] fArr2) {
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = fArr[2];
        float f8 = fArr[3];
        float f9 = fArr[4];
        float f10 = fArr[5];
        float f11 = fArr[6];
        float f12 = fArr[7];
        float f13 = fArr[8];
        float f14 = fArr[9];
        float f15 = fArr[10];
        float f16 = fArr[11];
        float f17 = fArr[12];
        float f18 = fArr[13];
        float f19 = fArr[14];
        float f20 = fArr[15];
        float f21 = (f5 * f10) - (f6 * f9);
        float f22 = (f5 * f11) - (f7 * f9);
        float f23 = (f5 * f12) - (f8 * f9);
        float f24 = (f6 * f11) - (f7 * f10);
        float f25 = (f6 * f12) - (f8 * f10);
        float f26 = (f7 * f12) - (f8 * f11);
        float f27 = (f13 * f18) - (f14 * f17);
        float f28 = (f13 * f19) - (f15 * f17);
        float f29 = (f13 * f20) - (f16 * f17);
        float f30 = (f14 * f19) - (f15 * f18);
        float f31 = (f14 * f20) - (f16 * f18);
        float f32 = (f15 * f20) - (f16 * f19);
        float f33 = (f26 * f27) + (((f24 * f29) + ((f23 * f30) + ((f21 * f32) - (f22 * f31)))) - (f25 * f28));
        if (f33 == 0.0f) {
            return false;
        }
        float f34 = 1.0f / f33;
        fArr2[0] = ((f12 * f30) + ((f10 * f32) - (f11 * f31))) * f34;
        fArr2[1] = (((f7 * f31) + ((-f6) * f32)) - (f8 * f30)) * f34;
        fArr2[2] = ((f20 * f24) + ((f18 * f26) - (f19 * f25))) * f34;
        fArr2[3] = (((f15 * f25) + ((-f14) * f26)) - (f16 * f24)) * f34;
        float f35 = -f9;
        fArr2[4] = (((f11 * f29) + (f35 * f32)) - (f12 * f28)) * f34;
        fArr2[5] = ((f8 * f28) + ((f32 * f5) - (f7 * f29))) * f34;
        float f36 = -f17;
        fArr2[6] = (((f19 * f23) + (f36 * f26)) - (f20 * f22)) * f34;
        fArr2[7] = ((f16 * f22) + ((f26 * f13) - (f15 * f23))) * f34;
        fArr2[8] = ((f12 * f27) + ((f9 * f31) - (f10 * f29))) * f34;
        fArr2[9] = (((f29 * f6) + ((-f5) * f31)) - (f8 * f27)) * f34;
        fArr2[10] = ((f20 * f21) + ((f17 * f25) - (f18 * f23))) * f34;
        fArr2[11] = (((f23 * f14) + ((-f13) * f25)) - (f16 * f21)) * f34;
        fArr2[12] = (((f10 * f28) + (f35 * f30)) - (f11 * f27)) * f34;
        fArr2[13] = ((f7 * f27) + ((f5 * f30) - (f6 * f28))) * f34;
        fArr2[14] = (((f18 * f22) + (f36 * f24)) - (f19 * f21)) * f34;
        fArr2[15] = ((f15 * f21) + ((f13 * f24) - (f14 * f22))) * f34;
        return true;
    }

    public static final boolean p(p1.d0 d0Var, p1.d0 d0Var2) {
        p1.d0 d0VarR = d0Var2.r();
        if (d0VarR == null) {
            return false;
        }
        return d0VarR.equals(d0Var) || p(d0Var, d0VarR);
    }

    public static final boolean q(w1.n nVar) {
        w1.i iVar = nVar.f6318d;
        if (iVar.f6309e) {
            return true;
        }
        Set setKeySet = iVar.f6308d.keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return false;
        }
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            if (((w1.t) it.next()).f6366c) {
                return true;
            }
        }
        return false;
    }

    public static final boolean r(y0.j0 j0Var, float f5, float f6) {
        if (j0Var instanceof y0.h0) {
            x0.d dVar = ((y0.h0) j0Var).f6633a;
            return dVar.f6534a <= f5 && f5 < dVar.f6536c && dVar.f6535b <= f6 && f6 < dVar.f6537d;
        }
        if (!(j0Var instanceof y0.i0)) {
            if (j0Var instanceof y0.g0) {
                return s(((y0.g0) j0Var).f6628a, f5, f6);
            }
            throw new i4.p();
        }
        x0.e eVar = ((y0.i0) j0Var).f6636a;
        float f7 = eVar.f6538a;
        long j5 = eVar.f6543f;
        long j6 = eVar.f6545h;
        long j7 = eVar.f6544g;
        float f8 = eVar.f6541d;
        float f9 = eVar.f6539b;
        float f10 = eVar.f6540c;
        long j8 = eVar.f6542e;
        if (f5 < f7 || f5 >= f10 || f6 < f9 || f6 >= f8) {
            return false;
        }
        if (x0.a.b(j5) + x0.a.b(j8) <= eVar.b()) {
            if (x0.a.b(j7) + x0.a.b(j6) <= eVar.b()) {
                if (x0.a.c(j6) + x0.a.c(j8) <= eVar.a()) {
                    if (x0.a.c(j7) + x0.a.c(j5) <= eVar.a()) {
                        float fB = x0.a.b(j8) + f7;
                        float fC = x0.a.c(j8) + f9;
                        float fB2 = f10 - x0.a.b(j5);
                        float fC2 = x0.a.c(j5) + f9;
                        float fB3 = f10 - x0.a.b(j7);
                        float fC3 = f8 - x0.a.c(j7);
                        float fC4 = f8 - x0.a.c(j6);
                        float fB4 = x0.a.b(j6) + f7;
                        if (f5 < fB && f6 < fC) {
                            return t(f5, f6, eVar.f6542e, fB, fC);
                        }
                        if (f5 < fB4 && f6 > fC4) {
                            return t(f5, f6, eVar.f6545h, fB4, fC4);
                        }
                        if (f5 > fB2 && f6 < fC2) {
                            return t(f5, f6, eVar.f6543f, fB2, fC2);
                        }
                        if (f5 <= fB3 || f6 <= fC3) {
                            return true;
                        }
                        return t(f5, f6, eVar.f6544g, fB3, fC3);
                    }
                }
            }
        }
        y0.j jVarH = y0.l0.h();
        y0.k0.b(jVarH, eVar);
        return s(jVarH, f5, f6);
    }

    public static final boolean s(y0.k0 k0Var, float f5, float f6) {
        x0.d dVar = new x0.d(f5 - 0.005f, f6 - 0.005f, f5 + 0.005f, f6 + 0.005f);
        y0.j jVarH = y0.l0.h();
        y0.k0.a(jVarH, dVar);
        y0.j jVarH2 = y0.l0.h();
        jVarH2.c(k0Var, jVarH, 1);
        boolean zIsEmpty = jVarH2.f6638a.isEmpty();
        jVarH2.d();
        jVarH.d();
        return !zIsEmpty;
    }

    public static final boolean t(float f5, float f6, long j5, float f7, float f8) {
        float f9 = f5 - f7;
        float f10 = f6 - f8;
        float fB = x0.a.b(j5);
        float fC = x0.a.c(j5);
        return ((f10 * f10) / (fC * fC)) + ((f9 * f9) / (fB * fB)) <= 1.0f;
    }

    public static final void u(float[] fArr, float[] fArr2) {
        float fK = k(fArr2, 0, fArr, 0);
        float fK2 = k(fArr2, 0, fArr, 1);
        float fK3 = k(fArr2, 0, fArr, 2);
        float fK4 = k(fArr2, 0, fArr, 3);
        float fK5 = k(fArr2, 1, fArr, 0);
        float fK6 = k(fArr2, 1, fArr, 1);
        float fK7 = k(fArr2, 1, fArr, 2);
        float fK8 = k(fArr2, 1, fArr, 3);
        float fK9 = k(fArr2, 2, fArr, 0);
        float fK10 = k(fArr2, 2, fArr, 1);
        float fK11 = k(fArr2, 2, fArr, 2);
        float fK12 = k(fArr2, 2, fArr, 3);
        float fK13 = k(fArr2, 3, fArr, 0);
        float fK14 = k(fArr2, 3, fArr, 1);
        float fK15 = k(fArr2, 3, fArr, 2);
        float fK16 = k(fArr2, 3, fArr, 3);
        fArr[0] = fK;
        fArr[1] = fK2;
        fArr[2] = fK3;
        fArr[3] = fK4;
        fArr[4] = fK5;
        fArr[5] = fK6;
        fArr[6] = fK7;
        fArr[7] = fK8;
        fArr[8] = fK9;
        fArr[9] = fK10;
        fArr[10] = fK11;
        fArr[11] = fK12;
        fArr[12] = fK13;
        fArr[13] = fK14;
        fArr[14] = fK15;
        fArr[15] = fK16;
    }

    public static final void v(b1 b1Var, int i5) {
        Object next;
        Iterator<T> it = b1Var.getLayoutNodeToHolder().entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((p1.d0) ((Map.Entry) next).getKey()).f4781e != i5);
        Map.Entry entry = (Map.Entry) next;
        if (entry != null && entry.getValue() != null) {
            throw new ClassCastException();
        }
    }

    public static final String w(Object obj) {
        return (obj.getClass().isAnonymousClass() ? obj.getClass().getName() : obj.getClass().getSimpleName()) + '@' + String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
    }

    public static final String x(int i5) {
        if (i5 == 0) {
            return "android.widget.Button";
        }
        if (i5 == 1) {
            return "android.widget.CheckBox";
        }
        if (i5 == 3) {
            return "android.widget.RadioButton";
        }
        if (i5 == 5) {
            return "android.widget.ImageView";
        }
        if (i5 == 6) {
            return "android.widget.Spinner";
        }
        return null;
    }

    public static void y(View view) {
        try {
            if (!q2.f5313v) {
                q2.f5313v = true;
                if (Build.VERSION.SDK_INT < 28) {
                    q2.f5311t = View.class.getDeclaredMethod("updateDisplayListIfDirty", null);
                    q2.f5312u = View.class.getDeclaredField("mRecreateDisplayList");
                } else {
                    q2.f5311t = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                    q2.f5312u = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                }
                Method method = q2.f5311t;
                if (method != null) {
                    method.setAccessible(true);
                }
                Field field = q2.f5312u;
                if (field != null) {
                    field.setAccessible(true);
                }
            }
            Field field2 = q2.f5312u;
            if (field2 != null) {
                field2.setBoolean(view, true);
            }
            Method method2 = q2.f5311t;
            if (method2 != null) {
                method2.invoke(view, null);
            }
        } catch (Throwable unused) {
            q2.f5314w = true;
        }
    }
}
