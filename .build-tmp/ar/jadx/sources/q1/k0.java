package q1;

import android.view.PointerIcon;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k0 f5255a = new k0();

    public final void a(View view, j1.q qVar) {
        PointerIcon systemIcon = qVar instanceof j1.a ? PointerIcon.getSystemIcon(view.getContext(), ((j1.a) qVar).f3212b) : PointerIcon.getSystemIcon(view.getContext(), 1000);
        if (b4.l.a(view.getPointerIcon(), systemIcon)) {
            return;
        }
        view.setPointerIcon(systemIcon);
    }
}
