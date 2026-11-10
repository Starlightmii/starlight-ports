package com.oplus.systemui.media;

import android.content.Context;
import android.content.res.Resources;
import android.media.RoutingSessionInfo;
import android.media.session.MediaController;
import com.android.settingslib.media.BluetoothMediaDevice;
import com.android.settingslib.media.InfoMediaDevice;
import com.android.settingslib.media.MediaDevice;
import com.android.systemui.media.MediaDeviceDrawableResult;
import com.android.systemui.media.OplusMediaDeviceManagerEx;
import com.oplus.bluetooth.OplusA2dpSharingManager;
import com.oplus.systemui.qs.utils.BluetoothIconUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDeviceManagerExImpl extends OplusMediaDeviceManagerEx {
    private final Context context;

    public OplusMediaDeviceManagerExImpl(Context context) {
        this.context = context;
    }

    public MediaDeviceDrawableResult getMediaDrawable(MediaDevice mediaDevice) {
        int bluetoothResIdIgnoreMic;
        int i;
        if (mediaDevice instanceof BluetoothMediaDevice) {
            bluetoothResIdIgnoreMic = BluetoothIconUtil.getBluetoothResIdIgnoreMic(((BluetoothMediaDevice) mediaDevice).getCachedDevice(), true);
        } else if (mediaDevice instanceof InfoMediaDevice) {
            bluetoothResIdIgnoreMic = 2131237135;
        } else {
            bluetoothResIdIgnoreMic = ((mediaDevice == null || mediaDevice.getDeviceType() != 3) && (mediaDevice == null || mediaDevice.getDeviceType() != 2)) ? 2131237104 : 2131237168;
        }
        if (OplusA2dpSharingManager.getOplusA2dpSharingManager().inSharingMode()) {
            bluetoothResIdIgnoreMic = 2131237115;
        } else if (bluetoothResIdIgnoreMic == 2131237104) {
            bluetoothResIdIgnoreMic = 2131237112;
        }
        if (bluetoothResIdIgnoreMic == 0) {
            return super.getMediaDrawable(mediaDevice);
        }
        if (bluetoothResIdIgnoreMic == 2131237104) {
            i = 2131237108;
        } else if (bluetoothResIdIgnoreMic == 2131237117) {
            i = 2131237118;
        } else if (bluetoothResIdIgnoreMic == 2131237120) {
            i = 2131237121;
        } else if (bluetoothResIdIgnoreMic == 2131237128) {
            i = 2131237129;
        } else if (bluetoothResIdIgnoreMic == 2131237110) {
            i = 2131237111;
        } else if (bluetoothResIdIgnoreMic == 2131237112) {
            i = 2131237113;
        } else {
            i = bluetoothResIdIgnoreMic == 2131237168 ? 2131237169 : bluetoothResIdIgnoreMic;
        }
        return new MediaDeviceDrawableResult(this.context.getResources().getDrawable(bluetoothResIdIgnoreMic, this.context.getTheme()), this.context.getResources().getDrawable(i, this.context.getTheme()), bluetoothResIdIgnoreMic);
    }

    public String getPhoneName(MediaDevice mediaDevice, MediaController mediaController, RoutingSessionInfo routingSessionInfo) {
        String string;
        CharSequence name;
        String string2;
        if (mediaController != null && routingSessionInfo == null) {
            return null;
        }
        if (routingSessionInfo != null && (name = routingSessionInfo.getName()) != null && (string2 = name.toString()) != null) {
            return string2;
        }
        if (mediaDevice instanceof BluetoothMediaDevice) {
            return ((BluetoothMediaDevice) mediaDevice).getName();
        }
        if (mediaDevice instanceof InfoMediaDevice) {
            return ((InfoMediaDevice) mediaDevice).getName();
        }
        if ((mediaDevice != null && mediaDevice.getDeviceType() == 3) || (mediaDevice != null && mediaDevice.getDeviceType() == 2)) {
            return mediaDevice.getName();
        }
        Resources resources = this.context.getResources();
        if (resources != null && (string = resources.getString(2131954490)) != null) {
            return string;
        }
        if (mediaDevice != null) {
            return mediaDevice.getName();
        }
        return null;
    }
}
