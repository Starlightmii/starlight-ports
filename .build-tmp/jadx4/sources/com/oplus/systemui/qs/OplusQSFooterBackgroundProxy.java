package com.oplus.systemui.qs;

import android.view.View;
import com.airbnb.lottie.compose.LottieAnimationKt$;
import com.oplus.systemui.qs.base.background.BaseBackgroundProxy;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFooterBackgroundProxy extends BaseBackgroundProxy {
    private final View bgView;
    private final RoundRectOutlineProvider outlineProvider;
    private final Float weight;

    public OplusQSFooterBackgroundProxy(View view, Float f, float f2) {
        LottieAnimationKt$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new LottieAnimationKt$.ExternalSyntheticLambda3();
        externalSyntheticLambda3.f$0 = f2;
        VarHandle.storeStoreFence();
        super(view, f, externalSyntheticLambda3);
        this.bgView = view;
        this.weight = f;
        this.outlineProvider = new RoundRectOutlineProvider(f2, (Float) null);
    }

    @Override // com.oplus.systemui.qs.base.background.BaseBackgroundProxy
    public RoundRectOutlineProvider getOutlineProvider() {
        return this.outlineProvider;
    }

    @Override // com.oplus.systemui.qs.base.background.BaseBackgroundProxy
    public final void refreshViewBackground() {
        this.bgView.setBackground(getBgDrawable());
        getBgDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) StdQSResPool.quickEntranceDrawable.getValue(), this.bgView, true, 4));
        if (getBgDrawable().getPathProvider() != getOutlineProvider()) {
            getBgDrawable().setPathProvider(getOutlineProvider());
        } else {
            getBgDrawable().invalidatePath();
        }
    }
}
