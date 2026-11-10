package com.oplus.systemui.qs.media;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.UserHandle;
import android.view.CrossWindowBlurListeners;
import android.view.View;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.content.OplusIntent;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 implements View.OnClickListener {
    public /* synthetic */ Object $data;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusMediaViewPagerAdapter this$0;
    public /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$1;

    public /* synthetic */ OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0122  */
    /* JADX WARN: Code duplicated, block: B:45:0x012a  */
    /* JADX WARN: Code duplicated, block: B:50:0x0147  */
    /* JADX WARN: Code duplicated, block: B:58:0x0165  */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        MediaOutputDialogManager mediaOutputDialogManager;
        OplusMediaViewPagerAdapter.IClickListener iClickListener;
        OplusMediaViewPagerAdapter.IClickListener iClickListener2;
        OplusMediaViewPagerAdapter.IClickListener iClickListener3;
        OplusMediaViewPagerAdapter.IClickListener iClickListener4;
        switch (this.$r8$classId) {
            case 0:
                if (ScrimUtil.isLowGaussianLevel(this.this$0.mContext)) {
                    if (this.this$1.mMediaOutputDialogFactory == null) {
                        this.this$1.mMediaOutputDialogFactory = (MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class);
                    }
                    if (QsUtil.isPowerSavingMode()) {
                    }
                    if (this.this$0.mClickListener != null) {
                        ((OplusQsMediaDetailPanelDialog) iClickListener).onCancelClick(z);
                    }
                    mediaOutputDialogManager = this.this$1.mMediaOutputDialogFactory;
                    if (mediaOutputDialogManager != null) {
                        MediaOutputDialogManager.create$default(mediaOutputDialogManager, ((MediaData) this.$data).getPackageName(), true, (View) null, false, false, ((MediaData) this.$data).getToken(), false, 160);
                    }
                } else {
                    QsColorUtil.INSTANCE.getClass();
                    if (QsColorUtil.isSuperPowerSaveState) {
                        if (this.this$1.mMediaOutputDialogFactory == null) {
                            this.this$1.mMediaOutputDialogFactory = (MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class);
                        }
                        z = (QsUtil.isPowerSavingMode() && CrossWindowBlurListeners.getInstance().isCrossWindowBlurEnabled()) ? false : true;
                        if (this.this$0.mClickListener != null && (iClickListener = this.this$0.mClickListener) != null) {
                            ((OplusQsMediaDetailPanelDialog) iClickListener).onCancelClick(z);
                        }
                        mediaOutputDialogManager = this.this$1.mMediaOutputDialogFactory;
                        if (mediaOutputDialogManager != null) {
                            MediaOutputDialogManager.create$default(mediaOutputDialogManager, ((MediaData) this.$data).getPackageName(), true, (View) null, false, false, ((MediaData) this.$data).getToken(), false, 160);
                        }
                    } else {
                        if (this.this$1.mMediaOutputDialogFactory == null) {
                            this.this$1.mMediaOutputDialogFactory = (MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class);
                        }
                        MediaOutputDialogManager mediaOutputDialogManager2 = this.this$1.mMediaOutputDialogFactory;
                        View viewCreate$default = mediaOutputDialogManager2 != null ? MediaOutputDialogManager.create$default(mediaOutputDialogManager2, ((MediaData) this.$data).getPackageName(), true, (View) null, false, false, ((MediaData) this.$data).getToken(), false, 128) : null;
                        OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "onMediaOutPutClick: packageName=" + ((MediaData) this.$data).getPackageName());
                        if (viewCreate$default != null && (iClickListener2 = this.this$0.mClickListener) != null) {
                            iClickListener2.onMediaOutputClick(viewCreate$default);
                        }
                    }
                }
                QsStatisticUtil.collectMediaPanelActionClick(this.this$0.mContext, "7");
                break;
            case 1:
                Intent launchIntentForPackage = this.this$0.mContext.createContextAsUser(UserHandle.of(((MediaData) this.$data).getUserId()), 0).getPackageManager().getLaunchIntentForPackage(((MediaData) this.$data).getPackageName());
                OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "launchMediaAppByIntent");
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(131072);
                    OplusIntent.setOplusFlags(launchIntentForPackage, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT);
                    int i = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
                    ActivityStarter activityStarter = (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class);
                    if (activityStarter != null) {
                        activityStarter.startActivity(launchIntentForPackage, true, true);
                    }
                }
                if (this.this$0.mClickListener != null && (iClickListener3 = this.this$0.mClickListener) != null) {
                    ((OplusQsMediaDetailPanelDialog) iClickListener3).onCancelClick(true);
                }
                QsStatisticUtil.collectMediaPanelActionClick(this.this$0.mContext, "13");
                break;
            default:
                OplusNotificationLogger.logMediaPanel("OplusMediaViewPagerAdapter", "launchMediaAppByPendingIntent");
                PendingIntent pendingIntent = (PendingIntent) this.$data;
                int i2 = OplusMediaViewPagerAdapter.OplusMediaViewHolder.$r8$clinit;
                ActivityStarter activityStarter2 = (ActivityStarter) Dependency.sDependency.getDependencyInner(ActivityStarter.class);
                if (activityStarter2 != null) {
                    activityStarter2.startPendingIntentDismissingKeyguard(pendingIntent);
                }
                if (this.this$0.mClickListener != null && (iClickListener4 = this.this$0.mClickListener) != null) {
                    ((OplusQsMediaDetailPanelDialog) iClickListener4).onCancelClick(true);
                }
                QsStatisticUtil.collectMediaPanelActionClick(this.this$0.mContext, "13");
                break;
        }
    }
}
