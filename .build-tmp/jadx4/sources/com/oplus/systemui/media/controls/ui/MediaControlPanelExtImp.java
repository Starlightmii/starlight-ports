package com.oplus.systemui.media.controls.ui;

import android.animation.AnimatorSet;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.systemui.media.MediaCarouselControllerEx;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.ui.MediaControlPanelExt;
import com.android.systemui.media.controls.ui.animation.MetadataAnimationHandler;
import com.android.systemui.media.controls.ui.controller.MediaControlPanel;
import com.android.systemui.media.controls.ui.controller.MediaViewController;
import com.android.systemui.media.controls.ui.drawable.IlluminationDrawable;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.surfaceeffects.view.ripple.MultiRippleController;
import com.android.systemui.surfaceeffects.view.turbulencenoise.TurbulenceNoiseController;
import com.android.systemui.util.animation.TransitionLayout;
import com.coui.appcompat.seekbar.COUISeekBar;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaControlPanelExtImp extends MediaControlPanelExt implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaControlPanelExtImp";
    private final ActivityStarter activityStarter;
    private final Context context;
    private Job mIconJob;
    private final MediaCarouselControllerEx mediaCarouselControllerEx;
    private final Lazy mediaOutputDialogManager;
    private final CoroutineScope scope;

    public MediaControlPanelExtImp(ActivityStarter activityStarter, Lazy lazy, CoroutineScope coroutineScope, Context context, MediaCarouselControllerEx mediaCarouselControllerEx) {
        this.activityStarter = activityStarter;
        this.mediaOutputDialogManager = lazy;
        this.scope = coroutineScope;
        this.context = context;
        this.mediaCarouselControllerEx = mediaCarouselControllerEx;
    }

    public static final String access$getContentDescriptionByMediaData(MediaControlPanelExtImp mediaControlPanelExtImp, Context context, MediaData mediaData) {
        mediaControlPanelExtImp.getClass();
        if (context == null || mediaData == null) {
            return "";
        }
        CharSequence song = mediaData.getSong();
        if (song == null) {
            song = "";
        }
        CharSequence artist = mediaData.getArtist();
        if (artist == null) {
            artist = "";
        }
        String app = mediaData.getApp();
        return context.getString(2131952792, song, artist, app != null ? app : "");
    }

    public boolean bindActionButtons(MediaViewHolder mediaViewHolder, MediaData mediaData, ConstraintSet constraintSet, Function2 function2) {
        if (mediaViewHolder == null || mediaData == null || constraintSet == null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "bindActionButtons, mediaViewHolder=" + mediaViewHolder + ", data=" + mediaData + ", expandedSet=" + constraintSet);
            return false;
        }
        OplusMediaActions oplusMediaActionsCreateOplusMediaActions = MediaActionBinderKt.createOplusMediaActions(this.context, mediaData, OsBinderCacheUtils.getCurrentUserId() == 0, this.activityStarter, (MediaOutputDialogManager) this.mediaOutputDialogManager.get());
        MediaControlPanelExtImp$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new MediaControlPanelExtImp$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = constraintSet;
        externalSyntheticLambda0.f$1 = mediaViewHolder;
        externalSyntheticLambda0.f$2 = function2;
        VarHandle.storeStoreFence();
        if (oplusMediaActionsCreateOplusMediaActions.getNotNullActions().size() > 2) {
            constraintSet.setVisibility(2131361897, 8);
            constraintSet.setVisibility(2131361900, 8);
            if (ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1) {
                externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getAction1(), 2131361891, 4);
                externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getAction0(), 2131361894, 4);
            } else {
                externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getAction0(), 2131361891, 4);
                externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getAction1(), 2131361894, 4);
            }
            externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getPreOrCustom(), 2131361909, 4);
            externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getPlayPauseOrCustom(), 2131361908, 4);
            externalSyntheticLambda0.invoke(oplusMediaActionsCreateOplusMediaActions.getNextOrCustom(), 2131361907, 4);
        } else {
            constraintSet.setVisibility(2131361891, 8);
            constraintSet.setVisibility(2131361909, 8);
            constraintSet.setVisibility(2131361908, 8);
            constraintSet.setVisibility(2131361907, 8);
            constraintSet.setVisibility(2131361894, 8);
            externalSyntheticLambda0.invoke(CollectionsKt___CollectionsKt.firstOrNull(oplusMediaActionsCreateOplusMediaActions.getNotNullActions()), 2131361897, 8);
            externalSyntheticLambda0.invoke(CollectionsKt___CollectionsKt.getOrNull(oplusMediaActionsCreateOplusMediaActions.getNotNullActions(), 1), 2131361900, 8);
        }
        constraintSet.setVisibility(2131363914, 0);
        constraintSet.setAlpha(2131363914, 1.0f);
        return true;
    }

    public void bindNoIntentClickListener(Context context, MediaViewHolder mediaViewHolder, String str) {
        TransitionLayout player = mediaViewHolder.getPlayer();
        bindNoIntentClickListener.1 r0 = new bindNoIntentClickListener.1();
        r0.$context = context;
        r0.$packageName = str;
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        player.setOnClickListener(r0);
    }

    public void initButtonColor(ImageButton imageButton, int i) {
        imageButton.setColorFilter(i);
    }

    public boolean isBuffering(MediaController mediaController) {
        if (mediaController == null) {
            return true;
        }
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MediaControlPanelExtImp-->PlaybackState is null");
            }
            return false;
        }
        boolean z = playbackState.getState() == 6;
        if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "MediaControlPanelExtImp-->Currently buffering media data, please wait for buffering to complete");
        }
        return z;
    }

    public boolean isValidTap(FalsingManager falsingManager, MediaController mediaController) {
        return true;
    }

    public boolean proxyAttachPlayer(MediaViewHolder mediaViewHolder, SeekBarViewModel seekBarViewModel, MediaViewController mediaViewController) {
        MediaControlPanel mediaControlPanel = getMediaControlPanel();
        mediaControlPanel.mMediaViewHolder = mediaViewHolder;
        TransitionLayout player = mediaViewHolder.getPlayer();
        mediaControlPanel.mSeekBarObserver = new OplusMediaNotificationSeekBarObserver(mediaViewHolder);
        seekBarViewModel.getProgress().observeForever(mediaControlPanel.mSeekBarObserver);
        COUISeekBar seekBar = mediaViewHolder.getSeekBar();
        COUISeekBar cOUISeekBar = seekBar instanceof COUISeekBar ? seekBar : null;
        if (cOUISeekBar != null) {
            seekBarViewModel.attachTouchSeekBarHandlers(cOUISeekBar);
        }
        seekBarViewModel.setScrubbingChangeListener(mediaControlPanel.mScrubbingChangeListener);
        seekBarViewModel.setEnabledChangeListener(mediaControlPanel.mEnabledChangeListener);
        mediaViewController.attach(player);
        mediaControlPanel.mMediaViewHolder.getAlbumView().setLayerType(2, null);
        TextView titleText = mediaControlPanel.mMediaViewHolder.getTitleText();
        TextView artistText = mediaControlPanel.mMediaViewHolder.getArtistText();
        View explicitIndicator = mediaControlPanel.mMediaViewHolder.getExplicitIndicator();
        AnimatorSet animatorSetLoadAnimator = mediaControlPanel.loadAnimator(2130772523, Interpolators.EMPHASIZED_DECELERATE, new View[]{titleText, artistText, explicitIndicator});
        AnimatorSet animatorSetLoadAnimator2 = mediaControlPanel.loadAnimator(2130772524, Interpolators.EMPHASIZED_ACCELERATE, new View[]{titleText, artistText, explicitIndicator});
        mediaControlPanel.mMultiRippleController = new MultiRippleController(mediaViewHolder.getMultiRippleView());
        mediaControlPanel.mColorSchemeTransition = new OplusColorSchemeTransition(mediaControlPanel.mContext, mediaControlPanel.mMediaViewHolder, mediaControlPanel.mMultiRippleController, new TurbulenceNoiseController(mediaViewHolder.getTurbulenceNoiseView()));
        animatorSetLoadAnimator2.getClass();
        animatorSetLoadAnimator.getClass();
        mediaControlPanel.mMetadataAnimationHandler = new MetadataAnimationHandler(animatorSetLoadAnimator2, animatorSetLoadAnimator);
        if (player.getBackground() instanceof IlluminationDrawable) {
            player.getBackground();
        }
        return true;
    }

    public boolean proxyBindArtworkAndColors(MediaData mediaData, String str, boolean z) {
        MediaControlPanel mediaControlPanel = getMediaControlPanel();
        int iHashCode = mediaData.hashCode();
        String str2 = "MediaControlPanel#bindArtworkAndColors<" + str + ">";
        Trace.beginAsyncSection(str2, iHashCode);
        int i = mediaControlPanel.mArtworkNextBindRequestId;
        mediaControlPanel.mArtworkNextBindRequestId = i + 1;
        int measuredWidth = mediaControlPanel.mMediaViewHolder.getAlbumView().getMeasuredWidth();
        int measuredHeight = mediaControlPanel.mMediaViewHolder.getAlbumView().getMeasuredHeight();
        Job job = this.mIconJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.mIconJob = BuildersKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new proxyBindArtworkAndColors.1.1(mediaData, mediaControlPanel, measuredWidth, measuredHeight, i, str2, iHashCode, this, (Continuation) null), 2);
        return true;
    }

    public void recordClickPanelButton(String str, CharSequence charSequence) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "MediaControlPanelExtImp-->".concat("Clicked on the media panel, name is " + str + " contentDescription is " + ((Object) charSequence)));
        }
    }

    public boolean updateScrubbingTimeVisibility(MediaViewHolder mediaViewHolder) {
        TextView scrubbingElapsedTimeView;
        TextView scrubbingTotalTimeView;
        if (mediaViewHolder != null && (scrubbingTotalTimeView = mediaViewHolder.getScrubbingTotalTimeView()) != null) {
            scrubbingTotalTimeView.setVisibility(0);
        }
        if (mediaViewHolder == null || (scrubbingElapsedTimeView = mediaViewHolder.getScrubbingElapsedTimeView()) == null) {
            return true;
        }
        scrubbingElapsedTimeView.setVisibility(0);
        return true;
    }

    public boolean updateTextWithoutAnim(TextView textView, TextView textView2, MediaData mediaData, long j) {
        if (textView == null || textView2 == null || mediaData == null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "updateArtistTextOnly, titleText=" + textView + ", artistText=" + textView2 + ", data=" + mediaData);
            return false;
        }
        if (mediaData.getMediaDataEx().getLyricData().isEnabled(this.mediaCarouselControllerEx.isPanoramicAod() ? 4 : 16)) {
            textView.setText(mediaData.getMediaDataEx().getLyricData().getCurrentLyric(j));
            textView2.setText(mediaData.getMediaDataEx().getLyricData().getSongInfo());
            return true;
        }
        if (Intrinsics.areEqual(textView2.getText(), mediaData.getArtist())) {
            textView.setText(mediaData.getSong());
            return true;
        }
        if (this.mediaCarouselControllerEx.isScreenONOrPanoramicAod()) {
            return false;
        }
        textView.setText(mediaData.getSong());
        textView2.setText(mediaData.getArtist());
        return true;
    }
}
