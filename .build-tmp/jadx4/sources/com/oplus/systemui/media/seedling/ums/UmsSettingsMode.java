package com.oplus.systemui.media.seedling.ums;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.util.kotlin.Quad;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UmsSettingsMode {
    private static final String NM_PROVIDER = "content://com.oplus.notificationmanager.livealert.provider";
    private static final String NM_PROVIDER_METHOD = "queryLiveAlertSettings";
    private static final String NM_PROVIDER_QUERY_KEY_SERVICE_IDS = "serviceIds";
    private static final String NM_PROVIDER_RESULT_KEY_CLOSED = "closedEntry";
    private static final String NM_PROVIDER_RESULT_KEY_ENABLE = "enable";
    private static final String NM_PROVIDER_RESULT_KEY_RESULT = "result";
    private static final String NM_SETTINGS_OBSERVER = "content://com.oplus.notificationmanager.livealert.provider/change_livealert_settings";
    private static final String TAG = "UmsSettingsMode";
    private static final String UMS_BUNDLE_KEY_FLUID_CLOUD_RESULT = "fluidCloudResult";
    private static final String UMS_CLOSE_ENTRY_KEY = "closeEntry";
    private static final String UMS_MEDIA_CONTROLLER_SWITCH = "serviceSwitch";
    private static final String UMS_QUERY_TYPE_KEY = "queryType";
    private static final String UMS_SERVICE_ID_KEY = "serviceId";
    private static final String UMS_SERVICE_ID_VALUE = "268452006";
    private static final String URI_UMS_QUERY_METHOD = "queryFluidCloud";
    private static final String URI_UMS_STATICTIS = "content://com.oplus.pantanal.ums.statictis";
    public static final String URI_UMS_STATICTIS_SETTINGS = "content://com.oplus.pantanal.ums.statictis/change_advice_settings";
    public static final UmsSettingsMode INSTANCE = new UmsSettingsMode();
    private static final Lazy providerUri$delegate = LazyKt__LazyJVMKt.lazy(new UmsSettingsMode$.ExternalSyntheticLambda0(0));
    private static final Lazy observerUri$delegate = LazyKt__LazyJVMKt.lazy(new UmsSettingsMode$.ExternalSyntheticLambda0(1));

    /* JADX WARN: Code duplicated, block: B:100:0x021c  */
    /* JADX WARN: Code duplicated, block: B:102:0x0220  */
    /* JADX WARN: Code duplicated, block: B:103:0x022a  */
    /* JADX WARN: Code duplicated, block: B:105:0x022e  */
    /* JADX WARN: Code duplicated, block: B:106:0x0236  */
    /* JADX WARN: Code duplicated, block: B:108:0x023a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0244  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:45:0x0103  */
    /* JADX WARN: Code duplicated, block: B:46:0x010d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0111  */
    /* JADX WARN: Code duplicated, block: B:49:0x0119  */
    /* JADX WARN: Code duplicated, block: B:51:0x011d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0127  */
    /* JADX WARN: Code duplicated, block: B:64:0x0197  */
    /* JADX WARN: Instruction removed from duplicated block: B:109:0x0244, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:52:0x0127, please report this as an issue */
    public static final Quad getSettingsValue(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        Object obj;
        Throwable th;
        boolean z4;
        boolean z5;
        boolean z6;
        Object obj2;
        Throwable th2;
        Context contextCreateContextAsUser = context.createContextAsUser(UserHandle.of(OsBinderCacheUtils.getCurrentUserId()), 4);
        boolean z7 = false;
        String string = null;
        if (FeatureOption.isLiteLiveAlertDevice()) {
            Bundle bundleM = CommunalWidgetHost$.ExternalSyntheticOutline0.m(1004, UMS_QUERY_TYPE_KEY);
            bundleM.putStringArrayList(NM_PROVIDER_QUERY_KEY_SERVICE_IDS, CollectionsKt__CollectionsKt.arrayListOf(new String[]{"268452006"}));
            try {
                Bundle bundleCall = contextCreateContextAsUser.getContentResolver().call(Uri.parse(NM_PROVIDER), NM_PROVIDER_METHOD, (String) null, bundleM);
                string = bundleCall != null ? bundleCall.getString(NM_PROVIDER_RESULT_KEY_RESULT) : null;
                OplusNotificationLogger.logMediaSeedlingDebug(TAG, "resultString " + string);
                if (string == null || string.length() == 0) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "resultString from ums is null or empty");
                    z4 = false;
                    z5 = false;
                    z6 = true;
                } else {
                    JSONObject jSONObject = new JSONArray(string).getJSONObject(0);
                    boolean z8 = jSONObject.getInt("enable") == 1;
                    try {
                        int i = jSONObject.getInt(NM_PROVIDER_RESULT_KEY_CLOSED);
                        z5 = ((i >> 3) & 1) == 0;
                        boolean z9 = ((i >> 6) & 1) == 0;
                        try {
                            OplusNotificationLogger.logMediaSeedling(TAG, "serviceSwitch: " + z8 + "; closeEntryValue: " + i + "; isUmsStatusBarSwitchOpen: " + z5 + "; isUmsNotificationSwitchOpen: " + z9);
                            z7 = z8;
                            z4 = z9;
                            z6 = false;
                        } catch (Throwable th3) {
                            th = th3;
                            z7 = z8;
                            z4 = z9;
                            z6 = false;
                            obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                            th2 = Result.exceptionOrNull-impl(obj2);
                            if (th2 != null) {
                                if (th2 instanceof IllegalArgumentException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call provider error: ", th2.getMessage(), TAG);
                                } else if (th2 instanceof JSONException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th2.getMessage(), TAG);
                                } else if (th2 instanceof SecurityException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call provider security exception: ", th2.getMessage(), TAG);
                                } else {
                                    OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th2.getClass().getSimpleName() + ", " + th2.getMessage());
                                }
                            }
                            return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z5), Boolean.valueOf(z4), Boolean.valueOf(z6));
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        z5 = false;
                        z6 = true;
                        z7 = z8;
                        z4 = false;
                    }
                }
                try {
                    obj2 = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th5) {
                    th = th5;
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                }
            } catch (Throwable th6) {
                th = th6;
                z4 = false;
                z5 = false;
                z6 = true;
            }
            th2 = Result.exceptionOrNull-impl(obj2);
            if (th2 != null) {
                if (th2 instanceof IllegalArgumentException) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call provider error: ", th2.getMessage(), TAG);
                } else if (th2 instanceof JSONException) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th2.getMessage(), TAG);
                } else if (th2 instanceof SecurityException) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call provider security exception: ", th2.getMessage(), TAG);
                } else {
                    OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th2.getClass().getSimpleName() + ", " + th2.getMessage());
                }
            }
            return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z5), Boolean.valueOf(z4), Boolean.valueOf(z6));
        }
        Bundle bundle = new Bundle();
        bundle.putInt(UMS_QUERY_TYPE_KEY, 1001);
        bundle.putString(UMS_SERVICE_ID_KEY, "268452006");
        try {
            Bundle bundleCall2 = contextCreateContextAsUser.getContentResolver().call(Uri.parse(URI_UMS_STATICTIS), URI_UMS_QUERY_METHOD, (String) null, bundle);
            if (bundleCall2 != null) {
                try {
                    string = bundleCall2.getString(UMS_BUNDLE_KEY_FLUID_CLOUD_RESULT);
                    if (string != null || string.length() == 0) {
                        z = true;
                        try {
                            OplusNotificationLogger.logMediaSeedling(TAG, "resultString from ums is null or empty");
                            z2 = true;
                            z7 = false;
                            z3 = false;
                        } catch (Throwable th7) {
                            th = th7;
                            z2 = z;
                            z3 = z7;
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                            th = Result.exceptionOrNull-impl(obj);
                            if (th != null) {
                                if (th instanceof IllegalArgumentException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider error: ", th.getMessage(), TAG);
                                } else if (th instanceof JSONException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th.getMessage(), TAG);
                                } else if (th instanceof SecurityException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider security exception: ", th.getMessage(), TAG);
                                } else {
                                    OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th.getClass().getSimpleName() + ", " + th.getMessage());
                                }
                            }
                            return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z7), Boolean.valueOf(z3), Boolean.valueOf(z2));
                        }
                    } else {
                        JSONObject jSONObject2 = new JSONObject(string);
                        boolean z10 = jSONObject2.getInt(UMS_MEDIA_CONTROLLER_SWITCH) == 1;
                        try {
                            int i2 = jSONObject2.getInt(UMS_CLOSE_ENTRY_KEY);
                            z7 = ((i2 >> 3) & 1) == 0;
                            boolean z11 = ((i2 >> 6) & 1) == 0;
                            try {
                                OplusNotificationLogger.logMediaSeedling(TAG, "serviceSwitch: " + z10 + "; closeEntryValue: " + i2 + "; isUmsStatusBarSwitchOpen: " + z7 + "; isUmsNotificationSwitchOpen: " + z11);
                                z7 = z10;
                                z3 = z11;
                                z2 = false;
                            } catch (Throwable th8) {
                                th = th8;
                                z7 = z10;
                                z3 = z11;
                                z2 = false;
                                obj = Result.constructor-impl(ResultKt.createFailure(th));
                                th = Result.exceptionOrNull-impl(obj);
                                if (th != null) {
                                    if (th instanceof IllegalArgumentException) {
                                        OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider error: ", th.getMessage(), TAG);
                                    } else if (th instanceof JSONException) {
                                        OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th.getMessage(), TAG);
                                    } else if (th instanceof SecurityException) {
                                        OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider security exception: ", th.getMessage(), TAG);
                                    } else {
                                        OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th.getClass().getSimpleName() + ", " + th.getMessage());
                                    }
                                }
                                return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z7), Boolean.valueOf(z3), Boolean.valueOf(z2));
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            z2 = true;
                            z7 = z10;
                            z3 = z7;
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                            th = Result.exceptionOrNull-impl(obj);
                            if (th != null) {
                                if (th instanceof IllegalArgumentException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider error: ", th.getMessage(), TAG);
                                } else if (th instanceof JSONException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th.getMessage(), TAG);
                                } else if (th instanceof SecurityException) {
                                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider security exception: ", th.getMessage(), TAG);
                                } else {
                                    OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th.getClass().getSimpleName() + ", " + th.getMessage());
                                }
                            }
                            return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z7), Boolean.valueOf(z3), Boolean.valueOf(z2));
                        }
                    }
                    try {
                        obj = Result.constructor-impl(Unit.INSTANCE);
                    } catch (Throwable th10) {
                        th = th10;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                } catch (Throwable th11) {
                    th = th11;
                    z7 = false;
                    z3 = false;
                    z2 = true;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                    th = Result.exceptionOrNull-impl(obj);
                    if (th != null) {
                        if (th instanceof IllegalArgumentException) {
                            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider error: ", th.getMessage(), TAG);
                        } else if (th instanceof JSONException) {
                            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th.getMessage(), TAG);
                        } else if (th instanceof SecurityException) {
                            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider security exception: ", th.getMessage(), TAG);
                        } else {
                            OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th.getClass().getSimpleName() + ", " + th.getMessage());
                        }
                    }
                    return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z7), Boolean.valueOf(z3), Boolean.valueOf(z2));
                }
            } else if (string != null) {
                z = true;
                OplusNotificationLogger.logMediaSeedling(TAG, "resultString from ums is null or empty");
                z2 = true;
                z7 = false;
                z3 = false;
                obj = Result.constructor-impl(Unit.INSTANCE);
            } else {
                z = true;
                OplusNotificationLogger.logMediaSeedling(TAG, "resultString from ums is null or empty");
                z2 = true;
                z7 = false;
                z3 = false;
                obj = Result.constructor-impl(Unit.INSTANCE);
            }
        } catch (Throwable th12) {
            th = th12;
            z = true;
        }
        th = Result.exceptionOrNull-impl(obj);
        if (th != null) {
            if (th instanceof IllegalArgumentException) {
                OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider error: ", th.getMessage(), TAG);
            } else if (th instanceof JSONException) {
                OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("jsonObject parse fails: ", th.getMessage(), TAG);
            } else if (th instanceof SecurityException) {
                OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("call ums provider security exception: ", th.getMessage(), TAG);
            } else {
                OplusNotificationLogger.logMediaSeedling(TAG, "unknown exception: " + th.getClass().getSimpleName() + ", " + th.getMessage());
            }
        }
        return new Quad(Boolean.valueOf(z7), Boolean.valueOf(z7), Boolean.valueOf(z3), Boolean.valueOf(z2));
    }

    public static final boolean registerSettingObserver(Context context, ContentObserver contentObserver) {
        Object obj;
        UmsSettingsMode umsSettingsMode = INSTANCE;
        try {
            context.getContentResolver().registerContentObserverAsUser(umsSettingsMode.getObserverUri(), false, contentObserver, UserHandle.ALL);
            OplusNotificationLogger.logMediaSeedling(TAG, "registered settings observer for " + umsSettingsMode.getObserverUri().getAuthority());
            obj = Result.constructor-impl(Boolean.TRUE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "register settings observer failed: " + th2.getClass() + " " + th2.getMessage());
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public final Uri getObserverUri() {
        return (Uri) observerUri$delegate.getValue();
    }

    public final Uri getProviderUri() {
        return (Uri) providerUri$delegate.getValue();
    }
}
