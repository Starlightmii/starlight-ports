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
public final class RedHorizontalSingleClockController$mListener$1 implements ChargingAnimationImpl.IAnimChargeChangedListener {
    public /* synthetic */ RedHorizontalSingleClockController this$0;

    public final void animChargeHasChanged(int i, boolean z) {
        ClockExtraTextView clockExtraTextView;
        RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
        boolean z2 = redHorizontalSingleClockController.mIsCharging;
        int i2 = redHorizontalSingleClockController.mChargingAnimState;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("animChargeHasChanged , state : ", i, " , isCharging : ", " ,mIsCharging : ", z);
        sbM.append(z2);
        sbM.append(", mChargingAnimState : ");
        sbM.append(i2);
        KgdLogUtil.i(RedHorizontalSingleClockController.TAG, sbM.toString());
        if (i == redHorizontalSingleClockController.mChargingAnimState && z == redHorizontalSingleClockController.mIsCharging) {
            RedHorizontalSingleClockController.access$resetChargeTipsIfNeeded(redHorizontalSingleClockController, z);
            KgdLogUtil.w(RedHorizontalSingleClockController.TAG, "state and charging is same , return");
            return;
        }
        if (i == 5) {
            redHorizontalSingleClockController.mChargingAnimState = 4;
            redHorizontalSingleClockController.mIsCharging = true;
            ClockExtraTextView clockExtraTextView2 = redHorizontalSingleClockController.mClockExtraTextViewBig;
            if (clockExtraTextView2 != null) {
                clockExtraTextView2.setAlpha(0.0f);
            }
            ClockExtraTextView clockExtraTextView3 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
            if (clockExtraTextView3 != null) {
                clockExtraTextView3.setAlpha(1.0f);
            }
            LinearLayout linearLayout = redHorizontalSingleClockController.mDataChargeContainer;
            if (linearLayout != null) {
                linearLayout.setAlpha(1.0f);
            }
        }
        if (i == 1) {
            RedHorizontalSingleClockController.access$resetAnim(redHorizontalSingleClockController);
        }
        if (i == 2) {
            redHorizontalSingleClockController.mIsCharging = true;
            redHorizontalSingleClockController.mChargingAnimState = 2;
            redHorizontalSingleClockController.updateAlphaWhenCharging(z);
            return;
        }
        if (i == 4 && z) {
            redHorizontalSingleClockController.mIsCharging = true;
            redHorizontalSingleClockController.mChargingAnimState = 4;
            ClockExtraTextView clockExtraTextView4 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
            if (clockExtraTextView4 != null) {
                clockExtraTextView4.setAlpha(1.0f);
            }
            RedHorizontalSingleClockController.access$endDisChargingAnimIfNeed(redHorizontalSingleClockController);
            KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "playScreenOnAnim when isCharging, state CHARGE_ANIM_CANCEL");
            RedHorizontalSingleClockController.access$playScreenOnAnim(redHorizontalSingleClockController);
            return;
        }
        if (!z && redHorizontalSingleClockController.mIsCharging) {
            String str = NumberFormat.getPercentInstance().format(redHorizontalSingleClockController.mBatteryLevel / 100.0f);
            ClockExtraTextView clockExtraTextView5 = redHorizontalSingleClockController.mClockExtraTextViewBig;
            if (clockExtraTextView5 != null) {
                str.getClass();
                clockExtraTextView5.setCurrentLevel(str);
            }
            redHorizontalSingleClockController.mIsCharging = false;
            AnimatorSet animatorSet = redHorizontalSingleClockController.mScreenOnAnimSet;
            if (animatorSet != null && animatorSet.isRunning()) {
                redHorizontalSingleClockController.mChargingAnimState = 4;
                if (redHorizontalSingleClockController.mBatteryIsLow) {
                    return;
                }
                ClockExtraTextView clockExtraTextView6 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView6 != null) {
                    clockExtraTextView6.setAlpha(0.0f);
                }
                ClockExtraTextView clockExtraTextView7 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView7 != null) {
                    clockExtraTextView7.setText("");
                    return;
                }
                return;
            }
            int i3 = redHorizontalSingleClockController.mChargingAnimState;
            if ((i3 == 2 || i3 == 1) && i == 4) {
                redHorizontalSingleClockController.mChargingAnimState = 4;
                if (!redHorizontalSingleClockController.mBatteryIsLow) {
                    ClockExtraTextView clockExtraTextView8 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                    if (clockExtraTextView8 != null) {
                        clockExtraTextView8.setAlpha(0.0f);
                    }
                    ClockExtraTextView clockExtraTextView9 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                    if (clockExtraTextView9 != null) {
                        clockExtraTextView9.setText("");
                    }
                }
                KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "playScreenOnAnim create or resume, state CHARGE_ANIM_CANCEL");
                RedHorizontalSingleClockController.access$playScreenOnAnim(redHorizontalSingleClockController);
                return;
            }
            if (NotificationCommHelper.Companion.getInstance().isKeyguardShowing) {
                KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "isKeyguardShowing showing");
                RedHorizontalSingleClockController.access$playDischargingAnim(redHorizontalSingleClockController);
            } else {
                redHorizontalSingleClockController.updateAlphaWhenCharging(false);
                ClockExtraTextView clockExtraTextView10 = redHorizontalSingleClockController.mClockExtraTextViewNormal;
                if (clockExtraTextView10 != null) {
                    clockExtraTextView10.setText("");
                }
                if (!redHorizontalSingleClockController.mBatteryIsLow && (clockExtraTextView = redHorizontalSingleClockController.mClockExtraTextViewNormal) != null) {
                    clockExtraTextView.setAlpha(0.0f);
                }
                KgdLogUtil.i(RedHorizontalSingleClockController.TAG, "isKeyguardShowing not showing");
            }
        }
        redHorizontalSingleClockController.mChargingAnimState = i;
        redHorizontalSingleClockController.mIsCharging = z;
    }

    public final void onBatteryLevelChange(int i) {
        RedHorizontalSingleClockController redHorizontalSingleClockController = this.this$0;
        redHorizontalSingleClockController.mBatteryLevel = i;
        boolean z = i < 20;
        redHorizontalSingleClockController.mBatteryIsLow = z;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onBatteryLevelChange, mBatteryIsLow : ", RedHorizontalSingleClockController.TAG, z);
        ChargeUtils.setBatteryIsLow(redHorizontalSingleClockController.mBatteryIsLow);
    }

    public final void onKeyguardVisibleChanged(boolean z) {
        if (z) {
            return;
        }
        RedHorizontalSingleClockController.access$resetChargeAndShowView(this.this$0);
    }
}
