package com.oplusos.systemui.aod.mediapanel;

import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Triple;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaData {
    public static final AodMediaData INSTANCE = new AodMediaData();
    private static final Comparator<MediaSortKey> comparator;
    private static final Map<String, MediaSortKey> mediaDataMap;
    private static final TreeSet<MediaSortKey> mediaSortKeySet;
    private static SmartspaceMediaData smartspaceMediaData;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MediaSortKey {
        private final MediaData data;
        public final boolean isSsMediaRec;
        private final String oldKey;
        public final long updateTime;

        public MediaSortKey(boolean z, MediaData mediaData, String str, long j) {
            this.isSsMediaRec = z;
            this.data = mediaData;
            this.oldKey = str;
            this.updateTime = j;
        }

        public final MediaData component2() {
            return this.data;
        }

        public final String component3() {
            return this.oldKey;
        }

        public final MediaSortKey copy(boolean z, MediaData mediaData, String str, long j) {
            return new MediaSortKey(z, mediaData, str, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaSortKey)) {
                return false;
            }
            MediaSortKey mediaSortKey = (MediaSortKey) obj;
            return this.isSsMediaRec == mediaSortKey.isSsMediaRec && Intrinsics.areEqual(this.data, mediaSortKey.data) && Intrinsics.areEqual(this.oldKey, mediaSortKey.oldKey) && this.updateTime == mediaSortKey.updateTime;
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
            int iHashCode = (this.data.hashCode() + (Boolean.hashCode(this.isSsMediaRec) * 31)) * 31;
            String str = this.oldKey;
            return Long.hashCode(this.updateTime) + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31);
        }

        public String toString() {
            return "MediaSortKey(isSsMediaRec=" + this.isSsMediaRec + ", data=" + this.data + ", oldKey=" + this.oldKey + ", updateTime=" + this.updateTime + ")";
        }
    }

    static {
        AodMediaData$special$$inlined$compareByDescending$1 aodMediaData$special$$inlined$compareByDescending$1 = new AodMediaData$special$$inlined$compareByDescending$1();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$1 = new AodMediaData$special$$inlined$thenByDescending$1(0);
        aodMediaData$special$$inlined$thenByDescending$1.$this_thenByDescending = aodMediaData$special$$inlined$compareByDescending$1;
        VarHandle.storeStoreFence();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$2 = new AodMediaData$special$$inlined$thenByDescending$1(1);
        aodMediaData$special$$inlined$thenByDescending$2.$this_thenByDescending = aodMediaData$special$$inlined$thenByDescending$1;
        VarHandle.storeStoreFence();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$3 = new AodMediaData$special$$inlined$thenByDescending$1(2);
        aodMediaData$special$$inlined$thenByDescending$3.$this_thenByDescending = aodMediaData$special$$inlined$thenByDescending$2;
        VarHandle.storeStoreFence();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$4 = new AodMediaData$special$$inlined$thenByDescending$1(3);
        aodMediaData$special$$inlined$thenByDescending$4.$this_thenByDescending = aodMediaData$special$$inlined$thenByDescending$3;
        VarHandle.storeStoreFence();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$5 = new AodMediaData$special$$inlined$thenByDescending$1(4);
        aodMediaData$special$$inlined$thenByDescending$5.$this_thenByDescending = aodMediaData$special$$inlined$thenByDescending$4;
        VarHandle.storeStoreFence();
        AodMediaData$special$$inlined$thenByDescending$1 aodMediaData$special$$inlined$thenByDescending$6 = new AodMediaData$special$$inlined$thenByDescending$1(5);
        aodMediaData$special$$inlined$thenByDescending$6.$this_thenByDescending = aodMediaData$special$$inlined$thenByDescending$5;
        VarHandle.storeStoreFence();
        comparator = aodMediaData$special$$inlined$thenByDescending$6;
        mediaSortKeySet = new TreeSet<>(aodMediaData$special$$inlined$thenByDescending$6);
        mediaDataMap = new LinkedHashMap();
    }

    public final void addMediaSortKey(String str, String str2, MediaData mediaData) {
        if (str2 != null && !str2.equals(str)) {
            removeMediaSortKey(str2);
        }
        removeMediaSortKey(str);
        MediaSortKey mediaSortKey = new MediaSortKey(false, mediaData, str2, System.currentTimeMillis());
        mediaDataMap.put(str, mediaSortKey);
        mediaSortKeySet.add(mediaSortKey);
    }

    public final MediaSortKey getFirstActiveMediaSortKey() {
        Set<Map.Entry<String, MediaSortKey>> setEntrySet = mediaDataMap.entrySet();
        Comparator<MediaSortKey> comparator2 = comparator;
        Iterator<T> it = setEntrySet.iterator();
        if (!it.hasNext()) {
            return null;
        }
        MediaSortKey mediaSortKey = (MediaSortKey) ((Map.Entry) it.next()).getValue();
        while (it.hasNext()) {
            MediaSortKey mediaSortKey2 = (MediaSortKey) ((Map.Entry) it.next()).getValue();
            if (comparator2.compare(mediaSortKey, mediaSortKey2) > 0) {
                mediaSortKey = mediaSortKey2;
            }
        }
        return mediaSortKey;
    }

    public final String getMediaDataKey(MediaSortKey mediaSortKey) {
        for (String str : mediaDataMap.keySet()) {
            if (Intrinsics.areEqual(mediaSortKey, mediaDataMap.get(str))) {
                return str;
            }
        }
        return null;
    }

    public final Map<String, MediaSortKey> getMediaDataMap() {
        return mediaDataMap;
    }

    public final MediaSortKey getMediaSortKey(String str) {
        MediaSortKey mediaSortKey = mediaDataMap.get(str);
        if (mediaSortKey == null || !mediaSortKeySet.contains(mediaSortKey)) {
            return null;
        }
        return mediaSortKey;
    }

    public final int getMediaSortKeyIndex(String str) {
        MediaSortKey mediaSortKey = mediaDataMap.get(str);
        int i = 0;
        for (Object obj : mediaSortKeySet) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual((MediaSortKey) obj, mediaSortKey)) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public final TreeSet<MediaSortKey> getMediaSortKeySet() {
        return mediaSortKeySet;
    }

    public final boolean getShouldPrioritizeSs$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return false;
    }

    public final SmartspaceMediaData getSmartspaceMediaData$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return smartspaceMediaData;
    }

    public final List<Triple<String, MediaData, Boolean>> mediaData() {
        Set<Map.Entry<String, MediaSortKey>> setEntrySet = mediaDataMap.entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            arrayList.add(new Triple(entry.getKey(), ((MediaSortKey) entry.getValue()).getData(), Boolean.valueOf(((MediaSortKey) entry.getValue()).isSsMediaRec)));
        }
        return arrayList;
    }

    public final void moveIfExists(String str, String str2) {
        Map<String, MediaSortKey> map;
        MediaSortKey mediaSortKeyRemove;
        if (str == null || str.equals(str2) || (mediaSortKeyRemove = (map = mediaDataMap).remove(str)) == null) {
            return;
        }
        INSTANCE.removeMediaSortKey(str2);
        map.put(str2, mediaSortKeyRemove);
    }

    public final Boolean removeMediaSortKey(String str) {
        MediaSortKey mediaSortKeyRemove = mediaDataMap.remove(str);
        if (mediaSortKeyRemove == null) {
            return null;
        }
        if (mediaSortKeyRemove.isSsMediaRec) {
            smartspaceMediaData = null;
        }
        return Boolean.valueOf(mediaSortKeySet.remove(mediaSortKeyRemove));
    }

    public final String smartspaceMediaKey() {
        Iterator<T> it = mediaDataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (((MediaSortKey) entry.getValue()).isSsMediaRec) {
                return (String) entry.getKey();
            }
        }
        return null;
    }
}
