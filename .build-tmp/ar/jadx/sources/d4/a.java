package d4;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.k0;
import androidx.lifecycle.r0;
import b3.t;
import b3.u;
import b4.l;
import c0.w2;
import c3.b;
import e1.e;
import e4.d;
import f0.b2;
import f0.o;
import f0.v;
import f0.w1;
import io.github.andrea_lyz.assistrestore.R;
import j.a0;
import j.f0;
import j.z;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import k2.m;
import k2.n;
import n.h0;
import n1.i;
import p1.j;
import q.f;
import r0.h;
import r0.p;
import u2.c;
import y0.c0;
import y0.g;
import y0.s0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e f1630a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f1631b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static e f1632c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static e f1633d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static e f1634e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static e f1635f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static e f1636g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static e f1637h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static long f1638i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Method f1639j;

    public static final boolean A(long j5) {
        n[] nVarArr = m.f3420b;
        return (j5 & 1095216660480L) == 0;
    }

    public static final long B(float f5, long j5) {
        long jFloatToIntBits = j5 | (((long) Float.floatToIntBits(f5)) & 4294967295L);
        n[] nVarArr = m.f3420b;
        return jFloatToIntBits;
    }

    public static b E(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j5;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i5 = byteBufferDuplicate.getShort() & 65535;
        if (i5 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i6 = 0;
        while (true) {
            if (i6 >= i5) {
                j5 = -1;
                break;
            }
            int i7 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j5 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i7) {
                break;
            }
            i6++;
        }
        if (j5 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j5 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j6 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i8 = 0; i8 < j6; i8++) {
                int i9 = byteBufferDuplicate.getInt();
                long j7 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i9 || 1701669481 == i9) {
                    byteBufferDuplicate.position((int) (j7 + j5));
                    b bVar = new b();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    bVar.f1504g = byteBufferDuplicate;
                    bVar.f1501d = iPosition;
                    int i10 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    bVar.f1502e = i10;
                    bVar.f1503f = ((ByteBuffer) bVar.f1504g).getShort(i10);
                    return bVar;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static final boolean F(z zVar, Object obj, Object obj2) {
        Object objE = zVar.e(obj);
        if (objE == null) {
            return false;
        }
        if (!(objE instanceof a0)) {
            if (!objE.equals(obj2)) {
                return false;
            }
            zVar.g(obj);
            return true;
        }
        a0 a0Var = (a0) objE;
        boolean zJ = a0Var.j(obj2);
        if (zJ && a0Var.g()) {
            zVar.g(obj);
        }
        return zJ;
    }

    public static final void G(z zVar, Object obj) {
        boolean zG;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i5 = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        int i8 = (i5 << 3) + i7;
                        Object obj2 = zVar.f3177b[i8];
                        Object obj3 = zVar.f3178c[i8];
                        if (obj3 instanceof a0) {
                            a0 a0Var = (a0) obj3;
                            a0Var.j(obj);
                            zG = a0Var.g();
                        } else {
                            zG = obj3 == obj;
                        }
                        if (zG) {
                            zVar.h(i8);
                        }
                    }
                    j5 >>= 8;
                }
                if (i6 != 8) {
                    return;
                }
            }
            if (i5 == length) {
                return;
            } else {
                i5++;
            }
        }
    }

    public static int H(float f5) {
        if (Float.isNaN(f5)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f5);
    }

    public static e4.b I(d dVar) {
        l.e("<this>", dVar);
        return new e4.b(dVar.f2074d, dVar.f2075e, dVar.f2076f > 0 ? 2 : -2);
    }

    public static final void J(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void K(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void L(String str) {
        throw new IllegalStateException(str);
    }

    public static final void M(String str) {
        throw new IllegalStateException(str);
    }

    public static d N(int i5, int i6) {
        if (i6 > Integer.MIN_VALUE) {
            return new d(i5, i6 - 1, 1);
        }
        d dVar = d.f2081g;
        return d.f2081g;
    }

    public static final k2.d a(Context context) {
        float f5 = context.getResources().getConfiguration().fontScale;
        float f6 = context.getResources().getDisplayMetrics().density;
        l2.a aVarA = l2.b.a(f5);
        if (aVarA == null) {
            aVarA = new k2.l(f5);
        }
        return new k2.d(f6, f5, aVarA);
    }

    public static final void b(d1.b bVar, p pVar, f0.p pVar2, int i5) {
        int i6;
        h hVar = r0.b.f5657h;
        v vVar = (v) pVar2;
        vVar.X(1142754848);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(bVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(null) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(pVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.f(hVar) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.f(i.f4118a) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.c(1.0f) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i6 |= vVar.f(null) ? 1048576 : 524288;
        }
        if ((599187 & i6) == 599186 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.V(1040398089);
            vVar.p(false);
            p pVarD = androidx.compose.ui.draw.a.d(f.k(pVar.e(r0.m.f5680a)), bVar, null, 2);
            int iHashCode = Long.hashCode(vVar.S);
            p pVarC = r0.a.c(vVar, pVarD);
            w1 w1VarL = vVar.l();
            j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, h0.f3918a);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            p1.h hVar2 = p1.i.f4871f;
            if (vVar.R || !l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar2);
            }
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new w2(i5, 3, bVar, pVar);
        }
    }

    public static final void c(c0 c0Var, p pVar, v vVar, int i5) {
        boolean zF = vVar.f(c0Var);
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            Bitmap bitmap = ((g) c0Var).f6627a;
            d1.a aVar = new d1.a(c0Var, a.a.f(bitmap.getWidth(), bitmap.getHeight()));
            vVar.f0(aVar);
            objK = aVar;
        }
        b((d1.a) objK, pVar, vVar, 4194288 & i5);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0040 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x004b  */
    /* JADX WARN: Code duplicated, block: B:22:0x0053  */
    /* JADX WARN: Code duplicated, block: B:24:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0032 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:18:0x0041). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:22:0x0053
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object d(j1.c0 r7, v3.a r8) {
        /*
            boolean r0 = r8 instanceof o.b
            if (r0 == 0) goto L13
            r0 = r8
            o.b r0 = (o.b) r0
            int r1 = r0.f4321f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4321f = r1
            goto L18
        L13:
            o.b r0 = new o.b
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f4320e
            int r1 = r0.f4321f
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            j1.c0 r7 = r0.f4319d
            p3.j.O(r8)
            goto L41
        L27:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2f:
            p3.j.O(r8)
        L32:
            r0.f4319d = r7
            r0.f4321f = r2
            j1.j r8 = j1.j.f3261e
            java.lang.Object r8 = r7.c(r8, r0)
            u3.a r1 = u3.a.f5838d
            if (r8 != r1) goto L41
            return r1
        L41:
            j1.i r8 = (j1.i) r8
            int r1 = r8.f3258b
            java.lang.Object r8 = r8.f3257a
            r1 = r1 & 66
            if (r1 == 0) goto L32
            int r1 = r8.size()
            r3 = 0
            r4 = r3
        L51:
            if (r4 >= r1) goto L6a
            java.lang.Object r5 = r8.get(r4)
            j1.u r5 = (j1.u) r5
            boolean r6 = r5.b()
            if (r6 != 0) goto L32
            boolean r6 = r5.f3281h
            if (r6 != 0) goto L32
            boolean r5 = r5.f3277d
            if (r5 == 0) goto L32
            int r4 = r4 + 1
            goto L51
        L6a:
            java.lang.Object r7 = r8.get(r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.a.d(j1.c0, v3.a):java.lang.Object");
    }

    public static final void e(z zVar, Object obj, Object obj2) {
        int iD = zVar.d(obj);
        boolean z2 = iD < 0;
        Object obj3 = z2 ? null : zVar.f3178c[iD];
        if (obj3 != null) {
            if (obj3 instanceof a0) {
                ((a0) obj3).a(obj2);
            } else if (obj3 != obj2) {
                a0 a0Var = new a0();
                a0Var.a(obj3);
                a0Var.a(obj2);
                obj2 = a0Var;
            }
            obj2 = obj3;
        }
        if (!z2) {
            zVar.f3178c[iD] = obj2;
            return;
        }
        int i5 = ~iD;
        zVar.f3177b[i5] = obj;
        zVar.f3178c[i5] = obj2;
    }

    public static void f(Throwable th, Throwable th2) {
        l.e("<this>", th);
        l.e("exception", th2);
        if (th != th2) {
            Integer num = y3.a.f6891a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = x3.a.f6567a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static void g(String str) {
        if (str.length() > 127) {
            str = str.substring(0, 127);
        }
        Trace.beginSection(str);
    }

    public static double k(double d5, double d6, double d7) {
        if (d6 <= d7) {
            if (d5 < d6) {
                return d6;
            }
            return d5 > d7 ? d7 : d5;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d7 + " is less than minimum " + d6 + '.');
    }

    public static float l(float f5, float f6, float f7) {
        if (f6 <= f7) {
            if (f5 < f6) {
                return f6;
            }
            return f5 > f7 ? f7 : f5;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f7 + " is less than minimum " + f6 + '.');
    }

    public static int m(int i5, int i6, int i7) {
        if (i6 <= i7) {
            if (i5 < i6) {
                return i6;
            }
            return i5 > i7 ? i7 : i5;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i7 + " is less than minimum " + i6 + '.');
    }

    public static long n(long j5, long j6, long j7) {
        if (j6 <= j7) {
            if (j5 < j6) {
                return j6;
            }
            return j5 > j7 ? j7 : j5;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j7 + " is less than minimum " + j6 + '.');
    }

    public static z o() {
        long[] jArr = f0.f3102a;
        return new z();
    }

    public static final boolean p(x0.d dVar, float f5, float f6) {
        float f7 = dVar.f6534a;
        if (f5 > dVar.f6536c || f7 > f5) {
            return false;
        }
        return f6 <= dVar.f6537d && dVar.f6535b <= f6;
    }

    public static u q(Context context) {
        ProviderInfo providerInfo;
        c cVar;
        ApplicationInfo applicationInfo;
        k0 cVar2 = Build.VERSION.SDK_INT >= 28 ? new b3.c(6) : new k0(6);
        PackageManager packageManager = context.getPackageManager();
        p3.p.h("Package manager required to locate emoji font provider", packageManager);
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            cVar = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArrK = cVar2.k(packageManager, str2);
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArrK) {
                    arrayList.add(signature.toByteArray());
                }
                cVar = new c(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList), null, null);
            } catch (PackageManager.NameNotFoundException e5) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e5);
                cVar = null;
            }
        }
        if (cVar == null) {
            return null;
        }
        return new u(new t(context, cVar));
    }

    public static final d2.e r(Context context) {
        k0 k0Var = new k0(11);
        context.getApplicationContext();
        return new d2.e(k0Var, new d2.a(Build.VERSION.SDK_INT >= 31 ? d2.l.f1616a.a(context) : 0));
    }

    public static r0 s(Class cls) throws InvocationTargetException {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(null);
            if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                throw new RuntimeException("Cannot create an instance of " + cls);
            }
            try {
                Object objNewInstance = declaredConstructor.newInstance(null);
                l.b(objNewInstance);
                return (r0) objNewInstance;
            } catch (IllegalAccessException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            } catch (InstantiationException e6) {
                throw new RuntimeException("Cannot create an instance of " + cls, e6);
            }
        } catch (NoSuchMethodException e7) {
            throw new RuntimeException("Cannot create an instance of " + cls, e7);
        }
    }

    public static final j3.g t(View view) {
        l.e("<this>", view);
        while (view != null) {
            Object tag = view.getTag(R.id.view_tree_saved_state_registry_owner);
            j3.g gVar = tag instanceof j3.g ? (j3.g) tag : null;
            if (gVar != null) {
                return gVar;
            }
            Object objN = f.n(view);
            view = objN instanceof View ? (View) objN : null;
        }
        return null;
    }

    public static final e u() {
        e eVar = f1633d;
        if (eVar != null) {
            return eVar;
        }
        e1.d dVar = new e1.d("Rounded.DataObject");
        int i5 = e1.a0.f1864a;
        long j5 = y0.u.f6689b;
        s0 s0Var = new s0(j5);
        e1.f fVar = new e1.f();
        fVar.h(4.0f, 7.0f);
        fVar.l(2.0f);
        fVar.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        fVar.e(0.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(2.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.e(0.0f);
        fVar.c(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        fVar.l(2.0f);
        fVar.c(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        fVar.e(2.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(0.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.d(7.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        fVar.l(-2.0f);
        fVar.c(0.0f, -1.3f, -0.84f, -2.42f, -2.0f, -2.83f);
        fVar.l(-0.34f);
        fVar.b(5.16f, 11.42f, 6.0f, 10.3f, 6.0f, 9.0f);
        fVar.k(7.0f);
        fVar.c(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        fVar.e(2.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(0.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.d(7.0f);
        fVar.b(5.34f, 4.0f, 4.0f, 5.34f, 4.0f, 7.0f);
        fVar.a();
        e1.d.a(dVar, fVar.f1910d, s0Var);
        s0 s0Var2 = new s0(j5);
        e1.f fVar2 = new e1.f();
        fVar2.h(21.0f, 10.0f);
        fVar2.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        fVar2.k(7.0f);
        fVar2.c(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        fVar2.e(-2.0f);
        fVar2.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar2.l(0.0f);
        fVar2.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar2.e(2.0f);
        fVar2.c(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        fVar2.l(2.0f);
        fVar2.c(0.0f, 1.3f, 0.84f, 2.42f, 2.0f, 2.83f);
        fVar2.l(0.34f);
        fVar2.c(-1.16f, 0.41f, -2.0f, 1.52f, -2.0f, 2.83f);
        fVar2.l(2.0f);
        fVar2.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        fVar2.e(-2.0f);
        fVar2.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar2.l(0.0f);
        fVar2.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar2.e(2.0f);
        fVar2.c(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        fVar2.l(-2.0f);
        fVar2.c(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        fVar2.e(0.0f);
        fVar2.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar2.l(-2.0f);
        fVar2.b(22.0f, 10.45f, 21.55f, 10.0f, 21.0f, 10.0f);
        fVar2.f(21.0f, 10.0f);
        fVar2.a();
        e1.d.a(dVar, fVar2.f1910d, s0Var2);
        e eVarB = dVar.b();
        f1633d = eVarB;
        return eVarB;
    }

    public static final long v(double d5) {
        return B((float) d5, 4294967296L);
    }

    public static final long w(int i5) {
        return B(i5, 4294967296L);
    }

    public static final int x(int i5, int i6) {
        return (i5 >> i6) & 31;
    }

    public static boolean y() {
        if (Build.VERSION.SDK_INT >= 29) {
            return m3.a.a();
        }
        try {
            if (f1639j == null) {
                f1638i = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f1639j = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f1639j.invoke(null, Long.valueOf(f1638i))).booleanValue();
        } catch (Exception e5) {
            if (!(e5 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e5);
                return false;
            }
            Throwable cause = e5.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static boolean z(int i5) {
        int type = Character.getType(i5);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    public abstract void C(o2.f fVar, o2.f fVar2);

    public abstract void D(o2.f fVar, Thread thread);

    public abstract boolean h(o2.g gVar, o2.c cVar);

    public abstract boolean i(o2.g gVar, Object obj, Object obj2);

    public abstract boolean j(o2.g gVar, o2.f fVar, o2.f fVar2);
}
