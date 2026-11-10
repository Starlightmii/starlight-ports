package com.oplus.systemui.qs.utils;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.wifi.OplusWifiManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.verizon.loginenginesvc.clientsdk.MhsAuthorizedClient;
import com.verizon.loginenginesvc.clientsdk.internal.LoginSvcClient;
import java.lang.invoke.VarHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsOperatorUtils {
    private static final String KEY_APN = "apn";
    private static final String PROFILE_NAME = "3G_HOT";
    private static final String PROFILE_TYPE = "dun";
    private static final String PROFILE_VISIBLE = "0";
    private static final String QUERY_CONDITION = "type = ? and numeric = ? and user_visible != ? and name != ?";
    private static final String TAG = "QsOperatorUtils";
    private static AlertDialog mMhsAuthProgressDialog;
    private static MhsAuthorizedClientCallback mMhsAuthorizedCallBack;
    private static MhsAuthorizedClient mMhsAuthorizedClient;
    private static AlertDialog mTetheringErrorDialog;
    public static final QsOperatorUtils INSTANCE = new QsOperatorUtils();
    private static final Object showProgressToken = new Object();
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    private static final DialogInterface.OnClickListener dialogClickListener = QsOperatorUtils$dialogClickListener$1.INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface MhsAuthResultCallback {
        void onAuthResult(boolean z);
    }

    public static final void access$showMhsProgressDialog(Context context, boolean z) {
        Window window;
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(context, 2132017612);
        cOUIAlertDialogBuilder.setView(2131558781);
        cOUIAlertDialogBuilder.setNegativeButton(R.string.cancel, dialogClickListener);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.setCancelable(false).create();
        mMhsAuthProgressDialog = alertDialogCreate;
        if (alertDialogCreate != null && (window = alertDialogCreate.getWindow()) != null) {
            window.setType(2009);
        }
        AlertDialog alertDialog = mMhsAuthProgressDialog;
        if (alertDialog != null) {
            alertDialog.setOnDismissListener(showMhsProgressDialog.1.INSTANCE);
        }
        SystemUIDialog.setShowForAllUsers(mMhsAuthProgressDialog, true);
        SystemUIDialog.registerDismissListener(mMhsAuthProgressDialog, (Runnable) null);
        SystemUIDialog.setWindowOnTop(mMhsAuthProgressDialog, z);
        AlertDialog alertDialog2 = mMhsAuthProgressDialog;
        if (alertDialog2 != null) {
            alertDialog2.show();
        }
    }

    public static final void dismissMhsProgressDialog() {
        AlertDialog alertDialog;
        AlertDialog alertDialog2 = mMhsAuthProgressDialog;
        if (alertDialog2 == null || !alertDialog2.isShowing() || (alertDialog = mMhsAuthProgressDialog) == null) {
            return;
        }
        alertDialog.dismiss();
    }

    public static final void dismissTetheringErrorDialog() {
        AlertDialog alertDialog;
        AlertDialog alertDialog2 = mTetheringErrorDialog;
        if (alertDialog2 == null || alertDialog2 == null || !alertDialog2.isShowing() || (alertDialog = mTetheringErrorDialog) == null) {
            return;
        }
        alertDialog.dismiss();
    }

    public static final boolean isATTHotspotUnAuth(Context context, int i, int i2) {
        Context applicationContext;
        boolean z = false;
        if (context != null && (applicationContext = context.getApplicationContext()) != null) {
            int i3 = !CustomizeFeatureOption.checkSlotIdValid(i2) ? -1 : CustomizeFeatureOption.sTetheringEntitlement[i2];
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i3, "Check TetheringEntitlement sCarrier = ", " ,", "QsOperatorUtils-->", "SystemUi--QuickSettings");
            }
            if (i3 == 0 || i3 == 1) {
                try {
                    OplusWifiManager oplusWifiManager = new OplusWifiManager(applicationContext);
                    Class<?> cls = oplusWifiManager.getClass();
                    Class<?> cls2 = Integer.TYPE;
                    Method declaredMethod = cls.getDeclaredMethod("attRequestAuthenticationBeforeTurnOnHotspot", cls2, Boolean.TYPE, cls2);
                    declaredMethod.setAccessible(true);
                    boolean zBooleanValue = ((Boolean) declaredMethod.invoke(oplusWifiManager, Integer.valueOf(i), Boolean.TRUE, Integer.valueOf(i3))).booleanValue();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "QsOperatorUtils-->".concat("isTetheringEntitlementSuccess: result = " + zBooleanValue));
                    }
                    z = zBooleanValue;
                } catch (IllegalAccessException e) {
                    Log.e(TAG, "get tethering entitlement status error: " + e);
                } catch (NoSuchMethodException e2) {
                    Log.e(TAG, "get tethering entitlement status error: " + e2);
                } catch (InvocationTargetException e3) {
                    Log.e(TAG, "get tethering entitlement status error: " + e3);
                }
                return !z;
            }
        }
        return false;
    }

    public static final boolean isSprintHotspotUnAuth(Context context, boolean z, int i, boolean z2) {
        Context applicationContext;
        String simOperator;
        Object obj;
        if (context != null && (applicationContext = context.getApplicationContext()) != null) {
            if (z) {
                showTetheringErrorDialog(applicationContext, 2131953549, 2131953545, z2);
                return true;
            }
            TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getApplicationContext().getSystemService(TelephonyManager.class);
            if (telephonyManager == null || (simOperator = telephonyManager.getSimOperator()) == null) {
                simOperator = "";
            }
            try {
                obj = Result.constructor-impl(applicationContext.getContentResolver().query(Telephony.Carriers.CONTENT_URI, new String[]{KEY_APN}, QUERY_CONDITION, new String[]{PROFILE_TYPE, simOperator, "0", PROFILE_NAME}, null));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            Cursor cursor = (Cursor) obj;
            if (cursor != null) {
                int count = cursor.getCount();
                cursor.close();
                if (count <= 0) {
                    if (i == 0) {
                        showTetheringErrorDialog(applicationContext, 2131953547, 2131953546, z2);
                    } else if (i == 1) {
                        showTetheringErrorDialog(applicationContext, 2131957079, 2131957078, z2);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isVZWHotspotUnAuth(Context context, MhsAuthResultCallback mhsAuthResultCallback, int i, int i2, boolean z) {
        Context applicationContext;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            return false;
        }
        Handler handler = mHandler;
        isVZWHotspotUnAuth.1 r2 = new isVZWHotspotUnAuth.1();
        r2.$appContext = applicationContext;
        r2.$isKeyguardShowing = z;
        VarHandle.storeStoreFence();
        handler.postDelayed(r2, showProgressToken, 1001L);
        MhsAuthorizedClientCallback mhsAuthorizedClientCallback = mMhsAuthorizedCallBack;
        if (mhsAuthorizedClientCallback == null) {
            mhsAuthorizedClientCallback = new MhsAuthorizedClientCallback(context);
            mMhsAuthorizedCallBack = mhsAuthorizedClientCallback;
        }
        mhsAuthorizedClientCallback.setMAuthResultCallback(mhsAuthResultCallback);
        mhsAuthorizedClientCallback.setMType(i2);
        MhsAuthorizedClient mhsAuthorizedClient = new MhsAuthorizedClient(context, Integer.valueOf(i), Looper.getMainLooper());
        mhsAuthorizedClient.mCallback = mhsAuthorizedClientCallback;
        VarHandle.storeStoreFence();
        mMhsAuthorizedClient = mhsAuthorizedClient;
        synchronized (mhsAuthorizedClient) {
            if (((LoginSvcClient) mhsAuthorizedClient).mInProgress) {
                return true;
            }
            ComponentName serviceComponentName = mhsAuthorizedClient.getServiceComponentName();
            if (serviceComponentName == null) {
                mhsAuthorizedClient.postError("service not found", (Throwable) null);
                return true;
            }
            ((LoginSvcClient) mhsAuthorizedClient).mInProgress = true;
            ((LoginSvcClient) mhsAuthorizedClient).mHandler.postDelayed(((LoginSvcClient) mhsAuthorizedClient).mTimeoutRunnable, 25000L);
            mhsAuthorizedClient.performBind(serviceComponentName);
            return true;
        }
    }

    public static final void showTetheringErrorDialog(Context context, int i, int i2, boolean z) {
        Window window;
        Looper looperMyLooper = Looper.myLooper();
        Handler handler = mHandler;
        if (!Intrinsics.areEqual(looperMyLooper, handler.getLooper())) {
            showTetheringErrorDialog.1 r0 = new showTetheringErrorDialog.1();
            r0.$context = context;
            r0.$titleId = i;
            r0.$messageId = i2;
            r0.$isKeyguardShowing = z;
            VarHandle.storeStoreFence();
            handler.post(r0);
            return;
        }
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(context, 2132017612);
        if (i > 0) {
            cOUIAlertDialogBuilder.setTitle(i);
        }
        if (i2 > 0) {
            cOUIAlertDialogBuilder.setMessage(i2);
        }
        cOUIAlertDialogBuilder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.setCancelable(false).create();
        mTetheringErrorDialog = alertDialogCreate;
        if (alertDialogCreate != null && (window = alertDialogCreate.getWindow()) != null) {
            window.setType(2009);
        }
        AlertDialog alertDialog = mTetheringErrorDialog;
        if (alertDialog != null) {
            alertDialog.setOnDismissListener(showTetheringErrorDialog.2.INSTANCE);
        }
        SystemUIDialog.setShowForAllUsers(mTetheringErrorDialog, true);
        SystemUIDialog.registerDismissListener(mTetheringErrorDialog, (Runnable) null);
        SystemUIDialog.setWindowOnTop(mTetheringErrorDialog, z);
        AlertDialog alertDialog2 = mTetheringErrorDialog;
        if (alertDialog2 != null) {
            alertDialog2.show();
        }
    }
}
