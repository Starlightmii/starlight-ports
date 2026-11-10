package k3;

import a4.e;
import android.graphics.Matrix;
import androidx.lifecycle.k0;
import androidx.lifecycle.m0;
import androidx.lifecycle.p;
import androidx.lifecycle.x;
import b4.m;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.util.LinkedHashMap;
import y0.f0;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f3430d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f3431e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f3432f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f3433g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Cloneable f3434h;

    /* JADX WARN: Multi-variable type inference failed */
    public b(e eVar) {
        this.f3430d = (m) eVar;
        this.f3427a = true;
        this.f3428b = true;
        this.f3429c = true;
    }

    public b(MainActivity mainActivity, m0 m0Var) {
        this.f3430d = mainActivity;
        this.f3431e = m0Var;
        this.f3432f = new k0(24);
        this.f3433g = new LinkedHashMap();
        this.f3429c = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [float[], java.lang.Cloneable] */
    public float[] a(Object obj) {
        float[] fArr = (float[]) this.f3434h;
        float[] fArr2 = fArr;
        if (fArr == null) {
            ?? A = f0.a();
            this.f3434h = A;
            fArr2 = A;
        }
        if (this.f3428b) {
            this.f3429c = q1.m0.o(b(obj), fArr2);
            this.f3428b = false;
        }
        if (this.f3429c) {
            return fArr2;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [a4.e, b4.m] */
    public float[] b(Object obj) {
        float[] fArrA = (float[]) this.f3433g;
        if (fArrA == null) {
            fArrA = f0.a();
            this.f3433g = fArrA;
        }
        if (!this.f3427a) {
            return fArrA;
        }
        Matrix matrix = (Matrix) this.f3431e;
        if (matrix == null) {
            matrix = new Matrix();
            this.f3431e = matrix;
        }
        ((m) this.f3430d).invoke(obj, matrix);
        Matrix matrix2 = (Matrix) this.f3432f;
        if (matrix2 == null || !matrix.equals(matrix2)) {
            l0.r(matrix, fArrA);
            this.f3431e = matrix2;
            this.f3432f = matrix;
        }
        this.f3427a = false;
        return fArrA;
    }

    public void c() {
        this.f3427a = true;
        this.f3428b = true;
    }

    public void d() {
        MainActivity mainActivity = (MainActivity) this.f3430d;
        if (((x) mainActivity.getLifecycle()).f298c != p.f275e) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        if (this.f3427a) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        ((m0) this.f3431e).a();
        mainActivity.getLifecycle().a(new a(0, this));
        this.f3427a = true;
    }
}
