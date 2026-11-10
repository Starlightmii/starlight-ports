package com.oplus.systemui.notification.material;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.shadowedge.COUIShadowEdgeDrawable;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpShadowEdgeController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpShadowEdgeController";
    private Context context;
    public float cornerRadius;
    private COUIShadowEdgeDrawable drawable;
    private WeakReference<View> drawableHostView;
    private final Runnable edgeAvailabilityListener;
    private WeakReference<View> invalidateTargetView;
    public boolean isDarkStyle;
    public boolean isListeningRegistered;
    private final Rect tmpBounds = new Rect();
    public volatile boolean isShadowEdgeEnabled = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final HeadsUpShadowEdgeController create(Context context) {
            if (NotificationMaterialShadowEdgeUtils.isShadowEdgeEnabled(context)) {
                return new HeadsUpShadowEdgeController();
            }
            return null;
        }
    }

    public HeadsUpShadowEdgeController() {
        edgeAvailabilityListener.1 r0 = new edgeAvailabilityListener.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        this.edgeAvailabilityListener = r0;
    }

    public static final HeadsUpShadowEdgeController create(Context context) {
        return Companion.create(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getCurrentAvailableState() {
        COUIMaterialStateManager.INSTANCE.getClass();
        if (!COUIMaterialStateManager.materialVersionCheck() || !FeatureOption.getEnableLightFeature() || !COUIMaterialStateManager.MATERIAL_STROKE_ENABLE) {
            return false;
        }
        ScrimControllerEx.Companion.getClass();
        return (ScrimControllerEx.isThirdTheme || GaussBlurUtils.isQSGaussBlurDisabled()) ? false : true;
    }

    public static /* synthetic */ void show$default(HeadsUpShadowEdgeController headsUpShadowEdgeController, View view, Context context, float f, View view2, boolean z, int i) {
        if ((i & 8) != 0) {
            view2 = null;
        }
        View view3 = view2;
        if ((i & 16) != 0) {
            z = false;
        }
        headsUpShadowEdgeController.show(view, context, f, view3, z);
    }

    public final void attachInternal() {
        WeakReference<View> weakReference;
        View view;
        if (!this.isShadowEdgeEnabled || (weakReference = this.drawableHostView) == null || (view = weakReference.get()) == null) {
            return;
        }
        float f = this.cornerRadius;
        float defaultWeight = SmoothRoundEx.INSTANCE.getDefaultWeight();
        float f2 = f * 1.5f;
        float f3 = (8.0f * defaultWeight) / ((defaultWeight * 3.0f) + 3.0f);
        NotificationMaterialShadowEdgeUtils.ShadowEdgeCornerParams shadowEdgeCornerParams = new NotificationMaterialShadowEdgeUtils.ShadowEdgeCornerParams(f2, f3, 1);
        if (f2 > 0.0f && f3 > 0.0f) {
            if (this.drawable == null) {
                this.drawable = new COUIShadowEdgeDrawable();
            }
            NotificationMaterialShadowEdgeUtils.updateShadowEdgeDrawable(this.drawable, shadowEdgeCornerParams, this.isDarkStyle);
        }
        NotificationMaterialShadowEdgeUtils.INSTANCE.bindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(view, this.drawable);
    }

    public final void draw(Canvas canvas, View view, Rect rect) {
        if (this.drawable == null || canvas == null || rect == null) {
            return;
        }
        int translationX = view != null ? (int) view.getTranslationX() : 0;
        int translationY = view != null ? (int) view.getTranslationY() : 0;
        this.tmpBounds.set(rect.left + translationX, rect.top + translationY, rect.right + translationX, rect.bottom + translationY);
        NotificationMaterialShadowEdgeUtils.INSTANCE.drawShadowEdge$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(canvas, this.drawable, this.tmpBounds);
    }

    public final boolean isAttached() {
        return this.drawable != null;
    }

    public final void listenToMaterialState(boolean z) {
        COUIMaterialStateManager.INSTANCE.getClass();
        if (COUIMaterialStateManager.materialVersionCheck()) {
            boolean z2 = this.isListeningRegistered;
            if (z) {
                if (z2) {
                    return;
                }
                NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(this.edgeAvailabilityListener);
                this.isListeningRegistered = true;
                return;
            }
            if (z2) {
                NotificationSpotLightEffectManager.removeOnSpotLightAvailabilityChangedListener(this.edgeAvailabilityListener);
                this.isListeningRegistered = false;
            }
        }
    }

    public final void refresh(float f) {
        if (isAttached()) {
            if ((Math.abs(f - this.cornerRadius) >= 1.0f || f == 0.0f) && f != this.cornerRadius) {
                this.cornerRadius = f;
                refreshInternal(this.isShadowEdgeEnabled);
            }
        }
    }

    public final void refreshInternal(boolean z) {
        View view;
        View view2;
        if (z) {
            attachInternal();
        } else {
            COUIShadowEdgeDrawable cOUIShadowEdgeDrawable = this.drawable;
            if (cOUIShadowEdgeDrawable != null) {
                NotificationMaterialShadowEdgeUtils.INSTANCE.unbindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(cOUIShadowEdgeDrawable);
                this.drawable = null;
            }
        }
        WeakReference<View> weakReference = this.invalidateTargetView;
        if (weakReference != null && (view2 = weakReference.get()) != null) {
            view2.invalidate();
            return;
        }
        WeakReference<View> weakReference2 = this.drawableHostView;
        if (weakReference2 == null || (view = weakReference2.get()) == null) {
            return;
        }
        view.invalidate();
    }

    public final void refreshUiStyle(boolean z) {
        if (isAttached() && this.isDarkStyle != z) {
            this.isDarkStyle = z;
            NotificationMaterialShadowEdgeUtils.updateShadowEdgeDrawableParam(this.drawable, z);
        }
    }

    public final void release() {
        if (this.isListeningRegistered) {
            listenToMaterialState(false);
            this.isListeningRegistered = false;
        }
        COUIShadowEdgeDrawable cOUIShadowEdgeDrawable = this.drawable;
        if (cOUIShadowEdgeDrawable != null) {
            NotificationMaterialShadowEdgeUtils.INSTANCE.unbindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(cOUIShadowEdgeDrawable);
            this.drawable = null;
        }
        this.drawableHostView = null;
        this.invalidateTargetView = null;
        this.context = null;
        this.isListeningRegistered = false;
    }

    public final void show(View view, Context context, float f, View view2, boolean z) {
        COUIShadowEdgeDrawable cOUIShadowEdgeDrawable = this.drawable;
        if (cOUIShadowEdgeDrawable != null) {
            NotificationMaterialShadowEdgeUtils.INSTANCE.unbindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(cOUIShadowEdgeDrawable);
            this.drawable = null;
        }
        this.drawableHostView = new WeakReference<>(view);
        if (view2 != null) {
            view = view2;
        }
        this.invalidateTargetView = new WeakReference<>(view);
        this.context = context.getApplicationContext();
        this.cornerRadius = f;
        this.isDarkStyle = z;
        listenToMaterialState(false);
        this.isShadowEdgeEnabled = getCurrentAvailableState();
        listenToMaterialState(true);
        attachInternal();
    }

    public final void show(View view, Context context, float f, View view2) {
        show$default(this, view, context, f, view2, false, 16);
    }

    public final void show(View view, Context context, float f) {
        show$default(this, view, context, f, null, false, 24);
    }
}
