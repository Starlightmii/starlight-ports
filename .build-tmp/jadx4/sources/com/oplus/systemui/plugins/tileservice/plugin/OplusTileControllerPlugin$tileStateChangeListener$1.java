package com.oplus.systemui.plugins.tileservice.plugin;

import android.content.ComponentName;
import android.service.quicksettings.Tile;
import android.util.Log;
import com.android.systemui.plugins.qs.OplusTileController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileControllerPlugin$tileStateChangeListener$1 implements TileStateController.TileStateChangeListener {
    public /* synthetic */ OplusTileControllerPlugin this$0;

    @Override // com.oplus.systemui.plugins.tileservice.plugin.TileStateController.TileStateChangeListener
    public final void onSettingsStateChange(ComponentName componentName, int i) {
        Tile qsTile;
        Log.d("OplusTileControllerPlugin", "onSettingsStateChange: " + i + ", componentName: " + componentName);
        OplusTileControllerPlugin oplusTileControllerPlugin = this.this$0;
        OplusTileController.ITileServicesWrapper iTileServicesWrapper = oplusTileControllerPlugin.wrapper;
        if (iTileServicesWrapper == null || (qsTile = iTileServicesWrapper.getQsTile(componentName)) == null) {
            return;
        }
        qsTile.setState(i);
        OplusTileController.ITileServicesWrapper iTileServicesWrapper2 = oplusTileControllerPlugin.wrapper;
        if (iTileServicesWrapper2 != null) {
            iTileServicesWrapper2.updateQsTile(componentName, qsTile);
        }
    }

    @Override // com.oplus.systemui.plugins.tileservice.plugin.TileStateController.TileStateChangeListener
    public final void refreshState(ComponentName componentName) {
        Log.d("OplusTileControllerPlugin", "refreshState: componentName=" + componentName);
        OplusTileController.ITileServicesWrapper iTileServicesWrapper = this.this$0.wrapper;
        if (iTileServicesWrapper != null) {
            iTileServicesWrapper.refreshState(componentName);
        }
    }
}
