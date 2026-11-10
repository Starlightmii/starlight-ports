package o;

import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f4340a = f0.l.s(g.f4338a);

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            return b4.l.a((i) ((j) obj).f4340a.getValue(), (i) this.f4340a.getValue());
        }
        return false;
    }

    public final int hashCode() {
        return ((i) this.f4340a.getValue()).hashCode();
    }

    public final String toString() {
        return "ContextMenuState(status=" + ((i) this.f4340a.getValue()) + ')';
    }
}
