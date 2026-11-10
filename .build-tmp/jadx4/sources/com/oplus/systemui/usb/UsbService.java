package com.oplus.systemui.usb;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.OplusActivityManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.provider.Settings;
import android.telephony.OplusOSTelephonyManager;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.slice.widget.RowView$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.internal.telephony.ITelephony;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.common.charge.OplusAddBatteryStatus;
import com.android.systemui.common.charge.OplusBatteryStatus;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.qs.deprecated.PagedTileLayout$2$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.dialog.adapter.ChoiceListAdapter;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.qs.utils.QsOperatorUtils;
import com.oplus.systemui.shutdown.ShutdownBiometricPrompt;
import com.oplus.systemui.usb.receiver.UsbStateReceiver;
import com.oplus.systemui.usb.utils.OplusUsbData;
import com.oplus.systemui.usb.utils.UsbUtils;
import com.oplus.systemui.utils.NotificationConstants;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.UsbFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.util.OpUtils;
import java.lang.invoke.VarHandle;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class UsbService extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public UsbService$$ExternalSyntheticLambda1 mAuthListener;
    public final AnonymousClass5 mBatteryStateChangedListener;
    public Handler mBgHandler;
    public boolean mCanChangePowerRole;
    public CancellationSignal mCancelSignal;
    public UsbService mContext;
    public volatile int mCurrentDataRole;
    public volatile int mCurrentPowerRole;
    public int mCurrentSelectType;
    public int mDefaultDataPhoneId;
    public int mDefaultDataSubscriptionId;
    public boolean mDestroyed;
    public boolean mForceShow;
    public boolean mHasShow;
    public boolean mIsAuthenticating;
    public boolean mIsBigFontScale;
    public volatile boolean mIsCheckSwitchStatus;
    public boolean mIsExchangeUser;
    public volatile boolean mIsFirstShowUsbDialog;
    public boolean mIsGovernmentVersion;
    public boolean mIsSelfFunctionsSet;
    public boolean mIsShowDialogFromNotification;
    public KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public boolean mLastUsbConnected;
    public final AnonymousClass4 mMyHandler;
    public boolean mNeedShowUsbDialog;
    public boolean mNoTransmitting;
    public NotificationManager mNotificationManager;
    public boolean mOcarUsbConnected;
    public AnonymousClass12 mOcarUsbReceiver;
    public final String mOnlyChargeFunction;
    public int mOperate;
    public OplusOSTelephonyManager mOplusOSTelephonyManager;
    public Resources mResources;
    public final AnonymousClass2 mRunnable;
    public int mSelectTypeFromSettings;
    public SharedPreferences mSharedPreferences;
    public ChoiceListAdapter mSingleChoiceListAdapter;
    public SubscriptionManager mSubscriptionManager;
    public volatile boolean mUsbConnected;
    public final Handler mUsbDialogHandler;
    public final List mUsbListData;
    public UsbManager mUsbManager;
    public volatile AlertDialog mUsbSelectDialog;
    public COUIAlertDialogBuilder mUsbSelectDialogBuilder;
    public final AnonymousClass3 mUsbSelectTypeObserver;
    public boolean mUserAuthenticated;
    public UserManager mUserManager;
    public final VzAuthResult mVzAuthResult;
    public final Configuration mLastConfiguration = new Configuration();
    public final AnonymousClass3 mAdbObserver = new AnonymousClass3();
    public final AnonymousClass1 mUsbStateReceiver = new AnonymousClass1();

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends UsbStateReceiver {
        public AnonymousClass1() {
        }

        @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
        public final void onReceive(Context context, Intent intent) {
            boolean z;
            String action = intent.getAction();
            FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("onReceive, and action is: ", action, LogUtil.TAG_COMMON, "UsbService");
            UsbService usbService = UsbService.this;
            if (usbService.mDestroyed) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "Usb service was destroyed");
                return;
            }
            if (TextUtils.isEmpty(action)) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "action is empty");
                return;
            }
            if ("com.oplus.test.show.usbselector.dialog".equals(action)) {
                try {
                    z = !OpUtils.sIsClosedSuperFirewall;
                } catch (Exception e) {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "get isFullFunMode exception = " + e);
                    z = false;
                }
                if (z || usbService.isUsbTransferAllowed()) {
                    usbService.mIsShowDialogFromNotification = true;
                    ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", new StringBuilder("mIsShowDialogFromNotification = "), usbService.mIsShowDialogFromNotification);
                    Handler handler = usbService.mUsbDialogHandler;
                    UsbService$1$$ExternalSyntheticLambda0 usbService$1$$ExternalSyntheticLambda0 = new UsbService$1$$ExternalSyntheticLambda0(0);
                    usbService$1$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    handler.post(usbService$1$$ExternalSyntheticLambda0);
                } else {
                    RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(usbService.mContext, "no_usb_file_transfer", KeyguardUpdateMonitor.getCurrentUser());
                    if (enforcedAdminCheckIfRestrictionEnforced != null && !RestrictedLockUtilsInternal.hasBaseUserRestriction(usbService.mContext, "no_usb_file_transfer", KeyguardUpdateMonitor.getCurrentUser())) {
                        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(usbService.mContext, enforcedAdminCheckIfRestrictionEnforced);
                    }
                }
            } else {
                UsbService.m474$$Nest$mhelpDefineUsbStateReceiver(usbService, context, intent, action);
            }
            usbService.mNeedShowUsbDialog = true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$10, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass10 implements DialogInterface.OnDismissListener {
        public /* synthetic */ UsbService this$0;

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            UsbService usbService = this.this$0;
            usbService.mNeedShowUsbDialog = false;
            usbService.mIsShowDialogFromNotification = false;
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "onDismiss mIsShowDialogFromNotification = " + this.this$0.mIsShowDialogFromNotification);
            NotificationConstants.setsDialogState(NotificationConstants.getsDialogState() & (-2));
            UsbService.adjustFingerprintIconForUsbDialog("onUsbDismiss", true);
            QsOperatorUtils qsOperatorUtils = QsOperatorUtils.INSTANCE;
            if (CustomizeFeatureOption.sIsShowSprintHotSpotDia) {
                QsOperatorUtils.dismissTetheringErrorDialog();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$11, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass11 implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            NotificationConstants.setsDialogState(NotificationConstants.getsDialogState() | 1);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$12, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass12 extends BroadcastReceiver {
        public AnonymousClass12() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "OcarUsbReceiver: " + intent);
            if ("android.ocar.action.CONNECT_STATE".equals(intent.getAction())) {
                UsbService.this.mOcarUsbConnected = intent.getBooleanExtra("usb_connect", false);
                UsbService usbService = UsbService.this;
                if (usbService.mOcarUsbConnected) {
                    Handler handler = usbService.mUsbDialogHandler;
                    UsbService$1$$ExternalSyntheticLambda0 usbService$1$$ExternalSyntheticLambda0 = new UsbService$1$$ExternalSyntheticLambda0(1);
                    usbService$1$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    handler.post(usbService$1$$ExternalSyntheticLambda0);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ UsbService this$0;

        @Override // java.lang.Runnable
        public final void run() {
            StringBuilder sb = new StringBuilder("mLastUsbConnected = ");
            sb.append(this.this$0.mLastUsbConnected);
            sb.append(", mUsbConnected = ");
            sb.append(this.this$0.mUsbConnected);
            sb.append(", mIsExchangeUser = ");
            sb.append(this.this$0.mIsExchangeUser);
            sb.append(", currentSelectType = ");
            sb.append(this.this$0.mCurrentSelectType);
            sb.append(", isSelfFunctionsSet  = ");
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", sb, this.this$0.mIsSelfFunctionsSet);
            if (this.this$0.mUsbConnected) {
                UsbService usbService = this.this$0;
                if (!usbService.mLastUsbConnected) {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isSupportReverseCharging() && this.this$0.mCurrentDataRole == 1) {
                        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "source to sink    return");
                        return;
                    } else {
                        UsbService usbService2 = this.this$0;
                        UsbService.m475$$Nest$monUsbConnected(usbService2, usbService2.mContext);
                    }
                } else {
                    if (usbService.mIsSelfFunctionsSet) {
                        usbService.mIsSelfFunctionsSet = false;
                        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "mIsSelfFunctionsSet return");
                        return;
                    }
                    int iUsbFunctionsToSelectType = usbService.usbFunctionsToSelectType(usbService.mUsbManager.getCurrentFunctions());
                    if (iUsbFunctionsToSelectType == -1) {
                        iUsbFunctionsToSelectType = 0;
                    }
                    UsbService usbService3 = this.this$0;
                    if (usbService3.mIsExchangeUser) {
                        usbService3.mIsExchangeUser = false;
                    }
                    if (usbService3.mCurrentSelectType == iUsbFunctionsToSelectType) {
                        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "mCurrentSelectType==currentSelectType  return");
                        return;
                    }
                    usbService3.mCurrentSelectType = iUsbFunctionsToSelectType;
                    usbService3.mNeedShowUsbDialog = false;
                    usbService3.saveSelectTypeIfNeeded(iUsbFunctionsToSelectType);
                    UsbService usbService4 = this.this$0;
                    usbService4.updateAdbNotification(usbService4.mContext);
                    UsbService usbService5 = this.this$0;
                    usbService5.updateUsbNotification(iUsbFunctionsToSelectType, usbService5.mContext);
                }
            }
            UsbService usbService6 = this.this$0;
            usbService6.mLastUsbConnected = usbService6.mUsbConnected;
            if (this.this$0.mUsbConnected) {
                return;
            }
            this.this$0.destroyUsbService();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements OplusBatteryController.IBatteryStateChanged {
        public /* synthetic */ UsbService this$0;

        @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
        public final void onAdditionalBatteryStateChanged(OplusAddBatteryStatus oplusAddBatteryStatus) {
            UsbService usbService = this.this$0;
            int i = UsbService.$r8$clinit;
            usbService.mUsbConnected = usbService.isUsbConnected();
            StringBuilder sb = new StringBuilder("onBatteryStateChanged mUsbConnected:");
            sb.append(this.this$0.mUsbConnected);
            sb.append("  ,mCurrentPowerRole:");
            sb.append(this.this$0.mCurrentPowerRole);
            sb.append("  ,mIsFirstShowUsbDialog:");
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", sb, this.this$0.mIsFirstShowUsbDialog);
            if (this.this$0.mUsbConnected) {
                this.this$0.mNeedShowUsbDialog = true;
                FeatureOption.INSTANCE.getClass();
                boolean zIsSupportReverseCharging = FeatureOption.isSupportReverseCharging();
                UsbService usbService2 = this.this$0;
                if (!zIsSupportReverseCharging) {
                    usbService2.onServiceUpdated(0L);
                } else if (usbService2.mCurrentPowerRole == -1 || !this.this$0.mIsFirstShowUsbDialog) {
                    this.this$0.onServiceUpdated(0L);
                }
            }
        }

        @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
        public final void onBatteryStateChanged(int i) {
        }

        @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
        public final void onBatteryStateChanged(OplusBatteryStatus oplusBatteryStatus) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass7 {
        public /* synthetic */ UsbService this$0;
        public /* synthetic */ boolean val$force;
        public /* synthetic */ int val$selectType;
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass8 implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass9 implements DialogInterface.OnClickListener {
        public /* synthetic */ UsbService this$0;
        public /* synthetic */ AnonymousClass7 val$onItemSelectListener;

        /* JADX WARN: Code duplicated, block: B:28:0x0086  */
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            boolean zIsATTHotspotUnAuth;
            if (this.val$onItemSelectListener != null) {
                if (i < 0 || i >= this.this$0.mUsbListData.size()) {
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onClick index out of bounds: which=", ", size=");
                    sbM.append(this.this$0.mUsbListData.size());
                    LogUtil.e(LogUtil.TAG_COMMON, "UsbService", sbM.toString());
                    return;
                }
                OplusUsbData oplusUsbData = (OplusUsbData) this.this$0.mUsbListData.get(i);
                if (oplusUsbData != null) {
                    UsbService usbService = this.val$onItemSelectListener.this$0;
                    int type = oplusUsbData.getType();
                    int i2 = UsbService.$r8$clinit;
                    if (3 == type) {
                        boolean z = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing;
                        QsOperatorUtils qsOperatorUtils = QsOperatorUtils.INSTANCE;
                        if (CustomizeFeatureOption.sIsShowSprintHotSpotDia) {
                            zIsATTHotspotUnAuth = QsOperatorUtils.isSprintHotspotUnAuth(usbService.mContext, !usbService.hasInsertSimCard(), 1, z);
                        } else {
                            int i3 = usbService.mDefaultDataPhoneId;
                            if (!CustomizeFeatureOption.checkSlotIdValid(i3) ? false : CustomizeFeatureOption.sVZWTethering[i3]) {
                                zIsATTHotspotUnAuth = QsOperatorUtils.isVZWHotspotUnAuth(usbService.mContext, usbService.mVzAuthResult, usbService.mDefaultDataSubscriptionId, 1, z);
                            } else {
                                int i4 = usbService.mDefaultDataPhoneId;
                                if ((!CustomizeFeatureOption.checkSlotIdValid(i4) ? -1 : CustomizeFeatureOption.sTetheringEntitlement[i4]) != -1) {
                                    zIsATTHotspotUnAuth = QsOperatorUtils.isATTHotspotUnAuth(usbService.mContext, 0, usbService.mDefaultDataPhoneId);
                                } else {
                                    zIsATTHotspotUnAuth = false;
                                }
                            }
                        }
                    } else {
                        zIsATTHotspotUnAuth = false;
                    }
                    if (zIsATTHotspotUnAuth) {
                        return;
                    }
                    AnonymousClass7 anonymousClass7 = this.val$onItemSelectListener;
                    UsbService usbService2 = anonymousClass7.this$0;
                    int type2 = oplusUsbData.getType();
                    boolean z2 = anonymousClass7.val$force;
                    int i5 = anonymousClass7.val$selectType;
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbService", TextFieldBuffer$.ExternalSyntheticOutline0.m(type2, i5, "change usb mode  new UsbMode = ", ", old UsbMode = "));
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isSupportReverseCharging()) {
                        if (type2 == 5 && !usbService2.mCanChangePowerRole) {
                            usbService2.delayDismiss();
                            if (usbService2.mCurrentPowerRole == 1) {
                                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "checkUsbModeChangeAllowed getPowerRole is source return");
                            } else {
                                UsbService usbService3 = usbService2.mContext;
                                Toast.makeText(usbService3, usbService3.getString(2131957072), 0).show();
                                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "checkUsbModeChangeAllowed usb_not_support_reverse_charging return");
                                usbService2.mOperate = 2;
                                usbService2.savePoint("fail", "sink to source canChangePowerRole false");
                            }
                        } else if (type2 == 0 && !usbService2.mCanChangePowerRole) {
                            if (usbService2.mCurrentPowerRole == 2) {
                                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "checkUsbModeChangeAllowed getPowerRole is sink return true");
                            } else {
                                usbService2.delayDismiss();
                                UsbService usbService4 = usbService2.mContext;
                                Toast.makeText(usbService4, usbService4.getString(2131957069), 0).show();
                                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "checkUsbModeChangeAllowed usb_not_support_charge return");
                                usbService2.mOperate = 1;
                                usbService2.savePoint("fail", "source to sink canChangePowerRole false");
                            }
                        }
                        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "changeUsbMode checkUsbModeChangeAllowed return");
                        return;
                    }
                    UsbService$.ExternalSyntheticLambda16 externalSyntheticLambda16 = new UsbService$.ExternalSyntheticLambda16();
                    externalSyntheticLambda16.f$0 = usbService2;
                    externalSyntheticLambda16.f$1 = type2;
                    externalSyntheticLambda16.f$2 = z2;
                    externalSyntheticLambda16.f$3 = i5;
                    VarHandle.storeStoreFence();
                    UsbService$.ExternalSyntheticLambda15 externalSyntheticLambda15 = new UsbService$.ExternalSyntheticLambda15(1);
                    externalSyntheticLambda15.f$0 = usbService2;
                    externalSyntheticLambda15.f$1 = i5;
                    VarHandle.storeStoreFence();
                    UsbService$$ExternalSyntheticLambda1 usbService$$ExternalSyntheticLambda1 = usbService2.mAuthListener;
                    if (usbService$$ExternalSyntheticLambda1 != null) {
                        UsbService usbService5 = usbService$$ExternalSyntheticLambda1.f$0;
                        if (!FeatureOption.isExpRegion()) {
                            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "not export region, no need to auth");
                        } else if (type2 == 0) {
                            PagedTileLayout$2$.ExternalSyntheticOutline0.m(type2, "select mode is only_charge usbMode = ", LogUtil.TAG_COMMON, "UsbService");
                        } else if (FeatureOption.isSupportReverseCharging() && type2 == 5) {
                            PagedTileLayout$2$.ExternalSyntheticOutline0.m(type2, "select mode is reverse_charging usbMode = ", LogUtil.TAG_COMMON, "UsbService");
                        } else if (usbService5.mUserAuthenticated) {
                            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "already authenticated");
                        } else {
                            int iIntValue = ShutdownBiometricPrompt.canAuthenticate(usbService5.mContext).intValue();
                            if (iIntValue == 0) {
                                usbService5.mIsAuthenticating = true;
                                usbService5.mCancelSignal = new CancellationSignal();
                                ShutdownBiometricPrompt shutdownBiometricPromptCreate = ShutdownBiometricPrompt.create(usbService5.mContext);
                                boolean zIsUnlockingWithBiometricAllowed = usbService5.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed();
                                CancellationSignal cancellationSignal = usbService5.mCancelSignal;
                                6 r1 = new 6();
                                r1.this$0 = usbService5;
                                r1.val$action = externalSyntheticLambda16;
                                r1.val$recoverAction = externalSyntheticLambda15;
                                VarHandle.storeStoreFence();
                                shutdownBiometricPromptCreate.authenticate(zIsUnlockingWithBiometricAllowed, cancellationSignal, r1);
                                return;
                            }
                            LogUtil.w(LogUtil.TAG_COMMON, "UsbService", "canAuthenticate error result = " + iIntValue);
                            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "authenticate unavailable");
                        }
                    }
                    externalSyntheticLambda16.run();
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CancelNotificationHandler implements Runnable {
        public final int mId;
        public final SoftReference mManagerSoftReference;

        public CancelNotificationHandler(NotificationManager notificationManager, int i) {
            this.mId = i;
            this.mManagerSoftReference = new SoftReference(notificationManager);
        }

        @Override // java.lang.Runnable
        public final void run() {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "CancelNotificationHandler:" + this.mManagerSoftReference.get());
            if (this.mManagerSoftReference.get() != null) {
                ((NotificationManager) this.mManagerSoftReference.get()).cancel(this.mId);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PostNotificationHandler implements Runnable {
        public final int mId;
        public final SoftReference mManagerSoftReference;
        public final SoftReference mNotificationSoftReference;

        public PostNotificationHandler(NotificationManager notificationManager, Notification notification, int i) {
            this.mId = i;
            this.mManagerSoftReference = new SoftReference(notificationManager);
            this.mNotificationSoftReference = new SoftReference(notification);
        }

        @Override // java.lang.Runnable
        public final void run() {
            StringBuilder sb = new StringBuilder("on posting USB notification: notificationmanager reference null is  ");
            sb.append(this.mManagerSoftReference.get() == null);
            sb.append("; notification reference null is ");
            ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_COMMON, "UsbService", sb, this.mNotificationSoftReference.get() == null);
            if (this.mManagerSoftReference.get() == null || this.mNotificationSoftReference.get() == null) {
                return;
            }
            ((NotificationManager) this.mManagerSoftReference.get()).notify(this.mId, (Notification) this.mNotificationSoftReference.get());
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "USB notification is posted!");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VzAuthResult implements QsOperatorUtils.MhsAuthResultCallback {
        public /* synthetic */ UsbService this$0;

        @Override // com.oplus.systemui.qs.utils.QsOperatorUtils.MhsAuthResultCallback
        public final void onAuthResult(boolean z) {
            Handler handler = this.this$0.mUsbDialogHandler;
            if (z) {
                UsbService$VzAuthResult$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new UsbService$VzAuthResult$.ExternalSyntheticLambda0(0);
                externalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                handler.post(externalSyntheticLambda0);
                return;
            }
            UsbService$VzAuthResult$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new UsbService$VzAuthResult$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(externalSyntheticLambda1);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhelpDefineUsbStateReceiver, reason: not valid java name */
    public static void m474$$Nest$mhelpDefineUsbStateReceiver(UsbService usbService, Context context, Intent intent, String str) {
        int remeberSelectType = usbService.getRemeberSelectType();
        usbService.mNeedShowUsbDialog = false;
        str.hashCode();
        switch (str) {
            case "com.oplus.test.usb.tethering":
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "change from " + remeberSelectType + "to usb tethering");
                usbService.saveSelectTypeIfNeeded(3);
                usbService.updateUsbNotification(3, context);
                usbService.changeUsbConfig(3, context);
                UsbFeatureOption.INSTANCE.getClass();
                if (UsbFeatureOption.isUsbServiceForCta) {
                    usbService.updateAdbNotification(context);
                    break;
                }
                break;
            case "com.oplus.test.file.transmit":
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", ListPopupWindow$.ExternalSyntheticOutline0.m(remeberSelectType, "ACTION_FILE_TRANSMIT, selectType = ", " , mNoTransmitting = "), usbService.mNoTransmitting);
                if (usbService.mNoTransmitting) {
                    usbService.saveSelectTypeIfNeeded(1);
                    usbService.mNoTransmitting = false;
                    usbService.mMyHandler.sendEmptyMessageDelayed(2, 1000L);
                    usbService.changeUsbConfig(1, context);
                    usbService.updateUsbNotification(1, context);
                }
                UsbFeatureOption.INSTANCE.getClass();
                if (UsbFeatureOption.isUsbServiceForCta) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbService", "updateAdbNotificationAdv_becase has configed the feature:oplus.cta.support");
                    usbService.updateAdbNotification(context);
                    break;
                }
                break;
            case "android.intent.action.CLOSE_SYSTEM_DIALOGS":
                String stringExtra = intent.getStringExtra("reason");
                if (stringExtra != null) {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "ACTION_CLOSE_SYSTEM_DIALOGS ,reason = ".concat(stringExtra));
                    Handler handler = usbService.mUsbDialogHandler;
                    UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda0 = new UsbService$$ExternalSyntheticLambda0(2);
                    usbService$$ExternalSyntheticLambda0.f$0 = usbService;
                    VarHandle.storeStoreFence();
                    handler.post(usbService$$ExternalSyntheticLambda0);
                    break;
                }
                break;
            case "com.oplus.test.only.charge":
                FeatureOption.INSTANCE.getClass();
                boolean zIsSupportReverseCharging = FeatureOption.isSupportReverseCharging();
                if (!zIsSupportReverseCharging) {
                    usbService.saveSelectTypeIfNeeded(0);
                }
                usbService.changeUsbConfig(0, context);
                if (!zIsSupportReverseCharging) {
                    usbService.updateUsbNotification(0, context);
                }
                if (Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) == 0) {
                    UsbFeatureOption.INSTANCE.getClass();
                    if (UsbFeatureOption.isUsbServiceForCta) {
                        usbService.updateAdbNotification(context);
                    }
                    break;
                }
                break;
            case "com.oplus.test.midi":
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "change from " + remeberSelectType + "to midi");
                usbService.saveSelectTypeIfNeeded(4);
                usbService.updateUsbNotification(4, context);
                usbService.changeUsbConfig(4, context);
                UsbFeatureOption.INSTANCE.getClass();
                if (UsbFeatureOption.isUsbServiceForCta) {
                    usbService.updateAdbNotification(context);
                    break;
                }
                break;
            case "android.intent.action.TIME_SET":
                if (usbService.mUsbConnected) {
                    if (!OpUtils.sIsClosedSuperFirewall) {
                        usbService.mIsBigFontScale = context.getResources().getConfiguration().fontScale >= 1.3f;
                        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", ListPopupWindow$.ExternalSyntheticOutline0.m(remeberSelectType, "size change selectType == ", "; mIsBigFontScale "), usbService.mIsBigFontScale);
                        if (usbService.mNotificationManager == null) {
                            usbService.mNotificationManager = (NotificationManager) usbService.mContext.getSystemService("notification");
                        }
                        usbService.mNotificationManager.cancel(10004);
                        usbService.mNotificationManager.cancel(10005);
                        usbService.updateAdbNotification(context);
                        usbService.updateUsbNotification(remeberSelectType, context);
                    } else {
                        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "Closed Super Firewall, No ops on config changed");
                    }
                    break;
                } else {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "configuration change while usb disconnected");
                    break;
                }
                break;
            case "com.oplus.test.image.transmit":
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", ListPopupWindow$.ExternalSyntheticOutline0.m(remeberSelectType, "ACTION_IMAGE_TRANSMIT, selectType = ", " , mNoTransmitting= "), usbService.mNoTransmitting);
                if (usbService.mNoTransmitting) {
                    usbService.saveSelectTypeIfNeeded(2);
                    usbService.mNoTransmitting = false;
                    usbService.mMyHandler.sendEmptyMessageDelayed(2, 1000L);
                    usbService.changeUsbConfig(2, context);
                    usbService.updateUsbNotification(2, context);
                }
                UsbFeatureOption.INSTANCE.getClass();
                if (UsbFeatureOption.isUsbServiceForCta) {
                    usbService.updateAdbNotification(context);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: -$$Nest$monUsbConnected, reason: not valid java name */
    public static void m475$$Nest$monUsbConnected(UsbService usbService, UsbService usbService2) {
        if (SystemProperties.get("ro.oplus.factory_mode", "0").equals("1")) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "isAtoVersion on");
            return;
        }
        UsbService usbService3 = usbService.mContext;
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "registerUSBSelectTypeObserver");
        ContentResolver contentResolver = usbService3.getContentResolver();
        contentResolver.registerContentObserver(Settings.System.getUriFor("usb_remeber_selection"), false, usbService.mUsbSelectTypeObserver);
        usbService.mSelectTypeFromSettings = Settings.System.getInt(contentResolver, "usb_remeber_selection", 1);
        int defaultSelectType = usbService.getDefaultSelectType();
        usbService.saveSelectTypeIfNeeded(defaultSelectType);
        usbService.mCurrentSelectType = defaultSelectType;
        usbService.updateAdbNotification(usbService2);
        usbService.updateUsbNotification(defaultSelectType, usbService2);
    }

    /* JADX INFO: renamed from: -$$Nest$mswitchPowerRoleTimeOut, reason: not valid java name */
    public static void m476$$Nest$mswitchPowerRoleTimeOut(UsbService usbService, int i, String str) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging()) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", str);
            usbService.mIsCheckSwitchStatus = true;
            usbService.mNeedShowUsbDialog = false;
            UsbService usbService2 = usbService.mContext;
            Toast.makeText(usbService2, usbService2.getString(i), 0).show();
            usbService.savePoint("fail", str);
            usbService.updateUsbNotification(usbService.getRemeberSelectType(), usbService.mContext);
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.oplus.systemui.usb.UsbService$4] */
    public UsbService() {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mRunnable = anonymousClass2;
        this.mUsbSelectTypeObserver = new AnonymousClass3(new Handler(Looper.getMainLooper()));
        this.mCanChangePowerRole = false;
        this.mCurrentPowerRole = -1;
        this.mCurrentDataRole = -1;
        this.mIsCheckSwitchStatus = true;
        this.mIsFirstShowUsbDialog = false;
        this.mNeedShowUsbDialog = true;
        this.mIsBigFontScale = false;
        this.mUsbConnected = false;
        this.mLastUsbConnected = false;
        this.mNoTransmitting = true;
        this.mOplusOSTelephonyManager = null;
        this.mHasShow = false;
        this.mIsGovernmentVersion = false;
        this.mUsbListData = Collections.synchronizedList(new ArrayList());
        this.mOnlyChargeFunction = "midi";
        this.mUsbDialogHandler = new Handler(Looper.getMainLooper());
        this.mIsShowDialogFromNotification = false;
        this.mOcarUsbConnected = false;
        this.mOperate = -1;
        this.mIsExchangeUser = false;
        this.mSelectTypeFromSettings = 1;
        this.mIsSelfFunctionsSet = false;
        this.mUserAuthenticated = false;
        this.mIsAuthenticating = false;
        this.mMyHandler = new Handler(Looper.getMainLooper()) { // from class: com.oplus.systemui.usb.UsbService.4
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                if (i != 1) {
                    if (i == 2) {
                        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", " mNoTransmitting");
                        UsbService.this.mNoTransmitting = true;
                        return;
                    } else if (i == 3) {
                        UsbService.m476$$Nest$mswitchPowerRoleTimeOut(UsbService.this, 2131957069, "source to sink timeout");
                        return;
                    } else {
                        if (i != 4) {
                            return;
                        }
                        UsbService.m476$$Nest$mswitchPowerRoleTimeOut(UsbService.this, 2131957072, "sink to source timeout");
                        return;
                    }
                }
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", new StringBuilder("two:display adb debug notify! and mUsbConnected = "), UsbService.this.mUsbConnected);
                if (UsbService.this.mUsbConnected) {
                    UsbService usbService = UsbService.this;
                    if (usbService.mNotificationManager == null) {
                        usbService.mNotificationManager = (NotificationManager) usbService.mContext.getSystemService("notification");
                        return;
                    }
                    if (usbService.mIsGovernmentVersion && Settings.Secure.getInt(usbService.mContext.getContentResolver(), "ZQ_ADB_ENABLED", 1) == 0) {
                        return;
                    }
                    UsbService usbService2 = UsbService.this;
                    if (usbService2.mBgHandler == null) {
                        usbService2.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
                    }
                    UsbService usbService3 = UsbService.this;
                    usbService3.mBgHandler.post(new PostNotificationHandler(usbService3.mNotificationManager, (Notification) message.obj, 10004));
                }
            }
        };
        VzAuthResult vzAuthResult = new VzAuthResult();
        vzAuthResult.this$0 = this;
        VarHandle.storeStoreFence();
        this.mVzAuthResult = vzAuthResult;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mBatteryStateChangedListener = anonymousClass5;
    }

    public static void adjustFingerprintIconForUsbDialog(String str, boolean z) {
        if (Dependency.sDependency.getDependencyInner(KeyguardStateController.class) == null || ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
            return;
        }
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().showOrHideFingerprintIconTemporarily(z, str);
    }

    private int[] getChoiceListAdapterIcons() {
        List list = this.mUsbListData;
        return list == null ? new int[0] : list.stream().mapToInt(new UsbService$$ExternalSyntheticLambda8()).toArray();
    }

    private CharSequence[] getChoiceListAdapterItems() {
        List list = this.mUsbListData;
        return list == null ? new CharSequence[0] : (CharSequence[]) list.stream().map(new UsbService$$ExternalSyntheticLambda5()).filter(new UsbService$$ExternalSyntheticLambda6()).toArray(new UsbService$$ExternalSyntheticLambda7());
    }

    private int getDefaultSelectType() {
        UsbFeatureOption.INSTANCE.getClass();
        if (UsbFeatureOption.isUsbServiceForCta) {
            return 2;
        }
        String customUSBPolicy = UsbUtils.getCustomUSBPolicy();
        if (customUSBPolicy == null || customUSBPolicy.equals("unlimited")) {
            if (UsbUtils.isDisableUSBDialog()) {
                return 1;
            }
            return usbFunctionsToSelectType(this.mUsbManager.getScreenUnlockedFunctions());
        }
        if (customUSBPolicy.equals("none")) {
            return 0;
        }
        return customUSBPolicy.equals("mtp") ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRemeberSelectType() {
        try {
            return this.mSelectTypeFromSettings;
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "UsbService", "get USB_REMEBER_SELECTION  error e3 =" + e);
            return 1;
        }
    }

    public final void changeUsbConfig(int i, Context context) {
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "changeUsbConfig_select_type = " + i);
        if ((isUsbNCMMode() || this.mOcarUsbConnected) && !this.mIsShowDialogFromNotification) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "Ignore change Usb Config for NCM mode");
            return;
        }
        if (this.mUsbManager == null) {
            this.mUsbManager = (UsbManager) context.getSystemService("usb");
        }
        if (this.mBgHandler == null) {
            this.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        }
        Handler handler = this.mBgHandler;
        UsbService$.ExternalSyntheticLambda15 externalSyntheticLambda15 = new UsbService$.ExternalSyntheticLambda15(0);
        externalSyntheticLambda15.f$0 = this;
        externalSyntheticLambda15.f$1 = i;
        VarHandle.storeStoreFence();
        handler.post(externalSyntheticLambda15);
    }

    public final void delayDismiss() {
        Handler handler = this.mUsbDialogHandler;
        UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda0 = new UsbService$$ExternalSyntheticLambda0(0);
        usbService$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(usbService$$ExternalSyntheticLambda0, 200L);
    }

    public final void destroyUsbService() {
        this.mUserAuthenticated = false;
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancel(10005);
            this.mNotificationManager.cancel(10004);
        }
        stopSelf();
        unRegisterAdbStateObservers();
        unregisterUSBSelectTypeObserver(this.mContext);
    }

    public final int getChoiceListCheckedIndex(int i) {
        List list = this.mUsbListData;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.mUsbListData.size(); i2++) {
                if (((OplusUsbData) this.mUsbListData.get(i2)).getType() == i) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public final boolean hasInsertSimCard() {
        FeatureOption.INSTANCE.getClass();
        boolean zHasIccCard = false;
        if (FeatureOption.isMtkGeminiSupport() || FeatureOption.isQcomGeminiSupport()) {
            boolean zIsSimInserted = isSimInserted(0);
            boolean zIsSimInserted2 = isSimInserted(1);
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("dulcard, and isSIM1Active = ", ", isSIM2Active = ", zIsSimInserted, zIsSimInserted2));
            if (zIsSimInserted || zIsSimInserted2) {
                zHasIccCard = true;
            }
        } else {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "SingleCard");
            try {
                zHasIccCard = ITelephony.Stub.asInterface(ServiceManager.getService("phone")).hasIccCard();
            } catch (RemoteException unused) {
                LogUtil.e(LogUtil.TAG_COMMON, "UsbService", "RemoteException2");
            }
        }
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isSIM1Insert = ", LogUtil.TAG_COMMON, "UsbService", zHasIccCard);
        return zHasIccCard;
    }

    public final boolean isSimInserted(int i) {
        if (this.mOplusOSTelephonyManager == null) {
            this.mOplusOSTelephonyManager = OplusOSTelephonyManager.getDefault(this.mContext);
        }
        boolean zHasIccCardGemini = this.mOplusOSTelephonyManager.hasIccCardGemini(i);
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", KeyguardLogger$.ExternalSyntheticOutline0.m("isSimInserted(", i, "), SimInserted=", zHasIccCardGemini));
        return zHasIccCardGemini;
    }

    public final boolean isUsbConnected() {
        boolean z = OplusBatteryController.getInstance().getPlugged() == 2;
        if (z != this.mUsbConnected) {
            this.mLastUsbConnected = this.mUsbConnected;
            this.mUsbConnected = z;
        }
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isUsbConnected() returned: ", " ,mLastUsbConnected:", z);
        sbM.append(this.mLastUsbConnected);
        sbM.append(" ,mUsbConnected:");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", sbM, this.mUsbConnected);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging() && this.mCurrentPowerRole == 1) {
            return true;
        }
        return z;
    }

    public final boolean isUsbNCMMode() {
        if (this.mUsbManager == null) {
            this.mUsbManager = (UsbManager) this.mContext.getSystemService("usb");
        }
        boolean zContains = UsbManager.usbFunctionsToString(this.mUsbManager.getCurrentFunctions()).toLowerCase().contains("ncm");
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isNCMMode = ", LogUtil.TAG_COMMON, "UsbService", zContains);
        return zContains;
    }

    public final boolean isUsbTransferAllowed() {
        if (this.mUserManager == null) {
            this.mUserManager = (UserManager) getSystemService("user");
        }
        boolean z = this.mUsbManager == null || !this.mUserManager.hasUserRestriction("no_usb_file_transfer");
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isUsbTransferAllowed = ", LogUtil.TAG_COMMON, "UsbService", z);
        return z;
    }

    public final boolean isZQVersionUsbEnable() {
        return this.mIsGovernmentVersion && Settings.Secure.getInt(this.mContext.getContentResolver(), "MTP_TRANSFER_ENABLED", 1) == 0;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0092, code lost:
    
        if (r4.mCurrentDataRole == 1) goto L40;
     */
    @Override // android.app.Service, android.content.ComponentCallbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onConfigurationChanged(android.content.res.Configuration r5) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.usb.UsbService.onConfigurationChanged(android.content.res.Configuration):void");
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.mDestroyed = false;
        this.mContext = this;
        Resources resources = getResources();
        this.mResources = resources;
        this.mLastConfiguration.setTo(resources.getConfiguration());
        OplusUsbBackend.getInstance(this.mContext).setIsServiceRunning(true);
        setTheme(2132019084);
        COUIThemeOverlay.getInstance().applyThemeOverlays(this);
        this.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        this.mUsbManager = (UsbManager) getSystemService("usb");
        this.mIsBigFontScale = this.mResources.getConfiguration().fontScale >= 1.3f;
        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "oncreate() isbigFrontScale = " + this.mIsBigFontScale);
        this.mIsGovernmentVersion = getPackageManager().hasSystemFeature("oplus.business.custom");
        this.mUserManager = (UserManager) getSystemService("user");
        this.mUsbConnected = isUsbConnected();
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "initOcarUsbReceiver");
        this.mOcarUsbReceiver = new AnonymousClass12();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.ocar.action.CONNECT_STATE");
        this.mContext.registerReceiver(this.mOcarUsbReceiver, intentFilter, 2);
        this.mUsbStateReceiver.register(this.mContext, UserHandle.CURRENT, (Handler) null);
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "registerAdbContentObservers");
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor("adb_enabled"), this.mAdbObserver);
        if (this.mIsGovernmentVersion) {
            ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor("MTP_TRANSFER_ENABLED"), this.mAdbObserver);
        }
        OplusBatteryController.getInstance().registerIBatteryStateChanged(this.mBatteryStateChangedListener);
        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "onCreate_select_type = " + getRemeberSelectType());
        if (!isUsbTransferAllowed()) {
            saveSelectTypeIfNeeded(0);
            this.mCurrentSelectType = 0;
        }
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("com.oplus.usbselection_shared_prefs", 0);
        this.mSharedPreferences = sharedPreferences;
        this.mHasShow = sharedPreferences.getBoolean("hasShow", false);
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_COMMON, "UsbService", new StringBuilder("onCreate_mHasShow = "), this.mHasShow);
        if (this.mKeyguardUpdateMonitor == null) {
            this.mKeyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        }
        if (this.mAuthListener == null) {
            UsbService$$ExternalSyntheticLambda1 usbService$$ExternalSyntheticLambda1 = new UsbService$$ExternalSyntheticLambda1();
            usbService$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.mAuthListener = usbService$$ExternalSyntheticLambda1;
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        Handler handler;
        super.onDestroy();
        this.mDestroyed = true;
        OplusUsbBackend.getInstance(this.mContext).setIsServiceRunning(false);
        OplusUsbBackend.getInstance(this.mContext).setServiceCurrentDataRole(0);
        stopForeground(true);
        this.mAuthListener = null;
        CancellationSignal cancellationSignal = this.mCancelSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            this.mCancelSignal = null;
            this.mIsAuthenticating = false;
        }
        AnonymousClass4 anonymousClass4 = this.mMyHandler;
        if (anonymousClass4 != null) {
            anonymousClass4.removeCallbacksAndMessages(null);
        }
        if (ThreadManager.getInstance().getMainHandler().hasCallbacks(this.mRunnable)) {
            ThreadManager.getInstance().getMainHandler().removeCallbacks(this.mRunnable);
        }
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        }
        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "onDestroy_mNotificationManager = " + this.mNotificationManager + ", and mUsbStateReceiver = " + this.mUsbStateReceiver + " ,mBgHandler:" + this.mBgHandler);
        this.mUsbStateReceiver.unregister(this.mContext);
        OplusBatteryController.getInstance().unregisterIBatteryStateChanged(this.mBatteryStateChangedListener);
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "unRegisterOcarUsbReceiver");
        this.mOcarUsbConnected = false;
        AnonymousClass12 anonymousClass12 = this.mOcarUsbReceiver;
        if (anonymousClass12 != null) {
            this.mContext.unregisterReceiver(anonymousClass12);
        }
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null && (handler = this.mBgHandler) != null) {
            handler.post(new CancelNotificationHandler(notificationManager, 10004));
            this.mBgHandler.post(new CancelNotificationHandler(this.mNotificationManager, 10005));
            this.mNotificationManager = null;
        }
        this.mUsbDialogHandler.removeCallbacksAndMessages(null);
        Handler handler2 = this.mUsbDialogHandler;
        UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda0 = new UsbService$$ExternalSyntheticLambda0(1);
        usbService$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler2.post(usbService$$ExternalSyntheticLambda0);
        unRegisterAdbStateObservers();
        unregisterUSBSelectTypeObserver(this.mContext);
    }

    public final void onServiceUpdated(long j) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging() && !this.mIsCheckSwitchStatus && !this.mIsExchangeUser) {
            ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_COMMON, "UsbService", new StringBuilder("onServiceUpdated  return mIsCheckSwitchStatus:"), this.mIsCheckSwitchStatus);
            return;
        }
        if (ThreadManager.getInstance().getMainHandler().hasCallbacks(this.mRunnable)) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", " onServiceUpdated removeCallbacks");
            ThreadManager.getInstance().getMainHandler().removeCallbacks(this.mRunnable);
        }
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", " onServiceUpdated postDelayed " + j);
        ThreadManager.getInstance().getMainHandler().postDelayed(this.mRunnable, j);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        boolean booleanExtra;
        boolean booleanExtra2;
        int intExtra;
        int intExtra2;
        if (intent != null) {
            this.mIsExchangeUser = intent.getBooleanExtra("isExchangeUser", false);
            booleanExtra = intent.getBooleanExtra("updatePowerState", false);
        } else {
            booleanExtra = false;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging()) {
            if (intent != null) {
                booleanExtra2 = intent.getBooleanExtra("canChangePowerRole", false);
                intExtra = intent.getIntExtra("currentPowerRole", 0);
                intExtra2 = intent.getIntExtra("currentDataRole", 0);
            } else {
                booleanExtra2 = false;
                intExtra = 0;
                intExtra2 = 0;
            }
            StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("onStartCommand  canChangePowerRole:", intExtra, "  ,currentPowerRole:", "  ,currentDataRole:", booleanExtra2);
            sbM.append(intExtra2);
            sbM.append("  ,mUsbConnected:");
            KeyguardLogger$.ExternalSyntheticOutline0.m("  ,updatePowerState:", "   ,mCurrentPowerRole:", sbM, this.mUsbConnected, booleanExtra);
            sbM.append(this.mCurrentPowerRole);
            sbM.append("   ,mCurrentDataRole:");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mCurrentDataRole, LogUtil.TAG_COMMON, "UsbService", sbM);
            OplusUsbBackend.getInstance(this.mContext).setServiceCurrentDataRole(intExtra2);
            if (booleanExtra) {
                if (ThreadManager.getInstance().getMainHandler().hasCallbacks(this.mRunnable)) {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbService", " checkSwitchStatus removeCallbacks");
                    ThreadManager.getInstance().getMainHandler().removeCallbacks(this.mRunnable);
                }
                if (intExtra == 0) {
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "isSwitchFailed  powerRole is none  fail");
                } else if (this.mCurrentPowerRole == intExtra) {
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "isSwitchFailed mCurrentPowerRole = currentPowerRole  fail");
                } else {
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "handleSwitchSuccess");
                    if (intExtra == 1) {
                        this.mCurrentSelectType = 5;
                        this.mUsbConnected = intExtra2 == 2;
                    } else if (intExtra == 2) {
                        this.mCurrentSelectType = 0;
                    }
                    delayDismiss();
                    this.mIsCheckSwitchStatus = true;
                    this.mCanChangePowerRole = booleanExtra2;
                    this.mCurrentPowerRole = intExtra;
                    this.mCurrentDataRole = intExtra2;
                    this.mNeedShowUsbDialog = false;
                    saveSelectTypeIfNeeded(this.mCurrentSelectType);
                    savePoint("success", "");
                    removeMessages(3);
                    removeMessages(4);
                    updateAdbNotification(this.mContext);
                    updateUsbNotification(getRemeberSelectType(), this.mContext);
                }
                this.mIsCheckSwitchStatus = false;
                this.mNeedShowUsbDialog = false;
                this.mCanChangePowerRole = booleanExtra2;
                this.mCurrentDataRole = intExtra2;
            } else {
                this.mCanChangePowerRole = booleanExtra2;
                this.mIsCheckSwitchStatus = true;
                this.mCurrentPowerRole = intExtra;
                this.mCurrentDataRole = intExtra2;
                if (this.mCurrentPowerRole == 1 && this.mCurrentDataRole == 2) {
                    this.mUsbConnected = true;
                }
                if (intExtra2 == 1) {
                    boolean z = OplusBatteryController.getInstance().getPlugged() == 1;
                    StringBuilder sbM2 = RowView$.ExternalSyntheticOutline0.m("dealWithSource  isAcConnected:", "  ,mIsCheckSwitchStatus:", z);
                    sbM2.append(this.mIsCheckSwitchStatus);
                    sbM2.append("  ,mIsExchangeUser:");
                    sbM2.append(this.mIsExchangeUser);
                    sbM2.append("  ,mCurrentPowerRole:");
                    sbM2.append(intExtra);
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbService", sbM2.toString());
                    if (z) {
                        OplusUsbBackend.getInstance(this.mContext).setIsServiceRunning(false);
                        destroyUsbService();
                    } else {
                        if (intExtra == 2) {
                            this.mCurrentSelectType = 0;
                        } else {
                            this.mCurrentSelectType = 5;
                        }
                        saveSelectTypeIfNeeded(this.mCurrentSelectType);
                        updateUsbNotification(this.mCurrentSelectType, this.mContext);
                    }
                } else if (this.mUsbConnected) {
                    onServiceUpdated(0L);
                } else {
                    onServiceUpdated(2000L);
                }
            }
        } else if (this.mUsbConnected) {
            onServiceUpdated(0L);
        } else {
            onServiceUpdated(2000L);
        }
        return super.onStartCommand(intent, i, i2);
    }

    public final void onUsbSelect(int i) {
        Intent intent;
        PagedTileLayout$2$.ExternalSyntheticOutline0.m(i, "onUsbSelect = ", LogUtil.TAG_COMMON, "UsbService");
        if (i == 0) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isSupportReverseCharging()) {
                saveSelectTypeIfNeeded(0);
            }
            intent = new Intent("com.oplus.test.only.charge");
        } else if (1 == i) {
            if (this.mNoTransmitting) {
                saveSelectTypeIfNeeded(1);
            }
            intent = new Intent("com.oplus.test.file.transmit");
        } else if (2 == i) {
            if (this.mNoTransmitting) {
                saveSelectTypeIfNeeded(2);
            }
            intent = new Intent("com.oplus.test.image.transmit");
        } else if (3 == i) {
            saveSelectTypeIfNeeded(3);
            intent = new Intent("com.oplus.test.usb.tethering");
        } else if (4 == i) {
            saveSelectTypeIfNeeded(4);
            intent = new Intent("com.oplus.test.midi");
        } else {
            if (5 == i) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "onUsbSelect=REVERSE_CHARGING");
                UsbService usbService = this.mContext;
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "change from：" + i + "dealReverseCharge");
                changeUsbConfig(5, usbService);
                return;
            }
            intent = null;
        }
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "onUsbSelect_usbIntent = " + intent);
        this.mContext.sendBroadcast(intent, ProviderUtil.OPLUS_COMPONENT_SAFE);
    }

    public final void performUsbDialogAction(int i) {
        switch (i) {
            case 1001:
                showUsbDialog(true);
                break;
            case 1002:
                showUsbDialog(false);
                break;
            case 1003:
                if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.isShowing()) {
                    this.mUsbSelectDialog.dismiss();
                    break;
                }
                break;
            case 1004:
                if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.isShowing()) {
                    this.mUsbSelectDialog.dismiss();
                }
                showUsbDialog(true);
                break;
            case 1005:
                if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.isShowing()) {
                    this.mUsbSelectDialog.dismiss();
                    break;
                }
                break;
            case 1006:
                if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.isShowing()) {
                    this.mUsbSelectDialog.dismiss();
                }
                showUsbDialog(false);
                break;
        }
    }

    public final void reshowDialog(boolean z) {
        if (!this.mUsbConnected) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "usb is not connected, no need to reshow usb dialog");
            return;
        }
        Handler handler = this.mUsbDialogHandler;
        UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda0 = new UsbService$$ExternalSyntheticLambda0(4);
        usbService$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(usbService$$ExternalSyntheticLambda0, 100L);
        if (z) {
            Handler handler2 = this.mUsbDialogHandler;
            UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda1 = new UsbService$$ExternalSyntheticLambda0(5);
            usbService$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            handler2.postDelayed(usbService$$ExternalSyntheticLambda1, 1000L);
        }
    }

    public final void savePoint(String str, String str2) {
        int i = this.mOperate;
        if (i == -1) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "savePoint mOperate -1 ");
            return;
        }
        UsbService usbService = this.mContext;
        if (usbService != null) {
            HashMap map = new HashMap();
            map.put("operate", String.valueOf(i));
            map.put(ZoomDCSManager.STATUS, str);
            map.put("message", str2);
            OplusTrackProxyUtil.onCommon(usbService, "20082", "usb_reverse_charging", map);
        }
        this.mOperate = -1;
    }

    public final void saveSelectTypeIfNeeded(int i) {
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mSelectTypeFromSettings, LogUtil.TAG_COMMON, "UsbService", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "saveSelectTypeIfNeeded  selectType = ", "  ,mSelectTypeFromSettings:"));
        if (i != this.mSelectTypeFromSettings) {
            this.mSelectTypeFromSettings = i;
            Settings.System.putInt(this.mContext.getContentResolver(), "usb_remeber_selection", i);
        }
    }

    public final void setUsbSelectAdapter() {
        this.mUsbListData.clear();
        this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957083), 2131237542, 0));
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging() && OplusUsbBackend.getInstance(this.mContext).getCanChangePowerRole()) {
            this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957076), 2131237540, 5));
        }
        this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957084) + getResources().getString(2131957081), 2131237544, 1));
        this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957085), 2131237547, 2));
        this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957086), 2131237548, 3));
        this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957082), 2131237545, 4));
    }

    public final void showUsbDialog(boolean z) {
        ComponentName topActivityComponentName;
        if (this.mSubscriptionManager == null) {
            this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service");
        }
        this.mDefaultDataPhoneId = SubscriptionManager.getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId());
        this.mForceShow = z;
        this.mDefaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        this.mIsFirstShowUsbDialog = true;
        if (this.mIsExchangeUser) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "multi-user switching does not display pop-up windows");
            this.mIsExchangeUser = false;
            return;
        }
        if (!z) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog in export region");
                return;
            }
            boolean zEquals = SystemProperties.get("persist.sys.allcommode", "false").equals("true");
            TaskbarDelegate$1$.ExternalSyntheticOutline0.m("needReset_isAllcommode = ", LogUtil.TAG_COMMON, "UsbService", zEquals);
            if (!zEquals) {
                UsbFeatureOption.INSTANCE.getClass();
                if (!UsbFeatureOption.isUsbServiceForCta) {
                    if (UsbFeatureOption.isAdbNeedSimAuto) {
                        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog for cmcc mode");
                        return;
                    } else if (OpUtils.sIsClosedSuperFirewall) {
                        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog for cts mode");
                        return;
                    } else if (FeatureOption.isGameMlbbSupport()) {
                        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog for MLBB version");
                        return;
                    }
                }
            }
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog for CTA version");
            return;
        }
        if (Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) == 0) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog on setupwizard");
            return;
        }
        if (UsbUtils.isDisableUSBDialog()) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignore show usb dialog if enterprise customization");
            return;
        }
        if ((isUsbNCMMode() || this.mOcarUsbConnected) && !this.mIsShowDialogFromNotification) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "Ignore show usb dialog when oCar usb connected");
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isGameLolSupport() || FeatureOption.isGamePelSupport()) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "Ignore show usb dialog for Game LOL or Game for peace");
            return;
        }
        if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.isShowing()) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "don't show again if dialog is showing :" + Thread.currentThread().getName());
            return;
        }
        AnonymousClass7 anonymousClass7 = null;
        try {
            topActivityComponentName = new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            Log.e("UsbSelection_Utils", e.getMessage());
            topActivityComponentName = null;
        }
        if ("com.android.permissioncontroller.permission.ui.GrantPermissionsActivity".equals(topActivityComponentName != null ? topActivityComponentName.getClassName() : "")) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "don't show usb diaolog, for permission dialog showing");
            return;
        }
        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "ignoreShowDialog   showUsbDialog_UsbDialogActivity.showSelecttypeDialog");
        int remeberSelectType = getRemeberSelectType();
        PagedTileLayout$2$.ExternalSyntheticOutline0.m(remeberSelectType, "initusbdialog  selectType:", LogUtil.TAG_COMMON, "UsbService");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging()) {
            if (this.mCurrentDataRole == 0 || this.mCurrentDataRole == -1) {
                int powerRole = OplusUsbBackend.getInstance(this.mContext).getPowerRole();
                int dataRole = OplusUsbBackend.getInstance(this.mContext).getDataRole();
                boolean canChangePowerRole = OplusUsbBackend.getInstance(this.mContext).getCanChangePowerRole();
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", FloatList$.ExternalSyntheticOutline0.m("initUsbDialogData currentPowerRole:", powerRole, dataRole, "  ,currentDataRole:", " ,canChangePowerRole:"), canChangePowerRole);
                this.mCurrentPowerRole = powerRole;
                this.mCurrentDataRole = dataRole;
                this.mCanChangePowerRole = canChangePowerRole;
                if (this.mCurrentDataRole == 1) {
                    OplusUsbBackend.getInstance(this.mContext).setServiceCurrentDataRole(this.mCurrentDataRole);
                }
            }
            if (this.mCurrentDataRole == 2) {
                setUsbSelectAdapter();
            } else if (this.mCurrentDataRole == 1) {
                this.mUsbListData.clear();
                this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957083), 2131237542, 0));
                this.mUsbListData.add(new OplusUsbData(getResources().getString(2131957076), 2131237540, 5));
            } else {
                OplusUsbBackend.getInstance(this.mContext).setCanChangePowerRole(false);
                setUsbSelectAdapter();
                LogUtil.e(LogUtil.TAG_COMMON, "UsbService", "initUsbDialogData error:" + this.mCurrentPowerRole);
            }
        } else {
            setUsbSelectAdapter();
        }
        int choiceListCheckedIndex = getChoiceListCheckedIndex(remeberSelectType);
        if (choiceListCheckedIndex == -1) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "initusbdialog, USB list data is empty or selectType: " + remeberSelectType + "not found");
        } else {
            this.mContext.setTheme(2132019095);
            COUIThemeOverlay.getInstance().applyThemeOverlays(this.mContext);
            ChoiceListAdapter choiceListAdapter = new ChoiceListAdapter(this.mContext, 2131559418, getChoiceListAdapterItems(), (CharSequence[]) null, (boolean[]) null, (boolean[]) null, false);
            this.mSingleChoiceListAdapter = choiceListAdapter;
            choiceListAdapter.setIcons(getChoiceListAdapterIcons());
            UsbService usbService = this.mContext;
            try {
                String customUSBPolicy = UsbUtils.getCustomUSBPolicy();
                if (customUSBPolicy == null || customUSBPolicy.equals("unlimited")) {
                    OplusCustomizeRestrictionManager oplusCustomizeRestrictionManager = OplusCustomizeRestrictionManager.getInstance(usbService);
                    if (oplusCustomizeRestrictionManager == null || !oplusCustomizeRestrictionManager.isUSBFileTransferDisabled()) {
                        anonymousClass7 = new AnonymousClass7();
                        anonymousClass7.this$0 = this;
                        anonymousClass7.val$force = z;
                        anonymousClass7.val$selectType = remeberSelectType;
                        VarHandle.storeStoreFence();
                    }
                } else {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbSelection_Utils", "disabled because of transfer policy = ".concat(customUSBPolicy));
                }
            } catch (Exception unused) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbSelection_Utils", "no class named OplusDevicepolicyManager");
            }
            COUIAlertDialogBuilder windowType = new COUIAlertDialogBuilder(this.mContext, 2132017609).setTitle(2131955296).setBlurBackgroundDrawable(true).setWindowType(2003);
            ChoiceListAdapter choiceListAdapter2 = this.mSingleChoiceListAdapter;
            AnonymousClass9 anonymousClass9 = new AnonymousClass9();
            anonymousClass9.this$0 = this;
            anonymousClass9.val$onItemSelectListener = anonymousClass7;
            VarHandle.storeStoreFence();
            windowType.setSingleChoiceItems(choiceListAdapter2, choiceListCheckedIndex, anonymousClass9);
            windowType.setNegativeButton$1(2131955076, new AnonymousClass8());
            this.mUsbSelectDialogBuilder = windowType;
            this.mUsbSelectDialog = windowType.create();
            if (this.mUsbSelectDialog != null && this.mUsbSelectDialog.getWindow() != null && this.mUsbSelectDialog.getOnBackInvokedDispatcher() != null) {
                OnBackInvokedDispatcher onBackInvokedDispatcher = this.mUsbSelectDialog.getWindow().getOnBackInvokedDispatcher();
                UsbService$$ExternalSyntheticLambda12 usbService$$ExternalSyntheticLambda12 = new UsbService$$ExternalSyntheticLambda12();
                usbService$$ExternalSyntheticLambda12.f$0 = this;
                VarHandle.storeStoreFence();
                onBackInvokedDispatcher.registerOnBackInvokedCallback(0, usbService$$ExternalSyntheticLambda12);
            }
            AlertDialog alertDialog = this.mUsbSelectDialog;
            if (alertDialog != null) {
                AnonymousClass10 anonymousClass10 = new AnonymousClass10();
                anonymousClass10.this$0 = this;
                VarHandle.storeStoreFence();
                alertDialog.setOnDismissListener(anonymousClass10);
                alertDialog.setOnShowListener(new AnonymousClass11());
            }
        }
        adjustFingerprintIconForUsbDialog("UsbService, Show USB Dialog", false);
        if (this.mUsbSelectDialog == null) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "dialog is null");
            return;
        }
        if (this.mUsbSelectDialogBuilder == null) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "builder is null");
        } else {
            if (this.mUsbSelectDialog.isShowing()) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "dialog is showing");
                return;
            }
            LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "showUsbDialog_UsbDialogActivity.showSelectDialog");
            this.mUsbSelectDialog.show();
            this.mUsbSelectDialogBuilder.updateViewAfterShown();
        }
    }

    public final void unRegisterAdbStateObservers() {
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "unRegisterAdbStateObservers, mAdbObserver = " + this.mAdbObserver);
        ObserverDispatcher.getInstance().unregisterObserver(this.mAdbObserver);
    }

    public final void unregisterUSBSelectTypeObserver(UsbService usbService) {
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "unregisterUSBSelectTypeObserver, mUSBSelectTypeObserver = " + this.mUsbSelectTypeObserver);
        usbService.getContentResolver().unregisterContentObserver(this.mUsbSelectTypeObserver);
    }

    public final void updateAdbNotification(Context context) {
        if (!this.mUsbConnected) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "mUsbConnected is false return");
            return;
        }
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        }
        boolean z = Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) != 0;
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("isAdbEnable is:", LogUtil.TAG_COMMON, "UsbService", z);
        if (!z) {
            this.mNotificationManager.cancel(10004);
            return;
        }
        CharSequence text = this.mResources.getText(2131952033);
        CharSequence text2 = this.mResources.getText(2131952032);
        Log.d("UsbService", "updateAdbNotification_title = " + ((Object) text) + ", message = " + ((Object) text2));
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "updateAdbNotification_title = " + ((Object) text) + ", message = " + ((Object) text2));
        Notification.Builder builder = new Notification.Builder(context, "INS");
        builder.setSmallIcon(2131232433);
        builder.setAutoCancel(false);
        builder.setContentTitle(text);
        builder.setContentText(text2);
        builder.setShowWhen(true);
        builder.setWhen(System.currentTimeMillis());
        Notification notificationBuild = builder.build();
        notificationBuild.flags = 34;
        notificationBuild.contentIntent = PendingIntent.getActivityAsUser(context, 0, Intent.makeRestartActivityTask(new ComponentName("com.android.settings", "com.android.settings.Settings$DevelopmentSettingsDashboardActivity")), 67108864, null, UserHandle.CURRENT);
        if (getRemeberSelectType() == 0) {
            UsbFeatureOption.INSTANCE.getClass();
            if (UsbFeatureOption.isUsbServiceForCta) {
                this.mNotificationManager.cancel(10004);
                return;
            }
        }
        sendMessageDelayed(obtainMessage(1, notificationBuild), 50L);
    }

    public final void updateUsbNotification(int i, Context context) {
        String string;
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "updateUsbNotification_mNotificationManager = " + this.mNotificationManager + "selectType is:" + i);
        StringBuilder sb = new StringBuilder("updateUsbNotification,and selectType =");
        sb.append(i);
        LogUtil.d(LogUtil.TAG_COMMON, "UsbService", sb.toString());
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        }
        if (isZQVersionUsbEnable()) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "isZQVersionUsbEnable return ");
            this.mNotificationManager.cancel(10005);
            return;
        }
        int remeberSelectType = getRemeberSelectType();
        if (i != remeberSelectType) {
            LogUtil.w(LogUtil.TAG_COMMON, "UsbService", "update select type is not remeber type,  remebertype = " + remeberSelectType);
            return;
        }
        Intent intent = new Intent("com.oplus.test.show.usbselector.dialog");
        if (i == 0) {
            string = this.mContext.getString(2131957045);
        } else if (1 == i) {
            string = this.mContext.getString(2131957065);
        } else if (2 == i) {
            string = this.mContext.getString(2131957068);
        } else if (3 == i) {
            string = this.mContext.getString(17042295);
        } else if (4 == i) {
            string = this.mContext.getString(17042284);
        } else {
            string = 5 == i ? this.mContext.getString(2131957077) : "";
        }
        intent.setPackage("com.android.systemui");
        intent.addFlags(536870912);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 33554432);
        Notification.Builder builder = new Notification.Builder(context, "INS");
        builder.setSmallIcon(2131237538);
        builder.setGroup("UsbSelectionNotification");
        Notification notificationBuild = builder.build();
        notificationBuild.flags = 34;
        builder.setContentIntent(broadcast);
        builder.setAutoCancel(false);
        builder.setContentTitle(string);
        builder.setPriority(2);
        builder.setContentText(this.mContext.getString(2131957066));
        boolean z = SystemProperties.getBoolean("persist.sys.oplus.usbactive", false);
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isUsbActive = ", ",and FeatureOption.IS_ADB_NEED_SIM_AUTO is :", z);
        UsbFeatureOption.INSTANCE.getClass();
        sbM.append(UsbFeatureOption.isAdbNeedSimAuto);
        sbM.append(", IS_ADB_NEED_SIM_MANUAL = ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "UsbService", sbM, UsbFeatureOption.isAdbNeedSimManual);
        if ((UsbFeatureOption.isAdbNeedSimAuto || UsbFeatureOption.isAdbNeedSimManual) && !z) {
            if (!hasInsertSimCard()) {
                if (this.mHasShow) {
                    return;
                }
                this.mContext.sendBroadcast(new Intent("com.oplus.test.only.charge"), ProviderUtil.OPLUS_COMPONENT_SAFE);
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "when usb is not active in cmcc version,send ACTION_ONLY_CHARGE");
                this.mHasShow = true;
                SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
                editorEdit.putBoolean("hasShow", true);
                editorEdit.apply();
                Intent intent2 = new Intent("oplus.intent.action.INSERT_SIMCARD_DIALOG");
                intent2.addFlags(268435456);
                intent2.setPackage("com.android.systemui");
                startActivity(intent2);
                return;
            }
            try {
                SystemProperties.set("persist.sys.oplus.usbactive", "true");
            } catch (Exception e) {
                Log.w("UsbService", "exception: " + e.getMessage());
            }
            this.mContext.sendBroadcast(new Intent("com.oplus.test.file.transmit"), ProviderUtil.OPLUS_COMPONENT_SAFE);
            Toast.makeText(this.mContext, 2131952031, 1).show();
            UsbFeatureOption.INSTANCE.getClass();
            if (UsbFeatureOption.isAdbNeedSimAuto && !UsbFeatureOption.isAdbNeedSimManual) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "OPEN_ADB");
                Settings.Global.putInt(context.getContentResolver(), "adb_enabled", 1);
            }
        }
        if (this.mBgHandler == null) {
            this.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        }
        this.mBgHandler.post(new PostNotificationHandler(this.mNotificationManager, notificationBuild, 10005));
        if (!this.mNeedShowUsbDialog) {
            this.mNeedShowUsbDialog = true;
            return;
        }
        Handler handler = this.mUsbDialogHandler;
        UsbService$$ExternalSyntheticLambda0 usbService$$ExternalSyntheticLambda0 = new UsbService$$ExternalSyntheticLambda0(9);
        usbService$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(usbService$$ExternalSyntheticLambda0);
    }

    public final int usbFunctionsToSelectType(long j) {
        if (j == 4) {
            return 1;
        }
        if (j == 8) {
            return 4;
        }
        if (j == 16) {
            return 2;
        }
        if (j == 32) {
            return 3;
        }
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isSupportReverseCharging() && this.mCurrentPowerRole == 1) ? 5 : 0;
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.UsbService$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends ContentObserver {
        public final /* synthetic */ int $r8$classId = 1;

        public AnonymousClass3() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            switch (this.$r8$classId) {
                case 1:
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "AdbEnableObserver_onChange() " + uri);
                    if (uri.equals(Settings.Global.getUriFor("adb_enabled"))) {
                        UsbService usbService = UsbService.this;
                        usbService.updateAdbNotification(usbService.mContext);
                    }
                    if (UsbService.this.mIsGovernmentVersion && uri.equals(Settings.Secure.getUriFor("ZQ_ADB_ENABLED"))) {
                        UsbService usbService2 = UsbService.this;
                        if (usbService2.mIsGovernmentVersion && Settings.Secure.getInt(usbService2.mContext.getContentResolver(), "ZQ_ADB_ENABLED", 1) == 0) {
                            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "AdbEnableObserver_onChange() SETTING_ZQ_ADB_ENABLED cancelZQVersionAdbNotify");
                            NotificationManager notificationManager = UsbService.this.mNotificationManager;
                            if (notificationManager != null) {
                                notificationManager.cancel(10004);
                            }
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "AdbEnableObserver_onChange() SETTING_ZQ_ADB_ENABLED updateAdbNotificationAdv");
                            UsbService usbService3 = UsbService.this;
                            usbService3.updateAdbNotification(usbService3.mContext);
                        }
                    }
                    if (UsbService.this.mIsGovernmentVersion && uri.equals(Settings.Secure.getUriFor("MTP_TRANSFER_ENABLED"))) {
                        if (!UsbService.this.isZQVersionUsbEnable()) {
                            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "AdbEnableObserver_onChange() SETTING_MTP_TRANSFER_ENABLED sendBroadcast(ACTION_FILE_TRANSMIT)");
                            UsbService.this.mContext.sendBroadcast(new Intent("com.oplus.test.file.transmit"), ProviderUtil.OPLUS_COMPONENT_SAFE);
                        } else {
                            LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "AdbEnableObserver_onChange() SETTING_MTP_TRANSFER_ENABLED cancelZQVersionUsbNotify");
                            UsbService usbService4 = UsbService.this;
                            NotificationManager notificationManager2 = usbService4.mNotificationManager;
                            if (notificationManager2 != null) {
                                notificationManager2.cancel(10005);
                            }
                            if (!(usbService4.isUsbNCMMode() || usbService4.mOcarUsbConnected) || usbService4.mIsShowDialogFromNotification) {
                                usbService4.mUsbManager.setCurrentFunction(usbService4.mOnlyChargeFunction, false);
                                usbService4.mCurrentSelectType = 4;
                                LogUtil.d(LogUtil.TAG_COMMON, "UsbService", "cancelZQVersionUsbNotify");
                            } else {
                                LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "cancelZQVersionUsbNotify - Ignore change Usb Config for NCM mode");
                            }
                        }
                        break;
                    }
                    break;
                default:
                    super.onChange(z, uri);
                    break;
            }
        }

        public AnonymousClass3(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    super.onChange(z);
                    UsbService usbService = UsbService.this;
                    usbService.mSelectTypeFromSettings = Settings.System.getInt(usbService.mContext.getContentResolver(), "usb_remeber_selection", 1);
                    if (UsbUtils.getCustomUSBPolicy() != null && !UsbUtils.getCustomUSBPolicy().equals("unlimited")) {
                        UsbService usbService2 = UsbService.this;
                        int i = usbService2.mSelectTypeFromSettings;
                        usbService2.mCurrentSelectType = i;
                        usbService2.onUsbSelect(i);
                        break;
                    }
                    break;
                default:
                    super.onChange(z);
                    break;
            }
        }
    }
}
