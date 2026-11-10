package com.oplus.systemui.notification.row;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomMenuRow;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemCreator {
    public static final NotificationMenuRowItemCreator INSTANCE = new NotificationMenuRowItemCreator();

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.NotificationMenuRowItemCreator$createLottieItem$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Function0 $getLottieResource;
        public /* synthetic */ NotificationMenuRowItemLottieView $lottieAnimationView;

        @Override // java.lang.Runnable
        public final void run() {
            this.$lottieAnimationView.getLottieView().setAnimation((String) this.$getLottieResource.invoke());
        }
    }

    public static final NotificationMenuRow.NotificationMenuItem createImageItem(Context context, int i, int i2, int i3) {
        NotificationMenuRowItemImageView notificationMenuRowItemImageView = new NotificationMenuRowItemImageView(context, null);
        Drawable drawable = context.getDrawable(i2);
        ImageView imageView = notificationMenuRowItemImageView.getImageView();
        imageView.setImageDrawable(drawable);
        OplusCustomMenuRow.Companion.setSettingImageDrawableTint(context, imageView, MaterialColorMode.MATERIAL);
        OplusGutsContent oplusGutsContent = new OplusGutsContent(context);
        NotificationMenuRow.NotificationMenuItem notificationMenuItem = new NotificationMenuRow.NotificationMenuItem(context, context.getString(i), oplusGutsContent, notificationMenuRowItemImageView);
        oplusGutsContent.getContentView().setTag(2131364198, Integer.valueOf(i3));
        return notificationMenuItem;
    }

    public static final NotificationMenuRow.NotificationMenuItem createLottieItem(Context context, int i, Function0 function0, int i2) {
        NotificationMenuRowItemLottieView notificationMenuRowItemLottieView = new NotificationMenuRowItemLottieView(context, null);
        LottieAnimationView lottieView = notificationMenuRowItemLottieView.getLottieView();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$getLottieResource = function0;
        anonymousClass1.$lottieAnimationView = notificationMenuRowItemLottieView;
        VarHandle.storeStoreFence();
        lottieView.post(anonymousClass1);
        OplusGutsContent oplusGutsContent = new OplusGutsContent(context);
        NotificationMenuRow.NotificationMenuItem notificationMenuItem = new NotificationMenuRow.NotificationMenuItem(context, context.getString(i), oplusGutsContent, notificationMenuRowItemLottieView);
        oplusGutsContent.getContentView().setTag(2131364198, Integer.valueOf(i2));
        return notificationMenuItem;
    }

    public static final NotificationMenuRow.NotificationMenuItem createOplusItem(Context context, int i, int i2, int i3, int i4) {
        Resources resources = context.getResources();
        NotificationGuts.GutsContent gutsContent = (NotificationGuts.GutsContent) (i2 != -1 ? LayoutInflater.from(context).inflate(i2, (ViewGroup) null, false) : new OplusGutsContent(context));
        NotificationMenuRow.NotificationMenuItem notificationMenuItem = new NotificationMenuRow.NotificationMenuItem(context, resources.getString(i), gutsContent, i3);
        View menuView = notificationMenuItem.getMenuView();
        ImageView imageView = menuView instanceof ImageView ? (ImageView) menuView : null;
        if (imageView != null) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setAlpha(0.0f);
        }
        gutsContent.getContentView().setTag(2131364198, Integer.valueOf(i4));
        return notificationMenuItem;
    }

    public static final boolean isSwipeToEnd(float f, View view) {
        int layoutDirection = view.getLayoutDirection();
        return (layoutDirection == 0 && f > 0.0f) || (layoutDirection == 1 && f < 0.0f);
    }
}
