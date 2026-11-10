package com.oplus.systemui.plugins.qs.customize.view;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.IPressFeedBack;
import com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BrightnessVolumeViewModel;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableBrightnessVolumeViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableMediaViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditablePluginViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableThreeStageViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.MediaViewModel;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.TileViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.TileUiType;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback;
import com.oplusos.systemui.common.adapter.BaseRecyclerAdapter;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.BaseViewHolder;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.IViewHolderCreator;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditTileAdapter extends BaseRecyclerAdapter implements ILayoutCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EditTileAdapter";
    private static RecyclerView.RecycledViewPool sharedRecyclerPool;
    private RecyclerView attachedRecyclerView;
    private CellInfo cellInfo;
    private IDeformStateChanged deformStateChangedListener;
    private final TileEditViewModel editViewModel;
    private IHostContainerAbility hostContainerAbility;
    public boolean isDragMode;
    public boolean isEditMode;
    public boolean isImmersiveOperation;
    public boolean isListening;
    public final boolean isPersonalArea;
    private IPressFeedBack pressFeedBack;
    public boolean resizeEnabled;
    public boolean tileNameEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final RecyclerView.RecycledViewPool getSharedRecyclerPool() {
            RecyclerView.RecycledViewPool recycledViewPool = EditTileAdapter.sharedRecyclerPool;
            if (recycledViewPool != null) {
                return recycledViewPool;
            }
            RecyclerView.RecycledViewPool recycledViewPool2 = new RecyclerView.RecycledViewPool();
            recycledViewPool2.setMaxRecycledViews(1, 10);
            recycledViewPool2.setMaxRecycledViews(2, 1);
            recycledViewPool2.setMaxRecycledViews(3, 1);
            recycledViewPool2.setMaxRecycledViews(4, 1);
            recycledViewPool2.setMaxRecycledViews(5, 1);
            EditTileAdapter.sharedRecyclerPool = recycledViewPool2;
            return recycledViewPool2;
        }
    }

    public EditTileAdapter(Context context, TileEditViewModel tileEditViewModel, boolean z) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$isPersonalArea = z;
        anonymousClass1.viewModel = tileEditViewModel;
        VarHandle.storeStoreFence();
        super(context, anonymousClass1);
        this.editViewModel = tileEditViewModel;
        this.isPersonalArea = z;
        this.tileNameEnabled = true;
        setHasStableIds(true);
    }

    private final List<BaseEditableViewHolder> getAllViewHolders() {
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IntIterator it = CollectionsKt__CollectionsKt.getIndices(getDataList()).iterator();
        while (it.hasNext()) {
            long itemId = getItemId(it.nextInt());
            if (itemId != -1) {
                RecyclerView.ViewHolder viewHolderFindViewHolderForItemId = recyclerView.findViewHolderForItemId(itemId);
                BaseEditableViewHolder baseEditableViewHolder = viewHolderFindViewHolderForItemId instanceof BaseEditableViewHolder ? (BaseEditableViewHolder) viewHolderFindViewHolderForItemId : null;
                if (baseEditableViewHolder != null) {
                    arrayList.add(baseEditableViewHolder);
                }
            }
        }
        List<BaseEditableViewHolder> list = CollectionsKt___CollectionsKt.toList(arrayList);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(list.size(), "[getAllViewHolders] size=", "EditTileAdapter-->", "SystemUi--QuickSettings");
        }
        return list;
    }

    private final void setListeningForViewHolder(boolean z) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).setListening(z);
        }
    }

    private final void setMaxRecycledViews(RecyclerView recyclerView) {
        recyclerView.setRecycledViewPool(Companion.getSharedRecyclerPool());
    }

    public final boolean checkEditModeAbnormal() {
        List<BaseEditableViewHolder> allViewHolders = getAllViewHolders();
        if ((allViewHolders instanceof Collection) && allViewHolders.isEmpty()) {
            return false;
        }
        for (BaseEditableViewHolder baseEditableViewHolder : allViewHolders) {
            if (baseEditableViewHolder.getDeformationContainer().isRemoveIconVisible()) {
                LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "[checkEditModeAbnormal] deformationContainer=" + baseEditableViewHolder.getDeformationContainer());
                return true;
            }
        }
        return false;
    }

    public final BaseViewHolder createAndBindViewHolder(GridData gridData, ViewGroup viewGroup) {
        BaseEditableViewHolder editableThreeStageViewHolder;
        boolean z = gridData.getData() instanceof TileViewModel;
        boolean z2 = this.isPersonalArea;
        if (z) {
            editableThreeStageViewHolder = ((TileViewModel) gridData.getData()).getUiType() == TileUiType.THREE_STAGE ? new EditableThreeStageViewHolder(viewGroup.getContext(), viewGroup, this.editViewModel) : new EditableTileViewHolder(viewGroup.getContext(), viewGroup, this.editViewModel, z2);
        } else {
            editableThreeStageViewHolder = null;
        }
        if (editableThreeStageViewHolder == null) {
            return null;
        }
        editableThreeStageViewHolder.onViewCreated();
        ((RecyclerView.ViewHolder) editableThreeStageViewHolder).itemView.setLayerType(2, null);
        editableThreeStageViewHolder.setEnableTileName(this.tileNameEnabled);
        editableThreeStageViewHolder.setEditMode(this.isEditMode);
        editableThreeStageViewHolder.setCellInfo(this.cellInfo);
        editableThreeStageViewHolder.setListening(this.isListening);
        editableThreeStageViewHolder.setDragMode(this.isDragMode);
        editableThreeStageViewHolder.setImmersiveOperation(this.isImmersiveOperation);
        editableThreeStageViewHolder.setBeingDragged(true);
        editableThreeStageViewHolder.setPersonalArea(z2);
        CellCalculatorManager.CellSize cellSize = CellCalculatorManager.Companion.getInstance(viewGroup.getContext()).getCellSize(z2);
        ((RecyclerView.ViewHolder) editableThreeStageViewHolder).itemView.getLayoutParams().width = cellSize.getCellWidth();
        ((RecyclerView.ViewHolder) editableThreeStageViewHolder).itemView.getLayoutParams().height = cellSize.getCellHeight();
        editableThreeStageViewHolder.bindData(gridData);
        return editableThreeStageViewHolder;
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("EditTileAdapter{ItemCount=" + getItemCount() + "}, RecyclerView=" + this.attachedRecyclerView + " }");
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView != null) {
            IntIterator it = CollectionsKt__CollectionsKt.getIndices(getDataList()).iterator();
            while (it.hasNext()) {
                int iNextInt = it.nextInt();
                long itemId = getItemId(iNextInt);
                if (itemId != -1) {
                    RecyclerView.ViewHolder viewHolderFindViewHolderForItemId = recyclerView.findViewHolderForItemId(itemId);
                    printWriter.println("position: " + iNextInt);
                    printWriter.println("data=" + getDataList().get(iNextInt));
                    printWriter.println("holder= " + viewHolderFindViewHolderForItemId);
                    printWriter.println("holder.itemView=" + (viewHolderFindViewHolderForItemId != null ? viewHolderFindViewHolderForItemId.itemView : null));
                    printWriter.println();
                }
            }
        }
    }

    public final CellInfo getCellInfo() {
        return this.cellInfo;
    }

    public final IDeformStateChanged getDeformStateChangedListener() {
        return this.deformStateChangedListener;
    }

    public final IHostContainerAbility getHostContainerAbility() {
        return this.hostContainerAbility;
    }

    public final long getItemId(int i) {
        GridData gridData = (GridData) CollectionsKt___CollectionsKt.getOrNull(getDataList(), i);
        if (gridData == null) {
            return -1L;
        }
        return gridData.getUniqueId();
    }

    @Override // com.oplusos.systemui.common.adapter.BaseRecyclerAdapter
    public final int getItemViewType(int i) {
        GridData gridData = (GridData) CollectionsKt___CollectionsKt.getOrNull(getDataList(), i);
        if (gridData == null) {
            return 0;
        }
        Object data = gridData.getData();
        if (data instanceof BrightnessVolumeViewModel) {
            return 2;
        }
        if (data instanceof MediaViewModel) {
            return 3;
        }
        if (data == null ? true : data instanceof QsPanelPlugin) {
            return 4;
        }
        return ((TileViewModel) gridData.getData()).getUiType() == TileUiType.THREE_STAGE ? 5 : 1;
    }

    public final IPressFeedBack getPressFeedBack() {
        return this.pressFeedBack;
    }

    public final boolean getResizeEnabled() {
        return this.resizeEnabled;
    }

    public final boolean getTileNameEnabled() {
        return this.tileNameEnabled;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.attachedRecyclerView = recyclerView;
        setMaxRecycledViews(recyclerView);
    }

    @Override // com.oplusos.systemui.common.adapter.BaseRecyclerAdapter
    public void onBindViewHolder(BaseEditableViewHolder baseEditableViewHolder, int i) {
        baseEditableViewHolder.setEnableTileName(this.tileNameEnabled);
        baseEditableViewHolder.setEditMode(this.isEditMode);
        baseEditableViewHolder.setEnableResize(this.resizeEnabled);
        baseEditableViewHolder.setCellInfo(this.cellInfo);
        baseEditableViewHolder.setListening(this.isListening);
        baseEditableViewHolder.setPressFeedBack(this.pressFeedBack);
        baseEditableViewHolder.setDragMode(this.isDragMode);
        baseEditableViewHolder.setImmersiveOperation(this.isImmersiveOperation);
        baseEditableViewHolder.setDeformStateChangeListener(this.deformStateChangedListener);
        baseEditableViewHolder.setHostContainerAbility(this.hostContainerAbility);
        baseEditableViewHolder.setPersonalArea(this.isPersonalArea);
        super.onBindViewHolder((BaseViewHolder) baseEditableViewHolder, i);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public void onCellInfoChanged(CellInfo cellInfo) {
        setCellInfo(cellInfo);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public final void onCellNumChanged(int i, int i2) {
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.attachedRecyclerView = null;
    }

    public final void onThemeApplied(boolean z) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).onThemeApplied(z);
        }
    }

    public final void setCellInfo(CellInfo cellInfo) {
        if (Intrinsics.areEqual(this.cellInfo, cellInfo)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setCellInfo] " + cellInfo, null, 8);
        this.cellInfo = cellInfo;
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).setCellInfo(cellInfo);
        }
    }

    public final void setDeformStateChangedListener(IDeformStateChanged iDeformStateChanged) {
        if (Intrinsics.areEqual(this.deformStateChangedListener, iDeformStateChanged)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setResizeCallBack] " + iDeformStateChanged, null, 8);
        this.deformStateChangedListener = iDeformStateChanged;
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).setDeformStateChangeListener(iDeformStateChanged);
        }
    }

    public final void setDragMode(boolean z) {
        if (this.isDragMode != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setIsDragMode] ", z), null, 8);
            this.isDragMode = z;
            Iterator<T> it = getAllViewHolders().iterator();
            while (it.hasNext()) {
                ((BaseEditableViewHolder) it.next()).setDragMode(z);
            }
        }
    }

    public final void setHostContainerAbility(IHostContainerAbility iHostContainerAbility) {
        if (Intrinsics.areEqual(this.hostContainerAbility, iHostContainerAbility)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setHostContainerAbility] " + iHostContainerAbility, null, 8);
        this.hostContainerAbility = iHostContainerAbility;
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).setHostContainerAbility(iHostContainerAbility);
        }
    }

    public final void setImmersiveOperation(boolean z) {
        if (this.isImmersiveOperation != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[isImmersiveOperation] ", "EditTileAdapter-->", "SystemUi--QuickSettings", z);
            }
            this.isImmersiveOperation = z;
            Iterator<T> it = getAllViewHolders().iterator();
            while (it.hasNext()) {
                ((BaseEditableViewHolder) it.next()).setImmersiveOperation(z);
            }
        }
    }

    public final void setListening(boolean z) {
        if (this.isListening != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setIsListening] ", z), null, 8);
            this.isListening = z;
            setListeningForViewHolder(z);
        }
    }

    public final void setPressFeedBack(IPressFeedBack iPressFeedBack) {
        if (Intrinsics.areEqual(this.pressFeedBack, iPressFeedBack)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setPressFeedBack] " + iPressFeedBack, null, 8);
        this.pressFeedBack = iPressFeedBack;
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).setPressFeedBack(iPressFeedBack);
        }
    }

    public final void setResizeEnabled(boolean z) {
        if (this.resizeEnabled != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setResizeEnabled] ", z), null, 8);
            this.resizeEnabled = z;
            Iterator<T> it = getAllViewHolders().iterator();
            while (it.hasNext()) {
                ((BaseEditableViewHolder) it.next()).setEnableResize(z);
            }
        }
    }

    public final void setTileNameEnabled(boolean z) {
        if (this.tileNameEnabled != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setTileNameEnabled] ", z), null, 8);
            this.tileNameEnabled = z;
            Iterator<T> it = getAllViewHolders().iterator();
            while (it.hasNext()) {
                ((BaseEditableViewHolder) it.next()).setEnableTileName(z);
            }
        }
    }

    public final void submitList(List<? extends GridData> list, List<Loc> list2, List<Loc> list3) {
        EditTileAdapter$submitList$diffResult$1 editTileAdapter$submitList$diffResult$1 = new EditTileAdapter$submitList$diffResult$1();
        editTileAdapter$submitList$diffResult$1.this$0 = this;
        editTileAdapter$submitList$diffResult$1.$newData = list;
        editTileAdapter$submitList$diffResult$1.$oldLocList = list2;
        editTileAdapter$submitList$diffResult$1.$newLocList = list3;
        VarHandle.storeStoreFence();
        DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(editTileAdapter$submitList$diffResult$1);
        setDataList(list);
        diffResultCalculateDiff.dispatchUpdatesTo(this);
    }

    public final void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).updateColor(colorType);
        }
    }

    public final void updateDrawable(List<SpanSize> list, boolean z) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).updateDrawable(list, z);
        }
    }

    public final void updateEditMode$1(boolean z) {
        if (this.isEditMode != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[updateEditMode] ", z), null, 8);
            this.isEditMode = z;
            for (BaseEditableViewHolder baseEditableViewHolder : getAllViewHolders()) {
                if (baseEditableViewHolder instanceof EditablePluginViewHolder) {
                    EditablePluginViewHolder editablePluginViewHolder = (EditablePluginViewHolder) baseEditableViewHolder;
                    editablePluginViewHolder.updateEditModeWithAnimation = true;
                    editablePluginViewHolder.setEditMode(z);
                    editablePluginViewHolder.updateEditModeWithAnimation = false;
                } else {
                    baseEditableViewHolder.setEditMode(z);
                }
            }
        }
    }

    public final void updateIconDrawable(boolean z) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).updateIconDrawable(z);
        }
    }

    public final void updateIconTint() {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).updateIconTint();
        }
    }

    public final void updateOutline(List<SpanSize> list) {
        Iterator<T> it = getAllViewHolders().iterator();
        while (it.hasNext()) {
            ((BaseEditableViewHolder) it.next()).updateOutline(list);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IViewHolderCreator {
        public /* synthetic */ boolean $isPersonalArea;
        public TileEditViewModel viewModel;

        @Override // com.oplusos.systemui.common.viewholder.IViewHolderCreator
        public final BaseViewHolder create(Context context, int i, ViewGroup viewGroup) {
            if (i == 2) {
                return new EditableBrightnessVolumeViewHolder(context, viewGroup, getViewModel());
            }
            if (i == 3) {
                return new EditableMediaViewHolder(context, viewGroup, getViewModel());
            }
            if (i != 4) {
                return i != 5 ? new EditableTileViewHolder(context, viewGroup, getViewModel(), this.$isPersonalArea) : new EditableThreeStageViewHolder(context, viewGroup, getViewModel());
            }
            return new EditablePluginViewHolder(context, viewGroup, getViewModel());
        }

        @Override // com.oplusos.systemui.common.viewholder.IViewHolderCreator
        public TileEditViewModel getViewModel() {
            return this.viewModel;
        }
    }
}
