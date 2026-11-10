package com.oplus.systemui.keyguard.wallpaper.pictorial.app;

import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialAppDataImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PictorialAppDataImpl f$0;

    public /* synthetic */ PictorialAppDataImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PictorialAppDataImpl pictorialAppDataImpl = this.f$0;
        switch (i) {
            case 0:
                pictorialAppDataImpl.mIsSupportWidgetTaglineMessageView = PictorialUtils.isWidgetTaglineSupport(pictorialAppDataImpl.mContext, pictorialAppDataImpl.mCurrentPictorialAppInfo.getPkgName());
                break;
            case 1:
                int i2 = PictorialAppDataImpl.$r8$clinit;
                pictorialAppDataImpl.clearPictorialAppForPgName("com.glance.internet");
                break;
            case 2:
                int i3 = PictorialAppDataImpl.$r8$clinit;
                pictorialAppDataImpl.clearPictorialAppForPgName("com.heytap.pictorial");
                pictorialAppDataImpl.clearPictorialAppForPgName("com.glance.internet");
                break;
            default:
                pictorialAppDataImpl.clearPictorialApp(pictorialAppDataImpl.mNextPictorialAppInfo);
                break;
        }
    }
}
