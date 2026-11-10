package com.oplus.systemui.toast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.INotificationManager;
import android.app.ITransientNotificationCallback;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.biometrics.IBiometricContextListener;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ServiceManager;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.IWindowManager;
import android.view.View;
import android.view.WindowManagerGlobal;
import android.view.accessibility.IAccessibilityManager;
import android.widget.ToastPresenter;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.systemui.CoreStartable;
import com.android.systemui.people.PeopleBackupFollowUpJob$;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.toast.OplusToastLogger;
import com.oplus.systemui.common.helper.ResponsiveUIModelHelper;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusToastUI implements CoreStartable, ConfigurationController.ConfigurationListener, CommandQueue.Callbacks {
    public ITransientNotificationCallback mCallback;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public int mDensity;
    public final IAccessibilityManager mIAccessibilityManager;
    public final INotificationManager mNotificationManager;
    public int mOrientation;
    public boolean mOrientationChange;
    public ToastPresenter mPresenter;
    public int mSmallestScreenWidth;
    public int mSubDisPlayId;
    public Binder mSubWindowToken;
    public OplusSystemUIToast mToast;
    public final OplusToastFactory mToastFactory;
    public ToastItem mToastItem;
    public final OplusToastLogger mToastLogger;
    public ToastOutAnimatorListener mToastOutAnimatorListener;
    public ResponsiveUIModelHelper mUiModeHelper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ToastItem {
        public boolean mCancelled;
        public IBinder mCurrentWindowToken;
        public Context mDisplayContext;
        public int mDisplayId;
        public String mPackageName;
        public CharSequence mText;
        public IBinder mToken;
        public int mUid;
        public /* synthetic */ OplusToastUI this$0;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ToastOutAnimatorListener extends AnimatorListenerAdapter {
        public final ITransientNotificationCallback mPrevCallback;
        public final ToastPresenter mPrevPresenter;
        public Runnable mShowNextToastRunnable;

        public ToastOutAnimatorListener(ToastPresenter toastPresenter, ITransientNotificationCallback iTransientNotificationCallback, Runnable runnable) {
            this.mPrevPresenter = toastPresenter;
            this.mPrevCallback = iTransientNotificationCallback;
            this.mShowNextToastRunnable = runnable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.mPrevPresenter.hide(this.mPrevCallback);
            Runnable runnable = this.mShowNextToastRunnable;
            if (runnable != null) {
                runnable.run();
            }
            OplusToastUI.this.mToastOutAnimatorListener = null;
        }

        public void setShowNextToastRunnable(Runnable runnable) {
            this.mShowNextToastRunnable = runnable;
        }
    }

    public OplusToastUI(Context context, CommandQueue commandQueue, OplusToastFactory oplusToastFactory, OplusToastLogger oplusToastLogger) {
        INotificationManager iNotificationManagerAsInterface = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        IAccessibilityManager iAccessibilityManagerAsInterface = IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
        this.mOrientation = 1;
        this.mDensity = 0;
        this.mSmallestScreenWidth = 0;
        this.mOrientationChange = false;
        this.mSubDisPlayId = 0;
        this.mToastItem = null;
        this.mContext = context;
        this.mCommandQueue = commandQueue;
        this.mNotificationManager = iNotificationManagerAsInterface;
        this.mIAccessibilityManager = iAccessibilityManagerAsInterface;
        this.mToastFactory = oplusToastFactory;
        this.mToastLogger = oplusToastLogger;
    }

    public final void hideCurrentToast$1(OplusToastUI$$ExternalSyntheticLambda0 oplusToastUI$$ExternalSyntheticLambda0) {
        OplusSystemUIToast oplusSystemUIToast = this.mToast;
        if (oplusSystemUIToast != null) {
            View view = oplusSystemUIToast.mToastView;
            if (view instanceof OplusToastLayout) {
                ((OplusToastLayout) view).setVisibilityChangedListener(null);
            }
            Animator animator = oplusSystemUIToast.mInAnimator;
            if (animator != null && animator.isRunning()) {
                oplusSystemUIToast.mInAnimator.cancel();
            }
            if (this.mToast.getOutAnimation() != null) {
                Animator outAnimation = this.mToast.getOutAnimation();
                ToastOutAnimatorListener toastOutAnimatorListener = new ToastOutAnimatorListener(this.mPresenter, this.mCallback, oplusToastUI$$ExternalSyntheticLambda0);
                this.mToastOutAnimatorListener = toastOutAnimatorListener;
                outAnimation.addListener(toastOutAnimatorListener);
                outAnimation.start();
            } else {
                this.mPresenter.hide(this.mCallback);
                if (oplusToastUI$$ExternalSyntheticLambda0 != null) {
                    oplusToastUI$$ExternalSyntheticLambda0.run();
                }
            }
        }
        this.mToast = null;
        this.mPresenter = null;
        this.mCallback = null;
    }

    public final void hideToast(String str, IBinder iBinder) {
        int i;
        IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
        try {
            Binder binder = this.mSubWindowToken;
            if (binder != null && (i = this.mSubDisPlayId) != 0) {
                windowManagerService.removeWindowToken(binder, i);
            }
            this.mSubWindowToken = null;
            this.mSubDisPlayId = 0;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("removeWindowToken exception ：");
            sb.append(e);
            sb.append(" ,mSubWindowToken:");
            sb.append(this.mSubWindowToken);
            sb.append(",mSubDisPlayId:");
            ListPopupWindow$.ExternalSyntheticOutline0.m(this.mSubDisPlayId, "OplusToastUI", sb);
        }
        ToastPresenter toastPresenter = this.mPresenter;
        if (toastPresenter == null || !Objects.equals(toastPresenter.getPackageName(), str) || !Objects.equals(this.mPresenter.getToken(), iBinder)) {
            ToastItem toastItem = this.mToastItem;
            if (toastItem != null) {
                String str2 = toastItem.mPackageName;
                if (Objects.equals(str2, str) && Objects.equals(toastItem.mToken, iBinder)) {
                    if (toastItem.this$0.mToast == null) {
                        Log.i("OplusToastUI", "hide an in-progress toast for " + str2);
                    }
                    toastItem.mCancelled = true;
                }
            }
            MotionLayout$.ExternalSyntheticOutline0.m("Attempt to hide non-current toast from package ", str, "OplusToastUI");
            return;
        }
        this.mToastItem = null;
        OplusNotificationLogger.logToastState("no_need_print", str, iBinder.toString(), false);
        this.mToastLogger.logOnHideToast(str, iBinder.toString());
        hideCurrentToast$1(null);
    }

    public final void onConfigChanged(Configuration configuration) {
        ResponsiveUIModelHelper responsiveUIModelHelper = this.mUiModeHelper;
        if (responsiveUIModelHelper != null) {
            responsiveUIModelHelper.onConfigChanged(configuration);
        }
        int i = configuration.orientation;
        if (i != this.mOrientation) {
            this.mOrientation = i;
            OplusSystemUIToast oplusSystemUIToast = this.mToast;
            if (oplusSystemUIToast != null) {
                this.mToastLogger.logOrientationChange(oplusSystemUIToast.mText.toString(), this.mOrientation == 1);
                this.mToast.onOrientationChange(this.mOrientation);
                this.mPresenter.updateLayoutParams(this.mToast.getXOffset().intValue(), this.mToast.getYOffset().intValue(), this.mToast.getHorizontalMargin().intValue(), this.mToast.getVerticalMargin().intValue(), this.mToast.getGravity().intValue());
            }
            this.mOrientationChange = true;
        }
        if (!this.mOrientationChange && this.mDensity == configuration.densityDpi && this.mSmallestScreenWidth == configuration.smallestScreenWidthDp) {
            return;
        }
        OplusSystemUIToast oplusSystemUIToast2 = this.mToast;
        if (oplusSystemUIToast2 != null && (oplusSystemUIToast2.getView() instanceof OplusToastLayout)) {
            OplusToastLayout oplusToastLayout = (OplusToastLayout) this.mToast.getView();
            oplusToastLayout.initResourse();
            this.mToast.setTextViewMaxSize();
            oplusToastLayout.setIsTheFirstFrame(true);
        }
        this.mDensity = configuration.densityDpi;
        this.mSmallestScreenWidth = configuration.smallestScreenWidthDp;
        this.mOrientationChange = false;
    }

    public /* bridge */ /* synthetic */ void setBiometricContextListener(IBiometricContextListener iBiometricContextListener) {
        super.setBiometricContextListener(iBiometricContextListener);
    }

    public /* bridge */ /* synthetic */ void setQsTiles(String[] strArr) {
        super.setQsTiles(strArr);
    }

    public /* bridge */ /* synthetic */ void setSplitscreenFocus(boolean z) {
        super.setSplitscreenFocus(z);
    }

    public /* bridge */ /* synthetic */ void setTopAppHidesStatusBar(boolean z) {
        super.setTopAppHidesStatusBar(z);
    }

    public /* bridge */ /* synthetic */ void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        super.setUdfpsRefreshRateCallback(iUdfpsRefreshRateRequestCallback);
    }

    public final void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback, int i3) {
        ToastItem toastItem = new ToastItem();
        toastItem.this$0 = this;
        toastItem.mCancelled = false;
        toastItem.mUid = i;
        toastItem.mPackageName = str;
        toastItem.mToken = iBinder;
        toastItem.mText = charSequence;
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        if (companion.getInstance(this.mContext).getBaseSubDisplayShow()) {
            int secondaryDisplayId = companion.getInstance(this.mContext).getSecondaryDisplayId();
            if (i3 != secondaryDisplayId) {
                Binder binder = new Binder();
                IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
                try {
                    windowManagerService.removeWindowToken(iBinder2, i3);
                    windowManagerService.addWindowToken(binder, 2005, secondaryDisplayId, (Bundle) null);
                    this.mSubDisPlayId = secondaryDisplayId;
                    this.mSubWindowToken = binder;
                } catch (Exception e) {
                    PeopleBackupFollowUpJob$.ExternalSyntheticOutline0.m("addWindowToken or removeWindowToken exception ：", "OplusToastUI", e);
                }
                i3 = secondaryDisplayId;
                iBinder2 = binder;
            } else {
                i3 = secondaryDisplayId;
            }
        }
        toastItem.mDisplayId = i3;
        toastItem.mCurrentWindowToken = iBinder2;
        if (this.mUiModeHelper == null) {
            this.mUiModeHelper = new ResponsiveUIModelHelper();
        }
        VarHandle.storeStoreFence();
        OplusToastUI$$ExternalSyntheticLambda0 oplusToastUI$$ExternalSyntheticLambda0 = new OplusToastUI$$ExternalSyntheticLambda0();
        oplusToastUI$$ExternalSyntheticLambda0.f$0 = toastItem;
        oplusToastUI$$ExternalSyntheticLambda0.f$1 = iTransientNotificationCallback;
        oplusToastUI$$ExternalSyntheticLambda0.f$2 = i2;
        VarHandle.storeStoreFence();
        ToastItem toastItem2 = this.mToastItem;
        this.mToastItem = toastItem;
        ToastOutAnimatorListener toastOutAnimatorListener = this.mToastOutAnimatorListener;
        if (toastOutAnimatorListener != null) {
            toastOutAnimatorListener.setShowNextToastRunnable(oplusToastUI$$ExternalSyntheticLambda0);
            return;
        }
        if (this.mPresenter != null) {
            hideCurrentToast$1(oplusToastUI$$ExternalSyntheticLambda0);
            return;
        }
        if (toastItem2 == null) {
            oplusToastUI$$ExternalSyntheticLambda0.run();
            return;
        }
        Log.i("OplusToastUI", "cancel in-progress toast for " + toastItem2.mPackageName + ": " + FileProvider$.ExternalSyntheticOutline0.m("a new toast is coming, from ", str));
        toastItem2.mCancelled = true;
        oplusToastUI$$ExternalSyntheticLambda0.run();
    }

    public final void start() {
        this.mCommandQueue.addCallback(this);
    }
}
