package com.oplus.systemui.statusbar.notification.util;

import android.media.AudioAttributes;
import android.media.SoundPool;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SoundUtil {
    public final HashMap mSoundMap = new HashMap();
    public final SoundPool mSoundPool;

    public SoundUtil() {
        SoundPool.Builder builder = new SoundPool.Builder();
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setLegacyStreamType(1).build();
        builder.setMaxStreams(1);
        builder.setAudioAttributes(audioAttributesBuild);
        this.mSoundPool = builder.build();
    }

    public void setCompleteListener(SoundPool.OnLoadCompleteListener onLoadCompleteListener) {
        this.mSoundPool.setOnLoadCompleteListener(onLoadCompleteListener);
    }
}
