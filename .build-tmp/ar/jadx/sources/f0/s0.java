package f0;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends CancellationException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(int i5) {
        super("rememberCoroutineScope left the composition");
        switch (i5) {
            case 1:
                super("The coroutine scope left the composition");
                break;
            default:
                break;
        }
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(n0.i.f4070a);
        return this;
    }
}
