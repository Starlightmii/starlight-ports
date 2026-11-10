package n2;

import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.l0;
import c0.j1;
import f0.b2;
import f0.n0;
import f0.s1;
import f0.v;
import io.github.andrea_lyz.assistrestore.R;
import java.util.UUID;
import y0.x;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends q1.a {
    public Object A;
    public final s1 B;
    public boolean C;
    public final int[] D;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a4.a f4236l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public t f4237m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f4238n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final View f4239o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final r f4240p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final WindowManager f4241q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final WindowManager.LayoutParams f4242r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public s f4243s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k2.k f4244t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final s1 f4245u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final s1 f4246v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public k2.i f4247w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final n0 f4248x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f4249y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final p0.t f4250z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(a4.a aVar, t tVar, String str, View view, k2.b bVar, s sVar, UUID uuid) {
        super(view.getContext());
        r qVar = Build.VERSION.SDK_INT >= 29 ? new q() : new r();
        this.f4236l = aVar;
        this.f4237m = tVar;
        this.f4238n = str;
        this.f4239o = view;
        this.f4240p = qVar;
        Object systemService = view.getContext().getSystemService("window");
        b4.l.c("null cannot be cast to non-null type android.view.WindowManager", systemService);
        this.f4241q = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        t tVar2 = this.f4237m;
        boolean zB = j.b(view);
        boolean z2 = tVar2.f4252b;
        int i5 = tVar2.f4251a;
        if (z2 && zB) {
            i5 |= 8192;
        } else if (z2 && !zB) {
            i5 &= -8193;
        }
        layoutParams.flags = i5;
        layoutParams.type = 1002;
        layoutParams.token = view.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(view.getContext().getResources().getString(R.string.default_popup_window_title));
        this.f4242r = layoutParams;
        this.f4243s = sVar;
        this.f4244t = k2.k.f3416d;
        this.f4245u = f0.l.s(null);
        this.f4246v = f0.l.s(null);
        this.f4248x = f0.l.n(new b0.a(14, this));
        this.f4249y = new Rect();
        this.f4250z = new p0.t(new f(this, 2));
        setId(android.R.id.content);
        setTag(R.id.view_tree_lifecycle_owner, l0.c(view));
        setTag(R.id.view_tree_view_model_store_owner, l0.d(view));
        setTag(R.id.view_tree_saved_state_registry_owner, d4.a.t(view));
        setTag(R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(bVar.v((float) 8));
        setOutlineProvider(new b1.n(1));
        this.B = f0.l.s(m.f4228a);
        this.D = new int[2];
    }

    private final a4.e getContent() {
        return (a4.e) this.B.getValue();
    }

    private final int getDisplayHeight() {
        return Math.round(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density);
    }

    private final int getDisplayWidth() {
        return Math.round(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density);
    }

    public static /* synthetic */ void getParams$ui_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final n1.p getParentLayoutCoordinates() {
        return (n1.p) this.f4246v.getValue();
    }

    private final void setContent(a4.e eVar) {
        this.B.setValue(eVar);
    }

    private final void setParentLayoutCoordinates(n1.p pVar) {
        this.f4246v.setValue(pVar);
    }

    @Override // q1.a
    public final void a(f0.p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(-857613600);
        if ((((vVar.h(this) ? 4 : 2) | i5) & 3) == 2 && vVar.z()) {
            vVar.Q();
        } else {
            getContent().invoke(vVar, 0);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new j1(i5, 2, this);
        }
    }

    @Override // q1.a
    public final void d(boolean z2, int i5, int i6, int i7, int i8) {
        super.d(z2, i5, i6, i7, i8);
        this.f4237m.getClass();
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        WindowManager.LayoutParams layoutParams = this.f4242r;
        layoutParams.width = measuredWidth;
        layoutParams.height = childAt.getMeasuredHeight();
        this.f4240p.getClass();
        this.f4241q.updateViewLayout(this, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (keyEvent.getKeyCode() == 4 && this.f4237m.f4253c) {
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && (keyDispatcherState = getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent) && !keyEvent.isCanceled()) {
                a4.a aVar = this.f4236l;
                if (aVar != null) {
                    aVar.a();
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // q1.a
    public final void e(int i5, int i6) {
        this.f4237m.getClass();
        super.e(View.MeasureSpec.makeMeasureSpec(getDisplayWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), Integer.MIN_VALUE));
    }

    public final boolean getCanCalculatePosition() {
        return ((Boolean) this.f4248x.getValue()).booleanValue();
    }

    public final WindowManager.LayoutParams getParams$ui_release() {
        return this.f4242r;
    }

    public final k2.k getParentLayoutDirection() {
        return this.f4244t;
    }

    /* JADX INFO: renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final k2.j m11getPopupContentSizebOM6tXw() {
        return (k2.j) this.f4245u.getValue();
    }

    public final s getPositionProvider() {
        return this.f4243s;
    }

    @Override // q1.a
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.C;
    }

    public q1.a getSubCompositionView() {
        return this;
    }

    public final String getTestTag() {
        return this.f4238n;
    }

    public /* bridge */ /* synthetic */ View getViewRoot() {
        return null;
    }

    public final void h(f0.t tVar, n0.d dVar) {
        setParentCompositionContext(tVar);
        setContent(dVar);
        this.C = true;
    }

    public final void i(a4.a aVar, t tVar, String str, k2.k kVar) {
        int i5;
        this.f4236l = aVar;
        this.f4238n = str;
        if (!b4.l.a(this.f4237m, tVar)) {
            tVar.getClass();
            this.f4237m = tVar;
            boolean zB = j.b(this.f4239o);
            boolean z2 = tVar.f4252b;
            int i6 = tVar.f4251a;
            if (z2 && zB) {
                i6 |= 8192;
            } else if (z2 && !zB) {
                i6 &= -8193;
            }
            WindowManager.LayoutParams layoutParams = this.f4242r;
            layoutParams.flags = i6;
            this.f4240p.getClass();
            this.f4241q.updateViewLayout(this, layoutParams);
        }
        int iOrdinal = kVar.ordinal();
        if (iOrdinal != 0) {
            i5 = 1;
            if (iOrdinal != 1) {
                throw new i4.p();
            }
        } else {
            i5 = 0;
        }
        super.setLayoutDirection(i5);
    }

    public final void j() {
        n1.p parentLayoutCoordinates = getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            if (!parentLayoutCoordinates.N()) {
                parentLayoutCoordinates = null;
            }
            if (parentLayoutCoordinates == null) {
                return;
            }
            long jY = parentLayoutCoordinates.Y();
            long jG = parentLayoutCoordinates.g(0L);
            long jD = h1.c.d(Math.round(x0.c.d(jG)), Math.round(x0.c.e(jG)));
            int i5 = (int) (jD >> 32);
            int i6 = (int) (jD & 4294967295L);
            k2.i iVar = new k2.i(i5, i6, ((int) (jY >> 32)) + i5, ((int) (jY & 4294967295L)) + i6);
            if (iVar.equals(this.f4247w)) {
                return;
            }
            this.f4247w = iVar;
            l();
        }
    }

    public final void k(n1.p pVar) {
        setParentLayoutCoordinates(pVar);
        j();
    }

    public final void l() {
        k2.j jVarM11getPopupContentSizebOM6tXw;
        k2.i iVar = this.f4247w;
        if (iVar == null || (jVarM11getPopupContentSizebOM6tXw = m11getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        long j5 = jVarM11getPopupContentSizebOM6tXw.f3415a;
        r rVar = this.f4240p;
        rVar.getClass();
        View view = this.f4239o;
        Rect rect = this.f4249y;
        view.getWindowVisibleDisplayFrame(rect);
        long jF = a.a.f(rect.right - rect.left, rect.bottom - rect.top);
        b4.v vVar = new b4.v();
        vVar.f696d = 0L;
        this.f4250z.c(this, e.f4213j, new o(vVar, this, iVar, jF, j5));
        long j6 = vVar.f696d;
        WindowManager.LayoutParams layoutParams = this.f4242r;
        layoutParams.x = (int) (j6 >> 32);
        layoutParams.y = (int) (j6 & 4294967295L);
        if (this.f4237m.f4255e) {
            rVar.a(this, (int) (jF >> 32), (int) (jF & 4294967295L));
        }
        this.f4241q.updateViewLayout(this, layoutParams);
    }

    @Override // q1.a, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f4250z.d();
        if (!this.f4237m.f4253c || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (this.A == null) {
            this.A = k.a(this.f4236l);
        }
        k.b(this, this.A);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p0.t tVar = this.f4250z;
        x xVar = tVar.f4752h;
        if (xVar != null) {
            xVar.a();
        }
        tVar.a();
        if (Build.VERSION.SDK_INT >= 33) {
            k.c(this, this.A);
        }
        this.A = null;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f4237m.f4254d) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent != null && motionEvent.getAction() == 0 && (motionEvent.getX() < 0.0f || motionEvent.getX() >= getWidth() || motionEvent.getY() < 0.0f || motionEvent.getY() >= getHeight())) {
            a4.a aVar = this.f4236l;
            if (aVar != null) {
                aVar.a();
                return true;
            }
        } else {
            if (motionEvent == null || motionEvent.getAction() != 4) {
                return super.onTouchEvent(motionEvent);
            }
            a4.a aVar2 = this.f4236l;
            if (aVar2 != null) {
                aVar2.a();
            }
        }
        return true;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i5) {
    }

    public final void setParentLayoutDirection(k2.k kVar) {
        this.f4244t = kVar;
    }

    /* JADX INFO: renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m12setPopupContentSizefhxjrPA(k2.j jVar) {
        this.f4245u.setValue(jVar);
    }

    public final void setPositionProvider(s sVar) {
        this.f4243s = sVar;
    }

    public final void setTestTag(String str) {
        this.f4238n = str;
    }
}
