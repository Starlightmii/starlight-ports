package com.oplusos.systemui.common.viewdialog.model;

import android.content.Context;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ViewDialogAdapter extends ViewDialogAnimationManager.AnimationCallBack {
    default ViewDialogAnimationManager createAnimationManager(View view) {
        return null;
    }

    View createDialogView(Context context);

    default int getContentViewId() {
        return -1;
    }

    default View getExpandableView() {
        return null;
    }

    default int getHideContentViewId() {
        return getContentViewId();
    }

    String getViewTag();

    default boolean needInterceptShadeTouchOnShow() {
        return true;
    }

    default boolean onBackAction() {
        return false;
    }

    void onDismiss();

    default void onIntercept() {
    }

    void onShow();

    default void setExpandableView(View view) {
    }

    default boolean windowInsetsEnabled() {
        return true;
    }
}
