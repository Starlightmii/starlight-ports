package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.util.Log;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.effect.DampingCurve;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDefaultScrollCalculator implements CapsuleScrollCalculator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleDefaultScrollCalculator";
    public int capsuleHeight;
    public int capsuleWidth;
    public int earMargin;
    public int earToCapsuleDistance;
    public int earWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final int getBASE_CARD_MIN_WIDTH() {
            return (int) CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getBaseCardMinWidth().get().floatValue();
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int calculateBaseCardVerticalClip(int i, float f, int i2, CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, CapsuleViewState.ScrollClipInfo scrollClipInfo) {
        float fAbs = Math.abs(f);
        float capsuleWidth = getCapsuleWidth();
        if (fAbs > capsuleWidth) {
            fAbs = capsuleWidth;
        }
        float capsuleWidth2 = fAbs / getCapsuleWidth();
        if (f == 0.0f) {
            return i2;
        }
        int capsuleWidth3 = (getCapsuleWidth() - scrollClipInfo.getHorizontalClipTotal()) - ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.getClipWidth();
        int capsuleHeight = (getCapsuleHeight() - ((int) (((1.0f - ExtStateAnimParams.CommonParams.INSTANCE.getTOUCH_SCALE().get().floatValue()) * getCapsuleHeight()) * capsuleWidth2))) - (i2 * 2);
        if (capsuleHeight > capsuleWidth3) {
            capsuleHeight = capsuleWidth3;
        }
        int capsuleHeight2 = (getCapsuleHeight() - capsuleHeight) / 2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("calcVerticalClip(");
            sb.append(capsuleItemView);
            sb.append("): realWidth:");
            sb.append(capsuleWidth3);
            sb.append(", clipV: ");
            MobileInputLogger$.ExternalSyntheticOutline0.m(capsuleHeight2, "CapsuleDefaultScrollCalculator-->", "SystemUi--Notification", sb);
        }
        return capsuleHeight2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final int calculateNextCardVerticalClip(int i) {
        int capsuleHeight = getCapsuleHeight();
        if (i > capsuleHeight) {
            i = capsuleHeight;
        }
        return getCapsuleHeight() - i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getBaseCardMinWidth() {
        return Companion.getBASE_CARD_MIN_WIDTH();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final float getBaseCardTransX(float f) {
        int iAbs = (int) Math.abs(f);
        int capsuleWidth = getCapsuleWidth() - Companion.getBASE_CARD_MIN_WIDTH();
        float f2 = 0.0f;
        if (iAbs > capsuleWidth) {
            int i = iAbs - capsuleWidth;
            DampingCurve dampingCurve = DampingCurve.INSTANCE;
            CapsuleAnimParams.CapsuleScrollParams capsuleScrollParams = CapsuleAnimParams.CapsuleScrollParams.INSTANCE;
            float fFloatValue = capsuleScrollParams.getBaseCardTranxMax().get().floatValue();
            float fFloatValue2 = capsuleScrollParams.getBaseCardTranXRatio().get().floatValue();
            float f3 = i;
            dampingCurve.getClass();
            float fAbs = (1.0f - (1.0f / (((Math.abs(f3) * fFloatValue2) / fFloatValue) + 1.0f))) * fFloatValue;
            if (f3 < 0.0f) {
                fAbs = -fAbs;
            }
            f2 = f < 0.0f ? fAbs : -fAbs;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("getBaseTransX: ", f, LogUtil.SEPARATOR, f2, "CapsuleDefaultScrollCalculator-->"));
        }
        return f2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final int getCapsuleBetweenMargin(int i, int i2) {
        int capsuleWidth = (int) (getCapsuleWidth() * 0.8f);
        if (i < i2) {
            return 0;
        }
        if (i < capsuleWidth) {
            int i3 = i - (i2 + ((int) ((i - i2) / 2.0f)));
            if (i3 > 25) {
                return 25;
            }
            return i3;
        }
        int i4 = 25 - (i - capsuleWidth);
        if (i4 < 0) {
            return 0;
        }
        return i4;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getCapsuleHeight() {
        return this.capsuleHeight;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getCapsuleWidth() {
        return this.capsuleWidth;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getEarMargin() {
        return this.earMargin;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getEarToCapsuleDistance() {
        return this.earToCapsuleDistance;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getEarWidth() {
        return this.earWidth;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public /* bridge */ /* synthetic */ int getMaxOverScrollWidth() {
        return super.getMaxOverScrollWidth();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getNextCardMinWidth() {
        return 0;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void setCapsuleHeight(int i) {
        this.capsuleHeight = i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void setCapsuleWidth(int i) {
        this.capsuleWidth = i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void setEarMargin(int i) {
        this.earMargin = i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void setEarToCapsuleDistance(int i) {
        this.earToCapsuleDistance = i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void setEarWidth(int i) {
        this.earWidth = i;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public /* bridge */ /* synthetic */ void updateBaseCardScale(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f) {
        super.updateBaseCardScale(capsuleItemView, capsuleViewState, f);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateEarState(CapsuleEarController capsuleEarController, int i, boolean z) {
        capsuleEarController.setNeedShow(capsuleEarController.getNeedShow() && i == 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("updateEarState: needShow:", i, ", scrollX=", "CapsuleDefaultScrollCalculator-->", capsuleEarController.getNeedShow()));
        }
        capsuleEarController.setTransitionX(0.0f);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateNextCardOverScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, float f2) {
        capsuleViewState.setWidth(getCapsuleWidth() + ((int) f2));
        capsuleViewState.setXTranslation(f > 0.0f ? f2 / 2.0f : (-f2) / 2.0f);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public /* bridge */ /* synthetic */ void updateNextCardScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, int i) {
        super.updateNextCardScrollState(capsuleItemView, capsuleViewState, f, i);
    }
}
