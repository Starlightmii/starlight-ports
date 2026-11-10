package com.oplus.systemui.navigationbar.gesture.proxy;

import android.content.Context;
import android.content.SharedPreferences;
import com.oplus.systemui.navigationbar.observer.NavGestureProcessObserver;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarDailyStatisticProxy extends AbsDailyStatisticProxy {
    @Override // com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy
    public void onStatistic(Context context) {
        int navState = NavBarSettingsValueProxy.getNavState(context);
        HashMap map = new HashMap();
        map.put("navigation_bar_type", String.valueOf(navState));
        if (navState == 0 || navState == 1) {
            int virtualKeyCombinationType = NavBarSettingsValueProxy.getVirtualKeyCombinationType(context);
            int virtualKeyLongPressHomeCuiEnable = NavBarSettingsValueProxy.getVirtualKeyLongPressHomeCuiEnable(context);
            map.put("navigation_bar_virtual_key_combination", String.valueOf(virtualKeyCombinationType));
            map.put("wake_cui_switch_state", String.valueOf(virtualKeyLongPressHomeCuiEnable));
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportNavBarButtonsPosition()) {
                map.put(NavBarSettingsValueProxy.KEY_NAV_BUTTONS_POSITION, String.valueOf(NavBarSettingsValueProxy.getNavButtonsPosition(context)));
            }
        } else if (navState == 2) {
            int swipeUpGestureBarType = NavBarSettingsValueProxy.getSwipeUpGestureBarType(context);
            int swipeUpGestureMistouchPreventionEnable = NavBarSettingsValueProxy.getSwipeUpGestureMistouchPreventionEnable(context);
            map.put("hide_swipe_up_gesture_bar_enable", String.valueOf(swipeUpGestureBarType));
            map.put("swipe_up_gesture_mistouch_prevention_enable", String.valueOf(swipeUpGestureMistouchPreventionEnable));
        } else if (navState == 3) {
            int swipeSideGestureMistouchPreventionType = NavBarSettingsValueProxy.getSwipeSideGestureMistouchPreventionType(context);
            int swipeSideGestureBarType = NavBarSettingsValueProxy.getSwipeSideGestureBarType(context);
            int swipeSideEnableBackVibrate = NavBarSettingsValueProxy.getSwipeSideEnableBackVibrate(context);
            int navbarHandlerCuiEnable = NavBarSettingsValueProxy.getNavbarHandlerCuiEnable(context);
            String navGestureProgressState = NavGestureProcessObserver.Companion.getInstance().getNavGestureProgressState();
            map.put("hide_swipe_side_gesture_bar_enable", String.valueOf(swipeSideGestureBarType));
            map.put("swipe_side_gesture_mistouch_prevention_enable", String.valueOf(swipeSideGestureMistouchPreventionType));
            map.put("gesture_swipe_back_trigger_width", navGestureProgressState);
            map.put(NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BACK_VIBRATE_ENABLE, String.valueOf(swipeSideEnableBackVibrate));
            map.put("wake_cui_switch_state", String.valueOf(navbarHandlerCuiEnable));
        }
        OplusTrackProxyUtil.onCommon(context, "20082002", "navigation_bar_setting", map);
        if (context == null) {
            LogUtil.i(LogUtil.TAG_KEYBOARD, "NavigationBarStatisticUtil", "context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("OplusKeyBoardShortcutsSp", 0);
        if (sharedPreferences == null) {
            LogUtil.i(LogUtil.TAG_KEYBOARD, "NavigationBarStatisticUtil", "sp is null");
            return;
        }
        int i = sharedPreferences.getInt("old", 0);
        int i2 = sharedPreferences.getInt(OplusAnimationController.PACKAGE_SYSTEM, 0);
        int i3 = sharedPreferences.getInt("win", 0);
        int i4 = sharedPreferences.getInt("mac", 0);
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            LogUtil.i(LogUtil.TAG_KEYBOARD, "NavigationBarStatisticUtil", "all is 0");
            return;
        }
        HashMap map2 = new HashMap();
        if (i != 0) {
            map2.put("old", String.valueOf(i));
        }
        if (i2 != 0) {
            map2.put(OplusAnimationController.PACKAGE_SYSTEM, String.valueOf(i2));
        }
        if (i3 != 0) {
            map2.put("win", String.valueOf(i3));
        }
        if (i4 != 0) {
            map2.put("mac", String.valueOf(i4));
        }
        HashMap map3 = new HashMap();
        FeatureOption.INSTANCE.getClass();
        map3.put("region", FeatureOption.isExpRegion() ? ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH : "1");
        map3.put("count", new JSONObject(map2).toString());
        LogUtil.d(LogUtil.TAG_KEYBOARD, "NavigationBarStatisticUtil", "point:" + map3);
        OplusTrackProxyUtil.onCommon(context, "20082", "keyboard_shorcuts_panel_open_num", map3);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (editorEdit == null) {
            LogUtil.i(LogUtil.TAG_KEYBOARD, "NavigationBarStatisticUtil", "editor null");
        } else {
            editorEdit.clear();
            editorEdit.apply();
        }
    }
}
