package com.oplus.systemui.qs.scenario.domain.viewmodel;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.privacy.dialog.PrivacyGroupElement;
import com.oplus.systemui.privacy.dialog.QSPrivacyDialogElement;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.res.data.repository.QSConfigurationRepository;
import com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository;
import com.oplus.systemui.qs.res.data.repository.QSThemeConfigRepository;
import com.oplus.systemui.qs.res.domain.util.ResourcesUtil;
import com.oplus.systemui.qs.scenario.domain.interactor.PrivacyViewInteractor;
import com.oplus.systemui.qs.scenario.domain.interactor.ScenarioViewInteractor;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyScenarioCapsuleViewModelImpl implements PrivacyScenarioCapsuleViewModel {
    private static final String TAG = "ScenarioCapsuleViewModelImpl";
    private final MutableStateFlow _privacyAppName;
    private final MutableStateFlow _privacyPermGroup;
    private final MutableStateFlow _scenarioName;
    private final MutableStateFlow _scenarioPermGroup;
    private final MutableStateFlow _showSpacer;
    private final StateFlow capsuleState;
    private final QSConfigurationRepository configurationRepository;
    private final Context context;
    public boolean enteredFromPrivacy;
    public boolean exitingToPrivacy;
    private final MutableStateFlow isListening;
    private final StateFlow isTextMarqueeEnable;
    private final GradientTileDrawable.TileTypeConfig normalDrawable;
    private final QSPersonalityRepository personalityRepository;
    private String previousCapsuleState;
    private final StateFlow privacyAppName;
    private final StateFlow privacyElementList;
    private final PrivacyViewInteractor privacyInteractor;
    private final StateFlow privacyPermGroup;
    private final StateFlow qsExpansionState;
    private final StateFlow scenarioCapsuleDrawable;
    private final Flow scenarioIconRes;
    private final Flow scenarioLightAction;
    private final ScenarioLightRecord scenarioLightRecord;
    private final StateFlow scenarioName;
    private final StateFlow scenarioPermGroup;
    private final StateFlow scenarioPreviewInfo;
    private final ScenarioAssistantRepository scenarioRepository;
    private final CoroutineScope scope;
    private final ShadeInteractor shadeInteractor;
    private final StateFlow showCapsule;
    private final StateFlow showPrivacy;
    private final StateFlow showScenario;
    private final StateFlow showSpacer;
    private final SuperPowerSaveRepository superPowerRepository;
    private final Flow textColorRes;
    private final Flow themeChangeFlow;
    private final QSThemeConfigRepository themeConfigRepository;
    private final Lazy viewInteractor;
    public static final Companion Companion = new Companion();
    private static final List<PrivacyType> validPrivacyTypes = CollectionsKt__CollectionsKt.listOf(new PrivacyType[]{PrivacyType.TYPE_CAMERA, PrivacyType.TYPE_MICROPHONE, PrivacyType.TYPE_LOCATION});

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScenarioLightRecord {
        private ScenarioInfo lastPlayedScenarioInfo;
        public long lastPlayedTimeMs;

        public final boolean isSameScenario(ScenarioInfo scenarioInfo) {
            String scenarioType = scenarioInfo.getScenarioType();
            ScenarioInfo scenarioInfo2 = this.lastPlayedScenarioInfo;
            return Intrinsics.areEqual(scenarioType, scenarioInfo2 != null ? scenarioInfo2.getScenarioType() : null);
        }

        public final void markPlayed(ScenarioInfo scenarioInfo) {
            this.lastPlayedScenarioInfo = scenarioInfo;
            this.lastPlayedTimeMs = System.currentTimeMillis();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.scenario.domain.viewmodel.PrivacyScenarioCapsuleViewModelImpl$getBlurBackground$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function4 {
        /* synthetic */ float F$0;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            float fFloatValue = ((Number) obj3).floatValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
            anonymousClass1.Z$0 = zBooleanValue;
            anonymousClass1.Z$1 = zBooleanValue2;
            anonymousClass1.F$0 = fFloatValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            float f = this.F$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return new MixColorTileDrawable.TileTypeConfig(QSBlurConfigProvider.getSepInactiveBlurConfig(z2, z, f), null, 0, 0, null, GradientStrokeLineAdapter.getSepScenarioCapsuleBlurLightConfig(z2, z), 30);
        }
    }

    public PrivacyScenarioCapsuleViewModelImpl(CoroutineScope coroutineScope, Context context, Lazy lazy, PrivacyViewInteractor privacyViewInteractor, ScenarioAssistantRepository scenarioAssistantRepository, ShadeInteractor shadeInteractor, QSThemeConfigRepository qSThemeConfigRepository, QSConfigurationRepository qSConfigurationRepository, SuperPowerSaveRepository superPowerSaveRepository, QSPersonalityRepository qSPersonalityRepository) {
        this.scope = coroutineScope;
        this.context = context;
        this.viewInteractor = lazy;
        this.privacyInteractor = privacyViewInteractor;
        this.scenarioRepository = scenarioAssistantRepository;
        this.shadeInteractor = shadeInteractor;
        this.themeConfigRepository = qSThemeConfigRepository;
        this.configurationRepository = qSConfigurationRepository;
        this.superPowerRepository = superPowerSaveRepository;
        this.personalityRepository = qSPersonalityRepository;
        Flow flowMapLatest = FlowKt.mapLatest(shadeInteractor.getSeparateQsExpansion(), new PrivacyScenarioCapsuleViewModelImpl$qsExpansionState$1(2, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(flowMapLatest, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.qsExpansionState = stateFlowStateIn;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this.isListening = MutableStateFlow;
        StateFlow stateFlowStateIn2 = FlowKt.stateIn(FlowKt.onEach(FlowKt.transformLatest(MutableStateFlow, new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1(this, null)), new privacyElementList.2(this, (Continuation) null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), CollectionsKt__CollectionsKt.emptyList());
        this.privacyElementList = stateFlowStateIn2;
        StateFlow stateFlowStateIn3 = FlowKt.stateIn(FlowKt.onEach(FlowKt.transformLatest(MutableStateFlow, new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$2(this, null)), new PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.scenarioPreviewInfo = stateFlowStateIn3;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList());
        this._scenarioPermGroup = MutableStateFlow2;
        this.scenarioPermGroup = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList());
        this._privacyPermGroup = MutableStateFlow3;
        this.privacyPermGroup = FlowKt.asStateFlow(MutableStateFlow3);
        Flow flowMapLatest2 = FlowKt.mapLatest(stateFlowStateIn2, new PrivacyScenarioCapsuleViewModelImpl$showPrivacy$1(2, null));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        StateFlow stateFlowStateIn4 = FlowKt.stateIn(flowMapLatest2, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this.showPrivacy = stateFlowStateIn4;
        StateFlow stateFlowStateIn5 = FlowKt.stateIn(FlowKt.mapLatest(stateFlowStateIn3, new PrivacyScenarioCapsuleViewModelImpl$showScenario$1(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.showScenario = stateFlowStateIn5;
        this.capsuleState = FlowKt.stateIn(FlowKt.combine(stateFlowStateIn4, stateFlowStateIn5, new PrivacyScenarioCapsuleViewModelImpl$capsuleState$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.showCapsule = FlowKt.stateIn(FlowKt.mapLatest(getCapsuleState(), new PrivacyScenarioCapsuleViewModelImpl$showCapsule$1(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._showSpacer = MutableStateFlow4;
        this.showSpacer = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow MutableStateFlow5 = StateFlowKt.MutableStateFlow((Object) null);
        this._privacyAppName = MutableStateFlow5;
        this.privacyAppName = FlowKt.asStateFlow(MutableStateFlow5);
        PrivacyScenarioCapsuleViewModelImpl$special$$inlined$map$1 privacyScenarioCapsuleViewModelImpl$special$$inlined$map$1 = new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$map$1(1);
        privacyScenarioCapsuleViewModelImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = stateFlowStateIn3;
        VarHandle.storeStoreFence();
        this.scenarioIconRes = privacyScenarioCapsuleViewModelImpl$special$$inlined$map$1;
        MutableStateFlow MutableStateFlow6 = StateFlowKt.MutableStateFlow((Object) null);
        this._scenarioName = MutableStateFlow6;
        this.scenarioName = FlowKt.asStateFlow(MutableStateFlow6);
        this.textColorRes = FlowKt.mapLatest(BaseQSResPool.INSTANCE.isGlobalThemeApplied(), new PrivacyScenarioCapsuleViewModelImpl$textColorRes$1(2, null));
        this.isTextMarqueeEnable = FlowKt.asStateFlow(MutableStateFlow);
        SharedFlow themeChangeEvent = qSThemeConfigRepository.getThemeChangeEvent();
        PrivacyScenarioCapsuleViewModelImpl$special$$inlined$map$1 privacyScenarioCapsuleViewModelImpl$special$$inlined$map$2 = new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$map$1(0);
        privacyScenarioCapsuleViewModelImpl$special$$inlined$map$2.$this_unsafeTransform$inlined = themeChangeEvent;
        VarHandle.storeStoreFence();
        this.themeChangeFlow = FlowKt.onStart(privacyScenarioCapsuleViewModelImpl$special$$inlined$map$2, new PrivacyScenarioCapsuleViewModelImpl$themeChangeFlow$2(2, null));
        GradientTileDrawable.TileTypeConfig tileTypeConfig = new GradientTileDrawable.TileTypeConfig(ResourcesUtil.getQuickEntranceNormalBgColor(context), 0, 0, 0, 0, 62, 0);
        this.normalDrawable = tileTypeConfig;
        this.scenarioCapsuleDrawable = FlowKt.stateIn(FlowKt.mapLatest(FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(qSThemeConfigRepository.isGlobalThemeApplied(), qSThemeConfigRepository.isLowGaussianLevel(), superPowerSaveRepository.getSuperPowerSaveMode(), new PrivacyScenarioCapsuleViewModelImpl$scenarioCapsuleDrawable$1(this, null)), new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$3(3, null))), new PrivacyScenarioCapsuleViewModelImpl$scenarioCapsuleDrawable$3(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), tileTypeConfig.getDefaultTypeBuilder());
        this.scenarioLightAction = FlowKt.combine(getShowCapsule(), getCapsuleState(), stateFlowStateIn3, stateFlowStateIn, new PrivacyScenarioCapsuleViewModelImpl$scenarioLightAction$1(this, null));
        this.scenarioLightRecord = new ScenarioLightRecord();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x005e  */
    /* JADX WARN: Code duplicated, block: B:57:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:59:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:60:0x00d3  */
    public static final String access$buildScenarioLightAction(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, boolean z, String str, ScenarioInfo scenarioInfo, int i) {
        long j;
        long j2;
        String str2;
        boolean z2;
        privacyScenarioCapsuleViewModelImpl.getClass();
        boolean z3 = i == 2;
        boolean z4 = i == 0;
        String str3 = privacyScenarioCapsuleViewModelImpl.previousCapsuleState;
        boolean zIsSameScenario = scenarioInfo != null ? privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.isSameScenario(scenarioInfo) : false;
        String str4 = "hide";
        if (!z || z4) {
            j = 0;
            j2 = 7200000;
            privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = false;
            privacyScenarioCapsuleViewModelImpl.exitingToPrivacy = false;
        } else {
            j2 = 7200000;
            if (Intrinsics.areEqual(str3, "SCENARIO_CAPSULE") && Intrinsics.areEqual(str, "PRIVACY_CAPSULE")) {
                privacyScenarioCapsuleViewModelImpl.exitingToPrivacy = true;
                privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = false;
                str4 = "fade_out_then_hide";
            } else if (privacyScenarioCapsuleViewModelImpl.exitingToPrivacy && Intrinsics.areEqual(str, "PRIVACY_CAPSULE")) {
                j = 0;
                str4 = "noop";
            } else if (Intrinsics.areEqual(str3, "PRIVACY_CAPSULE") && Intrinsics.areEqual(str, "SCENARIO_CAPSULE") && scenarioInfo != null) {
                privacyScenarioCapsuleViewModelImpl.exitingToPrivacy = false;
                if (z3) {
                    privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = false;
                    privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.markPlayed(scenarioInfo);
                    str4 = "fade_in_then_play";
                } else {
                    privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = true;
                    str4 = "fade_in_first_frame";
                }
            } else if (!privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy || scenarioInfo == null) {
                if (scenarioInfo == null) {
                    if (Intrinsics.areEqual(str, "SCENARIO_CAPSULE")) {
                        j = 0;
                        str4 = "noop";
                    } else {
                        privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = false;
                        privacyScenarioCapsuleViewModelImpl.exitingToPrivacy = false;
                    }
                } else if (privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.isSameScenario(scenarioInfo)) {
                    ScenarioLightRecord scenarioLightRecord = privacyScenarioCapsuleViewModelImpl.scenarioLightRecord;
                    if (scenarioLightRecord.lastPlayedTimeMs == 0) {
                        j = 0;
                    } else {
                        j = 0;
                        if (System.currentTimeMillis() - scenarioLightRecord.lastPlayedTimeMs < 7200000) {
                            str4 = "show_final_frame";
                        }
                    }
                    if (z3) {
                        privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.markPlayed(scenarioInfo);
                        str4 = AodMediaStatisticUtil.EVENT_PLAY;
                    } else {
                        str4 = "show_first_frame";
                    }
                } else {
                    j = 0;
                    if (z3) {
                        str4 = "show_first_frame";
                    } else {
                        privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.markPlayed(scenarioInfo);
                        str4 = AodMediaStatisticUtil.EVENT_PLAY;
                    }
                }
            } else if (z3) {
                privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy = false;
                privacyScenarioCapsuleViewModelImpl.scenarioLightRecord.markPlayed(scenarioInfo);
                str4 = AodMediaStatisticUtil.EVENT_PLAY;
            } else {
                j = 0;
                str4 = "noop";
            }
            j = 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            String scenarioName = scenarioInfo != null ? scenarioInfo.getScenarioName() : null;
            boolean z5 = privacyScenarioCapsuleViewModelImpl.enteredFromPrivacy;
            boolean z6 = privacyScenarioCapsuleViewModelImpl.exitingToPrivacy;
            ScenarioLightRecord scenarioLightRecord2 = privacyScenarioCapsuleViewModelImpl.scenarioLightRecord;
            if (scenarioLightRecord2.lastPlayedTimeMs == j) {
                str2 = str4;
            } else {
                str2 = str4;
                if (System.currentTimeMillis() - scenarioLightRecord2.lastPlayedTimeMs < j2) {
                    z2 = false;
                }
                str4 = str2;
                StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("buildScenarioLightAction: action=", str4, ", show=", z, ", capsule=");
                ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str, ", scenario=", scenarioName, ", expanded=");
                KeyguardLogger$.ExternalSyntheticOutline0.m(", collapsed=", ", prev=", sbM, z3, z4);
                DeviceSettingItem$.ExternalSyntheticOutline0.m(str3, ", fromPrivacy=", ", exitingPrivacy=", sbM, z5);
                KeyguardLogger$.ExternalSyntheticOutline0.m(", same=", ", canReplay=", sbM, z6, zIsSameScenario);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("ScenarioCapsuleViewModelImpl-->", "SystemUi--QuickSettings", sbM, z2);
            }
            z2 = true;
            str4 = str2;
            StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m("buildScenarioLightAction: action=", str4, ", show=", z, ", capsule=");
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM2, str, ", scenario=", scenarioName, ", expanded=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", collapsed=", ", prev=", sbM2, z3, z4);
            DeviceSettingItem$.ExternalSyntheticOutline0.m(str3, ", fromPrivacy=", ", exitingPrivacy=", sbM2, z5);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", same=", ", canReplay=", sbM2, z6, zIsSameScenario);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("ScenarioCapsuleViewModelImpl-->", "SystemUi--QuickSettings", sbM2, z2);
        }
        privacyScenarioCapsuleViewModelImpl.previousCapsuleState = str;
        return str4;
    }

    public static final void access$updatePrivacyAppName(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, List list) {
        Object next;
        privacyScenarioCapsuleViewModelImpl.getClass();
        Iterator it = list.iterator();
        String string = null;
        PrivacyType privacyType = null;
        loop0: while (it.hasNext()) {
            QSPrivacyDialogElement qSPrivacyDialogElement = (QSPrivacyDialogElement) it.next();
            for (PrivacyGroupElement privacyGroupElement : qSPrivacyDialogElement.getPermGroupNames()) {
                Iterator<T> it2 = validPrivacyTypes.iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!Intrinsics.areEqual(((PrivacyType) next).getPermGroupName(), privacyGroupElement.getPermGroupName()));
                PrivacyType privacyType2 = (PrivacyType) next;
                if (privacyType2 != null) {
                    if (privacyType == null || privacyType2.compareTo(privacyType) < 0) {
                        string = qSPrivacyDialogElement.getApplicationName().toString();
                        privacyType = privacyType2;
                    }
                    if (privacyType == PrivacyType.TYPE_CAMERA) {
                        break loop0;
                    }
                }
            }
        }
        privacyScenarioCapsuleViewModelImpl._privacyAppName.setValue(string != null ? string : null);
    }

    public static final void access$updatePrivacyPermGroup(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, List list) {
        privacyScenarioCapsuleViewModelImpl.getClass();
        if (list.isEmpty()) {
            return;
        }
        privacyScenarioCapsuleViewModelImpl._privacyPermGroup.setValue(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow getBlurBackground() {
        return FlowKt.combine(this.personalityRepository.getSepSupportStrokeCorner(), this.configurationRepository.isNightMode(), BaseQSResPool.INSTANCE.getMirrorScaleValue(), new AnonymousClass1(4, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow getGlobalThemeBackground() {
        return FlowKt.mapLatest(this.themeChangeFlow, new getGlobalThemeBackground.1(this, (Continuation) null));
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getCapsuleState() {
        return this.capsuleState;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getPrivacyAppName() {
        return this.privacyAppName;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getPrivacyPermGroup() {
        return this.privacyPermGroup;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getScenarioCapsuleDrawable() {
        return this.scenarioCapsuleDrawable;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public Flow getScenarioIconRes() {
        return this.scenarioIconRes;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public Flow getScenarioLightAction() {
        return this.scenarioLightAction;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getScenarioName() {
        return this.scenarioName;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getScenarioPermGroup() {
        return this.scenarioPermGroup;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getShowCapsule() {
        return this.showCapsule;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow getShowSpacer() {
        return this.showSpacer;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public Flow getTextColorRes() {
        return this.textColorRes;
    }

    public final boolean isAnimationEnable() {
        return ((Boolean) this.isListening.getValue()).booleanValue() && ((Number) this.qsExpansionState.getValue()).intValue() != 0;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public StateFlow isTextMarqueeEnable() {
        return this.isTextMarqueeEnable;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public boolean onCapsuleClick(View view) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view, "onCapsuleClick: view="), null, 8);
        SeparateQSStatisicUtil.updateScenarioEntryMode(SeparateQSStatisicUtil.SCENARIO_ENTRY_MODE_CLICK);
        ((ScenarioViewInteractor) this.viewInteractor.get()).showDialog(true, view);
        return true;
    }

    @Override // com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel
    public void setListening(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setListening: listening=", z), null, 8);
        this.isListening.setValue(Boolean.valueOf(z));
    }
}
