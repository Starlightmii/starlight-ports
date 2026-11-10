package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.compose.animation.core.MonoSpline$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticOutline0;
import androidx.core.graphics.ColorUtils;
import com.android.keyguard.OplusLockIconView$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.R;
import com.android.systemui.application.impl.SystemUIApplicationImpl$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$$ExternalSyntheticOutline0;
import com.android.systemui.navigationbar.views.buttons.ButtonInterface;
import com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticOutline0;
import com.oplus.app.OplusAppEnterInfo;
import com.oplus.app.OplusAppExitInfo;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.app.OplusAppSwitchManager;
import com.oplus.keyguard.VFXController$stopAnim$1$$ExternalSyntheticOutline0;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$$ExternalSyntheticOutline0;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleAnimType;
import com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleAnimatorController;
import com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleOldAnimType;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureHandleCuiObserver;
import com.oplus.systemui.navigationbar.observer.SystemFoldModeObserver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.navigationbar.utils.OcrScreenCompatVersion;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.statusbar.controller.ScreenBurnInProtector;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class OplusNavigationHandle extends View implements ButtonInterface, ScreenBurnInProtector.OnBurnInPreventCallback {
    public static final Companion Companion = new Companion();
    public static final String NAVIGATION_GESTURE_VIEW_LANDSCAPE_WIDTH = "navigation_gesture_view_landscape_width";
    public static final String NAVIGATION_GESTURE_VIEW_WIDTH = "navigation_gesture_view_width";
    public static final String TAG = "OplusNavigationHandle";
    public int animationColorFrom;
    public final int animationColorTo;
    private OplusAppSwitchManager.OnAppSwitchObserver appSwitchObserver;
    public float fadedAnimProgress;
    public int fadedAnimationColorFrom;
    public int fadedAnimationColorTo;
    public boolean inGestureXRange;
    public boolean isCalledLongPress;
    public boolean isInScroll;
    public boolean isInScrollX;
    public boolean isInScrollY;
    public boolean isInSplitScreenMode;
    public boolean isKeyguardShowing;
    public boolean isMultitouch;
    public boolean isSplitScreenChange;
    public float longPressAnimProgress;
    public final float mAdditionalHeightForAnimation;
    public final int mBrightDarkColor;
    public final int mBrightLightColor;
    public final boolean mBurnInEnable;
    public final float mBurnInMoveDistance;
    public boolean mBurnInMovingUp;
    public float mCurrentBurnInMove;
    public final int mDarkColor;
    public final int mFadedDarkColor;
    public final int mFadedLightColor;
    private NavigationGestureDetector mGestureDetector;
    private NavigationGestureDetector mGestureDetectorOld;
    public final int mHandleBottom;
    public final int mHeight;
    public final float mIntensityTransition;
    public int mLandscapeWidth;
    private String mLastAppPkgName;
    public boolean mLastFadedColorState;
    public float mLastIntensity;
    public final int mLightColor;
    public final float mMoveThreshold;
    private final Paint mPaint;
    public int mPortraitWidth;
    public final int mRadius;
    public boolean mRequiresInvalidate;
    public final int mSplitScreenColor;
    public final boolean mTestDisableFadedAnim;
    public final boolean mTestStartFadedAnim;
    public final long mTestStartFadedAnimDuration;
    private OplusHandleAnimatorController oplusHandleAnimatorController;
    public int paintColor;
    private final OplusNavigationHandle$uiHandler$1 uiHandler;
    public int viewScreenLeft;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r9v5, types: [com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle$uiHandler$1] */
    public OplusNavigationHandle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mIntensityTransition = 1.0f;
        this.mBurnInMovingUp = true;
        this.animationColorFrom = paint.getColor();
        this.animationColorTo = -1;
        this.fadedAnimationColorFrom = paint.getColor();
        this.fadedAnimationColorTo = paint.getColor();
        this.paintColor = paint.getColor();
        this.mTestDisableFadedAnim = SystemProperties.getBoolean("debug.oplus.oplus_navigation_disable_faded_anim", false);
        this.mTestStartFadedAnim = SystemProperties.getBoolean("debug.oplus.systemui.oplus_navigation_start_faded_anim", false);
        this.mTestStartFadedAnimDuration = SystemProperties.getLong("debug.oplus.systemui.oplus_navigation_start_faded_anim_duration", 300000L);
        this.mBurnInEnable = SystemProperties.getBoolean("ro.oplus.systemui.navbar_burnin_enable", true);
        Resources resources = context.getResources();
        this.mRadius = resources.getDimensionPixelSize(R.dimen.navigation_handle_radius);
        this.mHandleBottom = resources.getDimensionPixelSize(R.dimen.navigation_handle_bottom);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.navigation_handle_height);
        this.mHeight = dimensionPixelOffset;
        this.mAdditionalHeightForAnimation = dimensionPixelOffset * 2.3f;
        this.mMoveThreshold = resources.getDimension(R.dimen.navigation_handle_move_distance);
        this.mBurnInMoveDistance = resources.getDimensionPixelSize(R.dimen.navigation_handle_burn_in_move_distance);
        this.mPortraitWidth = getGestureWidthRes(NAVIGATION_GESTURE_VIEW_WIDTH);
        this.mLandscapeWidth = getGestureWidthRes(NAVIGATION_GESTURE_VIEW_LANDSCAPE_WIDTH);
        setLayerType(2, paint);
        int themeAttr = Utils.getThemeAttr(R.attr.darkIconTheme, context);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.lightIconTheme, context));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(R.attr.homeHandleColor, 0, contextThemeWrapper);
        this.mBrightLightColor = Utils.getColorAttrDefaultColor(R.attr.brightHomeHandleColor, 0, contextThemeWrapper);
        this.mFadedLightColor = Utils.getColorAttrDefaultColor(R.attr.fadedHomeHandleColor, 0, contextThemeWrapper);
        this.mDarkColor = Utils.getColorAttrDefaultColor(R.attr.homeHandleColor, 0, contextThemeWrapper2);
        this.mBrightDarkColor = Utils.getColorAttrDefaultColor(R.attr.brightHomeHandleColor, 0, contextThemeWrapper2);
        this.mFadedDarkColor = Utils.getColorAttrDefaultColor(R.attr.fadedHomeHandleColor, 0, contextThemeWrapper2);
        this.mSplitScreenColor = context.getResources().getColor(R.color.navigation_bar_home_handle_split_screen_color, context.getTheme());
        paint.setAntiAlias(true);
        setFocusable(false);
        this.oplusHandleAnimatorController = new OplusHandleAnimatorController(context, this);
        this.mGestureDetector = new NavigationGestureDetector(context, new NavigationGestureDetector.SimpleOnGestureListener() { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.1
            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onDown() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (oplusNavigationHandle.shouldHandlePress()) {
                    GestureHomeHandleEventController.INSTANCE.onDown();
                }
                OplusNavigationHandle.this.postBrightColorTask();
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onLongPress() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    Log.d("SystemUi--NavBar", SystemUIApplicationImpl$$ExternalSyntheticOutline0.m("GestureDetector onLongPress,inGestureXRange: ", ", enableLongPress： ", "OplusNavigationHandle-->", oplusNavigationHandle.inGestureXRange, oplusNavigationHandle.shouldHandlePress()));
                }
                OplusNavigationHandle oplusNavigationHandle2 = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (oplusNavigationHandle2.shouldHandlePress() && OplusNavigationHandle.this.oplusHandleAnimatorController.isLongPressAnimRunning()) {
                    OplusNavigationHandle oplusNavigationHandle3 = OplusNavigationHandle.this;
                    oplusNavigationHandle3.isCalledLongPress = true;
                    oplusNavigationHandle3.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndLongPressAnim, 0.0f);
                    GestureHomeHandleEventController.INSTANCE.onLongClick();
                }
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onPreLongPress() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (oplusNavigationHandle.shouldHandlePress()) {
                    GestureHomeHandleEventController.INSTANCE.onPreLongPress();
                }
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (motionEvent != null) {
                    OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                    float y = motionEvent2.getY() - motionEvent.getY();
                    float x = motionEvent2.getX() - motionEvent.getX();
                    if (!oplusNavigationHandle.isInScroll && (Math.abs(y) > oplusNavigationHandle.mMoveThreshold || Math.abs(x) > oplusNavigationHandle.mMoveThreshold)) {
                        oplusNavigationHandle.isInScroll = true;
                        if (Math.abs(y) > Math.abs(x)) {
                            oplusNavigationHandle.isInScrollY = true;
                        } else {
                            oplusNavigationHandle.isInScrollX = true;
                        }
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                        boolean z = oplusNavigationHandle.isInScroll;
                        boolean z2 = oplusNavigationHandle.isInScrollX;
                        boolean z3 = oplusNavigationHandle.isInScrollY;
                        StringBuilder sbM = TextFieldImplKt$$ExternalSyntheticOutline0.m("isInScroll:", ", isInScrollX:", ",isInScrollY:", z, z2);
                        sbM.append(z3);
                        sbM.append(",");
                        Log.d("SystemUi--NavBar", "OplusNavigationHandle-->".concat(sbM.toString()));
                    }
                    if (oplusNavigationHandle.isInScroll) {
                        if (oplusNavigationHandle.isInScrollX) {
                            if (oplusNavigationHandle.isKeyguardShowing || !ScrimUtil.isMiddleOrHighAnimLevel(oplusNavigationHandle.getContext())) {
                                oplusNavigationHandle.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndLongPressAnim, 0.0f);
                            } else {
                                oplusNavigationHandle.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.StartFollowXHandAnim, x);
                            }
                        }
                        if (oplusNavigationHandle.isInScrollY) {
                            if (ScrimUtil.isMiddleOrHighAnimLevel(oplusNavigationHandle.getContext())) {
                                oplusNavigationHandle.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.StartFollowYHandAnim, y);
                            } else {
                                oplusNavigationHandle.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndLongPressAnim, 0.0f);
                            }
                        }
                        if (!oplusNavigationHandle.shouldHandlePress() || oplusNavigationHandle.isCalledLongPress) {
                            return;
                        }
                        GestureHomeHandleEventController.INSTANCE.onLongPressCancel();
                    }
                }
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onShowPress() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (oplusNavigationHandle.shouldHandlePress()) {
                    OplusNavigationHandle.this.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.StartLongPressAnim, 0.0f);
                    GestureHomeHandleEventController.INSTANCE.onShowPress();
                }
            }
        });
        this.mGestureDetectorOld = new NavigationGestureDetector(context, new NavigationGestureDetector.SimpleOnGestureListener() { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.2
            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onDown() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                oplusNavigationHandle.postBrightColorTask();
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onLongPress() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (!oplusNavigationHandle.shouldHandlePress() || OplusNavigationHandle.this.oplusHandleAnimatorController.isOldTransitionAnimRunning()) {
                    return;
                }
                GestureHomeHandleEventController.INSTANCE.onLongClick();
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (motionEvent != null) {
                    OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                    float y = motionEvent2.getY() - motionEvent.getY();
                    float x = motionEvent2.getX() - motionEvent.getX();
                    if (oplusNavigationHandle.oplusHandleAnimatorController.isOldTransitionAnimRunning()) {
                        return;
                    }
                    if (Math.abs(y) > oplusNavigationHandle.mMoveThreshold || Math.abs(x) > oplusNavigationHandle.mMoveThreshold) {
                        if (Math.abs(y) > Math.abs(x)) {
                            oplusNavigationHandle.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.StartUpTransitionAnim);
                        } else if (x > 0.0f) {
                            oplusNavigationHandle.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.StartRightTransitionAnim);
                        } else {
                            oplusNavigationHandle.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.StartLeftTransitionAnim);
                        }
                    }
                }
            }

            @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector.SimpleOnGestureListener
            public final void onShowPress() {
                OplusNavigationHandle oplusNavigationHandle = OplusNavigationHandle.this;
                Companion companion = OplusNavigationHandle.Companion;
                if (oplusNavigationHandle.shouldHandlePress()) {
                    OplusNavigationHandle.this.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.StartOldLongPressAnim);
                    GestureHomeHandleEventController.INSTANCE.onShowPress();
                }
            }
        });
        this.appSwitchObserver = new OplusAppSwitchManager.OnAppSwitchObserver() { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle$appSwitchObserver$1
            public final void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) {
            }

            public final void onActivityExit(OplusAppExitInfo oplusAppExitInfo) {
            }

            public final void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) {
                if (oplusAppEnterInfo != null) {
                    OplusNavigationHandle oplusNavigationHandle = this.this$0;
                    int i = oplusAppEnterInfo.windowMode;
                    if (i == 1 || (i == 6 && Intrinsics.areEqual(oplusNavigationHandle.mLastAppPkgName, oplusAppEnterInfo.targetName))) {
                        if (oplusNavigationHandle.isShown() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                            Log.d("SystemUi--NavBar", "OplusNavigationHandle-->".concat("onAppEnter intensity = " + oplusNavigationHandle.mLastIntensity + " targetName = " + oplusAppEnterInfo.targetName + " mLastAppPkgName = " + oplusNavigationHandle.mLastAppPkgName + " paintColor = " + oplusNavigationHandle.colorIntToHex(oplusNavigationHandle.mPaint.getColor())));
                        }
                        oplusNavigationHandle.mLastAppPkgName = oplusAppEnterInfo.targetName;
                        oplusNavigationHandle.postBrightColorTask();
                    }
                }
            }

            public final void onAppExit(OplusAppExitInfo oplusAppExitInfo) {
            }
        };
        final Looper mainLooper = Looper.getMainLooper();
        this.uiHandler = new Handler(mainLooper) { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle$uiHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    OplusNavigationHandle oplusNavigationHandle = this.this$0;
                    oplusNavigationHandle.mLastFadedColorState = true;
                    OplusNavigationHandle.access$updatePaintColor(oplusNavigationHandle, oplusNavigationHandle.mFadedLightColor, oplusNavigationHandle.mFadedDarkColor);
                } else {
                    if (i == 2) {
                        OplusNavigationHandle oplusNavigationHandle2 = this.this$0;
                        oplusNavigationHandle2.mLastFadedColorState = false;
                        OplusNavigationHandle.access$updatePaintColor(oplusNavigationHandle2, oplusNavigationHandle2.mBrightLightColor, oplusNavigationHandle2.mBrightDarkColor);
                        this.this$0.postFadedColorTask();
                        return;
                    }
                    if (i != 3) {
                        return;
                    }
                    OplusNavigationHandle oplusNavigationHandle3 = this.this$0;
                    OplusNavigationHandle.Companion companion = OplusNavigationHandle.Companion;
                    oplusNavigationHandle3.postNextTestBrightColorTask();
                }
            }
        };
    }

    public static final void access$updatePaintColor(OplusNavigationHandle oplusNavigationHandle, int i, int i2) {
        int iIntValue;
        if (oplusNavigationHandle.isSplitScreenSetColor() || !oplusNavigationHandle.isShown() || oplusNavigationHandle.oplusHandleAnimatorController.isPressAnimatorRunning() || oplusNavigationHandle.mPaint.getColor() == (iIntValue = ((Integer) ArgbEvaluator.getInstance().evaluate(oplusNavigationHandle.mLastIntensity, Integer.valueOf(i), Integer.valueOf(i2))).intValue())) {
            return;
        }
        oplusNavigationHandle.fadedAnimationColorFrom = oplusNavigationHandle.mPaint.getColor();
        oplusNavigationHandle.fadedAnimationColorTo = iIntValue;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            float f = oplusNavigationHandle.mLastIntensity;
            String strColorIntToHex = oplusNavigationHandle.colorIntToHex(oplusNavigationHandle.fadedAnimationColorFrom);
            String strColorIntToHex2 = oplusNavigationHandle.colorIntToHex(oplusNavigationHandle.fadedAnimationColorTo);
            StringBuilder sb = new StringBuilder("updatePaintColor intensity = ");
            sb.append(f);
            sb.append("  fadedAnimationColorFrom = ");
            sb.append(strColorIntToHex);
            sb.append(" fadedAnimationColorTo = ");
            MediaTimeoutListener$PlaybackStateListener$$ExternalSyntheticOutline0.m(strColorIntToHex2, "OplusNavigationHandle-->", "SystemUi--NavBar", sb);
        }
        if (!oplusNavigationHandle.mTestDisableFadedAnim) {
            oplusNavigationHandle.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.StartFadedAnim, 1.0f);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OplusNavigationHandle-->updatePaintColor disable faded anim, set color directly.");
        }
        oplusNavigationHandle.mPaint.setColor(iIntValue);
        oplusNavigationHandle.invalidateView();
    }

    private final int getGestureViewLeftScreenWidth() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        return iArr[0];
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void abortCurrentGesture() {
    }

    public final String colorIntToHex(int i) {
        return String.format("#%08X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
    }

    public final float getCurrentBurnInMove() {
        return this.mCurrentBurnInMove;
    }

    public final float getFadedAnimProgress() {
        return this.fadedAnimProgress;
    }

    public final int getGestureWidthRes(String str) {
        Resources resources = ((View) this).mContext.getResources();
        int identifier = resources.getIdentifier(str, "dimen", "oplus");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public final float getLongPressAnimProgress() {
        return this.longPressAnimProgress;
    }

    public final void handleValidTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.isMultitouch = false;
            this.isCalledLongPress = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                NavBarFeatureOption.INSTANCE.getClass();
                OplusLockIconView$$ExternalSyntheticOutline0.m("handleValidTouchEvent, isSupportNewAnim() ", "OplusNavigationHandle-->", "SystemUi--NavBar", !NavBarFeatureOption.sIsSupportTaskbar);
            }
        }
        if (this.isMultitouch) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OplusNavigationHandle-->handleValidTouchEvent isMultitouch");
                return;
            }
            return;
        }
        NavBarFeatureOption navBarFeatureOption = NavBarFeatureOption.INSTANCE;
        navBarFeatureOption.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            NavigationGestureDetector navigationGestureDetector = this.mGestureDetectorOld;
            if (navigationGestureDetector != null) {
                navigationGestureDetector.onTouchEvent(motionEvent);
            }
        } else {
            NavigationGestureDetector navigationGestureDetector2 = this.mGestureDetector;
            if (navigationGestureDetector2 != null) {
                navigationGestureDetector2.onTouchEvent(motionEvent);
            }
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            int i = this.viewScreenLeft;
            this.inGestureXRange = ((float) i) <= x && x <= ((float) (getWidth() + i));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", VFXController$stopAnim$1$$ExternalSyntheticOutline0.m("inGestureXRange ", this.viewScreenLeft, " ,viewLeft = ", "OplusNavigationHandle-->", this.inGestureXRange));
                return;
            }
            return;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0.m(motionEvent.getActionMasked(), "isUpOrCancel:", "OplusNavigationHandle-->", "SystemUi--NavBar");
            }
            if (shouldHandlePress() && !this.isCalledLongPress) {
                GestureHomeHandleEventController.INSTANCE.onLongPressCancel();
            }
            resetAnimator();
            return;
        }
        if (actionMasked != 5) {
            return;
        }
        navBarFeatureOption.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            return;
        }
        if (shouldHandlePress() && !this.isCalledLongPress) {
            GestureHomeHandleEventController.INSTANCE.onLongPressCancel();
        }
        this.isMultitouch = true;
        resetAnimator();
    }

    public final void invalidateHandle(float f) {
        this.mPaint.setColor(ColorUtils.blendARGB(this.animationColorFrom, this.animationColorTo, f));
        setScaleX(1.0f - (f * 0.2f));
        invalidateView();
    }

    public final void invalidateView() {
        if (getVisibility() != 0 || getAlpha() <= 0.0f) {
            this.mRequiresInvalidate = true;
        } else {
            invalidate();
        }
    }

    public final boolean isSplitScreenSetColor() {
        int rotation = ((View) this).mContext.getDisplay().getRotation();
        boolean z = rotation == 1 || rotation == 3;
        FeatureOption.INSTANCE.getClass();
        return this.isInSplitScreenMode && NavBarUtils.isGestureSideMode() && (z || (FeatureOption.isFoldDevice() && SystemFoldModeObserver.Companion.getInstance().isFoldOpen() && !FeatureOption.isFoldRemapDisabled()));
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mBurnInEnable) {
            postNextTestBrightColorTask();
            ScreenBurnInProtector.Companion companion = ScreenBurnInProtector.Companion;
            if (!companion.getInstance().hasCallback(this)) {
                companion.getInstance().addCallback(this);
            }
            OplusAppSwitchManager.getInstance().registerAppSwitchObserver(getContext(), this.appSwitchObserver, (OplusAppSwitchConfig) null);
        }
    }

    @Override // com.oplusos.systemui.statusbar.controller.ScreenBurnInProtector.OnBurnInPreventCallback
    public final void onBurnInPreventTrigger(float f) {
        if (!isShown() || this.oplusHandleAnimatorController.isHandleAnimatorRunning()) {
            return;
        }
        if (getTranslationY() != this.mCurrentBurnInMove) {
            boolean z = this.mBurnInMovingUp;
            float translationY = getTranslationY();
            float f2 = this.mCurrentBurnInMove;
            StringBuilder sbM = NotificationPanelViewController$$ExternalSyntheticOutline0.m("updateBurnInPosition reset current burn move distance, moving up ", z, " translationY = ", translationY, " , mCurrentBurnInMove = ");
            sbM.append(f2);
            LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, sbM.toString(), null, 8);
            this.mCurrentBurnInMove = getTranslationY();
        }
        if (Math.abs(this.mCurrentBurnInMove) >= this.mBurnInMoveDistance) {
            this.mBurnInMovingUp = !this.mBurnInMovingUp;
        }
        this.mCurrentBurnInMove += this.mBurnInMovingUp ? -1.0f : 1.0f;
        StringBuilder sbM2 = MonoSpline$$ExternalSyntheticOutline0.m("onBurnInPreventTrigger currentTransitionY = ", getTranslationY(), "  mCurrentBurnInMove = ", this.mCurrentBurnInMove, " view = ");
        sbM2.append(this);
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, sbM2.toString(), null, 8);
        setTranslationY(this.mCurrentBurnInMove);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mBurnInEnable) {
            ScreenBurnInProtector.Companion companion = ScreenBurnInProtector.Companion;
            if (companion.getInstance().hasCallback(this)) {
                this.mCurrentBurnInMove = 0.0f;
                companion.getInstance().removeCallback(this);
            }
            OplusAppSwitchManager.getInstance().unregisterAppSwitchObserver(getContext(), this.appSwitchObserver);
            removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        float f = this.mHeight + (this.mAdditionalHeightForAnimation * this.longPressAnimProgress);
        float f2 = height - this.mHandleBottom;
        float f3 = f2 - f;
        float width = getWidth();
        float f4 = (this.mRadius * f) / this.mHeight;
        canvas.clipRect(0.0f, f3, width, f2);
        canvas.drawRoundRect(0.0f, f3, width, f2, f4, f4, this.mPaint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.viewScreenLeft = getGestureViewLeftScreenWidth();
    }

    public final void postBrightColorTask() {
        if (this.mBurnInEnable) {
            removeMessages(2);
            sendEmptyMessage(2);
        }
    }

    public final void postFadedColorTask() {
        if (this.mBurnInEnable) {
            removeMessages(1);
            sendEmptyMessageDelayed(1, 2000L);
        }
    }

    public final void postNextTestBrightColorTask() {
        if (this.mTestStartFadedAnim && isShown()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OplusNavigationHandle-->postNextTestBrightColorTask start");
            }
            postBrightColorTask();
            removeMessages(3);
            sendEmptyMessageDelayed(3, this.mTestStartFadedAnimDuration);
        }
    }

    public final void resetAnimator() {
        NavBarFeatureOption.INSTANCE.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            this.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.ResetTransitionAnim);
            this.oplusHandleAnimatorController.doHandleOldAnimator(OplusHandleOldAnimType.EndOldLongPressAnim);
            return;
        }
        this.isInScroll = false;
        this.isInScrollY = false;
        this.isInScrollX = false;
        OplusHandleAnimatorController oplusHandleAnimatorController = this.oplusHandleAnimatorController;
        oplusHandleAnimatorController.followHandInterruptLongPress = false;
        oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndLongPressAnim, 0.0f);
        this.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndFollowHandAnim, this.mCurrentBurnInMove);
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        if (f <= 0.0f || !this.mRequiresInvalidate) {
            return;
        }
        this.mRequiresInvalidate = false;
        invalidate();
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public void setDarkIntensity(float f) {
        int i;
        int i2;
        int iIntValue;
        this.mLastIntensity = f;
        boolean z = this.mBurnInEnable;
        if (z) {
            i = this.mLastFadedColorState ? this.mFadedDarkColor : this.mBrightDarkColor;
        } else {
            i = this.mDarkColor;
        }
        if (z) {
            i2 = this.mLastFadedColorState ? this.mFadedLightColor : this.mBrightLightColor;
        } else {
            i2 = this.mLightColor;
        }
        if (isSplitScreenSetColor()) {
            iIntValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mSplitScreenColor), Integer.valueOf(this.mSplitScreenColor))).intValue();
        } else if (this.isSplitScreenChange) {
            this.isSplitScreenChange = false;
            ArgbEvaluator argbEvaluator = ArgbEvaluator.getInstance();
            float f2 = this.mIntensityTransition;
            Integer numValueOf = Integer.valueOf(this.mSplitScreenColor);
            if (f <= 0.5f) {
                i = i2;
            }
            iIntValue = ((Integer) argbEvaluator.evaluate(f2, numValueOf, Integer.valueOf(i))).intValue();
        } else {
            iIntValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(i2), Integer.valueOf(i))).intValue();
        }
        this.paintColor = iIntValue;
        if (this.mPaint.getColor() == iIntValue || this.oplusHandleAnimatorController.isLongPressAnimRunning() || this.oplusHandleAnimatorController.isOldLongPressAnimRunning()) {
            return;
        }
        if (this.oplusHandleAnimatorController.isFadedAnimatorRunning()) {
            this.oplusHandleAnimatorController.doHandleAnimator(OplusHandleAnimType.EndFadedAnim, 0.0f);
        }
        this.mPaint.setColor(iIntValue);
        this.animationColorFrom = iIntValue;
        invalidateView();
        removeMessages(1);
        if (this.mLastFadedColorState) {
            return;
        }
        postFadedColorTask();
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public void setDelayTouchFeedback(boolean z) {
    }

    public final void setFadedAnimProgress(float f) {
        this.fadedAnimProgress = f;
        if (f >= 0.0f) {
            this.mPaint.setColor(ColorUtils.blendARGB(this.fadedAnimationColorFrom, this.fadedAnimationColorTo, f));
            this.animationColorFrom = this.mPaint.getColor();
        }
        invalidateView();
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public void setImageDrawable(Drawable drawable) {
    }

    public final void setInSplitScreenMode(boolean z) {
        this.isInSplitScreenMode = z;
    }

    public final void setKeyguardState(boolean z) {
        this.isKeyguardShowing = z;
    }

    public final void setLongPressAnimProgress(float f) {
        this.longPressAnimProgress = f;
        if (f >= 0.0f) {
            this.mPaint.setColor(ColorUtils.blendARGB(this.animationColorFrom, this.animationColorTo, f));
        }
        setScaleX((this.longPressAnimProgress * 0.2f) + 1.0f);
        invalidateView();
    }

    public final void setSplitScreenChange(boolean z) {
        this.isSplitScreenChange = z;
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public void setVertical(boolean z) {
        this.mPortraitWidth = getGestureWidthRes(NAVIGATION_GESTURE_VIEW_WIDTH);
        this.mLandscapeWidth = getGestureWidthRes(NAVIGATION_GESTURE_VIEW_LANDSCAPE_WIDTH);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", OplusKeyguardSimInputViewController$$ExternalSyntheticOutline0.m("mPortraitWidth = ", this.mPortraitWidth, this.mLandscapeWidth, "mLandscapeWidth = ", "OplusNavigationHandle-->"));
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.width = z ? this.mPortraitWidth : this.mLandscapeWidth;
        setLayoutParams(layoutParams);
    }

    public final boolean shouldHandlePress() {
        if (this.isKeyguardShowing) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                return false;
            }
        }
        if (!this.inGestureXRange) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return true;
        }
        NavBarFeatureOption.INSTANCE.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar && SwipeSideGestureHandleCuiObserver.INSTANCE.getWakeHandleCuiEnable()) {
            return true;
        }
        if (!NavBarFeatureOption.sIsSupportTaskbar && OplusNavBarUtils.isEnableOcrScreen() && !this.isKeyguardShowing) {
            return true;
        }
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            return false;
        }
        OcrScreenCompatVersion.INSTANCE.getClass();
        return !OcrScreenCompatVersion.isMetaDataSupport && SwipeSideGestureHandleCuiObserver.INSTANCE.getWakeHandleCuiEnable();
    }

    public final void updatePaintColor() {
        int color = this.mPaint.getColor();
        int i = this.paintColor;
        if (color != i) {
            this.mPaint.setColor(i);
            this.animationColorFrom = this.paintColor;
            invalidateView();
        }
        postFadedColorTask();
    }

    public OplusNavigationHandle(Context context) {
        this(context, null);
    }
}
