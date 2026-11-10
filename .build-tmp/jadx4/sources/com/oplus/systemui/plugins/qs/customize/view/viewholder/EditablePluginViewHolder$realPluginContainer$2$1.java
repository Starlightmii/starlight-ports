package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.view.OplusSmoothRoundedManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditablePluginViewHolder$realPluginContainer$2$1 extends FrameLayout implements ITransitionDesc, IEditableView, IQSSpotLightConfig {
    public final /* synthetic */ Context $context;
    public boolean isEditMode;
    public final /* synthetic */ EditablePluginViewHolder this$0;
    public final float viewRadius;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditablePluginViewHolder$realPluginContainer$2$1(Context context, EditablePluginViewHolder editablePluginViewHolder) {
        super(context);
        this.$context = context;
        this.this$0 = editablePluginViewHolder;
        this.viewRadius = CellCalculatorManager.Companion.getInstance(context).dpToPxWithInitialDensity(62.0f) / 2.0f;
        boolean zIsSmoothRadiusOn = OplusSmoothRoundedManager.isSmoothRadiusOn();
        SmoothCornersGradientDrawable smoothCornersGradientDrawable = new SmoothCornersGradientDrawable();
        smoothCornersGradientDrawable.setStroke(editablePluginViewHolder.strokeWidth, context.getColor(2131103214));
        editablePluginViewHolder.bgView.setBackground(smoothCornersGradientDrawable);
        editablePluginViewHolder.bgView.setVisibility(8);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172080);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        OplusRoundParams fullCornerParams = OplusQsSmoothRoundUtil.getFullCornerParams(getViewRadius());
        if (zIsSmoothRadiusOn) {
            smoothCornersGradientDrawable.setSmoothRound(fullCornerParams.getRadius() + 7.0f, fullCornerParams.getWeight());
        } else {
            smoothCornersGradientDrawable.setSmoothRound(OplusSmoothRoundedManager.reverseNoWeightRadius(fullCornerParams.getRadius(), fullCornerParams.getWeight()) + 7.0f, 0.0f);
        }
        LogUtil.i$default(LogUtil.TAG_QS, "EditablePluginViewHolder", "[init] viewRadius = " + getViewRadius() + ", smoothRadiusOn = " + zIsSmoothRadiusOn + ", roundParams = " + fullCornerParams, null, 8);
        addView(editablePluginViewHolder.bgView, layoutParams);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public final List collectProperties(String str, List list) {
        if ("parent_translation_transition".equals(str)) {
            list.add(new Pair(this.this$0.bgView, FadeTransition.class));
        }
        return list;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    public boolean getEnablePressFeedback() {
        return super.getEnablePressFeedback();
    }

    public int getSpotLightType() {
        return 2;
    }

    public float getViewRadius() {
        return this.viewRadius;
    }

    public final boolean isEditMode() {
        return this.isEditMode;
    }

    public final boolean isViewSupportSpotLight() {
        return this.isEditMode;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        QsPanelPlugin qsPanelPlugin = this.this$0.panelPlugin;
        if (qsPanelPlugin != null) {
            qsPanelPlugin.onSizeChange(i3, i4, i, i2);
        }
    }

    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            this.this$0.bgView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.view.View
    public final void setLayerType(int i, Paint paint) {
        this.this$0.bgView.setLayerType(i, paint);
    }

    public final void updateSpotLightPath(Path path, Object obj) {
        OplusRoundParams fullCornerParams = OplusQsSmoothRoundUtil.getFullCornerParams(getViewRadius());
        CellCalculatorManager.LayoutConstraint layoutConstraint = CellCalculatorManager.Companion.getInstance(this.$context).getLayoutConstraint(this.this$0.realSpanSize, false, this.isEditMode, false);
        path.addRoundRect(new RectF(0.0f, 0.0f, layoutConstraint.getCapsuleWidth(), layoutConstraint.getCapsuleHeight()), fullCornerParams.getRadius(), fullCornerParams.getRadius(), Path.Direction.CW);
    }
}
