package com.oplus.systemui.qs.base.seek;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.dispatch.OCDHandler;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsToggleSliderTouchExt implements AccessibilityManager.TouchExplorationStateChangeListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsToggleSliderTouchExt";
    private final AccessibilityManager accessibilityManager;
    private final Handler bgHandler;
    public float downX;
    public float downY;
    private final OplusQsToggleSliderTouchExt$internalLayoutClickProxy$1 internalLayoutClickProxy;
    private final View.OnLongClickListener internalLongClickProxy;
    private final OplusQsToggleSliderTouchExt$internalToggleClickProxy$1 internalToggleClickProxy;
    public boolean isLayoutSlopTouch;
    public boolean isMove;
    private final OplusQsBaseToggleSliderLayout layout;
    private View.OnClickListener layoutClickListener;
    private final CheckForLongPress layoutPendingCheckForLongPress;
    private final UnsetPressedState layoutUnsetPressedState;
    private View.OnLongClickListener longClickListener;
    public int realScreenHeight;
    public int realScreenWidth;
    public final int slop;
    private View.OnClickListener toggleClickListener;
    private final CheckForLongPress togglePendingCheckForLongPress;
    private final UnsetPressedState toggleUnsetPressedState;
    private final View toggleView;
    private View touchTarget;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CheckForLongPress implements Runnable {
        public boolean hasPerformedLongPress;
        public boolean originalPressedState;
        public int originalWindowAttachCount;
        private final View view;

        public CheckForLongPress(View view) {
            this.view = view;
        }

        public final void checkForLongClick(long j) {
            boolean z = false;
            this.hasPerformedLongPress = false;
            this.originalWindowAttachCount = OplusQsToggleSliderTouchExt.this.layout.getWindowAttachCount();
            View view = this.view;
            if (view != null && view.isPressed()) {
                z = true;
            }
            this.originalPressedState = z;
            View view2 = this.view;
            if (view2 != null) {
                view2.postDelayed(this, j);
            }
        }

        public final boolean getHasPerformedLongPress() {
            return this.hasPerformedLongPress;
        }

        public final void removeLongPressCallback() {
            View view = this.view;
            if (view != null) {
                view.removeCallbacks(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            View view = this.view;
            if (view == null || view.getParent() == null || this.originalPressedState != this.view.isPressed() || this.originalWindowAttachCount != OplusQsToggleSliderTouchExt.this.layout.getWindowAttachCount()) {
                return;
            }
            OplusQsToggleSliderTouchExt oplusQsToggleSliderTouchExt = OplusQsToggleSliderTouchExt.this;
            if (!oplusQsToggleSliderTouchExt.isLayoutSlopTouch && oplusQsToggleSliderTouchExt.internalLongClickProxy.onLongClick(this.view)) {
                this.hasPerformedLongPress = true;
            }
        }

        public final void setHasPerformedLongPress(boolean z) {
            this.hasPerformedLongPress = z;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UnsetPressedState implements Runnable {
        private final View view;

        public UnsetPressedState(View view) {
            this.view = view;
        }

        @Override // java.lang.Runnable
        public final void run() {
            View view = this.view;
            if (view != null) {
                view.setPressed(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.seek.OplusQsToggleSliderTouchExt$onAttach$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusQsToggleSliderTouchExt this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusQsToggleSliderTouchExt oplusQsToggleSliderTouchExt = this.this$0;
            AccessibilityManager accessibilityManager = oplusQsToggleSliderTouchExt.accessibilityManager;
            oplusQsToggleSliderTouchExt.onTouchExplorationStateChanged(accessibilityManager != null ? accessibilityManager.isTouchExplorationEnabled() : false);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.seek.OplusQsToggleSliderTouchExt$onTouchExplorationStateChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02511 implements Runnable {
        public /* synthetic */ boolean $enabled;
        public /* synthetic */ OplusQsToggleSliderTouchExt this$0;

        @Override // java.lang.Runnable
        public final void run() {
            View view = this.this$0.toggleView;
            if (view != null) {
                OplusQsToggleSliderTouchExt.access$configClickable(this.this$0, view, this.$enabled);
            }
        }
    }

    public OplusQsToggleSliderTouchExt(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout) {
        this.layout = oplusQsBaseToggleSliderLayout;
        View toggleView = oplusQsBaseToggleSliderLayout.getToggleView();
        this.toggleView = toggleView;
        this.bgHandler = new OCDHandler(SystemUIThreadUtil.getWorkLooper());
        this.toggleUnsetPressedState = new UnsetPressedState(toggleView);
        this.togglePendingCheckForLongPress = new CheckForLongPress(toggleView);
        this.layoutUnsetPressedState = new UnsetPressedState(oplusQsBaseToggleSliderLayout);
        this.layoutPendingCheckForLongPress = new CheckForLongPress(oplusQsBaseToggleSliderLayout);
        OplusQsToggleSliderTouchExt$internalToggleClickProxy$1 oplusQsToggleSliderTouchExt$internalToggleClickProxy$1 = new OplusQsToggleSliderTouchExt$internalToggleClickProxy$1();
        oplusQsToggleSliderTouchExt$internalToggleClickProxy$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.internalToggleClickProxy = oplusQsToggleSliderTouchExt$internalToggleClickProxy$1;
        OplusQsToggleSliderTouchExt$internalLayoutClickProxy$1 oplusQsToggleSliderTouchExt$internalLayoutClickProxy$1 = new OplusQsToggleSliderTouchExt$internalLayoutClickProxy$1();
        oplusQsToggleSliderTouchExt$internalLayoutClickProxy$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.internalLayoutClickProxy = oplusQsToggleSliderTouchExt$internalLayoutClickProxy$1;
        OplusQsToggleSliderTouchExt$internalLongClickProxy$1 oplusQsToggleSliderTouchExt$internalLongClickProxy$1 = new OplusQsToggleSliderTouchExt$internalLongClickProxy$1();
        oplusQsToggleSliderTouchExt$internalLongClickProxy$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.internalLongClickProxy = oplusQsToggleSliderTouchExt$internalLongClickProxy$1;
        this.accessibilityManager = (AccessibilityManager) oplusQsBaseToggleSliderLayout.getContext().getSystemService(AccessibilityManager.class);
        this.slop = ViewConfiguration.get(oplusQsBaseToggleSliderLayout.getContext()).getScaledTouchSlop();
    }

    public static final void access$configClickable(OplusQsToggleSliderTouchExt oplusQsToggleSliderTouchExt, View view, boolean z) {
        view.setOnClickListener(z ? oplusQsToggleSliderTouchExt.internalToggleClickProxy : null);
        view.setOnLongClickListener(z ? oplusQsToggleSliderTouchExt.internalLongClickProxy : null);
        view.setClickable(z);
        view.setLongClickable(z);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0015  */
    public final void dispatchTouchEvent(MotionEvent motionEvent) {
        View view;
        if (motionEvent.getAction() == 0) {
            View view2 = this.toggleView;
            if (view2 == null) {
                view2 = this.layout;
            } else {
                if (!isPointInView(motionEvent, view2)) {
                    view2 = null;
                }
                if (view2 == null) {
                    view2 = this.layout;
                }
            }
            this.touchTarget = view2;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view2, "dispatchTouchEvent touchTarget="), null, 8);
        }
        View view3 = this.touchTarget;
        if (Intrinsics.areEqual(view3, this.layout)) {
            onTouchEvent(this.layout, motionEvent, this.layoutPendingCheckForLongPress, this.layoutUnsetPressedState, false);
        } else {
            if (!Intrinsics.areEqual(view3, this.toggleView) || (view = this.toggleView) == null) {
                return;
            }
            onTouchEvent(view, motionEvent, this.togglePendingCheckForLongPress, this.toggleUnsetPressedState, true);
        }
    }

    public final boolean getOneHandedEnable() {
        return this.layout.getOneHandedEnable();
    }

    public final void initRealSize(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
        int i = point.x;
        int i2 = point.y;
        int i3 = i > i2 ? i2 : i;
        this.realScreenWidth = i3;
        if (i < i2) {
            i = i2;
        }
        this.realScreenHeight = i;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getRealSize point: " + point + " realScreenWidth: " + i3 + " realScreenHeight: " + i, null, 8);
    }

    public final boolean isPointInView(MotionEvent motionEvent, View view) {
        int[] locationOnScreen = view.getLocationOnScreen();
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (view.getContext().getResources().getConfiguration().orientation == 1 && getOneHandedEnable()) {
            int i = this.realScreenWidth;
            int i2 = this.realScreenHeight;
            if (i < i2) {
                i = i2;
            }
            rawY -= i / 2;
        }
        if (locationOnScreen.length == 2) {
            int i3 = locationOnScreen[0];
            if (rawX > i3 && rawX < view.getWidth() + i3) {
                int i4 = locationOnScreen[1];
                if (rawY > i4 && rawY < view.getHeight() + i4) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void onAttach() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.addTouchExplorationStateChangeListener(this, this.bgHandler);
        }
        Handler handler = this.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
        initRealSize(this.layout.getContext());
    }

    public final void onDetach() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.removeTouchExplorationStateChangeListener(this);
        }
    }

    public final void onTouchEvent(View view, MotionEvent motionEvent, CheckForLongPress checkForLongPress, UnsetPressedState unsetPressedState, boolean z) {
        int action = motionEvent.getAction();
        if (action == 0) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onTouchEvent ACTION_DOWN isPressed=", " isInScrollingContainer=", view.isPressed(), view.isInScrollingContainer()), null, 8);
            checkForLongPress.setHasPerformedLongPress(false);
            if (z) {
                view.setPressed(true);
            }
            this.downX = motionEvent.getRawX();
            this.downY = motionEvent.getRawY();
            checkForLongPress.checkForLongClick(ViewConfiguration.getLongPressTimeout());
            return;
        }
        if (action == 1) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onTouchEvent ACTION_UP isPressed=", view.isPressed()), null, 8);
            if (z && view.isPressed()) {
                if (!checkForLongPress.getHasPerformedLongPress()) {
                    checkForLongPress.removeLongPressCallback();
                    if (!view.post(this.internalToggleClickProxy)) {
                        this.internalToggleClickProxy.run();
                    }
                }
                view.postDelayed(unsetPressedState, 200L);
            } else if (!this.isLayoutSlopTouch) {
                if (!checkForLongPress.getHasPerformedLongPress()) {
                    checkForLongPress.removeLongPressCallback();
                    if (!this.isMove && !view.post(this.internalLayoutClickProxy)) {
                        this.internalLayoutClickProxy.run();
                    }
                }
                view.postDelayed(unsetPressedState, 200L);
            }
            this.isLayoutSlopTouch = false;
            this.isMove = false;
            return;
        }
        if (action != 2) {
            if (action != 3) {
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "onTouchEvent ACTION_CANCEL", null, 8);
            view.setPressed(false);
            checkForLongPress.removeLongPressCallback();
            checkForLongPress.setHasPerformedLongPress(false);
            this.isLayoutSlopTouch = false;
            this.isMove = false;
            return;
        }
        boolean zIsPointInView = isPointInView(motionEvent, view);
        boolean z2 = Math.abs(motionEvent.getRawX() - this.downX) > ((float) this.slop) || Math.abs(motionEvent.getRawY() - this.downY) > ((float) this.slop);
        if ((z2 || !zIsPointInView) && view.isPressed()) {
            view.setPressed(false);
        }
        if (z2) {
            this.isLayoutSlopTouch = true;
            checkForLongPress.removeLongPressCallback();
        }
        if (isPointInView(motionEvent, this.layout)) {
            return;
        }
        checkForLongPress.removeLongPressCallback();
        this.isMove = true;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onTouchExplorationStateChanged: ", z), null, 8);
        RunnableC02511 runnableC02511 = new RunnableC02511();
        runnableC02511.this$0 = this;
        runnableC02511.$enabled = z;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC02511);
    }

    public final void setLayoutClickListener(View.OnClickListener onClickListener) {
        this.layoutClickListener = onClickListener;
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.longClickListener = onLongClickListener;
    }

    public final void setToggleClickListener(View.OnClickListener onClickListener) {
        this.toggleClickListener = onClickListener;
    }
}
