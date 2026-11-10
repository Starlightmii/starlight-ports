package com.oplus.systemui.qs.media;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.logging.InstanceId;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.models.OplusMediaDataModel;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.media.OplusMediaEvent;
import com.oplus.media.OplusMediaPlayerInfo;
import com.oplus.media.OplusPlaybackCommandData;
import com.oplus.systemui.media.OplusMediaDataFilterExImpl;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaUtil {
    public static final OplusQsMediaUtil INSTANCE = new OplusQsMediaUtil();
    private static final String TAG = "OplusQsMediaUtil";

    public static final List<MediaData> getHistoryPlayList(String str) {
        OplusMediaDataModel oplusMediaDataModel = (OplusMediaDataModel) DependencyEx.sDependency.getDependency(OplusMediaDataModel.class);
        ArrayList arrayList = new ArrayList();
        List historyMediaPlayerInfoList = oplusMediaDataModel.getHistoryMediaPlayerInfoList();
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : historyMediaPlayerInfoList) {
            OplusMediaPlayerInfo oplusMediaPlayerInfo = (OplusMediaPlayerInfo) obj;
            String packageName = oplusMediaPlayerInfo.getPackageName();
            String playerId = oplusMediaPlayerInfo.getPlayerId();
            MediaSession.Token token = oplusMediaPlayerInfo.getToken();
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("historyMediaPlayerInfoList: ", packageName, " ", playerId, " ");
            sbM.append(token);
            OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
            String packageName2 = oplusMediaPlayerInfo.getPackageName();
            String playerId2 = oplusMediaPlayerInfo.getPlayerId();
            MediaSession.Token token2 = oplusMediaPlayerInfo.getToken();
            if (hashSet.add(packageName2 + "_" + playerId2 + "_" + (token2 != null ? Integer.valueOf(token2.hashCode()) : ""))) {
                arrayList2.add(obj);
            }
        }
        List<OplusMediaPlayerInfo> mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList2);
        OplusNotificationLogger.logMediaPanel(TAG, "historyMediaPlayerInfoList: " + mutableList.size() + " userId is " + OsBinderCacheUtils.getCurrentUserId());
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            getHistoryPlayList.1 r2 = new getHistoryPlayList.1();
            r2.$superPowerListString = str;
            VarHandle.storeStoreFence();
            mutableList.removeIf(r2);
        }
        for (OplusMediaPlayerInfo oplusMediaPlayerInfo2 : mutableList) {
            arrayList.add(MediaData.copy$default(OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getEMPTY(), OsBinderCacheUtils.getCurrentUserId(), (CharSequence) null, (CharSequence) null, (Icon) null, (List) null, (List) null, (MediaButton) null, oplusMediaPlayerInfo2.getPackageName(), (PendingIntent) null, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, oplusMediaPlayerInfo2.getPlayerId(), (Boolean) null, 0L, 0L, (InstanceId) null, 0, (OplusMediaDataEx) null, 2146958334));
        }
        return arrayList;
    }

    public static final void handleResumeApp(MediaData mediaData, Context context) {
        OplusNotificationLogger.logMediaPanel(TAG, "handleResumeAppTask----> playId is " + mediaData.getNotificationKey());
        try {
            OplusMediaEvent.Builder data = new OplusMediaEvent.Builder(1).setData(new OplusPlaybackCommandData(1));
            if (!TextUtils.isEmpty(mediaData.getNotificationKey())) {
                data.setPlayerId(mediaData.getNotificationKey());
            }
            QsUtil.getAndUnFreezePackage(mediaData.getPackageName(), mediaData.getUserId(), context);
            ((OplusMediaDataModel) DependencyEx.sDependency.getDependency(OplusMediaDataModel.class)).sendEvent(data.build());
        } catch (RemoteException unused) {
            OplusNotificationLogger.logMediaPanel(TAG, "mediaControlCenter sendEvent exception");
        }
    }

    public static final MediaData selectByPackageOrToken(String str, MediaSession.Token token) {
        MediaData mediaDataByPackageName;
        MediaData mediaDataByToken;
        if (token != null && (mediaDataByToken = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getMediaDataByToken(token)) != null) {
            return mediaDataByToken;
        }
        if (str == null || str.length() == 0 || (mediaDataByPackageName = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getMediaDataByPackageName(str)) == null) {
            return null;
        }
        return mediaDataByPackageName;
    }

    public static final MediaData selectPlayingOnes() {
        OplusMediaDataFilterExImpl.MediaPlayerData mediaPlayerData = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE;
        OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey firstActiveMediaSortKey = mediaPlayerData.getFirstActiveMediaSortKey();
        if (firstActiveMediaSortKey == null || mediaPlayerData.getMediaDataKey(firstActiveMediaSortKey) == null) {
            return null;
        }
        return firstActiveMediaSortKey.getData();
    }
}
