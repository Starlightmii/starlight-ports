package com.oplus.systemui.notification.headsup;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import androidx.collection.FloatList$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManagerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpManagerExImpl extends OplusHeadsUpManagerEx implements OplusNotificationEx, CoreStartable {
    private static final String BANNER_ACTIVITY_ACTION = "com.oplus.notificationmanager.action.ON_BANNER_SELECTION";
    public static final Companion Companion = new Companion();
    private static final String HEADS_UP_SNOOZE_TIME = "snooze_time_ms";
    private static final String HIGHLIGHT_KEY = ":settings:fragment_args_key";
    private static final String SHOULD_SHOW_SNOONE_PROMPT = "should_show_snooze_prompt";
    private static final String SNOOZE_SETTING_ID = "key_banner_suppress_policy";
    private static final String TAG = "OplusHeadsUpManagerExImpl";
    private final Lazy activityStarter;
    private Context context;
    private final Handler handler;
    private final Lazy headsUpManager;
    private final NotificationLockscreenUserManager lockscreenUserManager;
    public int needSoonzePrompt;
    private final Lazy screenLifecycle;
    private final ContentObserver settingsObserver;
    private final Lazy snackbarWindow;
    public int snoozeLengthMs;
    private final OplusHeadsUpManagerExImpl$userChangedListener$1 userChangedListener;
    public int user = -2;
    public final Uri suppressBannerModeUri = Settings.System.getUriFor(HEADS_UP_SNOOZE_TIME);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpManagerExImpl$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public /* synthetic */ OplusHeadsUpManagerExImpl this$0;

        public final void onKeyguardShowingChanged() {
            boolean z = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing;
            OplusHeadsUpManagerExImpl oplusHeadsUpManagerExImpl = this.this$0;
            if (oplusHeadsUpManagerExImpl.getSnoozeLengthMs() == Integer.MAX_VALUE && z) {
                ((HeadsUpManagerImpl) oplusHeadsUpManagerExImpl.headsUpManager.get()).cleanSnoozePkgs("screen off");
            }
        }
    }

    public OplusHeadsUpManagerExImpl(Context context, final Handler handler, NotificationLockscreenUserManager notificationLockscreenUserManager, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.context = context;
        this.handler = handler;
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.headsUpManager = lazy;
        this.snackbarWindow = lazy2;
        this.screenLifecycle = lazy3;
        this.activityStarter = lazy4;
        this.needSoonzePrompt = Settings.System.getIntForUser(this.context.getContentResolver(), SHOULD_SHOW_SNOONE_PROMPT, 1, -2);
        OplusHeadsUpManagerExImpl$userChangedListener$1 oplusHeadsUpManagerExImpl$userChangedListener$1 = new OplusHeadsUpManagerExImpl$userChangedListener$1();
        oplusHeadsUpManagerExImpl$userChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userChangedListener = oplusHeadsUpManagerExImpl$userChangedListener$1;
        this.settingsObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.headsup.OplusHeadsUpManagerExImpl$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.this$0.loadSnoozeMode("snooze mode changed");
                OplusHeadsUpManagerExImpl oplusHeadsUpManagerExImpl = this.this$0;
                if (oplusHeadsUpManagerExImpl.needSoonzePrompt != 0) {
                    oplusHeadsUpManagerExImpl.disableSnoozePrompt();
                }
                ((HeadsUpManagerImpl) this.this$0.headsUpManager.get()).cleanSnoozePkgs("settings changed");
            }
        };
    }

    public static final void access$jumpToSnoozeSetting(OplusHeadsUpManagerExImpl oplusHeadsUpManagerExImpl) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "jumpToSnoozeSetting", null, 8);
        Intent intent = new Intent(BANNER_ACTIVITY_ACTION);
        new Bundle().putString(HIGHLIGHT_KEY, SNOOZE_SETTING_ID);
        intent.putExtra(HIGHLIGHT_KEY, SNOOZE_SETTING_ID);
        ((ActivityStarter) oplusHeadsUpManagerExImpl.activityStarter.get()).postStartActivityDismissingKeyguard(intent, 0);
    }

    public final void disableSnoozePrompt() {
        this.needSoonzePrompt = 0;
        Settings.System.putIntForUser(this.context.getContentResolver(), SHOULD_SHOW_SNOONE_PROMPT, 0, this.user);
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUpManagerEx
    public int getSnoozeLengthMs() {
        return this.snoozeLengthMs;
    }

    public final void loadSnoozeMode(String str) {
        int intForUser = Settings.System.getIntForUser(this.context.getContentResolver(), HEADS_UP_SNOOZE_TIME, 0, this.user);
        if (intForUser == -1) {
            intForUser = Integer.MAX_VALUE;
        }
        setSnoozeLengthMs(intForUser);
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("loadSnoozeMode: time:", getSnoozeLengthMs(), this.user, ", user:", ", reason:");
        sbM.append(str);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUpManagerEx
    public void onHeadsUpSnoozed(StatusBarNotification statusBarNotification) {
        if (this.needSoonzePrompt == 1) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "prompt!", null, 8);
            Handler handler = this.handler;
            OplusHeadsUpManagerExImpl$promptIfFirstSnooze$1 oplusHeadsUpManagerExImpl$promptIfFirstSnooze$1 = new OplusHeadsUpManagerExImpl$promptIfFirstSnooze$1();
            oplusHeadsUpManagerExImpl$promptIfFirstSnooze$1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(oplusHeadsUpManagerExImpl$promptIfFirstSnooze$1);
            disableSnoozePrompt();
        }
        int snoozeLengthMs = getSnoozeLengthMs() == Integer.MAX_VALUE ? -1 : getSnoozeLengthMs();
        Context context = this.context;
        int i = this.user;
        String packageName = statusBarNotification.getPackageName();
        int uid = statusBarNotification.getUid();
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", Fragment$5$.ExternalSyntheticOutline0.m(KeyguardLogger$.ExternalSyntheticOutline0.m(i, "onHeadsUpSnooze : userid:", ", pkg:", packageName, "("), uid, snoozeLengthMs, "), time:"));
        HashMap map = new HashMap();
        map.put("user_id", String.valueOf(i));
        map.put("pkg", packageName);
        map.put("uid", String.valueOf(uid));
        map.put("policy", String.valueOf(snoozeLengthMs));
        OplusTrackProxyUtil.onCommon(context, "20082", "oplus_heads_up_notification_snooze", map);
    }

    public void setSnoozeLengthMs(int i) {
        this.snoozeLengthMs = i;
    }

    public final void start() {
        this.lockscreenUserManager.addUserChangedListener(this.userChangedListener);
        loadSnoozeMode("init");
        this.context.getContentResolver().registerContentObserver(this.suppressBannerModeUri, false, this.settingsObserver, this.user);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(anonymousClass1);
    }
}
