package androidx.compose.ui.input.key;

import a4.c;
import b4.l;
import h1.e;
import p1.q0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class KeyInputElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f195b;

    public KeyInputElement(c cVar, c cVar2) {
        this.f194a = cVar;
        this.f195b = cVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyInputElement)) {
            return false;
        }
        KeyInputElement keyInputElement = (KeyInputElement) obj;
        return l.a(this.f194a, keyInputElement.f194a) && l.a(this.f195b, keyInputElement.f195b);
    }

    @Override // p1.q0
    public final o h() {
        e eVar = new e();
        eVar.f2613q = this.f194a;
        eVar.f2614r = this.f195b;
        return eVar;
    }

    public final int hashCode() {
        c cVar = this.f194a;
        int iHashCode = (cVar == null ? 0 : cVar.hashCode()) * 31;
        c cVar2 = this.f195b;
        return iHashCode + (cVar2 != null ? cVar2.hashCode() : 0);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        e eVar = (e) oVar;
        eVar.f2613q = this.f194a;
        eVar.f2614r = this.f195b;
    }

    public final String toString() {
        return "KeyInputElement(onKeyEvent=" + this.f194a + ", onPreKeyEvent=" + this.f195b + ')';
    }
}
