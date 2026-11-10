package com.oplus.zoom;

import com.oplus.OplusTrackManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomTrack implements OplusTrackManager.TrackListener {
    private String mEventGroup;
    private ConcurrentHashMap<String, Map<String, String>> mMaps = new ConcurrentHashMap<>();
    private String mTrackAppId;

    public String getEventGroup() {
        return this.mEventGroup;
    }

    public Map<String, Map<String, String>> getEventMaps() {
        return this.mMaps;
    }

    public String getTrackAppId() {
        return this.mTrackAppId;
    }

    public void setTrackEvent(String str, String str2, String str3, Map map) {
        this.mTrackAppId = str;
        this.mEventGroup = str2;
        this.mMaps.put(str3, map);
    }
}
