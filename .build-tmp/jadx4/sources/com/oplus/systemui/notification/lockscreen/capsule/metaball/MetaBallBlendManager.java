package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.graphics.PointF;
import android.graphics.Rect;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleBackgroundView;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaBallBlendManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MetaBallBlendManager";
    private MetaBallBlurDrawable.MetaBall ballFocusToNext;
    private MetaBallBlurDrawable.MetaBall ballNextToFocus;
    private CapsuleMetaBallController.MetaBallViewContext metaBallContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void clearMetaBallResource(String str) {
        exitMetaBallBlendState();
        if (this.metaBallContext != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("clear metaBalls: reason=", str, "MetaBallBlendManager-->", "SystemUi--Notification");
        }
        this.metaBallContext = null;
    }

    public final void exitMetaBallBlendState() {
        MetaBallBlurDrawable nextCardBgDrawable;
        MetaBallBlurDrawable baseCardBgDrawable;
        MetaBallBlurDrawable nextCardBgDrawable2;
        MetaBallBlurDrawable baseCardBgDrawable2;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext = this.metaBallContext;
        if (metaBallViewContext != null && (baseCardBgDrawable2 = metaBallViewContext.getBaseCardBgDrawable()) != null) {
            NotificationMenuRowProviderKt.setBlendRange(baseCardBgDrawable2, 0, "MetaBallBlendManager exitMetaBallBlendState");
        }
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext2 = this.metaBallContext;
        if (metaBallViewContext2 != null && (nextCardBgDrawable2 = metaBallViewContext2.getNextCardBgDrawable()) != null) {
            NotificationMenuRowProviderKt.setBlendRange(nextCardBgDrawable2, 0, "MetaBallBlendManager exitMetaBallBlendState");
        }
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext3 = this.metaBallContext;
        if (metaBallViewContext3 != null && (baseCardBgDrawable = metaBallViewContext3.getBaseCardBgDrawable()) != null) {
            baseCardBgDrawable.clearMetaBalls();
        }
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext4 = this.metaBallContext;
        if (metaBallViewContext4 != null && (nextCardBgDrawable = metaBallViewContext4.getNextCardBgDrawable()) != null) {
            nextCardBgDrawable.clearMetaBalls();
        }
        this.ballFocusToNext = null;
        this.ballNextToFocus = null;
    }

    public final void handleMetaBallBlending(Rect rect, Rect rect2, float f, float f2) {
        MetaBallBlurDrawable nextCardBgDrawable;
        MetaBallBlurDrawable baseCardBgDrawable;
        float fCoerceIn;
        MetaBallBlurDrawable nextCardBgDrawable2;
        MetaBallBlurDrawable baseCardBgDrawable2;
        CapsuleBackgroundView baseCardBgView;
        CapsuleBackgroundView nextCardBgView;
        if (this.ballFocusToNext == null || this.ballNextToFocus == null) {
            CapsuleMetaBallController.MetaBallViewContext metaBallViewContext = this.metaBallContext;
            MetaBallBlurDrawable baseCardBgDrawable3 = metaBallViewContext != null ? metaBallViewContext.getBaseCardBgDrawable() : null;
            CapsuleMetaBallController.MetaBallViewContext metaBallViewContext2 = this.metaBallContext;
            MetaBallBlurDrawable nextCardBgDrawable3 = metaBallViewContext2 != null ? metaBallViewContext2.getNextCardBgDrawable() : null;
            if (nextCardBgDrawable3 != null) {
                nextCardBgDrawable3.setMainBallIndex(2);
            }
            if (baseCardBgDrawable3 != null) {
                baseCardBgDrawable3.setMainBallIndex(1);
            }
            CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
            float defaultRadius = capsuleConfig.getDefaultRadius();
            if (baseCardBgDrawable3 != null) {
                baseCardBgDrawable3.setCornerParams(SmoothRoundEx.INSTANCE.createCapsuleCornerParams(defaultRadius));
            }
            if (nextCardBgDrawable3 != null) {
                nextCardBgDrawable3.setCornerParams(SmoothRoundEx.INSTANCE.createCapsuleCornerParams(defaultRadius));
            }
            float[] fArr = {(f - f2) + (rect.centerX() - rect2.centerX()), rect.centerY() - rect2.centerY()};
            float defaultRadius2 = capsuleConfig.getDefaultRadius();
            CapsuleMetaBallController.MetaBallViewContext metaBallViewContext3 = this.metaBallContext;
            if (metaBallViewContext3 != null && (baseCardBgDrawable = metaBallViewContext3.getBaseCardBgDrawable()) != null) {
                PointF pointF = new PointF(fArr[0], fArr[1]);
                PointF pointF2 = new PointF(rect.width(), rect.height());
                MetaBallBlurDrawable.Companion companion = MetaBallBlurDrawable.Companion;
                this.ballFocusToNext = baseCardBgDrawable.addMetaBall(2, pointF, pointF2, defaultRadius2, 1.0f, 1.0f, 1.2f, 2.0f);
            }
            CapsuleMetaBallController.MetaBallViewContext metaBallViewContext4 = this.metaBallContext;
            if (metaBallViewContext4 != null && (nextCardBgDrawable = metaBallViewContext4.getNextCardBgDrawable()) != null) {
                PointF pointF3 = new PointF(-fArr[0], fArr[1]);
                PointF pointF4 = new PointF(rect2.width(), rect2.height());
                MetaBallBlurDrawable.Companion companion2 = MetaBallBlurDrawable.Companion;
                this.ballNextToFocus = nextCardBgDrawable.addMetaBall(1, pointF3, pointF4, defaultRadius2, 1.0f, 1.0f, 1.2f, 2.0f);
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m("addMetaBallInner: pos:[", fArr[0], ", ", fArr[1], "]"), null, 8);
        } else {
            float[] fArr2 = {(f - f2) + (rect.centerX() - rect2.centerX()), rect.centerY() - rect2.centerY()};
            MetaBallBlurDrawable.MetaBall metaBall = this.ballFocusToNext;
            if (metaBall != null) {
                metaBall.updatePosition(fArr2[0], fArr2[1]);
            }
            MetaBallBlurDrawable.MetaBall metaBall2 = this.ballFocusToNext;
            if (metaBall2 != null) {
                metaBall2.updateSize(rect.width(), rect.height());
            }
            MetaBallBlurDrawable.MetaBall metaBall3 = this.ballNextToFocus;
            if (metaBall3 != null) {
                metaBall3.updatePosition(-fArr2[0], fArr2[1]);
            }
            MetaBallBlurDrawable.MetaBall metaBall4 = this.ballNextToFocus;
            if (metaBall4 != null) {
                metaBall4.updateSize(rect2.width(), rect2.height());
            }
        }
        CapsuleConfig capsuleConfig2 = CapsuleConfig.INSTANCE;
        float itemWidthMultiCard = capsuleConfig2.getItemWidthMultiCard() * 0.12f;
        float fCoerceIn2 = ((float) rect.width()) <= ((float) rect.height()) + itemWidthMultiCard ? RangesKt___RangesKt.coerceIn((rect.width() - rect.height()) / itemWidthMultiCard, 0.0f, 1.0f) : 1.0f;
        float fCoerceIn3 = ((float) rect2.width()) <= ((float) rect2.height()) + itemWidthMultiCard ? RangesKt___RangesKt.coerceIn((rect2.width() - rect2.height()) / itemWidthMultiCard, 0.0f, 1.0f) : 1.0f;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext5 = this.metaBallContext;
        if (metaBallViewContext5 != null && (nextCardBgView = metaBallViewContext5.getNextCardBgView()) != null) {
            CapsuleBackgroundView.Companion companion3 = CapsuleBackgroundView.Companion;
            nextCardBgView.setStrokeAndShadowAlpha(fCoerceIn2, 1.0f, null);
        }
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext6 = this.metaBallContext;
        if (metaBallViewContext6 != null && (baseCardBgView = metaBallViewContext6.getBaseCardBgView()) != null) {
            CapsuleBackgroundView.Companion companion4 = CapsuleBackgroundView.Companion;
            baseCardBgView.setStrokeAndShadowAlpha(fCoerceIn3, 1.0f, null);
        }
        float itemWidthMultiCard2 = capsuleConfig2.getItemWidthMultiCard() * 0.12f;
        if (rect.width() <= rect.height() + itemWidthMultiCard2) {
            fCoerceIn = RangesKt___RangesKt.coerceIn((rect.width() - rect.height()) / itemWidthMultiCard2, 0.0f, 1.0f);
        } else {
            fCoerceIn = ((float) rect2.width()) <= ((float) rect2.height()) + itemWidthMultiCard2 ? RangesKt___RangesKt.coerceIn((rect2.width() - rect2.height()) / itemWidthMultiCard2, 0.0f, 1.0f) : 1.0f;
        }
        float f3 = (fCoerceIn * 50.0f) + 1.0f;
        float f4 = f3 <= 50.0f ? f3 : 50.0f;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext7 = this.metaBallContext;
        if (metaBallViewContext7 != null && (baseCardBgDrawable2 = metaBallViewContext7.getBaseCardBgDrawable()) != null) {
            NotificationMenuRowProviderKt.setBlendRange(baseCardBgDrawable2, (int) f4, "MetaBallBlendManager updateMetaBallBlendRange");
        }
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext8 = this.metaBallContext;
        if (metaBallViewContext8 != null && (nextCardBgDrawable2 = metaBallViewContext8.getNextCardBgDrawable()) != null) {
            NotificationMenuRowProviderKt.setBlendRange(nextCardBgDrawable2, (int) f4, "MetaBallBlendManager updateMetaBallBlendRange");
        }
        MetaBallBlurDrawable.MetaBall metaBall5 = this.ballFocusToNext;
        if (metaBall5 != null) {
            MetaBallBlurDrawable.MetaBall.updateBlendShape$default(metaBall5, f4);
        }
        MetaBallBlurDrawable.MetaBall metaBall6 = this.ballNextToFocus;
        if (metaBall6 != null) {
            MetaBallBlurDrawable.MetaBall.updateBlendShape$default(metaBall6, f4);
        }
    }

    public final void initMetaBallViewContext(CapsuleMetaBallController.MetaBallViewContext metaBallViewContext) {
        this.metaBallContext = metaBallViewContext;
    }

    public final boolean isBindState() {
        return (this.ballFocusToNext == null || this.ballNextToFocus == null) ? false : true;
    }
}
