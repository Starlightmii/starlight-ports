package com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ViewOverlayManagerKt;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileFixedBoundShareTransitionProperty extends TileFixedShareTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileFixedBoundShareTransitionProperty";
    private final Rect endRect;
    public final boolean isNestedBoundShare;
    private BaseDeformationAnimationController.OnAnimateListener onAnimateListener;
    private TileFixedShareTransitionProperty.SpringAnimParam springAnimParam;
    private final Lazy springAnimation$delegate;
    private final Rect startRect;
    private final Rect tmpRect;

    public static COUISpringAnimation $r8$lambda$xNFJ7wrsvtMKMOb6MKqGkGSdPeU(TileFixedBoundShareTransitionProperty tileFixedBoundShareTransitionProperty) {
        COUISpringAnimation cOUISpringAnimationCreateSpringAnimation = tileFixedBoundShareTransitionProperty.createSpringAnimation("开关固定形变胶囊宽度动画", tileFixedBoundShareTransitionProperty.springAnimParam.getValue(), 0.17f, 0.35f, OliveLinkConnector.SEP_QS_SERVER_NAME);
        springAnimation.2.1.1 r0 = new springAnimation.2.1.1();
        r0.this$0 = tileFixedBoundShareTransitionProperty;
        VarHandle.storeStoreFence();
        cOUISpringAnimationCreateSpringAnimation.addUpdateListener(r0);
        springAnimation.2.1.2 r1 = new springAnimation.2.1.2();
        r1.this$0 = tileFixedBoundShareTransitionProperty;
        VarHandle.storeStoreFence();
        cOUISpringAnimationCreateSpringAnimation.addEndListener(r1);
        return cOUISpringAnimationCreateSpringAnimation;
    }

    public TileFixedBoundShareTransitionProperty(ViewGroup viewGroup, int i, boolean z) {
        super(viewGroup, i, new Rect(), new Rect());
        this.isNestedBoundShare = z;
        this.startRect = new Rect();
        this.endRect = new Rect();
        this.tmpRect = new Rect();
        this.springAnimParam = new TileFixedShareTransitionProperty.SpringAnimParam();
        TileFixedBoundShareTransitionProperty$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new TileFixedBoundShareTransitionProperty$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnimation$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
    }

    private final COUISpringAnimation getSpringAnimation() {
        return (COUISpringAnimation) this.springAnimation$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedShareTransitionProperty, com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void captureValues(List<? extends ViewGroup> list) {
        super.captureValues(list);
        for (ViewGroup viewGroup : list) {
            if (viewGroup instanceof OplusQSResizeableTileView) {
                SpanSize tileViewSize = ((OplusQSResizeableTileView) viewGroup).getTileViewSize();
                TileFixedShareTransitionProperty.Companion companion = TileFixedShareTransitionProperty.Companion;
                if (Intrinsics.areEqual(tileViewSize, companion.getSIZE_1X1())) {
                    this.startRect.set((Rect) getValueFrom(viewGroup));
                } else if (Intrinsics.areEqual(tileViewSize, companion.getSIZE_2X1())) {
                    this.endRect.set((Rect) getValueFrom(viewGroup));
                    Rect rect = this.endRect;
                    if (rect.top <= 0) {
                        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
                        rect.top = marginLayoutParams != null ? marginLayoutParams.topMargin : 0;
                        Rect rect2 = this.endRect;
                        rect2.bottom += rect2.top;
                    }
                }
            }
        }
        this.springAnimParam.setValue(Intrinsics.areEqual(getCurSpanSize(), TileFixedShareTransitionProperty.Companion.getSIZE_1X1()) ? 1.0f : 0.0f);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedShareTransitionProperty
    public TileFixedShareTransitionProperty createShareTransitionCopy(ViewGroup viewGroup) {
        TileFixedBoundShareTransitionProperty tileFixedBoundShareTransitionProperty = new TileFixedBoundShareTransitionProperty(viewGroup, getSharedElementId(), false);
        tileFixedBoundShareTransitionProperty.setValue(getValue());
        tileFixedBoundShareTransitionProperty.startRect.set(this.startRect);
        tileFixedBoundShareTransitionProperty.endRect.set(this.endRect);
        tileFixedBoundShareTransitionProperty.springAnimParam = TileFixedShareTransitionProperty.SpringAnimParam.copy$default(this.springAnimParam, Intrinsics.areEqual(DeformationFrameLayout.Companion.getSize(viewGroup), TileFixedShareTransitionProperty.Companion.getSIZE_1X1()) ? 0.0f : 1.0f);
        if (this.springAnimParam.getValue() == tileFixedBoundShareTransitionProperty.springAnimParam.getFinalPosition()) {
            return tileFixedBoundShareTransitionProperty;
        }
        COUISpringAnimation springAnimation = tileFixedBoundShareTransitionProperty.getSpringAnimation();
        springAnimation.setStartValue(tileFixedBoundShareTransitionProperty.springAnimParam.getValue());
        springAnimation.setStartVelocity(tileFixedBoundShareTransitionProperty.springAnimParam.getVelocity());
        springAnimation.animateToFinalPosition(tileFixedBoundShareTransitionProperty.springAnimParam.getFinalPosition());
        return tileFixedBoundShareTransitionProperty;
    }

    public final BaseDeformationAnimationController.OnAnimateListener getOnAnimateListener() {
        return this.onAnimateListener;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public Rect getValue(View view) {
        Rect rect = new Rect();
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedShareTransitionProperty
    public final void initPreView() {
        SpanSize curSpanSize = getCurSpanSize();
        TileFixedShareTransitionProperty.Companion companion = TileFixedShareTransitionProperty.Companion;
        if (Intrinsics.areEqual(curSpanSize, companion.getSIZE_1X1())) {
            this.tmpRect.set(this.endRect);
        } else if (Intrinsics.areEqual(getCurSpanSize(), companion.getSIZE_2X1())) {
            this.tmpRect.set(this.startRect);
        }
        setValue(this.tmpRect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final void onTransitionEnd(boolean z) {
        View viewFindViewByIdUseCache;
        super.onTransitionEnd(false);
        ViewGroup targetViewHost = getTargetViewHost();
        if (targetViewHost != 0) {
            if (targetViewHost instanceof OplusQSResizeableTileView) {
                SpanSize tileViewSize = ((OplusQSResizeableTileView) targetViewHost).getTileViewSize();
                TileFixedShareTransitionProperty.Companion companion = TileFixedShareTransitionProperty.Companion;
                if (Intrinsics.areEqual(tileViewSize, companion.getSIZE_1X1())) {
                    this.tmpRect.set((Rect) getValueFrom(targetViewHost));
                } else if (Intrinsics.areEqual(tileViewSize, companion.getSIZE_2X1())) {
                    this.tmpRect.set((Rect) getValueFrom(targetViewHost));
                    Rect rect = this.tmpRect;
                    if (rect.top <= 0) {
                        ViewGroup.LayoutParams layoutParams = targetViewHost.getLayoutParams();
                        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
                        rect.top = marginLayoutParams != null ? marginLayoutParams.topMargin : 0;
                        Rect rect2 = this.tmpRect;
                        rect2.bottom += rect2.top;
                    }
                }
            }
            if (!this.isNestedBoundShare && (viewFindViewByIdUseCache = ShareTransitionProperty.Companion.findViewByIdUseCache(targetViewHost, getSharedElementId(), true)) != null) {
                ViewOverlayManagerKt.minusBoundsDiff(viewFindViewByIdUseCache, this.tmpRect);
            }
            setValue(this.tmpRect);
            if (this.isCreateSpringAnimation) {
                getSpringAnimation().cancel();
            }
            this.onAnimateListener = null;
            ShareTransitionProperty.Companion.clearIdCache(targetViewHost, getSharedElementId());
        }
    }

    public final void setOnAnimateListener(BaseDeformationAnimationController.OnAnimateListener onAnimateListener) {
        this.onAnimateListener = onAnimateListener;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void setValue(View view, Rect rect) {
        if (view.getParent() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "TileFixedBoundShareTransitionProperty-->target.parent is null, return");
                return;
            }
            return;
        }
        int iWidth = rect.width();
        int iRoundToInt = MathKt.roundToInt((((View) view.getParent()).getWidth() - iWidth) / 2.0f);
        int i = rect.top;
        view.setLeftTopRightBottom(iRoundToInt, i, iWidth + iRoundToInt, rect.height() + i);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedShareTransitionProperty
    public void startTransition(Rect rect, Rect rect2, float f, float f2) {
        float f3 = Intrinsics.areEqual(rect2, this.endRect) ? 1.0f : 0.0f;
        this.springAnimParam.setFinalPosition(f3);
        getSpringAnimation().animateToFinalPosition(f3);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("startTransition finalPosition = ", f3, "TileFixedBoundShareTransitionProperty-->", "SystemUi--QuickSettings");
        }
    }
}
