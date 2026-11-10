package i4;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient a1 f2775d;

    public t0(String str, Throwable th, a1 a1Var) {
        super(str);
        this.f2775d = a1Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof t0)) {
            return false;
        }
        t0 t0Var = (t0) obj;
        return b4.l.a(t0Var.getMessage(), getMessage()) && b4.l.a(t0Var.f2775d, this.f2775d) && b4.l.a(t0Var.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        String message = getMessage();
        b4.l.b(message);
        int iHashCode = (this.f2775d.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.f2775d;
    }
}
