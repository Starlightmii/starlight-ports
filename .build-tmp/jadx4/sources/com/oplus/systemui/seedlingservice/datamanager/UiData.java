package com.oplus.systemui.seedlingservice.datamanager;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UiData {
    private JSONObject jsonObject = new JSONObject();

    public final JSONObject build() {
        return this.jsonObject;
    }

    public final UiData put(String str, Object obj) throws JSONException {
        this.jsonObject.put(str, obj);
        return this;
    }

    public String toString() {
        return this.jsonObject.toString();
    }
}
