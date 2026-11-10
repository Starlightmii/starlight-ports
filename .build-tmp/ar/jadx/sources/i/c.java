package i;

import androidx.lifecycle.u;
import androidx.lifecycle.w;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f2637e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f2638f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f2639g;

    public c(u uVar, w wVar) {
        this.f2636d = uVar;
        this.f2637e = wVar;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2636d.equals(cVar.f2636d) && this.f2637e.equals(cVar.f2637e);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f2636d;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f2637e;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f2636d.hashCode() ^ this.f2637e.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f2636d + "=" + this.f2637e;
    }
}
