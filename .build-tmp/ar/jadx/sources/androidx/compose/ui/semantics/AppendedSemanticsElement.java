package androidx.compose.ui.semantics;

import a4.c;
import b4.l;
import p1.q0;
import r0.o;
import w1.b;
import w1.i;
import w1.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AppendedSemanticsElement extends q0 implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f210a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f211b;

    public AppendedSemanticsElement(c cVar, boolean z2) {
        this.f210a = z2;
        this.f211b = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppendedSemanticsElement)) {
            return false;
        }
        AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) obj;
        return this.f210a == appendedSemanticsElement.f210a && l.a(this.f211b, appendedSemanticsElement.f211b);
    }

    @Override // w1.j
    public final i g() {
        i iVar = new i();
        iVar.f6309e = this.f210a;
        this.f211b.c(iVar);
        return iVar;
    }

    @Override // p1.q0
    public final o h() {
        return new b(this.f210a, false, this.f211b);
    }

    public final int hashCode() {
        return this.f211b.hashCode() + (Boolean.hashCode(this.f210a) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        b bVar = (b) oVar;
        bVar.f6274q = this.f210a;
        bVar.f6276s = this.f211b;
    }

    public final String toString() {
        return "AppendedSemanticsElement(mergeDescendants=" + this.f210a + ", properties=" + this.f211b + ')';
    }
}
