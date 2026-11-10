package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.content.res.Configuration;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryStyleModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryRepositoryImpl$batteryStyle$1$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ Ref.ObjectRef $model;

    public final void onConfigChanged(Configuration configuration) {
        Ref.ObjectRef objectRef = this.$model;
        objectRef.element = BatteryStyleModel.copy$default((BatteryStyleModel) objectRef.element, 0, 0, configuration.getLocales(), configuration.densityDpi, false, 103);
    }

    public final void onDensityOrFontScaleChanged() {
        Ref.ObjectRef objectRef = this.$model;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onDensityOrFontScaleChanged, model: ", objectRef.element, "BatteryRepository-->", "SystemUi--Statusbar");
        }
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
    }

    public final void onLocaleListChanged() {
        Ref.ObjectRef objectRef = this.$model;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onLocaleListChanged, model: ", objectRef.element, "BatteryRepository-->", "SystemUi--Statusbar");
        }
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
    }
}
