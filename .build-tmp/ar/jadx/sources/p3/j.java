package p3;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import b4.z;
import c0.c2;
import c0.t2;
import e2.f0;
import e2.x;
import f0.b0;
import f0.b2;
import f0.n2;
import f0.s2;
import f0.v;
import f0.w1;
import f0.w2;
import io.github.andrea_lyz.assistrestore.R;
import j.r;
import j.y;
import j1.c0;
import j1.u;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import n1.u0;
import n1.v0;
import p1.y0;
import q1.j1;
import q1.o2;
import q3.s;
import v.o0;
import w1.q;
import w1.t;
import y0.l0;
import y1.g0;
import z.a0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5017a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f5018b;

    public j() {
        new ConcurrentHashMap();
    }

    public static final Object A(w1.i iVar, t tVar) {
        Object obj = iVar.f6308d.get(tVar);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public static t3.c B(t3.c cVar) {
        t3.c cVarIntercepted;
        b4.l.e("<this>", cVar);
        v3.c cVar2 = cVar instanceof v3.c ? (v3.c) cVar : null;
        return (cVar2 == null || (cVarIntercepted = cVar2.intercepted()) == null) ? cVar : cVarIntercepted;
    }

    public static final boolean C(long j5) {
        long j6 = (j5 & 9187343241974906880L) ^ 9187343241974906880L;
        return (((~j6) & (j6 - 4294967297L)) & (-9223372034707292160L)) == 0;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, java.util.List] */
    public static final boolean D(j1.i iVar) {
        ?? r5 = iVar.f3257a;
        int size = r5.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (((u) r5.get(i5)).f3282i != 2) {
                return false;
            }
        }
        return true;
    }

    public static final boolean E(long j5) {
        return (j5 & 9223372034707292159L) != 9205357640488583168L;
    }

    public static final boolean F(long j5) {
        return (j5 & 9223372034707292159L) == 9205357640488583168L;
    }

    public static List G(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        b4.l.d("singletonList(...)", listSingletonList);
        return listSingletonList;
    }

    public static List H(Object... objArr) {
        if (objArr.length <= 0) {
            return s.f5477d;
        }
        List listAsList = Arrays.asList(objArr);
        b4.l.d("asList(...)", listAsList);
        return listAsList;
    }

    public static ArrayList I(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new q3.i(objArr));
    }

    public static List J(Resources resources, int i5) {
        if (i5 == 0) {
            return Collections.EMPTY_LIST;
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i5);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList();
            if (typedArrayObtainTypedArray.getType(0) == 1) {
                for (int i6 = 0; i6 < typedArrayObtainTypedArray.length(); i6++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i6, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i5);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static final Object K(p1.l lVar, x0.d dVar, v3.c cVar) {
        s.a aVar;
        Object objZ;
        if (((r0.o) lVar).f5681d.f5693p) {
            y0 y0VarR = p1.f.r(lVar);
            if (((r0.o) lVar).f5681d.f5693p) {
                s.a hVar = (s.a) p1.f.i(lVar, s.g.f5757s);
                if (hVar == null) {
                    hVar = new s.h(lVar);
                }
                aVar = hVar;
            } else {
                aVar = null;
            }
            if (aVar != null && (objZ = aVar.z(y0VarR, new t2(dVar, 8, y0VarR), cVar)) == u3.a.f5838d) {
                return objZ;
            }
        }
        return n.f5026a;
    }

    public static void N() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static final void O(Object obj) {
        if (obj instanceof h) {
            throw ((h) obj).f5016d;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [q0.j] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    public static final ArrayList P(s2 s2Var, int i5, Integer num) {
        ?? jVar = new q0.j(s2Var);
        i5 = s2Var.q(i5);
        f0.a aVarA = s2Var.a(i5);
        while (i5 >= 0) {
            jVar.c(s2Var.i(i5), s2Var.k(i5) ? s2Var.p(s2Var.f2306b, i5) : f0.o.f2281a, s2Var.f2305a.f(i5), num);
            if (i5 >= 0) {
                f0.a aVar = aVarA;
                aVarA = s2Var.a(i5);
                i5 = s2Var.q(i5);
                num = aVar;
            } else {
                num = aVarA;
            }
        }
        return jVar.f5096a;
    }

    public static final void Q(w1.n nVar, int i5, v1.h hVar) {
        h0.e eVar = new h0.e(new w1.n[16]);
        List listG = nVar.g(false, false);
        while (true) {
            eVar.d(eVar.f2578f, listG);
            while (eVar.k()) {
                w1.n nVar2 = (w1.n) eVar.m(eVar.f2578f - 1);
                y0 y0VarC = nVar2.c();
                w1.i iVar = nVar2.f6318d;
                boolean zS0 = y0VarC != null ? y0VarC.S0() : false;
                LinkedHashMap linkedHashMap = iVar.f6308d;
                if (!zS0 && !linkedHashMap.containsKey(q.f6348m) && !linkedHashMap.containsKey(q.f6344i)) {
                    y0 y0VarC2 = nVar2.c();
                    if (y0VarC2 == null) {
                        d4.a.M("Expected semantics node to have a coordinator.");
                        throw null;
                    }
                    x0.d dVarE = v0.e(y0VarC2);
                    int iRound = Math.round(dVarE.f6534a);
                    int iRound2 = Math.round(dVarE.f6535b);
                    int iRound3 = Math.round(dVarE.f6536c);
                    int iRound4 = Math.round(dVarE.f6537d);
                    k2.i iVar2 = new k2.i(iRound, iRound2, iRound3, iRound4);
                    if (iRound < iRound3 && iRound2 < iRound4) {
                        Object obj = iVar.f6308d.get(w1.h.f6286e);
                        if (obj == null) {
                            obj = null;
                        }
                        a4.e eVar2 = (a4.e) obj;
                        Object obj2 = linkedHashMap.get(q.f6351p);
                        w1.g gVar = (w1.g) (obj2 != null ? obj2 : null);
                        if (eVar2 == null || gVar == null || ((Number) gVar.f6281b.a()).floatValue() <= 0.0f) {
                            listG = nVar2.g(false, false);
                        } else {
                            int i6 = i5 + 1;
                            hVar.c(new v1.j(nVar2, i6, iVar2, y0VarC2));
                            Q(nVar2, i6, hVar);
                        }
                    }
                }
            }
            return;
        }
    }

    public static Object R(a4.e eVar, Object obj, t3.c cVar) {
        b4.l.e("<this>", eVar);
        t3.h context = cVar.getContext();
        Object dVar = context == t3.i.f5807d ? new u3.d(cVar) : new u3.e(cVar, context);
        z.c(2, eVar);
        return eVar.invoke(obj, dVar);
    }

    public static final void a(z.l lVar, r0.c cVar, n0.d dVar, f0.p pVar, int i5) {
        int i6;
        v vVar = (v) pVar;
        vVar.X(476043083);
        if ((i5 & 6) == 0) {
            i6 = ((i5 & 8) == 0 ? vVar.f(lVar) : vVar.h(lVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(cVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(dVar) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            boolean z2 = ((i6 & 14) == 4 || ((i6 & 8) != 0 && vVar.f(lVar))) | ((i6 & 112) == 32);
            Object objK = vVar.K();
            if (z2 || objK == f0.o.f2281a) {
                objK = new z.k(cVar, lVar);
                vVar.f0(objK);
            }
            n2.j.a((z.k) objK, null, new n2.t(1, false, false), dVar, vVar, ((i6 << 3) & 7168) | 384, 2);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new u0(lVar, cVar, dVar, i5, 3);
        }
    }

    public static final long b(float f5, float f6) {
        return (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
    }

    /* JADX WARN: Code duplicated, block: B:73:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d3  */
    public static final void c(z.l lVar, boolean z2, j2.h hVar, boolean z3, long j5, r0.p pVar, f0.p pVar2, int i5) {
        int i6;
        int i7;
        long j6;
        boolean z4;
        long j7;
        z.l lVar2 = lVar;
        v vVar = (v) pVar2;
        vVar.X(-843755800);
        if ((i5 & 6) == 0) {
            i6 = ((i5 & 8) == 0 ? vVar.f(lVar2) : vVar.h(lVar2) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.g(z2) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(hVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.g(z3) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.f(pVar) ? 131072 : 65536;
        }
        if ((74899 & i6) == 74898 && vVar.z()) {
            vVar.Q();
            j7 = j5;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                i7 = i6 & (-57345);
                j6 = 9205357640488583168L;
            } else {
                vVar.Q();
                i7 = i6 & (-57345);
                j6 = j5;
            }
            vVar.q();
            j2.h hVar2 = j2.h.f3319e;
            j2.h hVar3 = j2.h.f3318d;
            if (z2) {
                float f5 = z.v.f7015a;
                if ((hVar != hVar3 || z3) && !(hVar == hVar2 && z3)) {
                    z4 = false;
                } else {
                    z4 = true;
                }
            } else {
                float f6 = z.v.f7015a;
                if ((hVar == hVar3 && !z3) || (hVar == hVar2 && z3)) {
                    z4 = false;
                } else {
                    z4 = true;
                }
            }
            r0.e eVar = z4 ? r0.a.f5649b : r0.a.f5648a;
            int i8 = i7 & 14;
            boolean zG = (i8 == 4 || ((i7 & 8) != 0 && vVar.h(lVar2))) | ((i7 & 112) == 32) | vVar.g(z4);
            Object objK = vVar.K();
            if (zG || objK == f0.o.f2281a) {
                objK = new z.e(lVar2, z2, z4);
                vVar.f0(objK);
            }
            r0.p pVarA = w1.k.a(pVar, (a4.c) objK);
            long j8 = j6;
            lVar2 = lVar;
            a(lVar2, eVar, n0.i.c(280174801, new z.c((o2) vVar.j(j1.f5248q), j8, z4, pVarA, lVar), vVar), vVar, i8 | 384);
            j7 = j8;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z.d(lVar2, z2, hVar, z3, j7, pVar, i5);
        }
    }

    public static final void d(r0.p pVar, a4.a aVar, boolean z2, f0.p pVar2, int i5) {
        int i6;
        v vVar = (v) pVar2;
        vVar.X(2111672474);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(pVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if (((i6 | (vVar.h(aVar) ? 32 : 16) | (vVar.g(z2) ? 256 : 128)) & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            r.c.a(vVar, r0.a.a(androidx.compose.foundation.layout.c.i(pVar, z.v.f7015a, z.v.f7016b), new z.i(aVar, z2)));
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z.f(pVar, aVar, z2, i5);
        }
    }

    public static final void e(r0.p pVar, n0.d dVar, f0.p pVar2, int i5) {
        v vVar = (v) pVar2;
        vVar.X(-2105228848);
        if ((((vVar.f(pVar) ? 4 : 2) | i5) & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVar);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, a0.f6894a);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            dVar.invoke(vVar, 6);
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.l(i5, 13, pVar, dVar);
        }
    }

    public static final long f(float f5, float f6) {
        return (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
    }

    public static final long g(int i5, int i6) {
        if (i5 < 0) {
            throw new IllegalArgumentException(("start cannot be negative. [start: " + i5 + ", end: " + i6 + ']').toString());
        }
        if (i6 >= 0) {
            long j5 = (((long) i6) & 4294967295L) | (((long) i5) << 32);
            int i7 = g0.f6769c;
            return j5;
        }
        throw new IllegalArgumentException(("end cannot be negative. [start: " + i5 + ", end: " + i6 + ']').toString());
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0040 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x004c  */
    /* JADX WARN: Code duplicated, block: B:23:0x0059 A[LOOP:0: B:19:0x004a->B:23:0x0059, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:18:0x0041). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object h(j1.c0 r6, v3.a r7) {
        /*
            boolean r0 = r7 instanceof z.r
            if (r0 == 0) goto L13
            r0 = r7
            z.r r0 = (z.r) r0
            int r1 = r0.f7001f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7001f = r1
            goto L18
        L13:
            z.r r0 = new z.r
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f7000e
            int r1 = r0.f7001f
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            j1.c0 r6 = r0.f6999d
            O(r7)
            goto L41
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            O(r7)
        L32:
            r0.f6999d = r6
            r0.f7001f = r2
            j1.j r7 = j1.j.f3261e
            java.lang.Object r7 = r6.c(r7, r0)
            u3.a r1 = u3.a.f5838d
            if (r7 != r1) goto L41
            return r1
        L41:
            j1.i r7 = (j1.i) r7
            java.lang.Object r1 = r7.f3257a
            int r3 = r1.size()
            r4 = 0
        L4a:
            if (r4 >= r3) goto L5c
            java.lang.Object r5 = r1.get(r4)
            j1.u r5 = (j1.u) r5
            boolean r5 = j1.s.a(r5)
            if (r5 != 0) goto L59
            goto L32
        L59:
            int r4 = r4 + 1
            goto L4a
        L5c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.j.h(j1.c0, v3.a):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:65:0x0136  */
    /* JADX WARN: Code duplicated, block: B:67:0x0142  */
    /* JADX WARN: Code duplicated, block: B:69:0x014e  */
    /* JADX WARN: Code duplicated, block: B:76:0x0151 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, java.util.List] */
    public static final Object i(c0 c0Var, a1.d dVar, p0.u uVar, j1.i iVar, v3.a aVar) {
        z.s sVar;
        f0 f0Var;
        a1.d dVar2;
        a1.d dVar3;
        o0 o0Var;
        ?? r11;
        int size;
        u uVar2;
        if (aVar instanceof z.s) {
            sVar = (z.s) aVar;
            int i5 = sVar.f7005g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                sVar.f7005g = i5 - Integer.MIN_VALUE;
            } else {
                sVar = new z.s(aVar);
            }
        } else {
            sVar = new z.s(aVar);
        }
        Object objC = sVar.f7004f;
        int i6 = sVar.f7005g;
        int i7 = 0;
        boolean z2 = true;
        if (i6 == 0) {
            O(objC);
            o2 o2Var = (o2) uVar.f4756b;
            u uVar3 = (u) uVar.f4757c;
            u uVar4 = (u) iVar.f3257a.get(0);
            if (uVar3 == null || uVar4.f3275b - uVar3.f3275b >= o2Var.b()) {
                uVar.f4755a = 1;
            } else {
                int i8 = uVar3.f3282i;
                float f5 = p.z.f4654a;
                if (x0.c.c(x0.c.g(uVar3.f3276c, uVar4.f3276c)) < (i8 == 2 ? o2Var.d() * p.z.f4654a : o2Var.d())) {
                    uVar.f4755a++;
                } else {
                    uVar.f4755a = 1;
                }
            }
            uVar.f4757c = uVar4;
            u uVar5 = (u) iVar.f3257a.get(0);
            int i9 = uVar.f4755a;
            if (i9 != 1) {
                f0Var = i9 != 2 ? z.o.f6989f : z.o.f6988e;
            } else {
                f0Var = z.o.f6987d;
            }
            f0 f0Var2 = f0Var;
            long j5 = uVar5.f3276c;
            e0 e0Var = (e0) dVar.f31e;
            if (!e0Var.h() || e0Var.j().f2062a.f6759d.length() == 0 || (o0Var = e0Var.f6930d) == null || o0Var.d() == null) {
                dVar2 = dVar;
                z2 = false;
            } else {
                w0.n nVar = e0Var.f6935i;
                if (nVar != null) {
                    nVar.a(w0.h.f6239i);
                }
                e0Var.f6938l = j5;
                e0Var.f6943q = -1;
                e0Var.f(true);
                dVar2 = dVar;
                dVar2.y(e0Var.j(), e0Var.f6938l, true, f0Var2);
            }
            if (z2) {
                long j6 = uVar5.f3274a;
                c2 c2Var = new c2(dVar2, 27, f0Var2);
                sVar.f7002d = c0Var;
                sVar.f7003e = dVar2;
                sVar.f7005g = 2;
                objC = p.z.c(c0Var, j6, c2Var, sVar);
                u3.a aVar2 = u3.a.f5838d;
                if (objC == aVar2) {
                    return aVar2;
                }
                dVar3 = dVar2;
                if (((Boolean) objC).booleanValue()) {
                    r11 = c0Var.f3228h.f3236u.f3257a;
                    size = r11.size();
                    while (i7 < size) {
                        uVar2 = (u) r11.get(i7);
                        if (j1.s.b(uVar2)) {
                            uVar2.a();
                        }
                        i7++;
                    }
                }
                dVar3.getClass();
            }
        } else if (i6 == 1) {
            a1.d dVar4 = sVar.f7003e;
            c0 c0Var2 = sVar.f7002d;
            O(objC);
            if (((Boolean) objC).booleanValue()) {
                ?? r12 = c0Var2.f3228h.f3236u.f3257a;
                int size2 = r12.size();
                while (i7 < size2) {
                    u uVar6 = (u) r12.get(i7);
                    if (j1.s.b(uVar6)) {
                        uVar6.a();
                    }
                    i7++;
                }
            }
            dVar4.getClass();
        } else {
            if (i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            dVar3 = sVar.f7003e;
            c0Var = sVar.f7002d;
            O(objC);
            if (((Boolean) objC).booleanValue()) {
                r11 = c0Var.f3228h.f3236u.f3257a;
                size = r11.size();
                while (i7 < size) {
                    uVar2 = (u) r11.get(i7);
                    if (j1.s.b(uVar2)) {
                        uVar2.a();
                    }
                    i7++;
                }
            }
            dVar3.getClass();
        }
        return n.f5026a;
    }

    public static final ExtractedText j(x xVar) {
        ExtractedText extractedText = new ExtractedText();
        String str = xVar.f2062a.f6759d;
        extractedText.text = str;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = str.length();
        extractedText.partialStartOffset = -1;
        long j5 = xVar.f2063b;
        extractedText.selectionStart = g0.e(j5);
        extractedText.selectionEnd = g0.d(j5);
        extractedText.flags = !h4.g.Q(xVar.f2062a.f6759d, '\n') ? 1 : 0;
        return extractedText;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00aa, code lost:
    
        if (r14 == r5) goto L39;
     */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object k(j1.c0 r11, v.u0 r12, j1.i r13, v3.a r14) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.j.k(j1.c0, v.u0, j1.i, v3.a):java.lang.Object");
    }

    public static ArrayList l(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new q3.i(objArr));
    }

    public static r3.b m(r3.b bVar) {
        bVar.f();
        bVar.f5718f = true;
        return bVar.f5717e > 0 ? bVar : r3.b.f5715g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [q0.j] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3, types: [f0.a] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.Integer] */
    public static final List n(w2 w2Var, Integer num, int i5, Integer num2) {
        int iE;
        int iS;
        y yVar;
        if (w2Var.f2412w || w2Var.p() == 0) {
            return s.f5477d;
        }
        ?? jVar = new q0.j(w2Var);
        if (num2 != null) {
            iE = num2.intValue();
        } else {
            iE = w2Var.f2411v;
            if (iE < 0) {
                iE = w2Var.E(w2Var.f2391b, i5);
            }
        }
        if (num == 0) {
            int iN = w2Var.f2398i - w2Var.N(w2Var.f2391b, w2Var.r(i5));
            r rVar = w2Var.f2408s;
            num = Integer.valueOf(iN + ((rVar == null || (yVar = (y) rVar.d(i5)) == null) ? 0 : yVar.f3175b));
        }
        int iR = w2Var.r(i5) * 5;
        int[] iArr = w2Var.f2391b;
        if (iR < iArr.length) {
            iS = w2Var.s(i5);
        } else {
            int iE2 = iE >= 0 ? w2Var.E(iArr, iE) : iE;
            iS = w2Var.s(iE);
            int i6 = iE;
            iE = iE2;
            i5 = i6;
        }
        while (i5 >= 0) {
            jVar.c(iS, (w2Var.f2391b[(w2Var.r(i5) * 5) + 1] & 536870912) != 0 ? w2Var.t(i5) : f0.o.f2281a, w2Var.O(i5), num);
            num = w2Var.b(i5);
            if (iE >= 0) {
                int iE3 = w2Var.E(w2Var.f2391b, iE);
                iS = w2Var.s(iE);
                int i7 = iE;
                iE = iE3;
                i5 = i7;
            } else {
                i5 = iE;
            }
        }
        return jVar.f5096a;
    }

    public static final long o(long j5, int i5) {
        int i6 = g0.f6769c;
        int i7 = (int) (j5 >> 32);
        int iM = d4.a.m(i7, 0, i5);
        int i8 = (int) (4294967295L & j5);
        int iM2 = d4.a.m(i8, 0, i5);
        return (iM == i7 && iM2 == i8) ? j5 : g(iM, iM2);
    }

    public static Handler p(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return s2.a.a(looper);
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (IllegalAccessException e5) {
            e = e5;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (InstantiationException e6) {
            e = e6;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (NoSuchMethodException e7) {
            e = e7;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (InvocationTargetException e8) {
            Throwable cause = e8.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static t3.c q(a4.e eVar, t3.c cVar, t3.c cVar2) {
        b4.l.e("<this>", eVar);
        if (eVar instanceof v3.a) {
            return ((v3.a) eVar).create(cVar, cVar2);
        }
        t3.h context = cVar2.getContext();
        return context == t3.i.f5807d ? new u3.b(eVar, cVar2, cVar) : new u3.c(cVar2, context, eVar, cVar);
    }

    public static final h r(Throwable th) {
        b4.l.e("exception", th);
        return new h(th);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0029  */
    public static final y0.c0 t(v0.c cVar, float f5) {
        int iCeil = ((int) Math.ceil(f5)) * 2;
        y0.g gVarF = p.f5029a;
        y0.d dVarA = p.f5030b;
        a1.c cVar2 = p.f5031c;
        if (gVarF == null || dVarA == null) {
            gVarF = l0.f(iCeil, iCeil, 1);
            p.f5029a = gVarF;
            dVarA = l0.a(gVarF);
            p.f5030b = dVarA;
        } else {
            Bitmap bitmap = gVarF.f6627a;
            if (iCeil > bitmap.getWidth() || iCeil > bitmap.getHeight()) {
                gVarF = l0.f(iCeil, iCeil, 1);
                p.f5029a = gVarF;
                dVarA = l0.a(gVarF);
                p.f5030b = dVarA;
            }
        }
        y0.g gVar = gVarF;
        y0.d dVar = dVarA;
        if (cVar2 == null) {
            cVar2 = new a1.c();
            p.f5031c = cVar2;
        }
        a1.c cVar3 = cVar2;
        a1.a aVar = cVar3.f26d;
        k2.k layoutDirection = cVar.f6165d.getLayoutDirection();
        Bitmap bitmap2 = gVar.f6627a;
        long jF = f(bitmap2.getWidth(), bitmap2.getHeight());
        k2.b bVar = aVar.f18a;
        k2.k kVar = aVar.f19b;
        y0.r rVar = aVar.f20c;
        long j5 = aVar.f21d;
        aVar.f18a = cVar;
        aVar.f19b = layoutDirection;
        aVar.f20c = dVar;
        aVar.f21d = jF;
        dVar.p();
        a1.f.B(cVar3, y0.u.f6689b, cVar3.b(), 58);
        a1.f.B(cVar3, l0.d(4278190080L), f(f5, f5), 120);
        a1.f.C(cVar3, l0.d(4278190080L), f5, b(f5, f5), 120);
        dVar.m();
        aVar.f18a = bVar;
        aVar.f19b = kVar;
        aVar.f20c = rVar;
        aVar.f21d = j5;
        return gVar;
    }

    public static boolean u(View view, KeyEvent keyEvent) {
        ArrayList arrayList;
        int size;
        int iIndexOfKey;
        int i5 = w2.u.f6438a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = w2.t.f6434d;
        w2.t tVar = (w2.t) view.getTag(R.id.tag_unhandled_key_event_manager);
        WeakReference weakReference = null;
        if (tVar == null) {
            tVar = new w2.t();
            tVar.f6435a = null;
            tVar.f6436b = null;
            tVar.f6437c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, tVar);
        }
        WeakReference weakReference2 = tVar.f6437c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        tVar.f6437c = new WeakReference(keyEvent);
        if (tVar.f6436b == null) {
            tVar.f6436b = new SparseArray();
        }
        SparseArray sparseArray = tVar.f6436b;
        if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !view2.isAttachedToWindow() || (arrayList = (ArrayList) view2.getTag(R.id.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        arrayList.get(size).getClass();
        throw new ClassCastException();
    }

    public static final Integer v(s2 s2Var, b0 b0Var, int i5, int i6) {
        Integer numV;
        int[] iArr = s2Var.f2306b;
        while (true) {
            if (i5 >= i6) {
                return null;
            }
            int i7 = iArr[(i5 * 5) + 3] + i5;
            if (s2Var.j(i5) && s2Var.i(i5) == 206 && b4.l.a(s2Var.p(iArr, i5), f0.z.f2433e)) {
                Object objH = s2Var.h(i5, 0);
                f0.o2 o2Var = objH instanceof f0.o2 ? (f0.o2) objH : null;
                n2 n2Var = o2Var != null ? o2Var.f2285a : null;
                f0.s sVar = n2Var instanceof f0.s ? (f0.s) n2Var : null;
                if (sVar != null && sVar.f2302d.equals(b0Var)) {
                    return Integer.valueOf(i5);
                }
            }
            if (s2Var.d(i5) && (numV = v(s2Var, b0Var, i5 + 1, i7)) != null) {
                return Integer.valueOf(numV.intValue());
            }
            i5 = i7;
        }
    }

    public static final long w(long j5) {
        if (j5 != 9205357640488583168L) {
            return b(Float.intBitsToFloat((int) (j5 >> 32)) / 2.0f, Float.intBitsToFloat((int) (j5 & 4294967295L)) / 2.0f);
        }
        throw new IllegalStateException("Size is unspecified");
    }

    public static final Class x(b4.f fVar) {
        Class clsA = fVar.a();
        b4.l.c("null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>", clsA);
        return clsA;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class y(f4.b bVar) {
        b4.l.e("<this>", bVar);
        Class clsA = ((b4.e) bVar).a();
        if (clsA.isPrimitive()) {
            String name = clsA.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return clsA;
    }

    public static int z(List list) {
        b4.l.e("<this>", list);
        return list.size() - 1;
    }

    public abstract void L(boolean z2);

    public abstract void M(boolean z2);

    public abstract Typeface s(Context context, u2.d[] dVarArr);
}
