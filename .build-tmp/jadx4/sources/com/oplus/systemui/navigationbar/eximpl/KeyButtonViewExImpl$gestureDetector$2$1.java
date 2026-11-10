package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.android.systemui.shared.system.QuickStepContract;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyButtonViewExImpl$gestureDetector$2$1 extends NavigationGestureDetector.SimpleOnGestureListener {
    public /* synthetic */ KeyButtonViewExImpl this$0;

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
    public final void onDown() {
        this.this$0.isCalledLongPress = false;
        GestureHomeHandleEventController.INSTANCE.onDown();
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
    public final void onLongPress() {
        this.this$0.isCalledLongPress = true;
        GestureHomeHandleEventController.INSTANCE.onLongClick();
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
    public final void onPreLongPress() {
        GestureHomeHandleEventController.INSTANCE.onPreLongPress();
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
    public final void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2) {
        if (motionEvent != null) {
            KeyButtonViewExImpl keyButtonViewExImpl = this.this$0;
            float y = motionEvent2.getY() - motionEvent.getY();
            float x = motionEvent2.getX() - motionEvent.getX();
            Context context = keyButtonViewExImpl.getContext();
            int i = QuickStepContract.$r8$clinit;
            float scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 3.0f;
            if (keyButtonViewExImpl.isCalledLongPress) {
                return;
            }
            if (Math.abs(x) > scaledTouchSlop || Math.abs(y) > scaledTouchSlop) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    Log.d("SystemUi--NavBar", "KeyButtonViewExImpl-->onMotionEvent: action_move cancel");
                }
                GestureHomeHandleEventController.INSTANCE.onLongPressCancel();
            }
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
    public final void onShowPress() {
        GestureHomeHandleEventController.INSTANCE.onShowPress();
    }
}
