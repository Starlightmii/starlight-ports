package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
interface TargetChoice {

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class App implements TargetChoice {
        public static final int $stable = 0;
        private final String packageName;

        public App(String str) {
            b4.l.e("packageName", str);
            this.packageName = str;
        }

        public static /* synthetic */ App copy$default(App app, String str, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                str = app.packageName;
            }
            return app.copy(str);
        }

        public final String component1() {
            return this.packageName;
        }

        public final App copy(String str) {
            b4.l.e("packageName", str);
            return new App(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof App) && b4.l.a(this.packageName, ((App) obj).packageName);
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public int hashCode() {
            return this.packageName.hashCode();
        }

        public String toString() {
            return "App(packageName=" + this.packageName + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class CircleToSearch implements TargetChoice {
        public static final int $stable = 0;
        public static final CircleToSearch INSTANCE = new CircleToSearch();

        private CircleToSearch() {
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof CircleToSearch);
        }

        public int hashCode() {
            return -705198364;
        }

        public String toString() {
            return "CircleToSearch";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class Custom implements TargetChoice {
        public static final int $stable = 0;
        private final String packageName;

        public Custom(String str) {
            b4.l.e("packageName", str);
            this.packageName = str;
        }

        public static /* synthetic */ Custom copy$default(Custom custom, String str, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                str = custom.packageName;
            }
            return custom.copy(str);
        }

        public final String component1() {
            return this.packageName;
        }

        public final Custom copy(String str) {
            b4.l.e("packageName", str);
            return new Custom(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Custom) && b4.l.a(this.packageName, ((Custom) obj).packageName);
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public int hashCode() {
            return this.packageName.hashCode();
        }

        public String toString() {
            return "Custom(packageName=" + this.packageName + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class FollowDefault implements TargetChoice {
        public static final int $stable = 0;
        public static final FollowDefault INSTANCE = new FollowDefault();

        private FollowDefault() {
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof FollowDefault);
        }

        public int hashCode() {
            return 690223423;
        }

        public String toString() {
            return "FollowDefault";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class None implements TargetChoice {
        public static final int $stable = 0;
        public static final None INSTANCE = new None();

        private None() {
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof None);
        }

        public int hashCode() {
            return 932779625;
        }

        public String toString() {
            return "None";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class Oem implements TargetChoice {
        public static final int $stable = 0;
        public static final Oem INSTANCE = new Oem();

        private Oem() {
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Oem);
        }

        public int hashCode() {
            return 2108300294;
        }

        public String toString() {
            return "Oem";
        }
    }
}
