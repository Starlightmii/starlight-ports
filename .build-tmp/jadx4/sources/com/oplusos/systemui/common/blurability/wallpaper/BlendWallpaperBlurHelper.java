package com.oplusos.systemui.common.blurability.wallpaper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.slice.widget.RowView$;
import com.oplus.posteffect.drawable.BlendDrawable;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BlendWallpaperBlurHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BlendWallpaperBlurHelper";
    private BlendWallpaperBlurDrawable blendDrawable;
    public boolean blurEnabled;
    private final Function1 excludeRules;
    private final View hostView;
    private Function1 onBlendDrawableCreated;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BlendWallpaperBlurHelper(View view, Function1 function1) {
        this.hostView = view;
        this.excludeRules = function1;
    }

    public final void ensureBlendDrawable() {
        Context applicationContext;
        if (this.blendDrawable == null && this.hostView.isAttachedToWindow()) {
            Function1 function1 = this.excludeRules;
            if (function1 == null || !((Boolean) function1.invoke(this.hostView)).booleanValue()) {
                Context context = this.hostView.getContext();
                if (context == null || (applicationContext = context.getApplicationContext()) == null) {
                    applicationContext = this.hostView.getViewRootImpl().mContext;
                }
                if (this.blendDrawable == null && this.blurEnabled && this.hostView.isAttachedToWindow()) {
                    applicationContext.getClass();
                    BlendWallpaperBlurDrawable blendWallpaperBlurDrawable = new BlendWallpaperBlurDrawable(new BlendDrawable(applicationContext, true), this.hostView);
                    this.blendDrawable = blendWallpaperBlurDrawable;
                    Function1 function2 = this.onBlendDrawableCreated;
                    if (function2 != null) {
                        function2.invoke(blendWallpaperBlurDrawable);
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "BlendWallpaperBlurHelper-->".concat("[ensureBlurDrawable] " + DebugToolKt.getName(this.hostView, false) + " blendDrawable=" + this.blendDrawable + " created! onBlendDrawableCreated=" + this.onBlendDrawableCreated));
                }
            }
        }
    }

    public final BlendWallpaperBlurDrawable getBlendDrawable() {
        return this.blendDrawable;
    }

    public final boolean getBlurEnabled() {
        return this.blurEnabled;
    }

    public final Function1 getOnBlendDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.onBlendDrawableCreated;
    }

    public final void setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(boolean z) {
        if (this.blurEnabled != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("[setBlurEnabled] ", ", this: ", z);
                sbM.append(this.hostView);
                Log.d("SystemUi--Common", "BlendWallpaperBlurHelper-->".concat(sbM.toString()));
            }
            this.blurEnabled = z;
            if (z) {
                ensureBlendDrawable();
            } else {
                this.blendDrawable = null;
            }
        }
    }

    public final void setOnBlendDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(Function1 function1) {
        this.onBlendDrawableCreated = function1;
    }
}
