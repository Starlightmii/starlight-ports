package s0;

import android.view.ViewStructure;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f5764a = new c();

    public final int a(ViewStructure viewStructure, int i5) {
        return viewStructure.addChildCount(i5);
    }

    public final ViewStructure b(ViewStructure viewStructure, int i5) {
        return viewStructure.newChild(i5);
    }

    public final void c(ViewStructure viewStructure, int i5, int i6, int i7, int i8, int i9, int i10) {
        viewStructure.setDimens(i5, i6, i7, i8, i9, i10);
    }

    public final void d(ViewStructure viewStructure, int i5, String str, String str2, String str3) {
        viewStructure.setId(i5, str, str2, str3);
    }
}
