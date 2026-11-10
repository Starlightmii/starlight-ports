package com.oplus.systemui.statusbar.notification.helper;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.telephony.OplusTelephonyManager;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.fragment.app.Fragment$5$;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.slice.widget.RowView$;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.oplus.confinemode.OplusConfineModeManager;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.notification.envelope.EnvelopeHelper;
import com.oplus.systemui.notification.interruption.gamespace.QSNoDisturbObserver;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.qs.utils.SimStateUtil;
import com.oplus.systemui.statusbar.controller.NoDisturbController;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.observer.GameBarrageObserver;
import com.oplus.systemui.statusbar.observer.GameModeObserver;
import com.oplus.systemui.statusbar.observer.GameSpaceObserver;
import com.oplus.systemui.statusbar.observer.InCallUIFloatingWindowObserver;
import com.oplus.telephony.CarrierLockInfo;
import com.oplus.telephony.DeviceLockInfo;
import com.oplus.telephony.ImsiLockInfo;
import com.oplus.telephony.RadioManager;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class HeadsUpHelper {
    public final AnonymousClass2 mConfigurationListener;
    public final Context mContext;
    PerDisplayRepository mDisplaySubComponentRepository;
    EnvelopeHelper mEnvelopeHelper;
    public boolean mEnvelopeShow;
    public final GameBarrageObserver mGameBarrageObserver;
    public final GameModeObserver mGameModeObserver;
    public final GameSpaceObserver mGameSpaceObserver;
    public final String[] mGameSpaceWhitelist;
    public final InCallUIFloatingWindowObserver mInCallUIFloatingWindowObserver;
    public final AnonymousClass1 mMultiUserListener;
    NotificationCenterHelper mNotificationCenterHelper;
    Lazy mNotificationDependOnKeyguard;
    public int mOrientation;
    public final QSNoDisturbObserver mQSNoDisturbObserver;
    ShadeDisplaysRepository mShadeDisplaysRepository;
    SmallAppHelper mSmallAppHelper;
    public String[] mSupportDanmakuPkg;
    public String mThirdPartTelAppPkg;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IMultiUserListener {
        public /* synthetic */ HeadsUpHelper this$0;

        @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
        public final void onSwitched(int i) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "on user switched update danmake value");
            this.this$0.mGameBarrageObserver.onChange(false, null);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ HeadsUpHelper this$0;

        public final void onConfigChanged(Configuration configuration) {
            this.this$0.mOrientation = configuration.orientation;
        }
    }

    public HeadsUpHelper(Context context, GameSpaceObserver gameSpaceObserver, QSNoDisturbObserver qSNoDisturbObserver, GameBarrageObserver gameBarrageObserver, InCallUIFloatingWindowObserver inCallUIFloatingWindowObserver, ConfigurationController configurationController) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mEnvelopeShow = false;
        new HashMap();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mContext = context;
        this.mGameSpaceWhitelist = context.getResources().getStringArray(2130903212);
        this.mGameSpaceObserver = gameSpaceObserver;
        this.mQSNoDisturbObserver = qSNoDisturbObserver;
        gameSpaceObserver.getClass();
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor("disturb_for_game_space_mode_flag"), gameSpaceObserver, -1);
        gameSpaceObserver.isInGameSpaceDisturb = gameSpaceObserver.inGameSpaceDisturb();
        qSNoDisturbObserver.getClass();
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(NoDisturbController.SETTING_KEY_NO_DISTURB), qSNoDisturbObserver, -1);
        qSNoDisturbObserver.isInGameSpaceDisturb = qSNoDisturbObserver.isBannerBlockedByQS();
        GameModeObserver gameModeObserver = new GameModeObserver();
        this.mGameModeObserver = gameModeObserver;
        gameModeObserver.register(context, false);
        this.mInCallUIFloatingWindowObserver = inCallUIFloatingWindowObserver;
        inCallUIFloatingWindowObserver.observe();
        Settings.Secure.getIntForUser(context.getContentResolver(), NoDisturbController.SETTING_KEY_NO_DISTURB, -1, -2);
        this.mGameBarrageObserver = gameBarrageObserver;
        gameBarrageObserver.observe();
        MultiUserManager.getInstance().addListener(anonymousClass1);
        this.mOrientation = context.getResources().getConfiguration().orientation;
        configurationController.addCallback(anonymousClass2);
    }

    public boolean getEnvelopeShow() {
        return this.mEnvelopeShow;
    }

    public final int getImportance(NotificationEntry notificationEntry) {
        StatusBarNotification sbn = notificationEntry.getSbn();
        int importance = notificationEntry.getImportance();
        int i = 3;
        if (SmallAppHelper.Companion.isSmallApp(sbn)) {
            StringBuilder sb = new StringBuilder("shouldHeadsUp:unimportant notification: ");
            sb.append(notificationEntry.getEx().getEncryptionKey());
            sb.append(",smallAppImportance: ");
            sb.append(this.mSmallAppHelper.getImportanceOfSmallApp(sbn, 3));
            sb.append(",sbn-importance:");
            sb.append(notificationEntry.getImportance());
            sb.append(",priority:");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(sbn.getNotification().priority, LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", sb);
            importance = this.mSmallAppHelper.getImportanceOfSmallApp(sbn, importance);
        }
        if (importance == 0 || !(sbn.getNotification().getChannelId() == null || "miscellaneous".equals(sbn.getNotification().getChannelId()))) {
            if (LogUtil.getNormalDebug()) {
                StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(importance, "Notification--getImportance oriImportance = ", ",vibrate:");
                sbM.append(Arrays.toString(sbn.getNotification().vibrate));
                sbM.append(",sound:");
                sbM.append(sbn.getNotification().sound);
                Log.d("HeadsUpHelper", sbM.toString());
            }
            return importance;
        }
        boolean z = (sbn.getNotification().vibrate == null && sbn.getNotification().sound == null && (sbn.getNotification().defaults & 2) == 0 && (sbn.getNotification().defaults & 1) == 0) ? false : true;
        boolean z2 = sbn.getNotification().priority >= 1;
        boolean z3 = "com.tencent.mm".equals(sbn.getPackageName()) && !sbn.isOngoing() && sbn.getNotification().priority >= 0;
        if ((z && z2) || z3) {
            i = 4;
        } else if (!z2 && !z && importance > 2) {
            i = 2;
        } else if ((!z2 || z || importance <= 3) && (z2 || importance <= 3)) {
            i = importance;
        }
        int i2 = sbn.getNotification().fullScreenIntent == null ? i : 4;
        if (LogUtil.getNormalDebug()) {
            StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m("Notification--getImportance oriImportance = ", i2, ",isNoisy:", ",isHigh:", z);
            sbM2.append(z2);
            sbM2.append(",fullIntent:");
            ChildHelper$.ExternalSyntheticOutline0.m("HeadsUpHelper", sbM2, sbn.getNotification().fullScreenIntent != null);
        }
        return i2;
    }

    public String getThirdPartTelAppPkg() {
        return this.mThirdPartTelAppPkg;
    }

    public boolean getTopIsFullscreen() {
        StatusBarWindowState statusBarWindowState = StatusBarWindowState.Showing;
        StatusBarWindowState statusBarWindowState2 = (StatusBarWindowState) ((ReferenceSysUIDisplaySubcomponent) this.mDisplaySubComponentRepository.getOrDefault(((Integer) this.mShadeDisplaysRepository.getDisplayId().getValue()).intValue())).getStatusBarWindowStateRepository().getWindowState().getValue();
        boolean z = statusBarWindowState2 == StatusBarWindowState.Hidden || statusBarWindowState2 == StatusBarWindowState.Hiding;
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "getTopIsFullscreen = " + z + ", statusBarWindowState = " + statusBarWindowState2);
        return z;
    }

    public final boolean isThirdPartInCallNotification(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null || statusBarNotification.getNotification().fullScreenIntent == null || !"call".equals(statusBarNotification.getNotification().category) || "com.android.incallui".equals(statusBarNotification.getPackageName()) || "com.google.android.dialer".equals(statusBarNotification.getPackageName())) {
            return false;
        }
        this.mThirdPartTelAppPkg = statusBarNotification.getPackageName();
        return true;
    }

    public void setEnvelopeShow(boolean z) {
        this.mEnvelopeShow = z;
    }

    public final boolean shouldPeekByOS(StatusBarNotification statusBarNotification) {
        ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).getClass();
        LockDeadUtil.INSTANCE.getClass();
        if (!LockDeadUtil.isLockDeadState) {
            Uri uri = Util.ROM_UPDATE_CONTENT_URI;
            if (ChildrenModeObserver.getInstance().mIsChildrenMode) {
                if (LogUtil.getNormalDebug()) {
                    Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--isChildrenMode");
                    return false;
                }
            } else if (!suppress(statusBarNotification)) {
                ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).isKeyguardShowing();
                if (this.mInCallUIFloatingWindowObserver.getShowing()) {
                    if (LogUtil.getNormalDebug()) {
                        Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--suppressForCall");
                        return false;
                    }
                } else if (this.mEnvelopeShow) {
                    if (LogUtil.getNormalDebug()) {
                        Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--envelopeShow");
                        return false;
                    }
                } else {
                    if (!suppressForThirdPartInCall(statusBarNotification)) {
                        return true;
                    }
                    if (LogUtil.getNormalDebug()) {
                        Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--suppressForThirdPartInCall");
                    }
                }
            } else if (LogUtil.getNormalDebug()) {
                Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--suppress");
                return false;
            }
        } else if (LogUtil.getNormalDebug()) {
            Log.d("HeadsUpHelper", "Notification--No peeking: suppress by os policy--lockDeadState");
            return false;
        }
        return false;
    }

    public final boolean suppress(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        String string = statusBarNotification.getNotification().extras.getString("small_app_package", "");
        if (SmallAppHelper.isSmallApp(statusBarNotification) && this.mSmallAppHelper.suppressForSmallApp(string)) {
            if (LogUtil.getNormalDebug()) {
                Fragment$5$.ExternalSyntheticOutline0.m("Notification--suppress-smallApp ,smallAppPkg:", string, "HeadsUpHelper");
                return true;
            }
        } else {
            if (!((SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper()).getSmartDriverBannerBlocked(packageName)) {
                return false;
            }
            if (LogUtil.getNormalDebug()) {
                Log.d("HeadsUpHelper", "Notification--suppressForSmartDrive");
            }
        }
        return true;
    }

    public final boolean suppressForGameSpaceOrSA(String str, StatusBarNotification statusBarNotification) {
        boolean z;
        String string;
        if (!Arrays.asList(this.mGameSpaceWhitelist).contains(str)) {
            boolean z2 = this.mGameModeObserver.isInGameMode;
            QSNoDisturbObserver qSNoDisturbObserver = this.mQSNoDisturbObserver;
            if (z2) {
                boolean zIsThirdPartInCallNotification = isThirdPartInCallNotification(statusBarNotification);
                boolean z3 = OplusConfineModeManager.getInstance().getConfineMode() == 8;
                boolean z4 = !zIsThirdPartInCallNotification || z3;
                z = this.mGameSpaceObserver.isInGameSpaceDisturb && z4;
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m(" keyword : isThirdPartyCall = ", " , isGameFocusModeOn = ", " , shouldSuppressThirdPartCall = ", zIsThirdPartInCallNotification, z3);
                sbM.append(z4);
                string = sbM.toString();
            } else {
                z = qSNoDisturbObserver.isInGameSpaceDisturb;
                string = "";
            }
            if (z) {
                if (statusBarNotification != null) {
                    statusBarNotification.getKey();
                }
                StringBuilder sbM2 = RowView$.ExternalSyntheticOutline0.m("Notification--suppressForGameSpaceOrSA isSuppress:true isGameMode:", " mIsBlockBannerByQS:", z2);
                sbM2.append(qSNoDisturbObserver.isInGameSpaceDisturb);
                sbM2.append(string);
                LogUtil.i(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", sbM2.toString());
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:94:0x0186  */
    public final boolean suppressForThirdPartInCall(StatusBarNotification statusBarNotification) {
        boolean z;
        RadioManager radioManagerCreateForSlotId;
        ImsiLockInfo imsiLockInfo;
        DeviceLockInfo deviceLockInfo;
        int i;
        int operator;
        Bundle bundle;
        if (statusBarNotification.getNotification().category != null || statusBarNotification.getNotification().fullScreenIntent != null || !statusBarNotification.getPackageName().equals(getThirdPartTelAppPkg()) || !statusBarNotification.getPackageName().equals(AppInfoUtil.getCurrentTopAppPackageName())) {
            if ("com.tencent.mm".equals(statusBarNotification.getPackageName()) && isThirdPartInCallNotification(statusBarNotification)) {
                Notification notification = statusBarNotification.getNotification();
                boolean topIsFullscreen = getTopIsFullscreen();
                if (notification == null || (bundle = notification.extras) == null || !bundle.getBoolean("in_full_screen_mode") || !topIsFullscreen) {
                    return true;
                }
            }
            Uri uri = Util.ROM_UPDATE_CONTENT_URI;
            if (!ChildrenModeObserver.getInstance().mIsChildrenMode || statusBarNotification.getNotification().fullScreenIntent == null || !"call".equals(statusBarNotification.getNotification().category)) {
                if (statusBarNotification.getNotification().fullScreenIntent != null && "call".equals(statusBarNotification.getNotification().category) && ("com.android.incallui".equals(statusBarNotification.getPackageName()) || "com.google.android.dialer".equals(statusBarNotification.getPackageName()))) {
                    if (this.mContext != null) {
                        SimStateUtil.INSTANCE.getClass();
                        boolean zIsChannelLockSupport = SimStateUtil.isChannelLockSupport();
                        Context context = this.mContext;
                        int i2 = -1;
                        if (zIsChannelLockSupport) {
                            RadioManager radioManagerCreateForSlotId2 = RadioManager.createForSlotId(context, 0);
                            if (radioManagerCreateForSlotId2 != null) {
                                DeviceLockInfo deviceLockInfo2 = radioManagerCreateForSlotId2.getDeviceLockInfo();
                                int lockState = deviceLockInfo2 != null ? deviceLockInfo2.getLockState() : -1;
                                CarrierLockInfo carrierLockInfo = radioManagerCreateForSlotId2.getCarrierLockInfo();
                                operator = carrierLockInfo != null ? carrierLockInfo.getOperator() : -1;
                                i2 = lockState;
                            } else {
                                operator = -1;
                            }
                            i = i2;
                            i2 = operator;
                        } else {
                            OplusTelephonyManager oplusTelephonyManager = OplusTelephonyManager.getInstance(context);
                            Bundle bundleRequestForTelephonyEvent = oplusTelephonyManager.requestForTelephonyEvent(0, 6028, (Bundle) null);
                            Bundle bundleRequestForTelephonyEvent2 = oplusTelephonyManager.requestForTelephonyEvent(0, 6008, (Bundle) null);
                            if (bundleRequestForTelephonyEvent == null || bundleRequestForTelephonyEvent2 == null) {
                                i = -1;
                            } else {
                                try {
                                    i = bundleRequestForTelephonyEvent.getInt("result", -1);
                                    try {
                                        i2 = bundleRequestForTelephonyEvent2.getInt("result", -1);
                                    } catch (Exception e) {
                                        e = e;
                                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "get result exception: " + e.getMessage());
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                    i = -1;
                                }
                            }
                        }
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "operatorResult: ", " lockDeviceResult = "));
                        if (i2 <= 0 || i <= 0) {
                        }
                    }
                    OplusTelephonyManager oplusTelephonyManager2 = OplusTelephonyManager.getInstance(this.mContext);
                    try {
                        SimStateUtil.INSTANCE.getClass();
                        if (SimStateUtil.isChannelLockSupport()) {
                            RadioManager radioManagerCreateForSlotId3 = RadioManager.createForSlotId(this.mContext, 0);
                            if (radioManagerCreateForSlotId3 == null || (deviceLockInfo = radioManagerCreateForSlotId3.getDeviceLockInfo()) == null || deviceLockInfo.getLockState() != 1) {
                                z = false;
                            } else {
                                z = true;
                            }
                        } else {
                            int[] simlockOfflineLock = oplusTelephonyManager2.getSimlockOfflineLock(0);
                            if (simlockOfflineLock == null || simlockOfflineLock.length < 2 || simlockOfflineLock[1] != 1) {
                                z = false;
                            } else {
                                z = true;
                            }
                        }
                        LogUtil.i(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "deviceLocked = " + z);
                    } catch (Exception e3) {
                        LogUtil.i(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "isOfflineOrPrePaidDeviceLocked: " + e3.getMessage());
                        z = false;
                    } catch (NoSuchMethodError unused) {
                        LogUtil.i(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "not support");
                        z = false;
                    }
                    if (!z) {
                        try {
                            SimStateUtil.INSTANCE.getClass();
                            if (!SimStateUtil.isChannelLockSupport() || (radioManagerCreateForSlotId = RadioManager.createForSlotId(this.mContext, 0)) == null || (imsiLockInfo = radioManagerCreateForSlotId.getImsiLockInfo()) == null || imsiLockInfo.getLockState() != 2) {
                            }
                        } catch (Exception e4) {
                            LogUtil.i(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "isImsiDeviceLocked: " + e4.getMessage());
                        }
                    }
                }
                return false;
            }
            if (LogUtil.getNormalDebug()) {
                Log.d("HeadsUpHelper", "Notification--childrenMode--incall, apkPackage: " + statusBarNotification.getPackageName());
                return true;
            }
        }
        return true;
    }
}
