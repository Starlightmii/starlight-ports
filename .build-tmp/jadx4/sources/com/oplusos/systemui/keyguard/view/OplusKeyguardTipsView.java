package com.oplusos.systemui.keyguard.view;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardTipsView extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusKeyguardTipsView";
    private KeyguardMarqueeView keyguardMarqueueView;
    private PictorialMediator pictorialMediator;
    public int type;
    private KeyguardWallpaperInfo wallpaperInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.keyguard.view.OplusKeyguardTipsView$onVisibilityChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusKeyguardTipsView this$0;

        @Override // java.lang.Runnable
        public final void run() {
            PictorialMediator pictorialMediator = this.this$0.pictorialMediator;
            if (pictorialMediator != null) {
                pictorialMediator.brightBottomTxtShow(this.this$0.wallpaperInfo);
            }
        }
    }

    public /* synthetic */ OplusKeyguardTipsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getText() {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            return keyguardMarqueeView.getText();
        }
        return null;
    }

    public final int getType() {
        return this.type;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        syncMarqueeVisibility();
    }

    public final void onConfigChanged(Configuration configuration) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.onConfigChanged(configuration);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131168670);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.setMarginStart(dimensionPixelSize);
            layoutParams2.setMarginEnd(dimensionPixelSize);
            layoutParams2.height = getResources().getDimensionPixelSize(2131168668);
            setLayoutParams(layoutParams2);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.keyguardMarqueueView = (KeyguardMarqueeView) findViewById(2131363585);
        syncMarqueeVisibility();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        KeyguardMarqueeView keyguardMarqueeView;
        super.onLayout(z, i, i2, i3, i4);
        if (getVisibility() != 0 || (keyguardMarqueeView = this.keyguardMarqueueView) == null) {
            return;
        }
        keyguardMarqueeView.refreshTextMarqueeIfNeed();
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (Intrinsics.areEqual(view, this) && i == 0 && (this.wallpaperInfo instanceof PictorialWallpaperInfo)) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(anonymousClass1);
        }
    }

    public final void refreshNoMarqueue(String str) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.refreshNoMarqueue(str);
        }
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.setAlpha(f);
        }
    }

    public final void setHasMore(boolean z) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.setHasMore(z);
        }
    }

    public final void setInverseColorTextShadow(boolean z) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.setInverseColorTextShadow(z);
        }
    }

    public final void setKeyguardWallpaperInfo(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        this.wallpaperInfo = keyguardWallpaperInfo;
    }

    public final void setPictorialMediator(PictorialMediator pictorialMediator) {
        this.pictorialMediator = pictorialMediator;
    }

    public final void setText(int i, String str) {
        this.type = i;
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.setText(str);
        }
        syncMarqueeVisibility();
    }

    public final void setTextColor(int i) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.setTextColor(i);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        syncMarqueeVisibility();
    }

    public final void startMarqueue(String str) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.startMarqueue(str);
        }
    }

    public final void stopMarqueue(String str) {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView != null) {
            keyguardMarqueeView.stopMarqueue(str);
        }
    }

    public final void syncMarqueeVisibility() {
        KeyguardMarqueeView keyguardMarqueeView = this.keyguardMarqueueView;
        if (keyguardMarqueeView == null) {
            return;
        }
        keyguardMarqueeView.setVisibility((getVisibility() != 0 || TextUtils.isEmpty(keyguardMarqueeView.getText())) ? 8 : 0);
    }

    public OplusKeyguardTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusKeyguardTipsView(Context context) {
        this(context, null, 0, 6, null);
    }

    public OplusKeyguardTipsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
