package com.oplus.systemui.statusbar.notification.stack;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderViewEx;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.TextShadowUtils;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.text.HexExtensionsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SectionHeaderViewExImpl extends SectionHeaderViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SectionHeaderViewExImpl";
    private WeakReference<ImageView> clearButton;
    private final Context context;
    private final CoroutineScope coroutineScope;
    public final boolean isMiddleOrHighAnimLevel;
    private WeakReference<TextView> labelView;
    public float lastSectionHeaderAlpha = -1.0f;
    private final Lazy notificationMaterialColorEx;
    private final Lazy oplusQsColorManager;
    private final OplusQsColorManagerEx.QsColorStateObserver qsColorStateObserver;
    private Job reverseColorJob;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SectionHeaderViewExImpl(Lazy lazy, Context context, CoroutineScope coroutineScope, Lazy lazy2) {
        this.oplusQsColorManager = lazy;
        this.context = context;
        this.coroutineScope = coroutineScope;
        this.notificationMaterialColorEx = lazy2;
        this.isMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        SectionHeaderViewExImpl$qsColorStateObserver$1 sectionHeaderViewExImpl$qsColorStateObserver$1 = new SectionHeaderViewExImpl$qsColorStateObserver$1();
        sectionHeaderViewExImpl$qsColorStateObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsColorStateObserver = sectionHeaderViewExImpl$qsColorStateObserver$1;
    }

    public void bindContents(TextView textView, ImageView imageView) {
        this.labelView = new WeakReference<>(textView);
        this.clearButton = new WeakReference<>(imageView);
        updateSectionHeaderColor();
    }

    public void checkIfNeedUpdateSectionHeaderBlurRadius(View view, float f) {
        float f2 = this.lastSectionHeaderAlpha;
        if (f == f2) {
            return;
        }
        if (f == 0.0f || f == 1.0f || Math.abs(f - f2) >= 0.003921569f) {
            this.lastSectionHeaderAlpha = f;
            if (this.isMiddleOrHighAnimLevel) {
                if (f <= 0.0f) {
                    view.setRenderEffect(null);
                } else {
                    float f3 = (1.0f - f) * 30.0f;
                    view.setRenderEffect(RenderEffect.createBlurEffect(f3, f3, Shader.TileMode.CLAMP));
                }
            }
        }
    }

    public final void onAttachedToWindow() {
        QsColorUtil.getQsColorState();
        ((OplusQsColorManager) this.oplusQsColorManager.get()).addQsColorStateObserver(this.qsColorStateObserver);
        Job job = this.reverseColorJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.reverseColorJob = BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new onAttachedToWindow.1(this, (Continuation) null), 2);
    }

    public final void onDetachedFromWindow() {
        ((OplusQsColorManager) this.oplusQsColorManager.get()).removeQsColorStateObserver(this.qsColorStateObserver);
        Job job = this.reverseColorJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.reverseColorJob = null;
    }

    public void setForegroundColor(TextView textView, ImageView imageView, int i, Context context) {
        updateSectionHeaderColor();
    }

    public final void updateSectionHeaderColor() {
        WeakReference<TextView> weakReference = this.labelView;
        TextView textView = weakReference != null ? weakReference.get() : null;
        if (textView == null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "labelView is null", null, 8);
            return;
        }
        WeakReference<ImageView> weakReference2 = this.clearButton;
        ImageView imageView = weakReference2 != null ? weakReference2.get() : null;
        boolean zBooleanValue = ((Boolean) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).isKeyguardReverseColor().getValue()).booleanValue();
        Context context = this.context;
        if (zBooleanValue) {
            int color = context.getColor(2131102865);
            int color2 = this.context.getColor(2131102863);
            textView.setTextColor(color);
            TextShadowUtils.setInverseColorTextShadow(textView, true, 18.0f);
            if (imageView != null) {
                imageView.setImageTintList(ColorStateList.valueOf(color2));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("updateSectionHeaderColor reverse text=", HexExtensionsKt.toHexString$default(color), "SectionHeaderViewExImpl-->", "SystemUi--Notification");
            }
        } else {
            boolean zIsGlobalThemeApplied = NotificationColorUtil.isGlobalThemeApplied(context);
            Context context2 = this.context;
            int color3 = zIsGlobalThemeApplied ? context2.getColor(2131103705) : context2.getColor(2131102864);
            boolean zIsGlobalThemeApplied2 = NotificationColorUtil.isGlobalThemeApplied(this.context);
            Context context3 = this.context;
            int color4 = zIsGlobalThemeApplied2 ? context3.getColor(2131103705) : context3.getColor(2131102862);
            textView.setTextColor(color3);
            TextShadowUtils.setInverseColorTextShadow(textView, false, 18.0f);
            if (imageView != null) {
                imageView.setImageTintList(ColorStateList.valueOf(color4));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("updateSectionHeaderColor normal text=", HexExtensionsKt.toHexString$default(color3), "SectionHeaderViewExImpl-->", "SystemUi--Notification");
            }
        }
        SectionHeaderClearButtonImageView sectionHeaderClearButtonImageView = imageView instanceof SectionHeaderClearButtonImageView ? (SectionHeaderClearButtonImageView) imageView : null;
        if (sectionHeaderClearButtonImageView != null) {
            sectionHeaderClearButtonImageView.refreshMaterialBackground();
        }
    }
}
