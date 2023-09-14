<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/x509/AlgorithmId.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/x509/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/x509/AlgorithmId.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/x509/AlgorithmId.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/x509/AlgorithmId.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/x509/AlgorithmId.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/x509/AlgorithmId.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/x509/AlgorithmId.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/x509/AlgorithmId.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/fdcd822abdcf/src/share/classes/sun/security/x509/AlgorithmId.java">fdcd822abdcf</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/7a7302919f69/src/share/classes/sun/security/x509/AlgorithmId.java">7a7302919f69</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1996, 2021, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.x509;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.*;</span><a href="#l28"></a>
<span id="l29">import java.security.spec.AlgorithmParameterSpec;</span><a href="#l29"></a>
<span id="l30">import java.security.spec.InvalidParameterSpecException;</span><a href="#l30"></a>
<span id="l31">import java.security.spec.MGF1ParameterSpec;</span><a href="#l31"></a>
<span id="l32">import java.security.spec.PSSParameterSpec;</span><a href="#l32"></a>
<span id="l33">import java.util.*;</span><a href="#l33"></a>
<span id="l34">import java.security.*;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">import sun.security.rsa.PSSParameters;</span><a href="#l36"></a>
<span id="l37">import sun.security.util.*;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">/**</span><a href="#l40"></a>
<span id="l41"> * This class identifies algorithms, such as cryptographic transforms, each</span><a href="#l41"></a>
<span id="l42"> * of which may be associated with parameters.  Instances of this base class</span><a href="#l42"></a>
<span id="l43"> * are used when this runtime environment has no special knowledge of the</span><a href="#l43"></a>
<span id="l44"> * algorithm type, and may also be used in other cases.  Equivalence is</span><a href="#l44"></a>
<span id="l45"> * defined according to OID and (where relevant) parameters.</span><a href="#l45"></a>
<span id="l46"> *</span><a href="#l46"></a>
<span id="l47"> * &lt;P&gt;Subclasses may be used, for example when when the algorithm ID has</span><a href="#l47"></a>
<span id="l48"> * associated parameters which some code (e.g. code using public keys) needs</span><a href="#l48"></a>
<span id="l49"> * to have parsed.  Two examples of such algorithms are Diffie-Hellman key</span><a href="#l49"></a>
<span id="l50"> * exchange, and the Digital Signature Standard Algorithm (DSS/DSA).</span><a href="#l50"></a>
<span id="l51"> *</span><a href="#l51"></a>
<span id="l52"> * &lt;P&gt;The OID constants defined in this class correspond to some widely</span><a href="#l52"></a>
<span id="l53"> * used algorithms, for which conventional string names have been defined.</span><a href="#l53"></a>
<span id="l54"> * This class is not a general repository for OIDs, or for such string names.</span><a href="#l54"></a>
<span id="l55"> * Note that the mappings between algorithm IDs and algorithm names is</span><a href="#l55"></a>
<span id="l56"> * not one-to-one.</span><a href="#l56"></a>
<span id="l57"> *</span><a href="#l57"></a>
<span id="l58"> *</span><a href="#l58"></a>
<span id="l59"> * @author David Brownell</span><a href="#l59"></a>
<span id="l60"> * @author Amit Kapoor</span><a href="#l60"></a>
<span id="l61"> * @author Hemma Prafullchandra</span><a href="#l61"></a>
<span id="l62"> */</span><a href="#l62"></a>
<span id="l63">public class AlgorithmId implements Serializable, DerEncoder {</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    /** use serialVersionUID from JDK 1.1. for interoperability */</span><a href="#l65"></a>
<span id="l66">    private static final long serialVersionUID = 7205873507486557157L;</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    /**</span><a href="#l68"></a>
<span id="l69">     * The object identitifer being used for this algorithm.</span><a href="#l69"></a>
<span id="l70">     */</span><a href="#l70"></a>
<span id="l71">    private ObjectIdentifier algid;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    // The (parsed) parameters</span><a href="#l73"></a>
<span id="l74">    private AlgorithmParameters algParams;</span><a href="#l74"></a>
<span id="l75">    private boolean constructedFromDer = true;</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    /**</span><a href="#l77"></a>
<span id="l78">     * Parameters for this algorithm.  These are stored in unparsed</span><a href="#l78"></a>
<span id="l79">     * DER-encoded form; subclasses can be made to automaticaly parse</span><a href="#l79"></a>
<span id="l80">     * them so there is fast access to these parameters.</span><a href="#l80"></a>
<span id="l81">     */</span><a href="#l81"></a>
<span id="l82">    protected DerValue          params;</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">    private transient byte[] encodedParams;</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    /**</span><a href="#l86"></a>
<span id="l87">     * Constructs an algorithm ID which will be initialized</span><a href="#l87"></a>
<span id="l88">     * separately, for example by deserialization.</span><a href="#l88"></a>
<span id="l89">     * @deprecated use one of the other constructors.</span><a href="#l89"></a>
<span id="l90">     */</span><a href="#l90"></a>
<span id="l91">    @Deprecated</span><a href="#l91"></a>
<span id="l92">    public AlgorithmId() { }</span><a href="#l92"></a>
<span id="l93"></span><a href="#l93"></a>
<span id="l94">    /**</span><a href="#l94"></a>
<span id="l95">     * Constructs a parameterless algorithm ID.</span><a href="#l95"></a>
<span id="l96">     *</span><a href="#l96"></a>
<span id="l97">     * @param oid the identifier for the algorithm</span><a href="#l97"></a>
<span id="l98">     */</span><a href="#l98"></a>
<span id="l99">    public AlgorithmId(ObjectIdentifier oid) {</span><a href="#l99"></a>
<span id="l100">        algid = oid;</span><a href="#l100"></a>
<span id="l101">    }</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">    /**</span><a href="#l103"></a>
<span id="l104">     * Constructs an algorithm ID with algorithm parameters.</span><a href="#l104"></a>
<span id="l105">     *</span><a href="#l105"></a>
<span id="l106">     * @param oid the identifier for the algorithm.</span><a href="#l106"></a>
<span id="l107">     * @param algparams the associated algorithm parameters.</span><a href="#l107"></a>
<span id="l108">     */</span><a href="#l108"></a>
<span id="l109">    public AlgorithmId(ObjectIdentifier oid, AlgorithmParameters algparams) {</span><a href="#l109"></a>
<span id="l110">        algid = oid;</span><a href="#l110"></a>
<span id="l111">        algParams = algparams;</span><a href="#l111"></a>
<span id="l112">        constructedFromDer = false;</span><a href="#l112"></a>
<span id="l113">        if (algParams != null) {</span><a href="#l113"></a>
<span id="l114">            try {</span><a href="#l114"></a>
<span id="l115">                encodedParams = algParams.getEncoded();</span><a href="#l115"></a>
<span id="l116">            } catch (IOException ioe) {</span><a href="#l116"></a>
<span id="l117">                // It should be safe to ignore this.</span><a href="#l117"></a>
<span id="l118">                // This exception can occur if AlgorithmParameters was not</span><a href="#l118"></a>
<span id="l119">                // initialized (which should not occur), or if it was</span><a href="#l119"></a>
<span id="l120">                // initialized with bogus parameters, which should have</span><a href="#l120"></a>
<span id="l121">                // been detected when init was called.</span><a href="#l121"></a>
<span id="l122">                assert false;</span><a href="#l122"></a>
<span id="l123">            }</span><a href="#l123"></a>
<span id="l124">        }</span><a href="#l124"></a>
<span id="l125">    }</span><a href="#l125"></a>
<span id="l126"></span><a href="#l126"></a>
<span id="l127">    private AlgorithmId(ObjectIdentifier oid, DerValue params)</span><a href="#l127"></a>
<span id="l128">            throws IOException {</span><a href="#l128"></a>
<span id="l129">        this.algid = oid;</span><a href="#l129"></a>
<span id="l130">        this.params = params;</span><a href="#l130"></a>
<span id="l131">        if (this.params != null) {</span><a href="#l131"></a>
<span id="l132">            encodedParams = params.toByteArray();</span><a href="#l132"></a>
<span id="l133">            decodeParams();</span><a href="#l133"></a>
<span id="l134">        }</span><a href="#l134"></a>
<span id="l135">    }</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">    protected void decodeParams() throws IOException {</span><a href="#l137"></a>
<span id="l138">        String algidName = getName();</span><a href="#l138"></a>
<span id="l139">        try {</span><a href="#l139"></a>
<span id="l140">            algParams = AlgorithmParameters.getInstance(algidName);</span><a href="#l140"></a>
<span id="l141">        } catch (NoSuchAlgorithmException e) {</span><a href="#l141"></a>
<span id="l142">            /*</span><a href="#l142"></a>
<span id="l143">             * This algorithm parameter type is not supported, so we cannot</span><a href="#l143"></a>
<span id="l144">             * parse the parameters.</span><a href="#l144"></a>
<span id="l145">             */</span><a href="#l145"></a>
<span id="l146">            algParams = null;</span><a href="#l146"></a>
<span id="l147">            return;</span><a href="#l147"></a>
<span id="l148">        }</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">        // Decode (parse) the parameters</span><a href="#l150"></a>
<span id="l151">        algParams.init(encodedParams.clone());</span><a href="#l151"></a>
<span id="l152">    }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">    /**</span><a href="#l154"></a>
<span id="l155">     * Marshal a DER-encoded &quot;AlgorithmID&quot; sequence on the DER stream.</span><a href="#l155"></a>
<span id="l156">     */</span><a href="#l156"></a>
<span id="l157">    public final void encode(DerOutputStream out) throws IOException {</span><a href="#l157"></a>
<span id="l158">        derEncode(out);</span><a href="#l158"></a>
<span id="l159">    }</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">    /**</span><a href="#l161"></a>
<span id="l162">     * DER encode this object onto an output stream.</span><a href="#l162"></a>
<span id="l163">     * Implements the &lt;code&gt;DerEncoder&lt;/code&gt; interface.</span><a href="#l163"></a>
<span id="l164">     *</span><a href="#l164"></a>
<span id="l165">     * @param out</span><a href="#l165"></a>
<span id="l166">     * the output stream on which to write the DER encoding.</span><a href="#l166"></a>
<span id="l167">     *</span><a href="#l167"></a>
<span id="l168">     * @exception IOException on encoding error.</span><a href="#l168"></a>
<span id="l169">     */</span><a href="#l169"></a>
<span id="l170">    @Override</span><a href="#l170"></a>
<span id="l171">    public void derEncode (OutputStream out) throws IOException {</span><a href="#l171"></a>
<span id="l172">        DerOutputStream bytes = new DerOutputStream();</span><a href="#l172"></a>
<span id="l173">        DerOutputStream tmp = new DerOutputStream();</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">        bytes.putOID(algid);</span><a href="#l175"></a>
<span id="l176">        // Setup params from algParams since no DER encoding is given</span><a href="#l176"></a>
<span id="l177">        if (constructedFromDer == false) {</span><a href="#l177"></a>
<span id="l178">            if (encodedParams != null) {</span><a href="#l178"></a>
<span id="l179">                params = new DerValue(encodedParams);</span><a href="#l179"></a>
<span id="l180">            } else {</span><a href="#l180"></a>
<span id="l181">                params = null;</span><a href="#l181"></a>
<span id="l182">            }</span><a href="#l182"></a>
<span id="l183">        }</span><a href="#l183"></a>
<span id="l184">        if (params == null) {</span><a href="#l184"></a>
<span id="l185">            // Changes backed out for compatibility with Solaris</span><a href="#l185"></a>
<span id="l186"></span><a href="#l186"></a>
<span id="l187">            // Several AlgorithmId should omit the whole parameter part when</span><a href="#l187"></a>
<span id="l188">            // it's NULL. They are ---</span><a href="#l188"></a>
<span id="l189">            // rfc3370 2.1: Implementations SHOULD generate SHA-1</span><a href="#l189"></a>
<span id="l190">            // AlgorithmIdentifiers with absent parameters.</span><a href="#l190"></a>
<span id="l191">            // rfc3447 C1: When id-sha1, id-sha224, id-sha256, id-sha384 and</span><a href="#l191"></a>
<span id="l192">            // id-sha512 are used in an AlgorithmIdentifier the parameters</span><a href="#l192"></a>
<span id="l193">            // (which are optional) SHOULD be omitted.</span><a href="#l193"></a>
<span id="l194">            // rfc3279 2.3.2: The id-dsa algorithm syntax includes optional</span><a href="#l194"></a>
<span id="l195">            // domain parameters... When omitted, the parameters component</span><a href="#l195"></a>
<span id="l196">            // MUST be omitted entirely</span><a href="#l196"></a>
<span id="l197">            // rfc3370 3.1: When the id-dsa-with-sha1 algorithm identifier</span><a href="#l197"></a>
<span id="l198">            // is used, the AlgorithmIdentifier parameters field MUST be absent.</span><a href="#l198"></a>
<span id="l199">            /*if (</span><a href="#l199"></a>
<span id="l200">                algid.equals((Object)SHA_oid) ||</span><a href="#l200"></a>
<span id="l201">                algid.equals((Object)SHA224_oid) ||</span><a href="#l201"></a>
<span id="l202">                algid.equals((Object)SHA256_oid) ||</span><a href="#l202"></a>
<span id="l203">                algid.equals((Object)SHA384_oid) ||</span><a href="#l203"></a>
<span id="l204">                algid.equals((Object)SHA512_oid) ||</span><a href="#l204"></a>
<span id="l205">                algid.equals((Object)SHA512_224_oid) ||</span><a href="#l205"></a>
<span id="l206">                algid.equals((Object)SHA512_256_oid) ||</span><a href="#l206"></a>
<span id="l207">                algid.equals((Object)DSA_oid) ||</span><a href="#l207"></a>
<span id="l208">                algid.equals((Object)sha1WithDSA_oid)) {</span><a href="#l208"></a>
<span id="l209">                ; // no parameter part encoded</span><a href="#l209"></a>
<span id="l210">            } else {</span><a href="#l210"></a>
<span id="l211">                bytes.putNull();</span><a href="#l211"></a>
<span id="l212">            }*/</span><a href="#l212"></a>
<span id="l213">            if (algid.equals(RSASSA_PSS_oid)) {</span><a href="#l213"></a>
<span id="l214">                // RFC 4055 3.3: when an RSASSA-PSS key does not require</span><a href="#l214"></a>
<span id="l215">                // parameter validation, field is absent.</span><a href="#l215"></a>
<span id="l216">            } else {</span><a href="#l216"></a>
<span id="l217">                bytes.putNull();</span><a href="#l217"></a>
<span id="l218">            }</span><a href="#l218"></a>
<span id="l219">        } else {</span><a href="#l219"></a>
<span id="l220">            bytes.putDerValue(params);</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222">        tmp.write(DerValue.tag_Sequence, bytes);</span><a href="#l222"></a>
<span id="l223">        out.write(tmp.toByteArray());</span><a href="#l223"></a>
<span id="l224">    }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">    /**</span><a href="#l227"></a>
<span id="l228">     * Returns the DER-encoded X.509 AlgorithmId as a byte array.</span><a href="#l228"></a>
<span id="l229">     */</span><a href="#l229"></a>
<span id="l230">    public final byte[] encode() throws IOException {</span><a href="#l230"></a>
<span id="l231">        DerOutputStream out = new DerOutputStream();</span><a href="#l231"></a>
<span id="l232">        derEncode(out);</span><a href="#l232"></a>
<span id="l233">        return out.toByteArray();</span><a href="#l233"></a>
<span id="l234">    }</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">    /**</span><a href="#l236"></a>
<span id="l237">     * Returns the ISO OID for this algorithm.  This is usually converted</span><a href="#l237"></a>
<span id="l238">     * to a string and used as part of an algorithm name, for example</span><a href="#l238"></a>
<span id="l239">     * &quot;OID.1.3.14.3.2.13&quot; style notation.  Use the &lt;code&gt;getName&lt;/code&gt;</span><a href="#l239"></a>
<span id="l240">     * call when you do not need to ensure cross-system portability</span><a href="#l240"></a>
<span id="l241">     * of algorithm names, or need a user friendly name.</span><a href="#l241"></a>
<span id="l242">     */</span><a href="#l242"></a>
<span id="l243">    public final ObjectIdentifier getOID () {</span><a href="#l243"></a>
<span id="l244">        return algid;</span><a href="#l244"></a>
<span id="l245">    }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247">    /**</span><a href="#l247"></a>
<span id="l248">     * Returns a name for the algorithm which may be more intelligible</span><a href="#l248"></a>
<span id="l249">     * to humans than the algorithm's OID, but which won't necessarily</span><a href="#l249"></a>
<span id="l250">     * be comprehensible on other systems.  For example, this might</span><a href="#l250"></a>
<span id="l251">     * return a name such as &quot;MD5withRSA&quot; for a signature algorithm on</span><a href="#l251"></a>
<span id="l252">     * some systems.  It also returns names like &quot;OID.1.2.3.4&quot;, when</span><a href="#l252"></a>
<span id="l253">     * no particular name for the algorithm is known.</span><a href="#l253"></a>
<span id="l254">     *</span><a href="#l254"></a>
<span id="l255">     * Note: for ecdsa-with-SHA2 plus hash algorithm (Ex: SHA-256), this method</span><a href="#l255"></a>
<span id="l256">     * returns the &quot;full&quot; signature algorithm (Ex: SHA256withECDSA) directly.</span><a href="#l256"></a>
<span id="l257">     */</span><a href="#l257"></a>
<span id="l258">    public String getName() {</span><a href="#l258"></a>
<span id="l259">        String algName = nameTable.get(algid);</span><a href="#l259"></a>
<span id="l260">        if (algName != null) {</span><a href="#l260"></a>
<span id="l261">            return algName;</span><a href="#l261"></a>
<span id="l262">        }</span><a href="#l262"></a>
<span id="l263">        if ((params != null) &amp;&amp; algid.equals((Object)specifiedWithECDSA_oid)) {</span><a href="#l263"></a>
<span id="l264">            try {</span><a href="#l264"></a>
<span id="l265">                AlgorithmId paramsId =</span><a href="#l265"></a>
<span id="l266">                        AlgorithmId.parse(new DerValue(encodedParams));</span><a href="#l266"></a>
<span id="l267">                String paramsName = paramsId.getName();</span><a href="#l267"></a>
<span id="l268">                algName = makeSigAlg(paramsName, &quot;EC&quot;);</span><a href="#l268"></a>
<span id="l269">            } catch (IOException e) {</span><a href="#l269"></a>
<span id="l270">                // ignore</span><a href="#l270"></a>
<span id="l271">            }</span><a href="#l271"></a>
<span id="l272">        }</span><a href="#l272"></a>
<span id="l273">        return (algName == null) ? algid.toString() : algName;</span><a href="#l273"></a>
<span id="l274">    }</span><a href="#l274"></a>
<span id="l275"></span><a href="#l275"></a>
<span id="l276">    public AlgorithmParameters getParameters() {</span><a href="#l276"></a>
<span id="l277">        return algParams;</span><a href="#l277"></a>
<span id="l278">    }</span><a href="#l278"></a>
<span id="l279"></span><a href="#l279"></a>
<span id="l280">    /**</span><a href="#l280"></a>
<span id="l281">     * Returns the DER encoded parameter, which can then be</span><a href="#l281"></a>
<span id="l282">     * used to initialize java.security.AlgorithmParameters.</span><a href="#l282"></a>
<span id="l283">     *</span><a href="#l283"></a>
<span id="l284">     * Note that this* method should always return a new array as it is called</span><a href="#l284"></a>
<span id="l285">     * directly by the JDK implementation of X509Certificate.getSigAlgParams()</span><a href="#l285"></a>
<span id="l286">     * and X509CRL.getSigAlgParams().</span><a href="#l286"></a>
<span id="l287">     *</span><a href="#l287"></a>
<span id="l288">     * Note: for ecdsa-with-SHA2 plus hash algorithm (Ex: SHA-256), this method</span><a href="#l288"></a>
<span id="l289">     * returns null because {@link #getName()} has already returned the &quot;full&quot;</span><a href="#l289"></a>
<span id="l290">     * signature algorithm (Ex: SHA256withECDSA).</span><a href="#l290"></a>
<span id="l291">     *</span><a href="#l291"></a>
<span id="l292">     * @return DER encoded parameters, or null not present.</span><a href="#l292"></a>
<span id="l293">     */</span><a href="#l293"></a>
<span id="l294">    public byte[] getEncodedParams() throws IOException {</span><a href="#l294"></a>
<span id="l295">        return (encodedParams == null || algid.equals(specifiedWithECDSA_oid))</span><a href="#l295"></a>
<span id="l296">                ? null</span><a href="#l296"></a>
<span id="l297">                : encodedParams.clone();</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    /**</span><a href="#l300"></a>
<span id="l301">     * Returns true iff the argument indicates the same algorithm</span><a href="#l301"></a>
<span id="l302">     * with the same parameters.</span><a href="#l302"></a>
<span id="l303">     */</span><a href="#l303"></a>
<span id="l304">    public boolean equals(AlgorithmId other) {</span><a href="#l304"></a>
<span id="l305">        return algid.equals((Object)other.algid) &amp;&amp;</span><a href="#l305"></a>
<span id="l306">            Arrays.equals(encodedParams, other.encodedParams);</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    /**</span><a href="#l309"></a>
<span id="l310">     * Compares this AlgorithmID to another.  If algorithm parameters are</span><a href="#l310"></a>
<span id="l311">     * available, they are compared.  Otherwise, just the object IDs</span><a href="#l311"></a>
<span id="l312">     * for the algorithm are compared.</span><a href="#l312"></a>
<span id="l313">     *</span><a href="#l313"></a>
<span id="l314">     * @param other preferably an AlgorithmId, else an ObjectIdentifier</span><a href="#l314"></a>
<span id="l315">     */</span><a href="#l315"></a>
<span id="l316">    @Override</span><a href="#l316"></a>
<span id="l317">    public boolean equals(Object other) {</span><a href="#l317"></a>
<span id="l318">        if (this == other) {</span><a href="#l318"></a>
<span id="l319">            return true;</span><a href="#l319"></a>
<span id="l320">        }</span><a href="#l320"></a>
<span id="l321">        if (other instanceof AlgorithmId) {</span><a href="#l321"></a>
<span id="l322">            return equals((AlgorithmId) other);</span><a href="#l322"></a>
<span id="l323">        } else if (other instanceof ObjectIdentifier) {</span><a href="#l323"></a>
<span id="l324">            return equals((ObjectIdentifier) other);</span><a href="#l324"></a>
<span id="l325">        } else {</span><a href="#l325"></a>
<span id="l326">            return false;</span><a href="#l326"></a>
<span id="l327">        }</span><a href="#l327"></a>
<span id="l328">    }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">    /**</span><a href="#l330"></a>
<span id="l331">     * Compares two algorithm IDs for equality.  Returns true iff</span><a href="#l331"></a>
<span id="l332">     * they are the same algorithm, ignoring algorithm parameters.</span><a href="#l332"></a>
<span id="l333">     */</span><a href="#l333"></a>
<span id="l334">    public final boolean equals(ObjectIdentifier id) {</span><a href="#l334"></a>
<span id="l335">        return algid.equals((Object)id);</span><a href="#l335"></a>
<span id="l336">    }</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">    /**</span><a href="#l338"></a>
<span id="l339">     * Returns a hashcode for this AlgorithmId.</span><a href="#l339"></a>
<span id="l340">     *</span><a href="#l340"></a>
<span id="l341">     * @return a hashcode for this AlgorithmId.</span><a href="#l341"></a>
<span id="l342">     */</span><a href="#l342"></a>
<span id="l343">    @Override</span><a href="#l343"></a>
<span id="l344">    public int hashCode() {</span><a href="#l344"></a>
<span id="l345">        int hashCode = algid.hashCode();</span><a href="#l345"></a>
<span id="l346">        hashCode = 31 * hashCode + Arrays.hashCode(encodedParams);</span><a href="#l346"></a>
<span id="l347">        return hashCode;</span><a href="#l347"></a>
<span id="l348">    }</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">    /**</span><a href="#l350"></a>
<span id="l351">     * Provides a human-readable description of the algorithm parameters.</span><a href="#l351"></a>
<span id="l352">     * This may be redefined by subclasses which parse those parameters.</span><a href="#l352"></a>
<span id="l353">     */</span><a href="#l353"></a>
<span id="l354">    protected String paramsToString() {</span><a href="#l354"></a>
<span id="l355">        if (encodedParams == null) {</span><a href="#l355"></a>
<span id="l356">            return &quot;&quot;;</span><a href="#l356"></a>
<span id="l357">        } else if (algParams != null) {</span><a href="#l357"></a>
<span id="l358">            return &quot;, &quot; + algParams.toString();</span><a href="#l358"></a>
<span id="l359">        } else {</span><a href="#l359"></a>
<span id="l360">            return &quot;, params unparsed&quot;;</span><a href="#l360"></a>
<span id="l361">        }</span><a href="#l361"></a>
<span id="l362">    }</span><a href="#l362"></a>
<span id="l363"></span><a href="#l363"></a>
<span id="l364">    /**</span><a href="#l364"></a>
<span id="l365">     * Returns a string describing the algorithm and its parameters.</span><a href="#l365"></a>
<span id="l366">     */</span><a href="#l366"></a>
<span id="l367">    @Override</span><a href="#l367"></a>
<span id="l368">    public String toString() {</span><a href="#l368"></a>
<span id="l369">        return getName() + paramsToString();</span><a href="#l369"></a>
<span id="l370">    }</span><a href="#l370"></a>
<span id="l371"></span><a href="#l371"></a>
<span id="l372">    /**</span><a href="#l372"></a>
<span id="l373">     * Parse (unmarshal) an ID from a DER sequence input value.  This form</span><a href="#l373"></a>
<span id="l374">     * parsing might be used when expanding a value which has already been</span><a href="#l374"></a>
<span id="l375">     * partially unmarshaled as a set or sequence member.</span><a href="#l375"></a>
<span id="l376">     *</span><a href="#l376"></a>
<span id="l377">     * @exception IOException on error.</span><a href="#l377"></a>
<span id="l378">     * @param val the input value, which contains the algid and, if</span><a href="#l378"></a>
<span id="l379">     *          there are any parameters, those parameters.</span><a href="#l379"></a>
<span id="l380">     * @return an ID for the algorithm.  If the system is configured</span><a href="#l380"></a>
<span id="l381">     *          appropriately, this may be an instance of a class</span><a href="#l381"></a>
<span id="l382">     *          with some kind of special support for this algorithm.</span><a href="#l382"></a>
<span id="l383">     *          In that case, you may &quot;narrow&quot; the type of the ID.</span><a href="#l383"></a>
<span id="l384">     */</span><a href="#l384"></a>
<span id="l385">    public static AlgorithmId parse(DerValue val) throws IOException {</span><a href="#l385"></a>
<span id="l386">        if (val.tag != DerValue.tag_Sequence) {</span><a href="#l386"></a>
<span id="l387">            throw new IOException(&quot;algid parse error, not a sequence&quot;);</span><a href="#l387"></a>
<span id="l388">        }</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">        /*</span><a href="#l390"></a>
<span id="l391">         * Get the algorithm ID and any parameters.</span><a href="#l391"></a>
<span id="l392">         */</span><a href="#l392"></a>
<span id="l393">        ObjectIdentifier        algid;</span><a href="#l393"></a>
<span id="l394">        DerValue                params;</span><a href="#l394"></a>
<span id="l395">        DerInputStream          in = val.toDerInputStream();</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">        algid = in.getOID();</span><a href="#l397"></a>
<span id="l398">        if (in.available() == 0) {</span><a href="#l398"></a>
<span id="l399">            params = null;</span><a href="#l399"></a>
<span id="l400">        } else {</span><a href="#l400"></a>
<span id="l401">            params = in.getDerValue();</span><a href="#l401"></a>
<span id="l402">            if (params.tag == DerValue.tag_Null) {</span><a href="#l402"></a>
<span id="l403">                if (params.length() != 0) {</span><a href="#l403"></a>
<span id="l404">                    throw new IOException(&quot;invalid NULL&quot;);</span><a href="#l404"></a>
<span id="l405">                }</span><a href="#l405"></a>
<span id="l406">                params = null;</span><a href="#l406"></a>
<span id="l407">            }</span><a href="#l407"></a>
<span id="l408">            if (in.available() != 0) {</span><a href="#l408"></a>
<span id="l409">                throw new IOException(&quot;Invalid AlgorithmIdentifier: extra data&quot;);</span><a href="#l409"></a>
<span id="l410">            }</span><a href="#l410"></a>
<span id="l411">        }</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">        return new AlgorithmId(algid, params);</span><a href="#l413"></a>
<span id="l414">    }</span><a href="#l414"></a>
<span id="l415"></span><a href="#l415"></a>
<span id="l416">    /**</span><a href="#l416"></a>
<span id="l417">     * Returns one of the algorithm IDs most commonly associated</span><a href="#l417"></a>
<span id="l418">     * with this algorithm name.</span><a href="#l418"></a>
<span id="l419">     *</span><a href="#l419"></a>
<span id="l420">     * @param algname the name being used</span><a href="#l420"></a>
<span id="l421">     * @deprecated use the short get form of this method.</span><a href="#l421"></a>
<span id="l422">     * @exception NoSuchAlgorithmException on error.</span><a href="#l422"></a>
<span id="l423">     */</span><a href="#l423"></a>
<span id="l424">    @Deprecated</span><a href="#l424"></a>
<span id="l425">    public static AlgorithmId getAlgorithmId(String algname)</span><a href="#l425"></a>
<span id="l426">            throws NoSuchAlgorithmException {</span><a href="#l426"></a>
<span id="l427">        return get(algname);</span><a href="#l427"></a>
<span id="l428">    }</span><a href="#l428"></a>
<span id="l429"></span><a href="#l429"></a>
<span id="l430">    /**</span><a href="#l430"></a>
<span id="l431">     * Returns one of the algorithm IDs most commonly associated</span><a href="#l431"></a>
<span id="l432">     * with this algorithm name.</span><a href="#l432"></a>
<span id="l433">     *</span><a href="#l433"></a>
<span id="l434">     * @param algname the name being used</span><a href="#l434"></a>
<span id="l435">     * @exception NoSuchAlgorithmException on error.</span><a href="#l435"></a>
<span id="l436">     */</span><a href="#l436"></a>
<span id="l437">    public static AlgorithmId get(String algname)</span><a href="#l437"></a>
<span id="l438">            throws NoSuchAlgorithmException {</span><a href="#l438"></a>
<span id="l439">        ObjectIdentifier oid;</span><a href="#l439"></a>
<span id="l440">        try {</span><a href="#l440"></a>
<span id="l441">            oid = algOID(algname);</span><a href="#l441"></a>
<span id="l442">        } catch (IOException ioe) {</span><a href="#l442"></a>
<span id="l443">            throw new NoSuchAlgorithmException</span><a href="#l443"></a>
<span id="l444">                (&quot;Invalid ObjectIdentifier &quot; + algname);</span><a href="#l444"></a>
<span id="l445">        }</span><a href="#l445"></a>
<span id="l446"></span><a href="#l446"></a>
<span id="l447">        if (oid == null) {</span><a href="#l447"></a>
<span id="l448">            throw new NoSuchAlgorithmException</span><a href="#l448"></a>
<span id="l449">                (&quot;unrecognized algorithm name: &quot; + algname);</span><a href="#l449"></a>
<span id="l450">        }</span><a href="#l450"></a>
<span id="l451">        return new AlgorithmId(oid);</span><a href="#l451"></a>
<span id="l452">    }</span><a href="#l452"></a>
<span id="l453"></span><a href="#l453"></a>
<span id="l454">    /**</span><a href="#l454"></a>
<span id="l455">     * Returns one of the algorithm IDs most commonly associated</span><a href="#l455"></a>
<span id="l456">     * with this algorithm parameters.</span><a href="#l456"></a>
<span id="l457">     *</span><a href="#l457"></a>
<span id="l458">     * @param algparams the associated algorithm parameters.</span><a href="#l458"></a>
<span id="l459">     * @exception NoSuchAlgorithmException on error.</span><a href="#l459"></a>
<span id="l460">     */</span><a href="#l460"></a>
<span id="l461">    public static AlgorithmId get(AlgorithmParameters algparams)</span><a href="#l461"></a>
<span id="l462">            throws NoSuchAlgorithmException {</span><a href="#l462"></a>
<span id="l463">        ObjectIdentifier oid;</span><a href="#l463"></a>
<span id="l464">        String algname = algparams.getAlgorithm();</span><a href="#l464"></a>
<span id="l465">        try {</span><a href="#l465"></a>
<span id="l466">            oid = algOID(algname);</span><a href="#l466"></a>
<span id="l467">        } catch (IOException ioe) {</span><a href="#l467"></a>
<span id="l468">            throw new NoSuchAlgorithmException</span><a href="#l468"></a>
<span id="l469">                (&quot;Invalid ObjectIdentifier &quot; + algname);</span><a href="#l469"></a>
<span id="l470">        }</span><a href="#l470"></a>
<span id="l471">        if (oid == null) {</span><a href="#l471"></a>
<span id="l472">            throw new NoSuchAlgorithmException</span><a href="#l472"></a>
<span id="l473">                (&quot;unrecognized algorithm name: &quot; + algname);</span><a href="#l473"></a>
<span id="l474">        }</span><a href="#l474"></a>
<span id="l475">        return new AlgorithmId(oid, algparams);</span><a href="#l475"></a>
<span id="l476">    }</span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">    /*</span><a href="#l478"></a>
<span id="l479">     * Translates from some common algorithm names to the</span><a href="#l479"></a>
<span id="l480">     * OID with which they're usually associated ... this mapping</span><a href="#l480"></a>
<span id="l481">     * is the reverse of the one below, except in those cases</span><a href="#l481"></a>
<span id="l482">     * where synonyms are supported or where a given algorithm</span><a href="#l482"></a>
<span id="l483">     * is commonly associated with multiple OIDs.</span><a href="#l483"></a>
<span id="l484">     *</span><a href="#l484"></a>
<span id="l485">     * XXX This method needs to be enhanced so that we can also pass the</span><a href="#l485"></a>
<span id="l486">     * scope of the algorithm name to it, e.g., the algorithm name &quot;DSA&quot;</span><a href="#l486"></a>
<span id="l487">     * may have a different OID when used as a &quot;Signature&quot; algorithm than when</span><a href="#l487"></a>
<span id="l488">     * used as a &quot;KeyPairGenerator&quot; algorithm.</span><a href="#l488"></a>
<span id="l489">     */</span><a href="#l489"></a>
<span id="l490">    private static ObjectIdentifier algOID(String name) throws IOException {</span><a href="#l490"></a>
<span id="l491">        // See if algname is in printable OID (&quot;dot-dot&quot;) notation</span><a href="#l491"></a>
<span id="l492">        if (name.indexOf('.') != -1) {</span><a href="#l492"></a>
<span id="l493">            if (name.startsWith(&quot;OID.&quot;)) {</span><a href="#l493"></a>
<span id="l494">                return new ObjectIdentifier(name.substring(&quot;OID.&quot;.length()));</span><a href="#l494"></a>
<span id="l495">            } else {</span><a href="#l495"></a>
<span id="l496">                return new ObjectIdentifier(name);</span><a href="#l496"></a>
<span id="l497">            }</span><a href="#l497"></a>
<span id="l498">        }</span><a href="#l498"></a>
<span id="l499"></span><a href="#l499"></a>
<span id="l500">        // Digesting algorithms</span><a href="#l500"></a>
<span id="l501">        if (name.equalsIgnoreCase(&quot;MD5&quot;)) {</span><a href="#l501"></a>
<span id="l502">            return AlgorithmId.MD5_oid;</span><a href="#l502"></a>
<span id="l503">        }</span><a href="#l503"></a>
<span id="l504">        if (name.equalsIgnoreCase(&quot;MD2&quot;)) {</span><a href="#l504"></a>
<span id="l505">            return AlgorithmId.MD2_oid;</span><a href="#l505"></a>
<span id="l506">        }</span><a href="#l506"></a>
<span id="l507">        if (name.equalsIgnoreCase(&quot;SHA&quot;) || name.equalsIgnoreCase(&quot;SHA1&quot;)</span><a href="#l507"></a>
<span id="l508">            || name.equalsIgnoreCase(&quot;SHA-1&quot;)) {</span><a href="#l508"></a>
<span id="l509">            return AlgorithmId.SHA_oid;</span><a href="#l509"></a>
<span id="l510">        }</span><a href="#l510"></a>
<span id="l511">        if (name.equalsIgnoreCase(&quot;SHA-256&quot;) ||</span><a href="#l511"></a>
<span id="l512">            name.equalsIgnoreCase(&quot;SHA256&quot;)) {</span><a href="#l512"></a>
<span id="l513">            return AlgorithmId.SHA256_oid;</span><a href="#l513"></a>
<span id="l514">        }</span><a href="#l514"></a>
<span id="l515">        if (name.equalsIgnoreCase(&quot;SHA-384&quot;) ||</span><a href="#l515"></a>
<span id="l516">            name.equalsIgnoreCase(&quot;SHA384&quot;)) {</span><a href="#l516"></a>
<span id="l517">            return AlgorithmId.SHA384_oid;</span><a href="#l517"></a>
<span id="l518">        }</span><a href="#l518"></a>
<span id="l519">        if (name.equalsIgnoreCase(&quot;SHA-512&quot;) ||</span><a href="#l519"></a>
<span id="l520">            name.equalsIgnoreCase(&quot;SHA512&quot;)) {</span><a href="#l520"></a>
<span id="l521">            return AlgorithmId.SHA512_oid;</span><a href="#l521"></a>
<span id="l522">        }</span><a href="#l522"></a>
<span id="l523">        if (name.equalsIgnoreCase(&quot;SHA-224&quot;) ||</span><a href="#l523"></a>
<span id="l524">            name.equalsIgnoreCase(&quot;SHA224&quot;)) {</span><a href="#l524"></a>
<span id="l525">            return AlgorithmId.SHA224_oid;</span><a href="#l525"></a>
<span id="l526">        }</span><a href="#l526"></a>
<span id="l527">        if (name.equalsIgnoreCase(&quot;SHA-512/224&quot;) ||</span><a href="#l527"></a>
<span id="l528">            name.equalsIgnoreCase(&quot;SHA512/224&quot;)) {</span><a href="#l528"></a>
<span id="l529">            return AlgorithmId.SHA512_224_oid;</span><a href="#l529"></a>
<span id="l530">        }</span><a href="#l530"></a>
<span id="l531">        if (name.equalsIgnoreCase(&quot;SHA-512/256&quot;) ||</span><a href="#l531"></a>
<span id="l532">            name.equalsIgnoreCase(&quot;SHA512/256&quot;)) {</span><a href="#l532"></a>
<span id="l533">            return AlgorithmId.SHA512_256_oid;</span><a href="#l533"></a>
<span id="l534">        }</span><a href="#l534"></a>
<span id="l535">        // Various public key algorithms</span><a href="#l535"></a>
<span id="l536">        if (name.equalsIgnoreCase(&quot;RSA&quot;)) {</span><a href="#l536"></a>
<span id="l537">            return AlgorithmId.RSAEncryption_oid;</span><a href="#l537"></a>
<span id="l538">        }</span><a href="#l538"></a>
<span id="l539">        if (name.equalsIgnoreCase(&quot;RSASSA-PSS&quot;)) {</span><a href="#l539"></a>
<span id="l540">            return AlgorithmId.RSASSA_PSS_oid;</span><a href="#l540"></a>
<span id="l541">        }</span><a href="#l541"></a>
<span id="l542">        if (name.equalsIgnoreCase(&quot;RSAES-OAEP&quot;)) {</span><a href="#l542"></a>
<span id="l543">            return AlgorithmId.RSAES_OAEP_oid;</span><a href="#l543"></a>
<span id="l544">        }</span><a href="#l544"></a>
<span id="l545">        if (name.equalsIgnoreCase(&quot;Diffie-Hellman&quot;)</span><a href="#l545"></a>
<span id="l546">            || name.equalsIgnoreCase(&quot;DH&quot;)) {</span><a href="#l546"></a>
<span id="l547">            return AlgorithmId.DH_oid;</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549">        if (name.equalsIgnoreCase(&quot;DSA&quot;)) {</span><a href="#l549"></a>
<span id="l550">            return AlgorithmId.DSA_oid;</span><a href="#l550"></a>
<span id="l551">        }</span><a href="#l551"></a>
<span id="l552">        if (name.equalsIgnoreCase(&quot;EC&quot;)) {</span><a href="#l552"></a>
<span id="l553">            return EC_oid;</span><a href="#l553"></a>
<span id="l554">        }</span><a href="#l554"></a>
<span id="l555">        if (name.equalsIgnoreCase(&quot;ECDH&quot;)) {</span><a href="#l555"></a>
<span id="l556">            return AlgorithmId.ECDH_oid;</span><a href="#l556"></a>
<span id="l557">        }</span><a href="#l557"></a>
<span id="l558"></span><a href="#l558"></a>
<span id="l559">        // Secret key algorithms</span><a href="#l559"></a>
<span id="l560">        if (name.equalsIgnoreCase(&quot;AES&quot;)) {</span><a href="#l560"></a>
<span id="l561">            return AlgorithmId.AES_oid;</span><a href="#l561"></a>
<span id="l562">        }</span><a href="#l562"></a>
<span id="l563"></span><a href="#l563"></a>
<span id="l564">        // Common signature types</span><a href="#l564"></a>
<span id="l565">        if (name.equalsIgnoreCase(&quot;MD5withRSA&quot;)</span><a href="#l565"></a>
<span id="l566">            || name.equalsIgnoreCase(&quot;MD5/RSA&quot;)) {</span><a href="#l566"></a>
<span id="l567">            return AlgorithmId.md5WithRSAEncryption_oid;</span><a href="#l567"></a>
<span id="l568">        }</span><a href="#l568"></a>
<span id="l569">        if (name.equalsIgnoreCase(&quot;MD2withRSA&quot;)</span><a href="#l569"></a>
<span id="l570">            || name.equalsIgnoreCase(&quot;MD2/RSA&quot;)) {</span><a href="#l570"></a>
<span id="l571">            return AlgorithmId.md2WithRSAEncryption_oid;</span><a href="#l571"></a>
<span id="l572">        }</span><a href="#l572"></a>
<span id="l573">        if (name.equalsIgnoreCase(&quot;SHAwithDSA&quot;)</span><a href="#l573"></a>
<span id="l574">            || name.equalsIgnoreCase(&quot;SHA1withDSA&quot;)</span><a href="#l574"></a>
<span id="l575">            || name.equalsIgnoreCase(&quot;SHA/DSA&quot;)</span><a href="#l575"></a>
<span id="l576">            || name.equalsIgnoreCase(&quot;SHA1/DSA&quot;)</span><a href="#l576"></a>
<span id="l577">            || name.equalsIgnoreCase(&quot;DSAWithSHA1&quot;)</span><a href="#l577"></a>
<span id="l578">            || name.equalsIgnoreCase(&quot;DSS&quot;)</span><a href="#l578"></a>
<span id="l579">            || name.equalsIgnoreCase(&quot;SHA-1/DSA&quot;)) {</span><a href="#l579"></a>
<span id="l580">            return AlgorithmId.sha1WithDSA_oid;</span><a href="#l580"></a>
<span id="l581">        }</span><a href="#l581"></a>
<span id="l582">        if (name.equalsIgnoreCase(&quot;SHA224WithDSA&quot;)) {</span><a href="#l582"></a>
<span id="l583">            return AlgorithmId.sha224WithDSA_oid;</span><a href="#l583"></a>
<span id="l584">        }</span><a href="#l584"></a>
<span id="l585">        if (name.equalsIgnoreCase(&quot;SHA256WithDSA&quot;)) {</span><a href="#l585"></a>
<span id="l586">            return AlgorithmId.sha256WithDSA_oid;</span><a href="#l586"></a>
<span id="l587">        }</span><a href="#l587"></a>
<span id="l588">        if (name.equalsIgnoreCase(&quot;SHA1WithRSA&quot;)</span><a href="#l588"></a>
<span id="l589">            || name.equalsIgnoreCase(&quot;SHA1/RSA&quot;)) {</span><a href="#l589"></a>
<span id="l590">            return AlgorithmId.sha1WithRSAEncryption_oid;</span><a href="#l590"></a>
<span id="l591">        }</span><a href="#l591"></a>
<span id="l592">        if (name.equalsIgnoreCase(&quot;SHA1withECDSA&quot;)</span><a href="#l592"></a>
<span id="l593">                || name.equalsIgnoreCase(&quot;ECDSA&quot;)) {</span><a href="#l593"></a>
<span id="l594">            return AlgorithmId.sha1WithECDSA_oid;</span><a href="#l594"></a>
<span id="l595">        }</span><a href="#l595"></a>
<span id="l596">        if (name.equalsIgnoreCase(&quot;SHA224withECDSA&quot;)) {</span><a href="#l596"></a>
<span id="l597">            return AlgorithmId.sha224WithECDSA_oid;</span><a href="#l597"></a>
<span id="l598">        }</span><a href="#l598"></a>
<span id="l599">        if (name.equalsIgnoreCase(&quot;SHA256withECDSA&quot;)) {</span><a href="#l599"></a>
<span id="l600">            return AlgorithmId.sha256WithECDSA_oid;</span><a href="#l600"></a>
<span id="l601">        }</span><a href="#l601"></a>
<span id="l602">        if (name.equalsIgnoreCase(&quot;SHA384withECDSA&quot;)) {</span><a href="#l602"></a>
<span id="l603">            return AlgorithmId.sha384WithECDSA_oid;</span><a href="#l603"></a>
<span id="l604">        }</span><a href="#l604"></a>
<span id="l605">        if (name.equalsIgnoreCase(&quot;SHA512withECDSA&quot;)) {</span><a href="#l605"></a>
<span id="l606">            return AlgorithmId.sha512WithECDSA_oid;</span><a href="#l606"></a>
<span id="l607">        }</span><a href="#l607"></a>
<span id="l608"></span><a href="#l608"></a>
<span id="l609">        // See if any of the installed providers supply a mapping from</span><a href="#l609"></a>
<span id="l610">        // the given algorithm name to an OID string</span><a href="#l610"></a>
<span id="l611">        String oidString;</span><a href="#l611"></a>
<span id="l612">        if (!initOidTable) {</span><a href="#l612"></a>
<span id="l613">            Provider[] provs = Security.getProviders();</span><a href="#l613"></a>
<span id="l614">            for (int i=0; i&lt;provs.length; i++) {</span><a href="#l614"></a>
<span id="l615">                for (Enumeration&lt;Object&gt; enum_ = provs[i].keys();</span><a href="#l615"></a>
<span id="l616">                     enum_.hasMoreElements(); ) {</span><a href="#l616"></a>
<span id="l617">                    String alias = (String)enum_.nextElement();</span><a href="#l617"></a>
<span id="l618">                    String upperCaseAlias = alias.toUpperCase(Locale.ENGLISH);</span><a href="#l618"></a>
<span id="l619">                    int index;</span><a href="#l619"></a>
<span id="l620">                    if (upperCaseAlias.startsWith(&quot;ALG.ALIAS&quot;) &amp;&amp;</span><a href="#l620"></a>
<span id="l621">                            (index=upperCaseAlias.indexOf(&quot;OID.&quot;, 0)) != -1) {</span><a href="#l621"></a>
<span id="l622">                        index += &quot;OID.&quot;.length();</span><a href="#l622"></a>
<span id="l623">                        if (index == alias.length()) {</span><a href="#l623"></a>
<span id="l624">                            // invalid alias entry</span><a href="#l624"></a>
<span id="l625">                            break;</span><a href="#l625"></a>
<span id="l626">                        }</span><a href="#l626"></a>
<span id="l627">                        if (oidTable == null) {</span><a href="#l627"></a>
<span id="l628">                            oidTable = new HashMap&lt;String,ObjectIdentifier&gt;();</span><a href="#l628"></a>
<span id="l629">                        }</span><a href="#l629"></a>
<span id="l630">                        oidString = alias.substring(index);</span><a href="#l630"></a>
<span id="l631">                        String stdAlgName = provs[i].getProperty(alias);</span><a href="#l631"></a>
<span id="l632">                        if (stdAlgName != null) {</span><a href="#l632"></a>
<span id="l633">                            stdAlgName = stdAlgName.toUpperCase(Locale.ENGLISH);</span><a href="#l633"></a>
<span id="l634">                        }</span><a href="#l634"></a>
<span id="l635">                        if (stdAlgName != null &amp;&amp;</span><a href="#l635"></a>
<span id="l636">                                oidTable.get(stdAlgName) == null) {</span><a href="#l636"></a>
<span id="l637">                            oidTable.put(stdAlgName,</span><a href="#l637"></a>
<span id="l638">                                         new ObjectIdentifier(oidString));</span><a href="#l638"></a>
<span id="l639">                        }</span><a href="#l639"></a>
<span id="l640">                    }</span><a href="#l640"></a>
<span id="l641">                }</span><a href="#l641"></a>
<span id="l642">            }</span><a href="#l642"></a>
<span id="l643"></span><a href="#l643"></a>
<span id="l644">            if (oidTable == null) {</span><a href="#l644"></a>
<span id="l645">                oidTable = Collections.&lt;String,ObjectIdentifier&gt;emptyMap();</span><a href="#l645"></a>
<span id="l646">            }</span><a href="#l646"></a>
<span id="l647">            initOidTable = true;</span><a href="#l647"></a>
<span id="l648">        }</span><a href="#l648"></a>
<span id="l649"></span><a href="#l649"></a>
<span id="l650">        return oidTable.get(name.toUpperCase(Locale.ENGLISH));</span><a href="#l650"></a>
<span id="l651">    }</span><a href="#l651"></a>
<span id="l652"></span><a href="#l652"></a>
<span id="l653">    private static ObjectIdentifier oid(int ... values) {</span><a href="#l653"></a>
<span id="l654">        return ObjectIdentifier.newInternal(values);</span><a href="#l654"></a>
<span id="l655">    }</span><a href="#l655"></a>
<span id="l656"></span><a href="#l656"></a>
<span id="l657">    private static boolean initOidTable = false;</span><a href="#l657"></a>
<span id="l658">    private static Map&lt;String,ObjectIdentifier&gt; oidTable;</span><a href="#l658"></a>
<span id="l659">    private static final Map&lt;ObjectIdentifier,String&gt; nameTable;</span><a href="#l659"></a>
<span id="l660"></span><a href="#l660"></a>
<span id="l661">    /*****************************************************************/</span><a href="#l661"></a>
<span id="l662"></span><a href="#l662"></a>
<span id="l663">    /*</span><a href="#l663"></a>
<span id="l664">     * HASHING ALGORITHMS</span><a href="#l664"></a>
<span id="l665">     */</span><a href="#l665"></a>
<span id="l666"></span><a href="#l666"></a>
<span id="l667">    /**</span><a href="#l667"></a>
<span id="l668">     * Algorithm ID for the MD2 Message Digest Algorthm, from RFC 1319.</span><a href="#l668"></a>
<span id="l669">     * OID = 1.2.840.113549.2.2</span><a href="#l669"></a>
<span id="l670">     */</span><a href="#l670"></a>
<span id="l671">    public static final ObjectIdentifier MD2_oid =</span><a href="#l671"></a>
<span id="l672">    ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 2, 2});</span><a href="#l672"></a>
<span id="l673"></span><a href="#l673"></a>
<span id="l674">    /**</span><a href="#l674"></a>
<span id="l675">     * Algorithm ID for the MD5 Message Digest Algorthm, from RFC 1321.</span><a href="#l675"></a>
<span id="l676">     * OID = 1.2.840.113549.2.5</span><a href="#l676"></a>
<span id="l677">     */</span><a href="#l677"></a>
<span id="l678">    public static final ObjectIdentifier MD5_oid =</span><a href="#l678"></a>
<span id="l679">    ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 2, 5});</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">    /**</span><a href="#l681"></a>
<span id="l682">     * Algorithm ID for the SHA1 Message Digest Algorithm, from FIPS 180-1.</span><a href="#l682"></a>
<span id="l683">     * This is sometimes called &quot;SHA&quot;, though that is often confusing since</span><a href="#l683"></a>
<span id="l684">     * many people refer to FIPS 180 (which has an error) as defining SHA.</span><a href="#l684"></a>
<span id="l685">     * OID = 1.3.14.3.2.26. Old SHA-0 OID: 1.3.14.3.2.18.</span><a href="#l685"></a>
<span id="l686">     */</span><a href="#l686"></a>
<span id="l687">    public static final ObjectIdentifier SHA_oid =</span><a href="#l687"></a>
<span id="l688">    ObjectIdentifier.newInternal(new int[] {1, 3, 14, 3, 2, 26});</span><a href="#l688"></a>
<span id="l689"></span><a href="#l689"></a>
<span id="l690">    public static final ObjectIdentifier SHA224_oid =</span><a href="#l690"></a>
<span id="l691">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 4});</span><a href="#l691"></a>
<span id="l692"></span><a href="#l692"></a>
<span id="l693">    public static final ObjectIdentifier SHA256_oid =</span><a href="#l693"></a>
<span id="l694">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 1});</span><a href="#l694"></a>
<span id="l695"></span><a href="#l695"></a>
<span id="l696">    public static final ObjectIdentifier SHA384_oid =</span><a href="#l696"></a>
<span id="l697">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 2});</span><a href="#l697"></a>
<span id="l698"></span><a href="#l698"></a>
<span id="l699">    public static final ObjectIdentifier SHA512_oid =</span><a href="#l699"></a>
<span id="l700">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 3});</span><a href="#l700"></a>
<span id="l701"></span><a href="#l701"></a>
<span id="l702">    public static final ObjectIdentifier SHA512_224_oid =</span><a href="#l702"></a>
<span id="l703">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 5});</span><a href="#l703"></a>
<span id="l704"></span><a href="#l704"></a>
<span id="l705">    public static final ObjectIdentifier SHA512_256_oid =</span><a href="#l705"></a>
<span id="l706">    ObjectIdentifier.newInternal(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 6});</span><a href="#l706"></a>
<span id="l707"></span><a href="#l707"></a>
<span id="l708">    /*</span><a href="#l708"></a>
<span id="l709">     * COMMON PUBLIC KEY TYPES</span><a href="#l709"></a>
<span id="l710">     */</span><a href="#l710"></a>
<span id="l711">    private static final int DH_data[] = { 1, 2, 840, 113549, 1, 3, 1 };</span><a href="#l711"></a>
<span id="l712">    private static final int DH_PKIX_data[] = { 1, 2, 840, 10046, 2, 1 };</span><a href="#l712"></a>
<span id="l713">    private static final int DSA_OIW_data[] = { 1, 3, 14, 3, 2, 12 };</span><a href="#l713"></a>
<span id="l714">    private static final int DSA_PKIX_data[] = { 1, 2, 840, 10040, 4, 1 };</span><a href="#l714"></a>
<span id="l715">    private static final int RSA_data[] = { 2, 5, 8, 1, 1 };</span><a href="#l715"></a>
<span id="l716"></span><a href="#l716"></a>
<span id="l717">    public static final ObjectIdentifier DH_oid;</span><a href="#l717"></a>
<span id="l718">    public static final ObjectIdentifier DH_PKIX_oid;</span><a href="#l718"></a>
<span id="l719">    public static final ObjectIdentifier DSA_oid;</span><a href="#l719"></a>
<span id="l720">    public static final ObjectIdentifier DSA_OIW_oid;</span><a href="#l720"></a>
<span id="l721">    public static final ObjectIdentifier EC_oid = oid(1, 2, 840, 10045, 2, 1);</span><a href="#l721"></a>
<span id="l722">    public static final ObjectIdentifier ECDH_oid = oid(1, 3, 132, 1, 12);</span><a href="#l722"></a>
<span id="l723">    public static final ObjectIdentifier RSA_oid;</span><a href="#l723"></a>
<span id="l724">    public static final ObjectIdentifier RSAEncryption_oid =</span><a href="#l724"></a>
<span id="l725">                                            oid(1, 2, 840, 113549, 1, 1, 1);</span><a href="#l725"></a>
<span id="l726">    public static final ObjectIdentifier RSAES_OAEP_oid =</span><a href="#l726"></a>
<span id="l727">                                            oid(1, 2, 840, 113549, 1, 1, 7);</span><a href="#l727"></a>
<span id="l728">    public static final ObjectIdentifier mgf1_oid =</span><a href="#l728"></a>
<span id="l729">                                            oid(1, 2, 840, 113549, 1, 1, 8);</span><a href="#l729"></a>
<span id="l730">    public static final ObjectIdentifier RSASSA_PSS_oid =</span><a href="#l730"></a>
<span id="l731">                                            oid(1, 2, 840, 113549, 1, 1, 10);</span><a href="#l731"></a>
<span id="l732"></span><a href="#l732"></a>
<span id="l733">    /*</span><a href="#l733"></a>
<span id="l734">     * COMMON SECRET KEY TYPES</span><a href="#l734"></a>
<span id="l735">     */</span><a href="#l735"></a>
<span id="l736">    public static final ObjectIdentifier AES_oid =</span><a href="#l736"></a>
<span id="l737">                                            oid(2, 16, 840, 1, 101, 3, 4, 1);</span><a href="#l737"></a>
<span id="l738"></span><a href="#l738"></a>
<span id="l739">    /*</span><a href="#l739"></a>
<span id="l740">     * COMMON SIGNATURE ALGORITHMS</span><a href="#l740"></a>
<span id="l741">     */</span><a href="#l741"></a>
<span id="l742">    private static final int md2WithRSAEncryption_data[] =</span><a href="#l742"></a>
<span id="l743">                                       { 1, 2, 840, 113549, 1, 1, 2 };</span><a href="#l743"></a>
<span id="l744">    private static final int md5WithRSAEncryption_data[] =</span><a href="#l744"></a>
<span id="l745">                                       { 1, 2, 840, 113549, 1, 1, 4 };</span><a href="#l745"></a>
<span id="l746">    private static final int sha1WithRSAEncryption_data[] =</span><a href="#l746"></a>
<span id="l747">                                       { 1, 2, 840, 113549, 1, 1, 5 };</span><a href="#l747"></a>
<span id="l748">    private static final int sha1WithRSAEncryption_OIW_data[] =</span><a href="#l748"></a>
<span id="l749">                                       { 1, 3, 14, 3, 2, 29 };</span><a href="#l749"></a>
<span id="l750">    private static final int sha224WithRSAEncryption_data[] =</span><a href="#l750"></a>
<span id="l751">                                       { 1, 2, 840, 113549, 1, 1, 14 };</span><a href="#l751"></a>
<span id="l752">    private static final int sha256WithRSAEncryption_data[] =</span><a href="#l752"></a>
<span id="l753">                                       { 1, 2, 840, 113549, 1, 1, 11 };</span><a href="#l753"></a>
<span id="l754">    private static final int sha384WithRSAEncryption_data[] =</span><a href="#l754"></a>
<span id="l755">                                       { 1, 2, 840, 113549, 1, 1, 12 };</span><a href="#l755"></a>
<span id="l756">    private static final int sha512WithRSAEncryption_data[] =</span><a href="#l756"></a>
<span id="l757">                                       { 1, 2, 840, 113549, 1, 1, 13 };</span><a href="#l757"></a>
<span id="l758"></span><a href="#l758"></a>
<span id="l759">    private static final int shaWithDSA_OIW_data[] =</span><a href="#l759"></a>
<span id="l760">                                       { 1, 3, 14, 3, 2, 13 };</span><a href="#l760"></a>
<span id="l761">    private static final int sha1WithDSA_OIW_data[] =</span><a href="#l761"></a>
<span id="l762">                                       { 1, 3, 14, 3, 2, 27 };</span><a href="#l762"></a>
<span id="l763">    private static final int dsaWithSHA1_PKIX_data[] =</span><a href="#l763"></a>
<span id="l764">                                       { 1, 2, 840, 10040, 4, 3 };</span><a href="#l764"></a>
<span id="l765"></span><a href="#l765"></a>
<span id="l766">    public static final ObjectIdentifier md2WithRSAEncryption_oid;</span><a href="#l766"></a>
<span id="l767">    public static final ObjectIdentifier md5WithRSAEncryption_oid;</span><a href="#l767"></a>
<span id="l768">    public static final ObjectIdentifier sha1WithRSAEncryption_oid;</span><a href="#l768"></a>
<span id="l769">    public static final ObjectIdentifier sha1WithRSAEncryption_OIW_oid;</span><a href="#l769"></a>
<span id="l770">    public static final ObjectIdentifier sha224WithRSAEncryption_oid;</span><a href="#l770"></a>
<span id="l771">    public static final ObjectIdentifier sha256WithRSAEncryption_oid;</span><a href="#l771"></a>
<span id="l772">    public static final ObjectIdentifier sha384WithRSAEncryption_oid;</span><a href="#l772"></a>
<span id="l773">    public static final ObjectIdentifier sha512WithRSAEncryption_oid;</span><a href="#l773"></a>
<span id="l774">    public static final ObjectIdentifier sha512_224WithRSAEncryption_oid =</span><a href="#l774"></a>
<span id="l775">                                            oid(1, 2, 840, 113549, 1, 1, 15);</span><a href="#l775"></a>
<span id="l776">    public static final ObjectIdentifier sha512_256WithRSAEncryption_oid =</span><a href="#l776"></a>
<span id="l777">                                            oid(1, 2, 840, 113549, 1, 1, 16);;</span><a href="#l777"></a>
<span id="l778"></span><a href="#l778"></a>
<span id="l779">    public static final ObjectIdentifier shaWithDSA_OIW_oid;</span><a href="#l779"></a>
<span id="l780">    public static final ObjectIdentifier sha1WithDSA_OIW_oid;</span><a href="#l780"></a>
<span id="l781">    public static final ObjectIdentifier sha1WithDSA_oid;</span><a href="#l781"></a>
<span id="l782">    public static final ObjectIdentifier sha224WithDSA_oid =</span><a href="#l782"></a>
<span id="l783">                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 1);</span><a href="#l783"></a>
<span id="l784">    public static final ObjectIdentifier sha256WithDSA_oid =</span><a href="#l784"></a>
<span id="l785">                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 2);</span><a href="#l785"></a>
<span id="l786"></span><a href="#l786"></a>
<span id="l787">    public static final ObjectIdentifier sha1WithECDSA_oid =</span><a href="#l787"></a>
<span id="l788">                                            oid(1, 2, 840, 10045, 4, 1);</span><a href="#l788"></a>
<span id="l789">    public static final ObjectIdentifier sha224WithECDSA_oid =</span><a href="#l789"></a>
<span id="l790">                                            oid(1, 2, 840, 10045, 4, 3, 1);</span><a href="#l790"></a>
<span id="l791">    public static final ObjectIdentifier sha256WithECDSA_oid =</span><a href="#l791"></a>
<span id="l792">                                            oid(1, 2, 840, 10045, 4, 3, 2);</span><a href="#l792"></a>
<span id="l793">    public static final ObjectIdentifier sha384WithECDSA_oid =</span><a href="#l793"></a>
<span id="l794">                                            oid(1, 2, 840, 10045, 4, 3, 3);</span><a href="#l794"></a>
<span id="l795">    public static final ObjectIdentifier sha512WithECDSA_oid =</span><a href="#l795"></a>
<span id="l796">                                            oid(1, 2, 840, 10045, 4, 3, 4);</span><a href="#l796"></a>
<span id="l797">    public static final ObjectIdentifier specifiedWithECDSA_oid =</span><a href="#l797"></a>
<span id="l798">                                            oid(1, 2, 840, 10045, 4, 3);</span><a href="#l798"></a>
<span id="l799"></span><a href="#l799"></a>
<span id="l800">    /**</span><a href="#l800"></a>
<span id="l801">     * Algorithm ID for the PBE encryption algorithms from PKCS#5 and</span><a href="#l801"></a>
<span id="l802">     * PKCS#12.</span><a href="#l802"></a>
<span id="l803">     */</span><a href="#l803"></a>
<span id="l804">    public static final ObjectIdentifier pbeWithMD5AndDES_oid =</span><a href="#l804"></a>
<span id="l805">        ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 5, 3});</span><a href="#l805"></a>
<span id="l806">    public static final ObjectIdentifier pbeWithMD5AndRC2_oid =</span><a href="#l806"></a>
<span id="l807">        ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 1, 5, 6});</span><a href="#l807"></a>
<span id="l808">    public static final ObjectIdentifier pbeWithSHA1AndDES_oid =</span><a href="#l808"></a>
<span id="l809">        ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 1, 5, 10});</span><a href="#l809"></a>
<span id="l810">    public static final ObjectIdentifier pbeWithSHA1AndRC2_oid =</span><a href="#l810"></a>
<span id="l811">        ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 1, 5, 11});</span><a href="#l811"></a>
<span id="l812">    public static ObjectIdentifier pbeWithSHA1AndDESede_oid =</span><a href="#l812"></a>
<span id="l813">        ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 1, 12, 1, 3});</span><a href="#l813"></a>
<span id="l814">    public static ObjectIdentifier pbeWithSHA1AndRC2_40_oid =</span><a href="#l814"></a>
<span id="l815">        ObjectIdentifier.newInternal(new int[] {1, 2, 840, 113549, 1, 12, 1, 6});</span><a href="#l815"></a>
<span id="l816"></span><a href="#l816"></a>
<span id="l817">    static {</span><a href="#l817"></a>
<span id="l818">    /*</span><a href="#l818"></a>
<span id="l819">     * Note the preferred OIDs are named simply with no &quot;OIW&quot; or</span><a href="#l819"></a>
<span id="l820">     * &quot;PKIX&quot; in them, even though they may point to data from these</span><a href="#l820"></a>
<span id="l821">     * specs; e.g. SHA_oid, DH_oid, DSA_oid, SHA1WithDSA_oid...</span><a href="#l821"></a>
<span id="l822">     */</span><a href="#l822"></a>
<span id="l823">    /**</span><a href="#l823"></a>
<span id="l824">     * Algorithm ID for Diffie Hellman Key agreement, from PKCS #3.</span><a href="#l824"></a>
<span id="l825">     * Parameters include public values P and G, and may optionally specify</span><a href="#l825"></a>
<span id="l826">     * the length of the private key X.  Alternatively, algorithm parameters</span><a href="#l826"></a>
<span id="l827">     * may be derived from another source such as a Certificate Authority's</span><a href="#l827"></a>
<span id="l828">     * certificate.</span><a href="#l828"></a>
<span id="l829">     * OID = 1.2.840.113549.1.3.1</span><a href="#l829"></a>
<span id="l830">     */</span><a href="#l830"></a>
<span id="l831">        DH_oid = ObjectIdentifier.newInternal(DH_data);</span><a href="#l831"></a>
<span id="l832"></span><a href="#l832"></a>
<span id="l833">    /**</span><a href="#l833"></a>
<span id="l834">     * Algorithm ID for the Diffie Hellman Key Agreement (DH), from RFC 3279.</span><a href="#l834"></a>
<span id="l835">     * Parameters may include public values P and G.</span><a href="#l835"></a>
<span id="l836">     * OID = 1.2.840.10046.2.1</span><a href="#l836"></a>
<span id="l837">     */</span><a href="#l837"></a>
<span id="l838">        DH_PKIX_oid = ObjectIdentifier.newInternal(DH_PKIX_data);</span><a href="#l838"></a>
<span id="l839"></span><a href="#l839"></a>
<span id="l840">    /**</span><a href="#l840"></a>
<span id="l841">     * Algorithm ID for the Digital Signing Algorithm (DSA), from the</span><a href="#l841"></a>
<span id="l842">     * NIST OIW Stable Agreements part 12.</span><a href="#l842"></a>
<span id="l843">     * Parameters may include public values P, Q, and G; or these may be</span><a href="#l843"></a>
<span id="l844">     * derived from</span><a href="#l844"></a>
<span id="l845">     * another source such as a Certificate Authority's certificate.</span><a href="#l845"></a>
<span id="l846">     * OID = 1.3.14.3.2.12</span><a href="#l846"></a>
<span id="l847">     */</span><a href="#l847"></a>
<span id="l848">        DSA_OIW_oid = ObjectIdentifier.newInternal(DSA_OIW_data);</span><a href="#l848"></a>
<span id="l849"></span><a href="#l849"></a>
<span id="l850">    /**</span><a href="#l850"></a>
<span id="l851">     * Algorithm ID for the Digital Signing Algorithm (DSA), from RFC 3279.</span><a href="#l851"></a>
<span id="l852">     * Parameters may include public values P, Q, and G; or these may be</span><a href="#l852"></a>
<span id="l853">     * derived from another source such as a Certificate Authority's</span><a href="#l853"></a>
<span id="l854">     * certificate.</span><a href="#l854"></a>
<span id="l855">     * OID = 1.2.840.10040.4.1</span><a href="#l855"></a>
<span id="l856">     */</span><a href="#l856"></a>
<span id="l857">        DSA_oid = ObjectIdentifier.newInternal(DSA_PKIX_data);</span><a href="#l857"></a>
<span id="l858"></span><a href="#l858"></a>
<span id="l859">    /**</span><a href="#l859"></a>
<span id="l860">     * Algorithm ID for RSA keys used for any purpose, as defined in X.509.</span><a href="#l860"></a>
<span id="l861">     * The algorithm parameter is a single value, the number of bits in the</span><a href="#l861"></a>
<span id="l862">     * public modulus.</span><a href="#l862"></a>
<span id="l863">     * OID = 2.5.8.1.1</span><a href="#l863"></a>
<span id="l864">     */</span><a href="#l864"></a>
<span id="l865">        RSA_oid = ObjectIdentifier.newInternal(RSA_data);</span><a href="#l865"></a>
<span id="l866"></span><a href="#l866"></a>
<span id="l867">    /**</span><a href="#l867"></a>
<span id="l868">     * Identifies a signing algorithm where an MD2 digest is encrypted</span><a href="#l868"></a>
<span id="l869">     * using an RSA private key; defined in PKCS #1.  Use of this</span><a href="#l869"></a>
<span id="l870">     * signing algorithm is discouraged due to MD2 vulnerabilities.</span><a href="#l870"></a>
<span id="l871">     * OID = 1.2.840.113549.1.1.2</span><a href="#l871"></a>
<span id="l872">     */</span><a href="#l872"></a>
<span id="l873">        md2WithRSAEncryption_oid =</span><a href="#l873"></a>
<span id="l874">            ObjectIdentifier.newInternal(md2WithRSAEncryption_data);</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">    /**</span><a href="#l876"></a>
<span id="l877">     * Identifies a signing algorithm where an MD5 digest is</span><a href="#l877"></a>
<span id="l878">     * encrypted using an RSA private key; defined in PKCS #1.</span><a href="#l878"></a>
<span id="l879">     * OID = 1.2.840.113549.1.1.4</span><a href="#l879"></a>
<span id="l880">     */</span><a href="#l880"></a>
<span id="l881">        md5WithRSAEncryption_oid =</span><a href="#l881"></a>
<span id="l882">            ObjectIdentifier.newInternal(md5WithRSAEncryption_data);</span><a href="#l882"></a>
<span id="l883"></span><a href="#l883"></a>
<span id="l884">    /**</span><a href="#l884"></a>
<span id="l885">     * Identifies a signing algorithm where a SHA1 digest is</span><a href="#l885"></a>
<span id="l886">     * encrypted using an RSA private key; defined by RSA DSI.</span><a href="#l886"></a>
<span id="l887">     * OID = 1.2.840.113549.1.1.5</span><a href="#l887"></a>
<span id="l888">     */</span><a href="#l888"></a>
<span id="l889">        sha1WithRSAEncryption_oid =</span><a href="#l889"></a>
<span id="l890">            ObjectIdentifier.newInternal(sha1WithRSAEncryption_data);</span><a href="#l890"></a>
<span id="l891"></span><a href="#l891"></a>
<span id="l892">    /**</span><a href="#l892"></a>
<span id="l893">     * Identifies a signing algorithm where a SHA1 digest is</span><a href="#l893"></a>
<span id="l894">     * encrypted using an RSA private key; defined in NIST OIW.</span><a href="#l894"></a>
<span id="l895">     * OID = 1.3.14.3.2.29</span><a href="#l895"></a>
<span id="l896">     */</span><a href="#l896"></a>
<span id="l897">        sha1WithRSAEncryption_OIW_oid =</span><a href="#l897"></a>
<span id="l898">            ObjectIdentifier.newInternal(sha1WithRSAEncryption_OIW_data);</span><a href="#l898"></a>
<span id="l899"></span><a href="#l899"></a>
<span id="l900">    /**</span><a href="#l900"></a>
<span id="l901">     * Identifies a signing algorithm where a SHA224 digest is</span><a href="#l901"></a>
<span id="l902">     * encrypted using an RSA private key; defined by PKCS #1.</span><a href="#l902"></a>
<span id="l903">     * OID = 1.2.840.113549.1.1.14</span><a href="#l903"></a>
<span id="l904">     */</span><a href="#l904"></a>
<span id="l905">        sha224WithRSAEncryption_oid =</span><a href="#l905"></a>
<span id="l906">            ObjectIdentifier.newInternal(sha224WithRSAEncryption_data);</span><a href="#l906"></a>
<span id="l907"></span><a href="#l907"></a>
<span id="l908">    /**</span><a href="#l908"></a>
<span id="l909">     * Identifies a signing algorithm where a SHA256 digest is</span><a href="#l909"></a>
<span id="l910">     * encrypted using an RSA private key; defined by PKCS #1.</span><a href="#l910"></a>
<span id="l911">     * OID = 1.2.840.113549.1.1.11</span><a href="#l911"></a>
<span id="l912">     */</span><a href="#l912"></a>
<span id="l913">        sha256WithRSAEncryption_oid =</span><a href="#l913"></a>
<span id="l914">            ObjectIdentifier.newInternal(sha256WithRSAEncryption_data);</span><a href="#l914"></a>
<span id="l915"></span><a href="#l915"></a>
<span id="l916">    /**</span><a href="#l916"></a>
<span id="l917">     * Identifies a signing algorithm where a SHA384 digest is</span><a href="#l917"></a>
<span id="l918">     * encrypted using an RSA private key; defined by PKCS #1.</span><a href="#l918"></a>
<span id="l919">     * OID = 1.2.840.113549.1.1.12</span><a href="#l919"></a>
<span id="l920">     */</span><a href="#l920"></a>
<span id="l921">        sha384WithRSAEncryption_oid =</span><a href="#l921"></a>
<span id="l922">            ObjectIdentifier.newInternal(sha384WithRSAEncryption_data);</span><a href="#l922"></a>
<span id="l923"></span><a href="#l923"></a>
<span id="l924">    /**</span><a href="#l924"></a>
<span id="l925">     * Identifies a signing algorithm where a SHA512 digest is</span><a href="#l925"></a>
<span id="l926">     * encrypted using an RSA private key; defined by PKCS #1.</span><a href="#l926"></a>
<span id="l927">     * OID = 1.2.840.113549.1.1.13</span><a href="#l927"></a>
<span id="l928">     */</span><a href="#l928"></a>
<span id="l929">        sha512WithRSAEncryption_oid =</span><a href="#l929"></a>
<span id="l930">            ObjectIdentifier.newInternal(sha512WithRSAEncryption_data);</span><a href="#l930"></a>
<span id="l931"></span><a href="#l931"></a>
<span id="l932">    /**</span><a href="#l932"></a>
<span id="l933">     * Identifies the FIPS 186 &quot;Digital Signature Standard&quot; (DSS), where a</span><a href="#l933"></a>
<span id="l934">     * SHA digest is signed using the Digital Signing Algorithm (DSA).</span><a href="#l934"></a>
<span id="l935">     * This should not be used.</span><a href="#l935"></a>
<span id="l936">     * OID = 1.3.14.3.2.13</span><a href="#l936"></a>
<span id="l937">     */</span><a href="#l937"></a>
<span id="l938">        shaWithDSA_OIW_oid = ObjectIdentifier.newInternal(shaWithDSA_OIW_data);</span><a href="#l938"></a>
<span id="l939"></span><a href="#l939"></a>
<span id="l940">    /**</span><a href="#l940"></a>
<span id="l941">     * Identifies the FIPS 186 &quot;Digital Signature Standard&quot; (DSS), where a</span><a href="#l941"></a>
<span id="l942">     * SHA1 digest is signed using the Digital Signing Algorithm (DSA).</span><a href="#l942"></a>
<span id="l943">     * OID = 1.3.14.3.2.27</span><a href="#l943"></a>
<span id="l944">     */</span><a href="#l944"></a>
<span id="l945">        sha1WithDSA_OIW_oid = ObjectIdentifier.newInternal(sha1WithDSA_OIW_data);</span><a href="#l945"></a>
<span id="l946"></span><a href="#l946"></a>
<span id="l947">    /**</span><a href="#l947"></a>
<span id="l948">     * Identifies the FIPS 186 &quot;Digital Signature Standard&quot; (DSS), where a</span><a href="#l948"></a>
<span id="l949">     * SHA1 digest is signed using the Digital Signing Algorithm (DSA).</span><a href="#l949"></a>
<span id="l950">     * OID = 1.2.840.10040.4.3</span><a href="#l950"></a>
<span id="l951">     */</span><a href="#l951"></a>
<span id="l952">        sha1WithDSA_oid = ObjectIdentifier.newInternal(dsaWithSHA1_PKIX_data);</span><a href="#l952"></a>
<span id="l953"></span><a href="#l953"></a>
<span id="l954">        nameTable = new HashMap&lt;ObjectIdentifier,String&gt;();</span><a href="#l954"></a>
<span id="l955">        nameTable.put(MD5_oid, &quot;MD5&quot;);</span><a href="#l955"></a>
<span id="l956">        nameTable.put(MD2_oid, &quot;MD2&quot;);</span><a href="#l956"></a>
<span id="l957">        nameTable.put(SHA_oid, &quot;SHA-1&quot;);</span><a href="#l957"></a>
<span id="l958">        nameTable.put(SHA224_oid, &quot;SHA-224&quot;);</span><a href="#l958"></a>
<span id="l959">        nameTable.put(SHA256_oid, &quot;SHA-256&quot;);</span><a href="#l959"></a>
<span id="l960">        nameTable.put(SHA384_oid, &quot;SHA-384&quot;);</span><a href="#l960"></a>
<span id="l961">        nameTable.put(SHA512_oid, &quot;SHA-512&quot;);</span><a href="#l961"></a>
<span id="l962">        nameTable.put(SHA512_224_oid, &quot;SHA-512/224&quot;);</span><a href="#l962"></a>
<span id="l963">        nameTable.put(SHA512_256_oid, &quot;SHA-512/256&quot;);</span><a href="#l963"></a>
<span id="l964">        nameTable.put(RSAEncryption_oid, &quot;RSA&quot;);</span><a href="#l964"></a>
<span id="l965">        nameTable.put(RSA_oid, &quot;RSA&quot;);</span><a href="#l965"></a>
<span id="l966">        nameTable.put(DH_oid, &quot;Diffie-Hellman&quot;);</span><a href="#l966"></a>
<span id="l967">        nameTable.put(DH_PKIX_oid, &quot;Diffie-Hellman&quot;);</span><a href="#l967"></a>
<span id="l968">        nameTable.put(DSA_oid, &quot;DSA&quot;);</span><a href="#l968"></a>
<span id="l969">        nameTable.put(DSA_OIW_oid, &quot;DSA&quot;);</span><a href="#l969"></a>
<span id="l970">        nameTable.put(EC_oid, &quot;EC&quot;);</span><a href="#l970"></a>
<span id="l971">        nameTable.put(ECDH_oid, &quot;ECDH&quot;);</span><a href="#l971"></a>
<span id="l972"></span><a href="#l972"></a>
<span id="l973">        nameTable.put(AES_oid, &quot;AES&quot;);</span><a href="#l973"></a>
<span id="l974"></span><a href="#l974"></a>
<span id="l975">        nameTable.put(sha1WithECDSA_oid, &quot;SHA1withECDSA&quot;);</span><a href="#l975"></a>
<span id="l976">        nameTable.put(sha224WithECDSA_oid, &quot;SHA224withECDSA&quot;);</span><a href="#l976"></a>
<span id="l977">        nameTable.put(sha256WithECDSA_oid, &quot;SHA256withECDSA&quot;);</span><a href="#l977"></a>
<span id="l978">        nameTable.put(sha384WithECDSA_oid, &quot;SHA384withECDSA&quot;);</span><a href="#l978"></a>
<span id="l979">        nameTable.put(sha512WithECDSA_oid, &quot;SHA512withECDSA&quot;);</span><a href="#l979"></a>
<span id="l980">        nameTable.put(md5WithRSAEncryption_oid, &quot;MD5withRSA&quot;);</span><a href="#l980"></a>
<span id="l981">        nameTable.put(md2WithRSAEncryption_oid, &quot;MD2withRSA&quot;);</span><a href="#l981"></a>
<span id="l982">        nameTable.put(sha1WithDSA_oid, &quot;SHA1withDSA&quot;);</span><a href="#l982"></a>
<span id="l983">        nameTable.put(sha1WithDSA_OIW_oid, &quot;SHA1withDSA&quot;);</span><a href="#l983"></a>
<span id="l984">        nameTable.put(shaWithDSA_OIW_oid, &quot;SHA1withDSA&quot;);</span><a href="#l984"></a>
<span id="l985">        nameTable.put(sha224WithDSA_oid, &quot;SHA224withDSA&quot;);</span><a href="#l985"></a>
<span id="l986">        nameTable.put(sha256WithDSA_oid, &quot;SHA256withDSA&quot;);</span><a href="#l986"></a>
<span id="l987">        nameTable.put(sha1WithRSAEncryption_oid, &quot;SHA1withRSA&quot;);</span><a href="#l987"></a>
<span id="l988">        nameTable.put(sha1WithRSAEncryption_OIW_oid, &quot;SHA1withRSA&quot;);</span><a href="#l988"></a>
<span id="l989">        nameTable.put(sha224WithRSAEncryption_oid, &quot;SHA224withRSA&quot;);</span><a href="#l989"></a>
<span id="l990">        nameTable.put(sha256WithRSAEncryption_oid, &quot;SHA256withRSA&quot;);</span><a href="#l990"></a>
<span id="l991">        nameTable.put(sha384WithRSAEncryption_oid, &quot;SHA384withRSA&quot;);</span><a href="#l991"></a>
<span id="l992">        nameTable.put(sha512WithRSAEncryption_oid, &quot;SHA512withRSA&quot;);</span><a href="#l992"></a>
<span id="l993">        nameTable.put(sha512_224WithRSAEncryption_oid, &quot;SHA512/224withRSA&quot;);</span><a href="#l993"></a>
<span id="l994">        nameTable.put(sha512_256WithRSAEncryption_oid, &quot;SHA512/256withRSA&quot;);</span><a href="#l994"></a>
<span id="l995">        nameTable.put(RSASSA_PSS_oid, &quot;RSASSA-PSS&quot;);</span><a href="#l995"></a>
<span id="l996">        nameTable.put(RSAES_OAEP_oid, &quot;RSAES-OAEP&quot;);</span><a href="#l996"></a>
<span id="l997"></span><a href="#l997"></a>
<span id="l998">        nameTable.put(pbeWithMD5AndDES_oid, &quot;PBEWithMD5AndDES&quot;);</span><a href="#l998"></a>
<span id="l999">        nameTable.put(pbeWithMD5AndRC2_oid, &quot;PBEWithMD5AndRC2&quot;);</span><a href="#l999"></a>
<span id="l1000">        nameTable.put(pbeWithSHA1AndDES_oid, &quot;PBEWithSHA1AndDES&quot;);</span><a href="#l1000"></a>
<span id="l1001">        nameTable.put(pbeWithSHA1AndRC2_oid, &quot;PBEWithSHA1AndRC2&quot;);</span><a href="#l1001"></a>
<span id="l1002">        nameTable.put(pbeWithSHA1AndDESede_oid, &quot;PBEWithSHA1AndDESede&quot;);</span><a href="#l1002"></a>
<span id="l1003">        nameTable.put(pbeWithSHA1AndRC2_40_oid, &quot;PBEWithSHA1AndRC2_40&quot;);</span><a href="#l1003"></a>
<span id="l1004">    }</span><a href="#l1004"></a>
<span id="l1005"></span><a href="#l1005"></a>
<span id="l1006">    /**</span><a href="#l1006"></a>
<span id="l1007">     * Creates a signature algorithm name from a digest algorithm</span><a href="#l1007"></a>
<span id="l1008">     * name and a encryption algorithm name.</span><a href="#l1008"></a>
<span id="l1009">     */</span><a href="#l1009"></a>
<span id="l1010">    public static String makeSigAlg(String digAlg, String encAlg) {</span><a href="#l1010"></a>
<span id="l1011">        digAlg = digAlg.replace(&quot;-&quot;, &quot;&quot;);</span><a href="#l1011"></a>
<span id="l1012">        if (encAlg.equalsIgnoreCase(&quot;EC&quot;)) encAlg = &quot;ECDSA&quot;;</span><a href="#l1012"></a>
<span id="l1013"></span><a href="#l1013"></a>
<span id="l1014">        return digAlg + &quot;with&quot; + encAlg;</span><a href="#l1014"></a>
<span id="l1015">    }</span><a href="#l1015"></a>
<span id="l1016"></span><a href="#l1016"></a>
<span id="l1017">    /**</span><a href="#l1017"></a>
<span id="l1018">     * Extracts the encryption algorithm name from a signature</span><a href="#l1018"></a>
<span id="l1019">     * algorithm name.</span><a href="#l1019"></a>
<span id="l1020">      */</span><a href="#l1020"></a>
<span id="l1021">    public static String getEncAlgFromSigAlg(String signatureAlgorithm) {</span><a href="#l1021"></a>
<span id="l1022">        signatureAlgorithm = signatureAlgorithm.toUpperCase(Locale.ENGLISH);</span><a href="#l1022"></a>
<span id="l1023">        int with = signatureAlgorithm.indexOf(&quot;WITH&quot;);</span><a href="#l1023"></a>
<span id="l1024">        String keyAlgorithm = null;</span><a href="#l1024"></a>
<span id="l1025">        if (with &gt; 0) {</span><a href="#l1025"></a>
<span id="l1026">            int and = signatureAlgorithm.indexOf(&quot;AND&quot;, with + 4);</span><a href="#l1026"></a>
<span id="l1027">            if (and &gt; 0) {</span><a href="#l1027"></a>
<span id="l1028">                keyAlgorithm = signatureAlgorithm.substring(with + 4, and);</span><a href="#l1028"></a>
<span id="l1029">            } else {</span><a href="#l1029"></a>
<span id="l1030">                keyAlgorithm = signatureAlgorithm.substring(with + 4);</span><a href="#l1030"></a>
<span id="l1031">            }</span><a href="#l1031"></a>
<span id="l1032">            if (keyAlgorithm.equalsIgnoreCase(&quot;ECDSA&quot;)) {</span><a href="#l1032"></a>
<span id="l1033">                keyAlgorithm = &quot;EC&quot;;</span><a href="#l1033"></a>
<span id="l1034">            }</span><a href="#l1034"></a>
<span id="l1035">        }</span><a href="#l1035"></a>
<span id="l1036">        return keyAlgorithm;</span><a href="#l1036"></a>
<span id="l1037">    }</span><a href="#l1037"></a>
<span id="l1038"></span><a href="#l1038"></a>
<span id="l1039">    /**</span><a href="#l1039"></a>
<span id="l1040">     * Extracts the digest algorithm name from a signature</span><a href="#l1040"></a>
<span id="l1041">     * algorithm name.</span><a href="#l1041"></a>
<span id="l1042">      */</span><a href="#l1042"></a>
<span id="l1043">    public static String getDigAlgFromSigAlg(String signatureAlgorithm) {</span><a href="#l1043"></a>
<span id="l1044">        signatureAlgorithm = signatureAlgorithm.toUpperCase(Locale.ENGLISH);</span><a href="#l1044"></a>
<span id="l1045">        int with = signatureAlgorithm.indexOf(&quot;WITH&quot;);</span><a href="#l1045"></a>
<span id="l1046">        if (with &gt; 0) {</span><a href="#l1046"></a>
<span id="l1047">            return signatureAlgorithm.substring(0, with);</span><a href="#l1047"></a>
<span id="l1048">        }</span><a href="#l1048"></a>
<span id="l1049">        return null;</span><a href="#l1049"></a>
<span id="l1050">    }</span><a href="#l1050"></a>
<span id="l1051"></span><a href="#l1051"></a>
<span id="l1052">    // Most commonly used PSSParameterSpec and AlgorithmId</span><a href="#l1052"></a>
<span id="l1053">    private static class PSSParamsHolder {</span><a href="#l1053"></a>
<span id="l1054"></span><a href="#l1054"></a>
<span id="l1055">        final static PSSParameterSpec PSS_256_SPEC = new PSSParameterSpec(</span><a href="#l1055"></a>
<span id="l1056">                &quot;SHA-256&quot;, &quot;MGF1&quot;,</span><a href="#l1056"></a>
<span id="l1057">                new MGF1ParameterSpec(&quot;SHA-256&quot;),</span><a href="#l1057"></a>
<span id="l1058">                32, PSSParameterSpec.TRAILER_FIELD_BC);</span><a href="#l1058"></a>
<span id="l1059">        final static PSSParameterSpec PSS_384_SPEC = new PSSParameterSpec(</span><a href="#l1059"></a>
<span id="l1060">                &quot;SHA-384&quot;, &quot;MGF1&quot;,</span><a href="#l1060"></a>
<span id="l1061">                new MGF1ParameterSpec(&quot;SHA-384&quot;),</span><a href="#l1061"></a>
<span id="l1062">                48, PSSParameterSpec.TRAILER_FIELD_BC);</span><a href="#l1062"></a>
<span id="l1063">        final static PSSParameterSpec PSS_512_SPEC = new PSSParameterSpec(</span><a href="#l1063"></a>
<span id="l1064">                &quot;SHA-512&quot;, &quot;MGF1&quot;,</span><a href="#l1064"></a>
<span id="l1065">                new MGF1ParameterSpec(&quot;SHA-512&quot;),</span><a href="#l1065"></a>
<span id="l1066">                64, PSSParameterSpec.TRAILER_FIELD_BC);</span><a href="#l1066"></a>
<span id="l1067"></span><a href="#l1067"></a>
<span id="l1068">        final static AlgorithmId PSS_256_ID;</span><a href="#l1068"></a>
<span id="l1069">        final static AlgorithmId PSS_384_ID;</span><a href="#l1069"></a>
<span id="l1070">        final static AlgorithmId PSS_512_ID;</span><a href="#l1070"></a>
<span id="l1071"></span><a href="#l1071"></a>
<span id="l1072">        static {</span><a href="#l1072"></a>
<span id="l1073">            try {</span><a href="#l1073"></a>
<span id="l1074">                PSS_256_ID = new AlgorithmId(RSASSA_PSS_oid,</span><a href="#l1074"></a>
<span id="l1075">                        new DerValue(PSSParameters.getEncoded(PSS_256_SPEC)));</span><a href="#l1075"></a>
<span id="l1076">                PSS_384_ID = new AlgorithmId(RSASSA_PSS_oid,</span><a href="#l1076"></a>
<span id="l1077">                        new DerValue(PSSParameters.getEncoded(PSS_384_SPEC)));</span><a href="#l1077"></a>
<span id="l1078">                PSS_512_ID = new AlgorithmId(RSASSA_PSS_oid,</span><a href="#l1078"></a>
<span id="l1079">                        new DerValue(PSSParameters.getEncoded(PSS_512_SPEC)));</span><a href="#l1079"></a>
<span id="l1080">            } catch (IOException e) {</span><a href="#l1080"></a>
<span id="l1081">                throw new AssertionError(&quot;Should not happen&quot;, e);</span><a href="#l1081"></a>
<span id="l1082">            }</span><a href="#l1082"></a>
<span id="l1083">        }</span><a href="#l1083"></a>
<span id="l1084">    }</span><a href="#l1084"></a>
<span id="l1085"></span><a href="#l1085"></a>
<span id="l1086">    public static AlgorithmId getWithParameterSpec(String algName,</span><a href="#l1086"></a>
<span id="l1087">            AlgorithmParameterSpec spec) throws NoSuchAlgorithmException {</span><a href="#l1087"></a>
<span id="l1088"></span><a href="#l1088"></a>
<span id="l1089">        if (spec == null) {</span><a href="#l1089"></a>
<span id="l1090">            return AlgorithmId.get(algName);</span><a href="#l1090"></a>
<span id="l1091">        } else if (spec == PSSParamsHolder.PSS_256_SPEC) {</span><a href="#l1091"></a>
<span id="l1092">            return PSSParamsHolder.PSS_256_ID;</span><a href="#l1092"></a>
<span id="l1093">        } else if (spec == PSSParamsHolder.PSS_384_SPEC) {</span><a href="#l1093"></a>
<span id="l1094">            return PSSParamsHolder.PSS_384_ID;</span><a href="#l1094"></a>
<span id="l1095">        } else if (spec == PSSParamsHolder.PSS_512_SPEC) {</span><a href="#l1095"></a>
<span id="l1096">            return PSSParamsHolder.PSS_512_ID;</span><a href="#l1096"></a>
<span id="l1097">        } else {</span><a href="#l1097"></a>
<span id="l1098">            try {</span><a href="#l1098"></a>
<span id="l1099">                AlgorithmParameters result =</span><a href="#l1099"></a>
<span id="l1100">                        AlgorithmParameters.getInstance(algName);</span><a href="#l1100"></a>
<span id="l1101">                result.init(spec);</span><a href="#l1101"></a>
<span id="l1102">                return get(result);</span><a href="#l1102"></a>
<span id="l1103">            } catch (InvalidParameterSpecException | NoSuchAlgorithmException e) {</span><a href="#l1103"></a>
<span id="l1104">                throw new ProviderException(e);</span><a href="#l1104"></a>
<span id="l1105">            }</span><a href="#l1105"></a>
<span id="l1106">        }</span><a href="#l1106"></a>
<span id="l1107">    }</span><a href="#l1107"></a>
<span id="l1108"></span><a href="#l1108"></a>
<span id="l1109">    public static PSSParameterSpec getDefaultAlgorithmParameterSpec(</span><a href="#l1109"></a>
<span id="l1110">            String sigAlg, PrivateKey k) {</span><a href="#l1110"></a>
<span id="l1111">        if (sigAlg.equalsIgnoreCase(&quot;RSASSA-PSS&quot;)) {</span><a href="#l1111"></a>
<span id="l1112">            switch (ifcFfcStrength(KeyUtil.getKeySize(k))) {</span><a href="#l1112"></a>
<span id="l1113">                case &quot;SHA256&quot;:</span><a href="#l1113"></a>
<span id="l1114">                    return PSSParamsHolder.PSS_256_SPEC;</span><a href="#l1114"></a>
<span id="l1115">                case &quot;SHA384&quot;:</span><a href="#l1115"></a>
<span id="l1116">                    return PSSParamsHolder.PSS_384_SPEC;</span><a href="#l1116"></a>
<span id="l1117">                case &quot;SHA512&quot;:</span><a href="#l1117"></a>
<span id="l1118">                    return PSSParamsHolder.PSS_512_SPEC;</span><a href="#l1118"></a>
<span id="l1119">                default:</span><a href="#l1119"></a>
<span id="l1120">                    throw new AssertionError(&quot;Should not happen&quot;);</span><a href="#l1120"></a>
<span id="l1121">            }</span><a href="#l1121"></a>
<span id="l1122">        } else {</span><a href="#l1122"></a>
<span id="l1123">            return null;</span><a href="#l1123"></a>
<span id="l1124">        }</span><a href="#l1124"></a>
<span id="l1125">    }</span><a href="#l1125"></a>
<span id="l1126"></span><a href="#l1126"></a>
<span id="l1127">    // Same values for RSA and DSA (from 8056174)</span><a href="#l1127"></a>
<span id="l1128">    private static String ifcFfcStrength(int bitLength) {</span><a href="#l1128"></a>
<span id="l1129">        if (bitLength &gt; 7680) { // 256 bits</span><a href="#l1129"></a>
<span id="l1130">            return &quot;SHA512&quot;;</span><a href="#l1130"></a>
<span id="l1131">        } else if (bitLength &gt; 3072) {  // 192 bits</span><a href="#l1131"></a>
<span id="l1132">            return &quot;SHA384&quot;;</span><a href="#l1132"></a>
<span id="l1133">        } else  { // 128 bits and less</span><a href="#l1133"></a>
<span id="l1134">            return &quot;SHA256&quot;;</span><a href="#l1134"></a>
<span id="l1135">        }</span><a href="#l1135"></a>
<span id="l1136">    }</span><a href="#l1136"></a>
<span id="l1137">}</span><a href="#l1137"></a></pre>
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

