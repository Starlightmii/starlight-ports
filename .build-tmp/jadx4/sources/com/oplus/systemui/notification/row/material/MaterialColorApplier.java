package com.oplus.systemui.notification.row.material;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationDecoratedCustomViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger;
import com.oplus.systemui.notification.row.material.wrapper.ButtonMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.EditTextMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.ExpandButtonMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.ExpandGroupHeaderMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.ExpandGroupHeaderReverseWrapper;
import com.oplus.systemui.notification.row.material.wrapper.ImageViewMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper;
import com.oplus.systemui.notification.row.material.wrapper.NotificationDateTimeMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.NotificationIconMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.ProgressBarMaterialWrapper;
import com.oplus.systemui.notification.row.material.wrapper.TextViewMaterialWrapper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaterialColorApplier {
    public static final Companion Companion = new Companion();
    private static final Set<Integer> EXCLUDE_IDS = SetsKt__SetsKt.setOf(new Integer[]{2131362193, 2131362193, Integer.valueOf(R.id.messaging_group_content_container), Integer.valueOf(R.id.shortcut), 2131364315});
    public static final String PROGRESS_STYLE_NAME = Notification.ProgressStyle.class.getSimpleName();
    private static final String TAG = "MaterialColorApplier";
    private final List<MaterialViewWrapper> wrappers;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$6wxi42tXlkYJUdLAYHVlZRmwxpE(MaterialColorApplier materialColorApplier, MaterialColorMode materialColorMode, Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        Object next;
        Iterator<T> it = materialColorApplier.wrappers.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((MaterialViewWrapper) next).accept(view));
        MaterialViewWrapper materialViewWrapper = (MaterialViewWrapper) next;
        if (materialViewWrapper != null) {
            materialViewWrapper.applyNormal(context, view, expandableNotificationRow, materialColorMode);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$8qfL0ftX_P6BFdTbY2rHU6ta5Ys(MaterialColorApplier materialColorApplier, MaterialColorMode materialColorMode, Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        Object next;
        Iterator<T> it = materialColorApplier.wrappers.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((MaterialViewWrapper) next).accept(view));
        MaterialViewWrapper materialViewWrapper = (MaterialViewWrapper) next;
        if (materialViewWrapper != null) {
            materialViewWrapper.applyMaterial(context, view, expandableNotificationRow, materialColorMode);
        }
        return Unit.INSTANCE;
    }

    public MaterialColorApplier(ExpandGroupHeaderReverseWrapper expandGroupHeaderReverseWrapper, ExpandGroupHeaderMaterialWrapper expandGroupHeaderMaterialWrapper, ExpandButtonMaterialWrapper expandButtonMaterialWrapper, NotificationDateTimeMaterialWrapper notificationDateTimeMaterialWrapper, NotificationIconMaterialWrapper notificationIconMaterialWrapper, EditTextMaterialWrapper editTextMaterialWrapper, ButtonMaterialWrapper buttonMaterialWrapper, TextViewMaterialWrapper textViewMaterialWrapper, ProgressBarMaterialWrapper progressBarMaterialWrapper, ImageViewMaterialWrapper imageViewMaterialWrapper) {
        this.wrappers = CollectionsKt__CollectionsKt.listOf(new MaterialViewWrapper[]{expandGroupHeaderReverseWrapper, expandGroupHeaderMaterialWrapper, expandButtonMaterialWrapper, notificationDateTimeMaterialWrapper, notificationIconMaterialWrapper, editTextMaterialWrapper, buttonMaterialWrapper, textViewMaterialWrapper, progressBarMaterialWrapper, imageViewMaterialWrapper});
    }

    public static View getWrappedCustomViewFromMainColumn(ViewGroup viewGroup) {
        int iIntValue;
        if (viewGroup != null) {
            Object tag = viewGroup.getTag(R.id.permission_group);
            Integer num = tag instanceof Integer ? (Integer) tag : null;
            if (num != null && (iIntValue = num.intValue()) != -1) {
                return viewGroup.getChildAt(iIntValue);
            }
        }
        return null;
    }

    public static boolean isSkipCustomNotification(View view) {
        Drawable background;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            View childAt = viewGroup.getChildAt(0);
            if (((childAt == null || (background = childAt.getBackground()) == null) ? 0 : background.getAlpha()) > 0) {
                return true;
            }
        }
        return false;
    }

    public static void traverse(Context context, boolean z, View view, ExpandableNotificationRow expandableNotificationRow, int i, Function3 function3) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        Bundle bundle;
        int iResolveViewType = NotificationDetailColorChanger.Companion.resolveViewType(view, i);
        if (!(view instanceof ViewGroup)) {
            if (view instanceof ProgressBar) {
                NotificationEntry entry2 = expandableNotificationRow.getEntry();
                if (Intrinsics.areEqual(entry2 != null ? entry2.getNotificationStyle() : null, PROGRESS_STYLE_NAME)) {
                    return;
                }
                NotificationViewWrapper notificationViewWrapper = expandableNotificationRow.getNotificationViewWrapper();
                if (NotificationDecoratedCustomViewWrapper.hasCustomView(notificationViewWrapper != null ? notificationViewWrapper.mView : null)) {
                    return;
                }
            }
            function3.invoke(context, view, expandableNotificationRow);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        boolean zIsSkipCustomNotification = true;
        if (!EXCLUDE_IDS.contains(Integer.valueOf(viewGroup.getId())) && !(viewGroup instanceof RemoteInputView)) {
            if (z && viewGroup.getId() == 16909425) {
                zIsSkipCustomNotification = isSkipCustomNotification(getWrappedCustomViewFromMainColumn(viewGroup));
            } else if (z || viewGroup != expandableNotificationRow || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (notification = sbn.getNotification()) == null || (bundle = notification.extras) == null || !bundle.getBoolean("android.contains.customView", false)) {
                zIsSkipCustomNotification = false;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("traverse skip fully custom view notification, key=", expandableNotificationRow.getKey(), "MaterialColorApplier-->", "SystemUi--Notification");
                }
                zIsSkipCustomNotification = isSkipCustomNotification(viewGroup);
            }
        }
        if (zIsSkipCustomNotification) {
            return;
        }
        if (viewGroup.getId() != 2131364384) {
            if (view instanceof NotificationExpandButton) {
                function3.invoke(context, view, expandableNotificationRow);
            }
            View wrappedCustomViewFromMainColumn = viewGroup.getId() == 16909425 ? getWrappedCustomViewFromMainColumn(viewGroup) : null;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                int i3 = (wrappedCustomViewFromMainColumn == null || childAt != wrappedCustomViewFromMainColumn) ? iResolveViewType : 7;
                childAt.getClass();
                traverse(context, z, childAt, expandableNotificationRow, i3, function3);
            }
            return;
        }
        if (iResolveViewType != 7 && iResolveViewType != 8 && iResolveViewType != 9) {
            function3.invoke(context, view, expandableNotificationRow);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "MaterialColorApplier-->".concat("intercept expand_group_header under custom type=" + iResolveViewType + ", key=" + expandableNotificationRow.getKey()));
        }
    }

    public final void applyColorMode(Context context, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "MaterialColorApplier-->".concat("applyColorMode key=" + expandableNotificationRow.getKey() + ", mode=" + materialColorMode));
        }
        int iOrdinal = materialColorMode.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            NotificationContentView showingLayout = expandableNotificationRow.getShowingLayout();
            boolean z = (showingLayout != null ? showingLayout.findViewById(R.id.persistAcrossReboots) : null) != null;
            int iResolveRowRootType = NotificationDetailColorChanger.Companion.resolveRowRootType(expandableNotificationRow.getNotificationViewWrapper());
            MaterialColorApplier$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new MaterialColorApplier$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = this;
            externalSyntheticLambda0.f$1 = materialColorMode;
            VarHandle.storeStoreFence();
            traverse(context, z, expandableNotificationRow, expandableNotificationRow, iResolveRowRootType, externalSyntheticLambda0);
            return;
        }
        if (iOrdinal != 3) {
            return;
        }
        NotificationContentView showingLayout2 = expandableNotificationRow.getShowingLayout();
        boolean z2 = (showingLayout2 != null ? showingLayout2.findViewById(R.id.persistAcrossReboots) : null) != null;
        int iResolveRowRootType2 = NotificationDetailColorChanger.Companion.resolveRowRootType(expandableNotificationRow.getNotificationViewWrapper());
        MaterialColorApplier$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new MaterialColorApplier$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = materialColorMode;
        VarHandle.storeStoreFence();
        traverse(context, z2, expandableNotificationRow, expandableNotificationRow, iResolveRowRootType2, externalSyntheticLambda1);
    }
}
