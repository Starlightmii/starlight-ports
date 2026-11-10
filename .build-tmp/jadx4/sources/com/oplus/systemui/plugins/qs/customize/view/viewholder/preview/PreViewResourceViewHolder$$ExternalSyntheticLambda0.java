package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.widget.ImageView;
import com.oplus.systemui.plugins.qs.widget.OplusPreviewTileViewInfoProvider;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundProxy;
import com.oplus.systemui.qs.base.widget.QsViewOutlineProvider;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PreViewResourceViewHolder$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PreViewResourceViewHolder f$0;

    public /* synthetic */ PreViewResourceViewHolder$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        PreViewResourceViewHolder preViewResourceViewHolder = this.f$0;
        switch (i) {
            case 0:
                int i2 = PreViewResourceViewHolder.$r8$clinit;
                ImageView imageView = preViewResourceViewHolder.resourceHolderView;
                if (imageView == null) {
                    imageView = null;
                }
                QsViewOutlineProvider.QsViewRoundRectOutlineProvider qsViewRoundRectOutlineProvider = new QsViewOutlineProvider.QsViewRoundRectOutlineProvider(imageView, PreViewResourceViewHolder$bgViewInfoProvider$2$1.INSTANCE);
                ImageView imageView2 = preViewResourceViewHolder.resourceHolderView;
                return new OplusPreviewTileViewInfoProvider(qsViewRoundRectOutlineProvider, imageView2 != null ? imageView2 : null);
            default:
                return QsViewBackgroundProxy.Companion.getQsStaticViewBackgroundProxy(preViewResourceViewHolder.getBgViewInfoProvider());
        }
    }
}
