package com.oplus.systemui.statusbar.icon.ui.binder;

import android.os.Trace;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconColors;
import com.android.systemui.util.view.ViewUtilKt;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.statusbar.notification.util.OplusNotificationSmallIconUtil;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1 implements FlowCollector {
    public /* synthetic */ OplusContrastColorUtil $oplusContrastColorUtil$inlined;
    public /* synthetic */ StatusBarIconView $view$inlined;

    public final Object emit(Object obj, Continuation continuation) {
        StatusBarIconView statusBarIconView = this.$view$inlined;
        String str = Trace.isTagEnabled(4096L) ? "SBIV#bindIconColors" : null;
        boolean z = str != null;
        if (z) {
            TraceUtilsKt.beginSlice(4096L, str);
        }
        try {
            NotificationIconColors notificationIconColors = (NotificationIconColors) obj;
            OplusContrastColorUtil oplusContrastColorUtil = this.$oplusContrastColorUtil$inlined;
            StatusBarNotification notification = statusBarIconView.getNotification();
            if (OpNotificationUtils.isGrayscaleOplus(statusBarIconView, oplusContrastColorUtil, notification != null ? notification.getNotification() : null, Boxing.boxBoolean(false)) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                StringBuilder sb = new StringBuilder("bindIconColors-->");
                StatusBarNotification notification2 = statusBarIconView.getNotification();
                String key = notification2 != null ? notification2.getKey() : null;
                StatusBarNotification notification3 = statusBarIconView.getNotification();
                sb.append("view: " + key + " isColorizedisSmallIcon: " + OplusNotificationSmallIconUtil.useAppIconForSmallIcon(notification3 != null ? notification3.getNotification() : null));
                Log.d("SystemUi--Statusbar", sb.toString());
            }
            statusBarIconView.setStaticDrawableColor(notificationIconColors.staticDrawableColor(ViewUtilKt.viewBoundsOnScreen(statusBarIconView)));
            statusBarIconView.setDecorColor(notificationIconColors.getTint());
            return Unit.INSTANCE;
        } finally {
            if (z) {
                Trace.traceEnd(4096L);
            }
        }
    }
}
