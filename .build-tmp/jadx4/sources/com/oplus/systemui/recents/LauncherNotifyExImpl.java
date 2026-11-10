package com.oplus.systemui.recents;

import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.LauncherNotifyEx;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LauncherNotifyExImpl extends LauncherNotifyEx implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LauncherNotifyExImpl";
    public boolean isInRecents;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "LauncherNotifyExImpl isInRecents: ", this.isInRecents);
    }

    public final boolean isInRecents() {
        return this.isInRecents;
    }

    public final void notifySwipeToRecentFinished(boolean z) {
        this.isInRecents = z;
    }
}
