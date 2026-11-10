package w2;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static boolean f6409m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static Method f6410n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static Class f6411o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static Field f6412p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static Field f6413q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsets f6414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public r2.c[] f6415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r2.c f6416e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public z0 f6417f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public r2.c f6418g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6419h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6420i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6421j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Rect[][] f6422k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Rect[][] f6423l;

    public m0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var);
        this.f6416e = null;
        this.f6422k = new Rect[10][];
        this.f6423l = new Rect[10][];
        this.f6414c = windowInsets;
    }

    private e A(View view) {
        Display display;
        if (view == null || (display = view.getDisplay()) == null) {
            return null;
        }
        Point point = new Point();
        display.getRealSize(point);
        if (this.f6441a.f6456a.r()) {
            return e.a(point.x, point.y, true, 0, 0, 0, 0);
        }
        l lVarO = q.f.o(display, 0);
        l lVarO2 = q.f.o(display, 1);
        l lVarO3 = q.f.o(display, 2);
        l lVarO4 = q.f.o(display, 3);
        return e.a(point.x, point.y, false, lVarO != null ? lVarO.f6402b : 0, lVarO2 != null ? lVarO2.f6402b : 0, lVarO3 != null ? lVarO3.f6402b : 0, lVarO4 != null ? lVarO4.f6402b : 0);
    }

    private static List<Rect> B(Rect[][] rectArr, int i5) {
        Rect[] rectArr2;
        Rect[] rectArr3 = null;
        for (int i6 = 1; i6 <= 512; i6 <<= 1) {
            if ((i5 & i6) != 0 && (rectArr2 = rectArr[q.f.p(i6)]) != null) {
                if (rectArr3 == null) {
                    rectArr3 = rectArr2;
                } else {
                    Rect[] rectArr4 = new Rect[rectArr3.length + rectArr2.length];
                    System.arraycopy(rectArr3, 0, rectArr4, 0, rectArr3.length);
                    System.arraycopy(rectArr2, 0, rectArr4, rectArr3.length, rectArr2.length);
                    rectArr3 = rectArr4;
                }
            }
        }
        return rectArr3 == null ? Collections.EMPTY_LIST : Arrays.asList(rectArr3);
    }

    private Rect[] C(r2.c cVar) {
        ArrayList arrayList = new ArrayList();
        int i5 = cVar.f5697a;
        int i6 = cVar.f5700d;
        int i7 = cVar.f5699c;
        int i8 = cVar.f5698b;
        if (i5 != 0) {
            arrayList.add(new Rect(0, 0, cVar.f5697a, this.f6420i));
        }
        if (i8 != 0) {
            arrayList.add(new Rect(0, 0, this.f6421j, i8));
        }
        if (i7 != 0) {
            int i9 = this.f6421j;
            arrayList.add(new Rect(i9 - i7, 0, i9, this.f6420i));
        }
        if (i6 != 0) {
            int i10 = this.f6420i;
            arrayList.add(new Rect(0, i10 - i6, this.f6421j, i10));
        }
        return (Rect[]) arrayList.toArray(new Rect[arrayList.size()]);
    }

    private r2.c D(int i5, boolean z2) {
        r2.c cVarA = r2.c.f5696e;
        for (int i6 = 1; i6 <= 512; i6 <<= 1) {
            if ((i5 & i6) != 0) {
                cVarA = r2.c.a(cVarA, E(i6, z2));
            }
        }
        return cVarA;
    }

    private r2.c F() {
        z0 z0Var = this.f6417f;
        return z0Var != null ? z0Var.f6456a.k() : r2.c.f5696e;
    }

    private r2.c G(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f6409m) {
            I();
        }
        Method method = f6410n;
        if (method != null && f6411o != null && f6412p != null) {
            try {
                Object objInvoke = method.invoke(view, null);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) f6412p.get(f6413q.get(objInvoke));
                if (rect != null) {
                    return r2.c.b(rect.left, rect.top, rect.right, rect.bottom);
                }
                return null;
            } catch (ReflectiveOperationException e5) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e5.getMessage(), e5);
            }
        }
        return null;
    }

    private static void I() {
        try {
            f6410n = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f6411o = cls;
            f6412p = cls.getDeclaredField("mVisibleInsets");
            f6413q = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            f6412p.setAccessible(true);
            f6413q.setAccessible(true);
        } catch (ReflectiveOperationException e5) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e5.getMessage(), e5);
        }
        f6409m = true;
    }

    public static boolean K(int i5, int i6) {
        return (i5 & 6) == (i6 & 6);
    }

    public r2.c E(int i5, boolean z2) {
        r2.c cVarK;
        int i6;
        r2.c cVar = r2.c.f5696e;
        if (i5 != 1) {
            if (i5 != 2) {
                if (i5 == 8) {
                    r2.c[] cVarArr = this.f6415d;
                    cVarK = cVarArr != null ? cVarArr[q.f.p(8)] : null;
                    if (cVarK != null) {
                        return cVarK;
                    }
                    r2.c cVarM = m();
                    r2.c cVarF = F();
                    int i7 = cVarM.f5700d;
                    if (i7 > cVarF.f5700d) {
                        return r2.c.b(0, 0, 0, i7);
                    }
                    r2.c cVar2 = this.f6418g;
                    if (cVar2 != null && !cVar2.equals(cVar) && (i6 = this.f6418g.f5700d) > cVarF.f5700d) {
                        return r2.c.b(0, 0, 0, i6);
                    }
                } else {
                    if (i5 == 16) {
                        return l();
                    }
                    if (i5 == 32) {
                        return j();
                    }
                    if (i5 == 64) {
                        return n();
                    }
                    if (i5 == 128) {
                        z0 z0Var = this.f6417f;
                        c cVarG = z0Var != null ? z0Var.f6456a.g() : g();
                        if (cVarG != null) {
                            int i8 = Build.VERSION.SDK_INT;
                            return r2.c.b(i8 >= 28 ? s2.a.d(cVarG.f6378a) : 0, i8 >= 28 ? s2.a.f(cVarG.f6378a) : 0, i8 >= 28 ? s2.a.e(cVarG.f6378a) : 0, i8 >= 28 ? s2.a.c(cVarG.f6378a) : 0);
                        }
                    }
                }
            } else {
                if (z2) {
                    r2.c cVarF2 = F();
                    r2.c cVarK2 = k();
                    return r2.c.b(Math.max(cVarF2.f5697a, cVarK2.f5697a), 0, Math.max(cVarF2.f5699c, cVarK2.f5699c), Math.max(cVarF2.f5700d, cVarK2.f5700d));
                }
                if ((this.f6419h & 2) == 0) {
                    r2.c cVarM2 = m();
                    z0 z0Var2 = this.f6417f;
                    cVarK = z0Var2 != null ? z0Var2.f6456a.k() : null;
                    int iMin = cVarM2.f5700d;
                    if (cVarK != null) {
                        iMin = Math.min(iMin, cVarK.f5700d);
                    }
                    return r2.c.b(cVarM2.f5697a, 0, cVarM2.f5699c, iMin);
                }
            }
        } else {
            if (z2) {
                return r2.c.b(0, Math.max(F().f5698b, m().f5698b), 0, 0);
            }
            if ((this.f6419h & 4) == 0) {
                return r2.c.b(0, m().f5698b, 0, 0);
            }
        }
        return cVar;
    }

    public boolean H(int i5) {
        if (i5 != 1 && i5 != 2) {
            if (i5 == 4) {
                return false;
            }
            if (i5 != 8 && i5 != 128) {
                return true;
            }
        }
        return !E(i5, false).equals(r2.c.f5696e);
    }

    public void J(r2.c cVar) {
        this.f6418g = cVar;
    }

    @Override // w2.v0
    public void d(View view) {
        this.f6421j = view.getWidth();
        this.f6420i = view.getHeight();
        r2.c cVarG = G(view);
        if (cVarG == null) {
            cVarG = r2.c.f5696e;
        }
        J(cVarG);
    }

    @Override // w2.v0
    public List<Rect> e(int i5) {
        return B(this.f6422k, i5);
    }

    @Override // w2.v0
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        m0 m0Var = (m0) obj;
        return Objects.equals(this.f6418g, m0Var.f6418g) && K(this.f6419h, m0Var.f6419h);
    }

    @Override // w2.v0
    public List<Rect> f(int i5) {
        return B(this.f6423l, i5);
    }

    @Override // w2.v0
    public r2.c h(int i5) {
        return D(i5, false);
    }

    @Override // w2.v0
    public r2.c i(int i5) {
        return D(i5, true);
    }

    @Override // w2.v0
    public final r2.c m() {
        if (this.f6416e == null) {
            WindowInsets windowInsets = this.f6414c;
            this.f6416e = r2.c.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f6416e;
    }

    @Override // w2.v0
    public void o(View view) {
        A(view);
    }

    @Override // w2.v0
    public void p() {
        for (int i5 = 1; i5 <= 512; i5 <<= 1) {
            int iP = q.f.p(i5);
            this.f6422k[iP] = C(h(i5));
            if (i5 != 8) {
                this.f6423l[iP] = C(i(i5));
            }
        }
    }

    @Override // w2.v0
    public boolean r() {
        return this.f6414c.isRound();
    }

    @Override // w2.v0
    public boolean s(int i5) {
        for (int i6 = 1; i6 <= 512; i6 <<= 1) {
            if ((i5 & i6) != 0 && !H(i6)) {
                return false;
            }
        }
        return true;
    }

    @Override // w2.v0
    public void t(e eVar) {
    }

    @Override // w2.v0
    public void u(r2.c[] cVarArr) {
        this.f6415d = cVarArr;
    }

    @Override // w2.v0
    public void v(z0 z0Var) {
        this.f6417f = z0Var;
    }

    @Override // w2.v0
    public void x(int i5) {
        this.f6419h = i5;
    }

    @Override // w2.v0
    public void y(Rect[][] rectArr) {
        Objects.requireNonNull(rectArr);
        this.f6422k = (Rect[][]) rectArr.clone();
    }

    @Override // w2.v0
    public void z(Rect[][] rectArr) {
        Objects.requireNonNull(rectArr);
        this.f6423l = (Rect[][]) rectArr.clone();
    }
}
