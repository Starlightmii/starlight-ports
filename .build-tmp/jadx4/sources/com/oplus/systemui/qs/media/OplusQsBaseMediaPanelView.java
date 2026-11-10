package com.oplus.systemui.qs.media;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.ObservableField;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.MediaViewProvider;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.common.ui.view.LaunchableConstraintLayout;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.domain.pipeline.MediaActionsKt;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.IHostAbility;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.coui.appcompat.COUIMaterialStateManager;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.media.OplusMediaDataFilterExImpl;
import com.oplus.systemui.media.seedling.MediaSeedlingBgThread;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.qs.OplusQSSmallSpaceController;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.InterestingViewFlag;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.ViewTagUtils;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView;
import com.oplus.systemui.qs.base.springtransition.AlphaTransitionProperty;
import com.oplus.systemui.qs.base.springtransition.ColorTransitionProperty;
import com.oplus.systemui.qs.base.springtransition.DrawableTransitionAlphaTarget;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.ImageViewColorFilterTarget;
import com.oplus.systemui.qs.base.springtransition.TextViewColorTarget;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.media.multilight.OplusQsMediaBackgroundDrawable;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.OplusQsMediaCacheUtil;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplus.util.OplusContextUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.model.MediaPanelData;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsBaseMediaPanelView extends LaunchableConstraintLayout implements ITransitionDesc, IEditableView, MediaViewProvider, IQSSpotLightConfig {
    private static final String SUPER_POWER_SAVE_DESKTOP_APP_LIST = "super_power_save_desktop_app_list";
    private static final String TAG = "OplusQsBaseMediaPanelView";
    public boolean avoidRefresh;
    public View bg;
    private final TileDrawableWrapper bgDrawable;
    private Handler bgHandler;
    private final DrawableTransitionAlphaTarget bgTransitionTarget;
    private View blurOverlayPlaceholderView;
    private View couiSeekBarView;
    public float coverCornerRadius;
    public float coverCornerWeight;
    private AppCompatImageView coverImg;
    public boolean defaultCover;
    private TextView defaultTip;
    private TextViewColorTarget defaultTipColorProperty;
    private OplusDotProgressBar dotLoading;
    public boolean enableStrokeLight;
    public int expectPlayState;
    private View favoritePlaceholderView;
    private Handler handler;
    private List<MediaData> historyList;
    public boolean inAudioSharing;
    public boolean isBeingDragged;
    public boolean isCurPauseAction;
    public boolean isEditMode;
    public boolean isLaunchingAnimating;
    public final boolean isMiddleOrHighAnimLevel;
    public final boolean isSupportLightEffect;
    public boolean isViewVisible;
    private OplusOutlineAdapter itemViewOplusOutlineAdapter;
    private Outline itemViewOutlineLast;
    public int lastMeasureHeight;
    public int lastMeasureWidth;
    private String lastMultiLightTrackKey;
    private MediaData launchingPendingData;
    private final Runnable lightAvailabilityListener;
    private final Runnable loadingRunnable;
    private View lyricPlaceholderView;
    private MediaController mediaController;
    private MediaOutputDialogManager mediaOutPutDialogFactory;
    private ImageButton mediaOutputBtn;
    private ImageViewColorFilterTarget mediaOutputBtnColorProperty;
    private View mediaOutputPlaceholderView;
    private MediaPanelData mediaPanelData;
    private ConstraintLayout mediaPanelLayout;
    private final NullableObservableField<Unit> musicRouteChange;
    private OplusLottieButton nextBtn;
    private ImageViewColorFilterTarget nextBtnColorProperty;
    private MediaData pendingMediaData;
    private MediaData pendingRefreshMediaData;
    private OplusQsPlayPauseButton playOrPauseBtn;
    private ImageViewColorFilterTarget playOrPauseBtnColorProperty;
    private OplusLottieButton preBtn;
    private ImageViewColorFilterTarget preBtnColorProperty;
    public int primaryColor80;
    private final OplusQsBaseMediaPanelView$refreshActionButtonRunnable$1 refreshActionButtonRunnable;
    private final Runnable refreshPhotoCoverRunnable;
    private View seekBarPlaceholderView;
    private final Runnable stopLoadingRunnable;
    private String superPowerListString;
    private TextView text;
    private TextViewColorTarget textColorProperty;
    private final TileDrawableWrapper themeBgDrawable;
    private MediaData themeMediaData;
    private TextView title;
    private TextViewColorTarget titleColorProperty;
    private final ConstraintSet tmpConstrainSet;
    private MediaSession.Token token;
    private final OplusQsMediaBackgroundDrawable transitionDrawable;
    private final InterestingViewFlag viewFlag;
    public final float viewRadius;
    private View volumeSeekBarPlaceholderView;
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(2, 2);
    private static final List<Double> TINE_LIST = CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(80.0d));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView$setSemanticButton$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ MediaData $data;
        public /* synthetic */ OplusLottieButton $imageButton;
        public /* synthetic */ MediaAction $mediaAction;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQsBaseMediaPanelView this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Runnable action;
            int i = this.$r8$classId;
            String str = "5";
            String str2 = AodMediaStatisticUtil.EVENT_NEXT;
            switch (i) {
                case 0:
                    OplusLottieButton oplusLottieButton = this.$imageButton;
                    if (Intrinsics.areEqual(oplusLottieButton, this.this$0.getPreBtn())) {
                        str2 = "pre";
                    } else if (Intrinsics.areEqual(oplusLottieButton, this.this$0.getPlayOrPauseBtn())) {
                        str2 = "pauseOrPlay";
                    } else if (!Intrinsics.areEqual(oplusLottieButton, this.this$0.getNextBtn())) {
                        str2 = "";
                    }
                    OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "click event ".concat(str2));
                    OplusQsBaseMediaPanelView.access$avoidRefreshActionButton(this.this$0);
                    if (this.this$0.isBuffering()) {
                        OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "Currently buffering media data, please wait for buffering to complete , pkg= " + LogUtil.encryptionNotificationKey(this.$data.getNotificationKey()));
                        if (NotificationMediaManager.isPlayingState(this.$data.getMediaDataEx().getPreState())) {
                            OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "setSemanticButton: preState is playing");
                            this.this$0.handler.post(this.this$0.loadingRunnable);
                            this.this$0.handler.postDelayed(this.this$0.stopLoadingRunnable, 3000L);
                        }
                    }
                    if (Intrinsics.areEqual(this.$imageButton, this.this$0.getPlayOrPauseBtn())) {
                        OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
                        oplusQsBaseMediaPanelView.expectPlayState = oplusQsBaseMediaPanelView.isCurPauseAction ? 2 : 3;
                    }
                    Runnable action2 = this.$mediaAction.getAction();
                    if (action2 != null) {
                        action2.run();
                    }
                    Context context = this.this$0.getContext();
                    OplusLottieButton oplusLottieButton2 = this.$imageButton;
                    if (Intrinsics.areEqual(oplusLottieButton2, this.this$0.getPreBtn())) {
                        str = "3";
                    } else if (Intrinsics.areEqual(oplusLottieButton2, this.this$0.getPlayOrPauseBtn())) {
                        str = "4";
                    } else if (!Intrinsics.areEqual(oplusLottieButton2, this.this$0.getNextBtn())) {
                        str = "";
                    }
                    QsStatisticUtil.collectMediaPanelActionClick(context, str);
                    break;
                default:
                    OplusLottieButton oplusLottieButton3 = this.$imageButton;
                    if (Intrinsics.areEqual(oplusLottieButton3, this.this$0.getPreBtn())) {
                        str2 = "pre";
                    } else if (Intrinsics.areEqual(oplusLottieButton3, this.this$0.getPlayOrPauseBtn())) {
                        str2 = "pauseOrPlay";
                    } else if (!Intrinsics.areEqual(oplusLottieButton3, this.this$0.getNextBtn())) {
                        str2 = "";
                    }
                    OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "click event ".concat(str2));
                    if (this.this$0.isBuffering()) {
                        OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "Currently buffering media data, please wait for buffering to complete ");
                        if (NotificationMediaManager.isPlayingState(this.$data.getMediaDataEx().getPreState())) {
                            OplusNotificationLogger.logMediaPanel(OplusQsBaseMediaPanelView.TAG, "bindViewWithAction: preState is playing");
                            this.this$0.handler.post(this.this$0.loadingRunnable);
                            this.this$0.handler.postDelayed(this.this$0.stopLoadingRunnable, 3000L);
                        }
                    }
                    this.this$0.handler.postDelayed(this.this$0.loadingRunnable, 1000L);
                    this.this$0.handler.postDelayed(this.this$0.stopLoadingRunnable, 3000L);
                    MediaAction mediaAction = this.$mediaAction;
                    if (mediaAction != null && (action = mediaAction.getAction()) != null) {
                        action.run();
                    }
                    Context context2 = this.this$0.getContext();
                    OplusLottieButton oplusLottieButton4 = this.$imageButton;
                    if (Intrinsics.areEqual(oplusLottieButton4, this.this$0.getPreBtn())) {
                        str = "3";
                    } else if (Intrinsics.areEqual(oplusLottieButton4, this.this$0.getPlayOrPauseBtn())) {
                        str = "4";
                    } else if (!Intrinsics.areEqual(oplusLottieButton4, this.this$0.getNextBtn())) {
                        str = "";
                    }
                    QsStatisticUtil.collectMediaPanelActionClick(context2, str);
                    break;
            }
        }
    }

    public static Unit $r8$lambda$A6RnVVz7l68iaMQ5SO4_TDnhNR8(OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView) {
        if (oplusQsBaseMediaPanelView.mediaPanelData == null) {
            oplusQsBaseMediaPanelView.updateMediaOutputBtn(((Number) OplusQsVolumeIconView.Companion.getMusicStreamRoute().get()).intValue());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsBaseMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.handler = new Handler(Looper.getMainLooper());
        this.bgHandler = new Handler(MediaSeedlingBgThread.INSTANCE.getThreadLooper());
        this.historyList = new ArrayList();
        int i3 = 1;
        this.defaultCover = true;
        ObservableField[] observableFieldArr = {OplusQsVolumeIconView.Companion.getMusicStreamRoute()};
        OplusQsBaseMediaPanelView$$ExternalSyntheticLambda0 oplusQsBaseMediaPanelView$$ExternalSyntheticLambda0 = new OplusQsBaseMediaPanelView$$ExternalSyntheticLambda0();
        oplusQsBaseMediaPanelView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        int i4 = 0;
        this.musicRouteChange = new NullableObservableField<>(observableFieldArr, false, oplusQsBaseMediaPanelView$$ExternalSyntheticLambda0);
        this.superPowerListString = "";
        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
        this.bgDrawable = tileDrawableWrapper;
        TileDrawableWrapper tileDrawableWrapper2 = new TileDrawableWrapper(null);
        this.themeBgDrawable = tileDrawableWrapper2;
        OplusQsMediaBackgroundDrawable oplusQsMediaBackgroundDrawable = new OplusQsMediaBackgroundDrawable(context, tileDrawableWrapper, tileDrawableWrapper2);
        this.transitionDrawable = oplusQsMediaBackgroundDrawable;
        this.bgTransitionTarget = new DrawableTransitionAlphaTarget(oplusQsMediaBackgroundDrawable);
        this.viewFlag = new InterestingViewFlag();
        this.tmpConstrainSet = new ConstraintSet();
        this.coverCornerWeight = 3.0f;
        boolean zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(((ViewGroup) this).mContext);
        this.isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        this.isSupportLightEffect = FeatureOption.getEnableMaterialFeature() && zIsMiddleOrHighAnimLevel && FeatureOption.getEnableLiveAlertEffectLight();
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$1 = new OplusQsBaseMediaPanelView$loadingRunnable$1(i3);
        oplusQsBaseMediaPanelView$loadingRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.lightAvailabilityListener = oplusQsBaseMediaPanelView$loadingRunnable$1;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$2 = new OplusQsBaseMediaPanelView$loadingRunnable$1(6);
        oplusQsBaseMediaPanelView$loadingRunnable$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.refreshPhotoCoverRunnable = oplusQsBaseMediaPanelView$loadingRunnable$2;
        this.viewRadius = ((ViewGroup) this).mContext.getResources().getDimension(2131171111);
        OplusQsBaseMediaPanelView$refreshActionButtonRunnable$1 oplusQsBaseMediaPanelView$refreshActionButtonRunnable$1 = new OplusQsBaseMediaPanelView$refreshActionButtonRunnable$1();
        oplusQsBaseMediaPanelView$refreshActionButtonRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.refreshActionButtonRunnable = oplusQsBaseMediaPanelView$refreshActionButtonRunnable$1;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$3 = new OplusQsBaseMediaPanelView$loadingRunnable$1(i4);
        oplusQsBaseMediaPanelView$loadingRunnable$3.this$0 = this;
        VarHandle.storeStoreFence();
        this.loadingRunnable = oplusQsBaseMediaPanelView$loadingRunnable$3;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$4 = new OplusQsBaseMediaPanelView$loadingRunnable$1(7);
        oplusQsBaseMediaPanelView$loadingRunnable$4.this$0 = this;
        VarHandle.storeStoreFence();
        this.stopLoadingRunnable = oplusQsBaseMediaPanelView$loadingRunnable$4;
    }

    public static final void access$avoidRefreshActionButton(OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView) {
        if (!oplusQsBaseMediaPanelView.avoidRefresh) {
            oplusQsBaseMediaPanelView.pendingRefreshMediaData = null;
            oplusQsBaseMediaPanelView.avoidRefresh = true;
        }
        oplusQsBaseMediaPanelView.handler.removeCallbacks(oplusQsBaseMediaPanelView.refreshActionButtonRunnable);
        oplusQsBaseMediaPanelView.handler.postDelayed(oplusQsBaseMediaPanelView.refreshActionButtonRunnable, 1300L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r4v3 */
    public static final void access$handleClickMediaOutputBtn(OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView, MediaData mediaData) {
        ?? r4;
        if (oplusQsBaseMediaPanelView.mediaOutPutDialogFactory == null) {
            oplusQsBaseMediaPanelView.mediaOutPutDialogFactory = (MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class);
        }
        MediaOutputDialogManager mediaOutputDialogManager = oplusQsBaseMediaPanelView.mediaOutPutDialogFactory;
        if (mediaOutputDialogManager != null) {
            ?? r5 = oplusQsBaseMediaPanelView;
            MediaOutputDialogManager.create$default(mediaOutputDialogManager, mediaData != null ? mediaData.getPackageName() : null, true, (View) r5, true, false, mediaData != null ? mediaData.getToken() : null, false, 160);
            r4 = r5;
        } else {
            r4 = oplusQsBaseMediaPanelView;
        }
        QsStatisticUtil.collectMediaPanelActionClick(r4.getContext(), "6");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void access$refreshMultiLightEffect(OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView, boolean z, String str) {
        if (oplusQsBaseMediaPanelView.isMiddleOrHighAnimLevel) {
            boolean zIsGlobalThemeApplied = QsColorUtil.isGlobalThemeApplied(oplusQsBaseMediaPanelView.getContext());
            boolean z2 = oplusQsBaseMediaPanelView.enableStrokeLight;
            String str2 = oplusQsBaseMediaPanelView.lastMultiLightTrackKey;
            boolean z3 = oplusQsBaseMediaPanelView.isSupportLightEffect;
            boolean z4 = oplusQsBaseMediaPanelView.isEditMode;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("refreshMultiLightEffect reason =", str, " spotLightEffectAvailable=", z2, ", lastMultiLightTrackKey=");
            DeviceSettingItem$.ExternalSyntheticOutline0.m(str2, ", isGlobalThemeApplied=", ", shouldUseDiffusion=", sbM, zIsGlobalThemeApplied);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isSupportLightEffect=", ", isEditMode=", sbM, z, z3);
            sbM.append(z4);
            OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
            if (oplusQsBaseMediaPanelView.lastMultiLightTrackKey == null || zIsGlobalThemeApplied || !oplusQsBaseMediaPanelView.isSupportLightEffect || !oplusQsBaseMediaPanelView.enableStrokeLight) {
                OplusQsMediaBackgroundDrawable.clearLight$default(oplusQsBaseMediaPanelView.transitionDrawable);
                return;
            }
            oplusQsBaseMediaPanelView.transitionDrawable.applyShadowAndOutlineLight(oplusQsBaseMediaPanelView.primaryColor80, OplusDarkModeUtil.isNightMode(oplusQsBaseMediaPanelView.getContext()));
            if (z && !oplusQsBaseMediaPanelView.isEditMode && oplusQsBaseMediaPanelView.isShown()) {
                Pair<Float, Float> diffusionPositionOffsets = oplusQsBaseMediaPanelView.getDiffusionPositionOffsets();
                oplusQsBaseMediaPanelView.transitionDrawable.applySpreadLight(((Number) diffusionPositionOffsets.component1()).floatValue(), ((Number) diffusionPositionOffsets.component2()).floatValue());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getCoverImgRes() {
        if (this.isEditMode) {
            return 2131234399;
        }
        if (QsColorUtil.isGlobalThemeApplied(getContext()) || ScrimUtil.isLowGaussianLevel(getContext()) || GaussBlurUtils.isQSGaussBlurDisabled()) {
            return (QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode) || OplusDarkModeUtil.isNightMode(getContext())) ? 2131234398 : 2131234396;
        }
        return 2131234397;
    }

    private final int getDeviceHeadphonesRes() {
        return this.isEditMode ? 2131237118 : 2131237117;
    }

    private final int getDeviceSwitchRes() {
        return this.isEditMode ? 2131237113 : 2131237112;
    }

    private final int getDeviceWiredRes() {
        return this.isEditMode ? 2131237169 : 2131237168;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Pair<Float, Float> getDiffusionPositionOffsets() {
        Float fValueOf = Float.valueOf(0.0f);
        AppCompatImageView appCompatImageView = this.coverImg;
        if (appCompatImageView == null) {
            return new Pair<>(fValueOf, fValueOf);
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        appCompatImageView.getLocationOnScreen(iArr);
        getLocationOnScreen(iArr2);
        return new Pair<>(Float.valueOf(((appCompatImageView.getWidth() / 2.0f) + iArr[0]) - ((getWidth() / 2.0f) + iArr2[0])), Float.valueOf(((appCompatImageView.getHeight() / 2.0f) + iArr[1]) - ((getHeight() / 2.0f) + iArr2[1])));
    }

    private final int getNextBtnRes() {
        return this.isEditMode ? 2131234389 : 2131234388;
    }

    private final int getPreBtnRes() {
        return this.isEditMode ? 2131234395 : 2131234394;
    }

    public static void updateTalkBackEnable(View view, boolean z) {
        if (z) {
            view.setImportantForAccessibility(2);
        } else {
            view.setImportantForAccessibility(1);
        }
    }

    public final void bindCoverImg(MediaData mediaData) {
        if (mediaData == null || mediaData.getResumption()) {
            this.handler.removeCallbacks(this.refreshPhotoCoverRunnable);
            AppCompatImageView appCompatImageView = this.coverImg;
            if (appCompatImageView != null) {
                appCompatImageView.setImageDrawable((Drawable) null);
            }
            AppCompatImageView appCompatImageView2 = this.coverImg;
            if (appCompatImageView2 != null) {
                appCompatImageView2.setVisibility(4);
            }
            this.defaultCover = true;
            return;
        }
        this.handler.removeCallbacks(this.refreshPhotoCoverRunnable);
        AppCompatImageView appCompatImageView3 = this.coverImg;
        if (appCompatImageView3 != null) {
            appCompatImageView3.setVisibility(0);
        }
        if (mediaData.getArtwork() == null) {
            this.handler.postDelayed(this.refreshPhotoCoverRunnable, 200L);
            resetCoverImg();
        } else {
            AppCompatImageView appCompatImageView4 = this.coverImg;
            if (appCompatImageView4 != null) {
                appCompatImageView4.setImageIcon(mediaData.getArtwork());
            }
            this.defaultCover = false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:69:0x0124  */
    /* JADX WARN: Code duplicated, block: B:71:0x0130  */
    /* JADX WARN: Code duplicated, block: B:75:0x0143  */
    public final void bindMediaAction(MediaData mediaData) {
        Boolean boolIsPlaying = mediaData != null ? mediaData.isPlaying() : null;
        MediaSession.Token token = mediaData != null ? mediaData.getToken() : null;
        boolean z = this.avoidRefresh;
        int i = this.expectPlayState;
        String packageName = mediaData != null ? mediaData.getPackageName() : null;
        MediaData mediaData2 = this.themeMediaData;
        String packageName2 = mediaData2 != null ? mediaData2.getPackageName() : null;
        StringBuilder sb = new StringBuilder("mediaData.isPlaying= ");
        sb.append(boolIsPlaying);
        sb.append(", token = ");
        sb.append(token);
        sb.append(", avoidRefresh = ");
        PromptIconState$.ExternalSyntheticOutline0.m(i, ", expectPlayState = ", ", mediaData.packageName = ", sb, z);
        sb.append(packageName);
        sb.append(", themeData.packageName = ");
        sb.append(packageName2);
        OplusNotificationLogger.logMediaPanel(TAG, sb.toString());
        if ((mediaData != null ? mediaData.getToken() : null) == null) {
            resetButtonEnableState("bindMediaAction token is null");
            return;
        }
        if (this.avoidRefresh) {
            this.pendingRefreshMediaData = mediaData;
            stopLoading();
            if (this.expectPlayState != 0 && isMediaDataExpected(mediaData)) {
                this.handler.removeCallbacks(this.refreshActionButtonRunnable);
                refreshActionButton();
                return;
            } else {
                if (this.expectPlayState == 0 && Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE)) {
                    this.handler.removeCallbacks(this.refreshActionButtonRunnable);
                    refreshActionButton();
                    return;
                }
                return;
            }
        }
        if (mediaData.getSemanticActions() != null) {
            MediaButton semanticActions = mediaData.getSemanticActions();
            if ((semanticActions != null ? semanticActions.getPlayOrPause() : null) != null) {
                MediaButton semanticActions2 = mediaData.getSemanticActions();
                MediaAction prevOrCustom = semanticActions2 != null ? semanticActions2.getPrevOrCustom() : null;
                MediaAction playOrPause = semanticActions2 != null ? semanticActions2.getPlayOrPause() : null;
                MediaAction nextOrCustom = semanticActions2 != null ? semanticActions2.getNextOrCustom() : null;
                this.expectPlayState = 0;
                setSemanticButton(this.preBtn, getPreBtnRes(), prevOrCustom, mediaData);
                OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
                if (Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE) || (NotificationMediaManager.isPlayingState(mediaData.getMediaDataEx().getPreState()) && isBuffering())) {
                    this.isCurPauseAction = true;
                    OplusQsPlayPauseButton oplusQsPlayPauseButton2 = this.playOrPauseBtn;
                    if (oplusQsPlayPauseButton2 != null) {
                        oplusQsPlayPauseButton2.setPlaybackState(3);
                    }
                } else {
                    this.isCurPauseAction = false;
                    OplusQsPlayPauseButton oplusQsPlayPauseButton3 = this.playOrPauseBtn;
                    if (oplusQsPlayPauseButton3 != null) {
                        oplusQsPlayPauseButton3.setPlaybackState(2);
                    }
                }
                setSemanticButton(oplusQsPlayPauseButton, 0, playOrPause, mediaData);
                setSemanticButton(this.nextBtn, getNextBtnRes(), nextOrCustom, mediaData);
            } else if (mediaData.getActions().isEmpty()) {
                resetButtonEnableState("bindMediaAction not support mediaAction");
            } else {
                OplusNotificationLogger.logMediaPanel(TAG, "bindMediaNotification");
                bindMediaNotificationAction(mediaData);
            }
        } else if (mediaData.getActions().isEmpty()) {
            OplusNotificationLogger.logMediaPanel(TAG, "bindMediaNotification");
            try {
                bindMediaNotificationAction(mediaData);
            } catch (RemoteException unused) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "media data is exception");
            }
        } else {
            resetButtonEnableState("bindMediaAction not support mediaAction");
        }
        MediaData mediaData3 = this.themeMediaData;
        if ((mediaData3 != null ? Intrinsics.areEqual(mediaData3.isPlaying(), Boolean.TRUE) : false) || !Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE)) {
            this.themeMediaData = mediaData;
        } else {
            this.themeMediaData = mediaData;
            setLayerType(0, null);
        }
    }

    public final void bindMediaData(MediaData mediaData, String str) {
        if (this.isViewVisible || mediaData == null) {
            bindMediaDataInner(mediaData, str);
            return;
        }
        this.pendingMediaData = mediaData;
        this.lastMultiLightTrackKey = ((Object) mediaData.getSong()) + "|" + ((Object) mediaData.getArtist());
        StringBuilder sb = new StringBuilder("bindMediaData reason=");
        sb.append(str);
        sb.append(", delayed for invisible");
        OplusNotificationLogger.logMediaPanel(TAG, sb.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bindMediaDataInner(MediaData mediaData, String str) {
        MediaPanelData mediaPanelData;
        CharSequence string;
        int color;
        Drawable.ConstantState constantState;
        int i = 0;
        OplusNotificationLogger.logMediaPanel(TAG, "bindMediaData reason=" + str);
        Drawable icon = null;
        this.pendingMediaData = null;
        OplusQsMediaItemViewCache.INSTANCE.setShowMedia(true);
        if (mediaData != null) {
            String artist = mediaData.getMediaDataEx().getLyricData().getArtist();
            if (artist == null) {
                CharSequence artist2 = mediaData.getArtist();
                artist = artist2 != null ? artist2.toString() : null;
            }
            String song = mediaData.getMediaDataEx().getLyricData().getSong();
            if (song == null) {
                CharSequence song2 = mediaData.getSong();
                song = song2 != null ? song2.toString() : null;
            }
            mediaPanelData = new MediaPanelData(artist, song, getContext().getString(2131955666), getContext().getString(2131955666), 0, 0);
        } else {
            mediaPanelData = null;
        }
        this.mediaPanelData = mediaPanelData;
        if (this.isLaunchingAnimating) {
            this.launchingPendingData = mediaData;
            return;
        }
        this.launchingPendingData = null;
        MediaSession.Token token = mediaData != null ? mediaData.getToken() : null;
        if (!Intrinsics.areEqual(this.token, token)) {
            this.token = token;
            this.mediaController = token != null ? new MediaController(getContext(), token) : null;
        }
        MediaController mediaController = this.mediaController;
        if (mediaController != null) {
            bindMediaProgress(mediaController);
        }
        bindCoverImg(mediaData);
        bindTitleAndText(this.mediaPanelData);
        ImageButton imageButton = this.mediaOutputBtn;
        if (mediaData != null) {
            if (imageButton != null) {
                imageButton.setEnabled(true);
            }
            MediaDeviceData device = mediaData.getDevice();
            float f = ((device == null || device.getEnabled()) && !mediaData.getResumption()) ? 1.0f : 0.38f;
            ImageButton imageButton2 = this.mediaOutputBtn;
            if (imageButton2 != null) {
                imageButton2.setAlpha(f);
            }
            if (device == null || (string = device.getName()) == null) {
                string = ((ViewGroup) this).mContext.getString(2131954562);
            }
            if (device == null || device.getIconResId() == 2131237104) {
                ImageButton imageButton3 = this.mediaOutputBtn;
                if (imageButton3 != null) {
                    imageButton3.setImageResource(2131237112);
                }
            } else {
                Drawable icon2 = device.getIcon();
                if (icon2 != null && (constantState = icon2.getConstantState()) != null) {
                    icon = constantState.newDrawable();
                }
                ImageButton imageButton4 = this.mediaOutputBtn;
                if (imageButton4 != null) {
                    if (icon == null) {
                        icon = device.getIcon();
                    }
                    imageButton4.setImageDrawable(icon);
                }
            }
            if (QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode)) {
                color = getContext().getColor(2131103287);
            } else {
                color = QsColorUtil.isMediaIconNeedUseLightColor(((ViewGroup) this).mContext, this.isEditMode) ? QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR : ((ViewGroup) this).mContext.getColor(2131103367);
            }
            ImageViewColorFilterTarget imageViewColorFilterTarget = this.mediaOutputBtnColorProperty;
            if (imageViewColorFilterTarget != null) {
                imageViewColorFilterTarget.set(color);
            }
            ImageButton imageButton5 = this.mediaOutputBtn;
            if (imageButton5 != null) {
                imageButton5.setContentDescription(string);
            }
            ImageButton imageButton6 = this.mediaOutputBtn;
            if (imageButton6 != null) {
                OplusQsBaseMediaPanelView$bindDevice$1 oplusQsBaseMediaPanelView$bindDevice$1 = new OplusQsBaseMediaPanelView$bindDevice$1(i);
                oplusQsBaseMediaPanelView$bindDevice$1.this$0 = this;
                oplusQsBaseMediaPanelView$bindDevice$1.$data = mediaData;
                VarHandle.storeStoreFence();
                imageButton6.setOnClickListener(oplusQsBaseMediaPanelView$bindDevice$1);
            }
        } else if (imageButton != null) {
            imageButton.setEnabled(false);
        }
        bindMediaAction(mediaData);
        setBtnColor();
        updateMultiLightForCurrentTrack(mediaData);
    }

    public final void bindMediaNotificationAction(MediaData mediaData) {
        List actionsToShowInCompact = mediaData.getActionsToShowInCompact();
        List notificationActions = MediaActionsKt.getNotificationActions(mediaData.getActions(), (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class));
        List listListOf = CollectionsKt__CollectionsKt.listOf(new OplusLottieButton[]{this.preBtn, this.playOrPauseBtn, this.nextBtn});
        int i = 0;
        for (int i2 = 0; i2 < notificationActions.size(); i2++) {
            if (actionsToShowInCompact.contains(Integer.valueOf(i2)) && i < listListOf.size()) {
                bindViewWithAction((OplusLottieButton) listListOf.get(i), (MediaAction) notificationActions.get(i2), mediaData);
                i++;
            }
        }
        while (i < listListOf.size()) {
            bindViewWithAction((OplusLottieButton) listListOf.get(i), null, mediaData);
            i++;
        }
    }

    public void bindMediaProgress(MediaController mediaController) {
    }

    public final void bindOrResetMediaData(String str, boolean z) {
        MediaData mediaDataSelectPlayingOnes = OplusQsMediaUtil.selectPlayingOnes();
        Boolean boolIsPlaying = mediaDataSelectPlayingOnes != null ? mediaDataSelectPlayingOnes.isPlaying() : null;
        String packageName = mediaDataSelectPlayingOnes != null ? mediaDataSelectPlayingOnes.getPackageName() : null;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("bindOrResetMediaData reason: ", str, ", bindMedia=", z, ", selectData.isPlaying=");
        sbM.append(boolIsPlaying);
        sbM.append(", pkg=");
        sbM.append(packageName);
        sbM.append(", selectData: ");
        sbM.append(mediaDataSelectPlayingOnes);
        OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
        if (mediaDataSelectPlayingOnes == null) {
            resetButtonEnableState(str);
        } else if (z) {
            bindMediaAction(mediaDataSelectPlayingOnes);
            setBtnColor();
        }
    }

    public final void bindTitleAndText(MediaPanelData mediaPanelData) {
        TextView textView = this.defaultTip;
        if (mediaPanelData == null) {
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.title;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
            TextView textView3 = this.text;
            if (textView3 != null) {
                textView3.setVisibility(4);
                return;
            }
            return;
        }
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView4 = this.title;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
        TextView textView5 = this.title;
        if (textView5 != null) {
            textView5.setText(mediaPanelData.getSong());
        }
        TextView textView6 = this.text;
        if (textView6 != null) {
            textView6.setVisibility(0);
        }
        TextView textView7 = this.text;
        if (textView7 != null) {
            textView7.setText(mediaPanelData.getArtist());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bindViewWithAction(OplusLottieButton oplusLottieButton, MediaAction mediaAction, MediaData mediaData) {
        Drawable icon;
        OplusMediaActionEx mediaActionEx;
        Icon icon2;
        stopLoading();
        if (mediaAction == null || (mediaActionEx = mediaAction.getMediaActionEx()) == null || (icon2 = mediaActionEx.getIcon()) == null || (icon = icon2.loadDrawableAsUser(((ViewGroup) this).mContext, OsBinderCacheUtils.getCurrentUserId())) == null) {
            icon = mediaAction != null ? mediaAction.getIcon() : null;
        }
        if (icon != null) {
            if (oplusLottieButton != null) {
                oplusLottieButton.setImageDrawable(icon);
            }
            if (oplusLottieButton != null) {
                oplusLottieButton.setColorFilter(getContext().getColor(2131100936));
            }
        }
        if (oplusLottieButton != null) {
            oplusLottieButton.setVisibility((mediaAction != null ? mediaAction.getAction() : null) != null ? 0 : 4);
        }
        if (oplusLottieButton != null) {
            oplusLottieButton.setContentDescription(mediaAction != null ? mediaAction.getContentDescription() : null);
        }
        int i = 1;
        if (oplusLottieButton != null) {
            oplusLottieButton.setEnabled((mediaAction != null ? mediaAction.getAction() : null) != null);
        }
        if (oplusLottieButton != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
            anonymousClass1.$imageButton = oplusLottieButton;
            anonymousClass1.this$0 = this;
            anonymousClass1.$data = mediaData;
            anonymousClass1.$mediaAction = mediaAction;
            VarHandle.storeStoreFence();
            oplusLottieButton.setOnClickListener(anonymousClass1);
        }
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if (ITransitionDesc.MAIN_TRANSITION.equals(str) && BaseQSResPool.globalThemeApplied()) {
            list.add(new Pair<>(this.bgTransitionTarget, AlphaTransitionProperty.class));
            ImageViewColorFilterTarget imageViewColorFilterTarget = this.mediaOutputBtnColorProperty;
            if (imageViewColorFilterTarget != null) {
                list.add(new Pair<>(imageViewColorFilterTarget, ColorTransitionProperty.class));
            }
            TextViewColorTarget textViewColorTarget = this.defaultTipColorProperty;
            if (textViewColorTarget != null) {
                list.add(new Pair<>(textViewColorTarget, ColorTransitionProperty.class));
            }
            TextViewColorTarget textViewColorTarget2 = this.titleColorProperty;
            if (textViewColorTarget2 != null) {
                list.add(new Pair<>(textViewColorTarget2, ColorTransitionProperty.class));
            }
            TextViewColorTarget textViewColorTarget3 = this.textColorProperty;
            if (textViewColorTarget3 != null) {
                list.add(new Pair<>(textViewColorTarget3, ColorTransitionProperty.class));
            }
            ImageViewColorFilterTarget imageViewColorFilterTarget2 = this.preBtnColorProperty;
            if (imageViewColorFilterTarget2 != null) {
                list.add(new Pair<>(imageViewColorFilterTarget2, ColorTransitionProperty.class));
            }
            ImageViewColorFilterTarget imageViewColorFilterTarget3 = this.playOrPauseBtnColorProperty;
            if (imageViewColorFilterTarget3 != null) {
                list.add(new Pair<>(imageViewColorFilterTarget3, ColorTransitionProperty.class));
            }
            ImageViewColorFilterTarget imageViewColorFilterTarget4 = this.nextBtnColorProperty;
            if (imageViewColorFilterTarget4 != null) {
                list.add(new Pair<>(imageViewColorFilterTarget4, ColorTransitionProperty.class));
            }
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void editModeChange(boolean z) {
        setClickable(!z);
        setLongClickable(!z);
    }

    public View getAppIcon() {
        return this.mediaOutputBtn;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Float getAppIconRadius() {
        float dimension = getContext().getResources().getDimension(2131171041);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(getContext(), dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        return Float.valueOf(dimension);
    }

    public final View getBg() {
        View view = this.bg;
        if (view != null) {
            return view;
        }
        return null;
    }

    public final TileDrawableWrapper getBgDrawable() {
        return this.bgDrawable;
    }

    public final Handler getBgHandler() {
        return this.bgHandler;
    }

    public final DrawableTransitionAlphaTarget getBgTransitionTarget() {
        return this.bgTransitionTarget;
    }

    public View getBgView() {
        return getBg();
    }

    public View getBlurOverlayPlaceholder() {
        return this.blurOverlayPlaceholderView;
    }

    public final View getBlurOverlayPlaceholderView() {
        return this.blurOverlayPlaceholderView;
    }

    public /* bridge */ /* synthetic */ View getCarouselLayout() {
        return super.getCarouselLayout();
    }

    public /* bridge */ /* synthetic */ List getChildViews() {
        return super.getChildViews();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getContainer() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Float getContainerCornerRadius() {
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(getContext(), getViewRadius());
        return Float.valueOf(roundParams != null ? roundParams.getRadius() : getViewRadius());
    }

    public View getCouiSeekBar() {
        return null;
    }

    public final View getCouiSeekBarView() {
        return this.couiSeekBarView;
    }

    /* JADX INFO: renamed from: getCoverImg, reason: collision with other method in class */
    public final AppCompatImageView m284getCoverImg() {
        return this.coverImg;
    }

    public Float getCoverImgBottomRadius() {
        return getCoverImgRadius();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Float getCoverImgRadius() {
        float dimension = getContext().getResources().getDimension(2131171047);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(getContext(), dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        return Float.valueOf(dimension);
    }

    /* JADX INFO: renamed from: getDefaultTip, reason: collision with other method in class */
    public final TextView m285getDefaultTip() {
        return this.defaultTip;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getDimensionPixelSize(int i, IHostAbility.LayoutConstraint layoutConstraint, boolean z) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        Float fValueOf = layoutConstraint != null ? Float.valueOf(layoutConstraint.getInnerContentRatio()) : null;
        if (z) {
            fValueOf = layoutConstraint != null ? Float.valueOf(layoutConstraint.getInnerMarginRatio()) : null;
        }
        if (fValueOf != null) {
            return (int) (fValueOf.floatValue() * dimensionPixelSize);
        }
        return dimensionPixelSize;
    }

    public View getDotLoadingView() {
        return this.dotLoading;
    }

    public boolean getEnablePressFeedback() {
        return this.isEditMode;
    }

    public View getFavoritePlaceholder() {
        return this.favoritePlaceholderView;
    }

    public final View getFavoritePlaceholderView() {
        return this.favoritePlaceholderView;
    }

    public View getLyricPlaceholder() {
        return this.lyricPlaceholderView;
    }

    public final View getLyricPlaceholderView() {
        return this.lyricPlaceholderView;
    }

    public final MediaController getMediaController() {
        return this.mediaController;
    }

    public final MediaPanelData getMediaData() {
        this.handler.removeCallbacks(this.stopLoadingRunnable);
        this.handler.post(this.stopLoadingRunnable);
        return this.mediaPanelData;
    }

    /* JADX INFO: renamed from: getMediaOutputBtn, reason: collision with other method in class */
    public final ImageButton m286getMediaOutputBtn() {
        return this.mediaOutputBtn;
    }

    public final View getMediaOutputPlaceholderView() {
        return this.mediaOutputPlaceholderView;
    }

    public View getMediaOutputSwitchBtn() {
        return this.mediaOutputBtn;
    }

    public /* bridge */ /* synthetic */ View getMediaPageIndicator() {
        return super.getMediaPageIndicator();
    }

    public final MediaPanelData getMediaPanelData() {
        return this.mediaPanelData;
    }

    public final ConstraintLayout getMediaPanelLayout() {
        return this.mediaPanelLayout;
    }

    public final OplusLottieButton getNextBtn() {
        return this.nextBtn;
    }

    public View getNextButton() {
        return this.nextBtn;
    }

    public /* bridge */ /* synthetic */ int getOffsetPixels() {
        return super.getOffsetPixels();
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    public final OplusQsPlayPauseButton getPlayOrPauseBtn() {
        return this.playOrPauseBtn;
    }

    public View getPlayOrPauseButton() {
        return this.playOrPauseBtn;
    }

    public final OplusLottieButton getPreBtn() {
        return this.preBtn;
    }

    public View getPreButton() {
        return this.preBtn;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float getPressScale() {
        return getScaleX();
    }

    public /* bridge */ /* synthetic */ View getProgressiveBlurOverlay() {
        return super.getProgressiveBlurOverlay();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IHostAbility.LayoutConstraint getQSCapsuleLayoutConstraint() {
        return ((OplusSeparateQSManager) DependencyEx.sDependency.getDependency(OplusSeparateQSManager.class)).getLayoutConstraint(getContext(), DEFAULT_SIZE, this.isEditMode);
    }

    public View getSeekBarPlaceholder() {
        return this.seekBarPlaceholderView;
    }

    public final View getSeekBarPlaceholderView() {
        return this.seekBarPlaceholderView;
    }

    /* JADX INFO: renamed from: getText, reason: collision with other method in class */
    public final TextView m287getText() {
        return this.text;
    }

    public final TileDrawableWrapper getThemeBgDrawable() {
        return this.themeBgDrawable;
    }

    /* JADX INFO: renamed from: getTitle, reason: collision with other method in class */
    public final TextView m288getTitle() {
        return this.title;
    }

    public final ConstraintSet getTmpConstrainSet() {
        return this.tmpConstrainSet;
    }

    public MediaSession.Token getToken() {
        return this.token;
    }

    public final OplusQsMediaBackgroundDrawable getTransitionDrawable() {
        return this.transitionDrawable;
    }

    public final InterestingViewFlag getViewFlag() {
        return this.viewFlag;
    }

    public float getViewRadius() {
        return this.viewRadius;
    }

    public View getVolumeSeekBarPlaceholder() {
        return this.volumeSeekBarPlaceholderView;
    }

    public final View getVolumeSeekBarPlaceholderView() {
        return this.volumeSeekBarPlaceholderView;
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    public void initSeekBarParams(SeekBarViewModel.Factory factory) {
    }

    public void initView() {
    }

    public final boolean isBuffering() {
        MediaController mediaController = this.mediaController;
        if (mediaController == null) {
            return true;
        }
        PlaybackState playbackState = mediaController.getPlaybackState();
        return playbackState != null && playbackState.getState() == 6;
    }

    public final boolean isEditMode() {
        return this.isEditMode;
    }

    public final boolean isHistoryListEmpty() {
        return this.historyList.isEmpty() && OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getMediaSortKeySet().isEmpty();
    }

    public final boolean isMediaDataExpected(MediaData mediaData) {
        if (mediaData == null) {
            return false;
        }
        boolean zAreEqual = Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE);
        int i = this.expectPlayState;
        if (!zAreEqual) {
            return i == 2;
        }
        if (i != 3) {
            String packageName = mediaData.getPackageName();
            MediaData mediaData2 = this.themeMediaData;
            if (Intrinsics.areEqual(packageName, mediaData2 != null ? mediaData2.getPackageName() : null)) {
                return false;
            }
        }
        return true;
    }

    public boolean isMediaLoading(View view) {
        if (view instanceof OplusDotProgressBar) {
            return ((OplusDotProgressBar) view).isAnimationRunning();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.ViewGroup*/.onAttachedToWindow();
        this.musicRouteChange.attach();
        getBg().setBackground(this.transitionDrawable);
        if (ViewTagUtils.getSepQSViewTag(this)) {
            BaseQSResPool.globalThemeApplied();
            updateBgVisibility();
            onDrawableUpdate(false);
            onDrawableUpdate(true);
        } else {
            onDrawableUpdate(true);
        }
        onOutlineUpdate();
        if (this.isMiddleOrHighAnimLevel) {
            COUIMaterialStateManager.INSTANCE.getClass();
            this.enableStrokeLight = COUIMaterialStateManager.MATERIAL_STROKE_ENABLE;
            NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(this.lightAvailabilityListener);
            onSpotLightEnableChanged();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.view.ViewGroup*/.onConfigurationChanged(configuration);
        updateResource$2();
        setOutlineProvider();
        bindOrResetMediaData("onConfigurationChanged", false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] onCreateDrawableState(int i) {
        int[] drawableState = this.viewFlag.getDrawableState();
        if (!this.viewFlag.setDragged(this.isBeingDragged) && drawableState != null) {
            return drawableState;
        }
        int[] iArrOnCreateDrawableState = super/*android.view.ViewGroup*/.onCreateDrawableState(i + 1);
        if (this.isBeingDragged) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, TileViewFlag.Dragged.getFlag());
        }
        this.viewFlag.setDrawableState(iArrOnCreateDrawableState);
        iArrOnCreateDrawableState.getClass();
        return iArrOnCreateDrawableState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.view.ViewGroup*/.onDetachedFromWindow();
        this.pendingMediaData = null;
        this.handler.removeCallbacks(this.loadingRunnable);
        this.handler.removeCallbacks(this.stopLoadingRunnable);
        this.musicRouteChange.detach();
        this.handler.removeCallbacks(this.refreshPhotoCoverRunnable);
        this.handler.removeCallbacks(this.refreshActionButtonRunnable);
        if (this.isMiddleOrHighAnimLevel) {
            NotificationSpotLightEffectManager.removeOnSpotLightAvailabilityChangedListener(this.lightAvailabilityListener);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDrawableUpdate(boolean z) {
        if (!ViewTagUtils.getSepQSViewTag(this)) {
            this.bgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) StdQSResPool.mediaPanelDrawable.getValue(), getBg(), false, 6));
        } else if (z) {
            this.themeBgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getMediaPanelThemeDrawable().getValue(), getBg(), false, 4));
        } else {
            this.bgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getMediaPanelDrawable().getValue(), getBg(), false, 6));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.view.ViewGroup*/.onFinishInflate();
        setBg(requireViewById(2131362254));
        this.coverImg = requireViewById(2131364329);
        setOutlineProvider();
        ImageButton imageButton = (ImageButton) requireViewById(2131364338);
        imageButton.getClass();
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.mediaOutputBtnColorProperty = new ImageViewColorFilterTarget(imageButton, mode);
        this.mediaOutputBtn = imageButton;
        TextView textView = (TextView) requireViewById(2131364373);
        textView.getClass();
        this.titleColorProperty = new TextViewColorTarget(textView);
        this.title = textView;
        TextView textView2 = (TextView) requireViewById(2131364372);
        textView2.getClass();
        this.textColorProperty = new TextViewColorTarget(textView2);
        this.text = textView2;
        TextView textView3 = (TextView) requireViewById(2131364368);
        textView3.getClass();
        this.defaultTipColorProperty = new TextViewColorTarget(textView3);
        this.defaultTip = textView3;
        OplusLottieButton oplusLottieButton = (OplusLottieButton) requireViewById(2131364344);
        this.preBtnColorProperty = new ImageViewColorFilterTarget(oplusLottieButton.getImageView(), mode);
        this.preBtn = oplusLottieButton;
        OplusQsPlayPauseButton oplusQsPlayPauseButton = (OplusQsPlayPauseButton) requireViewById(2131364343);
        this.playOrPauseBtnColorProperty = new ImageViewColorFilterTarget(oplusQsPlayPauseButton.getImageView(), mode);
        this.playOrPauseBtn = oplusQsPlayPauseButton;
        this.dotLoading = (OplusDotProgressBar) requireViewById(2131364313);
        OplusLottieButton oplusLottieButton2 = (OplusLottieButton) requireViewById(2131364342);
        this.nextBtnColorProperty = new ImageViewColorFilterTarget(oplusLottieButton2.getImageView(), mode);
        this.nextBtn = oplusLottieButton2;
        this.blurOverlayPlaceholderView = requireViewById(2131364323);
        this.volumeSeekBarPlaceholderView = requireViewById(2131364376);
        this.seekBarPlaceholderView = requireViewById(2131364327);
        this.couiSeekBarView = requireViewById(2131364326);
        this.favoritePlaceholderView = requireViewById(2131364331);
        this.lyricPlaceholderView = requireViewById(2131364334);
        this.mediaOutputPlaceholderView = requireViewById(2131364337);
        this.mediaPanelLayout = requireViewById(2131364399);
        initView();
        updateResource$2();
        if (QsColorUtil.isIconNeedUseLightColor(((ViewGroup) this).mContext, this.isEditMode)) {
            updateColor();
        }
        setOnTouchListener(new PressFeedbackHelper.QsTilePressFeedback() { // from class: com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView.onFinishInflate.8
            @Override // com.oplus.systemui.qs.base.tile.PressFeedbackHelper.QsTilePressFeedback, android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (OplusQsBaseMediaPanelView.this.isEditMode) {
                    return false;
                }
                return super.onTouch(view, motionEvent);
            }
        });
        setMediaOutputView();
        Handler handler = this.bgHandler;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$1 = new OplusQsBaseMediaPanelView$loadingRunnable$1(3);
        oplusQsBaseMediaPanelView$loadingRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusQsBaseMediaPanelView$loadingRunnable$1);
    }

    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size != this.lastMeasureWidth || size2 != this.lastMeasureHeight) {
            this.lastMeasureWidth = size;
            this.lastMeasureHeight = size2;
            if (((Boolean) ((OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class)).getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                updateCapsuleLayout();
            }
        }
        super/*androidx.constraintlayout.widget.ConstraintLayout*/.onMeasure(i, i2);
    }

    public final void onMediaPlayerRemoveByPackage() {
        Handler handler = this.bgHandler;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$1 = new OplusQsBaseMediaPanelView$loadingRunnable$1(5);
        oplusQsBaseMediaPanelView$loadingRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusQsBaseMediaPanelView$loadingRunnable$1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onOutlineUpdate() {
        this.transitionDrawable.setPathProvider((CornerOutlineProvider) (ViewTagUtils.getSepQSViewTag(this) ? SepQSResPool.INSTANCE.getMediaPanelOutline() : StdQSResPool.INSTANCE.getMediaPanelOutline()).getValue());
        this.transitionDrawable.setCornerParams(this.transitionDrawable.getPathProvider().getCornerRadius(this.transitionDrawable), this.transitionDrawable.getPathProvider().getCornerWeight(this.transitionDrawable));
    }

    public final void onShadeExpanded(float f) {
        OplusLottieButton oplusLottieButton = this.preBtn;
        if (oplusLottieButton != null && oplusLottieButton.qsState != f) {
            oplusLottieButton.qsState = f;
            oplusLottieButton.updateBackground();
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
        if (oplusQsPlayPauseButton != null && oplusQsPlayPauseButton.qsState != f) {
            oplusQsPlayPauseButton.qsState = f;
            oplusQsPlayPauseButton.updateBackground();
        }
        OplusLottieButton oplusLottieButton2 = this.nextBtn;
        if (oplusLottieButton2 == null || oplusLottieButton2.qsState == f) {
            return;
        }
        oplusLottieButton2.qsState = f;
        oplusLottieButton2.updateBackground();
    }

    public final void onSpotLightEnableChanged() {
        boolean z = NotificationSpotLightEffectManager.spotLightEnabled;
        OplusNotificationLogger.logMediaPanel(TAG, "onSpotLightEnableChanged spotLightEnable=" + z);
        OplusLottieButton oplusLottieButton = this.preBtn;
        if (oplusLottieButton != null && oplusLottieButton.spotLightEnable != z) {
            oplusLottieButton.spotLightEnable = z;
            oplusLottieButton.updateBackground();
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
        if (oplusQsPlayPauseButton != null && oplusQsPlayPauseButton.spotLightEnable != z) {
            oplusQsPlayPauseButton.spotLightEnable = z;
            oplusQsPlayPauseButton.updateBackground();
        }
        OplusLottieButton oplusLottieButton2 = this.nextBtn;
        if (oplusLottieButton2 == null || oplusLottieButton2.spotLightEnable == z) {
            return;
        }
        oplusLottieButton2.spotLightEnable = z;
        oplusLottieButton2.updateBackground();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onVisibilityAggregated(boolean z) {
        super/*android.view.ViewGroup*/.onVisibilityAggregated(z);
        boolean z2 = this.isViewVisible;
        if (z2 != z) {
            OplusNotificationLogger.logMediaPanel(TAG, "onVisibilityAggregated, " + z2 + "->" + z);
            this.isViewVisible = z;
            if (z) {
                String strM = KeyguardLogger$.ExternalSyntheticOutline0.m("flush pending for visible pendingMediaData is not null =", this.pendingMediaData == null);
                MediaData mediaData = this.pendingMediaData;
                if (mediaData != null) {
                    bindMediaDataInner(mediaData, strM);
                }
            }
        }
    }

    public final void queryHistoryMediaData() {
        if (TextUtils.isEmpty(this.superPowerListString)) {
            updateSuperPowerList();
        }
        this.historyList = OplusQsMediaUtil.getHistoryPlayList(this.superPowerListString);
    }

    public final void refreshActionButton() {
        OplusNotificationLogger.logMediaPanel(TAG, "refreshActionButton avoidRefresh=" + this.avoidRefresh);
        if (this.avoidRefresh) {
            this.avoidRefresh = false;
            MediaData mediaData = this.pendingRefreshMediaData;
            if (mediaData != null) {
                bindMediaAction(mediaData);
                setBtnColor();
            } else {
                bindOrResetMediaData("refreshActionButton pendingRefreshMediaData null", true);
            }
            this.pendingRefreshMediaData = null;
        }
    }

    public void removeObserver$1() {
    }

    public final void resetButtonEnableState(String str) {
        OplusNotificationLogger.logMediaPanel(TAG, "resetButtonEnableState reason=" + str);
        OplusQsMediaItemViewCache.INSTANCE.setShowMedia(false);
        this.launchingPendingData = null;
        updateMediaOutputBtn(((Number) OplusQsVolumeIconView.Companion.getMusicStreamRoute().get()).intValue());
        OplusLottieButton oplusLottieButton = this.preBtn;
        if (oplusLottieButton != null) {
            oplusLottieButton.setImageResource(getPreBtnRes());
        }
        OplusLottieButton oplusLottieButton2 = this.preBtn;
        if (oplusLottieButton2 != null) {
            oplusLottieButton2.setLottieRes(2131886228);
        }
        OplusLottieButton oplusLottieButton3 = this.preBtn;
        if (oplusLottieButton3 != null) {
            oplusLottieButton3.setEnabled(false);
        }
        OplusLottieButton oplusLottieButton4 = this.preBtn;
        if (oplusLottieButton4 != null) {
            oplusLottieButton4.setOnClickListener(null);
        }
        OplusLottieButton oplusLottieButton5 = this.nextBtn;
        if (oplusLottieButton5 != null) {
            oplusLottieButton5.setImageResource(getNextBtnRes());
        }
        OplusLottieButton oplusLottieButton6 = this.nextBtn;
        if (oplusLottieButton6 != null) {
            oplusLottieButton6.setLottieRes(2131886227);
        }
        OplusLottieButton oplusLottieButton7 = this.nextBtn;
        if (oplusLottieButton7 != null) {
            oplusLottieButton7.setEnabled(false);
        }
        OplusLottieButton oplusLottieButton8 = this.nextBtn;
        if (oplusLottieButton8 != null) {
            oplusLottieButton8.setOnClickListener(null);
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
        if (oplusQsPlayPauseButton != null) {
            oplusQsPlayPauseButton.setPlaybackState(2);
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton2 = this.playOrPauseBtn;
        int i = 1;
        if (oplusQsPlayPauseButton2 != null) {
            oplusQsPlayPauseButton2.setEnabled(!this.historyList.isEmpty());
        }
        TextView textView = this.text;
        if (textView != null) {
            textView.setVisibility(4);
        }
        TextView textView2 = this.title;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
        TextView textView3 = this.defaultTip;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        this.handler.removeCallbacks(this.refreshPhotoCoverRunnable);
        resetCoverImg();
        this.pendingRefreshMediaData = null;
        this.themeMediaData = null;
        this.avoidRefresh = false;
        ImageButton imageButton = this.mediaOutputBtn;
        if (imageButton != null) {
            OplusQsBaseMediaPanelView$handleDefaultClickListener$1 oplusQsBaseMediaPanelView$handleDefaultClickListener$1 = new OplusQsBaseMediaPanelView$handleDefaultClickListener$1();
            oplusQsBaseMediaPanelView$handleDefaultClickListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            imageButton.setOnClickListener(oplusQsBaseMediaPanelView$handleDefaultClickListener$1);
        }
        if (this.historyList.isEmpty()) {
            OplusNotificationLogger.logMediaPanel(TAG, "history list is empty");
        } else {
            MediaData mediaData = this.historyList.get(0);
            bindCoverImg(mediaData);
            OplusQsPlayPauseButton oplusQsPlayPauseButton3 = this.playOrPauseBtn;
            if (oplusQsPlayPauseButton3 != null) {
                OplusQsBaseMediaPanelView$bindDevice$1 oplusQsBaseMediaPanelView$bindDevice$1 = new OplusQsBaseMediaPanelView$bindDevice$1(i);
                oplusQsBaseMediaPanelView$bindDevice$1.this$0 = this;
                oplusQsBaseMediaPanelView$bindDevice$1.$data = mediaData;
                VarHandle.storeStoreFence();
                oplusQsPlayPauseButton3.setOnClickListener(oplusQsBaseMediaPanelView$bindDevice$1);
            }
        }
        editModeChange(this.isEditMode);
        updateColor();
        setMediaData(null);
        this.primaryColor80 = 0;
        updateMultiLightForCurrentTrack(null);
    }

    public final void resetCoverImg() {
        AppCompatImageView appCompatImageView = this.coverImg;
        if (appCompatImageView != null) {
            appCompatImageView.setImageDrawable((Drawable) null);
        }
        AppCompatImageView appCompatImageView2 = this.coverImg;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setImageResource(getCoverImgRes());
        }
        this.defaultCover = true;
    }

    public final void resetCoverImgCornerRadius() {
        setOutlineProvider();
    }

    public final void resetView() {
        MediaPanelData mediaPanelData = this.mediaPanelData;
        TextView textView = this.defaultTip;
        if (mediaPanelData == null) {
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.title;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
            TextView textView3 = this.text;
            if (textView3 != null) {
                textView3.setVisibility(4);
                return;
            }
            return;
        }
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView4 = this.title;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
        TextView textView5 = this.text;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[isBeingDragged] ", "OplusQsBaseMediaPanelView-->", "SystemUi--QuickSettings", z);
            }
            this.isBeingDragged = z;
            refreshDrawableState();
        }
    }

    public final void setBg(View view) {
        this.bg = view;
    }

    public final void setBgHandler(Handler handler) {
        this.bgHandler = handler;
    }

    public final void setBlurOverlayPlaceholderView(View view) {
        this.blurOverlayPlaceholderView = view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBtnColor() {
        int[] iArr;
        int[] iArr2;
        int color;
        int color2;
        int color3;
        boolean zIsWallpaperBlurDisable = ScrimUtil.isWallpaperBlurDisable();
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(getContext());
        int[][] iArr3 = {new int[]{R.attr.state_enabled}, new int[]{-16842910}};
        int attrColor = OplusContextUtil.getAttrColor(getContext(), 2130969314);
        int attrColor2 = OplusContextUtil.getAttrColor(getContext(), 2130969282);
        int color4 = getContext().getColor(2131103287);
        int color5 = getContext().getColor(2131103286);
        if (zIsWallpaperBlurDisable) {
            if (!zIsNightMode) {
                color4 = QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR;
            }
            if (zIsNightMode) {
                attrColor2 = color5;
            }
            iArr2 = new int[]{color4, attrColor2};
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "setBtnColor isNeedSeparateDarkThemeColor", null, 8);
            iArr2 = new int[]{color4, color5};
        } else {
            if (QsColorUtil.isGlobalThemeApplied(getContext())) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "setBtnColor isGlobalThemeApplied", null, 8);
                if (this.isEditMode) {
                    color = getContext().getColor(2131103747);
                    color2 = getContext().getColor(2131103750);
                } else {
                    color = getContext().getColor(2131103746);
                    color2 = getContext().getColor(2131103749);
                }
                iArr = new int[]{color, color2};
            } else if (QsColorUtil.isMediaIconNeedUseLightColor(getContext(), this.isEditMode)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "setBtnColor isMediaIconNeedUseLightColor", null, 8);
                iArr2 = new int[]{QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR, attrColor2};
            } else {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "setBtnColor default", null, 8);
                iArr = new int[]{attrColor, attrColor2};
            }
            iArr2 = iArr;
        }
        ColorStateList colorStateList = new ColorStateList(iArr3, iArr2);
        ImageButton imageButton = this.mediaOutputBtn;
        int i = (imageButton == null || !imageButton.isEnabled()) ? iArr2[1] : iArr2[0];
        ImageViewColorFilterTarget imageViewColorFilterTarget = this.mediaOutputBtnColorProperty;
        if (imageViewColorFilterTarget != null) {
            imageViewColorFilterTarget.set(i);
        }
        OplusLottieButton oplusLottieButton = this.preBtn;
        int i2 = (oplusLottieButton == null || !oplusLottieButton.isEnabled()) ? iArr2[1] : iArr2[0];
        ImageViewColorFilterTarget imageViewColorFilterTarget2 = this.preBtnColorProperty;
        if (imageViewColorFilterTarget2 != null) {
            imageViewColorFilterTarget2.set(i2);
        }
        OplusLottieButton oplusLottieButton2 = this.preBtn;
        if (oplusLottieButton2 != null) {
            oplusLottieButton2.setLottieColorFilter(i2);
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
        int i3 = (oplusQsPlayPauseButton == null || !oplusQsPlayPauseButton.isEnabled()) ? iArr2[1] : iArr2[0];
        ImageViewColorFilterTarget imageViewColorFilterTarget3 = this.playOrPauseBtnColorProperty;
        if (imageViewColorFilterTarget3 != null) {
            imageViewColorFilterTarget3.set(i3);
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton2 = this.playOrPauseBtn;
        if (oplusQsPlayPauseButton2 != null) {
            oplusQsPlayPauseButton2.setLottieColorFilter(i3);
        }
        OplusLottieButton oplusLottieButton3 = this.nextBtn;
        int i4 = (oplusLottieButton3 == null || !oplusLottieButton3.isEnabled()) ? iArr2[1] : iArr2[0];
        ImageViewColorFilterTarget imageViewColorFilterTarget4 = this.nextBtnColorProperty;
        if (imageViewColorFilterTarget4 != null) {
            imageViewColorFilterTarget4.set(i4);
        }
        OplusLottieButton oplusLottieButton4 = this.nextBtn;
        if (oplusLottieButton4 != null) {
            oplusLottieButton4.setLottieColorFilter(i4);
        }
        if (zIsWallpaperBlurDisable) {
            color3 = zIsNightMode ? getContext().getColor(2131100939) : QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR;
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode)) {
            color3 = getContext().getColor(2131100939);
        } else {
            color3 = QsColorUtil.isIconNeedUseLightColor(getContext(), this.isEditMode) ? QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR : getContext().getColor(2131100936);
        }
        OplusDotProgressBar oplusDotProgressBar = this.dotLoading;
        if (oplusDotProgressBar != null) {
            oplusDotProgressBar.setDotBackground(color3);
        }
        setSeekBarColor(colorStateList);
    }

    public final void setCouiSeekBarView(View view) {
        this.couiSeekBarView = view;
    }

    public final void setCoverImg(AppCompatImageView appCompatImageView) {
        this.coverImg = appCompatImageView;
    }

    public final void setDefaultTip(TextView textView) {
        this.defaultTip = textView;
    }

    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[isEditMode] ", "OplusQsBaseMediaPanelView-->", "SystemUi--QuickSettings", z);
            }
            this.isEditMode = z;
            OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
            if (oplusQsPlayPauseButton != null) {
                oplusQsPlayPauseButton.setEditMode(z);
            }
            updateBgVisibility();
            if (BaseQSResPool.globalThemeApplied()) {
                updateColor();
                bindMediaAction(this.themeMediaData);
            }
            editModeChange(z);
            ImageButton imageButton = this.mediaOutputBtn;
            if (imageButton != null) {
                updateTalkBackEnable(imageButton, z);
            }
            TextView textView = this.text;
            if (textView != null) {
                updateTalkBackEnable(textView, z);
            }
            TextView textView2 = this.title;
            if (textView2 != null) {
                updateTalkBackEnable(textView2, z);
            }
            TextView textView3 = this.defaultTip;
            if (textView3 != null) {
                updateTalkBackEnable(textView3, z);
            }
            OplusDotProgressBar oplusDotProgressBar = this.dotLoading;
            if (oplusDotProgressBar != null) {
                updateTalkBackEnable(oplusDotProgressBar, z);
            }
        }
    }

    public final void setFavoritePlaceholderView(View view) {
        this.favoritePlaceholderView = view;
    }

    public void setLaunchingAnimating(boolean z) {
        MediaData mediaData;
        if (z != this.isLaunchingAnimating) {
            this.isLaunchingAnimating = z;
            if (z || (mediaData = this.launchingPendingData) == null) {
                return;
            }
            bindMediaData(mediaData, "isLaunchingAnimating false");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLayerType(int i, Paint paint) {
        MediaData mediaData = this.themeMediaData;
        if ((mediaData != null ? Intrinsics.areEqual(mediaData.isPlaying(), Boolean.TRUE) : false) && i == 2) {
            return;
        }
        if (!BaseQSResPool.supportMixColor()) {
            super/*android.view.ViewGroup*/.setLayerType(i, paint);
            return;
        }
        ConstraintLayout constraintLayout = this.mediaPanelLayout;
        if (constraintLayout != null) {
            constraintLayout.setLayerType(i, paint);
        }
    }

    public final void setLyricPlaceholderView(View view) {
        this.lyricPlaceholderView = view;
    }

    public final void setMediaController(MediaController mediaController) {
        this.mediaController = mediaController;
    }

    public void setMediaData(MediaPanelData mediaPanelData) {
        if (mediaPanelData == null) {
            this.mediaPanelData = null;
        }
        bindTitleAndText(mediaPanelData);
    }

    public final boolean setMediaLayout(int i, int i2, int i3) {
        this.tmpConstrainSet.clone(this.mediaPanelLayout);
        AppCompatImageView appCompatImageView = this.coverImg;
        boolean z = false;
        if (appCompatImageView != null) {
            ConstraintLayout.LayoutParams layoutParams = appCompatImageView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
            if (layoutParams2 == null || ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin != i) {
                this.tmpConstrainSet.constrainWidth(appCompatImageView.getId(), i2);
                this.tmpConstrainSet.constrainHeight(appCompatImageView.getId(), i2);
                this.tmpConstrainSet.connect(appCompatImageView.getId(), 3, 0, 3, i);
                z = true;
            }
            ConstraintLayout.LayoutParams layoutParams3 = appCompatImageView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 == null || layoutParams4.getMarginStart() != i) {
                this.tmpConstrainSet.connect(appCompatImageView.getId(), 6, 0, 6, i);
                z = true;
            }
        }
        ImageButton imageButton = this.mediaOutputBtn;
        if (imageButton == null) {
            return z;
        }
        ConstraintLayout.LayoutParams layoutParams5 = imageButton.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
        if (layoutParams6 == null || ((ViewGroup.MarginLayoutParams) layoutParams6).topMargin != i) {
            this.tmpConstrainSet.constrainWidth(imageButton.getId(), i3);
            this.tmpConstrainSet.constrainHeight(imageButton.getId(), i3);
            this.tmpConstrainSet.connect(imageButton.getId(), 3, 0, 3, i);
            z = true;
        }
        ConstraintLayout.LayoutParams layoutParams7 = imageButton.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams8 = layoutParams7 instanceof ConstraintLayout.LayoutParams ? layoutParams7 : null;
        if (layoutParams8 != null && layoutParams8.getMarginEnd() == i) {
            return z;
        }
        this.tmpConstrainSet.connect(imageButton.getId(), 7, 0, 7, i);
        return true;
    }

    public final void setMediaOutputBtn(ImageButton imageButton) {
        this.mediaOutputBtn = imageButton;
    }

    public final void setMediaOutputDialogFactory(MediaOutputDialogManager mediaOutputDialogManager) {
        this.mediaOutPutDialogFactory = mediaOutputDialogManager;
    }

    public final void setMediaOutputPlaceholderView(View view) {
        this.mediaOutputPlaceholderView = view;
    }

    public final void setMediaOutputView() {
        OplusNotificationLogger.logMediaPanel(TAG, "current userid is : " + OsBinderCacheUtils.getCurrentUserId());
        int currentUserId = OsBinderCacheUtils.getCurrentUserId();
        ImageButton imageButton = this.mediaOutputBtn;
        if (currentUserId != 0) {
            if (imageButton != null) {
                imageButton.setVisibility(8);
            }
        } else if (imageButton != null) {
            imageButton.setVisibility(0);
        }
    }

    public final void setMediaPanelData(MediaPanelData mediaPanelData) {
        this.mediaPanelData = mediaPanelData;
    }

    public final void setMediaPanelLayout(ConstraintLayout constraintLayout) {
        this.mediaPanelLayout = constraintLayout;
    }

    public final void setMediaTextSize(int i, int i2) {
        TextView textView = this.defaultTip;
        if (textView != null && textView != null) {
            textView.setTextSize(0, i);
        }
        TextView textView2 = this.title;
        if (textView2 != null && textView2 != null) {
            textView2.setTextSize(0, i);
        }
        TextView textView3 = this.text;
        if (textView3 == null || textView3 == null) {
            return;
        }
        textView3.setTextSize(0, i2);
    }

    public final void setNextBtn(OplusLottieButton oplusLottieButton) {
        this.nextBtn = oplusLottieButton;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setOutlineProvider() {
        if (this.coverImg == null) {
            return;
        }
        float dimension = getContext().getResources().getDimension(2131171047);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(((ViewGroup) this).mContext, dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        this.coverCornerRadius = dimension;
        this.coverCornerWeight = roundParams != null ? roundParams.getWeight() : 3.0f;
        ImageView imageView = this.coverImg;
        if (imageView == null) {
            imageView = null;
        }
        if (imageView == null) {
            return;
        }
        imageView.setElevation(0.0f);
        imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView$applyCoverOutlineProvider$1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (view.getWidth() <= 0 || view.getHeight() <= 0) {
                    outline.setEmpty();
                    return;
                }
                if (this.this$0.itemViewOplusOutlineAdapter == null || !Intrinsics.areEqual(this.this$0.itemViewOutlineLast, outline)) {
                    this.this$0.itemViewOutlineLast = outline;
                    this.this$0.itemViewOplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
                }
                OplusOutlineAdapter oplusOutlineAdapter = this.this$0.itemViewOplusOutlineAdapter;
                if (oplusOutlineAdapter != null) {
                    int width = view.getWidth();
                    int height = view.getHeight();
                    OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
                    oplusOutlineAdapter.setSmoothRoundRect(0, 0, width, height, oplusQsBaseMediaPanelView.coverCornerRadius, oplusQsBaseMediaPanelView.coverCornerWeight);
                }
            }
        });
        imageView.setClipToOutline(true);
    }

    public final void setPlayOrPauseBtn(OplusQsPlayPauseButton oplusQsPlayPauseButton) {
        this.playOrPauseBtn = oplusQsPlayPauseButton;
    }

    public final void setPreBtn(OplusLottieButton oplusLottieButton) {
        this.preBtn = oplusLottieButton;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setPressed(boolean z) {
        if (this.isEditMode) {
            return;
        }
        super/*android.view.ViewGroup*/.setPressed(z);
    }

    public void setSeekBarColor(ColorStateList colorStateList) {
    }

    public final void setSeekBarPlaceholderView(View view) {
        this.seekBarPlaceholderView = view;
    }

    public final void setSemanticButton(OplusLottieButton oplusLottieButton, int i, MediaAction mediaAction, MediaData mediaData) {
        stopLoading();
        int i2 = 0;
        if (oplusLottieButton != null) {
            oplusLottieButton.setVisibility(0);
        }
        if (oplusLottieButton != null) {
            oplusLottieButton.setImageResource(i);
        }
        if (mediaAction == null) {
            if (oplusLottieButton != null) {
                oplusLottieButton.setContentDescription(null);
            }
            if (oplusLottieButton != null) {
                oplusLottieButton.setEnabled(false);
                return;
            }
            return;
        }
        if (oplusLottieButton != null) {
            oplusLottieButton.setContentDescription(mediaAction.getContentDescription());
        }
        if (Intrinsics.areEqual(oplusLottieButton, this.playOrPauseBtn)) {
            if (oplusLottieButton != null) {
                oplusLottieButton.setEnabled(true);
            }
        } else if (oplusLottieButton != null) {
            oplusLottieButton.setEnabled(mediaAction.getAction() != null && mediaAction.getMediaActionEx().isStandardAction);
        }
        if (oplusLottieButton != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i2);
            anonymousClass1.$imageButton = oplusLottieButton;
            anonymousClass1.this$0 = this;
            anonymousClass1.$data = mediaData;
            anonymousClass1.$mediaAction = mediaAction;
            VarHandle.storeStoreFence();
            oplusLottieButton.setOnClickListener(anonymousClass1);
        }
        editModeChange(this.isEditMode);
    }

    public final void setText(TextView textView) {
        this.text = textView;
    }

    public final void setTitle(TextView textView) {
        this.title = textView;
    }

    public final void setVolumeSeekBarPlaceholderView(View view) {
        this.volumeSeekBarPlaceholderView = view;
    }

    public final void stopLoading() {
        this.handler.removeCallbacks(this.loadingRunnable);
        this.handler.removeCallbacks(this.stopLoadingRunnable);
        this.handler.post(this.stopLoadingRunnable);
    }

    public final void unBindMediaData() {
        this.pendingMediaData = null;
        Handler handler = this.bgHandler;
        OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$1 = new OplusQsBaseMediaPanelView$loadingRunnable$1(9);
        oplusQsBaseMediaPanelView$loadingRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusQsBaseMediaPanelView$loadingRunnable$1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateBgVisibility() {
        if (ViewTagUtils.getSepQSViewTag(this)) {
            if (!BaseQSResPool.globalThemeApplied() || this.isEditMode) {
                this.transitionDrawable.setTransitionAlpha(255);
            } else {
                this.transitionDrawable.setTransitionAlpha(0);
            }
        }
    }

    public void updateCapsuleLayout() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateColor() {
        int color;
        int color2;
        AppCompatImageView appCompatImageView;
        AppCompatImageView appCompatImageView2;
        setBtnColor();
        boolean zIsWallpaperBlurDisable = ScrimUtil.isWallpaperBlurDisable();
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(getContext());
        if (zIsWallpaperBlurDisable) {
            color = !zIsNightMode ? getContext().getColor(2131100936) : getContext().getColor(2131103287);
            color2 = color;
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode)) {
            color = getContext().getColor(2131103287);
            color2 = getContext().getColor(2131103287);
        } else if (!QsColorUtil.isGlobalThemeApplied(getContext())) {
            color = getContext().getColor(2131100936);
            color2 = getContext().getColor(2131100936);
        } else if (this.isEditMode) {
            color = getContext().getColor(2131103727);
            color2 = getContext().getColor(2131103726);
        } else {
            color = getContext().getColor(2131103706);
            color2 = getContext().getColor(2131103706);
        }
        TextViewColorTarget textViewColorTarget = this.titleColorProperty;
        if (textViewColorTarget != null) {
            textViewColorTarget.set(color);
        }
        TextViewColorTarget textViewColorTarget2 = this.textColorProperty;
        if (textViewColorTarget2 != null) {
            textViewColorTarget2.set(color2);
        }
        TextViewColorTarget textViewColorTarget3 = this.defaultTipColorProperty;
        if (textViewColorTarget3 != null) {
            textViewColorTarget3.set(color2);
        }
        if (!this.defaultCover || (appCompatImageView = this.coverImg) == null || appCompatImageView.getVisibility() != 0 || (appCompatImageView2 = this.coverImg) == null) {
            return;
        }
        appCompatImageView2.setImageResource(getCoverImgRes());
    }

    public final void updateCoverImgCornerRadius(float f, float f2) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.coverCornerRadius = f;
        AppCompatImageView appCompatImageView = this.coverImg;
        if (appCompatImageView != null) {
            appCompatImageView.invalidateOutline();
        }
    }

    public /* bridge */ /* synthetic */ void updateFinishExpand(String str) {
        super.updateFinishExpand(str);
    }

    public final void updateMediaOutputBtn(int i) {
        if (this.inAudioSharing) {
            ImageButton imageButton = this.mediaOutputBtn;
            if (imageButton != null) {
                imageButton.setImageResource(2131237115);
            }
        } else if (i == 1) {
            ImageButton imageButton2 = this.mediaOutputBtn;
            if (imageButton2 != null) {
                imageButton2.setImageResource(getDeviceHeadphonesRes());
            }
        } else {
            ImageButton imageButton3 = this.mediaOutputBtn;
            if (i == 0) {
                if (imageButton3 != null) {
                    imageButton3.setImageResource(getDeviceWiredRes());
                }
            } else if (imageButton3 != null) {
                imageButton3.setImageResource(getDeviceSwitchRes());
            }
        }
        ImageButton imageButton4 = this.mediaOutputBtn;
        if (imageButton4 != null) {
            imageButton4.setEnabled(true);
        }
    }

    public final void updateMultiLightForCurrentTrack(MediaData mediaData) {
        if (this.isMiddleOrHighAnimLevel) {
            if (mediaData == null || mediaData.getArtwork() == null) {
                this.lastMultiLightTrackKey = null;
                OplusQsMediaBackgroundDrawable.clearLight$default(this.transitionDrawable);
                if (mediaData == null) {
                    OplusQsMediaCacheUtil.INSTANCE.clearCache();
                    return;
                }
                return;
            }
            Handler handler = this.bgHandler;
            OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1 oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1 = new OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1(1);
            oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1.$selectMediaData = mediaData;
            oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResource$2() {
        if (((Boolean) ((OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class)).getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
            return;
        }
        boolean z = false;
        boolean z2 = true;
        boolean z3 = OplusQSSmallSpaceController.Companion.isSmallSpace(((ViewGroup) this).mContext, false) && OplusQSDisplayModeUtils.isBigDisplayMode(((ViewGroup) this).mContext);
        QSFeatureOption.INSTANCE.getClass();
        int dimensionPixelSize = (z3 || (QSFeatureOption.isSupportVolumeSeekBar() && OplusQSDisplayModeUtils.isBigDisplayMode(((ViewGroup) this).mContext))) ? getResources().getDimensionPixelSize(2131171109) : getResources().getDimensionPixelSize(2131171108);
        this.tmpConstrainSet.clone(this.mediaPanelLayout);
        OplusLottieButton oplusLottieButton = this.preBtn;
        if (oplusLottieButton != null) {
            ConstraintLayout.LayoutParams layoutParams = oplusLottieButton.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
            if (layoutParams2 == null || ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin != dimensionPixelSize) {
                this.tmpConstrainSet.connect(oplusLottieButton.getId(), 4, 0, 4, dimensionPixelSize);
                z = true;
            }
        }
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.playOrPauseBtn;
        if (oplusQsPlayPauseButton != null) {
            ConstraintLayout.LayoutParams layoutParams3 = oplusQsPlayPauseButton.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 == null || ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin != dimensionPixelSize) {
                this.tmpConstrainSet.connect(oplusQsPlayPauseButton.getId(), 4, 0, 4, dimensionPixelSize);
                z = true;
            }
        }
        OplusLottieButton oplusLottieButton2 = this.nextBtn;
        if (oplusLottieButton2 != null) {
            ConstraintLayout.LayoutParams layoutParams5 = oplusLottieButton2.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
            if (layoutParams6 == null || ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin != dimensionPixelSize) {
                this.tmpConstrainSet.connect(oplusLottieButton2.getId(), 4, 0, 4, dimensionPixelSize);
                z = true;
            }
        }
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131171110);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(2131171117);
        AppCompatImageView appCompatImageView = this.coverImg;
        if (appCompatImageView != null) {
            ConstraintLayout.LayoutParams layoutParams7 = appCompatImageView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams8 = layoutParams7 instanceof ConstraintLayout.LayoutParams ? layoutParams7 : null;
            if (layoutParams8 == null || ((ViewGroup.MarginLayoutParams) layoutParams8).topMargin != dimensionPixelSize2) {
                this.tmpConstrainSet.constrainWidth(appCompatImageView.getId(), dimensionPixelSize3);
                this.tmpConstrainSet.constrainHeight(appCompatImageView.getId(), dimensionPixelSize3);
                this.tmpConstrainSet.connect(appCompatImageView.getId(), 3, 0, 3, dimensionPixelSize2);
                z = true;
            }
        }
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(2131171051);
        ImageButton imageButton = this.mediaOutputBtn;
        if (imageButton != null) {
            ConstraintLayout.LayoutParams layoutParams9 = imageButton.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams10 = layoutParams9 instanceof ConstraintLayout.LayoutParams ? layoutParams9 : null;
            if (layoutParams10 == null || ((ViewGroup.MarginLayoutParams) layoutParams10).topMargin != dimensionPixelSize4) {
                this.tmpConstrainSet.connect(imageButton.getId(), 3, 0, 3, dimensionPixelSize4);
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            this.tmpConstrainSet.applyTo(this.mediaPanelLayout);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateSuperPowerList() {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            Context context = ((ViewGroup) this).mContext;
            String stringForUser = Settings.System.getStringForUser(context != null ? context.getContentResolver() : null, "super_power_save_desktop_app_list", 0);
            if (stringForUser == null) {
                stringForUser = "";
            }
            this.superPowerListString = stringForUser;
        }
    }

    public View getCoverImg() {
        return this.coverImg;
    }

    public View getDefaultTip() {
        return this.defaultTip;
    }

    public View getMediaOutputBtn() {
        return this.mediaOutputPlaceholderView;
    }

    public View getText() {
        return this.text;
    }

    public View getTitle() {
        return this.title;
    }

    public OplusQsBaseMediaPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public OplusQsBaseMediaPanelView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public /* synthetic */ OplusQsBaseMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    public OplusQsBaseMediaPanelView(Context context) {
        this(context, null, 0, 14, 0);
    }
}
