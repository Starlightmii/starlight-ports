package com.oplus.systemui.subdisplay.biometrics;

import android.content.Context;
import android.hardware.face.FaceSensorPropertiesInternal;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.biometrics.AuthContainerView;
import com.android.systemui.biometrics.ParentAuthBiometricView;
import com.android.systemui.biometrics.Utils;
import com.android.systemui.biometrics.shared.model.PromptKind;
import com.android.systemui.biometrics.ui.CredentialView;
import com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel;
import com.android.systemui.biometrics.ui.viewmodel.SubCredentialViewAnimatorStyle;
import com.android.systemui.subdisplay.OplusSubAuthContainerViewEx;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.subdisplay.security.SubAnimationUtils;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSubAuthContainerViewExImpl extends OplusSubAuthContainerViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSubAuthContainerViewExImpl";
    private final Context context;
    private PromptKind credentialType = PromptKind.Pin.INSTANCE;
    public boolean isShowInSub;
    private EffectiveAnimationView lockView;
    private OplusSubAuthLockViewHelper subAuthLockViewHelper;
    private CredentialViewModel viewModel;

    public OplusSubAuthContainerViewExImpl(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getBackgroundColor() {
        if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            return -16777216;
        }
        return this.context.getColor(2131103777);
    }

    public static boolean isLockOrPasswordMode(LockPatternUtils lockPatternUtils, int i) {
        if (Intrinsics.areEqual(Utils.getCredentialType(lockPatternUtils, i), PromptKind.Password.INSTANCE)) {
            return true;
        }
        return lockPatternUtils.getLockoutEndTime(i).toMillis() - SystemClock.elapsedRealtime() > 0;
    }

    public boolean actionUseDeviceCredential(ParentAuthBiometricView parentAuthBiometricView, View view, LockPatternUtils lockPatternUtils, int i, Function0 function0) {
        if (!this.isShowInSub) {
            return false;
        }
        if (function0 != null) {
            function0.invoke();
        }
        if ((parentAuthBiometricView != null && parentAuthBiometricView.isAttachedToWindow()) || this.isShowInSub) {
            updateLockIconTintForCredential();
            if (parentAuthBiometricView != null) {
                parentAuthBiometricView.subAnimationExit(true, false, (Runnable) null);
            }
            boolean zIsLockOrPasswordMode = isLockOrPasswordMode(lockPatternUtils, i);
            CredentialViewModel credentialViewModel = this.viewModel;
            if (credentialViewModel != null) {
                credentialViewModel.setSubCredentialViewAnimateStyle(new SubCredentialViewAnimatorStyle(true, false, zIsLockOrPasswordMode, zIsLockOrPasswordMode, (Runnable) null));
            }
        }
        return true;
    }

    public boolean addBiometricView(FrameLayout frameLayout, ParentAuthBiometricView parentAuthBiometricView) {
        if (!this.isShowInSub) {
            return false;
        }
        if (parentAuthBiometricView == null) {
            return true;
        }
        frameLayout.addView(parentAuthBiometricView);
        return true;
    }

    public void addCredentialView(CredentialView credentialView, CredentialViewModel credentialViewModel, List<? extends FingerprintSensorPropertiesInternal> list, List<? extends FaceSensorPropertiesInternal> list2, int[] iArr) {
        this.viewModel = credentialViewModel;
        credentialView.setSensorProperties(Utils.findFirstSensorProperties(list, iArr), Utils.findFirstSensorProperties(list2, iArr));
        updateLockIconTintForCredential();
    }

    public ParentAuthBiometricView createSubAuthBiometricView(LayoutInflater layoutInflater, FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal, FaceSensorPropertiesInternal faceSensorPropertiesInternal) {
        if (!this.isShowInSub || (fingerprintSensorPropertiesInternal == null && faceSensorPropertiesInternal == null)) {
            return null;
        }
        if (Intrinsics.areEqual(this.credentialType, PromptKind.Password.INSTANCE)) {
            OplusSubAuthBiometricContinueView oplusSubAuthBiometricContinueViewInflate = layoutInflater.inflate(2131559405, (ViewGroup) null, false);
            oplusSubAuthBiometricContinueViewInflate.setSubAuthLockViewHelper(this.subAuthLockViewHelper);
            return oplusSubAuthBiometricContinueViewInflate;
        }
        OplusSubAuthBiometricView oplusSubAuthBiometricViewInflate = layoutInflater.inflate(2131559404, (ViewGroup) null, false);
        oplusSubAuthBiometricViewInflate.setSubAuthLockViewHelper(this.subAuthLockViewHelper);
        return oplusSubAuthBiometricViewInflate;
    }

    public View createSubCredentialView(PromptKind promptKind, LayoutInflater layoutInflater) {
        Log.d(TAG, "createSubCredentialView, isShowInSub=" + this.isShowInSub + ",credentialType=" + promptKind);
        if (!this.isShowInSub) {
            return null;
        }
        if (promptKind instanceof PromptKind.Pin) {
            return layoutInflater.inflate(2131559408, (ViewGroup) null, false);
        }
        if (promptKind instanceof PromptKind.Password) {
            return layoutInflater.inflate(2131559407, (ViewGroup) null, false);
        }
        if (promptKind instanceof PromptKind.Pattern) {
            return layoutInflater.inflate(2131559409, (ViewGroup) null, false);
        }
        return null;
    }

    public WindowManager createSubWindowManager(WindowManager windowManager, String str) {
        Context contextCreateWindowContext;
        if (this.isShowInSub) {
            Context subDisplayContext = OplusSubDisplayUtils.Companion.getInstance(this.context).getSubDisplayContext();
            Object systemService = null;
            if (subDisplayContext != null && (contextCreateWindowContext = subDisplayContext.createWindowContext(getWindowType(str), null)) != null) {
                systemService = contextCreateWindowContext.getSystemService("window");
            }
            if (systemService != null) {
                return (WindowManager) systemService;
            }
        }
        return windowManager;
    }

    public int getWindowType(String str) {
        if (Intrinsics.areEqual(str, "com.android.systemui")) {
            return 2020;
        }
        return this.isShowInSub ? 2038 : 2009;
    }

    public final boolean isShowInSub() {
        return OplusSubDisplayUtils.Companion.getInstance(this.context).getSubDisplayShow();
    }

    public void onAttachedToWindow(AuthContainerView authContainerView) {
        if (this.isShowInSub) {
            ViewTreeObserver viewTreeObserver = authContainerView.getViewTreeObserver();
            onAttachedToWindow.1 r1 = new onAttachedToWindow.1();
            r1.$authContainerView = authContainerView;
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            viewTreeObserver.addOnPreDrawListener(r1);
        }
    }

    public void onCreate(View view, ImageView imageView, ViewGroup viewGroup, LockPatternUtils lockPatternUtils, int i) {
        boolean subDisplayShow = OplusSubDisplayUtils.Companion.getInstance(this.context).getSubDisplayShow();
        this.isShowInSub = subDisplayShow;
        if (subDisplayShow) {
            view.setVisibility(0);
            imageView.setVisibility(0);
            EffectiveAnimationView effectiveAnimationViewFindViewById = viewGroup.findViewById(2131363640);
            this.lockView = effectiveAnimationViewFindViewById;
            if (effectiveAnimationViewFindViewById != null) {
                effectiveAnimationViewFindViewById.setVisibility(0);
                this.subAuthLockViewHelper = new OplusSubAuthLockViewHelper(effectiveAnimationViewFindViewById);
            }
            this.credentialType = Utils.getCredentialType(lockPatternUtils, i);
        }
    }

    public boolean startSubEnterAnimation(ParentAuthBiometricView parentAuthBiometricView, View view, LockPatternUtils lockPatternUtils, int i, Runnable runnable) {
        if (!this.isShowInSub) {
            return false;
        }
        if (parentAuthBiometricView != null) {
            OplusSubAuthLockViewHelper oplusSubAuthLockViewHelper = this.subAuthLockViewHelper;
            if (oplusSubAuthLockViewHelper != null) {
                oplusSubAuthLockViewHelper.applyTint(-1);
            }
            parentAuthBiometricView.subAnimationEnter(runnable);
            return true;
        }
        if (view == null) {
            return true;
        }
        updateLockIconTintForCredential();
        CredentialViewModel credentialViewModel = this.viewModel;
        if (credentialViewModel == null) {
            return true;
        }
        credentialViewModel.setSubCredentialViewAnimateStyle(new SubCredentialViewAnimatorStyle(true, false, false, isLockOrPasswordMode(lockPatternUtils, i), runnable));
        return true;
    }

    public boolean subAnimateAway(ParentAuthBiometricView parentAuthBiometricView, View view, int i, LockPatternUtils lockPatternUtils, int i2, AuthContainerView authContainerView, Runnable runnable) {
        long j;
        if (!this.isShowInSub) {
            return false;
        }
        boolean z = i == 4 || i == 7;
        if (view == null || !view.isAttachedToWindow()) {
            j = 0;
        } else {
            CredentialViewModel credentialViewModel = this.viewModel;
            if (credentialViewModel != null) {
                credentialViewModel.setSubCredentialViewAnimateStyle(new SubCredentialViewAnimatorStyle(false, z, false, isLockOrPasswordMode(lockPatternUtils, i2), runnable));
            }
            j = 100;
        }
        EffectiveAnimationView effectiveAnimationView = this.lockView;
        if (effectiveAnimationView != null) {
            SubAnimationUtils.bgAndLockViewAnimationExit(authContainerView, effectiveAnimationView, j, (Function0) null).start();
        }
        if (parentAuthBiometricView != null && parentAuthBiometricView.isAttachedToWindow()) {
            parentAuthBiometricView.subAnimationExit(false, z, runnable);
        }
        return true;
    }

    public final void updateLockIconTintForCredential() {
        OplusSubAuthLockViewHelper oplusSubAuthLockViewHelper = this.subAuthLockViewHelper;
        if (oplusSubAuthLockViewHelper != null) {
            oplusSubAuthLockViewHelper.applyTintForCredentialBackground();
        }
        OplusSubAuthLockViewHelper oplusSubAuthLockViewHelper2 = this.subAuthLockViewHelper;
        if (oplusSubAuthLockViewHelper2 != null) {
            oplusSubAuthLockViewHelper2.updateState(9);
        }
    }
}
