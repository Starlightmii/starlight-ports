package com.oplus.systemui.qs.helper;

import android.R;
import android.app.AppGlobals;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserManager;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import com.android.internal.util.UserIcons;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.user.UserSwitcherControllerHelperEx;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplus.systemui.common.receiver.SystemDialogReceiver;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QsBgThread;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class UserSwitcherControllerHelper extends UserSwitcherControllerHelperEx {
    public AlertDialog mAddUserDialog;
    public final Handler mBgHandler;
    public AlertDialog mConfirmSwitchToGuestDialog;
    public AlertDialog mConfirmSwitchToNewUserDialog;
    public final Context mContext;
    public Runnable mEndClosingListener;
    public AlertDialog mExitGuestDialog;
    public final Handler mMainHandler;
    public AlertDialog mNewSessionDialog;
    public final PanelInteractor mPanelInteractor;
    public AlertDialog mSetUpUserDialog;
    public final Lazy mShadeController;
    public final UserSwitcherControllerHelper$$ExternalSyntheticLambda6 mSystemDialogListener;
    public AlertDialog mUserCreatingDialog;
    public final UserSwitcherInteractor mUserInteractor;
    public final UserManager mUserManager;
    public AlertDialog mUserOperationExceptionDialog;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.helper.UserSwitcherControllerHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ UserSwitcherControllerHelper this$0;

        public final void onStartedGoingToSleep() {
            this.this$0.dismissAllDialog();
        }
    }

    public UserSwitcherControllerHelper(Context context, WakefulnessLifecycle wakefulnessLifecycle, Handler handler, UserSwitcherInteractor userSwitcherInteractor, PanelInteractor panelInteractor, Lazy lazy, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        UserSwitcherControllerHelper$$ExternalSyntheticLambda6 userSwitcherControllerHelper$$ExternalSyntheticLambda6 = new UserSwitcherControllerHelper$$ExternalSyntheticLambda6();
        userSwitcherControllerHelper$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.mSystemDialogListener = userSwitcherControllerHelper$$ExternalSyntheticLambda6;
        this.mUserManager = UserManager.get(context);
        this.mBgHandler = new Handler(QsBgThread.INSTANCE.getThreadLooper());
        this.mMainHandler = handler;
        this.mUserInteractor = userSwitcherInteractor;
        this.mPanelInteractor = panelInteractor;
        this.mContext = context;
        this.mShadeController = lazy;
        SystemDialogReceiver.getInstance().addListener(userSwitcherControllerHelper$$ExternalSyntheticLambda6);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        ((Lifecycle) wakefulnessLifecycle).mObservers.add(anonymousClass1);
    }

    public static Drawable getDrawable(Context context, UserRecord userRecord) {
        UserInfo userInfo;
        boolean z = userRecord.isGuest;
        if (userRecord.isAddUser) {
            return context.getDrawable(2131237233);
        }
        Drawable defaultUserIcon = UserIcons.getDefaultUserIcon(context.getResources(), (z || (userInfo = userRecord.info) == null) ? -10000 : userInfo.id, false);
        if (z) {
            return userRecord.isCurrent ? context.getDrawable(2131237232) : context.getDrawable(2131237231);
        }
        ColorFilter colorFilter = defaultUserIcon.getColorFilter();
        if (colorFilter instanceof PorterDuffColorFilter) {
            defaultUserIcon.setColorFilter(((PorterDuffColorFilter) colorFilter).getColor(), PorterDuff.Mode.DST_IN);
            return defaultUserIcon;
        }
        LogUtil.d(LogUtil.TAG_STATUSBAR, "UserSwitcherControllerHelper", "use default userIcon with default color");
        return defaultUserIcon;
    }

    public static void setDimAmount(Window window) {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        boolean zIsSuperPowerSaveModel = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
        BatteryControllerImpl batteryControllerImpl = (BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class);
        if (zIsSuperPowerSaveModel || batteryControllerImpl.mPowerSave) {
            window.setDimAmount(0.7f);
        } else {
            window.setDimAmount(0.0f);
        }
    }

    private static void setWindowOnTop(Dialog dialog) {
        dialog.getWindow().setType(2017);
    }

    public final void addPostCollapseAction() {
        ShadeController shadeController = (ShadeController) this.mShadeController.get();
        UserSwitcherControllerHelper$.ExternalSyntheticLambda22 externalSyntheticLambda22 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda22(0);
        externalSyntheticLambda22.f$0 = this;
        VarHandle.storeStoreFence();
        shadeController.addPostCollapseAction(externalSyntheticLambda22);
    }

    public final boolean addUserDialog(Context context, Function0 function0) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            LogUtil.i(LogUtil.TAG_QS, "UserSwitcherControllerHelper", "addUserDialog:fast click filter,ignore");
            return true;
        }
        COUIAlertDialogBuilder message = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mContext), 2132017608).setTitle(2131955872).setMessage(2131955299);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda7();
        externalSyntheticLambda7.f$0 = this;
        externalSyntheticLambda7.f$1 = context;
        externalSyntheticLambda7.f$2 = function0;
        VarHandle.storeStoreFence();
        message.setPositiveButton(2131957107, externalSyntheticLambda7);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda1(7);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        message.setNegativeButton(R.string.cancel, externalSyntheticLambda1);
        COUIAlertDialogBuilder blurBackgroundDrawable = message.setBlurBackgroundDrawable(true);
        AlertDialog alertDialogCreate = blurBackgroundDrawable.create();
        this.mAddUserDialog = alertDialogCreate;
        setWindowOnTop(alertDialogCreate);
        setDimAmount(this.mAddUserDialog.getWindow());
        this.mAddUserDialog.show();
        blurBackgroundDrawable.updateViewAfterShown();
        return true;
    }

    public final boolean confirmSwitchToGuest(UserRecord userRecord, int i, Function0 function0) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            LogUtil.i(LogUtil.TAG_QS, "UserSwitcherControllerHelper", "confirmSwitchToGuest:fast click filter,ignore");
            return true;
        }
        COUIAlertDialogBuilder message = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mContext), 2132017608).setTitle(2131955872).setMessage(2131957105);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda3();
        externalSyntheticLambda3.f$0 = this;
        externalSyntheticLambda3.f$1 = userRecord;
        externalSyntheticLambda3.f$2 = i;
        externalSyntheticLambda3.f$3 = function0;
        VarHandle.storeStoreFence();
        message.setPositiveButton(2131955298, externalSyntheticLambda3);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda1(6);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        message.setNegativeButton(R.string.cancel, externalSyntheticLambda1);
        COUIAlertDialogBuilder blurBackgroundDrawable = message.setBlurBackgroundDrawable(true);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda2(2);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        AlertDialog alertDialogCreate = blurBackgroundDrawable.setOnDismissListener(externalSyntheticLambda2).create();
        this.mConfirmSwitchToGuestDialog = alertDialogCreate;
        alertDialogCreate.getWindow().setType(2017);
        setDimAmount(this.mConfirmSwitchToGuestDialog.getWindow());
        this.mConfirmSwitchToGuestDialog.show();
        blurBackgroundDrawable.updateViewAfterShown();
        return true;
    }

    public final boolean confirmSwitchToNewUser(UserRecord userRecord, int i, Function0 function0) {
        if (userRecord == null || (userRecord.isGuest && userRecord.info != null)) {
            confirmSwitchToGuest(userRecord, i, function0);
            return true;
        }
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            LogUtil.i(LogUtil.TAG_QS, "UserSwitcherControllerHelper", "confirmSwitchToNewUser:fast click filter,ignore");
            return true;
        }
        COUIAlertDialogBuilder title = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mContext), 2132017608).setTitle(2131955872);
        Context context = this.mContext;
        UserInfo userInfo = userRecord.info;
        COUIAlertDialogBuilder message = title.setMessage(context.getString(2131957106, userInfo != null ? userInfo.name : ""));
        UserSwitcherControllerHelper$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = i;
        externalSyntheticLambda0.f$2 = function0;
        VarHandle.storeStoreFence();
        message.setPositiveButton(2131955298, externalSyntheticLambda0);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda1(0);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        message.setNegativeButton(R.string.cancel, externalSyntheticLambda1);
        COUIAlertDialogBuilder blurBackgroundDrawable = message.setBlurBackgroundDrawable(true);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda2(0);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        AlertDialog alertDialogCreate = blurBackgroundDrawable.setOnDismissListener(externalSyntheticLambda2).create();
        this.mConfirmSwitchToNewUserDialog = alertDialogCreate;
        alertDialogCreate.getWindow().setType(2017);
        setDimAmount(this.mConfirmSwitchToNewUserDialog.getWindow());
        this.mConfirmSwitchToNewUserDialog.show();
        blurBackgroundDrawable.updateViewAfterShown();
        QsStatisticUtil.collectSwitchUserTypeEvent(this.mContext, i == 0 ? "1" : ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH, ShadeExpansionHelperImpl.VAL_INVALID);
        return true;
    }

    public final void dismissAllDialog() {
        AlertDialog alertDialog = this.mNewSessionDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mNewSessionDialog.dismiss();
        }
        AlertDialog alertDialog2 = this.mAddUserDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.mAddUserDialog.dismiss();
        }
        AlertDialog alertDialog3 = this.mExitGuestDialog;
        if (alertDialog3 != null && alertDialog3.isShowing()) {
            this.mExitGuestDialog.dismiss();
        }
        AlertDialog alertDialog4 = this.mConfirmSwitchToGuestDialog;
        if (alertDialog4 != null && alertDialog4.isShowing()) {
            this.mConfirmSwitchToGuestDialog.dismiss();
        }
        AlertDialog alertDialog5 = this.mConfirmSwitchToNewUserDialog;
        if (alertDialog5 != null && alertDialog5.isShowing()) {
            this.mConfirmSwitchToNewUserDialog.dismiss();
        }
        AlertDialog alertDialog6 = this.mUserOperationExceptionDialog;
        if (alertDialog6 != null && alertDialog6.isShowing()) {
            this.mUserOperationExceptionDialog.dismiss();
        }
        AlertDialog alertDialog7 = this.mSetUpUserDialog;
        if (alertDialog7 == null || !alertDialog7.isShowing()) {
            return;
        }
        this.mSetUpUserDialog.dismiss();
    }

    public final void dismissUserCreatingDialog() {
        Runnable externalSyntheticLambda22 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda22(1);
        ((UserSwitcherControllerHelper$.ExternalSyntheticLambda22) externalSyntheticLambda22).f$0 = this;
        VarHandle.storeStoreFence();
        this.mMainHandler.post(externalSyntheticLambda22);
    }

    public final Dialog exitGuestDialog(Context context, int i, Function0 function0, int i2) {
        COUIAlertDialogBuilder message = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mContext), 2132017616).setTitle(2131955137).setMessage(2131955136);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda10();
        externalSyntheticLambda10.f$0 = this;
        externalSyntheticLambda10.f$1 = i;
        externalSyntheticLambda10.f$2 = i2;
        externalSyntheticLambda10.f$3 = context;
        externalSyntheticLambda10.f$4 = function0;
        VarHandle.storeStoreFence();
        message.setNeutralButton(2131953456, externalSyntheticLambda10);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda1(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        message.setNegativeButton(R.string.cancel, externalSyntheticLambda1);
        COUIAlertDialogBuilder blurBackgroundDrawable = message.setBlurBackgroundDrawable(true);
        UserSwitcherControllerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda2(1);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        AlertDialog alertDialogCreate = blurBackgroundDrawable.setOnDismissListener(externalSyntheticLambda2).create();
        this.mExitGuestDialog = alertDialogCreate;
        setWindowOnTop(alertDialogCreate);
        setDimAmount(this.mExitGuestDialog.getWindow());
        this.mExitGuestDialog.show();
        blurBackgroundDrawable.updateViewAfterShown();
        return this.mExitGuestDialog;
    }

    public AlertDialog getSwitchDialog() {
        return this.mNewSessionDialog;
    }

    public final boolean isStorageLowAndToast(Context context) {
        boolean zIsStorageLow;
        try {
            zIsStorageLow = AppGlobals.getPackageManager().isStorageLow();
        } catch (RemoteException e) {
            LogUtil.w(LogUtil.TAG_STATUSBAR, "UserSwitcherControllerHelper", "iPM.isStorageLow e " + e);
            zIsStorageLow = false;
        }
        if (zIsStorageLow) {
            Runnable externalSyntheticLambda20 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda20(1);
            ((UserSwitcherControllerHelper$.ExternalSyntheticLambda20) externalSyntheticLambda20).f$0 = context;
            VarHandle.storeStoreFence();
            this.mMainHandler.post(externalSyntheticLambda20);
        }
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("isStorageLow ", LogUtil.TAG_QS, "UserSwitcherControllerHelper", zIsStorageLow);
        return zIsStorageLow;
    }

    public final void showErrorMessage(Context context) {
        Runnable externalSyntheticLambda20 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda20(0);
        ((UserSwitcherControllerHelper$.ExternalSyntheticLambda20) externalSyntheticLambda20).f$0 = context;
        VarHandle.storeStoreFence();
        this.mMainHandler.post(externalSyntheticLambda20);
    }

    public final void showUserCreatingDialog(Context context) {
        Runnable externalSyntheticLambda9 = new UserSwitcherControllerHelper$.ExternalSyntheticLambda9(3);
        ((UserSwitcherControllerHelper$.ExternalSyntheticLambda9) externalSyntheticLambda9).f$0 = this;
        ((UserSwitcherControllerHelper$.ExternalSyntheticLambda9) externalSyntheticLambda9).f$1 = context;
        VarHandle.storeStoreFence();
        this.mMainHandler.post(externalSyntheticLambda9);
    }
}
