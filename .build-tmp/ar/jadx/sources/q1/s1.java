package q1;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s1 implements t3.g, d2.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ s1 f5361d = new s1();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w2 f5362e = new w2();

    public static final boolean a() {
        Class cls = u.B0;
        try {
            if (u.B0 == null) {
                Class<?> cls2 = Class.forName("android.os.SystemProperties");
                u.B0 = cls2;
                u.C0 = cls2.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            }
            Method method = u.C0;
            Object objInvoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
            Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
