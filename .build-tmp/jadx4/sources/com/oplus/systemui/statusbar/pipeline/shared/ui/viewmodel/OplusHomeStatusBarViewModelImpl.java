package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import android.content.Context;
import android.graphics.RectF;
import com.android.systemui.log.table.DiffableExtensionsKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.events.domain.interactor.SystemStatusEventAnimationInteractor;
import com.android.systemui.statusbar.layout.ui.viewmodel.AppHandlesViewModel;
import com.android.systemui.statusbar.layout.ui.viewmodel.StatusBarBoundsViewModel;
import com.android.systemui.statusbar.phone.domain.interactor.IsAreaDark;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.battery.ui.viewmodel.BatteryViewModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractor;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.HomeStatusBarInteractor;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.StatusBarVisibilityInteractor;
import com.android.systemui.statusbar.pipeline.shared.domain.model.StatusBarDisableFlagsVisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.model.ChipsVisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.model.VisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.HomeStatusBarViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.StatusBarOperatorNameViewModel;
import com.android.systemui.statusbar.policy.ShouldShowForDisplay;
import com.android.systemui.statusbar.quickactions.shared.model.QuickActionChipModel;
import com.android.systemui.statusbar.systemstatusicons.domain.interactor.SystemStatusIconBlocklistInteractor;
import com.android.systemui.statusbar.systemstatusicons.ui.viewmodel.SystemStatusIconsViewModel;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.statusbar.clock.ClockSecondsController;
import com.oplus.systemui.statusbar.phone.fragment.CollapsedStatusBarViewModelEx;
import com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.StatClockSeedlingInteractor;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHomeStatusBarViewModelImpl implements OplusHomeStatusBarViewModel, HomeStatusBarViewModel {
    public static final Companion Companion = new Companion();
    private static final String KEY_CARRIER_INFO_SWITCH_SETTINGS = "carrier_info_settings_show";
    private static final String TAG = "OplusHomeStatusBarViewModel";
    private final StateFlow animationState;
    private final ClockSecondsController clockSecondsController;
    private final Context context;
    private final StateFlow displaySlotSettings;
    private final MutableStateFlow hasFullscreenPrivacyIcon;
    private final Flow hideSecBySeeding;
    private final HomeStatusBarViewModel homeStatusBarViewModel;
    private final Flow isClockVisibleWithSeedling;
    private final Flow isDynamicIconVisible;
    private final Flow isNotificationIconContainerVisible;
    private final Flow isPromptViewVisible;
    private final Flow isSeedlingContainerVisible;
    private final MutableStateFlow minutesArea;
    private final MobileConnectionsRepository mobileConnectionsRepository;
    private final MobileIconsInteractor mobileIconsInteractor;
    private final StateFlow operatorNameForDisplaySlot;
    private final OplusStatusBarOperatorNameViewModel oplusOperatorNameViewModel;
    private final Flow oplusShouldShowOperatorName;
    private final MutableStateFlow secArea;
    private final SecureSettings secureSettings;
    private final Flow seedlingIntersectsMinutesArea;
    private final OplusSeparateNotificationAndQSEx separateNotificationAndQSEx;
    private final ShouldShowForDisplay shouldShowForDisplay;
    private final Flow shouldShowOperatorNameView;
    private final StatClockSeedlingInteractor statClockSeedlingInteractor;
    private final Flow statusBarClockShowSeconds;
    private final Lazy statusBarStateController;
    private final CollapsedStatusBarViewModelEx statusBarViewModelEx;
    private final StateFlow systemInfoCombinedVis;
    private final TableLogBuffer tableLogger;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OplusHomeStatusBarViewModelFactoryImpl extends OplusHomeStatusBarViewModel.OplusHomeStatusBarViewModelFactory {
        @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel.OplusHomeStatusBarViewModelFactory
        /* synthetic */ OplusHomeStatusBarViewModel create(HomeStatusBarViewModel homeStatusBarViewModel);

        @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel.OplusHomeStatusBarViewModelFactory
        OplusHomeStatusBarViewModelImpl create(HomeStatusBarViewModel homeStatusBarViewModel);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModelImpl$isClockVisibleWithSeedling$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
            anonymousClass1.L$0 = (VisibilityModel) obj;
            anonymousClass1.Z$0 = zBooleanValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            VisibilityModel visibilityModel = (VisibilityModel) this.L$0;
            boolean z = this.Z$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return new VisibilityModel((visibilityModel.getVisibility() != 0 || z) ? 4 : 0, visibilityModel.getShouldAnimateChange());
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModelImpl$isNotificationIconContainerVisible$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03041 extends SuspendLambda implements Function3 {
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;

        public C03041(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            C03041 c03041 = OplusHomeStatusBarViewModelImpl.this.new C03041((Continuation) obj3);
            c03041.L$0 = (VisibilityModel) obj;
            c03041.Z$0 = zBooleanValue;
            return c03041.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            VisibilityModel visibilityModel = (VisibilityModel) this.L$0;
            boolean z = this.Z$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl = OplusHomeStatusBarViewModelImpl.this;
            boolean z2 = visibilityModel.getVisibility() == 0 && !z;
            oplusHomeStatusBarViewModelImpl.getClass();
            return visibilityModel.copy(z2 ? 0 : 8, visibilityModel.getShouldAnimateChange());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModelImpl$isPromptViewVisible$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03051 extends SuspendLambda implements Function5 {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        public C03051(Continuation continuation) {
            super(5, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
            C03051 c03051 = OplusHomeStatusBarViewModelImpl.this.new C03051((Continuation) obj5);
            c03051.L$0 = (VisibilityModel) obj;
            c03051.Z$0 = zBooleanValue;
            c03051.Z$1 = zBooleanValue2;
            c03051.L$1 = (StatusBarDisableFlagsVisibilityModel) obj4;
            return c03051.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            VisibilityModel visibilityModel = (VisibilityModel) this.L$0;
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            StatusBarDisableFlagsVisibilityModel statusBarDisableFlagsVisibilityModel = (StatusBarDisableFlagsVisibilityModel) this.L$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl = OplusHomeStatusBarViewModelImpl.this;
            boolean z3 = visibilityModel.getVisibility() == 0 && z && !z2;
            oplusHomeStatusBarViewModelImpl.getClass();
            return new VisibilityModel(z3 ? 0 : 8, statusBarDisableFlagsVisibilityModel.getAnimate());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModelImpl$isSeedlingContainerVisible$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03061 extends SuspendLambda implements Function6 {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        /* synthetic */ boolean Z$2;
        int label;

        public C03061(Continuation continuation) {
            super(6, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
            boolean zBooleanValue3 = ((Boolean) obj5).booleanValue();
            C03061 c03061 = OplusHomeStatusBarViewModelImpl.this.new C03061((Continuation) obj6);
            c03061.L$0 = (VisibilityModel) obj;
            c03061.Z$0 = zBooleanValue;
            c03061.Z$1 = zBooleanValue2;
            c03061.L$1 = (StatusBarDisableFlagsVisibilityModel) obj4;
            c03061.Z$2 = zBooleanValue3;
            return c03061.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            VisibilityModel visibilityModel = (VisibilityModel) this.L$0;
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            StatusBarDisableFlagsVisibilityModel statusBarDisableFlagsVisibilityModel = (StatusBarDisableFlagsVisibilityModel) this.L$1;
            boolean z3 = this.Z$2;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) OplusHomeStatusBarViewModelImpl.this.statusBarStateController.get();
            boolean z4 = ((SeedlingPluginManager.Companion.isSupportCapsuleFake(OplusHomeStatusBarViewModelImpl.this.context) && (statusBarStateControllerImpl != null && statusBarStateControllerImpl.mIsExpanded)) || z3) ? false : true;
            OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl = OplusHomeStatusBarViewModelImpl.this;
            boolean z5 = visibilityModel.getVisibility() == 0 && z && !z2 && !z3;
            oplusHomeStatusBarViewModelImpl.getClass();
            return new VisibilityModel(z5 ? 0 : 4, statusBarDisableFlagsVisibilityModel.getAnimate() && z4);
        }
    }

    public OplusHomeStatusBarViewModelImpl(HomeStatusBarViewModel homeStatusBarViewModel, Context context, int i, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, HomeStatusBarInteractor homeStatusBarInteractor, StatusBarVisibilityInteractor statusBarVisibilityInteractor, AirplaneModeInteractor airplaneModeInteractor, MobileIconsInteractor mobileIconsInteractor, MobileConnectionsRepository mobileConnectionsRepository, TableLogBufferFactory tableLogBufferFactory, SystemStatusEventAnimationInteractor systemStatusEventAnimationInteractor, CollapsedStatusBarViewModelEx collapsedStatusBarViewModelEx, Lazy lazy, OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor, ShouldShowForDisplay shouldShowForDisplay, StatClockSeedlingInteractor statClockSeedlingInteractor, ClockSecondsController clockSecondsController) {
        Continuation continuation;
        int i2;
        Flow flowDistinctUntilChanged;
        Flow flowFlowOf;
        this.homeStatusBarViewModel = homeStatusBarViewModel;
        this.context = context;
        this.mobileIconsInteractor = mobileIconsInteractor;
        this.mobileConnectionsRepository = mobileConnectionsRepository;
        this.statusBarViewModelEx = collapsedStatusBarViewModelEx;
        this.statusBarStateController = lazy;
        this.separateNotificationAndQSEx = oplusSeparateNotificationAndQSEx;
        this.secureSettings = secureSettings;
        this.shouldShowForDisplay = shouldShowForDisplay;
        this.statClockSeedlingInteractor = statClockSeedlingInteractor;
        this.clockSecondsController = clockSecondsController;
        TableLogBuffer orCreate = tableLogBufferFactory.getOrCreate("HomeStatusBarViewModelTableLog[" + i + "]", ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION);
        this.tableLogger = orCreate;
        Flow flowFlowOf2 = !CustomizeFeatureOption.sIsShowOperatorName ? FlowKt.flowOf(-1) : FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new OplusHomeStatusBarViewModelImpl$special$.inlined.flatMapLatest.1(this, (Continuation) null));
        CoroutineScope settingsScope = secureSettings.getSettingsScope();
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(flowFlowOf2, settingsScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.displaySlotSettings = stateFlowStateIn;
        StateFlow stateFlowStateIn2 = FlowKt.stateIn(FlowKt.flowOn(FlowKt.transformLatest(stateFlowStateIn, new OplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2(this, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.operatorNameForDisplaySlot = stateFlowStateIn2;
        this.oplusOperatorNameViewModel = new OplusStatusBarOperatorNameViewModel(stateFlowStateIn2, mobileIconsInteractor);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow((Object) null);
        this.minutesArea = MutableStateFlow;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow((Object) null);
        this.secArea = MutableStateFlow2;
        this.hideSecBySeeding = !shouldShowForDisplay.getShowSeedling() ? FlowKt.flowOf(Boolean.FALSE) : FlowKt.distinctUntilChanged(FlowKt.combine(statClockSeedlingInteractor.getSeedlingState(), MutableStateFlow2, new hideSecBySeeding.1(this, (Continuation) null)));
        Flow flowFlowOf3 = !shouldShowForDisplay.getShowSeedling() ? FlowKt.flowOf(Boolean.FALSE) : FlowKt.distinctUntilChanged(FlowKt.combine(statClockSeedlingInteractor.getSeedlingState(), MutableStateFlow, new seedlingIntersectsMinutesArea.1(this, (Continuation) null)));
        this.seedlingIntersectsMinutesArea = flowFlowOf3;
        this.statusBarClockShowSeconds = clockSecondsController.getShowSeconds();
        if (shouldShowForDisplay.getShowSeedling()) {
            continuation = null;
            i2 = 3;
            flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(isClockVisible(), flowFlowOf3, new AnonymousClass1(3, null)));
        } else {
            flowDistinctUntilChanged = isClockVisible();
            continuation = null;
            i2 = 3;
        }
        this.isClockVisibleWithSeedling = FlowKt.flowOn(flowDistinctUntilChanged, coroutineDispatcher);
        Flow flowCombine = FlowKt.combine(airplaneModeInteractor.isAirplaneMode(), stateFlowStateIn, new OplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1(i2, continuation));
        this.oplusShouldShowOperatorName = flowCombine;
        this.shouldShowOperatorNameView = FlowKt.flowOn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.combine(statusBarVisibilityInteractor.getShouldHomeStatusBarBeVisible(), homeStatusBarInteractor.getVisibilityViaDisableFlags(), flowCombine, collapsedStatusBarViewModelEx.getMiniCapsuleShowing(), new OplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1(5, continuation))), orCreate, "", "oplus_showOperatorName", false), coroutineDispatcher);
        this.isNotificationIconContainerVisible = FlowKt.flowOn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(!shouldShowForDisplay.getShowNotify() ? FlowKt.flowOf(new VisibilityModel(8, false)) : FlowKt.combine(homeStatusBarViewModel.isNotificationIconContainerVisible(), collapsedStatusBarViewModelEx.getMiniCapsuleShowing(), new C03041(null))), orCreate, "oplus_notifContainer", new VisibilityModel(4, false)), coroutineDispatcher);
        StateFlow systemInfoCombinedVis = homeStatusBarViewModel.getSystemInfoCombinedVis();
        OplusHomeStatusBarViewModelImpl$special$$inlined$map$1 oplusHomeStatusBarViewModelImpl$special$$inlined$map$1 = new OplusHomeStatusBarViewModelImpl$special$$inlined$map$1(0);
        oplusHomeStatusBarViewModelImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = systemInfoCombinedVis;
        VarHandle.storeStoreFence();
        this.systemInfoCombinedVis = FlowKt.stateIn(FlowKt.distinctUntilChanged(oplusHomeStatusBarViewModelImpl$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), homeStatusBarViewModel.getSystemInfoCombinedVis().getValue());
        this.isPromptViewVisible = FlowKt.flowOn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(!shouldShowForDisplay.getShowPrompt() ? FlowKt.flowOf(new VisibilityModel(8, false)) : FlowKt.combine(isNotificationIconContainerVisible(), collapsedStatusBarViewModelEx.getShouldShowPromptView(), collapsedStatusBarViewModelEx.getMiniCapsuleShowing(), homeStatusBarInteractor.getVisibilityViaDisableFlags(), new C03051(null))), orCreate, "promptView", new VisibilityModel(8, false)), coroutineDispatcher);
        Boolean bool = Boolean.FALSE;
        this.hasFullscreenPrivacyIcon = StateFlowKt.MutableStateFlow(bool);
        this.isSeedlingContainerVisible = FlowKt.flowOn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(!shouldShowForDisplay.getShowSeedling() ? FlowKt.flowOf(new VisibilityModel(4, false)) : FlowKt.combine(isNotificationIconContainerVisible(), statusBarVisibilityInteractor.getCanShowOngoingActivityChips(), collapsedStatusBarViewModelEx.getMiniCapsuleShowing(), homeStatusBarInteractor.getVisibilityViaDisableFlags(), getHasFullscreenPrivacyIcon(), new C03061(null))), orCreate, "seedlingContainer", new VisibilityModel(4, false)), coroutineDispatcher);
        if (shouldShowForDisplay.getShowDynamic()) {
            StateFlow systemInfoCombinedVis2 = getSystemInfoCombinedVis();
            OplusHomeStatusBarViewModelImpl$special$$inlined$map$1 oplusHomeStatusBarViewModelImpl$special$$inlined$map$2 = new OplusHomeStatusBarViewModelImpl$special$$inlined$map$1(1);
            oplusHomeStatusBarViewModelImpl$special$$inlined$map$2.$this_unsafeTransform$inlined = systemInfoCombinedVis2;
            VarHandle.storeStoreFence();
            flowFlowOf = oplusHomeStatusBarViewModelImpl$special$$inlined$map$2;
        } else {
            flowFlowOf = FlowKt.flowOf(bool);
        }
        this.isDynamicIconVisible = FlowKt.flowOn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(flowFlowOf), orCreate, "", "dynamicIcon", false), coroutineDispatcher);
        this.animationState = systemStatusEventAnimationInteractor.getAnimationState();
    }

    public static final boolean access$intersectsSeedling(OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl, List list, Pair pair) {
        oplusHomeStatusBarViewModelImpl.getClass();
        return (pair == null || list.isEmpty() || !HiddenIconDispatcher.Companion.isInAreas(new ArrayList<>(list), pair)) ? false : true;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Object activate(Continuation continuation) {
        return this.homeStatusBarViewModel.activate(continuation);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StateFlow getAnimationState() {
        return this.animationState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public AppHandlesViewModel.Factory getAppHandlesViewModelFactory() {
        return this.homeStatusBarViewModel.getAppHandlesViewModelFactory();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getAreNotificationsLightsOut() {
        return this.homeStatusBarViewModel.getAreNotificationsLightsOut();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public IsAreaDark getAreaDark() {
        return this.homeStatusBarViewModel.getAreaDark();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getAreaTint() {
        return this.homeStatusBarViewModel.getAreaTint();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getContentArea() {
        return this.homeStatusBarViewModel.getContentArea();
    }

    public final StateFlow getDisplaySlotSettings() {
        return this.displaySlotSettings;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public MutableStateFlow getHasFullscreenPrivacyIcon() {
        return this.hasFullscreenPrivacyIcon;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public boolean getHasStatusBarNotifications() {
        return this.homeStatusBarViewModel.getHasStatusBarNotifications();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getHideSecBySeeding() {
        return this.hideSecBySeeding;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getIconBlockList() {
        return this.homeStatusBarViewModel.getIconBlockList();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StateFlow getMediaProjectionStopDialogDueToCallEndedState() {
        return this.homeStatusBarViewModel.getMediaProjectionStopDialogDueToCallEndedState();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public ChipsVisibilityModel getOngoingActivityChips() {
        return this.homeStatusBarViewModel.getOngoingActivityChips();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StatusBarOperatorNameViewModel getOperatorNameViewModel() {
        return this.oplusOperatorNameViewModel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public List<QuickActionChipModel> getPopupChips() {
        return this.homeStatusBarViewModel.getPopupChips();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getShouldShowOperatorNameView() {
        return this.shouldShowOperatorNameView;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StatusBarBoundsViewModel.Factory getStatusBarBoundsViewModelFactory() {
        return this.homeStatusBarViewModel.getStatusBarBoundsViewModelFactory();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getStatusBarClockShowSeconds() {
        return this.statusBarClockShowSeconds;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StateFlow getSystemInfoCombinedVis() {
        return this.systemInfoCombinedVis;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public SystemStatusIconBlocklistInteractor getSystemStatusIconBlockListInteractor() {
        return this.homeStatusBarViewModel.getSystemStatusIconBlockListInteractor();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public SystemStatusIconsViewModel.Factory getSystemStatusIconsViewModelFactory() {
        return this.homeStatusBarViewModel.getSystemStatusIconsViewModelFactory();
    }

    public final TableLogBuffer getTableLogger() {
        return this.tableLogger;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow getTransitionFromLockscreenToDreamStartedEvent() {
        return this.homeStatusBarViewModel.getTransitionFromLockscreenToDreamStartedEvent();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public BatteryViewModel.BasedOnUserSetting.Factory getUnifiedBatteryViewModel() {
        return this.homeStatusBarViewModel.getUnifiedBatteryViewModel();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public boolean getUseDesktopStatusBar() {
        return this.homeStatusBarViewModel.getUseDesktopStatusBar();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isClockVisible() {
        return this.homeStatusBarViewModel.isClockVisible();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isClockVisibleWithSeedling() {
        return this.isClockVisibleWithSeedling;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isDynamicIconVisible() {
        return this.isDynamicIconVisible;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StateFlow isHomeStatusBarAllowed() {
        return this.homeStatusBarViewModel.isHomeStatusBarAllowed();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isNotificationIconContainerVisible() {
        return this.isNotificationIconContainerVisible;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isPromptViewVisible() {
        return this.isPromptViewVisible;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public Flow isSeedlingContainerVisible() {
        return this.isSeedlingContainerVisible;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public StateFlow isTransitioningFromLockscreenToOccluded() {
        return this.homeStatusBarViewModel.isTransitioningFromLockscreenToOccluded();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public void notifyClockLayoutAreasChanged(Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2) {
        if (!Intrinsics.areEqual(this.minutesArea.getValue(), pair)) {
            this.minutesArea.setValue(pair);
        }
        if (Intrinsics.areEqual(this.secArea.getValue(), pair2)) {
            return;
        }
        this.secArea.setValue(pair2);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel
    public void onChipBoundsChanged(String str, RectF rectF) {
        this.homeStatusBarViewModel.onChipBoundsChanged(str, rectF);
    }
}
