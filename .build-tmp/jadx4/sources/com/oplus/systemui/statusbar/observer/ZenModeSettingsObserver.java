package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ZenModeSettingsObserver extends AbstractObserver {
    public boolean mZenModeStatusOn = false;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return OplusNoDisturbListener.ZEN_MODE_SETTING;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mZenModeStatusOn = AbsSettingsValueProxy.getGlobalIntValue(context, OplusNoDisturbListener.ZEN_MODE_SETTING, 0) == 1;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onRegister(Context context) {
        super.onRegister(context);
        this.mZenModeStatusOn = AbsSettingsValueProxy.getGlobalIntValue(context, OplusNoDisturbListener.ZEN_MODE_SETTING, 0) == 1;
    }
}
