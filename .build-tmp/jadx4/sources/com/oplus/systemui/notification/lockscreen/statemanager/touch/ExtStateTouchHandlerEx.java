package com.oplus.systemui.notification.lockscreen.statemanager.touch;

import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExtStateTouchHandlerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ExtStateTouchHandlerEx";
    private SwipeDirection lastTriggeredDirection;
    private Float lastY;
    public boolean needHandler;
    public float sameDirection;
    private Integer stateAtDown;
    private final ILockScreenExtStateController stateManager;
    private final IExtStateTouchSwipeAction swipeAction;
    private final String tag;
    private final ExtStateTouchHandlerType type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx$ExtStateTouchHandlerType[], still in use, count: 1, list:
      (r0v1 com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx$ExtStateTouchHandlerType[]) from 0x002b: INVOKE (r0v1 com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx$ExtStateTouchHandlerType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ExtStateTouchHandlerType {
        STACK("stack"),
        CAPSULE("capsule"),
        IMMERSIVE("immerBg");

        public static final /* synthetic */ EnumEntries $ENTRIES;
        private final String tag;

        static {
            $ENTRIES = EnumEntriesKt.enumEntries(extStateTouchHandlerTypeArr);
        }

        public ExtStateTouchHandlerType(String str) {
            super(str, i);
            this.tag = str;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static ExtStateTouchHandlerType valueOf(String str) {
            return (ExtStateTouchHandlerType) Enum.valueOf(ExtStateTouchHandlerType.class, str);
        }

        public static ExtStateTouchHandlerType[] values() {
            return (ExtStateTouchHandlerType[]) $VALUES.clone();
        }

        public final String getTag() {
            return this.tag;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class SwipeDirection {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ SwipeDirection[] $VALUES;
        public static final SwipeDirection DOWN;
        public static final SwipeDirection UP;

        static {
            SwipeDirection swipeDirection = new SwipeDirection("UP", 0);
            UP = swipeDirection;
            SwipeDirection swipeDirection2 = new SwipeDirection("DOWN", 1);
            DOWN = swipeDirection2;
            SwipeDirection[] swipeDirectionArr = {swipeDirection, swipeDirection2};
            $VALUES = swipeDirectionArr;
            $ENTRIES = EnumEntriesKt.enumEntries(swipeDirectionArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static SwipeDirection valueOf(String str) {
            return (SwipeDirection) Enum.valueOf(SwipeDirection.class, str);
        }

        public static SwipeDirection[] values() {
            return (SwipeDirection[]) $VALUES.clone();
        }
    }

    public ExtStateTouchHandlerEx(ExtStateTouchHandlerType extStateTouchHandlerType, ILockScreenExtStateController iLockScreenExtStateController, IExtStateTouchSwipeAction iExtStateTouchSwipeAction) {
        this.type = extStateTouchHandlerType;
        this.stateManager = iLockScreenExtStateController;
        this.swipeAction = iExtStateTouchSwipeAction;
        this.tag = extStateTouchHandlerType.getTag();
    }

    private final int getCurrentState() {
        return ((ILockScreenExtStateController.ExtStateEvent) this.stateManager.getExtStateEvent().getValue()).getNewState();
    }

    /* JADX WARN: Code duplicated, block: B:112:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:114:0x01f8  */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        if (r0 != 3) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void reset() {
        this.needHandler = false;
        this.sameDirection = 0.0f;
        this.lastY = null;
        this.stateAtDown = null;
        this.lastTriggeredDirection = null;
    }
}
