package com.oplus.systemui.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.statusbar.policy.ZenModeControllerEx;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ZenModeControllerExImpl extends ZenModeControllerEx {
    private static final String BROADCAST_INTENT = "com.oplus.notificationmanager.action.UPDATE_NOTIFICATION_INFO";
    public static final Companion Companion = new Companion();
    private static final String KEY_NO_MEDIA = "no_media";
    private static final String PACKAGE = "pkg";
    private static final String PACKAGE_NAME = "com.android.launcher";
    public static final String TAG = "ZenModeControllerEx";
    private static final String TYPE = "type";
    private static final String TYPE_EXTRA = "zenmode";
    private static final String UID = "uid";
    public static final String ZEN_SUPPRESSED_EFFECT_BADGE = "zen_suppressed_effect_badge";
    public static final String ZEN_SUPPRESSED_EFFECT_CURVED_DISPLAY = "zen_suppressed_effect_curved_dispaly";
    public static final String ZEN_SUPPRESSED_EFFECT_KEY_GARD = "zen_suppressed_effect_key_gard";
    private final UserSettingObserver badgeOnSetting;
    private final Handler bgHandler;
    private final Context context;
    private final UserSettingObserver curvedDisplayCheckedSetting;
    private final Handler handler;
    public long lastBadgeOnChangeTime;
    public long lastCurvedDisplayCheckedChangeTime;
    public long lastLockScreenSelectedChangeTime;
    private final UserSettingObserver lockScreenSelectedSetting;
    private final SecureSettings secureSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.policy.ZenModeControllerExImpl$sendLauncherBroadcast$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Context $context;
        public /* synthetic */ String $pkg;

        @Override // java.lang.Runnable
        public final void run() {
            Intent intent = new Intent(ZenModeControllerExImpl.BROADCAST_INTENT);
            intent.setPackage("com.android.launcher");
            intent.putExtra("type", ZenModeControllerExImpl.TYPE_EXTRA);
            intent.putExtra("pkg", this.$pkg);
            intent.putExtra(ZenModeControllerExImpl.UID, 0);
            this.$context.sendBroadcast(intent);
            Log.d(ZenModeControllerExImpl.TAG, "sent broadcast to launcher due to zen mode changed.");
        }
    }

    public ZenModeControllerExImpl(Context context, final SecureSettings secureSettings, final Handler handler, Handler handler2) {
        this.context = context;
        this.secureSettings = secureSettings;
        this.handler = handler;
        this.bgHandler = handler2;
        final int currentUserId = OsBinderCacheUtils.getCurrentUserId();
        final int i = 2;
        this.lockScreenSelectedSetting = new UserSettingObserver(this, secureSettings, handler, currentUserId, i) { // from class: com.oplus.systemui.statusbar.policy.ZenModeControllerExImpl$badgeOnSetting$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ ZenModeControllerExImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_BADGE, currentUserId, 0);
                this.$r8$classId = i;
                switch (i) {
                    case 1:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_CURVED_DISPLAY, currentUserId, 1);
                        break;
                    case 2:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD, currentUserId, 0);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            public final void handleValueChanged(int i2, boolean z) {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.resetZenModeBadgeOn();
                        break;
                    case 1:
                        this.this$0.resetZenModeCurvedDisplayChecked();
                        break;
                    default:
                        this.this$0.resetZenModeLockScreenSelected();
                        break;
                }
            }
        };
        final int currentUserId2 = OsBinderCacheUtils.getCurrentUserId();
        final int i2 = 1;
        this.curvedDisplayCheckedSetting = new UserSettingObserver(this, secureSettings, handler, currentUserId2, i2) { // from class: com.oplus.systemui.statusbar.policy.ZenModeControllerExImpl$badgeOnSetting$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ ZenModeControllerExImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_BADGE, currentUserId2, 0);
                this.$r8$classId = i2;
                switch (i2) {
                    case 1:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_CURVED_DISPLAY, currentUserId2, 1);
                        break;
                    case 2:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD, currentUserId2, 0);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            public final void handleValueChanged(int i3, boolean z) {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.resetZenModeBadgeOn();
                        break;
                    case 1:
                        this.this$0.resetZenModeCurvedDisplayChecked();
                        break;
                    default:
                        this.this$0.resetZenModeLockScreenSelected();
                        break;
                }
            }
        };
        final int currentUserId3 = OsBinderCacheUtils.getCurrentUserId();
        final int i3 = 0;
        this.badgeOnSetting = new UserSettingObserver(this, secureSettings, handler, currentUserId3, i3) { // from class: com.oplus.systemui.statusbar.policy.ZenModeControllerExImpl$badgeOnSetting$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ ZenModeControllerExImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_BADGE, currentUserId3, 0);
                this.$r8$classId = i3;
                switch (i3) {
                    case 1:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_CURVED_DISPLAY, currentUserId3, 1);
                        break;
                    case 2:
                        this.this$0 = this;
                        super(secureSettings, handler, ZenModeControllerExImpl.ZEN_SUPPRESSED_EFFECT_KEY_GARD, currentUserId3, 0);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            public final void handleValueChanged(int i4, boolean z) {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.resetZenModeBadgeOn();
                        break;
                    case 1:
                        this.this$0.resetZenModeCurvedDisplayChecked();
                        break;
                    default:
                        this.this$0.resetZenModeLockScreenSelected();
                        break;
                }
            }
        };
    }

    private final void setStreamMusic(boolean z) {
        ((AudioManager) this.context.getSystemService("audio")).setStreamMute(3, z);
    }

    public final void register() {
        this.lockScreenSelectedSetting.setListening(true);
        this.curvedDisplayCheckedSetting.setListening(true);
        this.badgeOnSetting.setListening(true);
        resetZenModeLockScreenSelected();
        resetZenModeCurvedDisplayChecked();
        resetZenModeBadgeOn();
    }

    public final void resetZenModeBadgeOn() {
        if (OpUtils.sIsClosedSuperFirewall || this.badgeOnSetting.getValue() == 1) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastBadgeOnChangeTime >= 1000) {
            this.lastBadgeOnChangeTime = jCurrentTimeMillis;
            this.badgeOnSetting.setValue(1);
        } else if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ZenModeControllerEx-->resetZenModeBadgeOn ignore for time gate!");
        }
    }

    public final void resetZenModeCurvedDisplayChecked() {
        if (OpUtils.sIsClosedSuperFirewall || this.curvedDisplayCheckedSetting.getValue() == 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastCurvedDisplayCheckedChangeTime >= 1000) {
            this.lastCurvedDisplayCheckedChangeTime = jCurrentTimeMillis;
            this.curvedDisplayCheckedSetting.setValue(0);
        } else if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ZenModeControllerEx-->resetZenModeCurvedDispalayChecked ignore for time gate!");
        }
    }

    public final void resetZenModeLockScreenSelected() {
        if (OpUtils.sIsClosedSuperFirewall || this.lockScreenSelectedSetting.getValue() == 1) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastLockScreenSelectedChangeTime >= 1000) {
            this.lastLockScreenSelectedChangeTime = jCurrentTimeMillis;
            this.lockScreenSelectedSetting.setValue(1);
        } else if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ZenModeControllerEx-->resetZenModeLockScreenSelected ignore for time gate!");
        }
    }

    public void sendLauncherBroadcast(Context context, String str) {
        Handler handler = this.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$pkg = str;
        anonymousClass1.$context = context;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void updateZenModeMedia() {
        int i = Settings.Global.getInt(this.context.getContentResolver(), OplusNoDisturbListener.ZEN_MODE_SETTING, 0);
        int intForUser = Settings.Secure.getIntForUser(this.context.getContentResolver(), KEY_NO_MEDIA, 0, OsBinderCacheUtils.getCurrentUserId());
        AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
        if (i == 1 && intForUser == 1) {
            setStreamMusic(true);
        } else if (i == 0 && audioManager.isStreamMute(3)) {
            setStreamMusic(false);
        }
    }
}
