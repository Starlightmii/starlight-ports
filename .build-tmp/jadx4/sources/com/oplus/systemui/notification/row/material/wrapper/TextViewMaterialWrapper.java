package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.OplusNotificationDateTimeView;
import android.widget.TextView;
import com.android.internal.widget.EmphasizedNotificationButton;
import com.android.systemui.statusbar.notification.collection.coordinator.CenterAlignedImageSpan;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.sequences.SequencesKt__SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TextViewMaterialWrapper implements MaterialViewWrapper {
    public static void applySummarizationIconTint(TextView textView, int i) {
        Drawable drawable;
        Drawable drawableMutate;
        CharSequence text = textView.getText();
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            ImageSpan imageSpan = (ImageSpan) ArraysKt___ArraysKt.firstOrNull(spanned.getSpans(0, spanned.length(), ImageSpan.class));
            if (imageSpan == null || (drawable = imageSpan.getDrawable()) == null || (drawableMutate = drawable.mutate()) == null) {
                return;
            }
            drawableMutate.setTint(i);
            if (drawableMutate.getBounds().isEmpty()) {
                drawableMutate.setBounds(0, 0, drawableMutate.getIntrinsicWidth(), drawableMutate.getIntrinsicHeight());
            }
            int spanStart = spanned.getSpanStart(imageSpan);
            int spanEnd = spanned.getSpanEnd(imageSpan);
            if (spanStart != 0 || spanEnd < 0 || spanEnd > spanned.length()) {
                return;
            }
            int i2 = spanEnd + 1;
            int length = spanned.length();
            if (i2 > length) {
                i2 = length;
            }
            textView.setText(new SpannableStringBuilder().append("  ", new CenterAlignedImageSpan(drawableMutate), 0).append((CharSequence) " ").append(spanned.subSequence(i2, spanned.length())));
        }
    }

    public static boolean hasParentWithBackground(View view) {
        Object tag = view.getTag(2131364208);
        if (tag != null) {
            return ((Boolean) tag).booleanValue();
        }
        Drawable background = view.getBackground();
        boolean z = false;
        if ((background != null ? background.getAlpha() : 0) > 0) {
            z = true;
            break;
        }
        Object parent = view.getParent();
        for (View view2 : SequencesKt__SequencesKt.generateSequence(parent instanceof View ? (View) parent : null, new TextViewMaterialWrapper$$ExternalSyntheticLambda0())) {
            if (!(view2 instanceof GroupOverlayContainerView)) {
                Drawable background2 = view2.getBackground();
                if ((background2 != null ? background2.getAlpha() : 0) > 0) {
                    z = true;
                    break;
                }
            }
        }
        view.setTag(2131364208, Boolean.valueOf(z));
        return z;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return (!(view instanceof TextView) || (view instanceof EditText) || (view instanceof OplusNotificationDateTimeView) || (view instanceof EmphasizedNotificationButton)) ? false : true;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, TextView textView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        if (hasParentWithBackground(textView)) {
            return;
        }
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        textView.setTextColor(notificationColorPaletteFrom.getPrimaryColor());
        if (textView.getId() == 16909341) {
            applySummarizationIconTint(textView, notificationColorPaletteFrom.getPrimaryColor());
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, TextView textView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        if (hasParentWithBackground(textView)) {
            return;
        }
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        int secondaryColor = textView.getId() == 16909747 ? notificationColorPaletteFrom.getSecondaryColor() : notificationColorPaletteFrom.getPrimaryColor();
        textView.setTextColor(secondaryColor);
        if (textView.getId() == 16909341) {
            applySummarizationIconTint(textView, secondaryColor);
        }
    }
}
