package com.oplus.systemui.separate;

import android.util.Log;
import android.view.ViewGroup;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController$fractionChangeListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ OplusSimpleQSFakeController this$0;

    public final void onFractionChanged(float f) {
        OplusQSDateView dateView;
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f / 0.86f, 0.0f, 1.0f);
        OplusSimpleQSFakeController oplusSimpleQSFakeController = this.this$0;
        if (oplusSimpleQSFakeController.isUpdateFakeViewLocation) {
            oplusSimpleQSFakeController.resetExtraTakeOverCondition();
            if (!((OplusSeparateNotificationAndQSEx) oplusSimpleQSFakeController.separateNotificationAndQSEx.get()).isFullyCollapsed() || oplusSimpleQSFakeController.isFakeClockGone) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("return ExpandedFractionChangeListener, isFakeClockGone: ", "OplusSimpleQSFakeController-->", "SystemUi--Notification", oplusSimpleQSFakeController.isFakeClockGone);
                }
                ViewGroup viewGroup = oplusSimpleQSFakeController.oplusQSFakeContainer;
                if (viewGroup != null) {
                    viewGroup.setVisibility(4);
                }
                ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusQSFakeFixedContainer;
                if (viewGroup2 != null) {
                    viewGroup2.setVisibility(4);
                }
                OplusSimpleQSFakeController.access$resetQsViewsExpandLocation(oplusSimpleQSFakeController);
                OplusSimpleQSFakeController.access$updateFakeFraction(oplusSimpleQSFakeController);
                if (f == 1.0f) {
                    OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
                    if (oplusQSSimpleHeader != null && (dateView = oplusQSSimpleHeader.getDateView()) != null) {
                        dateView.setSelected(true);
                    }
                    oplusSimpleQSFakeController.isFakeClockGone = false;
                }
                if (f == 0.0f) {
                    oplusSimpleQSFakeController.resetAllViewsTransitionAlpha(f);
                    oplusSimpleQSFakeController.updateFakeClockScale(fCoerceIn);
                    OplusSimpleQSFakeController.access$updateFakeSimpleQSTranslation(oplusSimpleQSFakeController, fCoerceIn);
                    return;
                }
                return;
            }
            if (oplusSimpleQSFakeController.isNotSupportSeamlessTransfer()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->now is NotSupportSeamlessTransfer");
                }
                OplusSimpleQSFakeController.access$resetQsViewsExpandLocation(oplusSimpleQSFakeController);
                OplusSimpleQSFakeController.access$resetfakeViewsStartLocation(oplusSimpleQSFakeController);
            }
            oplusSimpleQSFakeController.setFakeViewLocationUpdateFlag(false);
            OplusSimpleQSFakeController.access$updateFakeToStatusBarTranslation(oplusSimpleQSFakeController);
            ViewGroup viewGroup3 = oplusSimpleQSFakeController.oplusQSFakeContainer;
            if (viewGroup3 != null) {
                viewGroup3.setVisibility(0);
            }
            ViewGroup viewGroup4 = oplusSimpleQSFakeController.oplusQSFakeFixedContainer;
            if (viewGroup4 != null) {
                viewGroup4.setVisibility(0);
            }
        }
        OplusSimpleQSFakeController.access$updateFakeFraction(oplusSimpleQSFakeController);
        OplusSimpleQSFakeController.access$updateExtraSimpleQSFootTranslationY(oplusSimpleQSFakeController, fCoerceIn);
        if (!oplusSimpleQSFakeController.isNotSupportSeamlessTransfer()) {
            oplusSimpleQSFakeController.updateQSClockScale(fCoerceIn);
            oplusSimpleQSFakeController.updateFakeClockScale(fCoerceIn);
            OplusSimpleQSFakeController.access$updateFakeSimpleQSTranslation(oplusSimpleQSFakeController, fCoerceIn);
            OplusSimpleQSFakeController.access$updateSimpleQSTranslation(oplusSimpleQSFakeController, fCoerceIn);
        }
        if (LogUtil.panicEnabled) {
            LogBuffer logBuffer = oplusSimpleQSFakeController.getLogBuffer();
            LogLevel logLevel = LogLevel.DEBUG;
            float extraSimpleQSFootTranslationY = SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY();
            OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            Float fValueOf = oplusQSSimpleHeader2 != null ? Float.valueOf(oplusQSSimpleHeader2.getTranslationY()) : null;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("fraction ", f, " ,fractionTemp:", fCoerceIn, ",extraSimpleQSFootTranslationY：");
            sbM.append(extraSimpleQSFootTranslationY);
            sbM.append(",oplusQSSimpleHeader.ty: ");
            sbM.append(fValueOf);
            logBuffer.log("OplusSimpleQSFakeController", logLevel, sbM.toString(), (Throwable) null);
        }
        if (f == 0 || f == 1065353216) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("fraction ", f, " ,fraction = 0 or 1,extraSimpleQSFootTranslationY: ", SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY(), "OplusSimpleQSFakeController-->"));
            }
            oplusSimpleQSFakeController.setFakeViewLocationUpdateFlag(true);
            oplusSimpleQSFakeController.resetExtraTakeOverCondition();
            oplusSimpleQSFakeController.resetAllViewsTransitionAlpha(f);
            oplusSimpleQSFakeController.hideQsFakeContainer("fraction = 0 or 1", oplusSimpleQSFakeController.statusBarStateController.getState() == 1);
        }
    }
}
