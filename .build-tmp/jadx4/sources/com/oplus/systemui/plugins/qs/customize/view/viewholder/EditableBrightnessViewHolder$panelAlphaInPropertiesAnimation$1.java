package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder$panelAlphaInPropertiesAnimation$1 implements Consumer {
    public /* synthetic */ EditableBrightnessViewHolder this$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Function0 function0 = this.this$0.endAction;
        if (function0 != null) {
            function0.invoke();
        }
    }
}
