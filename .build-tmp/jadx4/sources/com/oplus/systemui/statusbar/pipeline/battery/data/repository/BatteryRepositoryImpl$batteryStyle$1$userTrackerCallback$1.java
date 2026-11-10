package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.content.Context;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryStyleModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 implements UserTracker.Callback {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ Ref.ObjectRef $model;
    public /* synthetic */ CoroutineScope $scope;
    public /* synthetic */ BatteryRepositoryImpl this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public final void onUserChanged(int i, Context context) {
        super.onUserChanged(i, context);
        Ref.ObjectRef objectRef = this.$model;
        objectRef.element = BatteryStyleModel.copy$default((BatteryStyleModel) objectRef.element, 0, 0, null, 0, false, 63);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "BatteryRepository-->".concat("onUserChanged model: " + objectRef.element + " newUser:" + i));
        }
        BuildersKt.launch$default(this.$scope, Dispatchers.getMain(), (CoroutineStart) null, new onUserChanged.2(this.$$this$conflatedCallbackFlow, objectRef, (Continuation) null), 2);
    }

    public final void onUserChanging(int i, Context context) {
        BatteryRepositoryImpl batteryRepositoryImpl = this.this$0;
        int batteryStyle = batteryRepositoryImpl.getBatteryStyle(i);
        int displayPowerPercent = batteryRepositoryImpl.getDisplayPowerPercent(i, batteryStyle);
        Ref.ObjectRef objectRef = this.$model;
        objectRef.element = BatteryStyleModel.copy$default((BatteryStyleModel) objectRef.element, displayPowerPercent, batteryStyle, null, 0, true, 60);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "BatteryRepository-->".concat("onUserChanging model: " + objectRef.element + " newUser:" + i));
        }
        BuildersKt.launch$default(this.$scope, Dispatchers.getMain(), (CoroutineStart) null, new onUserChanging.2(this.$$this$conflatedCallbackFlow, objectRef, (Continuation) null), 2);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
