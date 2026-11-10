package androidx.compose.ui.layout;

import n1.q;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class LayoutIdElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f200a;

    public LayoutIdElement(String str) {
        this.f200a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutIdElement) && this.f200a.equals(((LayoutIdElement) obj).f200a);
    }

    @Override // p1.q0
    public final o h() {
        q qVar = new q();
        qVar.f4147q = this.f200a;
        return qVar;
    }

    public final int hashCode() {
        return this.f200a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((q) oVar).f4147q = this.f200a;
    }

    public final String toString() {
        return "LayoutIdElement(layoutId=" + ((Object) this.f200a) + ')';
    }
}
