package com.oplus.systemui.navigationbar.gesture.otherbusiness;

import android.content.Context;
import com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenBusiness;
import com.oplus.systemui.navigationbar.utils.OcrScreenCompatVersion;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OtherBusinessManager {
    private IOtherBusiness business;
    private final Context context;

    public OtherBusinessManager(Context context) {
        this.context = context;
    }

    public final IOtherBusiness createBusinessInstance() {
        OcrScreenCompatVersion.INSTANCE.getClass();
        return (OcrScreenCompatVersion.isMetaDataSupport && OplusNavBarUtils.isSupportOcrScreen()) ? new OplusOcrScreenBusiness(this.context) : new SpeedChassistMainBusiness(this.context);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void initOtherBusinessManager() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return;
        }
        IOtherBusiness iOtherBusinessCreateBusinessInstance = createBusinessInstance();
        this.business = iOtherBusinessCreateBusinessInstance;
        iOtherBusinessCreateBusinessInstance.init();
        if (OplusNavBarUtils.isSupportOcrScreen()) {
            OtherBusinessManager$setupMetaDataListener$1 otherBusinessManager$setupMetaDataListener$1 = new OtherBusinessManager$setupMetaDataListener$1();
            otherBusinessManager$setupMetaDataListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            OcrScreenCompatVersion.INSTANCE.addMetaDataChangeListener(otherBusinessManager$setupMetaDataListener$1);
        }
    }
}
