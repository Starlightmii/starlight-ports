package com.oplus.systemui.media.controls.pipeline;

import androidx.car.app.hardware.common.CarZone$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusActionConfig {
    public static final Companion Companion = new Companion();
    public static final String HEART_ACTION_KEY = "oplusLike";
    public static final String METADATA_KEY_OPLUS_ACTION_CONFIG = "oplusActionConfig";
    private final String key;
    private final String uriKey;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final List<OplusActionConfig> parseFrom(String str) {
            if (str.length() == 0) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String strOptString = jSONObject.optString("key");
                    strOptString.getClass();
                    if (strOptString.length() <= 0) {
                        strOptString = null;
                    }
                    String strOptString2 = jSONObject.optString("uriKey", "");
                    strOptString2.getClass();
                    String str2 = strOptString2.length() > 0 ? strOptString2 : null;
                    if (strOptString != null) {
                        arrayList.add(new OplusActionConfig(strOptString, str2));
                    }
                }
                return arrayList;
            } catch (Throwable th) {
                Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
                if (thM != null) {
                    GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "[ PARSE ERROR ] ", thM.getMessage(), LogUtil.TAG_MEDIA, "OplusActionConfig");
                }
                return CollectionsKt__CollectionsKt.emptyList();
            }
        }
    }

    public OplusActionConfig(String str, String str2) {
        this.key = str;
        this.uriKey = str2;
    }

    public final String component1() {
        return this.key;
    }

    public final String component2() {
        return this.uriKey;
    }

    public final OplusActionConfig copy(String str, String str2) {
        return new OplusActionConfig(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusActionConfig)) {
            return false;
        }
        OplusActionConfig oplusActionConfig = (OplusActionConfig) obj;
        return Intrinsics.areEqual(this.key, oplusActionConfig.key) && Intrinsics.areEqual(this.uriKey, oplusActionConfig.uriKey);
    }

    public final String getKey() {
        return this.key;
    }

    public final String getUriKey() {
        return this.uriKey;
    }

    public final int hashCode() {
        int iHashCode = this.key.hashCode() * 31;
        String str = this.uriKey;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return CarZone$.ExternalSyntheticOutline0.m("OplusActionConfig(key=", this.key, ", uriKey=", this.uriKey, ")");
    }
}
