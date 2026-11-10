package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1 extends SuspendLambda implements Function5 {
    final /* synthetic */ boolean $isScenario;
    /* synthetic */ float F$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ SepQSTileResInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ GradientStrokeLineAdapter.StrokeParamsTemplate $activeParamsTemplate;
        final /* synthetic */ BlurMixConfig $inactiveConfig;
        final /* synthetic */ GradientStrokeLineAdapter.StrokeParamsTemplate $inactiveParamsTemplate;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BlurMixConfig blurMixConfig, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate2, Continuation continuation) {
            super(2, continuation);
            this.$inactiveConfig = blurMixConfig;
            this.$activeParamsTemplate = strokeParamsTemplate;
            this.$inactiveParamsTemplate = strokeParamsTemplate2;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inactiveConfig, this.$activeParamsTemplate, this.$inactiveParamsTemplate, continuation);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            int i = this.I$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BlurMixConfig blurMixConfig = this.$inactiveConfig;
            return new MixColorTileDrawable.TileTypeConfig(blurMixConfig, blurMixConfig, i, 0, this.$activeParamsTemplate, this.$inactiveParamsTemplate);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation, boolean z) {
        super(5, continuation);
        this.$isScenario = z;
        this.this$0 = sepQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        float fFloatValue = ((Number) obj4).floatValue();
        boolean z = this.$isScenario;
        SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1 sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1 = new SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1(this.this$0, (Continuation) obj5, z);
        sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1.Z$0 = zBooleanValue;
        sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1.Z$1 = zBooleanValue2;
        sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1.Z$2 = zBooleanValue3;
        sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1.F$0 = fFloatValue;
        return sepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        float f = this.F$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        GradientStrokeLineAdapter.StrokeParamsTemplate qSTileActiveBlurLightConfig = GradientStrokeLineAdapter.getQSTileActiveBlurLightConfig(z3, z2);
        GradientStrokeLineAdapter.StrokeParamsTemplate qSTileInActiveBlurLightConfig = GradientStrokeLineAdapter.getQSTileInActiveBlurLightConfig(z3, z2);
        BlurMixConfig scenarioInactiveBlurConfig = this.$isScenario ? QSBlurConfigProvider.getScenarioInactiveBlurConfig(z3, f) : QSBlurConfigProvider.getSepInactiveBlurConfig(z3, z2, f);
        return z ? FlowKt.flowOf(new MixColorTileDrawable.TileTypeConfig(QSBlurConfigProvider.getSepActiveBlurConfig(z3, f), scenarioInactiveBlurConfig, 0, 0, qSTileActiveBlurLightConfig, qSTileInActiveBlurLightConfig, 12)) : FlowKt.mapLatest(this.this$0.activeColorFlow, new AnonymousClass1(scenarioInactiveBlurConfig, qSTileActiveBlurLightConfig, qSTileInActiveBlurLightConfig, null));
    }
}
