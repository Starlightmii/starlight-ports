package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootView extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSRootView";
    public boolean disallowChildIntercept;
    public float lastAlpha;
    private OplusQSRootViewComponent.TouchHandler touchHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusQSRootView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchDragEvent(DragEvent dragEvent) {
        dragEvent.getAction();
        return super.dispatchDragEvent(dragEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        OplusQSRootViewComponent.TouchHandler touchHandler = this.touchHandler;
        if (touchHandler != null) {
            touchHandler.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final boolean getDisallowChildIntercept() {
        return this.disallowChildIntercept;
    }

    @Override // android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        return true;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int layoutDirection = getContext().getResources().getConfiguration().getLayoutDirection();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(getLayoutDirection(), layoutDirection, "layoutDirection by view: ", ", layoutDirection by context: "), null, 8);
        setLayoutDirection(layoutDirection);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OplusQSRootViewComponent.TouchHandler touchHandler = this.touchHandler;
        if (touchHandler != null) {
            return touchHandler.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OplusQSRootViewComponent.TouchHandler touchHandler = this.touchHandler;
        if (touchHandler != null) {
            return touchHandler.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        if (!z || !this.disallowChildIntercept) {
            super.requestDisallowInterceptTouchEvent(z);
        } else {
            setDisallowChildIntercept(false);
            super.requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        if (f == this.lastAlpha) {
            return;
        }
        if (f == 1.0f || f == 0.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("setAlpha ", f, "SeparateQSRootView-->", "SystemUi--QuickSettings");
            }
            this.lastAlpha = f;
        }
    }

    public final void setDisallowChildIntercept(boolean z) {
        if (z != this.disallowChildIntercept) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("disallowChildIntercept:", z), null, 8);
            this.disallowChildIntercept = z;
        }
    }

    public final void setOnTouchListener(OplusQSRootViewComponent.TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }

    public OplusQSRootView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusQSRootView(Context context) {
        this(context, null, 0, 6, null);
    }

    public OplusQSRootView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastAlpha = 1.0f;
    }
}
