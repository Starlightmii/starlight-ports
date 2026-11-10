package l0;

import g4.e;
import java.util.Iterator;
import k0.c;
import q3.h;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends h implements i0.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f3495g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f3496d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f3497e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f3498f;

    static {
        m0.b bVar = m0.b.f3811a;
        f3495g = new b(bVar, bVar, c.f3360f);
    }

    public b(Object obj, Object obj2, c cVar) {
        this.f3496d = obj;
        this.f3497e = obj2;
        this.f3498f = cVar;
    }

    @Override // q3.a
    public final int a() {
        c cVar = this.f3498f;
        cVar.getClass();
        return cVar.f3362e;
    }

    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.f3498f.containsKey(obj);
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new e(this.f3496d, this.f3498f);
    }
}
