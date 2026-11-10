package com.oplus.systemui.statusbar.phone;

import android.content.res.Resources;
import android.util.MathUtils;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithmEx;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardClockPositionAlgorithmExImpl extends KeyguardClockPositionAlgorithmEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardClockPositionAl";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public float getClockScale() {
        return (getBase() == null || !getCanScaleFadePanel()) ? super.getClockScale() : MathUtils.lerp(0.9f, 1.0f, getFinalPanelExpansion());
    }

    public int getStatusViewBottomMargin(Resources resources) {
        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusLockScreenWallpaperController().getWallpaperType() == 2 ? resources.getDimensionPixelSize(2131168714) : super.getStatusViewBottomMargin(resources);
    }

    public void runAddition(KeyguardClockPositionAlgorithm.Result result) {
        KeyguardClockPositionAlgorithm base = getBase();
        if (base == null || !LogUtil.getNormalDebug()) {
            return;
        }
        KeyguardClockPositionAlgorithmExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new KeyguardClockPositionAlgorithmExImpl$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = base;
        externalSyntheticLambda0.f$2 = result;
        VarHandle.storeStoreFence();
        KgdLogUtil.d$default(TAG, "ClockPosition", 10, false, externalSyntheticLambda0, 8);
    }

    public final void setupAfter(float f, float f2) {
        KeyguardClockPositionAlgorithm base;
        setOriginalPanelExpansion(f);
        if (getCanScaleFadePanel() && (base = getBase()) != null && base.mIsSplitShade) {
            f = f2;
        }
        setFinalPanelExpansion(f);
    }
}
