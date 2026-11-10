package com.oplus.systemui.notification.statistic;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.util.AlarmTimeout;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomRowTrackLinkHelper {
    private static final String KEY_EVENT_TIME = "eventTime";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_PKG = "package";
    private static final String KEY_SERVICEID = "serviceId";
    private static final String KEY_USERID = "userId";
    private static final String LOCATION_KEYGUARD = "keyguard";
    private static final String LOCATION_NOTIFICATION_BAR = "notification_bar";
    private static final String SEEDLING_CARD_EVENT_ID = "seedling_card_expose";
    private static final String TAG = "CustomRowTrackLinkHelper";
    private AlarmTimeout alarmTimeout;
    private final ArrayList<Map<String, String>> customRowShowCache = new ArrayList<>();
    private final ReentrantLock seedlingReportLock = new ReentrantLock();
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new CustomRowTrackLinkHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final CustomRowTrackLinkHelper getInstance() {
            return (CustomRowTrackLinkHelper) CustomRowTrackLinkHelper.instance$delegate.getValue();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$QTV_-RxfpzTQuou-jTlFnEgVyc8, reason: not valid java name */
    public static Unit m103$r8$lambda$QTV_RxfpzTQuoujTlFnEgVyc8(CustomRowTrackLinkHelper customRowTrackLinkHelper, Context context) {
        customRowTrackLinkHelper.seedlingReportLock.lock();
        try {
            if (customRowTrackLinkHelper.customRowShowCache.isEmpty()) {
                return Unit.INSTANCE;
            }
            OplusTrackProxyUtil.onCommonBatch(context, "20082", SEEDLING_CARD_EVENT_ID, customRowTrackLinkHelper.customRowShowCache, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
            customRowTrackLinkHelper.customRowShowCache.clear();
            return Unit.INSTANCE;
        } finally {
            customRowTrackLinkHelper.seedlingReportLock.unlock();
        }
    }

    public static final CustomRowTrackLinkHelper getInstance() {
        return Companion.getInstance();
    }

    public final void init(Context context, Handler handler) {
        Object systemService = context.getSystemService("alarm");
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            return;
        }
        CustomRowTrackLinkHelper$startScheduleAlarm$1 customRowTrackLinkHelper$startScheduleAlarm$1 = new CustomRowTrackLinkHelper$startScheduleAlarm$1();
        customRowTrackLinkHelper$startScheduleAlarm$1.this$0 = this;
        customRowTrackLinkHelper$startScheduleAlarm$1.$context = context;
        VarHandle.storeStoreFence();
        this.alarmTimeout = new AlarmTimeout(alarmManager, customRowTrackLinkHelper$startScheduleAlarm$1, TAG, handler);
        scheduleAlarm();
    }

    public final void onExpose(CustomRowEntry customRowEntry, boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str = z ? "keyguard" : LOCATION_NOTIFICATION_BAR;
        ArrayList<Map<String, String>> arrayList = this.customRowShowCache;
        if (arrayList == null || !arrayList.isEmpty()) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Map<String, String> map = arrayList.get(i);
                i++;
                Map<String, String> map2 = map;
                if (Intrinsics.areEqual(map2.get(KEY_SERVICEID), customRowEntry.getServiceId()) && Intrinsics.areEqual(map2.get(KEY_PKG), customRowEntry.getPackageName()) && Intrinsics.areEqual(map2.get(KEY_USERID), String.valueOf(customRowEntry.getUserId())) && Intrinsics.areEqual(map2.get(KEY_LOCATION), str)) {
                    return;
                }
            }
        }
        this.customRowShowCache.add(MapsKt__MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(KEY_SERVICEID, customRowEntry.getServiceId()), TuplesKt.to(KEY_LOCATION, str), TuplesKt.to(KEY_PKG, customRowEntry.getPackageName()), TuplesKt.to(KEY_USERID, String.valueOf(customRowEntry.getUserId())), TuplesKt.to(KEY_EVENT_TIME, String.valueOf(jCurrentTimeMillis))}));
    }

    public final void scheduleAlarm() {
        AlarmTimeout alarmTimeout = this.alarmTimeout;
        if (alarmTimeout != null) {
            alarmTimeout.schedule(2, 3, 86400000L);
        }
    }
}
