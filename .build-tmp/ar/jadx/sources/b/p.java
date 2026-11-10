package b;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.p0;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f359e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ MainActivity f360f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(MainActivity mainActivity, int i5) {
        super(0);
        this.f359e = i5;
        this.f360f = mainActivity;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f359e) {
            case 0:
                MainActivity mainActivity = this.f360f;
                return new p0(mainActivity.getApplication(), mainActivity, mainActivity.getIntent() != null ? mainActivity.getIntent().getExtras() : null);
            case 1:
                this.f360f.reportFullyDrawn();
                return p3.n.f5026a;
            case 2:
                MainActivity mainActivity2 = this.f360f;
                return new y(((r) mainActivity2).reportFullyDrawnExecutor, new p(mainActivity2, 1));
            default:
                MainActivity mainActivity3 = this.f360f;
                i0 i0Var = new i0(new e(mainActivity3, 1));
                if (Build.VERSION.SDK_INT >= 33) {
                    if (b4.l.a(Looper.myLooper(), Looper.getMainLooper())) {
                        mainActivity3.getLifecycle().a(new i(i0Var, 0, mainActivity3));
                    } else {
                        new Handler(Looper.getMainLooper()).post(new q(mainActivity3, 0, i0Var));
                    }
                }
                return i0Var;
        }
    }
}
