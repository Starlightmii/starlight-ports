package com.oplus.systemui.shade;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        COUISpringAnimation cOUISpringAnimation = (COUISpringAnimation) obj;
        switch (this.$r8$classId) {
            case 0:
                if (cOUISpringAnimation == null) {
                    return null;
                }
                if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
                    cOUISpringAnimation = null;
                }
                if (cOUISpringAnimation == null) {
                    return null;
                }
                cOUISpringAnimation.animateToFinalPosition(1.0f);
                return Unit.INSTANCE;
            default:
                if (cOUISpringAnimation == null) {
                    return null;
                }
                if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
                    cOUISpringAnimation = null;
                }
                if (cOUISpringAnimation == null) {
                    return null;
                }
                cOUISpringAnimation.animateToFinalPosition(1.0f);
                return Unit.INSTANCE;
        }
    }
}
