package n;

import android.view.View;
import android.widget.Magnifier;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 implements z0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b1 f3873b = new b1(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b1 f3874c = new b1(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3875a;

    public /* synthetic */ b1(int i5) {
        this.f3875a = i5;
    }

    @Override // n.z0
    public final boolean a() {
        switch (this.f3875a) {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override // n.z0
    public final y0 b(View view, k2.b bVar) {
        switch (this.f3875a) {
            case 0:
                return new a1(new Magnifier(view));
            default:
                return new c1(new Magnifier(view));
        }
    }
}
