package com.oplus.systemui.usb;

import androidx.appcompat.app.AlertDialog;
import com.android.systemui.ExpandHelper$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.utils.NotificationConstants;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UsbService$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ UsbService f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        UsbService usbService = this.f$0;
        switch (i) {
            case 0:
                int i2 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1003);
                break;
            case 1:
                if (usbService.mUsbSelectDialog != null) {
                    AlertDialog alertDialog = usbService.mUsbSelectDialog;
                    UsbService$.ExternalSyntheticLambda11 externalSyntheticLambda11 = new UsbService$.ExternalSyntheticLambda11();
                    externalSyntheticLambda11.f$0 = usbService;
                    VarHandle.storeStoreFence();
                    alertDialog.setOnDismissListener(externalSyntheticLambda11);
                }
                usbService.performUsbDialogAction(1003);
                break;
            case 2:
                int i3 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1003);
                break;
            case 3:
                int i4 = UsbService.$r8$clinit;
                NotificationConstants.setsDialogState(NotificationConstants.getsDialogState() & (-2));
                usbService.mIsShowDialogFromNotification = false;
                ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_COMMON, "UsbService", new StringBuilder("onDismiss2 mIsShowDialogFromNotification = "), usbService.mIsShowDialogFromNotification);
                UsbService.adjustFingerprintIconForUsbDialog("onUsbDismiss", true);
                break;
            case 4:
                int i5 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1001);
                break;
            case 5:
                int i6 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1003);
                break;
            case 6:
                int i7 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1004);
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                int i8 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1005);
                break;
            case 8:
                int i9 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1004);
                break;
            default:
                int i10 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1002);
                break;
        }
    }
}
