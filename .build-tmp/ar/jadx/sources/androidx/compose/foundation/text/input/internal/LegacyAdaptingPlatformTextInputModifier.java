package androidx.compose.foundation.text.input.internal;

import b4.l;
import p1.q0;
import r0.o;
import v.o0;
import x.c;
import x.q;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class LegacyAdaptingPlatformTextInputModifier extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o0 f140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e0 f141c;

    public LegacyAdaptingPlatformTextInputModifier(c cVar, o0 o0Var, e0 e0Var) {
        this.f139a = cVar;
        this.f140b = o0Var;
        this.f141c = e0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LegacyAdaptingPlatformTextInputModifier)) {
            return false;
        }
        LegacyAdaptingPlatformTextInputModifier legacyAdaptingPlatformTextInputModifier = (LegacyAdaptingPlatformTextInputModifier) obj;
        return l.a(this.f139a, legacyAdaptingPlatformTextInputModifier.f139a) && l.a(this.f140b, legacyAdaptingPlatformTextInputModifier.f140b) && l.a(this.f141c, legacyAdaptingPlatformTextInputModifier.f141c);
    }

    @Override // p1.q0
    public final o h() {
        return new q(this.f139a, this.f140b, this.f141c);
    }

    public final int hashCode() {
        return this.f141c.hashCode() + ((this.f140b.hashCode() + (this.f139a.hashCode() * 31)) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        q qVar = (q) oVar;
        if (qVar.f5693p) {
            qVar.f6478q.g();
            qVar.f6478q.k(qVar);
        }
        c cVar = this.f139a;
        qVar.f6478q = cVar;
        if (qVar.f5693p) {
            if (cVar.f6463a != null) {
                throw new IllegalStateException("Expected textInputModifierNode to be null");
            }
            cVar.f6463a = qVar;
        }
        qVar.f6479r = this.f140b;
        qVar.f6480s = this.f141c;
    }

    public final String toString() {
        return "LegacyAdaptingPlatformTextInputModifier(serviceAdapter=" + this.f139a + ", legacyTextFieldState=" + this.f140b + ", textFieldSelectionManager=" + this.f141c + ')';
    }
}
