package com.oplus.utrace.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.compose.ui.node.LayoutNode$;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt__ArraysKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class UtilsKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final String TAG = "UTrace.Lib.Utils";
    private static volatile String processName = "";
    private static final AtomicLong previousTransformLogWindow = new AtomicLong(0);
    private static final String dexFileName = getDexFileName();
    private static volatile String logSuffix = generateLogSuffix();

    public static final boolean checkIntent(Intent intent) {
        Object obj;
        try {
            intent.getBooleanExtra("check_intent", false);
            obj = Result.constructor-impl(Boolean.TRUE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            intent.replaceExtras((Bundle) null);
            Logs.INSTANCE.w(TAG, "checkIntent() exception=" + th2 + " intent=" + intent);
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static final String generateLogSuffix() {
        String[] strArr = {dexFileName, processName, "1.4.18-f23f716"};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 3) {
            String str = strArr[i];
            i++;
            if (str != null && !StringsKt__StringsKt.isBlank(str)) {
                arrayList.add(str);
            }
        }
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, "|", (CharSequence) null, (CharSequence) null, (Function1) null, 62);
        if (StringsKt__StringsKt.isBlank(strJoinToString$default)) {
            strJoinToString$default = null;
        }
        if (strJoinToString$default == null) {
            return null;
        }
        return "[" + strJoinToString$default + ']';
    }

    private static final String getCurrentDexFileName() {
        try {
            Logs logs = Logs.INSTANCE;
            ClassLoader classLoader = logs.getClass().getClassLoader();
            if (logs.getDebuggable()) {
                logs.d(TAG, Intrinsics.stringPlus(classLoader, "getCurrentDexFileName() classLoader="));
            }
            Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
            declaredField.setAccessible(true);
            String firstDexFileNameFromPathList = getFirstDexFileNameFromPathList(declaredField.get(classLoader));
            if (logs.getDebuggable()) {
                logs.d(TAG, Intrinsics.stringPlus(firstDexFileNameFromPathList, "getCurrentDexFileName() result="));
            }
            return firstDexFileNameFromPathList;
        } catch (Throwable th) {
            Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
            if (thM == null) {
                return null;
            }
            Logs logs2 = Logs.INSTANCE;
            if (!logs2.getDebuggable()) {
                return null;
            }
            logs2.d(TAG, Intrinsics.stringPlus(thM.getMessage(), "getCurrentDexFileName() exception="), thM);
            return null;
        }
    }

    private static final String getDexFileName() {
        String name;
        String currentDexFileName = getCurrentDexFileName();
        if (currentDexFileName == null || (name = new File(currentDexFileName).getName()) == null) {
            return null;
        }
        return StringsKt__StringsJVMKt.endsWith(name, ".apk", false) ? LayoutNode$.ExternalSyntheticOutline0.m(4, 0, name) : name;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0062  */
    public static final String getFirstDexFileNameFromPathList(Object obj) throws IllegalAccessException, NoSuchFieldException {
        List listFilterNotNull;
        String str;
        if (obj == null) {
            return null;
        }
        Field declaredField = obj.getClass().getDeclaredField("dexElements");
        declaredField.setAccessible(true);
        Object obj2 = declaredField.get(obj);
        Object[] objArr = obj2 instanceof Object[] ? (Object[]) obj2 : null;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d(TAG, Intrinsics.stringPlus(ArraysKt__ArraysKt.contentDeepToString(objArr), "getFirstDexFileNameFromPathList() dexElements="));
        }
        if (objArr == null || (listFilterNotNull = ArraysKt___ArraysKt.filterNotNull(objArr)) == null) {
            return null;
        }
        for (Object obj3 : listFilterNotNull) {
            Field declaredField2 = obj3.getClass().getDeclaredField("dexFile");
            declaredField2.setAccessible(true);
            Object obj4 = declaredField2.get(obj3);
            if (obj4 == null) {
                str = null;
            } else {
                Field declaredField3 = obj4.getClass().getDeclaredField("mFileName");
                declaredField3.setAccessible(true);
                Object obj5 = declaredField3.get(obj4);
                str = obj5 instanceof String ? (String) obj5 : null;
                if (str == null || !new File(str).isFile()) {
                    str = null;
                }
            }
            if (str != null) {
                return str;
            }
        }
        return null;
    }

    public static final String getProcessName(Context context) {
        if (processName.length() == 0) {
            processName = Application.getProcessName();
            logSuffix = generateLogSuffix();
        }
        return processName;
    }

    public static final void submitSafe(ExecutorService executorService, Runnable runnable) {
        Object obj;
        try {
            obj = Result.constructor-impl(executorService.submit(runnable));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.w(TAG, Intrinsics.stringPlus(th2, "submitSafe() exception="));
    }

    public static final String transformLogMessage(String str) {
        String str2 = logSuffix;
        if (str2 != null) {
            long jCurrentTimeMillis = System.currentTimeMillis() / 500;
            if (previousTransformLogWindow.getAndSet(jCurrentTimeMillis) == jCurrentTimeMillis) {
                str2 = null;
            }
            if (str2 != null) {
                return str + ' ' + str2;
            }
        }
        return str;
    }
}
