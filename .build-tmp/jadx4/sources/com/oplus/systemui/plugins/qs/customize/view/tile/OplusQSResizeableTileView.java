package com.oplus.systemui.plugins.qs.customize.view.tile;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.car.app.serialization.Bundler$;
import androidx.core.view.ViewGroupKt;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.plugins.qs.TileTipsData;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.OplusSeparateClickTileManager;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable;
import com.oplus.systemui.qs.base.res.model.InterestingViewFlag;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.DrawableTransitionAlphaTarget;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.tile.OplusQsDeformRadiusParam;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSResizeableTileView extends QSTileView implements IEditableView, ITransitionDesc, IQSSpotLightConfig {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy accessibilityHelper$delegate;
    private final Lazy bg$delegate;
    private final TileDrawableWrapper bgDrawable;
    private final DrawableTransitionAlphaTarget bgTransitionTarget;
    public boolean clicked;
    private OplusQsDeformRadiusParam deformPathProvider;
    private final Lazy iconFrame$delegate;
    private final QSIconView iconView;
    private final InterestingViewFlag interestingViewFlag;
    public boolean isBeingDragged;
    public boolean isEditMode;
    private final Lazy labelResourceManager$delegate;
    private QsLabelView labelView;
    public int lastMeasureHeight;
    public int lastMeasureWidth;
    private final Lazy leftView$delegate;
    private final Lazy padLock$delegate;
    private DisposableHandle preloadDisposableHandle;
    private final TileDrawableWrapper themeDrawable;
    private TileClickListener tileClickListener;
    private QSTile.State tileState;
    private final TileTransitionDrawable transitionDrawable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StateChangeRunnable implements Runnable {
        private final QSTile.State newState;

        public StateChangeRunnable(QSTile.State state) {
            this.newState = state;
        }

        public boolean equals(Object obj) {
            return obj instanceof StateChangeRunnable;
        }

        public final int hashCode() {
            return Reflection.getOrCreateKotlinClass(StateChangeRunnable.class).hashCode();
        }

        @Override // java.lang.Runnable
        public final void run() {
            OplusQSResizeableTileView.access$handleStateChanged(OplusQSResizeableTileView.this, this.newState);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TileClickListener {
        void tileOnClick(String str, QSTile.State state);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView$init$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function0 {
        public final Object invoke() {
            OplusQSResizeableTileView.access$performViewClick((OplusQSResizeableTileView) ((CallableReference) this).receiver);
            return Unit.INSTANCE;
        }
    }

    public OplusQSResizeableTileView(Context context, QSIconView qSIconView, QsLabelView qsLabelView) {
        super(context);
        this.iconView = qSIconView;
        this.labelView = qsLabelView;
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda2 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(0);
        oplusQSResizeableTileView$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.accessibilityHelper$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda2);
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda3 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(1);
        oplusQSResizeableTileView$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.labelResourceManager$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda3);
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda4 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(2);
        oplusQSResizeableTileView$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconFrame$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda4);
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda5 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(3);
        oplusQSResizeableTileView$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.bg$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda5);
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda6 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(4);
        oplusQSResizeableTileView$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.leftView$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda6);
        OplusQSResizeableTileView$$ExternalSyntheticLambda2 oplusQSResizeableTileView$$ExternalSyntheticLambda7 = new OplusQSResizeableTileView$$ExternalSyntheticLambda2(5);
        oplusQSResizeableTileView$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.padLock$delegate = LazyKt__LazyJVMKt.lazy(oplusQSResizeableTileView$$ExternalSyntheticLambda7);
        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
        this.bgDrawable = tileDrawableWrapper;
        TileDrawableWrapper tileDrawableWrapper2 = new TileDrawableWrapper(null);
        this.themeDrawable = tileDrawableWrapper2;
        TileTransitionDrawable tileTransitionDrawable = new TileTransitionDrawable(tileDrawableWrapper, tileDrawableWrapper2);
        this.transitionDrawable = tileTransitionDrawable;
        this.bgTransitionTarget = new DrawableTransitionAlphaTarget(tileTransitionDrawable);
        this.interestingViewFlag = new InterestingViewFlag();
        qSIconView.setId(2131364810);
    }

    /* JADX WARN: Code duplicated, block: B:66:0x0151  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void access$handleStateChanged(OplusQSResizeableTileView oplusQSResizeableTileView, QSTile.State state) {
        boolean z;
        String str;
        oplusQSResizeableTileView.getClass();
        String str2 = state.spec;
        if (str2 != null && str2.length() != 0 && oplusQSResizeableTileView.clicked && (str = state.spec) != null) {
            switch (str.hashCode()) {
                case -40300674:
                    if (str.equals("rotation")) {
                        JankUtils.rotationSwitchSceneEnd(oplusQSResizeableTileView.getContext());
                    }
                    break;
                case 3154:
                    if (str.equals(OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH)) {
                        JankUtils.bluetoothSwitchSceneEnd(oplusQSResizeableTileView.getContext());
                    }
                    break;
                case 3049826:
                    if (str.equals(OplusQSHighlightTileViewLabelTextHandler.CELL)) {
                        JankUtils.cellularSwitchSceneEnd(oplusQSResizeableTileView.getContext());
                    }
                    break;
                case 3649301:
                    if (str.equals(OplusQSHighlightTileViewLabelTextHandler.WIFI)) {
                        JankUtils.wlanSwitchSceneEnd(oplusQSResizeableTileView.getContext());
                    }
                    break;
                case 1901043637:
                    if (str.equals("location")) {
                        JankUtils.locationSwitchSceneEnd(oplusQSResizeableTileView.getContext());
                    }
                    break;
            }
        }
        String simpleName = oplusQSResizeableTileView.getClass().getSimpleName();
        QSTile.State state2 = oplusQSResizeableTileView.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("handleStateChanged----> \ncurrent State = " + oplusQSResizeableTileView.tileState + " \nnewState = " + state + " \nisEditMode = " + oplusQSResizeableTileView.isEditMode);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        state.toggleable = state.toggleable || state.state == 2;
        oplusQSResizeableTileView.setContentDescription(state.contentDescription);
        oplusQSResizeableTileView.getLeftView().setContentDescription(state.contentDescription);
        oplusQSResizeableTileView.setStateDescription(oplusQSResizeableTileView.iconView.getResources().getString(state.state == 2 ? 2131956739 : 2131956738));
        oplusQSResizeableTileView.getLeftView().setStateDescription(oplusQSResizeableTileView.getLeftView().getResources().getString(state.state == 2 ? 2131956739 : 2131956738));
        oplusQSResizeableTileView.refreshTileInnerLabelColor(state);
        oplusQSResizeableTileView.refreshTileOuterLabelColor(state);
        oplusQSResizeableTileView.refreshClickable(state);
        oplusQSResizeableTileView.getAccessibilityHelper().handleQSTileStateChanged(state);
        View padLock = oplusQSResizeableTileView.getPadLock();
        if (padLock != null) {
            padLock.setVisibility(state.disabledByPolicy ? 0 : 8);
        }
        oplusQSResizeableTileView.handleTileStateChange(state);
        oplusQSResizeableTileView.tileState = state;
        oplusQSResizeableTileView.refreshDrawableState();
        QSIconView icon = oplusQSResizeableTileView.getIcon();
        if (oplusQSResizeableTileView.isShown() && oplusQSResizeableTileView.getAlpha() == 1.0f) {
            int[] iArr = new int[2];
            oplusQSResizeableTileView.getLocationOnScreen(iArr);
            z = iArr[1] >= (-oplusQSResizeableTileView.getHeight());
        }
        icon.setIcon(state, z);
    }

    public static final void access$performViewClick(OplusQSResizeableTileView oplusQSResizeableTileView) {
        TileTipsData tileTipsData;
        oplusQSResizeableTileView.getAccessibilityHelper().mClicked = true;
        oplusQSResizeableTileView.clicked = true;
        OplusSeparateClickTileManager companion = OplusSeparateClickTileManager.Companion.getInstance();
        QSTile.State state = oplusQSResizeableTileView.tileState;
        CharSequence mainText = (state == null || (tileTipsData = state.functionName) == null) ? null : tileTipsData.getMainText();
        QSTile.State state2 = oplusQSResizeableTileView.tileState;
        companion.onQStileClick(mainText, state2 != null ? state2.state : 0);
    }

    public abstract void bindClickListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, Function0 function0);

    public abstract /* synthetic */ List collectProperties(String str, List list);

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.LinearLayout*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    public final OplusQSHighlightTileViewAccessibilityHelper getAccessibilityHelper() {
        return (OplusQSHighlightTileViewAccessibilityHelper) this.accessibilityHelper$delegate.getValue();
    }

    public final Drawable getBackgroundDrawable() {
        return this.bgDrawable;
    }

    public final View getBg() {
        return (View) this.bg$delegate.getValue();
    }

    public final TileDrawableWrapper getBgDrawable() {
        return this.bgDrawable;
    }

    public final DrawableTransitionAlphaTarget getBgTransitionTarget() {
        return this.bgTransitionTarget;
    }

    public abstract Expandable getClickExpandView();

    public final boolean getClicked() {
        return this.clicked;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float getCornerRadius() {
        return getContext().getResources().getDimension(2131172172) * CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), getTileViewSize(), false, false, 14).getInnerMarginRatio();
    }

    public Float getCornerWeight() {
        return null;
    }

    public OplusQsDeformRadiusParam getDeformPathProvider() {
        return this.deformPathProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getDetailY() {
        return (getHeight() / 2) + getTop();
    }

    public boolean getEnablePressFeedback() {
        return super.getEnablePressFeedback();
    }

    public final QSIconView getIcon() {
        return this.iconView;
    }

    public final ViewGroup getIconFrame() {
        return (ViewGroup) this.iconFrame$delegate.getValue();
    }

    public final QSIconView getIconView() {
        return this.iconView;
    }

    public final InterestingViewFlag getInterestingViewFlag() {
        return this.interestingViewFlag;
    }

    public final IOplusQSTileViewLabelColorManager getLabelResourceManager() {
        return (IOplusQSTileViewLabelColorManager) this.labelResourceManager$delegate.getValue();
    }

    public final QsLabelView getLabelView() {
        return this.labelView;
    }

    public final FrameLayout getLeftView() {
        return (FrameLayout) this.leftView$delegate.getValue();
    }

    public final View getPadLock() {
        return (View) this.padLock$delegate.getValue();
    }

    public abstract View getSecondaryClickHostView();

    public int getSpotLightType() {
        return 2;
    }

    public final TileDrawableWrapper getThemeDrawable() {
        return this.themeDrawable;
    }

    public final TileClickListener getTileClickListener() {
        return this.tileClickListener;
    }

    public final QSTile.State getTileState() {
        return this.tileState;
    }

    public abstract SpanSize getTileViewSize();

    public final TileTransitionDrawable getTransitionDrawable() {
        return this.transitionDrawable;
    }

    public abstract void handleCellLayoutConstraintChanged(CellCalculatorManager.LayoutConstraint layoutConstraint);

    public abstract void handleEditModeChanged(boolean z);

    public abstract void handleLabelColorChanged(Pair<? extends ColorStateList, ? extends ColorStateList> pair);

    public abstract void handleTileStateChange(QSTile.State state);

    public final boolean hasOverlappingRendering() {
        return false;
    }

    public void init(QSTile qSTile) {
        View view;
        String str = qSTile.getState().spec;
        QSTile.State state = this.tileState;
        DisposableHandle disposableHandlePreloadAndRelease = null;
        if (!Intrinsics.areEqual(str, state != null ? state.spec : null)) {
            this.tileState = null;
        }
        Expandable clickExpandView = getClickExpandView();
        if (clickExpandView != null && (view = clickExpandView.getView()) != null) {
            view.setTag(qSTile.getTileSpec());
        }
        View secondaryClickHostView = getSecondaryClickHostView();
        int i = 1;
        int i2 = 0;
        if (secondaryClickHostView != null) {
            OplusQSResizeableTileView$$ExternalSyntheticLambda0 oplusQSResizeableTileView$$ExternalSyntheticLambda0 = new OplusQSResizeableTileView$$ExternalSyntheticLambda0(0);
            oplusQSResizeableTileView$$ExternalSyntheticLambda0.f$0 = secondaryClickHostView;
            oplusQSResizeableTileView$$ExternalSyntheticLambda0.f$1 = qSTile;
            oplusQSResizeableTileView$$ExternalSyntheticLambda0.f$2 = clickExpandView;
            VarHandle.storeStoreFence();
            OplusQSResizeableTileView$$ExternalSyntheticLambda0 oplusQSResizeableTileView$$ExternalSyntheticLambda1 = new OplusQSResizeableTileView$$ExternalSyntheticLambda0(1);
            oplusQSResizeableTileView$$ExternalSyntheticLambda1.f$0 = secondaryClickHostView;
            oplusQSResizeableTileView$$ExternalSyntheticLambda1.f$1 = qSTile;
            oplusQSResizeableTileView$$ExternalSyntheticLambda1.f$2 = clickExpandView;
            VarHandle.storeStoreFence();
            disposableHandlePreloadAndRelease = ViewExtKt.preloadAndRelease(secondaryClickHostView, oplusQSResizeableTileView$$ExternalSyntheticLambda0, oplusQSResizeableTileView$$ExternalSyntheticLambda1);
        }
        this.preloadDisposableHandle = disposableHandlePreloadAndRelease;
        OplusQSResizeableTileView$init$tileClick$1 oplusQSResizeableTileView$init$tileClick$1 = new OplusQSResizeableTileView$init$tileClick$1(i2);
        oplusQSResizeableTileView$init$tileClick$1.$expandable = clickExpandView;
        oplusQSResizeableTileView$init$tileClick$1.$tile = qSTile;
        oplusQSResizeableTileView$init$tileClick$1.this$0 = this;
        VarHandle.storeStoreFence();
        OplusQSResizeableTileView$init$tileClick$1 oplusQSResizeableTileView$init$tileClick$2 = new OplusQSResizeableTileView$init$tileClick$1(i);
        oplusQSResizeableTileView$init$tileClick$2.$tile = qSTile;
        oplusQSResizeableTileView$init$tileClick$2.$expandable = secondaryClickHostView;
        oplusQSResizeableTileView$init$tileClick$2.this$0 = this;
        VarHandle.storeStoreFence();
        OplusQSResizeableTileView$init$tileLongClick$1 oplusQSResizeableTileView$init$tileLongClick$1 = new OplusQSResizeableTileView$init$tileLongClick$1();
        oplusQSResizeableTileView$init$tileLongClick$1.$tile = qSTile;
        oplusQSResizeableTileView$init$tileLongClick$1.$expandable = clickExpandView;
        oplusQSResizeableTileView$init$tileLongClick$1.this$0 = this;
        VarHandle.storeStoreFence();
        bindClickListener(oplusQSResizeableTileView$init$tileClick$1, oplusQSResizeableTileView$init$tileClick$2, oplusQSResizeableTileView$init$tileLongClick$1, new AnonymousClass3(0, this, OplusQSResizeableTileView.class, "performViewClick", "performViewClick()V", 0));
        if (qSTile.isTileReady()) {
            setStateImmediately(qSTile.getState());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View initializeBgView() {
        return requireViewById(2131364808);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ViewGroup initializeIconFrame() {
        return (ViewGroup) requireViewById(2131363392);
    }

    public abstract IOplusQSTileViewLabelColorManager initializeLabelColorManager();

    /* JADX WARN: Multi-variable type inference failed */
    public FrameLayout initializeLeftView() {
        return (FrameLayout) requireViewById(2131363707);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View initializePadLockView() {
        return findViewById(2131364898);
    }

    public final boolean isEditMode() {
        return this.isEditMode;
    }

    public boolean isNeedLayer(View view) {
        return view.getId() != 2131364808;
    }

    public final void logD(Function0 function0) {
        String simpleName = getClass().getSimpleName();
        QSTile.State state = this.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            String str = (String) function0.invoke();
            if (str == null) {
                str = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str, "SystemUi--QuickSettings");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.LinearLayout*/.onAttachedToWindow();
        getBg().setBackground(this.transitionDrawable);
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(getTileViewSize());
        onOutlineUpdate(listListOf);
        onDrawableUpdate(listListOf, false);
        onDrawableUpdate(listListOf, true);
        onThemeApplied(BaseQSResPool.globalThemeApplied());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.LinearLayout*/.onConfigurationChanged(configuration);
        String simpleName = getClass().getSimpleName();
        QSTile.State state = this.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state != null ? state.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", strM.concat("-->onConfigurationChanged"));
        }
        this.iconView.updateIconColorImmediately();
        refreshTileOuterLabelColor(this.tileState);
        refreshTileInnerLabelColor(this.tileState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] onCreateDrawableState(int i) {
        InterestingViewFlag interestingViewFlag = getInterestingViewFlag();
        int[] drawableState = interestingViewFlag.getDrawableState();
        InterestingViewFlag interestingViewFlag2 = getInterestingViewFlag();
        QSTile.State state = this.tileState;
        boolean z = interestingViewFlag2.setDragged(this.isBeingDragged) || interestingViewFlag2.setTileState(state != null ? state.state : 1);
        QSTile.State state2 = this.tileState;
        if (!interestingViewFlag2.setToggleable(state2 != null && state2.toggleable) && !z && drawableState != null) {
            return drawableState;
        }
        String simpleName = getClass().getSimpleName();
        QSTile.State state3 = this.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state3 != null ? state3.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("onCreateDrawableState: viewFlag=" + interestingViewFlag);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int[] iArrOnCreateDrawableState = super/*android.widget.LinearLayout*/.onCreateDrawableState(i + 3);
        int tileState = interestingViewFlag.getTileState();
        if (tileState == 0) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Unavailable.getFlag());
        } else if (tileState == 2) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Active.getFlag());
        } else if (tileState != 101) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Inactive.getFlag());
        } else {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Inoperable.getFlag());
        }
        if (interestingViewFlag.isDragged()) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Dragged.getFlag());
        }
        if (interestingViewFlag.isToggleable()) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Toggleable.getFlag());
        }
        interestingViewFlag.setDrawableState(iArrOnCreateDrawableState);
        iArrOnCreateDrawableState.getClass();
        return iArrOnCreateDrawableState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super/*android.widget.LinearLayout*/.onInitializeAccessibilityEvent(accessibilityEvent);
        getAccessibilityHelper().onInitializeAccessibilityEvent(accessibilityEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super/*android.widget.LinearLayout*/.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        getAccessibilityHelper().onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size != this.lastMeasureWidth || size2 != this.lastMeasureHeight) {
            this.lastMeasureWidth = size;
            this.lastMeasureHeight = size2;
            handleCellLayoutConstraintChanged(CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), getTileViewSize(), false, false, 14));
        }
        super/*android.widget.LinearLayout*/.onMeasure(i, i2);
    }

    public void onQsColorStateChanged(QSTile.State state) {
        String simpleName = getClass().getSimpleName();
        QSTile.State state2 = this.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("onQsColorStateChanged state: " + state);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (state != null) {
            refreshTileInnerLabelColor(this.tileState);
            refreshTileOuterLabelColor(this.tileState);
        }
    }

    public void onRecycle() {
        DisposableHandle disposableHandle = this.preloadDisposableHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        this.preloadDisposableHandle = null;
        setBeingDragged(false);
        this.iconView.onRecycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onStateChanged(QSTile.State state) {
        String simpleName = getClass().getSimpleName();
        QSTile.State state2 = this.tileState;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(simpleName, "-", state2 != null ? state2.spec : null);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR);
            sbM.append("onStateChanged state: " + state + ", isAttachedToWindow : " + isAttachedToWindow());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        StateChangeRunnable stateChangeRunnable = new StateChangeRunnable(state.copy());
        removeCallbacks(stateChangeRunnable);
        post(stateChangeRunnable);
    }

    public final void onThemeApplied(boolean z) {
        this.iconView.onThemeApplied(z);
        if (!BaseQSResPool.globalThemeApplied() || this.isEditMode) {
            this.transitionDrawable.setTransitionAlpha(255);
        } else {
            this.transitionDrawable.setTransitionAlpha(0);
        }
    }

    public abstract void refreshClickable(QSTile.State state);

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshTileInnerLabelColor(QSTile.State state) {
        IOplusQSTileViewLabelColorManager labelResourceManager = getLabelResourceManager();
        labelResourceManager.initLabelColor(getContext());
        Pair<ColorStateList, ColorStateList> colorByTileState = labelResourceManager.getColorByTileState(getContext(), state);
        if (colorByTileState != null) {
            handleLabelColorChanged(colorByTileState);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshTileOuterLabelColor(QSTile.State state) {
        QsLabelView qsLabelView;
        if (!isAttachedToWindow() || (qsLabelView = this.labelView) == null) {
            return;
        }
        qsLabelView.updateColor(QsColorUtil.getQsColorState(), (state != null && state.state == 0) || (state != null && state.state == 101), this.isEditMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            refreshDrawableState();
        }
    }

    public final void setClicked(boolean z) {
        this.clicked = z;
    }

    public void setDeformPathProvider(OplusQsDeformRadiusParam oplusQsDeformRadiusParam) {
        this.deformPathProvider = oplusQsDeformRadiusParam;
    }

    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            if (z) {
                getIcon().disableAnimation();
            } else {
                getIcon().enableAnimation();
            }
            if (!BaseQSResPool.globalThemeApplied() || this.isEditMode) {
                this.transitionDrawable.setTransitionAlpha(255);
            } else {
                this.transitionDrawable.setTransitionAlpha(0);
            }
            this.iconView.disableTheme(z);
            if (BaseQSResPool.globalThemeApplied()) {
                getLabelResourceManager().disableTheme(this.isEditMode);
                refreshTileInnerLabelColor(this.tileState);
            }
            handleEditModeChanged(z);
            QSTile.State state = this.tileState;
            if (state != null) {
                refreshClickable(state);
            }
        }
    }

    public final void setLabelView(QsLabelView qsLabelView) {
        this.labelView = qsLabelView;
    }

    public final void setPosition(int i) {
    }

    public final void setStateImmediately(QSTile.State state) {
        this.iconView.updateIconColorImmediately();
        new StateChangeRunnable(state.copy()).run();
    }

    public final void setTileClickListener(TileClickListener tileClickListener) {
        this.tileClickListener = tileClickListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void spiltActionForLayerOrNot() {
        android.util.Pair pair = ((QSTileView) this).mSplitViews;
        List list = (List) pair.first;
        List list2 = (List) pair.second;
        list.clear();
        list2.clear();
        if (BaseQSResPool.supportMixColor()) {
            for (View view : ViewGroupKt.getChildren(this)) {
                if (getIconFrame() != view) {
                    list.add(view);
                    list2.add(view);
                } else {
                    for (View view2 : ViewGroupKt.getChildren(getIconFrame())) {
                        list.add(view2);
                        if (isNeedLayer(view2)) {
                            list2.add(view2);
                        }
                    }
                }
            }
        }
        if (list2.isEmpty()) {
            list.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View updateAccessibilityOrder(View view) {
        setAccessibilityTraversalAfter(view != null ? view.getId() : 0);
        return this;
    }

    public void updateSpotLightPath(Path path, Object obj) {
        CornerOutlineProvider pathProvider = this.transitionDrawable.getPathProvider();
        TileTransitionDrawable tileTransitionDrawable = this.transitionDrawable;
        CornerOutlineProvider.Companion companion = CornerOutlineProvider.Companion;
        pathProvider.updatePath(tileTransitionDrawable, path, null, obj);
    }

    public final FrameLayout getIconWithBackground() {
        return getLeftView();
    }
}
