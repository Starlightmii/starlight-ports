package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.database.ContentObserver;
import android.os.Handler;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
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
final class OplusWifiRepositoryImpl$wifiTechnicalEnable$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OplusWifiRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusWifiRepositoryImpl$wifiTechnicalEnable$1(OplusWifiRepositoryImpl oplusWifiRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusWifiRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusWifiRepositoryImpl$wifiTechnicalEnable$1 oplusWifiRepositoryImpl$wifiTechnicalEnable$1 = new OplusWifiRepositoryImpl$wifiTechnicalEnable$1(this.this$0, continuation);
        oplusWifiRepositoryImpl$wifiTechnicalEnable$1.L$0 = obj;
        return oplusWifiRepositoryImpl$wifiTechnicalEnable$1;
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
            Handler handler = new Handler();
            final OplusWifiRepositoryImpl oplusWifiRepositoryImpl = this.this$0;
            ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$wifiTechnicalEnable$1$wifiTechnicalStandardObserver$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    int wifiTechnicalStandardState = CommonSettingsValueProxy.Companion.getWifiTechnicalStandardState(oplusWifiRepositoryImpl.context);
                    boolean z2 = wifiTechnicalStandardState == 1;
                    LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusWifiRepositoryImpl", KeyguardLogger$.ExternalSyntheticOutline0.m("mWifiTechnicalOn = ", z2, ", getWifiTechnicalStandardState = ", wifiTechnicalStandardState), null, 8);
                    OplusWifiRepositoryImpl.access$logWifiTechnicalEnable(oplusWifiRepositoryImpl, z2, wifiTechnicalStandardState);
                    producerScope.trySend-JP2dKIU(Boolean.valueOf(z2));
                }
            };
            MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
            companion.registerForUserSwitch(this.this$0.context, companion.getGlobalUri(CommonSettingsValueProxy.KEY_WIFI_USE_TECHNICAL_STANDARD_ICONS_SWITCH_ON), false, contentObserver, -2);
            OplusWifiRepositoryImpl oplusWifiRepositoryImpl2 = this.this$0;
            OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0 oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0 = new OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0(1);
            oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0.f$0 = oplusWifiRepositoryImpl2;
            oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0.f$1 = contentObserver;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(contentObserver);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
