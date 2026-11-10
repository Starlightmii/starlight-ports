package com.oplus.systemui.qs.pipeline.data.event;

import android.content.Context;
import android.util.ArraySet;
import androidx.car.app.SurfaceContainer$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.common.backuprestore.proxy.AbsBackupRestoreProxy;
import com.oplus.systemui.qs.pipeline.data.repository.StandardQSSettingsRestoredRepository$restoreData$1$onRestoreData$1;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSBackupRestoreProxy extends AbsBackupRestoreProxy {
    private static final String AUTO_ADD_SETTING = "qs_auto_tiles";
    public static final Companion Companion = new Companion();
    private static final String SEP_TILES_SETTING = "sysui_sep_qs_tiles";
    private static final String TILES_SETTING = "sysui_qs_tiles";
    private final CopyOnWriteArrayList<CallBack> callBacks = new CopyOnWriteArrayList<>();
    private final QSHost qsHost;
    private final SecureSettings secureSettings;
    private final Provider separateQSManagerProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CallBack {
        void onRestoreTileData(RestoreData restoreData);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSBackupRestoreProxy(QSHost qSHost, SecureSettings secureSettings, Provider provider) {
        this.qsHost = qSHost;
        this.secureSettings = secureSettings;
        this.separateQSManagerProvider = provider;
    }

    private final String getAutoAddedTileString() {
        String stringForUser = this.secureSettings.getStringForUser(AUTO_ADD_SETTING, this.qsHost.getUserId());
        return stringForUser == null ? "" : stringForUser;
    }

    private final String getCurrentSepTileString() {
        String strBackupTiles;
        OplusSeparateQSPlugin separateQSPlugin = ((OplusSeparateQSManager) this.separateQSManagerProvider.get()).getSeparateQSPlugin();
        return (separateQSPlugin == null || (strBackupTiles = separateQSPlugin.backupTiles()) == null) ? "" : strBackupTiles;
    }

    private final String getCurrentTileString() {
        String stringForUser = this.secureSettings.getStringForUser(TILES_SETTING, this.qsHost.getUserId());
        return stringForUser == null ? "" : stringForUser;
    }

    public final void addCallBack(CallBack callBack) {
        this.callBacks.add(callBack);
    }

    public ArraySet<String> loadKeys(Context context) {
        ArraySet<String> arraySet = new ArraySet<>();
        arraySet.add(TILES_SETTING);
        arraySet.add(AUTO_ADD_SETTING);
        arraySet.add(SEP_TILES_SETTING);
        return arraySet;
    }

    public HashMap<String, String> onBackup(Context context) {
        int userId = this.qsHost.getUserId();
        HashMap<String, String> map = new HashMap<>();
        String currentTileString = getCurrentTileString();
        String autoAddedTileString = getAutoAddedTileString();
        map.put(TILES_SETTING, currentTileString);
        map.put(AUTO_ADD_SETTING, autoAddedTileString);
        map.put(SEP_TILES_SETTING, getCurrentSepTileString());
        LogUtil.i$default(LogUtil.TAG_QS, getTAG(), SurfaceContainer$.ExternalSyntheticOutline0.m(userId, ")", ExifInterface$.ExternalSyntheticOutline1.m("onBackup: tileList=[", currentTileString, "], autoAddedTileList=[", autoAddedTileString, "] for User(id=")), null, 8);
        return map;
    }

    public void onRestore(Context context, HashMap<String, String> map) {
        OplusSeparateQSPlugin separateQSPlugin;
        int userId = this.qsHost.getUserId();
        String safeStringValue = getSafeStringValue(map, TILES_SETTING);
        String safeStringValue2 = getSafeStringValue(map, AUTO_ADD_SETTING);
        String stringValue = getStringValue(map, SEP_TILES_SETTING);
        boolean z = stringValue == null;
        TilesSettingConverter tilesSettingConverter = TilesSettingConverter.INSTANCE;
        RestoreData restoreData = new RestoreData(tilesSettingConverter.toTilesList(safeStringValue), tilesSettingConverter.toTilesSet(safeStringValue2), userId, stringValue);
        LogUtil.i$default(LogUtil.TAG_QS, getTAG(), "onRestore: from settings: " + z + " restoreData=" + restoreData, null, 8);
        if (safeStringValue.length() > 0) {
            Iterator<T> it = this.callBacks.iterator();
            while (it.hasNext()) {
                ((StandardQSSettingsRestoredRepository$restoreData$1$onRestoreData$1) ((CallBack) it.next())).onRestoreTileData(restoreData);
            }
        }
        if (!z || (separateQSPlugin = ((OplusSeparateQSManager) this.separateQSManagerProvider.get()).getSeparateQSPlugin()) == null) {
            return;
        }
        separateQSPlugin.restoreTiles();
    }

    public final void removeCallBack(CallBack callBack) {
        this.callBacks.remove(callBack);
    }
}
