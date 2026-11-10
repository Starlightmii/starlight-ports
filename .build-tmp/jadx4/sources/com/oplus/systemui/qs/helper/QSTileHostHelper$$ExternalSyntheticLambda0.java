package com.oplus.systemui.qs.helper;

import android.content.ComponentName;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSTileHostHelper$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ QSTileHostHelper f$0;

    @Override // java.lang.Runnable
    public final void run() {
        QSTileHostHelper qSTileHostHelper = this.f$0;
        String tileSettings = QsSettingsValueProxy.getTileSettings(qSTileHostHelper.mContext);
        ArrayList arrayList = new ArrayList();
        for (String str : tileSettings == null ? qSTileHostHelper.getDefaultTileSpecs(qSTileHostHelper.mContext) : Arrays.asList(tileSettings.split(OplusPreventModeData.SEPARATOR))) {
            if (str.startsWith("custom(")) {
                if (!QSTileHostHelper.isComponentAvailable(qSTileHostHelper.mContext, ComponentName.unflattenFromString(str.substring(7, str.length() - 1)))) {
                    LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "checkDefaultComponentAvailable remove tileSpec ".concat(str));
                    arrayList.add(str);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            qSTileHostHelper.mHost.removeTile((String) obj);
        }
    }
}
