package com.oplus.systemui.qs.media;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplus.systemui.media.OplusMediaDataFilterExImpl;
import com.oplus.systemui.qs.OplusQSContainerImplController;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaControllerExImpl implements MediaDataManager.Listener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsMediaControllerExImpl";
    private MediaData currentMediaData;
    private IMediaModeChangeListener mediaModeChangeListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IMediaModeChangeListener {
    }

    public OplusQsMediaControllerExImpl(MediaDataManager mediaDataManager) {
        OplusNotificationLogger.logMediaPanel(TAG, "init mediaDataManager: " + mediaDataManager);
        mediaDataManager.addListener(this);
    }

    private final void setCurrentMediaData(MediaData mediaData) {
        MediaData mediaData2 = this.currentMediaData;
        if ((mediaData2 == null && mediaData != null) || (mediaData2 != null && mediaData == null)) {
            boolean z = mediaData != null;
            OplusNotificationLogger.logMediaPanel(TAG, "mediaMode changed: " + z);
            IMediaModeChangeListener iMediaModeChangeListener = this.mediaModeChangeListener;
            if (iMediaModeChangeListener != null) {
                ((OplusQSContainerImplController.AnonymousClass1) iMediaModeChangeListener).onChanged(z);
            }
        }
        this.currentMediaData = mediaData;
    }

    public final IMediaModeChangeListener getMediaModeChangeListener() {
        return this.mediaModeChangeListener;
    }

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super.onCurrentActiveMediaChanged(str, mediaData);
    }

    public void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isMediaShowSupport) {
            return;
        }
        OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey firstActiveMediaSortKey = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getFirstActiveMediaSortKey();
        setCurrentMediaData(firstActiveMediaSortKey != null ? firstActiveMediaSortKey.getData() : null);
    }

    public void onMediaDataRemoved(String str, boolean z) {
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isMediaShowSupport) {
            return;
        }
        OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey firstActiveMediaSortKey = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getFirstActiveMediaSortKey();
        setCurrentMediaData(firstActiveMediaSortKey != null ? firstActiveMediaSortKey.getData() : null);
    }

    public final void setMediaModeChangeListener(IMediaModeChangeListener iMediaModeChangeListener) {
        if (iMediaModeChangeListener != null) {
            ((OplusQSContainerImplController.AnonymousClass1) iMediaModeChangeListener).onChanged(this.currentMediaData != null);
        }
        this.mediaModeChangeListener = iMediaModeChangeListener;
    }
}
