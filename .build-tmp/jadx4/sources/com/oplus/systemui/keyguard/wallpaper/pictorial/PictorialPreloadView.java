package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplusos.keyguard.statistic.KeyguardStatistic;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialPreloadView extends RelativeLayout implements IKeyguardSliderPager, PictorialWallpaperHelper.IPictorialPreloadListener, PictorialMediator.IPictorialMediatorCallback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mBlockSlideTrigger;
    public int mDirection;
    public ImageView mIvPreloadPictoralImage;
    public float mOffset;
    public PictorialMediator mPictorialMediator;
    public PictorialPreloadHelper mPictorialPreloadHelper;
    public final PictorialWallpaperHelper mPictorialWallpaperHelper;
    public int mPositionOffsetPixels;
    public final PictorialPreloadView$$ExternalSyntheticLambda0 mResetStartedPictorialSlidePageRunnable;
    public int mScreenWidth;
    public boolean mSelected;
    public boolean mStartingPictorialSlidePage;
    public PictorialWallpaperInfo mWallpaperInfo;

    /* JADX INFO: renamed from: $r8$lambda$fI-jkK7YCTohdjqpJC8Wnp6UXOE, reason: not valid java name */
    public static void m10$r8$lambda$fIjkK7YCTohdjqpJC8Wnp6UXOE(PictorialPreloadView pictorialPreloadView, boolean z, boolean z2, String str, int i) {
        int i2;
        if (!z) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268470272);
            if (z2) {
                i2 = 2;
                intent.putExtra("swipe_entrance", pictorialPreloadView.mDirection == 0 ? 2 : 1);
                if (i == 1) {
                    PictorialWallpaperInfo pictorialWallpaperInfo = pictorialPreloadView.mWallpaperInfo;
                    if (pictorialWallpaperInfo != null) {
                        intent.putExtra(PictorialAppInfo.ID, pictorialWallpaperInfo.getImageId());
                        intent.putExtra("groupid", pictorialWallpaperInfo.getGroupId());
                    }
                    i2 = 0;
                } else if (i != 2) {
                    i2 = 1;
                } else if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getIsUiEngineSwipe()) {
                    i2 = 4;
                }
            } else {
                i2 = 3;
                if ("affordance".equals(str)) {
                    if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).hasPictorialSpace()) {
                        intent.setPackage(((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialPackageName());
                        intent.setAction("com.heytap.pictorial.action.START_SELECTION");
                        PictorialSpaceBean pictorialSpaceBean = PictorialWallpaperHelper.getInstance(((RelativeLayout) pictorialPreloadView).mContext).getPictorialSpaceBean();
                        if (pictorialSpaceBean != null) {
                            intent.putExtra("pictorial_extra", pictorialSpaceBean.getPictorialExtra());
                        }
                    }
                    intent.putExtra("swipe_entrance", 3);
                    i2 = 5;
                }
            }
            intent.putExtra("enterReferer", i2);
            String str2 = "startPictorialSlidePage type=" + i2 + " lockScreenType=" + i + " info";
            PictorialWallpaperInfo pictorialWallpaperInfo2 = pictorialPreloadView.mWallpaperInfo;
            if (pictorialWallpaperInfo2 == null) {
                StringBuilder sb = PictorialUtils.sStringBuilder;
            } else {
                StringBuilder sb2 = PictorialUtils.sStringBuilder;
                if (sb2 == null) {
                    PictorialUtils.sStringBuilder = new StringBuilder();
                } else {
                    sb2.delete(0, sb2.length());
                }
                PictorialUtils.sStringBuilder.append(str2);
                StringBuilder sb3 = PictorialUtils.sStringBuilder;
                sb3.append(" = ( ");
                sb3.append("imageID = " + pictorialWallpaperInfo2.getImageId());
                sb3.append(", groupID = " + pictorialWallpaperInfo2.getGroupId());
                sb3.append(", title = " + pictorialWallpaperInfo2.getTextTitle());
                sb3.append(", content = " + pictorialWallpaperInfo2.getTextContent());
                sb3.append(", type = " + pictorialWallpaperInfo2.getGroupType());
                sb3.append(", webUrl = " + pictorialWallpaperInfo2.getWebUrl());
                sb3.append(", deepLink = " + pictorialWallpaperInfo2.getDeepLink());
                sb3.append(", instantAppLink = " + pictorialWallpaperInfo2.getInstantAppLink());
                sb3.append(", clickText = " + pictorialWallpaperInfo2.getClickText());
                sb3.append(", uri = " + pictorialWallpaperInfo2.getFileUri());
                PictorialUtils.sStringBuilder.append(" )");
                KgdLogUtil.d("PictorialUtils", PictorialUtils.sStringBuilder.toString());
            }
            if (!"affordance".equals(str) || !((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).hasPictorialSpace()) {
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialPreloadHelper().updatePictorialSlidePageIntent(intent, ((RelativeLayout) pictorialPreloadView).mContext, pictorialPreloadView.mWallpaperInfo);
            }
            try {
                ((RelativeLayout) pictorialPreloadView).mContext.startActivity(intent, ActivityOptions.makeCustomAnimation(((RelativeLayout) pictorialPreloadView).mContext, 0, 0).toBundle());
                if (z2) {
                    pictorialPreloadView.setStartingPictorialSlidePage(true);
                    KeyguardStatistic.sendEventData(pictorialPreloadView.getContext(), "counts_slide_enter_slide_page", null);
                }
            } catch (ActivityNotFoundException e) {
                KgdLogUtil.w("PictorialPreloadView", "seePictorialDetails. Fail to start pictorial details activity!" + e.getMessage());
                if (z2) {
                    pictorialPreloadView.setStartingPictorialSlidePage(false);
                }
            }
        } else if (z2) {
            KeyguardStatistic.sendEventData(pictorialPreloadView.getContext(), "counts_slide_enter_slide_page", null);
        }
        if (z2) {
            pictorialPreloadView.removeCallbacks(pictorialPreloadView.mResetStartedPictorialSlidePageRunnable);
            pictorialPreloadView.postDelayed(pictorialPreloadView.mResetStartedPictorialSlidePageRunnable, 1000L);
        }
    }

    public static void $r8$lambda$rUG0V5p5lgspLAwa4n_aaJykQXo(PictorialPreloadView pictorialPreloadView, boolean z, String str, int i) {
        int i2;
        PictorialSpaceBean pictorialSpaceBean;
        boolean zCallPictorialPage;
        PictorialWallpaperInfo pictorialWallpaperInfo;
        int i3 = 1;
        if (z) {
            pictorialPreloadView.setStartingPictorialSlidePage(true);
        }
        if (pictorialPreloadView.mPictorialMediator == null) {
            pictorialPreloadView.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        PictorialWallpaperInfo pictorialWallpaperInfo2 = pictorialPreloadView.mWallpaperInfo;
        if (i == 1 && pictorialWallpaperInfo2 == null) {
            KgdLogUtil.w("PictorialPreloadView", "callPictorialPage: mWallpaperInfo is null, lockScreenType=" + i);
            zCallPictorialPage = false;
        } else {
            if ("slide".equals(str)) {
                i2 = pictorialPreloadView.mDirection == 0 ? 3 : 2;
                if (i == 2) {
                    i2 = 5;
                }
            } else {
                i2 = "affordance".equals(str) ? 4 : 2;
            }
            if (!"affordance".equals(str) || !((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).hasPictorialSpace()) {
                PictorialAppData pictorialAppData = pictorialPreloadView.mPictorialMediator.getPictorialAppData();
                if (pictorialPreloadView.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe() && !pictorialPreloadView.mPictorialMediator.isMainSwitchStatusOn() && pictorialAppData != null) {
                    i2 = 10;
                }
            }
            Bundle bundle = new Bundle();
            if ("slide".equals(str)) {
                bundle.putInt("swipe_entrance", pictorialPreloadView.mDirection == 0 ? 2 : 1);
                if (i == 1 && (pictorialWallpaperInfo = pictorialPreloadView.mWallpaperInfo) != null) {
                    bundle.putString(PictorialAppInfo.ID, pictorialWallpaperInfo.getImageId());
                    bundle.putString("groupid", pictorialWallpaperInfo.getGroupId());
                }
            } else if ("affordance".equals(str)) {
                if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).hasPictorialSpace() && (pictorialSpaceBean = PictorialWallpaperHelper.getInstance(((RelativeLayout) pictorialPreloadView).mContext).getPictorialSpaceBean()) != null) {
                    bundle.putString("pictorial_extra", pictorialSpaceBean.getPictorialExtra());
                }
                bundle.putInt("swipe_entrance", 3);
                PictorialWallpaperInfo pictorialWallpaperInfo3 = pictorialPreloadView.mWallpaperInfo;
                if (pictorialWallpaperInfo3 != null) {
                    bundle.putString("imageId", pictorialWallpaperInfo3.getImageId());
                }
            }
            if (!"slide".equals(str)) {
                i3 = "affordance".equals(str) ? 5 : 3;
            } else if (i == 1) {
                i3 = 0;
            } else if (i == 2) {
                i3 = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getIsUiEngineSwipe() ? 4 : 2;
            }
            bundle.putInt("enterReferer", i3);
            PictorialWallpaperInfo pictorialWallpaperInfo4 = pictorialPreloadView.mWallpaperInfo;
            if (pictorialWallpaperInfo4 != null) {
                bundle.putString("txtGroupId", pictorialWallpaperInfo4.getTxtGroupId());
            }
            if (!"affordance".equals(str) || !((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).hasPictorialSpace()) {
                PictorialAppData pictorialAppData2 = pictorialPreloadView.mPictorialMediator.getPictorialAppData();
                if (!pictorialPreloadView.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe() || pictorialPreloadView.mPictorialMediator.isMainSwitchStatusOn() || pictorialAppData2 == null) {
                    PictorialWallpaperInfo pictorialWallpaperInfo5 = pictorialPreloadView.mWallpaperInfo;
                    if (pictorialWallpaperInfo5 != null) {
                        bundle.putString("imageId", pictorialWallpaperInfo5.getImageId());
                    }
                } else {
                    bundle.putInt("enterReferer", 0);
                }
            }
            zCallPictorialPage = pictorialPreloadView.mPictorialMediator.callPictorialPage(i2, bundle);
        }
        PictorialPreloadView$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new PictorialPreloadView$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = pictorialPreloadView;
        externalSyntheticLambda2.f$1 = zCallPictorialPage;
        externalSyntheticLambda2.f$2 = z;
        externalSyntheticLambda2.f$3 = str;
        externalSyntheticLambda2.f$4 = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(externalSyntheticLambda2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PictorialPreloadView(Context context, AttributeSet attributeSet, int i, int i2) {
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        super(companion.updateDisplayConfiguration(context), attributeSet, i, i2);
        this.mBlockSlideTrigger = false;
        this.mSelected = false;
        this.mDirection = 2;
        PictorialPreloadView$$ExternalSyntheticLambda0 pictorialPreloadView$$ExternalSyntheticLambda0 = new PictorialPreloadView$$ExternalSyntheticLambda0();
        pictorialPreloadView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mResetStartedPictorialSlidePageRunnable = pictorialPreloadView$$ExternalSyntheticLambda0;
        PictorialWallpaperHelper pictorialWallpaperHelper = PictorialWallpaperHelper.getInstance(context);
        this.mPictorialWallpaperHelper = pictorialWallpaperHelper;
        pictorialWallpaperHelper.setPreloadWallpaperChangeListener(this);
        this.mScreenWidth = companion.getKeyguardUtils(((RelativeLayout) this).mContext).getScreenSize()[0];
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void attached() {
        this.mPictorialWallpaperHelper.setPreloadWallpaperChangeListener(this);
        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialPreloadHelper().dispatchPictorialPreloadViewLoad();
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        this.mPictorialMediator = pictorialMediator;
        pictorialMediator.registerCallback(this);
        this.mPictorialPreloadHelper = this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper();
    }

    public final void checkAndStartPictorialSlide(float f, int i) {
        if ((i == 0 || Math.abs(f) < 0.01f) && this.mSelected) {
            if (this.mBlockSlideTrigger) {
                KgdLogUtil.i("PictorialPreloadView", "block slide trigger");
            } else {
                startPictorialSlidePage("slide");
            }
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void detached() {
        PictorialWallpaperHelper pictorialWallpaperHelper = this.mPictorialWallpaperHelper;
        pictorialWallpaperHelper.mPreloadChangeListeners.remove(this);
        pictorialWallpaperHelper.mPictorialLoader.removePreloadWallpaperChangeListener(this);
        PictorialStateWatcher.getInstance().dispatchPictorialPreloadViewLoad(false);
        this.mPictorialMediator.unregisterCallback(this);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final int direction() {
        return this.mDirection;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public View getView() {
        return this;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void hide() {
        KgdLogUtil.i("PictorialPreloadView", "hide");
        this.mIvPreloadPictoralImage.setImageDrawable(null);
        if (this.mStartingPictorialSlidePage && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isKeyguardOccluded()) {
            ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mEntryPictorialSlidePage = true;
        }
        PictorialStateWatcher.getInstance().dispatchPictorialPreloadViewLoad(false);
        setStartingPictorialSlidePage(false);
        removeCallbacks(this.mResetStartedPictorialSlidePageRunnable);
        this.mOffset = 0.0f;
        this.mPositionOffsetPixels = 0;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(2131363512);
        this.mIvPreloadPictoralImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setTag(103);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageScrollStateChanged(int i) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageScrolled(float f, int i) {
        this.mOffset = f;
        this.mPositionOffsetPixels = i;
        checkAndStartPictorialSlide(f, i);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageSelected(boolean z) {
        this.mSelected = z;
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialPreloadView", new StringBuilder("onPageSelected : mSelected = "), this.mSelected);
        checkAndStartPictorialSlide(this.mOffset, this.mPositionOffsetPixels);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
    public final void onSwitchPictorialApp() {
        this.mPictorialPreloadHelper = this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper();
    }

    public final void onWallpaperChange() {
        PictorialPreloadHelper pictorialPreloadHelper = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialPreloadHelper();
        this.mWallpaperInfo = pictorialPreloadHelper.onWallpaperChange(((RelativeLayout) this).mContext, this.mIvPreloadPictoralImage, this.mPictorialWallpaperHelper);
        pictorialPreloadHelper.dispatchPictorialPreloadViewLoad();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final int pagerType() {
        return 103;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x009d A[RETURN] */
    public final boolean readyToShow() {
        if (!((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialEnabled) {
            KgdLogUtil.i("PictorialPreloadView", "The app is disabled.");
            return false;
        }
        if (this.mIvPreloadPictoralImage != null) {
            if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isGlanceStyle() || ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isTbStyle() || ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isFlavorOneExpLH()) {
                boolean z = this.mStartingPictorialSlidePage;
                return !z;
            }
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
                if (pictorialMediator.isOplusGLStyle()) {
                    if (pictorialMediator.mEnablePlaySwitchOn) {
                        this.mPictorialPreloadHelper.getClass();
                        KgdLogUtil.i("PictorialPreloadView", "readyToShow on:false");
                        return false;
                    }
                    OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialPreloadView", new StringBuilder("readyToShow off:"), this.mStartingPictorialSlidePage);
                    if (!this.mStartingPictorialSlidePage && !ChildrenModeObserver.getInstance().mIsChildrenMode) {
                        return true;
                    }
                }
            }
            if (this.mIvPreloadPictoralImage.getDrawable() != null && !this.mStartingPictorialSlidePage) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(int i) {
        this.mDirection = i;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public /* bridge */ /* synthetic */ void setImmersive(boolean z) {
        super.setImmersive(z);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public /* bridge */ /* synthetic */ void setProcessY(float f) {
        super.setProcessY(f);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public /* bridge */ /* synthetic */ void setQsExpanded(boolean z) {
        super.setQsExpanded(z);
    }

    public void setStartingPictorialSlidePage(boolean z) {
        this.mStartingPictorialSlidePage = z;
        PictorialUtils.setIsPictorialSlidePagerStarting(z);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ec  */
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void show() {
        boolean z;
        KgdLogUtil.i("PictorialPreloadView", "show, mScreenWidth=" + this.mScreenWidth + ", width=" + getMeasuredWidth() + ", height=" + getMeasuredHeight() + ", displayid=" + ((RelativeLayout) this).mContext.getDisplayId());
        if (this.mIvPreloadPictoralImage == null) {
            this.mPositionOffsetPixels = this.mScreenWidth;
        } else {
            FeatureOption.INSTANCE.getClass();
            boolean z2 = true;
            if (FeatureOption.isLargeScreenFolding()) {
                if (FeatureOption.isWhiteSwanFoldDevice()) {
                    this.mPositionOffsetPixels = this.mScreenWidth;
                } else {
                    int measuredWidth = getMeasuredWidth();
                    int measuredHeight = getMeasuredHeight();
                    int i = getResources().getConfiguration().orientation;
                    if (measuredWidth <= 0 || measuredHeight <= 0) {
                        z = false;
                    } else {
                        if (i == 1 && measuredWidth > measuredHeight) {
                            this.mScreenWidth = measuredHeight;
                            KgdLogUtil.w("PictorialPreloadView", "syncLayoutAndSlideMetricsForShow: portrait but w>h, short edge=" + this.mScreenWidth);
                        } else if (i != 2 || measuredWidth >= measuredHeight) {
                            this.mScreenWidth = measuredWidth;
                            z = false;
                        } else {
                            this.mScreenWidth = measuredHeight;
                            KgdLogUtil.w("PictorialPreloadView", "syncLayoutAndSlideMetricsForShow: landscape but w<h, long edge=" + this.mScreenWidth);
                        }
                        z = true;
                    }
                    this.mPositionOffsetPixels = this.mScreenWidth;
                }
                if (this.mIvPreloadPictoralImage.getLayoutParams().width == -1 || getMeasuredWidth() == this.mIvPreloadPictoralImage.getMeasuredWidth()) {
                    z2 = z;
                } else {
                    KgdLogUtil.d("PictorialPreloadView", "syncLayoutAndSlideMetricsForShow: root vs image measured width mismatch");
                }
                if (z2) {
                    requestLayout();
                }
            } else {
                int measuredWidth2 = getMeasuredWidth();
                int measuredHeight2 = getMeasuredHeight();
                if (measuredWidth2 > 0 && measuredHeight2 > 0) {
                    this.mScreenWidth = Math.min(measuredWidth2, measuredHeight2);
                }
                this.mPositionOffsetPixels = this.mScreenWidth;
            }
            z = false;
            if (this.mIvPreloadPictoralImage.getLayoutParams().width == -1) {
                z2 = z;
            } else {
                z2 = z;
            }
            if (z2) {
                requestLayout();
            }
        }
        onWallpaperChange();
        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mEntryPictorialSlidePage = false;
        this.mOffset = 1.0f;
    }

    public final void startPictorialSlidePage(String str) {
        boolean zEquals = "slide".equals(str);
        boolean zEquals2 = "affordance".equals(str);
        boolean z = true;
        boolean z2 = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOffAndCanLeftSwipe() && !((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchStatusOn();
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (!pictorialMediator.isHKStyle() && !pictorialMediator.isTbStyle() && !z2 && !pictorialMediator.isGlanceStyle()) {
            z = false;
        }
        if (pictorialMediator.isOplusGLStyle()) {
            if (!zEquals2 && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mEnablePlaySwitchOn && this.mWallpaperInfo == null) {
                KgdLogUtil.i("PictorialPreloadView", "startPictorialSlidePage mWallpaperInfo is null on VerticalStyle");
                return;
            }
        } else if (((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isFlavorOneExpLH()) {
            if (!((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn()) {
                KgdLogUtil.i("PictorialPreloadView", "startPictorialSlidePage LH is disable");
                return;
            }
        } else if (!zEquals2 && this.mWallpaperInfo == null && !z) {
            KgdLogUtil.i("PictorialPreloadView", "startPictorialSlidePage mWallpaperInfo is null");
            return;
        } else if (pictorialMediator.isGlanceStyle() && !pictorialMediator.mEnablePlaySwitchOn) {
            return;
        }
        if (!((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).isKeyguardVisible$1()) {
            KgdLogUtil.i("PictorialPreloadView", "startPictorialSlidePage not showing, so return");
            return;
        }
        if (zEquals && this.mStartingPictorialSlidePage) {
            return;
        }
        if (((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).isFingerprintAuthenticated() || ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).getIsFaceAuthenticated() || ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mKeyguardGoingAway) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportMotorHardware()) {
                KgdLogUtil.i("PictorialPreloadView", "startPictorialSlidePage is already unlock, so return");
                return;
            }
        }
        int mType = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusLockScreenWallpaperController().getCurrentWallpaperInfo() != null ? ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusLockScreenWallpaperController().getCurrentWallpaperInfo().getMType() : 0;
        PictorialPreloadView$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new PictorialPreloadView$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = zEquals;
        externalSyntheticLambda1.f$2 = str;
        externalSyntheticLambda1.f$3 = mType;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(externalSyntheticLambda1);
    }

    public PictorialPreloadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PictorialPreloadView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, -1, -1);
    }

    public PictorialPreloadView(Context context) {
        this(context, null);
    }
}
