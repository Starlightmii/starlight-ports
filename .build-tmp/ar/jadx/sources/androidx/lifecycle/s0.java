package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends u0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static s0 f289c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k0 f290d = new k0(3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Application f291b;

    public s0(Application application) {
        this.f291b = application;
    }

    @Override // androidx.lifecycle.u0, androidx.lifecycle.t0
    public final r0 a(Class cls) {
        Application application = this.f291b;
        if (application == null) {
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }
        if (!a.class.isAssignableFrom(cls)) {
            return d4.a.s(cls);
        }
        try {
            r0 r0Var = (r0) cls.getConstructor(Application.class).newInstance(application);
            b4.l.b(r0Var);
            return r0Var;
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Cannot create an instance of " + cls, e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException("Cannot create an instance of " + cls, e6);
        } catch (NoSuchMethodException e7) {
            throw new RuntimeException("Cannot create an instance of " + cls, e7);
        } catch (InvocationTargetException e8) {
            throw new RuntimeException("Cannot create an instance of " + cls, e8);
        }
    }
}
