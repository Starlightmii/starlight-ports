package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Context;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
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
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StdQSTileResInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StdQSTileResInteractor";
    private final StateFlow activeColorFlow;
    private final SharedFlow configuration;
    private final Context context;
    private final StateFlow darkThemeAccentColor;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final StateFlow isGlobalThemeApplied;
    private final StateFlow isHighlightDefaultShape;
    private final StateFlow isLowGaussianLevel;
    private final StateFlow isNightMode;
    private final StateFlow isSupportStrokeCorner;
    private final StateFlow mirrorScaleValue;
    private final OplusPanelModeRepository panelModeRepository;
    private final QSPersonalityRepository personalityRepository;
    private final CoroutineScope scope;
    private final StateFlow superPowerSaveMode;
    private final SharedFlow themeChangeEvent;
    private final Flow themeChangeFlow;
    private final Flow tileShapeConfig;
    private final StateFlow tintTileIconEnable;
    private final StateFlow twoToneThemeEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.StdQSTileResInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return StdQSTileResInteractor.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowTransformLatest = FlowKt.transformLatest(StdQSTileResInteractor.this.panelModeRepository.getEnableSeparateNotificationAndQS(), new StdQSTileResInteractor$init$1$invokeSuspend$$inlined$flatMapLatest$1(StdQSTileResInteractor.this, null));
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

    public StdQSTileResInteractor(CoroutineScope coroutineScope, Context context, QSPersonalityRepository qSPersonalityRepository, OplusPanelModeRepository oplusPanelModeRepository, QSColorfulIconRepository qSColorfulIconRepository, QSConfigurationRepository qSConfigurationRepository, QSThemeConfigRepository qSThemeConfigRepository, SuperPowerSaveRepository superPowerSaveRepository) {
        this.scope = coroutineScope;
        this.context = context;
        this.personalityRepository = qSPersonalityRepository;
        this.panelModeRepository = oplusPanelModeRepository;
        this.configuration = qSConfigurationRepository.getConfiguration();
        this.isNightMode = qSConfigurationRepository.isNightMode();
        this.isGlobalThemeApplied = qSThemeConfigRepository.isGlobalThemeApplied();
        StateFlow stateFlowIsLowGaussianLevel = qSThemeConfigRepository.isLowGaussianLevel();
        this.isLowGaussianLevel = stateFlowIsLowGaussianLevel;
        SharedFlow themeChangeEvent = qSThemeConfigRepository.getThemeChangeEvent();
        this.themeChangeEvent = themeChangeEvent;
        this.superPowerSaveMode = superPowerSaveRepository.getSuperPowerSaveMode();
        BaseQSResPool baseQSResPool = BaseQSResPool.INSTANCE;
        StateFlow stateFlowM244getDarkThemeAccentColor = baseQSResPool.m244getDarkThemeAccentColor();
        this.darkThemeAccentColor = stateFlowM244getDarkThemeAccentColor;
        this.tileShapeConfig = qSPersonalityRepository.getStdTileShapeConfig();
        this.isHighlightDefaultShape = qSPersonalityRepository.isHighlightDefaultShape();
        this.tintTileIconEnable = qSColorfulIconRepository.getTintTileIconEnable();
        this.isSupportStrokeCorner = qSPersonalityRepository.getStdSupportStrokeCorner();
        StateFlow twoToneThemeEnabled = qSColorfulIconRepository.getTwoToneThemeEnabled();
        this.twoToneThemeEnabled = twoToneThemeEnabled;
        this.mirrorScaleValue = baseQSResPool.getMirrorScaleValue();
        this.activeColorFlow = FlowKt.stateIn(FlowKt.onEach(FlowKt.transformLatest(FlowKt.combine(stateFlowM244getDarkThemeAccentColor, twoToneThemeEnabled, stateFlowIsLowGaussianLevel, new StdQSTileResInteractor$activeColorFlow$1(this, null)), new StdQSTileResInteractor$special$$inlined$flatMapLatest$1(3, null)), new StdQSTileResInteractor$activeColorFlow$3(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), stateFlowM244getDarkThemeAccentColor.getValue());
        StdQSTileResInteractor$special$$inlined$map$1 stdQSTileResInteractor$special$$inlined$map$1 = new StdQSTileResInteractor$special$$inlined$map$1();
        stdQSTileResInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = themeChangeEvent;
        VarHandle.storeStoreFence();
        this.themeChangeFlow = FlowKt.onStart(stdQSTileResInteractor$special$$inlined$map$1, new StdQSTileResInteractor$themeChangeFlow$2(2, null));
    }

    public static final Object access$startDetailTileBackgroundCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(stdQSTileResInteractor.isGlobalThemeApplied, new StdQSTileResInteractor$startDetailTileBackgroundCollection$$inlined$flatMapLatest$1(stdQSTileResInteractor, null))), new StdQSTileResInteractor$startDetailTileBackgroundCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startDetailTileIconColorStateCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(stdQSTileResInteractor.isGlobalThemeApplied, new StdQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1(stdQSTileResInteractor, null))), new StdQSTileResInteractor$startDetailTileIconColorStateCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startHighlightBackgroundCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(stdQSTileResInteractor.isLowGaussianLevel, stdQSTileResInteractor.superPowerSaveMode, stdQSTileResInteractor.isGlobalThemeApplied, new StdQSTileResInteractor$startHighlightBackgroundCollection$2(stdQSTileResInteractor, null)), new StdQSTileResInteractor$startHighlightBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new StdQSTileResInteractor$startHighlightBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startHighlightTileOutlineCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(stdQSTileResInteractor.configuration, stdQSTileResInteractor.tileShapeConfig, stdQSTileResInteractor.isHighlightDefaultShape, new StdQSTileResInteractor$startHighlightTileOutlineCollection$2(stdQSTileResInteractor, null))), new StdQSTileResInteractor$startHighlightTileOutlineCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startQuickEntranceBackgroundCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(stdQSTileResInteractor.isGlobalThemeApplied, stdQSTileResInteractor.isLowGaussianLevel, stdQSTileResInteractor.superPowerSaveMode, new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$2(stdQSTileResInteractor, null)), new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startTileIconColorStateCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(stdQSTileResInteractor.tintTileIconEnable, new StdQSTileResInteractor$startTileIconColorStateCollection$$inlined$flatMapLatest$1(FlowKt.combine(stdQSTileResInteractor.isGlobalThemeApplied, stdQSTileResInteractor.isNightMode, stdQSTileResInteractor.isLowGaussianLevel, stdQSTileResInteractor.themeChangeFlow, stdQSTileResInteractor.twoToneThemeEnabled, new StdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(stdQSTileResInteractor, null)), null))), new StdQSTileResInteractor$startTileIconColorStateCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startTileOutlineCollection(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.combine(stdQSTileResInteractor.configuration, stdQSTileResInteractor.tileShapeConfig, new StdQSTileResInteractor$startTileOutlineCollection$2(stdQSTileResInteractor, null))), new StdQSTileResInteractor$startTileOutlineCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }
}
