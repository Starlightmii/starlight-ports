package com.oplus.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisablerEx;
import com.oplus.systemui.navigationbar.observer.SystemFoldModeObserver;
import com.oplusos.systemui.common.utils.DeviceUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoteInputQuickSettingsDisablerExImpl extends RemoteInputQuickSettingsDisablerEx {
    private final Context context;

    public RemoteInputQuickSettingsDisablerExImpl(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isFoldOpenAndLarge() {
        return SystemFoldModeObserver.Companion.getInstance().isFoldOpen() && DeviceUtil.shouldUseLargeFoldConfig(this.context.getResources());
    }
}
