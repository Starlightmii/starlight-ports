package com.oplus.systemui.volume.dagger;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.PowerManager;
import android.os.UserHandle;
import android.util.SparseBooleanArray;
import android.view.ContextThemeWrapper;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Prefs;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.model.SysUiState;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.topui.TopUiController;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.android.systemui.volume.OplusVolumeUIEx;
import com.oplus.osense.OsenseResClient;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.subdisplay.dialog.DialogContentUpdateToSecondaryHomeController;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeDialogImpl$$ExternalSyntheticLambda0;
import com.oplus.systemui.volume.OplusVolumeDialogImpl$$ExternalSyntheticLambda2;
import com.oplus.systemui.volume.OplusVolumeDialogImpl$$ExternalSyntheticLambda4;
import com.oplus.systemui.volume.OplusVolumeDialogImpl.H;
import com.oplus.systemui.volume.OplusVolumeSnackBarController;
import com.oplus.systemui.volume.ability.IconAbility;
import com.oplus.systemui.volume.ability.VibrateAbility;
import com.oplus.systemui.volume.manager.VolumeManager;
import com.oplus.systemui.volume.manager.VolumeSupplyExportApi;
import com.oplus.systemui.volume.repository.AudioDataRepository;
import com.oplus.systemui.volume.repository.MultiMediaRepository;
import com.oplus.systemui.volume.repository.OplusVolumeRepository;
import com.oplus.systemui.volume.repository.RingModeRepository;
import com.oplus.systemui.volume.repository.SafetyWarningRepository;
import com.oplus.systemui.volume.repository.SuperVolumeRepository;
import com.oplus.systemui.volume.subDisplay.VolumeDialogImplSubDisplay;
import com.oplus.systemui.volume.subDisplay.VolumeDialogImplSubDisplay$;
import com.oplus.systemui.volume.utils.OplusRMChangeVibrateHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface VolumeModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final VolumeDialogImplSubDisplay provideSubVolumeDialog(Context context, VolumeDialogController volumeDialogController, AccessibilityManagerWrapper accessibilityManagerWrapper, ConfigurationController configurationController, DialogContentUpdateToSecondaryHomeController dialogContentUpdateToSecondaryHomeController) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isFoldDevice() || !FeatureOption.isFoldRemapDisabled()) {
                return null;
            }
            VolumeDialogImplSubDisplay volumeDialogImplSubDisplay = new VolumeDialogImplSubDisplay();
            volumeDialogImplSubDisplay.mHandler = new VolumeDialogImplSubDisplay.H(volumeDialogImplSubDisplay);
            volumeDialogImplSubDisplay.mRows = new ArrayList();
            VolumeDialogImplSubDisplay$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = volumeDialogImplSubDisplay;
            VarHandle.storeStoreFence();
            volumeDialogImplSubDisplay.mA11yServicesStateChangeListener = externalSyntheticLambda1;
            volumeDialogImplSubDisplay.mSafetyWarningLock = new Object();
            volumeDialogImplSubDisplay.mActiveStream = -1;
            volumeDialogImplSubDisplay.mPrevActiveStream = -1;
            volumeDialogImplSubDisplay.mAutomute = true;
            volumeDialogImplSubDisplay.mSilentMode = true;
            volumeDialogImplSubDisplay.mTempImportantStrem = -1;
            volumeDialogImplSubDisplay.mThreeKeystate = -1;
            volumeDialogImplSubDisplay.mSubDisplayRotation = 0;
            volumeDialogImplSubDisplay.mThemeIconColor = 0;
            volumeDialogImplSubDisplay.mThemeTextColor = 0;
            volumeDialogImplSubDisplay.mThemeBgColor = 0;
            volumeDialogImplSubDisplay.viewOutlineProvider = new VolumeDialogImplSubDisplay.AnonymousClass1();
            VolumeDialogImplSubDisplay$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda2();
            externalSyntheticLambda2.f$0 = volumeDialogImplSubDisplay;
            VarHandle.storeStoreFence();
            volumeDialogImplSubDisplay.mStateChangedListener = externalSyntheticLambda2;
            VolumeDialogImplSubDisplay$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new VolumeDialogImplSubDisplay$.ExternalSyntheticLambda3();
            externalSyntheticLambda3.f$0 = volumeDialogImplSubDisplay;
            VarHandle.storeStoreFence();
            volumeDialogImplSubDisplay.mBlurEnabledListener = externalSyntheticLambda3;
            VolumeDialogImplSubDisplay.6 r0 = new VolumeDialogImplSubDisplay.6();
            r0.this$0 = volumeDialogImplSubDisplay;
            VarHandle.storeStoreFence();
            volumeDialogImplSubDisplay.mControllerCallbackH = r0;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 2132020277);
            Configuration configuration = new Configuration(contextThemeWrapper.getResources().getConfiguration());
            configuration.densityDpi = 296;
            volumeDialogImplSubDisplay.mContext = contextThemeWrapper.createContextAsUser(new UserHandle(OsBinderCacheUtils.getCurrentUserId()), 1).createConfigurationContext(configuration);
            volumeDialogImplSubDisplay.mResourceContext = context;
            Context subDisplayContext = AodUtils.getSubDisplayContext(context);
            volumeDialogImplSubDisplay.mContext = subDisplayContext;
            volumeDialogImplSubDisplay.mController = volumeDialogController;
            volumeDialogImplSubDisplay.mOplusVolumeController = ((OplusVolumeUIEx) DependencyEx.sDependency.getDependency(OplusVolumeUIEx.class)).getOplusVolumeController(subDisplayContext, volumeDialogController);
            volumeDialogImplSubDisplay.mVibrateAbility = new VibrateAbility(context);
            volumeDialogImplSubDisplay.mKeyguard = (KeyguardManager) subDisplayContext.getSystemService(SeparateQSStatisicUtil.VALUE_KEYGUARD);
            volumeDialogImplSubDisplay.mAccessibilityMgr = accessibilityManagerWrapper;
            volumeDialogImplSubDisplay.mAudioManager = (AudioManager) subDisplayContext.getSystemService("audio");
            volumeDialogImplSubDisplay.mOplusSubDisplayUtils = new OplusSubDisplayUtils(context);
            volumeDialogImplSubDisplay.mPowerManager = (PowerManager) subDisplayContext.getSystemService("power");
            volumeDialogImplSubDisplay.mBlurRadius = GlobalActionsUtils.dp2px(subDisplayContext, 42.857143f);
            volumeDialogImplSubDisplay.mDisplayManager = new DisplayManager(subDisplayContext);
            volumeDialogImplSubDisplay.mConfigurationController = configurationController;
            volumeDialogImplSubDisplay.mSysUiState = (SysUiState) Dependency.sDependency.getDependencyInner(SysUiState.class);
            volumeDialogImplSubDisplay.mDialogContentUpdateToSecondaryHomeController = dialogContentUpdateToSecondaryHomeController;
            VarHandle.storeStoreFence();
            return volumeDialogImplSubDisplay;
        }

        public final VolumeDialog provideVolumeDialog(Context context, AccessibilityManagerWrapper accessibilityManagerWrapper, ConfigurationController configurationController, ActivityStarter activityStarter, UserContextProvider userContextProvider, DumpManager dumpManager, KeyguardStateController keyguardStateController, LocalBluetoothManager localBluetoothManager, VolumeManager volumeManager, OplusRMChangeVibrateHelper oplusRMChangeVibrateHelper, Executor executor, TopUiController topUiController) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = new OplusVolumeDialogImpl();
            OplusVolumeDialogImpl.H h = oplusVolumeDialogImpl.new H();
            oplusVolumeDialogImpl.mHandler = h;
            oplusVolumeDialogImpl.mRows = new ArrayList();
            oplusVolumeDialogImpl.mDynamic = new SparseBooleanArray();
            OplusVolumeDialogImpl.SetTopUiRunnable setTopUiRunnable = new OplusVolumeDialogImpl.SetTopUiRunnable();
            setTopUiRunnable.mStatusBarController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
            setTopUiRunnable.mHasTopUi = true;
            VarHandle.storeStoreFence();
            oplusVolumeDialogImpl.mSetTopUiRunnable = setTopUiRunnable;
            OplusVolumeDialogImpl$$ExternalSyntheticLambda2 oplusVolumeDialogImpl$$ExternalSyntheticLambda2 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda2();
            oplusVolumeDialogImpl$$ExternalSyntheticLambda2.f$0 = oplusVolumeDialogImpl;
            VarHandle.storeStoreFence();
            oplusVolumeDialogImpl.mA11yServicesStateChangeListener = oplusVolumeDialogImpl$$ExternalSyntheticLambda2;
            oplusVolumeDialogImpl.mActiveStream = -1;
            oplusVolumeDialogImpl.mAutomute = true;
            oplusVolumeDialogImpl.mSilentMode = true;
            oplusVolumeDialogImpl.mConfigChanged = false;
            oplusVolumeDialogImpl.mIsMultiRowShowing = false;
            oplusVolumeDialogImpl.mSettingsButtonClickTime = "";
            oplusVolumeDialogImpl.mSettingsButtonClickCount = 0;
            oplusVolumeDialogImpl.mIsCaptionComponentEnabled = false;
            oplusVolumeDialogImpl.isAnimationStart = true;
            oplusVolumeDialogImpl.mNowScreenOrientation = 0;
            oplusVolumeDialogImpl.mInterruptTimeoutReasonCode = -1;
            oplusVolumeDialogImpl.firstShowUI = true;
            oplusVolumeDialogImpl.mDelaySliderShowForUserSwitch = false;
            OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda0(1);
            oplusVolumeDialogImpl$$ExternalSyntheticLambda0.f$0 = oplusVolumeDialogImpl;
            VarHandle.storeStoreFence();
            oplusVolumeDialogImpl.mUserSwitchDelayedSliderShow = oplusVolumeDialogImpl$$ExternalSyntheticLambda0;
            oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = false;
            OplusVolumeDialogImpl.AnonymousClass1 anonymousClass1 = new OplusVolumeDialogImpl.AnonymousClass1();
            anonymousClass1.this$0 = oplusVolumeDialogImpl;
            VarHandle.storeStoreFence();
            oplusVolumeDialogImpl.mLaunchSoundSettingsAfterDismissDelay = anonymousClass1;
            oplusVolumeDialogImpl.mIsIconClicked = false;
            oplusVolumeDialogImpl.mPreventSeekBar = false;
            OplusVolumeDialogImpl$$ExternalSyntheticLambda4 oplusVolumeDialogImpl$$ExternalSyntheticLambda4 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda4();
            oplusVolumeDialogImpl$$ExternalSyntheticLambda4.f$0 = oplusVolumeDialogImpl;
            VarHandle.storeStoreFence();
            oplusVolumeDialogImpl.mStateChangedListener = oplusVolumeDialogImpl$$ExternalSyntheticLambda4;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 2132020277);
            oplusVolumeDialogImpl.mContext = contextThemeWrapper;
            VolumeDialogController volumeDialogController = (VolumeDialogController) Dependency.sDependency.getDependencyInner(VolumeDialogController.class);
            oplusVolumeDialogImpl.mController = volumeDialogController;
            OplusVolumeControllerEx oplusVolumeController = ((OplusVolumeUIEx) DependencyEx.sDependency.getDependency(OplusVolumeUIEx.class)).getOplusVolumeController(contextThemeWrapper, volumeDialogController);
            oplusVolumeDialogImpl.mOplusVolumeController = oplusVolumeController;
            oplusVolumeDialogImpl.mOplusVolumeSnackBarController = OplusVolumeSnackBarController.Companion.getInstance(contextThemeWrapper);
            if (oplusVolumeDialogImpl.mOplusVolumeRepository == null) {
                oplusVolumeDialogImpl.mOplusVolumeRepository = new OplusVolumeRepository(contextThemeWrapper);
            }
            OplusVolumeRepository oplusVolumeRepository = oplusVolumeDialogImpl.mOplusVolumeRepository;
            oplusVolumeRepository.setViewImpl(oplusVolumeDialogImpl);
            oplusVolumeRepository.setController(volumeDialogController, oplusVolumeController);
            oplusVolumeDialogImpl.mSuperVolumeRepository = new SuperVolumeRepository(contextThemeWrapper, oplusVolumeDialogImpl);
            new RingModeRepository(contextThemeWrapper, oplusVolumeDialogImpl);
            oplusVolumeDialogImpl.mMultiMediaRepository = new MultiMediaRepository(contextThemeWrapper, oplusVolumeDialogImpl);
            oplusVolumeDialogImpl.mAudioDataRepository = new AudioDataRepository(contextThemeWrapper, oplusVolumeDialogImpl, executor);
            oplusVolumeDialogImpl.mSafetyWarningRepository = new SafetyWarningRepository(contextThemeWrapper, oplusVolumeDialogImpl);
            oplusVolumeDialogImpl.mVibrateAbility = new VibrateAbility(contextThemeWrapper);
            oplusVolumeDialogImpl.mIconAbility = new IconAbility(contextThemeWrapper, oplusVolumeDialogImpl);
            oplusVolumeDialogImpl.mKeyguard = (KeyguardManager) contextThemeWrapper.getSystemService(SeparateQSStatisicUtil.VALUE_KEYGUARD);
            oplusVolumeDialogImpl.mDeviceProvisionedController = (DeviceProvisionedController) Dependency.sDependency.getDependencyInner(DeviceProvisionedController.class);
            oplusVolumeDialogImpl.mAudioManager = (AudioManager) contextThemeWrapper.getSystemService("audio");
            oplusVolumeDialogImpl.mHasSeenODICaptionsTooltip = Prefs.getBoolean(context, "HasSeenODICaptionsTooltip", false);
            oplusVolumeDialogImpl.mOsenseResClient = OsenseResClient.get(OplusVolumeDialogImpl.class);
            VolumeSupplyExportApi.INSTANCE.setOplusVolumeDialogImpl(oplusVolumeDialogImpl);
            oplusVolumeDialogImpl.mUserContextProvider = userContextProvider;
            oplusVolumeDialogImpl.mConfigurationController = configurationController;
            oplusVolumeDialogImpl.mAccessibilityMgr = accessibilityManagerWrapper;
            oplusVolumeDialogImpl.mBluetoothManager = localBluetoothManager;
            oplusVolumeDialogImpl.mactivityStarter = activityStarter;
            oplusVolumeDialogImpl.mKeyguardStateController = keyguardStateController;
            oplusVolumeDialogImpl.mVolumeManager = volumeManager;
            oplusVolumeDialogImpl.mOplusRMChangeVibrateHelper = oplusRMChangeVibrateHelper;
            setTopUiRunnable.setTopUiController(topUiController);
            if (dumpManager != null) {
                dumpManager.registerDumpable(oplusVolumeDialogImpl);
            }
            VarHandle.storeStoreFence();
            h.obtainMessage(5, 1, 0).sendToTarget();
            oplusVolumeDialogImpl.setAutomute(true);
            oplusVolumeDialogImpl.setSilentMode(false);
            return oplusVolumeDialogImpl;
        }
    }
}
