package com.oplus.systemui.qs.media;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.models.player.MediaActionPriority;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.models.player.OplusMediaLyricData;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.coui.appcompat.seekbar.COUIIntentSeekBar;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.media.controls.ui.MediaActionBinderKt;
import com.oplus.systemui.media.controls.ui.OplusMediaActions;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.detail.OplusQSDetail;
import com.oplus.systemui.qs.detail.utils.QsDetailBackgroundUtils;
import com.oplus.systemui.qs.media.multilight.OplusMediaItemBackgroundDrawable;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.observer.PowerSavingModeObserver;
import com.oplus.util.OplusContextUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewPagerAdapter extends RecyclerView.Adapter {
    private static final String TAG = "OplusMediaViewPagerAdapter";
    public boolean coverClipDelegatedToMasterClip;
    public boolean enableStrokeLight;
    public boolean inAudioSharing;
    public final boolean isMiddleOrHighAnimLevel;
    public boolean isShowMediaOutput;
    public final boolean isSupportLightEffect;
    private final Runnable lightAvailabilityListener;
    private IClickListener mClickListener;
    private final Context mContext;
    public boolean mIsDestroyed;
    private List<MediaData> mMediaDataList;
    private List<OplusMediaViewHolder> mViewHolderList = new ArrayList();
    public final OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState;
    public boolean photoClipForLaunchAnim;
    public int primaryColor80;
    private final CoroutineScope scope;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;
    private final Lazy specialPackageNames$delegate;
    public boolean viewPagerScrolling;
    public static final Companion Companion = new Companion();
    private static final List<Double> TINE_LIST = CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(80.0d));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getPhotoCoverSmoothRadius(Context context) {
            float dimension = context.getResources().getDimension(2131170171);
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, dimension);
            return roundParams != null ? roundParams.getRadius() : dimension;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IClickListener {
        void onMediaOutputClick(View view);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusMediaViewHolder extends RecyclerView.ViewHolder {
        public static final /* synthetic */ int $r8$clinit = 0;
        private SeekBarViewModel.AllowedProgressUpdateListener allowedProgressUpdateListener;
        private OplusOutlineAdapter appIconOplusOutlineAdapter;
        private Outline appIconOutlineLast;
        public float appIconOutlineRadius;
        public float appIconOutlineWeight;
        public float appIconSteadyOutlineRadius;
        public final float containerDesignCornerRadius;
        private final Lazy iconAlphaSpringAnimatorSets$delegate;
        private final Lazy iconAlphaSpringAnimatorSetsTwoToFive$delegate;
        private final Lazy iconHideAlphaSpringAnimatorSets$delegate;
        private final Lazy iconHideAlphaSpringAnimatorSetsTwoToFive$delegate;
        private final Lazy iconHideScaleSpringAnimatorSets$delegate;
        private final Lazy iconHideScaleSpringAnimatorSetsTwoToFive$delegate;
        private final Lazy iconScaleSpringAnimatorSets$delegate;
        private final Lazy iconScaleSpringAnimatorSetsTwoToFive$delegate;
        private ArrayList<ImageButton> imageButtonsFive;
        private ArrayList<ImageButton> imageButtonsTwo;
        public boolean isNewLayout;
        private final Lazy itemBackgroundDrawable$delegate;
        private OplusOutlineAdapter itemViewOplusOutlineAdapter;
        private Outline itemViewOutlineLast;
        public float itemViewOutlineRadius;
        public float itemViewOutlineWeight;
        private Icon lastIcon;
        public int lastSize;
        private OplusOutlineAdapter lightOverlayOplusOutlineAdapter;
        private Outline lightOverlayOutlineLast;
        public float lightOverlayOutlineRadius;
        public float lightOverlayOutlineWeight;
        private FrameLayout mActionPlayOrPauseFrameLayout;
        private AppCompatImageView mAppIcon;
        private Pair<? extends Drawable, ? extends Drawable> mAppIconPair;
        private final Handler mBgHandler;
        private ImageButton mBtnAction1;
        private ImageButton mBtnAction2;
        private View mContainerView;
        private MediaController mController;
        private TextView mCurrentTime;
        private TextView mDefaultTip;
        private OplusDotProgressBar mDotLoading;
        private Handler mHandler;
        private Job mIconJob;
        private FrameLayout mLightOverlay;
        private final Runnable mLoadingRunnable;
        private ImageButton mLocalFavoriteButton;
        private ImageButton mLocalLyricBtn;
        private ViewGroup mMediaCoverLayout;
        private ImageButton mMediaOutputBtn;
        private ViewGroup mMediaOutputBtnContainer;
        private MediaOutputDialogManager mMediaOutputDialogFactory;
        private TextView mMediaOutputText;
        private final NullableObservableField<Unit> mMusicRouteChange;
        private final Mutex mMutex;
        private ImageButton mNextBtn;
        private OplusDotProgressBar mOplusDotActionAnimation;
        private FrameLayout mOplusMediaPanelContainerAction4;
        public boolean mPendingProgress;
        private ProgressiveBlurOverlay mPhotoBlurOverlay;
        private FrameLayout mPhotoBlurOverlayParent;
        private ImageView mPhotoCover;
        private ImageButton mPlayOrPauseBtn;
        private ImageButton mPreBtn;
        private COUIIntentSeekBar mSeekBar;
        private OplusQsSeekBarObserver mSeekBarObserver;
        private RelativeLayout mSeekBarPatent;
        public long mSeekBarProgressBlockUntilMs;
        private SeekBarViewModel mSeekBarViewModel;
        private SeekBarViewModel.ScrubbingChangeListener mSeekScrubbingChangeListener;
        private final Runnable mStopLoadingRunnable;
        private TextView mText;
        private TextView mTitle;
        private MediaSession.Token mToken;
        private TextView mTotalTime;
        private ImageView mVolumeMax;
        private ImageView mVolumeMin;
        private SeekBar mVolumeSeekBar;
        private VolumeSeekBarViewModel mVolumeSeekBarViewModel;
        private ConstraintLayout mediaButtonConstraintLayout;
        private List<View> mediaChildViews;
        private MediaData mediaData;
        public boolean needAnimation;
        private Drawable overLayStrokeBackground;
        public float photoCoverLaunchRadius;
        public float photoCoverLaunchWeight;
        private OplusOutlineAdapter photoCoverOplusOutlineAdapter;
        private Outline photoCoverOutlineLast;
        private final Runnable refreshPhotoCoverRunnable;
        private ViewBlurProxy viewBlurProxy;
        private RelativeLayout volumeSeekLayout;

        public static ScaleSpringAnimationHelper $r8$lambda$1SqqqkyzwfoD1F7XXNKfEGdJHYU(OplusMediaViewHolder oplusMediaViewHolder) {
            return new ScaleSpringAnimationHelper(oplusMediaViewHolder.imageButtonsFive, 0.0f, 0.2f);
        }

        public static Unit $r8$lambda$52WntmfoeczIlJdd0D49r2c3Qv8(OplusMediaViewHolder oplusMediaViewHolder) {
            ArrayList<ImageButton> arrayList = oplusMediaViewHolder.imageButtonsFive;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                ImageButton imageButton = arrayList.get(i);
                i++;
                ImageButton imageButton2 = imageButton;
                imageButton2.setAlpha(1.0f);
                imageButton2.setScaleX(1.0f);
                imageButton2.setScaleY(1.0f);
            }
            ImageButton imageButton3 = oplusMediaViewHolder.mLocalFavoriteButton;
            if (imageButton3 != null) {
                imageButton3.setVisibility(8);
            }
            ImageButton imageButton4 = oplusMediaViewHolder.mLocalLyricBtn;
            if (imageButton4 != null) {
                imageButton4.setVisibility(8);
            }
            ImageButton imageButton5 = oplusMediaViewHolder.mPreBtn;
            if (imageButton5 != null) {
                imageButton5.setVisibility(8);
            }
            ImageButton imageButton6 = oplusMediaViewHolder.mNextBtn;
            if (imageButton6 != null) {
                imageButton6.setVisibility(8);
            }
            FrameLayout frameLayout = oplusMediaViewHolder.mActionPlayOrPauseFrameLayout;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            return Unit.INSTANCE;
        }

        public static AlphaSpringAnimatorHelper $r8$lambda$8f66dkJW3c4Z2afNUZy4jmegiMo(OplusMediaViewHolder oplusMediaViewHolder) {
            ArrayList<ImageButton> arrayList = oplusMediaViewHolder.imageButtonsFive;
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(2);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0.f$0 = oplusMediaViewHolder;
            VarHandle.storeStoreFence();
            return new AlphaSpringAnimatorHelper(arrayList, 0.0f, 0.2f, oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0);
        }

        public static Unit $r8$lambda$J4ldsIYkvpUO778VDWYS503XgFE(OplusMediaViewHolder oplusMediaViewHolder) {
            ArrayList<ImageButton> arrayList = oplusMediaViewHolder.imageButtonsTwo;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                ImageButton imageButton = arrayList.get(i);
                i++;
                ImageButton imageButton2 = imageButton;
                imageButton2.setAlpha(1.0f);
                imageButton2.setScaleX(1.0f);
                imageButton2.setScaleY(1.0f);
            }
            ImageButton imageButton3 = oplusMediaViewHolder.mBtnAction2;
            if (imageButton3 != null) {
                imageButton3.setVisibility(8);
            }
            FrameLayout frameLayout = oplusMediaViewHolder.mOplusMediaPanelContainerAction4;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            return Unit.INSTANCE;
        }

        public static ScaleSpringAnimationHelper $r8$lambda$Jw3zyl4SsuMiixI4c5HO5ySb4pU(OplusMediaViewHolder oplusMediaViewHolder) {
            return new ScaleSpringAnimationHelper(oplusMediaViewHolder.imageButtonsTwo, 0.0f, 0.2f);
        }

        public static AlphaSpringAnimatorHelper $r8$lambda$WUJ_iP2_E99j58JwDzsLnmjXCvs(OplusMediaViewHolder oplusMediaViewHolder) {
            return new AlphaSpringAnimatorHelper(oplusMediaViewHolder.imageButtonsTwo, 0.0f, 0.35f, new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda10(0));
        }

        public static AlphaSpringAnimatorHelper $r8$lambda$aScBE25vwCSXW62qK8kQmvc_kT8(OplusMediaViewHolder oplusMediaViewHolder) {
            ArrayList<ImageButton> arrayList = oplusMediaViewHolder.imageButtonsTwo;
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(1);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0.f$0 = oplusMediaViewHolder;
            VarHandle.storeStoreFence();
            return new AlphaSpringAnimatorHelper(arrayList, 0.0f, 0.2f, oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0);
        }

        public static ScaleSpringAnimationHelper $r8$lambda$isBO3hIHhpDmNnZaF4sNrQ8aI5g(OplusMediaViewHolder oplusMediaViewHolder) {
            return new ScaleSpringAnimationHelper(oplusMediaViewHolder.imageButtonsTwo, 0.0f, 0.35f);
        }

        public static AlphaSpringAnimatorHelper $r8$lambda$o6GMZ3eo8_Dvzy4LH5sEdwXuptg(OplusMediaViewHolder oplusMediaViewHolder) {
            return new AlphaSpringAnimatorHelper(oplusMediaViewHolder.imageButtonsFive, 0.0f, 0.35f, new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda10(1));
        }

        public static ScaleSpringAnimationHelper $r8$lambda$wgN7q9PBgj0QIY97TLNhsIoxrxU(OplusMediaViewHolder oplusMediaViewHolder) {
            return new ScaleSpringAnimationHelper(oplusMediaViewHolder.imageButtonsFive, 0.0f, 0.35f);
        }

        /* JADX WARN: Code duplicated, block: B:36:0x01cb A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:37:0x01cd  */
        /* JADX WARN: Code duplicated, block: B:39:0x01d6  */
        /* JADX WARN: Code duplicated, block: B:40:0x01e2  */
        public OplusMediaViewHolder(View view) {
            Drawable drawableMutate;
            int color;
            int i;
            super(view);
            this.mContainerView = view;
            this.mediaChildViews = new ArrayList();
            this.imageButtonsFive = new ArrayList<>();
            this.imageButtonsTwo = new ArrayList<>();
            this.itemViewOutlineWeight = 3.0f;
            this.lightOverlayOutlineWeight = 3.0f;
            this.appIconOutlineWeight = 3.0f;
            this.photoCoverLaunchWeight = 3.0f;
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(0);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconAlphaSpringAnimatorSets$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0);
            int i2 = 3;
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(i2);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconHideAlphaSpringAnimatorSets$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda1);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda2 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(4);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconScaleSpringAnimatorSets$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda2);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda3 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(5);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconHideScaleSpringAnimatorSets$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda3);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda4 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(6);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconAlphaSpringAnimatorSetsTwoToFive$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda4);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda5 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(7);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconHideAlphaSpringAnimatorSetsTwoToFive$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda5);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda6 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(8);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda6.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconScaleSpringAnimatorSetsTwoToFive$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda6);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda7 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda0(9);
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda7.f$0 = this;
            VarHandle.storeStoreFence();
            this.iconHideScaleSpringAnimatorSetsTwoToFive$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda7);
            this.mMutex = MutexKt.Mutex(false);
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
            OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0 = new OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0(1);
            oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0.f$0 = OplusMediaViewPagerAdapter.this;
            VarHandle.storeStoreFence();
            this.itemBackgroundDrawable$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0);
            ObservableField[] observableFieldArr = {OplusQsVolumeIconView.Companion.getMusicStreamRoute()};
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9 oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9();
            oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9.f$0 = this;
            VarHandle.storeStoreFence();
            this.mMusicRouteChange = new NullableObservableField<>(observableFieldArr, false, oplusMediaViewPagerAdapter$OplusMediaViewHolder$$ExternalSyntheticLambda9);
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1 anonymousClass1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1(i2);
            anonymousClass1.this$0 = this;
            anonymousClass1.$mQsMediaData = OplusMediaViewPagerAdapter.this;
            VarHandle.storeStoreFence();
            this.refreshPhotoCoverRunnable = anonymousClass1;
            int i3 = 2;
            OplusMediaViewPagerAdapter$lightAvailabilityListener$1 oplusMediaViewPagerAdapter$lightAvailabilityListener$1 = new OplusMediaViewPagerAdapter$lightAvailabilityListener$1(i3);
            oplusMediaViewPagerAdapter$lightAvailabilityListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.mLoadingRunnable = oplusMediaViewPagerAdapter$lightAvailabilityListener$1;
            OplusMediaViewPagerAdapter$lightAvailabilityListener$1 oplusMediaViewPagerAdapter$lightAvailabilityListener$2 = new OplusMediaViewPagerAdapter$lightAvailabilityListener$1(i2);
            oplusMediaViewPagerAdapter$lightAvailabilityListener$2.this$0 = this;
            VarHandle.storeStoreFence();
            this.mStopLoadingRunnable = oplusMediaViewPagerAdapter$lightAvailabilityListener$2;
            ensureSeekBarScrubbingListener.1 r0 = new ensureSeekBarScrubbingListener.1();
            r0.$holder = this;
            VarHandle.storeStoreFence();
            this.allowedProgressUpdateListener = r0;
            Drawable background = this.mContainerView.getBackground();
            if (background == null) {
                Drawable drawable = OplusMediaViewPagerAdapter.this.mContext.getDrawable(2131236364);
                background = (drawable == null || (background = drawable.mutate()) == null) ? new GradientDrawable() : background;
                this.mContainerView.setBackground(background);
            }
            if (background instanceof LayerDrawable) {
                Drawable drawable2 = ((LayerDrawable) background).getDrawable(0);
                drawableMutate = drawable2 != null ? drawable2.mutate() : null;
            } else {
                drawableMutate = background.mutate();
            }
            Context context = OplusMediaViewPagerAdapter.this.mContext;
            boolean z = OplusMediaViewPagerAdapter.this.isMiddleOrHighAnimLevel;
            boolean z2 = OplusMediaViewPagerAdapter.this.isSupportLightEffect;
            boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(context);
            boolean z3 = drawableMutate instanceof GradientDrawable;
            if (z3 && !QsColorUtil.isGlobalThemeApplied(OplusMediaViewPagerAdapter.this.mContext)) {
                boolean zBooleanValue = ((Boolean) OplusMediaViewPagerAdapter.this.oplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
                if (zIsLowGaussianLevel) {
                    QsColorUtil.INSTANCE.getClass();
                    if (!QsColorUtil.isSuperPowerSaveState) {
                        boolean zIsWallpaperBlurDisable = ScrimUtil.isWallpaperBlurDisable();
                        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(OplusMediaViewPagerAdapter.this.mContext);
                        if (zIsWallpaperBlurDisable && zIsNightMode) {
                            i = QsColorUtil.QS_TILE_BG_INACTIVE_DARK_COLOR;
                        } else if (zIsWallpaperBlurDisable) {
                            i = QsColorUtil.QS_TILE_BG_INACTIVE_COLOR;
                        } else {
                            i = (zBooleanValue || zIsNightMode) ? QsColorUtil.LIGHT_OS_QS_TILE_BG_INACTIVE_DARK_COLOR : QsColorUtil.QS_TILE_BG_INACTIVE_COLOR;
                        }
                        ((GradientDrawable) drawableMutate).setColor(i);
                    } else if (zBooleanValue) {
                        GradientDrawable gradientDrawable = (GradientDrawable) drawableMutate;
                        if (QsUtil.isPowerSavingMode()) {
                            color = OplusMediaViewPagerAdapter.this.mContext.getColor(2131103197);
                        } else {
                            color = OplusMediaViewPagerAdapter.this.mContext.getColor(2131103200);
                        }
                        gradientDrawable.setColor(color);
                    }
                } else if (zBooleanValue) {
                    GradientDrawable gradientDrawable2 = (GradientDrawable) drawableMutate;
                    if (QsUtil.isPowerSavingMode()) {
                        color = OplusMediaViewPagerAdapter.this.mContext.getColor(2131103197);
                    } else {
                        color = OplusMediaViewPagerAdapter.this.mContext.getColor(2131103200);
                    }
                    gradientDrawable2.setColor(color);
                }
            }
            boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(OplusMediaViewPagerAdapter.this.mContext, false);
            if (z3 && !zIsLowGaussianLevel && zIsNeedDarkThemeColor) {
                ((GradientDrawable) drawableMutate).setColor(OplusMediaViewPagerAdapter.this.mContext.getColor(2131103201));
            }
            drawableMutate = drawableMutate instanceof AutoBlurDrawable ? drawableMutate : QsDetailBackgroundUtils.provideBlurBackground(this.mContainerView, drawableMutate, null, !z2);
            AutoBlurDrawable autoBlurDrawable = drawableMutate instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawableMutate : null;
            this.viewBlurProxy = autoBlurDrawable != null ? autoBlurDrawable.getViewBlurProxy() : null;
            this.mContainerView.setBackground(drawableMutate);
            clearContainerBackgroundSelfDrawnCornersIfOutlineClipped();
            this.mAppIcon = ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364354);
            setAppIconOutlineProvider();
            this.mTitle = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364366);
            this.mText = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364362);
            this.mDefaultTip = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364352);
            this.mPreBtn = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364350);
            this.mPlayOrPauseBtn = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364348);
            this.mActionPlayOrPauseFrameLayout = (FrameLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364349);
            this.mDotLoading = (OplusDotProgressBar) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364313);
            this.mNextBtn = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364347);
            this.mSeekBar = ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364360);
            this.volumeSeekLayout = (RelativeLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364375);
            this.mVolumeSeekBar = (SeekBar) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364374);
            this.mSeekBarPatent = (RelativeLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364361);
            this.mCurrentTime = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364365);
            this.mTotalTime = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364351);
            this.mediaButtonConstraintLayout = ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364345);
            this.mLocalFavoriteButton = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364353);
            this.mBtnAction1 = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131362362);
            this.mBtnAction2 = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131362363);
            this.mOplusMediaPanelContainerAction4 = (FrameLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364346);
            this.mOplusDotActionAnimation = (OplusDotProgressBar) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364312);
            this.mLocalLyricBtn = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364355);
            this.mPhotoCover = (ImageView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364356);
            this.mPhotoBlurOverlay = (ProgressiveBlurOverlay) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364357);
            this.mPhotoBlurOverlayParent = (FrameLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364358);
            this.mMediaOutputBtn = (ImageButton) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364338);
            this.mMediaOutputText = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364371);
            ViewGroup viewGroup = (ViewGroup) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364369);
            this.mMediaOutputBtnContainer = viewGroup;
            if (viewGroup != null) {
                new OutPutBackgroundProxy(viewGroup, OplusMediaViewPagerAdapter.this.mContext.getResources().getDimension(2131171050)).applyBackgroundStyle();
            }
            this.mMediaCoverLayout = (ViewGroup) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364359);
            if (z) {
                FrameLayout frameLayout = (FrameLayout) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364333);
                this.mLightOverlay = frameLayout;
                if (frameLayout != null) {
                    frameLayout.setVisibility(0);
                }
            }
            this.containerDesignCornerRadius = ((RecyclerView.ViewHolder) this).itemView.getContext().getResources().getDimension(2131171057);
            applySteadyContainerOutlineRadius();
            ensureItemViewOutlineProvider();
            FrameLayout frameLayout2 = this.mLightOverlay;
            if (frameLayout2 != null) {
                frameLayout2.setOutlineProvider(new OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1(this, i3));
            }
            FrameLayout frameLayout3 = this.mLightOverlay;
            if (frameLayout3 != null) {
                frameLayout3.setClipToOutline(true);
            }
            applyPhotoCoverLayoutClip();
            setAppIconOutlineProvider();
            ImageButton imageButton = this.mLocalFavoriteButton;
            if (imageButton != null) {
                imageButton.setVisibility(0);
            }
            ImageButton imageButton2 = this.mLocalLyricBtn;
            if (imageButton2 != null) {
                imageButton2.setVisibility(0);
            }
            ImageButton imageButton3 = this.mPlayOrPauseBtn;
            if (imageButton3 != null) {
                imageButton3.setVisibility(0);
            }
            this.mVolumeMin = (ImageView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131366024);
            this.mVolumeMax = (ImageView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131366023);
            SeekBar seekBar = this.mVolumeSeekBar;
            if (seekBar != null) {
                seekBar.setThumbOffset(0);
            }
            COUIIntentSeekBar cOUIIntentSeekBar = this.mSeekBar;
            if (cOUIIntentSeekBar != null) {
                cOUIIntentSeekBar.setThumbOffset(0);
            }
            COUIIntentSeekBar cOUIIntentSeekBar2 = this.mSeekBar;
            if (cOUIIntentSeekBar2 != null) {
                cOUIIntentSeekBar2.setMoveType(1);
            }
            COUIIntentSeekBar cOUIIntentSeekBar3 = this.mSeekBar;
            if (cOUIIntentSeekBar3 != null) {
                cOUIIntentSeekBar3.setFollowSystemTheme(false);
            }
            boolean z4 = PowerSavingModeObserver.Companion.getInstance().batterySavingMode;
            QsColorUtil.INSTANCE.getClass();
            boolean z5 = QsColorUtil.isSuperPowerSaveState;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateSeekBarLightEnable, isSuperSaveMode=", ", isSupportLightEffect=", ", isSaveMode=", z5, z2);
            sbM.append(z4);
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, sbM.toString());
            if (z5 || !z2 || z4) {
                COUIIntentSeekBar cOUIIntentSeekBar4 = this.mSeekBar;
                if (cOUIIntentSeekBar4 != null) {
                    cOUIIntentSeekBar4.setUseMaterialEffect(0);
                }
            } else {
                COUIIntentSeekBar cOUIIntentSeekBar5 = this.mSeekBar;
                if (cOUIIntentSeekBar5 != null) {
                    cOUIIntentSeekBar5.setUseMaterialEffect(1);
                }
            }
            int currentUserId = OsBinderCacheUtils.getCurrentUserId();
            ViewGroup viewGroup2 = this.mMediaOutputBtnContainer;
            if (currentUserId != 0) {
                if (viewGroup2 != null) {
                    viewGroup2.setVisibility(8);
                }
            } else if (viewGroup2 != null) {
                viewGroup2.setVisibility(0);
            }
            COUIIntentSeekBar cOUIIntentSeekBar6 = this.mSeekBar;
            if (cOUIIntentSeekBar6 != null) {
                cOUIIntentSeekBar6.setAlpha(1.0f);
            }
            RelativeLayout relativeLayout = this.mSeekBarPatent;
            if (relativeLayout != null) {
                relativeLayout.setAlpha(1.0f);
                this.mediaChildViews.add(relativeLayout);
            }
            ConstraintLayout constraintLayout = this.mediaButtonConstraintLayout;
            if (constraintLayout != null) {
                constraintLayout.setAlpha(1.0f);
                this.mediaChildViews.add(constraintLayout);
            }
            RelativeLayout relativeLayout2 = this.volumeSeekLayout;
            if (relativeLayout2 != null) {
                relativeLayout2.setAlpha(1.0f);
                this.mediaChildViews.add(relativeLayout2);
            }
            ArrayList<ImageButton> arrayList = new ArrayList<>();
            ImageButton imageButton4 = this.mLocalFavoriteButton;
            if (imageButton4 != null) {
                arrayList.add(imageButton4);
            }
            ImageButton imageButton5 = this.mLocalLyricBtn;
            if (imageButton5 != null) {
                arrayList.add(imageButton5);
            }
            ImageButton imageButton6 = this.mPreBtn;
            if (imageButton6 != null) {
                arrayList.add(imageButton6);
            }
            ImageButton imageButton7 = this.mPlayOrPauseBtn;
            if (imageButton7 != null) {
                arrayList.add(imageButton7);
            }
            ImageButton imageButton8 = this.mNextBtn;
            if (imageButton8 != null) {
                arrayList.add(imageButton8);
            }
            this.imageButtonsFive = arrayList;
            ArrayList<ImageButton> arrayList2 = new ArrayList<>();
            ImageButton imageButton9 = this.mBtnAction1;
            if (imageButton9 != null) {
                arrayList2.add(imageButton9);
            }
            ImageButton imageButton10 = this.mBtnAction2;
            if (imageButton10 != null) {
                arrayList2.add(imageButton10);
            }
            this.imageButtonsTwo = arrayList2;
            if (z) {
                FrameLayout frameLayout4 = this.mLightOverlay;
                if (frameLayout4 != null) {
                    frameLayout4.setBackground(getItemBackgroundDrawable());
                }
                float dimension = ((RecyclerView.ViewHolder) this).itemView.getContext().getResources().getDimension(2131171057);
                OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(((RecyclerView.ViewHolder) this).itemView.getContext(), dimension);
                dimension = roundParams != null ? roundParams.getRadius() : dimension;
                float weight = roundParams != null ? roundParams.getWeight() : 3.0f;
                OplusMediaItemBackgroundDrawable itemBackgroundDrawable = getItemBackgroundDrawable();
                if (itemBackgroundDrawable != null) {
                    itemBackgroundDrawable.setCornerParams(dimension, weight);
                }
                FrameLayout frameLayout5 = this.mLightOverlay;
                if (frameLayout5 != null) {
                    frameLayout5.setLayerType(2, null);
                }
            }
        }

        public static final void access$applyItemBackgroundLightParams(OplusMediaViewHolder oplusMediaViewHolder) {
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (oplusMediaViewPagerAdapter.enableStrokeLight && oplusMediaViewPagerAdapter.isSupportLightEffect) {
                OplusMediaItemBackgroundDrawable itemBackgroundDrawable = oplusMediaViewHolder.getItemBackgroundDrawable();
                if (itemBackgroundDrawable != null) {
                    itemBackgroundDrawable.applyShadowAndOutlineLight(oplusMediaViewPagerAdapter.primaryColor80);
                    return;
                }
                return;
            }
            OplusMediaItemBackgroundDrawable itemBackgroundDrawable2 = oplusMediaViewHolder.getItemBackgroundDrawable();
            if (itemBackgroundDrawable2 != null) {
                itemBackgroundDrawable2.clearLight();
            }
        }

        /* JADX WARN: Code duplicated, block: B:40:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:47:0x00d1  */
        /* JADX WARN: Code duplicated, block: B:49:0x00d8 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public static final Object access$getAppIcon(OplusMediaViewHolder oplusMediaViewHolder, String str, ContinuationImpl continuationImpl) {
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1 oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1;
            Drawable drawable;
            Mutex mutex;
            Pair<? extends Drawable, ? extends Drawable> pair;
            Drawable bitmapDrawable;
            oplusMediaViewHolder.getClass();
            if (continuationImpl instanceof OplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1) {
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1 = (OplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1) continuationImpl;
                int i = oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.label = i - Integer.MIN_VALUE;
                } else {
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1(oplusMediaViewHolder, continuationImpl);
                }
            } else {
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1(oplusMediaViewHolder, continuationImpl);
            }
            Object packageIcon = oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(packageIcon);
                NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.label = 1;
                packageIcon = notificationResolverEx.getPackageIcon(str, oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1);
                if (packageIcon != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                str = (String) oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$0;
                ResultKt.throwOnFailure(packageIcon);
            } else {
                if (i2 != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                mutex = (Mutex) oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$2;
                drawable = (Drawable) oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$1;
                ResultKt.throwOnFailure(packageIcon);
            }
            try {
                if (!(drawable instanceof BitmapDrawable) || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                    bitmapDrawable = drawable;
                } else {
                    bitmapDrawable = new BitmapDrawable(((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView.getContext().getResources(), DrawableKt.toBitmap$default(drawable, 0, 0, 7));
                }
                oplusMediaViewHolder.mAppIconPair = new Pair<>(drawable, bitmapDrawable);
                mutex.unlock((Object) null);
                pair = oplusMediaViewHolder.mAppIconPair;
                if (pair != null) {
                    return (Drawable) pair.getSecond();
                }
                return null;
            } catch (Throwable th) {
                mutex.unlock((Object) null);
                throw th;
            }
            Drawable drawable2 = (Drawable) packageIcon;
            if (drawable2 != null) {
                Pair<? extends Drawable, ? extends Drawable> pair2 = oplusMediaViewHolder.mAppIconPair;
                if (!Intrinsics.areEqual(pair2 != null ? (Drawable) pair2.getFirst() : null, drawable2)) {
                    Mutex mutex2 = oplusMediaViewHolder.mMutex;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$1 = drawable2;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.L$2 = mutex2;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.I$0 = 0;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1.label = 2;
                    if (mutex2.lock((Object) null, oplusMediaViewPagerAdapter$OplusMediaViewHolder$getAppIcon$1) != coroutine_suspended) {
                        drawable = drawable2;
                        mutex = mutex2;
                        if (drawable instanceof BitmapDrawable) {
                            bitmapDrawable = drawable;
                        } else {
                            bitmapDrawable = drawable;
                        }
                        oplusMediaViewHolder.mAppIconPair = new Pair<>(drawable, bitmapDrawable);
                        mutex.unlock((Object) null);
                    }
                    return coroutine_suspended;
                }
            }
            pair = oplusMediaViewHolder.mAppIconPair;
            if (pair != null) {
                return (Drawable) pair.getSecond();
            }
            return null;
        }

        public static final void access$handleIconException(OplusMediaViewHolder oplusMediaViewHolder) {
            Handler handler = oplusMediaViewHolder.mHandler;
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1 anonymousClass1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1(1);
            anonymousClass1.$mQsMediaData = oplusMediaViewPagerAdapter;
            anonymousClass1.this$0 = oplusMediaViewHolder;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }

        public static final boolean access$isBuffering(OplusMediaViewHolder oplusMediaViewHolder) {
            MediaController mediaController = oplusMediaViewHolder.mController;
            if (mediaController == null) {
                return true;
            }
            PlaybackState playbackState = mediaController.getPlaybackState();
            return playbackState != null && playbackState.getState() == 6;
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0037  */
        /* JADX WARN: Code duplicated, block: B:18:0x004c  */
        /* JADX WARN: Code duplicated, block: B:9:0x002b  */
        public static final void access$mediaOplusTrack(OplusMediaViewHolder oplusMediaViewHolder, MediaData mediaData, ImageButton imageButton, MediaAction mediaAction, OplusMediaActions oplusMediaActions) {
            boolean zAreEqual = Intrinsics.areEqual(mediaData.getMediaDataEx().getMediaActionPriority(), MediaActionPriority.PreferSemanticActions.INSTANCE);
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (!zAreEqual) {
                QsStatisticUtil.collectMediaPanelActionClick(oplusMediaViewPagerAdapter.mContext, "11" + ((Object) mediaAction.getContentDescription()));
                return;
            }
            Context context = oplusMediaViewPagerAdapter.mContext;
            String str = "8";
            if (!Intrinsics.areEqual(imageButton, oplusMediaViewHolder.mPreBtn)) {
                if (Intrinsics.areEqual(imageButton, oplusMediaViewHolder.mPlayOrPauseBtn)) {
                    str = "9";
                } else if (Intrinsics.areEqual(imageButton, oplusMediaViewHolder.mNextBtn)) {
                    str = "10";
                } else if (!Intrinsics.areEqual(imageButton, oplusMediaViewHolder.mBtnAction1) && !Intrinsics.areEqual(imageButton, oplusMediaViewHolder.mBtnAction2)) {
                    str = "";
                } else if (Intrinsics.areEqual(mediaAction, oplusMediaActions.getPlayPauseOrCustom())) {
                    str = "9";
                } else if (!Intrinsics.areEqual(mediaAction, oplusMediaActions.getPreOrCustom())) {
                    if (Intrinsics.areEqual(mediaAction, oplusMediaActions.getNextOrCustom())) {
                        str = "10";
                    } else {
                        str = "";
                    }
                }
            }
            QsStatisticUtil.collectMediaPanelActionClick(context, str);
        }

        private final AlphaSpringAnimatorHelper getIconAlphaSpringAnimatorSets() {
            return (AlphaSpringAnimatorHelper) this.iconAlphaSpringAnimatorSets$delegate.getValue();
        }

        private final AlphaSpringAnimatorHelper getIconAlphaSpringAnimatorSetsTwoToFive() {
            return (AlphaSpringAnimatorHelper) this.iconAlphaSpringAnimatorSetsTwoToFive$delegate.getValue();
        }

        private final AlphaSpringAnimatorHelper getIconHideAlphaSpringAnimatorSets() {
            return (AlphaSpringAnimatorHelper) this.iconHideAlphaSpringAnimatorSets$delegate.getValue();
        }

        private final AlphaSpringAnimatorHelper getIconHideAlphaSpringAnimatorSetsTwoToFive() {
            return (AlphaSpringAnimatorHelper) this.iconHideAlphaSpringAnimatorSetsTwoToFive$delegate.getValue();
        }

        private final ScaleSpringAnimationHelper getIconHideScaleSpringAnimatorSets() {
            return (ScaleSpringAnimationHelper) this.iconHideScaleSpringAnimatorSets$delegate.getValue();
        }

        private final ScaleSpringAnimationHelper getIconHideScaleSpringAnimatorSetsTwoToFive() {
            return (ScaleSpringAnimationHelper) this.iconHideScaleSpringAnimatorSetsTwoToFive$delegate.getValue();
        }

        private final ScaleSpringAnimationHelper getIconScaleSpringAnimatorSets() {
            return (ScaleSpringAnimationHelper) this.iconScaleSpringAnimatorSets$delegate.getValue();
        }

        private final ScaleSpringAnimationHelper getIconScaleSpringAnimatorSetsTwoToFive() {
            return (ScaleSpringAnimationHelper) this.iconScaleSpringAnimatorSetsTwoToFive$delegate.getValue();
        }

        private final OplusMediaItemBackgroundDrawable getItemBackgroundDrawable() {
            return (OplusMediaItemBackgroundDrawable) this.itemBackgroundDrawable$delegate.getValue();
        }

        public static void resetNoPlayState(ImageButton imageButton, int i, boolean z) {
            if (imageButton != null) {
                imageButton.clearColorFilter();
            }
            if (imageButton != null) {
                imageButton.setImageResource(i);
            }
            if (imageButton != null) {
                imageButton.setContentDescription(null);
            }
            if (!z || imageButton == null) {
                return;
            }
            imageButton.setEnabled(false);
        }

        public static void setTintList(ImageView imageView, ColorStateList colorStateList) {
            if (imageView != null) {
                imageView.clearColorFilter();
            }
            if (imageView != null) {
                imageView.setImageTintList(colorStateList);
            }
        }

        public final void applyPhotoCoverLaunchOutline() {
            ViewGroup viewGroup = this.mMediaCoverLayout;
            if (viewGroup == null) {
                return;
            }
            Companion companion = OplusMediaViewPagerAdapter.Companion;
            final OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            float dimension = oplusMediaViewPagerAdapter.isLandscape$2() ? ((RecyclerView.ViewHolder) this).itemView.getContext().getResources().getDimension(2131170171) : this.containerDesignCornerRadius;
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(((RecyclerView.ViewHolder) this).itemView.getContext(), dimension);
            if (roundParams != null) {
                dimension = roundParams.getRadius();
            }
            this.photoCoverLaunchRadius = dimension;
            this.photoCoverLaunchWeight = roundParams != null ? roundParams.getWeight() : 3.0f;
            viewGroup.setClipChildren(true);
            viewGroup.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyPhotoCoverLaunchOutline$1
                @Override // android.view.ViewOutlineProvider
                public final void getOutline(View view, Outline outline) {
                    if (this.this$0.photoCoverOplusOutlineAdapter == null || !Intrinsics.areEqual(this.this$0.photoCoverOutlineLast, outline)) {
                        this.this$0.photoCoverOutlineLast = outline;
                        this.this$0.photoCoverOplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
                    }
                    OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = oplusMediaViewPagerAdapter;
                    OplusMediaViewPagerAdapter.Companion companion2 = OplusMediaViewPagerAdapter.Companion;
                    int height = oplusMediaViewPagerAdapter2.isLandscape$2() ? view.getHeight() : view.getHeight() + ((int) this.this$0.photoCoverLaunchRadius);
                    OplusOutlineAdapter oplusOutlineAdapter = this.this$0.photoCoverOplusOutlineAdapter;
                    if (oplusOutlineAdapter != null) {
                        int width = view.getWidth();
                        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.this$0;
                        oplusOutlineAdapter.setSmoothRoundRect(0, 0, width, height, oplusMediaViewHolder.photoCoverLaunchRadius, oplusMediaViewHolder.photoCoverLaunchWeight);
                    }
                }
            });
            viewGroup.setClipToOutline(true);
            viewGroup.invalidateOutline();
        }

        public final void applyPhotoCoverLayoutClip() {
            ViewGroup viewGroup = this.mMediaCoverLayout;
            if (viewGroup == null) {
                return;
            }
            Context context = ((RecyclerView.ViewHolder) this).itemView.getContext();
            float radius = this.containerDesignCornerRadius;
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, radius);
            if (roundParams != null) {
                radius = roundParams.getRadius();
            }
            float dimension = ((RecyclerView.ViewHolder) this).itemView.getContext().getResources().getDimension(2131170171);
            OplusRoundParams roundParams2 = OplusQsSmoothRoundUtil.getRoundParams(((RecyclerView.ViewHolder) this).itemView.getContext(), dimension);
            if (roundParams2 != null) {
                dimension = roundParams2.getRadius();
            }
            float weight = roundParams2 != null ? roundParams2.getWeight() : 3.0f;
            ImageView imageView = this.mPhotoCover;
            if (imageView != null) {
                imageView.setClipToOutline(false);
            }
            ImageView imageView2 = this.mPhotoCover;
            if (imageView2 != null) {
                imageView2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            }
            viewGroup.setClipChildren(true);
            if (dimension >= radius) {
                viewGroup.setClipToOutline(false);
                viewGroup.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            } else {
                viewGroup.setOutlineProvider(new applyPhotoCoverLayoutClip.1(dimension, radius, this, weight));
                viewGroup.setClipToOutline(true);
                viewGroup.invalidateOutline();
            }
        }

        public final void applySteadyContainerOutlineRadius() {
            Pair pairResolveContainerOutlineParams = resolveContainerOutlineParams();
            float fFloatValue = ((Number) pairResolveContainerOutlineParams.component1()).floatValue();
            float fFloatValue2 = ((Number) pairResolveContainerOutlineParams.component2()).floatValue();
            this.itemViewOutlineRadius = fFloatValue;
            this.itemViewOutlineWeight = fFloatValue2;
            this.lightOverlayOutlineRadius = fFloatValue;
            this.lightOverlayOutlineWeight = fFloatValue2;
        }

        public final void bind(MediaData mediaData) throws Throwable {
            Throwable th;
            MediaAction action0;
            MediaAction action1;
            this.mediaData = mediaData;
            if ((mediaData != null ? mediaData.getToken() : null) == null) {
                String notificationKey = mediaData != null ? mediaData.getNotificationKey() : null;
                ImageButton imageButton = this.mPlayOrPauseBtn;
                if (notificationKey != null) {
                    if (imageButton != null) {
                        OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1 oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1();
                        oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.$mQsMediaData = mediaData;
                        oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.this$0 = this;
                        VarHandle.storeStoreFence();
                        imageButton.setOnClickListener(oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1);
                    }
                } else if (imageButton != null) {
                    imageButton.setEnabled(false);
                }
                bindMediaIcon(mediaData);
                bindPhotoClickEvent(mediaData);
                bindDevice$1(mediaData);
                showNoPlayView();
                updateViewColor$1();
                FrameLayout frameLayout = this.mLightOverlay;
                if (frameLayout != null) {
                    frameLayout.setBackground(getItemBackgroundDrawable());
                }
                refreshItemMultiLightBackground();
                ProgressiveBlurOverlay progressiveBlurOverlay = this.mPhotoBlurOverlay;
                if (progressiveBlurOverlay != null) {
                    progressiveBlurOverlay.setVisibility(8);
                    return;
                }
                return;
            }
            MediaSession.Token token = mediaData.getToken();
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "token new " + token + " ----- old token is " + this.mToken);
            boolean zAreEqual = Intrinsics.areEqual(this.mToken, token);
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (!zAreEqual) {
                this.mToken = token;
                this.mController = token != null ? new MediaController(oplusMediaViewPagerAdapter.mContext, token) : null;
            }
            bindMediaProgress();
            bindMediaIcon(mediaData);
            TextView textView = this.mDefaultTip;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.mTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.mText;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            COUIIntentSeekBar cOUIIntentSeekBar = this.mSeekBar;
            updateTextContent(cOUIIntentSeekBar != null ? Integer.valueOf(cOUIIntentSeekBar.getProgress()) : null);
            OplusMediaActions oplusMediaActionsCreateQSMediaActions = MediaActionBinderKt.createQSMediaActions(mediaData, (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class));
            if (oplusMediaActionsCreateQSMediaActions != null) {
                int size = oplusMediaActionsCreateQSMediaActions.getNotNullActions().size();
                int i = this.lastSize;
                boolean z = i != size && ((i <= 2 && 2 < size) || (size <= 2 && 2 < i));
                this.needAnimation = z;
                if (!z) {
                    th = null;
                } else if (i > 2) {
                    AlphaSpringAnimatorHelper iconHideAlphaSpringAnimatorSets = getIconHideAlphaSpringAnimatorSets();
                    iconHideAlphaSpringAnimatorSets.setStartValue(1.0f);
                    iconHideAlphaSpringAnimatorSets.animateTo(0.0f);
                    ArrayList<ImageButton> arrayList = this.imageButtonsTwo;
                    int size2 = arrayList.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        ImageButton imageButton2 = arrayList.get(i2);
                        i2++;
                        imageButton2.setAlpha(0.0f);
                    }
                    th = null;
                    ScaleSpringAnimationHelper iconHideScaleSpringAnimatorSets = getIconHideScaleSpringAnimatorSets();
                    iconHideScaleSpringAnimatorSets.setStartValue(1.0f);
                    iconHideScaleSpringAnimatorSets.animateTo(0.6f);
                } else {
                    th = null;
                    AlphaSpringAnimatorHelper iconHideAlphaSpringAnimatorSetsTwoToFive = getIconHideAlphaSpringAnimatorSetsTwoToFive();
                    iconHideAlphaSpringAnimatorSetsTwoToFive.setStartValue(1.0f);
                    iconHideAlphaSpringAnimatorSetsTwoToFive.animateTo(0.0f);
                    ArrayList<ImageButton> arrayList2 = this.imageButtonsFive;
                    int size3 = arrayList2.size();
                    int i3 = 0;
                    while (i3 < size3) {
                        ImageButton imageButton3 = arrayList2.get(i3);
                        i3++;
                        imageButton3.setAlpha(0.0f);
                    }
                    ScaleSpringAnimationHelper iconHideScaleSpringAnimatorSetsTwoToFive = getIconHideScaleSpringAnimatorSetsTwoToFive();
                    iconHideScaleSpringAnimatorSetsTwoToFive.setStartValue(1.0f);
                    iconHideScaleSpringAnimatorSetsTwoToFive.animateTo(0.6f);
                }
                boolean z2 = oplusMediaViewPagerAdapter.mContext.getResources().getConfiguration().getLayoutDirection() == 1;
                if (oplusMediaActionsCreateQSMediaActions.getNotNullActions().size() > 4) {
                    setLayoutVisibility(oplusMediaActionsCreateQSMediaActions.getNotNullActions(), z2);
                    MediaAction preOrCustom = oplusMediaActionsCreateQSMediaActions.getPreOrCustom();
                    MediaAction playPauseOrCustom = oplusMediaActionsCreateQSMediaActions.getPlayPauseOrCustom();
                    MediaAction nextOrCustom = oplusMediaActionsCreateQSMediaActions.getNextOrCustom();
                    setSemanticButton(this.mPreBtn, preOrCustom, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    setSemanticButton(this.mPlayOrPauseBtn, playPauseOrCustom, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    setSemanticButton(this.mNextBtn, nextOrCustom, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    if (z2) {
                        action0 = oplusMediaActionsCreateQSMediaActions.getAction1();
                        action1 = oplusMediaActionsCreateQSMediaActions.getAction0();
                    } else {
                        action0 = oplusMediaActionsCreateQSMediaActions.getAction0();
                        action1 = oplusMediaActionsCreateQSMediaActions.getAction1();
                    }
                    MediaAction mediaAction = action1;
                    MediaAction mediaAction2 = action0;
                    if (mediaAction2 == null && mediaAction == null) {
                        ImageButton imageButton4 = this.mLocalFavoriteButton;
                        if (imageButton4 != null) {
                            imageButton4.setVisibility(4);
                        }
                        ImageButton imageButton5 = this.mLocalLyricBtn;
                        if (imageButton5 != null) {
                            imageButton5.setVisibility(4);
                        }
                    } else if (mediaAction2 == null || mediaAction != null) {
                        ImageButton imageButton6 = this.mLocalFavoriteButton;
                        if (mediaAction2 == null) {
                            if (imageButton6 != null) {
                                imageButton6.setVisibility(4);
                            }
                            ImageButton imageButton7 = this.mLocalLyricBtn;
                            if (imageButton7 != null) {
                                imageButton7.setVisibility(0);
                            }
                            setSemanticButton(this.mLocalLyricBtn, mediaAction, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        } else {
                            if (imageButton6 != null) {
                                imageButton6.setVisibility(0);
                            }
                            ImageButton imageButton8 = this.mLocalLyricBtn;
                            if (imageButton8 != null) {
                                imageButton8.setVisibility(0);
                            }
                            setSemanticButton(this.mLocalFavoriteButton, mediaAction2, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mLocalLyricBtn, mediaAction, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        }
                    } else {
                        ImageButton imageButton9 = this.mLocalFavoriteButton;
                        if (imageButton9 != null) {
                            imageButton9.setVisibility(0);
                        }
                        ImageButton imageButton10 = this.mLocalLyricBtn;
                        if (imageButton10 != null) {
                            imageButton10.setVisibility(4);
                        }
                        setSemanticButton(this.mLocalFavoriteButton, mediaAction2, mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    }
                } else if (oplusMediaActionsCreateQSMediaActions.getNotNullActions().size() == 4) {
                    setLayoutVisibility(oplusMediaActionsCreateQSMediaActions.getNotNullActions(), z2);
                    if (oplusMediaActionsCreateQSMediaActions.getAction0() == null || oplusMediaActionsCreateQSMediaActions.getAction1() == null) {
                        setSemanticButton(this.mPreBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(0), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        setSemanticButton(this.mPlayOrPauseBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(1), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        setSemanticButton(this.mNextBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(2), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        if (z2) {
                            setSemanticButton(this.mLocalLyricBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(3), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        } else {
                            setSemanticButton(this.mLocalFavoriteButton, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(3), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        }
                    } else {
                        setSemanticButton(this.mPlayOrPauseBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(2), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        if (z2) {
                            setSemanticButton(this.mPreBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(3), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mPlayOrPauseBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(1), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mNextBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(2), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mLocalLyricBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(0), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        } else {
                            setSemanticButton(this.mLocalFavoriteButton, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(0), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mPreBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(1), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mPlayOrPauseBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(2), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                            setSemanticButton(this.mNextBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(3), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                        }
                    }
                } else if (oplusMediaActionsCreateQSMediaActions.getNotNullActions().size() == 3) {
                    setLayoutVisibility(oplusMediaActionsCreateQSMediaActions.getNotNullActions(), z2);
                    setSemanticButton(this.mPreBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(0), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    setSemanticButton(this.mPlayOrPauseBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(1), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                    setSemanticButton(this.mNextBtn, oplusMediaActionsCreateQSMediaActions.getNotNullActions().get(2), mediaData, oplusMediaActionsCreateQSMediaActions, false);
                } else if (oplusMediaActionsCreateQSMediaActions.getNotNullActions().size() <= 2 && !oplusMediaActionsCreateQSMediaActions.getNotNullActions().isEmpty()) {
                    setLayoutVisibility(oplusMediaActionsCreateQSMediaActions.getNotNullActions(), z2);
                    int i4 = 0;
                    for (Object obj : oplusMediaActionsCreateQSMediaActions.getNotNullActions()) {
                        int i5 = i4 + 1;
                        if (i4 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                            throw th;
                        }
                        MediaAction mediaAction3 = (MediaAction) obj;
                        if (i4 == 0) {
                            setSemanticButton(this.mBtnAction1, mediaAction3, mediaData, oplusMediaActionsCreateQSMediaActions, true);
                        } else if (i4 == 1) {
                            setSemanticButton(this.mBtnAction2, mediaAction3, mediaData, oplusMediaActionsCreateQSMediaActions, true);
                        }
                        i4 = i5;
                    }
                }
                if (this.needAnimation) {
                    if (this.lastSize > 2) {
                        AlphaSpringAnimatorHelper iconAlphaSpringAnimatorSets = getIconAlphaSpringAnimatorSets();
                        iconAlphaSpringAnimatorSets.setStartValue(0.0f);
                        iconAlphaSpringAnimatorSets.animateTo(1.0f);
                        ScaleSpringAnimationHelper iconScaleSpringAnimatorSets = getIconScaleSpringAnimatorSets();
                        iconScaleSpringAnimatorSets.setStartValue(0.6f);
                        iconScaleSpringAnimatorSets.animateTo(1.0f);
                    } else {
                        AlphaSpringAnimatorHelper iconAlphaSpringAnimatorSetsTwoToFive = getIconAlphaSpringAnimatorSetsTwoToFive();
                        iconAlphaSpringAnimatorSetsTwoToFive.setStartValue(0.0f);
                        iconAlphaSpringAnimatorSetsTwoToFive.animateTo(1.0f);
                        ScaleSpringAnimationHelper iconScaleSpringAnimatorSetsTwoToFive = getIconScaleSpringAnimatorSetsTwoToFive();
                        iconScaleSpringAnimatorSetsTwoToFive.setStartValue(0.6f);
                        iconScaleSpringAnimatorSetsTwoToFive.animateTo(1.0f);
                    }
                    this.lastSize = size;
                }
                ProgressiveBlurOverlay progressiveBlurOverlay2 = this.mPhotoBlurOverlay;
                if (progressiveBlurOverlay2 != null) {
                    progressiveBlurOverlay2.setVisibility((oplusMediaViewPagerAdapter.enableStrokeLight && oplusMediaViewPagerAdapter.isSupportLightEffect) ? 0 : 8);
                }
            } else {
                showNoPlayView();
                ProgressiveBlurOverlay progressiveBlurOverlay3 = this.mPhotoBlurOverlay;
                if (progressiveBlurOverlay3 != null) {
                    progressiveBlurOverlay3.setVisibility(8);
                }
            }
            bindDevice$1(mediaData);
            bindPhotoClickEvent(mediaData);
            updateViewColor$1();
            FrameLayout frameLayout2 = this.mLightOverlay;
            if (frameLayout2 != null) {
                frameLayout2.setBackground(getItemBackgroundDrawable());
            }
            refreshItemMultiLightBackground();
        }

        public final void bindDevice$1(MediaData mediaData) {
            CharSequence string;
            Drawable icon;
            if (mediaData == null) {
                ViewGroup viewGroup = this.mMediaOutputBtnContainer;
                if (viewGroup != null) {
                    viewGroup.setVisibility(4);
                    return;
                }
                return;
            }
            MediaDeviceData device = mediaData.getDevice();
            float f = ((device == null || device.getEnabled()) && !mediaData.getResumption()) ? 1.0f : 0.38f;
            ImageButton imageButton = this.mMediaOutputBtn;
            if (imageButton != null) {
                imageButton.setAlpha(f);
            }
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (device == null || (string = device.getName()) == null) {
                string = oplusMediaViewPagerAdapter.mContext.getString(2131954562);
            }
            if (device == null || (icon = device.getIconWithoutTheme()) == null) {
                icon = device != null ? device.getIcon() : null;
            }
            ImageButton imageButton2 = this.mMediaOutputBtn;
            if (icon != null) {
                if (imageButton2 != null) {
                    imageButton2.setImageDrawable(icon);
                }
            } else if (imageButton2 != null) {
                imageButton2.setImageResource(2131237113);
            }
            ImageButton imageButton3 = this.mMediaOutputBtn;
            if (imageButton3 != null) {
                imageButton3.clearColorFilter();
            }
            int i = 0;
            boolean zIsMediaIconNeedUseLightColor = QsColorUtil.isMediaIconNeedUseLightColor(oplusMediaViewPagerAdapter.mContext, false);
            ImageButton imageButton4 = this.mMediaOutputBtn;
            if (zIsMediaIconNeedUseLightColor) {
                if (imageButton4 != null) {
                    imageButton4.setColorFilter(QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR, PorterDuff.Mode.SRC_IN);
                }
            } else if (imageButton4 != null) {
                imageButton4.setColorFilter(oplusMediaViewPagerAdapter.mContext.getColor(2131103367), PorterDuff.Mode.SRC_IN);
            }
            ViewGroup viewGroup2 = this.mMediaOutputBtnContainer;
            if (viewGroup2 != null) {
                viewGroup2.setContentDescription(string);
            }
            ViewGroup viewGroup3 = this.mMediaOutputBtnContainer;
            if (viewGroup3 != null) {
                OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1(i);
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.this$0 = oplusMediaViewPagerAdapter;
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.this$1 = this;
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.$data = mediaData;
                VarHandle.storeStoreFence();
                viewGroup3.setOnClickListener(oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1);
            }
        }

        public final void bindMediaIcon(MediaData mediaData) {
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (mediaData == null) {
                AppCompatImageView appCompatImageView = this.mAppIcon;
                if (appCompatImageView != null) {
                    appCompatImageView.setVisibility(8);
                }
                ImageView imageView = this.mPhotoCover;
                if (imageView != null) {
                    imageView.setImageResource(oplusMediaViewPagerAdapter.getDefaultPhotoDrawable());
                    return;
                }
                return;
            }
            this.mHandler.removeCallbacks(this.refreshPhotoCoverRunnable);
            AppCompatImageView appCompatImageView2 = this.mAppIcon;
            if (appCompatImageView2 != null) {
                appCompatImageView2.clearColorFilter();
            }
            if (mediaData.getResumption()) {
                AppCompatImageView appCompatImageView3 = this.mAppIcon;
                if (appCompatImageView3 != null) {
                    appCompatImageView3.setVisibility(8);
                }
            } else {
                Job job = this.mIconJob;
                if (job != null) {
                    Job.Key key = Job.Key;
                    job.cancel((CancellationException) null);
                }
                this.mIconJob = BuildersKt.launch$default(oplusMediaViewPagerAdapter.scope, (CoroutineContext) null, (CoroutineStart) null, new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaIcon$1(this, mediaData, oplusMediaViewPagerAdapter, null), 3);
            }
            if (mediaData.getArtwork() != null && mediaData.getToken() != null) {
                ImageView imageView2 = this.mPhotoCover;
                if (imageView2 != null) {
                    imageView2.setImageIcon(mediaData.getArtwork());
                    return;
                }
                return;
            }
            ImageView imageView3 = this.mPhotoCover;
            if ((imageView3 != null ? imageView3.getDrawable() : null) != null) {
                this.mHandler.postDelayed(this.refreshPhotoCoverRunnable, 200L);
                return;
            }
            ImageView imageView4 = this.mPhotoCover;
            if (imageView4 != null) {
                imageView4.setImageResource(oplusMediaViewPagerAdapter.getDefaultPhotoDrawable());
            }
        }

        public final void bindMediaProgress() {
            PlaybackState playbackState;
            if (this.mController != null) {
                SeekBarViewModel seekBarViewModel = this.mSeekBarViewModel;
                int i = 1;
                if ((seekBarViewModel != null && !seekBarViewModel.isAllowedProgressUpdate) || SystemClock.uptimeMillis() < this.mSeekBarProgressBlockUntilMs) {
                    this.mPendingProgress = true;
                    return;
                }
                this.mPendingProgress = false;
                MediaController mediaController = this.mController;
                if (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null || playbackState.getState() != 3) {
                    COUIIntentSeekBar cOUIIntentSeekBar = this.mSeekBar;
                    if (cOUIIntentSeekBar != null) {
                        cOUIIntentSeekBar.setPlaybackState(com.coui.appcompat.seekbar.PlaybackState.PAUSED);
                    }
                } else {
                    COUIIntentSeekBar cOUIIntentSeekBar2 = this.mSeekBar;
                    if (cOUIIntentSeekBar2 != null) {
                        cOUIIntentSeekBar2.setPlaybackState(com.coui.appcompat.seekbar.PlaybackState.PLAYING);
                    }
                }
                OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "bindMediaProgress via ViewModel, controller=" + this.mController);
                SeekBarViewModel seekBarViewModel2 = this.mSeekBarViewModel;
                if (seekBarViewModel2 != null) {
                    seekBarViewModel2.setListening(true);
                }
                Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
                OplusMediaViewPagerAdapter$lightAvailabilityListener$1 oplusMediaViewPagerAdapter$lightAvailabilityListener$1 = new OplusMediaViewPagerAdapter$lightAvailabilityListener$1(i);
                oplusMediaViewPagerAdapter$lightAvailabilityListener$1.this$0 = this;
                VarHandle.storeStoreFence();
                executor.execute(oplusMediaViewPagerAdapter$lightAvailabilityListener$1);
            }
        }

        public final void bindPhotoClickEvent(MediaData mediaData) {
            PendingIntent clickIntent = mediaData != null ? mediaData.getClickIntent() : null;
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (clickIntent != null) {
                OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "bindPhotoByClickIntent");
                ViewGroup viewGroup = this.mMediaCoverLayout;
                if (viewGroup != null) {
                    OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1(2);
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.this$1 = this;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.$data = clickIntent;
                    oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1.this$0 = oplusMediaViewPagerAdapter;
                    VarHandle.storeStoreFence();
                    viewGroup.setOnClickListener(oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1);
                    return;
                }
                return;
            }
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "bindPhotoByIntent");
            if ((mediaData != null ? mediaData.getPackageName() : null) == null) {
                OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "bindPhotoByIntent packageName is null");
                return;
            }
            ViewGroup viewGroup2 = this.mMediaCoverLayout;
            if (viewGroup2 != null) {
                OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1 oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$2 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$1(1);
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$2.this$0 = oplusMediaViewPagerAdapter;
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$2.$data = mediaData;
                oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$2.this$1 = this;
                VarHandle.storeStoreFence();
                viewGroup2.setOnClickListener(oplusMediaViewPagerAdapter$OplusMediaViewHolder$bindDevice$2);
            }
        }

        public final void clearContainerBackgroundSelfDrawnCornersIfOutlineClipped() {
            if (this.mContainerView.getBackground() instanceof AutoBlurDrawable) {
                return;
            }
            Drawable background = this.mContainerView.getBackground();
            GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
            if (gradientDrawable == null || gradientDrawable.getCornerRadius() == 0.0f) {
                return;
            }
            gradientDrawable.setCornerRadius(0.0f);
            gradientDrawable.invalidateSelf();
        }

        public final void clearPhotoCoverLaunchOutlineClip() {
            ImageView imageView = this.mPhotoCover;
            if (imageView != null) {
                imageView.setClipToOutline(false);
            }
            ImageView imageView2 = this.mPhotoCover;
            if (imageView2 != null) {
                imageView2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            }
            ViewGroup viewGroup = this.mMediaCoverLayout;
            if (viewGroup != null) {
                viewGroup.setClipToOutline(false);
            }
            ViewGroup viewGroup2 = this.mMediaCoverLayout;
            if (viewGroup2 != null) {
                viewGroup2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            }
        }

        public final void ensureItemViewOutlineProvider() {
            ((RecyclerView.ViewHolder) this).itemView.setOutlineProvider(new OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1(this, 1));
            ((RecyclerView.ViewHolder) this).itemView.setClipToOutline(true);
        }

        public final SeekBarViewModel.AllowedProgressUpdateListener getAllowedProgressUpdateListener() {
            return this.allowedProgressUpdateListener;
        }

        public final FrameLayout getMActionPlayOrPauseFrameLayout() {
            return this.mActionPlayOrPauseFrameLayout;
        }

        public final AppCompatImageView getMAppIcon() {
            return this.mAppIcon;
        }

        public final Handler getMBgHandler() {
            return this.mBgHandler;
        }

        public final ImageButton getMBtnAction1() {
            return this.mBtnAction1;
        }

        public final ImageButton getMBtnAction2() {
            return this.mBtnAction2;
        }

        public final View getMContainerView() {
            return this.mContainerView;
        }

        public final MediaController getMController() {
            return this.mController;
        }

        public final TextView getMCurrentTime() {
            return this.mCurrentTime;
        }

        public final TextView getMDefaultTip() {
            return this.mDefaultTip;
        }

        public final OplusDotProgressBar getMDotLoading() {
            return this.mDotLoading;
        }

        public final Handler getMHandler() {
            return this.mHandler;
        }

        public final FrameLayout getMLightOverlay() {
            return this.mLightOverlay;
        }

        public final Runnable getMLoadingRunnable() {
            return this.mLoadingRunnable;
        }

        public final ImageButton getMLocalFavoriteButton() {
            return this.mLocalFavoriteButton;
        }

        public final ImageButton getMLocalLyricBtn() {
            return this.mLocalLyricBtn;
        }

        public final ViewGroup getMMediaCoverLayout() {
            return this.mMediaCoverLayout;
        }

        public final ImageButton getMMediaOutputBtn() {
            return this.mMediaOutputBtn;
        }

        public final ViewGroup getMMediaOutputBtnContainer() {
            return this.mMediaOutputBtnContainer;
        }

        public final TextView getMMediaOutputText() {
            return this.mMediaOutputText;
        }

        public final NullableObservableField<Unit> getMMusicRouteChange() {
            return this.mMusicRouteChange;
        }

        public final ImageButton getMNextBtn() {
            return this.mNextBtn;
        }

        public final OplusDotProgressBar getMOplusDotActionAnimation() {
            return this.mOplusDotActionAnimation;
        }

        public final FrameLayout getMOplusMediaPanelContainerAction4() {
            return this.mOplusMediaPanelContainerAction4;
        }

        public final boolean getMPendingProgress() {
            return this.mPendingProgress;
        }

        public final ProgressiveBlurOverlay getMPhotoBlurOverlay() {
            return this.mPhotoBlurOverlay;
        }

        public final FrameLayout getMPhotoBlurOverlayParent() {
            return this.mPhotoBlurOverlayParent;
        }

        public final ImageView getMPhotoCover() {
            return this.mPhotoCover;
        }

        public final ImageButton getMPlayOrPauseBtn() {
            return this.mPlayOrPauseBtn;
        }

        public final ImageButton getMPreBtn() {
            return this.mPreBtn;
        }

        public final COUIIntentSeekBar getMSeekBar() {
            return this.mSeekBar;
        }

        public final OplusQsSeekBarObserver getMSeekBarObserver() {
            return this.mSeekBarObserver;
        }

        public final RelativeLayout getMSeekBarPatent() {
            return this.mSeekBarPatent;
        }

        public final SeekBarViewModel getMSeekBarViewModel() {
            return this.mSeekBarViewModel;
        }

        public final SeekBarViewModel.ScrubbingChangeListener getMSeekScrubbingChangeListener() {
            return this.mSeekScrubbingChangeListener;
        }

        public final Runnable getMStopLoadingRunnable() {
            return this.mStopLoadingRunnable;
        }

        public final TextView getMText() {
            return this.mText;
        }

        public final TextView getMTitle() {
            return this.mTitle;
        }

        public final MediaSession.Token getMToken() {
            return this.mToken;
        }

        public final TextView getMTotalTime() {
            return this.mTotalTime;
        }

        public final ImageView getMVolumeMax() {
            return this.mVolumeMax;
        }

        public final ImageView getMVolumeMin() {
            return this.mVolumeMin;
        }

        public final SeekBar getMVolumeSeekBar() {
            return this.mVolumeSeekBar;
        }

        public final VolumeSeekBarViewModel getMVolumeSeekBarViewModel() {
            return this.mVolumeSeekBarViewModel;
        }

        public final ConstraintLayout getMediaButtonConstraintLayout() {
            return this.mediaButtonConstraintLayout;
        }

        public final List<View> getMediaChildViews() {
            return this.mediaChildViews;
        }

        public final MediaData getMediaData() {
            return this.mediaData;
        }

        public final Runnable getRefreshPhotoCoverRunnable() {
            return this.refreshPhotoCoverRunnable;
        }

        public final ViewBlurProxy getViewBlurProxy() {
            return this.viewBlurProxy;
        }

        public final RelativeLayout getVolumeSeekLayout() {
            return this.volumeSeekLayout;
        }

        public final void initContainerLaunchOutlineRadius() {
            ((RecyclerView.ViewHolder) this).itemView.setClipToOutline(false);
            ((RecyclerView.ViewHolder) this).itemView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            FrameLayout frameLayout = this.mLightOverlay;
            if (frameLayout != null) {
                frameLayout.setOutlineProvider(null);
            }
            FrameLayout frameLayout2 = this.mLightOverlay;
            if (frameLayout2 != null) {
                frameLayout2.setClipToOutline(false);
            }
            clearContainerBackgroundSelfDrawnCornersIfOutlineClipped();
        }

        public final void invalidatePhotoCoverOutline() {
            ViewGroup viewGroup = this.mMediaCoverLayout;
            if (viewGroup != null) {
                viewGroup.invalidateOutline();
            }
            ViewGroup viewGroup2 = this.mMediaCoverLayout;
            if (viewGroup2 != null) {
                viewGroup2.invalidate();
            }
            ImageView imageView = this.mPhotoCover;
            if (imageView != null) {
                imageView.invalidate();
            }
        }

        public final void markSeekBarUserInteraction() {
            this.mSeekBarProgressBlockUntilMs = SystemClock.uptimeMillis() + 1000;
            OplusQsSeekBarObserver oplusQsSeekBarObserver = this.mSeekBarObserver;
            if (oplusQsSeekBarObserver != null) {
                oplusQsSeekBarObserver.lastUserSeekUptimeMs = SystemClock.uptimeMillis();
            }
        }

        public final void onDestroy() {
            SeekBarViewModel seekBarViewModel = this.mSeekBarViewModel;
            if (seekBarViewModel != null) {
                seekBarViewModel.onDestroy();
            }
            ImageButton imageButton = this.mMediaOutputBtn;
            if (imageButton != null) {
                imageButton.setImageDrawable(null);
            }
            ImageView imageView = this.mPhotoCover;
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
            AppCompatImageView appCompatImageView = this.mAppIcon;
            if (appCompatImageView != null) {
                appCompatImageView.setImageDrawable((Drawable) null);
            }
            ImageButton imageButton2 = this.mPreBtn;
            if (imageButton2 != null) {
                imageButton2.setOnClickListener(null);
            }
            ImageButton imageButton3 = this.mPreBtn;
            if (imageButton3 != null) {
                imageButton3.setImageDrawable(null);
            }
            ImageButton imageButton4 = this.mPlayOrPauseBtn;
            if (imageButton4 != null) {
                imageButton4.setOnClickListener(null);
            }
            ImageButton imageButton5 = this.mPlayOrPauseBtn;
            if (imageButton5 != null) {
                imageButton5.setImageDrawable(null);
            }
            ImageButton imageButton6 = this.mNextBtn;
            if (imageButton6 != null) {
                imageButton6.setOnClickListener(null);
            }
            ImageButton imageButton7 = this.mNextBtn;
            if (imageButton7 != null) {
                imageButton7.setImageDrawable(null);
            }
            ImageButton imageButton8 = this.mLocalFavoriteButton;
            if (imageButton8 != null) {
                imageButton8.setOnClickListener(null);
            }
            ImageButton imageButton9 = this.mLocalFavoriteButton;
            if (imageButton9 != null) {
                imageButton9.setImageDrawable(null);
            }
            ImageButton imageButton10 = this.mLocalLyricBtn;
            if (imageButton10 != null) {
                imageButton10.setOnClickListener(null);
            }
            ImageButton imageButton11 = this.mLocalLyricBtn;
            if (imageButton11 != null) {
                imageButton11.setImageDrawable(null);
            }
            ViewGroup viewGroup = this.mMediaCoverLayout;
            if (viewGroup != null) {
                viewGroup.setOnClickListener(null);
            }
            ViewGroup viewGroup2 = this.mMediaOutputBtnContainer;
            if (viewGroup2 != null) {
                viewGroup2.setOnClickListener(null);
            }
            ImageButton imageButton12 = this.mPlayOrPauseBtn;
            if (imageButton12 != null) {
                imageButton12.setVisibility(0);
            }
            this.mPreBtn = null;
            this.mPlayOrPauseBtn = null;
            this.mNextBtn = null;
            this.mBtnAction1 = null;
            this.mBtnAction2 = null;
            getIconAlphaSpringAnimatorSets().cancelAnimation();
            getIconHideAlphaSpringAnimatorSets().cancelAnimation();
            getIconScaleSpringAnimatorSets().cancelAnimation();
            getIconHideScaleSpringAnimatorSets().cancelAnimation();
            getIconAlphaSpringAnimatorSetsTwoToFive().cancelAnimation();
            getIconHideAlphaSpringAnimatorSetsTwoToFive().cancelAnimation();
            getIconScaleSpringAnimatorSetsTwoToFive().cancelAnimation();
            getIconHideScaleSpringAnimatorSetsTwoToFive().cancelAnimation();
            this.imageButtonsFive.clear();
            this.imageButtonsTwo.clear();
            OplusDotProgressBar oplusDotProgressBar = this.mDotLoading;
            if (oplusDotProgressBar != null) {
                oplusDotProgressBar.setVisibility(4);
            }
            OplusDotProgressBar oplusDotProgressBar2 = this.mOplusDotActionAnimation;
            if (oplusDotProgressBar2 != null) {
                oplusDotProgressBar2.setVisibility(4);
            }
            VolumeSeekBarViewModel volumeSeekBarViewModel = this.mVolumeSeekBarViewModel;
            if (volumeSeekBarViewModel != null) {
                volumeSeekBarViewModel.onViewDetached();
            }
            Companion companion = OplusMediaViewPagerAdapter.Companion;
            OplusMediaViewPagerAdapter.this.getClass();
            OplusMediaViewPagerAdapter.releaseHolderObserver(this);
            Job job = this.mIconJob;
            if (job != null) {
                Job.Key key = Job.Key;
                job.cancel((CancellationException) null);
            }
            this.mIconJob = null;
            this.mAppIconPair = null;
            ((RecyclerView.ViewHolder) this).itemView.setAccessibilityDelegate(null);
            ((RecyclerView.ViewHolder) this).itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            ViewParent parent = ((RecyclerView.ViewHolder) this).itemView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(((RecyclerView.ViewHolder) this).itemView);
            }
            this.mHandler.removeCallbacks(this.refreshPhotoCoverRunnable);
            this.mediaChildViews.clear();
            this.mContainerView.setBackground(null);
            FrameLayout frameLayout = this.mLightOverlay;
            if (frameLayout != null) {
                frameLayout.setBackground(null);
            }
            this.mLightOverlay = null;
            this.overLayStrokeBackground = null;
            this.lastIcon = null;
            stopPhysicsMove$1();
            this.itemViewOplusOutlineAdapter = null;
            this.itemViewOutlineLast = null;
            this.photoCoverOplusOutlineAdapter = null;
            this.photoCoverOutlineLast = null;
            this.lightOverlayOplusOutlineAdapter = null;
            this.lightOverlayOutlineLast = null;
        }

        public final void refreshItemMultiLightBackground() {
            MediaData mediaData = this.mediaData;
            MediaSession.Token token = mediaData != null ? mediaData.getToken() : null;
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (token != null) {
                Handler handler = this.mBgHandler;
                OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1 anonymousClass1 = new OplusMediaViewPagerAdapter$OplusMediaViewHolder$bindMediaView$1.AnonymousClass1(2);
                anonymousClass1.$mQsMediaData = oplusMediaViewPagerAdapter;
                anonymousClass1.this$0 = this;
                VarHandle.storeStoreFence();
                handler.post(anonymousClass1);
                return;
            }
            boolean zIsNightMode = OplusDarkModeUtil.isNightMode(oplusMediaViewPagerAdapter.mContext);
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "applyDefaultItemBackgroundLightParams, nightMode=" + zIsNightMode);
            if (oplusMediaViewPagerAdapter.enableStrokeLight && oplusMediaViewPagerAdapter.isSupportLightEffect) {
                OplusMediaItemBackgroundDrawable itemBackgroundDrawable = getItemBackgroundDrawable();
                if (itemBackgroundDrawable != null) {
                    itemBackgroundDrawable.applyDefaultShadowAndOutlineLight(zIsNightMode);
                }
            } else {
                OplusMediaItemBackgroundDrawable itemBackgroundDrawable2 = getItemBackgroundDrawable();
                if (itemBackgroundDrawable2 != null) {
                    itemBackgroundDrawable2.clearLight();
                }
            }
            updateSeekBarColor();
        }

        public final void resetAppIconOutlineRadius() {
            this.appIconOutlineRadius = this.appIconSteadyOutlineRadius;
            AppCompatImageView appCompatImageView = this.mAppIcon;
            if (appCompatImageView != null) {
                appCompatImageView.invalidateOutline();
            }
        }

        public final Pair resolveContainerOutlineParams() {
            Context context = ((RecyclerView.ViewHolder) this).itemView.getContext();
            float radius = this.containerDesignCornerRadius;
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, radius);
            if (roundParams != null) {
                radius = roundParams.getRadius();
            }
            return TuplesKt.to(Float.valueOf(radius), Float.valueOf(roundParams != null ? roundParams.getWeight() : 3.0f));
        }

        public final void restoreContainerOutlineRadius() {
            applySteadyContainerOutlineRadius();
            ensureItemViewOutlineProvider();
            FrameLayout frameLayout = this.mLightOverlay;
            if (frameLayout != null) {
                frameLayout.setOutlineProvider(new OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1(this, 2));
            }
            FrameLayout frameLayout2 = this.mLightOverlay;
            if (frameLayout2 != null) {
                frameLayout2.setClipToOutline(true);
            }
            FrameLayout frameLayout3 = this.mLightOverlay;
            if (frameLayout3 != null) {
                frameLayout3.invalidateOutline();
            }
            restoreLightOverlayDrawableCorner();
            clearContainerBackgroundSelfDrawnCornersIfOutlineClipped();
            ((RecyclerView.ViewHolder) this).itemView.invalidateOutline();
        }

        public final void restoreLightOverlayDrawableCorner() {
            if (OplusMediaViewPagerAdapter.this.isMiddleOrHighAnimLevel) {
                Pair pairResolveContainerOutlineParams = resolveContainerOutlineParams();
                float fFloatValue = ((Number) pairResolveContainerOutlineParams.component1()).floatValue();
                float fFloatValue2 = ((Number) pairResolveContainerOutlineParams.component2()).floatValue();
                OplusMediaItemBackgroundDrawable itemBackgroundDrawable = getItemBackgroundDrawable();
                if (itemBackgroundDrawable != null) {
                    itemBackgroundDrawable.setCornerParams(fFloatValue, fFloatValue2);
                }
            }
        }

        public final void setAllowedProgressUpdateListener(SeekBarViewModel.AllowedProgressUpdateListener allowedProgressUpdateListener) {
            this.allowedProgressUpdateListener = allowedProgressUpdateListener;
        }

        public final void setAppIconOutlineProvider() {
            AppCompatImageView appCompatImageView = this.mAppIcon;
            if (appCompatImageView == null) {
                return;
            }
            float dimension = ((RecyclerView.ViewHolder) this).itemView.getContext().getResources().getDimension(2131171041);
            OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(((RecyclerView.ViewHolder) this).itemView.getContext(), dimension);
            if (roundParams != null) {
                dimension = roundParams.getRadius();
            }
            this.appIconSteadyOutlineRadius = dimension;
            this.appIconOutlineRadius = dimension;
            this.appIconOutlineWeight = roundParams != null ? roundParams.getWeight() : 3.0f;
            appCompatImageView.setElevation(0.0f);
            appCompatImageView.setOutlineProvider(new OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1(this, 0));
            appCompatImageView.setClipToOutline(true);
        }

        public final void setLayoutVisibility(List list, boolean z) {
            if (list.size() > 4) {
                OplusDotProgressBar oplusDotProgressBar = this.mOplusDotActionAnimation;
                if (oplusDotProgressBar != null) {
                    oplusDotProgressBar.stopAnimation();
                }
                if (!this.needAnimation) {
                    ImageButton imageButton = this.mBtnAction2;
                    if (imageButton != null) {
                        imageButton.setVisibility(8);
                    }
                    FrameLayout frameLayout = this.mOplusMediaPanelContainerAction4;
                    if (frameLayout != null) {
                        frameLayout.setVisibility(8);
                    }
                }
                FrameLayout frameLayout2 = this.mActionPlayOrPauseFrameLayout;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(0);
                }
                ImageButton imageButton2 = this.mPlayOrPauseBtn;
                if (imageButton2 != null) {
                    imageButton2.setVisibility(0);
                }
                ImageButton imageButton3 = this.mPreBtn;
                if (imageButton3 != null) {
                    imageButton3.setVisibility(0);
                }
                ImageButton imageButton4 = this.mNextBtn;
                if (imageButton4 != null) {
                    imageButton4.setVisibility(0);
                    return;
                }
                return;
            }
            if (list.size() == 3) {
                OplusDotProgressBar oplusDotProgressBar2 = this.mOplusDotActionAnimation;
                if (oplusDotProgressBar2 != null) {
                    oplusDotProgressBar2.stopAnimation();
                }
                if (!this.needAnimation) {
                    ImageButton imageButton5 = this.mBtnAction2;
                    if (imageButton5 != null) {
                        imageButton5.setVisibility(8);
                    }
                    FrameLayout frameLayout3 = this.mOplusMediaPanelContainerAction4;
                    if (frameLayout3 != null) {
                        frameLayout3.setVisibility(8);
                    }
                }
                FrameLayout frameLayout4 = this.mActionPlayOrPauseFrameLayout;
                if (frameLayout4 != null) {
                    frameLayout4.setVisibility(0);
                }
                ImageButton imageButton6 = this.mPreBtn;
                if (imageButton6 != null) {
                    imageButton6.setVisibility(0);
                }
                ImageButton imageButton7 = this.mNextBtn;
                if (imageButton7 != null) {
                    imageButton7.setVisibility(0);
                }
                ImageButton imageButton8 = this.mPlayOrPauseBtn;
                if (imageButton8 != null) {
                    imageButton8.setVisibility(0);
                }
                ImageButton imageButton9 = this.mLocalFavoriteButton;
                if (imageButton9 != null) {
                    imageButton9.setVisibility(4);
                }
                ImageButton imageButton10 = this.mLocalLyricBtn;
                if (imageButton10 != null) {
                    imageButton10.setVisibility(4);
                    return;
                }
                return;
            }
            if (list.size() != 4) {
                if (list.isEmpty()) {
                    return;
                }
                OplusDotProgressBar oplusDotProgressBar3 = this.mDotLoading;
                if (oplusDotProgressBar3 != null) {
                    oplusDotProgressBar3.stopAnimation();
                }
                if (!this.needAnimation) {
                    ImageButton imageButton11 = this.mLocalFavoriteButton;
                    if (imageButton11 != null) {
                        imageButton11.setVisibility(8);
                    }
                    ImageButton imageButton12 = this.mLocalLyricBtn;
                    if (imageButton12 != null) {
                        imageButton12.setVisibility(8);
                    }
                    ImageButton imageButton13 = this.mPreBtn;
                    if (imageButton13 != null) {
                        imageButton13.setVisibility(8);
                    }
                    ImageButton imageButton14 = this.mNextBtn;
                    if (imageButton14 != null) {
                        imageButton14.setVisibility(8);
                    }
                    FrameLayout frameLayout5 = this.mActionPlayOrPauseFrameLayout;
                    if (frameLayout5 != null) {
                        frameLayout5.setVisibility(8);
                    }
                }
                FrameLayout frameLayout6 = this.mOplusMediaPanelContainerAction4;
                if (frameLayout6 != null) {
                    frameLayout6.setVisibility(0);
                }
                ImageButton imageButton15 = this.mBtnAction1;
                if (imageButton15 != null) {
                    imageButton15.setVisibility(8);
                }
                ImageButton imageButton16 = this.mBtnAction2;
                if (imageButton16 != null) {
                    imageButton16.setVisibility(8);
                    return;
                }
                return;
            }
            OplusDotProgressBar oplusDotProgressBar4 = this.mOplusDotActionAnimation;
            if (oplusDotProgressBar4 != null) {
                oplusDotProgressBar4.stopAnimation();
            }
            if (!this.needAnimation) {
                ImageButton imageButton17 = this.mBtnAction2;
                if (imageButton17 != null) {
                    imageButton17.setVisibility(8);
                }
                FrameLayout frameLayout7 = this.mOplusMediaPanelContainerAction4;
                if (frameLayout7 != null) {
                    frameLayout7.setVisibility(8);
                }
            }
            FrameLayout frameLayout8 = this.mActionPlayOrPauseFrameLayout;
            if (frameLayout8 != null) {
                frameLayout8.setVisibility(0);
            }
            ImageButton imageButton18 = this.mPreBtn;
            if (imageButton18 != null) {
                imageButton18.setVisibility(0);
            }
            ImageButton imageButton19 = this.mNextBtn;
            if (imageButton19 != null) {
                imageButton19.setVisibility(0);
            }
            ImageButton imageButton20 = this.mPlayOrPauseBtn;
            if (imageButton20 != null) {
                imageButton20.setVisibility(0);
            }
            if (z) {
                ImageButton imageButton21 = this.mLocalLyricBtn;
                if (imageButton21 != null) {
                    imageButton21.setVisibility(0);
                }
                ImageButton imageButton22 = this.mLocalFavoriteButton;
                if (imageButton22 != null) {
                    imageButton22.setVisibility(4);
                    return;
                }
                return;
            }
            ImageButton imageButton23 = this.mLocalFavoriteButton;
            if (imageButton23 != null) {
                imageButton23.setVisibility(0);
            }
            ImageButton imageButton24 = this.mLocalLyricBtn;
            if (imageButton24 != null) {
                imageButton24.setVisibility(4);
            }
        }

        public final void setMActionPlayOrPauseFrameLayout(FrameLayout frameLayout) {
            this.mActionPlayOrPauseFrameLayout = frameLayout;
        }

        public final void setMAppIcon(AppCompatImageView appCompatImageView) {
            this.mAppIcon = appCompatImageView;
        }

        public final void setMBtnAction1(ImageButton imageButton) {
            this.mBtnAction1 = imageButton;
        }

        public final void setMBtnAction2(ImageButton imageButton) {
            this.mBtnAction2 = imageButton;
        }

        public final void setMContainerView(View view) {
            this.mContainerView = view;
        }

        public final void setMController(MediaController mediaController) {
            this.mController = mediaController;
        }

        public final void setMCurrentTime(TextView textView) {
            this.mCurrentTime = textView;
        }

        public final void setMDefaultTip(TextView textView) {
            this.mDefaultTip = textView;
        }

        public final void setMDotLoading(OplusDotProgressBar oplusDotProgressBar) {
            this.mDotLoading = oplusDotProgressBar;
        }

        public final void setMHandler(Handler handler) {
            this.mHandler = handler;
        }

        public final void setMLightOverlay(FrameLayout frameLayout) {
            this.mLightOverlay = frameLayout;
        }

        public final void setMLocalFavoriteButton(ImageButton imageButton) {
            this.mLocalFavoriteButton = imageButton;
        }

        public final void setMLocalLyricBtn(ImageButton imageButton) {
            this.mLocalLyricBtn = imageButton;
        }

        public final void setMMediaCoverLayout(ViewGroup viewGroup) {
            this.mMediaCoverLayout = viewGroup;
        }

        public final void setMMediaOutputBtn(ImageButton imageButton) {
            this.mMediaOutputBtn = imageButton;
        }

        public final void setMMediaOutputBtnContainer(ViewGroup viewGroup) {
            this.mMediaOutputBtnContainer = viewGroup;
        }

        public final void setMMediaOutputText(TextView textView) {
            this.mMediaOutputText = textView;
        }

        public final void setMNextBtn(ImageButton imageButton) {
            this.mNextBtn = imageButton;
        }

        public final void setMOplusDotActionAnimation(OplusDotProgressBar oplusDotProgressBar) {
            this.mOplusDotActionAnimation = oplusDotProgressBar;
        }

        public final void setMOplusMediaPanelContainerAction4(FrameLayout frameLayout) {
            this.mOplusMediaPanelContainerAction4 = frameLayout;
        }

        public final void setMPendingProgress(boolean z) {
            this.mPendingProgress = z;
        }

        public final void setMPhotoBlurOverlay(ProgressiveBlurOverlay progressiveBlurOverlay) {
            this.mPhotoBlurOverlay = progressiveBlurOverlay;
        }

        public final void setMPhotoBlurOverlayParent(FrameLayout frameLayout) {
            this.mPhotoBlurOverlayParent = frameLayout;
        }

        public final void setMPhotoCover(ImageView imageView) {
            this.mPhotoCover = imageView;
        }

        public final void setMPlayOrPauseBtn(ImageButton imageButton) {
            this.mPlayOrPauseBtn = imageButton;
        }

        public final void setMPreBtn(ImageButton imageButton) {
            this.mPreBtn = imageButton;
        }

        public final void setMSeekBar(COUIIntentSeekBar cOUIIntentSeekBar) {
            this.mSeekBar = cOUIIntentSeekBar;
        }

        public final void setMSeekBarObserver(OplusQsSeekBarObserver oplusQsSeekBarObserver) {
            this.mSeekBarObserver = oplusQsSeekBarObserver;
        }

        public final void setMSeekBarPatent(RelativeLayout relativeLayout) {
            this.mSeekBarPatent = relativeLayout;
        }

        public final void setMSeekBarViewModel(SeekBarViewModel seekBarViewModel) {
            this.mSeekBarViewModel = seekBarViewModel;
        }

        public final void setMSeekScrubbingChangeListener(SeekBarViewModel.ScrubbingChangeListener scrubbingChangeListener) {
            this.mSeekScrubbingChangeListener = scrubbingChangeListener;
        }

        public final void setMText(TextView textView) {
            this.mText = textView;
        }

        public final void setMTitle(TextView textView) {
            this.mTitle = textView;
        }

        public final void setMToken(MediaSession.Token token) {
            this.mToken = token;
        }

        public final void setMTotalTime(TextView textView) {
            this.mTotalTime = textView;
        }

        public final void setMVolumeMax(ImageView imageView) {
            this.mVolumeMax = imageView;
        }

        public final void setMVolumeMin(ImageView imageView) {
            this.mVolumeMin = imageView;
        }

        public final void setMVolumeSeekBar(SeekBar seekBar) {
            this.mVolumeSeekBar = seekBar;
        }

        public final void setMVolumeSeekBarViewModel(VolumeSeekBarViewModel volumeSeekBarViewModel) {
            this.mVolumeSeekBarViewModel = volumeSeekBarViewModel;
        }

        public final void setMediaButtonConstraintLayout(ConstraintLayout constraintLayout) {
            this.mediaButtonConstraintLayout = constraintLayout;
        }

        public final void setMediaChildViews(List<View> list) {
            this.mediaChildViews = list;
        }

        public final void setMediaData(MediaData mediaData) {
            this.mediaData = mediaData;
        }

        public final void setNewLayout(boolean z) {
            this.isNewLayout = z;
        }

        public final void setSemanticButton(ImageButton imageButton, MediaAction mediaAction, MediaData mediaData, OplusMediaActions oplusMediaActions, boolean z) {
            Drawable icon;
            if (mediaAction == null) {
                if (imageButton != null) {
                    imageButton.setContentDescription(null);
                }
                if (imageButton != null) {
                    imageButton.setEnabled(false);
                    return;
                }
                return;
            }
            this.isNewLayout = z;
            if (Intrinsics.areEqual(imageButton, this.mPlayOrPauseBtn) || Intrinsics.areEqual(imageButton, this.mBtnAction1)) {
                this.mHandler.removeCallbacks(this.mLoadingRunnable);
                this.mHandler.removeCallbacks(this.mStopLoadingRunnable);
                this.mStopLoadingRunnable.run();
            }
            Icon icon2 = mediaAction.getMediaActionEx().getIcon();
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            if (icon2 == null || (icon = icon2.loadDrawableAsUser(oplusMediaViewPagerAdapter.mContext, OsBinderCacheUtils.getCurrentUserId())) == null) {
                icon = mediaAction.getIcon();
            }
            if (icon != null) {
                MediaAction playPauseOrCustom = oplusMediaActions.getPlayPauseOrCustom();
                if ((playPauseOrCustom != null ? playPauseOrCustom.getAction() : null) == null && (icon instanceof Animatable)) {
                    ((Animatable) icon).start();
                }
                int dimensionPixelSize = oplusMediaViewPagerAdapter.mContext.getResources().getDimensionPixelSize(2131171053);
                Icon icon3 = mediaAction.getMediaActionEx().getIcon();
                if (!oplusMediaViewPagerAdapter.getSpecialPackageNames().contains(icon3 != null ? icon3.getResPackage() : null) || icon.getIntrinsicHeight() <= dimensionPixelSize) {
                    if (imageButton != null) {
                        imageButton.setScaleType(ImageView.ScaleType.CENTER);
                    }
                    int dimensionPixelSize2 = oplusMediaViewPagerAdapter.mContext.getResources().getDimensionPixelSize(2131171054);
                    if (imageButton != null) {
                        imageButton.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
                    }
                } else {
                    if (imageButton != null) {
                        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                    int dimensionPixelSize3 = oplusMediaViewPagerAdapter.mContext.getResources().getDimensionPixelSize(2131171055);
                    int dimensionPixelSize4 = oplusMediaViewPagerAdapter.mContext.getResources().getDimensionPixelSize(2131171056);
                    if (imageButton != null) {
                        imageButton.setPadding(dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize3, dimensionPixelSize4);
                    }
                }
                if (imageButton != null) {
                    imageButton.setImageDrawable(icon);
                }
            }
            if (imageButton != null) {
                imageButton.setVisibility(0);
            }
            if (imageButton != null) {
                imageButton.setContentDescription(mediaAction.getContentDescription());
            }
            if (imageButton != null) {
                imageButton.setEnabled(mediaAction.getAction() != null);
            }
            if (imageButton != null) {
                setSemanticButton.1 r10 = new setSemanticButton.1();
                r10.$imageButton = imageButton;
                r10.this$0 = this;
                r10.$data = mediaData;
                r10.$mediaAction = mediaAction;
                r10.$oplusMediaActions = oplusMediaActions;
                VarHandle.storeStoreFence();
                imageButton.setOnClickListener(r10);
            }
        }

        public final void setViewBlurProxy(ViewBlurProxy viewBlurProxy) {
            this.viewBlurProxy = viewBlurProxy;
        }

        public final void setVolumeSeekLayout(RelativeLayout relativeLayout) {
            this.volumeSeekLayout = relativeLayout;
        }

        public final void showNoPlayView() {
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "showNoPlayView");
            updateMediaOutputBtn$1(((Number) OplusQsVolumeIconView.Companion.getMusicStreamRoute().get()).intValue());
            ImageButton imageButton = this.mPreBtn;
            if (imageButton != null) {
                resetNoPlayState(imageButton, 2131236356, true);
            }
            ImageButton imageButton2 = this.mPlayOrPauseBtn;
            if (imageButton2 != null) {
                resetNoPlayState(imageButton2, 2131236355, false);
            }
            ImageButton imageButton3 = this.mNextBtn;
            if (imageButton3 != null) {
                resetNoPlayState(imageButton3, 2131236353, true);
            }
            FeatureOption.INSTANCE.getClass();
            boolean zIsExpRegion = FeatureOption.isExpRegion();
            ImageButton imageButton4 = this.mLocalLyricBtn;
            if (zIsExpRegion) {
                if (imageButton4 != null) {
                    imageButton4.setVisibility(4);
                }
                ImageButton imageButton5 = this.mLocalFavoriteButton;
                if (imageButton5 != null) {
                    imageButton5.setVisibility(4);
                }
            } else {
                if (imageButton4 != null) {
                    resetNoPlayState(imageButton4, 2131235983, true);
                }
                ImageButton imageButton6 = this.mLocalFavoriteButton;
                if (imageButton6 != null) {
                    resetNoPlayState(imageButton6, 2131236357, true);
                }
            }
            TextView textView = this.mDefaultTip;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.mTitle;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = this.mText;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "resetProgressSeekbar");
            this.mPendingProgress = false;
            SeekBarViewModel seekBarViewModel = this.mSeekBarViewModel;
            if (seekBarViewModel != null) {
                seekBarViewModel.setListening(false);
            }
            SeekBarViewModel seekBarViewModel2 = this.mSeekBarViewModel;
            if (seekBarViewModel2 != null) {
                seekBarViewModel2.clearController();
            }
            if (OplusMediaViewPagerAdapter.this.mContext.getResources().getConfiguration().getLayoutDirection() == 1) {
                ImageButton imageButton7 = this.mLocalFavoriteButton;
                if (imageButton7 != null) {
                    imageButton7.setImageResource(2131235983);
                }
                ImageButton imageButton8 = this.mLocalLyricBtn;
                if (imageButton8 != null) {
                    imageButton8.setImageResource(2131236357);
                }
            }
        }

        public final void stopPhysicsMove$1() {
            OplusNotificationLogger.logMediaPanel(OplusMediaViewPagerAdapter.TAG, "stopClikAnim stopPhysicsMove");
            COUIIntentSeekBar cOUIIntentSeekBar = this.mSeekBar;
            if (cOUIIntentSeekBar != null) {
                cOUIIntentSeekBar.stopClikAnim();
            }
            COUIIntentSeekBar cOUIIntentSeekBar2 = this.mSeekBar;
            if (cOUIIntentSeekBar2 != null) {
                cOUIIntentSeekBar2.stopPhysicsMove();
            }
        }

        public final void updateAppIconLaunchRadius(float f) {
            if (f <= 0.0f) {
                return;
            }
            this.appIconOutlineRadius = f;
            AppCompatImageView appCompatImageView = this.mAppIcon;
            if (appCompatImageView != null) {
                appCompatImageView.invalidateOutline();
            }
        }

        public final void updateMediaOutputBtn$1(int i) {
            if (OplusMediaViewPagerAdapter.this.inAudioSharing) {
                ImageButton imageButton = this.mMediaOutputBtn;
                if (imageButton != null) {
                    imageButton.setImageResource(2131237115);
                    return;
                }
                return;
            }
            if (i == 1) {
                ImageButton imageButton2 = this.mMediaOutputBtn;
                if (imageButton2 != null) {
                    imageButton2.setImageResource(2131237118);
                    return;
                }
                return;
            }
            ImageButton imageButton3 = this.mMediaOutputBtn;
            if (i == 0) {
                if (imageButton3 != null) {
                    imageButton3.setImageResource(2131237169);
                }
            } else if (imageButton3 != null) {
                imageButton3.setImageResource(2131237113);
            }
        }

        public final void updatePhotoCoverLaunchRadius(float f) {
            if (OplusMediaViewPagerAdapter.this.photoClipForLaunchAnim && f > 0.0f) {
                this.photoCoverLaunchRadius = f;
                ViewGroup viewGroup = this.mMediaCoverLayout;
                if (viewGroup != null) {
                    viewGroup.invalidateOutline();
                }
            }
        }

        public final void updateSeekBarColor() {
            boolean z = PowerSavingModeObserver.Companion.getInstance().batterySavingMode;
            QsColorUtil.INSTANCE.getClass();
            boolean z2 = QsColorUtil.isSuperPowerSaveState;
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            Context applicationContext = oplusMediaViewPagerAdapter.mContext.getApplicationContext();
            Context context = oplusMediaViewPagerAdapter.mContext;
            int i = OplusQSDetail.$r8$clinit;
            int attrColor = OplusContextUtil.getAttrColor(QsColorUtil.createDarkColorContext(applicationContext, QsColorUtil.isNeedDarkThemeColor(context, false)), 2130969283);
            if (z2) {
                COUIIntentSeekBar cOUIIntentSeekBar = this.mSeekBar;
                if (cOUIIntentSeekBar != null) {
                    cOUIIntentSeekBar.setSeekBarColors(ColorStateList.valueOf(attrColor), ColorStateList.valueOf(-1), (ColorStateList) null);
                    return;
                }
                return;
            }
            if (!oplusMediaViewPagerAdapter.isSupportLightEffect || z) {
                COUIIntentSeekBar cOUIIntentSeekBar2 = this.mSeekBar;
                if (cOUIIntentSeekBar2 != null) {
                    cOUIIntentSeekBar2.setSeekBarColors(ColorStateList.valueOf(attrColor), ColorStateList.valueOf(oplusMediaViewPagerAdapter.primaryColor80), (ColorStateList) null);
                    return;
                }
                return;
            }
            COUIIntentSeekBar cOUIIntentSeekBar3 = this.mSeekBar;
            if (cOUIIntentSeekBar3 != null) {
                cOUIIntentSeekBar3.setConicLightColor(oplusMediaViewPagerAdapter.primaryColor80);
            }
            COUIIntentSeekBar cOUIIntentSeekBar4 = this.mSeekBar;
            if (cOUIIntentSeekBar4 != null) {
                cOUIIntentSeekBar4.setSeekBarColors(ColorStateList.valueOf(attrColor), (ColorStateList) null, (ColorStateList) null);
            }
        }

        public final void updateTextContent(Integer num) {
            OplusMediaDataEx mediaDataEx;
            OplusMediaLyricData lyricData;
            if (OplusMediaViewPagerAdapter.this.isShowMediaOutput) {
                return;
            }
            if (num == null) {
                LogUtil.w$default(8, LogUtil.TAG_MEDIA, OplusMediaViewPagerAdapter.TAG, "update qs text content, position is null.");
                return;
            }
            MediaData mediaData = this.mediaData;
            if (mediaData != null && (mediaDataEx = mediaData.getMediaDataEx()) != null && (lyricData = mediaDataEx.getLyricData()) != null && lyricData.isEnabled(32)) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setText(lyricData.getCurrentLyric(num.intValue()));
                }
                TextView textView2 = this.mText;
                if (textView2 != null) {
                    textView2.setText(lyricData.getSongInfo());
                    return;
                }
                return;
            }
            MediaData mediaData2 = this.mediaData;
            if (mediaData2 != null) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setText(mediaData2.getSong());
                }
                TextView textView4 = this.mText;
                if (textView4 != null) {
                    textView4.setText(mediaData2.getArtist());
                }
            }
        }

        public final void updateViewColor$1() {
            int color;
            boolean zIsWallpaperBlurDisable = ScrimUtil.isWallpaperBlurDisable();
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = OplusMediaViewPagerAdapter.this;
            boolean zIsNightMode = OplusDarkModeUtil.isNightMode(oplusMediaViewPagerAdapter.mContext);
            if (QsColorUtil.isGlobalThemeApplied(oplusMediaViewPagerAdapter.mContext)) {
                color = oplusMediaViewPagerAdapter.mContext.getColor(2131103706);
            } else if (zIsWallpaperBlurDisable) {
                color = !zIsNightMode ? OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969314) : oplusMediaViewPagerAdapter.mContext.getColor(2131103287);
            } else {
                color = QsColorUtil.isNeedDarkThemeColor(oplusMediaViewPagerAdapter.mContext, false) ? oplusMediaViewPagerAdapter.mContext.getColor(2131103287) : OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969314);
            }
            TextView textView = this.mDefaultTip;
            if (textView != null) {
                textView.setTextColor(color);
            }
            TextView textView2 = this.mTitle;
            if (textView2 != null) {
                textView2.setTextColor(color);
            }
            TextView textView3 = this.mText;
            if (textView3 != null) {
                textView3.setTextColor(color);
            }
            if (QsColorUtil.isGlobalThemeApplied(oplusMediaViewPagerAdapter.mContext)) {
                int attrColor = OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969293);
                ImageButton imageButton = this.mMediaOutputBtn;
                if (imageButton != null) {
                    setTintList(imageButton, ColorStateList.valueOf(attrColor));
                }
                TextView textView4 = this.mMediaOutputText;
                if (textView4 != null) {
                    textView4.setTextColor(ColorStateList.valueOf(attrColor));
                }
                ImageView imageView = this.mVolumeMin;
                if (imageView != null) {
                    imageView.setImageTintList(ColorStateList.valueOf(attrColor));
                }
                ImageView imageView2 = this.mVolumeMax;
                if (imageView2 != null) {
                    imageView2.setImageTintList(ColorStateList.valueOf(attrColor));
                }
                TextView textView5 = this.mCurrentTime;
                if (textView5 != null) {
                    textView5.setTextColor(ColorStateList.valueOf(attrColor));
                }
                TextView textView6 = this.mTotalTime;
                if (textView6 != null) {
                    textView6.setTextColor(ColorStateList.valueOf(attrColor));
                }
                SeekBar seekBar = this.mVolumeSeekBar;
                if (seekBar != null) {
                    seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969283)));
                }
                SeekBar seekBar2 = this.mVolumeSeekBar;
                if (seekBar2 != null) {
                    seekBar2.setProgressTintList(ColorStateList.valueOf(OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969314)));
                }
                SeekBar seekBar3 = this.mVolumeSeekBar;
                if (seekBar3 != null) {
                    seekBar3.setThumbTintList(ColorStateList.valueOf(OplusContextUtil.getAttrColor(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), 2130969314)));
                }
            } else if (QsColorUtil.isNeedDarkThemeColor(oplusMediaViewPagerAdapter.mContext, false)) {
                ImageButton imageButton2 = this.mMediaOutputBtn;
                if (imageButton2 != null) {
                    setTintList(imageButton2, ColorStateList.valueOf(oplusMediaViewPagerAdapter.mContext.getColor(2131100939)));
                }
                TextView textView7 = this.mMediaOutputText;
                if (textView7 != null) {
                    textView7.setTextColor(ColorStateList.valueOf(oplusMediaViewPagerAdapter.mContext.getColor(2131100939)));
                }
                ImageView imageView3 = this.mVolumeMin;
                if (imageView3 != null) {
                    imageView3.setImageTintList(ColorStateList.valueOf(oplusMediaViewPagerAdapter.mContext.getColor(2131100939)));
                }
                ImageView imageView4 = this.mVolumeMax;
                if (imageView4 != null) {
                    imageView4.setImageTintList(ColorStateList.valueOf(oplusMediaViewPagerAdapter.mContext.getColor(2131100939)));
                }
                OplusDotProgressBar oplusDotProgressBar = this.mDotLoading;
                if (oplusDotProgressBar != null) {
                    oplusDotProgressBar.setDotBackground(oplusMediaViewPagerAdapter.mContext.getColor(2131100939));
                }
                OplusDotProgressBar oplusDotProgressBar2 = this.mOplusDotActionAnimation;
                if (oplusDotProgressBar2 != null) {
                    oplusDotProgressBar2.setDotBackground(oplusMediaViewPagerAdapter.mContext.getColor(2131100939));
                }
            }
            Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(oplusMediaViewPagerAdapter.mContext.getApplicationContext(), QsColorUtil.isNeedDarkThemeColor(oplusMediaViewPagerAdapter.mContext, false));
            ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}, new int[]{-16842910}}, new int[]{COUIContextUtil.getAttrColor(contextCreateDarkColorContext, 2130969314), COUIContextUtil.getAttrColor(contextCreateDarkColorContext, 2130969282)});
            ImageButton imageButton3 = this.mLocalFavoriteButton;
            if (imageButton3 != null) {
                setTintList(imageButton3, colorStateList);
            }
            ImageButton imageButton4 = this.mBtnAction2;
            if (imageButton4 != null) {
                setTintList(imageButton4, colorStateList);
            }
            ImageButton imageButton5 = this.mBtnAction1;
            if (imageButton5 != null) {
                setTintList(imageButton5, colorStateList);
            }
            ImageButton imageButton6 = this.mPreBtn;
            if (imageButton6 != null) {
                setTintList(imageButton6, colorStateList);
            }
            ImageButton imageButton7 = this.mPlayOrPauseBtn;
            if (imageButton7 != null) {
                setTintList(imageButton7, colorStateList);
            }
            ImageButton imageButton8 = this.mNextBtn;
            if (imageButton8 != null) {
                setTintList(imageButton8, colorStateList);
            }
            ImageButton imageButton9 = this.mLocalLyricBtn;
            if (imageButton9 != null) {
                setTintList(imageButton9, colorStateList);
            }
        }
    }

    public OplusMediaViewPagerAdapter(Context context, SeekBarViewModel.Factory factory, CoroutineScope coroutineScope) {
        this.mContext = context;
        this.seekBarViewModelFactory = factory;
        this.scope = coroutineScope;
        boolean zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        this.isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        int i = 0;
        this.isSupportLightEffect = FeatureOption.getEnableMaterialFeature() && zIsMiddleOrHighAnimLevel && FeatureOption.getEnableLiveAlertEffectLight();
        OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0 oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0 = new OplusMediaViewPagerAdapter$$ExternalSyntheticLambda0(i);
        oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.specialPackageNames$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaViewPagerAdapter$$ExternalSyntheticLambda0);
        OplusMediaViewPagerAdapter$lightAvailabilityListener$1 oplusMediaViewPagerAdapter$lightAvailabilityListener$1 = new OplusMediaViewPagerAdapter$lightAvailabilityListener$1(i);
        oplusMediaViewPagerAdapter$lightAvailabilityListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.lightAvailabilityListener = oplusMediaViewPagerAdapter$lightAvailabilityListener$1;
        COUIMaterialStateManager.INSTANCE.getClass();
        this.enableStrokeLight = COUIMaterialStateManager.MATERIAL_STROKE_ENABLE;
        NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(oplusMediaViewPagerAdapter$lightAvailabilityListener$1);
        this.oplusSeparateNotificationAndQSState = (OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class);
    }

    public static void ensureSeekBarScrubbingListener(OplusMediaViewHolder oplusMediaViewHolder) {
        SeekBarViewModel mSeekBarViewModel;
        if (oplusMediaViewHolder.getMSeekScrubbingChangeListener() == null) {
            ensureSeekBarScrubbingListener.1 r0 = new ensureSeekBarScrubbingListener.1();
            r0.$holder = oplusMediaViewHolder;
            VarHandle.storeStoreFence();
            oplusMediaViewHolder.setMSeekScrubbingChangeListener(r0);
        }
        SeekBarViewModel.ScrubbingChangeListener mSeekScrubbingChangeListener = oplusMediaViewHolder.getMSeekScrubbingChangeListener();
        if (mSeekScrubbingChangeListener == null || (mSeekBarViewModel = oplusMediaViewHolder.getMSeekBarViewModel()) == null) {
            return;
        }
        mSeekBarViewModel.setScrubbingChangeListener(mSeekScrubbingChangeListener);
    }

    public static final float getPhotoCoverSmoothRadius(Context context) {
        return Companion.getPhotoCoverSmoothRadius(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> getSpecialPackageNames() {
        return (Set) this.specialPackageNames$delegate.getValue();
    }

    public static void releaseHolderObserver(OplusMediaViewHolder oplusMediaViewHolder) {
        SeekBarViewModel mSeekBarViewModel;
        SeekBarViewModel mSeekBarViewModel2;
        LiveData progress;
        OplusNotificationLogger.logMediaPanel(TAG, "releaseHolderObserver");
        OplusQsSeekBarObserver mSeekBarObserver = oplusMediaViewHolder.getMSeekBarObserver();
        if (mSeekBarObserver != null && (mSeekBarViewModel2 = oplusMediaViewHolder.getMSeekBarViewModel()) != null && (progress = mSeekBarViewModel2.getProgress()) != null) {
            progress.removeObserver(mSeekBarObserver);
        }
        SeekBarViewModel.ScrubbingChangeListener mSeekScrubbingChangeListener = oplusMediaViewHolder.getMSeekScrubbingChangeListener();
        if (mSeekScrubbingChangeListener != null && (mSeekBarViewModel = oplusMediaViewHolder.getMSeekBarViewModel()) != null) {
            mSeekBarViewModel.removeScrubbingChangeListener(mSeekScrubbingChangeListener);
        }
        oplusMediaViewHolder.setMSeekScrubbingChangeListener(null);
        oplusMediaViewHolder.getMHandler().removeCallbacks(oplusMediaViewHolder.getMLoadingRunnable());
        oplusMediaViewHolder.getMHandler().removeCallbacks(oplusMediaViewHolder.getMStopLoadingRunnable());
        SeekBarViewModel mSeekBarViewModel3 = oplusMediaViewHolder.getMSeekBarViewModel();
        if (mSeekBarViewModel3 != null) {
            mSeekBarViewModel3.setAllowedProgressUpdateListener((SeekBarViewModel.AllowedProgressUpdateListener) null);
        }
        oplusMediaViewHolder.getMMusicRouteChange().detach();
    }

    public final void applyPhotoCoverClipPolicyToHolders() {
        for (OplusMediaViewHolder oplusMediaViewHolder : this.mViewHolderList) {
            if (this.viewPagerScrolling) {
                oplusMediaViewHolder.applySteadyContainerOutlineRadius();
                oplusMediaViewHolder.ensureItemViewOutlineProvider();
                oplusMediaViewHolder.restoreLightOverlayDrawableCorner();
                ((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView.invalidateOutline();
            } else {
                oplusMediaViewHolder.applyPhotoCoverLayoutClip();
            }
        }
    }

    public final void clearHolder() {
        for (OplusMediaViewHolder oplusMediaViewHolder : this.mViewHolderList) {
            oplusMediaViewHolder.onDestroy();
            OplusQsMediaItemViewCache.INSTANCE.saveItemView(((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView);
        }
        this.mViewHolderList.clear();
    }

    public final void delegateCoverClipToMasterClip() {
        if (this.coverClipDelegatedToMasterClip) {
            return;
        }
        this.coverClipDelegatedToMasterClip = true;
        if (isLandscape$2()) {
            return;
        }
        Iterator<T> it = this.mViewHolderList.iterator();
        while (it.hasNext()) {
            ((OplusMediaViewHolder) it.next()).clearPhotoCoverLaunchOutlineClip();
        }
    }

    public final int getDefaultPhotoDrawable() {
        if (!QsColorUtil.isGlobalThemeApplied(this.mContext) && !ScrimUtil.isLowGaussianLevel(this.mContext) && !GaussBlurUtils.isQSGaussBlurDisabled()) {
            return isLandscape$2() ? 2131236359 : 2131236360;
        }
        if (QsColorUtil.isNeedDarkThemeColor(this.mContext, false)) {
            return isLandscape$2() ? 2131236361 : 2131236362;
        }
        if (OplusDarkModeUtil.isNightMode(this.mContext)) {
            return isLandscape$2() ? 2131236361 : 2131236362;
        }
        return isLandscape$2() ? 2131236358 : 2131236363;
    }

    public int getItemCount() {
        List<MediaData> list = this.mMediaDataList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final OplusMediaViewHolder getViewHolderByPosition(int i) {
        Object obj = null;
        if (i < 0 || i >= this.mViewHolderList.size()) {
            return null;
        }
        for (Object obj2 : this.mViewHolderList) {
            OplusMediaViewHolder oplusMediaViewHolder = (OplusMediaViewHolder) obj2;
            if (oplusMediaViewHolder.getBindingAdapterPosition() == i || oplusMediaViewHolder.getAbsoluteAdapterPosition() == i) {
                obj = obj2;
                break;
            }
        }
        return (OplusMediaViewHolder) obj;
    }

    public final void invalidatePhotoCoverOutlineOnScroll() {
        for (OplusMediaViewHolder oplusMediaViewHolder : this.mViewHolderList) {
            if (!this.photoClipForLaunchAnim || this.viewPagerScrolling) {
                oplusMediaViewHolder.applySteadyContainerOutlineRadius();
                oplusMediaViewHolder.ensureItemViewOutlineProvider();
                oplusMediaViewHolder.restoreLightOverlayDrawableCorner();
                ((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView.invalidateOutline();
            }
            oplusMediaViewHolder.invalidatePhotoCoverOutline();
            ((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView.invalidateOutline();
        }
    }

    public final boolean isLandscape$2() {
        boolean z = this.mContext.getResources().getConfiguration().orientation == 2;
        FeatureOption.INSTANCE.getClass();
        return z && !((FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet());
    }

    public final void notifyData(List<MediaData> list) {
        this.mMediaDataList = list;
    }

    public void onBindViewHolder(OplusMediaViewHolder oplusMediaViewHolder, int i) throws Throwable {
        MediaData mediaData;
        List<MediaData> list = this.mMediaDataList;
        if (list == null || !list.isEmpty()) {
            List<MediaData> list2 = this.mMediaDataList;
            OplusNotificationLogger.logMediaPanel(TAG, "onBindViewHolder ----position is -->" + i + " -----current media data is " + ((list2 == null || (mediaData = list2.get(i)) == null) ? null : MediaDataExtKt.printInfo(mediaData, true)));
            List<MediaData> list3 = this.mMediaDataList;
            oplusMediaViewHolder.bind(list3 != null ? list3.get(i) : null);
        }
    }

    public OplusMediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        VolumeSeekBarViewModel mVolumeSeekBarViewModel;
        SeekBarViewModel mSeekBarViewModel;
        OplusNotificationLogger.logMediaPanel(TAG, " onCreateViewHolder");
        Context context = this.mContext;
        Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false));
        View itemView = OplusQsMediaItemViewCache.INSTANCE.getItemView();
        if (itemView == null) {
            itemView = LayoutInflater.from(contextCreateDarkColorContext).inflate(2131559522, viewGroup, false);
        }
        OplusMediaViewHolder oplusMediaViewHolder = new OplusMediaViewHolder(itemView);
        if (!this.mViewHolderList.contains(oplusMediaViewHolder)) {
            this.mViewHolderList.add(oplusMediaViewHolder);
        }
        oplusMediaViewHolder.applyPhotoCoverLayoutClip();
        SeekBarViewModel seekBarViewModelCreate = this.seekBarViewModelFactory.create();
        seekBarViewModelCreate.setInterceptUpdateWhenTrack(true);
        oplusMediaViewHolder.setMSeekBarViewModel(seekBarViewModelCreate);
        oplusMediaViewHolder.setMSeekBarObserver(new OplusQsSeekBarObserver(this.mContext, oplusMediaViewHolder));
        ensureSeekBarScrubbingListener(oplusMediaViewHolder);
        COUIIntentSeekBar mSeekBar = oplusMediaViewHolder.getMSeekBar();
        if (mSeekBar != null && (mSeekBarViewModel = oplusMediaViewHolder.getMSeekBarViewModel()) != null) {
            mSeekBarViewModel.attachTouchSeekBarHandlers(mSeekBar);
        }
        oplusMediaViewHolder.setMVolumeSeekBarViewModel(new VolumeSeekBarViewModel(this.mContext, oplusMediaViewHolder.getMBgHandler()));
        SeekBar mVolumeSeekBar = oplusMediaViewHolder.getMVolumeSeekBar();
        if (mVolumeSeekBar != null && (mVolumeSeekBarViewModel = oplusMediaViewHolder.getMVolumeSeekBarViewModel()) != null) {
            mVolumeSeekBarViewModel.onViewAttached(mVolumeSeekBar);
        }
        return oplusMediaViewHolder;
    }

    public final void onDestroy() {
        NotificationSpotLightEffectManager.removeOnSpotLightAvailabilityChangedListener(this.lightAvailabilityListener);
        this.mIsDestroyed = true;
        List<MediaData> list = this.mMediaDataList;
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
        clearHolder();
    }

    public void onViewAttachedToWindow(OplusMediaViewHolder oplusMediaViewHolder) {
        SeekBarViewModel mSeekBarViewModel;
        LiveData progress;
        super.onViewAttachedToWindow(oplusMediaViewHolder);
        OplusNotificationLogger.logMediaPanel(TAG, "onViewAttachedToWindow");
        OplusQsSeekBarObserver mSeekBarObserver = oplusMediaViewHolder.getMSeekBarObserver();
        if (mSeekBarObserver != null && (mSeekBarViewModel = oplusMediaViewHolder.getMSeekBarViewModel()) != null && (progress = mSeekBarViewModel.getProgress()) != null) {
            progress.observeForever(mSeekBarObserver);
        }
        SeekBarViewModel mSeekBarViewModel2 = oplusMediaViewHolder.getMSeekBarViewModel();
        if (mSeekBarViewModel2 != null) {
            mSeekBarViewModel2.setAllowedProgressUpdateListener(oplusMediaViewHolder.getAllowedProgressUpdateListener());
        }
        ensureSeekBarScrubbingListener(oplusMediaViewHolder);
        oplusMediaViewHolder.getMMusicRouteChange().attach();
    }

    public void onViewDetachedFromWindow(OplusMediaViewHolder oplusMediaViewHolder) {
        super.onViewDetachedFromWindow(oplusMediaViewHolder);
        OplusNotificationLogger.logMediaPanel(TAG, "onViewDetachedFromWindow");
        releaseHolderObserver(oplusMediaViewHolder);
    }

    public final void setIClickListener(IClickListener iClickListener) {
        this.mClickListener = iClickListener;
    }

    public final void setPhotoClipForLaunchAnimation(boolean z) {
        if (this.photoClipForLaunchAnim == z) {
            return;
        }
        this.photoClipForLaunchAnim = z;
        if (!z) {
            this.coverClipDelegatedToMasterClip = false;
            Iterator<T> it = this.mViewHolderList.iterator();
            while (it.hasNext()) {
                ((OplusMediaViewHolder) it.next()).restoreContainerOutlineRadius();
            }
            applyPhotoCoverClipPolicyToHolders();
            return;
        }
        this.coverClipDelegatedToMasterClip = false;
        Iterator<T> it2 = this.mViewHolderList.iterator();
        while (it2.hasNext()) {
            ((OplusMediaViewHolder) it2.next()).initContainerLaunchOutlineRadius();
        }
        Iterator<T> it3 = this.mViewHolderList.iterator();
        while (it3.hasNext()) {
            ((OplusMediaViewHolder) it3.next()).applyPhotoCoverLaunchOutline();
        }
    }

    public final void setShowMediaOutput(boolean z) {
        this.isShowMediaOutput = z;
    }

    public final void setViewPagerScrolling(boolean z) {
        if (this.viewPagerScrolling == z) {
            return;
        }
        this.viewPagerScrolling = z;
        if (!this.photoClipForLaunchAnim) {
            applyPhotoCoverClipPolicyToHolders();
            return;
        }
        for (OplusMediaViewHolder oplusMediaViewHolder : this.mViewHolderList) {
            if (z) {
                oplusMediaViewHolder.applySteadyContainerOutlineRadius();
                oplusMediaViewHolder.ensureItemViewOutlineProvider();
                oplusMediaViewHolder.restoreLightOverlayDrawableCorner();
                ((RecyclerView.ViewHolder) oplusMediaViewHolder).itemView.invalidateOutline();
                if (!isLandscape$2()) {
                    oplusMediaViewHolder.clearPhotoCoverLaunchOutlineClip();
                    this.coverClipDelegatedToMasterClip = true;
                }
            } else {
                oplusMediaViewHolder.initContainerLaunchOutlineRadius();
                if (this.coverClipDelegatedToMasterClip && !isLandscape$2()) {
                    oplusMediaViewHolder.clearPhotoCoverLaunchOutlineClip();
                } else if (this.photoClipForLaunchAnim) {
                    oplusMediaViewHolder.applyPhotoCoverLaunchOutline();
                }
            }
        }
    }

    public final void updateAllPhotoCoverAnimationRadii(float f) {
        if (this.photoClipForLaunchAnim) {
            if (!this.coverClipDelegatedToMasterClip || isLandscape$2()) {
                Iterator<T> it = this.mViewHolderList.iterator();
                while (it.hasNext()) {
                    ((OplusMediaViewHolder) it.next()).updatePhotoCoverLaunchRadius(f);
                }
            }
        }
    }
}
