package n4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f4308a = 0;

    static {
        Object objR;
        Object objR2;
        Exception exc = new Exception();
        String simpleName = a.a.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            objR = v3.a.class.getCanonicalName();
        } catch (Throwable th) {
            objR = p3.j.r(th);
        }
        if (p3.i.a(objR) != null) {
            objR = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            objR2 = r.class.getCanonicalName();
        } catch (Throwable th2) {
            objR2 = p3.j.r(th2);
        }
        if (p3.i.a(objR2) != null) {
            objR2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
