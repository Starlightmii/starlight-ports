package com.oplus.systemui.qs;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.OplusOSTelephonyManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Range;
import androidx.car.app.model.TimerSpan$;
import com.android.settingslib.net.DataUsageController;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.oplus.systemui.qs.utils.DataUsageUtils;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDataUsageController {
    public static final Uri DAILY_ALERT_PROVIDER_URI = Uri.parse("content://com.android.phone.DailyAlertProvider");
    public final Context mContext;
    public final DataUsageController mDataController;
    public final NetworkController mNetworkController;
    public final NetworkStatsManager mNetworkStatsManager;
    public final NetworkPolicyManager mPolicyManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DataUsageInfo {
        public long limitLevel;
        public long usageLevel;
    }

    public OplusDataUsageController(Context context, NetworkController networkController) {
        this.mDataController = networkController.getMobileDataController();
        this.mNetworkStatsManager = (NetworkStatsManager) context.getSystemService(NetworkStatsManager.class);
        this.mPolicyManager = NetworkPolicyManager.from(context);
        this.mNetworkController = networkController;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        int i = Settings.Global.getInt(contentResolver, "daily_alert_version", 0);
        if ((i & OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) != 65536) {
            i |= OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS;
            Settings.Global.putInt(contentResolver, "daily_alert_version", i);
        }
        LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "syncDailyAlertVersion version: 0x" + Integer.toHexString(i));
    }

    private int getActiveDataSubscriptionId() {
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        int subscriptionId = -1;
        if (activeDataSubscriptionId != -1) {
            return activeDataSubscriptionId;
        }
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(this.mContext);
        if (subscriptionManagerFrom != null) {
            SubscriptionInfo defaultDataSubscriptionInfo = subscriptionManagerFrom.getDefaultDataSubscriptionInfo();
            if (defaultDataSubscriptionInfo != null) {
                subscriptionId = defaultDataSubscriptionInfo.getSubscriptionId();
            } else {
                List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManagerFrom.getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null && activeSubscriptionInfoList.size() > 0) {
                    defaultDataSubscriptionInfo = activeSubscriptionInfoList.get(0);
                    subscriptionId = defaultDataSubscriptionInfo.getSubscriptionId();
                }
            }
        }
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(subscriptionId, "getActiveDataSubscriptionId return -1, getDefaultSubscriptionId: ", LogUtil.TAG_STATUSBAR, "OplusDataUsageController");
        return subscriptionId;
    }

    private String getActiveSubscriberId() {
        TelephonyManager telephonyManagerFrom = TelephonyManager.from(this.mContext);
        int activeDataSubscriptionId = getActiveDataSubscriptionId();
        if (activeDataSubscriptionId == -1) {
            activeDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        }
        return telephonyManagerFrom.getSubscriberId(activeDataSubscriptionId);
    }

    private long getStartTimeOfDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private long getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime().getTime();
    }

    public final String formatBytes(long j) {
        Context context = this.mContext;
        if (j < 0) {
            j = 0;
        }
        return Formatter.formatFileSize(context, j, 8).replace('k', 'K');
    }

    public final String getDataUsageInfo(int i, String str) {
        NetworkPolicy networkPolicy;
        long epochMilli;
        long epochMilli2;
        long txBytes;
        DataUsageInfo dataUsageInfo;
        long txBytes2;
        boolean zIsOplusHasSoftSimCard;
        NetworkPolicy[] networkPolicies;
        int activeDataSubscriptionId = SubscriptionManager.isValidSubscriptionId(i) ? i : getActiveDataSubscriptionId();
        NetworkTemplate mobileTemplate = DataUsageUtils.getMobileTemplate(activeDataSubscriptionId, this.mContext);
        Resources resources = this.mContext.getResources();
        NetworkPolicyManager networkPolicyManager = this.mPolicyManager;
        if (networkPolicyManager != null && mobileTemplate != null && (networkPolicies = networkPolicyManager.getNetworkPolicies()) != null) {
            int length = networkPolicies.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    NetworkPolicy networkPolicy2 = networkPolicies[i2];
                    if (networkPolicy2 != null && mobileTemplate.equals(networkPolicy2.template)) {
                        networkPolicy = networkPolicy2;
                        break;
                    }
                    i2++;
                } else {
                    networkPolicy = null;
                    break;
                }
            }
        } else {
            networkPolicy = null;
            break;
        }
        System.currentTimeMillis();
        Iterator itCycleIterator = networkPolicy != null ? networkPolicy.cycleIterator() : null;
        if (itCycleIterator == null || !itCycleIterator.hasNext()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(5, calendar.getActualMinimum(5));
            long time = calendar.getTime().getTime();
            calendar.set(5, calendar.getActualMaximum(5));
            long time2 = calendar.getTime().getTime();
            StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(time, "getDataUsageInfo: start=", ", end=");
            sbM.append(time2);
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", sbM.toString());
            epochMilli = time;
            epochMilli2 = time2;
        } else {
            Range range = (Range) itCycleIterator.next();
            epochMilli = ((ZonedDateTime) range.getLower()).toInstant().toEpochMilli();
            epochMilli2 = ((ZonedDateTime) range.getUpper()).toInstant().toEpochMilli();
        }
        NetworkStats.Bucket bucketQuerySummaryForDevice = this.mNetworkStatsManager.querySummaryForDevice(mobileTemplate, epochMilli, epochMilli2);
        if (bucketQuerySummaryForDevice != null) {
            txBytes = bucketQuerySummaryForDevice.getTxBytes() + bucketQuerySummaryForDevice.getRxBytes();
        } else {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "Failed to get data usage, no entry data");
            txBytes = -1;
        }
        if (txBytes < 0) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "no entry data");
            dataUsageInfo = null;
        } else {
            DataUsageInfo dataUsageInfo2 = new DataUsageInfo();
            dataUsageInfo2.usageLevel = txBytes;
            if (networkPolicy != null) {
                long j = networkPolicy.limitBytes;
                if (j <= 0) {
                    j = 0;
                }
                dataUsageInfo2.limitLevel = j;
            } else {
                this.mDataController.getDefaultWarningLevel();
            }
            NetworkController networkController = this.mNetworkController;
            if (networkController != null) {
                networkController.getMobileDataNetworkName();
            }
            dataUsageInfo = dataUsageInfo2;
        }
        if (dataUsageInfo == null) {
            return "";
        }
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = SubscriptionManager.from(this.mContext).getActiveSubscriptionInfoForSimSlotIndex(SubscriptionManager.getSlotIndex(activeDataSubscriptionId));
        String iccId = activeSubscriptionInfoForSimSlotIndex != null ? activeSubscriptionInfoForSimSlotIndex.getIccId() : null;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int i3 = Settings.Global.getInt(contentResolver, "daily_alert_use_new_method", -1);
        long j2 = Settings.Global.getLong(contentResolver, "oplus_comm_trafficmonitor_daily_alert_" + iccId, -1L);
        if (i3 != 1) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "getDailyAlert methodFlag: " + i3 + ", numDailyAlert: " + j2);
        } else if (j2 != -1) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "Synchronization is not complete, numDailyAlert: " + j2);
        } else {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("iccid", iccId);
                bundle.putLong(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE, -1L);
                Bundle bundleCall = contentResolver.call(DAILY_ALERT_PROVIDER_URI, "get_daily_alert", (String) null, bundle);
                if (bundleCall != null) {
                    j2 = bundleCall.getLong("result");
                }
            } catch (Exception e) {
                LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "getDailyAlert Exception: " + e);
            }
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "getDailyAlert methodFlag: " + i3 + ", numDailyAlert: " + j2);
        }
        NetworkStats.Bucket bucketQuerySummaryForDevice2 = this.mNetworkStatsManager.querySummaryForDevice(mobileTemplate, getStartTimeOfDay(), getTodayEnd());
        if (bucketQuerySummaryForDevice2 != null) {
            txBytes2 = bucketQuerySummaryForDevice2.getTxBytes() + bucketQuerySummaryForDevice2.getRxBytes();
        } else {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusDataUsageController", "Failed to get data usage, no entry data");
            txBytes2 = -1;
        }
        if (j2 > 0) {
            return "QS_CARRIER".equals(str) ? resources.getString(2131955799, formatBytes(txBytes2), formatBytes(j2 - txBytes2)) : formatBytes(txBytes2);
        }
        if (dataUsageInfo.limitLevel > 0) {
            return "QS_CARRIER".equals(str) ? resources.getString(2131955797, formatBytes(txBytes2), formatBytes(dataUsageInfo.limitLevel - dataUsageInfo.usageLevel)) : formatBytes(txBytes2);
        }
        Context context = this.mContext;
        if (context == null) {
            LogUtil.d(LogUtil.TAG_STATUSBAR, "NetworkServiceProxy", "hasSoftSimCard false, context is null");
            zIsOplusHasSoftSimCard = false;
        } else {
            zIsOplusHasSoftSimCard = OplusOSTelephonyManager.getDefault(context).isOplusHasSoftSimCard();
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("hasSoftSimCard ", LogUtil.TAG_STATUSBAR, "NetworkServiceProxy", zIsOplusHasSoftSimCard);
        }
        if (!zIsOplusHasSoftSimCard) {
            return "QS_CARRIER".equals(str) ? resources.getString(2131955796, formatBytes(txBytes2), formatBytes(dataUsageInfo.usageLevel)) : formatBytes(txBytes2);
        }
        Context context2 = this.mContext;
        int intForUser = Settings.System.getIntForUser(context2.getContentResolver(), "OPLUS_SIM_VSIM_TYPE", 1, context2.getUserId());
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(intForUser, "isNoWorriesTraffic trafficType: ", LogUtil.TAG_QS, "OplusDataUsageController");
        return intForUser == 2 ? resources.getString(2131955807) : resources.getString(2131955802, formatBytes(dataUsageInfo.usageLevel));
    }

    public final String getDataUsageInfo(int i) {
        if (SubscriptionManager.isValidSubscriptionId(i)) {
            return getDataUsageInfo(i, "QS_CARRIER");
        }
        return getDataUsageInfo(i, "QS_CELLULAR");
    }

    public String getDataUsageInfo() {
        return getDataUsageInfo(-1, "QS_CARRIER");
    }
}
