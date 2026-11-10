package com.oplus.systemui.media.output;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.Icon;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.core.view.ViewKt$;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.models.player.OplusMediaLyricData;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputAdapterBase;
import com.android.systemui.media.dialog.MediaSwitchingController;
import com.android.systemui.media.dialog.MissingPermissionsWarning;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.content.OplusIntent;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.common.dialog.OplusQSDetailDialogProvider;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransitionDelayManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator;
import com.oplus.systemui.qs.media.OplusQsMediaUtil;
import com.oplus.systemui.qs.media.VolumeSeekBarViewModel;
import com.oplus.transition.OplusAnimationController;
import com.oplus.util.OplusContextUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaOutputSwitchingPage extends OplusMediaOutputPage implements MediaSwitchingController.Callback, ConfigurationController.ConfigurationListener, Observer {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaOutputSwitchingPage";
    private OplusMediaOutputAdapter adapter;
    private OplusMediaOutputBottomLayout bottomButton;
    public final ConfigurationController configurationController;
    private COUIRecyclerView devicesRecyclerView;
    private final OplusQSDetailDialogProvider dialogProvider;
    private ViewGroup dialogView;
    private final Lazy fadeTransition$delegate;
    private final FrameLayout fullScreenView;
    private AppCompatImageView headerIcon;
    public float headerIconCornerRadius;
    public float headerIconCornerWeight;
    private OplusOutlineAdapter headerIconOplusOutlineAdapter;
    private Outline headerIconOutlineLast;
    private TextView headerSubtitle;
    private TextView headerTitle;
    public boolean isVisibleVolumeSeekBar;
    public boolean lastShowVolumeSeekBar;
    private final Lazy manager$delegate;
    private MediaController mediaController;
    private MediaData mediaData;
    private final MediaDataManager mediaDataManager;
    private final mediaDataUpdateListener.1 mediaDataUpdateListener;
    private final MediaSwitchingController mediaSwitchingController;
    private final Lazy moveTransition$delegate;
    private TextView permissionsWarning;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;
    private SeekBarViewModel seekbarViewModel;
    private final Spring springConfig;
    private ImageView volumeMax;
    private ImageView volumeMin;
    private FrameLayout volumeSeekBarParent;
    private VolumeSeekBarViewModel volumeSeekBarViewModel;
    private SeekBar volumeSeekbar;

    /* JADX INFO: renamed from: $r8$lambda$ld_uoW2ju5bU47ln_-Vs9Btli6E, reason: not valid java name */
    public static SpringTransitionDelayManager m18$r8$lambda$ld_uoW2ju5bU47ln_Vs9Btli6E(OplusMediaOutputSwitchingPage oplusMediaOutputSwitchingPage) {
        return new SpringTransitionDelayManager(oplusMediaOutputSwitchingPage.fullScreenView);
    }

    public OplusMediaOutputSwitchingPage(Context context, OplusMediaOutputPageController oplusMediaOutputPageController, OutputAudioSharingInteractor outputAudioSharingInteractor, MediaSwitchingController mediaSwitchingController, SeekBarViewModel.Factory factory, MediaDataManager mediaDataManager, OplusQSDetailDialogProvider oplusQSDetailDialogProvider) {
        super(context, oplusMediaOutputPageController, outputAudioSharingInteractor);
        this.mediaSwitchingController = mediaSwitchingController;
        this.seekBarViewModelFactory = factory;
        this.mediaDataManager = mediaDataManager;
        this.dialogProvider = oplusQSDetailDialogProvider;
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.fullScreenView = frameLayout;
        boolean z = false;
        OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.fadeTransition$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
        OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.moveTransition$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda1);
        OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new OplusMediaOutputSwitchingPage$.ExternalSyntheticLambda0(2);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.manager$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
        this.springConfig = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "媒体切换音量条显示动画", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
        this.configurationController = (ConfigurationController) Dependency.sDependency.getDependencyInner(ConfigurationController.class);
        this.headerIconCornerWeight = 3.0f;
        this.isVisibleVolumeSeekBar = true;
        if (outputAudioSharingInteractor.inAudioSharing() && this.isVisibleVolumeSeekBar) {
            z = true;
        }
        this.lastShowVolumeSeekBar = z;
        mediaDataUpdateListener.1 r4 = new mediaDataUpdateListener.1();
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.mediaDataUpdateListener = r4;
        this.adapter = new OplusMediaOutputAdapter(mediaSwitchingController, this, outputAudioSharingInteractor, context);
        this.seekbarViewModel = factory.create();
    }

    public static final void access$bindIconClickEvent(OplusMediaOutputSwitchingPage oplusMediaOutputSwitchingPage, MediaData mediaData) {
        Intent launchIntentForPackage;
        PendingIntent clickIntent = mediaData != null ? mediaData.getClickIntent() : null;
        if (clickIntent != null) {
            ActivityStarter activityStarter = (ActivityStarter) Dependency.sDependency.getDependencyInner(ActivityStarter.class);
            if (activityStarter != null) {
                activityStarter.startPendingIntentDismissingKeyguard(clickIntent);
            }
            OplusQSDetailDialogProvider oplusQSDetailDialogProvider = oplusMediaOutputSwitchingPage.dialogProvider;
            if (oplusQSDetailDialogProvider != null) {
                oplusQSDetailDialogProvider.destroy(false);
                return;
            }
            return;
        }
        if (mediaData == null || (launchIntentForPackage = oplusMediaOutputSwitchingPage.getContext().createContextAsUser(UserHandle.of(mediaData.getUserId()), 0).getPackageManager().getLaunchIntentForPackage(mediaData.getPackageName())) == null) {
            return;
        }
        launchIntentForPackage.addFlags(131072);
        OplusIntent.setOplusFlags(launchIntentForPackage, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT);
        ActivityStarter activityStarter2 = (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class);
        if (activityStarter2 != null) {
            activityStarter2.startActivity(launchIntentForPackage, true, true);
        }
        OplusQSDetailDialogProvider oplusQSDetailDialogProvider2 = oplusMediaOutputSwitchingPage.dialogProvider;
        if (oplusQSDetailDialogProvider2 != null) {
            oplusQSDetailDialogProvider2.destroy(false);
        }
    }

    private final SpringTransition getFadeTransition() {
        return (SpringTransition) this.fadeTransition$delegate.getValue();
    }

    private final SpringTransitionDelayManager getManager() {
        return (SpringTransitionDelayManager) this.manager$delegate.getValue();
    }

    private final SpringTransition getMoveTransition() {
        return (SpringTransition) this.moveTransition$delegate.getValue();
    }

    public final void createDialogView() {
        CharSequence text;
        CharSequence text2 = null;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(2131559536, (ViewGroup) null);
        this.dialogView = viewGroup;
        viewGroup.setLayoutDirection(getContext().getResources().getConfiguration().getLayoutDirection());
        this.volumeSeekBarViewModel = new VolumeSeekBarViewModel(getContext(), new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER)));
        this.volumeSeekBarParent = (FrameLayout) viewGroup.requireViewById(2131364340);
        SeekBar seekBar = (SeekBar) viewGroup.requireViewById(2131364339);
        seekBar.setThumbOffset(0);
        VolumeSeekBarViewModel volumeSeekBarViewModel = this.volumeSeekBarViewModel;
        if (volumeSeekBarViewModel != null) {
            volumeSeekBarViewModel.onViewAttached(seekBar);
        }
        this.volumeSeekbar = seekBar;
        this.headerTitle = (TextView) viewGroup.requireViewById(2131363345);
        this.headerSubtitle = (TextView) viewGroup.requireViewById(2131363343);
        AppCompatImageView appCompatImageViewRequireViewById = viewGroup.requireViewById(2131363340);
        this.headerIcon = appCompatImageViewRequireViewById;
        if (appCompatImageViewRequireViewById != null) {
            float dimension = getContext().getResources().getDimension(2131171048);
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(getContext(), dimension);
            if (roundParams != null) {
                dimension = roundParams.getRadius();
            }
            this.headerIconCornerRadius = dimension;
            this.headerIconCornerWeight = roundParams != null ? roundParams.getWeight() : 3.0f;
            appCompatImageViewRequireViewById.setElevation(0.0f);
            appCompatImageViewRequireViewById.setOutlineProvider(new applyHeaderIconOutlineProvider.1(this));
            appCompatImageViewRequireViewById.setClipToOutline(true);
        }
        View viewRequireViewById = viewGroup.requireViewById(2131363386);
        this.bottomButton = viewGroup.requireViewById(2131364336);
        this.permissionsWarning = (TextView) viewGroup.requireViewById(2131366060);
        COUIRecyclerView cOUIRecyclerViewRequireViewById = viewGroup.requireViewById(2131363746);
        this.devicesRecyclerView = cOUIRecyclerViewRequireViewById;
        if (cOUIRecyclerViewRequireViewById != null) {
            cOUIRecyclerViewRequireViewById.setLayoutManager(new LayoutManagerWrapper(this, getContext()));
            cOUIRecyclerViewRequireViewById.setItemAnimator(new OplusItemSpringAnimator(new OplusMediaOutputItemAnimator(), null));
            cOUIRecyclerViewRequireViewById.setMotionEventSplittingEnabled(false);
            cOUIRecyclerViewRequireViewById.setAdapter(this.adapter);
            cOUIRecyclerViewRequireViewById.setHasFixedSize(false);
        }
        OplusMediaOutputAdapter oplusMediaOutputAdapter = this.adapter;
        if (oplusMediaOutputAdapter != null) {
            oplusMediaOutputAdapter.setRecyclerView(this.devicesRecyclerView);
        }
        updateDevicesListLayout(getAudioSharingInteractor().inAudioSharing());
        updateBottomButton(((Boolean) getAudioSharingInteractor().getAudioSharingAvailable().getValue()).booleanValue(), false);
        this.volumeMin = (ImageView) viewGroup.findViewById(2131363911);
        this.volumeMax = (ImageView) viewGroup.findViewById(2131363910);
        if (BaseQSResPool.globalThemeApplied()) {
            int attrColor = OplusContextUtil.getAttrColor(getContext(), 2130969293);
            ImageView imageView = this.volumeMin;
            if (imageView != null) {
                imageView.setImageTintList(ColorStateList.valueOf(attrColor));
            }
            ImageView imageView2 = this.volumeMax;
            if (imageView2 != null) {
                imageView2.setImageTintList(ColorStateList.valueOf(attrColor));
            }
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
            ImageView imageView3 = this.volumeMin;
            if (imageView3 != null) {
                imageView3.setImageTintList(ColorStateList.valueOf(getContext().getColor(2131100939)));
            }
            ImageView imageView4 = this.volumeMax;
            if (imageView4 != null) {
                imageView4.setImageTintList(ColorStateList.valueOf(getContext().getColor(2131100939)));
            }
        }
        refreshHeaderMedia();
        TextView textView = this.headerSubtitle;
        if (textView == null || (text = textView.getText()) == null) {
            TextView textView2 = this.headerTitle;
            if (textView2 != null) {
                text2 = textView2.getText();
            }
        } else {
            text2 = text;
        }
        if (text2 != null) {
            ViewExtKt.setTalkBackEnabled(viewRequireViewById, true, text2);
        }
        createDialogView.3.2 r2 = new createDialogView.3.2(0);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        viewRequireViewById.setOnClickListener(r2);
        boolean z = getContext().getResources().getConfiguration().orientation == 1;
        FeatureOption.INSTANCE.getClass();
        if ((FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) || FeatureOption.isTablet() || z) {
            return;
        }
        updateHeaderIconLayoutParams(viewGroup);
    }

    public final void dismissDialog() {
    }

    @Override // com.oplus.systemui.media.output.OplusMediaOutputPage, com.oplus.systemui.media.output.page.IOplusPage
    public View getView() {
        if (this.dialogView == null) {
            createDialogView();
            this.fullScreenView.addView(this.dialogView);
        }
        return this.fullScreenView;
    }

    public final boolean isLandscape() {
        boolean z = getContext().getResources().getConfiguration().orientation == 2;
        FeatureOption.INSTANCE.getClass();
        return z && !((FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet());
    }

    public final boolean isRealDialog() {
        return this.dialogProvider != null;
    }

    public void onChanged(SeekBarViewModel.Progress progress) {
        Integer elapsedTime = progress.getElapsedTime();
        if (elapsedTime != null) {
            updateMediaLyric(elapsedTime.intValue());
        }
    }

    public void onConfigChanged(Configuration configuration) {
        OplusMediaOutputAdapter oplusMediaOutputAdapter = this.adapter;
        if (oplusMediaOutputAdapter != null) {
            oplusMediaOutputAdapter.setIsDragging(false);
        }
        this.mediaSwitchingController.setRefreshing(false);
        createDialogView();
        refresh(true);
        this.fullScreenView.removeAllViews();
        this.fullScreenView.addView(this.dialogView);
    }

    public final void onDeviceListChanged() {
        onShow.2.1 r0 = new onShow.2.1(2);
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
        this.mediaSwitchingController.notifyStreamVolumeChanged();
    }

    @Override // com.oplus.systemui.media.output.page.IOplusPage
    public final void onDismiss() {
        this.mediaSwitchingController.stop();
        VolumeSeekBarViewModel volumeSeekBarViewModel = this.volumeSeekBarViewModel;
        if (volumeSeekBarViewModel != null) {
            volumeSeekBarViewModel.onViewDetached();
        }
        COUIRecyclerView cOUIRecyclerView = this.devicesRecyclerView;
        if (cOUIRecyclerView != null) {
            cOUIRecyclerView.setLayoutManager((RecyclerView.LayoutManager) null);
        }
        this.configurationController.removeCallback(this);
        this.mediaDataManager.removeListener(this.mediaDataUpdateListener);
        SeekBarViewModel seekBarViewModel = this.seekbarViewModel;
        if (seekBarViewModel != null) {
            seekBarViewModel.getProgress().removeObserver(this);
            seekBarViewModel.onDestroy();
        }
        this.seekbarViewModel = null;
        getAudioSharingInteractor().removeOnPhoneModeChangedListener();
    }

    public final void onMediaChanged() {
    }

    public final void onMediaDataChanged(MediaData mediaData) {
        PlaybackState playbackState;
        MediaSession.Token token;
        this.mediaData = mediaData;
        MediaController mediaController = (mediaData == null || (token = mediaData.getToken()) == null) ? null : new MediaController(getContext(), token);
        this.mediaController = mediaController;
        boolean z = mediaController != null;
        SeekBarViewModel seekBarViewModel = this.seekbarViewModel;
        if (seekBarViewModel != null) {
            seekBarViewModel.setListening(z);
            Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
            onMediaDataChanged.2.1 r3 = new onMediaDataChanged.2.1(0);
            r3.$it = seekBarViewModel;
            r3.this$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(r3);
        }
        if (z) {
            MediaController mediaController2 = this.mediaController;
            if (mediaController2 != null && (playbackState = mediaController2.getPlaybackState()) != null) {
                updateMediaLyric((int) playbackState.getPosition());
            }
        } else {
            refreshHeaderTitle();
        }
        updateArtwork();
    }

    public final void onMediaStoppedOrPaused() {
    }

    public final void onQuickAccessButtonsChanged() {
    }

    public final void onRouteChanged() {
        onShow.2.1 r0 = new onShow.2.1(3);
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    @Override // com.oplus.systemui.media.output.page.IOplusPage
    public final void onShow() {
        LiveData progress;
        this.mediaSwitchingController.setRefreshing(false);
        this.mediaSwitchingController.start(this);
        this.configurationController.addCallback(this);
        SeekBarViewModel seekBarViewModel = this.seekbarViewModel;
        if (seekBarViewModel != null && (progress = seekBarViewModel.getProgress()) != null) {
            progress.observeForever(this);
        }
        this.mediaDataManager.addListener(this.mediaDataUpdateListener);
        RepeatWhenAttachedKt.repeatWhenAttached$default(this.fullScreenView, Dispatchers.getMain(), new onShow.1(this, (Continuation) null), 2);
        OutputAudioSharingInteractor audioSharingInteractor = getAudioSharingInteractor();
        onShow.2 r1 = new onShow.2();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        audioSharingInteractor.addOnPhoneModeChangedListener(r1);
    }

    public final void refresh(boolean z) {
        MissingPermissionsWarning missingPermissionsWarning = this.mediaSwitchingController.getMissingPermissionsWarning();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
            Log.d("SystemUi--Media", "OplusMediaOutputSwitchingPage-->".concat("refreshWarningSection warningInfo: " + missingPermissionsWarning));
        }
        if (missingPermissionsWarning == null) {
            OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout = this.bottomButton;
            if (oplusMediaOutputBottomLayout != null) {
                Button button = oplusMediaOutputBottomLayout.leftButton;
                if (button == null) {
                    button = null;
                }
                button.setVisibility(8);
                oplusMediaOutputBottomLayout.refreshDividerVisibility();
            }
            TextView textView = this.permissionsWarning;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            boolean zBooleanValue = ((Boolean) getAudioSharingInteractor().getAudioSharingAvailable().getValue()).booleanValue();
            String appName = missingPermissionsWarning.getAppName();
            OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout2 = this.bottomButton;
            if (oplusMediaOutputBottomLayout2 != null) {
                String string = getContext().getString(2131954509, appName);
                createDialogView.3.2 r6 = new createDialogView.3.2(1);
                r6.this$0 = this;
                VarHandle.storeStoreFence();
                oplusMediaOutputBottomLayout2.setLeftButton(string, r6);
            }
            OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout3 = this.bottomButton;
            if (oplusMediaOutputBottomLayout3 != null) {
                oplusMediaOutputBottomLayout3.setVisibility(0);
            }
            TextView textView2 = this.permissionsWarning;
            if (textView2 != null) {
                textView2.setText(getContext().getString(2131954505, appName));
            }
            TextView textView3 = this.permissionsWarning;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            updateBottomButton(zBooleanValue, true);
        }
        if (this.mediaSwitchingController.isRefreshing()) {
            return;
        }
        this.mediaSwitchingController.setRefreshing(true);
        OplusMediaOutputAdapter oplusMediaOutputAdapter = this.adapter;
        if (oplusMediaOutputAdapter != null) {
            if (((MediaOutputAdapterBase) oplusMediaOutputAdapter).mIsDragging) {
                this.mediaSwitchingController.setRefreshing(false);
                this.mediaSwitchingController.refreshDataSetIfNeeded();
                return;
            }
            int currentActivePosition = oplusMediaOutputAdapter.getCurrentActivePosition();
            if (z || currentActivePosition < 0 || currentActivePosition >= oplusMediaOutputAdapter.getItemCount()) {
                oplusMediaOutputAdapter.updateItems();
            } else {
                oplusMediaOutputAdapter.notifyItemChanged(currentActivePosition);
            }
        }
    }

    public final void refreshHeaderMedia() {
        MediaMetadata metadata;
        String packageName = this.mediaSwitchingController.getPackageName();
        MediaSession.Token sessionToken = this.mediaSwitchingController.getSessionToken();
        MediaData mediaDataSelectByPackageOrToken = OplusQsMediaUtil.selectByPackageOrToken(packageName, sessionToken);
        if (mediaDataSelectByPackageOrToken != null) {
            onMediaDataChanged(mediaDataSelectByPackageOrToken);
            return;
        }
        if (packageName == null || packageName.length() == 0) {
            onMediaDataChanged(OplusQsMediaUtil.selectPlayingOnes());
            return;
        }
        if (sessionToken == null) {
            onMediaDataChanged(null);
            return;
        }
        this.mediaData = null;
        this.mediaController = new MediaController(getContext(), sessionToken);
        SeekBarViewModel seekBarViewModel = this.seekbarViewModel;
        if (seekBarViewModel != null) {
            seekBarViewModel.setListening(true);
            Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
            onMediaDataChanged.2.1 r4 = new onMediaDataChanged.2.1(1);
            r4.$it = seekBarViewModel;
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(r4);
        }
        MediaController mediaController = this.mediaController;
        MediaDescription description = (mediaController == null || (metadata = mediaController.getMetadata()) == null) ? null : metadata.getDescription();
        CharSequence title = description != null ? description.getTitle() : null;
        if (title == null || title.length() == 0) {
            refreshHeaderTitle();
        } else {
            TextView textView = this.headerSubtitle;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.headerTitle;
            if (textView2 != null) {
                textView2.setText(title);
            }
            TextView textView3 = this.headerSubtitle;
            if (textView3 != null) {
                textView3.setText(description.getSubtitle());
            }
        }
        Bitmap iconBitmap = description != null ? description.getIconBitmap() : null;
        AppCompatImageView appCompatImageView = this.headerIcon;
        if (iconBitmap == null || appCompatImageView == null) {
            updateArtwork();
        } else {
            appCompatImageView.setImageBitmap(iconBitmap);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusMediaOutputSwitchingPage-->".concat("bindHeaderFromSessionToken: title=" + ((Object) title) + " hasArtwork=" + (iconBitmap != null)));
        }
    }

    public final void refreshHeaderTitle() {
        MediaData mediaData = this.mediaData;
        MediaSession.Token token = mediaData != null ? mediaData.getToken() : null;
        TextView textView = this.headerSubtitle;
        if (token == null) {
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.headerTitle;
            if (textView2 != null) {
                textView2.setText(getContext().getResources().getString(2131955214));
                return;
            }
            return;
        }
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView3 = this.headerTitle;
        if (textView3 != null) {
            MediaData mediaData2 = this.mediaData;
            textView3.setText(mediaData2 != null ? mediaData2.getSong() : null);
        }
        TextView textView4 = this.headerSubtitle;
        if (textView4 != null) {
            MediaData mediaData3 = this.mediaData;
            textView4.setText(mediaData3 != null ? mediaData3.getArtist() : null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0055  */
    /* JADX WARN: Code duplicated, block: B:28:0x0057  */
    public final void updateArtwork() {
        int i;
        AppCompatImageView appCompatImageView = this.headerIcon;
        if (appCompatImageView == null) {
            return;
        }
        MediaData mediaData = this.mediaData;
        Icon artwork = mediaData != null ? mediaData.getArtwork() : null;
        if (artwork != null) {
            appCompatImageView.setImageIcon(artwork);
            return;
        }
        if (!QsColorUtil.isGlobalThemeApplied(getContext()) && !ScrimUtil.isLowGaussianLevel(getContext()) && !GaussBlurUtils.isQSGaussBlurDisabled()) {
            i = isLandscape() ? 2131236359 : 2131234397;
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
            if (isLandscape()) {
                i = 2131236361;
            } else {
                i = 2131234398;
            }
        } else if (!OplusDarkModeUtil.isNightMode(getContext())) {
            i = isLandscape() ? 2131236358 : 2131234396;
        } else if (isLandscape()) {
            i = 2131236361;
        } else {
            i = 2131234398;
        }
        appCompatImageView.setImageResource(i);
    }

    public final void updateBottomButton(boolean z, boolean z2) {
        OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout;
        boolean z3 = true;
        if (!z2 && this.mediaSwitchingController.getMissingPermissionsWarning() == null) {
            z3 = false;
        }
        if (z && (oplusMediaOutputBottomLayout = this.bottomButton) != null) {
            String string = getContext().getResources().getString(2131955592);
            createDialogView.3.2 r3 = new createDialogView.3.2(2);
            r3.this$0 = this;
            VarHandle.storeStoreFence();
            oplusMediaOutputBottomLayout.setRightButton(string, r3);
        }
        if (z || z3) {
            OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout2 = this.bottomButton;
            if (oplusMediaOutputBottomLayout2 != null) {
                oplusMediaOutputBottomLayout2.setVisibility(0);
                return;
            }
            return;
        }
        OplusMediaOutputBottomLayout oplusMediaOutputBottomLayout3 = this.bottomButton;
        if (oplusMediaOutputBottomLayout3 != null) {
            oplusMediaOutputBottomLayout3.setVisibility(8);
        }
    }

    public final void updateDevicesListLayout(boolean z) {
        FrameLayout frameLayout;
        COUIRecyclerView cOUIRecyclerView = this.devicesRecyclerView;
        if (cOUIRecyclerView == null || (frameLayout = this.volumeSeekBarParent) == null) {
            return;
        }
        boolean z2 = z && this.isVisibleVolumeSeekBar;
        boolean z3 = this.lastShowVolumeSeekBar != z2;
        int width = cOUIRecyclerView.getWidth();
        if (this.fullScreenView.isAttachedToWindow() && width > 0 && z3) {
            getManager().cancelTransition();
            getFadeTransition().addTargetProperty(new FadeTransition(frameLayout));
            getMoveTransition().addTargetProperty(new BoundsTransition(cOUIRecyclerView));
            if (z2) {
                getManager().addTransition(getFadeTransition(), 100L);
                getManager().addTransition(getMoveTransition());
            } else {
                getManager().addTransition(getFadeTransition());
                getManager().addTransition(getMoveTransition(), 100L);
            }
            SpringTransitionDelayManager manager = getManager();
            Set<SpringTransition> transitions = getManager().getTransitions();
            int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(transitions, 10));
            if (iMapCapacity < 16) {
                iMapCapacity = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
            for (Object obj : transitions) {
                linkedHashMap.put(obj, new Pair(Float.valueOf(0.0f), Float.valueOf(1.0f)));
            }
            manager.beginDelayedTransition(linkedHashMap);
        }
        frameLayout.setVisibility(z2 ? 0 : 8);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusMediaOutputSwitchingPage-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m("updateDevicesListLayout: inAudioSharing:", " isVisibleVolumeSeekBar:", " needShowVolumeSeekBar:", z, this.isVisibleVolumeSeekBar), z2);
        }
        this.lastShowVolumeSeekBar = z2;
    }

    public final void updateHeaderIconLayoutParams(View view) {
        if (!view.isLaidOut() || view.isLayoutRequested()) {
            OplusMediaOutputSwitchingPage$updateHeaderIconLayoutParams$.inlined.doOnLayout.1 r0 = new OplusMediaOutputSwitchingPage$updateHeaderIconLayoutParams$.inlined.doOnLayout.1();
            r0.this$0 = this;
            r0.$dialogView$inlined = view;
            VarHandle.storeStoreFence();
            view.addOnLayoutChangeListener(r0);
            return;
        }
        AppCompatImageView appCompatImageView = this.headerIcon;
        int height = appCompatImageView != null ? appCompatImageView.getHeight() : 0;
        TextView textView = this.headerTitle;
        int bottom = textView != null ? textView.getBottom() : 0;
        int height2 = view.getHeight();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172235);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(bottom, "OplusMediaOutputSwitchingPage-->", "SystemUi--QuickSettings", FloatList$.ExternalSyntheticOutline0.m("dialogViewHeight:", height2, height, " iconHeight:", " titleBottom:"));
        }
        if (height == 0 || bottom == 0 || height2 == 0) {
            updateHeaderIconLayoutParams(view);
            return;
        }
        int i = (height2 - bottom) - dimensionPixelSize;
        if (i < 0) {
            AppCompatImageView appCompatImageView2 = this.headerIcon;
            if (appCompatImageView2 != null) {
                ViewGroup.LayoutParams layoutParams = appCompatImageView2.getLayoutParams();
                if (layoutParams == null) {
                    ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                    return;
                }
                int i2 = height + i;
                layoutParams.width = i2;
                layoutParams.height = i2;
                appCompatImageView2.setLayoutParams(layoutParams);
            }
            AppCompatImageView appCompatImageView3 = this.headerIcon;
            if (appCompatImageView3 != null) {
                appCompatImageView3.invalidateOutline();
            }
        }
    }

    public final void updateMediaLyric(int i) {
        TextView textView;
        TextView textView2;
        OplusMediaDataEx mediaDataEx;
        OplusMediaLyricData lyricData;
        TextView textView3;
        CharSequence text;
        TextView textView4;
        CharSequence text2;
        if (!getPageManager().isMediaDetailToMediaOutputAnimating || (textView3 = this.headerTitle) == null || (text = textView3.getText()) == null || text.length() <= 0 || (textView4 = this.headerSubtitle) == null || (text2 = textView4.getText()) == null || text2.length() <= 0) {
            MediaData mediaData = this.mediaData;
            if (mediaData != null && (mediaDataEx = mediaData.getMediaDataEx()) != null && (lyricData = mediaDataEx.getLyricData()) != null && lyricData.isEnabled(32)) {
                TextView textView5 = this.headerTitle;
                if (textView5 != null) {
                    textView5.setText(lyricData.getCurrentLyric(i));
                }
                TextView textView6 = this.headerSubtitle;
                if (textView6 != null) {
                    textView6.setText(lyricData.getSongInfo());
                    return;
                }
                return;
            }
            MediaData mediaData2 = this.mediaData;
            if (mediaData2 != null) {
                TextView textView7 = this.headerTitle;
                if (!Intrinsics.areEqual(textView7 != null ? textView7.getText() : null, mediaData2.getSong()) && (textView2 = this.headerTitle) != null) {
                    textView2.setText(mediaData2.getSong());
                }
                TextView textView8 = this.headerSubtitle;
                if (Intrinsics.areEqual(textView8 != null ? textView8.getText() : null, mediaData2.getArtist()) || (textView = this.headerSubtitle) == null) {
                    return;
                }
                textView.setText(mediaData2.getArtist());
            }
        }
    }
}
