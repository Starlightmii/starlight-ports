package com.oplus.systemui.notification.blur;

import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewBlurManager$notifTracker$1 implements OnBeforeRenderListListener {
    public /* synthetic */ ViewBlurManager this$0;

    public final void onBeforeRenderList(List list) {
        ViewBlurManager viewBlurManager = this.this$0;
        viewBlurManager.entries = list;
        LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
        if (logBuffer != null) {
            logBuffer.log("ViewBlurManager", LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("onBeforeRenderList isOnKeyGuard=", ViewBlurManager.isOnKeyGuard$default(viewBlurManager), ",entries.size=", list.size()), (Throwable) null);
        }
        if (Intrinsics.areEqual(ViewBlurProxy.BlurType.BlurTypeStatic.INSTANCE, viewBlurManager.decideBlurType()) && viewBlurManager.statusBarStateController.getState() == 2) {
            ViewBlurManager.access$delaySetStaticBlur(viewBlurManager);
        } else {
            viewBlurManager.updateRowsBlur();
        }
    }
}
