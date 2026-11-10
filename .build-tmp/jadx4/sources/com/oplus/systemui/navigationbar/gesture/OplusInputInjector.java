package com.oplus.systemui.navigationbar.gesture;

import android.graphics.RectF;
import android.hardware.input.InputManagerGlobal;
import android.os.SystemClock;
import android.util.Singleton;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusInputInjector implements LauncherProxyService.LauncherProxyListener {
    public static final AnonymousClass1 sOplusInputInjectorSingleton = new AnonymousClass1();
    public RectF mScreenRegion;
    public boolean mShouldInject;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.OplusInputInjector$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends Singleton {
        public final Object create() {
            OplusInputInjector oplusInputInjector = new OplusInputInjector();
            oplusInputInjector.mScreenRegion = new RectF();
            oplusInputInjector.mShouldInject = true;
            return oplusInputInjector;
        }
    }

    public static OplusInputInjector getInstance() {
        return (OplusInputInjector) sOplusInputInjectorSingleton.get();
    }

    public final void injectAllEvent(MotionEvent motionEvent, MotionEvent motionEvent2, List list) {
        StringBuilder sb = new StringBuilder("injectAllEvent down: ");
        sb.append(motionEvent);
        sb.append(" up: ");
        sb.append(motionEvent2);
        sb.append(" ,events = ");
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(list.isEmpty() ? 0 : list.size(), LogUtil.TAG_NAVBAR, "OplusInputInjector", sb);
        if (motionEvent != null) {
            injectMotionEvent(motionEvent);
        }
        while (!list.isEmpty()) {
            injectMotionEvent((MotionEvent) list.remove(0));
        }
        if (motionEvent2 != null) {
            injectMotionEvent(motionEvent2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003a  */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x003a, please report this as an issue */
    public final void injectMotionEvent(MotionEvent motionEvent) {
        boolean z;
        boolean zInjectInputEvent = false;
        if (motionEvent.getAction() == 0) {
            RectF rectF = this.mScreenRegion;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (rectF == null) {
                LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "checkDownEventInScreen mScreenRegion:" + this.mScreenRegion + " does not screen area this event");
                z = false;
            } else {
                float f = rectF.left;
                float f2 = rectF.right;
                if (f < f2) {
                    float f3 = rectF.top;
                    float f4 = rectF.bottom;
                    if (f3 >= f4 || x < f || x > f2 || y < f3 || y > f4) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "checkDownEventInScreen mScreenRegion:" + this.mScreenRegion + " does not screen area this event");
                        z = false;
                    } else {
                        z = true;
                    }
                } else {
                    LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "checkDownEventInScreen mScreenRegion:" + this.mScreenRegion + " does not screen area this event");
                    z = false;
                }
            }
            this.mShouldInject = z;
        }
        if (!this.mShouldInject) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "checkDownEventInScreen do not inject sourceEvent:" + motionEvent);
            return;
        }
        if ((motionEvent.getAction() == 0 && (motionEvent.getEventTime() - motionEvent.getDownTime() == 1 || motionEvent.getEdgeFlags() == 1073741824)) || motionEvent.getEdgeFlags() == 1073741824) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "checkEventShouldInject repeat injection sourceEvent:" + motionEvent);
            return;
        }
        motionEvent.setEdgeFlags(1073741824);
        MotionEvent motionEventResetEvent = NavBarUtils.resetEvent(motionEvent);
        motionEventResetEvent.setDownTime(motionEventResetEvent.getDownTime() - 1);
        try {
            zInjectInputEvent = InputManagerGlobal.getInstance().injectInputEvent(motionEventResetEvent, 0);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "OplusInputInjector", "injectMotionEvent error " + e);
        }
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "injectMotionEvent result " + zInjectInputEvent + ", event = " + motionEventResetEvent);
    }

    public final void notifyInjectAllEvent(MotionEvent motionEvent, MotionEvent motionEvent2, List list) {
        injectAllEvent(motionEvent, motionEvent2, list);
    }

    public final void notifyInjectEvent(MotionEvent motionEvent, MotionEvent motionEvent2) {
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "notifyInjectEvent down: " + motionEvent + " up: " + motionEvent2);
        if (motionEvent != null) {
            injectMotionEvent(motionEvent);
        }
        if (motionEvent2 != null) {
            injectMotionEvent(motionEvent2);
        }
    }

    public final void notifyKeyEvent(int i) {
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusInputInjector", "notifyKeyEvent key: = " + i);
        long jUptimeMillis = SystemClock.uptimeMillis();
        InputManagerGlobal.getInstance().injectInputEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0, 0, -1, 0, 72, 257), 0);
        long jUptimeMillis2 = SystemClock.uptimeMillis();
        InputManagerGlobal.getInstance().injectInputEvent(new KeyEvent(jUptimeMillis2, jUptimeMillis2, 1, i, 0, 0, -1, 0, 72, 257), 0);
    }

    public /* bridge */ /* synthetic */ void setAssistantOverridesRequested(int[] iArr) {
        super.setAssistantOverridesRequested(iArr);
    }
}
