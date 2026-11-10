package com.oplus.systemui.qs.media;

import com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9 implements Function1 {
    public /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder f$0;

    public final Object invoke(Object obj) {
        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.f$0;
        ((Boolean) obj).getClass();
        int i = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
        oplusMediaViewHolder.updateMediaOutputBtn$1(((Number) OplusQsVolumeIconView.Companion.getMusicStreamRoute().get()).intValue());
        return Unit.INSTANCE;
    }
}
