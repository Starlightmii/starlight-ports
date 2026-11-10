package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.Context;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SystemSettings;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WellBeingAssistantRepositoryImpl implements WellBeingAssistantRepository {
    public static final Companion Companion = new Companion();
    private static final String PACKAGE_NAME_WELL_BEING_ASSISTANT = "com.realme.wellbeing";
    private static final String TAG = "WellBeingAssistantRepositoryImpl";
    public static boolean isWellBeingAssistantState;
    private final Lazy isWellBeingPkgExist$delegate;
    private final String settingKey;
    private final SystemSettings systemSetting;
    private final StateFlow wellBeingAssistantMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public WellBeingAssistantRepositoryImpl(Context context, SelectedUserInteractor selectedUserInteractor, CoroutineContext coroutineContext, CoroutineScope coroutineScope, SystemSettings systemSettings) {
        this.systemSetting = systemSettings;
        WellBeingAssistantRepositoryImpl$$ExternalSyntheticLambda0 wellBeingAssistantRepositoryImpl$$ExternalSyntheticLambda0 = new WellBeingAssistantRepositoryImpl$$ExternalSyntheticLambda0();
        wellBeingAssistantRepositoryImpl$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.isWellBeingPkgExist$delegate = LazyKt__LazyJVMKt.lazy(wellBeingAssistantRepositoryImpl$$ExternalSyntheticLambda0);
        this.settingKey = CommonSettingsValueProxy.KEY_WELL_BEING_ASSISTANT_MODE_STATE;
        this.wellBeingAssistantMode = FlowKt.stateIn(FlowKt.flowOn(FlowKt.onEach(FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new WellBeingAssistantRepositoryImpl$special$$inlined$flatMapLatest$1(null, this)), new WellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2(2, null)), coroutineContext), coroutineScope, SharingStarted.Companion.getEagerly(), Boolean.FALSE);
    }

    public static final boolean access$isWellBeingAssistantState(WellBeingAssistantRepositoryImpl wellBeingAssistantRepositoryImpl, int i) {
        return wellBeingAssistantRepositoryImpl.isSupportWellBeingAssistant() && wellBeingAssistantRepositoryImpl.systemSetting.getIntForUser(wellBeingAssistantRepositoryImpl.settingKey, 0, i) == 1;
    }

    public static /* synthetic */ void getWellBeingAssistantMode$annotations() {
    }

    public /* bridge */ /* synthetic */ boolean getInWellBeingAssistantMode() {
        return super.getInWellBeingAssistantMode();
    }

    public StateFlow getWellBeingAssistantMode() {
        return this.wellBeingAssistantMode;
    }

    public final boolean isSupportWellBeingAssistant() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        return FlavorOneFeatureOption.isFlavorOneDevice() && ((Boolean) this.isWellBeingPkgExist$delegate.getValue()).booleanValue();
    }
}
