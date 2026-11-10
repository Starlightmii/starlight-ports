package com.oplus.utrace.sdk;

import android.net.Uri;
import android.os.Bundle;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplus.utrace.utils.Logs;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceCompat {
    public static final UTraceCompat INSTANCE = new UTraceCompat();
    private static final String KEY_LEGACY = "utracecontext";
    private static final String KEY_NEW = "utrace2";
    private static final String TAG = "UTrace.Sdk.Compat";

    public static /* synthetic */ UTraceContext readFromBundle$default(UTraceCompat uTraceCompat, Bundle bundle, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = KEY_LEGACY;
        }
        if ((i & 4) != 0) {
            str2 = KEY_NEW;
        }
        return uTraceCompat.readFromBundle(bundle, str, str2);
    }

    public static /* synthetic */ UTraceContext readFromUri$default(UTraceCompat uTraceCompat, Uri uri, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = KEY_LEGACY;
        }
        if ((i & 4) != 0) {
            str2 = KEY_NEW;
        }
        return uTraceCompat.readFromUri(uri, str, str2);
    }

    public static /* synthetic */ Bundle writeToBundle$default(UTraceCompat uTraceCompat, UTraceContext uTraceContext, Bundle bundle, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str = KEY_LEGACY;
        }
        if ((i & 8) != 0) {
            str2 = KEY_NEW;
        }
        return uTraceCompat.writeToBundle(uTraceContext, bundle, str, str2);
    }

    public static /* synthetic */ Uri.Builder writeToUri$default(UTraceCompat uTraceCompat, UTraceContext uTraceContext, Uri.Builder builder, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str = KEY_LEGACY;
        }
        if ((i & 8) != 0) {
            str2 = KEY_NEW;
        }
        return uTraceCompat.writeToUri(uTraceContext, builder, str, str2);
    }

    public final UTraceContext readFromBundle(Bundle bundle, String str, String str2) {
        try {
            if (bundle.containsKey(str2)) {
                String string = bundle.getString(str2);
                if (string == null) {
                    return null;
                }
                return UTraceContextV2.Companion.fromJSONString(string);
            }
            UTraceContext uTraceContext = (UTraceContext) bundle.getParcelable(str);
            if (uTraceContext == null) {
                return null;
            }
            return UTraceContextV2.Companion.fromLegacyObj$utrace_sdk_liteRelease(uTraceContext);
        } catch (Throwable th) {
            Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
            if (thM != null) {
                Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(thM.getMessage(), "readFromBundle, error: "), thM);
            }
            return null;
        }
    }

    public final UTraceContext readFromJsonString(String str) {
        try {
            return UTraceContextV2.Companion.fromJSONString(str);
        } catch (Throwable th) {
            Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
            if (thM == null) {
                return null;
            }
            Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(thM, "readFromJsonString, error: "));
            return null;
        }
    }

    public final UTraceContext readFromUri(Uri uri, String str, String str2) {
        Object obj;
        try {
            String queryParameter = uri.getQueryParameter(str2);
            if (queryParameter != null) {
                return UTraceContextV2.Companion.fromJSONString(queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter(str);
            if (queryParameter2 != null) {
                return UTraceContextV2.Companion.fromLegacyObj$utrace_sdk_liteRelease(UTraceContext.CREATOR.unserialize(queryParameter2));
            }
            obj = Result.constructor-impl((Object) null);
            Throwable th = Result.exceptionOrNull-impl(obj);
            if (th != null) {
                Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th.getMessage(), "readFromUri, error: "), th);
            }
            return null;
        } catch (Throwable th2) {
            obj = Result.constructor-impl(ResultKt.createFailure(th2));
        }
    }

    public final Bundle writeToBundle(UTraceContext uTraceContext, Bundle bundle, String str, String str2) {
        Object obj;
        try {
            if (uTraceContext instanceof UTraceContextV2) {
                bundle.putParcelable(str, ((UTraceContextV2) uTraceContext).toLegacyObj());
                bundle.putString(str2, ((UTraceContextV2) uTraceContext).toJsonString());
            } else {
                bundle.putParcelable(str, uTraceContext);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th2.getMessage(), "writeToBundle, error: "), th2);
        }
        return bundle;
    }

    public final String writeToJsonString(UTraceContext uTraceContext) {
        try {
            return uTraceContext instanceof UTraceContextV2 ? ((UTraceContextV2) uTraceContext).toJsonString() : UTraceContextV2.Companion.fromLegacyObj$utrace_sdk_liteRelease(uTraceContext).toJsonString();
        } catch (Throwable th) {
            Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
            if (thM == null) {
                return "";
            }
            Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(thM.getMessage(), "writeToJsonString, error: "), thM);
            return "";
        }
    }

    public final Uri.Builder writeToUri(UTraceContext uTraceContext, Uri.Builder builder, String str, String str2) {
        Object obj;
        Uri.Builder builderAppendQueryParameter;
        try {
            if (uTraceContext instanceof UTraceContextV2) {
                builder.appendQueryParameter(str, ((UTraceContextV2) uTraceContext).toLegacyObj().serializeToString());
                builderAppendQueryParameter = builder.appendQueryParameter(str2, ((UTraceContextV2) uTraceContext).toJsonString());
            } else {
                builderAppendQueryParameter = builder.appendQueryParameter(str, uTraceContext.serializeToString());
            }
            obj = Result.constructor-impl(builderAppendQueryParameter);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th2.getMessage(), "writeToUri, error: "), th2);
        }
        return builder;
    }
}
