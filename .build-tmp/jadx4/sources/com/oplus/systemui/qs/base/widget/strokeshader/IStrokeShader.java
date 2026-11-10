package com.oplus.systemui.qs.base.widget.strokeshader;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IStrokeShader {
    default void drawShader(Canvas canvas, Function0 function0) {
    }

    default GradientStrokeLineParams getStrokeLineParams() {
        return null;
    }

    default void setCorner(GradientStrokeShader.CornerType cornerType, float f, float f2) {
    }

    default void setStrokeBackground(Function0 function0) {
    }

    default void setStrokeEnable(boolean z) {
    }

    default void updatePaddingRect(Rect rect) {
    }
}
