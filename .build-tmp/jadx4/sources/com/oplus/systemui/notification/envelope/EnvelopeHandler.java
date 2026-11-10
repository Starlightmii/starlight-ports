package com.oplus.systemui.notification.envelope;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import android.os.UserHandle;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.helper.StudyCenterHelper;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.notification.helper.SmartDriverHelper;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EnvelopeHandler {
    Lazy mEnvelopeHelper;
    Lazy mHeadsUpHelper;
    Lazy mPrivacyHelper;

    /* JADX WARN: Code duplicated, block: B:12:0x0052  */
    /* JADX WARN: Code duplicated, block: B:14:0x005a  */
    /* JADX WARN: Code duplicated, block: B:15:0x0062  */
    /* JADX WARN: Code duplicated, block: B:17:0x0076  */
    /* JADX WARN: Code duplicated, block: B:18:0x007d  */
    /* JADX WARN: Code duplicated, block: B:20:0x0083  */
    /* JADX WARN: Code duplicated, block: B:21:0x008a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0098  */
    /* JADX WARN: Code duplicated, block: B:24:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:27:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:29:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:38:0x0123  */
    /* JADX WARN: Code duplicated, block: B:39:0x0136  */
    /* JADX WARN: Code duplicated, block: B:41:0x014b  */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    public final void handleEnvelopeNotification(Context context, int i, int i2, Notification notification, int i3, int i4, String str, int i5, boolean z, EnvelopeHelper.AnonymousClass5 anonymousClass5, int i6) {
        boolean z2;
        boolean zNeedEncrypted;
        int identifier;
        SmartDriverHelper smartDriverHelper;
        PowerManager powerManager;
        KeyguardManager keyguardManager;
        if (i3 != 1) {
            if (i3 == 2) {
                zNeedEncrypted = ((PrivacyHelper) this.mPrivacyHelper.get()).needEncrypted("com.tencent.mobileqq", i4);
            } else {
                z2 = false;
            }
            sendEnvelopeNotification(context, i, i2, notification, i4, str, z);
            if (i5 == 1) {
                if ((i6 & 16) != 0) {
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i6, "shouldSuppressByDnD suppressedEffects = ", LogUtil.TAG_NOTIFICATION, "Envelope");
                } else {
                    smartDriverHelper = (SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper();
                    if (smartDriverHelper.getSmartDriverBannerBlocked("com.tencent.mm")) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because driver mode");
                    } else if (smartDriverHelper.isNavigationMode()) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because navigation mode");
                    } else if (((HeadsUpHelper) this.mHeadsUpHelper.get()).mGameModeObserver.isInGameMode) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because game mode");
                        EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "game_mode", (EnvelopeHelper) this.mEnvelopeHelper.get());
                    } else if (ChildrenModeObserver.getInstance().mIsChildrenMode) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--enqueueEnvelopeNotification:close envelope feature because current state is children mode");
                        EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "children_mode", (EnvelopeHelper) this.mEnvelopeHelper.get());
                    } else {
                        Uri uri = Util.ROM_UPDATE_CONTENT_URI;
                        if (StudyCenterHelper.Companion.getInstance().isStudyCenterMode) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--enqueueEnvelopeNotification:close envelope feature because current state is study center mode");
                            EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, CommonSettingsValueProxy.KEY_STUDY_CENTER_MODE, (EnvelopeHelper) this.mEnvelopeHelper.get());
                        } else {
                            powerManager = (PowerManager) context.getSystemService("power");
                            keyguardManager = (KeyguardManager) context.getSystemService(SeparateQSStatisicUtil.VALUE_KEYGUARD);
                            if (powerManager.isInteractive()) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because power off");
                                EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "power_off", (EnvelopeHelper) this.mEnvelopeHelper.get());
                            } else if (!keyguardManager.inKeyguardRestrictedInputMode() || notification == 0) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because keyguard_show");
                                EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "keyguard_show", (EnvelopeHelper) this.mEnvelopeHelper.get());
                            } else {
                                try {
                                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--openNotification: auto open envelope notification");
                                    notification.contentIntent.send();
                                    EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 1, (String) null, (EnvelopeHelper) this.mEnvelopeHelper.get());
                                } catch (PendingIntent.CanceledException unused) {
                                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open notification");
                                }
                            }
                        }
                    }
                }
            }
            if (anonymousClass5 != null) {
                if (i5 == 1 || ((HeadsUpHelper) this.mHeadsUpHelper.get()).mGameModeObserver.isInGameMode || ((SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper()).getSmartDriverBannerBlocked("com.tencent.mm")) {
                    identifier = UserHandle.getUserHandleForUid(i4).getIdentifier();
                    if (KeyguardUpdateMonitor.getCurrentUser() != identifier || (OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(identifier)) && KeyguardUpdateMonitor.getCurrentUser() == 0)) {
                        anonymousClass5.this$0.mEnvelopeViewManager.onReceiveEnvelope(notification, i3, z, z2);
                    }
                    return;
                }
                return;
            }
        }
        zNeedEncrypted = ((PrivacyHelper) this.mPrivacyHelper.get()).needEncrypted("com.tencent.mm", i4);
        z2 = zNeedEncrypted;
        sendEnvelopeNotification(context, i, i2, notification, i4, str, z);
        if (i5 == 1) {
            if ((i6 & 16) != 0) {
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i6, "shouldSuppressByDnD suppressedEffects = ", LogUtil.TAG_NOTIFICATION, "Envelope");
            } else {
                smartDriverHelper = (SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper();
                if (smartDriverHelper.getSmartDriverBannerBlocked("com.tencent.mm")) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because driver mode");
                } else if (smartDriverHelper.isNavigationMode()) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because navigation mode");
                } else if (((HeadsUpHelper) this.mHeadsUpHelper.get()).mGameModeObserver.isInGameMode) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because game mode");
                    EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "game_mode", (EnvelopeHelper) this.mEnvelopeHelper.get());
                } else if (ChildrenModeObserver.getInstance().mIsChildrenMode) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--enqueueEnvelopeNotification:close envelope feature because current state is children mode");
                    EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "children_mode", (EnvelopeHelper) this.mEnvelopeHelper.get());
                } else {
                    Uri uri2 = Util.ROM_UPDATE_CONTENT_URI;
                    if (StudyCenterHelper.Companion.getInstance().isStudyCenterMode) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--enqueueEnvelopeNotification:close envelope feature because current state is study center mode");
                        EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, CommonSettingsValueProxy.KEY_STUDY_CENTER_MODE, (EnvelopeHelper) this.mEnvelopeHelper.get());
                    } else {
                        powerManager = (PowerManager) context.getSystemService("power");
                        keyguardManager = (KeyguardManager) context.getSystemService(SeparateQSStatisicUtil.VALUE_KEYGUARD);
                        if (powerManager.isInteractive()) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because power off");
                            EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "power_off", (EnvelopeHelper) this.mEnvelopeHelper.get());
                        } else if (keyguardManager.inKeyguardRestrictedInputMode()) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because keyguard_show");
                            EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "keyguard_show", (EnvelopeHelper) this.mEnvelopeHelper.get());
                        } else {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--Can't open envelope because keyguard_show");
                            EnvelopeStatisticUtil.collectEnvelopeAutoOpen(context, 0, "keyguard_show", (EnvelopeHelper) this.mEnvelopeHelper.get());
                        }
                    }
                }
            }
        }
        if (anonymousClass5 != null) {
            if (i5 == 1) {
            }
            identifier = UserHandle.getUserHandleForUid(i4).getIdentifier();
            if (KeyguardUpdateMonitor.getCurrentUser() != identifier) {
            }
            anonymousClass5.this$0.mEnvelopeViewManager.onReceiveEnvelope(notification, i3, z, z2);
        }
    }

    public void sendEnvelopeNotification(Context context, int i, int i2, Notification notification, int i3, String str, boolean z) {
    }
}
