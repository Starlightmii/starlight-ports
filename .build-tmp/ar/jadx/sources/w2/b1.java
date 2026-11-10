package w2;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class b1 extends p3.j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsetsController f6376c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Window f6377d;

    public b1(Window window) {
        this.f6376c = window.getInsetsController();
        this.f6377d = window;
    }

    @Override // p3.j
    public void L(boolean z2) {
        S(16, 16, z2);
    }

    @Override // p3.j
    public void M(boolean z2) {
        S(8192, 8, z2);
    }

    public final void S(int i5, int i6, boolean z2) {
        Window window = this.f6377d;
        if (window == null) {
            if (z2) {
                this.f6376c.setSystemBarsAppearance(i6, i6);
                return;
            } else {
                this.f6376c.setSystemBarsAppearance(0, i6);
                return;
            }
        }
        if (z2) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(i5 | decorView.getSystemUiVisibility());
        } else {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility((~i5) & decorView2.getSystemUiVisibility());
        }
    }
}
