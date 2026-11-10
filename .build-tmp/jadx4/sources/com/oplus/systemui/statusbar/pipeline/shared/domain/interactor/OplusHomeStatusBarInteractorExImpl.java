package com.oplus.systemui.statusbar.pipeline.shared.domain.interactor;

import android.content.Context;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.OplusHomeStatusBarInteractorEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHomeStatusBarInteractorExImpl extends OplusHomeStatusBarInteractorEx {
    private final Context context;
    private final KeyguardStateController keyguardStateController;

    public OplusHomeStatusBarInteractorExImpl(Context context, KeyguardStateController keyguardStateController) {
        this.context = context;
        this.keyguardStateController = keyguardStateController;
    }

    public final boolean shouldAnimateWhenDisableChanged() {
        if (ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
            return true;
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        if (keyguardStateControllerImpl2.mOccluded) {
            return true;
        }
        return (keyguardStateControllerImpl2.mShowing || keyguardStateControllerImpl2.mKeyguardFadingAway || keyguardStateControllerImpl.getEx().isUnlockAnimationRunning()) ? false : true;
    }
}
