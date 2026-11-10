package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.core.view.ViewGroupKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.qs.IEditableView;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.DragResizeHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.TileFixedResizeHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.DropAnim;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaProperty;
import com.oplus.systemui.qs.base.springtransition.DrawableBoundsTransition;
import com.oplus.systemui.qs.base.springtransition.DrawableStrokeWidthProperty;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.ScaleXYTransition;
import com.oplus.systemui.qs.base.springtransition.VerticalBoundsTransition;
import com.oplus.systemui.qs.base.springtransition.ViewStrokeWidthProperty;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeformationFrameLayout extends MultiAnimFrameLayout implements ITransitionDesc, OplusItemSpringAnimator.ItemAnimationListener, IDeformableView {
    public static final Companion Companion = new Companion();
    private static final SpanSize SIZE_1X1;
    private static final SpanSize SIZE_2X1;
    private static final String TAG = "DeformationFrameLayout";
    private final Map<SpanSize, Size> _validSizeMap;
    private SpanSize curSize;
    private View currInnerView;
    private View.OnTouchListener defaultPressFeedbackHandler;
    private IDeformationCallback deformCallback;
    private final Lazy disableIcon$delegate;
    private final Lazy dragBarView$delegate;
    private final Lazy dragResizeHelper$delegate;
    private final DeformationFrameLayout$dragResizeHelperCallback$1 dragResizeHelperCallback;
    private View.OnTouchListener editingPressFeedBackHandler;
    public boolean enableTileName;
    private final DeformationFrameLayout$fixedResizeHelperCallback$1 fixedResizeHelperCallback;
    public boolean isBeingDragged;
    public boolean isDragBarBePressing;
    public boolean isDragDisable;
    public boolean isDragMode;
    public boolean isEditMode;
    public boolean isImmersiveOperation;
    public boolean isPersonalArea;
    private final Lazy labelView$delegate;
    public int lastMeasureHeight;
    public int lastMeasureWidth;
    private final Lazy removeIcon$delegate;
    public int removeMarginTop;
    private StrokeDrawable strokeDrawable;
    private final Lazy tileFixedResizeHelper$delegate;
    public ITileViewCreator tileViewCreator;
    private final Map<SpanSize, Size> validSizeMap;
    public boolean withResizeBar;
    public boolean withStrokeDrawable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SpanSize getSIZE_1X1() {
            return DeformationFrameLayout.SIZE_1X1;
        }

        public final SpanSize getSIZE_2X1() {
            return DeformationFrameLayout.SIZE_2X1;
        }

        public final SpanSize getSize(View view) {
            Object tag = view.getTag(2131365982);
            if (tag instanceof SpanSize) {
                return (SpanSize) tag;
            }
            return null;
        }

        public final void setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(View view, SpanSize spanSize) {
            view.setTag(2131365982, spanSize);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PressDragBarTouchListener implements View.OnTouchListener {
        public /* synthetic */ DeformationFrameLayout this$0;

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            DeformationFrameLayout deformationFrameLayout = this.this$0;
            if (action == 0) {
                View currInnerView = deformationFrameLayout.getCurrInnerView();
                ViewGroup viewGroup = currInnerView instanceof ViewGroup ? (ViewGroup) currInnerView : null;
                if (viewGroup != null) {
                    DeformationFrameLayout deformationFrameLayout2 = this.this$0;
                    if (deformationFrameLayout2.getDragResizeHelper().onActionDown(motionEvent, viewGroup, new DeformationFrameLayout$PressDragBarTouchListener$onTouch$1$1(0, deformationFrameLayout2, DeformationFrameLayout.class, "getCurrSupportSize", "getCurrSupportSize()Ljava/util/Map;", 0))) {
                        deformationFrameLayout2.setDragBarBePressing(true);
                        return true;
                    }
                }
            } else if (deformationFrameLayout.isDragBarBePressing) {
                deformationFrameLayout.setDragBarBePressing((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) ? false : true);
                if (this.this$0.getDragResizeHelper().onTouchEvent(motionEvent)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static TileFixedResizeHelper $r8$lambda$eH2lPouLPBpSlyrez5R3hzy4Mes(DeformationFrameLayout deformationFrameLayout) {
        return new TileFixedResizeHelper(deformationFrameLayout, deformationFrameLayout.fixedResizeHelperCallback);
    }

    public static boolean $r8$lambda$g2YSZWoL6ttpocTxoMD2kH11Hrc(boolean z, DeformationFrameLayout deformationFrameLayout, MotionEvent motionEvent) {
        Boolean boolValueOf = null;
        if (z) {
            View.OnTouchListener onTouchListener = deformationFrameLayout.defaultPressFeedbackHandler;
            if (onTouchListener != null) {
                boolValueOf = Boolean.valueOf(onTouchListener.onTouch(deformationFrameLayout, motionEvent));
            }
        } else {
            View.OnTouchListener onTouchListener2 = deformationFrameLayout.editingPressFeedBackHandler;
            if (onTouchListener2 != null) {
                boolValueOf = Boolean.valueOf(onTouchListener2.onTouch(deformationFrameLayout, motionEvent));
            }
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$zpA7-c-GlsITLfLSWLUtyOOSDVM, reason: not valid java name */
    public static DragResizeHelper m166$r8$lambda$zpA7cGlsITLfLSWLUtyOOSDVM(DeformationFrameLayout deformationFrameLayout) {
        return new DragResizeHelper(deformationFrameLayout, deformationFrameLayout.getDragBarView().getDragBarSize(), deformationFrameLayout.dragResizeHelperCallback);
    }

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        SIZE_1X1 = spanCache.spanSize(1, 1);
        SIZE_2X1 = spanCache.spanSize(2, 1);
    }

    public DeformationFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isPersonalArea = true;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this._validSizeMap = linkedHashMap;
        this.validSizeMap = linkedHashMap;
        this.curSize = SpanCache.INSTANCE.spanSize(1, 1);
        this.enableTileName = true;
        DeformationFrameLayout$dragResizeHelperCallback$1 deformationFrameLayout$dragResizeHelperCallback$1 = new DeformationFrameLayout$dragResizeHelperCallback$1();
        deformationFrameLayout$dragResizeHelperCallback$1.this$0 = this;
        deformationFrameLayout$dragResizeHelperCallback$1.$context = context;
        VarHandle.storeStoreFence();
        this.dragResizeHelperCallback = deformationFrameLayout$dragResizeHelperCallback$1;
        DeformationFrameLayout$fixedResizeHelperCallback$1 deformationFrameLayout$fixedResizeHelperCallback$1 = new DeformationFrameLayout$fixedResizeHelperCallback$1();
        deformationFrameLayout$fixedResizeHelperCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fixedResizeHelperCallback = deformationFrameLayout$fixedResizeHelperCallback$1;
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda0 = new DeformationFrameLayout$$ExternalSyntheticLambda0(0);
        deformationFrameLayout$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragResizeHelper$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda0);
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda1 = new DeformationFrameLayout$$ExternalSyntheticLambda0(1);
        deformationFrameLayout$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileFixedResizeHelper$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda1);
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda2 = new DeformationFrameLayout$$ExternalSyntheticLambda0(2);
        deformationFrameLayout$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.removeIcon$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda2);
        this.removeMarginTop = CellCalculatorManager.Companion.lockDpi(context.getResources().getDimensionPixelSize(2131171782), context);
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda3 = new DeformationFrameLayout$$ExternalSyntheticLambda0(3);
        deformationFrameLayout$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.disableIcon$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda3);
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda4 = new DeformationFrameLayout$$ExternalSyntheticLambda0(4);
        deformationFrameLayout$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragBarView$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda4);
        StrokeDrawable strokeDrawable = new StrokeDrawable(context);
        strokeDrawable.setCallback(this);
        this.strokeDrawable = strokeDrawable;
        DeformationFrameLayout$$ExternalSyntheticLambda0 deformationFrameLayout$$ExternalSyntheticLambda5 = new DeformationFrameLayout$$ExternalSyntheticLambda0(5);
        deformationFrameLayout$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.labelView$delegate = LazyKt__LazyJVMKt.lazy(deformationFrameLayout$$ExternalSyntheticLambda5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:6:0x0018  */
    public final Map<SpanSize, Size> getCurrSupportSize() {
        List list;
        IDeformationCallback iDeformationCallback = this.deformCallback;
        if (iDeformationCallback != null) {
            list = ((BaseEditableViewHolder.AnonymousClass3) iDeformationCallback).getActualSupportSize(CollectionsKt___CollectionsKt.toList(this._validSizeMap.keySet()));
            if (list == null) {
                list = CollectionsKt___CollectionsKt.toList(this._validSizeMap.keySet());
            }
        } else {
            list = CollectionsKt___CollectionsKt.toList(this._validSizeMap.keySet());
        }
        Map<SpanSize, Size> map = this._validSizeMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<SpanSize, Size> entry : map.entrySet()) {
            if (list.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    private final ImageView getDisableIcon() {
        return (ImageView) this.disableIcon$delegate.getValue();
    }

    private final DragBarView getDragBarView() {
        return (DragBarView) this.dragBarView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DragResizeHelper getDragResizeHelper() {
        return (DragResizeHelper) this.dragResizeHelper$delegate.getValue();
    }

    private final View.OnTouchListener getRealPressFeedbackHandler() {
        return this.isEditMode ? this.editingPressFeedBackHandler : this.defaultPressFeedbackHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getRemoveIcon() {
        return (ImageView) this.removeIcon$delegate.getValue();
    }

    private final TileFixedResizeHelper getTileFixedResizeHelper() {
        return (TileFixedResizeHelper) this.tileFixedResizeHelper$delegate.getValue();
    }

    private final float getTileViewRadius() {
        IEditableView iEditableView = this.currInnerView;
        IEditableView iEditableView2 = iEditableView instanceof IEditableView ? iEditableView : null;
        if (iEditableView2 != null) {
            return iEditableView2.getViewRadius();
        }
        return Intrinsics.areEqual(this.curSize, SpanCache.INSTANCE.spanSize(1, 1)) ? CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), this.curSize, false, false, 14).getCapsuleHeight() / 2.0f : getContext().getResources().getDimension(2131172172);
    }

    public static /* synthetic */ void init$default(DeformationFrameLayout deformationFrameLayout, SpanSize spanSize, ITileViewCreator iTileViewCreator, IDeformationAnimationController iDeformationAnimationController, boolean z, int i) {
        if ((i & 4) != 0) {
            iDeformationAnimationController = null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        deformationFrameLayout.init(spanSize, iTileViewCreator, iDeformationAnimationController, z);
    }

    public static String logString(View view) {
        StringBuilder sb = new StringBuilder("[");
        sb.append("V=" + view.getVisibility() + OplusPreventModeData.SEPARATOR);
        sb.append("A=" + view.getAlpha() + OplusPreventModeData.SEPARATOR);
        sb.append("tA=" + view.getTransitionAlpha() + OplusPreventModeData.SEPARATOR);
        sb.append("sX=" + view.getScaleX() + OplusPreventModeData.SEPARATOR);
        sb.append("sY=" + view.getScaleY() + OplusPreventModeData.SEPARATOR);
        sb.append("tX=" + view.getTranslationX() + OplusPreventModeData.SEPARATOR);
        sb.append("tY=" + view.getTranslationY() + OplusPreventModeData.SEPARATOR);
        sb.append("tZ=" + view.getTranslationZ() + OplusPreventModeData.SEPARATOR);
        sb.append("]");
        return sb.toString();
    }

    private final void setCurrInnerView(View view) {
        if (Intrinsics.areEqual(this.currInnerView, view)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setCurrInnerView] " + view + ", size=" + (view != null ? Companion.getSize(view) : null)));
        }
        this.currInnerView = view;
        IEditableView iEditableView = view instanceof IEditableView ? (IEditableView) view : null;
        if (iEditableView != null) {
            iEditableView.setEditMode(this.isEditMode);
        }
        View view2 = this.currInnerView;
        if (view2 != null) {
            ITileViewCreator iTileViewCreator = this.tileViewCreator;
            (iTileViewCreator != null ? iTileViewCreator : null).onInnerViewChanged(view2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragBarBePressing(boolean z) {
        if (this.isDragBarBePressing != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[isDragBarBePressing] " + z + " for " + this));
            }
            this.isDragBarBePressing = z;
            requestDisallowInterceptTouchEvent(z);
            IDeformationCallback iDeformationCallback = this.deformCallback;
            if (z) {
                if (iDeformationCallback != null) {
                    ((BaseEditableViewHolder.AnonymousClass3) iDeformationCallback).onDeformStarted();
                }
            } else if (iDeformationCallback != null) {
                ((BaseEditableViewHolder.AnonymousClass3) iDeformationCallback).onDeformEnd();
            }
        }
    }

    public static void updateStrokeAndDragBar$default(DeformationFrameLayout deformationFrameLayout) {
        float tileViewRadius = deformationFrameLayout.getTileViewRadius();
        View view = deformationFrameLayout.currInnerView;
        if (view != null) {
            deformationFrameLayout.strokeDrawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            deformationFrameLayout.strokeDrawable.setRadius(tileViewRadius);
            deformationFrameLayout.getDragBarView().setLeftTopRightBottom(view.getLeft(), view.getTop(), view.getRight() + ((int) deformationFrameLayout.getDragBarView().getStrokeExtraWidth()), view.getBottom() + ((int) deformationFrameLayout.getDragBarView().getStrokeExtraWidth()));
            deformationFrameLayout.getDragBarView().setRadius(tileViewRadius);
        }
    }

    public final void addInnerView(View view, SpanSize spanSize) {
        Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(view, spanSize);
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        if (this.isPersonalArea) {
            spanSize = DataConvertorKt.mergeTwoCols(spanSize);
        }
        SpanSize spanSize2 = spanSize;
        ITileViewCreator iTileViewCreator = this.tileViewCreator;
        CellCalculatorManager.LayoutConstraint layoutConstraint = (iTileViewCreator == null ? null : iTileViewCreator).getLayoutConstraint(getContext(), spanSize2, this.enableTileName, this.isEditMode, this.isPersonalArea);
        Size size = new Size(layoutConstraint.getCapsuleWidth(), layoutConstraint.getCapsuleHeight());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size.getWidth(), size.getHeight());
        layoutParams.gravity = 49;
        addView(view, 0, layoutParams);
    }

    public final void changeDragDisableMode() {
        int i = 8;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[changeDragDisableMode] " + getDisableIcon(), null, 8);
        ImageView disableIcon = getDisableIcon();
        if (this.isDragDisable && this.isBeingDragged && this.isEditMode) {
            i = 0;
        }
        disableIcon.setVisibility(i);
        disableIcon.setScaleX(getDisableIcon().getVisibility() == 0 ? 1.0f : 0.0f);
        disableIcon.setScaleY(getDisableIcon().getVisibility() == 0 ? 1.0f : 0.0f);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        switch (str.hashCode()) {
            case -1993040168:
                if (str.equals("parent_translation_transition")) {
                    list.add(new Pair<>(getRemoveIcon(), BoundsTransition.class));
                    View view = this.currInnerView;
                    if (view != null) {
                        list.add(new Pair<>(view, BoundsTransition.class));
                    }
                    if (this.enableTileName) {
                        list.add(new Pair<>(getLabelView(), BoundsTransition.class));
                        list.add(new Pair<>(getLabelView().getTextAndIndicatorView(), BoundsTransition.class));
                        list.add(new Pair<>(getLabelView().getIndicatorView(), FadeTransition.class));
                    }
                    if (this.withResizeBar) {
                        list.add(new Pair<>(getDragBarView(), FadeTransition.class));
                        list.add(new Pair<>(getDragBarView(), ScaleXYTransition.class));
                        list.add(new Pair<>(getDragBarView(), BoundsTransition.class));
                    }
                    if (this.withStrokeDrawable) {
                        list.add(new Pair<>(this.strokeDrawable, DrawableAlphaProperty.class));
                        list.add(new Pair<>(this.strokeDrawable, DrawableBoundsTransition.class));
                    }
                    list.add(new Pair<>(this, VerticalBoundsTransition.class));
                    return list;
                }
                return list;
            case -884953867:
                if (str.equals("remove_icon_scale_transition")) {
                    list.add(new Pair<>(getRemoveIcon(), ScaleXYTransition.class));
                    return list;
                }
                return list;
            case -462624981:
                if (str.equals("drag_label_transition") && this.enableTileName) {
                    list.add(new Pair<>(getLabelView(), FadeTransition.class));
                    return list;
                }
                return list;
            case 11668800:
                if (str.equals("drag_transition")) {
                    list.add(new Pair<>(getRemoveIcon(), BoundsTransition.class));
                    if (this.withResizeBar) {
                        list.add(new Pair<>(getDragBarView(), FadeTransition.class));
                        list.add(new Pair<>(getDragBarView(), ScaleXYTransition.class));
                        list.add(new Pair<>(getDragBarView(), ViewStrokeWidthProperty.class));
                    }
                    if (this.withStrokeDrawable) {
                        list.add(new Pair<>(this.strokeDrawable, DrawableStrokeWidthProperty.class));
                        return list;
                    }
                }
                return list;
            case 1709818871:
                if (str.equals("drag_disable_transition") && this.isBeingDragged) {
                    list.add(new Pair<>(getDisableIcon(), FadeTransition.class));
                    list.add(new Pair<>(getDisableIcon(), ScaleXYTransition.class));
                    return list;
                }
                return list;
            case 2061627489:
                if (str.equals("remove_icon_alpha_transition")) {
                    list.add(new Pair<>(getRemoveIcon(), FadeTransition.class));
                    return list;
                }
                return list;
            default:
                return list;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View.OnTouchListener realPressFeedbackHandler;
        View view = this.currInnerView;
        if (view != null) {
            if (motionEvent.getActionMasked() == 0 && this.isEditMode) {
                SharedSpotLightEffect sharedSpotLightEffect = SharedSpotLightEffect.INSTANCE;
                MotionEvent lastSpotLightDownEvent = sharedSpotLightEffect.getLastSpotLightDownEvent();
                if (lastSpotLightDownEvent != null) {
                    lastSpotLightDownEvent.recycle();
                }
                sharedSpotLightEffect.setLastSpotLightDownEvent(MotionEvent.obtain(motionEvent));
            }
            SharedSpotLightEffect.onTouchEvent(view, motionEvent);
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        IEditableView iEditableView = this.currInnerView;
        IEditableView iEditableView2 = iEditableView instanceof IEditableView ? iEditableView : null;
        boolean enablePressFeedback = iEditableView2 != null ? iEditableView2.getEnablePressFeedback() : true;
        if (!this.isDragBarBePressing && enablePressFeedback && (realPressFeedbackHandler = getRealPressFeedbackHandler()) != null) {
            realPressFeedbackHandler.onTouch(this, motionEvent);
        }
        return zDispatchTouchEvent;
    }

    public final void endAnim() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("endAnim: isDeforming = ", "DeformationFrameLayout-->", "SystemUi--QuickSettings", this.fixedResizeHelperCallback.isDeforming);
        }
        if (this.fixedResizeHelperCallback.isDeforming) {
            getTileFixedResizeHelper().onEnd();
            ViewGroup hostView = getHostView();
            if (!Intrinsics.areEqual(getSpanSize(), DropAnim.Companion.getSIZE_1X1()) || hostView == null) {
                return;
            }
            setRight(getMeasuredWidth() + getLeft());
            int width = hostView.getWidth();
            int iRoundToInt = MathKt.roundToInt((getWidth() - hostView.getWidth()) / 2.0f);
            hostView.setLeftTopRightBottom(iRoundToInt, hostView.getTop(), width + iRoundToInt, hostView.getBottom());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView
    public void executeSizeTransformAnimation(SpanSize spanSize, Function1 function1) {
        View view = this.currInnerView;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            SpanSize size = Companion.getSize(viewGroup);
            SpanSize spanSize2 = SIZE_1X1;
            if (Intrinsics.areEqual(size, spanSize2)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->executeSizeTransformAnimation labelView InVisible");
                }
                getLabelView().setVisibility(4);
            }
            Size size2 = this._validSizeMap.get(spanSize2);
            if (size2 == null) {
                return;
            }
            Map<SpanSize, Size> map = this._validSizeMap;
            SpanSize spanSize3 = SIZE_2X1;
            Size size3 = map.get(spanSize3);
            if (size3 == null) {
                return;
            }
            getTileFixedResizeHelper().sizeTransform(viewGroup, spanSize, getCurrRealSize(), MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(spanSize2, size2), TuplesKt.to(spanSize3, size3)}), function1);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView
    public IDeformationAnimationController getController() {
        return getTileFixedResizeHelper().getAnimationController();
    }

    public final SpanSize getCurSize() {
        return this.curSize;
    }

    public final View getCurrInnerView() {
        return this.currInnerView;
    }

    public final SpanSize getCurrRealSize() {
        boolean z = this.isPersonalArea;
        SpanSize spanSize = this.curSize;
        return z ? DataConvertorKt.revertToTwoCols(spanSize) : spanSize;
    }

    public final View.OnTouchListener getDefaultPressFeedbackHandler() {
        return this.defaultPressFeedbackHandler;
    }

    public final IDeformationCallback getDeformCallback() {
        return this.deformCallback;
    }

    public final View.OnTouchListener getEditingPressFeedBackHandler() {
        return this.editingPressFeedBackHandler;
    }

    public final boolean getEnableTileName() {
        return this.enableTileName;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView
    public ViewGroup getHostView() {
        View view = this.currInnerView;
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    public final QsLabelView getLabelView() {
        return (QsLabelView) this.labelView$delegate.getValue();
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator.ItemAnimationListener
    public boolean getNeedItemAddAnimation() {
        return !this.isDragMode;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator.ItemAnimationListener
    public boolean getNeedItemChangeAnimation() {
        return !this.dragResizeHelperCallback.isDeforming;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator.ItemAnimationListener
    public boolean getNeedItemMoveAnimation() {
        return !this.isBeingDragged;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator.ItemAnimationListener
    public boolean getNeedItemRemoveAnimation() {
        return !this.isDragMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView
    public SpanSize getSpanSize() {
        SpanSize size;
        View view = this.currInnerView;
        return (view == null || (size = Companion.getSize(view)) == null) ? SIZE_1X1 : size;
    }

    public final Map<SpanSize, Size> getValidSizeMap() {
        return this.validSizeMap;
    }

    public final boolean getWithResizeBar() {
        return this.withResizeBar;
    }

    public final boolean getWithStrokeDrawable() {
        return this.withStrokeDrawable;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void highlightDragBarAndStroke(boolean z) {
        StrokeDrawable strokeDrawable = this.strokeDrawable;
        if (z) {
            strokeDrawable.setStrokeWidth(CellCalculatorManager.Companion.lockDpi(getContext().getResources().getDimensionPixelSize(2131171792), getContext()));
            getDragBarView().setStrokeWidth(getDragBarView().getDragBarHighLightValue());
        } else {
            strokeDrawable.setStrokeWidth(CellCalculatorManager.Companion.lockDpi(getContext().getResources().getDimensionPixelSize(2131171791), getContext()));
            getDragBarView().setStrokeWidth(getDragBarView().getDragBarDefaultValue());
        }
    }

    public final void init(SpanSize spanSize, ITileViewCreator iTileViewCreator, IDeformationAnimationController iDeformationAnimationController, boolean z) {
        setPersonalArea(z);
        setWillNotDraw(false);
        this.tileViewCreator = iTileViewCreator;
        setCurSize(spanSize);
        getDragResizeHelper().setAnimationController(iDeformationAnimationController);
        getTileFixedResizeHelper().setAnimationController(iDeformationAnimationController);
        if (this.currInnerView == null) {
            refreshInnerView();
        }
        getRemoveIcon().setImageResource(2131235976);
        ImageView removeIcon = getRemoveIcon();
        Object parent = removeIcon.getParent();
        View view = parent instanceof View ? (View) parent : null;
        int dimension = ((int) getContext().getResources().getDimension(2131171779)) + ((int) getContext().getResources().getDimension(2131171780));
        Rect rect = new Rect();
        rect.left = 0;
        rect.right = dimension;
        rect.top = 0;
        rect.bottom = dimension;
        if (view != null) {
            view.setTouchDelegate(new TouchDelegate(rect, removeIcon));
        }
        onStrokeViewStatusChange(false);
        getDragBarView().onDragBarShowStatusChange(false);
        highlightDragBarAndStroke(false);
        onRemoveIconShowStatusChange(false);
        changeDragDisableMode();
    }

    public final boolean isDeforming() {
        return this.fixedResizeHelperCallback.isDeforming;
    }

    public final boolean isRemoveIconVisible() {
        return getRemoveIcon().getVisibility() == 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.removeMarginTop = CellCalculatorManager.Companion.lockDpi(getContext().getResources().getDimensionPixelSize(2131171782), getContext());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View.OnTouchListener onTouchListener = this.defaultPressFeedbackHandler;
        PressFeedbackHelper.QsTilePressFeedback qsTilePressFeedback = onTouchListener instanceof PressFeedbackHelper.QsTilePressFeedback ? (PressFeedbackHelper.QsTilePressFeedback) onTouchListener : null;
        if (qsTilePressFeedback != null) {
            qsTilePressFeedback.reset();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.strokeDrawable.getAlpha() != 0) {
            this.strokeDrawable.draw(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.isEditMode && isLongClickable() && accessibilityNodeInfo != null) {
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId(), getResources().getString(2131951848)));
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateStrokeAndDragBar$default(this);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size != this.lastMeasureWidth || size2 != this.lastMeasureHeight) {
            this.lastMeasureWidth = size;
            this.lastMeasureHeight = size2;
            updateCapsuleLayoutConstraint();
        }
        super.onMeasure(i, i2);
    }

    public final void onRecycled() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onRecycled(), , view: " + this, null, 8);
        getLabelView().updateText(null);
        setDragBarBePressing(false);
        setTransitionAlpha(1.0f);
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        onRemoveIconShowStatusChange(false);
        updateLabelViewVisibility();
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
        View view = this.currInnerView;
        IEditableView iEditableView = view instanceof IEditableView ? (IEditableView) view : null;
        if (iEditableView != null) {
            iEditableView.onRecycle();
        }
        getDelegate().reset();
    }

    public final void onRemoveIconShowStatusChange(boolean z) {
        ImageView removeIcon = getRemoveIcon();
        removeIcon.setVisibility(!z ? 4 : 0);
        removeIcon.setClickable(z);
        removeIcon.setScaleX(z ? 1.0f : 0.0f);
        removeIcon.setScaleY(z ? 1.0f : 0.0f);
    }

    public final void onStrokeViewStatusChange(boolean z) {
        this.strokeDrawable.setAlpha(z ? 153 : 0);
    }

    public final void refreshInnerView() {
        Iterator it = ViewGroupKt.getChildren(this).iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View view = (View) it.next();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[addViewInner] child size=" + Companion.getSize(view) + " " + ShareTransitionProperty.Companion.simpleNameAndHash(view)));
            }
            Companion companion = Companion;
            if (Intrinsics.areEqual(companion.getSize(view), getCurrRealSize())) {
                view.setVisibility(0);
                setCurrInnerView(view);
                z = true;
            } else if (companion.getSize(view) != null) {
                removeView(view);
                SpanSize size = companion.getSize(view);
                if (size != null) {
                    ITileViewCreator iTileViewCreator = this.tileViewCreator;
                    (iTileViewCreator != null ? iTileViewCreator : null).recycle(size, view);
                }
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[addViewInner] " + z + " for " + this));
        }
        if (z) {
            return;
        }
        ITileViewCreator iTileViewCreator2 = this.tileViewCreator;
        View viewFetchTileView = (iTileViewCreator2 != null ? iTileViewCreator2 : null).fetchTileView(getLabelView(), getCurrRealSize(), false);
        addInnerView(viewFetchTileView, getCurrRealSize());
        setCurrInnerView(viewFetchTileView);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[addViewInner] " + this.currInnerView + " for " + this));
        }
    }

    public final void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setIsBeingDragged] " + z + " for " + this));
            }
            this.isBeingDragged = z;
            updateLabelViewVisibility();
            View view = this.currInnerView;
            if (view != null) {
                if (z) {
                    isBeingDragged.2.1 r3 = new isBeingDragged.2.1();
                    r3.$hostView = view;
                    VarHandle.storeStoreFence();
                    post(r3);
                    return;
                }
                MotionEvent lastSpotLightDownEvent = SharedSpotLightEffect.INSTANCE.getLastSpotLightDownEvent();
                if (lastSpotLightDownEvent != null) {
                    MotionEvent motionEventObtain = MotionEvent.obtain(lastSpotLightDownEvent);
                    motionEventObtain.setAction(3);
                    SharedSpotLightEffect.onTouchEvent(view, motionEventObtain);
                    motionEventObtain.recycle();
                }
                SharedSpotLightEffect.recycleLastTouchEvent();
            }
        }
    }

    public final void setCurSize(SpanSize spanSize) {
        if (Intrinsics.areEqual(this.curSize, spanSize)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setSize] " + spanSize + " for " + this));
        }
        this.curSize = spanSize;
        refreshInnerView();
        updateCapsuleLayoutConstraint();
    }

    public final void setDefaultPressFeedbackHandler(View.OnTouchListener onTouchListener) {
        this.defaultPressFeedbackHandler = onTouchListener;
    }

    public final void setDeformCallback(IDeformationCallback iDeformationCallback) {
        this.deformCallback = iDeformationCallback;
    }

    public final void setDragDisable(boolean z) {
        if (this.isDragDisable != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setIsDragDisable] " + z + " for " + this, null, 8);
            this.isDragDisable = z;
            changeDragDisableMode();
        }
    }

    public final void setDragMode(boolean z) {
        this.isDragMode = z;
    }

    public final void setEditMode(boolean z) {
        IEditableView iEditableView;
        if (this.isEditMode != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setIsEditMode] " + z + " for " + this));
            }
            this.isEditMode = z;
            if (z) {
                LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[enterEditMode] " + getRemoveIcon() + " VISIBLE", null, 8);
                onRemoveIconShowStatusChange(true);
                View view = this.currInnerView;
                iEditableView = view instanceof IEditableView ? (IEditableView) view : null;
                if (iEditableView != null) {
                    iEditableView.setEditMode(true);
                }
                getDragBarView().onDragBarShowStatusChange(this.withResizeBar);
                onStrokeViewStatusChange(this.withStrokeDrawable);
            } else {
                LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[exitEditMode] " + getRemoveIcon() + " INVISIBLE", null, 8);
                onRemoveIconShowStatusChange(false);
                View view2 = this.currInnerView;
                iEditableView = view2 instanceof IEditableView ? (IEditableView) view2 : null;
                if (iEditableView != null) {
                    iEditableView.setEditMode(false);
                }
                getDragBarView().onDragBarShowStatusChange(false);
                onStrokeViewStatusChange(false);
            }
            updateCapsuleLayoutConstraint();
            DeformationFrameLayout$$ExternalSyntheticLambda9 deformationFrameLayout$$ExternalSyntheticLambda9 = new DeformationFrameLayout$$ExternalSyntheticLambda9();
            deformationFrameLayout$$ExternalSyntheticLambda9.f$0 = z;
            deformationFrameLayout$$ExternalSyntheticLambda9.f$1 = this;
            VarHandle.storeStoreFence();
            GlobalUtils.genCancelMotionEvent(deformationFrameLayout$$ExternalSyntheticLambda9);
        }
    }

    public final void setEditingPressFeedBackHandler(View.OnTouchListener onTouchListener) {
        this.editingPressFeedBackHandler = onTouchListener;
    }

    public final void setEnableTileName(boolean z) {
        if (this.enableTileName != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[enableTileName] ", "DeformationFrameLayout-->", "SystemUi--QuickSettings", z);
            }
            this.enableTileName = z;
            updateLabelViewVisibility();
            updateCapsuleLayoutConstraint();
        }
    }

    public final void setImmersiveOperation(boolean z) {
        if (this.isImmersiveOperation != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[isImmersiveOperation] " + z + " for " + this));
            }
            this.isImmersiveOperation = z;
            if (z) {
                LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[startImmersiveOperation] " + getRemoveIcon() + " VISIBLE", null, 8);
                onRemoveIconShowStatusChange(false);
                updateLabelViewVisibility();
                getDragBarView().onDragBarShowStatusChange(this.isDragBarBePressing);
                highlightDragBarAndStroke(this.isDragBarBePressing);
                return;
            }
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[endImmersiveOperation] " + getRemoveIcon() + " INVISIBLE", null, 8);
            onRemoveIconShowStatusChange(this.isEditMode);
            updateLabelViewVisibility();
            getDragBarView().onDragBarShowStatusChange(this.withResizeBar);
            highlightDragBarAndStroke(false);
        }
    }

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
        View view = this.currInnerView;
        if (view != null) {
            view.setLayerType(i, paint);
        }
        getLabelView().setLayerType(i, paint);
    }

    public final void setPersonalArea(boolean z) {
        if (this.isPersonalArea != z) {
            this.isPersonalArea = z;
            if (this.tileViewCreator != null) {
                refreshInnerView();
                updateCapsuleLayoutConstraint();
            }
        }
    }

    public final void setRemoveIconClickListener(View.OnClickListener onClickListener) {
        getRemoveIcon().setOnClickListener(onClickListener);
    }

    public final void setRemoveIconContentDescription(CharSequence charSequence) {
        getRemoveIcon().setContentDescription(charSequence);
        getRemoveIcon().setImportantForAccessibility(1);
    }

    public final void setValidSize(Map<SpanSize, Size> map) {
        if (map.isEmpty()) {
            return;
        }
        this._validSizeMap.clear();
        this._validSizeMap.putAll(map);
    }

    public final void setWithResizeBar(boolean z) {
        if (this.withResizeBar != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setWithResizeBar] " + z + " for " + this));
            }
            this.withResizeBar = z;
            if (this.isDragBarBePressing) {
                return;
            }
            getDragBarView().onDragBarShowStatusChange(this.isEditMode && z);
        }
    }

    public final void setWithStrokeDrawable(boolean z) {
        if (this.withStrokeDrawable != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[setWithStrokeDrawable] " + z + " for " + this));
            }
            this.withStrokeDrawable = z;
            onStrokeViewStatusChange(this.isEditMode && z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView
    public void sizeAnimContinue(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Function1 function1) {
        View view = this.currInnerView;
        SpanSize size = view != null ? Companion.getSize(view) : null;
        Companion companion = Companion;
        if (Intrinsics.areEqual(size, companion.getSize(viewGroup))) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->sizeAnimContinue currInnerView == lastHostView return");
            }
            onAnimationEnd();
            return;
        }
        Map<SpanSize, Size> map = this._validSizeMap;
        SpanSize spanSize = SIZE_1X1;
        Size size2 = map.get(spanSize);
        if (size2 == null) {
            return;
        }
        Map<SpanSize, Size> map2 = this._validSizeMap;
        SpanSize spanSize2 = SIZE_2X1;
        Size size3 = map2.get(spanSize2);
        if (size3 == null) {
            return;
        }
        Map<SpanSize, Size> mapMapOf = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(spanSize, size2), TuplesKt.to(spanSize2, size3)});
        View view2 = this.currInnerView;
        ViewGroup viewGroup2 = view2 instanceof ViewGroup ? (ViewGroup) view2 : null;
        if (viewGroup2 != null) {
            if (Intrinsics.areEqual(companion.getSize(viewGroup2), spanSize)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->sizeAnimContinue labelView InVisible");
                }
                getLabelView().setVisibility(4);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("sizeAnimContinue currHostView : " + viewGroup2 + ", lastHostView : " + viewGroup));
            }
            getTileFixedResizeHelper().continueAnimation(iDeformationAnimationController, viewGroup2, mapMapOf, function1);
        }
    }

    @Override // android.view.View
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        int length = sb.length() - 1;
        if (length < 0) {
            length = 0;
        }
        sb.insert(length, logString(this));
        sb.append(", enableTileName=" + this.enableTileName);
        sb.append(", isEditMode=" + this.isEditMode);
        sb.append(", isDragMode=" + this.isDragMode);
        sb.append(", isBeingDragged=" + this.isBeingDragged);
        sb.append(", withResizeBar=" + this.withResizeBar);
        sb.append(", isImmersiveOperation=" + this.isImmersiveOperation);
        sb.append(", removeIcon=".concat(logString(getRemoveIcon())));
        sb.append(", blockChangeFlag=" + getDelegate().blockTranslationAndScaleChange);
        sb.append(", enableTag=" + getDelegate().enableTag());
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateCapsuleLayoutConstraint() {
        ITileViewCreator iTileViewCreator = this.tileViewCreator;
        if (iTileViewCreator == null) {
            iTileViewCreator = null;
        }
        CellCalculatorManager.LayoutConstraint layoutConstraint = iTileViewCreator.getLayoutConstraint(getContext(), this.curSize, this.enableTileName, this.isEditMode, this.isPersonalArea);
        View view = this.currInnerView;
        Object[] objArr = 0;
        if (view != null) {
            DeformationFrameLayout$$ExternalSyntheticLambda6 deformationFrameLayout$$ExternalSyntheticLambda6 = new DeformationFrameLayout$$ExternalSyntheticLambda6(objArr == true ? 1 : 0);
            deformationFrameLayout$$ExternalSyntheticLambda6.f$0 = layoutConstraint;
            VarHandle.storeStoreFence();
            ViewExtKt.updateLayoutParamsOptimized(view, deformationFrameLayout$$ExternalSyntheticLambda6);
        }
        if (this.enableTileName) {
            getLabelView().setTextLineHeightAndSpacing(layoutConstraint.getLabelLineHeight());
            getLabelView().getTextView().setTextSize(0, layoutConstraint.getLabelSize());
            int i = 1;
            getLabelView().getTextView().setSingleLine(layoutConstraint.getLabelLines() == 1);
            getLabelView().getTextView().setMaxLines(layoutConstraint.getLabelLines());
            QsLabelView labelView = getLabelView();
            DeformationFrameLayout$$ExternalSyntheticLambda6 deformationFrameLayout$$ExternalSyntheticLambda7 = new DeformationFrameLayout$$ExternalSyntheticLambda6(i);
            deformationFrameLayout$$ExternalSyntheticLambda7.f$0 = layoutConstraint;
            VarHandle.storeStoreFence();
            ViewExtKt.updateLayoutParamsOptimized(labelView, deformationFrameLayout$$ExternalSyntheticLambda7);
            QsLabelView labelView2 = getLabelView();
            labelView2.setPaddingRelative(labelView2.getPaddingStart(), layoutConstraint.getLabelMarginTop(), labelView2.getPaddingEnd(), labelView2.getPaddingBottom());
        }
        ImageView removeIcon = getRemoveIcon();
        DeformationFrameLayout$$ExternalSyntheticLambda8 deformationFrameLayout$$ExternalSyntheticLambda8 = new DeformationFrameLayout$$ExternalSyntheticLambda8();
        deformationFrameLayout$$ExternalSyntheticLambda8.f$0 = this;
        deformationFrameLayout$$ExternalSyntheticLambda8.f$1 = layoutConstraint;
        VarHandle.storeStoreFence();
        ViewExtKt.updateLayoutParamsOptimized(removeIcon, deformationFrameLayout$$ExternalSyntheticLambda8);
    }

    public final void updateLabelViewVisibility() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("DeformationFrameLayout-->", "SystemUi--Common", TextFieldImplKt$.ExternalSyntheticOutline0.m("[updateLabelViewVisibility] isBeingDragged : ", ", enableTileName: ", ", isDragBarBePressing: ", this.isBeingDragged, this.enableTileName), this.isDragBarBePressing);
        }
        getLabelView().setVisibility(this.isBeingDragged || !this.enableTileName || this.isDragBarBePressing ? 4 : 0);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || Intrinsics.areEqual(drawable, this.strokeDrawable);
    }

    public DeformationFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public DeformationFrameLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ DeformationFrameLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public DeformationFrameLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
