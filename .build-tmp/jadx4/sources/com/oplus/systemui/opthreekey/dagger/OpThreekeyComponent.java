package com.oplus.systemui.opthreekey.dagger;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateListener;
import com.oplus.systemui.opthreekey.seeding.OpThreeKeySeedling;
import com.oplus.systemui.opthreekey.tips.OpThreekeyDialogImpl;
import com.oplus.systemui.opthreekey.utils.ConvertThreeKeyStateUtil;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OpThreekeyComponent {
    public final KeyguardViewMediator mKeyguardViewMediator;
    public final OpThreekeyDialogImpl mThreekeyDialog;

    public OpThreekeyComponent(Context context, Looper looper, KeyguardViewMediator keyguardViewMediator, ConfigurationController configurationController, ScreenLifecycle screenLifecycle, SeedlingPluginManager seedlingPluginManager, OpThreeKeySeedling opThreeKeySeedling, WakefulnessLifecycle wakefulnessLifecycle) {
        this.mKeyguardViewMediator = keyguardViewMediator;
        Log.d("OpThreekeyComponent", "OpThreekeyComponent init");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusAlertSlider()) {
            OpThreekeyDialogImpl opThreekeyDialogImpl = this.mThreekeyDialog;
            if (opThreekeyDialogImpl != null) {
                opThreekeyDialogImpl.mConfigurationController.removeCallback(opThreekeyDialogImpl);
                opThreekeyDialogImpl.mWakefulnessLifecycle.removeObserver(opThreekeyDialogImpl.mWakefulnessObserver);
                ThreeKeyStateListener.getInstance().removeObserver(opThreekeyDialogImpl.mContext, opThreekeyDialogImpl);
                ScreenLifecycle screenLifecycle2 = opThreekeyDialogImpl.mScreenLifecycle;
                if (screenLifecycle2 != null) {
                    screenLifecycle2.removeObserver(opThreekeyDialogImpl);
                }
                SmoothTransitionController.getInstance(opThreekeyDialogImpl.mContext).unregisterSmoothTransitionCallback(opThreekeyDialogImpl.mKeyguardStyleServiceCallback);
            }
            OpThreekeyDialogImpl opThreekeyDialogImpl2 = new OpThreekeyDialogImpl(context, configurationController, screenLifecycle, seedlingPluginManager, opThreeKeySeedling, wakefulnessLifecycle);
            this.mThreekeyDialog = opThreekeyDialogImpl2;
            opThreekeyDialogImpl2.mWindowType = 2020;
            opThreekeyDialogImpl2.mContext.getResources().getConfiguration();
            opThreekeyDialogImpl2.mListener = this;
            ScreenLifecycle screenLifecycle3 = opThreekeyDialogImpl2.mScreenLifecycle;
            if (screenLifecycle3 != null) {
                ((Lifecycle) screenLifecycle3).mObservers.add(opThreekeyDialogImpl2);
            }
            ThreeKeyStateListener.getInstance().addObserver(opThreekeyDialogImpl2.mContext, opThreekeyDialogImpl2);
            opThreekeyDialogImpl2.mConfigurationController.addCallback(opThreekeyDialogImpl2);
            opThreekeyDialogImpl2.mWakefulnessLifecycle.addObserver(opThreekeyDialogImpl2.mWakefulnessObserver);
            opThreekeyDialogImpl2.mThreeKeystate = ConvertThreeKeyStateUtil.convertThreeKeyStatus(ThreeKeyStateListener.getThreeKeyMode(opThreekeyDialogImpl2.mContext));
            if (ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH.equals(VolumeFeatureOption.INSTANCE.getVolumeThreekeyLocation()) && DeviceStateController.getInstance().isFolding()) {
                opThreekeyDialogImpl2.isRightTk = true;
            }
            SmoothTransitionController.getInstance(opThreekeyDialogImpl2.mContext).registerSmoothTransitionCallback(opThreekeyDialogImpl2.mKeyguardStyleServiceCallback, false, true);
            opThreekeyDialogImpl2.initDialog();
        }
    }
}
