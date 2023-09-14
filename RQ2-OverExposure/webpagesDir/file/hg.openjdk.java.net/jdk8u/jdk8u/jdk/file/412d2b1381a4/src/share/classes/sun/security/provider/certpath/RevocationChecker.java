<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/RevocationChecker.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/RevocationChecker.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/e329505b1e6a/src/share/classes/sun/security/provider/certpath/RevocationChecker.java">e329505b1e6a</a> </td>
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
<span id="l2"> * Copyright (c) 2012, 2017, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.math.BigInteger;</span><a href="#l29"></a>
<span id="l30">import java.net.URI;</span><a href="#l30"></a>
<span id="l31">import java.net.URISyntaxException;</span><a href="#l31"></a>
<span id="l32">import java.security.AccessController;</span><a href="#l32"></a>
<span id="l33">import java.security.InvalidAlgorithmParameterException;</span><a href="#l33"></a>
<span id="l34">import java.security.NoSuchAlgorithmException;</span><a href="#l34"></a>
<span id="l35">import java.security.PrivilegedAction;</span><a href="#l35"></a>
<span id="l36">import java.security.PublicKey;</span><a href="#l36"></a>
<span id="l37">import java.security.Security;</span><a href="#l37"></a>
<span id="l38">import java.security.cert.CertPathValidatorException.BasicReason;</span><a href="#l38"></a>
<span id="l39">import java.security.cert.Extension;</span><a href="#l39"></a>
<span id="l40">import java.security.cert.*;</span><a href="#l40"></a>
<span id="l41">import java.util.*;</span><a href="#l41"></a>
<span id="l42">import javax.security.auth.x500.X500Principal;</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">import static sun.security.provider.certpath.OCSP.*;</span><a href="#l44"></a>
<span id="l45">import static sun.security.provider.certpath.PKIX.*;</span><a href="#l45"></a>
<span id="l46">import sun.security.x509.*;</span><a href="#l46"></a>
<span id="l47">import static sun.security.x509.PKIXExtensions.*;</span><a href="#l47"></a>
<span id="l48">import sun.security.util.Debug;</span><a href="#l48"></a>
<span id="l49"></span><a href="#l49"></a>
<span id="l50">class RevocationChecker extends PKIXRevocationChecker {</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">    private TrustAnchor anchor;</span><a href="#l54"></a>
<span id="l55">    private ValidatorParams params;</span><a href="#l55"></a>
<span id="l56">    private boolean onlyEE;</span><a href="#l56"></a>
<span id="l57">    private boolean softFail;</span><a href="#l57"></a>
<span id="l58">    private boolean crlDP;</span><a href="#l58"></a>
<span id="l59">    private URI responderURI;</span><a href="#l59"></a>
<span id="l60">    private X509Certificate responderCert;</span><a href="#l60"></a>
<span id="l61">    private List&lt;CertStore&gt; certStores;</span><a href="#l61"></a>
<span id="l62">    private Map&lt;X509Certificate, byte[]&gt; ocspResponses;</span><a href="#l62"></a>
<span id="l63">    private List&lt;Extension&gt; ocspExtensions;</span><a href="#l63"></a>
<span id="l64">    private final boolean legacy;</span><a href="#l64"></a>
<span id="l65">    private LinkedList&lt;CertPathValidatorException&gt; softFailExceptions =</span><a href="#l65"></a>
<span id="l66">        new LinkedList&lt;&gt;();</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    // state variables</span><a href="#l68"></a>
<span id="l69">    private OCSPResponse.IssuerInfo issuerInfo;</span><a href="#l69"></a>
<span id="l70">    private PublicKey prevPubKey;</span><a href="#l70"></a>
<span id="l71">    private boolean crlSignFlag;</span><a href="#l71"></a>
<span id="l72">    private int certIndex;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    private enum Mode { PREFER_OCSP, PREFER_CRLS, ONLY_CRLS, ONLY_OCSP };</span><a href="#l74"></a>
<span id="l75">    private Mode mode = Mode.PREFER_OCSP;</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    private static class RevocationProperties {</span><a href="#l77"></a>
<span id="l78">        boolean onlyEE;</span><a href="#l78"></a>
<span id="l79">        boolean ocspEnabled;</span><a href="#l79"></a>
<span id="l80">        boolean crlDPEnabled;</span><a href="#l80"></a>
<span id="l81">        String ocspUrl;</span><a href="#l81"></a>
<span id="l82">        String ocspSubject;</span><a href="#l82"></a>
<span id="l83">        String ocspIssuer;</span><a href="#l83"></a>
<span id="l84">        String ocspSerial;</span><a href="#l84"></a>
<span id="l85">    }</span><a href="#l85"></a>
<span id="l86"></span><a href="#l86"></a>
<span id="l87">    RevocationChecker() {</span><a href="#l87"></a>
<span id="l88">        legacy = false;</span><a href="#l88"></a>
<span id="l89">    }</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">    RevocationChecker(TrustAnchor anchor, ValidatorParams params)</span><a href="#l91"></a>
<span id="l92">        throws CertPathValidatorException</span><a href="#l92"></a>
<span id="l93">    {</span><a href="#l93"></a>
<span id="l94">        legacy = true;</span><a href="#l94"></a>
<span id="l95">        init(anchor, params);</span><a href="#l95"></a>
<span id="l96">    }</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    void init(TrustAnchor anchor, ValidatorParams params)</span><a href="#l98"></a>
<span id="l99">        throws CertPathValidatorException</span><a href="#l99"></a>
<span id="l100">    {</span><a href="#l100"></a>
<span id="l101">        RevocationProperties rp = getRevocationProperties();</span><a href="#l101"></a>
<span id="l102">        URI uri = getOcspResponder();</span><a href="#l102"></a>
<span id="l103">        responderURI = (uri == null) ? toURI(rp.ocspUrl) : uri;</span><a href="#l103"></a>
<span id="l104">        X509Certificate cert = getOcspResponderCert();</span><a href="#l104"></a>
<span id="l105">        responderCert = (cert == null)</span><a href="#l105"></a>
<span id="l106">                        ? getResponderCert(rp, params.trustAnchors(),</span><a href="#l106"></a>
<span id="l107">                                           params.certStores())</span><a href="#l107"></a>
<span id="l108">                        : cert;</span><a href="#l108"></a>
<span id="l109">        Set&lt;Option&gt; options = getOptions();</span><a href="#l109"></a>
<span id="l110">        for (Option option : options) {</span><a href="#l110"></a>
<span id="l111">            switch (option) {</span><a href="#l111"></a>
<span id="l112">            case ONLY_END_ENTITY:</span><a href="#l112"></a>
<span id="l113">            case PREFER_CRLS:</span><a href="#l113"></a>
<span id="l114">            case SOFT_FAIL:</span><a href="#l114"></a>
<span id="l115">            case NO_FALLBACK:</span><a href="#l115"></a>
<span id="l116">                break;</span><a href="#l116"></a>
<span id="l117">            default:</span><a href="#l117"></a>
<span id="l118">                throw new CertPathValidatorException(</span><a href="#l118"></a>
<span id="l119">                    &quot;Unrecognized revocation parameter option: &quot; + option);</span><a href="#l119"></a>
<span id="l120">            }</span><a href="#l120"></a>
<span id="l121">        }</span><a href="#l121"></a>
<span id="l122">        softFail = options.contains(Option.SOFT_FAIL);</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">        // set mode, only end entity flag</span><a href="#l124"></a>
<span id="l125">        if (legacy) {</span><a href="#l125"></a>
<span id="l126">            mode = (rp.ocspEnabled) ? Mode.PREFER_OCSP : Mode.ONLY_CRLS;</span><a href="#l126"></a>
<span id="l127">            onlyEE = rp.onlyEE;</span><a href="#l127"></a>
<span id="l128">        } else {</span><a href="#l128"></a>
<span id="l129">            if (options.contains(Option.NO_FALLBACK)) {</span><a href="#l129"></a>
<span id="l130">                if (options.contains(Option.PREFER_CRLS)) {</span><a href="#l130"></a>
<span id="l131">                    mode = Mode.ONLY_CRLS;</span><a href="#l131"></a>
<span id="l132">                } else {</span><a href="#l132"></a>
<span id="l133">                    mode = Mode.ONLY_OCSP;</span><a href="#l133"></a>
<span id="l134">                }</span><a href="#l134"></a>
<span id="l135">            } else if (options.contains(Option.PREFER_CRLS)) {</span><a href="#l135"></a>
<span id="l136">                mode = Mode.PREFER_CRLS;</span><a href="#l136"></a>
<span id="l137">            }</span><a href="#l137"></a>
<span id="l138">            onlyEE = options.contains(Option.ONLY_END_ENTITY);</span><a href="#l138"></a>
<span id="l139">        }</span><a href="#l139"></a>
<span id="l140">        if (legacy) {</span><a href="#l140"></a>
<span id="l141">            crlDP = rp.crlDPEnabled;</span><a href="#l141"></a>
<span id="l142">        } else {</span><a href="#l142"></a>
<span id="l143">            crlDP = true;</span><a href="#l143"></a>
<span id="l144">        }</span><a href="#l144"></a>
<span id="l145">        ocspResponses = getOcspResponses();</span><a href="#l145"></a>
<span id="l146">        ocspExtensions = getOcspExtensions();</span><a href="#l146"></a>
<span id="l147"></span><a href="#l147"></a>
<span id="l148">        this.anchor = anchor;</span><a href="#l148"></a>
<span id="l149">        this.params = params;</span><a href="#l149"></a>
<span id="l150">        this.certStores = new ArrayList&lt;&gt;(params.certStores());</span><a href="#l150"></a>
<span id="l151">        try {</span><a href="#l151"></a>
<span id="l152">            this.certStores.add(CertStore.getInstance(&quot;Collection&quot;,</span><a href="#l152"></a>
<span id="l153">                new CollectionCertStoreParameters(params.certificates())));</span><a href="#l153"></a>
<span id="l154">        } catch (InvalidAlgorithmParameterException |</span><a href="#l154"></a>
<span id="l155">                 NoSuchAlgorithmException e) {</span><a href="#l155"></a>
<span id="l156">            // should never occur but not necessarily fatal, so log it,</span><a href="#l156"></a>
<span id="l157">            // ignore and continue</span><a href="#l157"></a>
<span id="l158">            if (debug != null) {</span><a href="#l158"></a>
<span id="l159">                debug.println(&quot;RevocationChecker: &quot; +</span><a href="#l159"></a>
<span id="l160">                              &quot;error creating Collection CertStore: &quot; + e);</span><a href="#l160"></a>
<span id="l161">            }</span><a href="#l161"></a>
<span id="l162">        }</span><a href="#l162"></a>
<span id="l163">    }</span><a href="#l163"></a>
<span id="l164"></span><a href="#l164"></a>
<span id="l165">    private static URI toURI(String uriString)</span><a href="#l165"></a>
<span id="l166">        throws CertPathValidatorException</span><a href="#l166"></a>
<span id="l167">    {</span><a href="#l167"></a>
<span id="l168">        try {</span><a href="#l168"></a>
<span id="l169">            if (uriString != null) {</span><a href="#l169"></a>
<span id="l170">                return new URI(uriString);</span><a href="#l170"></a>
<span id="l171">            }</span><a href="#l171"></a>
<span id="l172">            return null;</span><a href="#l172"></a>
<span id="l173">        } catch (URISyntaxException e) {</span><a href="#l173"></a>
<span id="l174">            throw new CertPathValidatorException(</span><a href="#l174"></a>
<span id="l175">                &quot;cannot parse ocsp.responderURL property&quot;, e);</span><a href="#l175"></a>
<span id="l176">        }</span><a href="#l176"></a>
<span id="l177">    }</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    private static RevocationProperties getRevocationProperties() {</span><a href="#l179"></a>
<span id="l180">        return AccessController.doPrivileged(</span><a href="#l180"></a>
<span id="l181">            new PrivilegedAction&lt;RevocationProperties&gt;() {</span><a href="#l181"></a>
<span id="l182">                public RevocationProperties run() {</span><a href="#l182"></a>
<span id="l183">                    RevocationProperties rp = new RevocationProperties();</span><a href="#l183"></a>
<span id="l184">                    String onlyEE = Security.getProperty(</span><a href="#l184"></a>
<span id="l185">                        &quot;com.sun.security.onlyCheckRevocationOfEECert&quot;);</span><a href="#l185"></a>
<span id="l186">                    rp.onlyEE = onlyEE != null</span><a href="#l186"></a>
<span id="l187">                                &amp;&amp; onlyEE.equalsIgnoreCase(&quot;true&quot;);</span><a href="#l187"></a>
<span id="l188">                    String ocspEnabled = Security.getProperty(&quot;ocsp.enable&quot;);</span><a href="#l188"></a>
<span id="l189">                    rp.ocspEnabled = ocspEnabled != null</span><a href="#l189"></a>
<span id="l190">                                     &amp;&amp; ocspEnabled.equalsIgnoreCase(&quot;true&quot;);</span><a href="#l190"></a>
<span id="l191">                    rp.ocspUrl = Security.getProperty(&quot;ocsp.responderURL&quot;);</span><a href="#l191"></a>
<span id="l192">                    rp.ocspSubject</span><a href="#l192"></a>
<span id="l193">                        = Security.getProperty(&quot;ocsp.responderCertSubjectName&quot;);</span><a href="#l193"></a>
<span id="l194">                    rp.ocspIssuer</span><a href="#l194"></a>
<span id="l195">                        = Security.getProperty(&quot;ocsp.responderCertIssuerName&quot;);</span><a href="#l195"></a>
<span id="l196">                    rp.ocspSerial</span><a href="#l196"></a>
<span id="l197">                        = Security.getProperty(&quot;ocsp.responderCertSerialNumber&quot;);</span><a href="#l197"></a>
<span id="l198">                    rp.crlDPEnabled</span><a href="#l198"></a>
<span id="l199">                        = Boolean.getBoolean(&quot;com.sun.security.enableCRLDP&quot;);</span><a href="#l199"></a>
<span id="l200">                    return rp;</span><a href="#l200"></a>
<span id="l201">                }</span><a href="#l201"></a>
<span id="l202">            }</span><a href="#l202"></a>
<span id="l203">        );</span><a href="#l203"></a>
<span id="l204">    }</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">    private static X509Certificate getResponderCert(RevocationProperties rp,</span><a href="#l206"></a>
<span id="l207">                                                    Set&lt;TrustAnchor&gt; anchors,</span><a href="#l207"></a>
<span id="l208">                                                    List&lt;CertStore&gt; stores)</span><a href="#l208"></a>
<span id="l209">        throws CertPathValidatorException</span><a href="#l209"></a>
<span id="l210">    {</span><a href="#l210"></a>
<span id="l211">        if (rp.ocspSubject != null) {</span><a href="#l211"></a>
<span id="l212">            return getResponderCert(rp.ocspSubject, anchors, stores);</span><a href="#l212"></a>
<span id="l213">        } else if (rp.ocspIssuer != null &amp;&amp; rp.ocspSerial != null) {</span><a href="#l213"></a>
<span id="l214">            return getResponderCert(rp.ocspIssuer, rp.ocspSerial,</span><a href="#l214"></a>
<span id="l215">                                    anchors, stores);</span><a href="#l215"></a>
<span id="l216">        } else if (rp.ocspIssuer != null || rp.ocspSerial != null) {</span><a href="#l216"></a>
<span id="l217">            throw new CertPathValidatorException(</span><a href="#l217"></a>
<span id="l218">                &quot;Must specify both ocsp.responderCertIssuerName and &quot; +</span><a href="#l218"></a>
<span id="l219">                &quot;ocsp.responderCertSerialNumber properties&quot;);</span><a href="#l219"></a>
<span id="l220">        }</span><a href="#l220"></a>
<span id="l221">        return null;</span><a href="#l221"></a>
<span id="l222">    }</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    private static X509Certificate getResponderCert(String subject,</span><a href="#l224"></a>
<span id="l225">                                                    Set&lt;TrustAnchor&gt; anchors,</span><a href="#l225"></a>
<span id="l226">                                                    List&lt;CertStore&gt; stores)</span><a href="#l226"></a>
<span id="l227">        throws CertPathValidatorException</span><a href="#l227"></a>
<span id="l228">    {</span><a href="#l228"></a>
<span id="l229">        X509CertSelector sel = new X509CertSelector();</span><a href="#l229"></a>
<span id="l230">        try {</span><a href="#l230"></a>
<span id="l231">            sel.setSubject(new X500Principal(subject));</span><a href="#l231"></a>
<span id="l232">        } catch (IllegalArgumentException e) {</span><a href="#l232"></a>
<span id="l233">            throw new CertPathValidatorException(</span><a href="#l233"></a>
<span id="l234">                &quot;cannot parse ocsp.responderCertSubjectName property&quot;, e);</span><a href="#l234"></a>
<span id="l235">        }</span><a href="#l235"></a>
<span id="l236">        return getResponderCert(sel, anchors, stores);</span><a href="#l236"></a>
<span id="l237">    }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">    private static X509Certificate getResponderCert(String issuer,</span><a href="#l239"></a>
<span id="l240">                                                    String serial,</span><a href="#l240"></a>
<span id="l241">                                                    Set&lt;TrustAnchor&gt; anchors,</span><a href="#l241"></a>
<span id="l242">                                                    List&lt;CertStore&gt; stores)</span><a href="#l242"></a>
<span id="l243">        throws CertPathValidatorException</span><a href="#l243"></a>
<span id="l244">    {</span><a href="#l244"></a>
<span id="l245">        X509CertSelector sel = new X509CertSelector();</span><a href="#l245"></a>
<span id="l246">        try {</span><a href="#l246"></a>
<span id="l247">            sel.setIssuer(new X500Principal(issuer));</span><a href="#l247"></a>
<span id="l248">        } catch (IllegalArgumentException e) {</span><a href="#l248"></a>
<span id="l249">            throw new CertPathValidatorException(</span><a href="#l249"></a>
<span id="l250">                &quot;cannot parse ocsp.responderCertIssuerName property&quot;, e);</span><a href="#l250"></a>
<span id="l251">        }</span><a href="#l251"></a>
<span id="l252">        try {</span><a href="#l252"></a>
<span id="l253">            sel.setSerialNumber(new BigInteger(stripOutSeparators(serial), 16));</span><a href="#l253"></a>
<span id="l254">        } catch (NumberFormatException e) {</span><a href="#l254"></a>
<span id="l255">            throw new CertPathValidatorException(</span><a href="#l255"></a>
<span id="l256">                &quot;cannot parse ocsp.responderCertSerialNumber property&quot;, e);</span><a href="#l256"></a>
<span id="l257">        }</span><a href="#l257"></a>
<span id="l258">        return getResponderCert(sel, anchors, stores);</span><a href="#l258"></a>
<span id="l259">    }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">    private static X509Certificate getResponderCert(X509CertSelector sel,</span><a href="#l261"></a>
<span id="l262">                                                    Set&lt;TrustAnchor&gt; anchors,</span><a href="#l262"></a>
<span id="l263">                                                    List&lt;CertStore&gt; stores)</span><a href="#l263"></a>
<span id="l264">        throws CertPathValidatorException</span><a href="#l264"></a>
<span id="l265">    {</span><a href="#l265"></a>
<span id="l266">        // first check TrustAnchors</span><a href="#l266"></a>
<span id="l267">        for (TrustAnchor anchor : anchors) {</span><a href="#l267"></a>
<span id="l268">            X509Certificate cert = anchor.getTrustedCert();</span><a href="#l268"></a>
<span id="l269">            if (cert == null) {</span><a href="#l269"></a>
<span id="l270">                continue;</span><a href="#l270"></a>
<span id="l271">            }</span><a href="#l271"></a>
<span id="l272">            if (sel.match(cert)) {</span><a href="#l272"></a>
<span id="l273">                return cert;</span><a href="#l273"></a>
<span id="l274">            }</span><a href="#l274"></a>
<span id="l275">        }</span><a href="#l275"></a>
<span id="l276">        // now check CertStores</span><a href="#l276"></a>
<span id="l277">        for (CertStore store : stores) {</span><a href="#l277"></a>
<span id="l278">            try {</span><a href="#l278"></a>
<span id="l279">                Collection&lt;? extends Certificate&gt; certs =</span><a href="#l279"></a>
<span id="l280">                    store.getCertificates(sel);</span><a href="#l280"></a>
<span id="l281">                if (!certs.isEmpty()) {</span><a href="#l281"></a>
<span id="l282">                    return (X509Certificate)certs.iterator().next();</span><a href="#l282"></a>
<span id="l283">                }</span><a href="#l283"></a>
<span id="l284">            } catch (CertStoreException e) {</span><a href="#l284"></a>
<span id="l285">                // ignore and try next CertStore</span><a href="#l285"></a>
<span id="l286">                if (debug != null) {</span><a href="#l286"></a>
<span id="l287">                    debug.println(&quot;CertStore exception:&quot; + e);</span><a href="#l287"></a>
<span id="l288">                }</span><a href="#l288"></a>
<span id="l289">                continue;</span><a href="#l289"></a>
<span id="l290">            }</span><a href="#l290"></a>
<span id="l291">        }</span><a href="#l291"></a>
<span id="l292">        throw new CertPathValidatorException(</span><a href="#l292"></a>
<span id="l293">            &quot;Cannot find the responder's certificate &quot; +</span><a href="#l293"></a>
<span id="l294">            &quot;(set using the OCSP security properties).&quot;);</span><a href="#l294"></a>
<span id="l295">    }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">    @Override</span><a href="#l297"></a>
<span id="l298">    public void init(boolean forward) throws CertPathValidatorException {</span><a href="#l298"></a>
<span id="l299">        if (forward) {</span><a href="#l299"></a>
<span id="l300">            throw new</span><a href="#l300"></a>
<span id="l301">                CertPathValidatorException(&quot;forward checking not supported&quot;);</span><a href="#l301"></a>
<span id="l302">        }</span><a href="#l302"></a>
<span id="l303">        if (anchor != null) {</span><a href="#l303"></a>
<span id="l304">            issuerInfo = new OCSPResponse.IssuerInfo(anchor);</span><a href="#l304"></a>
<span id="l305">            prevPubKey = issuerInfo.getPublicKey();</span><a href="#l305"></a>
<span id="l306"></span><a href="#l306"></a>
<span id="l307">        }</span><a href="#l307"></a>
<span id="l308">        crlSignFlag = true;</span><a href="#l308"></a>
<span id="l309">        if (params != null &amp;&amp; params.certPath() != null) {</span><a href="#l309"></a>
<span id="l310">            certIndex = params.certPath().getCertificates().size() - 1;</span><a href="#l310"></a>
<span id="l311">        } else {</span><a href="#l311"></a>
<span id="l312">            certIndex = -1;</span><a href="#l312"></a>
<span id="l313">        }</span><a href="#l313"></a>
<span id="l314">        softFailExceptions.clear();</span><a href="#l314"></a>
<span id="l315">    }</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">    @Override</span><a href="#l317"></a>
<span id="l318">    public boolean isForwardCheckingSupported() {</span><a href="#l318"></a>
<span id="l319">        return false;</span><a href="#l319"></a>
<span id="l320">    }</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">    @Override</span><a href="#l322"></a>
<span id="l323">    public Set&lt;String&gt; getSupportedExtensions() {</span><a href="#l323"></a>
<span id="l324">        return null;</span><a href="#l324"></a>
<span id="l325">    }</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">    @Override</span><a href="#l327"></a>
<span id="l328">    public List&lt;CertPathValidatorException&gt; getSoftFailExceptions() {</span><a href="#l328"></a>
<span id="l329">        return Collections.unmodifiableList(softFailExceptions);</span><a href="#l329"></a>
<span id="l330">    }</span><a href="#l330"></a>
<span id="l331"></span><a href="#l331"></a>
<span id="l332">    @Override</span><a href="#l332"></a>
<span id="l333">    public void check(Certificate cert, Collection&lt;String&gt; unresolvedCritExts)</span><a href="#l333"></a>
<span id="l334">        throws CertPathValidatorException</span><a href="#l334"></a>
<span id="l335">    {</span><a href="#l335"></a>
<span id="l336">        check((X509Certificate)cert, unresolvedCritExts,</span><a href="#l336"></a>
<span id="l337">              prevPubKey, crlSignFlag);</span><a href="#l337"></a>
<span id="l338">    }</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">    private void check(X509Certificate xcert,</span><a href="#l340"></a>
<span id="l341">                       Collection&lt;String&gt; unresolvedCritExts,</span><a href="#l341"></a>
<span id="l342">                       PublicKey pubKey, boolean crlSignFlag)</span><a href="#l342"></a>
<span id="l343">        throws CertPathValidatorException</span><a href="#l343"></a>
<span id="l344">    {</span><a href="#l344"></a>
<span id="l345">        if (debug != null) {</span><a href="#l345"></a>
<span id="l346">            debug.println(&quot;RevocationChecker.check: checking cert&quot; +</span><a href="#l346"></a>
<span id="l347">                &quot;\n  SN: &quot; + Debug.toHexString(xcert.getSerialNumber()) +</span><a href="#l347"></a>
<span id="l348">                &quot;\n  Subject: &quot; + xcert.getSubjectX500Principal() +</span><a href="#l348"></a>
<span id="l349">                &quot;\n  Issuer: &quot; + xcert.getIssuerX500Principal());</span><a href="#l349"></a>
<span id="l350">        }</span><a href="#l350"></a>
<span id="l351">        try {</span><a href="#l351"></a>
<span id="l352">            if (onlyEE &amp;&amp; xcert.getBasicConstraints() != -1) {</span><a href="#l352"></a>
<span id="l353">                if (debug != null) {</span><a href="#l353"></a>
<span id="l354">                    debug.println(&quot;Skipping revocation check; cert is not &quot; +</span><a href="#l354"></a>
<span id="l355">                                  &quot;an end entity cert&quot;);</span><a href="#l355"></a>
<span id="l356">                }</span><a href="#l356"></a>
<span id="l357">                return;</span><a href="#l357"></a>
<span id="l358">            }</span><a href="#l358"></a>
<span id="l359">            switch (mode) {</span><a href="#l359"></a>
<span id="l360">                case PREFER_OCSP:</span><a href="#l360"></a>
<span id="l361">                case ONLY_OCSP:</span><a href="#l361"></a>
<span id="l362">                    checkOCSP(xcert, unresolvedCritExts);</span><a href="#l362"></a>
<span id="l363">                    break;</span><a href="#l363"></a>
<span id="l364">                case PREFER_CRLS:</span><a href="#l364"></a>
<span id="l365">                case ONLY_CRLS:</span><a href="#l365"></a>
<span id="l366">                    checkCRLs(xcert, unresolvedCritExts, null,</span><a href="#l366"></a>
<span id="l367">                              pubKey, crlSignFlag);</span><a href="#l367"></a>
<span id="l368">                    break;</span><a href="#l368"></a>
<span id="l369">            }</span><a href="#l369"></a>
<span id="l370">        } catch (CertPathValidatorException e) {</span><a href="#l370"></a>
<span id="l371">            if (e.getReason() == BasicReason.REVOKED) {</span><a href="#l371"></a>
<span id="l372">                throw e;</span><a href="#l372"></a>
<span id="l373">            }</span><a href="#l373"></a>
<span id="l374">            boolean eSoftFail = isSoftFailException(e);</span><a href="#l374"></a>
<span id="l375">            if (eSoftFail) {</span><a href="#l375"></a>
<span id="l376">                if (mode == Mode.ONLY_OCSP || mode == Mode.ONLY_CRLS) {</span><a href="#l376"></a>
<span id="l377">                    return;</span><a href="#l377"></a>
<span id="l378">                }</span><a href="#l378"></a>
<span id="l379">            } else {</span><a href="#l379"></a>
<span id="l380">                if (mode == Mode.ONLY_OCSP || mode == Mode.ONLY_CRLS) {</span><a href="#l380"></a>
<span id="l381">                    throw e;</span><a href="#l381"></a>
<span id="l382">                }</span><a href="#l382"></a>
<span id="l383">            }</span><a href="#l383"></a>
<span id="l384">            CertPathValidatorException cause = e;</span><a href="#l384"></a>
<span id="l385">            // Otherwise, failover</span><a href="#l385"></a>
<span id="l386">            if (debug != null) {</span><a href="#l386"></a>
<span id="l387">                debug.println(&quot;RevocationChecker.check() &quot; + e.getMessage());</span><a href="#l387"></a>
<span id="l388">                debug.println(&quot;RevocationChecker.check() preparing to failover&quot;);</span><a href="#l388"></a>
<span id="l389">            }</span><a href="#l389"></a>
<span id="l390">            try {</span><a href="#l390"></a>
<span id="l391">                switch (mode) {</span><a href="#l391"></a>
<span id="l392">                    case PREFER_OCSP:</span><a href="#l392"></a>
<span id="l393">                        checkCRLs(xcert, unresolvedCritExts, null,</span><a href="#l393"></a>
<span id="l394">                                  pubKey, crlSignFlag);</span><a href="#l394"></a>
<span id="l395">                        break;</span><a href="#l395"></a>
<span id="l396">                    case PREFER_CRLS:</span><a href="#l396"></a>
<span id="l397">                        checkOCSP(xcert, unresolvedCritExts);</span><a href="#l397"></a>
<span id="l398">                        break;</span><a href="#l398"></a>
<span id="l399">                }</span><a href="#l399"></a>
<span id="l400">            } catch (CertPathValidatorException x) {</span><a href="#l400"></a>
<span id="l401">                if (debug != null) {</span><a href="#l401"></a>
<span id="l402">                    debug.println(&quot;RevocationChecker.check() failover failed&quot;);</span><a href="#l402"></a>
<span id="l403">                    debug.println(&quot;RevocationChecker.check() &quot; + x.getMessage());</span><a href="#l403"></a>
<span id="l404">                }</span><a href="#l404"></a>
<span id="l405">                if (x.getReason() == BasicReason.REVOKED) {</span><a href="#l405"></a>
<span id="l406">                    throw x;</span><a href="#l406"></a>
<span id="l407">                }</span><a href="#l407"></a>
<span id="l408">                if (!isSoftFailException(x)) {</span><a href="#l408"></a>
<span id="l409">                    cause.addSuppressed(x);</span><a href="#l409"></a>
<span id="l410">                    throw cause;</span><a href="#l410"></a>
<span id="l411">                } else {</span><a href="#l411"></a>
<span id="l412">                    // only pass if both exceptions were soft failures</span><a href="#l412"></a>
<span id="l413">                    if (!eSoftFail) {</span><a href="#l413"></a>
<span id="l414">                        throw cause;</span><a href="#l414"></a>
<span id="l415">                    }</span><a href="#l415"></a>
<span id="l416">                }</span><a href="#l416"></a>
<span id="l417">            }</span><a href="#l417"></a>
<span id="l418">        } finally {</span><a href="#l418"></a>
<span id="l419">            updateState(xcert);</span><a href="#l419"></a>
<span id="l420">        }</span><a href="#l420"></a>
<span id="l421">    }</span><a href="#l421"></a>
<span id="l422"></span><a href="#l422"></a>
<span id="l423">    private boolean isSoftFailException(CertPathValidatorException e) {</span><a href="#l423"></a>
<span id="l424">        if (softFail &amp;&amp;</span><a href="#l424"></a>
<span id="l425">            e.getReason() == BasicReason.UNDETERMINED_REVOCATION_STATUS)</span><a href="#l425"></a>
<span id="l426">        {</span><a href="#l426"></a>
<span id="l427">            // recreate exception with correct index</span><a href="#l427"></a>
<span id="l428">            CertPathValidatorException e2 = new CertPathValidatorException(</span><a href="#l428"></a>
<span id="l429">                e.getMessage(), e.getCause(), params.certPath(), certIndex,</span><a href="#l429"></a>
<span id="l430">                e.getReason());</span><a href="#l430"></a>
<span id="l431">            softFailExceptions.addFirst(e2);</span><a href="#l431"></a>
<span id="l432">            return true;</span><a href="#l432"></a>
<span id="l433">        }</span><a href="#l433"></a>
<span id="l434">        return false;</span><a href="#l434"></a>
<span id="l435">    }</span><a href="#l435"></a>
<span id="l436"></span><a href="#l436"></a>
<span id="l437">    private void updateState(X509Certificate cert)</span><a href="#l437"></a>
<span id="l438">        throws CertPathValidatorException</span><a href="#l438"></a>
<span id="l439">    {</span><a href="#l439"></a>
<span id="l440">        issuerInfo = new OCSPResponse.IssuerInfo(anchor, cert);</span><a href="#l440"></a>
<span id="l441"></span><a href="#l441"></a>
<span id="l442">        // Make new public key if parameters are missing</span><a href="#l442"></a>
<span id="l443">        PublicKey pubKey = cert.getPublicKey();</span><a href="#l443"></a>
<span id="l444">        if (PKIX.isDSAPublicKeyWithoutParams(pubKey)) {</span><a href="#l444"></a>
<span id="l445">            // pubKey needs to inherit DSA parameters from prev key</span><a href="#l445"></a>
<span id="l446">            pubKey = BasicChecker.makeInheritedParamsKey(pubKey, prevPubKey);</span><a href="#l446"></a>
<span id="l447">        }</span><a href="#l447"></a>
<span id="l448">        prevPubKey = pubKey;</span><a href="#l448"></a>
<span id="l449">        crlSignFlag = certCanSignCrl(cert);</span><a href="#l449"></a>
<span id="l450">        if (certIndex &gt; 0) {</span><a href="#l450"></a>
<span id="l451">            certIndex--;</span><a href="#l451"></a>
<span id="l452">        }</span><a href="#l452"></a>
<span id="l453">    }</span><a href="#l453"></a>
<span id="l454"></span><a href="#l454"></a>
<span id="l455">    // Maximum clock skew in milliseconds (15 minutes) allowed when checking</span><a href="#l455"></a>
<span id="l456">    // validity of CRLs</span><a href="#l456"></a>
<span id="l457">    private static final long MAX_CLOCK_SKEW = 900000;</span><a href="#l457"></a>
<span id="l458">    private void checkCRLs(X509Certificate cert,</span><a href="#l458"></a>
<span id="l459">                           Collection&lt;String&gt; unresolvedCritExts,</span><a href="#l459"></a>
<span id="l460">                           Set&lt;X509Certificate&gt; stackedCerts,</span><a href="#l460"></a>
<span id="l461">                           PublicKey pubKey, boolean signFlag)</span><a href="#l461"></a>
<span id="l462">        throws CertPathValidatorException</span><a href="#l462"></a>
<span id="l463">    {</span><a href="#l463"></a>
<span id="l464">        checkCRLs(cert, pubKey, null, signFlag, true,</span><a href="#l464"></a>
<span id="l465">                  stackedCerts, params.trustAnchors());</span><a href="#l465"></a>
<span id="l466">    }</span><a href="#l466"></a>
<span id="l467"></span><a href="#l467"></a>
<span id="l468">    static boolean isCausedByNetworkIssue(String type, CertStoreException cse) {</span><a href="#l468"></a>
<span id="l469">        boolean result;</span><a href="#l469"></a>
<span id="l470">        Throwable t = cse.getCause();</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">        switch (type) {</span><a href="#l472"></a>
<span id="l473">            case &quot;LDAP&quot;:</span><a href="#l473"></a>
<span id="l474">                if (t != null) {</span><a href="#l474"></a>
<span id="l475">                    // These two exception classes are inside java.naming module</span><a href="#l475"></a>
<span id="l476">                    String cn = t.getClass().getName();</span><a href="#l476"></a>
<span id="l477">                    result = (cn.equals(&quot;javax.naming.ServiceUnavailableException&quot;) ||</span><a href="#l477"></a>
<span id="l478">                        cn.equals(&quot;javax.naming.CommunicationException&quot;));</span><a href="#l478"></a>
<span id="l479">                } else {</span><a href="#l479"></a>
<span id="l480">                    result = false;</span><a href="#l480"></a>
<span id="l481">                }</span><a href="#l481"></a>
<span id="l482">                break;</span><a href="#l482"></a>
<span id="l483">            case &quot;SSLServer&quot;:</span><a href="#l483"></a>
<span id="l484">                result = (t != null &amp;&amp; t instanceof IOException);</span><a href="#l484"></a>
<span id="l485">                break;</span><a href="#l485"></a>
<span id="l486">            case &quot;URI&quot;:</span><a href="#l486"></a>
<span id="l487">                result = (t != null &amp;&amp; t instanceof IOException);</span><a href="#l487"></a>
<span id="l488">                break;</span><a href="#l488"></a>
<span id="l489">            default:</span><a href="#l489"></a>
<span id="l490">                // we don't know about any other remote CertStore types</span><a href="#l490"></a>
<span id="l491">                return false;</span><a href="#l491"></a>
<span id="l492">        }</span><a href="#l492"></a>
<span id="l493">        return result;</span><a href="#l493"></a>
<span id="l494">    }</span><a href="#l494"></a>
<span id="l495"></span><a href="#l495"></a>
<span id="l496">    private void checkCRLs(X509Certificate cert, PublicKey prevKey,</span><a href="#l496"></a>
<span id="l497">                           X509Certificate prevCert, boolean signFlag,</span><a href="#l497"></a>
<span id="l498">                           boolean allowSeparateKey,</span><a href="#l498"></a>
<span id="l499">                           Set&lt;X509Certificate&gt; stackedCerts,</span><a href="#l499"></a>
<span id="l500">                           Set&lt;TrustAnchor&gt; anchors)</span><a href="#l500"></a>
<span id="l501">        throws CertPathValidatorException</span><a href="#l501"></a>
<span id="l502">    {</span><a href="#l502"></a>
<span id="l503">        if (debug != null) {</span><a href="#l503"></a>
<span id="l504">            debug.println(&quot;RevocationChecker.checkCRLs()&quot; +</span><a href="#l504"></a>
<span id="l505">                          &quot; ---checking revocation status ...&quot;);</span><a href="#l505"></a>
<span id="l506">        }</span><a href="#l506"></a>
<span id="l507"></span><a href="#l507"></a>
<span id="l508">        // Reject circular dependencies - RFC 5280 is not explicit on how</span><a href="#l508"></a>
<span id="l509">        // to handle this, but does suggest that they can be a security</span><a href="#l509"></a>
<span id="l510">        // risk and can create unresolvable dependencies</span><a href="#l510"></a>
<span id="l511">        if (stackedCerts != null &amp;&amp; stackedCerts.contains(cert)) {</span><a href="#l511"></a>
<span id="l512">            if (debug != null) {</span><a href="#l512"></a>
<span id="l513">                debug.println(&quot;RevocationChecker.checkCRLs()&quot; +</span><a href="#l513"></a>
<span id="l514">                              &quot; circular dependency&quot;);</span><a href="#l514"></a>
<span id="l515">            }</span><a href="#l515"></a>
<span id="l516">            throw new CertPathValidatorException</span><a href="#l516"></a>
<span id="l517">                 (&quot;Could not determine revocation status&quot;, null, null, -1,</span><a href="#l517"></a>
<span id="l518">                  BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l518"></a>
<span id="l519">        }</span><a href="#l519"></a>
<span id="l520"></span><a href="#l520"></a>
<span id="l521">        Set&lt;X509CRL&gt; possibleCRLs = new HashSet&lt;&gt;();</span><a href="#l521"></a>
<span id="l522">        Set&lt;X509CRL&gt; approvedCRLs = new HashSet&lt;&gt;();</span><a href="#l522"></a>
<span id="l523">        X509CRLSelector sel = new X509CRLSelector();</span><a href="#l523"></a>
<span id="l524">        sel.setCertificateChecking(cert);</span><a href="#l524"></a>
<span id="l525">        CertPathHelper.setDateAndTime(sel, params.date(), MAX_CLOCK_SKEW);</span><a href="#l525"></a>
<span id="l526"></span><a href="#l526"></a>
<span id="l527">        // First, check user-specified CertStores</span><a href="#l527"></a>
<span id="l528">        CertPathValidatorException networkFailureException = null;</span><a href="#l528"></a>
<span id="l529">        for (CertStore store : certStores) {</span><a href="#l529"></a>
<span id="l530">            try {</span><a href="#l530"></a>
<span id="l531">                for (CRL crl : store.getCRLs(sel)) {</span><a href="#l531"></a>
<span id="l532">                    possibleCRLs.add((X509CRL)crl);</span><a href="#l532"></a>
<span id="l533">                }</span><a href="#l533"></a>
<span id="l534">            } catch (CertStoreException e) {</span><a href="#l534"></a>
<span id="l535">                if (debug != null) {</span><a href="#l535"></a>
<span id="l536">                    debug.println(&quot;RevocationChecker.checkCRLs() &quot; +</span><a href="#l536"></a>
<span id="l537">                                  &quot;CertStoreException: &quot; + e.getMessage());</span><a href="#l537"></a>
<span id="l538">                }</span><a href="#l538"></a>
<span id="l539">                if (networkFailureException == null &amp;&amp;</span><a href="#l539"></a>
<span id="l540">                    isCausedByNetworkIssue(store.getType(),e)) {</span><a href="#l540"></a>
<span id="l541">                    // save this exception, we may need to throw it later</span><a href="#l541"></a>
<span id="l542">                    networkFailureException = new CertPathValidatorException(</span><a href="#l542"></a>
<span id="l543">                        &quot;Unable to determine revocation status due to &quot; +</span><a href="#l543"></a>
<span id="l544">                        &quot;network error&quot;, e, null, -1,</span><a href="#l544"></a>
<span id="l545">                        BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l545"></a>
<span id="l546">                }</span><a href="#l546"></a>
<span id="l547">            }</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">        if (debug != null) {</span><a href="#l550"></a>
<span id="l551">            debug.println(&quot;RevocationChecker.checkCRLs() &quot; +</span><a href="#l551"></a>
<span id="l552">                          &quot;possible crls.size() = &quot; + possibleCRLs.size());</span><a href="#l552"></a>
<span id="l553">        }</span><a href="#l553"></a>
<span id="l554">        boolean[] reasonsMask = new boolean[9];</span><a href="#l554"></a>
<span id="l555">        if (!possibleCRLs.isEmpty()) {</span><a href="#l555"></a>
<span id="l556">            // Now that we have a list of possible CRLs, see which ones can</span><a href="#l556"></a>
<span id="l557">            // be approved</span><a href="#l557"></a>
<span id="l558">            approvedCRLs.addAll(verifyPossibleCRLs(possibleCRLs, cert, prevKey,</span><a href="#l558"></a>
<span id="l559">                                                   signFlag, reasonsMask,</span><a href="#l559"></a>
<span id="l560">                                                   anchors));</span><a href="#l560"></a>
<span id="l561">        }</span><a href="#l561"></a>
<span id="l562"></span><a href="#l562"></a>
<span id="l563">        if (debug != null) {</span><a href="#l563"></a>
<span id="l564">            debug.println(&quot;RevocationChecker.checkCRLs() &quot; +</span><a href="#l564"></a>
<span id="l565">                          &quot;approved crls.size() = &quot; + approvedCRLs.size());</span><a href="#l565"></a>
<span id="l566">        }</span><a href="#l566"></a>
<span id="l567"></span><a href="#l567"></a>
<span id="l568">        // make sure that we have at least one CRL that _could_ cover</span><a href="#l568"></a>
<span id="l569">        // the certificate in question and all reasons are covered</span><a href="#l569"></a>
<span id="l570">        if (!approvedCRLs.isEmpty() &amp;&amp;</span><a href="#l570"></a>
<span id="l571">            Arrays.equals(reasonsMask, ALL_REASONS))</span><a href="#l571"></a>
<span id="l572">        {</span><a href="#l572"></a>
<span id="l573">            checkApprovedCRLs(cert, approvedCRLs);</span><a href="#l573"></a>
<span id="l574">        } else {</span><a href="#l574"></a>
<span id="l575">            // Check Distribution Points</span><a href="#l575"></a>
<span id="l576">            // all CRLs returned by the DP Fetcher have also been verified</span><a href="#l576"></a>
<span id="l577">            try {</span><a href="#l577"></a>
<span id="l578">                if (crlDP) {</span><a href="#l578"></a>
<span id="l579">                    approvedCRLs.addAll(DistributionPointFetcher.getCRLs(</span><a href="#l579"></a>
<span id="l580">                            sel, signFlag, prevKey, prevCert,</span><a href="#l580"></a>
<span id="l581">                            params.sigProvider(), certStores, reasonsMask,</span><a href="#l581"></a>
<span id="l582">                            anchors, null, params.variant(), anchor));</span><a href="#l582"></a>
<span id="l583">                }</span><a href="#l583"></a>
<span id="l584">            } catch (CertStoreException e) {</span><a href="#l584"></a>
<span id="l585">                if (e instanceof CertStoreTypeException) {</span><a href="#l585"></a>
<span id="l586">                    CertStoreTypeException cste = (CertStoreTypeException)e;</span><a href="#l586"></a>
<span id="l587">                    if (isCausedByNetworkIssue(cste.getType(), e)) {</span><a href="#l587"></a>
<span id="l588">                        throw new CertPathValidatorException(</span><a href="#l588"></a>
<span id="l589">                            &quot;Unable to determine revocation status due to &quot; +</span><a href="#l589"></a>
<span id="l590">                            &quot;network error&quot;, e, null, -1,</span><a href="#l590"></a>
<span id="l591">                            BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l591"></a>
<span id="l592">                    }</span><a href="#l592"></a>
<span id="l593">                }</span><a href="#l593"></a>
<span id="l594">                throw new CertPathValidatorException(e);</span><a href="#l594"></a>
<span id="l595">            }</span><a href="#l595"></a>
<span id="l596">            if (!approvedCRLs.isEmpty() &amp;&amp;</span><a href="#l596"></a>
<span id="l597">                Arrays.equals(reasonsMask, ALL_REASONS))</span><a href="#l597"></a>
<span id="l598">            {</span><a href="#l598"></a>
<span id="l599">                checkApprovedCRLs(cert, approvedCRLs);</span><a href="#l599"></a>
<span id="l600">            } else {</span><a href="#l600"></a>
<span id="l601">                if (allowSeparateKey) {</span><a href="#l601"></a>
<span id="l602">                    try {</span><a href="#l602"></a>
<span id="l603">                        verifyWithSeparateSigningKey(cert, prevKey, signFlag,</span><a href="#l603"></a>
<span id="l604">                                                     stackedCerts);</span><a href="#l604"></a>
<span id="l605">                        return;</span><a href="#l605"></a>
<span id="l606">                    } catch (CertPathValidatorException cpve) {</span><a href="#l606"></a>
<span id="l607">                        if (networkFailureException != null) {</span><a href="#l607"></a>
<span id="l608">                            // if a network issue previously prevented us from</span><a href="#l608"></a>
<span id="l609">                            // retrieving a CRL from one of the user-specified</span><a href="#l609"></a>
<span id="l610">                            // CertStores, throw it now so it can be handled</span><a href="#l610"></a>
<span id="l611">                            // appropriately</span><a href="#l611"></a>
<span id="l612">                            throw networkFailureException;</span><a href="#l612"></a>
<span id="l613">                        }</span><a href="#l613"></a>
<span id="l614">                        throw cpve;</span><a href="#l614"></a>
<span id="l615">                    }</span><a href="#l615"></a>
<span id="l616">                } else {</span><a href="#l616"></a>
<span id="l617">                    if (networkFailureException != null) {</span><a href="#l617"></a>
<span id="l618">                        // if a network issue previously prevented us from</span><a href="#l618"></a>
<span id="l619">                        // retrieving a CRL from one of the user-specified</span><a href="#l619"></a>
<span id="l620">                        // CertStores, throw it now so it can be handled</span><a href="#l620"></a>
<span id="l621">                        // appropriately</span><a href="#l621"></a>
<span id="l622">                        throw networkFailureException;</span><a href="#l622"></a>
<span id="l623">                    }</span><a href="#l623"></a>
<span id="l624">                    throw new CertPathValidatorException(</span><a href="#l624"></a>
<span id="l625">                        &quot;Could not determine revocation status&quot;, null, null, -1,</span><a href="#l625"></a>
<span id="l626">                        BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l626"></a>
<span id="l627">                }</span><a href="#l627"></a>
<span id="l628">            }</span><a href="#l628"></a>
<span id="l629">        }</span><a href="#l629"></a>
<span id="l630">    }</span><a href="#l630"></a>
<span id="l631"></span><a href="#l631"></a>
<span id="l632">    private void checkApprovedCRLs(X509Certificate cert,</span><a href="#l632"></a>
<span id="l633">                                   Set&lt;X509CRL&gt; approvedCRLs)</span><a href="#l633"></a>
<span id="l634">        throws CertPathValidatorException</span><a href="#l634"></a>
<span id="l635">    {</span><a href="#l635"></a>
<span id="l636">        // See if the cert is in the set of approved crls.</span><a href="#l636"></a>
<span id="l637">        if (debug != null) {</span><a href="#l637"></a>
<span id="l638">            BigInteger sn = cert.getSerialNumber();</span><a href="#l638"></a>
<span id="l639">            debug.println(&quot;RevocationChecker.checkApprovedCRLs() &quot; +</span><a href="#l639"></a>
<span id="l640">                          &quot;starting the final sweep...&quot;);</span><a href="#l640"></a>
<span id="l641">            debug.println(&quot;RevocationChecker.checkApprovedCRLs()&quot; +</span><a href="#l641"></a>
<span id="l642">                          &quot; cert SN: &quot; + sn.toString());</span><a href="#l642"></a>
<span id="l643">        }</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">        CRLReason reasonCode = CRLReason.UNSPECIFIED;</span><a href="#l645"></a>
<span id="l646">        X509CRLEntryImpl entry = null;</span><a href="#l646"></a>
<span id="l647">        for (X509CRL crl : approvedCRLs) {</span><a href="#l647"></a>
<span id="l648">            X509CRLEntry e = crl.getRevokedCertificate(cert);</span><a href="#l648"></a>
<span id="l649">            if (e != null) {</span><a href="#l649"></a>
<span id="l650">                try {</span><a href="#l650"></a>
<span id="l651">                    entry = X509CRLEntryImpl.toImpl(e);</span><a href="#l651"></a>
<span id="l652">                } catch (CRLException ce) {</span><a href="#l652"></a>
<span id="l653">                    throw new CertPathValidatorException(ce);</span><a href="#l653"></a>
<span id="l654">                }</span><a href="#l654"></a>
<span id="l655">                if (debug != null) {</span><a href="#l655"></a>
<span id="l656">                    debug.println(&quot;RevocationChecker.checkApprovedCRLs()&quot;</span><a href="#l656"></a>
<span id="l657">                        + &quot; CRL entry: &quot; + entry.toString());</span><a href="#l657"></a>
<span id="l658">                }</span><a href="#l658"></a>
<span id="l659"></span><a href="#l659"></a>
<span id="l660">                /*</span><a href="#l660"></a>
<span id="l661">                 * Abort CRL validation and throw exception if there are any</span><a href="#l661"></a>
<span id="l662">                 * unrecognized critical CRL entry extensions (see section</span><a href="#l662"></a>
<span id="l663">                 * 5.3 of RFC 5280).</span><a href="#l663"></a>
<span id="l664">                 */</span><a href="#l664"></a>
<span id="l665">                Set&lt;String&gt; unresCritExts = entry.getCriticalExtensionOIDs();</span><a href="#l665"></a>
<span id="l666">                if (unresCritExts != null &amp;&amp; !unresCritExts.isEmpty()) {</span><a href="#l666"></a>
<span id="l667">                    /* remove any that we will process */</span><a href="#l667"></a>
<span id="l668">                    unresCritExts.remove(ReasonCode_Id.toString());</span><a href="#l668"></a>
<span id="l669">                    unresCritExts.remove(CertificateIssuer_Id.toString());</span><a href="#l669"></a>
<span id="l670">                    if (!unresCritExts.isEmpty()) {</span><a href="#l670"></a>
<span id="l671">                        throw new CertPathValidatorException(</span><a href="#l671"></a>
<span id="l672">                            &quot;Unrecognized critical extension(s) in revoked &quot; +</span><a href="#l672"></a>
<span id="l673">                            &quot;CRL entry&quot;);</span><a href="#l673"></a>
<span id="l674">                    }</span><a href="#l674"></a>
<span id="l675">                }</span><a href="#l675"></a>
<span id="l676"></span><a href="#l676"></a>
<span id="l677">                reasonCode = entry.getRevocationReason();</span><a href="#l677"></a>
<span id="l678">                if (reasonCode == null) {</span><a href="#l678"></a>
<span id="l679">                    reasonCode = CRLReason.UNSPECIFIED;</span><a href="#l679"></a>
<span id="l680">                }</span><a href="#l680"></a>
<span id="l681">                Date revocationDate = entry.getRevocationDate();</span><a href="#l681"></a>
<span id="l682">                if (revocationDate.before(params.date())) {</span><a href="#l682"></a>
<span id="l683">                    Throwable t = new CertificateRevokedException(</span><a href="#l683"></a>
<span id="l684">                        revocationDate, reasonCode,</span><a href="#l684"></a>
<span id="l685">                        crl.getIssuerX500Principal(), entry.getExtensions());</span><a href="#l685"></a>
<span id="l686">                    throw new CertPathValidatorException(</span><a href="#l686"></a>
<span id="l687">                        t.getMessage(), t, null, -1, BasicReason.REVOKED);</span><a href="#l687"></a>
<span id="l688">                }</span><a href="#l688"></a>
<span id="l689">            }</span><a href="#l689"></a>
<span id="l690">        }</span><a href="#l690"></a>
<span id="l691">    }</span><a href="#l691"></a>
<span id="l692"></span><a href="#l692"></a>
<span id="l693">    private void checkOCSP(X509Certificate cert,</span><a href="#l693"></a>
<span id="l694">                           Collection&lt;String&gt; unresolvedCritExts)</span><a href="#l694"></a>
<span id="l695">        throws CertPathValidatorException</span><a href="#l695"></a>
<span id="l696">    {</span><a href="#l696"></a>
<span id="l697">        X509CertImpl currCert = null;</span><a href="#l697"></a>
<span id="l698">        try {</span><a href="#l698"></a>
<span id="l699">            currCert = X509CertImpl.toImpl(cert);</span><a href="#l699"></a>
<span id="l700">        } catch (CertificateException ce) {</span><a href="#l700"></a>
<span id="l701">            throw new CertPathValidatorException(ce);</span><a href="#l701"></a>
<span id="l702">        }</span><a href="#l702"></a>
<span id="l703"></span><a href="#l703"></a>
<span id="l704">        // The algorithm constraints of the OCSP trusted responder certificate</span><a href="#l704"></a>
<span id="l705">        // does not need to be checked in this code. The constraints will be</span><a href="#l705"></a>
<span id="l706">        // checked when the responder's certificate is validated.</span><a href="#l706"></a>
<span id="l707"></span><a href="#l707"></a>
<span id="l708">        OCSPResponse response = null;</span><a href="#l708"></a>
<span id="l709">        CertId certId = null;</span><a href="#l709"></a>
<span id="l710">        try {</span><a href="#l710"></a>
<span id="l711">            certId = new CertId(issuerInfo.getName(), issuerInfo.getPublicKey(),</span><a href="#l711"></a>
<span id="l712">                    currCert.getSerialNumberObject());</span><a href="#l712"></a>
<span id="l713"></span><a href="#l713"></a>
<span id="l714">            // check if there is a cached OCSP response available</span><a href="#l714"></a>
<span id="l715">            byte[] responseBytes = ocspResponses.get(cert);</span><a href="#l715"></a>
<span id="l716">            if (responseBytes != null) {</span><a href="#l716"></a>
<span id="l717">                if (debug != null) {</span><a href="#l717"></a>
<span id="l718">                    debug.println(&quot;Found cached OCSP response&quot;);</span><a href="#l718"></a>
<span id="l719">                }</span><a href="#l719"></a>
<span id="l720">                response = new OCSPResponse(responseBytes);</span><a href="#l720"></a>
<span id="l721"></span><a href="#l721"></a>
<span id="l722">                // verify the response</span><a href="#l722"></a>
<span id="l723">                byte[] nonce = null;</span><a href="#l723"></a>
<span id="l724">                for (Extension ext : ocspExtensions) {</span><a href="#l724"></a>
<span id="l725">                    if (ext.getId().equals(&quot;1.3.6.1.5.5.7.48.1.2&quot;)) {</span><a href="#l725"></a>
<span id="l726">                        nonce = ext.getValue();</span><a href="#l726"></a>
<span id="l727">                    }</span><a href="#l727"></a>
<span id="l728">                }</span><a href="#l728"></a>
<span id="l729">                response.verify(Collections.singletonList(certId), issuerInfo,</span><a href="#l729"></a>
<span id="l730">                        responderCert, params.date(), nonce, params.variant());</span><a href="#l730"></a>
<span id="l731"></span><a href="#l731"></a>
<span id="l732">            } else {</span><a href="#l732"></a>
<span id="l733">                URI responderURI = (this.responderURI != null)</span><a href="#l733"></a>
<span id="l734">                                   ? this.responderURI</span><a href="#l734"></a>
<span id="l735">                                   : OCSP.getResponderURI(currCert);</span><a href="#l735"></a>
<span id="l736">                if (responderURI == null) {</span><a href="#l736"></a>
<span id="l737">                    throw new CertPathValidatorException(</span><a href="#l737"></a>
<span id="l738">                        &quot;Certificate does not specify OCSP responder&quot;, null,</span><a href="#l738"></a>
<span id="l739">                        null, -1);</span><a href="#l739"></a>
<span id="l740">                }</span><a href="#l740"></a>
<span id="l741"></span><a href="#l741"></a>
<span id="l742">                response = OCSP.check(Collections.singletonList(certId),</span><a href="#l742"></a>
<span id="l743">                        responderURI, issuerInfo, responderCert, null,</span><a href="#l743"></a>
<span id="l744">                        ocspExtensions, params.variant());</span><a href="#l744"></a>
<span id="l745">            }</span><a href="#l745"></a>
<span id="l746">        } catch (IOException e) {</span><a href="#l746"></a>
<span id="l747">            throw new CertPathValidatorException(</span><a href="#l747"></a>
<span id="l748">                &quot;Unable to determine revocation status due to network error&quot;,</span><a href="#l748"></a>
<span id="l749">                e, null, -1, BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l749"></a>
<span id="l750">        }</span><a href="#l750"></a>
<span id="l751"></span><a href="#l751"></a>
<span id="l752">        RevocationStatus rs =</span><a href="#l752"></a>
<span id="l753">            (RevocationStatus)response.getSingleResponse(certId);</span><a href="#l753"></a>
<span id="l754">        RevocationStatus.CertStatus certStatus = rs.getCertStatus();</span><a href="#l754"></a>
<span id="l755">        if (certStatus == RevocationStatus.CertStatus.REVOKED) {</span><a href="#l755"></a>
<span id="l756">            Date revocationTime = rs.getRevocationTime();</span><a href="#l756"></a>
<span id="l757">            if (revocationTime.before(params.date())) {</span><a href="#l757"></a>
<span id="l758">                Throwable t = new CertificateRevokedException(</span><a href="#l758"></a>
<span id="l759">                    revocationTime, rs.getRevocationReason(),</span><a href="#l759"></a>
<span id="l760">                    response.getSignerCertificate().getSubjectX500Principal(),</span><a href="#l760"></a>
<span id="l761">                    rs.getSingleExtensions());</span><a href="#l761"></a>
<span id="l762">                throw new CertPathValidatorException(t.getMessage(), t, null,</span><a href="#l762"></a>
<span id="l763">                                                     -1, BasicReason.REVOKED);</span><a href="#l763"></a>
<span id="l764">            }</span><a href="#l764"></a>
<span id="l765">        } else if (certStatus == RevocationStatus.CertStatus.UNKNOWN) {</span><a href="#l765"></a>
<span id="l766">            throw new CertPathValidatorException(</span><a href="#l766"></a>
<span id="l767">                &quot;Certificate's revocation status is unknown&quot;, null,</span><a href="#l767"></a>
<span id="l768">                params.certPath(), -1,</span><a href="#l768"></a>
<span id="l769">                BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l769"></a>
<span id="l770">        }</span><a href="#l770"></a>
<span id="l771">    }</span><a href="#l771"></a>
<span id="l772"></span><a href="#l772"></a>
<span id="l773">    /*</span><a href="#l773"></a>
<span id="l774">     * Removes any non-hexadecimal characters from a string.</span><a href="#l774"></a>
<span id="l775">     */</span><a href="#l775"></a>
<span id="l776">    private static final String HEX_DIGITS = &quot;0123456789ABCDEFabcdef&quot;;</span><a href="#l776"></a>
<span id="l777">    private static String stripOutSeparators(String value) {</span><a href="#l777"></a>
<span id="l778">        char[] chars = value.toCharArray();</span><a href="#l778"></a>
<span id="l779">        StringBuilder hexNumber = new StringBuilder();</span><a href="#l779"></a>
<span id="l780">        for (int i = 0; i &lt; chars.length; i++) {</span><a href="#l780"></a>
<span id="l781">            if (HEX_DIGITS.indexOf(chars[i]) != -1) {</span><a href="#l781"></a>
<span id="l782">                hexNumber.append(chars[i]);</span><a href="#l782"></a>
<span id="l783">            }</span><a href="#l783"></a>
<span id="l784">        }</span><a href="#l784"></a>
<span id="l785">        return hexNumber.toString();</span><a href="#l785"></a>
<span id="l786">    }</span><a href="#l786"></a>
<span id="l787"></span><a href="#l787"></a>
<span id="l788">    /**</span><a href="#l788"></a>
<span id="l789">     * Checks that a cert can be used to verify a CRL.</span><a href="#l789"></a>
<span id="l790">     *</span><a href="#l790"></a>
<span id="l791">     * @param cert an X509Certificate to check</span><a href="#l791"></a>
<span id="l792">     * @return a boolean specifying if the cert is allowed to vouch for the</span><a href="#l792"></a>
<span id="l793">     *         validity of a CRL</span><a href="#l793"></a>
<span id="l794">     */</span><a href="#l794"></a>
<span id="l795">    static boolean certCanSignCrl(X509Certificate cert) {</span><a href="#l795"></a>
<span id="l796">        // if the cert doesn't include the key usage ext, or</span><a href="#l796"></a>
<span id="l797">        // the key usage ext asserts cRLSigning, return true,</span><a href="#l797"></a>
<span id="l798">        // otherwise return false.</span><a href="#l798"></a>
<span id="l799">        boolean[] keyUsage = cert.getKeyUsage();</span><a href="#l799"></a>
<span id="l800">        if (keyUsage != null) {</span><a href="#l800"></a>
<span id="l801">            return keyUsage[6];</span><a href="#l801"></a>
<span id="l802">        }</span><a href="#l802"></a>
<span id="l803">        return false;</span><a href="#l803"></a>
<span id="l804">    }</span><a href="#l804"></a>
<span id="l805"></span><a href="#l805"></a>
<span id="l806">    /**</span><a href="#l806"></a>
<span id="l807">     * Internal method that verifies a set of possible_crls,</span><a href="#l807"></a>
<span id="l808">     * and sees if each is approved, based on the cert.</span><a href="#l808"></a>
<span id="l809">     *</span><a href="#l809"></a>
<span id="l810">     * @param crls a set of possible CRLs to test for acceptability</span><a href="#l810"></a>
<span id="l811">     * @param cert the certificate whose revocation status is being checked</span><a href="#l811"></a>
<span id="l812">     * @param signFlag &lt;code&gt;true&lt;/code&gt; if prevKey was trusted to sign CRLs</span><a href="#l812"></a>
<span id="l813">     * @param prevKey the public key of the issuer of cert</span><a href="#l813"></a>
<span id="l814">     * @param reasonsMask the reason code mask</span><a href="#l814"></a>
<span id="l815">     * @param trustAnchors a &lt;code&gt;Set&lt;/code&gt; of &lt;code&gt;TrustAnchor&lt;/code&gt;s&gt;</span><a href="#l815"></a>
<span id="l816">     * @return a collection of approved crls (or an empty collection)</span><a href="#l816"></a>
<span id="l817">     */</span><a href="#l817"></a>
<span id="l818">    private static final boolean[] ALL_REASONS =</span><a href="#l818"></a>
<span id="l819">        {true, true, true, true, true, true, true, true, true};</span><a href="#l819"></a>
<span id="l820">    private Collection&lt;X509CRL&gt; verifyPossibleCRLs(Set&lt;X509CRL&gt; crls,</span><a href="#l820"></a>
<span id="l821">                                                   X509Certificate cert,</span><a href="#l821"></a>
<span id="l822">                                                   PublicKey prevKey,</span><a href="#l822"></a>
<span id="l823">                                                   boolean signFlag,</span><a href="#l823"></a>
<span id="l824">                                                   boolean[] reasonsMask,</span><a href="#l824"></a>
<span id="l825">                                                   Set&lt;TrustAnchor&gt; anchors)</span><a href="#l825"></a>
<span id="l826">        throws CertPathValidatorException</span><a href="#l826"></a>
<span id="l827">    {</span><a href="#l827"></a>
<span id="l828">        try {</span><a href="#l828"></a>
<span id="l829">            X509CertImpl certImpl = X509CertImpl.toImpl(cert);</span><a href="#l829"></a>
<span id="l830">            if (debug != null) {</span><a href="#l830"></a>
<span id="l831">                debug.println(&quot;RevocationChecker.verifyPossibleCRLs: &quot; +</span><a href="#l831"></a>
<span id="l832">                              &quot;Checking CRLDPs for &quot;</span><a href="#l832"></a>
<span id="l833">                              + certImpl.getSubjectX500Principal());</span><a href="#l833"></a>
<span id="l834">            }</span><a href="#l834"></a>
<span id="l835">            CRLDistributionPointsExtension ext =</span><a href="#l835"></a>
<span id="l836">                certImpl.getCRLDistributionPointsExtension();</span><a href="#l836"></a>
<span id="l837">            List&lt;DistributionPoint&gt; points = null;</span><a href="#l837"></a>
<span id="l838">            if (ext == null) {</span><a href="#l838"></a>
<span id="l839">                // assume a DP with reasons and CRLIssuer fields omitted</span><a href="#l839"></a>
<span id="l840">                // and a DP name of the cert issuer.</span><a href="#l840"></a>
<span id="l841">                // TODO add issuerAltName too</span><a href="#l841"></a>
<span id="l842">                X500Name certIssuer = (X500Name)certImpl.getIssuerDN();</span><a href="#l842"></a>
<span id="l843">                DistributionPoint point = new DistributionPoint(</span><a href="#l843"></a>
<span id="l844">                     new GeneralNames().add(new GeneralName(certIssuer)),</span><a href="#l844"></a>
<span id="l845">                     null, null);</span><a href="#l845"></a>
<span id="l846">                points = Collections.singletonList(point);</span><a href="#l846"></a>
<span id="l847">            } else {</span><a href="#l847"></a>
<span id="l848">                points = ext.get(CRLDistributionPointsExtension.POINTS);</span><a href="#l848"></a>
<span id="l849">            }</span><a href="#l849"></a>
<span id="l850">            Set&lt;X509CRL&gt; results = new HashSet&lt;&gt;();</span><a href="#l850"></a>
<span id="l851">            for (DistributionPoint point : points) {</span><a href="#l851"></a>
<span id="l852">                for (X509CRL crl : crls) {</span><a href="#l852"></a>
<span id="l853">                    if (DistributionPointFetcher.verifyCRL(</span><a href="#l853"></a>
<span id="l854">                            certImpl, point, crl, reasonsMask, signFlag,</span><a href="#l854"></a>
<span id="l855">                            prevKey, null, params.sigProvider(), anchors,</span><a href="#l855"></a>
<span id="l856">                            certStores, params.date(), params.variant(), anchor))</span><a href="#l856"></a>
<span id="l857">                    {</span><a href="#l857"></a>
<span id="l858">                        results.add(crl);</span><a href="#l858"></a>
<span id="l859">                    }</span><a href="#l859"></a>
<span id="l860">                }</span><a href="#l860"></a>
<span id="l861">                if (Arrays.equals(reasonsMask, ALL_REASONS))</span><a href="#l861"></a>
<span id="l862">                    break;</span><a href="#l862"></a>
<span id="l863">            }</span><a href="#l863"></a>
<span id="l864">            return results;</span><a href="#l864"></a>
<span id="l865">        } catch (CertificateException | CRLException | IOException e) {</span><a href="#l865"></a>
<span id="l866">            if (debug != null) {</span><a href="#l866"></a>
<span id="l867">                debug.println(&quot;Exception while verifying CRL: &quot;+e.getMessage());</span><a href="#l867"></a>
<span id="l868">                e.printStackTrace();</span><a href="#l868"></a>
<span id="l869">            }</span><a href="#l869"></a>
<span id="l870">            return Collections.emptySet();</span><a href="#l870"></a>
<span id="l871">        }</span><a href="#l871"></a>
<span id="l872">    }</span><a href="#l872"></a>
<span id="l873"></span><a href="#l873"></a>
<span id="l874">    /**</span><a href="#l874"></a>
<span id="l875">     * We have a cert whose revocation status couldn't be verified by</span><a href="#l875"></a>
<span id="l876">     * a CRL issued by the cert that issued the CRL. See if we can</span><a href="#l876"></a>
<span id="l877">     * find a valid CRL issued by a separate key that can verify the</span><a href="#l877"></a>
<span id="l878">     * revocation status of this certificate.</span><a href="#l878"></a>
<span id="l879">     * &lt;p&gt;</span><a href="#l879"></a>
<span id="l880">     * Note that this does not provide support for indirect CRLs,</span><a href="#l880"></a>
<span id="l881">     * only CRLs signed with a different key (but the same issuer</span><a href="#l881"></a>
<span id="l882">     * name) as the certificate being checked.</span><a href="#l882"></a>
<span id="l883">     *</span><a href="#l883"></a>
<span id="l884">     * @param currCert the &lt;code&gt;X509Certificate&lt;/code&gt; to be checked</span><a href="#l884"></a>
<span id="l885">     * @param prevKey the &lt;code&gt;PublicKey&lt;/code&gt; that failed</span><a href="#l885"></a>
<span id="l886">     * @param signFlag &lt;code&gt;true&lt;/code&gt; if that key was trusted to sign CRLs</span><a href="#l886"></a>
<span id="l887">     * @param stackedCerts a &lt;code&gt;Set&lt;/code&gt; of &lt;code&gt;X509Certificate&lt;/code&gt;s&gt;</span><a href="#l887"></a>
<span id="l888">     *                     whose revocation status depends on the</span><a href="#l888"></a>
<span id="l889">     *                     non-revoked status of this cert. To avoid</span><a href="#l889"></a>
<span id="l890">     *                     circular dependencies, we assume they're</span><a href="#l890"></a>
<span id="l891">     *                     revoked while checking the revocation</span><a href="#l891"></a>
<span id="l892">     *                     status of this cert.</span><a href="#l892"></a>
<span id="l893">     * @throws CertPathValidatorException if the cert's revocation status</span><a href="#l893"></a>
<span id="l894">     *         cannot be verified successfully with another key</span><a href="#l894"></a>
<span id="l895">     */</span><a href="#l895"></a>
<span id="l896">    private void verifyWithSeparateSigningKey(X509Certificate cert,</span><a href="#l896"></a>
<span id="l897">                                              PublicKey prevKey,</span><a href="#l897"></a>
<span id="l898">                                              boolean signFlag,</span><a href="#l898"></a>
<span id="l899">                                              Set&lt;X509Certificate&gt; stackedCerts)</span><a href="#l899"></a>
<span id="l900">        throws CertPathValidatorException</span><a href="#l900"></a>
<span id="l901">    {</span><a href="#l901"></a>
<span id="l902">        String msg = &quot;revocation status&quot;;</span><a href="#l902"></a>
<span id="l903">        if (debug != null) {</span><a href="#l903"></a>
<span id="l904">            debug.println(</span><a href="#l904"></a>
<span id="l905">                &quot;RevocationChecker.verifyWithSeparateSigningKey()&quot; +</span><a href="#l905"></a>
<span id="l906">                &quot; ---checking &quot; + msg + &quot;...&quot;);</span><a href="#l906"></a>
<span id="l907">        }</span><a href="#l907"></a>
<span id="l908"></span><a href="#l908"></a>
<span id="l909">        // Reject circular dependencies - RFC 5280 is not explicit on how</span><a href="#l909"></a>
<span id="l910">        // to handle this, but does suggest that they can be a security</span><a href="#l910"></a>
<span id="l911">        // risk and can create unresolvable dependencies</span><a href="#l911"></a>
<span id="l912">        if ((stackedCerts != null) &amp;&amp; stackedCerts.contains(cert)) {</span><a href="#l912"></a>
<span id="l913">            if (debug != null) {</span><a href="#l913"></a>
<span id="l914">                debug.println(</span><a href="#l914"></a>
<span id="l915">                    &quot;RevocationChecker.verifyWithSeparateSigningKey()&quot; +</span><a href="#l915"></a>
<span id="l916">                    &quot; circular dependency&quot;);</span><a href="#l916"></a>
<span id="l917">            }</span><a href="#l917"></a>
<span id="l918">            throw new CertPathValidatorException</span><a href="#l918"></a>
<span id="l919">                (&quot;Could not determine revocation status&quot;, null, null, -1,</span><a href="#l919"></a>
<span id="l920">                 BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l920"></a>
<span id="l921">        }</span><a href="#l921"></a>
<span id="l922"></span><a href="#l922"></a>
<span id="l923">        // Try to find another key that might be able to sign</span><a href="#l923"></a>
<span id="l924">        // CRLs vouching for this cert.</span><a href="#l924"></a>
<span id="l925">        // If prevKey wasn't trusted, maybe we just didn't have the right</span><a href="#l925"></a>
<span id="l926">        // path to it. Don't rule that key out.</span><a href="#l926"></a>
<span id="l927">        if (!signFlag) {</span><a href="#l927"></a>
<span id="l928">            buildToNewKey(cert, null, stackedCerts);</span><a href="#l928"></a>
<span id="l929">        } else {</span><a href="#l929"></a>
<span id="l930">            buildToNewKey(cert, prevKey, stackedCerts);</span><a href="#l930"></a>
<span id="l931">        }</span><a href="#l931"></a>
<span id="l932">    }</span><a href="#l932"></a>
<span id="l933"></span><a href="#l933"></a>
<span id="l934">    /**</span><a href="#l934"></a>
<span id="l935">     * Tries to find a CertPath that establishes a key that can be</span><a href="#l935"></a>
<span id="l936">     * used to verify the revocation status of a given certificate.</span><a href="#l936"></a>
<span id="l937">     * Ignores keys that have previously been tried. Throws a</span><a href="#l937"></a>
<span id="l938">     * CertPathValidatorException if no such key could be found.</span><a href="#l938"></a>
<span id="l939">     *</span><a href="#l939"></a>
<span id="l940">     * @param currCert the &lt;code&gt;X509Certificate&lt;/code&gt; to be checked</span><a href="#l940"></a>
<span id="l941">     * @param prevKey the &lt;code&gt;PublicKey&lt;/code&gt; of the certificate whose key</span><a href="#l941"></a>
<span id="l942">     *    cannot be used to vouch for the CRL and should be ignored</span><a href="#l942"></a>
<span id="l943">     * @param stackedCerts a &lt;code&gt;Set&lt;/code&gt; of &lt;code&gt;X509Certificate&lt;/code&gt;s&gt;</span><a href="#l943"></a>
<span id="l944">     *                     whose revocation status depends on the</span><a href="#l944"></a>
<span id="l945">     *                     establishment of this path.</span><a href="#l945"></a>
<span id="l946">     * @throws CertPathValidatorException on failure</span><a href="#l946"></a>
<span id="l947">     */</span><a href="#l947"></a>
<span id="l948">    private static final boolean [] CRL_SIGN_USAGE =</span><a href="#l948"></a>
<span id="l949">        { false, false, false, false, false, false, true };</span><a href="#l949"></a>
<span id="l950">    private void buildToNewKey(X509Certificate currCert,</span><a href="#l950"></a>
<span id="l951">                               PublicKey prevKey,</span><a href="#l951"></a>
<span id="l952">                               Set&lt;X509Certificate&gt; stackedCerts)</span><a href="#l952"></a>
<span id="l953">        throws CertPathValidatorException</span><a href="#l953"></a>
<span id="l954">    {</span><a href="#l954"></a>
<span id="l955"></span><a href="#l955"></a>
<span id="l956">        if (debug != null) {</span><a href="#l956"></a>
<span id="l957">            debug.println(&quot;RevocationChecker.buildToNewKey()&quot; +</span><a href="#l957"></a>
<span id="l958">                          &quot; starting work&quot;);</span><a href="#l958"></a>
<span id="l959">        }</span><a href="#l959"></a>
<span id="l960">        Set&lt;PublicKey&gt; badKeys = new HashSet&lt;&gt;();</span><a href="#l960"></a>
<span id="l961">        if (prevKey != null) {</span><a href="#l961"></a>
<span id="l962">            badKeys.add(prevKey);</span><a href="#l962"></a>
<span id="l963">        }</span><a href="#l963"></a>
<span id="l964">        X509CertSelector certSel = new RejectKeySelector(badKeys);</span><a href="#l964"></a>
<span id="l965">        certSel.setSubject(currCert.getIssuerX500Principal());</span><a href="#l965"></a>
<span id="l966">        certSel.setKeyUsage(CRL_SIGN_USAGE);</span><a href="#l966"></a>
<span id="l967"></span><a href="#l967"></a>
<span id="l968">        Set&lt;TrustAnchor&gt; newAnchors = anchor == null ?</span><a href="#l968"></a>
<span id="l969">                                      params.trustAnchors() :</span><a href="#l969"></a>
<span id="l970">                                      Collections.singleton(anchor);</span><a href="#l970"></a>
<span id="l971"></span><a href="#l971"></a>
<span id="l972">        PKIXBuilderParameters builderParams;</span><a href="#l972"></a>
<span id="l973">        try {</span><a href="#l973"></a>
<span id="l974">            builderParams = new PKIXBuilderParameters(newAnchors, certSel);</span><a href="#l974"></a>
<span id="l975">        } catch (InvalidAlgorithmParameterException iape) {</span><a href="#l975"></a>
<span id="l976">            throw new RuntimeException(iape); // should never occur</span><a href="#l976"></a>
<span id="l977">        }</span><a href="#l977"></a>
<span id="l978">        builderParams.setInitialPolicies(params.initialPolicies());</span><a href="#l978"></a>
<span id="l979">        builderParams.setCertStores(certStores);</span><a href="#l979"></a>
<span id="l980">        builderParams.setExplicitPolicyRequired</span><a href="#l980"></a>
<span id="l981">            (params.explicitPolicyRequired());</span><a href="#l981"></a>
<span id="l982">        builderParams.setPolicyMappingInhibited</span><a href="#l982"></a>
<span id="l983">            (params.policyMappingInhibited());</span><a href="#l983"></a>
<span id="l984">        builderParams.setAnyPolicyInhibited(params.anyPolicyInhibited());</span><a href="#l984"></a>
<span id="l985">        // Policy qualifiers must be rejected, since we don't have</span><a href="#l985"></a>
<span id="l986">        // any way to convey them back to the application.</span><a href="#l986"></a>
<span id="l987">        // That's the default, so no need to write code.</span><a href="#l987"></a>
<span id="l988">        builderParams.setDate(params.date());</span><a href="#l988"></a>
<span id="l989">        builderParams.setCertPathCheckers(params.certPathCheckers());</span><a href="#l989"></a>
<span id="l990">        builderParams.setSigProvider(params.sigProvider());</span><a href="#l990"></a>
<span id="l991"></span><a href="#l991"></a>
<span id="l992">        // Skip revocation during this build to detect circular</span><a href="#l992"></a>
<span id="l993">        // references. But check revocation afterwards, using the</span><a href="#l993"></a>
<span id="l994">        // key (or any other that works).</span><a href="#l994"></a>
<span id="l995">        builderParams.setRevocationEnabled(false);</span><a href="#l995"></a>
<span id="l996"></span><a href="#l996"></a>
<span id="l997">        // check for AuthorityInformationAccess extension</span><a href="#l997"></a>
<span id="l998">        if (Builder.USE_AIA == true) {</span><a href="#l998"></a>
<span id="l999">            X509CertImpl currCertImpl = null;</span><a href="#l999"></a>
<span id="l1000">            try {</span><a href="#l1000"></a>
<span id="l1001">                currCertImpl = X509CertImpl.toImpl(currCert);</span><a href="#l1001"></a>
<span id="l1002">            } catch (CertificateException ce) {</span><a href="#l1002"></a>
<span id="l1003">                // ignore but log it</span><a href="#l1003"></a>
<span id="l1004">                if (debug != null) {</span><a href="#l1004"></a>
<span id="l1005">                    debug.println(&quot;RevocationChecker.buildToNewKey: &quot; +</span><a href="#l1005"></a>
<span id="l1006">                                  &quot;error decoding cert: &quot; + ce);</span><a href="#l1006"></a>
<span id="l1007">                }</span><a href="#l1007"></a>
<span id="l1008">            }</span><a href="#l1008"></a>
<span id="l1009">            AuthorityInfoAccessExtension aiaExt = null;</span><a href="#l1009"></a>
<span id="l1010">            if (currCertImpl != null) {</span><a href="#l1010"></a>
<span id="l1011">                aiaExt = currCertImpl.getAuthorityInfoAccessExtension();</span><a href="#l1011"></a>
<span id="l1012">            }</span><a href="#l1012"></a>
<span id="l1013">            if (aiaExt != null) {</span><a href="#l1013"></a>
<span id="l1014">                List&lt;AccessDescription&gt; adList = aiaExt.getAccessDescriptions();</span><a href="#l1014"></a>
<span id="l1015">                if (adList != null) {</span><a href="#l1015"></a>
<span id="l1016">                    for (AccessDescription ad : adList) {</span><a href="#l1016"></a>
<span id="l1017">                        CertStore cs = URICertStore.getInstance(ad);</span><a href="#l1017"></a>
<span id="l1018">                        if (cs != null) {</span><a href="#l1018"></a>
<span id="l1019">                            if (debug != null) {</span><a href="#l1019"></a>
<span id="l1020">                                debug.println(&quot;adding AIAext CertStore&quot;);</span><a href="#l1020"></a>
<span id="l1021">                            }</span><a href="#l1021"></a>
<span id="l1022">                            builderParams.addCertStore(cs);</span><a href="#l1022"></a>
<span id="l1023">                        }</span><a href="#l1023"></a>
<span id="l1024">                    }</span><a href="#l1024"></a>
<span id="l1025">                }</span><a href="#l1025"></a>
<span id="l1026">            }</span><a href="#l1026"></a>
<span id="l1027">        }</span><a href="#l1027"></a>
<span id="l1028"></span><a href="#l1028"></a>
<span id="l1029">        CertPathBuilder builder = null;</span><a href="#l1029"></a>
<span id="l1030">        try {</span><a href="#l1030"></a>
<span id="l1031">            builder = CertPathBuilder.getInstance(&quot;PKIX&quot;);</span><a href="#l1031"></a>
<span id="l1032">        } catch (NoSuchAlgorithmException nsae) {</span><a href="#l1032"></a>
<span id="l1033">            throw new CertPathValidatorException(nsae);</span><a href="#l1033"></a>
<span id="l1034">        }</span><a href="#l1034"></a>
<span id="l1035">        while (true) {</span><a href="#l1035"></a>
<span id="l1036">            try {</span><a href="#l1036"></a>
<span id="l1037">                if (debug != null) {</span><a href="#l1037"></a>
<span id="l1038">                    debug.println(&quot;RevocationChecker.buildToNewKey()&quot; +</span><a href="#l1038"></a>
<span id="l1039">                                  &quot; about to try build ...&quot;);</span><a href="#l1039"></a>
<span id="l1040">                }</span><a href="#l1040"></a>
<span id="l1041">                PKIXCertPathBuilderResult cpbr =</span><a href="#l1041"></a>
<span id="l1042">                    (PKIXCertPathBuilderResult)builder.build(builderParams);</span><a href="#l1042"></a>
<span id="l1043"></span><a href="#l1043"></a>
<span id="l1044">                if (debug != null) {</span><a href="#l1044"></a>
<span id="l1045">                    debug.println(&quot;RevocationChecker.buildToNewKey()&quot; +</span><a href="#l1045"></a>
<span id="l1046">                                  &quot; about to check revocation ...&quot;);</span><a href="#l1046"></a>
<span id="l1047">                }</span><a href="#l1047"></a>
<span id="l1048">                // Now check revocation of all certs in path, assuming that</span><a href="#l1048"></a>
<span id="l1049">                // the stackedCerts are revoked.</span><a href="#l1049"></a>
<span id="l1050">                if (stackedCerts == null) {</span><a href="#l1050"></a>
<span id="l1051">                    stackedCerts = new HashSet&lt;X509Certificate&gt;();</span><a href="#l1051"></a>
<span id="l1052">                }</span><a href="#l1052"></a>
<span id="l1053">                stackedCerts.add(currCert);</span><a href="#l1053"></a>
<span id="l1054">                TrustAnchor ta = cpbr.getTrustAnchor();</span><a href="#l1054"></a>
<span id="l1055">                PublicKey prevKey2 = ta.getCAPublicKey();</span><a href="#l1055"></a>
<span id="l1056">                if (prevKey2 == null) {</span><a href="#l1056"></a>
<span id="l1057">                    prevKey2 = ta.getTrustedCert().getPublicKey();</span><a href="#l1057"></a>
<span id="l1058">                }</span><a href="#l1058"></a>
<span id="l1059">                boolean signFlag = true;</span><a href="#l1059"></a>
<span id="l1060">                List&lt;? extends Certificate&gt; cpList =</span><a href="#l1060"></a>
<span id="l1061">                    cpbr.getCertPath().getCertificates();</span><a href="#l1061"></a>
<span id="l1062">                try {</span><a href="#l1062"></a>
<span id="l1063">                    for (int i = cpList.size() - 1; i &gt;= 0; i--) {</span><a href="#l1063"></a>
<span id="l1064">                        X509Certificate cert = (X509Certificate) cpList.get(i);</span><a href="#l1064"></a>
<span id="l1065"></span><a href="#l1065"></a>
<span id="l1066">                        if (debug != null) {</span><a href="#l1066"></a>
<span id="l1067">                            debug.println(&quot;RevocationChecker.buildToNewKey()&quot;</span><a href="#l1067"></a>
<span id="l1068">                                    + &quot; index &quot; + i + &quot; checking &quot;</span><a href="#l1068"></a>
<span id="l1069">                                    + cert);</span><a href="#l1069"></a>
<span id="l1070">                        }</span><a href="#l1070"></a>
<span id="l1071">                        checkCRLs(cert, prevKey2, null, signFlag, true,</span><a href="#l1071"></a>
<span id="l1072">                                stackedCerts, newAnchors);</span><a href="#l1072"></a>
<span id="l1073">                        signFlag = certCanSignCrl(cert);</span><a href="#l1073"></a>
<span id="l1074">                        prevKey2 = cert.getPublicKey();</span><a href="#l1074"></a>
<span id="l1075">                    }</span><a href="#l1075"></a>
<span id="l1076">                } catch (CertPathValidatorException cpve) {</span><a href="#l1076"></a>
<span id="l1077">                    // ignore it and try to get another key</span><a href="#l1077"></a>
<span id="l1078">                    badKeys.add(cpbr.getPublicKey());</span><a href="#l1078"></a>
<span id="l1079">                    continue;</span><a href="#l1079"></a>
<span id="l1080">                }</span><a href="#l1080"></a>
<span id="l1081"></span><a href="#l1081"></a>
<span id="l1082">                if (debug != null) {</span><a href="#l1082"></a>
<span id="l1083">                    debug.println(&quot;RevocationChecker.buildToNewKey()&quot; +</span><a href="#l1083"></a>
<span id="l1084">                                  &quot; got key &quot; + cpbr.getPublicKey());</span><a href="#l1084"></a>
<span id="l1085">                }</span><a href="#l1085"></a>
<span id="l1086">                // Now check revocation on the current cert using that key and</span><a href="#l1086"></a>
<span id="l1087">                // the corresponding certificate.</span><a href="#l1087"></a>
<span id="l1088">                // If it doesn't check out, try to find a different key.</span><a href="#l1088"></a>
<span id="l1089">                // And if we can't find a key, then return false.</span><a href="#l1089"></a>
<span id="l1090">                PublicKey newKey = cpbr.getPublicKey();</span><a href="#l1090"></a>
<span id="l1091">                X509Certificate newCert = cpList.isEmpty() ?</span><a href="#l1091"></a>
<span id="l1092">                    null : (X509Certificate) cpList.get(0);</span><a href="#l1092"></a>
<span id="l1093">                try {</span><a href="#l1093"></a>
<span id="l1094">                    checkCRLs(currCert, newKey, newCert,</span><a href="#l1094"></a>
<span id="l1095">                              true, false, null, params.trustAnchors());</span><a href="#l1095"></a>
<span id="l1096">                    // If that passed, the cert is OK!</span><a href="#l1096"></a>
<span id="l1097">                    return;</span><a href="#l1097"></a>
<span id="l1098">                } catch (CertPathValidatorException cpve) {</span><a href="#l1098"></a>
<span id="l1099">                    // If it is revoked, rethrow exception</span><a href="#l1099"></a>
<span id="l1100">                    if (cpve.getReason() == BasicReason.REVOKED) {</span><a href="#l1100"></a>
<span id="l1101">                        throw cpve;</span><a href="#l1101"></a>
<span id="l1102">                    }</span><a href="#l1102"></a>
<span id="l1103">                    // Otherwise, ignore the exception and</span><a href="#l1103"></a>
<span id="l1104">                    // try to get another key.</span><a href="#l1104"></a>
<span id="l1105">                }</span><a href="#l1105"></a>
<span id="l1106">                badKeys.add(newKey);</span><a href="#l1106"></a>
<span id="l1107">            } catch (InvalidAlgorithmParameterException iape) {</span><a href="#l1107"></a>
<span id="l1108">                throw new CertPathValidatorException(iape);</span><a href="#l1108"></a>
<span id="l1109">            } catch (CertPathBuilderException cpbe) {</span><a href="#l1109"></a>
<span id="l1110">                throw new CertPathValidatorException</span><a href="#l1110"></a>
<span id="l1111">                    (&quot;Could not determine revocation status&quot;, null, null,</span><a href="#l1111"></a>
<span id="l1112">                     -1, BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l1112"></a>
<span id="l1113">            }</span><a href="#l1113"></a>
<span id="l1114">        }</span><a href="#l1114"></a>
<span id="l1115">    }</span><a href="#l1115"></a>
<span id="l1116"></span><a href="#l1116"></a>
<span id="l1117">    /*</span><a href="#l1117"></a>
<span id="l1118">     * This inner class extends the X509CertSelector to add an additional</span><a href="#l1118"></a>
<span id="l1119">     * check to make sure the subject public key isn't on a particular list.</span><a href="#l1119"></a>
<span id="l1120">     * This class is used by buildToNewKey() to make sure the builder doesn't</span><a href="#l1120"></a>
<span id="l1121">     * end up with a CertPath to a public key that has already been rejected.</span><a href="#l1121"></a>
<span id="l1122">     */</span><a href="#l1122"></a>
<span id="l1123">    private static class RejectKeySelector extends X509CertSelector {</span><a href="#l1123"></a>
<span id="l1124">        private final Set&lt;PublicKey&gt; badKeySet;</span><a href="#l1124"></a>
<span id="l1125"></span><a href="#l1125"></a>
<span id="l1126">        /**</span><a href="#l1126"></a>
<span id="l1127">         * Creates a new &lt;code&gt;RejectKeySelector&lt;/code&gt;.</span><a href="#l1127"></a>
<span id="l1128">         *</span><a href="#l1128"></a>
<span id="l1129">         * @param badPublicKeys a &lt;code&gt;Set&lt;/code&gt; of</span><a href="#l1129"></a>
<span id="l1130">         *                      &lt;code&gt;PublicKey&lt;/code&gt;s that</span><a href="#l1130"></a>
<span id="l1131">         *                      should be rejected (or &lt;code&gt;null&lt;/code&gt;</span><a href="#l1131"></a>
<span id="l1132">         *                      if no such check should be done)</span><a href="#l1132"></a>
<span id="l1133">         */</span><a href="#l1133"></a>
<span id="l1134">        RejectKeySelector(Set&lt;PublicKey&gt; badPublicKeys) {</span><a href="#l1134"></a>
<span id="l1135">            this.badKeySet = badPublicKeys;</span><a href="#l1135"></a>
<span id="l1136">        }</span><a href="#l1136"></a>
<span id="l1137"></span><a href="#l1137"></a>
<span id="l1138">        /**</span><a href="#l1138"></a>
<span id="l1139">         * Decides whether a &lt;code&gt;Certificate&lt;/code&gt; should be selected.</span><a href="#l1139"></a>
<span id="l1140">         *</span><a href="#l1140"></a>
<span id="l1141">         * @param cert the &lt;code&gt;Certificate&lt;/code&gt; to be checked</span><a href="#l1141"></a>
<span id="l1142">         * @return &lt;code&gt;true&lt;/code&gt; if the &lt;code&gt;Certificate&lt;/code&gt; should be</span><a href="#l1142"></a>
<span id="l1143">         *         selected, &lt;code&gt;false&lt;/code&gt; otherwise</span><a href="#l1143"></a>
<span id="l1144">         */</span><a href="#l1144"></a>
<span id="l1145">        @Override</span><a href="#l1145"></a>
<span id="l1146">        public boolean match(Certificate cert) {</span><a href="#l1146"></a>
<span id="l1147">            if (!super.match(cert))</span><a href="#l1147"></a>
<span id="l1148">                return(false);</span><a href="#l1148"></a>
<span id="l1149"></span><a href="#l1149"></a>
<span id="l1150">            if (badKeySet.contains(cert.getPublicKey())) {</span><a href="#l1150"></a>
<span id="l1151">                if (debug != null)</span><a href="#l1151"></a>
<span id="l1152">                    debug.println(&quot;RejectKeySelector.match: bad key&quot;);</span><a href="#l1152"></a>
<span id="l1153">                return false;</span><a href="#l1153"></a>
<span id="l1154">            }</span><a href="#l1154"></a>
<span id="l1155"></span><a href="#l1155"></a>
<span id="l1156">            if (debug != null)</span><a href="#l1156"></a>
<span id="l1157">                debug.println(&quot;RejectKeySelector.match: returning true&quot;);</span><a href="#l1157"></a>
<span id="l1158">            return true;</span><a href="#l1158"></a>
<span id="l1159">        }</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">        /**</span><a href="#l1161"></a>
<span id="l1162">         * Return a printable representation of the &lt;code&gt;CertSelector&lt;/code&gt;.</span><a href="#l1162"></a>
<span id="l1163">         *</span><a href="#l1163"></a>
<span id="l1164">         * @return a &lt;code&gt;String&lt;/code&gt; describing the contents of the</span><a href="#l1164"></a>
<span id="l1165">         *         &lt;code&gt;CertSelector&lt;/code&gt;</span><a href="#l1165"></a>
<span id="l1166">         */</span><a href="#l1166"></a>
<span id="l1167">        @Override</span><a href="#l1167"></a>
<span id="l1168">        public String toString() {</span><a href="#l1168"></a>
<span id="l1169">            StringBuilder sb = new StringBuilder();</span><a href="#l1169"></a>
<span id="l1170">            sb.append(&quot;RejectKeySelector: [\n&quot;);</span><a href="#l1170"></a>
<span id="l1171">            sb.append(super.toString());</span><a href="#l1171"></a>
<span id="l1172">            sb.append(badKeySet);</span><a href="#l1172"></a>
<span id="l1173">            sb.append(&quot;]&quot;);</span><a href="#l1173"></a>
<span id="l1174">            return sb.toString();</span><a href="#l1174"></a>
<span id="l1175">        }</span><a href="#l1175"></a>
<span id="l1176">    }</span><a href="#l1176"></a>
<span id="l1177">}</span><a href="#l1177"></a></pre>
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

