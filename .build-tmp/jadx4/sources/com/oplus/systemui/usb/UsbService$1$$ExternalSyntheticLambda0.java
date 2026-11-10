package com.oplus.systemui.usb;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UsbService$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                UsbService usbService = ((UsbService.AnonymousClass1) obj).this$0;
                int i2 = UsbService.$r8$clinit;
                usbService.performUsbDialogAction(1001);
                break;
            case 1:
                UsbService usbService2 = ((UsbService.AnonymousClass12) obj).this$0;
                int i3 = UsbService.$r8$clinit;
                usbService2.performUsbDialogAction(1003);
                break;
            case 2:
                ((UsbService$.ExternalSyntheticLambda16) obj).run();
                break;
            default:
                ((UsbService$.ExternalSyntheticLambda15) obj).run();
                break;
        }
    }
}
