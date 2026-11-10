package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelPullEnableObserver extends AbstractObserver {
    public boolean mDisablePullStatusBar;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "oplus_customize_status_bar_enable_when_lock";
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
        update$4(context);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onRegister(Context context) {
        super.onRegister(context);
        update$4(context);
    }

    public void setDisablePullStatusBar(boolean z) {
        this.mDisablePullStatusBar = z;
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("setDisablePullStatusBar: disablePullStatusBar=", LogUtil.TAG_COMMON, "PanelPullEnableObserver", z);
    }

    public final void update$4(Context context) {
        this.mDisablePullStatusBar = AbsSettingsValueProxy.getSystemIntValue(context, "oplus_customize_status_bar_enable_when_lock", 1) == 0;
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_COMMON, "PanelPullEnableObserver", new StringBuilder("update: disablePullStatusBar="), this.mDisablePullStatusBar);
    }
}
