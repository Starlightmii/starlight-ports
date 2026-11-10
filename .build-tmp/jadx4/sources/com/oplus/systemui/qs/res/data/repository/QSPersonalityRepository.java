package com.oplus.systemui.qs.res.data.repository;

import android.content.Context;
import android.graphics.Path;
import android.util.PathParser;
import android.util.SparseArray;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.coui.appcompat.COUIMaterialStateManager;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPersonalityRepository {
    public static final Companion Companion = new Companion();
    private static final String STROKE_ENABLE_KEY = "system_material_stroke_enable";
    private static final String TAG = "QSPersonalityRepository";
    private final Context context;
    private final StateFlow couiSpotLightEnabled;
    private final ShapeConfig defaultConfig;
    private final ShapeConfig defaultMediaConfig;
    private final Flow detailPanelRadius;
    private final StateFlow featureSpotLightConfig;
    private final StateFlow isHighlightDefaultShape;
    private final Flow isOneHandedEnable;
    private final StateFlow isSupportStrokeCorner;
    private final SecureSettings secureSettings;
    private final Flow sepMediaPanelRadiusRes;
    private final Flow sepSupportSpotLight;
    private final StateFlow sepSupportStrokeCorner;
    private final String settingsKey = CommonSettingsValueProxy.KEY_QS_PERSONALITY_SHAPE_TYPE;
    private final SparseArray<ShapeConfig> shapeConfigs;
    private final StateFlow shapeType;
    private final Flow stdMediaTileShapeConfig;
    private final Flow stdSupportSpotLight;
    private final StateFlow stdSupportStrokeCorner;
    private final Flow stdTileShapeConfig;
    private final SystemSettings systemSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Path getPath(Context context, int i) {
            return PathParser.createPathFromPathData(context.getString(i));
        }
    }

    public QSPersonalityRepository(Context context, SystemSettings systemSettings, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor) {
        this.context = context;
        this.systemSettings = systemSettings;
        this.secureSettings = secureSettings;
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new QSPersonalityRepository$couiSpotLightEnabled$1(this, null));
        QSPersonalityRepository$special$$inlined$map$1 qSPersonalityRepository$special$$inlined$map$1 = new QSPersonalityRepository$special$$inlined$map$1();
        qSPersonalityRepository$special$$inlined$map$1.$this_unsafeTransform$inlined = flowConflatedCallbackFlow;
        qSPersonalityRepository$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        CoroutineScope settingsScope = systemSettings.getSettingsScope();
        SharingStarted.Companion companion = SharingStarted.Companion;
        SharingStarted eagerly = companion.getEagerly();
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        cOUIMaterialStateManager.getClass();
        this.couiSpotLightEnabled = FlowKt.stateIn(qSPersonalityRepository$special$$inlined$map$1, settingsScope, eagerly, Boolean.valueOf(!COUIMaterialStateManager.materialVersionCheck() ? false : cOUIMaterialStateManager.getMATERIAL_SPOTLIGHT_ENABLE$coui_support_effect_release()));
        Companion companion2 = Companion;
        ShapeConfig shapeConfig = new ShapeConfig(companion2.getPath(context, 2131955853), 2131171938, true, 32);
        this.defaultConfig = shapeConfig;
        this.defaultMediaConfig = shapeConfig.copy(shapeConfig.path, 2131171936, shapeConfig.stdWidth, shapeConfig.stdHeight, shapeConfig.circleShape, shapeConfig.supportSmoothRound);
        SparseArray<ShapeConfig> sparseArray = new SparseArray<>();
        sparseArray.put(0, new ShapeConfig(companion2.getPath(context, 2131955858), 2131171942, false, 16));
        sparseArray.put(1, new ShapeConfig(companion2.getPath(context, 2131955856), 2131171940, false, 16));
        sparseArray.put(2, shapeConfig);
        sparseArray.put(3, new ShapeConfig(companion2.getPath(context, 2131955855), 2131171938, false, 48));
        sparseArray.put(4, new ShapeConfig(companion2.getPath(context, 2131955857), 2131171941, false, 48));
        sparseArray.put(5, new ShapeConfig(companion2.getPath(context, 2131955854), 2131171942, false, 48));
        this.shapeConfigs = sparseArray;
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new QSPersonalityRepository$special$$inlined$flatMapLatest$1(this, null)), systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 2);
        this.shapeType = stateFlowStateIn;
        this.detailPanelRadius = FlowKt.flowOf(2131171633);
        this.sepMediaPanelRadiusRes = FlowKt.flowOf(2131171111);
        this.stdTileShapeConfig = FlowKt.mapLatest(stateFlowStateIn, new QSPersonalityRepository$stdTileShapeConfig$1(this, null));
        QSPersonalityRepository$special$$inlined$map$2 qSPersonalityRepository$special$$inlined$map$2 = new QSPersonalityRepository$special$$inlined$map$2();
        qSPersonalityRepository$special$$inlined$map$2.$this_unsafeTransform$inlined = stateFlowStateIn;
        VarHandle.storeStoreFence();
        StateFlow stateFlowStateIn2 = FlowKt.stateIn(qSPersonalityRepository$special$$inlined$map$2, systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.TRUE);
        this.isHighlightDefaultShape = stateFlowStateIn2;
        this.stdMediaTileShapeConfig = FlowKt.combine(stateFlowStateIn, stateFlowStateIn2, new QSPersonalityRepository$stdMediaTileShapeConfig$1(this, null));
        this.featureSpotLightConfig = FlowKt.asStateFlow(StateFlowKt.MutableStateFlow(Boolean.valueOf(FeatureOption.getEnableLightFeature())));
        Flow flowTransformLatest = FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new QSPersonalityRepository$special$$inlined$flatMapLatest$2(this, null));
        CoroutineScope settingsScope2 = systemSettings.getSettingsScope();
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.isSupportStrokeCorner = FlowKt.stateIn(flowTransformLatest, settingsScope2, sharingStartedWhileSubscribed$default, bool);
        BaseQSResPool baseQSResPool = BaseQSResPool.INSTANCE;
        this.sepSupportStrokeCorner = FlowKt.stateIn(FlowKt.transformLatest(baseQSResPool.isMixColorSupport(), new QSPersonalityRepository$special$$inlined$flatMapLatest$3(this, null)), systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.stdSupportStrokeCorner = FlowKt.stateIn(FlowKt.transformLatest(baseQSResPool.isMixColorSupport(), new QSPersonalityRepository$special$$inlined$flatMapLatest$4(this, null)), systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.stdSupportSpotLight = FlowKt.transformLatest(baseQSResPool.isMixColorSupport(), new QSPersonalityRepository$special$$inlined$flatMapLatest$5(this, null));
        this.sepSupportSpotLight = FlowKt.transformLatest(baseQSResPool.isMixColorSupport(), new QSPersonalityRepository$special$$inlined$flatMapLatest$6(this, null));
        this.isOneHandedEnable = FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new QSPersonalityRepository$special$$inlined$flatMapLatest$7(this, null));
    }

    public static final boolean access$iSupportStrokeSettingEnabled(QSPersonalityRepository qSPersonalityRepository, int i) {
        return qSPersonalityRepository.systemSettings.getIntForUser(STROKE_ENABLE_KEY, 0, i) == 1;
    }

    public static final boolean access$isOneHandedEnabled(QSPersonalityRepository qSPersonalityRepository, int i) {
        return qSPersonalityRepository.secureSettings.getIntForUser("one_handed_mode_activated", 0, i) == 1;
    }

    public final StateFlow getCouiSpotLightEnabled() {
        return this.couiSpotLightEnabled;
    }

    public final ShapeConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public final ShapeConfig getDefaultMediaConfig() {
        return this.defaultMediaConfig;
    }

    public final Flow getDetailPanelRadius() {
        return this.detailPanelRadius;
    }

    public final StateFlow getFeatureSpotLightConfig() {
        return this.featureSpotLightConfig;
    }

    public final Flow getSepMediaPanelRadiusRes() {
        return this.sepMediaPanelRadiusRes;
    }

    public final Flow getSepSupportSpotLight() {
        return this.sepSupportSpotLight;
    }

    public final StateFlow getSepSupportStrokeCorner() {
        return this.sepSupportStrokeCorner;
    }

    public final Flow getStdMediaTileShapeConfig() {
        return this.stdMediaTileShapeConfig;
    }

    public final Flow getStdSupportSpotLight() {
        return this.stdSupportSpotLight;
    }

    public final StateFlow getStdSupportStrokeCorner() {
        return this.stdSupportStrokeCorner;
    }

    public final Flow getStdTileShapeConfig() {
        return this.stdTileShapeConfig;
    }

    public final StateFlow isHighlightDefaultShape() {
        return this.isHighlightDefaultShape;
    }

    public final Flow isOneHandedEnable() {
        return this.isOneHandedEnable;
    }

    public final StateFlow isSupportStrokeCorner() {
        return this.isSupportStrokeCorner;
    }

    public final void resetQSShapeType() {
        this.systemSettings.putIntForUser(this.settingsKey, 2, -2);
    }
}
