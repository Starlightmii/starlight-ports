package h1;

import android.view.KeyEvent;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends o implements d {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public a4.c f2613q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a4.c f2614r;

    @Override // h1.d
    public final boolean N(KeyEvent keyEvent) {
        a4.c cVar = this.f2613q;
        if (cVar != null) {
            return ((Boolean) cVar.c(new b(keyEvent))).booleanValue();
        }
        return false;
    }

    @Override // h1.d
    public final boolean i(KeyEvent keyEvent) {
        a4.c cVar = this.f2614r;
        if (cVar != null) {
            return ((Boolean) cVar.c(new b(keyEvent))).booleanValue();
        }
        return false;
    }
}
