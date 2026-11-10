package w2;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayCutout f6378a;

    public c(DisplayCutout displayCutout) {
        this.f6378a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f6378a, ((c) obj).f6378a);
    }

    public final int hashCode() {
        return this.f6378a.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f6378a + "}";
    }
}
