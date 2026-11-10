package com.oplus.systemui.statusbar.observer;

import android.net.Uri;
import android.os.Handler;
import com.oplus.systemui.statusbar.proxy.StatusBarSettingsValueProxy;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationPromptModeObserver extends AbstractObserver {
    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Handler getHandler() {
        return new Handler();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return StatusBarSettingsValueProxy.KEY_NOTIFICATION_PROMPT_MODE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }
}
