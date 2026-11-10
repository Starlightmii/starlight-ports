package com.oplus.systemui.volume.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioSystem;
import android.os.Debug;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.AlphaOptimizedImageButton;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.volume.ConfigurableTexts;
import com.android.systemui.volume.Events;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.osense.OsenseResClient;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.volume.OplusCaptionsToggleImageButton;
import com.oplus.systemui.volume.OplusVolumeCaptions;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeDialogImpl$$ExternalSyntheticLambda7;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplus.systemui.volume.VolumeListAdapter;
import com.oplus.systemui.volume.anim.OplusVolumePanelAccessoryAnimator;
import com.oplus.systemui.volume.anim.OplusVolumeSideShowSession;
import com.oplus.systemui.volume.anim.PanelExpandPreludeAnimator;
import com.oplus.systemui.volume.anim.PanelTransitionSession;
import com.oplus.systemui.volume.anim.VolumeItemAnimator;
import com.oplus.systemui.volume.eximpl.OplusVolumeControllerExImpl;
import com.oplus.systemui.volume.manager.VolumeSupplyExportApi;
import com.oplus.systemui.volume.observer.AnimationScaleSettingsObserver;
import com.oplus.systemui.volume.observer.MultiMediaSettingsObserver;
import com.oplus.systemui.volume.repository.MultiMediaRepository;
import com.oplus.systemui.volume.repository.SuperVolumeRepository;
import com.oplus.systemui.volume.utils.OplusVolumeAnimationJankTrace;
import com.oplus.systemui.volume.utils.OplusVolumeFrameRateUtilKt;
import com.oplus.systemui.volume.utils.OplusVolumePanelTiming;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplus.systemui.volume.utils.VolumeLayoutUtil;
import com.oplus.systemui.volume.utils.effect.OplusVolumeMaterialEffectObserver;
import com.oplus.systemui.volume.utils.material.OplusVolumeSettingsButtonDrawableHelper;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplus.systemui.volume.utils.material.VolumePlatformBlurHostHelper;
import com.oplus.systemui.volume.utils.material.VolumePlatformBlurSession;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView {
    public static final Companion Companion = new Companion();
    public static final String MULTI_APP_VOLUME_COUNT = "multi_app_volume_show_count";
    public static final String TAG = "OplusVolumeDialogView";
    public static float sScale;
    public static int screenHeight;
    private final VolumePlatformBlurHostHelper blurHostHelper;
    public boolean configChanged;
    private final Context context;
    private Job dismissFallbackJob;
    public boolean iconParentButtonSizeChanged;
    public boolean isSeekbarBgReplaced;
    private OplusVolumeSideAccessoryButton mAppVolumeAdjustFl;
    private ConfigurableTexts mConfigurableTexts;
    private ViewGroup mDialogView;
    public int mGfxSceneId;
    public boolean mHovering;
    public boolean mIsDialogAnimRunning;
    public boolean mIsRowParentAnimRunning;
    private OplusVolumeSideAccessoryButton mMoreRowStreamLl;
    private ImageButton mMoreStreamsButton;
    private EffectiveAnimationView mODICaptionsAnimation;
    private OplusCaptionsToggleImageButton mODICaptionsIcon;
    private ViewStub mODICaptionsTooltipViewStub;
    private OplusVolumeSideAccessoryButton mODICaptionsView;
    private ViewTreeObserver.OnComputeInternalInsetsListener mOnComputeInternalInsetsListener;
    private View mPortraitPanelSliderTouchTarget;
    private OplusVolumeSettingsButton mSettingsButton;
    private COUISnackBar mSnackBar;
    private ViewGroup mVerticalRowIconParent;
    private TextView mVolumePanelTipsText;
    private ImageView mVolumePanelTitleIcon;
    private TextView mVolumePanelTitleText;
    private RecyclerView mVolumeRecyclerview;
    private TextView mVolumeSettingText;
    private Window mWindow;
    public float marginLeft;
    private Runnable materialEffectUnregister;
    private Context newContext;
    private final OplusVolumePanelAccessoryAnimator panelAccessoryAnimator;
    private final PanelExpandPreludeAnimator panelExpandPrelude;
    private Runnable panelHideEndRunnable;
    private Runnable pendingTerminalHide;
    private View pendingTerminalHideRoot;
    private final VolumePlatformBlurSession platformBlurSession;
    private FrameLayout singleAppVolumeButtonContainer;
    public long terminalHideGeneration;
    private final CoroutineScope viewScope;
    private final ArrayList<OplusVolumeRow> visibleRowList;
    private final ArrayList<ConstraintLayout.LayoutParams> visibleSlideList;
    private CustomDialog volumeDialog;
    private final Lazy volumeDimensions$delegate;
    private final OplusVolumeDialogImpl volumeInteractor;
    private VolumeListAdapter volumeListAdapter;
    private SliderState sliderState = SliderState.DISMISSED;
    public boolean mIsDefaultVolumeTheme = true;
    private final PanelTransitionSession panelTransitionSession = new PanelTransitionSession();
    private final OplusVolumeSideShowSession sideShowSession = new OplusVolumeSideShowSession();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getSScale$annotations() {
        }

        public final float getSScale() {
            return OplusVolumeDialogView.sScale;
        }

        public final int getScreenHeight() {
            return OplusVolumeDialogView.screenHeight;
        }

        public final void setSScale(float f) {
            OplusVolumeDialogView.sScale = f;
        }

        public final void setScreenHeight(int i) {
            OplusVolumeDialogView.screenHeight = i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CustomDialog extends Dialog implements DialogInterface {
        public CustomDialog(Context context) {
            super(context, 2132020277);
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            OplusVolumeDialogView.this.getVolumeInteractor().rescheduleTimeoutH();
            if (OplusVolumeDialogView.access$tryDispatchPortraitPanelSliderTouch(OplusVolumeDialogView.this, motionEvent)) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.app.Dialog
        public final void onBackPressed() {
            OplusVolumeDialogView.this.getVolumeInteractor().dismissH$1(1);
        }

        @Override // android.app.Dialog
        public final void onStart() {
            setCanceledOnTouchOutside(true);
            super.onStart();
        }

        @Override // android.app.Dialog
        public final void onStop() {
            super.onStop();
            OplusVolumeDialogView.this.getVolumeInteractor().mHandler.sendEmptyMessage(4);
        }

        @Override // android.app.Dialog
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean zInRangeOfView;
            boolean zInRangeOfView2;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onTouchEvent: mDialog.isShowing: ", "mShowing = ", "OplusVolumeDialogView-->", isShowing(), isShowing()));
            }
            if (isShowing()) {
                if (!OplusVolumeDialogView.this.getVolumeInteractor().mExpanded) {
                    OplusVolumeDialogImpl volumeInteractor = OplusVolumeDialogView.this.getVolumeInteractor();
                    OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = OplusVolumeDialogView.this.mMoreRowStreamLl;
                    volumeInteractor.getClass();
                    if (!OplusVolumeDialogImpl.inRangeOfView(motionEvent, oplusVolumeSideAccessoryButton)) {
                        OplusVolumeDialogView.this.getVolumeInteractor().dismiss(1);
                        return true;
                    }
                }
                if (OplusVolumeDialogView.this.getVolumeInteractor().mExpanded) {
                    OplusVolumeDialogImpl volumeInteractor2 = OplusVolumeDialogView.this.getVolumeInteractor();
                    OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = OplusVolumeDialogView.this.mMoreRowStreamLl;
                    volumeInteractor2.getClass();
                    if (!OplusVolumeDialogImpl.inRangeOfView(motionEvent, oplusVolumeSideAccessoryButton2)) {
                        OplusVolumeDialogView.this.getVolumeInteractor().dismiss(1);
                        return true;
                    }
                }
                if (motionEvent.getAction() == 1) {
                    OplusVolumeRow oplusVolumeRowFindRow = OplusVolumeDialogView.this.getVolumeInteractor().findRow(5);
                    OplusVolumeRow oplusVolumeRowFindRow2 = OplusVolumeDialogView.this.getVolumeInteractor().findRow(2);
                    if (oplusVolumeRowFindRow != null) {
                        OplusVolumeDialogImpl volumeInteractor3 = OplusVolumeDialogView.this.getVolumeInteractor();
                        FrameLayout sliderFrame = oplusVolumeRowFindRow.getSliderFrame();
                        volumeInteractor3.getClass();
                        zInRangeOfView = OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame);
                    } else {
                        zInRangeOfView = false;
                    }
                    if (oplusVolumeRowFindRow2 != null) {
                        OplusVolumeDialogImpl volumeInteractor4 = OplusVolumeDialogView.this.getVolumeInteractor();
                        FrameLayout sliderFrame2 = oplusVolumeRowFindRow2.getSliderFrame();
                        volumeInteractor4.getClass();
                        zInRangeOfView2 = OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame2);
                    } else {
                        zInRangeOfView2 = false;
                    }
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isOplusAlertSlider() && OplusVolumeDialogView.this.getVolumeInteractor().mState != null && ((OplusVolumeDialogView.this.getVolumeInteractor().mState.ringerModeInternal == 0 || OplusVolumeDialogView.this.getVolumeInteractor().mState.ringerModeInternal == 1) && (zInRangeOfView || zInRangeOfView2))) {
                        if (OplusVolumeDialogView.this.getVolumeInteractor().mState.ringerModeInternal == 0 || ThreeKeyStealthModeObserver.Companion.getInstance().isStealthModeOn()) {
                            OplusVolumeDialogView.this.getVolumeInteractor().isNeedToShowToastUi(4);
                            return true;
                        }
                        OplusVolumeDialogView.this.getVolumeInteractor().isNeedToShowToastUi(5);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SliderState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ SliderState[] $VALUES;
        public static final SliderState DISMISSED;
        public static final SliderState DISMISSING;
        public static final SliderState SHOWING;

        static {
            SliderState sliderState = new SliderState("DISMISSED", 0);
            DISMISSED = sliderState;
            SliderState sliderState2 = new SliderState("SHOWING", 1);
            SHOWING = sliderState2;
            SliderState sliderState3 = new SliderState("DISMISSING", 2);
            DISMISSING = sliderState3;
            SliderState[] sliderStateArr = {sliderState, sliderState2, sliderState3};
            $VALUES = sliderStateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(sliderStateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static SliderState valueOf(String str) {
            return (SliderState) Enum.valueOf(SliderState.class, str);
        }

        public static SliderState[] values() {
            return (SliderState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VolumeDimensions {
        public float buttonHeight;
        public float cornerRadius;
        public float seekbarHeight;

        public VolumeDimensions(float f, float f2, float f3) {
            this.seekbarHeight = f;
            this.buttonHeight = f2;
            this.cornerRadius = f3;
        }

        public final VolumeDimensions copy(float f, float f2, float f3) {
            return new VolumeDimensions(f, f2, f3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VolumeDimensions)) {
                return false;
            }
            VolumeDimensions volumeDimensions = (VolumeDimensions) obj;
            return Float.compare(this.seekbarHeight, volumeDimensions.seekbarHeight) == 0 && Float.compare(this.buttonHeight, volumeDimensions.buttonHeight) == 0 && Float.compare(this.cornerRadius, volumeDimensions.cornerRadius) == 0;
        }

        public final float getButtonHeight() {
            return this.buttonHeight;
        }

        public final float getCornerRadius() {
            return this.cornerRadius;
        }

        public final float getSeekbarHeight() {
            return this.seekbarHeight;
        }

        public final int hashCode() {
            return Float.hashCode(this.cornerRadius) + ChangeSize$.ExternalSyntheticOutline0.m(this.buttonHeight, Float.hashCode(this.seekbarHeight) * 31, 31);
        }

        public final void setButtonHeight(float f) {
            this.buttonHeight = f;
        }

        public final void setCornerRadius(float f) {
            this.cornerRadius = f;
        }

        public final void setSeekbarHeight(float f) {
            this.seekbarHeight = f;
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.cornerRadius, ")", MonoSpline$.ExternalSyntheticOutline0.m("VolumeDimensions(seekbarHeight=", this.seekbarHeight, ", buttonHeight=", this.buttonHeight, ", cornerRadius="));
        }

        public final void updateDimensions(Resources resources) {
            this.seekbarHeight = resources.getDimensionPixelSize(2131173975);
            this.buttonHeight = resources.getDimensionPixelSize(2131173949);
            this.cornerRadius = resources.getDimensionPixelSize(2131173968);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$doShowOrHideAnimate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ boolean $changeAlpha;
        public /* synthetic */ boolean $expanded;
        public /* synthetic */ boolean $needResetY;
        public /* synthetic */ Runnable $onEnd;
        public /* synthetic */ int $reason;
        public /* synthetic */ View $target;
        public /* synthetic */ boolean $toShow;
        public /* synthetic */ float $translationX;
        public /* synthetic */ OplusVolumeDialogView this$0;

        @Override // java.lang.Runnable
        public final void run() {
            VolumeAnimUtil.resetShowOrHideAnimateView(this.$target, this.$translationX, this.$toShow, 3, Boolean.valueOf(this.$needResetY));
            if (this.$changeAlpha) {
                if (this.$toShow) {
                    this.this$0.getVolumeInteractor().rescheduleTimeoutH();
                } else {
                    int i = this.$reason;
                    if (i != -1) {
                        this.this$0.doAfterHide(i, this.$expanded);
                    }
                }
            }
            this.$onEnd.run();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$getSeekbarInternalInsetsListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewTreeObserverOnComputeInternalInsetsListenerC03101 implements ViewTreeObserver.OnComputeInternalInsetsListener {
        public /* synthetic */ OplusVolumeDialogView this$0;

        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            internalInsetsInfo.setTouchableInsets(3);
            Region region = new Region();
            Rect rect = new Rect();
            COUISnackBar cOUISnackBar = this.this$0.mSnackBar;
            if (cOUISnackBar != null && cOUISnackBar.getGlobalVisibleRect(rect)) {
                region.op(rect, Region.Op.UNION);
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.this$0.mMoreRowStreamLl;
            if (oplusVolumeSideAccessoryButton != null && oplusVolumeSideAccessoryButton.getGlobalVisibleRect(rect)) {
                region.op(rect, Region.Op.UNION);
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.this$0.mAppVolumeAdjustFl;
            if (oplusVolumeSideAccessoryButton2 != null && oplusVolumeSideAccessoryButton2.getGlobalVisibleRect(rect)) {
                region.op(rect, Region.Op.UNION);
            }
            RecyclerView recyclerView = this.this$0.mVolumeRecyclerview;
            if (recyclerView != null) {
                OplusVolumeDialogView oplusVolumeDialogView = this.this$0;
                if (recyclerView.getGlobalVisibleRect(rect)) {
                    region.op(rect, Region.Op.UNION);
                    OplusVolumeDialogView.access$cutRecyclerViewBlankArea(oplusVolumeDialogView, recyclerView, region);
                }
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton3 = this.this$0.mODICaptionsView;
            if (oplusVolumeSideAccessoryButton3 != null && oplusVolumeSideAccessoryButton3.getGlobalVisibleRect(rect)) {
                region.op(rect, Region.Op.UNION);
            }
            internalInsetsInfo.touchableRegion.set(region);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$initDialog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnHoverListenerC03111 implements View.OnHoverListener {
        public /* synthetic */ OplusVolumeDialogView this$0;

        @Override // android.view.View.OnHoverListener
        public final boolean onHover(View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            this.this$0.setMHovering(actionMasked == 9 || actionMasked == 7);
            this.this$0.getVolumeInteractor().rescheduleTimeoutH();
            if (actionMasked == 9 && this.this$0.getVolumeInteractor().mExpanded) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                View viewAccess$findVolumeSliderAtPosition = OplusVolumeDialogView.access$findVolumeSliderAtPosition(this.this$0, rawX, rawY);
                if (viewAccess$findVolumeSliderAtPosition == null) {
                    viewAccess$findVolumeSliderAtPosition = OplusVolumeDialogView.access$findVolumeTextAtPosition(this.this$0, rawX, rawY);
                }
                if (viewAccess$findVolumeSliderAtPosition != null) {
                    viewAccess$findVolumeSliderAtPosition.performAccessibilityAction(64, null);
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$initDialog$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnAttachStateChangeListener {
        public /* synthetic */ OplusVolumeDialogView this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, OplusVolumeDialogView.TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onViewAttachedToWindow isLowGaussianLevelOrSuperPowerSave: ", ", mIsDefaultVolumeTheme = ", VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.this$0.getContext()), this.this$0.getMIsDefaultVolumeTheme()), null, 8);
            this.this$0.blurHostHelper.registerOliveTuning();
            Runnable runnable = this.this$0.materialEffectUnregister;
            if (runnable != null) {
                runnable.run();
            }
            OplusVolumeDialogView oplusVolumeDialogView = this.this$0;
            Context context = oplusVolumeDialogView.getContext();
            OplusVolumeDialogView oplusVolumeDialogView2 = this.this$0;
            flushRowAndIconParent.3 r1 = new flushRowAndIconParent.3(1);
            r1.this$0 = oplusVolumeDialogView2;
            VarHandle.storeStoreFence();
            flushRowAndIconParent.3 r2 = new flushRowAndIconParent.3(2);
            r2.this$0 = oplusVolumeDialogView2;
            VarHandle.storeStoreFence();
            oplusVolumeDialogView.materialEffectUnregister = OplusVolumeMaterialEffectObserver.register(context, r1, r2);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            Runnable runnable = this.this$0.materialEffectUnregister;
            if (runnable != null) {
                runnable.run();
            }
            this.this$0.materialEffectUnregister = null;
            this.this$0.blurHostHelper.unregisterOliveTuning();
            this.this$0.removeInsetsListener();
            this.this$0.platformBlurSession.onDetached(view);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC03121 implements Runnable {
        public /* synthetic */ int $enterAttachRetry;
        public /* synthetic */ int $reason;
        public /* synthetic */ OplusVolumeRow $row;
        public /* synthetic */ Long $sideEnterGeneration;
        public /* synthetic */ boolean $toShow;
        public /* synthetic */ float $translationX;
        public /* synthetic */ OplusVolumeDialogView this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.startShowOrHideAnimWithOnlyRow(this.$translationX, this.$toShow, this.$reason, this.$row, this.$enterAttachRetry + 1, this.$sideEnterGeneration);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$updateAppVolumeAdjust$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03132 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref.IntRef $buttonUnderSeekbarNumber;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$updateAppVolumeAdjust$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Ref.IntRef $buttonUnderSeekbarNumber;
            int label;
            final /* synthetic */ OplusVolumeDialogView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusVolumeDialogView oplusVolumeDialogView, Ref.IntRef intRef, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusVolumeDialogView;
                this.$buttonUnderSeekbarNumber = intRef;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$buttonUnderSeekbarNumber, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                FeatureOption.INSTANCE.getClass();
                if (!FeatureOption.isExpRegion()) {
                    Settings.System.putInt(this.this$0.getContext().getContentResolver(), OplusVolumeDialogView.MULTI_APP_VOLUME_COUNT, this.$buttonUnderSeekbarNumber.element);
                    Ref.IntRef intRef = this.$buttonUnderSeekbarNumber;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intRef.element, "set MULTI_APP_VOLUME_COUNT : ", "OplusVolumeDialogView-->", "SystemUi--Volume");
                    }
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03132(Ref.IntRef intRef, Continuation continuation) {
            super(2, continuation);
            this.$buttonUnderSeekbarNumber = intRef;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusVolumeDialogView.this.new C03132(this.$buttonUnderSeekbarNumber, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusVolumeDialogView.this, this.$buttonUnderSeekbarNumber, null);
                this.label = 1;
                if (BuildersKt.withContext(io, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$updateRowsH$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03141 extends SuspendLambda implements Function2 {
        final /* synthetic */ OplusVolumeRow $activeRow;
        final /* synthetic */ Function0 $onComplete;
        final /* synthetic */ boolean $sideNotify;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$updateRowsH$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01761 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusVolumeRow $activeRow;
            final /* synthetic */ Function0 $onComplete;
            final /* synthetic */ boolean $sideNotify;
            int label;
            final /* synthetic */ OplusVolumeDialogView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01761(OplusVolumeDialogView oplusVolumeDialogView, OplusVolumeRow oplusVolumeRow, boolean z, Function0 function0, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusVolumeDialogView;
                this.$activeRow = oplusVolumeRow;
                this.$sideNotify = z;
                this.$onComplete = function0;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01761(this.this$0, this.$activeRow, this.$sideNotify, this.$onComplete, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                OplusVolumeDialogView.access$updateSystemRows(this.this$0, this.$activeRow);
                if (!this.this$0.getVolumeInteractor().mExpanded && this.$sideNotify && this.this$0.isSliderShowing() && !this.this$0.isDismissing()) {
                    OplusVolumeDialogView.access$requestSideStructuralUpdate(this.this$0);
                }
                Function0 function0 = this.$onComplete;
                if (function0 != null) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03141(OplusVolumeRow oplusVolumeRow, boolean z, Function0 function0, Continuation continuation) {
            super(2, continuation);
            this.$activeRow = oplusVolumeRow;
            this.$sideNotify = z;
            this.$onComplete = function0;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusVolumeDialogView.this.new C03141(this.$activeRow, this.$sideNotify, this.$onComplete, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusVolumeDialogView.this.getVolumeInteractor().mAudioDataRepository.fetchStreamActiveState();
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C01761 c01761 = new C01761(OplusVolumeDialogView.this, this.$activeRow, this.$sideNotify, this.$onComplete, null);
                this.label = 1;
                if (BuildersKt.withContext(main, c01761, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static boolean $r8$lambda$1BFdyl26VaukWNmNIpFbwyq2h0E(OplusVolumeDialogView oplusVolumeDialogView) {
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        boolean z = false;
        if (volumeListAdapter != null && !volumeListAdapter.isSystemPanel) {
            z = true;
        }
        return !z;
    }

    public static VolumeDimensions $r8$lambda$3TLQ4uLzK5Lh9hj4X1udsgcbcCs(OplusVolumeDialogView oplusVolumeDialogView) {
        Resources resources;
        VolumeDimensions volumeDimensions = new VolumeDimensions(0.0f, 0.0f, 0.0f);
        Context context = oplusVolumeDialogView.newContext;
        if (context != null && (resources = context.getResources()) != null) {
            volumeDimensions.updateDimensions(resources);
        }
        return volumeDimensions;
    }

    /* JADX INFO: renamed from: $r8$lambda$5hMb88bCr1D-Of6UnBJA7J1RGg0, reason: not valid java name */
    public static Context m486$r8$lambda$5hMb88bCr1DOf6UnBJA7J1RGg0(OplusVolumeDialogView oplusVolumeDialogView) {
        Context context = oplusVolumeDialogView.newContext;
        return context == null ? oplusVolumeDialogView.context : context;
    }

    /* JADX INFO: renamed from: $r8$lambda$L2HRpWTcJtSzCJWxnqoXEky-O_w, reason: not valid java name */
    public static Unit m487$r8$lambda$L2HRpWTcJtSzCJWxnqoXEkyO_w(OplusVolumeDialogView oplusVolumeDialogView, float f) {
        oplusVolumeDialogView.blurHostHelper.applyBarPanelBottomProgress(f);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$PiyLj7mJPt5V0MkpBOs0K4E4OhY(OplusVolumeDialogView oplusVolumeDialogView, long j) {
        oplusVolumeDialogView.showOrHideAnimation(oplusVolumeDialogView.getSideAnimTranslationX(), true, -1, Long.valueOf(j));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$aY0QqZuF1IcmK97WtJitEFofkgE(OplusVolumeDialogView oplusVolumeDialogView, long j) {
        oplusVolumeDialogView.blurHostHelper.scheduleSideAccessoryBlurCaptureRefresh();
        VolumePlatformBlurHostHelper volumePlatformBlurHostHelper = oplusVolumeDialogView.blurHostHelper;
        OplusVolumeDialogView$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new OplusVolumeDialogView$.ExternalSyntheticLambda9(1);
        externalSyntheticLambda9.f$0 = oplusVolumeDialogView;
        externalSyntheticLambda9.f$1 = j;
        VarHandle.storeStoreFence();
        volumePlatformBlurHostHelper.enterSideCaptureWhenSettled(externalSyntheticLambda9);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$r91muaz4yMzSOHavZ9oClR42MLk(OplusVolumeDialogView oplusVolumeDialogView, OplusVolumeDialogView$.ExternalSyntheticLambda9 externalSyntheticLambda9) {
        if (oplusVolumeDialogView.volumeInteractor.mExpanded) {
            return Unit.INSTANCE;
        }
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter != null) {
            volumeListAdapter.submitSideListForEnter(externalSyntheticLambda9);
        } else {
            externalSyntheticLambda9.invoke();
        }
        return Unit.INSTANCE;
    }

    public OplusVolumeDialogView(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        blurHostHelper.1 r1 = new blurHostHelper.1(2);
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        VolumePlatformBlurSession volumePlatformBlurSession = new VolumePlatformBlurSession(context, r1);
        this.platformBlurSession = volumePlatformBlurSession;
        blurHostHelper.1 r2 = new blurHostHelper.1(0);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        this.blurHostHelper = new VolumePlatformBlurHostHelper(volumePlatformBlurSession, r2);
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(0);
        oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda1 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(5);
        oplusVolumeDialogView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda2 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(6);
        oplusVolumeDialogView$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda3 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(7);
        oplusVolumeDialogView$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda4 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(8);
        oplusVolumeDialogView$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        OplusVolumePanelAccessoryAnimator oplusVolumePanelAccessoryAnimator = new OplusVolumePanelAccessoryAnimator(context, oplusVolumeDialogView$$ExternalSyntheticLambda0, oplusVolumeDialogView$$ExternalSyntheticLambda1, oplusVolumeDialogView$$ExternalSyntheticLambda2, oplusVolumeDialogView$$ExternalSyntheticLambda3, oplusVolumeDialogView$$ExternalSyntheticLambda4);
        OplusVolumeDialogView$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new OplusVolumeDialogView$.ExternalSyntheticLambda5();
        externalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        oplusVolumePanelAccessoryAnimator.setOnPanelBackgroundProgress(externalSyntheticLambda5);
        this.panelAccessoryAnimator = oplusVolumePanelAccessoryAnimator;
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda5 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(9);
        oplusVolumeDialogView$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.panelExpandPrelude = new PanelExpandPreludeAnimator(oplusVolumeDialogView$$ExternalSyntheticLambda5);
        this.visibleSlideList = new ArrayList<>();
        this.visibleRowList = new ArrayList<>();
        this.configChanged = true;
        this.viewScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getDefault()));
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda6 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(10);
        oplusVolumeDialogView$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeDimensions$delegate = LazyKt__LazyJVMKt.lazy(oplusVolumeDialogView$$ExternalSyntheticLambda6);
    }

    public static final void access$applyDialogSessionCleanup(OplusVolumeDialogView oplusVolumeDialogView) {
        int i = oplusVolumeDialogView.mGfxSceneId;
        if (i != 0) {
            OplusVolumeAnimationJankTrace.endScene(oplusVolumeDialogView.context, i);
            oplusVolumeDialogView.mGfxSceneId = 0;
        }
        OplusVolumeDialogImpl oplusVolumeDialogImpl = oplusVolumeDialogView.volumeInteractor;
        oplusVolumeDialogImpl.mIsIconClicked = false;
        oplusVolumeDialogImpl.mController.notifyVisible(false);
        oplusVolumeDialogView.volumeInteractor.mOplusVolumeController.setPanelExpandedSysUiFlag(false);
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter != null) {
            volumeListAdapter.startBackAim();
        }
        OplusVolumeDialogImpl oplusVolumeDialogImpl2 = oplusVolumeDialogView.volumeInteractor;
        OsenseResClient osenseResClient = oplusVolumeDialogImpl2.mOsenseResClient;
        if (osenseResClient != null) {
            osenseResClient.osenseClrSceneAction(oplusVolumeDialogImpl2.mRequest);
        }
        oplusVolumeDialogView.blurHostHelper.clearPanelBackgroundCache();
        oplusVolumeDialogView.blurHostHelper.clearBarBackgroundCache();
        oplusVolumeDialogView.volumeInteractor.setHasTopUi(false);
        oplusVolumeDialogView.volumeInteractor.resetRowTrackingState();
        OplusSysUiSchedMgr.Companion.getInstance().schedForVolumeDialog(false);
        EffectiveAnimationView effectiveAnimationView = oplusVolumeDialogView.mODICaptionsAnimation;
        if (effectiveAnimationView != null) {
            effectiveAnimationView.removeAllAnimatorListeners();
        }
        if (oplusVolumeDialogView.volumeInteractor.getExpandedState().booleanValue()) {
            ViewGroup viewGroup = oplusVolumeDialogView.mDialogView;
            if (viewGroup != null) {
                OplusVolumeFrameRateUtilKt.resetFrameRate(viewGroup, 20502);
            }
        } else {
            ViewGroup viewGroup2 = oplusVolumeDialogView.mVerticalRowIconParent;
            if (viewGroup2 != null) {
                OplusVolumeFrameRateUtilKt.resetFrameRate(viewGroup2, 20501);
            }
        }
        OplusVolumeRow activeRow = oplusVolumeDialogView.volumeInteractor.getActiveRow();
        ObjectAnimator anim = activeRow != null ? activeRow.getAnim() : null;
        if (anim != null) {
            anim.removeAllListeners();
        }
        VolumeListAdapter volumeListAdapter2 = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter2 != null) {
            volumeListAdapter2.resumeItemAnimatorAfterSideEnterBind();
            volumeListAdapter2.prepareSideDataSource();
            VolumeListAdapter.AnonymousClass1.RunnableC01751 runnableC01751 = new VolumeListAdapter.AnonymousClass1.RunnableC01751(3);
            runnableC01751.$onComplete = volumeListAdapter2;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(runnableC01751);
        }
    }

    public static final void access$cutRecyclerViewBlankArea(OplusVolumeDialogView oplusVolumeDialogView, RecyclerView recyclerView, Region region) {
        Rect rect = new Rect();
        recyclerView.getGlobalVisibleRect(rect);
        Region region2 = new Region();
        int childCount = recyclerView.getChildCount();
        if (childCount > 1) {
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                Rect rect2 = new Rect();
                childAt.getGlobalVisibleRect(rect2);
                region2.op(rect2, Region.Op.UNION);
            }
        } else {
            Rect rect3 = new Rect();
            Rect rect4 = new Rect();
            View childAt2 = recyclerView.getChildAt(0);
            FrameLayout frameLayout = childAt2 != null ? (FrameLayout) childAt2.findViewById(2131364972) : null;
            if (frameLayout != null) {
                frameLayout.getGlobalVisibleRect(rect3);
                region2.op(rect3, Region.Op.UNION);
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = oplusVolumeDialogView.mMoreRowStreamLl;
            if (oplusVolumeSideAccessoryButton != null) {
                oplusVolumeSideAccessoryButton.getGlobalVisibleRect(rect4);
            }
            region2.op(rect4, Region.Op.UNION);
        }
        Region region3 = new Region(rect);
        Region.Op op = Region.Op.DIFFERENCE;
        region3.op(region2, op);
        region.op(region3, op);
    }

    public static final View access$findVolumeSliderAtPosition(OplusVolumeDialogView oplusVolumeDialogView, int i, int i2) {
        RecyclerView recyclerView = oplusVolumeDialogView.mVolumeRecyclerview;
        if (recyclerView == null) {
            return null;
        }
        int childCount = recyclerView.getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                return null;
            }
            View viewFindViewById = recyclerView.getChildAt(childCount).findViewById(2131364971);
            if (viewFindViewById != null && VolumeLayoutUtil.containsPanelAccessibilityBounds(viewFindViewById, i, i2)) {
                return viewFindViewById;
            }
        }
    }

    public static final View access$findVolumeTextAtPosition(OplusVolumeDialogView oplusVolumeDialogView, int i, int i2) {
        RecyclerView recyclerView = oplusVolumeDialogView.mVolumeRecyclerview;
        if (recyclerView == null) {
            return null;
        }
        int childCount = recyclerView.getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                return null;
            }
            View viewFindViewById = recyclerView.getChildAt(childCount).findViewById(2131366045);
            if (viewFindViewById != null && VolumeLayoutUtil.containsPanelAccessibilityBounds(viewFindViewById, i, i2)) {
                return viewFindViewById;
            }
        }
    }

    public static final void access$refreshVolumeBlurLight(OplusVolumeDialogView oplusVolumeDialogView) {
        ArrayList<OplusVolumeRow> volumeRowList;
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter != null && (volumeRowList = volumeListAdapter.getVolumeRowList()) != null) {
            int size = volumeRowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = volumeRowList.get(i);
                i++;
                OplusVolumeSeekBar slider = oplusVolumeRow.getSlider();
                if (slider != null) {
                    slider.mMaterialHost.refreshPlatformLightForGateChange();
                }
            }
        }
        OplusVolumeSettingsButton oplusVolumeSettingsButton = oplusVolumeDialogView.mSettingsButton;
        if (oplusVolumeSettingsButton != null) {
            oplusVolumeSettingsButton.refreshBlurLightEnableState(oplusVolumeDialogView.mIsDefaultVolumeTheme);
        }
        Iterator it = CollectionsKt__CollectionsKt.listOfNotNull(new OplusVolumeSideAccessoryButton[]{oplusVolumeDialogView.mMoreRowStreamLl, oplusVolumeDialogView.mAppVolumeAdjustFl, oplusVolumeDialogView.mODICaptionsView}).iterator();
        while (it.hasNext()) {
            ((OplusVolumeSideAccessoryButton) it.next()).refreshBlurLightEnableState(oplusVolumeDialogView.mIsDefaultVolumeTheme);
        }
    }

    public static final void access$refreshVolumeSpotLight(OplusVolumeDialogView oplusVolumeDialogView) {
        ArrayList<OplusVolumeRow> volumeRowList;
        int i = 0;
        oplusVolumeDialogView.refreshVolumeSpotLightType(false);
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter != null && (volumeRowList = volumeListAdapter.getVolumeRowList()) != null) {
            int size = volumeRowList.size();
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = volumeRowList.get(i);
                i++;
                OplusVolumeSeekBar slider = oplusVolumeRow.getSlider();
                if (slider != null) {
                    slider.refreshSpotLightEnableState();
                }
            }
        }
        OplusVolumeSettingsButton oplusVolumeSettingsButton = oplusVolumeDialogView.mSettingsButton;
        if (oplusVolumeSettingsButton != null) {
            oplusVolumeSettingsButton.isDefaultVolumeTheme = oplusVolumeDialogView.mIsDefaultVolumeTheme;
            oplusVolumeSettingsButton.applySpotLightPolicy();
        }
        for (OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton : CollectionsKt__CollectionsKt.listOfNotNull(new OplusVolumeSideAccessoryButton[]{oplusVolumeDialogView.mMoreRowStreamLl, oplusVolumeDialogView.mAppVolumeAdjustFl, oplusVolumeDialogView.mODICaptionsView})) {
            oplusVolumeSideAccessoryButton.isDefaultVolumeTheme = oplusVolumeDialogView.mIsDefaultVolumeTheme;
            oplusVolumeSideAccessoryButton.applySpotLightPolicy();
        }
    }

    public static final void access$requestSideStructuralUpdate(OplusVolumeDialogView oplusVolumeDialogView) {
        if (oplusVolumeDialogView.volumeInteractor.mExpanded) {
            return;
        }
        VolumeListAdapter volumeListAdapter = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter != null) {
            volumeListAdapter.filterSideList();
        }
        VolumeListAdapter volumeListAdapter2 = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter2 == null || !volumeListAdapter2.needsSideStructuralSubmit()) {
            return;
        }
        if (oplusVolumeDialogView.sideShowSession.shouldDeferStructuralSubmit() || oplusVolumeDialogView.shouldBlockSideStructuralForAnim()) {
            oplusVolumeDialogView.sideShowSession.enqueueStructuralUpdate();
            return;
        }
        VolumeListAdapter volumeListAdapter3 = oplusVolumeDialogView.volumeListAdapter;
        if (volumeListAdapter3 != null) {
            OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(2);
            oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = oplusVolumeDialogView;
            VarHandle.storeStoreFence();
            volumeListAdapter3.flushPendingSideList(oplusVolumeDialogView$$ExternalSyntheticLambda0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00c3  */
    public static final boolean access$tryDispatchPortraitPanelSliderTouch(OplusVolumeDialogView oplusVolumeDialogView, MotionEvent motionEvent) {
        VolumeListAdapter volumeListAdapter;
        RecyclerView recyclerView;
        View viewFindViewById;
        View view;
        OplusVolumeDialogImpl oplusVolumeDialogImpl;
        VolumeDialogController.State state;
        int i;
        OplusVolumeRow oplusVolumeRowFindRow;
        OplusVolumeRow oplusVolumeRowFindRow2;
        boolean z;
        boolean z2 = false;
        if (oplusVolumeDialogView.volumeInteractor.mExpanded && (volumeListAdapter = oplusVolumeDialogView.volumeListAdapter) != null && volumeListAdapter.getCurrentDataSource() == 1 && oplusVolumeDialogView.isSliderShowing() && (recyclerView = oplusVolumeDialogView.mVolumeRecyclerview) != null) {
            int[] iArr = new int[2];
            recyclerView.getLocationOnScreen(iArr);
            Pair pair = new Pair(Float.valueOf(motionEvent.getRawX() - iArr[0]), Float.valueOf(motionEvent.getRawY() - iArr[1]));
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if ((actionMasked == 1 || actionMasked == 2 || actionMasked == 3 || actionMasked == 5 || actionMasked == 6) && (view = oplusVolumeDialogView.mPortraitPanelSliderTouchTarget) != null) {
                    dispatchTouchFromRecyclerToDescendant(recyclerView, view, motionEvent, fFloatValue, fFloatValue2);
                    if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                        oplusVolumeDialogView.mPortraitPanelSliderTouchTarget = null;
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isOplusAlertSlider() && (state = (oplusVolumeDialogImpl = oplusVolumeDialogView.volumeInteractor).mState) != null && (((i = state.ringerModeInternal) == 0 || i == 1) && (oplusVolumeRowFindRow = oplusVolumeDialogImpl.findRow(5)) != null && (oplusVolumeRowFindRow2 = oplusVolumeDialogView.volumeInteractor.findRow(2)) != null)) {
                            FrameLayout sliderFrame = oplusVolumeRowFindRow.getSliderFrame();
                            if (sliderFrame != null) {
                                oplusVolumeDialogView.volumeInteractor.getClass();
                                if (OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame)) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else {
                                z = false;
                            }
                            FrameLayout sliderFrame2 = oplusVolumeRowFindRow2.getSliderFrame();
                            if (sliderFrame2 != null) {
                                oplusVolumeDialogView.volumeInteractor.getClass();
                                if (OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame2)) {
                                    z2 = true;
                                }
                            }
                            if (z || z2) {
                                oplusVolumeDialogView.volumeInteractor.isNeedToShowToastUi((i == 0 || ThreeKeyStealthModeObserver.Companion.getInstance().isStealthModeOn()) ? 4 : 5);
                                return true;
                            }
                        }
                    }
                    return true;
                }
            } else {
                oplusVolumeDialogView.mPortraitPanelSliderTouchTarget = null;
                View viewFindItemViewAtTransformedCoords = VolumeLayoutUtil.findItemViewAtTransformedCoords(recyclerView, fFloatValue, fFloatValue2);
                if (viewFindItemViewAtTransformedCoords != null && (viewFindViewById = viewFindItemViewAtTransformedCoords.findViewById(2131364971)) != null && dispatchTouchFromRecyclerToDescendant(recyclerView, viewFindViewById, motionEvent, fFloatValue, fFloatValue2)) {
                    oplusVolumeDialogView.mPortraitPanelSliderTouchTarget = viewFindViewById;
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v21, types: [android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void access$updateSystemRows(OplusVolumeDialogView oplusVolumeDialogView, OplusVolumeRow oplusVolumeRow) {
        ViewGroup viewGroup;
        VolumeDialogController.StreamState ss;
        ObjectAnimator anim;
        for (Iterator it = oplusVolumeDialogView.volumeInteractor.mRows.iterator(); it.hasNext(); it = it) {
            OplusVolumeRow oplusVolumeRow2 = (OplusVolumeRow) it.next();
            boolean zAreEqual = Intrinsics.areEqual(oplusVolumeRow2, oplusVolumeRow);
            OplusVolumeDialogImpl oplusVolumeDialogImpl = oplusVolumeDialogView.volumeInteractor;
            boolean zShouldBeVisibleH = oplusVolumeDialogImpl.shouldBeVisibleH(oplusVolumeRow2, oplusVolumeRow, oplusVolumeDialogImpl.mExpanded);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                int size = ((ArrayList) oplusVolumeDialogView.volumeInteractor.mRows).size();
                int stream = oplusVolumeRow2.getStream();
                boolean z = !oplusVolumeRow2.isVerticalLayout;
                boolean z2 = !oplusVolumeRow2.isMultiMediaVolume;
                View view = oplusVolumeRow2.getView();
                Boolean boolValueOf = view != null ? Boolean.valueOf(view.isShown()) : null;
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("mRows size:", size, stream, " ,row stream=", " ,shouldBeVisible=");
                KeyguardLogger$.ExternalSyntheticOutline0.m(" ,!row.isVerticalLayout=", " ,!row.isMultiMediaVolume=", sbM, zShouldBeVisibleH, z);
                sbM.append(z2);
                sbM.append(" ,row.view.isShown()=");
                sbM.append(boolValueOf);
                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->".concat(sbM.toString()));
            }
            oplusVolumeRow2.setShowInSide(oplusVolumeDialogView.volumeInteractor.shouldBeVisibleH(oplusVolumeRow2, oplusVolumeRow, false));
            oplusVolumeRow2.setShowInPanel(oplusVolumeDialogView.volumeInteractor.shouldBeVisibleH(oplusVolumeRow2, oplusVolumeRow, true));
            if (oplusVolumeDialogView.volumeInteractor.mExpanded) {
                if (!oplusVolumeRow2.isVerticalLayout && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("updateRowsH shouldBeVisible:", "OplusVolumeDialogView-->", "SystemUi--Volume", zShouldBeVisibleH);
                }
            } else if (zShouldBeVisibleH && !oplusVolumeRow2.isVerticalLayout && !oplusVolumeRow2.isMultiMediaVolume) {
                oplusVolumeDialogView.convertRowLayout(oplusVolumeRow2, false);
                ?? slider = oplusVolumeRow2.getSlider();
                if (slider != 0) {
                    slider.mMaterialHost.syncElevationAndOutline();
                    if (!oplusVolumeDialogView.isDismissing() && oplusVolumeDialogView.isSliderShowing() && (viewGroup = oplusVolumeDialogView.mDialogView) != null) {
                        if (viewGroup.getViewRootImpl() != null && !oplusVolumeDialogView.blurHostHelper.hasBarBackgroundCache(oplusVolumeRow2.getStream()) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow2.getStream(), "create layer ", "OplusVolumeDialogView-->", "SystemUi--Volume");
                        }
                        oplusVolumeDialogView.blurHostHelper.assignVolumeBarBackground(viewGroup, (OplusVolumeSeekBar) slider, oplusVolumeRow2.getStream(), true);
                        boolean z3 = Math.abs(slider.getTranslationX()) > 1.0f;
                        VolumeDialogController.StreamState ss2 = oplusVolumeRow2.getSs();
                        boolean z4 = ss2 != null && OplusVolumeRow.Companion.getImpliedLevel(slider, slider.getProgress()) == ss2.level;
                        if (!oplusVolumeRow2.getTracking() && ((oplusVolumeRow2.getAnim() == null || (anim = oplusVolumeRow2.getAnim()) == null || !anim.isRunning()) && !z3 && !z4 && (ss = oplusVolumeRow2.getSs()) != null)) {
                            int i = ss.muted ? 0 : ss.level * 100;
                            if (slider.getProgress() != i) {
                                slider.setProgress(i);
                            }
                        }
                    }
                }
            }
            View view2 = oplusVolumeRow2.getView();
            if (view2 != null && view2.isShown()) {
                oplusVolumeDialogView.volumeInteractor.getClass();
                if (zAreEqual) {
                    oplusVolumeRow2.getSlider().requestFocus();
                }
            }
        }
    }

    public static boolean dispatchTouchFromRecyclerToDescendant(RecyclerView recyclerView, View view, MotionEvent motionEvent, float f, float f2) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setLocation(f, f2);
        ArrayList arrayList = new ArrayList();
        View view2 = view;
        while (view2 != null && view2 != recyclerView) {
            arrayList.add(0, view2);
            Object parent = view2.getParent();
            view2 = parent instanceof View ? (View) parent : null;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            View view3 = (View) obj;
            ViewParent parent2 = view3.getParent();
            ViewGroup viewGroup = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
            if (viewGroup == null) {
                motionEventObtain.recycle();
                return false;
            }
            motionEventObtain.offsetLocation(viewGroup.getScrollX() - view3.getLeft(), viewGroup.getScrollY() - view3.getTop());
            if (!view3.hasIdentityMatrix()) {
                Matrix matrix = new Matrix(view3.getMatrix());
                Matrix matrix2 = new Matrix();
                if (!matrix.invert(matrix2)) {
                    motionEventObtain.recycle();
                    return false;
                }
                motionEventObtain.transform(matrix2);
            }
        }
        boolean zDispatchTouchEvent = view.dispatchTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
        return zDispatchTouchEvent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getCornerRadius() {
        return getVolumeDimensions().getCornerRadius();
    }

    private final float getIconHalfDelta() {
        return getVolumeDimensions().getButtonHeight() / 2.0f;
    }

    public static final float getSScale() {
        return Companion.getSScale();
    }

    private final ViewTreeObserver.OnComputeInternalInsetsListener getSeekbarInternalInsetsListener() {
        ViewTreeObserverOnComputeInternalInsetsListenerC03101 viewTreeObserverOnComputeInternalInsetsListenerC03101 = new ViewTreeObserverOnComputeInternalInsetsListenerC03101();
        viewTreeObserverOnComputeInternalInsetsListenerC03101.this$0 = this;
        VarHandle.storeStoreFence();
        return viewTreeObserverOnComputeInternalInsetsListenerC03101;
    }

    private final float getSideAnimTranslationX() {
        List currentList;
        int size;
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        int i = 1;
        if (volumeListAdapter != null && (currentList = volumeListAdapter.getCurrentList()) != null && (size = currentList.size()) > 1) {
            i = size;
        }
        FeatureOption.INSTANCE.getClass();
        boolean zIsOplusVolumeKeyInRight = FeatureOption.isOplusVolumeKeyInRight();
        Context context = this.newContext;
        return zIsOplusVolumeKeyInRight ? VolumeLayoutUtil.dp2Px(i * 56.0f, context) + this.marginLeft : (-VolumeLayoutUtil.dp2Px(i * 56.0f, context)) - this.marginLeft;
    }

    private final Context getUpdateNewContext() {
        Context context = this.newContext;
        return context == null ? this.context : context;
    }

    private final VolumeDimensions getVolumeDimensions() {
        return (VolumeDimensions) this.volumeDimensions$delegate.getValue();
    }

    public static final void setSScale(float f) {
        Companion.setSScale(f);
    }

    private final void setViewsAlpha(float f) {
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mMoreRowStreamLl;
        if (oplusVolumeSideAccessoryButton != null) {
            oplusVolumeSideAccessoryButton.setAlpha(f);
        }
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mAppVolumeAdjustFl;
        if (oplusVolumeSideAccessoryButton2 != null) {
            oplusVolumeSideAccessoryButton2.setAlpha(f);
        }
    }

    public static /* synthetic */ void updateRowsH$default(OplusVolumeDialogView oplusVolumeDialogView, OplusVolumeRow oplusVolumeRow, boolean z, int i) {
        if ((i & 2) != 0) {
            z = false;
        }
        oplusVolumeDialogView.updateRowsH(oplusVolumeRow, z, null);
    }

    public final void adaptThemeDrawable(View view, View view2, View view3, boolean z) {
        Drawable drawableMutate;
        Drawable drawableMutate2;
        Drawable drawableNewDrawable;
        boolean z2 = false;
        this.iconParentButtonSizeChanged = false;
        this.isSeekbarBgReplaced = false;
        if (z) {
            return;
        }
        Drawable drawable = this.context.getDrawable(2131237396);
        boolean z3 = drawable instanceof BitmapDrawable;
        if (!z3) {
            drawable = this.context.getDrawable(2131237395);
        }
        if (drawable == null) {
            return;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (view != null) {
            if (constantState == null || (drawableNewDrawable = constantState.newDrawable()) == null) {
                drawableNewDrawable = drawable;
            }
            view.setBackground(drawableNewDrawable);
        }
        if (view2 != null) {
            if (constantState == null || (drawableMutate2 = constantState.newDrawable()) == null) {
                drawableMutate2 = drawable.mutate();
            }
            view2.setBackground(drawableMutate2);
        }
        if (view3 != null) {
            if (constantState == null || (drawableMutate = constantState.newDrawable()) == null) {
                drawableMutate = drawable.mutate();
            }
            view3.setBackground(drawableMutate);
        }
        if (!z3) {
            this.iconParentButtonSizeChanged = true;
            int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131173975);
            VolumeLayoutUtil.setHeight(view, dimensionPixelSize);
            VolumeLayoutUtil.setHeight(view2, dimensionPixelSize);
            VolumeLayoutUtil.setHeight(view3, dimensionPixelSize);
        }
        Drawable drawable2 = this.context.getDrawable(2131237443);
        if (drawable2 != null && !(drawable2 instanceof GradientDrawable)) {
            z2 = true;
        }
        this.isSeekbarBgReplaced = z2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->".concat(IconProvider$.ExternalSyntheticOutline0.m("adaptThemeDrawable use OS17 Background: ", " isSeekbarBgReplaced: ", " ", z3, this.isSeekbarBgReplaced)));
        }
    }

    public final void addSingleAppVolumeButtonController(FrameLayout frameLayout) {
        if (this.singleAppVolumeButtonContainer != null) {
            return;
        }
        ViewParent parent = frameLayout.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(frameLayout);
        }
        ViewGroup viewGroup2 = this.mDialogView;
        if (viewGroup2 != null) {
            viewGroup2.addView(frameLayout);
            this.singleAppVolumeButtonContainer = frameLayout;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->SingleAppVolumeButton container dynamically added to mDialogView");
            }
        }
    }

    public final void applyPanelLeaveCleanup() {
        endGfxSceneIfActive(this.newContext, 302);
        ViewGroup viewGroup = this.mDialogView;
        if (viewGroup != null) {
            OplusVolumeFrameRateUtilKt.endFrameRateElevate(viewGroup, 20502);
        }
        this.volumeInteractor.mOplusVolumeController.setPanelExpandedSysUiFlag(false);
        this.panelTransitionSession.reset();
        this.volumeInteractor.resetRowTrackingState();
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
        OsenseResClient osenseResClient = oplusVolumeDialogImpl.mOsenseResClient;
        if (osenseResClient != null) {
            osenseResClient.osenseClrSceneAction(oplusVolumeDialogImpl.mRequest);
        }
        this.blurHostHelper.handoffPanelToSideForReshow();
        this.panelAccessoryAnimator.prepareTitleForCollapsedState();
        AlphaOptimizedImageButton alphaOptimizedImageButton = this.mSettingsButton;
        if (alphaOptimizedImageButton != null) {
            alphaOptimizedImageButton.setVisibility(8);
        }
        TextView textView = this.mVolumeSettingText;
        if (textView != null) {
            textView.setVisibility(8);
        }
        setViewsAlpha(1.0f);
        ImageButton imageButton = this.mMoreStreamsButton;
        if (imageButton != null) {
            imageButton.setImageAlpha(255);
        }
        Window window = this.mWindow;
        if (window != null) {
            window.clearFlags(131072);
            window.addFlags(8);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.ImageButton, com.oplus.systemui.volume.view.OplusVolumeSettingsButton] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void applySettingsButtonDrawable(int i) {
        ?? r0 = this.mSettingsButton;
        if (r0 == 0) {
            return;
        }
        if (this.mIsDefaultVolumeTheme) {
            Drawable drawableCreate = OplusVolumeSettingsButtonDrawableHelper.create(r0.getContext(), i);
            if (drawableCreate == null) {
                return;
            }
            r0.setImageDrawable(drawableCreate);
            r0.refreshIconTintForPlatformBlur();
        }
        r0.refreshBlurLightEnableState(this.mIsDefaultVolumeTheme);
        r0.isDefaultVolumeTheme = this.mIsDefaultVolumeTheme;
        r0.applySpotLightPolicy();
        Iterator it = CollectionsKt__CollectionsKt.listOfNotNull(new OplusVolumeSideAccessoryButton[]{this.mMoreRowStreamLl, this.mAppVolumeAdjustFl, this.mODICaptionsView}).iterator();
        while (it.hasNext()) {
            ((OplusVolumeSideAccessoryButton) it.next()).refreshBlurLightEnableState(this.mIsDefaultVolumeTheme);
        }
        for (OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton : CollectionsKt__CollectionsKt.listOfNotNull(new OplusVolumeSideAccessoryButton[]{this.mMoreRowStreamLl, this.mAppVolumeAdjustFl, this.mODICaptionsView})) {
            oplusVolumeSideAccessoryButton.isDefaultVolumeTheme = this.mIsDefaultVolumeTheme;
            oplusVolumeSideAccessoryButton.applySpotLightPolicy();
        }
    }

    public final void beforeShowSingleAppVolumeButton(ViewTreeObserver.OnComputeInternalInsetsListener onComputeInternalInsetsListener) {
        ViewTreeObserver viewTreeObserver;
        this.volumeInteractor.mMultiMediaRepository.updateRowsHMultiMediaRows();
        ViewGroup viewGroup = this.mDialogView;
        if (viewGroup != null) {
            this.platformBlurSession.releaseForSingleApp(viewGroup);
        }
        hideBySingleButton(true);
        ViewGroup viewGroup2 = this.mVerticalRowIconParent;
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(8);
        }
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView != null) {
            recyclerView.setVisibility(4);
        }
        if (onComputeInternalInsetsListener != null) {
            removeInsetsListener();
            ViewGroup viewGroup3 = this.mDialogView;
            if (viewGroup3 != null && (viewTreeObserver = viewGroup3.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnComputeInternalInsetsListener(onComputeInternalInsetsListener);
            }
            this.mOnComputeInternalInsetsListener = onComputeInternalInsetsListener;
        }
    }

    public final void beginGfxScene(Context context, int i) {
        int i2 = this.mGfxSceneId;
        if (i2 != 0) {
            OplusVolumeAnimationJankTrace.endScene(context, i2);
        }
        OplusVolumeAnimationJankTrace.beginScene(context, i);
        this.mGfxSceneId = i;
    }

    public final void cancelBtnAnim() {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        ViewPropertyAnimator viewPropertyAnimatorAnimate2;
        ViewPropertyAnimator viewPropertyAnimatorAnimate3;
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mMoreRowStreamLl;
        if (oplusVolumeSideAccessoryButton != null && (viewPropertyAnimatorAnimate3 = oplusVolumeSideAccessoryButton.animate()) != null) {
            viewPropertyAnimatorAnimate3.cancel();
        }
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mAppVolumeAdjustFl;
        if (oplusVolumeSideAccessoryButton2 != null && (viewPropertyAnimatorAnimate2 = oplusVolumeSideAccessoryButton2.animate()) != null) {
            viewPropertyAnimatorAnimate2.cancel();
        }
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton3 = this.mODICaptionsView;
        if (oplusVolumeSideAccessoryButton3 == null || (viewPropertyAnimatorAnimate = oplusVolumeSideAccessoryButton3.animate()) == null) {
            return;
        }
        viewPropertyAnimatorAnimate.cancel();
    }

    public final void cancelDismissAnimationsForFallback() {
        RecyclerView.ItemAnimator itemAnimator;
        ArrayList<OplusVolumeRow> sideShowList;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        cancelPanelAccessoryHideAnimations();
        this.panelExpandPrelude.cancelRunningAnimationOnly();
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null && (sideShowList = volumeListAdapter.getSideShowList()) != null) {
            int size = sideShowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = sideShowList.get(i);
                i++;
                View view = oplusVolumeRow.getView();
                if (view != null && (viewPropertyAnimatorAnimate = view.animate()) != null) {
                    viewPropertyAnimatorAnimate.cancel();
                }
            }
        }
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView == null || (itemAnimator = recyclerView.getItemAnimator()) == null) {
            return;
        }
        itemAnimator.endAnimations();
    }

    public final void cancelDismissFallback() {
        Job job = this.dismissFallbackJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.dismissFallbackJob = null;
    }

    public final void cancelPanelAccessoryHideAnimations() {
        ViewGroup viewGroup;
        Runnable runnable = this.panelHideEndRunnable;
        if (runnable != null && (viewGroup = this.mDialogView) != null) {
            viewGroup.removeCallbacks(runnable);
        }
        this.panelHideEndRunnable = null;
        OplusVolumePanelAccessoryAnimator oplusVolumePanelAccessoryAnimator = this.panelAccessoryAnimator;
        oplusVolumePanelAccessoryAnimator.accessoryFadeGeneration++;
        Iterator it = oplusVolumePanelAccessoryAnimator.dismissAccessoryViews().iterator();
        while (it.hasNext()) {
            ((View) it.next()).animate().cancel();
        }
    }

    public final void cancelPendingTerminalHide(boolean z) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        View view;
        this.terminalHideGeneration++;
        Runnable runnable = this.pendingTerminalHide;
        if (runnable != null && (view = this.pendingTerminalHideRoot) != null) {
            view.removeCallbacks(runnable);
        }
        this.pendingTerminalHide = null;
        this.pendingTerminalHideRoot = null;
        if (!z || (viewGroup = this.mDialogView) == null || viewGroup.getVisibility() != 4 || (viewGroup2 = this.mDialogView) == null) {
            return;
        }
        viewGroup2.setVisibility(0);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0036  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void checkSettingButtonIsVisible() {
        int i;
        ViewGroup viewGroup;
        VolumeListAdapter volumeListAdapter;
        AlphaOptimizedImageButton alphaOptimizedImageButton = this.mSettingsButton;
        if (alphaOptimizedImageButton != null) {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
            if (this.volumeInteractor.mDeviceProvisionedController.isCurrentUserSetup() && activityManager.getLockTaskModeState() == 0) {
                SuperPowerSaveRepositoryImpl.Companion.getClass();
                if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel() || (volumeListAdapter = this.volumeListAdapter) == null || !volumeListAdapter.isSystemPanel) {
                    i = 4;
                } else {
                    i = 0;
                }
            } else {
                i = 4;
            }
            if (i != alphaOptimizedImageButton.getVisibility()) {
                alphaOptimizedImageButton.setVisibility(i);
                TextView textView = this.mVolumeSettingText;
                if (textView != null) {
                    textView.setVisibility(i);
                }
            }
            if (i != 0 || (viewGroup = this.mDialogView) == null) {
                return;
            }
            this.blurHostHelper.assignSettingsButtonBackground(viewGroup, true);
        }
    }

    public final boolean convertRowLayout(OplusVolumeRow oplusVolumeRow, boolean z) {
        if (oplusVolumeRow == null) {
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "row is null");
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("convertRowLayout horizontal: ", oplusVolumeRow.getStream(), ", row.stream: ", "OplusVolumeDialogView-->", z));
        }
        if (z) {
            oplusVolumeRow.setVerticalLayout(false);
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && oplusVolumeRow.isMultiMediaVolume) {
                boolean z2 = ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1;
                TextView nameText = oplusVolumeRow.getNameText();
                if (nameText != null) {
                    nameText.setTextDirection(z2 ? 4 : 5);
                }
            }
        } else if (!oplusVolumeRow.isMultiMediaVolume) {
            oplusVolumeRow.setVerticalLayout(true);
        }
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            this.volumeInteractor.mSuperVolumeRepository.showSuperVolume(oplusVolumeRow.isSuperVolume, oplusVolumeRow);
        }
        return true;
    }

    public final void dismissH(int i) {
        OplusVolumeDialogView oplusVolumeDialogView;
        int i2;
        ArrayList<OplusVolumeRow> panelShowList;
        int size;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        cancelPendingTerminalHide(false);
        String caller = Debug.getCaller();
        boolean z = this.volumeInteractor.mExpanded;
        boolean zIsDismissing = isDismissing();
        boolean zIsSliderShowing = isSliderShowing();
        CustomDialog customDialog = this.volumeDialog;
        Boolean boolValueOf = customDialog != null ? Boolean.valueOf(customDialog.isShowing()) : null;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "mDialog.dismiss() reason: ", ",from: ", caller, ",mExpanded = ");
        KeyguardLogger$.ExternalSyntheticOutline0.m(",mDismissing = ", ",isSliderShowing = ", sbM, z, zIsDismissing);
        sbM.append(zIsSliderShowing);
        sbM.append(",volumeDialog.isShowing: ");
        sbM.append(boolValueOf);
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, sbM.toString(), null, 8);
        CustomDialog customDialog2 = this.volumeDialog;
        int i3 = 1;
        if (customDialog2 != null) {
            COUISnackBar cOUISnackBar = this.mSnackBar;
            if (cOUISnackBar != null) {
                cOUISnackBar.dismiss(!customDialog2.isShowing());
            }
            if (!customDialog2.isShowing()) {
                cancelDismissFallback();
                return;
            } else if (isDismissing() && i != 4) {
                return;
            }
        }
        ViewGroup viewGroup = this.mDialogView;
        if (viewGroup != null && (viewPropertyAnimatorAnimate = viewGroup.animate()) != null) {
            viewPropertyAnimatorAnimate.cancel();
        }
        boolean z2 = this.volumeInteractor.mHasSeenODICaptionsTooltip;
        if (i == 4) {
            cancelDismissAnimationsForFallback();
            doAfterHide(i, this.volumeInteractor.mExpanded);
            return;
        }
        if (!isSliderShowing()) {
            cancelDismissFallback();
            resetSliderState();
            this.platformBlurSession.clearResources(false);
            CustomDialog customDialog3 = this.volumeDialog;
            if (customDialog3 != null) {
                customDialog3.dismiss();
                return;
            }
            return;
        }
        setSliderState(SliderState.DISMISSING);
        CustomDialog customDialog4 = this.volumeDialog;
        if (customDialog4 != null && customDialog4.isShowing() && isSliderShowing()) {
            cancelDismissFallback();
            VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
            if (volumeListAdapter != null && (panelShowList = volumeListAdapter.getPanelShowList()) != null && (size = panelShowList.size()) >= 1) {
                i3 = size;
            }
            oplusVolumeDialogView = this;
            i2 = i;
            oplusVolumeDialogView.dismissFallbackJob = BuildersKt.launch$default(this.viewScope, (CoroutineContext) null, (CoroutineStart) null, new OplusVolumeDialogView$scheduleDismissFallback$1(OplusVolumePanelTiming.resolveDismissFallbackTimeoutMs(this.context, i3, Math.max(AnimationScaleSettingsObserver.Companion.getInstance().getAnimationScale(), 1.0f)), oplusVolumeDialogView, i2, null), 3);
        } else {
            oplusVolumeDialogView = this;
            i2 = i;
            oplusVolumeDialogView.cancelDismissFallback();
        }
        if (oplusVolumeDialogView.mVolumeRecyclerview != null) {
            oplusVolumeDialogView.showOrHideAnimation(oplusVolumeDialogView.getSideAnimTranslationX(), false, i2, null);
        } else {
            oplusVolumeDialogView.doAfterHide(i2, oplusVolumeDialogView.volumeInteractor.mExpanded);
        }
    }

    public final void doAfterHide(int i, boolean z) {
        CustomDialog customDialog;
        ViewGroup viewGroup;
        cancelDismissFallback();
        if (this.sliderState == SliderState.SHOWING) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "doAfterHide skip: already SHOWING (side reshow race)", null, 8);
            return;
        }
        this.mIsDialogAnimRunning = false;
        this.mIsRowParentAnimRunning = false;
        this.volumeInteractor.mIsMultiRowShowing = false;
        resetSeekBar();
        updateRowsH$default(this, this.volumeInteractor.getActiveRow(), false, 6);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->dismissH: onAnimationEnd");
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        int i2 = 1;
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
            if (oplusVolumeDialogImpl.mDialogShowingForMultiMedia) {
                MultiMediaRepository multiMediaRepository = oplusVolumeDialogImpl.mMultiMediaRepository;
                multiMediaRepository.getClass();
                MultiMediaRepository.AnonymousClass1 anonymousClass1 = new MultiMediaRepository.AnonymousClass1(i2);
                anonymousClass1.this$0 = multiMediaRepository;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(anonymousClass1);
            }
        }
        if (z) {
            this.panelTransitionSession.reset();
            ViewGroup viewGroup2 = this.mDialogView;
            if (viewGroup2 != null) {
                viewGroup2.setAlpha(1.0f);
            }
            RecyclerView recyclerView = this.mVolumeRecyclerview;
            if (recyclerView != null) {
                recyclerView.setAlpha(1.0f);
            }
            for (View view : this.panelAccessoryAnimator.dismissAccessoryViews()) {
                view.setAlpha(1.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
            }
            setViewsAlpha(1.0f);
            ImageButton imageButton = this.mMoreStreamsButton;
            if (imageButton != null) {
                imageButton.setImageAlpha(255);
            }
            Window window = this.mWindow;
            if (window != null) {
                window.setFlags(8, 131080);
            }
        } else {
            for (OplusVolumeRow oplusVolumeRow : this.volumeInteractor.mRows) {
                if (oplusVolumeRow.getStream() == 3 || oplusVolumeRow.getStream() == 2) {
                    if (oplusVolumeRow.getIcon().isAnimating()) {
                        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "handleAbnormalIconAnimEnd:" + oplusVolumeRow.getStream() + ",isAnim " + oplusVolumeRow.getIcon().isAnimating() + ",max_frame: " + oplusVolumeRow.getIcon().getMaxFrame());
                        if (oplusVolumeRow.getIcon().getSpeed() > 0.0f) {
                            oplusVolumeRow.getIcon().setFrame((int) oplusVolumeRow.getIcon().getMaxFrame());
                        } else {
                            oplusVolumeRow.getIcon().setFrame((int) oplusVolumeRow.getIcon().getMinFrame());
                        }
                    }
                }
            }
            this.sideShowSession.reset();
        }
        resetSliderState();
        if (i == 4 || (customDialog = this.volumeDialog) == null || !customDialog.isShowing() || (viewGroup = this.mDialogView) == null || !viewGroup.isAttachedToWindow()) {
            releaseBlurResourcesAndDismiss(i);
            return;
        }
        cancelPendingTerminalHide(false);
        ViewGroup viewGroup3 = this.mDialogView;
        if (viewGroup3 == null) {
            releaseBlurResourcesAndDismiss(i);
            return;
        }
        CustomDialog customDialog2 = this.volumeDialog;
        if (customDialog2 == null) {
            releaseBlurResourcesAndDismiss(i);
            return;
        }
        long j = this.terminalHideGeneration + 1;
        this.terminalHideGeneration = j;
        viewGroup3.setVisibility(4);
        this.platformBlurSession.pauseCaptureForTerminalHide(viewGroup3);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        scheduleTerminalHide.1 r4 = new scheduleTerminalHide.1();
        r4.this$0 = this;
        r4.$terminalHide = objectRef;
        r4.$generation = j;
        r4.$dialog = customDialog2;
        r4.$reason = i;
        VarHandle.storeStoreFence();
        objectRef.element = r4;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        scheduleTerminalHide.2 r1 = new scheduleTerminalHide.2();
        r1.$generation = j;
        r1.this$0 = this;
        r1.$waitForInvisibleFrame = objectRef2;
        r1.$terminalHide = objectRef;
        r1.$root = viewGroup3;
        VarHandle.storeStoreFence();
        objectRef2.element = r1;
        this.pendingTerminalHide = r1;
        this.pendingTerminalHideRoot = viewGroup3;
        viewGroup3.postOnAnimation(r1);
    }

    public final void doShowOrHideAnimate(View view, long j, long j2, float f, boolean z, int i, Runnable runnable, Runnable runnable2, boolean z2) {
        boolean z3 = this.volumeInteractor.mExpanded;
        COUISpringInterpolator cOUISpringInterpolator = z ? VolumeAnimUtil.VOLUME_SHOW_INTERPOLATOR : VolumeAnimUtil.VOLUME_HIDE_INTERPOLATOR;
        float f2 = z ? 0.0f : f;
        float f3 = z ? 1.0f : 0.5f;
        float f4 = z ? 1.0f : 0.0f;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$target = view;
        anonymousClass1.$translationX = f;
        anonymousClass1.$toShow = z;
        anonymousClass1.$needResetY = z2;
        anonymousClass1.$changeAlpha = true;
        anonymousClass1.this$0 = this;
        anonymousClass1.$reason = i;
        anonymousClass1.$expanded = z3;
        anonymousClass1.$onEnd = runnable2;
        VarHandle.storeStoreFence();
        VolumeAnimUtil.startViewPropertyAnimation(view, j2, j, cOUISpringInterpolator, f2, 0.0f, f3, f4, runnable, anonymousClass1, null, 13);
    }

    public final void endGfxSceneIfActive(Context context, int i) {
        if (this.mGfxSceneId == i) {
            OplusVolumeAnimationJankTrace.endScene(context, i);
            this.mGfxSceneId = 0;
        }
    }

    public final void expandPanel() {
        int color;
        endGfxSceneIfActive(this.newContext, 301);
        beginGfxScene(this.newContext, 302);
        ViewGroup viewGroup = this.mDialogView;
        if (viewGroup != null) {
            this.blurHostHelper.unfreezePlatformBlurCaptureRegion();
            this.platformBlurSession.prepareEngine();
            this.blurHostHelper.initPanelOnAttach(viewGroup);
            this.platformBlurSession.startAttachedObservers(viewGroup);
        }
        this.sideShowSession.reset();
        PanelTransitionSession.markExpandStarted$default(this.panelTransitionSession);
        this.panelExpandPrelude.start();
        refreshVolumeSpotLightType(false);
        resetSeekBarAndResetAnimateState(false);
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView != null) {
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (childAt != null) {
                    childAt.animate().cancel();
                    childAt.setAlpha(1.0f);
                    childAt.setTranslationX(0.0f);
                    childAt.setTranslationY(0.0f);
                }
            }
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null) {
            VolumeListAdapter.Companion companion = VolumeListAdapter.Companion;
            volumeListAdapter.switchToDataSourcePanel(false);
        }
        hideBySingleButton(false);
        removeInsetsListener();
        resetSeekBarAndResetAnimateState(true);
        COUISnackBar cOUISnackBar = this.mSnackBar;
        if (cOUISnackBar != null) {
            cOUISnackBar.dismiss();
        }
        checkSettingButtonIsVisible();
        boolean zIsGaussianBlurDisable = VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.context);
        if (zIsGaussianBlurDisable && this.configChanged) {
            this.configChanged = false;
            boolean zIsNightMode = OplusDarkModeUtil.isNightMode(this.context);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("nightModeChangeOnGaussianBlurDisable, currentNightMode: ", ", mIsDefaultVolumeTheme: ", "OplusVolumeDialogView-->", zIsNightMode, this.mIsDefaultVolumeTheme));
            }
            if (zIsNightMode) {
                ImageView imageView = this.mVolumePanelTitleIcon;
                if (imageView != null) {
                    imageView.setImageTintList(ColorStateList.valueOf(-1));
                }
                applySettingsButtonDrawable(-1);
                TextView textView = this.mVolumeSettingText;
                if (textView != null) {
                    textView.setTextColor(-1);
                }
                TextView textView2 = this.mVolumePanelTipsText;
                if (textView2 != null) {
                    textView2.setTextColor(-1);
                }
                TextView textView3 = this.mVolumePanelTitleText;
                if (textView3 != null) {
                    textView3.setTextColor(-1);
                }
                VolumeListAdapter volumeListAdapter2 = this.volumeListAdapter;
                if (volumeListAdapter2 != null) {
                    volumeListAdapter2.textColor = -1;
                }
            } else {
                if (this.mIsDefaultVolumeTheme) {
                    ImageView imageView2 = this.mVolumePanelTitleIcon;
                    color = -16777216;
                    if (imageView2 != null) {
                        imageView2.setImageTintList(ColorStateList.valueOf(-16777216));
                    }
                    applySettingsButtonDrawable(-16777216);
                } else {
                    color = this.context.getColor(2131103138);
                }
                TextView textView4 = this.mVolumeSettingText;
                if (textView4 != null) {
                    textView4.setTextColor(color);
                }
                TextView textView5 = this.mVolumePanelTipsText;
                if (textView5 != null) {
                    textView5.setTextColor(color);
                }
                TextView textView6 = this.mVolumePanelTitleText;
                if (textView6 != null) {
                    textView6.setTextColor(color);
                }
                VolumeListAdapter volumeListAdapter3 = this.volumeListAdapter;
                if (volumeListAdapter3 != null) {
                    volumeListAdapter3.textColor = color;
                }
            }
            refreshVolumeSpotLightType(true);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->".concat(IconProvider$.ExternalSyntheticOutline0.m("nightModeChangeOnGaussianBlurDisable , isGaussianBlurDisable: ", ", configChanged: ", " return", zIsGaussianBlurDisable, this.configChanged)));
        }
        this.blurHostHelper.mountPanelBackground();
        this.blurHostHelper.ensurePanelRowsPlatformBlur(true);
        this.panelAccessoryAnimator.performExpandAnimations();
        updateRowsH$default(this, this.volumeInteractor.getActiveRow(), false, 6);
        watchPanelExpandCompletion();
    }

    public final void flushRowAndIconParent() {
        ArrayList<OplusVolumeRow> sideShowList;
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null && (sideShowList = volumeListAdapter.getSideShowList()) != null) {
            int size = sideShowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = sideShowList.get(i);
                i++;
                OplusVolumeRow oplusVolumeRow2 = oplusVolumeRow;
                View view = oplusVolumeRow2.getView();
                if (view != null) {
                    view.animate().cancel();
                    OplusVolumeSeekBar slider = oplusVolumeRow2.getSlider();
                    if (slider != null) {
                        slider.resetSeekBar();
                    }
                    this.blurHostHelper.applyVolumeBarBackgroundFromCache(oplusVolumeRow2, view);
                    VolumeAnimUtil.resetShowOrHideAnimateView(view, 0.0f, true, 1, Boolean.TRUE);
                }
            }
        }
        ViewGroup viewGroup = this.mVerticalRowIconParent;
        if (viewGroup != null) {
            viewGroup.animate().cancel();
            cancelBtnAnim();
            VolumeAnimUtil.resetShowOrHideAnimateView(viewGroup, 0.0f, true, 1, Boolean.TRUE);
        }
        ViewGroup viewGroup2 = this.mDialogView;
        if (viewGroup2 != null) {
            flushRowAndIconParent.3 r2 = new flushRowAndIconParent.3(0);
            r2.this$0 = this;
            VarHandle.storeStoreFence();
            viewGroup2.post(r2);
        }
    }

    public final boolean flushSideStructuralUpdateIfReady() {
        VolumeListAdapter volumeListAdapter;
        if (this.volumeInteractor.mExpanded || shouldBlockSideStructuralForAnim()) {
            return false;
        }
        VolumeListAdapter volumeListAdapter2 = this.volumeListAdapter;
        if (volumeListAdapter2 != null) {
            volumeListAdapter2.filterSideList();
        }
        VolumeListAdapter volumeListAdapter3 = this.volumeListAdapter;
        if (volumeListAdapter3 != null && volumeListAdapter3.needsSideStructuralSubmit() && (volumeListAdapter = this.volumeListAdapter) != null) {
            OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(3);
            oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            volumeListAdapter.flushPendingSideList(oplusVolumeDialogView$$ExternalSyntheticLambda0);
        }
        return true;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ConfigurableTexts getMConfigurableTexts() {
        return this.mConfigurableTexts;
    }

    public final boolean getMHovering() {
        return this.mHovering;
    }

    public final boolean getMIsDefaultVolumeTheme() {
        return this.mIsDefaultVolumeTheme;
    }

    public final ViewGroup getMVerticalRowIconParent() {
        return this.mVerticalRowIconParent;
    }

    public final Context getNewContext() {
        return this.newContext;
    }

    public final SliderState getSliderState() {
        return this.sliderState;
    }

    public final ArrayList<View> getVisRows() {
        int iFindFirstVisibleItemPosition;
        int iFindLastVisibleItemPosition;
        ArrayList<View> arrayList = new ArrayList<>();
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        RecyclerView.LayoutManager layoutManager = recyclerView != null ? recyclerView.getLayoutManager() : null;
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager != null && (iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()) <= (iFindLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition())) {
            while (true) {
                View viewFindViewByPosition = linearLayoutManager.findViewByPosition(iFindFirstVisibleItemPosition);
                if (viewFindViewByPosition != null) {
                    arrayList.add(((FrameLayout) viewFindViewByPosition.findViewById(2131365996)).getChildAt(0));
                }
                if (iFindFirstVisibleItemPosition == iFindLastVisibleItemPosition) {
                    break;
                }
                iFindFirstVisibleItemPosition++;
            }
        }
        return arrayList;
    }

    public final CustomDialog getVolumeDialog() {
        return this.volumeDialog;
    }

    public final OplusVolumeDialogImpl getVolumeInteractor() {
        return this.volumeInteractor;
    }

    public final VolumeListAdapter getVolumeListAdapter() {
        return this.volumeListAdapter;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void hideBySingleButton(boolean z) {
        int i = z ? 8 : 0;
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView != null) {
            recyclerView.setVisibility(i);
        }
        AlphaOptimizedImageButton alphaOptimizedImageButton = this.mSettingsButton;
        if (alphaOptimizedImageButton != null) {
            alphaOptimizedImageButton.setVisibility(i);
        }
        TextView textView = this.mVolumeSettingText;
        if (textView != null) {
            textView.setVisibility(i);
        }
        TextView textView2 = this.mVolumePanelTipsText;
        if (textView2 != null) {
            textView2.setVisibility(i);
        }
        if (z) {
            ImageView imageView = this.mVolumePanelTitleIcon;
            if (imageView != null) {
                imageView.setVisibility(i);
            }
            TextView textView3 = this.mVolumePanelTitleText;
            if (textView3 != null) {
                textView3.setVisibility(i);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00ab  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void initDialog() {
        Resources resources;
        float dimension;
        float f;
        View decorView;
        View decorView2;
        Resources resources2;
        Resources resources3;
        int i = 0;
        cancelPendingTerminalHide(false);
        CustomDialog customDialog = this.volumeDialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
        this.volumeDialog = null;
        int initialDisplayDensity = KeyguardUtils.Companion.getInitialDisplayDensity();
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        Configuration configuration = new Configuration(this.context.getResources().getConfiguration());
        configuration.densityDpi = (int) Math.min(displayMetrics.densityDpi, initialDisplayDensity);
        this.newContext = this.context.createConfigurationContext(configuration);
        sScale = configuration.densityDpi / 160.0f;
        screenHeight = displayMetrics.heightPixels;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->".concat("initDialog: set fixed density:" + sScale + ", screenHeight:" + screenHeight));
        }
        int i2 = 1;
        this.configChanged = true;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() || VolumeLayoutUtil.isLandscape(this.context)) {
            Context context = this.newContext;
            if (context == null || (resources = context.getResources()) == null) {
                dimension = 0.0f;
            } else {
                dimension = resources.getDimension(2131173888);
            }
        } else {
            Context context2 = this.newContext;
            if (context2 == null || (resources3 = context2.getResources()) == null) {
                dimension = 0.0f;
            } else {
                dimension = resources3.getDimension(2131173887);
            }
        }
        this.marginLeft = dimension;
        Context context3 = this.newContext;
        if (context3 != null && (resources2 = context3.getResources()) != null) {
            getVolumeDimensions().updateDimensions(resources2);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("updateParamOnConfigChanged() marginLeft = ", this.marginLeft, "OplusVolumeDialogView-->", "SystemUi--Volume");
        }
        this.volumeDialog = new CustomDialog(getUpdateNewContext());
        this.mConfigurableTexts = new ConfigurableTexts(this.newContext);
        this.mHovering = false;
        resetSliderState();
        this.mIsRowParentAnimRunning = false;
        this.volumeInteractor.mIsMultiRowShowing = false;
        this.sideShowSession.reset();
        this.volumeInteractor.mExpanded = false;
        CustomDialog customDialog2 = this.volumeDialog;
        Window window = customDialog2 != null ? customDialog2.getWindow() : null;
        this.mWindow = window;
        if (window != null) {
            window.requestFeature(1);
        }
        Window window2 = this.mWindow;
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
        }
        Window window3 = this.mWindow;
        if (window3 != null) {
            window3.clearFlags(196610);
        }
        Window window4 = this.mWindow;
        if (window4 != null) {
            window4.addFlags(17563912);
        }
        Window window5 = this.mWindow;
        if (window5 != null) {
            window5.setType(2020);
        }
        Window window6 = this.mWindow;
        if (window6 != null) {
            window6.setWindowAnimations(0);
        }
        Window window7 = this.mWindow;
        WindowManager.LayoutParams attributes = window7 != null ? window7.getAttributes() : null;
        if (attributes != null) {
            attributes.format = -3;
        }
        if (attributes != null) {
            attributes.setTitle("OplusVolumeDialogImpl");
        }
        if (attributes != null) {
            attributes.windowAnimations = -1;
        }
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("initDialog isOplusVolumeKeyInRight=", FeatureOption.isOplusVolumeKeyInRight()), null, 8);
        if (FeatureOption.isOplusVolumeKeyInRight()) {
            if (attributes != null) {
                attributes.gravity = 21;
            }
            Window window8 = this.mWindow;
            if (window8 != null) {
                window8.setAttributes(attributes);
            }
            Window window9 = this.mWindow;
            if (window9 != null && (decorView2 = window9.getDecorView()) != null) {
                decorView2.setSystemUiVisibility(1792);
            }
            boolean zIsTablet = FeatureOption.isTablet();
            CustomDialog customDialog3 = this.volumeDialog;
            if (zIsTablet) {
                if (customDialog3 != null) {
                    customDialog3.setContentView(2131559423);
                }
            } else if (customDialog3 != null) {
                customDialog3.setContentView(2131559421);
            }
        } else {
            if (attributes != null) {
                attributes.gravity = 51;
            }
            if (VolumeLayoutUtil.isLandscape(this.context)) {
                if (attributes != null) {
                    attributes.gravity = 19;
                }
                f = 0.0f;
            } else {
                f = 175.0f;
            }
            if (attributes != null) {
                attributes.y = VolumeLayoutUtil.dp2Px(f, this.newContext);
            }
            Window window10 = this.mWindow;
            if (window10 != null) {
                window10.setAttributes(attributes);
            }
            Window window11 = this.mWindow;
            if (window11 != null && (decorView = window11.getDecorView()) != null) {
                decorView.setSystemUiVisibility(1792);
            }
            boolean zIsTablet2 = FeatureOption.isTablet();
            CustomDialog customDialog4 = this.volumeDialog;
            if (zIsTablet2) {
                if (customDialog4 != null) {
                    customDialog4.setContentView(2131559422);
                }
            } else if (customDialog4 != null) {
                customDialog4.setContentView(2131559419);
            }
        }
        CustomDialog customDialog5 = this.volumeDialog;
        ViewGroup viewGroup = customDialog5 != null ? (ViewGroup) customDialog5.findViewById(2131365997) : null;
        this.mDialogView = viewGroup;
        this.mVerticalRowIconParent = viewGroup != null ? (ViewGroup) viewGroup.findViewById(2131365958) : null;
        Window window12 = this.mWindow;
        if (window12 != null) {
            window12.setLayout(-1, -1);
        }
        boolean z = !this.context.getResources().getBoolean(2131034340);
        this.mIsDefaultVolumeTheme = z;
        OplusVolumeSeekBar.setIsDefaultVolumeTheme(z);
        CustomDialog customDialog6 = this.volumeDialog;
        this.mMoreRowStreamLl = customDialog6 != null ? (OplusVolumeSideAccessoryButton) customDialog6.findViewById(2131364006) : null;
        CustomDialog customDialog7 = this.volumeDialog;
        this.mAppVolumeAdjustFl = customDialog7 != null ? (OplusVolumeSideAccessoryButton) customDialog7.findViewById(2131362112) : null;
        this.singleAppVolumeButtonContainer = null;
        ViewGroup viewGroup2 = this.mDialogView;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(0.0f);
        }
        CustomDialog customDialog8 = this.volumeDialog;
        if (customDialog8 != null) {
            customDialog8.setCanceledOnTouchOutside(true);
        }
        CustomDialog customDialog9 = this.volumeDialog;
        if (customDialog9 != null) {
            OplusVolumeDialogView$setDialogListener$1 oplusVolumeDialogView$setDialogListener$1 = new OplusVolumeDialogView$setDialogListener$1();
            oplusVolumeDialogView$setDialogListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            customDialog9.setOnShowListener(oplusVolumeDialogView$setDialogListener$1);
        }
        CustomDialog customDialog10 = this.volumeDialog;
        if (customDialog10 != null) {
            OplusVolumeDialogView$setDialogListener$2 oplusVolumeDialogView$setDialogListener$2 = new OplusVolumeDialogView$setDialogListener$2();
            oplusVolumeDialogView$setDialogListener$2.this$0 = this;
            VarHandle.storeStoreFence();
            customDialog10.setOnDismissListener(oplusVolumeDialogView$setDialogListener$2);
        }
        ViewGroup viewGroup3 = this.mDialogView;
        if (viewGroup3 != null) {
            ViewOnHoverListenerC03111 viewOnHoverListenerC03111 = new ViewOnHoverListenerC03111();
            viewOnHoverListenerC03111.this$0 = this;
            VarHandle.storeStoreFence();
            viewGroup3.setOnHoverListener(viewOnHoverListenerC03111);
        }
        ViewGroup viewGroup4 = this.mDialogView;
        if (viewGroup4 != null) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            viewGroup4.addOnAttachStateChangeListener(anonymousClass2);
        }
        CustomDialog customDialog11 = this.volumeDialog;
        this.mSettingsButton = customDialog11 != null ? (OplusVolumeSettingsButton) customDialog11.findViewById(2131363378) : null;
        applySettingsButtonDrawable(-1);
        CustomDialog customDialog12 = this.volumeDialog;
        this.mVolumeSettingText = customDialog12 != null ? (TextView) customDialog12.findViewById(2131363379) : null;
        CustomDialog customDialog13 = this.volumeDialog;
        TextView textView = customDialog13 != null ? (TextView) customDialog13.findViewById(2131365824) : null;
        this.mVolumePanelTipsText = textView;
        if (textView != null) {
            this.volumeInteractor.mOplusVolumeTipsControler = new OplusVolumeTipsControler(textView);
        }
        CustomDialog customDialog14 = this.volumeDialog;
        this.mVolumePanelTitleIcon = customDialog14 != null ? (ImageView) customDialog14.findViewById(2131366031) : null;
        CustomDialog customDialog15 = this.volumeDialog;
        this.mVolumePanelTitleText = customDialog15 != null ? (TextView) customDialog15.findViewById(2131366032) : null;
        this.panelAccessoryAnimator.setSettingsButton(this.mSettingsButton);
        this.panelAccessoryAnimator.setVolumeSettingText(this.mVolumeSettingText);
        this.panelAccessoryAnimator.setPanelTipsText(this.mVolumePanelTipsText);
        this.panelAccessoryAnimator.setPanelTitleIcon(this.mVolumePanelTitleIcon);
        this.panelAccessoryAnimator.setPanelTitleText(this.mVolumePanelTitleText);
        this.panelAccessoryAnimator.prepareTitleForCollapsedState();
        CustomDialog customDialog16 = this.volumeDialog;
        RecyclerView recyclerViewFindViewById = customDialog16 != null ? customDialog16.findViewById(2131366033) : null;
        this.mVolumeRecyclerview = recyclerViewFindViewById;
        int i3 = 2;
        if (recyclerViewFindViewById != null) {
            recyclerViewFindViewById.setImportantForAccessibility(2);
            OplusVolumeDialogView$initDialog$4$layoutManager$1 oplusVolumeDialogView$initDialog$4$layoutManager$1 = new OplusVolumeDialogView$initDialog$4$layoutManager$1(this.context);
            oplusVolumeDialogView$initDialog$4$layoutManager$1.setOrientation(0);
            if (FeatureOption.isOplusVolumeKeyInRight()) {
                oplusVolumeDialogView$initDialog$4$layoutManager$1.setStackFromEnd(true);
                oplusVolumeDialogView$initDialog$4$layoutManager$1.setReverseLayout(false);
            }
            recyclerViewFindViewById.setLayoutManager(oplusVolumeDialogView$initDialog$4$layoutManager$1);
            recyclerViewFindViewById.setLayoutDirection(0);
            VolumeListAdapter volumeListAdapter = new VolumeListAdapter(recyclerViewFindViewById);
            this.volumeListAdapter = volumeListAdapter;
            recyclerViewFindViewById.setAdapter(volumeListAdapter);
            VolumeListAdapter volumeListAdapter2 = this.volumeListAdapter;
            if (volumeListAdapter2 != null) {
                VolumeItemAnimator volumeItemAnimator = new VolumeItemAnimator(volumeListAdapter2);
                volumeItemAnimator.setPanelTransitionSession(this.panelTransitionSession);
                volumeListAdapter2.attachVolumeItemAnimator(volumeItemAnimator);
            }
            OplusVolumeDialogView$initDialog$4$2 oplusVolumeDialogView$initDialog$4$2 = new OplusVolumeDialogView$initDialog$4$2();
            oplusVolumeDialogView$initDialog$4$2.$it = recyclerViewFindViewById;
            oplusVolumeDialogView$initDialog$4$2.this$0 = this;
            VarHandle.storeStoreFence();
            recyclerViewFindViewById.setOnTouchListener(oplusVolumeDialogView$initDialog$4$2);
        }
        CustomDialog customDialog17 = this.volumeDialog;
        this.mMoreStreamsButton = customDialog17 != null ? (ImageButton) customDialog17.findViewById(2131364005) : null;
        CustomDialog customDialog18 = this.volumeDialog;
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = customDialog18 != null ? (OplusVolumeSideAccessoryButton) customDialog18.findViewById(2131364219) : null;
        this.mODICaptionsView = oplusVolumeSideAccessoryButton;
        adaptThemeDrawable(this.mMoreRowStreamLl, this.mAppVolumeAdjustFl, oplusVolumeSideAccessoryButton, this.mIsDefaultVolumeTheme);
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mODICaptionsView;
        this.mODICaptionsIcon = oplusVolumeSideAccessoryButton2 != null ? (OplusCaptionsToggleImageButton) oplusVolumeSideAccessoryButton2.findViewById(2131364221) : null;
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton3 = this.mODICaptionsView;
        this.mODICaptionsAnimation = oplusVolumeSideAccessoryButton3 != null ? (EffectiveAnimationView) oplusVolumeSideAccessoryButton3.findViewById(2131364220) : null;
        CustomDialog customDialog19 = this.volumeDialog;
        ViewStub viewStub = customDialog19 != null ? (ViewStub) customDialog19.findViewById(2131364222) : null;
        this.mODICaptionsTooltipViewStub = viewStub;
        if (this.volumeInteractor.mHasSeenODICaptionsTooltip && viewStub != null) {
            ViewGroup viewGroup5 = this.mDialogView;
            if (viewGroup5 != null) {
                viewGroup5.removeView(viewStub);
            }
            this.mODICaptionsTooltipViewStub = null;
        }
        if (this.mODICaptionsView != null && FeatureOption.isExpRegion()) {
            updateODICaptionsH(this.volumeInteractor.mIsCaptionComponentEnabled);
        }
        if (this.volumeInteractor.mRows.isEmpty()) {
            if (!AudioSystem.isSingleVolume(this.context)) {
                this.volumeInteractor.addRow(10, 2131237391, 2131237391, true, false);
            }
            this.volumeInteractor.addRow(3, 2131237420, 2131237422, true, true);
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isOplusAssistantVolumeSupport: ", FeatureOption.isOplusAssistantVolumeSupport()), null, 8);
            if (FeatureOption.isOplusAssistantVolumeSupport()) {
                this.volumeInteractor.addRow(11, 2131237397, 2131237398, true, true);
            } else if (FeatureOption.isExpRegion()) {
                this.volumeInteractor.addRow(11, 2131237406, 2131237407, true, true);
            }
            if (!AudioSystem.isSingleVolume(this.context)) {
                this.volumeInteractor.addRow(2, 2131237436, 2131237438, true, false);
                this.volumeInteractor.addRow(5, 2131237427, 2131237429, true, false);
                this.volumeInteractor.addRow(4, 2131237393, 2131237394, true, false);
                this.volumeInteractor.addRow(0, 2131237453, 2131237453, false, false);
                this.volumeInteractor.addRow(1, 2131237436, 2131237438, false, false);
            }
        } else {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
            for (OplusVolumeRow oplusVolumeRow : oplusVolumeDialogImpl.mRows) {
                int stream = oplusVolumeRow.getStream();
                int iconRes = oplusVolumeRow.getIconRes();
                int iconMuteRes = oplusVolumeRow.getIconMuteRes();
                boolean important = oplusVolumeRow.getImportant();
                boolean defaultStream = oplusVolumeRow.getDefaultStream();
                oplusVolumeRow.stream = stream;
                oplusVolumeRow.iconRes = iconRes;
                oplusVolumeRow.iconMuteRes = iconMuteRes;
                oplusVolumeRow.important = important;
                oplusVolumeRow.defaultStream = defaultStream;
                oplusVolumeRow.initRow();
                LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "addExistingRows row.stream = " + oplusVolumeRow.getStream());
                oplusVolumeDialogImpl.mOplusVolumeDialogView.getVolumeListAdapter().addItem(oplusVolumeRow, null);
                oplusVolumeDialogImpl.mIconAbility.updateVolumeRowH(oplusVolumeRow, false, oplusVolumeDialogImpl.mState);
            }
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            this.volumeInteractor.mMultiMediaRepository.initMultiMediaRows();
        }
        OplusVolumeDialogImpl oplusVolumeDialogImpl2 = this.volumeInteractor;
        AlphaOptimizedImageButton alphaOptimizedImageButton = this.mSettingsButton;
        oplusVolumeDialogImpl2.mOplusVolumeDialogView.checkSettingButtonIsVisible();
        if (oplusVolumeDialogImpl2.mSoundSettingsActivity == null) {
            oplusVolumeDialogImpl2.mSoundSettingsActivity = oplusVolumeDialogImpl2.mContext.getString(2131956497);
        }
        if (alphaOptimizedImageButton != null) {
            OplusVolumeDialogImpl$$ExternalSyntheticLambda7 oplusVolumeDialogImpl$$ExternalSyntheticLambda7 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda7();
            oplusVolumeDialogImpl$$ExternalSyntheticLambda7.f$0 = oplusVolumeDialogImpl2;
            VarHandle.storeStoreFence();
            alphaOptimizedImageButton.setOnClickListener(oplusVolumeDialogImpl$$ExternalSyntheticLambda7);
        }
        oplusVolumeDialogImpl2.mOplusVolumeDialogView.getVolumeDialog().setCanceledOnTouchOutside(true);
        OplusVolumeDialogView oplusVolumeDialogView = oplusVolumeDialogImpl2.mOplusVolumeDialogView;
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton4 = oplusVolumeDialogView.mMoreRowStreamLl;
        if (oplusVolumeSideAccessoryButton4 != null) {
            OplusVolumeDialogView$initODICaptionsH$3$1 oplusVolumeDialogView$initODICaptionsH$3$1 = new OplusVolumeDialogView$initODICaptionsH$3$1(i3);
            oplusVolumeDialogView$initODICaptionsH$3$1.this$0 = oplusVolumeDialogView;
            VarHandle.storeStoreFence();
            oplusVolumeSideAccessoryButton4.setOnClickListener(oplusVolumeDialogView$initODICaptionsH$3$1);
        }
        OplusVolumeDialogView oplusVolumeDialogView2 = oplusVolumeDialogImpl2.mOplusVolumeDialogView;
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "appVolumeAdjustClick mAppVolumeAdjustFl: " + oplusVolumeDialogView2.mAppVolumeAdjustFl, null, 8);
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton5 = oplusVolumeDialogView2.mAppVolumeAdjustFl;
        if (oplusVolumeSideAccessoryButton5 != null) {
            OplusVolumeDialogView$initODICaptionsH$3$1 oplusVolumeDialogView$initODICaptionsH$3$2 = new OplusVolumeDialogView$initODICaptionsH$3$1(i2);
            oplusVolumeDialogView$initODICaptionsH$3$2.this$0 = oplusVolumeDialogView2;
            VarHandle.storeStoreFence();
            oplusVolumeSideAccessoryButton5.setOnClickListener(oplusVolumeDialogView$initODICaptionsH$3$2);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            AlphaOptimizedImageButton alphaOptimizedImageButton2 = this.mODICaptionsIcon;
            if (alphaOptimizedImageButton2 != null) {
                alphaOptimizedImageButton2.setClickable(false);
                OplusCaptionsToggleImageButton oplusCaptionsToggleImageButton = this.mODICaptionsIcon;
                if (oplusCaptionsToggleImageButton != null) {
                    blurHostHelper.1 r4 = new blurHostHelper.1(1);
                    r4.this$0 = this;
                    VarHandle.storeStoreFence();
                    oplusCaptionsToggleImageButton.setOnConfirmedTapListener(r4);
                }
            }
            EffectiveAnimationView effectiveAnimationView = this.mODICaptionsAnimation;
            if (effectiveAnimationView != null) {
                effectiveAnimationView.setClickable(false);
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton6 = this.mODICaptionsView;
            if (oplusVolumeSideAccessoryButton6 != null) {
                OplusVolumeDialogView$initODICaptionsH$3$1 oplusVolumeDialogView$initODICaptionsH$3$3 = new OplusVolumeDialogView$initODICaptionsH$3$1(i);
                oplusVolumeDialogView$initODICaptionsH$3$3.this$0 = this;
                VarHandle.storeStoreFence();
                oplusVolumeSideAccessoryButton6.setOnClickListener(oplusVolumeDialogView$initODICaptionsH$3$3);
            }
            this.volumeInteractor.mController.getCaptionsComponentState(false);
        }
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl3 = this.volumeInteractor;
            SuperVolumeRepository superVolumeRepository = oplusVolumeDialogImpl3.mSuperVolumeRepository;
            superVolumeRepository.initSuperVolume(superVolumeRepository.getSuperCallStreamVolumeDevice(0, oplusVolumeDialogImpl3.mAudioManager, true));
        }
        VolumeListAdapter volumeListAdapter3 = this.volumeListAdapter;
        if (volumeListAdapter3 != null) {
            volumeListAdapter3.resetAnim();
        }
    }

    public final boolean isDismissing() {
        return this.sliderState == SliderState.DISMISSING;
    }

    public final boolean isSideVolumeRowTracking() {
        ArrayList<OplusVolumeRow> sideShowList;
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null && (sideShowList = volumeListAdapter.getSideShowList()) != null && !sideShowList.isEmpty()) {
            int size = sideShowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = sideShowList.get(i);
                i++;
                if (oplusVolumeRow.getTracking()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isSliderShowing() {
        SliderState sliderState = this.sliderState;
        return sliderState == SliderState.SHOWING || sliderState == SliderState.DISMISSING;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void notifySideEnterAnimationEnded(Long l) {
        if (l != null) {
            long jLongValue = l.longValue();
            OplusVolumeSideShowSession oplusVolumeSideShowSession = this.sideShowSession;
            OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(4);
            oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            if (oplusVolumeSideShowSession.markEnterCompleted(jLongValue, oplusVolumeDialogView$$ExternalSyntheticLambda0)) {
                this.blurHostHelper.onSideEnterAnimationEnded();
            }
        }
    }

    public final void onAppVolumeAdjustClick() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->appVolumeAdjustClick");
        }
        if (this.volumeInteractor.mExpanded) {
            return;
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null) {
            volumeListAdapter.setPanelType(false);
        }
        setSliderState(SliderState.SHOWING);
        this.volumeInteractor.expandPanel();
        BuildersKt.launch$default(this.viewScope, (CoroutineContext) null, (CoroutineStart) null, new onAppVolumeAdjustClick.2(this, (Continuation) null), 3);
    }

    public final void onConfigChanged(Configuration configuration) {
        this.mIsRowParentAnimRunning = false;
        this.volumeInteractor.mIsMultiRowShowing = false;
        CustomDialog customDialog = this.volumeDialog;
        if (customDialog == null || !customDialog.isShowing()) {
            return;
        }
        customDialog.dismiss();
        this.mHovering = false;
        resetSliderState();
    }

    public final void onThemeChanged() {
        CustomDialog customDialog = this.volumeDialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
        COUISnackBar cOUISnackBar = this.mSnackBar;
        if (cOUISnackBar != null) {
            cOUISnackBar.dismiss(!isSliderShowing());
        }
        initDialog();
    }

    public final void refreshVisibleRow(OplusVolumeRow oplusVolumeRow) {
        ArrayList<OplusVolumeRow> sideShowList;
        if (!this.visibleRowList.isEmpty()) {
            this.visibleRowList.clear();
        }
        if (!this.visibleSlideList.isEmpty()) {
            this.visibleSlideList.clear();
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter == null || (sideShowList = volumeListAdapter.getSideShowList()) == null || !oplusVolumeRow.isVerticalLayout || sideShowList.size() <= 1) {
            return;
        }
        int size = sideShowList.size();
        for (int i = 0; i < size; i++) {
            for (OplusVolumeRow oplusVolumeRow2 : this.volumeInteractor.mRows) {
                if (Intrinsics.areEqual(oplusVolumeRow2, sideShowList.get(i)) && this.volumeInteractor.shouldBeVisibleH(oplusVolumeRow2, oplusVolumeRow, false)) {
                    this.visibleRowList.add(oplusVolumeRow2);
                    FrameLayout sliderFrame = oplusVolumeRow2.getSliderFrame();
                    ViewGroup.LayoutParams layoutParams = sliderFrame != null ? sliderFrame.getLayoutParams() : null;
                    ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
                    if (layoutParams2 != null) {
                        this.visibleSlideList.add(layoutParams2);
                    }
                }
            }
        }
    }

    public final void refreshVolumeSpotLightType(boolean z) {
        ArrayList<OplusVolumeRow> volumeRowList;
        boolean z2 = this.volumeInteractor.mExpanded;
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null && (volumeRowList = volumeListAdapter.getVolumeRowList()) != null) {
            int size = volumeRowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = volumeRowList.get(i);
                i++;
                OplusVolumeSeekBar slider = oplusVolumeRow.getSlider();
                if (slider != null) {
                    slider.mSpotLightHelper.refreshSpotLightType(z2, z);
                }
            }
        }
        OplusVolumeSettingsButton oplusVolumeSettingsButton = this.mSettingsButton;
        if (oplusVolumeSettingsButton != null) {
            oplusVolumeSettingsButton.refreshSpotLightType();
        }
    }

    public final void releaseBlurResourcesAndDismiss(int i) {
        CustomDialog customDialog = this.volumeDialog;
        boolean z = false;
        if (customDialog != null && customDialog.isShowing()) {
            z = true;
        }
        this.platformBlurSession.clearResources(z);
        if (customDialog != null) {
            if (customDialog.isShowing()) {
                Events.writeEvent(1, new Object[]{Integer.valueOf(i)});
            }
            customDialog.dismiss();
        }
    }

    public final void removeInsetsListener() {
        ViewGroup viewGroup;
        ViewTreeObserver viewTreeObserver;
        ViewTreeObserver.OnComputeInternalInsetsListener onComputeInternalInsetsListener = this.mOnComputeInternalInsetsListener;
        if (onComputeInternalInsetsListener != null && (viewGroup = this.mDialogView) != null && (viewTreeObserver = viewGroup.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnComputeInternalInsetsListener(onComputeInternalInsetsListener);
        }
        this.mOnComputeInternalInsetsListener = null;
    }

    public final void resetBtnThinkAnim(float f) {
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mMoreRowStreamLl;
        Boolean bool = Boolean.TRUE;
        VolumeAnimUtil.resetShowOrHideAnimateView(oplusVolumeSideAccessoryButton, f, true, 1, bool);
        VolumeAnimUtil.resetShowOrHideAnimateView(this.mAppVolumeAdjustFl, f, true, 1, bool);
        VolumeAnimUtil.resetShowOrHideAnimateView(this.mODICaptionsView, f, true, 1, bool);
    }

    public final void resetSeekBar() {
        ArrayList<OplusVolumeRow> sideShowList;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBar");
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter == null || (sideShowList = volumeListAdapter.getSideShowList()) == null) {
            return;
        }
        for (OplusVolumeRow oplusVolumeRow : sideShowList) {
            if (!oplusVolumeRow.getTracking()) {
                OplusVolumeSeekBar slider = oplusVolumeRow.getSlider();
                if (slider != null) {
                    slider.resetSeekBar();
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBar skip while tracking");
            }
        }
    }

    public final void resetSeekBarAndResetAnimateState(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBarAndResetAnimateState");
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        ArrayList<OplusVolumeRow> sideShowList = null;
        if (z) {
            if (volumeListAdapter != null) {
                sideShowList = volumeListAdapter.getPanelShowList();
            }
        } else if (volumeListAdapter != null) {
            sideShowList = volumeListAdapter.getSideShowList();
        }
        if (sideShowList != null) {
            int size = sideShowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = sideShowList.get(i);
                i++;
                OplusVolumeRow oplusVolumeRow2 = oplusVolumeRow;
                if (!oplusVolumeRow2.getTracking()) {
                    VolumeAnimUtil.resetShowOrHideAnimateView(oplusVolumeRow2.getView(), 0.0f, true, 1, Boolean.TRUE);
                    OplusVolumeSeekBar slider = oplusVolumeRow2.getSlider();
                    if (slider != null) {
                        slider.resetSeekBar();
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBarAndResetAnimateState skip row while tracking");
                }
            }
        }
    }

    public final void resetSliderState() {
        setSliderState(SliderState.DISMISSED);
        this.mPortraitPanelSliderTouchTarget = null;
    }

    public final void setExpandSuperVolumeAnim(OplusVolumeRow oplusVolumeRow, boolean z) {
        int dimensionPixelSize = getUpdateNewContext().getResources().getDimensionPixelSize(z ? 2131173972 : 2131173971);
        FrameLayout sliderFrame = oplusVolumeRow.getSliderFrame();
        if (sliderFrame != null) {
            ConstraintLayout.LayoutParams layoutParams = sliderFrame.getLayoutParams();
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(((ViewGroup.MarginLayoutParams) layoutParams).topMargin, dimensionPixelSize);
            valueAnimatorOfInt.setDuration(483L);
            valueAnimatorOfInt.setInterpolator(VolumeAnimUtil.SUPER_VOLUME_MAIN_INTERPOLATOR);
            setExpandSuperVolumeAnim.1.1 r2 = new setExpandSuperVolumeAnim.1.1();
            r2.$slideParams = layoutParams;
            r2.$it = sliderFrame;
            r2.this$0 = this;
            r2.$activeRow = oplusVolumeRow;
            VarHandle.storeStoreFence();
            valueAnimatorOfInt.addUpdateListener(r2);
        }
    }

    public final void setMConfigurableTexts(ConfigurableTexts configurableTexts) {
        this.mConfigurableTexts = configurableTexts;
    }

    public final void setMHovering(boolean z) {
        this.mHovering = z;
    }

    public final void setMIsDefaultVolumeTheme(boolean z) {
        this.mIsDefaultVolumeTheme = z;
    }

    public final void setMVerticalRowIconParent(ViewGroup viewGroup) {
        this.mVerticalRowIconParent = viewGroup;
    }

    public final void setNewContext(Context context) {
        this.newContext = context;
    }

    public final void setSliderState(SliderState sliderState) {
        this.sliderState = sliderState;
    }

    public final void setVolumeDialog(CustomDialog customDialog) {
        this.volumeDialog = customDialog;
    }

    public final void setVolumeListAdapter(VolumeListAdapter volumeListAdapter) {
        this.volumeListAdapter = volumeListAdapter;
    }

    public final boolean shouldBlockSideStructuralForAnim() {
        ArrayList<OplusVolumeRow> sideShowList;
        if (isSideVolumeRowTracking()) {
            return true;
        }
        ViewGroup viewGroup = this.mVerticalRowIconParent;
        if (viewGroup != null && (viewGroup.getScaleX() > 1.0f || viewGroup.getScaleY() > 1.0f || viewGroup.getTranslationY() < -1.0f)) {
            return true;
        }
        VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
        if (volumeListAdapter != null && (sideShowList = volumeListAdapter.getSideShowList()) != null) {
            int size = sideShowList.size();
            int i = 0;
            while (i < size) {
                OplusVolumeRow oplusVolumeRow = sideShowList.get(i);
                i++;
                OplusVolumeRow oplusVolumeRow2 = oplusVolumeRow;
                View view = oplusVolumeRow2.getView();
                if (view != null && (view.getScaleX() > 1.0f || view.getScaleY() > 1.0f || view.getTranslationY() < -1.0f)) {
                    return true;
                }
                COUIVerticalSeekBar slider = oplusVolumeRow2.getSlider();
                if (slider != null && Math.abs(slider.getTranslationX()) > 1.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void showOrHideAnimation(float f, boolean z, int i, Long l) {
        ArrayList<OplusVolumeRow> sideShowList;
        ArrayList<OplusVolumeRow> panelShowList;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        RecyclerView recyclerView;
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
        boolean z2 = oplusVolumeDialogImpl.mExpanded;
        boolean z3 = oplusVolumeDialogImpl.mIsMultiRowShowing;
        StringBuilder sb = new StringBuilder("showOrHideAnimation translationX: ");
        sb.append(f);
        sb.append(" ,toShow: ");
        sb.append(z);
        sb.append(" ,reason: ");
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i, " ,mExpanded: ", " , mIsMultiRowShowing = ", sb, z2);
        sb.append(z3);
        RecyclerView.ItemAnimator itemAnimator = null;
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, sb.toString(), null, 8);
        if (z && (recyclerView = this.mVolumeRecyclerview) != null) {
            recyclerView.setAlpha(1.0f);
        }
        ViewGroup viewGroup = this.mDialogView;
        if (viewGroup != null && (viewPropertyAnimatorAnimate = viewGroup.animate()) != null) {
            viewPropertyAnimatorAnimate.cancel();
        }
        PanelExpandPreludeAnimator panelExpandPreludeAnimator = this.panelExpandPrelude;
        int i2 = 1;
        if (z) {
            panelExpandPreludeAnimator.cancelHandoff(true);
            this.platformBlurSession.onBeforeShowAnimate();
        } else {
            panelExpandPreludeAnimator.cancelRunningAnimationOnly();
            this.platformBlurSession.onBeforeHideAnimate();
        }
        cancelBtnAnim();
        if (z) {
            cancelDismissFallback();
            setSliderState(SliderState.SHOWING);
            resetSeekBar();
        }
        if (!z && this.volumeInteractor.mExpanded) {
            ViewGroup viewGroup2 = this.mDialogView;
            if (viewGroup2 == null) {
                return;
            }
            VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
            List list = (volumeListAdapter == null || (panelShowList = volumeListAdapter.getPanelShowList()) == null) ? null : CollectionsKt___CollectionsKt.toList(panelShowList);
            if (list == null) {
                list = CollectionsKt__CollectionsKt.emptyList();
            }
            PathInterpolator pathInterpolator = VolumeAnimUtil.VOLUME_MAIN_INTERPOLATOR;
            RecyclerView recyclerView2 = this.mVolumeRecyclerview;
            RecyclerView.ItemAnimator itemAnimator2 = recyclerView2 != null ? recyclerView2.getItemAnimator() : null;
            VolumeItemAnimator volumeItemAnimator = itemAnimator2 instanceof VolumeItemAnimator ? (VolumeItemAnimator) itemAnimator2 : null;
            if (volumeItemAnimator != null) {
                volumeItemAnimator.setPanelTransitionSession(this.panelTransitionSession);
            }
            int size = list.size();
            this.panelTransitionSession.planCollapse(this.context, size < 1 ? 1 : size, volumeItemAnimator != null && volumeItemAnimator.isPanelExpandRunning(), volumeItemAnimator != null && volumeItemAnimator.hasPanelExpandStarted(), i == 5 && VolumeLayoutUtil.isLandscape(this.context) && this.volumeInteractor.mExpanded, SystemClock.uptimeMillis());
            if (!this.panelTransitionSession.isNormalPanelCollapseMode()) {
                this.blurHostHelper.restoreSideMaterialAfterPanelInterrupt();
            }
            PanelExpandPreludeAnimator panelExpandPreludeAnimator2 = this.panelExpandPrelude;
            long iconFadeDurationMs = this.panelTransitionSession.getIconFadeDurationMs();
            pathInterpolator.getClass();
            VolumeListAdapter volumeListAdapter2 = this.volumeListAdapter;
            panelExpandPreludeAnimator2.collapseForPanelDismiss(iconFadeDurationMs, pathInterpolator, volumeListAdapter2 != null && volumeListAdapter2.isSystemPanel && volumeListAdapter2.getSideShowList().size() == 1);
            cancelPanelAccessoryHideAnimations();
            viewGroup2.animate().cancel();
            viewGroup2.setAlpha(1.0f);
            RecyclerView recyclerView3 = this.mVolumeRecyclerview;
            if (recyclerView3 != null) {
                recyclerView3.setAlpha(1.0f);
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((OplusVolumeRow) it.next()).setTouchable(false);
            }
            this.mIsDialogAnimRunning = true;
            OplusVolumeFrameRateUtilKt.startFrameRateElevate(viewGroup2, 20502);
            if (this.panelTransitionSession.isNormalPanelCollapseMode()) {
                VolumeListAdapter volumeListAdapter3 = this.volumeListAdapter;
                if (volumeListAdapter3 != null) {
                    VolumeListAdapter.Companion companion = VolumeListAdapter.Companion;
                    volumeListAdapter3.clearPanel(null);
                }
            } else {
                if (volumeItemAnimator != null) {
                    volumeItemAnimator.handoffExpandForCollapse(this.mVolumeRecyclerview);
                }
                RecyclerView recyclerView4 = this.mVolumeRecyclerview;
                if (recyclerView4 != null) {
                    flushRowAndIconParent.3 r5 = new flushRowAndIconParent.3(3);
                    r5.this$0 = this;
                    VarHandle.storeStoreFence();
                    recyclerView4.post(r5);
                }
            }
            this.panelAccessoryAnimator.collapseForDismiss(this.panelTransitionSession, new OplusVolumeDialogView$.ExternalSyntheticLambda16());
            long hideDurationMs = this.panelTransitionSession.getHideDurationMs();
            boolean zIsNormalPanelCollapseMode = this.panelTransitionSession.isNormalPanelCollapseMode();
            Runnable runnable = this.panelHideEndRunnable;
            if (runnable != null) {
                viewGroup2.removeCallbacks(runnable);
            }
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            schedulePanelHideEnd.finishHide.1 r9 = new schedulePanelHideEnd.finishHide.1();
            r9.$finished = booleanRef;
            r9.this$0 = this;
            r9.$reason = i;
            r9.$dialogView = viewGroup2;
            r9.$panelRows = list;
            VarHandle.storeStoreFence();
            this.panelHideEndRunnable = r9;
            RecyclerView recyclerView5 = this.mVolumeRecyclerview;
            itemAnimator = recyclerView5 != null ? recyclerView5.getItemAnimator() : null;
            if (!zIsNormalPanelCollapseMode && itemAnimator != null && itemAnimator.isRunning()) {
                schedulePanelHideEnd.2 r0 = new schedulePanelHideEnd.2();
                r0.$dialogView = viewGroup2;
                r0.$finishHide = r9;
                VarHandle.storeStoreFence();
                if (itemAnimator.isRunning()) {
                    itemAnimator.mFinishedListeners.add(r0);
                } else {
                    r0.onAnimationsFinished();
                }
            }
            viewGroup2.postDelayed(r9, hideDurationMs + 32);
            return;
        }
        if (!z && !this.volumeInteractor.mExpanded) {
            this.sideShowSession.beginExit();
        }
        VolumeListAdapter volumeListAdapter4 = this.volumeListAdapter;
        if (volumeListAdapter4 != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(volumeListAdapter4.getSideShowList());
            int size2 = arrayList.size();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(size2, "showOrHideAnimation visChildCount: ", "OplusVolumeDialogView-->", "SystemUi--Volume");
            }
            if (size2 == 0) {
                if (z) {
                    notifySideEnterAnimationEnded(l);
                    return;
                }
                LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "showOrHideAnimation: visChildCount==0 on hide, doAfterHide reason=" + i);
                doAfterHide(i, this.volumeInteractor.mExpanded);
                return;
            }
            FeatureOption.INSTANCE.getClass();
            boolean zIsOplusVolumeKeyInRight = FeatureOption.isOplusVolumeKeyInRight();
            int i3 = zIsOplusVolumeKeyInRight ? size2 - 1 : 0;
            int i4 = (!(z && zIsOplusVolumeKeyInRight) && (z || zIsOplusVolumeKeyInRight)) ? 0 : size2 - 1;
            int i5 = 0;
            while (i5 < size2) {
                OplusVolumeSeekBar slider = ((OplusVolumeRow) arrayList.get(i5)).getSlider();
                if (slider != null) {
                    if (i5 == i3 && z) {
                        blurHostHelper.1 r8 = new blurHostHelper.1(3);
                        r8.this$0 = this;
                        VarHandle.storeStoreFence();
                        slider.setOnBottomDeformedListener(r8);
                        itemAnimator = null;
                    } else {
                        slider.mBottomDeformedListener = itemAnimator;
                    }
                }
                i5++;
                i2 = 1;
            }
            if (size2 == i2) {
                startShowOrHideAnimWithOnlyRow(f, z, i, (OplusVolumeRow) arrayList.get(0), 0, l);
                return;
            }
            if (!this.volumeInteractor.mIsMultiRowShowing) {
                if (z) {
                    resetBtnThinkAnim(f);
                }
                boolean z4 = !z;
                VolumeAnimUtil.resetShowOrHideAnimateView(this.mVerticalRowIconParent, f, z4, 3, Boolean.TRUE);
                VolumeListAdapter volumeListAdapter5 = this.volumeListAdapter;
                if (volumeListAdapter5 != null && (sideShowList = volumeListAdapter5.getSideShowList()) != null) {
                    int size3 = sideShowList.size();
                    int i6 = 0;
                    while (i6 < size3) {
                        VolumeAnimUtil.resetShowOrHideAnimateView(sideShowList.get(i6).getView(), f, z4, 3, Boolean.TRUE);
                        i6++;
                        size3 = size3;
                        sideShowList = sideShowList;
                    }
                }
            }
            RecyclerView recyclerView6 = this.mVolumeRecyclerview;
            if (recyclerView6 != null) {
                showOrHideAnimation.1.3 r10 = new showOrHideAnimation.1.3();
                r10.$visChildCount = size2;
                r10.$toShow = z;
                r10.$isRight = zIsOplusVolumeKeyInRight;
                r10.$visChild = arrayList;
                r10.$index = i3;
                r10.this$0 = this;
                r10.$translationX = f;
                r10.$reason = i;
                r10.$lastAnimatedRowIndex = i4;
                r10.$sideEnterGeneration = l;
                VarHandle.storeStoreFence();
                recyclerView6.post(r10);
            }
        }
        if (z || this.volumeListAdapter != null) {
            return;
        }
        LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "showOrHideAnimation: volumeListAdapter==null on hide, doAfterHide reason=" + i);
        doAfterHide(i, this.volumeInteractor.mExpanded);
    }

    /* JADX WARN: Code duplicated, block: B:94:0x01b6  */
    public final void showSliderVolume(OplusVolumeRow oplusVolumeRow) {
        ArrayList<OplusVolumeRow> panelShowList;
        RecyclerView.ItemAnimator itemAnimator;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        ViewPropertyAnimator viewPropertyAnimatorAnimate2;
        VolumeDialogController.StreamState ss;
        ViewPropertyAnimator viewPropertyAnimatorAnimate3;
        ViewTreeObserver viewTreeObserver;
        Window window;
        View decorView;
        EffectiveAnimationView effectiveAnimationView;
        cancelPendingTerminalHide(true);
        cancelDismissFallback();
        if (this.mIsDialogAnimRunning || this.panelHideEndRunnable != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->abortPanelCollapseForSideReshow");
            }
            cancelPanelAccessoryHideAnimations();
            this.mIsDialogAnimRunning = false;
            cancelDismissFallback();
            ViewGroup viewGroup = this.mDialogView;
            if (viewGroup != null && (viewPropertyAnimatorAnimate2 = viewGroup.animate()) != null) {
                viewPropertyAnimatorAnimate2.cancel();
            }
            RecyclerView recyclerView = this.mVolumeRecyclerview;
            if (recyclerView != null && (viewPropertyAnimatorAnimate = recyclerView.animate()) != null) {
                viewPropertyAnimatorAnimate.cancel();
            }
            RecyclerView recyclerView2 = this.mVolumeRecyclerview;
            if (recyclerView2 != null && (itemAnimator = recyclerView2.getItemAnimator()) != null) {
                itemAnimator.endAnimations();
            }
            this.panelExpandPrelude.cancelRunningAnimationOnly();
            applyPanelLeaveCleanup();
            VolumeListAdapter volumeListAdapter = this.volumeListAdapter;
            if (volumeListAdapter != null && (panelShowList = volumeListAdapter.getPanelShowList()) != null) {
                int size = panelShowList.size();
                int i = 0;
                while (i < size) {
                    OplusVolumeRow oplusVolumeRow2 = panelShowList.get(i);
                    i++;
                    oplusVolumeRow2.setTouchable(true);
                }
            }
        }
        ViewGroup viewGroup2 = this.mDialogView;
        if (viewGroup2 != null) {
            this.blurHostHelper.unfreezePlatformBlurCaptureRegion();
            this.platformBlurSession.prepareEngine();
            this.blurHostHelper.initPanelOnAttach(viewGroup2);
            this.platformBlurSession.startAttachedObservers(viewGroup2);
        }
        this.mPortraitPanelSliderTouchTarget = null;
        hideBySingleButton(false);
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mMoreRowStreamLl;
        if (oplusVolumeSideAccessoryButton != null && oplusVolumeSideAccessoryButton.getLayoutParams().width != VolumeLayoutUtil.dp2Px(46.0f, this.newContext)) {
            ImageButton imageButton = this.mMoreStreamsButton;
            if (imageButton != null) {
                imageButton.setImageAlpha(255);
            }
            this.volumeInteractor.mExpanded = false;
            VolumeSupplyExportApi.INSTANCE.notifyVolumeDialogIsExpandChanged();
        }
        refreshVolumeSpotLightType(false);
        AlphaOptimizedImageButton alphaOptimizedImageButton = this.mSettingsButton;
        if (alphaOptimizedImageButton != null) {
            alphaOptimizedImageButton.setVisibility(8);
        }
        TextView textView = this.mVolumeSettingText;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.volumeInteractor.mOplusVolumeTipsControler.clearTip();
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
        if (!oplusVolumeDialogImpl.mHasSeenODICaptionsTooltip && this.mODICaptionsTooltipViewStub != null) {
            oplusVolumeDialogImpl.mController.getCaptionsComponentState(true);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() && (effectiveAnimationView = this.mODICaptionsAnimation) != null) {
            effectiveAnimationView.removeAllAnimatorListeners();
            initODICaptionsAnimation.1.1 r4 = new initODICaptionsAnimation.1.1();
            r4.this$0 = this;
            r4.$it = effectiveAnimationView;
            VarHandle.storeStoreFence();
            effectiveAnimationView.effectiveDrawable.animator.addListener(r4);
        }
        CustomDialog customDialog = this.volumeDialog;
        if (customDialog != null && (window = customDialog.getWindow()) != null && (decorView = window.getDecorView()) != null) {
            this.mSnackBar = COUISnackBar.make(decorView, this.context.getString(2131953416));
        }
        COUISnackBar cOUISnackBar = this.mSnackBar;
        if (cOUISnackBar != null) {
            cOUISnackBar.setFocusableInTouchMode(false);
        }
        this.panelAccessoryAnimator.prepareTitleForCollapsedState();
        ViewTreeObserver.OnComputeInternalInsetsListener seekbarInternalInsetsListener = getSeekbarInternalInsetsListener();
        removeInsetsListener();
        ViewGroup viewGroup3 = this.mDialogView;
        if (viewGroup3 != null && (viewTreeObserver = viewGroup3.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnComputeInternalInsetsListener(seekbarInternalInsetsListener);
        }
        this.mOnComputeInternalInsetsListener = seekbarInternalInsetsListener;
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("showSliderVolume isLowGaussianLevelOrSuperPowerSave: ", ", mIsDefaultVolumeTheme = ", VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.context), this.mIsDefaultVolumeTheme), null, 8);
        if (isDismissing()) {
            ViewGroup viewGroup4 = this.mDialogView;
            if (viewGroup4 != null && (viewPropertyAnimatorAnimate3 = viewGroup4.animate()) != null) {
                viewPropertyAnimatorAnimate3.cancel();
            }
            this.panelExpandPrelude.cancelRunningAnimationOnly();
        }
        RecyclerView recyclerView3 = this.mVolumeRecyclerview;
        if (recyclerView3 != null) {
            setSliderState(SliderState.SHOWING);
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled) {
                OplusVolumeDialogImpl oplusVolumeDialogImpl2 = this.volumeInteractor;
                if (oplusVolumeDialogImpl2.mDialogShowingForMultiMedia) {
                    MultiMediaRepository multiMediaRepository = oplusVolumeDialogImpl2.mMultiMediaRepository;
                    OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(11);
                    oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    multiMediaRepository.updateHMultiMediaRows(oplusVolumeDialogView$$ExternalSyntheticLambda0);
                } else {
                    updateAppVolumeAdjust();
                }
            } else {
                updateAppVolumeAdjust();
            }
            recyclerView3.setAlpha(0.0f);
            ViewGroup viewGroup5 = this.mVerticalRowIconParent;
            if (viewGroup5 != null) {
                viewGroup5.setAlpha(0.0f);
            }
            ViewGroup viewGroup6 = this.mVerticalRowIconParent;
            if (viewGroup6 != null) {
                viewGroup6.setVisibility(0);
            }
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mMoreRowStreamLl;
            if (oplusVolumeSideAccessoryButton2 != null) {
                oplusVolumeSideAccessoryButton2.setVisibility(0);
            }
            if (this.mIsDefaultVolumeTheme) {
                this.blurHostHelper.scheduleSideAccessoryBlurCaptureRefresh();
            } else {
                adaptThemeDrawable(this.mMoreRowStreamLl, this.mAppVolumeAdjustFl, this.mODICaptionsView, false);
            }
            long jBeginEnter = this.sideShowSession.beginEnter();
            OplusVolumeDialogView$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new OplusVolumeDialogView$.ExternalSyntheticLambda9(0);
            externalSyntheticLambda9.f$0 = this;
            externalSyntheticLambda9.f$1 = jBeginEnter;
            VarHandle.storeStoreFence();
            OplusVolumeDialogView$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new OplusVolumeDialogView$.ExternalSyntheticLambda10();
            externalSyntheticLambda10.f$0 = this;
            externalSyntheticLambda10.f$1 = externalSyntheticLambda9;
            VarHandle.storeStoreFence();
            updateRowsH(oplusVolumeRow, false, externalSyntheticLambda10);
        }
        OplusVolumeSeekBar slider = oplusVolumeRow.getSlider();
        if (slider != null && (ss = oplusVolumeRow.getSs()) != null && OplusVolumeRow.Companion.getImpliedLevel(slider, slider.getProgress()) == ss.level) {
            this.volumeInteractor.setVolumeChangedAndFromKey(Boolean.TRUE);
        }
        OplusVolumeDialogImpl oplusVolumeDialogImpl3 = this.volumeInteractor;
        oplusVolumeDialogImpl3.mOplusVolumeSnackBarController.showSnackBar(this.mSnackBar, oplusVolumeDialogImpl3.mState, oplusVolumeDialogImpl3.mKeyguardStateController);
    }

    public final void startBtnThickAnim(float f, boolean z) {
        COUISpringInterpolator cOUISpringInterpolator;
        if (this.newContext != null) {
            long j = z ? 450L : 600L;
            if (z) {
                cOUISpringInterpolator = VolumeAnimUtil.VOLUME_START_THICK_INTERPOLATOR;
                cOUISpringInterpolator.getClass();
            } else {
                cOUISpringInterpolator = VolumeAnimUtil.VOLUME_END_THICK_INTERPOLATOR;
                cOUISpringInterpolator.getClass();
            }
            COUISpringInterpolator cOUISpringInterpolator2 = cOUISpringInterpolator;
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mMoreRowStreamLl;
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mMoreRowStreamLl;
            Triple triple = new Triple(oplusVolumeSideAccessoryButton, 1, Boolean.valueOf(oplusVolumeSideAccessoryButton2 != null && oplusVolumeSideAccessoryButton2.getVisibility() == 0));
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton3 = this.mAppVolumeAdjustFl;
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton4 = this.mAppVolumeAdjustFl;
            Triple triple2 = new Triple(oplusVolumeSideAccessoryButton3, 2, Boolean.valueOf(oplusVolumeSideAccessoryButton4 != null && oplusVolumeSideAccessoryButton4.getVisibility() == 0));
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton5 = this.mODICaptionsView;
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton6 = this.mODICaptionsView;
            int i = 0;
            for (Triple triple3 : CollectionsKt__CollectionsKt.listOf(new Triple[]{triple, triple2, new Triple(oplusVolumeSideAccessoryButton5, 3, Boolean.valueOf(oplusVolumeSideAccessoryButton6 != null && oplusVolumeSideAccessoryButton6.getVisibility() == 0))})) {
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton7 = (OplusVolumeSideAccessoryButton) triple3.component1();
                int iIntValue = ((Number) triple3.component2()).intValue();
                if (((Boolean) triple3.component3()).booleanValue()) {
                    int i2 = iIntValue - i;
                    float iconHalfDelta = (-((i2 * 2) - 1)) * getIconHalfDelta();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", "OplusVolumeDialogView-->".concat("startBtnThickAnim : iconIndex" + i2 + ",transY:" + iconHalfDelta));
                    }
                    float f2 = z ? f : 0.0f;
                    float f3 = z ? iconHalfDelta : 0.0f;
                    float f4 = !z ? 1 : 0;
                    VolumeAnimUtil.startViewPropertyAnimation(oplusVolumeSideAccessoryButton7, 0L, j, cOUISpringInterpolator2, f2, f3, f4, f4, null, null, null, 15);
                } else {
                    i++;
                }
            }
        }
    }

    public final void startShowOrHideAnimWithOnlyRow(float f, boolean z, int i, OplusVolumeRow oplusVolumeRow, int i2, Long l) {
        View view;
        View view2;
        if (z) {
            resetBtnThinkAnim(f);
            View view3 = oplusVolumeRow.getView();
            Boolean bool = Boolean.TRUE;
            VolumeAnimUtil.resetShowOrHideAnimateView(view3, f, false, 3, bool);
            VolumeAnimUtil.resetShowOrHideAnimateView(this.mVerticalRowIconParent, f, false, 3, bool);
        } else if (!this.mIsRowParentAnimRunning) {
            View view4 = oplusVolumeRow.getView();
            Boolean bool2 = Boolean.TRUE;
            VolumeAnimUtil.resetShowOrHideAnimateView(view4, f, true, 3, bool2);
            VolumeAnimUtil.resetShowOrHideAnimateView(this.mVerticalRowIconParent, f, true, 3, bool2);
        }
        if ((oplusVolumeRow.getView() == null || !((view2 = oplusVolumeRow.getView()) == null || view2.isAttachedToWindow())) && !z) {
            View view5 = oplusVolumeRow.getView();
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "startShowOrHideAnimWithOnlyRow view is null or row.view.isAttachedToWindow " + (view5 != null ? Boolean.valueOf(view5.isAttachedToWindow()) : null));
            doAfterHide(i, this.volumeInteractor.mExpanded);
            return;
        }
        if (!z || (oplusVolumeRow.getView() != null && ((view = oplusVolumeRow.getView()) == null || view.isAttachedToWindow()))) {
            View view6 = oplusVolumeRow.getView();
            if (view6 != null) {
                OplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1 oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1 = new OplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1();
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.this$0 = this;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$row = oplusVolumeRow;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$it = view6;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$toShow = z;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$translationX = f;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$reason = i;
                oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1.$sideEnterGeneration = l;
                VarHandle.storeStoreFence();
                view6.postOnAnimation(oplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1);
                return;
            }
            return;
        }
        if (i2 >= 2) {
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "startShowOrHideAnimWithOnlyRow view not attached after retry, skip enter anim");
            notifySideEnterAnimationEnded(l);
            return;
        }
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView != null) {
            RunnableC03121 runnableC03121 = new RunnableC03121();
            runnableC03121.this$0 = this;
            runnableC03121.$translationX = f;
            runnableC03121.$toShow = z;
            runnableC03121.$reason = i;
            runnableC03121.$row = oplusVolumeRow;
            runnableC03121.$enterAttachRetry = i2;
            runnableC03121.$sideEnterGeneration = l;
            VarHandle.storeStoreFence();
            recyclerView.postOnAnimation(runnableC03121);
        }
    }

    public final void tryFlushPendingSideStructuralUpdate() {
        OplusVolumeSideShowSession oplusVolumeSideShowSession = this.sideShowSession;
        OplusVolumeDialogView$$ExternalSyntheticLambda0 oplusVolumeDialogView$$ExternalSyntheticLambda0 = new OplusVolumeDialogView$$ExternalSyntheticLambda0(1);
        oplusVolumeDialogView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        oplusVolumeSideShowSession.flushPendingStructuralUpdate(oplusVolumeDialogView$$ExternalSyntheticLambda0);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:31:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d1  */
    public final void updateAppVolumeAdjust() {
        boolean z = MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled;
        Ref.IntRef intRef = new Ref.IntRef();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mAppVolumeAdjustFl;
            FlavorOneFeatureOption.INSTANCE.getClass();
            boolean sFlavorOneMultiMediaDevice = VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice();
            boolean z2 = this.volumeInteractor.mExpanded;
            boolean zIsFolding = DeviceStateController.getInstance().isFolding();
            StringBuilder sb = new StringBuilder("updateAppVolumeAdjust isMultiMediaSettingsEnable=");
            sb.append(z);
            sb.append(",mAppVolumeAdjustFl=");
            sb.append(oplusVolumeSideAccessoryButton);
            sb.append(",isFlavorMultiMediaDevice=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(",mExpanded:=", ",isFolding=", sb, sFlavorOneMultiMediaDevice, z2);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusVolumeDialogView-->", "SystemUi--Volume", sb, zIsFolding);
        }
        if (this.volumeInteractor.mExpanded) {
            return;
        }
        int i = 0;
        if (this.mAppVolumeAdjustFl != null && !isDismissing()) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && z && !this.volumeInteractor.mMultiMediaRepository.getMultiMediaRows().isEmpty()) {
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton2 = this.mMoreRowStreamLl;
                if ((oplusVolumeSideAccessoryButton2 != null ? Integer.valueOf(oplusVolumeSideAccessoryButton2.getVisibility()) : null) == 0) {
                    OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton3 = this.mAppVolumeAdjustFl;
                    if ((oplusVolumeSideAccessoryButton3 != null ? Integer.valueOf(oplusVolumeSideAccessoryButton3.getVisibility()) : null) != 0) {
                        if (isSideVolumeRowTracking()) {
                            if (LogUtil.getNormalDebug()) {
                                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->updateBtWithAppAdjust skip resetSeekBarAndBtn while tracking");
                            }
                            cancelBtnAnim();
                            resetBtnThinkAnim(0.0f);
                        } else {
                            resetSeekBarAndResetAnimateState(false);
                            if (LogUtil.getNormalDebug()) {
                                Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBarAndBtn");
                            }
                            cancelBtnAnim();
                            resetBtnThinkAnim(0.0f);
                        }
                    }
                } else if (isSideVolumeRowTracking()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", "OplusVolumeDialogView-->updateBtWithAppAdjust skip resetSeekBarAndBtn while tracking");
                    }
                    cancelBtnAnim();
                    resetBtnThinkAnim(0.0f);
                } else {
                    resetSeekBarAndResetAnimateState(false);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", "OplusVolumeDialogView-->resetSeekBarAndBtn");
                    }
                    cancelBtnAnim();
                    resetBtnThinkAnim(0.0f);
                }
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton4 = this.mMoreRowStreamLl;
                if (oplusVolumeSideAccessoryButton4 != null) {
                    oplusVolumeSideAccessoryButton4.setVisibility(0);
                }
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton5 = this.mAppVolumeAdjustFl;
                if (oplusVolumeSideAccessoryButton5 != null) {
                    oplusVolumeSideAccessoryButton5.setVisibility(0);
                }
                this.blurHostHelper.scheduleSideAccessoryBlurCaptureRefresh();
                intRef.element = 2;
            } else {
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton6 = this.mAppVolumeAdjustFl;
                if (oplusVolumeSideAccessoryButton6 != null) {
                    oplusVolumeSideAccessoryButton6.setVisibility(8);
                }
                intRef.element = 1;
            }
        }
        BuildersKt.launch$default(this.viewScope, (CoroutineContext) null, (CoroutineStart) null, new C03132(intRef, null), 3);
        if (isSliderShowing() && VolumeLayoutUtil.isLandscape(this.context)) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                return;
            }
            if (!FeatureOption.isLargeScreenFolding() || DeviceStateController.getInstance().isFolding()) {
                int iMax = Math.max(38, 19);
                Resources resources = getUpdateNewContext().getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(2131173976) + (this.iconParentButtonSizeChanged ? resources.getDimensionPixelSize(2131173975) : resources.getDimensionPixelSize(2131173949));
                ViewGroup viewGroup = this.mVerticalRowIconParent;
                LinearLayout linearLayout = viewGroup instanceof LinearLayout ? (LinearLayout) viewGroup : null;
                if (linearLayout != null) {
                    Collection collectionUntil = RangesKt___RangesKt.until(0, linearLayout.getChildCount());
                    if (!(collectionUntil instanceof Collection) || !collectionUntil.isEmpty()) {
                        IntIterator it = collectionUntil.iterator();
                        while (it.hasNext()) {
                            if (linearLayout.getChildAt(it.nextInt()).getVisibility() == 0 && (i = i + 1) < 0) {
                                CollectionsKt__CollectionsKt.throwCountOverflow();
                                throw null;
                            }
                        }
                    }
                }
                RecyclerView recyclerView = this.mVolumeRecyclerview;
                if (recyclerView != null) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                    int dimensionPixelSize2 = resources.getDimensionPixelSize(2131173978);
                    int i2 = screenHeight;
                    int i3 = dimensionPixelSize * i;
                    int i4 = iMax * 2;
                    if (i2 < ((dimensionPixelSize2 + i3) * 1.1f) + i4) {
                        dimensionPixelSize2 = (int) (((i2 - i4) / 1.1f) - i3);
                    }
                    int i5 = (i2 - (i3 + dimensionPixelSize2)) / 2;
                    int dimensionPixelSize3 = resources.getDimensionPixelSize(2131173939) + resources.getDimensionPixelSize(2131171238) + resources.getDimensionPixelSize(2131171237) + dimensionPixelSize2;
                    if (((ViewGroup.MarginLayoutParams) layoutParams).height != dimensionPixelSize3 || ((ViewGroup.MarginLayoutParams) layoutParams).topMargin != i5) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).height = dimensionPixelSize3;
                        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i5;
                        layoutParams.verticalBias = 0.0f;
                        recyclerView.setLayoutParams(layoutParams);
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                        int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateSeekbarLengthOnLandscape seekbarHeight: ", dimensionPixelSize2, i6, ", lp.height: ", ", lp.topMargin: ");
                        RectList$.ExternalSyntheticOutline0.m(i7, dimensionPixelSize, ", bottomHeight: ", ", bottomNum: ", sbM);
                        MobileInputLogger$.ExternalSyntheticOutline0.m(i, "OplusVolumeDialogView-->", "SystemUi--Volume", sbM);
                    }
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [android.widget.ImageButton, com.oplus.systemui.volume.OplusCaptionsToggleImageButton] */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.widget.ImageButton, com.oplus.systemui.volume.OplusCaptionsToggleImageButton] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateCaptionsIcon(int i) {
        ?? r4;
        boolean zAreCaptionsEnabled = ((OplusVolumeControllerExImpl) this.volumeInteractor.mOplusVolumeController).areCaptionsEnabled();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("updateCaptionsIcon captionsEnabled=", i, "reason=", "OplusVolumeDialogView-->", zAreCaptionsEnabled));
        }
        OplusCaptionsToggleImageButton oplusCaptionsToggleImageButton = this.mODICaptionsIcon;
        if ((oplusCaptionsToggleImageButton != null ? Boolean.valueOf(oplusCaptionsToggleImageButton.getCaptionsEnabled()) : null) != Boolean.valueOf(zAreCaptionsEnabled)) {
            if (i != 0) {
                if (i == 1 && (r4 = this.mODICaptionsIcon) != 0) {
                    r4.post(r4.setCaptionsEnabled(zAreCaptionsEnabled));
                    return;
                }
                return;
            }
            EffectiveAnimationView effectiveAnimationView = this.mODICaptionsAnimation;
            if (effectiveAnimationView != null) {
                if (effectiveAnimationView.isAnimating()) {
                    effectiveAnimationView.cancelAnimation();
                }
                effectiveAnimationView.setVisibility(0);
                effectiveAnimationView.setAnimation(zAreCaptionsEnabled ? "volumeui/ic_volume_odi_caption_enable_light.json" : "volumeui/ic_volume_odi_caption_disable_light.json");
                OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mODICaptionsView;
                if (oplusVolumeSideAccessoryButton != null) {
                    oplusVolumeSideAccessoryButton.refreshIconTintForPlatformBlur();
                }
                effectiveAnimationView.playAnimation();
                ?? r5 = this.mODICaptionsIcon;
                if (r5 != 0) {
                    r5.post(r5.setCaptionsEnabled(zAreCaptionsEnabled));
                }
            }
        }
    }

    public final void updateODICaptionsH(boolean z) {
        OplusVolumeCaptions oplusVolumeCaptionEx = this.volumeInteractor.getOplusVolumeCaptionEx();
        boolean z2 = this.volumeInteractor.mExpanded;
        OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.mODICaptionsView;
        oplusVolumeCaptionEx.getClass();
        if (oplusVolumeSideAccessoryButton != null) {
            oplusVolumeSideAccessoryButton.setVisibility((!z || z2) ? 8 : 0);
        }
        if (z) {
            updateCaptionsIcon(1);
        }
    }

    public final void updateRowsH(OplusVolumeRow oplusVolumeRow, boolean z, Function0 function0) {
        CustomDialog customDialog;
        VolumeListAdapter volumeListAdapter;
        ArrayList<OplusVolumeRow> sideShowList;
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateRowsH activeRow stream=", oplusVolumeRow.getStream(), ", show:", isSliderShowing()), null, 8);
        if (!isSliderShowing()) {
            this.volumeInteractor.trimObsoleteH();
        }
        BuildersKt.launch$default(this.viewScope, (CoroutineContext) null, (CoroutineStart) null, new C03141(oplusVolumeRow, z, function0, null), 3);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
            Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateRowsH mDialogShowed:", " mExpanded:", "OplusVolumeDialogView-->", oplusVolumeDialogImpl.mDialogShowingForMultiMedia, oplusVolumeDialogImpl.mExpanded));
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            this.volumeInteractor.mMultiMediaRepository.updateRowsHMultiMediaRows();
        }
        if (!this.volumeInteractor.mExpanded || (customDialog = this.volumeDialog) == null || !customDialog.isShowing() || (volumeListAdapter = this.volumeListAdapter) == null || !volumeListAdapter.isSystemPanel || (sideShowList = volumeListAdapter.getSideShowList()) == null) {
            return;
        }
        int size = sideShowList.size();
        int i = 0;
        while (i < size) {
            OplusVolumeRow oplusVolumeRow2 = sideShowList.get(i);
            i++;
            convertRowLayout(oplusVolumeRow2, true);
        }
    }

    public final void updateState(boolean z) {
        updateODICaptionsH(z);
        Window window = this.mWindow;
        if (window != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
            window.setTitle(oplusVolumeDialogImpl.mContext.getString(2131957180, oplusVolumeDialogImpl.mIconAbility.getStreamLabelH(oplusVolumeDialogImpl.getActiveRow().getSs())));
        }
    }

    public final void watchPanelExpandCompletion() {
        RecyclerView recyclerView = this.mVolumeRecyclerview;
        if (recyclerView == null) {
            return;
        }
        showOrHideAnimation.1.3.2.1 r1 = new showOrHideAnimation.1.3.2.1(1);
        r1.this$0 = this;
        r1.$it = recyclerView;
        VarHandle.storeStoreFence();
        recyclerView.postOnAnimation(r1);
    }

    public final void updateRowsH(OplusVolumeRow oplusVolumeRow, boolean z) {
        updateRowsH$default(this, oplusVolumeRow, z, 4);
    }

    public final void updateRowsH(OplusVolumeRow oplusVolumeRow) {
        updateRowsH$default(this, oplusVolumeRow, false, 6);
    }
}
