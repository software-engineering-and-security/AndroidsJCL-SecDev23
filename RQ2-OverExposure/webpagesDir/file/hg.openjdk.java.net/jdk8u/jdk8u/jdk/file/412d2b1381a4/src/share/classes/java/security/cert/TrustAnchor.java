<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/java/security/cert/TrustAnchor.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/java/security/cert/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/security/cert/TrustAnchor.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/java/security/cert/TrustAnchor.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/java/security/cert/TrustAnchor.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/java/security/cert/TrustAnchor.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/java/security/cert/TrustAnchor.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/java/security/cert/TrustAnchor.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/security/cert/TrustAnchor.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cd9f7780d964/src/share/classes/java/security/cert/TrustAnchor.java">cd9f7780d964</a> </td>
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
<span id="l2"> * Copyright (c) 2001, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.security.cert;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.security.PublicKey;</span><a href="#l29"></a>
<span id="l30"></span><a href="#l30"></a>
<span id="l31">import javax.security.auth.x500.X500Principal;</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">import sun.security.util.AnchorCertificates;</span><a href="#l33"></a>
<span id="l34">import sun.security.x509.NameConstraintsExtension;</span><a href="#l34"></a>
<span id="l35">import sun.security.x509.X500Name;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">/**</span><a href="#l37"></a>
<span id="l38"> * A trust anchor or most-trusted Certification Authority (CA).</span><a href="#l38"></a>
<span id="l39"> * &lt;p&gt;</span><a href="#l39"></a>
<span id="l40"> * This class represents a &quot;most-trusted CA&quot;, which is used as a trust anchor</span><a href="#l40"></a>
<span id="l41"> * for validating X.509 certification paths. A most-trusted CA includes the</span><a href="#l41"></a>
<span id="l42"> * public key of the CA, the CA's name, and any constraints upon the set of</span><a href="#l42"></a>
<span id="l43"> * paths which may be validated using this key. These parameters can be</span><a href="#l43"></a>
<span id="l44"> * specified in the form of a trusted {@code X509Certificate} or as</span><a href="#l44"></a>
<span id="l45"> * individual parameters.</span><a href="#l45"></a>
<span id="l46"> * &lt;p&gt;</span><a href="#l46"></a>
<span id="l47"> * &lt;b&gt;Concurrent Access&lt;/b&gt;</span><a href="#l47"></a>
<span id="l48"> * &lt;p&gt;All {@code TrustAnchor} objects must be immutable and</span><a href="#l48"></a>
<span id="l49"> * thread-safe. That is, multiple threads may concurrently invoke the</span><a href="#l49"></a>
<span id="l50"> * methods defined in this class on a single {@code TrustAnchor}</span><a href="#l50"></a>
<span id="l51"> * object (or more than one) with no ill effects. Requiring</span><a href="#l51"></a>
<span id="l52"> * {@code TrustAnchor} objects to be immutable and thread-safe</span><a href="#l52"></a>
<span id="l53"> * allows them to be passed around to various pieces of code without</span><a href="#l53"></a>
<span id="l54"> * worrying about coordinating access. This stipulation applies to all</span><a href="#l54"></a>
<span id="l55"> * public fields and methods of this class and any added or overridden</span><a href="#l55"></a>
<span id="l56"> * by subclasses.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> * @see PKIXParameters#PKIXParameters(Set)</span><a href="#l58"></a>
<span id="l59"> * @see PKIXBuilderParameters#PKIXBuilderParameters(Set, CertSelector)</span><a href="#l59"></a>
<span id="l60"> *</span><a href="#l60"></a>
<span id="l61"> * @since       1.4</span><a href="#l61"></a>
<span id="l62"> * @author      Sean Mullan</span><a href="#l62"></a>
<span id="l63"> */</span><a href="#l63"></a>
<span id="l64">public class TrustAnchor {</span><a href="#l64"></a>
<span id="l65"></span><a href="#l65"></a>
<span id="l66">    private final PublicKey pubKey;</span><a href="#l66"></a>
<span id="l67">    private final String caName;</span><a href="#l67"></a>
<span id="l68">    private final X500Principal caPrincipal;</span><a href="#l68"></a>
<span id="l69">    private final X509Certificate trustedCert;</span><a href="#l69"></a>
<span id="l70">    private byte[] ncBytes;</span><a href="#l70"></a>
<span id="l71">    private NameConstraintsExtension nc;</span><a href="#l71"></a>
<span id="l72">    private boolean jdkCA;</span><a href="#l72"></a>
<span id="l73">    private boolean hasJdkCABeenChecked;</span><a href="#l73"></a>
<span id="l74"></span><a href="#l74"></a>
<span id="l75">    static {</span><a href="#l75"></a>
<span id="l76">        CertPathHelperImpl.initialize();</span><a href="#l76"></a>
<span id="l77">    }</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    /**</span><a href="#l79"></a>
<span id="l80">     * Creates an instance of {@code TrustAnchor} with the specified</span><a href="#l80"></a>
<span id="l81">     * {@code X509Certificate} and optional name constraints, which</span><a href="#l81"></a>
<span id="l82">     * are intended to be used as additional constraints when validating</span><a href="#l82"></a>
<span id="l83">     * an X.509 certification path.</span><a href="#l83"></a>
<span id="l84">     * &lt;p&gt;</span><a href="#l84"></a>
<span id="l85">     * The name constraints are specified as a byte array. This byte array</span><a href="#l85"></a>
<span id="l86">     * should contain the DER encoded form of the name constraints, as they</span><a href="#l86"></a>
<span id="l87">     * would appear in the NameConstraints structure defined in</span><a href="#l87"></a>
<span id="l88">     * &lt;a href=&quot;http://tools.ietf.org/html/rfc5280&quot;&gt;RFC 5280&lt;/a&gt;</span><a href="#l88"></a>
<span id="l89">     * and X.509. The ASN.1 definition of this structure appears below.</span><a href="#l89"></a>
<span id="l90">     *</span><a href="#l90"></a>
<span id="l91">     * &lt;pre&gt;{@code</span><a href="#l91"></a>
<span id="l92">     *  NameConstraints ::= SEQUENCE {</span><a href="#l92"></a>
<span id="l93">     *       permittedSubtrees       [0]     GeneralSubtrees OPTIONAL,</span><a href="#l93"></a>
<span id="l94">     *       excludedSubtrees        [1]     GeneralSubtrees OPTIONAL }</span><a href="#l94"></a>
<span id="l95">     *</span><a href="#l95"></a>
<span id="l96">     *  GeneralSubtrees ::= SEQUENCE SIZE (1..MAX) OF GeneralSubtree</span><a href="#l96"></a>
<span id="l97">     *</span><a href="#l97"></a>
<span id="l98">     *  GeneralSubtree ::= SEQUENCE {</span><a href="#l98"></a>
<span id="l99">     *       base                    GeneralName,</span><a href="#l99"></a>
<span id="l100">     *       minimum         [0]     BaseDistance DEFAULT 0,</span><a href="#l100"></a>
<span id="l101">     *       maximum         [1]     BaseDistance OPTIONAL }</span><a href="#l101"></a>
<span id="l102">     *</span><a href="#l102"></a>
<span id="l103">     *  BaseDistance ::= INTEGER (0..MAX)</span><a href="#l103"></a>
<span id="l104">     *</span><a href="#l104"></a>
<span id="l105">     *  GeneralName ::= CHOICE {</span><a href="#l105"></a>
<span id="l106">     *       otherName                       [0]     OtherName,</span><a href="#l106"></a>
<span id="l107">     *       rfc822Name                      [1]     IA5String,</span><a href="#l107"></a>
<span id="l108">     *       dNSName                         [2]     IA5String,</span><a href="#l108"></a>
<span id="l109">     *       x400Address                     [3]     ORAddress,</span><a href="#l109"></a>
<span id="l110">     *       directoryName                   [4]     Name,</span><a href="#l110"></a>
<span id="l111">     *       ediPartyName                    [5]     EDIPartyName,</span><a href="#l111"></a>
<span id="l112">     *       uniformResourceIdentifier       [6]     IA5String,</span><a href="#l112"></a>
<span id="l113">     *       iPAddress                       [7]     OCTET STRING,</span><a href="#l113"></a>
<span id="l114">     *       registeredID                    [8]     OBJECT IDENTIFIER}</span><a href="#l114"></a>
<span id="l115">     * }&lt;/pre&gt;</span><a href="#l115"></a>
<span id="l116">     * &lt;p&gt;</span><a href="#l116"></a>
<span id="l117">     * Note that the name constraints byte array supplied is cloned to protect</span><a href="#l117"></a>
<span id="l118">     * against subsequent modifications.</span><a href="#l118"></a>
<span id="l119">     *</span><a href="#l119"></a>
<span id="l120">     * @param trustedCert a trusted {@code X509Certificate}</span><a href="#l120"></a>
<span id="l121">     * @param nameConstraints a byte array containing the ASN.1 DER encoding of</span><a href="#l121"></a>
<span id="l122">     * a NameConstraints extension to be used for checking name constraints.</span><a href="#l122"></a>
<span id="l123">     * Only the value of the extension is included, not the OID or criticality</span><a href="#l123"></a>
<span id="l124">     * flag. Specify {@code null} to omit the parameter.</span><a href="#l124"></a>
<span id="l125">     * @throws IllegalArgumentException if the name constraints cannot be</span><a href="#l125"></a>
<span id="l126">     * decoded</span><a href="#l126"></a>
<span id="l127">     * @throws NullPointerException if the specified</span><a href="#l127"></a>
<span id="l128">     * {@code X509Certificate} is {@code null}</span><a href="#l128"></a>
<span id="l129">     */</span><a href="#l129"></a>
<span id="l130">    public TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints)</span><a href="#l130"></a>
<span id="l131">    {</span><a href="#l131"></a>
<span id="l132">        if (trustedCert == null)</span><a href="#l132"></a>
<span id="l133">            throw new NullPointerException(&quot;the trustedCert parameter must &quot; +</span><a href="#l133"></a>
<span id="l134">                &quot;be non-null&quot;);</span><a href="#l134"></a>
<span id="l135">        this.trustedCert = trustedCert;</span><a href="#l135"></a>
<span id="l136">        this.pubKey = null;</span><a href="#l136"></a>
<span id="l137">        this.caName = null;</span><a href="#l137"></a>
<span id="l138">        this.caPrincipal = null;</span><a href="#l138"></a>
<span id="l139">        setNameConstraints(nameConstraints);</span><a href="#l139"></a>
<span id="l140">    }</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">    /**</span><a href="#l142"></a>
<span id="l143">     * Creates an instance of {@code TrustAnchor} where the</span><a href="#l143"></a>
<span id="l144">     * most-trusted CA is specified as an X500Principal and public key.</span><a href="#l144"></a>
<span id="l145">     * Name constraints are an optional parameter, and are intended to be used</span><a href="#l145"></a>
<span id="l146">     * as additional constraints when validating an X.509 certification path.</span><a href="#l146"></a>
<span id="l147">     * &lt;p&gt;</span><a href="#l147"></a>
<span id="l148">     * The name constraints are specified as a byte array. This byte array</span><a href="#l148"></a>
<span id="l149">     * contains the DER encoded form of the name constraints, as they</span><a href="#l149"></a>
<span id="l150">     * would appear in the NameConstraints structure defined in RFC 5280</span><a href="#l150"></a>
<span id="l151">     * and X.509. The ASN.1 notation for this structure is supplied in the</span><a href="#l151"></a>
<span id="l152">     * documentation for</span><a href="#l152"></a>
<span id="l153">     * {@link #TrustAnchor(X509Certificate, byte[])</span><a href="#l153"></a>
<span id="l154">     * TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) }.</span><a href="#l154"></a>
<span id="l155">     * &lt;p&gt;</span><a href="#l155"></a>
<span id="l156">     * Note that the name constraints byte array supplied here is cloned to</span><a href="#l156"></a>
<span id="l157">     * protect against subsequent modifications.</span><a href="#l157"></a>
<span id="l158">     *</span><a href="#l158"></a>
<span id="l159">     * @param caPrincipal the name of the most-trusted CA as X500Principal</span><a href="#l159"></a>
<span id="l160">     * @param pubKey the public key of the most-trusted CA</span><a href="#l160"></a>
<span id="l161">     * @param nameConstraints a byte array containing the ASN.1 DER encoding of</span><a href="#l161"></a>
<span id="l162">     * a NameConstraints extension to be used for checking name constraints.</span><a href="#l162"></a>
<span id="l163">     * Only the value of the extension is included, not the OID or criticality</span><a href="#l163"></a>
<span id="l164">     * flag. Specify {@code null} to omit the parameter.</span><a href="#l164"></a>
<span id="l165">     * @throws NullPointerException if the specified {@code caPrincipal} or</span><a href="#l165"></a>
<span id="l166">     * {@code pubKey} parameter is {@code null}</span><a href="#l166"></a>
<span id="l167">     * @since 1.5</span><a href="#l167"></a>
<span id="l168">     */</span><a href="#l168"></a>
<span id="l169">    public TrustAnchor(X500Principal caPrincipal, PublicKey pubKey,</span><a href="#l169"></a>
<span id="l170">            byte[] nameConstraints) {</span><a href="#l170"></a>
<span id="l171">        if ((caPrincipal == null) || (pubKey == null)) {</span><a href="#l171"></a>
<span id="l172">            throw new NullPointerException();</span><a href="#l172"></a>
<span id="l173">        }</span><a href="#l173"></a>
<span id="l174">        this.trustedCert = null;</span><a href="#l174"></a>
<span id="l175">        this.caPrincipal = caPrincipal;</span><a href="#l175"></a>
<span id="l176">        this.caName = caPrincipal.getName();</span><a href="#l176"></a>
<span id="l177">        this.pubKey = pubKey;</span><a href="#l177"></a>
<span id="l178">        setNameConstraints(nameConstraints);</span><a href="#l178"></a>
<span id="l179">    }</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">    /**</span><a href="#l181"></a>
<span id="l182">     * Creates an instance of {@code TrustAnchor} where the</span><a href="#l182"></a>
<span id="l183">     * most-trusted CA is specified as a distinguished name and public key.</span><a href="#l183"></a>
<span id="l184">     * Name constraints are an optional parameter, and are intended to be used</span><a href="#l184"></a>
<span id="l185">     * as additional constraints when validating an X.509 certification path.</span><a href="#l185"></a>
<span id="l186">     * &lt;p&gt;</span><a href="#l186"></a>
<span id="l187">     * The name constraints are specified as a byte array. This byte array</span><a href="#l187"></a>
<span id="l188">     * contains the DER encoded form of the name constraints, as they</span><a href="#l188"></a>
<span id="l189">     * would appear in the NameConstraints structure defined in RFC 5280</span><a href="#l189"></a>
<span id="l190">     * and X.509. The ASN.1 notation for this structure is supplied in the</span><a href="#l190"></a>
<span id="l191">     * documentation for</span><a href="#l191"></a>
<span id="l192">     * {@link #TrustAnchor(X509Certificate, byte[])</span><a href="#l192"></a>
<span id="l193">     * TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) }.</span><a href="#l193"></a>
<span id="l194">     * &lt;p&gt;</span><a href="#l194"></a>
<span id="l195">     * Note that the name constraints byte array supplied here is cloned to</span><a href="#l195"></a>
<span id="l196">     * protect against subsequent modifications.</span><a href="#l196"></a>
<span id="l197">     *</span><a href="#l197"></a>
<span id="l198">     * @param caName the X.500 distinguished name of the most-trusted CA in</span><a href="#l198"></a>
<span id="l199">     * &lt;a href=&quot;http://www.ietf.org/rfc/rfc2253.txt&quot;&gt;RFC 2253&lt;/a&gt;</span><a href="#l199"></a>
<span id="l200">     * {@code String} format</span><a href="#l200"></a>
<span id="l201">     * @param pubKey the public key of the most-trusted CA</span><a href="#l201"></a>
<span id="l202">     * @param nameConstraints a byte array containing the ASN.1 DER encoding of</span><a href="#l202"></a>
<span id="l203">     * a NameConstraints extension to be used for checking name constraints.</span><a href="#l203"></a>
<span id="l204">     * Only the value of the extension is included, not the OID or criticality</span><a href="#l204"></a>
<span id="l205">     * flag. Specify {@code null} to omit the parameter.</span><a href="#l205"></a>
<span id="l206">     * @throws IllegalArgumentException if the specified</span><a href="#l206"></a>
<span id="l207">     * {@code caName} parameter is empty {@code (caName.length() == 0)}</span><a href="#l207"></a>
<span id="l208">     * or incorrectly formatted or the name constraints cannot be decoded</span><a href="#l208"></a>
<span id="l209">     * @throws NullPointerException if the specified {@code caName} or</span><a href="#l209"></a>
<span id="l210">     * {@code pubKey} parameter is {@code null}</span><a href="#l210"></a>
<span id="l211">     */</span><a href="#l211"></a>
<span id="l212">    public TrustAnchor(String caName, PublicKey pubKey, byte[] nameConstraints)</span><a href="#l212"></a>
<span id="l213">    {</span><a href="#l213"></a>
<span id="l214">        if (pubKey == null)</span><a href="#l214"></a>
<span id="l215">            throw new NullPointerException(&quot;the pubKey parameter must be &quot; +</span><a href="#l215"></a>
<span id="l216">                &quot;non-null&quot;);</span><a href="#l216"></a>
<span id="l217">        if (caName == null)</span><a href="#l217"></a>
<span id="l218">            throw new NullPointerException(&quot;the caName parameter must be &quot; +</span><a href="#l218"></a>
<span id="l219">                &quot;non-null&quot;);</span><a href="#l219"></a>
<span id="l220">        if (caName.length() == 0)</span><a href="#l220"></a>
<span id="l221">            throw new IllegalArgumentException(&quot;the caName &quot; +</span><a href="#l221"></a>
<span id="l222">                &quot;parameter must be a non-empty String&quot;);</span><a href="#l222"></a>
<span id="l223">        // check if caName is formatted correctly</span><a href="#l223"></a>
<span id="l224">        this.caPrincipal = new X500Principal(caName);</span><a href="#l224"></a>
<span id="l225">        this.pubKey = pubKey;</span><a href="#l225"></a>
<span id="l226">        this.caName = caName;</span><a href="#l226"></a>
<span id="l227">        this.trustedCert = null;</span><a href="#l227"></a>
<span id="l228">        setNameConstraints(nameConstraints);</span><a href="#l228"></a>
<span id="l229">    }</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">    /**</span><a href="#l231"></a>
<span id="l232">     * Returns the most-trusted CA certificate.</span><a href="#l232"></a>
<span id="l233">     *</span><a href="#l233"></a>
<span id="l234">     * @return a trusted {@code X509Certificate} or {@code null}</span><a href="#l234"></a>
<span id="l235">     * if the trust anchor was not specified as a trusted certificate</span><a href="#l235"></a>
<span id="l236">     */</span><a href="#l236"></a>
<span id="l237">    public final X509Certificate getTrustedCert() {</span><a href="#l237"></a>
<span id="l238">        return this.trustedCert;</span><a href="#l238"></a>
<span id="l239">    }</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">    /**</span><a href="#l241"></a>
<span id="l242">     * Returns the name of the most-trusted CA as an X500Principal.</span><a href="#l242"></a>
<span id="l243">     *</span><a href="#l243"></a>
<span id="l244">     * @return the X.500 distinguished name of the most-trusted CA, or</span><a href="#l244"></a>
<span id="l245">     * {@code null} if the trust anchor was not specified as a trusted</span><a href="#l245"></a>
<span id="l246">     * public key and name or X500Principal pair</span><a href="#l246"></a>
<span id="l247">     * @since 1.5</span><a href="#l247"></a>
<span id="l248">     */</span><a href="#l248"></a>
<span id="l249">    public final X500Principal getCA() {</span><a href="#l249"></a>
<span id="l250">        return this.caPrincipal;</span><a href="#l250"></a>
<span id="l251">    }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">    /**</span><a href="#l253"></a>
<span id="l254">     * Returns the name of the most-trusted CA in RFC 2253 {@code String}</span><a href="#l254"></a>
<span id="l255">     * format.</span><a href="#l255"></a>
<span id="l256">     *</span><a href="#l256"></a>
<span id="l257">     * @return the X.500 distinguished name of the most-trusted CA, or</span><a href="#l257"></a>
<span id="l258">     * {@code null} if the trust anchor was not specified as a trusted</span><a href="#l258"></a>
<span id="l259">     * public key and name or X500Principal pair</span><a href="#l259"></a>
<span id="l260">     */</span><a href="#l260"></a>
<span id="l261">    public final String getCAName() {</span><a href="#l261"></a>
<span id="l262">        return this.caName;</span><a href="#l262"></a>
<span id="l263">    }</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">    /**</span><a href="#l265"></a>
<span id="l266">     * Returns the public key of the most-trusted CA.</span><a href="#l266"></a>
<span id="l267">     *</span><a href="#l267"></a>
<span id="l268">     * @return the public key of the most-trusted CA, or {@code null}</span><a href="#l268"></a>
<span id="l269">     * if the trust anchor was not specified as a trusted public key and name</span><a href="#l269"></a>
<span id="l270">     * or X500Principal pair</span><a href="#l270"></a>
<span id="l271">     */</span><a href="#l271"></a>
<span id="l272">    public final PublicKey getCAPublicKey() {</span><a href="#l272"></a>
<span id="l273">        return this.pubKey;</span><a href="#l273"></a>
<span id="l274">    }</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    /**</span><a href="#l276"></a>
<span id="l277">     * Decode the name constraints and clone them if not null.</span><a href="#l277"></a>
<span id="l278">     */</span><a href="#l278"></a>
<span id="l279">    private void setNameConstraints(byte[] bytes) {</span><a href="#l279"></a>
<span id="l280">        if (bytes == null) {</span><a href="#l280"></a>
<span id="l281">            ncBytes = null;</span><a href="#l281"></a>
<span id="l282">            nc = null;</span><a href="#l282"></a>
<span id="l283">        } else {</span><a href="#l283"></a>
<span id="l284">            ncBytes = bytes.clone();</span><a href="#l284"></a>
<span id="l285">            // validate DER encoding</span><a href="#l285"></a>
<span id="l286">            try {</span><a href="#l286"></a>
<span id="l287">                nc = new NameConstraintsExtension(Boolean.FALSE, bytes);</span><a href="#l287"></a>
<span id="l288">            } catch (IOException ioe) {</span><a href="#l288"></a>
<span id="l289">                IllegalArgumentException iae =</span><a href="#l289"></a>
<span id="l290">                    new IllegalArgumentException(ioe.getMessage());</span><a href="#l290"></a>
<span id="l291">                iae.initCause(ioe);</span><a href="#l291"></a>
<span id="l292">                throw iae;</span><a href="#l292"></a>
<span id="l293">            }</span><a href="#l293"></a>
<span id="l294">        }</span><a href="#l294"></a>
<span id="l295">    }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">    /**</span><a href="#l297"></a>
<span id="l298">     * Returns the name constraints parameter. The specified name constraints</span><a href="#l298"></a>
<span id="l299">     * are associated with this trust anchor and are intended to be used</span><a href="#l299"></a>
<span id="l300">     * as additional constraints when validating an X.509 certification path.</span><a href="#l300"></a>
<span id="l301">     * &lt;p&gt;</span><a href="#l301"></a>
<span id="l302">     * The name constraints are returned as a byte array. This byte array</span><a href="#l302"></a>
<span id="l303">     * contains the DER encoded form of the name constraints, as they</span><a href="#l303"></a>
<span id="l304">     * would appear in the NameConstraints structure defined in RFC 5280</span><a href="#l304"></a>
<span id="l305">     * and X.509. The ASN.1 notation for this structure is supplied in the</span><a href="#l305"></a>
<span id="l306">     * documentation for</span><a href="#l306"></a>
<span id="l307">     * {@link #TrustAnchor(X509Certificate, byte[])</span><a href="#l307"></a>
<span id="l308">     * TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) }.</span><a href="#l308"></a>
<span id="l309">     * &lt;p&gt;</span><a href="#l309"></a>
<span id="l310">     * Note that the byte array returned is cloned to protect against</span><a href="#l310"></a>
<span id="l311">     * subsequent modifications.</span><a href="#l311"></a>
<span id="l312">     *</span><a href="#l312"></a>
<span id="l313">     * @return a byte array containing the ASN.1 DER encoding of</span><a href="#l313"></a>
<span id="l314">     *         a NameConstraints extension used for checking name constraints,</span><a href="#l314"></a>
<span id="l315">     *         or {@code null} if not set.</span><a href="#l315"></a>
<span id="l316">     */</span><a href="#l316"></a>
<span id="l317">    public final byte [] getNameConstraints() {</span><a href="#l317"></a>
<span id="l318">        return ncBytes == null ? null : ncBytes.clone();</span><a href="#l318"></a>
<span id="l319">    }</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">    /**</span><a href="#l321"></a>
<span id="l322">     * Returns a formatted string describing the {@code TrustAnchor}.</span><a href="#l322"></a>
<span id="l323">     *</span><a href="#l323"></a>
<span id="l324">     * @return a formatted string describing the {@code TrustAnchor}</span><a href="#l324"></a>
<span id="l325">     */</span><a href="#l325"></a>
<span id="l326">    public String toString() {</span><a href="#l326"></a>
<span id="l327">        StringBuffer sb = new StringBuffer();</span><a href="#l327"></a>
<span id="l328">        sb.append(&quot;[\n&quot;);</span><a href="#l328"></a>
<span id="l329">        if (pubKey != null) {</span><a href="#l329"></a>
<span id="l330">            sb.append(&quot;  Trusted CA Public Key: &quot; + pubKey.toString() + &quot;\n&quot;);</span><a href="#l330"></a>
<span id="l331">            sb.append(&quot;  Trusted CA Issuer Name: &quot;</span><a href="#l331"></a>
<span id="l332">                + String.valueOf(caName) + &quot;\n&quot;);</span><a href="#l332"></a>
<span id="l333">        } else {</span><a href="#l333"></a>
<span id="l334">            sb.append(&quot;  Trusted CA cert: &quot; + trustedCert.toString() + &quot;\n&quot;);</span><a href="#l334"></a>
<span id="l335">        }</span><a href="#l335"></a>
<span id="l336">        if (nc != null)</span><a href="#l336"></a>
<span id="l337">            sb.append(&quot;  Name Constraints: &quot; + nc.toString() + &quot;\n&quot;);</span><a href="#l337"></a>
<span id="l338">        return sb.toString();</span><a href="#l338"></a>
<span id="l339">    }</span><a href="#l339"></a>
<span id="l340"></span><a href="#l340"></a>
<span id="l341">    /**</span><a href="#l341"></a>
<span id="l342">     * Returns true if anchor is a JDK CA (a root CA that is included by</span><a href="#l342"></a>
<span id="l343">     * default in the cacerts keystore).</span><a href="#l343"></a>
<span id="l344">     */</span><a href="#l344"></a>
<span id="l345">    synchronized boolean isJdkCA() {</span><a href="#l345"></a>
<span id="l346">        if (!hasJdkCABeenChecked) {</span><a href="#l346"></a>
<span id="l347">            if (trustedCert != null) {</span><a href="#l347"></a>
<span id="l348">                jdkCA = AnchorCertificates.contains(trustedCert);</span><a href="#l348"></a>
<span id="l349">            }</span><a href="#l349"></a>
<span id="l350">            hasJdkCABeenChecked = true;</span><a href="#l350"></a>
<span id="l351">        }</span><a href="#l351"></a>
<span id="l352">        return jdkCA;</span><a href="#l352"></a>
<span id="l353">    }</span><a href="#l353"></a>
<span id="l354">}</span><a href="#l354"></a></pre>
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

