package j1;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3301d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(String str, int i5) {
        super(str);
        this.f3301d = i5;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        switch (this.f3301d) {
            case 0:
                setStackTrace(z.f3304b);
                break;
            case 1:
                setStackTrace(new StackTraceElement[0]);
                break;
            case 2:
                setStackTrace(new StackTraceElement[0]);
                break;
            case 3:
                setStackTrace(new StackTraceElement[0]);
                break;
            default:
                setStackTrace(r0.a.f5652e);
                break;
        }
        return this;
    }
}
