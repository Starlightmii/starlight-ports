package com.oplus.systemui.notification.statistic;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackerLinkConfig {
    public static final NotificationTrackerLinkConfig INSTANCE = new NotificationTrackerLinkConfig();
    private static final String SETTINGS_TRACKER_WHITELIST_CONFIG = "notification_track_link_config";
    private static final String TAG = "NotificationTrackerLinkConfig";
    private static ContentObserver appTrackListObserver;
    private static volatile TrackConfigData trackConfig;
    private static Runnable updateConfigRunnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TrackConfigData {

        @SerializedName(alternate = {"dailyList"}, value = "daily_list")
        private final List<String> dailyList;
        private final Integer exposeDailyReportCount;
        private final Integer exposeReportSizeOnce;

        @SerializedName(alternate = {"policyList"}, value = "policy_list")
        private final List<String> policyList;

        public TrackConfigData(List<String> list, List<String> list2, Integer num, Integer num2) {
            this.policyList = list;
            this.dailyList = list2;
            this.exposeDailyReportCount = num;
            this.exposeReportSizeOnce = num2;
        }

        public final List<String> component1() {
            return this.policyList;
        }

        public final List<String> component2() {
            return this.dailyList;
        }

        public final Integer component3() {
            return this.exposeDailyReportCount;
        }

        public final Integer component4() {
            return this.exposeReportSizeOnce;
        }

        public final TrackConfigData copy(List<String> list, List<String> list2, Integer num, Integer num2) {
            return new TrackConfigData(list, list2, num, num2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TrackConfigData)) {
                return false;
            }
            TrackConfigData trackConfigData = (TrackConfigData) obj;
            return Intrinsics.areEqual(this.policyList, trackConfigData.policyList) && Intrinsics.areEqual(this.dailyList, trackConfigData.dailyList) && Intrinsics.areEqual(this.exposeDailyReportCount, trackConfigData.exposeDailyReportCount) && Intrinsics.areEqual(this.exposeReportSizeOnce, trackConfigData.exposeReportSizeOnce);
        }

        public final List<String> getDailyList() {
            return this.dailyList;
        }

        public final Integer getExposeDailyReportCount() {
            return this.exposeDailyReportCount;
        }

        public final Integer getExposeReportSizeOnce() {
            return this.exposeReportSizeOnce;
        }

        public final List<String> getPolicyList() {
            return this.policyList;
        }

        public final int hashCode() {
            List<String> list = this.policyList;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<String> list2 = this.dailyList;
            int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
            Integer num = this.exposeDailyReportCount;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.exposeReportSizeOnce;
            return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "TrackConfigData(policyList=" + this.policyList + ", dailyList=" + this.dailyList + ", exposeDailyReportCount=" + this.exposeDailyReportCount + ", exposeReportSizeOnce=" + this.exposeReportSizeOnce + ")";
        }
    }

    public static Unit $r8$lambda$5T6j85Hp6cnwbtuxZxPuwnSVBjw(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), SETTINGS_TRACKER_WHITELIST_CONFIG);
        INSTANCE.getClass();
        if (string == null || string.length() == 0) {
            trackConfig = null;
        } else {
            try {
                trackConfig = (TrackConfigData) new GsonBuilder().create().fromJson(TrackConfigData.class, string);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationTrackerLinkConfig-->".concat("parseWhitelist policyList:" + trackConfig));
                }
            } catch (JsonSyntaxException e) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, e.getMessage());
            }
        }
        Runnable runnable = updateConfigRunnable;
        if (runnable != null) {
            runnable.run();
        }
        return Unit.INSTANCE;
    }

    public static final int getExposeReportSize() {
        Integer exposeReportSizeOnce;
        TrackConfigData trackConfigData = trackConfig;
        if (trackConfigData == null || (exposeReportSizeOnce = trackConfigData.getExposeReportSizeOnce()) == null) {
            return 100;
        }
        if (exposeReportSizeOnce.intValue() <= 0) {
            exposeReportSizeOnce = null;
        }
        if (exposeReportSizeOnce != null) {
            return exposeReportSizeOnce.intValue();
        }
        return 100;
    }

    public static final boolean isInDailyUploadWhiteList(String str) {
        List<String> dailyList;
        TrackConfigData trackConfigData = trackConfig;
        if (trackConfigData == null || (dailyList = trackConfigData.getDailyList()) == null) {
            return false;
        }
        return dailyList.contains(str);
    }

    public static final boolean isInPolicyWhiteList(String str) {
        List<String> policyList;
        TrackConfigData trackConfigData = trackConfig;
        if (trackConfigData == null || (policyList = trackConfigData.getPolicyList()) == null) {
            return false;
        }
        return policyList.contains(str);
    }

    public final long getExposeDailyReportCount() {
        Integer exposeDailyReportCount;
        TrackConfigData trackConfigData = trackConfig;
        int iIntValue = (trackConfigData == null || (exposeDailyReportCount = trackConfigData.getExposeDailyReportCount()) == null) ? 4 : exposeDailyReportCount.intValue();
        if (iIntValue <= 0) {
            return 21600000L;
        }
        return 86400000 / ((long) iIntValue);
    }

    public final TrackConfigData getTrackConfig() {
        return trackConfig;
    }

    public final void init(final Context context, final Handler handler, Runnable runnable) {
        updateConfigRunnable = runnable;
        if (appTrackListObserver == null) {
            appTrackListObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.statistic.NotificationTrackerLinkConfig$registerAppTrackListObserver$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    NotificationTrackerLinkConfig notificationTrackerLinkConfig = NotificationTrackerLinkConfig.INSTANCE;
                    Context context2 = context;
                    notificationTrackerLinkConfig.getClass();
                    ThreadManager companion = ThreadManager.Companion.getInstance();
                    NotificationTrackerLinkConfig$$ExternalSyntheticLambda0 notificationTrackerLinkConfig$$ExternalSyntheticLambda0 = new NotificationTrackerLinkConfig$$ExternalSyntheticLambda0();
                    notificationTrackerLinkConfig$$ExternalSyntheticLambda0.f$0 = context2;
                    VarHandle.storeStoreFence();
                    companion.postInThread(notificationTrackerLinkConfig$$ExternalSyntheticLambda0);
                }
            };
        }
        ContentObserver contentObserver = appTrackListObserver;
        if (contentObserver != null) {
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SETTINGS_TRACKER_WHITELIST_CONFIG), false, contentObserver);
        }
        ThreadManager companion = ThreadManager.Companion.getInstance();
        NotificationTrackerLinkConfig$$ExternalSyntheticLambda0 notificationTrackerLinkConfig$$ExternalSyntheticLambda0 = new NotificationTrackerLinkConfig$$ExternalSyntheticLambda0();
        notificationTrackerLinkConfig$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        companion.postInThread(notificationTrackerLinkConfig$$ExternalSyntheticLambda0);
    }
}
