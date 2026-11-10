package com.oplusos.systemui.notification.commercialize;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CommercializeSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String KEY_UNIMPORTANT_EXEMPTION = "unimportant_exemption";
    public static final String KEY_UNIMPORTANT_NOTIFICAITON = "unimportant_notification";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getBlackListString(Context context) {
            return MultiUserContentHelper.Companion.getGlobalStringValue(context, CommercializeSettingsValueProxy.KEY_UNIMPORTANT_NOTIFICAITON);
        }

        public final String getUnimportantExemptionString(Context context) {
            if (context != null) {
                return MultiUserContentHelper.Companion.getGlobalStringValue(context, CommercializeSettingsValueProxy.KEY_UNIMPORTANT_EXEMPTION);
            }
            return null;
        }

        public final void putUnimportantExemptionString(Context context, String str) {
            MultiUserContentHelper.Companion.setGlobalStringValue(context, CommercializeSettingsValueProxy.KEY_UNIMPORTANT_EXEMPTION, str);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class NotificationBlackListAppObserver extends AbstractObserver {
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public String getKey() {
            return CommercializeSettingsValueProxy.KEY_UNIMPORTANT_NOTIFICAITON;
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public Function<String, Uri> getUri() {
            return AbstractObserver.Companion.toGlobalUri();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class UnimportantExemptionObserver extends AbstractObserver {
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public String getKey() {
            return CommercializeSettingsValueProxy.KEY_UNIMPORTANT_EXEMPTION;
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public Function<String, Uri> getUri() {
            return AbstractObserver.Companion.toGlobalUri();
        }
    }
}
