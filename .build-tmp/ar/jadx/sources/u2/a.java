package u2;

import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5822b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List f5823c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Objects.equals(this.f5821a, aVar.f5821a) && Objects.equals(this.f5822b, aVar.f5822b) && Objects.equals(this.f5823c, aVar.f5823c);
    }

    public final int hashCode() {
        return Objects.hash(this.f5821a, this.f5822b, this.f5823c);
    }
}
