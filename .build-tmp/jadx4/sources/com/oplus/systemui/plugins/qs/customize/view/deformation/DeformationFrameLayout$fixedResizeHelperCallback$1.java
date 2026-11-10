package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.util.Log;
import android.view.View;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.google.gson.Gson$;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeformationFrameLayout$fixedResizeHelperCallback$1 implements IFixedResizeHelperCallback {
    public boolean isDeforming;
    public /* synthetic */ DeformationFrameLayout this$0;

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback
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

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback
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

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback
    public void setDeforming(boolean z) {
        DeformationFrameLayout deformationFrameLayout = this.this$0;
        if (this.isDeforming != z) {
            this.isDeforming = z;
            deformationFrameLayout.setClipChildren(z);
            deformationFrameLayout.setClipToPadding(z);
        }
        deformationFrameLayout.getLabelView().setTranslationX(z ? (deformationFrameLayout.getWidth() - deformationFrameLayout.getLabelView().getWidth()) / 2.0f : 0.0f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[isDeforming] value ", ", isDragMode", "DeformationFrameLayout-->", z, deformationFrameLayout.isDragMode));
        }
        if (z || deformationFrameLayout.isDragMode) {
            return;
        }
        deformationFrameLayout.updateLabelViewVisibility();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback.IFixedResizeHelperCallback
    public void setRemoveIconTranslationX(float f) {
        this.this$0.getRemoveIcon().setTranslationX(f);
    }
}
