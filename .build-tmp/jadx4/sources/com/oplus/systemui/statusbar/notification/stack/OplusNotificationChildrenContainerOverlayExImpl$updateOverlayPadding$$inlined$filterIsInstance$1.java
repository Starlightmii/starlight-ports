package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl$updateOverlayPadding$$inlined$filterIsInstance$1 implements Function1 {
    public static final OplusNotificationChildrenContainerOverlayExImpl$updateOverlayPadding$$inlined$filterIsInstance$1 INSTANCE = new OplusNotificationChildrenContainerOverlayExImpl$updateOverlayPadding$$inlined$filterIsInstance$1();

    public final Object invoke(Object obj) {
        return Boolean.valueOf(obj instanceof HybridNotificationView);
    }
}
