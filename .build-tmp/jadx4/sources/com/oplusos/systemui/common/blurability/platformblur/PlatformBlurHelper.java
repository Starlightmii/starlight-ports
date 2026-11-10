package com.oplusos.systemui.common.blurability.platformblur;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import androidx.slice.widget.RowView$;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlatformBlurHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PlatformBlurHelper";
    private final BlurConfig blurConfig;
    private PlatformBlurDrawable blurDrawable;
    public boolean blurEnabled;
    private final Function1 excludeRules;
    private final View hostView;
    private Function1 onBlurDrawableCreated;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PlatformBlurHelper(View view, Function1 function1, BlurConfig blurConfig) {
        this.hostView = view;
        this.excludeRules = function1;
        this.blurConfig = blurConfig;
    }

    public final void ensureBlurDrawable() {
        Function1 function1;
        SurfaceControl surfaceControl;
        Context applicationContext;
        MetaBallBlurDrawable blurDrawable;
        if (this.blurDrawable == null && this.hostView.isAttachedToWindow()) {
            Function1 function2 = this.excludeRules;
            if (function2 == null || !((Boolean) function2.invoke(this.hostView)).booleanValue()) {
                ViewRootImpl viewRootImpl = this.hostView.getViewRootImpl();
                PlatformBlurDrawable platformBlurDrawable = null;
                if (viewRootImpl != null && (surfaceControl = viewRootImpl.getSurfaceControl()) != null && surfaceControl.isValid()) {
                    Context context = this.hostView.getContext();
                    if (context == null || (applicationContext = context.getApplicationContext()) == null) {
                        applicationContext = this.hostView.getViewRootImpl().mContext;
                    }
                    BlurConfig blurConfig = this.blurConfig;
                    if (blurConfig == null || !blurConfig.isSupportMetaBall) {
                        applicationContext.getClass();
                        blurDrawable = new BlurDrawable(applicationContext, surfaceControl);
                    } else {
                        applicationContext.getClass();
                        blurDrawable = new MetaBallBlurDrawable(surfaceControl, applicationContext, 0, 124);
                    }
                    blurDrawable.setPositionDrawThresholdPx(4);
                    PlatformBlurHelper$$ExternalSyntheticLambda0 platformBlurHelper$$ExternalSyntheticLambda0 = new PlatformBlurHelper$$ExternalSyntheticLambda0();
                    platformBlurHelper$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    platformBlurDrawable = new PlatformBlurDrawable(blurDrawable, platformBlurHelper$$ExternalSyntheticLambda0);
                }
                this.blurDrawable = platformBlurDrawable;
                if (platformBlurDrawable != null && (function1 = this.onBlurDrawableCreated) != null) {
                    function1.invoke(platformBlurDrawable);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "PlatformBlurHelper-->".concat("[ensureBlurDrawable] " + DebugToolKt.getName(this.hostView, false) + " blurDrawable=" + this.blurDrawable + " created! onBlurDrawableCreated=" + this.onBlurDrawableCreated));
                }
            }
        }
    }

    public final PlatformBlurDrawable getBlurDrawable() {
        return this.blurDrawable;
    }

    public final boolean getBlurEnabled() {
        return this.blurEnabled;
    }

    public final Function1 getOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.onBlurDrawableCreated;
    }

    public final void setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(boolean z) {
        if (this.blurEnabled != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("[setBlurEnabled] ", ", this: ", z);
                sbM.append(this.hostView);
                Log.d("SystemUi--Common", "PlatformBlurHelper-->".concat(sbM.toString()));
            }
            this.blurEnabled = z;
            if (z) {
                ensureBlurDrawable();
            } else {
                this.blurDrawable = null;
            }
        }
    }

    public final void setOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(Function1 function1) {
        this.onBlurDrawableCreated = function1;
    }

    public String toString() {
        return "PlatformBlurHelper(blurDrawable=" + this.blurDrawable + ')';
    }
}
