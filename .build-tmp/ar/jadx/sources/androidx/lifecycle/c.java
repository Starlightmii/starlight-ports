package androidx.lifecycle;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f234a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f235b;

    public c(int i5, Method method) {
        this.f234a = i5;
        this.f235b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f234a == cVar.f234a && this.f235b.getName().equals(cVar.f235b.getName());
    }

    public final int hashCode() {
        return this.f235b.getName().hashCode() + (this.f234a * 31);
    }
}
