package com.oplus.systemui.media.controls.pipeline;

import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import android.service.notification.StatusBarNotification;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerExImpl$createActionsFromNotification$1$1 implements Runnable {
    public /* synthetic */ List $actionIcons;
    public /* synthetic */ List $actionsToShowCollapsed;
    public /* synthetic */ String $key;
    public /* synthetic */ StatusBarNotification $sbn;
    public /* synthetic */ OplusMediaDataManagerExImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        MediaData mediaData = this.this$0.getMediaDataManager().getMediaData(this.$key);
        MediaData mediaDataCopy$default = mediaData != null ? MediaData.copy$default(mediaData, 0, (CharSequence) null, (CharSequence) null, (Icon) null, this.$actionIcons, this.$actionsToShowCollapsed, (MediaButton) null, (String) null, this.$sbn.getNotification().contentIntent, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, (String) null, (Boolean) null, 0L, 0L, (InstanceId) null, 0, OplusMediaDataEx.copy$default(mediaData.getMediaDataEx(), (MediaMetadata) null, (PlaybackState) null, 0, 0L, false, 2047), 1073737343) : null;
        MediaData mediaData2 = this.this$0.getMediaDataManager().getMediaData(this.$key);
        boolean z = false;
        if (mediaData2 != null && this.$sbn.getNormalizedUserId() == mediaData2.getUserId()) {
            z = true;
        }
        if (mediaDataCopy$default != null && mediaDataCopy$default.getInitialized() && z) {
            mediaDataCopy$default.getMediaDataEx().setNoUpdateTime(true);
            MediaDataManager mediaDataManager = this.this$0.getMediaDataManager();
            String str = this.$key;
            mediaDataManager.onMediaDataLoaded(str, str, mediaDataCopy$default, "createActionsFromNotification");
            return;
        }
        OplusNotificationLogger.logMediaPanelNormal(OplusMediaDataManagerExImpl.TAG, "createActionsFromNotification media --key=" + this.$key + ", not current user}");
    }
}
