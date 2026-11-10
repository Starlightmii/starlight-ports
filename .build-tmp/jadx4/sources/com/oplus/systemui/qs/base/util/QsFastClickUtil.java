package com.oplus.systemui.qs.base.util;

import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsFastClickUtil {
    private static final String TAG = "QsFastClickUtil";
    public static final QsFastClickUtil INSTANCE = new QsFastClickUtil();
    public static final String TYPE_HOTSPOT = "HOTSPOT";
    public static final String TYPE_CELLULAR = "CELLULAR";
    public static final String TYPE_DARK_MODE = "DARK_MODE";
    public static final String TYPE_USERSWITCH = "USER_SWITCH";
    public static final String TYPE_QS_TILE_HIGHLIGHT = "QS_TILE_HIGHLIGHT";
    public static final String TYPE_QS_TILE_FLASHLIGHT = "QS_TILE_FLASHLIGHT";
    public static final String TYPE_QS_SHOW_DIALOG = "QS_SHOW_DIALOG";
    public static final String TYPE_AIRPLANMODE = "AIR_PLAN_MODE";
    public static final String TYPE_QS_EDIT_CLICK = "QS_EDIT_CLICK";
    public static final String TYPE_QS_ADD_TILE_CLICK = "QS_ADD_TILE_CLICK";
    private static final Map<String, Long> lastTimeMap = MapsKt__MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(TYPE_HOTSPOT, 0L), TuplesKt.to(TYPE_CELLULAR, 0L), TuplesKt.to(TYPE_DARK_MODE, 0L), TuplesKt.to(TYPE_USERSWITCH, 0L), TuplesKt.to(TYPE_QS_TILE_HIGHLIGHT, 0L), TuplesKt.to(TYPE_QS_TILE_FLASHLIGHT, 0L), TuplesKt.to(TYPE_QS_SHOW_DIALOG, 0L), TuplesKt.to(TYPE_AIRPLANMODE, 0L), TuplesKt.to(TYPE_QS_EDIT_CLICK, 0L), TuplesKt.to(TYPE_QS_ADD_TILE_CLICK, 0L)});

    public static final boolean isFastClick(String str) {
        int i;
        Map<String, Long> map = lastTimeMap;
        long jLongValue = map.getOrDefault(str, 0L).longValue();
        if (Intrinsics.areEqual(str, TYPE_QS_ADD_TILE_CLICK)) {
            i = ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION;
        } else {
            i = Intrinsics.areEqual(str, TYPE_DARK_MODE) ? ZoomParameterHelper.MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP : 600;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - jLongValue) < i) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                return true;
            }
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("isFastClick = true, type = ", str, "QsFastClickUtil-->", "SystemUi--QuickSettings");
            return true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("isFastClick = false, type = ", str, "QsFastClickUtil-->", "SystemUi--QuickSettings");
        }
        map.put(str, Long.valueOf(jCurrentTimeMillis));
        return false;
    }
}
