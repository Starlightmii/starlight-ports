package com.oplus.systemui.statusbar.notification.util;

import android.content.Context;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.OplusBatchTrack;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewStatisticUtil {
    public static final ViewStatisticUtil INSTANCE = new ViewStatisticUtil();

    public static final void clearAllButtonClicked(Context context, String str) {
        HashMap map = new HashMap();
        map.put("event_screen_orientation", NotificationStatisticUtil.getScreenOrientation(context));
        map.put("event_time", NotificationStatisticUtil.getEventTime());
        map.put("notifications_count", str);
        OplusBatchTrack.onCommon(context, "20082", "clear_all_notifications_batch", map);
    }
}
