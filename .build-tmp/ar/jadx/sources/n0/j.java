package n0;

import android.os.Trace;
import f0.n2;
import f0.o2;
import j.a0;
import j.g0;
import j.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set f4072a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q0.d f4073b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h0.e f4074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f4075d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h0.e f4076e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h0.e f4077f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h0.e f4078g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a0 f4079h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public z f4080i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f4081j;

    public j() {
        h0.e eVar = new h0.e(new o2[16]);
        this.f4074c = eVar;
        int i5 = g0.f3106a;
        this.f4075d = new a0();
        this.f4076e = eVar;
        this.f4077f = new h0.e(new Object[16]);
        this.f4078g = new h0.e(new a4.a[16]);
    }

    public final void a() {
        this.f4072a = null;
        this.f4073b = null;
        h0.e eVar = this.f4074c;
        eVar.g();
        this.f4075d.b();
        this.f4076e = eVar;
        this.f4077f.g();
        this.f4078g.g();
        this.f4079h = null;
        this.f4080i = null;
        this.f4081j = null;
    }

    public final void b() {
        Set set = this.f4072a;
        if (set == null || set.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:abandons");
        try {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                n2 n2Var = (n2) it.next();
                it.remove();
                n2Var.e();
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x009d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c() {
        /*
            r6 = this;
            java.util.Set r0 = r6.f4072a
            if (r0 != 0) goto L6
            goto La2
        L6:
            h0.e r1 = r6.f4077f
            int r2 = r1.f2578f
            if (r2 == 0) goto L64
            java.lang.String r2 = "Compose:onForgotten"
            android.os.Trace.beginSection(r2)
            j.a0 r2 = r6.f4079h     // Catch: java.lang.Throwable -> L5a
            int r3 = r1.f2578f     // Catch: java.lang.Throwable -> L5a
            int r3 = r3 + (-1)
        L17:
            r4 = -1
            if (r4 >= r3) goto L5c
            java.lang.Object[] r4 = r1.f2576d     // Catch: java.lang.Throwable -> L5a
            r4 = r4[r3]     // Catch: java.lang.Throwable -> L5a
            boolean r5 = r4 instanceof f0.o2     // Catch: java.lang.Throwable -> L2e
            if (r5 == 0) goto L30
            r5 = r4
            f0.o2 r5 = (f0.o2) r5     // Catch: java.lang.Throwable -> L2e
            f0.n2 r5 = r5.f2285a     // Catch: java.lang.Throwable -> L2e
            r0.remove(r5)     // Catch: java.lang.Throwable -> L2e
            r5.l()     // Catch: java.lang.Throwable -> L2e
            goto L30
        L2e:
            r0 = move-exception
            goto L4c
        L30:
            boolean r5 = r4 instanceof f0.m     // Catch: java.lang.Throwable -> L2e
            if (r5 == 0) goto L49
            if (r2 == 0) goto L43
            boolean r5 = r2.c(r4)     // Catch: java.lang.Throwable -> L2e
            if (r5 == 0) goto L43
            r5 = r4
            f0.m r5 = (f0.m) r5     // Catch: java.lang.Throwable -> L2e
            r5.b()     // Catch: java.lang.Throwable -> L2e
            goto L49
        L43:
            r5 = r4
            f0.m r5 = (f0.m) r5     // Catch: java.lang.Throwable -> L2e
            r5.c()     // Catch: java.lang.Throwable -> L2e
        L49:
            int r3 = r3 + (-1)
            goto L17
        L4c:
            q0.d r1 = r6.f4073b     // Catch: java.lang.Throwable -> L5a
            if (r1 == 0) goto L59
            f0.f2 r2 = new f0.f2     // Catch: java.lang.Throwable -> L5a
            r3 = 1
            r2.<init>(r1, r3, r4)     // Catch: java.lang.Throwable -> L5a
            p3.p.D(r0, r2)     // Catch: java.lang.Throwable -> L5a
        L59:
            throw r0     // Catch: java.lang.Throwable -> L5a
        L5a:
            r0 = move-exception
            goto L60
        L5c:
            android.os.Trace.endSection()
            goto L64
        L60:
            android.os.Trace.endSection()
            throw r0
        L64:
            h0.e r0 = r6.f4074c
            int r1 = r0.f2578f
            if (r1 == 0) goto La2
            java.lang.String r1 = "Compose:onRemembered"
            android.os.Trace.beginSection(r1)
            java.util.Set r1 = r6.f4072a     // Catch: java.lang.Throwable -> L9d
            if (r1 != 0) goto L74
            goto L99
        L74:
            java.lang.Object[] r2 = r0.f2576d     // Catch: java.lang.Throwable -> L9d
            int r0 = r0.f2578f     // Catch: java.lang.Throwable -> L9d
            r3 = 0
        L79:
            if (r3 >= r0) goto L99
            r4 = r2[r3]     // Catch: java.lang.Throwable -> L9d
            f0.o2 r4 = (f0.o2) r4     // Catch: java.lang.Throwable -> L9d
            f0.n2 r5 = r4.f2285a     // Catch: java.lang.Throwable -> L9d
            r1.remove(r5)     // Catch: java.lang.Throwable -> L9d
            r5.d()     // Catch: java.lang.Throwable -> L8a
            int r3 = r3 + 1
            goto L79
        L8a:
            r0 = move-exception
            q0.d r1 = r6.f4073b     // Catch: java.lang.Throwable -> L9d
            if (r1 == 0) goto L98
            f0.f2 r2 = new f0.f2     // Catch: java.lang.Throwable -> L9d
            r3 = 1
            r2.<init>(r1, r3, r4)     // Catch: java.lang.Throwable -> L9d
            p3.p.D(r0, r2)     // Catch: java.lang.Throwable -> L9d
        L98:
            throw r0     // Catch: java.lang.Throwable -> L9d
        L99:
            android.os.Trace.endSection()
            return
        L9d:
            r0 = move-exception
            android.os.Trace.endSection()
            throw r0
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.j.c():void");
    }

    public final void d(o2 o2Var) {
        a0 a0Var = this.f4075d;
        if (!a0Var.c(o2Var)) {
            this.f4077f.b(o2Var);
            return;
        }
        a0Var.j(o2Var);
        if (!this.f4076e.l(o2Var)) {
            h0.e eVar = this.f4074c;
            if (!eVar.l(o2Var)) {
                Object[] objArr = eVar.f2576d;
                int i5 = eVar.f2578f;
                for (int i6 = 0; i6 < i5; i6++) {
                    if (((o2) objArr[i6]).f2285a instanceof f) {
                        throw null;
                    }
                }
            }
        }
        Set set = this.f4072a;
        if (set == null) {
            return;
        }
        set.add(o2Var.f2285a);
    }

    public final void e(Set set, q0.d dVar) {
        a();
        this.f4072a = set;
        this.f4073b = dVar;
    }
}
