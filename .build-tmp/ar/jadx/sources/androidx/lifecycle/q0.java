package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f285a = p3.j.H(Application.class, h0.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List f286b = p3.j.G(h0.class);

    public static final Constructor a(Class cls, List list) {
        List listG;
        b4.l.e("signature", list);
        b4.b bVarH = b4.l.h(cls.getConstructors());
        while (bVarH.hasNext()) {
            Constructor constructor = (Constructor) bVarH.next();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            b4.l.d("getParameterTypes(...)", parameterTypes);
            int length = parameterTypes.length;
            if (length == 0) {
                listG = q3.s.f5477d;
            } else if (length != 1) {
                Object[] objArrCopyOf = Arrays.copyOf(parameterTypes, parameterTypes.length);
                b4.l.d("copyOf(...)", objArrCopyOf);
                listG = Arrays.asList(objArrCopyOf);
                b4.l.d("asList(...)", listG);
            } else {
                listG = p3.j.G(parameterTypes[0]);
            }
            if (list.equals(listG)) {
                return constructor;
            }
            if (list.size() == listG.size() && listG.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final r0 b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (r0) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Failed to access " + cls, e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e6);
        } catch (InvocationTargetException e7) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e7.getCause());
        }
    }
}
