package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsGlobalThemeResourceUtil;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileResInteractor$activeColorFlow$1 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ SepQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$activeColorFlow$1(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = sepQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        SepQSTileResInteractor$activeColorFlow$1 sepQSTileResInteractor$activeColorFlow$1 = new SepQSTileResInteractor$activeColorFlow$1(this.this$0, (Continuation) obj4);
        sepQSTileResInteractor$activeColorFlow$1.Z$0 = zBooleanValue;
        sepQSTileResInteractor$activeColorFlow$1.Z$1 = zBooleanValue2;
        sepQSTileResInteractor$activeColorFlow$1.Z$2 = zBooleanValue3;
        return sepQSTileResInteractor$activeColorFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z2) {
            return this.this$0.colorfulAccentColor;
        }
        SepQSTileResInteractor sepQSTileResInteractor = this.this$0;
        if (z) {
            return FlowKt.flowOf(Boxing.boxInt(sepQSTileResInteractor.context.getColor(QsGlobalThemeResourceUtil.getDefaultAccentColor())));
        }
        return z3 ? FlowKt.flowOf(Boxing.boxInt(QSColorfulIconRepository.Companion.getSepTwoToneAccentColor(sepQSTileResInteractor.context, QsColorUtil.INSTANCE.getWallpaperBlurDisable()))) : sepQSTileResInteractor.darkThemeAccentColor;
    }
}
