package q1;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b3 extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k4.c f5133a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b3(k4.c cVar, Handler handler) {
        super(handler);
        this.f5133a = cVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z2, Uri uri) {
        this.f5133a.o(p3.n.f5026a);
    }
}
