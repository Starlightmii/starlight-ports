package com.oplus.systemui.navigationbar.utils;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OplusScreenDragUtil;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureBarTypeObserver;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.navigationbar.EdgeBackGestureHandlerController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarUtils {
    public static final NavBarUtils INSTANCE = new NavBarUtils();
    private static final String TAG = "NavBarUtils";
    public static int gestureUiVisibleState = -1;
    public static volatile boolean isNavColorReversalEnable = false;
    public static boolean navBarWindowVisible = true;
    public static boolean screenPinningActive;
    public static boolean taskbarEnableState;

    public static final EdgeBackGestureHandler getEdgeBackController(Context context) {
        return EdgeBackGestureHandlerController.getEdgeBackController(context.getDisplayId());
    }

    public static final int getNavBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", OplusAnimationController.PACKAGE_SYSTEM);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static final int getNavState() {
        return NavStateObserver.INSTANCE.getNavState();
    }

    public static final int getNavType() {
        int navState = getNavState();
        if (navState == 0 || navState == 1) {
            return 0;
        }
        if (navState != 2) {
            return navState != 3 ? -1 : 2;
        }
        return 1;
    }

    public static final int getNavigationBarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(DeviceUtil.isLandscape(context) ? R.dimen.notification_2025_header_height : R.dimen.notification_2025_expand_button_right_icon_spacing);
    }

    public static final boolean getScreenPinningActive() {
        return screenPinningActive;
    }

    public static /* synthetic */ void getScreenPinningActive$annotations() {
    }

    public static final boolean getTaskbarStatus() {
        return taskbarEnableState;
    }

    public static final boolean isAssistantAvailable(Context context, int i, int i2) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() && !CustomizeFeatureOption.sIsSupportCircleToSearch && QuickStepContract.isGesturalMode(i)) {
            ComponentName assistComponentForUser = ((AssistManager) Dependency.sDependency.getDependencyInner(AssistManager.class)).mAssistUtils.getAssistComponentForUser(-2);
            if (assistComponentForUser != null && !"com.heytap.speechassist".equals(assistComponentForUser.getPackageName())) {
                if (Settings.Secure.getIntForUser(context.getContentResolver(), "assist_touch_gesture_enabled", context.getResources().getBoolean(R.bool.config_audio_ringer_mode_affects_alarm_stream) ? 1 : 0, i2) != 0) {
                    return true;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    Log.d("SystemUi--NavBar", "NavBarUtils-->assist touch gesture disabled");
                    return false;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "NavBarUtils-->".concat("component unavailable ,componentName = " + assistComponentForUser));
            }
        }
        return false;
    }

    public static final boolean isGestureSideMode() {
        return getNavState() == 3;
    }

    public static final boolean isGestureUpMode() {
        return getNavState() == 2;
    }

    public static final boolean isLongPressHomeEnabled(Context context, int i) {
        return Settings.Secure.getIntForUser(context.getContentResolver(), NavBarSettingsValueProxy.KEY_ASSIST_LONG_PRESS_HOME_ENABLED, context.getResources().getBoolean(R.bool.config_attachNavBarToAppDuringTransition) ? 1 : 0, i) != 0;
    }

    public static final boolean isSideGestureBarHide() {
        return isGestureSideMode() && SwipeSideGestureBarTypeObserver.INSTANCE.getSwipeSideGestureBarType() == 1;
    }

    public static final boolean isSubDisplayId(Context context, int i) {
        ConfigurationOnSubDisplay.Companion.getInstance(context).getClass();
        return ConfigurationOnSubDisplay.isSubDisplayId(i);
    }

    public static final boolean isSupportHotZoneAvoid(Context context) {
        FeatureOption.INSTANCE.getClass();
        boolean z = FeatureOption.isSupportPanoramaWorkStation() && OplusNavBarUtils.isFoldOpenNavBar(context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(" isSupportHotZoneAvoid= ", "NavBarUtils-->", "SystemUi--NavBar", z);
        }
        return z;
    }

    public static final boolean isVirtualKeyMode() {
        int navState = getNavState();
        return navState == 0 || navState == 1;
    }

    public static final MotionEvent offsetEvent(MotionEvent motionEvent) {
        if (!OplusScreenDragUtil.isOffsetState()) {
            return motionEvent;
        }
        float scale = OplusScreenDragUtil.getScale();
        int offsetX = OplusScreenDragUtil.getOffsetX();
        int offsetY = OplusScreenDragUtil.getOffsetY();
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        int metaState = motionEvent.getMetaState();
        int buttonState = motionEvent.getButtonState();
        float xPrecision = motionEvent.getXPrecision();
        float yPrecision = motionEvent.getYPrecision();
        int deviceId = motionEvent.getDeviceId();
        int edgeFlags = motionEvent.getEdgeFlags();
        int source = motionEvent.getSource();
        int flags = motionEvent.getFlags();
        int i = 0;
        while (i < pointerCount) {
            pointerPropertiesArr[i] = new MotionEvent.PointerProperties();
            pointerCoordsArr[i] = new MotionEvent.PointerCoords();
            motionEvent.getPointerProperties(i, pointerPropertiesArr[i]);
            motionEvent.getPointerCoords(i, pointerCoordsArr[i]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i];
            pointerCoords.getClass();
            pointerCoords.x = (motionEvent.getX(i) - offsetX) / scale;
            MotionEvent.PointerCoords pointerCoords2 = pointerCoordsArr[i];
            pointerCoords2.getClass();
            pointerCoords2.y = (motionEvent.getY(i) - offsetY) / scale;
            i++;
            eventTime = eventTime;
        }
        long j = eventTime;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(offsetY, "NavBarUtils-->", "SystemUi--NavBar", KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(scale, offsetX, "offsetEvent scale:", " offsetX:", " offsetY:"));
        }
        motionEvent.recycle();
        return MotionEvent.obtain(downTime, j, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags, source, flags);
    }

    public static final MotionEvent resetEvent(MotionEvent motionEvent) {
        if (!OplusScreenDragUtil.isOffsetState()) {
            return motionEvent;
        }
        float scale = OplusScreenDragUtil.getScale();
        int offsetX = OplusScreenDragUtil.getOffsetX();
        int offsetY = OplusScreenDragUtil.getOffsetY();
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        int metaState = motionEvent.getMetaState();
        int buttonState = motionEvent.getButtonState();
        float xPrecision = motionEvent.getXPrecision();
        float yPrecision = motionEvent.getYPrecision();
        int deviceId = motionEvent.getDeviceId();
        int edgeFlags = motionEvent.getEdgeFlags();
        int source = motionEvent.getSource();
        int flags = motionEvent.getFlags();
        int i = 0;
        while (i < pointerCount) {
            pointerPropertiesArr[i] = new MotionEvent.PointerProperties();
            pointerCoordsArr[i] = new MotionEvent.PointerCoords();
            motionEvent.getPointerProperties(i, pointerPropertiesArr[i]);
            motionEvent.getPointerCoords(i, pointerCoordsArr[i]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i];
            pointerCoords.getClass();
            pointerCoords.x = (motionEvent.getX(i) * scale) + offsetX;
            MotionEvent.PointerCoords pointerCoords2 = pointerCoordsArr[i];
            pointerCoords2.getClass();
            pointerCoords2.y = (motionEvent.getY(i) * scale) + offsetY;
            i++;
            eventTime = eventTime;
        }
        long j = eventTime;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(offsetY, "NavBarUtils-->", "SystemUi--NavBar", KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(scale, offsetX, "offsetEvent scale:", " offsetX:", " offsetY:"));
        }
        motionEvent.recycle();
        return MotionEvent.obtain(downTime, j, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags, source, flags);
    }

    public static final void setNavBarWindowVisible(Context context, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setNavBarWindowVisible, visible = ", "NavBarUtils-->", "SystemUi--NavBar", z);
        }
        navBarWindowVisible = z;
        updateNavGestureVisibility(context);
    }

    public static final void setNavColorReversalEnable(boolean z) {
        isNavColorReversalEnable = z;
    }

    public static final void setScreenPinningActive(boolean z) {
        screenPinningActive = z;
    }

    public static final void setTaskbarStatus(Context context, boolean z) {
        taskbarEnableState = z;
        updateNavGestureVisibility(context);
    }

    public static final void updateNavGestureVisibility(Context context) {
        boolean z = getNavState() == 3;
        boolean z2 = SwipeSideGestureBarTypeObserver.INSTANCE.getSwipeSideGestureBarType() == 1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            boolean z3 = navBarWindowVisible;
            boolean z4 = taskbarEnableState;
            int i = gestureUiVisibleState;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("navGestureEnable = ", " , gestureUiHide = ", " , mNavBarWindowVisible = ", z, z2);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" , taskbarEnableState = ", ", gestureUiVisibleState = ", sbM, z3, z4);
            MobileInputLogger$.ExternalSyntheticOutline0.m(i, "NavBarUtils-->", "SystemUi--NavBar", sbM);
        }
        if (!z || z2 || !navBarWindowVisible || taskbarEnableState) {
            if (gestureUiVisibleState == 1) {
                return;
            }
            gestureUiVisibleState = 1;
            NavBarSettingsValueProxy.Companion.setSwipeSideGestureBarVisibilityType(context, 1);
            return;
        }
        if (gestureUiVisibleState == 0) {
            return;
        }
        gestureUiVisibleState = 0;
        NavBarSettingsValueProxy.Companion.setSwipeSideGestureBarVisibilityType(context, 0);
    }
}
