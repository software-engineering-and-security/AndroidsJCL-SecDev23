<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/util/SignatureFileVerifier.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/util/SignatureFileVerifier.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/util/SignatureFileVerifier.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/util/SignatureFileVerifier.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/util/SignatureFileVerifier.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/util/SignatureFileVerifier.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/util/SignatureFileVerifier.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/util/SignatureFileVerifier.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/fde7fd2a2fd2/src/share/classes/sun/security/util/SignatureFileVerifier.java">fde7fd2a2fd2</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/465a2d03f9b0/src/share/classes/sun/security/util/SignatureFileVerifier.java">465a2d03f9b0</a> </td>
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
<span id="l28">import java.io.ByteArrayInputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.security.CodeSigner;</span><a href="#l30"></a>
<span id="l31">import java.security.GeneralSecurityException;</span><a href="#l31"></a>
<span id="l32">import java.security.MessageDigest;</span><a href="#l32"></a>
<span id="l33">import java.security.NoSuchAlgorithmException;</span><a href="#l33"></a>
<span id="l34">import java.security.SignatureException;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.CertPath;</span><a href="#l35"></a>
<span id="l36">import java.security.cert.X509Certificate;</span><a href="#l36"></a>
<span id="l37">import java.security.cert.CertificateException;</span><a href="#l37"></a>
<span id="l38">import java.security.cert.CertificateFactory;</span><a href="#l38"></a>
<span id="l39">import java.util.ArrayList;</span><a href="#l39"></a>
<span id="l40">import java.util.Base64;</span><a href="#l40"></a>
<span id="l41">import java.util.HashMap;</span><a href="#l41"></a>
<span id="l42">import java.util.Hashtable;</span><a href="#l42"></a>
<span id="l43">import java.util.Iterator;</span><a href="#l43"></a>
<span id="l44">import java.util.List;</span><a href="#l44"></a>
<span id="l45">import java.util.Locale;</span><a href="#l45"></a>
<span id="l46">import java.util.Map;</span><a href="#l46"></a>
<span id="l47">import java.util.Set;</span><a href="#l47"></a>
<span id="l48">import java.util.jar.Attributes;</span><a href="#l48"></a>
<span id="l49">import java.util.jar.JarException;</span><a href="#l49"></a>
<span id="l50">import java.util.jar.JarFile;</span><a href="#l50"></a>
<span id="l51">import java.util.jar.Manifest;</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">import sun.security.jca.Providers;</span><a href="#l53"></a>
<span id="l54">import sun.security.pkcs.PKCS7;</span><a href="#l54"></a>
<span id="l55">import sun.security.pkcs.SignerInfo;</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">public class SignatureFileVerifier {</span><a href="#l57"></a>
<span id="l58"></span><a href="#l58"></a>
<span id="l59">    /* Are we debugging ? */</span><a href="#l59"></a>
<span id="l60">    private static final Debug debug = Debug.getInstance(&quot;jar&quot;);</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    private ArrayList&lt;CodeSigner[]&gt; signerCache;</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">    private static final String ATTR_DIGEST =</span><a href="#l64"></a>
<span id="l65">        (&quot;-DIGEST-&quot; + ManifestDigester.MF_MAIN_ATTRS).toUpperCase</span><a href="#l65"></a>
<span id="l66">        (Locale.ENGLISH);</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    /** the PKCS7 block for this .DSA/.RSA/.EC file */</span><a href="#l68"></a>
<span id="l69">    private PKCS7 block;</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    /** the raw bytes of the .SF file */</span><a href="#l71"></a>
<span id="l72">    private byte[] sfBytes;</span><a href="#l72"></a>
<span id="l73"></span><a href="#l73"></a>
<span id="l74">    /** the name of the signature block file, uppercased and without</span><a href="#l74"></a>
<span id="l75">     *  the extension (.DSA/.RSA/.EC)</span><a href="#l75"></a>
<span id="l76">     */</span><a href="#l76"></a>
<span id="l77">    private String name;</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    /** the ManifestDigester */</span><a href="#l79"></a>
<span id="l80">    private ManifestDigester md;</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">    /** cache of created MessageDigest objects */</span><a href="#l82"></a>
<span id="l83">    private HashMap&lt;String, MessageDigest&gt; createdDigests;</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    /* workaround for parsing Netscape jars  */</span><a href="#l85"></a>
<span id="l86">    private boolean workaround = false;</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">    /* for generating certpath objects */</span><a href="#l88"></a>
<span id="l89">    private CertificateFactory certificateFactory = null;</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">    /** Algorithms that have been previously checked against disabled</span><a href="#l91"></a>
<span id="l92">     *  constraints.</span><a href="#l92"></a>
<span id="l93">     */</span><a href="#l93"></a>
<span id="l94">    private Map&lt;String, Boolean&gt; permittedAlgs = new HashMap&lt;&gt;();</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    /** ConstraintsParameters for checking disabled algorithms */</span><a href="#l96"></a>
<span id="l97">    private JarConstraintsParameters params;</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    /**</span><a href="#l99"></a>
<span id="l100">     * Create the named SignatureFileVerifier.</span><a href="#l100"></a>
<span id="l101">     *</span><a href="#l101"></a>
<span id="l102">     * @param name the name of the signature block file (.DSA/.RSA/.EC)</span><a href="#l102"></a>
<span id="l103">     *</span><a href="#l103"></a>
<span id="l104">     * @param rawBytes the raw bytes of the signature block file</span><a href="#l104"></a>
<span id="l105">     */</span><a href="#l105"></a>
<span id="l106">    public SignatureFileVerifier(ArrayList&lt;CodeSigner[]&gt; signerCache,</span><a href="#l106"></a>
<span id="l107">                                 ManifestDigester md,</span><a href="#l107"></a>
<span id="l108">                                 String name,</span><a href="#l108"></a>
<span id="l109">                                 byte[] rawBytes)</span><a href="#l109"></a>
<span id="l110">        throws IOException, CertificateException</span><a href="#l110"></a>
<span id="l111">    {</span><a href="#l111"></a>
<span id="l112">        // new PKCS7() calls CertificateFactory.getInstance()</span><a href="#l112"></a>
<span id="l113">        // need to use local providers here, see Providers class</span><a href="#l113"></a>
<span id="l114">        Object obj = null;</span><a href="#l114"></a>
<span id="l115">        try {</span><a href="#l115"></a>
<span id="l116">            obj = Providers.startJarVerification();</span><a href="#l116"></a>
<span id="l117">            block = new PKCS7(rawBytes);</span><a href="#l117"></a>
<span id="l118">            sfBytes = block.getContentInfo().getData();</span><a href="#l118"></a>
<span id="l119">            certificateFactory = CertificateFactory.getInstance(&quot;X509&quot;);</span><a href="#l119"></a>
<span id="l120">        } finally {</span><a href="#l120"></a>
<span id="l121">            Providers.stopJarVerification(obj);</span><a href="#l121"></a>
<span id="l122">        }</span><a href="#l122"></a>
<span id="l123">        this.name = name.substring(0, name.lastIndexOf('.'))</span><a href="#l123"></a>
<span id="l124">                                                   .toUpperCase(Locale.ENGLISH);</span><a href="#l124"></a>
<span id="l125">        this.md = md;</span><a href="#l125"></a>
<span id="l126">        this.signerCache = signerCache;</span><a href="#l126"></a>
<span id="l127">    }</span><a href="#l127"></a>
<span id="l128"></span><a href="#l128"></a>
<span id="l129">    /**</span><a href="#l129"></a>
<span id="l130">     * returns true if we need the .SF file</span><a href="#l130"></a>
<span id="l131">     */</span><a href="#l131"></a>
<span id="l132">    public boolean needSignatureFileBytes()</span><a href="#l132"></a>
<span id="l133">    {</span><a href="#l133"></a>
<span id="l134"></span><a href="#l134"></a>
<span id="l135">        return sfBytes == null;</span><a href="#l135"></a>
<span id="l136">    }</span><a href="#l136"></a>
<span id="l137"></span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">    /**</span><a href="#l139"></a>
<span id="l140">     * returns true if we need this .SF file.</span><a href="#l140"></a>
<span id="l141">     *</span><a href="#l141"></a>
<span id="l142">     * @param name the name of the .SF file without the extension</span><a href="#l142"></a>
<span id="l143">     *</span><a href="#l143"></a>
<span id="l144">     */</span><a href="#l144"></a>
<span id="l145">    public boolean needSignatureFile(String name)</span><a href="#l145"></a>
<span id="l146">    {</span><a href="#l146"></a>
<span id="l147">        return this.name.equalsIgnoreCase(name);</span><a href="#l147"></a>
<span id="l148">    }</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">    /**</span><a href="#l150"></a>
<span id="l151">     * used to set the raw bytes of the .SF file when it</span><a href="#l151"></a>
<span id="l152">     * is external to the signature block file.</span><a href="#l152"></a>
<span id="l153">     */</span><a href="#l153"></a>
<span id="l154">    public void setSignatureFile(byte[] sfBytes)</span><a href="#l154"></a>
<span id="l155">    {</span><a href="#l155"></a>
<span id="l156">        this.sfBytes = sfBytes;</span><a href="#l156"></a>
<span id="l157">    }</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">    /**</span><a href="#l159"></a>
<span id="l160">     * Utility method used by JarVerifier and JarSigner</span><a href="#l160"></a>
<span id="l161">     * to determine the signature file names and PKCS7 block</span><a href="#l161"></a>
<span id="l162">     * files names that are supported</span><a href="#l162"></a>
<span id="l163">     *</span><a href="#l163"></a>
<span id="l164">     * @param s file name</span><a href="#l164"></a>
<span id="l165">     * @return true if the input file name is a supported</span><a href="#l165"></a>
<span id="l166">     *          Signature File or PKCS7 block file name</span><a href="#l166"></a>
<span id="l167">     */</span><a href="#l167"></a>
<span id="l168">    public static boolean isBlockOrSF(String s) {</span><a href="#l168"></a>
<span id="l169">        // we currently only support DSA and RSA PKCS7 blocks</span><a href="#l169"></a>
<span id="l170">        return s.endsWith(&quot;.SF&quot;)</span><a href="#l170"></a>
<span id="l171">            || s.endsWith(&quot;.DSA&quot;)</span><a href="#l171"></a>
<span id="l172">            || s.endsWith(&quot;.RSA&quot;)</span><a href="#l172"></a>
<span id="l173">            || s.endsWith(&quot;.EC&quot;);</span><a href="#l173"></a>
<span id="l174">    }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">    /**</span><a href="#l176"></a>
<span id="l177">     * Yet another utility method used by JarVerifier and JarSigner</span><a href="#l177"></a>
<span id="l178">     * to determine what files are signature related, which includes</span><a href="#l178"></a>
<span id="l179">     * the MANIFEST, SF files, known signature block files, and other</span><a href="#l179"></a>
<span id="l180">     * unknown signature related files (those starting with SIG- with</span><a href="#l180"></a>
<span id="l181">     * an optional [A-Z0-9]{1,3} extension right inside META-INF).</span><a href="#l181"></a>
<span id="l182">     *</span><a href="#l182"></a>
<span id="l183">     * @param name file name</span><a href="#l183"></a>
<span id="l184">     * @return true if the input file name is signature related</span><a href="#l184"></a>
<span id="l185">     */</span><a href="#l185"></a>
<span id="l186">    public static boolean isSigningRelated(String name) {</span><a href="#l186"></a>
<span id="l187">        name = name.toUpperCase(Locale.ENGLISH);</span><a href="#l187"></a>
<span id="l188">        if (!name.startsWith(&quot;META-INF/&quot;)) {</span><a href="#l188"></a>
<span id="l189">            return false;</span><a href="#l189"></a>
<span id="l190">        }</span><a href="#l190"></a>
<span id="l191">        name = name.substring(9);</span><a href="#l191"></a>
<span id="l192">        if (name.indexOf('/') != -1) {</span><a href="#l192"></a>
<span id="l193">            return false;</span><a href="#l193"></a>
<span id="l194">        }</span><a href="#l194"></a>
<span id="l195">        if (isBlockOrSF(name) || name.equals(&quot;MANIFEST.MF&quot;)) {</span><a href="#l195"></a>
<span id="l196">            return true;</span><a href="#l196"></a>
<span id="l197">        } else if (name.startsWith(&quot;SIG-&quot;)) {</span><a href="#l197"></a>
<span id="l198">            // check filename extension</span><a href="#l198"></a>
<span id="l199">            // see http://docs.oracle.com/javase/7/docs/technotes/guides/jar/jar.html#Digital_Signatures</span><a href="#l199"></a>
<span id="l200">            // for what filename extensions are legal</span><a href="#l200"></a>
<span id="l201">            int extIndex = name.lastIndexOf('.');</span><a href="#l201"></a>
<span id="l202">            if (extIndex != -1) {</span><a href="#l202"></a>
<span id="l203">                String ext = name.substring(extIndex + 1);</span><a href="#l203"></a>
<span id="l204">                // validate length first</span><a href="#l204"></a>
<span id="l205">                if (ext.length() &gt; 3 || ext.length() &lt; 1) {</span><a href="#l205"></a>
<span id="l206">                    return false;</span><a href="#l206"></a>
<span id="l207">                }</span><a href="#l207"></a>
<span id="l208">                // then check chars, must be in [a-zA-Z0-9] per the jar spec</span><a href="#l208"></a>
<span id="l209">                for (int index = 0; index &lt; ext.length(); index++) {</span><a href="#l209"></a>
<span id="l210">                    char cc = ext.charAt(index);</span><a href="#l210"></a>
<span id="l211">                    // chars are promoted to uppercase so skip lowercase checks</span><a href="#l211"></a>
<span id="l212">                    if ((cc &lt; 'A' || cc &gt; 'Z') &amp;&amp; (cc &lt; '0' || cc &gt; '9')) {</span><a href="#l212"></a>
<span id="l213">                        return false;</span><a href="#l213"></a>
<span id="l214">                    }</span><a href="#l214"></a>
<span id="l215">                }</span><a href="#l215"></a>
<span id="l216">            }</span><a href="#l216"></a>
<span id="l217">            return true; // no extension is OK</span><a href="#l217"></a>
<span id="l218">        }</span><a href="#l218"></a>
<span id="l219">        return false;</span><a href="#l219"></a>
<span id="l220">    }</span><a href="#l220"></a>
<span id="l221"></span><a href="#l221"></a>
<span id="l222">    /** get digest from cache */</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    private MessageDigest getDigest(String algorithm)</span><a href="#l224"></a>
<span id="l225">            throws SignatureException {</span><a href="#l225"></a>
<span id="l226">        if (createdDigests == null)</span><a href="#l226"></a>
<span id="l227">            createdDigests = new HashMap&lt;&gt;();</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">        MessageDigest digest = createdDigests.get(algorithm);</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">        if (digest == null) {</span><a href="#l231"></a>
<span id="l232">            try {</span><a href="#l232"></a>
<span id="l233">                digest = MessageDigest.getInstance(algorithm);</span><a href="#l233"></a>
<span id="l234">                createdDigests.put(algorithm, digest);</span><a href="#l234"></a>
<span id="l235">            } catch (NoSuchAlgorithmException nsae) {</span><a href="#l235"></a>
<span id="l236">                // ignore</span><a href="#l236"></a>
<span id="l237">            }</span><a href="#l237"></a>
<span id="l238">        }</span><a href="#l238"></a>
<span id="l239">        return digest;</span><a href="#l239"></a>
<span id="l240">    }</span><a href="#l240"></a>
<span id="l241"></span><a href="#l241"></a>
<span id="l242">    /**</span><a href="#l242"></a>
<span id="l243">     * process the signature block file. Goes through the .SF file</span><a href="#l243"></a>
<span id="l244">     * and adds code signers for each section where the .SF section</span><a href="#l244"></a>
<span id="l245">     * hash was verified against the Manifest section.</span><a href="#l245"></a>
<span id="l246">     *</span><a href="#l246"></a>
<span id="l247">     *</span><a href="#l247"></a>
<span id="l248">     */</span><a href="#l248"></a>
<span id="l249">    public void process(Hashtable&lt;String, CodeSigner[]&gt; signers,</span><a href="#l249"></a>
<span id="l250">            List&lt;Object&gt; manifestDigests)</span><a href="#l250"></a>
<span id="l251">        throws IOException, SignatureException, NoSuchAlgorithmException,</span><a href="#l251"></a>
<span id="l252">            JarException, CertificateException</span><a href="#l252"></a>
<span id="l253">    {</span><a href="#l253"></a>
<span id="l254">        // calls Signature.getInstance() and MessageDigest.getInstance()</span><a href="#l254"></a>
<span id="l255">        // need to use local providers here, see Providers class</span><a href="#l255"></a>
<span id="l256">        Object obj = null;</span><a href="#l256"></a>
<span id="l257">        try {</span><a href="#l257"></a>
<span id="l258">            obj = Providers.startJarVerification();</span><a href="#l258"></a>
<span id="l259">            processImpl(signers, manifestDigests);</span><a href="#l259"></a>
<span id="l260">        } finally {</span><a href="#l260"></a>
<span id="l261">            Providers.stopJarVerification(obj);</span><a href="#l261"></a>
<span id="l262">        }</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">    }</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">    private void processImpl(Hashtable&lt;String, CodeSigner[]&gt; signers,</span><a href="#l266"></a>
<span id="l267">            List&lt;Object&gt; manifestDigests)</span><a href="#l267"></a>
<span id="l268">        throws IOException, SignatureException, NoSuchAlgorithmException,</span><a href="#l268"></a>
<span id="l269">            JarException, CertificateException</span><a href="#l269"></a>
<span id="l270">    {</span><a href="#l270"></a>
<span id="l271">        Manifest sf = new Manifest();</span><a href="#l271"></a>
<span id="l272">        sf.read(new ByteArrayInputStream(sfBytes));</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">        String version =</span><a href="#l274"></a>
<span id="l275">            sf.getMainAttributes().getValue(Attributes.Name.SIGNATURE_VERSION);</span><a href="#l275"></a>
<span id="l276"></span><a href="#l276"></a>
<span id="l277">        if ((version == null) || !(version.equalsIgnoreCase(&quot;1.0&quot;))) {</span><a href="#l277"></a>
<span id="l278">            // XXX: should this be an exception?</span><a href="#l278"></a>
<span id="l279">            // for now we just ignore this signature file</span><a href="#l279"></a>
<span id="l280">            return;</span><a href="#l280"></a>
<span id="l281">        }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">        SignerInfo[] infos = block.verify(sfBytes);</span><a href="#l283"></a>
<span id="l284"></span><a href="#l284"></a>
<span id="l285">        if (infos == null) {</span><a href="#l285"></a>
<span id="l286">            throw new SecurityException(&quot;cannot verify signature block file &quot; +</span><a href="#l286"></a>
<span id="l287">                                        name);</span><a href="#l287"></a>
<span id="l288">        }</span><a href="#l288"></a>
<span id="l289"></span><a href="#l289"></a>
<span id="l290">        CodeSigner[] newSigners = getSigners(infos, block);</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">        // make sure we have something to do all this work for...</span><a href="#l292"></a>
<span id="l293">        if (newSigners == null) {</span><a href="#l293"></a>
<span id="l294">            return;</span><a href="#l294"></a>
<span id="l295">        }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">        // check if any of the algorithms used to verify the SignerInfos</span><a href="#l297"></a>
<span id="l298">        // are disabled</span><a href="#l298"></a>
<span id="l299">        params = new JarConstraintsParameters(newSigners);</span><a href="#l299"></a>
<span id="l300">        Set&lt;String&gt; notDisabledAlgorithms =</span><a href="#l300"></a>
<span id="l301">            SignerInfo.verifyAlgorithms(infos, params, name + &quot; PKCS7&quot;);</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        // add the SignerInfo algorithms that are ok to the permittedAlgs map</span><a href="#l303"></a>
<span id="l304">        // so they are not checked again</span><a href="#l304"></a>
<span id="l305">        for (String algorithm : notDisabledAlgorithms) {</span><a href="#l305"></a>
<span id="l306">            permittedAlgs.put(algorithm, Boolean.TRUE);</span><a href="#l306"></a>
<span id="l307">        }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">        Iterator&lt;Map.Entry&lt;String,Attributes&gt;&gt; entries =</span><a href="#l309"></a>
<span id="l310">                                sf.getEntries().entrySet().iterator();</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">        // see if we can verify the whole manifest first</span><a href="#l312"></a>
<span id="l313">        boolean manifestSigned = verifyManifestHash(sf, md, manifestDigests);</span><a href="#l313"></a>
<span id="l314"></span><a href="#l314"></a>
<span id="l315">        // verify manifest main attributes</span><a href="#l315"></a>
<span id="l316">        if (!manifestSigned &amp;&amp; !verifyManifestMainAttrs(sf, md)) {</span><a href="#l316"></a>
<span id="l317">            throw new SecurityException</span><a href="#l317"></a>
<span id="l318">                (&quot;Invalid signature file digest for Manifest main attributes&quot;);</span><a href="#l318"></a>
<span id="l319">        }</span><a href="#l319"></a>
<span id="l320"></span><a href="#l320"></a>
<span id="l321">        // go through each section in the signature file</span><a href="#l321"></a>
<span id="l322">        while(entries.hasNext()) {</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">            Map.Entry&lt;String,Attributes&gt; e = entries.next();</span><a href="#l324"></a>
<span id="l325">            String name = e.getKey();</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">            if (manifestSigned ||</span><a href="#l327"></a>
<span id="l328">                (verifySection(e.getValue(), name, md))) {</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">                if (name.startsWith(&quot;./&quot;))</span><a href="#l330"></a>
<span id="l331">                    name = name.substring(2);</span><a href="#l331"></a>
<span id="l332"></span><a href="#l332"></a>
<span id="l333">                if (name.startsWith(&quot;/&quot;))</span><a href="#l333"></a>
<span id="l334">                    name = name.substring(1);</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">                updateSigners(newSigners, signers, name);</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">                if (debug != null) {</span><a href="#l338"></a>
<span id="l339">                    debug.println(&quot;processSignature signed name = &quot;+name);</span><a href="#l339"></a>
<span id="l340">                }</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">            } else if (debug != null) {</span><a href="#l342"></a>
<span id="l343">                debug.println(&quot;processSignature unsigned name = &quot;+name);</span><a href="#l343"></a>
<span id="l344">            }</span><a href="#l344"></a>
<span id="l345">        }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">        // MANIFEST.MF is always regarded as signed</span><a href="#l347"></a>
<span id="l348">        updateSigners(newSigners, signers, JarFile.MANIFEST_NAME);</span><a href="#l348"></a>
<span id="l349">    }</span><a href="#l349"></a>
<span id="l350"></span><a href="#l350"></a>
<span id="l351">    /**</span><a href="#l351"></a>
<span id="l352">     * Check if algorithm is permitted using the permittedAlgs Map.</span><a href="#l352"></a>
<span id="l353">     * If the algorithm is not in the map, check against disabled algorithms and</span><a href="#l353"></a>
<span id="l354">     * store the result. If the algorithm is in the map use that result.</span><a href="#l354"></a>
<span id="l355">     * False is returned for weak algorithm, true for good algorithms.</span><a href="#l355"></a>
<span id="l356">     */</span><a href="#l356"></a>
<span id="l357">    private boolean permittedCheck(String key, String algorithm) {</span><a href="#l357"></a>
<span id="l358">        Boolean permitted = permittedAlgs.get(algorithm);</span><a href="#l358"></a>
<span id="l359">        if (permitted == null) {</span><a href="#l359"></a>
<span id="l360">            try {</span><a href="#l360"></a>
<span id="l361">                params.setExtendedExceptionMsg(name + &quot;.SF&quot;, key + &quot; attribute&quot;);</span><a href="#l361"></a>
<span id="l362">                DisabledAlgorithmConstraints</span><a href="#l362"></a>
<span id="l363">                    .jarConstraints().permits(algorithm, params);</span><a href="#l363"></a>
<span id="l364">            } catch (GeneralSecurityException e) {</span><a href="#l364"></a>
<span id="l365">                permittedAlgs.put(algorithm, Boolean.FALSE);</span><a href="#l365"></a>
<span id="l366">                permittedAlgs.put(key.toUpperCase(), Boolean.FALSE);</span><a href="#l366"></a>
<span id="l367">                if (debug != null) {</span><a href="#l367"></a>
<span id="l368">                    if (e.getMessage() != null) {</span><a href="#l368"></a>
<span id="l369">                        debug.println(key + &quot;:  &quot; + e.getMessage());</span><a href="#l369"></a>
<span id="l370">                    } else {</span><a href="#l370"></a>
<span id="l371">                        debug.println(&quot;Debug info only. &quot; +  key + &quot;:  &quot; +</span><a href="#l371"></a>
<span id="l372">                            algorithm +</span><a href="#l372"></a>
<span id="l373">                            &quot; was disabled, no exception msg given.&quot;);</span><a href="#l373"></a>
<span id="l374">                        e.printStackTrace();</span><a href="#l374"></a>
<span id="l375">                    }</span><a href="#l375"></a>
<span id="l376">                }</span><a href="#l376"></a>
<span id="l377">                return false;</span><a href="#l377"></a>
<span id="l378">            }</span><a href="#l378"></a>
<span id="l379"></span><a href="#l379"></a>
<span id="l380">            permittedAlgs.put(algorithm, Boolean.TRUE);</span><a href="#l380"></a>
<span id="l381">            return true;</span><a href="#l381"></a>
<span id="l382">        }</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">        // Algorithm has already been checked, return the value from map.</span><a href="#l384"></a>
<span id="l385">        return permitted.booleanValue();</span><a href="#l385"></a>
<span id="l386">    }</span><a href="#l386"></a>
<span id="l387"></span><a href="#l387"></a>
<span id="l388">    /**</span><a href="#l388"></a>
<span id="l389">     * With a given header (*-DIGEST*), return a string that lists all the</span><a href="#l389"></a>
<span id="l390">     * algorithms associated with the header.</span><a href="#l390"></a>
<span id="l391">     * If there are none, return &quot;Unknown Algorithm&quot;.</span><a href="#l391"></a>
<span id="l392">     */</span><a href="#l392"></a>
<span id="l393">    String getWeakAlgorithms(String header) {</span><a href="#l393"></a>
<span id="l394">        String w = &quot;&quot;;</span><a href="#l394"></a>
<span id="l395">        try {</span><a href="#l395"></a>
<span id="l396">            for (String key : permittedAlgs.keySet()) {</span><a href="#l396"></a>
<span id="l397">                if (key.endsWith(header)) {</span><a href="#l397"></a>
<span id="l398">                    w += key.substring(0, key.length() - header.length()) + &quot; &quot;;</span><a href="#l398"></a>
<span id="l399">                }</span><a href="#l399"></a>
<span id="l400">            }</span><a href="#l400"></a>
<span id="l401">        } catch (RuntimeException e) {</span><a href="#l401"></a>
<span id="l402">            w = &quot;Unknown Algorithm(s).  Error processing &quot; + header + &quot;.  &quot; +</span><a href="#l402"></a>
<span id="l403">                    e.getMessage();</span><a href="#l403"></a>
<span id="l404">        }</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">        // This means we have an error in finding weak algorithms, run in</span><a href="#l406"></a>
<span id="l407">        // debug mode to see permittedAlgs map's values.</span><a href="#l407"></a>
<span id="l408">        if (w.length() == 0) {</span><a href="#l408"></a>
<span id="l409">            return &quot;Unknown Algorithm(s)&quot;;</span><a href="#l409"></a>
<span id="l410">        }</span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">        return w;</span><a href="#l412"></a>
<span id="l413">    }</span><a href="#l413"></a>
<span id="l414"></span><a href="#l414"></a>
<span id="l415">    /**</span><a href="#l415"></a>
<span id="l416">     * See if the whole manifest was signed.</span><a href="#l416"></a>
<span id="l417">     */</span><a href="#l417"></a>
<span id="l418">    private boolean verifyManifestHash(Manifest sf,</span><a href="#l418"></a>
<span id="l419">                                       ManifestDigester md,</span><a href="#l419"></a>
<span id="l420">                                       List&lt;Object&gt; manifestDigests)</span><a href="#l420"></a>
<span id="l421">         throws IOException, SignatureException</span><a href="#l421"></a>
<span id="l422">    {</span><a href="#l422"></a>
<span id="l423">        Attributes mattr = sf.getMainAttributes();</span><a href="#l423"></a>
<span id="l424">        boolean manifestSigned = false;</span><a href="#l424"></a>
<span id="l425">        // If only weak algorithms are used.</span><a href="#l425"></a>
<span id="l426">        boolean weakAlgs = true;</span><a href="#l426"></a>
<span id="l427">        // If a &quot;*-DIGEST-MANIFEST&quot; entry is found.</span><a href="#l427"></a>
<span id="l428">        boolean validEntry = false;</span><a href="#l428"></a>
<span id="l429"></span><a href="#l429"></a>
<span id="l430">        // go through all the attributes and process *-Digest-Manifest entries</span><a href="#l430"></a>
<span id="l431">        for (Map.Entry&lt;Object,Object&gt; se : mattr.entrySet()) {</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">            String key = se.getKey().toString();</span><a href="#l433"></a>
<span id="l434"></span><a href="#l434"></a>
<span id="l435">            if (key.toUpperCase(Locale.ENGLISH).endsWith(&quot;-DIGEST-MANIFEST&quot;)) {</span><a href="#l435"></a>
<span id="l436">                // 16 is length of &quot;-Digest-Manifest&quot;</span><a href="#l436"></a>
<span id="l437">                String algorithm = key.substring(0, key.length()-16);</span><a href="#l437"></a>
<span id="l438">                validEntry = true;</span><a href="#l438"></a>
<span id="l439"></span><a href="#l439"></a>
<span id="l440">                // Check if this algorithm is permitted, skip if false.</span><a href="#l440"></a>
<span id="l441">                if (!permittedCheck(key, algorithm)) {</span><a href="#l441"></a>
<span id="l442">                    continue;</span><a href="#l442"></a>
<span id="l443">                }</span><a href="#l443"></a>
<span id="l444"></span><a href="#l444"></a>
<span id="l445">                // A non-weak algorithm was used, any weak algorithms found do</span><a href="#l445"></a>
<span id="l446">                // not need to be reported.</span><a href="#l446"></a>
<span id="l447">                weakAlgs = false;</span><a href="#l447"></a>
<span id="l448"></span><a href="#l448"></a>
<span id="l449">                manifestDigests.add(key);</span><a href="#l449"></a>
<span id="l450">                manifestDigests.add(se.getValue());</span><a href="#l450"></a>
<span id="l451">                MessageDigest digest = getDigest(algorithm);</span><a href="#l451"></a>
<span id="l452">                if (digest != null) {</span><a href="#l452"></a>
<span id="l453">                    byte[] computedHash = md.manifestDigest(digest);</span><a href="#l453"></a>
<span id="l454">                    byte[] expectedHash =</span><a href="#l454"></a>
<span id="l455">                        Base64.getMimeDecoder().decode((String)se.getValue());</span><a href="#l455"></a>
<span id="l456"></span><a href="#l456"></a>
<span id="l457">                    if (debug != null) {</span><a href="#l457"></a>
<span id="l458">                        debug.println(&quot;Signature File: Manifest digest &quot; +</span><a href="#l458"></a>
<span id="l459">                                algorithm);</span><a href="#l459"></a>
<span id="l460">                        debug.println( &quot;  sigfile  &quot; + toHex(expectedHash));</span><a href="#l460"></a>
<span id="l461">                        debug.println( &quot;  computed &quot; + toHex(computedHash));</span><a href="#l461"></a>
<span id="l462">                        debug.println();</span><a href="#l462"></a>
<span id="l463">                    }</span><a href="#l463"></a>
<span id="l464"></span><a href="#l464"></a>
<span id="l465">                    if (MessageDigest.isEqual(computedHash, expectedHash)) {</span><a href="#l465"></a>
<span id="l466">                        manifestSigned = true;</span><a href="#l466"></a>
<span id="l467">                    } else {</span><a href="#l467"></a>
<span id="l468">                        //XXX: we will continue and verify each section</span><a href="#l468"></a>
<span id="l469">                    }</span><a href="#l469"></a>
<span id="l470">                }</span><a href="#l470"></a>
<span id="l471">            }</span><a href="#l471"></a>
<span id="l472">        }</span><a href="#l472"></a>
<span id="l473"></span><a href="#l473"></a>
<span id="l474">        if (debug != null) {</span><a href="#l474"></a>
<span id="l475">            debug.println(&quot;PermittedAlgs mapping: &quot;);</span><a href="#l475"></a>
<span id="l476">            for (String key : permittedAlgs.keySet()) {</span><a href="#l476"></a>
<span id="l477">                debug.println(key + &quot; : &quot; +</span><a href="#l477"></a>
<span id="l478">                        permittedAlgs.get(key).toString());</span><a href="#l478"></a>
<span id="l479">            }</span><a href="#l479"></a>
<span id="l480">        }</span><a href="#l480"></a>
<span id="l481"></span><a href="#l481"></a>
<span id="l482">        // If there were only weak algorithms entries used, throw an exception.</span><a href="#l482"></a>
<span id="l483">        if (validEntry &amp;&amp; weakAlgs) {</span><a href="#l483"></a>
<span id="l484">            throw new SignatureException(&quot;Manifest hash check failed &quot; +</span><a href="#l484"></a>
<span id="l485">                    &quot;(DIGEST-MANIFEST). Disabled algorithm(s) used: &quot; +</span><a href="#l485"></a>
<span id="l486">                    getWeakAlgorithms(&quot;-DIGEST-MANIFEST&quot;));</span><a href="#l486"></a>
<span id="l487">        }</span><a href="#l487"></a>
<span id="l488">        return manifestSigned;</span><a href="#l488"></a>
<span id="l489">    }</span><a href="#l489"></a>
<span id="l490"></span><a href="#l490"></a>
<span id="l491">    private boolean verifyManifestMainAttrs(Manifest sf, ManifestDigester md)</span><a href="#l491"></a>
<span id="l492">         throws IOException, SignatureException</span><a href="#l492"></a>
<span id="l493">    {</span><a href="#l493"></a>
<span id="l494">        Attributes mattr = sf.getMainAttributes();</span><a href="#l494"></a>
<span id="l495">        boolean attrsVerified = true;</span><a href="#l495"></a>
<span id="l496">        // If only weak algorithms are used.</span><a href="#l496"></a>
<span id="l497">        boolean weakAlgs = true;</span><a href="#l497"></a>
<span id="l498">        // If a ATTR_DIGEST entry is found.</span><a href="#l498"></a>
<span id="l499">        boolean validEntry = false;</span><a href="#l499"></a>
<span id="l500"></span><a href="#l500"></a>
<span id="l501">        // go through all the attributes and process</span><a href="#l501"></a>
<span id="l502">        // digest entries for the manifest main attributes</span><a href="#l502"></a>
<span id="l503">        for (Map.Entry&lt;Object,Object&gt; se : mattr.entrySet()) {</span><a href="#l503"></a>
<span id="l504">            String key = se.getKey().toString();</span><a href="#l504"></a>
<span id="l505"></span><a href="#l505"></a>
<span id="l506">            if (key.toUpperCase(Locale.ENGLISH).endsWith(ATTR_DIGEST)) {</span><a href="#l506"></a>
<span id="l507">                String algorithm =</span><a href="#l507"></a>
<span id="l508">                        key.substring(0, key.length() - ATTR_DIGEST.length());</span><a href="#l508"></a>
<span id="l509">                validEntry = true;</span><a href="#l509"></a>
<span id="l510"></span><a href="#l510"></a>
<span id="l511">                // Check if this algorithm is permitted, skip if false.</span><a href="#l511"></a>
<span id="l512">                if (!permittedCheck(key, algorithm)) {</span><a href="#l512"></a>
<span id="l513">                    continue;</span><a href="#l513"></a>
<span id="l514">                }</span><a href="#l514"></a>
<span id="l515"></span><a href="#l515"></a>
<span id="l516">                // A non-weak algorithm was used, any weak algorithms found do</span><a href="#l516"></a>
<span id="l517">                // not need to be reported.</span><a href="#l517"></a>
<span id="l518">                weakAlgs = false;</span><a href="#l518"></a>
<span id="l519"></span><a href="#l519"></a>
<span id="l520">                MessageDigest digest = getDigest(algorithm);</span><a href="#l520"></a>
<span id="l521">                if (digest != null) {</span><a href="#l521"></a>
<span id="l522">                    ManifestDigester.Entry mde =</span><a href="#l522"></a>
<span id="l523">                        md.get(ManifestDigester.MF_MAIN_ATTRS, false);</span><a href="#l523"></a>
<span id="l524">                    byte[] computedHash = mde.digest(digest);</span><a href="#l524"></a>
<span id="l525">                    byte[] expectedHash =</span><a href="#l525"></a>
<span id="l526">                        Base64.getMimeDecoder().decode((String)se.getValue());</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">                    if (debug != null) {</span><a href="#l528"></a>
<span id="l529">                     debug.println(&quot;Signature File: &quot; +</span><a href="#l529"></a>
<span id="l530">                                        &quot;Manifest Main Attributes digest &quot; +</span><a href="#l530"></a>
<span id="l531">                                        digest.getAlgorithm());</span><a href="#l531"></a>
<span id="l532">                     debug.println( &quot;  sigfile  &quot; + toHex(expectedHash));</span><a href="#l532"></a>
<span id="l533">                     debug.println( &quot;  computed &quot; + toHex(computedHash));</span><a href="#l533"></a>
<span id="l534">                     debug.println();</span><a href="#l534"></a>
<span id="l535">                    }</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">                    if (MessageDigest.isEqual(computedHash, expectedHash)) {</span><a href="#l537"></a>
<span id="l538">                        // good</span><a href="#l538"></a>
<span id="l539">                    } else {</span><a href="#l539"></a>
<span id="l540">                        // we will *not* continue and verify each section</span><a href="#l540"></a>
<span id="l541">                        attrsVerified = false;</span><a href="#l541"></a>
<span id="l542">                        if (debug != null) {</span><a href="#l542"></a>
<span id="l543">                            debug.println(&quot;Verification of &quot; +</span><a href="#l543"></a>
<span id="l544">                                        &quot;Manifest main attributes failed&quot;);</span><a href="#l544"></a>
<span id="l545">                            debug.println();</span><a href="#l545"></a>
<span id="l546">                        }</span><a href="#l546"></a>
<span id="l547">                        break;</span><a href="#l547"></a>
<span id="l548">                    }</span><a href="#l548"></a>
<span id="l549">                }</span><a href="#l549"></a>
<span id="l550">            }</span><a href="#l550"></a>
<span id="l551">        }</span><a href="#l551"></a>
<span id="l552"></span><a href="#l552"></a>
<span id="l553">        if (debug != null) {</span><a href="#l553"></a>
<span id="l554">            debug.println(&quot;PermittedAlgs mapping: &quot;);</span><a href="#l554"></a>
<span id="l555">            for (String key : permittedAlgs.keySet()) {</span><a href="#l555"></a>
<span id="l556">                debug.println(key + &quot; : &quot; +</span><a href="#l556"></a>
<span id="l557">                        permittedAlgs.get(key).toString());</span><a href="#l557"></a>
<span id="l558">            }</span><a href="#l558"></a>
<span id="l559">        }</span><a href="#l559"></a>
<span id="l560"></span><a href="#l560"></a>
<span id="l561">        // If there were only weak algorithms entries used, throw an exception.</span><a href="#l561"></a>
<span id="l562">        if (validEntry &amp;&amp; weakAlgs) {</span><a href="#l562"></a>
<span id="l563">            throw new SignatureException(&quot;Manifest Main Attribute check &quot; +</span><a href="#l563"></a>
<span id="l564">                    &quot;failed (&quot; + ATTR_DIGEST + &quot;).  &quot; +</span><a href="#l564"></a>
<span id="l565">                    &quot;Disabled algorithm(s) used: &quot; +</span><a href="#l565"></a>
<span id="l566">                    getWeakAlgorithms(ATTR_DIGEST));</span><a href="#l566"></a>
<span id="l567">        }</span><a href="#l567"></a>
<span id="l568"></span><a href="#l568"></a>
<span id="l569">        // this method returns 'true' if either:</span><a href="#l569"></a>
<span id="l570">        //      . manifest main attributes were not signed, or</span><a href="#l570"></a>
<span id="l571">        //      . manifest main attributes were signed and verified</span><a href="#l571"></a>
<span id="l572">        return attrsVerified;</span><a href="#l572"></a>
<span id="l573">    }</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">    /**</span><a href="#l575"></a>
<span id="l576">     * given the .SF digest header, and the data from the</span><a href="#l576"></a>
<span id="l577">     * section in the manifest, see if the hashes match.</span><a href="#l577"></a>
<span id="l578">     * if not, throw a SecurityException.</span><a href="#l578"></a>
<span id="l579">     *</span><a href="#l579"></a>
<span id="l580">     * @return true if all the -Digest headers verified</span><a href="#l580"></a>
<span id="l581">     * @exception SecurityException if the hash was not equal</span><a href="#l581"></a>
<span id="l582">     */</span><a href="#l582"></a>
<span id="l583"></span><a href="#l583"></a>
<span id="l584">    private boolean verifySection(Attributes sfAttr,</span><a href="#l584"></a>
<span id="l585">                                  String name,</span><a href="#l585"></a>
<span id="l586">                                  ManifestDigester md)</span><a href="#l586"></a>
<span id="l587">         throws IOException, SignatureException</span><a href="#l587"></a>
<span id="l588">    {</span><a href="#l588"></a>
<span id="l589">        boolean oneDigestVerified = false;</span><a href="#l589"></a>
<span id="l590">        ManifestDigester.Entry mde = md.get(name,block.isOldStyle());</span><a href="#l590"></a>
<span id="l591">        // If only weak algorithms are used.</span><a href="#l591"></a>
<span id="l592">        boolean weakAlgs = true;</span><a href="#l592"></a>
<span id="l593">        // If a &quot;*-DIGEST&quot; entry is found.</span><a href="#l593"></a>
<span id="l594">        boolean validEntry = false;</span><a href="#l594"></a>
<span id="l595"></span><a href="#l595"></a>
<span id="l596">        if (mde == null) {</span><a href="#l596"></a>
<span id="l597">            throw new SecurityException(</span><a href="#l597"></a>
<span id="l598">                  &quot;no manifest section for signature file entry &quot;+name);</span><a href="#l598"></a>
<span id="l599">        }</span><a href="#l599"></a>
<span id="l600"></span><a href="#l600"></a>
<span id="l601">        if (sfAttr != null) {</span><a href="#l601"></a>
<span id="l602">            //sun.security.util.HexDumpEncoder hex = new sun.security.util.HexDumpEncoder();</span><a href="#l602"></a>
<span id="l603">            //hex.encodeBuffer(data, System.out);</span><a href="#l603"></a>
<span id="l604"></span><a href="#l604"></a>
<span id="l605">            // go through all the attributes and process *-Digest entries</span><a href="#l605"></a>
<span id="l606">            for (Map.Entry&lt;Object,Object&gt; se : sfAttr.entrySet()) {</span><a href="#l606"></a>
<span id="l607">                String key = se.getKey().toString();</span><a href="#l607"></a>
<span id="l608"></span><a href="#l608"></a>
<span id="l609">                if (key.toUpperCase(Locale.ENGLISH).endsWith(&quot;-DIGEST&quot;)) {</span><a href="#l609"></a>
<span id="l610">                    // 7 is length of &quot;-Digest&quot;</span><a href="#l610"></a>
<span id="l611">                    String algorithm = key.substring(0, key.length()-7);</span><a href="#l611"></a>
<span id="l612">                    validEntry = true;</span><a href="#l612"></a>
<span id="l613"></span><a href="#l613"></a>
<span id="l614">                    // Check if this algorithm is permitted, skip if false.</span><a href="#l614"></a>
<span id="l615">                    if (!permittedCheck(key, algorithm)) {</span><a href="#l615"></a>
<span id="l616">                        continue;</span><a href="#l616"></a>
<span id="l617">                    }</span><a href="#l617"></a>
<span id="l618"></span><a href="#l618"></a>
<span id="l619">                    // A non-weak algorithm was used, any weak algorithms found do</span><a href="#l619"></a>
<span id="l620">                    // not need to be reported.</span><a href="#l620"></a>
<span id="l621">                    weakAlgs = false;</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">                    MessageDigest digest = getDigest(algorithm);</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">                    if (digest != null) {</span><a href="#l625"></a>
<span id="l626">                        boolean ok = false;</span><a href="#l626"></a>
<span id="l627"></span><a href="#l627"></a>
<span id="l628">                        byte[] expected =</span><a href="#l628"></a>
<span id="l629">                            Base64.getMimeDecoder().decode((String)se.getValue());</span><a href="#l629"></a>
<span id="l630">                        byte[] computed;</span><a href="#l630"></a>
<span id="l631">                        if (workaround) {</span><a href="#l631"></a>
<span id="l632">                            computed = mde.digestWorkaround(digest);</span><a href="#l632"></a>
<span id="l633">                        } else {</span><a href="#l633"></a>
<span id="l634">                            computed = mde.digest(digest);</span><a href="#l634"></a>
<span id="l635">                        }</span><a href="#l635"></a>
<span id="l636"></span><a href="#l636"></a>
<span id="l637">                        if (debug != null) {</span><a href="#l637"></a>
<span id="l638">                          debug.println(&quot;Signature Block File: &quot; +</span><a href="#l638"></a>
<span id="l639">                                   name + &quot; digest=&quot; + digest.getAlgorithm());</span><a href="#l639"></a>
<span id="l640">                          debug.println(&quot;  expected &quot; + toHex(expected));</span><a href="#l640"></a>
<span id="l641">                          debug.println(&quot;  computed &quot; + toHex(computed));</span><a href="#l641"></a>
<span id="l642">                          debug.println();</span><a href="#l642"></a>
<span id="l643">                        }</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">                        if (MessageDigest.isEqual(computed, expected)) {</span><a href="#l645"></a>
<span id="l646">                            oneDigestVerified = true;</span><a href="#l646"></a>
<span id="l647">                            ok = true;</span><a href="#l647"></a>
<span id="l648">                        } else {</span><a href="#l648"></a>
<span id="l649">                            // attempt to fallback to the workaround</span><a href="#l649"></a>
<span id="l650">                            if (!workaround) {</span><a href="#l650"></a>
<span id="l651">                               computed = mde.digestWorkaround(digest);</span><a href="#l651"></a>
<span id="l652">                               if (MessageDigest.isEqual(computed, expected)) {</span><a href="#l652"></a>
<span id="l653">                                   if (debug != null) {</span><a href="#l653"></a>
<span id="l654">                                       debug.println(&quot;  re-computed &quot; + toHex(computed));</span><a href="#l654"></a>
<span id="l655">                                       debug.println();</span><a href="#l655"></a>
<span id="l656">                                   }</span><a href="#l656"></a>
<span id="l657">                                   workaround = true;</span><a href="#l657"></a>
<span id="l658">                                   oneDigestVerified = true;</span><a href="#l658"></a>
<span id="l659">                                   ok = true;</span><a href="#l659"></a>
<span id="l660">                               }</span><a href="#l660"></a>
<span id="l661">                            }</span><a href="#l661"></a>
<span id="l662">                        }</span><a href="#l662"></a>
<span id="l663">                        if (!ok){</span><a href="#l663"></a>
<span id="l664">                            throw new SecurityException(&quot;invalid &quot; +</span><a href="#l664"></a>
<span id="l665">                                       digest.getAlgorithm() +</span><a href="#l665"></a>
<span id="l666">                                       &quot; signature file digest for &quot; + name);</span><a href="#l666"></a>
<span id="l667">                        }</span><a href="#l667"></a>
<span id="l668">                    }</span><a href="#l668"></a>
<span id="l669">                }</span><a href="#l669"></a>
<span id="l670">            }</span><a href="#l670"></a>
<span id="l671">        }</span><a href="#l671"></a>
<span id="l672"></span><a href="#l672"></a>
<span id="l673">        if (debug != null) {</span><a href="#l673"></a>
<span id="l674">            debug.println(&quot;PermittedAlgs mapping: &quot;);</span><a href="#l674"></a>
<span id="l675">            for (String key : permittedAlgs.keySet()) {</span><a href="#l675"></a>
<span id="l676">                debug.println(key + &quot; : &quot; +</span><a href="#l676"></a>
<span id="l677">                        permittedAlgs.get(key).toString());</span><a href="#l677"></a>
<span id="l678">            }</span><a href="#l678"></a>
<span id="l679">        }</span><a href="#l679"></a>
<span id="l680"></span><a href="#l680"></a>
<span id="l681">        // If there were only weak algorithms entries used, throw an exception.</span><a href="#l681"></a>
<span id="l682">        if (validEntry &amp;&amp; weakAlgs) {</span><a href="#l682"></a>
<span id="l683">            throw new SignatureException(&quot;Manifest Main Attribute check &quot; +</span><a href="#l683"></a>
<span id="l684">                    &quot;failed (DIGEST).  Disabled algorithm(s) used: &quot; +</span><a href="#l684"></a>
<span id="l685">                    getWeakAlgorithms(&quot;DIGEST&quot;));</span><a href="#l685"></a>
<span id="l686">        }</span><a href="#l686"></a>
<span id="l687"></span><a href="#l687"></a>
<span id="l688">        return oneDigestVerified;</span><a href="#l688"></a>
<span id="l689">    }</span><a href="#l689"></a>
<span id="l690"></span><a href="#l690"></a>
<span id="l691">    /**</span><a href="#l691"></a>
<span id="l692">     * Given the PKCS7 block and SignerInfo[], create an array of</span><a href="#l692"></a>
<span id="l693">     * CodeSigner objects. We do this only *once* for a given</span><a href="#l693"></a>
<span id="l694">     * signature block file.</span><a href="#l694"></a>
<span id="l695">     */</span><a href="#l695"></a>
<span id="l696">    private CodeSigner[] getSigners(SignerInfo[] infos, PKCS7 block)</span><a href="#l696"></a>
<span id="l697">        throws IOException, NoSuchAlgorithmException, SignatureException,</span><a href="#l697"></a>
<span id="l698">            CertificateException {</span><a href="#l698"></a>
<span id="l699"></span><a href="#l699"></a>
<span id="l700">        ArrayList&lt;CodeSigner&gt; signers = null;</span><a href="#l700"></a>
<span id="l701"></span><a href="#l701"></a>
<span id="l702">        for (int i = 0; i &lt; infos.length; i++) {</span><a href="#l702"></a>
<span id="l703"></span><a href="#l703"></a>
<span id="l704">            SignerInfo info = infos[i];</span><a href="#l704"></a>
<span id="l705">            ArrayList&lt;X509Certificate&gt; chain = info.getCertificateChain(block);</span><a href="#l705"></a>
<span id="l706">            CertPath certChain = certificateFactory.generateCertPath(chain);</span><a href="#l706"></a>
<span id="l707">            if (signers == null) {</span><a href="#l707"></a>
<span id="l708">                signers = new ArrayList&lt;&gt;();</span><a href="#l708"></a>
<span id="l709">            }</span><a href="#l709"></a>
<span id="l710">            // Append the new code signer. If timestamp is invalid, this</span><a href="#l710"></a>
<span id="l711">            // jar will be treated as unsigned.</span><a href="#l711"></a>
<span id="l712">            signers.add(new CodeSigner(certChain, info.getTimestamp()));</span><a href="#l712"></a>
<span id="l713"></span><a href="#l713"></a>
<span id="l714">            if (debug != null) {</span><a href="#l714"></a>
<span id="l715">                debug.println(&quot;Signature Block Certificate: &quot; +</span><a href="#l715"></a>
<span id="l716">                    chain.get(0));</span><a href="#l716"></a>
<span id="l717">            }</span><a href="#l717"></a>
<span id="l718">        }</span><a href="#l718"></a>
<span id="l719"></span><a href="#l719"></a>
<span id="l720">        if (signers != null) {</span><a href="#l720"></a>
<span id="l721">            return signers.toArray(new CodeSigner[signers.size()]);</span><a href="#l721"></a>
<span id="l722">        } else {</span><a href="#l722"></a>
<span id="l723">            return null;</span><a href="#l723"></a>
<span id="l724">        }</span><a href="#l724"></a>
<span id="l725">    }</span><a href="#l725"></a>
<span id="l726"></span><a href="#l726"></a>
<span id="l727">    // for the toHex function</span><a href="#l727"></a>
<span id="l728">    private static final char[] hexc =</span><a href="#l728"></a>
<span id="l729">            {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};</span><a href="#l729"></a>
<span id="l730">    /**</span><a href="#l730"></a>
<span id="l731">     * convert a byte array to a hex string for debugging purposes</span><a href="#l731"></a>
<span id="l732">     * @param data the binary data to be converted to a hex string</span><a href="#l732"></a>
<span id="l733">     * @return an ASCII hex string</span><a href="#l733"></a>
<span id="l734">     */</span><a href="#l734"></a>
<span id="l735"></span><a href="#l735"></a>
<span id="l736">    static String toHex(byte[] data) {</span><a href="#l736"></a>
<span id="l737"></span><a href="#l737"></a>
<span id="l738">        StringBuilder sb = new StringBuilder(data.length*2);</span><a href="#l738"></a>
<span id="l739"></span><a href="#l739"></a>
<span id="l740">        for (int i=0; i&lt;data.length; i++) {</span><a href="#l740"></a>
<span id="l741">            sb.append(hexc[(data[i] &gt;&gt;4) &amp; 0x0f]);</span><a href="#l741"></a>
<span id="l742">            sb.append(hexc[data[i] &amp; 0x0f]);</span><a href="#l742"></a>
<span id="l743">        }</span><a href="#l743"></a>
<span id="l744">        return sb.toString();</span><a href="#l744"></a>
<span id="l745">    }</span><a href="#l745"></a>
<span id="l746"></span><a href="#l746"></a>
<span id="l747">    // returns true if set contains signer</span><a href="#l747"></a>
<span id="l748">    static boolean contains(CodeSigner[] set, CodeSigner signer)</span><a href="#l748"></a>
<span id="l749">    {</span><a href="#l749"></a>
<span id="l750">        for (int i = 0; i &lt; set.length; i++) {</span><a href="#l750"></a>
<span id="l751">            if (set[i].equals(signer))</span><a href="#l751"></a>
<span id="l752">                return true;</span><a href="#l752"></a>
<span id="l753">        }</span><a href="#l753"></a>
<span id="l754">        return false;</span><a href="#l754"></a>
<span id="l755">    }</span><a href="#l755"></a>
<span id="l756"></span><a href="#l756"></a>
<span id="l757">    // returns true if subset is a subset of set</span><a href="#l757"></a>
<span id="l758">    static boolean isSubSet(CodeSigner[] subset, CodeSigner[] set)</span><a href="#l758"></a>
<span id="l759">    {</span><a href="#l759"></a>
<span id="l760">        // check for the same object</span><a href="#l760"></a>
<span id="l761">        if (set == subset)</span><a href="#l761"></a>
<span id="l762">            return true;</span><a href="#l762"></a>
<span id="l763"></span><a href="#l763"></a>
<span id="l764">        boolean match;</span><a href="#l764"></a>
<span id="l765">        for (int i = 0; i &lt; subset.length; i++) {</span><a href="#l765"></a>
<span id="l766">            if (!contains(set, subset[i]))</span><a href="#l766"></a>
<span id="l767">                return false;</span><a href="#l767"></a>
<span id="l768">        }</span><a href="#l768"></a>
<span id="l769">        return true;</span><a href="#l769"></a>
<span id="l770">    }</span><a href="#l770"></a>
<span id="l771"></span><a href="#l771"></a>
<span id="l772">    /**</span><a href="#l772"></a>
<span id="l773">     * returns true if signer contains exactly the same code signers as</span><a href="#l773"></a>
<span id="l774">     * oldSigner and newSigner, false otherwise. oldSigner</span><a href="#l774"></a>
<span id="l775">     * is allowed to be null.</span><a href="#l775"></a>
<span id="l776">     */</span><a href="#l776"></a>
<span id="l777">    static boolean matches(CodeSigner[] signers, CodeSigner[] oldSigners,</span><a href="#l777"></a>
<span id="l778">        CodeSigner[] newSigners) {</span><a href="#l778"></a>
<span id="l779"></span><a href="#l779"></a>
<span id="l780">        // special case</span><a href="#l780"></a>
<span id="l781">        if ((oldSigners == null) &amp;&amp; (signers == newSigners))</span><a href="#l781"></a>
<span id="l782">            return true;</span><a href="#l782"></a>
<span id="l783"></span><a href="#l783"></a>
<span id="l784">        boolean match;</span><a href="#l784"></a>
<span id="l785"></span><a href="#l785"></a>
<span id="l786">        // make sure all oldSigners are in signers</span><a href="#l786"></a>
<span id="l787">        if ((oldSigners != null) &amp;&amp; !isSubSet(oldSigners, signers))</span><a href="#l787"></a>
<span id="l788">            return false;</span><a href="#l788"></a>
<span id="l789"></span><a href="#l789"></a>
<span id="l790">        // make sure all newSigners are in signers</span><a href="#l790"></a>
<span id="l791">        if (!isSubSet(newSigners, signers)) {</span><a href="#l791"></a>
<span id="l792">            return false;</span><a href="#l792"></a>
<span id="l793">        }</span><a href="#l793"></a>
<span id="l794"></span><a href="#l794"></a>
<span id="l795">        // now make sure all the code signers in signers are</span><a href="#l795"></a>
<span id="l796">        // also in oldSigners or newSigners</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">        for (int i = 0; i &lt; signers.length; i++) {</span><a href="#l798"></a>
<span id="l799">            boolean found =</span><a href="#l799"></a>
<span id="l800">                ((oldSigners != null) &amp;&amp; contains(oldSigners, signers[i])) ||</span><a href="#l800"></a>
<span id="l801">                contains(newSigners, signers[i]);</span><a href="#l801"></a>
<span id="l802">            if (!found)</span><a href="#l802"></a>
<span id="l803">                return false;</span><a href="#l803"></a>
<span id="l804">        }</span><a href="#l804"></a>
<span id="l805">        return true;</span><a href="#l805"></a>
<span id="l806">    }</span><a href="#l806"></a>
<span id="l807"></span><a href="#l807"></a>
<span id="l808">    void updateSigners(CodeSigner[] newSigners,</span><a href="#l808"></a>
<span id="l809">        Hashtable&lt;String, CodeSigner[]&gt; signers, String name) {</span><a href="#l809"></a>
<span id="l810"></span><a href="#l810"></a>
<span id="l811">        CodeSigner[] oldSigners = signers.get(name);</span><a href="#l811"></a>
<span id="l812"></span><a href="#l812"></a>
<span id="l813">        // search through the cache for a match, go in reverse order</span><a href="#l813"></a>
<span id="l814">        // as we are more likely to find a match with the last one</span><a href="#l814"></a>
<span id="l815">        // added to the cache</span><a href="#l815"></a>
<span id="l816"></span><a href="#l816"></a>
<span id="l817">        CodeSigner[] cachedSigners;</span><a href="#l817"></a>
<span id="l818">        for (int i = signerCache.size() - 1; i != -1; i--) {</span><a href="#l818"></a>
<span id="l819">            cachedSigners = signerCache.get(i);</span><a href="#l819"></a>
<span id="l820">            if (matches(cachedSigners, oldSigners, newSigners)) {</span><a href="#l820"></a>
<span id="l821">                signers.put(name, cachedSigners);</span><a href="#l821"></a>
<span id="l822">                return;</span><a href="#l822"></a>
<span id="l823">            }</span><a href="#l823"></a>
<span id="l824">        }</span><a href="#l824"></a>
<span id="l825"></span><a href="#l825"></a>
<span id="l826">        if (oldSigners == null) {</span><a href="#l826"></a>
<span id="l827">            cachedSigners = newSigners;</span><a href="#l827"></a>
<span id="l828">        } else {</span><a href="#l828"></a>
<span id="l829">            cachedSigners =</span><a href="#l829"></a>
<span id="l830">                new CodeSigner[oldSigners.length + newSigners.length];</span><a href="#l830"></a>
<span id="l831">            System.arraycopy(oldSigners, 0, cachedSigners, 0,</span><a href="#l831"></a>
<span id="l832">                oldSigners.length);</span><a href="#l832"></a>
<span id="l833">            System.arraycopy(newSigners, 0, cachedSigners, oldSigners.length,</span><a href="#l833"></a>
<span id="l834">                newSigners.length);</span><a href="#l834"></a>
<span id="l835">        }</span><a href="#l835"></a>
<span id="l836">        signerCache.add(cachedSigners);</span><a href="#l836"></a>
<span id="l837">        signers.put(name, cachedSigners);</span><a href="#l837"></a>
<span id="l838">    }</span><a href="#l838"></a>
<span id="l839">}</span><a href="#l839"></a></pre>
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

