package com.oplus.systemui.notification.notificationcenter;

import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.statusbar.notification.helper.AfterSaleModeAlertHelper;
import com.oplus.systemui.statusbar.notification.helper.DndAlertHelper;
import com.oplus.systemui.statusbar.notification.helper.HelperExtManager;
import com.oplus.systemui.statusbar.notification.helper.SmartDriverHelper;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.TimeZone;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationCenterManager extends HelperExtManager implements NotificationCenterManagerEx {
    Lazy mAonAuthManager;
    public Context mContext;
    DndAlertHelper mDndAlertHelper;
    public final AnonymousClass1 mIMultiUserListener;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public SysuiStatusBarStateController mStatusBarStateController;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.notificationcenter.NotificationCenterManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IMultiUserListener {
        public /* synthetic */ NotificationCenterManager this$0;

        @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
        public final void onSwitched(int i) {
            ThreadManager threadManager = ThreadManager.getInstance();
            NotificationCenterManager$1$$ExternalSyntheticLambda0 notificationCenterManager$1$$ExternalSyntheticLambda0 = new NotificationCenterManager$1$$ExternalSyntheticLambda0();
            notificationCenterManager$1$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            threadManager.postInThreadDelay(notificationCenterManager$1$$ExternalSyntheticLambda0, 5000L);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.notificationcenter.NotificationCenterManager$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ NotificationCenterManager this$0;

        public final void onTimeChanged() {
            AodMediaStatisticUtil.onTimeChanged(this.this$0.mContext);
        }

        public final void onTimeZoneChanged(TimeZone timeZone) {
            AodMediaStatisticUtil.onTimeChanged(this.this$0.mContext);
        }
    }

    public NotificationCenterManager() {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mIMultiUserListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mKeyguardUpdateMonitorCallback = anonymousClass2;
    }

    public static void initNotificationPolicy(Context context) {
        NotificationManager notificationManager;
        NotificationManager.Policy notificationPolicy;
        if (MultiUserContentHelper.getSecureIntValue(context, "NotificationInitPolicy", 0) == 0 && MultiUserContentHelper.getSecureIntValue(context, "ColorNotificationInitPolicy", 0) == 0 && (notificationPolicy = (notificationManager = (NotificationManager) context.getSystemService("notification")).getNotificationPolicy()) != null) {
            notificationManager.setNotificationPolicy(new NotificationManager.Policy(notificationPolicy.priorityCategories & (-17), notificationPolicy.priorityCallSenders, notificationPolicy.priorityMessageSenders, notificationPolicy.suppressedVisualEffects & (-257), notificationPolicy.state));
            MultiUserContentHelper.setSecureIntValue(context, "NotificationInitPolicy", 1);
        }
        if (MultiUserContentHelper.getSecureIntValue(context, "show_notification_snooze", 0) == 1) {
            MultiUserContentHelper.setSecureIntValue(context, "show_notification_snooze", 0);
            MultiUserContentHelper.setSecureIntValue(context, "delay_notification_switch_state", 1);
        }
        if (MultiUserContentHelper.getSecureIntValue(context, "keyguard_notification_visibility_migrated", 0) != 0) {
            return;
        }
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDevice()) {
            return;
        }
        boolean z = MultiUserContentHelper.getSecureIntValue(context, "lock_screen_show_notifications", 1) != 0;
        boolean z2 = MultiUserContentHelper.getSecureIntValue(context, "lock_screen_allow_private_notifications", 1) != 0;
        int secureIntValue = MultiUserContentHelper.getSecureIntValue(context, "keyguard_notification_visibility", 1);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(secureIntValue, LogUtil.TAG_NOTIFICATION, "NotificationCenterManager", TextFieldImplKt$.ExternalSyntheticOutline0.m("migrateLockscreenShowNotifFields oldLockscreenShowNotif:", ",oldAllowPrivateNotif:", ",oldKeyguardNotifVisibility:", z, z2));
        if (!z || secureIntValue == 3) {
            if (secureIntValue != 3) {
                MultiUserContentHelper.setSecureIntValue(context, "keyguard_notification_visibility", 3);
            }
        } else if (!z2 && secureIntValue == 1) {
            MultiUserContentHelper.setSecureIntValue(context, "keyguard_notification_visibility", 2);
        }
        if (!z) {
            MultiUserContentHelper.setSecureIntValue(context, "lock_screen_show_notifications", 1);
        }
        if (!z2) {
            MultiUserContentHelper.setSecureIntValue(context, "lock_screen_allow_private_notifications", 1);
        }
        MultiUserContentHelper.setSecureIntValue(context, "keyguard_notification_visibility_migrated", 1);
    }

    public int getBarState() {
        return this.mStatusBarStateController.getState();
    }

    public Context getContext() {
        return this.mContext;
    }

    public final Object getHelper() {
        Object obj = this.mHelpers.get(SmartDriverHelper.class);
        try {
            Context context = this.mContext;
            SmartDriverHelper smartDriverHelper = new SmartDriverHelper();
            smartDriverHelper.mContext = context;
            smartDriverHelper.mSmartDriverPkg = context.getString(2131956480);
            return smartDriverHelper;
        } catch (Exception e) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationCenterManager", "getHelper: class: " + SmartDriverHelper.class.toString() + " = null ," + e.getMessage());
            Log.d("NotificationCenterManager", "getHelper: class: " + SmartDriverHelper.class.toString() + " = null ," + Log.getStackTraceString(e));
            return obj;
        }
    }

    public final void init(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.mContext = context;
        ThreadManager threadManager = ThreadManager.getInstance();
        NotificationCenterManager$$ExternalSyntheticLambda0 notificationCenterManager$$ExternalSyntheticLambda0 = new NotificationCenterManager$$ExternalSyntheticLambda0();
        notificationCenterManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        threadManager.postInThread(notificationCenterManager$$ExternalSyntheticLambda0);
        this.mStatusBarStateController = (SysuiStatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
        AfterSaleModeAlertHelper afterSaleModeAlertHelper = (AfterSaleModeAlertHelper) DependencyEx.sDependency.getDependency(AfterSaleModeAlertHelper.class);
        afterSaleModeAlertHelper.getClass();
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "AfterSaleModeAlertHelper", "Register repairModeReceiver");
        ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(afterSaleModeAlertHelper.mRepairModeReceiver, new IntentFilter("android.intent.action.USER_SWITCHED"), (Executor) null, UserHandle.ALL);
        MultiUserManager.getInstance().addListener(this.mIMultiUserListener);
    }

    public final void onCentralSurfacesStartComplete() {
        ((AonAuthManager) this.mAonAuthManager.get()).init();
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(this.mKeyguardUpdateMonitorCallback);
    }
}
