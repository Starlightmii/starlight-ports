package s1;

import android.R;
import android.view.Menu;
import i4.p;
import m.h;
import v.t;
import x0.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0.a f5769a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f5770b = d.f6533e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f5771c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t f5772d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f5773e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t f5774f = null;

    public b(b0.a aVar) {
        this.f5769a = aVar;
    }

    public static void a(int i5, Menu menu) {
        int i6;
        int iC = h.c(i5);
        int iC2 = h.c(i5);
        int iC3 = h.c(i5);
        if (iC3 == 0) {
            i6 = R.string.copy;
        } else if (iC3 == 1) {
            i6 = R.string.paste;
        } else if (iC3 == 2) {
            i6 = R.string.cut;
        } else {
            if (iC3 != 3) {
                throw new p();
            }
            i6 = R.string.selectAll;
        }
        menu.add(0, iC, iC2, i6).setShowAsAction(1);
    }

    public static void b(Menu menu, int i5, a4.a aVar) {
        if (aVar != null && menu.findItem(h.c(i5)) == null) {
            a(i5, menu);
        } else {
            if (aVar != null || menu.findItem(h.c(i5)) == null) {
                return;
            }
            menu.removeItem(h.c(i5));
        }
    }
}
