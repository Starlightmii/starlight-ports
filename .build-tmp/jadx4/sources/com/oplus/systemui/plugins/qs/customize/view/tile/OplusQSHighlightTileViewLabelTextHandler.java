package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.plugins.qs.QSTile;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewLabelTextHandler {
    public static final String BED_TIME = "custom(com.google.android.apps.wellbeing/.screen.ui.GrayscaleTileService)";
    public static final String BLUETOOTH = "bt";
    public static final String CAMERA_ACCESS = "cameratoggle";
    public static final String CELL = "cell";
    public static final String DND = "dnd";
    public static final String HOTSPOT = "hotspot";
    public static final String INK_MODE = "custom(com.oplus.eyeprotect/.services.InkModeTileService)";
    public static final OplusQSHighlightTileViewLabelTextHandler INSTANCE = new OplusQSHighlightTileViewLabelTextHandler();
    public static final String MIC_ACCESS = "mictoggle";
    public static final String TAG = "OplusQSHighlightCommon";
    public static final String WIFI = "wifi";

    /* JADX WARN: Code duplicated, block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0143  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final CharSequence handleSpecialCases$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(Context context, CharSequence charSequence, QSTile.State state) {
        CharSequence charSequence2;
        CharSequence charSequence3;
        CharSequence charSequence4;
        CharSequence charSequence5;
        String str = state.spec;
        if (str == null) {
            return null;
        }
        switch (str.hashCode()) {
            case -1718937151:
                if (!str.equals(INK_MODE)) {
                    return null;
                }
                if (state.state == 2 || (charSequence4 = state.secondaryLabel) == null || charSequence4.length() == 0) {
                    return null;
                }
                CharSequence charSequence6 = state.secondaryLabel;
                state.labelDesc = charSequence6;
                return charSequence6;
            case -37334949:
                if (!str.equals(MIC_ACCESS)) {
                    return null;
                }
                if (state.state == 1) {
                    return null;
                }
                CharSequence charSequence7 = state.secondaryLabel;
                state.labelDesc = charSequence7;
                return charSequence7;
            case 3154:
                if (str.equals(BLUETOOTH) && (charSequence2 = state.label) != null && charSequence2.length() != 0 && state.state == 2) {
                    return state.labelDesc;
                }
                return null;
            case 99610:
                if (!str.equals(DND) || state.state != 2 || (charSequence3 = state.secondaryLabel) == null || charSequence3.length() == 0) {
                    return null;
                }
                CharSequence charSequence8 = state.secondaryLabel;
                state.labelDesc = charSequence8;
                return charSequence8;
            case 3049826:
                if (!str.equals(CELL) || state.state != 0) {
                    return null;
                }
                CharSequence charSequence9 = state.label;
                if (charSequence9 != null && charSequence9.length() != 0) {
                    return null;
                }
                CharSequence charSequence10 = state.secondaryLabel;
                state.labelDesc = charSequence10;
                return charSequence10;
            case 3649301:
                if (!str.equals(WIFI) || state.state != 2) {
                    return null;
                }
                if (!TextUtils.equals(state.label, context.getResources().getString(2131955875))) {
                    state.labelDesc = context.getResources().getString(2131955245);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "OplusQSHighlightCommon-->".concat("handleTextForSpecialCases: mLabelDesc?.text: " + ((Object) charSequence) + " secondaryLabel: " + ((Object) state.secondaryLabel)));
                    }
                    return state.labelDesc;
                }
                CharSequence charSequence11 = state.secondaryLabel;
                if (charSequence11 == null || charSequence11.length() == 0) {
                    return state.labelDesc;
                }
                state.labelDesc = state.secondaryLabel;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQSHighlightCommon-->".concat("handleTextForSpecialCases: mLabelDesc?.text: " + ((Object) charSequence) + " secondaryLabel: " + ((Object) state.secondaryLabel)));
                }
                return state.labelDesc;
            case 6344377:
                if (!str.equals(CAMERA_ACCESS)) {
                    return null;
                }
                if (state.state == 1) {
                    return null;
                }
                CharSequence charSequence12 = state.secondaryLabel;
                state.labelDesc = charSequence12;
                return charSequence12;
            case 472658989:
                if (!str.equals(BED_TIME)) {
                    return null;
                }
                return state.state == 2 ? null : null;
            case 1099603663:
                if (!str.equals(HOTSPOT) || state.state != 2 || (charSequence5 = state.secondaryLabel) == null || charSequence5.length() == 0) {
                    return null;
                }
                CharSequence charSequence13 = state.secondaryLabel;
                state.labelDesc = charSequence13;
                return charSequence13;
            default:
                return null;
        }
    }
}
