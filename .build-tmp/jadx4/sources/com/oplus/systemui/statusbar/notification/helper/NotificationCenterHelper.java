package com.oplus.systemui.statusbar.notification.helper;

import android.app.INotificationManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy;
import com.oplus.systemui.statusbar.notification.smallapp.SmallApp;
import com.oplus.systemui.statusbar.notification.smallapp.SmallAppUtil;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCenterHelper implements NotificationCenterProxy.ProxyActionListener {
    public static final Companion Companion = new Companion();
    private static final String DELAY_SWITCH_STATE = "delay_notification_switch_state";
    private static final String TAG = "NotificationCenterHelper";
    private static final String UNIMPORTANCE_STYLE = "unimportant_notification_style";
    public boolean isShowNotificationSnooze;
    private final Context mContext;
    private final INotificationManager mINotificationManager;
    private final NotificationsController mNotificationsController;
    private final ContentObserver mShowNotificationSnoozeObserver;
    private ArrayList<String> noPopmenuBackList;
    private final NotificationCenterConnect notificationCenterConnect;
    private final NotificationCenterProxy notificationCenterProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface NormAppGetter {
        Object get(StatusBarNotification statusBarNotification, NotificationChannel notificationChannel, String str, int i, String str2);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface SmallAppGetter {
        Object get(SmallApp smallApp);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper$canShowIcon$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NormAppGetter {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NotificationCenterHelper this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper.NormAppGetter
        public final Object get(StatusBarNotification statusBarNotification, NotificationChannel notificationChannel, String str, int i, String str2) {
            switch (this.$r8$classId) {
                case 0:
                    Object obj = Boolean.TRUE;
                    try {
                        Field declaredField = notificationChannel.getClass().getDeclaredField("mOplusNotificationChannelExt");
                        Class[] clsArr = new Class[0];
                        declaredField.setAccessible(true);
                        Object obj2 = declaredField.get(notificationChannel);
                        Object objInvoke = obj2.getClass().getMethod("canShowIcon", (Class[]) Arrays.copyOf(clsArr, 0)).invoke(obj2, Arrays.copyOf(clsArr, 0));
                        if (objInvoke != null) {
                            obj = objInvoke;
                        }
                    } catch (Exception unused) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationCenterHelper-->".concat("Notification--reflection call failed."));
                        }
                    }
                    return (Boolean) obj;
                default:
                    return Integer.valueOf(notificationChannel.getLockscreenVisibility());
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper$canShowIcon$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements SmallAppGetter {
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper.SmallAppGetter
        public final Object get(SmallApp smallApp) {
            switch (this.$r8$classId) {
                case 0:
                    return Boolean.valueOf(smallApp.mShowIcon);
                default:
                    return Integer.valueOf(smallApp.getLockscreenVisibility());
            }
        }
    }

    public NotificationCenterHelper(NotificationsController notificationsController, INotificationManager iNotificationManager, Context context, Lazy lazy) {
        final Handler handler = new Handler();
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper$mShowNotificationSnoozeObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                this.this$0.updateShowNotificationSnooze();
            }
        };
        this.mShowNotificationSnoozeObserver = contentObserver;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "init: ", null, 8);
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        Context userContext = Intrinsics.areEqual(context.getUser(), ((UserTracker) lazy.get()).getUserHandle()) ? applicationContext : ((UserTracker) lazy.get()).getUserContext();
        NotificationCenterConnect.Companion companion = NotificationCenterConnect.Companion;
        userContext.getClass();
        NotificationCenterConnect notificationCenterConnectCreate = companion.create(userContext, 1500L);
        this.notificationCenterConnect = notificationCenterConnectCreate;
        this.mNotificationsController = notificationsController;
        this.mINotificationManager = iNotificationManager;
        NotificationCenterProxy notificationCenterProxy = new NotificationCenterProxy(applicationContext, userContext, (UserTracker) lazy.get(), notificationCenterConnectCreate);
        this.notificationCenterProxy = notificationCenterProxy;
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(DELAY_SWITCH_STATE), contentObserver, -1);
        notificationCenterProxy.startRegister(this);
        updateShowNotificationSnooze();
        OplusNotificationCommercializeControl.registerOnlineOngoingDismissWhiteList(context);
    }

    public final boolean canShowIcon(NotificationEntry notificationEntry) {
        int i = 0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        return ((Boolean) get(notificationEntry, anonymousClass1, new AnonymousClass2(i), Boolean.FALSE)).booleanValue();
    }

    public final Object get(NotificationEntry notificationEntry, NormAppGetter normAppGetter, SmallAppGetter smallAppGetter, Object obj) {
        StatusBarNotification sbn;
        if (notificationEntry != null) {
            try {
                sbn = notificationEntry.getSbn();
            } catch (Exception e) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("Notification--", FileProvider$.ExternalSyntheticOutline0.m("Notification--get: error:", e.getMessage()), "NotificationCenterHelper-->", "SystemUi--Notification");
                }
            }
        } else {
            sbn = null;
        }
        StatusBarNotification statusBarNotification = sbn;
        if (statusBarNotification != null) {
            SmallAppHelper.Companion companion = SmallAppHelper.Companion;
            if (companion.isSmallApp(statusBarNotification)) {
                SmallApp smallAppForCache = SmallAppUtil.getSmallAppForCache(this.mContext, companion.getSmallAppPkg(statusBarNotification));
                if (smallAppForCache != null) {
                    return smallAppGetter.get(smallAppForCache);
                }
            } else {
                String packageName = statusBarNotification.getPackageName();
                int uid = statusBarNotification.getUid();
                String channelId = statusBarNotification.getNotification().getChannelId();
                NotificationChannel channel = getChannel(notificationEntry);
                if (channel != null) {
                    return normAppGetter.get(statusBarNotification, channel, packageName, uid, channelId);
                }
            }
        }
        return obj;
    }

    public final NotificationChannel getChannel(NotificationEntry notificationEntry) {
        if (notificationEntry != null) {
            return notificationEntry.getChannel();
        }
        return null;
    }

    public final int getLockscreenVisibility(NotificationEntry notificationEntry) {
        int i = 1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        return ((Number) get(notificationEntry, anonymousClass1, new AnonymousClass2(i), 1)).intValue();
    }

    public final int getStowOption(String str, int i, int i2) {
        Integer numValueOf = Integer.valueOf(i2);
        NotificationCenterHelper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationCenterHelper$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = str;
        externalSyntheticLambda0.f$1 = i;
        VarHandle.storeStoreFence();
        Number number = (Number) runAndWaitResult(numValueOf, externalSyntheticLambda0);
        int iIntValue = number.intValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(iIntValue, "NotificationCenterHelper-->", "SystemUi--Notification", ConstraintSet$.ExternalSyntheticOutline0.m(i, "getStowOption: pkg = ", str, ", uid = ", ", option = "));
        }
        return number.intValue();
    }

    public final boolean isNoPopmenuByPkg(Context context, String str) {
        if (str != null && str.length() != 0) {
            ArrayList<String> arrayList = this.noPopmenuBackList;
            if (arrayList == null || arrayList.isEmpty()) {
                this.noPopmenuBackList = new ArrayList<>(ArraysKt___ArraysKt.toList(context.getResources().getStringArray(2130903248)));
            }
            ArrayList<String> arrayList2 = this.noPopmenuBackList;
            if (arrayList2 != null && !arrayList2.isEmpty() && arrayList2.contains(str)) {
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    return true;
                }
                Log.d("SystemUi--Common", "NotificationCenterHelper-->isNoPopmenuByPkg by backList");
                return true;
            }
        }
        return false;
    }

    @Override // com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy.ProxyActionListener
    public void notifyBroadcast(String str, Intent intent) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("notifyBroadcast:: action = ", str, "NotificationCenterHelper-->", "SystemUi--Notification");
        }
        NotificationCenterConnect notificationCenterConnect = this.notificationCenterConnect;
        NotificationCenterHelper$$ExternalSyntheticLambda1 notificationCenterHelper$$ExternalSyntheticLambda1 = new NotificationCenterHelper$$ExternalSyntheticLambda1();
        notificationCenterHelper$$ExternalSyntheticLambda1.f$0 = str;
        notificationCenterHelper$$ExternalSyntheticLambda1.f$1 = intent;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(notificationCenterHelper$$ExternalSyntheticLambda1);
    }

    public final void notifyChannelOrGroupAdded(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
        NotificationCenterConnect notificationCenterConnect = this.notificationCenterConnect;
        NotificationCenterHelper$$ExternalSyntheticLambda5 notificationCenterHelper$$ExternalSyntheticLambda5 = new NotificationCenterHelper$$ExternalSyntheticLambda5();
        notificationCenterHelper$$ExternalSyntheticLambda5.f$0 = str;
        notificationCenterHelper$$ExternalSyntheticLambda5.f$1 = userHandle;
        notificationCenterHelper$$ExternalSyntheticLambda5.f$2 = notificationChannel;
        notificationCenterHelper$$ExternalSyntheticLambda5.f$3 = i;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(notificationCenterHelper$$ExternalSyntheticLambda5);
    }

    @Override // com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy.ProxyActionListener
    public void onUserChanged(int i, Context context) {
        this.notificationCenterConnect.onUserChanged(i, context);
        updateShowNotificationSnooze();
    }

    @Override // com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy.ProxyActionListener, com.oplus.systemui.notification.notificationcenter.NotificationServiceCaller
    public <T> T runAndWaitResult(T t, Function1 function1) {
        return (T) this.notificationCenterConnect.runAndWaitResult(t, function1);
    }

    public final void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z, int i2) {
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "setNotificationsEnabledWithImportanceLockForPackage:: pkg = ", str, ", uid = ", ", enabled2 = ");
        sbM.append(z);
        sbM.append(", reason = ");
        sbM.append(i2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        NotificationCenterConnect notificationCenterConnect = this.notificationCenterConnect;
        NotificationCenterHelper$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new NotificationCenterHelper$.ExternalSyntheticLambda3();
        externalSyntheticLambda3.f$0 = str;
        externalSyntheticLambda3.f$1 = i;
        externalSyntheticLambda3.f$2 = z;
        externalSyntheticLambda3.f$3 = i2;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(externalSyntheticLambda3);
    }

    public final void setStowOption(String str, int i, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(i2, "NotificationCenterHelper-->", "SystemUi--Notification", ConstraintSet$.ExternalSyntheticOutline0.m(i, "setStowOption:: pkg = ", str, ", uid = ", ", option = "));
        }
        NotificationCenterConnect notificationCenterConnect = this.notificationCenterConnect;
        NotificationCenterHelper$$ExternalSyntheticLambda2 notificationCenterHelper$$ExternalSyntheticLambda2 = new NotificationCenterHelper$$ExternalSyntheticLambda2();
        notificationCenterHelper$$ExternalSyntheticLambda2.f$0 = str;
        notificationCenterHelper$$ExternalSyntheticLambda2.f$1 = i;
        notificationCenterHelper$$ExternalSyntheticLambda2.f$2 = i2;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(notificationCenterHelper$$ExternalSyntheticLambda2);
    }

    public final void switchNotificationChannel(String str, int i, String str2, String str3, boolean z, int i2) {
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("switchNotificationChannel:: pkg = ", str, ", channelID = ", str2, ", manualType = ");
        DeviceSettingItem$.ExternalSyntheticOutline0.m(str3, ", enabled1 = ", ", reason:", sbM, z);
        sbM.append(i2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        NotificationCenterConnect notificationCenterConnect = this.notificationCenterConnect;
        NotificationCenterHelper$$ExternalSyntheticLambda4 notificationCenterHelper$$ExternalSyntheticLambda4 = new NotificationCenterHelper$$ExternalSyntheticLambda4();
        notificationCenterHelper$$ExternalSyntheticLambda4.f$0 = str;
        notificationCenterHelper$$ExternalSyntheticLambda4.f$1 = i;
        notificationCenterHelper$$ExternalSyntheticLambda4.f$2 = str2;
        notificationCenterHelper$$ExternalSyntheticLambda4.f$3 = str3;
        notificationCenterHelper$$ExternalSyntheticLambda4.f$4 = z;
        notificationCenterHelper$$ExternalSyntheticLambda4.f$5 = i2;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(notificationCenterHelper$$ExternalSyntheticLambda4);
    }

    public final void updateShowNotificationSnooze() {
        Context context = this.mContext;
        context.getClass();
        this.isShowNotificationSnooze = Settings.Secure.getIntForUser(context.getContentResolver(), DELAY_SWITCH_STATE, 0, -2) == 1;
    }
}
