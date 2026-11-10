package com.oplus.systemui.notification.row;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.util.DimensionKt;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.base.radius.RadiusParams;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowMetaBallController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MenuRowMetaBallController";
    private final Map<View, MetaBallRelation> ballMap = new LinkedHashMap();
    private final Context context;
    private MetaBallBlurDrawable currentCardDrawable;
    public float lastTranslationX;
    private final NotificationMenuRowProvider provider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MetaBallRelation {
        private final MetaBallBlurDrawable.MetaBall ballCardToMenu;
        private final MetaBallBlurDrawable.MetaBall ballMenuToCard;
        private final MetaBallBlurDrawable menuItemDrawable;

        public MetaBallRelation(MetaBallBlurDrawable.MetaBall metaBall, MetaBallBlurDrawable.MetaBall metaBall2, MetaBallBlurDrawable metaBallBlurDrawable) {
            this.ballCardToMenu = metaBall;
            this.ballMenuToCard = metaBall2;
            this.menuItemDrawable = metaBallBlurDrawable;
        }

        public final MetaBallBlurDrawable.MetaBall component1() {
            return this.ballCardToMenu;
        }

        public final MetaBallBlurDrawable.MetaBall component2() {
            return this.ballMenuToCard;
        }

        public final MetaBallBlurDrawable component3() {
            return this.menuItemDrawable;
        }

        public final MetaBallRelation copy(MetaBallBlurDrawable.MetaBall metaBall, MetaBallBlurDrawable.MetaBall metaBall2, MetaBallBlurDrawable metaBallBlurDrawable) {
            return new MetaBallRelation(metaBall, metaBall2, metaBallBlurDrawable);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MetaBallRelation)) {
                return false;
            }
            MetaBallRelation metaBallRelation = (MetaBallRelation) obj;
            return Intrinsics.areEqual(this.ballCardToMenu, metaBallRelation.ballCardToMenu) && Intrinsics.areEqual(this.ballMenuToCard, metaBallRelation.ballMenuToCard) && Intrinsics.areEqual(this.menuItemDrawable, metaBallRelation.menuItemDrawable);
        }

        public final MetaBallBlurDrawable.MetaBall getBallCardToMenu() {
            return this.ballCardToMenu;
        }

        public final MetaBallBlurDrawable.MetaBall getBallMenuToCard() {
            return this.ballMenuToCard;
        }

        public final MetaBallBlurDrawable getMenuItemDrawable() {
            return this.menuItemDrawable;
        }

        public final int hashCode() {
            return this.menuItemDrawable.hashCode() + ((this.ballMenuToCard.hashCode() + (this.ballCardToMenu.hashCode() * 31)) * 31);
        }

        public String toString() {
            return NotificationMenuRowLog.simpleNameAndHash(this.menuItemDrawable) + "_" + NotificationMenuRowLog.simpleNameAndHash(this.ballCardToMenu) + "_" + NotificationMenuRowLog.simpleNameAndHash(this.ballMenuToCard) + "_";
        }
    }

    public NotificationMenuRowMetaBallController(NotificationMenuRowProvider notificationMenuRowProvider, Context context) {
        this.provider = notificationMenuRowProvider;
        this.context = context;
    }

    private final int getBlendRange() {
        return DeviceUtil.dp2px(this.context, NotificationMenuRowParams.AnimateParamFour.INSTANCE.getBlendRange().get().floatValue());
    }

    private final float getBlendShape() {
        return getBlendRange();
    }

    private final float getBlurCornerRadius() {
        return DimensionKt.dpToPx(RadiusParams.INSTANCE.getCARD_MENU_CONER().get(), this.context);
    }

    private final MetaBallBlurDrawable getCardBlurDrawable() {
        return this.provider.provideCardMetaBallDrawable();
    }

    private final int getCardHeight() {
        return this.provider.provideCardActualHeight();
    }

    private final float getCardTranslationX() {
        return this.provider.provideCardTranslationX();
    }

    private final int getCardWidth() {
        return this.provider.provideCardWidth();
    }

    private final View getNotificationRowView() {
        return this.provider.provideCardRowAsView();
    }

    public static MetaBallBlurDrawable requireMetaBallDrawable(Drawable drawable) {
        MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(drawable);
        NotificationMenuRowLog.e("MenuRowMetaBallController sourceDrawable = " + NotificationMenuRowLog.simpleNameAndHash(metaBallBlurDrawableTryGetMetaBallBlurDrawable));
        if (metaBallBlurDrawableTryGetMetaBallBlurDrawable != null) {
            return metaBallBlurDrawableTryGetMetaBallBlurDrawable;
        }
        NotificationMenuRowLog.e("MenuRowMetaBallController, sourceDrawable type error !!");
        return null;
    }

    private final void setCurrentCardDrawable(MetaBallBlurDrawable metaBallBlurDrawable) {
        if (metaBallBlurDrawable != null) {
            metaBallBlurDrawable.setEnableBlurShader(true);
            NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawable, getBlendRange(), "NotificationMenuRowMetaBallController SET currentCardDrawable");
            metaBallBlurDrawable.setMainBallIndex(1);
            metaBallBlurDrawable.setBlendMode(0);
            metaBallBlurDrawable.setCornerParams(SmoothRoundEx.INSTANCE.createCornerParams(getBlurCornerRadius(), true));
        }
        this.currentCardDrawable = metaBallBlurDrawable;
    }

    public final boolean addMetaBallData(int i, View view) {
        MetaBallBlurDrawable metaBallBlurDrawableRequireMetaBallDrawable;
        NotificationMenuRowLog.i("MenuRowMetaBallController START ," + NotificationMenuRowLog.simpleNameAndHash(getNotificationRowView()) + OplusPreventModeData.SEPARATOR + NotificationMenuRowLog.simpleNameAndHash(view));
        MetaBallBlurDrawable metaBallBlurDrawableRequireMetaBallDrawable2 = requireMetaBallDrawable(view.getBackground());
        if (metaBallBlurDrawableRequireMetaBallDrawable2 != null && (metaBallBlurDrawableRequireMetaBallDrawable = requireMetaBallDrawable(getCardBlurDrawable())) != null) {
            MetaBallBlurDrawable metaBallBlurDrawable = this.currentCardDrawable;
            MetaBallRelation metaBallRelation = null;
            if (metaBallBlurDrawableRequireMetaBallDrawable != metaBallBlurDrawable) {
                if (metaBallBlurDrawable != null) {
                    metaBallBlurDrawable.clearMetaBalls();
                }
                metaBallBlurDrawableRequireMetaBallDrawable.clearMetaBalls();
                setCurrentCardDrawable(metaBallBlurDrawableRequireMetaBallDrawable);
                metaBallBlurDrawableRequireMetaBallDrawable2.clearMetaBalls();
                Iterator<Map.Entry<View, MetaBallRelation>> it = this.ballMap.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().getMenuItemDrawable().clearMetaBalls();
                }
                this.ballMap.clear();
            } else {
                MetaBallRelation metaBallRelation2 = this.ballMap.get(view);
                if (metaBallRelation2 == null) {
                    metaBallBlurDrawableRequireMetaBallDrawable2.clearMetaBalls();
                } else if (Intrinsics.areEqual(metaBallRelation2.getMenuItemDrawable(), metaBallBlurDrawableRequireMetaBallDrawable2)) {
                    metaBallRelation = metaBallRelation2;
                } else {
                    MetaBallBlurDrawable metaBallBlurDrawable2 = this.currentCardDrawable;
                    if (metaBallBlurDrawable2 != null) {
                        metaBallBlurDrawable2.removeMetaBall(metaBallRelation2.getBallCardToMenu());
                    }
                    metaBallRelation2.getMenuItemDrawable().clearMetaBalls();
                    metaBallBlurDrawableRequireMetaBallDrawable2.clearMetaBalls();
                    this.ballMap.remove(view);
                }
            }
            if (metaBallRelation != null) {
                NotificationMenuRowLog.i("MenuRowMetaBallController returned  cached ball data = " + metaBallRelation);
                return true;
            }
            int i2 = i + 2;
            float[] relativePosition = getRelativePosition(getCardTranslationX(), view);
            PointF pointF = new PointF(relativePosition[0], relativePosition[1]);
            PointF pointF2 = new PointF(view.getWidth(), view.getHeight());
            float blurCornerRadius = getBlurCornerRadius();
            SmoothRoundEx smoothRoundEx = SmoothRoundEx.INSTANCE;
            float defaultWeight = smoothRoundEx.getDefaultWeight();
            float blendShape = getBlendShape();
            MetaBallBlurDrawable.Companion companion = MetaBallBlurDrawable.Companion;
            MetaBallBlurDrawable.MetaBall metaBallAddMetaBall = metaBallBlurDrawableRequireMetaBallDrawable.addMetaBall(i2, pointF, pointF2, blurCornerRadius, defaultWeight, blendShape, 1.2f, 2.0f);
            if (metaBallAddMetaBall != null) {
                metaBallBlurDrawableRequireMetaBallDrawable2.clearMetaBalls();
                metaBallBlurDrawableRequireMetaBallDrawable2.setEnableBlurShader(true);
                NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableRequireMetaBallDrawable2, getBlendRange(), "createMenu2Card");
                metaBallBlurDrawableRequireMetaBallDrawable2.setMainBallIndex(i2);
                metaBallBlurDrawableRequireMetaBallDrawable2.setBlendMode(0);
                metaBallBlurDrawableRequireMetaBallDrawable2.setCornerParams(smoothRoundEx.createMetaBallsCornerParams(getBlurCornerRadius()));
                MetaBallBlurDrawable.MetaBall metaBallAddMetaBall2 = metaBallBlurDrawableRequireMetaBallDrawable2.addMetaBall(1, new PointF(-relativePosition[0], -relativePosition[1]), new PointF(getCardWidth() + getCardTranslationX(), getCardHeight()), getBlurCornerRadius(), smoothRoundEx.getDefaultWeight(), getBlendShape(), 1.2f, 2.0f);
                if (metaBallAddMetaBall2 != null) {
                    MetaBallRelation metaBallRelation3 = new MetaBallRelation(metaBallAddMetaBall, metaBallAddMetaBall2, metaBallBlurDrawableRequireMetaBallDrawable2);
                    this.ballMap.put(view, metaBallRelation3);
                    NotificationMenuRowLog.i("MenuRowMetaBallController finish , cached ball data = " + metaBallRelation3);
                    return true;
                }
            }
        }
        return false;
    }

    public final void clearAllMetaBalls() {
        MetaBallBlurDrawable metaBallBlurDrawable = this.currentCardDrawable;
        if (metaBallBlurDrawable != null) {
            metaBallBlurDrawable.clearMetaBalls();
        }
        setCurrentCardDrawable(null);
        Iterator<Map.Entry<View, MetaBallRelation>> it = this.ballMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().getMenuItemDrawable().clearMetaBalls();
        }
        this.ballMap.clear();
    }

    public final float getLastTranslationX() {
        return this.lastTranslationX;
    }

    public final float[] getRelativePosition(float f, View view) {
        boolean zIsLayoutRtl = view.isLayoutRtl();
        int[] iArr = new int[2];
        getNotificationRowView().getLocationOnScreen(iArr);
        float cardWidth = zIsLayoutRtl ? getCardWidth() - f : getCardWidth() + f;
        int cardHeight = getCardHeight();
        float cardWidth2 = zIsLayoutRtl ? (iArr[0] + getCardWidth()) - (cardWidth / 2.0f) : iArr[0] + (cardWidth / 2.0f);
        float f2 = (cardHeight / 2.0f) + iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        return new float[]{((view.getWidth() / 2.0f) + iArr2[0]) - cardWidth2, ((view.getHeight() / 2.0f) + iArr2[1]) - f2};
    }

    public final void setLastTranslationX(float f) {
        this.lastTranslationX = f;
    }

    public final void updateMetaBallPosition(float f) {
        boolean zIsLayoutRtl = getNotificationRowView().isLayoutRtl();
        int cardHeight = getCardHeight();
        this.lastTranslationX = f;
        for (Map.Entry<View, MetaBallRelation> entry : this.ballMap.entrySet()) {
            View key = entry.getKey();
            MetaBallRelation value = entry.getValue();
            Drawable background = key.getBackground();
            if (background != null) {
                float fHeight = background.getBounds().height() / key.getHeight();
                float[] relativePosition = getRelativePosition(f, key);
                value.getBallCardToMenu().updatePosition(relativePosition[0], relativePosition[1]);
                value.getBallCardToMenu().updateSize(key.getWidth() * fHeight, key.getHeight() * fHeight);
                value.getBallMenuToCard().updatePosition(-relativePosition[0], -relativePosition[1]);
                value.getBallMenuToCard().updateSize(zIsLayoutRtl ? getCardWidth() - f : getCardWidth() + f, cardHeight);
            }
        }
    }

    public final void updateMetaBallPositionNoMeta(float f) {
        boolean zIsLayoutRtl = getNotificationRowView().isLayoutRtl();
        int cardHeight = getCardHeight();
        this.lastTranslationX = f;
        for (Map.Entry<View, MetaBallRelation> entry : this.ballMap.entrySet()) {
            View key = entry.getKey();
            MetaBallRelation value = entry.getValue();
            float[] relativePosition = getRelativePosition(f, key);
            value.getBallCardToMenu().updatePosition(relativePosition[0] + 10000.0f, relativePosition[1]);
            value.getBallCardToMenu().updateSize(key.getWidth(), key.getHeight());
            value.getBallMenuToCard().updatePosition((-relativePosition[0]) - 10000.0f, -relativePosition[1]);
            value.getBallMenuToCard().updateSize(zIsLayoutRtl ? getCardWidth() - f : getCardWidth() + f, cardHeight);
        }
    }
}
