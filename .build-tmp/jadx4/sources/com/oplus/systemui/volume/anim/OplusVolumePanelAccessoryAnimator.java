package com.oplus.systemui.volume.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import com.coui.appcompat.animation.COUIInEaseInterpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import com.oplus.systemui.volume.utils.material.OplusVolumeAutoBlurAnimHelper;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumePanelAccessoryAnimator {
    public static final Companion Companion = new Companion();
    public int accessoryFadeGeneration;
    private final Function0 backgroundAnimationAnchorProvider;
    private final Function0 backgroundDrawableProvider;
    private ObjectAnimator bgAlphaAnimator;
    private ValueAnimator blurAnimator;
    private final Context context;
    private final Function0 isDefaultVolumeThemeProvider;
    private final Function0 isSystemPanelProvider;
    private Function1 onPanelBackgroundProgress;
    public int onceStaticWaitGeneration;
    private TextView panelTipsText;
    private ImageView panelTitleIcon;
    private TextView panelTitleText;
    private Runnable pendingOnceStaticReady;
    private View settingsButton;
    private final Function0 titleContextProvider;
    private View volumeSettingText;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusVolumePanelAccessoryAnimator(Context context, Function0 function0, Function0 function1, Function0 function2, Function0 function3, Function0 function4) {
        this.context = context;
        this.backgroundDrawableProvider = function0;
        this.backgroundAnimationAnchorProvider = function1;
        this.isDefaultVolumeThemeProvider = function2;
        this.isSystemPanelProvider = function3;
        this.titleContextProvider = function4;
    }

    public static void animateSettingsEntry$default(OplusVolumePanelAccessoryAnimator oplusVolumePanelAccessoryAnimator, View view, COUISpringInterpolator cOUISpringInterpolator, int i) {
        if ((i & 8) != 0) {
            cOUISpringInterpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_PANEL_EXPAND_INTERPOLATOR();
        }
        COUISpringInterpolator cOUISpringInterpolator2 = cOUISpringInterpolator;
        oplusVolumePanelAccessoryAnimator.getClass();
        if (view == null) {
            return;
        }
        VolumePropertyAnimationUtil.viewPropertyAnimation(view, (322 & 2) != 0 ? 0L : 150L, 350L, cOUISpringInterpolator2, new VolumePropertyAnimationUtil.PropertyAnimationParams(0.8f, 0.0f), new VolumePropertyAnimationUtil.PropertyAnimationParams(1.0f, 1.0f), null, (322 & 128) != 0 ? null : null, (322 & 256) != 0 ? null : null);
    }

    public final void cancelPendingOnceStaticReady() {
        View view;
        this.onceStaticWaitGeneration++;
        Runnable runnable = this.pendingOnceStaticReady;
        if (runnable != null && (view = (View) this.backgroundAnimationAnchorProvider.invoke()) != null) {
            view.removeCallbacks(runnable);
        }
        this.pendingOnceStaticReady = null;
    }

    public final void collapseForDismiss(PanelTransitionSession panelTransitionSession, Function0 function0) {
        int iCoerceIn;
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        ViewPropertyAnimator viewPropertyAnimatorAnimate2;
        ViewPropertyAnimator viewPropertyAnimatorAnimate3;
        ViewPropertyAnimator viewPropertyAnimatorAnimate4;
        boolean zIsNormalPanelCollapseMode = panelTransitionSession.isNormalPanelCollapseMode();
        cancelPendingOnceStaticReady();
        ValueAnimator valueAnimator = this.blurAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        Object obj = null;
        this.blurAnimator = null;
        ObjectAnimator objectAnimator = this.bgAlphaAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.bgAlphaAnimator = null;
        View view = this.settingsButton;
        if (view != null && (viewPropertyAnimatorAnimate4 = view.animate()) != null) {
            viewPropertyAnimatorAnimate4.cancel();
        }
        View view2 = this.volumeSettingText;
        if (view2 != null && (viewPropertyAnimatorAnimate3 = view2.animate()) != null) {
            viewPropertyAnimatorAnimate3.cancel();
        }
        ImageView imageView = this.panelTitleIcon;
        if (imageView != null && (viewPropertyAnimatorAnimate2 = imageView.animate()) != null) {
            viewPropertyAnimatorAnimate2.cancel();
        }
        TextView textView = this.panelTitleText;
        if (textView != null && (viewPropertyAnimatorAnimate = textView.animate()) != null) {
            viewPropertyAnimatorAnimate.cancel();
        }
        float currentBlurAmount = !zIsNormalPanelCollapseMode ? getCurrentBlurAmount() : Math.max(getCurrentBlurAmount(), 1.0f);
        long blurFadeDurationMs = panelTransitionSession.getBlurFadeDurationMs();
        int i = 0;
        if (VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.context) || !((Boolean) this.isDefaultVolumeThemeProvider.invoke()).booleanValue()) {
            Drawable drawable = (Drawable) this.backgroundDrawableProvider.invoke();
            if (drawable != null) {
                ObjectAnimator objectAnimator2 = this.bgAlphaAnimator;
                if (objectAnimator2 != null) {
                    objectAnimator2.cancel();
                }
                if (currentBlurAmount >= 1.0f) {
                    iCoerceIn = drawable.getAlpha();
                    if (iCoerceIn < 0) {
                        iCoerceIn = 0;
                    }
                } else {
                    iCoerceIn = RangesKt___RangesKt.coerceIn((int) (currentBlurAmount * 255.0f), 0, 255);
                }
                if (iCoerceIn > 0) {
                    ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, iCoerceIn, 0);
                    objectAnimatorOfInt.setDuration(blurFadeDurationMs);
                    objectAnimatorOfInt.setInterpolator(new COUIInEaseInterpolator());
                    objectAnimatorOfInt.start();
                    this.bgAlphaAnimator = objectAnimatorOfInt;
                }
            }
        } else if (currentBlurAmount <= 0.0f) {
            Function1 function1 = this.onPanelBackgroundProgress;
            if (function1 != null) {
                function1.invoke(Float.valueOf(0.0f));
            }
        } else {
            ValueAnimator valueAnimator2 = this.blurAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(currentBlurAmount, 0.0f);
            valueAnimatorOfFloat.setDuration(blurFadeDurationMs);
            valueAnimatorOfFloat.setInterpolator(new COUIInEaseInterpolator());
            startBlurHide.1.1 r3 = new startBlurHide.1.1(0);
            r3.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(r3);
            valueAnimatorOfFloat.addListener(new startBlurHide.1.2(this, 0));
            valueAnimatorOfFloat.start();
            this.blurAnimator = valueAnimatorOfFloat;
        }
        long accessoryFadeDurationMs = panelTransitionSession.getAccessoryFadeDurationMs();
        List listDismissAccessoryViews = dismissAccessoryViews();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : listDismissAccessoryViews) {
            View view3 = (View) obj2;
            if (view3.getVisibility() == 0 && (!zIsNormalPanelCollapseMode || view3.getAlpha() > 0.0f)) {
                arrayList.add(obj2);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator it = dismissAccessoryViews().iterator();
        while (it.hasNext()) {
            ((View) it.next()).animate().cancel();
        }
        int i2 = this.accessoryFadeGeneration + 1;
        this.accessoryFadeGeneration = i2;
        int size = arrayList.size();
        while (i < size) {
            Object obj3 = arrayList.get(i);
            i++;
            if (((View) obj3).isAttachedToWindow()) {
                obj = obj3;
                break;
            }
        }
        View view4 = (View) obj;
        if (view4 == null) {
            view4 = (View) CollectionsKt___CollectionsKt.first(arrayList);
        }
        startAccessoryFadeOut.2 r0 = new startAccessoryFadeOut.2();
        r0.$generation = i2;
        r0.this$0 = this;
        r0.$targets = arrayList;
        r0.$durationMs = accessoryFadeDurationMs;
        r0.$onUpdate = function0;
        VarHandle.storeStoreFence();
        view4.postOnAnimation(r0);
    }

    public final List dismissAccessoryViews() {
        return CollectionsKt__CollectionsKt.listOfNotNull(new View[]{this.settingsButton, this.volumeSettingText, this.panelTipsText, this.panelTitleIcon, this.panelTitleText});
    }

    public final float getCurrentBlurAmount() {
        Drawable drawable = (Drawable) this.backgroundDrawableProvider.invoke();
        if (drawable instanceof AutoBlurDrawable) {
            return ((AutoBlurDrawable) drawable).getViewBlurProxy().getBlurAmount();
        }
        if (drawable != null) {
            return drawable.getAlpha() / 255.0f;
        }
        return 0.0f;
    }

    public final Function1 getOnPanelBackgroundProgress() {
        return this.onPanelBackgroundProgress;
    }

    public final TextView getPanelTipsText() {
        return this.panelTipsText;
    }

    public final ImageView getPanelTitleIcon() {
        return this.panelTitleIcon;
    }

    public final TextView getPanelTitleText() {
        return this.panelTitleText;
    }

    public final View getSettingsButton() {
        return this.settingsButton;
    }

    public final View getVolumeSettingText() {
        return this.volumeSettingText;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0083  */
    public final void performExpandAnimations() {
        TextView textView;
        View view = this.settingsButton;
        if (view != null && view.getVisibility() == 0) {
            animateSettingsEntry$default(this, this.settingsButton, null, 30);
            animateSettingsEntry$default(this, this.volumeSettingText, null, 30);
        }
        if (VolumeBlurManager.INSTANCE.isGaussianBlurDisable(this.context) || !((Boolean) this.isDefaultVolumeThemeProvider.invoke()).booleanValue()) {
            Drawable drawable = (Drawable) this.backgroundDrawableProvider.invoke();
            if (drawable != null) {
                ObjectAnimator objectAnimator = this.bgAlphaAnimator;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, 0, 255);
                objectAnimatorOfInt.setDuration(200L);
                objectAnimatorOfInt.setInterpolator(new COUIInEaseInterpolator());
                objectAnimatorOfInt.start();
                this.bgAlphaAnimator = objectAnimatorOfInt;
            }
        } else {
            Drawable drawable2 = (Drawable) this.backgroundDrawableProvider.invoke();
            if (VolumeBlurManager.isVolumeMotionPlatformBlur(this.context) || !(drawable2 instanceof AutoBlurDrawable)) {
                startPlatformBackgroundExpandAnimation();
            } else {
                AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) drawable2;
                PlatformBlurDrawable cachePlatformStaticBlurDrawable = autoBlurDrawable.getViewBlurProxy().getCachePlatformStaticBlurDrawable();
                if (cachePlatformStaticBlurDrawable == null || !cachePlatformStaticBlurDrawable.hasReceivedBlurContent) {
                    OplusVolumeAutoBlurAnimHelper.applyBlurAmount(autoBlurDrawable, 0.0f);
                    cancelPendingOnceStaticReady();
                    int i = this.onceStaticWaitGeneration + 1;
                    this.onceStaticWaitGeneration = i;
                    View view2 = (View) this.backgroundAnimationAnchorProvider.invoke();
                    if (view2 != null) {
                        waitForOnceStaticThenStart.runnable.1 r5 = new waitForOnceStaticThenStart.runnable.1();
                        r5.$generation = i;
                        r5.this$0 = this;
                        r5.$anchor = view2;
                        r5.$expectedDrawable = autoBlurDrawable;
                        VarHandle.storeStoreFence();
                        this.pendingOnceStaticReady = r5;
                        view2.postOnAnimation(r5);
                    }
                } else {
                    startPlatformBackgroundExpandAnimation();
                }
            }
        }
        ImageView imageView = this.panelTitleIcon;
        if (imageView == null || (textView = this.panelTitleText) == null) {
            return;
        }
        Context context = (Context) this.titleContextProvider.invoke();
        if (((Boolean) this.isSystemPanelProvider.invoke()).booleanValue()) {
            textView.setText(2131957175);
            imageView.setImageResource(2131237447);
            imageView.setContentDescription(context.getString(2131957175));
        } else {
            textView.setText(2131957174);
            imageView.setImageResource(2131237445);
            imageView.setContentDescription(context.getString(2131957174));
        }
        imageView.animate().cancel();
        textView.animate().cancel();
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.8f);
        imageView.setScaleY(0.8f);
        textView.setAlpha(0.0f);
        textView.setScaleX(0.8f);
        textView.setScaleY(0.8f);
        imageView.setVisibility(0);
        textView.setVisibility(0);
        COUISpringInterpolator volume_panel_expand_interpolator = VolumePropertyAnimationUtil.INSTANCE.getVOLUME_PANEL_EXPAND_INTERPOLATOR();
        animateSettingsEntry$default(this, imageView, volume_panel_expand_interpolator, 16);
        animateSettingsEntry$default(this, textView, volume_panel_expand_interpolator, 16);
    }

    public final void prepareTitleForCollapsedState() {
        ImageView imageView = this.panelTitleIcon;
        if (imageView != null) {
            imageView.animate().cancel();
            imageView.setAlpha(0.0f);
            imageView.setScaleX(0.8f);
            imageView.setScaleY(0.8f);
            imageView.setTranslationY(0.0f);
            imageView.setVisibility(8);
        }
        TextView textView = this.panelTitleText;
        if (textView != null) {
            textView.animate().cancel();
            textView.setAlpha(0.0f);
            textView.setScaleX(0.8f);
            textView.setScaleY(0.8f);
            textView.setTranslationY(0.0f);
            textView.setVisibility(8);
        }
    }

    public final void setOnPanelBackgroundProgress(Function1 function1) {
        this.onPanelBackgroundProgress = function1;
    }

    public final void setPanelTipsText(TextView textView) {
        this.panelTipsText = textView;
    }

    public final void setPanelTitleIcon(ImageView imageView) {
        this.panelTitleIcon = imageView;
    }

    public final void setPanelTitleText(TextView textView) {
        this.panelTitleText = textView;
    }

    public final void setSettingsButton(View view) {
        this.settingsButton = view;
    }

    public final void setVolumeSettingText(View view) {
        this.volumeSettingText = view;
    }

    public final void startPlatformBackgroundExpandAnimation() {
        cancelPendingOnceStaticReady();
        ValueAnimator valueAnimator = this.blurAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(new COUIInEaseInterpolator());
        valueAnimatorOfFloat.setDuration(200L);
        startBlurHide.1.1 r1 = new startBlurHide.1.1(1);
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r1);
        valueAnimatorOfFloat.addListener(new startBlurHide.1.2(this, 1));
        valueAnimatorOfFloat.start();
        this.blurAnimator = valueAnimatorOfFloat;
    }
}
