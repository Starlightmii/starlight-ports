package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationTopSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String KEY_TOP_NOTIFICATION = "top_notification";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getNotificationTopString(Context context, int i) {
            return AbsSettingsValueProxy.Companion.getSecureStringValue(context, NotificationTopSettingsValueProxy.KEY_TOP_NOTIFICATION, i);
        }

        public final void putNotificationTopString(Context context, String str, int i) {
            AbsSettingsValueProxy.Companion.setSecureStringValue(context, NotificationTopSettingsValueProxy.KEY_TOP_NOTIFICATION, str, i);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class NotificationTopListObserver extends AbstractObserver {
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public String getKey() {
            return NotificationTopSettingsValueProxy.KEY_TOP_NOTIFICATION;
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public Function<String, Uri> getUri() {
            return AbstractObserver.Companion.toSecureUri();
        }
    }
}
