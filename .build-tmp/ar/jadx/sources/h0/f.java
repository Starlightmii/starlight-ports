package h0;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static final void a(int i5, List list) {
        int size = list.size();
        if (i5 < 0 || i5 >= size) {
            c(i5, size);
        }
    }

    public static final void b(List list, int i5, int i6) {
        if (i5 > i6) {
            f(i5, i6);
        }
        if (i5 < 0) {
            d(i5);
        }
        if (i6 > list.size()) {
            e(i6, list.size());
        }
    }

    private static final void c(int i5, int i6) {
        throw new IndexOutOfBoundsException("Index " + i5 + " is out of bounds. The list has " + i6 + " elements.");
    }

    private static final void d(int i5) {
        throw new IndexOutOfBoundsException("fromIndex (" + i5 + ") is less than 0.");
    }

    private static final void e(int i5, int i6) {
        throw new IndexOutOfBoundsException("toIndex (" + i5 + ") is more than than the list size (" + i6 + ')');
    }

    private static final void f(int i5, int i6) {
        throw new IllegalArgumentException("Indices are out of order. fromIndex (" + i5 + ") is greater than toIndex (" + i6 + ").");
    }
}
