package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Context;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableConfig;
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
import kotlin.jvm.functions.Function3;
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
public final class SepQSTileResInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepQSTileResInteractor";
    private final StateFlow activeColorFlow;
    private final StateFlow colorfulAccentColor;
    private final StateFlow colorfulIconAvailable;
    private final SharedFlow configuration;
    private final Context context;
    private final StateFlow darkThemeAccentColor;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final StateFlow isGlobalThemeApplied;
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
    private final StateFlow tintTileIconEnable;
    private final StateFlow twoToneThemeEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSTileResInteractor.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowTransformLatest = FlowKt.transformLatest(SepQSTileResInteractor.this.panelModeRepository.getEnableSeparateNotificationAndQS(), new SepQSTileResInteractor$init$1$invokeSuspend$$inlined$flatMapLatest$1(SepQSTileResInteractor.this, null));
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startHighLightTileBackgroundCollection$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Continuation continuation, boolean z) {
            super(3, continuation);
            this.$isScenario = z;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            AnonymousClass2 anonymousClass2 = SepQSTileResInteractor.this.new AnonymousClass2((Continuation) obj3, this.$isScenario);
            anonymousClass2.Z$0 = zBooleanValue;
            anonymousClass2.Z$1 = zBooleanValue2;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            SepQSTileResInteractor sepQSTileResInteractor = SepQSTileResInteractor.this;
            if (z2) {
                return sepQSTileResInteractor.getSuperPowerBackground(true);
            }
            return z ? SepQSTileResInteractor.access$getLightOSBackground(sepQSTileResInteractor, true) : SepQSTileResInteractor.access$startHighLightTileBackgroundCollection$getBlurBackground$5(sepQSTileResInteractor, this.$isScenario);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startHighLightTileBackgroundCollection$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$isScenario = z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$isScenario, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((QSDrawableConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            QSDrawableConfig qSDrawableConfig = (QSDrawableConfig) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (this.$isScenario) {
                SepQSResPool.updateScenarioHighlightTileViewDrawable(qSDrawableConfig.getSepHighlightTypeBuilder());
            } else {
                SepQSResPool.updateHighLightTileViewDrawable(qSDrawableConfig.getSepHighlightTypeBuilder());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startHighlightIconBgCollection$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02672 extends SuspendLambda implements Function3 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02672(Continuation continuation, boolean z) {
            super(3, continuation);
            this.$isScenario = z;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            C02672 c02672 = SepQSTileResInteractor.this.new C02672((Continuation) obj3, this.$isScenario);
            c02672.Z$0 = zBooleanValue;
            c02672.Z$1 = zBooleanValue2;
            return c02672.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return (z && z2) ? SepQSTileResInteractor.access$startHighlightIconBgCollection$getBlurBackground$10(SepQSTileResInteractor.this, this.$isScenario) : SepQSTileResInteractor.access$startHighlightIconBgCollection$getGradientBackground(SepQSTileResInteractor.this);
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startHighlightIconBgCollection$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02684 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02684(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$isScenario = z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C02684 c02684 = new C02684(this.$isScenario, continuation);
            c02684.L$0 = obj;
            return c02684;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((QSDrawableConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            QSDrawableConfig qSDrawableConfig = (QSDrawableConfig) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (this.$isScenario) {
                SepQSResPool.updateScenarioHighlightTileIconDrawable(qSDrawableConfig.getSepTileTypeBuilder());
            } else {
                SepQSResPool.updateHighLightIconBg(qSDrawableConfig.getSepHighlightIconBgBuilder());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startTileBackgroundCollection$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02692 extends SuspendLambda implements Function3 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02692(Continuation continuation, boolean z) {
            super(3, continuation);
            this.$isScenario = z;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            C02692 c02692 = SepQSTileResInteractor.this.new C02692((Continuation) obj3, this.$isScenario);
            c02692.Z$0 = zBooleanValue;
            c02692.Z$1 = zBooleanValue2;
            return c02692.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            SepQSTileResInteractor sepQSTileResInteractor = SepQSTileResInteractor.this;
            if (z2) {
                return sepQSTileResInteractor.getSuperPowerBackground(false);
            }
            return z ? SepQSTileResInteractor.access$getLightOSBackground(sepQSTileResInteractor, false) : SepQSTileResInteractor.access$startTileBackgroundCollection$getBlurBackground(sepQSTileResInteractor, this.$isScenario);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor$startTileBackgroundCollection$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02704 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $isScenario;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02704(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$isScenario = z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C02704 c02704 = new C02704(this.$isScenario, continuation);
            c02704.L$0 = obj;
            return c02704;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((QSDrawableConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            QSDrawableConfig qSDrawableConfig = (QSDrawableConfig) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (this.$isScenario) {
                SepQSResPool.updateScenarioTileViewDrawable(qSDrawableConfig.getSepTileTypeBuilder());
            } else {
                SepQSResPool.updateTileViewDrawable(qSDrawableConfig.getSepTileTypeBuilder());
            }
            return Unit.INSTANCE;
        }
    }

    public SepQSTileResInteractor(CoroutineScope coroutineScope, Context context, QSPersonalityRepository qSPersonalityRepository, OplusPanelModeRepository oplusPanelModeRepository, QSColorfulIconRepository qSColorfulIconRepository, QSConfigurationRepository qSConfigurationRepository, QSThemeConfigRepository qSThemeConfigRepository, SuperPowerSaveRepository superPowerSaveRepository) {
        this.scope = coroutineScope;
        this.context = context;
        this.personalityRepository = qSPersonalityRepository;
        this.panelModeRepository = oplusPanelModeRepository;
        this.configuration = qSConfigurationRepository.getConfiguration();
        this.isNightMode = qSConfigurationRepository.isNightMode();
        StateFlow stateFlowIsGlobalThemeApplied = qSThemeConfigRepository.isGlobalThemeApplied();
        this.isGlobalThemeApplied = stateFlowIsGlobalThemeApplied;
        this.isLowGaussianLevel = qSThemeConfigRepository.isLowGaussianLevel();
        SharedFlow themeChangeEvent = qSThemeConfigRepository.getThemeChangeEvent();
        this.themeChangeEvent = themeChangeEvent;
        this.superPowerSaveMode = superPowerSaveRepository.getSuperPowerSaveMode();
        StateFlow colorfulIconAvailable = qSColorfulIconRepository.getColorfulIconAvailable();
        this.colorfulIconAvailable = colorfulIconAvailable;
        this.colorfulAccentColor = qSColorfulIconRepository.getAccentColor();
        BaseQSResPool baseQSResPool = BaseQSResPool.INSTANCE;
        StateFlow stateFlowM244getDarkThemeAccentColor = baseQSResPool.m244getDarkThemeAccentColor();
        this.darkThemeAccentColor = stateFlowM244getDarkThemeAccentColor;
        this.tintTileIconEnable = qSColorfulIconRepository.getTintTileIconEnable();
        this.isSupportStrokeCorner = qSPersonalityRepository.getSepSupportStrokeCorner();
        StateFlow twoToneThemeEnabled = qSColorfulIconRepository.getTwoToneThemeEnabled();
        this.twoToneThemeEnabled = twoToneThemeEnabled;
        this.mirrorScaleValue = baseQSResPool.getMirrorScaleValue();
        this.activeColorFlow = FlowKt.stateIn(FlowKt.onEach(FlowKt.transformLatest(FlowKt.combine(stateFlowIsGlobalThemeApplied, colorfulIconAvailable, twoToneThemeEnabled, new SepQSTileResInteractor$activeColorFlow$1(this, null)), new SepQSTileResInteractor$special$$inlined$flatMapLatest$1(3, null)), new SepQSTileResInteractor$activeColorFlow$3(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), stateFlowM244getDarkThemeAccentColor.getValue());
        SepQSTileResInteractor$special$$inlined$map$1 sepQSTileResInteractor$special$$inlined$map$1 = new SepQSTileResInteractor$special$$inlined$map$1();
        sepQSTileResInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = themeChangeEvent;
        VarHandle.storeStoreFence();
        this.themeChangeFlow = FlowKt.onStart(sepQSTileResInteractor$special$$inlined$map$1, new SepQSTileResInteractor$themeChangeFlow$2(2, null));
    }

    public static final Flow access$getLightOSBackground(SepQSTileResInteractor sepQSTileResInteractor, boolean z) {
        return FlowKt.combine(sepQSTileResInteractor.activeColorFlow, sepQSTileResInteractor.isNightMode, new SepQSTileResInteractor$getLightOSBackground$1(z, null));
    }

    public static final Object access$startDetailTileBackgroundCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.isGlobalThemeApplied, new SepQSTileResInteractor$startDetailTileBackgroundCollection$$inlined$flatMapLatest$1(sepQSTileResInteractor, null))), new SepQSTileResInteractor$startDetailTileBackgroundCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startDetailTileIconColorStateCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.isGlobalThemeApplied, new SepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1(sepQSTileResInteractor, null))), new SepQSTileResInteractor$startDetailTileIconColorStateCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Flow access$startHighLightTileBackgroundCollection$getBlurBackground$5(SepQSTileResInteractor sepQSTileResInteractor, boolean z) {
        return FlowKt.transformLatest(FlowKt.combine(sepQSTileResInteractor.colorfulIconAvailable, sepQSTileResInteractor.isSupportStrokeCorner, sepQSTileResInteractor.isNightMode, sepQSTileResInteractor.mirrorScaleValue, new SepQSTileResInteractor$startHighLightTileBackgroundCollection$getBlurBackground$1(sepQSTileResInteractor, null, z)), new SepQSTileResInteractor$startHighLightTileBackgroundCollection$getBlurBackground$5$$inlined$flatMapLatest$1(3, null));
    }

    public static final Flow access$startHighlightIconBgCollection$getBlurBackground$10(SepQSTileResInteractor sepQSTileResInteractor, boolean z) {
        return FlowKt.combine(sepQSTileResInteractor.isSupportStrokeCorner, sepQSTileResInteractor.isNightMode, sepQSTileResInteractor.mirrorScaleValue, new SepQSTileResInteractor$startHighlightIconBgCollection$getBlurBackground$1(z, null));
    }

    public static final Flow access$startHighlightIconBgCollection$getGradientBackground(SepQSTileResInteractor sepQSTileResInteractor) {
        return FlowKt.mapLatest(sepQSTileResInteractor.activeColorFlow, new SepQSTileResInteractor$startHighlightIconBgCollection$getGradientBackground$1(2, null));
    }

    public static final Object access$startHighlightIconThemeBgCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.isGlobalThemeApplied, new SepQSTileResInteractor$startHighlightIconThemeBgCollection$$inlined$flatMapLatest$1(sepQSTileResInteractor, null))), new SepQSTileResInteractor$startHighlightIconThemeBgCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startHighlightThemeBackgroundCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.isGlobalThemeApplied, new SepQSTileResInteractor$startHighlightThemeBackgroundCollection$$inlined$flatMapLatest$1(sepQSTileResInteractor, null))), new SepQSTileResInteractor$startHighlightThemeBackgroundCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startHighlightTileOutlineCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.mapLatest(sepQSTileResInteractor.configuration, new SepQSTileResInteractor$startHighlightTileOutlineCollection$2(2, null))), new SepQSTileResInteractor$startHighlightTileOutlineCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Flow access$startTileBackgroundCollection$getBlurBackground(SepQSTileResInteractor sepQSTileResInteractor, boolean z) {
        return FlowKt.transformLatest(FlowKt.combine(sepQSTileResInteractor.colorfulIconAvailable, sepQSTileResInteractor.isSupportStrokeCorner, sepQSTileResInteractor.isNightMode, sepQSTileResInteractor.mirrorScaleValue, new SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1(sepQSTileResInteractor, null, z)), new SepQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$$inlined$flatMapLatest$1(3, null));
    }

    public static final Object access$startTileIconColorStateCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.tintTileIconEnable, new SepQSTileResInteractor$startTileIconColorStateCollection$$inlined$flatMapLatest$1(FlowKt.combine(sepQSTileResInteractor.colorfulIconAvailable, sepQSTileResInteractor.twoToneThemeEnabled, sepQSTileResInteractor.isNightMode, new SepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(sepQSTileResInteractor, null)), null))), new SepQSTileResInteractor$startTileIconColorStateCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startTileIconThemeColorStateCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(sepQSTileResInteractor.isGlobalThemeApplied, sepQSTileResInteractor.tintTileIconEnable, new SepQSTileResInteractor$startTileIconThemeColorStateCollection$2(3, null)), new SepQSTileResInteractor$startTileIconThemeColorStateCollection$$inlined$flatMapLatest$1(FlowKt.combine(sepQSTileResInteractor.twoToneThemeEnabled, sepQSTileResInteractor.themeChangeFlow, new SepQSTileResInteractor$startTileIconThemeColorStateCollection$iconColorState$1(sepQSTileResInteractor, null)), null))), new SepQSTileResInteractor$startTileIconThemeColorStateCollection$4(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public static final Object access$startTileThemeBackgroundCollection(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(sepQSTileResInteractor.isGlobalThemeApplied, new SepQSTileResInteractor$startTileThemeBackgroundCollection$$inlined$flatMapLatest$1(sepQSTileResInteractor, null))), new SepQSTileResInteractor$startTileThemeBackgroundCollection$3(2, null), continuation);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final Flow getSuperPowerBackground(boolean z) {
        return FlowKt.combine(this.activeColorFlow, this.isLowGaussianLevel, this.isNightMode, new getSuperPowerBackground.1(this, (Continuation) null, z));
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }

    public final Object startHighLightTileBackgroundCollection(boolean z, SuspendLambda suspendLambda) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(this.isLowGaussianLevel, this.superPowerSaveMode, new AnonymousClass2(null, z)), new SepQSTileResInteractor$startHighLightTileBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new AnonymousClass4(z, null), suspendLambda);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final Object startHighlightIconBgCollection(boolean z, SuspendLambda suspendLambda) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(BaseQSResPool.INSTANCE.isMixColorSupport(), this.colorfulIconAvailable, new C02672(null, z)), new SepQSTileResInteractor$startHighlightIconBgCollection$$inlined$flatMapLatest$1(3, null))), new C02684(z, null), suspendLambda);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    public final Object startTileBackgroundCollection(boolean z, SuspendLambda suspendLambda) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(this.isLowGaussianLevel, this.superPowerSaveMode, new C02692(null, z)), new SepQSTileResInteractor$startTileBackgroundCollection$$inlined$flatMapLatest$1(3, null))), new C02704(z, null), suspendLambda);
        return objCollectLatest == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }
}
