package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.view.drawable.EmptyPlaceHolderProxy;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.SpanCache;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderAction extends ViewController implements ITransitionDesc {
    private final Context context;
    public boolean isEditMode;
    public StaggeredPagerLayoutManager layoutManager;
    private EmptyPlaceHolderProxy placeHolderProvider;
    private final COUIRecyclerView recyclerView;

    public EmptyPlaceHolderAction(COUIRecyclerView cOUIRecyclerView) {
        super(cOUIRecyclerView);
        this.recyclerView = cOUIRecyclerView;
        this.context = cOUIRecyclerView.getContext();
        this.placeHolderProvider = new EmptyPlaceHolderProxy(cOUIRecyclerView);
    }

    private final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            updatePlaceHolder();
        }
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if (Intrinsics.areEqual(str, "drag_transition")) {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.layoutManager;
            if (staggeredPagerLayoutManager == null) {
                staggeredPagerLayoutManager = null;
            }
            Iterator<T> it = staggeredPagerLayoutManager.getAllEmptyDrawables().iterator();
            while (it.hasNext()) {
                list.add(new Pair<>((Drawable) it.next(), EmptyPlaceHolderProxy.PlaceHolderFgAlphaTransition.class));
            }
        }
        return list;
    }

    public final void onConfigurationChanged() {
        this.placeHolderProvider = new EmptyPlaceHolderProxy(this.recyclerView);
        updatePlaceHolder();
    }

    public final void onEnterAddingMode() {
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.layoutManager;
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        for (Drawable drawable : staggeredPagerLayoutManager.getAllEmptyDrawables()) {
            EmptyPlaceHolderProxy.PlaceHolderLayerDrawable placeHolderLayerDrawable = drawable instanceof EmptyPlaceHolderProxy.PlaceHolderLayerDrawable ? (EmptyPlaceHolderProxy.PlaceHolderLayerDrawable) drawable : null;
            if (placeHolderLayerDrawable != null) {
                placeHolderLayerDrawable.setFgAlpha(0);
            }
        }
    }

    public final void onEnterEditMode(boolean z) {
        setEditMode(true);
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1 emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1 = new EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1(1);
        emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1.this$0 = this;
        emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1.$withAnimation$inlined = z;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(cOUIRecyclerView, emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1);
    }

    public final void onExitAddingMode() {
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.layoutManager;
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        for (Drawable drawable : staggeredPagerLayoutManager.getAllEmptyDrawablesByLoc()) {
            EmptyPlaceHolderProxy.PlaceHolderLayerDrawable placeHolderLayerDrawable = drawable instanceof EmptyPlaceHolderProxy.PlaceHolderLayerDrawable ? (EmptyPlaceHolderProxy.PlaceHolderLayerDrawable) drawable : null;
            if (placeHolderLayerDrawable != null) {
                placeHolderLayerDrawable.setFgAlpha(255);
            }
        }
    }

    public final void onExitEditMode(boolean z) {
        setEditMode(false);
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1 emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1 = new EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1(0);
        emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1.this$0 = this;
        emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1.$withAnimation$inlined = z;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(cOUIRecyclerView, emptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        if (staggeredPagerLayoutManager == null) {
            Path$.ExternalSyntheticBUOutline0.m("onViewAttached no valid layoutManager");
        } else {
            this.layoutManager = staggeredPagerLayoutManager;
            updatePlaceHolder();
        }
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
    }

    public final void updatePlaceHolder() {
        View view = this.mView;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        CellCalculatorManager.LayoutConstraint layoutConstraint$default = CellCalculatorManager.getLayoutConstraint$default(companion.getInstance(this.context), SpanCache.INSTANCE.spanSize(1, 1), false, this.isEditMode, 8);
        Drawable drawable = this.placeHolderProvider.getDrawable();
        int cellWidth = (companion.getInstance(this.context).getCellSize(true).getCellWidth() - layoutConstraint$default.getCapsuleWidth()) / 2;
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.layoutManager;
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        Rect rect = new Rect(cellWidth, layoutConstraint$default.getLayoutMarginTop(), layoutConstraint$default.getCapsuleWidth() + cellWidth, layoutConstraint$default.getCapsuleHeight() + layoutConstraint$default.getLayoutMarginTop());
        EmptyPlaceHolderAction$$ExternalSyntheticLambda0 emptyPlaceHolderAction$$ExternalSyntheticLambda0 = new EmptyPlaceHolderAction$$ExternalSyntheticLambda0();
        emptyPlaceHolderAction$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        staggeredPagerLayoutManager.setEmptyPlaceHolder(drawable, rect, emptyPlaceHolderAction$$ExternalSyntheticLambda0);
    }
}
