package com.oplus.systemui.statusbar.phone;

import android.R;
import android.app.AlarmManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.bluetooth.BluetoothLogger;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.phone.OplusPhoneStatusBarPolicyEx;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.phone.worklife.OPWLBHelper;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.common.receiver.HeadsetPlugReceiver;
import com.oplus.systemui.keyguard.telephony.SimStateHelper;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplus.systemui.statusbar.controller.NoDisturbController;
import com.oplus.systemui.statusbar.observer.HighPerformanceObserver;
import com.oplus.systemui.statusbar.observer.PhoneMuteStateCustomObserver;
import com.oplus.systemui.statusbar.observer.StatusBarAlarmStateObserver;
import com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl;
import com.oplus.systemui.volume.repository.RingModeRepository;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhoneStatusBarPolicyExImpl extends OplusPhoneStatusBarPolicyEx implements SecurityController.SecurityControllerCallback, IReceiverListener, OPWLBHelper.IStatusBarIconChange {
    private static final String ACTION_BLUETOOTH_TRANSFER_STATE = "com.android.bluetooth.opp.action.TRANSFER_STATE";
    private static final String ALER_SLIDER_MODE = "alert_slider_mode";
    private static final String CLOCK_PKG_NAME = "com.coloros.alarmclock";
    private static final String EXTRA_STATE_BLUETOOTH = "com.android.bluetooth.opp.extra.STATE";
    private static final String FLAVOR_TWO_CLOCK_PKG_NAME = "com.oneplus.deskclock";
    private static final String KEY_OPLUS_AUDIO_GET_MUTE_PHONE = "OPLUS_AUDIO_GET_MUTE_PHONE";
    private static final String LOGGING_STATUS_KEY = "log_switch_type";
    private static final String TAG = "OplusPhoneStatusBarPolicyEx";
    private final AlarmManager alarmManager;
    private final AudioManager audioManager;
    private final Handler bgHandler;
    public int bluetoothBatteryLevel;
    public boolean bluetoothConnected;
    private final BluetoothController bluetoothController;
    public boolean bluetoothDownloading;
    private BluetoothHeadset bluetoothHeadset;
    private final BluetoothProfile.ServiceListener bluetoothProfileServiceListener;
    public boolean bluetoothUploading;
    private final Context context;
    public boolean currentUserSetup;
    public boolean customVolumeVisible;
    public boolean emergencyOnly;
    private final Handler handler;
    private final IReceiverListener headsetPlugReceiver;
    public boolean highPerformanceMode;
    private final OplusPhoneStatusBarPolicyExImpl$highPerformanceObserver$1 highPerformanceObserver;
    private final LogBuffer logBuffer;
    private final BluetoothLogger mLogger;
    private final AtomicInteger mNfcstate;
    private final PhoneMuteStateCustomObserver phoneMuteStateCustomObserver;
    private final Resources resources;
    private final SecurityController securityController;
    private final String slotAlarmClock;
    private final String slotBluetooth;
    private final String slotCamera;
    private final String slotConnectedDisplay;
    private final String slotHeadset;
    private final String slotHighPerformance;
    private final String slotHotspot;
    private final String slotLocation;
    private final String slotLogging;
    private final String slotMicrophone;
    private final String slotNfc;
    private final String slotSOS;
    private final String slotSettingKeyBlockBanner;
    private final String slotVPN;
    private final String slotVolume;
    private String slotWLB;
    private final String slotZen;
    private final OplusPhoneStatusBarPolicyExImpl$statusBarAlarmStateObserver$1 statusBarAlarmStateObserver;
    private final StatusBarIconController statusBarIconController;
    private final UserTracker userTracker;
    private final ContentObserver vibrateWhenSilentObserver;
    private final Uri vibrateWhenSilentUri;
    public boolean volumeVisible;
    public int zen;
    private final ZenModeController zenController;
    public static final Companion Companion = new Companion();
    private static final int[] BT_BATTERY_BLACK = {2131236716, 2131236716, 2131236716, 2131236717, 2131236718, 2131236719, 2131236720, 2131236721, 2131236722, 2131236723};
    private static final int[] BT_BATTERY_LOW = {2131236724, 2131236725};

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NoDisturbController.Callback {
        public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

        public final void updateSettingblockBannerrIcon(boolean z) {
            OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("updateSettingblockBannerrIcon: blockBannerIconStatus = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", z);
            }
            oplusPhoneStatusBarPolicyExImpl.statusBarIconController.setIconVisibility(oplusPhoneStatusBarPolicyExImpl.slotSettingKeyBlockBanner, z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OpUpdateVolumeZenObject {
        private final String volumeDescription;
        public final int volumeIconId;
        public final boolean volumeVisible;
        private final String zenDescription;
        public final int zenIconId;
        public final boolean zenVisible;

        public OpUpdateVolumeZenObject(boolean z, int i, String str, boolean z2, int i2, String str2) {
            this.zenVisible = z;
            this.zenIconId = i;
            this.zenDescription = str;
            this.volumeVisible = z2;
            this.volumeIconId = i2;
            this.volumeDescription = str2;
        }

        public final String component3() {
            return this.zenDescription;
        }

        public final String component6() {
            return this.volumeDescription;
        }

        public final OpUpdateVolumeZenObject copy$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(boolean z, int i, String str, boolean z2, int i2, String str2) {
            return new OpUpdateVolumeZenObject(z, i, str, z2, i2, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OpUpdateVolumeZenObject)) {
                return false;
            }
            OpUpdateVolumeZenObject opUpdateVolumeZenObject = (OpUpdateVolumeZenObject) obj;
            return this.zenVisible == opUpdateVolumeZenObject.zenVisible && this.zenIconId == opUpdateVolumeZenObject.zenIconId && Intrinsics.areEqual(this.zenDescription, opUpdateVolumeZenObject.zenDescription) && this.volumeVisible == opUpdateVolumeZenObject.volumeVisible && this.volumeIconId == opUpdateVolumeZenObject.volumeIconId && Intrinsics.areEqual(this.volumeDescription, opUpdateVolumeZenObject.volumeDescription);
        }

        public final String getVolumeDescription() {
            return this.volumeDescription;
        }

        public final int getVolumeIconId() {
            return this.volumeIconId;
        }

        public final boolean getVolumeVisible() {
            return this.volumeVisible;
        }

        public final String getZenDescription() {
            return this.zenDescription;
        }

        public final int getZenIconId() {
            return this.zenIconId;
        }

        public final boolean getZenVisible() {
            return this.zenVisible;
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.zenIconId, Boolean.hashCode(this.zenVisible) * 31, 31);
            String str = this.zenDescription;
            int iM2 = MonoSpline$.ExternalSyntheticOutline0.m(this.volumeIconId, ChangeSize$.ExternalSyntheticOutline0.m((iM + (str == null ? 0 : str.hashCode())) * 31, 31, this.volumeVisible), 31);
            String str2 = this.volumeDescription;
            return iM2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            String str = this.zenDescription;
            String str2 = this.volumeDescription;
            StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("OpUpdateVolumeZenObject(zenVisible=", this.zenIconId, ", zenIconId=", ", zenDescription=", this.zenVisible);
            DeviceSettingItem$.ExternalSyntheticOutline0.m(str, ", volumeVisible=", ", volumeIconId=", sbM, this.volumeVisible);
            sbM.append(this.volumeIconId);
            sbM.append(", volumeDescription=");
            sbM.append(str2);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$handleBroadcast$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02951 implements Runnable {
        public /* synthetic */ Object $intent;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$handleBroadcast$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class RunnableC01331 implements Runnable {
            public /* synthetic */ int $level;
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

            public /* synthetic */ RunnableC01331(int i) {
                this.$r8$classId = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (this.$r8$classId) {
                    case 0:
                        OplusPhoneStatusBarPolicyExImpl.access$applyBluetoothBatterySlot(this.this$0, this.$level);
                        break;
                    default:
                        OplusPhoneStatusBarPolicyExImpl.access$applyBluetoothBatterySlot(this.this$0, this.$level);
                        break;
                }
            }
        }

        public /* synthetic */ RunnableC02951(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                    int iAccess$calculateBatteryLevel = OplusPhoneStatusBarPolicyExImpl.access$calculateBatteryLevel(oplusPhoneStatusBarPolicyExImpl, OplusPhoneStatusBarPolicyExImpl.access$computeBtBatteryInBgThread(oplusPhoneStatusBarPolicyExImpl, (Intent) this.$intent));
                    Handler handler = this.this$0.handler;
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl2 = this.this$0;
                    RunnableC01331 runnableC01331 = new RunnableC01331(0);
                    runnableC01331.this$0 = oplusPhoneStatusBarPolicyExImpl2;
                    runnableC01331.$level = iAccess$calculateBatteryLevel;
                    VarHandle.storeStoreFence();
                    handler.post(runnableC01331);
                    break;
                default:
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl3 = this.this$0;
                    BluetoothProfile bluetoothProfile = (BluetoothProfile) this.$intent;
                    oplusPhoneStatusBarPolicyExImpl3.bluetoothHeadset = bluetoothProfile instanceof BluetoothHeadset ? (BluetoothHeadset) bluetoothProfile : null;
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl4 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("bluetoothProfile, onServiceConnected: bluetoothHeadset = " + oplusPhoneStatusBarPolicyExImpl4.bluetoothHeadset));
                    }
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl5 = this.this$0;
                    int iAccess$calculateBatteryLevel2 = OplusPhoneStatusBarPolicyExImpl.access$calculateBatteryLevel(oplusPhoneStatusBarPolicyExImpl5, OplusPhoneStatusBarPolicyExImpl.access$computeBtBatteryInBgThread(oplusPhoneStatusBarPolicyExImpl5, null));
                    Handler handler2 = this.this$0.handler;
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl6 = this.this$0;
                    RunnableC01331 runnableC01332 = new RunnableC01331(1);
                    runnableC01332.this$0 = oplusPhoneStatusBarPolicyExImpl6;
                    runnableC01332.$level = iAccess$calculateBatteryLevel2;
                    VarHandle.storeStoreFence();
                    handler2.post(runnableC01332);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$updateSOS$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02961 implements Runnable {
        public /* synthetic */ boolean $emergencyOnly;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

        public /* synthetic */ RunnableC02961(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    StatusBarFeatureOption.INSTANCE.getClass();
                    boolean z = StatusBarFeatureOption.isSupportSosStatIcon;
                    SimStateHelper.Companion.getInstance(this.this$0.context).getClass();
                    boolean z2 = false;
                    boolean z3 = SimStateHelper.isVoWifiState(0) || SimStateHelper.isVoWifiState(1);
                    boolean z4 = this.$emergencyOnly;
                    if (z4 && z && !z3) {
                        z2 = true;
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", isAnySimInVoWifiState = ", "OplusPhoneStatusBarPolicyEx-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("updateSOS: show = ", ", emergencyOnly = ", ", isAuArea = ", z2, z4), z, z3));
                    }
                    this.this$0.logBuffer.log(OplusPhoneStatusBarPolicyExImpl.TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("updateSOS: show = ", ", emergencyOnly = ", ", isAuArea = ", z2, this.$emergencyOnly), z, ", isAnySimInVoWifiState = ", z3), (Throwable) null);
                    this.this$0.statusBarIconController.setIconVisibility(this.this$0.slotSOS, z2);
                    break;
                case 1:
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                    oplusPhoneStatusBarPolicyExImpl.updateNFCIcon(oplusPhoneStatusBarPolicyExImpl.mNfcstate.get());
                    this.this$0.logBuffer.log(OplusPhoneStatusBarPolicyExImpl.TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("initNFCIcon(), nfcState = ", this.$emergencyOnly, ", mNFCState = ", this.this$0.mNfcstate.get()), (Throwable) null);
                    boolean z5 = this.$emergencyOnly;
                    OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl2 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("initNFCIcon(), nfcState = ", oplusPhoneStatusBarPolicyExImpl2.mNfcstate.get(), ",  mNFCState = ", "OplusPhoneStatusBarPolicyEx-->", z5));
                        break;
                    }
                    break;
                default:
                    OplusPhoneStatusBarPolicyExImpl.access$updateVPN(this.this$0, this.$emergencyOnly);
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$highPerformanceObserver$1, com.oplusos.systemui.common.observer.AbstractObserver] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$statusBarAlarmStateObserver$1, com.oplusos.systemui.common.observer.AbstractObserver] */
    public OplusPhoneStatusBarPolicyExImpl(Context context, Resources resources, final Handler handler, Handler handler2, AlarmManager alarmManager, StatusBarIconController statusBarIconController, AudioManager audioManager, ZenModeController zenModeController, BluetoothController bluetoothController, SecurityController securityController, NoDisturbController noDisturbController, NetworkController networkController, UserTracker userTracker, BluetoothLogger bluetoothLogger, LogBuffer logBuffer) {
        this.context = context;
        this.resources = resources;
        this.handler = handler;
        this.bgHandler = handler2;
        this.alarmManager = alarmManager;
        this.statusBarIconController = statusBarIconController;
        this.audioManager = audioManager;
        this.zenController = zenModeController;
        this.bluetoothController = bluetoothController;
        this.securityController = securityController;
        this.userTracker = userTracker;
        this.mLogger = bluetoothLogger;
        this.logBuffer = logBuffer;
        this.slotAlarmClock = resources.getString(R.string.widget_default_package_name);
        String string = resources.getString(R.string.zen_mode_duration_minutes);
        this.slotVolume = string;
        String string2 = resources.getString(R.string.work_profile_telephony_paused_text);
        this.slotLocation = string2;
        this.slotZen = resources.getString(R.string.zen_mode_feature_name);
        this.slotBluetooth = resources.getString(R.string.wifi_calling_off_summary);
        this.slotHotspot = resources.getString(R.string.work_profile_deleted_details);
        this.slotConnectedDisplay = resources.getString(R.string.work_mode_emergency_call_button);
        this.slotLogging = "logging";
        this.slotSOS = "sos";
        this.slotSettingKeyBlockBanner = "block_banner";
        this.slotMicrophone = "microphone";
        this.slotCamera = "camera";
        this.slotNfc = "nfc";
        this.slotVPN = "vpn";
        this.slotHighPerformance = "high_performance";
        this.slotHeadset = "headset";
        this.slotWLB = "";
        this.bluetoothBatteryLevel = -1;
        this.mNfcstate = new AtomicInteger(-1);
        Uri uriFor = Settings.System.getUriFor(RingModeRepository.VIBRATE_WHEN_SILENT);
        this.vibrateWhenSilentUri = uriFor;
        ?? r5 = new HighPerformanceObserver() { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$highPerformanceObserver$1
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                CommonSettingsValueProxy.Companion companion = CommonSettingsValueProxy.Companion;
                OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                oplusPhoneStatusBarPolicyExImpl.highPerformanceMode = companion.getHighPerformanceModeState(oplusPhoneStatusBarPolicyExImpl.context) == 1;
                oplusPhoneStatusBarPolicyExImpl.updateHighPerformanceIcon();
            }
        };
        this.highPerformanceObserver = r5;
        OplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1 oplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1 = new OplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1();
        oplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.bluetoothProfileServiceListener = oplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1;
        PhoneMuteStateCustomObserver phoneMuteStateCustomObserver = new PhoneMuteStateCustomObserver() { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$phoneMuteStateCustomObserver$1
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                oplusPhoneStatusBarPolicyExImpl.updateCustomVolumeZen(oplusPhoneStatusBarPolicyExImpl.zen);
            }
        };
        this.phoneMuteStateCustomObserver = phoneMuteStateCustomObserver;
        final int i = 2;
        ContentObserver contentObserver = new ContentObserver(this, handler) { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$holidayModeObserver$1
            public final /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i) {
                    case 2:
                        super.onChange(z, uri);
                        if (this.this$0.vibrateWhenSilentUri != null && Intrinsics.areEqual(this.this$0.vibrateWhenSilentUri, uri)) {
                            OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                            oplusPhoneStatusBarPolicyExImpl.updateCustomVolumeZen(oplusPhoneStatusBarPolicyExImpl.zen);
                            break;
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.updateHolidayIcon();
                        break;
                    case 1:
                        this.this$0.updateLogIcon();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        };
        this.vibrateWhenSilentObserver = contentObserver;
        ?? r6 = new StatusBarAlarmStateObserver() { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$statusBarAlarmStateObserver$1
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                oplusPhoneStatusBarPolicyExImpl.updateAlarm(oplusPhoneStatusBarPolicyExImpl.currentUserSetup);
            }
        };
        this.statusBarAlarmStateObserver = r6;
        OplusPhoneStatusBarPolicyExImpl$headsetPlugReceiver$1 oplusPhoneStatusBarPolicyExImpl$headsetPlugReceiver$1 = new OplusPhoneStatusBarPolicyExImpl$headsetPlugReceiver$1();
        oplusPhoneStatusBarPolicyExImpl$headsetPlugReceiver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.headsetPlugReceiver = oplusPhoneStatusBarPolicyExImpl$headsetPlugReceiver$1;
        FlavorOneFeatureOption.INSTANCE.getClass();
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarIconController;
        statusBarIconControllerImpl.setIcon(string, FlavorOneFeatureOption.isFlavorOneDevice() ? 2131236973 : 2131236986, (CharSequence) null);
        final int i2 = 0;
        statusBarIconControllerImpl.setIconVisibility(string, false);
        statusBarIconControllerImpl.setIcon("logging", 2131236979, resources.getString(2131951845));
        statusBarIconControllerImpl.setIcon("vpn", 2131237065, resources.getString(2131951998));
        statusBarIconControllerImpl.setIconVisibility("vpn", false);
        statusBarIconControllerImpl.setIcon("nfc", 2131236982, resources.getString(2131951884));
        statusBarIconControllerImpl.setIconVisibility("nfc", false);
        statusBarIconControllerImpl.setIcon("sos", 2131237061, resources.getString(2131951970));
        statusBarIconControllerImpl.setIconVisibility("sos", false);
        statusBarIconControllerImpl.setIcon("microphone", 2131236981, PrivacyType.TYPE_MICROPHONE.getName(context));
        statusBarIconControllerImpl.setIconVisibility("microphone", false);
        statusBarIconControllerImpl.setIcon("camera", 2131236937, PrivacyType.TYPE_CAMERA.getName(context));
        statusBarIconControllerImpl.setIconVisibility("camera", false);
        statusBarIconControllerImpl.setIcon(string2, 2131236978, resources.getString(2131951843));
        statusBarIconControllerImpl.setIconVisibility(string2, false);
        statusBarIconControllerImpl.setIcon("block_banner", 2131237063, resources.getString(2131956564));
        statusBarIconControllerImpl.setIconVisibility("block_banner", false);
        statusBarIconControllerImpl.setIcon("high_performance", 2131236976, context.getString(2131951819));
        statusBarIconControllerImpl.setIconVisibility("high_performance", false);
        ((SecurityControllerImpl) securityController).addCallback(this);
        OplusPhoneStatusBarPolicyExImpl$emergencyListener$1 oplusPhoneStatusBarPolicyExImpl$emergencyListener$1 = new OplusPhoneStatusBarPolicyExImpl$emergencyListener$1();
        oplusPhoneStatusBarPolicyExImpl$emergencyListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        NetworkControllerImpl networkControllerImpl = (NetworkControllerImpl) networkController;
        final int i3 = 1;
        networkControllerImpl.mCallbackHandler.obtainMessage(6, 1, 0, oplusPhoneStatusBarPolicyExImpl$emergencyListener$1).sendToTarget();
        networkControllerImpl.mCallbackHandler.setEmergencyCallsOnly(networkControllerImpl.isEmergencyOnly());
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(OplusNoDisturbListener.ZEN_MODE_SETTING), new ContentObserver(this, handler) { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$holidayModeObserver$1
            public final /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i2) {
                    case 2:
                        super.onChange(z, uri);
                        if (this.this$0.vibrateWhenSilentUri != null && Intrinsics.areEqual(this.this$0.vibrateWhenSilentUri, uri)) {
                            OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                            oplusPhoneStatusBarPolicyExImpl.updateCustomVolumeZen(oplusPhoneStatusBarPolicyExImpl.zen);
                            break;
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.updateHolidayIcon();
                        break;
                    case 1:
                        this.this$0.updateLogIcon();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        });
        updateHolidayIcon();
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(LOGGING_STATUS_KEY), new ContentObserver(this, handler) { // from class: com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl$holidayModeObserver$1
            public final /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i3) {
                    case 2:
                        super.onChange(z, uri);
                        if (this.this$0.vibrateWhenSilentUri != null && Intrinsics.areEqual(this.this$0.vibrateWhenSilentUri, uri)) {
                            OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                            oplusPhoneStatusBarPolicyExImpl.updateCustomVolumeZen(oplusPhoneStatusBarPolicyExImpl.zen);
                            break;
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i3) {
                    case 0:
                        this.this$0.updateHolidayIcon();
                        break;
                    case 1:
                        this.this$0.updateLogIcon();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        });
        updateLogIcon();
        this.highPerformanceMode = CommonSettingsValueProxy.Companion.getHighPerformanceModeState(context) == 1;
        r5.register(context, 0, true);
        updateHighPerformanceIcon();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        noDisturbController.addCallback(anonymousClass1);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean profileProxy = defaultAdapter != null ? defaultAdapter.getProfileProxy(context, oplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1, 1) : false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("addBluetoothHeadsetListener(), result = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", profileProxy);
        }
        phoneMuteStateCustomObserver.register(context, 0, true);
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            r6.register(context, 0, true);
        }
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.sWlbFeatureSupport) {
            this.slotWLB = resources.getString(2131956650);
        } else {
            Log.d(TAG, "WLB feature not supported");
        }
        VolumeFeatureOption.INSTANCE.getClass();
        if (!VolumeFeatureOption.isSupportsStealthMode() || uriFor == null) {
            return;
        }
        try {
            context.getContentResolver().registerContentObserver(uriFor, false, contentObserver);
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public static final void access$applyBluetoothBatterySlot(OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl, int i) {
        oplusPhoneStatusBarPolicyExImpl.bluetoothBatteryLevel = i;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusPhoneStatusBarPolicyExImpl.bluetoothBatteryLevel, "applyBluetoothBatterySlot() bluetoothBatteryLevel = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar");
        }
        oplusPhoneStatusBarPolicyExImpl.updateBluetooth$1();
    }

    public static final int access$calculateBatteryLevel(OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl, int i) {
        if (i <= -1) {
            return -1;
        }
        if (i < 2) {
            return 0;
        }
        if (i < 10) {
            return 1;
        }
        int iCeil = (int) Math.ceil(((double) i) / 10.0d);
        int[] iArr = BT_BATTERY_BLACK;
        return iCeil >= iArr.length ? iArr.length - 1 : iCeil;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0057  */
    public static final int access$computeBtBatteryInBgThread(OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl, Intent intent) {
        int batteryLevel;
        BluetoothClass bluetoothClass;
        BluetoothDevice activeDevice;
        BluetoothDevice activeDevice2;
        BluetoothHeadset bluetoothHeadset = oplusPhoneStatusBarPolicyExImpl.bluetoothHeadset;
        if (bluetoothHeadset == null || bluetoothHeadset.getActiveDevice() == null) {
            BluetoothDevice bluetoothDevice = intent != null ? (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") : null;
            Integer numValueOf = (bluetoothDevice == null || (bluetoothClass = bluetoothDevice.getBluetoothClass()) == null) ? null : Integer.valueOf(bluetoothClass.getMajorDeviceClass());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("computeBtBatteryInBgThread(), action = " + (intent != null ? intent.getAction() : null) + ", bluetoothDevice.batteryLevel = " + (bluetoothDevice != null ? Integer.valueOf(bluetoothDevice.getBatteryLevel()) : null) + ", majorDeviceClass = " + numValueOf));
            }
            if ((numValueOf == null || numValueOf.intValue() != 512) && bluetoothDevice != null) {
                batteryLevel = bluetoothDevice.getBatteryLevel();
            } else {
                batteryLevel = -1;
            }
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                BluetoothHeadset bluetoothHeadset2 = oplusPhoneStatusBarPolicyExImpl.bluetoothHeadset;
                Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("computeBtBatteryInBgThread(), bluetoothProfile, activeDevice.batteryLevel = " + ((bluetoothHeadset2 == null || (activeDevice2 = bluetoothHeadset2.getActiveDevice()) == null) ? null : Integer.valueOf(activeDevice2.getBatteryLevel()))));
            }
            BluetoothHeadset bluetoothHeadset3 = oplusPhoneStatusBarPolicyExImpl.bluetoothHeadset;
            if (bluetoothHeadset3 == null || (activeDevice = bluetoothHeadset3.getActiveDevice()) == null) {
                batteryLevel = -1;
            } else {
                batteryLevel = activeDevice.getBatteryLevel();
            }
        }
        CachedBluetoothDevice connectedLeAudio = oplusPhoneStatusBarPolicyExImpl.getConnectedLeAudio();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("connectedLeAudio.batteryLevel " + (connectedLeAudio != null ? Integer.valueOf(connectedLeAudio.getBatteryLevel()) : null)));
        }
        return (batteryLevel != -1 || connectedLeAudio == null) ? batteryLevel : connectedLeAudio.getBatteryLevel();
    }

    public static final void access$initNFCIcon(OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl) {
        Handler handler = oplusPhoneStatusBarPolicyExImpl.bgHandler;
        OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 = new OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1(0);
        oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1.this$0 = oplusPhoneStatusBarPolicyExImpl;
        VarHandle.storeStoreFence();
        handler.post(oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1);
    }

    public static final void access$updateVPN(OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl, boolean z) {
        oplusPhoneStatusBarPolicyExImpl.statusBarIconController.setIconVisibility(oplusPhoneStatusBarPolicyExImpl.slotVPN, z);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat(String.format("vpn: %s", Arrays.copyOf(new Object[]{z ? "VISIBLE" : "GONE"}, 1))));
        }
    }

    private final String getBluetoothIconDesc() {
        return this.resources.getString(this.bluetoothConnected ? 2131951725 : 2131951733);
    }

    private final int getBluetoothIconForeDrawableRes() {
        int i;
        if (this.bluetoothConnected && (i = this.bluetoothBatteryLevel - 1) >= 0) {
            int[] iArr = BT_BATTERY_LOW;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return -1;
    }

    private final int getBluetoothIconRes() {
        int i;
        if (this.bluetoothDownloading || this.bluetoothUploading) {
            return 2131236935;
        }
        boolean z = this.bluetoothConnected;
        if (z && (i = this.bluetoothBatteryLevel) >= 0) {
            int[] iArr = BT_BATTERY_BLACK;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return z ? 2131236942 : 2131236940;
    }

    private final CachedBluetoothDevice getConnectedLeAudio() {
        Object next;
        Iterator it = this.bluetoothController.getConnectedDevices().iterator();
        while (it.hasNext()) {
            next = it.next();
            CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) next;
            if (cachedBluetoothDevice.getGroupId() != -1 && cachedBluetoothDevice.isConnectedLeAudioDevice()) {
                return (CachedBluetoothDevice) next;
            }
        }
        next = null;
        return (CachedBluetoothDevice) next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getNfcEnable() {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.nfc")) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->getNfcEnable...nfc disable ");
            }
            return false;
        }
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.context);
        boolean zIsEnabled = defaultAdapter != null ? defaultAdapter.isEnabled() : false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("nfcAdapter = " + defaultAdapter + ", getNfcEnable_nfcState = " + zIsEnabled));
        }
        return zIsEnabled;
    }

    public void addActionAndRegister(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        intentFilter.addAction("android.intent.action.ALARM_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        intentFilter.addAction(ACTION_BLUETOOTH_TRANSFER_STATE);
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.nfc.action.ADAPTER_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter.addAction("android.bluetooth.action.LE_AUDIO_ACTIVE_DEVICE_CHANGED");
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
            intentFilter2.addDataScheme("package");
            this.context.registerReceiver(broadcastReceiver, intentFilter2, 2);
        }
        HeadsetPlugReceiver.Companion companion = HeadsetPlugReceiver.Companion;
        companion.getInstance().register(this.context);
        companion.getInstance().addListener(this.headsetPlugReceiver);
        updateHeadsetPlug();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void handleBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        int i = 0;
        switch (action.hashCode()) {
            case -2061652054:
                if (action.equals(ACTION_BLUETOOTH_TRANSFER_STATE)) {
                    this.mLogger.logStateChange(String.valueOf(intent.getIntExtra(EXTRA_STATE_BLUETOOTH, 0)));
                    int intExtra = intent.getIntExtra(EXTRA_STATE_BLUETOOTH, 0);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intExtra, "updateBluetoothState(), state = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar");
                    }
                    if (intExtra == 1) {
                        this.bluetoothUploading = true;
                    } else if (intExtra == 2) {
                        this.bluetoothUploading = false;
                    } else if (intExtra == 3) {
                        this.bluetoothDownloading = true;
                    } else if (intExtra == 4) {
                        this.bluetoothDownloading = false;
                    }
                    updateBluetooth$1();
                    break;
                }
                break;
            case -1530327060:
                if (!action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                }
                this.mLogger.logDeviceConnectionStateChanged((String) null, String.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)));
                updateBluetooth$1();
                break;
            case -749511570:
                if (!action.equals("android.bluetooth.action.LE_AUDIO_ACTIVE_DEVICE_CHANGED")) {
                }
                this.logBuffer.log(TAG, LogLevel.DEBUG, FileProvider$.ExternalSyntheticOutline0.m("intent.action = ", intent.getAction()), (Throwable) null);
                Handler handler = this.bgHandler;
                RunnableC02951 runnableC02951 = new RunnableC02951(i);
                runnableC02951.this$0 = this;
                runnableC02951.$intent = intent;
                VarHandle.storeStoreFence();
                handler.post(runnableC02951);
                break;
            case 17117692:
                if (!action.equals("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                }
                this.logBuffer.log(TAG, LogLevel.DEBUG, FileProvider$.ExternalSyntheticOutline0.m("intent.action = ", intent.getAction()), (Throwable) null);
                Handler handler2 = this.bgHandler;
                RunnableC02951 runnableC02952 = new RunnableC02951(i);
                runnableC02952.this$0 = this;
                runnableC02952.$intent = intent;
                VarHandle.storeStoreFence();
                handler2.post(runnableC02952);
                break;
            case 267468725:
                if (action.equals("android.intent.action.PACKAGE_DATA_CLEARED")) {
                    FeatureOption.INSTANCE.getClass();
                    if (!FeatureOption.isExpRegion()) {
                        Uri data = intent.getData();
                        String schemeSpecificPart = data != null ? data.getSchemeSpecificPart() : null;
                        if (TextUtils.equals(schemeSpecificPart, CLOCK_PKG_NAME) || TextUtils.equals(schemeSpecificPart, FLAVOR_TWO_CLOCK_PKG_NAME)) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("clear package [", schemeSpecificPart, "] data", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar");
                            }
                            CommonSettingsValueProxy.Companion.setStatusBarAlarmIconState(this.context, 0);
                        }
                    }
                    break;
                }
                break;
            case 579327048:
                if (!action.equals("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED")) {
                }
                this.logBuffer.log(TAG, LogLevel.DEBUG, FileProvider$.ExternalSyntheticOutline0.m("intent.action = ", intent.getAction()), (Throwable) null);
                Handler handler3 = this.bgHandler;
                RunnableC02951 runnableC02953 = new RunnableC02951(i);
                runnableC02953.this$0 = this;
                runnableC02953.$intent = intent;
                VarHandle.storeStoreFence();
                handler3.post(runnableC02953);
                break;
            case 1123270207:
                if (!action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                }
                this.mLogger.logDeviceConnectionStateChanged((String) null, String.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)));
                updateBluetooth$1();
                break;
            case 1943044864:
                if (action.equals("android.nfc.action.ADAPTER_STATE_CHANGED")) {
                    this.logBuffer.log(TAG, LogLevel.DEBUG, ListPopupWindow$.ExternalSyntheticOutline0.m(intent.getIntExtra("android.nfc.extra.ADAPTER_STATE", -1), "ACTION_ADAPTER_STATE_CHANGED, EXTRA_ADAPTER_STATE = "), (Throwable) null);
                    if (this.context.getPackageManager().hasSystemFeature("android.hardware.nfc")) {
                        int intExtra2 = intent.getIntExtra("android.nfc.extra.ADAPTER_STATE", -1);
                        this.mNfcstate.set(intExtra2);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("updateNFCIcon...mNFCState = ", intExtra2, this.mNfcstate.get(), ", mNfcstate.get() = ", "OplusPhoneStatusBarPolicyEx-->"));
                        }
                        updateNFCIcon(this.mNfcstate.get());
                        break;
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->updateNFC...nfc disable ");
                        break;
                    }
                }
                break;
        }
    }

    public final void init() {
        int identifier;
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.sWlbFeatureSupport) {
            OPWLBHelper oPWLBHelper = (OPWLBHelper) DependencyEx.sDependency.getDependency(OPWLBHelper.class);
            oPWLBHelper.checkAndIncludeWLBTile();
            oPWLBHelper.registerStatusBarObserver(this);
            if (this.slotWLB.length() > 0 && (identifier = this.resources.getIdentifier("op_stat_sys_wlb_mode", "drawable", "com.android.systemui")) != 0) {
                this.statusBarIconController.setIcon(this.slotWLB, identifier, (CharSequence) null);
                this.statusBarIconController.setIconVisibility(this.slotWLB, ((OPWLBHelperImpl) oPWLBHelper).getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0) == 1);
            }
        }
        this.statusBarIconController.setIcon(this.slotConnectedDisplay, 2131236939, this.resources.getString(2131955113));
        Handler handler = this.handler;
        OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 = new OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1(2);
        oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1, 500L);
    }

    public final void onHideWLBStatusBarIcon() {
        if (this.slotWLB.length() > 0) {
            this.statusBarIconController.setIconVisibility(this.slotWLB, false);
        }
    }

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public void onReceive(Context context, Intent intent) {
        if ("android.settings.OPLUS_REGION_CHANGED".equals(intent.getAction())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onReceive: REGION_CHANGED, emergencyOnly = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", this.emergencyOnly);
            }
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("onReceive: REGION_CHANGED, emergencyOnly = ", this.emergencyOnly), (Throwable) null);
            updateSOS(this.emergencyOnly);
        }
    }

    public final void onShowWLBStatusBarIcon() {
        if (this.slotWLB.length() > 0) {
            this.statusBarIconController.setIconVisibility(this.slotWLB, true);
        }
    }

    public final void onStateChanged() {
        boolean zIsVpnEnabled = this.securityController.isVpnEnabled();
        Handler handler = this.handler;
        RunnableC02961 runnableC02961 = new RunnableC02961(2);
        runnableC02961.this$0 = this;
        runnableC02961.$emergencyOnly = zIsVpnEnabled;
        VarHandle.storeStoreFence();
        handler.post(runnableC02961);
    }

    public final void updateAlarm(boolean z) {
        this.currentUserSetup = z;
        FeatureOption.INSTANCE.getClass();
        boolean z2 = false;
        if (!FeatureOption.isExpRegion()) {
            z2 = CommonSettingsValueProxy.Companion.getStatusBarAlarmIconState(this.context) == 1;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("update clock icon from clock, show: ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", z2);
            }
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("update clock icon from clock, show: ", z2), (Throwable) null);
            this.statusBarIconController.setIconVisibility(this.slotAlarmClock, z2);
            return;
        }
        AlarmManager.AlarmClockInfo nextAlarmClock = this.alarmManager.getNextAlarmClock(-2);
        boolean z3 = (nextAlarmClock != null ? nextAlarmClock.getTriggerTime() : 0L) > 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateAlarm(), alarmSet = ", ", mCurrentUserSetup = ", "OplusPhoneStatusBarPolicyEx-->", z3, z));
        }
        this.logBuffer.log(TAG, LogLevel.DEBUG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateAlarm(), alarmSet = ", ", mCurrentUserSetup = ", z3, z), (Throwable) null);
        StatusBarIconControllerImpl statusBarIconControllerImpl = this.statusBarIconController;
        String str = this.slotAlarmClock;
        if (z && z3) {
            z2 = true;
        }
        statusBarIconControllerImpl.setIconVisibility(str, z2);
    }

    public final void updateBluetooth$1() {
        BluetoothControllerImpl bluetoothControllerImpl = this.bluetoothController;
        boolean z = bluetoothControllerImpl.mEnabled;
        this.bluetoothConnected = bluetoothControllerImpl.mConnectionState == 2;
        if (!z) {
            this.bluetoothConnected = false;
            this.bluetoothDownloading = false;
            this.bluetoothUploading = false;
            this.bluetoothBatteryLevel = -1;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            BluetoothControllerImpl bluetoothControllerImpl2 = this.bluetoothController;
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateBluetooth(), isBluetoothEnabled = ", ", isBluetoothConnected = ", "OplusPhoneStatusBarPolicyEx-->", bluetoothControllerImpl2.mEnabled, bluetoothControllerImpl2.mConnectionState == 2));
        }
        int bluetoothIconRes = getBluetoothIconRes();
        int bluetoothIconForeDrawableRes = getBluetoothIconForeDrawableRes();
        String bluetoothIconDesc = getBluetoothIconDesc();
        if (z) {
            StatusBarIconControllerImpl statusBarIconControllerImpl = this.statusBarIconController;
            String str = this.slotBluetooth;
            StatusBarIconControllerImpl statusBarIconControllerImpl2 = statusBarIconControllerImpl;
            statusBarIconControllerImpl2.getClass();
            OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusStatusBarIconControllerEx().setIcon(statusBarIconControllerImpl2.mContext, str, bluetoothIconRes, bluetoothIconForeDrawableRes, bluetoothIconDesc, statusBarIconControllerImpl2.mStatusBarIconList);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(this.slotBluetooth, "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", KeyguardLogger$.ExternalSyntheticOutline0.m("updateBluetooth(), resourceId = ", bluetoothIconRes, ", enabled = ", ", slotBluetooth = ", z));
        }
        this.statusBarIconController.setIconVisibility(this.slotBluetooth, z);
    }

    public final void updateCustomVolumeZen(int i) {
        String string;
        String string2;
        String string3;
        String string4;
        boolean z;
        int i2;
        FeatureOption.INSTANCE.getClass();
        int volumeIconId = 2131236985;
        boolean volumeVisible = false;
        if (FeatureOption.isOplusAlertSlider()) {
            int i3 = Settings.Global.getInt(this.context.getContentResolver(), "alert_slider_mode", -1);
            if (i3 == 1) {
                VolumeFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.isSupportsStealthMode() && QsSettingsValueProxy.Companion.getVibrateWhenSilent(this.context, 0) == 1) {
                    string3 = this.resources.getString(2131951948);
                    string4 = string3;
                    z = true;
                    i2 = 2131236986;
                } else {
                    string2 = this.resources.getString(2131951947);
                    string4 = string2;
                    z = true;
                    i2 = 2131236985;
                }
            } else if (i3 != 2) {
                string4 = this.resources.getString(2131955789);
                i2 = 2131236985;
                z = false;
            } else {
                VolumeFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.isSupportsStealthMode() && QsSettingsValueProxy.Companion.getVibrateWhenSilent(this.context, 0) == 0) {
                    string2 = this.resources.getString(2131951947);
                    string4 = string2;
                    z = true;
                    i2 = 2131236985;
                } else {
                    string3 = this.resources.getString(2131951948);
                    string4 = string3;
                    z = true;
                    i2 = 2131236986;
                }
            }
            OpUpdateVolumeZenObject opUpdateVolumeZenObject = new OpUpdateVolumeZenObject(false, 0, null, z, i2, string4);
            volumeVisible = opUpdateVolumeZenObject.getVolumeVisible();
            volumeIconId = opUpdateVolumeZenObject.getVolumeIconId();
            string = opUpdateVolumeZenObject.getVolumeDescription();
            this.zen = i;
        } else if (StringsKt__StringsJVMKt.equals("true", this.audioManager.getParameters(KEY_OPLUS_AUDIO_GET_MUTE_PHONE), true)) {
            string = this.resources.getString(2131951947);
            volumeVisible = true;
        } else {
            volumeIconId = 0;
            string = null;
        }
        if (volumeVisible) {
            this.statusBarIconController.setIcon(this.slotVolume, volumeIconId, string);
        }
        if (volumeVisible != this.customVolumeVisible) {
            this.statusBarIconController.setIconVisibility(this.slotVolume, volumeVisible);
            this.customVolumeVisible = volumeVisible;
        }
        LogBuffer logBuffer = this.logBuffer;
        LogLevel logLevel = LogLevel.DEBUG;
        boolean zIsOplusAlertSlider = FeatureOption.isOplusAlertSlider();
        boolean zEquals = StringsKt__StringsJVMKt.equals("true", this.audioManager.getParameters(KEY_OPLUS_AUDIO_GET_MUTE_PHONE), true);
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("updateCustomVolumeZen: zen=", i, ", isOplusAlertSlider = ", ", isDisableVolumeMute = ", zIsOplusAlertSlider);
        sbM.append(zEquals);
        logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
    }

    public final void updateHeadsetPlug() {
        HeadsetPlugReceiver.Companion companion = HeadsetPlugReceiver.Companion;
        boolean connected = companion.getInstance().getConnected();
        boolean hasMic = companion.getInstance().getHasMic();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateHeadsetPlug connected:", " hasMic:", "OplusPhoneStatusBarPolicyEx-->", connected, hasMic));
        }
        this.logBuffer.log(TAG, LogLevel.DEBUG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateHeadsetPlug connected:", " hasMic:", connected, hasMic), (Throwable) null);
        if (!connected) {
            this.statusBarIconController.setIconVisibility(this.slotHeadset, false);
            return;
        }
        this.statusBarIconController.setIcon(this.slotHeadset, 2131236974, this.resources.getString(hasMic ? 2131951977 : 2131951976));
        this.statusBarIconController.setIconVisibility(this.slotHeadset, true);
    }

    public final void updateHighPerformanceIcon() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("highPerformanceMode = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", this.highPerformanceMode);
        }
        this.statusBarIconController.setIconVisibility(this.slotHighPerformance, this.highPerformanceMode);
    }

    public final void updateHolidayIcon() {
        boolean z = Settings.Global.getInt(this.context.getContentResolver(), OplusNoDisturbListener.ZEN_MODE_SETTING, 0) != 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("State of holidy_mode is :" + z + ", we refresh HolidayIcon now!"));
        }
        this.statusBarIconController.setIconVisibility(this.slotZen, z);
    }

    public final void updateLogIcon() {
        this.statusBarIconController.setIconVisibility(this.slotLogging, Settings.System.getInt(this.context.getContentResolver(), LOGGING_STATUS_KEY, 0) != 0);
    }

    public final void updateNFCIcon(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(" updateNFCIcon(), mNFCState = ", "OplusPhoneStatusBarPolicyEx-->", "SystemUi--Statusbar", i == 3);
        }
        if (i == 1) {
            this.statusBarIconController.setIconVisibility(this.slotNfc, false);
            return;
        }
        StatusBarIconControllerImpl statusBarIconControllerImpl = this.statusBarIconController;
        if (i != 3) {
            statusBarIconControllerImpl.setIconVisibility(this.slotNfc, false);
        } else {
            statusBarIconControllerImpl.setIconVisibility(this.slotNfc, true);
        }
    }

    public final void updateSOS(boolean z) {
        Handler handler = this.handler;
        RunnableC02961 runnableC02961 = new RunnableC02961(0);
        runnableC02961.this$0 = this;
        runnableC02961.$emergencyOnly = z;
        VarHandle.storeStoreFence();
        handler.post(runnableC02961);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0062  */
    public void updateVolumeZenEx(int i, Integer num) {
        boolean z;
        int i2;
        String string;
        if (ZenModeConfig.isZenOverridingRinger(i, this.zenController.getConsolidatedPolicy())) {
            z = false;
            i2 = 0;
            string = null;
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->".concat("updateVolumeZen: ringerModeInternal = " + num));
            }
            z = true;
            if (num != null && num.intValue() == 1) {
                string = this.resources.getString(2131951948);
                i2 = 2131236986;
            } else if (num != null && num.intValue() == 0) {
                string = this.resources.getString(2131951947);
                i2 = 2131236985;
            } else {
                z = false;
                i2 = 0;
                string = null;
            }
        }
        if (z) {
            this.statusBarIconController.setIcon(this.slotVolume, i2, string);
        }
        if (this.volumeVisible != z) {
            this.statusBarIconController.setIconVisibility(this.slotVolume, z);
            this.volumeVisible = z;
        }
        this.logBuffer.log(TAG, LogLevel.DEBUG, "ringerModeInternal = " + num, (Throwable) null);
        updateCustomVolumeZen(i);
    }
}
