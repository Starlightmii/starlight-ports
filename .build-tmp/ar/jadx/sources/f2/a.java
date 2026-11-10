package f2;

import b4.l;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Locale f2444a;

    public a(Locale locale) {
        this.f2444a = locale;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return l.a(this.f2444a.toLanguageTag(), ((a) obj).f2444a.toLanguageTag());
    }

    public final int hashCode() {
        return this.f2444a.toLanguageTag().hashCode();
    }

    public final String toString() {
        return this.f2444a.toLanguageTag();
    }
}
