package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson$;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.CalculateDeformSizeUtils;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeformationFrameLayout$dragResizeHelperCallback$1 implements IDragResizeHelperCallback {
    public /* synthetic */ Context $context;
    public boolean isDeforming;
    public /* synthetic */ DeformationFrameLayout this$0;

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback
    public final View getInnerViewAttachedData(SpanSize spanSize) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        if (!Intrinsics.areEqual(deformationFrameLayout.getCurrRealSize(), spanSize)) {
            ITileViewCreator iTileViewCreator = deformationFrameLayout.tileViewCreator;
            View viewFetchTileView = (iTileViewCreator != null ? iTileViewCreator : null).fetchTileView(deformationFrameLayout.getLabelView(), spanSize, true);
            DeformationFrameLayout.Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(viewFetchTileView, spanSize);
            return viewFetchTileView;
        }
        View currInnerView = deformationFrameLayout.getCurrInnerView();
        if (currInnerView != null) {
            return currInnerView;
        }
        Gson$.ExternalSyntheticBUOutline0.m$1(spanSize, "currInnerView is null for ");
        return null;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback
    public final Integer getInnerViewLeft(SpanSize spanSize) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        ITileViewCreator iTileViewCreator = deformationFrameLayout.tileViewCreator;
        if (iTileViewCreator == null) {
            iTileViewCreator = null;
        }
        Context context = this.$context;
        CellInfo cellInfo = iTileViewCreator.getCellInfo();
        if (deformationFrameLayout.isPersonalArea) {
            spanSize = DataConvertorKt.mergeTwoCols(spanSize);
        }
        SpanSize spanSize2 = spanSize;
        if (cellInfo == null) {
            return null;
        }
        int colSpan = ((spanSize2.getColSpan() - 1) * cellInfo.getHorizontalSpace()) + (spanSize2.getColSpan() * cellInfo.getCellWidth());
        ITileViewCreator iTileViewCreator2 = deformationFrameLayout.tileViewCreator;
        return Integer.valueOf((colSpan - (iTileViewCreator2 == null ? null : iTileViewCreator2).getLayoutConstraint(context, spanSize2, deformationFrameLayout.getEnableTileName(), deformationFrameLayout.isEditMode, deformationFrameLayout.isPersonalArea).getCapsuleWidth()) / 2);
    }

    public final void onSizeChanged(int i, int i2) {
        IDeformationCallback deformCallback;
        SpanSize spanSize;
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        if (deformationFrameLayout.isDragBarBePressing && (deformCallback = deformationFrameLayout.getDeformCallback()) != null) {
            BaseEditableViewHolder baseEditableViewHolder = ((BaseEditableViewHolder.AnonymousClass3) deformCallback).this$0;
            int i3 = BaseEditableViewHolder.$r8$clinit;
            if (baseEditableViewHolder.getViewModel() instanceof TileEditViewModel) {
                CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
                CellCalculatorManager companion2 = companion.getInstance(baseEditableViewHolder.getContext());
                SpanCache spanCache = SpanCache.INSTANCE;
                SpanSize spanSizeCalculateSpanSize = CalculateDeformSizeUtils.calculateSpanSize(companion2.getLayoutConstraint(spanCache.spanSize(1, 1), baseEditableViewHolder.getEnableTileName(), baseEditableViewHolder.isEditMode(), baseEditableViewHolder.isPersonalArea), companion.getInstance(baseEditableViewHolder.getContext()).getCellSize(baseEditableViewHolder.isPersonalArea), i, i2);
                if (!Intrinsics.areEqual(spanSizeCalculateSpanSize, baseEditableViewHolder.lastSpanSize)) {
                    GridData gridData = (GridData) baseEditableViewHolder.getData();
                    if (gridData == null || (spanSize = gridData.getSize()) == null) {
                        spanSize = spanCache.spanSize(1, 1);
                    }
                    baseEditableViewHolder.sendIntent(new TileEditIntent.DeformTileIntent(baseEditableViewHolder.getPosition(), spanSizeCalculateSpanSize));
                    LogUtil.i$default(baseEditableViewHolder.tag, "onSizeChange position=" + baseEditableViewHolder.getPosition() + " nSize=" + spanSizeCalculateSpanSize + " lastSpanSize=" + baseEditableViewHolder.lastSpanSize + " cSize=" + spanSize, null, null, 12);
                    baseEditableViewHolder.lastSpanSize = spanSizeCalculateSpanSize;
                }
            }
        }
        DeformationFrameLayout.updateStrokeAndDragBar$default(deformationFrameLayout);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback
    public final void recycle(View view) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        SpanSize size = DeformationFrameLayout.Companion.getSize(view);
        if (size == null || Intrinsics.areEqual(view, deformationFrameLayout.getCurrInnerView())) {
            return;
        }
        ITileViewCreator iTileViewCreator = deformationFrameLayout.tileViewCreator;
        if (iTileViewCreator == null) {
            iTileViewCreator = null;
        }
        iTileViewCreator.recycle(size, view);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback
    public void setDeforming(boolean z) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        if (this.isDeforming != z) {
            this.isDeforming = z;
            deformationFrameLayout.setClipChildren(z);
            deformationFrameLayout.setClipToPadding(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDragResizeHelperCallback
    public final void updateInnerView(View view, SpanSize spanSize) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DeformationFrameLayout-->".concat("[updateInnerView] " + spanSize + ", " + view));
        }
        DeformationFrameLayout.Companion.setSize$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(view, spanSize);
        if (!Intrinsics.areEqual(deformationFrameLayout.getCurrInnerView(), view)) {
            deformationFrameLayout.addInnerView(view, spanSize);
        }
        if (deformationFrameLayout.isPersonalArea) {
            spanSize = DataConvertorKt.mergeTwoCols(spanSize);
        }
        deformationFrameLayout.setCurSize(spanSize);
        IDeformationCallback deformCallback = deformationFrameLayout.getDeformCallback();
        if (deformCallback != null) {
            ((BaseEditableViewHolder.AnonymousClass3) deformCallback).onSizeUpdated(deformationFrameLayout.getCurSize());
        }
    }
}
