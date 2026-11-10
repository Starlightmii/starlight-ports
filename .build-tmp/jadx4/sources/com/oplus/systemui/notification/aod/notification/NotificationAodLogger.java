package com.oplus.systemui.notification.aod.notification;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationAodLogger {
    private final LogBuffer buffer;

    public NotificationAodLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String aodLogKey(View view) {
        CustomRowEntry entry;
        if (view instanceof ExpandableNotificationRow) {
            return NotificationUtilsKt.getLogKeyNonNull(((ExpandableNotificationRow) view).getEntry());
        }
        if (!(view instanceof OplusCustomRow) || (entry = ((OplusCustomRow) view).getEntry()) == null) {
            return null;
        }
        return entry.getKey();
    }

    public final void logClearAodDirty(Drawable drawable, String str, View view, int i) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationAodLogger$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationAodLogger$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda0.f$0 = i;
        externalSyntheticLambda0.f$1 = str;
        VarHandle.storeStoreFence();
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain("NotificationAodLogger", logLevel, externalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setStr1(aodLogKey(view));
        logMessageObtain.setInt1(drawable != null ? drawable.hashCode() : -1);
        logBuffer.commit(logMessageObtain);
    }

    public final void tryLogStart(PlatformBlurDrawable platformBlurDrawable, String str, View view, int i) {
        if (platformBlurDrawable.isAodDirty) {
            return;
        }
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationAodLogger$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationAodLogger$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = i;
        externalSyntheticLambda0.f$1 = str;
        VarHandle.storeStoreFence();
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain("NotificationAodLogger", logLevel, externalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setStr1(aodLogKey(view));
        logMessageObtain.setInt1(platformBlurDrawable.hashCode());
        logBuffer.commit(logMessageObtain);
    }
}
