package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.systemui.shade.NotificationPanelViewController$;
import kotlin.Pair;
import kotlin.TuplesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMotionTracker {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleMotionTracker";
    public boolean beingDragged;
    private final Context context;
    public boolean horizontalDragging;
    private MotionPoint lastMotionPoint = new MotionPoint();
    private VelocityTracker velocityTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MoveDiffInfo {
        public final boolean horizontalDragging;
        public final float xDiff;
        public final float yDiff;

        public MoveDiffInfo(float f, float f2, boolean z) {
            this.horizontalDragging = z;
            this.xDiff = f;
            this.yDiff = f2;
        }

        public final MoveDiffInfo copy(boolean z, float f, float f2) {
            return new MoveDiffInfo(f, f2, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MoveDiffInfo)) {
                return false;
            }
            MoveDiffInfo moveDiffInfo = (MoveDiffInfo) obj;
            return this.horizontalDragging == moveDiffInfo.horizontalDragging && Float.compare(this.xDiff, moveDiffInfo.xDiff) == 0 && Float.compare(this.yDiff, moveDiffInfo.yDiff) == 0;
        }

        public final boolean getHorizontalDragging() {
            return this.horizontalDragging;
        }

        public final float getXDiff() {
            return this.xDiff;
        }

        public final float getYDiff() {
            return this.yDiff;
        }

        public final int hashCode() {
            return Float.hashCode(this.yDiff) + ChangeSize$.ExternalSyntheticOutline0.m(this.xDiff, Boolean.hashCode(this.horizontalDragging) * 31, 31);
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.yDiff, ")", NotificationPanelViewController$.ExternalSyntheticOutline0.m("MoveDiffInfo(horizontalDragging=", this.horizontalDragging, ", xDiff=", this.xDiff, ", yDiff="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MoveVelocityInfo {
        public final boolean horizontalDragging;
        public final float xVelocity;
        public final float yVelocity;

        public MoveVelocityInfo(float f, float f2, boolean z) {
            this.horizontalDragging = z;
            this.xVelocity = f;
            this.yVelocity = f2;
        }

        public final MoveVelocityInfo copy(boolean z, float f, float f2) {
            return new MoveVelocityInfo(f, f2, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MoveVelocityInfo)) {
                return false;
            }
            MoveVelocityInfo moveVelocityInfo = (MoveVelocityInfo) obj;
            return this.horizontalDragging == moveVelocityInfo.horizontalDragging && Float.compare(this.xVelocity, moveVelocityInfo.xVelocity) == 0 && Float.compare(this.yVelocity, moveVelocityInfo.yVelocity) == 0;
        }

        public final boolean getHorizontalDragging() {
            return this.horizontalDragging;
        }

        public final float getXVelocity() {
            return this.xVelocity;
        }

        public final float getYVelocity() {
            return this.yVelocity;
        }

        public final int hashCode() {
            return Float.hashCode(this.yVelocity) + ChangeSize$.ExternalSyntheticOutline0.m(this.xVelocity, Boolean.hashCode(this.horizontalDragging) * 31, 31);
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.yVelocity, ")", NotificationPanelViewController$.ExternalSyntheticOutline0.m("MoveVelocityInfo(horizontalDragging=", this.horizontalDragging, ", xVelocity=", this.xVelocity, ", yVelocity="));
        }
    }

    public CapsuleMotionTracker(Context context) {
        this.context = context;
    }

    public final MoveVelocityInfo computeCurrentVelocity(MotionEvent motionEvent) {
        trackMovement(motionEvent, 0.0f, false);
        float scaledMaximumFlingVelocity = ViewConfiguration.get(this.context).getScaledMaximumFlingVelocity();
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.computeCurrentVelocity(1000, scaledMaximumFlingVelocity);
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        float xVelocity = velocityTracker2 != null ? velocityTracker2.getXVelocity() : 0.0f;
        VelocityTracker velocityTracker3 = this.velocityTracker;
        return new MoveVelocityInfo(xVelocity, velocityTracker3 != null ? velocityTracker3.getYVelocity() : 0.0f, this.horizontalDragging);
    }

    public final MoveDiffInfo diffInfo(MotionEvent motionEvent) {
        Pair<Float, Float> pairDiff = new MotionPoint(motionEvent.getRawX(), motionEvent.getRawY()).diff(this.lastMotionPoint);
        return new MoveDiffInfo(((Number) pairDiff.component1()).floatValue(), ((Number) pairDiff.component2()).floatValue(), this.horizontalDragging);
    }

    public final boolean getBeingDragged() {
        return this.beingDragged;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getHorizontalDragging() {
        return this.horizontalDragging;
    }

    public final boolean getVerticalDragging() {
        return this.beingDragged && !this.horizontalDragging;
    }

    public final void initOrResetVelocityTracker(MotionEvent motionEvent) {
        this.beingDragged = false;
        this.horizontalDragging = false;
        this.lastMotionPoint = new MotionPoint();
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        trackMovement(motionEvent, 0.0f, false);
    }

    public final void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.velocityTracker = null;
        this.beingDragged = false;
        this.horizontalDragging = false;
        this.lastMotionPoint = new MotionPoint();
    }

    public final MoveDiffInfo trackMovement(MotionEvent motionEvent, float f, boolean z) {
        MotionPoint motionPointCopy;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        MotionPoint motionPoint = new MotionPoint(motionEvent.getRawX(), motionEvent.getRawY());
        if (z) {
            this.beingDragged = true;
            boolean zIsHorizontalDrag = motionPoint.isHorizontalDrag(this.lastMotionPoint);
            this.horizontalDragging = zIsHorizontalDrag;
            MotionPoint motionPoint2 = this.lastMotionPoint;
            if (zIsHorizontalDrag) {
                if (motionPoint.dragLeft(motionPoint2)) {
                    f = -f;
                }
                MotionPoint motionPoint3 = this.lastMotionPoint;
                motionPointCopy = motionPoint3.copy(motionPoint3.getX() + f, motionPoint3.y);
            } else {
                if (motionPoint.dragUp(motionPoint2)) {
                    f = -f;
                }
                MotionPoint motionPoint4 = this.lastMotionPoint;
                motionPointCopy = motionPoint4.copy(motionPoint4.x, motionPoint4.getY() + f);
            }
            this.lastMotionPoint = motionPointCopy;
        }
        MoveDiffInfo moveDiffInfoDiffInfo = diffInfo(motionEvent);
        this.lastMotionPoint = motionPoint;
        return moveDiffInfoDiffInfo;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MotionPoint {
        public float x;
        public float y;

        public MotionPoint(float f, float f2) {
            this.x = f;
            this.y = f2;
        }

        public final MotionPoint copy(float f, float f2) {
            return new MotionPoint(f, f2);
        }

        public final Pair<Float, Float> diff(MotionPoint motionPoint) {
            return TuplesKt.to(Float.valueOf(xDiff(motionPoint)), Float.valueOf(yDiff(motionPoint)));
        }

        public final boolean dragLeft(MotionPoint motionPoint) {
            return xDiff(motionPoint) < 0.0f;
        }

        public final boolean dragUp(MotionPoint motionPoint) {
            return yDiff(motionPoint) < 0.0f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MotionPoint)) {
                return false;
            }
            MotionPoint motionPoint = (MotionPoint) obj;
            return Float.compare(this.x, motionPoint.x) == 0 && Float.compare(this.y, motionPoint.y) == 0;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final int hashCode() {
            return Float.hashCode(this.y) + (Float.hashCode(this.x) * 31);
        }

        public final boolean isHorizontalDrag(MotionPoint motionPoint) {
            return Math.abs(xDiff(motionPoint)) >= Math.abs(yDiff(motionPoint));
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public String toString() {
            return ListPopupWindow$.ExternalSyntheticOutline0.m("MotionPoint(x=", this.x, ", y=", this.y, ")");
        }

        public final float xDiff(MotionPoint motionPoint) {
            return this.x - motionPoint.x;
        }

        public final float yDiff(MotionPoint motionPoint) {
            return this.y - motionPoint.y;
        }

        public /* synthetic */ MotionPoint() {
            this(0.0f, 0.0f);
        }
    }
}
