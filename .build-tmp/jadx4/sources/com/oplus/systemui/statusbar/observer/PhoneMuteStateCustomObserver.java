package com.oplus.systemui.statusbar.observer;

import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PhoneMuteStateCustomObserver extends AbstractObserver {
    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.PHONE_MUTE_STATE_FOR_CUSTOM;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }
}
