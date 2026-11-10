package com.oplus.systemui.notification.lockscreen.config;

import android.os.SystemProperties;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDebug {
    private static final String DEBUG_LOG_SWITCH_ITEM_VIEW_UPDATER = "debug.capsule.log.switch.itemViewUpdater";
    private static final String DEBUG_PROP_CAPSULE_PIN_DURATION = "debug.capsule.pin.duration";
    private static final String DEBUG_PROP_CAPSULE_PIN_EXTENSION_DURATION = "debug.capsule.pin.extension.duration";
    private static final String DEBUG_PROP_REMIND_BY_ORDER = "debug.capsule.pin.remindByOrder";
    public static final CapsuleDebug INSTANCE = new CapsuleDebug();
    private static final Lazy logSwitchItemViewUpdate$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleDebug$$ExternalSyntheticLambda0());

    public final Long getCapsulePinDuration(long j) {
        Object obj;
        try {
            obj = Result.constructor-impl(Long.valueOf(Math.min(SystemProperties.getLong(DEBUG_PROP_CAPSULE_PIN_DURATION, j), j)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        return (Long) obj;
    }

    public final Long getCapsulePinExtensionDuration(long j) {
        Object obj;
        try {
            obj = Result.constructor-impl(Long.valueOf(Math.min(SystemProperties.getLong(DEBUG_PROP_CAPSULE_PIN_EXTENSION_DURATION, j), j)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        return (Long) obj;
    }

    public final boolean getLogSwitchItemViewUpdate() {
        return ((Boolean) logSwitchItemViewUpdate$delegate.getValue()).booleanValue();
    }

    public final boolean getRemindCardByOrder() {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(SystemProperties.getBoolean(DEBUG_PROP_REMIND_BY_ORDER, false)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }
}
