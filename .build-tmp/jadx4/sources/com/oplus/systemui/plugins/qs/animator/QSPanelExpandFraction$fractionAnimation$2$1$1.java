package com.oplus.systemui.plugins.qs.animator;

import androidx.compose.animation.core.MonoSpline$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelExpandFraction$fractionAnimation$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ Ref.FloatRef $lastResponse;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ COUISpringAnimation $this_apply;
    public /* synthetic */ QSPanelExpandFraction this$0;

    public /* synthetic */ QSPanelExpandFraction$fractionAnimation$2$1$1(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                QSPanelExpandFraction qSPanelExpandFraction = this.this$0;
                float f3 = qSPanelExpandFraction.maxFastBlurResponse;
                qSPanelExpandFraction.setRawFraction(f);
                if (qSPanelExpandFraction.isOnFling && qSPanelExpandFraction.panelClosedOnDown) {
                    float rawHideResponse = qSPanelExpandFraction.toExpand ? 0.2f : QSPanelExpandFraction.Companion.getRawHideResponse();
                    if (qSPanelExpandFraction.yVelocity > 1000.0f) {
                        rawHideResponse = MonoSpline$.ExternalSyntheticOutline0.m(f3, rawHideResponse, RangesKt___RangesKt.coerceIn(qSPanelExpandFraction.getRawFraction() / 0.2f, 0.0f, 1.0f), f3);
                    }
                    Ref.FloatRef floatRef = this.$lastResponse;
                    if (floatRef.element != rawHideResponse) {
                        floatRef.element = rawHideResponse;
                        AnimatorUtils.tryUpdateStiffness(this.$this_apply, AnimatorUtils.responseToStiffness(rawHideResponse));
                        break;
                    }
                }
                break;
            default:
                QSPanelExpandFraction qSPanelExpandFraction2 = this.this$0;
                float f4 = qSPanelExpandFraction2.maxFastBlurResponse;
                qSPanelExpandFraction2.setBlurRawFraction(f);
                if (qSPanelExpandFraction2.isOnFling) {
                    if (qSPanelExpandFraction2.panelClosedOnDown || !qSPanelExpandFraction2.toExpand) {
                        boolean z = qSPanelExpandFraction2.toExpand;
                        float fM = 0.35f;
                        if (z && qSPanelExpandFraction2.yVelocity > 1000.0f) {
                            fM = MonoSpline$.ExternalSyntheticOutline0.m(f4, 0.35f, RangesKt___RangesKt.coerceIn(qSPanelExpandFraction2.blurRawFraction / 0.2f, 0.0f, 1.0f), f4);
                        } else if (!z && qSPanelExpandFraction2.getRawFraction() < 0.015f) {
                            fM = 0.15f;
                        } else if (!qSPanelExpandFraction2.stub.getUseMotionBlur() && !qSPanelExpandFraction2.toExpand) {
                            fM = 0.45f;
                        }
                        Ref.FloatRef floatRef2 = this.$lastResponse;
                        if (floatRef2.element != fM) {
                            floatRef2.element = fM;
                            AnimatorUtils.tryUpdateStiffness(this.$this_apply, AnimatorUtils.responseToStiffness(fM));
                            break;
                        }
                    }
                }
                break;
        }
    }
}
