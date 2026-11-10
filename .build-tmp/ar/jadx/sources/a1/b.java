package a1;

import android.os.Build;
import android.util.SparseArray;
import androidx.lifecycle.k0;
import b3.v;
import b3.w;
import b3.x;
import b4.l;
import d2.r;
import f0.d2;
import f0.g3;
import io.github.andrea_lyz.assistrestore.R;
import j.f0;
import j.m;
import j.y;
import j.z;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import k2.k;
import n1.p;
import p1.d0;
import p1.e1;
import q1.u;
import r0.o;
import v.m0;
import y0.a0;
import y0.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements f0.c, a0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f22g = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f23d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f24e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f25f;

    public b(int i5) {
        switch (i5) {
            case 5:
                this.f23d = new WeakHashMap();
                this.f24e = new WeakHashMap();
                this.f25f = new WeakHashMap();
                break;
            case 6:
                this.f24e = new b0.a(7, this);
                break;
            case 7:
            default:
                this.f25f = new k0(17);
                break;
            case 8:
                this.f23d = new AtomicReference(n0.i.f4071b);
                this.f24e = new Object();
                break;
        }
    }

    public b(r rVar, b bVar) {
        this.f23d = rVar;
        this.f24e = bVar;
        this.f25f = rVar.f1625d;
    }

    public /* synthetic */ b(Object obj, Object obj2, Object obj3) {
        this.f23d = obj;
        this.f24e = obj2;
        this.f25f = obj3;
    }

    public b(d0 d0Var) {
        this.f23d = d0Var;
        this.f24e = new ArrayList();
        this.f25f = d0Var;
    }

    public void A(y0.r rVar) {
        ((c) this.f25f).f26d.f20c = rVar;
    }

    public void B(k2.b bVar) {
        ((c) this.f25f).f26d.f18a = bVar;
    }

    public void C(k kVar) {
        ((c) this.f25f).f26d.f19b = kVar;
    }

    public void D(long j5) {
        ((c) this.f25f).f26d.f21d = j5;
    }

    public void E() {
        z zVar = (z) this.f23d;
        String str = (String) this.f24e;
        List list = (List) zVar.g(str);
        if (list != null) {
            list.remove((d2) this.f25f);
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        zVar.i(str, list);
    }

    @Override // y0.a0
    public void a(b1.b bVar) {
        synchronized (this.f24e) {
            if (!bVar.f492q) {
                bVar.f492q = true;
                bVar.b();
            }
        }
    }

    @Override // y0.a0
    public b1.b b() {
        b1.d iVar;
        b1.b bVar;
        synchronized (this.f24e) {
            try {
                u uVar = (u) this.f23d;
                int i5 = Build.VERSION.SDK_INT;
                if (i5 >= 29) {
                    y0.f.a(uVar);
                }
                if (i5 >= 29) {
                    iVar = new b1.g();
                } else if (f22g) {
                    try {
                        iVar = new b1.e((u) this.f23d, new s(), new c());
                    } catch (Throwable unused) {
                        f22g = false;
                        iVar = new b1.i(w((u) this.f23d));
                    }
                } else {
                    iVar = new b1.i(w((u) this.f23d));
                }
                bVar = new b1.b(iVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        return bVar;
    }

    @Override // f0.c
    public void c(int i5, Object obj) {
        ((d0) this.f25f).x(i5, (d0) obj);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00b0  */
    public void d(long j5, List list, boolean z2) {
        boolean z3;
        boolean z4;
        long j6;
        j1.g gVar;
        Object objB;
        y yVar;
        Object obj;
        y yVar2;
        j1.h hVar = (j1.h) this.f24e;
        j.u uVar = (j.u) this.f25f;
        int i5 = 0;
        uVar.f3155e = 0;
        long[] jArr = uVar.f3151a;
        char c5 = 7;
        if (jArr != f0.f3102a) {
            q3.k.F(jArr);
            long[] jArr2 = uVar.f3151a;
            int i6 = uVar.f3154d;
            int i7 = i6 >> 3;
            long j7 = 255 << ((i6 & 7) << 3);
            jArr2[i7] = (jArr2[i7] & (~j7)) | j7;
        }
        q3.k.E(uVar.f3153c, 0, uVar.f3154d);
        uVar.f3156f = f0.a(uVar.f3154d) - uVar.f3155e;
        int size = list.size();
        boolean z5 = true;
        j1.h hVar2 = hVar;
        int i8 = 0;
        boolean z6 = true;
        while (i8 < size) {
            o oVar = (o) list.get(i8);
            if (z6) {
                h0.e eVar = hVar2.f3256a;
                int i9 = eVar.f2578f;
                if (i9 <= 0) {
                    obj = null;
                    break;
                }
                Object[] objArr = eVar.f2576d;
                int i10 = i5;
                while (true) {
                    obj = objArr[i10];
                    if (l.a(((j1.g) obj).f3248b, oVar)) {
                        break;
                    }
                    int i11 = i10 + 1;
                    if (i11 >= i9) {
                        obj = null;
                        break;
                    }
                    i10 = i11;
                }
                gVar = (j1.g) obj;
                if (gVar != null) {
                    gVar.f3254h = true;
                    gVar.f3249c.a(j5);
                    Object objB2 = uVar.b(j5);
                    if (objB2 == null) {
                        yVar2 = objB2;
                        y yVar3 = new y();
                        uVar.d(j5, yVar3);
                        yVar2 = yVar3;
                    }
                    yVar2 = objB2;
                    yVar2.a(gVar);
                } else {
                    z6 = false;
                    gVar = new j1.g(oVar);
                    gVar.f3249c.a(j5);
                    objB = uVar.b(j5);
                    yVar = objB;
                    if (objB == null) {
                        y yVar4 = new y();
                        uVar.d(j5, yVar4);
                        yVar = yVar4;
                    }
                    yVar.a(gVar);
                    hVar2.f3256a.b(gVar);
                }
            } else {
                gVar = new j1.g(oVar);
                gVar.f3249c.a(j5);
                objB = uVar.b(j5);
                yVar = objB;
                if (objB == null) {
                    y yVar5 = new y();
                    uVar.d(j5, yVar5);
                    yVar = yVar5;
                }
                yVar.a(gVar);
                hVar2.f3256a.b(gVar);
            }
            hVar2 = gVar;
            i8++;
            i5 = 0;
        }
        if (!z2) {
            return;
        }
        long[] jArr3 = uVar.f3152b;
        Object[] objArr2 = uVar.f3153c;
        long[] jArr4 = uVar.f3151a;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i12 = 0;
        while (true) {
            long j8 = jArr4[i12];
            if ((((~j8) << c5) & j8 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i13 = 8 - ((~(i12 - length)) >>> 31);
                int i14 = 0;
                while (i14 < i13) {
                    if ((j8 & 255) < 128) {
                        int i15 = (i12 << 3) + i14;
                        boolean z7 = z5;
                        j6 = j8;
                        long j9 = jArr3[i15];
                        y yVar6 = (y) objArr2[i15];
                        h0.e eVar2 = hVar.f3256a;
                        int i16 = eVar2.f2578f;
                        if (i16 > 0) {
                            Object[] objArr3 = eVar2.f2576d;
                            int i17 = 0;
                            while (true) {
                                z4 = z7;
                                ((j1.g) objArr3[i17]).i(j9, yVar6);
                                int i18 = i17 + 1;
                                if (i18 >= i16) {
                                    break;
                                }
                                i17 = i18;
                                z7 = z4;
                            }
                        } else {
                            z4 = z7;
                        }
                    } else {
                        z4 = z5;
                        j6 = j8;
                    }
                    j8 = j6 >> 8;
                    i14++;
                    z5 = z4;
                }
                z3 = z5;
                if (i13 != 8) {
                    return;
                }
            } else {
                z3 = z5;
            }
            if (i12 == length) {
                return;
            }
            i12++;
            z5 = z3;
            c5 = 7;
        }
    }

    @Override // f0.c
    public void e(Object obj) {
        ((ArrayList) this.f24e).add(this.f25f);
        this.f25f = obj;
    }

    public void f() {
        ((ArrayList) this.f24e).clear();
        this.f25f = this.f23d;
        ((d0) this.f23d).L();
    }

    @Override // f0.c
    public /* bridge */ /* synthetic */ void h(int i5, Object obj) {
    }

    @Override // f0.c
    public void i(int i5, int i6, int i7) {
        ((d0) this.f25f).G(i5, i6, i7);
    }

    @Override // f0.c
    public Object j() {
        return this.f25f;
    }

    @Override // f0.c
    public void k(int i5, int i6) {
        ((d0) this.f25f).M(i5, i6);
    }

    public boolean l(a2.h hVar, boolean z2) {
        boolean z3;
        boolean z4;
        j1.h hVar2 = (j1.h) this.f24e;
        boolean zA = hVar2.a((m) hVar.f49e, (p) this.f23d, hVar, z2);
        h0.e eVar = hVar2.f3256a;
        if (zA) {
            int i5 = eVar.f2578f;
            if (i5 > 0) {
                Object[] objArr = eVar.f2576d;
                int i6 = 0;
                z3 = false;
                do {
                    z3 = ((j1.g) objArr[i6]).h(hVar, z2) || z3;
                    i6++;
                } while (i6 < i5);
            } else {
                z3 = false;
            }
            int i7 = eVar.f2578f;
            if (i7 > 0) {
                Object[] objArr2 = eVar.f2576d;
                int i8 = 0;
                z4 = false;
                do {
                    z4 = ((j1.g) objArr2[i8]).g(hVar) || z4;
                    i8++;
                } while (i8 < i7);
            } else {
                z4 = false;
            }
            hVar2.c(hVar);
            if (z4 || z3) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public Object n(long j5, long j6, v3.c cVar) {
        i1.a aVar;
        long j7;
        if (cVar instanceof i1.a) {
            aVar = (i1.a) cVar;
            int i5 = aVar.f2648f;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                aVar.f2648f = i5 - Integer.MIN_VALUE;
            } else {
                aVar = new i1.a(this, cVar);
            }
        } else {
            aVar = new i1.a(this, cVar);
        }
        i1.a aVar2 = aVar;
        Object objZ0 = aVar2.f2646d;
        int i6 = aVar2.f2648f;
        if (i6 == 0) {
            p3.j.O(objZ0);
            i1.e eVar = (i1.e) this.f23d;
            i1.e eVar2 = null;
            if (eVar != null && eVar.f5693p) {
                eVar2 = (i1.e) p1.f.j(eVar);
            }
            if (eVar2 != null) {
                aVar2.f2648f = 1;
                objZ0 = eVar2.z0(j5, j6, aVar2);
                u3.a aVar3 = u3.a.f5838d;
                if (objZ0 == aVar3) {
                    return aVar3;
                }
            } else {
                j7 = 0;
            }
            return new k2.o(j7);
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        p3.j.O(objZ0);
        j7 = ((k2.o) objZ0).f3424a;
        return new k2.o(j7);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public Object o(long j5, v3.c cVar) {
        i1.b bVar;
        long j6;
        if (cVar instanceof i1.b) {
            bVar = (i1.b) cVar;
            int i5 = bVar.f2651f;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                bVar.f2651f = i5 - Integer.MIN_VALUE;
            } else {
                bVar = new i1.b(this, cVar);
            }
        } else {
            bVar = new i1.b(this, cVar);
        }
        Object objB0 = bVar.f2649d;
        int i6 = bVar.f2651f;
        if (i6 == 0) {
            p3.j.O(objB0);
            i1.e eVar = (i1.e) this.f23d;
            i1.e eVar2 = null;
            if (eVar != null && eVar.f5693p) {
                eVar2 = (i1.e) p1.f.j(eVar);
            }
            if (eVar2 != null) {
                bVar.f2651f = 1;
                objB0 = eVar2.B0(j5, bVar);
                u3.a aVar = u3.a.f5838d;
                if (objB0 == aVar) {
                    return aVar;
                }
            } else {
                j6 = 0;
            }
            return new k2.o(j6);
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        p3.j.O(objB0);
        j6 = ((k2.o) objB0).f3424a;
        return new k2.o(j6);
    }

    @Override // f0.c
    public void p() {
        ArrayList arrayList = (ArrayList) this.f24e;
        this.f25f = arrayList.remove(arrayList.size() - 1);
    }

    public Object q() {
        long jB = n0.i.b();
        if (jB == n0.l.f4085a) {
            return this.f25f;
        }
        n0.k kVar = (n0.k) ((AtomicReference) this.f23d).get();
        int iA = kVar.a(jB);
        if (iA >= 0) {
            return kVar.f4084c[iA];
        }
        return null;
    }

    public y0.r r() {
        return ((c) this.f25f).f26d.f20c;
    }

    public m0 s() {
        m0 m0Var = (m0) this.f24e;
        if (m0Var != null) {
            return m0Var;
        }
        l.j("keyboardActions");
        throw null;
    }

    public long t() {
        return ((c) this.f25f).f26d.f21d;
    }

    public boolean u(CharSequence charSequence, int i5, int i6, x xVar) {
        if ((xVar.f663c & 3) == 0) {
            b3.g gVar = (b3.g) this.f25f;
            c3.a aVarB = xVar.b();
            int iA = aVarB.a(8);
            if (iA != 0) {
                ((ByteBuffer) aVarB.f1504g).getShort(iA + aVarB.f1501d);
            }
            b3.d dVar = (b3.d) gVar;
            dVar.getClass();
            ThreadLocal threadLocal = b3.d.f605b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i5 < i6) {
                sb.append(charSequence.charAt(i5));
                i5++;
            }
            boolean zHasGlyph = dVar.f606a.hasGlyph(sb.toString());
            int i7 = xVar.f663c & 4;
            xVar.f663c = zHasGlyph ? i7 | 2 : i7 | 1;
        }
        return (xVar.f663c & 3) == 2;
    }

    public boolean v() {
        if (((g3) this.f23d).getValue() != this.f25f) {
            return true;
        }
        b bVar = (b) this.f24e;
        return bVar != null && bVar.v();
    }

    public c1.a w(u uVar) {
        c1.b bVar = (c1.b) this.f25f;
        if (bVar != null) {
            return bVar;
        }
        c1.b bVar2 = new c1.b(uVar.getContext());
        bVar2.setClipChildren(false);
        bVar2.setClipToPadding(false);
        bVar2.setTag(R.id.hide_graphics_layer_in_inspector_tag, Boolean.TRUE);
        uVar.addView(bVar2, -1);
        this.f25f = bVar2;
        return bVar2;
    }

    public void x() {
        e1 e1Var = ((d0) this.f23d).f4788l;
        if (e1Var != null) {
            ((u) e1Var).t();
        }
    }

    public Object y(CharSequence charSequence, int i5, int i6, int i7, boolean z2, b3.p pVar) {
        int i8;
        char c5;
        b3.s sVar = new b3.s((v) ((w) this.f24e).f658f);
        int iCodePointAt = Character.codePointAt(charSequence, i5);
        int i9 = 0;
        boolean zJ = true;
        int iCharCount = i5;
        loop0: while (true) {
            i8 = iCharCount;
            while (true) {
                if (iCharCount < i6 && i9 < i7 && zJ) {
                    SparseArray sparseArray = ((v) sVar.f640f).f654a;
                    v vVar = sparseArray == null ? null : (v) sparseArray.get(iCodePointAt);
                    if (sVar.f636b == 2) {
                        if (vVar != null) {
                            sVar.f640f = vVar;
                            sVar.f638d++;
                        } else {
                            if (iCodePointAt == 65038) {
                                sVar.c();
                            } else if (iCodePointAt != 65039) {
                                v vVar2 = (v) sVar.f640f;
                                if (vVar2.f655b != null) {
                                    if (sVar.f638d != 1) {
                                        sVar.f641g = vVar2;
                                        sVar.c();
                                    } else if (sVar.d()) {
                                        sVar.f641g = (v) sVar.f640f;
                                        sVar.c();
                                    } else {
                                        sVar.c();
                                    }
                                    c5 = 3;
                                } else {
                                    sVar.c();
                                }
                            }
                            c5 = 1;
                        }
                        c5 = 2;
                    } else if (vVar == null) {
                        sVar.c();
                        c5 = 1;
                    } else {
                        sVar.f636b = 2;
                        sVar.f640f = vVar;
                        sVar.f638d = 1;
                        c5 = 2;
                    }
                    sVar.f637c = iCodePointAt;
                    if (c5 == 1) {
                        iCharCount = Character.charCount(Character.codePointAt(charSequence, i8)) + i8;
                        if (iCharCount >= i6) {
                            break;
                        }
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                        break;
                    }
                    if (c5 == 2) {
                        int iCharCount2 = Character.charCount(iCodePointAt) + iCharCount;
                        if (iCharCount2 < i6) {
                            iCodePointAt = Character.codePointAt(charSequence, iCharCount2);
                        }
                        iCharCount = iCharCount2;
                    } else if (c5 == 3) {
                        if (!z2 && u(charSequence, i8, iCharCount, ((v) sVar.f641g).f655b)) {
                            break;
                        }
                        zJ = pVar.j(charSequence, i8, iCharCount, ((v) sVar.f641g).f655b);
                        i9++;
                        break;
                    }
                } else {
                    break loop0;
                }
            }
        }
        if (sVar.f636b == 2 && ((v) sVar.f640f).f655b != null && ((sVar.f638d > 1 || sVar.d()) && i9 < i7 && zJ && (z2 || !u(charSequence, i8, iCharCount, ((v) sVar.f640f).f655b)))) {
            pVar.j(charSequence, i8, iCharCount, ((v) sVar.f640f).f655b);
        }
        return pVar.a();
    }

    public void z(Object obj) {
        long jB = n0.i.b();
        if (jB == n0.l.f4085a) {
            this.f25f = obj;
            return;
        }
        synchronized (this.f24e) {
            n0.k kVar = (n0.k) ((AtomicReference) this.f23d).get();
            int iA = kVar.a(jB);
            if (iA < 0) {
                ((AtomicReference) this.f23d).set(kVar.b(jB, obj));
            } else {
                kVar.f4084c[iA] = obj;
            }
        }
    }
}
