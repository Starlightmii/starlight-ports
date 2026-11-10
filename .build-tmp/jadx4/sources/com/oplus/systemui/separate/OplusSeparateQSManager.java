package com.oplus.systemui.separate;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.IBinder;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.ToastPlugin;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.IHostAbility;
import com.android.systemui.plugins.qs.IQSPanelPluginScanner;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSBrightness;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSGuide;
import com.android.systemui.plugins.qs.QSHostWrapper;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.android.systemui.plugins.qs.QSSecurity;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.plugins.qs.QSVolume;
import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.pipeline.data.repository.IQsSettingInitialStateRepository;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.scene.data.repository.WindowRootViewVisibilityRepository;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.PanelType;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationShadeDepthControllerEx;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimController$;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.common.helper.ResponsiveUIModelHelper;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;
import com.oplus.systemui.qs.base.res.util.ViewTagUtils;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController;
import com.oplus.systemui.qs.customize.OplusSepQueryTileHelper;
import com.oplus.systemui.qs.customize.OplusTileQueryHelper;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.media.OplusQsMediaDialogController;
import com.oplus.systemui.qs.media.OplusQsMediaPanelViewController;
import com.oplus.systemui.qs.media.OplusQsNormalMediaPanelView;
import com.oplus.systemui.qs.media.SepQsLargeMediaPanelView;
import com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository;
import com.oplus.systemui.qs.policy.OplusQSSecurityController;
import com.oplus.systemui.qs.scenario.domain.interactor.ScenarioViewInteractor;
import com.oplus.systemui.qs.slider.OplusQSBrightnessDialogController;
import com.oplus.systemui.qs.slider.OplusQSVolumeDialogController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQsVolumeController;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioPreviewShowStatePersister;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.separate.guide.OplusQsGuideEventController;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl$requestQsScrollBoost$1;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplus.systemui.toast.OplusToastFactory;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Function;
import kotlin.Deprecated;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager implements PluginListener, Dumpable, NotificationLockscreenUserManager.UserChangedListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSeparateQSManager";
    private final ActivityStarter activityStarter;
    private final Lazy brightnessDialogController;
    private final BroadcastDispatcher broadcastDispatcher;
    private final CentralSurfaces centralSurfaces;
    private final CommandQueue commandQueue;
    private final CommandQueue.Callbacks commandQueueCallback;
    private final Context context;
    private final CurrentTilesInteractor currentTilesInteractor;
    private final DialogTransitionAnimator dialogLaunchController;
    private final DumpManager dumpManager;
    private final Lazy guideEventController;
    private final kotlin.Lazy iSysUiStateCallback$delegate;
    public final boolean isAdd;
    public OplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 keyguardStateCallback;
    private final KeyguardStateController keyguardStateController;
    private final Lazy keyguardUpdateMonitor;
    private final LogBufferFactory logBufferFactory;
    private final NotificationPanelViewController notificationPanelViewController;
    private final Lazy notificationSectionsManagerEx;
    private final Lazy notificationShadeDepthControllerEx;
    private final NotificationShadeWindowController notificationShadeWindowController;
    private final Lazy oplusFakeViewProvider;
    private final PlatformBlurManager platformBlurManager;
    private final PluginDependencyProvider pluginDependencyProvider;
    private final PluginManager pluginManager;
    private OplusSeparateQSPlugin.IStub proxy;
    private final QSBrightnessFactory qSBrightnessFactory;
    private final OplusSeparateQSManager$qSHostWrapper$1 qSHostWrapper;
    private final OplusQSDetailInteractor.Factory qsDetailInteractorFactory;
    private final Lazy qsHeaderFactory;
    private final QSHost qsHost;
    private final Lazy qsMediaFactory;
    private final IQsSettingInitialStateRepository qsSettingInitialStateRepository;
    private final OplusQsVolumeController.Factory qsVolumeControllerFactory;
    private final OplusTileQueryHelper.Factory queryHelperFactory;
    private final ScenarioPreviewShowStatePersister scenarioPreviewShowStatePersister;
    private final ScenarioViewInteractor scenarioViewInteractor;
    private final ScrimController scrimController;
    private final OplusQSSecurityController.Factory securityControllerFactory;
    private final OplusSepQueryTileHelper.Factory sepQueryHelperFactory;
    private final TileSpecSeparateQSRepository sepTileSpecRepository;
    private final OplusSeparateQSFgsManager separateQSFgsManager;
    private final OplusPanelPluginScanner separateQSMyDeviceManager;
    private OplusSeparateQSPlugin separateQSPlugin;
    private final OplusSeparateQSPrivacyManager separateQSPrivacyManager;
    private final OplusSeparateQSQuickEntranceManager separateQSQuickEntranceManager;
    private final ShadeAnimationInteractor shadeAnimationInteractor;
    private final ShadeExpansionStateManager shadeExpansionStateManager;
    private final ShadeRepository shadeRepository;
    private final OplusSimpleQSManager simpleQsManager;
    private final QSSpecialModeUpdateMonitorImpl specialModeUpdateMonitorImpl;
    private final SysUiStateControllerImpl sysUiStateController;
    private final OplusToastFactory toastFactory;
    private final UserFileManager userFileManager;
    private final UserManager userManager;
    private final UserTracker userTracker;
    private final Lazy volumeDialogController;
    private final Lazy windowRootViewVisibilityRepository;
    private final List<Listener> callbacks = new ArrayList();
    private final Set<OplusSeparateQSPlugin.ISysUiStateListener> iSysUiStateCallbacks = new LinkedHashSet();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Listener {
        void onPluginAdded(OplusSeparateQSPlugin oplusSeparateQSPlugin, Context context);

        void onPluginRemove(OplusSeparateQSPlugin oplusSeparateQSPlugin);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSMediaFactory {
        private final OutputAudioSharingInteractor audioSharingInteractor;
        private final BroadcastDispatcher broadcastDispatcher;
        private final DialogTransitionAnimator dialogLaunchController;
        private final DumpManager dumpManager;
        private final MediaDataManager mediaDataManager;
        private final OplusQsMediaDialogController qsMediaDialogController;
        private final SeekBarViewModel.Factory seekBarViewModelFactory;
        private final ShadeInteractor shadeInteractor;

        public QSMediaFactory(ShadeInteractor shadeInteractor, MediaDataManager mediaDataManager, OplusQsMediaDialogController oplusQsMediaDialogController, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator, SeekBarViewModel.Factory factory, OutputAudioSharingInteractor outputAudioSharingInteractor, DumpManager dumpManager) {
            this.shadeInteractor = shadeInteractor;
            this.mediaDataManager = mediaDataManager;
            this.qsMediaDialogController = oplusQsMediaDialogController;
            this.broadcastDispatcher = broadcastDispatcher;
            this.dialogLaunchController = dialogTransitionAnimator;
            this.seekBarViewModelFactory = factory;
            this.audioSharingInteractor = outputAudioSharingInteractor;
            this.dumpManager = dumpManager;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [android.view.View, com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView, com.oplus.systemui.qs.media.OplusQsNormalMediaPanelView] */
        /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View, com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView, com.oplus.systemui.qs.media.SepQsLargeMediaPanelView] */
        public final OplusQsMediaPanelViewController createQSMediaPanelView(Context context, SpanSizeInPlugin spanSizeInPlugin) {
            if (SpanSizeKt.isFourXTwo(SpanCache.INSTANCE.spanSize(spanSizeInPlugin.getColSpan(), spanSizeInPlugin.getRowSpan()))) {
                ?? r2 = (SepQsLargeMediaPanelView) LayoutInflater.from(context).inflate(2131559348, (ViewGroup) null, false);
                ViewTagUtils.setSepQSViewTag(r2, true);
                return new OplusQsMediaPanelViewController(r2, this.mediaDataManager, this.qsMediaDialogController, this.broadcastDispatcher, this.dialogLaunchController, this.seekBarViewModelFactory, this.audioSharingInteractor, this.shadeInteractor, this.dumpManager);
            }
            ?? r3 = (OplusQsNormalMediaPanelView) LayoutInflater.from(context).inflate(2131559349, (ViewGroup) null, false);
            ViewTagUtils.setSepQSViewTag(r3, true);
            return new OplusQsMediaPanelViewController(r3, this.mediaDataManager, this.qsMediaDialogController, this.broadcastDispatcher, this.dialogLaunchController, this.seekBarViewModelFactory, this.audioSharingInteractor, this.shadeInteractor, this.dumpManager);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SeparateQSHost implements OplusSeparateQSPlugin.IStub {
        private final Context context;

        public SeparateQSHost(Context context) {
            this.context = context;
        }

        public void addFullScreenChangedCallback(OplusSeparateQSPlugin.ISysUiStateListener iSysUiStateListener) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            boolean zIsEmpty = oplusSeparateQSManager.iSysUiStateCallbacks.isEmpty();
            oplusSeparateQSManager.iSysUiStateCallbacks.add(iSysUiStateListener);
            if (zIsEmpty) {
                oplusSeparateQSManager.sysUiStateController.addListener(oplusSeparateQSManager.getISysUiStateCallback());
            }
        }

        public void addKeyguardShowingChangedCallback(Function0 function0) {
            OplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 = new OplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1();
            oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1.$callback = function0;
            VarHandle.storeStoreFence();
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            oplusSeparateQSManager.keyguardStateCallback = oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1;
            KeyguardStateControllerImpl keyguardStateControllerImpl = oplusSeparateQSManager.keyguardStateController;
            OplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$2 = oplusSeparateQSManager.keyguardStateCallback;
            if (oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$2 == null) {
                oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$2 = null;
            }
            KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
            keyguardStateControllerImpl2.getClass();
            keyguardStateControllerImpl2.addCallback(oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$2);
        }

        public QSFgs bindFgs() {
            return OplusSeparateQSManager.this.separateQSFgsManager.bindQSFgs();
        }

        public QSGuide bindGuideProvider() {
            return (QSGuide) OplusSeparateQSManager.this.guideEventController.get();
        }

        public QSPrivary bindPrivary() {
            return OplusSeparateQSManager.this.separateQSPrivacyManager.bindQSPrivary();
        }

        public QSBrightness bindQSBrightness(ViewGroup viewGroup, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSManager.TAG, "bindQSBrightness viewGroup: " + viewGroup, null, 8);
            return OplusSeparateQSManager.this.qSBrightnessFactory.bindQSBrightness(viewGroup, onClickListener, onLongClickListener);
        }

        public QSElement bindQSElement(String str, Context context) {
            boolean zAreEqual = Intrinsics.areEqual(str, "header");
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            if (zAreEqual) {
                OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderControllerCreate = ((QSHeaderFactory) oplusSeparateQSManager.qsHeaderFactory.get()).create(null);
                oplusQuickStatusBarHeaderControllerCreate.init();
                OplusSeparateQSManager$SeparateQSHost$bindQSElement$1 oplusSeparateQSManager$SeparateQSHost$bindQSElement$1 = new OplusSeparateQSManager$SeparateQSHost$bindQSElement$1();
                oplusSeparateQSManager$SeparateQSHost$bindQSElement$1.$headerController = oplusQuickStatusBarHeaderControllerCreate;
                VarHandle.storeStoreFence();
                return oplusSeparateQSManager$SeparateQSHost$bindQSElement$1;
            }
            OplusSeparateQSManager$SeparateQSHost$bindQSElement$2 oplusSeparateQSManager$SeparateQSHost$bindQSElement$2 = new OplusSeparateQSManager$SeparateQSHost$bindQSElement$2();
            oplusSeparateQSManager$SeparateQSHost$bindQSElement$2.this$0 = oplusSeparateQSManager;
            oplusSeparateQSManager$SeparateQSHost$bindQSElement$2.$context = context;
            oplusSeparateQSManager$SeparateQSHost$bindQSElement$2.qsMediaPanelViewControllerCache$delegate = LazyKt__LazyJVMKt.lazy(new OplusSeparateQSManager$SeparateQSHost$bindQSElement$2$$ExternalSyntheticLambda0());
            VarHandle.storeStoreFence();
            return oplusSeparateQSManager$SeparateQSHost$bindQSElement$2;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public QSFakeStatusElement bindQSFakeElement(String str, Context context) {
            int iHashCode = str.hashCode();
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            switch (iHashCode) {
                case -1897277039:
                    if (str.equals("fake_pricacy")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_PRIVACY);
                    }
                    break;
                case -1027852274:
                    if (str.equals("fake_carrier")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_CARRIER);
                    }
                    break;
                case -862086137:
                    if (str.equals("keyguard_fake_carrier")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.KEYGUARD_FAKE_CARRIER);
                    }
                    break;
                case -314348139:
                    if (str.equals("fake_notification")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_NOTIFICATION);
                    }
                    break;
                case 1134689308:
                    if (str.equals("fake_status")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_SYSTEM_ICON);
                    }
                    break;
                case 1417131267:
                    if (str.equals("keyguard_fake_status")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.KEYGUARD_FAKE_SYSTEM_ICON);
                    }
                    break;
                case 1961263780:
                    if (str.equals("fake_clock")) {
                        return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_CLOCK);
                    }
                    break;
            }
            return ((OplusFakeViewProvider) oplusSeparateQSManager.oplusFakeViewProvider.get()).getQsFakeView(FakeType.FAKE_SYSTEM_ICON);
        }

        public QSHostWrapper bindQSHost() {
            return OplusSeparateQSManager.this.qSHostWrapper;
        }

        public IQSPanelPluginScanner bindQSPanelPluginScanner() {
            return OplusSeparateQSManager.this.separateQSMyDeviceManager;
        }

        public QSSecurity bindQSSecurity() {
            return OplusSeparateQSManager.this.securityControllerFactory.create();
        }

        public QSTileQueryHelper bindQSTileQueryHelper() {
            return OplusSeparateQSManager.this.queryHelperFactory.create();
        }

        public QSVolume bindQSVolume() {
            return OplusSeparateQSManager.this.qsVolumeControllerFactory.create();
        }

        public QSQuickEntrance bindQuickEntrance() {
            return OplusSeparateQSManager.this.separateQSQuickEntranceManager.bindQuickEntrance();
        }

        public void bindScenarioView(ViewGroup viewGroup) {
            OplusSeparateQSManager.this.scenarioViewInteractor.bindView(viewGroup);
        }

        public SepQSTileQueryHelper bindSepQSTileQueryHelper() {
            return OplusSeparateQSManager.this.sepQueryHelperFactory.create();
        }

        public ToastPlugin.Toast bindSystemUIToast(Context context, CharSequence charSequence) {
            return OplusSeparateQSManager.this.toastFactory.createToast(context, charSequence, context.getPackageName(), context.getUserId(), context.getResources().getConfiguration().orientation, new ResponsiveUIModelHelper());
        }

        public boolean canSlideHToQSOnStatusBar(PointF pointF) {
            return OplusSeparateQSManager.this.centralSurfaces.getSeparateNotificationAndQS().canSlideHToQSOnStatusBar(pointF);
        }

        public final void cancelBrightnessMirrorFadeAnimation() {
            OplusSeparateQSManager.this.scrimController.getExt().cancelBrightnessFadeAnimation("blurFractionAnimationStart");
        }

        public LogBuffer createLogBuffer(String str, int i) {
            return LogBufferFactory.create$default(OplusSeparateQSManager.this.logBufferFactory, str, i, false, (String) null, 28);
        }

        public final void forceStopMotionBlur(boolean z) {
            OplusSeparateQSManager.this.platformBlurManager.forceStopMotionBlurRefresh(z);
        }

        public final Context getContext() {
            return this.context;
        }

        public Context getCurrentUserContext(Context context) {
            return MultiUserContentHelper.Companion.getCurrentUserContext(context);
        }

        public List<String> getInitQsSettingsState(int i) {
            return OplusSeparateQSManager.this.qsSettingInitialStateRepository.getInitOrCurrentTileSpecs(i);
        }

        public int getInitialDisplayDensity() {
            return KeyguardUtils.Companion.getInitialDisplayDensity();
        }

        public String getMediaSwitchItemText(Context context) {
            return context.getResources().getString(2131955664);
        }

        public boolean getNtIsFullCollapsed() {
            return OplusSeparateQSManager.this.notificationPanelViewController.isFullyCollapsed();
        }

        public boolean getOneHandedEnable() {
            return OneHandedObserver.Companion.getInstance().getOneHandedEnable();
        }

        public Boolean getScenarioPreviewState(String str) {
            return OplusSeparateQSManager.this.scenarioPreviewShowStatePersister.getState(str);
        }

        public Rect getScreenHoleRect(Context context) {
            return OplusStatusBarUtils.getScreenHoleRect(context);
        }

        public SharedPreferences getSharedPreferences(String str, int i, int i2) {
            return OplusSeparateQSManager.this.userFileManager.getSharedPreferences(str, i, i2);
        }

        public boolean getUseMotionBlur() {
            return ((Boolean) OplusSeparateQSManager.this.platformBlurManager.getUseMotionBlur().getValue()).booleanValue();
        }

        public void gpuBoomForTouchEvent(MotionEvent motionEvent) {
            ((OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class)).gpuBoomForTouchEvent(motionEvent);
        }

        public final boolean hasScenario() {
            return OplusSeparateQSManager.this.scenarioViewInteractor.hasScenario();
        }

        public final boolean isCenterHole() {
            return OplusStatusBarUtils.isCenterHole();
        }

        public final boolean isDeviceProvisioned() {
            return ((KeyguardUpdateMonitor) OplusSeparateQSManager.this.keyguardUpdateMonitor.get()).mDeviceProvisioned;
        }

        public final boolean isDraggingX() {
            return OplusSeparateQSManager.this.centralSurfaces.getSeparateNotificationAndQS().isDraggingX();
        }

        public final boolean isInXOverscroll() {
            return OplusSeparateQSManager.this.centralSurfaces.getSeparateNotificationAndQS().isInXOverscroll();
        }

        public final boolean isKeyguardOccluded() {
            return OplusSeparateQSManager.this.keyguardStateController.mOccluded;
        }

        public final boolean isKeyguardShowing() {
            return OplusSeparateQSManager.this.keyguardStateController.mShowing;
        }

        public final boolean isLeftHole() {
            return OplusStatusBarUtils.holePosition() == 1;
        }

        public final boolean isRightHole() {
            return OplusStatusBarUtils.isHoleRight();
        }

        public final void notifyEnableShowTileNameChange(boolean z) {
            OplusSeparateQSManager.this.simpleQsManager.updateTopMargin("showTileName");
        }

        public void notifyScenarioGuideState(String str) {
            OplusSeparateQSPlugin separateQSPlugin = OplusSeparateQSManager.this.getSeparateQSPlugin();
            if (separateQSPlugin != null) {
                separateQSPlugin.notifyScenarioGuideState(str);
            }
        }

        public final void notifySeparateQSEndMotionEvent(float f, boolean z) {
            OplusSeparateQSManager.this.centralSurfaces.getSeparateNotificationAndQS().notifySeparateQSEndMotionEvent(f, z);
        }

        public final void notifyStatusbarState(boolean z) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            oplusSeparateQSManager.sysUiStateController.notifyStatusbarState(z, oplusSeparateQSManager.getSeparateQSFakeView());
        }

        public final void onAZoneHeightChanged(int i) {
            OplusSeparateQSManager.this.simpleQsManager.onAZoneHeightChanged(i);
        }

        public final void onQsClosingBegin() {
            if (OplusSeparateQSManager.this.isQsVisible()) {
                ((OsenseHelperImpl) ((OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class))).autoGPUBoomForQs();
            }
            QSFragmentHelper.getInstance().onQsClosingBegin();
        }

        public final void onQsClosingEnd() {
            QSFragmentHelper.getInstance().onQsClosingEnd();
        }

        public final void onReloadQSRootView() {
            OplusSeparateQSManager.access$notifyReloadQSRootView(OplusSeparateQSManager.this);
        }

        public final void performHapticFeedback(boolean z) {
            if (z) {
                VibrationHelper.getInstance(this.context).onPerformHapticFeedback();
            }
        }

        public void postQSRunnableDismissingKeyguard(Runnable runnable) {
            OplusSeparateQSManager.this.activityStarter.postQSRunnableDismissingKeyguard(runnable);
        }

        public void registerDialogDismissListener(Dialog dialog, Runnable runnable) {
            SystemUIDialog.registerDismissListener(dialog, runnable);
        }

        public void registerForUserSwitch(Context context, Uri uri, boolean z, ContentObserver contentObserver) {
            MultiUserContentHelper.Companion.registerForUserSwitch(context, uri, z, contentObserver);
        }

        public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle userHandle, int i, String str) {
            OplusSeparateQSManager.this.broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter, executor, userHandle, i, str);
        }

        public final void registerScenarioAnimationCallback() {
            OplusSeparateQSManager.this.scenarioViewInteractor.registerScenarioAnimationCallback();
        }

        public void removeFullScreenChangedCallback(OplusSeparateQSPlugin.ISysUiStateListener iSysUiStateListener) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            oplusSeparateQSManager.iSysUiStateCallbacks.remove(iSysUiStateListener);
            if (oplusSeparateQSManager.iSysUiStateCallbacks.isEmpty()) {
                oplusSeparateQSManager.sysUiStateController.removeListener(oplusSeparateQSManager.getISysUiStateCallback());
            }
        }

        public final void removeKeyguardShowingChangedCallback() {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            KeyguardStateControllerImpl keyguardStateControllerImpl = oplusSeparateQSManager.keyguardStateController;
            OplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 = oplusSeparateQSManager.keyguardStateCallback;
            if (oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 == null) {
                oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1 = null;
            }
            keyguardStateControllerImpl.removeCallback(oplusSeparateQSManager$SeparateQSHost$addKeyguardShowingChangedCallback$1);
        }

        public final void requestQsScrollBoost(boolean z) {
            OsenseHelperImpl osenseHelperImpl = (OsenseHelperImpl) ((OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class));
            osenseHelperImpl.getClass();
            OsenseHelperImpl$requestQsScrollBoost$1 osenseHelperImpl$requestQsScrollBoost$1 = new OsenseHelperImpl$requestQsScrollBoost$1();
            osenseHelperImpl$requestQsScrollBoost$1.$enable = z;
            osenseHelperImpl$requestQsScrollBoost$1.this$0 = osenseHelperImpl;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(osenseHelperImpl$requestQsScrollBoost$1);
        }

        public final void restrainNavigationBarInvertColor(boolean z) {
            NotificationShadeDepthControllerEx notificationShadeDepthControllerEx = (NotificationShadeDepthControllerEx) OplusSeparateQSManager.this.notificationShadeDepthControllerEx.get();
            if (notificationShadeDepthControllerEx != null) {
                notificationShadeDepthControllerEx.restrainNavigationBarInvertColor(1, z);
            }
        }

        public void setScenarioPreviewState(String str, boolean z) {
            OplusSeparateQSManager.this.scenarioPreviewShowStatePersister.setState(str, z);
        }

        public void showBrightnessDialog(QSBrightness qSBrightness, View view, boolean z) {
            OplusQsBrightnessSliderController qsBrightnessSliderController;
            if (z) {
                VibrationHelper.getInstance(this.context).onPerformHapticFeedback();
            }
            OplusQSBrightnessDialogController oplusQSBrightnessDialogController = (OplusQSBrightnessDialogController) OplusSeparateQSManager.this.brightnessDialogController.get();
            boolean z2 = qSBrightness instanceof QSBrightnessFactory.QSBrightnessImpl;
            QSBrightnessFactory.QSBrightnessImpl qSBrightnessImpl = z2 ? (QSBrightnessFactory.QSBrightnessImpl) qSBrightness : null;
            if (qSBrightnessImpl == null || (qsBrightnessSliderController = qSBrightnessImpl.getQsBrightnessSliderController()) == null || qsBrightnessSliderController.getMTracking()) {
                return;
            }
            QSBrightnessFactory.QSBrightnessImpl qSBrightnessImpl2 = z2 ? (QSBrightnessFactory.QSBrightnessImpl) qSBrightness : null;
            oplusQSBrightnessDialogController.setFromSliderController(qSBrightnessImpl2 != null ? qSBrightnessImpl2.getQsBrightnessSliderController() : null);
            oplusQSBrightnessDialogController.showDialog(view);
        }

        public Function<Boolean, Boolean> showDialog(Context context, View view, boolean z, DetailAdapter detailAdapter, String str) {
            OplusQSDetailInteractor oplusQSDetailInteractorCreate = OplusSeparateQSManager.this.qsDetailInteractorFactory.create(detailAdapter);
            int size = bindPrivary().getPrivacyElementList().size();
            oplusQSDetailInteractorCreate.show(view);
            if (!TextUtils.isEmpty(str)) {
                QsStatisticUtil.collectSeparateQSCenterEvent(context, str, size, ShadeExpansionHelperImpl.VAL_INVALID, true);
            }
            showDialog.1 r0 = new showDialog.1();
            r0.$qsDetailInteractor = oplusQSDetailInteractorCreate;
            VarHandle.storeStoreFence();
            return r0;
        }

        public void showMediaSwitchDialog(Context context, View view) {
            MediaOutputDialogManager mediaOutputDialogManager = (MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class);
            if (mediaOutputDialogManager != null) {
                MediaOutputDialogManager.create$default(mediaOutputDialogManager, (String) null, true, (View) null, false, false, (MediaSession.Token) null, false, 252);
            }
            QsStatisticUtil.collectMediaPanelActionClick(context, "6");
        }

        public void showVolumeDialog(QSVolume qSVolume, Object obj, boolean z) {
            if (z) {
                VibrationHelper.getInstance(this.context).onPerformHapticFeedback();
            }
            OplusQsVolumeSliderController oplusQsVolumeSliderController = (OplusQsVolumeSliderController) obj;
            View expandableView = oplusQsVolumeSliderController.getExpandableView();
            OplusQSVolumeDialogController oplusQSVolumeDialogController = (OplusQSVolumeDialogController) OplusSeparateQSManager.this.volumeDialogController.get();
            oplusQSVolumeDialogController.setQsVolume(qSVolume);
            oplusQSVolumeDialogController.setFromSliderController(oplusQsVolumeSliderController);
            oplusQSVolumeDialogController.showDialog(expandableView);
        }

        public final void startWaitAction() {
            ((NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class)).startWaitAction(4);
        }

        public final void stopHorizontalSlideSwitchOnCollapse() {
            OplusSeparateNotificationAndQSEx separateNotificationAndQS = OplusSeparateQSManager.this.centralSurfaces.getSeparateNotificationAndQS();
            if (separateNotificationAndQS == null || !separateNotificationAndQS.enableSeparateNotificationAndQS()) {
                return;
            }
            separateNotificationAndQS.stopHorizontalSlideSwitchOnCollapse(PanelType.QS_PANEL);
        }

        public final void stopWaitAction() {
            ((NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class)).stopWaitAction(4);
        }

        public void trackingStart(View view) {
            if (KeyguardRapidUnlockUtils.Companion.getKeyguardComposed()) {
                return;
            }
            trackingStart.1.1 r0 = new trackingStart.1.1();
            r0.$this_run = view;
            VarHandle.storeStoreFence();
            view.post(r0);
        }

        public void unregister(Context context, ContentObserver contentObserver) {
            MultiUserContentHelper.Companion.unregister(context, contentObserver);
        }

        public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
            OplusSeparateQSManager.this.broadcastDispatcher.unregisterReceiver(broadcastReceiver);
        }

        public final void updateEditMode(boolean z) {
            OplusSeparateQSManager.this.scrimController.getExt().updateEditMode(z);
        }

        public final void updateSeparateBlurExpansion(float f, boolean z, boolean z2, float f2) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            ScrimController scrimController = oplusSeparateQSManager.scrimController;
            PanelType panelType = PanelType.QS_PANEL;
            ScrimControllerEx scrimControllerEx = scrimController.mExt;
            ScrimController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ScrimController$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = scrimController;
            VarHandle.storeStoreFence();
            scrimControllerEx.setQsPosition(oplusSeparateQSManager.getBlurFraction(), (int) f2, panelType, externalSyntheticLambda0);
            oplusSeparateQSManager.centralSurfaces.getSeparateNotificationAndQS().updateQSExpandFraction(f, f2, oplusSeparateQSManager.getBlurFraction(), z);
        }

        public final void updateSeparateQSExpandState(boolean z) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            oplusSeparateQSManager.notificationShadeWindowController.setQSPanelVisible(z);
            oplusSeparateQSManager.shadeRepository.setLegacyExpandedOrAwaitingInputTransferForQS(z);
            oplusSeparateQSManager.sysUiStateController.onSeparateQSExpand(z, oplusSeparateQSManager.getSeparateQSFakeView());
            ((WindowRootViewVisibilityRepository) oplusSeparateQSManager.windowRootViewVisibilityRepository.get()).setIsQSVisible(z);
            if (!z) {
                CentralSurfacesImpl centralSurfacesImpl = oplusSeparateQSManager.centralSurfaces;
                boolean zIsFullyCollapsed = centralSurfacesImpl.mShadeSurface.isFullyCollapsed();
                TransitionStep transitionStep = (TransitionStep) centralSurfacesImpl.mKeyguardTransitionInteractor.getTransitionState().getValue();
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("onOplusSeparateQSExpandedInvisible: notificationPanelCollapsed = ", ", mState = ", zIsFullyCollapsed);
                sbM.append(centralSurfacesImpl.mState);
                sbM.append(", mScreenOff = ");
                sbM.append(centralSurfacesImpl.mScreenOff);
                sbM.append(", transitionStep.getFrom = ");
                sbM.append(transitionStep.getFrom());
                sbM.append(", transitionStep.getTo = ");
                sbM.append(transitionStep.getTo());
                LogUtil.i(LogUtil.TAG_NOTIFICATION, "CentralSurfaces", sbM.toString());
                if (zIsFullyCollapsed && centralSurfacesImpl.mState == 1 && !centralSurfacesImpl.mScreenOff) {
                    KeyguardState from = transitionStep.getFrom();
                    KeyguardState keyguardState = KeyguardState.OFF;
                    if (from != keyguardState || transitionStep.getTo() != keyguardState) {
                        centralSurfacesImpl.onExpandedInvisible();
                    }
                }
            }
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSManager.TAG, "updateSeparateQSExpandState recomputeDisableFlags", null, 8);
            oplusSeparateQSManager.centralSurfaces.getShadeViewController().updateSystemUiStateFlags();
            oplusSeparateQSManager.shadeRepository.setQsShadeTracking(z);
        }

        public final void updateSeparateQSExpansion(float f, boolean z, boolean z2, float f2) {
            OplusSeparateQSManager oplusSeparateQSManager = OplusSeparateQSManager.this;
            ShadeExpansionStateManager shadeExpansionStateManager = oplusSeparateQSManager.shadeExpansionStateManager;
            PanelType panelType = PanelType.QS_PANEL;
            shadeExpansionStateManager.onPanelExpansionChanged(f, z, z2, "SeparateQS", panelType);
            oplusSeparateQSManager.shadeRepository.setQsShadeTracking(z2);
            SysUiStateControllerImpl sysUiStateControllerImpl = oplusSeparateQSManager.sysUiStateController;
            sysUiStateControllerImpl.separateQSExpandFraction = f;
            float fMax = Math.max(f, sysUiStateControllerImpl.separateNtExpandFraction);
            if (sysUiStateControllerImpl.panelExpandFraction != fMax) {
                sysUiStateControllerImpl.panelExpandFraction = fMax;
                sysUiStateControllerImpl.notifyExpandFractionChange(fMax, 3);
            }
            ScrimController scrimController = oplusSeparateQSManager.scrimController;
            ScrimControllerEx scrimControllerEx = scrimController.mExt;
            ScrimController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ScrimController$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = scrimController;
            VarHandle.storeStoreFence();
            scrimControllerEx.setQsPosition(oplusSeparateQSManager.getBlurFraction(), (int) f2, panelType, externalSyntheticLambda0);
            oplusSeparateQSManager.shadeRepository.setSeparateQsExpansion(f);
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardViewMediatorEx().syncOneShotClockAlphaForShade(f);
            if (z && f == 0.0f) {
                LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSManager.TAG, "updateSeparateQSExpansion: fraction == 0 && expand, not send to panelViewPager", null, 8);
            } else {
                oplusSeparateQSManager.centralSurfaces.getSeparateNotificationAndQS().updateQSExpandFraction(f, f2, oplusSeparateQSManager.getBlurFraction(), z);
            }
        }
    }

    public OplusSeparateQSManager(Context context, DumpManager dumpManager, UserManager userManager, UserTracker userTracker, PluginManager pluginManager, PluginDependencyProvider pluginDependencyProvider, BroadcastDispatcher broadcastDispatcher, QSHost qSHost, CurrentTilesInteractor currentTilesInteractor, ShadeExpansionStateManager shadeExpansionStateManager, NotificationShadeWindowController notificationShadeWindowController, QSSpecialModeUpdateMonitorImpl qSSpecialModeUpdateMonitorImpl, OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager, OplusSeparateQSFgsManager oplusSeparateQSFgsManager, OplusSimpleQSManager oplusSimpleQSManager, QSBrightnessFactory qSBrightnessFactory, OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager, OplusPanelPluginScanner oplusPanelPluginScanner, Lazy lazy, Lazy lazy2, Lazy lazy3, OplusQSSecurityController.Factory factory, OplusQsVolumeController.Factory factory2, ShadeRepository shadeRepository, Lazy lazy4, Lazy lazy5, DialogTransitionAnimator dialogTransitionAnimator, CommandQueue commandQueue, OplusTileQueryHelper.Factory factory3, OplusSepQueryTileHelper.Factory factory4, CentralSurfaces centralSurfaces, ScrimController scrimController, Lazy lazy6, Lazy lazy7, SysUiStateControllerImpl sysUiStateControllerImpl, OplusQSDetailInteractor.Factory factory5, PlatformBlurManager platformBlurManager, NotificationPanelViewController notificationPanelViewController, KeyguardStateController keyguardStateController, Lazy lazy8, TileSpecSeparateQSRepository tileSpecSeparateQSRepository, OplusToastFactory oplusToastFactory, IQsSettingInitialStateRepository iQsSettingInitialStateRepository, UserFileManager userFileManager, ActivityStarter activityStarter, ShadeAnimationInteractor shadeAnimationInteractor, LogBufferFactory logBufferFactory, Lazy lazy9, Lazy lazy10, ScenarioViewInteractor scenarioViewInteractor, ScenarioPreviewShowStatePersister scenarioPreviewShowStatePersister) {
        this.context = context;
        this.dumpManager = dumpManager;
        this.userManager = userManager;
        this.userTracker = userTracker;
        this.pluginManager = pluginManager;
        this.pluginDependencyProvider = pluginDependencyProvider;
        this.broadcastDispatcher = broadcastDispatcher;
        this.qsHost = qSHost;
        this.currentTilesInteractor = currentTilesInteractor;
        this.shadeExpansionStateManager = shadeExpansionStateManager;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.specialModeUpdateMonitorImpl = qSSpecialModeUpdateMonitorImpl;
        this.separateQSPrivacyManager = oplusSeparateQSPrivacyManager;
        this.separateQSFgsManager = oplusSeparateQSFgsManager;
        this.simpleQsManager = oplusSimpleQSManager;
        this.qSBrightnessFactory = qSBrightnessFactory;
        this.separateQSQuickEntranceManager = oplusSeparateQSQuickEntranceManager;
        this.separateQSMyDeviceManager = oplusPanelPluginScanner;
        this.qsHeaderFactory = lazy;
        this.oplusFakeViewProvider = lazy2;
        this.qsMediaFactory = lazy3;
        this.securityControllerFactory = factory;
        this.qsVolumeControllerFactory = factory2;
        this.shadeRepository = shadeRepository;
        this.notificationSectionsManagerEx = lazy4;
        this.notificationShadeDepthControllerEx = lazy5;
        this.dialogLaunchController = dialogTransitionAnimator;
        this.commandQueue = commandQueue;
        this.queryHelperFactory = factory3;
        this.sepQueryHelperFactory = factory4;
        this.centralSurfaces = centralSurfaces;
        this.scrimController = scrimController;
        this.guideEventController = lazy6;
        this.windowRootViewVisibilityRepository = lazy7;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.qsDetailInteractorFactory = factory5;
        this.platformBlurManager = platformBlurManager;
        this.notificationPanelViewController = notificationPanelViewController;
        this.keyguardStateController = keyguardStateController;
        this.keyguardUpdateMonitor = lazy8;
        this.sepTileSpecRepository = tileSpecSeparateQSRepository;
        this.toastFactory = oplusToastFactory;
        this.qsSettingInitialStateRepository = iQsSettingInitialStateRepository;
        this.userFileManager = userFileManager;
        this.activityStarter = activityStarter;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.logBufferFactory = logBufferFactory;
        this.brightnessDialogController = lazy9;
        this.volumeDialogController = lazy10;
        this.scenarioViewInteractor = scenarioViewInteractor;
        this.scenarioPreviewShowStatePersister = scenarioPreviewShowStatePersister;
        OplusSeparateQSManager$$ExternalSyntheticLambda0 oplusSeparateQSManager$$ExternalSyntheticLambda0 = new OplusSeparateQSManager$$ExternalSyntheticLambda0();
        oplusSeparateQSManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.iSysUiStateCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusSeparateQSManager$$ExternalSyntheticLambda0);
        OplusSeparateQSManager$qSHostWrapper$1 oplusSeparateQSManager$qSHostWrapper$1 = new OplusSeparateQSManager$qSHostWrapper$1();
        oplusSeparateQSManager$qSHostWrapper$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qSHostWrapper = oplusSeparateQSManager$qSHostWrapper$1;
        OplusSeparateQSManager$commandQueueCallback$1 oplusSeparateQSManager$commandQueueCallback$1 = new OplusSeparateQSManager$commandQueueCallback$1();
        oplusSeparateQSManager$commandQueueCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.commandQueueCallback = oplusSeparateQSManager$commandQueueCallback$1;
        pluginDependencyProvider.allowPluginDependency(QSSpecialModeUpdateMonitor.class, qSSpecialModeUpdateMonitorImpl);
        if (!this.isAdd) {
            pluginManager.addPluginListener(this, OplusSeparateQSPlugin.class, true);
            dumpManager.registerDumpable(TAG, this);
            this.isAdd = true;
        }
        oplusSeparateQSQuickEntranceManager.setOplusSeparateQSManager(this);
    }

    public static final void access$notifyReloadQSRootView(OplusSeparateQSManager oplusSeparateQSManager) {
        Iterator<T> it = oplusSeparateQSManager.callbacks.iterator();
        while (it.hasNext()) {
            OplusPanelViewPagerController oplusPanelViewPagerController = ((OplusPanelViewPagerController$separateQSPluginListener$1) ((Listener) it.next())).this$0;
            oplusPanelViewPagerController.loadQSPanelView(((Boolean) oplusPanelViewPagerController.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue());
        }
    }

    public static /* synthetic */ void collapseQSPanel$default(OplusSeparateQSManager oplusSeparateQSManager, boolean z, boolean z2, boolean z3, Function0 function0, int i) {
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        oplusSeparateQSManager.collapseQSPanel(z, z2, z3, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SysUiStateController.ISysUiStateListener getISysUiStateCallback() {
        return (SysUiStateController.ISysUiStateListener) this.iSysUiStateCallback$delegate.getValue();
    }

    public final void addCallback(Listener listener) {
        if (this.callbacks.contains(listener)) {
            return;
        }
        this.callbacks.add(listener);
    }

    public final void addScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.addScenarioAnimatorCallback(qSScenarioAnimListener);
        }
    }

    public final void clickTile(ComponentName componentName) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.clickTile(componentName);
        }
    }

    public final void collapseQSPanel(boolean z, boolean z2, boolean z3, Function0 function0) {
        boolean zBooleanValue = ((Boolean) this.shadeAnimationInteractor.isQsLaunchingActivity().getValue()).booleanValue();
        boolean z4 = z && !zBooleanValue;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("collapseQSPanel: shouldAnimate:", " isQsLaunchingActivity:", " animate:", z, zBooleanValue);
        sbM.append(z4);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.collapseQSPanel(z4, z2, z3, function0);
        }
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.dump(printWriter, strArr);
        }
    }

    public final void endPagerFlingAnimation() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.endPagerFlingAnimation();
        }
    }

    public final void expandQSPanel(boolean z, boolean z2) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin;
        if (!this.commandQueue.panelsEnabled() || (oplusSeparateQSPlugin = this.separateQSPlugin) == null) {
            return;
        }
        oplusSeparateQSPlugin.expandQSPanel(z, z2);
    }

    public final void flingToScenario(boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.flingToScenario(z);
        }
    }

    public final float getBlurFraction() {
        Float blurFraction;
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin == null || (blurFraction = oplusSeparateQSPlugin.getBlurFraction()) == null) {
            return 0.0f;
        }
        return blurFraction.floatValue();
    }

    public final Integer getBrightnessMirrorLayout() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return Integer.valueOf(oplusSeparateQSPlugin.getBrightnessMirrorLayout());
        }
        return null;
    }

    public final IBinder getExcludeWindowTokens() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getExcludeWindowTokens();
        }
        return null;
    }

    public final Triple<List<View>, List<View>, List<View>> getExtraAnimationViews() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getExtraAnimationViews();
        }
        return null;
    }

    public final Float getFirstElementShowDistance() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getFirstElementShowDistance();
        }
        return null;
    }

    public final IHostAbility.LayoutConstraint getLayoutConstraint(Context context, SpanSize spanSize, boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getLayoutConstraint(context, spanSize, z);
        }
        return null;
    }

    public final List<ViewGroup> getQSContainers() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getQSContainers();
        }
        return null;
    }

    public final Rect getQSPageViewArea() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getQSPageViewArea();
        }
        return null;
    }

    public final Pair<List<Rect>, List<Rect>> getQSScrollXHotZone() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getQSScrollXHotZone();
        }
        return null;
    }

    public final View getSeparateQSFakeView() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getSeparateQsFakeView();
        }
        return null;
    }

    public final OplusSeparateQSPlugin getSeparateQSPlugin() {
        return this.separateQSPlugin;
    }

    public final View getStatusBarContainer() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getStatusBarContainer();
        }
        return null;
    }

    public final Integer getStatusBarTopSpacing() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.getStatusBarTopSpacing();
        }
        return null;
    }

    public final void handleIgnoreParentBoundsForScroll(boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.handleIgnoreParentBoundsForScroll(z);
        }
    }

    public final boolean isAnimationRunningInSeqEvent() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isAnimationRunningInSeqEvent();
        }
        return false;
    }

    public final boolean isBlurFractionAnimationRunning() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        return oplusSeparateQSPlugin != null && oplusSeparateQSPlugin.isBlurFractionAnimationRunning();
    }

    public final boolean isClosing() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isClosing();
        }
        return false;
    }

    public final boolean isCustomizing() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        return oplusSeparateQSPlugin != null && oplusSeparateQSPlugin.isCustomizing();
    }

    public final boolean isFractionAnimationRunning() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        return oplusSeparateQSPlugin != null && oplusSeparateQSPlugin.isFractionAnimationRunning();
    }

    public final boolean isFullyCollapsed() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isFullyCollapsed();
        }
        return true;
    }

    public final boolean isFullyExpanded() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isFullyExpanded();
        }
        return false;
    }

    public final boolean isQsAnimationRunning() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isQsAnimationRunning();
        }
        return false;
    }

    public final boolean isQsVisible() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.isQsVisible();
        }
        return false;
    }

    public final boolean onBackPressed() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.onBackPressed();
        }
        return false;
    }

    public final View onCreateQSPanelView(ViewGroup viewGroup) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            return oplusSeparateQSPlugin.onCreateQSPanelView(viewGroup);
        }
        return null;
    }

    public void onPluginConnected(OplusSeparateQSPlugin oplusSeparateQSPlugin, Context context) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin2;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onPluginConnected:" + oplusSeparateQSPlugin + ", pluginContext:" + context, null, 8);
        this.separateQSPlugin = oplusSeparateQSPlugin;
        if (this.proxy == null) {
            this.proxy = new SeparateQSHost(this.context);
        }
        OplusSeparateQSPlugin.IStub iStub = this.proxy;
        if (iStub != null && (oplusSeparateQSPlugin2 = this.separateQSPlugin) != null) {
            oplusSeparateQSPlugin2.bindStub(iStub);
        }
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusPanelViewPagerController$separateQSPluginListener$1) ((Listener) it.next())).onPluginAdded(oplusSeparateQSPlugin, this.context);
        }
        this.commandQueue.addCallback(this.commandQueueCallback);
        TileSpecSeparateQSRepository tileSpecSeparateQSRepository = this.sepTileSpecRepository;
        OplusSeparateQSPlugin oplusSeparateQSPlugin3 = this.separateQSPlugin;
        tileSpecSeparateQSRepository.setSepTileSpecRepository(oplusSeparateQSPlugin3 != null ? oplusSeparateQSPlugin3.getSepTileSpecRepository() : null);
    }

    @Deprecated
    public void onPluginDisconnected(OplusSeparateQSPlugin oplusSeparateQSPlugin) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onPluginDisconnected:" + oplusSeparateQSPlugin, null, 8);
        super.onPluginDisconnected(oplusSeparateQSPlugin);
        this.separateQSPlugin = null;
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusPanelViewPagerController$separateQSPluginListener$1) ((Listener) it.next())).onPluginRemove(oplusSeparateQSPlugin);
        }
        this.commandQueue.removeCallback(this.commandQueueCallback);
        this.sepTileSpecRepository.setSepTileSpecRepository(null);
    }

    public final void onSeparateModeChange(boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.onSeparateModeChange(z);
        }
    }

    public final void onSystemIconScaleChanged(float f) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.onSystemIconScaleChanged(f);
        }
    }

    public final void onUserRemoved(int i) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onUserRemoved:"), null, 8);
        if (i == 0 || (oplusSeparateQSPlugin = this.separateQSPlugin) == null) {
            return;
        }
        oplusSeparateQSPlugin.onUserRemoved(i);
    }

    public final Unit releaseAllSpring() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin == null) {
            return null;
        }
        oplusSeparateQSPlugin.releaseAllSpring();
        return Unit.INSTANCE;
    }

    public final void removeCallback(Listener listener) {
        if (this.callbacks.contains(listener)) {
            this.callbacks.remove(listener);
        }
    }

    public final void removeScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.removeScenarioAnimatorCallback(qSScenarioAnimListener);
        }
    }

    public final void setBrightnessMirrorController(BrightnessMirrorController brightnessMirrorController) {
        this.qSBrightnessFactory.setBrightnessMirrorController(brightnessMirrorController);
    }

    public final void setBrightnessMirrorVisible(boolean z) {
        this.qSBrightnessFactory.setBrightnessMirrorVisible(z);
    }

    public final void setClipToPaddingForScroll(boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.setClipToPaddingForScroll(z);
        }
    }

    public final void setScreenShotClick() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.setScreenShotClick();
        }
    }

    public final void setTouchAndAnimationDisabled(boolean z) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.setTouchAndAnimationDisabled(z);
        }
    }

    public final StateFlow shouldHideStatusBar() {
        return ((OplusQsGuideEventController) this.guideEventController.get()).shouldHideStatusBar();
    }

    public final void showDeviceMonitoringDialog() {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.showDeviceMonitoringDialog();
        }
    }

    public final void syncBlurFractionFromExternal(float f) {
        OplusSeparateQSPlugin oplusSeparateQSPlugin = this.separateQSPlugin;
        if (oplusSeparateQSPlugin != null) {
            oplusSeparateQSPlugin.syncBlurFractionFromExternal(f);
        }
    }
}
