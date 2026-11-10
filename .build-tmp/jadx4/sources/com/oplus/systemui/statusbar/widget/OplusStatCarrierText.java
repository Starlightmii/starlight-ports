package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import com.android.keyguard.CarrierText;
import com.android.keyguard.logging.CarrierTextManagerLogger;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.FontSizeUtils;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatCarrierText extends CarrierText implements CoordAnimIcon {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatCarrierText";
    private CarrierTextManagerLogger logger;
    private final InterestingConfigChanges mInterestingConfigChanges;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusStatCarrierText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInterestingConfigChanges = new InterestingConfigChanges(1073745920);
    }

    public final CarrierTextManagerLogger getLogger() {
        return this.logger;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.TextView*/.onConfigurationChanged(configuration);
        if (this.mInterestingConfigChanges.applyNewConfig(configuration)) {
            FontSizeUtils.updateFontSize(this, 2131173088);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super/*android.widget.TextView*/.onDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*android.widget.TextView*/.onDraw(canvas);
        canvas.restore();
    }

    public final void setLogger(CarrierTextManagerLogger carrierTextManagerLogger) {
        this.logger = carrierTextManagerLogger;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startMarquee() {
        CarrierTextManagerLogger carrierTextManagerLogger;
        if (LogUtil.getNormalDebug() && isShown() && (carrierTextManagerLogger = this.logger) != null) {
            String strValueOf = String.valueOf(hasFocus());
            String strValueOf2 = String.valueOf(isSelected());
            CharSequence text = getText();
            String string = text != null ? text.toString() : null;
            carrierTextManagerLogger.logMarqueStack(strValueOf, strValueOf2, string + Log.getStackTraceString(new Throwable("startMarquee")));
        }
        super/*android.widget.TextView*/.startMarquee();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void stopMarquee() {
        CarrierTextManagerLogger carrierTextManagerLogger;
        if (LogUtil.getNormalDebug() && isShown() && (carrierTextManagerLogger = this.logger) != null) {
            String strValueOf = String.valueOf(hasFocus());
            String strValueOf2 = String.valueOf(isSelected());
            CharSequence text = getText();
            String string = text != null ? text.toString() : null;
            carrierTextManagerLogger.logMarqueStack(strValueOf, strValueOf2, string + Log.getStackTraceString(new Throwable("startMarquee")));
        }
        super/*android.widget.TextView*/.stopMarquee();
    }

    public OplusStatCarrierText(Context context) {
        this(context, null);
    }
}
