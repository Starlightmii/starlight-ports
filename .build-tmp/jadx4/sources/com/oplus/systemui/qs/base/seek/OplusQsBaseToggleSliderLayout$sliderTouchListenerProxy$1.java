package com.oplus.systemui.qs.base.seek;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1 implements View.OnTouchListener {
    public /* synthetic */ OplusQsBaseToggleSliderLayout this$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        ViewParent parent;
        OplusQsSliderTouchAnimationDelegate touchAnimationDelegate = this.this$0.getTouchAnimationDelegate();
        if (touchAnimationDelegate != null) {
            motionEvent.getClass();
            touchAnimationDelegate.handleTouchEvent(motionEvent);
        }
        OplusQsToggleSliderTouchExt oplusQsToggleSliderTouchExt = this.this$0.touchExt;
        motionEvent.getClass();
        oplusQsToggleSliderTouchExt.dispatchTouchEvent(motionEvent);
        if (this.this$0.disallowParentIntercept.get() && view != null && (parent = view.getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(this.this$0.needDisallowInterceptTouchEvent());
        }
        View.OnTouchListener onTouchListener = this.this$0.sliderTouchListener;
        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, motionEvent);
        }
        return false;
    }
}
