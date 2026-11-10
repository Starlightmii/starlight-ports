package b;

import android.os.Bundle;
import android.util.LongSparseArray;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import io.github.libxposed.service.RemotePreferences;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f363f;

    public /* synthetic */ q(Object obj, int i5, Object obj2) {
        this.f361d = i5;
        this.f362e = obj;
        this.f363f = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f361d) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.f362e;
                mainActivity.getLifecycle().a(new i((i0) this.f363f, 0, mainActivity));
                break;
            case 1:
                ((RemotePreferences.Editor) this.f362e).lambda$apply$0((Bundle) this.f363f);
                break;
            default:
                t0.a.a((t0.c) this.f362e, (LongSparseArray) this.f363f);
                break;
        }
    }
}
