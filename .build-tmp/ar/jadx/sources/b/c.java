package b;

import android.window.BackEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f315c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f316d;

    public c(BackEvent backEvent) {
        a aVar = a.f308a;
        float fD = aVar.d(backEvent);
        float fE = aVar.e(backEvent);
        float fB = aVar.b(backEvent);
        int iC = aVar.c(backEvent);
        this.f313a = fD;
        this.f314b = fE;
        this.f315c = fB;
        this.f316d = iC;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BackEventCompat{touchX=");
        sb.append(this.f313a);
        sb.append(", touchY=");
        sb.append(this.f314b);
        sb.append(", progress=");
        sb.append(this.f315c);
        sb.append(", swipeEdge=");
        return b.f(sb, this.f316d, '}');
    }
}
