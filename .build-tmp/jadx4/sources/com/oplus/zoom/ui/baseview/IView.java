package com.oplus.zoom.ui.baseview;

import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IView {
    public static final String FLOAT_HANDLE_VIEW_TITLE = "OplusOSZoomFloatHandleView";

    default void animAdd(AnimatorListenerAdapter animatorListenerAdapter) {
    }

    default void animRemove(AnimatorListenerAdapter animatorListenerAdapter) {
    }

    View getView();

    WindowManager.LayoutParams getWindowParams();

    default void onCreate() {
    }

    default void setHook(ViewHook viewHook) {
    }
}
