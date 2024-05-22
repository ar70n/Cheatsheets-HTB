package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

public class Lang {
    private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/%s_lang.txt";
    private static final Map<NameType, Lang> Langs = new EnumMap(NameType.class);
    private final Languages languages;
    private final List<LangRule> rules;

    private static final class LangRule {
        /* access modifiers changed from: private */
        public final boolean acceptOnMatch;
        /* access modifiers changed from: private */
        public final Set<String> languages;
        private final Pattern pattern;

        private LangRule(Pattern pattern2, Set<String> set, boolean z) {
            this.pattern = pattern2;
            this.languages = set;
            this.acceptOnMatch = z;
        }

        public boolean matches(String str) {
            return this.pattern.matcher(str).find();
        }
    }

    static {
        for (NameType nameType : NameType.values()) {
            Langs.put(nameType, loadFromResource(String.format(LANGUAGE_RULES_RN, new Object[]{nameType.getName()}), Languages.getInstance(nameType)));
        }
    }

    public static Lang instance(NameType nameType) {
        return Langs.get(nameType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bb, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c1, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c4, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.commons.codec.language.bm.Lang loadFromResource(java.lang.String r9, org.apache.commons.codec.language.bm.Languages r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Class<org.apache.commons.codec.language.bm.Lang> r1 = org.apache.commons.codec.language.bm.Lang.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.io.InputStream r1 = r1.getResourceAsStream(r9)
            if (r1 == 0) goto L_0x00c5
            java.util.Scanner r2 = new java.util.Scanner
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)
            r1 = 0
        L_0x0019:
            r3 = r1
        L_0x001a:
            boolean r4 = r2.hasNextLine()     // Catch:{ all -> 0x00b9 }
            if (r4 == 0) goto L_0x00b0
            java.lang.String r4 = r2.nextLine()     // Catch:{ all -> 0x00b9 }
            r5 = 1
            if (r3 == 0) goto L_0x0030
            java.lang.String r5 = "*/"
            boolean r4 = r4.endsWith(r5)     // Catch:{ all -> 0x00b9 }
            if (r4 == 0) goto L_0x001a
            goto L_0x0019
        L_0x0030:
            java.lang.String r6 = "/*"
            boolean r6 = r4.startsWith(r6)     // Catch:{ all -> 0x00b9 }
            if (r6 == 0) goto L_0x003a
            r3 = r5
            goto L_0x001a
        L_0x003a:
            java.lang.String r6 = "//"
            int r6 = r4.indexOf(r6)     // Catch:{ all -> 0x00b9 }
            if (r6 < 0) goto L_0x0047
            java.lang.String r6 = r4.substring(r1, r6)     // Catch:{ all -> 0x00b9 }
            goto L_0x0048
        L_0x0047:
            r6 = r4
        L_0x0048:
            java.lang.String r6 = r6.trim()     // Catch:{ all -> 0x00b9 }
            int r7 = r6.length()     // Catch:{ all -> 0x00b9 }
            if (r7 != 0) goto L_0x0053
            goto L_0x001a
        L_0x0053:
            java.lang.String r7 = "\\s+"
            java.lang.String[] r6 = r6.split(r7)     // Catch:{ all -> 0x00b9 }
            int r7 = r6.length     // Catch:{ all -> 0x00b9 }
            r8 = 3
            if (r7 != r8) goto L_0x0087
            r4 = r6[r1]     // Catch:{ all -> 0x00b9 }
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch:{ all -> 0x00b9 }
            r5 = r6[r5]     // Catch:{ all -> 0x00b9 }
            java.lang.String r7 = "\\+"
            java.lang.String[] r5 = r5.split(r7)     // Catch:{ all -> 0x00b9 }
            r7 = 2
            r6 = r6[r7]     // Catch:{ all -> 0x00b9 }
            java.lang.String r7 = "true"
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x00b9 }
            org.apache.commons.codec.language.bm.Lang$LangRule r7 = new org.apache.commons.codec.language.bm.Lang$LangRule     // Catch:{ all -> 0x00b9 }
            java.util.HashSet r8 = new java.util.HashSet     // Catch:{ all -> 0x00b9 }
            java.util.List r5 = java.util.Arrays.asList(r5)     // Catch:{ all -> 0x00b9 }
            r8.<init>(r5)     // Catch:{ all -> 0x00b9 }
            r5 = 0
            r7.<init>(r4, r8, r6)     // Catch:{ all -> 0x00b9 }
            r0.add(r7)     // Catch:{ all -> 0x00b9 }
            goto L_0x001a
        L_0x0087:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00b9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b9 }
            r0.<init>()     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "Malformed line '"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00b9 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "' in language resource '"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00b9 }
            java.lang.StringBuilder r9 = r0.append(r9)     // Catch:{ all -> 0x00b9 }
            java.lang.String r0 = "'"
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch:{ all -> 0x00b9 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00b9 }
            r10.<init>(r9)     // Catch:{ all -> 0x00b9 }
            throw r10     // Catch:{ all -> 0x00b9 }
        L_0x00b0:
            r2.close()
            org.apache.commons.codec.language.bm.Lang r9 = new org.apache.commons.codec.language.bm.Lang
            r9.<init>(r0, r10)
            return r9
        L_0x00b9:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r10 = move-exception
            r2.close()     // Catch:{ all -> 0x00c0 }
            goto L_0x00c4
        L_0x00c0:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x00c4:
            throw r10
        L_0x00c5:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Unable to resolve required resource:org/apache/commons/codec/language/bm/%s_lang.txt"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Lang.loadFromResource(java.lang.String, org.apache.commons.codec.language.bm.Languages):org.apache.commons.codec.language.bm.Lang");
    }

    private Lang(List<LangRule> list, Languages languages2) {
        this.rules = Collections.unmodifiableList(list);
        this.languages = languages2;
    }

    public String guessLanguage(String str) {
        Languages.LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : Languages.ANY;
    }

    public Languages.LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.languages.getLanguages());
        for (LangRule next : this.rules) {
            if (next.matches(lowerCase)) {
                if (next.acceptOnMatch) {
                    hashSet.retainAll(next.languages);
                } else {
                    hashSet.removeAll(next.languages);
                }
            }
        }
        Languages.LanguageSet from = Languages.LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
