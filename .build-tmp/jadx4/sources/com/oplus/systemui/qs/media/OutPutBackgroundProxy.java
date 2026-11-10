package com.oplus.systemui.qs.media;

import android.content.Context;
import android.util.StateSet;
import android.view.View;
import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.res.model.DrawableStateKt;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusContextUtil;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OutPutBackgroundProxy {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OutPutBackgroundProxy";
    private final View containerView;
    public final float cornerRadius;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OutPutBackgroundProxy(View view, float f) {
        this.containerView = view;
        this.cornerRadius = f;
    }

    public final void applyBackgroundStyle() {
        Context context = this.containerView.getContext();
        GradientTileDrawable gradientTileDrawable = (GradientTileDrawable) QSDrawableBuilder.build$default(new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() ? context.getColor(2131103253) : OplusContextUtil.getAttrColor(context, 2130969298)))})), this.containerView, true, 4);
        this.containerView.setBackground(gradientTileDrawable);
        OplusRoundParams fullCornerParams = OplusQsSmoothRoundUtil.getFullCornerParams(this.cornerRadius);
        gradientTileDrawable.setPathProvider(new RoundRectOutlineProvider(fullCornerParams.getRadius(), Float.valueOf(fullCornerParams.getWeight())));
    }
}
