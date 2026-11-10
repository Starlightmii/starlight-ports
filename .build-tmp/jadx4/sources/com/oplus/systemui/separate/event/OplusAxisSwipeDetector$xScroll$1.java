package com.oplus.systemui.separate.event;

import android.content.Context;
import android.graphics.PointF;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAxisSwipeDetector$xScroll$1 implements OplusAxisSwipeDetector.ScrollCondition {
    public /* synthetic */ Context $context;
    public /* synthetic */ OplusAxisSwipeDetector this$0;

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public final boolean canStartScroll(PointF pointF) {
        return Float.compare(Math.abs(pointF.x), this.this$0.getInSensitiveTouchSlop()) >= 0;
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public float getOverScrollMaxDisplacement() {
        return OplusSwipeDetector.Companion.getMAX_DISPLACEMENT_SCALE_FOR_OVERSCROLL() * this.this$0.getScreenWidth();
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public float getScrollNextFlingMinDisplacement() {
        return this.$context.getResources().getDimensionPixelSize(2131172013);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public float getScrollNextFlingMinVelocity() {
        return this.$context.getResources().getDimensionPixelSize(2131172014);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public float getScrollNextMinDisplacement() {
        return this.$context.getResources().getDimensionPixelSize(2131172015);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollCondition
    public final boolean isHotArea(PointF pointF) {
        return this.this$0.scrollListener.isInScrollXHotZone(pointF);
    }
}
