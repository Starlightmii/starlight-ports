package com.oplus.systemui.media.controls.pipeline;

import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.pipeline.OplusMediaDataManagerEx;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.media.OplusMediaPlayerInfo;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaPlayerManager$oplusMediaDataCallback$1 implements OplusMediaDataManagerEx.OplusMediaDataCallBack {
    public /* synthetic */ OplusMediaPlayerManager this$0;

    public final void onPlayerInfoChange(List list) {
        boolean zIsEmpty = list.isEmpty();
        OplusMediaPlayerManager oplusMediaPlayerManager = this.this$0;
        if (zIsEmpty) {
            OplusNotificationLogger.logMediaPanelNormal(OplusMediaPlayerManager.TAG, "DEBUG_MEDIA: ".concat("onPlayerInfoChange mediaPlayerInfos is empty"));
            oplusMediaPlayerManager.getOplusMediaDataModelImpl().startObserveEvent();
            return;
        }
        oplusMediaPlayerManager.oplusMediaPlayerDataInfoList.clear();
        for (OplusMediaPlayerInfo oplusMediaPlayerInfo : CollectionsKt___CollectionsKt.reversed(list)) {
            OplusNotificationLogger.logMediaPanelNormal(OplusMediaPlayerManager.TAG, SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onPlayerInfoChange mediaPlayerInfos:", oplusMediaPlayerInfo.getPlayerId(), ", ", oplusMediaPlayerInfo.getPackageName(), "DEBUG_MEDIA: "));
            oplusMediaPlayerManager.oplusMediaPlayerDataInfoList.add(oplusMediaPlayerInfo);
            oplusMediaPlayerManager.getOplusMediaDataModelImpl().requestMediaMetadata(oplusMediaPlayerInfo.getPlayerId());
        }
        oplusMediaPlayerManager.notifyMediaPlayerDataInfoChanged(OsBinderCacheUtils.getCurrentUserId(), oplusMediaPlayerManager.oplusMediaPlayerDataInfoList);
        oplusMediaPlayerManager.getOplusMediaDataModelImpl().startObserveEvent();
    }
}
