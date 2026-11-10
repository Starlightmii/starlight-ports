package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.view.View;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder$panelAlphaAnimator$2 implements Function {
    public static final EditableBrightnessViewHolder$panelAlphaAnimator$2 INSTANCE = new EditableBrightnessViewHolder$panelAlphaAnimator$2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Float.valueOf(((View) obj).getTransitionAlpha() * 255.0f);
    }
}
