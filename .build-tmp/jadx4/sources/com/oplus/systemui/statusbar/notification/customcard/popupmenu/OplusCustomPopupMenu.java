package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.service.notification.StatusBarNotification;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusCustomPopupMenu {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final OplusCustomPopupMenu newInstance(Context context, KeyguardViewMediator keyguardViewMediator) {
            return new OplusCustomPopupMenuImpl(context, keyguardViewMediator);
        }
    }

    void onConfigChanged(Configuration configuration);

    Object prepare(ViewGroup viewGroup, Rect rect, ViewGroup viewGroup2, CustomRowEntry customRowEntry, PopupWindow.OnDismissListener onDismissListener, Continuation continuation);

    Object showMenu(ViewGroup viewGroup, Rect rect, ViewGroup viewGroup2, CustomRowEntry customRowEntry, StatusBarNotification statusBarNotification, Function0 function0, KeyguardDismissUtil keyguardDismissUtil, Function2 function2, String str, Continuation continuation);
}
