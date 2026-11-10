package com.oplus.systemui.separate;

import android.view.View;
import com.coui.animatekit.progressive.HorizontalProgressiveAnimHelper;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class HorizontalProgressiveAnimController$resetHelper$1$1 implements Consumer {
    public /* synthetic */ HorizontalProgressiveAnimHelper $tmp0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.$tmp0.unregister(new View[]{(View) obj});
    }
}
