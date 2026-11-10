package com.oplusos.systemui.common.multiuser;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Trace;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiUserContentHolder {
    private Uri mUri;
    private WeakReference<ContentObserver> mWeakObserver;

    public MultiUserContentHolder(ContentObserver contentObserver, Uri uri) {
        this.mWeakObserver = new WeakReference<>(contentObserver);
        this.mUri = uri;
    }

    public final boolean isTarget(ContentObserver contentObserver) {
        return this.mWeakObserver.get() == contentObserver;
    }

    public final void onUserSwitch() {
        if (this.mWeakObserver.get() == null) {
            return;
        }
        ContentObserver contentObserver = this.mWeakObserver.get();
        contentObserver.getClass();
        Trace.traceBegin(8L, this.mUri.toString());
        contentObserver.dispatchChange(false);
        Trace.traceEnd(8L);
    }
}
