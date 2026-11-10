package com.oplus.systemui.statusbar.notification.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.wm.shell.bubbles.Bubbles;
import com.oplus.systemui.keyguard.customize.KeyguardCustomizeManager;
import com.oplus.systemui.notification.envelope.EnvelopeHelper;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.statusbar.notification.dnd.seedling.DndSeedling;
import com.oplus.systemui.statusbar.notification.smallapp.SmallApp;
import com.oplus.systemui.statusbar.notification.smallapp.SmallAppUtil;
import com.oplus.systemui.statusbar.observer.LockScreenNotificationEnableSinkingObserver;
import com.oplus.systemui.statusbar.observer.LockScreenNotificationStyleObserver;
import com.oplus.systemui.statusbar.observer.ZenModeKeyguardSettingsObserver;
import com.oplus.systemui.statusbar.observer.ZenModeSettingsObserver;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.feature.ChargingFeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Optional;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationKeyguardHelper {
    Lazy mAfterSaleModeAlertHelper;
    Optional<Bubbles> mBubblesOptional;
    public final Context mContext;
    Lazy mDndAlertHelper;
    Lazy mEnvelopeHelper;
    public boolean mHideForCharge;
    public final LockScreenNotificationEnableSinkingObserver mLockScreenNotificationEnableSinkingObserver;
    public int mLockScreenNotificationState;
    public final LockScreenNotificationStyleObserver mLockScreenNotificationStyleObserver;
    public final Handler mMainHandler;
    public final AnonymousClass1 mMultiUserListener;
    public final AnonymousClass2 mNotifFilter;
    Lazy mNotifPipeline;
    Lazy mNotificationCenterHelper;
    public final NotificationKeyguardHelper$$ExternalSyntheticLambda0 mResetChargeStateRunnable;
    public final StatusBarStateController mStatusBarStateController;
    public final ZenModeKeyguardSettingsObserver mZenModeKeyguardObserver;
    public final ZenModeSettingsObserver mZenModeObserver;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IMultiUserListener {
        public /* synthetic */ NotificationKeyguardHelper this$0;

        @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
        public final void onSwitched(int i) {
            LogUtil.i(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", "on user switched update dnd settings");
            this.this$0.updateNotificationLockScreenState();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends NotifFilter {
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LockScreenNotificationObserver extends ContentObserver {
        public LockScreenNotificationObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            NotificationKeyguardHelper.this.updateNotificationLockScreenState();
        }
    }

    public NotificationKeyguardHelper(Context context, DndSeedling dndSeedling) {
        ZenModeSettingsObserver zenModeSettingsObserver = new ZenModeSettingsObserver();
        this.mZenModeObserver = zenModeSettingsObserver;
        ZenModeKeyguardSettingsObserver zenModeKeyguardSettingsObserver = new ZenModeKeyguardSettingsObserver();
        this.mZenModeKeyguardObserver = zenModeKeyguardSettingsObserver;
        LockScreenNotificationObserver lockScreenNotificationObserver = new LockScreenNotificationObserver();
        LockScreenNotificationStyleObserver lockScreenNotificationStyleObserver = new LockScreenNotificationStyleObserver();
        this.mLockScreenNotificationStyleObserver = lockScreenNotificationStyleObserver;
        LockScreenNotificationEnableSinkingObserver lockScreenNotificationEnableSinkingObserver = new LockScreenNotificationEnableSinkingObserver();
        this.mLockScreenNotificationEnableSinkingObserver = lockScreenNotificationEnableSinkingObserver;
        this.mStatusBarStateController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
        this.mMainHandler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        NotificationKeyguardHelper$$ExternalSyntheticLambda0 notificationKeyguardHelper$$ExternalSyntheticLambda0 = new NotificationKeyguardHelper$$ExternalSyntheticLambda0();
        notificationKeyguardHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mResetChargeStateRunnable = notificationKeyguardHelper$$ExternalSyntheticLambda0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mNotifFilter = new AnonymousClass2("NotificationKeyguardHelupdateNotificationsNoneFilter");
        this.mContext = context;
        ObserverDispatcher.getInstance().init(context);
        Uri uriFor = Settings.Secure.getUriFor("keyguard_notification_visibility");
        zenModeSettingsObserver.register(context, false);
        zenModeKeyguardSettingsObserver.registerForUserSwitch(context, -1, false);
        lockScreenNotificationStyleObserver.registerForUserSwitch(context, -1, false);
        lockScreenNotificationEnableSinkingObserver.registerForUserSwitch(context, -1, false);
        ObserverDispatcher.getInstance().registerObserver(uriFor, lockScreenNotificationObserver, -1);
        MultiUserManager.getInstance().addListener(anonymousClass1);
        dndSeedling.init();
        updateNotificationLockScreenState();
    }

    public final void addFinalizeFilter() {
        ((NotifPipeline) this.mNotifPipeline.get()).addFinalizeFilter(this.mNotifFilter);
    }

    public Lazy getEnvelopeHelper() {
        return this.mEnvelopeHelper;
    }

    public int getLockScreenNotificationStyle() {
        return this.mLockScreenNotificationStyleObserver.getLockScreenNotificationStyle();
    }

    public Lazy getNotifPipeline() {
        return this.mNotifPipeline;
    }

    public final boolean isWhiteListNotificationOnKeyguard(NotificationEntry notificationEntry) {
        boolean z;
        SmartDriverHelper smartDriverHelper = (SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper();
        smartDriverHelper.getClass();
        if (notificationEntry.getSbn() == null || notificationEntry.getSbn().getNotification() == null) {
            z = false;
        } else {
            z = smartDriverHelper.mSmartDriverPkg.equals(notificationEntry.getSbn().getPackageName()) && "smart_drive_channel".equals(notificationEntry.getSbn().getNotification().getChannelId()) && !notificationEntry.getSbn().isClearable();
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isSmartDriveChannelNotification:", ",notification:", z);
            sbM.append(notificationEntry.getEx().getEncryptionKey());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--SmartDriverHelper", sbM.toString());
        }
        return z || ((DndAlertHelper) this.mDndAlertHelper.get()).isDNDNoticeChannelNotification(notificationEntry) || ((AfterSaleModeAlertHelper) this.mAfterSaleModeAlertHelper.get()).isAfterSaleModeNoticeChannelNotification(notificationEntry);
    }

    public final void resetNotificationForCharge() {
        ChargingFeatureOption.INSTANCE.getClass();
        if (ChargingFeatureOption.isSupportFullscreenChargeAnim()) {
            return;
        }
        StringBuilder sb = new StringBuilder("resetNotificationForCharge mHideForCharge ");
        sb.append(this.mHideForCharge);
        sb.append("  getState = ");
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        sb.append(statusBarStateController.getState());
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", sb.toString());
        if (this.mHideForCharge) {
            Handler handler = this.mMainHandler;
            NotificationKeyguardHelper$$ExternalSyntheticLambda0 notificationKeyguardHelper$$ExternalSyntheticLambda0 = this.mResetChargeStateRunnable;
            if (handler.hasCallbacks(notificationKeyguardHelper$$ExternalSyntheticLambda0)) {
                handler.removeCallbacks(notificationKeyguardHelper$$ExternalSyntheticLambda0);
            }
            this.mHideForCharge = false;
            int state = statusBarStateController.getState();
            if (state == 1 || state == 2) {
                this.mNotifFilter.invalidateList("updateNotificationsNoneFilter");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:47:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:50:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ff  */
    /* JADX WARN: Instruction removed from duplicated block: B:61:0x00ff, please report this as an issue */
    public final boolean shouldShowOnKeyguard(StatusBarNotification statusBarNotification) {
        Notification notification;
        StatusBarNotification sbn;
        boolean zIsSmallApp;
        String string;
        SmallApp smallAppForCache;
        int lockscreenVisibility;
        if (statusBarNotification != null) {
            int i = this.mLockScreenNotificationState;
            if (i == 2 || i == 1) {
                NotificationEntry entry = this.mNotifPipeline.get() != null ? ((NotifPipeline) this.mNotifPipeline.get()).getEntry(statusBarNotification.getKey()) : null;
                if (entry != null && this.mZenModeObserver.mZenModeStatusOn) {
                    NotificationChannel channel = entry.getChannel();
                    if (!entry.shouldSuppressVisualEffect(256)) {
                        if (this.mZenModeKeyguardObserver.mZenModeKeyguardShowStatus && channel != null && !channel.canBypassDnd()) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", "canBypassDnd" + channel.canBypassDnd());
                            return false;
                        }
                        if (!KeyguardCustomizeManager.Companion.getInstance().needDisableNotification()) {
                            EnvelopeHelper envelopeHelper = (EnvelopeHelper) this.mEnvelopeHelper.get();
                            notification = statusBarNotification.getNotification();
                            envelopeHelper.getClass();
                            if (notification == null) {
                                if (notification.extras.getString("notification_source") == null) {
                                }
                                return true;
                            }
                            if (LogUtil.getNormalDebug()) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "isEnvelopeNotification: notification is null");
                            }
                            if (entry != null) {
                            }
                            if (entry != null) {
                                sbn = entry.getSbn();
                                zIsSmallApp = SmallAppHelper.isSmallApp(sbn);
                                string = sbn.getNotification().extras.getString("small_app_package", "");
                                if (!zIsSmallApp) {
                                }
                                if (zIsSmallApp) {
                                    LogUtil.i(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", "Notification--shouldShowOnKeguard sb: " + LogUtil.encryptionNotificationKey(sbn) + ",smallApp:" + zIsSmallApp + ",smallAppPkg:" + string);
                                    smallAppForCache = SmallAppUtil.getSmallAppForCache(this.mContext, string);
                                    if (smallAppForCache != null) {
                                    }
                                }
                            }
                        }
                    }
                } else if (!KeyguardCustomizeManager.Companion.getInstance().needDisableNotification() && !ChildrenModeObserver.getInstance().mIsChildrenMode && !NotificationUtil.isATOVersion()) {
                    EnvelopeHelper envelopeHelper2 = (EnvelopeHelper) this.mEnvelopeHelper.get();
                    notification = statusBarNotification.getNotification();
                    envelopeHelper2.getClass();
                    if (notification == null) {
                        if (notification.extras.getString("notification_source") == null) {
                        }
                        return true;
                    }
                    if (LogUtil.getNormalDebug()) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--EnvelopeHelper", "isEnvelopeNotification: notification is null");
                    }
                    NotificationListenerService.Ranking ranking = entry != null ? entry.getRanking() : null;
                    if (entry != null) {
                        sbn = entry.getSbn();
                        zIsSmallApp = SmallAppHelper.isSmallApp(sbn);
                        string = sbn.getNotification().extras.getString("small_app_package", "");
                        if (!zIsSmallApp || (((lockscreenVisibility = ((NotificationCenterHelper) this.mNotificationCenterHelper.get()).getLockscreenVisibility(entry)) != -1000 && lockscreenVisibility != 1 && lockscreenVisibility != 0) || ranking == null || ranking.getLockscreenVisibilityOverride() == -1)) {
                            if (zIsSmallApp) {
                                LogUtil.i(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", "Notification--shouldShowOnKeguard sb: " + LogUtil.encryptionNotificationKey(sbn) + ",smallApp:" + zIsSmallApp + ",smallAppPkg:" + string);
                                smallAppForCache = SmallAppUtil.getSmallAppForCache(this.mContext, string);
                                if (smallAppForCache != null || !smallAppForCache.mNotify || !smallAppForCache.mLockScreen) {
                                }
                            }
                        }
                        return true;
                    }
                }
            } else if (LogUtil.getNormalDebug()) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", "shouldShowOnKeguard updateNotificationLockScreenState :");
                return false;
            }
        }
        return false;
    }

    public final void updateNotificationLockScreenState() {
        if (this.mContext != null) {
            NotificationFeatureOption.INSTANCE.getClass();
            this.mLockScreenNotificationState = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "keyguard_notification_visibility", NotificationFeatureOption.isDefaultLockScreenNotificationContentHidden ? 2 : 1, KeyguardUpdateMonitor.getCurrentUser());
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mLockScreenNotificationState, LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", new StringBuilder("updateNotificationLockScreenState: "));
        }
    }
}
