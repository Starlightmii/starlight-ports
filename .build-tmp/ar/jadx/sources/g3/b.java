package g3;

import b4.l;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f2547a = new LinkedHashMap();

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return l.a(this.f2547a, ((b) obj).f2547a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2547a.hashCode();
    }

    public final String toString() {
        return "CreationExtras(extras=" + this.f2547a + ')';
    }
}
