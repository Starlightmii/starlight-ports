package t3;

import b4.l;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i implements h, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f5807d = new i();

    @Override // t3.h
    public final h f(g gVar) {
        l.e("key", gVar);
        return this;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // t3.h
    public final f j(g gVar) {
        l.e("key", gVar);
        return null;
    }

    @Override // t3.h
    public final h k(h hVar) {
        l.e("context", hVar);
        return hVar;
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return obj;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }
}
