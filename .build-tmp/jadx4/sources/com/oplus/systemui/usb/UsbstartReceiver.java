package com.oplus.systemui.usb;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.util.JankUtils;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class UsbstartReceiver extends BroadcastReceiver {
    public static final int[] SCENE_USB_RELATED;
    public static final ComponentName SERVER_COMPONENT = new ComponentName("com.android.systemui", "com.oplus.systemui.usb.UsbService");

    static {
        SystemProperties.getBoolean("persist.sys.assert.panic", true);
        SCENE_USB_RELATED = new int[]{107, 108, 121, 122, 124, 130, 131};
    }

    public static Context createUserContext(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("currentUser", -1);
        if (intExtra == -1) {
            return context;
        }
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, new UserHandle(intExtra));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "Create package context as user exception : " + e.getMessage());
            return null;
        }
    }

    public static void handleUsbStateChange(Context context, Intent intent, Intent intent2) {
        boolean booleanExtra;
        Context contextCreateUserContext = createUserContext(context, intent);
        if (contextCreateUserContext == null) {
            LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", "handleUsbStateChange userContext is null");
            return;
        }
        int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        if (contextCreateUserContext.getUserId() != currentUserId) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(currentUserId, "Don't start the usb service when the user id of the current service is not the current system user id  currentUserId:", "  ,getUserId:");
            sbM.append(contextCreateUserContext.getUserId());
            LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", sbM.toString());
            return;
        }
        boolean booleanExtra2 = false;
        try {
            booleanExtra = intent.getBooleanExtra("connected", false);
            try {
                booleanExtra2 = intent.getBooleanExtra("configured", false);
            } catch (Exception unused) {
                LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "handleUsbStateChange Application Security Report");
            }
        } catch (Exception unused2) {
            booleanExtra = false;
        }
        LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("USB_STATE, connected:", ", configured:", booleanExtra, booleanExtra2));
        if (booleanExtra2) {
            JankUtils.gfxSceneEnd(contextCreateUserContext, SCENE_USB_RELATED);
        }
        OplusUiOffloadThread oplusUiOffloadThread = OplusUiOffloadThread.getInstance();
        UsbstartReceiver$$ExternalSyntheticLambda0 usbstartReceiver$$ExternalSyntheticLambda0 = new UsbstartReceiver$$ExternalSyntheticLambda0();
        usbstartReceiver$$ExternalSyntheticLambda0.f$0 = booleanExtra2;
        usbstartReceiver$$ExternalSyntheticLambda0.f$1 = contextCreateUserContext;
        usbstartReceiver$$ExternalSyntheticLambda0.f$2 = intent2;
        usbstartReceiver$$ExternalSyntheticLambda0.f$3 = booleanExtra;
        VarHandle.storeStoreFence();
        oplusUiOffloadThread.submitOnThreadExecutor(usbstartReceiver$$ExternalSyntheticLambda0);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setComponent(SERVER_COMPONENT);
        Log.i("UsbStartReceiver", "receive: " + intent);
        String action = intent.getAction();
        if ("android.hardware.usb.action.USB_STATE".equals(action)) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportReverseCharging()) {
                Context contextCreateUserContext = createUserContext(context, intent);
                if (contextCreateUserContext == null) {
                    LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", "USB_STATE userContext is null");
                    return;
                }
                if (OplusUsbBackend.getInstance(contextCreateUserContext).getDataRole() == 1) {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "currentDataRole is host so return");
                    return;
                }
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (FlavorOneFeatureOption.isFlavorOneDevice() && FlavorOneFeatureOption.sIsUsbPortSupport && OplusUsbBackend.getInstance(contextCreateUserContext).getCanChangePowerRole()) {
                    LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "UsbPortSupport so return");
                    return;
                } else {
                    intent2.putExtra("canChangePowerRole", OplusUsbBackend.getInstance(context).getCanChangePowerRole() || OplusUsbBackend.getInstance(contextCreateUserContext).getCanChangePowerRole());
                    intent2.putExtra("currentPowerRole", OplusUsbBackend.getInstance(contextCreateUserContext).getPowerRole());
                    intent2.putExtra("currentDataRole", OplusUsbBackend.getInstance(contextCreateUserContext).getDataRole());
                }
            }
            handleUsbStateChange(context, intent, intent2);
            return;
        }
        if (!"android.hardware.usb.action.OPLUS_USB_USER_SWITCH".equals(action)) {
            if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
                context.stopService(intent2);
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.oplus.usbselection_shared_prefs", 0).edit();
                editorEdit.putBoolean("hasShow", false);
                editorEdit.apply();
                return;
            }
            return;
        }
        intent2.putExtra("isExchangeUser", true);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging()) {
            Context contextCreateUserContext2 = createUserContext(context, intent);
            if (contextCreateUserContext2 == null) {
                LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", "handleUserSwitch userContext is null");
                return;
            }
            int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
            if (contextCreateUserContext2.getUserId() != currentUserId) {
                StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(currentUserId, "handleUserSwitch Don't start the usb service when the user id of the current service is not the current system user id  currentUserId:", "  ,getUserId:");
                sbM.append(contextCreateUserContext2.getUserId());
                LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", sbM.toString());
                return;
            }
            int dataRole = OplusUsbBackend.getInstance(contextCreateUserContext2).getDataRole();
            boolean z = OplusUsbBackend.getInstance(context).getCanChangePowerRole() || OplusUsbBackend.getInstance(contextCreateUserContext2).getCanChangePowerRole();
            StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m("handleUserSwitch canChangePowerRole:", dataRole, " ,dataRole:", " ,context.getUserId:", z);
            sbM2.append(context.getUserId());
            sbM2.append(" ,userContext.getUserId:");
            sbM2.append(contextCreateUserContext2.getUserId());
            LogUtil.i(LogUtil.TAG_COMMON, "UsbStartReceiver", sbM2.toString());
            intent2.putExtra("canChangePowerRole", z);
            intent2.putExtra("currentPowerRole", OplusUsbBackend.getInstance(contextCreateUserContext2).getPowerRole());
            intent2.putExtra("currentDataRole", dataRole);
            if (dataRole == 1 && z) {
                contextCreateUserContext2.startService(intent2);
                return;
            }
        }
        handleUsbStateChange(context, intent, intent2);
    }
}
