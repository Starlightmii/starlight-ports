package com.oplus.systemui.media.seedling.client;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AbsMediaClient$loadArtworkAndUpdate$1$1 implements Runnable {
    public /* synthetic */ Object $albumBgColor;
    public /* synthetic */ Object $artworkUri;
    public /* synthetic */ boolean $it;
    public /* synthetic */ AbsMediaClient this$0;

    @Override // java.lang.Runnable
    public final void run() {
        AbsMediaClient absMediaClient = this.this$0;
        if (absMediaClient.isDestroyed) {
            return;
        }
        absMediaClient.getAlbumLoader().update(this.$artworkUri, this.$albumBgColor, this.$it, this.this$0.getMediaData());
        AbsMediaClient absMediaClient2 = this.this$0;
        absMediaClient2.oldArtwork = absMediaClient2.getMediaData().getArtworkIcon();
        this.this$0.dispatchMediaDataLoaded(0, "loadArtworkAndUpdate");
    }
}
