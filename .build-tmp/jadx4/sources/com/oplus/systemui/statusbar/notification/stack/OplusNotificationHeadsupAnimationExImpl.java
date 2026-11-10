package com.oplus.systemui.statusbar.notification.stack;

import android.animation.ObjectAnimator;
import android.view.View;
import com.android.systemui.statusbar.notification.PhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.stack.NotificationHeadsUpAnimationEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.extend.OplusNotificationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationHeadsupAnimationExImpl extends NotificationHeadsUpAnimationEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationHeadsupAnimationExImpl";

    public void beforeCancelTranslationYAnimation(ObjectAnimator objectAnimator, View view) {
        if (objectAnimator == null || view == null) {
            return;
        }
        PhysicsPropertyAnimator.Companion companion = PhysicsPropertyAnimator.Companion;
        view.setTag(2131365784, null);
        view.setTag(ViewState.TAG_START_TRANSLATION_Y, null);
        view.setTag(ViewState.TAG_END_TRANSLATION_Y, null);
    }
}
