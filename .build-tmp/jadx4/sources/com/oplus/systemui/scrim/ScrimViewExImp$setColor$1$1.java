package com.oplus.systemui.scrim;

import android.animation.ValueAnimator;
import androidx.core.graphics.ColorUtils;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimViewExImp$setColor$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ int $colorFrom;
    public /* synthetic */ ViewBlurProxy $it;
    public /* synthetic */ int $maskColor;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.$it.getBlurConfig().setBlurColor(ColorUtils.blendARGB(this.$colorFrom, this.$maskColor, ((Float) valueAnimator.getAnimatedValue()).floatValue()));
        this.$it.applyBlurConfig();
    }
}
