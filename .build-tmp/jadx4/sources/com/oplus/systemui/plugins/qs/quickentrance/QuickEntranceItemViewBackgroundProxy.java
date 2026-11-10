package com.oplus.systemui.plugins.qs.quickentrance;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import com.oplus.systemui.qs.base.background.BaseBackgroundProxy;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QuickEntranceItemViewBackgroundProxy extends BaseBackgroundProxy {
    private final View bgView;
    private final Function0 radiusProvider;
    private final Float weight;

    public QuickEntranceItemViewBackgroundProxy(View view, Float f, Function0 function0) {
        super(view, f, function0);
        this.bgView = view;
        this.weight = f;
        this.radiusProvider = function0;
    }

    @Override // com.oplus.systemui.qs.base.background.BaseBackgroundProxy
    public final void refreshViewBackground() {
        this.bgView.setBackground(getBgDrawable());
        getOutlineProvider().update(((Number) this.radiusProvider.invoke()).floatValue(), this.weight);
        if (getBgDrawable().getPathProvider() != getOutlineProvider()) {
            getBgDrawable().setPathProvider(getOutlineProvider());
        } else {
            getBgDrawable().invalidatePath();
        }
        getBgDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getQuickEntranceDrawable().getValue(), this.bgView, true, 4));
        View view = this.bgView;
        ImageView imageView = view instanceof ImageView ? (ImageView) view : null;
        if (imageView != null) {
            imageView.setImageTintList(QsColorUtil.isGlobalThemeApplied(((ImageView) view).getContext()) ? ColorStateList.valueOf(((ImageView) this.bgView).getContext().getColor(2131103746)) : ColorStateList.valueOf(-1));
        }
    }
}
