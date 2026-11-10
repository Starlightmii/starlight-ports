package com.oplus.systemui.notification.row;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowMenuRowProvider extends NotificationMenuRowProvider {
    private final OplusCustomRow oplusCustomRow;

    public OplusCustomRowMenuRowProvider(OplusCustomRow oplusCustomRow) {
        this.oplusCustomRow = oplusCustomRow;
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final int provideCardActualHeight() {
        NotificationBackgroundView mBackgroundNormal = this.oplusCustomRow.getMBackgroundNormal();
        if (mBackgroundNormal != null) {
            return mBackgroundNormal.getActualHeight();
        }
        return 0;
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public MetaBallBlurDrawable provideCardMetaBallDrawable() {
        NotificationBackgroundViewExt ext;
        ViewBlurProxy viewBlurProxy;
        NotificationBackgroundView mBackgroundNormal = this.oplusCustomRow.getMBackgroundNormal();
        Drawable blurDrawable = null;
        if (mBackgroundNormal != null && (ext = mBackgroundNormal.getExt()) != null && (viewBlurProxy = ext.getViewBlurProxy()) != null) {
            blurDrawable = viewBlurProxy.getBlurDrawable(null);
        }
        return NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(blurDrawable);
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public View provideCardRowAsView() {
        return this.oplusCustomRow;
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final float provideCardTranslationX() {
        return this.oplusCustomRow.getTranslation();
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final int provideCardWidth() {
        return this.oplusCustomRow.getWidth();
    }
}
