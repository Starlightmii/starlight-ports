package com.oplus.systemui.navigationbar.positionview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.buttons.NearestTouchFrame;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.utils.NavBarScreenSizeHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PositionSwitchViewController implements NavigationBarTransitions.DarkIntensityListener, View.OnClickListener {
    public Context mContext;
    public boolean mIsAnimRunning;
    public boolean mIsDisableSwitchView;
    public PositionSwitchView mLeftSwitchView;
    public NearestTouchFrame mNavButtons;
    public PositionSwitchView mRightSwitchView;
    public static final Interpolator ANIMATION_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    public static final Interpolator ALPHA_ANIMATION_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);

    private int getScreenWidth() {
        int rotation = this.mContext.getDisplay().getRotation();
        Rect screenRect = NavBarScreenSizeHelper.getInstance(this.mContext).getScreenRect(this.mContext);
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(rotation, "getScreenWidth rotation:", "  ,width:");
        sbM.append(screenRect.width());
        sbM.append("  ,height:");
        sbM.append(screenRect.height());
        LogUtil.i(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", sbM.toString());
        if (rotation == 0 || rotation == 2) {
            return screenRect.width();
        }
        return (rotation == 1 || rotation == 3) ? screenRect.height() : screenRect.width();
    }

    public final Animator alphaAnimation(View view, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, z ? 1.0f : 0.0f, z ? 0.0f : 1.0f);
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("alphaAnimation(): hide = ", LogUtil.TAG_NAVBAR, "PositionSwitchViewController", z);
        objectAnimatorOfFloat.setInterpolator(ALPHA_ANIMATION_INTERPOLATOR);
        objectAnimatorOfFloat.setDuration(283L);
        objectAnimatorOfFloat.addListener(new 2(view, z));
        return objectAnimatorOfFloat;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (this.mIsAnimRunning) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "don't perform onClick for the animation is running");
            return;
        }
        if (id == 2131365528) {
            startAnimation(this.mLeftSwitchView, this.mRightSwitchView, this.mNavButtons, true);
            NavBarSettingsValueProxy.setNavButtonsSwitchDirect(view.getContext(), 0);
            Context context = view.getContext();
            HashMap map = new HashMap();
            map.put("buttons_position_switch_direction", String.valueOf(0));
            OplusTrackProxyUtil.onCommon(context, "20082002", "buttons_position_switch_event", map);
            return;
        }
        if (id == 2131365529) {
            startAnimation(this.mLeftSwitchView, this.mRightSwitchView, this.mNavButtons, false);
            NavBarSettingsValueProxy.setNavButtonsSwitchDirect(view.getContext(), 1);
            Context context2 = view.getContext();
            HashMap map2 = new HashMap();
            map2.put("buttons_position_switch_direction", String.valueOf(1));
            OplusTrackProxyUtil.onCommon(context2, "20082002", "buttons_position_switch_event", map2);
        }
    }

    public final void onDarkIntensity(float f) {
        LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "onDarkIntensity(): darkIntensity = " + f);
        this.mLeftSwitchView.updateDarkness(f);
        this.mRightSwitchView.updateDarkness(f);
    }

    public void setVisible(boolean z) {
        this.mLeftSwitchView.setVisibility(z ? 8 : 0);
        this.mRightSwitchView.setVisibility(z ? 0 : 8);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0076  */
    public final void startAnimation(View view, View view2, View view3, boolean z) {
        view.animate().cancel();
        view2.animate().cancel();
        view3.animate().cancel();
        Animator animatorAlphaAnimation = alphaAnimation(this.mLeftSwitchView, z);
        boolean z2 = !z;
        Animator animatorAlphaAnimation2 = alphaAnimation(this.mRightSwitchView, z2);
        boolean z3 = this.mNavButtons.getLayoutDirection() == 1;
        boolean z4 = ((FrameLayout.LayoutParams) this.mNavButtons.getLayoutParams()).gravity == 8388611;
        if (z3) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "translationXAnim: in RTL layout");
            z4 = !z4;
            z = z2;
        }
        int screenWidth = (getScreenWidth() - this.mNavButtons.getWidth()) - (this.mContext.getResources().getDimensionPixelSize(2131170460) * 2);
        NearestTouchFrame nearestTouchFrame = this.mNavButtons;
        float translationX = nearestTouchFrame.getTranslationX();
        if (z) {
            if (z4) {
                screenWidth = 0;
            } else {
                screenWidth = -screenWidth;
            }
        } else if (!z4) {
            screenWidth = 0;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(nearestTouchFrame, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, translationX, screenWidth);
        objectAnimatorOfFloat.setInterpolator(ANIMATION_INTERPOLATOR);
        objectAnimatorOfFloat.setDuration(350L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorAlphaAnimation, animatorAlphaAnimation2, objectAnimatorOfFloat);
        animatorSet.addListener(new 1(this));
        animatorSet.start();
    }
}
