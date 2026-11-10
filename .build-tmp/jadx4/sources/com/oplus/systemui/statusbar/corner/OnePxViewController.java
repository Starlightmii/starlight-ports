package com.oplus.systemui.statusbar.corner;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.RegionInterceptingFrameLayout;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OnePxViewController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OnePxViewController";
    public int colorsInvertedValue;
    private final Context context;
    private View leftEdgeView;
    private View rightEdgeView;
    private final WindowManager windowManager;
    public final boolean sDebugEdges = SystemProperties.getBoolean("debug.screen_decorations.edges.enable", false);
    public final int sEdgeBold = SystemProperties.getInt("debug.screen_decorations.edges.bold", 1);
    public final boolean sDebugScreenshotRoundedCorners = SystemProperties.getBoolean("debug.screenshot_rounded_corners", false);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OnePxViewController(Context context, WindowManager windowManager) {
        this.context = context;
        this.windowManager = windowManager;
    }

    public final void colorsInvertedValue(int i) {
        int i2;
        FeatureOption.INSTANCE.getClass();
        boolean zIsFoldDevice = FeatureOption.isFoldDevice();
        boolean z = this.sDebugEdges;
        if ((zIsFoldDevice || z) && this.colorsInvertedValue != i) {
            View view = this.leftEdgeView;
            int i3 = -1;
            if (view != null) {
                if (z) {
                    i2 = -65536;
                } else {
                    i2 = i == 0 ? -16777216 : -1;
                }
                view.setBackgroundColor(i2);
            }
            View view2 = this.rightEdgeView;
            if (view2 != null) {
                if (z) {
                    i3 = -65536;
                } else if (i == 0) {
                    i3 = -16777216;
                }
                view2.setBackgroundColor(i3);
            }
        }
        this.colorsInvertedValue = i;
    }

    public final WindowManager.LayoutParams getEdgeWindowParams(int i, int i2) {
        String str;
        int i3 = i - i2;
        int i4 = i3 < 0 ? i3 + 4 : i3;
        boolean z = (i4 == 3) | (i4 == 1);
        int i5 = this.sEdgeBold;
        int i6 = -1;
        int i7 = z ? -1 : i5;
        int i8 = i3 < 0 ? i3 + 4 : i3;
        if (!((i8 == 3) | (i8 == 1))) {
            i5 = -1;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i7, i5, 2024, 545259800, -3);
        int i9 = layoutParams.privateFlags;
        layoutParams.privateFlags = i9 | 80;
        if (!this.sDebugScreenshotRoundedCorners) {
            layoutParams.privateFlags = 1048656 | i9;
        }
        if (i == 0) {
            str = "ScreenDecorEdgeLeft";
        } else if (i == 1) {
            str = "ScreenDecorEdgeTop";
        } else if (i != 2) {
            str = i != 3 ? "ScreenDecorEdge" : "ScreenDecorEdgeBottom";
        } else {
            str = "ScreenDecorEdgeRight";
        }
        layoutParams.setTitle(str);
        if (i3 < 0) {
            i3 += 4;
        }
        if (i3 == 0) {
            i6 = 3;
        } else if (i3 == 1) {
            i6 = 48;
        } else if (i3 == 2) {
            i6 = 5;
        } else if (i3 == 3) {
            i6 = 80;
        }
        layoutParams.gravity = i6;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.privateFlags |= 16777216;
        return layoutParams;
    }

    public final void setupOnePx(int i) {
        int i2;
        FeatureOption.INSTANCE.getClass();
        boolean zIsFoldDevice = FeatureOption.isFoldDevice();
        boolean z = this.sDebugEdges;
        if (zIsFoldDevice || z) {
            StatusBarFeatureOption.INSTANCE.getClass();
            if (StatusBarFeatureOption.isOnePxOptSupport) {
                if (this.leftEdgeView == null || this.rightEdgeView == null) {
                    RegionInterceptingFrameLayout regionInterceptingFrameLayout = new RegionInterceptingFrameLayout(this.context);
                    this.leftEdgeView = regionInterceptingFrameLayout;
                    int i3 = -1;
                    if (z) {
                        i2 = -65536;
                    } else {
                        i2 = this.colorsInvertedValue == 0 ? -16777216 : -1;
                    }
                    regionInterceptingFrameLayout.setBackgroundColor(i2);
                    View view = this.leftEdgeView;
                    if (view != null) {
                        view.setSystemUiVisibility(256);
                    }
                    View view2 = this.leftEdgeView;
                    if (view2 != null) {
                        view2.setForceDarkAllowed(false);
                    }
                    this.windowManager.addView(this.leftEdgeView, getEdgeWindowParams(0, i));
                    RegionInterceptingFrameLayout regionInterceptingFrameLayout2 = new RegionInterceptingFrameLayout(this.context);
                    this.rightEdgeView = regionInterceptingFrameLayout2;
                    if (z) {
                        i3 = -65536;
                    } else if (this.colorsInvertedValue == 0) {
                        i3 = -16777216;
                    }
                    regionInterceptingFrameLayout2.setBackgroundColor(i3);
                    View view3 = this.rightEdgeView;
                    if (view3 != null) {
                        view3.setSystemUiVisibility(256);
                    }
                    View view4 = this.rightEdgeView;
                    if (view4 != null) {
                        view4.setForceDarkAllowed(false);
                    }
                    this.windowManager.addView(this.rightEdgeView, getEdgeWindowParams(2, i));
                    return;
                }
                return;
            }
        }
        View view5 = this.leftEdgeView;
        if (view5 == null || this.rightEdgeView == null) {
            return;
        }
        this.windowManager.removeViewImmediate(view5);
        this.windowManager.removeViewImmediate(this.rightEdgeView);
        this.leftEdgeView = null;
        this.rightEdgeView = null;
    }

    public final void updateEdgesParams(int i) {
        View view;
        FeatureOption.INSTANCE.getClass();
        if ((!FeatureOption.isFoldDevice() && !this.sDebugEdges) || (view = this.leftEdgeView) == null || this.rightEdgeView == null) {
            return;
        }
        this.windowManager.updateViewLayout(view, getEdgeWindowParams(0, i));
        this.windowManager.updateViewLayout(this.rightEdgeView, getEdgeWindowParams(2, i));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OnePxViewController-->".concat("updateEdgesParams rotation: " + i + ", left:" + getEdgeWindowParams(0, i) + ", right:" + getEdgeWindowParams(2, i)));
        }
    }
}
