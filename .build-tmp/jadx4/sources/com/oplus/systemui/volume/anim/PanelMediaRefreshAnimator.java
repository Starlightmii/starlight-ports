package com.oplus.systemui.volume.anim;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelMediaRefreshAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PanelMediaRefreshAnimator";
    private final Host host;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Host {
        void beginAdd(RecyclerView.ViewHolder viewHolder);

        void beginMove(RecyclerView.ViewHolder viewHolder);

        void dispatchRemoveFinished(RecyclerView.ViewHolder viewHolder);

        void finishAdd(RecyclerView.ViewHolder viewHolder);

        void finishChange(VolumeItemAnimator.ChangeInfo changeInfo, boolean z);

        void finishMove(RecyclerView.ViewHolder viewHolder);

        void finishPendingMove(RecyclerView.ViewHolder viewHolder);

        float getEndX(RecyclerView.ViewHolder viewHolder);

        float getTargetTranslationY(RecyclerView.ViewHolder viewHolder);

        void setFrameRateElevate(RecyclerView.ViewHolder viewHolder, boolean z);
    }

    public PanelMediaRefreshAnimator(Host host) {
        this.host = host;
    }

    public static final void access$playBatch$finishOnce(Ref.BooleanRef booleanRef, View view, PanelMediaRefreshAnimator panelMediaRefreshAnimator, RecyclerView.ViewHolder viewHolder, Ref.IntRef intRef, PendingBatch pendingBatch) {
        if (booleanRef.element) {
            return;
        }
        booleanRef.element = true;
        view.animate().setListener(null);
        ((VolumeItemAnimator.MediaRefreshHost) panelMediaRefreshAnimator.host).setFrameRateElevate(viewHolder, false);
        ((VolumeItemAnimator.MediaRefreshHost) panelMediaRefreshAnimator.host).finishMove(viewHolder);
        int i = intRef.element - 1;
        intRef.element = i;
        if (i == 0) {
            panelMediaRefreshAnimator.completeBatch(pendingBatch);
        }
    }

    public static final void access$playBatch$finishOnce$3(Ref.BooleanRef booleanRef, View view, PanelMediaRefreshAnimator panelMediaRefreshAnimator, RecyclerView.ViewHolder viewHolder, Ref.IntRef intRef, PendingBatch pendingBatch) {
        if (booleanRef.element) {
            return;
        }
        booleanRef.element = true;
        view.animate().setListener(null);
        if (view.getAlpha() < 1.0f) {
            view.setAlpha(1.0f);
        }
        ((VolumeItemAnimator.MediaRefreshHost) panelMediaRefreshAnimator.host).setFrameRateElevate(viewHolder, false);
        ((VolumeItemAnimator.MediaRefreshHost) panelMediaRefreshAnimator.host).finishAdd(viewHolder);
        int i = intRef.element - 1;
        intRef.element = i;
        if (i == 0) {
            panelMediaRefreshAnimator.completeBatch(pendingBatch);
        }
    }

    public final void completeBatch(PendingBatch pendingBatch) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("completeBatch additions=", pendingBatch.getAdditions().size(), pendingBatch.getMoveHolders().size(), " moves=", "PanelMediaRefreshAnimator-->"));
        }
        VolumeItemAnimator volumeItemAnimator = ((VolumeItemAnimator.MediaRefreshHost) this.host).this$0;
        volumeItemAnimator.getAdapter().setMediaRefreshLayout(false);
        volumeItemAnimator.dispatchFinishedWhenDone();
        if (volumeItemAnimator.getAdapter().getPendingMediaRefresh()) {
            RecyclerView hostRecyclerView$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = volumeItemAnimator.getAdapter().getHostRecyclerView$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            VolumeItemAnimator.MediaRefreshHost.onBatchFinished.1 r0 = new VolumeItemAnimator.MediaRefreshHost.onBatchFinished.1();
            r0.this$0 = volumeItemAnimator;
            VarHandle.storeStoreFence();
            hostRecyclerView$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.post(r0);
        }
    }

    public final void consumePendingAndPlay(RecyclerView recyclerView, ArrayList<RecyclerView.ViewHolder> arrayList, ArrayList<VolumeItemAnimator.MoveInfo> arrayList2, ArrayList<RecyclerView.ViewHolder> arrayList3, ArrayList<VolumeItemAnimator.ChangeInfo> arrayList4) {
        float f;
        long j;
        ArrayList arrayList5 = new ArrayList(arrayList);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            VolumeItemAnimator.MoveInfo moveInfo = arrayList2.get(i);
            i++;
            linkedHashSet.add(moveInfo.getHolder());
        }
        PendingBatch pendingBatch = new PendingBatch(recyclerView, arrayList5, linkedHashSet, new ArrayList(arrayList3), new ArrayList(arrayList4));
        arrayList.clear();
        arrayList2.clear();
        arrayList3.clear();
        arrayList4.clear();
        Iterator it = pendingBatch.getRemovals().iterator();
        while (true) {
            f = 1.0f;
            if (!it.hasNext()) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) it.next();
            if (viewHolder.itemView.getAlpha() < 1.0f) {
                viewHolder.itemView.setAlpha(1.0f);
            }
            ((VolumeItemAnimator.MediaRefreshHost) this.host).dispatchRemoveFinished(viewHolder);
        }
        for (VolumeItemAnimator.ChangeInfo changeInfo : pendingBatch.getChanges()) {
            if (changeInfo.getOldHolder() != null) {
                ((VolumeItemAnimator.MediaRefreshHost) this.host).finishChange(changeInfo, true);
            }
            if (changeInfo.getNewHolder() != null) {
                ((VolumeItemAnimator.MediaRefreshHost) this.host).finishChange(changeInfo, false);
            }
        }
        Set set = CollectionsKt___CollectionsKt.toSet(pendingBatch.getAdditions());
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Ref.IntRef intRef = new Ref.IntRef();
        RecyclerView recyclerView2 = pendingBatch.getRecyclerView();
        int childCount = recyclerView2.getChildCount();
        int i2 = 0;
        while (true) {
            j = 150;
            if (i2 >= childCount) {
                break;
            }
            RecyclerView.ViewHolder childViewHolder = recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i2));
            if (!set.contains(childViewHolder)) {
                View view = childViewHolder.itemView;
                view.animate().cancel();
                float endX = ((VolumeItemAnimator.MediaRefreshHost) this.host).getEndX(childViewHolder);
                if (Math.abs(view.getTranslationX() - endX) >= f) {
                    linkedHashSet2.add(childViewHolder);
                    intRef.element++;
                    ((VolumeItemAnimator.MediaRefreshHost) this.host).beginMove(childViewHolder);
                    ((VolumeItemAnimator.MediaRefreshHost) this.host).setFrameRateElevate(childViewHolder, true);
                    view.animate().translationX(endX).setDuration(150L).setInterpolator(VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_EXPAND_INTERPOLATOR()).setListener(new playBatch.3(new Ref.BooleanRef(), view, this, childViewHolder, intRef, pendingBatch, 0)).start();
                }
            }
            i2++;
            f = 1.0f;
        }
        for (RecyclerView.ViewHolder viewHolder2 : pendingBatch.getMoveHolders()) {
            if (!linkedHashSet2.contains(viewHolder2)) {
                ((VolumeItemAnimator.MediaRefreshHost) this.host).finishPendingMove(viewHolder2);
            }
        }
        for (RecyclerView.ViewHolder viewHolder3 : pendingBatch.getAdditions()) {
            View view2 = viewHolder3.itemView;
            view2.animate().cancel();
            view2.setTranslationX(((VolumeItemAnimator.MediaRefreshHost) this.host).getEndX(viewHolder3));
            view2.setTranslationY(((VolumeItemAnimator.MediaRefreshHost) this.host).getTargetTranslationY(viewHolder3));
            view2.setAlpha(0.0f);
            intRef.element++;
            ((VolumeItemAnimator.MediaRefreshHost) this.host).beginAdd(viewHolder3);
            ((VolumeItemAnimator.MediaRefreshHost) this.host).setFrameRateElevate(viewHolder3, true);
            view2.animate().alpha(1.0f).setStartDelay(j).setDuration(j).setInterpolator(VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_EXPAND_INTERPOLATOR()).setListener(new playBatch.3(new Ref.BooleanRef(), view2, this, viewHolder3, intRef, pendingBatch, 1)).start();
            j = j;
        }
        if (intRef.element == 0) {
            completeBatch(pendingBatch);
        }
    }
}
