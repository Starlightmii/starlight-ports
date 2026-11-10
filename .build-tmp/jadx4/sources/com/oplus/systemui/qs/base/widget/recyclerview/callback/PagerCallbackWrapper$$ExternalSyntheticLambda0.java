package com.oplus.systemui.qs.base.widget.recyclerview.callback;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PagerCallbackWrapper$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ int f$0;

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        int i2 = this.f$0;
        PagerCallback pagerCallback = (PagerCallback) obj;
        switch (i) {
            case 0:
                pagerCallback.onPageSizeChanged(i2);
                break;
            case 1:
                pagerCallback.onPageSelected(i2);
                break;
            default:
                pagerCallback.onPageScrollStateChanged(i2);
                break;
        }
        return Unit.INSTANCE;
    }
}
