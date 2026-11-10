package com.oplus.systemui.plugins.qs.animator;

import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelExpandFraction$fractionAnimation$2$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ Ref.FloatRef $lastResponse;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSPanelExpandFraction this$0;

    public /* synthetic */ QSPanelExpandFraction$fractionAnimation$2$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                QSPanelExpandFraction.Companion.setScreenShotClick(false);
                QSPanelExpandFraction qSPanelExpandFraction = this.this$0;
                qSPanelExpandFraction.beingSkipToEnd = false;
                qSPanelExpandFraction.yVelocity = 0.0f;
                this.$lastResponse.element = qSPanelExpandFraction.maxFastBlurResponse;
                if (!z) {
                    if (f == 1.0f || f == 0.0f) {
                        float f3 = qSPanelExpandFraction.blurRawFraction;
                        float rawFraction = qSPanelExpandFraction.getRawFraction();
                        boolean z2 = ((COUIDynamicAnimation) qSPanelExpandFraction.getBlurFractionAnimation()).mRunning;
                        boolean useMotionBlur = qSPanelExpandFraction.stub.getUseMotionBlur();
                        StringBuilder sb = new StringBuilder("[onFlingEndListener]");
                        sb.append(f);
                        sb.append(" ");
                        sb.append(z);
                        sb.append(" blurRawFraction=");
                        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f3, " rawFraction=", rawFraction, " isRunning=");
                        LogUtil.i$default(LogUtil.TAG_QS, "QSPanelExpandFraction", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z2, " isMotionBlur=", useMotionBlur), null, 8);
                        QSPanelExpandFraction$$ExternalSyntheticLambda0 qSPanelExpandFraction$$ExternalSyntheticLambda0 = new QSPanelExpandFraction$$ExternalSyntheticLambda0(3);
                        qSPanelExpandFraction$$ExternalSyntheticLambda0.f$0 = f;
                        VarHandle.storeStoreFence();
                        qSPanelExpandFraction.notifyFractionChangeCallback(qSPanelExpandFraction$$ExternalSyntheticLambda0);
                        boolean z3 = f == 1.0f;
                        if (!z3) {
                            QSPanelExpandFraction.access$skipToEndForBlurAnimation(qSPanelExpandFraction, z3);
                        }
                        break;
                    }
                }
                break;
            default:
                QSPanelExpandFraction qSPanelExpandFraction2 = this.this$0;
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("addEndListener blurRawFraction=", qSPanelExpandFraction2.blurRawFraction, " rawFraction=", qSPanelExpandFraction2.getRawFraction(), " value=");
                sbM.append(f);
                sbM.append(" isCancelled=");
                sbM.append(z);
                LogUtil.i$default(LogUtil.TAG_QS, "QSPanelExpandFraction", sbM.toString(), null, 8);
                this.$lastResponse.element = qSPanelExpandFraction2.maxFastBlurResponse;
                QSPanelExpandFraction$$ExternalSyntheticLambda0 qSPanelExpandFraction$$ExternalSyntheticLambda1 = new QSPanelExpandFraction$$ExternalSyntheticLambda0(2);
                qSPanelExpandFraction$$ExternalSyntheticLambda1.f$0 = f;
                VarHandle.storeStoreFence();
                qSPanelExpandFraction2.notifyFractionChangeCallback(qSPanelExpandFraction$$ExternalSyntheticLambda1);
                break;
        }
    }
}
