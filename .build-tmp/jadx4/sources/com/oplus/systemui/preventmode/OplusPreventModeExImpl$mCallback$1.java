package com.oplus.systemui.preventmode;

import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeExImpl$mCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ OplusPreventModeExImpl this$0;

    public final void onKeyguardShowingChanged() {
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        boolean mKeyguardIsShowing = oplusPreventModeExImpl.getMKeyguardIsShowing();
        boolean z = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing;
        boolean z2 = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onKeyguardShowingChanged; mKeyguardIsShowing: ", "; isShowing: ", ",isOccluded:", mKeyguardIsShowing, z);
        sbM.append(z2);
        KgdLogUtil.i(OplusPreventModeExImpl.TAG, sbM.toString());
        if (oplusPreventModeExImpl.getMKeyguardIsShowing() == ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing && oplusPreventModeExImpl.getMOccluded() == ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded) {
            oplusPreventModeExImpl.setMOccluded(((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded);
        } else {
            oplusPreventModeExImpl.setMOccluded(((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded);
            if (((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onKeyguardShow");
                if (!oplusPreventModeExImpl.getMShowPreventModeWindow()) {
                    oplusPreventModeExImpl.unregisterSensorListener();
                    oplusPreventModeExImpl.registerSensorListener();
                }
            } else {
                KgdLogUtil.i(OplusPreventModeExImpl.TAG, "onKeyguardHide");
                oplusPreventModeExImpl.hidePreventModeView(false);
                oplusPreventModeExImpl.unregisterSensorListener();
            }
        }
        oplusPreventModeExImpl.setMKeyguardIsShowing(((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing);
    }
}
