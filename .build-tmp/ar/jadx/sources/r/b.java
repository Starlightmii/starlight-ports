package r;

import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.c2;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5527a;

    public /* synthetic */ b(int i5) {
        this.f5527a = i5;
    }

    public static final a c(int i5, String str) {
        WeakHashMap weakHashMap = a1.f5506u;
        return new a(i5, str);
    }

    public static final y0 d(int i5, String str) {
        WeakHashMap weakHashMap = a1.f5506u;
        return new y0(new e0(0, 0, 0, 0), str);
    }

    public static a1 e(f0.p pVar) {
        a1 a1Var;
        f0.v vVar = (f0.v) pVar;
        View view = (View) vVar.j(AndroidCompositionLocals_androidKt.f208f);
        WeakHashMap weakHashMap = a1.f5506u;
        synchronized (weakHashMap) {
            try {
                Object a1Var2 = weakHashMap.get(view);
                if (a1Var2 == null) {
                    a1Var2 = new a1(view);
                    weakHashMap.put(view, a1Var2);
                }
                a1Var = (a1) a1Var2;
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean zH = vVar.h(a1Var) | vVar.h(view);
        Object objK = vVar.K();
        if (zH || objK == f0.o.f2281a) {
            objK = new c2(a1Var, 21, view);
            vVar.f0(objK);
        }
        f0.l.c(a1Var, (a4.c) objK, vVar);
        return a1Var;
    }

    @Override // r.e
    public void b(n1.h0 h0Var, int i5, int[] iArr, k2.k kVar, int[] iArr2) {
        switch (this.f5527a) {
            case 0:
                j.b(iArr, iArr2, false);
                break;
            case 1:
                j.c(i5, iArr, iArr2, false);
                break;
            default:
                if (kVar != k2.k.f3416d) {
                    j.c(i5, iArr, iArr2, true);
                } else {
                    j.b(iArr, iArr2, false);
                }
                break;
        }
    }

    public String toString() {
        switch (this.f5527a) {
            case 0:
                return "AbsoluteArrangement#Left";
            case 1:
                return "AbsoluteArrangement#Right";
            case 2:
                return "Arrangement#Start";
            default:
                return super.toString();
        }
    }
}
