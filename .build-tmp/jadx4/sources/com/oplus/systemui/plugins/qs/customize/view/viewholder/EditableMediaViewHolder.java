package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.MediaDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableMediaViewHolder extends BaseEditableViewHolder {
    private static final SpanSize BIGGER_SIZE;
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE;
    private static final String TAG = "EditableMediaViewHolder";
    public boolean isBeingDragged;
    public boolean isEditMode;
    private final Lazy qsMedia$delegate;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableMediaViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ EditableMediaViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            LogUtil.i$default(LogUtil.TAG_QS, EditableMediaViewHolder.TAG, "[fetchTileView] " + spanSize, null, 8);
            return this.this$0.getQsMedia().createView(new SpanSizeInPlugin(spanSize.getColSpan(), spanSize.getRowSpan()), z, !z);
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public CellInfo getCellInfo() {
            return this.this$0.getCellInfo();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SpanSize getBIGGER_SIZE() {
            return EditableMediaViewHolder.BIGGER_SIZE;
        }

        public final SpanSize getDEFAULT_SIZE() {
            return EditableMediaViewHolder.DEFAULT_SIZE;
        }
    }

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        DEFAULT_SIZE = spanCache.spanSize(1, 2);
        BIGGER_SIZE = spanCache.spanSize(2, 2);
    }

    public EditableMediaViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel);
        EditableMediaViewHolder$$ExternalSyntheticLambda0 editableMediaViewHolder$$ExternalSyntheticLambda0 = new EditableMediaViewHolder$$ExternalSyntheticLambda0();
        editableMediaViewHolder$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.qsMedia$delegate = LazyKt__LazyJVMKt.lazy(editableMediaViewHolder$$ExternalSyntheticLambda0);
        this.isEditMode = super.isEditMode;
        this.isBeingDragged = super.isBeingDragged;
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = DEFAULT_SIZE;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, anonymousClass1, new MediaDeformationAnimationController(), false, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QSElement getQsMedia() {
        return (QSElement) this.qsMedia$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        super.bindData(gridData);
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955212));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[bindData] ##curSize:" + getDeformationContainer().getCurSize() + " size:" + gridData.getSize(), null, 8);
        if (Intrinsics.areEqual(getDeformationContainer().getCurSize(), gridData.getSize()) || getDeformationContainer().isDragBarBePressing) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[bindData] curSize:" + getDeformationContainer().getCurSize() + " size:" + gridData.getSize(), null, 8);
        getDeformationContainer().setCurSize(gridData.getSize());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final void onThemeApplied(boolean z) {
        getQsMedia().onThemeApplied(z, new SpanSizeInPlugin(getDeformationContainer().getCurrRealSize().getColSpan(), getDeformationContainer().getCurrRealSize().getRowSpan()));
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        super.onViewCreated();
        getDeformationContainer().getLabelView().updateText(getContext().getResources().getString(2131955212));
        getDeformationContainer().getLabelView().updateColor(QsColorUtil.getQsColorState(), false, false);
        getDeformationContainer().setRemoveIconContentDescription(getContext().getString(2131955192, getContext().getResources().getString(2131955212), ""));
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            super.setBeingDragged(z);
            getQsMedia().setIsBeingDragged(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            super.setEditMode(z);
            View currInnerView = getDeformationContainer().getCurrInnerView();
            if (currInnerView != null) {
                ViewExtKt.setTalkBackEnabled(currInnerView, z, getContext().getString(2131955241));
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        getQsMedia().updateColor(colorType, new SpanSizeInPlugin(getDeformationContainer().getCurrRealSize().getColSpan(), getDeformationContainer().getCurrRealSize().getRowSpan()));
        QsLabelView labelView = getDeformationContainer().getLabelView();
        int qsColorState = QsColorUtil.getQsColorState();
        QsLabelView.Companion companion = QsLabelView.Companion;
        labelView.updateColor(qsColorState, false, false);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateDrawable(List<SpanSize> list, boolean z) {
        if (list.contains(SpanCache.TWO_X_TWO)) {
            getQsMedia().updateDrawable(z, new SpanSizeInPlugin(getDeformationContainer().getCurrRealSize().getColSpan(), getDeformationContainer().getCurrRealSize().getRowSpan()));
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateOutline(List<SpanSize> list) {
        if (list.contains(SpanCache.TWO_X_TWO)) {
            getQsMedia().updateOutline(new SpanSizeInPlugin(getDeformationContainer().getCurrRealSize().getColSpan(), getDeformationContainer().getCurrRealSize().getRowSpan()));
        }
    }
}
