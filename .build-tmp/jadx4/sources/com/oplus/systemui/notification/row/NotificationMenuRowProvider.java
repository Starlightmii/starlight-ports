package com.oplus.systemui.notification.row;

import android.view.View;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationMenuRowProvider {
    public abstract int provideCardActualHeight();

    public abstract MetaBallBlurDrawable provideCardMetaBallDrawable();

    public abstract View provideCardRowAsView();

    public abstract float provideCardTranslationX();

    public abstract int provideCardWidth();
}
