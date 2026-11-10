package com.oplus.systemui.media.seedling;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.systemui.media.seedling.client.AbsMediaClient;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController$mediaDataUpdateListener$1 implements MediaDataManager.Listener {
    public /* synthetic */ OplusMediaSeedlingController this$0;

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super.onCurrentActiveMediaChanged(str, mediaData);
    }

    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
        if (OsBinderCacheUtils.getCurrentUserId() != mediaData.getUserId() && !OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(mediaData.getUserId()))) {
            OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "currentUserId " + OsBinderCacheUtils.getCurrentUserId() + " userId " + mediaData.getUserId());
            return;
        }
        AbsMediaClient absMediaClientAccess$createMediaClient = (AbsMediaClient) oplusMediaSeedlingController.mediaClients.get(str);
        if (absMediaClientAccess$createMediaClient == null) {
            OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onMediaDataLoaded: createMediaClient packageName=" + mediaData.getPackageName() + " key=" + str);
            absMediaClientAccess$createMediaClient = OplusMediaSeedlingController.access$createMediaClient(oplusMediaSeedlingController, str, mediaData);
            absMediaClientAccess$createMediaClient.onCreate();
            oplusMediaSeedlingController.mediaClients.put(str, absMediaClientAccess$createMediaClient);
        } else {
            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("warning: onMediaDataLoaded: old mediaClient is not null! key=", str, "OplusMediaSeedlingController");
        }
        absMediaClientAccess$createMediaClient.onMediaDataLoaded(mediaData);
        if (Intrinsics.areEqual(str, str2) || str2 == null) {
            return;
        }
        OplusMediaSeedlingController.access$removeMediaClient(oplusMediaSeedlingController, str2);
    }

    public final void onMediaDataRemoved(String str, boolean z) {
        OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("onMediaDataRemoved: ", str, "OplusMediaSeedlingController");
        OplusMediaSeedlingController.access$removeMediaClient(this.this$0, str);
    }
}
