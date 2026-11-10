package q1;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 implements d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Matrix f5160d = new Matrix();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f5161e = new int[2];

    @Override // q1.d1
    public void b(View view, float[] fArr) {
        Matrix matrix = this.f5160d;
        matrix.reset();
        view.transformMatrixToGlobal(matrix);
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            view = parent;
            parent = view.getParent();
        }
        int[] iArr = this.f5161e;
        view.getLocationOnScreen(iArr);
        int i5 = iArr[0];
        int i6 = iArr[1];
        view.getLocationInWindow(iArr);
        matrix.postTranslate(iArr[0] - i5, iArr[1] - i6);
        y0.l0.r(matrix, fArr);
    }
}
