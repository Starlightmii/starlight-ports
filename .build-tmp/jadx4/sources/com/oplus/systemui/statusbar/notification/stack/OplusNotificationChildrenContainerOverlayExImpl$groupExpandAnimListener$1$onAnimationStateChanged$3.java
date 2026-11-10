package com.oplus.systemui.statusbar.notification.stack;

import android.view.View;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1$onAnimationStateChanged$3 extends FunctionReferenceImpl implements Function1 {
    public final Object invoke(Object obj) {
        return Boolean.valueOf(((OplusExpandableNotificationRowExImpl.Companion) ((CallableReference) this).receiver).isRenderExpensiveView((View) obj));
    }
}
