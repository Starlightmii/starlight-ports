package com.oplus.systemui.media.seedling.utils;

import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.wrapper.app.ActivityThread;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FeatureOption$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        String string = AppFeatureProviderUtils.getString(ActivityThread.currentApplication().getContentResolver(), "com.oplus.mediacontroller.fluidConfig", "");
        if (string.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                boolean zOptBoolean = jSONObject.optBoolean("disable_anim", false);
                int iOptInt = jSONObject.optInt("temperature_threshold", 47);
                OplusNotificationLogger.logMediaSeedling("FeatureOption", "mediaController fluidConfig feature: ".concat(string));
                return new Pair(Boolean.valueOf(zOptBoolean), Integer.valueOf(iOptInt));
            } catch (JSONException e) {
                OplusNotificationLogger.logMediaSeedling("FeatureOption", "json exception: " + e.getMessage() + "; fluidConfig: " + string);
            }
        }
        return new Pair(Boolean.FALSE, 47);
    }
}
