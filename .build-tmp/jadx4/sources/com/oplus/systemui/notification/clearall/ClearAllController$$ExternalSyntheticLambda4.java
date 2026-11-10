package com.oplus.systemui.notification.clearall;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.oplus.systemui.shade.AnimationType;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ClearAllController$$ExternalSyntheticLambda4 implements Function3 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ClearAllController f$0;
    public /* synthetic */ OplusClearAllButton f$1;

    public /* synthetic */ ClearAllController$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.$r8$classId) {
            case 0:
                ClearAllController clearAllController = this.f$0;
                AppCompatImageView appCompatImageView = this.f$1;
                float fFloatValue = ((Float) obj3).floatValue();
                clearAllController.playingAnimation = false;
                String strName = ((AnimationType) obj2).name();
                float f = clearAllController.targetAlpha;
                float alpha = appCompatImageView.getAlpha();
                float scaleX = appCompatImageView.getScaleX();
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("clearAllShow onAnimationEnd: ", strName, " ", fFloatValue, " targetAlpha:");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, " _view.alpha：", alpha, "_view.scaleX：");
                sbM.append(scaleX);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "ClearAllController", sbM.toString(), null, 8);
                return Unit.INSTANCE;
            default:
                return ClearAllController.$r8$lambda$iA31iO7oOzwElCOMnAwFEe7ks6w(this.f$1, this.f$0, (AnimationType) obj2, ((Float) obj3).floatValue());
        }
    }
}
