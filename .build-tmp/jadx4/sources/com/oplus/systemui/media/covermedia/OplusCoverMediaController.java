package com.oplus.systemui.media.covermedia;

import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.media.OplusMediaControllerEx;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCoverMediaController extends ViewController implements OplusMediaControllerEx.IMediaListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCoverMediaControll";
    private MediaData mCurData;
    private String mCurKeyHandling;
    private String mCurOldKeyHandling;
    private final MediaDataManager mediaDataManager;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusCoverMediaController(OplusCoverMediaView oplusCoverMediaView, MediaDataManager mediaDataManager) {
        super(oplusCoverMediaView);
        this.mediaDataManager = mediaDataManager;
    }

    public final MediaDataManager getMediaDataManager() {
        return this.mediaDataManager;
    }

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super/*com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener*/.onCurrentActiveMediaChanged(str, mediaData);
    }

    public void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        MediaData mediaData2;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("Cover onMediaDataLoaded() called with: curKeyHandling = [", str, "]. key = [", str, "], oldKey = [");
        sbM.append(str2);
        sbM.append("], data = [");
        sbM.append(mediaData);
        sbM.append("], immediately = [");
        sbM.append(z);
        sbM.append("] ");
        OplusNotificationLogger.logCoverMediaPanel(TAG, sbM.toString());
        this.mCurKeyHandling = str;
        this.mCurOldKeyHandling = str2;
        this.mCurData = mediaData;
        OplusNotificationLogger.logCoverMediaPanel(TAG, "bindDataForView() called, key = " + str);
        String str3 = this.mCurKeyHandling;
        if (str3 != null && (mediaData2 = this.mCurData) != null) {
            ((OplusCoverMediaView) ((ViewController) this).mView).bindPlayer(mediaData2, str3);
            return;
        }
        OplusNotificationLogger.logCoverMediaPanel(TAG, "Error!! bindDataForView() bind Failed!!! curData = " + this.mCurData + ", curKey = " + str3);
    }

    public void onMediaDataRemoved(String str, boolean z) {
        OplusNotificationLogger.logCoverMediaPanel(TAG, "COVER onMediaDataRemoved() called with: key = [" + str + "]");
        OplusNotificationLogger.logCoverMediaPanel(TAG, "unbindMediaData");
        if (Intrinsics.areEqual(str, this.mCurKeyHandling)) {
            this.mCurKeyHandling = null;
            this.mCurOldKeyHandling = null;
            this.mCurData = null;
        }
    }

    public final void onViewAttached() {
        OplusNotificationLogger.logCoverMediaPanel(TAG, "onViewAttached");
        MediaDataManager mediaDataManager = this.mediaDataManager;
        if (mediaDataManager != null) {
            mediaDataManager.addListener(this);
        }
    }

    public final void onViewDetached() {
        OplusNotificationLogger.logCoverMediaPanel(TAG, "onViewDetached");
        MediaDataManager mediaDataManager = this.mediaDataManager;
        if (mediaDataManager != null) {
            mediaDataManager.removeListener(this);
        }
    }
}
