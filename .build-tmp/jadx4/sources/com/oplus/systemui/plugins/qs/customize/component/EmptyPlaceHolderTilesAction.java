package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.util.QSColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import java.lang.invoke.VarHandle;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderTilesAction extends ViewController {
    private final Context context;
    public boolean isEditMode;
    public StaggeredPagerLayoutManager layoutManager;
    private final COUIRecyclerView recyclerView;

    public static Drawable $r8$lambda$xD92qGHR1awMnqt3w2MhIKggjYs(Drawable drawable, EmptyPlaceHolderTilesAction emptyPlaceHolderTilesAction, CellCalculatorManager.LayoutConstraint layoutConstraint, Drawable drawable2) {
        Drawable.ConstantState constantState;
        Drawable drawableNewDrawable;
        if (!(drawable instanceof BlurDrawable)) {
            return (drawable == null || (constantState = drawable.getConstantState()) == null || (drawableNewDrawable = constantState.newDrawable(emptyPlaceHolderTilesAction.context.getResources())) == null) ? drawable2 : drawableNewDrawable;
        }
        BlurDrawable blurDrawableCopy = ((BlurDrawable) drawable).copy(emptyPlaceHolderTilesAction.context);
        blurDrawableCopy.setShaderBlendParams(QSBlurConfigProvider.getSepEmptyHolderBlurConfig(emptyPlaceHolderTilesAction.context.getResources().getConfiguration().isNightModeActive(), 1.0f).getMixMultiShaderParams());
        blurDrawableCopy.setCornerRadius(layoutConstraint.getCapsuleWidth() / 2.0f);
        return blurDrawableCopy;
    }

    public EmptyPlaceHolderTilesAction(COUIRecyclerView cOUIRecyclerView) {
        super(cOUIRecyclerView);
        this.recyclerView = cOUIRecyclerView;
        this.context = cOUIRecyclerView.getContext();
    }

    private final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            updatePlaceHolder$1();
        }
    }

    public final void onEnterAddingMode(boolean z) {
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        EmptyPlaceHolderTilesAction$onExitAddingMode$.inlined.doOnPreDraw.1 r1 = new EmptyPlaceHolderTilesAction$onExitAddingMode$.inlined.doOnPreDraw.1(1);
        r1.this$0 = this;
        r1.$withAnimation$inlined = z;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(cOUIRecyclerView, r1);
    }

    public final void onEnterEditMode(boolean z) {
        setEditMode(true);
    }

    public final void onExitAddingMode(boolean z) {
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        EmptyPlaceHolderTilesAction$onExitAddingMode$.inlined.doOnPreDraw.1 r1 = new EmptyPlaceHolderTilesAction$onExitAddingMode$.inlined.doOnPreDraw.1(0);
        r1.this$0 = this;
        r1.$withAnimation$inlined = z;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(cOUIRecyclerView, r1);
    }

    public final void onExitEditMode(boolean z) {
        setEditMode(false);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        if (staggeredPagerLayoutManager == null) {
            Path$.ExternalSyntheticBUOutline0.m("onViewAttached no valid layoutManager");
        } else {
            this.layoutManager = staggeredPagerLayoutManager;
            updatePlaceHolder$1();
        }
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
    }

    public final void updatePlaceHolder$1() {
        View view = this.mView;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        CellCalculatorManager.LayoutConstraint layoutConstraint$default = CellCalculatorManager.getLayoutConstraint$default(companion.getInstance(this.context), SpanCache.INSTANCE.spanSize(1, 1), false, this.isEditMode, 2);
        int cellWidth = (companion.getInstance(this.context).getCellSize(false).getCellWidth() - layoutConstraint$default.getCapsuleWidth()) / 2;
        float capsuleWidth = layoutConstraint$default.getCapsuleWidth() / 2.0f;
        boolean zIsMixColorSupport = QsViewBackgroundKt.isMixColorSupport(this.context, QSColorUtil.isGaussBlurDisabled());
        Context context = this.context;
        BlurDrawable sepEmptyHolderBlurDrawable$default = zIsMixColorSupport ? QSBlurConfigProvider.getSepEmptyHolderBlurDrawable$default(context, this.recyclerView.getViewRootImpl().getSurfaceControl(), capsuleWidth, false, null, 56) : context.getDrawable(2131233384);
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.layoutManager;
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        Rect rect = new Rect(cellWidth, layoutConstraint$default.getLayoutMarginTop(), layoutConstraint$default.getCapsuleWidth() + cellWidth, layoutConstraint$default.getCapsuleHeight() + layoutConstraint$default.getLayoutMarginTop());
        EmptyPlaceHolderTilesAction$$ExternalSyntheticLambda0 emptyPlaceHolderTilesAction$$ExternalSyntheticLambda0 = new EmptyPlaceHolderTilesAction$$ExternalSyntheticLambda0();
        emptyPlaceHolderTilesAction$$ExternalSyntheticLambda0.f$0 = sepEmptyHolderBlurDrawable$default;
        emptyPlaceHolderTilesAction$$ExternalSyntheticLambda0.f$1 = this;
        emptyPlaceHolderTilesAction$$ExternalSyntheticLambda0.f$2 = layoutConstraint$default;
        VarHandle.storeStoreFence();
        staggeredPagerLayoutManager.setEmptyPlaceHolder(sepEmptyHolderBlurDrawable$default, rect, emptyPlaceHolderTilesAction$$ExternalSyntheticLambda0);
    }
}
