package com.oplus.systemui.statusbar.seeding;

import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$holeRectListener$1 implements StatusBarPaddingProvider.IScreenHoleChanged {
    public /* synthetic */ SeedlingPluginManager this$0;

    @Override // com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider.IScreenHoleChanged
    public final void onRectChanged(RectF rectF) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->".concat("onRectChanged: " + rectF));
        }
        Bundle bundle = new Bundle();
        bundle.putString("state", "onHoleRectChanged");
        bundle.putObject("rect", rectF);
        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }
}
