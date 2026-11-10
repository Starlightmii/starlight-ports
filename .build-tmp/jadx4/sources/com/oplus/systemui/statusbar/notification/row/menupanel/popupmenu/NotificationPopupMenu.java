package com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelHelper;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationPopupMenu {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationPopupMenu newInstance(Context context, NotificationPopupMenuOperation notificationPopupMenuOperation, KeyguardViewMediator keyguardViewMediator, SettingsPanelHelper settingsPanelHelper) {
            return new NotificationPopupMenuImpl(context, notificationPopupMenuOperation, keyguardViewMediator, settingsPanelHelper);
        }
    }

    void onConfigChanged(Configuration configuration);

    Object prepare(ExpandableNotificationRow expandableNotificationRow, Rect rect, ViewGroup viewGroup, PopupWindow.OnDismissListener onDismissListener, Continuation continuation);

    void showMenu(ExpandableNotificationRow expandableNotificationRow, Rect rect, ViewGroup viewGroup, KeyguardDismissUtil keyguardDismissUtil, Function3 function3);
}
