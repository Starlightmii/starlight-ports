package com.oplus.systemui.statusbar.policy;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderMirrorController extends OplusBrightnessMirrorController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsBrightnessSliderMirrorController";
    private final int[] originInt2Cache;
    private final ViewTreeObserver.OnPreDrawListener originPreDrawListener;
    public int originalHeight;
    public int originalWidth;
    private final OplusQsBrightnessSliderMirrorController$preDrawListener$1 preDrawListener;
    private final Lazy shadowConfig$delegate;
    private PressFeedbackHelper.QsTilePressFeedback tilePressFeedback;
    private final OplusQsBrightnessSliderMirrorController$viewOutlineProvider$1 viewOutlineProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.policy.OplusQsBrightnessSliderMirrorController$reinflate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnLayoutChangeListener {
        public /* synthetic */ OplusQsBrightnessSliderMirrorController this$0;

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            LogUtil.i$default(LogUtil.TAG_QS, OplusQsBrightnessSliderMirrorController.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "onLayoutChange left:", " top:"), null, 8);
            View originView = this.this$0.getOriginView();
            if (originView != null) {
                this.this$0.updateOriginCacheAndMirror(originView);
            }
            ((BrightnessMirrorController) this.this$0).mBrightnessMirror.removeOnLayoutChangeListener(this);
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.oplus.systemui.statusbar.policy.OplusQsBrightnessSliderMirrorController$viewOutlineProvider$1] */
    public OplusQsBrightnessSliderMirrorController(NotificationShadeWindowView notificationShadeWindowView, ShadeViewController shadeViewController, NotificationShadeDepthController notificationShadeDepthController, BrightnessSliderController.Factory factory, Consumer<Boolean> consumer) {
        super(notificationShadeWindowView, shadeViewController, notificationShadeDepthController, factory, consumer);
        this.tilePressFeedback = new PressFeedbackHelper.QsTilePressFeedback(((BrightnessMirrorController) this).mBrightnessMirror);
        this.originalWidth = -1;
        this.originalHeight = -1;
        this.shadowConfig$delegate = LazyKt__LazyJVMKt.lazy(new OplusQsBrightnessSliderMirrorController$$ExternalSyntheticLambda0());
        this.originInt2Cache = new int[2];
        this.viewOutlineProvider = new ViewOutlineProvider() { // from class: com.oplus.systemui.statusbar.policy.OplusQsBrightnessSliderMirrorController$viewOutlineProvider$1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                LogUtil.i$default(LogUtil.TAG_QS, "OplusQsBrightnessSliderMirrorController", "getOutline: outlineRect = " + this.this$0.getShadowConfig().getRect() + ", outlineRadius = " + this.this$0.getShadowConfig().getRadius() + ", outlineWeight = " + this.this$0.getShadowConfig().getWeight(), null, 8);
                float weight = this.this$0.getShadowConfig().getWeight();
                OplusQsBrightnessSliderMirrorController oplusQsBrightnessSliderMirrorController = this.this$0;
                if (weight == 0.0f) {
                    outline.setRoundRect(oplusQsBrightnessSliderMirrorController.getShadowConfig().getRect(), this.this$0.getShadowConfig().getRadius());
                } else {
                    Rect rect = oplusQsBrightnessSliderMirrorController.getShadowConfig().getRect();
                    this.this$0.getShadowConfig().getPath().rewind();
                    this.this$0.getShadowConfig().getOplusPath().addSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, this.this$0.getShadowConfig().getRadius(), this.this$0.getShadowConfig().getRadius(), this.this$0.getShadowConfig().getWeight(), Path.Direction.CCW);
                    outline.setPath(this.this$0.getShadowConfig().getPath());
                }
                outline.setAlpha(0.9f);
            }
        };
        OplusQsBrightnessSliderMirrorController$preDrawListener$1 oplusQsBrightnessSliderMirrorController$preDrawListener$1 = new OplusQsBrightnessSliderMirrorController$preDrawListener$1();
        oplusQsBrightnessSliderMirrorController$preDrawListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.preDrawListener = oplusQsBrightnessSliderMirrorController$preDrawListener$1;
        OplusQsBrightnessSliderMirrorController$originPreDrawListener$1 oplusQsBrightnessSliderMirrorController$originPreDrawListener$1 = new OplusQsBrightnessSliderMirrorController$originPreDrawListener$1();
        oplusQsBrightnessSliderMirrorController$originPreDrawListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.originPreDrawListener = oplusQsBrightnessSliderMirrorController$originPreDrawListener$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OutlineShadowConfig getShadowConfig() {
        return (OutlineShadowConfig) this.shadowConfig$delegate.getValue();
    }

    public static void setSize(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams;
        boolean z;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        boolean z2 = true;
        if (i != layoutParams.width) {
            layoutParams.width = i;
            z = true;
        } else {
            z = false;
        }
        if (i2 != layoutParams.height) {
            layoutParams.height = i2;
        } else {
            z2 = z;
        }
        if (z2) {
            view.setLayoutParams(layoutParams);
        }
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public final void handleMirrorInvisible() {
        View originView = getOriginView();
        if (originView != null) {
            ViewTreeObserver viewTreeObserver = originView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.originPreDrawListener);
            }
        }
        super.handleMirrorInvisible();
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public final void onMirrorHide() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onMirrorHide: ", null, 8);
        ((BrightnessMirrorController) this).mBrightnessMirror.setOutlineProvider(null);
        ((BrightnessMirrorController) this).mBrightnessMirror.setClipToOutline(false);
        ((BrightnessMirrorController) this).mBrightnessMirror.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        IBrightnessSliderController iBrightnessSliderController = ((BrightnessMirrorController) this).mToggleSliderController;
        View view = iBrightnessSliderController != null ? iBrightnessSliderController.getView() : null;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = view instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) view : null;
        if (oplusQsBaseToggleSliderLayout != null) {
            oplusQsBaseToggleSliderLayout.showSeekBarBackgroundStroke(false);
        }
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public final void onMirrorShow() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onMirrorShow", null, 8);
        ((BrightnessMirrorController) this).mBrightnessMirror.setClipToOutline(false);
        ((BrightnessMirrorController) this).mBrightnessMirror.setOutlineProvider(this.viewOutlineProvider);
        getShadowConfig().getRect().setEmpty();
        getShadowConfig().getSliderBackgroundRect().setEmpty();
        ((BrightnessMirrorController) this).mBrightnessMirror.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        ((BrightnessMirrorController) this).mBrightnessMirror.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        IBrightnessSliderController iBrightnessSliderController = ((BrightnessMirrorController) this).mToggleSliderController;
        View view = iBrightnessSliderController != null ? iBrightnessSliderController.getView() : null;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = view instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) view : null;
        if (oplusQsBaseToggleSliderLayout != null) {
            oplusQsBaseToggleSliderLayout.showSeekBarBackgroundStroke(true);
        }
    }

    public final void onSliderTouchEvent(MotionEvent motionEvent) {
        IQSSpotLightConfig slider;
        IBrightnessSliderController iBrightnessSliderController = ((BrightnessMirrorController) this).mToggleSliderController;
        View view = iBrightnessSliderController != null ? iBrightnessSliderController.getView() : null;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = view instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) view : null;
        if (oplusQsBaseToggleSliderLayout == null || (slider = oplusQsBaseToggleSliderLayout.getSlider()) == null) {
            return;
        }
        SharedSpotLightEffect.onTouchEvent(slider, motionEvent);
    }

    public final void reinflate() {
        super.reinflate();
        FrameLayout frameLayout = ((BrightnessMirrorController) this).mBrightnessMirror;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        frameLayout.addOnLayoutChangeListener(anonymousClass1);
        this.tilePressFeedback = new PressFeedbackHelper.QsTilePressFeedback(((BrightnessMirrorController) this).mBrightnessMirror);
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public void setLocationAndSize(View view) {
        View originView = getOriginView();
        if (originView != null) {
            ViewTreeObserver viewTreeObserver = originView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.originPreDrawListener);
            }
        }
        setOriginView(view);
        ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
        if (viewTreeObserver2.isAlive()) {
            viewTreeObserver2.addOnPreDrawListener(this.originPreDrawListener);
        }
        view.getLocationInWindow(this.originInt2Cache);
        setMirrorTranslationAndSize();
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public IBrightnessSliderController setMirrorLayout() {
        IBrightnessSliderController iBrightnessSliderControllerCreate = ((BrightnessMirrorController) this).mToggleSliderFactory.create(((BrightnessMirrorController) this).mBrightnessMirror.getContext(), ((BrightnessMirrorController) this).mBrightnessMirror);
        iBrightnessSliderControllerCreate.setIsMirror(true);
        iBrightnessSliderControllerCreate.doInit();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        int dimension = (int) ((BrightnessMirrorController) this).mBrightnessMirror.getContext().getResources().getDimension(2131171555);
        new FrameLayout.LayoutParams(((BrightnessMirrorController) this).mBrightnessMirror.getLayoutParams()).setMargins(dimension, dimension, dimension, dimension);
        Context context = ((BrightnessMirrorController) this).mBrightnessMirror.getContext();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131171554);
        FrameLayout frameLayout = ((BrightnessMirrorController) this).mBrightnessMirror;
        int color = context.getResources().getColor(2131101244);
        frameLayout.getResources().getDimensionPixelOffset(2131173615);
        frameLayout.setOutlineSpotShadowColor(color);
        frameLayout.setElevation(dimensionPixelSize);
        View view = iBrightnessSliderControllerCreate.getView();
        if (view != null) {
            ((BrightnessMirrorController) this).mBrightnessMirror.removeAllViews();
            ((BrightnessMirrorController) this).mBrightnessMirror.addView(view, layoutParams);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setMirrorLayout mBrightnessMirror= " + ((BrightnessMirrorController) this).mBrightnessMirror + " shadowSize: " + dimensionPixelSize, null, 8);
        return iBrightnessSliderControllerCreate;
    }

    public final void setMirrorTranslationAndSize() {
        View originView = getOriginView();
        if (originView == null) {
            return;
        }
        int scaleX = this.originInt2Cache[0] + ((int) ((originView.getScaleX() - 1.0f) * originView.getPivotX()));
        int scaleY = this.originInt2Cache[1] + ((int) (((originView.getScaleY() - 1.0f) * originView.getPivotY()) - originView.getTranslationY()));
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationX(0.0f);
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationY(0.0f);
        FrameLayout frameLayout = ((BrightnessMirrorController) this).mBrightnessMirror;
        int[] iArr = ((BrightnessMirrorController) this).mInt2Cache;
        frameLayout.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int dimension = (int) ((BrightnessMirrorController) this).mBrightnessMirror.getContext().getResources().getDimension(2131165742);
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationX(scaleX - i);
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationY((scaleY - i2) - dimension);
        ((BrightnessMirrorController) this).mBrightnessMirror.setPadding(0, dimension, 0, dimension);
        int i3 = this.originalWidth;
        ViewGroup.LayoutParams layoutParams = ((BrightnessMirrorController) this).mBrightnessMirror.getLayoutParams();
        if (i3 != layoutParams.width) {
            layoutParams.width = i3;
            ((BrightnessMirrorController) this).mBrightnessMirror.setLayoutParams(layoutParams);
        }
    }

    public final void updateOriginCacheAndMirror(View view) {
        view.getLocationInWindow(this.originInt2Cache);
        setMirrorTranslationAndSize();
    }

    @Override // com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController
    public final void updateResources() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateResources", null, 8);
        View view = ((BrightnessMirrorController) this).mToggleSliderController.getView();
        if (view == null) {
            return;
        }
        setSize(view, this.originalWidth, this.originalHeight);
        setSize(((BrightnessMirrorController) this).mBrightnessMirror, this.originalWidth, (((int) ((BrightnessMirrorController) this).mBrightnessMirror.getContext().getResources().getDimension(2131165742)) * 2) + this.originalHeight);
    }
}
