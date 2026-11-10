package com.oplus.systemui.navigationbar.gesture.sidegesture.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.android.systemui.navigationbar.GesturePointContainerEx;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.SideGestureViewManagerEx;
import com.oplus.systemui.navigationbar.gesture.manager.GestureUIThreadManager;
import com.oplus.systemui.navigationbar.gesture.sidegesture.GesturePointContainer;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.gesture.subdisplay.SecondaryRotationWatcher;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SideGestureViewManager extends SideGestureViewManagerEx {
    public static final Companion Companion = new Companion();
    private Bitmap backIcon;
    private final Context context;
    private Bitmap disableIcon;
    private final Display display;
    public boolean hasCreated;
    public final boolean isSubDisplayId;
    private SideGestureNavView navLeftView;
    private SideGestureNavView navRightView;
    public int rotation;
    private final SideGestureConfigurationEx sideGestureConfiguration;
    private final WindowManager windowManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SideGestureViewManager(Context context, Display display, SideGestureConfigurationEx sideGestureConfigurationEx) {
        this.display = display;
        this.sideGestureConfiguration = sideGestureConfigurationEx;
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext == null ? context : applicationContext;
        this.windowManager = (WindowManager) context.getSystemService("window");
        ConfigurationOnSubDisplay.Companion companion = ConfigurationOnSubDisplay.Companion;
        ConfigurationOnSubDisplay companion2 = companion.getInstance(context);
        int displayId = display.getDisplayId();
        companion2.getClass();
        this.rotation = ConfigurationOnSubDisplay.isSubDisplayId(displayId) ? SecondaryRotationWatcher.Companion.getSecondaryRotation() : display.getRotation();
        ConfigurationOnSubDisplay companion3 = companion.getInstance(context);
        int displayId2 = display.getDisplayId();
        companion3.getClass();
        this.isSubDisplayId = ConfigurationOnSubDisplay.isSubDisplayId(displayId2);
        this.navLeftView = new SideGestureNavView(context, 0, this.rotation, sideGestureConfigurationEx);
        this.navRightView = new SideGestureNavView(context, 1, this.rotation, sideGestureConfigurationEx);
    }

    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public final Bitmap adjustBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float iconSize = this.sideGestureConfiguration.getIconSize() / width;
        float iconSize2 = this.sideGestureConfiguration.getIconSize() / height;
        Matrix matrix = new Matrix();
        matrix.postScale(iconSize, iconSize2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public final void createIcon() {
        recycleBitmap(this.backIcon);
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.context.getResources(), 2131235683);
        this.backIcon = bitmapDecodeResource;
        boolean z = this.isSubDisplayId;
        if (z) {
            this.backIcon = adjustBitmap(bitmapDecodeResource);
        }
        this.navLeftView.setBackIcon(this.backIcon);
        this.navRightView.setBackIcon(this.backIcon);
        recycleBitmap(this.disableIcon);
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(this.context.getResources(), 2131235686);
        this.disableIcon = bitmapDecodeResource2;
        if (z) {
            this.disableIcon = adjustBitmap(bitmapDecodeResource2);
        }
    }

    public final void createView() {
        if (!this.hasCreated) {
            this.navLeftView.setVisibility(8);
            this.navLeftView.onConfigChanged(this.rotation);
            this.windowManager.addView(this.navLeftView, getSideViewLayoutParams(0));
            this.navRightView.setVisibility(8);
            this.navRightView.onConfigChanged(this.rotation);
            this.windowManager.addView(this.navRightView, getSideViewLayoutParams(1));
        }
        createIcon();
        this.hasCreated = true;
    }

    public final void destroyView() {
        if (this.hasCreated) {
            this.windowManager.removeView(this.navLeftView);
            this.windowManager.removeView(this.navRightView);
        }
        recycleBitmap(this.backIcon);
        recycleBitmap(this.disableIcon);
        this.hasCreated = false;
    }

    public final WindowManager.LayoutParams getSideViewLayoutParams(int i) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2024;
        layoutParams.setTrustedOverlay();
        layoutParams.windowAnimations = 0;
        layoutParams.format = 1;
        layoutParams.flags = 25167672;
        if (i == 0) {
            layoutParams.setTitle("ColorSideGestureLeft_displayId_" + this.display.getDisplayId());
            layoutParams.height = this.sideGestureConfiguration.getWindowHeight(this.rotation);
            layoutParams.width = this.sideGestureConfiguration.getWindowWidth();
            layoutParams.gravity = 51;
            layoutParams.x = 0;
            layoutParams.y = this.sideGestureConfiguration.getScreenHeight(this.rotation) - this.sideGestureConfiguration.getWindowHeight(this.rotation);
            return layoutParams;
        }
        if (i != 1) {
            return layoutParams;
        }
        layoutParams.setTitle("ColorSideGestureRight_displayId_" + this.display.getDisplayId());
        layoutParams.height = this.sideGestureConfiguration.getWindowHeight(this.rotation);
        layoutParams.width = this.sideGestureConfiguration.getWindowWidth();
        layoutParams.gravity = 51;
        layoutParams.x = this.sideGestureConfiguration.getScreenWidth(this.rotation) - this.sideGestureConfiguration.getWindowWidth();
        layoutParams.y = this.sideGestureConfiguration.getScreenHeight(this.rotation) - this.sideGestureConfiguration.getWindowHeight(this.rotation);
        return layoutParams;
    }

    public final boolean isExitAnimFinished(int i) {
        return !(i == 0 ? this.navLeftView : this.navRightView).exitAnimProcessing;
    }

    public final void onConfigChanged(int i) {
        this.rotation = i;
        this.windowManager.updateViewLayout(this.navLeftView, getSideViewLayoutParams(0));
        this.windowManager.updateViewLayout(this.navRightView, getSideViewLayoutParams(1));
        this.navLeftView.onConfigChanged(i);
        this.navRightView.onConfigChanged(i);
    }

    public void onDownEvent(int i, MotionEvent motionEvent, int i2) {
        if (i == 0) {
            this.navLeftView.onDownEvent(motionEvent, i2);
        } else {
            if (i != 1) {
                return;
            }
            this.navRightView.onDownEvent(motionEvent, i2);
        }
    }

    public void onGestureFinished(GesturePointContainerEx gesturePointContainerEx) {
        GesturePointContainer gesturePointContainer = (GesturePointContainer) gesturePointContainerEx;
        int side = gesturePointContainer.getSide();
        if (side == 0) {
            this.navLeftView.onGestureFinished(gesturePointContainer);
        } else {
            if (side != 1) {
                return;
            }
            this.navRightView.onGestureFinished(gesturePointContainer);
        }
    }

    public final void onUpEvent(int i) {
        if (i == 0) {
            this.navLeftView.onUpEvent();
        } else {
            if (i != 1) {
                return;
            }
            this.navRightView.onUpEvent();
        }
    }

    public void onUpdateGestureView(GesturePointContainerEx gesturePointContainerEx) {
        int side = gesturePointContainerEx.getSide();
        if (side == 0) {
            this.navLeftView.onUpdateGestureView(gesturePointContainerEx);
        } else {
            if (side != 1) {
                return;
            }
            this.navRightView.onUpdateGestureView(gesturePointContainerEx);
        }
    }

    public final void resizeView(int i) {
        recycleBitmap(this.backIcon);
        recycleBitmap(this.disableIcon);
        createIcon();
        onConfigChanged(i);
    }

    public final void updateBackIcon() {
        Handler gestureUIHandler = GestureUIThreadManager.INSTANCE.getGestureUIHandler();
        updateBackIcon.1 r1 = new updateBackIcon.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        gestureUIHandler.post(r1);
    }
}
