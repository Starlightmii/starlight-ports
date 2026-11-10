package h1;

import android.view.KeyEvent;
import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KeyEvent f2604a;

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return l.a(this.f2604a, ((b) obj).f2604a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2604a.hashCode();
    }

    public final String toString() {
        return "KeyEvent(nativeKeyEvent=" + this.f2604a + ')';
    }
}
