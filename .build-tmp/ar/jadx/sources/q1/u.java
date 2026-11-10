package q1;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u extends ViewGroup implements p1.e1, p1.k1, androidx.lifecycle.f {
    public static Class B0;
    public static Method C0;
    public final s0.a A;
    public final q A0;
    public boolean B;
    public final h C;
    public final p1.g1 D;
    public boolean E;
    public b1 F;
    public p1 G;
    public k2.a H;
    public boolean I;
    public final p1.p0 J;
    public final y0 K;
    public long L;
    public final int[] M;
    public final float[] N;
    public final float[] O;
    public final float[] P;
    public long Q;
    public boolean R;
    public long S;
    public boolean T;
    public final f0.s1 U;
    public final f0.n0 V;
    public a4.c W;
    public final j a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final k f5373b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final l f5374c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5375d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final e2.a0 f5376d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5377e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final e2.y f5378e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p1.f0 f5379f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final AtomicReference f5380f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f0.s1 f5381g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final k1 f5382g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final androidx.compose.ui.focus.b f5383h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final s1 f5384h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public t3.h f5385i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final f0.s1 f5386i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o1 f5387j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f5388j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final v2 f5389k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final f0.s1 f5390k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y0.s f5391l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final f1.b f5392l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p1.d0 f5393m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final g1.c f5394m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u f5395n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final o1.d f5396n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final w1.o f5397o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final s0 f5398o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f0 f5399p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public MotionEvent f5400p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public t0.c f5401q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public long f5402q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final g f5403r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final a2.h f5404r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final a1.b f5405s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final h0.e f5406s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final s0.f f5407t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final s f5408t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ArrayList f5409u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public final androidx.lifecycle.a0 f5410u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ArrayList f5411v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public boolean f5412v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f5413w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public final r f5414w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final j1.e f5415x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final d1 f5416x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final b0.a0 f5417y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public boolean f5418y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public a4.c f5419z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final v1.i f5420z0;

    /* JADX WARN: Type inference failed for: r3v18, types: [q1.j] */
    /* JADX WARN: Type inference failed for: r3v19, types: [q1.k] */
    /* JADX WARN: Type inference failed for: r3v20, types: [q1.l] */
    public u(Context context, t3.h hVar) {
        k2.k kVar;
        super(context);
        this.f5375d = 9205357640488583168L;
        int i5 = 1;
        this.f5377e = true;
        this.f5379f = new p1.f0();
        k2.d dVarA = d4.a.a(context);
        f0.h hVar2 = f0.h.f2187h;
        this.f5381g = new f0.s1(dVarA, hVar2);
        w1.c cVar = new w1.c();
        EmptySemanticsElement emptySemanticsElement = new EmptySemanticsElement(cVar);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        this.f5383h = new androidx.compose.ui.focus.b(new i4.v0(1, this, u.class, "registerOnEndApplyChangesListener", "registerOnEndApplyChangesListener(Lkotlin/jvm/functions/Function0;)V", i7, i6, 1), new o(2, this, u.class, "onRequestFocusForOwner", "onRequestFocusForOwner-7o62pno(Landroidx/compose/ui/focus/FocusDirection;Landroidx/compose/ui/geometry/Rect;)Z", 0, 0), new i4.v0(1, this, u.class, "onMoveFocusInChildren", "onMoveFocusInChildren-3ESFkO8(I)Z", i7, i6, 2), new b.h0(i8, this, u.class, "onClearFocusForOwner", "onClearFocusForOwner()V", i7, i6, 3), new b.h0(i8, this, u.class, "onFetchFocusRect", "onFetchFocusRect()Landroidx/compose/ui/geometry/Rect;", i7, i6, 4), new d0.n(this, u.class, "layoutDirection", "getLayoutDirection()Landroidx/compose/ui/unit/LayoutDirection;", 0, 1));
        o1 o1Var = new o1();
        this.f5385i = hVar;
        this.f5387j = o1Var;
        this.f5389k = new v2();
        int i9 = 0;
        r0.p pVarA = androidx.compose.ui.input.key.a.a(new p(this, i9));
        r0.p pVarA2 = androidx.compose.ui.input.rotary.a.a();
        this.f5391l = new y0.s();
        p1.d0 d0Var = new p1.d0(3);
        d0Var.V(n1.r0.f4150b);
        d0Var.T(getDensity());
        d0Var.W(emptySemanticsElement.e(pVarA2).e(pVarA).e(((androidx.compose.ui.focus.b) getFocusOwner()).f182i).e(o1Var.f5299c));
        this.f5393m = d0Var;
        this.f5395n = this;
        this.f5397o = new w1.o(getRoot(), cVar);
        f0 f0Var = new f0(this);
        this.f5399p = f0Var;
        this.f5401q = new t0.c(this, new b.h0(0, this, m0.class, "getContentCaptureSessionCompat", "getContentCaptureSessionCompat(Landroid/view/View;)Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", 1, 0, 2));
        this.f5403r = new g(context);
        a1.b bVar = new a1.b();
        bVar.f23d = this;
        bVar.f24e = new Object();
        this.f5405s = bVar;
        this.f5407t = new s0.f();
        this.f5409u = new ArrayList();
        this.f5415x = new j1.e();
        p1.d0 root = getRoot();
        b0.a0 a0Var = new b0.a0();
        a0Var.f374b = root;
        p1.t tVar = root.f4801y.f4969b;
        a1.b bVar2 = new a1.b();
        bVar2.f23d = tVar;
        bVar2.f24e = new j1.h(0);
        j.u uVar = new j.u();
        uVar.f3151a = j.f0.f3102a;
        uVar.f3152b = j.l.f3117a;
        uVar.f3153c = k.a.f3355c;
        uVar.c(j.f0.d(10));
        bVar2.f25f = uVar;
        a0Var.f375c = bVar2;
        a0Var.f376d = new a1.d(9);
        a0Var.f377e = new p1.r();
        this.f5417y = a0Var;
        this.f5419z = n.f5266f;
        this.A = new s0.a(this, getAutofillTree());
        this.C = new h(context);
        this.D = new p1.g1(new p(this, i5));
        this.J = new p1.p0(getRoot());
        this.K = new y0(ViewConfiguration.get(context));
        this.L = h1.c.d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.M = new int[]{0, 0};
        float[] fArrA = y0.f0.a();
        this.N = fArrA;
        this.O = y0.f0.a();
        this.P = y0.f0.a();
        this.Q = -1L;
        this.S = 9187343241974906880L;
        this.T = true;
        this.U = f0.l.s(null);
        this.V = f0.l.n(new r(this, i5));
        this.a0 = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: q1.j
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f5230a.G();
            }
        };
        this.f5373b0 = new ViewTreeObserver.OnScrollChangedListener() { // from class: q1.k
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f5254a.G();
            }
        };
        this.f5374c0 = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: q1.l
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z2) {
                this.f5257a.f5394m0.f2513a.setValue(new g1.a(z2 ? 1 : 2));
            }
        };
        e2.a0 a0Var2 = new e2.a0(getView(), this);
        this.f5376d0 = a0Var2;
        this.f5378e0 = new e2.y(a0Var2);
        this.f5380f0 = new AtomicReference(null);
        this.f5382g0 = new k1(getTextInputService());
        this.f5384h0 = new s1();
        this.f5386i0 = new f0.s1(d4.a.r(context), hVar2);
        Configuration configuration = context.getResources().getConfiguration();
        int i10 = Build.VERSION.SDK_INT;
        this.f5388j0 = i10 >= 31 ? configuration.fontWeightAdjustment : 0;
        int layoutDirection = context.getResources().getConfiguration().getLayoutDirection();
        k2.k kVar2 = k2.k.f3416d;
        if (layoutDirection != 0) {
            kVar = layoutDirection != 1 ? null : k2.k.f3417e;
        } else {
            kVar = kVar2;
        }
        this.f5390k0 = f0.l.s(kVar != null ? kVar : kVar2);
        this.f5392l0 = new f1.b(this);
        this.f5394m0 = new g1.c(isInTouchMode() ? 1 : 2);
        this.f5396n0 = new o1.d(this);
        this.f5398o0 = new s0(this);
        this.f5404r0 = new a2.h(24);
        this.f5406s0 = new h0.e(new a4.a[16]);
        this.f5408t0 = new s(this);
        this.f5410u0 = new androidx.lifecycle.a0(5, this);
        this.f5414w0 = new r(this, i9);
        this.f5416x0 = i10 < 29 ? new a2.h(fArrA) : new e1();
        addOnAttachStateChangeListener(this.f5401q);
        setWillNotDraw(false);
        setFocusable(true);
        l0.f5258a.a(this, 1, false);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        int i11 = w2.u.f6438a;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        setAccessibilityDelegate(f0Var.f6374b);
        setOnDragListener(o1Var);
        getRoot().e(this);
        if (i10 >= 29) {
            h0.f5216a.a(this);
        }
        this.f5420z0 = i10 >= 31 ? new v1.i() : null;
        this.A0 = new q(this);
    }

    public static final boolean g(u uVar, w0.b bVar, x0.d dVar) {
        Integer numH;
        if (uVar.isFocused() || uVar.hasFocus()) {
            return true;
        }
        return super.requestFocus((bVar == null || (numH = w0.d.H(bVar.f6225a)) == null) ? 130 : numH.intValue(), dVar != null ? y0.l0.u(dVar) : null);
    }

    @p3.a
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final m get_viewTreeOwners() {
        return (m) this.U.getValue();
    }

    public static void h(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = viewGroup.getChildAt(i5);
            if (childAt instanceof u) {
                ((u) childAt).t();
            } else if (childAt instanceof ViewGroup) {
                h((ViewGroup) childAt);
            }
        }
    }

    public static long i(int i5) {
        int mode = View.MeasureSpec.getMode(i5);
        int size = View.MeasureSpec.getSize(i5);
        if (mode == Integer.MIN_VALUE) {
            return (((long) 0) << 32) | ((long) size);
        }
        if (mode == 0) {
            return (((long) 0) << 32) | ((long) Integer.MAX_VALUE);
        }
        if (mode != 1073741824) {
            throw new IllegalStateException();
        }
        long j5 = size;
        return j5 | (j5 << 32);
    }

    public static View j(View view, int i5) throws NoSuchMethodException {
        if (Build.VERSION.SDK_INT < 29) {
            Method declaredMethod = View.class.getDeclaredMethod("getAccessibilityViewId", null);
            declaredMethod.setAccessible(true);
            if (b4.l.a(declaredMethod.invoke(view, null), Integer.valueOf(i5))) {
                return view;
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i6 = 0; i6 < childCount; i6++) {
                    View viewJ = j(viewGroup.getChildAt(i6), i5);
                    if (viewJ != null) {
                        return viewJ;
                    }
                }
            }
        }
        return null;
    }

    public static void l(p1.d0 d0Var) {
        d0Var.z();
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                l((p1.d0) objArr[i6]);
                i6++;
            } while (i6 < i5);
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0044  */
    /* JADX WARN: Code duplicated, block: B:37:0x007f  */
    public static boolean n(MotionEvent motionEvent) {
        boolean z2;
        float x4 = motionEvent.getX();
        if (Float.isInfinite(x4) || Float.isNaN(x4)) {
            z2 = true;
        } else {
            float y4 = motionEvent.getY();
            if (Float.isInfinite(y4) || Float.isNaN(y4)) {
                z2 = true;
            } else {
                float rawX = motionEvent.getRawX();
                if (Float.isInfinite(rawX) || Float.isNaN(rawX)) {
                    z2 = true;
                } else {
                    float rawY = motionEvent.getRawY();
                    if (Float.isInfinite(rawY) || Float.isNaN(rawY)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
            }
        }
        if (!z2) {
            int pointerCount = motionEvent.getPointerCount();
            for (int i5 = 1; i5 < pointerCount; i5++) {
                float x5 = motionEvent.getX(i5);
                if (Float.isInfinite(x5) || Float.isNaN(x5)) {
                    z2 = true;
                } else {
                    float y5 = motionEvent.getY(i5);
                    if (Float.isInfinite(y5) || Float.isNaN(y5) || (Build.VERSION.SDK_INT >= 29 && !w1.f5442a.a(motionEvent, i5))) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    break;
                }
            }
        }
        return z2;
    }

    private void setDensity(k2.b bVar) {
        this.f5381g.setValue(bVar);
    }

    private void setFontFamilyResolver(d2.d dVar) {
        this.f5386i0.setValue(dVar);
    }

    private void setLayoutDirection(k2.k kVar) {
        this.f5390k0.setValue(kVar);
    }

    private final void set_viewTreeOwners(m mVar) {
        this.U.setValue(mVar);
    }

    public final void A(p1.d1 d1Var) {
        a2.h hVar;
        h0.e eVar;
        Reference referencePoll;
        if (this.G != null) {
            b1.n nVar = q2.f5310s;
        }
        do {
            hVar = this.f5404r0;
            ReferenceQueue referenceQueue = (ReferenceQueue) hVar.f50f;
            eVar = (h0.e) hVar.f49e;
            referencePoll = referenceQueue.poll();
            if (referencePoll != null) {
                eVar.l(referencePoll);
            }
        } while (referencePoll != null);
        eVar.b(new WeakReference(d1Var, (ReferenceQueue) hVar.f50f));
    }

    public final void B(p1.d0 d0Var) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (d0Var != null) {
            while (d0Var != null && d0Var.f4802z.f4910r.f4877n == 1) {
                if (!this.I) {
                    p1.d0 d0VarR = d0Var.r();
                    if (d0VarR == null) {
                        break;
                    }
                    long j5 = d0VarR.f4801y.f4969b.f4133g;
                    if (k2.a.g(j5) && k2.a.f(j5)) {
                        break;
                    }
                }
                d0Var = d0Var.r();
            }
            if (d0Var == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public final long C(long j5) {
        z();
        float fD = x0.c.d(j5) - x0.c.d(this.S);
        float fE = x0.c.e(j5) - x0.c.e(this.S);
        return y0.f0.b(p3.j.b(fD, fE), this.P);
    }

    public final int D(MotionEvent motionEvent) {
        Object obj;
        if (this.f5418y0) {
            this.f5418y0 = false;
            int metaState = motionEvent.getMetaState();
            this.f5389k.getClass();
            v2.f5437b.setValue(new j1.y(metaState));
        }
        j1.e eVar = this.f5415x;
        a2.h hVarA = eVar.a(motionEvent, this);
        b0.a0 a0Var = this.f5417y;
        if (hVarA == null) {
            a0Var.d();
            return 0;
        }
        List list = (List) hVarA.f49e;
        int size = list.size() - 1;
        if (size < 0) {
            obj = null;
            break;
        }
        while (true) {
            int i5 = size - 1;
            obj = list.get(size);
            if (((j1.w) obj).f3294e) {
                break;
            }
            if (i5 < 0) {
                obj = null;
                break;
            }
            size = i5;
        }
        j1.w wVar = (j1.w) obj;
        if (wVar != null) {
            this.f5375d = wVar.f3293d;
        }
        int iC = a0Var.c(hVarA, this, o(motionEvent));
        int actionMasked = motionEvent.getActionMasked();
        if ((actionMasked != 0 && actionMasked != 5) || (iC & 1) != 0) {
            return iC;
        }
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        eVar.f3243c.delete(pointerId);
        eVar.f3242b.delete(pointerId);
        return iC;
    }

    public final void E(MotionEvent motionEvent, int i5, long j5, boolean z2) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (i5 != 9 && i5 != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i6 = 0; i6 < pointerCount; i6++) {
            pointerPropertiesArr[i6] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i7 = 0; i7 < pointerCount; i7++) {
            pointerCoordsArr[i7] = new MotionEvent.PointerCoords();
        }
        int i8 = 0;
        while (i8 < pointerCount) {
            int i9 = ((actionIndex < 0 || i8 < actionIndex) ? 0 : 1) + i8;
            motionEvent.getPointerProperties(i9, pointerPropertiesArr[i8]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i8];
            motionEvent.getPointerCoords(i9, pointerCoords);
            long jQ = q(p3.j.b(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = x0.c.d(jQ);
            pointerCoords.y = x0.c.e(jQ);
            i8++;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? j5 : motionEvent.getDownTime(), j5, i5, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), z2 ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        a2.h hVarA = this.f5415x.a(motionEventObtain, this);
        b4.l.b(hVarA);
        this.f5417y.c(hVarA, this, true);
        motionEventObtain.recycle();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final void F(a4.e eVar, v3.c cVar) {
        t tVar;
        if (cVar instanceof t) {
            tVar = (t) cVar;
            int i5 = tVar.f5366f;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                tVar.f5366f = i5 - Integer.MIN_VALUE;
            } else {
                tVar = new t(this, cVar);
            }
        } else {
            tVar = new t(this, cVar);
        }
        Object obj = tVar.f5364d;
        int i6 = tVar.f5366f;
        if (i6 == 0) {
            p3.j.O(obj);
            p pVar = new p(this, 2);
            tVar.f5366f = 1;
            if (i4.y.c(new l4.n(pVar, this.f5380f0, eVar, null), tVar) == u3.a.f5838d) {
                return;
            }
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        throw new i4.p();
    }

    public final void G() {
        int[] iArr = this.M;
        getLocationOnScreen(iArr);
        long j5 = this.L;
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        boolean z2 = false;
        int i7 = iArr[0];
        if (i5 != i7 || i6 != iArr[1]) {
            this.L = h1.c.d(i7, iArr[1]);
            if (i5 != Integer.MAX_VALUE && i6 != Integer.MAX_VALUE) {
                getRoot().f4802z.f4910r.r0();
                z2 = true;
            }
        }
        this.J.a(z2);
    }

    @Override // androidx.lifecycle.f
    public final void a(androidx.lifecycle.v vVar) {
        setShowLayoutBounds(s1.a());
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        addView(view, -1);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5) {
        b4.l.b(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, i5, layoutParams, true);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5, int i6) {
        ViewGroup.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.width = i5;
        layoutParamsGenerateDefaultLayoutParams.height = i6;
        addViewInLayout(view, -1, layoutParamsGenerateDefaultLayoutParams, true);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, i5, layoutParams, true);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, -1, layoutParams, true);
    }

    @Override // android.view.View
    public final void autofill(SparseArray sparseArray) {
        s0.a aVar = this.A;
        if (aVar != null) {
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                int iKeyAt = sparseArray.keyAt(i5);
                AutofillValue autofillValue = (AutofillValue) sparseArray.get(iKeyAt);
                s0.d dVar = s0.d.f5765a;
                if (dVar.d(autofillValue)) {
                    s0.f fVar = aVar.f5762b;
                    dVar.i(autofillValue).toString();
                    if (fVar.f5767a.get(Integer.valueOf(iKeyAt)) != null) {
                        throw new ClassCastException();
                    }
                } else {
                    if (dVar.b(autofillValue)) {
                        throw new i4.x("An operation is not implemented: b/138604541: Add onFill() callback for date");
                    }
                    if (dVar.c(autofillValue)) {
                        throw new i4.x("An operation is not implemented: b/138604541: Add onFill() callback for list");
                    }
                    if (dVar.e(autofillValue)) {
                        throw new i4.x("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i5) {
        return this.f5399p.f(false, i5, this.f5375d);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i5) {
        return this.f5399p.f(true, i5, this.f5375d);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        boolean z2;
        if (!isAttachedToWindow()) {
            l(getRoot());
        }
        r(true);
        synchronized (p0.m.f4719c) {
            j.a0 a0Var = p0.m.f4726j.f4666h;
            z2 = a0Var != null && a0Var.h();
        }
        if (z2) {
            p0.m.a();
        }
        this.f5413w = true;
        y0.s sVar = this.f5391l;
        y0.d dVar = sVar.f6683a;
        Canvas canvas2 = dVar.f6617a;
        dVar.f6617a = canvas;
        getRoot().j(dVar, null);
        sVar.f6683a.f6617a = canvas2;
        if (!this.f5409u.isEmpty()) {
            int size = this.f5409u.size();
            for (int i5 = 0; i5 < size; i5++) {
                ((p1.d1) this.f5409u.get(i5)).i();
            }
        }
        if (q2.f5314w) {
            int iSave = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave);
        }
        this.f5409u.clear();
        this.f5413w = false;
        ArrayList arrayList = this.f5411v;
        if (arrayList != null) {
            this.f5409u.addAll(arrayList);
            arrayList.clear();
        }
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        l1.a aVar;
        int size;
        p1.t0 t0Var;
        r0.o oVarE;
        p1.t0 t0Var2;
        if (this.f5412v0) {
            androidx.lifecycle.a0 a0Var = this.f5410u0;
            removeCallbacks(a0Var);
            if (motionEvent.getActionMasked() == 8) {
                this.f5412v0 = false;
            } else {
                a0Var.run();
            }
        }
        if (motionEvent.getActionMasked() != 8) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (n(motionEvent) || !isAttachedToWindow()) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (!motionEvent.isFromSource(4194304)) {
            return (k(motionEvent) & 1) != 0;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        motionEvent.getAxisValue(26);
        getContext();
        viewConfiguration.getScaledVerticalScrollFactor();
        getContext();
        viewConfiguration.getScaledHorizontalScrollFactor();
        motionEvent.getEventTime();
        motionEvent.getDeviceId();
        androidx.compose.ui.focus.b bVar = (androidx.compose.ui.focus.b) getFocusOwner();
        if (bVar.f180g.a()) {
            throw new IllegalStateException("Dispatching rotary event while focus system is invalidated.");
        }
        w0.r rVarG = w0.d.g(bVar.f179f);
        if (rVarG != null) {
            r0.o oVar = rVarG.f5681d;
            if (!oVar.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            p1.d0 d0VarS = p1.f.s(rVarG);
            loop0: while (true) {
                if (d0VarS == null) {
                    oVarE = null;
                    break;
                }
                if ((d0VarS.f4801y.f4972e.f5684g & 16384) != 0) {
                    while (oVar != null) {
                        if ((oVar.f5683f & 16384) != 0) {
                            h0.e eVar = null;
                            oVarE = oVar;
                            while (oVarE != null) {
                                if (oVarE instanceof l1.a) {
                                    break loop0;
                                }
                                if ((oVarE.f5683f & 16384) != 0 && (oVarE instanceof p1.m)) {
                                    int i5 = 0;
                                    for (r0.o oVar2 = ((p1.m) oVarE).f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
                                        if ((oVar2.f5683f & 16384) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                oVarE = oVar2;
                                            } else {
                                                if (eVar == null) {
                                                    eVar = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE != null) {
                                                    eVar.b(oVarE);
                                                    oVarE = null;
                                                }
                                                eVar.b(oVar2);
                                            }
                                        }
                                    }
                                    if (i5 == 1) {
                                    }
                                }
                                oVarE = p1.f.e(eVar);
                            }
                        }
                        oVar = oVar.f5685h;
                    }
                }
                d0VarS = d0VarS.r();
                oVar = (d0VarS == null || (t0Var2 = d0VarS.f4801y) == null) ? null : t0Var2.f4971d;
            }
            aVar = (l1.a) oVarE;
        } else {
            aVar = null;
        }
        if (aVar != null) {
            l1.a aVar2 = aVar;
            r0.o oVar3 = aVar2.f5681d;
            if (!oVar3.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            r0.o oVar4 = oVar3.f5685h;
            p1.d0 d0VarS2 = p1.f.s(aVar);
            ArrayList arrayList = null;
            while (d0VarS2 != null) {
                if ((d0VarS2.f4801y.f4972e.f5684g & 16384) != 0) {
                    while (oVar4 != null) {
                        if ((oVar4.f5683f & 16384) != 0) {
                            r0.o oVarE2 = oVar4;
                            h0.e eVar2 = null;
                            while (oVarE2 != null) {
                                if (oVarE2 instanceof l1.a) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(oVarE2);
                                } else if ((oVarE2.f5683f & 16384) != 0 && (oVarE2 instanceof p1.m)) {
                                    int i6 = 0;
                                    for (r0.o oVar5 = ((p1.m) oVarE2).f4921r; oVar5 != null; oVar5 = oVar5.f5686i) {
                                        if ((oVar5.f5683f & 16384) != 0) {
                                            i6++;
                                            if (i6 == 1) {
                                                oVarE2 = oVar5;
                                            } else {
                                                if (eVar2 == null) {
                                                    eVar2 = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE2 != null) {
                                                    eVar2.b(oVarE2);
                                                    oVarE2 = null;
                                                }
                                                eVar2.b(oVar5);
                                            }
                                        }
                                    }
                                    if (i6 == 1) {
                                    }
                                }
                                oVarE2 = p1.f.e(eVar2);
                            }
                        }
                        oVar4 = oVar4.f5685h;
                    }
                }
                d0VarS2 = d0VarS2.r();
                oVar4 = (d0VarS2 == null || (t0Var = d0VarS2.f4801y) == null) ? null : t0Var.f4971d;
            }
            if (arrayList != null && (size = arrayList.size() - 1) >= 0) {
                while (true) {
                    int i7 = size - 1;
                    ((l1.a) arrayList.get(size)).getClass();
                    if (i7 < 0) {
                        break;
                    }
                    size = i7;
                }
            }
            r0.o oVarE3 = aVar2.f5681d;
            h0.e eVar3 = null;
            while (oVarE3 != null) {
                if (oVarE3 instanceof l1.a) {
                } else if ((oVarE3.f5683f & 16384) != 0 && (oVarE3 instanceof p1.m)) {
                    int i8 = 0;
                    for (r0.o oVar6 = ((p1.m) oVarE3).f4921r; oVar6 != null; oVar6 = oVar6.f5686i) {
                        if ((oVar6.f5683f & 16384) != 0) {
                            i8++;
                            if (i8 == 1) {
                                oVarE3 = oVar6;
                            } else {
                                if (eVar3 == null) {
                                    eVar3 = new h0.e(new r0.o[16]);
                                }
                                if (oVarE3 != null) {
                                    eVar3.b(oVarE3);
                                    oVarE3 = null;
                                }
                                eVar3.b(oVar6);
                            }
                        }
                    }
                    if (i8 == 1) {
                    }
                }
                oVarE3 = p1.f.e(eVar3);
            }
            r0.o oVarE4 = aVar2.f5681d;
            h0.e eVar4 = null;
            while (oVarE4 != null) {
                if (oVarE4 instanceof l1.a) {
                } else if ((oVarE4.f5683f & 16384) != 0 && (oVarE4 instanceof p1.m)) {
                    int i9 = 0;
                    for (r0.o oVar7 = ((p1.m) oVarE4).f4921r; oVar7 != null; oVar7 = oVar7.f5686i) {
                        if ((oVar7.f5683f & 16384) != 0) {
                            i9++;
                            if (i9 == 1) {
                                oVarE4 = oVar7;
                            } else {
                                if (eVar4 == null) {
                                    eVar4 = new h0.e(new r0.o[16]);
                                }
                                if (oVarE4 != null) {
                                    eVar4.b(oVarE4);
                                    oVarE4 = null;
                                }
                                eVar4.b(oVar7);
                            }
                        }
                    }
                    if (i9 == 1) {
                    }
                }
                oVarE4 = p1.f.e(eVar4);
            }
            if (arrayList != null) {
                int size2 = arrayList.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    n nVar = ((l1.a) arrayList.get(i10)).f3499q;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:50:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:75:0x0142  */
    /* JADX WARN: Code duplicated, block: B:77:0x0149 A[RETURN] */
    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int iX;
        p1.t0 t0Var;
        boolean z2;
        boolean z3 = this.f5412v0;
        androidx.lifecycle.a0 a0Var = this.f5410u0;
        if (z3) {
            removeCallbacks(a0Var);
            a0Var.run();
        }
        if (!n(motionEvent) && isAttachedToWindow()) {
            f0 f0Var = this.f5399p;
            u uVar = f0Var.f5178d;
            AccessibilityManager accessibilityManager = f0Var.f5181g;
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                int action = motionEvent.getAction();
                if (action == 7 || action == 9) {
                    float x4 = motionEvent.getX();
                    float y4 = motionEvent.getY();
                    uVar.r(true);
                    p1.r rVar = new p1.r();
                    p1.d0 root = uVar.getRoot();
                    long jB = p3.j.b(x4, y4);
                    p1.t0 t0Var2 = root.f4801y;
                    p1.y0 y0Var = t0Var2.f4970c;
                    y0.n0 n0Var = p1.y0.H;
                    t0Var2.f4970c.P0(p1.y0.L, y0Var.I0(jB), rVar, true, true);
                    r0.o oVar = (r0.o) q3.l.c0(rVar);
                    p1.d0 d0VarS = oVar != null ? p1.f.s(oVar) : null;
                    if (d0VarS == null || (t0Var = d0VarS.f4801y) == null || !t0Var.d(8)) {
                        iX = Integer.MIN_VALUE;
                    } else {
                        w1.n nVarC = p3.p.c(d0VarS, false);
                        p1.y0 y0VarC = nVarC.c();
                        if (y0VarC != null ? y0VarC.S0() : false) {
                            z2 = false;
                        } else {
                            if (nVarC.f6318d.f6308d.containsKey(w1.q.f6348m)) {
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                        }
                        if (!z2) {
                            iX = Integer.MIN_VALUE;
                        } else {
                            if (uVar.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(d0VarS) != null) {
                                throw new ClassCastException();
                            }
                            iX = f0Var.x(d0VarS.f4781e);
                        }
                    }
                    uVar.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                    int i5 = f0Var.f5179e;
                    if (i5 != iX) {
                        f0Var.f5179e = iX;
                        f0.B(f0Var, iX, 128, null, 12);
                        f0.B(f0Var, i5, 256, null, 12);
                    }
                } else if (action == 10) {
                    int i6 = f0Var.f5179e;
                    if (i6 == Integer.MIN_VALUE) {
                        uVar.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                    } else if (i6 != Integer.MIN_VALUE) {
                        f0Var.f5179e = Integer.MIN_VALUE;
                        f0.B(f0Var, Integer.MIN_VALUE, 128, null, 12);
                        f0.B(f0Var, i6, 256, null, 12);
                    }
                }
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 7) {
                if (actionMasked == 10 && o(motionEvent)) {
                    if (motionEvent.getToolType(0) != 3 || motionEvent.getButtonState() == 0) {
                        MotionEvent motionEvent2 = this.f5400p0;
                        if (motionEvent2 != null) {
                            motionEvent2.recycle();
                        }
                        this.f5400p0 = MotionEvent.obtainNoHistory(motionEvent);
                        this.f5412v0 = true;
                        postDelayed(a0Var, 8L);
                        return false;
                    }
                } else if ((k(motionEvent) & 1) != 0) {
                    return true;
                }
            } else if (p(motionEvent)) {
                if ((k(motionEvent) & 1) != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isFocused()) {
            return ((androidx.compose.ui.focus.b) getFocusOwner()).b(keyEvent, new c0.t2(this, 6, keyEvent));
        }
        int metaState = keyEvent.getMetaState();
        this.f5389k.getClass();
        v2.f5437b.setValue(new j1.y(metaState));
        return ((androidx.compose.ui.focus.b) getFocusOwner()).b(keyEvent, w0.f.f6233f) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        p1.t0 t0Var;
        if (isFocused()) {
            androidx.compose.ui.focus.b bVar = (androidx.compose.ui.focus.b) getFocusOwner();
            if (bVar.f180g.a()) {
                throw new IllegalStateException("Dispatching intercepted soft keyboard event while focus system is invalidated.");
            }
            w0.r rVarG = w0.d.g(bVar.f179f);
            if (rVarG != null) {
                r0.o oVar = rVarG.f5681d;
                if (!oVar.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                p1.d0 d0VarS = p1.f.s(rVarG);
                while (d0VarS != null) {
                    if ((d0VarS.f4801y.f4972e.f5684g & 131072) != 0) {
                        while (oVar != null) {
                            if ((oVar.f5683f & 131072) != 0) {
                                r0.o oVarE = oVar;
                                h0.e eVar = null;
                                while (oVarE != null) {
                                    if ((oVarE.f5683f & 131072) != 0 && (oVarE instanceof p1.m)) {
                                        int i5 = 0;
                                        for (r0.o oVar2 = ((p1.m) oVarE).f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
                                            if ((oVar2.f5683f & 131072) != 0) {
                                                i5++;
                                                if (i5 == 1) {
                                                    oVarE = oVar2;
                                                } else {
                                                    if (eVar == null) {
                                                        eVar = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE != null) {
                                                        eVar.b(oVarE);
                                                        oVarE = null;
                                                    }
                                                    eVar.b(oVar2);
                                                }
                                            }
                                        }
                                        if (i5 == 1) {
                                        }
                                    }
                                    oVarE = p1.f.e(eVar);
                                }
                            }
                            oVar = oVar.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    oVar = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
                }
            }
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideStructure(ViewStructure viewStructure) {
        if (Build.VERSION.SDK_INT < 28) {
            g0.f5208a.a(viewStructure, getView());
        } else {
            super.dispatchProvideStructure(viewStructure);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f5412v0) {
            androidx.lifecycle.a0 a0Var = this.f5410u0;
            removeCallbacks(a0Var);
            MotionEvent motionEvent2 = this.f5400p0;
            b4.l.b(motionEvent2);
            if (motionEvent.getActionMasked() == 0 && motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) {
                this.f5412v0 = false;
            } else {
                a0Var.run();
            }
        }
        if (!n(motionEvent) && isAttachedToWindow() && (motionEvent.getActionMasked() != 2 || p(motionEvent))) {
            int iK = k(motionEvent);
            if ((iK & 2) != 0) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            if ((iK & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    public final View findViewByAccessibilityIdTraversal(int i5) throws IllegalAccessException, InvocationTargetException {
        try {
            if (Build.VERSION.SDK_INT < 29) {
                return j(this, i5);
            }
            Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(i5));
            if (objInvoke instanceof View) {
                return (View) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i5) {
        if (view != null) {
            x0.d dVarD = w0.d.d(view);
            w0.b bVarI = w0.d.I(i5);
            if (b4.l.a(((androidx.compose.ui.focus.b) getFocusOwner()).c(bVarI != null ? bVarI.f6225a : 6, dVarD, n.f5267g), Boolean.TRUE)) {
                return this;
            }
        }
        return super.focusSearch(view, i5);
    }

    public g getAccessibilityManager() {
        return this.f5403r;
    }

    public final b1 getAndroidViewsHandler$ui_release() {
        if (this.F == null) {
            b1 b1Var = new b1(getContext());
            this.F = b1Var;
            addView(b1Var, -1);
            requestLayout();
        }
        b1 b1Var2 = this.F;
        b4.l.b(b1Var2);
        return b1Var2;
    }

    public s0.b getAutofill() {
        return this.A;
    }

    public s0.f getAutofillTree() {
        return this.f5407t;
    }

    public h getClipboardManager() {
        return this.C;
    }

    public final a4.c getConfigurationChangeObserver() {
        return this.f5419z;
    }

    public final t0.c getContentCaptureManager$ui_release() {
        return this.f5401q;
    }

    public t3.h getCoroutineContext() {
        return this.f5385i;
    }

    public k2.b getDensity() {
        return (k2.b) this.f5381g.getValue();
    }

    public u0.a getDragAndDropManager() {
        return this.f5387j;
    }

    public w0.g getFocusOwner() {
        return this.f5383h;
    }

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        p3.n nVar;
        x0.d dVarU = u();
        if (dVarU != null) {
            rect.left = Math.round(dVarU.f6534a);
            rect.top = Math.round(dVarU.f6535b);
            rect.right = Math.round(dVarU.f6536c);
            rect.bottom = Math.round(dVarU.f6537d);
            nVar = p3.n.f5026a;
        } else {
            nVar = null;
        }
        if (nVar == null) {
            super.getFocusedRect(rect);
        }
    }

    public d2.d getFontFamilyResolver() {
        return (d2.d) this.f5386i0.getValue();
    }

    public d2.c getFontLoader() {
        return this.f5384h0;
    }

    public y0.a0 getGraphicsContext() {
        return this.f5405s;
    }

    public f1.a getHapticFeedBack() {
        return this.f5392l0;
    }

    public boolean getHasPendingMeasureOrLayout() {
        return this.J.f4946b.u();
    }

    public g1.b getInputModeManager() {
        return this.f5394m0;
    }

    public final long getLastMatrixRecalculationAnimationTime$ui_release() {
        return this.Q;
    }

    @Override // android.view.View, android.view.ViewParent
    public k2.k getLayoutDirection() {
        return (k2.k) this.f5390k0.getValue();
    }

    public long getMeasureIteration() {
        p1.p0 p0Var = this.J;
        if (p0Var.f4947c) {
            return p0Var.f4951g;
        }
        d4.a.K("measureIteration should be only used during the measure/layout pass");
        throw null;
    }

    public o1.d getModifierLocalManager() {
        return this.f5396n0;
    }

    public n1.l0 getPlacementScope() {
        int i5 = n1.o0.f4144b;
        return new n1.c0(1, this);
    }

    public j1.r getPointerIconService() {
        return this.A0;
    }

    public p1.d0 getRoot() {
        return this.f5393m;
    }

    public p1.k1 getRootForTest() {
        return this.f5395n;
    }

    public final boolean getScrollCaptureInProgress$ui_release() {
        v1.i iVar;
        if (Build.VERSION.SDK_INT < 31 || (iVar = this.f5420z0) == null) {
            return false;
        }
        return ((Boolean) iVar.f6204a.getValue()).booleanValue();
    }

    public w1.o getSemanticsOwner() {
        return this.f5397o;
    }

    public p1.f0 getSharedDrawScope() {
        return this.f5379f;
    }

    public boolean getShowLayoutBounds() {
        return this.E;
    }

    public p1.g1 getSnapshotObserver() {
        return this.D;
    }

    public k2 getSoftwareKeyboardController() {
        return this.f5382g0;
    }

    public e2.y getTextInputService() {
        return this.f5378e0;
    }

    public l2 getTextToolbar() {
        return this.f5398o0;
    }

    public View getView() {
        return this;
    }

    public o2 getViewConfiguration() {
        return this.K;
    }

    public final m getViewTreeOwners() {
        return (m) this.V.getValue();
    }

    public u2 getWindowInfo() {
        return this.f5389k;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00b2  */
    public final int k(MotionEvent motionEvent) {
        int actionMasked;
        u uVar;
        float[] fArr = this.O;
        removeCallbacks(this.f5408t0);
        try {
            this.Q = AnimationUtils.currentAnimationTimeMillis();
            this.f5416x0.b(this, fArr);
            m0.o(fArr, this.P);
            long jB = y0.f0.b(p3.j.b(motionEvent.getX(), motionEvent.getY()), fArr);
            this.S = p3.j.b(motionEvent.getRawX() - x0.c.d(jB), motionEvent.getRawY() - x0.c.e(jB));
            boolean z2 = true;
            this.R = true;
            r(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked2 = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.f5400p0;
                boolean z3 = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                b0.a0 a0Var = this.f5417y;
                if (motionEvent2 != null) {
                    try {
                        if ((motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) ? false : true) {
                            if (motionEvent2.getButtonState() != 0 || (actionMasked = motionEvent2.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6) {
                                a0Var.d();
                            } else if (motionEvent2.getActionMasked() != 10 && z3) {
                                E(motionEvent2, 10, motionEvent2.getEventTime(), true);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        Trace.endSection();
                        throw th;
                    }
                }
                boolean z4 = motionEvent.getToolType(0) == 3;
                if (z3 || !z4 || actionMasked2 == 3 || actionMasked2 == 9 || !o(motionEvent)) {
                    uVar = this;
                } else {
                    uVar = this;
                    uVar.E(motionEvent, 9, motionEvent.getEventTime(), true);
                }
                if (motionEvent2 != 0) {
                    motionEvent2.recycle();
                }
                MotionEvent motionEvent3 = uVar.f5400p0;
                if (motionEvent3 != null && motionEvent3.getAction() == 10) {
                    MotionEvent motionEvent4 = uVar.f5400p0;
                    int pointerId = motionEvent4 != null ? motionEvent4.getPointerId(0) : -1;
                    int action = motionEvent.getAction();
                    j1.e eVar = uVar.f5415x;
                    if (action == 9 && motionEvent.getHistorySize() == 0) {
                        if (pointerId >= 0) {
                            eVar.f3243c.delete(pointerId);
                            eVar.f3242b.delete(pointerId);
                        }
                    } else if (motionEvent.getAction() == 0 && motionEvent.getHistorySize() == 0) {
                        MotionEvent motionEvent5 = uVar.f5400p0;
                        float x4 = motionEvent5 != null ? motionEvent5.getX() : Float.NaN;
                        MotionEvent motionEvent6 = uVar.f5400p0;
                        boolean z5 = (x4 == motionEvent.getX() && (motionEvent6 != null ? motionEvent6.getY() : Float.NaN) == motionEvent.getY()) ? false : true;
                        MotionEvent motionEvent7 = uVar.f5400p0;
                        if ((motionEvent7 != null ? motionEvent7.getEventTime() : -1L) == motionEvent.getEventTime()) {
                            z2 = false;
                        }
                        if (z5 || z2) {
                            if (pointerId >= 0) {
                                eVar.f3243c.delete(pointerId);
                                eVar.f3242b.delete(pointerId);
                            }
                            ((j1.h) ((a1.b) a0Var.f375c).f24e).f3256a.g();
                        }
                    }
                }
                uVar.f5400p0 = MotionEvent.obtainNoHistory(motionEvent);
                int iD = D(motionEvent);
                Trace.endSection();
                uVar.R = false;
                return iD;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            this.R = false;
            throw th3;
        }
    }

    public final void m(p1.d0 d0Var) {
        int i5 = 0;
        this.J.l(d0Var, false);
        h0.e eVarU = d0Var.u();
        int i6 = eVarU.f2578f;
        if (i6 > 0) {
            Object[] objArr = eVarU.f2576d;
            do {
                m((p1.d0) objArr[i5]);
                i5++;
            } while (i5 < i6);
        }
    }

    public final boolean o(MotionEvent motionEvent) {
        float x4 = motionEvent.getX();
        float y4 = motionEvent.getY();
        return 0.0f <= x4 && x4 <= ((float) getWidth()) && 0.0f <= y4 && y4 <= ((float) getHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        androidx.lifecycle.q lifecycle;
        androidx.lifecycle.v vVar;
        super.onAttachedToWindow();
        this.f5389k.f5438a.setValue(Boolean.valueOf(hasWindowFocus()));
        m(getRoot());
        l(getRoot());
        getSnapshotObserver().f4835a.d();
        s0.a aVar = this.A;
        if (aVar != null) {
            s0.e.f5766a.a(aVar);
        }
        androidx.lifecycle.v vVarC = androidx.lifecycle.l0.c(this);
        j3.g gVarT = d4.a.t(this);
        m viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (vVarC != null && gVarT != null && (vVarC != (vVar = viewTreeOwners.f5259a) || gVarT != vVar))) {
            if (vVarC == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (gVarT == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycle = viewTreeOwners.f5259a.getLifecycle()) != null) {
                lifecycle.b(this);
            }
            vVarC.getLifecycle().a(this);
            m mVar = new m(vVarC, gVarT);
            set_viewTreeOwners(mVar);
            a4.c cVar = this.W;
            if (cVar != null) {
                cVar.c(mVar);
            }
            this.W = null;
        }
        this.f5394m0.f2513a.setValue(new g1.a(isInTouchMode() ? 1 : 2));
        m viewTreeOwners2 = getViewTreeOwners();
        androidx.lifecycle.q lifecycle2 = viewTreeOwners2 != null ? viewTreeOwners2.f5259a.getLifecycle() : null;
        if (lifecycle2 == null) {
            d4.a.M("No lifecycle owner exists");
            throw null;
        }
        lifecycle2.a(this);
        lifecycle2.a(this.f5401q);
        getViewTreeObserver().addOnGlobalLayoutListener(this.a0);
        getViewTreeObserver().addOnScrollChangedListener(this.f5373b0);
        getViewTreeObserver().addOnTouchModeChangeListener(this.f5374c0);
        if (Build.VERSION.SDK_INT >= 31) {
            j0.f5231a.b(this);
        }
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        r0.r rVar = (r0.r) this.f5380f0.get();
        r0 r0Var = (r0) (rVar != null ? rVar.f5695b : null);
        if (r0Var == null) {
            return this.f5376d0.f1978d;
        }
        r0.r rVar2 = (r0.r) r0Var.f5335g.get();
        t1 t1Var = (t1) (rVar2 != null ? rVar2.f5695b : null);
        return t1Var != null && (t1Var.f5371e ^ true);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setDensity(d4.a.a(getContext()));
        int i5 = Build.VERSION.SDK_INT;
        if ((i5 >= 31 ? configuration.fontWeightAdjustment : 0) != this.f5388j0) {
            this.f5388j0 = i5 >= 31 ? configuration.fontWeightAdjustment : 0;
            setFontFamilyResolver(d4.a.r(getContext()));
        }
        this.f5419z.c(configuration);
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        int i5;
        r0.r rVar = (r0.r) this.f5380f0.get();
        r0 r0Var = (r0) (rVar != null ? rVar.f5695b : null);
        if (r0Var == null) {
            e2.a0 a0Var = this.f5376d0;
            if (a0Var.f1978d) {
                e2.n nVar = a0Var.f1982h;
                e2.x xVar = a0Var.f1981g;
                int i6 = nVar.f2043e;
                boolean z2 = nVar.f2039a;
                int i7 = 4;
                if (i6 == 1) {
                    i5 = z2 ? 6 : 0;
                } else if (i6 == 0) {
                    i5 = 1;
                } else if (i6 == 2) {
                    i5 = 2;
                } else if (i6 == 6) {
                    i5 = 5;
                } else if (i6 == 5) {
                    i5 = 7;
                } else if (i6 == 3) {
                    i5 = 3;
                } else if (i6 == 4) {
                    i5 = 4;
                } else {
                    if (i6 != 7) {
                        throw new IllegalStateException("invalid ImeAction");
                    }
                }
                editorInfo.imeOptions = i5;
                int i8 = nVar.f2042d;
                if (i8 == 1) {
                    editorInfo.inputType = 1;
                } else if (i8 == 2) {
                    editorInfo.inputType = 1;
                    editorInfo.imeOptions = Integer.MIN_VALUE | i5;
                } else if (i8 == 3) {
                    editorInfo.inputType = 2;
                } else if (i8 == 4) {
                    editorInfo.inputType = 3;
                } else if (i8 == 5) {
                    editorInfo.inputType = 17;
                } else if (i8 == 6) {
                    editorInfo.inputType = 33;
                } else if (i8 == 7) {
                    editorInfo.inputType = 129;
                } else if (i8 == 8) {
                    editorInfo.inputType = 18;
                } else {
                    if (i8 != 9) {
                        throw new IllegalStateException("Invalid Keyboard Type");
                    }
                    editorInfo.inputType = 8194;
                }
                if (!z2) {
                    int i9 = editorInfo.inputType;
                    if ((i9 & 1) == 1) {
                        editorInfo.inputType = i9 | 131072;
                        if (i6 == 1) {
                            editorInfo.imeOptions |= 1073741824;
                        }
                    }
                }
                int i10 = editorInfo.inputType;
                if ((i10 & 1) == 1) {
                    int i11 = nVar.f2040b;
                    if (i11 == 1) {
                        editorInfo.inputType = i10 | 4096;
                    } else if (i11 == 2) {
                        editorInfo.inputType = i10 | 8192;
                    } else if (i11 == 3) {
                        editorInfo.inputType = i10 | 16384;
                    }
                    if (nVar.f2041c) {
                        editorInfo.inputType |= 32768;
                    }
                }
                long j5 = xVar.f2063b;
                int i12 = y1.g0.f6769c;
                editorInfo.initialSelStart = (int) (j5 >> 32);
                editorInfo.initialSelEnd = (int) (j5 & 4294967295L);
                q.f.s(editorInfo, xVar.f2062a.f6759d);
                editorInfo.imeOptions |= 33554432;
                if (b3.j.c()) {
                    b3.j.a().f(editorInfo);
                }
                e2.t tVar = new e2.t(a0Var.f1981g, new a1.d(i7, a0Var), a0Var.f1982h.f2041c);
                a0Var.f1983i.add(new WeakReference(tVar));
                return tVar;
            }
        } else {
            r0.r rVar2 = (r0.r) r0Var.f5335g.get();
            t1 t1Var = (t1) (rVar2 != null ? rVar2.f5695b : null);
            if (t1Var != null) {
                synchronized (t1Var.f5369c) {
                    if (t1Var.f5371e) {
                        return null;
                    }
                    x.w wVarA = t1Var.f5367a.a(editorInfo);
                    c0.g1 g1Var = new c0.g1(22, t1Var);
                    InputConnection pVar = Build.VERSION.SDK_INT >= 34 ? new e2.p(wVarA, g1Var) : new e2.o(wVarA, g1Var);
                    t1Var.f5370d.b(new WeakReference(pVar));
                    return pVar;
                }
            }
        }
        return null;
    }

    @Override // android.view.View
    public final void onCreateVirtualViewTranslationRequests(long[] jArr, int[] iArr, Consumer consumer) {
        t0.c cVar = this.f5401q;
        cVar.getClass();
        t0.a.f5777a.b(cVar, jArr, iArr, consumer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p0.t tVar = getSnapshotObserver().f4835a;
        y0.x xVar = tVar.f4752h;
        if (xVar != null) {
            xVar.a();
        }
        tVar.a();
        m viewTreeOwners = getViewTreeOwners();
        androidx.lifecycle.q lifecycle = viewTreeOwners != null ? viewTreeOwners.f5259a.getLifecycle() : null;
        if (lifecycle == null) {
            d4.a.M("No lifecycle owner exists");
            throw null;
        }
        lifecycle.b(this.f5401q);
        lifecycle.b(this);
        s0.a aVar = this.A;
        if (aVar != null) {
            s0.e.f5766a.b(aVar);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.a0);
        getViewTreeObserver().removeOnScrollChangedListener(this.f5373b0);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.f5374c0);
        if (Build.VERSION.SDK_INT >= 31) {
            j0.f5231a.a(this);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z2, int i5, Rect rect) {
        super.onFocusChanged(z2, i5, rect);
        if (z2 || hasFocus()) {
            return;
        }
        androidx.compose.ui.focus.b bVar = (androidx.compose.ui.focus.b) getFocusOwner();
        w0.r rVar = bVar.f179f;
        w0.s sVar = bVar.f181h;
        if (sVar.f6266b) {
            w0.d.e(rVar, true);
            return;
        }
        try {
            sVar.f6266b = true;
            w0.d.e(rVar, true);
        } finally {
            w0.s.b(sVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
        this.J.g(this.f5414w0);
        this.H = null;
        G();
        if (this.F != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i7 - i5, i8 - i6);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        p1.p0 p0Var = this.J;
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                m(getRoot());
            }
            long jI = i(i5);
            long jI2 = i(i6);
            long jB = h1.c.b((int) (jI >>> 32), (int) (jI & 4294967295L), (int) (jI2 >>> 32), (int) (4294967295L & jI2));
            k2.a aVar = this.H;
            if (aVar == null) {
                this.H = new k2.a(jB);
                this.I = false;
            } else if (!k2.a.c(aVar.f3400a, jB)) {
                this.I = true;
            }
            p0Var.m(jB);
            p0Var.h();
            setMeasuredDimension(getRoot().f4802z.f4910r.f4130d, getRoot().f4802z.f4910r.f4131e);
            if (this.F != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().f4802z.f4910r.f4130d, 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().f4802z.f4910r.f4131e, 1073741824));
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i5) {
        s0.a aVar;
        if (viewStructure == null || (aVar = this.A) == null) {
            return;
        }
        s0.f fVar = aVar.f5762b;
        int size = fVar.f5767a.size();
        s0.c cVar = s0.c.f5764a;
        int iA = cVar.a(viewStructure, size);
        for (Map.Entry entry : fVar.f5767a.entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            if (entry.getValue() != null) {
                throw new ClassCastException();
            }
            ViewStructure viewStructureB = cVar.b(viewStructure, iA);
            if (viewStructureB != null) {
                s0.d dVar = s0.d.f5765a;
                AutofillId autofillIdA = dVar.a(viewStructure);
                b4.l.b(autofillIdA);
                dVar.g(viewStructureB, autofillIdA, iIntValue);
                cVar.d(viewStructureB, iIntValue, aVar.f5761a.getContext().getPackageName(), null, null);
                dVar.h(viewStructureB, 1);
                throw null;
            }
            iA++;
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i5) {
        k2.k kVar;
        if (this.f5377e) {
            k2.k kVar2 = k2.k.f3416d;
            if (i5 != 0) {
                kVar = i5 != 1 ? null : k2.k.f3417e;
            } else {
                kVar = kVar2;
            }
            if (kVar != null) {
                kVar2 = kVar;
            }
            setLayoutDirection(kVar2);
        }
    }

    @Override // android.view.View
    public final void onScrollCaptureSearch(Rect rect, Point point, Consumer consumer) {
        v1.i iVar;
        if (Build.VERSION.SDK_INT < 31 || (iVar = this.f5420z0) == null) {
            return;
        }
        iVar.a(this, getSemanticsOwner(), getCoroutineContext(), consumer);
    }

    @Override // android.view.View
    public final void onVirtualViewTranslationResponses(LongSparseArray longSparseArray) {
        t0.c cVar = this.f5401q;
        cVar.getClass();
        t0.a.f5777a.c(cVar, longSparseArray);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z2) {
        boolean zA;
        this.f5389k.f5438a.setValue(Boolean.valueOf(z2));
        this.f5418y0 = true;
        super.onWindowFocusChanged(z2);
        if (!z2 || getShowLayoutBounds() == (zA = s1.a())) {
            return;
        }
        setShowLayoutBounds(zA);
        l(getRoot());
    }

    public final boolean p(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        return (motionEvent.getPointerCount() == 1 && (motionEvent2 = this.f5400p0) != null && motionEvent2.getPointerCount() == motionEvent.getPointerCount() && motionEvent.getRawX() == motionEvent2.getRawX() && motionEvent.getRawY() == motionEvent2.getRawY()) ? false : true;
    }

    public final long q(long j5) {
        z();
        long jB = y0.f0.b(j5, this.O);
        return p3.j.b(x0.c.d(this.S) + x0.c.d(jB), x0.c.e(this.S) + x0.c.e(jB));
    }

    public final void r(boolean z2) {
        r rVar;
        p1.p0 p0Var = this.J;
        if (p0Var.f4946b.u() || ((h0.e) p0Var.f4949e.f49e).k()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (z2) {
                try {
                    rVar = this.f5414w0;
                } finally {
                    Trace.endSection();
                }
            } else {
                rVar = null;
            }
            if (p0Var.g(rVar)) {
                requestLayout();
            }
            p0Var.a(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i5, Rect rect) {
        if (isFocused()) {
            return true;
        }
        int iOrdinal = ((androidx.compose.ui.focus.b) getFocusOwner()).f179f.z0().ordinal();
        if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
            return super.requestFocus(i5, rect);
        }
        if (iOrdinal != 3) {
            throw new i4.p();
        }
        if (isInTouchMode()) {
            return false;
        }
        w0.b bVarI = w0.d.I(i5);
        int i6 = bVarI != null ? bVarI.f6225a : 7;
        Boolean boolC = ((androidx.compose.ui.focus.b) getFocusOwner()).c(i6, rect != null ? new x0.d(rect.left, rect.top, rect.right, rect.bottom) : null, new n(i6));
        if (boolC != null) {
            return boolC.booleanValue();
        }
        return false;
    }

    public final void s(p1.d1 d1Var, boolean z2) {
        ArrayList arrayList = this.f5409u;
        if (!z2) {
            if (this.f5413w) {
                return;
            }
            arrayList.remove(d1Var);
            ArrayList arrayList2 = this.f5411v;
            if (arrayList2 != null) {
                arrayList2.remove(d1Var);
                return;
            }
            return;
        }
        if (!this.f5413w) {
            arrayList.add(d1Var);
            return;
        }
        ArrayList arrayList3 = this.f5411v;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList();
            this.f5411v = arrayList3;
        }
        arrayList3.add(d1Var);
    }

    public void setAccessibilityEventBatchIntervalMillis(long j5) {
        this.f5399p.f5182h = j5;
    }

    public final void setConfigurationChangeObserver(a4.c cVar) {
        this.f5419z = cVar;
    }

    public final void setContentCaptureManager$ui_release(t0.c cVar) {
        this.f5401q = cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public void setCoroutineContext(t3.h hVar) {
        int i5;
        int i6;
        this.f5385i = hVar;
        r0.o oVar = getRoot().f4801y.f4972e;
        if (oVar instanceof j1.d0) {
            ((j1.d0) oVar).A0();
        }
        r0.o oVar2 = oVar.f5681d;
        if (!oVar2.f5693p) {
            d4.a.L("visitSubtree called on an unattached node");
            throw null;
        }
        r0.o oVar3 = oVar2.f5686i;
        p1.d0 d0VarS = p1.f.s(oVar);
        int[] iArrCopyOf = new int[16];
        h0.e[] eVarArr = new h0.e[16];
        int i7 = 0;
        while (d0VarS != null) {
            if (oVar3 == null) {
                oVar3 = d0VarS.f4801y.f4972e;
            }
            if ((oVar3.f5684g & 16) != 0) {
                while (oVar3 != null) {
                    if ((oVar3.f5683f & 16) != 0) {
                        ?? E = oVar3;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof p1.j1) {
                                p1.j1 j1Var = (p1.j1) E;
                                if (j1Var instanceof j1.d0) {
                                    ((j1.d0) j1Var).A0();
                                }
                            } else if ((E.f5683f & 16) != 0 && (E instanceof p1.m)) {
                                r0.o oVar4 = ((p1.m) E).f4921r;
                                int i8 = 0;
                                while (oVar4 != null) {
                                    if ((oVar4.f5683f & 16) != 0) {
                                        i8++;
                                        if (i8 == 1) {
                                            E = E;
                                            eVar = eVar;
                                            eVar = eVar;
                                            E = oVar4;
                                        } else {
                                            if (eVar == 0) {
                                                eVar = new h0.e(new r0.o[16]);
                                            }
                                            if (E != 0) {
                                                eVar.b(E);
                                                E = 0;
                                            }
                                            eVar.b(oVar4);
                                        }
                                    } else {
                                        E = E;
                                        eVar = eVar;
                                    }
                                    oVar4 = oVar4.f5686i;
                                    E = E;
                                    eVar = eVar;
                                }
                                if (i8 == 1) {
                                    E = E;
                                    eVar = eVar;
                                } else {
                                    E = E;
                                    eVar = eVar;
                                }
                            }
                            E = p1.f.e(eVar);
                        }
                    }
                    oVar3 = oVar3.f5686i;
                }
            }
            h0.e eVarU = d0VarS.u();
            if (!eVarU.j()) {
                if (i7 >= iArrCopyOf.length) {
                    iArrCopyOf = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length * 2);
                    b4.l.d("copyOf(this, newSize)", iArrCopyOf);
                    Object[] objArrCopyOf = Arrays.copyOf(eVarArr, eVarArr.length * 2);
                    b4.l.d("copyOf(this, newSize)", objArrCopyOf);
                    eVarArr = (h0.e[]) objArrCopyOf;
                }
                iArrCopyOf[i7] = eVarU.f2578f - 1;
                eVarArr[i7] = eVarU;
                i7++;
            }
            if (i7 <= 0 || (i6 = iArrCopyOf[(i5 = i7 - 1)]) < 0) {
                d0VarS = null;
            } else {
                if (i7 <= 0) {
                    throw new IllegalStateException("Cannot call pop() on an empty stack. Guard with a call to isNotEmpty()");
                }
                h0.e eVar2 = eVarArr[i5];
                b4.l.b(eVar2);
                if (i6 > 0) {
                    iArrCopyOf[i5] = iArrCopyOf[i5] - 1;
                } else if (i6 == 0) {
                    eVarArr[i5] = null;
                    i7--;
                }
                d0VarS = (p1.d0) eVar2.f2576d[i6];
            }
            oVar3 = null;
        }
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j5) {
        this.Q = j5;
    }

    public final void setOnViewTreeOwnersAvailable(a4.c cVar) {
        m viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            cVar.c(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.W = cVar;
    }

    public void setShowLayoutBounds(boolean z2) {
        this.E = z2;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public final void t() {
        if (this.B) {
            p0.t tVar = getSnapshotObserver().f4835a;
            synchronized (tVar.f4751g) {
                try {
                    h0.e eVar = tVar.f4750f;
                    int i5 = eVar.f2578f;
                    int i6 = 0;
                    for (int i7 = 0; i7 < i5; i7++) {
                        p0.s sVar = (p0.s) eVar.f2576d[i7];
                        sVar.c();
                        if (!(sVar.f4737f.f3180e != 0)) {
                            i6++;
                        } else if (i6 > 0) {
                            Object[] objArr = eVar.f2576d;
                            objArr[i7 - i6] = objArr[i7];
                        }
                    }
                    int i8 = i5 - i6;
                    q3.k.E(eVar.f2576d, i8, i5);
                    eVar.f2578f = i8;
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.B = false;
        }
        b1 b1Var = this.F;
        if (b1Var != null) {
            h(b1Var);
        }
        while (this.f5406s0.k()) {
            int i9 = this.f5406s0.f2578f;
            for (int i10 = 0; i10 < i9; i10++) {
                Object[] objArr2 = this.f5406s0.f2576d;
                a4.a aVar = (a4.a) objArr2[i10];
                objArr2[i10] = null;
                if (aVar != null) {
                    aVar.a();
                }
            }
            this.f5406s0.n(0, i9);
        }
    }

    public final x0.d u() {
        if (isFocused()) {
            w0.r rVarG = w0.d.g(((androidx.compose.ui.focus.b) getFocusOwner()).f179f);
            if (rVarG != null) {
                return w0.d.j(rVarG);
            }
            return null;
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus != null) {
            return w0.d.d(viewFindFocus);
        }
        return null;
    }

    public final void v(p1.d0 d0Var) {
        f0 f0Var = this.f5399p;
        f0Var.f5199y = true;
        if (f0Var.q()) {
            f0Var.s(d0Var);
        }
        t0.c cVar = this.f5401q;
        cVar.f5790k = true;
        if (cVar.g() && cVar.f5791l.add(d0Var)) {
            cVar.f5792m.o(p3.n.f5026a);
        }
    }

    public final void w(p1.d0 d0Var, boolean z2, boolean z3) {
        p1.d0 d0VarR;
        p1.d0 d0VarR2;
        p1.h0 h0Var;
        p1.e0 e0Var;
        p1.p0 p0Var = this.J;
        if (!z2) {
            if (p0Var.l(d0Var, z3)) {
                B(d0Var);
                return;
            }
            return;
        }
        a2.h hVar = p0Var.f4946b;
        p1.d0 d0Var2 = d0Var.f4782f;
        p1.j0 j0Var = d0Var.f4802z;
        if (d0Var2 == null) {
            d4.a.L("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope");
            throw null;
        }
        int iC = m.h.c(j0Var.f4895c);
        if (iC != 0) {
            if (iC == 1) {
                return;
            }
            if (iC != 2 && iC != 3) {
                if (iC != 4) {
                    throw new i4.p();
                }
                if (!j0Var.f4899g || z3) {
                    j0Var.f4899g = true;
                    j0Var.f4896d = true;
                    if (d0Var.G) {
                        return;
                    }
                    if ((b4.l.a(d0Var.F(), Boolean.TRUE) || (j0Var.f4899g && (d0Var.p() == 1 || !((h0Var = j0Var.f4911s) == null || (e0Var = h0Var.f4859t) == null || !e0Var.e())))) && ((d0VarR = d0Var.r()) == null || !d0VarR.f4802z.f4899g)) {
                        hVar.o(d0Var, true);
                    } else if ((d0Var.E() || (j0Var.f4896d && p1.p0.f(d0Var))) && ((d0VarR2 = d0Var.r()) == null || !d0VarR2.f4802z.f4896d)) {
                        hVar.o(d0Var, false);
                    }
                    if (p0Var.f4948d) {
                        return;
                    }
                    B(d0Var);
                    return;
                }
                return;
            }
        }
        p0Var.f4952h.b(new p1.o0(d0Var, true, z3));
    }

    public final void x(p1.d0 d0Var, boolean z2, boolean z3) {
        p1.j0 j0Var = d0Var.f4802z;
        p1.p0 p0Var = this.J;
        if (!z2) {
            p0Var.getClass();
            int iC = m.h.c(j0Var.f4895c);
            if (iC == 0 || iC == 1 || iC == 2 || iC == 3) {
                return;
            }
            if (iC != 4) {
                throw new i4.p();
            }
            if (!z3 && d0Var.E() == j0Var.f4910r.f4885v && (j0Var.f4896d || j0Var.f4897e)) {
                return;
            }
            j0Var.f4897e = true;
            j0Var.f4898f = true;
            if (!d0Var.G && j0Var.f4910r.f4885v) {
                p1.d0 d0VarR = d0Var.r();
                if ((d0VarR == null || !d0VarR.f4802z.f4897e) && (d0VarR == null || !d0VarR.f4802z.f4896d)) {
                    p0Var.f4946b.o(d0Var, false);
                }
                if (p0Var.f4948d) {
                    return;
                }
                B(null);
                return;
            }
            return;
        }
        a2.h hVar = p0Var.f4946b;
        int iC2 = m.h.c(j0Var.f4895c);
        if (iC2 != 0) {
            if (iC2 == 1) {
                return;
            }
            if (iC2 != 2) {
                if (iC2 == 3) {
                    return;
                }
                if (iC2 != 4) {
                    throw new i4.p();
                }
            }
        }
        if ((j0Var.f4899g || j0Var.f4900h) && !z3) {
            return;
        }
        j0Var.f4900h = true;
        j0Var.f4901i = true;
        j0Var.f4897e = true;
        j0Var.f4898f = true;
        if (d0Var.G) {
            return;
        }
        p1.d0 d0VarR2 = d0Var.r();
        if (b4.l.a(d0Var.F(), Boolean.TRUE) && ((d0VarR2 == null || !d0VarR2.f4802z.f4899g) && (d0VarR2 == null || !d0VarR2.f4802z.f4900h))) {
            hVar.o(d0Var, true);
        } else if (d0Var.E() && ((d0VarR2 == null || !d0VarR2.f4802z.f4897e) && (d0VarR2 == null || !d0VarR2.f4802z.f4896d))) {
            hVar.o(d0Var, false);
        }
        if (p0Var.f4948d) {
            return;
        }
        B(null);
    }

    public final void y() {
        f0 f0Var = this.f5399p;
        f0Var.f5199y = true;
        if (f0Var.q() && !f0Var.J) {
            f0Var.J = true;
            f0Var.f5186l.post(f0Var.K);
        }
        t0.c cVar = this.f5401q;
        cVar.f5790k = true;
        if (!cVar.g() || cVar.f5798s) {
            return;
        }
        cVar.f5798s = true;
        cVar.f5793n.post(cVar.f5799t);
    }

    public final void z() {
        if (this.R) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.Q) {
            this.Q = jCurrentAnimationTimeMillis;
            d1 d1Var = this.f5416x0;
            float[] fArr = this.O;
            d1Var.b(this, fArr);
            m0.o(fArr, this.P);
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            int[] iArr = this.M;
            view.getLocationOnScreen(iArr);
            float f5 = iArr[0];
            float f6 = iArr[1];
            view.getLocationInWindow(iArr);
            this.S = p3.j.b(f5 - iArr[0], f6 - iArr[1]);
        }
    }
}
