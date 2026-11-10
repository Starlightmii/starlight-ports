package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalBannerSwitchObserver extends AbstractObserver {
    private static final String GLOBAL_BANNER_SWITCH_STATUS = "global_banner_switch_status";
    private static final String TAG = "GlobalBannerSwitchObserver";
    public static final GlobalBannerSwitchObserver INSTANCE = new GlobalBannerSwitchObserver();
    public static volatile boolean globalBannerSwitchEnabled = true;

    public final boolean getGlobalBannerSwitchEnabled() {
        return globalBannerSwitchEnabled;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Handler getHandler() {
        return ThreadManager.Companion.getInstance().getThreadHandler();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return GLOBAL_BANNER_SWITCH_STATUS;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        globalBannerSwitchEnabled = AbsSettingsValueProxy.Companion.getSecureIntValue(context, GLOBAL_BANNER_SWITCH_STATUS, 1) == 1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onChange] globalBannerSwitchEnabled = ", "GlobalBannerSwitchObserver-->", "SystemUi--Notification", globalBannerSwitchEnabled);
        }
        super.onChange(z);
    }
}
