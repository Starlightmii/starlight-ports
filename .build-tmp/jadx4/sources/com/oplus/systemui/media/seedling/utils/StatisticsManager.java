package com.oplus.systemui.media.seedling.utils;

import android.content.Context;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatisticsManager {
    public static final Companion Companion = new Companion();
    public static final String EVENT_ID_MEDIA_ACTION = "MediaAction";
    public static final String TAG = "StatisticsManager";
    public static final String UPLOAD_APP_CODE = "133400";
    public static final String UPLOAD_KEY_ACTION_TYPE = "actionType";
    public static final String UPLOAD_KEY_ENTRY = "entry";
    public static final String UPLOAD_KEY_EXTRA = "extra";
    public static final String UPLOAD_LOG_TAG = "MediaState";
    public static final String UPLOAD_VALUE_DEFAULT = "na";
    private static StatisticsManager instance;
    private Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StatisticsManager getInstance() {
            StatisticsManager statisticsManager;
            StatisticsManager statisticsManager2 = StatisticsManager.instance;
            if (statisticsManager2 != null) {
                return statisticsManager2;
            }
            synchronized (this) {
                statisticsManager = StatisticsManager.instance;
                if (statisticsManager == null) {
                    statisticsManager = new StatisticsManager();
                    StatisticsManager.instance = statisticsManager;
                }
            }
            return statisticsManager;
        }
    }

    public final void doUpload(int i, String str, String str2, String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("actionType", String.valueOf(i));
        linkedHashMap.put(UPLOAD_KEY_ENTRY, str);
        linkedHashMap.put(UPLOAD_KEY_EXTRA, str2);
        StringBuilder sb = new StringBuilder("doUpload actionType=");
        sb.append(i);
        ConstraintWidget$.ExternalSyntheticOutline0.m(sb, " entry=", str, " extraVale=", str2);
        sb.append(" eventId=");
        sb.append(str3);
        LogUtil.i(TAG, sb.toString());
        Context context = this.context;
        if (context != null) {
            OplusTrackProxyUtil.onCommonByBeanParams(context, UPLOAD_APP_CODE, UPLOAD_LOG_TAG, str3, linkedHashMap);
        }
    }

    public final void initStatisticsManager(Context context) {
        this.context = context;
    }
}
