package g4;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2555b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a4.c f2556c;

    public /* synthetic */ d(Object obj, a4.c cVar, int i5) {
        this.f2554a = i5;
        this.f2555b = obj;
        this.f2556c = cVar;
    }

    @Override // g4.f
    public final Iterator iterator() {
        switch (this.f2554a) {
            case 0:
                return new c(this);
            default:
                return new e(this);
        }
    }
}
