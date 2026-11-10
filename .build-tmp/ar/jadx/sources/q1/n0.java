package q1;

import android.os.Looper;
import android.view.Choreographer;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.l3;
import io.github.libxposed.service.IXposedService;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends b4.m implements a4.a {
    public static final n0 A;
    public static final n0 B;
    public static final n0 C;
    public static final n0 D;
    public static final n0 E;
    public static final n0 F;
    public static final n0 G;
    public static final n0 H;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n0 f5270f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n0 f5271g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final n0 f5272h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final n0 f5273i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final n0 f5274j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final n0 f5275k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final n0 f5276l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final n0 f5277m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final n0 f5278n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final n0 f5279o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final n0 f5280p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final n0 f5281q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final n0 f5282r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final n0 f5283s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final n0 f5284t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final n0 f5285u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final n0 f5286v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final n0 f5287w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final n0 f5288x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final n0 f5289y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final n0 f5290z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5291e;

    static {
        int i5 = 0;
        f5270f = new n0(i5, 0);
        f5271g = new n0(i5, 1);
        f5272h = new n0(i5, 2);
        f5273i = new n0(i5, 3);
        f5274j = new n0(i5, 4);
        f5275k = new n0(i5, 5);
        f5276l = new n0(i5, 6);
        f5277m = new n0(i5, 7);
        f5278n = new n0(i5, 8);
        f5279o = new n0(i5, 9);
        f5280p = new n0(i5, 10);
        f5281q = new n0(i5, 11);
        f5282r = new n0(i5, 12);
        f5283s = new n0(i5, 13);
        f5284t = new n0(i5, 14);
        f5285u = new n0(i5, 15);
        f5286v = new n0(i5, 16);
        f5287w = new n0(i5, 17);
        f5288x = new n0(i5, 18);
        f5289y = new n0(i5, 19);
        f5290z = new n0(i5, 20);
        A = new n0(i5, 21);
        B = new n0(i5, 22);
        C = new n0(i5, 23);
        D = new n0(i5, 24);
        E = new n0(i5, 25);
        F = new n0(i5, 26);
        G = new n0(i5, 27);
        H = new n0(i5, 28);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n0(int i5, int i6) {
        super(i5);
        this.f5291e = i6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a4.a
    public final Object a() throws Throwable {
        i4.l0 l0VarA;
        t3.h hVarF;
        Choreographer choreographer;
        Object[] objArr = 0;
        switch (this.f5291e) {
            case 0:
                AndroidCompositionLocals_androidKt.b("LocalConfiguration");
                throw null;
            case 1:
                AndroidCompositionLocals_androidKt.b("LocalContext");
                throw null;
            case 2:
                AndroidCompositionLocals_androidKt.b("LocalImageVectorCache");
                throw null;
            case 3:
                AndroidCompositionLocals_androidKt.b("LocalResourceIdCache");
                throw null;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                AndroidCompositionLocals_androidKt.b("LocalSavedStateRegistryOwner");
                throw null;
            case 5:
                AndroidCompositionLocals_androidKt.b("LocalView");
                throw null;
            case 6:
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    p4.e eVar = i4.e0.f2727a;
                    j4.d dVar = n4.m.f4303a;
                    a4.e l3Var = new l3(2, objArr == true ? 1 : 0, 1);
                    Thread threadCurrentThread = Thread.currentThread();
                    t3.d dVar2 = t3.d.f5806d;
                    t3.e eVar2 = (t3.e) dVar.j(dVar2);
                    t3.i iVar = t3.i.f5807d;
                    if (eVar2 == null) {
                        l0VarA = i4.k1.a();
                        hVarF = i4.y.f(iVar, p3.p.A(dVar, l0VarA), true);
                        p4.e eVar3 = i4.e0.f2727a;
                        if (hVarF != eVar3 && hVarF.j(dVar2) == null) {
                            hVarF = hVarF.k(eVar3);
                        }
                    } else {
                        if (eVar2 instanceof i4.l0) {
                        }
                        l0VarA = (i4.l0) i4.k1.f2748a.get();
                        hVarF = i4.y.f(iVar, dVar, true);
                        p4.e eVar4 = i4.e0.f2727a;
                        if (hVarF != eVar4 && hVarF.j(dVar2) == null) {
                            hVarF = hVarF.k(eVar4);
                        }
                    }
                    i4.c cVar = new i4.c(hVarF, threadCurrentThread, l0VarA);
                    cVar.c0(i4.w.f2779d, cVar, l3Var);
                    i4.l0 l0Var = cVar.f2720h;
                    if (l0Var != null) {
                        int i5 = i4.l0.f2750i;
                        l0Var.u(false);
                    }
                    while (!Thread.interrupted()) {
                        try {
                            long jV = l0Var != null ? l0Var.v() : Long.MAX_VALUE;
                            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i4.a1.f2715d;
                            if (atomicReferenceFieldUpdater.get(cVar) instanceof i4.o0) {
                                LockSupport.parkNanos(cVar, jV);
                            } else {
                                if (l0Var != null) {
                                    int i6 = i4.l0.f2750i;
                                    l0Var.n(false);
                                }
                                Object objR = i4.y.r(atomicReferenceFieldUpdater.get(cVar));
                                i4.o oVar = objR instanceof i4.o ? (i4.o) objR : null;
                                if (oVar != null) {
                                    throw oVar.f2763a;
                                }
                                choreographer = (Choreographer) objR;
                            }
                        } catch (Throwable th) {
                            if (l0Var != null) {
                                int i7 = i4.l0.f2750i;
                                l0Var.n(false);
                            }
                            throw th;
                        }
                    }
                    InterruptedException interruptedException = new InterruptedException();
                    cVar.x(interruptedException);
                    throw interruptedException;
                } else {
                    choreographer = Choreographer.getInstance();
                }
                v0 v0Var = new v0(choreographer, p3.j.p(Looper.getMainLooper()));
                return p3.p.A(v0Var, v0Var.f5435o);
            case 7:
            case 8:
                return null;
            case 9:
                j1.b("LocalAutofillTree");
                throw null;
            case 10:
                j1.b("LocalClipboardManager");
                throw null;
            case 11:
                j1.b("LocalDensity");
                throw null;
            case 12:
                j1.b("LocalFocusManager");
                throw null;
            case 13:
                j1.b("LocalFontFamilyResolver");
                throw null;
            case 14:
                j1.b("LocalFontLoader");
                throw null;
            case 15:
                j1.b("LocalGraphicsContext");
                throw null;
            case 16:
                j1.b("LocalHapticFeedback");
                throw null;
            case 17:
                j1.b("LocalInputManager");
                throw null;
            case 18:
                j1.b("LocalLayoutDirection");
                throw null;
            case 19:
                return null;
            case 20:
                return Boolean.FALSE;
            case 21:
            case 22:
                return null;
            case 23:
                j1.b("LocalTextToolbar");
                throw null;
            case 24:
                j1.b("LocalUriHandler");
                throw null;
            case 25:
                j1.b("LocalViewConfiguration");
                throw null;
            case 26:
                j1.b("LocalWindowInfo");
                throw null;
            case 27:
                return Boolean.FALSE;
            default:
                return null;
        }
    }
}
