package com.oplus.systemui.media.seedling.rus;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import androidx.collection.FloatList$;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaRusUpdateManager$checkAndInitLocalData$1 implements Runnable {
    public /* synthetic */ Context $context;
    public /* synthetic */ OplusMediaRusUpdateManager this$0;

    /* JADX WARN: Code duplicated, block: B:72:0x0084 A[EXC_TOP_SPLITTER, PHI: r2
      0x0084: PHI (r2v4 java.io.InputStream) = (r2v3 java.io.InputStream), (r2v5 java.io.InputStream) binds: [B:33:0x0082, B:39:0x00aa] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v2 */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        String string;
        AssetManager assets;
        Context context = this.$context;
        ?? r5 = 0;
        try {
            try {
                Resources resources = context.getResources();
                inputStreamOpen = (resources == null || (assets = resources.getAssets()) == null) ? null : assets.open("app_systemui_oplus_media_controller_config.xml");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
                    StringBuffer stringBuffer = new StringBuffer();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = "";
                    while (true) {
                        String line = bufferedReader.readLine();
                        objectRef.element = line;
                        if (line == null) {
                            break;
                        } else {
                            stringBuffer.append(line);
                        }
                    }
                    string = stringBuffer.toString();
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (IOException e) {
                            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("close readFromFile IOException: ", e.getMessage(), OplusMediaRusUpdateManager.TAG);
                        }
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "readFromFile FileNotFoundException: " + e.getMessage());
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (IOException e3) {
                            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("close readFromFile IOException: ", e3.getMessage(), OplusMediaRusUpdateManager.TAG);
                        }
                    }
                    string = null;
                } catch (IOException e4) {
                    e = e4;
                    OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "readFromFile IOException: " + e.getMessage());
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    string = null;
                }
            } catch (Throwable th2) {
                th = th2;
                r5 = context;
                if (r5 != 0) {
                    throw th;
                }
                try {
                    r5.close();
                    throw th;
                } catch (IOException e5) {
                    OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("close readFromFile IOException: ", e5.getMessage(), OplusMediaRusUpdateManager.TAG);
                    throw th;
                }
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            inputStreamOpen = null;
        } catch (IOException e7) {
            e = e7;
            inputStreamOpen = null;
        } catch (Throwable th3) {
            th = th3;
            if (r5 != 0) {
                throw th;
            }
            r5.close();
            throw th;
        }
        int iAccess$parseVersion = OplusMediaRusUpdateManager.access$parseVersion(this.this$0, string);
        String strAccess$getDataFromProvider = OplusMediaRusUpdateManager.access$getDataFromProvider(this.this$0, this.$context);
        int iAccess$parseVersion2 = OplusMediaRusUpdateManager.access$parseVersion(this.this$0, strAccess$getDataFromProvider);
        boolean z = strAccess$getDataFromProvider != null;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("localVersion: ", iAccess$parseVersion, iAccess$parseVersion2, ", rusVersion: ", ", rusXmlValue: ");
        sbM.append(z);
        OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, sbM.toString());
        if (iAccess$parseVersion2 > iAccess$parseVersion && strAccess$getDataFromProvider != null && strAccess$getDataFromProvider.length() != 0) {
            OplusMediaRusUpdateManager.access$parseAndPostApply(this.this$0, this.$context, iAccess$parseVersion2, strAccess$getDataFromProvider, "rus xml");
            return;
        }
        if (string != null && string.length() != 0) {
            OplusMediaRusUpdateManager.access$parseAndPostApply(this.this$0, this.$context, iAccess$parseVersion, string, "local xml");
            return;
        }
        OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "local xml is null, load config from SP cache");
        SharedPreferences sharedPreferences = this.$context.getSharedPreferences("oplus_media_controller_config_sp", 0);
        int i = sharedPreferences.getInt("rus_location_version_key", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("rus_content_key", null);
        if (stringSet == null) {
            stringSet = SetsKt__SetsKt.emptySet();
        }
        Set<String> stringSet2 = sharedPreferences.getStringSet("rus_blacklist_key", null);
        if (stringSet2 == null) {
            stringSet2 = SetsKt__SetsKt.emptySet();
        }
        OplusMediaRusUpdateManager.applyConfig(new MediaRusConfig(i, OplusMediaRusUpdateManager.parsePackageListFromStringSet(stringSet), OplusMediaRusUpdateManager.parsePackageListFromStringSet(stringSet2), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_action_rule_key"), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_action_rule_qs_key"), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_action_index_key"), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_lyric_entrance_key"), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_lyric_enable_key"), OplusMediaRusUpdateManager.getMapJSONFromSP(sharedPreferences, "rus_oplus_action_config_key")));
    }
}
