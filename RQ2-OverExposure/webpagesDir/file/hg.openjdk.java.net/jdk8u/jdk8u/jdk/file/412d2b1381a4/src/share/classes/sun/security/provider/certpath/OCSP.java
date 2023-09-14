<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/OCSP.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/OCSP.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSP.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSP.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSP.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSP.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSP.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/OCSP.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cc7f20a9beb2/src/share/classes/sun/security/provider/certpath/OCSP.java">cc7f20a9beb2</a> </td>
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
<span id="l2"> * Copyright (c) 2009, 2017, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l25">package sun.security.provider.certpath;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.io.InputStream;</span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.OutputStream;</span><a href="#l29"></a>
<span id="l30">import java.net.URI;</span><a href="#l30"></a>
<span id="l31">import java.net.URL;</span><a href="#l31"></a>
<span id="l32">import java.net.HttpURLConnection;</span><a href="#l32"></a>
<span id="l33">import java.security.cert.CertificateException;</span><a href="#l33"></a>
<span id="l34">import java.security.cert.CertPathValidatorException;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.CertPathValidatorException.BasicReason;</span><a href="#l35"></a>
<span id="l36">import java.security.cert.CRLReason;</span><a href="#l36"></a>
<span id="l37">import java.security.cert.Extension;</span><a href="#l37"></a>
<span id="l38">import java.security.cert.TrustAnchor;</span><a href="#l38"></a>
<span id="l39">import java.security.cert.X509Certificate;</span><a href="#l39"></a>
<span id="l40">import java.util.Arrays;</span><a href="#l40"></a>
<span id="l41">import java.util.Collections;</span><a href="#l41"></a>
<span id="l42">import java.util.Date;</span><a href="#l42"></a>
<span id="l43">import java.util.List;</span><a href="#l43"></a>
<span id="l44">import java.util.Map;</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">import sun.security.action.GetIntegerAction;</span><a href="#l46"></a>
<span id="l47">import sun.security.util.Debug;</span><a href="#l47"></a>
<span id="l48">import sun.security.validator.Validator;</span><a href="#l48"></a>
<span id="l49">import sun.security.x509.AccessDescription;</span><a href="#l49"></a>
<span id="l50">import sun.security.x509.AuthorityInfoAccessExtension;</span><a href="#l50"></a>
<span id="l51">import sun.security.x509.GeneralName;</span><a href="#l51"></a>
<span id="l52">import sun.security.x509.GeneralNameInterface;</span><a href="#l52"></a>
<span id="l53">import sun.security.x509.PKIXExtensions;</span><a href="#l53"></a>
<span id="l54">import sun.security.x509.URIName;</span><a href="#l54"></a>
<span id="l55">import sun.security.x509.X509CertImpl;</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">/**</span><a href="#l57"></a>
<span id="l58"> * This is a class that checks the revocation status of a certificate(s) using</span><a href="#l58"></a>
<span id="l59"> * OCSP. It is not a PKIXCertPathChecker and therefore can be used outside of</span><a href="#l59"></a>
<span id="l60"> * the CertPathValidator framework. It is useful when you want to</span><a href="#l60"></a>
<span id="l61"> * just check the revocation status of a certificate, and you don't want to</span><a href="#l61"></a>
<span id="l62"> * incur the overhead of validating all of the certificates in the</span><a href="#l62"></a>
<span id="l63"> * associated certificate chain.</span><a href="#l63"></a>
<span id="l64"> *</span><a href="#l64"></a>
<span id="l65"> * @author Sean Mullan</span><a href="#l65"></a>
<span id="l66"> */</span><a href="#l66"></a>
<span id="l67">public final class OCSP {</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    /**</span><a href="#l73"></a>
<span id="l74">     * Integer value indicating the timeout length, in seconds, to be</span><a href="#l74"></a>
<span id="l75">     * used for the OCSP check. A timeout of zero is interpreted as</span><a href="#l75"></a>
<span id="l76">     * an infinite timeout.</span><a href="#l76"></a>
<span id="l77">     */</span><a href="#l77"></a>
<span id="l78">    private static final int CONNECT_TIMEOUT = initializeTimeout();</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    /**</span><a href="#l80"></a>
<span id="l81">     * Initialize the timeout length by getting the OCSP timeout</span><a href="#l81"></a>
<span id="l82">     * system property. If the property has not been set, or if its</span><a href="#l82"></a>
<span id="l83">     * value is negative, set the timeout length to the default.</span><a href="#l83"></a>
<span id="l84">     */</span><a href="#l84"></a>
<span id="l85">    private static int initializeTimeout() {</span><a href="#l85"></a>
<span id="l86">        Integer tmp = java.security.AccessController.doPrivileged(</span><a href="#l86"></a>
<span id="l87">                new GetIntegerAction(&quot;com.sun.security.ocsp.timeout&quot;));</span><a href="#l87"></a>
<span id="l88">        if (tmp == null || tmp &lt; 0) {</span><a href="#l88"></a>
<span id="l89">            return DEFAULT_CONNECT_TIMEOUT;</span><a href="#l89"></a>
<span id="l90">        }</span><a href="#l90"></a>
<span id="l91">        // Convert to milliseconds, as the system property will be</span><a href="#l91"></a>
<span id="l92">        // specified in seconds</span><a href="#l92"></a>
<span id="l93">        return tmp * 1000;</span><a href="#l93"></a>
<span id="l94">    }</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    private OCSP() {}</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    /**</span><a href="#l99"></a>
<span id="l100">     * Obtains the revocation status of a certificate using OCSP.</span><a href="#l100"></a>
<span id="l101">     *</span><a href="#l101"></a>
<span id="l102">     * @param cert the certificate to be checked</span><a href="#l102"></a>
<span id="l103">     * @param issuerCert the issuer certificate</span><a href="#l103"></a>
<span id="l104">     * @param responderURI the URI of the OCSP responder</span><a href="#l104"></a>
<span id="l105">     * @param responderCert the OCSP responder's certificate</span><a href="#l105"></a>
<span id="l106">     * @param date the time the validity of the OCSP responder's certificate</span><a href="#l106"></a>
<span id="l107">     *    should be checked against. If null, the current time is used.</span><a href="#l107"></a>
<span id="l108">     * @return the RevocationStatus</span><a href="#l108"></a>
<span id="l109">     * @throws IOException if there is an exception connecting to or</span><a href="#l109"></a>
<span id="l110">     *    communicating with the OCSP responder</span><a href="#l110"></a>
<span id="l111">     * @throws CertPathValidatorException if an exception occurs while</span><a href="#l111"></a>
<span id="l112">     *    encoding the OCSP Request or validating the OCSP Response</span><a href="#l112"></a>
<span id="l113">     */</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    // Called by com.sun.deploy.security.TrustDecider</span><a href="#l115"></a>
<span id="l116">    public static RevocationStatus check(X509Certificate cert,</span><a href="#l116"></a>
<span id="l117">                                         X509Certificate issuerCert,</span><a href="#l117"></a>
<span id="l118">                                         URI responderURI,</span><a href="#l118"></a>
<span id="l119">                                         X509Certificate responderCert,</span><a href="#l119"></a>
<span id="l120">                                         Date date)</span><a href="#l120"></a>
<span id="l121">        throws IOException, CertPathValidatorException</span><a href="#l121"></a>
<span id="l122">    {</span><a href="#l122"></a>
<span id="l123">        return check(cert, issuerCert, responderURI, responderCert, date,</span><a href="#l123"></a>
<span id="l124">                     Collections.&lt;Extension&gt;emptyList(),</span><a href="#l124"></a>
<span id="l125">                     Validator.VAR_PLUGIN_CODE_SIGNING);</span><a href="#l125"></a>
<span id="l126">    }</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128"></span><a href="#l128"></a>
<span id="l129">    public static RevocationStatus check(X509Certificate cert,</span><a href="#l129"></a>
<span id="l130">            X509Certificate issuerCert, URI responderURI,</span><a href="#l130"></a>
<span id="l131">            X509Certificate responderCert, Date date, List&lt;Extension&gt; extensions,</span><a href="#l131"></a>
<span id="l132">            String variant)</span><a href="#l132"></a>
<span id="l133">        throws IOException, CertPathValidatorException</span><a href="#l133"></a>
<span id="l134">    {</span><a href="#l134"></a>
<span id="l135">        return check(cert, responderURI, null, issuerCert, responderCert, date,</span><a href="#l135"></a>
<span id="l136">                extensions, variant);</span><a href="#l136"></a>
<span id="l137">    }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">    public static RevocationStatus check(X509Certificate cert,</span><a href="#l139"></a>
<span id="l140">            URI responderURI, TrustAnchor anchor, X509Certificate issuerCert,</span><a href="#l140"></a>
<span id="l141">            X509Certificate responderCert, Date date,</span><a href="#l141"></a>
<span id="l142">            List&lt;Extension&gt; extensions, String variant)</span><a href="#l142"></a>
<span id="l143">            throws IOException, CertPathValidatorException</span><a href="#l143"></a>
<span id="l144">    {</span><a href="#l144"></a>
<span id="l145">        CertId certId;</span><a href="#l145"></a>
<span id="l146">        try {</span><a href="#l146"></a>
<span id="l147">            X509CertImpl certImpl = X509CertImpl.toImpl(cert);</span><a href="#l147"></a>
<span id="l148">            certId = new CertId(issuerCert, certImpl.getSerialNumberObject());</span><a href="#l148"></a>
<span id="l149">        } catch (CertificateException | IOException e) {</span><a href="#l149"></a>
<span id="l150">            throw new CertPathValidatorException</span><a href="#l150"></a>
<span id="l151">                (&quot;Exception while encoding OCSPRequest&quot;, e);</span><a href="#l151"></a>
<span id="l152">        }</span><a href="#l152"></a>
<span id="l153">        OCSPResponse ocspResponse = check(Collections.singletonList(certId),</span><a href="#l153"></a>
<span id="l154">                responderURI, new OCSPResponse.IssuerInfo(anchor, issuerCert),</span><a href="#l154"></a>
<span id="l155">                responderCert, date, extensions, variant);</span><a href="#l155"></a>
<span id="l156">        return (RevocationStatus) ocspResponse.getSingleResponse(certId);</span><a href="#l156"></a>
<span id="l157">    }</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">    /**</span><a href="#l159"></a>
<span id="l160">     * Checks the revocation status of a list of certificates using OCSP.</span><a href="#l160"></a>
<span id="l161">     *</span><a href="#l161"></a>
<span id="l162">     * @param certIds the CertIds to be checked</span><a href="#l162"></a>
<span id="l163">     * @param responderURI the URI of the OCSP responder</span><a href="#l163"></a>
<span id="l164">     * @param issuerInfo the issuer's certificate and/or subject and public key</span><a href="#l164"></a>
<span id="l165">     * @param responderCert the OCSP responder's certificate</span><a href="#l165"></a>
<span id="l166">     * @param date the time the validity of the OCSP responder's certificate</span><a href="#l166"></a>
<span id="l167">     *    should be checked against. If null, the current time is used.</span><a href="#l167"></a>
<span id="l168">     * @param extensions zero or more OCSP extensions to be included in the</span><a href="#l168"></a>
<span id="l169">     *    request.  If no extensions are requested, an empty {@code List} must</span><a href="#l169"></a>
<span id="l170">     *    be used.  A {@code null} value is not allowed.</span><a href="#l170"></a>
<span id="l171">     * @return the OCSPResponse</span><a href="#l171"></a>
<span id="l172">     * @throws IOException if there is an exception connecting to or</span><a href="#l172"></a>
<span id="l173">     *    communicating with the OCSP responder</span><a href="#l173"></a>
<span id="l174">     * @throws CertPathValidatorException if an exception occurs while</span><a href="#l174"></a>
<span id="l175">     *    encoding the OCSP Request or validating the OCSP Response</span><a href="#l175"></a>
<span id="l176">     */</span><a href="#l176"></a>
<span id="l177">    static OCSPResponse check(List&lt;CertId&gt; certIds, URI responderURI,</span><a href="#l177"></a>
<span id="l178">                              OCSPResponse.IssuerInfo issuerInfo,</span><a href="#l178"></a>
<span id="l179">                              X509Certificate responderCert, Date date,</span><a href="#l179"></a>
<span id="l180">                              List&lt;Extension&gt; extensions, String variant)</span><a href="#l180"></a>
<span id="l181">        throws IOException, CertPathValidatorException</span><a href="#l181"></a>
<span id="l182">    {</span><a href="#l182"></a>
<span id="l183">        byte[] nonce = null;</span><a href="#l183"></a>
<span id="l184">        for (Extension ext : extensions) {</span><a href="#l184"></a>
<span id="l185">            if (ext.getId().equals(PKIXExtensions.OCSPNonce_Id.toString())) {</span><a href="#l185"></a>
<span id="l186">                nonce = ext.getValue();</span><a href="#l186"></a>
<span id="l187">            }</span><a href="#l187"></a>
<span id="l188">        }</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">        OCSPResponse ocspResponse = null;</span><a href="#l190"></a>
<span id="l191">        try {</span><a href="#l191"></a>
<span id="l192">            byte[] response = getOCSPBytes(certIds, responderURI, extensions);</span><a href="#l192"></a>
<span id="l193">            ocspResponse = new OCSPResponse(response);</span><a href="#l193"></a>
<span id="l194"></span><a href="#l194"></a>
<span id="l195">            // verify the response</span><a href="#l195"></a>
<span id="l196">            ocspResponse.verify(certIds, issuerInfo, responderCert, date,</span><a href="#l196"></a>
<span id="l197">                    nonce, variant);</span><a href="#l197"></a>
<span id="l198">        } catch (IOException ioe) {</span><a href="#l198"></a>
<span id="l199">            throw new CertPathValidatorException(</span><a href="#l199"></a>
<span id="l200">                &quot;Unable to determine revocation status due to network error&quot;,</span><a href="#l200"></a>
<span id="l201">                ioe, null, -1, BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l201"></a>
<span id="l202">        }</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">        return ocspResponse;</span><a href="#l204"></a>
<span id="l205">    }</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">    /**</span><a href="#l208"></a>
<span id="l209">     * Send an OCSP request, then read and return the OCSP response bytes.</span><a href="#l209"></a>
<span id="l210">     *</span><a href="#l210"></a>
<span id="l211">     * @param certIds the CertIds to be checked</span><a href="#l211"></a>
<span id="l212">     * @param responderURI the URI of the OCSP responder</span><a href="#l212"></a>
<span id="l213">     * @param extensions zero or more OCSP extensions to be included in the</span><a href="#l213"></a>
<span id="l214">     *    request.  If no extensions are requested, an empty {@code List} must</span><a href="#l214"></a>
<span id="l215">     *    be used.  A {@code null} value is not allowed.</span><a href="#l215"></a>
<span id="l216">     *</span><a href="#l216"></a>
<span id="l217">     * @return the OCSP response bytes</span><a href="#l217"></a>
<span id="l218">     *</span><a href="#l218"></a>
<span id="l219">     * @throws IOException if there is an exception connecting to or</span><a href="#l219"></a>
<span id="l220">     *    communicating with the OCSP responder</span><a href="#l220"></a>
<span id="l221">     */</span><a href="#l221"></a>
<span id="l222">    public static byte[] getOCSPBytes(List&lt;CertId&gt; certIds, URI responderURI,</span><a href="#l222"></a>
<span id="l223">            List&lt;Extension&gt; extensions) throws IOException {</span><a href="#l223"></a>
<span id="l224">        OCSPRequest request = new OCSPRequest(certIds, extensions);</span><a href="#l224"></a>
<span id="l225">        byte[] bytes = request.encodeBytes();</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">        InputStream in = null;</span><a href="#l227"></a>
<span id="l228">        OutputStream out = null;</span><a href="#l228"></a>
<span id="l229">        byte[] response = null;</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">        try {</span><a href="#l231"></a>
<span id="l232">            URL url = responderURI.toURL();</span><a href="#l232"></a>
<span id="l233">            if (debug != null) {</span><a href="#l233"></a>
<span id="l234">                debug.println(&quot;connecting to OCSP service at: &quot; + url);</span><a href="#l234"></a>
<span id="l235">            }</span><a href="#l235"></a>
<span id="l236">            HttpURLConnection con = (HttpURLConnection)url.openConnection();</span><a href="#l236"></a>
<span id="l237">            con.setConnectTimeout(CONNECT_TIMEOUT);</span><a href="#l237"></a>
<span id="l238">            con.setReadTimeout(CONNECT_TIMEOUT);</span><a href="#l238"></a>
<span id="l239">            con.setDoOutput(true);</span><a href="#l239"></a>
<span id="l240">            con.setDoInput(true);</span><a href="#l240"></a>
<span id="l241">            con.setRequestMethod(&quot;POST&quot;);</span><a href="#l241"></a>
<span id="l242">            con.setRequestProperty</span><a href="#l242"></a>
<span id="l243">                (&quot;Content-type&quot;, &quot;application/ocsp-request&quot;);</span><a href="#l243"></a>
<span id="l244">            con.setRequestProperty</span><a href="#l244"></a>
<span id="l245">                (&quot;Content-length&quot;, String.valueOf(bytes.length));</span><a href="#l245"></a>
<span id="l246">            out = con.getOutputStream();</span><a href="#l246"></a>
<span id="l247">            out.write(bytes);</span><a href="#l247"></a>
<span id="l248">            out.flush();</span><a href="#l248"></a>
<span id="l249">            // Check the response</span><a href="#l249"></a>
<span id="l250">            if (debug != null &amp;&amp;</span><a href="#l250"></a>
<span id="l251">                con.getResponseCode() != HttpURLConnection.HTTP_OK) {</span><a href="#l251"></a>
<span id="l252">                debug.println(&quot;Received HTTP error: &quot; + con.getResponseCode()</span><a href="#l252"></a>
<span id="l253">                    + &quot; - &quot; + con.getResponseMessage());</span><a href="#l253"></a>
<span id="l254">            }</span><a href="#l254"></a>
<span id="l255">            in = con.getInputStream();</span><a href="#l255"></a>
<span id="l256">            int contentLength = con.getContentLength();</span><a href="#l256"></a>
<span id="l257">            if (contentLength == -1) {</span><a href="#l257"></a>
<span id="l258">                contentLength = Integer.MAX_VALUE;</span><a href="#l258"></a>
<span id="l259">            }</span><a href="#l259"></a>
<span id="l260">            response = new byte[contentLength &gt; 2048 ? 2048 : contentLength];</span><a href="#l260"></a>
<span id="l261">            int total = 0;</span><a href="#l261"></a>
<span id="l262">            while (total &lt; contentLength) {</span><a href="#l262"></a>
<span id="l263">                int count = in.read(response, total, response.length - total);</span><a href="#l263"></a>
<span id="l264">                if (count &lt; 0)</span><a href="#l264"></a>
<span id="l265">                    break;</span><a href="#l265"></a>
<span id="l266"></span><a href="#l266"></a>
<span id="l267">                total += count;</span><a href="#l267"></a>
<span id="l268">                if (total &gt;= response.length &amp;&amp; total &lt; contentLength) {</span><a href="#l268"></a>
<span id="l269">                    response = Arrays.copyOf(response, total * 2);</span><a href="#l269"></a>
<span id="l270">                }</span><a href="#l270"></a>
<span id="l271">            }</span><a href="#l271"></a>
<span id="l272">            response = Arrays.copyOf(response, total);</span><a href="#l272"></a>
<span id="l273">        } finally {</span><a href="#l273"></a>
<span id="l274">            if (in != null) {</span><a href="#l274"></a>
<span id="l275">                try {</span><a href="#l275"></a>
<span id="l276">                    in.close();</span><a href="#l276"></a>
<span id="l277">                } catch (IOException ioe) {</span><a href="#l277"></a>
<span id="l278">                    throw ioe;</span><a href="#l278"></a>
<span id="l279">                }</span><a href="#l279"></a>
<span id="l280">            }</span><a href="#l280"></a>
<span id="l281">            if (out != null) {</span><a href="#l281"></a>
<span id="l282">                try {</span><a href="#l282"></a>
<span id="l283">                    out.close();</span><a href="#l283"></a>
<span id="l284">                } catch (IOException ioe) {</span><a href="#l284"></a>
<span id="l285">                    throw ioe;</span><a href="#l285"></a>
<span id="l286">                }</span><a href="#l286"></a>
<span id="l287">            }</span><a href="#l287"></a>
<span id="l288">        }</span><a href="#l288"></a>
<span id="l289">        return response;</span><a href="#l289"></a>
<span id="l290">    }</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">    /**</span><a href="#l292"></a>
<span id="l293">     * Returns the URI of the OCSP Responder as specified in the</span><a href="#l293"></a>
<span id="l294">     * certificate's Authority Information Access extension, or null if</span><a href="#l294"></a>
<span id="l295">     * not specified.</span><a href="#l295"></a>
<span id="l296">     *</span><a href="#l296"></a>
<span id="l297">     * @param cert the certificate</span><a href="#l297"></a>
<span id="l298">     * @return the URI of the OCSP Responder, or null if not specified</span><a href="#l298"></a>
<span id="l299">     */</span><a href="#l299"></a>
<span id="l300">    // Called by com.sun.deploy.security.TrustDecider</span><a href="#l300"></a>
<span id="l301">    public static URI getResponderURI(X509Certificate cert) {</span><a href="#l301"></a>
<span id="l302">        try {</span><a href="#l302"></a>
<span id="l303">            return getResponderURI(X509CertImpl.toImpl(cert));</span><a href="#l303"></a>
<span id="l304">        } catch (CertificateException ce) {</span><a href="#l304"></a>
<span id="l305">            // treat this case as if the cert had no extension</span><a href="#l305"></a>
<span id="l306">            return null;</span><a href="#l306"></a>
<span id="l307">        }</span><a href="#l307"></a>
<span id="l308">    }</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">    static URI getResponderURI(X509CertImpl certImpl) {</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">        // Examine the certificate's AuthorityInfoAccess extension</span><a href="#l312"></a>
<span id="l313">        AuthorityInfoAccessExtension aia =</span><a href="#l313"></a>
<span id="l314">            certImpl.getAuthorityInfoAccessExtension();</span><a href="#l314"></a>
<span id="l315">        if (aia == null) {</span><a href="#l315"></a>
<span id="l316">            return null;</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318"></span><a href="#l318"></a>
<span id="l319">        List&lt;AccessDescription&gt; descriptions = aia.getAccessDescriptions();</span><a href="#l319"></a>
<span id="l320">        for (AccessDescription description : descriptions) {</span><a href="#l320"></a>
<span id="l321">            if (description.getAccessMethod().equals(</span><a href="#l321"></a>
<span id="l322">                AccessDescription.Ad_OCSP_Id)) {</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">                GeneralName generalName = description.getAccessLocation();</span><a href="#l324"></a>
<span id="l325">                if (generalName.getType() == GeneralNameInterface.NAME_URI) {</span><a href="#l325"></a>
<span id="l326">                    URIName uri = (URIName) generalName.getName();</span><a href="#l326"></a>
<span id="l327">                    return uri.getURI();</span><a href="#l327"></a>
<span id="l328">                }</span><a href="#l328"></a>
<span id="l329">            }</span><a href="#l329"></a>
<span id="l330">        }</span><a href="#l330"></a>
<span id="l331">        return null;</span><a href="#l331"></a>
<span id="l332">    }</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334">    /**</span><a href="#l334"></a>
<span id="l335">     * The Revocation Status of a certificate.</span><a href="#l335"></a>
<span id="l336">     */</span><a href="#l336"></a>
<span id="l337">    public static interface RevocationStatus {</span><a href="#l337"></a>
<span id="l338">        public enum CertStatus { GOOD, REVOKED, UNKNOWN };</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">        /**</span><a href="#l340"></a>
<span id="l341">         * Returns the revocation status.</span><a href="#l341"></a>
<span id="l342">         */</span><a href="#l342"></a>
<span id="l343">        CertStatus getCertStatus();</span><a href="#l343"></a>
<span id="l344">        /**</span><a href="#l344"></a>
<span id="l345">         * Returns the time when the certificate was revoked, or null</span><a href="#l345"></a>
<span id="l346">         * if it has not been revoked.</span><a href="#l346"></a>
<span id="l347">         */</span><a href="#l347"></a>
<span id="l348">        Date getRevocationTime();</span><a href="#l348"></a>
<span id="l349">        /**</span><a href="#l349"></a>
<span id="l350">         * Returns the reason the certificate was revoked, or null if it</span><a href="#l350"></a>
<span id="l351">         * has not been revoked.</span><a href="#l351"></a>
<span id="l352">         */</span><a href="#l352"></a>
<span id="l353">        CRLReason getRevocationReason();</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">        /**</span><a href="#l355"></a>
<span id="l356">         * Returns a Map of additional extensions.</span><a href="#l356"></a>
<span id="l357">         */</span><a href="#l357"></a>
<span id="l358">        Map&lt;String, Extension&gt; getSingleExtensions();</span><a href="#l358"></a>
<span id="l359">    }</span><a href="#l359"></a>
<span id="l360">}</span><a href="#l360"></a></pre>
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

