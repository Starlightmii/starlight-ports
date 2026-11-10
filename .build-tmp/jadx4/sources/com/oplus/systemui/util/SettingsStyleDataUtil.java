package com.oplus.systemui.util;

import android.content.Context;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.car.app.serialization.Bundler$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.settings.UserFileManager;
import com.oplus.keyguard.config.Config;
import com.oplus.keyguard.config.ConfigKt;
import com.oplus.keyguard.config.KeyguardStyleConfig;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.systemui.keyguard.data.personalise.PersonalityData;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.CRC32;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__FileReadWriteKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.Path$;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SettingsStyleDataUtil {
    private static final String FILE_DATA_ESCAPE = "keyguard_style_personality_data_escape.json";
    private static final String FILE_SECONDARY_DATA_ESCAPE = "keyguard_style_personality_data_secondary_display_escape.json";
    private static final String TAG = "SettingsStyleDataUtil";
    public static volatile boolean debugLog;
    public static final SettingsStyleDataUtil INSTANCE = new SettingsStyleDataUtil();
    private static final ReentrantReadWriteLock fileLock = new ReentrantReadWriteLock();

    /* JADX INFO: renamed from: com.oplus.systemui.util.SettingsStyleDataUtil$getCurrentThemeName$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ UserFileManager $userFileManager;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(UserFileManager userFileManager, Continuation continuation) {
            super(2, continuation);
            this.$userFileManager = userFileManager;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$userFileManager, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            PersonalityData personalityDataFromJsonString;
            KeyguardStyleConfig keyguardStyleConfigFromJsonString;
            String pkg;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            String strLoadStyleDataFile = SettingsStyleDataUtil.loadStyleDataFile(this.$userFileManager, false);
            if (strLoadStyleDataFile == null) {
                return "com.oplus.keyguard.clock.base";
            }
            String str = strLoadStyleDataFile.length() > 0 ? strLoadStyleDataFile : null;
            return (str == null || (personalityDataFromJsonString = ConfigKt.fromJsonString(PersonalityData.class, str, new SettingsStyleDataUtil$$ExternalSyntheticLambda0(3))) == null || (keyguardStyleConfigFromJsonString = ConfigKt.fromJsonString(KeyguardStyleConfig.class, personalityDataFromJsonString.getStyleData(), new SettingsStyleDataUtil$$ExternalSyntheticLambda0(4))) == null || (pkg = keyguardStyleConfigFromJsonString.getPkg()) == null) ? "com.oplus.keyguard.clock.base" : pkg;
        }
    }

    public static final String changeStyleDataToUnescapeOrEscape(Context context, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (z) {
            Config personalityData = (PersonalityData) ConfigKt.fromJsonString(PersonalityData.class, str, new SettingsStyleDataUtil$$ExternalSyntheticLambda0(0));
            if (personalityData == null) {
                personalityData = new PersonalityData((String) null, 0, 0L, false, false, false, 0, (String) null, (String) null, (String) null, 65535);
            }
            SettingsStyleDataUtil$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new SettingsStyleDataUtil$.ExternalSyntheticLambda1(0);
            externalSyntheticLambda1.f$0 = context;
            VarHandle.storeStoreFence();
            String jsonString = ConfigKt.toJsonString(personalityData, externalSyntheticLambda1);
            return jsonString == null ? str : jsonString;
        }
        SettingsStyleDataUtil$.ExternalSyntheticLambda1 externalSyntheticLambda2 = new SettingsStyleDataUtil$.ExternalSyntheticLambda1(1);
        externalSyntheticLambda2.f$0 = context;
        VarHandle.storeStoreFence();
        Config personalityData2 = (PersonalityData) ConfigKt.fromJsonString(PersonalityData.class, str, externalSyntheticLambda2);
        if (personalityData2 == null) {
            personalityData2 = new PersonalityData((String) null, 0, 0L, false, false, false, 0, (String) null, (String) null, (String) null, 65535);
        }
        String jsonString2 = ConfigKt.toJsonString(personalityData2, new SettingsStyleDataUtil$$ExternalSyntheticLambda0(1));
        if (jsonString2 == null) {
            jsonString2 = str;
        }
        Config personalityData3 = (PersonalityData) ConfigKt.fromJsonString(PersonalityData.class, jsonString2, new SettingsStyleDataUtil$$ExternalSyntheticLambda0(2));
        if (personalityData3 == null) {
            personalityData3 = new PersonalityData((String) null, 0, 0L, false, false, false, 0, (String) null, (String) null, (String) null, 65535);
        }
        return ConfigKt.toJsonString$default(personalityData3, (Function1) null, 1, (Object) null);
    }

    public static final void dump(PrintWriter printWriter, boolean z, UserFileManager userFileManager) {
        String strLoadStyleDataFile = loadStyleDataFile(userFileManager, z);
        printWriter.println("escapedData isUnescape:" + isUnescape(strLoadStyleDataFile));
        printWriter.println("escapedData:" + strLoadStyleDataFile);
    }

    public static final Object getCurrentThemeName(UserFileManager userFileManager, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(userFileManager, null), continuation);
    }

    public static final boolean getDebugLog() {
        return debugLog;
    }

    public static /* synthetic */ void getDebugLog$annotations() {
    }

    public static final int getUserId() {
        return Process.myUserHandle().isSystem() ? KeyguardUpdateMonitor.getCurrentUser() : UserHandle.myUserId();
    }

    public static final boolean isUnescape(String str) {
        Object obj;
        boolean z = false;
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            String strOptString = new JSONObject(str).optString("styleData", "");
            strOptString.getClass();
            if (strOptString.length() != 0) {
                JSONObject jSONObject = new JSONObject(strOptString);
                if (jSONObject.has("widgetStyleConfig") && jSONObject.optJSONObject("widgetStyleConfig") != null) {
                    z = true;
                }
            }
            obj = Result.constructor-impl(Boolean.valueOf(z));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static final String loadStyleDataFile(UserFileManager userFileManager, boolean z) {
        Object obj;
        String text;
        Object obj2 = null;
        if (userFileManager == null) {
            KgdLogUtil.i(TAG, "loadStyleDataFile: userFileManager is null");
            return null;
        }
        ReentrantReadWriteLock.ReadLock lock = fileLock.readLock();
        lock.lock();
        try {
            int currentUser = KeyguardUpdateMonitor.getCurrentUser();
            String str = z ? FILE_SECONDARY_DATA_ESCAPE : FILE_DATA_ESCAPE;
            try {
                File file = userFileManager.getFile(str, currentUser);
                if (file.exists()) {
                    text = FilesKt__FileReadWriteKt.readText(file, Charsets.UTF_8);
                    log("loadStyleDataFile: Successfully loaded " + file.getAbsolutePath() + " for user " + currentUser + ", isSecondary:" + z + " \n data:" + text + "}");
                } else {
                    KgdLogUtil.i(TAG, "loadStyleDataFile: File not exists " + file.getAbsolutePath() + " for user " + currentUser);
                    text = null;
                }
                obj = Result.constructor-impl(text);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                KgdLogUtil.i(TAG, "loadStyleDataFile failed: fileName=" + str + ", userId=" + currentUser + ", exception: " + th2.getMessage());
            }
            if (!(obj instanceof Result.Failure)) {
                obj2 = obj;
            }
            String str2 = (String) obj2;
            lock.unlock();
            return str2;
        } catch (Throwable th3) {
            lock.unlock();
            throw th3;
        }
    }

    public static final void log(String str) {
        if (debugLog) {
            KgdLogUtil.i(TAG, str);
        }
    }

    public static final void migrateSettingsDataToFile(Context context, UserFileManager userFileManager) {
        log("migrateSettingsDataToFile");
        if (userFileManager == null) {
            KgdLogUtil.i(TAG, "migrateSettingsDataToFile: userFileManager is null");
            return;
        }
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        for (Pair pair : CollectionsKt__CollectionsKt.listOf(new Pair[]{TuplesKt.to(Boolean.TRUE, "keyguard_style_personality_data_secondary_dislay"), TuplesKt.to(Boolean.FALSE, "keyguard_style_personality_data")})) {
            boolean zBooleanValue = ((Boolean) pair.component1()).booleanValue();
            String str = (String) pair.component2();
            MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
            String systemStringValue = companion.getSystemStringValue(context, str, currentUser);
            String str2 = zBooleanValue ? "secondary" : "primary";
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(currentUser, "migrateSettingsDataToFile, ", str2, " display, userId:", ", data:\n ");
            sbM.append(systemStringValue);
            log(sbM.toString());
            if (systemStringValue != null && systemStringValue.length() != 0) {
                try {
                    if (saveStyleDataFile(context, systemStringValue, userFileManager, zBooleanValue)) {
                        companion.setSystemStringValue(context, str, "", currentUser);
                        log("migrateSettingsDataToFile: Successfully migrated and deleted " + str2 + " display data");
                    } else {
                        KgdLogUtil.i(TAG, "migrateSettingsDataToFile: Failed to save " + str2 + " display data to file");
                    }
                } catch (IOException e) {
                    KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("migrateSettingsDataToFile: IOException while migrating ", str2, " display data, exception: ", e.getMessage(), TAG);
                } catch (IllegalArgumentException e2) {
                    KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("migrateSettingsDataToFile: IllegalArgumentException while migrating ", str2, " display data, exception: ", e2.getMessage(), TAG);
                } catch (SecurityException e3) {
                    KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("migrateSettingsDataToFile: SecurityException while migrating ", str2, " display data, exception: ", e3.getMessage(), TAG);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0081 A[Catch: all -> 0x007f, TryCatch #1 {all -> 0x007f, blocks: (B:21:0x006c, B:23:0x0072, B:25:0x0078, B:32:0x008c, B:34:0x0091, B:35:0x0099, B:28:0x0081, B:31:0x0089), top: B:43:0x006c }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0091 A[Catch: all -> 0x007f, TryCatch #1 {all -> 0x007f, blocks: (B:21:0x006c, B:23:0x0072, B:25:0x0078, B:32:0x008c, B:34:0x0091, B:35:0x0099, B:28:0x0081, B:31:0x0089), top: B:43:0x006c }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ac  */
    /* JADX INFO: renamed from: saveSingleFile-0E7RQCE, reason: not valid java name */
    public static final Object m477saveSingleFile0E7RQCE(String str, String str2, File file) {
        File file2;
        Object obj;
        Object obj2;
        Throwable th;
        File file3 = new File(file.getParent(), Bundler$.ExternalSyntheticOutline0.m(file.getName(), ".tmp"));
        if (file.exists()) {
            file2 = new File(file.getParent(), Bundler$.ExternalSyntheticOutline0.m(file.getName(), ".bak"));
            FilesKt__UtilsKt.copyTo$default(file, file2, true, 4);
        } else {
            file2 = null;
        }
        if (str == null) {
            str = "";
        }
        try {
            FilesKt__FileReadWriteKt.writeText(file3, str, Charsets.UTF_8);
            if (!file3.renameTo(file)) {
                throw new IOException("Failed to rename temp file to ".concat(str2));
            }
            if (file2 != null) {
                file2.delete();
            }
            obj = Result.constructor-impl(file);
            if (Result.exceptionOrNull-impl(obj) != null) {
                if (file2 != null) {
                    try {
                        if (file2.exists() && file.exists()) {
                            file.delete();
                            file2.renameTo(file);
                        } else if (file.exists() && file2 == null) {
                            file.delete();
                        }
                        file3.delete();
                        obj2 = Result.constructor-impl(file2 != null ? Boolean.valueOf(file2.delete()) : null);
                    } catch (Throwable th2) {
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
                    }
                    th = Result.exceptionOrNull-impl(obj2);
                    if (th != null) {
                        KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("Rollback failed for ", str2, ", exception: ", th.getMessage(), TAG);
                    }
                } else {
                    if (file.exists()) {
                        file.delete();
                    }
                    file3.delete();
                    obj2 = Result.constructor-impl(file2 != null ? Boolean.valueOf(file2.delete()) : null);
                    th = Result.exceptionOrNull-impl(obj2);
                    if (th != null) {
                        KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("Rollback failed for ", str2, ", exception: ", th.getMessage(), TAG);
                    }
                }
            }
            return obj;
        } catch (Throwable th3) {
            obj = Result.constructor-impl(ResultKt.createFailure(th3));
        }
    }

    public static final boolean saveStyleDataFile(Context context, String str, UserFileManager userFileManager, boolean z) {
        String strValueOf;
        boolean z2;
        int i = 0;
        if (userFileManager == null) {
            KgdLogUtil.i(TAG, "saveStyleDataFile: userFileManager is null");
            return false;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = fileLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        if (str != null) {
            try {
                if (isUnescape(str)) {
                    log("saveStyleDataFile: escapeStyleData is not escaped format, converting to escaped format");
                    String strChangeStyleDataToUnescapeOrEscape = changeStyleDataToUnescapeOrEscape(context, str, false);
                    if (strChangeStyleDataToUnescapeOrEscape != null) {
                        str = strChangeStyleDataToUnescapeOrEscape;
                    }
                }
            } finally {
                while (i < readHoldCount) {
                    lock.lock();
                    i++;
                }
                writeLock.unlock();
            }
        }
        log("saveStyleDataFile escapeStyleData:" + str);
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        String str2 = z ? FILE_SECONDARY_DATA_ESCAPE : FILE_DATA_ESCAPE;
        File file = userFileManager.getFile(str2, currentUser);
        Object objM477saveSingleFile0E7RQCE = m477saveSingleFile0E7RQCE(str, str2, file);
        if (objM477saveSingleFile0E7RQCE instanceof Result.Failure) {
            Throwable th = Result.exceptionOrNull-impl(objM477saveSingleFile0E7RQCE);
            KgdLogUtil.i(TAG, "saveStyleDataFile failed: userId=" + currentUser + ", escape file save failed, exception: " + (th != null ? th.getMessage() : null));
            z2 = false;
        } else {
            if (str == null || str.length() == 0) {
                strValueOf = "0";
            } else {
                CRC32 crc32 = new CRC32();
                crc32.update(str.getBytes(Charsets.UTF_8));
                strValueOf = String.valueOf(crc32.getValue());
            }
            Settings.System.putStringForUser(context.getContentResolver(), z ? "keyguard_style_personality_data_secondary_display_state" : "keyguard_style_personality_data_state", strValueOf, currentUser);
            log("saveStyleDataFile: Successfully saved escape file for user " + currentUser + ", escapeFile path = " + file.getAbsolutePath());
            z2 = true;
        }
        while (i < readHoldCount) {
            lock.lock();
            i++;
        }
        return z2;
    }

    public static final void setDebugLog(boolean z) {
        debugLog = z;
    }
}
