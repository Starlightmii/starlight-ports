package com.oplus.systemui.statusbar.observer;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PowerSavingModeObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final Lazy INSTANCE$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new PowerSavingModeObserver$$ExternalSyntheticLambda0());
    public static final String TAG = "PowerSavingModeObserver";
    public boolean batterySavingMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final PowerSavingModeObserver getINSTANCE() {
            return (PowerSavingModeObserver) PowerSavingModeObserver.INSTANCE$delegate.getValue();
        }

        public final PowerSavingModeObserver getInstance() {
            return getINSTANCE();
        }
    }

    public static final PowerSavingModeObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_POWER_SAVING_MODE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        ContentResolver contentResolver;
        Context context = getContext();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            this.batterySavingMode = Settings.Global.getInt(contentResolver, CommonSettingsValueProxy.KEY_POWER_SAVING_MODE, 0) != 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onChange batterySavingMode: ", "PowerSavingModeObserver-->", "SystemUi--Statusbar", this.batterySavingMode);
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        ContentResolver contentResolver;
        super.onRegister(context);
        Context context2 = getContext();
        if (context2 != null && (contentResolver = context2.getContentResolver()) != null) {
            this.batterySavingMode = Settings.Global.getInt(contentResolver, CommonSettingsValueProxy.KEY_POWER_SAVING_MODE, 0) != 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onRegister batterySavingMode: ", "PowerSavingModeObserver-->", "SystemUi--Statusbar", this.batterySavingMode);
        }
    }
}
