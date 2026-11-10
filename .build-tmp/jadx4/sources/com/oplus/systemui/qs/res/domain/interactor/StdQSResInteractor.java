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
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
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
public final class StdQSResInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StdQSResInteractor";
    private final SharedFlow configuration;
    private final Context context;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final StateFlow isGlobalThemeApplied;
    private final StateFlow isHighlightDefaultShape;
    private final StateFlow isLowGaussianLevel;
    private final StateFlow isNightMode;
    private final Flow isSupportSpotLight;
    private final StateFlow isSupportStrokeCorner;
    private final Flow mediaTileShapeConfig;
    private final StateFlow mirrorScaleValue;
    private final OplusPanelModeRepository panelModeRepository;
    private final QSPersonalityRepository personalityRepository;
    private final CoroutineScope scope;
    private final StateFlow superPowerSaveMode;
    private final SharedFlow themeChangeEvent;
    private final Flow themeChangeFlow;
    private final Flow tileShapeConfig;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.StdQSResInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return StdQSResInteractor.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowTransformLatest = FlowKt.transformLatest(StdQSResInteractor.this.panelModeRepository.getEnableSeparateNotificationAndQS(), new StdQSResInteractor$init$1$invokeSuspend$$inlined$flatMapLatest$1(StdQSResInteractor.this, null));
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

    public StdQSResInteractor(CoroutineScope coroutineScope, Context context, QSPersonalityRepository qSPersonalityRepository, OplusPanelModeRepository oplusPanelModeRepository, QSConfigurationRepository qSConfigurationRepository, QSThemeConfigRepository qSThemeConfigRepository, SuperPowerSaveRepository superPowerSaveRepository) {
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
        this.tileShapeConfig = qSPersonalityRepository.getStdTileShapeConfig();
        this.mediaTileShapeConfig = qSPersonalityRepository.getStdMediaTileShapeConfig();
        this.isHighlightDefaultShape = qSPersonalityRepository.isHighlightDefaultShape();
        this.superPowerSaveMode = superPowerSaveRepository.getSuperPowerSaveMode();
        this.isSupportStrokeCorner = qSPersonalityRepository.getStdSupportStrokeCorner();
        this.isSupportSpotLight = qSPersonalityRepository.getStdSupportSpotLight();
        this.mirrorScaleValue = BaseQSResPool.INSTANCE.getMirrorScaleValue();
        StdQSResInteractor$special$$inlined$map$1 stdQSResInteractor$special$$inlined$map$1 = new StdQSResInteractor$special$$inlined$map$1();
        stdQSResInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = themeChangeEvent;
        VarHandle.storeStoreFence();
        this.themeChangeFlow = FlowKt.onStart(stdQSResInteractor$special$$inlined$map$1, new StdQSResInteractor$themeChangeFlow$2(2, null));
    }

    public static final Object access$startDetailPanelBackgroundCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.transformLatest(FlowKt.combine(stdQSResInteractor.isGlobalThemeApplied, stdQSResInteractor.isLowGaussianLevel, stdQSResInteractor.superPowerSaveMode, new StdQSResInteractor$startDetailPanelBackgroundCollection$2(stdQSResInteractor, null)), new StdQSResInteractor$startDetailPanelBackgroundCollection$$inlined$flatMapLatest$1(3, null)), new StdQSResInteractor$startDetailPanelBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startHighlightDefaultShapeCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(stdQSResInteractor.isHighlightDefaultShape, new StdQSResInteractor$startHighlightDefaultShapeCollection$2(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startMediaPanelBackgroundCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(stdQSResInteractor.isLowGaussianLevel, stdQSResInteractor.superPowerSaveMode, stdQSResInteractor.isGlobalThemeApplied, new StdQSResInteractor$startMediaPanelBackgroundCollection$2(stdQSResInteractor, null)), new StdQSResInteractor$startMediaPanelBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new StdQSResInteractor$startMediaPanelBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startMediaPanelOutlineCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(stdQSResInteractor.configuration, FlowKt.distinctUntilChanged(FlowKt.transformLatest(stdQSResInteractor.isGlobalThemeApplied, new StdQSResInteractor$startMediaPanelOutlineCollection$getCornerRadiusRes$$inlined$flatMapLatest$1(null, FlowKt.flowOf(Boxing.boxInt(stdQSResInteractor.personalityRepository.getDefaultMediaConfig().getRadiusRes())), stdQSResInteractor))), new StdQSResInteractor$startMediaPanelOutlineCollection$2(stdQSResInteractor, null))), new StdQSResInteractor$startMediaPanelOutlineCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startSpotLightEnableCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(stdQSResInteractor.isSupportSpotLight, stdQSResInteractor.isGlobalThemeApplied, new StdQSResInteractor$startSpotLightEnableCollection$2(3, null))), new StdQSResInteractor$startSpotLightEnableCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startStrokeEnableCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(stdQSResInteractor.isSupportStrokeCorner, new StdQSResInteractor$startStrokeEnableCollection$2(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$startThemeChangeCollection(StdQSResInteractor stdQSResInteractor, ContinuationImpl continuationImpl) {
        StdQSResInteractor$startThemeChangeCollection$1 stdQSResInteractor$startThemeChangeCollection$1;
        stdQSResInteractor.getClass();
        if (continuationImpl instanceof StdQSResInteractor$startThemeChangeCollection$1) {
            stdQSResInteractor$startThemeChangeCollection$1 = (StdQSResInteractor$startThemeChangeCollection$1) continuationImpl;
            int i = stdQSResInteractor$startThemeChangeCollection$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                stdQSResInteractor$startThemeChangeCollection$1.label = i - Integer.MIN_VALUE;
            } else {
                stdQSResInteractor$startThemeChangeCollection$1 = new StdQSResInteractor$startThemeChangeCollection$1(stdQSResInteractor, continuationImpl);
            }
        } else {
            stdQSResInteractor$startThemeChangeCollection$1 = new StdQSResInteractor$startThemeChangeCollection$1(stdQSResInteractor, continuationImpl);
        }
        Object obj = stdQSResInteractor$startThemeChangeCollection$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = stdQSResInteractor$startThemeChangeCollection$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow sharedFlow = stdQSResInteractor.themeChangeEvent;
            StdQSResInteractor$startThemeChangeCollection$2 stdQSResInteractor$startThemeChangeCollection$2 = new StdQSResInteractor$startThemeChangeCollection$2();
            stdQSResInteractor$startThemeChangeCollection$2.this$0 = stdQSResInteractor;
            VarHandle.storeStoreFence();
            stdQSResInteractor$startThemeChangeCollection$1.label = 1;
            if (sharedFlow.collect(stdQSResInteractor$startThemeChangeCollection$2, stdQSResInteractor$startThemeChangeCollection$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    public static final Object access$startTileShapeConfigCollection(StdQSResInteractor stdQSResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(stdQSResInteractor.isGlobalThemeApplied, new StdQSResInteractor$startTileShapeConfigCollection$$inlined$flatMapLatest$1(null, FlowKt.flowOf(stdQSResInteractor.personalityRepository.getDefaultConfig()), stdQSResInteractor))), new StdQSResInteractor$startTileShapeConfigCollection$2(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }
}
