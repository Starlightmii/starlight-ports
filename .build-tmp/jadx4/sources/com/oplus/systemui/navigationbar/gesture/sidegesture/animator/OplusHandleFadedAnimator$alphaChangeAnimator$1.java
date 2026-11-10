package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleFadedAnimator$alphaChangeAnimator$1 extends FloatPropertyCompat {
    public final float getValue(Object obj) {
        return ((OplusNavigationHandle) obj).getFadedAnimProgress();
    }

    public final void setValue(Object obj, float f) {
        ((OplusNavigationHandle) obj).setFadedAnimProgress(f);
    }
}
