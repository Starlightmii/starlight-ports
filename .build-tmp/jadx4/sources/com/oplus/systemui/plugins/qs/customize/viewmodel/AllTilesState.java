package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.DialogPreviewConfig;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AllTilesState {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Loading extends AllTilesState {
        public static final Loading INSTANCE = new Loading();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Loading);
        }

        public final int hashCode() {
            return 1244742753;
        }

        public String toString() {
            return "Loading";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Normal extends AllTilesState {
        private final List<DialogPreviewConfig> dialogPreviewConfig;

        public Normal(List<DialogPreviewConfig> list) {
            this.dialogPreviewConfig = list;
        }

        public final List<DialogPreviewConfig> component1() {
            return this.dialogPreviewConfig;
        }

        public final Normal copy(List<DialogPreviewConfig> list) {
            return new Normal(list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Normal) && Intrinsics.areEqual(this.dialogPreviewConfig, ((Normal) obj).dialogPreviewConfig);
        }

        public final List<DialogPreviewConfig> getDialogPreviewConfig() {
            return this.dialogPreviewConfig;
        }

        public final int hashCode() {
            return this.dialogPreviewConfig.hashCode();
        }

        public String toString() {
            return TextFieldBuffer$.ExternalSyntheticOutline0.m(this.dialogPreviewConfig.hashCode(), this.dialogPreviewConfig.size(), " ", "@Normal(dialogPreviewConfig=");
        }
    }
}
