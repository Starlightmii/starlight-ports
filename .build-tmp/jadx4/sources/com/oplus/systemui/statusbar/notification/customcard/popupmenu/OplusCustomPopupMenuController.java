package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusCustomPopupMenuController {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final OplusCustomPopupMenuController newInstance(Context context, ViewBlurManager viewBlurManager, SysUiStateControllerImpl sysUiStateControllerImpl, KeyguardDismissUtil keyguardDismissUtil, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardViewMediator keyguardViewMediator, OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor, NotifCollection notifCollection) {
            return new OplusCustomPopupMenuControllerImpl(context, viewBlurManager, sysUiStateControllerImpl, keyguardDismissUtil, keyguardUpdateMonitor, keyguardViewMediator, oplusLiveAlertNotificationsInteractor, notifCollection);
        }
    }

    static /* synthetic */ void dismissPopupMenu$default(OplusCustomPopupMenuController oplusCustomPopupMenuController, String str, boolean z, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        oplusCustomPopupMenuController.dismissPopupMenu(str, z, null);
    }

    void dismissPopupMenu(String str, boolean z, Function0 function0);

    void initController(OplusCustomRow oplusCustomRow);

    void onConfigChanged(Configuration configuration);

    void showPopupMenu(View view, boolean z, CustomRowEntry customRowEntry, boolean z2, String str);
}
