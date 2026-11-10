package com.oplus.systemui.shade;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.oplus.systemui.keyguard.gradientmask.view.OplusKeyguardGradientMaskView;
import com.oplus.systemui.keyguard.ui.view.OplusKeyguardBottomAreaView;
import com.oplus.systemui.keyguard.view.CustomOplusKeyguardStyleClock;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveLockMaskView;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialEngineContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout;
import com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainer;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveBgContainer;
import com.oplus.systemui.separate.OplusPanelViewPager;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.keyguard.view.OplusKeyguardTipsView;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShadeViewProviderModuleExImpl {
    public static final Companion Companion = new Companion();
    public static final String KGD_BOTTOM_TIPS_HOST = "kgd_bottom_tips_host";
    private static final String TAG = "ShadeViewProviderModuleExImpl";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final PictorialMessageView provideGLPictorialMessageView(FrameLayout frameLayout, NotificationShadeWindowView notificationShadeWindowView, PictorialMediator pictorialMediator) {
            PictorialMessageView pictorialMessageViewCreatePictorialMessageView;
            PictorialBottomViewHelper pictorialBottomViewHelper;
            Context context = notificationShadeWindowView.getContext();
            PictorialFactory pictorialFactory = pictorialMediator.getPictorialFactory();
            if (pictorialFactory == null || (pictorialBottomViewHelper = pictorialFactory.getPictorialBottomViewHelper()) == null || (pictorialMessageViewCreatePictorialMessageView = pictorialBottomViewHelper.createPictorialMessageView(context)) == null) {
                context.getClass();
                pictorialMessageViewCreatePictorialMessageView = new createGonePictorialMessageView.1(context);
                pictorialMessageViewCreatePictorialMessageView.setVisibility(8);
                KgdLogUtil.w(ShadeViewProviderModuleExImpl.TAG, "PictorialFactory unavailable, GL tips will not show");
            }
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                layoutParams.gravity = 80;
                pictorialMessageViewCreatePictorialMessageView.setLayoutParams(layoutParams);
                pictorialMessageViewCreatePictorialMessageView.setVisibility(8);
                frameLayout.addView(pictorialMessageViewCreatePictorialMessageView, 0);
            }
            return pictorialMessageViewCreatePictorialMessageView;
        }

        public final FrameLayout provideKgdBottomTipsHostContainer(NotificationShadeWindowView notificationShadeWindowView) {
            return (FrameLayout) notificationShadeWindowView.findViewById(2131363556);
        }

        public final OplusPanelViewPager providePanelViewPager(NotificationShadeWindowView notificationShadeWindowView) {
            return (OplusPanelViewPager) notificationShadeWindowView.requireViewById(2131364479);
        }

        public final OplusImmersiveBgContainer providerImmersiveBgContainer(NotificationPanelView notificationPanelView) {
            OplusImmersiveBgContainer oplusImmersiveBgContainer = (OplusImmersiveBgContainer) notificationPanelView.findViewById(2131364293);
            if (oplusImmersiveBgContainer != null) {
                return oplusImmersiveBgContainer;
            }
            return new OplusImmersiveBgContainer(notificationPanelView.getContext(), null, 0, 6, null);
        }

        public final OplusKeyguardBottomAreaView providerKeyguardBottomAreaView(NotificationPanelView notificationPanelView) {
            OplusKeyguardBottomAreaView oplusKeyguardBottomAreaViewFindViewById = notificationPanelView.findViewById(2131363555);
            return oplusKeyguardBottomAreaViewFindViewById == null ? new OplusKeyguardBottomAreaView(notificationPanelView.getContext(), (AttributeSet) null, 0, 0, 14, (DefaultConstructorMarker) null) : oplusKeyguardBottomAreaViewFindViewById;
        }

        public final KeyguardEngineContainer providerKeyguardEngineContainer(NotificationPanelView notificationPanelView) {
            KeyguardEngineContainer keyguardEngineContainer = (KeyguardEngineContainer) notificationPanelView.findViewById(2131363591);
            return keyguardEngineContainer == null ? new KeyguardEngineContainer(notificationPanelView.getContext()) : keyguardEngineContainer;
        }

        public final PictorialEngineContainer providerKeyguardPictorialContainer(NotificationShadeWindowView notificationShadeWindowView) {
            PictorialEngineContainer pictorialEngineContainer = new PictorialEngineContainer(notificationShadeWindowView.getContext());
            pictorialEngineContainer.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            return pictorialEngineContainer;
        }

        public final KeyguardSliderLayout providerKeyguardSliderLayout(NotificationPanelView notificationPanelView) {
            return (KeyguardSliderLayout) notificationPanelView.findViewById(2131363604);
        }

        public final CustomOplusKeyguardStyleClock providerKeyguardStyleClock(NotificationPanelView notificationPanelView) {
            CustomOplusKeyguardStyleClock customOplusKeyguardStyleClockFindViewById = notificationPanelView.findViewById(2131363607);
            return customOplusKeyguardStyleClockFindViewById == null ? new CustomOplusKeyguardStyleClock(notificationPanelView.getContext(), (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null) : customOplusKeyguardStyleClockFindViewById;
        }

        public final OplusKeyguardTipsView providerKeyguardTipsView(FrameLayout frameLayout, NotificationShadeWindowView notificationShadeWindowView) {
            OplusKeyguardTipsView oplusKeyguardTipsView = (OplusKeyguardTipsView) LayoutInflater.from(notificationShadeWindowView.getContext().getApplicationContext()).inflate(2131558941, (ViewGroup) frameLayout, false);
            oplusKeyguardTipsView.setVisibility(8);
            frameLayout.addView(oplusKeyguardTipsView);
            return oplusKeyguardTipsView;
        }

        public final OplusKeyguardGradientMaskView providerKgdCapsuleMaskView(NotificationShadeWindowView notificationShadeWindowView) {
            OplusKeyguardGradientMaskView oplusKeyguardGradientMaskViewFindViewById = notificationShadeWindowView.findViewById(2131364320);
            return oplusKeyguardGradientMaskViewFindViewById == null ? new OplusKeyguardGradientMaskView(notificationShadeWindowView.getContext(), (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null) : oplusKeyguardGradientMaskViewFindViewById;
        }

        public final OplusKeyguardGradientMaskView providerKgdImmersedMaskView(NotificationShadeWindowView notificationShadeWindowView) {
            OplusKeyguardGradientMaskView oplusKeyguardGradientMaskViewFindViewById = notificationShadeWindowView.findViewById(2131364321);
            return oplusKeyguardGradientMaskViewFindViewById == null ? new OplusKeyguardGradientMaskView(notificationShadeWindowView.getContext(), (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null) : oplusKeyguardGradientMaskViewFindViewById;
        }

        public final OplusKgdImmersiveLockMaskView providerKgdImmersiveLockMaskView(NotificationShadeWindowView notificationShadeWindowView) {
            OplusKgdImmersiveLockMaskView oplusKgdImmersiveLockMaskViewFindViewById = notificationShadeWindowView.findViewById(2131363436);
            return oplusKgdImmersiveLockMaskViewFindViewById == null ? new OplusKgdImmersiveLockMaskView(notificationShadeWindowView.getContext(), (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null) : oplusKgdImmersiveLockMaskViewFindViewById;
        }

        public final OplusKgdImmersiveMaskView providerKgdImmersiveMaskView(NotificationShadeWindowView notificationShadeWindowView) {
            return new OplusKgdImmersiveMaskView(notificationShadeWindowView.getContext(), (AttributeSet) null, 6, 0);
        }

        public final View providesShadeHeaderView(NotificationShadeWindowView notificationShadeWindowView) {
            ViewStub viewStub = (ViewStub) notificationShadeWindowView.requireViewById(2131364770);
            viewStub.setLayoutResource(2131558983);
            return viewStub.inflate();
        }
    }
}
