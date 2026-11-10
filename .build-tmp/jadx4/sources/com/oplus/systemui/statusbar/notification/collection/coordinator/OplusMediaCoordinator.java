package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.util.Log;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String NFC_PACKAGE_NAME = "com.android.nfc";
    private static final String TAG = "OplusMediaCoordinator";
    private final Lazy mediaNotifSectioner$delegate;
    private final OplusMediaCoordinator$notifiFilter$1 notifiFilter = new NotifFilter() { // from class: com.oplus.systemui.statusbar.notification.collection.coordinator.OplusMediaCoordinator$notifiFilter$1
        {
            super("OplusMediaCoordinator");
        }

        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return this.this$0.shouldFilterOutByOplusMediaCoordinator(notificationEntry);
        }
    };
    private final NotificationMediaManager notificationMediaManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.statusbar.notification.collection.coordinator.OplusMediaCoordinator$notifiFilter$1] */
    public OplusMediaCoordinator(NotificationMediaManager notificationMediaManager) {
        this.notificationMediaManager = notificationMediaManager;
        OplusMediaCoordinator$$ExternalSyntheticLambda0 oplusMediaCoordinator$$ExternalSyntheticLambda0 = new OplusMediaCoordinator$$ExternalSyntheticLambda0();
        oplusMediaCoordinator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mediaNotifSectioner$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaCoordinator$$ExternalSyntheticLambda0);
    }

    public void attach(NotifPipeline notifPipeline) {
        notifPipeline.addFinalizeFilter(this.notifiFilter);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusMediaCoordinator-->attach addFinalizeFilter");
        }
    }

    public final NotifSectioner getMediaNotifSectioner() {
        return (NotifSectioner) this.mediaNotifSectioner$delegate.getValue();
    }

    public final boolean shouldFilterOutByOplusMediaCoordinator(NotificationEntry notificationEntry) {
        if (!CustomizeFeatureOption.sIsNotShowNfcNotification) {
            return false;
        }
        String packageName = notificationEntry.getSbn().getPackageName();
        if (packageName != null && !NFC_PACKAGE_NAME.equals(packageName)) {
            if (!this.notificationMediaManager.getOplusNotificationMediaManagerExt().isOplusCustomMediaNotification(notificationEntry)) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("shouldFilterOut：filter out the notification,entry=", notificationEntry.getEx().getEncryptionKey(), " ", "OplusMediaCoordinator-->", "SystemUi--Notification");
            }
        }
        return true;
    }
}
