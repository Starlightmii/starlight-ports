package j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3212b;

    public a(int i5) {
        this.f3212b = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!a.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.input.pointer.AndroidPointerIconType", obj);
        return this.f3212b == ((a) obj).f3212b;
    }

    public final int hashCode() {
        return this.f3212b;
    }

    public final String toString() {
        return b.b.f(new StringBuilder("AndroidPointerIcon(type="), this.f3212b, ')');
    }
}
