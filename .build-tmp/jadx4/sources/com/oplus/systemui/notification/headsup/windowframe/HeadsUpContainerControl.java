package com.oplus.systemui.notification.headsup.windowframe;

import android.content.res.Configuration;
import android.view.View;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface HeadsUpContainerControl {
    float getBaseTranslationY();

    float getTranslationY();

    View getView();

    boolean isHeadsUpView(View view);

    void notifySizeOrLocationChanged(Float f);

    void onConfigChanged(Configuration configuration);

    void reuseSwipeHelper(NotificationSwipeHelper notificationSwipeHelper);

    void setBaseTranslationY(float f);

    void setHeadsUpHelper(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper);

    void setTranslationY(float f);

    void setView(View view);
}
