package com.oplus.systemui.scenario.host.impl.data.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import com.oplus.systemui.scenario.host.impl.data.util.ScenarioDataTransformUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioAssistantRepositoryImpl implements ScenarioAssistantRepository {
    private static final String AUTHORITY = "com.oplus.exsystemserver.statictis";
    private static final String TAG = "ScenarioAssistantRepositoryImpl";
    private final MutableStateFlow _isMirrorShowing;
    private final CoroutineScope backgroundScope;
    private final Context context;
    private final StateFlow isMirrorShowing;
    private final StateFlow isScenarioSupport;
    private final StateFlow scenarioInfo;
    public static final Companion Companion = new Companion();
    private static final Uri CONTENT_URI = Uri.parse("content://com.oplus.exsystemserver.statictis");
    private static final Uri OBSERVE_CHANGE_URI = Uri.parse("content://com.oplus.exsystemserver.statictis/scene_function_provider");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScenarioAssistantRepositoryImpl(Context context, CoroutineScope coroutineScope) {
        this.context = context;
        this.backgroundScope = coroutineScope;
        FeatureOption.INSTANCE.getClass();
        this.isScenarioSupport = FeatureOption.isSupportScenarioAssistant() ? BaseQSResPool.INSTANCE.getEnableSeparateNotificationAndQS() : FlowKt.asStateFlow(StateFlowKt.MutableStateFlow(Boolean.FALSE));
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new ScenarioAssistantRepositoryImpl$scenarioInfo$1(this, null));
        ScenarioAssistantRepositoryImpl$special$$inlined$map$1 scenarioAssistantRepositoryImpl$special$$inlined$map$1 = new ScenarioAssistantRepositoryImpl$special$$inlined$map$1();
        scenarioAssistantRepositoryImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = flowConflatedCallbackFlow;
        scenarioAssistantRepositoryImpl$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.scenarioInfo = FlowKt.stateIn(scenarioAssistantRepositoryImpl$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 60000L, 2), (Object) null);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isMirrorShowing = MutableStateFlow;
        this.isMirrorShowing = FlowKt.asStateFlow(MutableStateFlow);
    }

    public static final ScenarioInfo access$queryScenarioAssistantInfo(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl) {
        String scenarioStatic;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->queryScenarioAssistantInfo: start");
        }
        ScenarioInfo scenarioInfoTransformCursor = ScenarioDataTransformUtil.INSTANCE.transformCursor(scenarioAssistantRepositoryImpl.context, scenarioAssistantRepositoryImpl.context.getContentResolver().query(CONTENT_URI, null, null, null, null));
        if (scenarioInfoTransformCursor == null || (scenarioStatic = scenarioInfoTransformCursor.getScenarioStatic()) == null) {
            scenarioStatic = "";
        }
        SeparateQSStatisicUtil.updateScenarioType(scenarioStatic);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("queryScenarioAssistantInfo: end and scenarioName = ", scenarioInfoTransformCursor != null ? scenarioInfoTransformCursor.getScenarioName() : null, "ScenarioAssistantRepositoryImpl-->", "SystemUi--QuickSettings");
        }
        return scenarioInfoTransformCursor;
    }

    public static final void access$registerScenarioAssistantObserver(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl, ScenarioAssistantRepositoryImpl$scenarioInfo$1.observer.1 r5) {
        Object obj;
        Unit unit;
        scenarioAssistantRepositoryImpl.getClass();
        try {
            ContentResolver contentResolver = scenarioAssistantRepositoryImpl.context.getContentResolver();
            if (contentResolver != null) {
                contentResolver.registerContentObserver(OBSERVE_CHANGE_URI, false, r5);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            obj = Result.constructor-impl(unit);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->".concat("registerScenarioAssistantObserver: failure, " + th2));
        }
        if (obj instanceof Result.Failure) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->registerScenarioAssistantObserver: success");
        }
    }

    public static final void access$unregisterScenarioAssistantObserver(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl, ContentObserver contentObserver) {
        Object obj;
        Unit unit;
        scenarioAssistantRepositoryImpl.getClass();
        try {
            ContentResolver contentResolver = scenarioAssistantRepositoryImpl.context.getContentResolver();
            if (contentResolver != null) {
                contentResolver.unregisterContentObserver(contentObserver);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            obj = Result.constructor-impl(unit);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->".concat("unregisterScenarioAssistantObserver: failure, " + th2));
        }
        if (obj instanceof Result.Failure) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->unregisterScenarioAssistantObserver: success");
        }
    }

    private static /* synthetic */ void getBackgroundScope$annotations() {
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow getScenarioInfo() {
        return this.scenarioInfo;
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow isMirrorShowing() {
        return this.isMirrorShowing;
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow isScenarioSupport() {
        return this.isScenarioSupport;
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public void setMirrorShowing(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setMirrorShowing: showing=", z), null, 8);
        this._isMirrorShowing.setValue(Boolean.valueOf(z));
    }
}
