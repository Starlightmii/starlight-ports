package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.ui.spatial.RectList$;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ PictorialMediator$2$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                PictorialMediator.AnonymousClass1 anonymousClass1 = (PictorialMediator.AnonymousClass1) obj;
                PictorialMediator pictorialMediator = anonymousClass1.this$0;
                pictorialMediator.mHandler.removeCallbacks(pictorialMediator.mReloadPictorialRunnable);
                anonymousClass1.this$0.mReloadPictorialRunnable.run();
                break;
            case 1:
                PictorialMediator pictorialMediator2 = ((PictorialMediator.AnonymousClass22) obj).this$0;
                pictorialMediator2.getPictorialFactory().getPictorialBottomViewHelper().updatePictorialGuideView(pictorialMediator2.mContext);
                break;
            case 2:
                PictorialMediator.AnonymousClass27 anonymousClass27 = (PictorialMediator.AnonymousClass27) obj;
                int wallpaperIdForUser = anonymousClass27.this$0.mWallpaperManager.getWallpaperIdForUser(2, 0);
                StringBuilder sb = new StringBuilder("onColorsChanged mLastWallpaperID=");
                RectList$.ExternalSyntheticOutline0.m(anonymousClass27.this$0.mLastWallpaperID, wallpaperIdForUser, ", ID=", ", skipFlag=", sb);
                sb.append(anonymousClass27.this$0.mSkipClosePictorialFestivalWallpaper.get());
                KgdLogUtil.d("PictorialMediator", sb.toString());
                PictorialMediator pictorialMediator3 = anonymousClass27.this$0;
                if (pictorialMediator3.mLastWallpaperID != wallpaperIdForUser) {
                    pictorialMediator3.mLastWallpaperID = wallpaperIdForUser;
                    boolean z = pictorialMediator3.mSkipClosePictorialFestivalWallpaper.get();
                    PictorialMediator pictorialMediator4 = anonymousClass27.this$0;
                    if (!z) {
                        KgdLogUtil.d("PictorialMediator", "closePictorialFestivalWallpaper isFestivalWallpaperUsed=" + PictorialMediator.sPictorialFestivalWallpaperUsed.get());
                        if (PictorialMediator.sPictorialFestivalWallpaperUsed.get()) {
                            Context context = pictorialMediator4.mContext;
                            StringBuilder sb2 = PictorialUtils.sStringBuilder;
                            Boolean boolValueOf = Boolean.FALSE;
                            if (context != null && context.getContentResolver() != null) {
                                try {
                                    Bundle bundleCall = context.getContentResolver().call(PictorialAppData.URI_WALLPAPER_ENTRANCE_SHOW, "method_disable_festival_wallpaper", (String) null, (Bundle) null);
                                    if (bundleCall != null) {
                                        boolValueOf = Boolean.valueOf(bundleCall.getBoolean("is_disable_success"));
                                    }
                                } catch (Exception unused) {
                                    KgdLogUtil.e("PictorialUtils", "disableFestivalWallpaper error");
                                }
                                KgdLogUtil.i("PictorialUtils", "[disableFestivalWallpaper] isSuccess=" + boolValueOf);
                            }
                            PictorialMediator.sPictorialFestivalWallpaperUsed.set(false);
                        }
                    } else {
                        pictorialMediator4.mSkipClosePictorialFestivalWallpaper.set(false);
                    }
                }
                break;
            default:
                PictorialMediator.m8$$Nest$monChangeTaglineSwitchState(((PictorialMediator.30) obj).this$0);
                break;
        }
    }
}
