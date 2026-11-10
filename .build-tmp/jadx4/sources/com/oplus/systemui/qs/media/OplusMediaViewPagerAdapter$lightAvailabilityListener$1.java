package com.oplus.systemui.qs.media;

import android.widget.ImageButton;
import com.android.systemui.media.controls.models.player.OplusMediaLyricData;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.coui.appcompat.COUIMaterialStateManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewPagerAdapter$lightAvailabilityListener$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object this$0;

    public /* synthetic */ OplusMediaViewPagerAdapter$lightAvailabilityListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = (OplusMediaViewPagerAdapter) this.this$0;
                COUIMaterialStateManager.INSTANCE.getClass();
                oplusMediaViewPagerAdapter.enableStrokeLight = COUIMaterialStateManager.MATERIAL_STROKE_ENABLE;
                OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "lightAvailabilityListener enableStrokeLight=" + ((OplusMediaViewPagerAdapter) this.this$0).enableStrokeLight);
                Iterator it = ((OplusMediaViewPagerAdapter) this.this$0).mViewHolderList.iterator();
                while (it.hasNext()) {
                    ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) it.next()).refreshItemMultiLightBackground();
                }
                break;
            case 1:
                SeekBarViewModel mSeekBarViewModel = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMSeekBarViewModel();
                if (mSeekBarViewModel != null) {
                    mSeekBarViewModel.updateController(((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMController(), (OplusMediaLyricData) null);
                }
                break;
            case 2:
                OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "loading animation start");
                OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = (OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0;
                if (!oplusMediaViewHolder.isNewLayout) {
                    ImageButton mPlayOrPauseBtn = oplusMediaViewHolder.getMPlayOrPauseBtn();
                    if (mPlayOrPauseBtn != null) {
                        mPlayOrPauseBtn.setVisibility(4);
                    }
                    OplusDotProgressBar mDotLoading = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMDotLoading();
                    if (mDotLoading != null) {
                        mDotLoading.setVisibility(0);
                    }
                    OplusDotProgressBar mDotLoading2 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMDotLoading();
                    if (mDotLoading2 != null) {
                        mDotLoading2.startAnimation();
                    }
                } else {
                    ImageButton mBtnAction1 = oplusMediaViewHolder.getMBtnAction1();
                    if (mBtnAction1 != null) {
                        mBtnAction1.setVisibility(4);
                    }
                    OplusDotProgressBar mOplusDotActionAnimation = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMOplusDotActionAnimation();
                    if (mOplusDotActionAnimation != null) {
                        mOplusDotActionAnimation.setVisibility(0);
                    }
                    OplusDotProgressBar mOplusDotActionAnimation2 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMOplusDotActionAnimation();
                    if (mOplusDotActionAnimation2 != null) {
                        mOplusDotActionAnimation2.startAnimation();
                    }
                }
                break;
            case 3:
                OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "loading animation stop");
                OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder2 = (OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0;
                if (!oplusMediaViewHolder2.isNewLayout) {
                    ImageButton mPlayOrPauseBtn2 = oplusMediaViewHolder2.getMPlayOrPauseBtn();
                    if (mPlayOrPauseBtn2 != null) {
                        mPlayOrPauseBtn2.setVisibility(0);
                    }
                    OplusDotProgressBar mDotLoading3 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMDotLoading();
                    if (mDotLoading3 != null) {
                        mDotLoading3.stopAnimation();
                    }
                    OplusDotProgressBar mDotLoading4 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMDotLoading();
                    if (mDotLoading4 != null) {
                        mDotLoading4.setVisibility(4);
                    }
                } else {
                    ImageButton mBtnAction2 = oplusMediaViewHolder2.getMBtnAction1();
                    if (mBtnAction2 != null) {
                        mBtnAction2.setVisibility(0);
                    }
                    OplusDotProgressBar mOplusDotActionAnimation3 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMOplusDotActionAnimation();
                    if (mOplusDotActionAnimation3 != null) {
                        mOplusDotActionAnimation3.stopAnimation();
                    }
                    OplusDotProgressBar mOplusDotActionAnimation4 = ((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0).getMOplusDotActionAnimation();
                    if (mOplusDotActionAnimation4 != null) {
                        mOplusDotActionAnimation4.setVisibility(4);
                    }
                }
                break;
            default:
                OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder3 = (OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0;
                int i = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
                oplusMediaViewHolder3.updateSeekBarColor();
                OplusMediaViewPagerAdapter.OplusMediaViewHolder.access$applyItemBackgroundLightParams((OplusMediaViewPagerAdapter.OplusMediaViewHolder) this.this$0);
                break;
        }
    }
}
