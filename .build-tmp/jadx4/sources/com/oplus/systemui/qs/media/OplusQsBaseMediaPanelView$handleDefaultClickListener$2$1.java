package com.oplus.systemui.qs.media;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.view.ViewGroup;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.plugins.qs.IEditableView;
import com.oplus.systemui.media.seedling.utils.BitmapUtils;
import com.oplus.systemui.qs.utils.OplusQsMediaCacheUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MediaData $selectMediaData;
    public /* synthetic */ OplusQsBaseMediaPanelView this$0;

    public /* synthetic */ OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Drawable drawableLoadDrawableSafely;
        switch (this.$r8$classId) {
            case 0:
                IEditableView iEditableView = this.this$0;
                MediaData mediaData = this.$selectMediaData;
                OplusQsBaseMediaPanelView.Companion companion = OplusQsBaseMediaPanelView.Companion;
                OplusQsMediaUtil.handleResumeApp(mediaData, iEditableView.getContext());
                break;
            default:
                Icon artwork = this.$selectMediaData.getArtwork();
                Bitmap bitmapSafely = (artwork == null || (drawableLoadDrawableSafely = BitmapUtils.loadDrawableSafely(artwork, ((ViewGroup) this.this$0).mContext)) == null) ? null : BitmapUtils.toBitmapSafely(drawableLoadDrawableSafely);
                OplusQsMediaCacheUtil oplusQsMediaCacheUtil = OplusQsMediaCacheUtil.INSTANCE;
                boolean zIsSameArtWork = oplusQsMediaCacheUtil.isSameArtWork(bitmapSafely);
                OplusNotificationLogger.logMediaPanel("OplusQsBaseMediaPanelView", "updateMultiLightForCurrentTrack, isSameArtWork=" + zIsSameArtWork);
                if (!zIsSameArtWork || this.this$0.primaryColor80 == 0) {
                    MediaData mediaData2 = this.$selectMediaData;
                    OplusQsBaseMediaPanelView.Companion companion2 = OplusQsBaseMediaPanelView.Companion;
                    String str = ((Object) mediaData2.getSong()) + "|" + ((Object) mediaData2.getArtist());
                    boolean zEquals = str.equals(this.this$0.lastMultiLightTrackKey);
                    boolean z = this.this$0.lastMultiLightTrackKey == null;
                    this.this$0.lastMultiLightTrackKey = str;
                    oplusQsMediaCacheUtil.updateCache(bitmapSafely);
                    Bitmap bitmapOverlayColorAtBottom = BitmapUtils.overlayColorAtBottom(bitmapSafely, ((ViewGroup) this.this$0).mContext.getColor(2131103095));
                    List<Integer> primaryColor = QsUtil.getPrimaryColor(((ViewGroup) this.this$0).mContext, bitmapOverlayColorAtBottom, OplusQsBaseMediaPanelView.TINE_LIST);
                    if (primaryColor.size() == OplusQsBaseMediaPanelView.TINE_LIST.size()) {
                        this.this$0.primaryColor80 = primaryColor.get(0).intValue();
                    }
                    if (bitmapOverlayColorAtBottom != null) {
                        bitmapOverlayColorAtBottom.recycle();
                    }
                    Handler handler = this.this$0.handler;
                    OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
                    OplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1 oplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1 = new OplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1();
                    oplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1.this$0 = oplusQsBaseMediaPanelView;
                    oplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1.$isSameSong = zEquals;
                    oplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1.$isFirstSong = z;
                    VarHandle.storeStoreFence();
                    handler.post(oplusQsBaseMediaPanelView$updateMultiLightForCurrentTrack$1$1);
                }
                break;
        }
    }
}
