package com.oplus.systemui.volume.repository;

import android.media.AudioManager;
import android.os.Message;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.VolumeDialogController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeDialogInterface;
import com.oplus.systemui.volume.observer.MultiMediaSettingsObserver;
import com.oplus.systemui.volume.utils.MultiVolumeKeyStateListener;
import com.oplus.systemui.volume.utils.VolumeStatisticsUtil;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeRepository$mControllerCallbackH$1 implements VolumeDialogController.Callbacks {
    public /* synthetic */ OplusVolumeRepository this$0;

    public final void onAccessibilityModeChanged(Boolean bool) {
        bool.getClass();
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).onAccessibilityModeChanged(bool);
        }
    }

    public final void onCaptionComponentStateChanged(Boolean bool, Boolean bool2) {
        boolean zBooleanValue = bool.booleanValue();
        bool2.getClass();
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            oplusVolumeDialogImpl.mIsCaptionComponentEnabled = zBooleanValue;
            oplusVolumeDialogImpl.mOplusVolumeDialogView.updateODICaptionsH(zBooleanValue);
        }
    }

    public final /* bridge */ /* synthetic */ void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2) {
        bool.booleanValue();
        bool2.booleanValue();
    }

    public final void onConfigurationChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeRepository-->onConfigurationChanged ");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "onConfigurationChanged ");
            oplusVolumeDialogImpl.mPendingLandscapeExpandedSettingsLaunch = false;
            oplusVolumeDialogImpl.mHandler.removeCallbacks(oplusVolumeDialogImpl.mLaunchSoundSettingsAfterDismissDelay);
            if (oplusVolumeDialogImpl.isShowing()) {
                oplusVolumeDialogImpl.mOplusVolumeDialogView.getVolumeDialog().dismiss();
            }
            if (oplusVolumeDialogImpl.mSingleAppVolumeButtonViewController.isShowing()) {
                oplusVolumeDialogImpl.mSingleAppVolumeButtonViewController.hideImmediately(true);
            }
            oplusVolumeDialogImpl.mConfigChanged = true;
            if (oplusVolumeDialogImpl.mDialogShowingForMultiMedia && oplusVolumeDialogImpl.mNowScreenOrientation != oplusVolumeDialogImpl.mContext.getResources().getConfiguration().orientation) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
                    ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusVolumeDialogImpl.mNowScreenOrientation, LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", new StringBuilder("onConfigurationChanged mNowScreenOrientation:"));
                    MultiMediaRepository multiMediaRepository = oplusVolumeDialogImpl.mMultiMediaRepository;
                    multiMediaRepository.getClass();
                    MultiMediaRepository.AnonymousClass1 anonymousClass1 = new MultiMediaRepository.AnonymousClass1(1);
                    anonymousClass1.this$0 = multiMediaRepository;
                    VarHandle.storeStoreFence();
                    SystemUIThreadUtil.runOnUiThread(anonymousClass1);
                }
            }
            if (oplusVolumeDialogImpl.mDialogShowingForMultiMedia && oplusVolumeDialogImpl.mNowScreenOrientation != oplusVolumeDialogImpl.mContext.getResources().getConfiguration().orientation) {
                VolumeFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.isOplusSuperVolume()) {
                    for (int i : SuperVolumeRepository.Companion.getMSuperVolumeRows()) {
                        OplusVolumeRow oplusVolumeRowFindRow = oplusVolumeDialogImpl.findRow(i);
                        if (oplusVolumeRowFindRow == null) {
                            LogUtil.e(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "SuperVolume VolumeRow is null!");
                            return;
                        }
                        if (!oplusVolumeRowFindRow.isMultiMediaVolume) {
                            oplusVolumeRowFindRow.setVerticalLayout(true);
                        }
                        oplusVolumeDialogImpl.mIconAbility.updateVolumeRowH(oplusVolumeRowFindRow, false, oplusVolumeDialogImpl.mState);
                    }
                }
            }
            oplusVolumeDialogImpl.mIsMultiRowShowing = false;
            synchronized (oplusVolumeDialogImpl.mSafetyWarningRepository.getSafetyWarningLock()) {
                try {
                    if (oplusVolumeDialogImpl.mSafetyWarningRepository.getSafetyWarning() != null && oplusVolumeDialogImpl.mSafetyWarningRepository.getSafetyWarning().getSafetyWarningDialog().isShowing()) {
                        oplusVolumeDialogImpl.mSafetyWarningRepository.setSafetyWarningPadding();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void onDateChanged() {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusVolumeDialogImpl.mSettingsButtonClickCount, LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", new StringBuilder("onDataChanged: volume settings button click times:"));
            int i = oplusVolumeDialogImpl.mSettingsButtonClickCount;
            if (i > 0) {
                VolumeStatisticsUtil.uploadBtnClickEvent(oplusVolumeDialogImpl.mContext, oplusVolumeDialogImpl.mSettingsButtonClickTime, String.valueOf(i));
                oplusVolumeDialogImpl.mSettingsButtonClickCount = 0;
                oplusVolumeDialogImpl.mSettingsButtonClickTime = "";
            }
        }
    }

    public final void onDismissRequested(int i) {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).dismiss(i);
        }
    }

    public final void onInitStateChange(VolumeDialogController.State state, AudioManager audioManager) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(state.ringerModeInternal, "onStateChanged onInitStateChange ringerModeInternal:", "OplusVolumeRepository-->", "SystemUi--Volume");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).onStateChangedH(state);
        }
    }

    public final void onLayoutDirectionChanged(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onLayoutDirectionChanged ", "OplusVolumeRepository-->", "SystemUi--Volume");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "onLayoutDirectionChanged ", LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl");
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && oplusVolumeDialogImpl.mDialogShowingForMultiMedia) {
                MultiMediaRepository multiMediaRepository = oplusVolumeDialogImpl.mMultiMediaRepository;
                multiMediaRepository.getClass();
                MultiMediaRepository.AnonymousClass1 anonymousClass1 = new MultiMediaRepository.AnonymousClass1(1);
                anonymousClass1.this$0 = multiMediaRepository;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(anonymousClass1);
            }
        }
    }

    public final void onScreenOff() {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).dismiss(4);
        }
    }

    public final void onShowCsdWarning(int i, int i2) {
    }

    public final void onShowRequested(int i, boolean z, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onShowRequested reason:", "OplusVolumeRepository-->", "SystemUi--Volume");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "show");
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).showH(i, false);
        }
    }

    public final void onShowSafetyWarning(int i) {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            if (FeatureOption.isGameMlbbSupport()) {
                oplusVolumeDialogImpl.mController.getAudioManager().disableSafeMediaVolume();
                LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "don't show volume dialog for MLBB version");
            } else if (oplusVolumeDialogImpl.mOplusVolumeRepository.shouldShow()) {
                oplusVolumeDialogImpl.mSafetyWarningRepository.showSafetyWarningH(i);
            }
        }
    }

    public final void onShowSilentHint() {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            if (oplusVolumeDialogImpl.mSilentMode) {
                oplusVolumeDialogImpl.mController.setRingerMode(2, false);
            }
        }
    }

    public final void onShowVibrateHint() {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            if (oplusVolumeDialogImpl.mSilentMode) {
                oplusVolumeDialogImpl.mController.setRingerMode(0, false);
            }
        }
    }

    public final void onStateChanged(VolumeDialogController.State state, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onStateChanged onStateChanged stream:", i, state.ringerModeInternal, " ringerModeInternal:", "OplusVolumeRepository-->"));
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).onStateChangedH(state, i);
        }
    }

    public final void onThemeChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeRepository-->theme change");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).onThemeChanged();
        }
    }

    public final void onVolumeChangedFromKey() {
    }

    public final void onVolumeKeyStateChange(boolean z) {
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            int screenState = ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState();
            boolean z2 = MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled;
            LogUtil.i(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", KeyguardLogger$.ExternalSyntheticOutline0.m("onVolumeKeyStateChange isMultiMediaSettingsEnable:", z2, ",screenState:", screenState));
            VolumeDialogController.State state = oplusVolumeDialogImpl.mState;
            if (state != null && state.zenMode == 1 && state.disallowMedia) {
                TemporarilyInfoManager.Companion.getInstance().show(oplusVolumeDialogImpl.mContext.getString(2131956974), null, String.valueOf(2131956974), new TemporarilyInfoManager.InfoShowStyle(14.0f, 2, QsColorUtil.getFooterTextColor(oplusVolumeDialogImpl.mContext), 1500L, true));
            }
            if (z2 && screenState == 2) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                boolean z3 = VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && !oplusVolumeDialogImpl.mMultiMediaRepository.getMultiMediaRows().isEmpty();
                MultiVolumeKeyStateListener.Companion companion = MultiVolumeKeyStateListener.Companion;
                boolean keyEventState = companion.getInstance(oplusVolumeDialogImpl.mContext).getKeyEventState();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onVolumeKeyStateChange state:", " needShowVolumeDialog:", " volumeKeyPressed:", z, z3);
                sbM.append(keyEventState);
                sbM.append(" mMultiMediaRows.size:");
                LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", sbM.toString());
                if (z && keyEventState && z3) {
                    oplusVolumeDialogImpl.showH(1, true);
                } else {
                    if (z) {
                        return;
                    }
                    companion.getInstance(oplusVolumeDialogImpl.mContext).setKeyEventState(false);
                }
            }
        }
    }

    public final void onVolumeKeyUp() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeRepository-->This is not a absolutely accurate notification that volume key is unpressed!");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) oplusVolumeDialogInterface;
            OplusVolumeDialogImpl.H h = oplusVolumeDialogImpl.mHandler;
            LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "This is not a absolutely accurate notification that volume key is unpressed!");
            if (oplusVolumeDialogImpl.isShowing()) {
                h.removeMessages(8);
                Message messageObtain = Message.obtain();
                messageObtain.what = 8;
                h.sendMessageDelayed(messageObtain, 150L);
            }
        }
    }

    public final void onStateChanged(VolumeDialogController.State state) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(state.ringerModeInternal, "onStateChanged onStateChangedH ringerModeInternal:", "OplusVolumeRepository-->", "SystemUi--Volume");
        }
        OplusVolumeDialogInterface oplusVolumeDialogInterface = this.this$0.mOplusVolumeDialogInterface;
        if (oplusVolumeDialogInterface != null) {
            ((OplusVolumeDialogImpl) oplusVolumeDialogInterface).onStateChangedH(state);
        }
    }
}
