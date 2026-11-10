package com.oplus.systemui.shared.clocks;

import android.animation.AnimatorSet;
import android.widget.LinearLayout;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import com.oplus.systemui.keyguard.ChargeUtils;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.keyguard.view.ClockExtraTextView;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.text.NumberFormat;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleClockController$mListener$1 implements ChargingAnimationImpl.IAnimChargeChangedListener {
    public /* synthetic */ SingleClockController this$0;

    public final void animChargeHasChanged(int i, boolean z) {
        ClockExtraTextView clockExtraTextView;
        SingleClockController singleClockController = this.this$0;
        boolean z2 = singleClockController.mIsCharging;
        int i2 = singleClockController.mChargingAnimState;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("animChargeHasChanged , state : ", i, " , isCharging : ", " ,mIsCharging : ", z);
        sbM.append(z2);
        sbM.append(", mChargingAnimState : ");
        sbM.append(i2);
        KgdLogUtil.i(SingleClockController.TAG, sbM.toString());
        if (i == singleClockController.mChargingAnimState && z == singleClockController.mIsCharging) {
            SingleClockController.access$resetChargeTipsIfNeeded(singleClockController, z);
            KgdLogUtil.w(SingleClockController.TAG, "state and charging is same , return");
            return;
        }
        if (i == 5) {
            singleClockController.mChargingAnimState = 4;
            singleClockController.mIsCharging = true;
            ClockExtraTextView clockExtraTextView2 = singleClockController.mClockExtraTextViewBig;
            if (clockExtraTextView2 != null) {
                clockExtraTextView2.setAlpha(0.0f);
            }
            ClockExtraTextView clockExtraTextView3 = singleClockController.mClockExtraTextViewNormal;
            if (clockExtraTextView3 != null) {
                clockExtraTextView3.setAlpha(1.0f);
            }
            LinearLayout linearLayout = singleClockController.mDataChargeContainer;
            if (linearLayout != null) {
                linearLayout.setAlpha(1.0f);
            }
        }
        if (i == 1) {
            SingleClockController.access$resetAnim(singleClockController);
        }
        if (i == 2) {
            singleClockController.mIsCharging = true;
            singleClockController.mChargingAnimState = 2;
            singleClockController.updateAlphaWhenCharging$1(z);
            return;
        }
        if (i == 4 && z) {
            singleClockController.mIsCharging = true;
            singleClockController.mChargingAnimState = 4;
            ClockExtraTextView clockExtraTextView4 = singleClockController.mClockExtraTextViewNormal;
            if (clockExtraTextView4 != null) {
                clockExtraTextView4.setAlpha(1.0f);
            }
            SingleClockController.access$endDisChargingAnimIfNeed(singleClockController);
            KgdLogUtil.i(SingleClockController.TAG, "playScreenOnAnim when isCharging, state CHARGE_ANIM_CANCEL");
            SingleClockController.access$playScreenOnAnim(singleClockController);
            return;
        }
        if (!z && singleClockController.mIsCharging) {
            String str = NumberFormat.getPercentInstance().format(singleClockController.mBatteryLevel / 100.0f);
            ClockExtraTextView clockExtraTextView5 = singleClockController.mClockExtraTextViewBig;
            if (clockExtraTextView5 != null) {
                str.getClass();
                clockExtraTextView5.setCurrentLevel(str);
            }
            singleClockController.mIsCharging = false;
            AnimatorSet animatorSet = singleClockController.mScreenOnAnimSet;
            if (animatorSet != null && animatorSet.isRunning()) {
                singleClockController.mChargingAnimState = 4;
                if (singleClockController.mBatteryIsLow) {
                    return;
                }
                ClockExtraTextView clockExtraTextView6 = singleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView6 != null) {
                    clockExtraTextView6.setAlpha(0.0f);
                }
                ClockExtraTextView clockExtraTextView7 = singleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView7 != null) {
                    clockExtraTextView7.setText("");
                    return;
                }
                return;
            }
            int i3 = singleClockController.mChargingAnimState;
            if ((i3 == 2 || i3 == 1) && i == 4) {
                singleClockController.mChargingAnimState = 4;
                if (!singleClockController.mBatteryIsLow) {
                    ClockExtraTextView clockExtraTextView8 = singleClockController.mClockExtraTextViewNormal;
                    if (clockExtraTextView8 != null) {
                        clockExtraTextView8.setAlpha(0.0f);
                    }
                    ClockExtraTextView clockExtraTextView9 = singleClockController.mClockExtraTextViewNormal;
                    if (clockExtraTextView9 != null) {
                        clockExtraTextView9.setText("");
                    }
                }
                KgdLogUtil.i(SingleClockController.TAG, "playScreenOnAnim create or resume, state CHARGE_ANIM_CANCEL");
                SingleClockController.access$playScreenOnAnim(singleClockController);
                return;
            }
            if (NotificationCommHelper.Companion.getInstance().isKeyguardShowing) {
                KgdLogUtil.i(SingleClockController.TAG, "isKeyguardShowing showing");
                SingleClockController.access$playDischargingAnim(singleClockController);
            } else {
                singleClockController.updateAlphaWhenCharging$1(false);
                ClockExtraTextView clockExtraTextView10 = singleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView10 != null) {
                    clockExtraTextView10.setText("");
                }
                if (!singleClockController.mBatteryIsLow && (clockExtraTextView = singleClockController.mClockExtraTextViewNormal) != null) {
                    clockExtraTextView.setAlpha(0.0f);
                }
                KgdLogUtil.i(SingleClockController.TAG, "isKeyguardShowing not showing");
            }
        }
        singleClockController.mChargingAnimState = i;
        singleClockController.mIsCharging = z;
    }

    public final void onBatteryLevelChange(int i) {
        SingleClockController singleClockController = this.this$0;
        singleClockController.mBatteryLevel = i;
        boolean z = i < 20;
        singleClockController.mBatteryIsLow = z;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onBatteryLevelChange, mBatteryIsLow : ", SingleClockController.TAG, z);
        ChargeUtils.setBatteryIsLow(singleClockController.mBatteryIsLow);
    }

    public final void onKeyguardVisibleChanged(boolean z) {
        if (z) {
            return;
        }
        SingleClockController.access$resetChargeAndShowView(this.this$0);
    }
}
