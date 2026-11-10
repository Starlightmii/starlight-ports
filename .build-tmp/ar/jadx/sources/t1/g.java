package t1;

import android.os.Bundle;
import android.view.ViewStructure;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static Bundle a(ViewStructure viewStructure) {
        return viewStructure.getExtras();
    }

    public static void b(ViewStructure viewStructure, String str) {
        viewStructure.setClassName(str);
    }

    public static void c(ViewStructure viewStructure, CharSequence charSequence) {
        viewStructure.setContentDescription(charSequence);
    }

    public static void d(ViewStructure viewStructure, int i5, int i6, int i7, int i8, int i9, int i10) {
        viewStructure.setDimens(i5, i6, i7, i8, i9, i10);
    }

    public static void e(ViewStructure viewStructure, CharSequence charSequence) {
        viewStructure.setText(charSequence);
    }

    public static void f(ViewStructure viewStructure, float f5, int i5, int i6, int i7) {
        viewStructure.setTextStyle(f5, i5, i6, i7);
    }
}
