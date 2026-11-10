package com.oplus.systemui.volume.repository;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
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
final class RingModeRepository$settingChanges$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ RingModeRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RingModeRepository$settingChanges$1(RingModeRepository ringModeRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ringModeRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        RingModeRepository$settingChanges$1 ringModeRepository$settingChanges$1 = new RingModeRepository$settingChanges$1(this.this$0, continuation);
        ringModeRepository$settingChanges$1.L$0 = obj;
        return ringModeRepository$settingChanges$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.database.ContentObserver, com.oplus.systemui.volume.repository.RingModeRepository$settingChanges$1$observer$1, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Handler handler = new Handler(Looper.getMainLooper());
            ?? r2 = new ContentObserver(handler) { // from class: com.oplus.systemui.volume.repository.RingModeRepository$settingChanges$1$observer$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z, Uri uri) {
                    producerScope.trySend-JP2dKIU(uri);
                }
            };
            ContentResolver contentResolver = this.this$0.context.getContentResolver();
            RingModeRepository ringModeRepository = this.this$0;
            Iterator it = CollectionsKt__CollectionsKt.listOfNotNull(new Uri[]{ringModeRepository.mAlertSliderUri, ringModeRepository.mVibrateWhenSilentUri}).iterator();
            while (it.hasNext()) {
                contentResolver.registerContentObserver((Uri) it.next(), false, r2);
            }
            RingModeRepository ringModeRepository2 = this.this$0;
            RingModeRepository$settingChanges$1$$ExternalSyntheticLambda0 ringModeRepository$settingChanges$1$$ExternalSyntheticLambda0 = new RingModeRepository$settingChanges$1$$ExternalSyntheticLambda0();
            ringModeRepository$settingChanges$1$$ExternalSyntheticLambda0.f$0 = ringModeRepository2;
            ringModeRepository$settingChanges$1$$ExternalSyntheticLambda0.f$1 = r2;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable((Object) r2);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, ringModeRepository$settingChanges$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
