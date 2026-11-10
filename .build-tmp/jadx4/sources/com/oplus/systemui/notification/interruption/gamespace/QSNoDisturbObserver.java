package com.oplus.systemui.notification.interruption.gamespace;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSNoDisturbObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String QS_NO_DISTURB = "qs_no_disturb";
    private final Context context;
    public boolean isInGameSpaceDisturb;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSNoDisturbObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isBannerBlockedByQS() {
        return Settings.Secure.getIntForUser(this.context.getContentResolver(), "qs_no_disturb", -1, -2) == 1;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        this.isInGameSpaceDisturb = isBannerBlockedByQS();
    }

    public final void setInGameSpaceDisturb(boolean z) {
        this.isInGameSpaceDisturb = z;
    }
}
