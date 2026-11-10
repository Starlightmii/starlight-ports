package com.oplus.systemui.volume.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.volume.VolumeListAdapter;
import com.oplus.systemui.volume.manager.VolumeAnimStateManager;
import com.oplus.systemui.volume.utils.OplusVolumeFrameRateUtilKt;
import com.oplus.systemui.volume.utils.OplusVolumePanelTiming;
import com.oplus.systemui.volume.utils.VolumeLayoutUtil;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeItemAnimator extends SimpleItemAnimator {
    public static final Companion Companion = new Companion();
    public static final String TAG = "VolumeItemAnimator";
    private final VolumeListAdapter adapter;
    public float cachedPanelBiasTranslationY;
    private final PanelHandoffController panelHandoffController;
    private final PanelMediaRefreshAnimator panelMediaRefreshAnimator;
    private PanelTransitionSession panelTransitionSession;
    private TimeInterpolator sDefaultInterpolator;
    private final ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList<>();
    private final ArrayList<MoveInfo> mPendingMoves = new ArrayList<>();
    private final ArrayList<ChangeInfo> mPendingChanges = new ArrayList<>();
    private ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList<>();
    private ArrayList<ArrayList<MoveInfo>> mMovesList = new ArrayList<>();
    private ArrayList<ArrayList<ChangeInfo>> mChangesList = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList<>();
    private final HashMap<RecyclerView.ViewHolder, PanelRowItemState> mPanelItemStateMap = new HashMap<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HandoffHost implements PanelHandoffController.Host {
        public /* synthetic */ VolumeItemAnimator this$0;

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void cancelAll(List<? extends RecyclerView.ViewHolder> list) {
            this.this$0.cancelAll(list);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void dispatchAddFinished(RecyclerView.ViewHolder viewHolder) {
            this.this$0.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void dispatchMoveFinished(RecyclerView.ViewHolder viewHolder) {
            this.this$0.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
            VolumeItemAnimator volumeItemAnimator = this.this$0;
            RecyclerView.ViewHolder oldHolder = changeInfo.getOldHolder();
            if (oldHolder != null) {
                volumeItemAnimator.endChangeAnimationIfNecessary(changeInfo, oldHolder);
            }
            RecyclerView.ViewHolder newHolder = changeInfo.getNewHolder();
            if (newHolder != null) {
                volumeItemAnimator.endChangeAnimationIfNecessary(changeInfo, newHolder);
            }
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public VolumeListAdapter getAdapter() {
            return this.this$0.getAdapter();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<RecyclerView.ViewHolder> getAddAnimations() {
            return this.this$0.getMAddAnimations();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<ArrayList<RecyclerView.ViewHolder>> getAdditionsList() {
            return this.this$0.getMAdditionsList();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<RecyclerView.ViewHolder> getChangeAnimations() {
            return this.this$0.getMChangeAnimations();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<ArrayList<ChangeInfo>> getChangesList() {
            return this.this$0.getMChangesList();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<RecyclerView.ViewHolder> getMoveAnimations() {
            return this.this$0.getMMoveAnimations();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public ArrayList<ArrayList<MoveInfo>> getMovesList() {
            return this.this$0.getMMovesList();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public List<RecyclerView.ViewHolder> getPendingAdditions() {
            return this.this$0.mPendingAdditions;
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public List<ChangeInfo> getPendingChanges() {
            return this.this$0.mPendingChanges;
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public List<MoveInfo> getPendingMoves() {
            return this.this$0.mPendingMoves;
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public boolean isPanelExpandStarted(RecyclerView.ViewHolder viewHolder) {
            PanelRowItemState panelRowItemStatePanelStateOrNull = this.this$0.panelStateOrNull(viewHolder);
            return panelRowItemStatePanelStateOrNull != null && panelRowItemStatePanelStateOrNull.getExpandStarted();
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public PanelRowItemState panelState(RecyclerView.ViewHolder viewHolder) {
            return this.this$0.panelState(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public PanelRowItemState panelStateOrNull(RecyclerView.ViewHolder viewHolder) {
            return this.this$0.panelStateOrNull(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void recordPanelEntranceTranslation(RecyclerView.ViewHolder viewHolder) {
            this.this$0.panelState(viewHolder).setStartTranslationX(Float.valueOf(viewHolder.itemView.getTranslationX()));
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void recordPanelExpandStagger(RecyclerView.ViewHolder viewHolder, int i, PanelEntranceType panelEntranceType) {
            PanelRowItemState panelRowItemStatePanelState = this.this$0.panelState(viewHolder);
            panelRowItemStatePanelState.setExpandShowIndex(Integer.valueOf(i));
            panelRowItemStatePanelState.setEntranceType(panelEntranceType);
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public void recordPanelStartTranslation(RecyclerView.ViewHolder viewHolder) {
            VolumeItemAnimator volumeItemAnimator = this.this$0;
            float fResolvePanelStartTranslationX = VolumeItemAnimator.resolvePanelStartTranslationX(viewHolder);
            viewHolder.itemView.setTranslationX(fResolvePanelStartTranslationX);
            volumeItemAnimator.panelState(viewHolder).setStartTranslationX(Float.valueOf(fResolvePanelStartTranslationX));
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public int resolvePanelExpandShowIndex(RecyclerView.ViewHolder viewHolder, int i) {
            int i2 = i - 1;
            int iCoerceIn = RangesKt___RangesKt.coerceIn(VolumeItemAnimator.resolveHolderStaggerPosition(viewHolder), 0, i2);
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusVolumeKeyInRight()) {
                return iCoerceIn;
            }
            int i3 = i2 - iCoerceIn;
            if (i3 < 0) {
                return 0;
            }
            return i3;
        }

        @Override // com.oplus.systemui.volume.anim.PanelHandoffController.Host
        public float resolvePanelStartTranslationX(RecyclerView.ViewHolder viewHolder) {
            return VolumeItemAnimator.resolvePanelStartTranslationX(viewHolder);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MediaRefreshHost implements PanelMediaRefreshAnimator.Host {
        public /* synthetic */ VolumeItemAnimator this$0;

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void beginAdd(RecyclerView.ViewHolder viewHolder) {
            this.this$0.getMAddAnimations().add(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void beginMove(RecyclerView.ViewHolder viewHolder) {
            this.this$0.getMMoveAnimations().add(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void dispatchRemoveFinished(RecyclerView.ViewHolder viewHolder) {
            this.this$0.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void finishAdd(RecyclerView.ViewHolder viewHolder) {
            VolumeItemAnimator volumeItemAnimator = this.this$0;
            volumeItemAnimator.getMAddAnimations().remove(viewHolder);
            volumeItemAnimator.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void finishChange(ChangeInfo changeInfo, boolean z) {
            RecyclerView.ViewHolder newHolder;
            if (z) {
                newHolder = changeInfo.getOldHolder();
            } else {
                newHolder = changeInfo.getNewHolder();
                if (newHolder == null) {
                    return;
                }
            }
            this.this$0.dispatchAnimationFinished(newHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void finishMove(RecyclerView.ViewHolder viewHolder) {
            VolumeItemAnimator volumeItemAnimator = this.this$0;
            volumeItemAnimator.getMMoveAnimations().remove(viewHolder);
            volumeItemAnimator.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void finishPendingMove(RecyclerView.ViewHolder viewHolder) {
            this.this$0.dispatchAnimationFinished(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public float getEndX(RecyclerView.ViewHolder viewHolder) {
            return this.this$0.getEndX(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public float getTargetTranslationY(RecyclerView.ViewHolder viewHolder) {
            return this.this$0.getTranslationY(viewHolder);
        }

        @Override // com.oplus.systemui.volume.anim.PanelMediaRefreshAnimator.Host
        public void setFrameRateElevate(RecyclerView.ViewHolder viewHolder, boolean z) {
            VolumeItemAnimator.access$setFrameRateElevateAndSetNavigationBar(this.this$0, viewHolder, z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MoveInfo {
        public int fromX;
        public int fromY;
        private RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        public MoveInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.holder = viewHolder;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }

        public final RecyclerView.ViewHolder component1() {
            return this.holder;
        }

        public final MoveInfo copy$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            return new MoveInfo(viewHolder, i, i2, i3, i4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MoveInfo)) {
                return false;
            }
            MoveInfo moveInfo = (MoveInfo) obj;
            return Intrinsics.areEqual(this.holder, moveInfo.holder) && this.fromX == moveInfo.fromX && this.fromY == moveInfo.fromY && this.toX == moveInfo.toX && this.toY == moveInfo.toY;
        }

        public final int getFromX() {
            return this.fromX;
        }

        public final int getFromY() {
            return this.fromY;
        }

        public final RecyclerView.ViewHolder getHolder() {
            return this.holder;
        }

        public final int getToX() {
            return this.toX;
        }

        public final int getToY() {
            return this.toY;
        }

        public final int hashCode() {
            return Integer.hashCode(this.toY) + MonoSpline$.ExternalSyntheticOutline0.m(this.toX, MonoSpline$.ExternalSyntheticOutline0.m(this.fromY, MonoSpline$.ExternalSyntheticOutline0.m(this.fromX, this.holder.hashCode() * 31, 31), 31), 31);
        }

        public final void setFromX(int i) {
            this.fromX = i;
        }

        public final void setFromY(int i) {
            this.fromY = i;
        }

        public final void setHolder(RecyclerView.ViewHolder viewHolder) {
            this.holder = viewHolder;
        }

        public final void setToX(int i) {
            this.toX = i;
        }

        public final void setToY(int i) {
            this.toY = i;
        }

        public String toString() {
            RecyclerView.ViewHolder viewHolder = this.holder;
            int i = this.fromX;
            int i2 = this.fromY;
            int i3 = this.toX;
            int i4 = this.toY;
            StringBuilder sb = new StringBuilder("MoveInfo(holder=");
            sb.append(viewHolder);
            sb.append(", fromX=");
            sb.append(i);
            sb.append(", fromY=");
            RectList$.ExternalSyntheticOutline0.m(i2, i3, ", toX=", ", toY=", sb);
            return SurfaceContainer$.ExternalSyntheticOutline0.m(i4, ")", sb);
        }
    }

    public VolumeItemAnimator(VolumeListAdapter volumeListAdapter) {
        this.adapter = volumeListAdapter;
        HandoffHost handoffHost = new HandoffHost();
        handoffHost.this$0 = this;
        VarHandle.storeStoreFence();
        VolumeItemAnimator$$ExternalSyntheticLambda0 volumeItemAnimator$$ExternalSyntheticLambda0 = new VolumeItemAnimator$$ExternalSyntheticLambda0();
        volumeItemAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.panelHandoffController = new PanelHandoffController(handoffHost, volumeItemAnimator$$ExternalSyntheticLambda0);
        MediaRefreshHost mediaRefreshHost = new MediaRefreshHost();
        mediaRefreshHost.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelMediaRefreshAnimator = new PanelMediaRefreshAnimator(mediaRefreshHost);
        this.cachedPanelBiasTranslationY = Float.NaN;
    }

    public static final void access$animateChangeImpl(VolumeItemAnimator volumeItemAnimator, ChangeInfo changeInfo) {
        VolumeItemAnimator volumeItemAnimator2;
        ChangeInfo changeInfo2;
        RecyclerView.ViewHolder oldHolder = changeInfo.getOldHolder();
        View view = oldHolder != null ? oldHolder.itemView : null;
        RecyclerView.ViewHolder newHolder = changeInfo.getNewHolder();
        View view2 = newHolder != null ? newHolder.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(volumeItemAnimator.getChangeDuration());
            volumeItemAnimator.mChangeAnimations.add(changeInfo.getOldHolder());
            duration.translationX(changeInfo.getToX() - changeInfo.getFromX());
            duration.translationY(changeInfo.getToY() - changeInfo.getFromY());
            volumeItemAnimator2 = volumeItemAnimator;
            changeInfo2 = changeInfo;
            duration.alpha(0.0f).setListener(new animateChangeImpl.1(volumeItemAnimator2, changeInfo2, duration, view, 0)).start();
        } else {
            volumeItemAnimator2 = volumeItemAnimator;
            changeInfo2 = changeInfo;
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            volumeItemAnimator2.mChangeAnimations.add(changeInfo2.getNewHolder());
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(volumeItemAnimator2.getChangeDuration()).alpha(1.0f).setListener(new animateChangeImpl.1(volumeItemAnimator2, changeInfo2, viewPropertyAnimatorAnimate, view2, 1)).start();
        }
    }

    public static final void access$clearAllHandoffPoses(VolumeItemAnimator volumeItemAnimator) {
        Iterator<PanelRowItemState> it = volumeItemAnimator.mPanelItemStateMap.values().iterator();
        while (it.hasNext()) {
            it.next().setHandoffPose((PanelHandoffPose) null);
        }
    }

    public static final void access$setFrameRateElevateAndSetNavigationBar(VolumeItemAnimator volumeItemAnimator, RecyclerView.ViewHolder viewHolder, boolean z) {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isOplusVolumeKeyInRight() || viewHolder.getAbsoluteAdapterPosition() == 0) {
            if (FeatureOption.isOplusVolumeKeyInRight() || viewHolder.getAbsoluteAdapterPosition() == volumeItemAnimator.adapter.getItemCount() - 1) {
                if (z) {
                    OplusVolumeFrameRateUtilKt.startFrameRateElevate(viewHolder.itemView, 20502);
                } else {
                    OplusVolumeFrameRateUtilKt.endFrameRateElevate(viewHolder.itemView, 20502);
                }
                VolumeAnimStateManager.INSTANCE.getClass();
                VolumeAnimStateManager.updateVolumeAnimPlay(z);
            }
        }
    }

    public static float getPaddingX(RecyclerView.ViewHolder viewHolder) {
        if (VolumeLayoutUtil.isLandscape(viewHolder.itemView.getContext())) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                return viewHolder.itemView.getContext().getResources().getDimension(2131173888);
            }
        }
        return viewHolder.itemView.getContext().getResources().getDimension(2131173887);
    }

    public static int resolveHolderStaggerPosition(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getAbsoluteAdapterPosition() != -1) {
            return viewHolder.getAbsoluteAdapterPosition();
        }
        if (viewHolder.getLayoutPosition() != -1) {
            return viewHolder.getLayoutPosition();
        }
        if (viewHolder.getOldPosition() != -1) {
            return viewHolder.getOldPosition();
        }
        return 0;
    }

    public static float resolvePanelStartTranslationX(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        float x = view.getX() - view.getTranslationX();
        FeatureOption.INSTANCE.getClass();
        int volumePanelWidth = FeatureOption.isOplusVolumeKeyInRight() ? (VolumeLayoutUtil.getVolumePanelWidth(viewHolder) - viewHolder.itemView.getWidth()) - VolumeLayoutUtil.dp2Px(11.0f, viewHolder.itemView.getContext()) : VolumeLayoutUtil.dp2Px(11.0f, viewHolder.itemView.getContext());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "VolumeItemAnimator-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("getStartX startX:", volumePanelWidth, viewHolder.getAbsoluteAdapterPosition(), " position: ", " ")));
        }
        return volumePanelWidth - x;
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        resetAnimation$3(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        if (this.adapter.getCurrentDataSource() == 1) {
            viewHolder.itemView.setTranslationY(0.0f);
        }
        this.mPendingAdditions.add(viewHolder);
        return true;
    }

    public final void animateAddImpl(final RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        final View view = viewHolder.itemView;
        final ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.mAddAnimations.add(viewHolder);
        final boolean z = false;
        if (this.adapter.getCurrentDataSource() == 1) {
            z = true;
        }
        if (z) {
            float fResolvePanelStartTranslationX = resolvePanelStartTranslationX(viewHolder);
            viewHolder.itemView.setTranslationX(fResolvePanelStartTranslationX);
            panelState(viewHolder).setStartTranslationX(Float.valueOf(fResolvePanelStartTranslationX));
        }
        int iResolvePanelExpandTotalCount = resolvePanelExpandTotalCount(i) - 1;
        int iCoerceIn = RangesKt___RangesKt.coerceIn(resolveHolderStaggerPosition(viewHolder), z, iResolvePanelExpandTotalCount);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusVolumeKeyInRight()) {
            i2 = iCoerceIn;
        } else {
            i2 = iResolvePanelExpandTotalCount - iCoerceIn;
            if (i2 < 0) {
                i2 = 0;
            }
        }
        if (z) {
            PanelEntranceType panelEntranceType = PanelEntranceType.ADD;
            PanelRowItemState panelRowItemStatePanelState = panelState(viewHolder);
            panelRowItemStatePanelState.setExpandShowIndex(Integer.valueOf(i2));
            panelRowItemStatePanelState.setEntranceType(panelEntranceType);
            long j = ((long) i2) * 33;
            viewPropertyAnimatorAnimate.translationX(getEndX(viewHolder)).translationY(getTranslationY(viewHolder)).setDuration(OplusVolumePanelTiming.resolveExpandItemDurationMs(this.adapter.getContext(), i2)).setStartDelay(j >= 0 ? j : 0L).setInterpolator(VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_EXPAND_INTERPOLATOR());
        } else {
            viewPropertyAnimatorAnimate.setDuration(0L);
        }
        viewPropertyAnimatorAnimate.alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.volume.anim.VolumeItemAnimator.animateAddImpl.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                if (z) {
                    return;
                }
                view.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                VolumeItemAnimator.access$setFrameRateElevateAndSetNavigationBar(this, viewHolder, false);
                viewPropertyAnimatorAnimate.setListener(null);
                this.dispatchAnimationFinished(viewHolder);
                this.getMAddAnimations().remove(viewHolder);
                this.dispatchFinishedWhenDone();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                if (z) {
                    this.panelState(viewHolder).setExpandStarted(true);
                }
                VolumeItemAnimator.access$setFrameRateElevateAndSetNavigationBar(this, viewHolder, true);
            }
        }).start();
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i, i2, i3, i4);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation$3(viewHolder);
        int i5 = (int) ((i3 - i) - translationX);
        int i6 = (int) ((i4 - i2) - translationY);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        if (viewHolder2 != null) {
            resetAnimation$3(viewHolder2);
            viewHolder2.itemView.setTranslationX(-i5);
            viewHolder2.itemView.setTranslationY(-i6);
            viewHolder2.itemView.setAlpha(0.0f);
        }
        this.mPendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        if (this.adapter.getCurrentDataSource() != 1) {
            i += (int) viewHolder.itemView.getTranslationX();
            i2 += (int) viewHolder.itemView.getTranslationY();
        }
        int i5 = i;
        int i6 = i2;
        resetAnimation$3(viewHolder);
        int i7 = i3 - i5;
        int i8 = i4 - i6;
        if (i7 == 0 && i8 == 0 && this.adapter.getCurrentDataSource() != 1) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        if (i7 != 0) {
            view.setTranslationX(-i7);
        }
        if (i8 != 0) {
            view.setTranslationY(-i8);
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, i5, i6, i3, i4));
        return true;
    }

    public final void animateMoveImpl(final RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4, int i5) {
        final View view = viewHolder.itemView;
        final int i6 = i3 - i;
        final int i7 = i4 - i2;
        int i8 = 0;
        final boolean z = this.adapter.getCurrentDataSource() == 1;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.mMoveAnimations.add(viewHolder);
        if (z) {
            panelState(viewHolder).setStartTranslationX(Float.valueOf(viewHolder.itemView.getTranslationX()));
        }
        int iResolvePanelExpandTotalCount = resolvePanelExpandTotalCount(i5) - 1;
        int iCoerceIn = RangesKt___RangesKt.coerceIn(resolveHolderStaggerPosition(viewHolder), 0, iResolvePanelExpandTotalCount);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusVolumeKeyInRight()) {
            i8 = iCoerceIn;
        } else {
            int i9 = iResolvePanelExpandTotalCount - iCoerceIn;
            if (i9 >= 0) {
                i8 = i9;
            }
        }
        if (z) {
            PanelEntranceType panelEntranceType = PanelEntranceType.MOVE;
            PanelRowItemState panelRowItemStatePanelState = panelState(viewHolder);
            panelRowItemStatePanelState.setExpandShowIndex(Integer.valueOf(i8));
            panelRowItemStatePanelState.setEntranceType(panelEntranceType);
            ViewPropertyAnimator duration = viewPropertyAnimatorAnimate.translationX(getEndX(viewHolder)).translationY(getTranslationY(viewHolder)).setDuration(OplusVolumePanelTiming.resolveExpandItemDurationMs(this.adapter.getContext(), i8));
            long j = ((long) i8) * 33;
            if (j < 0) {
                j = 0;
            }
            duration.setStartDelay(j).setInterpolator(VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_EXPAND_INTERPOLATOR());
        } else {
            viewPropertyAnimatorAnimate.setDuration(getMoveDuration());
        }
        viewPropertyAnimatorAnimate.setListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.volume.anim.VolumeItemAnimator.animateMoveImpl.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                if (z) {
                    return;
                }
                if (i6 != 0) {
                    view.setTranslationX(0.0f);
                }
                if (i7 != 0) {
                    view.setTranslationY(0.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                VolumeItemAnimator.access$setFrameRateElevateAndSetNavigationBar(this, viewHolder, false);
                viewPropertyAnimatorAnimate.setListener(null);
                this.dispatchAnimationFinished(viewHolder);
                this.getMMoveAnimations().remove(viewHolder);
                this.dispatchFinishedWhenDone();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                if (z) {
                    this.panelState(viewHolder).setExpandStarted(true);
                }
                VolumeItemAnimator.access$setFrameRateElevateAndSetNavigationBar(this, viewHolder, true);
            }
        }).start();
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getAbsoluteAdapterPosition() == -1 && this.adapter.getCurrentDataSource() == 0) {
            viewHolder.itemView.setAlpha(0.0f);
        }
        if (this.adapter.getCurrentDataSource() == 1) {
            PanelRowItemState panelRowItemStatePanelState = panelState(viewHolder);
            panelRowItemStatePanelState.setRemoveStaggerPosition(Integer.valueOf(resolveHolderStaggerPosition(viewHolder)));
            panelRowItemStatePanelState.setRemoveStartTranslationY(Float.valueOf(viewHolder.itemView.getTranslationY()));
        }
        resetAnimation$3(viewHolder);
        this.mPendingRemovals.add(viewHolder);
        return true;
    }

    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<? extends Object> list) {
        return (list.isEmpty() && ((SimpleItemAnimator) this).mSupportsChangeAnimations && !viewHolder.isInvalid()) ? false : true;
    }

    public final void cancelAll(List<? extends RecyclerView.ViewHolder> list) {
        View view;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            RecyclerView.ViewHolder viewHolder = list.get(size);
            if (viewHolder != null && (view = viewHolder.itemView) != null && (viewPropertyAnimatorAnimate = view.animate()) != null) {
                viewPropertyAnimatorAnimate.cancel();
            }
            if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    public final void dispatchFinishedWhenDone() {
        if (isRunning()) {
            return;
        }
        if (!this.panelHandoffController.shouldDeferResetInterruptCollapse(!this.mPendingRemovals.isEmpty(), !this.mRemoveAnimations.isEmpty())) {
            this.panelHandoffController.resetInterruptState();
        }
        dispatchAnimationsFinished();
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.mPendingMoves.get(size).getHolder() == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    this.mPendingMoves.remove(size);
                }
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        endChangeAnimation$3(viewHolder, this.mPendingChanges);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        int size2 = this.mChangesList.size() - 1;
        if (size2 >= 0) {
            while (true) {
                int i2 = size2 - 1;
                ArrayList<ChangeInfo> arrayList = this.mChangesList.get(size2);
                endChangeAnimation$3(viewHolder, arrayList);
                if (arrayList.isEmpty()) {
                    this.mChangesList.remove(size2);
                }
                if (i2 < 0) {
                    break;
                } else {
                    size2 = i2;
                }
            }
        }
        int size3 = this.mMovesList.size() - 1;
        if (size3 >= 0) {
            while (true) {
                int i3 = size3 - 1;
                ArrayList<MoveInfo> arrayList2 = this.mMovesList.get(size3);
                int size4 = arrayList2.size() - 1;
                if (size4 >= 0) {
                    while (true) {
                        int i4 = size4 - 1;
                        if (arrayList2.get(size4).getHolder() == viewHolder) {
                            view.setTranslationY(0.0f);
                            view.setTranslationX(0.0f);
                            dispatchAnimationFinished(viewHolder);
                            arrayList2.remove(size4);
                            if (!arrayList2.isEmpty()) {
                                break;
                            }
                            this.mMovesList.remove(size3);
                            break;
                        }
                        if (i4 < 0) {
                            break;
                        } else {
                            size4 = i4;
                        }
                    }
                }
                if (i3 < 0) {
                    break;
                } else {
                    size3 = i3;
                }
            }
        }
        int size5 = this.mAdditionsList.size() - 1;
        if (size5 >= 0) {
            while (true) {
                int i5 = size5 - 1;
                ArrayList<RecyclerView.ViewHolder> arrayList3 = this.mAdditionsList.get(size5);
                if (arrayList3.remove(viewHolder)) {
                    view.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder);
                    if (arrayList3.isEmpty()) {
                        this.mAdditionsList.remove(size5);
                    }
                }
                if (i5 < 0) {
                    break;
                } else {
                    size5 = i5;
                }
            }
        }
        dispatchFinishedWhenDone();
    }

    public final void endAnimations() {
        VolumeListAdapter volumeListAdapter = this.adapter;
        volumeListAdapter.isMediaRefreshLayout = false;
        volumeListAdapter.pendingMediaRefresh = false;
        volumeListAdapter.pendingMediaRefreshToken++;
        this.panelHandoffController.clearPendingPanelExpand(false, false);
        int size = this.mPendingRemovals.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                dispatchAnimationFinished(this.mPendingRemovals.get(size));
                this.mPendingRemovals.remove(size);
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        if (isRunning()) {
            this.panelHandoffController.clearPendingPanelExpand(false, true);
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            for (PanelRowItemState panelRowItemState : this.mPanelItemStateMap.values()) {
                panelRowItemState.setRemoveStaggerPosition((Integer) null);
                panelRowItemState.setRemoveStartTranslationY((Float) null);
            }
            this.mPanelItemStateMap.clear();
            this.panelHandoffController.resetInterruptState();
            dispatchAnimationsFinished();
        }
    }

    public final void endChangeAnimation$3(RecyclerView.ViewHolder viewHolder, List list) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.getOldHolder() == null && changeInfo.getNewHolder() == null) {
                list.remove(changeInfo);
            }
            if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    public final boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeInfo.getNewHolder() == viewHolder) {
            changeInfo.setNewHolder((RecyclerView.ViewHolder) null);
        } else {
            if (changeInfo.getOldHolder() != viewHolder) {
                return false;
            }
            changeInfo.setOldHolder((RecyclerView.ViewHolder) null);
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    public final VolumeListAdapter getAdapter() {
        return this.adapter;
    }

    public final float getEndX(RecyclerView.ViewHolder viewHolder) {
        float fDp2Px;
        int iResolveHolderStaggerPosition = resolveHolderStaggerPosition(viewHolder);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusVolumeKeyInRight()) {
            fDp2Px = (((this.adapter.getItemCount() * viewHolder.itemView.getWidth()) * 0.5f) + (getPaddingX(viewHolder) + VolumeLayoutUtil.dp2Px((iResolveHolderStaggerPosition * 20.0f) + (((1.0f - this.adapter.getItemCount()) * 20.0f) * 0.5f), viewHolder.itemView.getContext()))) - (VolumeLayoutUtil.getVolumePanelWidth(viewHolder) * 0.5f);
        } else {
            fDp2Px = VolumeLayoutUtil.dp2Px(iResolveHolderStaggerPosition * 20.0f, viewHolder.itemView.getContext()) + ((((VolumeLayoutUtil.getVolumePanelWidth(viewHolder) * 0.5f) - getPaddingX(viewHolder)) + VolumeLayoutUtil.dp2Px(((1.0f - this.adapter.getItemCount()) * 20.0f) * 0.5f, viewHolder.itemView.getContext())) - ((this.adapter.getItemCount() * viewHolder.itemView.getWidth()) * 0.5f));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "VolumeItemAnimator-->".concat("getEndX endX:" + fDp2Px + " position: " + iResolveHolderStaggerPosition + " "));
        }
        return fDp2Px;
    }

    public final ArrayList<RecyclerView.ViewHolder> getMAddAnimations() {
        return this.mAddAnimations;
    }

    public final ArrayList<ArrayList<RecyclerView.ViewHolder>> getMAdditionsList() {
        return this.mAdditionsList;
    }

    public final ArrayList<RecyclerView.ViewHolder> getMChangeAnimations() {
        return this.mChangeAnimations;
    }

    public final ArrayList<ArrayList<ChangeInfo>> getMChangesList() {
        return this.mChangesList;
    }

    public final ArrayList<RecyclerView.ViewHolder> getMMoveAnimations() {
        return this.mMoveAnimations;
    }

    public final ArrayList<ArrayList<MoveInfo>> getMMovesList() {
        return this.mMovesList;
    }

    public final ArrayList<RecyclerView.ViewHolder> getMRemoveAnimations() {
        return this.mRemoveAnimations;
    }

    public final PanelTransitionSession getPanelTransitionSession() {
        return this.panelTransitionSession;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004b  */
    /* JADX WARN: Code duplicated, block: B:13:0x0050  */
    /* JADX WARN: Code duplicated, block: B:15:0x005a  */
    /* JADX WARN: Code duplicated, block: B:18:0x0060  */
    /* JADX WARN: Code duplicated, block: B:20:0x0066  */
    /* JADX WARN: Code duplicated, block: B:22:0x0070  */
    /* JADX WARN: Code duplicated, block: B:27:0x0083 A[EDGE_INSN: B:27:0x0083->B:58:0x00ee BREAK  A[LOOP:0: B:19:0x0064->B:26:0x0080]] */
    /* JADX WARN: Code duplicated, block: B:28:0x0086  */
    /* JADX WARN: Code duplicated, block: B:30:0x0090  */
    /* JADX WARN: Code duplicated, block: B:31:0x0093  */
    /* JADX WARN: Code duplicated, block: B:33:0x0096  */
    /* JADX WARN: Code duplicated, block: B:34:0x009b  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:44:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:50:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:56:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:64:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x007b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x003a  */
    /* JADX WARN: Code duplicated, block: B:9:0x0043  */
    /* JADX WARN: Instruction removed from duplicated block: B:7:0x003a, please report this as an issue */
    public final float getTranslationY(RecyclerView.ViewHolder viewHolder) {
        int i;
        RecyclerView parent;
        RecyclerView recyclerView;
        ViewParent parent2;
        ConstraintLayout constraintLayout;
        RecyclerView recyclerView2;
        float volumePanelHeight;
        float fPanelBiasTranslationYFromCache;
        int childCount;
        RecyclerView.ViewHolder childViewHolder;
        float translationY;
        Context context = viewHolder.itemView.getContext();
        context.getClass();
        if (VolumeLayoutUtil.isLandscape(context)) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                i = 0;
                if (this.adapter.isMediaRefreshLayout) {
                    parent = viewHolder.itemView.getParent();
                    if (parent instanceof RecyclerView) {
                        recyclerView = parent;
                    } else {
                        recyclerView = null;
                    }
                    if (recyclerView == null) {
                        fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                    } else {
                        parent2 = recyclerView.getParent();
                        if (parent2 instanceof ConstraintLayout) {
                            constraintLayout = (ConstraintLayout) parent2;
                        } else {
                            constraintLayout = null;
                        }
                        if (constraintLayout == null) {
                            fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                        } else {
                            RecyclerView layoutParams = recyclerView.getLayoutParams();
                            recyclerView2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
                            if (recyclerView2 == null) {
                                fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                            } else {
                                int height = ((constraintLayout.getHeight() - recyclerView.getHeight()) - ((ViewGroup.MarginLayoutParams) recyclerView2).topMargin) - ((ViewGroup.MarginLayoutParams) recyclerView2).bottomMargin;
                                i = height >= 0 ? height : 0;
                                if (i == 0) {
                                    volumePanelHeight = 0.0f;
                                } else {
                                    volumePanelHeight = i * 0.22f;
                                    if (volumePanelHeight < 0.0f) {
                                        volumePanelHeight = 0.0f;
                                    }
                                }
                                if (volumePanelHeight > 0.0f) {
                                    this.cachedPanelBiasTranslationY = volumePanelHeight;
                                } else {
                                    fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                                }
                            }
                        }
                    }
                    volumePanelHeight = fPanelBiasTranslationYFromCache;
                } else if (Float.isNaN(this.cachedPanelBiasTranslationY)) {
                    RecyclerView parent3 = viewHolder.itemView.getParent();
                    recyclerView2 = parent3 instanceof RecyclerView ? parent3 : null;
                    if (recyclerView2 == null) {
                        volumePanelHeight = 0.0f;
                        break;
                    }
                    childCount = recyclerView2.getChildCount();
                    while (true) {
                        if (i >= childCount) {
                            volumePanelHeight = 0.0f;
                            break;
                        }
                        childViewHolder = recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i));
                        if (childViewHolder != viewHolder) {
                            translationY = childViewHolder.itemView.getTranslationY();
                            if (translationY == 0.0f) {
                                this.cachedPanelBiasTranslationY = translationY;
                                volumePanelHeight = translationY;
                                break;
                            }
                        }
                        i++;
                    }
                } else {
                    fPanelBiasTranslationYFromCache = this.cachedPanelBiasTranslationY;
                    volumePanelHeight = fPanelBiasTranslationYFromCache;
                }
            } else {
                volumePanelHeight = ((VolumeLayoutUtil.getVolumePanelHeight(viewHolder) - viewHolder.itemView.getHeight()) * 0.5f) - (VolumeLayoutUtil.getYLocationOnScreen(viewHolder.itemView) - viewHolder.itemView.getTranslationY());
            }
        } else {
            i = 0;
            if (this.adapter.isMediaRefreshLayout) {
                parent = viewHolder.itemView.getParent();
                if (parent instanceof RecyclerView) {
                    recyclerView = parent;
                } else {
                    recyclerView = null;
                }
                if (recyclerView == null) {
                    fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                } else {
                    parent2 = recyclerView.getParent();
                    if (parent2 instanceof ConstraintLayout) {
                        constraintLayout = (ConstraintLayout) parent2;
                    } else {
                        constraintLayout = null;
                    }
                    if (constraintLayout == null) {
                        fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                    } else {
                        RecyclerView layoutParams2 = recyclerView.getLayoutParams();
                        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
                        }
                        if (recyclerView2 == null) {
                            fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                        } else {
                            int height2 = ((constraintLayout.getHeight() - recyclerView.getHeight()) - ((ViewGroup.MarginLayoutParams) recyclerView2).topMargin) - ((ViewGroup.MarginLayoutParams) recyclerView2).bottomMargin;
                            if (height2 >= 0) {
                            }
                            if (i == 0) {
                                volumePanelHeight = 0.0f;
                            } else {
                                volumePanelHeight = i * 0.22f;
                                if (volumePanelHeight < 0.0f) {
                                    volumePanelHeight = 0.0f;
                                }
                            }
                            if (volumePanelHeight > 0.0f) {
                                this.cachedPanelBiasTranslationY = volumePanelHeight;
                            } else {
                                fPanelBiasTranslationYFromCache = panelBiasTranslationYFromCache();
                            }
                        }
                    }
                }
                volumePanelHeight = fPanelBiasTranslationYFromCache;
            } else if (Float.isNaN(this.cachedPanelBiasTranslationY)) {
                fPanelBiasTranslationYFromCache = this.cachedPanelBiasTranslationY;
                volumePanelHeight = fPanelBiasTranslationYFromCache;
            } else {
                RecyclerView parent4 = viewHolder.itemView.getParent();
                if (parent4 instanceof RecyclerView) {
                }
                if (recyclerView2 == null) {
                    volumePanelHeight = 0.0f;
                    break;
                }
                childCount = recyclerView2.getChildCount();
                while (true) {
                    if (i >= childCount) {
                        volumePanelHeight = 0.0f;
                        break;
                    }
                    childViewHolder = recyclerView2.getChildViewHolder(recyclerView2.getChildAt(i));
                    if (childViewHolder != viewHolder) {
                        translationY = childViewHolder.itemView.getTranslationY();
                        if (translationY == 0.0f) {
                            this.cachedPanelBiasTranslationY = translationY;
                            volumePanelHeight = translationY;
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "VolumeItemAnimator-->".concat("getTranslationY y:" + volumePanelHeight + " position: " + viewHolder.getAbsoluteAdapterPosition() + " "));
        }
        return volumePanelHeight;
    }

    public final void handoffExpandForCollapse(RecyclerView recyclerView) {
        this.panelHandoffController.handoffExpandForCollapse(recyclerView);
        PanelTransitionSession panelTransitionSession = this.panelTransitionSession;
        if (panelTransitionSession != null) {
            panelTransitionSession.markCollapsing();
        }
    }

    public final boolean hasPanelCollapseContext(RecyclerView.ViewHolder viewHolder) {
        PanelRowItemState panelRowItemStatePanelStateOrNull = panelStateOrNull(viewHolder);
        return (panelRowItemStatePanelStateOrNull != null ? panelRowItemStatePanelStateOrNull.getEntranceType() : null) != null || isNonNormalPanelCollapse();
    }

    public final boolean hasPanelExpandStarted() {
        Iterator<PanelRowItemState> it = this.mPanelItemStateMap.values().iterator();
        while (it.hasNext()) {
            if (it.next().getExpandStarted()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isNonNormalPanelCollapse() {
        PanelTransitionSession panelTransitionSession = this.panelTransitionSession;
        return panelTransitionSession != null && (panelTransitionSession.isNormalPanelCollapseMode() ^ true);
    }

    public final boolean isPanelCollapsePhaseActive() {
        PanelTransitionSession panelTransitionSession = this.panelTransitionSession;
        PanelTransitionSession.Phase phase = panelTransitionSession != null ? panelTransitionSession.getPhase() : null;
        int i = phase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[phase.ordinal()];
        return i == 1 || i == 2;
    }

    public final boolean isPanelExpandRunning() {
        if (!isPanelCollapsePhaseActive() && this.adapter.getCurrentDataSource() == 1) {
            return (this.mPendingAdditions.isEmpty() && this.mPendingMoves.isEmpty() && this.mAddAnimations.isEmpty() && this.mMoveAnimations.isEmpty() && this.mAdditionsList.isEmpty() && this.mMovesList.isEmpty()) ? false : true;
        }
        return false;
    }

    public final boolean isRunning() {
        return (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) ? false : true;
    }

    public final float panelBiasTranslationYFromCache() {
        if (Float.isNaN(this.cachedPanelBiasTranslationY)) {
            return 0.0f;
        }
        return this.cachedPanelBiasTranslationY;
    }

    public final PanelRowItemState panelState(RecyclerView.ViewHolder viewHolder) {
        HashMap<RecyclerView.ViewHolder, PanelRowItemState> map = this.mPanelItemStateMap;
        PanelRowItemState panelRowItemState = map.get(viewHolder);
        if (panelRowItemState == null) {
            PanelRowItemState panelRowItemState2 = new PanelRowItemState((PanelEntranceType) null, (Integer) null, (Float) null, false, (Integer) null, (Float) null, (PanelHandoffPose) null);
            map.put(viewHolder, panelRowItemState2);
            panelRowItemState = panelRowItemState2;
        }
        return panelRowItemState;
    }

    public final PanelRowItemState panelStateOrNull(RecyclerView.ViewHolder viewHolder) {
        return this.mPanelItemStateMap.get(viewHolder);
    }

    public final void resetAnimation$3(RecyclerView.ViewHolder viewHolder) {
        if (this.sDefaultInterpolator == null) {
            this.sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(this.sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    public final long resolvePanelCollapseDurationMs(int i, boolean z) {
        PanelTransitionSession panelTransitionSession = this.panelTransitionSession;
        if (panelTransitionSession == null || !panelTransitionSession.getUseLowLevelPanelHideTiming()) {
            Context context = this.adapter.getContext();
            PanelTransitionSession panelTransitionSession2 = this.panelTransitionSession;
            return OplusVolumePanelTiming.resolvePanelCollapseItemDurationMs(context, i, z, panelTransitionSession2 != null ? panelTransitionSession2.getInterruptDurationRatio() : 1.0f);
        }
        PanelTransitionSession panelTransitionSession3 = this.panelTransitionSession;
        float interruptDurationRatio = panelTransitionSession3 != null ? panelTransitionSession3.getInterruptDurationRatio() : 1.0f;
        long jMax = Math.max(100L, (((long) (650 - (i * 100))) * 2) / 3);
        if (!z) {
            if (jMax < 0) {
                return 0L;
            }
            return jMax;
        }
        long j = (long) (jMax * interruptDurationRatio);
        if (j < 300) {
            j = 300;
        }
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    public final int resolvePanelCollapseShowIndex(RecyclerView.ViewHolder viewHolder, int i) {
        Integer removeStaggerPosition;
        Integer expandShowIndex;
        PanelRowItemState panelRowItemStatePanelStateOrNull = panelStateOrNull(viewHolder);
        if (panelRowItemStatePanelStateOrNull != null && (expandShowIndex = panelRowItemStatePanelStateOrNull.getExpandShowIndex()) != null) {
            return expandShowIndex.intValue();
        }
        PanelRowItemState panelRowItemStatePanelStateOrNull2 = panelStateOrNull(viewHolder);
        int iCoerceIn = (panelRowItemStatePanelStateOrNull2 == null || (removeStaggerPosition = panelRowItemStatePanelStateOrNull2.getRemoveStaggerPosition()) == null) ? RangesKt___RangesKt.coerceIn(Math.max(0, viewHolder.getOldPosition()), 0, i - 1) : RangesKt___RangesKt.coerceIn(removeStaggerPosition.intValue(), 0, i - 1);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusVolumeKeyInRight()) {
            return iCoerceIn;
        }
        int i2 = (i - 1) - iCoerceIn;
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public final int resolvePanelExpandTotalCount(int i) {
        int iMax = Math.max(this.adapter.getItemCount(), i);
        if (iMax < 1) {
            return 1;
        }
        return iMax;
    }

    /* JADX WARN: Code duplicated, block: B:126:0x0202  */
    /* JADX WARN: Code duplicated, block: B:129:0x0215 A[LOOP:2: B:125:0x0200->B:129:0x0215, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:132:0x0221  */
    /* JADX WARN: Code duplicated, block: B:137:0x023a  */
    /* JADX WARN: Code duplicated, block: B:139:0x0245  */
    /* JADX WARN: Code duplicated, block: B:142:0x0257 A[LOOP:4: B:138:0x0243->B:142:0x0257, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:146:0x0267  */
    /* JADX WARN: Code duplicated, block: B:153:0x0284  */
    /* JADX WARN: Code duplicated, block: B:154:0x0286  */
    /* JADX WARN: Code duplicated, block: B:158:0x0292 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:159:0x0294  */
    /* JADX WARN: Code duplicated, block: B:162:0x029f  */
    /* JADX WARN: Code duplicated, block: B:163:0x02a4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:164:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:165:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:168:0x02af  */
    /* JADX WARN: Code duplicated, block: B:172:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:174:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:176:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:178:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:179:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:183:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:187:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:194:0x0314  */
    /* JADX WARN: Code duplicated, block: B:195:0x031b  */
    /* JADX WARN: Code duplicated, block: B:24:0x0091  */
    /* JADX WARN: Code duplicated, block: B:282:0x0218 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:283:0x01c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:284:0x01a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:285:0x01c7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:287:0x025a A[SYNTHETIC] */
    public final void runPendingAnimations() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        ArrayList arrayList;
        float f;
        PanelTransitionSession panelTransitionSession;
        float fResolvePanelStartTranslationX;
        boolean z5;
        ArrayList<ArrayList<RecyclerView.ViewHolder>> arrayList2;
        int size;
        int i;
        ArrayList<ArrayList<MoveInfo>> arrayList3;
        int size2;
        int i2;
        ArrayList<MoveInfo> arrayList4;
        int size3;
        int i3;
        MoveInfo moveInfo;
        ArrayList<MoveInfo> arrayList5;
        ArrayList<RecyclerView.ViewHolder> arrayList6;
        ArrayList<ArrayList<RecyclerView.ViewHolder>> arrayList7;
        COUISpringInterpolator volume_item_collapse_interpolator;
        PanelRowItemState panelRowItemStatePanelStateOrNull;
        PanelHandoffPose handoffPose;
        PanelRowItemState panelRowItemStatePanelStateOrNull2;
        Float removeStartTranslationY;
        float f2;
        float translationY;
        float translationY2;
        Float removeStartTranslationY2;
        PanelHandoffPose handoffPose2;
        PanelRowItemState panelRowItemStatePanelStateOrNull3;
        PanelHandoffPose handoffPose3;
        PanelRowItemState panelRowItemStatePanelStateOrNull4;
        PanelHandoffPose handoffPose4;
        Float startTranslationX;
        boolean zIsEmpty = this.mPendingRemovals.isEmpty();
        boolean zIsEmpty2 = this.mPendingMoves.isEmpty();
        boolean zIsEmpty3 = this.mPendingChanges.isEmpty();
        boolean zIsEmpty4 = this.mPendingAdditions.isEmpty();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty4 && zIsEmpty3) {
            return;
        }
        VolumeListAdapter volumeListAdapter = this.adapter;
        int i4 = 1;
        if (volumeListAdapter.isMediaRefreshLayout && volumeListAdapter.getCurrentDataSource() == 1) {
            this.panelMediaRefreshAnimator.consumePendingAndPlay(this.adapter.getHostRecyclerView$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(), this.mPendingRemovals, this.mPendingMoves, this.mPendingAdditions, this.mPendingChanges);
            return;
        }
        ArrayList arrayList8 = new ArrayList(this.mPendingRemovals);
        int size4 = arrayList8.size();
        int i5 = size4 < 1 ? 1 : size4;
        int size5 = arrayList8.size();
        int i6 = 0;
        int i7 = 0;
        while (i7 < size5) {
            int i8 = i7 + 1;
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) arrayList8.get(i7);
            viewHolder.getClass();
            View view = viewHolder.itemView;
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
            long j = 0;
            this.mRemoveAnimations.add(viewHolder);
            if (this.adapter.getCurrentDataSource() == i4) {
                this.panelHandoffController.ensureCollapseRemoving();
                ViewGroup viewGroup = (ViewGroup) viewHolder.itemView.findViewById(2131365996);
                if (viewGroup != null && viewGroup.getChildCount() > 0) {
                    View childAt = viewGroup.getChildAt(i6);
                    ArrayList<OplusVolumeRow> panelShowList = this.adapter.getPanelShowList();
                    if (panelShowList == null || !panelShowList.isEmpty()) {
                        int size6 = panelShowList.size();
                        int i9 = 0;
                        while (true) {
                            if (i9 < size6) {
                                OplusVolumeRow oplusVolumeRow = panelShowList.get(i9);
                                i9++;
                                ArrayList<OplusVolumeRow> arrayList9 = panelShowList;
                                if (oplusVolumeRow.getView() == childAt) {
                                    PanelTransitionSession panelTransitionSession2 = this.panelTransitionSession;
                                    boolean zHasPanelCollapseContext = ((panelTransitionSession2 != null ? panelTransitionSession2.getPhase() : null) == PanelTransitionSession.Phase.EXPANDING || (panelTransitionSession = this.panelTransitionSession) == null || !panelTransitionSession.isNormalPanelCollapseMode()) ? hasPanelCollapseContext(viewHolder) : true;
                                    if (zHasPanelCollapseContext) {
                                        viewPropertyAnimatorAnimate.getClass();
                                        PanelRowItemState panelRowItemStatePanelStateOrNull5 = panelStateOrNull(viewHolder);
                                        float translationX = (panelRowItemStatePanelStateOrNull5 == null || (startTranslationX = panelRowItemStatePanelStateOrNull5.getStartTranslationX()) == null) ? view.getTranslationX() : startTranslationX.floatValue();
                                        int iMax = Math.max(this.adapter.getItemCount(), i5);
                                        if (iMax < 1) {
                                            iMax = 1;
                                        }
                                        int iResolvePanelCollapseShowIndex = resolvePanelCollapseShowIndex(viewHolder, iMax);
                                        if (!isNonNormalPanelCollapse() || (panelRowItemStatePanelStateOrNull4 = panelStateOrNull(viewHolder)) == null || (handoffPose4 = panelRowItemStatePanelStateOrNull4.getHandoffPose()) == null) {
                                            PanelRowItemState panelRowItemStatePanelStateOrNull6 = panelStateOrNull(viewHolder);
                                            PanelEntranceType entranceType = panelRowItemStatePanelStateOrNull6 != null ? panelRowItemStatePanelStateOrNull6.getEntranceType() : null;
                                            int i10 = entranceType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[entranceType.ordinal()];
                                            if (i10 == -1) {
                                                fResolvePanelStartTranslationX = translationX;
                                            } else if (i10 != 1) {
                                                if (i10 != 2) {
                                                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                                                    return;
                                                }
                                                fResolvePanelStartTranslationX = translationX;
                                            } else {
                                                fResolvePanelStartTranslationX = resolvePanelStartTranslationX(viewHolder);
                                            }
                                        } else {
                                            fResolvePanelStartTranslationX = handoffPose4.getCollapseTargetTranslationX();
                                        }
                                        z = zIsEmpty;
                                        z2 = zIsEmpty2;
                                        long jResolvePanelCollapseDurationMs = resolvePanelCollapseDurationMs(iResolvePanelCollapseShowIndex, isNonNormalPanelCollapse());
                                        if (isNonNormalPanelCollapse()) {
                                            z3 = zIsEmpty3;
                                            z4 = zIsEmpty4;
                                        } else {
                                            int i11 = (iMax - 1) - iResolvePanelCollapseShowIndex;
                                            if (i11 < 0) {
                                                i11 = 0;
                                            }
                                            z3 = zIsEmpty3;
                                            z4 = zIsEmpty4;
                                            long j2 = ((long) i11) * 15;
                                            if (j2 >= 0) {
                                                j = j2;
                                            }
                                        }
                                        long j3 = j;
                                        PanelTransitionSession panelTransitionSession3 = this.panelTransitionSession;
                                        if (panelTransitionSession3 != null) {
                                            z5 = true;
                                            if (panelTransitionSession3.isNormalPanelCollapseMode()) {
                                                arrayList = arrayList8;
                                                if (isNonNormalPanelCollapse() && (panelRowItemStatePanelStateOrNull3 = panelStateOrNull(viewHolder)) != null && (handoffPose3 = panelRowItemStatePanelStateOrNull3.getHandoffPose()) != null) {
                                                    view.setTranslationY(handoffPose3.getCollapseStartTranslationY());
                                                }
                                                panelRowItemStatePanelStateOrNull2 = panelStateOrNull(viewHolder);
                                                if (isNonNormalPanelCollapse()) {
                                                    if (panelRowItemStatePanelStateOrNull2 == null && (handoffPose2 = panelRowItemStatePanelStateOrNull2.getHandoffPose()) != null) {
                                                        translationY2 = handoffPose2.getCollapseStartTranslationY();
                                                    } else if (panelRowItemStatePanelStateOrNull2 != null || (removeStartTranslationY2 = panelRowItemStatePanelStateOrNull2.getRemoveStartTranslationY()) == null) {
                                                        translationY2 = view.getTranslationY();
                                                    } else {
                                                        translationY2 = removeStartTranslationY2.floatValue();
                                                    }
                                                    f2 = 0.0f;
                                                } else {
                                                    if (panelRowItemStatePanelStateOrNull2 != null) {
                                                        removeStartTranslationY = panelRowItemStatePanelStateOrNull2.getRemoveStartTranslationY();
                                                    } else {
                                                        removeStartTranslationY = null;
                                                    }
                                                    f2 = 0.0f;
                                                    if (removeStartTranslationY == null && !Intrinsics.areEqual(removeStartTranslationY, 0.0f)) {
                                                        translationY = removeStartTranslationY.floatValue();
                                                    } else if (view.getTranslationY() == 0.0f) {
                                                        translationY = getTranslationY(viewHolder);
                                                        if (translationY == 0.0f) {
                                                            translationY = panelBiasTranslationYFromCache();
                                                            if (translationY == 0.0f) {
                                                                translationY2 = 0.0f;
                                                            }
                                                        }
                                                    } else {
                                                        translationY = view.getTranslationY();
                                                    }
                                                    translationY2 = translationY;
                                                }
                                                if (translationY2 != f2) {
                                                    view.setTranslationY(translationY2);
                                                    viewPropertyAnimatorAnimate.translationY(f2);
                                                }
                                            }
                                            if (isNonNormalPanelCollapse() && (panelRowItemStatePanelStateOrNull = panelStateOrNull(viewHolder)) != null && (handoffPose = panelRowItemStatePanelStateOrNull.getHandoffPose()) != null) {
                                                view.setTranslationX(handoffPose.getCollapseStartTranslationX());
                                            }
                                            viewPropertyAnimatorAnimate.translationX(fResolvePanelStartTranslationX);
                                            ViewPropertyAnimator startDelay = viewPropertyAnimatorAnimate.setDuration(jResolvePanelCollapseDurationMs).setStartDelay(j3);
                                            if (isNonNormalPanelCollapse()) {
                                                volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR();
                                            } else {
                                                volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_COLLAPSE_INTERPOLATOR();
                                            }
                                            startDelay.setInterpolator(volume_item_collapse_interpolator);
                                        } else {
                                            z5 = true;
                                        }
                                        PanelRowItemState panelRowItemStatePanelStateOrNull7 = panelStateOrNull(viewHolder);
                                        if (panelRowItemStatePanelStateOrNull7 == null || panelRowItemStatePanelStateOrNull7.getExpandStarted() != z5 || this.mAddAnimations.contains(viewHolder) || this.mMoveAnimations.contains(viewHolder) || this.mPendingAdditions.contains(viewHolder)) {
                                            arrayList = arrayList8;
                                        } else {
                                            ArrayList<MoveInfo> arrayList10 = this.mPendingMoves;
                                            if (arrayList10 == null || !arrayList10.isEmpty()) {
                                                int size7 = arrayList10.size();
                                                int i12 = 0;
                                                while (true) {
                                                    if (i12 < size7) {
                                                        MoveInfo moveInfo2 = arrayList10.get(i12);
                                                        i12++;
                                                        ArrayList<MoveInfo> arrayList11 = arrayList10;
                                                        if (moveInfo2.getHolder() != viewHolder) {
                                                            arrayList10 = arrayList11;
                                                        }
                                                    } else {
                                                        arrayList2 = this.mAdditionsList;
                                                        size = arrayList2.size();
                                                        i = 0;
                                                        while (true) {
                                                            if (i < size) {
                                                                arrayList6 = arrayList2.get(i);
                                                                i++;
                                                                arrayList7 = arrayList2;
                                                                if (arrayList6.contains(viewHolder)) {
                                                                    arrayList2 = arrayList7;
                                                                }
                                                            } else {
                                                                arrayList3 = this.mMovesList;
                                                                size2 = arrayList3.size();
                                                                i2 = 0;
                                                                while (true) {
                                                                    if (i2 < size2) {
                                                                        ArrayList<MoveInfo> arrayList12 = arrayList3.get(i2);
                                                                        i2++;
                                                                        ArrayList<ArrayList<MoveInfo>> arrayList13 = arrayList3;
                                                                        arrayList4 = arrayList12;
                                                                        if (arrayList4 == null && arrayList4.isEmpty()) {
                                                                            arrayList = arrayList8;
                                                                        } else {
                                                                            size3 = arrayList4.size();
                                                                            arrayList = arrayList8;
                                                                            i3 = 0;
                                                                            while (true) {
                                                                                if (i3 < size3) {
                                                                                    moveInfo = arrayList4.get(i3);
                                                                                    i3++;
                                                                                    arrayList5 = arrayList4;
                                                                                    if (moveInfo.getHolder() != viewHolder) {
                                                                                        arrayList4 = arrayList5;
                                                                                    }
                                                                                } else {
                                                                                    continue;
                                                                                }
                                                                            }
                                                                        }
                                                                        size2 = size2;
                                                                        arrayList3 = arrayList13;
                                                                        arrayList8 = arrayList;
                                                                    } else {
                                                                        arrayList = arrayList8;
                                                                        if (isNonNormalPanelCollapse()) {
                                                                            view.setTranslationY(handoffPose3.getCollapseStartTranslationY());
                                                                        }
                                                                        panelRowItemStatePanelStateOrNull2 = panelStateOrNull(viewHolder);
                                                                        if (isNonNormalPanelCollapse()) {
                                                                            if (panelRowItemStatePanelStateOrNull2 == null) {
                                                                                if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                                    translationY2 = view.getTranslationY();
                                                                                } else {
                                                                                    translationY2 = view.getTranslationY();
                                                                                }
                                                                            } else if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                                translationY2 = view.getTranslationY();
                                                                            } else {
                                                                                translationY2 = view.getTranslationY();
                                                                            }
                                                                            f2 = 0.0f;
                                                                        } else {
                                                                            if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                                removeStartTranslationY = panelRowItemStatePanelStateOrNull2.getRemoveStartTranslationY();
                                                                            } else {
                                                                                removeStartTranslationY = null;
                                                                            }
                                                                            f2 = 0.0f;
                                                                            if (removeStartTranslationY == null) {
                                                                                if (view.getTranslationY() == 0.0f) {
                                                                                    translationY = getTranslationY(viewHolder);
                                                                                    if (translationY == 0.0f) {
                                                                                        translationY = panelBiasTranslationYFromCache();
                                                                                        if (translationY == 0.0f) {
                                                                                            translationY2 = 0.0f;
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    translationY = view.getTranslationY();
                                                                                }
                                                                                translationY2 = translationY;
                                                                            } else {
                                                                                if (view.getTranslationY() == 0.0f) {
                                                                                    translationY = getTranslationY(viewHolder);
                                                                                    if (translationY == 0.0f) {
                                                                                        translationY = panelBiasTranslationYFromCache();
                                                                                        if (translationY == 0.0f) {
                                                                                            translationY2 = 0.0f;
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    translationY = view.getTranslationY();
                                                                                }
                                                                                translationY2 = translationY;
                                                                            }
                                                                        }
                                                                        if (translationY2 != f2) {
                                                                            view.setTranslationY(translationY2);
                                                                            viewPropertyAnimatorAnimate.translationY(f2);
                                                                        }
                                                                        if (isNonNormalPanelCollapse()) {
                                                                            view.setTranslationX(handoffPose.getCollapseStartTranslationX());
                                                                        }
                                                                        viewPropertyAnimatorAnimate.translationX(fResolvePanelStartTranslationX);
                                                                        ViewPropertyAnimator startDelay2 = viewPropertyAnimatorAnimate.setDuration(jResolvePanelCollapseDurationMs).setStartDelay(j3);
                                                                        if (isNonNormalPanelCollapse()) {
                                                                            volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR();
                                                                        } else {
                                                                            volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_COLLAPSE_INTERPOLATOR();
                                                                        }
                                                                        startDelay2.setInterpolator(volume_item_collapse_interpolator);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    arrayList = arrayList8;
                                                }
                                            } else {
                                                arrayList2 = this.mAdditionsList;
                                                size = arrayList2.size();
                                                i = 0;
                                                while (true) {
                                                    if (i < size) {
                                                        arrayList6 = arrayList2.get(i);
                                                        i++;
                                                        arrayList7 = arrayList2;
                                                        if (arrayList6.contains(viewHolder)) {
                                                            arrayList2 = arrayList7;
                                                        } else {
                                                            arrayList = arrayList8;
                                                        }
                                                    } else {
                                                        arrayList3 = this.mMovesList;
                                                        size2 = arrayList3.size();
                                                        i2 = 0;
                                                        while (true) {
                                                            if (i2 < size2) {
                                                                ArrayList<MoveInfo> arrayList14 = arrayList3.get(i2);
                                                                i2++;
                                                                ArrayList<ArrayList<MoveInfo>> arrayList15 = arrayList3;
                                                                arrayList4 = arrayList14;
                                                                if (arrayList4 == null) {
                                                                    size3 = arrayList4.size();
                                                                    arrayList = arrayList8;
                                                                    i3 = 0;
                                                                    while (true) {
                                                                        if (i3 < size3) {
                                                                            moveInfo = arrayList4.get(i3);
                                                                            i3++;
                                                                            arrayList5 = arrayList4;
                                                                            if (moveInfo.getHolder() != viewHolder) {
                                                                                arrayList4 = arrayList5;
                                                                            }
                                                                        } else {
                                                                            continue;
                                                                        }
                                                                    }
                                                                } else {
                                                                    size3 = arrayList4.size();
                                                                    arrayList = arrayList8;
                                                                    i3 = 0;
                                                                    while (true) {
                                                                        if (i3 < size3) {
                                                                            moveInfo = arrayList4.get(i3);
                                                                            i3++;
                                                                            arrayList5 = arrayList4;
                                                                            if (moveInfo.getHolder() != viewHolder) {
                                                                                arrayList4 = arrayList5;
                                                                            }
                                                                        } else {
                                                                            continue;
                                                                        }
                                                                    }
                                                                }
                                                                size2 = size2;
                                                                arrayList3 = arrayList15;
                                                                arrayList8 = arrayList;
                                                            } else {
                                                                arrayList = arrayList8;
                                                                if (isNonNormalPanelCollapse()) {
                                                                    view.setTranslationY(handoffPose3.getCollapseStartTranslationY());
                                                                }
                                                                panelRowItemStatePanelStateOrNull2 = panelStateOrNull(viewHolder);
                                                                if (isNonNormalPanelCollapse()) {
                                                                    if (panelRowItemStatePanelStateOrNull2 == null) {
                                                                        if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                            translationY2 = view.getTranslationY();
                                                                        } else {
                                                                            translationY2 = view.getTranslationY();
                                                                        }
                                                                    } else if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                        translationY2 = view.getTranslationY();
                                                                    } else {
                                                                        translationY2 = view.getTranslationY();
                                                                    }
                                                                    f2 = 0.0f;
                                                                } else {
                                                                    if (panelRowItemStatePanelStateOrNull2 != null) {
                                                                        removeStartTranslationY = panelRowItemStatePanelStateOrNull2.getRemoveStartTranslationY();
                                                                    } else {
                                                                        removeStartTranslationY = null;
                                                                    }
                                                                    f2 = 0.0f;
                                                                    if (removeStartTranslationY == null) {
                                                                        if (view.getTranslationY() == 0.0f) {
                                                                            translationY = getTranslationY(viewHolder);
                                                                            if (translationY == 0.0f) {
                                                                                translationY = panelBiasTranslationYFromCache();
                                                                                if (translationY == 0.0f) {
                                                                                    translationY2 = 0.0f;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            translationY = view.getTranslationY();
                                                                        }
                                                                        translationY2 = translationY;
                                                                    } else {
                                                                        if (view.getTranslationY() == 0.0f) {
                                                                            translationY = getTranslationY(viewHolder);
                                                                            if (translationY == 0.0f) {
                                                                                translationY = panelBiasTranslationYFromCache();
                                                                                if (translationY == 0.0f) {
                                                                                    translationY2 = 0.0f;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            translationY = view.getTranslationY();
                                                                        }
                                                                        translationY2 = translationY;
                                                                    }
                                                                }
                                                                if (translationY2 != f2) {
                                                                    view.setTranslationY(translationY2);
                                                                    viewPropertyAnimatorAnimate.translationY(f2);
                                                                }
                                                                if (isNonNormalPanelCollapse()) {
                                                                    view.setTranslationX(handoffPose.getCollapseStartTranslationX());
                                                                }
                                                                viewPropertyAnimatorAnimate.translationX(fResolvePanelStartTranslationX);
                                                                ViewPropertyAnimator startDelay3 = viewPropertyAnimatorAnimate.setDuration(jResolvePanelCollapseDurationMs).setStartDelay(j3);
                                                                if (isNonNormalPanelCollapse()) {
                                                                    volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR();
                                                                } else {
                                                                    volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_COLLAPSE_INTERPOLATOR();
                                                                }
                                                                startDelay3.setInterpolator(volume_item_collapse_interpolator);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        view.setTranslationY(0.0f);
                                        if (isNonNormalPanelCollapse()) {
                                            view.setTranslationX(handoffPose.getCollapseStartTranslationX());
                                        }
                                        viewPropertyAnimatorAnimate.translationX(fResolvePanelStartTranslationX);
                                        ViewPropertyAnimator startDelay4 = viewPropertyAnimatorAnimate.setDuration(jResolvePanelCollapseDurationMs).setStartDelay(j3);
                                        if (isNonNormalPanelCollapse()) {
                                            volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR();
                                        } else {
                                            volume_item_collapse_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_COLLAPSE_INTERPOLATOR();
                                        }
                                        startDelay4.setInterpolator(volume_item_collapse_interpolator);
                                    } else {
                                        z = zIsEmpty;
                                        z2 = zIsEmpty2;
                                        z3 = zIsEmpty3;
                                        z4 = zIsEmpty4;
                                        arrayList = arrayList8;
                                        if (hasPanelCollapseContext(viewHolder)) {
                                            viewPropertyAnimatorAnimate.getClass();
                                            this.panelHandoffController.prepareUnstartedEntrance(viewHolder, UnstartedEntranceSource.REMOVE);
                                            int iMax2 = Math.max(this.adapter.getItemCount(), i5);
                                            if (iMax2 < 1) {
                                                iMax2 = 1;
                                            }
                                            viewPropertyAnimatorAnimate.setDuration(resolvePanelCollapseDurationMs(resolvePanelCollapseShowIndex(viewHolder, iMax2), isNonNormalPanelCollapse())).setStartDelay(0L).setInterpolator(isNonNormalPanelCollapse() ? VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR() : VolumePropertyAnimationUtil.INSTANCE.getVOLUME_ITEM_COLLAPSE_INTERPOLATOR());
                                        } else {
                                            f = 0.0f;
                                            view.setTranslationX(0.0f);
                                            view.setTranslationY(0.0f);
                                            viewPropertyAnimatorAnimate.setDuration(getRemoveDuration()).getClass();
                                        }
                                    }
                                    f = 0.0f;
                                } else {
                                    panelShowList = arrayList9;
                                }
                            } else {
                                z = zIsEmpty;
                                z2 = zIsEmpty2;
                                z3 = zIsEmpty3;
                                z4 = zIsEmpty4;
                                arrayList = arrayList8;
                                f = 0.0f;
                                view.setTranslationX(0.0f);
                                view.setTranslationY(0.0f);
                                viewPropertyAnimatorAnimate.setDuration(getRemoveDuration()).getClass();
                            }
                        }
                    } else {
                        z = zIsEmpty;
                        z2 = zIsEmpty2;
                        z3 = zIsEmpty3;
                        z4 = zIsEmpty4;
                        arrayList = arrayList8;
                        f = 0.0f;
                        view.setTranslationX(0.0f);
                        view.setTranslationY(0.0f);
                        viewPropertyAnimatorAnimate.setDuration(getRemoveDuration()).getClass();
                    }
                } else {
                    z = zIsEmpty;
                    z2 = zIsEmpty2;
                    z3 = zIsEmpty3;
                    z4 = zIsEmpty4;
                    arrayList = arrayList8;
                    f = 0.0f;
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    viewPropertyAnimatorAnimate.setDuration(getRemoveDuration()).getClass();
                }
            } else {
                z = zIsEmpty;
                z2 = zIsEmpty2;
                z3 = zIsEmpty3;
                z4 = zIsEmpty4;
                arrayList = arrayList8;
                f = 0.0f;
                viewPropertyAnimatorAnimate.setDuration(getRemoveDuration()).getClass();
            }
            viewPropertyAnimatorAnimate.alpha(f).setListener(new animateChangeImpl.1(this, viewHolder, viewPropertyAnimatorAnimate, view, 2)).start();
            i7 = i8;
            zIsEmpty = z;
            zIsEmpty2 = z2;
            zIsEmpty3 = z3;
            zIsEmpty4 = z4;
            arrayList8 = arrayList;
            i4 = 1;
            i6 = 0;
        }
        boolean z6 = zIsEmpty;
        boolean z7 = zIsEmpty2;
        boolean z8 = zIsEmpty3;
        boolean z9 = zIsEmpty4;
        this.mPendingRemovals.clear();
        if (!z7) {
            ArrayList<MoveInfo> arrayList16 = new ArrayList<>();
            arrayList16.addAll(this.mPendingMoves);
            this.mPendingMoves.clear();
            if (!z6 && this.adapter.getCurrentDataSource() == 1 && isPanelCollapsePhaseActive()) {
                int size8 = arrayList16.size();
                int i13 = 0;
                while (i13 < size8) {
                    MoveInfo moveInfo3 = arrayList16.get(i13);
                    i13++;
                    dispatchAnimationFinished(moveInfo3.getHolder());
                }
                arrayList16.clear();
            } else {
                this.mMovesList.add(arrayList16);
                int iResolvePanelExpandTotalCount = resolvePanelExpandTotalCount(arrayList16.size());
                VolumeItemAnimator$runPendingAnimations$adder$1 volumeItemAnimator$runPendingAnimations$adder$1 = new VolumeItemAnimator$runPendingAnimations$adder$1(1);
                volumeItemAnimator$runPendingAnimations$adder$1.$additions = arrayList16;
                volumeItemAnimator$runPendingAnimations$adder$1.this$0 = this;
                volumeItemAnimator$runPendingAnimations$adder$1.$expandStaggerTotal = iResolvePanelExpandTotalCount;
                VarHandle.storeStoreFence();
                if (z6 || this.adapter.getCurrentDataSource() == 1) {
                    volumeItemAnimator$runPendingAnimations$adder$1.run();
                } else {
                    View view2 = arrayList16.get(0).getHolder().itemView;
                    long removeDuration = getRemoveDuration();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    view2.postOnAnimationDelayed(volumeItemAnimator$runPendingAnimations$adder$1, removeDuration);
                }
            }
        }
        if (!z8) {
            ArrayList<ChangeInfo> arrayList17 = new ArrayList<>();
            arrayList17.addAll(this.mPendingChanges);
            this.mChangesList.add(arrayList17);
            this.mPendingChanges.clear();
            Runnable runnable = new runPendingAnimations.changer.1();
            ((runPendingAnimations.changer.1) runnable).$changes = arrayList17;
            ((runPendingAnimations.changer.1) runnable).this$0 = this;
            VarHandle.storeStoreFence();
            if (z6) {
                runnable.run();
            } else {
                RecyclerView.ViewHolder oldHolder = arrayList17.get(0).getOldHolder();
                oldHolder.getClass();
                View view3 = oldHolder.itemView;
                long removeDuration2 = getRemoveDuration();
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                view3.postOnAnimationDelayed(runnable, removeDuration2);
            }
        }
        if (z9) {
            return;
        }
        ArrayList<RecyclerView.ViewHolder> arrayList18 = new ArrayList<>();
        arrayList18.addAll(this.mPendingAdditions);
        this.mPendingAdditions.clear();
        if (!z6 && this.adapter.getCurrentDataSource() == 1 && isPanelCollapsePhaseActive()) {
            int size9 = arrayList18.size();
            int i14 = 0;
            while (i14 < size9) {
                RecyclerView.ViewHolder viewHolder2 = arrayList18.get(i14);
                i14++;
                RecyclerView.ViewHolder viewHolder3 = viewHolder2;
                if (viewHolder3.itemView.getAlpha() < 1.0f) {
                    viewHolder3.itemView.setAlpha(1.0f);
                }
                dispatchAnimationFinished(viewHolder3);
            }
            arrayList18.clear();
            return;
        }
        this.mAdditionsList.add(arrayList18);
        int size10 = arrayList18.size();
        if (z6) {
            i5 = 0;
        }
        int iResolvePanelExpandTotalCount2 = resolvePanelExpandTotalCount(Math.max(size10, i5));
        VolumeItemAnimator$runPendingAnimations$adder$1 volumeItemAnimator$runPendingAnimations$adder$2 = new VolumeItemAnimator$runPendingAnimations$adder$1(0);
        volumeItemAnimator$runPendingAnimations$adder$2.$additions = arrayList18;
        volumeItemAnimator$runPendingAnimations$adder$2.this$0 = this;
        volumeItemAnimator$runPendingAnimations$adder$2.$expandStaggerTotal = iResolvePanelExpandTotalCount2;
        VarHandle.storeStoreFence();
        if ((z6 && z7 && z8) || this.adapter.getCurrentDataSource() == 1) {
            volumeItemAnimator$runPendingAnimations$adder$2.run();
            return;
        }
        long jMax = (long) (Math.max(!z7 ? getMoveDuration() : 0L, !z8 ? getChangeDuration() : 0L) + (!z6 ? getRemoveDuration() : 0L));
        View view4 = arrayList18.get(0).itemView;
        WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
        view4.postOnAnimationDelayed(volumeItemAnimator$runPendingAnimations$adder$2, jMax);
    }

    public final void setMAddAnimations(ArrayList<RecyclerView.ViewHolder> arrayList) {
        this.mAddAnimations = arrayList;
    }

    public final void setMAdditionsList(ArrayList<ArrayList<RecyclerView.ViewHolder>> arrayList) {
        this.mAdditionsList = arrayList;
    }

    public final void setMChangeAnimations(ArrayList<RecyclerView.ViewHolder> arrayList) {
        this.mChangeAnimations = arrayList;
    }

    public final void setMChangesList(ArrayList<ArrayList<ChangeInfo>> arrayList) {
        this.mChangesList = arrayList;
    }

    public final void setMMoveAnimations(ArrayList<RecyclerView.ViewHolder> arrayList) {
        this.mMoveAnimations = arrayList;
    }

    public final void setMMovesList(ArrayList<ArrayList<MoveInfo>> arrayList) {
        this.mMovesList = arrayList;
    }

    public final void setMRemoveAnimations(ArrayList<RecyclerView.ViewHolder> arrayList) {
        this.mRemoveAnimations = arrayList;
    }

    public final void setPanelTransitionSession(PanelTransitionSession panelTransitionSession) {
        this.panelTransitionSession = panelTransitionSession;
    }

    public final boolean shouldDeferMediaRefreshForPanelExpand() {
        if (isPanelCollapsePhaseActive() || this.adapter.getCurrentDataSource() != 1) {
            return false;
        }
        PanelTransitionSession panelTransitionSession = this.panelTransitionSession;
        return (panelTransitionSession != null ? panelTransitionSession.getPhase() : null) == PanelTransitionSession.Phase.EXPANDING && isPanelExpandRunning();
    }
}
