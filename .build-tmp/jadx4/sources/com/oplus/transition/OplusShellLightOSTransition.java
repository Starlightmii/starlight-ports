package com.oplus.transition;

import android.content.Context;
import android.content.res.Configuration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.window.TransitionInfo;
import com.android.wm.shell.R;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.google.android.collect.Sets;
import com.oplus.content.OplusFeatureConfigManager;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusShellLightOSTransition {
    private Context mContext;
    private boolean mFromUiModeChange = false;
    private boolean mIsNightMode;
    private boolean mUseDarkModeLoading;
    public static final boolean IS_LIGHTOS = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.windowanimaiton.light");
    public static final boolean OPLUS_FEATURE_WINDOW_ANIM_LOW_END_CONFIG = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.windowanimaiton.low.end.config");
    public static final Set<String> FIXED_LIGHTOS_ANIMATION_PACKAGE = Sets.newHashSet(new String[]{"com.tencent.mm"});

    public OplusShellLightOSTransition(Context context) {
        this.mUseDarkModeLoading = false;
        this.mContext = context;
        this.mUseDarkModeLoading = OplusFeatureConfigManager.getInstance().hasFeature("oplus.dark_mode.switch.loading");
        TransitionLog.always("isLightOS");
    }

    public Animation loadLightCompactWindowAnimation(TransitionInfo.AnimationOptions animationOptions, boolean z) {
        if (!TransitionAnimationUtil.isTabletDevice()) {
            return null;
        }
        String packageName = animationOptions != null ? animationOptions.getPackageName() : OplusAnimationController.PACKAGE_SYSTEM;
        if (TransitionAnimationUtil.isSystemApp(packageName) || !FIXED_LIGHTOS_ANIMATION_PACKAGE.contains(packageName)) {
            return null;
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, z ? R.anim.oplus_wallpaper_close_enter2 : R.anim.oplus_wallpaper_close_exit2);
        TransitionLog.always("loadLightCompactWindowAnimation");
        return animationLoadAnimation;
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z = (configuration.uiMode & 48) == 32;
        if (this.mIsNightMode != z) {
            this.mIsNightMode = z;
            this.mFromUiModeChange = true;
        }
    }
}
