package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.oplus.systemui.keyguard.wallpaper.BaseWallpaperHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialWallpaperHelper$7$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ PictorialWallpaperHelper.AnonymousClass7 f$0;
    public /* synthetic */ String f$1;

    @Override // java.lang.Runnable
    public final void run() {
        PictorialWallpaperHelper.AnonymousClass7 anonymousClass7 = this.f$0;
        String str = this.f$1;
        PictorialWallpaperHelper pictorialWallpaperHelper = anonymousClass7.this$0;
        PictorialMediator pictorialMediator = pictorialWallpaperHelper.mPictorialMediator;
        Context context = ((BaseWallpaperHelper) pictorialWallpaperHelper).mContext;
        if (pictorialMediator.mPictorialCursorHelper == null || context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Settings.System.putStringForUser(context.getContentResolver(), "pictorial_file_uri", str, -2);
        KgdLogUtil.i("PictorialCursorHelper", "updatePictorialFileUri fileUri = " + str);
    }
}
