package com.oplus.systemui.statusbar.policy;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.ViewRootImpl;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.window.OnBackInvokedCallback;
import android.window.WindowOnBackInvokedDispatcher;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.systemui.statusbar.policy.RemoteInputViewExt;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoteInputViewExtImpl extends RemoteInputViewExt implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String REMOTE_INPUI_VIEW = "RemoteInputView";
    private static final String TAG = "RemoteInputViewExtImpl";
    private final Context context;
    private final Lazy notificationMaterialColorEx$delegate = LazyKt__LazyJVMKt.lazy(new RemoteInputViewExtImpl$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public RemoteInputViewExtImpl(Context context) {
        this.context = context;
    }

    private final NotificationMaterialColorEx getNotificationMaterialColorEx() {
        return (NotificationMaterialColorEx) this.notificationMaterialColorEx$delegate.getValue();
    }

    public void checkRegister(RemoteInputView remoteInputView) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean zIsShown = remoteInputView.isShown();
            int windowVisibility = remoteInputView.getWindowVisibility();
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("RemoteInputViewExtImpl-->", "SystemUi--Notification", OplusLockIconView$.ExternalSyntheticOutline0.m(" isShown: ", windowVisibility, ", windowVisibility: ", ", isAttachedToWindow: ", zIsShown), remoteInputView.isAttachedToWindow());
        }
        if (remoteInputView.isShown() && remoteInputView.getWindowVisibility() == 0 && remoteInputView.isAttachedToWindow()) {
            remoteInputView.registerBackCallback();
        } else {
            remoteInputView.unregisterBackCallback();
        }
    }

    public void forceUnRegister(ViewRootImpl viewRootImpl) {
        WindowOnBackInvokedDispatcher onBackInvokedDispatcher;
        OnBackInvokedCallback topCallback = (viewRootImpl == null || (onBackInvokedDispatcher = viewRootImpl.getOnBackInvokedDispatcher()) == null) ? null : onBackInvokedDispatcher.getTopCallback();
        if (topCallback == null || !StringsKt__StringsKt.contains(topCallback.toString(), REMOTE_INPUI_VIEW, false)) {
            return;
        }
        viewRootImpl.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(topCallback);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "RemoteInputViewExtImpl--> top callback is remoteInputView, need unregister");
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getEditBgColor(int i) {
        return NotificationColorUtil.getRemoteInputEditBgColor(this.context, i);
    }

    public ColorStateList getRemoteTextColor(ColorStateList colorStateList) {
        return NotificationColorUtil.getRemoteInputMessageTextColor(this.context, colorStateList);
    }

    public final int getRemoteTextHintColor(int i) {
        return NotificationColorUtil.getRemoteInputTextHintColor(this.context, i);
    }

    public void onVisibilityChanged(int i, ExpandableNotificationRow expandableNotificationRow, LinearLayout linearLayout, RemoteInputView.RemoteEditText remoteEditText) {
        int remoteInputTextHintColor;
        int remoteInputEditBgColor;
        if (i == 0) {
            MaterialColorMode colorModeForRow = getNotificationMaterialColorEx().getColorModeForRow(expandableNotificationRow);
            NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(colorModeForRow);
            colorModeForRow.getClass();
            boolean z = (colorModeForRow == MaterialColorMode.UNINITIATED || colorModeForRow == MaterialColorMode.COLOR_NOTIFICATION) ? false : true;
            ColorStateList colorStateListValueOf = z ? ColorStateList.valueOf(notificationColorPaletteFrom.getPrimaryLabelColor()) : getRemoteTextColor(this.context.getColorStateList(2131103339));
            colorStateListValueOf.getClass();
            if (z) {
                remoteInputTextHintColor = notificationColorPaletteFrom.getSecondaryColorLabel();
            } else {
                remoteInputTextHintColor = NotificationColorUtil.getRemoteInputTextHintColor(this.context, this.context.getColor(2131103336));
            }
            if (z) {
                remoteInputEditBgColor = notificationColorPaletteFrom.getButtonBackgroundColor();
            } else {
                remoteInputEditBgColor = NotificationColorUtil.getRemoteInputEditBgColor(this.context, this.context.getColor(R.color.material_grey_50));
            }
            remoteEditText.setTextColor(colorStateListValueOf);
            remoteEditText.setHintTextColor(remoteInputTextHintColor);
            Drawable background = linearLayout.getBackground();
            GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
            if (gradientDrawable != null) {
                gradientDrawable.setColor(remoteInputEditBgColor);
            }
        }
    }

    public void setImageTintList(ImageButton imageButton, ColorStateList colorStateList) {
        imageButton.setImageTintList(null);
    }

    public void setRemoteEditImeVisible(NotificationEntry notificationEntry, boolean z) {
        notificationEntry.mRemoteEditImeVisible = z;
    }

    public void setSendImageDrawable(ImageButton imageButton) {
        if (imageButton != null) {
            imageButton.setImageDrawable(this.context.getDrawable(2131236434));
        }
    }
}
