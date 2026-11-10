package com.oplus.systemui.statusbar.notification.power;

import androidx.appcompat.app.AlertDialog;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPowerUI$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPowerUI f$0;

    public /* synthetic */ OplusPowerUI$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusPowerUI oplusPowerUI = this.f$0;
        switch (i) {
            case 0:
                oplusPowerUI.mIsBatteryOriginal = NotificationUtil.isBatteryOriginal();
                oplusPowerUI.mIsBatteryOriginalChecked = true;
                LogUtil.i(LogUtil.TAG_COMMON, "Oplus_PowerUI", "getIsBatteryOriginal  mIsBatteryOriginal:" + oplusPowerUI.mIsBatteryOriginal);
                oplusPowerUI.checkNonOriginalBatteryOnBoot();
                break;
            case 1:
                VibrationHelper.getInstance(oplusPowerUI.mContext).vibrateCustomized(oplusPowerUI.mContext, 2, true);
                break;
            default:
                oplusPowerUI.mWarnings.dismissLowBatteryWarning();
                AlertDialog alertDialog = oplusPowerUI.mWarnings.mInvalidChargerDialog;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                OplusPowerNotificationWarnings oplusPowerNotificationWarnings = oplusPowerUI.mWarnings;
                oplusPowerNotificationWarnings.dismissChargeErrorWarring(oplusPowerNotificationWarnings.mChargeErrorDialogType);
                break;
        }
    }
}
