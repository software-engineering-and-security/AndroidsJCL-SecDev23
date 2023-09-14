<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/util/ManifestEntryVerifier.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/412d2b1381a4">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/412d2b1381a4">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/412d2b1381a4">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/util/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/util/ManifestEntryVerifier.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/util/ManifestEntryVerifier.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/util/ManifestEntryVerifier.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/util/ManifestEntryVerifier.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/util/ManifestEntryVerifier.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/util/ManifestEntryVerifier.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/util/ManifestEntryVerifier.java @ 14391:412d2b1381a4</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8249906: Enhance opening JARs
8258247: Couple of issues in fix for JDK-8249906
8259428: AlgorithmId.getEncodedParams() should return copy
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 07 Apr 2021 05:57:56 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/c729ab3b13ae/src/share/classes/sun/security/util/ManifestEntryVerifier.java">c729ab3b13ae</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/973f51f86ecb/src/share/classes/sun/security/util/ManifestEntryVerifier.java">973f51f86ecb</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1997, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">package sun.security.util;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.security.*;</span><a href="#l28"></a>
<span id="l29">import java.io.*;</span><a href="#l29"></a>
<span id="l30">import java.util.*;</span><a href="#l30"></a>
<span id="l31">import java.util.jar.*;</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">import sun.security.jca.Providers;</span><a href="#l33"></a>
<span id="l34">import sun.security.util.DisabledAlgorithmConstraints;</span><a href="#l34"></a>
<span id="l35">import sun.security.util.JarConstraintsParameters;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">/**</span><a href="#l37"></a>
<span id="l38"> * This class is used to verify each entry in a jar file with its</span><a href="#l38"></a>
<span id="l39"> * manifest value.</span><a href="#l39"></a>
<span id="l40"> */</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">public class ManifestEntryVerifier {</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">    private static final Debug debug = Debug.getInstance(&quot;jar&quot;);</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">    /**</span><a href="#l46"></a>
<span id="l47">     * Holder class to lazily load Sun provider. NOTE: if</span><a href="#l47"></a>
<span id="l48">     * Providers.getSunProvider returned a cached provider, we could avoid the</span><a href="#l48"></a>
<span id="l49">     * need for caching the provider with this holder class; we should try to</span><a href="#l49"></a>
<span id="l50">     * revisit this in JDK 8.</span><a href="#l50"></a>
<span id="l51">     */</span><a href="#l51"></a>
<span id="l52">    private static class SunProviderHolder {</span><a href="#l52"></a>
<span id="l53">        private static final Provider instance = Providers.getSunProvider();</span><a href="#l53"></a>
<span id="l54">    }</span><a href="#l54"></a>
<span id="l55"></span><a href="#l55"></a>
<span id="l56">    /** the created digest objects */</span><a href="#l56"></a>
<span id="l57">    HashMap&lt;String, MessageDigest&gt; createdDigests;</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">    /** the digests in use for a given entry*/</span><a href="#l59"></a>
<span id="l60">    ArrayList&lt;MessageDigest&gt; digests;</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    /** the manifest hashes for the digests in use */</span><a href="#l62"></a>
<span id="l63">    ArrayList&lt;byte[]&gt; manifestHashes;</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    private String name = null;</span><a href="#l65"></a>
<span id="l66">    private Manifest man;</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    private boolean skip = true;</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    private JarEntry entry;</span><a href="#l70"></a>
<span id="l71"></span><a href="#l71"></a>
<span id="l72">    private CodeSigner[] signers = null;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    /**</span><a href="#l74"></a>
<span id="l75">     * Create a new ManifestEntryVerifier object.</span><a href="#l75"></a>
<span id="l76">     */</span><a href="#l76"></a>
<span id="l77">    public ManifestEntryVerifier(Manifest man)</span><a href="#l77"></a>
<span id="l78">    {</span><a href="#l78"></a>
<span id="l79">        createdDigests = new HashMap&lt;String, MessageDigest&gt;(11);</span><a href="#l79"></a>
<span id="l80">        digests = new ArrayList&lt;MessageDigest&gt;();</span><a href="#l80"></a>
<span id="l81">        manifestHashes = new ArrayList&lt;byte[]&gt;();</span><a href="#l81"></a>
<span id="l82">        this.man = man;</span><a href="#l82"></a>
<span id="l83">    }</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    /**</span><a href="#l85"></a>
<span id="l86">     * Find the hashes in the</span><a href="#l86"></a>
<span id="l87">     * manifest for this entry, save them, and set the MessageDigest</span><a href="#l87"></a>
<span id="l88">     * objects to calculate the hashes on the fly. If name is</span><a href="#l88"></a>
<span id="l89">     * null it signifies that update/verify should ignore this entry.</span><a href="#l89"></a>
<span id="l90">     */</span><a href="#l90"></a>
<span id="l91">    public void setEntry(String name, JarEntry entry)</span><a href="#l91"></a>
<span id="l92">        throws IOException</span><a href="#l92"></a>
<span id="l93">    {</span><a href="#l93"></a>
<span id="l94">        digests.clear();</span><a href="#l94"></a>
<span id="l95">        manifestHashes.clear();</span><a href="#l95"></a>
<span id="l96">        this.name = name;</span><a href="#l96"></a>
<span id="l97">        this.entry = entry;</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">        skip = true;</span><a href="#l99"></a>
<span id="l100">        signers = null;</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">        if (man == null || name == null) {</span><a href="#l102"></a>
<span id="l103">            return;</span><a href="#l103"></a>
<span id="l104">        }</span><a href="#l104"></a>
<span id="l105"></span><a href="#l105"></a>
<span id="l106">        /* get the headers from the manifest for this entry */</span><a href="#l106"></a>
<span id="l107">        /* if there aren't any, we can't verify any digests for this entry */</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">        skip = false;</span><a href="#l109"></a>
<span id="l110"></span><a href="#l110"></a>
<span id="l111">        Attributes attr = man.getAttributes(name);</span><a href="#l111"></a>
<span id="l112">        if (attr == null) {</span><a href="#l112"></a>
<span id="l113">            // ugh. we should be able to remove this at some point.</span><a href="#l113"></a>
<span id="l114">            // there are broken jars floating around with ./name and /name</span><a href="#l114"></a>
<span id="l115">            // in the manifest, and &quot;name&quot; in the zip/jar file.</span><a href="#l115"></a>
<span id="l116">            attr = man.getAttributes(&quot;./&quot;+name);</span><a href="#l116"></a>
<span id="l117">            if (attr == null) {</span><a href="#l117"></a>
<span id="l118">                attr = man.getAttributes(&quot;/&quot;+name);</span><a href="#l118"></a>
<span id="l119">                if (attr == null)</span><a href="#l119"></a>
<span id="l120">                    return;</span><a href="#l120"></a>
<span id="l121">            }</span><a href="#l121"></a>
<span id="l122">        }</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">        for (Map.Entry&lt;Object,Object&gt; se : attr.entrySet()) {</span><a href="#l124"></a>
<span id="l125">            String key = se.getKey().toString();</span><a href="#l125"></a>
<span id="l126"></span><a href="#l126"></a>
<span id="l127">            if (key.toUpperCase(Locale.ENGLISH).endsWith(&quot;-DIGEST&quot;)) {</span><a href="#l127"></a>
<span id="l128">                // 7 is length of &quot;-Digest&quot;</span><a href="#l128"></a>
<span id="l129">                String algorithm = key.substring(0, key.length()-7);</span><a href="#l129"></a>
<span id="l130"></span><a href="#l130"></a>
<span id="l131">                MessageDigest digest = createdDigests.get(algorithm);</span><a href="#l131"></a>
<span id="l132"></span><a href="#l132"></a>
<span id="l133">                if (digest == null) {</span><a href="#l133"></a>
<span id="l134">                    try {</span><a href="#l134"></a>
<span id="l135"></span><a href="#l135"></a>
<span id="l136">                        digest = MessageDigest.getInstance</span><a href="#l136"></a>
<span id="l137">                                        (algorithm, SunProviderHolder.instance);</span><a href="#l137"></a>
<span id="l138">                        createdDigests.put(algorithm, digest);</span><a href="#l138"></a>
<span id="l139">                    } catch (NoSuchAlgorithmException nsae) {</span><a href="#l139"></a>
<span id="l140">                        // ignore</span><a href="#l140"></a>
<span id="l141">                    }</span><a href="#l141"></a>
<span id="l142">                }</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">                if (digest != null) {</span><a href="#l144"></a>
<span id="l145">                    digest.reset();</span><a href="#l145"></a>
<span id="l146">                    digests.add(digest);</span><a href="#l146"></a>
<span id="l147">                    manifestHashes.add(</span><a href="#l147"></a>
<span id="l148">                                Base64.getMimeDecoder().decode((String)se.getValue()));</span><a href="#l148"></a>
<span id="l149">                }</span><a href="#l149"></a>
<span id="l150">            }</span><a href="#l150"></a>
<span id="l151">        }</span><a href="#l151"></a>
<span id="l152">    }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">    /**</span><a href="#l154"></a>
<span id="l155">     * update the digests for the digests we are interested in</span><a href="#l155"></a>
<span id="l156">     */</span><a href="#l156"></a>
<span id="l157">    public void update(byte buffer) {</span><a href="#l157"></a>
<span id="l158">        if (skip) return;</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">        for (int i=0; i &lt; digests.size(); i++) {</span><a href="#l160"></a>
<span id="l161">            digests.get(i).update(buffer);</span><a href="#l161"></a>
<span id="l162">        }</span><a href="#l162"></a>
<span id="l163">    }</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165">    /**</span><a href="#l165"></a>
<span id="l166">     * update the digests for the digests we are interested in</span><a href="#l166"></a>
<span id="l167">     */</span><a href="#l167"></a>
<span id="l168">    public void update(byte buffer[], int off, int len) {</span><a href="#l168"></a>
<span id="l169">        if (skip) return;</span><a href="#l169"></a>
<span id="l170"></span><a href="#l170"></a>
<span id="l171">        for (int i=0; i &lt; digests.size(); i++) {</span><a href="#l171"></a>
<span id="l172">            digests.get(i).update(buffer, off, len);</span><a href="#l172"></a>
<span id="l173">        }</span><a href="#l173"></a>
<span id="l174">    }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">    /**</span><a href="#l176"></a>
<span id="l177">     * get the JarEntry for this object</span><a href="#l177"></a>
<span id="l178">     */</span><a href="#l178"></a>
<span id="l179">    public JarEntry getEntry()</span><a href="#l179"></a>
<span id="l180">    {</span><a href="#l180"></a>
<span id="l181">        return entry;</span><a href="#l181"></a>
<span id="l182">    }</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">    /**</span><a href="#l184"></a>
<span id="l185">     * go through all the digests, calculating the final digest</span><a href="#l185"></a>
<span id="l186">     * and comparing it to the one in the manifest. If this is</span><a href="#l186"></a>
<span id="l187">     * the first time we have verified this object, remove its</span><a href="#l187"></a>
<span id="l188">     * code signers from sigFileSigners and place in verifiedSigners.</span><a href="#l188"></a>
<span id="l189">     *</span><a href="#l189"></a>
<span id="l190">     *</span><a href="#l190"></a>
<span id="l191">     */</span><a href="#l191"></a>
<span id="l192">    public CodeSigner[] verify(Hashtable&lt;String, CodeSigner[]&gt; verifiedSigners,</span><a href="#l192"></a>
<span id="l193">                Hashtable&lt;String, CodeSigner[]&gt; sigFileSigners)</span><a href="#l193"></a>
<span id="l194">        throws JarException</span><a href="#l194"></a>
<span id="l195">    {</span><a href="#l195"></a>
<span id="l196">        if (skip) {</span><a href="#l196"></a>
<span id="l197">            return null;</span><a href="#l197"></a>
<span id="l198">        }</span><a href="#l198"></a>
<span id="l199"></span><a href="#l199"></a>
<span id="l200">        if (digests.isEmpty()) {</span><a href="#l200"></a>
<span id="l201">            throw new SecurityException(&quot;digest missing for &quot; + name);</span><a href="#l201"></a>
<span id="l202">        }</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">        if (signers != null) {</span><a href="#l204"></a>
<span id="l205">            return signers;</span><a href="#l205"></a>
<span id="l206">        }</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        JarConstraintsParameters params =</span><a href="#l208"></a>
<span id="l209">            getParams(verifiedSigners, sigFileSigners);</span><a href="#l209"></a>
<span id="l210"></span><a href="#l210"></a>
<span id="l211">        for (int i=0; i &lt; digests.size(); i++) {</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">            MessageDigest digest = digests.get(i);</span><a href="#l213"></a>
<span id="l214">            if (params != null) {</span><a href="#l214"></a>
<span id="l215">                try {</span><a href="#l215"></a>
<span id="l216">                    params.setExtendedExceptionMsg(JarFile.MANIFEST_NAME,</span><a href="#l216"></a>
<span id="l217">                        name + &quot; entry&quot;);</span><a href="#l217"></a>
<span id="l218">                    DisabledAlgorithmConstraints.jarConstraints()</span><a href="#l218"></a>
<span id="l219">                           .permits(digest.getAlgorithm(), params);</span><a href="#l219"></a>
<span id="l220">                } catch (GeneralSecurityException e) {</span><a href="#l220"></a>
<span id="l221">                    if (debug != null) {</span><a href="#l221"></a>
<span id="l222">                        debug.println(&quot;Digest algorithm is restricted: &quot; + e);</span><a href="#l222"></a>
<span id="l223">                    }</span><a href="#l223"></a>
<span id="l224">                    return null;</span><a href="#l224"></a>
<span id="l225">                }</span><a href="#l225"></a>
<span id="l226">            }</span><a href="#l226"></a>
<span id="l227">            byte [] manHash = manifestHashes.get(i);</span><a href="#l227"></a>
<span id="l228">            byte [] theHash = digest.digest();</span><a href="#l228"></a>
<span id="l229"></span><a href="#l229"></a>
<span id="l230">            if (debug != null) {</span><a href="#l230"></a>
<span id="l231">                debug.println(&quot;Manifest Entry: &quot; +</span><a href="#l231"></a>
<span id="l232">                                   name + &quot; digest=&quot; + digest.getAlgorithm());</span><a href="#l232"></a>
<span id="l233">                debug.println(&quot;  manifest &quot; + toHex(manHash));</span><a href="#l233"></a>
<span id="l234">                debug.println(&quot;  computed &quot; + toHex(theHash));</span><a href="#l234"></a>
<span id="l235">                debug.println();</span><a href="#l235"></a>
<span id="l236">            }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">            if (!MessageDigest.isEqual(theHash, manHash))</span><a href="#l238"></a>
<span id="l239">                throw new SecurityException(digest.getAlgorithm()+</span><a href="#l239"></a>
<span id="l240">                                            &quot; digest error for &quot;+name);</span><a href="#l240"></a>
<span id="l241">        }</span><a href="#l241"></a>
<span id="l242"></span><a href="#l242"></a>
<span id="l243">        // take it out of sigFileSigners and put it in verifiedSigners...</span><a href="#l243"></a>
<span id="l244">        signers = sigFileSigners.remove(name);</span><a href="#l244"></a>
<span id="l245">        if (signers != null) {</span><a href="#l245"></a>
<span id="l246">            verifiedSigners.put(name, signers);</span><a href="#l246"></a>
<span id="l247">        }</span><a href="#l247"></a>
<span id="l248">        return signers;</span><a href="#l248"></a>
<span id="l249">    }</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">    /**</span><a href="#l251"></a>
<span id="l252">     * Get constraints parameters for JAR. The constraints should be</span><a href="#l252"></a>
<span id="l253">     * checked against all code signers. Returns the parameters,</span><a href="#l253"></a>
<span id="l254">     * or null if the signers for this entry have already been checked.</span><a href="#l254"></a>
<span id="l255">     */</span><a href="#l255"></a>
<span id="l256">    private JarConstraintsParameters getParams(</span><a href="#l256"></a>
<span id="l257">            Map&lt;String, CodeSigner[]&gt; verifiedSigners,</span><a href="#l257"></a>
<span id="l258">            Map&lt;String, CodeSigner[]&gt; sigFileSigners) {</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">        // verifiedSigners is usually preloaded with the Manifest's signers.</span><a href="#l260"></a>
<span id="l261">        // If verifiedSigners contains the Manifest, then it will have all of</span><a href="#l261"></a>
<span id="l262">        // the signers of the JAR. But if it doesn't then we need to fallback</span><a href="#l262"></a>
<span id="l263">        // and check verifiedSigners to see if the signers of this entry have</span><a href="#l263"></a>
<span id="l264">        // been checked already.</span><a href="#l264"></a>
<span id="l265">        if (verifiedSigners.containsKey(JarFile.MANIFEST_NAME)) {</span><a href="#l265"></a>
<span id="l266">            if (verifiedSigners.size() &gt; 1) {</span><a href="#l266"></a>
<span id="l267">                // this means we already checked it previously</span><a href="#l267"></a>
<span id="l268">                return null;</span><a href="#l268"></a>
<span id="l269">            } else {</span><a href="#l269"></a>
<span id="l270">                return new JarConstraintsParameters(</span><a href="#l270"></a>
<span id="l271">                    verifiedSigners.get(JarFile.MANIFEST_NAME));</span><a href="#l271"></a>
<span id="l272">            }</span><a href="#l272"></a>
<span id="l273">        } else {</span><a href="#l273"></a>
<span id="l274">            CodeSigner[] signers = sigFileSigners.get(name);</span><a href="#l274"></a>
<span id="l275">            if (verifiedSigners.containsValue(signers)) {</span><a href="#l275"></a>
<span id="l276">                return null;</span><a href="#l276"></a>
<span id="l277">            } else {</span><a href="#l277"></a>
<span id="l278">                return new JarConstraintsParameters(signers);</span><a href="#l278"></a>
<span id="l279">            }</span><a href="#l279"></a>
<span id="l280">        }</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    // for the toHex function</span><a href="#l283"></a>
<span id="l284">    private static final char[] hexc =</span><a href="#l284"></a>
<span id="l285">            {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};</span><a href="#l285"></a>
<span id="l286">    /**</span><a href="#l286"></a>
<span id="l287">     * convert a byte array to a hex string for debugging purposes</span><a href="#l287"></a>
<span id="l288">     * @param data the binary data to be converted to a hex string</span><a href="#l288"></a>
<span id="l289">     * @return an ASCII hex string</span><a href="#l289"></a>
<span id="l290">     */</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">    static String toHex(byte[] data) {</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">        StringBuffer sb = new StringBuffer(data.length*2);</span><a href="#l294"></a>
<span id="l295"></span><a href="#l295"></a>
<span id="l296">        for (int i=0; i&lt;data.length; i++) {</span><a href="#l296"></a>
<span id="l297">            sb.append(hexc[(data[i] &gt;&gt;4) &amp; 0x0f]);</span><a href="#l297"></a>
<span id="l298">            sb.append(hexc[data[i] &amp; 0x0f]);</span><a href="#l298"></a>
<span id="l299">        }</span><a href="#l299"></a>
<span id="l300">        return sb.toString();</span><a href="#l300"></a>
<span id="l301">    }</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">}</span><a href="#l303"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

