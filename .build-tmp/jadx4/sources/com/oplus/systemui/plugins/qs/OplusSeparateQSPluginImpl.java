package com.oplus.systemui.plugins.qs;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.qs.IHostAbility;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SepTileSpecRepository;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;
import com.oplus.systemui.plugins.qs.customize.SepQSPipelineCoreStartable;
import com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState;
import com.oplus.systemui.plugins.qs.dagger.DaggerSeparateQSComponent$SeparateQSComponentImpl;
import com.oplus.systemui.plugins.qs.dagger.DaggerSeparateQSComponent$SeparateQSViewComponentFactory;
import com.oplus.systemui.plugins.qs.dagger.DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSComponent;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSInitial;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewComponent;
import com.oplus.systemui.plugins.qs.dagger.SeparateQsGuideComponent;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpUtils;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnap;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper;
import com.oplus.systemui.qs.base.widget.recyclerview.ParallelCOUIRecyclerView;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
@DependsOn(target = QSSpecialModeUpdateMonitor.class)
@Requirements({@Requires(target = OplusSeparateQSPlugin.class, version = 1), @Requires(target = QSSpecialModeUpdateMonitor.class, version = 1)})
public final class OplusSeparateQSPluginImpl implements OplusSeparateQSPlugin {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSeparateQSPluginImpl";
    private static OplusSeparateQSPlugin.IStub proxy;
    public ContextInstance contextInstance;
    private SeparateQSComponent daggerComponent;
    private OplusQsGuideSpiritualManager guideManager;
    private OplusQSRootView qsPanelView;
    private OplusQSRootViewComponent qsPanelViewController;
    private List<CoreStartable> startables = new ArrayList();
    private Context sysuiContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSeparateQSPlugin.IStub getProxy() {
            return OplusSeparateQSPluginImpl.proxy;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl$bindStub$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusSeparateQSPluginImpl this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    if (this.this$0.guideManager == null) {
                        OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl = this.this$0;
                        oplusSeparateQSPluginImpl.guideManager = OplusSeparateQSPluginImpl.access$generateGuideManager(oplusSeparateQSPluginImpl);
                    }
                    OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = this.this$0.guideManager;
                    if (oplusQsGuideSpiritualManager != null) {
                        oplusQsGuideSpiritualManager.handlePluginBind();
                    }
                    break;
                default:
                    OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager2 = this.this$0.guideManager;
                    if (oplusQsGuideSpiritualManager2 != null) {
                        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualManager", " handlePluginDestroy", null, 8);
                        oplusQsGuideSpiritualManager2.releaseGuide();
                    }
                    this.this$0.guideManager = null;
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl$onSeparateModeChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01971 implements Runnable {
        public /* synthetic */ boolean $isSeparateMode;
        public /* synthetic */ OplusSeparateQSPluginImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = this.$isSeparateMode;
            OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl = this.this$0;
            if (!z) {
                OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = oplusSeparateQSPluginImpl.guideManager;
                if (oplusQsGuideSpiritualManager != null) {
                    oplusQsGuideSpiritualManager.onSeparateModeChange(false);
                    return;
                }
                return;
            }
            if (oplusSeparateQSPluginImpl.guideManager == null) {
                OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl2 = this.this$0;
                oplusSeparateQSPluginImpl2.guideManager = OplusSeparateQSPluginImpl.access$generateGuideManager(oplusSeparateQSPluginImpl2);
            }
            OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager2 = this.this$0.guideManager;
            if (oplusQsGuideSpiritualManager2 != null) {
                oplusQsGuideSpiritualManager2.onSeparateModeChange(true);
            }
        }
    }

