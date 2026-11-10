package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Context;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.res.data.repository.QSConfigurationRepository;
import com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository;
import com.oplus.systemui.qs.res.data.repository.QSThemeConfigRepository;
import com.oplus.systemui.separate.data.repository.OplusPanelModeRepository;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSResInteractor {
    private final SharedFlow configuration;
    private final Context context;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final StateFlow isGlobalThemeApplied;
    private final StateFlow isLowGaussianLevel;
    private final StateFlow isNightMode;
    private final Flow isSupportSpotLight;
    private final StateFlow isSupportStrokeCorner;
    private final StateFlow mirrorScaleValue;
    private final OplusPanelModeRepository panelModeRepository;
    private final QSPersonalityRepository personalityRepository;
    private final CoroutineScope scope;
    private final StateFlow superPowerSaveMode;
    private final SharedFlow themeChangeEvent;
    private final Flow themeChangeFlow;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSResInteractor.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowTransformLatest = FlowKt.transformLatest(SepQSResInteractor.this.panelModeRepository.getEnableSeparateNotificationAndQS(), new SepQSResInteractor$init$1$invokeSuspend$$inlined$flatMapLatest$1(SepQSResInteractor.this, null));
                this.label = 1;
                if (FlowKt.collect(flowTransformLatest, this) == coroutine_suspended) {
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

    public SepQSResInteractor(CoroutineScope coroutineScope, Context context, QSPersonalityRepository qSPersonalityRepository, QSConfigurationRepository qSConfigurationRepository, OplusPanelModeRepository oplusPanelModeRepository, QSThemeConfigRepository qSThemeConfigRepository, SuperPowerSaveRepository superPowerSaveRepository) {
        this.scope = coroutineScope;
        this.context = context;
        this.personalityRepository = qSPersonalityRepository;
        this.panelModeRepository = oplusPanelModeRepository;
        this.configuration = qSConfigurationRepository.getConfiguration();
        this.isNightMode = qSConfigurationRepository.isNightMode();
        this.isGlobalThemeApplied = qSThemeConfigRepository.isGlobalThemeApplied();
        SharedFlow themeChangeEvent = qSThemeConfigRepository.getThemeChangeEvent();
        this.themeChangeEvent = themeChangeEvent;
        this.isLowGaussianLevel = qSThemeConfigRepository.isLowGaussianLevel();
        this.superPowerSaveMode = superPowerSaveRepository.getSuperPowerSaveMode();
        this.isSupportStrokeCorner = qSPersonalityRepository.getSepSupportStrokeCorner();
        this.isSupportSpotLight = qSPersonalityRepository.getSepSupportSpotLight();
        this.mirrorScaleValue = BaseQSResPool.INSTANCE.getMirrorScaleValue();
        SepQSResInteractor$special$$inlined$map$1 sepQSResInteractor$special$$inlined$map$1 = new SepQSResInteractor$special$$inlined$map$1();
        sepQSResInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = themeChangeEvent;
        VarHandle.storeStoreFence();
        this.themeChangeFlow = FlowKt.onStart(sepQSResInteractor$special$$inlined$map$1, new SepQSResInteractor$themeChangeFlow$2(2, null));
    }

    public static final Object access$startCustomizerBackgroundCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(sepQSResInteractor.isLowGaussianLevel, sepQSResInteractor.superPowerSaveMode, new SepQSResInteractor$startCustomizerBackgroundCollection$2(sepQSResInteractor, null)), new SepQSResInteractor$startCustomizerBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new SepQSResInteractor$startCustomizerBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startDetailPanelBackgroundCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.transformLatest(FlowKt.combine(sepQSResInteractor.isGlobalThemeApplied, sepQSResInteractor.isLowGaussianLevel, sepQSResInteractor.superPowerSaveMode, new SepQSResInteractor$startDetailPanelBackgroundCollection$2(sepQSResInteractor, null)), new SepQSResInteractor$startDetailPanelBackgroundCollection$$inlined$flatMapLatest$1(3, null)), new SepQSResInteractor$startDetailPanelBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startMediaPanelBackgroundCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(sepQSResInteractor.isLowGaussianLevel, sepQSResInteractor.superPowerSaveMode, new SepQSResInteractor$startMediaPanelBackgroundCollection$2(sepQSResInteractor, null)), new SepQSResInteractor$startMediaPanelBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new SepQSResInteractor$startMediaPanelBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startMediaPanelOutlineCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(sepQSResInteractor.configuration, sepQSResInteractor.personalityRepository.getSepMediaPanelRadiusRes(), new SepQSResInteractor$startMediaPanelOutlineCollection$2(sepQSResInteractor, null))), new SepQSResInteractor$startMediaPanelOutlineCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startMediaPanelThemeBackgroundCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSResInteractor.isGlobalThemeApplied, new SepQSResInteractor$startMediaPanelThemeBackgroundCollection$$inlined$flatMapLatest$1(sepQSResInteractor, null))), new SepQSResInteractor$startMediaPanelThemeBackgroundCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startQuickEntranceBackgroundCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(sepQSResInteractor.isGlobalThemeApplied, sepQSResInteractor.isLowGaussianLevel, sepQSResInteractor.superPowerSaveMode, new SepQSResInteractor$startQuickEntranceBackgroundCollection$2(sepQSResInteractor, null)), new SepQSResInteractor$startQuickEntranceBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new SepQSResInteractor$startQuickEntranceBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startSpotLightEnableCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(sepQSResInteractor.isSupportSpotLight, sepQSResInteractor.isGlobalThemeApplied, new SepQSResInteractor$startSpotLightEnableCollection$2(3, null))), new SepQSResInteractor$startSpotLightEnableCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startStrokeEnableCollection(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(sepQSResInteractor.isSupportStrokeCorner, new SepQSResInteractor$startStrokeEnableCollection$2(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }
}
