package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMetaBallScrollCalculator implements CapsuleScrollCalculator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MetaBallScrollCalculator";
    public int capsuleHeight;
    public int capsuleWidth;
    public int earMargin;
    public int earToCapsuleDistance;
    public int earToCapsuleTranslationX;
    public int earWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int calculateBaseCardVerticalClip(int i, float f, int i2, CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, CapsuleViewState.ScrollClipInfo scrollClipInfo) {
        int capsuleWidth = getCapsuleWidth() - getBaseCardMinWidth();
        float capsuleHeight = getCapsuleHeight() - CapsuleEarShapeTool.INSTANCE.getCapsuleStartHeight();
        if (i < capsuleWidth && i >= 0) {
            capsuleHeight *= i / capsuleWidth;
        }
        int i3 = (int) (capsuleHeight / 2.0f);
        return i3 < i2 ? i2 : i3;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final int calculateNextCardVerticalClip(int i) {
        if (i > getCapsuleWidth()) {
            return 0;
        }
        return (int) ((getCapsuleHeight() - CapsuleEarShapeTool.INSTANCE.getCapsuleStartHeight()) * ((getCapsuleWidth() - i) / getCapsuleWidth()));
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public int getBaseCardMinWidth() {
        return getEarWidth() + 1;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final float getBaseCardTransX(float f) {
        float f2;
        int iAbs = (int) Math.abs(f);
        int i = this.earToCapsuleTranslationX;
        if (iAbs <= getCapsuleWidth() - i) {
            int capsuleWidth = getCapsuleWidth();
            boolean z = f < 0.0f;
            float fAbs = Math.abs(f);
            float f3 = i;
            f2 = z ? -f3 : f3;
            float f4 = capsuleWidth - f3;
            if (fAbs <= f4) {
                f2 *= fAbs / f4;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("updateBaseCardXTranslation: scrollX=", f, ", translationX=", f2, "MetaBallScrollCalculator-->"));
            }
        } else {
            f2 = i;
            if (f < 0.0f) {
                f2 = -f2;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("getBaseTransX: ", f, LogUtil.SEPARATOR, f2, "MetaBallScrollCalculator-->"));
        }
        return f2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final int getCapsuleBetweenMargin(int i, int i2) {
        return 0;
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
        return getEarWidth();
    }

    public final float getNextCardTransX(float f) {
        boolean z = f < 0.0f;
        float fAbs = Math.abs(f);
        float f2 = this.earToCapsuleTranslationX;
        if (!z) {
            f2 = -f2;
        }
        return fAbs <= 0.0f ? f2 : (1.0f - (fAbs / getCapsuleWidth())) * f2;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public final void initCapsuleParams(int i, int i2, int i3, int i4) {
        super.initCapsuleParams(i, i2, i3, i4);
        this.earToCapsuleTranslationX = getEarToCapsuleDistance() + 1;
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateBaseCardScale(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f) {
        Float fValueOf;
        int iAbs = Math.abs((int) f);
        if (iAbs <= getCapsuleWidth()) {
            fValueOf = Float.valueOf(1.0f);
        } else if (iAbs <= getCapsuleWidth() + this.earToCapsuleTranslationX) {
            fValueOf = Float.valueOf(1.0f - ((iAbs - getCapsuleWidth()) / this.earToCapsuleTranslationX));
        } else {
            fValueOf = iAbs > getCapsuleWidth() + this.earToCapsuleTranslationX ? Float.valueOf(0.0f) : null;
        }
        if (fValueOf != null) {
            float fFloatValue = fValueOf.floatValue();
            ViewExpandKtKt.setPivotXY(capsuleItemView, f < 0.0f ? capsuleItemView.getLocation().getLocationWithoutClip().left : capsuleItemView.getLocation().getLocationWithoutClip().right, capsuleItemView.getLocation().getLocationWithoutClip().centerY());
            capsuleViewState.setScaleX(fFloatValue);
            capsuleViewState.setScaleY(fFloatValue);
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateEarState(CapsuleEarController capsuleEarController, int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MetaBallScrollCalculator-->", "SystemUi--Notification", OplusLockIconView$.ExternalSyntheticOutline0.m("updateEarState: needShow:", i, ", scrollX=", ", hFling=", capsuleEarController.getNeedShow()), z);
        }
        float capsuleWidth = 0.0f;
        if (!capsuleEarController.getNeedShow()) {
            capsuleEarController.setTransitionX(0.0f);
            return;
        }
        AppCompatImageView leftEarView = i < 0 ? capsuleEarController.getLeftEarView() : capsuleEarController.getRightEarView();
        AppCompatImageView rightEarView = i < 0 ? capsuleEarController.getRightEarView() : capsuleEarController.getLeftEarView();
        int capsuleWidth2 = getCapsuleWidth() - getCapsuleHeight();
        int iAbs = Math.abs(i);
        if (iAbs == 0) {
            ViewExpandKtKt.setScaleXY(leftEarView, 1.0f, 1.0f);
            ViewExpandKtKt.setScaleXY(rightEarView, 1.0f, 1.0f);
            return;
        }
        if (z) {
            if (iAbs <= getCapsuleWidth() + this.earToCapsuleTranslationX) {
                capsuleWidth = iAbs <= getCapsuleWidth() ? 1.0f : 1.0f - ((iAbs - getCapsuleWidth()) / this.earToCapsuleTranslationX);
            }
            float fMax = Math.max(capsuleWidth, leftEarView.getScaleX());
            ViewExpandKtKt.setScaleXY(leftEarView, fMax, fMax);
            ViewExpandKtKt.setScaleXY(rightEarView, 1.0f, 1.0f);
            return;
        }
        if (iAbs > 0) {
            if (iAbs > capsuleWidth2) {
                iAbs = capsuleWidth2;
            }
            float f = iAbs / capsuleWidth2;
            float f2 = 1.0f - f;
            ViewExpandKtKt.setScaleXY(rightEarView, f, f);
            ViewExpandKtKt.setScaleXY(leftEarView, f2, f2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateNextCardOverScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, float f2) {
        float fAbs = (Math.abs(f2) / getCapsuleWidth()) + 1.0f;
        ViewExpandKtKt.setPivotXY(capsuleItemView, f > 0.0f ? capsuleItemView.getLocation().getLocationWithoutClip().left : capsuleItemView.getLocation().getLocationWithoutClip().right, capsuleItemView.getLocation().getLocationWithoutClip().centerY());
        capsuleViewState.setScaleX(fAbs);
        capsuleViewState.setXTranslation(getNextCardTransX(f));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("updateNextCardClipState expandWidth=", f2, ", x=", capsuleViewState.getXTranslation(), ", scale="), fAbs, "MetaBallScrollCalculator-->", "SystemUi--Notification");
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculator
    public void updateNextCardScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, int i) {
        capsuleViewState.setXTranslation(getNextCardTransX(f));
    }
}
