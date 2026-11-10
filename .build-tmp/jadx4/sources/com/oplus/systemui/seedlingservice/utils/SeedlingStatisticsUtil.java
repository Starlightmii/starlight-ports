package com.oplus.systemui.seedlingservice.utils;

import android.content.Context;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingStatisticsUtil {
    private static final String EVENT_SEEDLING_FLASHLIGHT = "seedling_flashlight";
    public static final SeedlingStatisticsUtil INSTANCE = new SeedlingStatisticsUtil();
    private static final String KEY_ACTION = "action";
    private static final String LOG_TAG = "20082";
    private static final String TAG = "SeedlingStatisticsUtil";
    public static final String VALUE_OFF = "1";
    public static final String VALUE_ON = "0";

    private SeedlingStatisticsUtil() {
    }

    public static final void trackSeedlingFlashlight(Context context, String str) {
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(KEY_ACTION, str)});
        LogUtil.d(TAG, "trackSeedlingFlashlight. " + mapMutableMapOf);
        if (context != null) {
            OplusTrackProxyUtil.onCommon(context, LOG_TAG, EVENT_SEEDLING_FLASHLIGHT, mapMutableMapOf);
        }
    }
}
