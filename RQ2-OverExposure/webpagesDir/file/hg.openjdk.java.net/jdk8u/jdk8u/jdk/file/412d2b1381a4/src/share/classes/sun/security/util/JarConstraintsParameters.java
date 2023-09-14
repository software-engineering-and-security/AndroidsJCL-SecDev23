<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/util/JarConstraintsParameters.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/util/JarConstraintsParameters.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/util/JarConstraintsParameters.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/util/JarConstraintsParameters.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/util/JarConstraintsParameters.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/util/JarConstraintsParameters.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/util/JarConstraintsParameters.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/util/JarConstraintsParameters.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"></td>
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
<span id="l2"> * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.security.CodeSigner;</span><a href="#l28"></a>
<span id="l29">import java.security.Key;</span><a href="#l29"></a>
<span id="l30">import java.security.Timestamp;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.CertPath;</span><a href="#l31"></a>
<span id="l32">import java.security.cert.X509Certificate;</span><a href="#l32"></a>
<span id="l33">import java.util.Date;</span><a href="#l33"></a>
<span id="l34">import java.util.HashSet;</span><a href="#l34"></a>
<span id="l35">import java.util.List;</span><a href="#l35"></a>
<span id="l36">import java.util.Set;</span><a href="#l36"></a>
<span id="l37">import sun.security.util.AnchorCertificates;</span><a href="#l37"></a>
<span id="l38">import sun.security.util.ConstraintsParameters;</span><a href="#l38"></a>
<span id="l39">import sun.security.validator.Validator;</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">/**</span><a href="#l41"></a>
<span id="l42"> * This class contains parameters for checking signed JARs against</span><a href="#l42"></a>
<span id="l43"> * constraints specified in the jdk.jar.disabledAlgorithms security</span><a href="#l43"></a>
<span id="l44"> * property.</span><a href="#l44"></a>
<span id="l45"> */</span><a href="#l45"></a>
<span id="l46">public class JarConstraintsParameters implements ConstraintsParameters {</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    // true if chain is anchored by a JDK root CA</span><a href="#l48"></a>
<span id="l49">    private boolean anchorIsJdkCA;</span><a href="#l49"></a>
<span id="l50">    private boolean anchorIsJdkCASet;</span><a href="#l50"></a>
<span id="l51">    // The timestamp of the signed JAR file, if timestamped</span><a href="#l51"></a>
<span id="l52">    private Date timestamp;</span><a href="#l52"></a>
<span id="l53">    // The keys of the signers</span><a href="#l53"></a>
<span id="l54">    private final Set&lt;Key&gt; keys;</span><a href="#l54"></a>
<span id="l55">    // The certs in the signers' chains that are issued by the trust anchor</span><a href="#l55"></a>
<span id="l56">    private final Set&lt;X509Certificate&gt; certsIssuedByAnchor;</span><a href="#l56"></a>
<span id="l57">    // The extended exception message</span><a href="#l57"></a>
<span id="l58">    private String message;</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">    /**</span><a href="#l60"></a>
<span id="l61">     * Create a JarConstraintsParameters.</span><a href="#l61"></a>
<span id="l62">     *</span><a href="#l62"></a>
<span id="l63">     * @param signers the CodeSigners that signed the JAR</span><a href="#l63"></a>
<span id="l64">     */</span><a href="#l64"></a>
<span id="l65">    public JarConstraintsParameters(CodeSigner[] signers) {</span><a href="#l65"></a>
<span id="l66">        this.keys = new HashSet&lt;&gt;();</span><a href="#l66"></a>
<span id="l67">        this.certsIssuedByAnchor = new HashSet&lt;&gt;();</span><a href="#l67"></a>
<span id="l68">        Date latestTimestamp = null;</span><a href="#l68"></a>
<span id="l69">        boolean skipTimestamp = false;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">        // Iterate over the signers and extract the keys, the latest</span><a href="#l71"></a>
<span id="l72">        // timestamp, and the last certificate of each chain which can be</span><a href="#l72"></a>
<span id="l73">        // used for checking if the signer's certificate chains back to a</span><a href="#l73"></a>
<span id="l74">        // JDK root CA</span><a href="#l74"></a>
<span id="l75">        for (CodeSigner signer : signers) {</span><a href="#l75"></a>
<span id="l76">            init(signer.getSignerCertPath());</span><a href="#l76"></a>
<span id="l77">            Timestamp timestamp = signer.getTimestamp();</span><a href="#l77"></a>
<span id="l78">            if (timestamp == null) {</span><a href="#l78"></a>
<span id="l79">                // this means one of the signers doesn't have a timestamp</span><a href="#l79"></a>
<span id="l80">                // and the JAR should be treated as if it isn't timestamped</span><a href="#l80"></a>
<span id="l81">                latestTimestamp = null;</span><a href="#l81"></a>
<span id="l82">                skipTimestamp = true;</span><a href="#l82"></a>
<span id="l83">            } else {</span><a href="#l83"></a>
<span id="l84">                // add the key and last cert of TSA too</span><a href="#l84"></a>
<span id="l85">                init(timestamp.getSignerCertPath());</span><a href="#l85"></a>
<span id="l86">                if (!skipTimestamp) {</span><a href="#l86"></a>
<span id="l87">                    Date timestampDate = timestamp.getTimestamp();</span><a href="#l87"></a>
<span id="l88">                    if (latestTimestamp == null) {</span><a href="#l88"></a>
<span id="l89">                        latestTimestamp = timestampDate;</span><a href="#l89"></a>
<span id="l90">                    } else {</span><a href="#l90"></a>
<span id="l91">                        if (latestTimestamp.before(timestampDate)) {</span><a href="#l91"></a>
<span id="l92">                            latestTimestamp = timestampDate;</span><a href="#l92"></a>
<span id="l93">                        }</span><a href="#l93"></a>
<span id="l94">                    }</span><a href="#l94"></a>
<span id="l95">                }</span><a href="#l95"></a>
<span id="l96">            }</span><a href="#l96"></a>
<span id="l97">        }</span><a href="#l97"></a>
<span id="l98">        this.timestamp = latestTimestamp;</span><a href="#l98"></a>
<span id="l99">    }</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    // extract last certificate and key from chain</span><a href="#l101"></a>
<span id="l102">    private void init(CertPath cp) {</span><a href="#l102"></a>
<span id="l103">        @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l103"></a>
<span id="l104">        List&lt;X509Certificate&gt; chain =</span><a href="#l104"></a>
<span id="l105">            (List&lt;X509Certificate&gt;)cp.getCertificates();</span><a href="#l105"></a>
<span id="l106">        if (!chain.isEmpty()) {</span><a href="#l106"></a>
<span id="l107">            this.certsIssuedByAnchor.add(chain.get(chain.size() - 1));</span><a href="#l107"></a>
<span id="l108">            this.keys.add(chain.get(0).getPublicKey());</span><a href="#l108"></a>
<span id="l109">        }</span><a href="#l109"></a>
<span id="l110">    }</span><a href="#l110"></a>
<span id="l111"></span><a href="#l111"></a>
<span id="l112">    @Override</span><a href="#l112"></a>
<span id="l113">    public String getVariant() {</span><a href="#l113"></a>
<span id="l114">        return Validator.VAR_GENERIC;</span><a href="#l114"></a>
<span id="l115">    }</span><a href="#l115"></a>
<span id="l116"></span><a href="#l116"></a>
<span id="l117">    /**</span><a href="#l117"></a>
<span id="l118">     * Since loading the cacerts keystore can be an expensive operation,</span><a href="#l118"></a>
<span id="l119">     * this is only performed if this method is called during a &quot;jdkCA&quot;</span><a href="#l119"></a>
<span id="l120">     * constraints check of a disabled algorithm, and the result is cached.</span><a href="#l120"></a>
<span id="l121">     *</span><a href="#l121"></a>
<span id="l122">     * @return true if at least one of the certificates are issued by a</span><a href="#l122"></a>
<span id="l123">     *              JDK root CA</span><a href="#l123"></a>
<span id="l124">     */</span><a href="#l124"></a>
<span id="l125">    @Override</span><a href="#l125"></a>
<span id="l126">    public boolean anchorIsJdkCA() {</span><a href="#l126"></a>
<span id="l127">        if (anchorIsJdkCASet) {</span><a href="#l127"></a>
<span id="l128">            return anchorIsJdkCA;</span><a href="#l128"></a>
<span id="l129">        }</span><a href="#l129"></a>
<span id="l130">        for (X509Certificate cert : certsIssuedByAnchor) {</span><a href="#l130"></a>
<span id="l131">            if (AnchorCertificates.issuerOf(cert)) {</span><a href="#l131"></a>
<span id="l132">                anchorIsJdkCA = true;</span><a href="#l132"></a>
<span id="l133">                break;</span><a href="#l133"></a>
<span id="l134">            }</span><a href="#l134"></a>
<span id="l135">        }</span><a href="#l135"></a>
<span id="l136">        anchorIsJdkCASet = true;</span><a href="#l136"></a>
<span id="l137">        return anchorIsJdkCA;</span><a href="#l137"></a>
<span id="l138">    }</span><a href="#l138"></a>
<span id="l139"></span><a href="#l139"></a>
<span id="l140">    @Override</span><a href="#l140"></a>
<span id="l141">    public Date getDate() {</span><a href="#l141"></a>
<span id="l142">        return timestamp;</span><a href="#l142"></a>
<span id="l143">    }</span><a href="#l143"></a>
<span id="l144"></span><a href="#l144"></a>
<span id="l145">    @Override</span><a href="#l145"></a>
<span id="l146">    public Set&lt;Key&gt; getKeys() {</span><a href="#l146"></a>
<span id="l147">        return keys;</span><a href="#l147"></a>
<span id="l148">    }</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">    /**</span><a href="#l150"></a>
<span id="l151">     * Sets the extended error message. Note: this should be used</span><a href="#l151"></a>
<span id="l152">     * carefully as it is specific to the attribute/entry/file being checked.</span><a href="#l152"></a>
<span id="l153">     *</span><a href="#l153"></a>
<span id="l154">     * @param file the name of the signature related file being verified</span><a href="#l154"></a>
<span id="l155">     * @param target the attribute containing the algorithm that is being</span><a href="#l155"></a>
<span id="l156">     *        checked</span><a href="#l156"></a>
<span id="l157">     */</span><a href="#l157"></a>
<span id="l158">    public void setExtendedExceptionMsg(String file, String target) {</span><a href="#l158"></a>
<span id="l159">        message = &quot; used&quot; + (target != null ? &quot; with &quot; + target : &quot;&quot;) +</span><a href="#l159"></a>
<span id="l160">                  &quot; in &quot; + file + &quot; file.&quot;;</span><a href="#l160"></a>
<span id="l161">    }</span><a href="#l161"></a>
<span id="l162"></span><a href="#l162"></a>
<span id="l163">    @Override</span><a href="#l163"></a>
<span id="l164">    public String extendedExceptionMsg() {</span><a href="#l164"></a>
<span id="l165">        return message;</span><a href="#l165"></a>
<span id="l166">    }</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">    @Override</span><a href="#l168"></a>
<span id="l169">    public String toString() {</span><a href="#l169"></a>
<span id="l170">        StringBuilder sb = new StringBuilder(&quot;[\n&quot;);</span><a href="#l170"></a>
<span id="l171">        sb.append(&quot;\n  Variant: &quot;).append(getVariant());</span><a href="#l171"></a>
<span id="l172">        sb.append(&quot;\n  Certs Issued by Anchor:&quot;);</span><a href="#l172"></a>
<span id="l173">        for (X509Certificate cert : certsIssuedByAnchor) {</span><a href="#l173"></a>
<span id="l174">            sb.append(&quot;\n    Cert Issuer: &quot;)</span><a href="#l174"></a>
<span id="l175">              .append(cert.getIssuerX500Principal());</span><a href="#l175"></a>
<span id="l176">            sb.append(&quot;\n    Cert Subject: &quot;)</span><a href="#l176"></a>
<span id="l177">              .append(cert.getSubjectX500Principal());</span><a href="#l177"></a>
<span id="l178">        }</span><a href="#l178"></a>
<span id="l179">        for (Key key : keys) {</span><a href="#l179"></a>
<span id="l180">            sb.append(&quot;\n  Key: &quot;).append(key.getAlgorithm());</span><a href="#l180"></a>
<span id="l181">        }</span><a href="#l181"></a>
<span id="l182">        if (timestamp != null) {</span><a href="#l182"></a>
<span id="l183">            sb.append(&quot;\n  Timestamp: &quot;).append(timestamp);</span><a href="#l183"></a>
<span id="l184">        }</span><a href="#l184"></a>
<span id="l185">        sb.append(&quot;\n]&quot;);</span><a href="#l185"></a>
<span id="l186">        return sb.toString();</span><a href="#l186"></a>
<span id="l187">    }</span><a href="#l187"></a>
<span id="l188">}</span><a href="#l188"></a></pre>
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

