package com.oplus.systemui.qs.media;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.android.internal.logging.InstanceId;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.MediaViewProvider;
import com.android.systemui.common.ui.view.LaunchableConstraintLayout;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.coui.appcompat.indicator.COUIPageIndicator;
import com.oplus.graphics.OplusOutlineAdapter;
import com.oplus.systemui.media.OplusMediaDataFilterExImpl;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplusos.systemui.animation.MediaDialogLaunchAnimatorController;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDetailPanelDialog extends LaunchableConstraintLayout implements OplusMediaViewPagerAdapter.IClickListener, MediaViewProvider {
    public static final Companion Companion = new Companion();
    private static final String SUPER_POWER_SAVE_DESKTOP_APP_LIST = "super_power_save_desktop_app_list";
    private static final String TAG = "OplusQsMediaDetailPanelDialog";
    public final OplusMediaViewPagerAdapter adapter;
    private final OutputAudioSharingInteractor audioSharingInteractor;
    private Callback callback;
    private final Comparator<MediaData> comparator;
    private final Context context;
    public int currentOrientation;
    public final ConstraintLayout frameContainerLayout;
    public final ConstraintLayout frameLayout;
    public boolean isAnimating;
    public boolean isLaunching;
    public boolean isLaunchingAnimating;
    public int mCurrentPosition;
    public boolean mIsMediaDetailStatus;
    private OplusOutlineAdapter masterClipAdapter;
    public boolean masterClipEnabled;
    private final int[] masterClipLocation;
    private Outline masterClipOutlineLast;
    public float masterClipRadius;
    public int masterClipScreenBottom;
    public int masterClipScreenLeft;
    public int masterClipScreenRight;
    public int masterClipScreenTop;
    public float masterClipWeight;
    private List<MediaData> mediaDataList;
    private DetailAnimationManager mediaOutputAnimationManager;
    private View mediaOutputDialogView;
    public final OplusQSPageIndicator mediaPageIndicator;
    public final ViewPager2 mediaViewPager;
    private OplusOutlineAdapter outlineAdapter;
    private Outline outlineLast;
    private SparseArray<MediaData> pendingUpdateData;
    public int positionOffsetPixels;
    private final CoroutineScope scope;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;
    private String superPowerListString;
    private ViewBlurProxy viewBlurProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsMediaDetailPanelDialog(Context context, SeekBarViewModel.Factory factory, CoroutineScope coroutineScope, OutputAudioSharingInteractor outputAudioSharingInteractor, Callback callback, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        this.seekBarViewModelFactory = factory;
        this.scope = coroutineScope;
        this.audioSharingInteractor = outputAudioSharingInteractor;
        this.callback = callback;
        this.superPowerListString = "";
        this.mediaDataList = new ArrayList();
        this.mIsMediaDetailStatus = true;
        OplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1 oplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1 = new OplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1();
        OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 = new OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1(0);
        oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1.$this_thenByDescending = oplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1;
        VarHandle.storeStoreFence();
        OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$2 = new OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1(1);
        oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$2.$this_thenByDescending = oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1;
        VarHandle.storeStoreFence();
        OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$3 = new OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1(2);
        oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$3.$this_thenByDescending = oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$2;
        VarHandle.storeStoreFence();
        OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$4 = new OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1(3);
        oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$4.$this_thenByDescending = oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$3;
        VarHandle.storeStoreFence();
        this.comparator = oplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$4;
        this.isLaunching = true;
        this.masterClipWeight = 3.0f;
        this.masterClipLocation = new int[2];
        ViewGroup.inflate(context, 2131559300, this);
        this.frameLayout = requireViewById(2131364325);
        this.frameContainerLayout = requireViewById(2131364328);
        ConstraintLayout constraintLayout = this.frameLayout;
        applyCarouselContainerOutline(constraintLayout == null ? null : constraintLayout);
        this.mediaViewPager = requireViewById(2131364324);
        OplusQSPageIndicator oplusQSPageIndicator = (OplusQSPageIndicator) requireViewById(2131364341);
        this.mediaPageIndicator = oplusQSPageIndicator;
        (oplusQSPageIndicator == null ? null : oplusQSPageIndicator).setUseOriginalRTL(true);
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = new OplusMediaViewPagerAdapter(context, factory, coroutineScope);
        this.adapter = oplusMediaViewPagerAdapter;
        ViewPager2 viewPager2 = this.mediaViewPager;
        (viewPager2 == null ? null : viewPager2).setAdapter(oplusMediaViewPagerAdapter);
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = this.adapter;
        (oplusMediaViewPagerAdapter2 == null ? null : oplusMediaViewPagerAdapter2).setIClickListener(this);
        ViewPager2 viewPager3 = this.mediaViewPager;
        (viewPager3 == null ? null : viewPager3).setOrientation(getViewPagerOrientation());
        ViewGroup viewGroup = this.frameLayout;
        (viewGroup == null ? null : viewGroup).setOnTouchListener(OplusQsMediaDetailPanelDialog$initCarouselView$1.INSTANCE);
        ViewPager2 viewPager4 = this.mediaViewPager;
        viewPager4 = viewPager4 == null ? null : viewPager4;
        OplusQsMediaDetailPanelDialog$initCarouselView$2 oplusQsMediaDetailPanelDialog$initCarouselView$2 = new OplusQsMediaDetailPanelDialog$initCarouselView$2();
        oplusQsMediaDetailPanelDialog$initCarouselView$2.this$0 = this;
        VarHandle.storeStoreFence();
        viewPager4.registerOnPageChangeCallback(oplusQsMediaDetailPanelDialog$initCarouselView$2);
        updatePageIndicatorOnConfigChanged();
        if (TextUtils.isEmpty(this.superPowerListString)) {
            updateSuperPowerList();
        }
        List<MediaData> historyPlayList = OplusQsMediaUtil.getHistoryPlayList(this.superPowerListString);
        OplusNotificationLogger.logMediaPanel(TAG, "bindData---> historyData is " + historyPlayList + ", history data size is " + historyPlayList.size());
        TreeSet<OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey> mediaSortKeySet = OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getMediaSortKeySet();
        this.mediaDataList.clear();
        if (mediaSortKeySet.isEmpty() && historyPlayList.isEmpty()) {
            OplusNotificationLogger.logMediaPanel(TAG, "integrateHisAndRecData---his and cur is empty");
        } else {
            for (OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey mediaSortKey : mediaSortKeySet) {
                integrateHisAndRecData.1.1 r3 = new integrateHisAndRecData.1.1();
                r3.$it = mediaSortKey;
                VarHandle.storeStoreFence();
                historyPlayList.removeIf(r3);
            }
            Iterator<T> it = mediaSortKeySet.iterator();
            while (it.hasNext()) {
                this.mediaDataList.add(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) it.next()).getData());
            }
            Iterator<T> it2 = historyPlayList.iterator();
            while (it2.hasNext()) {
                this.mediaDataList.add((MediaData) it2.next());
            }
            CollectionsKt___CollectionsKt.sortedWith(this.mediaDataList, this.comparator);
            Iterator<T> it3 = this.mediaDataList.iterator();
            while (it3.hasNext()) {
                OplusNotificationLogger.logMediaPanel(TAG, "integrateHisAndRecData:: player order,current package: " + ((MediaData) it3.next()).getPackageName());
            }
            if (this.mediaDataList.size() >= 3) {
                this.mediaDataList = this.mediaDataList.subList(0, 3);
            }
        }
        int size = this.mediaDataList.size();
        COUIPageIndicator cOUIPageIndicator = this.mediaPageIndicator;
        if (size > 1) {
            (cOUIPageIndicator == null ? null : cOUIPageIndicator).setVisibility(0);
            int size2 = this.mediaDataList.size();
            OplusQSPageIndicator oplusQSPageIndicator2 = this.mediaPageIndicator;
            if ((oplusQSPageIndicator2 == null ? null : oplusQSPageIndicator2).getDotsCount() != size2) {
                OplusQSPageIndicator oplusQSPageIndicator3 = this.mediaPageIndicator;
                (oplusQSPageIndicator3 == null ? null : oplusQSPageIndicator3).setDotsCount(size2);
            }
        } else {
            (cOUIPageIndicator == null ? null : cOUIPageIndicator).setVisibility(8);
        }
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter3 = this.adapter;
        (oplusMediaViewPagerAdapter3 == null ? null : oplusMediaViewPagerAdapter3).notifyData(this.mediaDataList);
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter4 = this.adapter;
        (oplusMediaViewPagerAdapter4 == null ? null : oplusMediaViewPagerAdapter4).notifyDataSetChanged();
        if (QsColorUtil.isNeedDarkThemeColor(this.context, false)) {
            OplusQSPageIndicator oplusQSPageIndicator4 = this.mediaPageIndicator;
            (oplusQSPageIndicator4 == null ? null : oplusQSPageIndicator4).setTraceDotColor(this.context.getColor(2131103718));
            OplusQSPageIndicator oplusQSPageIndicator5 = this.mediaPageIndicator;
            (oplusQSPageIndicator5 == null ? null : oplusQSPageIndicator5).setPageIndicatorDotsColor(this.context.getColor(2131103227));
        }
        View view = this.frameLayout;
        RepeatWhenAttachedKt.repeatWhenAttached$default(view == null ? null : view, Dispatchers.getMain().getImmediate(), new OplusQsMediaDetailPanelDialog$collectAudioSharingState$1(this, null), 2);
        OplusNotificationLogger.logMediaPanel(TAG, "setMediaOutputView current userid is : " + OsBinderCacheUtils.getCurrentUserId());
        applyCarouselLayoutSize();
        updatePageIndicatorOnConfigChanged();
    }

    public static final void access$finishLaunchAnimationOnViewPagerDrag(OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog) {
        Callback callback;
        if (oplusQsMediaDetailPanelDialog.isLaunchingAnimating && (callback = oplusQsMediaDetailPanelDialog.callback) != null) {
            OplusQsMediaDialogController.access$finishMediaPanelAnimationEarly(((OplusQsMediaDialogController$createQsMediaDialog$1) callback).this$0);
        }
    }

    public static final void access$refreshCarouselClipOnScroll(OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog) {
        if (oplusQsMediaDetailPanelDialog.isLaunchingAnimating) {
            ViewGroup viewGroup = oplusQsMediaDetailPanelDialog.frameContainerLayout;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.invalidateOutline();
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = oplusQsMediaDetailPanelDialog.adapter;
            (oplusMediaViewPagerAdapter != null ? oplusMediaViewPagerAdapter : null).invalidatePhotoCoverOutlineOnScroll();
            return;
        }
        ViewGroup viewGroup2 = oplusQsMediaDetailPanelDialog.frameLayout;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        viewGroup2.invalidateOutline();
        ViewGroup viewGroup3 = oplusQsMediaDetailPanelDialog.frameLayout;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        viewGroup3.invalidate();
        ViewGroup viewGroup4 = oplusQsMediaDetailPanelDialog.frameContainerLayout;
        if (viewGroup4 == null) {
            viewGroup4 = null;
        }
        viewGroup4.invalidateOutline();
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = oplusQsMediaDetailPanelDialog.adapter;
        (oplusMediaViewPagerAdapter2 != null ? oplusMediaViewPagerAdapter2 : null).invalidatePhotoCoverOutlineOnScroll();
    }

    private final int getCarouselLayoutHeight() {
        if (isLandscape$1() || isLargeScreen()) {
            return this.context.getResources().getDimensionPixelSize(2131171096);
        }
        Resources resources = this.context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131171124);
        if (OsBinderCacheUtils.getCurrentUserId() == 0) {
            dimensionPixelSize += resources.getDimensionPixelSize(2131171125);
        }
        return getCarouselContentWidthPx() + dimensionPixelSize;
    }

    private final int getCarouselLayoutWidth() {
        if (isLandscape$1() || isLargeScreen()) {
            return this.context.getResources().getDimensionPixelSize(2131171098);
        }
        return -1;
    }

    private final int getContentContainerWidth() {
        return (isLandscape$1() || isLargeScreen()) ? -2 : -1;
    }

    private final int getViewPagerOrientation() {
        return (!isLandscape$1() || isLargeScreen()) ? 0 : 1;
    }

    public static boolean isLargeScreen() {
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet();
    }

    public final void applyCarouselContainerOutline(ConstraintLayout constraintLayout) {
        final float dimension = this.context.getResources().getDimension(2131171057);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(this.context, dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        final float weight = roundParams != null ? roundParams.getWeight() : 3.0f;
        constraintLayout.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplus.systemui.qs.media.OplusQsMediaDetailPanelDialog.applyCarouselContainerOutline.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (OplusQsMediaDetailPanelDialog.this.outlineAdapter == null || !Intrinsics.areEqual(OplusQsMediaDetailPanelDialog.this.outlineLast, outline)) {
                    OplusQsMediaDetailPanelDialog.this.outlineLast = outline;
                    OplusQsMediaDetailPanelDialog.this.outlineAdapter = new OplusOutlineAdapter(outline, 1);
                }
                OplusOutlineAdapter oplusOutlineAdapter = OplusQsMediaDetailPanelDialog.this.outlineAdapter;
                if (oplusOutlineAdapter != null) {
                    oplusOutlineAdapter.setSmoothRoundRect(0, 0, view.getWidth(), view.getHeight(), dimension, weight);
                }
            }
        });
        constraintLayout.setClipToOutline(true);
        constraintLayout.setClipChildren(false);
    }

    public final void applyCarouselLayoutSize() {
        ViewGroup viewGroup = this.frameLayout;
        if (viewGroup == null) {
            viewGroup = null;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.width = getCarouselLayoutWidth();
        layoutParams.height = getCarouselLayoutHeight();
        ViewGroup viewGroup2 = this.frameLayout;
        (viewGroup2 != null ? viewGroup2 : null).setLayoutParams(layoutParams);
        int i = layoutParams.width;
        int carouselContentWidthPx = getCarouselContentWidthPx();
        int i2 = layoutParams.height;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("applyCarouselLayoutSize: width=", i, carouselContentWidthPx, " contentWidthPx=", " height=");
        sbM.append(i2);
        OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
    }

    public final void delegateCoverClipToMasterClip() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        oplusMediaViewPagerAdapter.delegateCoverClipToMasterClip();
        float f = this.masterClipRadius;
        if (f > 0.0f) {
            updateContainerMasterClip(f, this.masterClipScreenLeft, this.masterClipScreenTop, this.masterClipWeight, this.masterClipScreenRight, this.masterClipScreenBottom);
        }
    }

    public View getAppIcon() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMAppIcon();
        }
        return null;
    }

    public Float getAppIconRadius() {
        float dimension = this.context.getResources().getDimension(2131171041);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(this.context, dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        return Float.valueOf(dimension);
    }

    public View getBgView() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMLightOverlay();
        }
        return null;
    }

    public View getBlurOverlayPlaceholder() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMPhotoBlurOverlayParent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getCarouselContentWidthPx() {
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131171631) * 2;
        if (isLandscape$1() || isLargeScreen()) {
            return this.context.getResources().getDimensionPixelSize(2131171098);
        }
        int i = this.context.getResources().getDisplayMetrics().widthPixels - dimensionPixelSize;
        if (i < 0) {
            i = 0;
        }
        if (getWidth() <= 0) {
            OplusNotificationLogger.logMediaPanel(TAG, "getCarouselContentWidthPx parentWidth <0");
            return i;
        }
        int width = getWidth() - dimensionPixelSize;
        int i2 = width >= 0 ? width : 0;
        return i2 > i ? i : i2;
    }

    public View getCarouselLayout() {
        ConstraintLayout constraintLayout = this.frameLayout;
        if (constraintLayout == null) {
            return null;
        }
        return constraintLayout;
    }

    public List<View> getChildViews() {
        List<View> mediaChildViews;
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        return (viewHolderByPosition == null || (mediaChildViews = viewHolderByPosition.getMediaChildViews()) == null) ? new ArrayList() : mediaChildViews;
    }

    public View getContainer() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMContainerView();
        }
        return null;
    }

    public Float getContainerCornerRadius() {
        float dimension = this.context.getResources().getDimension(2131171057);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(this.context, dimension);
        if (roundParams != null) {
            dimension = roundParams.getRadius();
        }
        return Float.valueOf(dimension);
    }

    public View getCouiSeekBar() {
        return null;
    }

    public View getCoverImg() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMMediaCoverLayout();
        }
        return null;
    }

    public Float getCoverImgBottomRadius() {
        return Float.valueOf(0.0f);
    }

    public Float getCoverImgRadius() {
        return Float.valueOf(OplusMediaViewPagerAdapter.Companion.getPhotoCoverSmoothRadius(this.context));
    }

    public View getDefaultTip() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMDefaultTip();
        }
        return null;
    }

    public View getDotLoadingView() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMDotLoading();
        }
        return null;
    }

    public View getFavoritePlaceholder() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMLocalFavoriteButton();
        }
        return null;
    }

    public final boolean getIsMediaDetailStatus() {
        return this.mIsMediaDetailStatus;
    }

    public View getLyricPlaceholder() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMLocalLyricBtn();
        }
        return null;
    }

    public View getMediaOutputBtn() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMMediaOutputBtnContainer();
        }
        return null;
    }

    public /* bridge */ /* synthetic */ View getMediaOutputSwitchBtn() {
        return super.getMediaOutputSwitchBtn();
    }

    public View getMediaPageIndicator() {
        COUIPageIndicator cOUIPageIndicator = this.mediaPageIndicator;
        if (cOUIPageIndicator == null) {
            return null;
        }
        return cOUIPageIndicator;
    }

    public View getNextButton() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition == null) {
            return null;
        }
        ImageButton mBtnAction2 = viewHolderByPosition.getMBtnAction2();
        if (mBtnAction2 != null) {
            if (mBtnAction2.getVisibility() != 0) {
                mBtnAction2 = null;
            }
            if (mBtnAction2 != null) {
                return mBtnAction2;
            }
        }
        ImageButton mNextBtn = viewHolderByPosition.getMNextBtn();
        if (mNextBtn == null || mNextBtn.getVisibility() != 0) {
            return null;
        }
        return mNextBtn;
    }

    public int getOffsetPixels() {
        return this.positionOffsetPixels;
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    public View getPlayOrPauseButton() {
        ImageButton mBtnAction1;
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null && (mBtnAction1 = viewHolderByPosition.getMBtnAction1()) != null) {
            if (mBtnAction1.getVisibility() != 0) {
                mBtnAction1 = null;
            }
            if (mBtnAction1 != null) {
                return mBtnAction1;
            }
        }
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMPlayOrPauseBtn();
        }
        return null;
    }

    public View getPreButton() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMPreBtn();
        }
        return null;
    }

    public /* bridge */ /* synthetic */ float getPressScale() {
        return super.getPressScale();
    }

    public View getProgressiveBlurOverlay() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMPhotoBlurOverlay();
        }
        return null;
    }

    public View getSeekBarPlaceholder() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMSeekBarPatent();
        }
        return null;
    }

    public View getText() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMText();
        }
        return null;
    }

    public View getTitle() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getMTitle();
        }
        return null;
    }

    public MediaSession.Token getToken() {
        if (!this.mIsMediaDetailStatus || this.mCurrentPosition >= this.mediaDataList.size()) {
            return null;
        }
        return this.mediaDataList.get(this.mCurrentPosition).getToken();
    }

    public View getVolumeSeekBarPlaceholder() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            return viewHolderByPosition.getVolumeSeekLayout();
        }
        return null;
    }

    public final boolean isLandscape$1() {
        return this.context.getResources().getConfiguration().orientation == 2;
    }

    public boolean isMediaLoading(View view) {
        if (view instanceof OplusDotProgressBar) {
            return ((OplusDotProgressBar) view).isAnimationRunning();
        }
        return false;
    }

    public final boolean isMediaPageIndicatorBesideCarousel() {
        return isLandscape$1() && !isLargeScreen();
    }

    public final void onAnimationEnd(boolean z, boolean z2) {
        SparseArray<MediaData> sparseArray;
        SparseArray<MediaData> sparseArray2;
        OplusNotificationLogger.logMediaPanel(TAG, "onAnimationEnd: isLaunching:" + z + ", isCancel:" + z2);
        this.isLaunching = z;
        this.isAnimating = false;
        if (!z || z2 || (sparseArray = this.pendingUpdateData) == null || sparseArray.size() == 0 || (sparseArray2 = this.pendingUpdateData) == null) {
            return;
        }
        int size = sparseArray2.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = sparseArray2.keyAt(i);
            MediaData mediaDataValueAt = sparseArray2.valueAt(i);
            OplusNotificationLogger.logMediaPanel(TAG, "onAnimationEnd: index:" + iKeyAt);
            this.mediaDataList.set(iKeyAt, mediaDataValueAt);
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
            if (oplusMediaViewPagerAdapter == null) {
                oplusMediaViewPagerAdapter = null;
            }
            oplusMediaViewPagerAdapter.notifyItemChanged(iKeyAt, 0);
        }
    }

    public final void onCancelClick(boolean z) {
        Callback callback = this.callback;
        if (callback != null) {
            ((OplusQsMediaDialogController$createQsMediaDialog$1) callback).this$0.destroy(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        int i = configuration != null ? configuration.orientation : this.context.getResources().getConfiguration().orientation;
        OplusNotificationLogger.logMediaPanel(TAG, "onConfigurationChanged newOrientation:" + i + " currentOrientation:" + this.currentOrientation);
        if (i == this.currentOrientation) {
            super/*android.view.ViewGroup*/.onConfigurationChanged(configuration);
            return;
        }
        this.currentOrientation = i;
        if (!this.mIsMediaDetailStatus) {
            resetMediaOutputAfterRotation();
        }
        suppressLayout(true);
        try {
            super/*android.view.ViewGroup*/.onConfigurationChanged(configuration);
            applyCarouselLayoutSize();
            updatePageIndicatorOnConfigChanged();
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = null;
            if (oplusMediaViewPagerAdapter == null) {
                oplusMediaViewPagerAdapter = null;
            }
            oplusMediaViewPagerAdapter.clearHolder();
            ViewPager2 viewPager2 = this.mediaViewPager;
            if (viewPager2 == null) {
                viewPager2 = null;
            }
            viewPager2.setOrientation(getViewPagerOrientation());
            ViewPager2 viewPager3 = this.mediaViewPager;
            if (viewPager3 == null) {
                viewPager3 = null;
            }
            viewPager3.setAdapter((RecyclerView.Adapter) null);
            ViewPager2 viewPager4 = this.mediaViewPager;
            if (viewPager4 == null) {
                viewPager4 = null;
            }
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter3 = this.adapter;
            if (oplusMediaViewPagerAdapter3 == null) {
                oplusMediaViewPagerAdapter3 = null;
            }
            viewPager4.setAdapter(oplusMediaViewPagerAdapter3);
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter4 = this.adapter;
            if (oplusMediaViewPagerAdapter4 != null) {
                oplusMediaViewPagerAdapter2 = oplusMediaViewPagerAdapter4;
            }
            oplusMediaViewPagerAdapter2.notifyDataSetChanged();
            if (!this.mIsMediaDetailStatus) {
                resetMediaOutputAfterRotation();
            }
        } finally {
            suppressLayout(false);
        }
    }

    public final void onMediaDataLoaded(MediaData mediaData) {
        OplusNotificationLogger.logMediaPanel(TAG, "bindData --> data is : " + MediaDataExtKt.printInfo(mediaData, true));
        if (mediaData == null) {
            return;
        }
        if (this.mediaDataList.isEmpty()) {
            OplusNotificationLogger.logMediaPanel(TAG, "onMediaDataLoaded, mMediaDataList is empty");
            return;
        }
        int i = 0;
        for (Object obj : this.mediaDataList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaData mediaData2 = (MediaData) obj;
            if (Intrinsics.areEqual(mediaData2.getNotificationKey(), mediaData.getNotificationKey()) || (Intrinsics.areEqual(mediaData2.getPackageName(), mediaData.getPackageName()) && mediaData2.getUserId() == mediaData.getUserId())) {
                boolean z = this.isAnimating;
                boolean z2 = this.isLaunching;
                StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("onMediaDataLoaded: index:", i, ", isAnimating:", ", isLaunching:", z);
                sbM.append(z2);
                OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
                if (!getIsMediaDetailStatus()) {
                    return;
                }
                if (!this.isAnimating) {
                    this.mediaDataList.set(i, mediaData);
                    OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
                    (oplusMediaViewPagerAdapter != null ? oplusMediaViewPagerAdapter : null).notifyItemChanged(i, 0);
                } else if (this.isLaunching) {
                    SparseArray<MediaData> sparseArray = this.pendingUpdateData;
                    if (sparseArray == null) {
                        sparseArray = new SparseArray<>(3);
                        this.pendingUpdateData = sparseArray;
                    }
                    sparseArray.put(i, mediaData);
                }
            }
            i = i2;
        }
    }

    public final void onMediaDataRemoved(String str, boolean z) {
        Object next;
        OplusNotificationLogger.logMediaPanel(TAG, "onMediaDataRemoved --> key is : " + str);
        if (this.mediaDataList.isEmpty()) {
            OplusNotificationLogger.logMediaPanel(TAG, "onMediaDataRemoved, mMediaDataList is empty");
            return;
        }
        List<MediaData> historyPlayList = OplusQsMediaUtil.getHistoryPlayList(this.superPowerListString);
        int i = 0;
        for (Object obj : this.mediaDataList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaData mediaData = (MediaData) obj;
            if (Intrinsics.areEqual(mediaData.getNotificationKey(), str)) {
                Iterator<T> it = historyPlayList.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((MediaData) next).getPackageName(), mediaData.getPackageName()));
                MediaData mediaData2 = (MediaData) next;
                OplusNotificationLogger.logMediaPanel(TAG, "onMediaDataRemoved-->index:" + i + " historyMediaData:" + mediaData2 + " mediaData:" + mediaData);
                List<MediaData> list = this.mediaDataList;
                list.set(i, new MediaData(list.get(i).getUserId(), false, (String) null, (Icon) null, (CharSequence) null, (CharSequence) null, (Icon) null, (List) null, (List) null, (MediaButton) null, this.mediaDataList.get(i).getPackageName(), (MediaSession.Token) null, (PendingIntent) null, (MediaDeviceData) null, false, (Runnable) null, 0, mediaData2 != null ? mediaData2.getNotificationKey() : null, false, (Boolean) null, false, 0L, 0L, (InstanceId) null, 0, false, (Double) null, (OplusMediaDataEx) null, 2146956286));
                OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
                (oplusMediaViewPagerAdapter != null ? oplusMediaViewPagerAdapter : null).notifyItemChanged(i);
            }
            i = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusMediaViewPagerAdapter.IClickListener
    public void onMediaOutputClick(View view) {
        if (!this.mIsMediaDetailStatus) {
            OplusNotificationLogger.logMediaPanel(TAG, "onMediaOutputClick: ignored, already in media output mode");
            return;
        }
        this.mIsMediaDetailStatus = false;
        Callback callback = this.callback;
        if (callback != null) {
            OplusNotificationLogger.logMediaPanel(OplusQsMediaDialogController.TAG, "OplusQsMediaDialogController::onShowMediaOutputStart");
            OplusQsMediaDialogController oplusQsMediaDialogController = ((OplusQsMediaDialogController$createQsMediaDialog$1) callback).this$0;
            oplusQsMediaDialogController.viewDialogInteractor.cancelDetailAnimation();
            MediaDialogLaunchAnimatorController mediaDialogAnimatorController = oplusQsMediaDialogController.getMediaDialogAnimatorController();
            if (mediaDialogAnimatorController != null) {
                mediaDialogAnimatorController.onShowMediaOutputDialog();
            }
            oplusQsMediaDialogController.mediaOutputDetailAdapter.setMediaDetailToMediaOutputAnimating(true);
        }
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        oplusMediaViewPagerAdapter.setShowMediaOutput(true);
        Callback callback2 = this.callback;
        if (callback2 != null) {
            ((OplusQsMediaDialogController$createQsMediaDialog$1) callback2).this$0.clearMediaPanelAnimator();
        }
        this.mediaOutputDialogView = view;
        OplusNotificationLogger.logMediaPanelDebug(TAG, "showMediaOutPutDialog: mediaOutputDialog=" + view);
        addView(view, new FrameLayout.LayoutParams(-1, -1, 17));
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = new showMediaOutputDialog.animationCallback.1();
        ((showMediaOutputDialog.animationCallback.1) animationCallBack).this$0 = this;
        VarHandle.storeStoreFence();
        DetailAnimationManager detailAnimationManager = new DetailAnimationManager((LinearLayout) view.requireViewById(2131362831));
        this.mediaOutputAnimationManager = detailAnimationManager;
        ViewGroup viewGroup = this.frameLayout;
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroup.suppressLayout(true);
        ViewGroup viewGroup2 = this.frameContainerLayout;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        viewGroup2.suppressLayout(true);
        View view2 = this.frameLayout;
        if (view2 == null) {
            view2 = null;
        }
        detailAnimationManager.showDetailFromView(view2, 0, null, animationCallBack);
    }

    public final void onStop() {
        DetailAnimationManager detailAnimationManager = this.mediaOutputAnimationManager;
        if (detailAnimationManager != null) {
            detailAnimationManager.cancel();
        }
        this.mediaOutputAnimationManager = null;
        this.mediaOutputDialogView = null;
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        oplusMediaViewPagerAdapter.onDestroy();
        this.mediaDataList.clear();
        ViewGroup viewGroup = this.mediaViewPager;
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroup.removeAllViews();
        this.callback = null;
    }

    public final void prepareContainerMasterClip() {
        if (this.masterClipEnabled) {
            return;
        }
        this.masterClipEnabled = true;
        this.masterClipWeight = 3.0f;
        ViewGroup viewGroup = this.frameContainerLayout;
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroup.setClipChildren(true);
        ViewGroup viewGroup2 = this.frameContainerLayout;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        viewGroup2.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplus.systemui.qs.media.OplusQsMediaDetailPanelDialog.prepareContainerMasterClip.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = OplusQsMediaDetailPanelDialog.this;
                ViewGroup viewGroup3 = oplusQsMediaDetailPanelDialog.frameContainerLayout;
                if (viewGroup3 == null) {
                    viewGroup3 = null;
                }
                viewGroup3.getLocationOnScreen(oplusQsMediaDetailPanelDialog.masterClipLocation);
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog2 = OplusQsMediaDetailPanelDialog.this;
                int i = oplusQsMediaDetailPanelDialog2.masterClipScreenLeft - oplusQsMediaDetailPanelDialog2.masterClipLocation[0];
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog3 = OplusQsMediaDetailPanelDialog.this;
                int i2 = oplusQsMediaDetailPanelDialog3.masterClipScreenTop - oplusQsMediaDetailPanelDialog3.masterClipLocation[1];
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog4 = OplusQsMediaDetailPanelDialog.this;
                int i3 = oplusQsMediaDetailPanelDialog4.masterClipScreenRight - oplusQsMediaDetailPanelDialog4.masterClipLocation[0];
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog5 = OplusQsMediaDetailPanelDialog.this;
                int i4 = oplusQsMediaDetailPanelDialog5.masterClipScreenBottom - oplusQsMediaDetailPanelDialog5.masterClipLocation[1];
                if (OplusQsMediaDetailPanelDialog.this.masterClipAdapter == null || OplusQsMediaDetailPanelDialog.this.masterClipOutlineLast != outline) {
                    OplusQsMediaDetailPanelDialog.this.masterClipOutlineLast = outline;
                    OplusQsMediaDetailPanelDialog.this.masterClipAdapter = new OplusOutlineAdapter(outline, 1);
                }
                OplusOutlineAdapter oplusOutlineAdapter = OplusQsMediaDetailPanelDialog.this.masterClipAdapter;
                if (oplusOutlineAdapter != null) {
                    OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog6 = OplusQsMediaDetailPanelDialog.this;
                    oplusOutlineAdapter.setSmoothRoundRect(i, i2, i3, i4, oplusQsMediaDetailPanelDialog6.masterClipRadius, oplusQsMediaDetailPanelDialog6.masterClipWeight);
                }
            }
        });
        ViewGroup viewGroup3 = this.frameContainerLayout;
        (viewGroup3 != null ? viewGroup3 : null).setClipToOutline(true);
    }

    public final void resetAppIconCornerRadius() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            viewHolderByPosition.resetAppIconOutlineRadius();
        }
    }

    public final void resetCoverImgCornerRadius() {
    }

    public final void resetMediaOutputAfterRotation() {
        View view = this.mediaOutputDialogView;
        OplusNotificationLogger.logMediaPanel(TAG, "resetMediaOutputAfterRotation hasDialog=" + (view != null));
        DetailAnimationManager detailAnimationManager = this.mediaOutputAnimationManager;
        if (detailAnimationManager != null) {
            detailAnimationManager.cancel();
        }
        ViewGroup viewGroup = this.frameLayout;
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroup.suppressLayout(false);
        ViewGroup viewGroup2 = this.frameContainerLayout;
        (viewGroup2 != null ? viewGroup2 : null).suppressLayout(false);
        if (view == null) {
            return;
        }
        View viewFindViewById = view.findViewById(2131362831);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setAlpha(1.0f);
            viewFindViewById.setTranslationX(0.0f);
            viewFindViewById.setTranslationY(0.0f);
            viewFindViewById.setScaleX(1.0f);
            viewFindViewById.setScaleY(1.0f);
            viewFindViewById.forceLayout();
        }
        requestLayout();
    }

    public final void resetView() {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(0);
        if (viewHolderByPosition != null) {
            MediaData mediaData = viewHolderByPosition.getMediaData();
            if ((mediaData != null ? mediaData.getToken() : null) == null) {
                TextView mDefaultTip = viewHolderByPosition.getMDefaultTip();
                if (mDefaultTip != null) {
                    mDefaultTip.setVisibility(0);
                }
                TextView mTitle = viewHolderByPosition.getMTitle();
                if (mTitle != null) {
                    mTitle.setVisibility(8);
                }
                TextView mText = viewHolderByPosition.getMText();
                if (mText != null) {
                    mText.setVisibility(8);
                    return;
                }
                return;
            }
            TextView mDefaultTip2 = viewHolderByPosition.getMDefaultTip();
            if (mDefaultTip2 != null) {
                mDefaultTip2.setVisibility(8);
            }
            TextView mTitle2 = viewHolderByPosition.getMTitle();
            if (mTitle2 != null) {
                mTitle2.setVisibility(0);
            }
            TextView mText2 = viewHolderByPosition.getMText();
            if (mText2 != null) {
                mText2.setVisibility(0);
            }
        }
    }

    public final void restoreCarouselContainerOutline() {
        ConstraintLayout constraintLayout = this.frameLayout;
        if (constraintLayout == null) {
            constraintLayout = null;
        }
        applyCarouselContainerOutline(constraintLayout);
    }

    public final void restoreContainerMasterClip() {
        if (this.masterClipEnabled) {
            this.masterClipEnabled = false;
            ViewGroup viewGroup = this.frameContainerLayout;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.setClipToOutline(false);
            ViewGroup viewGroup2 = this.frameContainerLayout;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            this.masterClipAdapter = null;
            this.masterClipOutlineLast = null;
            this.masterClipScreenLeft = 0;
            this.masterClipScreenTop = 0;
            this.masterClipScreenRight = 0;
            this.masterClipScreenBottom = 0;
        }
    }

    public void setLaunchingAnimating(boolean z) {
        if (this.isLaunchingAnimating == z) {
            return;
        }
        this.isLaunchingAnimating = z;
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        oplusMediaViewPagerAdapter.setPhotoClipForLaunchAnimation(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLayerType(int i, Paint paint) {
        super/*android.view.ViewGroup*/.setLayerType(1, null);
    }

    public final void updateAppIconCornerRadius(float f) {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        if (viewHolderByPosition != null) {
            viewHolderByPosition.updateAppIconLaunchRadius(f);
        }
    }

    public final void updateBlurProgress(float f) {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        OplusMediaViewPagerAdapter.OplusMediaViewHolder viewHolderByPosition = oplusMediaViewPagerAdapter.getViewHolderByPosition(this.mCurrentPosition);
        ProgressiveBlurOverlay mPhotoBlurOverlay = viewHolderByPosition != null ? viewHolderByPosition.getMPhotoBlurOverlay() : null;
        if (mPhotoBlurOverlay != null) {
            mPhotoBlurOverlay.setBlurProgress(f);
        }
    }

    public final void updateContainerMasterClip(float f, int i, int i2, float f2, int i3, int i4) {
        if (f <= 0.0f) {
            return;
        }
        this.masterClipRadius = f;
        if (f2 <= 0.0f) {
            f2 = 3.0f;
        }
        this.masterClipWeight = f2;
        this.masterClipScreenLeft = i;
        this.masterClipScreenTop = i2;
        this.masterClipScreenRight = i3;
        this.masterClipScreenBottom = i4;
        if (this.masterClipEnabled) {
            ViewGroup viewGroup = this.frameContainerLayout;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.invalidateOutline();
        }
    }

    public final void updateCoverImgCornerRadii(float f, float f2, float f3, float f4) {
        OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = this.adapter;
        if (oplusMediaViewPagerAdapter == null) {
            oplusMediaViewPagerAdapter = null;
        }
        oplusMediaViewPagerAdapter.updateAllPhotoCoverAnimationRadii(f2);
    }

    public final void updateCoverImgCornerRadius(float f, float f2) {
        updateCoverImgCornerRadii(f, f, f2, f2);
    }

    public void updateFinishExpand(String str) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updatePageIndicatorOnConfigChanged() {
        View viewRequireViewById = requireViewById(2131364341);
        ViewGroup viewGroup = this.frameLayout;
        if (viewGroup == null) {
            viewGroup = null;
        }
        ViewGroup.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewGroup.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) viewRequireViewById.getLayoutParams();
        ((ConstraintLayout.LayoutParams) layoutParams).topToTop = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).topToBottom = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).bottomToTop = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).bottomToBottom = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).startToStart = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).startToEnd = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).endToStart = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).endToEnd = -1;
        ((ConstraintLayout.LayoutParams) layoutParams).horizontalBias = 0.5f;
        ((ConstraintLayout.LayoutParams) layoutParams).verticalBias = 0.5f;
        layoutParams2.topToTop = -1;
        layoutParams2.topToBottom = -1;
        layoutParams2.bottomToTop = -1;
        layoutParams2.bottomToBottom = -1;
        layoutParams2.startToStart = -1;
        layoutParams2.startToEnd = -1;
        layoutParams2.endToStart = -1;
        layoutParams2.endToEnd = -1;
        ViewGroup viewGroup2 = this.frameContainerLayout;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        ConstraintLayout.LayoutParams layoutParams3 = viewGroup2.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams3).width = getContentContainerWidth();
        ((ViewGroup.MarginLayoutParams) layoutParams3).height = -2;
        layoutParams3.topToTop = 0;
        layoutParams3.bottomToBottom = 0;
        layoutParams3.startToStart = 0;
        layoutParams3.endToEnd = 0;
        ViewGroup viewGroup3 = this.frameContainerLayout;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        viewGroup3.setLayoutParams(layoutParams3);
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131171971);
        int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(2131171536);
        int dimensionPixelSize3 = this.context.getResources().getDimensionPixelSize(2131171710);
        ViewPager2 viewPager2 = this.mediaViewPager;
        if (viewPager2 == null) {
            viewPager2 = null;
        }
        MarginPageTransformer marginPageTransformer = new MarginPageTransformer();
        Preconditions.checkArgumentNonnegative(dimensionPixelSize3, "Margin must be non-negative");
        marginPageTransformer.mMarginPx = dimensionPixelSize3;
        VarHandle.storeStoreFence();
        viewPager2.setPageTransformer(marginPageTransformer);
        if (!isLandscape$1() || isLargeScreen()) {
            viewRequireViewById.setRotation(0.0f);
            ((ConstraintLayout.LayoutParams) layoutParams).startToStart = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).endToEnd = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).topToTop = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).bottomToBottom = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).verticalBias = 0.5f;
            layoutParams2.topToBottom = 2131364328;
            layoutParams2.startToStart = 0;
            layoutParams2.endToEnd = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = dimensionPixelSize;
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = this.context.getResources().getDimensionPixelSize(2131171970);
            layoutParams2.setMarginStart(0);
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = -2;
            ((ViewGroup.MarginLayoutParams) layoutParams2).height = dimensionPixelSize2;
        } else {
            viewRequireViewById.setRotation(90.0f);
            ((ConstraintLayout.LayoutParams) layoutParams).startToStart = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).endToEnd = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).topToTop = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).bottomToBottom = 0;
            ((ConstraintLayout.LayoutParams) layoutParams).horizontalBias = 0.5f;
            ((ConstraintLayout.LayoutParams) layoutParams).verticalBias = 0.5f;
            layoutParams2.startToEnd = 2131364328;
            layoutParams2.topToTop = 2131364328;
            layoutParams2.bottomToBottom = 2131364328;
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
            ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = -2;
            ((ViewGroup.MarginLayoutParams) layoutParams2).height = dimensionPixelSize2;
        }
        ViewGroup viewGroup4 = this.frameLayout;
        (viewGroup4 != null ? viewGroup4 : null).setLayoutParams(layoutParams);
        viewRequireViewById.setLayoutParams(layoutParams2);
    }

    public final void updateSuperPowerList() {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            String stringForUser = Settings.System.getStringForUser(this.context.getContentResolver(), "super_power_save_desktop_app_list", 0);
            if (stringForUser == null) {
                stringForUser = "";
            }
            this.superPowerListString = stringForUser;
        }
    }
}
