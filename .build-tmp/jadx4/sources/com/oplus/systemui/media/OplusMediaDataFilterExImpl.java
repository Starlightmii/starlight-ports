package com.oplus.systemui.media;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.logging.InstanceId;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.pipeline.OplusMediaDataFilterEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataFilterExImpl extends OplusMediaDataFilterEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaDataFilterExImpl";
    private String curKey;
    private MediaData curMediaData;
    private String curOldKey;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MediaPlayerData {
        public static final String TAG = "MediaPlayerData";
        private static final Comparator<MediaSortKey> mComparator;
        private static final Map<String, MediaSortKey> mediaDataMap;
        private static final TreeSet<MediaSortKey> mediaSortKeySet;
        public static final MediaPlayerData INSTANCE = new MediaPlayerData();
        private static final MediaData EMPTY = new MediaData(-1, false, (String) null, (Icon) null, (CharSequence) null, (CharSequence) null, (Icon) null, CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), (MediaButton) null, "INVALID", (MediaSession.Token) null, (PendingIntent) null, (MediaDeviceData) null, true, (Runnable) null, 0, (String) null, false, (Boolean) null, false, 0, 0, InstanceId.fakeInstanceId(-1), -1, false, (Double) null, (OplusMediaDataEx) null, 1946042880);

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class MediaSortKey {
            private final MediaData data;
            private final String oldKey;
            public final long updateTime;

            public MediaSortKey(MediaData mediaData, String str, long j) {
                this.data = mediaData;
                this.oldKey = str;
                this.updateTime = j;
            }

            public final MediaData component1() {
                return this.data;
            }

            public final String component2() {
                return this.oldKey;
            }

            public final MediaSortKey copy(MediaData mediaData, String str, long j) {
                return new MediaSortKey(mediaData, str, j);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MediaSortKey)) {
                    return false;
                }
                MediaSortKey mediaSortKey = (MediaSortKey) obj;
                return Intrinsics.areEqual(this.data, mediaSortKey.data) && Intrinsics.areEqual(this.oldKey, mediaSortKey.oldKey) && this.updateTime == mediaSortKey.updateTime;
            }

            public final MediaData getData() {
                return this.data;
            }

            public final String getOldKey() {
                return this.oldKey;
            }

            public final long getUpdateTime() {
                return this.updateTime;
            }

            public final int hashCode() {
                int iHashCode = this.data.hashCode() * 31;
                String str = this.oldKey;
                return Long.hashCode(this.updateTime) + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31);
            }

            public String toString() {
                MediaData mediaData = this.data;
                String str = this.oldKey;
                StringBuilder sb = new StringBuilder("MediaSortKey(data=");
                sb.append(mediaData);
                sb.append(", oldKey=");
                sb.append(str);
                sb.append(", updateTime=");
                return MediaSessionCompat$QueueItem$.ExternalSyntheticOutline0.m(this.updateTime, ")", sb);
            }
        }

        static {
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1();
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(0);
            oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1.$this_thenByDescending = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1;
            VarHandle.storeStoreFence();
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$2 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(1);
            oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$2.$this_thenByDescending = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1;
            VarHandle.storeStoreFence();
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$3 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(2);
            oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$3.$this_thenByDescending = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$2;
            VarHandle.storeStoreFence();
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$4 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(3);
            oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$4.$this_thenByDescending = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$3;
            VarHandle.storeStoreFence();
            OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$5 = new OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(4);
            oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$5.$this_thenByDescending = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$4;
            VarHandle.storeStoreFence();
            mComparator = oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$5;
            mediaSortKeySet = new TreeSet<>(oplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$5);
            mediaDataMap = new LinkedHashMap();
        }

        public final void addMediaSortKey(String str, String str2, MediaData mediaData) {
            MediaData data;
            if (str2 != null && !str2.equals(str)) {
                OplusNotificationLogger.logMediaPanel(TAG, "removeOldMediaSortKey key:" + str2 + ", result:" + removeMediaSortKey(str2));
            }
            Map<String, MediaSortKey> map = mediaDataMap;
            MediaSortKey mediaSortKey = map.get(str);
            long updateTime = mediaSortKey != null ? mediaSortKey.getUpdateTime() : 0L;
            boolean z = false;
            boolean zAreEqual = (mediaSortKey == null || (data = mediaSortKey.getData()) == null) ? false : Intrinsics.areEqual(data.isPlaying(), Boolean.TRUE);
            boolean zAreEqual2 = Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE);
            if (mediaSortKey != null && zAreEqual2 == zAreEqual) {
                z = true;
            }
            long jCurrentTimeMillis = (mediaSortKey == null || !(mediaData.getMediaDataEx().getNoUpdateTime() || z)) ? System.currentTimeMillis() : mediaSortKey.getUpdateTime();
            Boolean boolRemoveMediaSortKey = removeMediaSortKey(str);
            MediaSortKey mediaSortKey2 = new MediaSortKey(mediaData, str2, jCurrentTimeMillis);
            map.put(str, mediaSortKey2);
            mediaSortKeySet.add(mediaSortKey2);
            String packageName = mediaData.getPackageName();
            boolean noUpdateTime = mediaData.getMediaDataEx().getNoUpdateTime();
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("addMediaSortKey newKey:", str, " oldKey:", str2, ", remove:");
            sbM.append(boolRemoveMediaSortKey);
            sbM.append(" packageName:");
            sbM.append(packageName);
            sbM.append(" playbackStateUnchanged:");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" newPlaying:", " oldPlaying:", sbM, z, zAreEqual2);
            sbM.append(zAreEqual);
            sbM.append(" updateTime:");
            sbM.append(jCurrentTimeMillis);
            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, " oldTime:", updateTime, " noUpdateTime:");
            sbM.append(noUpdateTime);
            OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
        }

        public final MediaData getEMPTY() {
            return EMPTY;
        }

        public final MediaSortKey getFirstActiveMediaSortKey() {
            Set<Map.Entry<String, MediaSortKey>> setEntrySet = mediaDataMap.entrySet();
            Comparator<MediaSortKey> comparator = mComparator;
            Iterator<T> it = setEntrySet.iterator();
            if (!it.hasNext()) {
                return null;
            }
            MediaSortKey mediaSortKey = (MediaSortKey) ((Map.Entry) it.next()).getValue();
            while (it.hasNext()) {
                MediaSortKey mediaSortKey2 = (MediaSortKey) ((Map.Entry) it.next()).getValue();
                if (comparator.compare(mediaSortKey, mediaSortKey2) > 0) {
                    mediaSortKey = mediaSortKey2;
                }
            }
            return mediaSortKey;
        }

        public final MediaData getMediaDataByPackageName(String str) {
            Collection<MediaSortKey> collectionValues = mediaDataMap.values();
            ArrayList arrayList = new ArrayList();
            for (Object obj : collectionValues) {
                if (Intrinsics.areEqual(((MediaSortKey) obj).getData().getPackageName(), str)) {
                    arrayList.add(obj);
                }
            }
            MediaSortKey mediaSortKey = (MediaSortKey) CollectionsKt___CollectionsKt.minWithOrNull(arrayList, mComparator);
            if (mediaSortKey != null) {
                return mediaSortKey.getData();
            }
            return null;
        }

        public final MediaData getMediaDataByToken(MediaSession.Token token) {
            Object next;
            Iterator<T> it = mediaDataMap.values().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((MediaSortKey) next).getData().getToken(), token));
            MediaSortKey mediaSortKey = (MediaSortKey) next;
            if (mediaSortKey != null) {
                return mediaSortKey.getData();
            }
            return null;
        }

        public final String getMediaDataKey(MediaSortKey mediaSortKey) {
            for (String str : mediaDataMap.keySet()) {
                if (Intrinsics.areEqual(mediaSortKey, mediaDataMap.get(str))) {
                    return str;
                }
            }
            return null;
        }

        public final int getMediaPlayerIndex(String str) {
            int i = 0;
            for (Object obj : mediaDataMap.entrySet()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), str)) {
                    return i;
                }
                i = i2;
            }
            return -1;
        }

        public final TreeSet<MediaSortKey> getMediaSortKeySet() {
            return mediaSortKeySet;
        }

        public final Boolean removeMediaSortKey(String str) {
            MediaSortKey mediaSortKeyRemove = mediaDataMap.remove(str);
            if (mediaSortKeyRemove != null) {
                return Boolean.valueOf(mediaSortKeySet.remove(mediaSortKeyRemove));
            }
            return null;
        }
    }

    public void dispatchMediaDataOnLoaded(String str, String str2, MediaData mediaData, Set<? extends MediaDataManager.Listener> set) {
        String mediaDataKey;
        if (filterMediaData(str, str2, mediaData, set)) {
            MediaPlayerData mediaPlayerData = MediaPlayerData.INSTANCE;
            mediaPlayerData.addMediaSortKey(str, str2, MediaData.copy$default(mediaData, 0, (CharSequence) null, (CharSequence) null, (Icon) null, (List) null, (List) null, (MediaButton) null, (String) null, (PendingIntent) null, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, (String) null, (Boolean) null, 0L, 0L, (InstanceId) null, 0, (OplusMediaDataEx) null, Integer.MAX_VALUE));
            this.curKey = str;
            this.curOldKey = str2;
            this.curMediaData = mediaData;
            MediaPlayerData.MediaSortKey firstActiveMediaSortKey = mediaPlayerData.getFirstActiveMediaSortKey();
            if (firstActiveMediaSortKey != null && (mediaDataKey = mediaPlayerData.getMediaDataKey(firstActiveMediaSortKey)) != null) {
                this.curKey = mediaDataKey;
                this.curOldKey = firstActiveMediaSortKey.getOldKey();
                this.curMediaData = firstActiveMediaSortKey.getData();
            }
            String strPrintInfo = MediaDataExtKt.printInfo(mediaData, true);
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("dispatchMediaDataOnLoaded key:", str, " oldKey:", str2, " data:");
            sbM.append(strPrintInfo);
            sbM.append(" listeners:");
            sbM.append(set);
            OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                ((MediaDataManager.Listener) it.next()).onMediaDataLoaded(str, str2, mediaData, true);
            }
        }
    }

    public void dispatchMediaDataOnRemove(String str, boolean z, Set<? extends MediaDataManager.Listener> set) {
        OplusNotificationLogger.logMediaPanel(TAG, "onMediaDataRemoved: " + str + " listeners: " + set);
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
        }
    }

    public boolean filterMediaData(String str, String str2, MediaData mediaData, Set<? extends MediaDataManager.Listener> set) {
        if (mediaData.getToken() != null) {
            return true;
        }
        String strPrintInfo = MediaDataExtKt.printInfo(mediaData, true);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("filterMediaData: key = ", str, ", oldKey: ", str2, ", data: ");
        sbM.append(strPrintInfo);
        OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
        if (str2 != null && !str2.equals(str)) {
            OplusNotificationLogger.logMediaPanel(TAG, "filterMediaData: oldKey: " + str2 + ", remove: " + MediaPlayerData.INSTANCE.removeMediaSortKey(str2));
        }
        if (Intrinsics.areEqual(str, this.curKey)) {
            this.curKey = null;
            this.curOldKey = null;
            this.curMediaData = null;
        }
        removePlayer(str, false, set);
        return false;
    }

    public MediaData getCurData() {
        return this.curMediaData;
    }

    public String getCurKeyHandling() {
        return this.curKey;
    }

    public String getCurOldKey() {
        return this.curOldKey;
    }

    public void loadCurrentMediaData(MediaDataManager.Listener listener) {
        MediaData mediaData;
        String str = this.curKey;
        if (str == null || (mediaData = this.curMediaData) == null) {
            return;
        }
        listener.onMediaDataLoaded(str, this.curOldKey, mediaData, true);
    }

    public void onMediaDataRemoved(String str, boolean z, Set<? extends MediaDataManager.Listener> set) {
        if (Intrinsics.areEqual(str, this.curKey)) {
            this.curKey = null;
            this.curOldKey = null;
            this.curMediaData = null;
        }
        removePlayer(str, z, set);
    }

    public void removePlayer(String str, boolean z, Set<? extends MediaDataManager.Listener> set) {
        String mediaDataKey;
        MediaPlayerData mediaPlayerData = MediaPlayerData.INSTANCE;
        mediaPlayerData.removeMediaSortKey(str);
        if (mediaPlayerData.getMediaSortKeySet().isEmpty()) {
            dispatchMediaDataOnRemove(str, z, set);
            return;
        }
        dispatchMediaDataOnRemove(str, z, set);
        MediaPlayerData.MediaSortKey firstActiveMediaSortKey = mediaPlayerData.getFirstActiveMediaSortKey();
        if (firstActiveMediaSortKey == null || (mediaDataKey = mediaPlayerData.getMediaDataKey(firstActiveMediaSortKey)) == null) {
            return;
        }
        dispatchMediaDataOnLoaded(mediaDataKey, mediaDataKey, firstActiveMediaSortKey.getData(), set);
    }
}
