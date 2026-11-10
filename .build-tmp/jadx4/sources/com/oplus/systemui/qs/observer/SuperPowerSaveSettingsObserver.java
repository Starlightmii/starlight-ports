package com.oplus.systemui.qs.observer;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper$$ExternalSyntheticLambda0;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveSettingsObserver extends AbstractObserver {
    private static final String TAG = "SuperPowerSaveSettingsObserver";
    public volatile boolean isSuperPowerSaveState;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new SuperPowerSaveSettingsObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final SuperPowerSaveSettingsObserver getInstance() {
            return (SuperPowerSaveSettingsObserver) SuperPowerSaveSettingsObserver.instance$delegate.getValue();
        }
    }

    public static final SuperPowerSaveSettingsObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_SUPER_POWERSAVE_MODE_STATE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.isSuperPowerSaveState = CommonSettingsValueProxy.Companion.getSuperPowerSaveState(context) == 1;
        QsColorUtil.INSTANCE.setSuperPowerSaveState(this.isSuperPowerSaveState);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("SuperPowerSaveSetting changed: mIsSuperPowerSaveState = ", "SuperPowerSaveSettingsObserver-->", "SystemUi--QuickSettings", this.isSuperPowerSaveState);
        }
        SuperPowerNotificationHelper superPowerNotificationHelper = SuperPowerNotificationHelper.getInstance(context);
        superPowerNotificationHelper.getClass();
        if (ThreadManager.getInstance().getMainHandler() != null) {
            Handler mainHandler = ThreadManager.getInstance().getMainHandler();
            SuperPowerNotificationHelper$$ExternalSyntheticLambda0 superPowerNotificationHelper$$ExternalSyntheticLambda0 = new SuperPowerNotificationHelper$$ExternalSyntheticLambda0();
            superPowerNotificationHelper$$ExternalSyntheticLambda0.f$0 = superPowerNotificationHelper;
            VarHandle.storeStoreFence();
            mainHandler.post(superPowerNotificationHelper$$ExternalSyntheticLambda0);
        }
        super.onChange(z);
    }
}
