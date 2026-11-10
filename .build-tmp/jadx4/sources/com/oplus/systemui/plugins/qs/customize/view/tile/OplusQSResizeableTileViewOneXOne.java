package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.animation.Expandable;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewOneXOne extends OplusQSResizeableTileView {
    private final SpanSize tileViewSize;
    public float viewRadius;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewOneXOne$bindClickListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ View.OnClickListener $qsTileClick;
        public /* synthetic */ Function0 $viewClick;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.$viewClick.invoke();
            this.$qsTileClick.onClick(view);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSResizeableTileViewOneXOne(Context context, QSIconView qSIconView, QsLabelView qsLabelView) {
        super(context, qSIconView, qsLabelView);
        this.tileViewSize = SpanCache.INSTANCE.spanSize(1, 1);
        LinearLayout.inflate(context, 2131559375, this);
        setTag(2131364409, Boolean.FALSE);
        setId(2131364411);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        setOrientation(1);
        setGravity(1);
        getLeftView().addView((View) qSIconView, -1, -1);
        this.viewRadius = getCornerRadius();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void bindClickListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, Function0 function0) {
        setOnLongClickListener(onLongClickListener);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$viewClick = function0;
        anonymousClass1.$qsTileClick = onClickListener;
        VarHandle.storeStoreFence();
        setOnClickListener(anonymousClass1);
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            labelView.setOnLongClickListener(onLongClickListener);
            OplusQSResizeableTileViewOneXOne$bindClickListener$2$1 oplusQSResizeableTileViewOneXOne$bindClickListener$2$1 = new OplusQSResizeableTileViewOneXOne$bindClickListener$2$1();
            oplusQSResizeableTileViewOneXOne$bindClickListener$2$1.$qsTileSecondaryClick = onClickListener2;
            VarHandle.storeStoreFence();
            labelView.setOnClickListener(oplusQSResizeableTileViewOneXOne$bindClickListener$2$1);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView, com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if (Intrinsics.areEqual(str, "enter_exit_edit_mode_main_transition") && BaseQSResPool.globalThemeApplied()) {
            list.add(new Pair<>(getBgTransitionTarget(), AlphaTransitionProperty.class));
            QSIconView iconView = getIconView();
            OplusQSIconView oplusQSIconView = iconView instanceof OplusQSIconView ? (OplusQSIconView) iconView : null;
            if (oplusQSIconView != null) {
                list.add(new Pair<>(oplusQSIconView.getIconViewTransitionTarget(), ColorTransitionProperty.class));
            }
        }
        return list;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public Expandable getClickExpandView() {
        return Expandable.Companion.fromView(getIconFrame());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public float getCornerRadius() {
        return CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), getTileViewSize(), false, false, 14).getCapsuleHeight() / 2.0f;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public Float getCornerWeight() {
        return ((CornerOutlineProvider) SepQSResPool.INSTANCE.getTileOutline().getValue()).getCornerWeight(getTransitionDrawable());
    }

    public View getLabelContainer() {
        return getLabelView();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public View getSecondaryClickHostView() {
        return getLabelView();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public int getSpotLightType() {
        return 1;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public SpanSize getTileViewSize() {
        return this.tileViewSize;
    }

    public float getViewRadius() {
        OplusQsDeformRadiusParam deformPathProvider = getDeformPathProvider();
        return deformPathProvider != null ? deformPathProvider.getRadius() : this.viewRadius;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleCellLayoutConstraintChanged(CellCalculatorManager.LayoutConstraint layoutConstraint) {
        QSTile.State state = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewOneXOne-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("handleCellLayoutConstraintChanged constraint = \n" + layoutConstraint);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        ((OplusQSIconView) getIconView()).setIconScaleRatio(layoutConstraint.getInnerContentRatio());
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0014  */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void handleEditModeChanged(boolean z) {
        boolean z2;
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            QSTile.State tileState = getTileState();
            if (tileState != null) {
                z2 = tileState.dualTarget && !z;
            }
            labelView.setEnableExpandIndicator(z2);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleLabelColorChanged(Pair<? extends ColorStateList, ? extends ColorStateList> pair) {
        QSTile.State tileState;
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            int qsColorState = QsColorUtil.getQsColorState();
            QSTile.State tileState2 = getTileState();
            labelView.updateColor(qsColorState, (tileState2 != null && tileState2.state == 0) || ((tileState = getTileState()) != null && tileState.state == 101), this.isEditMode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleTileStateChange(QSTile.State state) {
        QSTile.State tileState = getTileState();
        if ((tileState == null || state.state != tileState.state) && getClicked()) {
            OplusSeparateClickTileManager.Companion.getInstance().onClickTileStateChange(getContext(), state);
            setClicked(false);
        }
        QSTile.State tileState2 = getTileState();
        if ((tileState2 == null || state.threeStageMode != tileState2.threeStageMode) && getClicked()) {
            OplusSeparateClickTileManager.Companion.getInstance().onThreeStageChange(getContext(), state);
            setClicked(false);
        }
        QSTile.BooleanState tileState3 = getTileState();
        QSTile.BooleanState booleanState = tileState3 instanceof QSTile.BooleanState ? tileState3 : null;
        Boolean boolValueOf = booleanState != null ? Boolean.valueOf(((QSTile.AdapterState) booleanState).value) : null;
        QSTile.BooleanState booleanState2 = state instanceof QSTile.BooleanState ? (QSTile.BooleanState) state : null;
        if (!Intrinsics.areEqual(boolValueOf, booleanState2 != null ? Boolean.valueOf(((QSTile.AdapterState) booleanState2).value) : null)) {
            setClicked(false);
        }
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            if (!labelView.isAttachedToWindow()) {
                OplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1 oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1 = new OplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1();
                oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1.$this_doOnAttach = labelView;
                oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1.$this_apply$inlined = labelView;
                oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1.$newState$inlined = state;
                oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1.this$0 = this;
                VarHandle.storeStoreFence();
                labelView.addOnAttachStateChangeListener(oplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1);
                return;
            }
            labelView.setEnabled(!state.disabledByPolicy);
            labelView.setSelected(!TextUtils.isEmpty(state.label));
            labelView.setContentDescription(state.toggleable ? state.dualLabelContentDescription : null);
            labelView.setEnableExpandIndicator(state.dualTarget && !this.isEditMode);
            CharSequence charSequence = state.importantLabel;
            labelView.updateText((charSequence == null || charSequence.length() == 0) ? state.label : state.importantLabel);
            int qsColorState = QsColorUtil.getQsColorState();
            int i = state.state;
            labelView.updateColor(qsColorState, i == 0 || i == 101, this.isEditMode);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public View initializeBgView() {
        View viewInitializeBgView = super.initializeBgView();
        viewInitializeBgView.setTag(2131364408, Boolean.FALSE);
        return viewInitializeBgView;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public IOplusQSTileViewLabelColorManager initializeLabelColorManager() {
        return new OplusQSTileViewLabelColorManager();
    }

    public void onDrawableUpdate(List<SpanSize> list, boolean z) {
        if (list.contains(getTileViewSize())) {
            if (z) {
                getThemeDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getTileThemeDrawable().getValue(), getBg(), false, 4));
            } else {
                getBgDrawable().setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getTileViewDrawable().getValue(), getBg(), false, 6));
            }
        }
    }

    public void onOutlineUpdate(List<SpanSize> list) {
        if (list.contains(getTileViewSize())) {
            getTransitionDrawable().setPathProvider((CornerOutlineProvider) SepQSResPool.INSTANCE.getTileOutline().getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void onRecycle() {
        super.onRecycle();
        setOnLongClickListener(null);
        setOnClickListener(null);
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            labelView.setOnLongClickListener(null);
            labelView.setOnClickListener(null);
        }
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
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            labelView.setLongClickable(!this.isEditMode);
            labelView.setClickable(!this.isEditMode);
        }
    }

    public void setViewRadius(float f) {
        this.viewRadius = f;
    }
}
