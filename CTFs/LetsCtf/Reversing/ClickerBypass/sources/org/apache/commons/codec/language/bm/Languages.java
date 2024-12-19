package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Languages {
    public static final String ANY = "any";
    public static final LanguageSet ANY_LANGUAGE = new LanguageSet() {
        public boolean contains(String str) {
            return true;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isSingleton() {
            return false;
        }

        public LanguageSet merge(LanguageSet languageSet) {
            return languageSet;
        }

        public LanguageSet restrictTo(LanguageSet languageSet) {
            return languageSet;
        }

        public String toString() {
            return "ANY_LANGUAGE";
        }

        public String getAny() {
            throw new NoSuchElementException("Can't fetch any language from the any language set.");
        }
    };
    private static final Map<NameType, Languages> LANGUAGES = new EnumMap(NameType.class);
    public static final LanguageSet NO_LANGUAGES = new LanguageSet() {
        public boolean contains(String str) {
            return false;
        }

        public boolean isEmpty() {
            return true;
        }

        public boolean isSingleton() {
            return false;
        }

        public LanguageSet merge(LanguageSet languageSet) {
            return languageSet;
        }

        public LanguageSet restrictTo(LanguageSet languageSet) {
            return this;
        }

        public String toString() {
            return "NO_LANGUAGES";
        }

        public String getAny() {
            throw new NoSuchElementException("Can't fetch any language from the empty language set.");
        }
    };
    private final Set<String> languages;

    public static abstract class LanguageSet {
        public abstract boolean contains(String str);

        public abstract String getAny();

        public abstract boolean isEmpty();

        public abstract boolean isSingleton();

        /* access modifiers changed from: package-private */
        public abstract LanguageSet merge(LanguageSet languageSet);

        public abstract LanguageSet restrictTo(LanguageSet languageSet);

        public static LanguageSet from(Set<String> set) {
            return set.isEmpty() ? Languages.NO_LANGUAGES : new SomeLanguages(set);
        }
    }

    public static final class SomeLanguages extends LanguageSet {
        private final Set<String> languages;

        private SomeLanguages(Set<String> set) {
            this.languages = Collections.unmodifiableSet(set);
        }

        public boolean contains(String str) {
            return this.languages.contains(str);
        }

        public String getAny() {
            return this.languages.iterator().next();
        }

        public Set<String> getLanguages() {
            return this.languages;
        }

        public boolean isEmpty() {
            return this.languages.isEmpty();
        }

        public boolean isSingleton() {
            return this.languages.size() == 1;
        }

        public LanguageSet restrictTo(LanguageSet languageSet) {
            if (languageSet == Languages.NO_LANGUAGES) {
                return languageSet;
            }
            if (languageSet == Languages.ANY_LANGUAGE) {
                return this;
            }
            SomeLanguages someLanguages = (SomeLanguages) languageSet;
            HashSet hashSet = new HashSet(Math.min(this.languages.size(), someLanguages.languages.size()));
            for (String next : this.languages) {
                if (someLanguages.languages.contains(next)) {
                    hashSet.add(next);
                }
            }
            return from(hashSet);
        }

        public LanguageSet merge(LanguageSet languageSet) {
            if (languageSet == Languages.NO_LANGUAGES) {
                return this;
            }
            if (languageSet == Languages.ANY_LANGUAGE) {
                return languageSet;
            }
            HashSet hashSet = new HashSet(this.languages);
            for (String add : ((SomeLanguages) languageSet).languages) {
                hashSet.add(add);
            }
            return from(hashSet);
        }

        public String toString() {
            return "Languages(" + this.languages.toString() + ")";
        }
    }

    static {
        for (NameType nameType : NameType.values()) {
            LANGUAGES.put(nameType, getInstance(langResourceName(nameType)));
        }
    }

    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get(nameType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005c, code lost:
        r0.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.commons.codec.language.bm.Languages getInstance(java.lang.String r5) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.lang.Class<org.apache.commons.codec.language.bm.Languages> r1 = org.apache.commons.codec.language.bm.Languages.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.io.InputStream r1 = r1.getResourceAsStream(r5)
            if (r1 == 0) goto L_0x0060
            java.util.Scanner r5 = new java.util.Scanner
            java.lang.String r2 = "UTF-8"
            r5.<init>(r1, r2)
            r1 = 0
        L_0x0019:
            r2 = r1
        L_0x001a:
            boolean r3 = r5.hasNextLine()     // Catch:{ all -> 0x0054 }
            if (r3 == 0) goto L_0x0047
            java.lang.String r3 = r5.nextLine()     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = r3.trim()     // Catch:{ all -> 0x0054 }
            if (r2 == 0) goto L_0x0033
            java.lang.String r4 = "*/"
            boolean r3 = r3.endsWith(r4)     // Catch:{ all -> 0x0054 }
            if (r3 == 0) goto L_0x001a
            goto L_0x0019
        L_0x0033:
            java.lang.String r4 = "/*"
            boolean r4 = r3.startsWith(r4)     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x003d
            r2 = 1
            goto L_0x001a
        L_0x003d:
            int r4 = r3.length()     // Catch:{ all -> 0x0054 }
            if (r4 <= 0) goto L_0x001a
            r0.add(r3)     // Catch:{ all -> 0x0054 }
            goto L_0x001a
        L_0x0047:
            r5.close()
            org.apache.commons.codec.language.bm.Languages r5 = new org.apache.commons.codec.language.bm.Languages
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r0)
            r5.<init>(r0)
            return r5
        L_0x0054:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r1 = move-exception
            r5.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r5 = move-exception
            r0.addSuppressed(r5)
        L_0x005f:
            throw r1
        L_0x0060:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to resolve required resource: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Languages.getInstance(java.lang.String):org.apache.commons.codec.language.bm.Languages");
    }

    private static String langResourceName(NameType nameType) {
        return String.format("org/apache/commons/codec/language/bm/%s_languages.txt", new Object[]{nameType.getName()});
    }

    private Languages(Set<String> set) {
        this.languages = set;
    }

    public Set<String> getLanguages() {
        return this.languages;
    }
}
