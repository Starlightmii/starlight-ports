package com.oplus.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.common.shared.model.PackageChangeModel;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SysInlineServiceRepository$init$2$1$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SysInlineServiceRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SysInlineServiceRepository$init$2$1$2(SysInlineServiceRepository sysInlineServiceRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sysInlineServiceRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SysInlineServiceRepository$init$2$1$2 sysInlineServiceRepository$init$2$1$2 = new SysInlineServiceRepository$init$2$1$2(this.this$0, continuation);
        sysInlineServiceRepository$init$2$1$2.L$0 = obj;
        return sysInlineServiceRepository$init$2$1$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((PackageChangeModel) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PackageChangeModel packageChangeModel = (PackageChangeModel) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        Iterable<TileSpec> iterable = (Iterable) this.this$0.currentCustomTileSpecs.get();
        SysInlineServiceRepository sysInlineServiceRepository = this.this$0;
        for (TileSpec tileSpec : iterable) {
            if (StringsKt__StringsKt.contains(tileSpec.getSpec(), packageChangeModel.getPackageName(), false)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("packageChanged spec: ", tileSpec.getSpec(), "SysInlineServiceRepository-->", "SystemUi--QuickSettings");
                }
                sysInlineServiceRepository.customTileEx.updateTileServiceConfig(tileSpec.getSpec());
            }
        }
        return Unit.INSTANCE;
    }
}
