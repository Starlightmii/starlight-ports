package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodNotificationCoordinator implements CoreStartable, AodNotificationCoordinatorCallback {
    public static final Companion Companion = new Companion();
    private static final String PANORAMIC_AOD_CUSTOM_NOTIFICATION_PKG_BLACK = "panoramic_aod_custom_Notification_pkg_black";
    private final Context context;
    private final InitController initController;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    private List<String> packageNameList = CollectionsKt__CollectionsKt.arrayListOf(new String[]{""});
    private final ContentObserver pkgBlackObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.AodNotificationCoordinator$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ AodNotificationCoordinator this$0;

        @Override // java.lang.Runnable
        public final void run() {
            AodNotificationCoordinator.access$setPackageNameList(this.this$0);
            this.this$0.context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(AodNotificationCoordinator.PANORAMIC_AOD_CUSTOM_NOTIFICATION_PKG_BLACK), false, this.this$0.pkgBlackObserver);
        }
    }

    public AodNotificationCoordinator(Context context, InitController initController, OplusPanoramicAodRepository oplusPanoramicAodRepository) {
        this.context = context;
        this.initController = initController;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        final Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        this.pkgBlackObserver = new ContentObserver(workHandler) { // from class: com.oplus.systemui.statusbar.notification.collection.coordinator.AodNotificationCoordinator$pkgBlackObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                AodNotificationCoordinator.access$setPackageNameList(this.this$0);
            }
        };
    }

    public static final void access$setPackageNameList(AodNotificationCoordinator aodNotificationCoordinator) {
        String string = Settings.Global.getString(aodNotificationCoordinator.context.getContentResolver(), PANORAMIC_AOD_CUSTOM_NOTIFICATION_PKG_BLACK);
        if (string != null) {
            aodNotificationCoordinator.packageNameList = StringsKt__StringsKt.split$default(string, new char[]{','}, 6);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.collection.coordinator.AodNotificationCoordinatorCallback
    public boolean isHidePanoramicAodCustomNotification(NotificationEntry notificationEntry) {
        return this.oplusPanoramicAodRepository.getSwitchAodEnable() && this.packageNameList.contains(notificationEntry.getSbn().getPackageName()) && notificationEntry.containsCustomViews();
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}
