package com.oplus.zoom.animation;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomAnimationUtils {
    public static final float ANIM_ZOOM_CLOSE_SCALE_END = 0.0f;
    public static final float ANIM_ZOOM_CLOSE_SCALE_START = 1.0f;
    public static final float ANIM_ZOOM_FLOAT_CLOSE_SCALE_END = 0.093f;
    public static final float ANIM_ZOOM_FLOAT_CLOSE_SCALE_START = 1.0f;
    public static final float ANIM_ZOOM_FLOAT_OPEN_SCALE_END = 1.0f;
    public static final float ANIM_ZOOM_FLOAT_OPEN_SCALE_START = 0.5f;
    public static final float ANIM_ZOOM_OPEN_SCALE_END = 1.0f;
    public static final float ANIM_ZOOM_OPEN_SCALE_START = 0.0f;
    public static final Interpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    public static final long FULL_ANIM_DURATION = 400;
    public static final long STATE_CHANGE_DURATION = 300;
    private static final String TAG = "ZoomAnimation";
    public static final int TRANSIT_FLOAT_OPEN_TO_ZOOM = 103;
    public static final int TRANSIT_FULL = 202;
    public static final int TRANSIT_PEN_OPEN_TO_ZOOM = 105;
    public static final int TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM = 106;
    public static final int TRANSIT_REMOTE = 100;
    public static final int TRANSIT_SCALE_AND_POSITION = 200;
    public static final int TRANSIT_STATE_SWITCH = 201;
    public static final int TRANSIT_ZOOM_CLOSE = 102;
    public static final int TRANSIT_ZOOM_CLOSE_TO_FLOAT = 104;
    public static final int TRANSIT_ZOOM_OPEN = 101;
    public static final int TRANSIT_ZOOM_UNSET = -1;
    public static final long ZOOM_ANIM_DURATION = 330;
    public static final long ZOOM_TRANSITION_DURATION = 300;
    public static volatile boolean sAnimating = false;

    public static boolean isClosingTransition(int i) {
        return i == 102 || i == 104 || i == 202;
    }

    public static boolean isOpeningTransition(int i) {
        return i == 101 || i == 103 || i == 105 || i == 106;
    }

    public static String transitToString(int i) {
        if (i == -1) {
            return "UNSET";
        }
        switch (i) {
            case 101:
                return "ZOOM_OPEN";
            case 102:
                return "ZOOM_CLOSE";
            case 103:
                return "FLOAT_OPEN_TO_ZOOM";
            case 104:
                return "ZOOM_CLOSE_TO_FLOAT";
            case 105:
                return "PEN_OPEN_TO_ZOOM";
            case TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM /* 106 */:
                return "POCKET_STUDIO";
            default:
                switch (i) {
                    case TRANSIT_SCALE_AND_POSITION /* 200 */:
                        return "SCALE_AND_POSITION";
                    case TRANSIT_STATE_SWITCH /* 201 */:
                        return "SWITCH_STATE";
                    case TRANSIT_FULL /* 202 */:
                        return "FULL_SCREEN";
                    default:
                        return "ERROR";
                }
        }
    }
}
