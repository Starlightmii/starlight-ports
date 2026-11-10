package com.oplus.systemui.qs.media;

import com.oplus.systemui.qs.media.multilight.OplusMediaItemBackgroundDrawable;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusMediaViewPagerAdapter f$0;

    public /* synthetic */ OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.f$0;
        switch (i) {
            case 0:
                return ArraysKt___ArraysKt.toSet(oplusMediaViewPagerAdapter.mContext.getResources().getStringArray(2130903238));
            default:
                int i2 = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
                if (oplusMediaViewPagerAdapter.isMiddleOrHighAnimLevel) {
                    return new OplusMediaItemBackgroundDrawable(oplusMediaViewPagerAdapter.mContext);
                }
                return null;
        }
    }
}
