<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cc7f20a9beb2/src/share/classes/sun/security/provider/certpath/DistributionPointFetcher.java">cc7f20a9beb2</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 2002, 2017, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.provider.certpath;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.*;</span><a href="#l28"></a>
<span id="l29">import java.net.URI;</span><a href="#l29"></a>
<span id="l30">import java.security.*;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.*;</span><a href="#l31"></a>
<span id="l32">import javax.security.auth.x500.X500Principal;</span><a href="#l32"></a>
<span id="l33">import java.util.*;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">import sun.security.util.Debug;</span><a href="#l35"></a>
<span id="l36">import sun.security.validator.Validator;</span><a href="#l36"></a>
<span id="l37">import static sun.security.x509.PKIXExtensions.*;</span><a href="#l37"></a>
<span id="l38">import sun.security.x509.*;</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">/**</span><a href="#l40"></a>
<span id="l41"> * Class to obtain CRLs via the CRLDistributionPoints extension.</span><a href="#l41"></a>
<span id="l42"> * Note that the functionality of this class must be explicitly enabled</span><a href="#l42"></a>
<span id="l43"> * via a system property, see the USE_CRLDP variable below.</span><a href="#l43"></a>
<span id="l44"> *</span><a href="#l44"></a>
<span id="l45"> * This class uses the URICertStore class to fetch CRLs. The URICertStore</span><a href="#l45"></a>
<span id="l46"> * class also implements CRL caching: see the class description for more</span><a href="#l46"></a>
<span id="l47"> * information.</span><a href="#l47"></a>
<span id="l48"> *</span><a href="#l48"></a>
<span id="l49"> * @author Andreas Sterbenz</span><a href="#l49"></a>
<span id="l50"> * @author Sean Mullan</span><a href="#l50"></a>
<span id="l51"> * @since 1.4.2</span><a href="#l51"></a>
<span id="l52"> */</span><a href="#l52"></a>
<span id="l53">public class DistributionPointFetcher {</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">    private static final boolean[] ALL_REASONS =</span><a href="#l57"></a>
<span id="l58">        {true, true, true, true, true, true, true, true, true};</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">    /**</span><a href="#l60"></a>
<span id="l61">     * Private instantiation only.</span><a href="#l61"></a>
<span id="l62">     */</span><a href="#l62"></a>
<span id="l63">    private DistributionPointFetcher() {}</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    /**</span><a href="#l65"></a>
<span id="l66">     * Return the X509CRLs matching this selector. The selector must be</span><a href="#l66"></a>
<span id="l67">     * an X509CRLSelector with certificateChecking set.</span><a href="#l67"></a>
<span id="l68">     */</span><a href="#l68"></a>
<span id="l69">    public static Collection&lt;X509CRL&gt; getCRLs(X509CRLSelector selector,</span><a href="#l69"></a>
<span id="l70">            boolean signFlag, PublicKey prevKey, String provider,</span><a href="#l70"></a>
<span id="l71">            List&lt;CertStore&gt; certStores, boolean[] reasonsMask,</span><a href="#l71"></a>
<span id="l72">            Set&lt;TrustAnchor&gt; trustAnchors, Date validity, String variant)</span><a href="#l72"></a>
<span id="l73">            throws CertStoreException</span><a href="#l73"></a>
<span id="l74">    {</span><a href="#l74"></a>
<span id="l75">        return getCRLs(selector, signFlag, prevKey, null, provider, certStores,</span><a href="#l75"></a>
<span id="l76">                reasonsMask, trustAnchors, validity, variant, null);</span><a href="#l76"></a>
<span id="l77">    }</span><a href="#l77"></a>
<span id="l78">    /**</span><a href="#l78"></a>
<span id="l79">     * Return the X509CRLs matching this selector. The selector must be</span><a href="#l79"></a>
<span id="l80">     * an X509CRLSelector with certificateChecking set.</span><a href="#l80"></a>
<span id="l81">     */</span><a href="#l81"></a>
<span id="l82">    // Called by com.sun.deploy.security.RevocationChecker</span><a href="#l82"></a>
<span id="l83">    public static Collection&lt;X509CRL&gt; getCRLs(X509CRLSelector selector,</span><a href="#l83"></a>
<span id="l84">                                              boolean signFlag,</span><a href="#l84"></a>
<span id="l85">                                              PublicKey prevKey,</span><a href="#l85"></a>
<span id="l86">                                              String provider,</span><a href="#l86"></a>
<span id="l87">                                              List&lt;CertStore&gt; certStores,</span><a href="#l87"></a>
<span id="l88">                                              boolean[] reasonsMask,</span><a href="#l88"></a>
<span id="l89">                                              Set&lt;TrustAnchor&gt; trustAnchors,</span><a href="#l89"></a>
<span id="l90">                                              Date validity)</span><a href="#l90"></a>
<span id="l91">        throws CertStoreException</span><a href="#l91"></a>
<span id="l92">    {</span><a href="#l92"></a>
<span id="l93">        if (trustAnchors.isEmpty()) {</span><a href="#l93"></a>
<span id="l94">            throw new CertStoreException(</span><a href="#l94"></a>
<span id="l95">                &quot;at least one TrustAnchor must be specified&quot;);</span><a href="#l95"></a>
<span id="l96">        }</span><a href="#l96"></a>
<span id="l97">        TrustAnchor anchor = trustAnchors.iterator().next();</span><a href="#l97"></a>
<span id="l98">        return getCRLs(selector, signFlag, prevKey, null, provider, certStores,</span><a href="#l98"></a>
<span id="l99">                reasonsMask, trustAnchors, validity,</span><a href="#l99"></a>
<span id="l100">                Validator.VAR_PLUGIN_CODE_SIGNING, anchor);</span><a href="#l100"></a>
<span id="l101">    }</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">    /**</span><a href="#l103"></a>
<span id="l104">     * Return the X509CRLs matching this selector. The selector must be</span><a href="#l104"></a>
<span id="l105">     * an X509CRLSelector with certificateChecking set.</span><a href="#l105"></a>
<span id="l106">     */</span><a href="#l106"></a>
<span id="l107">    public static Collection&lt;X509CRL&gt; getCRLs(X509CRLSelector selector,</span><a href="#l107"></a>
<span id="l108">                                              boolean signFlag,</span><a href="#l108"></a>
<span id="l109">                                              PublicKey prevKey,</span><a href="#l109"></a>
<span id="l110">                                              X509Certificate prevCert,</span><a href="#l110"></a>
<span id="l111">                                              String provider,</span><a href="#l111"></a>
<span id="l112">                                              List&lt;CertStore&gt; certStores,</span><a href="#l112"></a>
<span id="l113">                                              boolean[] reasonsMask,</span><a href="#l113"></a>
<span id="l114">                                              Set&lt;TrustAnchor&gt; trustAnchors,</span><a href="#l114"></a>
<span id="l115">                                              Date validity,</span><a href="#l115"></a>
<span id="l116">                                              String variant,</span><a href="#l116"></a>
<span id="l117">                                              TrustAnchor anchor)</span><a href="#l117"></a>
<span id="l118">        throws CertStoreException</span><a href="#l118"></a>
<span id="l119">    {</span><a href="#l119"></a>
<span id="l120">        X509Certificate cert = selector.getCertificateChecking();</span><a href="#l120"></a>
<span id="l121">        if (cert == null) {</span><a href="#l121"></a>
<span id="l122">            return Collections.emptySet();</span><a href="#l122"></a>
<span id="l123">        }</span><a href="#l123"></a>
<span id="l124">        try {</span><a href="#l124"></a>
<span id="l125">            X509CertImpl certImpl = X509CertImpl.toImpl(cert);</span><a href="#l125"></a>
<span id="l126">            if (debug != null) {</span><a href="#l126"></a>
<span id="l127">                debug.println(&quot;DistributionPointFetcher.getCRLs: Checking &quot;</span><a href="#l127"></a>
<span id="l128">                        + &quot;CRLDPs for &quot; + certImpl.getSubjectX500Principal());</span><a href="#l128"></a>
<span id="l129">            }</span><a href="#l129"></a>
<span id="l130">            CRLDistributionPointsExtension ext =</span><a href="#l130"></a>
<span id="l131">                certImpl.getCRLDistributionPointsExtension();</span><a href="#l131"></a>
<span id="l132">            if (ext == null) {</span><a href="#l132"></a>
<span id="l133">                if (debug != null) {</span><a href="#l133"></a>
<span id="l134">                    debug.println(&quot;No CRLDP ext&quot;);</span><a href="#l134"></a>
<span id="l135">                }</span><a href="#l135"></a>
<span id="l136">                return Collections.emptySet();</span><a href="#l136"></a>
<span id="l137">            }</span><a href="#l137"></a>
<span id="l138">            List&lt;DistributionPoint&gt; points =</span><a href="#l138"></a>
<span id="l139">                    ext.get(CRLDistributionPointsExtension.POINTS);</span><a href="#l139"></a>
<span id="l140">            Set&lt;X509CRL&gt; results = new HashSet&lt;&gt;();</span><a href="#l140"></a>
<span id="l141">            for (Iterator&lt;DistributionPoint&gt; t = points.iterator();</span><a href="#l141"></a>
<span id="l142">                 t.hasNext() &amp;&amp; !Arrays.equals(reasonsMask, ALL_REASONS); ) {</span><a href="#l142"></a>
<span id="l143">                DistributionPoint point = t.next();</span><a href="#l143"></a>
<span id="l144">                Collection&lt;X509CRL&gt; crls = getCRLs(selector, certImpl,</span><a href="#l144"></a>
<span id="l145">                    point, reasonsMask, signFlag, prevKey, prevCert, provider,</span><a href="#l145"></a>
<span id="l146">                    certStores, trustAnchors, validity, variant, anchor);</span><a href="#l146"></a>
<span id="l147">                results.addAll(crls);</span><a href="#l147"></a>
<span id="l148">            }</span><a href="#l148"></a>
<span id="l149">            if (debug != null) {</span><a href="#l149"></a>
<span id="l150">                debug.println(&quot;Returning &quot; + results.size() + &quot; CRLs&quot;);</span><a href="#l150"></a>
<span id="l151">            }</span><a href="#l151"></a>
<span id="l152">            return results;</span><a href="#l152"></a>
<span id="l153">        } catch (CertificateException | IOException e) {</span><a href="#l153"></a>
<span id="l154">            return Collections.emptySet();</span><a href="#l154"></a>
<span id="l155">        }</span><a href="#l155"></a>
<span id="l156">    }</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">    /**</span><a href="#l158"></a>
<span id="l159">     * Download CRLs from the given distribution point, verify and return them.</span><a href="#l159"></a>
<span id="l160">     * See the top of the class for current limitations.</span><a href="#l160"></a>
<span id="l161">     *</span><a href="#l161"></a>
<span id="l162">     * @throws CertStoreException if there is an error retrieving the CRLs</span><a href="#l162"></a>
<span id="l163">     *         from one of the GeneralNames and no other CRLs are retrieved from</span><a href="#l163"></a>
<span id="l164">     *         the other GeneralNames. If more than one GeneralName throws an</span><a href="#l164"></a>
<span id="l165">     *         exception then the one from the last GeneralName is thrown.</span><a href="#l165"></a>
<span id="l166">     */</span><a href="#l166"></a>
<span id="l167">    private static Collection&lt;X509CRL&gt; getCRLs(X509CRLSelector selector,</span><a href="#l167"></a>
<span id="l168">        X509CertImpl certImpl, DistributionPoint point, boolean[] reasonsMask,</span><a href="#l168"></a>
<span id="l169">        boolean signFlag, PublicKey prevKey, X509Certificate prevCert,</span><a href="#l169"></a>
<span id="l170">        String provider, List&lt;CertStore&gt; certStores,</span><a href="#l170"></a>
<span id="l171">        Set&lt;TrustAnchor&gt; trustAnchors, Date validity, String variant,</span><a href="#l171"></a>
<span id="l172">        TrustAnchor anchor)</span><a href="#l172"></a>
<span id="l173">            throws CertStoreException {</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">        // check for full name</span><a href="#l175"></a>
<span id="l176">        GeneralNames fullName = point.getFullName();</span><a href="#l176"></a>
<span id="l177">        if (fullName == null) {</span><a href="#l177"></a>
<span id="l178">            // check for relative name</span><a href="#l178"></a>
<span id="l179">            RDN relativeName = point.getRelativeName();</span><a href="#l179"></a>
<span id="l180">            if (relativeName == null) {</span><a href="#l180"></a>
<span id="l181">                return Collections.emptySet();</span><a href="#l181"></a>
<span id="l182">            }</span><a href="#l182"></a>
<span id="l183">            try {</span><a href="#l183"></a>
<span id="l184">                GeneralNames crlIssuers = point.getCRLIssuer();</span><a href="#l184"></a>
<span id="l185">                if (crlIssuers == null) {</span><a href="#l185"></a>
<span id="l186">                    fullName = getFullNames</span><a href="#l186"></a>
<span id="l187">                        ((X500Name) certImpl.getIssuerDN(), relativeName);</span><a href="#l187"></a>
<span id="l188">                } else {</span><a href="#l188"></a>
<span id="l189">                    // should only be one CRL Issuer</span><a href="#l189"></a>
<span id="l190">                    if (crlIssuers.size() != 1) {</span><a href="#l190"></a>
<span id="l191">                        return Collections.emptySet();</span><a href="#l191"></a>
<span id="l192">                    } else {</span><a href="#l192"></a>
<span id="l193">                        fullName = getFullNames</span><a href="#l193"></a>
<span id="l194">                            ((X500Name) crlIssuers.get(0).getName(), relativeName);</span><a href="#l194"></a>
<span id="l195">                    }</span><a href="#l195"></a>
<span id="l196">                }</span><a href="#l196"></a>
<span id="l197">            } catch (IOException ioe) {</span><a href="#l197"></a>
<span id="l198">                return Collections.emptySet();</span><a href="#l198"></a>
<span id="l199">            }</span><a href="#l199"></a>
<span id="l200">        }</span><a href="#l200"></a>
<span id="l201">        Collection&lt;X509CRL&gt; possibleCRLs = new ArrayList&lt;&gt;();</span><a href="#l201"></a>
<span id="l202">        CertStoreException savedCSE = null;</span><a href="#l202"></a>
<span id="l203">        for (Iterator&lt;GeneralName&gt; t = fullName.iterator(); t.hasNext(); ) {</span><a href="#l203"></a>
<span id="l204">            try {</span><a href="#l204"></a>
<span id="l205">                GeneralName name = t.next();</span><a href="#l205"></a>
<span id="l206">                if (name.getType() == GeneralNameInterface.NAME_DIRECTORY) {</span><a href="#l206"></a>
<span id="l207">                    X500Name x500Name = (X500Name) name.getName();</span><a href="#l207"></a>
<span id="l208">                    possibleCRLs.addAll(</span><a href="#l208"></a>
<span id="l209">                        getCRLs(x500Name, certImpl.getIssuerX500Principal(),</span><a href="#l209"></a>
<span id="l210">                                certStores));</span><a href="#l210"></a>
<span id="l211">                } else if (name.getType() == GeneralNameInterface.NAME_URI) {</span><a href="#l211"></a>
<span id="l212">                    URIName uriName = (URIName)name.getName();</span><a href="#l212"></a>
<span id="l213">                    X509CRL crl = getCRL(uriName);</span><a href="#l213"></a>
<span id="l214">                    if (crl != null) {</span><a href="#l214"></a>
<span id="l215">                        possibleCRLs.add(crl);</span><a href="#l215"></a>
<span id="l216">                    }</span><a href="#l216"></a>
<span id="l217">                }</span><a href="#l217"></a>
<span id="l218">            } catch (CertStoreException cse) {</span><a href="#l218"></a>
<span id="l219">                savedCSE = cse;</span><a href="#l219"></a>
<span id="l220">            }</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222">        // only throw CertStoreException if no CRLs are retrieved</span><a href="#l222"></a>
<span id="l223">        if (possibleCRLs.isEmpty() &amp;&amp; savedCSE != null) {</span><a href="#l223"></a>
<span id="l224">            throw savedCSE;</span><a href="#l224"></a>
<span id="l225">        }</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">        Collection&lt;X509CRL&gt; crls = new ArrayList&lt;&gt;(2);</span><a href="#l227"></a>
<span id="l228">        for (X509CRL crl : possibleCRLs) {</span><a href="#l228"></a>
<span id="l229">            try {</span><a href="#l229"></a>
<span id="l230">                // make sure issuer is not set</span><a href="#l230"></a>
<span id="l231">                // we check the issuer in verifyCRLs method</span><a href="#l231"></a>
<span id="l232">                selector.setIssuerNames(null);</span><a href="#l232"></a>
<span id="l233">                if (selector.match(crl) &amp;&amp; verifyCRL(certImpl, point, crl,</span><a href="#l233"></a>
<span id="l234">                        reasonsMask, signFlag, prevKey, prevCert, provider,</span><a href="#l234"></a>
<span id="l235">                        trustAnchors, certStores, validity, variant, anchor)) {</span><a href="#l235"></a>
<span id="l236">                    crls.add(crl);</span><a href="#l236"></a>
<span id="l237">                }</span><a href="#l237"></a>
<span id="l238">            } catch (IOException | CRLException e) {</span><a href="#l238"></a>
<span id="l239">                // don't add the CRL</span><a href="#l239"></a>
<span id="l240">                if (debug != null) {</span><a href="#l240"></a>
<span id="l241">                    debug.println(&quot;Exception verifying CRL: &quot; + e.getMessage());</span><a href="#l241"></a>
<span id="l242">                    e.printStackTrace();</span><a href="#l242"></a>
<span id="l243">                }</span><a href="#l243"></a>
<span id="l244">            }</span><a href="#l244"></a>
<span id="l245">        }</span><a href="#l245"></a>
<span id="l246">        return crls;</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    /**</span><a href="#l249"></a>
<span id="l250">     * Download CRL from given URI.</span><a href="#l250"></a>
<span id="l251">     */</span><a href="#l251"></a>
<span id="l252">    private static X509CRL getCRL(URIName name) throws CertStoreException {</span><a href="#l252"></a>
<span id="l253">        URI uri = name.getURI();</span><a href="#l253"></a>
<span id="l254">        if (debug != null) {</span><a href="#l254"></a>
<span id="l255">            debug.println(&quot;Trying to fetch CRL from DP &quot; + uri);</span><a href="#l255"></a>
<span id="l256">        }</span><a href="#l256"></a>
<span id="l257">        CertStore ucs = null;</span><a href="#l257"></a>
<span id="l258">        try {</span><a href="#l258"></a>
<span id="l259">            ucs = URICertStore.getInstance</span><a href="#l259"></a>
<span id="l260">                (new URICertStore.URICertStoreParameters(uri));</span><a href="#l260"></a>
<span id="l261">        } catch (InvalidAlgorithmParameterException |</span><a href="#l261"></a>
<span id="l262">                 NoSuchAlgorithmException e) {</span><a href="#l262"></a>
<span id="l263">            if (debug != null) {</span><a href="#l263"></a>
<span id="l264">                debug.println(&quot;Can't create URICertStore: &quot; + e.getMessage());</span><a href="#l264"></a>
<span id="l265">            }</span><a href="#l265"></a>
<span id="l266">            return null;</span><a href="#l266"></a>
<span id="l267">        }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        Collection&lt;? extends CRL&gt; crls = ucs.getCRLs(null);</span><a href="#l269"></a>
<span id="l270">        if (crls.isEmpty()) {</span><a href="#l270"></a>
<span id="l271">            return null;</span><a href="#l271"></a>
<span id="l272">        } else {</span><a href="#l272"></a>
<span id="l273">            return (X509CRL) crls.iterator().next();</span><a href="#l273"></a>
<span id="l274">        }</span><a href="#l274"></a>
<span id="l275">    }</span><a href="#l275"></a>
<span id="l276"></span><a href="#l276"></a>
<span id="l277">    /**</span><a href="#l277"></a>
<span id="l278">     * Fetch CRLs from certStores.</span><a href="#l278"></a>
<span id="l279">     *</span><a href="#l279"></a>
<span id="l280">     * @throws CertStoreException if there is an error retrieving the CRLs from</span><a href="#l280"></a>
<span id="l281">     *         one of the CertStores and no other CRLs are retrieved from</span><a href="#l281"></a>
<span id="l282">     *         the other CertStores. If more than one CertStore throws an</span><a href="#l282"></a>
<span id="l283">     *         exception then the one from the last CertStore is thrown.</span><a href="#l283"></a>
<span id="l284">     */</span><a href="#l284"></a>
<span id="l285">    private static Collection&lt;X509CRL&gt; getCRLs(X500Name name,</span><a href="#l285"></a>
<span id="l286">                                               X500Principal certIssuer,</span><a href="#l286"></a>
<span id="l287">                                               List&lt;CertStore&gt; certStores)</span><a href="#l287"></a>
<span id="l288">        throws CertStoreException</span><a href="#l288"></a>
<span id="l289">    {</span><a href="#l289"></a>
<span id="l290">        if (debug != null) {</span><a href="#l290"></a>
<span id="l291">            debug.println(&quot;Trying to fetch CRL from DP &quot; + name);</span><a href="#l291"></a>
<span id="l292">        }</span><a href="#l292"></a>
<span id="l293">        X509CRLSelector xcs = new X509CRLSelector();</span><a href="#l293"></a>
<span id="l294">        xcs.addIssuer(name.asX500Principal());</span><a href="#l294"></a>
<span id="l295">        xcs.addIssuer(certIssuer);</span><a href="#l295"></a>
<span id="l296">        Collection&lt;X509CRL&gt; crls = new ArrayList&lt;&gt;();</span><a href="#l296"></a>
<span id="l297">        CertStoreException savedCSE = null;</span><a href="#l297"></a>
<span id="l298">        for (CertStore store : certStores) {</span><a href="#l298"></a>
<span id="l299">            try {</span><a href="#l299"></a>
<span id="l300">                for (CRL crl : store.getCRLs(xcs)) {</span><a href="#l300"></a>
<span id="l301">                    crls.add((X509CRL)crl);</span><a href="#l301"></a>
<span id="l302">                }</span><a href="#l302"></a>
<span id="l303">            } catch (CertStoreException cse) {</span><a href="#l303"></a>
<span id="l304">                if (debug != null) {</span><a href="#l304"></a>
<span id="l305">                    debug.println(&quot;Exception while retrieving &quot; +</span><a href="#l305"></a>
<span id="l306">                        &quot;CRLs: &quot; + cse);</span><a href="#l306"></a>
<span id="l307">                    cse.printStackTrace();</span><a href="#l307"></a>
<span id="l308">                }</span><a href="#l308"></a>
<span id="l309">                savedCSE = new PKIX.CertStoreTypeException(store.getType(),cse);</span><a href="#l309"></a>
<span id="l310">            }</span><a href="#l310"></a>
<span id="l311">        }</span><a href="#l311"></a>
<span id="l312">        // only throw CertStoreException if no CRLs are retrieved</span><a href="#l312"></a>
<span id="l313">        if (crls.isEmpty() &amp;&amp; savedCSE != null) {</span><a href="#l313"></a>
<span id="l314">            throw savedCSE;</span><a href="#l314"></a>
<span id="l315">        } else {</span><a href="#l315"></a>
<span id="l316">            return crls;</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">    }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    /**</span><a href="#l320"></a>
<span id="l321">     * Verifies a CRL for the given certificate's Distribution Point to</span><a href="#l321"></a>
<span id="l322">     * ensure it is appropriate for checking the revocation status.</span><a href="#l322"></a>
<span id="l323">     *</span><a href="#l323"></a>
<span id="l324">     * @param certImpl the certificate whose revocation status is being checked</span><a href="#l324"></a>
<span id="l325">     * @param point one of the distribution points of the certificate</span><a href="#l325"></a>
<span id="l326">     * @param crl the CRL</span><a href="#l326"></a>
<span id="l327">     * @param reasonsMask the interim reasons mask</span><a href="#l327"></a>
<span id="l328">     * @param signFlag true if prevKey can be used to verify the CRL</span><a href="#l328"></a>
<span id="l329">     * @param prevKey the public key that verifies the certificate's signature</span><a href="#l329"></a>
<span id="l330">     * @param prevCert the certificate whose public key verifies</span><a href="#l330"></a>
<span id="l331">     *        {@code certImpl}'s signature</span><a href="#l331"></a>
<span id="l332">     * @param provider the Signature provider to use</span><a href="#l332"></a>
<span id="l333">     * @param trustAnchors a {@code Set} of {@code TrustAnchor}s</span><a href="#l333"></a>
<span id="l334">     * @param certStores a {@code List} of {@code CertStore}s to be used in</span><a href="#l334"></a>
<span id="l335">     *        finding certificates and CRLs</span><a href="#l335"></a>
<span id="l336">     * @param validity the time for which the validity of the CRL issuer's</span><a href="#l336"></a>
<span id="l337">     *        certification path should be determined</span><a href="#l337"></a>
<span id="l338">     * @return true if ok, false if not</span><a href="#l338"></a>
<span id="l339">     */</span><a href="#l339"></a>
<span id="l340">    static boolean verifyCRL(X509CertImpl certImpl, DistributionPoint point,</span><a href="#l340"></a>
<span id="l341">        X509CRL crl, boolean[] reasonsMask, boolean signFlag,</span><a href="#l341"></a>
<span id="l342">        PublicKey prevKey, X509Certificate prevCert, String provider,</span><a href="#l342"></a>
<span id="l343">        Set&lt;TrustAnchor&gt; trustAnchors, List&lt;CertStore&gt; certStores,</span><a href="#l343"></a>
<span id="l344">        Date validity, String variant, TrustAnchor anchor)</span><a href="#l344"></a>
<span id="l345">        throws CRLException, IOException {</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">        if (debug != null) {</span><a href="#l347"></a>
<span id="l348">            debug.println(&quot;DistributionPointFetcher.verifyCRL: &quot; +</span><a href="#l348"></a>
<span id="l349">                &quot;checking revocation status for&quot; +</span><a href="#l349"></a>
<span id="l350">                &quot;\n  SN: &quot; + Debug.toHexString(certImpl.getSerialNumber()) +</span><a href="#l350"></a>
<span id="l351">                &quot;\n  Subject: &quot; + certImpl.getSubjectX500Principal() +</span><a href="#l351"></a>
<span id="l352">                &quot;\n  Issuer: &quot; + certImpl.getIssuerX500Principal());</span><a href="#l352"></a>
<span id="l353">        }</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">        boolean indirectCRL = false;</span><a href="#l355"></a>
<span id="l356">        X509CRLImpl crlImpl = X509CRLImpl.toImpl(crl);</span><a href="#l356"></a>
<span id="l357">        IssuingDistributionPointExtension idpExt =</span><a href="#l357"></a>
<span id="l358">            crlImpl.getIssuingDistributionPointExtension();</span><a href="#l358"></a>
<span id="l359">        X500Name certIssuer = (X500Name) certImpl.getIssuerDN();</span><a href="#l359"></a>
<span id="l360">        X500Name crlIssuer = (X500Name) crlImpl.getIssuerDN();</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362">        // if crlIssuer is set, verify that it matches the issuer of the</span><a href="#l362"></a>
<span id="l363">        // CRL and the CRL contains an IDP extension with the indirectCRL</span><a href="#l363"></a>
<span id="l364">        // boolean asserted. Otherwise, verify that the CRL issuer matches the</span><a href="#l364"></a>
<span id="l365">        // certificate issuer.</span><a href="#l365"></a>
<span id="l366">        GeneralNames pointCrlIssuers = point.getCRLIssuer();</span><a href="#l366"></a>
<span id="l367">        X500Name pointCrlIssuer = null;</span><a href="#l367"></a>
<span id="l368">        if (pointCrlIssuers != null) {</span><a href="#l368"></a>
<span id="l369">            if (idpExt == null ||</span><a href="#l369"></a>
<span id="l370">                ((Boolean) idpExt.get</span><a href="#l370"></a>
<span id="l371">                    (IssuingDistributionPointExtension.INDIRECT_CRL)).equals</span><a href="#l371"></a>
<span id="l372">                        (Boolean.FALSE)) {</span><a href="#l372"></a>
<span id="l373">                return false;</span><a href="#l373"></a>
<span id="l374">            }</span><a href="#l374"></a>
<span id="l375">            boolean match = false;</span><a href="#l375"></a>
<span id="l376">            for (Iterator&lt;GeneralName&gt; t = pointCrlIssuers.iterator();</span><a href="#l376"></a>
<span id="l377">                 !match &amp;&amp; t.hasNext(); ) {</span><a href="#l377"></a>
<span id="l378">                GeneralNameInterface name = t.next().getName();</span><a href="#l378"></a>
<span id="l379">                if (crlIssuer.equals(name) == true) {</span><a href="#l379"></a>
<span id="l380">                    pointCrlIssuer = (X500Name) name;</span><a href="#l380"></a>
<span id="l381">                    match = true;</span><a href="#l381"></a>
<span id="l382">                }</span><a href="#l382"></a>
<span id="l383">            }</span><a href="#l383"></a>
<span id="l384">            if (match == false) {</span><a href="#l384"></a>
<span id="l385">                return false;</span><a href="#l385"></a>
<span id="l386">            }</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">            // we accept the case that a CRL issuer provide status</span><a href="#l388"></a>
<span id="l389">            // information for itself.</span><a href="#l389"></a>
<span id="l390">            if (issues(certImpl, crlImpl, provider)) {</span><a href="#l390"></a>
<span id="l391">                // reset the public key used to verify the CRL's signature</span><a href="#l391"></a>
<span id="l392">                prevKey = certImpl.getPublicKey();</span><a href="#l392"></a>
<span id="l393">            } else {</span><a href="#l393"></a>
<span id="l394">                indirectCRL = true;</span><a href="#l394"></a>
<span id="l395">            }</span><a href="#l395"></a>
<span id="l396">        } else if (crlIssuer.equals(certIssuer) == false) {</span><a href="#l396"></a>
<span id="l397">            if (debug != null) {</span><a href="#l397"></a>
<span id="l398">                debug.println(&quot;crl issuer does not equal cert issuer.\n&quot; +</span><a href="#l398"></a>
<span id="l399">                              &quot;crl issuer: &quot; + crlIssuer + &quot;\n&quot; +</span><a href="#l399"></a>
<span id="l400">                              &quot;cert issuer: &quot; + certIssuer);</span><a href="#l400"></a>
<span id="l401">            }</span><a href="#l401"></a>
<span id="l402">            return false;</span><a href="#l402"></a>
<span id="l403">        } else {</span><a href="#l403"></a>
<span id="l404">            // in case of self-issued indirect CRL issuer.</span><a href="#l404"></a>
<span id="l405">            KeyIdentifier certAKID = certImpl.getAuthKeyId();</span><a href="#l405"></a>
<span id="l406">            KeyIdentifier crlAKID = crlImpl.getAuthKeyId();</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">            if (certAKID == null || crlAKID == null) {</span><a href="#l408"></a>
<span id="l409">                // cannot recognize indirect CRL without AKID</span><a href="#l409"></a>
<span id="l410"></span><a href="#l410"></a>
<span id="l411">                // we accept the case that a CRL issuer provide status</span><a href="#l411"></a>
<span id="l412">                // information for itself.</span><a href="#l412"></a>
<span id="l413">                if (issues(certImpl, crlImpl, provider)) {</span><a href="#l413"></a>
<span id="l414">                    // reset the public key used to verify the CRL's signature</span><a href="#l414"></a>
<span id="l415">                    prevKey = certImpl.getPublicKey();</span><a href="#l415"></a>
<span id="l416">                }</span><a href="#l416"></a>
<span id="l417">            } else if (!certAKID.equals(crlAKID)) {</span><a href="#l417"></a>
<span id="l418">                // we accept the case that a CRL issuer provide status</span><a href="#l418"></a>
<span id="l419">                // information for itself.</span><a href="#l419"></a>
<span id="l420">                if (issues(certImpl, crlImpl, provider)) {</span><a href="#l420"></a>
<span id="l421">                    // reset the public key used to verify the CRL's signature</span><a href="#l421"></a>
<span id="l422">                    prevKey = certImpl.getPublicKey();</span><a href="#l422"></a>
<span id="l423">                } else {</span><a href="#l423"></a>
<span id="l424">                    indirectCRL = true;</span><a href="#l424"></a>
<span id="l425">                }</span><a href="#l425"></a>
<span id="l426">            }</span><a href="#l426"></a>
<span id="l427">        }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">        if (!indirectCRL &amp;&amp; !signFlag) {</span><a href="#l429"></a>
<span id="l430">            // cert's key cannot be used to verify the CRL</span><a href="#l430"></a>
<span id="l431">            return false;</span><a href="#l431"></a>
<span id="l432">        }</span><a href="#l432"></a>
<span id="l433"></span><a href="#l433"></a>
<span id="l434">        if (idpExt != null) {</span><a href="#l434"></a>
<span id="l435">            DistributionPointName idpPoint = (DistributionPointName)</span><a href="#l435"></a>
<span id="l436">                idpExt.get(IssuingDistributionPointExtension.POINT);</span><a href="#l436"></a>
<span id="l437">            if (idpPoint != null) {</span><a href="#l437"></a>
<span id="l438">                GeneralNames idpNames = idpPoint.getFullName();</span><a href="#l438"></a>
<span id="l439">                if (idpNames == null) {</span><a href="#l439"></a>
<span id="l440">                    RDN relativeName = idpPoint.getRelativeName();</span><a href="#l440"></a>
<span id="l441">                    if (relativeName == null) {</span><a href="#l441"></a>
<span id="l442">                        if (debug != null) {</span><a href="#l442"></a>
<span id="l443">                           debug.println(&quot;IDP must be relative or full DN&quot;);</span><a href="#l443"></a>
<span id="l444">                        }</span><a href="#l444"></a>
<span id="l445">                        return false;</span><a href="#l445"></a>
<span id="l446">                    }</span><a href="#l446"></a>
<span id="l447">                    if (debug != null) {</span><a href="#l447"></a>
<span id="l448">                        debug.println(&quot;IDP relativeName:&quot; + relativeName);</span><a href="#l448"></a>
<span id="l449">                    }</span><a href="#l449"></a>
<span id="l450">                    idpNames = getFullNames(crlIssuer, relativeName);</span><a href="#l450"></a>
<span id="l451">                }</span><a href="#l451"></a>
<span id="l452">                // if the DP name is present in the IDP CRL extension and the</span><a href="#l452"></a>
<span id="l453">                // DP field is present in the DP, then verify that one of the</span><a href="#l453"></a>
<span id="l454">                // names in the IDP matches one of the names in the DP</span><a href="#l454"></a>
<span id="l455">                if (point.getFullName() != null ||</span><a href="#l455"></a>
<span id="l456">                    point.getRelativeName() != null) {</span><a href="#l456"></a>
<span id="l457">                    GeneralNames pointNames = point.getFullName();</span><a href="#l457"></a>
<span id="l458">                    if (pointNames == null) {</span><a href="#l458"></a>
<span id="l459">                        RDN relativeName = point.getRelativeName();</span><a href="#l459"></a>
<span id="l460">                        if (relativeName == null) {</span><a href="#l460"></a>
<span id="l461">                            if (debug != null) {</span><a href="#l461"></a>
<span id="l462">                                debug.println(&quot;DP must be relative or full DN&quot;);</span><a href="#l462"></a>
<span id="l463">                            }</span><a href="#l463"></a>
<span id="l464">                            return false;</span><a href="#l464"></a>
<span id="l465">                        }</span><a href="#l465"></a>
<span id="l466">                        if (debug != null) {</span><a href="#l466"></a>
<span id="l467">                            debug.println(&quot;DP relativeName:&quot; + relativeName);</span><a href="#l467"></a>
<span id="l468">                        }</span><a href="#l468"></a>
<span id="l469">                        if (indirectCRL) {</span><a href="#l469"></a>
<span id="l470">                            if (pointCrlIssuers.size() != 1) {</span><a href="#l470"></a>
<span id="l471">                                // RFC 5280: there must be only 1 CRL issuer</span><a href="#l471"></a>
<span id="l472">                                // name when relativeName is present</span><a href="#l472"></a>
<span id="l473">                                if (debug != null) {</span><a href="#l473"></a>
<span id="l474">                                    debug.println(&quot;must only be one CRL &quot; +</span><a href="#l474"></a>
<span id="l475">                                        &quot;issuer when relative name present&quot;);</span><a href="#l475"></a>
<span id="l476">                                }</span><a href="#l476"></a>
<span id="l477">                                return false;</span><a href="#l477"></a>
<span id="l478">                            }</span><a href="#l478"></a>
<span id="l479">                            pointNames = getFullNames</span><a href="#l479"></a>
<span id="l480">                                (pointCrlIssuer, relativeName);</span><a href="#l480"></a>
<span id="l481">                        } else {</span><a href="#l481"></a>
<span id="l482">                            pointNames = getFullNames(certIssuer, relativeName);</span><a href="#l482"></a>
<span id="l483">                        }</span><a href="#l483"></a>
<span id="l484">                    }</span><a href="#l484"></a>
<span id="l485">                    boolean match = false;</span><a href="#l485"></a>
<span id="l486">                    for (Iterator&lt;GeneralName&gt; i = idpNames.iterator();</span><a href="#l486"></a>
<span id="l487">                         !match &amp;&amp; i.hasNext(); ) {</span><a href="#l487"></a>
<span id="l488">                        GeneralNameInterface idpName = i.next().getName();</span><a href="#l488"></a>
<span id="l489">                        if (debug != null) {</span><a href="#l489"></a>
<span id="l490">                            debug.println(&quot;idpName: &quot; + idpName);</span><a href="#l490"></a>
<span id="l491">                        }</span><a href="#l491"></a>
<span id="l492">                        for (Iterator&lt;GeneralName&gt; p = pointNames.iterator();</span><a href="#l492"></a>
<span id="l493">                             !match &amp;&amp; p.hasNext(); ) {</span><a href="#l493"></a>
<span id="l494">                            GeneralNameInterface pointName = p.next().getName();</span><a href="#l494"></a>
<span id="l495">                            if (debug != null) {</span><a href="#l495"></a>
<span id="l496">                                debug.println(&quot;pointName: &quot; + pointName);</span><a href="#l496"></a>
<span id="l497">                            }</span><a href="#l497"></a>
<span id="l498">                            match = idpName.equals(pointName);</span><a href="#l498"></a>
<span id="l499">                        }</span><a href="#l499"></a>
<span id="l500">                    }</span><a href="#l500"></a>
<span id="l501">                    if (!match) {</span><a href="#l501"></a>
<span id="l502">                        if (debug != null) {</span><a href="#l502"></a>
<span id="l503">                            debug.println(&quot;IDP name does not match DP name&quot;);</span><a href="#l503"></a>
<span id="l504">                        }</span><a href="#l504"></a>
<span id="l505">                        return false;</span><a href="#l505"></a>
<span id="l506">                    }</span><a href="#l506"></a>
<span id="l507">                // if the DP name is present in the IDP CRL extension and the</span><a href="#l507"></a>
<span id="l508">                // DP field is absent from the DP, then verify that one of the</span><a href="#l508"></a>
<span id="l509">                // names in the IDP matches one of the names in the crlIssuer</span><a href="#l509"></a>
<span id="l510">                // field of the DP</span><a href="#l510"></a>
<span id="l511">                } else {</span><a href="#l511"></a>
<span id="l512">                    // verify that one of the names in the IDP matches one of</span><a href="#l512"></a>
<span id="l513">                    // the names in the cRLIssuer of the cert's DP</span><a href="#l513"></a>
<span id="l514">                    boolean match = false;</span><a href="#l514"></a>
<span id="l515">                    for (Iterator&lt;GeneralName&gt; t = pointCrlIssuers.iterator();</span><a href="#l515"></a>
<span id="l516">                         !match &amp;&amp; t.hasNext(); ) {</span><a href="#l516"></a>
<span id="l517">                        GeneralNameInterface crlIssuerName = t.next().getName();</span><a href="#l517"></a>
<span id="l518">                        for (Iterator&lt;GeneralName&gt; i = idpNames.iterator();</span><a href="#l518"></a>
<span id="l519">                             !match &amp;&amp; i.hasNext(); ) {</span><a href="#l519"></a>
<span id="l520">                            GeneralNameInterface idpName = i.next().getName();</span><a href="#l520"></a>
<span id="l521">                            match = crlIssuerName.equals(idpName);</span><a href="#l521"></a>
<span id="l522">                        }</span><a href="#l522"></a>
<span id="l523">                    }</span><a href="#l523"></a>
<span id="l524">                    if (!match) {</span><a href="#l524"></a>
<span id="l525">                        return false;</span><a href="#l525"></a>
<span id="l526">                    }</span><a href="#l526"></a>
<span id="l527">                }</span><a href="#l527"></a>
<span id="l528">            }</span><a href="#l528"></a>
<span id="l529"></span><a href="#l529"></a>
<span id="l530">            // if the onlyContainsUserCerts boolean is asserted, verify that the</span><a href="#l530"></a>
<span id="l531">            // cert is not a CA cert</span><a href="#l531"></a>
<span id="l532">            Boolean b = (Boolean)</span><a href="#l532"></a>
<span id="l533">                idpExt.get(IssuingDistributionPointExtension.ONLY_USER_CERTS);</span><a href="#l533"></a>
<span id="l534">            if (b.equals(Boolean.TRUE) &amp;&amp; certImpl.getBasicConstraints() != -1) {</span><a href="#l534"></a>
<span id="l535">                if (debug != null) {</span><a href="#l535"></a>
<span id="l536">                    debug.println(&quot;cert must be a EE cert&quot;);</span><a href="#l536"></a>
<span id="l537">                }</span><a href="#l537"></a>
<span id="l538">                return false;</span><a href="#l538"></a>
<span id="l539">            }</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">            // if the onlyContainsCACerts boolean is asserted, verify that the</span><a href="#l541"></a>
<span id="l542">            // cert is a CA cert</span><a href="#l542"></a>
<span id="l543">            b = (Boolean)</span><a href="#l543"></a>
<span id="l544">                idpExt.get(IssuingDistributionPointExtension.ONLY_CA_CERTS);</span><a href="#l544"></a>
<span id="l545">            if (b.equals(Boolean.TRUE) &amp;&amp; certImpl.getBasicConstraints() == -1) {</span><a href="#l545"></a>
<span id="l546">                if (debug != null) {</span><a href="#l546"></a>
<span id="l547">                    debug.println(&quot;cert must be a CA cert&quot;);</span><a href="#l547"></a>
<span id="l548">                }</span><a href="#l548"></a>
<span id="l549">                return false;</span><a href="#l549"></a>
<span id="l550">            }</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">            // verify that the onlyContainsAttributeCerts boolean is not</span><a href="#l552"></a>
<span id="l553">            // asserted</span><a href="#l553"></a>
<span id="l554">            b = (Boolean) idpExt.get</span><a href="#l554"></a>
<span id="l555">                (IssuingDistributionPointExtension.ONLY_ATTRIBUTE_CERTS);</span><a href="#l555"></a>
<span id="l556">            if (b.equals(Boolean.TRUE)) {</span><a href="#l556"></a>
<span id="l557">                if (debug != null) {</span><a href="#l557"></a>
<span id="l558">                    debug.println(&quot;cert must not be an AA cert&quot;);</span><a href="#l558"></a>
<span id="l559">                }</span><a href="#l559"></a>
<span id="l560">                return false;</span><a href="#l560"></a>
<span id="l561">            }</span><a href="#l561"></a>
<span id="l562">        }</span><a href="#l562"></a>
<span id="l563"></span><a href="#l563"></a>
<span id="l564">        // compute interim reasons mask</span><a href="#l564"></a>
<span id="l565">        boolean[] interimReasonsMask = new boolean[9];</span><a href="#l565"></a>
<span id="l566">        ReasonFlags reasons = null;</span><a href="#l566"></a>
<span id="l567">        if (idpExt != null) {</span><a href="#l567"></a>
<span id="l568">            reasons = (ReasonFlags)</span><a href="#l568"></a>
<span id="l569">                idpExt.get(IssuingDistributionPointExtension.REASONS);</span><a href="#l569"></a>
<span id="l570">        }</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">        boolean[] pointReasonFlags = point.getReasonFlags();</span><a href="#l572"></a>
<span id="l573">        if (reasons != null) {</span><a href="#l573"></a>
<span id="l574">            if (pointReasonFlags != null) {</span><a href="#l574"></a>
<span id="l575">                // set interim reasons mask to the intersection of</span><a href="#l575"></a>
<span id="l576">                // reasons in the DP and onlySomeReasons in the IDP</span><a href="#l576"></a>
<span id="l577">                boolean[] idpReasonFlags = reasons.getFlags();</span><a href="#l577"></a>
<span id="l578">                for (int i = 0; i &lt; interimReasonsMask.length; i++) {</span><a href="#l578"></a>
<span id="l579">                    interimReasonsMask[i] =</span><a href="#l579"></a>
<span id="l580">                        (i &lt; idpReasonFlags.length &amp;&amp; idpReasonFlags[i]) &amp;&amp;</span><a href="#l580"></a>
<span id="l581">                        (i &lt; pointReasonFlags.length &amp;&amp; pointReasonFlags[i]);</span><a href="#l581"></a>
<span id="l582">                }</span><a href="#l582"></a>
<span id="l583">            } else {</span><a href="#l583"></a>
<span id="l584">                // set interim reasons mask to the value of</span><a href="#l584"></a>
<span id="l585">                // onlySomeReasons in the IDP (and clone it since we may</span><a href="#l585"></a>
<span id="l586">                // modify it)</span><a href="#l586"></a>
<span id="l587">                interimReasonsMask = reasons.getFlags().clone();</span><a href="#l587"></a>
<span id="l588">            }</span><a href="#l588"></a>
<span id="l589">        } else if (idpExt == null || reasons == null) {</span><a href="#l589"></a>
<span id="l590">            if (pointReasonFlags != null) {</span><a href="#l590"></a>
<span id="l591">                // set interim reasons mask to the value of DP reasons</span><a href="#l591"></a>
<span id="l592">                interimReasonsMask = pointReasonFlags.clone();</span><a href="#l592"></a>
<span id="l593">            } else {</span><a href="#l593"></a>
<span id="l594">                // set interim reasons mask to the special value all-reasons</span><a href="#l594"></a>
<span id="l595">                Arrays.fill(interimReasonsMask, true);</span><a href="#l595"></a>
<span id="l596">            }</span><a href="#l596"></a>
<span id="l597">        }</span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">        // verify that interim reasons mask includes one or more reasons</span><a href="#l599"></a>
<span id="l600">        // not included in the reasons mask</span><a href="#l600"></a>
<span id="l601">        boolean oneOrMore = false;</span><a href="#l601"></a>
<span id="l602">        for (int i = 0; i &lt; interimReasonsMask.length &amp;&amp; !oneOrMore; i++) {</span><a href="#l602"></a>
<span id="l603">            if (interimReasonsMask[i] &amp;&amp;</span><a href="#l603"></a>
<span id="l604">                    !(i &lt; reasonsMask.length &amp;&amp; reasonsMask[i]))</span><a href="#l604"></a>
<span id="l605">            {</span><a href="#l605"></a>
<span id="l606">                oneOrMore = true;</span><a href="#l606"></a>
<span id="l607">            }</span><a href="#l607"></a>
<span id="l608">        }</span><a href="#l608"></a>
<span id="l609">        if (!oneOrMore) {</span><a href="#l609"></a>
<span id="l610">            return false;</span><a href="#l610"></a>
<span id="l611">        }</span><a href="#l611"></a>
<span id="l612"></span><a href="#l612"></a>
<span id="l613">        // Obtain and validate the certification path for the complete</span><a href="#l613"></a>
<span id="l614">        // CRL issuer (if indirect CRL). If a key usage extension is present</span><a href="#l614"></a>
<span id="l615">        // in the CRL issuer's certificate, verify that the cRLSign bit is set.</span><a href="#l615"></a>
<span id="l616">        if (indirectCRL) {</span><a href="#l616"></a>
<span id="l617">            X509CertSelector certSel = new X509CertSelector();</span><a href="#l617"></a>
<span id="l618">            certSel.setSubject(crlIssuer.asX500Principal());</span><a href="#l618"></a>
<span id="l619">            boolean[] crlSign = {false,false,false,false,false,false,true};</span><a href="#l619"></a>
<span id="l620">            certSel.setKeyUsage(crlSign);</span><a href="#l620"></a>
<span id="l621"></span><a href="#l621"></a>
<span id="l622">            // Currently by default, forward builder does not enable</span><a href="#l622"></a>
<span id="l623">            // subject/authority key identifier identifying for target</span><a href="#l623"></a>
<span id="l624">            // certificate, instead, it only compares the CRL issuer and</span><a href="#l624"></a>
<span id="l625">            // the target certificate subject. If the certificate of the</span><a href="#l625"></a>
<span id="l626">            // delegated CRL issuer is a self-issued certificate, the</span><a href="#l626"></a>
<span id="l627">            // builder is unable to find the proper CRL issuer by issuer</span><a href="#l627"></a>
<span id="l628">            // name only, there is a potential dead loop on finding the</span><a href="#l628"></a>
<span id="l629">            // proper issuer. It is of great help to narrow the target</span><a href="#l629"></a>
<span id="l630">            // scope down to aware of authority key identifiers in the</span><a href="#l630"></a>
<span id="l631">            // selector, for the purposes of breaking the dead loop.</span><a href="#l631"></a>
<span id="l632">            AuthorityKeyIdentifierExtension akidext =</span><a href="#l632"></a>
<span id="l633">                                            crlImpl.getAuthKeyIdExtension();</span><a href="#l633"></a>
<span id="l634">            if (akidext != null) {</span><a href="#l634"></a>
<span id="l635">                byte[] kid = akidext.getEncodedKeyIdentifier();</span><a href="#l635"></a>
<span id="l636">                if (kid != null) {</span><a href="#l636"></a>
<span id="l637">                    certSel.setSubjectKeyIdentifier(kid);</span><a href="#l637"></a>
<span id="l638">                }</span><a href="#l638"></a>
<span id="l639"></span><a href="#l639"></a>
<span id="l640">                SerialNumber asn = (SerialNumber)akidext.get(</span><a href="#l640"></a>
<span id="l641">                        AuthorityKeyIdentifierExtension.SERIAL_NUMBER);</span><a href="#l641"></a>
<span id="l642">                if (asn != null) {</span><a href="#l642"></a>
<span id="l643">                    certSel.setSerialNumber(asn.getNumber());</span><a href="#l643"></a>
<span id="l644">                }</span><a href="#l644"></a>
<span id="l645">                // the subject criterion will be set by builder automatically.</span><a href="#l645"></a>
<span id="l646">            }</span><a href="#l646"></a>
<span id="l647"></span><a href="#l647"></a>
<span id="l648">            // By now, we have validated the previous certificate, so we can</span><a href="#l648"></a>
<span id="l649">            // trust it during the validation of the CRL issuer.</span><a href="#l649"></a>
<span id="l650">            // In addition to the performance improvement, another benefit is to</span><a href="#l650"></a>
<span id="l651">            // break the dead loop while looking for the issuer back and forth</span><a href="#l651"></a>
<span id="l652">            // between the delegated self-issued certificate and its issuer.</span><a href="#l652"></a>
<span id="l653">            Set&lt;TrustAnchor&gt; newTrustAnchors = new HashSet&lt;&gt;(trustAnchors);</span><a href="#l653"></a>
<span id="l654"></span><a href="#l654"></a>
<span id="l655">            if (prevKey != null) {</span><a href="#l655"></a>
<span id="l656">                // Add the previous certificate as a trust anchor.</span><a href="#l656"></a>
<span id="l657">                // If prevCert is not null, we want to construct a TrustAnchor</span><a href="#l657"></a>
<span id="l658">                // using the cert object because when the certpath for the CRL</span><a href="#l658"></a>
<span id="l659">                // is built later, the CertSelector will make comparisons with</span><a href="#l659"></a>
<span id="l660">                // the TrustAnchor's trustedCert member rather than its pubKey.</span><a href="#l660"></a>
<span id="l661">                TrustAnchor temporary;</span><a href="#l661"></a>
<span id="l662">                if (prevCert != null) {</span><a href="#l662"></a>
<span id="l663">                    temporary = new TrustAnchor(prevCert, null);</span><a href="#l663"></a>
<span id="l664">                } else {</span><a href="#l664"></a>
<span id="l665">                    X500Principal principal = certImpl.getIssuerX500Principal();</span><a href="#l665"></a>
<span id="l666">                    temporary = new TrustAnchor(principal, prevKey, null);</span><a href="#l666"></a>
<span id="l667">                }</span><a href="#l667"></a>
<span id="l668">                newTrustAnchors.add(temporary);</span><a href="#l668"></a>
<span id="l669">            }</span><a href="#l669"></a>
<span id="l670"></span><a href="#l670"></a>
<span id="l671">            PKIXBuilderParameters params = null;</span><a href="#l671"></a>
<span id="l672">            try {</span><a href="#l672"></a>
<span id="l673">                params = new PKIXBuilderParameters(newTrustAnchors, certSel);</span><a href="#l673"></a>
<span id="l674">            } catch (InvalidAlgorithmParameterException iape) {</span><a href="#l674"></a>
<span id="l675">                throw new CRLException(iape);</span><a href="#l675"></a>
<span id="l676">            }</span><a href="#l676"></a>
<span id="l677">            params.setCertStores(certStores);</span><a href="#l677"></a>
<span id="l678">            params.setSigProvider(provider);</span><a href="#l678"></a>
<span id="l679">            params.setDate(validity);</span><a href="#l679"></a>
<span id="l680">            try {</span><a href="#l680"></a>
<span id="l681">                CertPathBuilder builder = CertPathBuilder.getInstance(&quot;PKIX&quot;);</span><a href="#l681"></a>
<span id="l682">                PKIXCertPathBuilderResult result =</span><a href="#l682"></a>
<span id="l683">                    (PKIXCertPathBuilderResult) builder.build(params);</span><a href="#l683"></a>
<span id="l684">                prevKey = result.getPublicKey();</span><a href="#l684"></a>
<span id="l685">            } catch (GeneralSecurityException e) {</span><a href="#l685"></a>
<span id="l686">                throw new CRLException(e);</span><a href="#l686"></a>
<span id="l687">            }</span><a href="#l687"></a>
<span id="l688">        }</span><a href="#l688"></a>
<span id="l689"></span><a href="#l689"></a>
<span id="l690">        // check the crl signature algorithm</span><a href="#l690"></a>
<span id="l691">        try {</span><a href="#l691"></a>
<span id="l692">            AlgorithmChecker.check(prevKey, crl, variant, anchor);</span><a href="#l692"></a>
<span id="l693">        } catch (CertPathValidatorException cpve) {</span><a href="#l693"></a>
<span id="l694">            if (debug != null) {</span><a href="#l694"></a>
<span id="l695">                debug.println(&quot;CRL signature algorithm check failed: &quot; + cpve);</span><a href="#l695"></a>
<span id="l696">            }</span><a href="#l696"></a>
<span id="l697">            return false;</span><a href="#l697"></a>
<span id="l698">        }</span><a href="#l698"></a>
<span id="l699"></span><a href="#l699"></a>
<span id="l700">        // validate the signature on the CRL</span><a href="#l700"></a>
<span id="l701">        try {</span><a href="#l701"></a>
<span id="l702">            crl.verify(prevKey, provider);</span><a href="#l702"></a>
<span id="l703">        } catch (GeneralSecurityException e) {</span><a href="#l703"></a>
<span id="l704">            if (debug != null) {</span><a href="#l704"></a>
<span id="l705">                debug.println(&quot;CRL signature failed to verify&quot;);</span><a href="#l705"></a>
<span id="l706">            }</span><a href="#l706"></a>
<span id="l707">            return false;</span><a href="#l707"></a>
<span id="l708">        }</span><a href="#l708"></a>
<span id="l709"></span><a href="#l709"></a>
<span id="l710">        // reject CRL if any unresolved critical extensions remain in the CRL.</span><a href="#l710"></a>
<span id="l711">        Set&lt;String&gt; unresCritExts = crl.getCriticalExtensionOIDs();</span><a href="#l711"></a>
<span id="l712">        // remove any that we have processed</span><a href="#l712"></a>
<span id="l713">        if (unresCritExts != null) {</span><a href="#l713"></a>
<span id="l714">            unresCritExts.remove(IssuingDistributionPoint_Id.toString());</span><a href="#l714"></a>
<span id="l715">            if (!unresCritExts.isEmpty()) {</span><a href="#l715"></a>
<span id="l716">                if (debug != null) {</span><a href="#l716"></a>
<span id="l717">                    debug.println(&quot;Unrecognized critical extension(s) in CRL: &quot;</span><a href="#l717"></a>
<span id="l718">                        + unresCritExts);</span><a href="#l718"></a>
<span id="l719">                    for (String ext : unresCritExts) {</span><a href="#l719"></a>
<span id="l720">                        debug.println(ext);</span><a href="#l720"></a>
<span id="l721">                    }</span><a href="#l721"></a>
<span id="l722">                }</span><a href="#l722"></a>
<span id="l723">                return false;</span><a href="#l723"></a>
<span id="l724">            }</span><a href="#l724"></a>
<span id="l725">        }</span><a href="#l725"></a>
<span id="l726"></span><a href="#l726"></a>
<span id="l727">        // update reasonsMask</span><a href="#l727"></a>
<span id="l728">        for (int i = 0; i &lt; reasonsMask.length; i++) {</span><a href="#l728"></a>
<span id="l729">            reasonsMask[i] = reasonsMask[i] ||</span><a href="#l729"></a>
<span id="l730">                    (i &lt; interimReasonsMask.length &amp;&amp; interimReasonsMask[i]);</span><a href="#l730"></a>
<span id="l731">        }</span><a href="#l731"></a>
<span id="l732"></span><a href="#l732"></a>
<span id="l733">        return true;</span><a href="#l733"></a>
<span id="l734">    }</span><a href="#l734"></a>
<span id="l735"></span><a href="#l735"></a>
<span id="l736">    /**</span><a href="#l736"></a>
<span id="l737">     * Append relative name to the issuer name and return a new</span><a href="#l737"></a>
<span id="l738">     * GeneralNames object.</span><a href="#l738"></a>
<span id="l739">     */</span><a href="#l739"></a>
<span id="l740">    private static GeneralNames getFullNames(X500Name issuer, RDN rdn)</span><a href="#l740"></a>
<span id="l741">        throws IOException</span><a href="#l741"></a>
<span id="l742">    {</span><a href="#l742"></a>
<span id="l743">        List&lt;RDN&gt; rdns = new ArrayList&lt;&gt;(issuer.rdns());</span><a href="#l743"></a>
<span id="l744">        rdns.add(rdn);</span><a href="#l744"></a>
<span id="l745">        X500Name fullName = new X500Name(rdns.toArray(new RDN[0]));</span><a href="#l745"></a>
<span id="l746">        GeneralNames fullNames = new GeneralNames();</span><a href="#l746"></a>
<span id="l747">        fullNames.add(new GeneralName(fullName));</span><a href="#l747"></a>
<span id="l748">        return fullNames;</span><a href="#l748"></a>
<span id="l749">    }</span><a href="#l749"></a>
<span id="l750"></span><a href="#l750"></a>
<span id="l751">    /**</span><a href="#l751"></a>
<span id="l752">     * Verifies whether a CRL is issued by a certain certificate</span><a href="#l752"></a>
<span id="l753">     *</span><a href="#l753"></a>
<span id="l754">     * @param cert the certificate</span><a href="#l754"></a>
<span id="l755">     * @param crl the CRL to be verified</span><a href="#l755"></a>
<span id="l756">     * @param provider the name of the signature provider</span><a href="#l756"></a>
<span id="l757">     */</span><a href="#l757"></a>
<span id="l758">    private static boolean issues(X509CertImpl cert, X509CRLImpl crl,</span><a href="#l758"></a>
<span id="l759">                                  String provider) throws IOException</span><a href="#l759"></a>
<span id="l760">    {</span><a href="#l760"></a>
<span id="l761">        boolean matched = false;</span><a href="#l761"></a>
<span id="l762"></span><a href="#l762"></a>
<span id="l763">        AdaptableX509CertSelector issuerSelector =</span><a href="#l763"></a>
<span id="l764">                                    new AdaptableX509CertSelector();</span><a href="#l764"></a>
<span id="l765"></span><a href="#l765"></a>
<span id="l766">        // check certificate's key usage</span><a href="#l766"></a>
<span id="l767">        boolean[] usages = cert.getKeyUsage();</span><a href="#l767"></a>
<span id="l768">        if (usages != null) {</span><a href="#l768"></a>
<span id="l769">            usages[6] = true;       // cRLSign</span><a href="#l769"></a>
<span id="l770">            issuerSelector.setKeyUsage(usages);</span><a href="#l770"></a>
<span id="l771">        }</span><a href="#l771"></a>
<span id="l772"></span><a href="#l772"></a>
<span id="l773">        // check certificate's subject</span><a href="#l773"></a>
<span id="l774">        X500Principal crlIssuer = crl.getIssuerX500Principal();</span><a href="#l774"></a>
<span id="l775">        issuerSelector.setSubject(crlIssuer);</span><a href="#l775"></a>
<span id="l776"></span><a href="#l776"></a>
<span id="l777">        /*</span><a href="#l777"></a>
<span id="l778">         * Facilitate certification path construction with authority</span><a href="#l778"></a>
<span id="l779">         * key identifier and subject key identifier.</span><a href="#l779"></a>
<span id="l780">         *</span><a href="#l780"></a>
<span id="l781">         * In practice, conforming CAs MUST use the key identifier method,</span><a href="#l781"></a>
<span id="l782">         * and MUST include authority key identifier extension in all CRLs</span><a href="#l782"></a>
<span id="l783">         * issued. [section 5.2.1, RFC 2459]</span><a href="#l783"></a>
<span id="l784">         */</span><a href="#l784"></a>
<span id="l785">        AuthorityKeyIdentifierExtension crlAKID = crl.getAuthKeyIdExtension();</span><a href="#l785"></a>
<span id="l786">        issuerSelector.setSkiAndSerialNumber(crlAKID);</span><a href="#l786"></a>
<span id="l787"></span><a href="#l787"></a>
<span id="l788">        matched = issuerSelector.match(cert);</span><a href="#l788"></a>
<span id="l789"></span><a href="#l789"></a>
<span id="l790">        // if AKID is unreliable, verify the CRL signature with the cert</span><a href="#l790"></a>
<span id="l791">        if (matched &amp;&amp; (crlAKID == null ||</span><a href="#l791"></a>
<span id="l792">                cert.getAuthorityKeyIdentifierExtension() == null)) {</span><a href="#l792"></a>
<span id="l793">            try {</span><a href="#l793"></a>
<span id="l794">                crl.verify(cert.getPublicKey(), provider);</span><a href="#l794"></a>
<span id="l795">                matched = true;</span><a href="#l795"></a>
<span id="l796">            } catch (GeneralSecurityException e) {</span><a href="#l796"></a>
<span id="l797">                matched = false;</span><a href="#l797"></a>
<span id="l798">            }</span><a href="#l798"></a>
<span id="l799">        }</span><a href="#l799"></a>
<span id="l800"></span><a href="#l800"></a>
<span id="l801">        return matched;</span><a href="#l801"></a>
<span id="l802">    }</span><a href="#l802"></a>
<span id="l803">}</span><a href="#l803"></a></pre>
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

