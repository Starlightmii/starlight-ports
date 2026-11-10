package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.view.View;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder$panelAlphaAnimator$1 implements BiConsumer {
    public static final EditableBrightnessViewHolder$panelAlphaAnimator$1 INSTANCE = new EditableBrightnessViewHolder$panelAlphaAnimator$1();

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((View) obj).setTransitionAlpha(((Float) obj2).floatValue() / 255.0f);
    }
}
