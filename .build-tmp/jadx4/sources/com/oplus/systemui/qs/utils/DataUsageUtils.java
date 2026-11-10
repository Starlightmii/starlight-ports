package com.oplus.systemui.qs.utils;

import android.content.Context;
import android.net.NetworkTemplate;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class DataUsageUtils {
    public static NetworkTemplate getMobileTemplate(int i, Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        int subscriptionId = telephonyManager.getSubscriptionId();
        List<SubscriptionInfo> availableSubscriptionInfoList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getAvailableSubscriptionInfoList();
        if (availableSubscriptionInfoList == null) {
            Log.i("DataUsageUtils", "Subscription is not inited: " + i);
            return getMobileTemplateForSubId(telephonyManager, subscriptionId);
        }
        for (SubscriptionInfo subscriptionInfo : availableSubscriptionInfoList) {
            if (subscriptionInfo != null && subscriptionInfo.getSubscriptionId() == i) {
                NetworkTemplate mobileTemplateForSubId = getMobileTemplateForSubId(telephonyManager, i);
                String[] mergedImsisFromGroup = telephonyManager.createForSubscriptionId(i).getMergedImsisFromGroup();
                if (mergedImsisFromGroup == null) {
                    Log.i("DataUsageUtils", "mergedImsisFromGroup is null.");
                    return mobileTemplateForSubId;
                }
                Set setOf = Set.of((Object[]) mergedImsisFromGroup);
                if (ArrayUtils.isEmpty(setOf)) {
                    Log.i("DataUsageUtils", "mergedSubscriberIds is null.");
                    return mobileTemplateForSubId;
                }
                Iterator it = setOf.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()) == null) {
                        Log.i("DataUsageUtils", "mergedSubscriberIds has null element");
                        return mobileTemplateForSubId;
                    }
                }
                return (!mobileTemplateForSubId.getSubscriberIds().isEmpty() && setOf.contains((String) mobileTemplateForSubId.getSubscriberIds().iterator().next())) ? new NetworkTemplate.Builder(mobileTemplateForSubId.getMatchRule()).setSubscriberIds(setOf).setWifiNetworkKeys(mobileTemplateForSubId.getWifiNetworkKeys()).setMeteredness(1).build() : mobileTemplateForSubId;
            }
        }
        Log.i("DataUsageUtils", "Subscription is not active: " + i);
        return getMobileTemplateForSubId(telephonyManager, subscriptionId);
    }

    public static NetworkTemplate getMobileTemplateForSubId(TelephonyManager telephonyManager, int i) {
        String subscriberId = telephonyManager.getSubscriberId(i);
        return subscriberId != null ? new NetworkTemplate.Builder(10).setSubscriberIds(Set.of(subscriberId)).setMeteredness(1).build() : new NetworkTemplate.Builder(1).setMeteredness(1).build();
    }
}
