package com.oplus.systemui.statusbar.icon.ui.binder;

import android.os.Trace;
import android.view.ViewGroup;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.notification.NotificationIconAreaBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.ConnectedDisplaysStatusBarNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel;
import com.oplus.systemui.statusbar.widget.NotificationBadgeView;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaBinder extends NotificationIconAreaBinder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationIconAreaBinder";
    private final ConnectedDisplaysStatusBarNotificationIconViewStore.Factory connectedDisplaysViewStoreFactory;
    private final StatusBarNotificationIconViewStore defaultDisplayViewStore;
    private final StatusBarIconViewBindingFailureTracker failureTracker;
    private final OplusNotificationIconAreaViewModel oplusViewModel;
    private final PerDisplayRepository perDisplaySubcomponentRepository;
    private final NotificationIconContainerStatusBarViewModel viewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationIconAreaBinder(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel, PerDisplayRepository perDisplayRepository, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, StatusBarNotificationIconViewStore statusBarNotificationIconViewStore, ConnectedDisplaysStatusBarNotificationIconViewStore.Factory factory) {
        this.viewModel = notificationIconContainerStatusBarViewModel;
        this.oplusViewModel = oplusNotificationIconAreaViewModel;
        this.perDisplaySubcomponentRepository = perDisplayRepository;
        this.failureTracker = statusBarIconViewBindingFailureTracker;
        this.defaultDisplayViewStore = statusBarNotificationIconViewStore;
        this.connectedDisplaysViewStoreFactory = factory;
    }

    public DisposableHandle bindWhileAttached(ViewGroup viewGroup, int i) {
        NotificationIconContainer notificationIconContainerRequireViewById = viewGroup.requireViewById(2131364175);
        NotificationBadgeView notificationBadgeView = (NotificationBadgeView) viewGroup.requireViewById(2131364200);
        String str = Trace.isTagEnabled(4096L) ? "NICStatusBar#bindWhileAttached" : null;
        boolean z = str != null;
        if (z) {
            TraceUtilsKt.beginSlice(4096L, str);
        }
        try {
            return RepeatWhenAttachedKt.repeatWhenAttached$default(viewGroup, (CoroutineContext) null, new OplusNotificationIconAreaBinder$bindWhileAttached$1$1(i, this, notificationIconContainerRequireViewById, notificationBadgeView, null), 3);
        } finally {
            if (z) {
                Trace.traceEnd(4096L);
            }
        }
    }
}
