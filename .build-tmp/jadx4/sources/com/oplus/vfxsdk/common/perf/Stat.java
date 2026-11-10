package com.oplus.vfxsdk.common.perf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Stat {
    private HashMap<String, FileStat> FileStats = new HashMap<>();
    private HashMap<Long, EngineStat> EngineStats = new HashMap<>();

    public final void clear() {
        this.FileStats.clear();
        this.EngineStats.clear();
    }

    public final HashMap<Long, EngineStat> getEngineStats() {
        return this.EngineStats;
    }

    public final HashMap<String, FileStat> getFileStats() {
        return this.FileStats;
    }

    public final void setEngineStats(HashMap<Long, EngineStat> map) {
        this.EngineStats = map;
    }

    public final void setFileStats(HashMap<String, FileStat> map) {
        this.FileStats = map;
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HashMap<String, FileStat> map = this.FileStats;
        ArrayList arrayList = new ArrayList(map.size());
        Iterator<Map.Entry<String, FileStat>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue().toJson());
        }
        jSONObject.put("files", new JSONArray((Collection) arrayList));
        HashMap<Long, EngineStat> map2 = this.EngineStats;
        ArrayList arrayList2 = new ArrayList(map2.size());
        Iterator<Map.Entry<Long, EngineStat>> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getValue().toJson());
        }
        jSONObject.put("engines", new JSONArray((Collection) arrayList2));
        return jSONObject;
    }
}
