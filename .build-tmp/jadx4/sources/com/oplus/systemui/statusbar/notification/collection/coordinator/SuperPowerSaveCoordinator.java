package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.util.ArraySet;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper;
import com.oplus.systemui.statusbar.seeding.livealert.LiveAlertService;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SuperPowerSaveCoordinator";
    private final NotificationDependOnCommon notificationDependOnCommon;
    private final SuperPowerSaveCoordinator$superPowerSaveNotifFilter$1 superPowerSaveNotifFilter = new NotifFilter() { // from class: com.oplus.systemui.statusbar.notification.collection.coordinator.SuperPowerSaveCoordinator$superPowerSaveNotifFilter$1
        {
            super("SuperPowerSaveCoordinatorSuperPowerSave");
        }

        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            SuperPowerNotificationHelper superPowerNotificationHelper = SuperPowerNotificationHelper.getInstance(this.this$0.notificationDependOnCommon.getApplicationContext());
            String packageName = notificationEntry.getSbn().getPackageName();
            int userId = notificationEntry.getSbn().getUserId();
            superPowerNotificationHelper.getClass();
            boolean z = false;
            if (SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
                if (!NotificationFeatureOption.INSTANCE.isSuperPowerModeDisabled(superPowerNotificationHelper.mContext)) {
                    boolean z2 = true;
                    try {
                        JSONArray jSONArray = new JSONArray(superPowerNotificationHelper.mSuperPowerListString);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String strOptString = jSONObject.optString(LiveAlertService.EXTRA_PACKAGE_NAME);
                            int iOptInt = jSONObject.optInt("user");
                            if (packageName.equals(strOptString) && iOptInt == userId) {
                                try {
                                    LogUtil.d(LogUtil.TAG_COMMON, "SuperPowerMode", "this notification can be showed" + packageName);
                                    z2 = false;
                                    break;
                                } catch (Exception e) {
                                    e = e;
                                    LogUtil.e(LogUtil.TAG_NOTIFICATION, "SuperPowerNotificationHelper", "isNeedToBlockInSuperPowerMode: " + e.getLocalizedMessage());
                                }
                            }
                        }
                        if (!superPowerNotificationHelper.mSuperPowerWhiteList.contains(packageName)) {
                            return z2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        z = z2;
                    }
                }
            }
            return z;
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.SuperPowerSaveCoordinator$attach$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ SuperPowerSaveCoordinator this$0;

        @Override // java.lang.Runnable
        public final void run() {
            invalidateList(getName());
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.statusbar.notification.collection.coordinator.SuperPowerSaveCoordinator$superPowerSaveNotifFilter$1] */
    public SuperPowerSaveCoordinator(NotificationDependOnCommon notificationDependOnCommon) {
        this.notificationDependOnCommon = notificationDependOnCommon;
    }

    public void attach(NotifPipeline notifPipeline) {
        SuperPowerNotificationHelper superPowerNotificationHelper = SuperPowerNotificationHelper.getInstance(this.notificationDependOnCommon.getApplicationContext());
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        ((ArraySet) superPowerNotificationHelper.mAppEncryptListeners).add(anonymousClass1);
        notifPipeline.addPreGroupFilter(this.superPowerSaveNotifFilter);
    }
}
