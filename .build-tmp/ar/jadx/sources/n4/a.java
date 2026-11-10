package n4;

import i4.j1;
import i4.k1;
import i4.l0;
import i4.n1;
import i4.s0;
import i4.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b3.r f4267a = new b3.r("CLOSED", 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b3.r f4268b = new b3.r("UNDEFINED", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b3.r f4269c = new b3.r("REUSABLE_CLAIMED", 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b3.r f4270d = new b3.r("NO_THREAD_ELEMENTS", 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f0.k f4271e = new f0.k(23);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f0.k f4272f = new f0.k(24);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f0.k f4273g = new f0.k(25);

    public static final void a(int i5) {
        if (i5 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i5).toString());
    }

    public static final Object b(q qVar, long j5, a4.e eVar) {
        while (true) {
            if (qVar.f4307c >= j5 && !qVar.c()) {
                return qVar;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b.f4274a;
            Object obj = atomicReferenceFieldUpdater.get(qVar);
            b3.r rVar = f4267a;
            if (obj == rVar) {
                return rVar;
            }
            q qVar2 = (q) ((b) obj);
            if (qVar2 == null) {
                qVar2 = (q) eVar.invoke(Long.valueOf(qVar.f4307c + 1), qVar);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(qVar, null, qVar2)) {
                        if (qVar.c()) {
                            qVar.d();
                        }
                    }
                } while (atomicReferenceFieldUpdater.get(qVar) == null);
            }
            qVar = qVar2;
        }
    }

    public static final q c(Object obj) {
        if (obj != f4267a) {
            return (q) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    public static final void d(Throwable th, t3.h hVar) {
        Throwable runtimeException;
        Iterator it = d.f4277a.iterator();
        while (it.hasNext()) {
            try {
                ((i4.t) it.next()).s(th, hVar);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    d4.a.f(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            d4.a.f(th, new e(hVar));
        } catch (Throwable unused) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static final boolean e(Object obj) {
        return obj == f4267a;
    }

    public static final Object f(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static final void g(t3.h hVar, Object obj) {
        if (obj == f4270d) {
            return;
        }
        if (!(obj instanceof u)) {
            Object objT = hVar.t(f4272f, null);
            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>", objT);
            b.b.l(objT);
            throw null;
        }
        u uVar = (u) obj;
        j1[] j1VarArr = uVar.f4313b;
        int length = j1VarArr.length - 1;
        if (length < 0) {
            return;
        }
        j1 j1Var = j1VarArr[length];
        b4.l.b(null);
        Object obj2 = uVar.f4312a[length];
        throw null;
    }

    public static final void h(Object obj, t3.c cVar) {
        if (!(cVar instanceof f)) {
            cVar.resumeWith(obj);
            return;
        }
        f fVar = (f) cVar;
        i4.r rVar = fVar.f4280g;
        v3.c cVar2 = fVar.f4281h;
        Throwable thA = p3.i.a(obj);
        Object oVar = thA == null ? obj : new i4.o(thA, false);
        if (rVar.h(cVar2.getContext())) {
            fVar.f4282i = oVar;
            fVar.f2723f = 1;
            rVar.e(cVar2.getContext(), fVar);
            return;
        }
        l0 l0VarA = k1.a();
        if (l0VarA.f2751f >= 4294967296L) {
            fVar.f4282i = oVar;
            fVar.f2723f = 1;
            l0VarA.o(fVar);
            return;
        }
        l0VarA.u(true);
        try {
            s0 s0Var = (s0) cVar2.getContext().j(i4.s.f2774e);
            if (s0Var == null || s0Var.a()) {
                Object obj2 = fVar.f4283j;
                t3.h context = cVar2.getContext();
                Object objL = l(context, obj2);
                n1 n1VarS = objL != f4270d ? y.s(cVar2, context, objL) : null;
                try {
                    cVar2.resumeWith(obj);
                    if (n1VarS == null || n1VarS.d0()) {
                        g(context, objL);
                    }
                } catch (Throwable th) {
                    if (n1VarS == null || n1VarS.d0()) {
                        g(context, objL);
                    }
                    throw th;
                }
            } else {
                fVar.resumeWith(p3.j.r(s0Var.m()));
            }
            while (l0VarA.w()) {
            }
        } catch (Throwable th2) {
            try {
                fVar.g(th2);
            } finally {
                l0VarA.n(true);
            }
        }
    }

    public static final long i(String str, long j5, long j6, long j7) {
        String property;
        boolean z2;
        String str2;
        Long lValueOf;
        int i5 = s.f4309a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j5;
        }
        h1.c.m(10);
        int length = property.length();
        if (length == 0) {
            str2 = property;
            lValueOf = null;
        } else {
            int i6 = 0;
            char cCharAt = property.charAt(0);
            long j8 = -9223372036854775807L;
            if (b4.l.f(cCharAt, 48) < 0) {
                z2 = true;
                if (length != 1) {
                    if (cCharAt != '+') {
                        if (cCharAt == '-') {
                            j8 = Long.MIN_VALUE;
                            i6 = 1;
                        }
                        lValueOf = null;
                    } else {
                        z2 = false;
                        i6 = 1;
                    }
                }
                str2 = property;
                lValueOf = null;
            } else {
                z2 = false;
            }
            long j9 = 0;
            long j10 = -256204778801521550L;
            while (true) {
                if (i6 >= length) {
                    str2 = property;
                    lValueOf = z2 ? Long.valueOf(j9) : Long.valueOf(-j9);
                } else {
                    int iDigit = Character.digit((int) property.charAt(i6), 10);
                    if (iDigit >= 0) {
                        if (j9 < j10) {
                            if (j10 == -256204778801521550L) {
                                str2 = property;
                                j10 = j8 / ((long) 10);
                                if (j9 < j10) {
                                }
                            }
                            lValueOf = null;
                        } else {
                            str2 = property;
                        }
                        long j11 = j9 * ((long) 10);
                        long j12 = iDigit;
                        if (j11 < j8 + j12) {
                            lValueOf = null;
                        } else {
                            j9 = j11 - j12;
                            i6++;
                            property = str2;
                        }
                    }
                    str2 = property;
                    lValueOf = null;
                }
            }
        }
        if (lValueOf == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + str2 + '\'').toString());
        }
        long jLongValue = lValueOf.longValue();
        if (j6 <= jLongValue && jLongValue <= j7) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j6 + ".." + j7 + ", but is '" + jLongValue + '\'').toString());
    }

    public static int j(int i5, int i6, String str) {
        return (int) i(str, i5, 1, (i6 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }

    public static final Object k(t3.h hVar) {
        Object objT = hVar.t(f4271e, 0);
        b4.l.b(objT);
        return objT;
    }

    public static final Object l(t3.h hVar, Object obj) {
        if (obj == null) {
            obj = k(hVar);
        }
        if (obj == 0) {
            return f4270d;
        }
        if (obj instanceof Integer) {
            return hVar.t(f4273g, new u(((Number) obj).intValue(), hVar));
        }
        b.b.l(obj);
        throw null;
    }
}
