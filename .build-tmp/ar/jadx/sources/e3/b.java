package e3;

import androidx.lifecycle.v;
import b4.l;
import f0.h3;
import f0.y1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import p3.h;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y1 f2073a;

    static {
        Object objR;
        try {
            ClassLoader classLoader = v.class.getClassLoader();
            l.b(classLoader);
            Method method = classLoader.loadClass("androidx.compose.ui.platform.AndroidCompositionLocals_androidKt").getMethod("getLocalLifecycleOwner", null);
            Annotation[] annotations = method.getAnnotations();
            int length = annotations.length;
            int i5 = 0;
            while (true) {
                if (i5 >= length) {
                    Object objInvoke = method.invoke(null, null);
                    if (objInvoke instanceof y1) {
                        objR = (y1) objInvoke;
                        break;
                    }
                } else if (!(annotations[i5] instanceof p3.a)) {
                    i5++;
                }
                objR = null;
                break;
            }
        } catch (Throwable th) {
            objR = j.r(th);
        }
        y1 h3Var = (y1) (objR instanceof h ? null : objR);
        if (h3Var == null) {
            h3Var = new h3(new a(0));
        }
        f2073a = h3Var;
    }
}
