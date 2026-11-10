package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplus.systemui.statusbar.policy.ZenModeControllerExImpl;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ZenModeKeyguardSettingsObserver extends AbstractObserver {
    public boolean mZenModeKeyguardScreenStatus = false;
    public boolean mZenModeKeyguardShowStatus = false;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int secureIntValue = AbsSettingsValueProxy.getSecureIntValue(context, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD, 1);
        this.mZenModeKeyguardScreenStatus = secureIntValue == 0;
        this.mZenModeKeyguardShowStatus = secureIntValue == 2;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onRegister(Context context) {
        super.onRegister(context);
        int secureIntValue = AbsSettingsValueProxy.getSecureIntValue(context, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD, 1);
        this.mZenModeKeyguardScreenStatus = secureIntValue == 0;
        this.mZenModeKeyguardShowStatus = secureIntValue == 2;
    }
}
