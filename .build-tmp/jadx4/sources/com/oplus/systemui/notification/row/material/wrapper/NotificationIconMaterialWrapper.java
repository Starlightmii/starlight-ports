package com.oplus.systemui.notification.row.material.wrapper;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.NotificationHeaderView;
import android.view.View;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.internal.widget.CachingIconView;
import com.android.internal.widget.NotificationRowIconView;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.oplusgroup.OplusGroupNotificationRowIconView;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationIconMaterialWrapper implements MaterialViewWrapper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationIconMaterialWrapper";
    public final OplusContrastColorUtil oplusContrastColorUtil;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class IconPaletteTag {
        public final int iconColor;
        public final int materialColor;
        public final int notificationColor;

        public IconPaletteTag(int i, int i2, int i3) {
            this.iconColor = i;
            this.materialColor = i2;
            this.notificationColor = i3;
        }

        public final IconPaletteTag copy(int i, int i2, int i3) {
            return new IconPaletteTag(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IconPaletteTag)) {
                return false;
            }
            IconPaletteTag iconPaletteTag = (IconPaletteTag) obj;
            return this.iconColor == iconPaletteTag.iconColor && this.materialColor == iconPaletteTag.materialColor && this.notificationColor == iconPaletteTag.notificationColor;
        }

        public final int getIconColor() {
            return this.iconColor;
        }

        public final int getMaterialColor() {
            return this.materialColor;
        }

        public final int getNotificationColor() {
            return this.notificationColor;
        }

        public final int hashCode() {
            return Integer.hashCode(this.notificationColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.materialColor, Integer.hashCode(this.iconColor) * 31, 31);
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.notificationColor, ")", FloatList$.ExternalSyntheticOutline0.m("IconPaletteTag(iconColor=", this.iconColor, this.materialColor, ", materialColor=", ", notificationColor="));
        }
    }

    public NotificationIconMaterialWrapper(Context context) {
        this.oplusContrastColorUtil = OplusContrastColorUtil.getInstance(context);
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        if (view instanceof OplusGroupNotificationRowIconView) {
            return true;
        }
        if (view.getId() == 16908294) {
            return (view instanceof NotificationRowIconView) || (view instanceof CachingIconView);
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:95:0x0142  */
    /* JADX WARN: Code duplicated, block: B:97:0x014f  */
    /* JADX WARN: Code duplicated, block: B:98:0x0152  */
    public final void applyIconColor(Context context, CachingIconView cachingIconView, int i, ExpandableNotificationRow expandableNotificationRow, boolean z, int i2) {
        int iCalculateContrastColor;
        int i3;
        List attachedChildren;
        NotificationHeaderView groupHeader;
        StatusBarNotification sbn;
        Notification notification;
        StatusBarNotification sbn2;
        StatusBarNotification sbn3;
        Notification notification2;
        Object tag = cachingIconView.getTag(2131363396);
        if (Intrinsics.areEqual(tag, Boolean.TRUE)) {
            NotificationEntry entry = expandableNotificationRow.getEntry();
            StatusBarNotification sbn4 = entry.getSbn();
            boolean z2 = (sbn4 == null || (notification2 = sbn4.getNotification()) == null || !notification2.isColorized()) ? false : true;
            EntryAdapter entryAdapter = expandableNotificationRow.getEntryAdapter();
            Notification notification3 = (entryAdapter == null || (sbn3 = entryAdapter.getSbn()) == null) ? null : sbn3.getNotification();
            NotificationEntry entry2 = expandableNotificationRow.getEntry();
            if (Intrinsics.areEqual((entry2 == null || (sbn2 = entry2.getSbn()) == null) ? null : sbn2.getPackageName(), OplusAnimationController.PACKAGE_SYSTEM) && notification3 != null && notification3.isGroupSummary() && (attachedChildren = expandableNotificationRow.getAttachedChildren()) != null) {
                List list = attachedChildren;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        NotificationEntry entry3 = ((ExpandableNotificationRow) it.next()).getEntry();
                        String channelId = (entry3 == null || (sbn = entry3.getSbn()) == null || (notification = sbn.getNotification()) == null) ? null : notification.getChannelId();
                        if (Intrinsics.areEqual(channelId, "TetherNotification") || Intrinsics.areEqual(channelId, "DurationNotification")) {
                            NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
                            if (cachingIconView != ((childrenContainer == null || (groupHeader = childrenContainer.getGroupHeader()) == null) ? null : groupHeader.findViewById(R.id.icon)) && !(cachingIconView instanceof OplusGroupNotificationRowIconView)) {
                                break;
                            }
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("isTetheringGroupSummary: ", entry.getKey(), "NotificationIconMaterialWrapper-->", "SystemUi--Notification");
                                return;
                            }
                            return;
                        }
                    }
                }
            }
            OplusContrastColorUtil oplusContrastColorUtil = this.oplusContrastColorUtil;
            if (z2 || !(notification3 == null || notification3.color == 0)) {
                Object tag2 = expandableNotificationRow.getTag(2131364387);
                IconPaletteTag iconPaletteTag = tag2 instanceof IconPaletteTag ? (IconPaletteTag) tag2 : null;
                if (iconPaletteTag == null || iconPaletteTag.getMaterialColor() != i) {
                    oplusContrastColorUtil.getClass();
                    iCalculateContrastColor = OplusContrastColorUtil.calculateContrastColor(context, notification3);
                    if (notification3 != null) {
                        i3 = notification3.color;
                    } else {
                        i3 = 0;
                    }
                    expandableNotificationRow.setTag(2131364387, new IconPaletteTag(iCalculateContrastColor, i, i3));
                } else {
                    if (Integer.valueOf(iconPaletteTag.getNotificationColor()).equals(notification3 != null ? Integer.valueOf(notification3.color) : null)) {
                        iCalculateContrastColor = iconPaletteTag.getIconColor();
                    } else {
                        oplusContrastColorUtil.getClass();
                        iCalculateContrastColor = OplusContrastColorUtil.calculateContrastColor(context, notification3);
                        if (notification3 != null) {
                            i3 = notification3.color;
                        } else {
                            i3 = 0;
                        }
                        expandableNotificationRow.setTag(2131364387, new IconPaletteTag(iCalculateContrastColor, i, i3));
                    }
                }
            } else {
                iCalculateContrastColor = (z || oplusContrastColorUtil.isVectorDrawable(cachingIconView.getDrawable())) ? i : i2;
            }
            cachingIconView.setOriginalIconColor(iCalculateContrastColor);
            Drawable drawable = cachingIconView.getDrawable();
            if (drawable != null) {
                drawable.setColorFilter(iCalculateContrastColor, PorterDuff.Mode.SRC_ATOP);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sb = new StringBuilder("NotificationIconMaterialWrapper-->");
                NotificationEntry entry4 = expandableNotificationRow.getEntry();
                String key = entry4 != null ? entry4.getKey() : null;
                Integer numValueOf = notification3 != null ? Integer.valueOf(notification3.color) : null;
                String hexString = Integer.toHexString(i);
                String hexString2 = Integer.toHexString(iCalculateContrastColor);
                StringBuilder sb2 = new StringBuilder("key=");
                sb2.append(key);
                sb2.append(", isGrayscale=");
                sb2.append(tag);
                sb2.append(", isColorized=");
                sb2.append(z2);
                sb2.append(", notification.color=");
                sb2.append(numValueOf);
                sb2.append("useFixedColor=");
                DeviceItem$.ExternalSyntheticOutline0.m(", request=", hexString, ", result=", sb2, z);
                sb2.append(hexString2);
                sb.append(sb2.toString());
                Log.d("SystemUi--Notification", sb.toString());
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, CachingIconView cachingIconView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        applyIconColor(context, cachingIconView, notificationColorPaletteFrom.getPrimaryColor(), expandableNotificationRow, true, notificationColorPaletteFrom.getPrimaryColor());
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, CachingIconView cachingIconView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        applyIconColor(context, cachingIconView, notificationColorPaletteFrom.getSmallIconColor(), expandableNotificationRow, false, notificationColorPaletteFrom.getIconFilterColor());
    }
}
