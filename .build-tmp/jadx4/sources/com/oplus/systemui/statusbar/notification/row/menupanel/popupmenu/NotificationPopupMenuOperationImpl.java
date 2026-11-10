package com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.util.HeadsUpToZoomUtils;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelHelper;
import com.oplus.systemui.notification.utlils.NotificationZoomStartUtil;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplus.systemui.statusbar.notification.helper.SmallAppHelper;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.notification.row.OplusNotificationGutsManagerExt;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopDialogController;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuOperationImpl extends NotificationPopupMenuOperation {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPopupMenuOperation";
    private final Lazy barService$delegate;
    private final Context context;
    public boolean isCloseChannel;
    private final MetricsLogger metricsLogger;
    private final NotifCollection notifCollection;
    private final dagger.Lazy notificationActivityStarterLazy;
    private final NotificationTopDialogController notificationTopDialogController;
    private final Lazy notificationTopListManage$delegate;
    private final SettingsPanelHelper settingsPanelHelper;
    private final ShadeController shadeController;
    private Toast toast;
    private final UserTracker userTracker;
    private final NotificationVisibilityProvider visibilityProvider;
    private final VisualStabilityCoordinator visualStabilityCoordinator;
    private final VisualStabilityProvider visualStabilityProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationPopupMenuOperationImpl(Context context, MetricsLogger metricsLogger, NotifCollection notifCollection, NotificationVisibilityProvider notificationVisibilityProvider, VisualStabilityProvider visualStabilityProvider, VisualStabilityCoordinator visualStabilityCoordinator, NotificationTopDialogController notificationTopDialogController, ShadeController shadeController, dagger.Lazy lazy, SettingsPanelHelper settingsPanelHelper, UserTracker userTracker) {
        this.context = context;
        this.metricsLogger = metricsLogger;
        this.notifCollection = notifCollection;
        this.visibilityProvider = notificationVisibilityProvider;
        this.visualStabilityProvider = visualStabilityProvider;
        this.visualStabilityCoordinator = visualStabilityCoordinator;
        this.notificationTopDialogController = notificationTopDialogController;
        this.shadeController = shadeController;
        this.notificationActivityStarterLazy = lazy;
        this.settingsPanelHelper = settingsPanelHelper;
        this.userTracker = userTracker;
        NotificationPopupMenuOperationImpl$$ExternalSyntheticLambda0 notificationPopupMenuOperationImpl$$ExternalSyntheticLambda0 = new NotificationPopupMenuOperationImpl$$ExternalSyntheticLambda0();
        notificationPopupMenuOperationImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.notificationTopListManage$delegate = LazyKt__LazyJVMKt.lazy(notificationPopupMenuOperationImpl$$ExternalSyntheticLambda0);
        this.barService$delegate = LazyKt__LazyJVMKt.lazy(new NotificationPopupMenuOperationImpl$$ExternalSyntheticLambda1());
    }

    private final IStatusBarService getBarService() {
        return (IStatusBarService) this.barService$delegate.getValue();
    }

    private final NotificationTopListManage getNotificationTopListManage() {
        return (NotificationTopListManage) this.notificationTopListManage$delegate.getValue();
    }

    public final void dismissLikeSwipe(ExpandableNotificationRow expandableNotificationRow) {
        try {
            this.notifCollection.dismissNotification(expandableNotificationRow.getEntry(), new DismissedByUserStats(3, this.visibilityProvider.obtain(expandableNotificationRow.getEntry(), true)));
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (IllegalStateException e2) {
            Log.e(TAG, Log.getStackTraceString(e2));
        } catch (Exception e3) {
            Log.e(TAG, Log.getStackTraceString(e3));
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation
    public void doOperation(NotificationPopupMenuOperation.Operation operation, ExpandableNotificationRow expandableNotificationRow) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->".concat("doOperation: menuOperation=" + operation + ", " + (ex != null ? ex.getEncryptionKey() : null)));
        }
        switch (operation.ordinal()) {
            case 0:
                setNotificationTop(expandableNotificationRow, true);
                break;
            case 1:
                setNotificationTop(expandableNotificationRow, false);
                break;
            case 2:
                setNotificationSilent(expandableNotificationRow, true);
                break;
            case 3:
                this.shadeController.animateCollapseShade(0);
                Notification notification = expandableNotificationRow.getEntry().getSbn().getNotification();
                PendingIntent pendingIntent = notification.contentIntent;
                if (pendingIntent == null) {
                    pendingIntent = notification.fullScreenIntent;
                }
                if (pendingIntent == null || !pendingIntent.isActivity()) {
                    pendingIntent = null;
                }
                if (pendingIntent != null) {
                    try {
                        if (!HeadsUpToZoomUtils.isZoomOneHandMode(this.context)) {
                            NotificationZoomStartUtil.startZoomFromNotification(pendingIntent.getIntent(), expandableNotificationRow.getEntry().getKey(), Integer.valueOf(expandableNotificationRow.getEntry().getSbn().getUserId()), this.context.getPackageName(), 20, expandableNotificationRow.getEntry().getSbn().getOpPkg());
                            Object obj = this.notificationActivityStarterLazy.get();
                            String str = obj instanceof StatusBarNotificationActivityStarter ? (StatusBarNotificationActivityStarter) obj : null;
                            if (str != null) {
                                ((StatusBarNotificationActivityStarter) str).mActivityStarterEx.scheduleAutoCancelDismissal(str, expandableNotificationRow.getEntry(), expandableNotificationRow);
                            }
                            NotificationStatisticUtil.onNotificationClickToZoom(expandableNotificationRow.getContext(), expandableNotificationRow.getEntry().getSbn(), expandableNotificationRow.mRowEx.getPackageFromPendingIntent(expandableNotificationRow));
                        } else {
                            pendingIntent.send();
                            Toast toast = this.toast;
                            Context context = this.context;
                            if (toast == null) {
                                this.toast = Toast.makeText(context, context.getResources().getString(2131955001), 0);
                            } else {
                                toast.setText(context.getResources().getString(2131955001));
                            }
                            Toast toast2 = this.toast;
                            if (toast2 != null) {
                                toast2.show();
                            }
                        }
                    } catch (PendingIntent.CanceledException unused) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->zoom reply wrong CanceledException");
                            return;
                        }
                        return;
                    } catch (RemoteException unused2) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->zoom reply wrong RemoteException");
                            return;
                        }
                        return;
                    } catch (SecurityException unused3) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->zoom reply wrong SecurityException");
                            return;
                        }
                        return;
                    }
                    break;
                }
                break;
            case 4:
                this.settingsPanelHelper.resolveSnoozeClick();
                break;
            case 5:
                setNotificationSilent(expandableNotificationRow, false);
                break;
            case 6:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusNotificationEntryEx ex2 = expandableNotificationRow.getEntry().getEx();
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("gotoSetting: ", ex2 != null ? ex2.getEncryptionKey() : null, "NotificationPopupMenuOperation-->", "SystemUi--Notification");
                }
                this.metricsLogger.action(205);
                IStatusBarService barService = getBarService();
                if (barService != null) {
                    barService.onNotificationSettingsViewed(expandableNotificationRow.getEntry().getKey());
                }
                int userId = this.userTracker.getUserId();
                int uid = expandableNotificationRow.getEntry().getSbn().getUid();
                boolean zAreEqual = Intrinsics.areEqual(expandableNotificationRow.getEntry().getSbn().getPackageName(), "com.android.systemui");
                Context context2 = this.context;
                dagger.Lazy lazy = this.notificationActivityStarterLazy;
                if (!zAreEqual) {
                    OplusNotificationGutsManagerExt.INSTANCE.startOplusAppNotificationSettingsActivity(context2, (NotificationActivityStarter) lazy.get(), uid, expandableNotificationRow);
                } else {
                    OplusNotificationGutsManagerExt.INSTANCE.startOplusAppNotificationSettingsActivity(context2, (NotificationActivityStarter) lazy.get(), UserHandle.getUid(userId, UserHandle.getAppId(uid)), expandableNotificationRow);
                }
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusNotificationEntryEx ex3 = expandableNotificationRow.getEntry().getEx();
                    Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("closeNotification: ", ex3 != null ? ex3.getEncryptionKey() : null, ", isCloseChannel=", this.isCloseChannel, "NotificationPopupMenuOperation-->"));
                }
                NotificationCenterHelper notificationCenterHelper = (NotificationCenterHelper) DependencyEx.sDependency.getDependency(NotificationCenterHelper.class);
                SmallAppHelper.Companion companion = SmallAppHelper.Companion;
                if (!companion.isSmallApp(expandableNotificationRow.getEntry().getSbn())) {
                    NotificationEntry entry = expandableNotificationRow.getEntry();
                    Context context3 = this.context;
                    entry.getClass();
                    if (OplusNotificationCommercializeControl.isChannelInWhiteList(context3, entry) || this.isCloseChannel) {
                        String packageName = entry.getSbn().getPackageName();
                        int uid2 = expandableNotificationRow.getEntry().getSbn().getUid();
                        NotificationChannel channel = entry.getChannel();
                        notificationCenterHelper.switchNotificationChannel(packageName, uid2, channel != null ? channel.getId() : null, "channel", false, 101);
                        if ((entry.getSbn().getNotification().flags & 64) != 0) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                OplusNotificationEntryEx ex4 = entry.getEx();
                                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("closeNotification: FGS notification, dismiss like swipe, ", ex4 != null ? ex4.getEncryptionKey() : null, "NotificationPopupMenuOperation-->", "SystemUi--Notification");
                            }
                            dismissLikeSwipe(expandableNotificationRow);
                        }
                    } else {
                        notificationCenterHelper.setNotificationsEnabledWithImportanceLockForPackage(entry.getSbn().getPackageName(), expandableNotificationRow.getEntry().getSbn().getUid(), false, 102);
                    }
                } else {
                    companion.setSmallAppNotify(this.context, false, expandableNotificationRow.getEntry().getSbn());
                    dismissLikeSwipe(expandableNotificationRow);
                }
                break;
            default:
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                break;
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation
    public final boolean isCloseChannel() {
        return this.isCloseChannel;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation
    public boolean isJumpActivity(NotificationPopupMenuOperation.Operation operation) {
        return operation == NotificationPopupMenuOperation.Operation.NOTIFICATION_TO_ZOOM || operation == NotificationPopupMenuOperation.Operation.NOTIFICATION_SETTING;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation
    public void setCloseChannel(boolean z) {
        this.isCloseChannel = z;
    }

    public final void setNotificationSilent(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->".concat("setNotificationSilent: isSilent=" + z + ", " + (ex != null ? ex.getEncryptionKey() : null)));
        }
        try {
            StatusBarNotification sbn = expandableNotificationRow.getEntry().getSbn();
            ((NotificationCenterHelper) DependencyEx.sDependency.getDependency(NotificationCenterHelper.class)).setStowOption(sbn.getPackageName(), sbn.getUid(), z ? -1 : 1);
            this.visualStabilityProvider.setReorderingAllowed(true);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->setNotificationSilent: isReorderingAllowed, Because the importance has changed,");
            }
            this.visualStabilityCoordinator.temporarilyAllowSectionChanges(expandableNotificationRow.getEntry(), SystemClock.uptimeMillis());
            String packageName = sbn.getPackageName();
            if (packageName != null) {
                CommercialBlackListManager.Companion.addExemption(packageName, this.context);
            }
        } catch (RemoteException unused) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPopupMenuOperation--> setNotificationSilent failed");
            }
        }
    }

    public final void setNotificationTop(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        StatusBarNotification sbn;
        String packageName;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
            Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->".concat("setNotificationTop: isTopped=" + z + ", " + (ex != null ? ex.getEncryptionKey() : null)));
        }
        try {
            this.visualStabilityProvider.setReorderingAllowed(true);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPopupMenuOperation-->setNotificationTop: isReorderingAllowed");
            }
            int integer = this.context.getResources().getInteger(2131427707);
            NotificationEntry representativeEntry2 = expandableNotificationRow.getEntry().getRepresentativeEntry();
            if (representativeEntry2 == null || (sbn = representativeEntry2.getSbn()) == null || (packageName = sbn.getPackageName()) == null || (representativeEntry = expandableNotificationRow.getEntry().getRepresentativeEntry()) == null || (sbn2 = representativeEntry.getSbn()) == null) {
                return;
            }
            int uid = sbn2.getUid();
            NotificationTopListManage notificationTopListManage = getNotificationTopListManage();
            Integer numValueOf = notificationTopListManage != null ? Integer.valueOf(((NotificationTopListManageImpl) notificationTopListManage).getNotificationTopNum()) : null;
            if (numValueOf != null && numValueOf.intValue() >= integer && z) {
                this.notificationTopDialogController.showDialog(packageName, Integer.valueOf(uid));
                return;
            }
            NotificationTopListManage notificationTopListManage2 = getNotificationTopListManage();
            if (notificationTopListManage2 != null) {
                ((NotificationTopListManageImpl) notificationTopListManage2).notificationTopOperation(this.context, packageName, Integer.valueOf(uid), z);
            }
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, Log.getStackTraceString(e));
        }
    }
}
