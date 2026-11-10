package com.oplus.systemui.notification.headsup.windowframe;

import android.view.View;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface HeadsUpAnimator {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface HeadsUpParams {
        public static final Companion Companion = Companion.$$INSTANCE;

        Float onTouchMove(MoveData moveData);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MoveData {
        public final float currY;
        public final float deltaY;
        public final float holdY;
        public final float startY;
        private final Function0 velocityYGetter;

        public MoveData(float f, float f2, float f3, float f4, Function0 function0) {
            this.holdY = f;
            this.currY = f2;
            this.startY = f3;
            this.deltaY = f4;
            this.velocityYGetter = function0;
        }

        public final Function0 component5() {
            return this.velocityYGetter;
        }

        public final MoveData copy(float f, float f2, float f3, float f4, Function0 function0) {
            return new MoveData(f, f2, f3, f4, function0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MoveData)) {
                return false;
            }
            MoveData moveData = (MoveData) obj;
            return Float.compare(this.holdY, moveData.holdY) == 0 && Float.compare(this.currY, moveData.currY) == 0 && Float.compare(this.startY, moveData.startY) == 0 && Float.compare(this.deltaY, moveData.deltaY) == 0 && Intrinsics.areEqual(this.velocityYGetter, moveData.velocityYGetter);
        }

        public final float getCurrY() {
            return this.currY;
        }

        public final float getDeltaY() {
            return this.deltaY;
        }

        public final float getHoldY() {
            return this.holdY;
        }

        public final float getStartY() {
            return this.startY;
        }

        public final Function0 getVelocityYGetter() {
            return this.velocityYGetter;
        }

        public final int hashCode() {
            return this.velocityYGetter.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.deltaY, ChangeSize$.ExternalSyntheticOutline0.m(this.startY, ChangeSize$.ExternalSyntheticOutline0.m(this.currY, Float.hashCode(this.holdY) * 31, 31), 31), 31);
        }

        public String toString() {
            Function0 function0 = this.velocityYGetter;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("MoveData(holdY=", this.holdY, ", currY=", this.currY, ", startY=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.startY, ", deltaY=", this.deltaY, ", velocityYGetter=");
            sbM.append(function0);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class NormalHeadsUpParams implements HeadsUpParams {
        private final AmbientState ambientState;

        public NormalHeadsUpParams(AmbientState ambientState) {
            this.ambientState = ambientState;
        }

        public boolean endHeadsUpTrackingEvent() {
            return false;
        }

        @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator.HeadsUpParams
        public Float onTouchMove(MoveData moveData) {
            float deltaY;
            int maxHeadsUpPullHeight = this.ambientState.getExt().getMaxHeadsUpPullHeight();
            if (moveData.getStartY() > moveData.getHoldY()) {
                deltaY = (moveData.getDeltaY() / 3.0f) + moveData.getStartY();
            } else {
                if (moveData.getDeltaY() + moveData.getStartY() > moveData.getHoldY()) {
                    deltaY = (((moveData.getDeltaY() + moveData.getStartY()) - moveData.getHoldY()) / 3.0f) + moveData.getHoldY();
                } else {
                    deltaY = moveData.getDeltaY() + moveData.getStartY();
                }
            }
            return Float.valueOf(Math.min(moveData.getHoldY() + maxHeadsUpPullHeight, deltaY));
        }
    }

    void dismiss(boolean z, String str, Function1 function1);

    boolean getIsNeedInterceptZoom();

    void reuseSwipeHelper(NotificationSwipeHelper notificationSwipeHelper);

    void setHeadsUpHelper(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper);

    void show(View view);
}
