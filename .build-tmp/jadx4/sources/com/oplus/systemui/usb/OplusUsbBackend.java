package com.oplus.systemui.usb;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbPort;
import android.hardware.usb.UsbPortStatus;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import com.android.systemui.ExpandHelper$;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.statusbar.phone.OplusUsbDialogInfoProvider;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUsbBackend {
    public static volatile OplusUsbBackend sInstance;
    public volatile boolean mBlockedUsbFlagStatus;
    public volatile Intent mCachedServerIntent;
    public Context mContext;
    public UsbPort mPort;
    public UsbPortStatus mPortStatus;
    public UsbManager mUsbManager;

    public static OplusUsbBackend getInstance(Context context) {
        OplusUsbBackend oplusUsbBackend;
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (OplusUsbBackend.class) {
            try {
                if (sInstance == null) {
                    OplusUsbBackend oplusUsbBackend2 = new OplusUsbBackend();
                    oplusUsbBackend2.mBlockedUsbFlagStatus = false;
                    oplusUsbBackend2.mCachedServerIntent = null;
                    oplusUsbBackend2.mContext = context;
                    oplusUsbBackend2.mUsbManager = (UsbManager) context.getSystemService(UsbManager.class);
                    VarHandle.storeStoreFence();
                    sInstance = oplusUsbBackend2;
                }
                oplusUsbBackend = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return oplusUsbBackend;
    }

    public boolean getCanChangePowerRole() {
        try {
            String str = SystemProperties.get("sys.oplus.usb.service_can_change_power_role", "0");
            if (TextUtils.isEmpty(str)) {
                LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "getCanChangePowerRole value is null");
                return false;
            }
            boolean zEquals = "1".equals(str);
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "getCanChangePowerRole value:" + str + ",canChangePowerRole:" + zEquals);
            return zEquals;
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("getCanChangePowerRole error:"), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
            return false;
        }
    }

    public int getDataRole() {
        updatePorts();
        UsbPortStatus usbPortStatus = this.mPortStatus;
        if (usbPortStatus == null) {
            return 0;
        }
        return usbPortStatus.getCurrentDataRole();
    }

    public boolean getIsServiceRunning() {
        try {
            String str = SystemProperties.get("sys.oplus.usb.service_is_running", "0");
            boolean zEquals = "1".equals(str);
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "getIsServiceRunning value:" + str);
            return zEquals;
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("getIsServiceRunning error: "), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
            return false;
        }
    }

    public int getPowerRole() {
        updatePorts();
        UsbPortStatus usbPortStatus = this.mPortStatus;
        if (usbPortStatus == null) {
            return 0;
        }
        return usbPortStatus.getCurrentPowerRole();
    }

    public boolean getServiceCurrentDataRole() {
        try {
            int i = SystemProperties.getInt("sys.oplus.usb.service_data_role", 0);
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "getServiceCurrentDataRole:" + i);
            return i == 1;
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("getServiceCurrentDataRoleDevices error:"), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
            return false;
        }
    }

    public void setBlockedUsbDialogFlagStatus(int i) {
        this.mBlockedUsbFlagStatus = (i & 16384) != 0;
    }

    public void setCanChangePowerRole(boolean z) {
        try {
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "setCanChangePowerRole canChangePowerRole:" + z);
            SystemProperties.set("sys.oplus.usb.service_can_change_power_role", z ? "1" : "0");
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("setCanChangePowerRole error:"), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
        }
    }

    public void setIsServiceRunning(boolean z) {
        try {
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "setIsServiceRunning isRunning:" + z);
            SystemProperties.set("sys.oplus.usb.service_is_running", z ? "1" : "0");
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("setIsServiceRunning error:"), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
        }
    }

    public void setPowerRole(int i) {
        int dataRole = getDataRole();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("setPowerRole  powerRole:", i, dataRole, "  ,newDateRole:", "  ,mPort:");
        sbM.append(this.mPort);
        LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", sbM.toString());
        UsbPort usbPort = this.mPort;
        if (usbPort != null) {
            usbPort.setRoles(i, dataRole);
        }
    }

    public void setServiceCurrentDataRole(int i) {
        try {
            LogUtil.d(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", "setServiceCurrentDataRole:" + i);
            SystemProperties.set("sys.oplus.usb.service_data_role", String.valueOf(i));
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("setServiceCurrentDataRole error:"), LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi");
        }
    }

    public final boolean shouldBlockService() {
        int currentBlockedValue = OplusUsbDialogInfoProvider.getCurrentBlockedValue();
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "OplusUsbBackend_SystemUi", ListPopupWindow$.ExternalSyntheticOutline0.m(currentBlockedValue, "shouldBlockService: blockedValue: ", ", blockedFlagStatus: "), this.mBlockedUsbFlagStatus);
        return this.mBlockedUsbFlagStatus && currentBlockedValue != 0;
    }

    public final void updatePorts() {
        this.mPort = null;
        List ports = this.mUsbManager.getPorts();
        int size = ports.size();
        for (int i = 0; i < size; i++) {
            UsbPortStatus status = ((UsbPort) ports.get(i)).getStatus();
            if (status != null && status.isConnected()) {
                this.mPort = (UsbPort) ports.get(i);
                this.mPortStatus = status;
                return;
            }
        }
    }
}
