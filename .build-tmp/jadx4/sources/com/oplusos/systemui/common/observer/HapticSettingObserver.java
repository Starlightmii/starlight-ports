package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HapticSettingObserver extends AbstractObserver {
    public boolean mSystemHapticEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class ObserverHolder {
        public static final HapticSettingObserver sInstance;

        static {
            HapticSettingObserver hapticSettingObserver = new HapticSettingObserver();
            hapticSettingObserver.mSystemHapticEnabled = false;
            sInstance = hapticSettingObserver;
        }
    }

    public static HapticSettingObserver getInstance() {
        return ObserverHolder.sInstance;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_HAPTIC_FEEDBACK;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mSystemHapticEnabled = CommonSettingsValueProxy.getHapticFeedbackState(context) == 1;
        super.onChange(z);
    }
}
