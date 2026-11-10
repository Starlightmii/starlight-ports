package com.oplus.systemui.qs.base.customizer;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusItemAnimator extends DefaultItemAnimator {
    public static final Interpolator ADD_REMOVE_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    public static final Interpolator SCALE_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    public static TimeInterpolator sDefaultInterpolator;
    public long mAlphaInDuration = 250;
    public long mAlphaOutDuration = 100;
    public int mMaxInPageTranslation = 1080;
    public final ArrayList mPendingRemovals = new ArrayList();
    public final ArrayList mPendingAdditions = new ArrayList();
    public final ArrayList mPendingMoves = new ArrayList();
    public final ArrayList mPendingChanges = new ArrayList();
    public final ArrayList mAdditionsList = new ArrayList();
    public final ArrayList mMovesList = new ArrayList();
    public final ArrayList mChangesList = new ArrayList();
    public final ArrayList mAddAnimations = new ArrayList();
    public final ArrayList mMoveAnimations = new ArrayList();
    public final ArrayList mRemoveAnimations = new ArrayList();
    public final ArrayList mChangeAnimations = new ArrayList();

    /* JADX INFO: renamed from: -$$Nest$maddRemoveAnimation, reason: not valid java name */
    public static void m242$$Nest$maddRemoveAnimation(OplusItemAnimator oplusItemAnimator, View view, float f, float f2, float f3, float f4) {
        if (view == null) {
            return;
        }
        view.animate().cancel();
        view.setScaleX(f);
        view.setScaleY(f2);
        view.animate().scaleX(f3).scaleY(f4).setDuration(417L).setInterpolator(SCALE_INTERPOLATOR).setListener(new 5(oplusItemAnimator, view)).start();
    }

    public final boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add(viewHolder);
        return true;
    }

    public final boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        resetAnimation(viewHolder);
        resetAnimation(viewHolder2);
        viewHolder2.itemView.setAlpha(0.0f);
        ArrayList arrayList = this.mPendingChanges;
        ChangeInfo changeInfo = new ChangeInfo();
        changeInfo.oldHolder = viewHolder;
        changeInfo.newHolder = viewHolder2;
        changeInfo.fromX = i;
        changeInfo.fromY = i2;
        changeInfo.toX = i3;
        changeInfo.toY = i4;
        arrayList.add(changeInfo);
        return true;
    }

    public final boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation(viewHolder);
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
        ArrayList arrayList = this.mPendingMoves;
        MoveInfo moveInfo = new MoveInfo();
        moveInfo.holder = viewHolder;
        moveInfo.fromX = translationX;
        moveInfo.fromY = translationY;
        moveInfo.toX = i3;
        moveInfo.toY = i4;
        arrayList.add(moveInfo);
        return true;
    }

    public final boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
        return true;
    }

    public final boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list);
    }

    public final void cancelAll(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((RecyclerView.ViewHolder) list.get(size)).itemView.animate().cancel();
        }
    }

    public final void dispatchFinishedWhenDone() {
        if (isRunning()) {
            return;
        }
        dispatchAnimationsFinished();
    }

    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((MoveInfo) this.mPendingMoves.get(size)).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchAnimationFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(viewHolder, this.mPendingChanges);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.mChangesList.get(size2);
            endChangeAnimation(viewHolder, arrayList);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size3);
            for (int size4 = arrayList2.size() - 1; size4 >= 0; size4--) {
                if (((MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
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
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAnimationFinished(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(viewHolder);
        this.mAddAnimations.remove(viewHolder);
        this.mChangeAnimations.remove(viewHolder);
        this.mMoveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    public final void endAnimations() {
        ArrayList arrayList;
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = (MoveInfo) this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        for (int size2 = this.mPendingRemovals.size() - 1; size2 >= 0; size2--) {
            dispatchAnimationFinished((RecyclerView.ViewHolder) this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        int size4 = this.mPendingChanges.size();
        while (true) {
            size4--;
            arrayList = this.mPendingChanges;
            if (size4 < 0) {
                break;
            }
            ChangeInfo changeInfo = (ChangeInfo) arrayList.get(size4);
            RecyclerView.ViewHolder viewHolder2 = changeInfo.oldHolder;
            if (viewHolder2 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder2);
            }
            RecyclerView.ViewHolder viewHolder3 = changeInfo.newHolder;
            if (viewHolder3 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder3);
            }
        }
        arrayList.clear();
        if (isRunning()) {
            for (int size5 = this.mMovesList.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size5);
                for (int size6 = arrayList2.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList2.get(size6);
                    View view2 = moveInfo2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchAnimationFinished(moveInfo2.holder);
                    arrayList2.remove(size6);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(arrayList2);
                    }
                }
            }
            for (int size7 = this.mAdditionsList.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size7);
                for (int size8 = arrayList3.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder4 = (RecyclerView.ViewHolder) arrayList3.get(size8);
                    viewHolder4.itemView.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder4);
                    arrayList3.remove(size8);
                    if (arrayList3.isEmpty()) {
                        this.mAdditionsList.remove(arrayList3);
                    }
                }
            }
            for (int size9 = this.mChangesList.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList4 = (ArrayList) this.mChangesList.get(size9);
                for (int size10 = arrayList4.size() - 1; size10 >= 0; size10--) {
                    ChangeInfo changeInfo2 = (ChangeInfo) arrayList4.get(size10);
                    RecyclerView.ViewHolder viewHolder5 = changeInfo2.oldHolder;
                    if (viewHolder5 != null) {
                        endChangeAnimationIfNecessary(changeInfo2, viewHolder5);
                    }
                    RecyclerView.ViewHolder viewHolder6 = changeInfo2.newHolder;
                    if (viewHolder6 != null) {
                        endChangeAnimationIfNecessary(changeInfo2, viewHolder6);
                    }
                    if (arrayList4.isEmpty()) {
                        this.mChangesList.remove(arrayList4);
                    }
                }
            }
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            dispatchAnimationsFinished();
        }
    }

    public final void endChangeAnimation(RecyclerView.ViewHolder viewHolder, List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }

    public final boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else {
            if (changeInfo.oldHolder != viewHolder) {
                return false;
            }
            changeInfo.oldHolder = null;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    public long getAlphaInDuration() {
        return this.mAlphaInDuration;
    }

    public long getAlphaOutDuration() {
        return this.mAlphaOutDuration;
    }

    public final boolean isRunning() {
        return (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) ? false : true;
    }

    public final void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    public final void runPendingAnimations() {
        boolean zIsEmpty = this.mPendingRemovals.isEmpty();
        boolean zIsEmpty2 = this.mPendingMoves.isEmpty();
        boolean zIsEmpty3 = this.mPendingChanges.isEmpty();
        boolean zIsEmpty4 = this.mPendingAdditions.isEmpty();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty4 && zIsEmpty3) {
            return;
        }
        ArrayList arrayList = this.mPendingRemovals;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) obj;
            View view = viewHolder.itemView;
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
            this.mRemoveAnimations.add(viewHolder);
            OplusItemAnimator oplusItemAnimator = this;
            viewPropertyAnimatorAnimate.setDuration(250L).setInterpolator(ADD_REMOVE_INTERPOLATOR).alpha(0.0f).setListener(new 4(oplusItemAnimator, viewHolder, view, viewPropertyAnimatorAnimate, 0)).start();
            this = oplusItemAnimator;
        }
        OplusItemAnimator oplusItemAnimator2 = this;
        oplusItemAnimator2.mPendingRemovals.clear();
        if (!zIsEmpty2) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(oplusItemAnimator2.mPendingMoves);
            oplusItemAnimator2.mMovesList.add(arrayList2);
            oplusItemAnimator2.mPendingMoves.clear();
            Runnable runnable = new 1(0);
            ((1) runnable).this$0 = oplusItemAnimator2;
            ((1) runnable).val$moves = arrayList2;
            VarHandle.storeStoreFence();
            if (zIsEmpty) {
                runnable.run();
            } else {
                View view2 = ((MoveInfo) arrayList2.get(0)).holder.itemView;
                long removeDuration = oplusItemAnimator2.getRemoveDuration();
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                view2.postOnAnimationDelayed(runnable, removeDuration);
            }
        }
        if (!zIsEmpty3) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(oplusItemAnimator2.mPendingChanges);
            oplusItemAnimator2.mChangesList.add(arrayList3);
            oplusItemAnimator2.mPendingChanges.clear();
            Runnable runnable2 = new 1(1);
            ((1) runnable2).this$0 = oplusItemAnimator2;
            ((1) runnable2).val$moves = arrayList3;
            VarHandle.storeStoreFence();
            if (zIsEmpty) {
                runnable2.run();
            } else {
                View view3 = ((ChangeInfo) arrayList3.get(0)).oldHolder.itemView;
                long removeDuration2 = oplusItemAnimator2.getRemoveDuration();
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                view3.postOnAnimationDelayed(runnable2, removeDuration2);
            }
        }
        if (zIsEmpty4) {
            return;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(oplusItemAnimator2.mPendingAdditions);
        oplusItemAnimator2.mAdditionsList.add(arrayList4);
        oplusItemAnimator2.mPendingAdditions.clear();
        Runnable runnable3 = new 1(2);
        ((1) runnable3).this$0 = oplusItemAnimator2;
        ((1) runnable3).val$moves = arrayList4;
        VarHandle.storeStoreFence();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
            runnable3.run();
            return;
        }
        long jMax = Math.max(!zIsEmpty2 ? oplusItemAnimator2.getMoveDuration() : 0L, zIsEmpty3 ? 0L : oplusItemAnimator2.getChangeDuration()) + (!zIsEmpty ? oplusItemAnimator2.getRemoveDuration() : 0L);
        View view4 = ((RecyclerView.ViewHolder) arrayList4.get(0)).itemView;
        WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
        view4.postOnAnimationDelayed(runnable3, jMax);
    }

    public void setAlphaInDuration(long j) {
        this.mAlphaInDuration = j;
    }

    public void setAlphaOutDuration(long j) {
        this.mAlphaOutDuration = j;
    }

    public void setMaxInPageTranslation(int i) {
        this.mMaxInPageTranslation = i;
    }
}
