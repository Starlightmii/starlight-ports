package com.oplus.systemui.separate.event;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusSwipeDetector {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSwipeDetector";
    private BlockScrollMode blockScrollMode;
    private final Context context;
    public MotionEvent currentDownEvent;
    private ScrollState currentScrollState;
    public MotionEvent currentUpEvent;
    public boolean downInXHotArea;
    public boolean downInXOverScrollHotArea;
    public boolean downInYHotArea;
    public final float inSensitiveTouchSlop;
    public boolean isNotificationSlop;
    public boolean isSettingState;
    private ScrollState latestScrollState;
    public final float maxVelocity;
    public final float touchSlop;
    private final PointF downPos = new PointF(-1.0f, -1.0f);
    private final PointF downFirstPos = new PointF(-1.0f, -1.0f);
    private final PointF currentMovePos = new PointF();
    private final PointF lastPos = new PointF();
    private final Queue<Runnable> setStateQueue = new LinkedList();
    public int activePointerId = -1;
    private final Lazy velocityTracker$delegate = LazyKt__LazyJVMKt.lazy(new OplusSwipeDetector$$ExternalSyntheticLambda0());
    private PointF lastDisplacement = new PointF();
    private PointF subtractDisplacement = new PointF();
    private PointF displacement = new PointF();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BlockScrollMode {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ BlockScrollMode[] $VALUES;
        public static final BlockScrollMode HORIZONTAL;
        public static final BlockScrollMode UNDECIDED;
        public static final BlockScrollMode VERTICAL;

        static {
            BlockScrollMode blockScrollMode = new BlockScrollMode("UNDECIDED", 0);
            UNDECIDED = blockScrollMode;
            BlockScrollMode blockScrollMode2 = new BlockScrollMode("HORIZONTAL", 1);
            HORIZONTAL = blockScrollMode2;
            BlockScrollMode blockScrollMode3 = new BlockScrollMode("VERTICAL", 2);
            VERTICAL = blockScrollMode3;
            BlockScrollMode[] blockScrollModeArr = {blockScrollMode, blockScrollMode2, blockScrollMode3};
            $VALUES = blockScrollModeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(blockScrollModeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static BlockScrollMode valueOf(String str) {
            return (BlockScrollMode) Enum.valueOf(BlockScrollMode.class, str);
        }

        public static BlockScrollMode[] values() {
            return (BlockScrollMode[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getINSENSITIVE_TOUCH_SLOP_RATIO$annotations() {
        }

        public static /* synthetic */ void getMAX_DISPLACEMENT_SCALE_FOR_OVERSCROLL$annotations() {
        }

        public static /* synthetic */ void getOVERSCROLL_FORMULA_CURVE_RATIO$annotations() {
        }

        public static /* synthetic */ void getOVERSCROLL_FORMULA_MAX_DISTANCE$annotations() {
        }

        public static /* synthetic */ void getQS_EXPAND_MIN_DISPLACEMENT$annotations() {
        }

        public static /* synthetic */ void getVELOCITY_UNI$annotations() {
        }

        public final float getINSENSITIVE_TOUCH_SLOP_RATIO() {
            return 1.6f;
        }

        public final float getMAX_DISPLACEMENT_SCALE_FOR_OVERSCROLL() {
            return 0.25f;
        }

        public final float getOVERSCROLL_FORMULA_CURVE_RATIO() {
            return 0.3f;
        }

        public final float getOVERSCROLL_FORMULA_MAX_DISTANCE() {
            return 1200.0f;
        }

        public final float getQS_EXPAND_MIN_DISPLACEMENT() {
            return 150.0f;
        }

        public final int getVELOCITY_UNI() {
            return 1000;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScrollState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ScrollState[] $VALUES;
        public static final ScrollState BLOCKING;
        public static final ScrollState DRAGGING_X;
        public static final ScrollState DRAGGING_X_OVERSCROLL;
        public static final ScrollState DRAGGING_Y;
        public static final ScrollState IDLE;
        public static final ScrollState SETTLING;

        static {
            ScrollState scrollState = new ScrollState("IDLE", 0);
            IDLE = scrollState;
            ScrollState scrollState2 = new ScrollState("DRAGGING_X", 1);
            DRAGGING_X = scrollState2;
            ScrollState scrollState3 = new ScrollState("DRAGGING_X_OVERSCROLL", 2);
            DRAGGING_X_OVERSCROLL = scrollState3;
            ScrollState scrollState4 = new ScrollState("DRAGGING_Y", 3);
            DRAGGING_Y = scrollState4;
            ScrollState scrollState5 = new ScrollState("SETTLING", 4);
            SETTLING = scrollState5;
            ScrollState scrollState6 = new ScrollState("BLOCKING", 5);
            BLOCKING = scrollState6;
            ScrollState[] scrollStateArr = {scrollState, scrollState2, scrollState3, scrollState4, scrollState5, scrollState6};
            $VALUES = scrollStateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(scrollStateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static ScrollState valueOf(String str) {
            return (ScrollState) Enum.valueOf(ScrollState.class, str);
        }

        public static ScrollState[] values() {
            return (ScrollState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.separate.event.OplusSwipeDetector$setState$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ ScrollState $newState;
        public /* synthetic */ OplusSwipeDetector this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.setState(this.$newState);
        }
    }

    public OplusSwipeDetector(Context context, ViewConfiguration viewConfiguration) {
        this.context = context;
        ScrollState scrollState = ScrollState.IDLE;
        this.currentScrollState = scrollState;
        this.latestScrollState = scrollState;
        this.currentUpEvent = MotionEvent.obtain(0L, 0L, 1, 0.0f, 0.0f, 0);
        this.currentDownEvent = MotionEvent.obtain(0L, 0L, 0, 0.0f, 0.0f, 0);
        this.blockScrollMode = BlockScrollMode.UNDECIDED;
        float scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.touchSlop = scaledTouchSlop;
        this.inSensitiveTouchSlop = scaledTouchSlop * 1.6f;
        this.maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public static final float getINSENSITIVE_TOUCH_SLOP_RATIO() {
        return Companion.getINSENSITIVE_TOUCH_SLOP_RATIO();
    }

    public static final float getMAX_DISPLACEMENT_SCALE_FOR_OVERSCROLL() {
        return Companion.getMAX_DISPLACEMENT_SCALE_FOR_OVERSCROLL();
    }

    public static final float getOVERSCROLL_FORMULA_CURVE_RATIO() {
        return Companion.getOVERSCROLL_FORMULA_CURVE_RATIO();
    }

    public static final float getOVERSCROLL_FORMULA_MAX_DISTANCE() {
        return Companion.getOVERSCROLL_FORMULA_MAX_DISTANCE();
    }

    public static final float getQS_EXPAND_MIN_DISPLACEMENT() {
        return Companion.getQS_EXPAND_MIN_DISPLACEMENT();
    }

    public static final int getVELOCITY_UNI() {
        return Companion.getVELOCITY_UNI();
    }

    private final VelocityTracker getVelocityTracker() {
        return (VelocityTracker) this.velocityTracker$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(ScrollState scrollState) {
        if (this.isSettingState) {
            Queue<Runnable> queue = this.setStateQueue;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$newState = scrollState;
            VarHandle.storeStoreFence();
            queue.add(anonymousClass1);
            return;
        }
        this.isSettingState = true;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setState:" + this.currentScrollState + "->" + scrollState, null, 8);
        if (scrollState == ScrollState.DRAGGING_X || scrollState == ScrollState.DRAGGING_Y || scrollState == ScrollState.DRAGGING_X_OVERSCROLL) {
            PointF pointF = this.subtractDisplacement;
            PointF pointF2 = this.displacement;
            float f = pointF2.x;
            float f2 = this.touchSlop;
            pointF.x = f > 0.0f ? f2 : -f2;
            if (pointF2.y <= 0.0f) {
                f2 = -f2;
            }
            pointF.y = f2;
            ScrollState scrollState2 = this.currentScrollState;
            if (scrollState2 == ScrollState.IDLE || scrollState2 == ScrollState.SETTLING) {
                reportDragStart(scrollState);
            }
        }
        if (scrollState == ScrollState.SETTLING) {
            reportDragEnd(this.currentUpEvent);
        }
        this.currentScrollState = scrollState;
        this.isSettingState = false;
        if (this.setStateQueue.isEmpty()) {
            return;
        }
        this.setStateQueue.remove().run();
    }

    public final void addMovement(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        getVelocityTracker().addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    public final void finishedScrolling() {
        setState(ScrollState.IDLE);
        reset();
    }

    public final BlockScrollMode getBlockScrollMode() {
        return this.blockScrollMode;
    }

    public final Context getContext() {
        return this.context;
    }

    public final PointF getDisplacement() {
        return this.displacement;
    }

    public final PointF getDownFirstPos() {
        return this.downFirstPos;
    }

    public final PointF getDownPos() {
        return this.downPos;
    }

    public final float getInSensitiveTouchSlop() {
        return this.inSensitiveTouchSlop;
    }

    public final ScrollState getLatestScrollState() {
        return this.latestScrollState;
    }

    public final PointF getSubtractDisplacement() {
        return this.subtractDisplacement;
    }

    public abstract boolean isAnimationBlocking();

    public abstract boolean isAnimationRunningInSeqEvent();

    public final boolean isBlocking() {
        return this.currentScrollState == ScrollState.BLOCKING;
    }

    public final boolean isDragging() {
        return isDraggingX() || isDraggingY() || isDraggingXOverScroll();
    }

    public final boolean isDraggingAndNotSettingState() {
        return (isDraggingX() || isDraggingY() || isDraggingXOverScroll()) && !this.isSettingState;
    }

    public final boolean isDraggingOrSettling() {
        return isDragging() || this.currentScrollState == ScrollState.SETTLING;
    }

    public final boolean isDraggingOrSettlingOrBlocking() {
        return isDragging() || this.currentScrollState == ScrollState.SETTLING || isBlocking();
    }

    public final boolean isDraggingX() {
        return this.currentScrollState == ScrollState.DRAGGING_X;
    }

    public final boolean isDraggingXOverScroll() {
        return this.currentScrollState == ScrollState.DRAGGING_X_OVERSCROLL;
    }

    public final boolean isDraggingY() {
        return this.currentScrollState == ScrollState.DRAGGING_Y;
    }

    public abstract boolean isInXHotArea(PointF pointF);

    public abstract boolean isInXOverScrollHotArea(PointF pointF);

    public abstract boolean isInYHotArea(PointF pointF);

    public abstract boolean isKeyGuardShowing();

    public final boolean isLastDragX() {
        return this.latestScrollState == ScrollState.DRAGGING_X;
    }

    public final boolean isLastDragXOver() {
        return this.latestScrollState == ScrollState.DRAGGING_X_OVERSCROLL;
    }

    public abstract boolean isNotificationListBeDragged();

    public abstract boolean isValidArea(float f);

    public abstract boolean isValidMoveDisplacement();

    public final boolean isXOverScroll() {
        return this.currentScrollState != ScrollState.IDLE;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            getVelocityTracker().clear();
        }
        if (actionMasked == 0) {
            addMovement(motionEvent);
            this.activePointerId = motionEvent.getPointerId(0);
            this.downPos.set(motionEvent.getX(), motionEvent.getY());
            this.downFirstPos.set(motionEvent.getX(), motionEvent.getY());
            this.lastPos.set(this.downPos);
            this.lastDisplacement.set(0.0f, 0.0f);
            this.displacement.set(0.0f, 0.0f);
            this.currentDownEvent = MotionEvent.obtain(motionEvent);
            this.downInXHotArea = isInXHotArea(this.downPos);
            this.downInYHotArea = isInYHotArea(this.downPos);
            updateInXOverScrollHotAreaIfNeeded();
            this.isNotificationSlop = false;
            this.blockScrollMode = BlockScrollMode.UNDECIDED;
            return true;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                addMovement(motionEvent);
                int iFindPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
                if (iFindPointerIndex != -1 && Float.compare(this.downPos.x, -1.0f) != 0 && Float.compare(this.downPos.y, -1.0f) != 0) {
                    this.currentMovePos.set(motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex));
                    PointF pointF = this.displacement;
                    PointF pointF2 = this.currentMovePos;
                    float f = pointF2.x;
                    PointF pointF3 = this.downPos;
                    pointF.set(f - pointF3.x, pointF2.y - pointF3.y);
                    this.lastPos.set(motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex));
                    boolean zIsAnimationBlocking = isAnimationBlocking();
                    float f2 = this.touchSlop;
                    if (!zIsAnimationBlocking && !isBlocking()) {
                        if (this.blockScrollMode == BlockScrollMode.UNDECIDED) {
                            if (Math.abs(this.displacement.y) >= f2 && Math.abs(this.displacement.y) >= Math.abs(this.displacement.x)) {
                                this.blockScrollMode = BlockScrollMode.VERTICAL;
                            } else if (Math.abs(this.displacement.x) >= this.inSensitiveTouchSlop && Math.abs(this.displacement.x) > Math.abs(this.displacement.y)) {
                                this.blockScrollMode = BlockScrollMode.HORIZONTAL;
                            }
                        }
                        BlockScrollMode blockScrollMode = this.blockScrollMode;
                        BlockScrollMode blockScrollMode2 = BlockScrollMode.VERTICAL;
                        if (blockScrollMode != blockScrollMode2 && !isBlocking() && !isDragging() && !isKeyGuardShowing()) {
                            if (this.isNotificationSlop) {
                                z = true;
                            } else {
                                if (isNotificationListBeDragged() && !this.downInYHotArea && shouldScrollStartInY(this.displacement) && Math.abs(this.displacement.y) > Math.abs(this.displacement.x)) {
                                    this.isNotificationSlop = true;
                                    this.blockScrollMode = blockScrollMode2;
                                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNotifyShadeTouchInterceptor setter:true", null, 8);
                                }
                                z = this.isNotificationSlop;
                            }
                            if (!z && this.downInXHotArea && shouldScrollStartInX(this.displacement) && Math.abs(this.displacement.x) > Math.abs(this.displacement.y) && !isAnimationRunningInSeqEvent()) {
                                if (isValidMoveDisplacement()) {
                                    ScrollState scrollState = ScrollState.DRAGGING_X;
                                    this.latestScrollState = scrollState;
                                    setState(scrollState);
                                } else if (this.downInXOverScrollHotArea) {
                                    ScrollState scrollState2 = ScrollState.DRAGGING_X_OVERSCROLL;
                                    this.latestScrollState = scrollState2;
                                    setState(scrollState2);
                                }
                            }
                        }
                        PointF pointF4 = this.downFirstPos;
                        if (this.blockScrollMode != BlockScrollMode.HORIZONTAL && !isBlocking() && !isDragging() && !isKeyGuardShowing() && this.downInYHotArea && shouldScrollStartInY(this.displacement) && Math.abs(this.displacement.x) < Math.abs(this.displacement.y) && Float.compare(this.displacement.y, 0.0f) > 0 && isValidArea(pointF4.x)) {
                            ScrollState scrollState3 = ScrollState.DRAGGING_Y;
                            this.latestScrollState = scrollState3;
                            setState(scrollState3);
                        }
                    } else if (!isBlocking() && !isDragging() && ((Math.abs(this.displacement.x) >= f2 || Math.abs(this.displacement.y) >= f2) && (Math.abs(this.displacement.x) >= Math.abs(this.displacement.y) || this.displacement.y >= 0.0f))) {
                        setState(ScrollState.BLOCKING);
                        reportBlockingStartInternal(motionEvent);
                        reportDragging(motionEvent);
                    }
                    if (isDragging()) {
                        reportDragging(motionEvent);
                        return true;
                    }
                    if (isBlocking()) {
                        reportDragging(motionEvent);
                    }
                }
            } else if (actionMasked != 3) {
                if (actionMasked == 6) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == this.activePointerId) {
                        int i = actionIndex == 0 ? 1 : 0;
                        this.downPos.set(motionEvent.getX(i) - (this.lastPos.x - this.downPos.x), motionEvent.getY(i) - (this.lastPos.y - this.downPos.y));
                        this.lastPos.set(motionEvent.getX(i), motionEvent.getY(i));
                        this.activePointerId = motionEvent.getPointerId(i);
                        return true;
                    }
                }
            }
            return true;
        }
        addMovement(motionEvent);
        this.currentUpEvent = MotionEvent.obtain(motionEvent);
        if (isDragging()) {
            setState(ScrollState.SETTLING);
        } else if (isBlocking()) {
            reportDragEnd(this.currentUpEvent);
            setState(ScrollState.IDLE);
        } else {
            reset();
        }
        getVelocityTracker().clear();
        return true;
    }

    public abstract void reportBlockingStartInternal(MotionEvent motionEvent);

    public final void reportDragEnd(MotionEvent motionEvent) {
        getVelocityTracker().computeCurrentVelocity(1000, this.maxVelocity);
        PointF pointF = new PointF(getVelocityTracker().getXVelocity(), getVelocityTracker().getYVelocity());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            PointF pointF2 = this.displacement;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("reportDragEnd displacement.x=", pointF2.x, " displacement.y=", pointF2.y, "  velocity=");
            sbM.append(pointF);
            Log.d("SystemUi--QuickSettings", "OplusSwipeDetector-->".concat(sbM.toString()));
        }
        reportDragEndInternal(pointF, motionEvent, this.lastDisplacement);
    }

    public abstract void reportDragEndInternal(PointF pointF, MotionEvent motionEvent, PointF pointF2);

    public final void reportDragStart(ScrollState scrollState) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "reportDragStart state:" + scrollState, null, 8);
        if (scrollState == ScrollState.DRAGGING_X || isDraggingXOverScroll(scrollState)) {
            reportDragXStartInternal(scrollState);
        } else if (scrollState == ScrollState.DRAGGING_Y) {
            reportDragYStartInternal(this.currentDownEvent);
        }
    }

    public abstract void reportDragXStartInternal(ScrollState scrollState);

    public abstract void reportDragYStartInternal(MotionEvent motionEvent);

    public final void reportDragging(MotionEvent motionEvent) {
        if (this.displacement != this.lastDisplacement) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                PointF pointF = this.displacement;
                Log.d("SystemUi--QuickSettings", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("reportDragging displacement.x=", pointF.x, " displacement.y=", pointF.y, "OplusSwipeDetector-->"));
            }
            this.lastDisplacement.set(this.displacement);
            reportDraggingInternal(this.lastDisplacement, motionEvent);
        }
    }

    public abstract void reportDraggingInternal(PointF pointF, MotionEvent motionEvent);

    public final void reset() {
        this.activePointerId = -1;
        this.downPos.set(-1.0f, -1.0f);
        this.downFirstPos.set(-1.0f, -1.0f);
        this.lastPos.set(this.downPos);
        this.lastDisplacement.set(0.0f, 0.0f);
        this.displacement.set(0.0f, 0.0f);
        this.downInXHotArea = false;
        this.downInYHotArea = false;
        this.downInXOverScrollHotArea = false;
        this.isNotificationSlop = false;
        this.blockScrollMode = BlockScrollMode.UNDECIDED;
    }

    public final void setBlockScrollMode(BlockScrollMode blockScrollMode) {
        this.blockScrollMode = blockScrollMode;
    }

    public final void setDisplacement(PointF pointF) {
        this.displacement = pointF;
    }

    public final void setLatestScrollState(ScrollState scrollState) {
        this.latestScrollState = scrollState;
    }

    public final void setSubtractDisplacement(PointF pointF) {
        this.subtractDisplacement = pointF;
    }

    public abstract boolean shouldScrollStartInX(PointF pointF);

    public abstract boolean shouldScrollStartInY(PointF pointF);

    public final void updateInXOverScrollHotAreaIfNeeded() {
        this.downInXOverScrollHotArea = isInXOverScrollHotArea(this.downPos);
    }

    public final boolean isDraggingXOverScroll(ScrollState scrollState) {
        return scrollState == ScrollState.DRAGGING_X_OVERSCROLL;
    }
}
