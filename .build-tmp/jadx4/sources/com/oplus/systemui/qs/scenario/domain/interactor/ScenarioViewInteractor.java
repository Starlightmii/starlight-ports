package com.oplus.systemui.qs.scenario.domain.interactor;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.plugins.scenario.ScenarioAssistantNavigation;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.scenario.domain.animation.ScenarioDialogAnimationManager;
import com.oplus.systemui.qs.scenario.domain.model.ScenarioAssistantNavigationImpl;
import com.oplus.systemui.qs.scenario.domain.model.ScenarioAssistantNavigationImpl$Factory;
import com.oplus.systemui.qs.scenario.domain.tile.ScenarioDetailTile;
import com.oplus.systemui.qs.scenario.domain.tile.ScenarioDetailTile$ScenarioDetailTileFactory;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileInfo;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder;
import com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioDialogViewBinder;
import com.oplus.systemui.scenario.host.impl.domain.viewmodel.PrivacyScenarioDialogViewModel;
import com.oplus.systemui.scenario.host.impl.domain.viewmodel.PrivacyScenarioDialogViewModel$Factory;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioViewInteractor implements ViewDialogAdapter {
    public static final Companion Companion = new Companion();
    public static final String TAG = "ScenarioViewInteractor";
    public static final String VIEW_TAG = "PrivacyScenarioCapsule";
    private final ScenarioViewInteractor$animationCallback$1 animationCallback;
    private final PrivacyScenarioCapsuleViewModel capsuleViewModel;
    private final ConfigurationController configurationController;
    private ScenarioDialogAnimationManager dialogAnimationManager;
    private final PrivacyScenarioDialogViewModel$Factory dialogViewModelFactory;
    private final ScenarioAssistantNavigationImpl$Factory navigationFactory;
    private final OperationViewInteractor operationViewInteractor;
    private final PrivacyViewInteractor privacyViewInteractor;
    private final ScenarioDetailTile$ScenarioDetailTileFactory scenarioDetailTileFactory;
    private final ScenarioAssistantRepository scenarioRepository;
    private final CoroutineScope scope;
    private final Lazy separateQSManager;
    private List<ScenarioDetailTile> tiles;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private WeakReference<ScenarioAssistantNavigation> navigationRef = new WeakReference<>(null);
    private WeakReference<View> expandableViewRef = new WeakReference<>(null);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScenarioViewInteractor(CoroutineScope coroutineScope, ConfigurationController configurationController, PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel, PrivacyScenarioDialogViewModel$Factory privacyScenarioDialogViewModel$Factory, SysUIViewDialogInteractor sysUIViewDialogInteractor, OperationViewInteractor operationViewInteractor, PrivacyViewInteractor privacyViewInteractor, ScenarioAssistantNavigationImpl$Factory scenarioAssistantNavigationImpl$Factory, ScenarioAssistantRepository scenarioAssistantRepository, Lazy lazy, ScenarioDetailTile$ScenarioDetailTileFactory scenarioDetailTile$ScenarioDetailTileFactory) {
        this.scope = coroutineScope;
        this.configurationController = configurationController;
        this.capsuleViewModel = privacyScenarioCapsuleViewModel;
        this.dialogViewModelFactory = privacyScenarioDialogViewModel$Factory;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.operationViewInteractor = operationViewInteractor;
        this.privacyViewInteractor = privacyViewInteractor;
        this.navigationFactory = scenarioAssistantNavigationImpl$Factory;
        this.scenarioRepository = scenarioAssistantRepository;
        this.separateQSManager = lazy;
        this.scenarioDetailTileFactory = scenarioDetailTile$ScenarioDetailTileFactory;
        ScenarioViewInteractor$animationCallback$1 scenarioViewInteractor$animationCallback$1 = new ScenarioViewInteractor$animationCallback$1();
        scenarioViewInteractor$animationCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationCallback = scenarioViewInteractor$animationCallback$1;
    }

    public final void bindView(ViewGroup viewGroup) {
        View viewBind = ScenarioCapsuleViewBinder.INSTANCE.bind(viewGroup, this.capsuleViewModel);
        viewBind.setTag(getViewTag());
        setExpandableView(viewBind);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public ViewDialogAnimationManager createAnimationManager(View view) {
        ScenarioDialogAnimationManager scenarioDialogAnimationManager = new ScenarioDialogAnimationManager(view, (OplusSeparateQSManager) this.separateQSManager.get(), this.configurationController);
        this.dialogAnimationManager = scenarioDialogAnimationManager;
        return scenarioDialogAnimationManager;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        ArrayList arrayList;
        List<ScenarioTileInfo> scenarioTiles;
        ScenarioInfo scenarioInfo = (ScenarioInfo) this.scenarioRepository.getScenarioInfo().getValue();
        PrivacyScenarioDialogViewModel privacyScenarioDialogViewModelCreate = this.dialogViewModelFactory.create(scenarioInfo);
        ScenarioAssistantNavigation scenarioAssistantNavigationCreate = this.navigationFactory.create(privacyScenarioDialogViewModelCreate);
        this.navigationRef = new WeakReference<>(scenarioAssistantNavigationCreate);
        if (scenarioInfo == null || (scenarioTiles = scenarioInfo.getScenarioTiles()) == null) {
            arrayList = null;
        } else {
            List<ScenarioTileInfo> list = scenarioTiles;
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ScenarioDetailTile scenarioDetailTileCreate = this.scenarioDetailTileFactory.create((ScenarioTileInfo) it.next(), scenarioAssistantNavigationCreate, privacyScenarioDialogViewModelCreate);
                scenarioDetailTileCreate.initialize$1();
                arrayList.add(scenarioDetailTileCreate);
            }
        }
        this.tiles = arrayList;
        return ScenarioDialogViewBinder.INSTANCE.bind(context, scenarioAssistantNavigationCreate, privacyScenarioDialogViewModelCreate, this.operationViewInteractor.createDialogView(context, this.tiles), this.privacyViewInteractor.createSepQSDialogView(context, scenarioAssistantNavigationCreate));
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getContentViewId() {
        return ScenarioDialogViewBinder.INSTANCE.getContentViewId();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View getExpandableView() {
        return this.expandableViewRef.get();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return VIEW_TAG;
    }

    public final boolean hasScenario() {
        return this.scenarioRepository.getScenarioInfo().getValue() != null;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final boolean onBackAction() {
        ScenarioAssistantNavigationImpl scenarioAssistantNavigationImpl = (ScenarioAssistantNavigation) this.navigationRef.get();
        if (scenarioAssistantNavigationImpl != null) {
            return scenarioAssistantNavigationImpl.navigateBack();
        }
        return false;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        SeparateQSStatisicUtil separateQSStatisicUtil = SeparateQSStatisicUtil.INSTANCE;
        separateQSStatisicUtil.getClass();
        if (!SeparateQSStatisicUtil.isSceneTileClick) {
            OplusUiOffloadThread.Companion.getInstance().runOnSubThread(dismissStatisic.1.INSTANCE);
        }
        separateQSStatisicUtil.setSceneTileClick(false);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioViewInteractor-->onDismiss: ");
        }
        this.capsuleViewModel.setListening(true);
        ScenarioAssistantNavigation scenarioAssistantNavigation = this.navigationRef.get();
        if (scenarioAssistantNavigation != null) {
            scenarioAssistantNavigation.setMirrorShowing(false);
        }
        this.navigationRef = new WeakReference<>(null);
        this.dialogAnimationManager = null;
        List<ScenarioDetailTile> list = this.tiles;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((ScenarioDetailTile) it.next()).destroy();
            }
        }
        this.tiles = null;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onShow() {
        this.capsuleViewModel.setListening(false);
    }

    public final void registerScenarioAnimationCallback() {
        ((OplusSeparateQSManager) this.separateQSManager.get()).addScenarioAnimatorCallback(this.animationCallback);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void setExpandableView(View view) {
        this.expandableViewRef = new WeakReference<>(view);
    }

    public final void showDialog(boolean z, View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Object value = this.capsuleViewModel.getCapsuleState().getValue();
            ScenarioInfo scenarioInfo = (ScenarioInfo) this.scenarioRepository.getScenarioInfo().getValue();
            String scenarioName = scenarioInfo != null ? scenarioInfo.getScenarioName() : null;
            StringBuilder sb = new StringBuilder("showDialog: fromClick = ");
            sb.append(z);
            sb.append(", capsuleState = ");
            sb.append(value);
            sb.append(", scenarioName = ");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(scenarioName, "ScenarioViewInteractor-->", "SystemUi--QuickSettings", sb);
        }
        if (this.capsuleViewModel.getCapsuleState().getValue() != null) {
            setExpandableView(view);
            this.viewDialogInteractor.showDialog(this);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "ScenarioViewInteractor-->showDialog: capsuleState = null");
        }
    }
}
