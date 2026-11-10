package w2;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z0 f6426a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f6427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j f6428c;

    public o(View view, j jVar) {
        this.f6427b = view;
        this.f6428c = jVar;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        z0 z0VarB = z0.b(view, windowInsets);
        int i5 = Build.VERSION.SDK_INT;
        j jVar = this.f6428c;
        if (i5 < 30) {
            p.a(windowInsets, this.f6427b);
            if (z0VarB.equals(this.f6426a)) {
                return ((r.c0) jVar).a(view, z0VarB).a();
            }
        }
        this.f6426a = z0VarB;
        z0 z0VarA = ((r.c0) jVar).a(view, z0VarB);
        if (i5 >= 30) {
            return z0VarA.a();
        }
        int i6 = u.f6438a;
        view.requestApplyInsets();
        return z0VarA.a();
    }
}
