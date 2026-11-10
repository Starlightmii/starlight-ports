package q1;

import io.github.andrea_lyz.assistrestore.ui.MainActivity;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i1 extends a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f0.s1 f5226l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5227m;

    public i1(MainActivity mainActivity) {
        super(mainActivity);
        this.f5226l = f0.l.s(null);
    }

    public static /* synthetic */ void getShouldCreateCompositionOnAttachedToWindow$annotations() {
    }

    @Override // q1.a
    public final void a(f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(420213850);
        if ((((vVar.h(this) ? 4 : 2) | i5) & 3) == 2 && vVar.z()) {
            vVar.Q();
        } else {
            a4.e eVar = (a4.e) this.f5226l.getValue();
            if (eVar == null) {
                vVar.V(358373017);
            } else {
                vVar.V(150107752);
                eVar.invoke(vVar, 0);
            }
            vVar.p(false);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.j1(i5, 6, this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return i1.class.getName();
    }

    @Override // q1.a
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.f5227m;
    }

    public final void setContent(a4.e eVar) {
        this.f5227m = true;
        this.f5226l.setValue(eVar);
        if (isAttachedToWindow()) {
            if (this.f5100g == null && !isAttachedToWindow()) {
                throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.");
            }
            c();
        }
    }
}
