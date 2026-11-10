package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.util.Log;
import android.view.MotionEvent;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.navigationbar.gesture.OplusInputInjector;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.systemui.navigationbar.utils.SingletonHolder;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventInterceptInjectHelper {
    public static final INSTANCE INSTANCE = new INSTANCE(INSTANCE.AnonymousClass1.INSTANCE);
    private static final String TAG = "EventInterceptInjectHelper";
    private MotionEvent downEvent;
    public boolean isInvalidEvent;
    public long lastDownTime;
    public long lastEventTime;
    public boolean thresholdCrossed;
    private MotionEvent upEvent;
    private DownState gestureDownState = DownState.INVALID;
    private ArrayList<MotionEvent> pointerEvent = new ArrayList<>();
    private LinkedList<MotionEvent> inputDownEventCache = new LinkedList<>();
    private LinkedList<MotionEvent> inputUpOrCancelEventCache = new LinkedList<>();
    public final int cacheSize = 2;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DownState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ DownState[] $VALUES;
        public static final DownState INVALID;
        public static final DownState SUCCESS;
        public static final DownState VALID;

        static {
            DownState downState = new DownState("INVALID", 0);
            INVALID = downState;
            DownState downState2 = new DownState("VALID", 1);
            VALID = downState2;
            DownState downState3 = new DownState("SUCCESS", 2);
            SUCCESS = downState3;
            DownState[] downStateArr = {downState, downState2, downState3};
            $VALUES = downStateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(downStateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static DownState valueOf(String str) {
            return (DownState) Enum.valueOf(DownState.class, str);
        }

        public static DownState[] values() {
            return (DownState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class INSTANCE extends SingletonHolder {

        /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.EventInterceptInjectHelper$INSTANCE$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(0, EventInterceptInjectHelper.class, "<init>", "<init>()V", 0);
            }

            public final Object invoke() {
                return new EventInterceptInjectHelper();
            }
        }
    }

    public final void addAllowGestureEvent(MotionEvent motionEvent) {
        addPointerEvent(motionEvent);
    }

    public final void addDownEvent(boolean z, boolean z2, boolean z3, MotionEvent motionEvent) {
        if (!z) {
            this.gestureDownState = DownState.INVALID;
            return;
        }
        if (!z2 || z3) {
            this.gestureDownState = DownState.VALID;
            injectValidEvent(motionEvent);
        } else {
            this.gestureDownState = DownState.SUCCESS;
            addPointerEvent(motionEvent);
        }
    }

    public final void addOtherEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
            if (motionEvent.getActionMasked() == 1) {
                injectAllEvent();
            }
            resetState();
        }
    }

    public final void addPointerEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            clearLastEvent();
            this.downEvent = MotionEvent.obtain(motionEvent);
            return;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.upEvent = MotionEvent.obtain(motionEvent);
            return;
        }
        if (actionMasked == 5) {
            this.pointerEvent.add(MotionEvent.obtain(motionEvent));
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        if (this.thresholdCrossed || !this.pointerEvent.isEmpty()) {
            this.pointerEvent.add(MotionEvent.obtain(motionEvent));
            return;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        OplusInputInjector.getInstance().injectMotionEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final void addUpEvent(boolean z, MotionEvent motionEvent) {
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "EventInterceptInjectHelper-->addUpEvent  MotionEvent.ACTION_UP");
            }
            addPointerEvent(motionEvent);
        }
    }

    public final void clearLastEvent() {
        MotionEvent motionEvent = this.downEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        this.downEvent = null;
        while (!this.pointerEvent.isEmpty()) {
            this.pointerEvent.remove(0).recycle();
        }
        MotionEvent motionEvent2 = this.upEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.upEvent = null;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println("EventInterceptInjectHelper:");
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, " isInvalidEvent: ", this.isInvalidEvent);
        printWriter.println(" gestureDownState: " + this.gestureDownState);
        printWriter.println(getCacheInputEvent());
    }

    public final String getCacheInputEvent() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (Object obj : this.inputDownEventCache) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            sb.append("inputDownEvent" + i2 + ": " + ((MotionEvent) obj) + "\n");
            i2 = i3;
        }
        for (Object obj2 : this.inputUpOrCancelEventCache) {
            int i4 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            sb.append("inputUpOrCancelEvent" + i + ": " + ((MotionEvent) obj2) + "\n");
            i = i4;
        }
        return sb.toString();
    }

    public final boolean getThresholdCrossed() {
        return this.thresholdCrossed;
    }

    public final void injectAllEvent() {
        MotionEvent motionEvent;
        MotionEvent motionEvent2;
        MotionEvent motionEvent3;
        if (this.thresholdCrossed) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            MotionEvent motionEvent4 = this.downEvent;
            MotionEvent motionEvent5 = this.upEvent;
            ArrayList<MotionEvent> arrayList = this.pointerEvent;
            NavBarFeatureOption.INSTANCE.getClass();
            Log.d("SystemUi--NavBar", "EventInterceptInjectHelper-->".concat("  downEvent = " + motionEvent4 + "   upEvent = " + motionEvent5 + "   pointerEvent " + arrayList + "  isTouchpadTwoFinger " + NavBarFeatureOption.isTouchpadTwoFinger()));
        }
        NavBarFeatureOption.INSTANCE.getClass();
        if (!NavBarFeatureOption.isTouchpadTwoFinger() || (motionEvent = this.downEvent) == null || motionEvent.getSource() != 8194 || (motionEvent2 = this.upEvent) == null || motionEvent2.getSource() != 8194 || (motionEvent3 = this.upEvent) == null || motionEvent3.getActionMasked() != 3) {
            OplusInputInjector.getInstance().injectAllEvent(this.downEvent, this.upEvent, this.pointerEvent);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "EventInterceptInjectHelper-->touch pad event return ");
        }
    }

    public final void injectValidEvent(MotionEvent motionEvent) {
        if (this.gestureDownState == DownState.VALID) {
            OplusInputInjector.getInstance().injectMotionEvent(motionEvent);
        }
    }

    public final boolean isInGesture() {
        return this.gestureDownState == DownState.SUCCESS;
    }

    public final boolean isInjectedEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent.getActionMasked() == 0) {
            if (OplusNavBarUtils.isTwoFingerSwipeTrackpadEvent(motionEvent)) {
                if (this.lastDownTime - motionEvent.getDownTime() == 1 && this.lastEventTime == motionEvent.getEventTime()) {
                    z = true;
                }
            } else if ((motionEvent.getEventTime() - motionEvent.getDownTime() == 1 && this.lastEventTime != motionEvent.getEventTime()) || this.lastEventTime == motionEvent.getEventTime()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    Log.d("SystemUi--NavBar", "EventInterceptInjectHelper-->the event is injected event");
                }
                z = true;
            }
            long eventTime = motionEvent.getEventTime();
            long downTime = motionEvent.getDownTime();
            this.lastEventTime = eventTime;
            this.lastDownTime = downTime;
        }
        return z;
    }

    public final boolean isMotionEventInvalid(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int i = this.cacheSize;
        if (actionMasked == 0) {
            if (this.inputDownEventCache.size() >= i) {
                this.inputDownEventCache.poll();
            }
            this.inputDownEventCache.offer(motionEvent);
        }
        if (motionEvent.getActionMasked() == 1) {
            if (this.inputUpOrCancelEventCache.size() >= i) {
                this.inputUpOrCancelEventCache.poll();
            }
            this.inputUpOrCancelEventCache.offer(motionEvent);
        }
        if (this.isInvalidEvent && motionEvent.getActionMasked() != 0) {
            return true;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.isInvalidEvent = isInjectedEvent(motionEvent);
        }
        return this.isInvalidEvent;
    }

    public final void onUpOrCancel() {
        MotionEvent motionEvent = this.upEvent;
        if (motionEvent != null && motionEvent.getActionMasked() == 1 && !this.thresholdCrossed) {
            injectAllEvent();
        }
        resetState();
    }

    public final void resetGestureDownState() {
        this.gestureDownState = DownState.INVALID;
    }

    public final void resetState() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "EventInterceptInjectHelper-->finish this dragging process, reset state");
        }
        clearLastEvent();
        this.gestureDownState = DownState.INVALID;
        this.thresholdCrossed = false;
    }

    public final void setThresholdCrossed(boolean z) {
        this.thresholdCrossed = z;
    }
}
