package com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper;

import android.content.Context;
import android.graphics.Rect;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.core.view.OneShotPreDrawListener;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.CopyViewProxy;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.DragAnimationHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.DragAnimationState;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.DragEventProxy;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.IElevationAdjust;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.IPressFeedBack;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.OnDragEventListener;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.DropAnim;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.CrossDragDataDelegate;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.CrossRegionDragData;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.InputDragData;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.OutputDragData;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.util.ItemDragViewElevationUtil;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.qs.base.util.SeparateDragTileData;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewDragHelper implements OnDragEventListener, IPressFeedBack, IElevationAdjust {
    public static final Companion Companion = new Companion();
    private static final String TAG = "RecyclerViewDragHelper";
    private final MutableStateFlow _dragAnimationStateData;
    private CopyViewProxy copyViewProxy;
    private final CrossDragDataDelegate crossDataDelegate;
    private DragAnimationHelper dragAnimationHelper;
    private final StateFlow dragAnimationStateFlow;
    private final Runnable dragRunnable;
    private RecyclerView.ViewHolder dragViewHolder;
    public boolean hasDropped;
    public int initialShadowPointX;
    public int initialShadowPointY;
    public boolean isDragMode;
    public boolean isInOneHandMode;
    public float lastLocationX;
    public float lastLocationY;
    public float locationX;
    public float locationY;
    private final Runnable onDropRunnable;
    private OutputDragData outputDragData;
    private RecyclerView recyclerView;
    private final ViewGroup rootView;
    private final RecyclerViewDragHelper$scrollListener$1 scrollListener;
    public final int touchSlop;
    private final List<DragAnimationHelper> animationList = new ArrayList();
    private final List<DropAnim> dropAnimationList = new ArrayList();
    private final HashMap<RecyclerView, ItemDragCallBack> recyclerViewCallBack = new HashMap<>();
    private final HashMap<RecyclerView, Rect> recyclerViewLocation = new HashMap<>();
    private final Rect dragViewRect = new Rect();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final int access$calculateOverlapArea(Companion companion, Rect rect, Rect rect2) {
            companion.getClass();
            int i = rect.left;
            int i2 = rect2.left;
            if (i < i2) {
                i = i2;
            }
            int i3 = rect.top;
            int i4 = rect2.top;
            if (i3 < i4) {
                i3 = i4;
            }
            int i5 = rect.right;
            int i6 = rect2.right;
            if (i5 > i6) {
                i5 = i6;
            }
            int i7 = rect.bottom;
            int i8 = rect2.bottom;
            if (i7 > i8) {
                i7 = i8;
            }
            if (i >= i5 || i3 >= i7) {
                return 0;
            }
            return (i7 - i3) * (i5 - i);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$6avmqme2alIqW-lHx6afW63fhJs, reason: not valid java name */
    public static CopyViewProxy m160$r8$lambda$6avmqme2alIqWlHx6afW63fhJs(RecyclerView.ViewHolder viewHolder, ItemDragCallBack itemDragCallBack, RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        RecyclerView.ViewHolder viewHolderCreateAndBindViewHolder = itemDragCallBack.createAndBindViewHolder(obj, recyclerViewDragHelper.rootView);
        if (viewHolderCreateAndBindViewHolder == null) {
            recyclerViewDragHelper.copyViewProxy = null;
        } else {
            recyclerViewDragHelper.copyViewProxy = new CopyViewProxy(viewHolderCreateAndBindViewHolder, viewHolder);
        }
        return recyclerViewDragHelper.copyViewProxy;
    }

    public static Unit $r8$lambda$EMgt9uFGtrFUSDdXZNM5Gnzm0go(RecyclerViewDragHelper recyclerViewDragHelper) {
        recyclerViewDragHelper._dragAnimationStateData.setValue(DragAnimationState.Running.INSTANCE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$GP5B-ZLfGQzBn5MqoFbD3Hq8Ljs, reason: not valid java name */
    public static RecyclerView.ViewHolder m161$r8$lambda$GP5BZLfGQzBn5MqoFbD3Hq8Ljs(RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        Iterator<T> it = recyclerViewDragHelper.recyclerViewCallBack.values().iterator();
        while (it.hasNext()) {
            RecyclerView.ViewHolder holder = ((ItemDragCallBack) it.next()).getHolder(obj);
            if (holder != null) {
                return holder;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$OLacBHUU_M0Q1WPGVq_loSBw-LU, reason: not valid java name */
    public static Unit m162$r8$lambda$OLacBHUU_M0Q1WPGVq_loSBwLU(RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        Object next;
        RecyclerView.ViewHolder holder;
        List<DragAnimationHelper> list = recyclerViewDragHelper.animationList;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            do {
                if (!it.hasNext()) {
                    recyclerViewDragHelper._dragAnimationStateData.setValue(DragAnimationState.Idle.INSTANCE);
                    break;
                }
            } while (!((DragAnimationHelper) it.next()).isDragAnimRunning);
        } else {
            recyclerViewDragHelper._dragAnimationStateData.setValue(DragAnimationState.Idle.INSTANCE);
            break;
        }
        Iterator<T> it2 = recyclerViewDragHelper.animationList.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!Intrinsics.areEqual(((DragAnimationHelper) next).getData(), obj));
        TypeIntrinsics.asMutableCollection(recyclerViewDragHelper.animationList).remove((DragAnimationHelper) next);
        if (recyclerViewDragHelper.copyViewProxy == null) {
            return Unit.INSTANCE;
        }
        Iterator<T> it3 = recyclerViewDragHelper.recyclerViewCallBack.values().iterator();
        do {
            if (!it3.hasNext()) {
                holder = null;
                break;
            }
            holder = ((ItemDragCallBack) it3.next()).getHolder(obj);
        } while (holder == null);
        CopyViewProxy copyViewProxy = recyclerViewDragHelper.copyViewProxy;
        if (Intrinsics.areEqual(copyViewProxy != null ? copyViewProxy.getHostViewHolder() : null, holder)) {
            recyclerViewDragHelper.copyViewProxy = null;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "set copyViewProxy null " + holder, null, 8);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$W7IPzKo1xn5mTsMOj2d6W87ivMQ(RecyclerView.ViewHolder viewHolder, ItemDragCallBack itemDragCallBack, RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        CrossDragDataDelegate crossDragDataDelegate = recyclerViewDragHelper.crossDataDelegate;
        RecyclerViewDragHelper$$ExternalSyntheticLambda5 recyclerViewDragHelper$$ExternalSyntheticLambda5 = new RecyclerViewDragHelper$$ExternalSyntheticLambda5(1);
        recyclerViewDragHelper$$ExternalSyntheticLambda5.f$0 = recyclerViewDragHelper;
        VarHandle.storeStoreFence();
        crossDragDataDelegate.updateDragData(recyclerViewDragHelper$$ExternalSyntheticLambda5);
        OutputDragData outputDragData = new OutputDragData(obj, itemDragCallBack.getIntentTarget(), viewHolder.itemView.getWidth(), viewHolder.itemView.getHeight(), recyclerViewDragHelper.crossDataDelegate);
        recyclerViewDragHelper.outputDragData = outputDragData;
        for (Map.Entry<RecyclerView, ItemDragCallBack> entry : recyclerViewDragHelper.recyclerViewCallBack.entrySet()) {
            RecyclerView key = entry.getKey();
            ItemDragCallBack value = entry.getValue();
            RecyclerViewDragHelper$notifyDragStart$1$1 recyclerViewDragHelper$notifyDragStart$1$1 = new RecyclerViewDragHelper$notifyDragStart$1$1();
            recyclerViewDragHelper$notifyDragStart$1$1.this$0 = recyclerViewDragHelper;
            VarHandle.storeStoreFence();
            value.registerViewHolderAdd(obj, recyclerViewDragHelper$notifyDragStart$1$1);
            float f = recyclerViewDragHelper.locationX;
            float f2 = recyclerViewDragHelper.locationY;
            OutputDragData outputDragData2 = outputDragData;
            value.onDragStart(key, f, f2, f + recyclerViewDragHelper.initialShadowPointX, f2 + recyclerViewDragHelper.initialShadowPointY, outputDragData2);
            outputDragData = outputDragData2;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$ZdZMHGxK3Vt0oKvhbhuA-E4yKKs, reason: not valid java name */
    public static RecyclerView.ViewHolder m163$r8$lambda$ZdZMHGxK3Vt0oKvhbhuAE4yKKs(RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        Iterator<T> it = recyclerViewDragHelper.recyclerViewCallBack.values().iterator();
        while (it.hasNext()) {
            RecyclerView.ViewHolder holder = ((ItemDragCallBack) it.next()).getHolder(obj);
            if (holder != null) {
                return holder;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$iQtQUgL-Yg9T5Zfdfi9MlwmIKt4, reason: not valid java name */
    public static Unit m164$r8$lambda$iQtQUgLYg9T5Zfdfi9MlwmIKt4(RecyclerViewDragHelper recyclerViewDragHelper, CrossRegionDragData crossRegionDragData) {
        IntentTarget intentTarget;
        ItemDragCallBack itemDragCallBack = recyclerViewDragHelper.getItemDragCallBack();
        if (itemDragCallBack == null || (intentTarget = itemDragCallBack.getIntentTarget()) == null) {
            intentTarget = IntentTarget.NON;
        }
        crossRegionDragData.setCurrentDragIntent(intentTarget);
        crossRegionDragData.setOriginLoc(null);
        crossRegionDragData.setSwapDataInPersonalArea(null);
        crossRegionDragData.setInsertSpecInOtherArea(null);
        crossRegionDragData.setLocInPersonalArea(null);
        crossRegionDragData.setCopyViewProxy(recyclerViewDragHelper.copyViewProxy);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ms0uvArz2oHHm53rYFMy45zCLdg(RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        for (Object obj2 : recyclerViewDragHelper.dropAnimationList) {
            if (Intrinsics.areEqual(((DropAnim) obj2).getData(), obj)) {
                TypeIntrinsics.asMutableCollection(recyclerViewDragHelper.dropAnimationList).remove((DropAnim) obj2);
                recyclerViewDragHelper.unregisterSwap();
                return Unit.INSTANCE;
            }
        }
        obj2 = null;
        TypeIntrinsics.asMutableCollection(recyclerViewDragHelper.dropAnimationList).remove((DropAnim) obj2);
        recyclerViewDragHelper.unregisterSwap();
        return Unit.INSTANCE;
    }

    public RecyclerViewDragHelper(ViewGroup viewGroup) {
        this.rootView = viewGroup;
        this.touchSlop = ViewConfiguration.get(viewGroup.getContext()).getScaledTouchSlop();
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(DragAnimationState.Idle.INSTANCE);
        this._dragAnimationStateData = MutableStateFlow;
        this.dragAnimationStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        this.crossDataDelegate = new CrossDragDataDelegate();
        RecyclerViewDragHelper$dragRunnable$1 recyclerViewDragHelper$dragRunnable$1 = new RecyclerViewDragHelper$dragRunnable$1(0);
        recyclerViewDragHelper$dragRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dragRunnable = recyclerViewDragHelper$dragRunnable$1;
        RecyclerViewDragHelper$dragRunnable$1 recyclerViewDragHelper$dragRunnable$2 = new RecyclerViewDragHelper$dragRunnable$1(2);
        recyclerViewDragHelper$dragRunnable$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.onDropRunnable = recyclerViewDragHelper$dragRunnable$2;
        RecyclerViewDragHelper$scrollListener$1 recyclerViewDragHelper$scrollListener$1 = new RecyclerViewDragHelper$scrollListener$1();
        recyclerViewDragHelper$scrollListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.scrollListener = recyclerViewDragHelper$scrollListener$1;
        viewGroup.setOnDragListener(new DragEventProxy(this));
    }

    public static final DropAnim access$findDropAnimation(RecyclerViewDragHelper recyclerViewDragHelper, Object obj) {
        Object next;
        Iterator<T> it = recyclerViewDragHelper.dropAnimationList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((DropAnim) next).getData(), obj));
        DropAnim dropAnim = (DropAnim) next;
        if (dropAnim != null) {
            return dropAnim;
        }
        int i = 1;
        RecyclerViewDragHelper$$ExternalSyntheticLambda0 recyclerViewDragHelper$$ExternalSyntheticLambda0 = new RecyclerViewDragHelper$$ExternalSyntheticLambda0(i);
        recyclerViewDragHelper$$ExternalSyntheticLambda0.f$0 = recyclerViewDragHelper;
        recyclerViewDragHelper$$ExternalSyntheticLambda0.f$1 = obj;
        VarHandle.storeStoreFence();
        RecyclerViewDragHelper$$ExternalSyntheticLambda2 recyclerViewDragHelper$$ExternalSyntheticLambda2 = new RecyclerViewDragHelper$$ExternalSyntheticLambda2(i);
        recyclerViewDragHelper$$ExternalSyntheticLambda2.f$0 = recyclerViewDragHelper;
        recyclerViewDragHelper$$ExternalSyntheticLambda2.f$1 = obj;
        VarHandle.storeStoreFence();
        DropAnim dropAnim2 = new DropAnim(obj, recyclerViewDragHelper$$ExternalSyntheticLambda0, recyclerViewDragHelper$$ExternalSyntheticLambda2);
        recyclerViewDragHelper.dropAnimationList.add(dropAnim2);
        return dropAnim2;
    }

    private final ItemDragCallBack getItemDragCallBack() {
        return this.recyclerViewCallBack.get(this.recyclerView);
    }

    private final int getScreenHeight() {
        return this.rootView.getContext().getResources().getConfiguration().windowConfiguration.getBounds().height();
    }

    private final void setRecyclerView(RecyclerView recyclerView) {
        if (Intrinsics.areEqual(this.recyclerView, recyclerView)) {
            return;
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.scrollListener);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.removeCallbacks(this.dragRunnable);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "exit " + this.recyclerView + ", enter " + recyclerView, null, 8);
        this.recyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(this.scrollListener);
        }
    }

    public final void doOnDrop() {
        if (this.outputDragData != null) {
            this.hasDropped = true;
            this.isDragMode = false;
            DragAnimationHelper dragAnimationHelper = this.dragAnimationHelper;
            if (dragAnimationHelper != null) {
                dragAnimationHelper.startDropAnim();
            }
            unregisterSwap();
        }
        this.dragViewHolder = null;
    }

    public final DragAnimationHelper findAnimation(Object obj) {
        Object next;
        Iterator<T> it = this.animationList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((DragAnimationHelper) next).getData(), obj));
        DragAnimationHelper dragAnimationHelper = (DragAnimationHelper) next;
        if (dragAnimationHelper != null) {
            return dragAnimationHelper;
        }
        ViewGroup viewGroup = this.rootView;
        int i = 0;
        RecyclerViewDragHelper$$ExternalSyntheticLambda0 recyclerViewDragHelper$$ExternalSyntheticLambda0 = new RecyclerViewDragHelper$$ExternalSyntheticLambda0(i);
        recyclerViewDragHelper$$ExternalSyntheticLambda0.f$0 = this;
        recyclerViewDragHelper$$ExternalSyntheticLambda0.f$1 = obj;
        VarHandle.storeStoreFence();
        RecyclerViewDragHelper$$ExternalSyntheticLambda1 recyclerViewDragHelper$$ExternalSyntheticLambda1 = new RecyclerViewDragHelper$$ExternalSyntheticLambda1();
        recyclerViewDragHelper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        RecyclerViewDragHelper$$ExternalSyntheticLambda2 recyclerViewDragHelper$$ExternalSyntheticLambda2 = new RecyclerViewDragHelper$$ExternalSyntheticLambda2(i);
        recyclerViewDragHelper$$ExternalSyntheticLambda2.f$0 = this;
        recyclerViewDragHelper$$ExternalSyntheticLambda2.f$1 = obj;
        VarHandle.storeStoreFence();
        DragAnimationHelper dragAnimationHelper2 = new DragAnimationHelper(viewGroup, obj, this, recyclerViewDragHelper$$ExternalSyntheticLambda0, recyclerViewDragHelper$$ExternalSyntheticLambda1, recyclerViewDragHelper$$ExternalSyntheticLambda2);
        this.animationList.add(dragAnimationHelper2);
        return dragAnimationHelper2;
    }

    public final StateFlow getDragAnimationStateFlow() {
        return this.dragAnimationStateFlow;
    }

    public final boolean isFirstCrossRegion() {
        IntentTarget intentTarget;
        ItemDragCallBack itemDragCallBack = getItemDragCallBack();
        if (itemDragCallBack == null || (intentTarget = itemDragCallBack.getIntentTarget()) == null) {
            intentTarget = IntentTarget.NON;
        }
        return (intentTarget == this.crossDataDelegate.getCrossDragData().getCurrentDragIntent() || this.crossDataDelegate.getCrossDragData().getCurrentDragIntent() == IntentTarget.NON) ? false : true;
    }

    public final void locationFix(float f, float f2, boolean z) {
        Object next;
        RecyclerView recyclerView;
        Map.Entry entry;
        this.locationX = f - this.initialShadowPointX;
        float f3 = f2 - this.initialShadowPointY;
        this.locationY = f3;
        if (!z && this.isInOneHandMode) {
            this.locationY = (getScreenHeight() / 2.0f) + f3;
        }
        Rect rect = this.dragViewRect;
        int i = (int) this.locationX;
        int i2 = (int) this.locationY;
        OutputDragData outputDragData = this.outputDragData;
        int dragViewWidth = (outputDragData != null ? outputDragData.getDragViewWidth() : 0) + i;
        int i3 = (int) this.locationY;
        OutputDragData outputDragData2 = this.outputDragData;
        rect.set(i, i2, dragViewWidth, i3 + (outputDragData2 != null ? outputDragData2.getDragViewHeight() : 0));
        Iterator<T> it = this.recyclerViewLocation.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                entry = (Map.Entry) next;
                entry.getClass();
            }
        } while (!((Rect) entry.getValue()).contains((int) this.locationX, (int) this.locationY));
        Map.Entry entry2 = (Map.Entry) next;
        if (entry2 == null || (recyclerView = (RecyclerView) entry2.getKey()) == null) {
            Iterator<T> it2 = this.recyclerViewLocation.entrySet().iterator();
            if (!it2.hasNext()) {
                Regex$.ExternalSyntheticBUOutline0.m();
                return;
            }
            Object next2 = it2.next();
            if (it2.hasNext()) {
                Map.Entry entry3 = (Map.Entry) next2;
                entry3.getClass();
                int iAccess$calculateOverlapArea = Companion.access$calculateOverlapArea(Companion, (Rect) entry3.getValue(), this.dragViewRect);
                do {
                    Object next3 = it2.next();
                    Map.Entry entry4 = (Map.Entry) next3;
                    entry4.getClass();
                    int iAccess$calculateOverlapArea2 = Companion.access$calculateOverlapArea(Companion, (Rect) entry4.getValue(), this.dragViewRect);
                    if (iAccess$calculateOverlapArea < iAccess$calculateOverlapArea2) {
                        next2 = next3;
                        iAccess$calculateOverlapArea = iAccess$calculateOverlapArea2;
                    }
                } while (it2.hasNext());
            }
            recyclerView = (RecyclerView) ((Map.Entry) next2).getKey();
        }
        setRecyclerView(recyclerView);
    }

    public final void moveIfDragWaitingEnough(boolean z) {
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        ItemDragCallBack itemDragCallBack;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.LayoutManager layoutManager2;
        this.lastLocationX = this.locationX;
        this.lastLocationY = this.locationY;
        RecyclerView recyclerView3 = this.recyclerView;
        Boolean boolValueOf = (recyclerView3 == null || (layoutManager2 = recyclerView3.getLayoutManager()) == null) ? null : Boolean.valueOf(layoutManager2.isSmoothScrolling());
        boolean z2 = this.hasDropped;
        RecyclerView recyclerView4 = this.recyclerView;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "moveIfDragWaitingEnough isSmoothScrolling=" + boolValueOf + ", hasDropped=" + z2 + ", scrollState=" + (recyclerView4 != null ? Integer.valueOf(recyclerView4.getScrollState()) : null), null, 8);
        RecyclerView recyclerView5 = this.recyclerView;
        if ((recyclerView5 == null || (layoutManager = recyclerView5.getLayoutManager()) == null || !layoutManager.isSmoothScrolling()) && (recyclerView = this.recyclerView) != null && recyclerView.getScrollState() == 0 && !this.hasDropped) {
            CrossDragDataDelegate crossDragDataDelegate = this.crossDataDelegate;
            RecyclerViewDragHelper$$ExternalSyntheticLambda5 recyclerViewDragHelper$$ExternalSyntheticLambda5 = new RecyclerViewDragHelper$$ExternalSyntheticLambda5(0);
            recyclerViewDragHelper$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            crossDragDataDelegate.updateDragData(recyclerViewDragHelper$$ExternalSyntheticLambda5);
            OutputDragData outputDragData = this.outputDragData;
            if (outputDragData == null || (recyclerView2 = this.recyclerView) == null || (itemDragCallBack = getItemDragCallBack()) == null) {
                return;
            }
            itemDragCallBack.onMove(recyclerView2, outputDragData, this.locationX, this.locationY, z);
        }
    }

    public final void moveIfNecessary(boolean z) {
        ItemDragCallBack itemDragCallBack;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null || !recyclerView.isLayoutRequested()) {
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 != null) {
                recyclerView2.removeCallbacks(this.dragRunnable);
            }
            long dragWaitingTime = (z || (itemDragCallBack = getItemDragCallBack()) == null) ? 0L : itemDragCallBack.getDragWaitingTime();
            if (dragWaitingTime <= 0) {
                moveIfDragWaitingEnough(true);
                return;
            }
            RecyclerView recyclerView3 = this.recyclerView;
            if (recyclerView3 != null) {
                recyclerView3.postDelayed(this.dragRunnable, dragWaitingTime);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.OnDragEventListener
    public void onDragEnd(boolean z, float f, float f2, Object obj) {
        Context context;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.removeCallbacks(this.dragRunnable);
        }
        this.isDragMode = false;
        if (!this.hasDropped) {
            DragAnimationHelper dragAnimationHelper = this.dragAnimationHelper;
            if (dragAnimationHelper != null) {
                dragAnimationHelper.startDropAnim();
            }
            unregisterSwap();
        }
        this.hasDropped = false;
        SeparateDragTileData separateDragTileData = SeparateQSStatisicUtil.getSeparateDragTileData();
        ItemDragCallBack itemDragCallBack = getItemDragCallBack();
        separateDragTileData.setEndAreaIsA((itemDragCallBack != null ? itemDragCallBack.getIntentTarget() : null) == IntentTarget.PERSONAL_LIST);
        OutputDragData outputDragData = this.outputDragData;
        if (outputDragData != null) {
            Iterator<T> it = this.recyclerViewCallBack.values().iterator();
            while (it.hasNext()) {
                ((ItemDragCallBack) it.next()).onDragEnd(outputDragData);
            }
        }
        SeparateDragTileData separateDragTileData2 = SeparateQSStatisicUtil.getSeparateDragTileData();
        boolean z2 = separateDragTileData2.getStartAreaIsA() != separateDragTileData2.getEndAreaIsA();
        boolean zAreEqual = Intrinsics.areEqual(separateDragTileData2.getQsStartDragLoc(), separateDragTileData2.getQsEndDragLoc());
        boolean zAreEqual2 = Intrinsics.areEqual(separateDragTileData2.getQsStartDragLoc(), separateDragTileData2.getQsStartInvalidDragLoc());
        if (z2 || !(zAreEqual2 || zAreEqual)) {
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 != null && (context = recyclerView2.getContext()) != null) {
                SeparateQSStatisicUtil.getEditTitleData().setAreaBefore(separateDragTileData2.getStartAreaIsA() ? "A" : "B");
                SeparateQSStatisicUtil.getEditTitleData().setAreaAfter(separateDragTileData2.getEndAreaIsA() ? "A" : "B");
                SeparateQSStatisicUtil.collectEditTitleTypeEvent(context, "edit_tile_drag", false);
            }
        } else {
            SeparateQSStatisicUtil.getEditTitleData().resetData();
        }
        this.outputDragData = null;
        this.dragViewHolder = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDragEnd", null, 8);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.OnDragEventListener
    public void onDragLocation(float f, float f2, Object obj) {
        OutputDragData outputDragData = this.outputDragData;
        if (outputDragData != null) {
            locationFix(f, f2, false);
            DragAnimationHelper dragAnimationHelper = this.dragAnimationHelper;
            if (dragAnimationHelper != null) {
                dragAnimationHelper.updateLocation(this.locationX, this.locationY);
            }
            float fAbs = MathUtils.abs(this.lastLocationX - this.locationX);
            float f3 = this.touchSlop;
            if (fAbs >= f3 || MathUtils.abs(this.lastLocationY - this.locationY) >= f3) {
                this.lastLocationX = this.locationX;
                this.lastLocationY = this.locationY;
                moveIfNecessary(false);
                Iterator<T> it = this.recyclerViewCallBack.values().iterator();
                while (it.hasNext()) {
                    ((ItemDragCallBack) it.next()).onDragLocation(outputDragData, this.locationX, this.locationY);
                }
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.OnDragEventListener
    public void onDragStart(float f, float f2, Object obj) {
        RecyclerView.ViewHolder holder;
        RecyclerView.ViewHolder viewHolder;
        int i = 0;
        this.hasDropped = false;
        int i2 = 1;
        this.isDragMode = true;
        RecyclerView.ViewHolder viewHolder2 = null;
        InputDragData inputDragData = obj instanceof InputDragData ? (InputDragData) obj : null;
        this.initialShadowPointX = inputDragData != null ? inputDragData.getDragStartX() : 0;
        this.initialShadowPointY = inputDragData != null ? inputDragData.getDragStartY() : 0;
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        boolean oneHandedEnable = proxy != null ? proxy.getOneHandedEnable() : false;
        this.isInOneHandMode = oneHandedEnable;
        int i3 = this.initialShadowPointX;
        int i4 = this.initialShadowPointY;
        StringBuilder sb = new StringBuilder("onDragStart, dragData: ");
        sb.append(inputDragData);
        sb.append(", dragStartX: ");
        sb.append(f);
        sb.append(", dragStartY: ");
        ChildHelper$.ExternalSyntheticOutline0.m(f2, i3, ", initialShadowPointX: ", ", initialShadowPointY: ", sb);
        sb.append(i4);
        sb.append(", isInOneHandMode: ");
        sb.append(oneHandedEnable);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sb.toString(), null, 8);
        Object dragItemInfo = inputDragData != null ? inputDragData.getDragItemInfo() : null;
        if (dragItemInfo != null) {
            for (Map.Entry<RecyclerView, Rect> entry : this.recyclerViewLocation.entrySet()) {
                entry.getKey().getGlobalVisibleRect(entry.getValue());
            }
            Iterator<T> it = this.recyclerViewCallBack.values().iterator();
            do {
                if (!it.hasNext()) {
                    holder = null;
                    break;
                }
                holder = ((ItemDragCallBack) it.next()).getHolder(dragItemInfo);
            } while (holder == null);
            if (holder != null) {
                this.outputDragData = new OutputDragData(dragItemInfo, IntentTarget.NON, holder.itemView.getWidth(), holder.itemView.getHeight(), this.crossDataDelegate);
                viewHolder2 = holder;
            }
            this.dragViewHolder = viewHolder2;
            locationFix(f, f2, true);
            this.lastLocationX = this.locationX;
            this.lastLocationY = this.locationY;
            ItemDragCallBack itemDragCallBack = getItemDragCallBack();
            if (itemDragCallBack == null || (viewHolder = this.dragViewHolder) == null) {
                return;
            }
            DragAnimationHelper dragAnimationHelperFindAnimation = findAnimation(dragItemInfo);
            RecyclerViewDragHelper$$ExternalSyntheticLambda3 recyclerViewDragHelper$$ExternalSyntheticLambda3 = new RecyclerViewDragHelper$$ExternalSyntheticLambda3(i);
            recyclerViewDragHelper$$ExternalSyntheticLambda3.f$0 = itemDragCallBack;
            recyclerViewDragHelper$$ExternalSyntheticLambda3.f$1 = dragItemInfo;
            recyclerViewDragHelper$$ExternalSyntheticLambda3.f$2 = this;
            recyclerViewDragHelper$$ExternalSyntheticLambda3.f$3 = viewHolder;
            VarHandle.storeStoreFence();
            float f3 = this.locationX;
            float f4 = this.locationY;
            RecyclerViewDragHelper$$ExternalSyntheticLambda3 recyclerViewDragHelper$$ExternalSyntheticLambda4 = new RecyclerViewDragHelper$$ExternalSyntheticLambda3(i2);
            recyclerViewDragHelper$$ExternalSyntheticLambda4.f$2 = this;
            recyclerViewDragHelper$$ExternalSyntheticLambda4.f$1 = dragItemInfo;
            recyclerViewDragHelper$$ExternalSyntheticLambda4.f$0 = itemDragCallBack;
            recyclerViewDragHelper$$ExternalSyntheticLambda4.f$3 = viewHolder;
            VarHandle.storeStoreFence();
            dragAnimationHelperFindAnimation.startDragAnim(recyclerViewDragHelper$$ExternalSyntheticLambda3, f3, f4, recyclerViewDragHelper$$ExternalSyntheticLambda4);
            this.dragAnimationHelper = dragAnimationHelperFindAnimation;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.OnDragEventListener
    public void onDrop(float f, float f2, Object obj) {
        boolean zAreEqual;
        RecyclerView recyclerView;
        RecyclerView.ViewHolder copyViewHolder;
        View view;
        boolean z = this.hasDropped;
        RecyclerView recyclerView2 = this.recyclerView;
        Boolean boolValueOf = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDrop hasDropped=" + z + " scrollState=" + (recyclerView2 != null ? Integer.valueOf(recyclerView2.getScrollState()) : null), null, 8);
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.removeCallbacks(this.dragRunnable);
        }
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 != null) {
            recyclerView4.removeCallbacks(this.onDropRunnable);
        }
        locationFix(f, f2, false);
        OutputDragData outputDragData = this.outputDragData;
        if (outputDragData != null) {
            ItemDragCallBack itemDragCallBack = getItemDragCallBack();
            zAreEqual = Intrinsics.areEqual(itemDragCallBack != null ? Boolean.valueOf(itemDragCallBack.isFullEmpty(outputDragData, this.locationX, this.locationY)) : null, Boolean.TRUE);
        } else {
            zAreEqual = false;
        }
        boolean zIsFirstCrossRegion = isFirstCrossRegion();
        CopyViewProxy copyViewProxy = this.copyViewProxy;
        if (copyViewProxy != null && (copyViewHolder = copyViewProxy.getCopyViewHolder()) != null && (view = copyViewHolder.itemView) != null) {
            boolValueOf = Boolean.valueOf(view.isLayoutRequested());
        }
        int i = 1;
        boolean z2 = !zIsFirstCrossRegion || Intrinsics.areEqual(boolValueOf, Boolean.FALSE);
        if (!zAreEqual || !z2 || (recyclerView = this.recyclerView) == null || recyclerView.getScrollState() != 0) {
            doOnDrop();
            return;
        }
        moveIfNecessary(true);
        RecyclerView recyclerView5 = this.recyclerView;
        if (recyclerView5 != null) {
            RecyclerViewDragHelper$dragRunnable$1 recyclerViewDragHelper$dragRunnable$1 = new RecyclerViewDragHelper$dragRunnable$1(i);
            recyclerViewDragHelper$dragRunnable$1.this$0 = this;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(recyclerView5, recyclerViewDragHelper$dragRunnable$1);
        }
    }

    public final void registerRecyclerView(RecyclerView recyclerView, ItemDragCallBack itemDragCallBack) {
        this.recyclerViewLocation.put(recyclerView, new Rect());
        this.recyclerViewCallBack.put(recyclerView, itemDragCallBack);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.IElevationAdjust
    public void resetElevation(RecyclerView.ViewHolder viewHolder) {
        ItemDragViewElevationUtil.resetElevation(viewHolder);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.IElevationAdjust
    public void setElevation(RecyclerView.ViewHolder viewHolder) {
        ItemDragViewElevationUtil.setElevation(this.recyclerViewCallBack.keySet(), viewHolder);
    }

    public final void stopDrag() {
        RecyclerView.ViewHolder hostViewHolder;
        View view = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[stopDrag] " + this.dragViewHolder, null, 8);
        CopyViewProxy copyViewProxy = this.copyViewProxy;
        if (copyViewProxy != null ? (hostViewHolder = copyViewProxy.getHostViewHolder()) != null : (hostViewHolder = this.dragViewHolder) != null) {
            view = hostViewHolder.itemView;
        }
        if (view != null) {
            view.cancelDragAndDrop();
        }
    }

    public final void unRegisterRecyclerView(RecyclerView recyclerView) {
        this.recyclerViewLocation.remove(recyclerView);
        this.recyclerViewCallBack.remove(recyclerView);
    }

    public final void unregisterSwap() {
        Object dragItemInfo;
        if (this.isDragMode || !this.dropAnimationList.isEmpty()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "unregisterSwap fail", null, 8);
            return;
        }
        for (Map.Entry<RecyclerView, ItemDragCallBack> entry : this.recyclerViewCallBack.entrySet()) {
            entry.getKey();
            ItemDragCallBack value = entry.getValue();
            OutputDragData outputDragData = this.outputDragData;
            if (outputDragData != null && (dragItemInfo = outputDragData.getDragItemInfo()) != null) {
                value.registerViewHolderAdd(dragItemInfo, null);
            }
        }
    }
}
