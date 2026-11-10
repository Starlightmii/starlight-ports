package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.content.Context;
import android.content.res.Configuration;
import android.view.WindowManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationViewCloner {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationViewCloner newInstance(Context context, WindowManager windowManager, ViewBlurManager viewBlurManager) {
            return new NotificationRowCloner(context, windowManager, viewBlurManager);
        }
    }

    Object clone(Object obj, ViewBlurProxy viewBlurProxy, Continuation continuation);

    void onConfigChanged(Configuration configuration);
}
