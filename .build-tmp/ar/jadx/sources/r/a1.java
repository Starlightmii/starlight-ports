package r;

import android.os.Build;
import android.view.View;
import io.github.andrea_lyz.assistrestore.R;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a1 {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final WeakHashMap f5506u = new WeakHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5507a = b.c(4, "captionBar");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f5508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f5509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f5510d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f5511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f5512f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5513g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a f5514h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f5515i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final y0 f5516j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final y0 f5517k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y0 f5518l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y0 f5519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final y0 f5520n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y0 f5521o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final y0 f5522p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final y0 f5523q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f5524r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f5525s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final c0 f5526t;

    public a1(View view) {
        a aVarC = b.c(128, "displayCutout");
        this.f5508b = aVarC;
        a aVarC2 = b.c(8, "ime");
        this.f5509c = aVarC2;
        a aVarC3 = b.c(32, "mandatorySystemGestures");
        this.f5510d = aVarC3;
        this.f5511e = b.c(2, "navigationBars");
        this.f5512f = b.c(1, "statusBars");
        a aVarC4 = b.c(519, "systemBars");
        this.f5513g = aVarC4;
        a aVarC5 = b.c(16, "systemGestures");
        this.f5514h = aVarC5;
        a aVarC6 = b.c(64, "tappableElement");
        this.f5515i = aVarC6;
        y0 y0Var = new y0(new e0(0, 0, 0, 0), "waterfall");
        this.f5516j = y0Var;
        new w0(new w0(aVarC4, aVarC2), aVarC);
        new w0(new w0(new w0(aVarC6, aVarC3), aVarC5), y0Var);
        this.f5517k = b.d(4, "captionBarIgnoringVisibility");
        this.f5518l = b.d(2, "navigationBarsIgnoringVisibility");
        this.f5519m = b.d(1, "statusBarsIgnoringVisibility");
        this.f5520n = b.d(519, "systemBarsIgnoringVisibility");
        this.f5521o = b.d(64, "tappableElementIgnoringVisibility");
        this.f5522p = b.d(8, "imeAnimationTarget");
        this.f5523q = b.d(8, "imeAnimationSource");
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        Object tag = view2 != null ? view2.getTag(R.id.consume_window_insets_tag) : null;
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        this.f5524r = bool != null ? bool.booleanValue() : true;
        this.f5526t = new c0(this);
    }

    public static void a(a1 a1Var, w2.z0 z0Var) {
        boolean z2 = false;
        a1Var.f5507a.f(z0Var, 0);
        a1Var.f5509c.f(z0Var, 0);
        a1Var.f5508b.f(z0Var, 0);
        a1Var.f5511e.f(z0Var, 0);
        a1Var.f5512f.f(z0Var, 0);
        a1Var.f5513g.f(z0Var, 0);
        a1Var.f5514h.f(z0Var, 0);
        a1Var.f5515i.f(z0Var, 0);
        a1Var.f5510d.f(z0Var, 0);
        a1Var.f5517k.f(c.f(z0Var.f6456a.i(4)));
        a1Var.f5518l.f(c.f(z0Var.f6456a.i(2)));
        a1Var.f5519m.f(c.f(z0Var.f6456a.i(1)));
        a1Var.f5520n.f(c.f(z0Var.f6456a.i(519)));
        a1Var.f5521o.f(c.f(z0Var.f6456a.i(64)));
        w2.c cVarG = z0Var.f6456a.g();
        if (cVarG != null) {
            a1Var.f5516j.f(c.f(Build.VERSION.SDK_INT >= 30 ? r2.c.c(t2.b.c(cVarG.f6378a)) : r2.c.f5696e));
        }
        synchronized (p0.m.f4719c) {
            j.a0 a0Var = p0.m.f4726j.f4666h;
            if (a0Var != null && a0Var.h()) {
                z2 = true;
            }
        }
        if (z2) {
            p0.m.a();
        }
    }
}
