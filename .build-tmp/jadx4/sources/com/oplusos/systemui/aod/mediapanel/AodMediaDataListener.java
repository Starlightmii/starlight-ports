package com.oplusos.systemui.aod.mediapanel;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaDataListener implements MediaDataManager.Listener {
    private static final String TAG = "AodMediaDataListener";
    private final Set<MediaDataManager.Listener> _listeners = new CopyOnWriteArraySet();
    private final AodMediaPanelSettingObserver aodMediaPanelSettingObserver;
    private final Context context;
    private MediaData curData;
    private String curKeyHandling;
    private String curOldKeyHandling;
    private final MediaDataManager mediaDataManager;
    public static final Companion Companion = new Companion();
    private static final List<String> aodMediaWhiteList = CollectionsKt__CollectionsKt.listOf(new String[]{"com.netease.cloudmusic", "com.kugou.android", "com.heytap.music", "com.tencent.qqmusic"});
    private static final List<String> aodMediaWhiteListExp = CollectionsKt__CollectionsJVMKt.listOf("com.spotify.music");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isAodMediaSupport(String str) {
            FeatureOption.INSTANCE.getClass();
            return FeatureOption.isExpRegion() ? CollectionsKt___CollectionsKt.contains(AodMediaDataListener.aodMediaWhiteListExp, str) : CollectionsKt___CollectionsKt.contains(AodMediaDataListener.aodMediaWhiteList, str);
        }

        public final boolean isAodMediaSupportWithoutFeature(String str) {
            return CollectionsKt___CollectionsKt.contains(AodMediaDataListener.aodMediaWhiteListExp, str) || CollectionsKt___CollectionsKt.contains(AodMediaDataListener.aodMediaWhiteList, str);
        }
    }

    public AodMediaDataListener(Context context, MediaDataManager mediaDataManager) {
        this.context = context;
        this.mediaDataManager = mediaDataManager;
        AodMediaPanelSettingObserver aodMediaPanelSettingObserver = new AodMediaPanelSettingObserver(context, new Handler(Looper.getMainLooper()));
        this.aodMediaPanelSettingObserver = aodMediaPanelSettingObserver;
        aodMediaPanelSettingObserver.register();
        mediaDataManager.addListener(this);
    }

    public final void addListener(MediaDataManager.Listener listener) {
        MediaData mediaData;
        this._listeners.add(listener);
        String str = this.curKeyHandling;
        if (str == null || (mediaData = this.curData) == null) {
            return;
        }
        String str2 = this.curOldKeyHandling;
        mediaData.getClass();
        listener.onMediaDataLoaded(str, str2, mediaData, true);
    }

    public final MediaData getCurData() {
        return this.curData;
    }

    public final String getCurKeyHandling() {
        return this.curKeyHandling;
    }

    public final String getCurOldKeyHandling() {
        return this.curOldKeyHandling;
    }

    public final boolean hasAodMediaData() {
        return this.curData != null;
    }

    public final boolean isAodMediaEnable() {
        return this.aodMediaPanelSettingObserver.getAodMediaEnable();
    }

    public /* bridge */ /* synthetic */ void onCurrentActiveMediaChanged(String str, MediaData mediaData) {
        super.onCurrentActiveMediaChanged(str, mediaData);
    }

    public void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z) {
        String mediaDataKey;
        if (!Companion.isAodMediaSupport(mediaData.getPackageName())) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "onMediaDataLoaded aod media not support for " + mediaData.getPackageName());
            return;
        }
        if (mediaData.getSemanticActions() == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "onMediaDataLoaded non semantic actions for " + mediaData.getPackageName());
            onMediaDataRemoved(str, true);
            return;
        }
        AodMediaData aodMediaData = AodMediaData.INSTANCE;
        aodMediaData.addMediaSortKey(str, str2, MediaData.copy$default(mediaData, 0, (CharSequence) null, (CharSequence) null, (Icon) null, (List) null, (List) null, (MediaButton) null, (String) null, (PendingIntent) null, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, (String) null, (Boolean) null, 0L, 0L, (InstanceId) null, 0, (OplusMediaDataEx) null, Integer.MAX_VALUE));
        AodMediaData.MediaSortKey firstActiveMediaSortKey = aodMediaData.getFirstActiveMediaSortKey();
        if (firstActiveMediaSortKey != null && (mediaDataKey = aodMediaData.getMediaDataKey(firstActiveMediaSortKey)) != null) {
            this.curKeyHandling = mediaDataKey;
            this.curOldKeyHandling = firstActiveMediaSortKey.getOldKey();
            this.curData = firstActiveMediaSortKey.getData();
        }
        String str3 = this.curKeyHandling;
        String strPrintInfo = MediaDataExtKt.printInfo(mediaData, true);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("AOD onMediaDataLoaded() called with: curKeyHandling = [", str3, "]. key = [", str, "], oldKey = [");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str2, "], data = [", strPrintInfo, "], immediately = [");
        sbM.append(z);
        sbM.append("] ");
        OplusNotificationLogger.logAodMediaPanel(TAG, sbM.toString());
        if (this.curKeyHandling == null || this.curData == null) {
            return;
        }
        for (MediaDataManager.Listener listener : this._listeners) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "onMediaDataLoaded: listener = " + listener);
            String str4 = this.curKeyHandling;
            str4.getClass();
            String str5 = this.curOldKeyHandling;
            MediaData mediaData2 = this.curData;
            mediaData2.getClass();
            listener.onMediaDataLoaded(str4, str5, mediaData2, true);
        }
    }

    public void onMediaDataRemoved(String str, boolean z) {
        String mediaDataKey;
        OplusNotificationLogger.logAodMediaPanel(TAG, "AOD onMediaDataRemoved() called with: key = [" + str + "]");
        AodMediaData aodMediaData = AodMediaData.INSTANCE;
        Boolean boolRemoveMediaSortKey = aodMediaData.removeMediaSortKey(str);
        if (Intrinsics.areEqual(str, this.curKeyHandling)) {
            this.curKeyHandling = null;
            this.curOldKeyHandling = null;
            this.curData = null;
        }
        if (boolRemoveMediaSortKey != null) {
            if (!aodMediaData.getMediaSortKeySet().isEmpty()) {
                AodMediaData.MediaSortKey firstActiveMediaSortKey = aodMediaData.getFirstActiveMediaSortKey();
                if (firstActiveMediaSortKey == null || (mediaDataKey = aodMediaData.getMediaDataKey(firstActiveMediaSortKey)) == null) {
                    return;
                }
                onMediaDataLoaded(mediaDataKey, mediaDataKey, firstActiveMediaSortKey.getData(), true);
                return;
            }
            for (MediaDataManager.Listener listener : CollectionsKt___CollectionsKt.toSet(this._listeners)) {
                OplusNotificationLogger.logAodMediaPanel(TAG, "onMediaDataRemoved: listener = " + listener);
                listener.onMediaDataRemoved(str, z);
            }
        }
    }

    public final void removeListener(MediaDataManager.Listener listener) {
        this._listeners.remove(listener);
    }
}
