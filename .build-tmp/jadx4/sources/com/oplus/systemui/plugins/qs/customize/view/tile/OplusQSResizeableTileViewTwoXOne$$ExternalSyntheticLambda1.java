package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1 implements Function1 {
    public /* synthetic */ int f$0;

    public final Object invoke(Object obj) {
        int i = this.f$0;
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) obj;
        int i2 = OplusQSResizeableTileViewTwoXOne.$r8$clinit;
        int i3 = layoutParams.height;
        layoutParams.height = i;
        return Boolean.valueOf(i3 != i);
    }
}
