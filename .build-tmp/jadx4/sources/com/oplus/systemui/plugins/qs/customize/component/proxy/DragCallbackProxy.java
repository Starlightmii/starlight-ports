package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.graphics.Rect;
import android.util.Log;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.CursorUtil$;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.OplusQSRootViewComponent;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.CopyViewProxy;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.ISwapOrMove;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.CrossDragDataDelegate;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.OutputDragData;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.CrossRegionDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.ItemViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper$notifyDragStart$1$1;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EmptyViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.TileViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileDataViewModelMapper;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.qs.base.util.SeparateDragTileData;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragCallbackProxy implements ItemDragCallBack {
    public static final Companion Companion = new Companion();
    private static final String _TAG = "DragCallbackProxy";
    public static boolean isDragDisable;
    public static boolean isDragMode;
    private final String TAG;
    private final Lazy customLayoutManager$delegate;
    private final Rect disableRect;
    private final EditModeSwitchAnimationManager editModeSwitchAnimationManager;
    private final IDragHostAbility hostAbility;
    private final RecyclerView hostRecyclerView;
    private final IntentTarget intentTarget;
    private final Lazy itemViewDragHelper$delegate;
    private final IComponentHostAbility rootViewHostAbility;
    private ISwapOrMove swapOrMove;
    private final String tagPrefix;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static ItemViewDragHelper $r8$lambda$22GYkkOb5yDoSngHNbkNwC8cIp4(DragCallbackProxy dragCallbackProxy) {
        return new ItemViewDragHelper(dragCallbackProxy.hostRecyclerView);
    }

    /* JADX INFO: renamed from: $r8$lambda$7K6NvwNyp-r_qF-kFZqoPZYmT3A, reason: not valid java name */
    public static StaggeredPagerLayoutManager m144$r8$lambda$7K6NvwNypr_qFkFZqoPZYmT3A(DragCallbackProxy dragCallbackProxy) {
        return (StaggeredPagerLayoutManager) dragCallbackProxy.hostRecyclerView.getLayoutManager();
    }

    public static Unit $r8$lambda$z858DC86rL7K2_vvkvDBIJivul4(OutputDragData outputDragData, DragCallbackProxy dragCallbackProxy, Ref.BooleanRef booleanRef, boolean z) {
        if (z) {
            CrossDragDataDelegate crossDataDelegate = outputDragData.getCrossDataDelegate();
            DragCallbackProxy$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new DragCallbackProxy$.ExternalSyntheticLambda7();
            externalSyntheticLambda7.f$0 = dragCallbackProxy;
            externalSyntheticLambda7.f$1 = outputDragData;
            VarHandle.storeStoreFence();
            crossDataDelegate.updateDragData(externalSyntheticLambda7);
        } else {
            String str = dragCallbackProxy.TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("[handleDragToOtherArea] Failed to move tile, isFirstMoveTile=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), booleanRef.element);
            }
        }
        return Unit.INSTANCE;
    }

    public DragCallbackProxy(RecyclerView recyclerView, IntentTarget intentTarget, EditModeSwitchAnimationManager editModeSwitchAnimationManager, IComponentHostAbility iComponentHostAbility, IDragHostAbility iDragHostAbility, String str) {
        this.hostRecyclerView = recyclerView;
        this.intentTarget = intentTarget;
        this.editModeSwitchAnimationManager = editModeSwitchAnimationManager;
        this.rootViewHostAbility = iComponentHostAbility;
        this.hostAbility = iDragHostAbility;
        this.tagPrefix = str;
        this.TAG = _TAG.concat(str != null ? "_".concat(str) : "");
        DragCallbackProxy$$ExternalSyntheticLambda2 dragCallbackProxy$$ExternalSyntheticLambda2 = new DragCallbackProxy$$ExternalSyntheticLambda2(0);
        dragCallbackProxy$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.customLayoutManager$delegate = LazyKt__LazyJVMKt.lazy(dragCallbackProxy$$ExternalSyntheticLambda2);
        DragCallbackProxy$$ExternalSyntheticLambda2 dragCallbackProxy$$ExternalSyntheticLambda3 = new DragCallbackProxy$$ExternalSyntheticLambda2(1);
        dragCallbackProxy$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.itemViewDragHelper$delegate = LazyKt__LazyJVMKt.lazy(dragCallbackProxy$$ExternalSyntheticLambda3);
        this.disableRect = new Rect();
    }

    private final StaggeredPagerLayoutManager getCustomLayoutManager() {
        return (StaggeredPagerLayoutManager) this.customLayoutManager$delegate.getValue();
    }

    private final ItemViewDragHelper getItemViewDragHelper() {
        return (ItemViewDragHelper) this.itemViewDragHelper$delegate.getValue();
    }

    public final void enterDragDisableMode(OutputDragData outputDragData) {
        RecyclerView.ViewHolder holder;
        if (isDragDisable) {
            return;
        }
        this.editModeSwitchAnimationManager.enterDragDisableMode();
        CopyViewProxy copyViewProxy = outputDragData.getCrossDataDelegate().getCrossDragData().getCopyViewProxy();
        if (copyViewProxy == null || (holder = copyViewProxy.getHostViewHolder()) == null) {
            holder = getHolder((GridData) outputDragData.getDragItemInfo());
        }
        if (holder instanceof BaseEditableViewHolder) {
            ((BaseEditableViewHolder) holder).setDragDisable(true);
        }
        IComponentHostAbility iComponentHostAbility = this.rootViewHostAbility;
        if (iComponentHostAbility != null) {
            ((OplusQSRootViewComponent) iComponentHostAbility).enterDragDisableMode();
        }
        isDragDisable = true;
    }

    public final void enterDragMode() {
        if (isDragMode) {
            return;
        }
        this.editModeSwitchAnimationManager.enterDragMode();
        IComponentHostAbility iComponentHostAbility = this.rootViewHostAbility;
        if (iComponentHostAbility != null) {
            iComponentHostAbility.enterDragMode(true, null);
        }
        isDragMode = true;
    }

    public final void exitDragDisableMode(OutputDragData outputDragData) {
        RecyclerView.ViewHolder holder;
        if (isDragDisable) {
            this.editModeSwitchAnimationManager.exitDragDisableMode();
            CopyViewProxy copyViewProxy = outputDragData.getCrossDataDelegate().getCrossDragData().getCopyViewProxy();
            if (copyViewProxy == null || (holder = copyViewProxy.getHostViewHolder()) == null) {
                holder = getHolder((GridData) outputDragData.getDragItemInfo());
            }
            if (holder instanceof BaseEditableViewHolder) {
                ((BaseEditableViewHolder) holder).setDragDisable(false);
            }
            IComponentHostAbility iComponentHostAbility = this.rootViewHostAbility;
            if (iComponentHostAbility != null) {
                ((OplusQSRootViewComponent) iComponentHostAbility).exitDragDisableMode();
            }
            isDragDisable = false;
        }
    }

    public final void exitDragMode() {
        if (isDragMode) {
            this.editModeSwitchAnimationManager.exitDragMode();
            IComponentHostAbility iComponentHostAbility = this.rootViewHostAbility;
            if (iComponentHostAbility != null) {
                ((OplusQSRootViewComponent) iComponentHostAbility).exitDragMode();
            }
            isDragMode = false;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public long getDragWaitingTime() {
        return super.getDragWaitingTime();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public RecyclerView.ViewHolder getHolder(GridData gridData) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.hostRecyclerView.findViewHolderForAdapterPosition(this.hostAbility.findIndexOf(gridData));
        if (viewHolderFindViewHolderForAdapterPosition instanceof EmptyViewHolder) {
            return null;
        }
        return viewHolderFindViewHolderForAdapterPosition;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public IntentTarget getIntentTarget() {
        return this.intentTarget;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public boolean isFullEmpty(OutputDragData outputDragData, float f, float f2) {
        IntentTarget intentTarget = this.intentTarget;
        IntentTarget intentTarget2 = IntentTarget.PERSONAL_LIST;
        if (intentTarget != intentTarget2) {
            return false;
        }
        if (outputDragData.getFromTarget() == intentTarget2) {
            return getItemViewDragHelper().isFullEmpty(f, f2, getCustomLayoutManager());
        }
        Loc touchUnderViewPositionLoc = getItemViewDragHelper().getTouchUnderViewPositionLoc(getCustomLayoutManager(), f, f2, ((GridData) outputDragData.getDragItemInfo()).getSize(), (IntentTarget) null);
        if (touchUnderViewPositionLoc != null) {
            return getCustomLayoutManager().isEmptyCell(touchUnderViewPositionLoc);
        }
        return false;
    }

    public final int locToPosition(Loc loc) {
        StaggeredPagerLayoutManager.LayoutConfig layoutConfig = getCustomLayoutManager().getLayoutConfig();
        return loc.getStartCol() + (layoutConfig.getColCount() * loc.getStartRow()) + (loc.getPageIndex() * layoutConfig.getRowCount() * layoutConfig.getColCount());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public void onDragEnd(OutputDragData outputDragData) {
        exitDragMode();
        if (outputDragData.getCrossDataDelegate().getCrossDragData().getCurrentDragIntent() == this.intentTarget) {
            this.disableRect.setEmpty();
            exitDragDisableMode(outputDragData);
        }
        int iFindIndexOf = this.hostAbility.findIndexOf((GridData) outputDragData.getDragItemInfo());
        if (-1 != iFindIndexOf) {
            this.hostAbility.sendIntent(TileEditIntent.DragEndIntent.INSTANCE);
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.hostRecyclerView.findViewHolderForAdapterPosition(iFindIndexOf);
            if (viewHolderFindViewHolderForAdapterPosition instanceof BaseEditableViewHolder) {
                ((BaseEditableViewHolder) viewHolderFindViewHolderForAdapterPosition).setBeingDragged(false);
            }
        }
        CopyViewProxy copyViewProxy = outputDragData.getCrossDataDelegate().getCrossDragData().getCopyViewProxy();
        RecyclerView.ViewHolder hostViewHolder = copyViewProxy != null ? copyViewProxy.getHostViewHolder() : null;
        BaseEditableViewHolder baseEditableViewHolder = hostViewHolder instanceof BaseEditableViewHolder ? (BaseEditableViewHolder) hostViewHolder : null;
        if (baseEditableViewHolder != null) {
            baseEditableViewHolder.setBeingDragged(false);
        }
        SeparateDragTileData separateDragTileData = SeparateQSStatisicUtil.getSeparateDragTileData();
        if (separateDragTileData.getEndAreaIsA() == (getIntentTarget() == IntentTarget.PERSONAL_LIST)) {
            if (iFindIndexOf == -1) {
                separateDragTileData.setEndAreaIsA(separateDragTileData.getStartAreaIsA());
                separateDragTileData.setQsEndDragLoc(separateDragTileData.getQsStartDragLoc());
            } else {
                separateDragTileData.setQsEndDragLoc(getCustomLayoutManager().getItemLoc(iFindIndexOf).toString());
            }
        }
        getItemViewDragHelper().onDragEnd(getCustomLayoutManager(), iFindIndexOf);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public void onDragLocation(OutputDragData outputDragData, float f, float f2) {
        if (outputDragData.getFromTarget() == this.intentTarget && !((GridData) outputDragData.getDragItemInfo()).getAllValidSize().contains(SpanCache.INSTANCE.spanSize(1, 1))) {
            if (getItemViewDragHelper().isNeedDisableDrag(f2)) {
                enterDragDisableMode(outputDragData);
            } else {
                exitDragDisableMode(outputDragData);
            }
        }
        if (!this.disableRect.isEmpty() && this.intentTarget == IntentTarget.PERSONAL_LIST && !getItemViewDragHelper().isNeedDisableDrag(f, f2, this.disableRect)) {
            this.disableRect.setEmpty();
            exitDragDisableMode(outputDragData);
        }
        getItemViewDragHelper().onDragLocation(f, f2, getCustomLayoutManager());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public void onDragStart(RecyclerView recyclerView, float f, float f2, float f3, float f4, OutputDragData outputDragData) {
        enterDragMode();
        if (((GridData) outputDragData.getDragItemInfo()).getData() != null && (outputDragData.getDragItemInfo() instanceof GridData)) {
            SeparateQSStatisicUtil.getEditTitleData().setType(TileDataViewModelMapper.Companion.getSpecFromGridData((GridData) outputDragData.getDragItemInfo()));
        }
        int iFindIndexOf = this.hostAbility.findIndexOf((GridData) outputDragData.getDragItemInfo());
        if (-1 != iFindIndexOf) {
            this.hostAbility.sendIntent(new TileEditIntent.DragStartIntent(iFindIndexOf));
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.hostRecyclerView.findViewHolderForAdapterPosition(iFindIndexOf);
            if (viewHolderFindViewHolderForAdapterPosition instanceof BaseEditableViewHolder) {
                ((BaseEditableViewHolder) viewHolderFindViewHolderForAdapterPosition).setBeingDragged(true);
            }
            if (outputDragData.getFromTarget() == IntentTarget.PERSONAL_LIST) {
                Loc itemLoc = getCustomLayoutManager().getItemLoc(iFindIndexOf);
                LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "onDragStart, originLocInPersonalArea: " + itemLoc, null, 8);
                CrossDragDataDelegate crossDataDelegate = outputDragData.getCrossDataDelegate();
                DragCallbackProxy$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new DragCallbackProxy$.ExternalSyntheticLambda1(0);
                externalSyntheticLambda1.f$0 = itemLoc;
                VarHandle.storeStoreFence();
                crossDataDelegate.updateDragData(externalSyntheticLambda1);
            }
        }
        getItemViewDragHelper().onDragStart(recyclerView.getContext(), outputDragData.getFromTarget() == IntentTarget.PERSONAL_LIST, getCustomLayoutManager(), f, f2, f3, f4, iFindIndexOf, outputDragData.getDragViewWidth(), outputDragData.getDragViewHeight());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public void onMove(RecyclerView recyclerView, OutputDragData outputDragData, float f, float f2, boolean z) {
        float f3;
        IntentTarget intentTarget;
        boolean z2;
        float f4;
        float f5;
        IntentTarget intentTarget2;
        Loc originLoc;
        String spec;
        String spec2;
        String spec3;
        String spec4;
        String spec5;
        String spec6;
        String spec7;
        String spec8;
        String spec9;
        String spec10;
        String spec11;
        String spec12;
        String spec13;
        boolean z3 = outputDragData.getCrossDataDelegate().getCrossDragData().isRegionFirstCrossed;
        IntentTarget intentTarget3 = getIntentTarget();
        IntentTarget fromTarget = outputDragData.getFromTarget();
        String str = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("onMove, isDropOnEmptyLoc: ", ", isRegionCrossed: ", " fromTarget=", z, z3);
            sbM2.append(fromTarget);
            sbM2.append(" toTarget=");
            sbM2.append(intentTarget3);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        IntentTarget intentTarget4 = IntentTarget.OTHER_LIST;
        if (intentTarget3 == intentTarget4 && z3) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            if (outputDragData.getFromTarget() == intentTarget4) {
                GridData swapDataInPersonalArea = outputDragData.getCrossDataDelegate().getCrossDragData().getSwapDataInPersonalArea();
                if (swapDataInPersonalArea != null) {
                    String str2 = this.TAG;
                    Object data = ((GridData) outputDragData.getDragItemInfo()).getData();
                    TileViewModel tileViewModel = data instanceof TileViewModel ? (TileViewModel) data : null;
                    if (tileViewModel == null || (spec10 = tileViewModel.getSpec()) == null) {
                        spec10 = "";
                    }
                    Object data2 = swapDataInPersonalArea.getData();
                    TileViewModel tileViewModel2 = data2 instanceof TileViewModel ? (TileViewModel) data2 : null;
                    if (tileViewModel2 == null || (spec11 = tileViewModel2.getSpec()) == null) {
                        spec11 = "";
                    }
                    LogUtil.i$default(LogUtil.TAG_QS, str2, CursorUtil$.ExternalSyntheticOutline0.m("handleDragToOtherArea call swap title, ", spec10, " -> ", spec11), null, 8);
                    ISwapOrMove iSwapOrMove = this.swapOrMove;
                    if (iSwapOrMove != null) {
                        ((RecyclerViewDragHelper$notifyDragStart$1$1) iSwapOrMove).onBeforeSwap(CollectionsKt__CollectionsJVMKt.listOf(swapDataInPersonalArea));
                    }
                    IDragHostAbility iDragHostAbility = this.hostAbility;
                    Object data3 = ((GridData) outputDragData.getDragItemInfo()).getData();
                    TileViewModel tileViewModel3 = data3 instanceof TileViewModel ? (TileViewModel) data3 : null;
                    if (tileViewModel3 == null || (spec12 = tileViewModel3.getSpec()) == null) {
                        spec12 = "";
                    }
                    Object data4 = swapDataInPersonalArea.getData();
                    TileViewModel tileViewModel4 = data4 instanceof TileViewModel ? (TileViewModel) data4 : null;
                    if (tileViewModel4 == null || (spec13 = tileViewModel4.getSpec()) == null) {
                        spec13 = "";
                    }
                    iDragHostAbility.sendIntent(new TileEditIntent.SwapTile(spec12, spec13));
                }
                booleanRef.element = false;
            }
            Loc touchUnderViewPositionLoc = getItemViewDragHelper().getTouchUnderViewPositionLoc(getCustomLayoutManager(), f, f2, ((GridData) outputDragData.getDragItemInfo()).getSize(), intentTarget4);
            int iLocToPosition = touchUnderViewPositionLoc != null ? locToPosition(touchUnderViewPositionLoc) : -1;
            if (iLocToPosition == -1 || !CrossRegionDragHelper.isLegalSizeForTarget((GridData) outputDragData.getDragItemInfo(), getIntentTarget())) {
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iLocToPosition, "onRegionCrossed, draggingIndex: "), null, 8);
            this.editModeSwitchAnimationManager.allowLayoutForOnce();
            IDragHostAbility iDragHostAbility2 = this.hostAbility;
            Object data5 = ((GridData) outputDragData.getDragItemInfo()).getData();
            TileViewModel tileViewModel5 = data5 instanceof TileViewModel ? (TileViewModel) data5 : null;
            String str3 = (tileViewModel5 == null || (spec9 = tileViewModel5.getSpec()) == null) ? "" : spec9;
            DragCallbackProxy$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new DragCallbackProxy$.ExternalSyntheticLambda5(0);
            externalSyntheticLambda5.f$0 = outputDragData;
            externalSyntheticLambda5.f$1 = this;
            externalSyntheticLambda5.f$2 = booleanRef;
            VarHandle.storeStoreFence();
            iDragHostAbility2.sendIntent(new TileEditIntent.MoveTile(str3, iLocToPosition, null, externalSyntheticLambda5, booleanRef.element, 4));
            return;
        }
        IntentTarget intentTarget5 = IntentTarget.PERSONAL_LIST;
        if (intentTarget3 != intentTarget5) {
            f3 = f;
            intentTarget = intentTarget5;
            z2 = true;
            f4 = f2;
        } else {
            if (fromTarget != intentTarget5) {
                Loc touchUnderViewPositionLoc2 = getItemViewDragHelper().getTouchUnderViewPositionLoc(getCustomLayoutManager(), f, f2, ((GridData) outputDragData.getDragItemInfo()).getSize(), intentTarget5);
                LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleDragToPersonalArea, isDropOnEmptyLoc: " + z + ", targetLoc: " + touchUnderViewPositionLoc2, null, 8);
                if (touchUnderViewPositionLoc2 != null && z) {
                    LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleDragToPersonalArea, drop on empty location", null, 8);
                    GridData swapDataInPersonalArea2 = outputDragData.getCrossDataDelegate().getCrossDragData().getSwapDataInPersonalArea();
                    if (swapDataInPersonalArea2 != null) {
                        ISwapOrMove iSwapOrMove2 = this.swapOrMove;
                        if (iSwapOrMove2 != null) {
                            ((RecyclerViewDragHelper$notifyDragStart$1$1) iSwapOrMove2).onBeforeSwap(CollectionsKt__CollectionsJVMKt.listOf(swapDataInPersonalArea2));
                        }
                        IDragHostAbility iDragHostAbility3 = this.hostAbility;
                        Object data6 = ((GridData) outputDragData.getDragItemInfo()).getData();
                        TileViewModel tileViewModel6 = data6 instanceof TileViewModel ? (TileViewModel) data6 : null;
                        if (tileViewModel6 == null || (spec7 = tileViewModel6.getSpec()) == null) {
                            spec7 = "";
                        }
                        Object data7 = swapDataInPersonalArea2.getData();
                        TileViewModel tileViewModel7 = data7 instanceof TileViewModel ? (TileViewModel) data7 : null;
                        if (tileViewModel7 == null || (spec8 = tileViewModel7.getSpec()) == null) {
                            spec8 = "";
                        }
                        iDragHostAbility3.sendIntent(new TileEditIntent.SwapTile(spec7, spec8));
                    }
                    LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "checkShouldMoveTile, locInPersonalArea: " + outputDragData.getCrossDataDelegate().getCrossDragData().getLocInPersonalArea() + ", call move title", null, 8);
                    IDragHostAbility iDragHostAbility4 = this.hostAbility;
                    Object data8 = ((GridData) outputDragData.getDragItemInfo()).getData();
                    TileViewModel tileViewModel8 = data8 instanceof TileViewModel ? (TileViewModel) data8 : null;
                    iDragHostAbility4.sendIntent(new TileEditIntent.MoveTile((tileViewModel8 == null || (spec6 = tileViewModel8.getSpec()) == null) ? "" : spec6, 0, touchUnderViewPositionLoc2, null, false, 26));
                    return;
                }
                if (touchUnderViewPositionLoc2 != null) {
                    Pair dataFromDragLocation = CrossRegionDragHelper.getDataFromDragLocation(this.hostRecyclerView, getCustomLayoutManager(), outputDragData, f, f2);
                    if (dataFromDragLocation == null) {
                        this.disableRect.setEmpty();
                        exitDragDisableMode(outputDragData);
                        return;
                    }
                    Loc loc = (Loc) dataFromDragLocation.component1();
                    GridData gridData = (GridData) dataFromDragLocation.component2();
                    if (!CrossRegionDragHelper.isLegalSizeForTarget(gridData, outputDragData.getFromTarget())) {
                        getItemViewDragHelper().calculateTargetViewRect(loc, gridData.getSize(), getCustomLayoutManager(), this.disableRect);
                        enterDragDisableMode(outputDragData);
                        return;
                    }
                    LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "call swap tile, " + gridData.getData() + " -> " + ((GridData) outputDragData.getDragItemInfo()).getData() + ",crossDragData: " + outputDragData.getCrossDataDelegate().getCrossDragData(), null, 8);
                    Object data9 = gridData.getData();
                    TileViewModel tileViewModel9 = data9 instanceof TileViewModel ? (TileViewModel) data9 : null;
                    if ((tileViewModel9 != null ? tileViewModel9.getSpec() : null) != null) {
                        Object data10 = gridData.getData();
                        TileViewModel tileViewModel10 = data10 instanceof TileViewModel ? (TileViewModel) data10 : null;
                        String spec14 = tileViewModel10 != null ? tileViewModel10.getSpec() : null;
                        Object data11 = ((GridData) outputDragData.getDragItemInfo()).getData();
                        TileViewModel tileViewModel11 = data11 instanceof TileViewModel ? (TileViewModel) data11 : null;
                        if (Intrinsics.areEqual(spec14, tileViewModel11 != null ? tileViewModel11.getSpec() : null)) {
                            String str4 = this.TAG;
                            Object data12 = gridData.getData();
                            LogUtil.i$default(LogUtil.TAG_QS, str4, (data12 instanceof TileViewModel ? (TileViewModel) data12 : null) + " has been swapped!", null, 8);
                            this.disableRect.setEmpty();
                            exitDragDisableMode(outputDragData);
                            return;
                        }
                    }
                    GridData swapDataInPersonalArea3 = outputDragData.getCrossDataDelegate().getCrossDragData().getSwapDataInPersonalArea();
                    if (swapDataInPersonalArea3 != null) {
                        ISwapOrMove iSwapOrMove3 = this.swapOrMove;
                        if (iSwapOrMove3 != null) {
                            ((RecyclerViewDragHelper$notifyDragStart$1$1) iSwapOrMove3).onBeforeSwap(CollectionsKt__CollectionsKt.listOf(new GridData[]{gridData, swapDataInPersonalArea3}));
                        }
                        IDragHostAbility iDragHostAbility5 = this.hostAbility;
                        Object data13 = ((GridData) outputDragData.getDragItemInfo()).getData();
                        TileViewModel tileViewModel12 = data13 instanceof TileViewModel ? (TileViewModel) data13 : null;
                        if (tileViewModel12 == null || (spec4 = tileViewModel12.getSpec()) == null) {
                            spec4 = "";
                        }
                        Object data14 = swapDataInPersonalArea3.getData();
                        TileViewModel tileViewModel13 = data14 instanceof TileViewModel ? (TileViewModel) data14 : null;
                        if (tileViewModel13 == null || (spec5 = tileViewModel13.getSpec()) == null) {
                            spec5 = "";
                        }
                        iDragHostAbility5.sendIntent(new TileEditIntent.SwapTile(spec4, spec5));
                    } else {
                        LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "call onBeforeSwap " + gridData, null, 8);
                        ISwapOrMove iSwapOrMove4 = this.swapOrMove;
                        if (iSwapOrMove4 != null) {
                            ((RecyclerViewDragHelper$notifyDragStart$1$1) iSwapOrMove4).onBeforeSwap(CollectionsKt__CollectionsJVMKt.listOf(gridData));
                        }
                    }
                    getItemViewDragHelper().onMove(this.hostAbility.findIndexOf(gridData), getCustomLayoutManager());
                    IDragHostAbility iDragHostAbility6 = this.hostAbility;
                    Object data15 = gridData.getData();
                    TileViewModel tileViewModel14 = data15 instanceof TileViewModel ? (TileViewModel) data15 : null;
                    if (tileViewModel14 == null || (spec2 = tileViewModel14.getSpec()) == null) {
                        spec2 = "";
                    }
                    Object data16 = ((GridData) outputDragData.getDragItemInfo()).getData();
                    TileViewModel tileViewModel15 = data16 instanceof TileViewModel ? (TileViewModel) data16 : null;
                    if (tileViewModel15 == null || (spec3 = tileViewModel15.getSpec()) == null) {
                        spec3 = "";
                    }
                    iDragHostAbility6.sendIntent(new TileEditIntent.SwapTile(spec2, spec3));
                    CrossDragDataDelegate crossDataDelegate = outputDragData.getCrossDataDelegate();
                    DragCallbackProxy$.ExternalSyntheticLambda5 externalSyntheticLambda6 = new DragCallbackProxy$.ExternalSyntheticLambda5(1);
                    externalSyntheticLambda6.f$1 = this;
                    externalSyntheticLambda6.f$0 = touchUnderViewPositionLoc2;
                    externalSyntheticLambda6.f$2 = gridData;
                    VarHandle.storeStoreFence();
                    crossDataDelegate.updateDragData(externalSyntheticLambda6);
                    LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "targetLoc: " + touchUnderViewPositionLoc2 + ",  data: " + dataFromDragLocation + ", crossData: " + outputDragData.getCrossDataDelegate().getCrossDragData(), null, 8);
                    this.disableRect.setEmpty();
                    exitDragDisableMode(outputDragData);
                    return;
                }
                return;
            }
            intentTarget = intentTarget5;
            z2 = true;
            f3 = f;
            f4 = f2;
            if (z3) {
                String str5 = this.TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(str5, LogUtil.SEPARATOR);
                    sbM3.append("handleDragBackToPersonalArea, crossData: " + outputDragData.getCrossDataDelegate().getCrossDragData());
                    Log.d("SystemUi--QuickSettings", sbM3.toString());
                }
                if (outputDragData.getCrossDataDelegate().getCrossDragData().getInsertSpecInOtherArea() != null && (originLoc = outputDragData.getCrossDataDelegate().getCrossDragData().getOriginLoc()) != null) {
                    IDragHostAbility iDragHostAbility7 = this.hostAbility;
                    Object data17 = ((GridData) outputDragData.getDragItemInfo()).getData();
                    TileViewModel tileViewModel16 = data17 instanceof TileViewModel ? (TileViewModel) data17 : null;
                    iDragHostAbility7.sendIntent(new TileEditIntent.MoveTile((tileViewModel16 == null || (spec = tileViewModel16.getSpec()) == null) ? "" : spec, 0, originLoc, null, false, 26));
                }
                CrossDragDataDelegate crossDataDelegate2 = outputDragData.getCrossDataDelegate();
                DragCallbackProxy$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new DragCallbackProxy$.ExternalSyntheticLambda1(1);
                externalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                crossDataDelegate2.updateDragData(externalSyntheticLambda1);
            }
        }
        boolean z4 = (intentTarget3 != intentTarget4 || fromTarget == intentTarget4) ? false : z2;
        int iFindIndexOf = this.hostAbility.findIndexOf((GridData) outputDragData.getDragItemInfo());
        ItemViewDragHelper itemViewDragHelper = getItemViewDragHelper();
        StaggeredPagerLayoutManager customLayoutManager = getCustomLayoutManager();
        SpanSize size = ((GridData) outputDragData.getDragItemInfo()).getSize();
        if (z4) {
            float f6 = f4;
            intentTarget2 = intentTarget4;
            f5 = f6;
        } else {
            f5 = f4;
            intentTarget2 = null;
        }
        Loc touchUnderViewPositionLoc3 = itemViewDragHelper.getTouchUnderViewPositionLoc(customLayoutManager, f3, f5, size, intentTarget2);
        String str6 = this.TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM4 = Bundler$.ExternalSyntheticOutline0.m(str6, LogUtil.SEPARATOR);
            sbM4.append("onMove insetPosition: " + iFindIndexOf + " endLoc:" + touchUnderViewPositionLoc3 + " isToOther=" + z4);
            Log.d("SystemUi--QuickSettings", sbM4.toString());
        }
        if (touchUnderViewPositionLoc3 != null) {
            this.editModeSwitchAnimationManager.allowLayoutForOnce();
            if (-1 != iFindIndexOf) {
                this.hostAbility.sendIntent(new TileEditIntent.DragTileIntent(iFindIndexOf, touchUnderViewPositionLoc3, intentTarget3 != intentTarget ? locToPosition(touchUnderViewPositionLoc3) : -1));
            }
            getItemViewDragHelper().updateDragViewPosition(f3, f5);
        }
        getItemViewDragHelper().onMove(this.hostAbility.findIndexOf((GridData) outputDragData.getDragItemInfo()), getCustomLayoutManager());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public void registerViewHolderAdd(GridData gridData, ISwapOrMove iSwapOrMove) {
        this.swapOrMove = iSwapOrMove;
        IDragHostAbility iDragHostAbility = this.hostAbility;
        DragCallbackProxy$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new DragCallbackProxy$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = gridData;
        externalSyntheticLambda0.f$1 = iSwapOrMove;
        VarHandle.storeStoreFence();
        iDragHostAbility.registerViewHolderAdd(externalSyntheticLambda0);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack
    public RecyclerView.ViewHolder createAndBindViewHolder(GridData gridData, ViewGroup viewGroup) {
        return this.hostAbility.createAndBindViewHolder(gridData, viewGroup);
    }
}
