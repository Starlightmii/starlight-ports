package q1;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import io.github.andrea_lyz.assistrestore.R;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakReference f5097d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public IBinder f5098e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f3 f5099f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f0.b0 f5100g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public n.i f5101h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5102i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5103j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5104k;

    public a(Context context) {
        super(context, null, 0);
        setClipChildren(false);
        setClipToPadding(false);
        x xVar = new x(1, this);
        addOnAttachStateChangeListener(xVar);
        e2.f0 f0Var = new e2.f0(3);
        h1.c.v(this).f51a.add(f0Var);
        this.f5101h = new n.i(this, xVar, f0Var, 4);
    }

    private static /* synthetic */ void getDisposeViewCompositionStrategy$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    private final void setParentContext(f0.b0 b0Var) {
        if (this.f5100g != b0Var) {
            this.f5100g = b0Var;
            if (b0Var != null) {
                this.f5097d = null;
            }
            f3 f3Var = this.f5099f;
            if (f3Var != null) {
                f3Var.e();
                this.f5099f = null;
                if (isAttachedToWindow()) {
                    c();
                }
            }
        }
    }

    private final void setPreviousAttachedWindowToken(IBinder iBinder) {
        if (this.f5098e != iBinder) {
            this.f5098e = iBinder;
            this.f5097d = null;
        }
    }

    public abstract void a(f0.p pVar, int i5);

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        b();
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5) {
        b();
        super.addView(view, i5);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5, int i6) {
        b();
        super.addView(view, i5, i6);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        b();
        super.addView(view, i5, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        b();
        super.addView(view, layoutParams);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        b();
        return super.addViewInLayout(view, i5, layoutParams);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i5, ViewGroup.LayoutParams layoutParams, boolean z2) {
        b();
        return super.addViewInLayout(view, i5, layoutParams, z2);
    }

    public final void b() {
        if (this.f5103j) {
            return;
        }
        throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
    }

    public final void c() {
        if (this.f5099f == null) {
            try {
                this.f5103j = true;
                this.f5099f = h3.a(this, f(), new n0.d(-656146368, true, new c0.j1(5, this)));
            } finally {
                this.f5103j = false;
            }
        }
    }

    public void d(boolean z2, int i5, int i6, int i7, int i8) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (i7 - i5) - getPaddingRight(), (i8 - i6) - getPaddingBottom());
        }
    }

    public void e(int i5, int i6) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.onMeasure(i5, i6);
            return;
        }
        childAt.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i5) - getPaddingLeft()) - getPaddingRight()), View.MeasureSpec.getMode(i5)), View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i6) - getPaddingTop()) - getPaddingBottom()), View.MeasureSpec.getMode(i6)));
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + childAt.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight());
    }

    public final f0.b0 f() {
        f0.l2 l2Var;
        t3.h hVar;
        f0.g gVar;
        f0.b0 b0VarB = this.f5100g;
        if (b0VarB == null) {
            b0VarB = c3.b(this);
            if (b0VarB == null) {
                for (ViewParent parent = getParent(); b0VarB == null && (parent instanceof View); parent = parent.getParent()) {
                    b0VarB = c3.b((View) parent);
                }
            }
            boolean z2 = false;
            if (b0VarB != null) {
                f0.b0 b0Var = (!(b0VarB instanceof f0.l2) || ((f0.g2) ((f0.l2) b0VarB).f2263u.getValue()).compareTo(f0.g2.f2178e) > 0) ? b0VarB : null;
                if (b0Var != null) {
                    this.f5097d = new WeakReference(b0Var);
                }
            } else {
                b0VarB = null;
            }
            if (b0VarB == null) {
                WeakReference weakReference = this.f5097d;
                if (weakReference == null || (b0VarB = (f0.b0) weakReference.get()) == null || ((b0VarB instanceof f0.l2) && ((f0.g2) ((f0.l2) b0VarB).f2263u.getValue()).compareTo(f0.g2.f2178e) <= 0)) {
                    b0VarB = null;
                }
                if (b0VarB == null) {
                    if (!isAttachedToWindow()) {
                        d4.a.L("Cannot locate windowRecomposer; View " + this + " is not attached to a window");
                        throw null;
                    }
                    View view = this;
                    Object parent2 = getParent();
                    while (parent2 instanceof View) {
                        View view2 = (View) parent2;
                        if (view2.getId() == 16908290) {
                            break;
                        }
                        view = view2;
                        parent2 = view2.getParent();
                    }
                    f0.b0 b0VarB2 = c3.b(view);
                    if (b0VarB2 == null) {
                        ((w2) x2.f5458a.get()).getClass();
                        t3.h hVar2 = t3.i.f5807d;
                        p3.k kVar = v0.f5424p;
                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            hVar = (t3.h) v0.f5424p.getValue();
                        } else {
                            hVar = (t3.h) v0.f5425q.get();
                            if (hVar == null) {
                                throw new IllegalStateException("no AndroidUiDispatcher for this thread");
                            }
                        }
                        t3.h hVarK = hVar.k(hVar2);
                        f0.g gVar2 = (f0.g) hVarK.j(f0.h.f2185f);
                        if (gVar2 != null) {
                            gVar = new f0.g(gVar2);
                            f0.c1 c1Var = (f0.c1) gVar.f2174f;
                            synchronized (c1Var.f2103a) {
                                c1Var.f2106d = false;
                            }
                        } else {
                            gVar = null;
                        }
                        b4.w wVar = new b4.w();
                        t3.h v1Var = (r0.q) hVarK.j(r0.b.f5668s);
                        if (v1Var == null) {
                            v1Var = new v1();
                            wVar.f697d = v1Var;
                        }
                        if (gVar != null) {
                            hVar2 = gVar;
                        }
                        t3.h hVarK2 = hVarK.k(hVar2).k(v1Var);
                        l2Var = new f0.l2(hVarK2);
                        synchronized (l2Var.f2245c) {
                            l2Var.f2262t = true;
                        }
                        n4.c cVarA = i4.y.a(hVarK2);
                        androidx.lifecycle.v vVarC = androidx.lifecycle.l0.c(view);
                        androidx.lifecycle.q lifecycle = vVarC != null ? vVarC.getLifecycle() : null;
                        if (lifecycle == null) {
                            d4.a.M("ViewTreeLifecycleOwner not found from " + view);
                            throw null;
                        }
                        view.addOnAttachStateChangeListener(new y2(view, l2Var));
                        lifecycle.a(new a3(cVarA, gVar, l2Var, wVar, view));
                        view.setTag(R.id.androidx_compose_ui_view_composition_context, l2Var);
                        Handler handler = view.getHandler();
                        int i5 = j4.e.f3352a;
                        t3.h hVar3 = new j4.d(handler, "windowRecomposer cleanup", false).f3351i;
                        b0.t tVar = new b0.t(l2Var, view, z2 ? 1 : 0, 14);
                        i4.w wVar2 = i4.w.f2782g;
                        if ((2 & 1) != 0) {
                            hVar3 = t3.i.f5807d;
                        }
                        int i6 = 2;
                        if ((2 & 2) != 0) {
                            wVar2 = i4.w.f2779d;
                        }
                        t3.h hVarF = i4.y.f(t3.i.f5807d, hVar3, true);
                        p4.e eVar = i4.e0.f2727a;
                        if (hVarF != eVar && hVarF.j(t3.d.f5806d) == null) {
                            hVarF = hVarF.k(eVar);
                        }
                        i4.a b1Var = wVar2 == i4.w.f2780e ? new i4.b1(hVarF, tVar) : new i4.h1(hVarF, true);
                        b1Var.c0(wVar2, b1Var, tVar);
                        view.addOnAttachStateChangeListener(new x(i6, b1Var));
                    } else {
                        if (!(b0VarB2 instanceof f0.l2)) {
                            throw new IllegalStateException("root viewTreeParentCompositionContext is not a Recomposer");
                        }
                        l2Var = (f0.l2) b0VarB2;
                    }
                    f0.l2 l2Var2 = ((f0.g2) l2Var.f2263u.getValue()).compareTo(f0.g2.f2178e) > 0 ? l2Var : null;
                    if (l2Var2 != null) {
                        this.f5097d = new WeakReference(l2Var2);
                    }
                    return l2Var;
                }
            }
        }
        return b0VarB;
    }

    public final boolean getHasComposition() {
        return this.f5099f != null;
    }

    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return true;
    }

    public final boolean getShowLayoutBounds() {
        return this.f5102i;
    }

    @Override // android.view.ViewGroup
    public final boolean isTransitionGroup() {
        return !this.f5104k || super.isTransitionGroup();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPreviousAttachedWindowToken(getWindowToken());
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
        d(z2, i5, i6, i7, i8);
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        c();
        e(i5, i6);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i5) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        childAt.setLayoutDirection(i5);
    }

    public final void setParentCompositionContext(f0.b0 b0Var) {
        setParentContext(b0Var);
    }

    public final void setShowLayoutBounds(boolean z2) {
        this.f5102i = z2;
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt != null) {
            ((u) ((p1.e1) childAt)).setShowLayoutBounds(z2);
        }
    }

    @Override // android.view.ViewGroup
    public void setTransitionGroup(boolean z2) {
        super.setTransitionGroup(z2);
        this.f5104k = true;
    }

    public final void setViewCompositionStrategy(n2 n2Var) {
        n.i iVar = this.f5101h;
        if (iVar != null) {
            iVar.a();
        }
        ((m0) n2Var).getClass();
        x xVar = new x(1, this);
        addOnAttachStateChangeListener(xVar);
        e2.f0 f0Var = new e2.f0(3);
        h1.c.v(this).f51a.add(f0Var);
        this.f5101h = new n.i(this, xVar, f0Var, 4);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
