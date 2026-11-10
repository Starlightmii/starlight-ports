package com.oplus.systemui.qs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.UiccCardInfo;
import android.telephony.UiccPortInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.connectivity.OplusQSNetworkControllerEx;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.keyguard.telephony.SimStateHelper;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSCarrierTextController implements NetworkController.EmergencyListener, Dumpable {
    public final Executor mBgExecutor;
    public final KeyguardUpdateMonitorCallback mCallback;
    public CharSequence[] mCarrierNames;
    public final CopyOnWriteArrayList mCarrierTextCallbacks = new CopyOnWriteArrayList();
    public final Context mContext;
    public boolean mEmergencyShouldUpdate;
    public final boolean mIsEmergencyCallCapable;
    public boolean mIsRegister;
    public KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public boolean mLastAirplaneMode;
    public boolean mLastEmergencyShouldUpdate;
    public boolean mLastForceReloadSub;
    public int mLastNumSubs;
    public final Handler mMainHandler;
    public final NetworkController mNetworkController;
    public final CharSequence mNoSimSeparator;
    public final OplusDataUsageController mOplusDataUsageController;
    public final AnonymousClass4 mPhoneStateListener;
    public final AnonymousClass5 mReceiver;
    public String mSatelliteCarrierText;
    public boolean mSatelliteConnected;
    public final CharSequence mSeparator;
    public boolean mShowDataUsage;
    public final AnonymousClass1 mSignalCallback;
    public final boolean[] mSimErrorState;
    public final int mSimSlotsNumber;
    public CopyOnWriteArrayList mSubscriptionInfo;
    public boolean mTelephonyCapable;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final AnonymousClass2 mWakefulnessObserver;
    public final WifiManager mWifiManager;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSCarrierTextController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements SignalCallback {
        public /* synthetic */ OplusQSCarrierTextController this$0;

        public /* bridge */ /* synthetic */ void setCallIndicator(IconState iconState, int i) {
            super.setCallIndicator(iconState, i);
        }

        public /* bridge */ /* synthetic */ void setEthernetIndicators(IconState iconState) {
            super.setEthernetIndicators(iconState);
        }

        public /* bridge */ /* synthetic */ void setHasAnySimReady(boolean z) {
            super.setHasAnySimReady(z);
        }

        public /* bridge */ /* synthetic */ void setIsAirplaneMode(IconState iconState) {
            super.setIsAirplaneMode(iconState);
        }

        public /* bridge */ /* synthetic */ void setMobileDataEnabled(boolean z) {
            super.setMobileDataEnabled(z);
        }

        public /* bridge */ /* synthetic */ void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            super.setMobileDataIndicators(mobileDataIndicators);
        }

        public void setSubs(List<SubscriptionInfo> list) {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "setSubs force reload subInfo");
            this.this$0.updateCarrierTextInBgThread(true);
        }

        public /* bridge */ /* synthetic */ void setWifiIndicators(WifiIndicators wifiIndicators) {
            super.setWifiIndicators(wifiIndicators);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSCarrierTextController$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ OplusQSCarrierTextController this$0;

        public final void onFinishedWakingUp() {
            Iterator it = this.this$0.mCarrierTextCallbacks.iterator();
            while (it.hasNext()) {
            }
        }

        public final void onStartedGoingToSleep() {
            Iterator it = this.this$0.mCarrierTextCallbacks.iterator();
            while (it.hasNext()) {
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSCarrierTextController$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ OplusQSCarrierTextController this$0;

        public final void onRefreshCarrierInfo() {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "onRefreshCarrierInfo");
            this.this$0.updateCarrierTextInBgThread(true);
        }

        public final void onSimStateChanged(int i, int i2, int i3) {
            OplusQSCarrierTextController oplusQSCarrierTextController = this.this$0;
            if (i2 < 0 || i2 >= oplusQSCarrierTextController.mSimSlotsNumber) {
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "OplusQSCarrierTextController", ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "onSimStateChanged() - slotId invalid: ", " mTelephonyCapable: "), oplusQSCarrierTextController.mTelephonyCapable);
                return;
            }
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "onSimStateChanged: " + OplusQSCarrierTextController.getStatusForIccState(i3));
            StatusMode statusForIccState = OplusQSCarrierTextController.getStatusForIccState(i3);
            boolean[] zArr = oplusQSCarrierTextController.mSimErrorState;
            if (statusForIccState == StatusMode.SimIoError) {
                zArr[i2] = true;
                oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
            } else if (zArr[i2]) {
                zArr[i2] = false;
                oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
            }
        }

        public final void onTelephonyCapable(boolean z) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("onTelephonyCapable() mTelephonyCapable: ", LogUtil.TAG_QS, "OplusQSCarrierTextController", z);
            OplusQSCarrierTextController oplusQSCarrierTextController = this.this$0;
            oplusQSCarrierTextController.mTelephonyCapable = z;
            oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSCarrierTextController$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements SatellitePolicy.IConnectionCallback {
        public /* synthetic */ OplusQSCarrierTextController this$0;

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onCarrierNameChanged(String str) {
            OplusQSCarrierTextController oplusQSCarrierTextController = this.this$0;
            oplusQSCarrierTextController.mSatelliteCarrierText = str;
            FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("onCarrierNameChanged:", str, LogUtil.TAG_STATUSBAR, "OplusQSCarrierTextController");
            oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteConnected() {
            OplusQSCarrierTextController oplusQSCarrierTextController = this.this$0;
            oplusQSCarrierTextController.mSatelliteConnected = true;
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusQSCarrierTextController", "onSatelliteConnected");
            oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteDisConnected() {
            OplusQSCarrierTextController oplusQSCarrierTextController = this.this$0;
            oplusQSCarrierTextController.mSatelliteConnected = false;
            LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusQSCarrierTextController", "onSatelliteDisConnected");
            oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onServiceStateChanged(boolean z) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CarrierTextCallback {
        default void setVisible(boolean z) {
        }

        void updateCarrierInfo(OplusCarrierTextCallbackInfo oplusCarrierTextCallbackInfo);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusCarrierTextCallbackInfo {
        public final boolean airplaneMode;
        public final boolean anySimReady;
        public final CharSequence carrierText;
        public final String dataUsage;

        public OplusCarrierTextCallbackInfo(String str, CharSequence charSequence, boolean z, boolean z2) {
            this.dataUsage = str;
            this.carrierText = charSequence;
            this.anySimReady = z;
            this.airplaneMode = z2;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class StatusMode {
        public static final /* synthetic */ StatusMode[] $VALUES;
        public static final StatusMode Normal;
        public static final StatusMode SimIoError;
        public static final StatusMode SimLocked;
        public static final StatusMode SimMissing;
        public static final StatusMode SimMissingLocked;
        public static final StatusMode SimNotReady;
        public static final StatusMode SimPermDisabled;
        public static final StatusMode SimPukLocked;
        public static final StatusMode SimUnknown;

        static {
            StatusMode statusMode = new StatusMode("Normal", 0);
            Normal = statusMode;
            StatusMode statusMode2 = new StatusMode("NetworkLocked", 1);
            StatusMode statusMode3 = new StatusMode("SimMissing", 2);
            SimMissing = statusMode3;
            StatusMode statusMode4 = new StatusMode("SimMissingLocked", 3);
            SimMissingLocked = statusMode4;
            StatusMode statusMode5 = new StatusMode("SimPukLocked", 4);
            SimPukLocked = statusMode5;
            StatusMode statusMode6 = new StatusMode("SimLocked", 5);
            SimLocked = statusMode6;
            StatusMode statusMode7 = new StatusMode("SimPermDisabled", 6);
            SimPermDisabled = statusMode7;
            StatusMode statusMode8 = new StatusMode("SimNotReady", 7);
            SimNotReady = statusMode8;
            StatusMode statusMode9 = new StatusMode("SimIoError", 8);
            SimIoError = statusMode9;
            StatusMode statusMode10 = new StatusMode("SimUnknown", 9);
            SimUnknown = statusMode10;
            $VALUES = new StatusMode[]{statusMode, statusMode2, statusMode3, statusMode4, statusMode5, statusMode6, statusMode7, statusMode8, statusMode9, statusMode10};
        }

        public static StatusMode valueOf(String str) {
            return (StatusMode) Enum.valueOf(StatusMode.class, str);
        }

        public static StatusMode[] values() {
            return (StatusMode[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Code duplicated, block: B:167:0x039c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:168:0x039e  */
    /* JADX WARN: Code duplicated, block: B:169:0x03ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:170:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:172:0x03c1  */
    /* JADX WARN: Code duplicated, block: B:198:0x046b  */
    /* JADX WARN: Code duplicated, block: B:199:0x0477  */
    /* JADX WARN: Code duplicated, block: B:201:0x0483  */
    /* JADX WARN: Code duplicated, block: B:203:0x0487  */
    /* JADX WARN: Code duplicated, block: B:207:0x0498  */
    /* JADX WARN: Code duplicated, block: B:208:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:216:0x04c4  */
    /* JADX WARN: Code duplicated, block: B:33:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:44:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:50:0x0115  */
    /* JADX WARN: Code duplicated, block: B:52:0x011e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0122  */
    /* JADX WARN: Code duplicated, block: B:55:0x012b  */
    /* JADX WARN: Code duplicated, block: B:56:0x012f  */
    /* JADX WARN: Code duplicated, block: B:80:0x01af  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    public static void $r8$lambda$TsvvTFF76CaCVfAEBQ3hplPVLK8(OplusQSCarrierTextController oplusQSCarrierTextController, boolean z) {
        String dataUsageInfo;
        int i;
        CharSequence string;
        String str;
        TelephonyManager telephonyManager;
        int[] iArr;
        String string2;
        String[] strArr;
        int i2;
        KeyguardUpdateMonitor keyguardUpdateMonitor;
        int dataNetworkType;
        long bitMaskForNetworkType;
        int i3;
        ServiceState serviceState;
        ServiceState serviceState2;
        boolean z2;
        ?? r9;
        boolean z3;
        if (oplusQSCarrierTextController.mKeyguardUpdateMonitor == null) {
            return;
        }
        FeatureOption.INSTANCE.getClass();
        int i4 = 0;
        if (FeatureOption.isWifiOnly() && !QSFeatureOption.INSTANCE.isVirtualCommInService(oplusQSCarrierTextController.mContext)) {
            oplusQSCarrierTextController.postToCallbackVisible(false);
            return;
        }
        oplusQSCarrierTextController.postToCallbackVisible(true);
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) oplusQSCarrierTextController.getSubscriptionInfo(z);
        int size = copyOnWriteArrayList.size();
        int[] iArr2 = new int[size];
        oplusQSCarrierTextController.mCarrierNames = new CharSequence[3];
        boolean zIsAirplaneModeOn = QsSettingsValueProxy.isAirplaneModeOn(oplusQSCarrierTextController.mContext);
        int i5 = 1;
        int i6 = 0;
        boolean z4 = false;
        while (true) {
            dataUsageInfo = "";
            if (i6 >= size) {
                break;
            }
            int i7 = i4;
            int subscriptionId = ((SubscriptionInfo) copyOnWriteArrayList.get(i6)).getSubscriptionId();
            int simSlotIndex = ((SubscriptionInfo) copyOnWriteArrayList.get(i6)).getSimSlotIndex();
            iArr2[i6] = subscriptionId;
            if (SubscriptionManager.isValidSlotIndex(simSlotIndex)) {
                oplusQSCarrierTextController.mCarrierNames[simSlotIndex] = "";
                int simStateForSlotId = oplusQSCarrierTextController.mKeyguardUpdateMonitor.getSimStateForSlotId(simSlotIndex);
                CharSequence carrierName = ((SubscriptionInfo) copyOnWriteArrayList.get(i6)).getCarrierName();
                if (CustomizeFeatureOption.sIsSupportNetworkStatus || CustomizeFeatureOption.sIsSupportAtLeast3G) {
                    SubscriptionInfo subscriptionInfo = (SubscriptionInfo) copyOnWriteArrayList.get(i6);
                    StringBuilder sb = new StringBuilder();
                    if (SubscriptionManager.isValidSlotIndex(simSlotIndex)) {
                        iArr = iArr2;
                        string2 = getOplusNetworkControllerEx().isFiveG(simSlotIndex) ? oplusQSCarrierTextController.mContext.getString(2131952947) : "";
                        if (string2.isEmpty()) {
                            int subscriptionId2 = subscriptionInfo.getSubscriptionId();
                            keyguardUpdateMonitor = oplusQSCarrierTextController.mKeyguardUpdateMonitor;
                            if (keyguardUpdateMonitor == null && (serviceState = (ServiceState) keyguardUpdateMonitor.mServiceStates.get(Integer.valueOf(subscriptionId2))) != null && (serviceState.getDataRegState() == 0 || serviceState.getVoiceRegState() == 0)) {
                                dataNetworkType = serviceState.getDataNetworkType();
                                if (dataNetworkType == 0) {
                                    dataNetworkType = serviceState.getVoiceNetworkType();
                                }
                            } else {
                                dataNetworkType = i7;
                            }
                            bitMaskForNetworkType = TelephonyManager.getBitMaskForNetworkType(dataNetworkType);
                            if ((bitMaskForNetworkType & 32843) == 0 && !CustomizeFeatureOption.sIsSupportAtLeast3G) {
                                i3 = 2131952719;
                            } else if ((bitMaskForNetworkType & 93108) != 0) {
                                i3 = 2131952720;
                            } else if ((bitMaskForNetworkType & 397312) != 0) {
                                i3 = 2131952721;
                            } else {
                                i3 = 2131952722;
                            }
                            string2 = oplusQSCarrierTextController.getContext().getResources().getString(i3);
                        }
                        if (!TextUtils.isEmpty(carrierName) || TextUtils.isEmpty(oplusQSCarrierTextController.mSeparator)) {
                            sb.append(carrierName);
                        } else {
                            String[] strArrSplit = carrierName.toString().split(((String) oplusQSCarrierTextController.mSeparator).toString(), 2);
                            int i8 = i7;
                            while (i8 < strArrSplit.length) {
                                if (TextUtils.isEmpty(strArrSplit[i8])) {
                                    strArr = strArrSplit;
                                    i2 = i8;
                                } else {
                                    if (TextUtils.isEmpty(string2)) {
                                        strArr = strArrSplit;
                                        i2 = i8;
                                    } else {
                                        strArr = strArrSplit;
                                        i2 = i8;
                                        strArr[i2] = Fragment$5$.ExternalSyntheticOutline0.m(strArr[i8], " ", string2, new StringBuilder());
                                    }
                                    if (i2 <= 0 || !strArr[i2].equals(strArr[i2 - 1])) {
                                        if (i2 > 0) {
                                            sb.append(oplusQSCarrierTextController.mSeparator);
                                        }
                                        sb.append(strArr[i2]);
                                    }
                                }
                                i8 = i2 + 1;
                                strArrSplit = strArr;
                            }
                        }
                        carrierName = sb.toString();
                    } else {
                        iArr = iArr2;
                    }
                    if (string2.isEmpty()) {
                        int subscriptionId3 = subscriptionInfo.getSubscriptionId();
                        keyguardUpdateMonitor = oplusQSCarrierTextController.mKeyguardUpdateMonitor;
                        if (keyguardUpdateMonitor == null) {
                            dataNetworkType = i7;
                        } else {
                            dataNetworkType = i7;
                        }
                        bitMaskForNetworkType = TelephonyManager.getBitMaskForNetworkType(dataNetworkType);
                        if ((bitMaskForNetworkType & 32843) == 0) {
                            if ((bitMaskForNetworkType & 93108) != 0) {
                                i3 = 2131952720;
                            } else if ((bitMaskForNetworkType & 397312) != 0) {
                                i3 = 2131952721;
                            } else {
                                i3 = 2131952722;
                            }
                        } else if ((bitMaskForNetworkType & 93108) != 0) {
                            i3 = 2131952720;
                        } else if ((bitMaskForNetworkType & 397312) != 0) {
                            i3 = 2131952721;
                        } else {
                            i3 = 2131952722;
                        }
                        string2 = oplusQSCarrierTextController.getContext().getResources().getString(i3);
                    }
                    if (TextUtils.isEmpty(carrierName)) {
                        sb.append(carrierName);
                    } else {
                        sb.append(carrierName);
                    }
                    carrierName = sb.toString();
                } else {
                    iArr = iArr2;
                    i6 = i6;
                    i5 = i5;
                }
                SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).getClass();
                CharSequence string3 = carrierName;
                if (SimStateHelper.isVoWifiState(simSlotIndex)) {
                    OplusQSCarrierTextControllerEx oplusQSCarrierTextControllerEx = OplusQSCarrierTextControllerEx.getInstance(oplusQSCarrierTextController.mContext);
                    oplusQSCarrierTextControllerEx.getClass();
                    String string4 = carrierName.toString();
                    OplusQSCarrierTextControllerEx.Config config = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId);
                    if (TextUtils.isEmpty(config.mWfcSpnCustomize)) {
                        OplusQSCarrierTextControllerEx.Config config2 = new OplusQSCarrierTextControllerEx.Config();
                        PersistableBundle configForSubId = oplusQSCarrierTextControllerEx.mConfigMgr.getConfigForSubId(subscriptionId);
                        if (configForSubId != null) {
                            config2.mWfcSpnCustomize = configForSubId.getString("wfc_spn_customize", "");
                        }
                        oplusQSCarrierTextControllerEx.mConfigMap.put(subscriptionId, config2);
                        if (!OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mLastConfigMap, subscriptionId).equals(OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId))) {
                            OplusQSCarrierTextControllerEx.Config config3 = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId);
                            OplusQSCarrierTextControllerEx.Config config4 = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mLastConfigMap, subscriptionId);
                            config4.getClass();
                            config4.mWfcSpnCustomize = config3.mWfcSpnCustomize;
                            oplusQSCarrierTextControllerEx.mLastConfigMap.put(subscriptionId, config4);
                        }
                        config = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId);
                    }
                    String str2 = !TextUtils.isEmpty(config.mWfcSpnCustomize) ? config.mWfcSpnCustomize : string4;
                    LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextControllerEx", "createCarrierInfoForOperatorWfc carrierInfo = " + ((Object) carrierName) + " wfcSpnCustomize = " + config.mWfcSpnCustomize);
                    string3 = str2;
                }
                if (zIsAirplaneModeOn) {
                    SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).getClass();
                    if (!SimStateHelper.isVoWifiState(simSlotIndex)) {
                        LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "slot:" + simSlotIndex + ", isAirPlaneMode, and no vowifi.");
                        boolean zIsAirplaneModeOn2 = QsSettingsValueProxy.isAirplaneModeOn(oplusQSCarrierTextController.mContext);
                        if (!CustomizeFeatureOption.sIsShowCarrierAirWfc || !zIsAirplaneModeOn2 || !getOplusNetworkControllerEx().getVoWifiState()) {
                            string3 = oplusQSCarrierTextController.getContext().getString(2131954009);
                        }
                    }
                }
                CharSequence carrierTextForSimState = oplusQSCarrierTextController.getCarrierTextForSimState(simStateForSlotId, string3);
                if (carrierTextForSimState == null) {
                    SimStateHelper simStateHelper = SimStateHelper.getInstance(oplusQSCarrierTextController.mContext);
                    boolean z5 = oplusQSCarrierTextController.mEmergencyShouldUpdate;
                    if (simSlotIndex == 0) {
                        z2 = simStateHelper.mHasServiceSim1;
                    } else if (simSlotIndex != 1) {
                        r9 = i7;
                    } else {
                        z3 = simStateHelper.mHasServiceSim2;
                    }
                    if (r9 != 0) {
                        r9 = z2;
                        r9 = z3;
                        if (!simStateHelper.isEmergencyOnlyBySlot(size, simSlotIndex, z5)) {
                            TelephonyManager telephonyManager2 = oplusQSCarrierTextController.getTelephonyManager();
                            carrierTextForSimState = oplusQSCarrierTextController.getCarrierTextForSimState(telephonyManager2 != null ? telephonyManager2.getSimState(simSlotIndex) : i7, string3);
                        }
                    } else {
                        r9 = z2;
                        r9 = z3;
                        simStateHelper.getClass();
                    }
                }
                if (SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).isEmergencyOnlyBySlot(size, simSlotIndex, oplusQSCarrierTextController.mEmergencyShouldUpdate)) {
                    LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "updateCarrierText emergency_calls_only");
                    carrierTextForSimState = oplusQSCarrierTextController.getContext().getString(2131953129);
                }
                if (carrierTextForSimState != null) {
                    oplusQSCarrierTextController.mCarrierNames[simSlotIndex] = carrierTextForSimState;
                    i5 = i7;
                } else {
                    i5 = i5;
                }
                if (simStateForSlotId == 5 && (serviceState2 = (ServiceState) oplusQSCarrierTextController.mKeyguardUpdateMonitor.mServiceStates.get(Integer.valueOf(subscriptionId))) != null && serviceState2.getDataRegState() == 0 && (serviceState2.getRilDataRadioTechnology() != 18 || (oplusQSCarrierTextController.mWifiManager.isWifiEnabled() && oplusQSCarrierTextController.mWifiManager.getConnectionInfo() != null && oplusQSCarrierTextController.mWifiManager.getConnectionInfo().getBSSID() != null))) {
                    z4 = true;
                }
            } else {
                copyOnWriteArrayList = copyOnWriteArrayList;
                iArr = iArr2;
                i6 = i6;
            }
            i6++;
            i4 = i7;
            copyOnWriteArrayList = copyOnWriteArrayList;
            iArr2 = iArr;
        }
        int i9 = i4;
        int[] iArr3 = iArr2;
        int i10 = i5;
        if (size != 0 || (telephonyManager = oplusQSCarrierTextController.getTelephonyManager()) == null) {
            i = i10;
        } else {
            int phoneCount = telephonyManager.getPhoneCount();
            i = i10;
            for (int i11 = i9; i11 < phoneCount; i11++) {
                int simState = telephonyManager.getSimState(i11);
                if (simState == 6) {
                    oplusQSCarrierTextController.mCarrierNames[i11] = oplusQSCarrierTextController.getCarrierTextForSimState(simState, "");
                    i = i9;
                }
            }
        }
        CharSequence[] charSequenceArr = oplusQSCarrierTextController.mCarrierNames;
        if (z4) {
            SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).getClass();
            if (SimStateHelper.isVoWifiState(i9) || SimStateHelper.isVoWifiState(1)) {
                if (i != 0) {
                    str = SystemProperties.get("android.telephony.mcc_change");
                    String str3 = SystemProperties.get("android.telephony.mcc_change2");
                    if (!"440".equals(str) || "441".equals(str) || "410".equals(str) || "440".equals(str3) || "441".equals(str3) || "410".equals(str3)) {
                        string = oplusQSCarrierTextController.getContext().getString(2131955139);
                    } else if (SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).showNoNetwork()) {
                        SimStateHelper simStateHelper2 = SimStateHelper.getInstance(oplusQSCarrierTextController.mContext);
                        simStateHelper2.getClass();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("  mHasEmergencyOnlySim2 = ", "SimStateHelper-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("mHasServiceSim1 = ", "  mHasServiceSim2 = ", "  mHasEmergencyOnlySim1 = ", simStateHelper2.mHasServiceSim1, simStateHelper2.mHasServiceSim2), simStateHelper2.mHasEmergencyOnlySim1, simStateHelper2.mHasEmergencyOnlySim2));
                        }
                        string = (CustomizeFeatureOption.sIsTmoSignalViewLayout || CustomizeFeatureOption.sIsSprintSignalViewLayout) ? concatenate(oplusQSCarrierTextController.getContext().getString(2131955139), oplusQSCarrierTextController.getContext().getString(2131954009), oplusQSCarrierTextController.mNoSimSeparator) : oplusQSCarrierTextController.getContext().getString(2131954008);
                    } else {
                        LogUtil.d("OplusQSCarrierTextController", "showNoNetwork = false");
                        string = oplusQSCarrierTextController.getContext().getString(2131953129);
                    }
                } else if (!SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).showNoNetwork()) {
                    string = null;
                } else if (!CustomizeFeatureOption.sIsTmoSignalViewLayout || CustomizeFeatureOption.sIsSprintSignalViewLayout) {
                    string = oplusQSCarrierTextController.getContext().getString(2131954009);
                } else {
                    string = oplusQSCarrierTextController.getContext().getString(2131954008);
                }
            } else if (zIsAirplaneModeOn) {
                string = oplusQSCarrierTextController.getContext().getString(2131952090);
            } else if (i != 0) {
                str = SystemProperties.get("android.telephony.mcc_change");
                String str4 = SystemProperties.get("android.telephony.mcc_change2");
                if ("440".equals(str)) {
                    string = oplusQSCarrierTextController.getContext().getString(2131955139);
                } else {
                    string = oplusQSCarrierTextController.getContext().getString(2131955139);
                }
            } else if (!SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).showNoNetwork()) {
                string = null;
            } else if (CustomizeFeatureOption.sIsTmoSignalViewLayout) {
                string = oplusQSCarrierTextController.getContext().getString(2131954009);
            } else {
                string = oplusQSCarrierTextController.getContext().getString(2131954009);
            }
        } else if (zIsAirplaneModeOn) {
            string = oplusQSCarrierTextController.getContext().getString(2131952090);
        } else if (i != 0) {
            str = SystemProperties.get("android.telephony.mcc_change");
            String str5 = SystemProperties.get("android.telephony.mcc_change2");
            if ("440".equals(str)) {
                string = oplusQSCarrierTextController.getContext().getString(2131955139);
            } else {
                string = oplusQSCarrierTextController.getContext().getString(2131955139);
            }
        } else if (!SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).showNoNetwork()) {
            string = null;
        } else if (CustomizeFeatureOption.sIsTmoSignalViewLayout) {
            string = oplusQSCarrierTextController.getContext().getString(2131954009);
        } else {
            string = oplusQSCarrierTextController.getContext().getString(2131954009);
        }
        if (TextUtils.isEmpty(string)) {
            if (zIsAirplaneModeOn) {
                SimStateHelper.getInstance(oplusQSCarrierTextController.mContext).getClass();
                if (SimStateHelper.isVoWifiState(i9) || SimStateHelper.isVoWifiState(1)) {
                    string = oplusQSCarrierTextController.joinNotEmpty(oplusQSCarrierTextController.mSeparator, charSequenceArr, size);
                }
            } else {
                string = oplusQSCarrierTextController.joinNotEmpty(oplusQSCarrierTextController.mSeparator, charSequenceArr, size);
            }
        }
        boolean zIsAirplaneModeOn3 = QsSettingsValueProxy.isAirplaneModeOn(oplusQSCarrierTextController.mContext);
        if (CustomizeFeatureOption.sIsShowCarrierAirWfc && zIsAirplaneModeOn3 && getOplusNetworkControllerEx().getVoWifiState()) {
            string = oplusQSCarrierTextController.joinNotEmpty(oplusQSCarrierTextController.mSeparator, charSequenceArr, size);
        }
        if (oplusQSCarrierTextController.mSatelliteConnected) {
            string = oplusQSCarrierTextController.mSatelliteCarrierText;
        }
        for (int i12 = i9; i12 < size; i12++) {
            if (SubscriptionManager.isValidSubscriptionId(iArr3[i12])) {
                if (zIsAirplaneModeOn || !oplusQSCarrierTextController.mShowDataUsage || !isAnySimEnabled() || !OplusQSCarrierTextControllerEx.getInstance(oplusQSCarrierTextController.mContext).mListening) {
                    break;
                    break;
                    break;
                    break;
                }
                dataUsageInfo = oplusQSCarrierTextController.mOplusDataUsageController.getDataUsageInfo();
                break;
            }
        }
        if (oplusQSCarrierTextController.mLastNumSubs != size || oplusQSCarrierTextController.mLastAirplaneMode != zIsAirplaneModeOn || oplusQSCarrierTextController.mLastForceReloadSub != z) {
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "OplusQSCarrierTextController", KeyguardLogger$.ExternalSyntheticOutline0.m("updateCarrierText numSubs: ", size, " airplaneMode:", " forceReloadSub:", zIsAirplaneModeOn), z);
        }
        oplusQSCarrierTextController.mLastNumSubs = size;
        oplusQSCarrierTextController.mLastAirplaneMode = zIsAirplaneModeOn;
        oplusQSCarrierTextController.mLastForceReloadSub = z;
        if (TextUtils.isEmpty(string)) {
            return;
        }
        OplusCarrierTextCallbackInfo oplusCarrierTextCallbackInfo = new OplusCarrierTextCallbackInfo(dataUsageInfo, string, i ^ 1, zIsAirplaneModeOn);
        Iterator it = oplusQSCarrierTextController.mCarrierTextCallbacks.iterator();
        while (it.hasNext()) {
            CarrierTextCallback carrierTextCallback = (CarrierTextCallback) ((WeakReference) it.next()).get();
            if (carrierTextCallback != null) {
                Handler handler = oplusQSCarrierTextController.mMainHandler;
                OplusQSCarrierTextController$$ExternalSyntheticLambda4 oplusQSCarrierTextController$$ExternalSyntheticLambda4 = new OplusQSCarrierTextController$$ExternalSyntheticLambda4();
                oplusQSCarrierTextController$$ExternalSyntheticLambda4.f$0 = carrierTextCallback;
                oplusQSCarrierTextController$$ExternalSyntheticLambda4.f$1 = oplusCarrierTextCallbackInfo;
                VarHandle.storeStoreFence();
                handler.post(oplusQSCarrierTextController$$ExternalSyntheticLambda4);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.oplus.systemui.qs.OplusQSCarrierTextController$4] */
    public OplusQSCarrierTextController(Context context, WifiManager wifiManager, NetworkController networkController, WakefulnessLifecycle wakefulnessLifecycle, Handler handler, Executor executor, SatellitePolicy satellitePolicy, DumpManager dumpManager) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSignalCallback = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWakefulnessObserver = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = anonymousClass3;
        this.mPhoneStateListener = new PhoneStateListener() { // from class: com.oplus.systemui.qs.OplusQSCarrierTextController.4
            @Override // android.telephony.PhoneStateListener
            public final void onActiveDataSubscriptionIdChanged(int i) {
                OplusQSCarrierTextController oplusQSCarrierTextController = OplusQSCarrierTextController.this;
                if (oplusQSCarrierTextController.mKeyguardUpdateMonitor != null) {
                    oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
                }
            }
        };
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.OplusQSCarrierTextController.5
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                boolean z;
                String action = intent.getAction();
                OplusQSCarrierTextController.this.setActiveSubscriptionInfoList();
                if (action.equals("android.telephony.action.CARRIER_CONFIG_CHANGED")) {
                    OplusQSCarrierTextControllerEx oplusQSCarrierTextControllerEx = OplusQSCarrierTextControllerEx.getInstance(context2);
                    List subscriptionInfo = OplusQSCarrierTextController.this.getSubscriptionInfo(false);
                    oplusQSCarrierTextControllerEx.getClass();
                    if (subscriptionInfo != null) {
                        int i = 0;
                        z = false;
                        while (true) {
                            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) subscriptionInfo;
                            if (i >= copyOnWriteArrayList.size()) {
                                break;
                            }
                            int subscriptionId = ((SubscriptionInfo) copyOnWriteArrayList.get(i)).getSubscriptionId();
                            OplusQSCarrierTextControllerEx.Config config = new OplusQSCarrierTextControllerEx.Config();
                            PersistableBundle configForSubId = oplusQSCarrierTextControllerEx.mConfigMgr.getConfigForSubId(subscriptionId);
                            if (configForSubId != null) {
                                config.mWfcSpnCustomize = configForSubId.getString("wfc_spn_customize", "");
                            }
                            oplusQSCarrierTextControllerEx.mConfigMap.put(subscriptionId, config);
                            if (!OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mLastConfigMap, subscriptionId).equals(OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId))) {
                                OplusQSCarrierTextControllerEx.Config config2 = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mConfigMap, subscriptionId);
                                OplusQSCarrierTextControllerEx.Config config3 = OplusQSCarrierTextControllerEx.getConfig(oplusQSCarrierTextControllerEx.mLastConfigMap, subscriptionId);
                                config3.getClass();
                                config3.mWfcSpnCustomize = config2.mWfcSpnCustomize;
                                oplusQSCarrierTextControllerEx.mLastConfigMap.put(subscriptionId, config3);
                                z = true;
                            }
                            i++;
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        OplusQSCarrierTextController.this.updateCarrierTextInBgThread(false);
                    }
                }
            }
        };
        this.mCarrierNames = new CharSequence[3];
        this.mSatelliteConnected = false;
        this.mSatelliteCarrierText = "";
        this.mLastNumSubs = -1;
        this.mContext = context;
        this.mIsEmergencyCallCapable = getTelephonyManager().isVoiceCapable();
        this.mWifiManager = wifiManager;
        this.mSeparator = context.getResources().getString(2131955595);
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        int supportedModemCount = getTelephonyManager().getSupportedModemCount();
        this.mSimSlotsNumber = supportedModemCount;
        this.mSimErrorState = new boolean[supportedModemCount];
        this.mMainHandler = handler;
        this.mBgExecutor = executor;
        this.mNoSimSeparator = context.getResources().getString(2131955594);
        this.mOplusDataUsageController = new OplusDataUsageController(context, networkController);
        this.mNetworkController = networkController;
        this.mEmergencyShouldUpdate = false;
        this.mLastEmergencyShouldUpdate = false;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(broadcastReceiver, intentFilter, executor);
        AnonymousClass6 anonymousClass6 = new AnonymousClass6();
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        satellitePolicy.addCallback((SatellitePolicy.IConnectionCallback) anonymousClass6);
        dumpManager.registerNormalDumpable("OplusQSCarrierTextController", this);
    }

    public static CharSequence concatenate(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        boolean zIsEmpty2 = TextUtils.isEmpty(charSequence2);
        if (zIsEmpty || zIsEmpty2) {
            if (zIsEmpty) {
                return !zIsEmpty2 ? charSequence2 : "";
            }
            return charSequence;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence);
        sb.append(charSequence3);
        sb.append(charSequence2);
        return sb.toString();
    }

    private Context getContext() {
        return this.mContext;
    }

    private String getMissingSimMessage() {
        return this.mTelephonyCapable ? getContext().getString(2131955139) : "";
    }

    private static OplusQSNetworkControllerEx getOplusNetworkControllerEx() {
        return ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsNetworkControllerEx();
    }

    public static boolean[] getSimInsertedStatus() {
        boolean[] simInsertedStatus = getOplusNetworkControllerEx().getSimInsertedStatus();
        if (simInsertedStatus.length > 1) {
            return simInsertedStatus;
        }
        return simInsertedStatus.length == 0 ? new boolean[]{false, false} : new boolean[]{simInsertedStatus[0], false};
    }

    public static StatusMode getStatusForIccState(int i) {
        if (!((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mDeviceProvisioned && (i == 1 || i == 7)) {
            i = 4;
        }
        switch (i) {
            case 1:
                return StatusMode.SimMissing;
            case 2:
                return StatusMode.SimLocked;
            case 3:
                return StatusMode.SimPukLocked;
            case 4:
                return StatusMode.SimMissingLocked;
            case 5:
                return StatusMode.Normal;
            case 6:
                return StatusMode.SimNotReady;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return StatusMode.SimPermDisabled;
            case 8:
                return StatusMode.SimIoError;
            default:
                return StatusMode.SimUnknown;
        }
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) this.mContext.getSystemService("phone");
    }

    private List<UiccCardInfo> getUiccCardsInfo() {
        return getTelephonyManager().getUiccCardsInfo();
    }

    public static boolean isAnySimEnabled() {
        boolean z;
        boolean z2;
        OplusQSNetworkControllerEx oplusNetworkControllerEx = getOplusNetworkControllerEx();
        boolean[] simForbiddenStatus = oplusNetworkControllerEx.getSimForbiddenStatus();
        boolean[] simInsertedStatus = oplusNetworkControllerEx.getSimInsertedStatus();
        if (simInsertedStatus.length <= 0 || simForbiddenStatus.length <= 0 || !simInsertedStatus[0]) {
            z = false;
        } else {
            z = !simForbiddenStatus[0];
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isAnySimEnabled enabledSim1 = ", LogUtil.TAG_QS, "OplusQSCarrierTextController", z);
        }
        if (simInsertedStatus.length <= 1 || simForbiddenStatus.length <= 1 || !simInsertedStatus[1]) {
            z2 = false;
        } else {
            z2 = !simForbiddenStatus[1];
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isAnySimEnabled enabledSim2 = ", LogUtil.TAG_QS, "OplusQSCarrierTextController", z2);
        }
        return z || z2;
    }

    public final boolean checkSimValid(boolean[] zArr, int i, int i2) {
        boolean z;
        if (i2 >= this.mSimSlotsNumber) {
            SimStateHelper simStateHelper = SimStateHelper.getInstance(this.mContext);
            boolean z2 = this.mEmergencyShouldUpdate;
            if (i != 0) {
                z = i != 1 ? false : simStateHelper.mHasServiceSim2;
            } else {
                z = simStateHelper.mHasServiceSim1;
            }
            if (!z) {
                simStateHelper.getClass();
            } else if (simStateHelper.isEmergencyOnlyBySlot(i2, i, z2)) {
            }
            if (i >= 2 || zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (printWriter == null) {
            return;
        }
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("OplusQSCarrierTextController", "updateCarrierText numSubs: ", printWriter);
        sbM.append(this.mLastNumSubs);
        sbM.append(" airplaneMode:");
        sbM.append(this.mLastAirplaneMode);
        sbM.append(" forceReloadSub:");
        OplusLockIconView$.ExternalSyntheticOutline0.m(sbM, this.mLastForceReloadSub, printWriter);
    }

    public final CharSequence getCarrierTextForSimState(int i, CharSequence charSequence) {
        int iOrdinal = getStatusForIccState(i).ordinal();
        if (iOrdinal == 0) {
            return charSequence;
        }
        boolean z = this.mIsEmergencyCallCapable;
        if (iOrdinal == 1) {
            CharSequence text = this.mContext.getText(2131953852);
            return z ? concatenate(text, charSequence, this.mNoSimSeparator) : text;
        }
        switch (iOrdinal) {
            case 4:
                return makeCarrierStringOnLocked(getContext().getText(2131953904), charSequence);
            case 5:
                return makeCarrierStringOnLocked(getContext().getText(2131953903), charSequence);
            case 6:
                CharSequence text2 = getContext().getText(2131953876);
                return z ? concatenate(text2, charSequence, this.mNoSimSeparator) : text2;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return getContext().getString(2131953129);
            case 8:
                CharSequence text3 = getContext().getText(2131953902);
                return z ? concatenate(text3, charSequence, this.mNoSimSeparator) : text3;
            default:
                return null;
        }
    }

    public final List getSubscriptionInfo(boolean z) {
        KeyguardUpdateMonitor keyguardUpdateMonitor;
        List filteredSubscriptionInfo;
        if ((this.mSubscriptionInfo == null || z) && (keyguardUpdateMonitor = this.mKeyguardUpdateMonitor) != null && (filteredSubscriptionInfo = keyguardUpdateMonitor.getFilteredSubscriptionInfo()) != null) {
            this.mSubscriptionInfo = new CopyOnWriteArrayList(filteredSubscriptionInfo);
        }
        if (this.mSubscriptionInfo == null) {
            this.mSubscriptionInfo = new CopyOnWriteArrayList();
        }
        return this.mSubscriptionInfo;
    }

    public final CharSequence joinNotEmpty(CharSequence charSequence, CharSequence[] charSequenceArr, int i) {
        int length = charSequenceArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        OplusQSNetworkControllerEx oplusNetworkControllerEx = getOplusNetworkControllerEx();
        boolean[] simForbiddenStatus = oplusNetworkControllerEx.getSimForbiddenStatus();
        boolean[] simInsertedStatus = oplusNetworkControllerEx.getSimInsertedStatus();
        boolean[] zArr = {simInsertedStatus[0] && simForbiddenStatus[0], simInsertedStatus[1] && simForbiddenStatus[1]};
        if (ListPopupWindow$.ExternalSyntheticOutline0.m(this.mContext) == 1) {
            for (int i2 = length - 1; i2 >= 0; i2--) {
                if (!TextUtils.isEmpty(charSequenceArr[i2]) && checkSimValid(zArr, i2, i)) {
                    if (!TextUtils.isEmpty(sb)) {
                        sb.append(charSequence);
                    }
                    sb.append("\u2068");
                    sb.append(charSequenceArr[i2]);
                    sb.append("\u2069");
                }
            }
        } else {
            for (int i3 = 0; i3 < length; i3++) {
                if (!TextUtils.isEmpty(charSequenceArr[i3]) && checkSimValid(zArr, i3, i)) {
                    if (!TextUtils.isEmpty(sb)) {
                        sb.append(charSequence);
                    }
                    sb.append(charSequenceArr[i3]);
                }
            }
        }
        return (zArr[0] && zArr[1]) ? String.valueOf(charSequenceArr[0]) : sb.toString();
    }

    public final CharSequence makeCarrierStringOnLocked(CharSequence charSequence, CharSequence charSequence2) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        boolean zIsEmpty2 = TextUtils.isEmpty(charSequence2);
        if (!zIsEmpty && !zIsEmpty2) {
            return this.mContext.getString(2131953776, charSequence2, charSequence);
        }
        if (zIsEmpty) {
            return !zIsEmpty2 ? charSequence2 : "";
        }
        return charSequence;
    }

    public final void postToCallbackVisible(boolean z) {
        Iterator it = this.mCarrierTextCallbacks.iterator();
        while (it.hasNext()) {
            CarrierTextCallback carrierTextCallback = (CarrierTextCallback) ((WeakReference) it.next()).get();
            if (carrierTextCallback != null) {
                OplusQSCarrierTextController$$ExternalSyntheticLambda2 oplusQSCarrierTextController$$ExternalSyntheticLambda2 = new OplusQSCarrierTextController$$ExternalSyntheticLambda2(1);
                oplusQSCarrierTextController$$ExternalSyntheticLambda2.f$0 = carrierTextCallback;
                oplusQSCarrierTextController$$ExternalSyntheticLambda2.f$1 = z;
                VarHandle.storeStoreFence();
                this.mMainHandler.post(oplusQSCarrierTextController$$ExternalSyntheticLambda2);
            }
        }
    }

    public final void removeCallback(CarrierTextCallback carrierTextCallback) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        int i = 1;
        int size = this.mCarrierTextCallbacks.size() - 1;
        while (true) {
            copyOnWriteArrayList = this.mCarrierTextCallbacks;
            if (size < 0) {
                break;
            }
            if (((WeakReference) copyOnWriteArrayList.get(size)).get() == carrierTextCallback) {
                this.mCarrierTextCallbacks.remove(size);
            }
            size--;
        }
        if (copyOnWriteArrayList.size() == 0) {
            TelephonyManager telephonyManager = getTelephonyManager();
            if (this.mKeyguardUpdateMonitor != null) {
                OplusQSCarrierTextController$$ExternalSyntheticLambda1 oplusQSCarrierTextController$$ExternalSyntheticLambda1 = new OplusQSCarrierTextController$$ExternalSyntheticLambda1(i);
                oplusQSCarrierTextController$$ExternalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                this.mMainHandler.post(oplusQSCarrierTextController$$ExternalSyntheticLambda1);
                this.mWakefulnessLifecycle.removeObserver(this.mWakefulnessObserver);
            }
            telephonyManager.listen(this.mPhoneStateListener, 0);
            NetworkControllerImpl networkControllerImpl = this.mNetworkController;
            networkControllerImpl.mCallbackHandler.obtainMessage(6, 0, 0, this).sendToTarget();
            networkControllerImpl.removeCallback(this.mSignalCallback);
            this.mIsRegister = false;
        }
    }

    public final void setActiveSubscriptionInfoList() {
        List<UiccCardInfo> uiccCardsInfo;
        List selectableSubscriptionInfoList = SubscriptionManager.from(this.mContext).getSelectableSubscriptionInfoList();
        ArrayList arrayList = null;
        if (selectableSubscriptionInfoList != null && !selectableSubscriptionInfoList.isEmpty() && (uiccCardsInfo = getUiccCardsInfo()) != null && !uiccCardsInfo.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < uiccCardsInfo.size(); i++) {
                UiccCardInfo uiccCardInfo = uiccCardsInfo.get(i);
                if (uiccCardInfo != null) {
                    for (int i2 = 0; i2 < selectableSubscriptionInfoList.size(); i2++) {
                        SubscriptionInfo subscriptionInfo = (SubscriptionInfo) selectableSubscriptionInfoList.get(i2);
                        ArrayList arrayList3 = new ArrayList();
                        Collection<UiccPortInfo> ports = uiccCardInfo.getPorts();
                        if (ports != null) {
                            Iterator<UiccPortInfo> it = ports.iterator();
                            while (it.hasNext()) {
                                String iccId = it.next().getIccId();
                                if (!TextUtils.isEmpty(iccId)) {
                                    arrayList3.add(iccId);
                                }
                            }
                        }
                        if (subscriptionInfo != null) {
                            String iccId2 = subscriptionInfo.getIccId();
                            if (arrayList3.contains(iccId2 == null ? null : iccId2.replaceAll("(?i)f*$", ""))) {
                                arrayList2.add(subscriptionInfo);
                            }
                        }
                    }
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            this.mSubscriptionInfo = new CopyOnWriteArrayList(arrayList);
        }
    }

    public void setEmergencyCallsOnly(boolean z) {
        this.mEmergencyShouldUpdate = z;
        StringBuilder sb = new StringBuilder("setEmergencyCallsOnly mEmergencyShouldUpdate=");
        sb.append(this.mEmergencyShouldUpdate);
        sb.append(" mLastEmergencyShouldUpdate=");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "OplusQSCarrierTextController", sb, this.mLastEmergencyShouldUpdate);
        if (this.mEmergencyShouldUpdate != this.mLastEmergencyShouldUpdate) {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierTextController", "setEmergencyCallsOnly updateCarrierText");
            updateCarrierTextInBgThread(false);
        }
        this.mLastEmergencyShouldUpdate = this.mEmergencyShouldUpdate;
    }

    public void setListening(CarrierTextCallback carrierTextCallback) {
        Iterator it = this.mCarrierTextCallbacks.iterator();
        while (it.hasNext()) {
            if (((CarrierTextCallback) ((WeakReference) it.next()).get()) == carrierTextCallback) {
                return;
            }
        }
        TelephonyManager telephonyManager = getTelephonyManager();
        if (carrierTextCallback != null) {
            this.mCarrierTextCallbacks.add(new WeakReference(carrierTextCallback));
            if (this.mIsRegister) {
                return;
            }
            boolean zIsDataCapable = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).isDataCapable();
            OplusQSCarrierTextController$$ExternalSyntheticLambda0 oplusQSCarrierTextController$$ExternalSyntheticLambda0 = new OplusQSCarrierTextController$$ExternalSyntheticLambda0();
            oplusQSCarrierTextController$$ExternalSyntheticLambda0.f$0 = zIsDataCapable;
            VarHandle.storeStoreFence();
            int i = 0;
            if (((Boolean) DejankUtils.whitelistIpcs(oplusQSCarrierTextController$$ExternalSyntheticLambda0)).booleanValue()) {
                this.mKeyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
                OplusQSCarrierTextController$$ExternalSyntheticLambda1 oplusQSCarrierTextController$$ExternalSyntheticLambda1 = new OplusQSCarrierTextController$$ExternalSyntheticLambda1(i);
                oplusQSCarrierTextController$$ExternalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                this.mMainHandler.post(oplusQSCarrierTextController$$ExternalSyntheticLambda1);
                this.mWakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
                telephonyManager.listen(this.mPhoneStateListener, 4194304);
                this.mIsRegister = true;
            } else {
                this.mKeyguardUpdateMonitor = null;
                carrierTextCallback.updateCarrierInfo(new OplusCarrierTextCallbackInfo("", "", false, false));
                this.mIsRegister = false;
            }
            NetworkControllerImpl networkControllerImpl = this.mNetworkController;
            networkControllerImpl.mCallbackHandler.obtainMessage(6, 1, 0, this).sendToTarget();
            networkControllerImpl.mCallbackHandler.setEmergencyCallsOnly(networkControllerImpl.isEmergencyOnly());
            networkControllerImpl.addCallback(this.mSignalCallback);
        }
    }

    public void setShowDataUsage(boolean z) {
        this.mShowDataUsage = z;
    }

    public final void updateCarrierTextInBgThread(boolean z) {
        OplusQSCarrierTextController$$ExternalSyntheticLambda2 oplusQSCarrierTextController$$ExternalSyntheticLambda2 = new OplusQSCarrierTextController$$ExternalSyntheticLambda2(0);
        oplusQSCarrierTextController$$ExternalSyntheticLambda2.f$0 = this;
        oplusQSCarrierTextController$$ExternalSyntheticLambda2.f$1 = z;
        VarHandle.storeStoreFence();
        AsyncTask.execute(oplusQSCarrierTextController$$ExternalSyntheticLambda2);
    }
}
