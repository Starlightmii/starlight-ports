package b4;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static void b(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        i(nullPointerException, l.class.getName());
        throw nullPointerException;
    }

    public static void c(String str, Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        i(nullPointerException, l.class.getName());
        throw nullPointerException;
    }

    public static void d(String str, Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        i(nullPointerException, l.class.getName());
        throw nullPointerException;
    }

    public static void e(String str, Object obj) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = l.class.getName();
            int i5 = 0;
            while (!stackTrace[i5].getClassName().equals(name)) {
                i5++;
            }
            while (stackTrace[i5].getClassName().equals(name)) {
                i5++;
            }
            StackTraceElement stackTraceElement = stackTrace[i5];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            i(nullPointerException, l.class.getName());
            throw nullPointerException;
        }
    }

    public static int f(int i5, int i6) {
        if (i5 < i6) {
            return -1;
        }
        return i5 == i6 ? 0 : 1;
    }

    public static int g(long j5, long j6) {
        if (j5 < j6) {
            return -1;
        }
        return j5 == j6 ? 0 : 1;
    }

    public static final b h(Object[] objArr) {
        e("array", objArr);
        return new b(objArr);
    }

    public static void i(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i5 = -1;
        for (int i6 = 0; i6 < length; i6++) {
            if (str.equals(stackTrace[i6].getClassName())) {
                i5 = i6;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i5 + 1, length));
    }

    public static void j(String str) {
        i4.p pVar = new i4.p("lateinit property " + str + " has not been initialized");
        i(pVar, l.class.getName());
        throw pVar;
    }
}
