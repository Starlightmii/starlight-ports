package androidx.compose.foundation.text.handwriting;

import b4.l;
import p1.q0;
import r0.o;
import w.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class StylusHandwritingElementWithNegativePadding extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a4.a f136a;

    public StylusHandwritingElementWithNegativePadding(a4.a aVar) {
        this.f136a = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StylusHandwritingElementWithNegativePadding) && l.a(this.f136a, ((StylusHandwritingElementWithNegativePadding) obj).f136a);
    }

    @Override // p1.q0
    public final o h() {
        return new b(this.f136a);
    }

    public final int hashCode() {
        return this.f136a.hashCode();
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((b) oVar).f6219s = this.f136a;
    }

    public final String toString() {
        return "StylusHandwritingElementWithNegativePadding(onHandwritingSlopExceeded=" + this.f136a + ')';
    }
}
