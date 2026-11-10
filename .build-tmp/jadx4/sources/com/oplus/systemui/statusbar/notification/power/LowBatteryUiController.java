package com.oplus.systemui.statusbar.notification.power;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LowBatteryUiController {
    public static volatile LowBatteryUiController sInstance;
    public LowBatteryDialog mLowBatteryDialogOnMainDisplay;
    public LowBatteryDialog mLowBatteryDialogOnSecondDisplay;

    public static LowBatteryUiController getInstance() {
        if (sInstance == null) {
            synchronized (LowBatteryUiController.class) {
                try {
                    if (sInstance == null) {
                        LowBatteryUiController lowBatteryUiController = new LowBatteryUiController();
                        lowBatteryUiController.mLowBatteryDialogOnMainDisplay = null;
                        lowBatteryUiController.mLowBatteryDialogOnSecondDisplay = null;
                        sInstance = lowBatteryUiController;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public final void dismissAll() {
        LowBatteryDialog lowBatteryDialog = this.mLowBatteryDialogOnMainDisplay;
        if (lowBatteryDialog != null && lowBatteryDialog.isShowing()) {
            this.mLowBatteryDialogOnMainDisplay.dismiss();
        }
        this.mLowBatteryDialogOnMainDisplay = null;
        dismissBatteryWarningOnSecondDisplay();
    }

    public final void dismissBatteryWarningOnSecondDisplay() {
        LowBatteryDialog lowBatteryDialog = this.mLowBatteryDialogOnSecondDisplay;
        if (lowBatteryDialog != null && lowBatteryDialog.isShowing()) {
            this.mLowBatteryDialogOnSecondDisplay.dismiss();
        }
        this.mLowBatteryDialogOnSecondDisplay = null;
    }
}
