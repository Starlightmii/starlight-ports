package com.oplus.util;

import android.content.Context;
import com.oplus.drs.statistics.OplusTrack;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusTrackProxyUtil {
    public static OplusTrack getTrackSplitScreen() {
        return OplusTrack.getInstance("20232");
    }

    public static OplusTrack getTrackWindowManager() {
        return OplusTrack.getInstance("20126");
    }

    public static void onCommonSplitScreen(Context context, String str, Map map) {
        getTrackSplitScreen().onCommon(context, "20232", "20232001", str, map);
    }

    public static void onCommonWindowManager(Context context, String str, String str2, String str3, Map map) {
        getTrackWindowManager().onCommon(context, str, str2, str3, map);
    }
}
