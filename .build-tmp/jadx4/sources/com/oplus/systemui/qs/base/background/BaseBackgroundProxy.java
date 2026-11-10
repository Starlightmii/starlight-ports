package com.oplus.systemui.qs.base.background;

import android.view.View;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BaseBackgroundProxy {
    private final TileDrawableWrapper bgDrawable = new TileDrawableWrapper(null);
    private final View bgView;
    private final RoundRectOutlineProvider outlineProvider;
    private final Function0 radiusProvider;
    private final Float weight;

    public BaseBackgroundProxy(View view, Float f, Function0 function0) {
        this.bgView = view;
        this.weight = f;
        this.radiusProvider = function0;
        this.outlineProvider = new RoundRectOutlineProvider(((Number) function0.invoke()).floatValue(), (Float) null);
    }

    public final TileDrawableWrapper getBgDrawable() {
        return this.bgDrawable;
    }

    public RoundRectOutlineProvider getOutlineProvider() {
        return this.outlineProvider;
    }

    public void refreshViewBackground() {
        this.bgView.setBackground(this.bgDrawable);
        getOutlineProvider().update(((Number) this.radiusProvider.invoke()).floatValue(), this.weight);
        CornerOutlineProvider pathProvider = this.bgDrawable.getPathProvider();
        RoundRectOutlineProvider outlineProvider = getOutlineProvider();
        TileDrawableWrapper tileDrawableWrapper = this.bgDrawable;
        if (pathProvider != outlineProvider) {
            tileDrawableWrapper.setPathProvider(getOutlineProvider());
        } else {
            tileDrawableWrapper.invalidatePath();
        }
        this.bgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getCustomizerDrawable().getValue(), this.bgView, true, 4));
    }
}
