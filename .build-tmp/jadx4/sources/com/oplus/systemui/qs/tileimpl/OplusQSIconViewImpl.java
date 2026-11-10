package com.oplus.systemui.qs.tileimpl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.node.LayoutNode$;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.res.widget.QSIconViewProxy;
import com.oplus.systemui.qs.helper.OplusQSTileSizeHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSIconViewImpl extends QSIconView {
    public int iconSizePx;
    private final QSIconViewProxy iconViewProxy;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSIconViewImpl(Context context) {
        super(context);
        this.iconViewProxy = new QSIconViewProxy(this);
        this.iconSizePx = OplusQSTileSizeHelper.getQsTileSize(context, 2);
        setDuplicateParentStateEnabled(true);
        addView(getIconView());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setIconSizePx(int i) {
        if (i == this.iconSizePx) {
            return;
        }
        this.iconSizePx = i;
        requestLayout();
    }

    public final void disableAnimation() {
        this.iconViewProxy.setAnimationEnable(false);
    }

    public final void enableAnimation() {
        this.iconViewProxy.setAnimationEnable(true);
    }

    public View getIconView() {
        return this.iconViewProxy.getIconView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.ViewGroup*/.onAttachedToWindow();
        onIconTintUpdate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        setIconSizePx(OplusQSTileSizeHelper.getQsTileSize(((ViewGroup) this).mContext, 2));
    }

    public final void onIconTintUpdate() {
        QSIconViewProxy qSIconViewProxy = this.iconViewProxy;
        TileIconColorState tileIconColorState = (TileIconColorState) StdQSResPool.tileIconColorState.getValue();
        qSIconViewProxy.setTintList(tileIconColorState != null ? tileIconColorState.getColorStateList(null) : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View iconView = getIconView();
        int measuredWidth = iconView.getMeasuredWidth();
        int measuredHeight = iconView.getMeasuredHeight();
        int measuredWidth2 = (getMeasuredWidth() - measuredWidth) / 2;
        int measuredHeight2 = (getMeasuredHeight() - measuredHeight) / 2;
        iconView.layout(measuredWidth2, measuredHeight2, measuredWidth + measuredWidth2, measuredHeight + measuredHeight2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        getIconView().measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(this.iconSizePx, 1073741824));
        setMeasuredDimension(size, getIconView().getMeasuredHeight());
    }

    public void setIcon(QSTile.State state, boolean z) {
        this.iconViewProxy.setIcon(state, z);
    }

    public final void setIconTint(ColorStateList colorStateList) {
        this.iconViewProxy.setTintList(colorStateList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        String strM = LayoutNode$.ExternalSyntheticOutline0.m(1, 0, super/*android.view.ViewGroup*/.toString());
        TileIconColorState tileIconColorState = StdQSResPool.getTileIconColorState();
        return strM.concat("[ tint color=" + (tileIconColorState != null ? tileIconColorState.getColorStateList(null) : null));
    }
}
