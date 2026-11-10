package com.oplus.systemui.volume.anim;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.volume.VolumeListAdapter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelHandoffController {
    private final Host host;
    private final Function0 sessionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Host {
        void cancelAll(List<? extends RecyclerView.ViewHolder> list);

        void dispatchAddFinished(RecyclerView.ViewHolder viewHolder);

        void dispatchMoveFinished(RecyclerView.ViewHolder viewHolder);

        void endChangeAnimationIfNecessary(VolumeItemAnimator.ChangeInfo changeInfo);

        VolumeListAdapter getAdapter();

        ArrayList<RecyclerView.ViewHolder> getAddAnimations();

        ArrayList<ArrayList<RecyclerView.ViewHolder>> getAdditionsList();

        ArrayList<RecyclerView.ViewHolder> getChangeAnimations();

        ArrayList<ArrayList<VolumeItemAnimator.ChangeInfo>> getChangesList();

        ArrayList<RecyclerView.ViewHolder> getMoveAnimations();

        ArrayList<ArrayList<VolumeItemAnimator.MoveInfo>> getMovesList();

        List<RecyclerView.ViewHolder> getPendingAdditions();

        List<VolumeItemAnimator.ChangeInfo> getPendingChanges();

        List<VolumeItemAnimator.MoveInfo> getPendingMoves();

        boolean isPanelExpandStarted(RecyclerView.ViewHolder viewHolder);

        PanelRowItemState panelState(RecyclerView.ViewHolder viewHolder);

        PanelRowItemState panelStateOrNull(RecyclerView.ViewHolder viewHolder);

        void recordPanelEntranceTranslation(RecyclerView.ViewHolder viewHolder);

        void recordPanelExpandStagger(RecyclerView.ViewHolder viewHolder, int i, PanelEntranceType panelEntranceType);

        void recordPanelStartTranslation(RecyclerView.ViewHolder viewHolder);

        int resolvePanelExpandShowIndex(RecyclerView.ViewHolder viewHolder, int i);

        float resolvePanelStartTranslationX(RecyclerView.ViewHolder viewHolder);
    }

    public PanelHandoffController(Host host, Function0 function0) {
        this.host = host;
        this.sessionProvider = function0;
    }

    public final void clearPendingPanelExpand(boolean z, boolean z2) {
        int size = ((VolumeItemAnimator.HandoffHost) this.host).getPendingMoves().size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                RecyclerView.ViewHolder holder = ((VolumeItemAnimator.HandoffHost) this.host).getPendingMoves().get(size).getHolder();
                if (z) {
                    prepareUnstartedEntrance(holder, UnstartedEntranceSource.HANDOFF_MOVE);
                }
                if (!z) {
                    holder.itemView.setTranslationX(0.0f);
                    holder.itemView.setTranslationY(0.0f);
                }
                ((VolumeItemAnimator.HandoffHost) this.host).dispatchMoveFinished(holder);
                ((VolumeItemAnimator.HandoffHost) this.host).getPendingMoves().remove(size);
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        int size2 = ((VolumeItemAnimator.HandoffHost) this.host).getPendingAdditions().size() - 1;
        if (size2 >= 0) {
            while (true) {
                int i2 = size2 - 1;
                RecyclerView.ViewHolder viewHolder = ((VolumeItemAnimator.HandoffHost) this.host).getPendingAdditions().get(size2);
                if (z) {
                    prepareUnstartedEntrance(viewHolder, UnstartedEntranceSource.HANDOFF_ADD);
                } else if (viewHolder.itemView.getAlpha() < 1.0f) {
                    viewHolder.itemView.setAlpha(1.0f);
                }
                ((VolumeItemAnimator.HandoffHost) this.host).dispatchAddFinished(viewHolder);
                ((VolumeItemAnimator.HandoffHost) this.host).getPendingAdditions().remove(size2);
                if (i2 < 0) {
                    break;
                } else {
                    size2 = i2;
                }
            }
        }
        int size3 = ((VolumeItemAnimator.HandoffHost) this.host).getPendingChanges().size() - 1;
        if (size3 >= 0) {
            while (true) {
                int i3 = size3 - 1;
                VolumeItemAnimator.HandoffHost handoffHost = (VolumeItemAnimator.HandoffHost) this.host;
                handoffHost.endChangeAnimationIfNecessary(handoffHost.getPendingChanges().get(size3));
                if (i3 < 0) {
                    break;
                } else {
                    size3 = i3;
                }
            }
        }
        ((VolumeItemAnimator.HandoffHost) this.host).getPendingChanges().clear();
        if (!z2) {
            return;
        }
        int size4 = ((VolumeItemAnimator.HandoffHost) this.host).getMovesList().size() - 1;
        if (size4 >= 0) {
            while (true) {
                int i4 = size4 - 1;
                ArrayList<VolumeItemAnimator.MoveInfo> arrayList = ((VolumeItemAnimator.HandoffHost) this.host).getMovesList().get(size4);
                int size5 = arrayList.size() - 1;
                if (size5 >= 0) {
                    while (true) {
                        int i5 = size5 - 1;
                        RecyclerView.ViewHolder holder2 = arrayList.get(size5).getHolder();
                        if (z) {
                            prepareUnstartedEntrance(holder2, UnstartedEntranceSource.HANDOFF_MOVE);
                        }
                        if (!z) {
                            holder2.itemView.setTranslationX(0.0f);
                            holder2.itemView.setTranslationY(0.0f);
                        }
                        ((VolumeItemAnimator.HandoffHost) this.host).dispatchMoveFinished(holder2);
                        arrayList.remove(size5);
                        if (i5 < 0) {
                            break;
                        } else {
                            size5 = i5;
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    ((VolumeItemAnimator.HandoffHost) this.host).getMovesList().remove(size4);
                }
                if (i4 < 0) {
                    break;
                } else {
                    size4 = i4;
                }
            }
        }
        int size6 = ((VolumeItemAnimator.HandoffHost) this.host).getAdditionsList().size() - 1;
        if (size6 >= 0) {
            while (true) {
                int i6 = size6 - 1;
                ArrayList<RecyclerView.ViewHolder> arrayList2 = ((VolumeItemAnimator.HandoffHost) this.host).getAdditionsList().get(size6);
                int size7 = arrayList2.size() - 1;
                if (size7 >= 0) {
                    while (true) {
                        int i7 = size7 - 1;
                        RecyclerView.ViewHolder viewHolder2 = arrayList2.get(size7);
                        if (z) {
                            prepareUnstartedEntrance(viewHolder2, UnstartedEntranceSource.HANDOFF_ADD);
                        } else if (viewHolder2.itemView.getAlpha() < 1.0f) {
                            viewHolder2.itemView.setAlpha(1.0f);
                        }
                        ((VolumeItemAnimator.HandoffHost) this.host).dispatchAddFinished(viewHolder2);
                        arrayList2.remove(size7);
                        if (i7 < 0) {
                            break;
                        } else {
                            size7 = i7;
                        }
                    }
                }
                if (arrayList2.isEmpty()) {
                    ((VolumeItemAnimator.HandoffHost) this.host).getAdditionsList().remove(size6);
                }
                if (i6 < 0) {
                    break;
                } else {
                    size6 = i6;
                }
            }
        }
        int size8 = ((VolumeItemAnimator.HandoffHost) this.host).getChangesList().size() - 1;
        if (size8 < 0) {
            return;
        }
        while (true) {
            int i8 = size8 - 1;
            ArrayList<VolumeItemAnimator.ChangeInfo> arrayList3 = ((VolumeItemAnimator.HandoffHost) this.host).getChangesList().get(size8);
            int size9 = arrayList3.size() - 1;
            if (size9 >= 0) {
                while (true) {
                    int i9 = size9 - 1;
                    ((VolumeItemAnimator.HandoffHost) this.host).endChangeAnimationIfNecessary(arrayList3.get(size9));
                    if (i9 < 0) {
                        break;
                    } else {
                        size9 = i9;
                    }
                }
            }
            if (arrayList3.isEmpty()) {
                ((VolumeItemAnimator.HandoffHost) this.host).getChangesList().remove(size8);
            }
            if (i8 < 0) {
                return;
            } else {
                size8 = i8;
            }
        }
    }

    public final void ensureCollapseRemoving() {
        PanelTransitionSession panelTransitionSession = (PanelTransitionSession) this.sessionProvider.invoke();
        if (panelTransitionSession != null && panelTransitionSession.getPhase() == PanelTransitionSession.Phase.HANDOFF) {
            panelTransitionSession.markCollapsing();
        }
    }

    public final void handoffExpandForCollapse(RecyclerView recyclerView) {
        float fFloatValue;
        int currentDataSource = ((VolumeItemAnimator.HandoffHost) this.host).getAdapter().getCurrentDataSource();
        Host host = this.host;
        if (currentDataSource != 1) {
            VolumeItemAnimator.access$clearAllHandoffPoses(((VolumeItemAnimator.HandoffHost) host).this$0);
        } else {
            VolumeItemAnimator.access$clearAllHandoffPoses(((VolumeItemAnimator.HandoffHost) host).this$0);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = CollectionsKt___CollectionsKt.filterNotNull(((VolumeItemAnimator.HandoffHost) this.host).getAddAnimations()).iterator();
            while (it.hasNext()) {
                linkedHashSet.add((RecyclerView.ViewHolder) it.next());
            }
            Iterator it2 = CollectionsKt___CollectionsKt.filterNotNull(((VolumeItemAnimator.HandoffHost) this.host).getMoveAnimations()).iterator();
            while (it2.hasNext()) {
                linkedHashSet.add((RecyclerView.ViewHolder) it2.next());
            }
            if (recyclerView != null) {
                int childCount = recyclerView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                    if (childViewHolder != null) {
                        linkedHashSet.add(childViewHolder);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : linkedHashSet) {
                if (((VolumeItemAnimator.HandoffHost) this.host).isPanelExpandStarted((RecyclerView.ViewHolder) obj)) {
                    arrayList.add(obj);
                }
            }
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj2 = arrayList.get(i2);
                i2++;
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) obj2;
                View view = viewHolder.itemView;
                PanelRowItemState panelRowItemStatePanelStateOrNull = ((VolumeItemAnimator.HandoffHost) this.host).panelStateOrNull(viewHolder);
                Float startTranslationX = panelRowItemStatePanelStateOrNull != null ? panelRowItemStatePanelStateOrNull.getStartTranslationX() : null;
                PanelEntranceType entranceType = panelRowItemStatePanelStateOrNull != null ? panelRowItemStatePanelStateOrNull.getEntranceType() : null;
                int i3 = entranceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[entranceType.ordinal()];
                if (i3 == -1) {
                    fFloatValue = startTranslationX != null ? startTranslationX.floatValue() : ((VolumeItemAnimator.HandoffHost) this.host).resolvePanelStartTranslationX(viewHolder);
                } else if (i3 == 1) {
                    fFloatValue = startTranslationX != null ? startTranslationX.floatValue() : ((VolumeItemAnimator.HandoffHost) this.host).resolvePanelStartTranslationX(viewHolder);
                } else {
                    if (i3 != 2) {
                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                        return;
                    }
                    fFloatValue = startTranslationX != null ? startTranslationX.floatValue() : view.getTranslationX();
                }
                ((VolumeItemAnimator.HandoffHost) this.host).panelState(viewHolder).setHandoffPose(new PanelHandoffPose(fFloatValue, view.getTranslationX(), view.getTranslationY()));
            }
        }
        clearPendingPanelExpand(true, true);
        VolumeItemAnimator.HandoffHost handoffHost = (VolumeItemAnimator.HandoffHost) this.host;
        handoffHost.cancelAll(handoffHost.getAddAnimations());
        VolumeItemAnimator.HandoffHost handoffHost2 = (VolumeItemAnimator.HandoffHost) this.host;
        handoffHost2.cancelAll(handoffHost2.getMoveAnimations());
        VolumeItemAnimator.HandoffHost handoffHost3 = (VolumeItemAnimator.HandoffHost) this.host;
        handoffHost3.cancelAll(handoffHost3.getChangeAnimations());
        ((VolumeItemAnimator.HandoffHost) this.host).getAddAnimations().clear();
        ((VolumeItemAnimator.HandoffHost) this.host).getMoveAnimations().clear();
        ((VolumeItemAnimator.HandoffHost) this.host).getChangeAnimations().clear();
        if (recyclerView == null) {
            ((VolumeItemAnimator.HandoffHost) this.host).this$0.dispatchFinishedWhenDone();
            return;
        }
        int childCount2 = recyclerView.getChildCount();
        for (int i4 = 0; i4 < childCount2; i4++) {
            recyclerView.getChildAt(i4).animate().cancel();
        }
        handoffExpandForCollapse.1.1 r0 = new handoffExpandForCollapse.1.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        recyclerView.post(r0);
    }

    public final void prepareUnstartedEntrance(RecyclerView.ViewHolder viewHolder, UnstartedEntranceSource unstartedEntranceSource) {
        if (((VolumeItemAnimator.HandoffHost) this.host).isPanelExpandStarted(viewHolder)) {
            return;
        }
        int iOrdinal = unstartedEntranceSource.ordinal();
        if (iOrdinal == 0) {
            ((VolumeItemAnimator.HandoffHost) this.host).recordPanelStartTranslation(viewHolder);
            PanelRowItemState panelRowItemStatePanelStateOrNull = ((VolumeItemAnimator.HandoffHost) this.host).panelStateOrNull(viewHolder);
            if ((panelRowItemStatePanelStateOrNull != null ? panelRowItemStatePanelStateOrNull.getEntranceType() : null) == null) {
                VolumeItemAnimator.HandoffHost handoffHost = (VolumeItemAnimator.HandoffHost) this.host;
                int iResolvePanelExpandTotalCount = handoffHost.this$0.resolvePanelExpandTotalCount(handoffHost.getAdapter().getItemCount());
                VolumeItemAnimator.HandoffHost handoffHost2 = (VolumeItemAnimator.HandoffHost) this.host;
                handoffHost2.recordPanelExpandStagger(viewHolder, handoffHost2.resolvePanelExpandShowIndex(viewHolder, iResolvePanelExpandTotalCount), PanelEntranceType.ADD);
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            PanelRowItemState panelRowItemStatePanelStateOrNull2 = ((VolumeItemAnimator.HandoffHost) this.host).panelStateOrNull(viewHolder);
            if ((panelRowItemStatePanelStateOrNull2 != null ? panelRowItemStatePanelStateOrNull2.getEntranceType() : null) != null) {
                return;
            }
            ((VolumeItemAnimator.HandoffHost) this.host).recordPanelEntranceTranslation(viewHolder);
            VolumeItemAnimator.HandoffHost handoffHost3 = (VolumeItemAnimator.HandoffHost) this.host;
            int iResolvePanelExpandTotalCount2 = handoffHost3.this$0.resolvePanelExpandTotalCount(handoffHost3.getAdapter().getItemCount());
            VolumeItemAnimator.HandoffHost handoffHost4 = (VolumeItemAnimator.HandoffHost) this.host;
            handoffHost4.recordPanelExpandStagger(viewHolder, handoffHost4.resolvePanelExpandShowIndex(viewHolder, iResolvePanelExpandTotalCount2), PanelEntranceType.MOVE);
            return;
        }
        if (iOrdinal != 2) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return;
        }
        PanelRowItemState panelRowItemStatePanelStateOrNull3 = ((VolumeItemAnimator.HandoffHost) this.host).panelStateOrNull(viewHolder);
        PanelEntranceType entranceType = panelRowItemStatePanelStateOrNull3 != null ? panelRowItemStatePanelStateOrNull3.getEntranceType() : null;
        int i = entranceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[entranceType.ordinal()];
        if (i == -1) {
            PanelTransitionSession panelTransitionSession = (PanelTransitionSession) this.sessionProvider.invoke();
            if (panelTransitionSession == null || !panelTransitionSession.isRowInterruptCollapse()) {
                return;
            }
            if ((panelRowItemStatePanelStateOrNull3 != null ? panelRowItemStatePanelStateOrNull3.getStartTranslationX() : null) == null) {
                ((VolumeItemAnimator.HandoffHost) this.host).recordPanelStartTranslation(viewHolder);
                return;
            }
            return;
        }
        if (i != 1) {
            if (i == 2) {
                return;
            }
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        } else if (panelRowItemStatePanelStateOrNull3.getStartTranslationX() == null) {
            ((VolumeItemAnimator.HandoffHost) this.host).recordPanelStartTranslation(viewHolder);
        }
    }

    public final void resetInterruptState() {
        VolumeItemAnimator.access$clearAllHandoffPoses(((VolumeItemAnimator.HandoffHost) this.host).this$0);
        PanelTransitionSession panelTransitionSession = (PanelTransitionSession) this.sessionProvider.invoke();
        if (panelTransitionSession != null) {
            panelTransitionSession.rowHandoffActive = false;
        }
    }

    public final boolean shouldDeferResetInterruptCollapse(boolean z, boolean z2) {
        PanelTransitionSession panelTransitionSession = (PanelTransitionSession) this.sessionProvider.invoke();
        return panelTransitionSession != null && panelTransitionSession.shouldDeferClearRowHandoff(z, z2);
    }
}
