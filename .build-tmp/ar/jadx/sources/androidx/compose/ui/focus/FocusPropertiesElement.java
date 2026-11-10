package androidx.compose.ui.focus;

import b4.l;
import p1.q0;
import r0.o;
import w0.h;
import w0.k;
import w0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class FocusPropertiesElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f172a;

    public FocusPropertiesElement(k kVar) {
        this.f172a = kVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusPropertiesElement) && l.a(this.f172a, ((FocusPropertiesElement) obj).f172a);
    }

    @Override // p1.q0
    public final o h() {
        m mVar = new m();
        mVar.f6253q = this.f172a;
        return mVar;
    }

    public final int hashCode() {
        return h.f6236f.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((m) oVar).f6253q = this.f172a;
    }

    public final String toString() {
        return "FocusPropertiesElement(scope=" + this.f172a + ')';
    }
}
