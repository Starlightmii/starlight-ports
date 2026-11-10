package com.oplus.systemui.separate.guide;

import android.content.Context;
import android.content.res.Configuration;
import com.android.systemui.plugins.qs.GuideEvent;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventController$specialModeUpdateMonitorListener$2$1 implements QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener {
    public /* synthetic */ OplusQsGuideEventController this$0;

    public final void onConfigChanged(Configuration configuration) {
        int i = configuration.orientation;
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        if (i != oplusQsGuideEventController.orientation) {
            oplusQsGuideEventController.orientation = i;
            oplusQsGuideEventController.notifyGuideEvent(GuideEvent.SCREEN_DIRECTION_CHANGE);
        }
    }

    public final void onSuperPowerSaveModeChanged() {
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        oplusQsGuideEventController.specialModeUpdateMonitor.getClass();
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            oplusQsGuideEventController.notifyGuideEvent(GuideEvent.SUPER_SAVE_MODE_CHANGE);
        }
    }

    public final void onUserChanged(int i, Context context) {
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        GuideEvent guideEvent = GuideEvent.MAIN_USER_CHANGE;
        OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
        oplusQsGuideEventController.notifyGuideEvent(guideEvent);
    }
}
