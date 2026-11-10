package com.oplus.systemui.statusbar.notification.power;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPowerNotificationWarningsForSubDisplay {
    public static OplusPowerNotificationWarningsForSubDisplay mInstance;
    public final Handler mBackgroundHandler;
    public final Context mContext;
    public final 1 mDisplayListener;
    public final LowBatteryUiController mLowBatteryControlInstance;
    public final Display mSubdisplay;
    public Context mWindowContext;
    public int mCurrentSecondaryDisplayState = 0;
    public int mBatteryLevel = 0;
    public int mWarningLevel = 0;
    public boolean mPlugged = false;
    public final Handler mMainHandler = new Handler();

    public OplusPowerNotificationWarningsForSubDisplay(Context context) {
        this.mLowBatteryControlInstance = null;
        1 r0 = new 1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        this.mDisplayListener = r0;
        this.mContext = context;
        this.mBackgroundHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        DisplayManager displayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mSubdisplay = displayManager.getDisplay(OplusSubDisplayUtils.Companion.getInstance(context).getSecondaryDisplayId());
        Handler handler = this.mBackgroundHandler;
        OplusPowerNotificationWarningsForSubDisplay$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusPowerNotificationWarningsForSubDisplay$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = displayManager;
        VarHandle.storeStoreFence();
        handler.post(externalSyntheticLambda0);
        this.mLowBatteryControlInstance = LowBatteryUiController.getInstance();
    }

    public static boolean isAodState(int i) {
        return i == 3 || i == 4;
    }

    public final LowBatteryDialog createBatteryLowDialogForSubDisplay(int i, int i2, boolean z) {
        this.mBatteryLevel = i2;
        this.mPlugged = z;
        this.mWarningLevel = i;
        StringBuilder sb = new StringBuilder("createBatteryLowDialogForSubDisplay mBatteryLevel: ");
        sb.append(this.mBatteryLevel);
        sb.append("   mPlugged = ");
        sb.append(this.mPlugged);
        sb.append("  mWarningLevel :");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mWarningLevel, LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", sb);
        Context context = this.mContext;
        Context contextCreateWindowContext = context.createDisplayContext(((DisplayManager) context.getSystemService("display")).getDisplay(OplusSubDisplayUtils.Companion.getInstance(context).getSecondaryDisplayId())).createWindowContext(2003, null);
        Configuration configuration = new Configuration(contextCreateWindowContext.getResources().getConfiguration());
        configuration.densityDpi = 300;
        configuration.fontScale = 1.0f;
        this.mWindowContext = contextCreateWindowContext.createConfigurationContext(configuration);
        if (isAodState(this.mSubdisplay.getState())) {
            return null;
        }
        if (i == 2) {
            if (z) {
                LogUtil.i(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createBatteryLowDialogForSubDisplay: warnningLevel is 2, and plug is in. do nothing.");
                return null;
            }
            Log.i("OplusPowerNotificationWarningsForSubDisplay", "createCritcalLowDialogForSubDisplay : " + i2);
            COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mWindowContext), 2132017612);
            cOUIAlertDialogBuilder.setTitle(this.mContext.getString(2131952916, Integer.valueOf(i2)));
            cOUIAlertDialogBuilder.setPositiveButton(2131952526, new 3());
            cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
            AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
            alertDialogCreate.getWindow().setType(2003);
            alertDialogCreate.getDelegate().setLocalNightMode(1);
            alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
            alertDialogCreate.setCancelable(false);
            return new LowBatteryDialog(alertDialogCreate, cOUIAlertDialogBuilder);
        }
        if (i != 20) {
            LogUtil.i(LogUtil.TAG, "OplusPowerNotificationWarningsForSubDisplay", "createBatteryLowDialogForSubDisplay: warnningLevel(" + i + ") is ivalid");
            return null;
        }
        if (FeatureOption.INSTANCE.isDisablePowerSave(this.mContext)) {
            LogUtil.i(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createBatteryLowDialogForSubDisplay: FEATURE_DISABLE_POWER_SAVE is true.");
            if (!z) {
                return createPlugChargerDialogforSubdisplay(i2);
            }
            LogUtil.i(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createBatteryLowDialogForSubDisplay: save power is on, and plug is in. do nothing.");
            return null;
        }
        if (!OplusPowerNotificationWarnings.getInstance().mIsSavePowerMode) {
            OplusPowerNotificationWarnings.getInstance().getClass();
            if (!AutoSaveModeSwitchObserver.Companion.getInstance().isAutoSaveModeOn || AutoSaveModeLevelObserver.Companion.getInstance().getAutoSaveModeLevel() != i2) {
                LogUtil.d(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createSavePowerDialogForSubDisplay: percent= " + i2);
                COUIAlertDialogBuilder cOUIAlertDialogBuilder2 = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mWindowContext), 2132017613);
                cOUIAlertDialogBuilder2.setTitle(this.mContext.getString(2131954295, Integer.valueOf(i2)));
                2 r10 = new 2(1);
                r10.this$0 = this;
                VarHandle.storeStoreFence();
                cOUIAlertDialogBuilder2.setPositiveButton(2131956544, r10);
                2 r11 = new 2(2);
                r11.this$0 = this;
                VarHandle.storeStoreFence();
                cOUIAlertDialogBuilder2.setNegativeButton(2131952524, r11);
                cOUIAlertDialogBuilder2.setBlurBackgroundDrawable(true);
                AlertDialog alertDialogCreate2 = cOUIAlertDialogBuilder2.create();
                alertDialogCreate2.getDelegate().setLocalNightMode(1);
                alertDialogCreate2.getWindow().setType(2003);
                alertDialogCreate2.getWindow().getAttributes().privateFlags |= 16;
                alertDialogCreate2.setCancelable(false);
                return new LowBatteryDialog(alertDialogCreate2, cOUIAlertDialogBuilder2);
            }
        }
        if (!z) {
            return createPlugChargerDialogforSubdisplay(i2);
        }
        LogUtil.i(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createBatteryLowDialogForSubDisplay: save power is on, and plug is in. do nothing.");
        return null;
    }

    public final LowBatteryDialog createPlugChargerDialogforSubdisplay(int i) {
        LogUtil.i(LogUtil.TAG_COMMON, "OplusPowerNotificationWarningsForSubDisplay", "createPlugChargerDialogForSubDisplay: percent= " + i);
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mWindowContext), 2132017612);
        cOUIAlertDialogBuilder.setTitle(this.mContext.getString(2131954292, Integer.valueOf(i)));
        2 r4 = new 2(0);
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIAlertDialogBuilder.setPositiveButton(2131952526, r4);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.getDelegate().setLocalNightMode(1);
        alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
        alertDialogCreate.setCancelable(false);
        return new LowBatteryDialog(alertDialogCreate, cOUIAlertDialogBuilder);
    }
}
