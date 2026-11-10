package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialBuriedUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.Calendar;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialBuriedHelper implements PictorialBuriedHelper {
    public static volatile TbPictorialBuriedHelper sInstance;
    public long mBuriedPointUpdateTime;
    public TBBuriedPoint mPoint;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TBBuriedPoint {
        public int TABOOLA_ACTIVE_0;
        public int TABOOLA_ACTIVE_1;
        public int TABOOLA_ACTIVE_2;
        public int TABOOLA_INSTALLED;
        public String mUUID;
    }

    public static TbPictorialBuriedHelper getInstance() {
        if (sInstance == null) {
            synchronized (TbPictorialBuriedHelper.class) {
                try {
                    if (sInstance == null) {
                        TbPictorialBuriedHelper tbPictorialBuriedHelper = new TbPictorialBuriedHelper();
                        TBBuriedPoint tBBuriedPoint = new TBBuriedPoint();
                        tBBuriedPoint.mUUID = "";
                        tbPictorialBuriedHelper.mPoint = tBBuriedPoint;
                        sInstance = tbPictorialBuriedHelper;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public final boolean isBuriedEnable() {
        return ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn() && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isTbStyle();
    }

    public final void onBackUp$2(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("tb_pictorial_event", 0).edit();
        TBBuriedPoint tBBuriedPoint = this.mPoint;
        editorEdit.putInt("taboola_installed", tBBuriedPoint.TABOOLA_INSTALLED);
        editorEdit.putInt("taboola_active_0", tBBuriedPoint.TABOOLA_ACTIVE_0);
        editorEdit.putInt("taboola_active_1", tBBuriedPoint.TABOOLA_ACTIVE_1);
        editorEdit.putInt("taboola_active_2", tBBuriedPoint.TABOOLA_ACTIVE_2);
        editorEdit.putString("uuid", tBBuriedPoint.mUUID);
        editorEdit.commit();
        KgdLogUtil.d("TbPictorialBuriedHelper", "onBackUp Point: TABOOLA_INSTALLED:" + tBBuriedPoint.TABOOLA_INSTALLED + ",TABOOLA_ACTIVE_0:" + tBBuriedPoint.TABOOLA_ACTIVE_0 + ",TABOOLA_ACTIVE_1:" + tBBuriedPoint.TABOOLA_ACTIVE_1 + ",TABOOLA_ACTIVE_2:" + tBBuriedPoint.TABOOLA_ACTIVE_2);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper
    public final void onStatistic(Context context) {
        uploadUserEvent$2(context);
        RmPictorialBuriedUtils.uploadRmPictorialEvent(context);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper
    public final void switchPictorialApp(Context context) {
        uploadUserEvent$2(context);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper
    public final void uploadActiveEvent(Context context, String str) {
        TBBuriedPoint tBBuriedPoint = this.mPoint;
        if (isBuriedEnable()) {
            str.hashCode();
            switch (str) {
                case "taboola_active_0":
                    tBBuriedPoint.TABOOLA_ACTIVE_0++;
                    break;
                case "taboola_active_1":
                    if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn() && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isAutoPlaySwitchOn() && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isTbStyle()) {
                        tBBuriedPoint.TABOOLA_ACTIVE_1++;
                        break;
                    }
                    break;
                case "taboola_active_2":
                    if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn() && !((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isAutoPlaySwitchOn() && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isTbStyle()) {
                        tBBuriedPoint.TABOOLA_ACTIVE_2++;
                        break;
                    }
                    break;
            }
            onBackUp$2(context);
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper
    public final void uploadInstalledEvent(Context context) {
        if (isBuriedEnable()) {
            this.mPoint.TABOOLA_INSTALLED = 1;
            onBackUp$2(context);
        }
    }

    public final void uploadUserEvent$2(Context context) {
        TBBuriedPoint tBBuriedPoint = this.mPoint;
        if (this.mBuriedPointUpdateTime == 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("tb_pictorial_event", 0);
            tBBuriedPoint.mUUID = sharedPreferences.getString("uuid", tBBuriedPoint.mUUID);
            tBBuriedPoint.TABOOLA_INSTALLED = sharedPreferences.getInt("taboola_installed", tBBuriedPoint.TABOOLA_INSTALLED);
            tBBuriedPoint.TABOOLA_ACTIVE_0 = sharedPreferences.getInt("taboola_active_0", tBBuriedPoint.TABOOLA_ACTIVE_0);
            tBBuriedPoint.TABOOLA_ACTIVE_1 = sharedPreferences.getInt("taboola_active_1", tBBuriedPoint.TABOOLA_ACTIVE_1);
            tBBuriedPoint.TABOOLA_ACTIVE_2 = sharedPreferences.getInt("taboola_active_2", tBBuriedPoint.TABOOLA_ACTIVE_2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(11, 0);
            calendar.set(12, 0);
            this.mBuriedPointUpdateTime = calendar.getTimeInMillis();
        }
        HashMap map = new HashMap();
        String str = tBBuriedPoint.mUUID;
        if (str != null) {
            map.put("uuid", str);
        }
        map.put("taboola_installed", String.valueOf(tBBuriedPoint.TABOOLA_INSTALLED));
        map.put("taboola_active_0", String.valueOf(tBBuriedPoint.TABOOLA_ACTIVE_0));
        map.put("taboola_active_1", String.valueOf(tBBuriedPoint.TABOOLA_ACTIVE_1));
        map.put("taboola_active_2", String.valueOf(tBBuriedPoint.TABOOLA_ACTIVE_2));
        KgdLogUtil.d("TbPictorialBuriedHelper", "start upload for taboola");
        OplusTrackProxyUtil.onCommon(context, "20082", "tb_pictorial_event", map);
        tBBuriedPoint.TABOOLA_INSTALLED = 0;
        tBBuriedPoint.TABOOLA_ACTIVE_0 = 0;
        tBBuriedPoint.TABOOLA_ACTIVE_1 = 0;
        tBBuriedPoint.TABOOLA_ACTIVE_2 = 0;
        onBackUp$2(context);
    }
}
