package androidx.lifecycle;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Trace;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.github.libxposed.service.IXposedService;
import java.nio.MappedByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import q1.i2;
import q1.j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a0 implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f231e;

    public /* synthetic */ a0(int i5, Object obj) {
        this.f230d = i5;
        this.f231e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:137:0x02a7 A[LOOP:6: B:116:0x026f->B:137:0x02a7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:267:0x02a5 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.Object, p3.d] */
    /* JADX WARN: Type inference failed for: r0v44, types: [java.lang.Object, p3.d] */
    @Override // java.lang.Runnable
    public final void run() {
        Boolean bool;
        Boolean boolValueOf;
        char c5;
        long j5;
        long[] jArr;
        j.r rVar;
        int i5;
        j.r rVar2;
        int i6;
        char c6;
        char c7 = 2;
        int i7 = 0;
        switch (this.f230d) {
            case 0:
                d0 d0Var = (d0) this.f231e;
                x xVar = d0Var.f245i;
                if (d0Var.f241e == 0) {
                    d0Var.f242f = true;
                    xVar.e(o.ON_PAUSE);
                }
                if (d0Var.f240d == 0 && d0Var.f242f) {
                    xVar.e(o.ON_STOP);
                    d0Var.f243g = true;
                    return;
                }
                return;
            case 1:
                b.n nVar = (b.n) this.f231e;
                b4.l.e("this$0", nVar);
                Runnable runnable = nVar.f356e;
                if (runnable != null) {
                    runnable.run();
                    nVar.f356e = null;
                    return;
                }
                return;
            case 2:
                b0.q.setRippleState$lambda$2((b0.q) this.f231e);
                return;
            case 3:
                b3.t tVar = (b3.t) this.f231e;
                synchronized (tVar.f645g) {
                    try {
                        if (tVar.f649k == null) {
                            return;
                        }
                        try {
                            u2.d dVarB = tVar.b();
                            int i8 = dVarB.f5837f;
                            if (i8 == 2) {
                                synchronized (tVar.f645g) {
                                }
                            }
                            if (i8 != 0) {
                                throw new RuntimeException("fetchFonts result is not OK. (" + i8 + ")");
                            }
                            try {
                                int i9 = t2.d.f5802a;
                                Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                k0 k0Var = tVar.f644f;
                                Context context = tVar.f642d;
                                k0Var.getClass();
                                u2.d[] dVarArr = {dVarB};
                                p3.j jVar = r2.d.f5701a;
                                d4.a.g("TypefaceCompat.createFromFontInfo");
                                try {
                                    Typeface typefaceS = r2.d.f5701a.s(context, dVarArr);
                                    Trace.endSection();
                                    MappedByteBuffer mappedByteBufferZ = p3.p.z(tVar.f642d, dVarB.f5832a);
                                    if (mappedByteBufferZ == null || typefaceS == null) {
                                        throw new RuntimeException("Unable to open file.");
                                    }
                                    try {
                                        Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                        b3.w wVar = new b3.w(typefaceS, d4.a.E(mappedByteBufferZ));
                                        Trace.endSection();
                                        Trace.endSection();
                                        synchronized (tVar.f645g) {
                                            try {
                                                h1.c cVar = tVar.f649k;
                                                if (cVar != null) {
                                                    cVar.J(wVar);
                                                }
                                            } catch (Throwable th) {
                                                throw th;
                                            }
                                            break;
                                        }
                                        tVar.a();
                                        return;
                                    } catch (Throwable th2) {
                                        int i10 = t2.d.f5802a;
                                        Trace.endSection();
                                        throw th2;
                                    }
                                } catch (Throwable th3) {
                                    Trace.endSection();
                                    throw th3;
                                }
                            } catch (Throwable th4) {
                                int i11 = t2.d.f5802a;
                                Trace.endSection();
                                throw th4;
                            }
                            break;
                        } catch (Throwable th5) {
                            synchronized (tVar.f645g) {
                                try {
                                    h1.c cVar2 = tVar.f649k;
                                    if (cVar2 != null) {
                                        cVar2.I(th5);
                                    }
                                    tVar.a();
                                    return;
                                } catch (Throwable th6) {
                                    throw th6;
                                }
                            }
                        }
                    } catch (Throwable th7) {
                        throw th7;
                    }
                }
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                int i12 = 2;
                e2.a0 a0Var = (e2.a0) this.f231e;
                a1.b bVar = a0Var.f1976b;
                a0Var.f1988n = null;
                h0.e eVar = a0Var.f1987m;
                int i13 = eVar.f2578f;
                if (i13 > 0) {
                    Object[] objArr = eVar.f2576d;
                    int i14 = 0;
                    Boolean bool2 = null;
                    boolValueOf = null;
                    while (true) {
                        e2.z zVar = (e2.z) objArr[i14];
                        int iOrdinal = zVar.ordinal();
                        if (iOrdinal != 0) {
                            if (iOrdinal == 1) {
                                bool2 = Boolean.FALSE;
                            } else if ((iOrdinal == i12 || iOrdinal == 3) && !b4.l.a(bool2, Boolean.FALSE)) {
                                boolValueOf = Boolean.valueOf(zVar == e2.z.f2069f);
                            }
                            i14++;
                            if (i14 >= i13) {
                                bool = bool2;
                            } else {
                                i12 = 2;
                            }
                        } else {
                            bool2 = Boolean.TRUE;
                        }
                        boolValueOf = bool2;
                        i14++;
                        if (i14 >= i13) {
                            bool = bool2;
                        } else {
                            i12 = 2;
                        }
                    }
                } else {
                    bool = null;
                    boolValueOf = null;
                }
                eVar.g();
                if (b4.l.a(bool, Boolean.TRUE)) {
                    ((InputMethodManager) bVar.f24e.getValue()).restartInput((View) bVar.f23d);
                }
                if (boolValueOf != null) {
                    if (boolValueOf.booleanValue()) {
                        ((a1.d) ((a1.d) bVar.f25f).f31e).w();
                    } else {
                        ((a1.d) ((a1.d) bVar.f25f).f31e).r();
                    }
                }
                if (b4.l.a(bool, Boolean.FALSE)) {
                    ((InputMethodManager) bVar.f24e.getValue()).restartInput((View) bVar.f23d);
                    return;
                }
                return;
            case 5:
                q1.u uVar = (q1.u) this.f231e;
                uVar.f5412v0 = false;
                MotionEvent motionEvent = uVar.f5400p0;
                b4.l.b(motionEvent);
                if (motionEvent.getActionMasked() != 10) {
                    throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.");
                }
                uVar.D(motionEvent);
                return;
            case 6:
                q1.f0 f0Var = (q1.f0) this.f231e;
                Trace.beginSection("measureAndLayout");
                try {
                    f0Var.f5178d.r(true);
                    Trace.endSection();
                    Trace.beginSection("checkForSemanticsChanges");
                    try {
                        f0Var.g();
                        Trace.endSection();
                        f0Var.J = false;
                        return;
                    } catch (Throwable th8) {
                        Trace.endSection();
                        throw th8;
                    }
                } catch (Throwable th9) {
                    Trace.endSection();
                    throw th9;
                }
            case 7:
                t0.c cVar3 = (t0.c) this.f231e;
                boolean zG = cVar3.g();
                j.r rVar3 = cVar3.f5796q;
                q1.u uVar2 = cVar3.f5783d;
                if (zG) {
                    uVar2.r(true);
                    cVar3.k(uVar2.getSemanticsOwner().a(), cVar3.f5797r);
                    cVar3.i(uVar2.getSemanticsOwner().a(), cVar3.f5797r);
                    j.r rVarF = cVar3.f();
                    int[] iArr = rVarF.f3139b;
                    long[] jArr2 = rVarF.f3138a;
                    int length = jArr2.length - 2;
                    char c8 = 7;
                    int i15 = 8;
                    if (length >= 0) {
                        int i16 = 0;
                        j5 = 255;
                        while (true) {
                            long j6 = jArr2[i16];
                            char c9 = c7;
                            if ((((~j6) << c8) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i17 = 8 - ((~(i16 - length)) >>> 31);
                                int i18 = i7;
                                while (i18 < i17) {
                                    if ((j6 & 255) < 128) {
                                        c6 = c8;
                                        int i19 = iArr[(i16 << 3) + i18];
                                        i2 i2Var = (i2) rVar3.d(i19);
                                        j2 j2Var = (j2) rVarF.d(i19);
                                        w1.n nVar2 = j2Var != null ? j2Var.f5252a : null;
                                        if (nVar2 == null) {
                                            d4.a.M("no value for specified key");
                                            throw null;
                                        }
                                        i6 = i15;
                                        int i20 = nVar2.f6321g;
                                        w1.i iVar = nVar2.f6318d;
                                        i5 = i18;
                                        LinkedHashMap linkedHashMap = iVar.f6308d;
                                        if (i2Var == null) {
                                            Iterator it = iVar.iterator();
                                            while (it.hasNext()) {
                                                Object key = ((Map.Entry) it.next()).getKey();
                                                j.r rVar4 = rVarF;
                                                w1.t tVar2 = w1.q.f6355t;
                                                if (b4.l.a(key, tVar2)) {
                                                    Object obj = linkedHashMap.get(tVar2);
                                                    if (obj == null) {
                                                        obj = null;
                                                    }
                                                    List list = (List) obj;
                                                    cVar3.j(i20, String.valueOf(list != null ? (y1.f) q3.l.X(list) : null));
                                                }
                                                rVarF = rVar4;
                                            }
                                            rVar2 = rVarF;
                                        } else {
                                            rVar2 = rVarF;
                                            Iterator it2 = iVar.iterator();
                                            while (it2.hasNext()) {
                                                w1.t tVar3 = (w1.t) ((Map.Entry) it2.next()).getKey();
                                                Iterator it3 = it2;
                                                w1.t tVar4 = w1.q.f6355t;
                                                if (b4.l.a(tVar3, tVar4)) {
                                                    Object obj2 = i2Var.f5228a.f6308d.get(tVar4);
                                                    if (obj2 == null) {
                                                        obj2 = null;
                                                    }
                                                    List list2 = (List) obj2;
                                                    y1.f fVar = list2 != null ? (y1.f) q3.l.X(list2) : null;
                                                    Object obj3 = linkedHashMap.get(tVar4);
                                                    if (obj3 == null) {
                                                        obj3 = null;
                                                    }
                                                    List list3 = (List) obj3;
                                                    y1.f fVar2 = list3 != null ? (y1.f) q3.l.X(list3) : null;
                                                    if (!b4.l.a(fVar, fVar2)) {
                                                        cVar3.j(i20, String.valueOf(fVar2));
                                                    }
                                                }
                                                it2 = it3;
                                            }
                                        }
                                    } else {
                                        i5 = i18;
                                        rVar2 = rVarF;
                                        i6 = i15;
                                        c6 = c8;
                                    }
                                    j6 >>= i6;
                                    i18 = i5 + 1;
                                    i15 = i6;
                                    c8 = c6;
                                    rVarF = rVar2;
                                }
                                rVar = rVarF;
                                c5 = c8;
                                if (i17 == i15) {
                                }
                            } else {
                                rVar = rVarF;
                                c5 = c8;
                            }
                            if (i16 != length) {
                                i16++;
                                c7 = c9;
                                c8 = c5;
                                rVarF = rVar;
                                i7 = 0;
                                i15 = 8;
                            }
                        }
                    } else {
                        c5 = 7;
                        j5 = 255;
                    }
                    rVar3.a();
                    j.r rVarF2 = cVar3.f();
                    int[] iArr2 = rVarF2.f3139b;
                    Object[] objArr2 = rVarF2.f3140c;
                    long[] jArr3 = rVarF2.f3138a;
                    int length2 = jArr3.length - 2;
                    if (length2 >= 0) {
                        int i21 = 0;
                        while (true) {
                            long j7 = jArr3[i21];
                            if ((((~j7) << c5) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i22 = 8 - ((~(i21 - length2)) >>> 31);
                                int i23 = 0;
                                while (i23 < i22) {
                                    if ((j7 & j5) < 128) {
                                        int i24 = (i21 << 3) + i23;
                                        rVar3.g(iArr2[i24], new i2(((j2) objArr2[i24]).f5252a, cVar3.f()));
                                    }
                                    j7 >>= 8;
                                    i23++;
                                    jArr3 = jArr3;
                                }
                                jArr = jArr3;
                                if (i22 == 8) {
                                }
                            } else {
                                jArr = jArr3;
                            }
                            if (i21 != length2) {
                                i21++;
                                jArr3 = jArr;
                            }
                        }
                    }
                    cVar3.f5797r = new i2(uVar2.getSemanticsOwner().a(), cVar3.f());
                    cVar3.f5798s = false;
                    return;
                }
                return;
            default:
                View view = (View) this.f231e;
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                return;
        }
    }
}