    public static final OplusQsGuideSpiritualManager access$generateGuideManager(OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl) {
        OplusQsGuideSpiritualManager guideManager;
        SeparateQSComponent separateQSComponent;
        SeparateQsGuideComponent.Builder guideComponent;
        Context context = oplusSeparateQSPluginImpl.sysuiContext;
        if (context == null || proxy == null) {
            return null;
        }
        boolean zIsNeedGuide = OplusQsGuideSpUtils.isNeedGuide(context);
        if (!zIsNeedGuide || (separateQSComponent = oplusSeparateQSPluginImpl.daggerComponent) == null || (guideComponent = ((DaggerSeparateQSComponent$SeparateQSComponentImpl) separateQSComponent).getGuideComponent()) == null) {
            guideManager = null;
        } else {
            DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder daggerSeparateQSComponent$SeparateQsGuideComponentBuilder = (DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder) guideComponent;
            daggerSeparateQSComponent$SeparateQsGuideComponentBuilder.inject = context;
            Handler workHandler = QSThreadUtil.getWorkHandler();
            workHandler.getClass();
            daggerSeparateQSComponent$SeparateQsGuideComponentBuilder.inject2 = workHandler;
            guideManager = daggerSeparateQSComponent$SeparateQsGuideComponentBuilder.build().getGuideManager();
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNeedGuide: " + zIsNeedGuide + ",  new guideManager = " + guideManager, null, 8);
        return guideManager;
    }

    public void addScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.addScenarioAnimatorCallback(qSScenarioAnimListener);
        }
    }

    public String backupTiles() {
        Object next;
        String strBackupTiles;
        Iterator<T> it = this.startables.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((CoreStartable) next) instanceof SepQSPipelineCoreStartable));
        CoreStartable coreStartable = (CoreStartable) next;
        SepQSPipelineCoreStartable sepQSPipelineCoreStartable = coreStartable instanceof SepQSPipelineCoreStartable ? (SepQSPipelineCoreStartable) coreStartable : null;
        return (sepQSPipelineCoreStartable == null || (strBackupTiles = sepQSPipelineCoreStartable.backupTiles()) == null) ? "" : strBackupTiles;
    }

    public void bindStub(OplusSeparateQSPlugin.IStub iStub) {
        Map<Class<?>, Provider> startables;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "bindStub = " + iStub, null, 8);
        proxy = iStub;
        SeparateQSInitial companion = SeparateQSInitial.Companion.getInstance();
        ContextInstance contextInstance = this.contextInstance;
        this.daggerComponent = companion.getSeparateQSComponent(contextInstance != null ? contextInstance : null, iStub);
        OplusQSSpecialModeProvider.Companion.getInstance().bindStub(iStub);
        OplusSeparateTileNameState.Companion.getInstance().bindStub(iStub);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnWorkThread(anonymousClass1);
        this.startables.clear();
        SeparateQSComponent separateQSComponent = this.daggerComponent;
        if (separateQSComponent == null || (startables = ((DaggerSeparateQSComponent$SeparateQSComponentImpl) separateQSComponent).getStartables()) == null) {
            return;
        }
        Iterator<Map.Entry<Class<?>, Provider>> it = startables.entrySet().iterator();
        while (it.hasNext()) {
            CoreStartable coreStartable = (CoreStartable) it.next().getValue().get();
            List<CoreStartable> list = this.startables;
            coreStartable.getClass();
            list.add(coreStartable);
            ((SepQSPipelineCoreStartable) coreStartable).start();
        }
    }

    public void clickTile(ComponentName componentName) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.clickTile(componentName);
        }
    }

    public final void closeCustomize(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.exitEditMode(z);
        }
    }

    public void collapseQSPanel(boolean z, boolean z2, boolean z3, Function0 function0) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (!z) {
            if (oplusQSRootViewComponent != null) {
                oplusQSRootViewComponent.collapseQSPanelWithoutAnimate(z2, function0);
            }
        } else if (oplusQSRootViewComponent != null) {
            OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
            oplusQSRootViewComponent.collapseQSPanel(z3, "");
        }
    }

    public final void disable(int i, int i2, int i3, boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.disable(i, i2, i3, z);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        OplusQSRootView oplusQSRootView = this.qsPanelView;
        if (oplusQSRootView != null) {
            return oplusQSRootView.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.dump(printWriter, strArr);
        }
        OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = this.guideManager;
        if (oplusQsGuideSpiritualManager != null) {
            oplusQsGuideSpiritualManager.dump(printWriter, strArr);
        }
        Iterator<T> it = this.startables.iterator();
        while (it.hasNext()) {
            ((CoreStartable) it.next()).dump(printWriter, strArr);
        }
    }

    public final void endPagerFlingAnimation() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            RecyclerView recyclerView = oplusQSRootViewComponent.otherTilesContainerView;
            if (recyclerView == null) {
                recyclerView = null;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
            if (staggeredPagerLayoutManager != null) {
                RecyclerView.OnFlingListener onFlingListener = staggeredPagerLayoutManager.getRecyclerView().getOnFlingListener();
                IPagerSnap iPagerSnap = onFlingListener instanceof IPagerSnap ? (IPagerSnap) onFlingListener : null;
                if (iPagerSnap != null) {
                    ((OplusPagerStaggerSnapHelper) iPagerSnap).endFlingAnimation();
                }
            }
        }
    }

    public final void expandQSPanel(boolean z, boolean z2) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (z) {
            if (oplusQSRootViewComponent != null) {
                oplusQSRootViewComponent.expandQSPanel(z2);
            }
        } else if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.expandQSPanelWithoutAnimate(z2);
        }
    }

    public final void flingToScenario(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.flingToScenario(z);
        }
    }

    public Float getBlurFraction() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return Float.valueOf(oplusQSRootViewComponent.getBlurFraction());
        }
        return null;
    }

    public int getBrightnessMirrorLayout() {
        return 2131559328;
    }

    public IBinder getExcludeWindowTokens() {
        OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = this.guideManager;
        if (oplusQsGuideSpiritualManager != null) {
            return oplusQsGuideSpiritualManager.getExcludeWindowTokens();
        }
        return null;
    }

    public Triple<List<View>, List<View>, List<View>> getExtraAnimationViews() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getExtraAnimationViews();
        }
        return null;
    }

    public Float getFirstElementShowDistance() {
        QSPanelAnimatorManager qsPanelAnimatorManager;
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return Float.valueOf(((oplusQSRootViewComponent == null || (qsPanelAnimatorManager = oplusQSRootViewComponent.getQsPanelAnimatorManager()) == null) ? 0.0f : qsPanelAnimatorManager.getDefaultSettingEngine()) * 0.86f);
    }

    public IHostAbility.LayoutConstraint getLayoutConstraint(Context context, SpanSize spanSize, boolean z) {
        CellCalculatorManager.LayoutConstraint layoutConstraint$default = CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(context), spanSize, false, z, 10);
        return new IHostAbility.LayoutConstraint(layoutConstraint$default.getCapsuleWidth(), layoutConstraint$default.getCapsuleHeight(), layoutConstraint$default.getLabelMarginTop(), layoutConstraint$default.getLabelSize(), layoutConstraint$default.getLabelLines(), layoutConstraint$default.getLabelLineHeight(), 0, 0, layoutConstraint$default.getInnerContentRatio(), layoutConstraint$default.getInnerMarginRatio(), false, 0, 0, new IHostAbility.TwoXOneTextStyleInfo(layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelTextSize(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelDescTextSize(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelLineHeight(), layoutConstraint$default.getTwoXOneTextStyleInfo().getTwoXOneLabelDescLineHeight()), 7360, (DefaultConstructorMarker) null);
    }

    public List<ViewGroup> getQSContainers() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getQSContainers();
        }
        return null;
    }

    public Rect getQSPageViewArea() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getQSPageViewArea();
        }
        return null;
    }

    public Pair<List<Rect>, List<Rect>> getQSScrollXHotZone() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getQSScrollXHotZone();
        }
        return null;
    }

    public SepTileSpecRepository getSepTileSpecRepository() {
        Object next;
        Iterator<T> it = this.startables.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((CoreStartable) next) instanceof SepQSPipelineCoreStartable));
        CoreStartable coreStartable = (CoreStartable) next;
        SepQSPipelineCoreStartable sepQSPipelineCoreStartable = coreStartable instanceof SepQSPipelineCoreStartable ? (SepQSPipelineCoreStartable) coreStartable : null;
        if (sepQSPipelineCoreStartable != null) {
            return sepQSPipelineCoreStartable.getSepTileSpecRepository();
        }
        return null;
    }

    public View getSeparateQsFakeView() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getFakeLayout();
        }
        return null;
    }

    public View getStatusBarContainer() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.getStatusBarContainer();
        }
        return null;
    }

    public Integer getStatusBarTopSpacing() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return Integer.valueOf(oplusQSRootViewComponent.getAreaTop());
        }
        return null;
    }

    public int getVersion() {
        return super/*com.android.systemui.plugins.Plugin*/.getVersion();
    }

    public final void handleIgnoreParentBoundsForScroll(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            ParallelCOUIRecyclerView.Companion companion = ParallelCOUIRecyclerView.Companion;
            RecyclerView recyclerView = oplusQSRootViewComponent.otherTilesContainerView;
            if (recyclerView == null) {
                recyclerView = null;
            }
            companion.handleIgnoreParentBoundsForScroll(recyclerView, z);
            RecyclerView recyclerView2 = oplusQSRootViewComponent.otherTilesContainerView;
            (recyclerView2 != null ? recyclerView2 : null).requestLayout();
        }
    }

    public final boolean isAnimationRunningInSeqEvent() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.isTracking && oplusQSRootViewComponent.isYTouchStart;
    }

    public final boolean isBlurFractionAnimationRunning() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.isBlurFractionAnimationRunning();
    }

    public final boolean isClosing() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.isClosing;
        }
        return false;
    }

    public final boolean isCustomizing() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.isEditMode;
    }

    public final boolean isFractionAnimationRunning() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.isFractionAnimationRunning();
    }

    public final boolean isFullyCollapsed() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.isFullyCollapsed();
        }
        return true;
    }

    public final boolean isFullyExpanded() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.isFullyExpanded();
    }

    public final boolean isQsAnimationRunning() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            return oplusQSRootViewComponent.isQsAnimationRunning();
        }
        return false;
    }

    public final boolean isQsVisible() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent == null || ((OplusQSRootView) oplusQSRootViewComponent.mView).getVisibility() == 0;
    }

    public void notifyScenarioGuideState(String str) {
        OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = this.guideManager;
        if (oplusQsGuideSpiritualManager != null) {
            oplusQsGuideSpiritualManager.onScenarioTypeChanged(str);
        }
    }

    public final boolean onBackPressed() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent == null || !oplusQSRootViewComponent.hideCouiPopup()) {
            OplusQSRootViewComponent oplusQSRootViewComponent2 = this.qsPanelViewController;
            if (oplusQSRootViewComponent2 == null || !oplusQSRootViewComponent2.isAddingMode) {
                if (oplusQSRootViewComponent2 == null || !oplusQSRootViewComponent2.exitEditMode(true)) {
                    return false;
                }
            } else if (!oplusQSRootViewComponent2.exitAddingMode(true)) {
                return false;
            }
        }
        return true;
    }

    public void onCreate(Context context, Context context2) {
        super/*com.android.systemui.plugins.Plugin*/.onCreate(context, context2);
        OplusQSSpecialModeProvider.Companion.getInstance().onCreate(this, context);
        this.sysuiContext = context;
        this.contextInstance = new ContextInstance(context2, context);
    }

    public View onCreateQSPanelView(ViewGroup viewGroup) {
        OplusQSRootViewComponent qSPanelViewController;
        SeparateQSViewComponent.Factory separateQSViewComponent;
        OplusQSRootViewComponent oplusQSRootViewComponent = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onCreateQSPanelView proxy = " + proxy, null, 8);
        OplusSeparateQSPlugin.IStub iStub = proxy;
        if (iStub != null) {
            View viewM = OplusLockIconView$.ExternalSyntheticOutline0.m(viewGroup, 2131559387, viewGroup, false);
            OplusQSRootView oplusQSRootView = viewM instanceof OplusQSRootView ? (OplusQSRootView) viewM : null;
            this.qsPanelView = oplusQSRootView;
            if (oplusQSRootView != null) {
                SeparateQSComponent separateQSComponent = this.daggerComponent;
                SeparateQSViewComponent separateQSViewComponentCreate = (separateQSComponent == null || (separateQSViewComponent = ((DaggerSeparateQSComponent$SeparateQSComponentImpl) separateQSComponent).getSeparateQSViewComponent()) == null) ? null : ((DaggerSeparateQSComponent$SeparateQSViewComponentFactory) separateQSViewComponent).create(oplusQSRootView);
                if (separateQSViewComponentCreate != null && (qSPanelViewController = separateQSViewComponentCreate.getQSPanelViewController()) != null) {
                    qSPanelViewController.init();
                    qSPanelViewController.getCellAreaProvider().getCellAreaConfig().bindStub(iStub);
                    qSPanelViewController.collapseQSPanelWithoutAnimate(false, null);
                    oplusQSRootViewComponent = qSPanelViewController;
                }
                this.qsPanelViewController = oplusQSRootViewComponent;
            }
            iStub.registerScenarioAnimationCallback();
            OplusQSRootView oplusQSRootView2 = this.qsPanelView;
            if (oplusQSRootView2 != null) {
                OplusSeparateQSPluginImpl$onCreateQSPanelView$1$2 oplusSeparateQSPluginImpl$onCreateQSPanelView$1$2 = new OplusSeparateQSPluginImpl$onCreateQSPanelView$1$2();
                oplusSeparateQSPluginImpl$onCreateQSPanelView$1$2.this$0 = this;
                VarHandle.storeStoreFence();
                oplusQSRootView2.addOnAttachStateChangeListener(oplusSeparateQSPluginImpl$onCreateQSPanelView$1$2);
            }
        }
        return this.qsPanelView;
    }

    public final void onDestroy() {
        super/*com.android.systemui.plugins.Plugin*/.onDestroy();
        OplusSeparateTileNameState.Companion.getInstance().onDestroy();
        OplusQSSpecialModeProvider.Companion.getInstance().onDestroy();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnWorkThread(anonymousClass1);
        Iterator<T> it = this.startables.iterator();
        while (it.hasNext()) {
            ((SepQSPipelineCoreStartable) ((CoreStartable) it.next())).destroy();
        }
        this.startables.clear();
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.onDestroy();
        }
    }

    public final void onInputFocusTransfer(boolean z, boolean z2, float f) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (z) {
            if (oplusQSRootViewComponent != null) {
                oplusQSRootViewComponent.startWaitingForExpandGesture();
            }
        } else if (oplusQSRootViewComponent != null) {
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("stopWaitingForExpandGesture cancel=", " expectingSynthesizedDown=", z2, oplusQSRootViewComponent.expectingSynthesizedDown), null, 8);
            if (oplusQSRootViewComponent.expectingSynthesizedDown) {
                oplusQSRootViewComponent.expectingSynthesizedDown = false;
                if (z2) {
                    oplusQSRootViewComponent.collapseQSPanelWithoutAnimate(true, null);
                } else {
                    oplusQSRootViewComponent.expandQSPanel(false);
                }
                oplusQSRootViewComponent.onTrackingStopped$1(!z2);
            }
        }
    }

    public final void onSeparateModeChange(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("separate mode change: ", z), null, 8);
        RunnableC01971 runnableC01971 = new RunnableC01971();
        runnableC01971.$isSeparateMode = z;
        runnableC01971.this$0 = this;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnWorkThread(runnableC01971);
    }

    public final void onSystemIconScaleChanged(float f) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.onSystemIconScaleChanged(f);
        }
    }

    public final void onUserRemoved(int i) {
        Context context = this.sysuiContext;
        if (context != null) {
            onUserRemoved.1.1 r0 = new onUserRemoved.1.1();
            r0.$it = context;
            r0.$userId = i;
            VarHandle.storeStoreFence();
            QSThreadUtil.runOnWorkThread(r0);
        }
    }

    public final void releaseAllSpring() {
        QSPanelAnimatorManager qsPanelAnimatorManager;
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent == null || (qsPanelAnimatorManager = oplusQSRootViewComponent.getQsPanelAnimatorManager()) == null) {
            return;
        }
        qsPanelAnimatorManager.resetSpring();
    }

    public void removeScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.removeScenarioAnimatorCallback(qSScenarioAnimListener);
        }
    }

    public final void restoreTiles() {
        Object next;
        Iterator<T> it = this.startables.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((CoreStartable) next) instanceof SepQSPipelineCoreStartable));
        CoreStartable coreStartable = (CoreStartable) next;
        SepQSPipelineCoreStartable sepQSPipelineCoreStartable = coreStartable instanceof SepQSPipelineCoreStartable ? (SepQSPipelineCoreStartable) coreStartable : null;
        if (sepQSPipelineCoreStartable != null) {
            sepQSPipelineCoreStartable.restoreTiles();
        }
    }

    public void setClipToPaddingForScroll(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.setClipToPaddingForScroll(z);
        }
    }

    public final void setScreenShotClick() {
        QSPanelExpandFraction.Companion.setScreenShotClick(true);
    }

    public void setTouchAndAnimationDisabled(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.setTouchAndAnimationDisabled(z);
        }
    }

    public final void showDeviceMonitoringDialog() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        if (oplusQSRootViewComponent != null) {
            oplusQSRootViewComponent.showDeviceMonitoringDialog();
        }
    }

    public final void showQSTip() {
    }

    public final boolean syncBlurFractionFromExternal(float f) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.qsPanelViewController;
        return oplusQSRootViewComponent != null && oplusQSRootViewComponent.syncBlurFractionFromExternal(f);
    }
}
