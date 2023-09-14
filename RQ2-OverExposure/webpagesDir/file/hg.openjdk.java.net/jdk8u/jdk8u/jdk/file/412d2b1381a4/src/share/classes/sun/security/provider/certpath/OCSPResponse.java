<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/OCSPResponse.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/OCSPResponse.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3054a00b5333/src/share/classes/sun/security/provider/certpath/OCSPResponse.java">3054a00b5333</a> </td>
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
<span id="l2"> * Copyright (c) 2003, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l29">import java.security.*;</span><a href="#l29"></a>
<span id="l30">import java.security.cert.CertificateException;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.CertificateParsingException;</span><a href="#l31"></a>
<span id="l32">import java.security.cert.CertPathValidatorException;</span><a href="#l32"></a>
<span id="l33">import java.security.cert.CertPathValidatorException.BasicReason;</span><a href="#l33"></a>
<span id="l34">import java.security.cert.CRLReason;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.TrustAnchor;</span><a href="#l35"></a>
<span id="l36">import java.security.cert.X509Certificate;</span><a href="#l36"></a>
<span id="l37">import java.util.ArrayList;</span><a href="#l37"></a>
<span id="l38">import java.util.Arrays;</span><a href="#l38"></a>
<span id="l39">import java.util.Collections;</span><a href="#l39"></a>
<span id="l40">import java.util.Date;</span><a href="#l40"></a>
<span id="l41">import java.util.HashMap;</span><a href="#l41"></a>
<span id="l42">import java.util.List;</span><a href="#l42"></a>
<span id="l43">import java.util.Map;</span><a href="#l43"></a>
<span id="l44">import java.util.Set;</span><a href="#l44"></a>
<span id="l45">import javax.security.auth.x500.X500Principal;</span><a href="#l45"></a>
<span id="l46"></span><a href="#l46"></a>
<span id="l47">import sun.misc.HexDumpEncoder;</span><a href="#l47"></a>
<span id="l48">import sun.security.action.GetIntegerAction;</span><a href="#l48"></a>
<span id="l49">import sun.security.x509.*;</span><a href="#l49"></a>
<span id="l50">import sun.security.util.*;</span><a href="#l50"></a>
<span id="l51"></span><a href="#l51"></a>
<span id="l52">/**</span><a href="#l52"></a>
<span id="l53"> * This class is used to process an OCSP response.</span><a href="#l53"></a>
<span id="l54"> * The OCSP Response is defined</span><a href="#l54"></a>
<span id="l55"> * in RFC 2560 and the ASN.1 encoding is as follows:</span><a href="#l55"></a>
<span id="l56"> * &lt;pre&gt;</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> *  OCSPResponse ::= SEQUENCE {</span><a href="#l58"></a>
<span id="l59"> *      responseStatus         OCSPResponseStatus,</span><a href="#l59"></a>
<span id="l60"> *      responseBytes          [0] EXPLICIT ResponseBytes OPTIONAL }</span><a href="#l60"></a>
<span id="l61"> *</span><a href="#l61"></a>
<span id="l62"> *   OCSPResponseStatus ::= ENUMERATED {</span><a href="#l62"></a>
<span id="l63"> *       successful            (0),  --Response has valid confirmations</span><a href="#l63"></a>
<span id="l64"> *       malformedRequest      (1),  --Illegal confirmation request</span><a href="#l64"></a>
<span id="l65"> *       internalError         (2),  --Internal error in issuer</span><a href="#l65"></a>
<span id="l66"> *       tryLater              (3),  --Try again later</span><a href="#l66"></a>
<span id="l67"> *                                   --(4) is not used</span><a href="#l67"></a>
<span id="l68"> *       sigRequired           (5),  --Must sign the request</span><a href="#l68"></a>
<span id="l69"> *       unauthorized          (6)   --Request unauthorized</span><a href="#l69"></a>
<span id="l70"> *   }</span><a href="#l70"></a>
<span id="l71"> *</span><a href="#l71"></a>
<span id="l72"> *   ResponseBytes ::=       SEQUENCE {</span><a href="#l72"></a>
<span id="l73"> *       responseType   OBJECT IDENTIFIER,</span><a href="#l73"></a>
<span id="l74"> *       response       OCTET STRING }</span><a href="#l74"></a>
<span id="l75"> *</span><a href="#l75"></a>
<span id="l76"> *   BasicOCSPResponse       ::= SEQUENCE {</span><a href="#l76"></a>
<span id="l77"> *      tbsResponseData      ResponseData,</span><a href="#l77"></a>
<span id="l78"> *      signatureAlgorithm   AlgorithmIdentifier,</span><a href="#l78"></a>
<span id="l79"> *      signature            BIT STRING,</span><a href="#l79"></a>
<span id="l80"> *      certs                [0] EXPLICIT SEQUENCE OF Certificate OPTIONAL }</span><a href="#l80"></a>
<span id="l81"> *</span><a href="#l81"></a>
<span id="l82"> *   The value for signature SHALL be computed on the hash of the DER</span><a href="#l82"></a>
<span id="l83"> *   encoding ResponseData.</span><a href="#l83"></a>
<span id="l84"> *</span><a href="#l84"></a>
<span id="l85"> *   ResponseData ::= SEQUENCE {</span><a href="#l85"></a>
<span id="l86"> *      version              [0] EXPLICIT Version DEFAULT v1,</span><a href="#l86"></a>
<span id="l87"> *      responderID              ResponderID,</span><a href="#l87"></a>
<span id="l88"> *      producedAt               GeneralizedTime,</span><a href="#l88"></a>
<span id="l89"> *      responses                SEQUENCE OF SingleResponse,</span><a href="#l89"></a>
<span id="l90"> *      responseExtensions   [1] EXPLICIT Extensions OPTIONAL }</span><a href="#l90"></a>
<span id="l91"> *</span><a href="#l91"></a>
<span id="l92"> *   ResponderID ::= CHOICE {</span><a href="#l92"></a>
<span id="l93"> *      byName               [1] Name,</span><a href="#l93"></a>
<span id="l94"> *      byKey                [2] KeyHash }</span><a href="#l94"></a>
<span id="l95"> *</span><a href="#l95"></a>
<span id="l96"> *   KeyHash ::= OCTET STRING -- SHA-1 hash of responder's public key</span><a href="#l96"></a>
<span id="l97"> *   (excluding the tag and length fields)</span><a href="#l97"></a>
<span id="l98"> *</span><a href="#l98"></a>
<span id="l99"> *   SingleResponse ::= SEQUENCE {</span><a href="#l99"></a>
<span id="l100"> *      certID                       CertID,</span><a href="#l100"></a>
<span id="l101"> *      certStatus                   CertStatus,</span><a href="#l101"></a>
<span id="l102"> *      thisUpdate                   GeneralizedTime,</span><a href="#l102"></a>
<span id="l103"> *      nextUpdate         [0]       EXPLICIT GeneralizedTime OPTIONAL,</span><a href="#l103"></a>
<span id="l104"> *      singleExtensions   [1]       EXPLICIT Extensions OPTIONAL }</span><a href="#l104"></a>
<span id="l105"> *</span><a href="#l105"></a>
<span id="l106"> *   CertStatus ::= CHOICE {</span><a href="#l106"></a>
<span id="l107"> *       good        [0]     IMPLICIT NULL,</span><a href="#l107"></a>
<span id="l108"> *       revoked     [1]     IMPLICIT RevokedInfo,</span><a href="#l108"></a>
<span id="l109"> *       unknown     [2]     IMPLICIT UnknownInfo }</span><a href="#l109"></a>
<span id="l110"> *</span><a href="#l110"></a>
<span id="l111"> *   RevokedInfo ::= SEQUENCE {</span><a href="#l111"></a>
<span id="l112"> *       revocationTime              GeneralizedTime,</span><a href="#l112"></a>
<span id="l113"> *       revocationReason    [0]     EXPLICIT CRLReason OPTIONAL }</span><a href="#l113"></a>
<span id="l114"> *</span><a href="#l114"></a>
<span id="l115"> *   UnknownInfo ::= NULL -- this can be replaced with an enumeration</span><a href="#l115"></a>
<span id="l116"> *</span><a href="#l116"></a>
<span id="l117"> * &lt;/pre&gt;</span><a href="#l117"></a>
<span id="l118"> *</span><a href="#l118"></a>
<span id="l119"> * @author      Ram Marti</span><a href="#l119"></a>
<span id="l120"> */</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">public final class OCSPResponse {</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">    public enum ResponseStatus {</span><a href="#l124"></a>
<span id="l125">        SUCCESSFUL,            // Response has valid confirmations</span><a href="#l125"></a>
<span id="l126">        MALFORMED_REQUEST,     // Illegal request</span><a href="#l126"></a>
<span id="l127">        INTERNAL_ERROR,        // Internal error in responder</span><a href="#l127"></a>
<span id="l128">        TRY_LATER,             // Try again later</span><a href="#l128"></a>
<span id="l129">        UNUSED,                // is not used</span><a href="#l129"></a>
<span id="l130">        SIG_REQUIRED,          // Must sign the request</span><a href="#l130"></a>
<span id="l131">        UNAUTHORIZED           // Request unauthorized</span><a href="#l131"></a>
<span id="l132">    };</span><a href="#l132"></a>
<span id="l133">    private static final ResponseStatus[] rsvalues = ResponseStatus.values();</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l135"></a>
<span id="l136">    private static final boolean dump = debug != null &amp;&amp; Debug.isOn(&quot;ocsp&quot;);</span><a href="#l136"></a>
<span id="l137">    private static final ObjectIdentifier OCSP_BASIC_RESPONSE_OID =</span><a href="#l137"></a>
<span id="l138">        ObjectIdentifier.newInternal(new int[] { 1, 3, 6, 1, 5, 5, 7, 48, 1, 1});</span><a href="#l138"></a>
<span id="l139">    private static final int CERT_STATUS_GOOD = 0;</span><a href="#l139"></a>
<span id="l140">    private static final int CERT_STATUS_REVOKED = 1;</span><a href="#l140"></a>
<span id="l141">    private static final int CERT_STATUS_UNKNOWN = 2;</span><a href="#l141"></a>
<span id="l142"></span><a href="#l142"></a>
<span id="l143">    // ResponderID CHOICE tags</span><a href="#l143"></a>
<span id="l144">    private static final int NAME_TAG = 1;</span><a href="#l144"></a>
<span id="l145">    private static final int KEY_TAG = 2;</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">    // Object identifier for the OCSPSigning key purpose</span><a href="#l147"></a>
<span id="l148">    private static final String KP_OCSP_SIGNING_OID = &quot;1.3.6.1.5.5.7.3.9&quot;;</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">    // Default maximum clock skew in milliseconds (15 minutes)</span><a href="#l150"></a>
<span id="l151">    // allowed when checking validity of OCSP responses</span><a href="#l151"></a>
<span id="l152">    private static final int DEFAULT_MAX_CLOCK_SKEW = 900000;</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">    /**</span><a href="#l154"></a>
<span id="l155">     * Integer value indicating the maximum allowable clock skew,</span><a href="#l155"></a>
<span id="l156">     * in milliseconds, to be used for the OCSP check.</span><a href="#l156"></a>
<span id="l157">     */</span><a href="#l157"></a>
<span id="l158">    private static final int MAX_CLOCK_SKEW = initializeClockSkew();</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">    /**</span><a href="#l160"></a>
<span id="l161">     * Initialize the maximum allowable clock skew by getting the OCSP</span><a href="#l161"></a>
<span id="l162">     * clock skew system property. If the property has not been set, or if its</span><a href="#l162"></a>
<span id="l163">     * value is negative, set the skew to the default.</span><a href="#l163"></a>
<span id="l164">     */</span><a href="#l164"></a>
<span id="l165">    private static int initializeClockSkew() {</span><a href="#l165"></a>
<span id="l166">        Integer tmp = java.security.AccessController.doPrivileged(</span><a href="#l166"></a>
<span id="l167">                new GetIntegerAction(&quot;com.sun.security.ocsp.clockSkew&quot;));</span><a href="#l167"></a>
<span id="l168">        if (tmp == null || tmp &lt; 0) {</span><a href="#l168"></a>
<span id="l169">            return DEFAULT_MAX_CLOCK_SKEW;</span><a href="#l169"></a>
<span id="l170">        }</span><a href="#l170"></a>
<span id="l171">        // Convert to milliseconds, as the system property will be</span><a href="#l171"></a>
<span id="l172">        // specified in seconds</span><a href="#l172"></a>
<span id="l173">        return tmp * 1000;</span><a href="#l173"></a>
<span id="l174">    }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">    // an array of all of the CRLReasons (used in SingleResponse)</span><a href="#l176"></a>
<span id="l177">    private static final CRLReason[] values = CRLReason.values();</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    private final ResponseStatus responseStatus;</span><a href="#l179"></a>
<span id="l180">    private final Map&lt;CertId, SingleResponse&gt; singleResponseMap;</span><a href="#l180"></a>
<span id="l181">    private final AlgorithmId sigAlgId;</span><a href="#l181"></a>
<span id="l182">    private final byte[] signature;</span><a href="#l182"></a>
<span id="l183">    private final byte[] tbsResponseData;</span><a href="#l183"></a>
<span id="l184">    private final byte[] responseNonce;</span><a href="#l184"></a>
<span id="l185">    private List&lt;X509CertImpl&gt; certs;</span><a href="#l185"></a>
<span id="l186">    private X509CertImpl signerCert = null;</span><a href="#l186"></a>
<span id="l187">    private final ResponderId respId;</span><a href="#l187"></a>
<span id="l188">    private Date producedAtDate = null;</span><a href="#l188"></a>
<span id="l189">    private final Map&lt;String, java.security.cert.Extension&gt; responseExtensions;</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    /*</span><a href="#l191"></a>
<span id="l192">     * Create an OCSP response from its ASN.1 DER encoding.</span><a href="#l192"></a>
<span id="l193">     *</span><a href="#l193"></a>
<span id="l194">     * @param bytes The DER-encoded bytes for an OCSP response</span><a href="#l194"></a>
<span id="l195">     */</span><a href="#l195"></a>
<span id="l196">    public OCSPResponse(byte[] bytes) throws IOException {</span><a href="#l196"></a>
<span id="l197">        if (dump) {</span><a href="#l197"></a>
<span id="l198">            HexDumpEncoder hexEnc = new HexDumpEncoder();</span><a href="#l198"></a>
<span id="l199">            debug.println(&quot;OCSPResponse bytes...\n\n&quot; +</span><a href="#l199"></a>
<span id="l200">                hexEnc.encode(bytes) + &quot;\n&quot;);</span><a href="#l200"></a>
<span id="l201">        }</span><a href="#l201"></a>
<span id="l202">        DerValue der = new DerValue(bytes);</span><a href="#l202"></a>
<span id="l203">        if (der.tag != DerValue.tag_Sequence) {</span><a href="#l203"></a>
<span id="l204">            throw new IOException(&quot;Bad encoding in OCSP response: &quot; +</span><a href="#l204"></a>
<span id="l205">                &quot;expected ASN.1 SEQUENCE tag.&quot;);</span><a href="#l205"></a>
<span id="l206">        }</span><a href="#l206"></a>
<span id="l207">        DerInputStream derIn = der.getData();</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">        // responseStatus</span><a href="#l209"></a>
<span id="l210">        int status = derIn.getEnumerated();</span><a href="#l210"></a>
<span id="l211">        if (status &gt;= 0 &amp;&amp; status &lt; rsvalues.length) {</span><a href="#l211"></a>
<span id="l212">            responseStatus = rsvalues[status];</span><a href="#l212"></a>
<span id="l213">        } else {</span><a href="#l213"></a>
<span id="l214">            // unspecified responseStatus</span><a href="#l214"></a>
<span id="l215">            throw new IOException(&quot;Unknown OCSPResponse status: &quot; + status);</span><a href="#l215"></a>
<span id="l216">        }</span><a href="#l216"></a>
<span id="l217">        if (debug != null) {</span><a href="#l217"></a>
<span id="l218">            debug.println(&quot;OCSP response status: &quot; + responseStatus);</span><a href="#l218"></a>
<span id="l219">        }</span><a href="#l219"></a>
<span id="l220">        if (responseStatus != ResponseStatus.SUCCESSFUL) {</span><a href="#l220"></a>
<span id="l221">            // no need to continue, responseBytes are not set.</span><a href="#l221"></a>
<span id="l222">            singleResponseMap = Collections.emptyMap();</span><a href="#l222"></a>
<span id="l223">            certs = new ArrayList&lt;X509CertImpl&gt;();</span><a href="#l223"></a>
<span id="l224">            sigAlgId = null;</span><a href="#l224"></a>
<span id="l225">            signature = null;</span><a href="#l225"></a>
<span id="l226">            tbsResponseData = null;</span><a href="#l226"></a>
<span id="l227">            responseNonce = null;</span><a href="#l227"></a>
<span id="l228">            responseExtensions = Collections.emptyMap();</span><a href="#l228"></a>
<span id="l229">            respId = null;</span><a href="#l229"></a>
<span id="l230">            return;</span><a href="#l230"></a>
<span id="l231">        }</span><a href="#l231"></a>
<span id="l232"></span><a href="#l232"></a>
<span id="l233">        // responseBytes</span><a href="#l233"></a>
<span id="l234">        der = derIn.getDerValue();</span><a href="#l234"></a>
<span id="l235">        if (!der.isContextSpecific((byte)0)) {</span><a href="#l235"></a>
<span id="l236">            throw new IOException(&quot;Bad encoding in responseBytes element &quot; +</span><a href="#l236"></a>
<span id="l237">                &quot;of OCSP response: expected ASN.1 context specific tag 0.&quot;);</span><a href="#l237"></a>
<span id="l238">        }</span><a href="#l238"></a>
<span id="l239">        DerValue tmp = der.data.getDerValue();</span><a href="#l239"></a>
<span id="l240">        if (tmp.tag != DerValue.tag_Sequence) {</span><a href="#l240"></a>
<span id="l241">            throw new IOException(&quot;Bad encoding in responseBytes element &quot; +</span><a href="#l241"></a>
<span id="l242">                &quot;of OCSP response: expected ASN.1 SEQUENCE tag.&quot;);</span><a href="#l242"></a>
<span id="l243">        }</span><a href="#l243"></a>
<span id="l244"></span><a href="#l244"></a>
<span id="l245">        // responseType</span><a href="#l245"></a>
<span id="l246">        derIn = tmp.data;</span><a href="#l246"></a>
<span id="l247">        ObjectIdentifier responseType = derIn.getOID();</span><a href="#l247"></a>
<span id="l248">        if (responseType.equals((Object)OCSP_BASIC_RESPONSE_OID)) {</span><a href="#l248"></a>
<span id="l249">            if (debug != null) {</span><a href="#l249"></a>
<span id="l250">                debug.println(&quot;OCSP response type: basic&quot;);</span><a href="#l250"></a>
<span id="l251">            }</span><a href="#l251"></a>
<span id="l252">        } else {</span><a href="#l252"></a>
<span id="l253">            if (debug != null) {</span><a href="#l253"></a>
<span id="l254">                debug.println(&quot;OCSP response type: &quot; + responseType);</span><a href="#l254"></a>
<span id="l255">            }</span><a href="#l255"></a>
<span id="l256">            throw new IOException(&quot;Unsupported OCSP response type: &quot; +</span><a href="#l256"></a>
<span id="l257">                                  responseType);</span><a href="#l257"></a>
<span id="l258">        }</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">        // BasicOCSPResponse</span><a href="#l260"></a>
<span id="l261">        DerInputStream basicOCSPResponse =</span><a href="#l261"></a>
<span id="l262">            new DerInputStream(derIn.getOctetString());</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">        DerValue[] seqTmp = basicOCSPResponse.getSequence(3);</span><a href="#l264"></a>
<span id="l265">        if (seqTmp.length &lt; 3) {</span><a href="#l265"></a>
<span id="l266">            throw new IOException(&quot;Unexpected BasicOCSPResponse value&quot;);</span><a href="#l266"></a>
<span id="l267">        }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        DerValue responseData = seqTmp[0];</span><a href="#l269"></a>
<span id="l270"></span><a href="#l270"></a>
<span id="l271">        // Need the DER encoded ResponseData to verify the signature later</span><a href="#l271"></a>
<span id="l272">        tbsResponseData = seqTmp[0].toByteArray();</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">        // tbsResponseData</span><a href="#l274"></a>
<span id="l275">        if (responseData.tag != DerValue.tag_Sequence) {</span><a href="#l275"></a>
<span id="l276">            throw new IOException(&quot;Bad encoding in tbsResponseData &quot; +</span><a href="#l276"></a>
<span id="l277">                &quot;element of OCSP response: expected ASN.1 SEQUENCE tag.&quot;);</span><a href="#l277"></a>
<span id="l278">        }</span><a href="#l278"></a>
<span id="l279">        DerInputStream seqDerIn = responseData.data;</span><a href="#l279"></a>
<span id="l280">        DerValue seq = seqDerIn.getDerValue();</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">        // version</span><a href="#l282"></a>
<span id="l283">        if (seq.isContextSpecific((byte)0)) {</span><a href="#l283"></a>
<span id="l284">            // seq[0] is version</span><a href="#l284"></a>
<span id="l285">            if (seq.isConstructed() &amp;&amp; seq.isContextSpecific()) {</span><a href="#l285"></a>
<span id="l286">                //System.out.println (&quot;version is available&quot;);</span><a href="#l286"></a>
<span id="l287">                seq = seq.data.getDerValue();</span><a href="#l287"></a>
<span id="l288">                int version = seq.getInteger();</span><a href="#l288"></a>
<span id="l289">                if (seq.data.available() != 0) {</span><a href="#l289"></a>
<span id="l290">                    throw new IOException(&quot;Bad encoding in version &quot; +</span><a href="#l290"></a>
<span id="l291">                        &quot; element of OCSP response: bad format&quot;);</span><a href="#l291"></a>
<span id="l292">                }</span><a href="#l292"></a>
<span id="l293">                seq = seqDerIn.getDerValue();</span><a href="#l293"></a>
<span id="l294">            }</span><a href="#l294"></a>
<span id="l295">        }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">        // responderID</span><a href="#l297"></a>
<span id="l298">        respId = new ResponderId(seq.toByteArray());</span><a href="#l298"></a>
<span id="l299">        if (debug != null) {</span><a href="#l299"></a>
<span id="l300">            debug.println(&quot;Responder ID: &quot; + respId);</span><a href="#l300"></a>
<span id="l301">        }</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        // producedAt</span><a href="#l303"></a>
<span id="l304">        seq = seqDerIn.getDerValue();</span><a href="#l304"></a>
<span id="l305">        producedAtDate = seq.getGeneralizedTime();</span><a href="#l305"></a>
<span id="l306">        if (debug != null) {</span><a href="#l306"></a>
<span id="l307">            debug.println(&quot;OCSP response produced at: &quot; + producedAtDate);</span><a href="#l307"></a>
<span id="l308">        }</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">        // responses</span><a href="#l310"></a>
<span id="l311">        DerValue[] singleResponseDer = seqDerIn.getSequence(1);</span><a href="#l311"></a>
<span id="l312">        singleResponseMap = new HashMap&lt;&gt;(singleResponseDer.length);</span><a href="#l312"></a>
<span id="l313">        if (debug != null) {</span><a href="#l313"></a>
<span id="l314">            debug.println(&quot;OCSP number of SingleResponses: &quot;</span><a href="#l314"></a>
<span id="l315">                          + singleResponseDer.length);</span><a href="#l315"></a>
<span id="l316">        }</span><a href="#l316"></a>
<span id="l317">        for (DerValue srDer : singleResponseDer) {</span><a href="#l317"></a>
<span id="l318">            SingleResponse singleResponse = new SingleResponse(srDer);</span><a href="#l318"></a>
<span id="l319">            singleResponseMap.put(singleResponse.getCertId(), singleResponse);</span><a href="#l319"></a>
<span id="l320">        }</span><a href="#l320"></a>
<span id="l321"></span><a href="#l321"></a>
<span id="l322">        // responseExtensions</span><a href="#l322"></a>
<span id="l323">        Map&lt;String, java.security.cert.Extension&gt; tmpExtMap = new HashMap&lt;&gt;();</span><a href="#l323"></a>
<span id="l324">        if (seqDerIn.available() &gt; 0) {</span><a href="#l324"></a>
<span id="l325">            seq = seqDerIn.getDerValue();</span><a href="#l325"></a>
<span id="l326">            if (seq.isContextSpecific((byte)1)) {</span><a href="#l326"></a>
<span id="l327">                tmpExtMap = parseExtensions(seq);</span><a href="#l327"></a>
<span id="l328">            }</span><a href="#l328"></a>
<span id="l329">        }</span><a href="#l329"></a>
<span id="l330">        responseExtensions = tmpExtMap;</span><a href="#l330"></a>
<span id="l331"></span><a href="#l331"></a>
<span id="l332">        // Attach the nonce value if found in the extension map</span><a href="#l332"></a>
<span id="l333">        Extension nonceExt = (Extension)tmpExtMap.get(</span><a href="#l333"></a>
<span id="l334">                PKIXExtensions.OCSPNonce_Id.toString());</span><a href="#l334"></a>
<span id="l335">        responseNonce = (nonceExt != null) ?</span><a href="#l335"></a>
<span id="l336">                nonceExt.getExtensionValue() : null;</span><a href="#l336"></a>
<span id="l337">        if (debug != null &amp;&amp; responseNonce != null) {</span><a href="#l337"></a>
<span id="l338">            debug.println(&quot;Response nonce: &quot; + Arrays.toString(responseNonce));</span><a href="#l338"></a>
<span id="l339">        }</span><a href="#l339"></a>
<span id="l340"></span><a href="#l340"></a>
<span id="l341">        // signatureAlgorithmId</span><a href="#l341"></a>
<span id="l342">        sigAlgId = AlgorithmId.parse(seqTmp[1]);</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">        // signature</span><a href="#l344"></a>
<span id="l345">        signature = seqTmp[2].getBitString();</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">        // if seq[3] is available , then it is a sequence of certificates</span><a href="#l347"></a>
<span id="l348">        if (seqTmp.length &gt; 3) {</span><a href="#l348"></a>
<span id="l349">            // certs are available</span><a href="#l349"></a>
<span id="l350">            DerValue seqCert = seqTmp[3];</span><a href="#l350"></a>
<span id="l351">            if (!seqCert.isContextSpecific((byte)0)) {</span><a href="#l351"></a>
<span id="l352">                throw new IOException(&quot;Bad encoding in certs element of &quot; +</span><a href="#l352"></a>
<span id="l353">                    &quot;OCSP response: expected ASN.1 context specific tag 0.&quot;);</span><a href="#l353"></a>
<span id="l354">            }</span><a href="#l354"></a>
<span id="l355">            DerValue[] derCerts = seqCert.getData().getSequence(3);</span><a href="#l355"></a>
<span id="l356">            certs = new ArrayList&lt;X509CertImpl&gt;(derCerts.length);</span><a href="#l356"></a>
<span id="l357">            try {</span><a href="#l357"></a>
<span id="l358">                for (int i = 0; i &lt; derCerts.length; i++) {</span><a href="#l358"></a>
<span id="l359">                    X509CertImpl cert =</span><a href="#l359"></a>
<span id="l360">                        new X509CertImpl(derCerts[i].toByteArray());</span><a href="#l360"></a>
<span id="l361">                    certs.add(cert);</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">                    if (debug != null) {</span><a href="#l363"></a>
<span id="l364">                        debug.println(&quot;OCSP response cert #&quot; + (i + 1) + &quot;: &quot; +</span><a href="#l364"></a>
<span id="l365">                            cert.getSubjectX500Principal());</span><a href="#l365"></a>
<span id="l366">                    }</span><a href="#l366"></a>
<span id="l367">                }</span><a href="#l367"></a>
<span id="l368">            } catch (CertificateException ce) {</span><a href="#l368"></a>
<span id="l369">                throw new IOException(&quot;Bad encoding in X509 Certificate&quot;, ce);</span><a href="#l369"></a>
<span id="l370">            }</span><a href="#l370"></a>
<span id="l371">        } else {</span><a href="#l371"></a>
<span id="l372">            certs = new ArrayList&lt;X509CertImpl&gt;();</span><a href="#l372"></a>
<span id="l373">        }</span><a href="#l373"></a>
<span id="l374">    }</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">    void verify(List&lt;CertId&gt; certIds, IssuerInfo issuerInfo,</span><a href="#l376"></a>
<span id="l377">            X509Certificate responderCert, Date date, byte[] nonce,</span><a href="#l377"></a>
<span id="l378">            String variant)</span><a href="#l378"></a>
<span id="l379">        throws CertPathValidatorException</span><a href="#l379"></a>
<span id="l380">    {</span><a href="#l380"></a>
<span id="l381">        switch (responseStatus) {</span><a href="#l381"></a>
<span id="l382">            case SUCCESSFUL:</span><a href="#l382"></a>
<span id="l383">                break;</span><a href="#l383"></a>
<span id="l384">            case TRY_LATER:</span><a href="#l384"></a>
<span id="l385">            case INTERNAL_ERROR:</span><a href="#l385"></a>
<span id="l386">                throw new CertPathValidatorException(</span><a href="#l386"></a>
<span id="l387">                    &quot;OCSP response error: &quot; + responseStatus, null, null, -1,</span><a href="#l387"></a>
<span id="l388">                    BasicReason.UNDETERMINED_REVOCATION_STATUS);</span><a href="#l388"></a>
<span id="l389">            case UNAUTHORIZED:</span><a href="#l389"></a>
<span id="l390">            default:</span><a href="#l390"></a>
<span id="l391">                throw new CertPathValidatorException(&quot;OCSP response error: &quot; +</span><a href="#l391"></a>
<span id="l392">                                                     responseStatus);</span><a href="#l392"></a>
<span id="l393">        }</span><a href="#l393"></a>
<span id="l394"></span><a href="#l394"></a>
<span id="l395">        // Check that the response includes a response for all of the</span><a href="#l395"></a>
<span id="l396">        // certs that were supplied in the request</span><a href="#l396"></a>
<span id="l397">        for (CertId certId : certIds) {</span><a href="#l397"></a>
<span id="l398">            SingleResponse sr = getSingleResponse(certId);</span><a href="#l398"></a>
<span id="l399">            if (sr == null) {</span><a href="#l399"></a>
<span id="l400">                if (debug != null) {</span><a href="#l400"></a>
<span id="l401">                    debug.println(&quot;No response found for CertId: &quot; + certId);</span><a href="#l401"></a>
<span id="l402">                }</span><a href="#l402"></a>
<span id="l403">                throw new CertPathValidatorException(</span><a href="#l403"></a>
<span id="l404">                    &quot;OCSP response does not include a response for a &quot; +</span><a href="#l404"></a>
<span id="l405">                    &quot;certificate supplied in the OCSP request&quot;);</span><a href="#l405"></a>
<span id="l406">            }</span><a href="#l406"></a>
<span id="l407">            if (debug != null) {</span><a href="#l407"></a>
<span id="l408">                debug.println(&quot;Status of certificate (with serial number &quot; +</span><a href="#l408"></a>
<span id="l409">                    certId.getSerialNumber() + &quot;) is: &quot; + sr.getCertStatus());</span><a href="#l409"></a>
<span id="l410">            }</span><a href="#l410"></a>
<span id="l411">        }</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">        // Locate the signer cert</span><a href="#l413"></a>
<span id="l414">        if (signerCert == null) {</span><a href="#l414"></a>
<span id="l415">            // Add the Issuing CA cert and/or Trusted Responder cert to the list</span><a href="#l415"></a>
<span id="l416">            // of certs from the OCSP response</span><a href="#l416"></a>
<span id="l417">            try {</span><a href="#l417"></a>
<span id="l418">                if (issuerInfo.getCertificate() != null) {</span><a href="#l418"></a>
<span id="l419">                    certs.add(X509CertImpl.toImpl(issuerInfo.getCertificate()));</span><a href="#l419"></a>
<span id="l420">                }</span><a href="#l420"></a>
<span id="l421">                if (responderCert != null) {</span><a href="#l421"></a>
<span id="l422">                    certs.add(X509CertImpl.toImpl(responderCert));</span><a href="#l422"></a>
<span id="l423">                }</span><a href="#l423"></a>
<span id="l424">            } catch (CertificateException ce) {</span><a href="#l424"></a>
<span id="l425">                throw new CertPathValidatorException(</span><a href="#l425"></a>
<span id="l426">                    &quot;Invalid issuer or trusted responder certificate&quot;, ce);</span><a href="#l426"></a>
<span id="l427">            }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">            if (respId.getType() == ResponderId.Type.BY_NAME) {</span><a href="#l429"></a>
<span id="l430">                X500Principal rName = respId.getResponderName();</span><a href="#l430"></a>
<span id="l431">                for (X509CertImpl cert : certs) {</span><a href="#l431"></a>
<span id="l432">                    if (cert.getSubjectX500Principal().equals(rName)) {</span><a href="#l432"></a>
<span id="l433">                        signerCert = cert;</span><a href="#l433"></a>
<span id="l434">                        break;</span><a href="#l434"></a>
<span id="l435">                    }</span><a href="#l435"></a>
<span id="l436">                }</span><a href="#l436"></a>
<span id="l437">            } else if (respId.getType() == ResponderId.Type.BY_KEY) {</span><a href="#l437"></a>
<span id="l438">                KeyIdentifier ridKeyId = respId.getKeyIdentifier();</span><a href="#l438"></a>
<span id="l439">                for (X509CertImpl cert : certs) {</span><a href="#l439"></a>
<span id="l440">                    // Match responder's key identifier against the cert's SKID</span><a href="#l440"></a>
<span id="l441">                    // This will match if the SKID is encoded using the 160-bit</span><a href="#l441"></a>
<span id="l442">                    // SHA-1 hash method as defined in RFC 5280.</span><a href="#l442"></a>
<span id="l443">                    KeyIdentifier certKeyId = cert.getSubjectKeyId();</span><a href="#l443"></a>
<span id="l444">                    if (certKeyId != null &amp;&amp; ridKeyId.equals(certKeyId)) {</span><a href="#l444"></a>
<span id="l445">                        signerCert = cert;</span><a href="#l445"></a>
<span id="l446">                        break;</span><a href="#l446"></a>
<span id="l447">                    } else {</span><a href="#l447"></a>
<span id="l448">                        // The certificate does not have a SKID or may have</span><a href="#l448"></a>
<span id="l449">                        // been using a different algorithm (ex: see RFC 7093).</span><a href="#l449"></a>
<span id="l450">                        // Check if the responder's key identifier matches</span><a href="#l450"></a>
<span id="l451">                        // against a newly generated key identifier of the</span><a href="#l451"></a>
<span id="l452">                        // cert's public key using the 160-bit SHA-1 method.</span><a href="#l452"></a>
<span id="l453">                        try {</span><a href="#l453"></a>
<span id="l454">                            certKeyId = new KeyIdentifier(cert.getPublicKey());</span><a href="#l454"></a>
<span id="l455">                        } catch (IOException e) {</span><a href="#l455"></a>
<span id="l456">                            // ignore</span><a href="#l456"></a>
<span id="l457">                        }</span><a href="#l457"></a>
<span id="l458">                        if (ridKeyId.equals(certKeyId)) {</span><a href="#l458"></a>
<span id="l459">                            signerCert = cert;</span><a href="#l459"></a>
<span id="l460">                            break;</span><a href="#l460"></a>
<span id="l461">                        }</span><a href="#l461"></a>
<span id="l462">                    }</span><a href="#l462"></a>
<span id="l463">                }</span><a href="#l463"></a>
<span id="l464">            }</span><a href="#l464"></a>
<span id="l465">        }</span><a href="#l465"></a>
<span id="l466"></span><a href="#l466"></a>
<span id="l467">        // Check whether the signer cert returned by the responder is trusted</span><a href="#l467"></a>
<span id="l468">        boolean signedByTrustedResponder = false;</span><a href="#l468"></a>
<span id="l469">        if (signerCert != null) {</span><a href="#l469"></a>
<span id="l470">            // Check if the response is signed by the issuing CA</span><a href="#l470"></a>
<span id="l471">            if (signerCert.getSubjectX500Principal().equals(</span><a href="#l471"></a>
<span id="l472">                    issuerInfo.getName()) &amp;&amp;</span><a href="#l472"></a>
<span id="l473">                    signerCert.getPublicKey().equals(</span><a href="#l473"></a>
<span id="l474">                            issuerInfo.getPublicKey())) {</span><a href="#l474"></a>
<span id="l475">                if (debug != null) {</span><a href="#l475"></a>
<span id="l476">                    debug.println(&quot;OCSP response is signed by the target's &quot; +</span><a href="#l476"></a>
<span id="l477">                        &quot;Issuing CA&quot;);</span><a href="#l477"></a>
<span id="l478">                }</span><a href="#l478"></a>
<span id="l479">                // cert is trusted, now verify the signed response</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481">            // Check if the response is signed by a trusted responder</span><a href="#l481"></a>
<span id="l482">            } else if (signerCert.equals(responderCert)) {</span><a href="#l482"></a>
<span id="l483">                signedByTrustedResponder = true;</span><a href="#l483"></a>
<span id="l484">                if (debug != null) {</span><a href="#l484"></a>
<span id="l485">                    debug.println(&quot;OCSP response is signed by a Trusted &quot; +</span><a href="#l485"></a>
<span id="l486">                        &quot;Responder&quot;);</span><a href="#l486"></a>
<span id="l487">                }</span><a href="#l487"></a>
<span id="l488">                // cert is trusted, now verify the signed response</span><a href="#l488"></a>
<span id="l489"></span><a href="#l489"></a>
<span id="l490">            // Check if the response is signed by an authorized responder</span><a href="#l490"></a>
<span id="l491">            } else if (signerCert.getIssuerX500Principal().equals(</span><a href="#l491"></a>
<span id="l492">                    issuerInfo.getName())) {</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494">                // Check for the OCSPSigning key purpose</span><a href="#l494"></a>
<span id="l495">                try {</span><a href="#l495"></a>
<span id="l496">                    List&lt;String&gt; keyPurposes = signerCert.getExtendedKeyUsage();</span><a href="#l496"></a>
<span id="l497">                    if (keyPurposes == null ||</span><a href="#l497"></a>
<span id="l498">                        !keyPurposes.contains(KP_OCSP_SIGNING_OID)) {</span><a href="#l498"></a>
<span id="l499">                        throw new CertPathValidatorException(</span><a href="#l499"></a>
<span id="l500">                            &quot;Responder's certificate not valid for signing &quot; +</span><a href="#l500"></a>
<span id="l501">                            &quot;OCSP responses&quot;);</span><a href="#l501"></a>
<span id="l502">                    }</span><a href="#l502"></a>
<span id="l503">                } catch (CertificateParsingException cpe) {</span><a href="#l503"></a>
<span id="l504">                    // assume cert is not valid for signing</span><a href="#l504"></a>
<span id="l505">                    throw new CertPathValidatorException(</span><a href="#l505"></a>
<span id="l506">                        &quot;Responder's certificate not valid for signing &quot; +</span><a href="#l506"></a>
<span id="l507">                        &quot;OCSP responses&quot;, cpe);</span><a href="#l507"></a>
<span id="l508">                }</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">                // Check algorithm constraints specified in security property</span><a href="#l510"></a>
<span id="l511">                // &quot;jdk.certpath.disabledAlgorithms&quot;.</span><a href="#l511"></a>
<span id="l512">                AlgorithmChecker algChecker =</span><a href="#l512"></a>
<span id="l513">                        new AlgorithmChecker(issuerInfo.getAnchor(), date,</span><a href="#l513"></a>
<span id="l514">                                variant);</span><a href="#l514"></a>
<span id="l515">                algChecker.init(false);</span><a href="#l515"></a>
<span id="l516">                algChecker.check(signerCert, Collections.&lt;String&gt;emptySet());</span><a href="#l516"></a>
<span id="l517"></span><a href="#l517"></a>
<span id="l518">                // check the validity</span><a href="#l518"></a>
<span id="l519">                try {</span><a href="#l519"></a>
<span id="l520">                    if (date == null) {</span><a href="#l520"></a>
<span id="l521">                        signerCert.checkValidity();</span><a href="#l521"></a>
<span id="l522">                    } else {</span><a href="#l522"></a>
<span id="l523">                        signerCert.checkValidity(date);</span><a href="#l523"></a>
<span id="l524">                    }</span><a href="#l524"></a>
<span id="l525">                } catch (CertificateException e) {</span><a href="#l525"></a>
<span id="l526">                    throw new CertPathValidatorException(</span><a href="#l526"></a>
<span id="l527">                        &quot;Responder's certificate not within the &quot; +</span><a href="#l527"></a>
<span id="l528">                        &quot;validity period&quot;, e);</span><a href="#l528"></a>
<span id="l529">                }</span><a href="#l529"></a>
<span id="l530"></span><a href="#l530"></a>
<span id="l531">                // check for revocation</span><a href="#l531"></a>
<span id="l532">                //</span><a href="#l532"></a>
<span id="l533">                // A CA may specify that an OCSP client can trust a</span><a href="#l533"></a>
<span id="l534">                // responder for the lifetime of the responder's</span><a href="#l534"></a>
<span id="l535">                // certificate. The CA does so by including the</span><a href="#l535"></a>
<span id="l536">                // extension id-pkix-ocsp-nocheck.</span><a href="#l536"></a>
<span id="l537">                //</span><a href="#l537"></a>
<span id="l538">                Extension noCheck =</span><a href="#l538"></a>
<span id="l539">                    signerCert.getExtension(PKIXExtensions.OCSPNoCheck_Id);</span><a href="#l539"></a>
<span id="l540">                if (noCheck != null) {</span><a href="#l540"></a>
<span id="l541">                    if (debug != null) {</span><a href="#l541"></a>
<span id="l542">                        debug.println(&quot;Responder's certificate includes &quot; +</span><a href="#l542"></a>
<span id="l543">                            &quot;the extension id-pkix-ocsp-nocheck.&quot;);</span><a href="#l543"></a>
<span id="l544">                    }</span><a href="#l544"></a>
<span id="l545">                } else {</span><a href="#l545"></a>
<span id="l546">                    // we should do the revocation checking of the</span><a href="#l546"></a>
<span id="l547">                    // authorized responder in a future update.</span><a href="#l547"></a>
<span id="l548">                }</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">                // verify the signature</span><a href="#l550"></a>
<span id="l551">                try {</span><a href="#l551"></a>
<span id="l552">                    signerCert.verify(issuerInfo.getPublicKey());</span><a href="#l552"></a>
<span id="l553">                    if (debug != null) {</span><a href="#l553"></a>
<span id="l554">                        debug.println(&quot;OCSP response is signed by an &quot; +</span><a href="#l554"></a>
<span id="l555">                            &quot;Authorized Responder&quot;);</span><a href="#l555"></a>
<span id="l556">                    }</span><a href="#l556"></a>
<span id="l557">                    // cert is trusted, now verify the signed response</span><a href="#l557"></a>
<span id="l558"></span><a href="#l558"></a>
<span id="l559">                } catch (GeneralSecurityException e) {</span><a href="#l559"></a>
<span id="l560">                    signerCert = null;</span><a href="#l560"></a>
<span id="l561">                }</span><a href="#l561"></a>
<span id="l562">            } else {</span><a href="#l562"></a>
<span id="l563">                throw new CertPathValidatorException(</span><a href="#l563"></a>
<span id="l564">                    &quot;Responder's certificate is not authorized to sign &quot; +</span><a href="#l564"></a>
<span id="l565">                    &quot;OCSP responses&quot;);</span><a href="#l565"></a>
<span id="l566">            }</span><a href="#l566"></a>
<span id="l567">        }</span><a href="#l567"></a>
<span id="l568"></span><a href="#l568"></a>
<span id="l569">        // Confirm that the signed response was generated using the public</span><a href="#l569"></a>
<span id="l570">        // key from the trusted responder cert</span><a href="#l570"></a>
<span id="l571">        if (signerCert != null) {</span><a href="#l571"></a>
<span id="l572">            // Check algorithm constraints specified in security property</span><a href="#l572"></a>
<span id="l573">            // &quot;jdk.certpath.disabledAlgorithms&quot;.</span><a href="#l573"></a>
<span id="l574">            AlgorithmChecker.check(signerCert.getPublicKey(), sigAlgId, variant,</span><a href="#l574"></a>
<span id="l575">                    signedByTrustedResponder</span><a href="#l575"></a>
<span id="l576">                        ? new TrustAnchor(responderCert, null)</span><a href="#l576"></a>
<span id="l577">                        : issuerInfo.getAnchor());</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">            if (!verifySignature(signerCert)) {</span><a href="#l579"></a>
<span id="l580">                throw new CertPathValidatorException(</span><a href="#l580"></a>
<span id="l581">                    &quot;Error verifying OCSP Response's signature&quot;);</span><a href="#l581"></a>
<span id="l582">            }</span><a href="#l582"></a>
<span id="l583">        } else {</span><a href="#l583"></a>
<span id="l584">            // Need responder's cert in order to verify the signature</span><a href="#l584"></a>
<span id="l585">            throw new CertPathValidatorException(</span><a href="#l585"></a>
<span id="l586">                &quot;Unable to verify OCSP Response's signature&quot;);</span><a href="#l586"></a>
<span id="l587">        }</span><a href="#l587"></a>
<span id="l588"></span><a href="#l588"></a>
<span id="l589">        if (nonce != null) {</span><a href="#l589"></a>
<span id="l590">            if (responseNonce != null &amp;&amp; !Arrays.equals(nonce, responseNonce)) {</span><a href="#l590"></a>
<span id="l591">                throw new CertPathValidatorException(&quot;Nonces don't match&quot;);</span><a href="#l591"></a>
<span id="l592">            }</span><a href="#l592"></a>
<span id="l593">        }</span><a href="#l593"></a>
<span id="l594"></span><a href="#l594"></a>
<span id="l595">        // Check freshness of OCSPResponse</span><a href="#l595"></a>
<span id="l596">        long now = (date == null) ? System.currentTimeMillis() : date.getTime();</span><a href="#l596"></a>
<span id="l597">        Date nowPlusSkew = new Date(now + MAX_CLOCK_SKEW);</span><a href="#l597"></a>
<span id="l598">        Date nowMinusSkew = new Date(now - MAX_CLOCK_SKEW);</span><a href="#l598"></a>
<span id="l599">        for (SingleResponse sr : singleResponseMap.values()) {</span><a href="#l599"></a>
<span id="l600">            if (debug != null) {</span><a href="#l600"></a>
<span id="l601">                String until = &quot;&quot;;</span><a href="#l601"></a>
<span id="l602">                if (sr.nextUpdate != null) {</span><a href="#l602"></a>
<span id="l603">                    until = &quot; until &quot; + sr.nextUpdate;</span><a href="#l603"></a>
<span id="l604">                }</span><a href="#l604"></a>
<span id="l605">                debug.println(&quot;OCSP response validity interval is from &quot; +</span><a href="#l605"></a>
<span id="l606">                        sr.thisUpdate + until);</span><a href="#l606"></a>
<span id="l607">                debug.println(&quot;Checking validity of OCSP response on: &quot; +</span><a href="#l607"></a>
<span id="l608">                        new Date(now));</span><a href="#l608"></a>
<span id="l609">            }</span><a href="#l609"></a>
<span id="l610"></span><a href="#l610"></a>
<span id="l611">            // Check that the test date is within the validity interval:</span><a href="#l611"></a>
<span id="l612">            //   [ thisUpdate - MAX_CLOCK_SKEW,</span><a href="#l612"></a>
<span id="l613">            //     MAX(thisUpdate, nextUpdate) + MAX_CLOCK_SKEW ]</span><a href="#l613"></a>
<span id="l614">            if (nowPlusSkew.before(sr.thisUpdate) ||</span><a href="#l614"></a>
<span id="l615">                    nowMinusSkew.after(</span><a href="#l615"></a>
<span id="l616">                    sr.nextUpdate != null ? sr.nextUpdate : sr.thisUpdate))</span><a href="#l616"></a>
<span id="l617">            {</span><a href="#l617"></a>
<span id="l618">                throw new CertPathValidatorException(</span><a href="#l618"></a>
<span id="l619">                                      &quot;Response is unreliable: its validity &quot; +</span><a href="#l619"></a>
<span id="l620">                                      &quot;interval is out-of-date&quot;);</span><a href="#l620"></a>
<span id="l621">            }</span><a href="#l621"></a>
<span id="l622">        }</span><a href="#l622"></a>
<span id="l623">    }</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    /**</span><a href="#l625"></a>
<span id="l626">     * Returns the OCSP ResponseStatus.</span><a href="#l626"></a>
<span id="l627">     *</span><a href="#l627"></a>
<span id="l628">     * @return the {@code ResponseStatus} for this OCSP response</span><a href="#l628"></a>
<span id="l629">     */</span><a href="#l629"></a>
<span id="l630">    public ResponseStatus getResponseStatus() {</span><a href="#l630"></a>
<span id="l631">        return responseStatus;</span><a href="#l631"></a>
<span id="l632">    }</span><a href="#l632"></a>
<span id="l633"></span><a href="#l633"></a>
<span id="l634">    /*</span><a href="#l634"></a>
<span id="l635">     * Verify the signature of the OCSP response.</span><a href="#l635"></a>
<span id="l636">     */</span><a href="#l636"></a>
<span id="l637">    private boolean verifySignature(X509Certificate cert)</span><a href="#l637"></a>
<span id="l638">        throws CertPathValidatorException {</span><a href="#l638"></a>
<span id="l639"></span><a href="#l639"></a>
<span id="l640">        try {</span><a href="#l640"></a>
<span id="l641">            Signature respSignature = Signature.getInstance(sigAlgId.getName());</span><a href="#l641"></a>
<span id="l642">            respSignature.initVerify(cert.getPublicKey());</span><a href="#l642"></a>
<span id="l643">            respSignature.update(tbsResponseData);</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">            if (respSignature.verify(signature)) {</span><a href="#l645"></a>
<span id="l646">                if (debug != null) {</span><a href="#l646"></a>
<span id="l647">                    debug.println(&quot;Verified signature of OCSP Response&quot;);</span><a href="#l647"></a>
<span id="l648">                }</span><a href="#l648"></a>
<span id="l649">                return true;</span><a href="#l649"></a>
<span id="l650"></span><a href="#l650"></a>
<span id="l651">            } else {</span><a href="#l651"></a>
<span id="l652">                if (debug != null) {</span><a href="#l652"></a>
<span id="l653">                    debug.println(</span><a href="#l653"></a>
<span id="l654">                        &quot;Error verifying signature of OCSP Response&quot;);</span><a href="#l654"></a>
<span id="l655">                }</span><a href="#l655"></a>
<span id="l656">                return false;</span><a href="#l656"></a>
<span id="l657">            }</span><a href="#l657"></a>
<span id="l658">        } catch (InvalidKeyException | NoSuchAlgorithmException |</span><a href="#l658"></a>
<span id="l659">                 SignatureException e)</span><a href="#l659"></a>
<span id="l660">        {</span><a href="#l660"></a>
<span id="l661">            throw new CertPathValidatorException(e);</span><a href="#l661"></a>
<span id="l662">        }</span><a href="#l662"></a>
<span id="l663">    }</span><a href="#l663"></a>
<span id="l664"></span><a href="#l664"></a>
<span id="l665">    /**</span><a href="#l665"></a>
<span id="l666">     * Returns the SingleResponse of the specified CertId, or null if</span><a href="#l666"></a>
<span id="l667">     * there is no response for that CertId.</span><a href="#l667"></a>
<span id="l668">     *</span><a href="#l668"></a>
<span id="l669">     * @param certId the {@code CertId} for a {@code SingleResponse} to be</span><a href="#l669"></a>
<span id="l670">     * searched for in the OCSP response.</span><a href="#l670"></a>
<span id="l671">     *</span><a href="#l671"></a>
<span id="l672">     * @return the {@code SingleResponse} for the provided {@code CertId},</span><a href="#l672"></a>
<span id="l673">     * or {@code null} if it is not found.</span><a href="#l673"></a>
<span id="l674">     */</span><a href="#l674"></a>
<span id="l675">    public SingleResponse getSingleResponse(CertId certId) {</span><a href="#l675"></a>
<span id="l676">        return singleResponseMap.get(certId);</span><a href="#l676"></a>
<span id="l677">    }</span><a href="#l677"></a>
<span id="l678"></span><a href="#l678"></a>
<span id="l679">    /**</span><a href="#l679"></a>
<span id="l680">     * Return a set of all CertIds in this {@code OCSPResponse}</span><a href="#l680"></a>
<span id="l681">     *</span><a href="#l681"></a>
<span id="l682">     * @return an unmodifiable set containing every {@code CertId} in this</span><a href="#l682"></a>
<span id="l683">     *      response.</span><a href="#l683"></a>
<span id="l684">     */</span><a href="#l684"></a>
<span id="l685">    public Set&lt;CertId&gt; getCertIds() {</span><a href="#l685"></a>
<span id="l686">        return Collections.unmodifiableSet(singleResponseMap.keySet());</span><a href="#l686"></a>
<span id="l687">    }</span><a href="#l687"></a>
<span id="l688"></span><a href="#l688"></a>
<span id="l689">    /*</span><a href="#l689"></a>
<span id="l690">     * Returns the certificate for the authority that signed the OCSP response.</span><a href="#l690"></a>
<span id="l691">     */</span><a href="#l691"></a>
<span id="l692">    X509Certificate getSignerCertificate() {</span><a href="#l692"></a>
<span id="l693">        return signerCert; // set in verify()</span><a href="#l693"></a>
<span id="l694">    }</span><a href="#l694"></a>
<span id="l695"></span><a href="#l695"></a>
<span id="l696">    /**</span><a href="#l696"></a>
<span id="l697">     * Get the {@code ResponderId} from this {@code OCSPResponse}</span><a href="#l697"></a>
<span id="l698">     *</span><a href="#l698"></a>
<span id="l699">     * @return the {@code ResponderId} from this response or {@code null}</span><a href="#l699"></a>
<span id="l700">     *      if no responder ID is in the body of the response (e.g. a</span><a href="#l700"></a>
<span id="l701">     *      response with a status other than SUCCESS.</span><a href="#l701"></a>
<span id="l702">     */</span><a href="#l702"></a>
<span id="l703">    public ResponderId getResponderId() {</span><a href="#l703"></a>
<span id="l704">        return respId;</span><a href="#l704"></a>
<span id="l705">    }</span><a href="#l705"></a>
<span id="l706"></span><a href="#l706"></a>
<span id="l707">    /**</span><a href="#l707"></a>
<span id="l708">     * Provide a String representation of an OCSPResponse</span><a href="#l708"></a>
<span id="l709">     *</span><a href="#l709"></a>
<span id="l710">     * @return a human-readable representation of the OCSPResponse</span><a href="#l710"></a>
<span id="l711">     */</span><a href="#l711"></a>
<span id="l712">    @Override</span><a href="#l712"></a>
<span id="l713">    public String toString() {</span><a href="#l713"></a>
<span id="l714">        StringBuilder sb = new StringBuilder();</span><a href="#l714"></a>
<span id="l715">        sb.append(&quot;OCSP Response:\n&quot;);</span><a href="#l715"></a>
<span id="l716">        sb.append(&quot;Response Status: &quot;).append(responseStatus).append(&quot;\n&quot;);</span><a href="#l716"></a>
<span id="l717">        sb.append(&quot;Responder ID: &quot;).append(respId).append(&quot;\n&quot;);</span><a href="#l717"></a>
<span id="l718">        sb.append(&quot;Produced at: &quot;).append(producedAtDate).append(&quot;\n&quot;);</span><a href="#l718"></a>
<span id="l719">        int count = singleResponseMap.size();</span><a href="#l719"></a>
<span id="l720">        sb.append(count).append(count == 1 ?</span><a href="#l720"></a>
<span id="l721">                &quot; response:\n&quot; : &quot; responses:\n&quot;);</span><a href="#l721"></a>
<span id="l722">        for (SingleResponse sr : singleResponseMap.values()) {</span><a href="#l722"></a>
<span id="l723">            sb.append(sr).append(&quot;\n&quot;);</span><a href="#l723"></a>
<span id="l724">        }</span><a href="#l724"></a>
<span id="l725">        if (responseExtensions != null &amp;&amp; responseExtensions.size() &gt; 0) {</span><a href="#l725"></a>
<span id="l726">            count = responseExtensions.size();</span><a href="#l726"></a>
<span id="l727">            sb.append(count).append(count == 1 ?</span><a href="#l727"></a>
<span id="l728">                    &quot; extension:\n&quot; : &quot; extensions:\n&quot;);</span><a href="#l728"></a>
<span id="l729">            for (String extId : responseExtensions.keySet()) {</span><a href="#l729"></a>
<span id="l730">                sb.append(responseExtensions.get(extId)).append(&quot;\n&quot;);</span><a href="#l730"></a>
<span id="l731">            }</span><a href="#l731"></a>
<span id="l732">        }</span><a href="#l732"></a>
<span id="l733"></span><a href="#l733"></a>
<span id="l734">        return sb.toString();</span><a href="#l734"></a>
<span id="l735">    }</span><a href="#l735"></a>
<span id="l736"></span><a href="#l736"></a>
<span id="l737">    /**</span><a href="#l737"></a>
<span id="l738">     * Build a String-Extension map from DER encoded data.</span><a href="#l738"></a>
<span id="l739">     * @param derVal A {@code DerValue} object built from a SEQUENCE of</span><a href="#l739"></a>
<span id="l740">     *      extensions</span><a href="#l740"></a>
<span id="l741">     *</span><a href="#l741"></a>
<span id="l742">     * @return a {@code Map} using the OID in string form as the keys.  If no</span><a href="#l742"></a>
<span id="l743">     *      extensions are found or an empty SEQUENCE is passed in, then</span><a href="#l743"></a>
<span id="l744">     *      an empty {@code Map} will be returned.</span><a href="#l744"></a>
<span id="l745">     *</span><a href="#l745"></a>
<span id="l746">     * @throws IOException if any decoding errors occur.</span><a href="#l746"></a>
<span id="l747">     */</span><a href="#l747"></a>
<span id="l748">    private static Map&lt;String, java.security.cert.Extension&gt;</span><a href="#l748"></a>
<span id="l749">        parseExtensions(DerValue derVal) throws IOException {</span><a href="#l749"></a>
<span id="l750">        DerValue[] extDer = derVal.data.getSequence(3);</span><a href="#l750"></a>
<span id="l751">        Map&lt;String, java.security.cert.Extension&gt; extMap =</span><a href="#l751"></a>
<span id="l752">                new HashMap&lt;&gt;(extDer.length);</span><a href="#l752"></a>
<span id="l753"></span><a href="#l753"></a>
<span id="l754">        for (DerValue extDerVal : extDer) {</span><a href="#l754"></a>
<span id="l755">            Extension ext = new Extension(extDerVal);</span><a href="#l755"></a>
<span id="l756">            if (debug != null) {</span><a href="#l756"></a>
<span id="l757">                debug.println(&quot;Extension: &quot; + ext);</span><a href="#l757"></a>
<span id="l758">            }</span><a href="#l758"></a>
<span id="l759">            // We don't support any extensions yet. Therefore, if it</span><a href="#l759"></a>
<span id="l760">            // is critical we must throw an exception because we</span><a href="#l760"></a>
<span id="l761">            // don't know how to process it.</span><a href="#l761"></a>
<span id="l762">            if (ext.isCritical()) {</span><a href="#l762"></a>
<span id="l763">                throw new IOException(&quot;Unsupported OCSP critical extension: &quot; +</span><a href="#l763"></a>
<span id="l764">                        ext.getExtensionId());</span><a href="#l764"></a>
<span id="l765">            }</span><a href="#l765"></a>
<span id="l766">            extMap.put(ext.getId(), ext);</span><a href="#l766"></a>
<span id="l767">        }</span><a href="#l767"></a>
<span id="l768"></span><a href="#l768"></a>
<span id="l769">        return extMap;</span><a href="#l769"></a>
<span id="l770">    }</span><a href="#l770"></a>
<span id="l771"></span><a href="#l771"></a>
<span id="l772">    /*</span><a href="#l772"></a>
<span id="l773">     * A class representing a single OCSP response.</span><a href="#l773"></a>
<span id="l774">     */</span><a href="#l774"></a>
<span id="l775">    public static final class SingleResponse implements OCSP.RevocationStatus {</span><a href="#l775"></a>
<span id="l776">        private final CertId certId;</span><a href="#l776"></a>
<span id="l777">        private final CertStatus certStatus;</span><a href="#l777"></a>
<span id="l778">        private final Date thisUpdate;</span><a href="#l778"></a>
<span id="l779">        private final Date nextUpdate;</span><a href="#l779"></a>
<span id="l780">        private final Date revocationTime;</span><a href="#l780"></a>
<span id="l781">        private final CRLReason revocationReason;</span><a href="#l781"></a>
<span id="l782">        private final Map&lt;String, java.security.cert.Extension&gt; singleExtensions;</span><a href="#l782"></a>
<span id="l783"></span><a href="#l783"></a>
<span id="l784">        private SingleResponse(DerValue der) throws IOException {</span><a href="#l784"></a>
<span id="l785">            if (der.tag != DerValue.tag_Sequence) {</span><a href="#l785"></a>
<span id="l786">                throw new IOException(&quot;Bad ASN.1 encoding in SingleResponse&quot;);</span><a href="#l786"></a>
<span id="l787">            }</span><a href="#l787"></a>
<span id="l788">            DerInputStream tmp = der.data;</span><a href="#l788"></a>
<span id="l789"></span><a href="#l789"></a>
<span id="l790">            certId = new CertId(tmp.getDerValue().data);</span><a href="#l790"></a>
<span id="l791">            DerValue derVal = tmp.getDerValue();</span><a href="#l791"></a>
<span id="l792">            short tag = (byte)(derVal.tag &amp; 0x1f);</span><a href="#l792"></a>
<span id="l793">            if (tag ==  CERT_STATUS_REVOKED) {</span><a href="#l793"></a>
<span id="l794">                certStatus = CertStatus.REVOKED;</span><a href="#l794"></a>
<span id="l795">                revocationTime = derVal.data.getGeneralizedTime();</span><a href="#l795"></a>
<span id="l796">                if (derVal.data.available() != 0) {</span><a href="#l796"></a>
<span id="l797">                    DerValue dv = derVal.data.getDerValue();</span><a href="#l797"></a>
<span id="l798">                    tag = (byte)(dv.tag &amp; 0x1f);</span><a href="#l798"></a>
<span id="l799">                    if (tag == 0) {</span><a href="#l799"></a>
<span id="l800">                        int reason = dv.data.getEnumerated();</span><a href="#l800"></a>
<span id="l801">                        // if reason out-of-range just leave as UNSPECIFIED</span><a href="#l801"></a>
<span id="l802">                        if (reason &gt;= 0 &amp;&amp; reason &lt; values.length) {</span><a href="#l802"></a>
<span id="l803">                            revocationReason = values[reason];</span><a href="#l803"></a>
<span id="l804">                        } else {</span><a href="#l804"></a>
<span id="l805">                            revocationReason = CRLReason.UNSPECIFIED;</span><a href="#l805"></a>
<span id="l806">                        }</span><a href="#l806"></a>
<span id="l807">                    } else {</span><a href="#l807"></a>
<span id="l808">                        revocationReason = CRLReason.UNSPECIFIED;</span><a href="#l808"></a>
<span id="l809">                    }</span><a href="#l809"></a>
<span id="l810">                } else {</span><a href="#l810"></a>
<span id="l811">                    revocationReason = CRLReason.UNSPECIFIED;</span><a href="#l811"></a>
<span id="l812">                }</span><a href="#l812"></a>
<span id="l813">                // RevokedInfo</span><a href="#l813"></a>
<span id="l814">                if (debug != null) {</span><a href="#l814"></a>
<span id="l815">                    debug.println(&quot;Revocation time: &quot; + revocationTime);</span><a href="#l815"></a>
<span id="l816">                    debug.println(&quot;Revocation reason: &quot; + revocationReason);</span><a href="#l816"></a>
<span id="l817">                }</span><a href="#l817"></a>
<span id="l818">            } else {</span><a href="#l818"></a>
<span id="l819">                revocationTime = null;</span><a href="#l819"></a>
<span id="l820">                revocationReason = null;</span><a href="#l820"></a>
<span id="l821">                if (tag == CERT_STATUS_GOOD) {</span><a href="#l821"></a>
<span id="l822">                    certStatus = CertStatus.GOOD;</span><a href="#l822"></a>
<span id="l823">                } else if (tag == CERT_STATUS_UNKNOWN) {</span><a href="#l823"></a>
<span id="l824">                    certStatus = CertStatus.UNKNOWN;</span><a href="#l824"></a>
<span id="l825">                } else {</span><a href="#l825"></a>
<span id="l826">                    throw new IOException(&quot;Invalid certificate status&quot;);</span><a href="#l826"></a>
<span id="l827">                }</span><a href="#l827"></a>
<span id="l828">            }</span><a href="#l828"></a>
<span id="l829"></span><a href="#l829"></a>
<span id="l830">            thisUpdate = tmp.getGeneralizedTime();</span><a href="#l830"></a>
<span id="l831">            if (debug != null) {</span><a href="#l831"></a>
<span id="l832">                debug.println(&quot;thisUpdate: &quot; + thisUpdate);</span><a href="#l832"></a>
<span id="l833">            }</span><a href="#l833"></a>
<span id="l834"></span><a href="#l834"></a>
<span id="l835">            // Parse optional fields like nextUpdate and singleExtensions</span><a href="#l835"></a>
<span id="l836">            Date tmpNextUpdate = null;</span><a href="#l836"></a>
<span id="l837">            Map&lt;String, java.security.cert.Extension&gt; tmpMap = null;</span><a href="#l837"></a>
<span id="l838"></span><a href="#l838"></a>
<span id="l839">            // Check for the first optional item, it could be nextUpdate</span><a href="#l839"></a>
<span id="l840">            // [CONTEXT 0] or singleExtensions [CONTEXT 1]</span><a href="#l840"></a>
<span id="l841">            if (tmp.available() &gt; 0) {</span><a href="#l841"></a>
<span id="l842">                derVal = tmp.getDerValue();</span><a href="#l842"></a>
<span id="l843"></span><a href="#l843"></a>
<span id="l844">                // nextUpdate processing</span><a href="#l844"></a>
<span id="l845">                if (derVal.isContextSpecific((byte)0)) {</span><a href="#l845"></a>
<span id="l846">                    tmpNextUpdate = derVal.data.getGeneralizedTime();</span><a href="#l846"></a>
<span id="l847">                    if (debug != null) {</span><a href="#l847"></a>
<span id="l848">                        debug.println(&quot;nextUpdate: &quot; + tmpNextUpdate);</span><a href="#l848"></a>
<span id="l849">                    }</span><a href="#l849"></a>
<span id="l850"></span><a href="#l850"></a>
<span id="l851">                    // If more data exists in the singleResponse, it</span><a href="#l851"></a>
<span id="l852">                    // can only be singleExtensions.  Get this DER value</span><a href="#l852"></a>
<span id="l853">                    // for processing in the next block</span><a href="#l853"></a>
<span id="l854">                    derVal = tmp.available() &gt; 0 ? tmp.getDerValue() : null;</span><a href="#l854"></a>
<span id="l855">                }</span><a href="#l855"></a>
<span id="l856"></span><a href="#l856"></a>
<span id="l857">                // singleExtensions processing</span><a href="#l857"></a>
<span id="l858">                if (derVal != null) {</span><a href="#l858"></a>
<span id="l859">                    if (derVal.isContextSpecific((byte)1)) {</span><a href="#l859"></a>
<span id="l860">                        tmpMap = parseExtensions(derVal);</span><a href="#l860"></a>
<span id="l861"></span><a href="#l861"></a>
<span id="l862">                        // There should not be any other items in the</span><a href="#l862"></a>
<span id="l863">                        // singleResponse at this point.</span><a href="#l863"></a>
<span id="l864">                        if (tmp.available() &gt; 0) {</span><a href="#l864"></a>
<span id="l865">                            throw new IOException(tmp.available() +</span><a href="#l865"></a>
<span id="l866">                                &quot; bytes of additional data in singleResponse&quot;);</span><a href="#l866"></a>
<span id="l867">                        }</span><a href="#l867"></a>
<span id="l868">                    } else {</span><a href="#l868"></a>
<span id="l869">                        // Unknown item in the singleResponse</span><a href="#l869"></a>
<span id="l870">                        throw new IOException(&quot;Unsupported singleResponse &quot; +</span><a href="#l870"></a>
<span id="l871">                            &quot;item, tag = &quot; + String.format(&quot;%02X&quot;, derVal.tag));</span><a href="#l871"></a>
<span id="l872">                    }</span><a href="#l872"></a>
<span id="l873">                }</span><a href="#l873"></a>
<span id="l874">            }</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">            nextUpdate = tmpNextUpdate;</span><a href="#l876"></a>
<span id="l877">            singleExtensions = (tmpMap != null) ? tmpMap :</span><a href="#l877"></a>
<span id="l878">                    Collections.emptyMap();</span><a href="#l878"></a>
<span id="l879">            if (debug != null) {</span><a href="#l879"></a>
<span id="l880">                for (java.security.cert.Extension ext :</span><a href="#l880"></a>
<span id="l881">                        singleExtensions.values()) {</span><a href="#l881"></a>
<span id="l882">                   debug.println(&quot;singleExtension: &quot; + ext);</span><a href="#l882"></a>
<span id="l883">                }</span><a href="#l883"></a>
<span id="l884">            }</span><a href="#l884"></a>
<span id="l885">        }</span><a href="#l885"></a>
<span id="l886"></span><a href="#l886"></a>
<span id="l887">        /*</span><a href="#l887"></a>
<span id="l888">         * Return the certificate's revocation status code</span><a href="#l888"></a>
<span id="l889">         */</span><a href="#l889"></a>
<span id="l890">        @Override</span><a href="#l890"></a>
<span id="l891">        public CertStatus getCertStatus() {</span><a href="#l891"></a>
<span id="l892">            return certStatus;</span><a href="#l892"></a>
<span id="l893">        }</span><a href="#l893"></a>
<span id="l894"></span><a href="#l894"></a>
<span id="l895">        /**</span><a href="#l895"></a>
<span id="l896">         * Get the Cert ID that this SingleResponse is for.</span><a href="#l896"></a>
<span id="l897">         *</span><a href="#l897"></a>
<span id="l898">         * @return the {@code CertId} for this {@code SingleResponse}</span><a href="#l898"></a>
<span id="l899">         */</span><a href="#l899"></a>
<span id="l900">        public CertId getCertId() {</span><a href="#l900"></a>
<span id="l901">            return certId;</span><a href="#l901"></a>
<span id="l902">        }</span><a href="#l902"></a>
<span id="l903"></span><a href="#l903"></a>
<span id="l904">        /**</span><a href="#l904"></a>
<span id="l905">         * Get the {@code thisUpdate} field from this {@code SingleResponse}.</span><a href="#l905"></a>
<span id="l906">         *</span><a href="#l906"></a>
<span id="l907">         * @return a {@link Date} object containing the thisUpdate date</span><a href="#l907"></a>
<span id="l908">         */</span><a href="#l908"></a>
<span id="l909">        public Date getThisUpdate() {</span><a href="#l909"></a>
<span id="l910">            return (thisUpdate != null ? (Date) thisUpdate.clone() : null);</span><a href="#l910"></a>
<span id="l911">        }</span><a href="#l911"></a>
<span id="l912"></span><a href="#l912"></a>
<span id="l913">        /**</span><a href="#l913"></a>
<span id="l914">         * Get the {@code nextUpdate} field from this {@code SingleResponse}.</span><a href="#l914"></a>
<span id="l915">         *</span><a href="#l915"></a>
<span id="l916">         * @return a {@link Date} object containing the nexUpdate date or</span><a href="#l916"></a>
<span id="l917">         * {@code null} if a nextUpdate field is not present in the response.</span><a href="#l917"></a>
<span id="l918">         */</span><a href="#l918"></a>
<span id="l919">        public Date getNextUpdate() {</span><a href="#l919"></a>
<span id="l920">            return (nextUpdate != null ? (Date) nextUpdate.clone() : null);</span><a href="#l920"></a>
<span id="l921">        }</span><a href="#l921"></a>
<span id="l922"></span><a href="#l922"></a>
<span id="l923">        /**</span><a href="#l923"></a>
<span id="l924">         * Get the {@code revocationTime} field from this</span><a href="#l924"></a>
<span id="l925">         * {@code SingleResponse}.</span><a href="#l925"></a>
<span id="l926">         *</span><a href="#l926"></a>
<span id="l927">         * @return a {@link Date} object containing the revocationTime date or</span><a href="#l927"></a>
<span id="l928">         * {@code null} if the {@code SingleResponse} does not have a status</span><a href="#l928"></a>
<span id="l929">         * of {@code REVOKED}.</span><a href="#l929"></a>
<span id="l930">         */</span><a href="#l930"></a>
<span id="l931">        @Override</span><a href="#l931"></a>
<span id="l932">        public Date getRevocationTime() {</span><a href="#l932"></a>
<span id="l933">            return (revocationTime != null ? (Date) revocationTime.clone() :</span><a href="#l933"></a>
<span id="l934">                    null);</span><a href="#l934"></a>
<span id="l935">        }</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">        /**</span><a href="#l937"></a>
<span id="l938">         * Get the {@code revocationReason} field for the</span><a href="#l938"></a>
<span id="l939">         * {@code SingleResponse}.</span><a href="#l939"></a>
<span id="l940">         *</span><a href="#l940"></a>
<span id="l941">         * @return a {@link CRLReason} containing the revocation reason, or</span><a href="#l941"></a>
<span id="l942">         * {@code null} if a revocation reason was not provided or the</span><a href="#l942"></a>
<span id="l943">         * response status is not {@code REVOKED}.</span><a href="#l943"></a>
<span id="l944">         */</span><a href="#l944"></a>
<span id="l945">        @Override</span><a href="#l945"></a>
<span id="l946">        public CRLReason getRevocationReason() {</span><a href="#l946"></a>
<span id="l947">            return revocationReason;</span><a href="#l947"></a>
<span id="l948">        }</span><a href="#l948"></a>
<span id="l949"></span><a href="#l949"></a>
<span id="l950">        /**</span><a href="#l950"></a>
<span id="l951">         * Get the {@code singleExtensions} for this {@code SingleResponse}.</span><a href="#l951"></a>
<span id="l952">         *</span><a href="#l952"></a>
<span id="l953">         * @return a {@link Map} of {@link Extension} objects, keyed by</span><a href="#l953"></a>
<span id="l954">         * their OID value in string form.</span><a href="#l954"></a>
<span id="l955">         */</span><a href="#l955"></a>
<span id="l956">        @Override</span><a href="#l956"></a>
<span id="l957">        public Map&lt;String, java.security.cert.Extension&gt; getSingleExtensions() {</span><a href="#l957"></a>
<span id="l958">            return Collections.unmodifiableMap(singleExtensions);</span><a href="#l958"></a>
<span id="l959">        }</span><a href="#l959"></a>
<span id="l960"></span><a href="#l960"></a>
<span id="l961">        /**</span><a href="#l961"></a>
<span id="l962">         * Construct a string representation of a single OCSP response.</span><a href="#l962"></a>
<span id="l963">         */</span><a href="#l963"></a>
<span id="l964">        @Override public String toString() {</span><a href="#l964"></a>
<span id="l965">            StringBuilder sb = new StringBuilder();</span><a href="#l965"></a>
<span id="l966">            sb.append(&quot;SingleResponse:\n&quot;);</span><a href="#l966"></a>
<span id="l967">            sb.append(certId);</span><a href="#l967"></a>
<span id="l968">            sb.append(&quot;\nCertStatus: &quot;).append(certStatus).append(&quot;\n&quot;);</span><a href="#l968"></a>
<span id="l969">            if (certStatus == CertStatus.REVOKED) {</span><a href="#l969"></a>
<span id="l970">                sb.append(&quot;revocationTime is &quot;);</span><a href="#l970"></a>
<span id="l971">                sb.append(revocationTime).append(&quot;\n&quot;);</span><a href="#l971"></a>
<span id="l972">                sb.append(&quot;revocationReason is &quot;);</span><a href="#l972"></a>
<span id="l973">                sb.append(revocationReason).append(&quot;\n&quot;);</span><a href="#l973"></a>
<span id="l974">            }</span><a href="#l974"></a>
<span id="l975">            sb.append(&quot;thisUpdate is &quot;).append(thisUpdate).append(&quot;\n&quot;);</span><a href="#l975"></a>
<span id="l976">            if (nextUpdate != null) {</span><a href="#l976"></a>
<span id="l977">                sb.append(&quot;nextUpdate is &quot;).append(nextUpdate).append(&quot;\n&quot;);</span><a href="#l977"></a>
<span id="l978">            }</span><a href="#l978"></a>
<span id="l979">            for (java.security.cert.Extension ext : singleExtensions.values()) {</span><a href="#l979"></a>
<span id="l980">                sb.append(&quot;singleExtension: &quot;);</span><a href="#l980"></a>
<span id="l981">                sb.append(ext.toString()).append(&quot;\n&quot;);</span><a href="#l981"></a>
<span id="l982">            }</span><a href="#l982"></a>
<span id="l983">            return sb.toString();</span><a href="#l983"></a>
<span id="l984">        }</span><a href="#l984"></a>
<span id="l985">    }</span><a href="#l985"></a>
<span id="l986"></span><a href="#l986"></a>
<span id="l987">    /**</span><a href="#l987"></a>
<span id="l988">     * Helper class that allows consumers to pass in issuer information.  This</span><a href="#l988"></a>
<span id="l989">     * will always consist of the issuer's name and public key, but may also</span><a href="#l989"></a>
<span id="l990">     * contain a certificate if the originating data is in that form.  The</span><a href="#l990"></a>
<span id="l991">     * trust anchor for the certificate chain will be included for certpath</span><a href="#l991"></a>
<span id="l992">     * disabled algorithm checking.</span><a href="#l992"></a>
<span id="l993">     */</span><a href="#l993"></a>
<span id="l994">    static final class IssuerInfo {</span><a href="#l994"></a>
<span id="l995">        private final TrustAnchor anchor;</span><a href="#l995"></a>
<span id="l996">        private final X509Certificate certificate;</span><a href="#l996"></a>
<span id="l997">        private final X500Principal name;</span><a href="#l997"></a>
<span id="l998">        private final PublicKey pubKey;</span><a href="#l998"></a>
<span id="l999"></span><a href="#l999"></a>
<span id="l1000">        IssuerInfo(TrustAnchor anchor) {</span><a href="#l1000"></a>
<span id="l1001">            this(anchor, (anchor != null) ? anchor.getTrustedCert() : null);</span><a href="#l1001"></a>
<span id="l1002">        }</span><a href="#l1002"></a>
<span id="l1003"></span><a href="#l1003"></a>
<span id="l1004">        IssuerInfo(X509Certificate issuerCert) {</span><a href="#l1004"></a>
<span id="l1005">            this(null, issuerCert);</span><a href="#l1005"></a>
<span id="l1006">        }</span><a href="#l1006"></a>
<span id="l1007"></span><a href="#l1007"></a>
<span id="l1008">        IssuerInfo(TrustAnchor anchor, X509Certificate issuerCert) {</span><a href="#l1008"></a>
<span id="l1009">            if (anchor == null &amp;&amp; issuerCert == null) {</span><a href="#l1009"></a>
<span id="l1010">                throw new NullPointerException(&quot;TrustAnchor and issuerCert &quot; +</span><a href="#l1010"></a>
<span id="l1011">                        &quot;cannot be null&quot;);</span><a href="#l1011"></a>
<span id="l1012">            }</span><a href="#l1012"></a>
<span id="l1013">            this.anchor = anchor;</span><a href="#l1013"></a>
<span id="l1014">            if (issuerCert != null) {</span><a href="#l1014"></a>
<span id="l1015">                name = issuerCert.getSubjectX500Principal();</span><a href="#l1015"></a>
<span id="l1016">                pubKey = issuerCert.getPublicKey();</span><a href="#l1016"></a>
<span id="l1017">                certificate = issuerCert;</span><a href="#l1017"></a>
<span id="l1018">            } else {</span><a href="#l1018"></a>
<span id="l1019">                name = anchor.getCA();</span><a href="#l1019"></a>
<span id="l1020">                pubKey = anchor.getCAPublicKey();</span><a href="#l1020"></a>
<span id="l1021">                certificate = anchor.getTrustedCert();</span><a href="#l1021"></a>
<span id="l1022">            }</span><a href="#l1022"></a>
<span id="l1023">        }</span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">        /**</span><a href="#l1025"></a>
<span id="l1026">         * Get the certificate in this IssuerInfo if present.</span><a href="#l1026"></a>
<span id="l1027">         *</span><a href="#l1027"></a>
<span id="l1028">         * @return the {@code X509Certificate} used to create this IssuerInfo</span><a href="#l1028"></a>
<span id="l1029">         * object, or {@code null} if a certificate was not used in its</span><a href="#l1029"></a>
<span id="l1030">         * creation.</span><a href="#l1030"></a>
<span id="l1031">         */</span><a href="#l1031"></a>
<span id="l1032">        X509Certificate getCertificate() {</span><a href="#l1032"></a>
<span id="l1033">            return certificate;</span><a href="#l1033"></a>
<span id="l1034">        }</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">        /**</span><a href="#l1036"></a>
<span id="l1037">         * Get the name of this issuer.</span><a href="#l1037"></a>
<span id="l1038">         *</span><a href="#l1038"></a>
<span id="l1039">         * @return an {@code X500Principal} corresponding to this issuer's</span><a href="#l1039"></a>
<span id="l1040">         * name.  If derived from an issuer's {@code X509Certificate} this</span><a href="#l1040"></a>
<span id="l1041">         * would be equivalent to the certificate subject name.</span><a href="#l1041"></a>
<span id="l1042">         */</span><a href="#l1042"></a>
<span id="l1043">        X500Principal getName() {</span><a href="#l1043"></a>
<span id="l1044">            return name;</span><a href="#l1044"></a>
<span id="l1045">        }</span><a href="#l1045"></a>
<span id="l1046"></span><a href="#l1046"></a>
<span id="l1047">        /**</span><a href="#l1047"></a>
<span id="l1048">         * Get the public key for this issuer.</span><a href="#l1048"></a>
<span id="l1049">         *</span><a href="#l1049"></a>
<span id="l1050">         * @return a {@code PublicKey} for this issuer.</span><a href="#l1050"></a>
<span id="l1051">         */</span><a href="#l1051"></a>
<span id="l1052">        PublicKey getPublicKey() {</span><a href="#l1052"></a>
<span id="l1053">            return pubKey;</span><a href="#l1053"></a>
<span id="l1054">        }</span><a href="#l1054"></a>
<span id="l1055"></span><a href="#l1055"></a>
<span id="l1056">        /**</span><a href="#l1056"></a>
<span id="l1057">         * Get the TrustAnchor for the certificate chain.</span><a href="#l1057"></a>
<span id="l1058">         *</span><a href="#l1058"></a>
<span id="l1059">         * @return a {@code TrustAnchor}.</span><a href="#l1059"></a>
<span id="l1060">         */</span><a href="#l1060"></a>
<span id="l1061">        TrustAnchor getAnchor() {</span><a href="#l1061"></a>
<span id="l1062">            return anchor;</span><a href="#l1062"></a>
<span id="l1063">        }</span><a href="#l1063"></a>
<span id="l1064"></span><a href="#l1064"></a>
<span id="l1065">        /**</span><a href="#l1065"></a>
<span id="l1066">         * Create a string representation of this IssuerInfo.</span><a href="#l1066"></a>
<span id="l1067">         *</span><a href="#l1067"></a>
<span id="l1068">         * @return a {@code String} form of this IssuerInfo object.</span><a href="#l1068"></a>
<span id="l1069">         */</span><a href="#l1069"></a>
<span id="l1070">        @Override</span><a href="#l1070"></a>
<span id="l1071">        public String toString() {</span><a href="#l1071"></a>
<span id="l1072">            StringBuilder sb = new StringBuilder();</span><a href="#l1072"></a>
<span id="l1073">            sb.append(&quot;Issuer Info:\n&quot;);</span><a href="#l1073"></a>
<span id="l1074">            sb.append(&quot;Name: &quot;).append(name.toString()).append(&quot;\n&quot;);</span><a href="#l1074"></a>
<span id="l1075">            sb.append(&quot;Public Key:\n&quot;).append(pubKey.toString()).append(&quot;\n&quot;);</span><a href="#l1075"></a>
<span id="l1076">            return sb.toString();</span><a href="#l1076"></a>
<span id="l1077">        }</span><a href="#l1077"></a>
<span id="l1078">    }</span><a href="#l1078"></a>
<span id="l1079">}</span><a href="#l1079"></a></pre>
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

