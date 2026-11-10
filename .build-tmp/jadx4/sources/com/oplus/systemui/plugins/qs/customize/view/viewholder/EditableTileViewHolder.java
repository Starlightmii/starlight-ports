package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.TileDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSIconView;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableThreeStageView;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewOneXOne;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXTwo;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSThreeStageIconView;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.TileUiType;
import com.oplus.systemui.plugins.qs.util.VibratorHelper;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EditableTileViewHolder extends BaseEditableViewHolder implements QSTile.Callback {
    private static final String TAG = "EditableTileViewHolder";
    public boolean addTileCallback;
    public boolean isBeingDragged;
    public boolean isListening;
    private final EditableTileViewHolder$qsTileClickListener$1 qsTileClickListener;
    public static final Companion Companion = new Companion();
    private static final Lazy qsTileViewCache$delegate = LazyKt__LazyJVMKt.lazy(new EditableTileViewHolder$$ExternalSyntheticLambda0());

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ITileViewCreator {
        public /* synthetic */ EditableTileViewHolder this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z) {
            OplusQSResizeableTileView oplusQSResizeableTileView;
            TileViewModel tileViewModel;
            QSTile qsTile;
            EditableTileViewHolder editableTileViewHolder = this.this$0;
            if (!z) {
                QSTileView qSTileViewFetchTileView = editableTileViewHolder.fetchTileView(spanSize, false);
                oplusQSResizeableTileView = qSTileViewFetchTileView instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) qSTileViewFetchTileView : null;
                if (oplusQSResizeableTileView != null) {
                    oplusQSResizeableTileView.setLabelView(qsLabelView);
                    oplusQSResizeableTileView.setTileClickListener(editableTileViewHolder.qsTileClickListener);
                }
                return qSTileViewFetchTileView;
            }
            QSTileView qSTileViewFetchTileView2 = editableTileViewHolder.fetchTileView(spanSize, true);
            oplusQSResizeableTileView = qSTileViewFetchTileView2 instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) qSTileViewFetchTileView2 : null;
            if (oplusQSResizeableTileView != null) {
                oplusQSResizeableTileView.setLabelView(qsLabelView);
                oplusQSResizeableTileView.setEditMode(editableTileViewHolder.isEditMode);
                GridData gridData = (GridData) editableTileViewHolder.getData();
                if (gridData != null && (tileViewModel = (TileViewModel) gridData.getData()) != null && (qsTile = tileViewModel.getQsTile()) != null) {
                    oplusQSResizeableTileView.setStateImmediately(qsTile.getState());
                }
            }
            return qSTileViewFetchTileView2;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public CellInfo getCellInfo() {
            return this.this$0.getCellInfo();
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final void onInnerViewChanged(View view) {
            TileViewModel tileViewModel;
            EditableTileViewHolder editableTileViewHolder = this.this$0;
            Companion companion = EditableTileViewHolder.Companion;
            GridData gridData = (GridData) editableTileViewHolder.getData();
            QSTile qsTile = (gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null) ? null : tileViewModel.getQsTile();
            if (qsTile != null) {
                editableTileViewHolder.getCurrRealTileView().init(qsTile);
                qsTile.refreshState();
            }
            View.OnTouchListener defaultPressFeedbackHandler = editableTileViewHolder.getDeformationContainer().getDefaultPressFeedbackHandler();
            PressFeedbackHelper.QsTilePressFeedback qsTilePressFeedback = defaultPressFeedbackHandler instanceof PressFeedbackHelper.QsTilePressFeedback ? (PressFeedbackHelper.QsTilePressFeedback) defaultPressFeedbackHandler : null;
            if (qsTilePressFeedback != null) {
                qsTilePressFeedback.setTargetView(view);
            }
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.ITileViewCreator
        public final void recycle(SpanSize spanSize, View view) {
            QSTileView qSTileView = view instanceof QSTileView ? (QSTileView) view : null;
            if (qSTileView != null) {
                EditableTileViewHolder.Companion.recycle(qSTileView);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final SparseArray<List<QSTileView>> getQsTileViewCache() {
            return (SparseArray) EditableTileViewHolder.qsTileViewCache$delegate.getValue();
        }

        public final void clear() {
            getQsTileViewCache().clear();
        }

        public final QSTileView fetch(SpanSize spanSize, TileUiType tileUiType, Context context) {
            int i;
            if (Intrinsics.areEqual(spanSize, SpanCache.TWO_X_TWO)) {
                i = 3;
            } else if (Intrinsics.areEqual(spanSize, SpanCache.TWO_X_ONE)) {
                i = tileUiType == TileUiType.THREE_STAGE ? 2 : 1;
            } else {
                i = 0;
            }
            List<QSTileView> arrayList = getQsTileViewCache().get(i);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                EditableTileViewHolder.Companion.getQsTileViewCache().set(i, arrayList);
            }
            if (!arrayList.isEmpty()) {
                return (QSTileView) CollectionsKt__MutableCollectionsKt.removeLast(arrayList);
            }
            if (SpanSizeKt.isTwoXOne(spanSize)) {
                return (tileUiType == TileUiType.THREE_STAGE && VibratorHelper.Companion.getInstance(context).hasVibrator()) ? new OplusQSResizeableThreeStageView(context, new OplusQSThreeStageIconView(context), null) : new OplusQSResizeableTileViewTwoXOne(context, new OplusQSIconView(context, spanSize, true), null);
            }
            return SpanSizeKt.isTwoXTwo(spanSize) ? new OplusQSResizeableTileViewTwoXTwo(context, new OplusQSIconView(context, spanSize, true), null) : new OplusQSResizeableTileViewOneXOne(context, new OplusQSIconView(context, SpanCache.INSTANCE.spanSize(1, 1), false), null);
        }

        public final void recycle(QSTileView qSTileView) {
            int i;
            OplusQSResizeableTileView oplusQSResizeableTileView = qSTileView instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) qSTileView : null;
            if (oplusQSResizeableTileView != null) {
                oplusQSResizeableTileView.setLabelView(null);
            }
            IEditableView iEditableView = qSTileView instanceof IEditableView ? (IEditableView) qSTileView : null;
            if (iEditableView != null) {
                iEditableView.onRecycle();
            }
            if (qSTileView instanceof OplusQSResizeableTileViewTwoXTwo) {
                i = 3;
            } else if (qSTileView instanceof OplusQSResizeableTileViewTwoXOne) {
                i = 1;
            } else {
                i = qSTileView instanceof OplusQSResizeableThreeStageView ? 2 : 0;
            }
            List<QSTileView> arrayList = getQsTileViewCache().get(i);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                EditableTileViewHolder.Companion.getQsTileViewCache().set(i, arrayList);
            }
            arrayList.add(qSTileView);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder$onViewCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnAttachStateChangeListener {
        public /* synthetic */ EditableTileViewHolder this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            TileViewModel tileViewModel;
            QSTile qsTile;
            EditableTileViewHolder editableTileViewHolder = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Companion companion = EditableTileViewHolder.Companion;
                GridData gridData = (GridData) editableTileViewHolder.getData();
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[onViewCreated] onViewAttachedToWindow: spec=", (gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null || (qsTile = tileViewModel.getQsTile()) == null) ? null : qsTile.getTileSpec(), "EditableTileViewHolder-->", "SystemUi--QuickSettings");
            }
            this.this$0.setAddTileCallback(true);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            TileViewModel tileViewModel;
            QSTile qsTile;
            EditableTileViewHolder editableTileViewHolder = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Companion companion = EditableTileViewHolder.Companion;
                GridData gridData = (GridData) editableTileViewHolder.getData();
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[onViewCreated] onViewDetachedFromWindow spec=", (gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null || (qsTile = tileViewModel.getQsTile()) == null) ? null : qsTile.getTileSpec(), "EditableTileViewHolder-->", "SystemUi--QuickSettings");
            }
            this.this$0.setAddTileCallback(false);
        }
    }

    public EditableTileViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel, boolean z) {
        super(context, viewGroup, tileEditViewModel);
        this.isListening = super.isListening;
        this.isBeingDragged = super.isBeingDragged;
        EditableTileViewHolder$qsTileClickListener$1 editableTileViewHolder$qsTileClickListener$1 = new EditableTileViewHolder$qsTileClickListener$1();
        editableTileViewHolder$qsTileClickListener$1.$viewModel = tileEditViewModel;
        editableTileViewHolder$qsTileClickListener$1.$context = context;
        VarHandle.storeStoreFence();
        this.qsTileClickListener = editableTileViewHolder$qsTileClickListener$1;
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = SpanCache.INSTANCE.spanSize(1, 1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        deformationContainer.init(spanSize, anonymousClass1, createTileDeformationAnimationController(), z);
        setPersonalArea(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAddTileCallback(boolean z) {
        TileViewModel tileViewModel;
        GridData gridData = (GridData) getData();
        QSTile qsTile = (gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null) ? null : tileViewModel.getQsTile();
        if (this.addTileCallback == z || qsTile == null) {
            return;
        }
        if (z) {
            setListeningForItem(isListening());
            qsTile.addCallback(this);
        } else {
            setListeningForItem(false);
            qsTile.removeCallback(this);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditableTileViewHolder-->".concat("[addTileCallback] " + z + " spec=" + qsTile.getTileSpec()));
        }
        this.addTileCallback = z;
    }

    private final void setListeningForItem(boolean z) {
        TileViewModel tileViewModel;
        QSTile qsTile;
        GridData gridData = getGridData();
        if (gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null || (qsTile = tileViewModel.getQsTile()) == null) {
            return;
        }
        qsTile.setListening(this, z);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        CharSequence tileLabel;
        TileViewModel tileViewModel;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditableTileViewHolder-->".concat("[bindData] spec=" + ((TileViewModel) gridData.getData()).getQsTile().getTileSpec() + ", " + this));
        }
        GridData gridData2 = (GridData) getData();
        QSTile qsTile = (gridData2 == null || (tileViewModel = (TileViewModel) gridData2.getData()) == null) ? null : tileViewModel.getQsTile();
        QSTile qsTile2 = ((TileViewModel) gridData.getData()).getQsTile();
        boolean zAreEqual = Intrinsics.areEqual(qsTile, qsTile2);
        if (!zAreEqual) {
            setAddTileCallback(false);
        }
        super.bindData(gridData);
        if (!zAreEqual) {
            setAddTileCallback(true);
        }
        if (SpanSizeKt.isOneXOne(gridData.getSize())) {
            QSTile.State state = qsTile2.getState();
            CharSequence charSequence = state.importantLabel;
            if (charSequence == null || charSequence.length() <= 0) {
                CharSequence charSequence2 = state.label;
                tileLabel = (charSequence2 == null || charSequence2.length() <= 0) ? qsTile2.getTileLabel() : state.label;
            } else {
                tileLabel = state.importantLabel;
            }
            getDeformationContainer().getLabelView().updateText(tileLabel);
        }
        if (Intrinsics.areEqual(getDeformationContainer().getCurSize(), gridData.getSize())) {
            getCurrRealTileView().init(qsTile2);
            qsTile2.refreshState();
        } else if (!getDeformationContainer().isDragBarBePressing) {
            getDeformationContainer().setCurSize(gridData.getSize());
        }
        getDeformationContainer().setRemoveIconContentDescription(getContext().getString(2131955192, qsTile2.getTileLabel(), ""));
    }

    public IDeformationAnimationController createTileDeformationAnimationController() {
        return new TileDeformationAnimationController();
    }

    public QSTileView fetchTileView(SpanSize spanSize, boolean z) {
        return Companion.fetch(spanSize, TileUiType.DEFAULT, getContext());
    }

    public QSTileView getCurrRealTileView() {
        QSTileView currInnerView = getDeformationContainer().getCurrInnerView();
        QSTileView qSTileView = currInnerView instanceof QSTileView ? currInnerView : null;
        if (qSTileView != null) {
            return qSTileView;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("getCurrRealTileView is not valid");
        return null;
    }

    public boolean isListening() {
        return this.isListening;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onRecycled() {
        TileViewModel tileViewModel;
        QSTile qsTile;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("EditableTileViewHolder-->");
            GridData gridData = (GridData) getData();
            sb.append("[onRecycled] spec=" + ((gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null || (qsTile = tileViewModel.getQsTile()) == null) ? null : qsTile.getTileSpec()));
            Log.d("SystemUi--QuickSettings", sb.toString());
        }
        setAddTileCallback(false);
        super.onRecycled();
    }

    public void onStateChanged(QSTile.State state) {
        getCurrRealTileView().onStateChanged(state);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final void onThemeApplied(boolean z) {
        getCurrRealTileView().onThemeApplied(z);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditableTileViewHolder-->".concat("[onViewCreated] " + this));
        }
        super.onViewCreated();
        View view = ((RecyclerView.ViewHolder) this).itemView;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        view.addOnAttachStateChangeListener(anonymousClass2);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            this.isBeingDragged = z;
            super.setBeingDragged(z);
            QSTileView currRealTileView = getCurrRealTileView();
            OplusQSResizeableTileView oplusQSResizeableTileView = currRealTileView instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) currRealTileView : null;
            if (oplusQSResizeableTileView != null) {
                oplusQSResizeableTileView.setBeingDragged(z);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        if (this.isListening != z) {
            this.isListening = z;
            setListeningForItem(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        TileViewModel tileViewModel;
        QSTile qsTile;
        QSTileView currRealTileView = getCurrRealTileView();
        GridData gridData = getGridData();
        currRealTileView.onQsColorStateChanged((gridData == null || (tileViewModel = (TileViewModel) gridData.getData()) == null || (qsTile = tileViewModel.getQsTile()) == null) ? null : qsTile.getState());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateDrawable(List<SpanSize> list, boolean z) {
        getCurrRealTileView().onDrawableUpdate(list, z);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final void updateIconDrawable(boolean z) {
        getCurrRealTileView().getIcon().onIconDrawableUpdate(z);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public final void updateIconTint() {
        getCurrRealTileView().getIcon().onIconTintUpdate();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateOutline(List<SpanSize> list) {
        getCurrRealTileView().onOutlineUpdate(list);
    }
}
