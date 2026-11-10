package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f5232a = new f0.h3(n0.f5277m);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f0.h3 f5233b = new f0.h3(n0.f5278n);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f0.h3 f5234c = new f0.h3(n0.f5279o);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f0.h3 f5235d = new f0.h3(n0.f5280p);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f0.h3 f5236e = new f0.h3(n0.f5285u);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f0.h3 f5237f = new f0.h3(n0.f5281q);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f0.h3 f5238g = new f0.h3(n0.f5282r);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f0.h3 f5239h = new f0.h3(n0.f5284t);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f0.h3 f5240i = new f0.h3(n0.f5283s);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final f0.h3 f5241j = new f0.h3(n0.f5286v);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final f0.h3 f5242k = new f0.h3(n0.f5287w);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final f0.h3 f5243l = new f0.h3(n0.f5288x);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f0.h3 f5244m = new f0.h3(n0.B);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f0.h3 f5245n = new f0.h3(n0.A);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final f0.h3 f5246o = new f0.h3(n0.C);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f0.h3 f5247p = new f0.h3(n0.D);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f0.h3 f5248q = new f0.h3(n0.E);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final f0.h3 f5249r = new f0.h3(n0.F);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final f0.h3 f5250s = new f0.h3(n0.f5289y);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final f0.j0 f5251t = new f0.j0(n0.f5290z);

    public static final void a(p1.e1 e1Var, x0 x0Var, a4.e eVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(874662829);
        int i6 = i5 | (vVar.f(e1Var) ? 4 : 2) | (vVar.f(x0Var) ? 32 : 16) | (vVar.h(eVar) ? 256 : 128);
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            u uVar = (u) e1Var;
            f0.z1 z1VarA = f5232a.a(uVar.getAccessibilityManager());
            f0.z1 z1VarA2 = f5233b.a(uVar.getAutofill());
            f0.z1 z1VarA3 = f5234c.a(uVar.getAutofillTree());
            f0.z1 z1VarA4 = f5235d.a(uVar.getClipboardManager());
            f0.z1 z1VarA5 = f5237f.a(uVar.getDensity());
            f0.z1 z1VarA6 = f5238g.a(uVar.getFocusOwner());
            f0.z1 z1VarA7 = f5239h.a(uVar.getFontLoader());
            z1VarA7.f2441f = false;
            f0.z1 z1VarA8 = f5240i.a(uVar.getFontFamilyResolver());
            z1VarA8.f2441f = false;
            f0.l.b(new f0.z1[]{z1VarA, z1VarA2, z1VarA3, z1VarA4, z1VarA5, z1VarA6, z1VarA7, z1VarA8, f5241j.a(uVar.getHapticFeedBack()), f5242k.a(uVar.getInputModeManager()), f5243l.a(uVar.getLayoutDirection()), f5244m.a(uVar.getTextInputService()), f5245n.a(uVar.getSoftwareKeyboardController()), f5246o.a(uVar.getTextToolbar()), f5247p.a(x0Var), f5248q.a(uVar.getViewConfiguration()), f5249r.a(uVar.getWindowInfo()), f5250s.a(uVar.getPointerIconService()), f5236e.a(uVar.getGraphicsContext())}, eVar, vVar, ((i6 >> 3) & 112) | 8);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.x0(e1Var, x0Var, eVar, i5, 5);
        }
    }

    public static final void b(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
