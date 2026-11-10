package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class BatteryRepositoryImpl$speedModeTurnedOn$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BatteryRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryRepositoryImpl$speedModeTurnedOn$1(BatteryRepositoryImpl batteryRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        BatteryRepositoryImpl$speedModeTurnedOn$1 batteryRepositoryImpl$speedModeTurnedOn$1 = new BatteryRepositoryImpl$speedModeTurnedOn$1(this.this$0, continuation);
        batteryRepositoryImpl$speedModeTurnedOn$1.L$0 = obj;
        return batteryRepositoryImpl$speedModeTurnedOn$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Handler handler = new Handler(Looper.getMainLooper());
            final BatteryRepositoryImpl batteryRepositoryImpl = this.this$0;
            ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$speedModeTurnedOn$1$speedModeObserver$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z, Uri uri) {
                    onChange(z);
                    int intForUser = Settings.System.getIntForUser(batteryRepositoryImpl.context.getContentResolver(), "speed_charge_state", 0, 0);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intForUser, "speedModeObserver: ", "BatteryRepository-->", "SystemUi--Statusbar");
                    }
                    producerScope.trySend-JP2dKIU(Boolean.valueOf(intForUser == 3));
                }
            };
            ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("speed_charge_state"), contentObserver, 0);
            BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 = new BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0(3);
            batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0.f$0 = contentObserver;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(contentObserver);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
