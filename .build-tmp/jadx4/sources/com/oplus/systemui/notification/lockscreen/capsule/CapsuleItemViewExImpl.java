package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemViewExImpl extends OplusExpandableViewExImpl {
    public float capsuleProgress;

    public CapsuleItemViewExImpl(Context context) {
        super(context);
        this.capsuleProgress = 1.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ void bind(View view) {
        super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public float getCapsuleProgress() {
        return this.capsuleProgress;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ NotificationBackgroundView getNotificationBackgroundView() {
        return super.getNotificationBackgroundView();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setCapsuleProgress(float f) {
        this.capsuleProgress = f;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public final void updateClipping() {
        super.updateClipping();
        ExpandableView view = getView();
        CapsuleItemView capsuleItemView = view instanceof CapsuleItemView ? (CapsuleItemView) view : null;
        if (capsuleItemView != null) {
            capsuleItemView.getLocation().loadViewStateClipInfo(capsuleItemView.getViewState());
            capsuleItemView.invalidateItemLocation();
        }
    }
}
