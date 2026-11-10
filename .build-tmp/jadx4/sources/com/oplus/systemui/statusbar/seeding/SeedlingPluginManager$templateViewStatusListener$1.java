package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.systemui.plugins.template.ITemplateViewWrapper;
import com.android.systemui.plugins.template.TemplateProxy;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$templateViewStatusListener$1 implements TemplateProxy.Listener {
    public final void onLoadFailure(int i, String str) {
    }

    public final void onLoadSuccess(ITemplateViewWrapper iTemplateViewWrapper) {
        LogUtil.i$default(LogUtil.TAG_AOD, "SeedlingPluginManager", "onLoadSuccess. view:" + iTemplateViewWrapper, null, 8);
    }

    public final void onStatusChanged(String str, Bundle bundle) {
    }

    public final void onUpdateFailure(int i, String str) {
    }

    public final void onUpdateSuccess() {
    }
}
