package com.oplus.systemui.qs.media;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDialogController$mediaDataUpdateListener$1 implements MediaDataManager.Listener {
    public /* synthetic */ OplusQsMediaDialogController this$0;

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super.onCurrentActiveMediaChanged(str, mediaData);
    }

    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0.mDialog;
        if (oplusQsMediaDetailPanelDialog != null) {
            oplusQsMediaDetailPanelDialog.onMediaDataLoaded(mediaData);
        }
    }

    public final void onMediaDataRemoved(String str, boolean z) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0.mDialog;
        if (oplusQsMediaDetailPanelDialog != null) {
            oplusQsMediaDetailPanelDialog.onMediaDataRemoved(str, z);
        }
        OplusNotificationLogger.logMediaPanel(OplusQsMediaDialogController.TAG, "onMediaDataRemoved, key is : " + str);
    }
}
