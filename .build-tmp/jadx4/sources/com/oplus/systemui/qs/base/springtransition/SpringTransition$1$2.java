package com.oplus.systemui.qs.base.springtransition;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpringTransition$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ SpringTransition this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        SpringTransition springTransition = this.this$0;
        String tag = springTransition.getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            sbM.append("[onTransitionEnd] cancelled = " + z + " ");
            Log.d("SystemUi--Common", sbM.toString());
        }
        if (!z) {
            springTransition.releaseInvalidProperty();
        }
        Iterator<TransitionProperty> allTargetProperties = springTransition.getAllTargetProperties();
        while (allTargetProperties.hasNext()) {
            allTargetProperties.next().onTransitionEnd(z);
        }
    }
}
