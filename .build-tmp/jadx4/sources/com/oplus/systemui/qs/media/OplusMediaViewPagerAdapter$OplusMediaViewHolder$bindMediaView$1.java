package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.models.OplusMediaDataModel;
import com.android.systemui.media.controls.shared.MediaProcessingHelperKt;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.oplus.media.OplusMediaEvent;
import com.oplus.media.OplusPlaybackCommandData;
import com.oplus.systemui.media.seedling.utils.BitmapUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1 implements View.OnClickListener {
    public /* synthetic */ MediaData $mQsMediaData;
    public /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $mQsMediaData;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppCompatImageView mAppIcon;
            Drawable drawableLoadDrawableSafely;
            switch (this.$r8$classId) {
                case 0:
                    OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.this$0;
                    MediaData mediaData = (MediaData) this.$mQsMediaData;
                    int i = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
                    OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "handleResumeAppTask----> playId is " + mediaData.getNotificationKey());
                    try {
                        OplusMediaEvent.Builder data = new OplusMediaEvent.Builder(1).setData(new OplusPlaybackCommandData(1));
                        if (!TextUtils.isEmpty(mediaData.getNotificationKey())) {
                            data.setPlayerId(mediaData.getNotificationKey());
                        }
                        QsUtil.getAndUnFreezePackage(mediaData.getPackageName(), mediaData.getUserId(), OplusMediaViewPagerAdapter.this.mContext);
                        ((OplusMediaDataModel) DependencyEx.sDependency.getDependency(OplusMediaDataModel.class)).sendEvent(data.build());
                    } catch (RemoteException unused) {
                        OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "mediaControlCenter sendEvent exception");
                        return;
                    }
                    break;
                case 1:
                    if (!((OplusMediaViewPagerAdapter) this.$mQsMediaData).mIsDestroyed && (mAppIcon = this.this$0.getMAppIcon()) != null) {
                        mAppIcon.setVisibility(8);
                        break;
                    }
                    break;
                case 2:
                    Context context = ((OplusMediaViewPagerAdapter) this.$mQsMediaData).mContext;
                    Icon icon = this.this$0.lastIcon;
                    MediaData mediaData2 = this.this$0.getMediaData();
                    boolean zAreIconsEqual = MediaProcessingHelperKt.areIconsEqual(context, icon, mediaData2 != null ? mediaData2.getArtwork() : null);
                    OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = (OplusMediaViewPagerAdapter) this.$mQsMediaData;
                    boolean z = oplusMediaViewPagerAdapter.enableStrokeLight;
                    boolean z2 = oplusMediaViewPagerAdapter.isSupportLightEffect;
                    boolean z3 = oplusMediaViewPagerAdapter.isMiddleOrHighAnimLevel;
                    StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("isSameArtWork=", ", enableStrokeLight=", ", isSupportLightEffect=", zAreIconsEqual, z);
                    sbM.append(z2);
                    sbM.append(", isMiddleOrHighAnimLevel=");
                    sbM.append(z3);
                    OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", sbM.toString());
                    if (!zAreIconsEqual) {
                        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder2 = this.this$0;
                        MediaData mediaData3 = oplusMediaViewHolder2.getMediaData();
                        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = OplusMediaViewPagerAdapter.this;
                        if (mediaData3 == null || mediaData3.getResumption() || mediaData3.getArtwork() == null) {
                            oplusMediaViewPagerAdapter2.primaryColor80 = COUIContextUtil.getAttrColor(QsColorUtil.isNeedDarkThemeColor(oplusMediaViewPagerAdapter2.mContext, false) ? QsColorUtil.createDarkColorContext(oplusMediaViewPagerAdapter2.mContext, true) : oplusMediaViewPagerAdapter2.mContext, 2130969314);
                        } else {
                            Icon artwork = mediaData3.getArtwork();
                            Bitmap bitmapOverlayColorAtBottom = BitmapUtils.overlayColorAtBottom((artwork == null || (drawableLoadDrawableSafely = BitmapUtils.loadDrawableSafely(artwork, oplusMediaViewPagerAdapter2.mContext)) == null) ? null : BitmapUtils.toBitmapSafely(drawableLoadDrawableSafely), oplusMediaViewPagerAdapter2.mContext.getColor(2131103095));
                            List<Integer> primaryColor = QsUtil.getPrimaryColor(oplusMediaViewPagerAdapter2.mContext, bitmapOverlayColorAtBottom, OplusMediaViewPagerAdapter.TINE_LIST);
                            if (primaryColor.size() == OplusMediaViewPagerAdapter.TINE_LIST.size()) {
                                oplusMediaViewPagerAdapter2.primaryColor80 = primaryColor.get(0).intValue();
                            }
                            if (bitmapOverlayColorAtBottom != null) {
                                bitmapOverlayColorAtBottom.recycle();
                            }
                        }
                        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder3 = this.this$0;
                        MediaData mediaData4 = oplusMediaViewHolder3.getMediaData();
                        oplusMediaViewHolder3.lastIcon = mediaData4 != null ? mediaData4.getArtwork() : null;
                        Handler mHandler = this.this$0.getMHandler();
                        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder4 = this.this$0;
                        OplusMediaViewPagerAdapter$lightAvailabilityListener$1 oplusMediaViewPagerAdapter$lightAvailabilityListener$1 = new OplusMediaViewPagerAdapter$lightAvailabilityListener$1(4);
                        oplusMediaViewPagerAdapter$lightAvailabilityListener$1.this$0 = oplusMediaViewHolder4;
                        VarHandle.storeStoreFence();
                        mHandler.post(oplusMediaViewPagerAdapter$lightAvailabilityListener$1);
                    }
                    break;
                default:
                    ImageView mPhotoCover = this.this$0.getMPhotoCover();
                    if (mPhotoCover != null) {
                        mPhotoCover.setImageResource(((OplusMediaViewPagerAdapter) this.$mQsMediaData).getDefaultPhotoDrawable());
                    }
                    break;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "mPlayOrPauseBtn clicked and handle reResume app task; playID is " + this.$mQsMediaData.getNotificationKey() + " ");
        this.this$0.getMHandler().post(this.this$0.getMLoadingRunnable());
        this.this$0.getMHandler().postDelayed(this.this$0.getMStopLoadingRunnable(), 20000L);
        Handler mBgHandler = this.this$0.getMBgHandler();
        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.this$0;
        MediaData mediaData = this.$mQsMediaData;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = oplusMediaViewHolder;
        anonymousClass1.$mQsMediaData = mediaData;
        VarHandle.storeStoreFence();
        mBgHandler.post(anonymousClass1);
    }
}
