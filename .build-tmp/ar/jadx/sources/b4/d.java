package b4;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements f4.a, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient f4.a f680d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f681e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f682f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f683g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f684h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f685i;

    public d(Object obj, Class cls, String str, String str2, boolean z2) {
        this.f681e = obj;
        this.f682f = cls;
        this.f683g = str;
        this.f684h = str2;
        this.f685i = z2;
    }

    public abstract f4.a d();

    public final e e() {
        boolean z2 = this.f685i;
        Class cls = this.f682f;
        if (!z2) {
            return x.a(cls);
        }
        x.f698a.getClass();
        return new p(cls);
    }
}
