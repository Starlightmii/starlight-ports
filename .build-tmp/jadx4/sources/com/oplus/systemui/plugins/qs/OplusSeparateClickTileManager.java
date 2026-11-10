package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.TileTipsData;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateClickTileManager {
    private static final String TAG = "OplusSeparateClickTileManager";
    private Integer clickQSTileState;
    private CharSequence clickQsTileFunctionName;
    public boolean qsListening;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new OplusSeparateClickTileManager$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSeparateClickTileManager getInstance() {
            return (OplusSeparateClickTileManager) OplusSeparateClickTileManager.instance$delegate.getValue();
        }
    }

    public final void onClickTileStateChange(Context context, QSTile.State state) {
        int i;
        String string;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z = state.toggleable;
            TileTipsData tileTipsData = state.functionName;
            int i2 = state.state;
            CharSequence charSequence = this.clickQsTileFunctionName;
            Log.d("SystemUi--QuickSettings", "OplusSeparateClickTileManager-->".concat("onClickTileStateChange ,toggleable = " + z + " ,state.functionName = " + tileTipsData + " ,state.state = " + i2 + ",clickQsTileFunctionName = " + ((Object) charSequence) + " ,clickQSTileState = " + this.clickQSTileState + " , qsListening = " + this.qsListening));
        }
        if (this.clickQsTileFunctionName != null && Intrinsics.areEqual(state.functionName.getMainText(), this.clickQsTileFunctionName)) {
            int i3 = state.state;
            Integer num = this.clickQSTileState;
            if ((num == null || i3 != num.intValue()) && (i = state.state) != 0 && state.toggleable && this.qsListening) {
                if (i == 2) {
                    string = context.getResources().getString(2131955268, state.functionName.getMainText());
                    string.getClass();
                } else {
                    string = context.getResources().getString(2131955267, state.functionName.getMainText());
                    string.getClass();
                }
                int color = QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState()) ? -1 : context.getColor(2131103752);
                TemporarilyInfoManager companion = TemporarilyInfoManager.Companion.getInstance();
                CharSequence subText = state.functionName.getSubText();
                companion.show(string, subText != null ? subText.toString() : null, String.valueOf(state.functionName.getMainText()), new TemporarilyInfoManager.InfoShowStyle(color, 11));
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    CharSequence subText2 = state.functionName.getSubText();
                    int i4 = state.state;
                    StringBuilder sb = new StringBuilder("show temporarily info text = ");
                    sb.append(string);
                    sb.append(",subtext = ");
                    sb.append((Object) subText2);
                    sb.append(",state = ");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(i4, "OplusSeparateClickTileManager-->", "SystemUi--QuickSettings", sb);
                }
            }
        }
        if (Intrinsics.areEqual(state.functionName.getMainText(), this.clickQsTileFunctionName)) {
            this.clickQsTileFunctionName = null;
            this.clickQSTileState = null;
        }
    }

    public final void onQStileClick(CharSequence charSequence, int i) {
        this.clickQsTileFunctionName = charSequence;
        this.clickQSTileState = Integer.valueOf(i);
    }

    public final void onThreeStageChange(Context context, QSTile.State state) {
        String string;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z = state.toggleable;
            TileTipsData tileTipsData = state.functionName;
            int i = state.state;
            CharSequence charSequence = this.clickQsTileFunctionName;
            Integer num = this.clickQSTileState;
            boolean z2 = this.qsListening;
            int i2 = state.threeStageMode;
            StringBuilder sb = new StringBuilder("onThreeStageChange ,toggleable = ");
            sb.append(z);
            sb.append(" ,state.functionName = ");
            sb.append(tileTipsData);
            sb.append(" ,state.state = ");
            sb.append(i);
            sb.append(",clickQsTileFunctionName = ");
            sb.append((Object) charSequence);
            sb.append(" ,clickQSTileState = ");
            sb.append(num);
            sb.append(" ,qsListening = ");
            sb.append(z2);
            sb.append(" threeStageMode:");
            MobileInputLogger$.ExternalSyntheticOutline0.m(i2, "OplusSeparateClickTileManager-->", "SystemUi--QuickSettings", sb);
        }
        if (state.state != 0 && state.toggleable && this.qsListening) {
            int i3 = state.threeStageMode;
            if (i3 == 0) {
                string = context.getResources().getString(2131955244);
            } else if (i3 != 1) {
                string = i3 != 2 ? "" : context.getResources().getString(2131955227);
            } else {
                string = context.getResources().getString(2131955249);
            }
            int color = QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState()) ? -1 : context.getColor(2131103752);
            TemporarilyInfoManager companion = TemporarilyInfoManager.Companion.getInstance();
            CharSequence subText = state.functionName.getSubText();
            companion.show(string, subText != null ? subText.toString() : null, String.valueOf(state.functionName.getMainText()), new TemporarilyInfoManager.InfoShowStyle(color, 11));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                int i4 = state.state;
                CharSequence subText2 = state.functionName.getSubText();
                int i5 = state.threeStageMode;
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i4, "show temporarily info text = ", string, ",state = ", ",subtext = ");
                sbM.append((Object) subText2);
                sbM.append(",threeStageMode:");
                sbM.append(i5);
                Log.d("SystemUi--QuickSettings", "OplusSeparateClickTileManager-->".concat(sbM.toString()));
            }
        }
        if (Intrinsics.areEqual(state.functionName.getMainText(), this.clickQsTileFunctionName)) {
            this.clickQsTileFunctionName = null;
            this.clickQSTileState = null;
        }
    }

    public final void setListening(boolean z) {
        this.qsListening = z;
        this.clickQsTileFunctionName = null;
        this.clickQSTileState = null;
    }
}
