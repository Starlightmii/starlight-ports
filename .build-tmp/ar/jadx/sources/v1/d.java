package v1;

import android.os.CancellationSignal;
import i4.h1;
import v.o0;
import y1.g0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements CancellationSignal.OnCancelListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f6195b;

    public /* synthetic */ d(int i5, Object obj) {
        this.f6194a = i5;
        this.f6195b = obj;
    }

    @Override // android.os.CancellationSignal.OnCancelListener
    public final void onCancel() {
        switch (this.f6194a) {
            case 0:
                ((h1) this.f6195b).b(null);
                break;
            default:
                e0 e0Var = (e0) this.f6195b;
                if (e0Var != null) {
                    o0 o0Var = e0Var.f6930d;
                    if (o0Var != null) {
                        o0Var.e(g0.f6768b);
                    }
                    o0 o0Var2 = e0Var.f6930d;
                    if (o0Var2 != null) {
                        o0Var2.f(g0.f6768b);
                        break;
                    }
                }
                break;
        }
    }
}
