package com.oplus.systemui.separate.event;

import android.util.Log;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QueueEventProcesser {
    public static final String TAG = "QueueEventProcesser";
    public static final Companion Companion = new Companion();
    private static final Queue<MotionEvent> queueGuideList = new ConcurrentLinkedQueue();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void add(MotionEvent motionEvent, String str) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                int actionMasked = motionEvent.getActionMasked();
                Log.d("SystemUi--QuickSettings", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m(actionMasked, "add caller =", str, " event.actionMasked=", " x="), motionEvent.getX(), " y=", motionEvent.getY(), "QueueEventProcesser-->"));
            }
            QueueEventProcesser.queueGuideList.offer(motionEvent);
        }

        public final void process(MotionEventProcesser motionEventProcesser, String str) {
            LogUtil.i$default(LogUtil.TAG_QS, QueueEventProcesser.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("process caller =", str, " queueGuideList.size=", QueueEventProcesser.queueGuideList.size()), null, 8);
            if (QueueEventProcesser.queueGuideList.isEmpty()) {
                return;
            }
            while (!QueueEventProcesser.queueGuideList.isEmpty()) {
                MotionEvent motionEvent = (MotionEvent) QueueEventProcesser.queueGuideList.poll();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    int actionMasked = motionEvent.getActionMasked();
                    float x = motionEvent.getX();
                    VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(NormalizedTouchData$.ExternalSyntheticOutline0.m(x, actionMasked, "process event.actionMasked=", " x=", " y="), motionEvent.getY(), "QueueEventProcesser-->", "SystemUi--QuickSettings");
                }
                motionEvent.getClass();
                motionEventProcesser.dispatchTouchEvent();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusQueueMotionEvent {
        public boolean dispatchDownEvent;
        public boolean isValid;
        public int pointerIndex = -1;
        private MotionEvent downEvent = MotionEvent.obtain(0, 0, 0, 0.0f, 0.0f, 0);

        public final boolean getDispatchDownEvent() {
            return this.dispatchDownEvent;
        }

        public final MotionEvent getDownEvent() {
            return this.downEvent;
        }

        public final int getPointerIndex() {
            return this.pointerIndex;
        }

        public final void setDispatchDownEvent(boolean z) {
            this.dispatchDownEvent = z;
        }

        public final void setDownEvent(MotionEvent motionEvent) {
            this.downEvent = motionEvent;
        }

        public final void setPointerIndex(int i) {
            this.pointerIndex = i;
        }

        public final void setValid(boolean z) {
            this.isValid = z;
        }
    }
}
