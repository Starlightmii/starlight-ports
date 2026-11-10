package com.oplus.systemui.shade;

import android.util.MathUtils;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ float $endAlpha;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ float $startAlpha;
    public /* synthetic */ Function1 $update;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                this.$update.invoke(Float.valueOf(MathUtils.lerp(this.$startAlpha, this.$endAlpha, f)));
                break;
            default:
                this.$update.invoke(Float.valueOf(MathUtils.lerp(this.$startAlpha, this.$endAlpha, f)));
                break;
        }
    }
}
