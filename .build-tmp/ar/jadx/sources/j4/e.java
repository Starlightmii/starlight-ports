package j4;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import b4.l;
import java.lang.reflect.InvocationTargetException;
import p3.h;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f3352a = 0;
    private static volatile Choreographer choreographer;

    static {
        Object objR;
        try {
            objR = new d(a(Looper.getMainLooper()));
        } catch (Throwable th) {
            objR = j.r(th);
        }
        if (objR instanceof h) {
            objR = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 28) {
            Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
            l.c("null cannot be cast to non-null type android.os.Handler", objInvoke);
            return (Handler) objInvoke;
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (NoSuchMethodException unused) {
            return new Handler(looper);
        }
    }
}
