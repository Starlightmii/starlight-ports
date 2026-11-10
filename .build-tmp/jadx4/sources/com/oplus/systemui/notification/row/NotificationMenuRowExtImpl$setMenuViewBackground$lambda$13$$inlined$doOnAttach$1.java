package com.oplus.systemui.notification.row;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int $r8$classId = 0;
    public /* synthetic */ View $this_apply$inlined;
    public /* synthetic */ View $this_doOnAttach;
    public /* synthetic */ NotificationMenuRowExtImpl this$0;

    public NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1(View view, View view2, NotificationMenuRowExtImpl notificationMenuRowExtImpl) {
        this.$this_doOnAttach = view;
        this.$this_apply$inlined = view2;
        this.this$0 = notificationMenuRowExtImpl;
    }

    private final void onViewAttachedToWindow$com$oplus$systemui$notification$row$NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$lambda$12$$inlined$doOnDetach$1(View view) {
    }

    private final void onViewDetachedFromWindow$com$oplus$systemui$notification$row$NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                Drawable menuItemBackground = this.this$0.getMenuItemBackground(this.$this_apply$inlined.getResources(), this.$this_apply$inlined);
                this.$this_apply$inlined.setBackground(menuItemBackground);
                ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
                NotificationMenuRowLog.e(companion.simpleNameAndHash(this.$this_apply$inlined) + " doOnAttach, setBackground = " + companion.simpleNameAndHash(menuItemBackground));
                View view2 = this.$this_apply$inlined;
                if (!view2.isAttachedToWindow()) {
                    NotificationMenuRowLog.e(companion.simpleNameAndHash(this.$this_apply$inlined) + " doOnDetach, post viewBlurManager.releaseBlurProxyForView");
                    this.this$0.getViewBlurManager().releaseBlurProxyForView(this.$this_apply$inlined, true, "menu detach");
                } else {
                    view2.addOnAttachStateChangeListener(new NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1(view2, this.$this_apply$inlined, this.this$0));
                }
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                break;
            default:
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                NotificationMenuRowLog.e(ShareTransitionProperty.Companion.simpleNameAndHash(this.$this_apply$inlined) + " doOnDetach, post viewBlurManager.releaseBlurProxyForView");
                this.this$0.getViewBlurManager().releaseBlurProxyForView(this.$this_apply$inlined, true, "menu detach");
                break;
        }
    }

    public /* synthetic */ NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1() {
    }
}
