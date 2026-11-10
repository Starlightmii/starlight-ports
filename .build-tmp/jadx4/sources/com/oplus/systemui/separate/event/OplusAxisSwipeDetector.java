package com.oplus.systemui.separate.event;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.oplus.systemui.separate.OplusPanelViewPagerController;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAxisSwipeDetector extends OplusSwipeDetector {
    private final ScrollListener scrollListener;
    private ScrollCondition xScroll;
    private ScrollCondition yScroll;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ScrollCondition {
        boolean canStartScroll(PointF pointF);

        float getOverScrollMaxDisplacement();

        float getScrollNextFlingMinDisplacement();

        float getScrollNextFlingMinVelocity();

        float getScrollNextMinDisplacement();

        boolean isHotArea(PointF pointF);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ScrollListener {
        boolean isInScrollXHotZone(PointF pointF);

        boolean isInScrollXOverHotArea(PointF pointF);

        boolean isInScrollYHotZone(PointF pointF);

        void onScrollBlockEnd(PointF pointF, PointF pointF2, boolean z);

        void onScrollBlockStart(MotionEvent motionEvent, PointF pointF);

        void onScrollBlocking(PointF pointF);

        void onScrollX(float f, MotionEvent motionEvent);

        void onScrollXEnd(PointF pointF, float f, MotionEvent motionEvent, boolean z);

        void onScrollXEndOverScroll(PointF pointF, float f, MotionEvent motionEvent, boolean z);

        void onScrollXOverScroll(float f, MotionEvent motionEvent);

        void onScrollY(float f, MotionEvent motionEvent);

        void onScrollYEnd(PointF pointF, float f, MotionEvent motionEvent, boolean z);

        void onScrollYStart(float f, MotionEvent motionEvent);
    }

    public OplusAxisSwipeDetector(Context context, ScrollListener scrollListener) {
        super(context, ViewConfiguration.get(context));
        this.scrollListener = scrollListener;
        OplusAxisSwipeDetector$xScroll$1 oplusAxisSwipeDetector$xScroll$1 = new OplusAxisSwipeDetector$xScroll$1();
        oplusAxisSwipeDetector$xScroll$1.$context = context;
        oplusAxisSwipeDetector$xScroll$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.xScroll = oplusAxisSwipeDetector$xScroll$1;
        OplusAxisSwipeDetector$yScroll$1 oplusAxisSwipeDetector$yScroll$1 = new OplusAxisSwipeDetector$yScroll$1();
        oplusAxisSwipeDetector$yScroll$1.this$0 = this;
        oplusAxisSwipeDetector$yScroll$1.$context = context;
        VarHandle.storeStoreFence();
        this.yScroll = oplusAxisSwipeDetector$yScroll$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getScreenWidth() {
        Rect bounds = getContext().getResources().getConfiguration().windowConfiguration.getBounds();
        return bounds.right - bounds.left;
    }

    public final ScrollCondition getYScroll() {
        return this.yScroll;
    }

    public final float getYScrollAppearDisplacement() {
        return this.yScroll.getScrollNextMinDisplacement();
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isAnimationBlocking() {
        return ((OplusPanelViewPagerController) this.scrollListener).isAnimationBlocking();
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isAnimationRunningInSeqEvent() {
        return ((OplusPanelViewPagerController) this.scrollListener).isAnimationRunningInSeqEvent();
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public boolean isInXHotArea(PointF pointF) {
        return this.xScroll.isHotArea(pointF);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public boolean isInXOverScrollHotArea(PointF pointF) {
        return this.scrollListener.isInScrollXOverHotArea(pointF);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public boolean isInYHotArea(PointF pointF) {
        return this.yScroll.isHotArea(pointF);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isKeyGuardShowing() {
        return ((OplusPanelViewPagerController) this.scrollListener).isKeyguardVisible$1();
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isNotificationListBeDragged() {
        return ((OplusPanelViewPagerController) this.scrollListener).isNotificationListBeDrag();
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isValidArea(float f) {
        boolean zIsRightDownExpanded = ((OplusPanelViewPagerController) this.scrollListener).isRightDownExpanded();
        ScrollListener scrollListener = this.scrollListener;
        if (zIsRightDownExpanded) {
            return !((OplusPanelViewPagerController) scrollListener).isRightArea(f);
        }
        return ((OplusPanelViewPagerController) scrollListener).isRightArea(f);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public final boolean isValidMoveDisplacement() {
        if (((OplusPanelViewPagerController) this.scrollListener).isRightDownExpanded()) {
            return Float.compare(getDisplacement().x, 0.0f) > 0;
        }
        return Float.compare(getDisplacement().x, 0.0f) < 0;
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public void reportBlockingStartInternal(MotionEvent motionEvent) {
        this.scrollListener.onScrollBlockStart(motionEvent, getDisplacement());
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public void reportDragEndInternal(PointF pointF, MotionEvent motionEvent, PointF pointF2) {
        float f = (isDraggingX() || isBlocking()) ? pointF.x : pointF.y;
        if (isDraggingX()) {
            ScrollListener scrollListener = this.scrollListener;
            float f2 = pointF2.x;
            scrollListener.onScrollXEnd(pointF, f2, motionEvent, shouldScrollXToNext(f, f2));
            return;
        }
        if (isDraggingXOverScroll()) {
            ScrollListener scrollListener2 = this.scrollListener;
            float f3 = pointF2.x;
            scrollListener2.onScrollXEndOverScroll(pointF, f3, motionEvent, shouldScrollXToNext(f, f3));
        } else {
            if (!isDraggingY()) {
                if (isBlocking()) {
                    this.scrollListener.onScrollBlockEnd(pointF, pointF2, shouldScrollXToNext(f, pointF2.x));
                    return;
                }
                return;
            }
            ScrollListener scrollListener3 = this.scrollListener;
            float f4 = pointF2.y;
            boolean z = false;
            if (f > (-this.yScroll.getScrollNextFlingMinVelocity()) && f4 >= 0.0f && (Math.abs(f) >= this.yScroll.getScrollNextFlingMinVelocity() || Float.compare(Math.abs(f4), this.yScroll.getScrollNextMinDisplacement()) >= 0)) {
                z = true;
            }
            scrollListener3.onScrollYEnd(pointF, f4, motionEvent, z);
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public void reportDragXStartInternal(OplusSwipeDetector.ScrollState scrollState) {
        boolean zIsDraggingXOverScroll = isDraggingXOverScroll(scrollState);
        ScrollListener scrollListener = this.scrollListener;
        if (zIsDraggingXOverScroll) {
            ((OplusPanelViewPagerController) scrollListener).onScrollXStartOverScroll(getSubtractDisplacement().x);
        } else {
            ((OplusPanelViewPagerController) scrollListener).onScrollXStart(getSubtractDisplacement().x);
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public void reportDragYStartInternal(MotionEvent motionEvent) {
        this.scrollListener.onScrollYStart(getSubtractDisplacement().y, motionEvent);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public void reportDraggingInternal(PointF pointF, MotionEvent motionEvent) {
        if (isDraggingX()) {
            this.scrollListener.onScrollX(pointF.x, motionEvent);
            return;
        }
        if (isDraggingXOverScroll()) {
            this.scrollListener.onScrollXOverScroll(pointF.x, motionEvent);
        } else if (isDraggingY()) {
            this.scrollListener.onScrollY(pointF.y, motionEvent);
        } else if (isBlocking()) {
            this.scrollListener.onScrollBlocking(pointF);
        }
    }

    public final void setYScroll(ScrollCondition scrollCondition) {
        this.yScroll = scrollCondition;
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public boolean shouldScrollStartInX(PointF pointF) {
        return this.xScroll.canStartScroll(pointF);
    }

    @Override // com.oplus.systemui.separate.event.OplusSwipeDetector
    public boolean shouldScrollStartInY(PointF pointF) {
        return this.yScroll.canStartScroll(pointF);
    }

    public final boolean shouldScrollXToNext(float f, float f2) {
        if (!((OplusPanelViewPagerController) this.scrollListener).isRightDownExpanded()) {
            if (f <= 0.0f && f2 <= 0.0f) {
            }
        }
        if (f < 0.0f || f2 < 0.0f) {
            return false;
        }
        return (Math.abs(f) >= this.xScroll.getScrollNextFlingMinVelocity() && Math.abs(f2) >= this.xScroll.getScrollNextFlingMinDisplacement()) || Float.compare(Math.abs(f2), this.xScroll.getScrollNextMinDisplacement()) >= 0;
    }

    public final boolean shouldScrollXToNext() {
        return Float.compare(Math.abs(getDisplacement().x), this.xScroll.getScrollNextMinDisplacement()) >= 0;
    }
}
