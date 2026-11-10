package com.oplus.transition;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.SurfaceControl;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.wm.shell.R;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.transition.AdaptiveSmoothShellAnimManager;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.oplus.content.OplusFeatureConfigManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusRotationAnimationController {
    private static final String LAUNCHER_PKG = "com.android.launcher";
    private ShellExecutor mAnimExecutor;
    private Context mContext;
    private int mCurrentScreenRotationDelta = 0;
    private boolean mFromUiModeChange = false;
    private boolean mIsNightMode;
    private ShellExecutor mMainExecutor;
    private boolean mUseDarkModeLoading;

    public OplusRotationAnimationController(Context context, ShellExecutor shellExecutor, ShellExecutor shellExecutor2) {
        this.mUseDarkModeLoading = false;
        this.mContext = context;
        this.mUseDarkModeLoading = OplusFeatureConfigManager.getInstance().hasFeature("oplus.dark_mode.switch.loading");
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
    }

    private Animation[] getOplusRotateAnimations(int i) {
        Animation animationLoadAnimation;
        Animation animationLoadAnimation2;
        Animation animation;
        Animation animation2;
        AnimationSet[] animationSetArr = new AnimationSet[2];
        if (i == 0) {
            if (this.mUseDarkModeLoading || !this.mFromUiModeChange) {
                animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_0_exit);
            } else {
                boolean zLoadLightScreenRotationAnimations = AdaptiveSmoothShellAnimManager.getInstance().loadLightScreenRotationAnimations();
                Context context = this.mContext;
                animationLoadAnimation = zLoadLightScreenRotationAnimations ? AnimationUtils.loadAnimation(context, R.anim.oplus_darkmode_exit_low) : AnimationUtils.loadAnimation(context, R.anim.oplus_darkmode_exit);
                this.mFromUiModeChange = false;
            }
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_0_enter);
            animationSetArr[0] = (AnimationSet) animationLoadAnimation;
            animationSetArr[1] = (AnimationSet) animationLoadAnimation2;
        } else if (i == 1) {
            animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_plus_90_exit);
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_plus_90_enter);
        } else {
            if (i != 2) {
                if (i != 3) {
                    animation2 = null;
                    animation = null;
                } else {
                    animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_minus_90_exit);
                    animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_minus_90_enter);
                }
                animationSetArr[0] = (AnimationSet) animation2;
                animationSetArr[1] = (AnimationSet) animation;
                return animationSetArr;
            }
            animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_180_exit);
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_screen_rotate_180_enter);
        }
        Animation animation3 = animationLoadAnimation;
        animation = animationLoadAnimation2;
        animation2 = animation3;
        animationSetArr[0] = (AnimationSet) animation2;
        animationSetArr[1] = (AnimationSet) animation;
        return animationSetArr;
    }

    public Animation[] getDefaultRotationAnimations(int i, int i2, int i3, int i4, int i5) {
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getScreenRotationAnimations ==> delta =:", i, i2, ",originWidth =:", ",originHeight =:");
        RectList$.ExternalSyntheticOutline0.m(i3, i4, ",finalWidth =:", ",finalHeight =:", sbM);
        sbM.append(i5);
        TransitionLog.debug(sbM.toString());
        Animation[] oplusRotateAnimations = getOplusRotateAnimations(i);
        this.mCurrentScreenRotationDelta = i;
        if (oplusRotateAnimations != null) {
            Animation animation = oplusRotateAnimations[0];
            boolean z = OplusShellLightOSTransition.IS_LIGHTOS;
            TransitionAnimationUtil.setAnimationHasRoundedCorners(animation, !z);
            TransitionAnimationUtil.setAnimationHasRoundedCorners(oplusRotateAnimations[1], true ^ z);
        }
        return oplusRotateAnimations;
    }

    public Animation[] getLightScreenRotationAnimations(int i, int i2, int i3, int i4, int i5) {
        Animation animationLoadAnimation;
        Animation animationLoadAnimation2;
        Animation animation;
        Animation animation2;
        TransitionLog.debug("getLightScreenRotationAnimations");
        Animation[] animationArr = new Animation[2];
        if (i == 0) {
            if (this.mUseDarkModeLoading || !this.mFromUiModeChange) {
                animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_0_exit);
            } else {
                animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_darkmode_exit);
                this.mFromUiModeChange = false;
            }
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_0_enter);
        } else if (i == 1) {
            animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_plus_90_exit);
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_plus_90_enter);
        } else {
            if (i != 2) {
                if (i != 3) {
                    animation2 = null;
                    animation = null;
                } else {
                    animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_minus_90_exit);
                    animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_minus_90_enter);
                }
                animationArr[0] = animation2;
                animationArr[1] = animation;
                return animationArr;
            }
            animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_180_exit);
            animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mContext, R.anim.oplus_light_screen_rotate_180_enter);
        }
        Animation animation3 = animationLoadAnimation;
        animation = animationLoadAnimation2;
        animation2 = animation3;
        animationArr[0] = animation2;
        animationArr[1] = animation;
        return animationArr;
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z = (configuration.uiMode & 48) == 32;
        if (this.mIsNightMode != z) {
            this.mIsNightMode = z;
            this.mFromUiModeChange = true;
        }
    }

    public boolean startScreenRotateBackColorAnimation(float[] fArr, Animation animation, SurfaceControl surfaceControl, TransactionPool transactionPool) {
        int i;
        StringBuilder sb = new StringBuilder("startScreenRotateBackColorAnimation ==> leash =:");
        sb.append(surfaceControl);
        sb.append(",mCurrentScreenRotationDelta =:");
        sb.append(this.mCurrentScreenRotationDelta);
        sb.append(",backColor =:0x");
        boolean z = false;
        sb.append(Integer.toHexString(Color.rgb(fArr[0], fArr[1], fArr[2])));
        TransitionLog.debug(sb.toString());
        if (!surfaceControl.isValid() || OplusShellLightOSTransition.IS_LIGHTOS) {
            return false;
        }
        if (fArr[0] >= 0.1f && ((i = this.mCurrentScreenRotationDelta) == 1 || i == 3)) {
            z = true;
        }
        SurfaceControl.Transaction transactionAcquire = transactionPool.acquire();
        if (z) {
            transactionAcquire.setAlpha(surfaceControl, 1.0f).setColor(surfaceControl, fArr).apply();
        } else {
            transactionAcquire.setColor(surfaceControl, fArr).apply();
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            int integer = this.mContext.getResources().getInteger(R.integer.oplus_screen_rotation_total_duration);
            Interpolator interpolatorLoadInterpolator = AnimationUtils.loadInterpolator(this.mContext, R.interpolator.oplus_screen_rotation_alpha_interpolator);
            alphaAnimation.setDuration(integer);
            alphaAnimation.setInterpolator(interpolatorLoadInterpolator);
        }
        return true;
    }
}
