package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import androidx.car.app.serialization.Bundler$;
import androidx.core.view.OneShotPreDrawListener;
import com.android.keyguard.OplusLockIconView$;
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
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewTwoXOne extends OplusQSResizeableTileView implements LaunchableView, OplusQSHighlightTileViewAnimatorController.Callback {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final OplusQSHighlightTileViewAnimatorLabel animatorForLabel;
    public boolean isAnimating;
    private final ViewGroup labelContainer;
    private final TextSwitcher labelDesc;
    private final IIntTarget labelDescTransitionTarget;
    public final int labelPadding;
    private final TextSwitcher labelTitle;
    private final IIntTarget labelTitleTransitionTarget;
    public int lastMeasureHeight;
    public int lastMeasureWidth;
    private LaunchableViewDelegate launchableViewDelegate;
    private final SpanSize tileViewSize;
    public final float viewRadius;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IIntTarget {
        public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

        @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
        public int get() {
            return TextSwitcherExtKt.getTextColor(this.this$0.labelTitle, 0);
        }

        @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
        public void set(int i) {
            TextSwitcherExtKt.setTextColor(this.this$0.labelTitle, ColorStateList.valueOf(i));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements IIntTarget {
        public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

        @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
        public int get() {
            return TextSwitcherExtKt.getTextColor(this.this$0.labelDesc, 0);
        }

        @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
        public void set(int i) {
            TextSwitcherExtKt.setTextColor(this.this$0.labelDesc, ColorStateList.valueOf(i));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$bindClickListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnLongClickListenerC02301 implements View.OnLongClickListener {
        public /* synthetic */ View.OnLongClickListener $qsTileLongClick;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Function0 $viewClick;
        public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

        public /* synthetic */ ViewOnLongClickListenerC02301(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            switch (this.$r8$classId) {
                case 0:
                    this.$qsTileLongClick.onLongClick(view);
                    QSTile.State tileState = this.this$0.getTileState();
                    if (tileState != null && tileState.dualTarget) {
                        this.$viewClick.invoke();
                    }
                    break;
                default:
                    this.$qsTileLongClick.onLongClick(view);
                    QSTile.State tileState2 = this.this$0.getTileState();
                    if (tileState2 != null && tileState2.dualTarget) {
                        this.$viewClick.invoke();
                    }
                    break;
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$bindClickListener$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnClickListenerC02312 implements View.OnClickListener {
        public /* synthetic */ View.OnClickListener $qsTileClick;
        public /* synthetic */ Function0 $viewClick;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.$viewClick.invoke();
            this.$qsTileClick.onClick(view);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$bindClickListener$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements View.OnClickListener {
        public /* synthetic */ View.OnClickListener $qsTileClick;
        public /* synthetic */ View.OnClickListener $qsTileSecondaryClick;
        public /* synthetic */ Function0 $viewClick;
        public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.$viewClick.invoke();
            QSTile.State tileState = this.this$0.getTileState();
            if (tileState == null || !tileState.dualTarget) {
                this.$qsTileClick.onClick(view);
            } else {
                this.$qsTileSecondaryClick.onClick(view);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: $r8$lambda$b4RqVXgg9a-wA2y2ZFIfFUba0rA, reason: not valid java name */
    public static Unit m172$r8$lambda$b4RqVXgg9awA2y2ZFIfFUba0rA(OplusQSResizeableTileViewTwoXOne oplusQSResizeableTileViewTwoXOne, int i) {
        super/*android.widget.LinearLayout*/.setVisibility(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSResizeableTileViewTwoXOne(Context context, QSIconView qSIconView, QsLabelView qsLabelView) {
        super(context, qSIconView, qsLabelView);
        this.animatorForLabel = new OplusQSHighlightTileViewAnimatorLabel(context);
        this.tileViewSize = SpanCache.INSTANCE.spanSize(2, 1);
        LinearLayout.inflate(context, 2131559377, this);
        setTag(2131364409, Boolean.TRUE);
        setId(2131364411);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        ViewGroup viewGroup = (ViewGroup) requireViewById(2131363622);
        this.labelContainer = viewGroup;
        this.labelTitle = (TextSwitcher) viewGroup.requireViewById(2131365664);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.labelTitleTransitionTarget = anonymousClass1;
        this.labelDesc = (TextSwitcher) viewGroup.requireViewById(2131365665);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.labelDescTransitionTarget = anonymousClass2;
        getLeftView().addView(qSIconView);
        this.labelPadding = CellCalculatorManager.Companion.lockDpi(context, 2131172166);
        updateTextStyleInfo();
        this.viewRadius = getCornerRadius();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void bindClickListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, Function0 function0) {
        FrameLayout leftView = getLeftView();
        ViewOnLongClickListenerC02301 viewOnLongClickListenerC02301 = new ViewOnLongClickListenerC02301(0);
        viewOnLongClickListenerC02301.$qsTileLongClick = onLongClickListener;
        viewOnLongClickListenerC02301.this$0 = this;
        viewOnLongClickListenerC02301.$viewClick = function0;
        VarHandle.storeStoreFence();
        leftView.setOnLongClickListener(viewOnLongClickListenerC02301);
        FrameLayout leftView2 = getLeftView();
        ViewOnClickListenerC02312 viewOnClickListenerC02312 = new ViewOnClickListenerC02312();
        viewOnClickListenerC02312.$viewClick = function0;
        viewOnClickListenerC02312.$qsTileClick = onClickListener;
        VarHandle.storeStoreFence();
        leftView2.setOnClickListener(viewOnClickListenerC02312);
        getLeftView().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne.bindClickListener.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                OplusQSResizeableTileViewTwoXOne.this.getAccessibilityHelper().onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            }
        });
        ViewGroup viewGroup = this.labelContainer;
        ViewOnLongClickListenerC02301 viewOnLongClickListenerC02302 = new ViewOnLongClickListenerC02301(1);
        viewOnLongClickListenerC02302.$qsTileLongClick = onLongClickListener;
        viewOnLongClickListenerC02302.this$0 = this;
        viewOnLongClickListenerC02302.$viewClick = function0;
        VarHandle.storeStoreFence();
        viewGroup.setOnLongClickListener(viewOnLongClickListenerC02302);
        ViewGroup viewGroup2 = this.labelContainer;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.$viewClick = function0;
        anonymousClass5.this$0 = this;
        anonymousClass5.$qsTileSecondaryClick = onClickListener2;
        anonymousClass5.$qsTileClick = onClickListener;
        VarHandle.storeStoreFence();
        viewGroup2.setOnClickListener(anonymousClass5);
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
        if (isAttachedToWindow()) {
            String string = TextSwitcherExtKt.getCurrentText(this.labelDesc).toString();
            CharSequence labelDescText = getLabelDescText(state);
            if (string.length() != 0 || labelDescText.length() != 0) {
                this.isAnimating = true;
                TextSwitcher textSwitcher = this.labelDesc;
                OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1 oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1 = new OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1(0);
                oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1.this$0 = this;
                oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1.$isOpen$inlined = z;
                VarHandle.storeStoreFence();
                OneShotPreDrawListener.add(textSwitcher, oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1);
                return;
            }
        }
        refreshTextDesc(state, z);
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

    /* JADX WARN: Multi-variable type inference failed */
    public final CharSequence getLabelDescText(QSTile.State state) {
        CharSequence charSequence;
        CharSequence charSequence2 = (state.state != 2 || (charSequence = state.labelDesc) == null || charSequence.length() == 0 || Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.WIFI) || Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH) || Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.HOTSPOT)) ? "" : state.labelDesc;
        CharSequence charSequenceHandleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic = OplusQSHighlightTileViewLabelTextHandler.INSTANCE.handleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(getContext(), TextSwitcherExtKt.getCurrentText(this.labelDesc), state);
        return charSequenceHandleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic == null ? charSequence2 : charSequenceHandleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic;
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
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXOne-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("handleCellLayoutConstraintChanged constraint = \n" + layoutConstraint);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        QSIconView iconView = getIconView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iconView.getLayoutParams();
        layoutParams.width = MathKt.roundToInt(layoutConstraint.getInnerContentRatio() * iconView.getResources().getDimensionPixelSize(2131173124));
        layoutParams.height = MathKt.roundToInt(layoutConstraint.getInnerContentRatio() * iconView.getResources().getDimensionPixelSize(2131173124));
        layoutParams.gravity = 17;
        iconView.setLayoutParams(layoutParams);
        FrameLayout leftView = getLeftView();
        leftView.setPaddingRelative(MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * leftView.getResources().getDimensionPixelSize(2131173123)), 0, 0, 0);
        ViewGroup viewGroup = this.labelContainer;
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        int iRoundToInt = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * companion.lockDpi(viewGroup.getContext(), 2131173127));
        int iRoundToInt2 = MathKt.roundToInt(layoutConstraint.getInnerMarginRatio() * companion.lockDpi(viewGroup.getContext(), 2131173126));
        int i = this.labelPadding;
        viewGroup.setPaddingRelative(iRoundToInt, i, iRoundToInt2, i);
        ((OplusQSIconView) getIconView()).setIconScaleRatio(layoutConstraint.getInnerContentRatio());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void handleEditModeChanged(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleLabelColorChanged(Pair<? extends ColorStateList, ? extends ColorStateList> pair) {
        QSTile.State state = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXOne-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            ColorStateList colorStateList = (ColorStateList) pair.getFirst();
            sbM.append("handleLabelColorChanged first - " + Integer.toHexString(colorStateList != null ? colorStateList.getDefaultColor() : 0));
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        QSTile.State state2 = ((OplusQSResizeableTileView) this).tileState;
        String strM2 = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXOne-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(strM2, LogUtil.SEPARATOR);
            ColorStateList colorStateList2 = (ColorStateList) pair.getSecond();
            sbM2.append("handleLabelColorChanged second - " + Integer.toHexString(colorStateList2 != null ? colorStateList2.getDefaultColor() : 0));
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        TextSwitcherExtKt.setTextColor(this.labelTitle, (ColorStateList) pair.getFirst());
        TextSwitcherExtKt.setTextColor(this.labelDesc, (ColorStateList) pair.getSecond());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void handleTileStateChange(QSTile.State state) {
        if (!state.dualTarget && state.toggleable && state.state == 2 && !Intrinsics.areEqual(state.spec, OplusQSHighlightTileViewLabelTextHandler.CELL)) {
            state.labelDesc = ((LinearLayout) this).mContext.getString(2131955246);
        }
        boolean z = state.state == 2;
        QSTile.State state2 = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXOne-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            int i = state.state;
            QSTile.State tileState = getTileState();
            Integer numValueOf = tileState != null ? Integer.valueOf(tileState.state) : null;
            StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m("playAnimation isOpen ", i, " state.state:", " mState:", z);
            sbM2.append(numValueOf);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (getTileState() == null) {
            doAnimation(state, z);
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
            OplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1 oplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1 = new OplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1();
            oplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1.$this_apply = labelView;
            oplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1.$newState = state;
            oplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            labelView.post(oplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public IOplusQSTileViewLabelColorManager initializeLabelColorManager() {
        return new OplusQSHighlightTileViewLabelColorManager();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public boolean isNeedLayer(View view) {
        return super.isNeedLayer(view) && view.getId() != 2131363707;
    }

    public final void onActivityLaunchAnimationEnd() {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateTextStyleInfo();
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

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size != this.lastMeasureWidth || size2 != this.lastMeasureHeight) {
            this.lastMeasureWidth = size;
            this.lastMeasureHeight = size2;
            updateTextStyleInfo();
        }
        super.onMeasure(i, i2);
    }

    public void onOutlineUpdate(List<SpanSize> list) {
        if (list.contains(getTileViewSize())) {
            getTransitionDrawable().setPathProvider((CornerOutlineProvider) SepQSResPool.INSTANCE.getHighLightTileOutline().getValue());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView
    public final void onRecycle() {
        super.onRecycle();
        getLeftView().setAccessibilityDelegate(null);
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
        updateLabelDescText(state);
        if (this.isAnimating) {
            return;
        }
        TextSwitcher textSwitcher = this.labelDesc;
        OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1 oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1 = new OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1(1);
        oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1.$isOpen$inlined = z;
        oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(textSwitcher, oplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setShouldBlockVisibilityChanges(boolean z) {
        if (z && this.launchableViewDelegate == null) {
            OplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda0 oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda0 = new OplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda0();
            oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.launchableViewDelegate = new LaunchableViewDelegate(this, oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda0);
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

    public final void updateLabelDescText(QSTile.State state) {
        TextSwitcherExtKt.setContent(this.labelDesc, getLabelDescText(state));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateTextStyleInfo() {
        CellCalculatorManager.TwoXOneTextStyleInfo twoXOneTextStyleInfo = CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), SpanCache.TWO_X_ONE, false, false, 14).getTwoXOneTextStyleInfo();
        QSTile.State state = ((OplusQSResizeableTileView) this).tileState;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("OplusQSResizeableTileViewTwoXOne-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("updateTextStyleInfo twoXOneTextStyleInfo = \n" + twoXOneTextStyleInfo);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        TextSwitcher textSwitcher = this.labelTitle;
        TextSwitcherExtKt.setLineHeight(textSwitcher, twoXOneTextStyleInfo.getTwoXOneLabelLineHeight());
        TextSwitcherExtKt.setLineSpacing(textSwitcher);
        TextSwitcherExtKt.setTextSizePx(textSwitcher, twoXOneTextStyleInfo.getTwoXOneLabelTextSize());
        int twoXOneLabelDescLineHeight = twoXOneTextStyleInfo.getTwoXOneLabelDescLineHeight();
        TextSwitcher textSwitcher2 = this.labelDesc;
        TextSwitcherExtKt.setLineHeight(textSwitcher2, twoXOneLabelDescLineHeight);
        TextSwitcherExtKt.setTextSizePx(textSwitcher2, twoXOneTextStyleInfo.getTwoXOneLabelDescTextSize());
        OplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1 oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1 = new OplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1();
        oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1.f$0 = twoXOneLabelDescLineHeight;
        VarHandle.storeStoreFence();
        ViewExtKt.updateLayoutParamsOptimized(textSwitcher2, oplusQSResizeableTileViewTwoXOne$$ExternalSyntheticLambda1);
    }
}
