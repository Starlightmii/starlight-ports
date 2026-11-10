package x;

import android.os.CancellationSignal;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import q1.o2;
import v.o0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f6469a = new e();

    public final void a(o0 o0Var, e0 e0Var, HandwritingGesture handwritingGesture, o2 o2Var, Executor executor, final IntConsumer intConsumer, a4.c cVar) {
        final int i5 = o0Var != null ? n.f6474a.i(o0Var, handwritingGesture, e0Var, o2Var, cVar) : 3;
        if (intConsumer == null) {
            return;
        }
        if (executor != null) {
            executor.execute(new Runnable() { // from class: x.d
                @Override // java.lang.Runnable
                public final void run() {
                    intConsumer.accept(i5);
                }
            });
        } else {
            intConsumer.accept(i5);
        }
    }

    public final boolean b(o0 o0Var, e0 e0Var, PreviewableHandwritingGesture previewableHandwritingGesture, CancellationSignal cancellationSignal) {
        if (o0Var != null) {
            return n.f6474a.A(o0Var, previewableHandwritingGesture, e0Var, cancellationSignal);
        }
        return false;
    }
}
