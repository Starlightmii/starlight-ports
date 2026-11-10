package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.OplusSeparateClickTileManager;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.springtransition.AlphaTransitionProperty;
import com.oplus.systemui.qs.base.springtransition.ColorTransitionProperty;
import com.oplus.systemui.qs.base.tile.OplusQsDeformRadiusParam;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableThreeStageView extends OplusQSResizeableTileView implements LaunchableView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSResizeableThreeStageView";
    private LaunchableViewDelegate launchableViewDelegate;
    private final SpanSize tileViewSize;
    public final float viewRadius;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit $r8$lambda$7OAroeo2B4a2NvM3qIKYnp2S5R0(OplusQSResizeableThreeStageView oplusQSResizeableThreeStageView, int i) {
        super/*android.widget.LinearLayout*/.setVisibility(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSResizeableThreeStageView(Context context, QSIconView qSIconView, QsLabelView qsLabelView) {
        super(context, qSIconView, qsLabelView);
        this.tileViewSize = SpanCache.INSTANCE.spanSize(2, 1);
        LinearLayout.inflate(context, 2131559382, this);
        setTag(2131364409, Boolean.TRUE);
        setId(2131364411);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        getLeftView().addView((View) qSIconView, -1, -1);
        this.viewRadius = getCornerRadius();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void bindClickListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, Function0 function0) {
        View iconView = getIconView().getIconView();
        OplusQSThreeStageLayout oplusQSThreeStageLayout = iconView instanceof OplusQSThreeStageLayout ? (OplusQSThreeStageLayout) iconView : null;
        if (oplusQSThreeStageLayout != null) {
            oplusQSThreeStageLayout.setOnClickListener(onClickListener);
            oplusQSThreeStageLayout.setOnLongClickListener(onLongClickListener);
            oplusQSThreeStageLayout.setViewClick(function0);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView, com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if (Intrinsics.areEqual(str, "enter_exit_edit_mode_main_transition") && BaseQSResPool.globalThemeApplied()) {
            list.add(new Pair<>(getBgTransitionTarget(), AlphaTransitionProperty.class));
            QSIconView iconView = getIconView();
            OplusQSThreeStageIconView oplusQSThreeStageIconView = iconView instanceof OplusQSThreeStageIconView ? (OplusQSThreeStageIconView) iconView : null;
            if (oplusQSThreeStageIconView != null) {
                list.add(new Pair<>(oplusQSThreeStageIconView.getIconBgTransitionTarget(), AlphaTransitionProperty.class));
                list.add(new Pair<>(oplusQSThreeStageIconView.getIconViewTransitionTarget(), ColorTransitionProperty.class));
            }
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public Expandable getClickExpandView() {
        return Expandable.Companion.fromView(this);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public float getCornerRadius() {
        return ((CornerOutlineProvider) SepQSResPool.INSTANCE.getHighLightTileOutline().getValue()).getCornerRadius(getTransitionDrawable());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public Float getCornerWeight() {
        return ((CornerOutlineProvider) SepQSResPool.INSTANCE.getHighLightTileOutline().getValue()).getCornerWeight(getTransitionDrawable());
    }

    public Rect getPaddingForLaunchAnimation() {
        return super.getPaddingForLaunchAnimation();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public View getSecondaryClickHostView() {
        return getLabelView();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public SpanSize getTileViewSize() {
        return this.tileViewSize;
    }

    public float getViewRadius() {
        OplusQsDeformRadiusParam deformPathProvider = getDeformPathProvider();
        return deformPathProvider != null ? deformPathProvider.getRadius() : this.viewRadius;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleCellLayoutConstraintChanged(CellCalculatorManager.LayoutConstraint layoutConstraint) {
        QSTile.State state = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableThreeStageView-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("handleCellLayoutConstraintChanged constraint = \n" + layoutConstraint);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int iRoundToInt = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * getResources().getDimensionPixelSize(2131173137));
        int iRoundToInt2 = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * getResources().getDimensionPixelSize(2131173136));
        View iconView = getIconView().getIconView();
        OplusQSThreeStageLayout oplusQSThreeStageLayout = iconView instanceof OplusQSThreeStageLayout ? (OplusQSThreeStageLayout) iconView : null;
        if (oplusQSThreeStageLayout != null) {
            oplusQSThreeStageLayout.updateContainerLayoutParams(iRoundToInt, iRoundToInt2);
        }
        if (getIconView() instanceof OplusQSThreeStageIconView) {
            ((OplusQSThreeStageIconView) getIconView()).setIconScaleRatio(layoutConstraint.getInnerContentRatio());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void handleEditModeChanged(boolean z) {
        View iconView = getIconView().getIconView();
        OplusQSThreeStageLayout oplusQSThreeStageLayout = iconView instanceof OplusQSThreeStageLayout ? (OplusQSThreeStageLayout) iconView : null;
        if (oplusQSThreeStageLayout != null) {
            oplusQSThreeStageLayout.isEditMode = z;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleLabelColorChanged(Pair<? extends ColorStateList, ? extends ColorStateList> pair) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleTileStateChange(QSTile.State state) {
        QSTile.State tileState;
        QSTile.State tileState2;
        if (!state.dualTarget && state.toggleable && state.state == 2 && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.CELL)) {
            state.labelDesc = ((LinearLayout) this).mContext.getString(2131955246);
        }
        state.toggleable = state.toggleable || state.state == 2;
        QSTile.State tileState3 = getTileState();
        if ((tileState3 == null || state.threeStageMode != tileState3.threeStageMode) && getClicked()) {
            OplusSeparateClickTileManager.Companion.getInstance().onThreeStageChange(getContext(), state);
            setClicked(false);
        }
        QSTile.State tileState4 = getTileState();
        if (tileState4 == null || state.dualTarget != tileState4.dualTarget || (tileState = getTileState()) == null || state.toggleable != tileState.toggleable || (tileState2 = getTileState()) == null || state.threeStageMode != tileState2.threeStageMode) {
            View iconView = getIconView().getIconView();
            OplusQSThreeStageLayout oplusQSThreeStageLayout = iconView instanceof OplusQSThreeStageLayout ? (OplusQSThreeStageLayout) iconView : null;
            if (oplusQSThreeStageLayout != null) {
                oplusQSThreeStageLayout.setSelectedIndex(state.threeStageMode, state.contentDescription);
            }
        }
        QsLabelView labelView = getLabelView();
        if (labelView == null || !labelView.isAttachedToWindow()) {
            return;
        }
        labelView.setEnabled(!state.disabledByPolicy);
        labelView.setSelected(!TextUtils.isEmpty(state.label));
        labelView.setContentDescription(state.toggleable ? state.dualLabelContentDescription : null);
        int qsColorState = QsColorUtil.getQsColorState();
        int i = state.state;
        labelView.updateColor(qsColorState, i == 0 || i == 101, this.isEditMode);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public IOplusQSTileViewLabelColorManager initializeLabelColorManager() {
        return new OplusQSHighlightTileViewLabelColorManager();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public View initializePadLockView() {
        return null;
    }

    public final void onActivityLaunchAnimationEnd() {
    }

    public void onDrawableUpdate(List<SpanSize> list, boolean z) {
        if (list.contains(getTileViewSize())) {
            if (z) {
                getThemeDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighLightTileThemeDrawable().getValue(), getBg(), false, 4));
            } else {
                getBgDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighlightTileViewDrawable().getValue(), getBg(), false, 6));
            }
        }
    }

    public void onOutlineUpdate(List<SpanSize> list) {
        if (list.contains(getTileViewSize())) {
            getTransitionDrawable().setPathProvider((CornerOutlineProvider) SepQSResPool.INSTANCE.getHighLightTileOutline().getValue());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void onRecycle() {
        super.onRecycle();
        OplusQSThreeStageLayout oplusQSThreeStageLayout = (OplusQSThreeStageLayout) getIconView().getIconView();
        oplusQSThreeStageLayout.setOnClickListener(null);
        oplusQSThreeStageLayout.setOnLongClickListener(null);
        oplusQSThreeStageLayout.clearRunnable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void refreshClickable(QSTile.State state) {
        boolean z = false;
        setClickable((state.state == 0 || this.isEditMode) ? false : true);
        if (state.handlesLongClick && !this.isEditMode) {
            z = true;
        }
        setLongClickable(z);
    }

    public void setLayerType(int i, Paint paint) {
        if (BaseQSResPool.supportMixColor()) {
            return;
        }
        super.setLayerType(i, paint);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setShouldBlockVisibilityChanges(boolean z) {
        if (z && this.launchableViewDelegate == null) {
            OplusQSResizeableThreeStageView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSResizeableThreeStageView$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.launchableViewDelegate = new LaunchableViewDelegate(this, externalSyntheticLambda0);
        }
        LaunchableViewDelegate launchableViewDelegate = this.launchableViewDelegate;
        if (launchableViewDelegate != null) {
            launchableViewDelegate.setShouldBlockVisibilityChanges(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setVisibility(int i) {
        LaunchableViewDelegate launchableViewDelegate = this.launchableViewDelegate;
        if (launchableViewDelegate != null) {
            launchableViewDelegate.setVisibility(i);
        } else {
            super/*android.widget.LinearLayout*/.setVisibility(i);
        }
    }
}
