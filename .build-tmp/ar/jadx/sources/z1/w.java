package z1;

import android.text.Layout;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Layout.Alignment f7148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Layout.Alignment f7149b;

    static {
        Layout.Alignment[] alignmentArrValues = Layout.Alignment.values();
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        Layout.Alignment alignment2 = alignment;
        for (Layout.Alignment alignment3 : alignmentArrValues) {
            if (b4.l.a(alignment3.name(), "ALIGN_LEFT")) {
                alignment = alignment3;
            } else if (b4.l.a(alignment3.name(), "ALIGN_RIGHT")) {
                alignment2 = alignment3;
            }
        }
        f7148a = alignment;
        f7149b = alignment2;
    }
}
