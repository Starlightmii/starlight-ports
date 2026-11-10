package b1;

import android.graphics.Outline;
import android.os.Build;
import b0.a0;
import y0.g0;
import y0.h0;
import y0.i0;
import y0.j0;
import y0.k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f476a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Outline f480e;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f484i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public j0 f485j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public k0 f486k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public y0.j f487l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f488m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public y0.h f489n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f490o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f492q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f493r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f494s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f495t;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k2.b f477b = a1.e.f32a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k2.k f478c = k2.k.f3416d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b4.m f479d = a.f473f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f481f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f482g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f483h = 9205357640488583168L;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a0 f491p = new a0();

    public b(d dVar) {
        this.f476a = dVar;
        dVar.x(false);
        this.f493r = 0L;
        this.f494s = 0L;
        this.f495t = 9205357640488583168L;
    }

    public final void a() {
        if (this.f481f) {
            d dVar = this.f476a;
            if (dVar.p() || dVar.G() > 0.0f) {
                k0 k0Var = this.f486k;
                if (k0Var != null) {
                    Outline outline = this.f480e;
                    if (outline == null) {
                        outline = new Outline();
                        this.f480e = outline;
                    }
                    int i5 = Build.VERSION.SDK_INT;
                    if (i5 > 28 || ((y0.j) k0Var).f6638a.isConvex()) {
                        if (i5 > 30) {
                            k.f557a.a(outline, k0Var);
                        } else {
                            if (!(k0Var instanceof y0.j)) {
                                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
                            }
                            outline.setConvexPath(((y0.j) k0Var).f6638a);
                        }
                        this.f488m = !outline.canClip();
                    } else {
                        Outline outline2 = this.f480e;
                        if (outline2 != null) {
                            outline2.setEmpty();
                        }
                        this.f488m = true;
                    }
                    this.f486k = k0Var;
                    outline.setAlpha(dVar.a());
                    dVar.o(outline);
                } else {
                    Outline outline3 = this.f480e;
                    if (outline3 == null) {
                        outline3 = new Outline();
                        this.f480e = outline3;
                    }
                    Outline outline4 = outline3;
                    long jO = a.a.O(this.f494s);
                    long j5 = this.f482g;
                    long j6 = this.f483h;
                    if (j6 != 9205357640488583168L) {
                        jO = j6;
                    }
                    outline4.setRoundRect(Math.round(x0.c.d(j5)), Math.round(x0.c.e(j5)), Math.round(x0.f.d(jO) + x0.c.d(j5)), Math.round(x0.f.b(jO) + x0.c.e(j5)), this.f484i);
                    outline4.setAlpha(dVar.a());
                    dVar.o(outline4);
                }
            } else {
                dVar.o(null);
            }
        }
        this.f481f = false;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x005c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x005e A[LOOP:0: B:14:0x0027->B:24:0x005e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x0061 A[EDGE_INSN: B:29:0x0061->B:25:0x0061 BREAK  A[LOOP:0: B:14:0x0027->B:24:0x005e], SYNTHETIC] */
    public final void b() {
        if (this.f492q && this.f490o == 0) {
            a0 a0Var = this.f491p;
            b bVar = (b) a0Var.f374b;
            if (bVar != null) {
                bVar.d();
                a0Var.f374b = null;
            }
            j.a0 a0Var2 = (j.a0) a0Var.f376d;
            if (a0Var2 != null) {
                Object[] objArr = a0Var2.f3067b;
                long[] jArr = a0Var2.f3066a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i5 = 0;
                    while (true) {
                        long j5 = jArr[i5];
                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i5 != length) {
                                break;
                                break;
                            }
                            i5++;
                        } else {
                            int i6 = 8 - ((~(i5 - length)) >>> 31);
                            for (int i7 = 0; i7 < i6; i7++) {
                                if ((255 & j5) < 128) {
                                    ((b) objArr[(i5 << 3) + i7]).d();
                                }
                                j5 >>= 8;
                            }
                            if (i6 != 8) {
                                break;
                            } else if (i5 != length) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                    }
                }
                a0Var2.b();
            }
            this.f476a.h();
        }
    }

    public final j0 c() {
        j0 h0Var;
        j0 j0Var = this.f485j;
        k0 k0Var = this.f486k;
        if (j0Var != null) {
            return j0Var;
        }
        if (k0Var != null) {
            g0 g0Var = new g0(k0Var);
            this.f485j = g0Var;
            return g0Var;
        }
        long jO = a.a.O(this.f494s);
        long j5 = this.f482g;
        long j6 = this.f483h;
        if (j6 != 9205357640488583168L) {
            jO = j6;
        }
        float fD = x0.c.d(j5);
        float fE = x0.c.e(j5);
        float fD2 = x0.f.d(jO) + fD;
        float fB = x0.f.b(jO) + fE;
        float f5 = this.f484i;
        if (f5 > 0.0f) {
            long jA = p3.p.a(f5, f5);
            long jA2 = p3.p.a(x0.a.b(jA), x0.a.c(jA));
            h0Var = new i0(new x0.e(fD, fE, fD2, fB, jA2, jA2, jA2, jA2));
        } else {
            h0Var = new h0(new x0.d(fD, fE, fD2, fB));
        }
        this.f485j = h0Var;
        return h0Var;
    }

    public final void d() {
        this.f490o--;
        b();
    }

    /* JADX WARN: Code duplicated, block: B:29:0x008e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x0090 A[LOOP:0: B:20:0x0059->B:30:0x0090, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x0093 A[EDGE_INSN: B:34:0x0093->B:31:0x0093 BREAK  A[LOOP:0: B:20:0x0059->B:30:0x0090], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [a4.c, b4.m] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void e() {
        a0 a0Var = this.f491p;
        a0Var.f375c = (b) a0Var.f374b;
        j.a0 a0Var2 = (j.a0) a0Var.f376d;
        if (a0Var2 != null && a0Var2.h()) {
            j.a0 a0Var3 = (j.a0) a0Var.f377e;
            if (a0Var3 == null) {
                int i5 = j.g0.f3106a;
                a0Var3 = new j.a0();
                a0Var.f377e = a0Var3;
            }
            a0Var3.i(a0Var2);
            a0Var2.b();
        }
        a0Var.f373a = true;
        this.f476a.H(this.f477b, this.f478c, this, this.f479d);
        a0Var.f373a = false;
        b bVar = (b) a0Var.f375c;
        if (bVar != null) {
            bVar.d();
        }
        j.a0 a0Var4 = (j.a0) a0Var.f377e;
        if (a0Var4 == null || !a0Var4.h()) {
            return;
        }
        Object[] objArr = a0Var4.f3067b;
        long[] jArr = a0Var4.f3066a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j5 = jArr[i6];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i6 != length) {
                        break;
                        break;
                    }
                    i6++;
                } else {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((255 & j5) < 128) {
                            ((b) objArr[(i6 << 3) + i8]).d();
                        }
                        j5 >>= 8;
                    }
                    if (i7 != 8) {
                        break;
                    } else if (i6 != length) {
                        break;
                    } else {
                        i6++;
                    }
                }
            }
        }
        a0Var4.b();
    }

    public final void f(float f5, long j5, long j6) {
        if (x0.c.b(this.f482g, j5) && x0.f.a(this.f483h, j6) && this.f484i == f5 && this.f486k == null) {
            return;
        }
        this.f485j = null;
        this.f486k = null;
        this.f481f = true;
        this.f488m = false;
        this.f482g = j5;
        this.f483h = j6;
        this.f484i = f5;
        a();
    }
}
