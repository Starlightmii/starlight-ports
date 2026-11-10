package com.oplus.systemui.statusbar.notification.power;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AutoSaveModeLevelObserver extends AbstractObserver {
    public static final String AUTO_SAVE_MODE_LEVEL_STRING = "power_save_open_level";
    public static final String TAG = "AutoSaveModeLevelObserver";
    public int autoSaveModeLevel;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AutoSaveModeLevelObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final AutoSaveModeLevelObserver getInstance() {
            return (AutoSaveModeLevelObserver) AutoSaveModeLevelObserver.instance$delegate.getValue();
        }
    }

    public final int getAutoSaveModeLevel() {
        return this.autoSaveModeLevel;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return AUTO_SAVE_MODE_LEVEL_STRING;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            this.autoSaveModeLevel = AbsSettingsValueProxy.Companion.getSystemIntValue(context, getKey(), 15, 0);
            if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.autoSaveModeLevel, "onChange autoSaveModeLevel = ", "Oplus_PowerUI-->", "SystemUi--Common");
            }
        }
    }

    public final void setAutoSaveModeLevel(int i) {
        this.autoSaveModeLevel = i;
    }
}
