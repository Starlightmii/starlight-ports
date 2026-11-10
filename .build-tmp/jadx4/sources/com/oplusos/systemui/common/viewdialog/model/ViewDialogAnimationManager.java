package com.oplusos.systemui.common.viewdialog.model;

import android.view.View;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ViewDialogAnimationManager {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AnimationCallBack {
        default void onAnimationEnd(boolean z, boolean z2) {
        }

        default void onAnimationStart(boolean z) {
        }

        default void onAnimationUpdate(float f) {
        }
    }

    void hideDetailToView(View view, String str, int i);

    void showDetailFromView(View view, int i, Function0 function0, AnimationCallBack animationCallBack);
}
