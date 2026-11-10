package com.oplus.systemui.keyguard.wallpaper.pictorial;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialWallpaperHelper$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ PictorialWallpaperHelper$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                PictorialWallpaperHelper.$r8$lambda$NCOVHCUz7YGrCZyj0qjZDwGUssI((PictorialWallpaperHelper) obj);
                break;
            default:
                PictorialWallpaperHelper pictorialWallpaperHelper = ((PictorialWallpaperHelper.AnonymousClass2) obj).this$0;
                if (!pictorialWallpaperHelper.mShouldUpdateUiWhenPanoramicAod) {
                    pictorialWallpaperHelper.updateAndFetchWallpaper("screen_off_p_aod", false);
                }
                break;
        }
    }
}
