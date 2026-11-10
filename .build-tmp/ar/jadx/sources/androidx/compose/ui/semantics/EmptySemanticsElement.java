package androidx.compose.ui.semantics;

import p1.q0;
import r0.o;
import w1.c;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class EmptySemanticsElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f212a;

    public EmptySemanticsElement(c cVar) {
        this.f212a = cVar;
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // p1.q0
    public final o h() {
        return this.f212a;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // p1.q0
    public final /* bridge */ /* synthetic */ void i(o oVar) {
    }
}
