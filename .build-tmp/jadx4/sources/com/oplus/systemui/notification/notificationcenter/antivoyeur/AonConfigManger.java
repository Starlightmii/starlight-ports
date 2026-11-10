package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface AonConfigManger {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final AonConfigManger create(Context context) {
            return new AonConfigMangerImpl(context);
        }
    }

    void notifyPkgRemoved(String str, boolean z);

    void setAllowAntiVoyeurEncryptedState(boolean z);
}
