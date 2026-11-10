package com.oplus.systemui.media.seedling.rus;

import android.content.Context;
import androidx.media3.session.MediaControllerImplLegacy$;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaRusUpdateManager$updateRusData$1 implements Runnable {
    public /* synthetic */ List $configList;
    public /* synthetic */ Context $context;
    public /* synthetic */ OplusMediaRusUpdateManager this$0;

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        if (this.$configList.contains(OplusMediaRusUpdateManager.OPLUS_MEDIA_CONTROLLER_CONFIG)) {
            String strAccess$getDataFromProvider = OplusMediaRusUpdateManager.access$getDataFromProvider(this.this$0, this.$context);
            int iAccess$parseVersion = OplusMediaRusUpdateManager.access$parseVersion(this.this$0, strAccess$getDataFromProvider);
            OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, MediaControllerImplLegacy$.ExternalSyntheticOutline0.m(iAccess$parseVersion, "OplusMediaController updateRusData rusVersion: ", " rusXmlValue: ", strAccess$getDataFromProvider, " "));
            if (strAccess$getDataFromProvider == null || strAccess$getDataFromProvider.length() == 0) {
                return;
            }
            int i = this.$context.getSharedPreferences("oplus_media_controller_config_sp", 0).getInt("rus_location_version_key", 0);
            if (iAccess$parseVersion > i) {
                OplusMediaRusUpdateManager.access$parseAndPostApply(this.this$0, this.$context, iAccess$parseVersion, strAccess$getDataFromProvider, "rus xml by rus update");
                return;
            }
            OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "OplusMediaController updateRusData rusVersion: " + iAccess$parseVersion + "  <= localVersion: " + i);
        }
    }
}
