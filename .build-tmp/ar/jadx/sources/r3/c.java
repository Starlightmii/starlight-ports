package r3;

import k2.k;
import p3.p;
import y0.h0;
import y0.j0;
import y0.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements q0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5719d;

    public /* synthetic */ c(int i5) {
        this.f5719d = i5;
    }

    @Override // y0.q0
    public j0 c(long j5, k kVar, k2.b bVar) {
        return new h0(p.b(0L, j5));
    }

    public String toString() {
        switch (this.f5719d) {
            case 2:
                return "RectangleShape";
            default:
                return super.toString();
        }
    }
}
