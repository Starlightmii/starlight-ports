package com.oplus.systemui.keyguard.wallpaper.pictorial.buried;

import android.content.Context;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialBuriedPointPorxy extends AbsDailyStatisticProxy {
    @Override // com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy
    public final void onStatistic(Context context) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion() && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialBuriedHelper() != null) {
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialBuriedHelper().onStatistic(context);
                return;
            }
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (!KeyguardFeatureOption.isSupportOplusGLPictorial || ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialBuriedHelper() == null) {
            return;
        }
        KgdLogUtil.i("PictorialBuriedPointPorxy", "onStatistic");
        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialBuriedHelper().onStatistic(context);
    }
}
