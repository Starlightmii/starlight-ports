package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.springtransition.AlphaTransitionProperty;
import com.oplus.systemui.qs.base.springtransition.ColorTransitionProperty;
import com.oplus.systemui.qs.base.springtransition.IIntTarget;
import com.oplus.systemui.qs.base.tile.OplusQsDeformRadiusParam;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.TextSwitcherExtKt;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewTwoXTwo extends OplusQSResizeableTileView implements LaunchableView, OplusQSHighlightTileViewAnimatorController.Callback {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final OplusQSHighlightTileViewAnimatorLabel animatorForLabel;
    public boolean isFirstInit;
    private final ViewGroup labelContainer;
    private final TextSwitcher labelDesc;
    private final IIntTarget labelDescTransitionTarget;
    private final TextSwitcher labelTitle;
    private final IIntTarget labelTitleTransitionTarget;
    private LaunchableViewDelegate launchableViewDelegate;
    private final SpanSize tileViewSize;
    public final float viewRadius;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: $r8$lambda$nIbdRJh-A58Huv4JcIaSsTdniSs, reason: not valid java name */
    public static Unit m173$r8$lambda$nIbdRJhA58Huv4JcIaSsTdniSs(OplusQSResizeableTileViewTwoXTwo oplusQSResizeableTileViewTwoXTwo, int i) {
        super/*android.widget.LinearLayout*/.setVisibility(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSResizeableTileViewTwoXTwo(Context context, QSIconView qSIconView, QsLabelView qsLabelView) {
        super(context, qSIconView, qsLabelView);
        this.isFirstInit = true;
        this.animatorForLabel = new OplusQSHighlightTileViewAnimatorLabel(context);
        this.tileViewSize = SpanCache.INSTANCE.spanSize(2, 2);
        LinearLayout.inflate(context, 2131559379, this);
        setTag(2131364409, Boolean.TRUE);
        setId(LinearLayout.generateViewId());
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        setOrientation(1);
        setGravity(1);
        ViewGroup viewGroup = (ViewGroup) requireViewById(2131363622);
        this.labelContainer = viewGroup;
        viewGroup.setId(2131363623);
        this.labelTitle = (TextSwitcher) viewGroup.requireViewById(2131365664);
        1 r5 = new 1();
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        this.labelTitleTransitionTarget = r5;
        this.labelDesc = (TextSwitcher) viewGroup.requireViewById(2131365665);
        2 r3 = new 2();
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        this.labelDescTransitionTarget = r3;
        getLeftView().addView((View) qSIconView, -1, -1);
        this.viewRadius = getCornerRadius();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void bindClickListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, Function0 function0) {
        FrameLayout leftView = getLeftView();
        bindClickListener.1 r1 = new bindClickListener.1(0);
        r1.$qsTileLongClick = onLongClickListener;
        r1.this$0 = this;
        r1.$viewClick = function0;
        VarHandle.storeStoreFence();
        leftView.setOnLongClickListener(r1);
        FrameLayout leftView2 = getLeftView();
        bindClickListener.2 r2 = new bindClickListener.2();
        r2.$viewClick = function0;
        r2.$qsTileClick = onClickListener;
        VarHandle.storeStoreFence();
        leftView2.setOnClickListener(r2);
        ViewGroup viewGroup = this.labelContainer;
        bindClickListener.1 r3 = new bindClickListener.1(1);
        r3.$qsTileLongClick = onLongClickListener;
        r3.this$0 = this;
        r3.$viewClick = function0;
        VarHandle.storeStoreFence();
        viewGroup.setOnLongClickListener(r3);
        ViewGroup viewGroup2 = this.labelContainer;
        bindClickListener.4 r0 = new bindClickListener.4();
        r0.this$0 = this;
        r0.$qsTileSecondaryClick = onClickListener2;
        r0.$viewClick = function0;
        r0.$qsTileClick = onClickListener;
        VarHandle.storeStoreFence();
        viewGroup2.setOnClickListener(r0);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView, com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if (Intrinsics.areEqual(str, "enter_exit_edit_mode_main_transition") && BaseQSResPool.globalThemeApplied()) {
            list.add(new Pair<>(getBgTransitionTarget(), AlphaTransitionProperty.class));
            QSIconView iconView = getIconView();
            OplusQSIconView oplusQSIconView = iconView instanceof OplusQSIconView ? (OplusQSIconView) iconView : null;
            if (oplusQSIconView != null) {
                list.add(new Pair<>(oplusQSIconView.getIconBgTransitionTarget(), AlphaTransitionProperty.class));
                list.add(new Pair<>(oplusQSIconView.getIconViewTransitionTarget(), ColorTransitionProperty.class));
            }
            list.add(new Pair<>(this.labelTitleTransitionTarget, ColorTransitionProperty.class));
            list.add(new Pair<>(this.labelDescTransitionTarget, ColorTransitionProperty.class));
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewAnimatorController.Callback
    public void doAnimation(QSTile.State state, boolean z) {
        if (!isAttachedToWindow()) {
            refreshTextDesc(state, z);
        } else {
            this.animatorForLabel.startLabelAnim(this.labelTitle, this.labelDesc, this.labelDesc.getMeasuredHeight(), z, new doAnimation.1(z, this, state));
        }
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
        return this.labelContainer;
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
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXTwo-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("handleCellLayoutConstraintChanged constraint = \n" + layoutConstraint);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        FrameLayout leftView = getLeftView();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) leftView.getLayoutParams();
        marginLayoutParams.setMarginStart(MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * leftView.getResources().getDimensionPixelSize(2131173130)));
        marginLayoutParams.topMargin = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * leftView.getResources().getDimensionPixelSize(2131173131));
        marginLayoutParams.width = MathKt.roundToInt(layoutConstraint.getInnerContentRatio() * leftView.getResources().getDimensionPixelSize(2131173132));
        marginLayoutParams.height = MathKt.roundToInt(layoutConstraint.getInnerContentRatio() * leftView.getResources().getDimensionPixelSize(2131173132));
        leftView.setLayoutParams(marginLayoutParams);
        ViewGroup viewGroup = this.labelContainer;
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        marginLayoutParams2.setMarginStart(MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * viewGroup.getResources().getDimensionPixelSize(2131173135)));
        marginLayoutParams2.bottomMargin = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * viewGroup.getResources().getDimensionPixelSize(2131173134));
        viewGroup.setLayoutParams(marginLayoutParams2);
        ((OplusQSIconView) getIconView()).setIconScaleRatio(layoutConstraint.getInnerContentRatio());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void handleEditModeChanged(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleLabelColorChanged(Pair<? extends ColorStateList, ? extends ColorStateList> pair) {
        TextSwitcherExtKt.setTextColor(this.labelTitle, (ColorStateList) pair.getFirst());
        TextSwitcherExtKt.setTextColor(this.labelDesc, (ColorStateList) pair.getSecond());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleTileStateChange(QSTile.State state) {
        if (!state.dualTarget && state.toggleable && state.state == 2 && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.CELL)) {
            state.labelDesc = ((LinearLayout) this).mContext.getString(2131955246);
        }
        state.toggleable = state.toggleable || state.state == 2;
        boolean z = state.state == 2;
        QSTile.State state2 = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXTwo-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            boolean z2 = this.isFirstInit;
            int i = state.state;
            QSTile.State tileState = getTileState();
            Integer numValueOf = tileState != null ? Integer.valueOf(tileState.state) : null;
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("playAnimation isOpen ", " isFirstInit: ", " state.state:", z, z2);
            sbM2.append(i);
            sbM2.append(" mState:");
            sbM2.append(numValueOf);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (this.isFirstInit) {
            this.isFirstInit = false;
            if (z) {
                updateLabelDescText$1(state);
            } else {
                doAnimation(state, false);
            }
        } else {
            OplusQSHighlightTileViewAnimatorController.INSTANCE.playAnimation(state, getTileState(), this);
        }
        CharSequence charSequence = state.label;
        if (charSequence != null) {
            TextSwitcherExtKt.setContent(this.labelTitle, charSequence);
            this.labelTitle.setSelected(!TextUtils.isEmpty(state.label));
        }
        this.labelContainer.setContentDescription(state.toggleable ? state.dualLabelContentDescription : null);
        this.labelTitle.setEnabled(!state.disabledByPolicy);
        QsLabelView labelView = getLabelView();
        if (labelView != null) {
            handleTileStateChange.1.1 r1 = new handleTileStateChange.1.1();
            r1.$this_apply = labelView;
            r1.$newState = state;
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            labelView.post(r1);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public IOplusQSTileViewLabelColorManager initializeLabelColorManager() {
        return new OplusQSHighlightTileViewLabelColorManager();
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
        getLeftView().setOnLongClickListener(null);
        getLeftView().setOnClickListener(null);
        this.labelContainer.setOnLongClickListener(null);
        this.labelContainer.setOnClickListener(null);
        this.labelDesc.reset();
        this.labelTitle.reset();
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
        getLeftView().setLongClickable(!this.isEditMode);
        getLeftView().setClickable(!this.isEditMode);
        this.labelContainer.setLongClickable(!this.isEditMode);
        this.labelContainer.setClickable(!this.isEditMode);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewAnimatorController.Callback
    public void refreshTextDesc(QSTile.State state, boolean z) {
        updateLabelDescText$1(state);
        TextSwitcher textSwitcher = this.labelDesc;
        if (!textSwitcher.isLaidOut() || textSwitcher.isLayoutRequested()) {
            OplusQSResizeableTileViewTwoXTwo$refreshTextDesc$.inlined.doOnLayout.1 r0 = new OplusQSResizeableTileViewTwoXTwo$refreshTextDesc$.inlined.doOnLayout.1();
            r0.this$0 = this;
            r0.$isOpen$inlined = z;
            VarHandle.storeStoreFence();
            textSwitcher.addOnLayoutChangeListener(r0);
            return;
        }
        float measuredHeight = this.labelDesc.getMeasuredHeight() / 2.0f;
        this.labelTitle.setTranslationY(z ? 0.0f : measuredHeight);
        TextSwitcher textSwitcher2 = this.labelDesc;
        if (z) {
            measuredHeight = 0.0f;
        }
        textSwitcher2.setTranslationY(measuredHeight);
        this.labelDesc.setAlpha(z ? 1.0f : 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setShouldBlockVisibilityChanges(boolean z) {
        if (z && this.launchableViewDelegate == null) {
            OplusQSResizeableTileViewTwoXTwo$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSResizeableTileViewTwoXTwo$.ExternalSyntheticLambda0();
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

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateLabelDescText$1(QSTile.State state) {
        if (state.state != 2) {
            this.labelDesc.setCurrentText("");
        } else {
            CharSequence charSequence = state.labelDesc;
            if (charSequence != null && charSequence.length() != 0 && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.WIFI) && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH) && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.HOTSPOT)) {
                TextSwitcherExtKt.setContent(this.labelDesc, state.labelDesc.toString());
            }
        }
        OplusQSHighlightTileViewLabelTextHandler.INSTANCE.handleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(getContext(), TextSwitcherExtKt.getCurrentText(this.labelDesc), state);
    }
}
