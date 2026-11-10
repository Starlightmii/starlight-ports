package com.oplus.systemui.qs.base.widget;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseQsViewBackground implements QsViewBackground {
    private final QsViewInfoProvider qsView;

    public BaseQsViewBackground(QsViewInfoProvider qsViewInfoProvider) {
        this.qsView = qsViewInfoProvider;
    }

    public GradientDrawable createOrUpdateGradientDrawable(GradientDrawable gradientDrawable) {
        float weight;
        float radius;
        SmoothCornersGradientDrawable smoothCornersGradientDrawable;
        View backgroundView = this.qsView.getBackgroundView();
        OplusRoundParams roundParams = getBgOutlineProvider().getRoundParams(backgroundView);
        float radius2 = roundParams != null ? roundParams.getRadius() : 0.0f;
        float weight2 = roundParams != null ? roundParams.getWeight() : 0.0f;
        boolean z = roundParams != null;
        boolean z2 = gradientDrawable instanceof SmoothCornersGradientDrawable;
        if (z2) {
            SmoothCornersGradientDrawable smoothCornersGradientDrawable2 = (SmoothCornersGradientDrawable) gradientDrawable;
            radius = smoothCornersGradientDrawable2.getRadius();
            weight = smoothCornersGradientDrawable2.getWeight();
        } else {
            weight = 0.0f;
            radius = 0.0f;
        }
        boolean z3 = (radius == radius2 && weight == weight2 && (!z || z2) && (z || !z2)) ? false : true;
        if (gradientDrawable != null && !z3) {
            if (gradientDrawable instanceof SmoothCornersGradientDrawable) {
                if (roundParams != null && roundParams.isCircle && backgroundView.getHeight() > 0) {
                    SmoothCornersGradientDrawable smoothCornersGradientDrawable3 = (SmoothCornersGradientDrawable) gradientDrawable;
                    smoothCornersGradientDrawable3.setSmoothRound(backgroundView.getHeight() / 2.0f, 0.0f);
                    smoothCornersGradientDrawable3.setRoundCircleType(false);
                    return gradientDrawable;
                }
                SmoothCornersGradientDrawable smoothCornersGradientDrawable4 = (SmoothCornersGradientDrawable) gradientDrawable;
                smoothCornersGradientDrawable4.setSmoothRound(radius2, weight2);
                smoothCornersGradientDrawable4.setRoundCircleType(roundParams != null ? roundParams.isCircle : false);
            }
            return gradientDrawable;
        }
        if (!z) {
            smoothCornersGradientDrawable = z2 ? (SmoothCornersGradientDrawable) gradientDrawable : null;
            if (smoothCornersGradientDrawable == null) {
                smoothCornersGradientDrawable = new SmoothCornersGradientDrawable();
            }
            smoothCornersGradientDrawable.setColor(0);
            smoothCornersGradientDrawable.setSmoothRound(0.0f, 0.0f);
            smoothCornersGradientDrawable.setRoundCircleType(false);
            return smoothCornersGradientDrawable;
        }
        smoothCornersGradientDrawable = z2 ? (SmoothCornersGradientDrawable) gradientDrawable : null;
        if (smoothCornersGradientDrawable == null) {
            smoothCornersGradientDrawable = new SmoothCornersGradientDrawable();
            smoothCornersGradientDrawable.setColor(0);
        }
        if (!roundParams.isCircle || backgroundView.getHeight() <= 0) {
            smoothCornersGradientDrawable.setSmoothRound(radius2, weight2);
            smoothCornersGradientDrawable.setRoundCircleType(roundParams.isCircle);
            return smoothCornersGradientDrawable;
        }
        smoothCornersGradientDrawable.setSmoothRound(backgroundView.getHeight() / 2.0f, 0.0f);
        smoothCornersGradientDrawable.setRoundCircleType(false);
        return smoothCornersGradientDrawable;
    }

    public abstract QsViewOutlineProvider getBgOutlineProvider();

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public /* bridge */ /* synthetic */ Pair getDeformBackgroundColor() {
        return super.getDeformBackgroundColor();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public /* bridge */ /* synthetic */ void handleStateChanged(QSTile.State state) {
        super.handleStateChanged(state);
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public /* bridge */ /* synthetic */ void setInDeform(boolean z) {
        super.setInDeform(z);
    }
}
