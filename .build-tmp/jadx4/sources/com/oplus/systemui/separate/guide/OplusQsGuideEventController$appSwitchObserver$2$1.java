package com.oplus.systemui.separate.guide;

import android.util.Log;
import com.android.systemui.plugins.qs.GuideEvent;
import com.oplus.app.OplusAppEnterInfo;
import com.oplus.app.OplusAppExitInfo;
import com.oplus.app.OplusAppSwitchManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventController$appSwitchObserver$2$1 implements OplusAppSwitchManager.OnAppSwitchObserver {
    public /* synthetic */ OplusQsGuideEventController this$0;

    public final void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideEventController-->".concat("onActivityEnter : " + oplusAppEnterInfo));
        }
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        oplusQsGuideEventController.isLaunchAppAtTop = true;
        oplusQsGuideEventController.notifyGuideEvent(GuideEvent.LAUNCH_APP_ENTER);
    }

    public final void onActivityExit(OplusAppExitInfo oplusAppExitInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideEventController-->".concat("onActivityExit : " + oplusAppExitInfo));
        }
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        oplusQsGuideEventController.isLaunchAppAtTop = false;
        oplusQsGuideEventController.notifyGuideEvent(GuideEvent.LAUNCH_APP_EXIT);
    }

    public final void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideEventController-->".concat("onAppEnter : " + oplusAppEnterInfo));
        }
    }

    public final void onAppExit(OplusAppExitInfo oplusAppExitInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideEventController-->".concat("onAppExit : " + oplusAppExitInfo));
        }
    }
}
