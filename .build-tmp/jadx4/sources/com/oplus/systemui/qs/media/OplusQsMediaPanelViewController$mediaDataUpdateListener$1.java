package com.oplus.systemui.qs.media;

import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.util.ViewController;
import com.oplus.multiapp.OplusMultiAppManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaPanelViewController$mediaDataUpdateListener$1 implements MediaDataManager.Listener {
    public /* synthetic */ OplusQsMediaPanelViewController this$0;

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super.onCurrentActiveMediaChanged(str, mediaData);
    }

    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        if (OsBinderCacheUtils.getCurrentUserId() != mediaData.getUserId() && !OplusMultiAppManager.getInstance().isMultiAppUserId(mediaData.getUserId())) {
            OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onMediaDataLoaded getCurrentUserId is " + OsBinderCacheUtils.getCurrentUserId() + "data.userId is " + mediaData.getUserId());
            return;
        }
        OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onMediaDataLoaded: " + MediaDataExtKt.printInfo(mediaData, true) + ", controller: " + this);
        MediaData mediaDataSelectPlayingOnes = OplusQsMediaUtil.selectPlayingOnes();
        if (!Intrinsics.areEqual(mediaDataSelectPlayingOnes != null ? mediaDataSelectPlayingOnes.getPackageName() : null, mediaData.getPackageName())) {
            OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onMediaDataLoaded selectData: " + MediaDataExtKt.printInfo(mediaDataSelectPlayingOnes, true));
        }
        ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).bindMediaData(mediaDataSelectPlayingOnes, "onMediaDataLoaded");
    }

    public final void onMediaDataRemoved(String str, boolean z) {
        OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onMediaDataRemoved: " + str);
        ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).unBindMediaData();
    }
}
