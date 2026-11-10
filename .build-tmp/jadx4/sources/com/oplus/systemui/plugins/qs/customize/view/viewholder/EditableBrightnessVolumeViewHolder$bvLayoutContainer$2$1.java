package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewKt$;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.widget.OplusQsToggleSliderLayout;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1 extends FrameLayout implements IEditableView, IQSSpotLightConfig {
    public final /* synthetic */ Context $context;
    public final View contentView;
    public boolean isEditMode;
    public float lastRadius;
    public float lastWeight;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1(Context context, EditableBrightnessVolumeViewHolder editableBrightnessVolumeViewHolder) {
        super(context);
        this.$context = context;
        this.lastWeight = 1.0f;
        View viewInflate = LayoutInflater.from(context).inflate(2131559331, (ViewGroup) this, false);
        this.contentView = viewInflate;
        CellCalculatorManager.LayoutConstraint layoutConstraint = CellCalculatorManager.Companion.getInstance(context).getLayoutConstraint(SpanCache.ONE_X_TWO, false, this.isEditMode, false);
        if (viewInflate != null) {
            View viewFindViewById = viewInflate.findViewById(2131365964);
            SmoothCornersGradientDrawable smoothCornersGradientDrawable = new SmoothCornersGradientDrawable();
            smoothCornersGradientDrawable.setStroke(editableBrightnessVolumeViewHolder.strokeWidth, context.getColor(2131103214));
            viewFindViewById.setBackground(smoothCornersGradientDrawable);
            editableBrightnessVolumeViewHolder.strokeBgView = viewFindViewById;
            setContentDescription(context.getResources().getString(2131955219));
            View view = editableBrightnessVolumeViewHolder.strokeBgView;
            ViewExtKt.setTalkBackEnabled(view == null ? null : view, false, getContentDescription());
            viewInflate.setImportantForAccessibility(2);
            OplusQsToggleSliderLayout oplusQsToggleSliderLayout = (OplusQsToggleSliderLayout) viewInflate.findViewById(2131362351);
            if (oplusQsToggleSliderLayout != null) {
                ViewGroup.LayoutParams layoutParams = oplusQsToggleSliderLayout.getLayoutParams();
                if (layoutParams == null) {
                    ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                    throw null;
                }
                layoutParams.width = layoutConstraint.getCapsuleWidth();
                oplusQsToggleSliderLayout.setLayoutParams(layoutParams);
            }
            editableBrightnessVolumeViewHolder.brightnessViewHolder.initSliderLayout(oplusQsToggleSliderLayout);
            if (oplusQsToggleSliderLayout != null) {
                EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0 editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0 = new EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0();
                editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0.f$0 = editableBrightnessVolumeViewHolder;
                editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0.f$1 = context;
                editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0.f$2 = this;
                editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0.f$3 = layoutConstraint;
                VarHandle.storeStoreFence();
                oplusQsToggleSliderLayout.setOnRadiusUpdateListener(editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0);
            }
            OplusQsToggleSliderLayout oplusQsToggleSliderLayout2 = (OplusQsToggleSliderLayout) viewInflate.findViewById(2131364814);
            if (oplusQsToggleSliderLayout2 != null) {
                ViewGroup.LayoutParams layoutParams2 = oplusQsToggleSliderLayout2.getLayoutParams();
                if (layoutParams2 == null) {
                    ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                    throw null;
                }
                layoutParams2.width = layoutConstraint.getCapsuleWidth();
                oplusQsToggleSliderLayout2.setLayoutParams(layoutParams2);
            }
            editableBrightnessVolumeViewHolder.volumeViewHolder.initSliderLayout(oplusQsToggleSliderLayout2);
            addView(viewInflate);
        }
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
        return 0.0f;
    }

    public final boolean isEditMode() {
        return this.isEditMode;
    }

    public final boolean isViewSupportSpotLight() {
        return this.isEditMode;
    }

    public void setEditMode(boolean z) {
        this.isEditMode = z;
    }

    @Override // android.view.View
    public final void setLayerType(int i, Paint paint) {
        View view = this.contentView;
        if (view != null) {
            view.setLayerType(i, paint);
        }
    }

    public final void updateSpotLightPath(Path path, Object obj) {
        float capsuleHeight = CellCalculatorManager.Companion.getInstance(this.$context).getLayoutConstraint(SpanCache.ONE_X_TWO, false, this.isEditMode, false).getCapsuleHeight();
        if (obj instanceof OplusPathAdapter) {
            RectF rectF = new RectF(0.0f, 0.0f, capsuleHeight, capsuleHeight);
            float f = this.lastRadius;
            ((OplusPathAdapter) obj).addSmoothRoundRect(rectF, f, f, this.lastWeight, Path.Direction.CW);
        } else {
            RectF rectF2 = new RectF(0.0f, 0.0f, capsuleHeight, capsuleHeight);
            float f2 = this.lastRadius;
            path.addRoundRect(rectF2, f2, f2, Path.Direction.CW);
        }
    }
}
