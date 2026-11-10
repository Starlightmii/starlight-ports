package w2;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 extends l0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Field f6387f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f6388g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Constructor f6389h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f6390i = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WindowInsets f6391e;

    public e0() {
        this.f6391e = i();
    }

    public e0(z0 z0Var) {
        super(z0Var);
        this.f6391e = z0Var.a();
    }

    private static WindowInsets i() {
        if (!f6388g) {
            try {
                f6387f = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e5);
            }
            f6388g = true;
        }
        Field field = f6387f;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e6) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e6);
            }
        }
        if (!f6390i) {
            try {
                f6389h = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e7) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e7);
            }
            f6390i = true;
        }
        Constructor constructor = f6389h;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e8) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e8);
            }
        }
        return null;
    }

    @Override // w2.l0
    public z0 b() {
        a();
        z0 z0VarB = z0.b(null, this.f6391e);
        r2.c[] cVarArr = this.f6405b;
        v0 v0Var = z0VarB.f6456a;
        v0Var.u(cVarArr);
        v0Var.w(null);
        v0Var.t(null);
        v0Var.y(this.f6406c);
        v0Var.z(this.f6407d);
        return z0VarB;
    }

    @Override // w2.l0
    public void g(r2.c cVar) {
        WindowInsets windowInsets = this.f6391e;
        if (windowInsets != null) {
            this.f6391e = windowInsets.replaceSystemWindowInsets(cVar.f5697a, cVar.f5698b, cVar.f5699c, cVar.f5700d);
        }
    }
}
