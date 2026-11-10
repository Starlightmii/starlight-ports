package j2;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f3322b = new j(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f3323c = new j(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f3324d = new j(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3325a;

    public j(int i5) {
        this.f3325a = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.f3325a == ((j) obj).f3325a;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3325a;
    }

    public final String toString() {
        int i5 = this.f3325a;
        if (i5 == 0) {
            return "TextDecoration.None";
        }
        ArrayList arrayList = new ArrayList();
        if ((i5 & 1) != 0) {
            arrayList.add("Underline");
        }
        if ((i5 & 2) != 0) {
            arrayList.add("LineThrough");
        }
        if (arrayList.size() == 1) {
            return "TextDecoration." + ((String) arrayList.get(0));
        }
        return "TextDecoration[" + h1.c.s(arrayList, ", ") + ']';
    }
}
