<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/validator/SimpleValidator.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/validator/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/validator/SimpleValidator.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/validator/SimpleValidator.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/validator/SimpleValidator.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/validator/SimpleValidator.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/validator/SimpleValidator.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/validator/SimpleValidator.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/validator/SimpleValidator.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/896bbc5499ff/src/share/classes/sun/security/validator/SimpleValidator.java">896bbc5499ff</a> </td>
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
<span id="l26">package sun.security.validator;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.util.*;</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31">import java.security.*;</span><a href="#l31"></a>
<span id="l32">import java.security.cert.*;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">import javax.security.auth.x500.X500Principal;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">import sun.security.x509.X509CertImpl;</span><a href="#l36"></a>
<span id="l37">import sun.security.x509.KeyIdentifier;</span><a href="#l37"></a>
<span id="l38">import sun.security.x509.NetscapeCertTypeExtension;</span><a href="#l38"></a>
<span id="l39">import sun.security.util.DerValue;</span><a href="#l39"></a>
<span id="l40">import sun.security.util.DerInputStream;</span><a href="#l40"></a>
<span id="l41">import sun.security.util.ObjectIdentifier;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">import sun.security.provider.certpath.AlgorithmChecker;</span><a href="#l43"></a>
<span id="l44">import sun.security.provider.certpath.UntrustedChecker;</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">/**</span><a href="#l46"></a>
<span id="l47"> * A simple validator implementation. It is based on code from the JSSE</span><a href="#l47"></a>
<span id="l48"> * X509TrustManagerImpl. This implementation is designed for compatibility with</span><a href="#l48"></a>
<span id="l49"> * deployed certificates and previous J2SE versions. It will never support</span><a href="#l49"></a>
<span id="l50"> * more advanced features and will be deemphasized in favor of the PKIX</span><a href="#l50"></a>
<span id="l51"> * validator going forward.</span><a href="#l51"></a>
<span id="l52"> * &lt;p&gt;</span><a href="#l52"></a>
<span id="l53"> * {@code SimpleValidator} objects are immutable once they have been created.</span><a href="#l53"></a>
<span id="l54"> * Please DO NOT add methods that can change the state of an instance once</span><a href="#l54"></a>
<span id="l55"> * it has been created.</span><a href="#l55"></a>
<span id="l56"> *</span><a href="#l56"></a>
<span id="l57"> * @author Andreas Sterbenz</span><a href="#l57"></a>
<span id="l58"> */</span><a href="#l58"></a>
<span id="l59">public final class SimpleValidator extends Validator {</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    // Constants for the OIDs we need</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    final static String OID_BASIC_CONSTRAINTS = &quot;2.5.29.19&quot;;</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    final static String OID_NETSCAPE_CERT_TYPE = &quot;2.16.840.1.113730.1.1&quot;;</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">    final static String OID_KEY_USAGE = &quot;2.5.29.15&quot;;</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    final static String OID_EXTENDED_KEY_USAGE = &quot;2.5.29.37&quot;;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    final static String OID_EKU_ANY_USAGE = &quot;2.5.29.37.0&quot;;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    final static ObjectIdentifier OBJID_NETSCAPE_CERT_TYPE =</span><a href="#l73"></a>
<span id="l74">        NetscapeCertTypeExtension.NetscapeCertType_Id;</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    private final static String NSCT_SSL_CA =</span><a href="#l76"></a>
<span id="l77">                                NetscapeCertTypeExtension.SSL_CA;</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    private final static String NSCT_CODE_SIGNING_CA =</span><a href="#l79"></a>
<span id="l80">                                NetscapeCertTypeExtension.OBJECT_SIGNING_CA;</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">    /**</span><a href="#l82"></a>
<span id="l83">     * The trusted certificates as:</span><a href="#l83"></a>
<span id="l84">     * Map (X500Principal)subject of trusted cert -&gt; List of X509Certificate</span><a href="#l84"></a>
<span id="l85">     * The list is used because there may be multiple certificates</span><a href="#l85"></a>
<span id="l86">     * with an identical subject DN.</span><a href="#l86"></a>
<span id="l87">     */</span><a href="#l87"></a>
<span id="l88">    private final Map&lt;X500Principal, List&lt;X509Certificate&gt;&gt;</span><a href="#l88"></a>
<span id="l89">                                            trustedX500Principals;</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">    /**</span><a href="#l91"></a>
<span id="l92">     * Set of the trusted certificates. Present only for</span><a href="#l92"></a>
<span id="l93">     * getTrustedCertificates().</span><a href="#l93"></a>
<span id="l94">     */</span><a href="#l94"></a>
<span id="l95">    private final Collection&lt;X509Certificate&gt; trustedCerts;</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    SimpleValidator(String variant, Collection&lt;X509Certificate&gt; trustedCerts) {</span><a href="#l97"></a>
<span id="l98">        super(TYPE_SIMPLE, variant);</span><a href="#l98"></a>
<span id="l99">        this.trustedCerts = trustedCerts;</span><a href="#l99"></a>
<span id="l100">        trustedX500Principals =</span><a href="#l100"></a>
<span id="l101">                        new HashMap&lt;X500Principal, List&lt;X509Certificate&gt;&gt;();</span><a href="#l101"></a>
<span id="l102">        for (X509Certificate cert : trustedCerts) {</span><a href="#l102"></a>
<span id="l103">            X500Principal principal = cert.getSubjectX500Principal();</span><a href="#l103"></a>
<span id="l104">            List&lt;X509Certificate&gt; list = trustedX500Principals.get(principal);</span><a href="#l104"></a>
<span id="l105">            if (list == null) {</span><a href="#l105"></a>
<span id="l106">                // this actually should be a set, but duplicate entries</span><a href="#l106"></a>
<span id="l107">                // are not a problem and we can avoid the Set overhead</span><a href="#l107"></a>
<span id="l108">                list = new ArrayList&lt;X509Certificate&gt;(2);</span><a href="#l108"></a>
<span id="l109">                trustedX500Principals.put(principal, list);</span><a href="#l109"></a>
<span id="l110">            }</span><a href="#l110"></a>
<span id="l111">            list.add(cert);</span><a href="#l111"></a>
<span id="l112">        }</span><a href="#l112"></a>
<span id="l113">    }</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    public Collection&lt;X509Certificate&gt; getTrustedCertificates() {</span><a href="#l115"></a>
<span id="l116">        return trustedCerts;</span><a href="#l116"></a>
<span id="l117">    }</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">    /**</span><a href="#l119"></a>
<span id="l120">     * Perform simple validation of chain. The arguments otherCerts and</span><a href="#l120"></a>
<span id="l121">     * parameter are ignored.</span><a href="#l121"></a>
<span id="l122">     */</span><a href="#l122"></a>
<span id="l123">    @Override</span><a href="#l123"></a>
<span id="l124">    X509Certificate[] engineValidate(X509Certificate[] chain,</span><a href="#l124"></a>
<span id="l125">            Collection&lt;X509Certificate&gt; otherCerts,</span><a href="#l125"></a>
<span id="l126">            List&lt;byte[]&gt; responseList,</span><a href="#l126"></a>
<span id="l127">            AlgorithmConstraints constraints,</span><a href="#l127"></a>
<span id="l128">            Object parameter) throws CertificateException {</span><a href="#l128"></a>
<span id="l129">        if ((chain == null) || (chain.length == 0)) {</span><a href="#l129"></a>
<span id="l130">            throw new CertificateException</span><a href="#l130"></a>
<span id="l131">                (&quot;null or zero-length certificate chain&quot;);</span><a href="#l131"></a>
<span id="l132">        }</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">        // make sure chain includes a trusted cert</span><a href="#l134"></a>
<span id="l135">        chain = buildTrustedChain(chain);</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">        @SuppressWarnings(&quot;deprecation&quot;)</span><a href="#l137"></a>
<span id="l138">        Date date = validationDate;</span><a href="#l138"></a>
<span id="l139">        if (date == null) {</span><a href="#l139"></a>
<span id="l140">            date = new Date();</span><a href="#l140"></a>
<span id="l141">        }</span><a href="#l141"></a>
<span id="l142"></span><a href="#l142"></a>
<span id="l143">        // create distrusted certificates checker</span><a href="#l143"></a>
<span id="l144">        UntrustedChecker untrustedChecker = new UntrustedChecker();</span><a href="#l144"></a>
<span id="l145"></span><a href="#l145"></a>
<span id="l146">        // check if anchor is untrusted</span><a href="#l146"></a>
<span id="l147">        X509Certificate anchorCert = chain[chain.length - 1];</span><a href="#l147"></a>
<span id="l148">        try {</span><a href="#l148"></a>
<span id="l149">            untrustedChecker.check(anchorCert);</span><a href="#l149"></a>
<span id="l150">        } catch (CertPathValidatorException cpve) {</span><a href="#l150"></a>
<span id="l151">            throw new ValidatorException(</span><a href="#l151"></a>
<span id="l152">                &quot;Untrusted certificate: &quot;+ anchorCert.getSubjectX500Principal(),</span><a href="#l152"></a>
<span id="l153">                ValidatorException.T_UNTRUSTED_CERT, anchorCert, cpve);</span><a href="#l153"></a>
<span id="l154">        }</span><a href="#l154"></a>
<span id="l155"></span><a href="#l155"></a>
<span id="l156">        // create default algorithm constraints checker</span><a href="#l156"></a>
<span id="l157">        TrustAnchor anchor = new TrustAnchor(anchorCert, null);</span><a href="#l157"></a>
<span id="l158">        AlgorithmChecker defaultAlgChecker =</span><a href="#l158"></a>
<span id="l159">                new AlgorithmChecker(anchor, variant);</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">        // create application level algorithm constraints checker</span><a href="#l161"></a>
<span id="l162">        AlgorithmChecker appAlgChecker = null;</span><a href="#l162"></a>
<span id="l163">        if (constraints != null) {</span><a href="#l163"></a>
<span id="l164">            appAlgChecker = new AlgorithmChecker(anchor, constraints, null,</span><a href="#l164"></a>
<span id="l165">                    variant);</span><a href="#l165"></a>
<span id="l166">        }</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">        // verify top down, starting at the certificate issued by</span><a href="#l168"></a>
<span id="l169">        // the trust anchor</span><a href="#l169"></a>
<span id="l170">        int maxPathLength = chain.length - 1;</span><a href="#l170"></a>
<span id="l171">        for (int i = chain.length - 2; i &gt;= 0; i--) {</span><a href="#l171"></a>
<span id="l172">            X509Certificate issuerCert = chain[i + 1];</span><a href="#l172"></a>
<span id="l173">            X509Certificate cert = chain[i];</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">            // check untrusted certificate</span><a href="#l175"></a>
<span id="l176">            try {</span><a href="#l176"></a>
<span id="l177">                // Untrusted checker does not care about the unresolved</span><a href="#l177"></a>
<span id="l178">                // critical extensions.</span><a href="#l178"></a>
<span id="l179">                untrustedChecker.check(cert, Collections.&lt;String&gt;emptySet());</span><a href="#l179"></a>
<span id="l180">            } catch (CertPathValidatorException cpve) {</span><a href="#l180"></a>
<span id="l181">                throw new ValidatorException(</span><a href="#l181"></a>
<span id="l182">                    &quot;Untrusted certificate: &quot; + cert.getSubjectX500Principal(),</span><a href="#l182"></a>
<span id="l183">                    ValidatorException.T_UNTRUSTED_CERT, cert, cpve);</span><a href="#l183"></a>
<span id="l184">            }</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">            // check certificate algorithm</span><a href="#l186"></a>
<span id="l187">            try {</span><a href="#l187"></a>
<span id="l188">                // Algorithm checker does not care about the unresolved</span><a href="#l188"></a>
<span id="l189">                // critical extensions.</span><a href="#l189"></a>
<span id="l190">                defaultAlgChecker.check(cert, Collections.&lt;String&gt;emptySet());</span><a href="#l190"></a>
<span id="l191">                if (appAlgChecker != null) {</span><a href="#l191"></a>
<span id="l192">                    appAlgChecker.check(cert, Collections.&lt;String&gt;emptySet());</span><a href="#l192"></a>
<span id="l193">                }</span><a href="#l193"></a>
<span id="l194">            } catch (CertPathValidatorException cpve) {</span><a href="#l194"></a>
<span id="l195">                throw new ValidatorException</span><a href="#l195"></a>
<span id="l196">                        (ValidatorException.T_ALGORITHM_DISABLED, cert, cpve);</span><a href="#l196"></a>
<span id="l197">            }</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">            // no validity check for code signing certs</span><a href="#l199"></a>
<span id="l200">            if ((variant.equals(VAR_CODE_SIGNING) == false)</span><a href="#l200"></a>
<span id="l201">                        &amp;&amp; (variant.equals(VAR_JCE_SIGNING) == false)) {</span><a href="#l201"></a>
<span id="l202">                cert.checkValidity(date);</span><a href="#l202"></a>
<span id="l203">            }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">            // check name chaining</span><a href="#l205"></a>
<span id="l206">            if (cert.getIssuerX500Principal().equals(</span><a href="#l206"></a>
<span id="l207">                        issuerCert.getSubjectX500Principal()) == false) {</span><a href="#l207"></a>
<span id="l208">                throw new ValidatorException</span><a href="#l208"></a>
<span id="l209">                        (ValidatorException.T_NAME_CHAINING, cert);</span><a href="#l209"></a>
<span id="l210">            }</span><a href="#l210"></a>
<span id="l211"></span><a href="#l211"></a>
<span id="l212">            // check signature</span><a href="#l212"></a>
<span id="l213">            try {</span><a href="#l213"></a>
<span id="l214">                cert.verify(issuerCert.getPublicKey());</span><a href="#l214"></a>
<span id="l215">            } catch (GeneralSecurityException e) {</span><a href="#l215"></a>
<span id="l216">                throw new ValidatorException</span><a href="#l216"></a>
<span id="l217">                        (ValidatorException.T_SIGNATURE_ERROR, cert, e);</span><a href="#l217"></a>
<span id="l218">            }</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">            // check extensions for CA certs</span><a href="#l220"></a>
<span id="l221">            if (i != 0) {</span><a href="#l221"></a>
<span id="l222">                maxPathLength = checkExtensions(cert, maxPathLength);</span><a href="#l222"></a>
<span id="l223">            }</span><a href="#l223"></a>
<span id="l224">        }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">        return chain;</span><a href="#l226"></a>
<span id="l227">    }</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    private int checkExtensions(X509Certificate cert, int maxPathLen)</span><a href="#l229"></a>
<span id="l230">            throws CertificateException {</span><a href="#l230"></a>
<span id="l231">        Set&lt;String&gt; critSet = cert.getCriticalExtensionOIDs();</span><a href="#l231"></a>
<span id="l232">        if (critSet == null) {</span><a href="#l232"></a>
<span id="l233">            critSet = Collections.&lt;String&gt;emptySet();</span><a href="#l233"></a>
<span id="l234">        }</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">        // Check the basic constraints extension</span><a href="#l236"></a>
<span id="l237">        int pathLenConstraint =</span><a href="#l237"></a>
<span id="l238">                checkBasicConstraints(cert, critSet, maxPathLen);</span><a href="#l238"></a>
<span id="l239"></span><a href="#l239"></a>
<span id="l240">        // Check the key usage and extended key usage extensions</span><a href="#l240"></a>
<span id="l241">        checkKeyUsage(cert, critSet);</span><a href="#l241"></a>
<span id="l242"></span><a href="#l242"></a>
<span id="l243">        // check Netscape certificate type extension</span><a href="#l243"></a>
<span id="l244">        checkNetscapeCertType(cert, critSet);</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">        if (!critSet.isEmpty()) {</span><a href="#l246"></a>
<span id="l247">            throw new ValidatorException</span><a href="#l247"></a>
<span id="l248">                (&quot;Certificate contains unknown critical extensions: &quot; + critSet,</span><a href="#l248"></a>
<span id="l249">                ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l249"></a>
<span id="l250">        }</span><a href="#l250"></a>
<span id="l251"></span><a href="#l251"></a>
<span id="l252">        return pathLenConstraint;</span><a href="#l252"></a>
<span id="l253">    }</span><a href="#l253"></a>
<span id="l254"></span><a href="#l254"></a>
<span id="l255">    private void checkNetscapeCertType(X509Certificate cert,</span><a href="#l255"></a>
<span id="l256">            Set&lt;String&gt; critSet) throws CertificateException {</span><a href="#l256"></a>
<span id="l257">        if (variant.equals(VAR_GENERIC)) {</span><a href="#l257"></a>
<span id="l258">            // nothing</span><a href="#l258"></a>
<span id="l259">        } else if (variant.equals(VAR_TLS_CLIENT)</span><a href="#l259"></a>
<span id="l260">                || variant.equals(VAR_TLS_SERVER)) {</span><a href="#l260"></a>
<span id="l261">            if (getNetscapeCertTypeBit(cert, NSCT_SSL_CA) == false) {</span><a href="#l261"></a>
<span id="l262">                throw new ValidatorException</span><a href="#l262"></a>
<span id="l263">                        (&quot;Invalid Netscape CertType extension for SSL CA &quot;</span><a href="#l263"></a>
<span id="l264">                        + &quot;certificate&quot;,</span><a href="#l264"></a>
<span id="l265">                        ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l265"></a>
<span id="l266">            }</span><a href="#l266"></a>
<span id="l267">            critSet.remove(OID_NETSCAPE_CERT_TYPE);</span><a href="#l267"></a>
<span id="l268">        } else if (variant.equals(VAR_CODE_SIGNING)</span><a href="#l268"></a>
<span id="l269">                || variant.equals(VAR_JCE_SIGNING)) {</span><a href="#l269"></a>
<span id="l270">            if (getNetscapeCertTypeBit(cert, NSCT_CODE_SIGNING_CA) == false) {</span><a href="#l270"></a>
<span id="l271">                throw new ValidatorException</span><a href="#l271"></a>
<span id="l272">                        (&quot;Invalid Netscape CertType extension for code &quot;</span><a href="#l272"></a>
<span id="l273">                        + &quot;signing CA certificate&quot;,</span><a href="#l273"></a>
<span id="l274">                        ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l274"></a>
<span id="l275">            }</span><a href="#l275"></a>
<span id="l276">            critSet.remove(OID_NETSCAPE_CERT_TYPE);</span><a href="#l276"></a>
<span id="l277">        } else {</span><a href="#l277"></a>
<span id="l278">            throw new CertificateException(&quot;Unknown variant &quot; + variant);</span><a href="#l278"></a>
<span id="l279">        }</span><a href="#l279"></a>
<span id="l280">    }</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">    /**</span><a href="#l282"></a>
<span id="l283">     * Get the value of the specified bit in the Netscape certificate type</span><a href="#l283"></a>
<span id="l284">     * extension. If the extension is not present at all, we return true.</span><a href="#l284"></a>
<span id="l285">     */</span><a href="#l285"></a>
<span id="l286">    static boolean getNetscapeCertTypeBit(X509Certificate cert, String type) {</span><a href="#l286"></a>
<span id="l287">        try {</span><a href="#l287"></a>
<span id="l288">            NetscapeCertTypeExtension ext;</span><a href="#l288"></a>
<span id="l289">            if (cert instanceof X509CertImpl) {</span><a href="#l289"></a>
<span id="l290">                X509CertImpl certImpl = (X509CertImpl)cert;</span><a href="#l290"></a>
<span id="l291">                ObjectIdentifier oid = OBJID_NETSCAPE_CERT_TYPE;</span><a href="#l291"></a>
<span id="l292">                ext = (NetscapeCertTypeExtension)certImpl.getExtension(oid);</span><a href="#l292"></a>
<span id="l293">                if (ext == null) {</span><a href="#l293"></a>
<span id="l294">                    return true;</span><a href="#l294"></a>
<span id="l295">                }</span><a href="#l295"></a>
<span id="l296">            } else {</span><a href="#l296"></a>
<span id="l297">                byte[] extVal = cert.getExtensionValue(OID_NETSCAPE_CERT_TYPE);</span><a href="#l297"></a>
<span id="l298">                if (extVal == null) {</span><a href="#l298"></a>
<span id="l299">                    return true;</span><a href="#l299"></a>
<span id="l300">                }</span><a href="#l300"></a>
<span id="l301">                DerInputStream in = new DerInputStream(extVal);</span><a href="#l301"></a>
<span id="l302">                byte[] encoded = in.getOctetString();</span><a href="#l302"></a>
<span id="l303">                encoded = new DerValue(encoded).getUnalignedBitString()</span><a href="#l303"></a>
<span id="l304">                                                                .toByteArray();</span><a href="#l304"></a>
<span id="l305">                ext = new NetscapeCertTypeExtension(encoded);</span><a href="#l305"></a>
<span id="l306">            }</span><a href="#l306"></a>
<span id="l307">            Boolean val = ext.get(type);</span><a href="#l307"></a>
<span id="l308">            return val.booleanValue();</span><a href="#l308"></a>
<span id="l309">        } catch (IOException e) {</span><a href="#l309"></a>
<span id="l310">            return false;</span><a href="#l310"></a>
<span id="l311">        }</span><a href="#l311"></a>
<span id="l312">    }</span><a href="#l312"></a>
<span id="l313"></span><a href="#l313"></a>
<span id="l314">    private int checkBasicConstraints(X509Certificate cert,</span><a href="#l314"></a>
<span id="l315">            Set&lt;String&gt; critSet, int maxPathLen) throws CertificateException {</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">        critSet.remove(OID_BASIC_CONSTRAINTS);</span><a href="#l317"></a>
<span id="l318">        int constraints = cert.getBasicConstraints();</span><a href="#l318"></a>
<span id="l319">        // reject, if extension missing or not a CA (constraints == -1)</span><a href="#l319"></a>
<span id="l320">        if (constraints &lt; 0) {</span><a href="#l320"></a>
<span id="l321">            throw new ValidatorException(&quot;End user tried to act as a CA&quot;,</span><a href="#l321"></a>
<span id="l322">                ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l322"></a>
<span id="l323">        }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">        // if the certificate is self-issued, ignore the pathLenConstraint</span><a href="#l325"></a>
<span id="l326">        // checking.</span><a href="#l326"></a>
<span id="l327">        if (!X509CertImpl.isSelfIssued(cert)) {</span><a href="#l327"></a>
<span id="l328">            if (maxPathLen &lt;= 0) {</span><a href="#l328"></a>
<span id="l329">                throw new ValidatorException(&quot;Violated path length constraints&quot;,</span><a href="#l329"></a>
<span id="l330">                    ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l330"></a>
<span id="l331">            }</span><a href="#l331"></a>
<span id="l332"></span><a href="#l332"></a>
<span id="l333">            maxPathLen--;</span><a href="#l333"></a>
<span id="l334">        }</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">        if (maxPathLen &gt; constraints) {</span><a href="#l336"></a>
<span id="l337">            maxPathLen = constraints;</span><a href="#l337"></a>
<span id="l338">        }</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">        return maxPathLen;</span><a href="#l340"></a>
<span id="l341">    }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">    /*</span><a href="#l343"></a>
<span id="l344">     * Verify the key usage and extended key usage for intermediate</span><a href="#l344"></a>
<span id="l345">     * certificates.</span><a href="#l345"></a>
<span id="l346">     */</span><a href="#l346"></a>
<span id="l347">    private void checkKeyUsage(X509Certificate cert, Set&lt;String&gt; critSet)</span><a href="#l347"></a>
<span id="l348">            throws CertificateException {</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">        critSet.remove(OID_KEY_USAGE);</span><a href="#l350"></a>
<span id="l351">        // EKU irrelevant in CA certificates</span><a href="#l351"></a>
<span id="l352">        critSet.remove(OID_EXTENDED_KEY_USAGE);</span><a href="#l352"></a>
<span id="l353"></span><a href="#l353"></a>
<span id="l354">        // check key usage extension</span><a href="#l354"></a>
<span id="l355">        boolean[] keyUsageInfo = cert.getKeyUsage();</span><a href="#l355"></a>
<span id="l356">        if (keyUsageInfo != null) {</span><a href="#l356"></a>
<span id="l357">            // keyUsageInfo[5] is for keyCertSign.</span><a href="#l357"></a>
<span id="l358">            if ((keyUsageInfo.length &lt; 6) || (keyUsageInfo[5] == false)) {</span><a href="#l358"></a>
<span id="l359">                throw new ValidatorException</span><a href="#l359"></a>
<span id="l360">                        (&quot;Wrong key usage: expected keyCertSign&quot;,</span><a href="#l360"></a>
<span id="l361">                        ValidatorException.T_CA_EXTENSIONS, cert);</span><a href="#l361"></a>
<span id="l362">            }</span><a href="#l362"></a>
<span id="l363">        }</span><a href="#l363"></a>
<span id="l364">    }</span><a href="#l364"></a>
<span id="l365"></span><a href="#l365"></a>
<span id="l366">    /**</span><a href="#l366"></a>
<span id="l367">     * Build a trusted certificate chain. This method always returns a chain</span><a href="#l367"></a>
<span id="l368">     * with a trust anchor as the final cert in the chain. If no trust anchor</span><a href="#l368"></a>
<span id="l369">     * could be found, a CertificateException is thrown.</span><a href="#l369"></a>
<span id="l370">     */</span><a href="#l370"></a>
<span id="l371">    private X509Certificate[] buildTrustedChain(X509Certificate[] chain)</span><a href="#l371"></a>
<span id="l372">            throws CertificateException {</span><a href="#l372"></a>
<span id="l373">        List&lt;X509Certificate&gt; c = new ArrayList&lt;X509Certificate&gt;(chain.length);</span><a href="#l373"></a>
<span id="l374">        // scan chain starting at EE cert</span><a href="#l374"></a>
<span id="l375">        // if a trusted certificate is found, append it and return</span><a href="#l375"></a>
<span id="l376">        for (int i = 0; i &lt; chain.length; i++) {</span><a href="#l376"></a>
<span id="l377">            X509Certificate cert = chain[i];</span><a href="#l377"></a>
<span id="l378">            X509Certificate trustedCert = getTrustedCertificate(cert);</span><a href="#l378"></a>
<span id="l379">            if (trustedCert != null) {</span><a href="#l379"></a>
<span id="l380">                c.add(trustedCert);</span><a href="#l380"></a>
<span id="l381">                return c.toArray(CHAIN0);</span><a href="#l381"></a>
<span id="l382">            }</span><a href="#l382"></a>
<span id="l383">            c.add(cert);</span><a href="#l383"></a>
<span id="l384">        }</span><a href="#l384"></a>
<span id="l385"></span><a href="#l385"></a>
<span id="l386">        // check if we can append a trusted cert</span><a href="#l386"></a>
<span id="l387">        X509Certificate cert = chain[chain.length - 1];</span><a href="#l387"></a>
<span id="l388">        X500Principal subject = cert.getSubjectX500Principal();</span><a href="#l388"></a>
<span id="l389">        X500Principal issuer = cert.getIssuerX500Principal();</span><a href="#l389"></a>
<span id="l390">        List&lt;X509Certificate&gt; list = trustedX500Principals.get(issuer);</span><a href="#l390"></a>
<span id="l391">        if (list != null) {</span><a href="#l391"></a>
<span id="l392">            X509Certificate trustedCert = list.iterator().next();</span><a href="#l392"></a>
<span id="l393">            c.add(trustedCert);</span><a href="#l393"></a>
<span id="l394">            return c.toArray(CHAIN0);</span><a href="#l394"></a>
<span id="l395">        }</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">        // no trusted cert found, error</span><a href="#l397"></a>
<span id="l398">        throw new ValidatorException(ValidatorException.T_NO_TRUST_ANCHOR);</span><a href="#l398"></a>
<span id="l399">    }</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">    /**</span><a href="#l401"></a>
<span id="l402">     * Return a trusted certificate that matches the input certificate,</span><a href="#l402"></a>
<span id="l403">     * or null if no such certificate can be found. This method also handles</span><a href="#l403"></a>
<span id="l404">     * cases where a CA re-issues a trust anchor with the same public key and</span><a href="#l404"></a>
<span id="l405">     * same subject and issuer names but a new validity period, etc.</span><a href="#l405"></a>
<span id="l406">     */</span><a href="#l406"></a>
<span id="l407">    private X509Certificate getTrustedCertificate(X509Certificate cert) {</span><a href="#l407"></a>
<span id="l408">        Principal certSubjectName = cert.getSubjectX500Principal();</span><a href="#l408"></a>
<span id="l409">        List&lt;X509Certificate&gt; list = trustedX500Principals.get(certSubjectName);</span><a href="#l409"></a>
<span id="l410">        if (list == null) {</span><a href="#l410"></a>
<span id="l411">            return null;</span><a href="#l411"></a>
<span id="l412">        }</span><a href="#l412"></a>
<span id="l413"></span><a href="#l413"></a>
<span id="l414">        Principal certIssuerName = cert.getIssuerX500Principal();</span><a href="#l414"></a>
<span id="l415">        PublicKey certPublicKey = cert.getPublicKey();</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">        for (X509Certificate mycert : list) {</span><a href="#l417"></a>
<span id="l418">            if (mycert.equals(cert)) {</span><a href="#l418"></a>
<span id="l419">                return cert;</span><a href="#l419"></a>
<span id="l420">            }</span><a href="#l420"></a>
<span id="l421">            if (!mycert.getIssuerX500Principal().equals(certIssuerName)) {</span><a href="#l421"></a>
<span id="l422">                continue;</span><a href="#l422"></a>
<span id="l423">            }</span><a href="#l423"></a>
<span id="l424">            if (!mycert.getPublicKey().equals(certPublicKey)) {</span><a href="#l424"></a>
<span id="l425">                continue;</span><a href="#l425"></a>
<span id="l426">            }</span><a href="#l426"></a>
<span id="l427"></span><a href="#l427"></a>
<span id="l428">            // All tests pass, this must be the one to use...</span><a href="#l428"></a>
<span id="l429">            return mycert;</span><a href="#l429"></a>
<span id="l430">        }</span><a href="#l430"></a>
<span id="l431">        return null;</span><a href="#l431"></a>
<span id="l432">    }</span><a href="#l432"></a>
<span id="l433"></span><a href="#l433"></a>
<span id="l434">}</span><a href="#l434"></a></pre>
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

