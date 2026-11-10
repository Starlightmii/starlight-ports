package androidx.compose.foundation;

import n.a0;
import p1.q0;
import q.k;
import r0.m;
import r0.o;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    static {
        new q0() { // from class: androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1
            public final boolean equals(Object obj) {
                return this == obj;
            }

            @Override // p1.q0
            public final o h() {
                return new a0();
            }

            public final int hashCode() {
                return System.identityHashCode(this);
            }

            @Override // p1.q0
            public final /* bridge */ /* synthetic */ void i(o oVar) {
            }
        };
    }

    public static final p a(p pVar, boolean z2, k kVar) {
        return pVar.e(z2 ? new FocusableElement(kVar) : m.f5680a);
    }
}
