package androidx.compose.foundation.layout;

import p1.q0;
import r.l;
import r0.h;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class BoxChildDataElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f89a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f90b;

    public BoxChildDataElement(h hVar, boolean z2) {
        this.f89a = hVar;
        this.f90b = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        BoxChildDataElement boxChildDataElement = obj instanceof BoxChildDataElement ? (BoxChildDataElement) obj : null;
        return boxChildDataElement != null && this.f89a.equals(boxChildDataElement.f89a) && this.f90b == boxChildDataElement.f90b;
    }

    @Override // p1.q0
    public final o h() {
        l lVar = new l();
        lVar.f5582q = this.f89a;
        lVar.f5583r = this.f90b;
        return lVar;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f90b) + (this.f89a.hashCode() * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        l lVar = (l) oVar;
        lVar.f5582q = this.f89a;
        lVar.f5583r = this.f90b;
    }
}
