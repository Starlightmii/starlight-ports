package com.oplus.systemui.qs.base.widget.recyclerview;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusItemSpringAnimator extends SimpleItemAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusItemSpringAnimator";
    public long addAnimatorDelay;
    public long changeAnimatorDelay;
    private TimeInterpolator defaultInterpolator;
    private final AbsOplusItemAnimator itemAnimator;
    private final RecyclerViewAnimationListener recyclerViewListener;
    private final ArrayList<RecyclerView.ViewHolder> pendingRemovals = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> pendingAdditions = new ArrayList<>();
    private final ArrayList<MoveInfo> pendingMoves = new ArrayList<>();
    private final ArrayList<ChangeInfo> pendingChanges = new ArrayList<>();
    private final ArrayList<ArrayList<RecyclerView.ViewHolder>> additionsList = new ArrayList<>();
    private final ArrayList<ArrayList<MoveInfo>> movesList = new ArrayList<>();
    private final ArrayList<ArrayList<ChangeInfo>> changesList = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> addAnimations = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> moveAnimations = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> removeAnimations = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> changeAnimations = new ArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ItemAnimationListener {
        boolean getNeedItemAddAnimation();

        boolean getNeedItemChangeAnimation();

        boolean getNeedItemMoveAnimation();

        boolean getNeedItemRemoveAnimation();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface RecyclerViewAnimationListener {
        boolean onAnimateAdd(RecyclerView.ViewHolder viewHolder);
    }

    public OplusItemSpringAnimator(AbsOplusItemAnimator absOplusItemAnimator, RecyclerViewAnimationListener recyclerViewAnimationListener) {
        this.itemAnimator = absOplusItemAnimator;
        this.recyclerViewListener = recyclerViewAnimationListener;
    }

    public static final void access$animateAddImpl(OplusItemSpringAnimator oplusItemSpringAnimator, RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        oplusItemSpringAnimator.addAnimations.add(viewHolder);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateAddImpl: start, " + viewHolder + " "));
        }
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateAddAnimationHolder = oplusItemSpringAnimator.itemAnimator.createAddAnimationHolder(view);
        animateAddImpl.2.1 r2 = new animateAddImpl.2.1(0);
        r2.$view = view;
        r2.this$0 = oplusItemSpringAnimator;
        r2.$holder = viewHolder;
        r2.$this_apply = oplusItemSpringAnimationHolderCreateAddAnimationHolder;
        VarHandle.storeStoreFence();
        oplusItemSpringAnimationHolderCreateAddAnimationHolder.setListener(r2);
        oplusItemSpringAnimationHolderCreateAddAnimationHolder.startSpringAnimation(oplusItemSpringAnimator.addAnimatorDelay);
        oplusItemSpringAnimator.addAnimatorDelay = 0L;
    }

    public static final void access$animateChangeImpl(OplusItemSpringAnimator oplusItemSpringAnimator, ChangeInfo changeInfo) {
        View view;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateChangeImpl: start , " + changeInfo));
        }
        if (changeInfo.getFromX() != changeInfo.getToX() || changeInfo.getFromY() != changeInfo.getToY()) {
            RecyclerView.ViewHolder oldHolder = changeInfo.getOldHolder();
            View view2 = oldHolder != null ? oldHolder.itemView : null;
            RecyclerView.ViewHolder newHolder = changeInfo.getNewHolder();
            View view3 = newHolder != null ? newHolder.itemView : null;
            if (view2 != null) {
                ViewPropertyAnimator duration = view2.animate().setDuration(oplusItemSpringAnimator.getChangeDuration());
                oplusItemSpringAnimator.changeAnimations.add(changeInfo.getOldHolder());
                duration.translationX(changeInfo.getToX() - changeInfo.getFromX());
                duration.translationY(changeInfo.getToY() - changeInfo.getFromY());
                duration.alpha(0.0f).setListener(new animateChangeImpl.2(oplusItemSpringAnimator, changeInfo, duration, view2, 0)).start();
            }
            if (view3 != null) {
                ViewPropertyAnimator viewPropertyAnimatorAnimate = view3.animate();
                oplusItemSpringAnimator.changeAnimations.add(changeInfo.getNewHolder());
                viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(oplusItemSpringAnimator.getChangeDuration()).alpha(1.0f).setListener(new animateChangeImpl.2(oplusItemSpringAnimator, changeInfo, viewPropertyAnimatorAnimate, view3, 1)).start();
                return;
            }
            return;
        }
        RecyclerView.ViewHolder oldHolder2 = changeInfo.getOldHolder();
        RecyclerView.ViewHolder newHolder2 = changeInfo.getNewHolder();
        boolean z = oldHolder2 == newHolder2;
        if (newHolder2 == null || (view = newHolder2.itemView) == null) {
            return;
        }
        if (z) {
            view.setAlpha(1.0f);
        }
        oplusItemSpringAnimator.changeAnimations.add(newHolder2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->animateChangeImpl:newViewAnimation start ");
        }
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateChangeAnimationHolder = oplusItemSpringAnimator.itemAnimator.createChangeAnimationHolder(view);
        animateChangeSameItem.1.2.1 r3 = new animateChangeSameItem.1.2.1();
        r3.$isSameHolder = z;
        r3.$oldHolder = oldHolder2;
        r3.$view = view;
        r3.this$0 = oplusItemSpringAnimator;
        r3.$newHolder = newHolder2;
        r3.$this_apply = oplusItemSpringAnimationHolderCreateChangeAnimationHolder;
        VarHandle.storeStoreFence();
        oplusItemSpringAnimationHolderCreateChangeAnimationHolder.setListener(r3);
        oplusItemSpringAnimationHolderCreateChangeAnimationHolder.startSpringAnimation(oplusItemSpringAnimator.changeAnimatorDelay);
        oplusItemSpringAnimator.changeAnimatorDelay = 0L;
    }

    public static final void access$animateMoveImpl(OplusItemSpringAnimator oplusItemSpringAnimator, RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        int right;
        ViewGroup viewGroup;
        int i5;
        View view = viewHolder.itemView;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = view.getContext().getResources().getConfiguration().getLayoutDirection() == 1;
        int right2 = Integer.MAX_VALUE;
        if (z3) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            right = viewGroup2 != null ? viewGroup2.getLeft() : Integer.MIN_VALUE;
        } else {
            ViewParent parent2 = view.getParent();
            ViewGroup viewGroup3 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
            right = viewGroup3 != null ? viewGroup3.getRight() : Integer.MAX_VALUE;
        }
        if (z3) {
            ViewParent parent3 = view.getParent();
            viewGroup = parent3 instanceof ViewGroup ? (ViewGroup) parent3 : null;
            if (viewGroup != null) {
                right2 = viewGroup.getRight();
            }
        } else {
            ViewParent parent4 = view.getParent();
            viewGroup = parent4 instanceof ViewGroup ? (ViewGroup) parent4 : null;
            right2 = viewGroup != null ? viewGroup.getLeft() : Integer.MIN_VALUE;
        }
        int i6 = i3 - i;
        int i7 = i4 - i2;
        int i8 = i3 + 1;
        if ((i8 <= right && right <= i && !z3) || ((i5 = i + 1) <= right && right <= i3 && z3)) {
            z2 = false;
            z = true;
        } else if ((((i5 > right || right > i3) && (i8 > right2 || right2 > i)) || z3) && (((i8 > right || right > i) && (i5 > right2 || right2 > i3)) || !z3)) {
            z2 = false;
        }
        oplusItemSpringAnimator.moveAnimations.add(viewHolder);
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateMoveAnimationHolder = oplusItemSpringAnimator.itemAnimator.createMoveAnimationHolder(view, z, z2, i6, i7);
        OplusItemSpringAnimator$animateMoveImpl$1 oplusItemSpringAnimator$animateMoveImpl$1 = new OplusItemSpringAnimator$animateMoveImpl$1();
        oplusItemSpringAnimator$animateMoveImpl$1.$moveInScreen = z;
        oplusItemSpringAnimator$animateMoveImpl$1.$deltaX = i6;
        oplusItemSpringAnimator$animateMoveImpl$1.$view = view;
        oplusItemSpringAnimator$animateMoveImpl$1.$deltaY = i7;
        oplusItemSpringAnimator$animateMoveImpl$1.this$0 = oplusItemSpringAnimator;
        oplusItemSpringAnimator$animateMoveImpl$1.$holder = viewHolder;
        oplusItemSpringAnimator$animateMoveImpl$1.$moveOutScreen = z2;
        oplusItemSpringAnimator$animateMoveImpl$1.$springAnimationHolder = oplusItemSpringAnimationHolderCreateMoveAnimationHolder;
        VarHandle.storeStoreFence();
        oplusItemSpringAnimationHolderCreateMoveAnimationHolder.setListener(oplusItemSpringAnimator$animateMoveImpl$1);
        oplusItemSpringAnimationHolderCreateMoveAnimationHolder.startSpringAnimation(0L);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateMoveImpl: start ," + viewHolder));
        }
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        RecyclerViewAnimationListener recyclerViewAnimationListener = this.recyclerViewListener;
        if (Intrinsics.areEqual(recyclerViewAnimationListener != null ? Boolean.valueOf(recyclerViewAnimationListener.onAnimateAdd(viewHolder)) : null, Boolean.TRUE)) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        KeyEvent.Callback callback = viewHolder.itemView;
        ItemAnimationListener itemAnimationListener = callback instanceof ItemAnimationListener ? (ItemAnimationListener) callback : null;
        if (Intrinsics.areEqual(itemAnimationListener != null ? Boolean.valueOf(itemAnimationListener.getNeedItemAddAnimation()) : null, Boolean.FALSE)) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        resetAnimation$2(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.pendingAdditions.add(viewHolder);
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
            return true;
        }
        Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->animateAdd: start , ");
        return true;
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        KeyEvent.Callback callback = viewHolder2 != null ? viewHolder2.itemView : null;
        ItemAnimationListener itemAnimationListener = callback instanceof ItemAnimationListener ? (ItemAnimationListener) callback : null;
        if (Intrinsics.areEqual(itemAnimationListener != null ? Boolean.valueOf(itemAnimationListener.getNeedItemChangeAnimation()) : null, Boolean.FALSE)) {
            dispatchAnimationFinished(viewHolder);
            if (Intrinsics.areEqual(viewHolder2, viewHolder)) {
                return false;
            }
            dispatchAnimationFinished(viewHolder2);
            return false;
        }
        if (viewHolder == viewHolder2 && (i != i3 || i2 != i4)) {
            return animateMove(viewHolder, i, i2, i3, i4);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation$2(viewHolder);
        int i5 = (int) ((i3 - i) - translationX);
        int i6 = (int) ((i4 - i2) - translationY);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        if (viewHolder2 != null) {
            resetAnimation$2(viewHolder2);
            viewHolder2.itemView.setTranslationX(-i5);
            viewHolder2.itemView.setTranslationY(-i6);
            if (!viewHolder2.equals(viewHolder)) {
                viewHolder2.itemView.setAlpha(0.0f);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->animateChange: start , ");
        }
        this.pendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        KeyEvent.Callback callback = viewHolder.itemView;
        ItemAnimationListener itemAnimationListener = callback instanceof ItemAnimationListener ? (ItemAnimationListener) callback : null;
        if (Intrinsics.areEqual(itemAnimationListener != null ? Boolean.valueOf(itemAnimationListener.getNeedItemMoveAnimation()) : null, Boolean.FALSE)) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        View view = viewHolder.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation$2(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->animateMove: start , ");
        }
        this.pendingMoves.add(new MoveInfo(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        KeyEvent.Callback callback = viewHolder.itemView;
        ItemAnimationListener itemAnimationListener = callback instanceof ItemAnimationListener ? (ItemAnimationListener) callback : null;
        if (Intrinsics.areEqual(itemAnimationListener != null ? Boolean.valueOf(itemAnimationListener.getNeedItemRemoveAnimation()) : null, Boolean.FALSE)) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        resetAnimation$2(viewHolder);
        this.pendingRemovals.add(viewHolder);
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
            return true;
        }
        Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->animateRemove: start , ");
        return true;
    }

    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<? extends Object> list) {
        return (list.isEmpty() && ((SimpleItemAnimator) this).mSupportsChangeAnimations && !viewHolder.isInvalid()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void cancelAll(List<? extends RecyclerView.ViewHolder> list) {
        View view;
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            RecyclerView.ViewHolder viewHolder = list.get(size);
            if (viewHolder != null && (view = viewHolder.itemView) != 0) {
                view.animate().cancel();
                OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.springAnimationHolder(view);
                if (oplusItemSpringAnimationHolderSpringAnimationHolder != null) {
                    oplusItemSpringAnimationHolderSpringAnimationHolder.cancel();
                }
                if (view instanceof ItemAnimationListener) {
                }
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
        dispatchAnimationsFinished();
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("[endAnimation] : start , " + viewHolder + " "));
        }
        View view = viewHolder.itemView;
        view.animate().cancel();
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.springAnimationHolder(view);
        if (oplusItemSpringAnimationHolderSpringAnimationHolder != null) {
            oplusItemSpringAnimationHolderSpringAnimationHolder.cancel();
        }
        int size = this.pendingMoves.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.pendingMoves.get(size).getHolder() == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    this.pendingMoves.remove(size);
                }
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        endChangeAnimation$2(viewHolder, this.pendingChanges);
        if (this.pendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.pendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        int size2 = this.changesList.size() - 1;
        if (size2 >= 0) {
            while (true) {
                int i2 = size2 - 1;
                ArrayList<ChangeInfo> arrayList = this.changesList.get(size2);
                endChangeAnimation$2(viewHolder, arrayList);
                if (arrayList.isEmpty()) {
                    this.changesList.remove(size2);
                }
                if (i2 < 0) {
                    break;
                } else {
                    size2 = i2;
                }
            }
        }
        int size3 = this.movesList.size() - 1;
        if (size3 >= 0) {
            while (true) {
                int i3 = size3 - 1;
                ArrayList<MoveInfo> arrayList2 = this.movesList.get(size3);
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
                            this.movesList.remove(size3);
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
        int size5 = this.additionsList.size() - 1;
        if (size5 >= 0) {
            while (true) {
                int i5 = size5 - 1;
                ArrayList<RecyclerView.ViewHolder> arrayList3 = this.additionsList.get(size5);
                if (arrayList3.remove(viewHolder)) {
                    view.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder);
                    if (arrayList3.isEmpty()) {
                        this.additionsList.remove(size5);
                    }
                }
                if (i5 < 0) {
                    break;
                } else {
                    size5 = i5;
                }
            }
        }
        this.removeAnimations.remove(viewHolder);
        this.addAnimations.remove(viewHolder);
        this.changeAnimations.remove(viewHolder);
        this.moveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    public final void endAnimations() {
        ArrayList<ChangeInfo> arrayList;
        int size = this.pendingMoves.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            }
            MoveInfo moveInfo = this.pendingMoves.get(size);
            View view = moveInfo.getHolder().itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(moveInfo.getHolder());
            this.pendingMoves.remove(size);
        }
        for (int size2 = this.pendingRemovals.size() - 1; -1 < size2; size2--) {
            dispatchAnimationFinished(this.pendingRemovals.get(size2));
            this.pendingRemovals.remove(size2);
        }
        int size3 = this.pendingAdditions.size();
        while (true) {
            size3--;
            if (-1 >= size3) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = this.pendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.pendingAdditions.remove(size3);
        }
        int size4 = this.pendingChanges.size();
        while (true) {
            size4--;
            arrayList = this.pendingChanges;
            if (-1 >= size4) {
                break;
            } else {
                endChangeAnimationIfNecessary(arrayList.get(size4));
            }
        }
        arrayList.clear();
        if (isRunning()) {
            for (int size5 = this.movesList.size() - 1; -1 < size5; size5--) {
                ArrayList<MoveInfo> arrayList2 = this.movesList.get(size5);
                for (int size6 = arrayList2.size() - 1; -1 < size6; size6--) {
                    MoveInfo moveInfo2 = arrayList2.get(size6);
                    View view2 = moveInfo2.getHolder().itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchAnimationFinished(moveInfo2.getHolder());
                    arrayList2.remove(size6);
                    if (arrayList2.isEmpty()) {
                        this.movesList.remove(arrayList2);
                    }
                }
            }
            for (int size7 = this.additionsList.size() - 1; -1 < size7; size7--) {
                ArrayList<RecyclerView.ViewHolder> arrayList3 = this.additionsList.get(size7);
                for (int size8 = arrayList3.size() - 1; -1 < size8; size8--) {
                    RecyclerView.ViewHolder viewHolder2 = arrayList3.get(size8);
                    viewHolder2.itemView.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder2);
                    arrayList3.remove(size8);
                    if (arrayList3.isEmpty()) {
                        this.additionsList.remove(arrayList3);
                    }
                }
            }
            for (int size9 = this.changesList.size() - 1; -1 < size9; size9--) {
                ArrayList<ChangeInfo> arrayList4 = this.changesList.get(size9);
                for (int size10 = arrayList4.size() - 1; -1 < size10; size10--) {
                    endChangeAnimationIfNecessary(arrayList4.get(size10));
                    if (arrayList4.isEmpty()) {
                        this.changesList.remove(arrayList4);
                    }
                }
            }
            cancelAll(this.removeAnimations);
            cancelAll(this.moveAnimations);
            cancelAll(this.addAnimations);
            cancelAll(this.changeAnimations);
            dispatchAnimationsFinished();
        }
    }

    public final void endChangeAnimation$2(RecyclerView.ViewHolder viewHolder, List list) {
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

    public final long getAddAnimatorDelay() {
        return this.addAnimatorDelay;
    }

    public final long getChangeAnimatorDelay() {
        return this.changeAnimatorDelay;
    }

    public final boolean isRunning() {
        return (this.pendingAdditions.isEmpty() && this.pendingChanges.isEmpty() && this.pendingMoves.isEmpty() && this.pendingRemovals.isEmpty() && this.moveAnimations.isEmpty() && this.removeAnimations.isEmpty() && this.addAnimations.isEmpty() && this.changeAnimations.isEmpty() && this.movesList.isEmpty() && this.additionsList.isEmpty() && this.changesList.isEmpty()) ? false : true;
    }

    public final void resetAnimation$2(RecyclerView.ViewHolder viewHolder) {
        if (this.defaultInterpolator == null) {
            this.defaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(this.defaultInterpolator);
        endAnimation(viewHolder);
    }

    public final void runPendingAnimations() {
        View view;
        boolean zIsEmpty = this.pendingRemovals.isEmpty();
        boolean zIsEmpty2 = this.pendingMoves.isEmpty();
        boolean zIsEmpty3 = this.pendingChanges.isEmpty();
        boolean zIsEmpty4 = this.pendingAdditions.isEmpty();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty4 && zIsEmpty3) {
            return;
        }
        ArrayList<RecyclerView.ViewHolder> arrayList = this.pendingRemovals;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = arrayList.get(i);
            i++;
            RecyclerView.ViewHolder viewHolder2 = viewHolder;
            View view2 = viewHolder2.itemView;
            this.removeAnimations.add(viewHolder2);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateRemoveImpl: start ," + viewHolder2));
            }
            OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateRemoveAnimationHolder = this.itemAnimator.createRemoveAnimationHolder(view2);
            animateAddImpl.2.1 r14 = new animateAddImpl.2.1(1);
            r14.this$0 = this;
            r14.$holder = viewHolder2;
            r14.$this_apply = oplusItemSpringAnimationHolderCreateRemoveAnimationHolder;
            r14.$view = view2;
            VarHandle.storeStoreFence();
            oplusItemSpringAnimationHolderCreateRemoveAnimationHolder.setListener(r14);
            oplusItemSpringAnimationHolderCreateRemoveAnimationHolder.startSpringAnimation(0L);
        }
        this.pendingRemovals.clear();
        if (!zIsEmpty2) {
            ArrayList<MoveInfo> arrayList2 = new ArrayList<>();
            arrayList2.addAll(this.pendingMoves);
            this.movesList.add(arrayList2);
            this.pendingMoves.clear();
            Runnable runnable = new runPendingAnimations.adder.1(2);
            ((runPendingAnimations.adder.1) runnable).$additions = arrayList2;
            ((runPendingAnimations.adder.1) runnable).this$0 = this;
            VarHandle.storeStoreFence();
            if (zIsEmpty) {
                runnable.run();
            } else {
                arrayList2.get(0).getHolder().itemView.postOnAnimationDelayed(runnable, getRemoveDuration());
            }
        }
        if (!zIsEmpty3) {
            ArrayList<ChangeInfo> arrayList3 = new ArrayList<>();
            arrayList3.addAll(this.pendingChanges);
            this.changesList.add(arrayList3);
            this.pendingChanges.clear();
            Runnable runnable2 = new runPendingAnimations.adder.1(1);
            ((runPendingAnimations.adder.1) runnable2).$additions = arrayList3;
            ((runPendingAnimations.adder.1) runnable2).this$0 = this;
            VarHandle.storeStoreFence();
            if (zIsEmpty) {
                runnable2.run();
            } else {
                RecyclerView.ViewHolder oldHolder = arrayList3.get(0).getOldHolder();
                if (oldHolder != null && (view = oldHolder.itemView) != null) {
                    view.postOnAnimationDelayed(runnable2, getRemoveDuration());
                }
            }
        }
        if (zIsEmpty4) {
            return;
        }
        ArrayList<RecyclerView.ViewHolder> arrayList4 = new ArrayList<>();
        arrayList4.addAll(this.pendingAdditions);
        this.additionsList.add(arrayList4);
        this.pendingAdditions.clear();
        Runnable runnable3 = new runPendingAnimations.adder.1(0);
        ((runPendingAnimations.adder.1) runnable3).$additions = arrayList4;
        ((runPendingAnimations.adder.1) runnable3).this$0 = this;
        VarHandle.storeStoreFence();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
            runnable3.run();
        } else {
            arrayList4.get(0).itemView.postOnAnimationDelayed(runnable3, (long) (Math.max(!zIsEmpty2 ? getMoveDuration() : 0L, zIsEmpty3 ? 0L : getChangeDuration()) + (!zIsEmpty ? getRemoveDuration() : 0L)));
        }
    }

    public final void setAddAnimatorDelay(long j) {
        this.addAnimatorDelay = j;
    }

    public final void setChangeAnimatorDelay(long j) {
        this.changeAnimatorDelay = j;
    }

    public final void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
        if (changeInfo.getOldHolder() != null) {
            RecyclerView.ViewHolder oldHolder = changeInfo.getOldHolder();
            oldHolder.getClass();
            endChangeAnimationIfNecessary(changeInfo, oldHolder);
        }
        if (changeInfo.getNewHolder() != null) {
            RecyclerView.ViewHolder newHolder = changeInfo.getNewHolder();
            newHolder.getClass();
            endChangeAnimationIfNecessary(changeInfo, newHolder);
        }
    }
}
